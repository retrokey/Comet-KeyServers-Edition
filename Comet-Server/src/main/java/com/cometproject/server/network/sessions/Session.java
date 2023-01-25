package com.cometproject.server.network.sessions;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.api.networking.sessions.ISession;
import com.cometproject.games.snowwar.data.SnowWarPlayerData;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.moderation.ModerationManager;
import com.cometproject.server.game.players.PlayerManager;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.game.rooms.types.components.games.RoomGame;
import com.cometproject.server.game.rooms.types.components.games.survival.SurvivalGame;
import com.cometproject.server.game.rooms.types.components.games.survival.types.SurvivalPlayer;
import com.cometproject.server.game.rooms.types.components.games.survival.types.SurvivalQueue;
import com.cometproject.server.network.messages.outgoing.notification.LogoutMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.AvatarUpdateMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.items.UpdateFloorItemMessageComposer;
import com.cometproject.server.protocol.codec.websockets.HttpCustomHandler;
import com.cometproject.server.protocol.crypto.exceptions.HabboEncryption;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.player.PlayerDao;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.UUID;


public class Session implements ISession {
    public static int CLIENT_VERSION = 0;
    private final ChannelHandlerContext channel;
    private final UUID uuid = UUID.randomUUID();
    private Logger LOGGER = LoggerFactory.getLogger("session");
    private SessionEventHandler eventHandler;

    private boolean isClone = false;
    private String uniqueId = "";
    private Player player;
    private boolean disconnectCalled = false;
    public SnowWarPlayerData snowWarPlayerData;

    private ChannelHandlerContext wsChannel;
    private final HabboEncryption encryption;
    private boolean handshakeFinished;
    private long lastPing = Comet.getTime();

    public Session(ChannelHandlerContext channel) {
        this.channel = channel;
        this.encryption = CometSettings.cryptoEnabled ? new HabboEncryption(CometSettings.crypto_e, CometSettings.crypto_n, CometSettings.crypto_d) : null;
    }

    public void initialise() {
        this.eventHandler = new SessionEventHandler(this);
    }

    public void onDisconnect() {
        if (this.disconnectCalled) {
            return;
        }

        this.disconnectCalled = true;

        PlayerManager.getInstance().getPlayerLoadExecutionService().submit(() -> {
            try {
                if (player != null && player.getData() != null)
                    PlayerManager.getInstance().remove(player.getId(), player.getData().getUsername(), this.channel.channel().attr(SessionManager.CHANNEL_ID_ATTR).get(), this.getIpAddress());

                this.eventHandler.dispose();

                if (this.player != null) {
                    if (this.getPlayer().getPermissions().getRank().modTool()) {
                        ModerationManager.getInstance().removeModerator(this);
                    }

                    if (this.getPlayer().getPermissions().getRank().messengerLogChat()) {
                        ModerationManager.getInstance().removeLogChatUser(this);
                    }

                    if (this.getPlayer().getPermissions().getRank().messengerAlfaChat()) {
                        ModerationManager.getInstance().removeAlfaChatUser(this);
                    }

                    if(this.getPlayer().getSurvivalRoomId() > 0) {
                        SurvivalQueue.getInstance().removePlayerFromQueue(this.getPlayer().getSurvivalRoomId(), this.getPlayer().getId(), this.getPlayer().getQueueData());
                    }

                    if(this.getPlayer().getEntity() != null && this.getPlayer().getEntity().isSurvivalMode()) {
                        final RoomGame game = this.getPlayer().getEntity().getRoom().getGame().getInstance();

                        if (!(game instanceof SurvivalGame))
                            return;

                        final SurvivalGame survivalGame = (SurvivalGame) game;
                        final SurvivalPlayer survivalPlayer = survivalGame.survivalPlayer(this.getPlayer().getData().getId());
                        if (survivalPlayer != null) {
                            ((SurvivalGame) game).playerLeaves(this.getPlayer().getData().getId(), true);
                        }
                    }

                    try {
                        this.getPlayer().dispose();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                this.setPlayer(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void disconnect() {
        this.onDisconnect();

        this.getChannel().disconnect();
    }

    public String getIpAddress() {
        String ipAddress = "0.0.0.0";

        if(this.getChannel().channel().hasAttr(HttpCustomHandler.WS_IP)) {
            return this.getChannel().channel().attr(HttpCustomHandler.WS_IP).get();
        } else if (this.player == null || !CometSettings.useDatabaseIp) {
            return ((InetSocketAddress) this.getChannel().channel().remoteAddress()).getAddress().getHostAddress();
        } else {
            if (this.getPlayer() != null) {
                ipAddress = PlayerDao.getIpAddress(this.getPlayer().getId());
            }
        }

        return ipAddress;
    }

    public void disconnect(String reason) {
        this.send(new LogoutMessageComposer(reason));
        this.disconnect();
    }

    public void handleMessageEvent(MessageEvent msg) {
        this.eventHandler.handle(msg);
    }

    public Session sendQueue(final IMessageComposer msg) {
        return this.send(msg, true);
    }

    public Session send(IMessageComposer msg) {
        return this.send(msg, false);
    }

    public Session send(IMessageComposer msg, boolean queue) {
        if (msg == null) {
            return this;
        }

        if (msg.getId() == 0) {
            LOGGER.debug("Unknown header ID for message: " + msg.getClass().getSimpleName());
        }

        if (!(msg instanceof AvatarUpdateMessageComposer) && !(msg instanceof UpdateFloorItemMessageComposer))
            LOGGER.debug("Sent message: " + msg.getClass().getSimpleName() + " / " + msg.getId());

        if (!queue) {
            this.channel.writeAndFlush(msg, channel.voidPromise());
        } else {
            this.channel.write(msg);
        }
        return this;
    }

    public void flush() {
        this.channel.flush();
    }

    public Logger getLogger() {
        return this.LOGGER;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        if (player == null || player.getData() == null) {
            return;
        }

        String username = player.getData().getUsername();

        this.LOGGER = LoggerFactory.getLogger("[" + username + "][" + player.getId() + "]");
        this.player = player;
        this.snowWarPlayerData = new SnowWarPlayerData(player);

        int channelId = this.channel.channel().attr(SessionManager.CHANNEL_ID_ATTR).get();

        PlayerManager.getInstance().put(player.getId(), channelId, username, this.getIpAddress());

        if (player.getPermissions().getRank().modTool()) {
            ModerationManager.getInstance().addModerator(player.getSession());
        }

        if (player.getPermissions().getRank().messengerAlfaChat()) {
            ModerationManager.getInstance().addAlfa(player.getSession());
        }

        if (player.getPermissions().getRank().messengerLogChat()) {
            ModerationManager.getInstance().addLogChatUser(player.getSession());
        }
    }

    public ChannelHandlerContext getChannel() {
        return this.channel;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public UUID getSessionId() {
        return uuid;
    }

    public HabboEncryption getEncryption() {
        return this.encryption;
    }

    public boolean isHandshakeFinished() {
        return handshakeFinished;
    }

    public void setHandshakeFinished(boolean handshakeFinished) {
        this.handshakeFinished = handshakeFinished;
    }

    public long getLastPing() {
        return lastPing;
    }

    public void setLastPing(long lastPing) {
        this.lastPing = lastPing;
    }

    public ChannelHandlerContext getWsChannel() {
        return wsChannel;
    }

    public void setWsChannel(ChannelHandlerContext wsChannel) {
        this.wsChannel = wsChannel;
    }
}
package com.cometproject.server.network.rcon;

import com.cometproject.api.config.Configuration;
import com.cometproject.api.game.GameContext;
import com.cometproject.api.game.catalog.types.bundles.RoomBundleItem;
import com.cometproject.api.game.rooms.models.CustomFloorMapData;
import com.cometproject.api.game.rooms.objects.IRoomItemData;
import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.api.utilities.JsonUtil;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.composers.catalog.BoughtItemMessageComposer;
import com.cometproject.server.composers.catalog.CatalogPublishMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.achievements.AchievementManager;
import com.cometproject.server.game.achievements.BattlePassGlobals;
import com.cometproject.server.game.achievements.types.BattlePassMission;
import com.cometproject.server.game.achievements.types.BattlePassMissionEnums;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.commands.CommandManager;
import com.cometproject.server.game.commands.user.room.BuyRoomCommand;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.landing.LandingManager;
import com.cometproject.server.game.moderation.BanManager;
import com.cometproject.server.game.moderation.ModerationManager;
import com.cometproject.server.game.moderation.types.BanType;
import com.cometproject.server.game.navigator.NavigatorManager;
import com.cometproject.server.game.permissions.PermissionsManager;
import com.cometproject.server.game.pets.PetManager;
import com.cometproject.server.game.pets.commands.PetCommandManager;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.game.quests.QuestManager;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.bundles.RoomBundleManager;
import com.cometproject.server.game.rooms.bundles.types.RoomBundle;
import com.cometproject.server.game.rooms.objects.items.RoomItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.DiceFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.wall.WheelWallItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.RoomReloadListener;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.moderation.ModToolMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.AdvancedAlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.engine.RoomForwardMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.settings.EnforceRoomCategoryMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.config.ConfigDao;
import com.cometproject.server.storage.queries.items.ItemDao;
import com.cometproject.server.storage.queries.player.PlayerDao;
import com.cometproject.server.storage.queries.rooms.RoomDao;
import com.cometproject.server.utilities.RandomUtil;
import com.cometproject.storage.api.StorageContext;
import com.google.common.collect.Sets;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class RconServer extends Thread{
    public String rPassword = Configuration.currentConfig().get("comet.rcon.tcp.password");

    private final org.apache.log4j.Logger log = Logger.getLogger(RconServer.class.getName());

    public void run(){
        while(true){
            try {
                startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startServer() throws IOException{
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        byte[] messageByte = new byte[1000];

        final int PUERTO = Integer.parseInt(Configuration.currentConfig().get("comet.rcon.tcp.port"));

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("RconServer inicializated. Waiting for incoming connections.");

            while (true) {
                sc = servidor.accept();
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                int bytesRead = in.read(messageByte);
                String message = new String(messageByte, 0, bytesRead);
                try{
                    String[] dataArray = message.split("\\|", -1);
                    System.out.println("[RCON] Packet received: " + message);
                    if(dataArray[0].equals("BAN")) BanUser(dataArray[1], dataArray[2], dataArray[3], dataArray[4], dataArray[5], out);
                    else if(dataArray[0].equals("GIVECOIN")) GiveCoin(dataArray[1], dataArray[2], dataArray[3], dataArray[4], out);
                    else if(dataArray[0].equals("GIVEBADGE")) GiveBadge(dataArray[1], dataArray[2], dataArray[3], out);
                    else if(dataArray[0].equals("ALERT")) SendAlert(dataArray[1], dataArray[2], dataArray[3], out);
                    else if(dataArray[0].equals("GLOBALALERT")) SendGlobalAlert(dataArray[1], dataArray[2], out);
                    else if(dataArray[0].equals("MUTE")) MutePlayer(dataArray[1], dataArray[2], dataArray[3], out);
                    else if(dataArray[0].equals("REFRESH")) Refresh(dataArray[1], dataArray[2], out);
                    else if(dataArray[0].equals("GOTOROOM")) GoToRoom(dataArray[1], dataArray[2], dataArray[3], out);
                    else if(dataArray[0].equals("CHANGEMOTTO")) ChangeMotto(dataArray[1], dataArray[2], dataArray[3], out);
                    else if(dataArray[0].equals("CHANGEFIGURE")) ChangeLook(dataArray[1], dataArray[2], dataArray[3], out);
                    else if(dataArray[0].equals("RELOADROOM")) ReloadRoom(dataArray[1], dataArray[2], out);
                    else if(dataArray[0].equals("AUTOFLOOR")) AutoFloor(dataArray[1], dataArray[2], out);
                    else if(dataArray[0].equals("MAXFLOOR")) MaxFloor(dataArray[1], dataArray[2], out);
                    else if(dataArray[0].equals("ALTURAFLOOR")) AlturaBuild(dataArray[1], dataArray[2], dataArray[3], out);
                    else if(dataArray[0].equals("ROTACIONFLOOR")) RotacionBuild(dataArray[1], dataArray[2], dataArray[3], out);
                    else if(dataArray[0].equals("ESTADOFLOOR")) StateBuild(dataArray[1], dataArray[2], dataArray[3], out);
                    else if(dataArray[0].equals("OPENBUILDERTOOL")) OpenBuilderMenu(dataArray[1], dataArray[2], out);
                    else if(dataArray[0].equals("BETDADOSCASINO")) BetDadosCasino(dataArray[1], dataArray[2], dataArray[3], dataArray[4], dataArray[5], out);
                    else if(dataArray[0].equals("BETRULETANORMAL")) BetRuletaNormal(dataArray[1], dataArray[2], dataArray[3], dataArray[4], dataArray[5], out);
                    else if(dataArray[0].equals("BETDADOSCASINO")) BetDadosCasino(dataArray[1], dataArray[2], dataArray[3], dataArray[4], dataArray[5], out);
                    else if(dataArray[0].equals("BETTRAGAPERRAS")) BetTragaperras(dataArray[1], dataArray[2], dataArray[3], dataArray[4], out);
                    else if(dataArray[0].equals("BETRULETACOLORES")) BetRuletaColor(dataArray[1], dataArray[2], dataArray[3], dataArray[4], out);
                    else if(dataArray[0].equals("OPENGALAXYPASS")) OpenBattlePass(dataArray[1], dataArray[2], out);
                    else if(dataArray[0].equals("GETINFOOPENGALAXYPASS")) GetBattlePassOpenInfo(dataArray[1], dataArray[2], out);
                    else if(dataArray[0].equals("BUYGALAXYPASS")) BuyGalaxyPass(dataArray[1], dataArray[2], out);
                    else if(dataArray[0].equals("SENDMENTION")) SendMention(dataArray[1], dataArray[2], dataArray[3], dataArray[4], out);
                    else if(dataArray[0].equals("CREATEWELCOMEROOM")) CreateWelcomeRoom(dataArray[1], dataArray[2], dataArray[3], out);
                    else if(dataArray[0].equals("SELLROOM")) SellRoom(dataArray[1], dataArray[2], dataArray[3], dataArray[4], dataArray[5], dataArray[6], out);
                    else SendStringToClient("Command not found", out);
                }
                catch (Exception ex){
                    SendStringToClient("Command not found", out);
                }

                sc.close();
            }

        } catch (IOException ex) {

        }
    }

    public void SellRoom(String username, String roomOwner, String roomAidi, String coinType, String coinQuantity, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        try{
            int roomId = Integer.parseInt(roomAidi);
            int quantity = Integer.parseInt(coinQuantity);

            Room room = RoomManager.getInstance().get(roomId);

            Session roomSeller = NetworkManager.getInstance().getSessions().getByPlayerUsername(roomOwner);
            if (roomSeller == null) {
                user.send(new NotificationMessageComposer("generic", "El dueño de la sala no está en linea."));
                return;
            }

            if(room != null){
                if(coinType.equals("1")){
                    if(user.getPlayer().getData().getCredits() > quantity){
                        user.getPlayer().getData().decreaseCredits(quantity);
                        roomSeller.getPlayer().getData().increaseCredits(quantity);
                    }
                    else{
                        user.send(new NotificationMessageComposer("generic", "No tienes dinero para comprar esta sala."));
                        return;
                    }
                }

                if(coinType.equals("2")){
                    if(user.getPlayer().getData().getActivityPoints() > quantity){
                        user.getPlayer().getData().decreaseActivityPoints(quantity);
                        roomSeller.getPlayer().getData().increaseActivityPoints(quantity);
                    }
                    else{
                        user.send(new NotificationMessageComposer("generic", "No tienes dinero para comprar esta sala."));
                        return;
                    }
                }

                if(coinType.equals("3")){
                    if(user.getPlayer().getData().getVipPoints() > quantity){
                        user.getPlayer().getData().decreaseVipPoints(quantity);
                        roomSeller.getPlayer().getData().increaseVipPoints(quantity);
                    }
                    else{
                        user.send(new NotificationMessageComposer("generic", "No tienes dinero para comprar esta sala."));
                        return;
                    }
                }

                user.getPlayer().sendBalance();
                user.getPlayer().flush();

                room.getData().setOwnerId(user.getPlayer().getId());
                room.getData().setOwner(user.getPlayer().getEntity().getUsername());

                GameContext.getCurrent().getRoomService().saveRoomData(user.getPlayer().getEntity().getRoom().getData());
                user.getPlayer().getData().save();
                roomSeller.getPlayer().getData().save();

                ArrayList<RoomItem> itemsToChange = new ArrayList<RoomItem>();

                itemsToChange.addAll(room.getItems().getFloorItems().values());
                itemsToChange.addAll(room.getItems().getWallItems().values());

                for(RoomItem item : itemsToChange)
                    ItemDao.updateOwnerItem(item.getId(), user.getPlayer().getId());

                RoomDao.updateOnwerRoom(room.getId(), user.getPlayer().getId(), user.getPlayer().getEntity().getUsername());

                RoomManager.getInstance().loadRoomsForUser(user.getPlayer());
                RoomManager.getInstance().loadRoomsForUser(roomSeller.getPlayer());

                RoomReloadListener reloadListener = new RoomReloadListener(room, (players, newRoom) -> {
                    for (Player player : players) {
                        if (player.getEntity() != null) continue;
                        player.getSession().send(new NotificationMessageComposer("furni_placement_error", Locale.getOrDefault("command.unload.roomReloaded", "The room was reloaded.")));
                        player.getSession().send(new RoomForwardMessageComposer(newRoom.getId()));
                    }
                });
                RoomManager.getInstance().addReloadListener(user.getPlayer().getEntity().getRoom().getId(), reloadListener);
                room.reload();
                ItemDao.updateRoomSellStatus(room.getId());
            }
            else{
                user.send(new NotificationMessageComposer("generic", "Error."));
                return;
            }

            SendStringToClient("OK", packet);
        }
        catch (Exception ex) { SendStringToClient("ERROR", packet); }
    }

    public void CreateWelcomeRoom(String username, String type, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        try{
            int roomType = Integer.parseInt(type);

            if(roomType == 1){
                RoomBundle bundle = RoomBundle.create(RoomManager.getInstance().get(318), "");
                int roomId = RoomManager.getInstance().createRoom("Sala de bienvenida", "", bundle.getRoomModelData(), 0, 20, 0, user, bundle.getConfig().getThicknessWall(), bundle.getConfig().getThicknessFloor(), bundle.getConfig().getDecorations(), bundle.getConfig().isHideWalls());
                Set<IRoomItemData> itemData = Sets.newHashSet();
                int playerId = user.getPlayer().getId();

                for (RoomBundleItem roomBundleItem : bundle.getRoomBundleData()) {
                    Position position = roomBundleItem.getWallPosition() == null ? new Position(roomBundleItem.getX(), roomBundleItem.getY(), roomBundleItem.getZ()) : null;
                    itemData.add(new RoomItemData(-1L, roomBundleItem.getItemId(), playerId, "", position, roomBundleItem.getRotation(), roomBundleItem.getExtraData(), roomBundleItem.getWallPosition(), null));
                }

                StorageContext.getCurrentContext().getRoomItemRepository().placeBundle(roomId, itemData);
                user.send(new EnforceRoomCategoryMessageComposer());
                user.send(new BoughtItemMessageComposer(BoughtItemMessageComposer.PurchaseType.BADGE));
                user.getPlayer().setLastRoomCreated((int)Comet.getTime());

                SendStringToClient("OK", packet);
                user.send(new RoomForwardMessageComposer(roomId));
            } else if(roomType == 1){
                RoomBundle bundle = RoomBundle.create(RoomManager.getInstance().get(319), "");
                int roomId = RoomManager.getInstance().createRoom("Sala de bienvenida", "", bundle.getRoomModelData(), 0, 20, 0, user, bundle.getConfig().getThicknessWall(), bundle.getConfig().getThicknessFloor(), bundle.getConfig().getDecorations(), bundle.getConfig().isHideWalls());
                Set<IRoomItemData> itemData = Sets.newHashSet();
                int playerId = user.getPlayer().getId();

                for (RoomBundleItem roomBundleItem : bundle.getRoomBundleData()) {
                    Position position = roomBundleItem.getWallPosition() == null ? new Position(roomBundleItem.getX(), roomBundleItem.getY(), roomBundleItem.getZ()) : null;
                    itemData.add(new RoomItemData(-1L, roomBundleItem.getItemId(), playerId, "", position, roomBundleItem.getRotation(), roomBundleItem.getExtraData(), roomBundleItem.getWallPosition(), null));
                }

                StorageContext.getCurrentContext().getRoomItemRepository().placeBundle(roomId, itemData);
                user.send(new EnforceRoomCategoryMessageComposer());
                user.send(new BoughtItemMessageComposer(BoughtItemMessageComposer.PurchaseType.BADGE));
                user.getPlayer().setLastRoomCreated((int)Comet.getTime());

                SendStringToClient("OK", packet);
                user.send(new RoomForwardMessageComposer(roomId));
            }
            else {
                RoomBundle bundle = RoomBundle.create(RoomManager.getInstance().get(320), "");
                int roomId = RoomManager.getInstance().createRoom("Sala de bienvenida", "", bundle.getRoomModelData(), 0, 20, 0, user, bundle.getConfig().getThicknessWall(), bundle.getConfig().getThicknessFloor(), bundle.getConfig().getDecorations(), bundle.getConfig().isHideWalls());
                Set<IRoomItemData> itemData = Sets.newHashSet();
                int playerId = user.getPlayer().getId();

                for (RoomBundleItem roomBundleItem : bundle.getRoomBundleData()) {
                    Position position = roomBundleItem.getWallPosition() == null ? new Position(roomBundleItem.getX(), roomBundleItem.getY(), roomBundleItem.getZ()) : null;
                    itemData.add(new RoomItemData(-1L, roomBundleItem.getItemId(), playerId, "", position, roomBundleItem.getRotation(), roomBundleItem.getExtraData(), roomBundleItem.getWallPosition(), null));
                }

                StorageContext.getCurrentContext().getRoomItemRepository().placeBundle(roomId, itemData);
                user.send(new EnforceRoomCategoryMessageComposer());
                user.send(new BoughtItemMessageComposer(BoughtItemMessageComposer.PurchaseType.BADGE));
                user.getPlayer().setLastRoomCreated((int)Comet.getTime());

                SendStringToClient("OK", packet);
                user.send(new RoomForwardMessageComposer(roomId));
            }
        }
        catch (Exception ex) { SendStringToClient("ERROR", packet); }
    }

    public void OpenBattlePass(String username, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        user.send(new MassEventMessageComposer("battlepass/open"));
        SendStringToClient("OK", packet);
    }

    public void BuyGalaxyPass(String username, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        if(user.getPlayer().getData().getCredits() >= 150){
            user.getPlayer().getData().decreaseCredits(150);
            user.getPlayer().sendBalance();
            user.getPlayer().getData().createBattlePass();
            user.send(new NotificationMessageComposer("generic", "¡Felicidades! Has comprado el GalaxyPass."));

            SendStringToClient("OK", packet);
            user.getPlayer().getData().updateBattlePass();
        }
        else {
            user.send(new NotificationMessageComposer("generic", "Necesitas 10 créditos para poder comprar el GalaxyPass"));
            SendStringToClient("ERROR", packet);
        }

    }

    public void SendMention(String username, String userToMention, String message, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(userToMention);
        if(user == null) return;

        user.send(new WhisperMessageComposer(user.getPlayer().getEntity().getId(), "El usuario " + username + " dice: " + message, 34));
        user.send(new MassEventMessageComposer("mal/o/" + username + "/" + message));

        SendStringToClient("OK", packet);
    }

    public void GetBattlePassOpenInfo(String username, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        SendStringToClient(user.getPlayer().getData().getBattlePassInfo(), packet);
    }

    public void OpenBuilderMenu(String username, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        if(!user.getPlayer().getEntity().getRoom().getRights().hasRights(user.getPlayer().getId()) && !user.getPlayer().getPermissions().getRank().roomFullControl()){
            user.send(new NotificationMessageComposer("generic", "No tienes permisos en esta sala."));
            return;
        }

        user.send(new MassEventMessageComposer("bawtool/show"));
        SendStringToClient("OK", packet);
    }

    public void BetTragaperras(String username, String quantity, String coin, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        try{
            int cantidad = Integer.parseInt(quantity);
            int moneda = Integer.parseInt(coin);

            if(cantidad == 0){
                user.send(new NotificationMessageComposer("generic", "No puedes apostar 0."));
                return;
            }

            if(user.getPlayer().getData().itemOnBet != null){
                if(moneda == 1){
                    if(user.getPlayer().getData().getCredits() >= cantidad){
                        user.getPlayer().getData().decreaseCredits(cantidad);
                        user.getPlayer().sendBalance();
                        user.getPlayer().getData().betMoney = cantidad;
                        user.getPlayer().getData().coinOnBet = moneda;
                        user.getPlayer().getData().hasPaidBet = true;

                        SendStringToClient("OK", packet);
                        user.getPlayer().getData().itemOnBet.onInteract(user.getPlayer().getEntity(), 1, false);
                    }
                    else{
                        user.send(new NotificationMessageComposer("generic", "No tienes dinero para realizar esta apuesta."));
                        SendStringToClient("ERROR", packet);
                    }
                }
                else if(moneda == 2){
                    if(user.getPlayer().getData().getActivityPoints() >= cantidad){
                        user.getPlayer().getData().decreaseActivityPoints(cantidad);
                        user.getPlayer().sendBalance();
                        user.getPlayer().getData().betMoney = cantidad;
                        user.getPlayer().getData().coinOnBet = moneda;
                        user.getPlayer().getData().hasPaidBet = true;

                        SendStringToClient("OK", packet);
                        user.getPlayer().getData().itemOnBet.onInteract(user.getPlayer().getEntity(), 1, false);
                    }
                    else{
                        user.send(new NotificationMessageComposer("generic", "No tienes dinero para realizar esta apuesta."));
                        SendStringToClient("ERROR", packet);
                    }
                }
                else if(moneda == 3){
                    if(user.getPlayer().getData().getVipPoints() >= cantidad){
                        user.getPlayer().getData().decreaseVipPoints(cantidad);
                        user.getPlayer().sendBalance();
                        user.getPlayer().getData().betMoney = cantidad;
                        user.getPlayer().getData().coinOnBet = moneda;
                        user.getPlayer().getData().hasPaidBet = true;

                        SendStringToClient("OK", packet);
                        user.getPlayer().getData().itemOnBet.onInteract(user.getPlayer().getEntity(), 1, false);
                    }
                    else{
                        user.send(new NotificationMessageComposer("generic", "No tienes dinero para realizar esta apuesta."));
                        SendStringToClient("ERROR", packet);
                    }
                }
            }
        }
        catch (Exception ex){
            SendStringToClient("ERROR", packet);
        }
    }

    public void BetRuletaColor(String username, String quantity, String coin, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        try{
            int cantidad = Integer.parseInt(quantity);
            int moneda = Integer.parseInt(coin);

            if(cantidad == 0){
                user.send(new NotificationMessageComposer("generic", "No puedes apostar 0."));
                return;
            }

            if(user.getPlayer().getData().itemOnBet != null){
                if(moneda == 1){
                    if(user.getPlayer().getData().getCredits() >= cantidad){
                        user.getPlayer().getData().decreaseCredits(cantidad);
                        user.getPlayer().sendBalance();
                        user.getPlayer().getData().betMoney = cantidad;
                        user.getPlayer().getData().coinOnBet = moneda;
                        user.getPlayer().getData().hasPaidBet = true;
                        ((WheelWallItem)user.getPlayer().getData().itemOnBet).client = user;

                        SendStringToClient("OK", packet);
                        user.getPlayer().getData().itemOnBet.onInteract(user.getPlayer().getEntity(), 1, false);
                    }
                    else{
                        user.send(new NotificationMessageComposer("generic", "No tienes dinero para realizar esta apuesta."));
                        SendStringToClient("ERROR", packet);
                    }
                }
                else if(moneda == 2){
                    if(user.getPlayer().getData().getActivityPoints() >= cantidad){
                        user.getPlayer().getData().decreaseActivityPoints(cantidad);
                        user.getPlayer().sendBalance();
                        user.getPlayer().getData().betMoney = cantidad;
                        user.getPlayer().getData().coinOnBet = moneda;
                        user.getPlayer().getData().hasPaidBet = true;
                        ((WheelWallItem)user.getPlayer().getData().itemOnBet).client = user;

                        SendStringToClient("OK", packet);
                        user.getPlayer().getData().itemOnBet.onInteract(user.getPlayer().getEntity(), 1, false);
                    }
                    else{
                        user.send(new NotificationMessageComposer("generic", "No tienes dinero para realizar esta apuesta."));
                        SendStringToClient("ERROR", packet);
                    }
                }
                else if(moneda == 3){
                    if(user.getPlayer().getData().getVipPoints() >= cantidad){
                        user.getPlayer().getData().decreaseVipPoints(cantidad);
                        user.getPlayer().sendBalance();
                        user.getPlayer().getData().betMoney = cantidad;
                        user.getPlayer().getData().coinOnBet = moneda;
                        user.getPlayer().getData().hasPaidBet = true;
                        ((WheelWallItem)user.getPlayer().getData().itemOnBet).client = user;

                        SendStringToClient("OK", packet);
                        user.getPlayer().getData().itemOnBet.onInteract(user.getPlayer().getEntity(), 1, false);
                    }
                    else{
                        user.send(new NotificationMessageComposer("generic", "No tienes dinero para realizar esta apuesta."));
                        SendStringToClient("ERROR", packet);
                    }
                }
            }
        }
        catch (Exception ex){
            SendStringToClient("ERROR", packet);
        }
    }

    public void BetDadosCasino(String username, String quantity, String coin, String number, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        try{
            int cantidad = Integer.parseInt(quantity);
            int numero = Integer.parseInt(number);
            int moneda = Integer.parseInt(coin);

            if(cantidad == 0){
                user.send(new NotificationMessageComposer("generic", "No puedes apostar 0."));
                return;
            }

            if(numero > 6 || numero < 1){
                user.send(new NotificationMessageComposer("generic", "Debes apostar un número entre el 1 y el 6."));
                return;
            }

            if(user.getPlayer().getData().itemOnBet != null){
                if(moneda == 1){
                    if(user.getPlayer().getData().getCredits() >= cantidad){
                        user.getPlayer().getData().decreaseCredits(cantidad);
                        user.getPlayer().sendBalance();
                        user.getPlayer().getData().betMoney = cantidad;
                        user.getPlayer().getData().coinOnBet = moneda;
                        user.getPlayer().getData().betNumber = numero;
                        user.getPlayer().getData().hasPaidBet = true;
                        ((DiceFloorItem)user.getPlayer().getData().itemOnBet).client = user;

                        SendStringToClient("OK", packet);
                        user.getPlayer().getData().itemOnBet.onInteract(user.getPlayer().getEntity(), 1, false);
                    }
                    else{
                        user.send(new NotificationMessageComposer("generic", "No tienes dinero para realizar esta apuesta."));
                        SendStringToClient("ERROR", packet);
                    }
                }
                else if(moneda == 2){
                    if(user.getPlayer().getData().getActivityPoints() >= cantidad){
                        user.getPlayer().getData().decreaseActivityPoints(cantidad);
                        user.getPlayer().sendBalance();
                        user.getPlayer().getData().betMoney = cantidad;
                        user.getPlayer().getData().coinOnBet = moneda;
                        user.getPlayer().getData().betNumber = numero;
                        user.getPlayer().getData().hasPaidBet = true;
                        ((DiceFloorItem)user.getPlayer().getData().itemOnBet).client = user;

                        SendStringToClient("OK", packet);
                        user.getPlayer().getData().itemOnBet.onInteract(user.getPlayer().getEntity(), 1, false);
                    }
                    else{
                        user.send(new NotificationMessageComposer("generic", "No tienes dinero para realizar esta apuesta."));
                        SendStringToClient("ERROR", packet);
                    }
                }
                else if(moneda == 3){
                    if(user.getPlayer().getData().getVipPoints() >= cantidad){
                        user.getPlayer().getData().decreaseVipPoints(cantidad);
                        user.getPlayer().sendBalance();
                        user.getPlayer().getData().betMoney = cantidad;
                        user.getPlayer().getData().coinOnBet = moneda;
                        user.getPlayer().getData().betNumber = numero;
                        user.getPlayer().getData().hasPaidBet = true;
                        ((DiceFloorItem)user.getPlayer().getData().itemOnBet).client = user;

                        SendStringToClient("OK", packet);
                        user.getPlayer().getData().itemOnBet.onInteract(user.getPlayer().getEntity(), 1, false);
                    }
                    else{
                        user.send(new NotificationMessageComposer("generic", "No tienes dinero para realizar esta apuesta."));
                        SendStringToClient("ERROR", packet);
                    }
                }
            }
        }
        catch (Exception ex){
            SendStringToClient("ERROR", packet);
        }
    }

    public void BetRuletaNormal(String username, String quantity, String coin, String number, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        try{
            int cantidad = Integer.parseInt(quantity);
            int numero = Integer.parseInt(number);
            int moneda = Integer.parseInt(coin);

            if(cantidad == 0){
                user.send(new NotificationMessageComposer("generic", "No puedes apostar 0."));
                return;
            }

            if(numero > 32 || numero < 0){
                user.send(new NotificationMessageComposer("generic", "Debes apostar un número entre el 0 y el 32."));
                return;
            }

            if(moneda == 1){
                if(user.getPlayer().getData().getCredits() >= cantidad){
                    user.getPlayer().getData().decreaseCredits(cantidad);
                    user.getPlayer().sendBalance();
                }
                else{
                    user.send(new NotificationMessageComposer("generic", "No tienes dinero para realizar esta apuesta."));
                    SendStringToClient("ERROR", packet);
                    return;
                }
            }
            else if(moneda == 2){
                if(user.getPlayer().getData().getActivityPoints() >= cantidad){
                    user.getPlayer().getData().decreaseActivityPoints(cantidad);
                    user.getPlayer().sendBalance();
                }
                else{
                    user.send(new NotificationMessageComposer("generic", "No tienes dinero para realizar esta apuesta."));
                    SendStringToClient("ERROR", packet);
                    return;
                }
            }
            else if(moneda == 3){
                if(user.getPlayer().getData().getVipPoints() >= cantidad){
                    user.getPlayer().getData().decreaseVipPoints(cantidad);
                    user.getPlayer().sendBalance();
                }
                else{
                    user.send(new NotificationMessageComposer("generic", "No tienes dinero para realizar esta apuesta."));
                    SendStringToClient("ERROR", packet);
                    return;
                }
            }

            BattlePassMission ms = BattlePassGlobals.battlePassMissions.stream().filter(x -> x.type == BattlePassMissionEnums.MissionType.PLAYCASINO).findAny().orElse(null);
            if(ms != null){
                if(user.getPlayer().getData().battlePass != null) user.getPlayer().getData().battlePass.addExperiencePoint(ms.id);
            }

            if(numero % 2 == 0) user.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new TalkMessageComposer(user.getPlayer().getEntity().getId(), "Apostando al número " + numero + ", par.", ChatEmotion.NONE, 26));
            else user.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new TalkMessageComposer(user.getPlayer().getEntity().getId(), "Apostando al número " + numero + ", impar.", ChatEmotion.NONE, 26));

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    int betNumber = RandomUtil.getRandomInt(0,36);
                    String messageToSend = "";
                    if(betNumber == numero && betNumber == 0){
                        int moneyToWin = cantidad * 5;
                        if(moneda == 1){
                            user.getPlayer().getData().increaseCredits(moneyToWin);
                            user.getPlayer().sendBalance();

                            messageToSend = "¡PLENO! 0, verde. Has ganado " + moneyToWin + " créditos.";
                        }
                        else if(moneda == 2){
                            user.getPlayer().getData().increaseActivityPoints(moneyToWin);
                            user.getPlayer().sendBalance();

                            messageToSend = "¡PLENO! 0, verde. Has ganado " + moneyToWin + " asteroides.";
                        }
                        else if(moneda == 3){
                            user.getPlayer().getData().increaseVipPoints(moneyToWin);
                            user.getPlayer().sendBalance();

                            messageToSend = "¡PLENO! 0, verde. Has ganado " + moneyToWin + " cometas.";
                        }
                    }
                    else if(betNumber == numero && betNumber != 0){
                        int moneyToWin = cantidad * 5;
                        if(moneda == 1){
                            user.getPlayer().getData().increaseCredits(moneyToWin);
                            user.getPlayer().sendBalance();

                            messageToSend = "¡PLENO! " + betNumber + ". Has ganado " + moneyToWin + " créditos.";
                        }
                        else if(moneda == 2){
                            user.getPlayer().getData().increaseActivityPoints(moneyToWin);
                            user.getPlayer().sendBalance();

                            messageToSend = "¡PLENO! " + betNumber + ". Has ganado " + moneyToWin + " asteroides.";
                        }
                        else if(moneda == 3){
                            user.getPlayer().getData().increaseVipPoints(moneyToWin);
                            user.getPlayer().sendBalance();

                            messageToSend = "¡PLENO! " + betNumber + ". Has ganado " + moneyToWin + " cometas.";
                        }
                    }
                    else if(betNumber % 2 == 0 && numero % 2 == 0){
                        int moneyToWin = (int)Math.round(cantidad * 1.2);
                        if(moneda == 1){
                            user.getPlayer().getData().increaseCredits(moneyToWin);
                            user.getPlayer().sendBalance();

                            messageToSend = betNumber + ", impar. Has ganado " + moneyToWin + " créditos.";
                        }
                        else if(moneda == 2){
                            user.getPlayer().getData().increaseActivityPoints(moneyToWin);
                            user.getPlayer().sendBalance();

                            messageToSend = betNumber + ", impar. Has ganado " + moneyToWin + " asteroides.";
                        }
                        else if(moneda == 3){
                            user.getPlayer().getData().increaseVipPoints(moneyToWin);
                            user.getPlayer().sendBalance();

                            messageToSend = betNumber + ", impar. Has ganado " + moneyToWin + " cometas.";
                        }
                    }
                    else if(betNumber % 2 != 0 && numero % 2 != 0){
                        int moneyToWin = (int)Math.round(cantidad * 1.2);
                        if(moneda == 1){
                            user.getPlayer().getData().increaseCredits(moneyToWin);
                            user.getPlayer().sendBalance();

                            messageToSend = betNumber + ", impar. Has ganado " + moneyToWin + " créditos.";
                        }
                        else if(moneda == 2){
                            user.getPlayer().getData().increaseActivityPoints(moneyToWin);
                            user.getPlayer().sendBalance();

                            messageToSend = betNumber + ", impar. Has ganado " + moneyToWin + " asteroides.";
                        }
                        else if(moneda == 3){
                            user.getPlayer().getData().increaseVipPoints(moneyToWin);
                            user.getPlayer().sendBalance();

                            messageToSend = betNumber + ", impar. Has ganado " + moneyToWin + " cometas.";
                        }
                    }
                    else messageToSend = betNumber + ". Has perdido.";

                    user.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new TalkMessageComposer(user.getPlayer().getEntity().getId(), messageToSend, ChatEmotion.NONE, 26));

                    user.getPlayer().getData().itemOnBet = null;
                    user.getPlayer().getData().betMoney = 0;
                    user.getPlayer().getData().coinOnBet = 0;
                    user.getPlayer().getData().betNumber = 0;
                    user.getPlayer().getData().hasPaidBet = false;
                }
            }, 4000);
        }
        catch (Exception ex){
            SendStringToClient("ERROR", packet);
        }
    }

    public void AlturaBuild(String username, String altura, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        if(!user.getPlayer().getEntity().getRoom().getRights().hasRights(user.getPlayer().getId()) && !user.getPlayer().getPermissions().getRank().roomFullControl()){
            user.send(new NotificationMessageComposer("generic", "No tienes permisos en esta sala."));
            return;
        }

        try{
            double height;

            try {
                height = Double.parseDouble(altura);
            } catch (Exception e) {
                height = -1;
            }

            if(height == 999){
                user.getPlayer().getEntity().setzok = false;
                user.send(new NotificationMessageComposer("generic", "Altura reestablecida"));
                user.flush();
            }

            if (height < -100 || height > 100) {
                user.getPlayer().getEntity().setzok = false;
                user.flush();
                return;
            }

            user.getPlayer().setItemPlacementHeight(height);
            user.getPlayer().getEntity().setzok = true;
            user.flush();
            user.send(new NotificationMessageComposer("generic", "La altura se ha actualizado a " + height));
            SendStringToClient("OK", packet);
        }
        catch (Exception ex){
            SendStringToClient("Error", packet);
        }
    }

    public void RotacionBuild(String username, String altura, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        if(!user.getPlayer().getEntity().getRoom().getRights().hasRights(user.getPlayer().getId()) && !user.getPlayer().getPermissions().getRank().roomFullControl()){
            user.send(new NotificationMessageComposer("generic", "No tienes permisos en esta sala."));
            return;
        }

        try{
            int height;

            try {
                height = Integer.parseInt(altura);
            } catch (Exception e) {
                height = -1;
            }

            if (height < -1 || height > 64) {
                SendStringToClient("Error", packet);
                return;
            }

            user.getPlayer().setItemPlacementRotation(height);
            user.send(new NotificationMessageComposer("generic", "Se ha cambiado la rotación a " + height));
            SendStringToClient("OK", packet);
        }
        catch (Exception ex){
            SendStringToClient("Error", packet);
            return;
        }
    }

    public void StateBuild(String username, String altura, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        if(!user.getPlayer().getEntity().getRoom().getRights().hasRights(user.getPlayer().getId()) && !user.getPlayer().getPermissions().getRank().roomFullControl()){
            user.send(new NotificationMessageComposer("generic", "No tienes permisos en esta sala."));
            return;
        }

        try{
            int height;

            try {
                height = Integer.parseInt(altura);
            } catch (Exception e) {
                height = -1;
            }

            if (height < -1 || height > 64) {
                SendStringToClient("Error", packet);
                return;
            }

            user.getPlayer().setItemPlacementState(height);
            user.send(new NotificationMessageComposer("generic", "Se ha cambiado el estado a " + height));
            SendStringToClient("OK", packet);
        }
        catch (Exception ex){
            SendStringToClient("Error", packet);
            return;
        }
    }

    public void ReloadRoom(String username, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        if(!user.getPlayer().getEntity().getRoom().getRights().hasRights(user.getPlayer().getId()) && !user.getPlayer().getPermissions().getRank().roomFullControl()){
            user.send(new NotificationMessageComposer("generic", "No tienes permisos en esta sala."));
            return;
        }

        Room room = user.getPlayer().getEntity().getRoom();
        RoomReloadListener reloadListener = new RoomReloadListener(room, (players, newRoom) -> {
            for (Player player : players) {
                if (player.getEntity() != null) continue;
                player.getSession().send(new NotificationMessageComposer("furni_placement_error", Locale.getOrDefault("command.unload.roomReloaded", "The room was reloaded.")));
                player.getSession().send(new RoomForwardMessageComposer(newRoom.getId()));
            }
        });
        RoomManager.getInstance().addReloadListener(user.getPlayer().getEntity().getRoom().getId(), reloadListener);
        room.reload();

        SendStringToClient("OK", packet);
    }

    public void AutoFloor(String username, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        if(!user.getPlayer().getEntity().getRoom().getRights().hasRights(user.getPlayer().getId()) && !user.getPlayer().getPermissions().getRank().roomFullControl()){
            user.send(new NotificationMessageComposer("generic", "No tienes permisos en esta sala."));
            return;
        }

        Room room = user.getPlayer().getEntity().getRoom();
        int sizeX = room.getMapping().getModel().getSizeX();
        int sizeY = room.getMapping().getModel().getSizeY();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < sizeY; ++i) {
            StringBuilder text2 = new StringBuilder();
            for (int j = 0; j < sizeX; ++j) {
                if (!room.getMapping().getTile(j, i).hasItems()) {
                    text2.append("x");
                    continue;
                }
                text2.append(this.parseInvers(room.getMapping().getTile(j, i).getTileHeight()));
            }
            text.append((CharSequence)text2);
            text.append('\r');
        }
        CustomFloorMapData floorMapData = new CustomFloorMapData(room.getModel().getDoorX(), room.getModel().getDoorY(), room.getModel().getDoorRotation(), text.toString().trim(), room.getModel().getRoomModelData().getWallHeight());
        room.getData().setHeightmap(JsonUtil.getInstance().toJson((Object)floorMapData));
        GameContext.getCurrent().getRoomService().saveRoomData(room.getData());
        room.getItems().commit();
        RoomReloadListener reloadListener = new RoomReloadListener(room, (players, newRoom) -> {
            for (Player player : players) {
                if (player.getEntity() != null) continue;
                player.getSession().send(new NotificationMessageComposer("furni_placement_error", Locale.get("command.floor.complete")));
                player.getSession().send(new RoomForwardMessageComposer(newRoom.getId()));
            }
        });
        RoomManager.getInstance().addReloadListener(room.getId(), reloadListener);
        room.reload();

        SendStringToClient("OK", packet);
    }

    public void MaxFloor(String username, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) return;

        if(!user.getPlayer().getEntity().getRoom().getRights().hasRights(user.getPlayer().getId()) && !user.getPlayer().getPermissions().getRank().roomFullControl()){
            user.send(new NotificationMessageComposer("generic", "No tienes permisos en esta sala."));
            return;
        }

        int maxLength = 64;
        Integer lengthX = maxLength;
        Integer lengthY = maxLength;

        StringBuilder map = new StringBuilder();
        for (int y = 0; y <= lengthY; ++y) {
            for (int x = 0; x <= lengthX; ++x) {
                if (y == 0) {
                    map.append("x");
                }
                else if (y == 1 && x == 0) {
                    map.append("0");
                }
                else if (x == 0) {
                    map.append("x");
                }
                else {
                    map.append("0");
                }
            }
            map.append("\r");
        }

        int doorX = 0;
        int doorY = 1;
        int doorRotation = 2;
        int wallHeight = -1;

        Room room = user.getPlayer().getEntity().getRoom();

        CustomFloorMapData floorMapData = new CustomFloorMapData(
                doorX,
                doorY,
                doorRotation,
                map.toString().trim(),
                wallHeight);

        room.getData().setHeightmap(JsonUtil.getInstance().toJson(floorMapData));

        GameContext.getCurrent().getRoomService().saveRoomData(room.getData());
        room.getItems().commit();

        RoomReloadListener reloadListener = new RoomReloadListener(room, (players, newRoom) -> {

            for (Player player : players) {
                if (player.getEntity() != null) continue;
                player.getSession().send(new NotificationMessageComposer("furni_placement_error", Locale.get("command.floor.complete")));
                player.getSession().send(new RoomForwardMessageComposer(newRoom.getId()));
            }
        });

        RoomManager.getInstance().addReloadListener(room.getId(), reloadListener);
        room.reload();
        SendStringToClient("OK", packet);
    }

    private char parseInvers(double input) {
        int result = input != 0.0 ? (input != 1.0 ? (input != 2.0 ? (input != 3.0 ? (input != 4.0 ? (input != 5.0 ? (input != 6.0 ? (input != 7.0 ? (input != 8.0 ? (input != 9.0 ? (input != 10.0 ? (input != 11.0 ? (input != 12.0 ? (input != 13.0 ? (input != 14.0 ? (input != 15.0 ? (input != 16.0 ? (input != 17.0 ? (input != 18.0 ? (input != 19.0 ? (input != 20.0 ? (input != 21.0 ? (input != 22.0 ? (input != 23.0 ? (input != 24.0 ? (input != 25.0 ? (input != 26.0 ? (input != 27.0 ? (input != 28.0 ? (input != 29.0 ? (input != 30.0 ? (input != 31.0 ? (input != 32.0 ? 120 : 119) : 118) : 117) : 116) : 115) : 114) : 113) : 112) : 111) : 110) : 109) : 108) : 107) : 106) : 105) : 104) : 103) : 102) : 101) : 100) : 99) : 98) : 97) : 57) : 56) : 55) : 54) : 53) : 52) : 51) : 50) : 49) : 48;
        return (char) result;
    }

    public void BanUser(String username, String type, String time, String reason, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        try{
            int seconds = Integer.parseInt(time);
            if(type.equalsIgnoreCase("player")){
                if(seconds == 0) seconds = 999999999;

                Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
                if(user == null) return;

                user.disconnect("banned");
                long expire = Comet.getTime() + (long)(seconds * 3600);
                BanManager.getInstance().banPlayer(BanType.USER, user.getPlayer().getId() + "", seconds, expire, reason, 0);
                SendStringToClient("Ok", packet);
            }
            else if(type.equalsIgnoreCase("ip")){
                if(seconds == 0) seconds = 999999999;

                long expire = Comet.getTime() + (long)(seconds * 3600);
                BanManager.getInstance().banPlayer(BanType.IP, username, seconds, expire, reason, 0);
                SendStringToClient("Ok", packet);
            }
            else if(type.equalsIgnoreCase("trades")){
                if(seconds == 0) seconds = 999999999;

                Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
                if(user == null) return;

                long expire = Comet.getTime() + (long)(seconds * 3600);

                user.getPlayer().getStats().setTradeLock(expire);
                user.getPlayer().getSettings().setAllowTrade(false);
                user.getPlayer().getSettings().flush();
                user.getPlayer().getStats().addBan();
                user.getPlayer().getStats().save();
                user.send(new NotificationMessageComposer("trade_block", Locale.getOrDefault("user.got.tradeblocked", "Se ha detectado una actividad sospechosa en tu cuenta y tus tradeos han sido bloqueados durante " + seconds + " minutos.")));
                BanManager.getInstance().banPlayer(BanType.TRADE, user.getPlayer().getId() + "", seconds, expire, reason, 0);
                SendStringToClient("Ok", packet);
            }
            else SendStringToClient("Error", packet);
        }
        catch (Exception ex){ }
    }

    public void GiveCoin(String username, String coin, String quantity, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        try{
            int cash = Integer.parseInt(quantity);

            Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
            if(user == null) return;

            if(coin.equalsIgnoreCase("creditos")){
                user.getPlayer().getData().increaseCredits(cash);
                user.getPlayer().sendBalance();

                SendStringToClient("Ok", packet);
            }
            else if(coin.equalsIgnoreCase("diamantes")){
                user.getPlayer().getData().increaseVipPoints(cash);
                user.getPlayer().sendBalance();

                SendStringToClient("Ok", packet);
            }
            else if(coin.equalsIgnoreCase("duckets")){
                user.getPlayer().getData().increaseBlackMoney(cash);
                user.getPlayer().sendBalance();

                SendStringToClient("Ok", packet);
            }
            else SendStringToClient("Error", packet);
        }
        catch (Exception ex){ }
    }

    public void GiveBadge(String username, String badgeCode, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) SendStringToClient("Error", packet);
        else{
            user.getPlayer().getInventory().addBadge(badgeCode, true);
            SendStringToClient("Ok", packet);
        }
    }

    public void SendAlert(String username, String text, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        if(user == null) SendStringToClient("Error", packet);
        else{
            user.send(new AlertMessageComposer(text));
            SendStringToClient("Ok", packet);
        }
    }

    public void SendGlobalAlert(String text, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        for (Session player : ModerationManager.getInstance().getLogChatUsers()) {
            player.send(new AlertMessageComposer(text));
        }

        SendStringToClient("Ok", packet);
    }

    public void MutePlayer(String username, String time, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        try{
            int minutes = Integer.parseInt(time);

            Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
            if(user == null) SendStringToClient("Error", packet);
            else{
                final int timeMuted = (int) Comet.getTime() + minutes;

                PlayerDao.addTimeMute(user.getPlayer().getId(), timeMuted);
                user.getPlayer().getData().setTimeMuted(timeMuted);
                user.send(new AdvancedAlertMessageComposer(Locale.getOrDefault("command.mute.muted", "You are muted for violating the rules! Your mute will expire in %timeleft% minutes").replace("%timeleft%", time + "")));
                SendStringToClient("Ok", packet);
            }
        }
        catch (Exception ex){ }
    }

    public void Refresh(String section, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        if(section.equalsIgnoreCase("bans")) BanManager.getInstance().loadBans();
        else if(section.equalsIgnoreCase("catalog")){
            CatalogManager.getInstance().loadItemsAndPages();
            CatalogManager.getInstance().loadGiftBoxes();
            CatalogManager.getInstance().loadNuxGifts();
            CatalogManager.getInstance().loadClothingItems();
            NetworkManager.getInstance().getSessions().broadcast(new CatalogPublishMessageComposer(false));
        }
        else if(section.equalsIgnoreCase("products")) CatalogManager.getInstance().loadRPProducts();
        else if(section.equalsIgnoreCase("offers")) CatalogManager.getInstance().loadOffers();
        else if(section.equalsIgnoreCase("navigator")){
            NavigatorManager.getInstance().loadCategories();
            NavigatorManager.getInstance().loadPublicRooms();
            NavigatorManager.getInstance().loadStaffPicks();
        }
        else if(section.equalsIgnoreCase("permissions")){
            PermissionsManager.getInstance().loadPermissions();
            PermissionsManager.getInstance().loadPerks();
            PermissionsManager.getInstance().loadEffectsOverride();
            PermissionsManager.getInstance().loadEffects();
        }
        else if(section.equalsIgnoreCase("config")) ConfigDao.getAll();
        else if(section.equalsIgnoreCase("survival")) ConfigDao.getSurvivalSettings();
        else if(section.equalsIgnoreCase("news")) LandingManager.getInstance().loadArticles();
        else if(section.equalsIgnoreCase("items")) ItemManager.getInstance().loadItemDefinitions();
        else if(section.equalsIgnoreCase("filter")) RoomManager.getInstance().getFilter().loadFilter();
        else if(section.equalsIgnoreCase("locale")){
            Locale.reload();
            CommandManager.getInstance().reloadAllCommands();
        }
        else if(section.equalsIgnoreCase("modpresets")){
            ModerationManager.getInstance().loadPresets();
            ModerationManager.getInstance().getModerators().forEach(session -> session.send(new ModToolMessageComposer()));
        }
        else if(section.equalsIgnoreCase("groupitems")) GameContext.getCurrent().getGroupService().getItemService().load();
        else if(section.equalsIgnoreCase("models")) GameContext.getCurrent().getRoomModelService().loadModels();
        else if(section.equalsIgnoreCase("music")) ItemManager.getInstance().loadMusicData();
        else if(section.equalsIgnoreCase("quests")) QuestManager.getInstance().loadQuests();
        else if(section.equalsIgnoreCase("achievements")) AchievementManager.getInstance().loadAchievements();
        else if(section.equalsIgnoreCase("pets")){
            PetManager.getInstance().loadPetRaces();
            PetManager.getInstance().loadPetSpeech();
            PetManager.getInstance().loadTransformablePets();
            PetManager.getInstance().loadPetBreedPallets();
            PetCommandManager.getInstance().initialize();
        }
        else if(section.equalsIgnoreCase("crafting")) ItemManager.getInstance().loadCraftingMachines();
        else if(section.equalsIgnoreCase("bundles")) RoomBundleManager.getInstance().initialize();

        SendStringToClient("Ok", packet);
    }

    public void GoToRoom(String username, String room, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        try{
            int roomId = Integer.parseInt(room);

            Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
            if(user == null) SendStringToClient("Error", packet);
            else {
                user.send(new RoomForwardMessageComposer(roomId));
                SendStringToClient("Ok", packet);
            }
        }
        catch (Exception ex){ }
    }

    public void ChangeMotto(String username, String motto, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        try{

            Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
            if(user == null) SendStringToClient("Error", packet);
            else {
                user.getPlayer().getData().setMotto(motto);
                user.getPlayer().getData().save();
                user.getPlayer().poof();
                SendStringToClient("Ok", packet);
            }
        }
        catch (Exception ex){ }
    }

    public void ChangeLook(String username, String figure, String rconPassword, DataOutputStream packet){
        if(!(rPassword.equals(rconPassword) || rPassword.contains(rconPassword))){
            SendStringToClient("Incorrect password.", packet);
            return;
        }

        try{

            Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
            if(user == null) SendStringToClient("Error", packet);
            else {
                user.getPlayer().getData().setFigure(figure);
                user.getPlayer().getData().save();
                user.getPlayer().poof();
                SendStringToClient("Ok", packet);
            }
        }
        catch (Exception ex){ }
    }


    public boolean SendStringToClient(String message, DataOutputStream packet){
        try {
            byte[] b = message.getBytes(StandardCharsets.US_ASCII);
            packet.write(b);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String merge(String[] params, int begin) {
        final StringBuilder mergedParams = new StringBuilder();

        for (int i = 0; i < params.length; i++) {
            if (i >= begin) {
                mergedParams.append(params[i]).append(" ");
            }
        }

        return mergedParams.toString();
    }

    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}

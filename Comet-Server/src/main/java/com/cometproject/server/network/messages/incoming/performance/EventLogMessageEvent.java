package com.cometproject.server.network.messages.incoming.performance;

import com.cometproject.api.config.CometSettings;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.types.components.games.survival.types.QueueData;
import com.cometproject.server.game.rooms.types.components.games.survival.types.SurvivalQueue;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.MassEventMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

import java.util.ArrayList;

public class EventLogMessageEvent implements Event {

    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        //
        /* private var _category:String;
        private var _type:String;
        private var _action:String;
        private var _extraString:String;
        private var _extraInt:int;*/

        final String category = msg.readString();
        final String type = msg.readString();
        final String action = msg.readString();
        final String extraString = msg.readString();
        final int extraInt = msg.readInt();

        if (client.getPlayer() == null || client.getPlayer().isDisposed) {
            return;
        }

        if(category.contains("Toolbar") && action.contains("toolbar.clicked") && type.contains("STORIES")) {
            if(!CometSettings.survivalEnabled) {
                client.send(new NotificationMessageComposer("royale", "El modo Battle Royale está en fase alfa, solo será activable temporalmente por Custom hasta que se finalice el sistema visual de colas.", ""));
                return;
            }

            final long currentTimeMs = System.currentTimeMillis();
            final long timeSinceLastUpdate = currentTimeMs - client.getPlayer().getLastPhotoTaken();

            if (timeSinceLastUpdate < 5000) {
                client.send(new NotificationMessageComposer("time_error", Locale.getOrDefault("action.time.error.message", "Debes esperar 5 segundos entre cada acción.")));
                return;
            }

            int roomId;

            if(client.getPlayer().getSurvivalRoomId() == 0){
                roomId = SurvivalQueue.getInstance().getAvailableScenario();
                client.getPlayer().setSurvivalRoomId(roomId);
            } else {
                roomId = client.getPlayer().getSurvivalRoomId();
            }

            if(SurvivalQueue.getInstance().getQueue(roomId) == null){
                SurvivalQueue.getInstance().addQueue(roomId, 0, new ArrayList<>());
            }

            if(SurvivalQueue.getInstance().playerHasQueue(roomId, client.getPlayer().getId())){
                SurvivalQueue.getInstance().removePlayerFromQueue(roomId, client.getPlayer().getId(), client.getPlayer().getQueueData());
                client.getPlayer().setSurvivalRoomId(0);
                client.getPlayer().sendBubble("newuser", "Has salido de la cola para el Battle Royale.");
                return;
            }

            QueueData data = new QueueData(client.getPlayer().getId(), client.getPlayer().getData().getUsername(), client.getPlayer().getData().getFigure());

            client.getPlayer().setQueueData(data);
            SurvivalQueue.getInstance().addPlayerToQueue(roomId, client.getPlayer().getId(), data);

            client.getPlayer().setLastPhotoTaken(currentTimeMs);
            //WebSocketSessionManager.getInstance().sendMessage(client.getWsChannel(), new BattleRoyaleQueueWebPacket("br_queue", SurvivalQueue.getInstance().getFigures(roomId)));

            client.getPlayer().sendBubble("newuser", "Acabas de unirte a una cola de supervivencia, actualmente hay " + SurvivalQueue.getInstance().getQueue(roomId).size() + " Habbos en la cola.");

            if(SurvivalQueue.getInstance().getQueue(roomId).size() >= CometSettings.survivalMinQueue){
                SurvivalQueue.getInstance().startGame(roomId);
            }
        }

        if(action.contains("RWUAM_AMBASSADOR_KICK")){
            client.getPlayer().getEntity().setStatusType(1);
        }

        if(action.contains("RWUAM_AMBASSADOR_MUTE_60MIN")){
            client.getPlayer().getEntity().setStatusType(2);
        }

        if(action.contains("RWUAM_AMBASSADOR_MUTE_18HOUR")){
            client.getPlayer().getEntity().setStatusType(3);
        }

        if(extraString.contains("event_alert")){
            client.send(new MassEventMessageComposer("navigator/goto/" + CometSettings.currentEventRoom)); }

        //client.getPlayer().getEventLogCategories().add(category);
    }
}

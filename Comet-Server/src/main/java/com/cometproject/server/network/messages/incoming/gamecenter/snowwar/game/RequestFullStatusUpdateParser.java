/*    */ package com.cometproject.server.network.messages.incoming.gamecenter.snowwar.game;
/*    */ 
/*    */ import com.cometproject.games.snowwar.SnowWarRoom;
/*    */ import com.cometproject.server.network.messages.incoming.Event;
/*    */ import com.cometproject.server.network.sessions.Session;
/*    */ import com.cometproject.server.protocol.messages.MessageEvent;
/*    */ 
/*    */ public class RequestFullStatusUpdateParser
/*    */   implements Event
/*    */ {
/*    */   public void handle(Session client, MessageEvent msg) throws Exception {
/* 12 */     SnowWarRoom room = client.snowWarPlayerData.currentSnowWar;
/* 13 */     if (room == null) {
/*    */       return;
/*    */     }
/*    */     
/* 17 */     room.fullGameStatusQueue.add(client.getChannel());
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\incoming\gamecenter\snowwar\game\RequestFullStatusUpdateParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
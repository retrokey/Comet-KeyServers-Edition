/*    */ package com.cometproject.server.network.messages.incoming.gamecenter.snowwar.account;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IMessageComposer;
/*    */ import com.cometproject.server.network.messages.incoming.Event;
/*    */ import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.UserBlockedComposer;
/*    */ import com.cometproject.server.network.sessions.Session;
/*    */ import com.cometproject.server.protocol.messages.MessageEvent;
/*    */ 
/*    */ public class GetUserBlockedParser
/*    */   implements Event
/*    */ {
/*    */   public void handle(Session client, MessageEvent msg) throws Exception {
/* 13 */     client.send((IMessageComposer)new UserBlockedComposer(0));
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\incoming\gamecenter\snowwar\account\GetUserBlockedParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
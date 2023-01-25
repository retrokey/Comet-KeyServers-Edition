/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.ComposerShit;
/*    */ import com.cometproject.games.snowwar.MessageWriter;
/*    */ import com.cometproject.games.snowwar.gameevents.PlayerLeft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SerializeGame2EventPlayerLeft
/*    */ {
/*    */   public static void parse(IComposer msg, PlayerLeft evt) {
/* 14 */     msg.writeInt(evt.player.objectId);
/*    */   }
/*    */   
/*    */   public static void parse(MessageWriter ClientMessage, PlayerLeft evt) {
/* 18 */     ComposerShit.add(evt.player.objectId, ClientMessage);
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\parse\SerializeGame2EventPlayerLeft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
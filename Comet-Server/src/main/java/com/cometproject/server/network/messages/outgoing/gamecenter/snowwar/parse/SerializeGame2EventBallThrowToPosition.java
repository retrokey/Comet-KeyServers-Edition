/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.ComposerShit;
/*    */ import com.cometproject.games.snowwar.MessageWriter;
/*    */ import com.cometproject.games.snowwar.gameevents.BallThrowToPosition;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SerializeGame2EventBallThrowToPosition
/*    */ {
/*    */   public static void parse(IComposer msg, BallThrowToPosition evt) {
/* 13 */     msg.writeInt(evt.attacker.objectId);
/* 14 */     msg.writeInt(evt.x);
/* 15 */     msg.writeInt(evt.y);
/* 16 */     msg.writeInt(evt.type);
/*    */   }
/*    */   
/*    */   public static void parse(MessageWriter ClientMessage, BallThrowToPosition evt) {
/* 20 */     ComposerShit.add(evt.attacker.objectId, ClientMessage);
/* 21 */     ComposerShit.add(evt.x, ClientMessage);
/* 22 */     ComposerShit.add(evt.y, ClientMessage);
/* 23 */     ComposerShit.add(evt.type, ClientMessage);
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\parse\SerializeGame2EventBallThrowToPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.ComposerShit;
/*    */ import com.cometproject.games.snowwar.MessageWriter;
/*    */ import com.cometproject.games.snowwar.gameevents.CreateSnowBall;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SerializeGame2EventCreateSnowBall
/*    */ {
/*    */   public static void parse(IComposer msg, CreateSnowBall evt) {
/* 13 */     msg.writeInt(evt.ball.objectId);
/* 14 */     msg.writeInt(evt.player.objectId);
/* 15 */     msg.writeInt(evt.x);
/* 16 */     msg.writeInt(evt.y);
/* 17 */     msg.writeInt(evt.type);
/*    */   }
/*    */   
/*    */   public static void parse(MessageWriter ClientMessage, CreateSnowBall evt) {
/* 21 */     ComposerShit.add(evt.ball.objectId, ClientMessage);
/* 22 */     ComposerShit.add(evt.player.objectId, ClientMessage);
/* 23 */     ComposerShit.add(evt.x, ClientMessage);
/* 24 */     ComposerShit.add(evt.y, ClientMessage);
/* 25 */     ComposerShit.add(evt.type, ClientMessage);
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\parse\SerializeGame2EventCreateSnowBall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
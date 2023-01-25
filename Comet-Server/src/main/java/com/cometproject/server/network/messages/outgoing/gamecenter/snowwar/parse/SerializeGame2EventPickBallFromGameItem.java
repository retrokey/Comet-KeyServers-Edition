/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.ComposerShit;
/*    */ import com.cometproject.games.snowwar.MessageWriter;
/*    */ import com.cometproject.games.snowwar.gameevents.PickBallFromGameItem;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SerializeGame2EventPickBallFromGameItem
/*    */ {
/*    */   public static void parse(IComposer msg, PickBallFromGameItem evt) {
/* 13 */     msg.writeInt(evt.player.objectId);
/* 14 */     msg.writeInt(evt.gameItem.objectId);
/*    */   }
/*    */   
/*    */   public static void parse(MessageWriter ClientMessage, PickBallFromGameItem evt) {
/* 18 */     ComposerShit.add(evt.player.objectId, ClientMessage);
/* 19 */     ComposerShit.add(evt.gameItem.objectId, ClientMessage);
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\parse\SerializeGame2EventPickBallFromGameItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.SnowWarRoom;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SerializeGame2GameResult
/*    */ {
/*    */   public static void parse(IComposer msg, SnowWarRoom arena) {
/* 11 */     msg.writeBoolean(true);
/* 12 */     msg.writeInt(arena.Result);
/* 13 */     msg.writeInt(arena.Winner);
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\parse\SerializeGame2GameResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
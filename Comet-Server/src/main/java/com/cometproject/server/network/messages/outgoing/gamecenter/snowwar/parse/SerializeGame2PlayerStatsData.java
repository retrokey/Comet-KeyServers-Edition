/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SerializeGame2PlayerStatsData
/*    */ {
/*    */   public static void parse(IComposer msg, HumanGameObject Player) {
/* 11 */     msg.writeInt(Player.score);
/* 12 */     msg.writeInt(Player.kills);
/* 13 */     msg.writeInt(0);
/* 14 */     msg.writeInt(Player.hits);
/* 15 */     msg.writeInt(0);
/* 16 */     msg.writeInt(0);
/* 17 */     msg.writeInt(0);
/* 18 */     msg.writeInt(0);
/* 19 */     msg.writeInt(0);
/* 20 */     msg.writeInt(0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\parse\SerializeGame2PlayerStatsData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
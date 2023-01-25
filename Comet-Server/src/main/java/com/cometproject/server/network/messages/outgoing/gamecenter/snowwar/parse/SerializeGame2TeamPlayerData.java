/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SerializeGame2TeamPlayerData
/*    */ {
/*    */   public static void parse(IComposer msg, HumanGameObject Player) {
/* 11 */     msg.writeString(Player.userName);
/* 12 */     msg.writeInt(Player.userId);
/* 13 */     msg.writeString(Player.look);
/* 14 */     msg.writeString(Player.sex);
/* 15 */     msg.writeInt(Player.score);
/* 16 */     SerializeGame2PlayerStatsData.parse(msg, Player);
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\parse\SerializeGame2TeamPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
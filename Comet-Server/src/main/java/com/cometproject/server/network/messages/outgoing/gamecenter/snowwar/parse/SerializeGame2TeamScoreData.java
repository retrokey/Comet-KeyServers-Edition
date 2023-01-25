/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SerializeGame2TeamScoreData
/*    */ {
/*    */   public static void parse(IComposer msg, int TeamId, int TeamScore, Collection<HumanGameObject> Players) {
/* 13 */     msg.writeInt(TeamId);
/* 14 */     msg.writeInt(TeamScore);
/* 15 */     msg.writeInt(Players.size());
/* 16 */     for (HumanGameObject Player : Players)
/* 17 */       SerializeGame2TeamPlayerData.parse(msg, Player); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\parse\SerializeGame2TeamScoreData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
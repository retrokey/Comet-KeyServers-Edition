/*    */ package com.cometproject.games;
/*    */ 
/*    */ import com.cometproject.games.snowwar.data.SnowWarPlayerData;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ public class GamesLeaderboard
/*    */ {
/* 10 */   public static final Map<Integer, GamesLeaderboard> leaderboards = new ConcurrentHashMap<>();
/*    */   
/*    */   public final int gameId;
/*    */   
/*    */   public List<SnowWarPlayerData> rankedList;
/*    */   
/*    */   public GamesLeaderboard(int id) {
/* 17 */     this.gameId = id;
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\games\GamesLeaderboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
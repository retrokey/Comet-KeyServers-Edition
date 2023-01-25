/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.leaderboards;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.server.game.players.data.GamePlayer;
import com.cometproject.server.protocol.messages.MessageComposer;
/*    */ import java.util.List;
/*    */ 
/*    */ public class WeeklyLeaderboardComposer
/*    */   extends MessageComposer {
/*    */   List<GamePlayer> data;
/*    */   private int gameId;
/*    */   
/*    */   public WeeklyLeaderboardComposer(int gameId) {
/* 15 */     this.gameId = gameId;
/*    */   }
/*    */ 
/*    */   @Override
/*    */   public void compose(IComposer msg) {
/* 21 */     int i = 1;
/*    */     
/* 23 */     msg.writeInt(2012);
/* 24 */     msg.writeInt(0);
/* 25 */     msg.writeInt(0);
/* 26 */     msg.writeInt(0);
/* 27 */     msg.writeInt(23);
/* 28 */     msg.writeInt(this.data.size());
/* 29 */     for (GamePlayer player : this.data) {
/* 30 */       msg.writeInt(player.getId());
/* 31 */       msg.writeInt(player.getPoints());
/* 32 */       msg.writeInt(i++);
/* 33 */       msg.writeString(player.getUsername());
/* 34 */       msg.writeString(player.getFigure());
/* 35 */       msg.writeString(player.getGender().toUpperCase());
/*    */     } 
/* 37 */     msg.writeInt(0);
/* 38 */     msg.writeInt(this.gameId);
/*    */   }
/*    */ 
/*    */   @Override
/*    */   public short getId() {
/* 43 */     return 855;
/*    */   }
/*    */ }
/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.leaderboards;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.server.game.players.data.GamePlayer;
import com.cometproject.server.protocol.messages.MessageComposer;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LastWeekLeaderboardComposer
/*    */   extends MessageComposer
/*    */ {
/*    */   List<GamePlayer> data;
/*    */   private int gameId;
/*    */   
/*    */   public LastWeekLeaderboardComposer(int gameId) {
/* 18 */     this.gameId = gameId;
/*    */   }
/*    */ 
/*    */   @Override
/*    */   public void compose(IComposer msg) {
/* 24 */     int i = 1;
/*    */     
/* 26 */     msg.writeInt(2018);
/* 27 */     msg.writeInt(0);
/* 28 */     msg.writeInt(0);
/* 29 */     msg.writeInt(-1);
/* 30 */     msg.writeInt(14);
/* 31 */     msg.writeInt(this.data.size());
/* 32 */     for (GamePlayer player : this.data) {
/* 33 */       msg.writeInt(player.getId());
/* 34 */       msg.writeInt(player.getPoints());
/* 35 */       msg.writeInt(i++);
/* 36 */       msg.writeString(player.getUsername());
/* 37 */       msg.writeString(player.getFigure());
/* 38 */       msg.writeString(player.getGender().toUpperCase());
/*    */     } 
/*    */     
/* 41 */     msg.writeInt(0);
/*    */     
/* 43 */     msg.writeInt(this.gameId);
/*    */   }
/*    */ 
/*    */   @Override
/*    */   public short getId() {
/* 48 */     return 188;
/*    */   }
/*    */ }
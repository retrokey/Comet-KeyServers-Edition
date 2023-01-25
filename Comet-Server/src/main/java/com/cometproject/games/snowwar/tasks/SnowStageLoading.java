/*    */ package com.cometproject.games.snowwar.tasks;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IMessageComposer;
/*    */ import com.cometproject.games.snowwar.SnowWarRoom;
/*    */ import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
/*    */ import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.StageStillLoadingComposer;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SnowStageLoading
/*    */ {
/*    */   public static void exec(SnowWarRoom room) {
/* 14 */     Collection<HumanGameObject> playersLoaded = room.getStageLoadedPlayers();
/*    */     
/* 16 */     if (playersLoaded != null) {
/* 17 */       room.broadcast(new StageStillLoadingComposer(playersLoaded));
/*    */       
/* 19 */       if (!playersLoaded.isEmpty()) {
/*    */         return;
/*    */       }
/*    */     } 
/*    */     
/* 24 */     for (HumanGameObject player : room.players.values()) {
/* 25 */       if (!player.stageLoaded) {
/*    */         return;
/*    */       }
/*    */     } 
/*    */     
/* 30 */     room.STATUS = 3;
/*    */   }
/*    */ }
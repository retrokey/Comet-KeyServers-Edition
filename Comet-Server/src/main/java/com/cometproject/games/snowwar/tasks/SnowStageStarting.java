/*    */ package com.cometproject.games.snowwar.tasks;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IMessageComposer;
/*    */ import com.cometproject.games.snowwar.SnowWarRoom;
/*    */ import com.cometproject.games.snowwar.gameobjects.GameItemObject;
/*    */ import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
/*    */ import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.StageStartingComposer;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SnowStageStarting
/*    */ {
/*    */   public static void exec(SnowWarRoom room) {
/* 17 */     room.gameObjects.clear();
/*    */     
/* 19 */     room.ArenaType.gameObjects(room.gameObjects, room);
/*    */     
/* 21 */     for (GameItemObject obj : room.gameObjects.values()) {
/*    */       
/* 23 */       obj._active = true;
/* 24 */       obj.objectId = room.objectIdCounter++;
/*    */     } 
/*    */     
/* 27 */     for (HumanGameObject player : room.players.values()) {
/* 28 */       room.addGameObject((GameItemObject)player);
/*    */     }
/*    */     
/* 31 */     room.checksum = 0;
/* 32 */     for (GameItemObject gameItemObject : room.gameObjects.values()) {
/* 33 */       gameItemObject.GenerateCHECKSUM(room, 1);
/*    */     }
/*    */     
/* 36 */     room.broadcast(new StageStartingComposer(room));
/*    */     
/* 38 */     room.fullGameStatusQueue = new ArrayList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\games\snowwar\tasks\SnowStageStarting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
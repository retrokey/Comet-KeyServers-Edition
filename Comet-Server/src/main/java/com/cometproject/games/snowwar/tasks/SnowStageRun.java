/*    */ package com.cometproject.games.snowwar.tasks;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IMessageComposer;
/*    */ import com.cometproject.games.snowwar.SnowWarRoom;
/*    */ import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.StageRunningComposer;
/*    */ 
/*    */ 
/*    */ public class SnowStageRun
/*    */ {
/*    */   public static void exec(SnowWarRoom room) {
/* 11 */     room.broadcast(new StageRunningComposer(120));
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\games\snowwar\tasks\SnowStageRun.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.cometproject.games.snowwar.gameevents;
/*    */ 
/*    */ import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
/*    */ import com.cometproject.games.snowwar.gameobjects.PickBallsGameItemObject;
/*    */ 
/*    */ public class PickBallFromGameItem
/*    */   extends Event
/*    */ {
/*    */   public HumanGameObject player;
/*    */   public PickBallsGameItemObject gameItem;
/*    */   
/*    */   public PickBallFromGameItem(HumanGameObject player, PickBallsGameItemObject gameItem) {
/* 13 */     this.EventType = 12;
/* 14 */     this.player = player;
/* 15 */     this.gameItem = gameItem;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void apply() {
/* 21 */     int local1 = this.player.availableSnowBallSlots();
/* 22 */     if (local1 > 0) {
/* 23 */       int local2 = this.gameItem.pickUp(1);
/* 24 */       if (local2 > 0) {
/* 25 */         this.player.addSnowBalls(local2);
/*    */       }
/*    */     } 
/*    */     
/* 29 */     if (this.gameItem.concurrentUses > 0)
/* 30 */       this.gameItem.concurrentUses--; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\games\snowwar\gameevents\PickBallFromGameItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
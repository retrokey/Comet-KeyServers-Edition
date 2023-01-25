/*    */ package com.cometproject.games.snowwar.gameevents;
/*    */ 
/*    */ import com.cometproject.games.snowwar.gameobjects.MachineGameObject;
/*    */ 
/*    */ public class AddBallToMachine
/*    */   extends Event
/*    */ {
/*    */   public MachineGameObject gameItem;
/*    */   
/*    */   public AddBallToMachine(MachineGameObject gameItem) {
/* 11 */     this.EventType = 11;
/* 12 */     this.gameItem = gameItem;
/*    */   }
/*    */ 
/*    */   
/*    */   public void apply() {
/* 17 */     this.gameItem.addSnowBall();
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\games\snowwar\gameevents\AddBallToMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
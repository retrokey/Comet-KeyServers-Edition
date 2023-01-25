/*    */ package com.cometproject.games.snowwar.gameevents;
/*    */ 
/*    */ import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
/*    */ 
/*    */ public class MakeSnowBall
/*    */   extends Event
/*    */ {
/*    */   public HumanGameObject player;
/*    */   
/*    */   public MakeSnowBall(HumanGameObject player) {
/* 11 */     this.EventType = 7;
/* 12 */     this.player = player;
/*    */   }
/*    */ 
/*    */   
/*    */   public void apply() {
/* 17 */     this.player.makeSnowBall();
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\games\snowwar\gameevents\MakeSnowBall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
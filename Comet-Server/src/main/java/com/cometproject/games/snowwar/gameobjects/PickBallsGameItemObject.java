/*    */ package com.cometproject.games.snowwar.gameobjects;
/*    */ 
/*    */ import com.cometproject.games.snowwar.Direction360;
/*    */ import com.cometproject.games.snowwar.PlayerTile;
/*    */ import com.cometproject.games.snowwar.Tile;
/*    */ 
/*    */ 
/*    */ public abstract class PickBallsGameItemObject
/*    */   extends GameItemObject
/*    */ {
/*    */   protected int parentFuseId;
/*    */   protected int snowBalls;
/*    */   protected Tile location;
/*    */   public int concurrentUses;
/*    */   
/*    */   public PickBallsGameItemObject(int _arg1, Tile _arg2, int _arg3, int _arg4) {
/* 17 */     super(_arg1);
/* 18 */     this.location = _arg2;
/* 19 */     this.snowBalls = _arg3;
/* 20 */     this.parentFuseId = _arg4;
/*    */   }
/*    */ 
/*    */   
/*    */   public Direction360 direction360() {
/* 25 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerTile location3D() {
/* 30 */     return this.location.location();
/*    */   }
/*    */   
/*    */   public int _4rk() {
/* 34 */     return this.parentFuseId;
/*    */   }
/*    */   
/*    */   public boolean canPickUpFromHere() {
/* 38 */     return (this.snowBalls > this.concurrentUses);
/*    */   }
/*    */   
/*    */   public int pickUp(int ammount) {
/* 42 */     if (this.snowBalls < ammount) {
/* 43 */       ammount = this.snowBalls;
/*    */     }
/* 45 */     onSnowballPickup(ammount);
/* 46 */     return ammount;
/*    */   }
/*    */   
/*    */   public abstract void onSnowballPickup(int paramInt);
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\games\snowwar\gameobjects\PickBallsGameItemObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
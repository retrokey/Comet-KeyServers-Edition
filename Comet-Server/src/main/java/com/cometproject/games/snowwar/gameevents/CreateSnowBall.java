/*    */ package com.cometproject.games.snowwar.gameevents;
/*    */ 
/*    */ import com.cometproject.games.snowwar.gameobjects.GameItemObject;
/*    */ import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
/*    */ import com.cometproject.games.snowwar.gameobjects.SnowBallGameObject;
/*    */ 
/*    */ public class CreateSnowBall
/*    */   extends Event {
/*    */   public SnowBallGameObject ball;
/*    */   public HumanGameObject player;
/*    */   public int x;
/*    */   public int y;
/*    */   public int type;
/*    */   
/*    */   public CreateSnowBall(SnowBallGameObject ball, HumanGameObject player, int x, int y, int type) {
/* 16 */     this.EventType = 8;
/* 17 */     this.ball = ball;
/* 18 */     this.player = player;
/* 19 */     this.x = x;
/* 20 */     this.y = y;
/* 21 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void apply() {
/* 26 */     this.ball.initialize(this.player.location3D().x(), this.player.location3D().y(), this.type, this.x, this.y, this.player);
/* 27 */     this.ball.GenerateCHECKSUM(this.player.currentSnowWar, 1);
/* 28 */     this.player.currentSnowWar.addGameObject(this.ball);
/*    */   }
/*    */ }
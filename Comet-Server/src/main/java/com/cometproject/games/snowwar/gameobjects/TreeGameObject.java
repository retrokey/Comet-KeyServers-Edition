/*     */ package com.cometproject.games.snowwar.gameobjects;
/*     */ 
/*     */ import com.cometproject.games.snowwar.Direction360;
/*     */ import com.cometproject.games.snowwar.Direction8;
/*     */ import com.cometproject.games.snowwar.PlayerTile;
/*     */ import com.cometproject.games.snowwar.SnowWarGameStage;
/*     */ import com.cometproject.games.snowwar.SnowWarRoom;
/*     */ import com.cometproject.games.snowwar.Tile;
/*     */ 
/*     */ 
/*     */ public class TreeGameObject
/*     */   extends GameItemObject
/*     */ {
/*  14 */   private static int[] _jU = new int[] { 0 };
/*  15 */   private static int[] _2Kl = new int[] { Tile.TILE_SIZE - SnowBallGameObject.boundingData[0] - 1 };
/*     */   
/*     */   private final int parentFuseId;
/*     */   
/*     */   private final Tile _0QF;
/*     */   private final Direction8 _direction8;
/*     */   private final Direction360 _direction360;
/*     */   private final int _height;
/*     */   private final int _ws;
/*     */   private int currentDamage;
/*     */   public SnowWarRoom currentSnowWar;
/*     */   
/*     */   public TreeGameObject(int x, int y, int rot, int height, int a, int b, int c, SnowWarGameStage _arg2, SnowWarRoom room) {
/*  28 */     super(9);
/*     */     
/*  30 */     this.currentSnowWar = room;
/*     */     
/*  32 */     this._0QF = _arg2.getTile(x, y);
/*  33 */     this._direction8 = Direction8.getDirection(rot);
/*  34 */     this._direction360 = new Direction360(Direction360.direction8ToDirection360Value(this._direction8));
/*  35 */     this.parentFuseId = a;
/*  36 */     this._height = height * Tile.TILE_SIZE;
/*  37 */     this.currentDamage = c;
/*  38 */     this._ws = b;
/*     */     
/*  40 */     _arg2._2Av(this);
/*     */     
/*  42 */     this._0QF._4AO(-this._height);
/*  43 */     this._0QF.setBlocked(true);
/*     */   }
/*     */   
/*     */   public void setDamage(int val) {
/*  47 */     this.currentSnowWar.checksum += val * 9 - getVariable(8) * 9;
/*  48 */     this.currentDamage = val;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVariable(int val) {
/*  53 */     if (val == 0) {
/*  54 */       return 2;
/*     */     }
/*  56 */     if (val == 1) {
/*  57 */       return this.objectId;
/*     */     }
/*  59 */     if (val == 2) {
/*  60 */       return this._0QF.location().x();
/*     */     }
/*  62 */     if (val == 3) {
/*  63 */       return this._0QF.location().y();
/*     */     }
/*  65 */     if (val == 4) {
/*  66 */       return this._direction8.getRot();
/*     */     }
/*  68 */     if (val == 5) {
/*  69 */       return this._height;
/*     */     }
/*  71 */     if (val == 6) {
/*  72 */       return this.parentFuseId;
/*     */     }
/*  74 */     if (val == 7) {
/*  75 */       return this._ws;
/*     */     }
/*     */     
/*  78 */     return this.currentDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] boundingData() {
/*  84 */     if (this.currentDamage < this._ws) {
/*  85 */       return _2Kl;
/*     */     }
/*  87 */     return _jU;
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerTile location3D() {
/*  92 */     return this._0QF.location();
/*     */   }
/*     */ 
/*     */   
/*     */   public Direction360 direction360() {
/*  97 */     return this._direction360;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onSnowBallHit(SnowBallGameObject _arg2) {
/* 102 */     if (this.currentDamage < this._ws) {
/* 103 */       setDamage(this.currentDamage + 1);
/*     */     } else {
/* 105 */       this._0QF.removeGameObject();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int _4ZU() {
/* 110 */     return this._ws;
/*     */   }
/*     */   
/*     */   public int _2Ti() {
/* 114 */     return this.currentDamage;
/*     */   }
/*     */   
/*     */   public int _4rk() {
/* 118 */     return this.parentFuseId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int collisionHeight() {
/* 123 */     return this._height;
/*     */   }
/*     */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\games\snowwar\gameobjects\TreeGameObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.cometproject.games.snowwar;
/*     */ 
/*     */ 
/*     */ public class PlayerTile
/*     */ {
/*     */   private int x;
/*     */   private int y;
/*     */   private int z;
/*     */   
/*     */   public PlayerTile(int _arg1, int _arg2, int _arg3) {
/*  11 */     this.x = _arg1;
/*  12 */     this.y = _arg2;
/*  13 */     this.z = _arg3;
/*     */   }
/*     */   
/*     */   public int x() {
/*  17 */     return this.x;
/*     */   }
/*     */   
/*     */   public int y() {
/*  21 */     return this.y;
/*     */   }
/*     */   
/*     */   public int z() {
/*  25 */     return this.z;
/*     */   }
/*     */   
/*     */   public void setXYZ(int _arg1, int _arg2, int _arg3) {
/*  29 */     this.x = _arg1;
/*  30 */     this.y = _arg2;
/*  31 */     this.z = _arg3;
/*     */   }
/*     */   
/*     */   public void setXY(int x, int y) {
/*  35 */     this.x = x;
/*  36 */     this.y = y;
/*     */   }
/*     */   
/*     */   public void setXYZ(PlayerTile _arg1) {
/*  40 */     this.x = _arg1.x;
/*  41 */     this.y = _arg1.y;
/*  42 */     this.z = _arg1.z;
/*     */   }
/*     */   
/*     */   public int distanceTo(PlayerTile _arg1) {
/*  46 */     int local1 = _arg1.x - this.x;
/*  47 */     int local2 = _arg1.y - this.y;
/*  48 */     int local3 = _arg1.z - this.z;
/*  49 */     int local4 = Math.abs(local1) + Math.abs(local2) + Math.abs(local3);
/*  50 */     return local4;
/*     */   }
/*     */   
/*     */   public Direction8 directionTo(PlayerTile _arg1) {
/*  54 */     if (_arg1.x == this.x && _arg1.y == this.y) {
/*  55 */       return null;
/*     */     }
/*  57 */     int local1 = _arg1.x - this.x;
/*  58 */     int local2 = _arg1.y - this.y;
/*  59 */     int local3 = Direction360.getRot(local1, local2);
/*  60 */     return Direction360.direction360ValueToDirection8(local3);
/*     */   }
/*     */   
/*     */   public boolean isSamePosition(Object _arg1) {
/*  64 */     if (this == _arg1) {
/*  65 */       return true;
/*     */     }
/*  67 */     if (!(_arg1 instanceof PlayerTile)) {
/*  68 */       return false;
/*     */     }
/*  70 */     PlayerTile local1 = (PlayerTile)_arg1;
/*  71 */     if (this.x != local1.x) {
/*  72 */       return false;
/*     */     }
/*  74 */     if (this.y != local1.y) {
/*  75 */       return false;
/*     */     }
/*  77 */     if (this.z != local1.z) {
/*  78 */       return false;
/*     */     }
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  85 */     return "_x:" + this.x + "yy:" + this.y + "_zz:" + this.z;
/*     */   }
/*     */   
/*     */   public boolean _0Dk(PlayerTile _arg1, int _arg2) {
/*  89 */     return _4D8(this.x, this.y, _arg1.x, _arg1.y, _arg2);
/*     */   }
/*     */   
/*     */   public static boolean _4D8(int _arg1, int _arg2, int _arg3, int _arg4, int _arg5) {
/*  93 */     int local5 = _arg3 - _arg1;
/*  94 */     if (local5 < 0) {
/*  95 */       local5 = -local5;
/*     */     }
/*  97 */     int local6 = _arg4 - _arg2;
/*  98 */     if (local6 < 0) {
/*  99 */       local6 = -local6;
/*     */     }
/* 101 */     if (local6 > _arg5 || local5 > _arg5) {
/* 102 */       return false;
/*     */     }
/* 104 */     if (local5 * local5 + local6 * local6 < _arg5 * _arg5) {
/* 105 */       return true;
/*     */     }
/* 107 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\games\snowwar\PlayerTile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
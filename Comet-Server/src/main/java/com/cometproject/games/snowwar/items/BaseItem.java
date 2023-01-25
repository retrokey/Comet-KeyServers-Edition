package com.cometproject.games.snowwar.items;

public class BaseItem {
/*  8 */   public static final BaseItem snst_tree1_d = new BaseItem("s", 4061, "snst_tree1_d", 1, 1);
/*  9 */   public static final BaseItem snst_block1 = new BaseItem("s", 4066, "snst_block1", 1, 1);
/* 10 */   public static final BaseItem snst_ballpile = new BaseItem("s", 4059, "snst_ballpile", 1, 1);
/* 11 */   public static final BaseItem xm09_man_a = new BaseItem("s", 3038, "xm09_man_a", 1, 1);
/* 12 */   public static final BaseItem xm09_man_c = new BaseItem("s", 3032, "xm09_man_c", 1, 1);
/* 13 */   public static final BaseItem xm09_man_b = new BaseItem("s", 3037, "xm09_man_b", 1, 1);
/* 14 */   public static final BaseItem snst_fence = new BaseItem("s", 4062, "snst_fence", 1, 2);
/* 15 */   public static final BaseItem ads_background = new BaseItem("s", 3704, "ads_background", 1, 1);
/* 16 */   public static final BaseItem snst_tree1 = new BaseItem("s", 4063, "snst_tree1", 1, 1);
/* 17 */   public static final BaseItem s_snowball_machine = new BaseItem("s", 4068, "s_snowball_machine", 1, 1);
/* 18 */   public static final BaseItem snst_iceblock = new BaseItem("s", 4064, "snst_iceblock", 1, 1);
/* 19 */   public static final BaseItem ads_igorraygun = new BaseItem("s", 2648, "ads_igorraygun", 1, 2);
/*    */   
/*    */   public int SpriteId;
/*    */   
/*    */   public String Name;
/*    */   public String Type;
/*    */   public int xDim;
/*    */   public int yDim;
/*    */   public float Height;
/*    */   public boolean allowWalk;
/* 29 */   public int itemExtraType = 0;
/*    */   
/*    */   public BaseItem(String type, int id, String name, int xdim, int ydim) {
/* 32 */     this.Type = type;
/* 33 */     this.SpriteId = id;
/* 34 */     this.Name = name;
/* 35 */     this.xDim = xdim;
/* 36 */     this.yDim = ydim;
/* 37 */     this.Height = 1.0F;
/*    */   }
/*    */ }
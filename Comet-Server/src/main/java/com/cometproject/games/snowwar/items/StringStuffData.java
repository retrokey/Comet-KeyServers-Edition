/*    */ package com.cometproject.games.snowwar.items;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ 
/*    */ 
/*    */ public class StringStuffData
/*    */   extends ExtraDataBase
/*    */ {
/*    */   public static final int TYPE_ID = 0;
/*    */   public String extraData;
/*    */   
/*    */   public int getType() {
/* 13 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public StringStuffData(StuffDataReader data) {
/* 19 */     if (data == null) {
/* 20 */       this.extraData = "";
/*    */     } else {
/* 22 */       this.extraData = data.readString();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] data() {
/* 28 */     if (this.extraData.isEmpty())
/*    */     {
/* 30 */       return null;
/*    */     }
/*    */     
/* 33 */     StuffDataWriter data = new StuffDataWriter(0);
/* 34 */     data.writeString(this.extraData);
/* 35 */     return data.getData();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExtraData(Object data) {
                if (data instanceof Integer) {
                    extraData = Integer.toString((Integer) data);
                } else {
                    extraData = (String) data;
                }

    /*    */   }
/*    */ 
/*    */   
/*    */   public String getWallLegacyString() {
/* 49 */     return this.extraData;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serializeComposer(IComposer writer) {
/* 54 */     writer.writeString(this.extraData);
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\games\snowwar\items\StringStuffData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
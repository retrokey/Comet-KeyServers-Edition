/*    */ package com.cometproject.games.snowwar.items;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StuffDataReader
/*    */ {
/*    */   public int type;
/*    */   public byte[] bytes;
/*    */   public int reader;
/*    */   
/*    */   public StuffDataReader(byte[] arr) {
/* 13 */     if (arr == null) {
/*    */       
/* 15 */       this.bytes = new byte[2];
/*    */       return;
/*    */     } 
/* 18 */     this.bytes = arr;
/* 19 */     this.type = readInt8();
/*    */   }
/*    */   
/*    */   public int readInt32() {
/* 23 */     return ((this.bytes[this.reader++] & 0xFF) << 24) + ((this.bytes[this.reader++] & 0xFF) << 16) + ((this.bytes[this.reader++] & 0xFF) << 8) + (this.bytes[this.reader++] & 0xFF);
/*    */   }
/*    */   
/*    */   public int readInt16() {
/* 27 */     return ((this.bytes[this.reader++] & 0xFF) << 8) + (this.bytes[this.reader++] & 0xFF);
/*    */   }
/*    */   
/*    */   public int readInt8() {
/* 31 */     return this.bytes[this.reader++] & 0xFF;
/*    */   }
/*    */   
/*    */   public String readString() {
/* 35 */     int len = readInt16();
/* 36 */     byte[] text = new byte[len];
/* 37 */     System.arraycopy(this.bytes, this.reader, text, 0, len);
/* 38 */     this.reader += len;
/* 39 */     return new String(text);
/*    */   }
/*    */   
/*    */   public boolean canRead(int len) {
/* 43 */     return (this.bytes.length - this.reader >= len);
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\games\snowwar\items\StuffDataReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.cometproject.games.snowwar;
/*    */ 
/*    */ public class ComposerShit {
/*    */   public static void add(Object add, MessageWriter Message) {
/*  5 */     if (add == null) {
/*  6 */       throw new UnsupportedOperationException("NULL Param in Append!");
/*    */     }
/*    */     
/*  9 */     if (add instanceof Integer) {
/* 10 */       Message.writeInt32((Integer) add);
/*    */       
/*    */       return;
/*    */     } 
/* 14 */     if (add instanceof Short) {
/* 15 */       Message.writeInt32((Short) add);
/*    */       
/*    */       return;
/*    */     } 
/* 19 */     if (add instanceof String) {
/* 20 */       Message.writeString((String)add);
/*    */       
/*    */       return;
/*    */     } 
/* 24 */     if (add instanceof Boolean) {
/* 25 */       Message.writeBoolean((Boolean) add);
/*    */       
/*    */       return;
/*    */     } 
/* 29 */     if (add instanceof byte[]) {
/* 30 */       Message.writeBytes((byte[])add);
/*    */       
/*    */       return;
/*    */     } 
/* 34 */     if (add instanceof Long) {
/* 35 */       Message.writeInt32(((Long)add).intValue());
/*    */       
/*    */       return;
/*    */     } 
/* 39 */     throw new UnsupportedOperationException("Bad Param in Append " + add.getClass());
/*    */   }
/*    */   
/*    */   public static void endPacket(MessageWriter Message) {
/* 43 */     int tmp = Message.writer;
/* 44 */     int len = tmp - 4;
/* 45 */     if (len < 2 || len > 131072) {
/* 46 */       throw new UnsupportedOperationException("Bad Message! Len=" + len);
/*    */     }
/*    */     
/* 49 */     Message.writer = 0;
/* 50 */     Message.writeInt32(len);
/* 51 */     Message.writer = tmp;
/* 52 */     Message.isReady = true;
/*    */   }
/*    */   
/*    */   public static void initPacket(int headerId, MessageWriter Message) {
/* 56 */     if (headerId == 0) {
/* 57 */       throw new UnsupportedOperationException("Header = 0!!");
/*    */     }
/*    */     
/* 60 */     Message.debugId = headerId;
/* 61 */     Message.writer = 4;
/* 62 */     Message.writeInt16((short)headerId);
/*    */   }
/*    */ }
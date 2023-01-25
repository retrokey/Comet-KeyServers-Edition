/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.messages.MessageComposer;

/*    */ public class JoiningGameFailedComposer
/*    */   extends MessageComposer
/*    */ {
/*    */   private final int errorCode;
/*    */   
/*    */   public JoiningGameFailedComposer(int errorCode) {
/* 17 */     this.errorCode = errorCode;
/*    */   }
/*    */ 
/*    */   @Override
/*    */   public void compose(IComposer msg) {
/* 22 */     msg.writeInt(this.errorCode);
/*    */   }
/*    */ 
/*    */   @Override
/*    */   public short getId() {
/* 27 */     return 0;
/*    */   }
/*    */ }
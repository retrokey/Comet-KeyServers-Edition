/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

/*    */
/*    */ public class InArenaQueueComposer
/*    */   extends MessageComposer
/*    */ {
/*    */   private final int position;
/*    */   
/*    */   public InArenaQueueComposer(int position) {
/* 12 */     this.position = position;
/*    */   }
/*    */ 
/*    */       @Override
/*    */   public void compose(IComposer msg) {
/* 17 */     msg.writeInt(this.position);
/*    */   }
        @Override
/*    */   public short getId() {
/* 21 */     return Composers.SnowInArenaQueueMessageComposer;
/*    */   }
/*    */ }

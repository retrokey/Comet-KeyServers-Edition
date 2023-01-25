/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StageRunningComposer
/*    */   extends MessageComposer
/*    */ {
/*    */   private final int seconds;
/*    */   
/*    */   public StageRunningComposer(int seconds) {
/* 18 */     this.seconds = seconds;
/*    */   }
/*    */ 
/*    */       @Override
/*    */   public void compose(IComposer msg) {
/* 23 */     msg.writeInt(this.seconds);
/*    */   }
/*    */ 
/*    */       @Override
/*    */   public short getId() {
/* 28 */     return Composers.StageRunningMessageComposer;
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\StageRunningComposer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
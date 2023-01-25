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
/*    */ public class UserBlockedComposer
/*    */   extends MessageComposer
/*    */ {
/*    */   private final int snowWarBlockedGame;
/*    */   
/*    */   public UserBlockedComposer(int snowWarBlockedGame) {
/* 18 */     this.snowWarBlockedGame = snowWarBlockedGame;
/*    */   }
/*    */ 
/*    */   @Override
/*    */   public void compose(IComposer msg) {
/* 23 */     msg.writeInt(this.snowWarBlockedGame);
/*    */   }
/*    */ 
/*    */   @Override
/*    */   public short getId() {
/* 28 */     return Composers.SnowStormStartBlockTickerComposer;
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\UserBlockedComposer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
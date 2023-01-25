/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.leaderboards;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.messages.MessageComposer;

/*    */
/*    */ public class WinningPrizeComposer
/*    */   extends MessageComposer {
/*    */   private int gameId;
/*    */   
/*    */   public WinningPrizeComposer(int gameId) {
/* 11 */     this.gameId = gameId;
/*    */   }
/*    */ 
/*    */   @Override
/*    */   public void compose(IComposer msg) {
/* 16 */     msg.writeInt(this.gameId);
/* 17 */     msg.writeInt(1);

/* 19 */     msg.writeString("s");
/* 20 */     msg.writeInt(230);
/* 21 */     msg.writeString("");
/* 22 */     msg.writeInt(1);
/* 23 */     msg.writeBoolean(false);

/* 26 */     msg.writeInt(0);
/* 27 */     msg.writeBoolean(true);
/*    */   }
    @Override
/*    */   public short getId() {
/* 45 */     return 1653;
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\leaderboards\WinningPrizeComposer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

/*    */ public class PlayerRematchesComposer
/*    */   extends MessageComposer
/*    */ {
/*    */   private final int playerId;
/*    */   
/*    */   public PlayerRematchesComposer(int playerId) {
/* 17 */     this.playerId = playerId;
/*    */   }
/*    */ 
/*    */   @Override
/*    */   public void compose(IComposer msg) {
/* 22 */     msg.writeInt(this.playerId);
/*    */   }
/*    */
/*    */   @Override
/*    */   public short getId() {
/* 27 */     return Composers.SnowStormUserRematchedComposer;
/*    */   }
/*    */ }

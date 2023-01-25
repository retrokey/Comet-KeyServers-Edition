/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.SnowWarRoom;
/*    */ import com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse.SerializeGame2GameObjects;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StageStartingComposer
/*    */   extends MessageComposer
/*    */ {
/*    */   private final SnowWarRoom arena;
/*    */   
/*    */   public StageStartingComposer(SnowWarRoom arena) {
/* 20 */     this.arena = arena;
/*    */   }
/*    */ 
/*    */       @Override
/*    */   public void compose(IComposer msg) {
/* 25 */     msg.writeInt(0);
/* 26 */     msg.writeString("snowwar_arena_0");
/* 27 */     msg.writeInt(5);
/* 28 */     SerializeGame2GameObjects.parse(msg, this.arena);
/*    */   }
/*    */ 
/*    */       @Override
/*    */   public short getId() {
/* 33 */     return Composers.SnowStageStartingMessageComposer;
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\StageStartingComposer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
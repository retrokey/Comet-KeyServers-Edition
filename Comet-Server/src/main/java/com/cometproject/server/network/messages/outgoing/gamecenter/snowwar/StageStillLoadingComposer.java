/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StageStillLoadingComposer
/*    */   extends MessageComposer
/*    */ {
/*    */   private final Collection<HumanGameObject> playersLoaded;
/*    */   
/*    */   public StageStillLoadingComposer(Collection<HumanGameObject> playersLoaded) {
/* 21 */     this.playersLoaded = playersLoaded;
/*    */   }
/*    */ 
/*    */       @Override
/*    */   public void compose(IComposer msg) {
/* 26 */     msg.writeInt(0);
/* 27 */     msg.writeInt(this.playersLoaded.size());
/* 28 */     for (HumanGameObject player : this.playersLoaded) {
/* 29 */       msg.writeInt(player.userId);
/*    */     }
/*    */   }
/*    */ 
/*    */       @Override
/*    */   public short getId() {
/* 35 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\StageStillLoadingComposer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
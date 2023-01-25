package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.games.snowwar.gameobjects.HumanGameObject;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

/*    */ public class PlayerExitedGameArenaComposer
/*    */   extends MessageComposer
/*    */ {
/*    */   private final HumanGameObject player;
/*    */   
/*    */   public PlayerExitedGameArenaComposer(HumanGameObject player) {
/* 18 */     this.player = player;
/*    */   }
/*    */ 
/*    */   @Override
/*    */   public void compose(IComposer msg) {
/* 23 */     msg.writeInt(this.player.userId);
/* 24 */     msg.writeInt(this.player.objectId);
/*    */   }

/*    */   @Override
/*    */   public short getId() {
/* 29 */     return Composers.SnowStormOnPlayerExitedArenaComposer;
/*    */   }
/*    */ }

/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.GamefuseObject;
/*    */ import com.cometproject.games.snowwar.Tile;
import com.cometproject.games.snowwar.items.MapStuffData;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SerializeFuseObject
/*    */ {
/*    */   public static void parse(IComposer msg, GamefuseObject fuseItem) {
/* 13 */     msg.writeString(fuseItem.baseItem.Name);
/* 14 */     msg.writeInt(fuseItem.itemId);
/* 15 */     msg.writeInt(fuseItem.X);
/* 16 */     msg.writeInt(fuseItem.Y);
/* 17 */     msg.writeInt(fuseItem.baseItem.xDim);
/* 18 */     msg.writeInt(fuseItem.baseItem.yDim);
/* 19 */     msg.writeInt((int)(fuseItem.baseItem.Height * Tile.TILE_SIZE));
/* 20 */     msg.writeInt(fuseItem.Rot);
/* 21 */     msg.writeInt(fuseItem.Z);
/* 22 */     msg.writeBoolean(fuseItem.baseItem.allowWalk);
/* 23 */     if (fuseItem.extraData instanceof MapStuffData) {
/* 24 */       msg.writeInt(1);
/* 25 */       fuseItem.extraData.serializeComposer(msg);
/*    */     } else {
/* 27 */       msg.writeInt(0);
/* 28 */       fuseItem.extraData.serializeComposer(msg);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\parse\SerializeFuseObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
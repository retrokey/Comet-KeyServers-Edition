/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.GamefuseObject;
/*    */ import com.cometproject.games.snowwar.SnowWarRoom;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SerializeArena
/*    */ {
/*    */   public static void parse(IComposer msg, SnowWarRoom arena) {
/* 12 */     msg.writeInt(arena.ArenaType.ArenaWidth);
/* 13 */     msg.writeInt(arena.ArenaType.ArenaHeight);
/* 14 */     msg.writeString(arena.ArenaType.HeightMap);
/* 15 */     msg.writeInt(arena.ArenaType.fuseObjects.size());
/* 16 */     for (GamefuseObject fuseItem : arena.ArenaType.fuseObjects)
/* 17 */       SerializeFuseObject.parse(msg, fuseItem); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\parse\SerializeArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
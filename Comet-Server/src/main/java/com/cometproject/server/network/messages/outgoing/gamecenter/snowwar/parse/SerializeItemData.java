/*    */ package com.cometproject.server.network.messages.outgoing.gamecenter.snowwar.parse;
/*    */ 
/*    */ import com.cometproject.api.networking.messages.IComposer;
/*    */ import com.cometproject.games.snowwar.items.BaseItem;
/*    */ import com.cometproject.games.snowwar.items.Item;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SerializeItemData
/*    */ {
/*    */   public static void parse(IComposer msg, BaseItem baseItem, Item item) {
/* 12 */     msg.writeInt(item.extraData.getType());
/* 13 */     item.extraData.serializeComposer(msg);
/*    */   }
/*    */ }


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\server\network\messages\outgoing\gamecenter\snowwar\parse\SerializeItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
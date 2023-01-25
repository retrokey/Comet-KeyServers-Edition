/*    */ package com.cometproject.server.network.messages.incoming.gamecenter.snowwar.game;
/*    */ 
/*    */ import com.cometproject.games.snowwar.SnowPlayerQueue;
/*    */ import com.cometproject.server.network.messages.incoming.Event;
/*    */ import com.cometproject.server.network.sessions.Session;
/*    */ import com.cometproject.server.protocol.messages.MessageEvent;
/*    */ 
/*    */ public class QuickJoinGameParser
/*    */   implements Event
/*    */ {
/*    */   public void handle(Session client, MessageEvent msg) throws Exception {
/* 12 */     SnowPlayerQueue.addPlayerInQueue(client);
/*    */   }
/*    */ }
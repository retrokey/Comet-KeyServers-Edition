//package com.cometproject.server.network.messages.outgoing.room.alerts;
//
//import com.cometproject.api.networking.messages.IComposer;
//import com.cometproject.server.network.messages.composers.MessageComposer;
//import com.cometproject.server.protocol.headers.Composers;
//
//
//public class RoomConnectionErrorMessageComposer extends MessageComposer {
//    private final int errorCode;
//    private final String extras;
//
//    public RoomConnectionErrorMessageComposer(final int errorCode, final String extras) {
//        this.errorCode = errorCode;
//        this.extras = extras;
//    }
//
//    @Override
//    public short getId() {
//        return Composers.RoomErrorNotifMessageComposer;
//    }
//
//    @Override
//    public void compose(IComposer msg) {
//        msg.writeInt(errorCode);
//
//        if (!extras.isEmpty())
//            msg.writeString(extras);
//
//    }
//}

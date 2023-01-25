package com.cometproject.games.snowwar.items;

import com.cometproject.api.networking.messages.IComposer;

public abstract class ExtraDataBase {

    public void setExtraData(Object extraData) {}
    public String getWallLegacyString() {
/* 13 */     return "";
/*    */   }
    public abstract byte[] data();
    public abstract int getType();
    public abstract void serializeComposer(IComposer paramIComposer);
}
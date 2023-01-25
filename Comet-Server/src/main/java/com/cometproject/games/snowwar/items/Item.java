package com.cometproject.games.snowwar.items;

public abstract class Item {
    public static final int UPDATE = 1;
    public static final int MOVE = 2;
    public static final int INSERT = 3;
    public static final int DELETE = 4;
    public int refId;
    private int mysqlAction;
    public int itemId;
    public BaseItem baseItem;
    public ExtraDataBase extraData;
    public int hashCode() {
        return this.itemId;
    }
}

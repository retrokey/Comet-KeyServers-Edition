
package com.cometproject.server.network.websockets.packets.outgoing.roleplay;

public class RolePlayProductWebPacket {
    private String handle;
    private String id;
    private String productname;
    private String itemId;
    private String cost;
    private String currency;
    private String type;
    private String image;

    public RolePlayProductWebPacket(String handle, int id, String productname, int itemId, int cost, int currency, String type, String image) {
        this.handle = handle;
        this.id = id + "";
        this.productname = productname;
        this.itemId = itemId + "";
        this.cost = cost + "";
        this.currency = currency + "";
        this.type = type;
        this.image = image;
    }

}
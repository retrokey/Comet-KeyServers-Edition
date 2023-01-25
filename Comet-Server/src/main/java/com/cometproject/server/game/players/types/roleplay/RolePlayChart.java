package com.cometproject.server.game.players.types.roleplay;

public class RolePlayChart {
    private int id;
    private String productname;
    private int itemId;
    private int cost;
    private int currency;
    private String type;
    private String image;

    public RolePlayChart(int id, String productname, int itemId, int cost, int currency, String type, String image){
        this.id = id;
        this.productname = productname;
        this.itemId = itemId;
        this.cost = cost;
        this.currency = currency;
        this.type = type;
        this.image = image;
    }

    public int getId() {
        return this.id;
    }

    public String getProductname() {
        return this.productname;
    }

    public int getItemId() {
        return this.itemId;
    }

    public int getCost() {
        return this.cost;
    }

    public int getCurrency() {
        return this.currency;
    }

    public String getType() {
        return this.type;
    }

    public String getImage() {
        return this.image;
    }
}

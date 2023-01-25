package com.cometproject.server.network.websockets.interfaces;

public class ShopOffer {
    private final int id;
    private final String name;
    private final int diamonds;
    private final int pixels;
    private final int days;
    private final int itemId;
    private final int amount;

    public ShopOffer(int recipeId, String name, int diamonds, int pixels, int days, int itemId, int amount) {
        this.id = recipeId;
        this.name = name;
        this.diamonds = diamonds;
        this.pixels = pixels;
        this.days = days;
        this.itemId = itemId;
        this.amount = amount;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public int getPixels() {
        return pixels;
    }

    public int getDays() {
        return days;
    }

    public int getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }
}

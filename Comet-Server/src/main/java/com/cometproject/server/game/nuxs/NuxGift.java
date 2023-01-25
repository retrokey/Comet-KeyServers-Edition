package com.cometproject.server.game.nuxs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NuxGift {
    public enum RewardType {
        ITEM,
        DIAMONDS,
        SEASONAL,
        BADGE,
        REWARD1,
        REWARD2,
        REWARD3,
        ROCK,
        PAPER,
        SCISSORS
    }

    private int id;
    private int pageType;
    private RewardType type;
    private String name;
    private String icon;
    private String productdata;
    private List<String> data = new ArrayList<>();

    public NuxGift(int id, String type, int pageType, String icon, String name, String productdata, String data){
        this.id = id;
        switch (type.toLowerCase()){
            case "item":
                this.type = RewardType.ITEM;
            break;
            case "diamonds":
                this.type = RewardType.DIAMONDS;
                break;
            case "seasonal":
                this.type = RewardType.SEASONAL;
                break;
            case "reward1":
                this.type = RewardType.REWARD1;
                break;
            case "reward2":
                this.type = RewardType.REWARD2;
                break;
            case "reward3":
                this.type = RewardType.REWARD3;
                break;
            case "rock":
                this.type = RewardType.ROCK;
                break;
            case "paper":
                this.type = RewardType.PAPER;
                break;
            case "scissors":
                this.type = RewardType.SCISSORS;
                break;
            case "badge":
            default:
                this.type = RewardType.BADGE;
                break;
        }
        this.pageType = pageType;
        this.icon = icon;
        this.name = name;
        this.productdata = productdata;
        Collections.addAll(this.data, data.split(","));
    }

    public int getId() { return this.id; }
    public int getPageType() { return this.pageType; }
    public RewardType getType() { return this.type; }
    public String getName() { return this.name; }
    public String getIcon() { return this.icon; }
    public String getProductdata() { return this.productdata; }
    public String getRandomData() {
        int max = this.data.size() - 1;
        int min = 0;

        return this.data.get( (int)Math.floor(Math.random() * (max - min)) + min );
    }
}
package com.cometproject.api.game.furniture.types;

import java.util.ArrayList;
import java.util.List;

public class CrackableReward {
    private final int hitRequirement;
    private final CrackableRewardType rewardType;
    private final CrackableType crackableType;

    private final String rewardData;
    private final int rewardDataInt;
    private List<Integer> rewardsId = new ArrayList<>();

    public CrackableReward(int hitRequirement, CrackableRewardType rewardType, CrackableType crackableType, String rewardData, int rewardDataInt) {
        this.hitRequirement = hitRequirement;
        this.rewardType = rewardType;
        this.crackableType = crackableType;
        this.rewardData = rewardData;
        this.rewardDataInt = rewardDataInt;
        if(!rewardData.contains(",")) {
            this.rewardsId.add(Integer.valueOf(rewardData));
        } else {
            for(String rewardId : rewardData.split(",")) { this.rewardsId.add(Integer.valueOf(rewardId)); }
        }
    }

    public int getHitRequirement() {
        return hitRequirement;
    }

    public CrackableRewardType getRewardType() {
        return rewardType;
    }

    public CrackableType getCrackableType() {
        return crackableType;
    }

    public List<Integer> getRewardsId() { return this.rewardsId; }

    public int getRandomReward() { return this.getRewardsId().get((int)Math.floor(Math.random() * this.getRewardsId().size())); }

    public String getRewardData() {
        return rewardData;
    }

    public int getRewardDataInt() {
        return this.rewardDataInt;
    }
}

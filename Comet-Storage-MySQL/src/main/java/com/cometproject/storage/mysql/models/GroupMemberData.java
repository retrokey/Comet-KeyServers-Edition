package com.cometproject.storage.mysql.models;

import com.cometproject.api.game.groups.types.components.membership.GroupAccessLevel;
import com.cometproject.api.game.groups.types.components.membership.IGroupMember;

public class GroupMemberData implements IGroupMember {

    private final int playerId;
    private final int groupId;
    private final int dateJoined;
    private String role;
    private int membershipId;
    private GroupAccessLevel groupAccessLevel;

    public GroupMemberData(int membershipId, int playerId, int groupId, int dateJoined, GroupAccessLevel groupAccessLevel, String role) {
        this.membershipId = membershipId;
        this.playerId = playerId;
        this.groupId = groupId;
        this.dateJoined = dateJoined;
        this.role = role;
        this.groupAccessLevel = groupAccessLevel;
    }


    @Override
    public int getMembershipId() {
        return this.membershipId;
    }

    @Override
    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    @Override
    public int getPlayerId() {
        return this.playerId;
    }

    @Override
    public int getGroupId() {
        return this.groupId;
    }

    @Override
    public GroupAccessLevel getAccessLevel() {
        return this.groupAccessLevel;
    }

    @Override
    public void setAccessLevel(GroupAccessLevel accessLevel) {
        this.groupAccessLevel = accessLevel;
    }

    @Override
    public int getDateJoined() {
        return this.dateJoined;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getRole() {
        return this.role;
    }
}

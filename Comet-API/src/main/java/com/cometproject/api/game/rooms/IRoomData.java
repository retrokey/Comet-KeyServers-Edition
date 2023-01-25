package com.cometproject.api.game.rooms;

import com.cometproject.api.game.rooms.settings.*;

import java.util.List;
import java.util.Map;

public interface IRoomData {
    int getId();

    String getName();

    String getDescription();

    int getOwnerId();

    String getOwner();

    int getMaxUsers();

    RoomAccessType getAccess();

    String getPassword();

    int getScore();

    String[] getTags();

    Map<String, String> getDecorations();

    String getModel();

    boolean getHideWalls();

    int getWallThickness();

    int getFloorThickness();

    void setId(int id);

    void setName(String name);

    void setDescription(String description);

    void setOwnerId(int ownerId);

    void setOwner(String owner);

    int getCategoryId();

    void setCategoryId(int category);

    void setMaxUsers(int maxUsers);

    void setAccess(RoomAccessType access);

    void setPassword(String password);

    void setScore(int score);

    void setTags(String[] tags);

    void setDecorations(Map<String, String> decorations);

    void setModel(String model);

    void setHideWalls(boolean hideWalls);

    void setThicknessWall(int thicknessWall);

    void setThicknessFloor(int thicknessFloor);

    boolean getAllowWalkthrough();

    boolean isAllowWalkthrough();

    void setAllowWalkthrough(boolean allowWalkthrough);

    void setHeightmap(String heightmap);

    String getHeightmap();

    boolean isAllowPets();

    void setAllowPets(boolean allowPets);

    String getOriginalPassword();

    void setOriginalPassword(String originalPassword);

    RoomTradeState getTradeState();

    void setTradeState(RoomTradeState tradeState);

    int getBubbleMode();

    void setBubbleMode(int bubbleMode);

    int getBubbleType();

    void setBubbleType(int bubbleType);

    int getBubbleScroll();

    void setBubbleScroll(int bubbleScroll);

    int getChatDistance();

    void setChatDistance(int chatDistance);

    int getAntiFloodSettings();

    void setAntiFloodSettings(int antiFloodSettings);

    RoomMuteState getMuteState();

    void setMuteState(RoomMuteState muteState);

    RoomKickState getKickState();

    void setKickState(RoomKickState kickState);

    RoomBanState getBanState();

    void setBanState(RoomBanState banState);

    List<String> getDisabledCommands();

    int getGroupId();

    String getRequiredBadge();

    RoomType getType();

    String getThumbnail();

    void setGroupId(int groupId);

    String getDecorationString();

    void setThumbnail(String thumbnail);

    boolean isWiredHidden();

    int getRollerSpeed();

    boolean hasSorting();

    boolean advancedCollision();

    void setAdvancedCollision(boolean advancedCollision);

    void setHasEntitySort(boolean hasEntitySort);

    void setRollerSpeed(int speed);

    void setIsWiredHidden(boolean hiddenWired);

    int getUserIdleTicks();

    void setUserIdleTicks(int ticks);
}

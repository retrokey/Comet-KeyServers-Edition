package com.cometproject.server.game.rooms.objects.items.types.floor.wired.conditions.custom;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredConditionItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.components.games.GameTeam;
import org.apache.commons.lang.StringUtils;


public class WiredConditionSuperWired extends WiredConditionItem {


    public WiredConditionSuperWired(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public int getInterface() {
        return 11;
    }

    @Override
    public boolean evaluate(RoomEntity entity, Object data) {

        Room team = this.getRoom();

        String str = this.getWiredData().getText();
        String[] finalText = str.split(":");

        switch (finalText[0].toLowerCase()) {
            case "enable":
                if (entity == null) {
                    return false;
                }
                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }

                return entity.getCurrentEffect().getEffectId() == Integer.parseInt(finalText[1]);
            case "noenable":
                if (entity == null) {
                    return false;
                }
                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }

                return entity.getCurrentEffect().getEffectId() != Integer.parseInt(finalText[1]);
            case "handitem":
                if (entity == null) {
                    return false;
                }
                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }

                return entity.getHandItem() == Integer.parseInt(finalText[1]);
            case "nohanditem":
                if (entity == null) {
                    return false;
                }
                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }

                return entity.getHandItem() != Integer.parseInt(finalText[1]);
            case "transform":
                if (entity == null) {
                    return false;
                }

                if (finalText[1].equalsIgnoreCase("true") || finalText[1].equalsIgnoreCase("false")){
                    return entity.isTransformed() == Boolean.parseBoolean(finalText[1]);
                }

                return false;
            case "mission":
                if (entity == null) {
                    return false;
                }

                return entity.getMotto().equalsIgnoreCase(finalText[1]);
            case "nomission":
                if (entity == null) {
                    return false;
                }

                return !entity.getMotto().equals(finalText[1]);
            case "dance":
                if (entity == null) {
                    return false;
                }

                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }

                return entity.getDanceId() == Integer.parseInt(finalText[1]);
            case "teamred-max":
                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }

                return team.getGame().getTeams().get(GameTeam.RED).size() <= Integer.parseInt(finalText[1]);
            case "teamred-min":
                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }

                return team.getGame().getTeams().get(GameTeam.RED).size() >= Integer.parseInt(finalText[1]);
            case "teamblue-max":
                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }

                return team.getGame().getTeams().get(GameTeam.BLUE).size() <= Integer.parseInt(finalText[1]);
            case "teamblue-min":
                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }

                return team.getGame().getTeams().get(GameTeam.BLUE).size() >= Integer.parseInt(finalText[1]);
            case "teamyellow-max":
                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }

                return team.getGame().getTeams().get(GameTeam.YELLOW).size() <= Integer.parseInt(finalText[1]);
            case "teamyellow-min":

                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }
                return team.getGame().getTeams().get(GameTeam.YELLOW).size() >= Integer.parseInt(finalText[1]);
            case "teamgreen-max":
                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }

                return team.getGame().getTeams().get(GameTeam.GREEN).size() <= Integer.parseInt(finalText[1]);
            case "teamgreen-min":

                if (!StringUtils.isNumeric(finalText[1])) {
                    return false;
                }

                return team.getGame().getTeams().get(GameTeam.GREEN).size() >= Integer.parseInt(finalText[1]);
            case "points":
                if (StringUtils.isNumeric(finalText[1])) {
                    if (entity.getAttribute("points_game") != null){
                        return Integer.parseInt(String.valueOf(entity.getAttribute("points_game"))) >= Integer.parseInt(finalText[1]);
                    }
                }
                return false;
            case "nopoints":
                if (StringUtils.isNumeric(finalText[1])) {
                    if (entity.getAttribute("points_game") != null){
                        return Integer.parseInt(String.valueOf(entity.getAttribute("points_game"))) < Integer.parseInt(finalText[1]);
                    }else {
                        return true;
                    }
                }
                return false;
        }
        return false;
    }
}
package com.cometproject.server.storage.queries.achievements;

import com.cometproject.api.game.achievements.types.*;
import com.cometproject.server.game.achievements.AchievementGroup;
import com.cometproject.server.game.achievements.AchievementManager;
import com.cometproject.server.game.achievements.types.Achievement;
import com.cometproject.server.game.achievements.types.TalentTrackLevel;
import com.cometproject.server.storage.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AchievementDao {

    public static int getAchievements(Map<AchievementType, IAchievementGroup> achievementGroups) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int count = 0;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT * FROM achievements WHERE enabled = '1' ORDER by group_name ASC", sqlConnection);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count++;

                final AchievementType groupName = AchievementType.getTypeByName(resultSet.getString("group_name"));

                if (groupName == null) continue;

                if (!achievementGroups.containsKey(groupName)) {
                    achievementGroups.put(groupName, new AchievementGroup(resultSet.getInt("id"), new HashMap<>(), resultSet.getString("group_name"), AchievementCategory.valueOf(resultSet.getString("category").toUpperCase())));
                }

                if (!achievementGroups.get(groupName).getAchievements().containsKey(resultSet.getInt("level"))) {
                    achievementGroups.get(groupName).getAchievements().put(resultSet.getInt("level"), create(resultSet));
                }
            }
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(resultSet);
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }

        return count;
    }

    public static int getTalents(Map<Integer, ITalentTrackLevel> talentTrackLevels) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int count = 0;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT * FROM achievements_talents", sqlConnection);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count++;
                
                String[] achievements = resultSet.getString("achievement_ids").split(",");
                String[] items = resultSet.getString("reward_items").split(",");
                String[] badges = new String[0];
                String[] perks = new String[0];

                if(!resultSet.getString("reward_perks").isEmpty()) {
                    perks = resultSet.getString("reward_perks").split(",");
                }

                if(!resultSet.getString("reward_badges").isEmpty()) {
                    badges = resultSet.getString("reward_perks").split(",");
                }

                Set<AchievementType> achList = new HashSet<>();
                Set<Integer> itemList = new HashSet<>();

                for(String s : achievements){
                    achList.add(AchievementType.getTypeByName(s));
                }

                for(String s : items){
                    itemList.add(Integer.parseInt(s));
                }
                
                talentTrackLevels.put(resultSet.getInt("id"), new TalentTrackLevel(resultSet.getInt("level"), achList, itemList, perks, badges));
            }
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(resultSet);
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }

        return count;
    }

    public static int getGameCenterAchievements(Map<Integer, Map<AchievementType, IAchievementGroup>> achievementGroups) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int count = 0;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT * FROM achievements WHERE enabled = '1' AND game_id > 0 ORDER by group_name ASC", sqlConnection);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count++;

                final int gameId = resultSet.getInt("game_id");
                final AchievementType groupName = AchievementType.getTypeByName(resultSet.getString("group_name"));

                if (groupName == null) continue;

                if(!achievementGroups.containsKey(gameId)) {
                    Map<AchievementType, IAchievementGroup> tempAch = new HashMap<>();
                    tempAch.put(groupName, new AchievementGroup(resultSet.getInt("id"), new HashMap<>(), resultSet.getString("group_name"), AchievementCategory.valueOf(resultSet.getString("category").toUpperCase())));

                    achievementGroups.put(gameId, tempAch);
                }

                if(achievementGroups.containsKey(gameId)) {
                    if (!achievementGroups.get(gameId).containsKey(groupName)) {
                        achievementGroups.get(gameId).put(groupName, new AchievementGroup(resultSet.getInt("id"), new HashMap<>(), resultSet.getString("group_name"), AchievementCategory.valueOf(resultSet.getString("category").toUpperCase())));
                    }

                    if (!achievementGroups.get(gameId).get(groupName).getAchievements().containsKey(resultSet.getInt("level"))) {
                        achievementGroups.get(gameId).get(groupName).getAchievements().put(resultSet.getInt("level"), create(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(resultSet);
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }

        return count;
    }

    private static Achievement create(ResultSet resultSet) throws SQLException {
        return new Achievement(resultSet.getInt("level"), resultSet.getInt("reward_activity_points"), resultSet.getInt("reward_achievement_points"), resultSet.getInt("reward_type"), resultSet.getInt("progress_requirement"));
    }

}

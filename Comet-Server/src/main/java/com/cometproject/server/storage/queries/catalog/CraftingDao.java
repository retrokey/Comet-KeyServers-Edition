package com.cometproject.server.storage.queries.catalog;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.server.game.items.crafting.CraftingMachine;
import com.cometproject.server.game.items.crafting.CraftingRecipe;
import com.cometproject.server.storage.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CraftingDao {
    public static void loadRecipes(CraftingMachine machine) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT * FROM furniture_crafting_recipes WHERE machineBaseId = ?", sqlConnection);
            preparedStatement.setInt(1, machine.getBaseId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AchievementType achievement = AchievementType.getTypeByName(resultSet.getString("achievement"));

                if(resultSet.getString("mode").equals("public"))
                    machine.getPublicRecipes().add(new CraftingRecipe(resultSet.getInt("id"), resultSet.getString("items"), resultSet.getString("result"), resultSet.getInt("result_limit"), resultSet.getInt("result_crafted"), resultSet.getString("badge"), achievement));
                if(resultSet.getString("mode").equals("secret"))
                    machine.getSecretRecipes().add(new CraftingRecipe(resultSet.getInt("id"), resultSet.getString("items"), resultSet.getString("result"), resultSet.getInt("result_limit"), resultSet.getInt("result_crafted"), resultSet.getString("badge"), achievement));
            }
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(resultSet);
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }

    public static void loadAllowedItems(CraftingMachine machine) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Map<Integer, String> data = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT itemName, itemId, machineBaseId FROM furniture_crafting_items WHERE machineBaseId = ?", sqlConnection);
            preparedStatement.setInt(1, machine.getBaseId());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                machine.getAllowedItems().put(resultSet.getInt("itemId"), resultSet.getString("itemName"));
            }
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(resultSet);
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }

    public static void updateLimitedRecipe(CraftingRecipe recipe) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            sqlConnection = SqlHelper.getConnection();
            preparedStatement = SqlHelper.prepare("UPDATE rewards_crafting_recipes SET result_crafted = ? WHERE id = ?", sqlConnection);
            preparedStatement.setInt(1, recipe.getResultTotalCrafted());
            preparedStatement.setInt(1, recipe.getId());

            SqlHelper.executeStatementSilently(preparedStatement, false);
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }

    public static void updateLimitedEgg() {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;

        int limitedAmount = CometSettings.incrementLimitedEgg();

        try {
            sqlConnection = SqlHelper.getConnection();
            preparedStatement = SqlHelper.prepare("UPDATE server_configuration SET easter_limited = ?", sqlConnection);
            preparedStatement.setInt(1, limitedAmount);

            SqlHelper.executeStatementSilently(preparedStatement, false);
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }
}

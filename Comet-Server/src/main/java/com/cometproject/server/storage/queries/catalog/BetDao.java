package com.cometproject.server.storage.queries.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.cometproject.server.game.gamecenter.GameCenterInfo;
import com.cometproject.server.game.players.data.GamePlayer;
import com.cometproject.server.storage.SqlHelper;


public class BetDao {
    public static int insertBet(int playerId, String type, String amount, String timestamp, String result) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("INSERT INTO server_bets (`user_id`, `type`, `amount`, `timestamp`, `result`) VALUES(" +
                    "?, ?, ?, ?, ?);", sqlConnection, true);

            preparedStatement.setInt(1, playerId);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, amount);
            preparedStatement.setString(4, timestamp);
            preparedStatement.setString(5, result);

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(resultSet);
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }

        return 0;
    }

    public static int insertRoulletteResult(String result, String timestamp) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("INSERT INTO server_bets_results (`result`, `timestamp`) VALUES(?, ?);", sqlConnection, true);
            preparedStatement.setString(1, result);
            preparedStatement.setString(2, timestamp);

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(resultSet);
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }

        return 0;
    }

    public static List<GamePlayer> getLeaderBoard(int gameId, int friendId, boolean lastWeek, boolean onlyStaff) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<GamePlayer> data = new LinkedList<>();

        String staff = "";

        if(onlyStaff)
            staff = "AND p.rank > 4";

        String messengerQuery = "";
        String messengerQueryWhere = "";

        if (friendId > 0) {
            messengerQuery = "INNER JOIN messenger_friendships m ON p.id = m.user_two_id";
            messengerQueryWhere = "AND m.user_one_id =" + friendId;
        }

        try {
            sqlConnection = SqlHelper.getConnection();
            preparedStatement = SqlHelper.prepare("SELECT p.id as playerId, p.username AS playerData_username, p.figure AS playerData_figure, p.gender AS playerData_gender, s.current_points AS currentPoints, s.last_points AS lastPoints FROM players p\n" +
                    "INNER JOIN player_gamecenter s ON p.id = s.player_id " + messengerQuery + " WHERE s.game_id = " + gameId + " " + messengerQueryWhere + " " + staff + " ORDER BY currentPoints DESC LIMIT 10", sqlConnection);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                data.add(new GamePlayer(resultSet.getInt("playerId"), resultSet.getString("playerData_username"), resultSet.getString("playerData_figure"), resultSet.getString("playerData_gender"), resultSet.getInt("currentPoints"), resultSet.getInt("lastPoints")));
            }

        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(resultSet);
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }

        return data;
    }

    public static List<GameCenterInfo> getGames() {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet config = null;

        List<GameCenterInfo> gameList = new ArrayList<>();

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT * FROM gamecenter_list WHERE visible = '1'", sqlConnection);
            config = preparedStatement.executeQuery();

            while (config.next()) {
                GameCenterInfo game = new GameCenterInfo(config.getInt("id"), config.getString("name"), config.getString("path"), config.getInt("roomId"));
                gameList.add(game);
            }
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(config);
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
        return gameList;
    }
}

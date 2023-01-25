package com.cometproject.server.storage.queries.pets;

import com.cometproject.api.game.pets.IPetData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.pets.data.PetData;
import com.cometproject.server.game.pets.data.PetMonsterPlantData;
import com.cometproject.server.game.pets.races.PetType;
import com.cometproject.server.storage.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RoomPetDao {
    public static List<IPetData> getPetsByRoomId(int roomId) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<IPetData> data = new ArrayList<>();

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT \n" +
                    "pet.id AS id,  \n" +
                    "pet.pet_name AS pet_name,  \n" +
                    "pet.level AS level,  \n" +
                    "pet.room_id AS room_id,  \n" +
                    "pet.scratches AS scratches,  \n" +
                    "pet.happiness AS happiness,  \n" +
                    "pet.experience AS experience,  \n" +
                    "pet.energy AS energy,  \n" +
                    "pet.hunger AS hunger,  \n" +
                    "pet.owner_id AS owner_id,  \n" +
                    "pet.colour AS colour,  \n" +
                    "pet.race_id AS race_id,  \n" +
                    "pet.type AS type,  \n" +
                    "pet.saddled AS saddled,  \n" +
                    "pet.hair_style AS hair_style,  \n" +
                    "pet.hair_colour AS hair_colour,  \n" +
                    "pet.any_rider AS any_rider,  \n" +
                    "pet.birthday AS birthday,  \n" +
                    "pet.extra_data AS extra_data,  \n" +
                    "pet.x AS x,   \n" +
                    "pet.z AS z,   \n" +
                    "pet.y AS y,  \n" +
                    "player.username AS owner_name  \n" +
                    "FROM pet_data AS pet   \n" +
                    "RIGHT JOIN players AS player ON player.id = pet.owner_id  \n" +
                    "WHERE pet.room_id = ?", sqlConnection);

            preparedStatement.setInt(1, roomId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PetData petData;
                if (resultSet.getInt("type") == PetType.MONSTERPLANT) {
                    petData = new PetMonsterPlantData(resultSet);
                } else {
                    petData = new PetData(resultSet);
                }
                petData.setRoomPosition(new Position(resultSet.getInt("x"), resultSet.getInt("y"), resultSet.getDouble("z")));
                data.add(petData);
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

    public static void updatePet(int roomId, int x, int y, int petId) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("UPDATE pet_data SET room_id = ?, x = ?, y = ? WHERE id = ?", sqlConnection);
            preparedStatement.setInt(1, roomId);
            preparedStatement.setInt(2, x);
            preparedStatement.setInt(3, y);
            preparedStatement.setInt(4, petId);

            preparedStatement.execute();
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }
}

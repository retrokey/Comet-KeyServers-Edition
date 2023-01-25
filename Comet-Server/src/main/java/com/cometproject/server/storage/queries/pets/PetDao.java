package com.cometproject.server.storage.queries.pets;

import com.cometproject.api.game.pets.IPetData;
import com.cometproject.api.game.pets.IPetStats;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.pets.data.*;
import com.cometproject.server.game.pets.races.PetBreedLevel;
import com.cometproject.server.game.pets.races.PetRace;
import com.cometproject.server.game.pets.races.PetType;
import com.cometproject.server.storage.SqlHelper;
import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class PetDao {
    public static List<PetRace> getRaces() {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<PetRace> data = new ArrayList<>();

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT * FROM pet_races", sqlConnection);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                data.add(new PetRace(resultSet));
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

    public static Map<Integer, PetSpeech> getMessages(AtomicInteger petSpeechCount) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Map<Integer, PetSpeech> data = new ConcurrentHashMap<>();

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT * FROM pet_messages", sqlConnection);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int petType = resultSet.getInt("pet_type");
                PetMessageType messageType = PetMessageType.valueOf(resultSet.getString("message_type"));

                if (!data.containsKey(petType)) {
                    data.put(petType, new PetSpeech());
                }

                PetSpeech petSpeech = data.get(petType);

                if (!petSpeech.getMessages().containsKey(messageType)) {
                    petSpeech.getMessages().put(messageType, Lists.newArrayList());
                }

                petSpeechCount.incrementAndGet();
                petSpeech.getMessages().get(messageType).add(resultSet.getString("message_string"));
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

    public static Map<String, String> getTransformablePets() {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Map<String, String> data = new ConcurrentHashMap<>();

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT * FROM pet_transformable", sqlConnection);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                data.put(resultSet.getString("name"), resultSet.getString("data"));
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

    public static Map<Integer, IPetData> getPetsByPlayerId(int playerId) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Map<Integer, IPetData> data = new ConcurrentHashMap<>();

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT  \n" +
                    "pet.id AS id,  \n" +
                    "pet.pet_name AS pet_name,  \n" +
                    "pet.level AS level,  \n" +
                    "pet.happiness AS happiness,  \n" +
                    "pet.room_id AS room_id,  \n" +
                    "pet.experience AS experience,  \n" +
                    "pet.scratches AS scratches,  \n" +
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
                    "pet.y AS y,  \n" +
                    "pet.z AS z,  \n" +
                    "player.username AS owner_name  \n" +
                    "FROM pet_data AS pet   \n" +
                    "RIGHT JOIN players AS player ON player.id = pet.owner_id  \n" +
                    "WHERE pet.owner_id = ? AND pet.room_id = 0", sqlConnection);

            preparedStatement.setInt(1, playerId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt("type") == PetType.MONSTERPLANT) {
                    data.put(resultSet.getInt("id"), new PetMonsterPlantData(resultSet));
                } else {
                    data.put(resultSet.getInt("id"), new PetData(resultSet));
                }
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

    public static int createPet(int ownerId, String petName, int type, int race, String colour, String extraData) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("INSERT INTO `pet_data` (`owner_id`, `pet_name`, `type`, `race_id`, `colour`, `scratches`, `level`, `happiness`, `experience`, `energy`, `birthday`, `extra_data`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", sqlConnection, true);

            preparedStatement.setInt(1, ownerId);
            preparedStatement.setString(2, petName);
            preparedStatement.setInt(3, type);
            preparedStatement.setInt(4, race);
            preparedStatement.setString(5, colour);
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, StaticPetProperties.DEFAULT_LEVEL);
            preparedStatement.setInt(8, StaticPetProperties.DEFAULT_HAPPINESS);
            preparedStatement.setInt(9, StaticPetProperties.DEFAULT_EXPERIENCE);
            preparedStatement.setInt(10, StaticPetProperties.DEFAULT_ENERGY);
            preparedStatement.setInt(11, (int) Comet.getTime());
            preparedStatement.setString(12, extraData);

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

    public static void savePosition(int x, int y, int id) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("UPDATE pet_data SET x = ?, y = ? WHERE id = ?", sqlConnection);
            preparedStatement.setInt(1, x);
            preparedStatement.setInt(2, y);
            preparedStatement.setInt(3, id);

            SqlHelper.executeStatementSilently(preparedStatement, false);
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }

    public static void saveStats(int scratches, int level, int happiness, int experience, int energy, int hunger, String extradata, int petId) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("UPDATE pet_data SET scratches = ?, level = ?, happiness = ?, experience = ?, energy = ?, hunger = ?, extra_data = ? WHERE id = ?", sqlConnection);

            preparedStatement.setInt(1, scratches);
            preparedStatement.setInt(2, level);
            preparedStatement.setInt(3, happiness);
            preparedStatement.setInt(4, experience);
            preparedStatement.setInt(5, energy);
            preparedStatement.setInt(6, hunger);
            preparedStatement.setString(7, extradata);

            preparedStatement.setInt(8, petId);

            SqlHelper.executeStatementSilently(preparedStatement, false);
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }

    public static void savePetsBatch(IPetData data) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            sqlConnection = SqlHelper.getConnection();
            preparedStatement = SqlHelper.prepare("UPDATE pet_data SET scratches = ?, level = ?, happiness = ?, experience = ?, energy = ?, hunger = ?, extra_data = ?, x = ?, y = ?, saddled = ?, hair_style = ?, hair_colour = ?, any_rider = ?, race_id = ?, room_id = ?, z = ? WHERE id = ?", sqlConnection);

                preparedStatement.setInt(1, data.getScratches());
                preparedStatement.setInt(2, data.getLevel());
                preparedStatement.setInt(3, data.getHappiness());
                preparedStatement.setInt(4, data.getExperience());
                preparedStatement.setInt(5, data.getExperience());
                preparedStatement.setInt(6, data.getHunger());
                preparedStatement.setString(7, (data.getExtradata() == null ? "" : data.getExtradata()));
                preparedStatement.setInt(8, data.getRoomPosition().getX());
                preparedStatement.setInt(9, data.getRoomPosition().getY());
                preparedStatement.setString(10, Boolean.toString(data.isSaddled()));
                preparedStatement.setInt(11, data.getHair());
                preparedStatement.setInt(12, data.getHairDye());
                preparedStatement.setString(13, Boolean.toString(data.isAnyRider()));
                preparedStatement.setInt(14, data.getRaceId());
                preparedStatement.setInt(15, data.getRoomId());
                preparedStatement.setDouble(16, data.getRoomPosition().getZ());
                preparedStatement.setInt(17, data.getId());
                preparedStatement.addBatch();


            preparedStatement.executeBatch();
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }


    public static void saveStatsBatch(final Set<IPetStats> petStats) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("UPDATE pet_data SET scratches = ?, level = ?, happiness = ?, experience = ?, energy = ?, hunger = ? WHERE id = ?;", sqlConnection);

            for (IPetStats pet : petStats) {
                preparedStatement.setInt(1, pet.getScratches());
                preparedStatement.setInt(2, pet.getLevel());
                preparedStatement.setInt(3, pet.getHappiness());
                preparedStatement.setInt(4, pet.getExperience());
                preparedStatement.setInt(5, pet.getEnergy());
                preparedStatement.setInt(6, pet.getHunger());

                preparedStatement.setInt(7, pet.getId());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }


    public static void deletePets(int playerId) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("DELETE FROM pet_data WHERE owner_id = ? AND room_id = 0;", sqlConnection);
            preparedStatement.setInt(1, playerId);

            SqlHelper.executeStatementSilently(preparedStatement, false);
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }

    public static void deletePet(int playerId, int petId) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("DELETE FROM pet_data WHERE owner_id = ? AND id = ?;", sqlConnection);
            preparedStatement.setInt(1, playerId);
            preparedStatement.setInt(2, petId);

            SqlHelper.executeStatementSilently(preparedStatement, false);
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }

    public static void saveHorseData(int id, boolean saddled, int hair, int hairDye, boolean anyRider, int raceId) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("UPDATE pet_data SET saddled = ?, hair_style = ?, hair_colour = ?, any_rider = ?, race_id = ? WHERE id = ?", sqlConnection);

            preparedStatement.setString(1, saddled ? "true" : "false");
            preparedStatement.setInt(2, hair);
            preparedStatement.setInt(3, hairDye);
            preparedStatement.setString(4, anyRider ? "true" : "false");
            preparedStatement.setInt(5, raceId);

            preparedStatement.setInt(6, id);

            SqlHelper.executeStatementSilently(preparedStatement, false);
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }

    public static Map<Integer, Map<PetBreedLevel, Set<Integer>>> getPetBreedPallets() {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Map<Integer, Map<PetBreedLevel, Set<Integer>>> data = new ConcurrentHashMap<>();

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT * FROM pet_breeds;", sqlConnection);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int petType = resultSet.getInt("pet_type");
                final int palletId = resultSet.getInt("pallet_id");
                final PetBreedLevel breedLevel = PetBreedLevel.valueOf(resultSet.getString("level"));

                if (!data.containsKey(petType)) {
                    data.put(petType, new ConcurrentHashMap<PetBreedLevel, Set<Integer>>() {{
                        put(PetBreedLevel.EPIC, new HashSet<>());
                        put(PetBreedLevel.RARE, new HashSet<>());
                        put(PetBreedLevel.UNCOMMON, new HashSet<>());
                        put(PetBreedLevel.COMMON, new HashSet<>());
                    }});
                }

                data.get(petType).get(breedLevel).add(palletId);
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
}

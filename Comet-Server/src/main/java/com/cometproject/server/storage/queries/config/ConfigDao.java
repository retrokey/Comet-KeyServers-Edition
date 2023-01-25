package com.cometproject.server.storage.queries.config;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.config.SurvivalSettings;
import com.cometproject.api.game.rooms.filter.FilterMode;
import com.cometproject.server.game.gamecenter.GameCenterInfo;
import com.cometproject.server.storage.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;


public class ConfigDao {
    public static void getAll() {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet config = null;
        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT * FROM server_configuration LIMIT 1", sqlConnection);

            config = preparedStatement.executeQuery();

            while (config.next()) {
                CometSettings.motdEnabled = config.getBoolean("motd_enabled");
                CometSettings.motdMessage = config.getString("motd_message");
                CometSettings.hotelName = config.getString("hotel_name");
                CometSettings.hotelUrl = config.getString("hotel_url");
                CometSettings.groupCost = config.getInt("group_cost");
                CometSettings.onlineRewardEnabled = config.getBoolean("online_reward_enabled");
                CometSettings.onlineRewardCredits = config.getInt("online_reward_credits");
                CometSettings.onlineRewardDuckets = config.getInt("online_reward_duckets");
                CometSettings.onlineRewardInterval = config.getInt("online_reward_interval");
                CometSettings.aboutImg = config.getString("about_image");
                CometSettings.aboutShowPlayersOnline = config.getBoolean("about_show_players_online");
                CometSettings.aboutShowRoomsActive = config.getBoolean("about_show_rooms_active");
                CometSettings.aboutShowUptime = config.getBoolean("about_show_uptime");
                CometSettings.floorEditorMaxX = config.getInt("floor_editor_max_x");
                CometSettings.floorEditorMaxY = config.getInt("floor_editor_max_y");
                CometSettings.floorEditorMaxTotal = config.getInt("floor_editor_max_total");
                CometSettings.roomMaxPlayers = config.getInt("room_max_players");
                CometSettings.roomEncryptPasswords = config.getBoolean("room_encrypt_passwords");
                CometSettings.roomCanPlaceItemOnEntity = config.getBoolean("room_can_place_item_on_entity");
                CometSettings.roomMaxBots = config.getInt("room_max_bots");
                CometSettings.roomMaxPets = config.getInt("room_max_pets");
                CometSettings.roomWiredRewardMinimumRank = config.getInt("room_wired_reward_minimum_rank");
                CometSettings.roomIdleMinutes = config.getInt("room_idle_minutes");
                CometSettings.wordFilterMode = FilterMode.valueOf(config.getString("word_filter_mode").toUpperCase());
                CometSettings.useDatabaseIp = config.getBoolean("use_database_ip");
                CometSettings.console_debugging = config.getBoolean("console_debug");
                CometSettings.saveLogins = config.getBoolean("save_logins");
                CometSettings.playerInfiniteBalance = config.getBoolean("player_infinite_balance");
                CometSettings.playerGiftCooldown = config.getInt("player_gift_cooldown");
                CometSettings.playerChangeFigureCooldown = config.getInt("player_change_figure_cooldown");
                CometSettings.playerFigureValidation = config.getBoolean("player_figure_validation");
                CometSettings.messengerMaxFriends = config.getInt("messenger_max_friends");
                CometSettings.messengerLogMessages = config.getBoolean("messenger_log_messages");
                CometSettings.cameraPhotoUrl = config.getString("camera_photo_url");
                CometSettings.cameraPhotoItemId = config.getInt("camera_photo_itemid");
                CometSettings.webSocketUrl = config.getString("websocket_url");
                CometSettings.cameraUploadUrl = config.getString("camera_photo_upload_url");
                CometSettings.thumbnailUploadUrl = config.getString("thumbnail_upload_url");

                CometSettings.maxConnectionsPerIpAddress = config.getInt("max_connections_per_ip");
                CometSettings.maxConnectionsBlockSuspicious = config.getBoolean("max_connections_block_suspicious");
                CometSettings.groupChatEnabled = config.getBoolean("group_chat_enabled");
                CometSettings.logCatalogPurchases = config.getBoolean("log_catalog_purchases");
                CometSettings.hallOfFameEnabled = config.getBoolean("hall_of_fame_enabled");
                CometSettings.hallOfFameCurrency = config.getString("hall_of_fame_currency");
                CometSettings.hallOfFameRefreshMinutes = config.getInt("hall_of_fame_refresh_minutes");
                CometSettings.hallOfFameTextsKey = config.getString("hall_of_fame_texts_key");

                CometSettings.wiredMaxEffects = config.getInt("room_wired_max_effects");
                CometSettings.wiredMaxTriggers = config.getInt("room_wired_max_triggers");
                CometSettings.wiredMaxExecuteStacks = config.getInt("room_wired_max_execute_stacks");

                CometSettings.snowStormMinPlayers = config.getInt("snow_storm_min_players");
                CometSettings.calendarTimestamp = config.getInt("calendar_timestamp");
                CometSettings.onlineRewardDiamondsInterval = config.getInt("online_reward_diamonds_interval");
                CometSettings.onlineRewardDiamonds = config.getInt("online_reward_diamonds");
                CometSettings.betSystemEnabled = config.getBoolean("bet_system_enabled");
                CometSettings.eventWinnerNotification = config.getBoolean("event_winner_enabled");
                CometSettings.maxSeasonalRewardPoints = config.getInt("max_seasonal_reward");
                CometSettings.seasonalRewardActivityPoints = config.getInt("seasonal_activity_points");
                CometSettings.betSystemRoomId = config.getInt("bet_system_roomid");
                CometSettings.bankSystemMinimumRequired = config.getInt("bank_mininum_required");
                CometSettings.communityGoal = config.getInt("community_goal");
                CometSettings.communityGoalPrize = config.getString("community_goal_prize");
                CometSettings.casinoRoomId = config.getInt("casino_roomid");
                CometSettings.globalEggsCrafted = config.getInt("easter_limited");
                CometSettings.bankSystemSeasonalEnabled = config.getBoolean("bank_seasonal_enabled");
                CometSettings.eventWinnerNotification = config.getBoolean("casino_free_roll_enabled");
                CometSettings.bonusBagEnabled = config.getBoolean("landing_bag_enabled");
                CometSettings.currencySystemEnabled = config.getBoolean("currency_enabled");
                CometSettings.bonusBagConfiguration = config.getString("landing_bag_configuration");
                CometSettings.baseWelcomeRoomId = config.getInt("welcome_room_id");

                final String characters = config.getString("word_filter_strict_chars");

                CometSettings.strictFilterCharacters.clear();

                for (String charSet : characters.split(",")) {
                    if (!charSet.contains(":")) continue;

                    final String[] chars = charSet.split(":");

                    if (chars.length == 2) {
                        CometSettings.strictFilterCharacters.put(chars[0], chars[1]);
                    } else {
                        CometSettings.strictFilterCharacters.put(chars[0], "");
                    }
                }


                final String doubleDays = config.getString("online_reward_double_days");
                CometSettings.onlineRewardDoubleDays.clear();

                if(doubleDays.length() > 1) {
                    final String[] days = doubleDays.split(",");

                    for(String day : days) {
                        CometSettings.onlineRewardDoubleDays.add(DayOfWeek.valueOf(day.toUpperCase()));
                    }
                }
            }
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(config);
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }

    public static void getSurvivalSettings() {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet config = null;
        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("SELECT * FROM server_survival_settings LIMIT 1", sqlConnection);

            config = preparedStatement.executeQuery();

            while (config.next()) {
                SurvivalSettings.gunDamage = config.getInt("gun_damage");
                SurvivalSettings.gunDistance = config.getInt("gun_distance");
                SurvivalSettings.gunCooldown = config.getInt("gun_cooldown");
                SurvivalSettings.gunBullets = config.getInt("gun_bullets");
                SurvivalSettings.sniperDamage = config.getInt("sniper_damage");
                SurvivalSettings.sniperDistance = config.getInt("sniper_distance");
                SurvivalSettings.sniperCooldown = config.getInt("sniper_cooldown");
                SurvivalSettings.sniperBullets = config.getInt("sniper_bullets");
                SurvivalSettings.meleeDamage = config.getInt("melee_damage");
                SurvivalSettings.meleeDistance = config.getInt("melee_distance");
                SurvivalSettings.meleeCooldown = config.getInt("melee_cooldown");
                SurvivalSettings.xpPerKill = config.getInt("slay_xp");
                SurvivalSettings.bulletsPerKill = config.getInt("slay_bullets");
                SurvivalSettings.shieldPerKill = config.getBoolean("slay_shield");
                SurvivalSettings.xpPerWin = config.getInt("win_xp");
                SurvivalSettings.chestBullets = config.getInt("chest_bullets");
                SurvivalSettings.speedBoostTime = config.getInt("speed_time");
            }
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(config);
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }
}



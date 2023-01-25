package com.cometproject.server.game.permissions.types;

import com.cometproject.api.game.players.data.components.permissions.PlayerRank;
import com.cometproject.server.game.permissions.Permission;
import com.cometproject.server.game.permissions.PermissionSetting;
import gnu.trove.map.hash.THashMap;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Rank implements PlayerRank {
    private final int id;
    private String name;

    private boolean floodBypass;
    private int floodTime;

    private boolean disconnectable;
    private boolean modTool;
    private boolean alfaTool;
    private boolean bannable;

    private boolean roomKickable;
    private boolean roomFullControl;
    private boolean roomMuteBypass;
    private boolean roomFilterBypass;
    private boolean roomIgnorable;
    private boolean roomEnterFull;
    private boolean roomEnterLocked;
    private boolean roomStaffPick;
    private boolean roomSeeWhispers;

    private boolean messengerStaffChat;
    private boolean messengerModsChat;
    private boolean messengerLogChat;
    private boolean messengerAlfaChat;
    private int messengerMaxFriends;

    private boolean aboutDetailed;
    private boolean aboutStats;
    private boolean loginNotif;

    private final THashMap<String, Permission> permissions;
    private final THashMap<String, String> variables;

    public Rank(ResultSet set) throws SQLException {
        this.permissions = new THashMap<>();
        this.variables = new THashMap<>();
        this.id = set.getInt("id");

        this.load(set);
    }

    public void load(ResultSet set) throws SQLException {
        ResultSetMetaData meta = set.getMetaData();
        this.name = set.getString("name");

        this.floodBypass = set.getString("flood_bypass").equals("1");
        this.floodTime = set.getInt("flood_time");
        this.disconnectable = set.getString("disconnectable").equals("1");
        this.modTool = set.getString("mod_tool").equals("1");
        this.alfaTool = set.getString("alfa_tool").equals("1");
        this.bannable = set.getString("bannable").equals("");
        this.roomKickable = set.getString("room_kickable").equals("1");
        this.roomFullControl = set.getString("room_full_control").equals("1");
        this.roomMuteBypass = set.getString("room_mute_bypass").equals("1");
        this.roomFilterBypass = set.getString("room_filter_bypass").equals("1");
        this.roomIgnorable = set.getString("room_ignorable").equals("1");
        this.roomEnterFull = set.getString("room_enter_full").equals("1");
        this.roomEnterLocked = set.getString("room_enter_locked").equals("1");
        this.roomStaffPick = set.getString("room_staff_pick").equals("1");
        this.roomSeeWhispers = set.getString("room_see_whispers").equals("1");
        this.messengerStaffChat = set.getString("messenger_staff_chat").equals("1");
        this.messengerModsChat = set.getString("messenger_mod_chat").equals("1");
        this.messengerLogChat = set.getString("messenger_log_chat").equals("1");
        this.messengerAlfaChat = set.getString("messenger_alfa_chat").equals("1");
        this.messengerMaxFriends = set.getInt("messenger_max_friends");
        this.aboutDetailed = set.getString("about_detailed").equals("1");
        this.aboutStats = set.getString("about_stats").equals("1");
        this.loginNotif = set.getString("login_notif").equals("1");

        for (int i = 1; i < meta.getColumnCount() + 1; i++) {
            String columnName = meta.getColumnName(i);
            if (columnName.startsWith("cmd_") || columnName.startsWith("acc_") || columnName.contains("_command")) {
                this.permissions.put(meta.getColumnName(i), new Permission(columnName, PermissionSetting.fromString(set.getString(i))));
            } else {
                this.variables.put(meta.getColumnName(i), set.getString(i));
            }
        }
    }

    public boolean hasPermission(String key, boolean isRoomOwner) {
        if (this.permissions.containsKey(key)) {
            Permission permission = this.permissions.get(key);

            return permission.setting == PermissionSetting.ALLOWED || permission.setting == PermissionSetting.ROOM_OWNER && isRoomOwner;

        }

        return false;
    }

    public THashMap<String, Permission> getPermissions() {
        return permissions;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean floodBypass() {
        return this.floodBypass;
    }

    @Override
    public int floodTime() {
        return this.floodTime;
    }

    @Override
    public boolean disconnectable() {
        return this.disconnectable;
    }

    @Override
    public boolean bannable() {
        return this.bannable;
    }

    @Override
    public boolean modTool() {
        return this.modTool;
    }

    @Override
    public boolean alfaTool() {
        return this.alfaTool;
    }

    @Override
    public boolean roomKickable() {
        return this.roomKickable;
    }

    @Override
    public boolean roomFullControl() {
        return this.roomFullControl;
    }

    @Override
    public boolean roomMuteBypass() {
        return this.roomMuteBypass;
    }

    @Override
    public boolean roomFilterBypass() {
        return this.roomFilterBypass;
    }

    @Override
    public boolean roomIgnorable() {
        return this.roomIgnorable;
    }

    @Override
    public boolean roomEnterFull() {
        return roomEnterFull;
    }

    @Override
    public boolean messengerStaffChat() {
        return this.messengerStaffChat;
    }

    @Override
    public boolean messengerModChat() {
        return this.messengerModsChat;
    }

    @Override
    public boolean messengerLogChat() {
        return this.messengerLogChat;
    }

    @Override
    public boolean messengerAlfaChat() {
        return this.messengerAlfaChat;
    }

    @Override
    public boolean roomEnterLocked() {
        return roomEnterLocked;
    }

    @Override
    public boolean roomStaffPick() {
        return this.roomStaffPick;
    }

    @Override
    public int messengerMaxFriends() {
        return this.messengerMaxFriends;
    }

    @Override
    public boolean aboutDetailed() {
        return this.aboutDetailed;
    }

    @Override
    public boolean aboutStats() {
        return aboutStats;
    }

    public boolean roomSeeWhispers() {
        return this.roomSeeWhispers;
    }

    public boolean sendLoginNotif() {
        return loginNotif;
    }
}

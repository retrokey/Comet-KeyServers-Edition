package com.cometproject.server.game.players.components;

import com.cometproject.api.game.players.data.components.PlayerPermissions;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.permissions.PermissionsManager;
import com.cometproject.server.game.permissions.types.CommandPermission;
import com.cometproject.server.game.permissions.types.OverrideCommandPermission;
import com.cometproject.server.game.permissions.types.Rank;
import com.cometproject.server.game.players.types.Player;


public class PermissionComponent implements PlayerPermissions {
    private Player player;

    public PermissionComponent(Player player) {
        this.player = player;
    }

    @Override
    public Rank getRank() {
        return PermissionsManager.getInstance().getRank(this.player.getData().getRank());
    }

    @Override
    public boolean hasCommand(String key) {

        if (PermissionsManager.getInstance().getOverrideCommands().containsKey(key)) {
            OverrideCommandPermission permission = PermissionsManager.getInstance().getOverrideCommands().get(key);

            if(permission == null)
                return false;

            if (permission.getPlayerId() == this.getPlayer().getData().getId() && permission.isEnabled()) {
                return true;
            }
        }

        return PermissionsManager.getInstance().hasPermission(this.player, key) || this.player.getPermissions().getRank().hasPermission(key, this.player.getEntity().getRoom() != null && (this.player.getEntity().hasRights()));
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void dispose() {

    }
}
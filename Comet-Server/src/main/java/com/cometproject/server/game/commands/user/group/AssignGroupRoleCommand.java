package com.cometproject.server.game.commands.user.group;

import com.cometproject.api.game.groups.types.IGroup;
import com.cometproject.api.game.groups.types.components.membership.IGroupMember;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.players.PlayerManager;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.storage.api.StorageContext;

public class AssignGroupRoleCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 2) {
            client.getPlayer().sendBubble("group_roles", Locale.getOrDefault("group.role.user.empty", "No has introducido ningún nombre de usuario."));
            return;
        }

        if (client.getPlayer().getId() != client.getPlayer().getEntity().getRoom().getData().getOwnerId() && !client.getPlayer().getPermissions().getRank().roomFullControl()) {
            client.getPlayer().sendBubble("group_roles", Locale.getOrDefault("command.roleassign.permission", "No tienes permisos para modificar el rol de este jugador."));
            return;
        }

        final Room room = client.getPlayer().getEntity().getRoom();

        if (room.getGroup() != null) {
            IGroup group = room.getGroup();
            String username = params[0];
            int playerId = PlayerManager.getInstance().getPlayerIdByUsername(username);
            String role = this.merge(params, 1);

            IGroupMember groupMember = group.getMembers().getAll().get(playerId);

            if (groupMember == null) {
                client.getPlayer().sendBubble("group_role", Locale.getOrDefault("command.grouprole.offline", "No has podido asignar el rol a %user% porque se encuentra desconectado.").replace("%user%", username));
                return;
            }

            groupMember.setRole(role);
            StorageContext.getCurrentContext().getGroupMemberRepository().updateRole(groupMember, role);
            client.getPlayer().sendBubble("group_role", Locale.getOrDefault("command.grouprole.assigned", "Has asignado correctamente el rol a %user%").replace("%user%", username));

        } else {
            client.getPlayer().sendBubble("group_roles", Locale.getOrDefault("command.grouprole.nogroup", "La sala en la que estás intentando asignar un rol de grupo no tiene grupo."));
        }
    }

    @Override
    public String getPermission() { return "grouprole_command"; }

    @Override
    public String getParameter() { return "%username% %role%"; }

    @Override
    public String getDescription() { return Locale.get("command.grouprole.description"); }
}

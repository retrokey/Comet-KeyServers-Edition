package com.cometproject.server.game.commands.user.settings;

import com.cometproject.api.game.GameContext;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.commands.CommandManager;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.notification.AdvancedAlertMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class DisableCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        Room room = client.getPlayer().getEntity().getRoom();

        if ((room.getData().getOwnerId() != client.getPlayer().getId() && !client.getPlayer().getPermissions().getRank().roomFullControl())) {
            sendNotif(Locale.getOrDefault("command.need.rights", "You have no rights to use this command in this room."), client);
            return;
        }

        if (params.length != 1) {
            return;
        }

        String disabledCommand = params[0];

        if (CommandManager.getInstance().isCommand(disabledCommand) && CommandManager.getInstance().getChatCommands().get(disabledCommand).canDisable()) {
            room.getData().getDisabledCommands().add(disabledCommand);
            GameContext.getCurrent().getRoomService().saveRoomData(room.getData());

            sendNotif(Locale.get("command.disablecommand.success"), client);
        } else {
            client.send(new AdvancedAlertMessageComposer(Locale.get("command.disablecommand.error")));
        }
    }

    @Override
    public String getPermission() {
        return "disablecommand_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.disablecommand.description");
    }
}

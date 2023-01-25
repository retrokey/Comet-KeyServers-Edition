package com.cometproject.server.game.commands.notifications;

import com.cometproject.server.game.commands.notifications.types.Notification;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.storage.queries.system.NotificationCommandsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class NotificationManager {
    private Map<String, Notification> notifications;

    private Logger LOGGER = LoggerFactory.getLogger(NotificationManager.class);

    public NotificationManager() {
        this.notifications = NotificationCommandsDao.getAll();

        LOGGER.info("Loaded " + notifications.size() + " notification commands");
    }

    public boolean isNotificationExecutor(String text, int rank) {
        return this.notifications.containsKey(text.substring(1)) && this.notifications.get(text.substring(1)).getMinRank() <= rank;
    }

    public void execute(Player player, String command) {
        Notification notification = this.notifications.get(command);

        if (notification == null)
            return;

        notification.execute(player);
    }
}

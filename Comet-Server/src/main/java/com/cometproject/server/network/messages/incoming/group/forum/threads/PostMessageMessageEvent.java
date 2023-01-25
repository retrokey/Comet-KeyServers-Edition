package com.cometproject.server.network.messages.incoming.group.forum.threads;

import com.cometproject.api.game.GameContext;
import com.cometproject.api.game.groups.types.IGroup;
import com.cometproject.api.game.groups.types.components.forum.ForumPermission;
import com.cometproject.api.game.groups.types.components.forum.IForumSettings;
import com.cometproject.api.game.groups.types.components.forum.IForumThread;
import com.cometproject.api.game.groups.types.components.forum.IForumThreadReply;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.composers.group.forums.GroupForumPostReplyMessageComposer;
import com.cometproject.server.composers.group.forums.GroupForumPostThreadMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.filter.FilterResult;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.AdvancedAlertMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.cometproject.server.storage.queries.groups.GroupForumThreadDao;

public class PostMessageMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        final int groupId = msg.readInt();
        final int threadId = msg.readInt();
        String subject = msg.readString();
        String message = msg.readString();

        final IGroup group = GameContext.getCurrent().getGroupService().getGroup(groupId);

        if (group == null || !group.getData().hasForum()) {
            return;
        }

        if (message.length() < 10 || message.length() > 4000) return;

        if (client.getPlayer().getLastForumPost() != 0) {
            if ((((int) Comet.getTime()) - client.getPlayer().getLastForumPost()) < 60) {
                return;
            }
        }

        if (!client.getPlayer().getPermissions().getRank().roomFilterBypass()) {
            FilterResult messageFilter = RoomManager.getInstance().getFilter().filter(message);

            if (messageFilter.isBlocked()) {
                messageFilter.sendLogToStaffs(client, "PostMessage");
                client.send(new AdvancedAlertMessageComposer(Locale.get("game.message.blocked").replace("%s", messageFilter.getMessage())));
                return;
            } else if (messageFilter.wasModified()) {
                message = messageFilter.getMessage();
            }

            FilterResult subjectFilter = RoomManager.getInstance().getFilter().filter(subject);

            if (subjectFilter.isBlocked()) {
                subjectFilter.sendLogToStaffs(client, "PostMessage");
                client.send(new AdvancedAlertMessageComposer(Locale.get("game.message.blocked").replace("%s", subjectFilter.getMessage())));
                return;
            } else if (subjectFilter.wasModified()) {
                subject = subjectFilter.getMessage();
            }
        }


        final IForumSettings forumSettings = group.getForum().getForumSettings();

        if (threadId == 0) {

            if (subject.length() < 10) {
                return;
            }

            boolean permissionToPost = true;

            if (forumSettings.getStartThreadsPermission() != ForumPermission.EVERYBODY) {
                switch (forumSettings.getStartThreadsPermission()) {
                    case ADMINISTRATORS:
                        if (!group.getMembers().getAdministrators().contains(client.getPlayer().getId())) {
                            permissionToPost = false;
                        }
                        break;

                    case OWNER:
                        if (client.getPlayer().getId() != group.getData().getOwnerId()) {
                            permissionToPost = false;
                        }
                        break;

                    case MEMBERS:
                        if (!group.getMembers().getAll().containsKey(client.getPlayer().getId())) {
                            permissionToPost = false;
                        }
                }
            }

            if (!permissionToPost) {
                // No permission notif?
                return;
            }

            IForumThread forumThread = GroupForumThreadDao.createThread(groupId, subject, message, client.getPlayer().getId());

            if (forumThread == null) {
                // Why u do dis?
                return;
            }

            group.getForum().getForumThreads().put(forumThread.getId(), forumThread);
            client.send(new GroupForumPostThreadMessageComposer(groupId, forumThread));

            client.getPlayer().setLastForumPost((int) Comet.getTime());
        } else {
            boolean permissionToPost = true;

            if (forumSettings.getPostPermission() != ForumPermission.EVERYBODY) {
                switch (forumSettings.getPostPermission()) {
                    case ADMINISTRATORS:
                        if (!group.getMembers().getAdministrators().contains(client.getPlayer().getId())) {
                            permissionToPost = false;
                        }
                        break;

                    case OWNER:
                        if (client.getPlayer().getId() != group.getData().getOwnerId()) {
                            permissionToPost = false;
                        }
                        break;

                    case MEMBERS:
                        if (!group.getMembers().getAll().containsKey(client.getPlayer().getId())) {
                            permissionToPost = false;
                        }
                }
            }

            if (!permissionToPost) {
                // No permission notif?
                return;
            }

            IForumThread forumThread = group.getForum().getForumThreads().get(threadId);

            if (forumThread == null) {
                return;
            }

            IForumThreadReply reply = GroupForumThreadDao.createReply(groupId, threadId, message, client.getPlayer().getId());

            if (reply == null) {
                return;
            }

            forumThread.addReply(reply);
            reply.setIndex(forumThread.getReplies().indexOf(reply));

            // Send to client.
            client.send(new GroupForumPostReplyMessageComposer(groupId, threadId, reply));
            client.getPlayer().setLastForumPost((int) Comet.getTime());
        }
    }
}

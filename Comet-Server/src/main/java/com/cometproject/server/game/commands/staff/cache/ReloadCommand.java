package com.cometproject.server.game.commands.staff.cache;

import com.cometproject.api.game.GameContext;
import com.cometproject.server.composers.catalog.CatalogPublishMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.achievements.AchievementManager;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.commands.CommandManager;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.landing.LandingManager;
import com.cometproject.server.game.moderation.BanManager;
import com.cometproject.server.game.moderation.ModerationManager;
import com.cometproject.server.game.navigator.NavigatorManager;
import com.cometproject.server.game.permissions.PermissionsManager;
import com.cometproject.server.game.pets.PetManager;
import com.cometproject.server.game.pets.commands.PetCommandManager;
import com.cometproject.server.game.polls.PollManager;
import com.cometproject.server.game.polls.types.Poll;
import com.cometproject.server.game.quests.QuestManager;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.bundles.RoomBundleManager;
import com.cometproject.server.game.rooms.emojis.Emoji;
import com.cometproject.server.game.rooms.emojis.EmojiGlobals;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.moderation.ModToolMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.MotdNotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.polls.InitializePollMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.config.ConfigDao;
import com.cometproject.server.storage.queries.rooms.RoomDao;

import java.util.ArrayList;


public class ReloadCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        String command = params.length == 0 ? "" : params[0];

        switch (command) {
            default:
                client.send(new MotdNotificationMessageComposer(
                        "Here's a list of what you can reload using the :reload <type> command!\n\n" +
                                "- bans\n" +
                                "- rpcode\n" +
                                "- catalog\n" +
                                "- survival\n" +
                                "- navigator\n" +
                                "- permissions\n" +
                                "- rooms\n" +
                                "- catalog\n" +
                                "- news\n" +
                                "- config\n" +
                                "- items\n" +
                                "- filter\n" +
                                "- locale\n" +
                                "- modpresets\n" +
                                "- groupitems\n" +
                                "- models\n" +
                                "- music\n" +
                                "- quests\n" +
                                "- achievements\n" +
                                "- pets\n" +
                                "- polls\n" +
                                "- crafting\n" +
                                "- emojis\n" +
                                "- bundles"
                ));

                break;
            case "bans":
                BanManager.getInstance().loadBans();

                sendNotif(Locale.get("command.reload.bans"), client);
                break;

            case "catalog":
                CatalogManager.getInstance().loadItemsAndPages();
                CatalogManager.getInstance().loadGiftBoxes();
                CatalogManager.getInstance().loadNuxGifts();
                CatalogManager.getInstance().loadClothingItems();

                NetworkManager.getInstance().getSessions().broadcast(new CatalogPublishMessageComposer(false));
                sendNotif(Locale.get("command.reload.catalog"), client);
                break;

            case "products":
                CatalogManager.getInstance().loadRPProducts();
                sendNotif(Locale.get("command.reload.products"), client);
                break;

            case "offers":
                CatalogManager.getInstance().loadOffers();
                sendNotif("Acabas de recargar las ofertas de la web.", client);
                break;

            case "navigator":
                NavigatorManager.getInstance().loadCategories();
                NavigatorManager.getInstance().loadPublicRooms();
                NavigatorManager.getInstance().loadStaffPicks();

                sendNotif(Locale.get("command.reload.navigator"), client);
                break;

            case "permissions":
                PermissionsManager.getInstance().loadPermissions();
                PermissionsManager.getInstance().loadPerks();
                PermissionsManager.getInstance().loadEffectsOverride();
                PermissionsManager.getInstance().loadEffects();

                sendNotif(Locale.get("command.reload.permissions"), client);
                break;

            case "config":
                ConfigDao.getAll();

                sendNotif(Locale.get("command.reload.config"), client);
                break;

            case "survival":
                ConfigDao.getSurvivalSettings();

                sendNotif(Locale.getOrDefault("command.reload.survival", "Has recargado correctamente la configuración del Battle Royale."), client);
                break;

            case "news":
                LandingManager.getInstance().loadArticles();

                sendNotif(Locale.get("command.reload.news"), client);
                break;

            case "items":
                ItemManager.getInstance().loadItemDefinitions();

                sendNotif(Locale.get("command.reload.items"), client);
                break;

            case "filter":
                RoomManager.getInstance().getFilter().loadFilter();

                sendNotif(Locale.get("command.reload.filter"), client);
                break;

            case "locale":
                Locale.reload();
                CommandManager.getInstance().reloadAllCommands();

                sendNotif(Locale.get("command.reload.locale"), client);
                break;

            case "modpresets":
                ModerationManager.getInstance().loadPresets();

                sendNotif(Locale.get("command.reload.modpresets"), client);

                ModerationManager.getInstance().getModerators().forEach((session -> {
                    session.send(new ModToolMessageComposer());
                }));
                break;

            case "groupitems":
                GameContext.getCurrent().getGroupService().getItemService().load();
                sendNotif(Locale.get("command.reload.groupitems"), client);
                break;

            case "models":
                GameContext.getCurrent().getRoomModelService().loadModels();

                sendNotif(Locale.get("command.reload.models"), client);
                break;

            case "music":
                ItemManager.getInstance().loadMusicData();
                sendNotif(Locale.get("command.reload.music"), client);
                break;

            case "quests":
                QuestManager.getInstance().loadQuests();
                sendNotif(Locale.get("command.reload.quests"), client);
                break;

            case "achievements":
                AchievementManager.getInstance().loadAchievements();

                sendNotif(Locale.get("command.reload.achievements"), client);
                break;

            case "pets":
                PetManager.getInstance().loadPetRaces();
                PetManager.getInstance().loadPetSpeech();
                PetManager.getInstance().loadTransformablePets();
                PetManager.getInstance().loadPetBreedPallets();

                PetCommandManager.getInstance().initialize();

                sendNotif(Locale.get("command.reload.pets"), client);
                break;

            case "crafting":
                ItemManager.getInstance().loadCraftingMachines();

                sendNotif(Locale.get("command.reload.crafting"), client);
                break;

            case "polls":
                PollManager.getInstance().initialize();

                if (PollManager.getInstance().roomHasPoll(client.getPlayer().getEntity().getRoom().getId())) {
                    Poll poll = PollManager.getInstance().getPollByRoomId(client.getPlayer().getEntity().getRoom().getId());

                    client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new InitializePollMessageComposer(poll.getPollId(), poll.getPollTitle(), poll.getThanksMessage()));
                }

                sendNotif(Locale.get("command.reload.polls"), client);
                break;
            case "bundles": {
                RoomBundleManager.getInstance().initialize();

                sendNotif(Locale.get("command.reload.bundles"), client);

                break;
            }

            case "emojis" : {
                EmojiGlobals.emojis = new ArrayList<Emoji>();
                RoomDao.getEmojis();
                sendNotif("Emojis actualizados correctamente", client);
            }
        }
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public String getPermission() {
        return "reload_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.reload.description");
    }
}

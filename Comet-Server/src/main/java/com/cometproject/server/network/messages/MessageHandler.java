package com.cometproject.server.network.messages;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.config.Configuration;
import com.cometproject.api.networking.messages.IMessageEventHandler;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.incoming.catalog.*;
import com.cometproject.server.network.messages.incoming.catalog.ads.CatalogPromotionGetRoomsMessageEvent;
import com.cometproject.server.network.messages.incoming.catalog.ads.PromoteRoomMessageEvent;
import com.cometproject.server.network.messages.incoming.catalog.ads.PromotionUpdateMessageEvent;
import com.cometproject.server.network.messages.incoming.catalog.club.GetClubPresentMessageEvent;
import com.cometproject.server.network.messages.incoming.catalog.club.GetPresentsPageMessageEvent;
import com.cometproject.server.network.messages.incoming.catalog.data.GetGiftWrappingConfigurationMessageEvent;
import com.cometproject.server.network.messages.incoming.catalog.groups.BuyGroupDialogMessageEvent;
import com.cometproject.server.network.messages.incoming.catalog.groups.BuyGroupMessageEvent;
import com.cometproject.server.network.messages.incoming.catalog.groups.GroupFurnitureCatalogMessageEvent;
import com.cometproject.server.network.messages.incoming.catalog.pets.PetRacesMessageEvent;
import com.cometproject.server.network.messages.incoming.catalog.pets.ValidatePetNameMessageEvent;
import com.cometproject.server.network.messages.incoming.crafting.*;
import com.cometproject.server.network.messages.incoming.gamecenter.*;
import com.cometproject.server.network.messages.incoming.gamecenter.snowwar.account.GetAccountGameStatusParser;
import com.cometproject.server.network.messages.incoming.gamecenter.snowwar.game.*;
import com.cometproject.server.network.messages.incoming.group.*;
import com.cometproject.server.network.messages.incoming.group.favourite.ClearFavouriteGroupMessageEvent;
import com.cometproject.server.network.messages.incoming.group.favourite.SetFavouriteGroupMessageEvent;
import com.cometproject.server.network.messages.incoming.group.forum.data.ForumDataMessageEvent;
import com.cometproject.server.network.messages.incoming.group.forum.data.GetForumsMessageEvent;
import com.cometproject.server.network.messages.incoming.group.forum.settings.SaveForumSettingsMessageEvent;
import com.cometproject.server.network.messages.incoming.group.forum.threads.*;
import com.cometproject.server.network.messages.incoming.group.settings.*;
import com.cometproject.server.network.messages.incoming.handshake.*;
import com.cometproject.server.network.messages.incoming.help.GetSanctionStatusEvent;
import com.cometproject.server.network.messages.incoming.help.HelpTicketMessageEvent;
import com.cometproject.server.network.messages.incoming.help.InitHelpToolMessageEvent;
import com.cometproject.server.network.messages.incoming.help.guides.*;
import com.cometproject.server.network.messages.incoming.landing.*;
import com.cometproject.server.network.messages.incoming.messenger.*;
import com.cometproject.server.network.messages.incoming.moderation.*;
import com.cometproject.server.network.messages.incoming.moderation.tickets.ModToolCloseIssueMessageEvent;
import com.cometproject.server.network.messages.incoming.moderation.tickets.ModToolPickTicketMessageEvent;
import com.cometproject.server.network.messages.incoming.moderation.tickets.ModToolReleaseIssueMessageEvent;
import com.cometproject.server.network.messages.incoming.moderation.tickets.ModToolTicketChatlogMessageEvent;
import com.cometproject.server.network.messages.incoming.music.SongDataMessageEvent;
import com.cometproject.server.network.messages.incoming.music.SongIdMessageEvent;
import com.cometproject.server.network.messages.incoming.music.playlist.PlaylistAddMessageEvent;
import com.cometproject.server.network.messages.incoming.music.playlist.PlaylistMessageEvent;
import com.cometproject.server.network.messages.incoming.music.playlist.PlaylistRemoveMessageEvent;
import com.cometproject.server.network.messages.incoming.navigator.*;
import com.cometproject.server.network.messages.incoming.navigator.updated.*;
import com.cometproject.server.network.messages.incoming.nuxs.ConfirmWelcomeGiftMessageEvent;
import com.cometproject.server.network.messages.incoming.nuxs.GetWelcomeGiftMessageEvent;
import com.cometproject.server.network.messages.incoming.nuxs.NewUserExperienceGiftOfferParserEvent;
import com.cometproject.server.network.messages.incoming.nuxs.VerifyEmailMessageEvent;
import com.cometproject.server.network.messages.incoming.performance.EventLogMessageEvent;
import com.cometproject.server.network.messages.incoming.performance.RequestLatencyTestMessageEvent;
import com.cometproject.server.network.messages.incoming.polls.GetInfobusPollsResultsMessageEvent;
import com.cometproject.server.network.messages.incoming.polls.GetPollMessageEvent;
import com.cometproject.server.network.messages.incoming.polls.SubmitPollAnswerMessageEvent;
import com.cometproject.server.network.messages.incoming.quests.CancelQuestMessageEvent;
import com.cometproject.server.network.messages.incoming.quests.NextQuestMessageEvent;
import com.cometproject.server.network.messages.incoming.quests.OpenQuestsMessageEvent;
import com.cometproject.server.network.messages.incoming.quests.StartQuestMessageEvent;
import com.cometproject.server.network.messages.incoming.room.access.AnswerDoorbellMessageEvent;
import com.cometproject.server.network.messages.incoming.room.access.LoadRoomByDoorBellMessageEvent;
import com.cometproject.server.network.messages.incoming.room.access.SpectateRoomMessageEvent;
import com.cometproject.server.network.messages.incoming.room.action.*;
import com.cometproject.server.network.messages.incoming.room.bots.BotConfigMessageEvent;
import com.cometproject.server.network.messages.incoming.room.bots.ModifyBotMessageEvent;
import com.cometproject.server.network.messages.incoming.room.bots.PlaceBotMessageEvent;
import com.cometproject.server.network.messages.incoming.room.bots.RemoveBotMessageEvent;
import com.cometproject.server.network.messages.incoming.room.engine.*;
import com.cometproject.server.network.messages.incoming.room.filter.EditWordFilterMessageEvent;
import com.cometproject.server.network.messages.incoming.room.filter.WordFilterListMessageEvent;
import com.cometproject.server.network.messages.incoming.room.floor.GetFloorPlanDoorMessageEvent;
import com.cometproject.server.network.messages.incoming.room.floor.GetTilesInUseMessageEvent;
import com.cometproject.server.network.messages.incoming.room.floor.SaveFloorMessageEvent;
import com.cometproject.server.network.messages.incoming.room.item.*;
import com.cometproject.server.network.messages.incoming.room.item.gifts.OpenGiftMessageEvent;
import com.cometproject.server.network.messages.incoming.room.item.lovelock.ConfirmLoveLockMessageEvent;
import com.cometproject.server.network.messages.incoming.room.item.mannequins.SaveMannequinFigureMessageEvent;
import com.cometproject.server.network.messages.incoming.room.item.mannequins.SaveMannequinMessageEvent;
import com.cometproject.server.network.messages.incoming.room.item.stickies.DeletePostItMessageEvent;
import com.cometproject.server.network.messages.incoming.room.item.stickies.OpenPostItMessageEvent;
import com.cometproject.server.network.messages.incoming.room.item.stickies.PlacePostItMessageEvent;
import com.cometproject.server.network.messages.incoming.room.item.stickies.SavePostItMessageEvent;
import com.cometproject.server.network.messages.incoming.room.item.wired.SaveWiredDataMessageEvent;
import com.cometproject.server.network.messages.incoming.room.item.wired.UpdateSnapshotsMessageEvent;
import com.cometproject.server.network.messages.incoming.room.moderation.*;
import com.cometproject.server.network.messages.incoming.room.pets.*;
import com.cometproject.server.network.messages.incoming.room.pets.horse.ApplyHorseEffectMessageEvent;
import com.cometproject.server.network.messages.incoming.room.pets.horse.ModifyWhoCanRideHorseMessageEvent;
import com.cometproject.server.network.messages.incoming.room.pets.horse.RemoveHorseSaddleMessageEvent;
import com.cometproject.server.network.messages.incoming.room.pets.horse.RideHorseMessageEvent;
import com.cometproject.server.network.messages.incoming.room.pets.monsterplants.MonsterPlantBreedMessageEvent;
import com.cometproject.server.network.messages.incoming.room.pets.monsterplants.MonsterPlantSetBreedableMessageEvent;
import com.cometproject.server.network.messages.incoming.room.settings.*;
import com.cometproject.server.network.messages.incoming.room.trading.*;
import com.cometproject.server.network.messages.incoming.sockets.AcceptableAlertMessageEvent;
import com.cometproject.server.network.messages.incoming.sockets.ClickPlayerMessageEvent;
import com.cometproject.server.network.messages.incoming.user.achievements.AchievementsListMessageEvent;
import com.cometproject.server.network.messages.incoming.user.camera.*;
import com.cometproject.server.network.messages.incoming.user.citizenship.OpenTalentTrackMessageEvent;
import com.cometproject.server.network.messages.incoming.user.club.ClubStatusMessageEvent;
import com.cometproject.server.network.messages.incoming.user.club.GetHCCenterInformationEvent;
import com.cometproject.server.network.messages.incoming.user.details.*;
import com.cometproject.server.network.messages.incoming.user.interactions.*;
import com.cometproject.server.network.messages.incoming.user.inventory.*;
import com.cometproject.server.network.messages.incoming.user.newyear.ResolutionSelectMessageEvent;
import com.cometproject.server.network.messages.incoming.user.profile.*;
import com.cometproject.server.network.messages.incoming.user.wardrobe.ChangeLooksMessageEvent;
import com.cometproject.server.network.messages.incoming.user.wardrobe.RedeemClothingMessageEvent;
import com.cometproject.server.network.messages.incoming.user.wardrobe.SaveWardrobeMessageEvent;
import com.cometproject.server.network.messages.incoming.user.wardrobe.WardrobeMessageEvent;
import com.cometproject.server.network.messages.incoming.user.youtube.LoadPlaylistMessageEvent;
import com.cometproject.server.network.messages.incoming.user.youtube.NextVideoMessageEvent;
import com.cometproject.server.network.messages.incoming.user.youtube.PlayVideoMessageEvent;
import com.cometproject.server.network.messages.outgoing.handshake.ConfirmUsernameMessageEvent;
import com.cometproject.server.network.messages.types.tasks.MessageEventTask;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.headers.Events;
import com.cometproject.server.protocol.messages.MessageEvent;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public final class MessageHandler {
    public static Logger LOGGER = LoggerFactory.getLogger(MessageHandler.class);

    private final Map<Short, Event> messages = Maps.newConcurrentMap();
    private final Map<Short, IMessageEventHandler> eventHandlers = Maps.newConcurrentMap();

    private final ExecutorService eventExecutor;
    private final boolean asyncEventExecution;

    public MessageHandler() {
        this.asyncEventExecution = Boolean.parseBoolean((String) Configuration.currentConfig().getOrDefault("comet.network.alternativePacketHandling.enabled", "false"));
//        this.eventExecutor = asyncEventExecution ? Executors.newFixedThreadPool(Integer.parseInt((String) Configuration.currentConfig().getOrDefault("comet.network.alternativePacketHandling.threads", "8"))) : null;

        if (this.asyncEventExecution) {
            switch ((String) Configuration.currentConfig().getOrDefault("comet.network.alternativePacketHandling.type", "threadpool")) {
                default:
                    LOGGER.info("Using fixed thread-pool event executor");
                    this.eventExecutor = Executors.newFixedThreadPool(32);
                    break;

                case "forkjoin":
                    LOGGER.info("Using fork-join event executor");
                    this.eventExecutor = new ForkJoinPool(Integer.parseInt((String) Configuration.currentConfig().getOrDefault("comet.network.alternativePacketHandling.coreSize", 16)), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
                    break;

            }
        } else {
            this.eventExecutor = null;
        }

        this.load();
    }

    public void load() {
        this.registerHandshake();
        this.registerModTool();
        this.registerHelpTool();
        this.registerMessenger();
        this.registerNavigator();
        this.registerUser();
        this.registerBots();
        this.registerRoom();
        this.registerRoomTrade();
        this.registerRoomModeration();
        this.registerRoomAccess();
        this.registerItems();
        this.registerCatalog();
        this.registerPets();
        this.registerLanding();
        this.registerGroups();
        this.registerGroupForums();
        this.registerQuests();
        this.registerPromotions();
        this.registerAchievements();
        this.registerPolls();
        this.registerMisc();
        this.registerMusic();
        this.registerCamera();
        this.registerGuideTool();
        this.registerGameCenter();
        this.registerSnowStorm();
        this.registerCrafting();

        LOGGER.info("Loaded " + this.getMessages().size() + " message events");
    }

    private void registerGameCenter() {
        this.getMessages().put(Events.GetGameListMessageEvent, new GetGameListMessageEvent());
        this.getMessages().put(Events.GetGameAchievementsMessageEvent, new GetGameAchievementsMessageEvent());
        this.getMessages().put(Events.GetWeeklyLeaderboardEvent, new GetWeeklyLeaderboardEvent());
        this.getMessages().put(Events.GetGameStatusMessageEvent, new GetGameStatusMessageEvent());
        this.getMessages().put(Events.GetGameLeaderboardsEvent, new GetGameLeaderboardsEvent());
        this.getMessages().put(Events.GetLastWeekLeaderboardEvent, new GetLastWeekLeaderboardEvent());
        this.getMessages().put(Events.GetGameAccountStatusMessageEvent, new GetGameAccountStatusMessageEvent());
        this.getMessages().put(Events.JoinGameQueueMessageEvent, new JoinGameQueueMessageEvent());
    }

    private void registerSnowStorm(){
        this.getMessages().put(Events.CheckGameDirectoryStatusParser, new CheckGameDirectoryStatusParser());
        this.getMessages().put(Events.LoadStageReadyParser, new LoadStageReadyParser());
        this.getMessages().put(Events.SetUserMoveTargetParser, new SetUserMoveTargetParser());
        this.getMessages().put(Events.ExitGameParser, new ExitGameParser());
        this.getMessages().put(Events.GameChatParser, new GameChatParser());
        this.getMessages().put(Events.ThrowSnowballAtHumanParser, new ThrowSnowballAtHumanParser());
        this.getMessages().put(Events.PlayAgainParser, new PlayAgainParser());
        this.getMessages().put(Events.SnowStormUserPickSnowballEvent, new MakeSnowballParser());
        this.getMessages().put(Events.GetLobbyStatus, new GetAccountGameStatusParser());
        this.getMessages().put(Events.ThrowSnowballAtPositionParser, new ThrowSnowballAtPositionParser());
    }

    private void registerMisc() {
        this.getMessages().put(Events.LatencyTestMessageEvent, new RequestLatencyTestMessageEvent());
        this.getMessages().put(Events.EventLogMessageEvent, new EventLogMessageEvent());
    }

    public void registerCrafting() {
        this.getMessages().put(Events.GetCraftingRecipesAvailableMessageEvent, new GetCraftingRecipesAvailableMessageEvent());
        this.getMessages().put(Events.CraftSecretMessageEvent, new CraftSecretMessageEvent());
        this.getMessages().put(Events.ViewCraftingRecipeMessageEvent, new ViewCraftingRecipeMessageEvent());
        this.getMessages().put(Events.ExecuteCraftingRecipeMessageEvent, new ExecuteCraftingRecipeMessageEvent());
        this.getMessages().put(Events.GetCraftingItemMessageEvent, new GetCraftingItemMessageEvent());
    }


    public void registerHandshake() {
        this.getMessages().put(Events.GetClientVersionMessageEvent, new CheckReleaseMessageEvent());
        this.getMessages().put(Events.InitCryptoMessageEvent, new InitCryptoMessageEvent());
        this.getMessages().put(Events.GenerateSecretKeyMessageEvent, new GenerateSecretKeyMessageEvent());
        this.getMessages().put(Events.SSOTicketMessageEvent, new SSOTicketMessageEvent());
        this.getMessages().put(Events.UniqueIDMessageEvent, new UniqueIdMessageEvent());
        this.getMessages().put(Events.ConfirmUsernameMessageEvent, new ConfirmUsernameMessageEvent());
    }

    public void registerModTool() {
        this.getMessages().put(Events.GetModeratorUserInfoMessageEvent, new ModToolUserInfoMessageEvent());
        this.getMessages().put(Events.GetModeratorUserChatlogMessageEvent, new ModToolUserChatlogMessageEvent());
        this.getMessages().put(Events.GetModeratorRoomChatlogMessageEvent, new ModToolRoomChatlogMessageEvent());
        this.getMessages().put(Events.ModerationBanMessageEvent, new ModToolBanUserMessageEvent());
        this.getMessages().put(Events.GetModeratorRoomInfoMessageEvent, new ModToolRoomInfoMessageEvent());
        this.getMessages().put(Events.GetModeratorUserRoomVisitsMessageEvent, new ModToolRoomVisitsMessageEvent());
        this.getMessages().put(Events.ModerationMsgMessageEvent, new ModToolUserAlertMessageEvent());
        this.getMessages().put(Events.ModerationCautionMessageEvent, new ModToolUserCautionMessageEvent());
        this.getMessages().put(Events.ModerationKickMessageEvent, new ModToolUserKickMessageEvent());
        this.getMessages().put(Events.ModeratorActionMessageEvent, new ModToolRoomAlertMessageEvent());
        this.getMessages().put(Events.ModerateRoomMessageEvent, new ModToolRoomActionMessageEvent());
        this.getMessages().put(Events.PickTicketMessageEvent, new ModToolPickTicketMessageEvent());
        this.getMessages().put(Events.GetModeratorTicketChatlogsMessageEvent, new ModToolTicketChatlogMessageEvent());
        this.getMessages().put(Events.CloseTicketMesageEvent, new ModToolCloseIssueMessageEvent());
        this.getMessages().put(Events.ReleaseTicketMessageEvent, new ModToolReleaseIssueMessageEvent());
        this.getMessages().put(Events.ModerationMuteMessageEvent, new ModerationMuteUserMessageEvent());
    }

    public void registerHelpTool() {
        this.getMessages().put(Events.OpenHelpToolMessageEvent, new InitHelpToolMessageEvent());
        this.getMessages().put(Events.SubmitNewTicketMessageEvent, new HelpTicketMessageEvent());
        this.getMessages().put(Events.GetSanctionStatusMessageEvent, new GetSanctionStatusEvent());
        this.getMessages().put(Events.GuideCloseHelpRequestMessageEvent, new GuideCloseHelpRequestMessageEvent());
        this.getMessages().put(Events.GuideUserTypingMessageEvent, new GuideUserTypingMessageEvent());
        this.getMessages().put(Events.GuideUserMessageMessageEvent, new GuideUserMessageMessageEvent());
        this.getMessages().put(Events.GuideInviteUserMessageEvent, new GuideRoomInviteMessageEvent());
        this.getMessages().put(Events.GuideVisitUserMessageEvent, new GuideFollowUserMessageEvent());
        this.getMessages().put(Events.GuideRecommendHelperMessageEvent, new GuideRecommendHelperMessageEvent());
        this.getMessages().put(Events.GuideCancelHelpRequestMessageEvent, new GuideCancelHelpRequestMessageEvent());
    }

    public void registerMessenger() {
        this.getMessages().put(Events.MessengerInitMessageEvent, new InitializeFriendListMessageEvent());
        this.getMessages().put(Events.SendMsgMessageEvent, new PrivateChatMessageEvent());
        this.getMessages().put(Events.RequestBuddyMessageEvent, new RequestFriendshipMessageEvent());
        this.getMessages().put(Events.AcceptBuddyMessageEvent, new AcceptFriendshipMessageEvent());
        this.getMessages().put(Events.HabboSearchMessageEvent, new SearchFriendsMessageEvent());
        this.getMessages().put(Events.FollowFriendMessageEvent, new FollowFriendMessageEvent());
        this.getMessages().put(Events.RemoveBuddyMessageEvent, new DeleteFriendsMessageEvent());
        this.getMessages().put(Events.SendRoomInviteMessageEvent, new InviteFriendsMessageEvent());
        this.getMessages().put(Events.DeclineBuddyMessageEvent, new DeclineFriendshipMessageEvent());
    }

    public void registerNavigator() {
        this.getMessages().put(Events.GetUserFlatCatsMessageEvent, new LoadCategoriesMessageEvent());
        this.getMessages().put(Events.InitializeNavigatorMessageEvent, new InitializeNewNavigatorMessageEvent());
        this.getMessages().put(Events.NavigatorSearchMessageEvent, new NewNavigatorSearchMessageEvent());
        this.getMessages().put(Events.CanCreateRoomMessageEvent, new CanCreateRoomMessageEvent());
        this.getMessages().put(Events.CreateFlatMessageEvent, new CreateRoomMessageEvent());
        this.getMessages().put(Events.GetNavigatorDisplaySettingsMessageEvent, new GetNavigatorDisplaySettingsMessageEvent());
        this.getMessages().put(Events.GetPromotableRoomsMessageEvent, new CatalogPromotionGetRoomsMessageEvent());
        this.getMessages().put(Events.StaffPickRoomMessageEvent, new AddToStaffPickedRoomsMessageEvent());
        this.getMessages().put(Events.ResizeNavigatorMessageEvent, new ResizeNavigatorMessageEvent());
        this.getMessages().put(Events.SaveNavigatorSearchMessageEvent, new SaveNavigatorSearchMessageEvent());
        this.getMessages().put(Events.DeleteNavigatorSavedSearchMessageEvent, new DeleteNavigatorSavedSearchMessageEvent());
        this.getMessages().put(Events.HabbletMessageEvent, new HabbletMessageEvent());
        this.getMessages().put(Events.AddFavouriteRoomMessageEvent, new ToggleFavouriteRoomMessageEvent());
        this.getMessages().put(Events.DeleteFavouriteRoomMessageEvent, new ToggleFavouriteRoomMessageEvent());
        this.getMessages().put(Events.NavigatorSaveViewModeMessageEvent, new NavigatorSaveViewModeMessageEvent());
    }

    public void registerUser() {
        this.getMessages().put(Events.GetExtendedProfileMessageEvent, new GetProfileMessageEvent());
        this.getMessages().put(Events.GetForumUserProfileMessageEvent, new GetProfileByUsernameMessageEvent());
        this.getMessages().put(Events.ScrGetUserInfoMessageEvent, new ClubStatusMessageEvent());
        this.getMessages().put(Events.GetHCCenterInformationEvent, new GetHCCenterInformationEvent());
        this.getMessages().put(Events.InfoRetrieveMessageEvent, new InfoRetrieveMessageEvent());
        this.getMessages().put(Events.ProcessNUXMessageEvent, new ProcessNUXMessageEvent());
        this.getMessages().put(Events.UpdateFigureDataMessageEvent, new ChangeLooksMessageEvent());
        this.getMessages().put(Events.RequestFurniInventoryMessageEvent, new OpenInventoryMessageEvent());
        this.getMessages().put(Events.LandingGetInventoryEvent, new OpenInventoryMessageEvent());
        this.getMessages().put(Events.GetBadgesMessageEvent, new BadgeInventoryMessageEvent());
        this.getMessages().put(Events.ChangeMottoMessageEvent, new ChangeMottoMessageEvent());
        this.getMessages().put(Events.GetRelationshipsMessageEvent, new GetRelationshipsMessageEvent());
        this.getMessages().put(Events.SetRelationshipMessageEvent, new SetRelationshipMessageEvent());
        this.getMessages().put(Events.SetActivatedBadgesMessageEvent, new WearBadgeMessageEvent());
        this.getMessages().put(Events.GetWardrobeMessageEvent, new WardrobeMessageEvent());
        this.getMessages().put(Events.SaveWardrobeOutfitMessageEvent, new SaveWardrobeMessageEvent());
        this.getMessages().put(Events.ChangeHomeRoomMessageEvent, new ChangeHomeRoomMessageEvent());
        this.getMessages().put(Events.SetSoundSettingsMessageEvent, new UpdateAudioSettingsMessageEvent());
        this.getMessages().put(Events.SetChatPreferenceMessageEvent, new UpdateChatStyleMessageEvent());
        this.getMessages().put(Events.SetMessengerInviteStatusMessageEvent, new IgnoreInvitationsMessageEvent());
        this.getMessages().put(Events.ChangeNameMessageEvent, new ChangeNameMessageEvent());
        this.getMessages().put(Events.CheckValidNameMessageEvent, new CheckValidNameMessageEvent());
        this.getMessages().put(Events.RedeemClothingMessageEvent, new RedeemClothingMessageEvent());
        this.getMessages().put(Events.EquipEffectMessageEvent, new EquipEffectMessageEvent());
        this.getMessages().put(Events.ResolutionSelectMessageEvent, new ResolutionSelectMessageEvent());
        this.getMessages().put(Events.SendAmbassadorAlertMessageEvent, new SendAmbassadorAlertMessageEvent());
        this.getMessages().put(Events.AddEntityToGroupWhisperMessageEvent, new AddEntityToGroupWhisperMessageEvent());
        this.getMessages().put(Events.OpenTalentTrackMessageEvent, new OpenTalentTrackMessageEvent());
        this.getMessages().put(Events.GetUserTagsMessageEvent, new GetUserTagsMessageEvent());
        this.getMessages().put(Events.GetWelcomeGiftMessageEvent, new GetWelcomeGiftMessageEvent());
        this.getMessages().put(Events.ConfirmWelcomeGiftMessageEvent, new ConfirmWelcomeGiftMessageEvent());
        this.getMessages().put(Events.SetRoomCameraFollowMessageEvent, new SetRoomCameraFollowMessageEvent());
        this.getMessages().put(Events.GetSoundSettingsMessageEvent, new GetSoundSettingsMessageEvent());
        this.getMessages().put(Events.SetRoomToolPreferenceMessageEvent, new SetRoomToolPreferenceMessageEvent());
        this.getMessages().put(Events.AcceptableAlertMessageEvent, new AcceptableAlertMessageEvent());
        this.getMessages().put(Events.ClickPlayerMessageEvent, new ClickPlayerMessageEvent());
    }

    public void registerBots() {
        this.getMessages().put(Events.GetBotInventoryMessageEvent, new BotInventoryMessageEvent());
        this.getMessages().put(Events.PlaceBotMessageEvent, new PlaceBotMessageEvent());
        this.getMessages().put(Events.CommandBotMessageEvent, new ModifyBotMessageEvent());
        this.getMessages().put(Events.PickUpBotMessageEvent, new RemoveBotMessageEvent());
        this.getMessages().put(Events.SaveBotSettingsMessageEvent, new BotConfigMessageEvent());
    }

    public void registerPets() {
        this.getMessages().put(Events.GetPetInventoryMessageEvent, new PetInventoryMessageEvent());
        this.getMessages().put(Events.PlacePetMessageEvent, new PlacePetMessageEvent());
        this.getMessages().put(Events.GetPetInformationMessageEvent, new PetInformationMessageEvent());
        this.getMessages().put(Events.PickUpPetMessageEvent, new RemovePetMessageEvent());
        this.getMessages().put(Events.RideHorseMessageEvent, new RideHorseMessageEvent());
        this.getMessages().put(Events.RespectPetMessageEvent, new ScratchPetMessageEvent());
        this.getMessages().put(Events.GetPetTrainingPanelMessageEvent, new GetPetTrainingPanelMessageEvent());
        this.getMessages().put(Events.ApplyHorseEffectMessageEvent, new ApplyHorseEffectMessageEvent());
        this.getMessages().put(Events.RemoveSaddleFromHorseMessageEvent, new RemoveHorseSaddleMessageEvent());
        this.getMessages().put(Events.ModifyWhoCanRideHorseMessageEvent, new ModifyWhoCanRideHorseMessageEvent());
        this.getMessages().put(Events.BreedPetsMessageEvent, new BreedPetsMessageEvent());
        this.getMessages().put(Events.OpenPetPackageMessageEvent, new OpenPetPackageMessageEvent());
        this.getMessages().put(Events.MonsterPlantBreedMessageEvent, new MonsterPlantBreedMessageEvent());
        this.getMessages().put(Events.MonsterPlantSetBreedableMessageEvent, new MonsterPlantSetBreedableMessageEvent());
    }

    public void registerRoom() {
        this.getMessages().put(Events.OpenFlatConnectionMessageEvent, new InitializeRoomMessageEvent());
        this.getMessages().put(Events.SpectateRoomMessageEvent, new SpectateRoomMessageEvent());
        this.getMessages().put(Events.GetGuestRoomMessageEvent, new FollowRoomInfoMessageEvent());
        this.getMessages().put(Events.GetRoomEntryDataMessageEvent, new AddUserToRoomMessageEvent());
        this.getMessages().put(Events.GoToHotelViewMessageEvent, new ExitRoomMessageEvent());
        this.getMessages().put(Events.ChatMessageEvent, new TalkMessageEvent());
        this.getMessages().put(Events.ShoutMessageEvent, new ShoutMessageEvent());
        this.getMessages().put(Events.WhisperMessageEvent, new WhisperMessageEvent());
        this.getMessages().put(Events.MoveAvatarMessageEvent, new WalkMessageEvent());
        this.getMessages().put(Events.ActionMessageEvent, new ApplyActionMessageEvent());
        this.getMessages().put(Events.ApplySignMessageEvent, new ApplySignMessageEvent());
        this.getMessages().put(Events.DanceMessageEvent, new ApplyDanceMessageEvent());
        this.getMessages().put(Events.GetRoomSettingsMessageEvent, new GetRoomSettingsDataMessageEvent());
        this.getMessages().put(Events.SaveRoomSettingsMessageEvent, new SaveRoomDataMessageEvent());
        this.getMessages().put(Events.RespectUserMessageEvent, new RespectUserMessageEvent());
        this.getMessages().put(Events.StartTypingMessageEvent, new StartTypingMessageEvent());
        this.getMessages().put(Events.CancelTypingMessageEvent, new StopTypingMessageEvent());
        this.getMessages().put(Events.LookToMessageEvent, new LookToMessageEvent());
        this.getMessages().put(Events.GetSelectedBadgesMessageEvent, new UserBadgesMessageEvent());
        this.getMessages().put(Events.ApplyDecorationMessageEvent, new ApplyDecorationMessageEvent());
        this.getMessages().put(Events.DropHandItemMessageEvent, new DropHandItemMessageEvent());
        this.getMessages().put(Events.DeleteRoomMessageEvent, new DeleteRoomMessageEvent());
        this.getMessages().put(Events.ToggleMuteToolMessageEvent, new MuteRoomMessageEvent());
        this.getMessages().put(Events.GiveRoomScoreMessageEvent, new RateRoomMessageEvent());
        this.getMessages().put(Events.GiveHandItemMessageEvent, new GiveHandItemMessageEvent());
        this.getMessages().put(Events.UpdateFloorPropertiesMessageEvent, new SaveFloorMessageEvent());
        this.getMessages().put(Events.InitializeFloorPlanSessionMessageEvent, new GetTilesInUseMessageEvent());
        this.getMessages().put(Events.InitializeDoorMessageEvent, new GetFloorPlanDoorMessageEvent());
        this.getMessages().put(Events.IgnoreUserMessageEvent, new IgnoreUserMessageEvent());
        this.getMessages().put(Events.UnIgnoreUserMessageEvent, new UnignoreUserMessageEvent());
        this.getMessages().put(Events.RemoveMyRightsMessageEvent, new RemoveOwnRightsMessageEvent());
        this.getMessages().put(Events.SitMessageEvent, new SitMessageEvent());
        this.getMessages().put(Events.GetFurnitureAliasesMessageEvent, new GetFurnitureAliasesMessageEvent());
        this.getMessages().put(Events.AnswerConfirmableAlertMessageEvent, new AnswerConfirmableAlertMessageEvent());
    }

    public void registerRoomTrade() {
        this.getMessages().put(Events.InitTradeMessageEvent, new BeginTradeMessageEvent());
        this.getMessages().put(Events.TradingOfferItemMessageEvent, new SendOfferMessageEvent());
        this.getMessages().put(Events.TradingOfferItemsMessageEvent, new TradingOfferItemsMessageEvent());
        this.getMessages().put(Events.TradingRemoveItemMessageEvent, new CancelOfferMessageEvent());
        this.getMessages().put(Events.TradingAcceptMessageEvent, new AcceptTradeMessageEvent());
        this.getMessages().put(Events.TradingModifyMessageEvent, new UnacceptTradeMessageEvent());
        this.getMessages().put(Events.TradingCancelMessageEvent, new CancelTradeMessageEvent());
        this.getMessages().put(Events.TradingConfirmMessageEvent, new ConfirmTradeMessageEvent());
        this.getMessages().put(Events.TradingCancelConfirmMessageEvent, new CancelConfirmTradeMessageEvent());
    }

    public void registerRoomModeration() {
        this.getMessages().put(Events.KickUserMessageEvent, new KickUserMessageEvent());
        this.getMessages().put(Events.BanUserMessageEvent, new BanUserMessageEvent());
        this.getMessages().put(Events.AssignRightsMessageEvent, new GiveRightsMessageEvent());
        this.getMessages().put(Events.RemoveRightsMessageEvent, new RemoveRightsMessageEvent());
        this.getMessages().put(Events.RemoveAllRightsMessageEvent, new RemoveAllRightsMessageEvent());
        this.getMessages().put(Events.GetRoomBannedUsersMessageEvent, new GetBannedUsersMessageEvent());
        this.getMessages().put(Events.UnbanUserFromRoomMessageEvent, new RoomUnbanUserMessageEvent());
        this.getMessages().put(Events.MuteUserMessageEvent, new MutePlayerMessageEvent());
        this.getMessages().put(Events.GetRoomRightsMessageEvent, new UsersWithRightsMessageEvent());
        this.getMessages().put(Events.GetRoomFilterListMessageEvent, new WordFilterListMessageEvent());
        this.getMessages().put(Events.ModifyRoomFilterListMessageEvent, new EditWordFilterMessageEvent());
    }

    public void registerRoomAccess() {
        this.getMessages().put(Events.LetUserInMessageEvent, new AnswerDoorbellMessageEvent());
        this.getMessages().put(Events.GoToFlatMessageEvent, new LoadRoomByDoorBellMessageEvent());
    }

    public void registerItems() {
        this.getMessages().put(Events.PlaceObjectMessageEvent, new PlaceItemMessageEvent());
        this.getMessages().put(Events.MoveObjectMessageEvent, new MoveFloorItemMessageEvent());
        this.getMessages().put(Events.MoveWallItemMessageEvent, new ChangeWallItemPositionMessageEvent());
        this.getMessages().put(Events.PickupObjectMessageEvent, new PickUpItemMessageEvent());
        this.getMessages().put(Events.UseFurnitureMessageEvent, new ChangeFloorItemStateMessageEvent());
        this.getMessages().put(Events.UseOneWayGateMessageEvent, new ChangeFloorItemStateMessageEvent());
        this.getMessages().put(Events.DiceOffMessageEvent, new OpenDiceMessageEvent());
        this.getMessages().put(Events.ThrowDiceMessageEvent, new RunDiceMessageEvent());
        this.getMessages().put(Events.InspectFurnitureMessageEvent, new InspectFurnitureMessageEvent());

        this.getMessages().put(Events.SaveWiredEffectConfigMessageEvent, new SaveWiredDataMessageEvent());
        this.getMessages().put(Events.SaveWiredConditionConfigMessageEvent, new SaveWiredDataMessageEvent());
        this.getMessages().put(Events.SaveWiredTriggerConfigMessageEvent, new SaveWiredDataMessageEvent());
        this.getMessages().put(Events.UpdateSnapshotsMessageEvent, new UpdateSnapshotsMessageEvent());

        this.getMessages().put(Events.CreditFurniRedeemMessageEvent, new ExchangeItemMessageEvent());
        this.getMessages().put(Events.UseWallItemMessageEvent, new UseWallItemMessageEvent());
        this.getMessages().put(Events.UseHabboWheelMessageEvent, new UseWallItemMessageEvent());
        this.getMessages().put(Events.SetMannequinNameMessageEvent, new SaveMannequinMessageEvent());
        this.getMessages().put(Events.SetMannequinFigureMessageEvent, new SaveMannequinFigureMessageEvent());
        this.getMessages().put(Events.SetTonerMessageEvent, new SaveTonerMessageEvent());
        this.getMessages().put(Events.SetObjectDataMessageEvent, new SaveBrandingMessageEvent());
        this.getMessages().put(Events.OpenGiftMessageEvent, new OpenGiftMessageEvent());
        this.getMessages().put(Events.GetMoodlightConfigMessageEvent, new UseMoodlightMessageEvent());
        this.getMessages().put(Events.ToggleMoodlightMessageEvent, new ToggleMoodlightMessageEvent());
        this.getMessages().put(Events.RoomDimmerSavePresetMessageEvent, new UpdateMoodlightMessageEvent());
        this.getMessages().put(Events.SetCustomStackingHeightMessageEvent, new SaveStackToolMessageEvent());
        this.getMessages().put(Events.AddStickyNoteMessageEvent, new PlacePostItMessageEvent());
        this.getMessages().put(Events.GetStickyNoteMessageEvent, new OpenPostItMessageEvent());
        this.getMessages().put(Events.UpdateStickyNoteMessageEvent, new SavePostItMessageEvent());
        this.getMessages().put(Events.DeleteStickyNoteMessageEvent, new DeletePostItMessageEvent());
        this.getMessages().put(Events.GetYouTubeTelevisionMessageEvent, new LoadPlaylistMessageEvent());
        this.getMessages().put(Events.ToggleYouTubeVideoMessageEvent, new PlayVideoMessageEvent());
        this.getMessages().put(Events.YouTubeGetNextVideo, new NextVideoMessageEvent());
        this.getMessages().put(Events.ConfirmLoveLockMessageEvent, new ConfirmLoveLockMessageEvent());
        this.getMessages().put(Events.SaveFootballGateMessageEvent, new SaveFootballGateMessageEvent());
        this.getMessages().put(Events.NewUserExperienceGiftOfferParserEvent, new NewUserExperienceGiftOfferParserEvent());
        this.getMessages().put(Events.VerifyEmailMessageEvent, new VerifyEmailMessageEvent());
    }

    public void registerPromotions() {
        this.getMessages().put(Events.PurchaseRoomPromotionMessageEvent, new PromoteRoomMessageEvent());
        this.getMessages().put(Events.EditRoomPromotionMessageEvent, new PromotionUpdateMessageEvent());
    }

    //
    public void registerCatalog() {
        this.getMessages().put(Events.GetCatalogIndexMessageEvent, new GetCataIndexMessageEvent());
        this.getMessages().put(Events.GetCatalogPageMessageEvent, new GetCataPageMessageEvent());
        this.getMessages().put(Events.PurchaseFromCatalogMessageEvent, new PurchaseItemMessageEvent());
        this.getMessages().put(Events.GetGiftWrappingConfigurationMessageEvent, new GetGiftWrappingConfigurationMessageEvent());
        this.getMessages().put(Events.GetGroupCreationWindowMessageEvent, new BuyGroupDialogMessageEvent());
        this.getMessages().put(Events.PurchaseGroupMessageEvent, new BuyGroupMessageEvent());
        this.getMessages().put(Events.GetSellablePetBreedsMessageEvent, new PetRacesMessageEvent());
        this.getMessages().put(Events.ValidatePetNameMessageEvent, new ValidatePetNameMessageEvent());
        this.getMessages().put(Events.PurchaseFromCatalogAsGiftMessageEvent, new PurchaseGiftMessageEvent());
        this.getMessages().put(Events.GetGroupFurniConfigMessageEvent, new GroupFurnitureCatalogMessageEvent());
        this.getMessages().put(Events.GetCatalogOfferMessageEvent, new GetCatalogOfferMessageEvent());
        this.getMessages().put(Events.RedeemVoucherMessageEvent, new RedeemVoucherMessageEvent());
        this.getMessages().put(Events.GetClubPresentMessageEvent, new GetClubPresentMessageEvent());
        this.getMessages().put(Events.GetPresentsPageMessageEvent, new GetPresentsPageMessageEvent());
        this.getMessages().put(Events.RequestMarketplaceConfigMessageEvent, new RequestMarketplaceConfigMessageEvent());
        this.getMessages().put(Events.PurchaseFireworksMessageEvent, new PurchaseFireworksMessageEvent());
        this.getMessages().put(Events.InspectFurnitureMessageEvent, new RequestCatalogModeMessageEvent());
        this.getMessages().put(Events.GetHCCenterInformationEvent, new GetClubPresentMessageEvent());
    }

    public void registerLanding() {
        this.getMessages().put(Events.GetPromoArticlesMessageEvent, new RefreshPromoArticlesMessageEvent());
        this.getMessages().put(Events.RefreshCampaignMessageEvent, new LandingLoadWidgetMessageEvent());
        this.getMessages().put(Events.LTDCountdownMessageEvent, new LTDCountdownMessageEvent());
        this.getMessages().put(Events.CheckCalendarDayMessageEvent, new CheckCalendarDayMessageEvent());
        this.getMessages().put(Events.ConcurrentUsersCompetitionStatusMessageEvent, new ConcurrentUsersCompetitionStatusMessageEvent());
        this.getMessages().put(Events.ConcurrentUsersCompetitionClaimReward, new ConcurrentUsersCompetitionClaimReward());
        this.getMessages().put(Events.BuyLotteryMessageEvent, new BuyLotteryMessageEvent());
    }

    public void registerGroups() {
        this.getMessages().put(Events.GetGroupInfoMessageEvent, new GroupInformationMessageEvent());
        this.getMessages().put(Events.GetGroupMembersMessageEvent, new GroupMembersMessageEvent());
        this.getMessages().put(Events.ManageGroupMessageEvent, new ManageGroupMessageEvent());
        this.getMessages().put(Events.RemoveGroupMemberMessageEvent, new RevokeMembershipMessageEvent());
        this.getMessages().put(Events.JoinGroupMessageEvent, new JoinGroupMessageEvent());
        this.getMessages().put(Events.UpdateGroupIdentityMessageEvent, new ModifyGroupTitleMessageEvent());
        this.getMessages().put(Events.TakeAdminRightsMessageEvent, new RevokeAdminMessageEvent());
        this.getMessages().put(Events.GiveAdminRightsMessageEvent, new GiveGroupAdminMessageEvent());
        this.getMessages().put(Events.UpdateGroupSettingsMessageEvent, new ModifyGroupSettingsMessageEvent());
        this.getMessages().put(Events.AcceptGroupMembershipMessageEvent, new AcceptMembershipMessageEvent());
        this.getMessages().put(Events.UpdateGroupBadgeMessageEvent, new ModifyGroupBadgeMessageEvent());
        this.getMessages().put(Events.SetGroupFavouriteMessageEvent, new SetFavouriteGroupMessageEvent());
        this.getMessages().put(Events.GetGroupFurniSettingsMessageEvent, new GroupFurnitureWidgetMessageEvent());
        this.getMessages().put(Events.UpdateGroupColoursMessageEvent, new GroupUpdateColoursMessageEvent());
        this.getMessages().put(Events.DeclineGroupMembershipMessageEvent, new DeclineMembershipMessageEvent());
        this.getMessages().put(Events.RemoveGroupFavouriteMessageEvent, new ClearFavouriteGroupMessageEvent());
        this.getMessages().put(Events.DeleteGroupMessageEvent, new DeleteGroupMessageEvent());
        //this.getMessages().put(Events.GroupConfirmRemoveMemberMessageEvent, new GroupConfirmRemoveMemberMessageEvent());
        this.getMessages().put(Events.RequestGuildPartsMessageEvent, new RequestGuildPartsMessageEvent());
    }

    //
    public void registerGroupForums() {
        this.getMessages().put(Events.GetForumStatsMessageEvent, new ForumDataMessageEvent());
        this.getMessages().put(Events.UpdateForumSettingsMessageEvent, new SaveForumSettingsMessageEvent());
        this.getMessages().put(Events.GetThreadsListDataMessageEvent, new ForumThreadsMessageEvent());
        this.getMessages().put(Events.PostGroupContentMessageEvent, new PostMessageMessageEvent());
        this.getMessages().put(Events.GetThreadDataMessageEvent, new ViewThreadMessageEvent());
        this.getMessages().put(Events.UpdateThreadMessageEvent, new UpdateThreadMessageEvent());
        this.getMessages().put(Events.GetForumsListDataMessageEvent, new GetForumsMessageEvent());
        this.getMessages().put(Events.DeleteGroupThreadMessageEvent, new HideGroupForumPostMessageEvent());
        this.getMessages().put(Events.DeleteGroupReplyMessageEvent, new HideGroupForumPostMessageEvent());
    }

    public void registerQuests() {
        this.getMessages().put(Events.GetQuestListMessageEvent, new OpenQuestsMessageEvent());
        this.getMessages().put(Events.GetQuestListMessageEvent2, new OpenQuestsMessageEvent());
        this.getMessages().put(Events.StartQuestMessageEvent, new StartQuestMessageEvent());
        this.getMessages().put(Events.CancelQuestMessageEvent, new CancelQuestMessageEvent());
        this.getMessages().put(Events.StartNextQuestMessageEvent, new NextQuestMessageEvent());
    }


    public void registerMusic() {
        this.getMessages().put(Events.SongInventoryMessageEvent, new SongInventoryMessageEvent());
        this.getMessages().put(Events.SongIdMessageEvent, new SongIdMessageEvent());
        this.getMessages().put(Events.SongDataMessageEvent, new SongDataMessageEvent());
        this.getMessages().put(Events.PlaylistAddMessageEvent, new PlaylistAddMessageEvent());
        this.getMessages().put(Events.PlaylistRemoveMessageEvent, new PlaylistRemoveMessageEvent());
        this.getMessages().put(Events.PlaylistMessageEvent, new PlaylistMessageEvent());
    }

    public void registerPolls() {
        this.getMessages().put(Events.GetPollMessageEvent, new GetPollMessageEvent());
        this.getMessages().put(Events.SubmitPollAnswerMessageEvent, new SubmitPollAnswerMessageEvent());
        this.getMessages().put(Events.GetInfobusPollsResultsMessageEvent, new GetInfobusPollsResultsMessageEvent());
    }

    public void registerAchievements() {
        this.getMessages().put(Events.GetAchievementsMessageEvent, new AchievementsListMessageEvent());
    }

    private void registerCamera() {
        this.getMessages().put(Events.ThumbnailMessageEvent, new ThumbnailMessageEvent());
        this.getMessages().put(Events.RenderRoomMessageEvent, new RenderRoomMessageEvent());
        this.getMessages().put(Events.PurchasePhotoMessageEvent, new PurchasePhotoMessageEvent());
        this.getMessages().put(Events.PhotoPricingMessageEvent, new PhotoPricingMessageEvent());
        this.getMessages().put(Events.SharePhotoMessageEvent, new SharePhotoMessageEvent());
    }

    public void registerGuideTool() {
        this.getMessages().put(Events.RequestGuideToolMessageEvent, new OpenGuideToolMessageEvent());
        this.getMessages().put(Events.RequestGuideAssistanceMessageEvent, new RequestGuideAssistanceMessageEvent());
        this.getMessages().put(Events.GuideHandleHelpRequestMessageEvent, new GuideHandleHelpRequestMessageEvent());
    }

    public void handle(MessageEvent message, Session client) {
        final Short header = message.getId();

        if (Comet.isDebugging) {
            LOGGER.debug(message.toString());
        }

        if (!Comet.isRunning)
            return;

        if (this.getMessages().containsKey(header)) {
            try {
                final Event event = this.getMessages().get(header);

                if (event != null) {
                    if (this.asyncEventExecution) {
                        this.eventExecutor.submit(new MessageEventTask(event, client, message));
                    } else {
                        final long start = System.currentTimeMillis();
                        LOGGER.debug("Started packet process for packet: [" + event.getClass().getSimpleName() + "][" + header + "]");

                        event.handle(client, message);

                        long timeTakenSinceCreation = ((System.currentTimeMillis() - start));

                        // If the packet took more than 100ms to be handled, red flag!
                        if (timeTakenSinceCreation >= 100) {
                            if (client.getPlayer() != null && client.getPlayer().getData() != null)
                                LOGGER.trace("[" + event.getClass().getSimpleName() + "][" + message.getId() + "][" + client.getPlayer().getId() + "][" + client.getPlayer().getData().getUsername() + "] Packet took " + timeTakenSinceCreation + "ms to execute");
                            else
                                LOGGER.trace("[" + event.getClass().getSimpleName() + "][" + message.getId() + "] Packet took " + timeTakenSinceCreation + "ms to execute");
                        }

                        if(CometSettings.console_debugging) {
                            //System.out.println("Receive: [" + header + "][" + event.getClass().getSimpleName() + "][" + message.toString() + "]");
                            client.getLogger().info("Receive: [" + header + "][" + event.getClass().getSimpleName() + "][" + message.toString() + "]");
                        }

                        LOGGER.debug("Finished packet process for packet: [" + event.getClass().getSimpleName() + "][" + header + "] in " + ((System.currentTimeMillis() - start)) + "ms");
                    }
                }
            } catch (Exception e) {
                if (client.getLogger() != null)
                    client.getLogger().error("Error while handling event: " + this.getMessages().get(header).getClass().getSimpleName(), e);
                else
                    LOGGER.error("Error while handling event: " + this.getMessages().get(header).getClass().getSimpleName(), e);
            }
        } else if (Comet.isDebugging) {
            LOGGER.debug("Unhandled message: " + Events.valueOfId(header) + " / " + header);
        }
    }

    public Map<Short, Event> getMessages() {
        return this.messages;
    }
}

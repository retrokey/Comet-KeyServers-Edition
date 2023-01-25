package com.cometproject.server.protocol.headers;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


public class Composers {
    // Handshake
    public static final short InitCryptoMessageComposer = 1347;// 3523
    public static final short UniqueMachineIDMessageComposer = 1488; // 602
    public static final short AuthenticationOKMessageComposer = 2491;// 3054
    public static final short UserRightsMessageComposer = 411;// 1081
    public static final short AvailabilityStatusMessageComposer = 2033;// 1769
    public static final short SoundSettingsMessageComposer = 513;// 1001
    public static final short AvatarEffectsMessageComposer = 340;// 899
    public static final short NavigatorSettingsMessageComposer = 2875;// 1776
    public static final short FavouritesMessageComposer = 151;// 2753
    public static final short CfhTopicsInitMessageComposer = 325;// 2333
    public static final short SecretKeyMessageComposer = 3885;// 3465
    public static final short LatencyResponseMessageComposer = 10;// 2757

    // Player
    public static final short UserObjectMessageComposer = 2725; // 1513
    public static final short BuildersClubMembershipMessageComposer = 1452; // 2286
    public static final short PerkAllowancesMessageComposer = 2586; // 3189
    public static final short FigureSetIdsMessageComposer = 1450; // 745
    public static final short ProfileInformationMessageComposer = 3898;// 1897
    public static final short ScrSendUserInfoMessageComposer = 954;// 3459
    public static final short SubscriptionCenterInfoMessageComposer = 3277;// 3148;
    public static final short StopAvatarEffectMessageComposer = 2228;// 449
    public static final short ClubDataMessageComposer = 2405;// 449
    public static final short TalentTrackMessageComposer = 3406;//
    public static final short TalentTrackLevelUpdateMessageComposer = 638;//

    // Mystery Box
    public static final short MisteryBoxOpenMessageComposer = 3201;// 3480
    public static final short MisteryBoxCloseMessageComposer = 596;// 2755
    public static final short MisteryBoxRewardMessageComposer = 3712;// 2185

    // Inventory
    public static final short BotInventoryMessageComposer = 3086;// 3095
    public static final short FurniListUpdateMessageComposer = 3151;// 1604
    public static final short PetInventoryMessageComposer = 3522;// 3808
    public static final short BadgesMessageComposer = 717; // 2969
    public static final short BadgeDefinitionsMessageComposer = 2501; // 1924
    public static final short FurniListRemoveMessageComposer = 159;// 1748
    public static final short FurniListNotificationMessageComposer = 2103;// 1310
    public static final short FurniListMessageComposer = 994;// 1669


    // Guide Tools TODO: Missing 1 composer regarding RoomGuideID.
    public static final short GuideToolsMessageComposer = 1548; // 2857
    public static final short GuideSessionAttachedMessageComposer = 1591;// 2485
    public static final short GuideSessionDetachedMessageComposer = 138;// 1443
    public static final short GuideSessionStartedMessageComposer = 3209;// 1219
    public static final short GuideSessionEndedMessageComposer = 1456;// 91
    public static final short GuideSessionErrorMessageComposer = 673;// 3550
    public static final short GuideSessionMessageMessageComposer = 841;// 3845
    public static final short GuideSessionInvitedToGuideRoomMessageComposer = 219;// 3149
    public static final short GuideSessionPartnerIsTypingMessageComposer = 1016;// 3101
    public static final short RoomQueueStatusMessageComposer = 2208;

    // Alerts
    public static final short MOTDNotificationMessageComposer = 2035;// 408
    public static final short RoomNotificationMessageComposer = 1992;// 3531
    public static final short BroadcastMessageAlertMessageComposer = 3801;// 82
    public static final short WiredAlertMessageComposer = 156;// 82
    public static final short ConfirmableAlertMessageComposer = 5213;// 82

    // Achievements & Quests
    public static final short VIPQuestPromotionMessageComposer = 2278;
    public static final short AchievementsMessageComposer = 305;// 2028
    public static final short AchievementProgressedMessageComposer = 2107; // 2167
    public static final short AchievementScoreMessageComposer = 1968;// 896
    public static final short AchievementUnlockedMessageComposer = 806;// 684
    public static final short DailyQuestMessageComposer = 1878;// 1485
    public static final short QuestListMessageComposer = 3625;// 2566
    public static final short QuestStartedMessageComposer = 230;// 325
    public static final short QuestAbortedMessageComposer = 3027;// 2671
    public static final short QuestCompletedMessageComposer = 949;// 2999

    // Navigator
    public static final short NavigatorSearchResultSetMessageComposer = 2690; // 3984
    public static final short NavigatorCollapsedCategoriesMessageComposer = 1543; // 1146
    public static final short NavigatorFlatCatsMessageComposer = 3244;// 3851
    public static final short NavigatorPreferencesMessageComposer = 518;// 2123
    public static final short NavigatorLiftedRoomsMessageComposer = 3104;// 3709
    public static final short NavigatorMetaDataParserMessageComposer = 3052;// 2631
    public static final short NavigatorSavedSearchesMessageComposer = 3984;// 2853

    // Messenger
    public static final short MessengerInitMessageComposer = 1605; // 913
    public static final short RoomInviteMessageComposer = 3870;// 1378
    public static final short FriendNotificationMessageComposer = 3082; // 1737
    public static final short BuddyListMessageComposer = 3130;// 2891
    public static final short FriendListUpdateMessageComposer = 2800;// 3872
    public static final short BuddyRequestsMessageComposer = 280;// 1151
    public static final short NewBuddyRequestMessageComposer = 2219;// 2311
    public static final short NewConsoleMessageMessageComposer = 1587;// 2606
    public static final short HabboSearchResultMessageComposer = 973;// 3766
    public static final short MessengerErrorMessageComposer = 3359;// 193

    // Wallet
    public static final short ActivityPointsMessageComposer = 2018;// 3304
    public static final short HabboActivityPointNotificationMessageComposer = 2275;// 2474
    public static final short CreditBalanceMessageComposer = 3475; // 1556
    public static final short ClubPresentsNotificationMessageComposer = 2188; // 1556
    public static final short ClubPresentRedeemedMessageComposer = 659; // 1556

    // Rooms
    public static final short GetGuestRoomResultMessageComposer = 687;// 1826
    public static final short PopularRoomTagsResultMessageComposer = 2012;// 2547
    public static final short MoodlightConfigMessageComposer = 2710;// 2266
    public static final short OpenConnectionMessageComposer = 758; // 3450
    public static final short RoomReadyMessageComposer = 2031;// 3334
    public static final short RoomPropertyMessageComposer = 2454;// 424
    public static final short YouAreOwnerMessageComposer = 339;// 2539
    public static final short YouAreControllerMessageComposer = 780;// 3668
    public static final short RoomRatingMessageComposer = 482;// 3267
    public static final short HeightMapMessageComposer = 2753;// 1010
    public static final short FloorHeightMapMessageComposer = 1301;// 3841
    public static final short RoomEventMessageComposer = 1840;// 1488
    public static final short PhotoPriceMessageComposer = 3878;// 953
    public static final short RoomEntryInfoMessageComposer = 749;// 3383
    public static final short RoomVisualizationSettingsMessageComposer = 3547;// 2988
    public static final short RoomSettingsDataMessageComposer = 1498;// 3075
    public static final short RoomForwardMessageComposer = 160;// 319
    public static final short RoomRightsListMessageComposer = 1284;// 3321
    public static final short RoomInfoUpdatedMessageComposer = 3297;// 3966
    public static final short GetRoomBannedUsersMessageComposer = 1869;// 2712
    public static final short RoomSettingsSavedMessageComposer = 948;// 539
    public static final short FloodControlMessageComposer = 566;// 3603
    public static final short FlatControllerAddedMessageComposer = 2088;// 3622
    public static final short FlatCreatedMessageComposer = 1304;// 3230
    public static final short FlatAccessibleMessageComposer = 3783;// 3140
    public static final short FloorPlanSendDoorMessageComposer = 1664;// 2201
    public static final short FloorPlanFloorMapMessageComposer = 3990;// 3542
    public static final short FlatControllerRemovedMessageComposer = 1327;// 257
    public static final short UserFlatCatsMessageComposer = 1562;// 2986
    public static final short FlatAccessDeniedMessageComposer = 878;// 448
    public static final short DoorbellMessageComposer = 2309;// 1099
    public static final short RoomAccessErrorMessageComposer = 899;// 1945
    public static final short UnbanUserFromRoomMessageComposer = 3429;// 1784
    public static final short CanCreateRoomMessageComposer = 378;// 277
    public static final short EnforceRoomZoomMessageComposer = 5211;// 277
    public static final short GetRoomFilterListMessageComposer = 2937;// 1798

    // Room Entities
    public static final short UserRemoveMessageComposer = 2661;// 2848
    public static final short SleepMessageComposer = 1797;// 157
    public static final short UserUpdateMessageComposer = 1640;// 2694
    public static final short UsersMessageComposer = 374;// 666 [ was 374 originally ]
    public static final short HabboUserBadgesMessageComposer = 1087;// 54
    public static final short UserTagsMessageComposer = 1255;// 2212
    public static final short GetRelationshipsMessageComposer = 2016;// 3068
    public static final short UserChangeMessageComposer = 3920;// 2098
    public static final short AvatarEffectMessageComposer = 1167;// 2398
    public static final short ActionMessageComposer = 1631;// 1536
    public static final short AvatarAspectUpdateMessageComposer = 2429;// 2786
    public static final short UserTypingMessageComposer = 1717;// 3293
    public static final short ChatMessageComposer = 1446;// 2174
    public static final short ShoutMessageComposer = 1036;// 3944
    public static final short WhisperMessageComposer = 2704;// 2810
    public static final short OpenBotActionMessageComposer = 1618;// 1469
    public static final short CheckPetNameMessageComposer = 1503;// 3920
    public static final short RespectPetNotificationMessageComposer = 2788;// 3856
    public static final short CarryObjectMessageComposer = 1474;// 3112
    public static final short UpdateFreezeLivesMessageComposer = 2324;// 2535 STRANGE AF
    public static final short RespectNotificationMessageComposer = 2815;// 3489
    public static final short IgnoreStatusMessageComposer = 207;// 3919
    public static final short DanceMessageComposer = 2233;// 1276

    // Room Pet Entities
    public static final short PetTrainingPanelMessageComposer = 1164;// 720
    public static final short PetInformationMessageComposer = 2901;// 3345
    public static final short PetHorseFigureInformationMessageComposer = 1924;// 3937
    public static final short SellablePetBreedsMessageComposer = 3331;// 569
    public static final short AddExperiencePointsMessageComposer = 2156;// 1117

    // Room Items
    public static final short ObjectsMessageComposer = 1778;// 1264
    public static final short ItemsMessageComposer = 1369;// 2649
    public static final short ObjectAddMessageComposer = 1534;// 3340
    public static final short ItemAddMessageComposer = 2187;// 366
    public static final short ObjectUpdateMessageComposer = 3776;// 1125
    public static final short ItemUpdateMessageComposer = 2009;// 1481
    public static final short UpdateStackMapMessageComposer = 558;// 2730
    public static final short ObjectRemoveMessageComposer = 2703;// 2039
    public static final short GroupFurniSettingsMessageComposer = 3293;// 1777
    public static final short FurnitureAliasesMessageComposer = 1723;// 316
    public static final short WiredRewardMessageComposer = 178;// 2960
    public static final short WiredTriggerConfigMessageComposer = 383;// 3478
    public static final short WiredConditionConfigMessageComposer = 1108;// 1810
    public static final short WiredSaveConfigMessageComposer = 1155;// 1991
    public static final short WiredEffectConfigMessageComposer = 1434;// 2726
    public static final short SlideObjectBundleMessageComposer = 3207;// 352
    public static final short OpenGiftMessageComposer = 56;// 3980
    public static final short StickyNoteMessageComposer = 2202;// 2208
    public static final short LoveLockDialogueCloseMessageComposer = 770;// 2027
    public static final short LoveLockDialogueMessageComposer = 3753;// 318
    public static final short LoveLockDialogueSetLockedMessageComposer = 382;// 2027
    public static final short FireworkDataChargesMessageComposer = 5210;// 2027

    // Room Trade
    public static final short TradingStartMessageComposer = 2505;// 2825
    public static final short TradingUpdateMessageComposer = 2024;// 2560
    public static final short TradingClosedMessageComposer = 1373;// 3671
    public static final short TradingAcceptMessageComposer = 2568;// 1464
    public static final short TradingCompleteMessageComposer = 2720;// 511
    public static final short TradingErrorMessageComposer = 217;// 1386
    public static final short TradingFinishMessageComposer = 1001;// 1940

    // Group
    public static final short GroupInfoMessageComposer = 1702;// 3190
    public static final short GroupFurniConfigMessageComposer = 420;// 1460
    public static final short GroupMembersMessageComposer = 1200;// 610 //
    public static final short ManageGroupMessageComposer = 3965;// 891
    public static final short HabboGroupBadgesMessageComposer = 2402;// 1333
    public static final short NewGroupInfoMessageComposer = 2808;// 2279
    public static final short GroupCreationWindowMessageComposer = 2159;// 870
    public static final short BadgeEditorPartsMessageComposer = 2238;// 2579
    public static final short RefreshFavouriteGroupMessageComposer = 876;// 1579
    public static final short GroupConfirmRemoveMemberMessageComposer = 1876;// 2328;//3992
    public static final short GroupEditErrorMessageComposer = 3988;// 2328;//3992

    // Group Forums
    public static final short GroupForumDataMessageComposer = 3011;// 2023
    public static final short ThreadsListDataMessageComposer = 1073;// 2597
    public static final short ThreadCreatedMessageComposer = 1862;// 871
    public static final short ThreadReplyMessageComposer = 2049;// 2413
    public static final short ThreadUpdatedMessageComposer = 2528;// 517
    public static final short ThreadDataMessageComposer = 509;// 3052
    public static final short ThreadUpdateReplyMessageComposer = 324;// 2551
    public static final short ForumsListDataMessageComposer = 3001;// 2103

    // Catalog
    public static final short CatalogItemDiscountMessageComposer = 2347;// 3575
    public static final short PromotableRoomsMessageComposer = 2468;// 2283
    public static final short CatalogUpdatedMessageComposer = 1866;// 489
    public static final short CatalogOfferMessageComposer = 3388;// 2600
    public static final short CatalogIndexMessageComposer = 1032;// 808
    public static final short CatalogPageMessageComposer = 804;// 2412
    public static final short CatalogGiftsPageMessageComposer = 619;// 3187
    public static final short PurchaseOKMessageComposer = 869;// 479
    public static final short PurchaseErrorMessageComposer = 1404;// 1408
    public static final short GiftWrappingConfigurationMessageComposer = 2234;// 1035
    public static final short GiftWrappingErrorMessageComposer = 1517;// 1434
    public static final short TargettedOfferMessageComposer = 119;// 1976
    public static final short MarketPlaceConfigMessageComposer = 1823;// 1976
    public static final short CatalogModeMessageComposer = 3828 ; // 2746

    // Crafting
    public static final short CraftableProductsMessageComposer = 1000;// 2221
    public static final short CraftableProductsToGetResultMessageComposer = 2774;// 1493
    public static final short CraftingFinalResultMessageComposer = 618;// 3261
    public static final short CraftingRecipeResultMessageComposer = 2124;// 1787
    public static final short CraftingDisplayTooltipMessageComposer = -1;// 1787

    // New User Experience [NUX]
    public static final short MassEventMessageComposer = 2023; // 2669
    public static final short NuxGiftSelectionViewMessageComposer = 3575;// 3122
    public static final short NuxGiftEmailViewMessageComposer = 2707;// 2343

    // Mistery Box
    public static final short MisteryBoxDataMessageComposer = 2833;// 2320
    public static final short CalendarPrizesMessageComposer = 2551;// 1500

    // Landing - Calendar
    public static final short PromoArticlesMessageComposer = 286; // 253
    public static final short CampaignMessageComposer = 1745;// 2081
    public static final short CampaignCalendarDataMessageComposer = 2531; // 1072
    public static final short CloseConnectionMessageComposer = 122;// 1297
    public static final short BonusBagMessageComposer = 1533;// 3951 // TODO: Add custom image handle on composer.
    public static final short LTDCountdownMessageComposer = 3926;// 824
    public static final short ConcurrentUsersCompetitionMessageComposer = 2737;// 1433
    public static final short SeasonalCalendarMessageComposer = 1122;// 1433

    // NUX - SMS - EMAIL
    public static final short EmailVerificationWindowMessageComposer = 800; // 2132
    public static final short SMSVerificationCompleteMessageComposer = 91; // 3369

    // Mod Tools - HELP
    public static final short ModeratorInitMessageComposer = 2696;// 2772
    public static final short ModeratorRoomInfoMessageComposer = 1333;// 154
    public static final short ModeratorUserInfoMessageComposer = 2866;// 134
    public static final short ModeratorRoomChatlogMessageComposer = 3434;// 1708
    public static final short ModeratorUserChatlogMessageComposer = 3377;// 2334
    public static final short ModeratorSupportTicketResponseMessageComposer = 934;// 1825
    public static final short ModeratorSupportTicketMessageComposer = 3609;// 283
    public static final short CallForHelpPendingCallsMessageComposer = 1121;// 2804
    public static final short RoomMuteSettingsMessageComposer = 2533;// 3071
    public static final short SanctionStatusMessageComposer = 2221;// 1053
    public static final short MaintenanceStatusMessageComposer = 1350;// 2724

    // Jukebox
    public static final short SongInventoryMessageComposer = 2602;// 484
    public static final short SongIdMessageComposer = 1381;// 3538
    public static final short SongDataMessageComposer = 3365;// 1822
    public static final short PlaylistMessageComposer = 34;// 1080
    public static final short PlayMusicMessageComposer = 469;// 1401;//1366

    // Gamecenter
    public static final short GameListMessageComposer = 222;// 1187;
    public static final short GameAchievementsMessageComposer = 2265;// 730;
    public static final short GameAccountStatusMessageComposer = 2893;// 1821
    public static final short GameStatusMessageComposer = 1805; //
    public static final short WeeklyLeaderboardComposer = 3512;// 3166;
    public static final short Game2WeeklyLeaderboardParser = 3560;// 1324;
    public static final short LastWeekLeaderboardComposer = 2196;// 2082;
    public static final short LuckyLosersComposer = 3097;// 307;
    public static final short GameCenterJoinQueueMessageComposer = 2260;// 307
    public static final short LoadGameMessageComposer = 3654;// 2610

    // Rentables
    public static final short RentableDataMessageComposer = 3559;// 2684;
    public static final short RentOfferMessageComposer = 2046;// 2204;

    // Infobus Polls
    public static final short StartInfobusPollMessageComposer = 5200;
    public static final short GetInfobusPollResultsMessageComposer = 5201;

    // Snowstorm
    public static final short SnowStormGameStartedComposer = 5000;
    public static final short SnowInArenaQueueMessageComposer = 5001;
    public static final short SnowStormStartBlockTickerComposer = 5002;
    public static final short SnowStormStartLobbyCounterComposer = 5003;
    public static final short SnowStormUnusedAlertGenericComposer = 5004;
    public static final short GameOwnerSerializationMessageComposer = 5005;
    public static final short SnowStormGameEndedComposer = 5006;
    public static final short SnowStormGenericErrorComposer = 5007;
    public static final short SnowStormQueuePlayerAddedComposer = 5008;
    public static final short SnowStormPlayAgainComposer = 5009;
    public static final short SnowStormGamesLeftComposer = 5010;
    public static final short SnowStormQueuePlayerRemovedComposer = 5011;
    public static final short SnowStormGamesInformationComposer = 5012;
    public static final short GameSerializationMessageComposer = 5013;
    public static final short UNUSED_SNOWSTORM_5014 = 5014;
    public static final short SnowGameStatusMessageComposer = 5015;
    public static final short SnowStormFullGameStatusComposer = 5016;
    public static final short SnowStageStartingMessageComposer = 5017;
    public static final short SnowStageLoadComposer = 5018;
    public static final short SnowStormRejoinPreviousRoomComposer = 5019;
    public static final short SnowArenaEnteredMessageComposer = 5020;
    public static final short SnowEnterArenaMessageComposer = 5021;
    public static final short SnowGameEndingMessageComposer = 5022;
    public static final short SnowStormUserChatMessageComposer = 5023;
    public static final short StageRunningMessageComposer = 5024;
    public static final short SnowStormOnStageEndingComposer = 5025;
    public static final short SnowStormIntializedPlayersComposer = 5026;
    public static final short SnowStormOnPlayerExitedArenaComposer = 5027;
    public static final short SnowStormGenericErrorComposer2 = 5028;
    public static final short SnowStormUserRematchedComposer = 5029;


    /*** --------- UPDATED - PENDING TO BE PLACED  -------- ***/
    public static final short GenericErrorMessageComposer = 3323;// 2856
    public static final short MutedMessageComposer = 826;// 1671;
    public static final short ModeratorTicketChatlogMessageComposer = 607;// 3492;//898
    public static final short ModeratorUserRoomVisitsMessageComposer = 1752;// 2415;//3082
    public static final short RemoveWallItemMessageComposer = 3208;// 2091
    public static final short EnforceCategoryUpdateMessageComposer = 3896;// 2621

    public static final short WardrobeMessageComposer = 3315;// 1178
    public static final short YouArePlayingGameMessageComposer = 448;// 613

    public static final short QuickPollMessageComposer = 2665;// 1232;
    public static final short QuickPollResultMessageComposer = 2589;// 2178
    public static final short QuickPollResultsMessageComposer = 1066;// 3139
    public static final short InitializePollMessageComposer = 3785;// 3726
    public static final short PollMessageComposer = 2997;// 430
    public static final short YouAreSpectatorMessageComposer = 1033;// 2666

    public static final short ThumbnailSavedMessageComposer = 3595;// 1101;
    public static final short PhotoPreviewMessageComposer = 3696;// 3115;
    public static final short PurchasedPhotoMessageComposer = 2783;// 3859;

    public static final short PetUpdateStatusComposer = 1907;// 751;

    public static final short UpdateUsernameMessageComposer = 118;// 393
    public static final short UserNameChangeMessageComposer = 2182;// 898
    public static final short NameChangeUpdateMessageComposer = 563;// 3436

    public static final short GraphicAlertMessageComposer = 3945;// 75

    public static final short NewYearResolutionMessageComposer = 66;// 3133
    public static final short SendHotelViewLooksMessageComposer = 3005;// 791

    public static final short PetBreedingMessageComposer = 634;// 3099
    public static final short PetBreedingCompleteMessageComposer = 2527;// 3034
    public static final short PetBreedingStartedMessageComposer = 1625;// 1692
    public static final short PetPackageMessageComposer = 2380;// 3781
    public static final short PetPackageOpenedMessageComposer = 546;// 1278
    /*** --------- NOT UPDATED YET  -------- ***/

    // NUX - SMS - EMAIL
    public static final short SMSVerificationWindowMessageComposer = 1542;
    public static final short SMSVerificationOfferMessageComposer = 2033;


    // Unused for the moment
    public static final short YoutubeDisplayPlaylistsMessageComposer = -1;// 1882
    public static final short YouTubeDisplayVideoMessageComposer = -1;// 29
    public static final short RoomActionMessageComposer = 5212; // 1550 DELETED PACKET DE ROTAR F
    public static final short CameraPhotoPreviewMessageComposer = 3115;//555  CameraPhotoPreviewMessageComposer = 1654
    public static final short CameraBuyPhotoMessageComposer = 3859;//1492     CameraBuyPhotoMessageComposer = 3273
    public static final short CameraPriceMessageComposer = 953;//3328         CameraPriceMessageComposer = 3092
    public static final short GuideSessionRequesterRoomMessageComposer = 2905;//3906
    public static final short GuardianNewReportReceivedMessageComposer = 2350;//1001
    public static final short GuardianVotingRequestedMessageComposer = 2094;//3075
    public static final short GuardianVotingVotesMessageComposer = 2692;//2449
    public static final short GuardianVotingResultMessageComposer = 1103;//847
    public static final short GuardianVotingTimeEndedMessageComposer = 3015;//238
    public static final short ModToolReportReceivedAlertMessageComposer = 3124;//2604
    public static final short BullyReportClosedMessageComposer = 1998;//1259
    public static final short BullyReportRequestMessageComposer = 2586;//3711
    public static final short BullyReportedMessageMessageComposer = 3004;//1416
    public static final short HelperRequestDisabledMessageComposer = 2817;//2975
    public static final short FindFriendsProcessResultMessageComposer = 932;//3682
    public static final short NavigatorFavoritedRoomMessageComposer = 3846;//1723
    public static final short FollowErrorMessageComposer = 344;//2054
    public static final short FriendRequestErrorMessageComposer = 2711;//75
    public static final short BotErrorMessageComposer = 2605;//2469
    public static final short PetErrorMessageComposer = 3987;//1819
    public static final short GroupAcceptMemberErrorMessageComposer = 2983;//2398
    public static final short RemoveGroupFromRoomMessageComposer = 740;//274
    public static final short RefreshGroupMembersListMessageComposer = 3186;//1775
    public static final short GroupMemberUpdateMessageComposer = 3263;//1740
    public static final short RemoveBotMessageComposer = 1551;//499
    public static final short ReceivedHandItemMessageComposer = 935;//3496
    public static final short LimitedEditionSoldOutMessageComposer = 2979;//3847
    public static final short LoveLockDialogueFinishedMessageComposer = 2355;//3449
    public static final short RoomChatSettingsMessageComposer = 2114;//2784
    public static final short AddBotMessageComposer = 1357;//1485
    public static final short PurchaseUnavailableErrorMessageComposer = 2866;//3463
    public static final short GroupFavoritePlayerUpdateMessageComposer = 3770;//3669
    public static final short JoinGroupErrorMessageComposer = 141;//1186

    private static Map<Short, String> composerPacketNames = new HashMap<>();

    static {
        try {
            for (Field field : Composers.class.getDeclaredFields()) {
                if (!Modifier.isPrivate(field.getModifiers()))
                    composerPacketNames.put(field.getShort(field.getName()), field.getName());
            }
        } catch (Exception ignored) {

        }
    }

    public static String valueOfId(short packetId) {
        if (composerPacketNames.containsKey(packetId)) {
            return composerPacketNames.get(packetId);
        }

        return "UnknownMessageComposer";
    }
}
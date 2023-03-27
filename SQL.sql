-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.11.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table hylib.achievements
DROP TABLE IF EXISTS `achievements`;
CREATE TABLE IF NOT EXISTS `achievements` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `group_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci NOT NULL DEFAULT 'ACH_',
  `category` enum('identity','explore','music','social','games','room_builder','tools','commercial','survival_mode','pets') CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci NOT NULL DEFAULT 'identity',
  `level` int(11) NOT NULL DEFAULT 1,
  `reward_activity_points` int(11) NOT NULL DEFAULT 100,
  `reward_achievement_points` int(11) DEFAULT 10,
  `reward_type` int(11) DEFAULT 0,
  `progress_requirement` int(11) NOT NULL DEFAULT 1,
  `enabled` enum('1','0') CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci NOT NULL DEFAULT '1',
  `game_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=1119 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.achievements: 939 rows
DELETE FROM `achievements`;
/*!40000 ALTER TABLE `achievements` DISABLE KEYS */;
INSERT INTO `achievements` (`id`, `group_name`, `category`, `level`, `reward_activity_points`, `reward_achievement_points`, `reward_type`, `progress_requirement`, `enabled`, `game_id`) VALUES
	(1, 'ACH_AvatarLooks', 'identity', 1, 0, 100, 0, 1, '1', 0),
	(2, 'ACH_Motto', 'identity', 1, 0, 100, 0, 1, '1', 0),
	(3, 'ACH_RespectGiven', 'social', 1, 0, 10, 0, 1, '1', 0),
	(4, 'ACH_RespectEarned', 'social', 1, 0, 10, 0, 1, '1', 0),
	(5, 'ACH_RespectEarned', 'social', 2, 0, 10, 0, 10, '1', 0),
	(6, 'ACH_RespectEarned', 'social', 3, 0, 10, 0, 20, '1', 0),
	(7, 'ACH_RespectEarned', 'social', 4, 0, 10, 0, 50, '1', 0),
	(8, 'ACH_RespectEarned', 'social', 5, 0, 10, 0, 100, '1', 0),
	(9, 'ACH_RespectEarned', 'social', 6, 0, 10, 0, 250, '1', 0),
	(10, 'ACH_RespectEarned', 'social', 7, 0, 10, 0, 500, '1', 0),
	(11, 'ACH_RespectEarned', 'social', 8, 0, 10, 0, 750, '1', 0),
	(12, 'ACH_RespectEarned', 'social', 9, 0, 15, 0, 1000, '1', 0),
	(13, 'ACH_RespectEarned', 'social', 10, 0, 20, 0, 1500, '1', 0),
	(14, 'ACH_RoomEntry', 'explore', 1, 0, 10, 0, 5, '1', 0),
	(15, 'ACH_RoomEntry', 'explore', 2, 0, 10, 0, 10, '1', 0),
	(16, 'ACH_RoomEntry', 'explore', 3, 0, 10, 0, 20, '1', 0),
	(17, 'ACH_RoomEntry', 'explore', 4, 0, 10, 0, 30, '1', 0),
	(18, 'ACH_RoomEntry', 'explore', 5, 0, 10, 0, 40, '1', 0),
	(19, 'ACH_PetLover', 'social', 1, 0, 10, 0, 1, '1', 0),
	(20, 'ACH_PetLover', 'social', 2, 0, 10, 0, 3, '1', 0),
	(21, 'ACH_PetLover', 'social', 3, 0, 10, 0, 9, '1', 0),
	(22, 'ACH_PetLover', 'social', 4, 0, 15, 0, 12, '1', 0),
	(23, 'ACH_PetLover', 'social', 5, 0, 20, 0, 15, '1', 0),
	(24, 'ACH_PetRespectGiver', 'social', 1, 0, 10, 0, 10, '1', 0),
	(25, 'ACH_PetLevelUp', 'social', 1, 0, 10, 0, 1, '1', 0),
	(26, 'ACH_PetRespectGiver', 'social', 2, 0, 10, 0, 50, '1', 0),
	(27, 'ACH_PetRespectGiver', 'social', 3, 0, 10, 0, 100, '1', 0),
	(28, 'ACH_PetRespectGiver', 'social', 4, 0, 10, 0, 150, '1', 0),
	(29, 'ACH_PetRespectGiver', 'social', 5, 0, 10, 0, 200, '1', 0),
	(30, 'ACH_PetRespectGiver', 'social', 6, 0, 10, 0, 250, '1', 0),
	(31, 'ACH_PetRespectGiver', 'social', 7, 0, 10, 0, 300, '1', 0),
	(32, 'ACH_PetRespectGiver', 'social', 8, 0, 10, 0, 350, '1', 0),
	(33, 'ACH_PetRespectGiver', 'social', 9, 0, 15, 0, 400, '1', 0),
	(34, 'ACH_PetRespectGiver', 'social', 10, 0, 20, 0, 500, '1', 0),
	(35, 'ACH_FriendListSize', 'social', 1, 0, 10, 0, 10, '1', 0),
	(36, 'ACH_FriendListSize', 'social', 2, 0, 10, 0, 20, '1', 0),
	(37, 'ACH_FriendListSize', 'social', 3, 0, 10, 0, 30, '1', 0),
	(38, 'ACH_FriendListSize', 'social', 4, 0, 10, 0, 40, '1', 0),
	(39, 'ACH_FriendListSize', 'social', 5, 0, 10, 0, 50, '1', 0),
	(40, 'ACH_FriendListSize', 'social', 6, 0, 10, 0, 60, '1', 0),
	(41, 'ACH_FriendListSize', 'social', 7, 0, 10, 0, 70, '1', 0),
	(42, 'ACH_FriendListSize', 'social', 8, 0, 10, 0, 80, '1', 0),
	(43, 'ACH_FriendListSize', 'social', 9, 0, 10, 0, 90, '1', 0),
	(44, 'ACH_FriendListSize', 'social', 10, 0, 10, 0, 100, '1', 0),
	(45, 'ACH_FriendListSize', 'social', 11, 0, 10, 0, 110, '1', 0),
	(46, 'ACH_FriendListSize', 'social', 12, 0, 10, 0, 120, '1', 0),
	(47, 'ACH_FriendListSize', 'social', 13, 0, 10, 0, 130, '1', 0),
	(48, 'ACH_FriendListSize', 'social', 14, 0, 15, 0, 140, '1', 0),
	(49, 'ACH_FriendListSize', 'social', 15, 0, 20, 0, 150, '1', 0),
	(50, 'ACH_RoomEntry', 'explore', 6, 0, 10, 0, 50, '1', 0),
	(51, 'ACH_RoomEntry', 'explore', 7, 0, 10, 0, 100, '1', 0),
	(52, 'ACH_RoomEntry', 'explore', 8, 0, 10, 0, 200, '1', 0),
	(53, 'ACH_RoomEntry', 'explore', 9, 0, 10, 0, 300, '1', 0),
	(54, 'ACH_RoomEntry', 'explore', 10, 0, 10, 0, 400, '1', 0),
	(55, 'ACH_RoomEntry', 'explore', 11, 0, 10, 0, 500, '1', 0),
	(56, 'ACH_RoomEntry', 'explore', 12, 0, 10, 0, 600, '1', 0),
	(57, 'ACH_RoomEntry', 'explore', 13, 0, 10, 0, 700, '1', 0),
	(58, 'ACH_RoomEntry', 'explore', 14, 0, 10, 0, 800, '1', 0),
	(59, 'ACH_RoomEntry', 'explore', 15, 0, 10, 0, 900, '1', 0),
	(60, 'ACH_RoomEntry', 'explore', 16, 0, 10, 0, 1000, '1', 0),
	(61, 'ACH_RoomEntry', 'explore', 17, 0, 10, 0, 1500, '1', 0),
	(62, 'ACH_RoomEntry', 'explore', 18, 0, 10, 0, 2000, '1', 0),
	(63, 'ACH_RoomEntry', 'explore', 19, 0, 15, 0, 2500, '1', 0),
	(64, 'ACH_RoomEntry', 'explore', 20, 0, 20, 0, 3000, '1', 0),
	(65, 'ACH_RespectGiven', 'social', 2, 0, 10, 0, 10, '1', 0),
	(66, 'ACH_RespectGiven', 'social', 3, 0, 10, 0, 20, '1', 0),
	(67, 'ACH_RespectGiven', 'social', 4, 0, 10, 0, 30, '1', 0),
	(68, 'ACH_RespectGiven', 'social', 5, 0, 10, 0, 40, '1', 0),
	(69, 'ACH_RespectGiven', 'social', 6, 0, 10, 0, 50, '1', 0),
	(70, 'ACH_RespectGiven', 'social', 7, 0, 10, 0, 100, '1', 0),
	(71, 'ACH_RespectGiven', 'social', 8, 0, 10, 0, 200, '1', 0),
	(72, 'ACH_RespectGiven', 'social', 9, 0, 10, 0, 300, '1', 0),
	(73, 'ACH_RespectGiven', 'social', 10, 0, 10, 0, 400, '1', 0),
	(74, 'ACH_RespectGiven', 'social', 11, 0, 10, 0, 500, '1', 0),
	(75, 'ACH_RespectGiven', 'social', 12, 0, 10, 0, 600, '1', 0),
	(76, 'ACH_RespectGiven', 'social', 13, 0, 10, 0, 700, '1', 0),
	(77, 'ACH_RespectGiven', 'social', 14, 0, 10, 0, 800, '1', 0),
	(78, 'ACH_RespectGiven', 'social', 15, 0, 10, 0, 900, '1', 0),
	(79, 'ACH_RespectGiven', 'social', 16, 0, 10, 0, 1000, '1', 0),
	(80, 'ACH_RespectGiven', 'social', 17, 0, 10, 0, 1500, '1', 0),
	(81, 'ACH_RespectGiven', 'social', 18, 0, 10, 0, 2000, '1', 0),
	(82, 'ACH_RespectGiven', 'social', 19, 0, 15, 0, 2500, '1', 0),
	(83, 'ACH_RespectGiven', 'social', 20, 0, 20, 0, 3000, '1', 0),
	(84, 'ACH_SelfModRoomFilterSeen', 'tools', 1, 0, 25, 0, 1, '1', 0),
	(85, 'ACH_SelfModDoorModeSeen', 'tools', 1, 0, 25, 0, 1, '1', 0),
	(86, 'ACH_SelfModWalkthroughSeen', 'tools', 1, 0, 25, 0, 1, '1', 0),
	(87, 'ACH_SelfModChatScrollSpeedSeen', 'tools', 1, 0, 25, 0, 1, '1', 0),
	(88, 'ACH_SelfModChatFloodFilterSeen', 'tools', 1, 0, 25, 0, 1, '1', 0),
	(89, 'ACH_SelfModChatHearRangeSeen', 'tools', 1, 0, 25, 0, 1, '1', 0),
	(90, 'ACH_SelfModIgnoreSeen', 'tools', 1, 0, 25, 0, 1, '1', 0),
	(91, 'ACH_SelfModMuteSeen', 'tools', 1, 0, 25, 0, 1, '1', 0),
	(92, 'ACH_SelfModKickSeen', 'tools', 1, 0, 25, 0, 1, '1', 0),
	(93, 'ACH_SelfModBanSeen', 'tools', 1, 0, 25, 0, 1, '1', 0),
	(94, 'ACH_Name', 'identity', 1, 0, 100, 0, 1, '1', 0),
	(95, 'ACH_BattleBallTilesLocked', 'games', 1, 0, 10, 0, 25, '1', 0),
	(96, 'ACH_BattleBallTilesLocked', 'games', 2, 0, 10, 0, 65, '1', 0),
	(97, 'ACH_BattleBallTilesLocked', 'games', 3, 0, 10, 0, 125, '1', 0),
	(98, 'ACH_BattleBallTilesLocked', 'games', 4, 0, 10, 0, 205, '1', 0),
	(99, 'ACH_BattleBallTilesLocked', 'games', 5, 0, 10, 0, 335, '1', 0),
	(100, 'ACH_BattleBallTilesLocked', 'games', 6, 0, 10, 0, 525, '1', 0),
	(101, 'ACH_BattleBallTilesLocked', 'games', 7, 0, 10, 0, 805, '1', 0),
	(102, 'ACH_BattleBallTilesLocked', 'games', 8, 0, 10, 0, 1235, '1', 0),
	(103, 'ACH_BattleBallTilesLocked', 'games', 9, 0, 10, 0, 1875, '1', 0),
	(104, 'ACH_BattleBallTilesLocked', 'games', 10, 0, 10, 0, 2875, '1', 0),
	(105, 'ACH_BattleBallTilesLocked', 'games', 11, 0, 10, 0, 4375, '1', 0),
	(106, 'ACH_BattleBallTilesLocked', 'games', 12, 0, 10, 0, 6875, '1', 0),
	(107, 'ACH_BattleBallTilesLocked', 'games', 13, 0, 10, 0, 10775, '1', 0),
	(108, 'ACH_BattleBallTilesLocked', 'games', 14, 0, 10, 0, 17075, '1', 0),
	(109, 'ACH_BattleBallTilesLocked', 'games', 15, 0, 10, 0, 27175, '1', 0),
	(110, 'ACH_BattleBallTilesLocked', 'games', 16, 0, 10, 0, 43275, '1', 0),
	(111, 'ACH_BattleBallTilesLocked', 'games', 17, 0, 10, 0, 69075, '1', 0),
	(112, 'ACH_BattleBallTilesLocked', 'games', 18, 0, 10, 0, 110375, '1', 0),
	(113, 'ACH_BattleBallTilesLocked', 'games', 19, 0, 15, 0, 176375, '1', 0),
	(114, 'ACH_BattleBallTilesLocked', 'games', 20, 0, 20, 0, 282075, '1', 0),
	(115, 'ACH_BattleBallPlayer', 'games', 1, 0, 10, 0, 50, '1', 0),
	(116, 'ACH_BattleBallPlayer', 'games', 2, 0, 10, 0, 125, '1', 0),
	(117, 'ACH_BattleBallPlayer', 'games', 3, 0, 10, 0, 240, '1', 0),
	(118, 'ACH_BattleBallPlayer', 'games', 4, 0, 10, 0, 410, '1', 0),
	(119, 'ACH_BattleBallPlayer', 'games', 5, 0, 10, 0, 655, '1', 0),
	(120, 'ACH_BattleBallPlayer', 'games', 6, 0, 10, 0, 1045, '1', 0),
	(121, 'ACH_BattleBallPlayer', 'games', 7, 0, 10, 0, 1615, '1', 0),
	(122, 'ACH_BattleBallPlayer', 'games', 8, 0, 10, 0, 2000, '1', 0),
	(123, 'ACH_BattleBallPlayer', 'games', 9, 0, 10, 0, 3765, '1', 0),
	(124, 'ACH_BattleBallPlayer', 'games', 10, 0, 10, 0, 6215, '1', 0),
	(125, 'ACH_BattleBallPlayer', 'games', 11, 0, 10, 0, 10865, '1', 0),
	(126, 'ACH_BattleBallPlayer', 'games', 12, 0, 10, 0, 19665, '1', 0),
	(127, 'ACH_BattleBallPlayer', 'games', 13, 0, 10, 0, 36365, '1', 0),
	(128, 'ACH_BattleBallPlayer', 'games', 14, 0, 10, 0, 68115, '1', 0),
	(129, 'ACH_BattleBallPlayer', 'games', 15, 0, 10, 0, 128415, '1', 0),
	(130, 'ACH_BattleBallPlayer', 'games', 16, 0, 10, 0, 242965, '1', 0),
	(131, 'ACH_BattleBallPlayer', 'games', 17, 0, 10, 0, 460615, '1', 0),
	(132, 'ACH_BattleBallPlayer', 'games', 18, 0, 10, 0, 874115, '1', 0),
	(133, 'ACH_BattleBallPlayer', 'games', 19, 0, 15, 0, 1659765, '1', 0),
	(134, 'ACH_BattleBallPlayer', 'games', 20, 0, 20, 0, 3152515, '1', 0),
	(135, 'ACH_BattleBallWinner', 'games', 1, 0, 10, 0, 50, '1', 0),
	(136, 'ACH_BattleBallWinner', 'games', 2, 0, 10, 0, 125, '1', 0),
	(137, 'ACH_BattleBallWinner', 'games', 3, 0, 10, 0, 240, '1', 0),
	(138, 'ACH_BattleBallWinner', 'games', 4, 0, 10, 0, 410, '1', 0),
	(139, 'ACH_BattleBallWinner', 'games', 5, 0, 10, 0, 655, '1', 0),
	(140, 'ACH_BattleBallWinner', 'games', 6, 0, 10, 0, 1045, '1', 0),
	(141, 'ACH_BattleBallWinner', 'games', 7, 0, 10, 0, 1615, '1', 0),
	(142, 'ACH_BattleBallWinner', 'games', 8, 0, 10, 0, 1, '1', 0),
	(143, 'ACH_BattleBallWinner', 'games', 9, 0, 10, 0, 3765, '1', 0),
	(144, 'ACH_BattleBallWinner', 'games', 10, 0, 10, 0, 6215, '1', 0),
	(145, 'ACH_BattleBallWinner', 'games', 11, 0, 10, 0, 10865, '1', 0),
	(146, 'ACH_BattleBallWinner', 'games', 12, 0, 10, 0, 19665, '1', 0),
	(147, 'ACH_BattleBallWinner', 'games', 13, 0, 10, 0, 36365, '1', 0),
	(148, 'ACH_BattleBallWinner', 'games', 14, 0, 10, 0, 68115, '1', 0),
	(149, 'ACH_BattleBallWinner', 'games', 15, 0, 10, 0, 128415, '1', 0),
	(150, 'ACH_BattleBallWinner', 'games', 16, 0, 10, 0, 242965, '1', 0),
	(151, 'ACH_BattleBallWinner', 'games', 17, 0, 10, 0, 460615, '1', 0),
	(152, 'ACH_BattleBallWinner', 'games', 18, 0, 10, 0, 874115, '1', 0),
	(153, 'ACH_BattleBallWinner', 'games', 19, 0, 15, 0, 1659765, '1', 0),
	(154, 'ACH_BattleBallWinner', 'games', 20, 0, 20, 0, 3152515, '1', 0),
	(155, 'ACH_FootballGoalScored', 'games', 1, 0, 10, 0, 1, '1', 0),
	(156, 'ACH_FootballGoalScored', 'games', 2, 0, 10, 0, 10, '1', 0),
	(157, 'ACH_FootballGoalScored', 'games', 3, 0, 10, 0, 100, '1', 0),
	(158, 'ACH_FootballGoalScored', 'games', 4, 0, 15, 0, 1000, '1', 0),
	(159, 'ACH_FootballGoalScored', 'games', 5, 0, 20, 0, 10000, '1', 0),
	(160, 'ACH_GiftGiver', 'social', 1, 0, 10, 0, 1, '1', 0),
	(161, 'ACH_GiftGiver', 'social', 2, 0, 10, 0, 6, '1', 0),
	(162, 'ACH_GiftGiver', 'social', 3, 0, 10, 0, 14, '1', 0),
	(163, 'ACH_GiftGiver', 'social', 4, 0, 10, 0, 26, '1', 0),
	(164, 'ACH_GiftGiver', 'social', 5, 0, 10, 0, 46, '1', 0),
	(165, 'ACH_GiftGiver', 'social', 6, 0, 10, 0, 86, '1', 0),
	(166, 'ACH_GiftGiver', 'social', 7, 0, 10, 0, 146, '1', 0),
	(167, 'ACH_GiftGiver', 'social', 8, 0, 10, 0, 236, '1', 0),
	(168, 'ACH_GiftGiver', 'social', 9, 0, 10, 0, 366, '1', 0),
	(169, 'ACH_GiftGiver', 'social', 10, 0, 10, 0, 566, '1', 0),
	(170, 'ACH_GiftReceiver', 'social', 1, 0, 10, 0, 1, '1', 0),
	(171, 'ACH_GiftReceiver', 'social', 2, 0, 10, 0, 6, '1', 0),
	(172, 'ACH_GiftReceiver', 'social', 3, 0, 10, 0, 14, '1', 0),
	(173, 'ACH_GiftReceiver', 'social', 4, 0, 10, 0, 26, '1', 0),
	(174, 'ACH_GiftReceiver', 'social', 5, 0, 10, 0, 46, '1', 0),
	(175, 'ACH_GiftReceiver', 'social', 6, 0, 10, 0, 86, '1', 0),
	(176, 'ACH_GiftReceiver', 'social', 7, 0, 10, 0, 146, '1', 0),
	(177, 'ACH_GiftReceiver', 'social', 8, 0, 10, 0, 236, '1', 0),
	(178, 'ACH_GiftReceiver', 'social', 9, 0, 10, 0, 366, '1', 0),
	(179, 'ACH_GiftReceiver', 'social', 10, 0, 20, 0, 566, '1', 0),
	(180, 'ACH_GiftGiver', 'social', 11, 0, 10, 0, 816, '1', 0),
	(181, 'ACH_GiftGiver', 'social', 12, 0, 10, 0, 1066, '1', 0),
	(182, 'ACH_GiftGiver', 'social', 13, 0, 10, 0, 1316, '1', 0),
	(183, 'ACH_GiftGiver', 'social', 14, 0, 15, 0, 1566, '1', 0),
	(184, 'ACH_GiftGiver', 'social', 15, 0, 20, 0, 1816, '1', 0),
	(185, 'ACH_BaseJumpDaysPlayed', 'games', 1, 0, 10, 0, 1, '1', 1),
	(186, 'ACH_BaseJumpDaysPlayed', 'games', 2, 0, 10, 0, 2, '1', 1),
	(187, 'ACH_BaseJumpDaysPlayed', 'games', 3, 0, 10, 0, 3, '1', 1),
	(188, 'ACH_BaseJumpDaysPlayed', 'games', 4, 0, 10, 0, 4, '1', 1),
	(189, 'ACH_BaseJumpDaysPlayed', 'games', 5, 0, 10, 0, 5, '1', 1),
	(190, 'ACH_BaseJumpDaysPlayed', 'games', 6, 0, 10, 0, 6, '1', 1),
	(191, 'ACH_BaseJumpDaysPlayed', 'games', 7, 0, 10, 0, 7, '1', 1),
	(192, 'ACH_BaseJumpDaysPlayed', 'games', 8, 0, 10, 0, 8, '1', 1),
	(193, 'ACH_BaseJumpDaysPlayed', 'games', 9, 0, 10, 0, 9, '1', 1),
	(194, 'ACH_BaseJumpDaysPlayed', 'games', 10, 0, 10, 0, 10, '1', 1),
	(195, 'ACH_BaseJumpDaysPlayed', 'games', 11, 0, 10, 0, 11, '1', 1),
	(196, 'ACH_BaseJumpDaysPlayed', 'games', 12, 0, 10, 0, 12, '1', 1),
	(197, 'ACH_BaseJumpDaysPlayed', 'games', 13, 0, 10, 0, 13, '1', 1),
	(198, 'ACH_BaseJumpDaysPlayed', 'games', 14, 0, 10, 0, 14, '1', 1),
	(199, 'ACH_BaseJumpDaysPlayed', 'games', 15, 0, 10, 0, 15, '1', 1),
	(200, 'ACH_BaseJumpDaysPlayed', 'games', 16, 0, 10, 0, 16, '1', 1),
	(201, 'ACH_BaseJumpDaysPlayed', 'games', 17, 0, 10, 0, 17, '1', 1),
	(202, 'ACH_BaseJumpDaysPlayed', 'games', 18, 0, 10, 0, 18, '1', 1),
	(203, 'ACH_BaseJumpDaysPlayed', 'games', 19, 0, 15, 0, 19, '1', 1),
	(204, 'ACH_BaseJumpDaysPlayed', 'games', 20, 0, 20, 0, 20, '1', 1),
	(205, 'ACH_BaseJumpBigParachute', 'games', 1, 0, 10, 0, 1, '1', 1),
	(206, 'ACH_BaseJumpBigParachute', 'games', 2, 0, 10, 0, 5, '1', 1),
	(207, 'ACH_BaseJumpBigParachute', 'games', 3, 0, 10, 0, 20, '1', 1),
	(208, 'ACH_BaseJumpBigParachute', 'games', 4, 0, 10, 0, 50, '1', 1),
	(209, 'ACH_BaseJumpBigParachute', 'games', 5, 0, 10, 0, 100, '1', 1),
	(210, 'ACH_BaseJumpBigParachute', 'games', 6, 0, 10, 0, 200, '1', 1),
	(211, 'ACH_BaseJumpBigParachute', 'games', 7, 0, 10, 0, 300, '1', 1),
	(212, 'ACH_BaseJumpBigParachute', 'games', 8, 0, 10, 0, 400, '1', 1),
	(213, 'ACH_BaseJumpBigParachute', 'games', 9, 0, 10, 0, 500, '1', 1),
	(214, 'ACH_BaseJumpBigParachute', 'games', 10, 0, 10, 0, 600, '1', 1),
	(215, 'ACH_BaseJumpBigParachute', 'games', 11, 0, 10, 0, 700, '1', 1),
	(216, 'ACH_BaseJumpBigParachute', 'games', 12, 0, 10, 0, 800, '1', 1),
	(217, 'ACH_BaseJumpBigParachute', 'games', 13, 0, 10, 0, 900, '1', 1),
	(218, 'ACH_BaseJumpBigParachute', 'games', 14, 0, 10, 0, 1000, '1', 1),
	(219, 'ACH_BaseJumpBigParachute', 'games', 15, 0, 10, 0, 1100, '1', 1),
	(220, 'ACH_BaseJumpBigParachute', 'games', 16, 0, 10, 0, 1200, '1', 1),
	(221, 'ACH_BaseJumpBigParachute', 'games', 17, 0, 10, 0, 1300, '1', 1),
	(222, 'ACH_BaseJumpBigParachute', 'games', 18, 0, 10, 0, 1400, '1', 1),
	(223, 'ACH_BaseJumpBigParachute', 'games', 19, 0, 15, 0, 1500, '1', 1),
	(224, 'ACH_BaseJumpBigParachute', 'games', 20, 0, 20, 0, 2000, '1', 1),
	(225, 'ACH_BaseJumpShield', 'games', 1, 0, 10, 0, 1, '1', 1),
	(226, 'ACH_BaseJumpShield', 'games', 2, 0, 10, 0, 5, '1', 1),
	(227, 'ACH_BaseJumpShield', 'games', 3, 0, 10, 0, 20, '1', 1),
	(228, 'ACH_BaseJumpShield', 'games', 4, 0, 10, 0, 50, '1', 1),
	(229, 'ACH_BaseJumpShield', 'games', 5, 0, 10, 0, 100, '1', 1),
	(230, 'ACH_BaseJumpShield', 'games', 6, 0, 10, 0, 200, '1', 1),
	(231, 'ACH_BaseJumpShield', 'games', 7, 0, 10, 0, 300, '1', 1),
	(232, 'ACH_BaseJumpShield', 'games', 8, 0, 10, 0, 400, '1', 1),
	(233, 'ACH_BaseJumpShield', 'games', 9, 0, 10, 0, 500, '1', 1),
	(234, 'ACH_BaseJumpShield', 'games', 10, 0, 10, 0, 600, '1', 1),
	(235, 'ACH_BaseJumpShield', 'games', 11, 0, 10, 0, 700, '1', 1),
	(236, 'ACH_BaseJumpShield', 'games', 12, 0, 10, 0, 800, '1', 1),
	(237, 'ACH_BaseJumpShield', 'games', 13, 0, 10, 0, 900, '1', 1),
	(238, 'ACH_BaseJumpShield', 'games', 14, 0, 10, 0, 1000, '1', 1),
	(239, 'ACH_BaseJumpShield', 'games', 15, 0, 10, 0, 1100, '1', 1),
	(240, 'ACH_BaseJumpShield', 'games', 16, 0, 10, 0, 1200, '1', 1),
	(241, 'ACH_BaseJumpShield', 'games', 17, 0, 10, 0, 1300, '1', 1),
	(242, 'ACH_BaseJumpShield', 'games', 18, 0, 10, 0, 1400, '1', 1),
	(243, 'ACH_BaseJumpShield', 'games', 19, 0, 15, 0, 1500, '1', 1),
	(244, 'ACH_BaseJumpShield', 'games', 20, 0, 20, 0, 2000, '1', 1),
	(245, 'ACH_BaseJumpWins', 'games', 1, 0, 10, 0, 1, '1', 1),
	(246, 'ACH_BaseJumpWins', 'games', 2, 0, 10, 0, 5, '1', 1),
	(247, 'ACH_BaseJumpWins', 'games', 3, 0, 10, 0, 20, '1', 1),
	(248, 'ACH_BaseJumpWins', 'games', 4, 0, 10, 0, 50, '1', 1),
	(249, 'ACH_BaseJumpWins', 'games', 5, 0, 10, 0, 100, '1', 1),
	(250, 'ACH_BaseJumpWins', 'games', 6, 0, 10, 0, 200, '1', 1),
	(251, 'ACH_BaseJumpWins', 'games', 7, 0, 10, 0, 300, '1', 1),
	(252, 'ACH_BaseJumpWins', 'games', 8, 0, 10, 0, 400, '1', 1),
	(253, 'ACH_BaseJumpWins', 'games', 9, 0, 10, 0, 500, '1', 1),
	(254, 'ACH_BaseJumpWins', 'games', 10, 0, 10, 0, 600, '1', 1),
	(255, 'ACH_BaseJumpWins', 'games', 11, 0, 10, 0, 700, '1', 1),
	(256, 'ACH_BaseJumpWins', 'games', 12, 0, 10, 0, 800, '1', 1),
	(257, 'ACH_BaseJumpWins', 'games', 13, 0, 10, 0, 900, '1', 1),
	(258, 'ACH_BaseJumpWins', 'games', 14, 0, 10, 0, 1000, '1', 1),
	(259, 'ACH_BaseJumpWins', 'games', 15, 0, 10, 0, 1100, '1', 1),
	(260, 'ACH_BaseJumpWins', 'games', 16, 0, 10, 0, 1200, '1', 1),
	(261, 'ACH_BaseJumpWins', 'games', 17, 0, 10, 0, 1300, '1', 1),
	(262, 'ACH_BaseJumpWins', 'games', 18, 0, 10, 0, 1400, '1', 1),
	(263, 'ACH_BaseJumpWins', 'games', 19, 0, 15, 0, 1500, '1', 1),
	(264, 'ACH_BaseJumpWins', 'games', 20, 0, 20, 0, 2000, '1', 1),
	(265, 'ACH_BaseJumpMissile', 'games', 1, 0, 10, 0, 1, '1', 1),
	(266, 'ACH_BaseJumpMissile', 'games', 2, 0, 10, 0, 5, '1', 1),
	(267, 'ACH_BaseJumpMissile', 'games', 3, 0, 10, 0, 20, '1', 1),
	(268, 'ACH_BaseJumpMissile', 'games', 4, 0, 10, 0, 50, '1', 1),
	(269, 'ACH_BaseJumpMissile', 'games', 5, 0, 10, 0, 100, '1', 1),
	(270, 'ACH_BaseJumpMissile', 'games', 6, 0, 10, 0, 200, '1', 1),
	(271, 'ACH_BaseJumpMissile', 'games', 7, 0, 10, 0, 300, '1', 1),
	(272, 'ACH_BaseJumpMissile', 'games', 8, 0, 10, 0, 400, '1', 1),
	(273, 'ACH_BaseJumpMissile', 'games', 9, 0, 10, 0, 500, '1', 1),
	(274, 'ACH_BaseJumpMissile', 'games', 10, 0, 10, 0, 600, '1', 1),
	(275, 'ACH_BaseJumpMissile', 'games', 11, 0, 10, 0, 700, '1', 1),
	(276, 'ACH_BaseJumpMissile', 'games', 12, 0, 10, 0, 800, '1', 1),
	(277, 'ACH_BaseJumpMissile', 'games', 13, 0, 10, 0, 900, '1', 1),
	(278, 'ACH_BaseJumpMissile', 'games', 14, 0, 10, 0, 1000, '1', 1),
	(279, 'ACH_BaseJumpMissile', 'games', 15, 0, 10, 0, 1100, '1', 1),
	(280, 'ACH_BaseJumpMissile', 'games', 16, 0, 10, 0, 1200, '1', 1),
	(281, 'ACH_BaseJumpMissile', 'games', 17, 0, 10, 0, 1300, '1', 1),
	(282, 'ACH_BaseJumpMissile', 'games', 18, 0, 10, 0, 1400, '1', 1),
	(283, 'ACH_BaseJumpMissile', 'games', 19, 0, 15, 0, 1500, '1', 1),
	(284, 'ACH_BaseJumpMissile', 'games', 20, 0, 20, 0, 2000, '1', 1),
	(285, 'ACH_AllTimeHotelPresence', 'identity', 1, 0, 0, 0, 60, '1', 0),
	(286, 'ACH_AllTimeHotelPresence', 'identity', 2, 0, 0, 0, 180, '1', 0),
	(287, 'ACH_AllTimeHotelPresence', 'identity', 3, 0, 0, 0, 480, '1', 0),
	(288, 'ACH_AllTimeHotelPresence', 'identity', 4, 0, 0, 0, 960, '1', 0),
	(289, 'ACH_AllTimeHotelPresence', 'identity', 5, 0, 0, 0, 2880, '1', 0),
	(290, 'ACH_AllTimeHotelPresence', 'identity', 6, 0, 0, 0, 8640, '1', 0),
	(291, 'ACH_AllTimeHotelPresence', 'identity', 7, 0, 0, 0, 17280, '1', 0),
	(292, 'ACH_AllTimeHotelPresence', 'identity', 8, 0, 0, 0, 34560, '1', 0),
	(293, 'ACH_AllTimeHotelPresence', 'identity', 9, 0, 0, 0, 69120, '1', 0),
	(294, 'ACH_AllTimeHotelPresence', 'identity', 10, 0, 0, 0, 138240, '1', 0),
	(295, 'ACH_AllTimeHotelPresence', 'identity', 11, 0, 0, 0, 7200, '1', 0),
	(296, 'ACH_AllTimeHotelPresence', 'identity', 12, 0, 0, 0, 10080, '1', 0),
	(297, 'ACH_AllTimeHotelPresence', 'identity', 13, 0, 0, 0, 20160, '1', 0),
	(298, 'ACH_AllTimeHotelPresence', 'identity', 14, 0, 0, 0, 30240, '1', 0),
	(299, 'ACH_AllTimeHotelPresence', 'identity', 15, 0, 0, 0, 40230, '1', 0),
	(300, 'ACH_AllTimeHotelPresence', 'identity', 16, 0, 0, 0, 50400, '1', 0),
	(301, 'ACH_AllTimeHotelPresence', 'identity', 17, 0, 0, 0, 60480, '1', 0),
	(302, 'ACH_AllTimeHotelPresence', 'identity', 18, 0, 0, 0, 80640, '1', 0),
	(303, 'ACH_AllTimeHotelPresence', 'identity', 19, 0, 0, 0, 100800, '1', 0),
	(304, 'ACH_AllTimeHotelPresence', 'identity', 20, 0, 0, 0, 138420, '1', 0),
	(305, 'ACH_RegistrationDuration', 'identity', 1, 0, 10, 0, 1, '1', 0),
	(306, 'ACH_RegistrationDuration', 'identity', 2, 0, 10, 0, 3, '1', 0),
	(307, 'ACH_RegistrationDuration', 'identity', 3, 0, 10, 0, 10, '1', 0),
	(308, 'ACH_RegistrationDuration', 'identity', 4, 0, 10, 0, 15, '1', 0),
	(309, 'ACH_RegistrationDuration', 'identity', 5, 0, 10, 0, 30, '1', 0),
	(310, 'ACH_RegistrationDuration', 'identity', 6, 0, 10, 0, 56, '1', 0),
	(311, 'ACH_RegistrationDuration', 'identity', 7, 0, 10, 0, 84, '1', 0),
	(312, 'ACH_RegistrationDuration', 'identity', 8, 0, 10, 0, 112, '1', 0),
	(313, 'ACH_RegistrationDuration', 'identity', 9, 0, 10, 0, 168, '1', 0),
	(314, 'ACH_RegistrationDuration', 'identity', 10, 0, 10, 0, 224, '1', 0),
	(315, 'ACH_RegistrationDuration', 'identity', 11, 0, 10, 0, 280, '1', 0),
	(316, 'ACH_RegistrationDuration', 'identity', 12, 0, 10, 0, 365, '1', 0),
	(317, 'ACH_RegistrationDuration', 'identity', 13, 0, 10, 0, 548, '1', 0),
	(318, 'ACH_RegistrationDuration', 'identity', 14, 0, 10, 0, 730, '1', 0),
	(319, 'ACH_RegistrationDuration', 'identity', 15, 0, 10, 0, 913, '1', 0),
	(320, 'ACH_RegistrationDuration', 'identity', 16, 0, 10, 0, 1095, '1', 0),
	(321, 'ACH_RegistrationDuration', 'identity', 17, 0, 10, 0, 1278, '1', 0),
	(322, 'ACH_RegistrationDuration', 'identity', 18, 0, 10, 0, 1460, '1', 0),
	(323, 'ACH_RegistrationDuration', 'identity', 19, 0, 15, 0, 1643, '1', 0),
	(324, 'ACH_RegistrationDuration', 'identity', 20, 0, 20, 0, 1825, '1', 0),
	(325, 'ACH_Login', 'identity', 1, 0, 10, 0, 1, '1', 0),
	(326, 'ACH_Login', 'identity', 2, 0, 10, 0, 8, '1', 0),
	(327, 'ACH_Login', 'identity', 3, 0, 10, 0, 15, '1', 0),
	(328, 'ACH_Login', 'identity', 4, 0, 10, 0, 28, '1', 0),
	(329, 'ACH_Login', 'identity', 5, 0, 10, 0, 35, '1', 0),
	(330, 'ACH_Login', 'identity', 6, 0, 10, 0, 50, '1', 0),
	(331, 'ACH_Login', 'identity', 7, 0, 10, 0, 70, '1', 0),
	(332, 'ACH_Login', 'identity', 8, 0, 10, 0, 80, '1', 0),
	(333, 'ACH_Login', 'identity', 9, 0, 10, 0, 90, '1', 0),
	(334, 'ACH_Login', 'identity', 10, 0, 10, 0, 100, '1', 0),
	(335, 'ACH_Login', 'identity', 11, 0, 10, 0, 120, '1', 0),
	(336, 'ACH_Login', 'identity', 12, 0, 10, 0, 140, '1', 0),
	(337, 'ACH_Login', 'identity', 13, 0, 10, 0, 160, '1', 0),
	(338, 'ACH_Login', 'identity', 14, 0, 10, 0, 180, '1', 0),
	(339, 'ACH_Login', 'identity', 15, 0, 10, 0, 200, '1', 0),
	(340, 'ACH_Login', 'identity', 16, 0, 10, 0, 250, '1', 0),
	(341, 'ACH_Login', 'identity', 17, 0, 10, 0, 300, '1', 0),
	(342, 'ACH_Login', 'identity', 18, 0, 10, 0, 350, '1', 0),
	(343, 'ACH_Login', 'identity', 19, 0, 15, 0, 400, '1', 0),
	(344, 'ACH_Login', 'identity', 20, 0, 20, 0, 500, '1', 0),
	(345, 'ACH_RoomDecoFloor', 'room_builder', 1, 0, 10, 0, 3, '1', 0),
	(346, 'ACH_RoomDecoFloor', 'room_builder', 2, 0, 10, 0, 5, '1', 0),
	(347, 'ACH_RoomDecoFloor', 'room_builder', 3, 0, 10, 0, 7, '1', 0),
	(348, 'ACH_RoomDecoFloor', 'room_builder', 4, 0, 10, 0, 12, '1', 0),
	(349, 'ACH_RoomDecoFloor', 'room_builder', 5, 0, 10, 0, 17, '1', 0),
	(350, 'ACH_RoomDecoFloor', 'room_builder', 6, 0, 10, 0, 22, '1', 0),
	(351, 'ACH_RoomDecoFloor', 'room_builder', 7, 0, 10, 0, 27, '1', 0),
	(352, 'ACH_RoomDecoFloor', 'room_builder', 8, 0, 10, 0, 32, '1', 0),
	(353, 'ACH_RoomDecoFloor', 'room_builder', 9, 0, 10, 0, 37, '1', 0),
	(354, 'ACH_RoomDecoFloor', 'room_builder', 10, 0, 10, 0, 47, '1', 0),
	(355, 'ACH_RoomDecoFloor', 'room_builder', 11, 0, 10, 0, 57, '1', 0),
	(356, 'ACH_RoomDecoFloor', 'room_builder', 12, 0, 10, 0, 67, '1', 0),
	(357, 'ACH_RoomDecoFloor', 'room_builder', 13, 0, 10, 0, 77, '1', 0),
	(358, 'ACH_RoomDecoFloor', 'room_builder', 14, 0, 10, 0, 97, '1', 0),
	(359, 'ACH_RoomDecoFloor', 'room_builder', 15, 0, 10, 0, 117, '1', 0),
	(360, 'ACH_RoomDecoFloor', 'room_builder', 16, 0, 10, 0, 137, '1', 0),
	(361, 'ACH_RoomDecoFloor', 'room_builder', 17, 0, 10, 0, 167, '1', 0),
	(362, 'ACH_RoomDecoFloor', 'room_builder', 18, 0, 10, 0, 197, '1', 0),
	(363, 'ACH_RoomDecoFloor', 'room_builder', 19, 0, 15, 0, 237, '1', 0),
	(364, 'ACH_RoomDecoFloor', 'room_builder', 20, 0, 20, 0, 287, '1', 0),
	(365, 'ACH_RoomDecoWallpaper', 'room_builder', 1, 0, 10, 0, 3, '1', 0),
	(366, 'ACH_RoomDecoWallpaper', 'room_builder', 2, 0, 10, 0, 5, '1', 0),
	(367, 'ACH_RoomDecoWallpaper', 'room_builder', 3, 0, 10, 0, 7, '1', 0),
	(368, 'ACH_RoomDecoWallpaper', 'room_builder', 4, 0, 10, 0, 12, '1', 0),
	(369, 'ACH_RoomDecoWallpaper', 'room_builder', 5, 0, 10, 0, 17, '1', 0),
	(370, 'ACH_RoomDecoWallpaper', 'room_builder', 6, 0, 10, 0, 22, '1', 0),
	(371, 'ACH_RoomDecoWallpaper', 'room_builder', 7, 0, 10, 0, 27, '1', 0),
	(372, 'ACH_RoomDecoWallpaper', 'room_builder', 8, 0, 10, 0, 32, '1', 0),
	(373, 'ACH_RoomDecoWallpaper', 'room_builder', 9, 0, 10, 0, 37, '1', 0),
	(374, 'ACH_RoomDecoWallpaper', 'room_builder', 10, 0, 10, 0, 47, '1', 0),
	(375, 'ACH_RoomDecoWallpaper', 'room_builder', 11, 0, 10, 0, 57, '1', 0),
	(376, 'ACH_RoomDecoWallpaper', 'room_builder', 12, 0, 10, 0, 67, '1', 0),
	(377, 'ACH_RoomDecoWallpaper', 'room_builder', 13, 0, 10, 0, 77, '1', 0),
	(378, 'ACH_RoomDecoWallpaper', 'room_builder', 14, 0, 10, 0, 97, '1', 0),
	(379, 'ACH_RoomDecoWallpaper', 'room_builder', 15, 0, 10, 0, 117, '1', 0),
	(380, 'ACH_RoomDecoWallpaper', 'room_builder', 16, 0, 10, 0, 137, '1', 0),
	(381, 'ACH_RoomDecoWallpaper', 'room_builder', 17, 0, 10, 0, 167, '1', 0),
	(382, 'ACH_RoomDecoWallpaper', 'room_builder', 18, 0, 10, 0, 197, '1', 0),
	(383, 'ACH_RoomDecoWallpaper', 'room_builder', 19, 0, 15, 0, 237, '1', 0),
	(384, 'ACH_RoomDecoWallpaper', 'room_builder', 20, 0, 20, 0, 287, '1', 0),
	(385, 'ACH_RoomDecoLandscape', 'room_builder', 1, 0, 10, 0, 3, '1', 0),
	(386, 'ACH_RoomDecoLandscape', 'room_builder', 2, 0, 10, 0, 5, '1', 0),
	(387, 'ACH_RoomDecoLandscape', 'room_builder', 3, 0, 10, 0, 7, '1', 0),
	(388, 'ACH_RoomDecoLandscape', 'room_builder', 4, 0, 10, 0, 12, '1', 0),
	(389, 'ACH_RoomDecoLandscape', 'room_builder', 5, 0, 10, 0, 17, '1', 0),
	(390, 'ACH_RoomDecoLandscape', 'room_builder', 6, 0, 10, 0, 22, '1', 0),
	(391, 'ACH_RoomDecoLandscape', 'room_builder', 7, 0, 10, 0, 27, '1', 0),
	(392, 'ACH_RoomDecoLandscape', 'room_builder', 8, 0, 10, 0, 32, '1', 0),
	(393, 'ACH_RoomDecoLandscape', 'room_builder', 9, 0, 10, 0, 37, '1', 0),
	(394, 'ACH_RoomDecoLandscape', 'room_builder', 10, 0, 10, 0, 47, '1', 0),
	(395, 'ACH_RoomDecoLandscape', 'room_builder', 11, 0, 10, 0, 57, '1', 0),
	(396, 'ACH_RoomDecoLandscape', 'room_builder', 12, 0, 10, 0, 67, '1', 0),
	(397, 'ACH_RoomDecoLandscape', 'room_builder', 13, 0, 10, 0, 77, '1', 0),
	(398, 'ACH_RoomDecoLandscape', 'room_builder', 14, 0, 10, 0, 97, '1', 0),
	(399, 'ACH_RoomDecoLandscape', 'room_builder', 15, 0, 10, 0, 117, '1', 0),
	(400, 'ACH_RoomDecoLandscape', 'room_builder', 16, 0, 10, 0, 137, '1', 0),
	(401, 'ACH_RoomDecoLandscape', 'room_builder', 17, 0, 10, 0, 167, '1', 0),
	(402, 'ACH_RoomDecoLandscape', 'room_builder', 18, 0, 10, 0, 197, '1', 0),
	(403, 'ACH_RoomDecoLandscape', 'room_builder', 19, 0, 15, 0, 237, '1', 0),
	(404, 'ACH_RoomDecoLandscape', 'room_builder', 20, 0, 20, 0, 287, '1', 0),
	(405, 'ACH_RoomDecoFurniCount', 'room_builder', 1, 0, 10, 0, 15, '1', 0),
	(406, 'ACH_RoomDecoFurniCount', 'room_builder', 2, 0, 10, 0, 20, '1', 0),
	(407, 'ACH_RoomDecoFurniCount', 'room_builder', 3, 0, 10, 0, 25, '1', 0),
	(408, 'ACH_RoomDecoFurniCount', 'room_builder', 4, 0, 10, 0, 30, '1', 0),
	(409, 'ACH_RoomDecoFurniCount', 'room_builder', 5, 0, 10, 0, 40, '1', 0),
	(410, 'ACH_RoomDecoFurniCount', 'room_builder', 6, 0, 10, 0, 45, '1', 0),
	(411, 'ACH_RoomDecoFurniCount', 'room_builder', 7, 0, 10, 0, 50, '1', 0),
	(412, 'ACH_RoomDecoFurniCount', 'room_builder', 8, 0, 10, 0, 65, '1', 0),
	(413, 'ACH_RoomDecoFurniCount', 'room_builder', 9, 0, 10, 0, 80, '1', 0),
	(414, 'ACH_RoomDecoFurniCount', 'room_builder', 10, 0, 10, 0, 95, '1', 0),
	(415, 'ACH_RoomDecoFurniCount', 'room_builder', 11, 0, 10, 0, 110, '1', 0),
	(416, 'ACH_RoomDecoFurniCount', 'room_builder', 12, 0, 10, 0, 125, '1', 0),
	(417, 'ACH_RoomDecoFurniCount', 'room_builder', 13, 0, 10, 0, 150, '1', 0),
	(418, 'ACH_RoomDecoFurniCount', 'room_builder', 14, 0, 10, 0, 170, '1', 0),
	(419, 'ACH_RoomDecoFurniCount', 'room_builder', 15, 0, 10, 0, 200, '1', 0),
	(420, 'ACH_RoomDecoFurniCount', 'room_builder', 16, 0, 10, 0, 230, '1', 0),
	(421, 'ACH_RoomDecoFurniCount', 'room_builder', 17, 0, 10, 0, 270, '1', 0),
	(422, 'ACH_RoomDecoFurniCount', 'room_builder', 18, 0, 10, 0, 320, '1', 0),
	(423, 'ACH_RoomDecoFurniCount', 'room_builder', 19, 0, 15, 0, 360, '1', 0),
	(424, 'ACH_RoomDecoFurniCount', 'room_builder', 20, 0, 20, 0, 410, '1', 0),
	(425, 'ACH_SelfModForumCanModerateSeen', 'tools', 1, 0, 10, 0, 1, '1', 0),
	(426, 'ACH_SelfModForumCanPostSeen', 'tools', 1, 0, 10, 0, 1, '1', 0),
	(427, 'ACH_SelfModForumCanPostThrdSeen', 'tools', 1, 0, 10, 0, 1, '1', 0),
	(428, 'ACH_SelfModForumCanReadSeen', 'tools', 1, 0, 10, 0, 1, '1', 0),
	(429, 'ACH_CameraPhotoCount', 'explore', 1, 0, 5, 0, 1, '1', 0),
	(430, 'ACH_CameraPhotoCount', 'explore', 2, 0, 5, 0, 3, '1', 0),
	(431, 'ACH_CameraPhotoCount', 'explore', 3, 0, 5, 0, 9, '1', 0),
	(432, 'ACH_CameraPhotoCount', 'explore', 4, 0, 5, 0, 13, '1', 0),
	(433, 'ACH_CameraPhotoCount', 'explore', 5, 0, 5, 0, 20, '1', 0),
	(434, 'ACH_CameraPhotoCount', 'explore', 6, 0, 5, 0, 25, '1', 0),
	(435, 'ACH_CameraPhotoCount', 'explore', 7, 0, 5, 0, 30, '1', 0),
	(436, 'ACH_CameraPhotoCount', 'explore', 8, 0, 5, 0, 35, '1', 0),
	(437, 'ACH_CameraPhotoCount', 'explore', 9, 0, 5, 0, 40, '1', 0),
	(438, 'ACH_CameraPhotoCount', 'explore', 10, 0, 5, 0, 46, '1', 0),
	(439, 'ACH_PinataWhacker', 'explore', 1, 0, 5, 0, 1, '1', 0),
	(440, 'ACH_PinataWhacker', 'explore', 2, 0, 100, 0, 5, '1', 0),
	(441, 'ACH_PinataWhacker', 'explore', 3, 0, 50, 0, 10, '1', 0),
	(442, 'ACH_PinataWhacker', 'explore', 4, 0, 50, 0, 25, '1', 0),
	(443, 'ACH_PinataWhacker', 'explore', 5, 0, 50, 0, 35, '1', 0),
	(444, 'ACH_PinataWhacker', 'explore', 6, 0, 50, 0, 50, '1', 0),
	(445, 'ACH_PinataWhacker', 'explore', 7, 0, 50, 0, 60, '1', 0),
	(446, 'ACH_PinataWhacker', 'explore', 8, 0, 50, 0, 75, '1', 0),
	(447, 'ACH_PinataWhacker', 'explore', 9, 0, 50, 0, 85, '1', 0),
	(448, 'ACH_PinataWhacker', 'explore', 10, 0, 50, 0, 100, '1', 0),
	(449, 'ACH_PinataWhacker', 'explore', 11, 0, 100, 0, 115, '1', 0),
	(450, 'ACH_PinataWhacker', 'explore', 12, 0, 100, 0, 120, '1', 0),
	(451, 'ACH_PinataWhacker', 'explore', 13, 0, 100, 0, 130, '1', 0),
	(452, 'ACH_PinataWhacker', 'explore', 14, 0, 100, 0, 140, '1', 0),
	(453, 'ACH_PinataWhacker', 'explore', 15, 0, 100, 0, 150, '1', 0),
	(454, 'ACH_PinataWhacker', 'explore', 16, 0, 100, 0, 160, '1', 0),
	(455, 'ACH_PinataWhacker', 'explore', 17, 0, 100, 0, 170, '1', 0),
	(456, 'ACH_PinataWhacker', 'explore', 18, 0, 100, 0, 180, '1', 0),
	(457, 'ACH_PinataWhacker', 'explore', 19, 0, 100, 0, 190, '1', 0),
	(458, 'ACH_PinataWhacker', 'explore', 20, 0, 100, 0, 200, '1', 0),
	(459, 'ACH_PinataBreaker', 'explore', 1, 0, 50, 0, 10, '1', 0),
	(460, 'ACH_PinataBreaker', 'explore', 2, 0, 50, 0, 25, '1', 0),
	(461, 'ACH_PinataBreaker', 'explore', 3, 0, 50, 0, 10, '1', 0),
	(462, 'ACH_PinataBreaker', 'explore', 4, 0, 50, 0, 25, '1', 0),
	(463, 'ACH_PinataBreaker', 'explore', 5, 0, 50, 0, 35, '1', 0),
	(464, 'ACH_PinataBreaker', 'explore', 6, 0, 50, 0, 50, '1', 0),
	(465, 'ACH_PinataBreaker', 'explore', 7, 0, 50, 0, 60, '1', 0),
	(466, 'ACH_PinataBreaker', 'explore', 8, 0, 50, 0, 75, '1', 0),
	(467, 'ACH_PinataBreaker', 'explore', 9, 0, 50, 0, 85, '1', 0),
	(468, 'ACH_PinataBreaker', 'explore', 10, 0, 50, 0, 100, '1', 0),
	(469, 'ACH_PinataBreaker', 'explore', 11, 0, 100, 0, 115, '1', 0),
	(470, 'ACH_PinataBreaker', 'explore', 12, 0, 100, 0, 120, '1', 0),
	(471, 'ACH_PinataBreaker', 'explore', 13, 0, 100, 0, 130, '1', 0),
	(472, 'ACH_PinataBreaker', 'explore', 14, 0, 100, 0, 140, '1', 0),
	(473, 'ACH_PinataBreaker', 'explore', 15, 0, 100, 0, 150, '1', 0),
	(474, 'ACH_PinataBreaker', 'explore', 16, 0, 100, 0, 160, '1', 0),
	(475, 'ACH_PinataBreaker', 'explore', 17, 0, 100, 0, 170, '1', 0),
	(476, 'ACH_PinataBreaker', 'explore', 18, 0, 100, 0, 180, '1', 0),
	(477, 'ACH_PinataBreaker', 'explore', 19, 0, 100, 0, 190, '1', 0),
	(478, 'ACH_PinataBreaker', 'explore', 20, 0, 100, 0, 200, '1', 0),
	(479, 'ACH_Citizenship', 'identity', 1, 0, 150, 0, 1, '1', 0),
	(480, 'ACH_GuideEnrollmentLifetime', 'social', 1, 0, 10, 0, 5, '1', 0),
	(481, 'ACH_GuideEnrollmentLifetime', 'social', 2, 0, 20, 0, 10, '1', 0),
	(482, 'ACH_GuideEnrollmentLifetime', 'social', 3, 0, 30, 0, 25, '1', 0),
	(483, 'ACH_GuideEnrollmentLifetime', 'social', 4, 0, 40, 0, 30, '1', 0),
	(484, 'ACH_GuideEnrollmentLifetime', 'social', 5, 0, 50, 0, 50, '1', 0),
	(485, 'ACH_GuideEnrollmentLifetime', 'social', 6, 0, 60, 0, 60, '1', 0),
	(486, 'ACH_GuideEnrollmentLifetime', 'social', 7, 0, 70, 0, 70, '1', 0),
	(487, 'ACH_GuideEnrollmentLifetime', 'social', 8, 0, 80, 0, 80, '1', 0),
	(488, 'ACH_GuideEnrollmentLifetime', 'social', 9, 0, 90, 0, 90, '1', 0),
	(489, 'ACH_GuideEnrollmentLifetime', 'social', 10, 0, 100, 0, 100, '1', 0),
	(490, 'ACH_GuideEnrollmentLifetime', 'social', 11, 0, 200, 0, 110, '1', 0),
	(491, 'ACH_GuideEnrollmentLifetime', 'social', 12, 0, 300, 0, 120, '1', 0),
	(492, 'ACH_GuideEnrollmentLifetime', 'social', 13, 0, 300, 0, 130, '1', 0),
	(493, 'ACH_GuideEnrollmentLifetime', 'social', 14, 0, 300, 0, 140, '1', 0),
	(494, 'ACH_GuideEnrollmentLifetime', 'social', 15, 0, 300, 0, 150, '1', 0),
	(495, 'ACH_GuideEnrollmentLifetime', 'social', 16, 0, 300, 0, 160, '1', 0),
	(496, 'ACH_GuideEnrollmentLifetime', 'social', 17, 0, 300, 0, 170, '1', 0),
	(497, 'ACH_GuideEnrollmentLifetime', 'social', 18, 0, 300, 0, 180, '1', 0),
	(498, 'ACH_GuideEnrollmentLifetime', 'social', 19, 0, 300, 0, 190, '1', 0),
	(499, 'ACH_GuideEnrollmentLifetime', 'social', 20, 0, 300, 0, 200, '1', 0),
	(500, 'ACH_Forum', 'identity', 1, 0, 150, 0, 5, '1', 0),
	(501, 'ACH_Forum', 'identity', 2, 0, 150, 0, 15, '1', 0),
	(502, 'ACH_Forum', 'identity', 3, 0, 300, 0, 25, '1', 0),
	(503, 'ACH_Forum', 'identity', 4, 0, 300, 0, 50, '1', 0),
	(504, 'ACH_Forum', 'identity', 5, 0, 300, 0, 75, '1', 0),
	(505, 'ACH_Forum', 'identity', 6, 0, 300, 0, 100, '1', 0),
	(506, 'ACH_Forum', 'identity', 7, 0, 300, 0, 125, '1', 0),
	(507, 'ACH_Forum', 'identity', 8, 0, 300, 0, 150, '1', 0),
	(508, 'ACH_Forum', 'identity', 9, 0, 300, 0, 175, '1', 0),
	(509, 'ACH_Forum', 'identity', 10, 0, 300, 0, 200, '1', 0),
	(510, 'ACH_LTDPurchased', 'explore', 1, 0, 250, 0, 5, '1', 0),
	(511, 'ACH_LTDPurchased', 'explore', 2, 0, 250, 0, 10, '1', 0),
	(512, 'ACH_LTDPurchased', 'explore', 3, 0, 250, 0, 15, '1', 0),
	(513, 'ACH_LTDPurchased', 'explore', 4, 0, 250, 0, 20, '1', 0),
	(514, 'ACH_LTDPurchased', 'explore', 5, 0, 250, 0, 25, '1', 0),
	(515, 'ACH_LTDPurchased', 'explore', 6, 0, 250, 0, 30, '1', 0),
	(516, 'ACH_LTDPurchased', 'explore', 7, 0, 250, 0, 35, '1', 0),
	(517, 'ACH_LTDPurchased', 'explore', 8, 0, 250, 0, 40, '1', 0),
	(518, 'ACH_LTDPurchased', 'explore', 9, 0, 250, 0, 45, '1', 0),
	(519, 'ACH_LTDPurchased', 'explore', 10, 0, 350, 0, 50, '1', 0),
	(520, 'ACH_LTDPurchased', 'explore', 11, 0, 350, 0, 55, '1', 0),
	(521, 'ACH_LTDPurchased', 'explore', 12, 0, 350, 0, 60, '1', 0),
	(522, 'ACH_LTDPurchased', 'explore', 13, 0, 350, 0, 65, '1', 0),
	(523, 'ACH_LTDPurchased', 'explore', 14, 0, 350, 0, 70, '1', 0),
	(524, 'ACH_LTDPurchased', 'explore', 15, 0, 350, 0, 75, '1', 0),
	(525, 'ACH_LTDPurchased', 'explore', 16, 0, 350, 0, 80, '1', 0),
	(526, 'ACH_LTDPurchased', 'explore', 17, 0, 350, 0, 85, '1', 0),
	(527, 'ACH_LTDPurchased', 'explore', 18, 0, 350, 0, 90, '1', 0),
	(528, 'ACH_LTDPurchased', 'explore', 19, 0, 350, 0, 95, '1', 0),
	(529, 'ACH_LTDPurchased', 'explore', 20, 0, 350, 0, 100, '1', 0),
	(530, 'ACH_CrystalCracker', 'explore', 1, 0, 100, 0, 5, '1', 0),
	(531, 'ACH_CrystalCracker', 'explore', 2, 0, 100, 0, 10, '1', 0),
	(532, 'ACH_CrystalCracker', 'explore', 3, 0, 100, 0, 10, '1', 0),
	(533, 'ACH_CrystalCracker', 'explore', 4, 0, 100, 0, 10, '1', 0),
	(534, 'ACH_CrystalCracker', 'explore', 5, 0, 100, 0, 15, '1', 0),
	(535, 'ACH_CrystalCracker', 'explore', 6, 0, 100, 0, 15, '1', 0),
	(536, 'ACH_CrystalCracker', 'explore', 7, 0, 100, 0, 10, '1', 0),
	(537, 'ACH_CrystalCracker', 'explore', 8, 0, 100, 0, 10, '1', 0),
	(538, 'ACH_CrystalCracker', 'explore', 9, 0, 100, 0, 15, '1', 0),
	(539, 'ACH_CrystalCracker', 'explore', 10, 0, 100, 0, 50, '1', 0),
	(540, 'ACH_Crafting', 'explore', 1, 0, 100, 0, 5, '1', 0),
	(541, 'ACH_Crafting', 'explore', 2, 0, 100, 0, 10, '1', 0),
	(542, 'ACH_Crafting', 'explore', 3, 0, 100, 0, 10, '1', 0),
	(543, 'ACH_Crafting', 'explore', 4, 0, 100, 0, 10, '1', 0),
	(544, 'ACH_Crafting', 'explore', 5, 0, 100, 0, 15, '1', 0),
	(545, 'ACH_Crafting', 'explore', 6, 0, 100, 0, 15, '1', 0),
	(546, 'ACH_Crafting', 'explore', 7, 0, 100, 0, 10, '1', 0),
	(547, 'ACH_Crafting', 'explore', 8, 0, 100, 0, 10, '1', 0),
	(548, 'ACH_Crafting', 'explore', 9, 0, 100, 0, 15, '1', 0),
	(549, 'ACH_Crafting', 'explore', 10, 0, 100, 0, 50, '1', 0),
	(550, 'ACH_ViciousViking', 'explore', 1, 0, 100, 0, 5, '1', 0),
	(551, 'ACH_ViciousViking', 'explore', 2, 0, 100, 0, 10, '1', 0),
	(552, 'ACH_ViciousViking', 'explore', 3, 0, 100, 0, 10, '1', 0),
	(553, 'ACH_ViciousViking', 'explore', 4, 0, 100, 0, 10, '1', 0),
	(554, 'ACH_ViciousViking', 'explore', 5, 0, 100, 0, 15, '1', 0),
	(555, 'ACH_ViciousViking', 'explore', 6, 0, 100, 0, 15, '1', 0),
	(556, 'ACH_ViciousViking', 'explore', 7, 0, 100, 0, 10, '1', 0),
	(557, 'ACH_ViciousViking', 'explore', 8, 0, 100, 0, 10, '1', 0),
	(558, 'ACH_ViciousViking', 'explore', 9, 0, 100, 0, 15, '1', 0),
	(559, 'ACH_ViciousViking', 'explore', 10, 0, 100, 0, 50, '1', 0),
	(560, 'ACH_EggCracker', 'explore', 1, 0, 100, 0, 1, '1', 0),
	(561, 'ACH_EggCracker', 'explore', 2, 0, 100, 0, 4, '1', 0),
	(562, 'ACH_EggCracker', 'explore', 3, 0, 100, 0, 5, '1', 0),
	(563, 'ACH_EggCracker', 'explore', 4, 0, 100, 0, 10, '1', 0),
	(564, 'ACH_EggCracker', 'explore', 5, 0, 100, 0, 10, '1', 0),
	(565, 'ACH_EggCracker', 'explore', 6, 0, 100, 0, 15, '1', 0),
	(566, 'ACH_EggCracker', 'explore', 7, 0, 100, 0, 10, '1', 0),
	(567, 'ACH_EggCracker', 'explore', 8, 0, 100, 0, 10, '1', 0),
	(568, 'ACH_EggCracker', 'explore', 9, 0, 100, 0, 15, '1', 0),
	(569, 'ACH_EggCracker', 'explore', 10, 0, 100, 0, 25, '1', 0),
	(610, 'ACH_FILWinner', 'games', 1, 0, 100, 0, 1, '1', 0),
	(611, 'ACH_FILWinner', 'games', 2, 0, 100, 0, 15, '1', 0),
	(612, 'ACH_FILWinner', 'games', 3, 0, 100, 0, 30, '1', 0),
	(613, 'ACH_FILWinner', 'games', 4, 0, 100, 0, 50, '1', 0),
	(614, 'ACH_FILWinner', 'games', 5, 0, 100, 0, 70, '1', 0),
	(615, 'ACH_FILWinner', 'games', 6, 0, 100, 0, 100, '1', 0),
	(616, 'ACH_FILWinner', 'games', 7, 0, 100, 0, 125, '1', 0),
	(617, 'ACH_FILWinner', 'games', 8, 0, 100, 0, 150, '1', 0),
	(618, 'ACH_FILWinner', 'games', 9, 0, 100, 0, 175, '1', 0),
	(619, 'ACH_FILWinner', 'games', 10, 0, 100, 0, 200, '1', 0),
	(620, 'ACH_FILWinner', 'games', 11, 0, 100, 0, 225, '1', 0),
	(621, 'ACH_FILWinner', 'games', 12, 0, 100, 0, 250, '1', 0),
	(622, 'ACH_FILWinner', 'games', 13, 0, 100, 0, 275, '1', 0),
	(623, 'ACH_FILWinner', 'games', 14, 0, 100, 0, 300, '1', 0),
	(624, 'ACH_FILWinner', 'games', 15, 0, 100, 0, 325, '1', 0),
	(625, 'ACH_FILWinner', 'games', 16, 0, 100, 0, 350, '1', 0),
	(626, 'ACH_FILWinner', 'games', 17, 0, 100, 0, 375, '1', 0),
	(627, 'ACH_FILWinner', 'games', 18, 0, 100, 0, 400, '1', 0),
	(628, 'ACH_FILWinner', 'games', 19, 0, 100, 0, 425, '1', 0),
	(629, 'ACH_FILWinner', 'games', 20, 0, 100, 0, 450, '1', 0),
	(630, 'ACH_ChessWinner', 'games', 20, 0, 100, 0, 25, '1', 0),
	(631, 'ACH_ChessWinner', 'games', 19, 0, 100, 0, 25, '1', 0),
	(632, 'ACH_ChessWinner', 'games', 18, 0, 100, 0, 25, '1', 0),
	(633, 'ACH_ChessWinner', 'games', 17, 0, 100, 0, 25, '1', 0),
	(634, 'ACH_ChessWinner', 'games', 16, 0, 100, 0, 25, '1', 0),
	(635, 'ACH_ChessWinner', 'games', 15, 0, 100, 0, 25, '1', 0),
	(636, 'ACH_ChessWinner', 'games', 14, 0, 100, 0, 25, '1', 0),
	(637, 'ACH_ChessWinner', 'games', 13, 0, 100, 0, 25, '1', 0),
	(638, 'ACH_ChessWinner', 'games', 12, 0, 100, 0, 25, '1', 0),
	(639, 'ACH_ChessWinner', 'games', 11, 0, 100, 0, 25, '1', 0),
	(640, 'ACH_ChessWinner', 'games', 10, 0, 100, 0, 25, '1', 0),
	(641, 'ACH_ChessWinner', 'games', 9, 0, 100, 0, 25, '1', 0),
	(642, 'ACH_ChessWinner', 'games', 8, 0, 100, 0, 25, '1', 0),
	(643, 'ACH_ChessWinner', 'games', 7, 0, 100, 0, 25, '1', 0),
	(644, 'ACH_ChessWinner', 'games', 6, 0, 100, 0, 30, '1', 0),
	(645, 'ACH_ChessWinner', 'games', 5, 0, 100, 0, 20, '1', 0),
	(646, 'ACH_ChessWinner', 'games', 4, 0, 100, 0, 20, '1', 0),
	(647, 'ACH_ChessWinner', 'games', 3, 0, 100, 0, 15, '1', 0),
	(648, 'ACH_ChessWinner', 'games', 2, 0, 100, 0, 14, '1', 0),
	(649, 'ACH_ChessWinner', 'games', 1, 0, 100, 0, 1, '1', 0),
	(650, 'ACH_ChessPlayed', 'games', 20, 0, 100, 0, 25, '1', 0),
	(651, 'ACH_ChessPlayed', 'games', 19, 0, 100, 0, 25, '1', 0),
	(652, 'ACH_ChessPlayed', 'games', 18, 0, 100, 0, 25, '1', 0),
	(653, 'ACH_ChessPlayed', 'games', 17, 0, 100, 0, 25, '1', 0),
	(654, 'ACH_ChessPlayed', 'games', 16, 0, 100, 0, 25, '1', 0),
	(655, 'ACH_ChessPlayed', 'games', 15, 0, 100, 0, 25, '1', 0),
	(656, 'ACH_ChessPlayed', 'games', 14, 0, 100, 0, 25, '1', 0),
	(657, 'ACH_ChessPlayed', 'games', 13, 0, 100, 0, 25, '1', 0),
	(658, 'ACH_ChessPlayed', 'games', 12, 0, 100, 0, 25, '1', 0),
	(659, 'ACH_ChessPlayed', 'games', 11, 0, 100, 0, 25, '1', 0),
	(660, 'ACH_ChessPlayed', 'games', 10, 0, 100, 0, 25, '1', 0),
	(661, 'ACH_ChessPlayed', 'games', 9, 0, 100, 0, 25, '1', 0),
	(662, 'ACH_ChessPlayed', 'games', 8, 0, 100, 0, 25, '1', 0),
	(663, 'ACH_ChessPlayed', 'games', 7, 0, 100, 0, 25, '1', 0),
	(664, 'ACH_ChessPlayed', 'games', 6, 0, 100, 0, 30, '1', 0),
	(665, 'ACH_ChessPlayed', 'games', 5, 0, 100, 0, 20, '1', 0),
	(666, 'ACH_ChessPlayed', 'games', 4, 0, 100, 0, 20, '1', 0),
	(667, 'ACH_ChessPlayed', 'games', 3, 0, 100, 0, 15, '1', 0),
	(668, 'ACH_ChessPlayed', 'games', 2, 0, 100, 0, 14, '1', 0),
	(669, 'ACH_ChessPlayed', 'games', 1, 0, 100, 0, 1, '1', 0),
	(670, 'ACH_GuideTourGiver', 'social', 1, 0, 10, 0, 1, '1', 0),
	(671, 'ACH_GuideTourGiver', 'social', 2, 0, 20, 0, 9, '1', 0),
	(672, 'ACH_GuideTourGiver', 'social', 3, 0, 30, 0, 25, '1', 0),
	(673, 'ACH_GuideTourGiver', 'social', 4, 0, 40, 0, 30, '1', 0),
	(674, 'ACH_GuideTourGiver', 'social', 5, 0, 50, 0, 50, '1', 0),
	(675, 'ACH_GuideTourGiver', 'social', 6, 0, 60, 0, 60, '1', 0),
	(676, 'ACH_GuideTourGiver', 'social', 7, 0, 70, 0, 70, '1', 0),
	(677, 'ACH_GuideTourGiver', 'social', 8, 0, 80, 0, 80, '1', 0),
	(678, 'ACH_GuideTourGiver', 'social', 9, 0, 90, 0, 90, '1', 0),
	(679, 'ACH_GuideTourGiver', 'social', 10, 0, 100, 0, 100, '1', 0),
	(680, 'ACH_GuideRecommendation', 'social', 1, 0, 10, 0, 1, '1', 0),
	(681, 'ACH_GuideRecommendation', 'social', 2, 0, 20, 0, 9, '1', 0),
	(682, 'ACH_GuideRecommendation', 'social', 3, 0, 30, 0, 25, '1', 0),
	(683, 'ACH_GuideRecommendation', 'social', 4, 0, 40, 0, 30, '1', 0),
	(684, 'ACH_GuideRecommendation', 'social', 5, 0, 50, 0, 50, '1', 0),
	(685, 'ACH_GuideRecommendation', 'social', 6, 0, 60, 0, 60, '1', 0),
	(686, 'ACH_GuideRecommendation', 'social', 7, 0, 70, 0, 70, '1', 0),
	(687, 'ACH_GuideRecommendation', 'social', 8, 0, 80, 0, 80, '1', 0),
	(688, 'ACH_GuideRecommendation', 'social', 9, 0, 90, 0, 90, '1', 0),
	(689, 'ACH_GuideRecommendation', 'social', 10, 0, 100, 0, 100, '1', 0),
	(690, 'ACH_PetRespectReceiver', 'social', 10, 0, 20, 0, 500, '1', 0),
	(691, 'ACH_PetRespectReceiver', 'social', 9, 0, 15, 0, 400, '1', 0),
	(692, 'ACH_PetRespectReceiver', 'social', 8, 0, 10, 0, 350, '1', 0),
	(693, 'ACH_PetRespectReceiver', 'social', 7, 0, 10, 0, 300, '1', 0),
	(694, 'ACH_PetRespectReceiver', 'social', 6, 0, 10, 0, 250, '1', 0),
	(695, 'ACH_PetRespectReceiver', 'social', 5, 0, 10, 0, 200, '1', 0),
	(696, 'ACH_PetRespectReceiver', 'social', 4, 0, 10, 0, 150, '1', 0),
	(697, 'ACH_PetRespectReceiver', 'social', 3, 0, 10, 0, 100, '1', 0),
	(698, 'ACH_PetRespectReceiver', 'social', 2, 0, 10, 0, 50, '1', 0),
	(699, 'ACH_PetRespectReceiver', 'social', 1, 0, 10, 0, 10, '1', 0),
	(700, 'ACH_Farmer', 'games', 10, 0, 20, 0, 30, '1', 0),
	(701, 'ACH_Farmer', 'games', 9, 0, 15, 0, 20, '1', 0),
	(702, 'ACH_Farmer', 'games', 8, 0, 10, 0, 20, '1', 0),
	(703, 'ACH_Farmer', 'games', 7, 0, 10, 0, 20, '1', 0),
	(704, 'ACH_Farmer', 'games', 6, 0, 10, 0, 20, '1', 0),
	(705, 'ACH_Farmer', 'games', 5, 0, 10, 0, 20, '1', 0),
	(706, 'ACH_Farmer', 'games', 4, 0, 10, 0, 10, '1', 0),
	(707, 'ACH_Farmer', 'games', 3, 0, 10, 0, 5, '1', 0),
	(708, 'ACH_Farmer', 'games', 2, 0, 10, 0, 4, '1', 0),
	(709, 'ACH_Farmer', 'games', 1, 0, 10, 0, 1, '1', 0),
	(710, 'ACH_SoKWinner', 'games', 1, 0, 100, 0, 1, '1', 0),
	(711, 'ACH_SoKWinner', 'games', 2, 0, 100, 0, 15, '1', 0),
	(712, 'ACH_SoKWinner', 'games', 3, 0, 100, 0, 30, '1', 0),
	(713, 'ACH_SoKWinner', 'games', 4, 0, 100, 0, 50, '1', 0),
	(714, 'ACH_SoKWinner', 'games', 5, 0, 100, 0, 70, '1', 0),
	(715, 'ACH_SoKWinner', 'games', 6, 0, 100, 0, 100, '1', 0),
	(716, 'ACH_SoKWinner', 'games', 7, 0, 100, 0, 125, '1', 0),
	(717, 'ACH_SoKWinner', 'games', 8, 0, 100, 0, 150, '1', 0),
	(718, 'ACH_SoKWinner', 'games', 9, 0, 100, 0, 175, '1', 0),
	(719, 'ACH_SoKWinner', 'games', 10, 0, 100, 0, 200, '1', 0),
	(720, 'ACH_SoKWinner', 'games', 11, 0, 100, 0, 225, '1', 0),
	(721, 'ACH_SoKWinner', 'games', 12, 0, 100, 0, 250, '1', 0),
	(722, 'ACH_SoKWinner', 'games', 13, 0, 100, 0, 275, '1', 0),
	(723, 'ACH_SoKWinner', 'games', 14, 0, 100, 0, 300, '1', 0),
	(724, 'ACH_SoKWinner', 'games', 15, 0, 100, 0, 325, '1', 0),
	(725, 'ACH_SoKWinner', 'games', 16, 0, 100, 0, 350, '1', 0),
	(726, 'ACH_SoKWinner', 'games', 17, 0, 100, 0, 375, '1', 0),
	(727, 'ACH_SoKWinner', 'games', 18, 0, 100, 0, 400, '1', 0),
	(728, 'ACH_SoKWinner', 'games', 19, 0, 100, 0, 425, '1', 0),
	(729, 'ACH_SoKSaver', 'games', 1, 0, 100, 0, 1, '1', 0),
	(730, 'ACH_SoKSaver', 'games', 2, 0, 100, 0, 15, '1', 0),
	(731, 'ACH_SoKSaver', 'games', 3, 0, 100, 0, 30, '1', 0),
	(732, 'ACH_SoKSaver', 'games', 4, 0, 100, 0, 50, '1', 0),
	(733, 'ACH_SoKSaver', 'games', 5, 0, 100, 0, 70, '1', 0),
	(734, 'ACH_SoKSaver', 'games', 6, 0, 100, 0, 100, '1', 0),
	(735, 'ACH_SoKSaver', 'games', 7, 0, 100, 0, 125, '1', 0),
	(736, 'ACH_SoKSaver', 'games', 8, 0, 100, 0, 150, '1', 0),
	(737, 'ACH_SoKSaver', 'games', 9, 0, 100, 0, 175, '1', 0),
	(738, 'ACH_SoKSaver', 'games', 10, 0, 100, 0, 200, '1', 0),
	(739, 'ACH_SoKSaver', 'games', 11, 0, 100, 0, 225, '1', 0),
	(740, 'ACH_SoKSaver', 'games', 12, 0, 100, 0, 250, '1', 0),
	(741, 'ACH_SoKSaver', 'games', 13, 0, 100, 0, 275, '1', 0),
	(742, 'ACH_SoKSaver', 'games', 14, 0, 100, 0, 300, '1', 0),
	(743, 'ACH_SoKSaver', 'games', 15, 0, 100, 0, 325, '1', 0),
	(744, 'ACH_SoKSaver', 'games', 16, 0, 100, 0, 350, '1', 0),
	(745, 'ACH_SoKSaver', 'games', 17, 0, 100, 0, 375, '1', 0),
	(746, 'ACH_SoKSaver', 'games', 18, 0, 100, 0, 400, '1', 0),
	(747, 'ACH_SoKSaver', 'games', 19, 0, 100, 0, 425, '1', 0),
	(748, 'ACH_SoKSaver', 'games', 20, 0, 100, 0, 450, '1', 0),
	(749, 'ACH_SoKKiller', 'games', 1, 0, 100, 0, 1, '1', 0),
	(750, 'ACH_SoKKiller', 'games', 2, 0, 100, 0, 15, '1', 0),
	(751, 'ACH_SoKKiller', 'games', 3, 0, 100, 0, 30, '1', 0),
	(752, 'ACH_SoKKiller', 'games', 4, 0, 100, 0, 50, '1', 0),
	(753, 'ACH_SoKKiller', 'games', 5, 0, 100, 0, 70, '1', 0),
	(754, 'ACH_SoKKiller', 'games', 6, 0, 100, 0, 100, '1', 0),
	(755, 'ACH_SoKKiller', 'games', 7, 0, 100, 0, 125, '1', 0),
	(756, 'ACH_SoKKiller', 'games', 8, 0, 100, 0, 150, '1', 0),
	(757, 'ACH_SoKKiller', 'games', 9, 0, 100, 0, 175, '1', 0),
	(758, 'ACH_SoKKiller', 'games', 10, 0, 100, 0, 200, '1', 0),
	(759, 'ACH_SoKKiller', 'games', 11, 0, 100, 0, 225, '1', 0),
	(760, 'ACH_SoKKiller', 'games', 12, 0, 100, 0, 250, '1', 0),
	(761, 'ACH_SoKKiller', 'games', 13, 0, 100, 0, 275, '1', 0),
	(762, 'ACH_SoKKiller', 'games', 14, 0, 100, 0, 300, '1', 0),
	(763, 'ACH_SoKKiller', 'games', 15, 0, 100, 0, 325, '1', 0),
	(764, 'ACH_SoKKiller', 'games', 16, 0, 100, 0, 350, '1', 0),
	(765, 'ACH_SoKKiller', 'games', 17, 0, 100, 0, 375, '1', 0),
	(766, 'ACH_SoKKiller', 'games', 18, 0, 100, 0, 400, '1', 0),
	(767, 'ACH_SoKKiller', 'games', 19, 0, 100, 0, 425, '1', 0),
	(768, 'ACH_SoKKiller', 'games', 20, 0, 100, 0, 450, '1', 0),
	(769, 'ACH_NotesReceived', 'room_builder', 1, 0, 50, 0, 1, '1', 0),
	(770, 'ACH_NotesReceived', 'room_builder', 2, 0, 50, 0, 25, '1', 0),
	(771, 'ACH_NotesReceived', 'room_builder', 3, 0, 100, 0, 50, '1', 0),
	(772, 'ACH_NotesReceived', 'room_builder', 4, 0, 100, 0, 100, '1', 0),
	(773, 'ACH_NotesReceived', 'room_builder', 5, 0, 100, 0, 250, '1', 0),
	(774, 'ACH_NotesReceived', 'room_builder', 6, 0, 100, 0, 350, '1', 0),
	(775, 'ACH_NotesReceived', 'room_builder', 7, 0, 100, 0, 500, '1', 0),
	(776, 'ACH_NotesReceived', 'room_builder', 8, 0, 100, 0, 750, '1', 0),
	(777, 'ACH_NotesReceived', 'room_builder', 9, 0, 100, 0, 1000, '1', 0),
	(778, 'ACH_NotesReceived', 'room_builder', 10, 0, 200, 0, 2000, '1', 0),
	(779, 'ACH_NotesLeft', 'room_builder', 1, 0, 50, 0, 1, '1', 0),
	(780, 'ACH_NotesLeft', 'room_builder', 2, 0, 50, 0, 25, '1', 0),
	(781, 'ACH_NotesLeft', 'room_builder', 3, 0, 100, 0, 50, '1', 0),
	(782, 'ACH_NotesLeft', 'room_builder', 4, 0, 100, 0, 100, '1', 0),
	(783, 'ACH_NotesLeft', 'room_builder', 5, 0, 100, 0, 250, '1', 0),
	(784, 'ACH_NotesLeft', 'room_builder', 6, 0, 100, 0, 350, '1', 0),
	(785, 'ACH_NotesLeft', 'room_builder', 7, 0, 100, 0, 500, '1', 0),
	(786, 'ACH_NotesLeft', 'room_builder', 8, 0, 100, 0, 750, '1', 0),
	(787, 'ACH_NotesLeft', 'room_builder', 9, 0, 100, 0, 1000, '1', 0),
	(788, 'ACH_NotesLeft', 'room_builder', 10, 0, 200, 0, 2000, '1', 0),
	(789, 'ACH_snowBoardBuild', 'room_builder', 1, 0, 50, 0, 1, '1', 0),
	(790, 'ACH_snowBoardBuild', 'room_builder', 2, 0, 50, 0, 5, '1', 0),
	(791, 'ACH_snowBoardBuild', 'room_builder', 3, 0, 100, 0, 25, '1', 0),
	(792, 'ACH_snowBoardBuild', 'room_builder', 4, 0, 100, 0, 50, '1', 0),
	(793, 'ACH_snowBoardBuild', 'room_builder', 5, 0, 100, 0, 100, '1', 0),
	(794, 'ACH_FireworksCharger', 'explore', 1, 0, 25, 0, 1, '1', 0),
	(795, 'ACH_FireworksCharger', 'explore', 2, 0, 25, 0, 25, '1', 0),
	(796, 'ACH_FireworksCharger', 'explore', 3, 0, 25, 0, 50, '1', 0),
	(797, 'ACH_FireworksCharger', 'explore', 4, 0, 25, 0, 75, '1', 0),
	(798, 'ACH_FireworksCharger', 'explore', 5, 0, 50, 0, 100, '1', 0),
	(799, 'ACH_FireworksCharger', 'explore', 6, 0, 25, 0, 125, '1', 0),
	(800, 'ACH_FireworksCharger', 'explore', 7, 0, 25, 0, 150, '1', 0),
	(801, 'ACH_FireworksCharger', 'explore', 8, 0, 25, 0, 175, '1', 0),
	(802, 'ACH_FireworksCharger', 'explore', 9, 0, 25, 0, 200, '1', 0),
	(803, 'ACH_FireworksCharger', 'explore', 10, 0, 100, 0, 250, '1', 0),
	(804, 'ACH_EsA', 'games', 1, 0, 50, 0, 1, '1', 0),
	(805, 'ACH_EsA', 'games', 2, 0, 50, 0, 25, '1', 0),
	(806, 'ACH_EsA', 'games', 3, 0, 50, 0, 50, '1', 0),
	(807, 'ACH_EsA', 'games', 4, 0, 50, 0, 75, '1', 0),
	(808, 'ACH_EsA', 'games', 5, 0, 50, 0, 100, '1', 0),
	(809, 'ACH_EsA', 'games', 6, 0, 50, 0, 125, '1', 0),
	(810, 'ACH_EsA', 'games', 7, 0, 50, 0, 150, '1', 0),
	(811, 'ACH_EsA', 'games', 8, 0, 50, 0, 200, '1', 0),
	(812, 'ACH_EsA', 'games', 9, 0, 50, 0, 250, '1', 0),
	(813, 'ACH_EsA', 'games', 10, 0, 100, 0, 300, '1', 0),
	(814, 'ACH_EsA', 'games', 11, 0, 100, 0, 350, '1', 0),
	(815, 'ACH_EsA', 'games', 12, 0, 100, 0, 400, '1', 0),
	(816, 'ACH_EsA', 'games', 13, 0, 100, 0, 450, '1', 0),
	(817, 'ACH_EsA', 'games', 14, 0, 100, 0, 500, '1', 0),
	(818, 'ACH_EsA', 'games', 15, 0, 100, 0, 550, '1', 0),
	(819, 'ACH_EsA', 'games', 16, 0, 100, 0, 600, '1', 0),
	(820, 'ACH_EsA', 'games', 17, 0, 100, 0, 650, '1', 0),
	(821, 'ACH_EsA', 'games', 18, 0, 100, 0, 700, '1', 0),
	(822, 'ACH_EsA', 'games', 19, 0, 100, 0, 850, '1', 0),
	(823, 'ACH_EsA', 'games', 20, 0, 300, 0, 1000, '1', 0),
	(824, 'ACH_FreezePlayer', 'games', 1, 0, 50, 0, 1, '1', 0),
	(825, 'ACH_FreezePlayer', 'games', 2, 0, 50, 0, 25, '1', 0),
	(826, 'ACH_FreezePlayer', 'games', 3, 0, 50, 0, 50, '1', 0),
	(827, 'ACH_FreezePlayer', 'games', 4, 0, 50, 0, 75, '1', 0),
	(828, 'ACH_FreezePlayer', 'games', 5, 0, 50, 0, 100, '1', 0),
	(829, 'ACH_FreezePlayer', 'games', 6, 0, 50, 0, 125, '1', 0),
	(830, 'ACH_FreezePlayer', 'games', 7, 0, 50, 0, 150, '1', 0),
	(831, 'ACH_FreezePlayer', 'games', 8, 0, 50, 0, 200, '1', 0),
	(832, 'ACH_FreezePlayer', 'games', 9, 0, 50, 0, 250, '1', 0),
	(833, 'ACH_FreezePlayer', 'games', 10, 0, 100, 0, 300, '1', 0),
	(834, 'ACH_FreezePlayer', 'games', 11, 0, 100, 0, 350, '1', 0),
	(835, 'ACH_FreezePlayer', 'games', 12, 0, 100, 0, 400, '1', 0),
	(836, 'ACH_FreezePlayer', 'games', 13, 0, 100, 0, 450, '1', 0),
	(837, 'ACH_FreezePlayer', 'games', 14, 0, 100, 0, 500, '1', 0),
	(838, 'ACH_FreezePlayer', 'games', 15, 0, 100, 0, 550, '1', 0),
	(839, 'ACH_FreezePlayer', 'games', 16, 0, 100, 0, 600, '1', 0),
	(840, 'ACH_FreezePlayer', 'games', 17, 0, 100, 0, 650, '1', 0),
	(841, 'ACH_FreezePlayer', 'games', 18, 0, 100, 0, 700, '1', 0),
	(842, 'ACH_FreezePlayer', 'games', 19, 0, 100, 0, 850, '1', 0),
	(843, 'ACH_FreezePlayer', 'games', 20, 0, 300, 0, 1000, '1', 0),
	(844, 'ACH_FreezePowerUp', 'games', 1, 0, 50, 0, 1, '1', 0),
	(845, 'ACH_FreezePowerUp', 'games', 2, 0, 50, 0, 25, '1', 0),
	(846, 'ACH_FreezePowerUp', 'games', 3, 0, 50, 0, 50, '1', 0),
	(847, 'ACH_FreezePowerUp', 'games', 4, 0, 50, 0, 75, '1', 0),
	(848, 'ACH_FreezePowerUp', 'games', 5, 0, 50, 0, 100, '1', 0),
	(849, 'ACH_FreezePowerUp', 'games', 6, 0, 50, 0, 125, '1', 0),
	(850, 'ACH_FreezePowerUp', 'games', 7, 0, 50, 0, 150, '1', 0),
	(851, 'ACH_FreezePowerUp', 'games', 8, 0, 50, 0, 200, '1', 0),
	(852, 'ACH_FreezePowerUp', 'games', 9, 0, 50, 0, 250, '1', 0),
	(853, 'ACH_FreezePowerUp', 'games', 10, 0, 100, 0, 300, '1', 0),
	(854, 'ACH_FreezePowerUp', 'games', 11, 0, 100, 0, 350, '1', 0),
	(855, 'ACH_FreezePowerUp', 'games', 12, 0, 100, 0, 400, '1', 0),
	(856, 'ACH_FreezePowerUp', 'games', 13, 0, 100, 0, 450, '1', 0),
	(857, 'ACH_FreezePowerUp', 'games', 14, 0, 100, 0, 500, '1', 0),
	(858, 'ACH_FreezePowerUp', 'games', 15, 0, 100, 0, 550, '1', 0),
	(859, 'ACH_FreezePowerUp', 'games', 16, 0, 100, 0, 600, '1', 0),
	(860, 'ACH_FreezePowerUp', 'games', 17, 0, 100, 0, 650, '1', 0),
	(861, 'ACH_FreezePowerUp', 'games', 18, 0, 100, 0, 700, '1', 0),
	(862, 'ACH_FreezePowerUp', 'games', 19, 0, 100, 0, 850, '1', 0),
	(863, 'ACH_FreezePowerUp', 'games', 20, 0, 300, 0, 1000, '1', 0),
	(864, 'ACH_FreezeWinner', 'games', 1, 0, 50, 0, 1, '1', 0),
	(865, 'ACH_FreezeWinner', 'games', 2, 0, 50, 0, 25, '1', 0),
	(866, 'ACH_FreezeWinner', 'games', 3, 0, 50, 0, 50, '1', 0),
	(867, 'ACH_FreezeWinner', 'games', 4, 0, 50, 0, 75, '1', 0),
	(868, 'ACH_FreezeWinner', 'games', 5, 0, 50, 0, 100, '1', 0),
	(869, 'ACH_FreezeWinner', 'games', 6, 0, 50, 0, 125, '1', 0),
	(870, 'ACH_FreezeWinner', 'games', 7, 0, 50, 0, 150, '1', 0),
	(871, 'ACH_FreezeWinner', 'games', 8, 0, 50, 0, 200, '1', 0),
	(872, 'ACH_FreezeWinner', 'games', 9, 0, 50, 0, 250, '1', 0),
	(873, 'ACH_FreezeWinner', 'games', 10, 0, 100, 0, 300, '1', 0),
	(874, 'ACH_FreezeWinner', 'games', 11, 0, 100, 0, 350, '1', 0),
	(875, 'ACH_FreezeWinner', 'games', 12, 0, 100, 0, 400, '1', 0),
	(876, 'ACH_FreezeWinner', 'games', 13, 0, 100, 0, 450, '1', 0),
	(877, 'ACH_FreezeWinner', 'games', 14, 0, 100, 0, 500, '1', 0),
	(878, 'ACH_FreezeWinner', 'games', 15, 0, 100, 0, 550, '1', 0),
	(879, 'ACH_FreezeWinner', 'games', 16, 0, 100, 0, 600, '1', 0),
	(880, 'ACH_FreezeWinner', 'games', 17, 0, 100, 0, 650, '1', 0),
	(881, 'ACH_FreezeWinner', 'games', 18, 0, 100, 0, 700, '1', 0),
	(882, 'ACH_FreezeWinner', 'games', 19, 0, 100, 0, 850, '1', 0),
	(883, 'ACH_FreezeWinner', 'games', 20, 0, 300, 0, 1000, '1', 0),
	(884, 'ACH_FreezeQuestCompleted', 'games', 1, 0, 50, 0, 1, '1', 0),
	(885, 'ACH_FreezeQuestCompleted', 'games', 2, 0, 50, 0, 25, '1', 0),
	(886, 'ACH_FreezeQuestCompleted', 'games', 3, 0, 50, 0, 50, '1', 0),
	(887, 'ACH_FreezeQuestCompleted', 'games', 4, 0, 50, 0, 75, '1', 0),
	(888, 'ACH_FreezeQuestCompleted', 'games', 5, 0, 50, 0, 100, '1', 0),
	(889, 'ACH_FreezeQuestCompleted', 'games', 6, 0, 50, 0, 125, '1', 0),
	(890, 'ACH_FreezeQuestCompleted', 'games', 7, 0, 50, 0, 150, '1', 0),
	(891, 'ACH_FreezeQuestCompleted', 'games', 8, 0, 50, 0, 200, '1', 0),
	(892, 'ACH_FreezeQuestCompleted', 'games', 9, 0, 50, 0, 250, '1', 0),
	(893, 'ACH_FreezeQuestCompleted', 'games', 10, 0, 100, 0, 300, '1', 0),
	(894, 'ACH_HabboRoue', 'games', 1, 0, 10, 0, 1, '1', 0),
	(895, 'ACH_HabboRoue', 'games', 2, 0, 10, 0, 25, '1', 0),
	(896, 'ACH_HabboRoue', 'games', 3, 0, 10, 0, 50, '1', 0),
	(897, 'ACH_HabboRoue', 'games', 4, 0, 10, 0, 75, '1', 0),
	(898, 'ACH_HabboRoue', 'games', 5, 0, 50, 0, 100, '1', 0),
	(899, 'ACH_HabboName', 'identity', 1, 0, 50, 0, 1, '1', 0),
	(900, 'ACH_KissHabbo', 'social', 1, 0, 10, 0, 1, '1', 0),
	(901, 'ACH_KissHabbo', 'social', 2, 0, 10, 0, 5, '1', 0),
	(902, 'ACH_KissHabbo', 'social', 3, 0, 10, 0, 10, '1', 0),
	(903, 'ACH_KissHabbo', 'social', 4, 0, 10, 0, 25, '1', 0),
	(904, 'ACH_KissHabbo', 'social', 5, 0, 10, 0, 50, '1', 0),
	(905, 'ACH_KissHabbo', 'social', 6, 0, 10, 0, 75, '1', 0),
	(906, 'ACH_KissHabbo', 'social', 7, 0, 10, 0, 100, '1', 0),
	(907, 'ACH_KissHabbo', 'social', 8, 0, 50, 0, 125, '1', 0),
	(908, 'ACH_KissHabbo', 'social', 9, 0, 50, 0, 150, '1', 0),
	(909, 'ACH_KissHabbo', 'social', 10, 0, 100, 0, 200, '1', 0),
	(913, 'ACH_AnimationRanking', 'games', 20, 0, 300, 0, 1000, '1', 0),
	(912, 'ACH_AnimationRanking', 'games', 19, 0, 100, 0, 850, '1', 0),
	(911, 'ACH_AnimationRanking', 'games', 18, 0, 100, 0, 700, '1', 0),
	(910, 'ACH_AnimationRanking', 'games', 17, 0, 100, 0, 650, '1', 0),
	(914, 'ACH_AnimationRanking', 'games', 16, 0, 100, 0, 600, '1', 0),
	(915, 'ACH_AnimationRanking', 'games', 15, 0, 100, 0, 550, '1', 0),
	(916, 'ACH_AnimationRanking', 'games', 14, 0, 100, 0, 500, '1', 0),
	(917, 'ACH_AnimationRanking', 'games', 13, 0, 100, 0, 450, '1', 0),
	(918, 'ACH_AnimationRanking', 'games', 12, 0, 100, 0, 400, '1', 0),
	(919, 'ACH_AnimationRanking', 'games', 11, 0, 100, 0, 350, '1', 0),
	(920, 'ACH_AnimationRanking', 'games', 10, 0, 100, 0, 300, '1', 0),
	(921, 'ACH_AnimationRanking', 'games', 9, 0, 50, 0, 250, '1', 0),
	(923, 'ACH_AnimationRanking', 'games', 8, 0, 50, 0, 200, '1', 0),
	(924, 'ACH_AnimationRanking', 'games', 7, 0, 50, 0, 150, '1', 0),
	(925, 'ACH_AnimationRanking', 'games', 6, 0, 50, 0, 125, '1', 0),
	(926, 'ACH_AnimationRanking', 'games', 5, 0, 50, 0, 100, '1', 0),
	(927, 'ACH_AnimationRanking', 'games', 4, 0, 50, 0, 75, '1', 0),
	(928, 'ACH_AnimationRanking', 'games', 3, 0, 50, 0, 50, '1', 0),
	(929, 'ACH_AnimationRanking', 'games', 2, 0, 50, 0, 25, '1', 0),
	(930, 'ACH_AnimationRanking', 'games', 1, 0, 50, 0, 1, '1', 0),
	(961, 'ACH_ThreatPlayer', 'explore', 1, 0, 10, 0, 1, '1', 0),
	(962, 'ACH_ThreatPlayer', 'explore', 2, 0, 10, 0, 5, '1', 0),
	(963, 'ACH_ThreatPlayer', 'explore', 3, 0, 10, 0, 10, '1', 0),
	(964, 'ACH_ThreatPlayer', 'explore', 4, 0, 10, 0, 25, '1', 0),
	(965, 'ACH_ThreatPlayer', 'explore', 5, 1, 10, 0, 50, '1', 0),
	(966, 'ACH_ThreatPlayer', 'explore', 6, 0, 10, 0, 60, '1', 0),
	(967, 'ACH_ThreatPlayer', 'explore', 7, 0, 10, 0, 80, '1', 0),
	(968, 'ACH_ThreatPlayer', 'explore', 8, 0, 50, 0, 100, '1', 0),
	(969, 'ACH_ThreatPlayer', 'explore', 9, 0, 50, 0, 125, '1', 0),
	(970, 'ACH_ThreatPlayer', 'explore', 10, 0, 100, 0, 150, '1', 0),
	(1118, 'ACH_Xmascrystal', 'explore', 1, 0, 5, 0, 1, '1', 0),
	(1117, 'ACH_Xmascrystal', 'explore', 2, 0, 10, 0, 4, '1', 0),
	(1116, 'ACH_Xmascrystal', 'explore', 3, 0, 15, 0, 8, '1', 0),
	(1115, 'ACH_Xmascrystal', 'explore', 4, 0, 20, 0, 12, '1', 0),
	(1114, 'ACH_Xmascrystal', 'explore', 5, 0, 20, 0, 16, '1', 0),
	(1113, 'ACH_Xmascrystal', 'explore', 6, 0, 20, 0, 20, '1', 0),
	(1112, 'ACH_Xmascrystal', 'explore', 7, 0, 30, 0, 30, '1', 0),
	(1111, 'ACH_Xmascrystal', 'explore', 8, 0, 40, 0, 40, '1', 0),
	(1110, 'ACH_Xmascrystal', 'explore', 9, 0, 100, 0, 50, '1', 0),
	(1109, 'ACH_Xmascrystal', 'explore', 10, 0, 200, 0, 75, '1', 0),
	(1027, 'ACH_Spr', 'explore', 1, 5, 10, 0, 1, '1', 0),
	(1028, 'ACH_Spr', 'explore', 2, 0, 10, 0, 10, '1', 0),
	(1029, 'ACH_Spr', 'explore', 3, 0, 10, 0, 20, '1', 0),
	(1030, 'ACH_Spr', 'explore', 4, 0, 10, 0, 30, '1', 0),
	(1031, 'ACH_Spr', 'explore', 5, 0, 10, 0, 40, '1', 0),
	(1032, 'ACH_Spr', 'explore', 6, 0, 10, 0, 50, '1', 0),
	(1033, 'ACH_Spr', 'explore', 7, 0, 10, 0, 100, '1', 0),
	(1034, 'ACH_Spr', 'explore', 8, 0, 10, 0, 200, '1', 0),
	(1035, 'ACH_Spr', 'explore', 9, 0, 10, 0, 300, '1', 0),
	(1036, 'ACH_Spr', 'explore', 10, 5, 10, 0, 500, '1', 0),
	(1089, 'ACH_RPS', 'games', 10, 25, 100, 0, 500, '1', 1),
	(1090, 'ACH_RPS', 'games', 9, 20, 25, 0, 375, '1', 1),
	(1091, 'ACH_RPS', 'games', 8, 10, 25, 0, 250, '1', 1),
	(1092, 'ACH_RPS', 'games', 7, 10, 25, 0, 200, '1', 1),
	(1093, 'ACH_RPS', 'games', 6, 10, 25, 0, 150, '1', 1),
	(1094, 'ACH_RPS', 'games', 5, 10, 50, 0, 100, '1', 1),
	(1095, 'ACH_RPS', 'games', 4, 10, 25, 0, 75, '1', 1),
	(1096, 'ACH_RPS', 'games', 3, 10, 25, 0, 50, '1', 1),
	(1097, 'ACH_RPS', 'games', 2, 10, 25, 0, 25, '1', 1),
	(1098, 'ACH_RPS', 'games', 1, 10, 25, 0, 1, '1', 1),
	(1099, 'ACH_Hweensummon', 'explore', 1, 10, 10, 0, 1, '1', 0),
	(1100, 'ACH_Hweensummon', 'explore', 2, 10, 10, 0, 4, '1', 0),
	(1101, 'ACH_Hweensummon', 'explore', 3, 10, 10, 0, 6, '1', 0),
	(1102, 'ACH_Hweensummon', 'explore', 4, 10, 10, 0, 8, '1', 0),
	(1103, 'ACH_Hweensummon', 'explore', 5, 10, 10, 0, 10, '1', 0),
	(1104, 'ACH_Hweensummon', 'explore', 6, 10, 10, 0, 12, '1', 0),
	(1105, 'ACH_Hweensummon', 'explore', 7, 10, 10, 0, 14, '1', 0),
	(1106, 'ACH_Hweensummon', 'explore', 8, 10, 10, 0, 16, '1', 0),
	(1107, 'ACH_Hweensummon', 'explore', 9, 10, 10, 0, 18, '1', 0),
	(1108, 'ACH_Hweensummon', 'explore', 10, 10, 10, 0, 20, '1', 0);
/*!40000 ALTER TABLE `achievements` ENABLE KEYS */;

-- Dumping structure for table hylib.achievements_talents
DROP TABLE IF EXISTS `achievements_talents`;
CREATE TABLE IF NOT EXISTS `achievements_talents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) NOT NULL DEFAULT 0,
  `achievement_ids` varchar(100) NOT NULL DEFAULT '',
  `reward_items` varchar(100) NOT NULL DEFAULT '',
  `reward_perks` varchar(100) NOT NULL DEFAULT '',
  `reward_badges` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.achievements_talents: ~0 rows (approximately)
DELETE FROM `achievements_talents`;
/*!40000 ALTER TABLE `achievements_talents` DISABLE KEYS */;
INSERT INTO `achievements_talents` (`id`, `level`, `achievement_ids`, `reward_items`, `reward_perks`, `reward_badges`) VALUES
	(1, 1, 'ACH_PetRespectGiver,ACH_RespectEarned,ACH_Motto', '204', '', '');
/*!40000 ALTER TABLE `achievements_talents` ENABLE KEYS */;

-- Dumping structure for table hylib.admin_pass
DROP TABLE IF EXISTS `admin_pass`;
CREATE TABLE IF NOT EXISTS `admin_pass` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `admin_password` int(6) NOT NULL,
  `assigned_to` varchar(250) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.admin_pass: ~0 rows (approximately)
DELETE FROM `admin_pass`;
/*!40000 ALTER TABLE `admin_pass` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_pass` ENABLE KEYS */;

-- Dumping structure for table hylib.badge_definitions
DROP TABLE IF EXISTS `badge_definitions`;
CREATE TABLE IF NOT EXISTS `badge_definitions` (
  `code` varchar(35) NOT NULL,
  `required_right` varchar(25) NOT NULL DEFAULT '',
  PRIMARY KEY (`code`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.badge_definitions: ~0 rows (approximately)
DELETE FROM `badge_definitions`;
/*!40000 ALTER TABLE `badge_definitions` DISABLE KEYS */;
/*!40000 ALTER TABLE `badge_definitions` ENABLE KEYS */;

-- Dumping structure for table hylib.badge_month
DROP TABLE IF EXISTS `badge_month`;
CREATE TABLE IF NOT EXISTS `badge_month` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) NOT NULL,
  `units` int(50) NOT NULL,
  `position` int(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.badge_month: ~0 rows (approximately)
DELETE FROM `badge_month`;
/*!40000 ALTER TABLE `badge_month` DISABLE KEYS */;
/*!40000 ALTER TABLE `badge_month` ENABLE KEYS */;

-- Dumping structure for table hylib.badge_shop
DROP TABLE IF EXISTS `badge_shop`;
CREATE TABLE IF NOT EXISTS `badge_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `badge_id` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.badge_shop: ~0 rows (approximately)
DELETE FROM `badge_shop`;
/*!40000 ALTER TABLE `badge_shop` DISABLE KEYS */;
/*!40000 ALTER TABLE `badge_shop` ENABLE KEYS */;

-- Dumping structure for table hylib.badge_veri
DROP TABLE IF EXISTS `badge_veri`;
CREATE TABLE IF NOT EXISTS `badge_veri` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `username` varchar(250) NOT NULL,
  `badge_name` varchar(40) NOT NULL,
  `badge_desc` varchar(40) NOT NULL,
  `image` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `venta` enum('0','1') NOT NULL DEFAULT '0',
  `user_id` varchar(50) NOT NULL,
  `precio` int(99) NOT NULL DEFAULT 0,
  `upbadge` enum('0','1') NOT NULL DEFAULT '0',
  `group_name` text NOT NULL,
  `group_id` varchar(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.badge_veri: ~0 rows (approximately)
DELETE FROM `badge_veri`;
/*!40000 ALTER TABLE `badge_veri` DISABLE KEYS */;
/*!40000 ALTER TABLE `badge_veri` ENABLE KEYS */;

-- Dumping structure for table hylib.badge_verificado
DROP TABLE IF EXISTS `badge_verificado`;
CREATE TABLE IF NOT EXISTS `badge_verificado` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `venta` enum('0','1') NOT NULL DEFAULT '0',
  `user_id` varchar(50) NOT NULL,
  `precio` int(99) NOT NULL DEFAULT 0,
  `ventas` int(99) NOT NULL DEFAULT 0,
  `ventasT` int(99) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.badge_verificado: ~0 rows (approximately)
DELETE FROM `badge_verificado`;
/*!40000 ALTER TABLE `badge_verificado` DISABLE KEYS */;
/*!40000 ALTER TABLE `badge_verificado` ENABLE KEYS */;

-- Dumping structure for table hylib.bans
DROP TABLE IF EXISTS `bans`;
CREATE TABLE IF NOT EXISTS `bans` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` enum('ip','machine','trade','mute','user') DEFAULT NULL,
  `expire` int(20) DEFAULT 0 COMMENT '0 = perminent',
  `data` varchar(100) DEFAULT '',
  `reason` varchar(100) DEFAULT '',
  `added_by` int(11) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.bans: ~0 rows (approximately)
DELETE FROM `bans`;
/*!40000 ALTER TABLE `bans` DISABLE KEYS */;
/*!40000 ALTER TABLE `bans` ENABLE KEYS */;

-- Dumping structure for table hylib.battlepass_active
DROP TABLE IF EXISTS `battlepass_active`;
CREATE TABLE IF NOT EXISTS `battlepass_active` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `player_id` int(250) NOT NULL,
  `level` int(3) NOT NULL,
  `exp` int(100) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.battlepass_active: ~4 rows (approximately)
DELETE FROM `battlepass_active`;
/*!40000 ALTER TABLE `battlepass_active` DISABLE KEYS */;
INSERT INTO `battlepass_active` (`id`, `player_id`, `level`, `exp`) VALUES
	(1, 13, 3, 17),
	(2, 11, 4, 35),
	(3, 12, 2, 12),
	(4, 17, 4, 39);
/*!40000 ALTER TABLE `battlepass_active` ENABLE KEYS */;

-- Dumping structure for table hylib.battlepass_completed
DROP TABLE IF EXISTS `battlepass_completed`;
CREATE TABLE IF NOT EXISTS `battlepass_completed` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `player_id` int(250) NOT NULL,
  `mission` int(3) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.battlepass_completed: ~48 rows (approximately)
DELETE FROM `battlepass_completed`;
/*!40000 ALTER TABLE `battlepass_completed` DISABLE KEYS */;
INSERT INTO `battlepass_completed` (`id`, `player_id`, `mission`) VALUES
	(1, 1, 1);
/*!40000 ALTER TABLE `battlepass_completed` ENABLE KEYS */;

-- Dumping structure for table hylib.bots
DROP TABLE IF EXISTS `bots`;
CREATE TABLE IF NOT EXISTS `bots` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) DEFAULT 0,
  `owner` varchar(100) DEFAULT '',
  `room_id` int(11) DEFAULT 0,
  `name` varchar(100) DEFAULT 'Placeholder',
  `figure` varchar(255) DEFAULT '',
  `gender` enum('m','f') DEFAULT 'm',
  `motto` varchar(100) DEFAULT NULL,
  `x` int(11) DEFAULT 0,
  `y` int(11) DEFAULT 0,
  `z` double DEFAULT 0,
  `messages` text DEFAULT NULL,
  `automatic_chat` enum('1','0') DEFAULT '1',
  `chat_delay` int(11) DEFAULT 7,
  `mode` enum('default','relaxed') DEFAULT 'default',
  `type` enum('generic','waiter','spy','test') DEFAULT 'generic',
  `data` text DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.bots: ~2 rows (approximately)
DELETE FROM `bots`;
/*!40000 ALTER TABLE `bots` DISABLE KEYS */;
INSERT INTO `bots` (`id`, `owner_id`, `owner`, `room_id`, `name`, `figure`, `gender`, `motto`, `x`, `y`, `z`, `messages`, `automatic_chat`, `chat_delay`, `mode`, `type`, `data`) VALUES
	(1, 30, '', 18, 'Juanjo', 'sh-3016-62.hd-3092-10.ch-9078924-82.lg-3078-110.hr-841-61.ea-1404-110.fa-1201-62.ca-990000198-62-62', 'm', '', 8, 7, 0.10000000000000009, '[]', '1', 7, 'relaxed', 'generic', NULL);

-- Dumping structure for table hylib.catalog_clothing
DROP TABLE IF EXISTS `catalog_clothing`;
CREATE TABLE IF NOT EXISTS `catalog_clothing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) NOT NULL,
  `clothing_items` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `item_name` (`item_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.catalog_clothing: ~60 rows (approximately)
DELETE FROM `catalog_clothing`;
/*!40000 ALTER TABLE `catalog_clothing` DISABLE KEYS */;
INSERT INTO `catalog_clothing` (`id`, `item_name`, `clothing_items`) VALUES
	(1, 'clothing_squid', '3356'),
	(2, 'clothing_party1', '3362'),
	(3, 'clothing_meowtfit', '3331,3334,3335,3337,3338'),
	(4, 'clothing_geometricskirt', '3341'),
	(5, 'clothing_waistcoatsuit', '3327'),
	(6, 'clothing_gatsby', '3322'),
	(7, 'clothing_bow', '3358'),
	(8, 'clothing_baldy', '3357'),
	(9, 'clothing_wavy2', '3339'),
	(10, 'clothing_mutton1', '3345'),
	(11, 'clothing_straw2', '3347'),
	(12, 'clothing_fishhat', '3349'),
	(13, 'clothing_rolled_jeans', '3320'),
	(14, 'clothing_birdshoes', '3348'),
	(15, 'clothing_mermaidoutfit', '3332,3333'),
	(16, 'clothing_droopycollar', '3336,3340'),
	(17, 'clothing_headband', '3352'),
	(18, 'clothing_flowercrown', '3329'),
	(19, 'clothing_hheadphones', '3324'),
	(20, 'clothing_mutton2', '3346'),
	(21, 'clothing_floralskirt', '3355'),
	(22, 'clothing_nightvision', '3318'),
	(23, 'clothing_mockymouse', '3359,3360,3361'),
	(24, 'clothing_xmas1', '3321'),
	(25, 'clothing_shavedside', '3325'),
	(26, 'clothing_geometricjumper', '3342'),
	(27, 'clothing_beard1', '3344'),
	(28, 'clothing_beads', '3343'),
	(29, 'clothing_twotonecardi', '3326'),
	(30, 'clothing_animalprint', '3353'),
	(31, 'clothing_party2', '3363'),
	(32, 'clothing_tshirt_rolled', '3323,335'),
	(33, 'clothing_rippedjeans', '3328'),
	(34, 'clothing_shoestights', '3354'),
	(35, 'clothing_kimono1', '3366,3367,3368,3369,3364'),
	(36, 'clothing_kimono2', '3365,3371,3372,3370'),
	(37, 'clothing_cyleather', '3373,3374'),
	(38, 'clothing_cystrapboots', '3383'),
	(39, 'clothing_cyphones', '3379'),
	(40, 'clothing_cyhair', '3377'),
	(41, 'clothing_cymask', '3378'),
	(42, 'clothing_cycircuit', '3385'),
	(43, 'clothing_cyhood', '3382'),
	(44, 'clothing_cyskirt', '3387'),
	(45, 'clothing_goggles', '3376'),
	(46, 'clothing_cystraphood', '3380,3381,3382,3383,3384'),
	(47, 'clothing_cystrappants', '3384'),
	(48, 'clothing_cyboots', '3375'),
	(49, 'clothing_cyzipped', ''),
	(50, 'clothing_cygirl', '3373, 3374, 3375,3376,3377,3387'),
	(51, 'clothing_cyglass', '3388'),
	(52, 'clothing_cyquif', '3386'),
	(53, 'clothing_bss_ckonghead', '901748'),
	(54, 'clothing_bss_darkangelwings', '901764'),
	(55, 'clothing_bss_gothiccoat', '901763'),
	(56, 'clothing_bss_gothicflowerhorns', '901755'),
	(57, 'clothing_bss_vrheadset', '901751'),
	(58, 'goree_v_valentineheart', '999999432,33322'),
	(59, 'clothing_U_pantaloneg6rer', '39483626,61156543'),
	(60, 'backpack_PAND_russellg6re', '3543543,9842,9843,9844');
/*!40000 ALTER TABLE `catalog_clothing` ENABLE KEYS */;

-- Dumping structure for table hylib.catalog_featured_pages
DROP TABLE IF EXISTS `catalog_featured_pages`;
CREATE TABLE IF NOT EXISTS `catalog_featured_pages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `caption` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci NOT NULL,
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci NOT NULL,
  `page_link` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci NOT NULL,
  `page_id` int(11) NOT NULL DEFAULT -1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.catalog_featured_pages: ~4 rows (approximately)
DELETE FROM `catalog_featured_pages`;
/*!40000 ALTER TABLE `catalog_featured_pages` DISABLE KEYS */;
INSERT INTO `catalog_featured_pages` (`id`, `caption`, `image`, `page_link`, `page_id`) VALUES
	(1, '¡Nuevo Rare!', 'catalogue/$RQCCNJX.png', 'edicionlimitada', -1),
	(2, 'Comunidad Habbo', 'catalogue/jefe1.png', 'navidad2019', -1),
	(3, '¡Ropa Rare!', 'catalogue/feature_v19_clothing.png', 'cabana2019', -1),
	(4, 'Economía', 'catalogue/feature_cata_hort_hween19_newfurni.png', 'economiainfo', -1);
/*!40000 ALTER TABLE `catalog_featured_pages` ENABLE KEYS */;

-- Dumping structure for table hylib.catalog_gift_nuxuser
DROP TABLE IF EXISTS `catalog_gift_nuxuser`;
CREATE TABLE IF NOT EXISTS `catalog_gift_nuxuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('seasonal','badge','item','sell','purchase','scissors','paper','rock','diamonds') NOT NULL DEFAULT 'item',
  `page_type` int(11) NOT NULL,
  `reward_icon` varchar(255) NOT NULL,
  `reward_name` varchar(255) NOT NULL,
  `reward_productdata` varchar(255) NOT NULL,
  `reward_data` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.catalog_gift_nuxuser: ~3 rows (approximately)
DELETE FROM `catalog_gift_nuxuser`;
/*!40000 ALTER TABLE `catalog_gift_nuxuser` DISABLE KEYS */;
INSERT INTO `catalog_gift_nuxuser` (`id`, `type`, `page_type`, `reward_icon`, `reward_name`, `reward_productdata`, `reward_data`) VALUES
	(1, 'rock', 4, 'album1584/STONE.gif', 'Piedra', 'MAMUT', '1'),
	(2, 'paper', 4, 'album1584/PAPER.gif', 'Papel', 'DUCK', '2'),
	(3, 'scissors', 4, 'album1584/CROCO.gif', 'Tijera', 'CROCO', '3');
/*!40000 ALTER TABLE `catalog_gift_nuxuser` ENABLE KEYS */;

-- Dumping structure for table hylib.catalog_gift_wrapping
DROP TABLE IF EXISTS `catalog_gift_wrapping`;
CREATE TABLE IF NOT EXISTS `catalog_gift_wrapping` (
  `type` enum('new','old') DEFAULT 'new',
  `sprite_id` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.catalog_gift_wrapping: ~18 rows (approximately)
DELETE FROM `catalog_gift_wrapping`;
/*!40000 ALTER TABLE `catalog_gift_wrapping` DISABLE KEYS */;
INSERT INTO `catalog_gift_wrapping` (`type`, `sprite_id`) VALUES
	('new', 3080),
	('new', 3081),
	('new', 3082),
	('new', 3083),
	('new', 3084),
	('new', 3085),
	('new', 3086),
	('new', 3087),
	('new', 3088),
	('new', 3089),
	('old', 187),
	('old', 188),
	('old', 189),
	('old', 190),
	('old', 191),
	('old', 192),
	('old', 193),
	('new', 1492);
/*!40000 ALTER TABLE `catalog_gift_wrapping` ENABLE KEYS */;

-- Dumping structure for table hylib.catalog_items
DROP TABLE IF EXISTS `catalog_items`;
CREATE TABLE IF NOT EXISTS `catalog_items` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `page_id` int(11) NOT NULL,
  `item_ids` varchar(255) NOT NULL,
  `catalog_name` varchar(100) NOT NULL,
  `cost_credits` int(11) NOT NULL DEFAULT 1,
  `cost_pixels` int(11) NOT NULL DEFAULT 0,
  `cost_diamonds` int(11) NOT NULL DEFAULT 0,
  `cost_seasonal` int(11) NOT NULL DEFAULT 0,
  `amount` int(11) NOT NULL DEFAULT 1,
  `limited_sells` int(11) NOT NULL DEFAULT 0,
  `limited_stack` int(11) NOT NULL DEFAULT 0,
  `offer_active` enum('0','1') NOT NULL DEFAULT '0',
  `extradata` varchar(255) NOT NULL DEFAULT '',
  `badge_id` varchar(10) DEFAULT '',
  `vip` enum('0','1','2') NOT NULL DEFAULT '0',
  `achievement` int(4) unsigned NOT NULL DEFAULT 0,
  `song_id` int(11) unsigned NOT NULL DEFAULT 0,
  `flat_id` int(11) NOT NULL DEFAULT -1,
  `cost_tokens` int(10) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `item_ids` (`item_ids`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3000000953 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping structure for table hylib.catalog_products
DROP TABLE IF EXISTS `catalog_products`;
CREATE TABLE IF NOT EXISTS `catalog_products` (
  `id` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL DEFAULT 0,
  `product_name` varchar(255) NOT NULL DEFAULT 'default',
  `cost` int(11) NOT NULL DEFAULT 5,
  `currency` int(11) NOT NULL DEFAULT 5,
  `type` varchar(255) NOT NULL DEFAULT 'normal',
  `image` varchar(255) NOT NULL DEFAULT 'throne',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.catalog_products: ~5 rows (approximately)
DELETE FROM `catalog_products`;
/*!40000 ALTER TABLE `catalog_products` DISABLE KEYS */;
INSERT INTO `catalog_products` (`id`, `item_id`, `product_name`, `cost`, `currency`, `type`, `image`) VALUES
	(1, 206, 'Trono', 5, 1, 'normal', 'throne'),
	(5, -1, 'AWP', 75, 1, 'weapon', 'AWP'),
	(6, -1, 'GUN', 55, 1, 'weapon', 'GUN'),
	(7, 25, '25 Balas', 5, 1, 'bullets', 'balas25'),
	(8, 100, '100 Balas', 20, 1, 'bullets', 'balas100');
/*!40000 ALTER TABLE `catalog_products` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_alerts

-- Dumping structure for table hylib.command_notifications
DROP TABLE IF EXISTS `command_notifications`;
CREATE TABLE IF NOT EXISTS `command_notifications` (
  `name` varchar(200) NOT NULL COMMENT 'The command for the player to type in (it does not override normal system commands)',
  PRIMARY KEY (`name`) USING BTREE,
  KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.command_notifications: ~0 rows (approximately)
DELETE FROM `command_notifications`;
/*!40000 ALTER TABLE `command_notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `command_notifications` ENABLE KEYS */;

-- Dumping structure for table hylib.contact
DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gebruikersnaam` int(10) NOT NULL DEFAULT 0,
  `email` varchar(999) NOT NULL,
  `onderwerp` varchar(999) NOT NULL DEFAULT '',
  `bericht` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

-- Dumping data for table hylib.contact: 0 rows
DELETE FROM `contact`;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;

-- Dumping structure for table hylib.dados_config
DROP TABLE IF EXISTS `dados_config`;
CREATE TABLE IF NOT EXISTS `dados_config` (
  `id` int(11) DEFAULT NULL,
  `is_open` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.dados_config: ~0 rows (approximately)
DELETE FROM `dados_config`;
/*!40000 ALTER TABLE `dados_config` DISABLE KEYS */;
INSERT INTO `dados_config` (`id`, `is_open`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `dados_config` ENABLE KEYS */;

-- Dumping structure for table hylib.dados_formulario
DROP TABLE IF EXISTS `dados_formulario`;
CREATE TABLE IF NOT EXISTS `dados_formulario` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `user_id` int(250) NOT NULL,
  `experiencia` text COLLATE utf8mb4_spanish_ci NOT NULL,
  `disponibilidad` text COLLATE utf8mb4_spanish_ci NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.dados_formulario: ~2 rows (approximately)
DELETE FROM `dados_formulario`;
/*!40000 ALTER TABLE `dados_formulario` DISABLE KEYS */;
INSERT INTO `dados_formulario` (`id`, `user_id`, `experiencia`, `disponibilidad`, `date`) VALUES
	(1, 1, 'sdfasda', 'sdfsd', '2021-12-09 03:09:59'),
	(2, 157, 'Si', 'cualquiera, soy de espaÃ±a', '2022-01-10 15:24:18');
/*!40000 ALTER TABLE `dados_formulario` ENABLE KEYS */;

-- Dumping structure for table hylib.datos
DROP TABLE IF EXISTS `datos`;
CREATE TABLE IF NOT EXISTS `datos` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `type` enum('home_comment','forum_like','forum_like_tema','forum_comment','happyb','badge_veri','badge_deny','banco_dk','banco_di','recargas','buybadge','photo') NOT NULL DEFAULT 'home_comment',
  `mensaje` text NOT NULL,
  `estado` int(1) NOT NULL DEFAULT 0,
  `user_id` int(150) NOT NULL DEFAULT 1,
  `autor` varchar(150) NOT NULL DEFAULT '0',
  `date` timestamp NOT NULL DEFAULT current_timestamp(),
  `url` varchar(150) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.datos: ~0 rows (approximately)
DELETE FROM `datos`;
/*!40000 ALTER TABLE `datos` DISABLE KEYS */;
/*!40000 ALTER TABLE `datos` ENABLE KEYS */;

-- Dumping structure for table hylib.emojis
DROP TABLE IF EXISTS `emojis`;
CREATE TABLE IF NOT EXISTS `emojis` (
  `id` int(250) NOT NULL,
  `key` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table hylib.emojis: ~49 rows (approximately)
DELETE FROM `emojis`;
/*!40000 ALTER TABLE `emojis` DISABLE KEYS */;
INSERT INTO `emojis` (`id`, `key`) VALUES
	(1, ':)'),
	(2, ':D'),
	(4, 'XD'),
	(3, ':guiÃ±o:'),
	(5, ':nervios:'),
	(6, ':reir:'),
	(7, ':lengua:'),
	(8, ':beso'),
	(9, ':love:'),
	(10, ':enamorado'),
	(11, ':sorprendido'),
	(12, ':sonrojado:'),
	(13, ':sueÃ±o:'),
	(14, ':nada:'),
	(15, ':triste:'),
	(16, ':porfavor:'),
	(17, ':confuso:'),
	(18, ':que:'),
	(19, ':shock:'),
	(20, ':ok:'),
	(21, ':notok:'),
	(22, ':nose:'),
	(23, ':nise:'),
	(24, ':perdido:'),
	(25, ':cool:'),
	(26, ':wow:'),
	(27, ':dormir:'),
	(28, ' :enojado:'),
	(29, ':nocomment:'),
	(30, ':muerto:'),
	(31, ':tonto:'),
	(32, ':gracias:'),
	(33, ':enfermo:'),
	(34, ': angel :'),
	(35, ':diablito:'),
	(36, ':travieso:'),
	(37, ':maiz:'),
	(38, ': banana :'),
	(39, ':berenjena:'),
	(40, ':piruleta:'),
	(41, ':galleta:'),
	(42, ':pelota:'),
	(43, ':nieve:'),
	(44, ':cubo:'),
	(45, ':rubi:'),
	(46, ':creditos:'),
	(47, ':corona:'),
	(48, ' :galaxy:'),
	(70, ':russ');
/*!40000 ALTER TABLE `emojis` ENABLE KEYS */;

-- Dumping structure for table hylib.fastfood_user_data
DROP TABLE IF EXISTS `fastfood_user_data`;
CREATE TABLE IF NOT EXISTS `fastfood_user_data` (
  `player_id` int(11) NOT NULL,
  `parachutes` int(11) NOT NULL DEFAULT 10,
  `missiles` int(11) NOT NULL DEFAULT 10,
  `shields` int(11) NOT NULL DEFAULT 10,
  `games_played` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.fastfood_user_data: ~0 rows (approximately)
DELETE FROM `fastfood_user_data`;
/*!40000 ALTER TABLE `fastfood_user_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `fastfood_user_data` ENABLE KEYS */;

-- Dumping structure for table hylib.forum_categories
DROP TABLE IF EXISTS `forum_categories`;
CREATE TABLE IF NOT EXISTS `forum_categories` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `num` int(2) NOT NULL,
  `title` text NOT NULL,
  `class` text NOT NULL,
  `about` text NOT NULL,
  `min_view_rank` int(2) NOT NULL DEFAULT 1,
  `min_post_rank` int(2) NOT NULL DEFAULT 1,
  `min_edit_rank` int(2) NOT NULL DEFAULT 1,
  `link` varchar(99) NOT NULL,
  `min_view_guia` int(2) NOT NULL DEFAULT 0,
  `min_post_guia` int(2) NOT NULL DEFAULT 0,
  `min_edit_guia` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.forum_categories: ~8 rows (approximately)
DELETE FROM `forum_categories`;
/*!40000 ALTER TABLE `forum_categories` DISABLE KEYS */;
INSERT INTO `forum_categories` (`id`, `num`, `title`, `class`, `about`, `min_view_rank`, `min_post_rank`, `min_edit_rank`, `link`, `min_view_guia`, `min_post_guia`, `min_edit_guia`) VALUES
	(1, 2, 'Discusi&oacute;n General', 'general', 'Temas Libres.', 1, 1, 7, 'c100', 0, 0, 0),
	(2, 3, 'Noticias %hotel_name% ', 'news', 'Ent&eacute;rate de lo nuevo y Eventos.', 8, 8, 8, 'c200', 0, 0, 0),
	(3, 4, 'Ideas & Sugerencias', 'ideas', 'Dinos todo lo que piensas, para poder mejorar.', 1, 1, 7, 'c300', 0, 0, 0),
	(4, 5, 'Soporte %hotel_name% ', 'support', 'Has alg&uacute;n Reclamo.', 1, 1, 7, 'c400', 0, 0, 0),
	(5, 6, 'Muestra tu Sala', 'rooms', 'Muestra tus Maravillas con las Salas.', 1, 1, 7, 'c500', 0, 0, 0),
	(6, 7, '&Aacute;lbum de Foto', 'photo', 'Muestra La cara Sexy que esta detras de tu keko', 1, 1, 7, 'c600', 0, 0, 0),
	(7, 1, 'Equipo de Guias', 'guias', 'Solamente para Guias', 1, 1, 3, 'c800', 1, 1, 4),
	(99, 1, 'Equipo Administrativo', 'staff', 'Acceso permitido solo a Staff', 5, 7, 9, 'c700', 0, 0, 0);
/*!40000 ALTER TABLE `forum_categories` ENABLE KEYS */;

-- Dumping structure for table hylib.forum_comments
DROP TABLE IF EXISTS `forum_comments`;
CREATE TABLE IF NOT EXISTS `forum_comments` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `comment` text NOT NULL,
  `newsid` int(10) NOT NULL,
  `userid` int(10) NOT NULL,
  `date` varchar(200) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.forum_comments: ~0 rows (approximately)
DELETE FROM `forum_comments`;
/*!40000 ALTER TABLE `forum_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `forum_comments` ENABLE KEYS */;

-- Dumping structure for table hylib.forum_likes
DROP TABLE IF EXISTS `forum_likes`;
CREATE TABLE IF NOT EXISTS `forum_likes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('Comment','Thread') COLLATE latin1_general_ci NOT NULL DEFAULT 'Comment',
  `type_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.forum_likes: ~0 rows (approximately)
DELETE FROM `forum_likes`;
/*!40000 ALTER TABLE `forum_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `forum_likes` ENABLE KEYS */;

-- Dumping structure for table hylib.forum_threads
DROP TABLE IF EXISTS `forum_threads`;
CREATE TABLE IF NOT EXISTS `forum_threads` (
  `id` int(11) NOT NULL,
  `title` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `longstory` text COLLATE latin1_general_ci NOT NULL,
  `published` timestamp NOT NULL DEFAULT current_timestamp(),
  `author` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `starred` int(1) NOT NULL DEFAULT 0,
  `locked` int(1) NOT NULL DEFAULT 0,
  `shortstory` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `image` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `lastcom` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `perma` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `sub` enum('0','1','2') COLLATE latin1_general_ci NOT NULL DEFAULT '0',
  `room` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `notificacion` enum('0','1') COLLATE latin1_general_ci NOT NULL DEFAULT '1',
  `badge` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.forum_threads: ~0 rows (approximately)
DELETE FROM `forum_threads`;
/*!40000 ALTER TABLE `forum_threads` DISABLE KEYS */;
/*!40000 ALTER TABLE `forum_threads` ENABLE KEYS */;

-- Dumping structure for table hylib.furniture_crafting_items
DROP TABLE IF EXISTS `furniture_crafting_items`;
CREATE TABLE IF NOT EXISTS `furniture_crafting_items` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `itemName` varchar(255) NOT NULL,
  `itemId` int(10) NOT NULL,
  `machineBaseId` int(10) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.furniture_crafting_items: ~0 rows (approximately)
DELETE FROM `furniture_crafting_items`;
/*!40000 ALTER TABLE `furniture_crafting_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `furniture_crafting_items` ENABLE KEYS */;

-- Dumping structure for table hylib.furniture_crafting_recipes
DROP TABLE IF EXISTS `furniture_crafting_recipes`;
CREATE TABLE IF NOT EXISTS `furniture_crafting_recipes` (
  `id` varchar(255) NOT NULL,
  `items` varchar(255) NOT NULL,
  `result` varchar(255) NOT NULL,
  `result_limit` int(10) NOT NULL,
  `result_crafted` int(10) NOT NULL,
  `machineBaseId` int(32) NOT NULL DEFAULT 1,
  `badge` varchar(255) NOT NULL,
  `achievement` varchar(255) NOT NULL,
  `mode` enum('public','secret') NOT NULL DEFAULT 'public'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.furniture_crafting_recipes: ~0 rows (approximately)
DELETE FROM `furniture_crafting_recipes`;
/*!40000 ALTER TABLE `furniture_crafting_recipes` DISABLE KEYS */;
/*!40000 ALTER TABLE `furniture_crafting_recipes` ENABLE KEYS */;


-- Dumping structure for table hylib.gamecenter_list
DROP TABLE IF EXISTS `gamecenter_list`;
CREATE TABLE IF NOT EXISTS `gamecenter_list` (
  `id` int(11) NOT NULL DEFAULT 0,
  `name` varchar(25) NOT NULL DEFAULT '',
  `roomId` int(11) NOT NULL,
  `path` varchar(125) NOT NULL,
  `visible` enum('0','1') DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.gamecenter_list: ~2 rows (approximately)
DELETE FROM `gamecenter_list`;
/*!40000 ALTER TABLE `gamecenter_list` DISABLE KEYS */;
INSERT INTO `gamecenter_list` (`id`, `name`, `roomId`, `path`, `visible`) VALUES
	(1, 'basejump', 1, 'http://localhost/c_images/gamecenter_basejump/', '1'),
	(2, 'snowwar', 2, 'http://localhost/c_images/gamecenter_snowwar/', '1');
/*!40000 ALTER TABLE `gamecenter_list` ENABLE KEYS */;

-- Dumping structure for table hylib.groups
DROP TABLE IF EXISTS `groups`;
CREATE TABLE IF NOT EXISTS `groups` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `badge` varchar(50) NOT NULL,
  `owner_id` int(11) unsigned NOT NULL,
  `room_id` int(10) unsigned NOT NULL DEFAULT 0,
  `created` int(50) NOT NULL,
  `type` enum('regular','exclusive','private') NOT NULL DEFAULT 'regular',
  `colour1` int(11) NOT NULL DEFAULT 242424,
  `colour2` int(11) NOT NULL DEFAULT 242424,
  `members_deco` enum('0','1') DEFAULT '0',
  `has_forum` enum('0','1') DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.groups: ~0 rows (approximately)
DELETE FROM `groups`;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;

-- Dumping structure for table hylib.group_forum_messages
DROP TABLE IF EXISTS `group_forum_messages`;
CREATE TABLE IF NOT EXISTS `group_forum_messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('THREAD','REPLY') DEFAULT 'THREAD',
  `thread_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `title` varchar(120) DEFAULT '',
  `message` varchar(4000) NOT NULL DEFAULT '',
  `author_id` int(11) NOT NULL,
  `author_timestamp` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT 1,
  `locked` enum('1','0') DEFAULT '0',
  `pinned` enum('1','0') DEFAULT '0',
  `moderator_id` int(11) NOT NULL DEFAULT 0,
  `moderator_username` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `group_id` (`group_id`) USING BTREE,
  KEY `thread_id` (`thread_id`) USING BTREE,
  KEY `author_id` (`author_id`) USING BTREE,
  KEY `type_group_id` (`type`,`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.group_forum_messages: ~0 rows (approximately)
DELETE FROM `group_forum_messages`;
/*!40000 ALTER TABLE `group_forum_messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_forum_messages` ENABLE KEYS */;

-- Dumping structure for table hylib.group_forum_settings
DROP TABLE IF EXISTS `group_forum_settings`;
CREATE TABLE IF NOT EXISTS `group_forum_settings` (
  `group_id` int(11) NOT NULL DEFAULT 0,
  `read_permission` enum('EVERYBODY','MEMBERS','ADMINISTRATORS') NOT NULL DEFAULT 'EVERYBODY',
  `post_permission` enum('EVERYBODY','MEMBERS','ADMINISTRATORS','OWNER') NOT NULL DEFAULT 'EVERYBODY',
  `thread_permission` enum('EVERYBODY','MEMBERS','ADMINISTRATORS','OWNER') NOT NULL DEFAULT 'EVERYBODY',
  `moderate_permission` enum('ADMINISTRATORS','OWNER') NOT NULL DEFAULT 'ADMINISTRATORS',
  PRIMARY KEY (`group_id`) USING BTREE,
  KEY `group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.group_forum_settings: ~0 rows (approximately)
DELETE FROM `group_forum_settings`;
/*!40000 ALTER TABLE `group_forum_settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_forum_settings` ENABLE KEYS */;

-- Dumping structure for table hylib.group_items
DROP TABLE IF EXISTS `group_items`;
CREATE TABLE IF NOT EXISTS `group_items` (
  `id` int(11) NOT NULL,
  `firstvalue` varchar(300) NOT NULL,
  `secondvalue` varchar(300) NOT NULL,
  `type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.group_items: ~0 rows (approximately)
DELETE FROM `group_items`;
/*!40000 ALTER TABLE `group_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_items` ENABLE KEYS */;

-- Dumping structure for table hylib.group_memberships
DROP TABLE IF EXISTS `group_memberships`;
CREATE TABLE IF NOT EXISTS `group_memberships` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` int(11) unsigned NOT NULL,
  `player_id` int(11) unsigned NOT NULL,
  `access_level` enum('owner','admin','member') NOT NULL DEFAULT 'member',
  `date_joined` int(11) unsigned NOT NULL DEFAULT 0,
  `role` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `groupid` (`group_id`) USING BTREE,
  KEY `userid` (`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.group_memberships: ~0 rows (approximately)
DELETE FROM `group_memberships`;
/*!40000 ALTER TABLE `group_memberships` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_memberships` ENABLE KEYS */;

-- Dumping structure for table hylib.group_requests
DROP TABLE IF EXISTS `group_requests`;
CREATE TABLE IF NOT EXISTS `group_requests` (
  `group_id` int(11) unsigned NOT NULL,
  `player_id` int(11) unsigned NOT NULL,
  KEY `groupid` (`group_id`) USING BTREE,
  KEY `userid` (`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.group_requests: ~0 rows (approximately)
DELETE FROM `group_requests`;
/*!40000 ALTER TABLE `group_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_requests` ENABLE KEYS */;

-- Dumping structure for table hylib.hilloversie
DROP TABLE IF EXISTS `hilloversie`;
CREATE TABLE IF NOT EXISTS `hilloversie` (
  `id` varchar(255) NOT NULL,
  `versie` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.hilloversie: ~0 rows (approximately)
DELETE FROM `hilloversie`;
/*!40000 ALTER TABLE `hilloversie` DISABLE KEYS */;
INSERT INTO `hilloversie` (`id`, `versie`) VALUES
	('1', 'V3.2');
/
-- Dumping structure for table hylib.home_comments
DROP TABLE IF EXISTS `home_comments`;
CREATE TABLE IF NOT EXISTS `home_comments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `comment` text NOT NULL,
  `newsid` int(10) NOT NULL,
  `userid` int(10) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.home_comments: ~0 rows (approximately)
DELETE FROM `home_comments`;
/*!40000 ALTER TABLE `home_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `home_comments` ENABLE KEYS */;

-- Dumping structure for table hylib.hp_logboek
DROP TABLE IF EXISTS `hp_logboek`;
CREATE TABLE IF NOT EXISTS `hp_logboek` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `browser` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table hylib.hp_logboek: ~0 rows (approximately)
DELETE FROM `hp_logboek`;
/*!40000 ALTER TABLE `hp_logboek` DISABLE KEYS */;
/*!40000 ALTER TABLE `hp_logboek` ENABLE KEYS */;

-- Dumping structure for table hylib.items_crackable_rewards
DROP TABLE IF EXISTS `items_crackable_rewards`;
CREATE TABLE IF NOT EXISTS `items_crackable_rewards` (
  `item_id` int(11) NOT NULL,
  `hit_requirement` int(11) NOT NULL,
  `crackable_type` enum('PRIVATE','PUBLIC') NOT NULL DEFAULT 'PRIVATE',
  `reward_type` enum('BADGE','ITEM','COINS','VIP_POINTS','ACTIVITY_POINTS','EFFECT') NOT NULL DEFAULT 'BADGE',
  `reward_data` text NOT NULL,
  `reward_data_int` int(11) NOT NULL DEFAULT 11,
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.items_crackable_rewards: ~0 rows (approximately)
DELETE FROM `items_crackable_rewards`;
/*!40000 ALTER TABLE `items_crackable_rewards` DISABLE KEYS */;
/*!40000 ALTER TABLE `items_crackable_rewards` ENABLE KEYS */;

-- Dumping structure for table hylib.items_limited_edition
DROP TABLE IF EXISTS `items_limited_edition`;
CREATE TABLE IF NOT EXISTS `items_limited_edition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL DEFAULT 0,
  `limited_id` int(11) NOT NULL DEFAULT 0,
  `limited_total` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `item_id` (`item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.items_limited_edition: ~0 rows (approximately)
DELETE FROM `items_limited_edition`;
/*!40000 ALTER TABLE `items_limited_edition` DISABLE KEYS */;
/*!40000 ALTER TABLE `items_limited_edition` ENABLE KEYS */;

-- Dumping structure for table hylib.items_moodlight
DROP TABLE IF EXISTS `items_moodlight`;
CREATE TABLE IF NOT EXISTS `items_moodlight` (
  `item_id` int(10) NOT NULL,
  `enabled` enum('1','0') NOT NULL DEFAULT '1',
  `active_preset` enum('1','2','3') NOT NULL DEFAULT '1',
  `preset_1` varchar(500) CHARACTER SET utf8mb4 NOT NULL,
  `preset_2` varchar(500) CHARACTER SET utf8mb4 NOT NULL,
  `preset_3` varchar(500) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.items_moodlight: ~4 rows (approximately)
DELETE FROM `items_moodlight`;
/*!40000 ALTER TABLE `items_moodlight` DISABLE KEYS */;
INSERT INTO `items_moodlight` (`item_id`, `enabled`, `active_preset`, `preset_1`, `preset_2`, `preset_3`) VALUES
	(2708, '1', '1', '{"backgroundOnly":true,"colour":"#0053F7","intensity":76}', '{"backgroundOnly":true,"colour":"#000000","intensity":255}', '{"backgroundOnly":true,"colour":"#000000","intensity":255}'),
	(3101, '0', '1', '{"backgroundOnly":true,"colour":"#000000","intensity":255}', '{"backgroundOnly":true,"colour":"#000000","intensity":255}', '{"backgroundOnly":true,"colour":"#000000","intensity":255}'),
	(3623, '0', '1', '{"backgroundOnly":true,"colour":"#000000","intensity":255}', '{"backgroundOnly":true,"colour":"#000000","intensity":255}', '{"backgroundOnly":true,"colour":"#000000","intensity":255}'),
	(3799, '1', '1', '{"backgroundOnly":true,"colour":"#0053F7","intensity":76}', '{"backgroundOnly":true,"colour":"#000000","intensity":255}', '{"backgroundOnly":true,"colour":"#000000","intensity":255}');
/*!40000 ALTER TABLE `items_moodlight` ENABLE KEYS */;

-- Dumping structure for table hylib.items_rentable
DROP TABLE IF EXISTS `items_rentable`;
CREATE TABLE IF NOT EXISTS `items_rentable` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `space_id` int(11) NOT NULL,
  `expiracy` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `player_id_badge_code` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.items_rentable: ~0 rows (approximately)
DELETE FROM `items_rentable`;
/*!40000 ALTER TABLE `items_rentable` DISABLE KEYS */;
/*!40000 ALTER TABLE `items_rentable` ENABLE KEYS */;

-- Dumping structure for table hylib.items_teles
DROP TABLE IF EXISTS `items_teles`;
CREATE TABLE IF NOT EXISTS `items_teles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_one` bigint(20) DEFAULT NULL,
  `id_two` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.items_teles: ~0 rows (approximately)
DELETE FROM `items_teles`;
/*!40000 ALTER TABLE `items_teles` DISABLE KEYS */;
/*!40000 ALTER TABLE `items_teles` ENABLE KEYS */;

-- Dumping structure for table hylib.items_wired_rewards
DROP TABLE IF EXISTS `items_wired_rewards`;
CREATE TABLE IF NOT EXISTS `items_wired_rewards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `reward_data` text NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.items_wired_rewards: ~0 rows (approximately)
DELETE FROM `items_wired_rewards`;
/*!40000 ALTER TABLE `items_wired_rewards` DISABLE KEYS */;
/*!40000 ALTER TABLE `items_wired_rewards` ENABLE KEYS */;

-- Dumping structure for table hylib.logs
DROP TABLE IF EXISTS `logs`;
CREATE TABLE IF NOT EXISTS `logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('ROOM_VISIT','FURNI_PURCHASE','ROOM_CHATLOG','MESSENGER_CHATLOG','COMMAND','ALFA_CHATLOG','TRADE','OFFLINE_CHATLOG') DEFAULT 'ROOM_CHATLOG',
  `room_id` int(11) DEFAULT -1,
  `user_id` int(11) DEFAULT -1,
  `data` text CHARACTER SET utf8mb4 DEFAULT NULL,
  `timestamp` int(11) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6159 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.logs: ~5,962 rows (approximately)
DELETE FROM `logs`;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` (`id`, `type`, `room_id`, `user_id`, `data`, `timestamp`) VALUES
	(1, 'ROOM_CHATLOG', 2, 12, 'https://media4.giphy.com/media/kLM9I1g8jsiAM/100', 1673168502),

-- Dumping structure for table hylib.logs_namechange
DROP TABLE IF EXISTS `logs_namechange`;
CREATE TABLE IF NOT EXISTS `logs_namechange` (
  `user_id` int(50) NOT NULL,
  `new_name` varchar(250) COLLATE utf8mb4_spanish_ci NOT NULL,
  `old_name` varchar(250) COLLATE utf8mb4_spanish_ci NOT NULL,
  `timestamp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.logs_namechange: ~2 rows (approximately)
DELETE FROM `logs_namechange`;
/*!40000 ALTER TABLE `logs_namechange` DISABLE KEYS */;
INSERT INTO `logs_namechange` (`user_id`, `new_name`, `old_name`, `timestamp`) VALUES
	(11, 'Angelu', 'Angeluu', 1673219841),
	(19, 'Scott', 'NitroV2', 1673305584);
/*!40000 ALTER TABLE `logs_namechange` ENABLE KEYS */;

-- Dumping structure for table hylib.logs_trades
DROP TABLE IF EXISTS `logs_trades`;
CREATE TABLE IF NOT EXISTS `logs_trades` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `player_id` int(250) NOT NULL,
  `by_id` int(250) NOT NULL,
  `items_id` int(250) NOT NULL,
  `item_id` bigint(250) NOT NULL,
  `times` int(250) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.logs_trades: ~0 rows (approximately)
DELETE FROM `logs_trades`;
/*!40000 ALTER TABLE `logs_trades` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs_trades` ENABLE KEYS */;

-- Dumping structure for table hylib.messenger_friendships
DROP TABLE IF EXISTS `messenger_friendships`;
CREATE TABLE IF NOT EXISTS `messenger_friendships` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_one_id` int(10) unsigned NOT NULL,
  `user_two_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `1` (`user_one_id`) USING BTREE,
  KEY `2` (`user_two_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.messenger_friendships: ~52 rows (approximately)
DELETE FROM `messenger_friendships`;
/*!40000 ALTER TABLE `messenger_friendships` DISABLE KEYS */;
INSERT INTO `messenger_friendships` (`id`, `user_one_id`, `user_two_id`) VALUES
	(1, 1, 1),
	
-- Dumping structure for table hylib.messenger_requests
DROP TABLE IF EXISTS `messenger_requests`;
CREATE TABLE IF NOT EXISTS `messenger_requests` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `from_id` int(10) unsigned NOT NULL,
  `to_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `from_id` (`from_id`) USING BTREE,
  KEY `to_id` (`to_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.messenger_requests: ~1 rows (approximately)
DELETE FROM `messenger_requests`;
/*!40000 ALTER TABLE `messenger_requests` DISABLE KEYS */;
INSERT INTO `messenger_requests` (`id`, `from_id`, `to_id`) VALUES
	(4, 13, 10);
/*!40000 ALTER TABLE `messenger_requests` ENABLE KEYS */;

-- Dumping structure for table hylib.moderation_actions
DROP TABLE IF EXISTS `moderation_actions`;
CREATE TABLE IF NOT EXISTS `moderation_actions` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT '',
  `message` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ban_hours` int(11) DEFAULT NULL,
  `avatar_ban_hours` int(11) DEFAULT NULL,
  `mute_hours` int(11) DEFAULT NULL,
  `trade_lock_hours` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.moderation_actions: ~4 rows (approximately)
DELETE FROM `moderation_actions`;
/*!40000 ALTER TABLE `moderation_actions` DISABLE KEYS */;
INSERT INTO `moderation_actions` (`id`, `category_id`, `name`, `message`, `description`, `ban_hours`, `avatar_ban_hours`, `mute_hours`, `trade_lock_hours`) VALUES
	(1, 1, 'Mute', 'Mute the player for a day.', '', 0, 0, 24, 0),
	(2, 1, 'Ban', 'Ban the player for a day.', '', 24, 24, 0, 0),
	(3, 2, 'Mute', 'Mute the player for 2 hours.', '', 0, 0, 2, 0),
	(4, 2, 'Warn', 'If you continue acting like a little bitch, you\'re gonna get banned!', 'Sends then a generic alert telling them that if they carry on, they will be banned.', 0, 0, 0, 0);
/*!40000 ALTER TABLE `moderation_actions` ENABLE KEYS */;

-- Dumping structure for table hylib.moderation_action_categories
DROP TABLE IF EXISTS `moderation_action_categories`;
CREATE TABLE IF NOT EXISTS `moderation_action_categories` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.moderation_action_categories: ~2 rows (approximately)
DELETE FROM `moderation_action_categories`;
/*!40000 ALTER TABLE `moderation_action_categories` DISABLE KEYS */;
INSERT INTO `moderation_action_categories` (`id`, `name`) VALUES
	(1, 'PII'),
	(2, 'Sexually Explicit');
/*!40000 ALTER TABLE `moderation_action_categories` ENABLE KEYS */;

-- Dumping structure for table hylib.moderation_help_tickets
DROP TABLE IF EXISTS `moderation_help_tickets`;
CREATE TABLE IF NOT EXISTS `moderation_help_tickets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` enum('CLOSED','IN_PROGRESS','OPEN','INVALID','ABUSIVE') NOT NULL DEFAULT 'OPEN',
  `submitter_id` int(11) NOT NULL DEFAULT 0,
  `reported_id` int(11) NOT NULL DEFAULT 0,
  `moderator_id` int(11) NOT NULL DEFAULT 0,
  `category_id` int(11) NOT NULL DEFAULT 0,
  `message` varchar(255) NOT NULL DEFAULT '',
  `chat_messages` text DEFAULT NULL,
  `room_id` int(11) NOT NULL DEFAULT 0,
  `timestamp_opened` int(11) NOT NULL DEFAULT 0,
  `timestamp_closed` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.moderation_help_tickets: ~70 rows (approximately)
DELETE FROM `moderation_help_tickets`;
/*!40000 ALTER TABLE `moderation_help_tickets` DISABLE KEYS */;
INSERT INTO `moderation_help_tickets` (`id`, `state`, `submitter_id`, `reported_id`, `moderator_id`, `category_id`, `message`, `chat_messages`, `room_id`, `timestamp_opened`, `timestamp_closed`) VALUES
	(1, 'IN_PROGRESS', 7, 86, 31, 2, 'test, '[{"playerId":86,"message":"un gei que me reporto"}]', 197, 1631549471, 0),

/*!40000 ALTER TABLE `moderation_help_tickets` ENABLE KEYS */;

-- Dumping structure for table hylib.moderation_presets
DROP TABLE IF EXISTS `moderation_presets`;
CREATE TABLE IF NOT EXISTS `moderation_presets` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` enum('user','room') NOT NULL DEFAULT 'user',
  `message` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.moderation_presets: ~16 rows (approximately)
DELETE FROM `moderation_presets`;
/*!40000 ALTER TABLE `moderation_presets` DISABLE KEYS */;
INSERT INTO `moderation_presets` (`id`, `type`, `message`) VALUES
	(1, 'user', 'Creemos que tu comportamiento no sigue las normas del hotel. Por favor cambialo, podrÃƒÂ­as ser sancionado.'),
	(2, 'room', 'Mmm quizÃƒÂ¡s el tema de esta sala sea mÃƒÂ¡s interesante para otro sitio que no sea iBoom, Ã‚Â¿no crees? Tengo que cerrarla y sacar a la gente de aquÃƒÂ­.'),
	(3, 'user', 'Queremos que estÃƒÂ©s seguro en el hotel. Por eso te recomendamos que no des ni pidas informaciÃƒÂ³n personal a usuarios desconocidos.'),
	(4, 'room', 'Mmm esta sala no es de confianza... se esta cometiendo un timo, recuerda pensar antes de apostar tus rares con otros usuarios, debo cerrar la sala y echaros de aquÃƒÂ­.'),
	(5, 'user', 'Ã‚Â¡No incites a las demÃƒÂ¡s personas a realizar contenido sexual dentro del hotel! PodrÃƒÂ¡s ser sancionado.'),
	(6, 'user', 'Ã‚Â¡No flodees la sala! PodrÃƒÂ¡s ser expulsado/a de la misma. o incluso sancionado por un moderador.'),
	(7, 'room', 'Ã‚Â¡Vaya! Parece que nos ha surgido un imprevisto en la sala... volveremos lo antes posible '),
	(8, 'user', 'Ã‚Â¡Tu comportamiento da mucho que desear! Por favor, respeta las normas de iBoom o serÃƒÂ¡s sancionado.'),
	(9, 'user', 'Ã‚Â¡Eh! Estas bloqueando una casilla, no te quedes ausente en lugares donde la gente quiera pasar o podrÃƒÂ­as ser kickeado de la sala.'),
	(10, 'room', 'El nombre de la sala es obsceno y ofensivo. Decidimos cambiarlo por uno mÃƒÂ¡s adecuado.'),
	(11, 'user', 'Tu actitud puede resultar molesta para algunos usuarios. Por favor modÃƒÂ©rate o serÃƒÂ¡s muteado/a.'),
	(12, 'user', 'Lo sentimos. El nombre de usuario que estÃƒÂ¡s usando es inapropiado. En breve serÃƒÂ¡s baneado, por favor create otro usuario y sigue disfrutando de iBoom.'),
	(13, 'user', 'No copies look\'s a personas que no conoces. Tal vez se molesten por que lo hagas asÃƒÂ­ que Ã‚Â¡pregÃƒÂºntales primero antes de hacerlo!'),
	(14, 'room', 'Esta sala no cumple con la normativa de iBoom, por ello procedemos a cerrarla y echar a todos los usuarios de la sala.'),
	(15, 'room', 'Ã‚Â¡Si ves publicistas de otro hotel reporta para que un staff los eche de iBoom!'),
	(16, 'user', 'Ã‚Â¡Eh! No nombres otros hoteles podrÃƒÂ­as ser banead@ por un Moderador');
/*!40000 ALTER TABLE `moderation_presets` ENABLE KEYS */;

-- Dumping structure for table hylib.moderation_recommendations
DROP TABLE IF EXISTS `moderation_recommendations`;
CREATE TABLE IF NOT EXISTS `moderation_recommendations` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `guide_id` int(10) NOT NULL,
  `player_id` int(10) NOT NULL,
  `timestamp` int(11) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.moderation_recommendations: ~41 rows (approximately)
DELETE FROM `moderation_recommendations`;
/*!40000 ALTER TABLE `moderation_recommendations` DISABLE KEYS */;
INSERT INTO `moderation_recommendations` (`id`, `guide_id`, `player_id`, `timestamp`) VALUES
	(1, 54, 89, 1631469496),
	(2, 51, 87, 1638073544),
	(3, 97, 1463, 1640643768),
	(4, 65, 494, 1660496865),
	(5, 65, 567, 1660499030),
	(6, 140, 494, 1660503660),
	(7, 65, 450, 1660585623),
	(8, 65, 689, 1660600096),
	(9, 5, 995, 1660679119),
	(10, 65, 535, 1660696486),
	(11, 96, 233, 1660803256),
	(12, 40, 369, 1661297538),
	(13, 40, 2350, 1661300949),
	(14, 65, 2720, 1661372395),
	(15, 65, 2669, 1661387452),
	(16, 96, 561, 1661629470),
	(17, 65, 4835, 1661972839),
	(18, 96, 5243, 1662327187),
	(19, 450, 689, 1662757786),
	(20, 96, 5508, 1662875411),
	(21, 96, 5508, 1662877546),
	(22, 96, 5508, 1662880810),
	(23, 65, 3339, 1663082590),
	(24, 96, 5560, 1663087189),
	(25, 96, 5560, 1663090857),
	(26, 96, 5560, 1663174023),
	(27, 96, 5560, 1663176178),
	(28, 65, 5597, 1663256884),
	(29, 65, 5209, 1663524097),
	(30, 65, 5560, 1663603866),
	(31, 96, 6667, 1663776455),
	(32, 1571, 5560, 1663781406),
	(33, 227, 539, 1663956416),
	(34, 704, 7096, 1664115431),
	(35, 503, 10215, 1665444764),
	(36, 503, 10215, 1665716134),
	(37, 227, 11920, 1667178561),
	(38, 96, 494, 1668018754),
	(39, 660, 1201, 1668965499),
	(40, 12, 22, 1673304669),
	(41, 12, 22, 1673304732);
/*!40000 ALTER TABLE `moderation_recommendations` ENABLE KEYS */;

-- Dumping structure for table hylib.navigator_categories
DROP TABLE IF EXISTS `navigator_categories`;
CREATE TABLE IF NOT EXISTS `navigator_categories` (
  `id` int(11) NOT NULL,
  `category` enum('official_view','hotel_view','myworld_view','roomads_view','query') NOT NULL DEFAULT 'hotel_view',
  `category_identifier` varchar(35) NOT NULL DEFAULT '',
  `public_name` varchar(50) NOT NULL DEFAULT '',
  `view_mode` enum('REGULAR','THUMBNAIL') NOT NULL DEFAULT 'REGULAR',
  `required_rank` int(11) NOT NULL DEFAULT 1,
  `category_type` varchar(25) NOT NULL DEFAULT 'category',
  `search_allowance` enum('NOTHING','SHOW_MORE') NOT NULL DEFAULT 'SHOW_MORE',
  `enabled` enum('0','1') NOT NULL DEFAULT '1',
  `visible` enum('0','1') NOT NULL DEFAULT '1',
  `order_id` int(11) NOT NULL DEFAULT 0,
  `room_count` int(11) NOT NULL DEFAULT 15,
  `room_count_expanded` int(11) NOT NULL DEFAULT 50,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.navigator_categories: ~22 rows (approximately)
DELETE FROM `navigator_categories`;
/*!40000 ALTER TABLE `navigator_categories` DISABLE KEYS */;
INSERT INTO `navigator_categories` (`id`, `category`, `category_identifier`, `public_name`, `view_mode`, `required_rank`, `category_type`, `search_allowance`, `enabled`, `visible`, `order_id`, `room_count`, `room_count_expanded`) VALUES
	(0, 'query', 'query', '', 'REGULAR', 1, 'query', 'NOTHING', '1', '1', 0, 12, 50),
	(1, 'official_view', 'official-root-2', 'Estancias oficiales', 'THUMBNAIL', 1, 'public', 'NOTHING', '1', '1', 1, 12, 50),
	(2, 'hotel_view', 'popular', 'Salas mÃ¡s visitadas', 'REGULAR', 1, 'popular', 'SHOW_MORE', '1', '1', 1, 50, 50),
	(3, 'myworld_view', 'my', '', 'REGULAR', 1, 'my_rooms', 'SHOW_MORE', '1', '1', 0, 12, 50),
	(4, 'myworld_view', 'favorites', '', 'REGULAR', 1, 'my_favorites', 'SHOW_MORE', '1', '1', 1, 12, 50),
	(5, 'myworld_view', 'my_groups', '', 'REGULAR', 1, 'my_groups', 'SHOW_MORE', '1', '1', 1, 12, 50),
	(6, 'myworld_view', 'history', '', 'REGULAR', 1, 'my_history', 'SHOW_MORE', '0', '1', 0, 12, 50),
	(7, 'myworld_view', 'friends_rooms', 'Salas de mis amigos', 'REGULAR', 1, 'my_friends_rooms', 'SHOW_MORE', '1', '1', 3, 12, 50),
	(8, 'myworld_view', 'history_freq', '', 'REGULAR', 1, 'my_history_freq', 'SHOW_MORE', '0', '1', 0, 12, 50),
	(9, 'roomads_view', 'new_ads', 'Top Promociones', 'REGULAR', 1, 'new_ads', 'SHOW_MORE', '1', '1', 0, 12, 50),
	(10, 'hotel_view', 'chat_chill_discussion', '[CHARLA] Conversa con gente de Habbo', 'REGULAR', 1, 'category', 'SHOW_MORE', '1', '0', 2, 12, 50),
	(11, 'hotel_view', 'games_events', 'Juegos 24/7', 'REGULAR', 1, 'category', 'SHOW_MORE', '1', '0', 3, 12, 50),
	(12, 'myworld_view', 'my_rights', 'Salas donde tengo derechos', 'REGULAR', 1, 'my_rights', 'SHOW_MORE', '0', '1', 0, 12, 50),
	(13, 'official_view', 'official-root', 'Salas Recomendadas', 'REGULAR', 1, 'staff_picks', 'NOTHING', '1', '1', 2, 12, 50),
	(14, 'official_view', 'official-root', 'AutomÃƒÂ¡ticos 24h', 'THUMBNAIL', 1, 'public', 'NOTHING', '1', '1', 0, 12, 50),
	(15, 'hotel_view', 'sugerences', 'Sugerencias a visitar', 'THUMBNAIL', 1, 'staff_picks', 'NOTHING', '1', '1', 0, 12, 50),
	(16, 'hotel_view', 'games_events', 'Juegos ClÃ¡sicos', 'REGULAR', 1, 'category', 'SHOW_MORE', '1', '0', 4, 12, 50),
	(17, 'hotel_view', 'games_events', 'Juegos RPG', 'REGULAR', 1, 'category', 'SHOW_MORE', '1', '0', 5, 12, 50),
	(18, 'hotel_view', 'games_events', 'Fiesta / Radio', 'REGULAR', 1, 'category', 'SHOW_MORE', '1', '0', 6, 12, 50),
	(19, 'hotel_view', 'games_events', 'Laberintos', 'REGULAR', 1, 'category', 'SHOW_MORE', '1', '0', 7, 12, 50),
	(20, 'hotel_view', 'chat_chill_discussion', 'Trade', 'REGULAR', 1, 'category', 'SHOW_MORE', '1', '0', 8, 12, 50),
	(21, 'hotel_view', 'chat_chill_discussion', 'Otros', 'REGULAR', 1, 'category', 'SHOW_MORE', '1', '0', 9, 12, 50);
/*!40000 ALTER TABLE `navigator_categories` ENABLE KEYS */;

-- Dumping structure for table hylib.navigator_publics
DROP TABLE IF EXISTS `navigator_publics`;
CREATE TABLE IF NOT EXISTS `navigator_publics` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `caption` varchar(64) NOT NULL,
  `description` varchar(150) NOT NULL,
  `image_url` text NOT NULL,
  `order_num` int(11) NOT NULL DEFAULT 1,
  `enabled` enum('0','1') NOT NULL DEFAULT '1',
  `category` varchar(255) NOT NULL DEFAULT 'official-root-2',
  PRIMARY KEY (`room_id`) USING BTREE,
  KEY `ordernum` (`order_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7212 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.navigator_publics: ~8 rows (approximately)
DELETE FROM `navigator_publics`;
/*!40000 ALTER TABLE `navigator_publics` DISABLE KEYS */;
INSERT INTO `navigator_publics` (`room_id`, `caption`, `description`, `image_url`, `order_num`, `enabled`, `category`) VALUES
	(19, '[LV] Bienvenidos a Habbo ', 'Recibidor oficial del hotel.', '', 1, '1', 'official-root'),
	(230, '[HG] Zona Picnic - ClÃ¡sica', 'Â¡Hace un buen dÃ­a para tomar el aire!', 'camera/thumbnails/230.png', 3, '1', 'official-root'),
	(243, '[HG] Infobus [HG]', 'SÃºbete al bus pixelado', '/ru.png', 1, '1', 'official-root'),
	(245, '[HG] Casino', 'Apuestas automÃ¡ticas.', 'camera/thumbnails/245.png', 4, '1', 'official-root'),
	(281, '[HG] Hospital', 'Â¿Tienes cita mÃ©dica?', 'camera/thumbnails/281.png', 7, '1', 'official-root'),
	(286, '[HG] Central de Juegos - FantasÃ­a', 'Diviertete con los mejores juegos.', 'camera/thumbnails/286.png', 6, '1', 'official-root'),
	(310, '[HGFM] Galaxy FM', 'Disfruta de buena mÃºsica.', 'camera/thumbnails/310.png', 9, '1', 'official-root'),
	(367, '[HG] Central de Dados', 'Â¿Unas apuestas?', 'camera/thumbnails/367.png', 8, '1', 'official-root');
/*!40000 ALTER TABLE `navigator_publics` ENABLE KEYS */;

-- Dumping structure for table hylib.navigator_staff_picks
DROP TABLE IF EXISTS `navigator_staff_picks`;
CREATE TABLE IF NOT EXISTS `navigator_staff_picks` (
  `room_id` int(11) NOT NULL DEFAULT 0,
  `picker` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.navigator_staff_picks: ~0 rows (approximately)
DELETE FROM `navigator_staff_picks`;
/*!40000 ALTER TABLE `navigator_staff_picks` DISABLE KEYS */;
/*!40000 ALTER TABLE `navigator_staff_picks` ENABLE KEYS */;

-- Dumping structure for table hylib.news_likes
DROP TABLE IF EXISTS `news_likes`;
CREATE TABLE IF NOT EXISTS `news_likes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `news_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `like` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.news_likes: ~0 rows (approximately)
DELETE FROM `news_likes`;
/*!40000 ALTER TABLE `news_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `news_likes` ENABLE KEYS */;

-- Dumping structure for table hylib.permission_chat_bubbles
DROP TABLE IF EXISTS `permission_chat_bubbles`;
CREATE TABLE IF NOT EXISTS `permission_chat_bubbles` (
  `bubble_id` int(11) NOT NULL,
  `minimum_rank` int(11) NOT NULL,
  PRIMARY KEY (`bubble_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.permission_chat_bubbles: ~38 rows (approximately)
DELETE FROM `permission_chat_bubbles`;
/*!40000 ALTER TABLE `permission_chat_bubbles` DISABLE KEYS */;
INSERT INTO `permission_chat_bubbles` (`bubble_id`, `minimum_rank`) VALUES
	(0, 1),
	(1, 1),
	(2, 2),
	(3, 2),
	(4, 1),
	(5, 1),
	(6, 1),
	(7, 4),
	(8, 4),
	(9, 1),
	(10, 1),
	(11, 1),
	(12, 3),
	(13, 1),
	(14, 1),
	(15, 1),
	(16, 3),
	(17, 6),
	(19, 3),
	(20, 3),
	(21, 3),
	(22, 3),
	(23, 5),
	(24, 2),
	(25, 6),
	(26, 4),
	(27, 4),
	(28, 4),
	(29, 2),
	(30, 2),
	(31, 3),
	(32, 3),
	(33, 3),
	(34, 3),
	(35, 3),
	(36, 4),
	(37, 4),
	(38, 4);
/*!40000 ALTER TABLE `permission_chat_bubbles` ENABLE KEYS */;

-- Dumping structure for table hylib.permission_commands
DROP TABLE IF EXISTS `permission_commands`;
CREATE TABLE IF NOT EXISTS `permission_commands` (
  `command_id` varchar(500) DEFAULT 'AboutCommand',
  `minimum_rank` int(11) DEFAULT 1,
  `vip_only` enum('1','0') DEFAULT NULL,
  `rights_only` enum('0','1') DEFAULT NULL,
  `rights_override` enum('NONE','RIGHTS','OWNER') DEFAULT 'NONE'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.permission_commands: ~79 rows (approximately)
DELETE FROM `permission_commands`;
/*!40000 ALTER TABLE `permission_commands` DISABLE KEYS */;
INSERT INTO `permission_commands` (`command_id`, `minimum_rank`, `vip_only`, `rights_only`, `rights_override`) VALUES
	('about_command', 6, '0', '0', 'NONE'),
	('commands_command', 1, '0', '0', 'NONE'),
	('build_command', 8, '0', '0', 'NONE'),
	('teleport_command', 4, '0', '0', 'NONE'),
	('pickall_command', 1, '0', '0', 'NONE'),
	('massmotd_command', 6, '0', '0', 'NONE'),
	('hotelalert_command', 6, '0', '0', 'NONE'),
	('invisible_command', 6, '0', '0', 'NONE'),
	('push_command', 1, '0', '0', 'NONE'),
	('moonwalk_command', 1, '0', '0', 'NONE'),
	('enable_command', 1, '0', '0', 'NONE'),
	('ban_command', 4, '0', '0', 'NONE'),
	('empty_command', 1, '0', '0', 'NONE'),
	('unload_command', 6, '0', '0', 'NONE'),
	('ipban_command', 5, '0', '0', 'NONE'),
	('givebadge_command', 6, '0', '0', 'NONE'),
	('reload_command', 6, '0', '0', 'NONE'),
	('transform_command', 1, '0', '0', 'NONE'),
	('coins_command', 6, '0', '0', 'NONE'),
	('pull_command', 1, '0', '0', 'NONE'),
	('sit_command', 1, '0', '0', 'NONE'),
	('alert_command', 5, '0', '0', 'NONE'),
	('points_command', 6, '0', '0', 'NONE'),
	('kick_command', 6, '0', '0', 'NONE'),
	('mimic_command', 6, '0', '0', 'NONE'),
	('machineban_command', 6, '0', '0', 'NONE'),
	('massbadge_command', 6, '0', '0', 'NONE'),
	('masscoins_command', 6, '0', '0', 'NONE'),
	('masspoints_command', 6, '0', '0', 'NONE'),
	('massduckets_command', 6, '0', '0', 'NONE'),
	('redeemcredits_command', 1, '0', '0', 'NONE'),
	('playerinfo_command', 6, '0', '0', 'NONE'),
	('roommute_command', 6, '0', '0', 'NONE'),
	('unmute_command', 6, '0', '0', 'NONE'),
	('handitem_command', 1, '0', '0', 'NONE'),
	('setmax_command', 1, '0', '0', 'NONE'),
	('removebadge_command', 6, '0', '0', 'NONE'),
	('deletegroup_command', 1, '0', '0', 'NONE'),
	('shutdown_command', 7, '0', '0', 'NONE'),
	('togglediagonal_command', 1, '0', '0', 'NONE'),
	('roll_command', 6, '0', '0', 'NONE'),
	('hotelalertlink_command', 6, '0', '0', 'NONE'),
	('summon_command', 7, '0', '0', 'NONE'),
	('togglefriends_command', 1, '0', '0', 'NONE'),
	('roomaction_command', 6, '0', '0', 'NONE'),
	('enablecommand_command', 4, '0', '0', 'NONE'),
	('disablecommand_command', 4, '0', '0', 'NONE'),
	('mute_command', 3, '0', '0', 'NONE'),
	('unmute_command', 3, '0', '0', 'NONE'),
	('punch_command', 1, '0', '0', 'NONE'),
	('bundle_command', 6, '0', '0', 'NONE'),
	('notification_command', 6, '0', '0', 'NONE'),
	('maintenance_command', 6, '0', '0', 'NONE'),
	('eventalert_command', 6, '0', '0', 'NONE'),
	('quickpoll_command', 6, '0', '0', 'NONE'),
	('ejectall_command', 1, '0', '0', 'NONE'),
	('fastwalk_command', 1, '0', '0', 'NONE'),
	('roomoption_command', 6, '0', '0', 'NONE'),
	('ignoreevents_command', 1, '0', '0', 'NONE'),
	('listen_command', 6, '0', '0', 'NONE'),
	('cloneroom_command', 6, '0', '0', 'NONE'),
	('height_command', 1, '0', '0', 'NONE'),
	('roomvideo_command', 7, '0', '0', 'NONE'),
	('staffalert_command', 2, '0', '0', 'NONE'),
	('mass_seasonal_command', 100, '0', '0', 'NONE'),
	('staffinfo_command', 2, '0', '0', 'NONE'),
	('unban_command', 6, '0', '0', 'NONE'),
	('height_command', 1, '0', '0', 'NONE'),
	('listen_command', 6, '0', '0', 'NONE'),
	('cloneroom_command', 6, '0', '0', 'NONE'),
	('setspeed_command', 1, '0', '0', 'NONE'),
	('lay_command', 1, '0', '0', 'NONE'),
	('follow_command', 1, '0', '0', 'NONE'),
	('noface_command', 1, '0', '0', 'NONE'),
	('disconnect_command', 6, '0', '0', 'NONE'),
	('donate_command', 7, '0', '0', 'NONE'),
	('duckets_command', 7, '0', '0', 'NONE'),
	('points_command', 7, '0', '0', 'NONE'),
	('cd_command', 7, '0', '0', 'NONE');
/*!40000 ALTER TABLE `permission_commands` ENABLE KEYS */;

-- Dumping structure for table hylib.permission_command_overrides
DROP TABLE IF EXISTS `permission_command_overrides`;
CREATE TABLE IF NOT EXISTS `permission_command_overrides` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `command_id` varchar(255) DEFAULT NULL,
  `player_id` int(11) DEFAULT NULL,
  `enabled` enum('1','0') DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `player_id` (`player_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.permission_command_overrides: ~6 rows (approximately)
DELETE FROM `permission_command_overrides`;
/*!40000 ALTER TABLE `permission_command_overrides` DISABLE KEYS */;
INSERT INTO `permission_command_overrides` (`id`, `command_id`, `player_id`, `enabled`) VALUES
	(3, '1', 2, '1'),
	(4, 'allowance_mention', 1, '1'),
	(5, 'allowance_candy', 1, '1'),
	(6, 'allowance_candy', 3, '1'),
	(8, 'allowance_candy', 2, '1'),
	(9, 'allowance_candy', 3, '1');
/*!40000 ALTER TABLE `permission_command_overrides` ENABLE KEYS */;

-- Dumping structure for table hylib.permission_effects
DROP TABLE IF EXISTS `permission_effects`;
CREATE TABLE IF NOT EXISTS `permission_effects` (
  `effect_id` int(11) NOT NULL,
  `minimum_rank` int(11) DEFAULT 7,
  PRIMARY KEY (`effect_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.permission_effects: ~21 rows (approximately)
DELETE FROM `permission_effects`;
/*!40000 ALTER TABLE `permission_effects` DISABLE KEYS */;
INSERT INTO `permission_effects` (`effect_id`, `minimum_rank`) VALUES
	(0, 1),
	(102, 13),
	(178, 10),
	(187, 7),
	(600, 23),
	(766, 17),
	(767, 11),
	(768, 6),
	(769, 12),
	(770, 14),
	(772, 15),
	(773, 16),
	(774, 19),
	(775, 18),
	(776, 20),
	(779, 21),
	(780, 23),
	(781, 8),
	(782, 5),
	(783, 9),
	(786, 2);
/*!40000 ALTER TABLE `permission_effects` ENABLE KEYS */;

-- Dumping structure for table hylib.permission_effects_overrides
DROP TABLE IF EXISTS `permission_effects_overrides`;
CREATE TABLE IF NOT EXISTS `permission_effects_overrides` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `effect_id` int(11) NOT NULL DEFAULT 1,
  `player_id` int(11) DEFAULT NULL,
  `enabled` enum('1','0') NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `player_id` (`player_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.permission_effects_overrides: ~6 rows (approximately)
DELETE FROM `permission_effects_overrides`;
/*!40000 ALTER TABLE `permission_effects_overrides` DISABLE KEYS */;
INSERT INTO `permission_effects_overrides` (`id`, `effect_id`, `player_id`, `enabled`) VALUES
	(3, 823, 2, '1'),
	(4, 823, 1, '1'),
	(5, 1, 1, '1'),
	(6, 1, 3, '1'),
	(8, 1, 2, '1'),
	(9, 1, 3, '1');
/*!40000 ALTER TABLE `permission_effects_overrides` ENABLE KEYS */;

-- Dumping structure for table hylib.permission_perks
DROP TABLE IF EXISTS `permission_perks`;
CREATE TABLE IF NOT EXISTS `permission_perks` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(75) DEFAULT 'FULL_CHAT',
  `data` varchar(255) DEFAULT '',
  `override_rank` enum('1','0') DEFAULT '0',
  `override_default` enum('1','0') DEFAULT '0',
  `min_rank` int(11) DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.permission_perks: ~20 rows (approximately)
DELETE FROM `permission_perks`;
/*!40000 ALTER TABLE `permission_perks` DISABLE KEYS */;
INSERT INTO `permission_perks` (`id`, `title`, `data`, `override_rank`, `override_default`, `min_rank`) VALUES
	(1, 'SAFE_CHAT', '', '1', '1', 1),
	(2, 'USE_GUIDE_TOOL', 'requirement.unfulfilled.helper_le', '0', '1', 2),
	(3, 'GIVE_GUIDE_TOURS', '', '0', '1', 2),
	(4, 'JUDGE_CHAT_REVIEWS', '', '0', '1', 3),
	(5, 'CALL_ON_HELPERS', '', '1', '1', 1),
	(6, 'CITIZEN', '', '1', '1', 1),
	(7, 'FULL_CHAT', '', '1', '1', 1),
	(8, 'TRADE', '', '1', '1', 1),
	(9, 'VOTE_IN_COMPETITIONS', 'requirement.unfulfilled.helper_level_2', '1', '1', 1),
	(10, 'NEW_UI', '', '1', '1', 1),
	(11, 'EXPERIMENTAL_CHAT_BETA', '', '1', '1', 1),
	(12, 'HEIGHTMAP_EDITOR_BETA', 'requirement.unfulfilled.feature_disabled', '0', '1', 1),
	(13, 'EXPERIMENTAL_TOOLBAR', '', '1', '1', 1),
	(16, 'CAMERA', '', '1', '1', 1),
	(17, 'BUILDER_AT_WORK', '', '1', '1', 1),
	(18, 'MOUSE_ZOOM', '', '1', '1', 1),
	(19, 'NAVIGATOR_PHASE_TWO_2014', '', '1', '1', 1),
	(20, 'NAVIGATOR_PHASE_TWO_2014', '', '1', '1', 1),
	(21, 'NAVIGATOR_ROOM_THUMBNAIL_CAMERA', '', '1', '1', 1),
	(22, 'MODTOOL', '', '1', '1', 14);
/*!40000 ALTER TABLE `permission_perks` ENABLE KEYS */;

-- Dumping structure for table hylib.permission_ranks
DROP TABLE IF EXISTS `permission_ranks`;
CREATE TABLE IF NOT EXISTS `permission_ranks` (
  `fuse` varchar(255) DEFAULT NULL,
  `min_rank` int(11) DEFAULT 1,
  `note` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.permission_ranks: ~14 rows (approximately)
DELETE FROM `permission_ranks`;
/*!40000 ALTER TABLE `permission_ranks` DISABLE KEYS */;
INSERT INTO `permission_ranks` (`fuse`, `min_rank`, `note`) VALUES
	('mod_tool', 6, 'The minimum rank of the user that can use the moderation tool.'),
	('room_enter_full', 3, 'The minimum rank of the user that can enter full rooms.'),
	('room_unkickable', 6, 'The minimum rank of the user than can\'t be kicked / banned from a room.'),
	('room_enter_locked', 6, 'The minimum rank of the user that can enter locked rooms.'),
	('about_detailed', 7, 'The minimum rank of the user that can view full info on about dialogue.'),
	('user_unbannable', 7, 'The minimum rank of the user who is unbannable.'),
	('room_staff_pick', 7, 'The minimum rank of the user able to "Add To staff picked rooms"'),
	('room_full_control', 7, 'The minimum rank of the user who has full access to all rooms.'),
	('bypass_filter', 9, 'The minimum rank of the user that can bypass the word filter.'),
	('undisconnectable', 7, 'The minimum rank of user that cannot be disconnected by another staff member.'),
	('bypass_flood', 7, 'The minimum rank of the user that can bypass the flood.'),
	('staff_chat', 6, 'The minimum rank of the user that can access staff chat.'),
	('room_see_whisper', 6, 'The minimum rank of the user who can see all whispers in the room.'),
	('about_stats', 7, 'The minimum rank of the user that can view online stats in the about command (Current online record & online record)');
/*!40000 ALTER TABLE `permission_ranks` ENABLE KEYS */;

-- Dumping structure for table hylib.pet_breeds
DROP TABLE IF EXISTS `pet_breeds`;
CREATE TABLE IF NOT EXISTS `pet_breeds` (
  `pet_type` int(11) NOT NULL DEFAULT 1,
  `pallet_id` int(11) NOT NULL DEFAULT 0,
  `level` enum('EPIC','RARE','UNCOMMON','COMMON') DEFAULT 'COMMON',
  PRIMARY KEY (`pet_type`,`pallet_id`) USING BTREE,
  KEY `pet_type` (`pet_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.pet_breeds: ~100 rows (approximately)
DELETE FROM `pet_breeds`;
/*!40000 ALTER TABLE `pet_breeds` DISABLE KEYS */;
INSERT INTO `pet_breeds` (`pet_type`, `pallet_id`, `level`) VALUES
	(24, 1, 'COMMON'),
	(24, 2, 'COMMON'),
	(24, 3, 'COMMON'),
	(24, 4, 'COMMON'),
	(24, 5, 'COMMON'),
	(24, 6, 'UNCOMMON'),
	(24, 7, 'UNCOMMON'),
	(24, 8, 'UNCOMMON'),
	(24, 9, 'UNCOMMON'),
	(24, 10, 'UNCOMMON'),
	(24, 11, 'UNCOMMON'),
	(24, 12, 'RARE'),
	(24, 13, 'RARE'),
	(24, 14, 'RARE'),
	(24, 15, 'RARE'),
	(24, 16, 'RARE'),
	(24, 17, 'RARE'),
	(24, 18, 'EPIC'),
	(24, 19, 'EPIC'),
	(24, 20, 'EPIC'),
	(25, 1, 'COMMON'),
	(25, 2, 'COMMON'),
	(25, 3, 'COMMON'),
	(25, 4, 'COMMON'),
	(25, 5, 'COMMON'),
	(25, 6, 'UNCOMMON'),
	(25, 7, 'UNCOMMON'),
	(25, 8, 'UNCOMMON'),
	(25, 9, 'UNCOMMON'),
	(25, 10, 'UNCOMMON'),
	(25, 11, 'UNCOMMON'),
	(25, 12, 'RARE'),
	(25, 13, 'RARE'),
	(25, 14, 'RARE'),
	(25, 15, 'RARE'),
	(25, 16, 'RARE'),
	(25, 17, 'RARE'),
	(25, 18, 'EPIC'),
	(25, 19, 'EPIC'),
	(25, 20, 'EPIC'),
	(28, 1, 'COMMON'),
	(28, 2, 'COMMON'),
	(28, 3, 'COMMON'),
	(28, 4, 'COMMON'),
	(28, 5, 'COMMON'),
	(28, 6, 'UNCOMMON'),
	(28, 7, 'UNCOMMON'),
	(28, 8, 'UNCOMMON'),
	(28, 9, 'UNCOMMON'),
	(28, 10, 'UNCOMMON'),
	(28, 11, 'UNCOMMON'),
	(28, 12, 'RARE'),
	(28, 13, 'RARE'),
	(28, 14, 'RARE'),
	(28, 15, 'RARE'),
	(28, 16, 'RARE'),
	(28, 17, 'RARE'),
	(28, 18, 'EPIC'),
	(28, 19, 'EPIC'),
	(28, 20, 'EPIC'),
	(29, 1, 'COMMON'),
	(29, 2, 'COMMON'),
	(29, 3, 'COMMON'),
	(29, 4, 'COMMON'),
	(29, 5, 'COMMON'),
	(29, 6, 'UNCOMMON'),
	(29, 7, 'UNCOMMON'),
	(29, 8, 'UNCOMMON'),
	(29, 9, 'UNCOMMON'),
	(29, 10, 'UNCOMMON'),
	(29, 11, 'UNCOMMON'),
	(29, 12, 'RARE'),
	(29, 13, 'RARE'),
	(29, 14, 'RARE'),
	(29, 15, 'RARE'),
	(29, 16, 'RARE'),
	(29, 17, 'RARE'),
	(29, 18, 'EPIC'),
	(29, 19, 'EPIC'),
	(29, 20, 'EPIC'),
	(30, 1, 'COMMON'),
	(30, 2, 'COMMON'),
	(30, 3, 'COMMON'),
	(30, 4, 'COMMON'),
	(30, 5, 'COMMON'),
	(30, 6, 'UNCOMMON'),
	(30, 7, 'UNCOMMON'),
	(30, 8, 'UNCOMMON'),
	(30, 9, 'UNCOMMON'),
	(30, 10, 'UNCOMMON'),
	(30, 11, 'UNCOMMON'),
	(30, 12, 'RARE'),
	(30, 13, 'RARE'),
	(30, 14, 'RARE'),
	(30, 15, 'RARE'),
	(30, 16, 'RARE'),
	(30, 17, 'RARE'),
	(30, 18, 'EPIC'),
	(30, 19, 'EPIC'),
	(30, 20, 'EPIC');
/*!40000 ALTER TABLE `pet_breeds` ENABLE KEYS */;

-- Dumping structure for table hylib.pet_data
DROP TABLE IF EXISTS `pet_data`;
CREATE TABLE IF NOT EXISTS `pet_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL DEFAULT 0,
  `room_id` int(11) DEFAULT 0,
  `pet_name` varchar(50) DEFAULT NULL,
  `race_id` int(11) DEFAULT 11,
  `type` int(11) DEFAULT NULL,
  `colour` text DEFAULT NULL,
  `scratches` int(11) NOT NULL DEFAULT 0,
  `level` int(11) NOT NULL DEFAULT 0,
  `happiness` int(11) NOT NULL DEFAULT 0,
  `experience` int(11) NOT NULL DEFAULT 0,
  `energy` int(11) NOT NULL DEFAULT 0,
  `hunger` int(11) NOT NULL DEFAULT 0,
  `x` int(11) DEFAULT 0,
  `y` int(11) DEFAULT 0,
  `z` double NOT NULL DEFAULT 0,
  `saddled` enum('true','false') DEFAULT 'false',
  `any_rider` enum('true','false') DEFAULT 'false',
  `hair_style` int(11) DEFAULT -1,
  `hair_colour` int(11) DEFAULT 0,
  `birthday` int(11) DEFAULT 0,
  `extra_data` varchar(500) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.pet_data: ~0 rows (approximately)
DELETE FROM `pet_data`;
/*!40000 ALTER TABLE `pet_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `pet_data` ENABLE KEYS */;

-- Dumping structure for table hylib.pet_messages
DROP TABLE IF EXISTS `pet_messages`;
CREATE TABLE IF NOT EXISTS `pet_messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pet_type` int(11) NOT NULL DEFAULT 0,
  `message_type` enum('GENERIC','SCRATCHED','WELCOME_HOME','HUNGRY','TIRED','SLEEPING') NOT NULL DEFAULT 'GENERIC',
  `message_string` varchar(100) NOT NULL DEFAULT 'Hiya %username%!!!',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.pet_messages: ~18 rows (approximately)
DELETE FROM `pet_messages`;
/*!40000 ALTER TABLE `pet_messages` DISABLE KEYS */;
INSERT INTO `pet_messages` (`id`, `pet_type`, `message_type`, `message_string`) VALUES
	(1, 2, 'WELCOME_HOME', 'Hiya %ownerName%!!!'),
	(2, 2, 'WELCOME_HOME', 'omg welcome home %ownerName%, I missed you so much!!!!!'),
	(3, 2, 'GENERIC', 'snap snap'),
	(4, 2, 'GENERIC', 'meow im a crocodile'),
	(5, 2, 'HUNGRY', 'get your ass here so i can munch on it'),
	(6, 2, 'HUNGRY', 'IM HUNGRY.. FEED ME'),
	(7, 2, 'TIRED', 'snap snap im so tired'),
	(8, 2, 'SCRATCHED', 'i love youuuu'),
	(9, 2, 'SCRATCHED', 'i was gonna eat you but then i got scratched'),
	(10, 9, 'GENERIC', 'snap snap im a furious snappin\' turtle'),
	(11, 9, 'WELCOME_HOME', 'welcome home bro!'),
	(12, 9, 'HUNGRY', 'im hungry'),
	(13, 9, 'TIRED', 'im tired'),
	(14, 9, 'SCRATCHED', 'thanks for scratchin me, bro!'),
	(15, -1, 'SLEEPING', '* Zzzzz zzzzzzzZ *'),
	(16, -1, 'HUNGRY', '* stomach rumbles *'),
	(17, -1, 'HUNGRY', '* Gggrrrrrrrr * I\'m so hungry!'),
	(18, -1, 'TIRED', 'Oohh I\'m exhausted!');
/*!40000 ALTER TABLE `pet_messages` ENABLE KEYS */;

-- Dumping structure for table hylib.pet_races
DROP TABLE IF EXISTS `pet_races`;
CREATE TABLE IF NOT EXISTS `pet_races` (
  `race_id` int(255) DEFAULT NULL,
  `colour1` int(255) DEFAULT NULL,
  `colour2` int(255) DEFAULT NULL,
  `has1colour` enum('1','0') DEFAULT NULL,
  `has2colour` enum('1','0') DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

-- Dumping data for table hylib.pet_races: 470 rows
DELETE FROM `pet_races`;
/*!40000 ALTER TABLE `pet_races` DISABLE KEYS */;
INSERT INTO `pet_races` (`race_id`, `colour1`, `colour2`, `has1colour`, `has2colour`) VALUES
	(12, 0, 0, '1', '0'),
	(12, 1, 1, '1', '0'),
	(12, 2, 2, '1', '0'),
	(12, 3, 3, '1', '0'),
	(12, 4, 4, '1', '0'),
	(12, 5, 5, '1', '0'),
	(15, 1, 2, '1', '0'),
	(15, 2, 3, '1', '0'),
	(15, 3, 4, '1', '0'),
	(15, 4, 5, '1', '0'),
	(15, 1, 6, '1', '0'),
	(15, 2, 7, '1', '0'),
	(15, 3, 8, '1', '0'),
	(15, 4, 9, '1', '0'),
	(15, 1, 10, '1', '0'),
	(15, 2, 11, '1', '0'),
	(15, 3, 12, '1', '0'),
	(15, 4, 13, '1', '0'),
	(15, 1, 14, '1', '0'),
	(15, 2, 15, '1', '0'),
	(15, 3, 16, '1', '0'),
	(15, 4, 17, '1', '0'),
	(15, 1, 18, '1', '0'),
	(15, 2, 19, '1', '0'),
	(15, 3, 20, '1', '0'),
	(15, 4, 21, '1', '0'),
	(15, 1, 22, '1', '0'),
	(15, 2, 23, '1', '0'),
	(15, 3, 24, '1', '0'),
	(15, 4, 25, '1', '0'),
	(15, 1, 26, '1', '0'),
	(15, 2, 27, '1', '0'),
	(15, 3, 28, '1', '0'),
	(15, 4, 29, '1', '0'),
	(15, 1, 30, '1', '0'),
	(15, 2, 31, '1', '0'),
	(15, 3, 32, '1', '0'),
	(15, 4, 33, '1', '0'),
	(15, 1, 34, '1', '0'),
	(15, 2, 35, '1', '0'),
	(15, 3, 36, '1', '0'),
	(15, 4, 37, '1', '0'),
	(15, 1, 38, '1', '0'),
	(15, 2, 39, '1', '0'),
	(15, 3, 40, '1', '0'),
	(15, 4, 41, '1', '0'),
	(15, 1, 42, '1', '0'),
	(15, 2, 43, '1', '0'),
	(15, 3, 44, '1', '0'),
	(15, 4, 45, '1', '0'),
	(15, 1, 46, '1', '0'),
	(15, 2, 47, '1', '0'),
	(15, 3, 48, '1', '0'),
	(15, 4, 49, '1', '0'),
	(0, 0, 0, '1', '0'),
	(0, 1, 1, '1', '0'),
	(0, 2, 2, '1', '0'),
	(0, 3, 3, '1', '0'),
	(0, 4, 4, '1', '0'),
	(0, 5, 5, '1', '0'),
	(0, 6, 6, '1', '0'),
	(0, 7, 7, '1', '0'),
	(0, 8, 8, '1', '0'),
	(0, 9, 9, '1', '0'),
	(0, 10, 10, '1', '0'),
	(0, 11, 11, '1', '0'),
	(0, 12, 12, '1', '0'),
	(0, 13, 13, '1', '0'),
	(0, 14, 14, '1', '0'),
	(0, 15, 15, '1', '0'),
	(0, 16, 16, '1', '0'),
	(0, 17, 17, '1', '0'),
	(0, 18, 18, '1', '0'),
	(0, 19, 19, '1', '0'),
	(0, 20, 20, '1', '0'),
	(0, 21, 21, '1', '0'),
	(0, 22, 22, '1', '0'),
	(0, 23, 23, '1', '0'),
	(0, 24, 24, '1', '0'),
	(1, 0, 0, '1', '0'),
	(1, 1, 1, '1', '0'),
	(1, 2, 2, '1', '0'),
	(1, 3, 3, '1', '0'),
	(1, 4, 4, '1', '0'),
	(1, 5, 5, '1', '0'),
	(1, 6, 6, '1', '0'),
	(1, 7, 7, '1', '0'),
	(1, 8, 8, '1', '0'),
	(1, 9, 9, '1', '0'),
	(1, 10, 10, '1', '0'),
	(1, 11, 11, '1', '0'),
	(1, 12, 12, '1', '0'),
	(1, 13, 13, '1', '0'),
	(1, 14, 14, '1', '0'),
	(1, 15, 15, '1', '0'),
	(1, 16, 16, '1', '0'),
	(1, 17, 17, '1', '0'),
	(1, 18, 18, '1', '0'),
	(1, 19, 19, '1', '0'),
	(1, 20, 20, '1', '0'),
	(1, 21, 21, '1', '0'),
	(1, 22, 22, '1', '0'),
	(1, 23, 23, '1', '0'),
	(1, 24, 24, '1', '0'),
	(10, 4, 4, '1', '0'),
	(9, 0, 0, '1', '0'),
	(9, 1, 1, '1', '0'),
	(9, 2, 2, '1', '0'),
	(9, 3, 3, '1', '0'),
	(9, 4, 4, '1', '0'),
	(9, 5, 5, '1', '0'),
	(9, 6, 6, '1', '0'),
	(9, 7, 7, '1', '0'),
	(9, 8, 8, '1', '0'),
	(11, 1, 1, '1', '0'),
	(11, 2, 2, '1', '0'),
	(11, 3, 3, '1', '0'),
	(11, 4, 4, '1', '0'),
	(11, 5, 5, '1', '0'),
	(11, 6, 6, '1', '0'),
	(11, 8, 8, '1', '0'),
	(11, 9, 9, '1', '0'),
	(11, 10, 10, '1', '0'),
	(11, 11, 11, '1', '0'),
	(11, 12, 12, '1', '0'),
	(11, 13, 13, '1', '0'),
	(11, 15, 15, '1', '0'),
	(11, 18, 18, '1', '0'),
	(8, 0, 0, '1', '0'),
	(8, 1, 1, '1', '0'),
	(8, 2, 2, '1', '0'),
	(8, 3, 3, '1', '0'),
	(8, 4, 4, '1', '0'),
	(8, 5, 5, '1', '0'),
	(8, 6, 6, '1', '0'),
	(8, 7, 7, '1', '0'),
	(8, 8, 8, '1', '0'),
	(8, 9, 9, '1', '0'),
	(8, 10, 10, '1', '0'),
	(8, 11, 11, '1', '0'),
	(8, 14, 14, '1', '0'),
	(3, 7, 7, '0', '1'),
	(3, 8, 8, '0', '1'),
	(3, 9, 9, '0', '1'),
	(3, 10, 10, '0', '1'),
	(3, 11, 11, '0', '1'),
	(5, 9, 9, '0', '1'),
	(8, 12, 12, '1', '0'),
	(8, 13, 13, '1', '0'),
	(7, 0, 0, '1', '0'),
	(7, 1, 1, '1', '0'),
	(7, 2, 2, '1', '0'),
	(7, 3, 3, '1', '0'),
	(7, 4, 4, '1', '0'),
	(7, 5, 5, '1', '0'),
	(7, 6, 6, '1', '0'),
	(7, 7, 7, '1', '0'),
	(5, 0, 0, '1', '0'),
	(5, 1, 1, '1', '0'),
	(5, 2, 2, '1', '0'),
	(5, 3, 3, '1', '0'),
	(5, 5, 5, '1', '0'),
	(5, 7, 7, '1', '0'),
	(5, 8, 8, '1', '0'),
	(3, 0, 0, '1', '0'),
	(3, 1, 1, '1', '0'),
	(3, 2, 2, '1', '0'),
	(3, 3, 3, '1', '0'),
	(3, 4, 4, '1', '0'),
	(3, 5, 5, '1', '0'),
	(3, 6, 6, '1', '0'),
	(4, 0, 0, '1', '0'),
	(4, 1, 1, '1', '0'),
	(4, 2, 2, '1', '0'),
	(4, 3, 3, '1', '0'),
	(2, 0, 0, '1', '0'),
	(2, 1, 1, '1', '0'),
	(2, 2, 2, '1', '0'),
	(2, 3, 3, '1', '0'),
	(2, 4, 4, '1', '0'),
	(2, 5, 5, '1', '0'),
	(2, 6, 6, '1', '0'),
	(2, 7, 7, '1', '0'),
	(2, 8, 8, '1', '0'),
	(2, 9, 9, '1', '0'),
	(2, 10, 10, '1', '0'),
	(2, 11, 11, '1', '0'),
	(6, 0, 0, '1', '0'),
	(6, 1, 1, '1', '0'),
	(6, 2, 2, '1', '0'),
	(6, 3, 3, '1', '0'),
	(6, 4, 4, '1', '0'),
	(6, 5, 5, '1', '0'),
	(6, 6, 6, '1', '0'),
	(6, 7, 7, '1', '0'),
	(6, 8, 8, '1', '0'),
	(6, 9, 9, '1', '0'),
	(6, 10, 10, '1', '0'),
	(6, 11, 11, '1', '0'),
	(6, 12, 12, '1', '0'),
	(10, 3, 3, '1', '0'),
	(10, 2, 2, '1', '0'),
	(10, 1, 1, '1', '0'),
	(10, 0, 0, '1', '0'),
	(22, 0, 0, '1', '0'),
	(21, 0, 0, '1', '0'),
	(17, 1, 1, '1', '0'),
	(17, 0, 0, '1', '0'),
	(17, 2, 2, '1', '0'),
	(17, 3, 3, '1', '0'),
	(17, 4, 4, '1', '0'),
	(18, 0, 0, '1', '0'),
	(19, 0, 0, '1', '0'),
	(20, 0, 0, '1', '0'),
	(26, 0, 0, '1', '0'),
	(26, 1, 1, '1', '0'),
	(26, 2, 2, '1', '0'),
	(26, 3, 3, '1', '0'),
	(26, 4, 4, '1', '0'),
	(26, 5, 5, '1', '0'),
	(26, 6, 6, '1', '0'),
	(5, 4, 4, '0', '1'),
	(5, 6, 6, '0', '1'),
	(27, 0, 0, '1', '0'),
	(5, 10, 10, '0', '1'),
	(5, 11, 11, '0', '1'),
	(8, 15, 15, '1', '0'),
	(8, 16, 16, '1', '0'),
	(8, 17, 17, '1', '0'),
	(9, 9, 9, '1', '0'),
	(9, 10, 10, '1', '0'),
	(9, 11, 11, '1', '0'),
	(11, 7, 7, '1', '0'),
	(11, 14, 14, '1', '0'),
	(11, 16, 16, '1', '0'),
	(11, 17, 17, '1', '0'),
	(12, 6, 6, '1', '0'),
	(12, 7, 7, '1', '0'),
	(12, 8, 8, '1', '0'),
	(12, 9, 9, '1', '0'),
	(12, 10, 10, '1', '0'),
	(14, 0, 0, '1', '0'),
	(14, 1, 1, '1', '0'),
	(14, 2, 2, '1', '0'),
	(14, 3, 3, '1', '0'),
	(14, 4, 4, '1', '0'),
	(14, 5, 5, '1', '0'),
	(14, 6, 6, '1', '0'),
	(14, 7, 7, '1', '0'),
	(14, 8, 8, '1', '0'),
	(14, 9, 9, '1', '0'),
	(14, 10, 10, '1', '0'),
	(14, 11, 11, '1', '0'),
	(14, 12, 12, '1', '0'),
	(14, 13, 13, '1', '0'),
	(14, 14, 14, '1', '0'),
	(24, 0, 0, '1', '0'),
	(24, 1, 1, '1', '0'),
	(24, 2, 2, '1', '0'),
	(24, 3, 3, '1', '0'),
	(24, 4, 4, '1', '0'),
	(24, 5, 5, '1', '0'),
	(24, 6, 6, '1', '0'),
	(24, 7, 7, '1', '0'),
	(24, 8, 8, '1', '0'),
	(24, 9, 9, '1', '0'),
	(24, 10, 10, '1', '0'),
	(24, 11, 11, '1', '0'),
	(24, 12, 12, '1', '0'),
	(24, 13, 13, '1', '0'),
	(24, 14, 14, '1', '0'),
	(24, 15, 15, '1', '0'),
	(24, 16, 16, '1', '0'),
	(24, 17, 17, '1', '0'),
	(24, 18, 18, '1', '0'),
	(24, 19, 19, '1', '0'),
	(25, 0, 0, '1', '0'),
	(25, 1, 1, '1', '0'),
	(25, 2, 2, '1', '0'),
	(25, 3, 3, '1', '0'),
	(25, 4, 4, '1', '0'),
	(25, 5, 5, '1', '0'),
	(25, 6, 6, '1', '0'),
	(25, 7, 7, '1', '0'),
	(25, 8, 8, '1', '0'),
	(25, 9, 9, '1', '0'),
	(25, 10, 10, '1', '0'),
	(25, 11, 11, '1', '0'),
	(25, 12, 12, '1', '0'),
	(25, 13, 13, '1', '0'),
	(25, 14, 14, '1', '0'),
	(25, 15, 15, '1', '0'),
	(25, 16, 16, '1', '0'),
	(25, 17, 17, '1', '0'),
	(25, 18, 18, '1', '0'),
	(25, 19, 19, '1', '0'),
	(28, 1, 1, '1', '0'),
	(28, 2, 2, '1', '0'),
	(28, 3, 3, '1', '0'),
	(28, 4, 4, '1', '0'),
	(28, 5, 5, '1', '0'),
	(28, 6, 6, '1', '0'),
	(28, 7, 7, '1', '0'),
	(28, 8, 8, '1', '0'),
	(28, 9, 9, '1', '0'),
	(28, 10, 10, '1', '0'),
	(28, 11, 11, '1', '0'),
	(28, 12, 12, '1', '0'),
	(28, 13, 13, '1', '0'),
	(28, 14, 14, '1', '0'),
	(28, 15, 15, '1', '0'),
	(28, 16, 16, '1', '0'),
	(28, 17, 17, '1', '0'),
	(28, 18, 18, '1', '0'),
	(28, 19, 19, '1', '0'),
	(28, 20, 20, '1', '0'),
	(29, 1, 1, '1', '0'),
	(29, 2, 2, '1', '0'),
	(29, 3, 3, '1', '0'),
	(29, 4, 4, '1', '0'),
	(29, 5, 5, '1', '0'),
	(29, 6, 6, '1', '0'),
	(29, 7, 7, '1', '0'),
	(29, 8, 8, '1', '0'),
	(29, 9, 9, '1', '0'),
	(29, 10, 10, '1', '0'),
	(29, 11, 11, '1', '0'),
	(29, 12, 12, '1', '0'),
	(29, 13, 13, '1', '0'),
	(29, 14, 14, '1', '0'),
	(29, 15, 15, '1', '0'),
	(29, 16, 16, '1', '0'),
	(29, 17, 17, '1', '0'),
	(29, 18, 18, '1', '0'),
	(29, 19, 19, '1', '0'),
	(29, 20, 20, '1', '0'),
	(30, 1, 1, '1', '0'),
	(30, 2, 2, '1', '0'),
	(30, 3, 3, '1', '0'),
	(30, 4, 4, '1', '0'),
	(30, 5, 5, '1', '0'),
	(30, 6, 6, '1', '0'),
	(30, 7, 7, '1', '0'),
	(30, 8, 8, '1', '0'),
	(30, 9, 9, '1', '0'),
	(30, 10, 10, '1', '0'),
	(30, 11, 11, '1', '0'),
	(30, 12, 12, '1', '0'),
	(30, 13, 13, '1', '0'),
	(30, 14, 14, '1', '0'),
	(30, 15, 15, '1', '0'),
	(30, 16, 16, '1', '0'),
	(30, 17, 17, '1', '0'),
	(30, 18, 18, '1', '0'),
	(30, 19, 19, '1', '0'),
	(30, 20, 20, '1', '0'),
	(31, 0, 0, '1', '0'),
	(31, 1, 1, '1', '0'),
	(31, 2, 2, '1', '0'),
	(31, 3, 3, '1', '0'),
	(31, 4, 4, '1', '0'),
	(31, 5, 5, '1', '0'),
	(31, 6, 6, '1', '0'),
	(31, 7, 7, '1', '0'),
	(31, 8, 8, '1', '0'),
	(31, 9, 9, '1', '0'),
	(33, 0, 0, '1', '0'),
	(33, 1, 1, '1', '0'),
	(33, 2, 2, '1', '0'),
	(33, 3, 3, '1', '0'),
	(33, 4, 4, '1', '0'),
	(33, 5, 5, '1', '0'),
	(33, 6, 6, '1', '0'),
	(33, 7, 7, '1', '0'),
	(33, 8, 8, '1', '0'),
	(33, 9, 9, '1', '0'),
	(33, 10, 10, '1', '0'),
	(33, 11, 11, '1', '0'),
	(33, 12, 12, '1', '0'),
	(33, 13, 13, '1', '0'),
	(33, 14, 14, '1', '0'),
	(33, 15, 15, '1', '0'),
	(33, 16, 16, '1', '0'),
	(33, 17, 17, '1', '0'),
	(33, 18, 18, '1', '0'),
	(33, 19, 19, '1', '0'),
	(33, 20, 20, '1', '0'),
	(34, 0, 0, '1', '0'),
	(34, 1, 1, '1', '0'),
	(34, 2, 2, '1', '0'),
	(34, 3, 3, '1', '0'),
	(34, 4, 4, '1', '0'),
	(34, 5, 5, '1', '0'),
	(34, 6, 6, '1', '0'),
	(34, 7, 7, '1', '0'),
	(34, 8, 8, '1', '0'),
	(34, 9, 9, '1', '0'),
	(34, 10, 10, '1', '0'),
	(34, 11, 11, '1', '0'),
	(34, 12, 12, '1', '0'),
	(34, 13, 13, '1', '0'),
	(34, 14, 14, '1', '0'),
	(34, 15, 15, '1', '0'),
	(34, 16, 16, '1', '0'),
	(34, 17, 17, '1', '0'),
	(34, 18, 18, '1', '0'),
	(34, 19, 19, '1', '0'),
	(27, 1, 1, '1', '0'),
	(27, 2, 2, '1', '0'),
	(27, 3, 3, '1', '0'),
	(27, 4, 4, '1', '0'),
	(27, 5, 5, '1', '0'),
	(27, 6, 6, '1', '0'),
	(27, 7, 7, '1', '0'),
	(27, 8, 8, '1', '0'),
	(27, 9, 9, '1', '0'),
	(27, 10, 10, '1', '0'),
	(27, 11, 11, '1', '0'),
	(27, 12, 12, '1', '0'),
	(27, 13, 13, '1', '0'),
	(27, 14, 14, '1', '0'),
	(27, 15, 15, '1', '0'),
	(27, 16, 16, '1', '0'),
	(27, 17, 17, '1', '0'),
	(27, 18, 18, '1', '0'),
	(27, 19, 19, '1', '0'),
	(35, 1, 1, '1', '1'),
	(36, 1, 1, '1', '1'),
	(37, 1, 1, '1', '1'),
	(38, 1, 1, '1', '1'),
	(39, 40, 40, '1', '1'),
	(40, 1, 1, '1', '1'),
	(41, 40, 40, '1', '1'),
	(42, 1, 1, '1', '1'),
	(43, 1, 1, '1', '1'),
	(44, 1, 1, '1', '1'),
	(45, 1, 1, '1', '1'),
	(46, 1, 1, '1', '1'),
	(47, 2, 2, '1', '0'),
	(47, 4, 4, '1', '0'),
	(47, 5, 5, '1', '0'),
	(47, 6, 6, '1', '0'),
	(47, 7, 7, '1', '0'),
	(47, 8, 8, '1', '0'),
	(47, 9, 9, '1', '0'),
	(47, 10, 10, '1', '0'),
	(47, 11, 11, '1', '0'),
	(47, 12, 12, '1', '0'),
	(47, 13, 13, '1', '0'),
	(47, 14, 14, '1', '0'),
	(47, 15, 15, '1', '0'),
	(47, 16, 16, '1', '0'),
	(47, 17, 17, '1', '0'),
	(47, 18, 18, '1', '0'),
	(48, 1, 1, '1', '1'),
	(49, 1, 1, '1', '1'),
	(50, 1, 1, '1', '1'),
	(51, 1, 1, '1', '1'),
	(52, 1, 1, '1', '1'),
	(53, 1, 1, '1', '1'),
	(54, 1, 1, '1', '1'),
	(55, 1, 1, '1', '1'),
	(56, 1, 1, '1', '1'),
	(57, 1, 1, '1', '1'),
	(58, 1, 1, '1', '1'),
	(59, 1, 1, '1', '1'),
	(60, 1, 1, '1', '1'),
	(61, 1, 1, '1', '1'),
	(62, 1, 1, '1', '1'),
	(63, 1, 1, '1', '1');
/*!40000 ALTER TABLE `pet_races` ENABLE KEYS */;

-- Dumping structure for table hylib.pet_transformable
DROP TABLE IF EXISTS `pet_transformable`;
CREATE TABLE IF NOT EXISTS `pet_transformable` (
  `name` varchar(50) NOT NULL,
  `data` varchar(50) NOT NULL COMMENT 'The first number is the pet ID, the rest is what determine the colours, hair etc.',
  PRIMARY KEY (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.pet_transformable: ~18 rows (approximately)
DELETE FROM `pet_transformable`;
/*!40000 ALTER TABLE `pet_transformable` DISABLE KEYS */;
INSERT INTO `pet_transformable` (`name`, `data`) VALUES
	('araÃ±a', '8 0 FFFFFF 2 2 -1 0 3 -1 0#0'),
	('caballo', '15 3 FFFFFF 2 2 -1 0 3 -1 0#3'),
	('cerdo', '5 0 FFFFFF 2 2 -1 0 3 -1 0#0'),
	('cocodrilo', '2 4 96E75A 2 2 -1 0 3 -1 0#4'),
	('conejo', '17 0 FFFFFF 0 0 0 0 0 0 0#0'),
	('dragon', '12 0 FFFFFF 2 2 -1 0 3 -1 0#0'),
	('gato', '1 0 F5E759 2 2 -1 0 3 -1 0#0'),
	('leon', '6 0 FFFFFF 2 2 -1 0 3 -1 0#0'),
	('mono', '14 0 FFFFFF 2 2 -1 0 3 -1 0#0'),
	('normal', ''),
	('oso', '4 0 FFFFFF 2 2 -1 0 3 -1 0#0'),
	('perro', '0 15 FEE4B2 2 2 -1 0 3 -1 0#15'),
	('planta', '16 0 FFFFFF 0 0 0 0 0 0 0#0'),
	('pollo', '10 0 FFFFFF 2 2 -1 0 3 -1 0#0'),
	('rana', '11 12 FFFFFF 2 2 -1 0 3 -1 0#12'),
	('rinoceronte', '7 0 CCCCCC 2 2 -1 0 3 -1 0#0'),
	('terrier', '3 0 FFFFFF 2 2 -1 0 3 -1 0#0'),
	('tortuga', '9 0 FFFFFF 2 2 -1 0 3 -1 0#0');
/*!40000 ALTER TABLE `pet_transformable` ENABLE KEYS */;

-- Dumping structure for table hylib.players
DROP TABLE IF EXISTS `players`;
CREATE TABLE IF NOT EXISTS `players` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(75) DEFAULT 'Avatar',
  `figure` varchar(255) DEFAULT 'hr-828-31.ch-255-82.sh-3089-64.hd-180-10.lg-3058-64',
  `motto` varchar(100) DEFAULT '',
  `credits` int(11) DEFAULT 5000,
  `vip_points` int(11) DEFAULT 0,
  `activity_points` int(11) NOT NULL DEFAULT 0,
  `rank` int(3) DEFAULT 1,
  `auth_ticket` varchar(100) DEFAULT NULL,
  `gender` enum('M','F') NOT NULL DEFAULT 'M',
  `reg_timestamp` int(11) NOT NULL DEFAULT 0,
  `reg_date` varchar(12) DEFAULT '10/06/2013',
  `staff_function` varchar(255) DEFAULT NULL,
  `last_online` int(11) DEFAULT 0,
  `online` enum('1','0') DEFAULT '0',
  `password` varchar(72) DEFAULT '',
  `email` varchar(72) DEFAULT '',
  `last_ip` varchar(120) DEFAULT NULL,
  `reg_ip` varchar(120) DEFAULT NULL,
  `ip_last` varchar(120) DEFAULT NULL,
  `ip_reg` varchar(120) DEFAULT NULL,
  `vip` enum('1','0') DEFAULT '0',
  `fame_occult` enum('1','0') DEFAULT '0',
  `achievement_points` int(11) DEFAULT 0,
  `favourite_group` int(11) DEFAULT 0,
  `chat_ticket` varchar(50) DEFAULT '',
  `quest_id` int(11) DEFAULT 0,
  `time_muted` int(11) DEFAULT 0,
  `name_colour` varchar(50) DEFAULT '000000',
  `dob` int(11) DEFAULT 0,
  `hk_code` int(11) DEFAULT 0,
  `seckey` varchar(999) DEFAULT NULL,
  `seasonal_points` int(11) DEFAULT 0,
  `user_likes` int(11) DEFAULT 0,
  `vip_timestamp` int(11) DEFAULT 0,
  `pin` varchar(4) DEFAULT NULL,
  `teamrank` int(1) DEFAULT 0,
  `fbid` varchar(255) DEFAULT NULL,
  `fbenable` enum('0','1','2') DEFAULT '1',
  `google_secret_code` varchar(200) DEFAULT NULL,
  `2fa_status` int(1) NOT NULL DEFAULT 0,
  `rank_team` varchar(255) NOT NULL DEFAULT 'lid',
  `referer` varchar(15) DEFAULT NULL,
  `pin_panel` varchar(15) DEFAULT NULL,
  `account_disabled` enum('1','0') DEFAULT '0',
  `staff_access` enum('1','0') DEFAULT '0',
  `staff_occult` enum('1','0') DEFAULT '0',
  `birthday` int(11) DEFAULT NULL,
  `event_points` int(11) DEFAULT NULL,
  `promo_points` int(11) DEFAULT NULL,
  `black_money` int(11) DEFAULT 0,
  `tag` varchar(250) DEFAULT '',
  `job` varchar(250) DEFAULT '',
  `view_points` int(11) DEFAULT 0,
  `banner_id` int(11) DEFAULT 0,
  `color_primary` varchar(250) DEFAULT 'rgba(255, 255 ,255, .81)',
  `color_text` varchar(250) DEFAULT 'rgba(0,0,0)',
  `color_secondary` varchar(250) DEFAULT 'rgba(53, 196, 96)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.players: ~39 rows (approximately)
DELETE FROM `players`;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` (`id`, `username`, `figure`, `motto`, `credits`, `vip_points`, `activity_points`, `rank`, `auth_ticket`, `gender`, `reg_timestamp`, `reg_date`, `staff_function`, `last_online`, `online`, `password`, `email`, `last_ip`, `reg_ip`, `ip_last`, `ip_reg`, `vip`, `fame_occult`, `achievement_points`, `favourite_group`, `chat_ticket`, `quest_id`, `time_muted`, `name_colour`, `dob`, `hk_code`, `seckey`, `seasonal_points`, `user_likes`, `vip_timestamp`, `pin`, `teamrank`, `fbid`, `fbenable`, `google_secret_code`, `2fa_status`, `rank_team`, `referer`, `pin_panel`, `account_disabled`, `staff_access`, `staff_occult`, `birthday`, `event_points`, `promo_points`, `black_money`, `tag`, `job`, `view_points`, `banner_id`, `color_primary`, `color_text`, `color_secondary`) VALUES
	(1, 'Systemaccount', 'sh-725-62.hd-600-1383.ch-635-95.lg-700-110.ha-1015-62', '', 0, 0, 0, 1, NULL, 'F', 1672800979, '10/06/2013', 'tarea admini', 1672800979, '0', '$2y$10$Jh6niWNHD/9yj8UJeHB2teIZ1ki/J.jws07YsJAuKj1bBw7IuleZ6', 'dasdasd@gmail.com', NULL, NULL, '45.189.242.36', '45.189.242.36', '0', '0', 0, 0, '', 0, 0, '000000', 0, 0, NULL, 0, 0, 0, NULL, 0, NULL, '1', NULL, 0, 'lid', NULL, '1234', '0', '0', '0', 1163987779, NULL, NULL, 0, '', '', 0, 0, 'rgba(255, 255 ,255, .81)', 'rgba(0,0,0)', 'rgba(53, 196, 96)'),
/*!40000 ALTER TABLE `players` ENABLE KEYS */;

-- Dumping structure for table hylib.player_access
DROP TABLE IF EXISTS `player_access`;
CREATE TABLE IF NOT EXISTS `player_access` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL DEFAULT 0,
  `hardware_id` varchar(255) NOT NULL DEFAULT '0',
  `ip_address` varchar(50) NOT NULL DEFAULT '0',
  `timestamp` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `player_id_hardware_id_ip_address` (`player_id`,`hardware_id`,`ip_address`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_access: ~0 rows (approximately)
DELETE FROM `player_access`;
/*!40000 ALTER TABLE `player_access` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_access` ENABLE KEYS */;

-- Dumping structure for table hylib.player_achievements
DROP TABLE IF EXISTS `player_achievements`;
CREATE TABLE IF NOT EXISTS `player_achievements` (
  `player_id` int(11) NOT NULL DEFAULT 0,
  `group` varchar(50) NOT NULL DEFAULT '',
  `level` tinyint(4) NOT NULL DEFAULT 1,
  `progress` int(11) NOT NULL DEFAULT 0,
  UNIQUE KEY `player_id_group` (`player_id`,`group`) USING BTREE,
  KEY `player_id` (`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_achievements: ~225 rows (approximately)
DELETE FROM `player_achievements`;
/*!40000 ALTER TABLE `player_achievements` DISABLE KEYS */;
INSERT INTO `player_achievements` (`player_id`, `group`, `level`, `progress`) VALUES
	(1, 'ACH_AllTimeHotelPresence', 1, 2),
/*!40000 ALTER TABLE `player_achievements` ENABLE KEYS */;

-- Dumping structure for table hylib.player_badges
DROP TABLE IF EXISTS `player_badges`;
CREATE TABLE IF NOT EXISTS `player_badges` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `badge_code` varchar(50) DEFAULT '',
  `slot` int(10) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `player_id_badge_code` (`player_id`,`badge_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_badges: ~138 rows (approximately)
DELETE FROM `player_badges`;
/*!40000 ALTER TABLE `player_badges` DISABLE KEYS */;
INSERT INTO `player_badges` (`id`, `player_id`, `badge_code`, `slot`) VALUES
	(1, 1, 'ACH_Login1', 0),
/*!40000 ALTER TABLE `player_badges` ENABLE KEYS */;

-- Dumping structure for table hylib.player_calendar
DROP TABLE IF EXISTS `player_calendar`;
CREATE TABLE IF NOT EXISTS `player_calendar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL,
  `day` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.player_calendar: ~0 rows (approximately)
DELETE FROM `player_calendar`;
/*!40000 ALTER TABLE `player_calendar` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_calendar` ENABLE KEYS */;

-- Dumping structure for table hylib.player_clothing
DROP TABLE IF EXISTS `player_clothing`;
CREATE TABLE IF NOT EXISTS `player_clothing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `player_id` (`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_clothing: ~0 rows (approximately)
DELETE FROM `player_clothing`;
/*!40000 ALTER TABLE `player_clothing` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_clothing` ENABLE KEYS */;

-- Dumping structure for table hylib.player_cms_event
DROP TABLE IF EXISTS `player_cms_event`;
CREATE TABLE IF NOT EXISTS `player_cms_event` (
  `user_id` int(250) NOT NULL,
  `get_diamonds` enum('0','1') NOT NULL DEFAULT '1',
  `get_duckets` enum('0','1') NOT NULL DEFAULT '1',
  `fecha` timestamp NOT NULL DEFAULT current_timestamp(),
  `tickets` int(11) NOT NULL DEFAULT 0,
  `tickets_duckets` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_cms_event: ~0 rows (approximately)
DELETE FROM `player_cms_event`;
/*!40000 ALTER TABLE `player_cms_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_cms_event` ENABLE KEYS */;

-- Dumping structure for table hylib.player_effects
DROP TABLE IF EXISTS `player_effects`;
CREATE TABLE IF NOT EXISTS `player_effects` (
  `player_id` int(11) NOT NULL,
  `effect_id` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`effect_id`) USING BTREE,
  KEY `player_id` (`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_effects: ~0 rows (approximately)
DELETE FROM `player_effects`;
/*!40000 ALTER TABLE `player_effects` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_effects` ENABLE KEYS */;

-- Dumping structure for table hylib.player_events
DROP TABLE IF EXISTS `player_events`;
CREATE TABLE IF NOT EXISTS `player_events` (
  `player_id` int(11) NOT NULL,
  `events` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_events: ~0 rows (approximately)
DELETE FROM `player_events`;
/*!40000 ALTER TABLE `player_events` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_events` ENABLE KEYS */;

-- Dumping structure for table hylib.player_favourite_rooms
DROP TABLE IF EXISTS `player_favourite_rooms`;
CREATE TABLE IF NOT EXISTS `player_favourite_rooms` (
  `player_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`room_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_favourite_rooms: ~0 rows (approximately)
DELETE FROM `player_favourite_rooms`;
/*!40000 ALTER TABLE `player_favourite_rooms` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_favourite_rooms` ENABLE KEYS */;

-- Dumping structure for table hylib.player_gamecenter
DROP TABLE IF EXISTS `player_gamecenter`;
CREATE TABLE IF NOT EXISTS `player_gamecenter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `current_points` int(11) NOT NULL,
  `last_points` int(11) NOT NULL,
  `last_game` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.player_gamecenter: ~0 rows (approximately)
DELETE FROM `player_gamecenter`;
/*!40000 ALTER TABLE `player_gamecenter` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_gamecenter` ENABLE KEYS */;

-- Dumping structure for table hylib.player_mistery
DROP TABLE IF EXISTS `player_mistery`;
CREATE TABLE IF NOT EXISTS `player_mistery` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `mistery_key` varchar(50) DEFAULT '',
  `mistery_box` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `player_id_badge_code` (`player_id`,`mistery_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.player_mistery: ~0 rows (approximately)
DELETE FROM `player_mistery`;
/*!40000 ALTER TABLE `player_mistery` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_mistery` ENABLE KEYS */;

-- Dumping structure for table hylib.player_navigator_view_modes
DROP TABLE IF EXISTS `player_navigator_view_modes`;
CREATE TABLE IF NOT EXISTS `player_navigator_view_modes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `view_mode` int(11) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `player_id_category` (`player_id`,`category`) USING BTREE,
  KEY `player_id` (`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_navigator_view_modes: ~0 rows (approximately)
DELETE FROM `player_navigator_view_modes`;
/*!40000 ALTER TABLE `player_navigator_view_modes` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_navigator_view_modes` ENABLE KEYS */;

-- Dumping structure for table hylib.player_notifications
DROP TABLE IF EXISTS `player_notifications`;
CREATE TABLE IF NOT EXISTS `player_notifications` (
  `user_id` int(250) NOT NULL,
  `home_comment` enum('0','1') NOT NULL DEFAULT '1',
  `forum_like` enum('0','1') NOT NULL DEFAULT '1',
  `forum_like_tema` enum('0','1') NOT NULL DEFAULT '1',
  `forum_comment` enum('0','1') NOT NULL DEFAULT '1',
  `happyb` enum('0','1') NOT NULL DEFAULT '1',
  `badge_veri` enum('0','1') NOT NULL DEFAULT '1',
  `banco_dk` enum('0','1') NOT NULL DEFAULT '1',
  `recargas` enum('0','1') NOT NULL DEFAULT '1',
  `buybadge` enum('0','1') NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_notifications: ~0 rows (approximately)
DELETE FROM `player_notifications`;
/*!40000 ALTER TABLE `player_notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_notifications` ENABLE KEYS */;

-- Dumping structure for table hylib.player_photos
DROP TABLE IF EXISTS `player_photos`;
CREATE TABLE IF NOT EXISTS `player_photos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `timestamp` int(11) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `photo` (`photo`) USING BTREE,
  KEY `player_id` (`player_id`) USING BTREE,
  KEY `room_id` (`room_id`) USING BTREE,
  KEY `player_id_room_id` (`player_id`,`room_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.player_photos: ~7 rows (approximately)
DELETE FROM `player_photos`;
/*!40000 ALTER TABLE `player_photos` DISABLE KEYS */;
INSERT INTO `player_photos` (`id`, `player_id`, `room_id`, `photo`, `timestamp`, `fecha`) VALUES
	(1, 11, 3, 'https://Habbo.eu/camara/11_1673169718.png', -1867526, '2023-01-08 09:21:59'),
	(3, 13, 5, 'https://Habbo.eu/camara/13_1673205332.png', -1831906, '2023-01-08 19:15:39'),
	(4, 12, 2, 'https://Habbo.eu/camara/12_1673263178.png', -1774057, '2023-01-09 11:19:47'),
	(5, 11, 1, 'https://Habbo.eu/camara/11_1673295260.png', -1741981, '2023-01-09 20:14:23'),
	(6, 11, 2, 'https://Habbo.eu/camara/11_1673299001.png', -1738242, '2023-01-09 21:16:43'),
	(7, 12, 12, 'https://Habbo.eu/camara/12_1673300317.png', -1736922, '2023-01-09 21:38:43'),
	(8, 26, 17, 'https://Habbo.eu/camara/26_1673382961.png', -1654237, '2023-01-10 20:36:47'),
	(9, 12, 19, 'C:\\inetpub\\wwwroot\\Habbo\\swfs\\c_images\\camera\\%thumbnail%.png', -873164, '2023-01-19 21:34:41'),
	(10, 13, 29, 'C:\\inetpub\\wwwroot\\Habbo\\swfs\\c_images\\camera\\', -872829, '2023-01-19 21:40:16');
/*!40000 ALTER TABLE `player_photos` ENABLE KEYS */;

-- Dumping structure for table hylib.player_quest_progression
DROP TABLE IF EXISTS `player_quest_progression`;
CREATE TABLE IF NOT EXISTS `player_quest_progression` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT 0,
  `quest_id` int(11) DEFAULT 0,
  `progress` int(11) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `player_id` (`player_id`) USING BTREE,
  KEY `quest_id` (`quest_id`) USING BTREE,
  KEY `playerId_questId` (`player_id`,`quest_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_quest_progression: ~0 rows (approximately)
DELETE FROM `player_quest_progression`;
/*!40000 ALTER TABLE `player_quest_progression` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_quest_progression` ENABLE KEYS */;

-- Dumping structure for table hylib.player_recent_purchases
DROP TABLE IF EXISTS `player_recent_purchases`;
CREATE TABLE IF NOT EXISTS `player_recent_purchases` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL DEFAULT 0,
  `catalog_item` int(11) NOT NULL DEFAULT 0,
  `amount` int(11) NOT NULL DEFAULT 0,
  `data` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `player_id` (`player_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=370 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_recent_purchases: ~347 rows (approximately)
DELETE FROM `player_recent_purchases`;
/*!40000 ALTER TABLE `player_recent_purchases` DISABLE KEYS */;
INSERT INTO `player_recent_purchases` (`id`, `player_id`, `catalog_item`, `amount`, `data`) VALUES
	
/*!40000 ALTER TABLE `player_recent_purchases` ENABLE KEYS */;

-- Dumping structure for table hylib.player_relationships
DROP TABLE IF EXISTS `player_relationships`;
CREATE TABLE IF NOT EXISTS `player_relationships` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `level` enum('poop','bobba','smile','heart') DEFAULT 'smile',
  `partner` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_relationships: ~3 rows (approximately)
DELETE FROM `player_relationships`;
/*!40000 ALTER TABLE `player_relationships` DISABLE KEYS */;
INSERT INTO `player_relationships` (`id`, `player_id`, `level`, `partner`) VALUES
	(1, 11, 'smile', 12),
	(2, 17, 'smile', 13),
	(3, 27, 'heart', 18);
/*!40000 ALTER TABLE `player_relationships` ENABLE KEYS */;

-- Dumping structure for table hylib.player_rewards
DROP TABLE IF EXISTS `player_rewards`;
CREATE TABLE IF NOT EXISTS `player_rewards` (
  `code` varchar(255) NOT NULL,
  `badge` varchar(20) NOT NULL,
  `vip_points` int(11) NOT NULL DEFAULT 0,
  `seasonal_points` int(11) NOT NULL DEFAULT 0,
  `active` enum('1','0') NOT NULL DEFAULT '1',
  PRIMARY KEY (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_rewards: ~0 rows (approximately)
DELETE FROM `player_rewards`;
/*!40000 ALTER TABLE `player_rewards` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_rewards` ENABLE KEYS */;

-- Dumping structure for table hylib.player_rewards_redeemed
DROP TABLE IF EXISTS `player_rewards_redeemed`;
CREATE TABLE IF NOT EXISTS `player_rewards_redeemed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT 0,
  `reward_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `player_id_reward_code` (`player_id`,`reward_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_rewards_redeemed: ~0 rows (approximately)
DELETE FROM `player_rewards_redeemed`;
/*!40000 ALTER TABLE `player_rewards_redeemed` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_rewards_redeemed` ENABLE KEYS */;

-- Dumping structure for table hylib.player_room_visits
DROP TABLE IF EXISTS `player_room_visits`;
CREATE TABLE IF NOT EXISTS `player_room_visits` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL DEFAULT 0,
  `room_id` int(11) NOT NULL DEFAULT 0,
  `time_enter` int(11) NOT NULL DEFAULT 0,
  `time_exit` int(11) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `player_id` (`player_id`) USING BTREE,
  KEY `room_id` (`room_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=741 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_room_visits: ~656 rows (approximately)
DELETE FROM `player_room_visits`;
/*!40000 ALTER TABLE `player_room_visits` DISABLE KEYS */;
INSERT INTO `player_room_visits` (`id`, `player_id`, `room_id`, `time_enter`, `time_exit`) VALUES
	(1, 1, 1, 1673140085, 1673140109),
	
/*!40000 ALTER TABLE `player_room_visits` ENABLE KEYS */;

-- Dumping structure for table hylib.player_saved_searches
DROP TABLE IF EXISTS `player_saved_searches`;
CREATE TABLE IF NOT EXISTS `player_saved_searches` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT 0,
  `view` varchar(50) DEFAULT 'myworld_view',
  `search_query` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `player_id` (`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_saved_searches: ~0 rows (approximately)
DELETE FROM `player_saved_searches`;
/*!40000 ALTER TABLE `player_saved_searches` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_saved_searches` ENABLE KEYS */;

-- Dumping structure for table hylib.player_settings
DROP TABLE IF EXISTS `player_settings`;
CREATE TABLE IF NOT EXISTS `player_settings` (
  `player_id` int(10) NOT NULL DEFAULT 0,
  `allow_follow` enum('1','0') NOT NULL DEFAULT '1',
  `volume` text DEFAULT NULL,
  `profile_picture` text DEFAULT NULL,
  `profile_cover` text DEFAULT NULL,
  `hide_online` enum('1','0') DEFAULT '0',
  `hide_inroom` enum('1','0') DEFAULT '0',
  `allow_friend_requests` enum('1','0') DEFAULT '1',
  `allow_trade` enum('1','0') DEFAULT '1',
  `home_room` int(11) DEFAULT 0,
  `wardrobe` text DEFAULT NULL,
  `playlist` text DEFAULT NULL,
  `chat_oldstyle` enum('1','0') DEFAULT '0',
  `follow_friend_mode` enum('EVERYBODY','FRIENDS','NOBODY') DEFAULT 'EVERYBODY',
  `ignore_invites` enum('1','0') DEFAULT '0',
  `allow_mimic` enum('1','0') DEFAULT '1',
  `navigator_x` int(11) DEFAULT 68,
  `navigator_y` int(11) DEFAULT 42,
  `navigator_width` int(11) DEFAULT 425,
  `navigator_height` int(11) DEFAULT 592,
  `navigator_show_searches` enum('1','0') DEFAULT '0',
  `ignore_events` enum('1','0') DEFAULT '0',
  `disable_whisper` enum('0','1') DEFAULT '0',
  `send_login_notif` enum('0','1') DEFAULT '0',
  `mention_type` enum('ALL','NONE','FRIENDS') DEFAULT 'ALL',
  `personalstaff` enum('0','1') DEFAULT '1',
  `event_type` varchar(255) DEFAULT '1',
  `camera_follow` enum('0','1') DEFAULT '1',
  `karma` int(11) DEFAULT 0,
  `prestige` int(11) DEFAULT 0,
  `personal_pin` varchar(255) DEFAULT '2503',
  `nux` int(11) DEFAULT 6,
  `claimed_goal` enum('1','0') DEFAULT '0',
  `royale_xp` int(11) DEFAULT 0,
  `bubble_id` int(11) DEFAULT 0,
  `room_tool_state` int(11) DEFAULT 0,
  KEY `avatar_setting` (`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_settings: ~13 rows (approximately)
DELETE FROM `player_settings`;
/*!40000 ALTER TABLE `player_settings` DISABLE KEYS */;
INSERT INTO `player_settings` (`player_id`, `allow_follow`, `volume`, `profile_picture`, `profile_cover`, `hide_online`, `hide_inroom`, `allow_friend_requests`, `allow_trade`, `home_room`, `wardrobe`, `playlist`, `chat_oldstyle`, `follow_friend_mode`, `ignore_invites`, `allow_mimic`, `navigator_x`, `navigator_y`, `navigator_width`, `navigator_height`, `navigator_show_searches`, `ignore_events`, `disable_whisper`, `send_login_notif`, `mention_type`, `personalstaff`, `event_type`, `camera_follow`, `karma`, `prestige`, `personal_pin`, `nux`, `claimed_goal`, `royale_xp`, `bubble_id`, `room_tool_state`) VALUES
	(1, '1', NULL, NULL, NULL, '0', '0', '1', '1', 0, NULL, NULL, '0', 'EVERYBODY', '0', '1', 68, 42, 425, 592, '0', '0', '0', '0', 'ALL', '1', '1', '1', 0, 0, '2503', 6, '0', 0, 0, 0),
	/*!40000 ALTER TABLE `player_settings` ENABLE KEYS */;

-- Dumping structure for table hylib.player_spec
DROP TABLE IF EXISTS `player_spec`;
CREATE TABLE IF NOT EXISTS `player_spec` (
  `id` int(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `birthday_1` varchar(2) NOT NULL,
  `birthday_2` varchar(2) NOT NULL,
  `birthday_3` varchar(4) NOT NULL,
  `happyB` int(10) NOT NULL DEFAULT 0,
  `happyBY` int(250) NOT NULL DEFAULT 0,
  `activated1` enum('0','1') NOT NULL DEFAULT '0',
  `username` varchar(250) NOT NULL,
  `userpo_referidos` int(99) NOT NULL DEFAULT 0,
  `country` text DEFAULT NULL,
  `radio` varchar(100) NOT NULL DEFAULT 'autoplay',
  `publi` enum('0','1','2','3') NOT NULL DEFAULT '0',
  `guia` enum('0','1','2','3','4') NOT NULL DEFAULT '0',
  `tarea` varchar(50) NOT NULL DEFAULT '0',
  `bancobloq` int(99) NOT NULL,
  `croupier` enum('0','1') NOT NULL DEFAULT '0',
  `mastertrade` enum('0','1','2','3','4') NOT NULL DEFAULT '0',
  `cms_signature` text DEFAULT NULL,
  `facebook` text NOT NULL,
  `twitter` text NOT NULL,
  `instagram` text NOT NULL,
  `youtube` text NOT NULL,
  `redes` enum('0','1','2') NOT NULL DEFAULT '1',
  `diamantes` enum('0','1','2') NOT NULL DEFAULT '1',
  `duckets` enum('0','1','2') NOT NULL DEFAULT '1',
  `puntoshonor` enum('0','1','2') NOT NULL DEFAULT '1',
  `placasperfil` enum('0','1','2') NOT NULL DEFAULT '1',
  `amigos` enum('0','1','2') NOT NULL DEFAULT '1',
  `salas` enum('0','1','2') NOT NULL DEFAULT '1',
  `libro` enum('0','1','2') NOT NULL DEFAULT '1',
  `perfil` enum('0','1','2') NOT NULL DEFAULT '1',
  `color` varchar(500) NOT NULL DEFAULT '#e6c873',
  `colorcinta` varchar(500) NOT NULL DEFAULT '#806f40',
  `cde` enum('0','1','2','3','4','5','6','7','8') NOT NULL DEFAULT '0',
  `pre` enum('0','1','2','3') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_spec: ~0 rows (approximately)
DELETE FROM `player_spec`;
/*!40000 ALTER TABLE `player_spec` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_spec` ENABLE KEYS */;

-- Dumping structure for table hylib.player_stats
DROP TABLE IF EXISTS `player_stats`;
CREATE TABLE IF NOT EXISTS `player_stats` (
  `player_id` int(10) NOT NULL DEFAULT 0,
  `achievement_score` int(10) DEFAULT 0,
  `total_respect_points` int(11) DEFAULT 0,
  `daily_respects` int(3) DEFAULT 3,
  `daily_scratches` int(3) DEFAULT 3,
  `help_tickets` int(11) DEFAULT 0,
  `cautions` int(11) DEFAULT 0,
  `help_tickets_abusive` int(11) DEFAULT 0,
  `bans` int(11) DEFAULT 0,
  `trade_lock` int(11) DEFAULT 0,
  `level` int(10) NOT NULL DEFAULT 0,
  `experience_points` int(10) NOT NULL DEFAULT 0,
  `fireworks` int(10) NOT NULL DEFAULT 0,
  `daily_rolls` int(10) NOT NULL DEFAULT 0,
  PRIMARY KEY (`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.player_stats: ~33 rows (approximately)
DELETE FROM `player_stats`;
/*!40000 ALTER TABLE `player_stats` DISABLE KEYS */;
INSERT INTO `player_stats` (`player_id`, `achievement_score`, `total_respect_points`, `daily_respects`, `daily_scratches`, `help_tickets`, `cautions`, `help_tickets_abusive`, `bans`, `trade_lock`, `level`, `experience_points`, `fireworks`, `daily_rolls`) VALUES
	(1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1),
/*!40000 ALTER TABLE `player_stats` ENABLE KEYS */;

-- Dumping structure for table hylib.player_subscriptions
DROP TABLE IF EXISTS `player_subscriptions`;
CREATE TABLE IF NOT EXISTS `player_subscriptions` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT 'habbo_vip',
  `start` int(255) DEFAULT 0,
  `expire` int(255) DEFAULT 0,
  `user_id` int(11) DEFAULT 0,
  `presents` int(11) DEFAULT 1,
  `allowed_items` int(11) DEFAULT 50,
  `borrowed_items` int(11) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.player_subscriptions: ~97 rows (approximately)
DELETE FROM `player_subscriptions`;
/*!40000 ALTER TABLE `player_subscriptions` DISABLE KEYS */;
INSERT INTO `player_subscriptions` (`id`, `type`, `start`, `expire`, `user_id`, `presents`, `allowed_items`, `borrowed_items`) VALUES
	(1, 'habbo_vip', 0, 1893549600, 1, 1, 1, 0),
	(
/*!40000 ALTER TABLE `player_subscriptions` ENABLE KEYS */;

-- Dumping structure for table hylib.player_tags
DROP TABLE IF EXISTS `player_tags`;
CREATE TABLE IF NOT EXISTS `player_tags` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `tag` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `player_id_badge_code` (`player_id`,`tag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.player_tags: ~0 rows (approximately)
DELETE FROM `player_tags`;
/*!40000 ALTER TABLE `player_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_tags` ENABLE KEYS */;

-- Dumping structure for table hylib.polls
DROP TABLE IF EXISTS `polls`;
CREATE TABLE IF NOT EXISTS `polls` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT 'Untitled Poll',
  `thanks_message` varchar(100) NOT NULL DEFAULT 'Thanks!',
  `reward_badge` varchar(100) NOT NULL DEFAULT 'US8',
  `reward_credits` int(11) NOT NULL DEFAULT 0,
  `reward_vip_points` int(11) NOT NULL DEFAULT 0,
  `reward_activity_points` int(11) NOT NULL DEFAULT 0,
  `reward_achievement_points` int(11) NOT NULL DEFAULT 0,
  `room_id` int(11) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `room_id` (`room_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.polls: ~0 rows (approximately)
DELETE FROM `polls`;
/*!40000 ALTER TABLE `polls` DISABLE KEYS */;
/*!40000 ALTER TABLE `polls` ENABLE KEYS */;

-- Dumping structure for table hylib.polls_answers
DROP TABLE IF EXISTS `polls_answers`;
CREATE TABLE IF NOT EXISTS `polls_answers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poll_id` int(11) DEFAULT 0,
  `question_id` int(11) DEFAULT 0,
  `player_id` int(11) DEFAULT 0,
  `answer` varchar(1000) DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `poll_id_question_id_player_id` (`poll_id`,`question_id`,`player_id`) USING BTREE,
  KEY `player_id` (`player_id`) USING BTREE,
  KEY `poll_id` (`poll_id`) USING BTREE,
  KEY `poll_id_question_id` (`poll_id`,`question_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.polls_answers: ~0 rows (approximately)
DELETE FROM `polls_answers`;
/*!40000 ALTER TABLE `polls_answers` DISABLE KEYS */;
/*!40000 ALTER TABLE `polls_answers` ENABLE KEYS */;

-- Dumping structure for table hylib.polls_questions
DROP TABLE IF EXISTS `polls_questions`;
CREATE TABLE IF NOT EXISTS `polls_questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poll_id` int(11) NOT NULL,
  `question_type` enum('WORDED','MULTIPLE_CHOICE','SINGLE_CHOICE') NOT NULL DEFAULT 'WORDED',
  `question` varchar(100) NOT NULL DEFAULT 'What do you think of Comet Server?',
  `options` text DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `poll_id` (`poll_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.polls_questions: ~0 rows (approximately)
DELETE FROM `polls_questions`;
/*!40000 ALTER TABLE `polls_questions` DISABLE KEYS */;
/*!40000 ALTER TABLE `polls_questions` ENABLE KEYS */;

-- Dumping structure for table hylib.profile_wall
DROP TABLE IF EXISTS `profile_wall`;
CREATE TABLE IF NOT EXISTS `profile_wall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` text NOT NULL,
  `titel` tinytext NOT NULL,
  `message` text NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.profile_wall: 0 rows
DELETE FROM `profile_wall`;
/*!40000 ALTER TABLE `profile_wall` DISABLE KEYS */;
/*!40000 ALTER TABLE `profile_wall` ENABLE KEYS */;

-- Dumping structure for table hylib.quests
DROP TABLE IF EXISTS `quests`;
CREATE TABLE IF NOT EXISTS `quests` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(32) NOT NULL DEFAULT '',
  `series_number` int(11) NOT NULL DEFAULT 0,
  `goal_type` int(10) NOT NULL DEFAULT 0,
  `goal_data` int(10) unsigned NOT NULL DEFAULT 0,
  `name` varchar(32) NOT NULL DEFAULT '',
  `reward` varchar(50) NOT NULL DEFAULT '10',
  `badge_id` varchar(50) NOT NULL DEFAULT '',
  `reward_type` enum('ACHIEVEMENT_POINTS','VIP_POINTS','ACTIVITY_POINTS','CREDITS','SEASONAL_POINTS','GO_TO_ROOM','ITEM','CANDY_CHEST','BADGE','WEEN_ENDING') NOT NULL DEFAULT 'ACTIVITY_POINTS',
  `data_bit` varchar(2) NOT NULL DEFAULT '',
  `enabled` enum('0','1') DEFAULT '1',
  `timestamp` int(10) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `nameUnique` (`name`) USING BTREE,
  KEY `nameKey` (`name`) USING BTREE,
  KEY `categoryKey` (`category`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.quests: ~41 rows (approximately)
DELETE FROM `quests`;
/*!40000 ALTER TABLE `quests` DISABLE KEYS */;
INSERT INTO `quests` (`id`, `category`, `series_number`, `goal_type`, `goal_data`, `name`, `reward`, `badge_id`, `reward_type`, `data_bit`, `enabled`, `timestamp`) VALUES
	(1, 'ween', 1, 17, 3976, 'FINDPUMPKIN', '1', '', 'ACHIEVEMENT_POINTS', '_2', '0', 0),
	(2, 'social', 1, 8, 1, 'ENTEROTHERSROOM', '0', '', 'ACTIVITY_POINTS', '_2', '1', 0),
	(3, 'identity', 1, 14, 1, 'CHANGEFIGURE', '0', '', 'ACTIVITY_POINTS', '_2', '1', 0),
	(4, 'explore', 1, 17, 3247, 'FINDLIFEGUARDTOWER', '50', '', 'SEASONAL_POINTS', '_2', '1', 0),
	(5, 'room_builder', 2, 1, 3, 'ROTATEITEM', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(6, 'room_builder', 3, 2, 1, 'PLACEITEM', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(7, 'room_builder', 4, 3, 1, 'PICKUPITEM', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(8, 'room_builder', 5, 4, 2, 'SWITCHSTATE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(9, 'room_builder', 6, 5, 1, 'STACKITEM', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(10, 'room_builder', 7, 6, 1, 'PLACEFLOOR', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(11, 'room_builder', 8, 7, 1, 'PLACEWALLPAPER', '0', '', 'ACTIVITY_POINTS', '_1', '1', 0),
	(12, 'identity', 2, 15, 1, 'CHANGEMOTTO', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(13, 'identity', 3, 16, 1, 'WEARBADGE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(14, 'social', 2, 9, 1, 'CHATWITHSOMEONE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(15, 'social', 3, 10, 1, 'REQUESTFRIEND', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(16, 'social', 4, 11, 1, 'GIVERESPECT', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(17, 'social', 5, 12, 1, 'DANCE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(18, 'social', 6, 13, 1, 'WAVE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(19, 'explore', 2, 17, 3530, 'SWIM', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(20, 'explore', 3, 17, 3562, 'FINDSURFBOARD', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(21, 'explore', 4, 17, 3549, 'FINDBEETLE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(22, 'explore', 5, 17, 2958, 'FINDNEONFLOOR', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(23, 'explore', 6, 17, 2964, 'FINDDISCOBALL', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(24, 'explore', 7, 17, 2608, 'FINDJUKEBOX', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(25, 'explore', 8, 17, 3643, 'FINDBBGATE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(26, 'explore', 9, 17, 3633, 'FINDBBTILE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(27, 'explore', 10, 17, 3642, 'FINDBBTELEPORT', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(28, 'explore', 11, 17, 3760, 'FINDFREEZEGATE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(29, 'explore', 12, 17, 3765, 'FINDFREEZESCOREBOARD', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(30, 'explore', 13, 17, 3759, 'FINDFREEZEEXITTILE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(31, 'explore', 14, 17, 1413, 'ICESKATE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(32, 'explore', 15, 17, 3741, 'FINDTAGPOLE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(33, 'explore', 16, 17, 2199, 'ROLLERSKATE', '0', '', 'ACTIVITY_POINTS', '', '1', 0),
	(34, 'room_builder', 1, 0, 3, 'MOVEITEM', '0', '', 'ACHIEVEMENT_POINTS', '_2', '1', 0),
	(35, 'ween', 2, 18, 5, 'BITEPLAYER', '1', '', 'ACHIEVEMENT_POINTS', '_2', '0', 0),
	(36, 'ween', 3, 19, 1, 'THROWCANDIES', '1', '', 'ACHIEVEMENT_POINTS', '', '0', 0),
	(37, 'ween', 4, 20, 1, 'THREATPLAYERS', '1', '', 'ACHIEVEMENT_POINTS', '', '0', 0),
	(38, 'ween', 5, 21, 1, 'PUKEPLAYERS', '1', '', 'ACHIEVEMENT_POINTS', '', '0', 0),
	(39, 'ween', 6, 22, 1, 'BURNPLAYERS', '1', '', 'ACHIEVEMENT_POINTS', '', '0', 0),
	(40, 'ween', 7, 23, 1, 'CHECKAKINATOR', '1', '', 'ACHIEVEMENT_POINTS', '', '0', 0),
	(41, 'ween', 8, 25, 1, 'FINALENDING', '3197', '', 'ITEM', '', '0', 0);
/*!40000 ALTER TABLE `quests` ENABLE KEYS */;

-- Dumping structure for table hylib.ranks
DROP TABLE IF EXISTS `ranks`;
CREATE TABLE IF NOT EXISTS `ranks` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET latin1 NOT NULL,
  `badgeid` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `colour` varchar(50) CHARACTER SET latin1 DEFAULT 'blue',
  `staff_page` enum('1','0') CHARACTER SET latin1 DEFAULT '0',
  `description` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.ranks: ~19 rows (approximately)
DELETE FROM `ranks`;
/*!40000 ALTER TABLE `ranks` DISABLE KEYS */;
INSERT INTO `ranks` (`id`, `name`, `badgeid`, `colour`, `staff_page`, `description`) VALUES
	(1, 'Usuario', NULL, 'blue', NULL, NULL),
	(2, 'VIP', NULL, 'blue', '0', NULL),
	(3, 'DJ', '', 'blue', '0', NULL),
	(4, 'MaW', '', 'blue', '0', ''),
	(5, 'Croupier', '', 'blue', '0', NULL),
	(7, 'Master Trader', '', 'blue', '0', NULL),
	(8, 'Guía', '', 'blue', '0', NULL),
	(9, 'Publicista', '', 'blue', '0', NULL),
	(10, 'Encargado de Publicidad', '', 'blue', '0', NULL),
	(11, 'Embajador', '', 'blue', '0', NULL),
	(12, 'Colaborador', '', 'blue', '0', NULL),
	(13, 'Moderador', '', 'blue', '0', NULL),
	(14, 'Game Master', '', 'blue', '0', NULL),
	(15, 'Co-Administrador', '', 'blue', '0', NULL),
	(16, 'Administrador', '', 'blue', '0', NULL),
	(17, 'CEO', NULL, 'blue', '0', NULL),
	(18, 'Fundador', '', 'blue', '0', NULL),
	(19, 'Desarrollador', '', 'blue', '0', NULL),
	(20, 'Oculto', '', 'blue', '0', NULL);
/*!40000 ALTER TABLE `ranks` ENABLE KEYS */;

-- Dumping structure for table hylib.referrer
DROP TABLE IF EXISTS `referrer`;
CREATE TABLE IF NOT EXISTS `referrer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` decimal(10,0) DEFAULT NULL,
  `refid` decimal(10,0) DEFAULT NULL,
  `diamonds` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table hylib.referrer: ~0 rows (approximately)
DELETE FROM `referrer`;
/*!40000 ALTER TABLE `referrer` DISABLE KEYS */;
/*!40000 ALTER TABLE `referrer` ENABLE KEYS */;

-- Dumping structure for table hylib.referrerbank
DROP TABLE IF EXISTS `referrerbank`;
CREATE TABLE IF NOT EXISTS `referrerbank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `diamonds` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table hylib.referrerbank: ~0 rows (approximately)
DELETE FROM `referrerbank`;
/*!40000 ALTER TABLE `referrerbank` DISABLE KEYS */;
/*!40000 ALTER TABLE `referrerbank` ENABLE KEYS */;

-- Dumping structure for table hylib.refers
DROP TABLE IF EXISTS `refers`;
CREATE TABLE IF NOT EXISTS `refers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registered_id` int(250) NOT NULL,
  `refer_id` int(250) NOT NULL,
  `timestamp` int(250) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.refers: ~0 rows (approximately)
DELETE FROM `refers`;
/*!40000 ALTER TABLE `refers` DISABLE KEYS */;
/*!40000 ALTER TABLE `refers` ENABLE KEYS */;

-- Dumping structure for table hylib.resetpassword
DROP TABLE IF EXISTS `resetpassword`;
CREATE TABLE IF NOT EXISTS `resetpassword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `resetkey` varchar(255) DEFAULT NULL,
  `enable` enum('0','1') DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table hylib.resetpassword: ~0 rows (approximately)
DELETE FROM `resetpassword`;
/*!40000 ALTER TABLE `resetpassword` DISABLE KEYS */;
/*!40000 ALTER TABLE `resetpassword` ENABLE KEYS */;

-- Dumping structure for table hylib.rooms
DROP TABLE IF EXISTS `rooms`;
CREATE TABLE IF NOT EXISTS `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('PUBLIC','PRIVATE') DEFAULT 'PRIVATE',
  `owner_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL DEFAULT 0,
  `owner` varchar(50) NOT NULL DEFAULT 'John',
  `name` varchar(64) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `description` varchar(255) NOT NULL DEFAULT '',
  `tags` varchar(65) NOT NULL DEFAULT '',
  `access_type` enum('password','doorbell','open','invisible') NOT NULL DEFAULT 'open',
  `password` varchar(64) NOT NULL DEFAULT '',
  `category` int(10) NOT NULL DEFAULT 15,
  `max_users` int(11) NOT NULL DEFAULT 25,
  `score` int(11) NOT NULL DEFAULT 0,
  `model` varchar(64) NOT NULL DEFAULT 'model_a',
  `allow_pets` enum('0','1') NOT NULL DEFAULT '1',
  `allow_walkthrough` enum('0','1') NOT NULL DEFAULT '1',
  `hide_walls` enum('0','1') NOT NULL DEFAULT '0',
  `thickness_wall` int(11) NOT NULL DEFAULT 1,
  `thickness_floor` int(11) NOT NULL DEFAULT 1,
  `decorations` varchar(128) NOT NULL DEFAULT 'landscape=0.0',
  `heightmap` text DEFAULT NULL,
  `trade_state` enum('OWNER_ONLY','ENABLED','DISABLED') DEFAULT 'ENABLED',
  `mute_state` enum('NONE','RIGHTS') NOT NULL DEFAULT 'NONE',
  `kick_state` enum('NONE','RIGHTS','EVERYONE') NOT NULL DEFAULT 'RIGHTS',
  `ban_state` enum('NONE','RIGHTS') NOT NULL DEFAULT 'NONE',
  `bubble_mode` tinyint(3) NOT NULL DEFAULT 0,
  `bubble_type` tinyint(3) DEFAULT 0,
  `bubble_scroll` tinyint(3) NOT NULL DEFAULT 0,
  `chat_distance` tinyint(3) NOT NULL DEFAULT 0,
  `flood_level` tinyint(3) NOT NULL DEFAULT 0,
  `disabled_commands` varchar(255) DEFAULT '',
  `required_badge` varchar(50) DEFAULT NULL,
  `thumbnail` varchar(128) NOT NULL DEFAULT 'navigator-thumbnail/default.png',
  `users_now` int(11) DEFAULT 0,
  `hide_wired` enum('0','1') DEFAULT '0',
  `allow_recount` enum('0','1') NOT NULL DEFAULT '0',
  `roller_speed` int(11) DEFAULT 4,
  `has_sorting` enum('0','1') NOT NULL DEFAULT '0',
  `advanced_collision` enum('0','1') NOT NULL DEFAULT '0',
  `user_idle_ticks` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `owner_id` (`id`,`owner_id`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `tags` (`tags`) USING BTREE,
  KEY `score` (`score`) USING BTREE,
  KEY `category` (`category`) USING BTREE,
  KEY `type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.rooms: ~24 rows (approximately)
DELETE FROM `rooms`;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` (`id`, `type`, `owner_id`, `group_id`, `owner`, `name`, `description`, `tags`, `access_type`, `password`, `category`, `max_users`, `score`, `model`, `allow_pets`, `allow_walkthrough`, `hide_walls`, `thickness_wall`, `thickness_floor`, `decorations`, `heightmap`, `trade_state`, `mute_state`, `kick_state`, `ban_state`, `bubble_mode`, `bubble_type`, `bubble_scroll`, `chat_distance`, `flood_level`, `disabled_commands`, `required_badge`, `thumbnail`, `users_now`, `hide_wired`, `allow_recount`, `roller_speed`, `has_sorting`, `advanced_collision`, `user_idle_ticks`) VALUES
	(1, 'PRIVATE', 10, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(4, 'PRIVATE', 7, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'OWNER_ONLY', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(5, 'PRIVATE', 13, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 1, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0,', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(6, 'PRIVATE', 13, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 1, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0,', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(7, 'PRIVATE', 14, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0,', '{"doorX":0,"doorY":1,"doorRotation":2,"wallHeight":-1,"modelData":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxx0xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxx0xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}', 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(8, 'PRIVATE', 11, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0,', '{"doorX":0,"doorY":1,"doorRotation":2,"wallHeight":-1,"modelData":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\r00000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000"}', 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(9, 'PRIVATE', 15, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(10, 'PRIVATE', 16, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(11, 'PRIVATE', 17, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(12, 'PRIVATE', 18, 0, 'Public Rooms', 'Public Rooms .', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(13, 'PRIVATE', 19, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 11, 70, 0, 'model_d', '0', '0', '0', 0, 0, 'landscape=0.0,', '{"doorX":5,"doorY":19,"doorRotation":2,"wallHeight":-1,"modelData":"xxxxxxxxxxxxxxx\\rxxx000000000000\\rxxx000000000000\\rxxx000000000000\\r0xx000000000000\\r0xx000000000000\\r0xx000000000000\\r0xx000000000000\\r0xx000000000000\\r0xxxx0000000000\\r0xxxx00000000xx\\r0xxxxxxx0000xxx\\r0xxxxxx00000xxx\\r0xxxxxx00000xxx\\r0xxxxxxxxxxxxxx\\r0xxxxxxxxxxxxxx\\r0xxxxxx00xxxxxx\\r0xxxxxxxxxxxxxx\\r0xxxxxxxxxxxxxx\\rxxxxx0xxxxxxxxx"}', 'OWNER_ONLY', 'NONE', 'EVERYONE', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(14, 'PRIVATE', 21, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(15, 'PRIVATE', 22, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(17, 'PRIVATE', 27, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 2, 'model_c', '1', '1', '1', 0, -2, 'landscape=0.0,', '{"doorX":4,"doorY":7,"doorRotation":2,"wallHeight":-1,"modelData":"xxxxxxxxxxxx\\rxxxxxxxxxxxx\\rxxxxxxxxxxxx\\rxxxxxxxxxxxx\\rxxxxxxxxxx00\\rxxxxx0000000\\rxxxxx000000x\\rxxxx0000000x\\rxxxxx000000x\\rxxxxx000000x\\rxxxxx0000000\\rxxxxxxxxxx00"}', 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(18, 'PRIVATE', 30, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 11, 50, 0, 'model_5', '0', '0', '0', -2, -2, 'landscape=0.0,', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(19, 'PRIVATE', 12, 0, 'Public Rooms', 'Public Rooms', 'Public Rooms', '', 'open', '', 10, 70, 0, 'model_5', '0', '0', '1', 0, -2, 'landscape=0.0,', '{"doorX":8,"doorY":17,"doorRotation":2,"wallHeight":-1,"modelData":"xxxxxxxxxxxxxxxxxxxx\\r0xxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxx0xx000\\rxxxxxxxxx0xx000xx000\\rxxxxxxxx00xx000xx000\\rxxxxxxxx00xxxxxxx000\\rxxxxxxxx000xxxxxx000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx000000000000\\rxxxxxxxx00000000000x\\rxxxxxxxx00000000000x\\rxxxxxxxx0000000000xx\\rxxxxxxxx00000000xxxx\\rxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxx0xxx"}', 'OWNER_ONLY', 'RIGHTS', 'EVERYONE', 'RIGHTS', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(20, 'PRIVATE', 35, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_5', '1', '0', '0', 0, 0, 'wallpaper=2804,floor=205,landscape=0.0,', '{"doorX":13,"doorY":18,"doorRotation":2,"wallHeight":2,"modelData":"xxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxx0000000000xxx\\rxxxxxxx00000000000000xxx\\rxxxxxxx00000000000000xxx\\rxxxxxxx0000000000000000x\\rxxxxxxx0000000000000000x\\rxxxxxx00000000000000000x\\rxxxxxx00000000000000000x\\rxxxxxx000000000000000000\\rxxxxxx00000000000000000x\\rxxxxxx00000000000000000x\\rxxxxxx000000000000000000\\rxxxxxx00000000000000000x\\rxxxxxx00000000000000000x\\rxxxxxx00000000000000xxxx\\rxxxxxx0000000000000xxxxx\\rxxxxxxxxx0000000000xxxxx\\rxxxxxxxxx0000000000xxxxx\\rxxxxxxxxxxxxx0xx0xxxxxxx"}', 'OWNER_ONLY', 'NONE', 'EVERYONE', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(21, 'PRIVATE', 36, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '1', 0, 0, 'wallpaper=1007,floor=403,landscape=0.0,', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(22, 'PRIVATE', 11, 0, 'Public Rooms', 'Public Rooms', '', '', 'doorbell', '', 0, 20, 0, 'model_a', '1', '1', '0', 0, 0, 'wallpaper=2804,floor=205,landscape=0.0,', '{"doorX":13,"doorY":18,"doorRotation":2,"wallHeight":2,"modelData":"xxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxxxxxxxxxxxxxxx\\rxxxxxxxxxxx0000000000xxx\\rxxxxxxx00000000000000xxx\\rxxxxxxx00000000000000xxx\\rxxxxxxx0000000000000000x\\rxxxxxxx0000000000000000x\\rxxxxxx00000000000000000x\\rxxxxxx00000000000000000x\\rxxxxxx000000000000000000\\rxxxxxx00000000000000000x\\rxxxxxx00000000000000000x\\rxxxxxx000000000000000000\\rxxxxxx00000000000000000x\\rxxxxxx00000000000000000x\\rxxxxxx00000000000000xxxx\\rxxxxxx0000000000000xxxxx\\rxxxxxxxxx0000000000xxxxx\\rxxxxxxxxx0000000000xxxxx\\rxxxxxxxxxxxxx0xx0xxxxxxx\\r"}', 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(23, 'PRIVATE', 20, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(24, 'PRIVATE', 37, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(26, 'PRIVATE', 11, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'wallpaper=2604,floor=205,landscape=0.0,', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(27, 'PRIVATE', 34, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_f', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(28, 'PRIVATE', 41, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 50, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0,', '{"doorX":0,"doorY":1,"doorRotation":2,"wallHeight":-1,"modelData":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\r00000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000\\rx0000000000000000000000000000000000000000000000000000000000000000"}', 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(29, 'PRIVATE', 12, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0,', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, NULL, NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(30, 'PRIVATE', 44, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_b', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(31, 'PRIVATE', 12, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_a', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL),
	(32, 'PRIVATE', 12, 0, 'Public Rooms', 'Public Rooms', '', '', 'open', '', 10, 10, 0, 'model_b', '1', '1', '0', 0, 0, 'landscape=0.0', NULL, 'DISABLED', 'NONE', 'RIGHTS', 'NONE', 0, 0, 0, 0, 0, '', NULL, 'navigator-thumbnail/default.png', 0, '0', '0', 4, '0', '0', NULL);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;

-- Dumping structure for table hylib.rooms_catalog
DROP TABLE IF EXISTS `rooms_catalog`;
CREATE TABLE IF NOT EXISTS `rooms_catalog` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `room_id` int(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  `image` varchar(250) NOT NULL,
  `owner` varchar(250) NOT NULL,
  `type` int(1) NOT NULL,
  `price` int(100) NOT NULL,
  `sell` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table hylib.rooms_catalog: ~0 rows (approximately)
DELETE FROM `rooms_catalog`;
/*!40000 ALTER TABLE `rooms_catalog` DISABLE KEYS */;
/*!40000 ALTER TABLE `rooms_catalog` ENABLE KEYS */;

-- Dumping structure for table hylib.rooms_promoted
DROP TABLE IF EXISTS `rooms_promoted`;
CREATE TABLE IF NOT EXISTS `rooms_promoted` (
  `room_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `time_start` int(11) DEFAULT NULL,
  `time_expire` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_id`) USING BTREE,
  KEY `expire` (`time_expire`) USING BTREE,
  KEY `room_id` (`room_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.rooms_promoted: ~0 rows (approximately)
DELETE FROM `rooms_promoted`;
/*!40000 ALTER TABLE `rooms_promoted` DISABLE KEYS */;
/*!40000 ALTER TABLE `rooms_promoted` ENABLE KEYS */;

-- Dumping structure for table hylib.room_bans
DROP TABLE IF EXISTS `room_bans`;
CREATE TABLE IF NOT EXISTS `room_bans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL DEFAULT 0,
  `player_id` int(11) NOT NULL DEFAULT 0,
  `expire_timestamp` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='latin1_swedish_ci';

-- Dumping data for table hylib.room_bans: ~0 rows (approximately)
DELETE FROM `room_bans`;
/*!40000 ALTER TABLE `room_bans` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_bans` ENABLE KEYS */;

-- Dumping structure for table hylib.room_bundles
DROP TABLE IF EXISTS `room_bundles`;
CREATE TABLE IF NOT EXISTS `room_bundles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enabled` enum('1','0') NOT NULL DEFAULT '1',
  `alias` varchar(255) DEFAULT 'roombundle',
  `room_id` int(11) DEFAULT NULL,
  `model_data` text DEFAULT NULL,
  `bundle_data` text DEFAULT NULL,
  `cost_credits` int(11) DEFAULT 20,
  `cost_seasonal` int(11) DEFAULT 0,
  `cost_vip` int(11) DEFAULT 0,
  `cost_activity_points` int(11) DEFAULT 0,
  `room_config` text DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `alias` (`alias`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.room_bundles: ~0 rows (approximately)
DELETE FROM `room_bundles`;
/*!40000 ALTER TABLE `room_bundles` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_bundles` ENABLE KEYS */;

-- Dumping structure for table hylib.room_models
DROP TABLE IF EXISTS `room_models`;
CREATE TABLE IF NOT EXISTS `room_models` (
  `id` varchar(100) NOT NULL,
  `door_x` int(11) NOT NULL,
  `door_y` int(11) NOT NULL,
  `door_dir` int(4) NOT NULL DEFAULT 2,
  `heightmap` text NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.room_models: ~55 rows (approximately)
DELETE FROM `room_models`;
/*!40000 ALTER TABLE `room_models` DISABLE KEYS */;
INSERT INTO `room_models` (`id`, `door_x`, `door_y`, `door_dir`, `heightmap`) VALUES
	('cinema_a', 20, 27, 0, 'xxxxxxx1xx11111111xxxxxx\r\nxxx1111111111111111xxxxx\r\nxxx111xxxx1111111111xxxx\r\nxxxx2xxxxxxxxxxxxxxxxxxx\r\nxx3x3x333311xxxxxxxxxx11\r\nxx3333333311111111111111\r\nxx3333333311111111111111\r\nxx3333333311111111122111\r\nxx3333333311x22222222111\r\nxx3333333311x22222222111\r\nxx3333333311xxxxxxxxx111\r\nxx3333333311111111111111\r\nxx3333333311111111111111\r\nxx3333333311111111111111\r\nxx3333333311111xxxx11111\r\nxx3333333311111xxxx11111\r\nxx3333333311111xxxx11111\r\nxx3333333311111xxxx11111\r\nxx3333333311111xxxx11111\r\nxx3333333311111xxxx11111\r\nxx3333333311111xxxx11111\r\n333333332111111xxxx11111\r\n333333332111111111111111\r\n333333332111111111111111\r\n333333332111111111111111\r\nxx3333332111111111111111\r\nxxxxxxxxxxxxxxxxxxx11111\r\nxxxxxxxxxxxxxxxxxxx11111\r\nxxxxxxxxxxxxxxxxxxx11111'),
	('custom_model', 0, 0, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxx0000000000000xxxx\r\nxxxxxxxxxxx000000000000000xxx\r\nxxxxxxxxxx00000000000000000xx\r\nxxxxxxxxxx00000000000000000xx\r\nxxxxxxxxxx00000000000000000xx\r\nxxxxxxxxxx11111111111111111xx\r\nxxxxxxxxxx22222222222222222xx\r\nxxxxxxxxxx22222222222222222xx\r\nxxxxxxxxxx22222222222222222xx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('dusty_lounge', 14, 1, 4, 'xxxxxxxxxxxxxx22xxxxxxxxxxxxx\r\nxxxxxxxxxx222x222x2xxxxxxxxxx\r\nxxxxxxx33322222222223xxxxxxx3\r\nxxxxxxx33322222222223xxxxxxx3\r\nxxxxxxx33322222222223x33333x3\r\nxxxxxxx33322222222223x33333x3\r\nxx111xx33322222222223xxxxxxx3\r\nxx111xxx332222222222333333333\r\nxx111xxxx32222222222333333333\r\nxx111xxxxxx222222222333333333\r\nxx111xxxxxxx1111111x333333333\r\nxx111xxxxxxx1111111x222222222\r\nxx111xxxxxx111111111111111111\r\nxx111xxxxxx111111111111111111\r\n11111xxxxxx111111111111111111\r\n11111xxxxxx111111111111111111\r\n11x11xxxxxx111111111111111111\r\n11xxxxxxxxx11111111111111111x\r\nx11xxxxxxxxx1111111x1111111xx\r\nxx11xxxxxxx111111111111111xxx\r\nxxx11xxxxxx11111111111111xxxx\r\nxxxx11111111111111111111xxxxx\r\nxxxxx11111111111111xxxxxxxxxx\r\nxxxxxxxxxxx11111111xxxxxxxxxx\r\nxxxxxxxxxxx11111111xxxxxxxxxx'),
	('model_0', 0, 4, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx00000000xx00000000xx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx00000000xx00000000xx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx00000000xx00000000xx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx0000\r\n000000000xx00000000xx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx0000\r\nx00000000xx00000000xx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx0000\r\nx00000000xx00000000xx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx0000\r\nx00000000xx00000000xx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx00000000xx00000000xx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_2', 0, 15, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxjjjjjjjjjjjjjx0000xxxxxxxxxx\r\nxjjjjjjjjjjjjjx0000xxxxxxxxxx\r\nxjjjjjjjjjjjjjx0000xxxxxxxxxx\r\nxjjjjjjjjjjjjjx0000xxxxxxxxxx\r\nxjjjjjjjjjjjjjx0000xxxxxxxxxx\r\nxjjjjjjjjjjjjjx0000xxxxxxxxxx\r\nxjjjjjjjjjjjjjx0000xxxxxxxxxx\r\nxjjjjjjjjjjjjjx0000xxxxxxxxxx\r\nxxxxxxxxxxxxiix0000xxxxxxxxxx\r\nxxxxxxxxxxxxhhx0000xxxxxxxxxx\r\nxxxxxxxxxxxxggx0000xxxxxxxxxx\r\nxxxxxxxxxxxxffx0000xxxxxxxxxx\r\nxxxxxxxxxxxxeex0000xxxxxxxxxx\r\nxeeeeeeeeeeeeex0000xxxxxxxxxx\r\neeeeeeeeeeeeeex0000xxxxxxxxxx\r\nxeeeeeeeeeeeeex0000xxxxxxxxxx\r\nxeeeeeeeeeeeeex0000xxxxxxxxxx\r\nxeeeeeeeeeeeeex0000xxxxxxxxxx\r\nxeeeeeeeeeeeeex0000xxxxxxxxxx\r\nxeeeeeeeeeeeeex0000xxxxxxxxxx\r\nxeeeeeeeeeeeeex0000xxxxxxxxxx\r\nxeeeeeeeeeeeeex0000xxxxxxxxxx\r\nxeeeeeeeeeeeeex0000xxxxxxxxxx\r\nxxxxxxxxxxxxddx00000000000000\r\nxxxxxxxxxxxxccx00000000000000\r\nxxxxxxxxxxxxbbx00000000000000\r\nxxxxxxxxxxxxaax00000000000000\r\nxaaaaaaaaaaaaax00000000000000\r\nxaaaaaaaaaaaaax00000000000000\r\nxaaaaaaaaaaaaax00000000000000\r\nxaaaaaaaaaaaaax00000000000000\r\nxaaaaaaaaaaaaax00000000000000\r\nxaaaaaaaaaaaaax00000000000000\r\nxaaaaaaaaaaaaax00000000000000\r\nxaaaaaaaaaaaaax00000000000000\r\nxaaaaaaaaaaaaax00000000000000\r\nxaaaaaaaaaaaaax00000000000000\r\nxxxxxxxxxxxx99x0000xxxxxxxxxx\r\nxxxxxxxxxxxx88x0000xxxxxxxxxx\r\nxxxxxxxxxxxx77x0000xxxxxxxxxx\r\nxxxxxxxxxxxx66x0000xxxxxxxxxx\r\nxxxxxxxxxxxx55x0000xxxxxxxxxx\r\nxxxxxxxxxxxx44x0000xxxxxxxxxx\r\nx4444444444444x0000xxxxxxxxxx\r\nx4444444444444x0000xxxxxxxxxx\r\nx4444444444444x0000xxxxxxxxxx\r\nx4444444444444x0000xxxxxxxxxx\r\nx4444444444444x0000xxxxxxxxxx\r\nx4444444444444x0000xxxxxxxxxx\r\nx4444444444444x0000xxxxxxxxxx\r\nx4444444444444x0000xxxxxxxxxx\r\nx4444444444444x0000xxxxxxxxxx\r\nx4444444444444x0000xxxxxxxxxx\r\nxxxxxxxxxxxx33x0000xxxxxxxxxx\r\nxxxxxxxxxxxx22x0000xxxxxxxxxx\r\nxxxxxxxxxxxx11x0000xxxxxxxxxx\r\nxxxxxxxxxxxx00x0000xxxxxxxxxx\r\nx000000000000000000xxxxxxxxxx\r\nx000000000000000000xxxxxxxxxx\r\nx000000000000000000xxxxxxxxxx\r\nx000000000000000000xxxxxxxxxx\r\nx000000000000000000xxxxxxxxxx\r\nx000000000000000000xxxxxxxxxx\r\nx000000000000000000xxxxxxxxxx\r\nx000000000000000000xxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_3', 0, 10, 2, 'xxxxxxxxxxxxxxxxx\r\nxxx0000000000000x\r\nxxx0000000000000x\r\nxxx0000000000000x\r\nxxx0000000000000x\r\nxxx0000000000000x\r\nxxx0000000000000x\r\nx000000000000000x\r\nx000000000000000x\r\nx000000000000000x\r\n0000000000000000x\r\nx000000000000000x\r\nx000000000000000x\r\nx000000000000000x\r\nxxxxxxxxxxxxxxxxx'),
	('model_4', 0, 10, 2, 'xxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxaaaaaaaaaaax\r\nxxxxxxxxxaaaaaaaaaaax\r\nxxxxxxxxxaaaaaaaaaaax\r\nxxxxxxxxxaaaaaaaaaaax\r\nx00000000xxxxxaaaaaax\r\nx00000000xxxxxaaaaaax\r\nx00000000xxxxxaaaaaax\r\nx00000000xxxxxaaaaaax\r\nx0000000000000aaaaaax\r\n00000000000000aaaaaax\r\nx0000000000000aaaaaax\r\nx0000000000000aaaaaax\r\nx0000000000000xxxxxxx\r\nx0000000000000xxxxxxx\r\nx0000000000000xxxxxxx\r\nx0000000000000xxxxxxx\r\nx0000000000000xxxxxxx\r\nx0000000000000xxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx'),
	('model_5', 0, 10, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\n000000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nx00000000000000000000000000000000x\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_6', 0, 15, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx222222222x000000000000000000000000xxxx\r\nx222222222x000000000000000000000000xxxx\r\nx222222222x000000000000000000000000xxxx\r\nx222222222x000000000000000000000000xxxx\r\nx222222222x000000000000000000000000xxxx\r\nx222222222x000000000000000000000000xxxx\r\nx222222222x000000000000000000000000xxxx\r\nx222222222x000000000000000000000000xxxx\r\nx222222222x00000000xxxxxxxx00000000xxxx\r\nx11xxxxxxxx00000000xxxxxxxx00000000xxxx\r\nx00x000000000000000xxxxxxxx00000000xxxx\r\nx00x000000000000000xxxxxxxx00000000xxxx\r\nx000000000000000000xxxxxxxx00000000xxxx\r\nx000000000000000000xxxxxxxx00000000xxxx\r\n0000000000000000000xxxxxxxx00000000xxxx\r\nx000000000000000000xxxxxxxx00000000xxxx\r\nx00x000000000000000xxxxxxxx00000000xxxx\r\nx00x000000000000000xxxxxxxx00000000xxxx\r\nx00xxxxxxxxxxxxxxxxxxxxxxxx00000000xxxx\r\nx00xxxxxxxxxxxxxxxxxxxxxxxx00000000xxxx\r\nx00x0000000000000000000000000000000xxxx\r\nx00x0000000000000000000000000000000xxxx\r\nx0000000000000000000000000000000000xxxx\r\nx0000000000000000000000000000000000xxxx\r\nx0000000000000000000000000000000000xxxx\r\nx0000000000000000000000000000000000xxxx\r\nx00x0000000000000000000000000000000xxxx\r\nx00x0000000000000000000000000000000xxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_7', 0, 17, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxx\r\nx222222xx00000000xxxxxxxx\r\nx222222xx00000000xxxxxxxx\r\nx2222221000000000xxxxxxxx\r\nx2222221000000000xxxxxxxx\r\nx222222xx00000000xxxxxxxx\r\nx222222xx00000000xxxxxxxx\r\nx222222xxxxxxxxxxxxxxxxxx\r\nx222222xkkkkkkxxiiiiiiiix\r\nx222222xkkkkkkxxiiiiiiiix\r\nx222222xkkkkkkjiiiiiiiiix\r\nx222222xkkkkkkjiiiiiiiiix\r\nx222222xkkkkkkxxiiiiiiiix\r\nxxx11xxxkkkkkkxxiiiiiiiix\r\nxxx00xxxkkkkkkxxxxxxxxxxx\r\nx000000xkkkkkkxxxxxxxxxxx\r\nx000000xkkkkkkxxxxxxxxxxx\r\n0000000xkkkkkkxxxxxxxxxxx\r\nx000000xkkkkkkxxxxxxxxxxx\r\nx000000xkkkkkkxxxxxxxxxxx\r\nx000000xxxjjxxxxxxxxxxxxx\r\nx000000xxxiixxxxxxxxxxxxx\r\nx000000xiiiiiixxxxxxxxxxx\r\nxxxxxxxxiiiiiixxxxxxxxxxx\r\nxxxxxxxxiiiiiixxxxxxxxxxx\r\nxxxxxxxxiiiiiixxxxxxxxxxx\r\nxxxxxxxxiiiiiixxxxxxxxxxx\r\nxxxxxxxxiiiiiixxxxxxxxxxx\r\nxxxxxxxxiiiiiixxxxxxxxxxx\r\nxxxxxxxxiiiiiixxxxxxxxxxx'),
	('model_8', 0, 15, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx5555555555555555555555555xxxxxxxxx\r\nx5555555555555555555555555xxxxxxxxx\r\nx5555555555555555555555555xxxxxxxxx\r\nx5555555555555555555555555xxxxxxxxx\r\nx5555555555555555555555555xxxxxxxxx\r\nx5555555555555555555555555xxxxxxxxx\r\nx5555555555xxxxxxxxxxxxxxxxxxxxxxxx\r\nx55555555554321000000000000000000xx\r\nx55555555554321000000000000000000xx\r\nx5555555555xxxxx00000000000000000xx\r\nx555555x44x0000000000000000000000xx\r\nx555555x33x0000000000000000000000xx\r\nx555555x22x0000000000000000000000xx\r\nx555555x11x0000000000000000000000xx\r\n5555555x00x0000000000000000000000xx\r\nx555555x0000000000000000000000000xx\r\nx555555x0000000000000000000000000xx\r\nx555555x0000000000000000000000000xx\r\nx555555x0000000000000000000000000xx\r\nx555555x0000000000000000000000000xx\r\nx555555x0000000000000000000000000xx\r\nx555555x0000000000000000000000000xx\r\nx555555x0000000000000000000000000xx\r\nx555555x0000000000000000000000000xx\r\nx555555x0000000000000000000000000xx\r\nxxxxxxxx0000000000000000000000000xx\r\nxxxxxxxx0000000000000000000000000xx\r\nxxxxxxxx0000000000000000000000000xx\r\nxxxxxxxx0000000000000000000000000xx\r\nxxxxxxxx0000000000000000000000000xx\r\nxxxxxxxx0000000000000000000000000xx\r\nxxxxxxxx0000000000000000000000000xx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_9', 0, 17, 2, 'xxxxxxxxxxxxxxxxxxxxxxxx\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\n00000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nx0000000000000000000000x\r\nxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_a', 3, 5, 2, 'xxxxxxxxxxxx\r\nxxxx00000000\r\nxxxx00000000\r\nxxxx00000000\r\nxxxx00000000\r\nxxxx00000000\r\nxxxx00000000\r\nxxxx00000000\r\nxxxx00000000\r\nxxxx00000000\r\nxxxx00000000\r\nxxxx00000000\r\nxxxx00000000\r\nxxxx00000000\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx'),
	('model_b', 0, 5, 2, 'xxxxxxxxxxxx\r\nxxxxx0000000\r\nxxxxx0000000\r\nxxxxx0000000\r\nxxxxx0000000\r\nx00000000000\r\nx00000000000\r\nx00000000000\r\nx00000000000\r\nx00000000000\r\nx00000000000\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx'),
	('model_b2g', 0, 0, 2, 'xxxxxxxxxxxxxx\r\nx00000x000000x\r\nx000000000000x\r\nx00000x000000x\r\nx00000x000000x\r\nx00000x000000x\r\nx00000x000000x\r\nxxx0xxx000000x\r\nx000000000000x\r\nx000000000000x\r\nx000000000000x\r\nx000000000000x\r\nx000000000000x\r\nx000000000000x\r\nx000000000000x\r\nx000000000000x\r\nx000000000000x\r\nx00000xx00000x\r\nxxxxxxxxxxxxxx'),
	('model_c', 4, 7, 2, 'xxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx'),
	('model_d', 4, 7, 2, 'xxxxxxxxxxxx\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxxxxxxxxxxx'),
	('model_e', 1, 5, 2, 'xxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxx0000000000\r\nxx0000000000\r\nxx0000000000\r\nxx0000000000\r\nxx0000000000\r\nxx0000000000\r\nxx0000000000\r\nxx0000000000\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx'),
	('model_f', 2, 5, 2, 'xxxxxxxxxxxx\r\nxxxxxxx0000x\r\nxxxxxxx0000x\r\nxxx00000000x\r\nxxx00000000x\r\nxxx00000000x\r\nxxx00000000x\r\nx0000000000x\r\nx0000000000x\r\nx0000000000x\r\nx0000000000x\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx'),
	('model_g', 1, 7, 2, 'xxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxx00000\r\nxxxxxxx00000\r\nxxxxxxx00000\r\nxx1111000000\r\nxx1111000000\r\nxx1111000000\r\nxx1111000000\r\nxx1111000000\r\nxxxxxxx00000\r\nxxxxxxx00000\r\nxxxxxxx00000\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx'),
	('model_h', 4, 4, 2, 'xxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxx111111x\r\nxxxxx111111x\r\nxxxxx111111x\r\nxxxxx111111x\r\nxxxxx111111x\r\nxxxxx000000x\r\nxxxxx000000x\r\nxxx00000000x\r\nxxx00000000x\r\nxxx00000000x\r\nxxx00000000x\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx\r\nxxxxxxxxxxxx'),
	('model_i', 0, 10, 2, 'xxxxxxxxxxxxxxxxx\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nx0000000000000000\r\nxxxxxxxxxxxxxxxxx'),
	('model_j', 0, 10, 2, 'xxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxx0000000000\r\nxxxxxxxxxxx0000000000\r\nxxxxxxxxxxx0000000000\r\nxxxxxxxxxxx0000000000\r\nxxxxxxxxxxx0000000000\r\nxxxxxxxxxxx0000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx0000000000xxxxxxxxxx\r\nx0000000000xxxxxxxxxx\r\nx0000000000xxxxxxxxxx\r\nx0000000000xxxxxxxxxx\r\nx0000000000xxxxxxxxxx\r\nx0000000000xxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx'),
	('model_k', 0, 13, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxx00000000\r\nxxxxxxxxxxxxxxxxx00000000\r\nxxxxxxxxxxxxxxxxx00000000\r\nxxxxxxxxxxxxxxxxx00000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nx000000000000000000000000\r\nx000000000000000000000000\r\nx000000000000000000000000\r\nx000000000000000000000000\r\nx000000000000000000000000\r\nx000000000000000000000000\r\nx000000000000000000000000\r\nx000000000000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_l', 0, 16, 2, 'xxxxxxxxxxxxxxxxxxxxx\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000xxxx00000000\r\nx00000000xxxx00000000\r\nx00000000xxxx00000000\r\nx00000000xxxx00000000\r\nx00000000xxxx00000000\r\nx00000000xxxx00000000\r\nx00000000xxxx00000000\r\nx00000000xxxx00000000\r\nx00000000xxxx00000000\r\nx00000000xxxx00000000\r\nx00000000xxxx00000000\r\nx00000000xxxx00000000\r\nxxxxxxxxxxxxxxxxxxxxx'),
	('model_m', 0, 15, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nx0000000000000000000000000000\r\nx0000000000000000000000000000\r\nx0000000000000000000000000000\r\nx0000000000000000000000000000\r\nx0000000000000000000000000000\r\nx0000000000000000000000000000\r\nx0000000000000000000000000000\r\nx0000000000000000000000000000\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxx00000000xxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_n', 0, 16, 2, 'xxxxxxxxxxxxxxxxxxxxx\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx000000xxxxxxxx000000\r\nx000000x000000x000000\r\nx000000x000000x000000\r\nx000000x000000x000000\r\nx000000x000000x000000\r\nx000000x000000x000000\r\nx000000x000000x000000\r\nx000000xxxxxxxx000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nx00000000000000000000\r\nxxxxxxxxxxxxxxxxxxxxx'),
	('model_o', 0, 18, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxx11111111xxxx\r\nxxxxxxxxxxxxx11111111xxxx\r\nxxxxxxxxxxxxx11111111xxxx\r\nxxxxxxxxxxxxx11111111xxxx\r\nxxxxxxxxxxxxx11111111xxxx\r\nxxxxxxxxxxxxx11111111xxxx\r\nxxxxxxxxxxxxx11111111xxxx\r\nxxxxxxxxxxxxx00000000xxxx\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nx111111100000000000000000\r\nx111111100000000000000000\r\nx111111100000000000000000\r\nx111111100000000000000000\r\nx111111100000000000000000\r\nx111111100000000000000000\r\nx111111100000000000000000\r\nx111111100000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxx0000000000000000\r\nxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_opening', 0, 23, 2, 'xxxxxxxxxxxxxxxxxxx\r\nxxxxxxx222222222222\r\nxxxxxxx222222222222\r\nxxxxxxx222222222222\r\nxxxxxxx222222222222\r\nxxxxxxx222222222222\r\nxxxxxxx222222222222\r\nxxxxxxx222222222222\r\nxxxxxxx111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx2222xx111111111111\r\nx2222xx000000000000\r\nx2222xx000000000000\r\nx2222xx000000000000\r\nx2222xx000000000000\r\nx2222xx000000000000\r\n22222xx000000000000\r\nx2222xx000000000000\r\nxxxxxxxxxxxxxxxxxxx'),
	('model_oscar', 0, 15, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx11111111xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx11111111xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx11111111xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx11111111xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx11111111xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx11111111xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx11111111xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx11111111xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxx11xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx1111111x11111111x00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx1111111x11111111x00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx1111111x11111111x00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx1111111111111111100000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx1111111111111111100000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx1111111x11111111x00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx1111111x11111111x00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx1111111x11111111x00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxx11xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx00000000xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_p', 0, 23, 2, 'xxxxxxxxxxxxxxxxxxx\r\nxxxxxxx222222222222\r\nxxxxxxx222222222222\r\nxxxxxxx222222222222\r\nxxxxxxx222222222222\r\nxxxxxxx222222222222\r\nxxxxxxx222222222222\r\nxxxxxxx22222222xxxx\r\nxxxxxxx11111111xxxx\r\nx222221111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx222221111111111111\r\nx2222xx11111111xxxx\r\nx2222xx00000000xxxx\r\nx2222xx000000000000\r\nx2222xx000000000000\r\nx2222xx000000000000\r\nx2222xx000000000000\r\n22222xx000000000000\r\nx2222xx000000000000\r\nxxxxxxxxxxxxxxxxxxx'),
	('model_q', 10, 4, 2, 'xxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxx22222222\r\nxxxxxxxxxxx22222222\r\nxxxxxxxxxxx22222222\r\nxxxxxxxxxxx22222222\r\nxxxxxxxxxxx22222222\r\nxxxxxxxxxxx22222222\r\nx222222222222222222\r\nx222222222222222222\r\nx222222222222222222\r\nx222222222222222222\r\nx222222222222222222\r\nx222222222222222222\r\nx2222xxxxxxxxxxxxxx\r\nx2222xxxxxxxxxxxxxx\r\nx2222211111xx000000\r\nx222221111110000000\r\nx222221111110000000\r\nx2222211111xx000000\r\nxx22xxx1111xxxxxxxx\r\nxx11xxx1111xxxxxxxx\r\nx1111xx1111xx000000\r\nx1111xx111110000000\r\nx1111xx111110000000\r\nx1111xx1111xx000000\r\nxxxxxxxxxxxxxxxxxxx'),
	('model_r', 10, 4, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxx33333333333333\r\nxxxxxxxxxxx33333333333333\r\nxxxxxxxxxxx33333333333333\r\nxxxxxxxxxx333333333333333\r\nxxxxxxxxxxx33333333333333\r\nxxxxxxxxxxx33333333333333\r\nxxxxxxx333333333333333333\r\nxxxxxxx333333333333333333\r\nxxxxxxx333333333333333333\r\nxxxxxxx333333333333333333\r\nxxxxxxx333333333333333333\r\nxxxxxxx333333333333333333\r\nx4444433333xxxxxxxxxxxxxx\r\nx4444433333xxxxxxxxxxxxxx\r\nx44444333333222xx000000xx\r\nx44444333333222xx000000xx\r\nxxx44xxxxxxxx22xx000000xx\r\nxxx33xxxxxxxx11xx000000xx\r\nxxx33322222211110000000xx\r\nxxx33322222211110000000xx\r\nxxxxxxxxxxxxxxxxx000000xx\r\nxxxxxxxxxxxxxxxxx000000xx\r\nxxxxxxxxxxxxxxxxx000000xx\r\nxxxxxxxxxxxxxxxxx000000xx\r\nxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_s', 0, 3, 2, 'xxxxxx\r\nx00000\r\nx00000\r\n000000\r\nx00000\r\nx00000\r\nx00000\r\nx00000'),
	('model_t', 0, 3, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx222222222222222222222222222x\r\nx222222222222222222222222222x\r\n2222222222222222222222222222x\r\nx222222222222222222222222222x\r\nx2222xxxxxx222222xxxxxxx2222x\r\nx2222xxxxxx111111xxxxxxx2222x\r\nx2222xx111111111111111xx2222x\r\nx2222xx111111111111111xx2222x\r\nx2222xx11xxx1111xxxx11xx2222x\r\nx2222xx11xxx0000xxxx11xx2222x\r\nx22222111x00000000xx11xx2222x\r\nx22222111x00000000xx11xx2222x\r\nx22222111x00000000xx11xx2222x\r\nx22222111x00000000xx11xx2222x\r\nx22222111x00000000xx11xx2222x\r\nx22222111x00000000xx11xx2222x\r\nx2222xx11xxxxxxxxxxx11xx2222x\r\nx2222xx11xxxxxxxxxxx11xx2222x\r\nx2222xx111111111111111xx2222x\r\nx2222xx111111111111111xx2222x\r\nx2222xxxxxxxxxxxxxxxxxxx2222x\r\nx2222xxxxxxxxxxxxxxxxxxx2222x\r\nx222222222222222222222222222x\r\nx222222222222222222222222222x\r\nx222222222222222222222222222x\r\nx222222222222222222222222222x\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_u', 0, 17, 2, 'xxxxxxxxxxxxxxxxxxxxxxxx\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\n11111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nx1111100000000000000000x\r\nxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_v', 0, 3, 2, 'xxxxxxxxxxxxxxxxxxxx\r\nx222221111111111111x\r\nx222221111111111111x\r\n2222221111111111111x\r\nx222221111111111111x\r\nx222221111111111111x\r\nx222221111111111111x\r\nxxxxxxxx1111xxxxxxxx\r\nxxxxxxxx0000xxxxxxxx\r\nx000000x0000x000000x\r\nx000000x0000x000000x\r\nx00000000000x000000x\r\nx00000000000x000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nxxxxxxxx00000000000x\r\nx000000x00000000000x\r\nx000000x0000xxxxxxxx\r\nx00000000000x000000x\r\nx00000000000x000000x\r\nx00000000000x000000x\r\nx00000000000x000000x\r\nxxxxxxxx0000x000000x\r\nx000000x0000x000000x\r\nx000000x0000x000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nxxxxxxxxxxxxxxxxxxxx'),
	('model_w', 0, 3, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx2222xx1111111111xx11111111\r\nx2222xx1111111111xx11111111\r\n222222111111111111111111111\r\nx22222111111111111111111111\r\nx22222111111111111111111111\r\nx22222111111111111111111111\r\nx2222xx1111111111xx11111111\r\nx2222xx1111111111xx11111111\r\nx2222xx1111111111xxxx1111xx\r\nx2222xx1111111111xxxx0000xx\r\nxxxxxxx1111111111xx00000000\r\nxxxxxxx1111111111xx00000000\r\nx22222111111111111000000000\r\nx22222111111111111000000000\r\nx22222111111111111000000000\r\nx22222111111111111000000000\r\nx2222xx1111111111xx00000000\r\nx2222xx1111111111xx00000000\r\nx2222xxxx1111xxxxxxxxxxxxxx\r\nx2222xxxx0000xxxxxxxxxxxxxx\r\nx2222x0000000000xxxxxxxxxxx\r\nx2222x0000000000xxxxxxxxxxx\r\nx2222x0000000000xxxxxxxxxxx\r\nx2222x0000000000xxxxxxxxxxx\r\nx2222x0000000000xxxxxxxxxxx\r\nx2222x0000000000xxxxxxxxxxx'),
	('model_x', 0, 12, 2, 'xxxxxxxxxxxxxxxxxxxx\r\nx000000000000000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nxxx00xxx0000xxx00xxx\r\nx000000x0000x000000x\r\nx000000x0000x000000x\r\nx000000x0000x000000x\r\nx000000x0000x000000x\r\n0000000x0000x000000x\r\nx000000x0000x000000x\r\nx000000x0000x000000x\r\nx000000x0000x000000x\r\nx000000x0000x000000x\r\nx000000x0000x000000x\r\nx000000xxxxxx000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nx000000000000000000x\r\nxxxxxxxxxxxxxxxxxxxx'),
	('model_y', 0, 3, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nx00000000xx0000000000xx0000x\r\nx00000000xx0000000000xx0000x\r\n000000000xx0000000000xx0000x\r\nx00000000xx0000000000xx0000x\r\nx00000000xx0000xx0000xx0000x\r\nx00000000xx0000xx0000xx0000x\r\nx00000000xx0000xx0000000000x\r\nx00000000xx0000xx0000000000x\r\nxxxxx0000xx0000xx0000000000x\r\nxxxxx0000xx0000xx0000000000x\r\nxxxxx0000xx0000xxxxxxxxxxxxx\r\nxxxxx0000xx0000xxxxxxxxxxxxx\r\nx00000000xx0000000000000000x\r\nx00000000xx0000000000000000x\r\nx00000000xx0000000000000000x\r\nx00000000xx0000000000000000x\r\nx0000xxxxxxxxxxxxxxxxxx0000x\r\nx0000xxxxxxxxxxxxxxxxxx0000x\r\nx00000000000000000000000000x\r\nx00000000000000000000000000x\r\nx00000000000000000000000000x\r\nx00000000000000000000000000x\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('model_z', 0, 9, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxx00000000000000000000\r\nxxxxxxxxxxx00000000000000000000\r\nxxxxxxxxxxx00000000000000000000\r\nx00000000xx00000000000000000000\r\nx00000000xx00000000000000000000\r\nx00000000xx00000000000000000000\r\nx00000000xx00000000000000000000\r\nx00000000xx00000000000000000000\r\n000000000xx00000000000000000000\r\nx00000000xx00000000000000000000\r\nx00000000xx00000000000000000000\r\nx00000000xx00000000000000000000\r\nx00000000xx00000000000000000000\r\nx00000000xx00000000000000000000\r\nx00000000xx00000000000000000000\r\nxxxxxxxxxxx00000000000000000000\r\nxxxxxxxxxxx00000000000000000000\r\nxxxxxxxxxxx00000000000000000000\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('netcafe', 22, 12, 2, 'xxxxx1111xxxxxxxxxxx1xxxx\r\nxxxxx1111111111111111xxxx\r\nxxxxx1111111111111111xxxx\r\nxxxxx1111111111111111xxxx\r\nxxxxxxxx0000000000000xxxx\r\n111111100000000000000xxxx\r\n111111100000000000000xxxx\r\n111111100000000000000xxxx\r\nxxxx11100000000000000xxxx\r\nx1xx11100000000000000xxxx\r\nx1xx11100000000000000xxxx\r\nx1xx111000000000000000000\r\nx1xx111000000000000000000\r\nxxxx111000000000000000000\r\nxxxx11100000000000000xxxx\r\nxxxx1110000000xx11111xxxx\r\nxxxxx111110000x111111xxxx\r\nxxxxxx111100001111111xxxx\r\nxxxxxx111100001111111xxxx\r\nxxxxxx111100001111111xxxx\r\nxxxxxx111100001111111xxxx\r\nxxxxxx111100001111111xxxx\r\nxxxxxx111100001111111xxxx\r\nxxxxx1111100001111111xxxx'),
	('newbie_lobby', 2, 11, 2, 'xxxxxxxxxxxxxxxx000000\r\nxxxxx0xxxxxxxxxx000000\r\nxxxxx00000000xxx000000\r\nxxxxx000000000xx000000\r\n0000000000000000000000\r\n0000000000000000000000\r\n0000000000000000000000\r\n0000000000000000000000\r\n0000000000000000000000\r\nxxxxx000000000000000xx\r\nxxxxx000000000000000xx\r\nx0000000000000000000xx\r\nx0000000000000000xxxxx\r\nxxxxxx00000000000xxxxx\r\nxxxxxxx0000000000xxxxx\r\nxxxxxxxx000000000xxxxx\r\nxxxxxxxx000000000xxxxx\r\nxxxxxxxx000000000xxxxx\r\nxxxxxxxx000000000xxxxx\r\nxxxxxxxx000000000xxxxx\r\nxxxxxxxx000000000xxxxx\r\nxxxxxx00000000000xxxxx\r\nxxxxxx00000000000xxxxx\r\nxxxxxx00000000000xxxxx\r\nxxxxxx00000000000xxxxx\r\nxxxxxx00000000000xxxxx\r\nxxxxx000000000000xxxxx\r\nxxxxx000000000000xxxxx'),
	('old_skool', 2, 1, 2, 'xx0xxxxxxxxxxxxxx\r\n0000000xxx00000xx\r\n0000000x0000000xx\r\n0000000xxxxxxxxxx\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n00000000000000000\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x\r\n0000000000000000x'),
	('orient', 35, 22, 6, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxx00000000xxxxxxxxxxxx\r\nxxxxxxxxxxxxxx1000000000xxxxxxxxxxxx\r\nxxxxxxxxxxxxxx1xxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxx1xxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxx1xxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxx1xxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxx1xxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxx1xxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxx1xxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxx1xx000x000xx111x111xxx\r\nxxxxxxxxxxxxxx1xx000x000xxxxxx1111xx\r\nxxxxxxxxxxxxxx1xx000x000x111111111xx\r\nxxxxxxxxxxxxxx1xx000x000x111111111xx\r\nxxx111111111111xx000x000x111111111xx\r\nxxx1xxxxxxxxxxxxx000x000x111111111xx\r\nxxx1x1111111111000000000x111111111xx\r\nxxx1x1111111111000000000xx1111111xxx\r\nxxx1x11xxxxxx11000000000xx1111111100\r\nxxx111xxxxxxx11000000000011111111100\r\nxxx111xxxxxxx11000000000011111111100\r\nxxxxx1xxxxxxx11000000000011111111100\r\nxxxxx11xxxxxx11000000000xx1111111100\r\nxxxxx1111111111000000000xx1111111xxx\r\nxxxxx1111111111xx000x000x111111111xx\r\nxxxxxxxxxxxxxxxxx000x000x111111111xx\r\nxxxxxxxxxxxxxxxxx000x000x111111111xx\r\nxxxxxxxxxxxxxxxxx000x000x111111111xx\r\nxxxxxxxxxxxxxxxxx000x000x111111111xx\r\nxxxxxxxxxxxxxxxxx000x00xx11xxxx111xx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxx11111111xx'),
	('park_a', 2, 15, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx0xxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx00xxxxxxxxxxxx\r\nxxxxxxxxxxxxx0x00xxxxxxxxxxx0x000xxxxxxxxxxx\r\nxxxxxxxxxxxx0000000000000000000000xxxxxxxxxx\r\nxxxxxxxxxxx000000000000000000000000xxxxxxxxx\r\nxxxxxxxxxxx0000000000000000000000000xxxxxxxx\r\nxxxxxxxxxxx00000000000000000000000000xxxxxxx\r\nxxxxxxxx000000000000000000000000000000xxxxxx\r\nxxxxxxx00000000000000000000000000000000xxxxx\r\nxxxxxxx000000000000000000000000000000000xxxx\r\nxxxxxxx0000000000000000000000000000000000xxx\r\nxxxxxxxxx000000000000000000000000000000000xx\r\n00000000000000000000xx00000000000000000000xx\r\n0000000000000000000xxxx00000000000xxxxxxx0xx\r\n0000000000000000000xxxx00000000000x00000xxxx\r\nxxxxx00x0000000000xxxxx0xxxxxx0000x0000000xx\r\nxxxxx0000000000000xxxxx0xx000x0000x000000xxx\r\nxxxxx0000000000000xxxxx0x000000000x00000xxxx\r\nxxxxx000000x0000000xxxx0x000000000xxx00xxxxx\r\nxxxxxxxx000x0000000xxx00xxx000000x0000xxxxxx\r\nxxxxxxxx000x000000xxxx0x0000000000000xxxxxxx\r\nxxxxxxxx000x000000011100000000000000xxxxxxxx\r\nxxxxxxxx000x00000001110000000000000xxxxxxxxx\r\nxxxxxxxxx00x0000000111x00000000x00xxxxxxxxxx\r\nxxxxxxxxxx0x0000000xxx0000000xxxxxxxxxxxxxxx\r\nxxxxxxxxxxxx000000xxxx0000000xxxxxxxxxxxxxxx\r\nxxxxxxxxxxxx000000xxx00xxxxx00xxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxx0xxx0xx000x00xxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxx0xxx0x000000xxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxx0xxxxx00000xxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxx0xxxxx00xxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxx0xxxxxxxxxxxxxxxxxxxx'),
	('park_b', 11, 2, 6, '0000x0000000\r\n0000xx000000\r\n000000000000\r\n00000000000x\r\n000000000000\r\n00x0000x0000'),
	('picnic', 16, 5, 4, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxx22222xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\n22xxxxxxxxxxxxx22xxxxxxxxxxxxxxxxxxxxx\r\n2222222222222222222x222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222xxx222222222222222222222222\r\n2222222222xx33x22222222222222222222222\r\n222222222xx3333x2222222222222222222222\r\n222222222x333333x222222222222222222222\r\n222222222x333333x222222222222222222222\r\n2222222222x3332x2222222222222222222222\r\n22222222222x33x22222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222x22222xxxx22222222222222222222\r\n22222222222222xxxx22222222222222222222\r\n22222222222222xxx222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222\r\n22222222222222222222222222222222222222'),
	('pizza', 5, 27, 2, 'xxxxxxxxx0000000\r\nx11111x1xx000000\r\n11xxxxx111x00000\r\n11x1111111xx0000\r\n11x1111111100000\r\nxxx1111111100000\r\n1111111111100000\r\n1111111111100000\r\n1111111111100000\r\n1111111111100000\r\n1111111111100000\r\n1111111111100000\r\n1111111111100000\r\n1111111111100000\r\n1111111111100000\r\n1111111111100000\r\n1111111111100000\r\n11111111111xxxxx\r\n1111111111xxxxxx\r\n1111111111111111\r\n1111111111111111\r\n1111111111111111\r\n1111111111111111\r\n1111111111111111\r\n1111111111111111\r\n11xx11xx11111111\r\nxxxx11xxxxxxxxxx\r\nxxxx11xxxxxxxxxx'),
	('pub_a', 15, 25, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxx2222222211111xxx\r\nxxxxxxxxx2222222211111xxx\r\nxxxxxxxxx2222222211111xxx\r\nxxxxxxxxx2222222211111xxx\r\nxxxxxxxxx2222222222111xxx\r\nxxxxxxxxx2222222222111xxx\r\nxxxxxxxxx2222222222000xxx\r\nxxxxxxxxx2222222222000xxx\r\nxxxxxxxxx2222222222000xxx\r\nxxxxxxxxx2222222222000xxx\r\nx333333332222222222000xxx\r\nx333333332222222222000xxx\r\nx333333332222222222000xxx\r\nx333333332222222222000xxx\r\nx333333332222222222000xxx\r\nx333332222222222222000xxx\r\nx333332222222222222000xxx\r\nx333332222222222222000xxx\r\nx333332222222222222000xxx\r\nx333333332222222222000xxx\r\nxxxxx31111112222222000xxx\r\nxxxxx31111111000000000xxx\r\nxxxxx31111111000000000xxx\r\nxxxxx31111111000000000xxx\r\nxxxxx31111111000000000xxx\r\nxxxxxxxxxxxxxxx00xxxxxxxx\r\nxxxxxxxxxxxxxxx00xxxxxxxx\r\nxxxxxxxxxxxxxxx00xxxxxxxx\r\nxxxxxxxxxxxxxxx00xxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('rooftop', 17, 12, 2, '44xxxxxxxxxxxxxxxxxx\r\n444xxxxxxxxxxx444444\r\n4444xxxxxxxxxx444444\r\n44444xxxx4xxxx444444\r\n444444xxx44xxx444444\r\n44444444444444444444\r\n44444444444444444444\r\n44444444444444444444\r\n44444444xx44xx44xx44\r\n44444444xx44xx44xx44\r\n44444444444444444444\r\n44444444444444444444\r\n44444444444444444444\r\nx444444x444444xx4444\r\nx444444x444444xx333x\r\nx444444x444444xx222x\r\nx444444x444444xx11xx\r\nx444444x444444xxxxxx'),
	('rooftop_2', 4, 9, 2, 'x0000x000\r\nxxxxxx000\r\n000000000\r\n000000000\r\n000000000\r\n000000000\r\n000000000\r\n000000000\r\n000000000\r\n000000000\r\nxxx000xxx\r\nxxx000xxx'),
	('star_lounge', 37, 36, 2, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx2222x4444442222xxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx22222x444x32222xxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx22222xx4xx22222xxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx222222222222222xxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx222222222222222xxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx222222222222222xxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx222222222222222xxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx222222222222222xxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx222222222222222xxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx22222222222222211111xxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx22222222222222211111xxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx22222222222222211111xxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx22222222222222211111xxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx22222222222222222111xxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxx22222222222222222111xxxxxxxxx\r\nxxxxxxxxxxxxxxxx3333x22222222222222xxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxx3333x22222222222222xxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxx3333x22222222221111xxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxx3333xx2x22222220000xxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxx333333332222222000000xxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxx333333332222222x0000000xxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxx33333332222222x0000000xxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxx222222000000xxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
	('tearoom', 21, 19, 6, 'xxxxxxxxxxxxxxxxxxxxxx\r\nxxxxxxxx3333x33333333x\r\n333333xx3333x33333333x\r\n3333333x3333x33333333x\r\n3333333x3333x33333333x\r\n3333333xxxxxx33333333x\r\n333333333333333333333x\r\n333333333333333333333x\r\n333333333333333333333x\r\n333333333333333333333x\r\n33333333222x333333333x\r\n33333333222x333333333x\r\n33333333222x333333333x\r\n33333333222x333333333x\r\n33333333111x333333333x\r\n33333333111x333333333x\r\n33333333111x333333333x\r\nxxxxxxxx111xxxxxxxxxxx\r\n11111111111111111111xx\r\n1111111111111111111111\r\n1111111111111111111111\r\n11111111111111111111xx'),
	('theater', 20, 27, 0, 'XXXXXXXXXXXXXXXXXXXXXXX\r\nXXXXXXXXXXXXXXXXXXXXXXX\r\nXXXXXXXXXXXXXXXXXXXXXXX\r\nXXXXXXXXXXXXXXXXXXXXXXX\r\nXXXXXXXXXXXXXXXXXXXXXXX\r\nXXXXXXXXXXXXXXXXXXXXXXX\r\nXXXXXXX111111111XXXXXXX\r\nXXXXXXX11111111100000XX\r\nXXXX00X11111111100000XX\r\nXXXX00x11111111100000XX\r\n4XXX00X11111111100000XX\r\n4440000XXXXXXXXX00000XX\r\n444000000000000000000XX\r\n4XX000000000000000000XX\r\n4XX0000000000000000000X\r\n44400000000000000000000\r\n44400000000000000000000\r\n44X0000000000000000O000\r\n44X11111111111111111000\r\n44X11111111111111111000\r\n33X11111111111111111000\r\n22X11111111111111111000\r\n22X11111111111111111000\r\n22X11111111111111111000\r\n22X11111111111111111000\r\n22X11111111111111111000\r\n22211111111111111111000\r\n22211111111111111111000\r\nXXXXXXXXXXXXXXXXXXXX00X\r\nXXXXXXXXXXXXXXXXXXXX00X');
/*!40000 ALTER TABLE `room_models` ENABLE KEYS */;

-- Dumping structure for table hylib.room_rights
DROP TABLE IF EXISTS `room_rights`;
CREATE TABLE IF NOT EXISTS `room_rights` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `player_id` int(10) DEFAULT 0,
  `room_id` int(10) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.room_rights: ~0 rows (approximately)
DELETE FROM `room_rights`;
/*!40000 ALTER TABLE `room_rights` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_rights` ENABLE KEYS */;

-- Dumping structure for table hylib.room_word_filter
DROP TABLE IF EXISTS `room_word_filter`;
CREATE TABLE IF NOT EXISTS `room_word_filter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL DEFAULT 0,
  `word` varchar(255) NOT NULL DEFAULT 'bobba',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `room_id_word` (`room_id`,`word`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.room_word_filter: ~0 rows (approximately)
DELETE FROM `room_word_filter`;
/*!40000 ALTER TABLE `room_word_filter` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_word_filter` ENABLE KEYS */;

-- Dumping structure for table hylib.server_articles
DROP TABLE IF EXISTS `server_articles`;
CREATE TABLE IF NOT EXISTS `server_articles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `message` varchar(512) DEFAULT NULL,
  `button_text` varchar(35) DEFAULT NULL,
  `button_link` varchar(512) DEFAULT '',
  `image_path` varchar(200) DEFAULT NULL,
  `visible` enum('1','0') DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.server_articles: ~0 rows (approximately)
DELETE FROM `server_articles`;
/*!40000 ALTER TABLE `server_articles` DISABLE KEYS */;
INSERT INTO `server_articles` (`id`, `title`, `message`, `button_text`, `button_link`, `image_path`, `visible`) VALUES
	(1, 'Bienvenidos a HGalaxy', 'Nos encontramos en fase BETA. Â¿Te gustarÃ­a estar al dÃ­a de todas las novedades del hotel? Â¡Ãšnete a nuestro discord desde el siguiente botÃ³n!', 'Ir a Discord', 'https://discord.gg/hgalaxy', 'LogoDiscord.png', '1');
/*!40000 ALTER TABLE `server_articles` ENABLE KEYS */;

-- Dumping structure for table hylib.server_bets
DROP TABLE IF EXISTS `server_bets`;
CREATE TABLE IF NOT EXISTS `server_bets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `amount` varchar(255) NOT NULL,
  `timestamp` varchar(255) NOT NULL,
  `result` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.server_bets: ~0 rows (approximately)
DELETE FROM `server_bets`;
/*!40000 ALTER TABLE `server_bets` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_bets` ENABLE KEYS */;

-- Dumping structure for table hylib.server_calendar_gifts
DROP TABLE IF EXISTS `server_calendar_gifts`;
CREATE TABLE IF NOT EXISTS `server_calendar_gifts` (
  `day` int(11) NOT NULL,
  `gift` varchar(24) NOT NULL DEFAULT '',
  `product` varchar(32) NOT NULL DEFAULT '',
  `image` varchar(128) DEFAULT '',
  `item` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.server_calendar_gifts: ~0 rows (approximately)
DELETE FROM `server_calendar_gifts`;
/*!40000 ALTER TABLE `server_calendar_gifts` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_calendar_gifts` ENABLE KEYS */;

-- Dumping structure for table hylib.server_config
DROP TABLE IF EXISTS `server_config`;
CREATE TABLE IF NOT EXISTS `server_config` (
  `key` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `value` text CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.server_config: ~2 rows (approximately)
DELETE FROM `server_config`;
/*!40000 ALTER TABLE `server_config` DISABLE KEYS */;
INSERT INTO `server_config` (`key`, `value`) VALUES
	('hotel.home.room', '19'),
	('hotel.motd.enabled', '0');
/*!40000 ALTER TABLE `server_config` ENABLE KEYS */;

-- Dumping structure for table hylib.server_configuration
DROP TABLE IF EXISTS `server_configuration`;
CREATE TABLE IF NOT EXISTS `server_configuration` (
  `motd_enabled` enum('true','false') NOT NULL DEFAULT 'true',
  `motd_message` varchar(500) NOT NULL DEFAULT 'Welcome to Comet!',
  `hotel_name` varchar(100) NOT NULL DEFAULT 'Comet',
  `hotel_url` varchar(100) NOT NULL DEFAULT '',
  `group_cost` int(11) NOT NULL DEFAULT 100,
  `online_reward_enabled` enum('true','false') NOT NULL DEFAULT 'true',
  `online_reward_interval` int(11) NOT NULL DEFAULT 15,
  `online_reward_credits` int(11) NOT NULL DEFAULT 150,
  `online_reward_duckets` int(11) NOT NULL DEFAULT 150,
  `online_reward_diamonds_interval` int(11) NOT NULL DEFAULT 150,
  `online_reward_diamonds` int(11) NOT NULL DEFAULT 150,
  `online_reward_double_days` varchar(255) NOT NULL DEFAULT '',
  `about_image` varchar(100) NOT NULL DEFAULT '',
  `about_show_players_online` enum('true','false') NOT NULL DEFAULT 'false',
  `about_show_rooms_active` enum('true','false') NOT NULL DEFAULT 'false',
  `about_show_uptime` enum('true','false') NOT NULL DEFAULT 'false',
  `floor_editor_max_x` int(11) NOT NULL DEFAULT 0,
  `floor_editor_max_y` int(11) NOT NULL DEFAULT 0,
  `floor_editor_max_total` int(11) NOT NULL DEFAULT 0,
  `room_max_players` int(11) NOT NULL DEFAULT 150,
  `room_encrypt_passwords` enum('true','false') NOT NULL DEFAULT 'false',
  `room_can_place_item_on_entity` enum('true','false') NOT NULL DEFAULT 'false',
  `room_max_bots` int(11) NOT NULL DEFAULT 15,
  `room_max_pets` int(11) NOT NULL DEFAULT 15,
  `room_wired_reward_minimum_rank` int(11) NOT NULL DEFAULT 7,
  `room_wired_max_effects` int(11) NOT NULL DEFAULT 10,
  `room_wired_max_triggers` int(11) NOT NULL DEFAULT 10,
  `room_wired_max_execute_stacks` int(11) NOT NULL DEFAULT 5,
  `room_idle_minutes` int(11) NOT NULL DEFAULT 15,
  `word_filter_mode` enum('default','smart','strict') NOT NULL DEFAULT 'default',
  `word_filter_strict_chars` varchar(500) NOT NULL DEFAULT '',
  `use_database_ip` enum('true','false') NOT NULL DEFAULT 'false',
  `save_logins` enum('true','false') NOT NULL DEFAULT 'false',
  `player_infinite_balance` enum('true','false') NOT NULL DEFAULT 'false',
  `player_gift_cooldown` int(11) NOT NULL DEFAULT 30,
  `player_change_figure_cooldown` int(11) NOT NULL DEFAULT 5,
  `player_figure_validation` enum('true','false') NOT NULL DEFAULT 'false',
  `messenger_max_friends` int(11) NOT NULL DEFAULT 1500,
  `messenger_log_messages` enum('true','false') NOT NULL DEFAULT 'true',
  `camera_photo_url` varchar(255) NOT NULL DEFAULT 'http://localhost:8080/camera/photo/%photoId%.png',
  `camera_photo_upload_url` varchar(255) NOT NULL DEFAULT 'http://localhost:8080/camera/upload/%photoId%',
  `camera_photo_itemid` int(11) NOT NULL DEFAULT 50001,
  `max_connections_per_ip` int(11) NOT NULL DEFAULT 5,
  `max_connections_block_suspicious` enum('true','false') NOT NULL DEFAULT 'true',
  `log_catalog_purchases` enum('true','false') DEFAULT 'true',
  `group_chat_enabled` enum('true','false') NOT NULL DEFAULT 'false',
  `hall_of_fame_enabled` enum('true','false') NOT NULL DEFAULT 'true',
  `hall_of_fame_currency` varchar(50) NOT NULL DEFAULT 'vip_points',
  `hall_of_fame_refresh_minutes` int(10) NOT NULL DEFAULT 10,
  `hall_of_fame_texts_key` varchar(50) NOT NULL DEFAULT 'halloffame',
  `bet_system_enabled` enum('true','false') NOT NULL DEFAULT 'true',
  `bet_system_roomid` int(11) NOT NULL DEFAULT 0,
  `event_winner_enabled` enum('true','false') NOT NULL DEFAULT 'false',
  `max_seasonal_reward` int(11) NOT NULL DEFAULT 50,
  `casino_free_roll_enabled` enum('true','false') NOT NULL DEFAULT 'false',
  `bank_mininum_required` int(11) NOT NULL DEFAULT 25,
  `bank_seasonal_enabled` enum('true','false') NOT NULL DEFAULT 'false',
  `landing_bag_enabled` enum('true','false') NOT NULL DEFAULT 'true',
  `landing_bag_configuration` varchar(255) NOT NULL DEFAULT 'item,102,diamonds,50',
  `currency_enabled` enum('true','false') NOT NULL DEFAULT 'false',
  `websocket_url` varchar(100) NOT NULL DEFAULT '',
  `casino_roomid` int(11) NOT NULL DEFAULT 12951,
  `community_goal` int(11) NOT NULL DEFAULT 150,
  `community_goal_prize` varchar(100) NOT NULL DEFAULT 'ADM',
  `easter_limited` int(11) NOT NULL DEFAULT 1,
  `calendar_timestamp` int(11) NOT NULL,
  `rp_hospital_roomid` int(11) NOT NULL DEFAULT 1,
  `rp_police_roomid` int(11) NOT NULL DEFAULT 1,
  `rp_law_roomid` int(11) NOT NULL DEFAULT 1,
  `rp_mafia_roomid` int(11) NOT NULL DEFAULT 1,
  `rp_politics_roomid` int(11) NOT NULL DEFAULT 1,
  `rp_hospital_salary` int(11) NOT NULL DEFAULT 1,
  `rp_police_salary` int(11) NOT NULL DEFAULT 1,
  `rp_law_salary` int(11) NOT NULL DEFAULT 1,
  `rp_mafia_salary` int(11) NOT NULL DEFAULT 1,
  `rp_politic_salary` int(11) NOT NULL DEFAULT 1,
  `rp_salary_interval` int(11) NOT NULL DEFAULT 1,
  `rp_hunger_interval` int(11) NOT NULL DEFAULT 60,
  `rp_hunger_tick_amount` int(11) NOT NULL DEFAULT 5,
  `rp_starving_interval` int(11) NOT NULL DEFAULT 35,
  `rp_starving_tick_amount` int(11) NOT NULL DEFAULT 5,
  `console_debug` enum('true','false') NOT NULL DEFAULT 'true',
  `snow_storm_min_players` int(11) NOT NULL DEFAULT 5,
  `welcome_room_id` int(11) NOT NULL DEFAULT 1,
  `seasonal_activity_points` int(11) NOT NULL DEFAULT 1,
  `thumbnail_upload_url` varchar(100) NOT NULL DEFAULT 'http://localhost:8080/camera/upload/%photoId%'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.server_configuration: ~0 rows (approximately)
DELETE FROM `server_configuration`;
/*!40000 ALTER TABLE `server_configuration` DISABLE KEYS */;
INSERT INTO `server_configuration` (`motd_enabled`, `motd_message`, `hotel_name`, `hotel_url`, `group_cost`, `online_reward_enabled`, `online_reward_interval`, `online_reward_credits`, `online_reward_duckets`, `online_reward_diamonds_interval`, `online_reward_diamonds`, `online_reward_double_days`, `about_image`, `about_show_players_online`, `about_show_rooms_active`, `about_show_uptime`, `floor_editor_max_x`, `floor_editor_max_y`, `floor_editor_max_total`, `room_max_players`, `room_encrypt_passwords`, `room_can_place_item_on_entity`, `room_max_bots`, `room_max_pets`, `room_wired_reward_minimum_rank`, `room_wired_max_effects`, `room_wired_max_triggers`, `room_wired_max_execute_stacks`, `room_idle_minutes`, `word_filter_mode`, `word_filter_strict_chars`, `use_database_ip`, `save_logins`, `player_infinite_balance`, `player_gift_cooldown`, `player_change_figure_cooldown`, `player_figure_validation`, `messenger_max_friends`, `messenger_log_messages`, `camera_photo_url`, `camera_photo_upload_url`, `camera_photo_itemid`, `max_connections_per_ip`, `max_connections_block_suspicious`, `log_catalog_purchases`, `group_chat_enabled`, `hall_of_fame_enabled`, `hall_of_fame_currency`, `hall_of_fame_refresh_minutes`, `hall_of_fame_texts_key`, `bet_system_enabled`, `bet_system_roomid`, `event_winner_enabled`, `max_seasonal_reward`, `casino_free_roll_enabled`, `bank_mininum_required`, `bank_seasonal_enabled`, `landing_bag_enabled`, `landing_bag_configuration`, `currency_enabled`, `websocket_url`, `casino_roomid`, `community_goal`, `community_goal_prize`, `easter_limited`, `calendar_timestamp`, `rp_hospital_roomid`, `rp_police_roomid`, `rp_law_roomid`, `rp_mafia_roomid`, `rp_politics_roomid`, `rp_hospital_salary`, `rp_police_salary`, `rp_law_salary`, `rp_mafia_salary`, `rp_politic_salary`, `rp_salary_interval`, `rp_hunger_interval`, `rp_hunger_tick_amount`, `rp_starving_interval`, `rp_starving_tick_amount`, `console_debug`, `snow_storm_min_players`, `welcome_room_id`, `seasonal_activity_points`, `thumbnail_upload_url`) VALUES
	('false', 'Ã‚Â¡Hola! Bienvenid@ a la nueva versiÃƒÂ³n de Habbo. Descubre todas las novedades que traemos para ti.', 'Habbo', 'http://Habbo.eu', 0, 'true', 45, 7, 5, 0, 0, 'sunday', 'https://i.imgur.com/vqn1Qpv.gif', 'true', 'true', 'true', 0, 0, 0, 100, 'false', 'true', 25, 25, 8, 80, 80, 80, 30, 'strict', '', 'false', 'false', 'false', 10, 5, 'false', 1100, 'true', 'C:\\inetpub\\wwwroot\\Habbo\\swfs\\c_images\\camera\\photo\\', 'C:\\inetpub\\wwwroot\\Habbo\\swfs\\c_images\\camera\\upload\\', 886734453, 10, 'true', 'true', 'false', 'false', 'vip_points', 15, 'vip_points', 'false', 0, 'true', 20, 'false', 8, 'false', 'false', 'bonusbag17_1,9161,5000', 'true', 'Habbo.eu:2087', 12951, 2, 'IT792', 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 60, 5, 35, 5, 'false', 3, 19, 1, '/wwwroot/Habbo/swfs/c_images/camera/thumbnails/');
/*!40000 ALTER TABLE `server_configuration` ENABLE KEYS */;

-- Dumping structure for table hylib.server_locale
DROP TABLE IF EXISTS `server_locale`;
CREATE TABLE IF NOT EXISTS `server_locale` (
  `key` varchar(50) NOT NULL,
  `value` text DEFAULT NULL,
  PRIMARY KEY (`key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.server_locale: ~185 rows (approximately)
DELETE FROM `server_locale`;
/*!40000 ALTER TABLE `server_locale` DISABLE KEYS */;
INSERT INTO `server_locale` (`key`, `value`) VALUES
	('activity.points.name', 'Asteroides'),
	('badge.deleted', '¡Se te ha retirado una placa!'),
	('badge.get', '¡Has recibido una placa de parte del equipo administrativo!'),
	('bots.chat.giveItemMessage', '¡Aquí tienes, %username%... Disfruta!'),
	('bots.chat.tooFar', 'Vas a tener que acercarte, %username%... ¡No te alcanzo!'),
	('camera.photoTaken', '¡Fotografía tomada exitosamente!'),
	('catalog.error.nooffer', '¡Oh! Este furni no tiene una oferta activa'),
	('catalog.error.notenough', '¡No tienes suficientes créditos para comprar este furni!'),
	('catalog.error.toomany', 'Solo puedes comprar hasta 50 furnis al mismo tiempo'),
	('coins.name', 'créditos'),
	('command.about.description', '<font color=\'#a10505\'><b>:about</b></font>  <b>-></b>  Muestra información del servidor.'),
	('command.about.name', 'about,info'),
	('command.alert.description', '<font color=\'#a10505\'><b>:alert [Usuario] [Mensaje]</b></font>  <b>-></b>  Envía una alerta a un usuario.'),
	('command.alert.name', 'alert,alerta'),
	('command.alert.title', 'Alerta'),
	('command.alertdj.image', 'DJ'),
	('command.alertinter.image', 'RRT'),
	('command.alertmaster.image', 'RRT'),
	('command.alertnoti.description', '<font color=\'#a10505\'><b>:alertnoti [Tipo] [Mensaje]</font>  -></b>  Envia una notificacion sobre un evento.'),
	('command.alertnoti.name', 'alertnoti,noti'),
	('command.autofloor.description', '<font color=\'#a10505\'><b>:autofloor</b></font>  <b>-></b>  Elimina el suelo que no posea furnis.'),
	('command.autofloor.name', 'autofloor'),
	('command.ban.description', ':ban - Banea la cuenta de un usuario'),
	('command.ban.error', 'Hubo un error al banear al usuario:'),
	('command.ban.name', 'ban'),
	('command.bank.description', '<font color=\'#a10505\'><b>:pay [Usuario] [Cantidad] [Moneda]</b></font>  <b>-</b>  Envía créditos o asteroides a un usuario.'),
	('command.bank.name', 'pay,pagar'),
	('command.build.description', ':build - Muestra la versión actual del servidor'),
	('command.build.name', 'build'),
	('command.bundle.create', 'Usa :bundle create <alias> para crear un bundle.'),
	('command.bundle.description', ':bundle - Úsalo para controlar los bundles'),
	('command.bundle.name', 'bundle'),
	('command.buyroom.description', '<font color=\'#a10505\'><b>:comprarsala</b></font>  <b>-></b>  ¿Sala en venta? ¡Cómprala ahora!'),
	('command.buyroom.name', 'comprarsala,buyroom'),
	('command.changelog.name', 'changelog'),
	('command.cloneroom.description', '<font color=\'#a10505\'><b>:cloneroom</b></font>  <b>-></b>  Haz una copia de la sala presente.'),
	('command.cloneroom.name', 'cloneroom,copyroom,dupliroom,copiarsala,duplicarsala'),
	('command.closedice.description', '<font color=\'#a10505\'><b>:cd</b></font>  <b>-></b>  Cierra automáticamente todos los dados del alrededor.'),
	('command.closedice.message', '<font color=\'#269464\'><b>¡%username% cerró en \' %amount% \' el dado!</b></font>'),
	('command.closedice.name', 'cd,closedice,cerrardados'),
	('command.coins.description', ':coins - (usuario) (cantidad) da una cantidad de créditos a un usuario'),
	('command.coins.errortitle', 'Error'),
	('command.coins.formaterror', 'Error en el formato'),
	('command.coins.name', 'coins'),
	('command.coins.received', 'Has recibido %amount% créditos'),
	('command.coins.title', 'Alert'),
	('command.colour.description', ':colour - ¡Cambia tu nombre de color!'),
	('command.colour.done', 'Ahora tu color es %colour%'),
	('command.colour.invalid', 'Color inválido, colores disponibles: %colours%'),
	('command.colour.name', 'colour'),
	('command.colour.reset', 'Tu chat se ha reseteado!'),
	('command.commands.description', '<font color=\'#a10505\'><b>:commands</b></font>  <b>-></b>  Abrir la lista de comandos.'),
	('command.commands.name', 'commands,comandos'),
	('command.commands.title', '- Lista de comandos'),
	('command.control.description', '<font color=\'#a10505\'><b>:control [Usuario]</b></font>  <b>-></b>  Controla a un usuario.'),
	('command.deletegroup.confirm', '<b>¿Estás seguro de querer eliminar este grupo?</b> Todos los items del grupo volverán al inventario de su correspondiente dueño y el grupo se eliminará para siempre.</font> <br><br>¡Usa el comando: <b>:deletegroup</b> de nuevo para eliminarlo!'),
	('command.deletegroup.description', '<font color=\'#a10505\'><b>:deletegroup</b></font>  <b>-></b>  Borra el grupo actual de la sala.'),
	('command.deletegroup.name', 'deletegroup,borrargrupo'),
	('command.disablecommand.description', '<font color=\'#a10505\'><b>:disablecommand</b></font>  <b>-></b>  Desactiva un comando para toda la sala.'),
	('command.disablecommand.error', '<font color=\'#a10505\'><b>¡Ha ocurrido un error al intentar desactivar el comando!</b></font>\\r\\r Inténtalo de nuevo.'),
	('command.disablecommand.name', 'disablecommand,desactivarcomando'),
	('command.disablecommand.success', '<font color=\'#05a13c\'><b>El comando fue deshabilitado con éxito.</b></font>'),
	('command.disabled', '<font color=\'#a10505\'><b>¡Este comando está deshabilitado en la sala!</b></font>'),
	('command.disablefun.description', ':disablefun - Desactiva todos los comandos "Fun" en sala'),
	('command.disablefun.name', 'disablefun'),
	('command.disablewhisper.description', '<font color=\'#a10505\'><b>:personalwhisper</font>  -></b>  Activa o desactiva los susurros.'),
	('command.disablewhisper.disabled', 'Ahora los susurros están desactivados'),
	('command.disablewhisper.enabled', 'Ahora los susurros están activados'),
	('command.disablewhisper.name', 'personalwhisper,desactivarsusurros,disusurros'),
	('command.disconnect.description', '<font color=\'#a10505\'><b>:dc [Usuario]</font>  -></b>  Desconecta a un usuario del hotel.'),
	('command.disconnect.errortitle', 'Error en el comando'),
	('command.disconnect.himself', 'No puedes desconectarte a ti mismo.'),
	('command.disconnect.name', 'disconnect,dc,desconectar'),
	('command.disconnect.undisconnectable', 'No puedes desconectar a este usuario'),
	('command.ejectall.name', 'ejectall'),
	('command.empty.confirm', '<font color=\'#a10505\'><b>¡Cuidado!</b></font>\\r¿Estás seguro? Borrarará todos los furnis, bots y mascotas de tu inventario.\\r\\rSi estás seguro escribe   <b>:empty items</b>'),
	('command.empty.description', '<font color=\'#a10505\'><b>:empty items</font>  -></b>  Elimina todos los furnis de tu inventario.'),
	('command.empty.emptied', '<font color=\'#f52e54\'><b>¡Tu inventario ha sido eliminado!</b></font>'),
	('command.empty.message', '<font color=\'#05a13c\'><b>¡Tu inventario fue limpiado con éxito!</b></font>'),
	('command.empty.name', 'empty,borrarinventario'),
	('command.emptybots.name', 'emptybots'),
	('command.emptyfriends.description', '<font color=\'#a10505\'><b>:emptyfriends</font>  -></b>  Elimina toda tu lista de amigos.'),
	('command.emptyfriends.name', 'emptyfriends,borraramigos'),
	('command.emptypets.name', 'emptypets'),
	('command.enable.description', '<font color=\'#a10505\'><b>:enable [Id]</font>  -></b>  Ponerse un efecto.'),
	('command.enable.invalidid', '<font color=\'#a10505\'><b>Efecto no válido. ¡Intenta con otro!</b></font>'),
	('command.enable.name', 'enable,efecto,fx'),
	('command.enablecommand.description', '<font color=\'#a10505\'><b>:enablecommand [Id]</font>  -></b>  Habilita un comando que haya sido desactivado en la sala.'),
	('command.enablecommand.error', '<font color=\'#a10505\'><b>¡Ha ocurrido un error al intentar activar el comando!</b></font>\\r\\r Inténtalo de nuevo.'),
	('command.enablecommand.name', 'enablecommand,activarcomando'),
	('command.enablecommand.success', '<font color=\'#05a13c\'><b>El comando fue habilitado con éxito.</b></font>'),
	('command.error', 'Error en el comando'),
	('command.error.disabled', 'Este comando está actualmente deshabilitado'),
	('command.eventalert.alerttitle', '¡Nuevo evento en el Hotel!'),
	('command.eventalert.buttontitle', 'Ir a la sala del juego'),
	('command.eventalert.description', '<b>[Deshabilitado] <font color=\'#a10505\'>:eha [Mensaje]</font>  -></b>  Manda una alerta de evento.'),
	('command.eventalert.message', '¡Nuevo evento en Habbo!<br>\r\n¡Consigue <b><font color="#00d2ff">Copos de Nieve</font></b> u otro tipo de premios!<br><br> %username% te invita a participar en: <b> %message%</b>. <br><br>Si quieres jugar haz clic en el botón de abajo, ¡buena suerte!\r\n¡Te esperamos!'),
	('command.eventalert.name', 'eventalert,eha,evento'),
	('command.eventlog.description', ':events - Revisa los últimos 150 eventos abiertos.'),
	('command.eventlog.name', 'events'),
	('command.eventreward.already_got', 'Enviaste los copos de nieve correctamente.'),
	('command.eventreward.description', ':eventreward - Da un premio de un evento'),
	('command.eventreward.image', 'eventG'),
	('command.eventreward.name', 'eventreward,win,reward,rw'),
	('command.eventreward.seasonal_points', '4'),
	('command.eventreward.vip_points', '0'),
	('command.eventwon.description', ':eventwon/ganador - Envia una notificación global cuando gana un usuario'),
	('command.eventwon.image', 'eventG'),
	('command.eventwon.message', '¡%user% ganó el evento! ;)'),
	('command.eventwon.name', 'eventwon,ganador'),
	('command.executed', 'Comando ejecutado exitosamente'),
	('command.fastwalk.description', ':fw/fastwalk - Te permite moverte mucho más rápido por la sala'),
	('command.fastwalk.name', 'fw,fastwalk'),
	('command.finalevent.description', ':event - Realiza un evento final en la sala.'),
	('command.finalevent.name', 'event'),
	('command.flagme.description', ':flagme - Cambia tu nombre de usuario'),
	('command.flagme.name', 'flagme'),
	('command.flaguser.description', ':flaguser - Habilita el cambiar nombre a un usuario'),
	('command.flaguser.message\r\ncommand.flaguser.message', '%s has cambiado tu nombre exitosamente. \r\nPuede cambiar su nombre haciendo clic en usted mismo. Haz esto en un minuto o serás baneado.'),
	('command.flaguser.name', 'flaguser'),
	('command.floor.complete', 'La sala se ha modificado. Puedes volver a entrar en ella.'),
	('command.floor.invalid', 'El modelo que has creado no es válido'),
	('command.floor.size', '¡Este modelo es muy grande!'),
	('command.flooredit.description', '<font color=\'#a10505\'><b>:flooredit</font>  -></b>  Elimina baldosas con floor incluyendo las que poseen furnis.'),
	('command.flooredit.disabled', '¡Se ha reestablecido la configurción del editor floor!'),
	('command.flooredit.enabled', 'Ahora puedes eliminar cualquier parte del suelo de tu sala desde el editor de Floor.'),
	('command.flooredit.name', 'flooredit,editarsala'),
	('command.follow.description', '<font color=\'#a10505\'><b>:follow [Usuario]</font>  -></b>  Sigue a un usuario.'),
	('command.follow.disabled', '¡El usuario seleccionado tiene actualmente el follow desactivado!'),
	('command.follow.name', 'follow,seguir'),
	('command.follow.none', '¿A quién te gustarí­a seguir?'),
	('command.follow.online', '¡Ups! Este usuario no se encuentra actualmente conectado.\n'),
	('command.follow.playerhimself', '¡Lo sentimos! No te puedes dar follow a ti mismo'),
	('command.follow.room', '¡El usuario seleccionado no se encuentra en ninguna sala!'),
	('command.freeze.description', ':freeze - Congela a un usuario'),
	('command.freeze.name', 'freeze'),
	('command.furnifix.description', ':furnifix - Edita los furnis'),
	('command.givebadge.description', '<font color=\'#a10505\'><b>:givebadge / :badge</font></b>  ->  Enví­a una placa al usuario.'),
	('command.givebadge.fail', '¡Error a la hora de darle una placa al usuario %username% código: %badge%!'),
	('command.givebadge.name', 'givebadge,badge'),
	('command.givebadge.success', '¡Se dió con éxito al usuario %username% la placa: %badge%!'),
	('command.giverank.description', ':giverank - Dale rango al usuario que desees'),
	('command.giverank.name', 'giverank'),
	('command.giverank.received', 'Tu rango ha sido actualizado a %r, porfavor recarga el client'),
	('command.giverank.success', 'Le has dado rango correctamente'),
	('command.globalalert.description', 'Envia una alerta global de eventos'),
	('command.globalalert.name', 'globalalert'),
	('command.globalbubble.description', ':globalbubble [Texto] - Envia una alerta global a todo el hotel'),
	('command.globalbubble.image', 'Frank'),
	('command.globalbubble.name', 'globalbubble,globu'),
	('command.handitem.description', '<font color=\'#a10505\'><b>:handitem [Id]</font>  -></b>  Te permite llevar un item en la mano.'),
	('command.handitem.name', 'handitem,item,objeto'),
	('command.height.description', '<font color=\'#a10505\'><b>:bh [Altura]</font>  -></b>  Define la altura al colocar los furnis.'),
	('command.height.invalid', '¡Altura inválida! Debe ser entre 0-64 (Puedes usar decimales)'),
	('command.height.name', 'bh,setz'),
	('command.height.param', '%height%'),
	('command.height.set', 'La altura está definida en %height%'),
	('command.help.description', '<font color=\'#a10505\'><b>:ayuda</font>  -></b>  Solicita ayuda a un guí­a.'),
	('command.help.name', 'ayuda,help'),
	('command.hidewired.description', '<font color=\'#a10505\'><b>:hidewired</font>  -></b>  Oculta todos los wired encontrados en la sala.'),
	('command.hidewired.name', 'hidewired,ocultarwired,hiwi'),
	('command.hidewired.shown', '¡Los wireds ahora están visibles!'),
	('command.hotelalert.description', ':ha - Enví­a una alerta a todo el Hotel'),
	('command.hotelalert.name', 'ha'),
	('command.hotelalert.title', 'Mensaje del Equipo Staff'),
	('command.hotelalertlink.buttontitle', 'Quiero ir'),
	('command.hotelalertlink.description', ':hotelalertlink/hal - Enví­a una alerta con enlace a todo el Hotel'),
	('command.hotelalertlink.name', 'hotelalertlink,hal'),
	('command.hotelalertlink.title', '¡Alerta Staff - Habbo!'),
	('command.hug.description', '<font color=\'#a10505\'><b>:abrazar [Usuario]</font>  -></b>  Abraza a un usuario.'),
	('command.hug.himself', '¡No puedes abrazarte a ti mismo!'),
	('command.hug.name', 'hug,abrazar'),
	('command.idle.description', ':afk - Ausente'),
	('command.idle.name', 'afk'),
	('command.invisible.description', ':invisible - Volverte invisible para todos los usuarios'),
	('command.invisible.disabled', 'Has desactivado el modo invisible'),
	('command.invisible.enabled', 'Ya tienes el modo invisible activado.'),
	('command.invisible.name', 'invisible'),
	('command.invitation.name', 'invite'),
	('command.ipban.description', ':ipban - Banea la IP del usuario prohibiénddole el acceso al Hotel'),
	('command.ipban.name', 'ipban'),
	('command.kick.description', ':kick - Kickea a un usuario de la sala.'),
	('command.kick.error', 'Error en el comando'),
	('command.kick.name', 'kick'),
	('command.kick.successmessage', '¡Fuiste kickeado de la sala!'),
	('command.kick.successtitle', '¡Puff!'),
	('command.kick.userkicked', '¡%username% fue kickeado de la sala!'),
	('command.kiss.description', '<font color=\'#a10505\'><b>:besar [Usuario]</font>  -></b>  Besa a un usuario.'),
	('command.kiss.himself', '¡No puedes besarte a ti mismo!'),
	('command.kiss.name', 'besar,kiss'),
	('command.kiss.word', 'besó a'),
	('command.lava.description', ':lava - Echa lava a un usuario'),
	('command.lava.name', 'lava'),
	('command.lay.description', '<font color=\'#a10505\'><b>:lay</font>  -></b>  Tumbarse en el suelo.'),
	('command.lay.name', 'lay'),
	('command.link.description', ':link - '),
	('command.link.message', ''),
	('command.link.name', 'link'),
	('command.listen.description', ':listen - Permite leer los mensajes de un usuario '),
	('command.listen.listening', '¡estás siendo leí­do por %username%!'),
	('command.listen.message', '%username%: %message%'),
	('command.listen.name', 'listen'),
	('command.listen.notfound', '¡No puedes leer los mensajes de ese usuario!'),
	('command.look.description', '<font color=\'#a10505\'><b>:look [Número]</font>  -></b>  Cambia tu look más rápido.'),
	('command.look.name', 'look,ropa,guardado'),
	('command.machineban.description', ':machineban - Prohibe acceder al computador de un usuario'),
	('command.machineban.name', 'machineban'),
	('command.maintenance.name', 'maintenance'),
	('command.makesay.description', ':say - Le hace decir al usuario seleccionado el mensaje seleccionado'),
	('command.makesay.name', 'say'),
	('command.married.description', '<font color=\'#a10505\'><b>:casarse [Usuario]</font>  -></b>  Casarse con un usuario.'),
	('command.married.name', 'married,boda,casarse'),
	('command.massbadge.description', ':massbadge - Da una placa a todos los usuarios conectados'),
	('command.massbadge.name', 'massbadge'),
	('command.masscoins.name', 'masscoins,masscredits'),
	('command.massdiamonds.description', ':massdiamonds - Envía la cantidad de diamantes a todos los usuarios conectados'),
	('command.massduckets.description', ':massduckets - Da duckets a todos los usuarios online'),
	('command.massduckets.name', 'massduckets'),
	('command.massfreeze.description', ':massfreeze - Freezea a todos los usuarios de la sala'),
	('command.massfreeze.frozen', 'Has sido freezeado'),
	('command.massfreeze.name', 'massfreeze'),
	('command.massfreeze.unfrozen', 'Has sido desfreezeado'),
	('command.massmotd.description', ':massmotd/masssa - Enviar una alerta tipo MOTD (tipo sa)'),
	('command.massmotd.name', 'massmotd,masssa'),
	('command.masspoints.name', 'massdiamonds,globaldiamonds'),
	('command.massrare.name', 'massrare'),
	('command.massteleport.description', ':massteleport - Atrae a todos los usuarios de la sala a tu posición'),
	('command.massteleport.name', 'massteleport'),
	('command.maxfloor.description', '<font color=\'#a10505\'><b>:maxfloor</b></font>  <b>-></b>  Agranda la sala con el lí­mite de baldosas permitido.'),
	('command.maxfloor.name', 'maxfloor'),
	('command.mentions.description', '<font color=\'#a10505\'><b>:menciones</font>  -></b>  Revisa tu historial de menciones.'),
	('command.mentions.name', 'mentions,menciones'),
	('command.mentionsettings.description', ':personalmentions - Escoje quien te puede o no mencionar'),
	('command.mentionsettings.name', 'personalmentions'),
	('command.mentionsettings.param', '%type% (todos, amigos, nadie)'),
	('command.mimic.description', '<font color=\'#a10505\'><b>:copy [Usuario]</font>  -></b>  Copia el look de un usuario.'),
	('command.mimic.name', 'mimic,copy,mc'),
	('command.moonwalk.description', '<font color=\'#a10505\'><b>:moonwalk</font>  -></b>  ¡Camina de reversa!'),
	('command.moonwalk.disabled', '¡El comando moonwalk ahora está desactivado!'),
	('command.moonwalk.enabled', '¡El comando moonwalk ahora está activado!'),
	('command.moonwalk.name', 'moonwalk'),
	('command.murder.description', '<font color=\'#a10505\'><b>:matar [Usuario]</font>  -></b>  Mata a un usuario.'),
	('command.murder.name', 'kill,matar'),
	('command.mute.description', ':shutup/muteuser - Mutea a un usuario'),
	('command.mute.muted', 'Has sido muteado, podrás volver a hablar en %timeleft% minutos.'),
	('command.mute.name', 'shutup,muteuser,mute,mmute,mutear'),
	('command.mute.negative', '¡Utiliza solo números positivos!'),
	('command.mute.nomore', '¡Solo puedes mutear a alguien máximo 600 segundos, así­ que la cantidad será cambiada a esa!'),
	('command.nalgada.description', '<font color=\'#a10505\'><b>:nalgada [Usuario]</font>  -></b>  Dale una nalgada a un usuario.'),
	('command.nalgada.name', 'nalgada,nalguear'),
	('command.namecolour.description', '<font color=\'#a10505\'><b>:colorname [Color]</font>  -></b>  Cambia el color de tu nombre.'),
	('command.namecolour.name', 'namecolour,color,colorname'),
	('command.namecolour.param', '%color%'),
	('command.noface.description', ':noface - Oculta tu cara'),
	('command.noface.name', 'noface'),
	('command.notaround', 'xd'),
	('command.notification.description', ':notification/nt <imagen> <mensaje>- Enví­a una notificación global'),
	('command.notification.name', 'notification,nt'),
	('command.nuke.description', '<font color=\'#a10505\'><b>:explotar [Usuario]</font>  -></b>  Explota a un usuario.'),
	('command.nuke.name', 'nuke,explotar'),
	('command.parameter.message', '%message%'),
	('command.parameter.superban', '%usuario%'),
	('command.parameter.unban', '%usuario%'),
	('command.personalstaff.description', ':personalstaff/staff - Activa o desactiva el efecto staff.'),
	('command.personalstaff.disabled', 'Se ha desactivado el efecto staff'),
	('command.personalstaff.enabled', 'Se ha activado el efecto staff'),
	('command.personalstaff.name', 'personalstaff,staff'),
	('command.pets.toomany', '¡Ya hay demasiadas mascotas en esta sala!'),
	('command.pickall.description', '<font color=\'#a10505\'><b>:pickall</font>  -></b>  Recoge todos los furnis de la sala.'),
	('command.pickall.name', 'pickall'),
	('command.playerinfo.description', ':ui - Muestra la información del usuario'),
	('command.playerinfo.name', 'playerinfo,userinfo,ui'),
	('command.points.currency.diamonds', 'Cometas'),
	('command.points.currency.duckets', 'Asteroides'),
	('command.points.currency.seasonal', 'Copos de Nieve'),
	('command.points.description', ':givemoneda - <Usuario> <Cantidad> <Tipo(seasonal/duckets)> Da un tipo de moneda (Si no se escribe ningún tipo dará diamantes)'),
	('command.points.name', 'dar'),
	('command.points.successmessage', 'Has recibido %amount% %type%'),
	('command.points.successtitle', '¡Nueva recompensa!'),
	('command.position.description', ':position/coords - Coordinadas de tu usuario'),
	('command.position.name', 'position,coords'),
	('command.prefix.description', '<font color=\'#a10505\'><b>:prefijo [Mensaje]</font>  -></b>  Agrega o cambia tu prefijo.'),
	('command.prefix.name', 'prefix,prefijo,pre'),
	('command.preguntar.description', ':preguntar - Bubble global'),
	('command.preguntar.name', 'preguntar'),
	('command.puke.description', '<font color=\'#a10505\'><b>:vomitar [Usuario]</font>  -></b>  Vomitar sobre un usuario.'),
	('command.puke.name', 'vomitar,puke'),
	('command.pull.description', '<font color=\'#a10505\'><b>:pull [Usuario]</font>  -></b>  Atrae a un usuario.'),
	('command.pull.message', '<font color=\'#1565c0\'><b>He traí­do a %playername% hacia mi.</b></font>'),
	('command.pull.name', 'pull'),
	('command.pull.playerhimself', '¡No puedes hacerte pull a ti mismo!'),
	('command.pull.usernotvip', '¡Debes ser VIP para usar este comando!'),
	('command.punch.description', '<font color=\'#a10505\'><b>:golpear [Usuario]</font>  -></b>  Golpea a un usuario.'),
	('command.punch.himself', 'No te puedes pegar a ti mismo'),
	('command.punch.name', 'punch,golpear,pegar,patear'),
	('command.punch.none', '¿A quién te gustarí­a golpear?'),
	('command.punch.word', 'pegó a'),
	('command.push.description', '<font color=\'#a10505\'><b>:push [Usuario]</font>  -></b>  Empuja a un usuario.'),
	('command.push.invalidusername', '¡Usuario inválido!'),
	('command.push.message', '<font color=\'#c62828\'><b>He empujado a %playername%</b></font>'),
	('command.push.name', 'push'),
	('command.push.outdoor', '¡No puedes expulsar a un usuario de la sala!'),
	('command.push.playerhimself', 'No puedes hacer push a ti mismo.'),
	('command.push.usernotvip', '¡Debes ser VIP para usar este comando!'),
	('command.quickpoll.description', ':quickpoll - Envia una encuesta rápida en la sala'),
	('command.quickpoll.name', 'quickpoll'),
	('command.redeemcredits.description', '<font color=\'#a10505\'><b>:canjearcreditos</font>  -></b>  Canjea todos los créditos de tu inventario.'),
	('command.redeemcredits.name', 'redeemcredits,canjearcreditos'),
	('command.reload.bans', 'Los baneos fueron resfrescados con éxito'),
	('command.reload.bundles', 'Se han recargado los bundles de salas.'),
	('command.reload.catalog', 'El catálogo fue refrescado con éxito'),
	('command.reload.description', ':refresh - Usa :refresh list para ver la lista completa de comandos'),
	('command.reload.items', 'Las definiciones de los furnis se refrescaron con éxito'),
	('command.reload.locale', 'La configuración local se refrescó con éxito.'),
	('command.reload.name', 'refresh,re'),
	('command.reload.navigation', 'El navegador se recargó con éxito.'),
	('command.reload.news', 'Los artí­culos de noticia fueron cambiados con éxito.'),
	('command.reload.permissions', 'Los permisos fueron refrescados con éxito.'),
	('command.reload.rooms', 'La sala se recargó con éxito!'),
	('command.reminder.description', ':eventosala - Crea un evento de sala'),
	('command.reminder.name', 'eventosala'),
	('command.removebadge.description', ':removebadge - (usuario) (código): Elimina una placa a un usuario'),
	('command.removebadge.name', 'removebadge,take'),
	('command.removebadge.success', 'Has eliminado exitosamente la placa de su inventario.'),
	('command.resetdicecount.description', ':pl - Reinicia tu contador de dados a 0.'),
	('command.resetdicecount.name', 'resetdicecount,rdc,pl'),
	('command.restart.description', ':restart - Reinicia el servidor'),
	('command.restart.name', 'restart'),
	('command.reward.description', ':reward - '),
	('command.reward.name', 'reward'),
	('command.reward.none', 'No puedes darte tu mismo reward'),
	('command.rob.description', '<font color=\'#a10505\'><b>:robar [Usuario]</font>  -></b>  Intenta robar a un usuario.'),
	('command.rob.failed', '<font color=\'#a10505\'><b>¡ %s le intentó robar a %b pero no lo ha conseguido</b></font>'),
	('command.rob.name', 'rob,robar'),
	('command.rob.success', '<font color=\'#05a13c\'><b> %s ha robado con éxito a %b</b></font>'),
	('command.roll.description', ':roll - '),
	('command.roll.name', 'roll'),
	('command.roomaction.description', ':roomaction - list para más información'),
	('command.roomaction.name', 'roomaction'),
	('command.roomalert.description', ':roomalert - Enví­a una alerta a todos los usuarios de la sala'),
	('command.roomalert.name', 'roomalert'),
	('command.roomkick.description', ':roomkick - (mensaje): Expulsa a todos los usuarios de la sala'),
	('command.roomkick.name', 'roomkick,rkick'),
	('command.roomkick.title', 'Alerta'),
	('command.roommute.description', ':roommute - Silencia a todos los usuarios de la sala'),
	('command.roommute.name', 'roommute'),
	('command.roomnotification.description', ':rn - Manda una notificación a toda la sala'),
	('command.roomnotification.name', 'roomnotification,rn'),
	('command.roomoption.description', ':roomoption - Opciones de sala <shake/rotate/disco>'),
	('command.roomoption.name', 'roomoption'),
	('command.roomvideo.description', ':roomvideo - Pon un video de youtube'),
	('command.roomvideo.name', 'roomvideo'),
	('command.rps.description', ':rps - Reta a un jugador a Piedra, Papel o Tijera.'),
	('command.rps.name', 'rps'),
	('command.screenshot.description', ':screenshot - Sacas una captura de pantalla a la sala en la que estás'),
	('command.search.description', '<font color=\'#a10505\'><b>:search</b></font>  <b>-></b>  Activa el modo búsqueda de furnis.'),
	('command.search.name', 'search,buscar'),
	('command.searchfurni.name', 'searchfurni'),
	('command.seasonal.name', 'seasonal'),
	('command.secuestrar.description', '<font color=\'#a10505\'><b>:secuestrar [Usuario]</font>  -></b>  Secuestra a un usuario.'),
	('command.secuestrar.name', 'secuestrar'),
	('command.sellroom.description', '<font color=\'#a10505\'><b>:vendersala [Cantidad]</font>  -></b>  Vende una sala.'),
	('command.sellroom.name', 'vendersala,sellroom'),
	('command.setbet.name', 'setbet'),
	('command.setidletimer.description', ':SetIdleTimer [Numero]'),
	('command.setidletimer.name', 'setidletimer'),
	('command.setmax.description', '<font color=\'#a10505\'><b>:setmax [Cantidad]</font>  -></b>  Cambia la cantidad máxima de usuarios en la sala.'),
	('command.setmax.done', '¡La cantidad máxima se cambió a %i!'),
	('command.setmax.invalid', '¡Debes especificar un número válido! Inténtalo de nuevo.'),
	('command.setmax.name', 'setmax'),
	('command.setmax.toomany', 'El número es muy grande, debe ser menor que %i.'),
	('command.setspeed.description', '<font color=\'#a10505\'><b>:setspeed [Cantidad]</font>  -></b>  Establece la velocidad de los rollers en una sala.'),
	('command.setspeed.invalid', '¡Debes utilizar solo números!'),
	('command.setspeed.name', 'setspeed'),
	('command.setspeed.none', '¡Para cambiar la velocidad de los rollers escribe :setspeed [Cantidad]!'),
	('command.setspeed.set', '¡La velocidad de los rollers se establecio en  %s!'),
	('command.setz.description', ':setz - Coloca un furni con una altura definida (entre 0 & 10)'),
	('command.setz.name', 'setz'),
	('command.sex.description', ':sexo - Coge con algun user'),
	('command.sex.name', 'sexo'),
	('command.shutdown.name', 'shutdown'),
	('command.sing.description', '<font color=\'#a10505\'><b>:cantar</font>  -></b>  Canta un temazo.'),
	('command.sing.name', 'cantar'),
	('command.sit.description', '<font color=\'#a10505\'><b>:sit</font>  -></b>  Siéntate en el piso.'),
	('command.sit.name', 'sit,sentarse'),
	('command.smoke.description', '<font color=\'#a10505\'><b>:fumar</font>  -></b>  Prende un porrito.'),
	('command.smoke.name', 'smoke,fumar'),
	('command.staffalert.description', ':sa - Manda una alerta a todos los staffs conectados'),
	('command.staffalert.name', 'sa'),
	('command.staffbubble.description', ':c - Enví­a una burbuja a todo el equipo administrativo.'),
	('command.staffbubble.image', 'NotStaff'),
	('command.staffbubble.name', 'c'),
	('command.staffinfo.description', ':staffinfo - Te muestra los staffs conectados y su sala'),
	('command.staffinfo.name', 'staffinfo'),
	('command.successful', 'Comando ejecutado con éxito'),
	('command.summon.description', ':summon - Extrae al usuario seleccionado a tu sala'),
	('command.summon.name', 'summon,get'),
	('command.summon.summoned', 'Un miembro del Equipo Staff te ha traí­do a su sala'),
	('command.superban.description', ':superban - Banea indefinidamente al usuario seleccionado'),
	('command.superban.name', 'superban'),
	('command.superpull.description', '<font color=\'#a10505\'><b>:spull</font>  -></b>  Atrae a un usuario lejano.'),
	('command.superpull.name', 'superpull,spull'),
	('command.superpush.description', ':SPush [Usuario] - Empujar con mas fuerza a un usuario.'),
	('command.superpush.name', 'spush'),
	('command.superwired.description', '<font color=\'#a10505\'><b>:superwired</font>  -></b>  Muestra la lista de comandos superwired.'),
	('command.superwired.name', 'superwired'),
	('command.teleport.description', '<font color=\'#a10505\'><b>:teleport</font>  -></b>  Activa o desactiva el teleport en la sala.'),
	('command.teleport.disabled', '¡Has desactivado el teleport!'),
	('command.teleport.enabled', '¡Has activado el teleport!'),
	('command.teleport.name', 'teleport,tele'),
	('command.togglediagonal.description', '<font color=\'#a10505\'><b>:roomdiagonal</font>  -></b>  Activa o desactiva las diagonales en la sala.'),
	('command.togglediagonal.name', 'roomdiagonal'),
	('command.toggleevents.description', ':personalalerts - Activa o desactiva las alertas de eventos'),
	('command.toggleevents.name', 'personalalerts'),
	('command.togglefriends.description', ':personalfriends - Escoje si te pueden o no mandar peticiones de amistad'),
	('command.togglefriends.name', 'personalfriends'),
	('command.toggleshoot.description', ':toggleshoot - Permite disparar el balón 6 casillas hacia delante en tu sala'),
	('command.toggleshoot.disabled', '¡El disparo de 6 casillas esta deshabilitado!'),
	('command.toggleshoot.enabled', '¡El disparo de 6 casillas esta habilitado!'),
	('command.toggleshoot.name', 'toggleshoot,roomshoot,ball,balon'),
	('command.tradeban.description', ':tradeban - Bloquea los tradeos a un usuario.'),
	('command.tradeban.name', 'tradeban'),
	('command.transform.description', '<font color=\'#a10505\'><b>:transform [Animal]</font>  -></b>  Transformarse en un animal.'),
	('command.transform.name', 'transform'),
	('command.unban.description', ':unban - Desbanea al usuario seleccionado'),
	('command.unban.name', 'unban'),
	('command.unban.notbanned', '¡Oops! Quizás este usuario no está baneado o está baneado de machineban'),
	('command.unban.success', '¡Has desbaneado al usuario %s exitosamente!'),
	('command.unload.description', '<font color=\'#a10505\'><b>:unload</font>  -></b>  Recarga la sala actual.'),
	('command.unload.name', 'reload,recargar'),
	('command.unload.roomReloaded', 'La sala ha sido recargada!'),
	('command.unmute.description', ':unmute - Desmutea al usuario seleccionado'),
	('command.unmute.name', 'unmute'),
	('command.unmute.unmuted', 'Has sido desmuteado, ya puedes volver a hablar!'),
	('command.user.mustbe.offline', 'El usuario debe estar desconectado'),
	('command.viewinventory.description', ':viewinventory - Cámbiate el inventario con el de otro usuario.'),
	('command.viewinventory.error', '¡Oops! Puede que el usuario no exista,  si existe, contacta con un staff'),
	('command.viewinventory.name', 'viewinventory'),
	('command.viewinventory.reset', '¡Volviste a tu inventario exitosamente!'),
	('command.viewinventory.success', '¡Has cambiado tu inventario exitosamente! Si quieres regresar a tu inventario escribe :viewinventory'),
	('command.vip', '¡Debes ser VIP para poder usar este comando!'),
	('command.voucher.name', 'voucher'),
	('command.warp.description', '<font color=\'#a10505\'><b>:warp [Usuario]</font>  -></b>  Teletransporta a un usuario a tu posición.'),
	('command.warp.name', 'warp'),
	('command.welcome.description', '<font color=\'#a10505\'><b>:welcome [Usuario]</b></font>  <b>-></b>  Enví­a una bienvenida a un usuario nuevo.'),
	('command.welcome.name', 'welcome,bienvenida,bienvenido'),
	('command.whisperalert.description', ':wha - manda una alerta'),
	('command.whisperalert.name', 'wha'),
	('command.window.description', ':ea - Selecciona el tipo de alerta que quieres recibir en eventos.'),
	('command.window.name', 'ea,swap'),
	('drink.bubble', 'bubble'),
	('drink.cappuccino', 'cappuccino'),
	('drink.coffee', 'coffee'),
	('drink.coke', 'coke'),
	('drink.cola', 'cola'),
	('drink.espresso', 'espresso'),
	('drink.iced_coffee', 'iced coffee'),
	('drink.java', 'java'),
	('drink.love_potion', 'potion'),
	('drink.milk', 'milk'),
	('drink.mocha', 'mocha'),
	('drink.pink_champagne', 'pink'),
	('drink.tea', 'tea'),
	('game.figure.invalid', '¡Look inválido!'),
	('game.kicked', '¡Fue expulsado de la sala!'),
	('game.logging.whisper', '<Susurrando a %username%>'),
	('game.message.blocked', 'Tu mensaje fue bloqueado porque dijiste \\"%s\\"'),
	('game.received.achievementPoints', '¡Has recibido %points% puntos de recompensa!'),
	('game.received.credits', '¡Has recibido {$} créditos!'),
	('game.received.credits.title', '¡Créditos recibidos!'),
	('game.room.full', '¡Esta Sala está llena!'),
	('game.room.jukeboxExists', 'Solo puedes tener una Jukebox por sala'),
	('game.trade.error', 'Hubo un error en el cambio, todos los furnis fueron devueltos a tu inventario.'),
	('game.whispering', 'Susurrando a %username%: %message%'),
	('giverank_command', 'giverank'),
	('Grrrrrr!', 'flaguser'),
	('help.ticket.pending.message', '¡Hey! Espera a que tu ticket de ayuda anterior sea resuelto para que enví­es uno nuevo.'),
	('help.ticket.pending.title', '¡Tu otro ticket no ha sido resuelto!'),
	('logchat.message', '%s ejecutó el comando: "%b" a las %c en la Sala: %d; [ID: %f]'),
	('logchat.minrank', '3'),
	('mention.all', 'todos'),
	('mention.all.set', '¡Ahora te puede mencionar todo el mundo!'),
	('mention.disabled', '¡Este usuario no permite que le mencionen!'),
	('mention.friends', 'amigos'),
	('mention.friends.set', '¡Ahora te pueden mencionar solo tus amigos!'),
	('mention.message', 'El usuario %s te ha mencionado en una sala (%b), pulsa aquí­ para ir a la sala.'),
	('mention.none', 'nadie'),
	('mention.none.set', '¡Ahora no te puede mencionar nadie!'),
	('mention.notexist', 'El usuario %s no existe o está desconectado.'),
	('mention.notfriend', '¡Necesitas ser amigo de este usuario para mencionarlo!'),
	('mention.success', 'Has mencionado a %s satisfactoriamente.'),
	('message.staffalert', '(SA) Alerta Staff:'),
	('mod.ban.nopermission', '¡No tienes permiso para banear a este usuario!'),
	('navigator.staff.picks.added.message', 'La habitación ha sido agregada a las salas de Selección Staff'),
	('navigator.staff.picks.added.title', 'Sala elegida para la Selección Staff'),
	('navigator.staff.picks.removed.message', 'La sala ha sido eliminada de la Selección Staff.'),
	('navigator.staff.picks.removed.title', 'Sala eliminada de Selección Staff'),
	('notification.eventwin', '¡%username% ha ganado un evento!'),
	('notification.eventwin.disabled', '¡Ahora tienes las notificaciones de los ganadores de concursos o juegos desactivadas!'),
	('notification.eventwin.enabled', '¡Ahora tienes las notificaciones de los ganadores de concursos o juegos activadas!'),
	('notification.eventwin.image', '¡%username% ha ganado un evento!'),
	('player.online', '¡%username% se ha conectado!'),
	('player.online.image', 'ru'),
	('position.command.name', 'position,coords'),
	('reward.double.points', 'Bienvenido a Habbo. Estamos en fase alpha, si encuentras algún bug, reportalo con el staff.'),
	('seasonal.name', 'Copos de Nieve'),
	('staff.chat.filter', 'El usuario %s se ha saltado el filtro en %b: [%c]'),
	('vip.points.name', 'cometas'),
	('whisper.disabled', '¡Oops! ¡El usuario al que estás intentando susurrar tiene los susurros desactivados!'),
	('whisper.minrank.force', '3'),
	('wired.custom.fastwalk.disabled', '¡Ahora tienes el fastwalk desactivado!'),
	('wired.custom.fastwalk.enabled', '¡Ahora tienes el fastwalk activado!'),
	('wired.reward.seasonal', '¡Has recibido %s Copos de Nieve');
/*!40000 ALTER TABLE `server_locale` ENABLE KEYS */;

-- Dumping structure for table hylib.server_permissions
DROP TABLE IF EXISTS `server_permissions`;
CREATE TABLE IF NOT EXISTS `server_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `flood_bypass` enum('1','0') NOT NULL DEFAULT '0',
  `flood_time` int(11) NOT NULL DEFAULT 30,
  `disconnectable` enum('1','0') NOT NULL DEFAULT '1',
  `mod_tool` enum('1','0') NOT NULL DEFAULT '0',
  `alfa_tool` enum('1','0') NOT NULL DEFAULT '0',
  `bannable` enum('1','0') NOT NULL DEFAULT '0',
  `room_kickable` enum('1','0') NOT NULL DEFAULT '1',
  `room_full_control` enum('1','0') NOT NULL DEFAULT '1',
  `room_mute_bypass` enum('1','0') NOT NULL DEFAULT '1',
  `room_filter_bypass` enum('1','0') NOT NULL DEFAULT '0',
  `room_ignorable` enum('1','0') NOT NULL DEFAULT '1',
  `room_enter_full` enum('1','0') NOT NULL DEFAULT '1',
  `cmd_search` enum('1','0') NOT NULL DEFAULT '1',
  `room_enter_locked` enum('1','0') NOT NULL DEFAULT '1',
  `room_staff_pick` enum('1','0') NOT NULL DEFAULT '1',
  `room_see_whispers` enum('1','0') NOT NULL DEFAULT '1',
  `messenger_staff_chat` enum('1','0') NOT NULL DEFAULT '0',
  `messenger_mod_chat` enum('1','0') NOT NULL DEFAULT '0',
  `messenger_log_chat` enum('1','0') NOT NULL DEFAULT '0',
  `messenger_alfa_chat` enum('1','0') NOT NULL DEFAULT '0',
  `messenger_max_friends` int(11) NOT NULL DEFAULT 1100,
  `about_detailed` enum('1','0') NOT NULL DEFAULT '0',
  `about_stats` enum('1','0') NOT NULL DEFAULT '0',
  `login_notif` enum('1','0') NOT NULL DEFAULT '0',
  `about_command` enum('1','0') DEFAULT '0',
  `globalbubble_command` enum('1','0') DEFAULT '0',
  `globalalert_command` enum('1','0') DEFAULT '0',
  `freeze_command` enum('1','0') DEFAULT '0',
  `alertnoti_command` enum('1','0') DEFAULT '0',
  `preguntar_command` enum('1','0') DEFAULT '0',
  `commands_command` enum('1','0') DEFAULT '1',
  `restart_command` enum('1','0') DEFAULT '0',
  `teleport_command` enum('2','1','0') DEFAULT '0',
  `sellroom_command` enum('2','1','0') DEFAULT '2',
  `pickall_command` enum('2','1','0') DEFAULT '0',
  `massmotd_command` enum('1','0') DEFAULT '0',
  `hotelalert_command` enum('1','0') DEFAULT '0',
  `invisible_command` enum('1','0') DEFAULT '0',
  `disablefun_command` enum('1','0') DEFAULT '0',
  `push_command` enum('2','1','0') DEFAULT '0',
  `moonwalk_command` enum('1','0') DEFAULT '0',
  `enable_command` enum('1','0') DEFAULT '0',
  `ban_command` enum('1','0') DEFAULT '0',
  `empty_command` enum('1','0') DEFAULT '0',
  `unload_command` enum('2','1','0') DEFAULT '0',
  `roomvideo_command` enum('2','1','0') DEFAULT '2',
  `ipban_command` enum('1','0') DEFAULT '0',
  `givebadge_command` enum('1','0') DEFAULT '0',
  `reload_command` enum('2','1','0') DEFAULT '0',
  `transform_command` enum('1','0') DEFAULT '0',
  `coins_command` enum('1','0') DEFAULT '0',
  `pull_command` enum('2','1','0') DEFAULT '0',
  `sit_command` enum('1','0') DEFAULT '0',
  `buyroom_command` enum('1','0') DEFAULT '1',
  `prefix_command` enum('1','0') DEFAULT '1',
  `alert_command` enum('1','0') DEFAULT '0',
  `points_command` enum('1','0') DEFAULT '0',
  `kick_command` enum('1','0') DEFAULT '0',
  `mimic_command` enum('1','0') DEFAULT '0',
  `machineban_command` enum('1','0') DEFAULT '0',
  `massbadge_command` enum('1','0') DEFAULT '0',
  `masscoins_command` enum('1','0') DEFAULT '0',
  `masspoints_command` enum('1','0') DEFAULT '0',
  `massduckets_command` enum('1','0') DEFAULT '0',
  `redeemcredits_command` enum('1','0') DEFAULT '0',
  `playerinfo_command` enum('1','0') DEFAULT '0',
  `roommute_command` enum('1','0') DEFAULT '0',
  `roomunmute_command` enum('1','0') DEFAULT '0',
  `handitem_command` enum('1','0') DEFAULT '0',
  `setmax_command` enum('2','1','0') DEFAULT '0',
  `removebadge_command` enum('1','0') DEFAULT '0',
  `deletegroup_command` enum('1','0') DEFAULT '0',
  `shutdown_command` enum('1','0') DEFAULT '0',
  `togglediagonal_command` enum('2','1','0') DEFAULT '0',
  `roll_command` enum('1','0') DEFAULT '0',
  `hotelalertlink_command` enum('1','0') DEFAULT '0',
  `summon_command` enum('1','0') DEFAULT '0',
  `togglefriends_command` enum('1','0') DEFAULT '0',
  `roomaction_command` enum('1','0') DEFAULT '0',
  `enablecommand_command` enum('1','0') DEFAULT '0',
  `disablecommand_command` enum('1','0') DEFAULT '0',
  `mute_command` enum('1','0') DEFAULT '0',
  `unmute_command` enum('1','0') DEFAULT '0',
  `punch_command` enum('2','1','0') DEFAULT '0',
  `bundle_command` enum('1','0') DEFAULT '0',
  `notification_command` enum('1','0') DEFAULT '0',
  `maintenance_command` enum('1','0') DEFAULT '0',
  `eventalert_command` enum('1','0') DEFAULT '0',
  `quickpoll_command` enum('1','0') DEFAULT '0',
  `ejectall_command` enum('1','0') DEFAULT '0',
  `fastwalk_command` enum('1','0') DEFAULT '0',
  `roomoption_command` enum('1','0') DEFAULT '0',
  `ignoreevents_command` enum('1','0') DEFAULT '0',
  `hidewired_command` enum('1','0') DEFAULT '0',
  `eventreward_command` enum('1','0') DEFAULT '0',
  `emptyfriends_command` enum('1','0') DEFAULT '0',
  `reward_command` enum('1','0') DEFAULT '0',
  `massteleport_command` enum('1','0') DEFAULT '0',
  `massfreeze_command` enum('1','0') DEFAULT '0',
  `eventwon_command` enum('1','0') DEFAULT '0',
  `height_command` enum('1','0') DEFAULT '0',
  `listen_command` enum('1','0') DEFAULT '0',
  `cloneroom_command` enum('1','0') DEFAULT '0',
  `follow_command` enum('1','0') DEFAULT '0',
  `rob_command` enum('1','0') DEFAULT '0',
  `kiss_command` enum('1','0') DEFAULT '0',
  `sex_command` enum('1','0') DEFAULT '1',
  `nalgada_command` enum('1','0') DEFAULT '1',
  `murder_command` enum('1','0') DEFAULT '1',
  `sing_command` enum('1','0') DEFAULT '1',
  `secuestrar_command` enum('1','0') DEFAULT '1',
  `nuke_command` enum('1','0') DEFAULT '1',
  `staffon_command` enum('1','0') DEFAULT '1',
  `hug_command` enum('1','0') DEFAULT '0',
  `lay_command` enum('1','0') DEFAULT '0',
  `mass_seasonal_command` enum('1','0') DEFAULT '0',
  `staffalert_command` enum('1','0') DEFAULT '0',
  `roomalert_command` enum('1','0') DEFAULT '0',
  `roomkick_command` enum('1','0') DEFAULT '0',
  `makesay_command` enum('1','0') DEFAULT '0',
  `disconnect_command` enum('1','0') DEFAULT '0',
  `superpull_command` enum('1','0') DEFAULT '0',
  `staffinfo_command` enum('1','0') DEFAULT '0',
  `unban_command` enum('1','0') DEFAULT '0',
  `roomnotification_command` enum('1','0') DEFAULT '0',
  `namecolour_command` enum('1','0') DEFAULT '0',
  `disablewhisper_command` enum('1','0') DEFAULT '0',
  `superban_command` enum('1','0') DEFAULT '0',
  `setspeed_command` enum('1','0') DEFAULT '0',
  `viewinventory_command` enum('1','0') DEFAULT '0',
  `mentionsettings_command` enum('1','0') DEFAULT '0',
  `toggleshoot_command` enum('1','0') DEFAULT '0',
  `personalstaff_command` enum('1','0') DEFAULT '0',
  `seasonal_command` enum('1','0') DEFAULT '0',
  `setbet_command` enum('1','0') DEFAULT '0',
  `warp_command` enum('2','1','0') DEFAULT '0',
  `changelog_command` enum('1','0') DEFAULT '0',
  `staffbubble_command` enum('1','0') DEFAULT '0',
  `tradeban_command` enum('1','0') DEFAULT '0',
  `resetdicecount_command` enum('1','0') DEFAULT '0',
  `betsystem_command` enum('1','0') DEFAULT '0',
  `rps_command` enum('1','0') DEFAULT '0',
  `bank_command` enum('1','0') DEFAULT '0',
  `eventlog_command` enum('1','0') DEFAULT '0',
  `finalevent_command` enum('1','0') DEFAULT '0',
  `selectwindow_command` enum('1','0') DEFAULT '0',
  `voucher_command` enum('1','0') DEFAULT '0',
  `puke_command` enum('1','0') DEFAULT '0',
  `help_command` enum('1','0') DEFAULT '0',
  `link_command` enum('1','0') DEFAULT '0',
  `idle_command` enum('1','0') DEFAULT '0',
  `control_command` enum('1','0') DEFAULT '0',
  `position_command` enum('1','0') DEFAULT '0',
  `build_command` enum('1','0') DEFAULT '0',
  `massrare_command` enum('1','0') DEFAULT '0',
  `invitation_command` enum('1','0') DEFAULT '0',
  `invite_command` enum('1','0') DEFAULT '0',
  `setz_command` enum('1','0') DEFAULT '0',
  `vipbundle_command` enum('1','0') DEFAULT '0',
  `flaguser_command` enum('1','0') DEFAULT '0',
  `override_command` enum('1','0') DEFAULT '0',
  `furnifix_command` enum('1','0') DEFAULT '0',
  `closedice_command` enum('1','0') DEFAULT '1',
  `smoke_command` enum('1','0') DEFAULT '1',
  `married_command` enum('1','0') DEFAULT '1',
  `mentions_command` enum('1','0') DEFAULT '1',
  `look_command` enum('1','0') DEFAULT '1',
  `giverank_command` enum('1','0') DEFAULT '0',
  `wha_command` enum('1','0') DEFAULT '0',
  `flagme_command` enum('1','0') DEFAULT '0',
  `autofloor_command` enum('2','1','0') DEFAULT '2',
  `maxfloor_command` enum('2','1','0') DEFAULT '2',
  `setidletimer_command` enum('2','1','0') DEFAULT '1',
  `flooredit_command` enum('0','1','2') DEFAULT '2',
  `superwired_command` enum('0','1') DEFAULT '1',
  `superpush_command` enum('0','1') DEFAULT '0',
  `welcome_command` enum('1','0') DEFAULT '0',
  `lava_command` enum('1','0') DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.server_permissions: ~19 rows (approximately)
DELETE FROM `server_permissions`;
/*!40000 ALTER TABLE `server_permissions` DISABLE KEYS */;
INSERT INTO `server_permissions` (`id`, `name`, `flood_bypass`, `flood_time`, `disconnectable`, `mod_tool`, `alfa_tool`, `bannable`, `room_kickable`, `room_full_control`, `room_mute_bypass`, `room_filter_bypass`, `room_ignorable`, `room_enter_full`, `cmd_search`, `room_enter_locked`, `room_staff_pick`, `room_see_whispers`, `messenger_staff_chat`, `messenger_mod_chat`, `messenger_log_chat`, `messenger_alfa_chat`, `messenger_max_friends`, `about_detailed`, `about_stats`, `login_notif`, `about_command`, `globalbubble_command`, `globalalert_command`, `freeze_command`, `alertnoti_command`, `preguntar_command`, `commands_command`, `restart_command`, `teleport_command`, `sellroom_command`, `pickall_command`, `massmotd_command`, `hotelalert_command`, `invisible_command`, `disablefun_command`, `push_command`, `moonwalk_command`, `enable_command`, `ban_command`, `empty_command`, `unload_command`, `roomvideo_command`, `ipban_command`, `givebadge_command`, `reload_command`, `transform_command`, `coins_command`, `pull_command`, `sit_command`, `buyroom_command`, `prefix_command`, `alert_command`, `points_command`, `kick_command`, `mimic_command`, `machineban_command`, `massbadge_command`, `masscoins_command`, `masspoints_command`, `massduckets_command`, `redeemcredits_command`, `playerinfo_command`, `roommute_command`, `roomunmute_command`, `handitem_command`, `setmax_command`, `removebadge_command`, `deletegroup_command`, `shutdown_command`, `togglediagonal_command`, `roll_command`, `hotelalertlink_command`, `summon_command`, `togglefriends_command`, `roomaction_command`, `enablecommand_command`, `disablecommand_command`, `mute_command`, `unmute_command`, `punch_command`, `bundle_command`, `notification_command`, `maintenance_command`, `eventalert_command`, `quickpoll_command`, `ejectall_command`, `fastwalk_command`, `roomoption_command`, `ignoreevents_command`, `hidewired_command`, `eventreward_command`, `emptyfriends_command`, `reward_command`, `massteleport_command`, `massfreeze_command`, `eventwon_command`, `height_command`, `listen_command`, `cloneroom_command`, `follow_command`, `rob_command`, `kiss_command`, `sex_command`, `nalgada_command`, `murder_command`, `sing_command`, `secuestrar_command`, `nuke_command`, `staffon_command`, `hug_command`, `lay_command`, `mass_seasonal_command`, `staffalert_command`, `roomalert_command`, `roomkick_command`, `makesay_command`, `disconnect_command`, `superpull_command`, `staffinfo_command`, `unban_command`, `roomnotification_command`, `namecolour_command`, `disablewhisper_command`, `superban_command`, `setspeed_command`, `viewinventory_command`, `mentionsettings_command`, `toggleshoot_command`, `personalstaff_command`, `seasonal_command`, `setbet_command`, `warp_command`, `changelog_command`, `staffbubble_command`, `tradeban_command`, `resetdicecount_command`, `betsystem_command`, `rps_command`, `bank_command`, `eventlog_command`, `finalevent_command`, `selectwindow_command`, `voucher_command`, `puke_command`, `help_command`, `link_command`, `idle_command`, `control_command`, `position_command`, `build_command`, `massrare_command`, `invitation_command`, `invite_command`, `setz_command`, `vipbundle_command`, `flaguser_command`, `override_command`, `furnifix_command`, `closedice_command`, `smoke_command`, `married_command`, `mentions_command`, `look_command`, `giverank_command`, `wha_command`, `flagme_command`, `autofloor_command`, `maxfloor_command`, `setidletimer_command`, `flooredit_command`, `superwired_command`, `superpush_command`, `welcome_command`, `lava_command`) VALUES
	(1, 'Usuario', '0', 20, '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', 1100, '1', '1', '0', '1', '0', '0', '0', '0', '0', '1', '0', '2', '2', '2', '0', '0', '0', '1', '1', '0', '1', '0', '1', '2', '2', '0', '0', '0', '1', '0', '1', '1', '1', '1', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '2', '0', '0', '0', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '2', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '0', '2', '1', '0', '0', '1'),
	(2, 'VIP', '1', 30, '1', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', 1100, '1', '1', '0', '1', '0', '0', '0', '0', '0', '1', '0', '2', '2', '2', '0', '0', '0', '0', '1', '1', '1', '0', '1', '2', '2', '0', '0', '2', '1', '0', '1', '1', '1', '1', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '2', '0', '0', '0', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '2', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '1', '0', '1'),
	(3, 'DJ', '1', 30, '1', '0', '0', '1', '0', '0', '1', '1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', 1100, '1', '1', '0', '1', '0', '1', '0', '0', '0', '1', '0', '2', '2', '2', '0', '0', '0', '0', '1', '1', '1', '0', '1', '2', '2', '0', '0', '2', '1', '0', '1', '1', '1', '1', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '2', '0', '0', '0', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '1', '0', '1', '0', '1', '0', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '1', '0', '0', '2', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '0', '0', '1'),
	(4, 'MaW', '1', 30, '1', '0', '0', '1', '0', '0', '1', '1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', 1100, '1', '1', '0', '1', '0', '0', '0', '1', '0', '1', '0', '2', '2', '2', '0', '0', '0', '0', '1', '1', '1', '0', '1', '2', '2', '0', '0', '2', '1', '0', '1', '1', '1', '1', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '2', '0', '0', '0', '2', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0', '1', '0', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '1', '0', '0', '2', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '0', '0', '1'),
	(5, 'Croupier', '1', 30, '1', '0', '0', '1', '0', '0', '1', '1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', 1100, '1', '1', '0', '1', '0', '0', '0', '0', '0', '1', '0', '2', '2', '2', '0', '0', '0', '0', '1', '1', '1', '0', '1', '2', '2', '0', '0', '2', '1', '0', '1', '1', '1', '1', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '2', '0', '0', '0', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '1', '0', '1', '0', '1', '0', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '1', '0', '0', '2', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '0', '0', '1'),
	(7, 'Master Trader', '1', 30, '1', '0', '0', '1', '0', '0', '1', '1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', 1100, '1', '1', '0', '1', '0', '0', '0', '0', '0', '1', '0', '2', '2', '2', '0', '0', '0', '0', '1', '1', '1', '0', '1', '2', '2', '0', '0', '2', '1', '0', '1', '1', '1', '1', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '2', '0', '0', '0', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '1', '0', '1', '0', '1', '0', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '1', '0', '0', '2', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '0', '0', '1'),
	(8, 'Guía', '1', 30, '1', '0', '1', '1', '0', '0', '1', '1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '1', 1100, '1', '1', '0', '1', '0', '0', '0', '0', '0', '1', '0', '2', '2', '2', '0', '0', '0', '0', '1', '1', '1', '0', '1', '2', '2', '0', '0', '2', '1', '0', '1', '1', '1', '1', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '2', '0', '0', '0', '2', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '1', '0', '1', '0', '1', '0', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '1', '0', '0', '2', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '0', '0', '1'),
	(9, 'Publicista', '1', 30, '1', '0', '0', '1', '0', '0', '1', '1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '1', 1100, '1', '1', '0', '1', '0', '0', '1', '0', '0', '1', '0', '2', '2', '2', '0', '0', '0', '0', '1', '1', '1', '0', '1', '2', '2', '0', '0', '2', '1', '0', '1', '1', '1', '1', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '2', '0', '0', '0', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '1', '0', '1', '0', '1', '0', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '1', '0', '0', '2', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '0', '0', '1'),
	(10, 'Encargado de Publicidad', '1', 20, '1', '0', '0', '1', '0', '0', '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '0', '1', 1100, '1', '1', '0', '1', '0', '0', '1', '1', '0', '1', '0', '1', '2', '2', '0', '0', '0', '0', '1', '1', '1', '0', '1', '2', '2', '0', '1', '2', '1', '0', '1', '1', '1', '1', '0', '0', '1', '1', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '2', '0', '0', '0', '2', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '1', '0', '1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '1', '0', '0', '2', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '0', '0', '1'),
	(11, 'Embajador', '1', 10, '1', '0', '1', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '1', 1100, '1', '1', '0', '1', '0', '0', '1', '0', '0', '1', '0', '1', '2', '2', '0', '0', '0', '0', '1', '1', '1', '0', '1', '2', '2', '0', '0', '2', '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '2', '0', '0', '0', '2', '0', '0', '0', '1', '0', '0', '0', '1', '1', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '1', '1', '0', '0', '2', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '0', '0', '1'),
	(12, 'Colaborador', '1', 10, '1', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '1', 1100, '1', '1', '0', '1', '0', '0', '1', '0', '0', '1', '0', '1', '2', '2', '0', '0', '0', '0', '1', '1', '1', '1', '1', '2', '2', '0', '1', '2', '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '2', '0', '0', '0', '2', '1', '0', '1', '1', '1', '1', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '0', '1', '0', '1', '1', '1', '0', '0', '1', '0', '1', '0', '1', '0', '1', '1', '1', '1', '0', '0', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '0', '0', '1'),
	(13, 'Moderador', '1', 30, '1', '0', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', 1100, '1', '1', '1', '1', '1', '1', '1', '0', '0', '1', '0', '1', '2', '2', '0', '0', '0', '0', '1', '1', '1', '1', '1', '2', '2', '0', '1', '2', '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '2', '0', '0', '0', '2', '1', '0', '1', '1', '1', '1', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '0', '1', '0', '1', '1', '1', '0', '0', '1', '0', '1', '0', '1', '0', '0', '1', '1', '1', '0', '0', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '0', '0', '1'),
	(14, 'Game Master', '1', 30, '1', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '1', 1100, '1', '1', '0', '1', '0', '0', '1', '0', '0', '1', '0', '1', '2', '2', '0', '0', '0', '0', '1', '1', '1', '1', '1', '2', '2', '0', '1', '2', '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '2', '0', '0', '0', '2', '1', '0', '1', '1', '1', '1', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '0', '1', '0', '1', '1', '1', '0', '0', '1', '0', '1', '0', '1', '0', '0', '1', '1', '1', '0', '0', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '0', '0', '1'),
	(15, 'Co-Administrador', '1', 30, '1', '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', 1100, '1', '1', '1', '1', '0', '0', '1', '1', '0', '1', '0', '1', '2', '2', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '2', '1', '1', '2', '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '1', '0', '0', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '1', '0', '0', '1', '1', '1', '1', '0', '0', '1', '0', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1', '1', '0', '1', '0', '2', '2', '2', '2', '1', '0', '0', '1'),
	(16, 'Administrador', '1', 30, '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', 1100, '1', '1', '0', '1', '0', '1', '1', '1', '1', '1', '0', '1', '2', '2', '0', '1', '0', '1', '1', '1', '1', '1', '1', '1', '2', '1', '1', '2', '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '2', '1', '1', '0', '0', '1'),
	(17, 'CEO', '1', 30, '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '2', '2', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '1', '1', '2', '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '2', '1', '2', '1', '1', '0', '1', '1'),
	(18, 'Fundador', '1', 30, '0', '1', '1', '1', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '1', '0', '0', '1', '0', '1', '1', '0', '1', '2', '2', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '2', '1', '1', '2', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '1', '0', '1', '1', '1', '0', '0', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '1', '0', '1', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '2', '2', '2', '2', '1', '1', '0', '1'),
	(19, 'Desarrollador', '1', 30, '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '2', '2', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '1', '1', '0', '1', '1'),
	(20, 'Oculto', '1', 30, '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '1', '0', '0', '1', '0', '1', '1', '0', '1', '2', '2', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '1', '0', '1', '1', '1', '0', '0', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '2', '2', '1', '0', '0', '1');
/*!40000 ALTER TABLE `server_permissions` ENABLE KEYS */;

-- Dumping structure for table hylib.server_permissions_ranks
DROP TABLE IF EXISTS `server_permissions_ranks`;
CREATE TABLE IF NOT EXISTS `server_permissions_ranks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `flood_bypass` enum('1','0') NOT NULL DEFAULT '0',
  `flood_time` int(11) NOT NULL DEFAULT 30,
  `disconnectable` enum('1','0') NOT NULL DEFAULT '1',
  `mod_tool` enum('1','0') NOT NULL DEFAULT '0',
  `bannable` enum('1','0') NOT NULL DEFAULT '0',
  `room_kickable` enum('1','0') NOT NULL DEFAULT '1',
  `room_full_control` enum('1','0') NOT NULL DEFAULT '1',
  `room_mute_bypass` enum('1','0') NOT NULL DEFAULT '1',
  `room_filter_bypass` enum('1','0') NOT NULL DEFAULT '0',
  `room_ignorable` enum('1','0') NOT NULL DEFAULT '1',
  `room_enter_full` enum('1','0') NOT NULL DEFAULT '1',
  `room_enter_locked` enum('1','0') NOT NULL DEFAULT '1',
  `room_staff_pick` enum('1','0') NOT NULL DEFAULT '1',
  `room_see_whispers` enum('1','0') NOT NULL DEFAULT '1',
  `messenger_staff_chat` enum('1','0') NOT NULL DEFAULT '0',
  `messenger_max_friends` int(11) NOT NULL DEFAULT 1100,
  `about_detailed` enum('1','0') NOT NULL DEFAULT '0',
  `about_stats` enum('1','0') NOT NULL DEFAULT '0',
  `messenger_log_chat` enum('1','0') NOT NULL DEFAULT '1',
  `login_notif` enum('1','0') DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.server_permissions_ranks: ~19 rows (approximately)
DELETE FROM `server_permissions_ranks`;
/*!40000 ALTER TABLE `server_permissions_ranks` DISABLE KEYS */;
INSERT INTO `server_permissions_ranks` (`id`, `name`, `flood_bypass`, `flood_time`, `disconnectable`, `mod_tool`, `bannable`, `room_kickable`, `room_full_control`, `room_mute_bypass`, `room_filter_bypass`, `room_ignorable`, `room_enter_full`, `room_enter_locked`, `room_staff_pick`, `room_see_whispers`, `messenger_staff_chat`, `messenger_max_friends`, `about_detailed`, `about_stats`, `messenger_log_chat`, `login_notif`) VALUES
	(1, 'Usuario', '0', 30, '1', '0', '1', '1', '0', '0', '0', '1', '0', '0', '1', '0', '0', 1100, '1', '1', '0', '0'),
	(2, 'VIP', '0', 30, '1', '0', '0', '0', '1', '1', '0', '0', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(3, 'DJ', '0', 30, '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(4, 'MaW', '0', 30, '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(5, 'Croupier', '0', 30, '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(7, 'Master Trader', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(8, 'Guía', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(9, 'Publicista', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(10, 'Encargado de Publicidad', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(11, 'Embajador', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(12, 'Colaborador', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(13, 'Moderador', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(14, 'Game Master', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(15, 'Co-Administrador', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(16, 'Administrador', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(17, 'CEO', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(18, 'Fundador', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(19, 'Desarrollador', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0'),
	(20, 'Oculto', '0', 30, '0', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', 1100, '1', '1', '1', '0');
/*!40000 ALTER TABLE `server_permissions_ranks` ENABLE KEYS */;

-- Dumping structure for table hylib.server_ranks
DROP TABLE IF EXISTS `server_ranks`;
CREATE TABLE IF NOT EXISTS `server_ranks` (
  `rank_id` int(11) NOT NULL DEFAULT 1,
  `title` varchar(50) NOT NULL DEFAULT 'Player',
  `badge_id` varchar(50) DEFAULT 'Player',
  `permission_mod_tool` enum('1','0') NOT NULL DEFAULT '0',
  `permission_room_full_control` enum('1','0') NOT NULL DEFAULT '0',
  `permission_bypass_filter` enum('1','0') NOT NULL DEFAULT '0',
  `permission_room_unkickable` enum('1','0') NOT NULL DEFAULT '0',
  `permission_unignorable` enum('1','0') NOT NULL DEFAULT '0',
  `permission_room_staff_pick` enum('1','0') NOT NULL DEFAULT '0',
  `messenger_log_chat` enum('1','0') NOT NULL DEFAULT '0',
  `flood_length` int(11) DEFAULT 30,
  PRIMARY KEY (`rank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.server_ranks: ~19 rows (approximately)
DELETE FROM `server_ranks`;
/*!40000 ALTER TABLE `server_ranks` DISABLE KEYS */;
INSERT INTO `server_ranks` (`rank_id`, `title`, `badge_id`, `permission_mod_tool`, `permission_room_full_control`, `permission_bypass_filter`, `permission_room_unkickable`, `permission_unignorable`, `permission_room_staff_pick`, `messenger_log_chat`, `flood_length`) VALUES
	(1, 'Usuario', '', '0', '0', '1', '0', '0', '0', '0', 30),
	(2, 'VIP', 'XXX', '0', '1', '1', '1', '1', '0', '0', 30),
	(3, 'DJ', 'ADM', '1', '1', '1', '1', '1', '0', '1', 30),
	(4, 'MaW', 'ADM', '1', '1', '1', '1', '1', '0', '1', 30),
	(5, 'Croupier', 'ADM', '1', '1', '1', '1', '1', '0', '1', 30),
	(7, 'Master Trader', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(8, 'GuÃ­a', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(9, 'Publicista', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(10, 'Encargado de Publicidad', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(11, 'Embajador', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(12, 'Colaborador', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(13, 'Moderador', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(14, 'Game Master', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(15, 'Co-Administrador', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(16, 'Administrador', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(17, 'CEO', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(18, 'Fundador', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(19, 'Desarrollador', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0),
	(20, 'Oculto', 'ADM', '1', '1', '1', '1', '1', '0', '1', 0);
/*!40000 ALTER TABLE `server_ranks` ENABLE KEYS */;

-- Dumping structure for table hylib.server_status
DROP TABLE IF EXISTS `server_status`;
CREATE TABLE IF NOT EXISTS `server_status` (
  `active_players` int(11) DEFAULT 0,
  `player_record` int(11) DEFAULT 0,
  `active_rooms` int(11) DEFAULT 0,
  `server_version` varchar(50) DEFAULT '0',
  `player_record_timestamp` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.server_status: ~0 rows (approximately)
DELETE FROM `server_status`;
/*!40000 ALTER TABLE `server_status` DISABLE KEYS */;
INSERT INTO `server_status` (`active_players`, `player_record`, `active_rooms`, `server_version`, `player_record_timestamp`) VALUES
	(3, 89, 1, '2.9.8-TEST1', 1642196735);
/*!40000 ALTER TABLE `server_status` ENABLE KEYS */;

-- Dumping structure for table hylib.server_survival_settings
DROP TABLE IF EXISTS `server_survival_settings`;
CREATE TABLE IF NOT EXISTS `server_survival_settings` (
  `gun_damage` int(11) NOT NULL DEFAULT 15,
  `gun_distance` int(11) NOT NULL DEFAULT 60,
  `gun_cooldown` int(11) NOT NULL DEFAULT 150,
  `gun_bullets` int(11) NOT NULL DEFAULT 150,
  `sniper_damage` int(11) NOT NULL DEFAULT 150,
  `sniper_distance` int(11) NOT NULL DEFAULT 5,
  `sniper_cooldown` int(11) NOT NULL DEFAULT 150,
  `sniper_bullets` int(11) NOT NULL DEFAULT 0,
  `melee_damage` int(11) NOT NULL DEFAULT 0,
  `melee_distance` int(11) NOT NULL DEFAULT 35,
  `melee_cooldown` int(11) NOT NULL DEFAULT 0,
  `slay_xp` int(11) NOT NULL DEFAULT 150,
  `win_xp` int(11) NOT NULL DEFAULT 5,
  `slay_bullets` int(11) NOT NULL DEFAULT 5,
  `slay_shield` enum('true','false') NOT NULL DEFAULT 'true',
  `chest_bullets` int(11) NOT NULL DEFAULT 5,
  `speed_time` int(11) NOT NULL DEFAULT 5
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.server_survival_settings: ~0 rows (approximately)
DELETE FROM `server_survival_settings`;
/*!40000 ALTER TABLE `server_survival_settings` DISABLE KEYS */;
INSERT INTO `server_survival_settings` (`gun_damage`, `gun_distance`, `gun_cooldown`, `gun_bullets`, `sniper_damage`, `sniper_distance`, `sniper_cooldown`, `sniper_bullets`, `melee_damage`, `melee_distance`, `melee_cooldown`, `slay_xp`, `win_xp`, `slay_bullets`, `slay_shield`, `chest_bullets`, `speed_time`) VALUES
	(10, 6, 2, 15, 20, 12, 3, 0, 1, 2, 0, 100, 5, 25, 'true', 25, 10);
/*!40000 ALTER TABLE `server_survival_settings` ENABLE KEYS */;

-- Dumping structure for table hylib.site_hotcampaigns
DROP TABLE IF EXISTS `site_hotcampaigns`;
CREATE TABLE IF NOT EXISTS `site_hotcampaigns` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL DEFAULT 1,
  `enabled` enum('0','1') NOT NULL DEFAULT '1',
  `image_url` text NOT NULL,
  `caption` text NOT NULL,
  `descr` text NOT NULL,
  `url` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.site_hotcampaigns: ~0 rows (approximately)
DELETE FROM `site_hotcampaigns`;
/*!40000 ALTER TABLE `site_hotcampaigns` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_hotcampaigns` ENABLE KEYS */;

-- Dumping structure for table hylib.site_news
DROP TABLE IF EXISTS `site_news`;
CREATE TABLE IF NOT EXISTS `site_news` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `seo_link` varchar(120) NOT NULL DEFAULT 'news-article',
  `title` text NOT NULL,
  `category_id` int(10) unsigned NOT NULL DEFAULT 1,
  `topstory_image` text NOT NULL,
  `body` text NOT NULL,
  `snippet` text NOT NULL,
  `datestr` varchar(50) NOT NULL,
  `timestamp` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `datestr` (`datestr`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.site_news: ~0 rows (approximately)
DELETE FROM `site_news`;
/*!40000 ALTER TABLE `site_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_news` ENABLE KEYS */;

-- Dumping structure for table hylib.sollicitaties
DROP TABLE IF EXISTS `sollicitaties`;
CREATE TABLE IF NOT EXISTS `sollicitaties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` text NOT NULL,
  `email` text NOT NULL,
  `skype` text NOT NULL,
  `leeftijd` text NOT NULL,
  `rank` text NOT NULL,
  `reden` text NOT NULL,
  `ervaring` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.sollicitaties: ~0 rows (approximately)
DELETE FROM `sollicitaties`;
/*!40000 ALTER TABLE `sollicitaties` DISABLE KEYS */;
/*!40000 ALTER TABLE `sollicitaties` ENABLE KEYS */;

-- Dumping structure for table hylib.songs
DROP TABLE IF EXISTS `songs`;
CREATE TABLE IF NOT EXISTS `songs` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `song_data` text NOT NULL,
  `artist` varchar(32) NOT NULL,
  `creator_id` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table hylib.songs: ~14 rows (approximately)
DELETE FROM `songs`;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
INSERT INTO `songs` (`id`, `name`, `song_data`, `artist`, `creator_id`) VALUES
	(1, 'Xmas Magic', '1:387,4;387,3;0,5;387,4;0,23;558,8;0,8;558,8;0,8;558,4;0,4;558,4;0,4;558,4;476,1;0,3;558,4;0,3;476,2;0,2;476,2;0,14;387,6:2:0,4;484,8;484,3;0,5;61,4;0,40;481,1;482,3;481,5;482,3;481,5;482,3;481,5;482,3;481,4;0,1;482,4;0,3;476,2;0,2;476,4:3:0,4;477,2;476,2;0,2;476,2;0,2;476,2;0,3;476,4;0,1;485,4;0,43;485,4;0,7;476,3;0,2;485,4;0,1;476,4;0,5;558,4:4:0,2;485,6;0,11;485,7;0,6;498,78;0,2;473,4;474,4;473,4:5:0,16;326,12;326,3;0,1;326,68;326,2:6:0,8;476,2;0,2;476,2;0,2;476,3;0,6;325,42;0,12;325,12;0,4;325,4:7:0,27;325,3;325,2;0,14;475,1;0,1;475,4;475,4;0,22;477,2;0,5;476,4;476,3:8:0,38;474,2;0,30;486,32:meta,1;c,1', 'Silent Aurora', 256000),
	(2, 'Who Dares Stacks', '1:255,4;310,7;0,9;310,14;0,4:2:0,3;309,1;308,7;309,1;308,7;309,1;308,7;309,1;308,7;309,1;0,2:3:0,4;162,7;0,1;162,7;0,1;162,15;0,3:4:0,6;135,4;135,4;135,4;0,2;135,4;135,4;135,4;135,4;0,2:5:0,14;155,2;0,2;155,2;314,1;315,1;314,1;315,1;314,1;315,1;314,1;0,1;314,1;315,1;314,1;315,1;314,1;315,1;314,1;315,1;0,2:6:0,12;311,1;0,1;311,1;0,1;311,1;0,1;311,1;0,1;311,1;0,5;155,2;311,1;0,1;311,1;0,1;311,1;0,3;311,1;0,1:', 'Rage Against the Fuse', 76000),
	(3, 'Push the Call for Help', '1:317,6;318,4;319,4;317,4;319,4;0,2:2:0,2;316,4;0,4;316,4;0,4;316,4;0,2:3:0,6;321,4;323,4;322,10:4:0,18;321,2;324,2;0,2:', 'BanzaiBabes', 56236),
	(4, 'About VIP Now', '1:152,20;146,1;0,1;152,4;151,4;152,20;153,4:2:0,8;145,12;146,1;0,1;145,4;0,1;151,2;0,1;145,20;0,1;150,2;0,1:3:0,10;150,2;146,1;0,1;150,2;146,1;0,1;150,2;146,1;0,7;151,2;0,2;150,2;146,1;0,1;150,2;146,1;0,1;150,2;0,1;146,1;0,1;146,1;0,1;146,3;0,4:4:0,4;148,2;147,2;148,2;147,2;148,2;147,2;148,2;147,2;148,4;147,2;148,6;147,2;148,2;147,2;148,2;147,2;148,2;147,2;148,2;147,2;0,4:', 'BanzaiBabes', 108000),
	(5, 'Habbowood', '1:280,4;265,4;264,4;263,8;0,16:2:262,4;263,8;266,4;267,4;264,12;262,4:3:0,4;268,8;269,4;270,4;268,8;282,4;285,4:4:0,20;74,4;75,3;81,3;0,6:', 'Michael Bauble', 72000),
	(6, 'I Write Bans not Tragedies', '1:248,4;247,4;252,4;251,8;245,4;250,4;252,4:2:359,4;250,4;359,4;345,8;0,4;359,8:3:0,3;347,1;359,4;352,8;342,4;350,4;342,4;350,4:4:0,3;357,1;334,4;246,4;343,12;334,4;340,2;0,1;347,1:', 'Pixel! at the Disco', 64000),
	(9, 'Too Lost In Lido', '1:317,4;408,7;0,1;410,16;413,4;406,4;410,8;412,4:2:0,2;321,2;443,22;91,2;317,8;443,8;412,2;0,2:3:0,3;320,2;0,7;414,4;445,4;412,2;323,2;412,4;96,2;412,2;414,4;445,7;448,1;317,4:4:0,3;324,2;0,6;448,1;0,6;96,2;322,4;96,2;99,2;322,4;412,2;0,2;322,2;96,2;322,2;0,1;324,2;0,3:', 'BanzaiBabes', 96000),
	(10, 'Gold Coin Digger', '1:104,10;102,8;104,4;102,8;104,4:2:0,2;181,8;182,8;181,4;182,8;130,2;106,2:3:0,2;105,2;106,2;186,3;187,1;185,1;184,1;185,1;184,1;185,1;184,1;185,1;187,1;186,3;187,1;185,1;184,1;185,1;184,1;185,1;184,1;185,1;184,1;72,1;100,2;0,1:4:0,4;103,6;133,1;101,1;133,1;101,1;133,1;101,1;133,1;101,1;103,4;133,1;101,1;133,1;101,1;133,1;101,1;133,1;101,1;105,2;101,1;0,1:', 'Kayne Quest', 68000),
	(11, 'Park Adventure', '1:200,12;199,3;201,1;200,28:2:0,2;190,1;0,1;191,1;0,1;192,1;0,1;190,2;191,1;192,1;190,2;0,1;193,2;190,2;191,1;0,1;190,1;192,2;0,1;191,2;178,2;0,1;178,2;0,1;178,2;0,2;177,2;0,1;176,4:3:0,8;176,2;0,1;177,2;0,3;179,2;0,2;177,2;0,2;176,2;0,1;178,6;0,1;178,2;0,2;177,4;176,2:4:0,8;197,16;0,20:', 'Kallomies', 88000),
	(12, 'Pet Romance', '1:118,1;0,1;136,2;0,2;137,2;0,2;137,2;136,2;137,2;136,4;71,6;0,4:2:121,6;122,4;123,4;122,4;123,2;122,4;0,2;169,2;0,2:3:0,1;125,1;143,8;68,2;165,2;69,1;0,1;69,1;168,1;169,2;69,1;0,1;69,1;125,1;143,4;167,1;0,1:4:0,2;120,8;138,4;120,6;138,6;66,2;121,2:', 'Lady BlaBla', 60000),
	(13, 'The Good Trade', '1:108,4;135,8;130,2;108,2;135,8;127,28:2:0,3;131,9;104,2;0,2;131,8;108,4;102,4;0,8;92,6;0,6:3:134,4;140,8;0,1;133,1;136,2;140,6;0,10;143,16;108,4:4:0,2;104,2;0,1;133,1;0,3;133,1;134,4;0,1;104,2;133,1;0,3;133,1;0,24;136,2;0,4:', 'Kayne Quest', 2167616),
	(14, 'The Ballad of Bonnie Blonde', '1:371,8;374,4;378,4;0,4;378,4;0,4;377,4;378,4;0,6;371,4;0,4;370,3;377,2;0,7:2:353,24;358,4;363,4;353,10;367,4;353,4;367,4;353,8:3:0,4;291,2;365,2;0,2;365,2;377,2;365,2;377,2;365,2;377,2;0,2;359,4;352,4;357,1;0,1;365,2;0,2;292,4;0,3;357,1;292,4;0,3;357,1;292,4;296,4:4:0,2;368,22;354,4;368,26;361,8:', 'Pixel! at the Disco', 124000),
	(15, 'Party Trax', '1:12,16;3,4;19,8;0,2;29,8;0,16:2:13,4;14,4;10,8;0,4;2,8;0,1;33,1;32,18;16,2;32,2;16,2:3:0,2;15,1;17,1;5,4;7,2;24,1;8,1;4,4;0,2;24,1;6,1;0,4;22,4;0,2;28,4;30,4;31,2;16,2;31,2;16,2;20,1;21,1;20,1;21,1;20,1;21,1;20,1;21,1:4:18,2;0,2;10,4;5,22;35,2;0,20;24,1;17,1:', 'Aerokid', 2171040),
	(16, 'Furni Face', '1:379,4;45,4;0,1;205,2;42,5;37,4;384,2;41,4;42,5;0,2:2:0,3;199,3;0,1;383,3;519,4;515,8;519,8;39,2;0,1:3:0,2;205,2;382,4;522,2;516,12;518,8;0,3:4:0,4;386,2;0,2;43,1;0,1;207,3;202,1;520,8;44,2;0,2;520,4;207,3:', 'Lady BlaBla', 66000);
/*!40000 ALTER TABLE `songs` ENABLE KEYS */;

-- Dumping structure for table hylib.staffapplication
DROP TABLE IF EXISTS `staffapplication`;
CREATE TABLE IF NOT EXISTS `staffapplication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` text DEFAULT NULL,
  `realname` text DEFAULT NULL,
  `skype` text DEFAULT NULL,
  `age` text DEFAULT NULL,
  `functie` text DEFAULT NULL,
  `onlinetime` text DEFAULT NULL,
  `experience` text DEFAULT NULL,
  `quarrel` text DEFAULT NULL,
  `serious` text DEFAULT NULL,
  `improve` text DEFAULT NULL,
  `microphone` text DEFAULT NULL,
  `ip` text DEFAULT NULL,
  `date` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table hylib.staffapplication: ~0 rows (approximately)
DELETE FROM `staffapplication`;
/*!40000 ALTER TABLE `staffapplication` DISABLE KEYS */;
/*!40000 ALTER TABLE `staffapplication` ENABLE KEYS */;

-- Dumping structure for table hylib.staff_logs
DROP TABLE IF EXISTS `staff_logs`;
CREATE TABLE IF NOT EXISTS `staff_logs` (
  `player_id` int(11) NOT NULL DEFAULT 0,
  `type` enum('ALERT','BAN','DISCONNECT','CHECK','NONE') NOT NULL DEFAULT 'NONE',
  `target` varchar(50) NOT NULL DEFAULT '',
  `value` varchar(255) DEFAULT '',
  `time` int(11) NOT NULL DEFAULT 0,
  KEY `player_id` (`player_id`),
  KEY `target` (`target`),
  KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.staff_logs: ~0 rows (approximately)
DELETE FROM `staff_logs`;
/*!40000 ALTER TABLE `staff_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_logs` ENABLE KEYS */;

-- Dumping structure for table hylib.sterrenkopen
DROP TABLE IF EXISTS `sterrenkopen`;
CREATE TABLE IF NOT EXISTS `sterrenkopen` (
  `naam` varchar(100) NOT NULL,
  `datum` datetime NOT NULL,
  `aantal` varchar(100) NOT NULL,
  `betaald` varchar(100) NOT NULL DEFAULT 'ja'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table hylib.sterrenkopen: ~0 rows (approximately)
DELETE FROM `sterrenkopen`;
/*!40000 ALTER TABLE `sterrenkopen` DISABLE KEYS */;
/*!40000 ALTER TABLE `sterrenkopen` ENABLE KEYS */;

-- Dumping structure for table hylib.teamapplication
DROP TABLE IF EXISTS `teamapplication`;
CREATE TABLE IF NOT EXISTS `teamapplication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` text DEFAULT NULL,
  `realname` text DEFAULT NULL,
  `skype` text DEFAULT NULL,
  `age` text DEFAULT NULL,
  `functie` text DEFAULT NULL,
  `onlinetime` text DEFAULT NULL,
  `experience` text DEFAULT NULL,
  `quarrel` text DEFAULT NULL,
  `serious` text DEFAULT NULL,
  `improve` text DEFAULT NULL,
  `microphone` text DEFAULT NULL,
  `ip` text DEFAULT NULL,
  `date` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table hylib.teamapplication: ~2 rows (approximately)
DELETE FROM `teamapplication`;
/*!40000 ALTER TABLE `teamapplication` DISABLE KEYS */;
INSERT INTO `teamapplication` (`id`, `username`, `realname`, `skype`, `age`, `functie`, `onlinetime`, `experience`, `quarrel`, `serious`, `improve`, `microphone`, `ip`, `date`) VALUES
	(1, 'Julian', 'info@julianvanschijndel.nl', 'Julian#7791', NULL, NULL, 'Ik wil dit even testen', ' test', 'test ', 'Beiden is mogelijk.', NULL, NULL, '84.25.151.38', '1535549671'),
	(2, 'Loleris', 'sgj.kerkhoff.03@gmail.com', 'Sander#8803', NULL, NULL, 'Administratie', ' Ik will graag helpen met Hotellen met moeite en daardoor kwam deze tervoorschijn \r\nIk will en zou willen Proberen dit hotel groter te krijgen.', ' ', 'Discord', NULL, NULL, '82.168.244.187', '1536861992');
/*!40000 ALTER TABLE `teamapplication` ENABLE KEYS */;

-- Dumping structure for table hylib.teams
DROP TABLE IF EXISTS `teams`;
CREATE TABLE IF NOT EXISTS `teams` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `badgeid` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.teams: ~5 rows (approximately)
DELETE FROM `teams`;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` (`id`, `name`, `badgeid`) VALUES
	(1, 'Spam Team', 'SPAM'),
	(3, 'Bouw Team', 'BOUW'),
	(4, 'Event Team', 'EVENT'),
	(5, 'Pixelaar', 'PIXEL'),
	(6, 'Gok Team', 'GOK');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;

-- Dumping structure for table hylib.uotw
DROP TABLE IF EXISTS `uotw`;
CREATE TABLE IF NOT EXISTS `uotw` (
  `userid` varchar(255) CHARACTER SET utf8mb3 DEFAULT NULL,
  `userid2` varchar(255) CHARACTER SET utf8mb3 DEFAULT NULL,
  `userid3` varchar(255) CHARACTER SET utf8mb3 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.uotw: ~0 rows (approximately)
DELETE FROM `uotw`;
/*!40000 ALTER TABLE `uotw` DISABLE KEYS */;
INSERT INTO `uotw` (`userid`, `userid2`, `userid3`) VALUES
	('294', 'Loleris', 'sh-300-110.hr-3357-61.hd-3093-2.fa-3344-110.ch-215-92.cc-3327-110-73.wa-2005-110.ea-3169-1408.lg-285-110');
/*!40000 ALTER TABLE `uotw` ENABLE KEYS */;

-- Dumping structure for table hylib.users_like
DROP TABLE IF EXISTS `users_like`;
CREATE TABLE IF NOT EXISTS `users_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `likefrom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table hylib.users_like: ~0 rows (approximately)
DELETE FROM `users_like`;
/*!40000 ALTER TABLE `users_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_like` ENABLE KEYS */;

-- Dumping structure for table hylib.user_session_log
DROP TABLE IF EXISTS `user_session_log`;
CREATE TABLE IF NOT EXISTS `user_session_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `browser` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table hylib.user_session_log: ~0 rows (approximately)
DELETE FROM `user_session_log`;
/*!40000 ALTER TABLE `user_session_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_session_log` ENABLE KEYS */;

-- Dumping structure for table hylib.vip
DROP TABLE IF EXISTS `vip`;
CREATE TABLE IF NOT EXISTS `vip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `timestamp` int(11) NOT NULL,
  `timestampend` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id_user` (`id_user`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.vip: ~0 rows (approximately)
DELETE FROM `vip`;
/*!40000 ALTER TABLE `vip` DISABLE KEYS */;
/*!40000 ALTER TABLE `vip` ENABLE KEYS */;

-- Dumping structure for table hylib.vouchers
DROP TABLE IF EXISTS `vouchers`;
CREATE TABLE IF NOT EXISTS `vouchers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('COINS','DUCKETS','VIP_POINTS','ROOM_BUNDLE','CRYPTOLOGY','ITEM') NOT NULL DEFAULT 'COINS',
  `data` text NOT NULL,
  `created_by` int(11) NOT NULL DEFAULT 0,
  `created_at` int(11) NOT NULL DEFAULT 0,
  `claimed_by` varchar(255) NOT NULL DEFAULT '0',
  `claimed_at` int(11) NOT NULL DEFAULT 0,
  `limit_use` int(11) NOT NULL DEFAULT 0,
  `status` enum('UNCLAIMED','CLAIMED') NOT NULL DEFAULT 'UNCLAIMED',
  `code` varchar(128) NOT NULL DEFAULT 'voucher-00001',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE,
  KEY `created_by` (`created_by`) USING BTREE,
  KEY `claimed_by` (`claimed_by`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.vouchers: ~0 rows (approximately)
DELETE FROM `vouchers`;
/*!40000 ALTER TABLE `vouchers` DISABLE KEYS */;
/*!40000 ALTER TABLE `vouchers` ENABLE KEYS */;

-- Dumping structure for table hylib.website_articles
DROP TABLE IF EXISTS `website_articles`;
CREATE TABLE IF NOT EXISTS `website_articles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `article_slug` varchar(50) NOT NULL DEFAULT '0',
  `article_title` varchar(96) NOT NULL DEFAULT '0',
  `article_description` varchar(128) NOT NULL DEFAULT '0',
  `article_body` text NOT NULL,
  `article_author` int(11) NOT NULL DEFAULT 0,
  `article_promo_image` varchar(96) NOT NULL DEFAULT '',
  `article_allow_comments` enum('true','false') NOT NULL DEFAULT 'false',
  `article_time_created` int(11) NOT NULL DEFAULT 0,
  `article_hidden` enum('true','false') DEFAULT 'false',
  PRIMARY KEY (`id`),
  UNIQUE KEY `article_slug` (`article_slug`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.website_articles: ~0 rows (approximately)
DELETE FROM `website_articles`;
/*!40000 ALTER TABLE `website_articles` DISABLE KEYS */;
/*!40000 ALTER TABLE `website_articles` ENABLE KEYS */;

-- Dumping structure for table hylib.website_config
DROP TABLE IF EXISTS `website_config`;
CREATE TABLE IF NOT EXISTS `website_config` (
  `hotel_name` varchar(50) DEFAULT 'Comet',
  `hotel_slogan` varchar(96) DEFAULT 'Comet',
  `hotel_description` text DEFAULT NULL,
  `twitter_username` varchar(50) DEFAULT 'habbo',
  `facebook_username` varchar(50) DEFAULT 'habbo',
  `twitter_widget_id` int(11) DEFAULT 0,
  `player_default_credits` int(11) DEFAULT 0,
  `player_default_activity_points` int(11) DEFAULT 0,
  `player_default_vip_points` int(11) DEFAULT 0,
  `player_default_figure` varchar(128) DEFAULT '0',
  `player_default_motto` varchar(128) DEFAULT '0',
  `player_default_homeroom` varchar(128) DEFAULT '0',
  `game_host` varchar(50) DEFAULT NULL,
  `game_port` int(5) DEFAULT NULL,
  `game_client_swf` varchar(128) DEFAULT '',
  `game_client_variables` varchar(128) DEFAULT '',
  `game_client_texts` varchar(128) DEFAULT '',
  `game_client_productdata` varchar(128) DEFAULT '',
  `game_client_furnidata` varchar(128) DEFAULT '',
  `game_client_base` varchar(128) DEFAULT '',
  `game_client_banner` varchar(128) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.website_config: ~0 rows (approximately)
DELETE FROM `website_config`;
/*!40000 ALTER TABLE `website_config` DISABLE KEYS */;
INSERT INTO `website_config` (`hotel_name`, `hotel_slogan`, `hotel_description`, `twitter_username`, `facebook_username`, `twitter_widget_id`, `player_default_credits`, `player_default_activity_points`, `player_default_vip_points`, `player_default_figure`, `player_default_motto`, `player_default_homeroom`, `game_host`, `game_port`, `game_client_swf`, `game_client_variables`, `game_client_texts`, `game_client_productdata`, `game_client_furnidata`, `game_client_base`, `game_client_banner`) VALUES
	('Comet', 'Make friends, join the fun, get noticed!', 'Check into the world\'s largest virtual hotel for FREE! Meet and make friends, play games, chat with others, create your avatar, design rooms and more...', '', 'habbo', 0, 500, 500, 10, 'hr-828-31.lg-3058-64.hd-190-10.sh-3089-64.ch-255-82', 'I | Comet', '0', 'localhost', 30000, 'http://localhost/comet/swf/gordon/PRODUCTION-201709192204-203982672/Habbo_test1.swf', 'http://localhost/comet/swf/gamedata/external_variables.txt?', 'http://localhost/comet/swf/gamedata/external_flash_texts.txt?', 'http://localhost/comet/swf/gamedata/productdata.txt?', 'http://localhost/comet/swf/gamedata/furnidata_leet.xml', 'http://localhost/comet/swf/gordon/PRODUCTION-201709192204-203982672/', 'http://comet.dev/game/banner.png');
/*!40000 ALTER TABLE `website_config` ENABLE KEYS */;

-- Dumping structure for table hylib.website_gamedata_texts
DROP TABLE IF EXISTS `website_gamedata_texts`;
CREATE TABLE IF NOT EXISTS `website_gamedata_texts` (
  `key` varchar(255) NOT NULL,
  `value` text NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.website_gamedata_texts: ~0 rows (approximately)
DELETE FROM `website_gamedata_texts`;
/*!40000 ALTER TABLE `website_gamedata_texts` DISABLE KEYS */;
/*!40000 ALTER TABLE `website_gamedata_texts` ENABLE KEYS */;

-- Dumping structure for table hylib.website_gamedata_variables
DROP TABLE IF EXISTS `website_gamedata_variables`;
CREATE TABLE IF NOT EXISTS `website_gamedata_variables` (
  `key` varchar(50) NOT NULL,
  `value` text DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.website_gamedata_variables: ~0 rows (approximately)
DELETE FROM `website_gamedata_variables`;
/*!40000 ALTER TABLE `website_gamedata_variables` DISABLE KEYS */;
/*!40000 ALTER TABLE `website_gamedata_variables` ENABLE KEYS */;

-- Dumping structure for table hylib.website_news
DROP TABLE IF EXISTS `website_news`;
CREATE TABLE IF NOT EXISTS `website_news` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `slug` varchar(255) NOT NULL DEFAULT '',
  `title` varchar(96) NOT NULL DEFAULT '0',
  `description` varchar(128) NOT NULL DEFAULT '0',
  `body` text NOT NULL,
  `images` text NOT NULL,
  `author` int(11) NOT NULL DEFAULT 0,
  `header` varchar(96) NOT NULL DEFAULT '',
  `timestamp` int(11) NOT NULL DEFAULT 0,
  `hidden` enum('0','1') DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.website_news: 0 rows
DELETE FROM `website_news`;
/*!40000 ALTER TABLE `website_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `website_news` ENABLE KEYS */;

-- Dumping structure for table hylib.wordfilter
DROP TABLE IF EXISTS `wordfilter`;
CREATE TABLE IF NOT EXISTS `wordfilter` (
  `word` varchar(50) NOT NULL,
  `replacement` varchar(50) DEFAULT '*****'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.wordfilter: ~161 rows (approximately)
DELETE FROM `wordfilter`;
/*!40000 ALTER TABLE `wordfilter` DISABLE KEYS */;
INSERT INTO `wordfilter` (`word`, `replacement`) VALUES
	('bobbu', '*****'),
	('hobba', '*****'),
	('habboin', '*****'),
	('bubbu', '*****'),
	('hevvo', '*****'),
	('jebbo', '*****'),
	('habboid', '*****'),
	('habboles', '*****'),
	('hibbop', '*****'),
	('jotel', '*****'),
	('xabbo', '*****'),
	('nuevoholo', '*****'),
	('hebbi', '*****'),
	('nubbe', '*****'),
	('bobbahotel', '*****'),
	('hartico', '*****'),
	('hreino', '*****'),
	('kekoland', '*****'),
	('habcity', '*****'),
	('habpvp', '*****'),
	('hibb', '*****'),
	('.net', '*****'),
	('habbom', '*****'),
	('hekos', '*****'),
	('habboid', '*****'),
	('hibbo', '*****'),
	('argenishb', '*****'),
	('gabbu', '*****'),
	('habbys', '*****'),
	('hking', '*****'),
	('hblue', '*****'),
	('.org', '*****'),
	('hpixel', '*****'),
	('hotelreciencreado', '*****'),
	('holoreciencreado', '*****'),
	('bbinet', '*****'),
	('mcom', '*****'),
	('hmeek', '*****'),
	('obba', '*****'),
	('llobb', '*****'),
	('llebbum', '*****'),
	('llibbi', '*****'),
	('hal3', '*****'),
	('accedeanuestromundo', '*****'),
	('ingresayahcycredits', '*****'),
	('ubbu', '*****'),
	('buscamostaff', '*****'),
	('holodead', '*****'),
	('hpaisa', '*****'),
	('xfun', '*****'),
	('hline', '*****'),
	('habbux', '*****'),
	('hebbi', '*****'),
	('sebuscanstaff', '*****'),
	('jevvo', '*****'),
	('hinablu', '*****'),
	('gabb', '*****'),
	('habbospeed', '*****'),
	('habbodo', '*****'),
	('sefgsegg', '******'),
	('haddoz', '*****'),
	('hfantasy', '*****'),
	('habbox', '*****'),
	('fantasy', '*****'),
	('habbooh', '*****'),
	('haddi', '*****'),
	('habtum', '*****'),
	('kekow', '*****'),
	('habbex', '*****'),
	('hobbux', '*****'),
	('hebbo', '*****'),
	('javvo', '*****'),
	('xetros', '*****'),
	('hlandia', '*****'),
	('habbolandia', '*****'),
	('hlandia', '*****'),
	('hbatar', '*****'),
	('habbatar', '*****'),
	('habitah', '*****'),
	('habbed', '*****'),
	('cubbo', '*****'),
	('habbi', '*****'),
	('bangboom', '*****'),
	('habboi', '*****'),
	('kubbix ', '*****'),
	('habbox', '*****'),
	('habbia', '*****'),
	('habboi', '*****'),
	('habbo.id', '*****'),
	('habbex', '*****'),
	('cubbo', '*****'),
	('aerohabbo', '*****'),
	('aero', '*****'),
	('j4bb4', '*****'),
	('J4bb4', '*****'),
	('J4bba', '*****'),
	('J4bba', '*****'),
	('j4b4', '*****'),
	('jabva', '*****'),
	('javba', '*****'),
	('javva', '*****'),
	('4erohabbo', '*****'),
	('jobba', '*****'),
	('h0bb4', '*****'),
	('hobb4', '*****'),
	('h0bba', '*****'),
	('x/k/e/k/o/s', '*****'),
	('fantasy.lu', '*****'),
	('haddoz.net', '*****'),
	('xkekos.tv', '*****'),
	('haddos', '*****'),
	('hados', '*****'),
	('hadoz', '*****'),
	('jadoz', '*****'),
	('jados', '*****'),
	('xkks', '*****'),
	('cubo', '*****'),
	('hevvo', '*****'),
	('aero', '*****'),
	('habbooh', '*****'),
	('Hobba', '*****'),
	('H/O/B/BA', '*****'),
	('K/u/b/b/o', '*****'),
	('h,o,b,b,a', '*****'),
	('Ku/b/b/o', '*****'),
	('K/u/b/b/0', '*****'),
	('XÃ—kÃ—eÃ—ko*s', '*****'),
	('X/k/e/k/o/s', '*****'),
	('Entra ya crÃ©ditos gratis y mucho mas', '*****'),
	('H"a"b"b"o', '*****'),
	('H/b/b/o/XD', '*****'),
	('H/a/b/bo', '*****'),
	('H/a/b/b/o/XD', '*****'),
	('HabboXD', '*****'),
	('Hlatino', '*****'),
	('H/l/a/t/i/n/o', '*****'),
	('Entra ya y pasala en grande', '*****'),
	('Hartico', '*****'),
	('H/a/r/t/i/c/0', '*****'),
	('H4rtic0', '*****'),
	('H/a/r/t/i/c/o', '*****'),
	('Ha/r/ti/co', '*****'),
	('Entrayacreditosgratis', '*****'),
	('H.a.r.t.i.c.o', '*****'),
	('H.a.b.b.o.XD', '*****'),
	('H.l.a.t.i.n', '*****'),
	('J.a.b.b.a', '*****'),
	('H.o.b.b.i.x', '*****'),
	('J.a', '*****'),
	('Javvo', '*****'),
	('H*a*r*t*i*c*o', '*****'),
	('He.v.vo', '*****'),
	('J.a', '*****'),
	('Ja.v.v.o', '*****'),
	('A.e.r.o.h.a', '*****'),
	('Aero.habbo', '*****'),
	('A/e/r/o/h/a/b/b/o', '*****'),
	('H.a.b.b.o.id', '*****'),
	('Habbo*id', '*****'),
	('Entra ya y pasala en grande ', '*****'),
	('habbustar', '*****');
/*!40000 ALTER TABLE `wordfilter` ENABLE KEYS */;

-- Dumping structure for table hylib.wordfilter_characters
DROP TABLE IF EXISTS `wordfilter_characters`;
CREATE TABLE IF NOT EXISTS `wordfilter_characters` (
  `character` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  `replacement` varchar(255) NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.wordfilter_characters: 198 rows
DELETE FROM `wordfilter_characters`;
/*!40000 ALTER TABLE `wordfilter_characters` DISABLE KEYS */;
INSERT INTO `wordfilter_characters` (`character`, `replacement`) VALUES
	('Ãƒâ€ ', 'A'),
	('ÃƒÂ', 'A'),
	('Ãƒâ€š', 'A'),
	('ÃƒÆ’', 'A'),
	('Ãƒâ‚¬', 'A'),
	('Ã„â€š', 'A'),
	('Ã„â€ž', 'A'),
	('Ã‡Â¼', 'A'),
	('Ã„â‚¬', 'A'),
	('Ãƒâ€¦', 'A'),
	('Ãƒâ€ž', 'A'),
	('ÃƒÂ ', 'a'),
	('ÃƒÂ¡', 'a'),
	('ÃƒÂ¢', 'a'),
	('ÃƒÂ£', 'a'),
	('ÃƒÂ¤', 'a'),
	('ÃƒÂ¥', 'a'),
	('Ã„Æ’', 'a'),
	('Ã„Â', 'a'),
	('Ã„â€¦', 'a'),
	('ÃƒÂ¦', 'a'),
	('Ã‡Â½', 'a'),
	('ÃƒÅ¾', 'b'),
	('ÃƒÂ¾', 'b'),
	('ÃƒÅ¸', 's'),
	('Ãƒâ€¡', 'C'),
	('Ã„Å’', 'C'),
	('Ã„â€ ', 'C'),
	('Ã„Ë†', 'C'),
	('Ã„Å ', 'C'),
	('ÃƒÂ§', 'c'),
	('Ã„Â', 'c'),
	('Ã„â€¡', 'c'),
	('Ã„â€°', 'c'),
	('Ã„â€¹', 'c'),
	('Ã„Â', 'D'),
	('Ã„Å½', 'D'),
	('Ã„â€˜', 'd'),
	('Ã„Â', 'd'),
	('ÃƒË†', 'E'),
	('Ãƒâ€°', 'E'),
	('ÃƒÅ ', 'E'),
	('Ãƒâ€¹', 'E'),
	('Ã„â€', 'E'),
	('Ã„â€™', 'E'),
	('Ã„Ëœ', 'E'),
	('Ã„â€“', 'E'),
	('ÃƒÂ¨', 'e'),
	('ÃƒÂ©', 'e'),
	('ÃƒÂª', 'e'),
	('ÃƒÂ«', 'e'),
	('Ã„â€¢', 'e'),
	('Ã„â€œ', 'e'),
	('Ã„â„¢', 'e'),
	('Ã„â€”', 'e'),
	('Ã„Å“', 'G'),
	('Ã„Å¾', 'G'),
	('Ã„Â ', 'G'),
	('Ã„Â¢', 'G'),
	('Ã„Â', 'g'),
	('Ã„Å¸', 'g'),
	('Ã„Â¡', 'g'),
	('Ã„Â£', 'g'),
	('Ã„Â¤', 'H'),
	('Ã„Â¦', 'H'),
	('Ã„Â¥', 'h'),
	('Ã„Â§', 'h'),
	('ÃƒÅ’', 'I'),
	('ÃƒÂ', 'I'),
	('ÃƒÅ½', 'I'),
	('ÃƒÂ', 'I'),
	('Ã„Â°', 'I'),
	('Ã„Â¨', 'I'),
	('Ã„Âª', 'I'),
	('Ã„Â¬', 'I'),
	('Ã„Â®', 'I'),
	('ÃƒÂ¬', 'i'),
	('ÃƒÂ­', 'i'),
	('ÃƒÂ®', 'i'),
	('ÃƒÂ¯', 'i'),
	('Ã„Â¯', 'i'),
	('Ã„Â©', 'i'),
	('Ã„Â«', 'i'),
	('Ã„Â­', 'i'),
	('Ã„Â±', 'i'),
	('Ã„Â´', 'J'),
	('Ã„Âµ', 'j'),
	('Ã„Â¶', '?'),
	('Ã„Â·', 'k'),
	('Ã„Â¸', 'k'),
	('Ã„Â¹', 'L'),
	('Ã„Â»', 'L'),
	('Ã„Â½', 'L'),
	('Ã„Â¿', 'L'),
	('Ã…Â', 'L'),
	('Ã„Âº', 'l'),
	('Ã„Â¼', 'l'),
	('Ã„Â¾', 'l'),
	('Ã…â‚¬', 'l'),
	('Ã…â€š', 'l'),
	('Ãƒâ€˜', 'N'),
	('Ã…Æ’', 'N'),
	('Ã…â€¡', 'N'),
	('Ã…â€¦', 'N'),
	('Ã…Å ', 'N'),
	('ÃƒÂ±', 'n'),
	('Ã…â€ž', 'n'),
	('Ã…Ë†', 'n'),
	('Ã…â€ ', 'n'),
	('Ã…â€¹', 'n'),
	('Ã…â€°', 'n'),
	('Ãƒâ€™', 'O'),
	('Ãƒâ€œ', 'O'),
	('Ãƒâ€', 'O'),
	('Ãƒâ€“', 'O'),
	('Ãƒâ€¢', 'O'),
	('ÃƒËœ', 'O'),
	('Ã…Å’', 'O'),
	('Ã…Å½', 'O'),
	('Ã…Â', 'O'),
	('Ã…â€™', 'o'),
	('ÃƒÂ²', 'o'),
	('ÃƒÂ³', 'o'),
	('ÃƒÂ´', 'o'),
	('ÃƒÂµ', 'o'),
	('ÃƒÂ¶', 'o'),
	('ÃƒÂ¸', 'o'),
	('Ã…Â', 'o'),
	('Ã…Â', 'o'),
	('Ã…â€˜', 'o'),
	('Ã…â€œ', 'o'),
	('ÃƒÂ°', 'o'),
	('Ã…â€', 'R'),
	('Ã…Ëœ', 'R'),
	('Ã…â€¢', 'r'),
	('Ã…â„¢', 'r'),
	('Ã…â€”', 'r'),
	('Ã…Â ', 'S'),
	('Ã…Å“', 'S'),
	('Ã…Å¡', 'S'),
	('Ã…Å¾', 'S'),
	('Ã…Â¡', 's'),
	('Ã…Â¤', 'T'),
	('Ã…â€º', 's'),
	('Ã…Â', 's'),
	('Ã…Å¸', 's'),
	('Ã…Â¦', 'T'),
	('Ã…Â¢', 'T'),
	('Ã…Â§', 't'),
	('Ã…Â£', 't'),
	('Ã…Â¥', 't'),
	('Ãƒâ„¢', 'U'),
	('ÃƒÅ¡', 'U'),
	('Ãƒâ€º', 'U'),
	('ÃƒÅ“', 'U'),
	('Ã…Â¨', 'U'),
	('Ã…Âª', 'U'),
	('Ã…Â¬', 'U'),
	('Ã…Â®', 'U'),
	('Ã…Â°', 'U'),
	('Ã…Â²', 'U'),
	('ÃƒÂ¹', 'u'),
	('ÃƒÂº', 'u'),
	('ÃƒÂ»', 'u'),
	('ÃƒÂ¼', 'u'),
	('Ã…Â©', 'u'),
	('Ã…Â«', 'u'),
	('Ã…Â­', 'u'),
	('Ã…Â¯', 'u'),
	('Ã…Â³', 'W'),
	('Ã…Â´', 'W'),
	('Ã¡Âºâ‚¬', 'W'),
	('Ã¡Âºâ€š', 'W'),
	('Ã¡Âºâ€ž', 'W'),
	('Ã…Âµ', 'w'),
	('Ã¡ÂºÂ', 'w'),
	('Ã¡ÂºÆ’', 'w'),
	('Ã¡Âºâ€¦', 'w'),
	('ÃƒÂ', 'Y'),
	('Ã…Â¸', 'Y'),
	('Ã…Â¶', 'Y'),
	('ÃƒÂ½', 'y'),
	('ÃƒÂ¿', 'y'),
	('Ã…Â·', 'y'),
	('Ã…Â½', 'Z'),
	('Ã…Â¹', 'Z'),
	('Ã…Â»', 'Z'),
	('Ã…Â¾', 'z'),
	('Ã…Âº', 'z'),
	('Ã…Â¼', 'z'),
	('Ã¢â‚¬Å“', '\\'),
	('Ã¢â‚¬Â', '\\'),
	('Ã¢â‚¬Â¦', '.'),
	('Ã¢â‚¬â€', '-'),
	('Ã¢â‚¬â€œ', '-'),
	('Ã‚Â¿', '?'),
	('Ã‚Â¡', '!'),
	('Ã©', 'e');
/*!40000 ALTER TABLE `wordfilter_characters` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
-- Dumping structure for table hylib.cms_alerts
DROP TABLE IF EXISTS `cms_alerts`;
CREATE TABLE IF NOT EXISTS `cms_alerts` (
  `id` varchar(255) NOT NULL,
  `alert` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- Dumping data for table hylib.cms_alerts: ~0 rows (approximately)
DELETE FROM `cms_alerts`;
/*!40000 ALTER TABLE `cms_alerts` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_alerts` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_catalog_categories
DROP TABLE IF EXISTS `cms_catalog_categories`;
CREATE TABLE IF NOT EXISTS `cms_catalog_categories` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `image` varchar(250) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.cms_catalog_categories: ~2 rows (approximately)
DELETE FROM `cms_catalog_categories`;
/*!40000 ALTER TABLE `cms_catalog_categories` DISABLE KEYS */;
INSERT INTO `cms_catalog_categories` (`id`, `name`, `image`) VALUES
	(2, 'Rares', 'https://2.bp.blogspot.com/-rk8Ft_JC2C4/XZVaFikKf1I/AAAAAAABWaU/Jz2Vl56Cqts6bc0u08KlZvbepGeIGeJ5gCKgBGAsYHg/s1600/Icon117.png'),
	(3, 'Megas', 'https://4.bp.blogspot.com/-RUg63sLQxcU/XZVaFmcweeI/AAAAAAABWaU/R8Pjn-AxMrkFH96giKNb2PCA4t3n2Q71QCKgBGAsYHg/s1600/Icon229.png');
/*!40000 ALTER TABLE `cms_catalog_categories` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_catalog_items
DROP TABLE IF EXISTS `cms_catalog_items`;
CREATE TABLE IF NOT EXISTS `cms_catalog_items` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `image` varchar(250) NOT NULL,
  `category` int(250) NOT NULL,
  `price_diamonds` int(250) NOT NULL,
  `price_th` int(250) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.cms_catalog_items: ~8 rows (approximately)
DELETE FROM `cms_catalog_items`;
/*!40000 ALTER TABLE `cms_catalog_items` DISABLE KEYS */;
INSERT INTO `cms_catalog_items` (`id`, `name`, `image`, `category`, `price_diamonds`, `price_th`) VALUES
	(25, 'Throne', 'https://i.imgur.com/0etIpwi.png', 2, 100, 0),
	(29, 'El CÃ©sped', 'https://i.imgur.com/vowWHBK.png', 2, 100, 0),
	(30, 'Alfombra Lunar', 'https://i.imgur.com/IXP5KRF.png', 2, 100, 0),
	(31, 'Fontana Gris', 'https://i.imgur.com/x0Q73kx.png', 2, 300, 0),
	(33, 'Fontana Roja', 'https://i.ibb.co/nn531HF/1508261391.png', 2, 200, 0),
	(34, 'DragÃ³n de fuego azul', 'https://catalogo.hobba.tv/img/furnis/mega_rares/dragon%20de%20fuego%20azul.gif', 2, 0, 6),
	(36, 'Koala', 'https://catalogo.hobba.tv/img/furnis/raros/koala.gif', 2, 0, 3),
	(37, 'Humareda Negra', 'https://catalogo.hobba.tv/img/furnis/raros/humareda%20negra.gif', 2, 0, 2);
/*!40000 ALTER TABLE `cms_catalog_items` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_clients
DROP TABLE IF EXISTS `cms_clients`;
CREATE TABLE IF NOT EXISTS `cms_clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `version` enum('0','24','60') DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_clients: ~0 rows (approximately)
DELETE FROM `cms_clients`;
/*!40000 ALTER TABLE `cms_clients` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_clients` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_comments
DROP TABLE IF EXISTS `cms_comments`;
CREATE TABLE IF NOT EXISTS `cms_comments` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL,
  `value` text DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `timestamp` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_comments: ~0 rows (approximately)
DELETE FROM `cms_comments`;
/*!40000 ALTER TABLE `cms_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_comments` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_errands
DROP TABLE IF EXISTS `cms_errands`;
CREATE TABLE IF NOT EXISTS `cms_errands` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_from_id` varchar(11) DEFAULT NULL,
  `user_to_id` varchar(11) DEFAULT NULL,
  `data` int(11) DEFAULT NULL,
  `value` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_errands: ~0 rows (approximately)
DELETE FROM `cms_errands`;
/*!40000 ALTER TABLE `cms_errands` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_errands` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_events
DROP TABLE IF EXISTS `cms_events`;
CREATE TABLE IF NOT EXISTS `cms_events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(25) DEFAULT NULL,
  `description` varchar(40) DEFAULT NULL,
  `type` enum('atividade','evento') DEFAULT NULL,
  `link` varchar(500) DEFAULT NULL,
  `image` varchar(300) DEFAULT NULL,
  `timestamp` int(11) DEFAULT NULL,
  `timestamp_expire` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_events: ~0 rows (approximately)
DELETE FROM `cms_events`;
/*!40000 ALTER TABLE `cms_events` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_events` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_forms
DROP TABLE IF EXISTS `cms_forms`;
CREATE TABLE IF NOT EXISTS `cms_forms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `form` int(11) DEFAULT 0,
  `usernames` text DEFAULT NULL,
  `expire_timestamp` int(11) DEFAULT NULL,
  `link` text DEFAULT NULL,
  `message` text DEFAULT NULL,
  `status` enum('enabled','disabled') DEFAULT 'disabled',
  `form_code` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_forms: ~0 rows (approximately)
DELETE FROM `cms_forms`;
/*!40000 ALTER TABLE `cms_forms` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_forms` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_news
DROP TABLE IF EXISTS `cms_news`;
CREATE TABLE IF NOT EXISTS `cms_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL DEFAULT '0',
  `shortstory` text NOT NULL,
  `longstory` text NOT NULL,
  `author` varchar(100) NOT NULL DEFAULT 'Tom',
  `date` int(11) NOT NULL DEFAULT 0,
  `expire_timestamp` int(11) NOT NULL DEFAULT 0,
  `form` int(11) NOT NULL DEFAULT 0,
  `rascunho` enum('0','1') NOT NULL DEFAULT '0',
  `comments` enum('enabled','disabled') NOT NULL DEFAULT 'enabled',
  `type` varchar(100) NOT NULL DEFAULT '1',
  `form_link` varchar(100) NOT NULL DEFAULT '1',
  `roomid` varchar(100) NOT NULL DEFAULT '1',
  `updated` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_news: ~0 rows (approximately)
DELETE FROM `cms_news`;
/*!40000 ALTER TABLE `cms_news` DISABLE KEYS */;
INSERT INTO `cms_news` (`id`, `title`, `image`, `shortstory`, `longstory`, `author`, `date`, `expire_timestamp`, `form`, `rascunho`, `comments`, `type`, `form_link`, `roomid`, `updated`) VALUES
	(2, 'Lavvos Level', 'https://i.imgur.com/8wueJCd.png', 'El renacimiento ha llegado y con el , una enorme calidad de servidor y eventos.', 'a', '1', 1672883320, 0, 1, '0', 'enabled', '1', '1', '1', '0');
/*!40000 ALTER TABLE `cms_news` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_news_comments
DROP TABLE IF EXISTS `cms_news_comments`;
CREATE TABLE IF NOT EXISTS `cms_news_comments` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `user_id` int(250) NOT NULL,
  `notice_id` int(250) NOT NULL,
  `comment` text COLLATE utf8mb4_spanish_ci NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.cms_news_comments: ~0 rows (approximately)
DELETE FROM `cms_news_comments`;
/*!40000 ALTER TABLE `cms_news_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_news_comments` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_news_like
DROP TABLE IF EXISTS `cms_news_like`;
CREATE TABLE IF NOT EXISTS `cms_news_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(255) DEFAULT NULL,
  `newsid` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_news_like: ~0 rows (approximately)
DELETE FROM `cms_news_like`;
/*!40000 ALTER TABLE `cms_news_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_news_like` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_news_message
DROP TABLE IF EXISTS `cms_news_message`;
CREATE TABLE IF NOT EXISTS `cms_news_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` int(11) NOT NULL DEFAULT 0,
  `buggid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `message` varchar(250) DEFAULT NULL,
  `hash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_news_message: ~0 rows (approximately)
DELETE FROM `cms_news_message`;
/*!40000 ALTER TABLE `cms_news_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_news_message` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_panel_logs
DROP TABLE IF EXISTS `cms_panel_logs`;
CREATE TABLE IF NOT EXISTS `cms_panel_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5147 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table hylib.cms_panel_logs: ~12 rows (approximately)
DELETE FROM `cms_panel_logs`;
/*!40000 ALTER TABLE `cms_panel_logs` DISABLE KEYS */;
INSERT INTO `cms_panel_logs` (`id`, `label`) VALUES
	(5135, 'login;Key;1672988623;85.215.164.123;success'),
	(5136, 'login;Key;1673323039;2a0c:5a80:1406:3800:d8de:7fab:80c3:51e8;success'),
	(5137, 'login;Key;1673920055;2a0c:5a80:1011:9900:6c28:1699:69be:af1;success'),
	(5138, 'pin-created;13;1673920130;2a0c:5a80:1011:9900:6c28:1699:69be:af1;success'),
	(5139, 'login;Angelu;1673920148;187.161.189.195;success'),
	(5140, 'update-own-rank;Angelu;1673920325;187.161.189.195;success'),
	(5141, 'login;Angelu;1673978123;200.63.44.167;success'),
	(5142, 'login;Angelu;1674002648;187.161.189.195;success'),
	(5143, 'update-rank-user;byAngelu;to:Station;1674002688;187.161.189.195;success'),
	(5144, 'login;Key;1674053356;2a0c:5a80:1011:9900:c1c5:bf7b:14fd:cdab;success'),
	(5145, 'pin-created;13;1674053367;2a0c:5a80:1011:9900:c1c5:bf7b:14fd:cdab;success'),
	(5146, 'login;Angelu;1674067461;200.63.45.8;success');
/*!40000 ALTER TABLE `cms_panel_logs` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_post_comments
DROP TABLE IF EXISTS `cms_post_comments`;
CREATE TABLE IF NOT EXISTS `cms_post_comments` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `type` enum('undefined','article','errand') NOT NULL DEFAULT 'undefined',
  `post_id` int(11) DEFAULT 0,
  `value` text DEFAULT NULL,
  `author_id` int(11) DEFAULT 0,
  `to_user_id` int(11) NOT NULL DEFAULT 0,
  `timestamp` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_post_comments: ~5 rows (approximately)
DELETE FROM `cms_post_comments`;
/*!40000 ALTER TABLE `cms_post_comments` DISABLE KEYS */;
INSERT INTO `cms_post_comments` (`id`, `type`, `post_id`, `value`, `author_id`, `to_user_id`, `timestamp`) VALUES
	(7, 'article', 2, 'test', 1, 0, 0),
	(8, 'article', 2, 'test2', 1, 0, 1672897115),
	(9, 'article', 2, 'ola', 9, 0, 1672963053),
	(10, 'article', 2, 'Holi', 5, 0, 1673046331),
	(11, 'article', 2, 'a', 15, 0, 1673222743);
/*!40000 ALTER TABLE `cms_post_comments` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_post_forms
DROP TABLE IF EXISTS `cms_post_forms`;
CREATE TABLE IF NOT EXISTS `cms_post_forms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('undefined','article','radio') NOT NULL,
  `post_id` int(11) DEFAULT 0,
  `label` text DEFAULT NULL,
  `user_id` int(11) DEFAULT 0,
  `timestamp` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_post_forms: ~0 rows (approximately)
DELETE FROM `cms_post_forms`;
/*!40000 ALTER TABLE `cms_post_forms` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_post_forms` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_post_reaction
DROP TABLE IF EXISTS `cms_post_reaction`;
CREATE TABLE IF NOT EXISTS `cms_post_reaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('undefined','article') NOT NULL DEFAULT 'undefined',
  `post_id` int(11) DEFAULT 0,
  `user_id` int(11) DEFAULT 0,
  `state` enum('undefined','like','deslike') NOT NULL DEFAULT 'undefined',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_post_reaction: ~3 rows (approximately)
DELETE FROM `cms_post_reaction`;
/*!40000 ALTER TABLE `cms_post_reaction` DISABLE KEYS */;
INSERT INTO `cms_post_reaction` (`id`, `type`, `post_id`, `user_id`, `state`) VALUES
	(18, 'article', 2, 1, 'like'),
	(19, 'article', 2, 8, 'like'),
	(20, 'article', 2, 5, 'undefined');
/*!40000 ALTER TABLE `cms_post_reaction` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_profile_comments
DROP TABLE IF EXISTS `cms_profile_comments`;
CREATE TABLE IF NOT EXISTS `cms_profile_comments` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `profile_id` int(250) NOT NULL,
  `user_id` int(250) NOT NULL,
  `comment` text COLLATE utf8mb4_spanish_ci NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.cms_profile_comments: ~0 rows (approximately)
DELETE FROM `cms_profile_comments`;
/*!40000 ALTER TABLE `cms_profile_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_profile_comments` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_profile_posts
DROP TABLE IF EXISTS `cms_profile_posts`;
CREATE TABLE IF NOT EXISTS `cms_profile_posts` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `user_id` int(250) NOT NULL,
  `post` text COLLATE utf8mb4_spanish_ci NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.cms_profile_posts: ~0 rows (approximately)
DELETE FROM `cms_profile_posts`;
/*!40000 ALTER TABLE `cms_profile_posts` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_profile_posts` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_rarevalues
DROP TABLE IF EXISTS `cms_rarevalues`;
CREATE TABLE IF NOT EXISTS `cms_rarevalues` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(25) DEFAULT '',
  `item_image` varchar(30) DEFAULT '',
  `item_cost` int(11) DEFAULT 0,
  `item_status` enum('up','down','same') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_rarevalues: ~0 rows (approximately)
DELETE FROM `cms_rarevalues`;
/*!40000 ALTER TABLE `cms_rarevalues` DISABLE KEYS */;
INSERT INTO `cms_rarevalues` (`id`, `item_name`, `item_image`, `item_cost`, `item_status`) VALUES
	(1, 'Drako Rosa', '', 15, 'same');
/*!40000 ALTER TABLE `cms_rarevalues` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_reactions
DROP TABLE IF EXISTS `cms_reactions`;
CREATE TABLE IF NOT EXISTS `cms_reactions` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `state` enum('0','1','2') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_reactions: ~0 rows (approximately)
DELETE FROM `cms_reactions`;
/*!40000 ALTER TABLE `cms_reactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_reactions` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_referred
DROP TABLE IF EXISTS `cms_referred`;
CREATE TABLE IF NOT EXISTS `cms_referred` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `user_id` int(250) NOT NULL,
  `referred_id` int(250) NOT NULL,
  `referred_ip` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table hylib.cms_referred: ~0 rows (approximately)
DELETE FROM `cms_referred`;
/*!40000 ALTER TABLE `cms_referred` DISABLE KEYS */;
INSERT INTO `cms_referred` (`id`, `user_id`, `referred_id`, `referred_ip`, `timestamp`) VALUES
	(1, 1, 154, '77.225.129.82', '2021-12-12 05:25:00');
/*!40000 ALTER TABLE `cms_referred` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_settings
DROP TABLE IF EXISTS `cms_settings`;
CREATE TABLE IF NOT EXISTS `cms_settings` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `hotelname` varchar(255) NOT NULL DEFAULT 'Habbo',
  `site` varchar(255) NOT NULL DEFAULT 'http://localhost/',
  `host` varchar(30) DEFAULT NULL,
  `port` int(10) DEFAULT NULL,
  `external_variables` text DEFAULT NULL,
  `external_override_variables` text DEFAULT NULL,
  `external_flash_texts` text DEFAULT NULL,
  `external_flash_override_texts` text DEFAULT NULL,
  `figuredata` text DEFAULT NULL,
  `figuremap` text DEFAULT NULL,
  `furnidata` text DEFAULT NULL,
  `flash_client_url` text DEFAULT NULL,
  `productdata` text DEFAULT NULL,
  `avatarimage` varchar(255) NOT NULL DEFAULT 'http://www.habbo.fr/habbo-imaging/',
  `maintenance` set('enabled','disabled') NOT NULL DEFAULT 'disabled',
  `facebook` text NOT NULL,
  `twitter` text NOT NULL,
  `discord` text NOT NULL,
  `application` text DEFAULT NULL,
  `recaptcha` varchar(255) NOT NULL,
  `credits` varchar(255) NOT NULL DEFAULT '5000',
  `diamonds` int(11) NOT NULL DEFAULT 0,
  `duckets` int(11) NOT NULL DEFAULT 0,
  `motto` text DEFAULT NULL,
  `rank` int(11) NOT NULL DEFAULT 1,
  `figure` varchar(300) DEFAULT NULL,
  `cms_name` text DEFAULT NULL,
  `cms_version` text DEFAULT NULL,
  `cms_developers` text DEFAULT NULL,
  `force_room` enum('0','1') NOT NULL DEFAULT '0',
  `force_room_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_settings: 1 rows
DELETE FROM `cms_settings`;
/*!40000 ALTER TABLE `cms_settings` DISABLE KEYS */;
INSERT INTO `cms_settings` (`id`, `hotelname`, `site`, `host`, `port`, `external_variables`, `external_override_variables`, `external_flash_texts`, `external_flash_override_texts`, `figuredata`, `figuremap`, `furnidata`, `flash_client_url`, `productdata`, `avatarimage`, `maintenance`, `facebook`, `twitter`, `discord`, `application`, `recaptcha`, `credits`, `diamonds`, `duckets`, `motto`, `rank`, `figure`, `cms_name`, `cms_version`, `cms_developers`, `force_room`, `force_room_id`) VALUES
	(1, 'Lavvos', 'https://lavvos.eu', '85.215.164.123', 30000, 'https://lavvos.eu/swfs/gamedata/external_variable.txt', 'https://lavvos.eu/swfs/gamedata/external_override_variables.txt', 'https://lavvos.eu/swfs/gamedata/external_flash_texts.txt', 'https://lavvos.eu/swfs/gamedata/external_flash_override_texts.txt', 'https://lavvos.eu/swfs/gamedata/figuredata.xml', 'https://lavvos.eu/swfs/gamedata/figuremap.xml', 'https://lavvos.eu/swfs/gamedata/furnidata.xml', '', 'https://lavvos.eu/swfs/gamedata/productdata.txt', 'https://habbo-imaging.lavvos.eu/?', 'enabled', 'https://www.facebook.com/oHabboPTBR', 'https://twitter.com/', 'https://discord.gg/mVQJGU9', '', '', '10000', 0, 0, 'Lavvos.us', 1, 'ea-990000128-153640-153640.wa-990000069-94-85.ch-877-81-1408.hd-180-1.ha-990000132-63-153640.he-990000148-153640.sh-987462842-81.ca-990000126-153640-153640.fa-990000146-153640.hr-990000131-39-158639.lg-275-81.cc-987462858-153638', 'Hylib', '0.0.3', 'Wake, Laxus e Dut', '0', 21);
/*!40000 ALTER TABLE `cms_settings` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_tweets
DROP TABLE IF EXISTS `cms_tweets`;
CREATE TABLE IF NOT EXISTS `cms_tweets` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `user_id` int(250) NOT NULL,
  `tweet` varchar(250) NOT NULL,
  `date` int(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table hylib.cms_tweets: ~0 rows (approximately)
DELETE FROM `cms_tweets`;
/*!40000 ALTER TABLE `cms_tweets` DISABLE KEYS */;
INSERT INTO `cms_tweets` (`id`, `user_id`, `tweet`, `date`) VALUES
	(1, 2, 'xdddd', 1672455749);
/*!40000 ALTER TABLE `cms_tweets` ENABLE KEYS */;

-- Dumping structure for table hylib.cms_wordfilter
DROP TABLE IF EXISTS `cms_wordfilter`;
CREATE TABLE IF NOT EXISTS `cms_wordfilter` (
  `word` text DEFAULT NULL,
  `replacement` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hylib.cms_wordfilter: ~0 rows (approximately)
DELETE FROM `cms_wordfilter`;
/*!40000 ALTER TABLE `cms_wordfilter` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_wordfilter` ENABLE KEYS */;

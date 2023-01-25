package com.cometproject.server.game.rooms.objects.entities.types.ai.bots;

import com.cometproject.api.game.quests.QuestType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.entities.types.ai.AbstractBotAI;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.permissions.FloodFilterMessageComposer;

public class QuestAI extends AbstractBotAI {
    public QuestAI(RoomEntity entity) {
        super(entity);
    }

    @Override
    public boolean onPlayerEnter(PlayerEntity playerEntity){
        if(playerEntity != null){
            if(playerEntity.getRoom().getData().getName().contains("Valhalla")){
                playerEntity.getPlayer().getQuests().progressQuest(QuestType.VAL21_4, 0);
            }
        }
        return false;
    }
    @Override
    public boolean onTalk(PlayerEntity entity, String message) {
        String triggerMessage = message.replaceAll("\\s", "");
        String answer = "";
        String genderTermination = entity.getGender().equals("F") ? "a" : "o";
        String username = entity.getUsername();

        for (Quests quest : keyword) {
            if (quest.getTrigger().equals(triggerMessage)) {
                if (entity.getPlayer().getRoomFloodTime() >= 1) {
                    entity.getPlayer().getSession().send(new FloodFilterMessageComposer(entity.getPlayer().getRoomFloodTime()));
                    return false;
                }

                if (entity.getPosition().distanceTo(this.getEntity().getPosition()) >= 4) {
                    this.getEntity().getRoom().getEntities().broadcastMessage(new TalkMessageComposer(this.getEntity().getId(), Locale.get("bots.chat.tooFar").replace("%username%", entity.getUsername()), RoomManager.getInstance().getEmotions().getEmotion(":("), 2));
                    return false;
                }

                if (entity.getPlayer().getData().getQuestId() != quest.getQuestId())
                    continue;

                switch (quest.getQuestId()) {
                    case 80:
                        if (this.getBotEntity().getData().getUsername().equals("Hestia")) {
                            answer = "¡Genial! Pero antes debes prepararte para el vuelo, %username%.";
                            entity.getPlayer().getQuests().progressQuest(QuestType.VAL21_2, 0);
                        }
                        break;

                    case 83:
                        if (this.getBotEntity().getData().getUsername().contains("Sim")) {
                            if (!entity.hasAttribute("hospital_step1")) {
                                answer = "¡Hombre! ¿Tú también vienes a revistarte para poder viajar, %username%?";
                                entity.setAttribute("hospital_step1", true);
                                break;
                            }

                            if (!entity.hasAttribute("hospital_step2")) {
                                answer = "¡Con ese estado de salud es imposible viajar, un médico debe curarte antes de poder volar!";
                                entity.setAttribute("hospital_step2", true);
                            }
                        }
                        break;

                    case 84:
                        if (this.getBotEntity().getData().getUsername().contains("Lauper")) {
                            if (!entity.hasAttribute("police1")) {
                                answer = "¡Hola, bienvenid" + genderTermination + " al Centro Policial Syn! ¿Qué necesitas, %username%?";
                                entity.setAttribute("police1", true);
                                this.speak(answer, username);
                                return false;
                            }

                            if (!entity.hasAttribute("police2")) {
                                answer = "¡Ah perfecto! Necesito hacerte unas preguntas... ¿A qué te dedicas en HTHOR?";
                                entity.setAttribute("police2", true);

                                this.speak(answer, username);
                                return false;
                            }
                        }
                        break;

                    case 85:
                        if (this.getBotEntity().getData().getUsername().contains("Recepcionista")) {
                            if (!entity.hasAttribute("airport1") && triggerMessage.contains("París")) {
                                answer = "Oh, París es una bellísima ciudad. ¿Cuántas maletas va a llevar?";
                                entity.setAttribute("airport1", true);
                                this.speak(answer, username);
                                return false;
                            }

                            if (!entity.hasAttribute("airport3") && entity.hasAttribute("airport2")) {
                                answer = "¡Sí eso, la Torre Eiffel!<br><br>Bueno, no te ocupo más,<br>te dejo la tarjeta de abordaje para el vuelo.<br><br>" +
                                        "Despegará el próximo <b>Sábado 30 de Febrero a las 23:30</b> Hora Española.<br><br>" +
                                        "¡Recuerda estar puntual para abordar!<br>" +
                                        "<img src='https://swf.hthor.com/swf/c_images/Quests/val_img.png'/><br><br><br><br><br>" +
                                        "¡Disfruta del vuelo y ponte guap" + genderTermination +" para la ocasión, %username%!";

                                this.speak(answer, username);

                                entity.getPlayer().getQuests().progressQuest(QuestType.VAL21_7, 0);
                                return false;
                            }
                        }
                        break;
                }
            } else {
                switch (entity.getPlayer().getData().getQuestId()) {
                    case 79:
                        if (this.getBotEntity().getData().getUsername().equals("Hestia")) {
                            answer = "¿Ah, que quieres venir conmigo?";
                            entity.getPlayer().getQuests().progressQuest(QuestType.VAL21_1, 0);
                        }
                        break;

                    case 84:
                        if (this.getBotEntity().getData().getUsername().contains("Lauper") && entity.hasAttribute("police2")) {
                            if (!entity.hasAttribute("police3")) {
                                answer = "Mmm está bien, %username%. ¿Está casad" + genderTermination + " con algún usuario o tiene pareja?";
                                entity.setAttribute("police3", true);
                                this.speak(answer, username);
                                return false;
                            }

                            if (!entity.hasAttribute("police4")) {
                                answer = "¿Ha viajado últimamente a otro país?";
                                entity.setAttribute("police4", true);
                                this.speak(answer, username);
                                return false;
                            }

                            if (!entity.hasAttribute("police5")) {
                                answer = "Perfecto. Por último... ¿cuál es el motivo de su viaje?";
                                entity.setAttribute("police5", true);
                                this.speak(answer, username);
                                return false;
                            }

                            if (entity.hasAttribute("police5") && !entity.hasAttribute("police6")) {
                                answer = "Pues eso es todo %username%, aquí tiene su pasaporte. ¡Tenga buen día!";
                                entity.setAttribute("police6", true);
                                entity.getPlayer().getQuests().progressQuest(QuestType.VAL21_6, 0);
                                this.speak(answer, username);
                                return false;
                            }
                        }
                        break;

                    case 85:
                        if (this.getBotEntity().getData().getUsername().contains("Recepcionista") && entity.hasAttribute("airport1")) {
                            if (!entity.hasAttribute("airport2") && entity.hasAttribute("airport1")) {
                                answer = "Está bien, el otro día hablando con mi marido me dijo que allí podría visitar un gran monumento… No recuerdo cómo se llamaba… ¿Te acuerdas?";
                                entity.setAttribute("airport2", true);
                                this.speak(answer, username);
                                return false;
                            }
                        }
                        break;
                }
            }
        }

        if (!answer.isEmpty()) {
            this.getEntity().getRoom().getEntities().broadcastMessage(
                    new TalkMessageComposer(this.getEntity().getId(), answer.replace("%username%", entity.getUsername()),
                            RoomManager.getInstance().getEmotions().getEmotion(":)"), 2));
        }

        return false;
    }

    public static final Quests[] keyword = {
            new Quests("Sí", 80),
            new Quests("Hola", 83),
            new Quests("Sí", 83),
            new Quests("Hola", 84),
            new Quests("pasaporte", 84),
            new Quests("París", 85),
            new Quests("TorreEiffel", 85)
    };

    private void speak(String answer, String username){
        this.getEntity().getRoom().getEntities().broadcastMessage(
                new TalkMessageComposer(this.getEntity().getId(), answer.replace("%username%", username),
                        RoomManager.getInstance().getEmotions().getEmotion(":)"), 2));
    }

    public static class Quests {
        private final String trigger;
        private final int questId;

        private Quests(String trigger, int questId) {
            this.trigger = trigger;
            this.questId = questId;
        }

        public String getTrigger() {
            return trigger;
        }

        public int getQuestId() {
            return questId;
        }
    }
}

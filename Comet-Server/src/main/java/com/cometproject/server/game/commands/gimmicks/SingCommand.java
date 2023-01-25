package com.cometproject.server.game.commands.gimmicks;

import com.cometproject.api.game.quests.QuestType;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.items.RoomItem;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.utilities.RandomUtil;
import com.cometproject.storage.api.data.rooms.RoomData;

import java.util.ArrayList;

public class SingCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        RoomData data = (RoomData)client.getPlayer().getEntity().getRoom().getData();
        if(!data.funCommands){
            SingCommand.sendNotif("Los FunCommands están desactivados en esta sala.", client);
            return;
        }
        String[] songs = new String[]{
                "Si tu novio te termina, yo te tengo la mejor medicina. Si tu novio te termina mezcla guaro con tequila",
                "Mejor dime que me extrañas. Que estas loca de hacerlo conmigo. Baby yo se que tú me quieres",
                "Perdí, jugué con una diabla que es experta en esos juegos del amor.",
                "Mami, yo te amo hasta el infinito, sólido como un meteorito, lo que siento jamás lo van a entender.",
                "Yo no tengo gripe, yo lo que tengo e' to', Mera, wo (Wo-wo-wo)",
                "Si hay sol, hay playa. Si hay playa, hay alcohol. Si hay alcohol, hay sexo. Si es contigo, mejor",
                "Si tu novio no te mama el culo, pa' eso que no mame",
                "Mala, tú eres mala, así me gustas, así me encantas."
        };

        int rand = RandomUtil.getRandomInt(0,7);

        String messagePlayer = " * " + songs[rand] +" *";
        client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), messagePlayer, 11));
        client.getPlayer().getEntity().applyEffect(new PlayerEffect(16, 18));
    }

    @Override
    public String getPermission() {
        return "sing_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault(null, "");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.sing.description");
    }
}


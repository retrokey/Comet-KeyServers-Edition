package com.cometproject.server.game.commands.vip;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.permissions.PermissionsManager;
import com.cometproject.server.game.permissions.types.EffectPermission;
import com.cometproject.server.game.permissions.types.OverrideCommandPermission;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.components.games.GameTeam;
import com.cometproject.server.network.sessions.Session;


public class EffectCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length != 1) {
            sendNotif(Locale.getOrDefault("command.enable.none", "To get a effect type :effect %number%"), client);
            return;
        }

        if(params[0].equals("779")){
            sendNotif("No puedes usar estos enables", client);
            return;
        }

        String[] vipEnables = { "102", "614", "615", "616", "617", "618", "619", "620", "621", "622", "623", "624", "625", "626", "627", "628", "629", "630", "631", "632", "633", "634" };

        boolean isVipEnable = false;
        for(String vipEnable : vipEnables){
            if(vipEnable.equals(params[0])){
                isVipEnable = true;
            }
        }

        if(client.getPlayer().getData().getRank() < 2 && isVipEnable){
            sendNotif("Este enable es solo para VIPs", client);
            return;
        }

        if(params[0].equals("YT")){
            switch (client.getPlayer().getData().getUsername()){
                case "Ruthy":
                case "Custom":
                case "SER0":
                case "Incrementum":
                case "Nandito123":
                case "Blacky":
                case "Fear":
                    PlayerEntity entity = client.getPlayer().getEntity();
                    entity.applyEffect(new PlayerEffect(823, 0));
                    break;
                default:
                    return;
            }
        }

        if(params[0].equals("VIP")){
            if(client.getPlayer().getSubscription().isValid()){
                PlayerEntity entity = client.getPlayer().getEntity();
                entity.applyEffect(new PlayerEffect(824, 0));
            }
            return;
        }

        try {
            int effectId = Integer.parseInt(params[0]);
            PlayerEntity entity = client.getPlayer().getEntity();

            for(EffectPermission effect : PermissionsManager.getInstance().getEffectsOverride().values()) {
                if (effect.getPlayerId() == client.getPlayer().getId() && effectId == effect.getEffectId() && effect.isEnabled()) {
                    if (entity.getCurrentEffect() != null) {
                        if (entity.getGameTeam() != null && entity.getGameTeam() != GameTeam.NONE) {
                            return;
                        }

                        if (entity.getCurrentEffect().isItemEffect()) {
                            return;
                        }

                    entity.applyEffect(new PlayerEffect(effectId, 0));
                    return;
                    }
                }
            }

            final Integer minimumRank = PermissionsManager.getInstance().getEffects().get(effectId);

            if (minimumRank != null && client.getPlayer().getData().getRank() < minimumRank) {
                client.getPlayer().sendBubble("permission", Locale.getOrDefault("not.enough.rank", "No dispones del rango suficiente para usar este efecto."));
                return;
            }

            if (entity.getCurrentEffect() != null) {
                if (entity.getGameTeam() != null && entity.getGameTeam() != GameTeam.NONE) {
                    return;
                }

                if (entity.getCurrentEffect().isItemEffect()) {
                    return;
                }
            }

            entity.applyEffect(new PlayerEffect(effectId, 0));
        } catch (Exception e) {
            sendNotif(Locale.get("command.enable.invalidid"), client);
        }
    }

    @Override
    public String getPermission() {
        return "enable_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.number", "%number%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.enable.description");
    }

    @Override
    public boolean canDisable() {
        return true;
    }
}

package com.cometproject.server.network.messages.outgoing.room.engine;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class FurnitureAliasesMessageComposer extends MessageComposer {
    @Override
    public short getId() {
        return Composers.FurnitureAliasesMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(18);
        msg.writeString("ads_pepsi0");
        msg.writeString("ads_pepsi0_camp");

        msg.writeString("ads_tlc_wheel");
        msg.writeString("ads_tlc_wheel_camp");

        msg.writeString("calippo");
        msg.writeString("calippo_cmp");

        msg.writeString("ads_wowpball");
        msg.writeString("ads_wowpball_camp");

        msg.writeString("ads_calip_fan");
        msg.writeString("ads_calip_fan_cmp");

        msg.writeString("ads_calip_parasol");
        msg.writeString("ads_calip_parasol_cmp");

        msg.writeString("ads_calip_chair");
        msg.writeString("ads_calip_chaircmp");

        msg.writeString("ads_calip_pool");
        msg.writeString("ads_calip_pool_cmp");

        msg.writeString("ads_calip_lava");
        msg.writeString("ads_calip_lava2");

        msg.writeString("ads_cheetos");
        msg.writeString("ads_cheetos_camp");

        msg.writeString("ads_cheetos_hotdog");
        msg.writeString("ads_cheetos_hotdog_camp");

        msg.writeString("ads_cheetos_bath");
        msg.writeString("ads_cheetos_bath_camp");

        msg.writeString("ads_sunnyvend");
        msg.writeString("ads_sunnyvend_camp");

        msg.writeString("ads_grefusa_yum");
        msg.writeString("ads_grefusa_yum_camp");

        msg.writeString("ads_grefusa_cactus");
        msg.writeString("ads_grefusa_cactus_camp");

        msg.writeString("ads_cl_sofa");
        msg.writeString("ads_cl_sofa_cmp");

        msg.writeString("ads_cltele");
        msg.writeString("ads_cltele_cmp");

        msg.writeString("ads_chups");
        msg.writeString("ads_chups_camp");

    }
}

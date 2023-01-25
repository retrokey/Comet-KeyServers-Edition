package com.cometproject.server.network.messages.outgoing.landing.calendar;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.landing.LandingManager;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class CampaignCalendarDataMessageComposer extends MessageComposer {

    private boolean openBox[];
    private int openCount;
    private int lateCount;
    private int unlockDay;
    private int openSize;

    public CampaignCalendarDataMessageComposer(boolean[] o) {
        this.openBox = o;
        this.unlockDay = LandingManager.getInstance().getUnlockDays();
        this.openSize = o.length;
    }


    @Override
    public short getId() {
        return Composers.CampaignCalendarDataMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeString("xmas14");
        msg.writeString("");

        msg.writeInt(this.unlockDay); // día actual.
        msg.writeInt(LandingManager.getInstance().getTotalDays()); // días totales

        for (int i = 0; i < this.openSize; i++){
            if(this.openBox[i]) {
                this.openCount++;
            } else {
                if(this.unlockDay == i)
                    continue;

                this.lateCount++;
            }
        }

        msg.writeInt(this.openCount); // cajas abiertas.

        for (int i = 0; i < this.openSize; i++){
            if(this.openBox[i])
                msg.writeInt(i);
        }

        msg.writeInt(this.lateCount); // cajas que se han pasado de fecha.

        for (int i = 0; i < this.openSize; i++){

            if(this.unlockDay == i)
                continue;

            if(!this.openBox[i]) {
                msg.writeInt(i);
            }
        }
    }
}

package com.cometproject.server.network.messages.outgoing.user.club;

import com.cometproject.api.game.catalog.types.ICatalogItem;
import com.cometproject.api.game.catalog.types.ICatalogPage;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.players.components.SubscriptionComponent;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;


public class CatalogGiftsPageMessageComposer extends MessageComposer {

    private final ICatalogPage catalogPage;
    private final SubscriptionComponent subscriptionComponent;
    private long max;
    private int dayOfMonth;
    private int timeLeft;

    public CatalogGiftsPageMessageComposer(final ICatalogPage catalogPage, final SubscriptionComponent subscriptionComponent) {
        this.catalogPage = catalogPage;
        this.subscriptionComponent = subscriptionComponent;
    }

    @Override
    public short getId() {
        return Composers.CatalogGiftsPageMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        flushData();

        int size = this.catalogPage.getItems().size();

        msg.writeInt(timeLeft);
        msg.writeInt(subscriptionComponent.getPresents());

        msg.writeInt(size);

        for (ICatalogItem item : this.catalogPage.getItems().values()) {
            item.composeClubPresents(msg);
        }

        msg.writeInt(size);

        for (ICatalogItem item : this.catalogPage.getItems().values()) {
            item.serializeAvailability(msg);
        }
    }

    private void flushData(){
        LocalDateTime dateTime = LocalDateTime.now();
        Calendar cal = Calendar.getInstance();
        ChronoField chronoField = ChronoField.DAY_OF_MONTH;
        max = dateTime.range(chronoField).getMaximum();
        dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        timeLeft = (int)max - dayOfMonth;
    }
}

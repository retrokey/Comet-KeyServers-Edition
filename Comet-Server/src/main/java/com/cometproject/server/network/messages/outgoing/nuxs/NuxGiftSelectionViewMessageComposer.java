package com.cometproject.server.network.messages.outgoing.nuxs;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.nuxs.NuxGift;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

public class NuxGiftSelectionViewMessageComposer extends MessageComposer {
    private static int pageType = 0;

    public NuxGiftSelectionViewMessageComposer(int pageType) {
        this.pageType = pageType;
    }

    @Override
    public short getId() {
        return Composers.NuxGiftSelectionViewMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {

        msg.writeInt(1); // Número de páginas.

        msg.writeInt(1);
        msg.writeInt(3);
        msg.writeInt(CatalogManager.getInstance().getNuxGiftsSelectionView(pageType).size()); // Número total de premios:

        for(NuxGift gift : CatalogManager.getInstance().getNuxGiftsSelectionView(pageType)) {
            msg.writeString(gift.getIcon()); // image.library.url + string
            msg.writeInt(1); // items:
            msg.writeString(gift.getProductdata()); // item_name (product_x_name)
            msg.writeString(""); // can be null
        }
    }
}
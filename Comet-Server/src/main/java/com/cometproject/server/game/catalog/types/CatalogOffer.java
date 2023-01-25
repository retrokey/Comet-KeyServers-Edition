package com.cometproject.server.game.catalog.types;

import com.cometproject.api.game.catalog.types.ICatalogOffer;

public class CatalogOffer implements ICatalogOffer {
    private int offerId;
    private int catalogPageId;
    private int catalogItemId;

    public CatalogOffer(int offerId, int catalogPageId, int catalogItemId) {
        this.offerId = offerId;
        this.catalogPageId = catalogPageId;
        this.catalogItemId = catalogItemId;
    }

    public int getOfferId() {
        return offerId;
    }

    public int getCatalogPageId() {
        return catalogPageId;
    }

    public int getCatalogItemId() {
        return catalogItemId;
    }
}

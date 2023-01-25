package com.cometproject.api.game.catalog.types.vouchers;

public interface IVoucher {
    int getId();

    VoucherType getType();

    String getData();

    int getCreatedBy();

    int getCreatedAt();

    String getClaimedBy();

    int getClaimedAt();

    int getLimitUse();

    VoucherStatus getStatus();

    String getCode();
}

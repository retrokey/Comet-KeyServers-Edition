package com.cometproject.server.network.messages.outgoing.room.items.wired.dialog;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.WiredUtil;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredConditionItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.conditions.positive.WiredConditionTriggererOnFurniStaff;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;


public class WiredConditionMessageComposer extends MessageComposer {

    private final WiredConditionItem wiredConditionItem;

    public WiredConditionMessageComposer(final WiredConditionItem wiredConditionItem) {
        this.wiredConditionItem = wiredConditionItem;
    }

    @Override
    public short getId() {
        return Composers.WiredConditionConfigMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeBoolean(false); // advanced
        msg.writeInt(wiredConditionItem instanceof WiredConditionTriggererOnFurniStaff ? 81 : WiredUtil.MAX_FURNI_SELECTION);

        msg.writeInt(wiredConditionItem.getWiredData().getSelectedIds().size());

        for (Long itemId : wiredConditionItem.getWiredData().getSelectedIds()) {
            msg.writeInt(ItemManager.getInstance().getItemVirtualId(itemId));
        }

        msg.writeInt(wiredConditionItem.getDefinition().getSpriteId());
        msg.writeInt(wiredConditionItem.getVirtualId());

        msg.writeString(wiredConditionItem.getWiredData().getText());

        msg.writeInt(wiredConditionItem.getWiredData().getParams().size());

        for (int param : wiredConditionItem.getWiredData().getParams().values()) {
            msg.writeInt(param);
        }

        msg.writeInt(wiredConditionItem.getWiredData().getSelectionType());
        msg.writeInt(wiredConditionItem.getInterface());
    }
}

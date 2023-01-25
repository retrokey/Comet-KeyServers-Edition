package com.cometproject.server.game.rooms.objects.items.types.floor.wired.data;

import com.cometproject.server.game.rooms.objects.items.types.floor.wired.WiredItemSnapshot;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;


public class WiredItemData {
    private int selectionType = 0;
    private List<Long> selectedIds;
    private String text;
    private Map<Integer, Integer> params;
    private Map<Long, WiredItemSnapshot> snapshots;

    public WiredItemData(int selectionType, List<Long> selectedIds, String text, Map<Integer, Integer> params, Map<Long, WiredItemSnapshot> snapshots) {
        this.selectionType = selectionType;
        this.selectedIds = selectedIds;
        this.text = StringUtils.abbreviate(text, 100);
        this.params = params;
        this.snapshots = snapshots;
    }

    public WiredItemData() {
        this.selectionType = 0;
        this.selectedIds = Lists.newArrayList();
        this.text = "";
        this.params = Maps.newHashMap();
        this.snapshots = Maps.newHashMap();
    }

    public void selectItem(long itemId) {
        this.selectedIds.add(itemId);
    }

    public int getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(int selectionType) {
        this.selectionType = selectionType;
    }

    public List<Long> getSelectedIds() {
        return selectedIds;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = StringUtils.abbreviate(text, 100);
    }

    public Map<Integer, Integer> getParams() {
        return params;
    }

    public void setParams(Map<Integer, Integer> params) {
        this.params = params;
    }

    public Map<Long, WiredItemSnapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(Map<Long, WiredItemSnapshot> snapshots) {
        this.snapshots = snapshots;
    }
}

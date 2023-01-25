package com.cometproject.games.snowwar.items;
import com.cometproject.api.networking.messages.IComposer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapStuffData extends ExtraDataBase {
    public Map<String, String> extraData;
    public static final String STATE = "state";
    public static final String RARITY = "rarity";

    public int getType() {
        return 1;
    }

    public MapStuffData(String data) {
        this.extraData = new ConcurrentHashMap<>();
        setExtraData(data);
    }

    public byte[] data() {
        StuffDataWriter data = new StuffDataWriter(1);
        data.writeInt8(this.extraData.size());
        for (String key : this.extraData.keySet()) {
            data.writeString(key);
            data.writeString(this.extraData.get(key));
        }
        return data.getData();
    }

    public void serializeComposer(IComposer writer) {
        writer.writeInt(this.extraData.size());
        for (Map.Entry<String, String> key : this.extraData.entrySet()) {
            writer.writeString(key.getKey());
            writer.writeString(key.getValue());
        }
    }

    public void setExtraData(String data) {
        String[] values = data.split("\t");
        for (String part : values) {
            if (!part.isEmpty() && !part.equals("=")) {

                String[] a = part.split("=");
                if (a.length == 2) {

                    this.extraData.put(a[0], a[1]);
                }
            }
        }
    }

    public String getWallLegacyString() {
        if (this.extraData == null) {
            return "";
        }

        String data = this.extraData.get("state");
        if (data == null) {
            return "";
        }

        return data;
    }
}
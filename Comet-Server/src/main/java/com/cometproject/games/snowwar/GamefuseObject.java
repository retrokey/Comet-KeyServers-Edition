package com.cometproject.games.snowwar;

import com.cometproject.games.snowwar.items.Item;
import com.cometproject.games.snowwar.items.StringStuffData;

public class GamefuseObject extends Item {
  public int X;
  public int Y;
  public int Rot;
  public int Z;

    public GamefuseObject() {
        extraData = new StringStuffData(null);
    }

}
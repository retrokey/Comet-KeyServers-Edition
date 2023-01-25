package com.cometproject.games.snowwar.gameevents;

public abstract class Event {
  public static final int PLAYERLEFT = 1;
  
  public static final int MOVE = 2;
  
  public static final int BALLTHROWHUMAN = 3;
  
  public static final int BALLTHROWPOSITION = 4;
  
  public static final int MAKENOWBALL = 7;
  
  public static final int CREATESNOWBALL = 8;
  
  public static final int ADDBALLTOMACHINE = 11;
  
  public static final int PICKBALLFROMGAMEITEM = 12;
  
  public int EventType;
  
  public abstract void apply();
}


/* Location:              C:\Users\Custom\Documents\SWFs Habbis\CMS\app\client\socket\jd-gui-windows-1.6.6\tambaleo.jar!\com\cometproject\games\snowwar\gameevents\Event.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
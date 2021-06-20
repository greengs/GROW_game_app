package com.google.appinventor.components.runtime.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameInstance {
  private String instanceId;
  
  private String leader;
  
  private Map<String, String> messageTimes = new HashMap<String, String>();
  
  private List<String> players = new ArrayList<String>(0);
  
  public GameInstance(String paramString) {
    this.instanceId = paramString;
    this.leader = "";
  }
  
  public String getInstanceId() {
    return this.instanceId;
  }
  
  public String getLeader() {
    return this.leader;
  }
  
  public String getMessageTime(String paramString) {
    return this.messageTimes.containsKey(paramString) ? this.messageTimes.get(paramString) : "";
  }
  
  public List<String> getPlayers() {
    return this.players;
  }
  
  public void putMessageTime(String paramString1, String paramString2) {
    this.messageTimes.put(paramString1, paramString2);
  }
  
  public void setLeader(String paramString) {
    this.leader = paramString;
  }
  
  public PlayerListDelta setPlayers(List<String> paramList) {
    if (paramList.equals(this.players))
      return PlayerListDelta.NO_CHANGE; 
    List<String> list = this.players;
    ArrayList<String> arrayList = new ArrayList<String>(paramList);
    this.players = new ArrayList<String>(paramList);
    arrayList.removeAll(list);
    list.removeAll(paramList);
    return (arrayList.size() == 0 && list.size() == 0) ? PlayerListDelta.NO_CHANGE : new PlayerListDelta(list, arrayList);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/GameInstance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.AsyncCallbackPair;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.GameInstance;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.PlayerListDelta;
import com.google.appinventor.components.runtime.util.WebServiceUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@DesignerComponent(category = ComponentCategory.INTERNAL, description = "Provides a way for applications to communicate with online game servers", iconName = "images/gameClient.png", nonVisible = true, version = 1)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, com.google.android.googleapps.permission.GOOGLE_AUTH")
public class GameClient extends AndroidNonvisibleComponent implements Component, OnResumeListener, OnStopListener {
  private static final String COMMAND_ARGUMENTS_KEY = "args";
  
  private static final String COMMAND_TYPE_KEY = "command";
  
  private static final String COUNT_KEY = "count";
  
  private static final String ERROR_RESPONSE_KEY = "e";
  
  private static final String GAME_ID_KEY = "gid";
  
  private static final String GET_INSTANCE_LISTS_COMMAND = "getinstancelists";
  
  private static final String GET_MESSAGES_COMMAND = "messages";
  
  private static final String INSTANCE_ID_KEY = "iid";
  
  private static final String INSTANCE_PUBLIC_KEY = "makepublic";
  
  private static final String INVITED_LIST_KEY = "invited";
  
  private static final String INVITEE_KEY = "inv";
  
  private static final String INVITE_COMMAND = "invite";
  
  private static final String JOINED_LIST_KEY = "joined";
  
  private static final String JOIN_INSTANCE_COMMAND = "joininstance";
  
  private static final String LEADER_KEY = "leader";
  
  private static final String LEAVE_INSTANCE_COMMAND = "leaveinstance";
  
  private static final String LOG_TAG = "GameClient";
  
  private static final String MESSAGES_LIST_KEY = "messages";
  
  private static final String MESSAGE_CONTENT_KEY = "contents";
  
  private static final String MESSAGE_RECIPIENTS_KEY = "mrec";
  
  private static final String MESSAGE_SENDER_KEY = "msender";
  
  private static final String MESSAGE_TIME_KEY = "mtime";
  
  private static final String NEW_INSTANCE_COMMAND = "newinstance";
  
  private static final String NEW_MESSAGE_COMMAND = "newmessage";
  
  private static final String PLAYERS_LIST_KEY = "players";
  
  private static final String PLAYER_ID_KEY = "pid";
  
  private static final String PUBLIC_LIST_KEY = "public";
  
  private static final String SERVER_COMMAND = "servercommand";
  
  private static final String SERVER_RETURN_VALUE_KEY = "response";
  
  private static final String SET_LEADER_COMMAND = "setleader";
  
  private static final String TYPE_KEY = "type";
  
  private Activity activityContext;
  
  private Handler androidUIHandler = new Handler();
  
  private String gameId;
  
  private GameInstance instance;
  
  private List<String> invitedInstances;
  
  private List<String> joinedInstances;
  
  private List<String> publicInstances;
  
  private String serviceUrl;
  
  private String userEmailAddress = "";
  
  public GameClient(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.activityContext = paramComponentContainer.$context();
    this.form.registerForOnResume(this);
    this.form.registerForOnStop(this);
    this.gameId = "";
    this.instance = new GameInstance("");
    this.joinedInstances = Lists.newArrayList();
    this.invitedInstances = Lists.newArrayList();
    this.publicInstances = Lists.newArrayList();
    this.serviceUrl = "http://appinvgameserver.appspot.com";
  }
  
  private void postCommandToGameServer(String paramString, List<NameValuePair> paramList, AsyncCallbackPair<JSONObject> paramAsyncCallbackPair) {
    postCommandToGameServer(paramString, paramList, paramAsyncCallbackPair, false);
  }
  
  private void postCommandToGameServer(final String commandName, final List<NameValuePair> params, final AsyncCallbackPair<JSONObject> callback, final boolean allowInstanceIdChange) {
    callback = new AsyncCallbackPair<JSONObject>() {
        public void onFailure(String param1String) {
          Log.d("GameClient", "Posting to server failed for " + commandName + " with arguments " + params + "\n Failure message: " + param1String);
          callback.onFailure(param1String);
        }
        
        public void onSuccess(JSONObject param1JSONObject) {
          Log.d("GameClient", "Received response for " + commandName + ": " + param1JSONObject.toString());
          try {
            if (param1JSONObject.getBoolean("e")) {
              callback.onFailure(param1JSONObject.getString("response"));
              return;
            } 
            String str1 = param1JSONObject.getString("gid");
            if (!str1.equals(GameClient.this.GameId())) {
              GameClient.this.Info("Incorrect game id in response: + " + str1 + ".");
              return;
            } 
          } catch (JSONException jSONException) {
            Log.w("GameClient", (Throwable)jSONException);
            callback.onFailure("Failed to parse JSON response to command " + commandName);
            return;
          } 
          String str = jSONException.getString("iid");
          if (str.equals("")) {
            callback.onSuccess(jSONException.getJSONObject("response"));
            return;
          } 
          if (str.equals(GameClient.this.InstanceId())) {
            GameClient.this.updateInstanceInfo((JSONObject)jSONException);
          } else if (allowInstanceIdChange || GameClient.this.InstanceId().equals("")) {
            GameClient.access$302(GameClient.this, new GameInstance(str));
            GameClient.this.updateInstanceInfo((JSONObject)jSONException);
            GameClient.this.InstanceIdChanged(str);
          } else {
            GameClient.this.Info("Ignored server response to " + commandName + " for incorrect instance " + str + ".");
            return;
          } 
          callback.onSuccess(jSONException.getJSONObject("response"));
        }
      };
    WebServiceUtil.getInstance().postCommandReturningObject(ServiceUrl(), commandName, params, callback);
  }
  
  private void postGetInstanceLists() {
    AsyncCallbackPair<JSONObject> asyncCallbackPair = new AsyncCallbackPair<JSONObject>() {
        public void onFailure(String param1String) {
          GameClient.this.WebServiceError("GetInstanceLists", "Failed to get up to date instance lists.");
        }
        
        public void onSuccess(JSONObject param1JSONObject) {
          GameClient.this.processInstanceLists(param1JSONObject);
          GameClient.this.FunctionCompleted("GetInstanceLists");
        }
      };
    postCommandToGameServer("getinstancelists", Lists.newArrayList((Object[])new NameValuePair[] { (NameValuePair)new BasicNameValuePair("gid", GameId()), (NameValuePair)new BasicNameValuePair("iid", InstanceId()), (NameValuePair)new BasicNameValuePair("pid", UserEmailAddress()) }), asyncCallbackPair);
  }
  
  private void postGetMessages(final String requestedType, int paramInt) {
    AsyncCallbackPair<JSONObject> asyncCallbackPair = new AsyncCallbackPair<JSONObject>() {
        public void onFailure(String param1String) {
          GameClient.this.WebServiceError("GetMessages", param1String);
        }
        
        public void onSuccess(JSONObject param1JSONObject) {
          try {
            int j = param1JSONObject.getInt("count");
            JSONArray jSONArray = param1JSONObject.getJSONArray("messages");
            for (int i = 0; i < j; i++) {
              JSONObject jSONObject = jSONArray.getJSONObject(i);
              String str1 = jSONObject.getString("type");
              String str2 = jSONObject.getString("msender");
              String str3 = jSONObject.getString("mtime");
              List<Object> list = JsonUtil.getListFromJsonArray(jSONObject.getJSONArray("contents"), true);
              if (requestedType.equals(""))
                GameClient.this.instance.putMessageTime(requestedType, str3); 
              GameClient.this.instance.putMessageTime(str1, str3);
              GameClient.this.GotMessage(str1, str2, list);
            } 
          } catch (JSONException jSONException) {
            Log.w("GameClient", (Throwable)jSONException);
            GameClient.this.Info("Failed to parse messages response.");
          } 
          GameClient.this.FunctionCompleted("GetMessages");
        }
      };
    if (InstanceId().equals("")) {
      Info("You must join an instance before attempting to fetch messages.");
      return;
    } 
    postCommandToGameServer("messages", Lists.newArrayList((Object[])new NameValuePair[] { (NameValuePair)new BasicNameValuePair("gid", GameId()), (NameValuePair)new BasicNameValuePair("iid", InstanceId()), (NameValuePair)new BasicNameValuePair("pid", UserEmailAddress()), (NameValuePair)new BasicNameValuePair("count", Integer.toString(paramInt)), (NameValuePair)new BasicNameValuePair("mtime", this.instance.getMessageTime(requestedType)), (NameValuePair)new BasicNameValuePair("type", requestedType) }), asyncCallbackPair);
  }
  
  private void postInvite(String paramString) {
    AsyncCallbackPair<JSONObject> asyncCallbackPair = new AsyncCallbackPair<JSONObject>() {
        public void onFailure(String param1String) {
          GameClient.this.WebServiceError("Invite", param1String);
        }
        
        public void onSuccess(JSONObject param1JSONObject) {
          try {
            String str = param1JSONObject.getString("inv");
            if (str.equals("")) {
              GameClient.this.Info(str + " was already invited.");
            } else {
              GameClient.this.Info("Successfully invited " + str + ".");
            } 
          } catch (JSONException jSONException) {
            Log.w("GameClient", (Throwable)jSONException);
            GameClient.this.Info("Failed to parse invite player response.");
          } 
          GameClient.this.FunctionCompleted("Invite");
        }
      };
    if (InstanceId().equals("")) {
      Info("You must have joined an instance before you can invite new players.");
      return;
    } 
    postCommandToGameServer("invite", Lists.newArrayList((Object[])new NameValuePair[] { (NameValuePair)new BasicNameValuePair("gid", GameId()), (NameValuePair)new BasicNameValuePair("iid", InstanceId()), (NameValuePair)new BasicNameValuePair("pid", UserEmailAddress()), (NameValuePair)new BasicNameValuePair("inv", paramString) }), asyncCallbackPair);
  }
  
  private void postLeaveInstance() {
    AsyncCallbackPair<JSONObject> asyncCallbackPair = new AsyncCallbackPair<JSONObject>() {
        public void onFailure(String param1String) {
          GameClient.this.WebServiceError("LeaveInstance", param1String);
        }
        
        public void onSuccess(JSONObject param1JSONObject) {
          GameClient.this.SetInstance("");
          GameClient.this.processInstanceLists(param1JSONObject);
          GameClient.this.FunctionCompleted("LeaveInstance");
        }
      };
    postCommandToGameServer("leaveinstance", Lists.newArrayList((Object[])new NameValuePair[] { (NameValuePair)new BasicNameValuePair("gid", GameId()), (NameValuePair)new BasicNameValuePair("iid", InstanceId()), (NameValuePair)new BasicNameValuePair("pid", UserEmailAddress()) }), asyncCallbackPair);
  }
  
  private void postMakeNewInstance(String paramString, Boolean paramBoolean) {
    AsyncCallbackPair<JSONObject> asyncCallbackPair = new AsyncCallbackPair<JSONObject>() {
        public void onFailure(String param1String) {
          GameClient.this.WebServiceError("MakeNewInstance", param1String);
        }
        
        public void onSuccess(JSONObject param1JSONObject) {
          GameClient.this.processInstanceLists(param1JSONObject);
          GameClient.this.NewInstanceMade(GameClient.this.InstanceId());
          GameClient.this.FunctionCompleted("MakeNewInstance");
        }
      };
    postCommandToGameServer("newinstance", Lists.newArrayList((Object[])new NameValuePair[] { (NameValuePair)new BasicNameValuePair("pid", UserEmailAddress()), (NameValuePair)new BasicNameValuePair("gid", GameId()), (NameValuePair)new BasicNameValuePair("iid", paramString), (NameValuePair)new BasicNameValuePair("makepublic", paramBoolean.toString()) }), asyncCallbackPair, true);
  }
  
  private void postNewMessage(String paramString, YailList paramYailList1, YailList paramYailList2) {
    AsyncCallbackPair<JSONObject> asyncCallbackPair = new AsyncCallbackPair<JSONObject>() {
        public void onFailure(String param1String) {
          GameClient.this.WebServiceError("SendMessage", param1String);
        }
        
        public void onSuccess(JSONObject param1JSONObject) {
          GameClient.this.FunctionCompleted("SendMessage");
        }
      };
    if (InstanceId().equals("")) {
      Info("You must have joined an instance before you can send messages.");
      return;
    } 
    postCommandToGameServer("newmessage", Lists.newArrayList((Object[])new NameValuePair[] { (NameValuePair)new BasicNameValuePair("gid", GameId()), (NameValuePair)new BasicNameValuePair("iid", InstanceId()), (NameValuePair)new BasicNameValuePair("pid", UserEmailAddress()), (NameValuePair)new BasicNameValuePair("type", paramString), (NameValuePair)new BasicNameValuePair("mrec", paramYailList1.toJSONString()), (NameValuePair)new BasicNameValuePair("contents", paramYailList2.toJSONString()), (NameValuePair)new BasicNameValuePair("mtime", this.instance.getMessageTime(paramString)) }), asyncCallbackPair);
  }
  
  private void postServerCommand(final String command, final YailList arguments) {
    AsyncCallbackPair<JSONObject> asyncCallbackPair = new AsyncCallbackPair<JSONObject>() {
        public void onFailure(String param1String) {
          GameClient.this.ServerCommandFailure(command, arguments);
          GameClient.this.WebServiceError("ServerCommand", param1String);
        }
        
        public void onSuccess(JSONObject param1JSONObject) {
          try {
            GameClient.this.ServerCommandSuccess(command, JsonUtil.getListFromJsonArray(param1JSONObject.getJSONArray("contents"), true));
          } catch (JSONException jSONException) {
            Log.w("GameClient", (Throwable)jSONException);
            GameClient.this.Info("Server command response failed to parse.");
          } 
          GameClient.this.FunctionCompleted("ServerCommand");
        }
      };
    Log.d("GameClient", "Going to post " + command + " with args " + arguments);
    postCommandToGameServer("servercommand", Lists.newArrayList((Object[])new NameValuePair[] { (NameValuePair)new BasicNameValuePair("gid", GameId()), (NameValuePair)new BasicNameValuePair("iid", InstanceId()), (NameValuePair)new BasicNameValuePair("pid", UserEmailAddress()), (NameValuePair)new BasicNameValuePair("command", command), (NameValuePair)new BasicNameValuePair("args", arguments.toJSONString()) }), asyncCallbackPair);
  }
  
  private void postSetInstance(String paramString) {
    AsyncCallbackPair<JSONObject> asyncCallbackPair = new AsyncCallbackPair<JSONObject>() {
        public void onFailure(String param1String) {
          GameClient.this.WebServiceError("SetInstance", param1String);
        }
        
        public void onSuccess(JSONObject param1JSONObject) {
          GameClient.this.processInstanceLists(param1JSONObject);
          GameClient.this.FunctionCompleted("SetInstance");
        }
      };
    postCommandToGameServer("joininstance", Lists.newArrayList((Object[])new NameValuePair[] { (NameValuePair)new BasicNameValuePair("gid", GameId()), (NameValuePair)new BasicNameValuePair("iid", paramString), (NameValuePair)new BasicNameValuePair("pid", UserEmailAddress()) }), asyncCallbackPair, true);
  }
  
  private void postSetLeader(String paramString) {
    AsyncCallbackPair<JSONObject> asyncCallbackPair = new AsyncCallbackPair<JSONObject>() {
        public void onFailure(String param1String) {
          GameClient.this.WebServiceError("SetLeader", param1String);
        }
        
        public void onSuccess(JSONObject param1JSONObject) {
          GameClient.this.FunctionCompleted("SetLeader");
        }
      };
    if (InstanceId().equals("")) {
      Info("You must join an instance before attempting to set a leader.");
      return;
    } 
    postCommandToGameServer("setleader", Lists.newArrayList((Object[])new NameValuePair[] { (NameValuePair)new BasicNameValuePair("gid", GameId()), (NameValuePair)new BasicNameValuePair("iid", InstanceId()), (NameValuePair)new BasicNameValuePair("pid", UserEmailAddress()), (NameValuePair)new BasicNameValuePair("leader", paramString) }), asyncCallbackPair);
  }
  
  private void processInstanceLists(JSONObject paramJSONObject) {
    try {
      this.joinedInstances = JsonUtil.getStringListFromJsonArray(paramJSONObject.getJSONArray("joined"));
      this.publicInstances = JsonUtil.getStringListFromJsonArray(paramJSONObject.getJSONArray("public"));
      List<String> list = JsonUtil.getStringListFromJsonArray(paramJSONObject.getJSONArray("invited"));
      if (!list.equals(InvitedInstances())) {
        List<String> list1 = this.invitedInstances;
        this.invitedInstances = list;
        list = new ArrayList<String>(list);
        list.removeAll(list1);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext())
          Invited(iterator.next()); 
      } 
    } catch (JSONException jSONException) {
      Log.w("GameClient", (Throwable)jSONException);
      Info("Instance lists failed to parse.");
    } 
  }
  
  private void updateInstanceInfo(JSONObject paramJSONObject) throws JSONException {
    boolean bool = false;
    String str = paramJSONObject.getString("leader");
    List list = JsonUtil.getStringListFromJsonArray(paramJSONObject.getJSONArray("players"));
    if (!Leader().equals(str)) {
      this.instance.setLeader(str);
      bool = true;
    } 
    PlayerListDelta playerListDelta = this.instance.setPlayers(list);
    if (playerListDelta != PlayerListDelta.NO_CHANGE) {
      Iterator<String> iterator2 = playerListDelta.getPlayersRemoved().iterator();
      while (iterator2.hasNext())
        PlayerLeft(iterator2.next()); 
      Iterator<String> iterator1 = playerListDelta.getPlayersAdded().iterator();
      while (iterator1.hasNext())
        PlayerJoined(iterator1.next()); 
    } 
    if (bool)
      NewLeader(Leader()); 
  }
  
  @SimpleEvent(description = "Indicates that a function call completed.")
  public void FunctionCompleted(final String functionName) {
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            Log.d("GameClient", "Request completed: " + functionName);
            EventDispatcher.dispatchEvent(GameClient.this, "FunctionCompleted", new Object[] { this.val$functionName });
          }
        });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The game name for this application. The same game ID can have one or more game instances.")
  public String GameId() {
    return this.gameId;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  public void GameId(String paramString) {
    this.gameId = paramString;
  }
  
  @SimpleFunction(description = "Updates the InstancesJoined and InstancesInvited lists. This procedure can be called before setting the InstanceId.")
  public void GetInstanceLists() {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            GameClient.this.postGetInstanceLists();
          }
        });
  }
  
  @SimpleFunction(description = "Retrieves messages of the specified type.")
  public void GetMessages(final String type, final int count) {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            GameClient.this.postGetMessages(type, count);
          }
        });
  }
  
  @SimpleEvent(description = "Indicates that a new message has been received.")
  public void GotMessage(final String type, final String sender, final List<Object> contents) {
    Log.d("GameClient", "Got message of type " + type);
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            EventDispatcher.dispatchEvent(GameClient.this, "GotMessage", new Object[] { this.val$type, this.val$sender, this.val$contents });
          }
        });
  }
  
  @SimpleEvent(description = "Indicates that something has occurred which the player should know about.")
  public void Info(final String message) {
    Log.d("GameClient", "Info: " + message);
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            EventDispatcher.dispatchEvent(GameClient.this, "Info", new Object[] { this.val$message });
          }
        });
  }
  
  public void Initialize() {
    Log.d("GameClient", "Initialize");
    if (this.gameId.equals(""))
      throw new YailRuntimeError("Game Id must not be empty.", "GameClient Configuration Error."); 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The game instance id.  Taken together,the game ID and the instance ID uniquely identify the game.")
  public String InstanceId() {
    return this.instance.getInstanceId();
  }
  
  @SimpleEvent(description = "Indicates that the InstanceId property has changed as a result of calling MakeNewInstance or SetInstance.")
  public void InstanceIdChanged(final String instanceId) {
    Log.d("GameClient", "Instance id changed to " + instanceId);
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            EventDispatcher.dispatchEvent(GameClient.this, "InstanceIdChanged", new Object[] { this.val$instanceId });
          }
        });
  }
  
  @SimpleFunction(description = "Invites a player to this game instance.")
  public void Invite(final String playerEmail) {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            GameClient.this.postInvite(playerEmail);
          }
        });
  }
  
  @SimpleEvent(description = "Indicates that a user has been invited to this game instance.")
  public void Invited(final String instanceId) {
    Log.d("GameClient", "Player invited to " + instanceId);
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            EventDispatcher.dispatchEvent(GameClient.this, "Invited", new Object[] { this.val$instanceId });
          }
        });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The set of game instances to which this player has been invited but has not yet joined.  To ensure current values are returned, first invoke GetInstanceLists.")
  public List<String> InvitedInstances() {
    return this.invitedInstances;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The set of game instances in which this player is participating.  To ensure current values are returned, first invoke GetInstanceLists.")
  public List<String> JoinedInstances() {
    return this.joinedInstances;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The game's leader. At any time, each game instance has only one leader, but the leader may change with time.  Initially, the leader is the game instance creator. Application writers determine special properties of the leader. The leader value is updated each time a successful communication is made with the server.")
  public String Leader() {
    return this.instance.getLeader();
  }
  
  @SimpleFunction(description = "Leaves the current instance.")
  public void LeaveInstance() {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            GameClient.this.postLeaveInstance();
          }
        });
  }
  
  @SimpleFunction(description = "Asks the server to create a new instance of this game.")
  public void MakeNewInstance(final String instanceId, final boolean makePublic) {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            GameClient.this.postMakeNewInstance(instanceId, Boolean.valueOf(makePublic));
          }
        });
  }
  
  @SimpleEvent(description = "Indicates that a new instance was successfully created after calling MakeNewInstance.")
  public void NewInstanceMade(final String instanceId) {
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            Log.d("GameClient", "New instance made: " + instanceId);
            EventDispatcher.dispatchEvent(GameClient.this, "NewInstanceMade", new Object[] { this.val$instanceId });
          }
        });
  }
  
  @SimpleEvent(description = "Indicates that this game has a new leader as specified through SetLeader")
  public void NewLeader(final String playerId) {
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            Log.d("GameClient", "Leader change to " + playerId);
            EventDispatcher.dispatchEvent(GameClient.this, "NewLeader", new Object[] { this.val$playerId });
          }
        });
  }
  
  @SimpleEvent(description = "Indicates that a new player has joined this game instance.")
  public void PlayerJoined(final String playerId) {
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            if (!playerId.equals(GameClient.this.UserEmailAddress())) {
              Log.d("GameClient", "Player joined: " + playerId);
              EventDispatcher.dispatchEvent(GameClient.this, "PlayerJoined", new Object[] { this.val$playerId });
            } 
          }
        });
  }
  
  @SimpleEvent(description = "Indicates that a player has left this game instance.")
  public void PlayerLeft(final String playerId) {
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            Log.d("GameClient", "Player left: " + playerId);
            EventDispatcher.dispatchEvent(GameClient.this, "PlayerLeft", new Object[] { this.val$playerId });
          }
        });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The current set of players for this game instance. Each player is designated by an email address, which is a string. The list of players is updated each time a successful communication is made with the game server.")
  public List<String> Players() {
    return this.instance.getPlayers();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The set of game instances that have been marked public. To ensure current values are returned, first invoke {@link #GetInstanceLists}. ")
  public List<String> PublicInstances() {
    return this.publicInstances;
  }
  
  @SimpleFunction(description = "Sends a keyed message to all recipients in the recipients list. The message will consist of the contents list.")
  public void SendMessage(final String type, final YailList recipients, final YailList contents) {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            GameClient.this.postNewMessage(type, recipients, contents);
          }
        });
  }
  
  @SimpleFunction(description = "Sends the specified command to the game server.")
  public void ServerCommand(final String command, final YailList arguments) {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            GameClient.this.postServerCommand(command, arguments);
          }
        });
  }
  
  @SimpleEvent(description = "Indicates that a server command failed.")
  public void ServerCommandFailure(final String command, final YailList arguments) {
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            Log.d("GameClient", "Server command failed: " + command);
            EventDispatcher.dispatchEvent(GameClient.this, "ServerCommandFailure", new Object[] { this.val$command, this.val$arguments });
          }
        });
  }
  
  @SimpleEvent(description = "Indicates that a server command returned successfully.")
  public void ServerCommandSuccess(final String command, final List<Object> response) {
    Log.d("GameClient", command + " server command returned.");
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            EventDispatcher.dispatchEvent(GameClient.this, "ServerCommandSuccess", new Object[] { this.val$command, this.val$response });
          }
        });
  }
  
  @DesignerProperty(defaultValue = "http://appinvgameserver.appspot.com", editorType = "string")
  @SimpleProperty(userVisible = false)
  public void ServiceURL(String paramString) {
    if (paramString.endsWith("/")) {
      this.serviceUrl = paramString.substring(0, paramString.length() - 1);
      return;
    } 
    this.serviceUrl = paramString;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The URL of the game server.")
  public String ServiceUrl() {
    return this.serviceUrl;
  }
  
  @SimpleFunction(description = "Sets InstanceId and joins the specified instance.")
  public void SetInstance(final String instanceId) {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            if (instanceId.equals("")) {
              Log.d("GameClient", "Instance id set to empty string.");
              if (!GameClient.this.InstanceId().equals("")) {
                GameClient.access$302(GameClient.this, new GameInstance(""));
                GameClient.this.InstanceIdChanged("");
                GameClient.this.FunctionCompleted("SetInstance");
              } 
              return;
            } 
            GameClient.this.postSetInstance(instanceId);
          }
        });
  }
  
  @SimpleFunction(description = "Tells the server to set the leader to playerId. Only the current leader may successfully set a new leader.")
  public void SetLeader(final String playerEmail) {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            GameClient.this.postSetLeader(playerEmail);
          }
        });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The email address that is being used as the player id for this game client.   At present, users must set this manually in oder to join a game.  But this property will change in the future so that is set automatically, and users will not be able to change it.")
  public String UserEmailAddress() {
    if (this.userEmailAddress.equals(""))
      Info("User email address is empty."); 
    return this.userEmailAddress;
  }
  
  @SimpleProperty
  public void UserEmailAddress(String paramString) {
    this.userEmailAddress = paramString;
    UserEmailAddressSet(paramString);
  }
  
  @SimpleEvent(description = "Indicates that the user email address has been set.")
  public void UserEmailAddressSet(final String emailAddress) {
    Log.d("GameClient", "Email address set.");
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            EventDispatcher.dispatchEvent(GameClient.this, "UserEmailAddressSet", new Object[] { this.val$emailAddress });
          }
        });
  }
  
  @SimpleEvent(description = "Indicates that an error occurred while communicating with the web server.")
  public void WebServiceError(final String functionName, final String message) {
    Log.e("GameClient", "WebServiceError: " + message);
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            EventDispatcher.dispatchEvent(GameClient.this, "WebServiceError", new Object[] { this.val$functionName, this.val$message });
          }
        });
  }
  
  public void onResume() {
    Log.d("GameClient", "Activity Resumed.");
  }
  
  public void onStop() {
    Log.d("GameClient", "Activity Stopped.");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/GameClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
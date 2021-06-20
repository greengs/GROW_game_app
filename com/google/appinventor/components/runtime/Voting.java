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
import com.google.appinventor.components.runtime.util.AsyncCallbackPair;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.WebServiceUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@DesignerComponent(category = ComponentCategory.INTERNAL, designerHelpDescription = "<p>The Voting component enables users to vote on a question by communicating with a Web service to retrieve a ballot and later sending back users' votes.</p>", iconName = "images/voting.png", nonVisible = true, version = 1)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class Voting extends AndroidNonvisibleComponent implements Component {
  private static final String BALLOT_OPTIONS_PARAMETER = "options";
  
  private static final String BALLOT_QUESTION_PARAMETER = "question";
  
  private static final String ID_REQUESTED_PARAMETER = "idRequested";
  
  private static final String IS_POLLING_PARAMETER = "isPolling";
  
  private static final String LOG_TAG = "Voting";
  
  private static final String REQUESTBALLOT_COMMAND = "requestballot";
  
  private static final String SENDBALLOT_COMMAND = "sendballot";
  
  private static final String USER_CHOICE_PARAMETER = "userchoice";
  
  private static final String USER_ID_PARAMETER = "userid";
  
  private Activity activityContext;
  
  private Handler androidUIHandler = new Handler();
  
  private ArrayList<String> ballotOptions = new ArrayList<String>();
  
  private String ballotOptionsString;
  
  private String ballotQuestion = "";
  
  private Boolean idRequested = Boolean.valueOf(false);
  
  private Boolean isPolling = Boolean.valueOf(false);
  
  private String serviceURL = "http://androvote.appspot.com";
  
  private ComponentContainer theContainer;
  
  private String userChoice = "";
  
  private String userId = "";
  
  public Voting(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.theContainer = paramComponentContainer;
    this.activityContext = paramComponentContainer.$context();
    this.serviceURL = "http://androvote.appspot.com";
  }
  
  private ArrayList<String> JSONArrayToArrayList(JSONArray paramJSONArray) throws JSONException {
    ArrayList<String> arrayList = new ArrayList();
    for (int i = 0; i < paramJSONArray.length(); i++)
      arrayList.add(paramJSONArray.getString(i)); 
    return arrayList;
  }
  
  private void postRequestBallot() {
    AsyncCallbackPair<JSONObject> asyncCallbackPair = new AsyncCallbackPair<JSONObject>() {
        public void onFailure(final String message) {
          Log.w("Voting", "postRequestBallot Failure " + message);
          Voting.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  Voting.this.WebServiceError(message);
                }
              });
        }
        
        public void onSuccess(JSONObject param1JSONObject) {
          if (param1JSONObject == null) {
            Voting.this.androidUIHandler.post(new Runnable() {
                  public void run() {
                    Voting.this.WebServiceError("The Web server did not respond to your request for a ballot");
                  }
                });
            return;
          } 
          try {
            Log.i("Voting", "postRequestBallot: ballot retrieved " + param1JSONObject);
            Voting.access$202(Voting.this, Boolean.valueOf(param1JSONObject.getBoolean("isPolling")));
            if (Voting.this.isPolling.booleanValue()) {
              Voting.access$302(Voting.this, Boolean.valueOf(param1JSONObject.getBoolean("idRequested")));
              Voting.access$402(Voting.this, param1JSONObject.getString("question"));
              Voting.access$502(Voting.this, param1JSONObject.getString("options"));
              Voting.access$602(Voting.this, Voting.this.JSONArrayToArrayList(new JSONArray(Voting.this.ballotOptionsString)));
              Voting.this.androidUIHandler.post(new Runnable() {
                    public void run() {
                      Voting.this.GotBallot();
                    }
                  });
              return;
            } 
          } catch (JSONException jSONException) {
            Voting.this.androidUIHandler.post(new Runnable() {
                  public void run() {
                    Voting.this.WebServiceError("The Web server returned a garbled object");
                  }
                });
            return;
          } 
          Voting.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  Voting.this.NoOpenPoll();
                }
              });
        }
      };
    WebServiceUtil.getInstance().postCommandReturningObject(this.serviceURL, "requestballot", null, asyncCallbackPair);
  }
  
  private void postSendBallot(String paramString1, String paramString2) {
    AsyncCallbackPair<String> asyncCallbackPair = new AsyncCallbackPair<String>() {
        public void onFailure(final String message) {
          Log.w("Voting", "postSendBallot Failure " + message);
          Voting.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  Voting.this.WebServiceError(message);
                }
              });
        }
        
        public void onSuccess(String param1String) {
          Voting.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  Voting.this.GotBallotConfirmation();
                }
              });
        }
      };
    WebServiceUtil.getInstance().postCommand(this.serviceURL, "sendballot", Lists.newArrayList((Object[])new NameValuePair[] { (NameValuePair)new BasicNameValuePair("userchoice", paramString1), (NameValuePair)new BasicNameValuePair("userid", paramString2) }), asyncCallbackPair);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The list of ballot options.")
  public List<String> BallotOptions() {
    return this.ballotOptions;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The question to be voted on.")
  public String BallotQuestion() {
    return this.ballotQuestion;
  }
  
  @SimpleEvent(description = "Event indicating that a ballot was retrieved from the Web service and that the properties <code>BallotQuestion</code> and <code>BallotOptions</code> have been set.  This is always preceded by a call to the method <code>RequestBallot</code>.")
  public void GotBallot() {
    EventDispatcher.dispatchEvent(this, "GotBallot", new Object[0]);
  }
  
  @SimpleEvent
  public void GotBallotConfirmation() {
    EventDispatcher.dispatchEvent(this, "GotBallotConfirmation", new Object[0]);
  }
  
  @SimpleEvent
  public void NoOpenPoll() {
    EventDispatcher.dispatchEvent(this, "NoOpenPoll", new Object[0]);
  }
  
  @SimpleFunction(description = "Send a request for a ballot to the Web service specified by the property <code>ServiceURL</code>.  When the completes, one of the following events will be raised: <code>GotBallot</code>, <code>NoOpenPoll</code>, or <code>WebServiceError</code>.")
  public void RequestBallot() {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            Voting.this.postRequestBallot();
          }
        });
  }
  
  @SimpleFunction(description = "Send a completed ballot to the Web service.  This should not be called until the properties <code>UserId</code> and <code>UserChoice</code> have been set by the application.")
  public void SendBallot() {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            Voting.this.postSendBallot(Voting.this.userChoice, Voting.this.userId);
          }
        });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The URL of the Voting service")
  public String ServiceURL() {
    return this.serviceURL;
  }
  
  @DesignerProperty(defaultValue = "http://androvote.appspot.com", editorType = "string")
  @SimpleProperty
  public void ServiceURL(String paramString) {
    this.serviceURL = paramString;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The ballot choice to send to the server, which must be set before <code>SendBallot</code> is called.  This must be one of <code>BallotOptions</code>.")
  public String UserChoice() {
    return this.userChoice;
  }
  
  @SimpleProperty
  public void UserChoice(String paramString) {
    this.userChoice = paramString;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The email address associated with this device. This property has been deprecated and always returns the empty text value.")
  public String UserEmailAddress() {
    return "";
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "A text identifying the voter that is sent to the Voting server along with the vote.  This must be set before <code>SendBallot</code> is called.")
  public String UserId() {
    return this.userId;
  }
  
  @SimpleProperty
  public void UserId(String paramString) {
    this.userId = paramString;
  }
  
  @SimpleEvent
  public void WebServiceError(String paramString) {
    EventDispatcher.dispatchEvent(this, "WebServiceError", new Object[] { paramString });
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Voting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
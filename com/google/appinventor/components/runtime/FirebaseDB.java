package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Config;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;
import com.firebase.client.ValueEventListener;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;

@DesignerComponent(androidMinSdk = 10, category = ComponentCategory.EXPERIMENTAL, description = "Non-visible component that communicates with Firebase to store and retrieve information.", designerHelpDescription = "Non-visible component that communicates with a Firebase to store and retrieve information.", iconName = "images/firebaseDB.png", nonVisible = true, version = 3)
@SimpleObject
@UsesLibraries(libraries = "firebase.jar")
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class FirebaseDB extends AndroidNonvisibleComponent implements Component {
  private static final String LOG_TAG = "Firebase";
  
  private static boolean isInitialized = false;
  
  private static boolean persist = false;
  
  private final Activity activity;
  
  private Handler androidUIHandler = new Handler();
  
  private Firebase.AuthStateListener authListener;
  
  private ChildEventListener childListener;
  
  private String defaultURL = null;
  
  private String developerBucket;
  
  private String firebaseToken;
  
  private String firebaseURL = null;
  
  private Firebase myFirebase;
  
  private String projectBucket;
  
  private boolean useDefault = true;
  
  public FirebaseDB(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.activity = paramComponentContainer.$context();
    Firebase.setAndroidContext((Context)this.activity);
    this.developerBucket = "";
    this.projectBucket = "";
    this.firebaseToken = "";
    this.childListener = new ChildEventListener() {
        public void onCancelled(final FirebaseError error) {
          FirebaseDB.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  FirebaseDB.this.FirebaseError(error.getMessage());
                }
              });
        }
        
        public void onChildAdded(final DataSnapshot snapshot, String param1String) {
          FirebaseDB.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  FirebaseDB.this.DataChanged(snapshot.getKey(), snapshot.getValue());
                }
              });
        }
        
        public void onChildChanged(final DataSnapshot snapshot, String param1String) {
          FirebaseDB.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  FirebaseDB.this.DataChanged(snapshot.getKey(), snapshot.getValue());
                }
              });
        }
        
        public void onChildMoved(DataSnapshot param1DataSnapshot, String param1String) {}
        
        public void onChildRemoved(DataSnapshot param1DataSnapshot) {
          Log.i("Firebase", "onChildRemoved: " + param1DataSnapshot.getKey() + " removed.");
        }
      };
    this.authListener = new Firebase.AuthStateListener() {
        public void onAuthStateChanged(AuthData param1AuthData) {
          Log.i("Firebase", "onAuthStateChanged: data = " + param1AuthData);
          if (param1AuthData == null)
            FirebaseDB.this.myFirebase.authWithCustomToken(FirebaseDB.this.firebaseToken, new Firebase.AuthResultHandler() {
                  public void onAuthenticated(AuthData param2AuthData) {
                    Log.i("Firebase", "Auth Successful.");
                  }
                  
                  public void onAuthenticationError(FirebaseError param2FirebaseError) {
                    Log.e("Firebase", "Auth Failed: " + param2FirebaseError.getMessage());
                  }
                }); 
        }
      };
  }
  
  private void connectFirebase() {
    if (SdkLevel.getLevel() < 10) {
      Notifier.oneButtonAlert(this.activity, "The version of Android on this device is too old to use Firebase.", "Android Too Old", "OK");
      return;
    } 
    if (this.useDefault) {
      this.myFirebase = new Firebase(this.firebaseURL + "developers/" + this.developerBucket + this.projectBucket);
    } else {
      this.myFirebase = new Firebase(this.firebaseURL + this.projectBucket);
    } 
    this.myFirebase.addChildEventListener(this.childListener);
    this.myFirebase.addAuthStateListener(this.authListener);
  }
  
  private void firebaseTransaction(final Transactional toRun, Firebase paramFirebase, final Runnable whenDone) {
    paramFirebase.runTransaction(new Transaction.Handler() {
          public Transaction.Result doTransaction(MutableData param1MutableData) {
            return toRun.run(param1MutableData);
          }
          
          public void onComplete(final FirebaseError firebaseError, boolean param1Boolean, DataSnapshot param1DataSnapshot) {
            if (firebaseError != null) {
              FirebaseDB.this.androidUIHandler.post(new Runnable() {
                    public void run() {
                      Log.i("Firebase", "AppendValue(onComplete): firebase: " + firebaseError.getMessage());
                      Log.i("Firebase", "AppendValue(onComplete): result.err: " + result.err);
                      FirebaseDB.this.FirebaseError(firebaseError.getMessage());
                    }
                  });
              return;
            } 
            if (!param1Boolean) {
              FirebaseDB.this.androidUIHandler.post(new Runnable() {
                    public void run() {
                      Log.i("Firebase", "AppendValue(!committed): result.err: " + result.err);
                      FirebaseDB.this.FirebaseError(result.err);
                    }
                  });
              return;
            } 
            if (whenDone != null) {
              FirebaseDB.this.androidUIHandler.post(whenDone);
              return;
            } 
          }
        });
  }
  
  private void resetListener() {
    if (!isInitialized)
      return; 
    if (this.myFirebase != null) {
      this.myFirebase.removeEventListener(this.childListener);
      this.myFirebase.removeAuthStateListener(this.authListener);
    } 
    this.myFirebase = null;
    connectFirebase();
  }
  
  @SimpleFunction(description = "Append a value to the end of a list atomically. If two devices use this function simultaneously, both will be appended and no data lost.")
  public void AppendValue(String paramString, final Object valueToAdd) {
    final ReturnVal result = new ReturnVal();
    Firebase firebase = this.myFirebase.child(paramString);
    firebaseTransaction(new Transactional(null, null, returnVal) {
          Transaction.Result run(MutableData param1MutableData) {
            Object object = param1MutableData.getValue();
            if (object == null) {
              result.err = "Previous value was empty.";
              return Transaction.abort();
            } 
            try {
              if (object instanceof String) {
                object = JsonUtil.getObjectFromJson((String)object, true);
                if (object instanceof List) {
                  ((List<Object>)object).add(valueToAdd);
                  try {
                    object = JsonUtil.getJsonRepresentation(object);
                    param1MutableData.setValue(object);
                    return Transaction.success(param1MutableData);
                  } catch (JSONException jSONException) {
                    result.err = "Could not convert value to JSON.";
                    return Transaction.abort();
                  } 
                } 
              } else {
                result.err = "Invalid JSON object in database (shouldn't happen!)";
                return Transaction.abort();
              } 
            } catch (JSONException jSONException) {
              result.err = "Invalid JSON object in database (shouldn't happen!)";
              return Transaction.abort();
            } 
            result.err = "You can only append to a list.";
            return Transaction.abort();
          }
        }firebase, null);
  }
  
  @SimpleFunction(description = "Remove the tag from Firebase")
  public void ClearTag(String paramString) {
    this.myFirebase.child(paramString).removeValue();
  }
  
  @SimpleEvent
  public void DataChanged(String paramString, Object paramObject) {
    Object object = paramObject;
    if (paramObject != null) {
      object = paramObject;
      try {
        if (paramObject instanceof String)
          object = JsonUtil.getObjectFromJson((String)paramObject, true); 
        EventDispatcher.dispatchEvent(this, "DataChanged", new Object[] { paramString, object });
        return;
      } catch (JSONException jSONException) {
        throw new YailRuntimeError("Value failed to convert from JSON.", "JSON Retrieval Error.");
      } 
    } 
    EventDispatcher.dispatchEvent(this, "DataChanged", new Object[] { jSONException, object });
  }
  
  @DesignerProperty(editorType = "string")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
  public void DefaultURL(String paramString) {
    this.defaultURL = paramString;
    if (this.useDefault) {
      this.firebaseURL = this.defaultURL;
      resetListener();
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
  public String DeveloperBucket() {
    return this.developerBucket;
  }
  
  @DesignerProperty(editorType = "string")
  @SimpleProperty
  public void DeveloperBucket(String paramString) {
    this.developerBucket = paramString;
    resetListener();
  }
  
  @SimpleEvent
  public void FirebaseError(String paramString) {
    Log.e("Firebase", paramString);
    if (!EventDispatcher.dispatchEvent(this, "FirebaseError", new Object[] { paramString }))
      Notifier.oneButtonAlert(this.form, paramString, "FirebaseError", "Continue"); 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
  public String FirebaseToken() {
    return this.firebaseToken;
  }
  
  @DesignerProperty(editorType = "string")
  @SimpleProperty
  public void FirebaseToken(String paramString) {
    this.firebaseToken = paramString;
    resetListener();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets the URL for this FirebaseDB.", userVisible = false)
  public String FirebaseURL() {
    return this.useDefault ? "DEFAULT" : this.firebaseURL;
  }
  
  @DesignerProperty(defaultValue = "DEFAULT", editorType = "FirbaseURL")
  @SimpleProperty(description = "Sets the URL for this FirebaseDB.")
  public void FirebaseURL(String paramString) {
    if (paramString.equals("DEFAULT")) {
      if (!this.useDefault) {
        this.useDefault = true;
        if (this.defaultURL == null) {
          Log.d("Firebase", "FirebaseURL called before DefaultURL (should not happen!)");
          return;
        } 
        this.firebaseURL = this.defaultURL;
        resetListener();
        return;
      } 
      this.firebaseURL = this.defaultURL;
      return;
    } 
    this.useDefault = false;
    StringBuilder stringBuilder = (new StringBuilder()).append(paramString);
    if (paramString.endsWith("/")) {
      paramString = "";
    } else {
      paramString = "/";
    } 
    paramString = stringBuilder.append(paramString).toString();
    if (!this.firebaseURL.equals(paramString)) {
      this.firebaseURL = paramString;
      this.useDefault = false;
      resetListener();
      return;
    } 
  }
  
  @SimpleEvent(description = "Event triggered by the \"RemoveFirst\" function. The argument \"value\" is the object that was the first in the list, and which is now removed.")
  public void FirstRemoved(Object paramObject) {
    EventDispatcher.dispatchEvent(this, "FirstRemoved", new Object[] { paramObject });
  }
  
  @SimpleFunction(description = "Get the list of tags for this application. When complete a \"TagList\" event will be triggered with the list of known tags.")
  public void GetTagList() {
    this.myFirebase.child("").addListenerForSingleValueEvent(new ValueEventListener() {
          public void onCancelled(FirebaseError param1FirebaseError) {}
          
          public void onDataChange(DataSnapshot param1DataSnapshot) {
            final Object listValue = param1DataSnapshot.getValue();
            if (object instanceof HashMap) {
              object = new ArrayList(((HashMap)object).keySet());
              FirebaseDB.this.androidUIHandler.post(new Runnable() {
                    public void run() {
                      FirebaseDB.this.TagList(listValue);
                    }
                  });
            } 
          }
        });
  }
  
  @SimpleFunction
  public void GetValue(final String tag, final Object valueIfTagNotThere) {
    this.myFirebase.child(tag).addListenerForSingleValueEvent(new ValueEventListener() {
          public void onCancelled(final FirebaseError error) {
            FirebaseDB.this.androidUIHandler.post(new Runnable() {
                  public void run() {
                    FirebaseDB.this.FirebaseError(error.getMessage());
                  }
                });
          }
          
          public void onDataChange(DataSnapshot param1DataSnapshot) {
            final AtomicReference<Object> value = new AtomicReference();
            try {
              if (param1DataSnapshot.exists()) {
                atomicReference.set(param1DataSnapshot.getValue());
              } else {
                atomicReference.set(JsonUtil.getJsonRepresentation(valueIfTagNotThere));
              } 
              FirebaseDB.this.androidUIHandler.post(new Runnable() {
                    public void run() {
                      FirebaseDB.this.GotValue(tag, value.get());
                    }
                  });
              return;
            } catch (JSONException jSONException) {
              throw new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
            } 
          }
        });
  }
  
  @SimpleEvent
  public void GotValue(String paramString, Object paramObject) {
    Object object = paramObject;
    if (paramObject != null) {
      object = paramObject;
      try {
        if (paramObject instanceof String)
          object = JsonUtil.getObjectFromJson((String)paramObject, true); 
        EventDispatcher.dispatchEvent(this, "GotValue", new Object[] { paramString, object });
        return;
      } catch (JSONException jSONException) {
        throw new YailRuntimeError("Value failed to convert from JSON.", "JSON Retrieval Error.");
      } 
    } 
    EventDispatcher.dispatchEvent(this, "GotValue", new Object[] { jSONException, object });
  }
  
  public void Initialize() {
    Log.i("Firebase", "Initalize called!");
    isInitialized = true;
    resetListener();
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(description = "If true, variables will retain their values when off-line and the App exits. Values will be uploaded to Firebase the next time the App is run while connected to the network. This is useful for applications which will gather data while not connected to the network. Note: AppendValue and RemoveFirst will not work correctly when off-line, they require a network connection.<br/><br/> <i>Note</i>: If you set Persist on any Firebase component, on any screen, it makes all Firebase components on all screens persistent. This is a limitation of the low level Firebase library. Also be aware that if you want to set persist to true, you should do so before connecting the Companion for incremental development.", userVisible = false)
  public void Persist(boolean paramBoolean) {
    Log.i("Firebase", "Persist Called: Value = " + paramBoolean);
    if (persist != paramBoolean) {
      if (isInitialized)
        throw new RuntimeException("You cannot change the Persist value of Firebase after Application Initialization, this includes the Companion"); 
      Config config = Firebase.getDefaultConfig();
      config.setPersistenceEnabled(paramBoolean);
      Firebase.setDefaultConfig(config);
      persist = paramBoolean;
      resetListener();
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets the ProjectBucket for this FirebaseDB.")
  public String ProjectBucket() {
    return this.projectBucket;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty(description = "Sets the ProjectBucket for this FirebaseDB.")
  public void ProjectBucket(String paramString) {
    if (!this.projectBucket.equals(paramString)) {
      this.projectBucket = paramString;
      resetListener();
    } 
  }
  
  @SimpleFunction(description = "Return the first element of a list and atomically remove it. If two devices use this function simultaneously, one will get the first element and the the other will get the second element, or an error if there is no available element. When the element is available, the \"FirstRemoved\" event will be triggered.")
  public void RemoveFirst(String paramString) {
    final ReturnVal result = new ReturnVal();
    Firebase firebase = this.myFirebase.child(paramString);
    firebaseTransaction(new Transactional(null, null, returnVal) {
          Transaction.Result run(MutableData param1MutableData) {
            Object object = param1MutableData.getValue();
            if (object == null) {
              result.err = "Previous value was empty.";
              return Transaction.abort();
            } 
            try {
              if (object instanceof String) {
                object = JsonUtil.getObjectFromJson((String)object, true);
                if (object instanceof List) {
                  if (((List)object).isEmpty()) {
                    result.err = "The list was empty";
                    return Transaction.abort();
                  } 
                  result.retval = ((List)object).remove(0);
                  try {
                    object = JsonUtil.getJsonRepresentation(YailList.makeList((List)object));
                    param1MutableData.setValue(object);
                    return Transaction.success(param1MutableData);
                  } catch (JSONException jSONException) {
                    result.err = "Could not convert value to JSON.";
                    return Transaction.abort();
                  } 
                } 
              } else {
                result.err = "Invalid JSON object in database (shouldn't happen!)";
                return Transaction.abort();
              } 
            } catch (JSONException jSONException) {
              result.err = "Invalid JSON object in database (shouldn't happen!)";
              return Transaction.abort();
            } 
            result.err = "You can only remove elements from a list.";
            return Transaction.abort();
          }
        }firebase, new Runnable() {
          public void run() {
            FirebaseDB.this.FirstRemoved(result.getRetval());
          }
        });
  }
  
  @SimpleFunction
  public void StoreValue(String paramString, Object paramObject) {
    Object object = paramObject;
    if (paramObject != null)
      try {
        object = JsonUtil.getJsonRepresentation(paramObject);
        this.myFirebase.child(paramString).setValue(object);
        return;
      } catch (JSONException jSONException) {
        throw new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
      }  
    this.myFirebase.child((String)jSONException).setValue(object);
  }
  
  @SimpleEvent(description = "Event triggered when we have received the list of known tags. Used with the \"GetTagList\" Function.")
  public void TagList(List paramList) {
    EventDispatcher.dispatchEvent(this, "TagList", new Object[] { paramList });
  }
  
  @SimpleFunction(description = "If you are having difficulty with the Companion and you are switching between different Firebase accounts, you may need to use this function to clear internal Firebase caches. You can just use the \"Do It\" function on this block in the blocks editor. Note: You should not normally need to use this block as part of an application.")
  public void Unauthenticate() {
    if (this.myFirebase == null)
      connectFirebase(); 
    this.myFirebase.unauth();
  }
  
  private static class ReturnVal {
    String err;
    
    Object retval;
    
    private ReturnVal() {}
    
    Object getRetval() {
      return this.retval;
    }
  }
  
  private static abstract class Transactional {
    final Object arg1;
    
    final Object arg2;
    
    final FirebaseDB.ReturnVal retv;
    
    Transactional(Object param1Object1, Object param1Object2, FirebaseDB.ReturnVal param1ReturnVal) {
      this.arg1 = param1Object1;
      this.arg2 = param1Object2;
      this.retv = param1ReturnVal;
    }
    
    FirebaseDB.ReturnVal getResult() {
      return this.retv;
    }
    
    abstract Transaction.Result run(MutableData param1MutableData);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/FirebaseDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
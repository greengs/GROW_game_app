package com.google.appinventor.components.runtime;

import android.os.Handler;
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
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.WebServiceUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

@DesignerComponent(category = ComponentCategory.STORAGE, description = "Non-visible component that communicates with a Web service to store and retrieve information.", iconName = "images/tinyWebDB.png", nonVisible = true, version = 2)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class TinyWebDB extends AndroidNonvisibleComponent implements Component {
  private static final String GETVALUE_COMMAND = "getvalue";
  
  private static final String LOG_TAG = "TinyWebDB";
  
  private static final String STOREAVALUE_COMMAND = "storeavalue";
  
  private static final String TAG_PARAMETER = "tag";
  
  private static final String VALUE_PARAMETER = "value";
  
  private Handler androidUIHandler = new Handler();
  
  private String serviceURL = "http://tinywebdb.appinventor.mit.edu/";
  
  public TinyWebDB(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
  }
  
  private void postGetValue(final String tag) {
    AsyncCallbackPair<JSONArray> asyncCallbackPair = new AsyncCallbackPair<JSONArray>() {
        public void onFailure(final String message) {
          TinyWebDB.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  TinyWebDB.this.WebServiceError(message);
                }
              });
        }
        
        public void onSuccess(JSONArray param1JSONArray) {
          if (param1JSONArray == null) {
            TinyWebDB.this.androidUIHandler.post(new Runnable() {
                  public void run() {
                    TinyWebDB.this.WebServiceError("The Web server did not respond to the get value request for the tag " + tag + ".");
                  }
                });
            return;
          } 
          try {
            final String tagFromWebDB = param1JSONArray.getString(1);
            final Object valueFromWebDB = param1JSONArray.getString(2);
            if (object.length() == 0) {
              object = "";
            } else {
              object = JsonUtil.getObjectFromJson((String)object, true);
            } 
            TinyWebDB.this.androidUIHandler.post(new Runnable() {
                  public void run() {
                    TinyWebDB.this.GotValue(tagFromWebDB, valueFromWebDB);
                  }
                });
            return;
          } catch (JSONException jSONException) {
            TinyWebDB.this.androidUIHandler.post(new Runnable() {
                  public void run() {
                    TinyWebDB.this.WebServiceError("The Web server returned a garbled value for the tag " + tag + ".");
                  }
                });
            return;
          } 
        }
      };
    WebServiceUtil.getInstance().postCommandReturningArray(this.serviceURL, "getvalue", Lists.newArrayList((Object[])new NameValuePair[] { (NameValuePair)new BasicNameValuePair("tag", tag) }), asyncCallbackPair);
  }
  
  private void postStoreValue(String paramString, Object paramObject) {
    AsyncCallbackPair<String> asyncCallbackPair = new AsyncCallbackPair<String>() {
        public void onFailure(final String message) {
          TinyWebDB.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  TinyWebDB.this.WebServiceError(message);
                }
              });
        }
        
        public void onSuccess(String param1String) {
          TinyWebDB.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  TinyWebDB.this.ValueStored();
                }
              });
        }
      };
    try {
      WebServiceUtil.getInstance().postCommand(this.serviceURL, "storeavalue", Lists.newArrayList((Object[])new NameValuePair[] { (NameValuePair)new BasicNameValuePair("tag", paramString), (NameValuePair)new BasicNameValuePair("value", JsonUtil.getJsonRepresentation(paramObject)) }), asyncCallbackPair);
      return;
    } catch (JSONException jSONException) {
      throw new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
    } 
  }
  
  @SimpleFunction(description = "Sends a request to the Web service to get the value stored under the given tag. The Web service must decide what to return if there is no value stored under the tag. This component accepts whatever is returned.")
  public void GetValue(final String tag) {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            TinyWebDB.this.postGetValue(tag);
          }
        });
  }
  
  @SimpleEvent(description = "Indicates that a GetValue server request has succeeded.")
  public void GotValue(String paramString, Object paramObject) {
    EventDispatcher.dispatchEvent(this, "GotValue", new Object[] { paramString, paramObject });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The URL of the web service database.")
  public String ServiceURL() {
    return this.serviceURL;
  }
  
  @DesignerProperty(defaultValue = "http://tinywebdb.appinventor.mit.edu", editorType = "string")
  @SimpleProperty
  public void ServiceURL(String paramString) {
    this.serviceURL = paramString;
  }
  
  @SimpleFunction(description = "Asks the Web service to store the given value under the given tag")
  public void StoreValue(final String tag, final Object valueToStore) {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            TinyWebDB.this.postStoreValue(tag, valueToStore);
          }
        });
  }
  
  @SimpleEvent(description = "Event indicating that a StoreValue server request has succeeded.")
  public void ValueStored() {
    EventDispatcher.dispatchEvent(this, "ValueStored", new Object[0]);
  }
  
  @SimpleEvent
  public void WebServiceError(String paramString) {
    EventDispatcher.dispatchEvent(this, "WebServiceError", new Object[] { paramString });
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/TinyWebDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
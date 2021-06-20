package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.PhoneStatus;
import com.google.appinventor.components.runtime.ReplForm;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RetValManager {
  private static final String LOG_TAG = "RetValManager";
  
  private static final long TENSECONDS = 10000L;
  
  private static ArrayList<JSONObject> currentArray;
  
  private static final Object semaphore = new Object();
  
  static {
    currentArray = new ArrayList<JSONObject>(10);
  }
  
  public static void appendReturnValue(String paramString1, String paramString2, String paramString3) {
    synchronized (semaphore) {
      JSONObject jSONObject = new JSONObject();
      try {
        jSONObject.put("status", paramString2);
        jSONObject.put("type", "return");
        jSONObject.put("value", paramString3);
        jSONObject.put("blockid", paramString1);
        boolean bool = currentArray.isEmpty();
        currentArray.add(jSONObject);
        if (PhoneStatus.getUseWebRTC()) {
          webRTCsendCurrent();
        } else if (bool) {
          semaphore.notifyAll();
        } 
        return;
      } catch (JSONException jSONException) {
        Log.e("RetValManager", "Error building retval", (Throwable)jSONException);
        return;
      } 
    } 
  }
  
  public static void assetTransferred(String paramString) {
    synchronized (semaphore) {
      JSONObject jSONObject = new JSONObject();
      try {
        jSONObject.put("status", "OK");
        jSONObject.put("type", "assetTransferred");
        if (paramString != null)
          jSONObject.put("value", paramString.toString()); 
        boolean bool = currentArray.isEmpty();
        currentArray.add(jSONObject);
        if (PhoneStatus.getUseWebRTC()) {
          webRTCsendCurrent();
        } else if (bool) {
          semaphore.notifyAll();
        } 
        return;
      } catch (JSONException jSONException) {
        Log.e("RetValManager", "Error building retval", (Throwable)jSONException);
        return;
      } 
    } 
  }
  
  public static void extensionsLoaded() {
    synchronized (semaphore) {
      JSONObject jSONObject = new JSONObject();
      try {
        jSONObject.put("status", "OK");
        jSONObject.put("type", "extensionsLoaded");
        boolean bool = currentArray.isEmpty();
        currentArray.add(jSONObject);
        if (PhoneStatus.getUseWebRTC()) {
          webRTCsendCurrent();
        } else if (bool) {
          semaphore.notifyAll();
        } 
        return;
      } catch (JSONException jSONException) {
        Log.e("RetValManager", "Error building retval", (Throwable)jSONException);
        return;
      } 
    } 
  }
  
  public static String fetch(boolean paramBoolean) {
    long l = System.currentTimeMillis();
    synchronized (semaphore) {
      while (true) {
        if (!currentArray.isEmpty() || !paramBoolean || System.currentTimeMillis() - l > 9900L) {
          JSONArray jSONArray = new JSONArray(currentArray);
          JSONObject jSONObject = new JSONObject();
          try {
            jSONObject.put("status", "OK");
            jSONObject.put("values", jSONArray);
            currentArray.clear();
            return jSONObject.toString();
          } catch (JSONException jSONException) {
            Log.e("RetValManager", "Error fetching retvals", (Throwable)jSONException);
            return "{\"status\" : \"BAD\", \"message\" : \"Failure in RetValManager\"}";
          } 
        } 
        try {
          semaphore.wait(10000L);
        } catch (InterruptedException interruptedException) {}
      } 
    } 
  }
  
  public static void popScreen(String paramString) {
    synchronized (semaphore) {
      JSONObject jSONObject = new JSONObject();
      try {
        jSONObject.put("status", "OK");
        jSONObject.put("type", "popScreen");
        if (paramString != null)
          jSONObject.put("value", paramString.toString()); 
        boolean bool = currentArray.isEmpty();
        currentArray.add(jSONObject);
        if (PhoneStatus.getUseWebRTC()) {
          webRTCsendCurrent();
        } else if (bool) {
          semaphore.notifyAll();
        } 
        return;
      } catch (JSONException jSONException) {
        Log.e("RetValManager", "Error building retval", (Throwable)jSONException);
        return;
      } 
    } 
  }
  
  public static void pushScreen(String paramString, Object paramObject) {
    synchronized (semaphore) {
      JSONObject jSONObject = new JSONObject();
      try {
        jSONObject.put("status", "OK");
        jSONObject.put("type", "pushScreen");
        jSONObject.put("screen", paramString);
        if (paramObject != null)
          jSONObject.put("value", paramObject.toString()); 
        boolean bool = currentArray.isEmpty();
        currentArray.add(jSONObject);
        if (PhoneStatus.getUseWebRTC()) {
          webRTCsendCurrent();
        } else if (bool) {
          semaphore.notifyAll();
        } 
        return;
      } catch (JSONException jSONException) {
        Log.e("RetValManager", "Error building retval", (Throwable)jSONException);
        return;
      } 
    } 
  }
  
  public static void sendError(String paramString) {
    synchronized (semaphore) {
      JSONObject jSONObject = new JSONObject();
      try {
        jSONObject.put("status", "OK");
        jSONObject.put("type", "error");
        jSONObject.put("value", paramString);
        boolean bool = currentArray.isEmpty();
        currentArray.add(jSONObject);
        if (PhoneStatus.getUseWebRTC()) {
          webRTCsendCurrent();
        } else if (bool) {
          semaphore.notifyAll();
        } 
        return;
      } catch (JSONException jSONException) {
        Log.e("RetValManager", "Error building retval", (Throwable)jSONException);
        return;
      } 
    } 
  }
  
  private static void webRTCsendCurrent() {
    try {
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("status", "OK");
      jSONObject.put("values", new JSONArray(currentArray));
      ReplForm.returnRetvals(jSONObject.toString());
      currentArray.clear();
      return;
    } catch (JSONException jSONException) {
      Log.e("RetValManager", "Error building retval", (Throwable)jSONException);
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/RetValManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
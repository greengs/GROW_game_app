package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.runtime.CloudDB;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import redis.clients.jedis.JedisPubSub;

public class CloudDBJedisListener extends JedisPubSub {
  private static final boolean DEBUG = false;
  
  private static String LOG_TAG = "CloudDB";
  
  public CloudDB cloudDB;
  
  private Thread myThread;
  
  public CloudDBJedisListener(CloudDB paramCloudDB) {
    this.cloudDB = paramCloudDB;
    this.myThread = Thread.currentThread();
  }
  
  public void onMessage(String paramString1, String paramString2) {
    try {
      List<String> list = (List)JsonUtil.getObjectFromJson(paramString2, false);
      paramString1 = list.get(0);
      Iterator<Object> iterator = ((List)list.get(1)).iterator();
      while (true) {
        String str;
        if (iterator.hasNext()) {
          Object object = iterator.next();
          str = JsonUtil.getJsonRepresentationIfValueFileName((Context)this.cloudDB.getForm(), object);
          if (str == null) {
            this.cloudDB.DataChanged(paramString1, object);
            continue;
          } 
        } else {
          return;
        } 
        this.cloudDB.DataChanged(paramString1, str);
      } 
    } catch (JSONException jSONException) {
      Log.e(LOG_TAG, "onMessage: JSONException", (Throwable)jSONException);
      this.cloudDB.CloudDBError("System Error: " + jSONException.getMessage());
    } 
  }
  
  public void onSubscribe(String paramString, int paramInt) {}
  
  public void terminate() {
    this.myThread.interrupt();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/CloudDBJedisListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
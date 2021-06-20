package com.google.appinventor.components.runtime.util;

import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebServiceUtil {
  private static final WebServiceUtil INSTANCE = new WebServiceUtil();
  
  private static final String LOG_TAG = "WebServiceUtil";
  
  private static HttpClient httpClient = null;
  
  private static Object httpClientSynchronizer = new Object();
  
  public static WebServiceUtil getInstance() {
    synchronized (httpClientSynchronizer) {
      if (httpClient == null) {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", (SocketFactory)PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", (SocketFactory)SSLSocketFactory.getSocketFactory(), 443));
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, 20000);
        ConnManagerParams.setMaxTotalConnections((HttpParams)basicHttpParams, 20);
        httpClient = (HttpClient)new DefaultHttpClient((ClientConnectionManager)new ThreadSafeClientConnManager((HttpParams)basicHttpParams, schemeRegistry), (HttpParams)basicHttpParams);
      } 
      return INSTANCE;
    } 
  }
  
  public void postCommand(String paramString1, String paramString2, List<NameValuePair> paramList, AsyncCallbackPair<String> paramAsyncCallbackPair) {
    Log.d("WebServiceUtil", "Posting " + paramString2 + " to " + paramString1 + " with arguments " + paramList);
    if (paramString1 == null || paramString1.equals(""))
      paramAsyncCallbackPair.onFailure("No service url to post command to."); 
    HttpPost httpPost = new HttpPost(paramString1 + "/" + paramString2);
    List<NameValuePair> list = paramList;
    if (paramList == null)
      list = new ArrayList<NameValuePair>(); 
    try {
      BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
      httpPost.setEntity((HttpEntity)new UrlEncodedFormEntity(list, "UTF-8"));
      httpPost.setHeader("Accept", "application/json");
      paramAsyncCallbackPair.onSuccess((String)httpClient.execute((HttpUriRequest)httpPost, (ResponseHandler)basicResponseHandler));
      return;
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      Log.w("WebServiceUtil", unsupportedEncodingException);
      paramAsyncCallbackPair.onFailure("Failed to encode params for web service call.");
      return;
    } catch (ClientProtocolException clientProtocolException) {
      Log.w("WebServiceUtil", (Throwable)clientProtocolException);
      paramAsyncCallbackPair.onFailure("Communication with the web service encountered a protocol exception.");
      return;
    } catch (IOException iOException) {
      Log.w("WebServiceUtil", iOException);
      paramAsyncCallbackPair.onFailure("Communication with the web service timed out.");
      return;
    } 
  }
  
  public void postCommandReturningArray(String paramString1, String paramString2, List<NameValuePair> paramList, final AsyncCallbackPair<JSONArray> callback) {
    postCommand(paramString1, paramString2, paramList, new AsyncCallbackPair<String>() {
          public void onFailure(String param1String) {
            callback.onFailure(param1String);
          }
          
          public void onSuccess(String param1String) {
            try {
              callback.onSuccess(new JSONArray(param1String));
              return;
            } catch (JSONException jSONException) {
              callback.onFailure(jSONException.getMessage());
              return;
            } 
          }
        });
  }
  
  public void postCommandReturningObject(String paramString1, String paramString2, List<NameValuePair> paramList, final AsyncCallbackPair<JSONObject> callback) {
    postCommand(paramString1, paramString2, paramList, new AsyncCallbackPair<String>() {
          public void onFailure(String param1String) {
            callback.onFailure(param1String);
          }
          
          public void onSuccess(String param1String) {
            try {
              callback.onSuccess(new JSONObject(param1String));
              return;
            } catch (JSONException jSONException) {
              callback.onFailure(jSONException.getMessage());
              return;
            } 
          }
        });
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/WebServiceUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
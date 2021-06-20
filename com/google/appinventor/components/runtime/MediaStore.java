package com.google.appinventor.components.runtime;

import android.os.Handler;
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
import com.google.appinventor.components.runtime.util.AsyncCallbackPair;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@DesignerComponent(category = ComponentCategory.INTERNAL, description = "Non-visible component that communicates with a Web service and stores media files.", iconName = "images/mediastore.png", nonVisible = true, version = 1)
@SimpleObject
@UsesLibraries({"httpmime.jar"})
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public final class MediaStore extends AndroidNonvisibleComponent implements Component {
  private static final String LOG_TAG_COMPONENT = "MediaStore: ";
  
  private Handler androidUIHandler;
  
  protected final ComponentContainer componentContainer;
  
  private String serviceURL;
  
  public MediaStore(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.componentContainer = paramComponentContainer;
    this.androidUIHandler = new Handler();
    this.serviceURL = "http://ai-mediaservice.appspot.com";
  }
  
  private String getUploadUrl() {
    try {
      HttpURLConnection httpURLConnection = (HttpURLConnection)(new URL(this.serviceURL)).openConnection();
      httpURLConnection.setRequestMethod("GET");
      httpURLConnection.setRequestProperty("User-Agent", "AppInventor");
      httpURLConnection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
      StringBuilder stringBuilder = new StringBuilder();
      while (true) {
        String str = bufferedReader.readLine();
        if (str != null) {
          stringBuilder.append(str);
          continue;
        } 
        bufferedReader.close();
        return stringBuilder.toString();
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
      return "";
    } 
  }
  
  @SimpleEvent
  public void MediaStored(String paramString) {
    EventDispatcher.dispatchEvent(this, "MediaStored", new Object[] { paramString });
  }
  
  @SimpleFunction
  public void PostMedia(String paramString) throws FileNotFoundException {
    AsyncCallbackPair<String> asyncCallbackPair = new AsyncCallbackPair<String>() {
        public void onFailure(final String message) {
          MediaStore.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  MediaStore.this.WebServiceError(message);
                }
              });
        }
        
        public void onSuccess(final String response) {
          MediaStore.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  MediaStore.this.MediaStored(response);
                }
              });
        }
      };
    try {
      DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
      MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
      multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
      if (paramString.split("/")[0].equals("file:"))
        paramString = (new File((new URL(paramString)).toURI())).getAbsolutePath(); 
      multipartEntityBuilder.addPart("file", (ContentBody)new FileBody(new File(paramString)));
      HttpEntity httpEntity = multipartEntityBuilder.build();
      HttpPost httpPost = new HttpPost(getUploadUrl());
      httpPost.setEntity(httpEntity);
      asyncCallbackPair.onSuccess(EntityUtils.toString(defaultHttpClient.execute((HttpUriRequest)httpPost).getEntity()));
      return;
    } catch (Exception exception) {
      exception.printStackTrace();
      asyncCallbackPair.onFailure(exception.getMessage());
      return;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String ServiceURL() {
    return this.serviceURL;
  }
  
  @DesignerProperty(defaultValue = "http://ai-mediaservice.appspot.com", editorType = "string")
  @SimpleProperty
  public void ServiceURL(String paramString) {
    this.serviceURL = paramString;
  }
  
  @SimpleEvent
  public void WebServiceError(String paramString) {
    EventDispatcher.dispatchEvent(this, "WebServiceError", new Object[] { paramString });
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/MediaStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
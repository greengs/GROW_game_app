package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.text.TextUtils;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "Use this component to translate words and sentences between different languages. This component needs Internet access, as it will request translations to the Yandex.Translate service. Specify the source and target language in the form source-target using two letter language codes. So\"en-es\" will translate from English to Spanish while \"es-ru\" will translate from Spanish to Russian. If you leave out the source language, the service will attempt to detect the source language. So providing just \"es\" will attempt to detect the source language and translate it to Spanish.<p /> This component is powered by the Yandex translation service.  See http://api.yandex.com/translate/ for more information, including the list of available languages and the meanings of the language codes and status codes. <p />Note: Translation happens asynchronously in the background. When the translation is complete, the \"GotTranslation\" event is triggered.", iconName = "images/yandex.png", nonVisible = true, version = 2)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public final class YandexTranslate extends AndroidNonvisibleComponent {
  public static final String YANDEX_TRANSLATE_SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";
  
  private final Activity activity;
  
  private final byte[] key1 = new byte[] { 
      -127, -88, 79, 80, 65, 112, -80, 87, -62, 126, 
      -125, -25, -31, 55, 107, -42, -63, -62, 33, -122, 
      1, 89, -33, 23, -19, 18, -81, 37, -67, 114, 
      92, -60, -76, -50, -59, -49, -114, -64, -96, -75, 
      117, -116, 53, -8, 44, 111, 120, 48, 41, 30, 
      85, -116, -31, 17, 87, -89, -49, -51, 47, 92, 
      121, -58, -80, -25, 86, 123, -36, -9, 101, -112, 
      -22, -28, -29, -14, -125, 46, -103, -36, 125, 114, 
      35, -31, 1, 123 };
  
  private final byte[] key2 = new byte[] { 
      -11, -38, 33, 35, 45, 94, -127, 121, -13, 80, 
      -79, -41, -48, 3, 91, -29, -15, -9, 117, -74, 
      49, 105, -26, 34, -35, 72, -127, 64, -116, 69, 
      111, -12, -48, -81, -11, -83, -69, -12, -108, -42, 
      65, -72, 86, -42, 27, 12, 26, 2, 28, 122, 
      51, -24, -45, 36, 54, -106, -87, -3, 27, 62, 
      65, -16, -126, -42, 99, 77, -70, -49, 83, -12, 
      -114, -35, -44, -109, -77, 28, -84, -66, 72, 22, 
      18, -126, 50, 78 };
  
  private String userYandexKey = "DEFAULT";
  
  private final String yandexKey;
  
  public YandexTranslate(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.form.setYandexTranslateTagline();
    this.yandexKey = gk();
    this.activity = paramComponentContainer.$context();
  }
  
  private static String getResponseContent(HttpURLConnection paramHttpURLConnection) throws IOException {
    String str2 = paramHttpURLConnection.getContentEncoding();
    String str1 = str2;
    if (str2 == null)
      str1 = "UTF-8"; 
    InputStreamReader inputStreamReader = new InputStreamReader(paramHttpURLConnection.getInputStream(), str1);
    try {
      StringBuilder stringBuilder;
      int i = paramHttpURLConnection.getContentLength();
      if (i != -1) {
        stringBuilder = new StringBuilder(i);
      } else {
        stringBuilder = new StringBuilder();
      } 
      char[] arrayOfChar = new char[1024];
      while (true) {
        i = inputStreamReader.read(arrayOfChar);
        if (i != -1) {
          stringBuilder.append(arrayOfChar, 0, i);
          continue;
        } 
        return stringBuilder.toString();
      } 
    } finally {
      inputStreamReader.close();
    } 
  }
  
  private String gk() {
    byte[] arrayOfByte = new byte[this.key1.length];
    for (int i = 0; i < this.key1.length; i++)
      arrayOfByte[i] = (byte)(this.key1[i] ^ this.key2[i]); 
    return new String(arrayOfByte);
  }
  
  private void performRequest(String paramString1, final String responseCode) throws IOException, JSONException {
    String str;
    StringBuilder stringBuilder = (new StringBuilder()).append("https://translate.yandex.net/api/v1.5/tr.json/translate?key=");
    if (TextUtils.equals(this.userYandexKey, "DEFAULT") || TextUtils.isEmpty(this.userYandexKey)) {
      str = this.yandexKey;
    } else {
      str = this.userYandexKey;
    } 
    HttpURLConnection httpURLConnection = (HttpURLConnection)(new URL(stringBuilder.append(str).append("&lang=").append(paramString1).append("&text=").append(URLEncoder.encode(responseCode, "UTF-8")).toString())).openConnection();
    if (httpURLConnection != null)
      try {
        JSONObject jSONObject = new JSONObject(getResponseContent(httpURLConnection));
        responseCode = jSONObject.getString("code");
        final String translation = (String)jSONObject.getJSONArray("text").get(0);
        this.activity.runOnUiThread(new Runnable() {
              public void run() {
                YandexTranslate.this.GotTranslation(responseCode, translation);
              }
            });
        return;
      } finally {
        httpURLConnection.disconnect();
      }  
  }
  
  @DesignerProperty(defaultValue = "DEFAULT", editorType = "string")
  @SimpleProperty(description = "Set the API Key to use with Yandex. You do not need to set this if you are using the MIT system because MIT has its own key builtin. If set, the key provided here will be used instead")
  public void ApiKey(String paramString) {
    this.userYandexKey = paramString;
  }
  
  @SimpleEvent(description = "Event triggered when the Yandex.Translate service returns the translated text. This event also provides a response code for error handling. If the responseCode is not 200, then something went wrong with the call, and the translation will not be available.")
  public void GotTranslation(String paramString1, String paramString2) {
    EventDispatcher.dispatchEvent(this, "GotTranslation", new Object[] { paramString1, paramString2 });
  }
  
  @SimpleFunction(description = "By providing a target language to translate to (for instance, 'es' for Spanish, 'en' for English, or 'ru' for Russian), and a word or sentence to translate, this method will request a translation to the Yandex.Translate service.\nOnce the text is translated by the external service, the event GotTranslation will be executed.\nNote: Yandex.Translate will attempt to detect the source language. You can also specify prepending it to the language translation. I.e., es-ru will specify Spanish to Russian translation.")
  public void RequestTranslation(final String languageToTranslateTo, final String textToTranslate) {
    if (TextUtils.isEmpty(this.yandexKey) && (TextUtils.isEmpty(this.userYandexKey) || TextUtils.equals(this.userYandexKey, "DEFAULT"))) {
      this.form.dispatchErrorOccurredEvent(this, "RequestTranslation", 2201, new Object[0]);
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            try {
              YandexTranslate.this.performRequest(languageToTranslateTo, textToTranslate);
              return;
            } catch (IOException iOException) {
              YandexTranslate.this.form.dispatchErrorOccurredEvent(YandexTranslate.this, "RequestTranslation", 2202, new Object[0]);
              return;
            } catch (JSONException jSONException) {
              YandexTranslate.this.form.dispatchErrorOccurredEvent(YandexTranslate.this, "RequestTranslation", 2203, new Object[0]);
              return;
            } 
          }
        });
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/YandexTranslate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
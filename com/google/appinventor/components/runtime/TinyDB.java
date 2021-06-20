package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.JsonUtil;
import java.util.ArrayList;
import java.util.Collections;
import org.json.JSONException;

@DesignerComponent(category = ComponentCategory.STORAGE, description = "TinyDB is a non-visible component that stores data for an app. <p> Apps created with App Inventor are initialized each time they run: If an app sets the value of a variable and the user then quits the app, the value of that variable will not be remembered the next time the app is run. In contrast, TinyDB is a <em> persistent </em> data store for the app, that is, the data stored there will be available each time the app is run. An example might be a game that saves the high score and retrieves it each time the game is played. </<p> <p> Data items are strings stored under <em>tags</em> . To store a data item, you specify the tag it should be stored under.  Subsequently, you can retrieve the data that was stored under a given tag. </p><p> There is only one data store per app. Even if you have multiple TinyDB components, they will use the same data store. To get the effect of separate stores, use different keys. Also each app has its own data store. You cannot use TinyDB to pass data between two different apps on the phone, although you <em>can</em> use TinyDb to shares data between the different screens of a multi-screen app. </p> <p>When you are developing apps using the AI Companion, all the apps using that companion will share the same TinyDb.  That sharing will disappear once the apps are packaged.  But, during development, you should be careful to clear the TinyDb each time you start working on a new app.</p>", iconName = "images/tinyDB.png", nonVisible = true, version = 2)
@SimpleObject
public class TinyDB extends AndroidNonvisibleComponent implements Component, Deleteable {
  public static final String DEFAULT_NAMESPACE = "TinyDB1";
  
  private Context context;
  
  private String namespace;
  
  private SharedPreferences sharedPreferences;
  
  public TinyDB(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.context = (Context)paramComponentContainer.$context();
    Namespace("TinyDB1");
  }
  
  @SimpleFunction(description = "Clear the entire data store.")
  public void ClearAll() {
    SharedPreferences.Editor editor = this.sharedPreferences.edit();
    editor.clear();
    editor.commit();
  }
  
  @SimpleFunction(description = "Clear the entry with the given tag.")
  public void ClearTag(String paramString) {
    SharedPreferences.Editor editor = this.sharedPreferences.edit();
    editor.remove(paramString);
    editor.commit();
  }
  
  @SimpleFunction(description = "Return a list of all the tags in the data store.")
  public Object GetTags() {
    ArrayList<Comparable> arrayList = new ArrayList();
    arrayList.addAll(this.sharedPreferences.getAll().keySet());
    Collections.sort(arrayList);
    return arrayList;
  }
  
  @SimpleFunction(description = "Retrieve the value stored under the given tag. If there's no such tag, then return valueIfTagNotThere.")
  public Object GetValue(String paramString, Object paramObject) {
    try {
      paramString = this.sharedPreferences.getString(paramString, "");
      return (paramString.length() == 0) ? paramObject : JsonUtil.getObjectFromJson(paramString, true);
    } catch (JSONException jSONException) {
      throw new YailRuntimeError("Value failed to convert from JSON.", "JSON Creation Error.");
    } 
  }
  
  @SimpleProperty(description = "Namespace for storing data.")
  public String Namespace() {
    return this.namespace;
  }
  
  @DesignerProperty(defaultValue = "TinyDB1", editorType = "string")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Namespace for storing data.")
  public void Namespace(String paramString) {
    this.namespace = paramString;
    this.sharedPreferences = this.context.getSharedPreferences(paramString, 0);
  }
  
  @SimpleFunction(description = "Store the given value under the given tag.  The storage persists on the phone when the app is restarted.")
  public void StoreValue(String paramString, Object paramObject) {
    SharedPreferences.Editor editor = this.sharedPreferences.edit();
    try {
      editor.putString(paramString, JsonUtil.getJsonRepresentation(paramObject));
      editor.commit();
      return;
    } catch (JSONException jSONException) {
      throw new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
    } 
  }
  
  public void onDelete() {
    SharedPreferences.Editor editor = this.sharedPreferences.edit();
    editor.clear();
    editor.commit();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/TinyDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
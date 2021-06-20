package com.google.appinventor.components.runtime;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.NougatUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.File;

@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "A component that can launch an activity using the <code>StartActivity</code> method. \n<p>Activities that can be launched include:<ul> <li> Starting another App Inventor for Android app. \n To do so, first      find out the <em>class</em> of the other application by      downloading the source code and using a file explorer or unzip      utility to find a file named      \"youngandroidproject/project.properties\".  \n The first line of      the file will start with \"main=\" and be followed by the class      name; for example,      <code>main=com.gmail.Bitdiddle.Ben.HelloPurr.Screen1</code>.       (The first components indicate that it was created by      Ben.Bitdiddle@gmail.com.)  \n To make your      <code>ActivityStarter</code> launch this application, set the      following properties: <ul>\n      <li> <code>ActivityPackage</code> to the class name, dropping the           last component (for example,           <code>com.gmail.Bitdiddle.Ben.HelloPurr</code>)</li>\n      <li> <code>ActivityClass</code> to the entire class name (for           example,           <code>com.gmail.Bitdiddle.Ben.HelloPurr.Screen1</code>)</li>      </ul></li> \n<li> Starting the camera application by setting the following      properties:<ul> \n     <li> <code>Action: android.intent.action.MAIN</code> </li> \n     <li> <code>ActivityPackage: com.android.camera</code> </li> \n     <li> <code>ActivityClass: com.android.camera.Camera</code></li>\n      </ul></li>\n<li> Performing web search.  Assuming the term you want to search      for is \"vampire\" (feel free to substitute your own choice), \n     set the properties to:\n<ul><code>     <li>Action: android.intent.action.WEB_SEARCH</li>      <li>ExtraKey: query</li>      <li>ExtraValue: vampire</li>      <li>ActivityPackage: com.google.android.providers.enhancedgooglesearch</li>     <li>ActivityClass: com.google.android.providers.enhancedgooglesearch.Launcher</li>      </code></ul></li> \n<li> Opening a browser to a specified web page.  Assuming the page you      want to go to is \"www.facebook.com\" (feel free to substitute      your own choice), set the properties to:\n<ul><code>      <li>Action: android.intent.action.VIEW</li>      <li>DataUri: http://www.facebook.com</li> </code> </ul> </li> </ul></p>", designerHelpDescription = "A component that can launch an activity using the <code>StartActivity</code> method.<p>Activities that can be launched include: <ul> \n<li> starting other App Inventor for Android apps </li> \n<li> starting the camera application </li> \n<li> performing web search </li> \n<li> opening a browser to a specified web page</li> \n<li> opening the map application to a specified location</li></ul> \nYou can also launch activities that return text data.  See the documentation on using the Activity Starter for examples.</p>", iconName = "images/activityStarter.png", nonVisible = true, version = 6)
@SimpleObject
public class ActivityStarter extends AndroidNonvisibleComponent implements ActivityResultListener, Component, Deleteable {
  private static final String LOG_TAG = "ActivityStarter";
  
  private String action;
  
  private String activityClass;
  
  private String activityPackage;
  
  private final ComponentContainer container;
  
  private String dataType;
  
  private String dataUri;
  
  private String extraKey;
  
  private String extraValue;
  
  private YailList extras;
  
  private int requestCode;
  
  private String result;
  
  private Intent resultIntent;
  
  private String resultName;
  
  public ActivityStarter(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.container = paramComponentContainer;
    this.result = "";
    Action("android.intent.action.MAIN");
    ActivityPackage("");
    ActivityClass("");
    DataUri("");
    DataType("");
    ExtraKey("");
    ExtraValue("");
    Extras(new YailList());
    ResultName("");
  }
  
  private Intent buildActivityIntent() {
    Uri uri1;
    if (this.dataUri.length() != 0) {
      uri1 = Uri.parse(this.dataUri);
    } else {
      uri1 = null;
    } 
    Intent intent2 = new Intent(this.action);
    Intent intent1 = intent2;
    Uri uri2 = uri1;
    if (uri1 != null) {
      intent1 = intent2;
      uri2 = uri1;
      if (this.dataUri.toLowerCase().startsWith("file://")) {
        Log.d("ActivityStarter", "Using file://");
        File file = new File(uri1.getPath());
        intent1 = intent2;
        uri2 = uri1;
        if (file.isFile()) {
          Log.d("ActivityStarter", "It's a file");
          uri2 = NougatUtil.getPackageUri(this.form, file);
          intent1 = new Intent(this.action);
          intent1.setFlags(1);
          Log.d("ActivityStarter", "added permissions");
        } 
      } 
    } 
    if (TextUtils.isEmpty(Action()))
      return null; 
    if (this.dataType.length() != 0) {
      if (uri2 != null) {
        intent1.setDataAndType(uri2, this.dataType);
      } else {
        intent1.setType(this.dataType);
      } 
    } else {
      intent1.setData(uri2);
    } 
    if (this.activityPackage.length() != 0 || this.activityClass.length() != 0) {
      intent1.setComponent(new ComponentName(this.activityPackage, this.activityClass));
    } else if (Action().equals("android.intent.action.MAIN")) {
      return null;
    } 
    if (this.extraKey.length() != 0 && this.extraValue.length() != 0) {
      Log.i("ActivityStarter", "Adding extra, key = " + this.extraKey + " value = " + this.extraValue);
      intent1.putExtra(this.extraKey, this.extraValue);
    } 
    Object[] arrayOfObject = this.extras.toArray();
    int j = arrayOfObject.length;
    int i = 0;
    while (true) {
      String str;
      Intent intent = intent1;
      if (i < j) {
        YailList yailList = (YailList)arrayOfObject[i];
        str = yailList.getString(0);
        Object object = yailList.getObject(1);
        Log.i("ActivityStarter", "Adding extra, key = " + str + " value = " + object);
        if (str.length() != 0)
          if (object instanceof YailList) {
            Log.i("ActivityStarter", "Adding extra list, key = " + str + " value = " + object);
            intent1.putExtra(str, ((YailList)object).toStringArray());
          } else {
            String str1 = yailList.getString(1);
            Log.i("ActivityStarter", "Adding extra string, key = " + str + " value = " + str1);
            intent1.putExtra(str, str1);
          }  
        i++;
        continue;
      } 
      return (Intent)str;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String Action() {
    return this.action;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void Action(String paramString) {
    this.action = paramString.trim();
  }
  
  @SimpleEvent(description = "Event raised if this ActivityStarter returns because the activity was canceled.")
  public void ActivityCanceled() {
    EventDispatcher.dispatchEvent(this, "ActivityCanceled", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String ActivityClass() {
    return this.activityClass;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void ActivityClass(String paramString) {
    this.activityClass = paramString.trim();
  }
  
  @SimpleEvent(description = "The ActivityError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible = false)
  public void ActivityError(String paramString) {}
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String ActivityPackage() {
    return this.activityPackage;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void ActivityPackage(String paramString) {
    this.activityPackage = paramString.trim();
  }
  
  @SimpleEvent(description = "Event raised after this ActivityStarter returns.")
  public void AfterActivity(String paramString) {
    EventDispatcher.dispatchEvent(this, "AfterActivity", new Object[] { paramString });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String DataType() {
    return this.dataType;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void DataType(String paramString) {
    this.dataType = paramString.trim();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String DataUri() {
    return this.dataUri;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void DataUri(String paramString) {
    this.dataUri = paramString.trim();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the extra key that will be passed to the activity.\nDEPRECATED: New code should use Extras property instead.")
  public String ExtraKey() {
    return this.extraKey;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void ExtraKey(String paramString) {
    this.extraKey = paramString.trim();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the extra value that will be passed to the activity.\nDEPRECATED: New code should use Extras property instead.")
  public String ExtraValue() {
    return this.extraValue;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void ExtraValue(String paramString) {
    this.extraValue = paramString.trim();
  }
  
  @SimpleProperty
  public YailList Extras() {
    return this.extras;
  }
  
  @SimpleProperty
  public void Extras(YailList paramYailList) {
    for (Object object : paramYailList.toArray()) {
      boolean bool;
      boolean bool1 = object instanceof YailList;
      if (bool1) {
        if (((YailList)object).size() == 2) {
          bool = true;
        } else {
          bool = false;
        } 
      } else {
        bool = false;
      } 
      if (!bool1 || !bool)
        throw new YailRuntimeError("Argument to Extras should be a list of pairs", "ActivityStarter Error"); 
    } 
    this.extras = paramYailList;
  }
  
  @SimpleFunction(description = "Returns the name of the activity that corresponds to this ActivityStarter, or an empty string if no corresponding activity can be found.")
  public String ResolveActivity() {
    Intent intent = buildActivityIntent();
    ResolveInfo resolveInfo = this.container.$context().getPackageManager().resolveActivity(intent, 0);
    return (resolveInfo != null && resolveInfo.activityInfo != null) ? resolveInfo.activityInfo.name : "";
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String Result() {
    return this.result;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String ResultName() {
    return this.resultName;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void ResultName(String paramString) {
    this.resultName = paramString.trim();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String ResultType() {
    if (this.resultIntent != null) {
      String str = this.resultIntent.getType();
      if (str != null)
        return str; 
    } 
    return "";
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String ResultUri() {
    if (this.resultIntent != null) {
      String str = this.resultIntent.getDataString();
      if (str != null)
        return str; 
    } 
    return "";
  }
  
  @SimpleFunction(description = "Start the activity corresponding to this ActivityStarter.")
  public void StartActivity() {
    this.resultIntent = null;
    this.result = "";
    Intent intent = buildActivityIntent();
    if (this.requestCode == 0)
      this.requestCode = this.form.registerForActivityResult(this); 
    if (intent == null) {
      this.form.dispatchErrorOccurredEvent(this, "StartActivity", 602, new Object[0]);
      return;
    } 
    try {
      this.container.$context().startActivityForResult(intent, this.requestCode);
      String str = this.container.$form().getOpenAnimType();
      AnimationUtil.ApplyOpenScreenAnimation(this.container.$context(), str);
      return;
    } catch (ActivityNotFoundException activityNotFoundException) {
      this.form.dispatchErrorOccurredEvent(this, "StartActivity", 601, new Object[0]);
      return;
    } 
  }
  
  public void onDelete() {
    this.form.unregisterForActivityResult(this);
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent) {
    if (paramInt1 == this.requestCode) {
      Log.i("ActivityStarter", "resultReturned - resultCode = " + paramInt2);
      if (paramInt2 == -1) {
        this.resultIntent = paramIntent;
        if (this.resultName.length() != 0 && this.resultIntent != null && this.resultIntent.hasExtra(this.resultName)) {
          this.result = this.resultIntent.getStringExtra(this.resultName);
        } else {
          this.result = "";
        } 
        AfterActivity(this.result);
        return;
      } 
    } else {
      return;
    } 
    if (paramInt2 == 0) {
      ActivityCanceled();
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/ActivityStarter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
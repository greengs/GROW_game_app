package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.appinventor.common.version.AppInventorFeatures;
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
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.collect.Sets;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.multidex.MultiDex;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.google.appinventor.components.runtime.util.PaintUtil;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.json.JSONException;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Top-level component containing all other components in the program", showOnPalette = false, version = 27)
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE"})
public class Form extends AppInventorCompatActivity implements Component, ComponentContainer, HandlesEventDispatching, ViewTreeObserver.OnGlobalLayoutListener {
  public static final String APPINVENTOR_URL_SCHEME = "appinventor";
  
  private static final String ARGUMENT_NAME = "APP_INVENTOR_START";
  
  public static final String ASSETS_PREFIX = "file:///android_asset/";
  
  private static final int DEFAULT_ACCENT_COLOR;
  
  private static final int DEFAULT_PRIMARY_COLOR_DARK = PaintUtil.hexStringToInt("&HFF303F9F");
  
  private static final String LOG_TAG = "Form";
  
  public static final int MAX_PERMISSION_NONCE = 100000;
  
  private static final String RESULT_NAME = "APP_INVENTOR_RESULT";
  
  private static final int SWITCH_FORM_REQUEST_CODE = 1;
  
  private static boolean _initialized;
  
  protected static Form activeForm;
  
  private static boolean applicationIsBeingClosed;
  
  private static long minimumToastWait;
  
  private static int nextRequestCode;
  
  private static boolean sCompatibilityMode;
  
  private static boolean showListsAsJson;
  
  private String aboutScreen;
  
  private int accentColor = DEFAULT_ACCENT_COLOR;
  
  private boolean actionBarEnabled = false;
  
  private final HashMap<Integer, ActivityResultListener> activityResultMap = Maps.newHashMap();
  
  private final Map<Integer, Set<ActivityResultListener>> activityResultMultiMap = Maps.newHashMap();
  
  private AlignmentUtil alignmentSetter;
  
  protected final Handler androidUIHandler = new Handler();
  
  private int backgroundColor;
  
  private Drawable backgroundDrawable;
  
  private String backgroundImagePath = "";
  
  private String closeAnimType;
  
  private float compatScalingFactor;
  
  private float deviceDensity;
  
  private LinkedHashMap<Integer, PercentStorageRecord> dimChanges = new LinkedHashMap<Integer, PercentStorageRecord>();
  
  private int formHeight;
  
  protected String formName;
  
  private int formWidth;
  
  private FrameLayout frameLayout;
  
  private FullScreenVideoUtil fullScreenVideoUtil;
  
  private int horizontalAlignment;
  
  private boolean keyboardShown = false;
  
  private long lastToastTime = System.nanoTime() - minimumToastWait;
  
  private String nextFormName;
  
  private final Set<OnClearListener> onClearListeners = Sets.newHashSet();
  
  private final Set<OnCreateOptionsMenuListener> onCreateOptionsMenuListeners = Sets.newHashSet();
  
  private final Set<OnDestroyListener> onDestroyListeners = Sets.newHashSet();
  
  private final Set<OnInitializeListener> onInitializeListeners = Sets.newHashSet();
  
  private final Set<OnNewIntentListener> onNewIntentListeners = Sets.newHashSet();
  
  private final Set<OnOptionsItemSelectedListener> onOptionsItemSelectedListeners = Sets.newHashSet();
  
  private final Set<OnOrientationChangeListener> onOrientationChangeListeners = Sets.newHashSet();
  
  private final Set<OnPauseListener> onPauseListeners = Sets.newHashSet();
  
  private final Set<OnResumeListener> onResumeListeners = Sets.newHashSet();
  
  private final Set<OnStopListener> onStopListeners = Sets.newHashSet();
  
  private String openAnimType;
  
  private final HashMap<Integer, PermissionResultHandler> permissionHandlers = Maps.newHashMap();
  
  private final Random permissionRandom = new Random();
  
  private final Set<String> permissions = new HashSet<String>();
  
  private int primaryColor = DEFAULT_PRIMARY_COLOR;
  
  private int primaryColorDark = DEFAULT_PRIMARY_COLOR_DARK;
  
  private ProgressDialog progress;
  
  private ScaledFrameLayout scaleLayout;
  
  private boolean screenInitialized;
  
  private boolean scrollable;
  
  private boolean showStatusBar = true;
  
  private boolean showTitle = true;
  
  protected String startupValue = "";
  
  protected String title = "";
  
  private boolean usesDarkTheme;
  
  private boolean usesDefaultBackground;
  
  private int verticalAlignment;
  
  private LinearLayout viewLayout;
  
  private String yandexTranslateTagline = "";
  
  static {
    DEFAULT_ACCENT_COLOR = PaintUtil.hexStringToInt("&HFFFF4081");
    nextRequestCode = 2;
    minimumToastWait = 10000000000L;
    _initialized = false;
  }
  
  private void closeApplication() {
    applicationIsBeingClosed = true;
    finish();
    if (this.formName.equals("Screen1"))
      System.exit(0); 
  }
  
  private void closeApplicationFromMenu() {
    closeApplication();
  }
  
  private static Object decodeJSONStringForForm(String paramString1, String paramString2) {
    Log.i("Form", "decodeJSONStringForForm -- decoding JSON representation:" + paramString1);
    Object object = "";
    try {
      Object object1 = JsonUtil.getObjectFromJson(paramString1, true);
      object = object1;
      Log.i("Form", "decodeJSONStringForForm -- got decoded JSON:" + object1.toString());
      return object1;
    } catch (JSONException jSONException) {
      activeForm.dispatchErrorOccurredEvent(activeForm, paramString2, 903, new Object[] { paramString1 });
      return object;
    } 
  }
  
  private void defaultPropertyValues() {
    if (isRepl()) {
      ActionBar(this.actionBarEnabled);
    } else {
      ActionBar(this.themeHelper.hasActionBar());
    } 
    Scrollable(false);
    Sizing("Responsive");
    AboutScreen("");
    BackgroundImage("");
    AlignHorizontal(1);
    AlignVertical(1);
    Title("");
    ShowStatusBar(true);
    TitleVisible(true);
    ShowListsAsJson(true);
    ActionBar(false);
    AccentColor(DEFAULT_ACCENT_COLOR);
    PrimaryColor(DEFAULT_PRIMARY_COLOR);
    PrimaryColorDark(DEFAULT_PRIMARY_COLOR_DARK);
    Theme("Classic");
    ScreenOrientation("unspecified");
    BackgroundColor(0);
  }
  
  public static void finishActivity() {
    if (activeForm != null) {
      activeForm.closeForm((Intent)null);
      return;
    } 
    throw new IllegalStateException("activeForm is null");
  }
  
  public static void finishActivityWithResult(Object paramObject) {
    if (activeForm != null) {
      if (activeForm instanceof ReplForm) {
        ((ReplForm)activeForm).setResult(paramObject);
        activeForm.closeForm((Intent)null);
        return;
      } 
      paramObject = jsonEncodeForForm(paramObject, "close screen with value");
      Intent intent = new Intent();
      intent.putExtra("APP_INVENTOR_RESULT", (String)paramObject);
      activeForm.closeForm(intent);
      return;
    } 
    throw new IllegalStateException("activeForm is null");
  }
  
  public static void finishActivityWithTextResult(String paramString) {
    if (activeForm != null) {
      Intent intent = new Intent();
      intent.putExtra("APP_INVENTOR_RESULT", paramString);
      activeForm.closeForm(intent);
      return;
    } 
    throw new IllegalStateException("activeForm is null");
  }
  
  public static void finishApplication() {
    if (activeForm != null) {
      activeForm.closeApplicationFromBlocks();
      return;
    } 
    throw new IllegalStateException("activeForm is null");
  }
  
  private Integer generateHashCode(AndroidViewComponent paramAndroidViewComponent, PercentStorageRecord.Dim paramDim) {
    return (paramDim == PercentStorageRecord.Dim.HEIGHT) ? Integer.valueOf(paramAndroidViewComponent.hashCode() * 2 + 1) : Integer.valueOf(paramAndroidViewComponent.hashCode() * 2);
  }
  
  private static int generateNewRequestCode() {
    int i = nextRequestCode;
    nextRequestCode = i + 1;
    return i;
  }
  
  public static Form getActiveForm() {
    return activeForm;
  }
  
  public static boolean getCompatibilityMode() {
    return sCompatibilityMode;
  }
  
  public static String getStartText() {
    if (activeForm != null)
      return activeForm.startupValue; 
    throw new IllegalStateException("activeForm is null");
  }
  
  public static Object getStartValue() {
    if (activeForm != null)
      return decodeJSONStringForForm(activeForm.startupValue, "get start value"); 
    throw new IllegalStateException("activeForm is null");
  }
  
  protected static String jsonEncodeForForm(Object paramObject, String paramString) {
    String str = "";
    Log.i("Form", "jsonEncodeForForm -- creating JSON representation:" + paramObject.toString());
    try {
      String str1 = JsonUtil.getJsonRepresentation(paramObject);
      str = str1;
      Log.i("Form", "jsonEncodeForForm -- got JSON representation:" + str1);
      return str1;
    } catch (JSONException jSONException) {
      activeForm.dispatchErrorOccurredEvent(activeForm, paramString, 904, new Object[] { paramObject.toString() });
      return str;
    } 
  }
  
  private void onCreateFinish2() {
    defaultPropertyValues();
    Intent intent = getIntent();
    if (intent != null && intent.hasExtra("APP_INVENTOR_START"))
      this.startupValue = intent.getStringExtra("APP_INVENTOR_START"); 
    this.fullScreenVideoUtil = new FullScreenVideoUtil(this, this.androidUIHandler);
    int i = (getWindow().getAttributes()).softInputMode;
    getWindow().setSoftInputMode(i | 0x10);
    $define();
    Initialize();
  }
  
  private void populatePermissions() {
    try {
      PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 4096);
      Collections.addAll(this.permissions, packageInfo.requestedPermissions);
      return;
    } catch (Exception exception) {
      Log.e("Form", "Exception while attempting to learn permissions.", exception);
      return;
    } 
  }
  
  private void recomputeLayout() {
    boolean bool;
    Log.d("Form", "recomputeLayout called");
    if (this.frameLayout != null)
      this.frameLayout.removeAllViews(); 
    if (this.titleBar != null && this.titleBar.getParent() == this.frameWithTitle) {
      bool = true;
    } else {
      bool = false;
    } 
    this.frameWithTitle.removeAllViews();
    if (bool)
      this.frameWithTitle.addView((View)this.titleBar, new ViewGroup.LayoutParams(-1, -2)); 
    if (this.scrollable) {
      this.frameLayout = (FrameLayout)new ScrollView((Context)this);
      if (Build.VERSION.SDK_INT >= 24)
        ((ScrollView)this.frameLayout).setFillViewport(true); 
    } else {
      this.frameLayout = new FrameLayout((Context)this);
    } 
    this.frameLayout.addView((View)this.viewLayout.getLayoutManager(), new ViewGroup.LayoutParams(-1, -1));
    setBackground((View)this.frameLayout);
    Log.d("Form", "About to create a new ScaledFrameLayout");
    this.scaleLayout = new ScaledFrameLayout((Context)this);
    this.scaleLayout.addView((View)this.frameLayout, new ViewGroup.LayoutParams(-1, -1));
    this.frameWithTitle.addView((View)this.scaleLayout, new ViewGroup.LayoutParams(-1, -1));
    this.frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
    this.scaleLayout.requestLayout();
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            if (Form.this.frameLayout != null && Form.this.frameLayout.getWidth() != 0 && Form.this.frameLayout.getHeight() != 0) {
              if (Form.sCompatibilityMode) {
                Form.this.Sizing("Fixed");
              } else {
                Form.this.Sizing("Responsive");
              } 
              Form.this.ReplayFormOrientation();
              Form.this.frameWithTitle.requestLayout();
              return;
            } 
            Form.this.androidUIHandler.post(this);
          }
        });
  }
  
  private void setBackground(View paramView) {
    ColorDrawable colorDrawable;
    int i = -1;
    Drawable drawable = this.backgroundDrawable;
    if (this.backgroundImagePath != "" && drawable != null) {
      drawable = this.backgroundDrawable.getConstantState().newDrawable();
      if (this.backgroundColor != 0)
        i = this.backgroundColor; 
      drawable.setColorFilter(i, PorterDuff.Mode.DST_OVER);
    } else {
      if (this.backgroundColor != 0)
        i = this.backgroundColor; 
      colorDrawable = new ColorDrawable(i);
    } 
    ViewUtil.setBackgroundImage(paramView, (Drawable)colorDrawable);
    paramView.invalidate();
  }
  
  private void showAboutApplicationNotification() {
    Notifier.oneButtonAlert(this, (this.aboutScreen + "<p><small><em>Invented with MIT App Inventor<br>appinventor.mit.edu</em></small></p>" + this.yandexTranslateTagline).replaceAll("\\n", "<br>"), "About this app", "Got it");
  }
  
  private void showExitApplicationNotification() {
    Runnable runnable1 = new Runnable() {
        public void run() {
          Form.this.closeApplicationFromMenu();
        }
      };
    Runnable runnable2 = new Runnable() {
        public void run() {}
      };
    Notifier.twoButtonDialog(this, "Stop this application and exit? You'll need to relaunch the application to use it again.", "Stop application?", "Stop and exit", "Don't stop", false, runnable1, runnable2, runnable2);
  }
  
  public static void switchForm(String paramString) {
    if (activeForm != null) {
      activeForm.startNewForm(paramString, (Object)null);
      return;
    } 
    throw new IllegalStateException("activeForm is null");
  }
  
  public static void switchFormWithStartValue(String paramString, Object paramObject) {
    Log.i("Form", "Open another screen with start value:" + paramString);
    if (activeForm != null) {
      activeForm.startNewForm(paramString, paramObject);
      return;
    } 
    throw new IllegalStateException("activeForm is null");
  }
  
  public void $add(AndroidViewComponent paramAndroidViewComponent) {
    this.viewLayout.add(paramAndroidViewComponent);
  }
  
  public Activity $context() {
    return this;
  }
  
  protected void $define() {
    throw new UnsupportedOperationException();
  }
  
  public Form $form() {
    return this;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Information about the screen.  It appears when \"About this Application\" is selected from the system menu. Use it to inform people about your app.  In multiple screen apps, each screen has its own AboutScreen info.")
  public String AboutScreen() {
    return this.aboutScreen;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "textArea")
  @SimpleProperty
  public void AboutScreen(String paramString) {
    this.aboutScreen = paramString;
  }
  
  @SimpleProperty
  public int AccentColor() {
    return this.accentColor;
  }
  
  @DesignerProperty(defaultValue = "&HFFFF4081", editorType = "color")
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is the accent color used for highlights and other user interface accents.", userVisible = false)
  public void AccentColor(int paramInt) {
    this.accentColor = paramInt;
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(userVisible = false)
  public void ActionBar(boolean paramBoolean) {
    if (SdkLevel.getLevel() >= 11 && this.actionBarEnabled != paramBoolean) {
      setActionBarEnabled(paramBoolean);
      if (paramBoolean) {
        hideTitleBar();
        this.actionBarEnabled = this.themeHelper.setActionBarVisible(this.showTitle);
      } else {
        maybeShowTitleBar();
        this.actionBarEnabled = this.themeHelper.setActionBarVisible(false);
      } 
      this.actionBarEnabled = paramBoolean;
      return;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the screen are aligned  horizontally. The choices are: 1 = left aligned, 2 = horizontally centered,  3 = right aligned.")
  public int AlignHorizontal() {
    return this.horizontalAlignment;
  }
  
  @DesignerProperty(defaultValue = "1", editorType = "horizontal_alignment")
  @SimpleProperty
  public void AlignHorizontal(int paramInt) {
    try {
      this.alignmentSetter.setHorizontalAlignment(paramInt);
      this.horizontalAlignment = paramInt;
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      dispatchErrorOccurredEvent(this, "HorizontalAlignment", 1401, new Object[] { Integer.valueOf(paramInt) });
      return;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how the contents of the arrangement are aligned vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom. Vertical alignment has no effect if the screen is scrollable.")
  public int AlignVertical() {
    return this.verticalAlignment;
  }
  
  @DesignerProperty(defaultValue = "1", editorType = "vertical_alignment")
  @SimpleProperty
  public void AlignVertical(int paramInt) {
    try {
      this.alignmentSetter.setVerticalAlignment(paramInt);
      this.verticalAlignment = paramInt;
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      dispatchErrorOccurredEvent(this, "VerticalAlignment", 1402, new Object[] { Integer.valueOf(paramInt) });
      return;
    } 
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty(description = "This is the display name of the installed application in the phone.If the AppName is blank, it will be set to the name of the project when the project is built.", userVisible = false)
  public void AppName(String paramString) {}
  
  @SimpleFunction(description = "Ask the user to grant access to a dangerous permission.")
  public void AskForPermission(String paramString) {
    String str = paramString;
    if (!paramString.contains("."))
      str = "android.permission." + paramString; 
    askPermission(str, new PermissionResultHandler() {
          public void HandlePermissionResponse(String param1String, boolean param1Boolean) {
            if (param1Boolean) {
              Form.this.PermissionGranted(param1String);
              return;
            } 
            Form.this.PermissionDenied(Form.this, "RequestPermission", param1String);
          }
        });
  }
  
  @SimpleEvent(description = "Device back button pressed.")
  public boolean BackPressed() {
    return EventDispatcher.dispatchEvent(this, "BackPressed", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public int BackgroundColor() {
    return this.backgroundColor;
  }
  
  @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
  @SimpleProperty
  public void BackgroundColor(int paramInt) {
    if (paramInt == 0) {
      this.usesDefaultBackground = true;
    } else {
      this.usesDefaultBackground = false;
      this.backgroundColor = paramInt;
    } 
    setBackground((View)this.frameLayout);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The screen background image.")
  public String BackgroundImage() {
    return this.backgroundImagePath;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "asset")
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The screen background image.")
  public void BackgroundImage(String paramString) {
    String str = paramString;
    if (paramString == null)
      str = ""; 
    this.backgroundImagePath = str;
    try {
      this.backgroundDrawable = (Drawable)MediaUtil.getBitmapDrawable(this, this.backgroundImagePath);
    } catch (IOException iOException) {
      Log.e("Form", "Unable to load " + this.backgroundImagePath);
      this.backgroundDrawable = null;
    } 
    setBackground((View)this.frameLayout);
  }
  
  @DesignerProperty(defaultValue = "", editorType = "subset_json")
  @SimpleProperty(description = "A JSON string representing the subset for the screen. Authors of template apps can use this to control what components, designer properties, and blocks are available in the project.", userVisible = false)
  public void BlocksToolkit(String paramString) {}
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The animation for closing current screen and returning  to the previous screen. Valid options are default, fade, zoom, slidehorizontal, slidevertical, and none")
  public String CloseScreenAnimation() {
    return this.closeAnimType;
  }
  
  @DesignerProperty(defaultValue = "default", editorType = "screen_animation")
  @SimpleProperty
  public void CloseScreenAnimation(String paramString) {
    if (paramString != "default" && paramString != "fade" && paramString != "zoom" && paramString != "slidehorizontal" && paramString != "slidevertical" && paramString != "none") {
      dispatchErrorOccurredEvent(this, "Screen", 905, new Object[] { paramString });
      return;
    } 
    this.closeAnimType = paramString;
  }
  
  @SimpleEvent(description = "Event raised when an error occurs. Only some errors will raise this condition.  For those errors, the system will show a notification by default.  You can use this event handler to prescribe an error behavior different than the default.")
  public void ErrorOccurred(Component paramComponent, String paramString1, int paramInt, String paramString2) {
    String str = paramComponent.getClass().getName();
    str = str.substring(str.lastIndexOf(".") + 1);
    Log.e("Form", "Form " + this.formName + " ErrorOccurred, errorNumber = " + paramInt + ", componentType = " + str + ", functionName = " + paramString1 + ", messages = " + paramString2);
    if (!EventDispatcher.dispatchEvent(this, "ErrorOccurred", new Object[] { paramComponent, paramString1, Integer.valueOf(paramInt), paramString2 }) && this.screenInitialized)
      (new Notifier(this)).ShowAlert("Error " + paramInt + ": " + paramString2); 
  }
  
  public void ErrorOccurredDialog(Component paramComponent, String paramString1, int paramInt, String paramString2, String paramString3, String paramString4) {
    String str = paramComponent.getClass().getName();
    str = str.substring(str.lastIndexOf(".") + 1);
    Log.e("Form", "Form " + this.formName + " ErrorOccurred, errorNumber = " + paramInt + ", componentType = " + str + ", functionName = " + paramString1 + ", messages = " + paramString2);
    if (!EventDispatcher.dispatchEvent(this, "ErrorOccurred", new Object[] { paramComponent, paramString1, Integer.valueOf(paramInt), paramString2 }) && this.screenInitialized)
      (new Notifier(this)).ShowMessageDialog("Error " + paramInt + ": " + paramString2, paramString3, paramString4); 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Screen height (y-size).")
  public int Height() {
    Log.d("Form", "Form.Height = " + this.formHeight);
    return this.formHeight;
  }
  
  @SimpleFunction(description = "Hide the onscreen soft keyboard.")
  public void HideKeyboard() {
    FrameLayout frameLayout;
    View view2 = getCurrentFocus();
    View view1 = view2;
    if (view2 == null)
      frameLayout = this.frameLayout; 
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(frameLayout.getWindowToken(), 0);
  }
  
  @DesignerProperty(defaultValue = "", editorType = "asset")
  @SimpleProperty(userVisible = false)
  public void Icon(String paramString) {}
  
  @SimpleEvent(description = "The Initialize event is run when the Screen starts and is only run once per screen.")
  public void Initialize() {
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            if (Form.this.frameLayout != null && Form.this.frameLayout.getWidth() != 0 && Form.this.frameLayout.getHeight() != 0) {
              EventDispatcher.dispatchEvent(Form.this, "Initialize", new Object[0]);
              if (Form.sCompatibilityMode) {
                Form.this.Sizing("Fixed");
              } else {
                Form.this.Sizing("Responsive");
              } 
              Form.access$502(Form.this, true);
              Iterator<OnInitializeListener> iterator = Form.this.onInitializeListeners.iterator();
              while (iterator.hasNext())
                ((OnInitializeListener)iterator.next()).onInitialize(); 
              if (Form.activeForm instanceof ReplForm)
                ((ReplForm)Form.activeForm).HandleReturnValues(); 
              return;
            } 
            Form.this.androidUIHandler.post(this);
          }
        });
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The animation for switching to another screen. Valid options are default, fade, zoom, slidehorizontal, slidevertical, and none")
  public String OpenScreenAnimation() {
    return this.openAnimType;
  }
  
  @DesignerProperty(defaultValue = "default", editorType = "screen_animation")
  @SimpleProperty
  public void OpenScreenAnimation(String paramString) {
    if (paramString != "default" && paramString != "fade" && paramString != "zoom" && paramString != "slidehorizontal" && paramString != "slidevertical" && paramString != "none") {
      dispatchErrorOccurredEvent(this, "Screen", 905, new Object[] { paramString });
      return;
    } 
    this.openAnimType = paramString;
  }
  
  @SimpleEvent(description = "Event raised when another screen has closed and control has returned to this screen.")
  public void OtherScreenClosed(String paramString, Object paramObject) {
    Log.i("Form", "Form " + this.formName + " OtherScreenClosed, otherScreenName = " + paramString + ", result = " + paramObject.toString());
    EventDispatcher.dispatchEvent(this, "OtherScreenClosed", new Object[] { paramString, paramObject });
  }
  
  @SimpleEvent
  public void PermissionDenied(Component paramComponent, String paramString1, String paramString2) {
    String str = paramString2;
    if (paramString2.startsWith("android.permission."))
      str = paramString2.replace("android.permission.", ""); 
    if (!EventDispatcher.dispatchEvent(this, "PermissionDenied", new Object[] { paramComponent, paramString1, str }))
      dispatchErrorOccurredEvent(paramComponent, paramString1, 908, new Object[] { str }); 
  }
  
  @SimpleEvent(description = "Event to handle when the app user has granted a needed permission. This event is only run when permission is granted in response to the AskForPermission method.")
  public void PermissionGranted(String paramString) {
    String str = paramString;
    if (paramString.startsWith("android.permission."))
      str = paramString.replace("android.permission.", ""); 
    EventDispatcher.dispatchEvent(this, "PermissionGranted", new Object[] { str });
  }
  
  @SimpleProperty(description = "The platform the app is running on, for example \"Android\" or \"iOS\".")
  public String Platform() {
    return "Android";
  }
  
  @SimpleProperty(description = "The dotted version number of the platform, such as 4.2.2 or 10.0. This is platform specific and there is no guarantee that it has a particular format.")
  public String PlatformVersion() {
    return Build.VERSION.RELEASE;
  }
  
  @SimpleProperty
  public int PrimaryColor() {
    return this.primaryColor;
  }
  
  @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is the primary color used for Material UI elements, such as the ActionBar.", userVisible = false)
  public void PrimaryColor(int paramInt) {
    setPrimaryColor(paramInt);
  }
  
  @SimpleProperty
  public int PrimaryColorDark() {
    return this.primaryColorDark;
  }
  
  @DesignerProperty(defaultValue = "&HFF303F9F", editorType = "color")
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is the primary color used for darker elements in Material UI.", userVisible = false)
  public void PrimaryColorDark(int paramInt) {
    this.primaryColorDark = paramInt;
  }
  
  void ReplayFormOrientation() {
    Log.d("Form", "ReplayFormOrientation()");
    LinkedHashMap linkedHashMap = (LinkedHashMap)this.dimChanges.clone();
    this.dimChanges.clear();
    for (PercentStorageRecord percentStorageRecord : linkedHashMap.values()) {
      if (percentStorageRecord.dim == PercentStorageRecord.Dim.HEIGHT) {
        percentStorageRecord.component.Height(percentStorageRecord.length);
        continue;
      } 
      percentStorageRecord.component.Width(percentStorageRecord.length);
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The requested screen orientation, specified as a text value.  Commonly used values are landscape, portrait, sensor, user and unspecified.  See the Android developer documentation for ActivityInfo.Screen_Orientation for the complete list of possible settings.")
  public String ScreenOrientation() {
    switch (getRequestedOrientation()) {
      default:
        return "unspecified";
      case 3:
        return "behind";
      case 0:
        return "landscape";
      case 5:
        return "nosensor";
      case 1:
        return "portrait";
      case 4:
        return "sensor";
      case -1:
        return "unspecified";
      case 2:
        return "user";
      case 10:
        return "fullSensor";
      case 8:
        return "reverseLandscape";
      case 9:
        return "reversePortrait";
      case 6:
        return "sensorLandscape";
      case 7:
        break;
    } 
    return "sensorPortrait";
  }
  
  @DesignerProperty(defaultValue = "unspecified", editorType = "screen_orientation")
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  @SuppressLint({"SourceLockedOrientationActivity"})
  public void ScreenOrientation(String paramString) {
    if (paramString.equalsIgnoreCase("behind")) {
      setRequestedOrientation(3);
      return;
    } 
    if (paramString.equalsIgnoreCase("landscape")) {
      setRequestedOrientation(0);
      return;
    } 
    if (paramString.equalsIgnoreCase("nosensor")) {
      setRequestedOrientation(5);
      return;
    } 
    if (paramString.equalsIgnoreCase("portrait")) {
      setRequestedOrientation(1);
      return;
    } 
    if (paramString.equalsIgnoreCase("sensor")) {
      setRequestedOrientation(4);
      return;
    } 
    if (paramString.equalsIgnoreCase("unspecified")) {
      setRequestedOrientation(-1);
      return;
    } 
    if (paramString.equalsIgnoreCase("user")) {
      setRequestedOrientation(2);
      return;
    } 
    if (SdkLevel.getLevel() >= 9) {
      if (paramString.equalsIgnoreCase("fullSensor")) {
        setRequestedOrientation(10);
        return;
      } 
      if (paramString.equalsIgnoreCase("reverseLandscape")) {
        setRequestedOrientation(8);
        return;
      } 
      if (paramString.equalsIgnoreCase("reversePortrait")) {
        setRequestedOrientation(9);
        return;
      } 
      if (paramString.equalsIgnoreCase("sensorLandscape")) {
        setRequestedOrientation(6);
        return;
      } 
      if (paramString.equalsIgnoreCase("sensorPortrait")) {
        setRequestedOrientation(7);
        return;
      } 
      dispatchErrorOccurredEvent(this, "ScreenOrientation", 901, new Object[] { paramString });
      return;
    } 
    dispatchErrorOccurredEvent(this, "ScreenOrientation", 901, new Object[] { paramString });
  }
  
  @SimpleEvent(description = "Screen orientation changed")
  public void ScreenOrientationChanged() {
    Iterator<OnOrientationChangeListener> iterator = this.onOrientationChangeListeners.iterator();
    while (iterator.hasNext())
      ((OnOrientationChangeListener)iterator.next()).onOrientationChange(); 
    EventDispatcher.dispatchEvent(this, "ScreenOrientationChanged", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void Scrollable(boolean paramBoolean) {
    if (this.scrollable == paramBoolean && this.frameLayout != null)
      return; 
    this.scrollable = paramBoolean;
    recomputeLayout();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "When checked, there will be a vertical scrollbar on the screen, and the height of the application can exceed the physical height of the device. When unchecked, the application height is constrained to the height of the device.")
  public boolean Scrollable() {
    return this.scrollable;
  }
  
  @DesignerProperty(alwaysSend = true, defaultValue = "True", editorType = "boolean")
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If false, lists will be converted to strings using Lisp notation, i.e., as symbols separated by spaces, e.g., (a 1 b2 (c d). If true, lists will appear as in Json or Python, e.g.  [\"a\", 1, \"b\", 2, [\"c\", \"d\"]].  This property appears only in Screen 1, and the value for Screen 1 determines the behavior for all screens. The property defaults to \"true\" meaning that the App Inventor programmer must explicitly set it to \"false\" if Lisp syntax is desired. In older versions of App Inventor, this setting defaulted to false. Older projects should not have been affected by this default settings update.", userVisible = false)
  public void ShowListsAsJson(boolean paramBoolean) {
    showListsAsJson = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
  public boolean ShowListsAsJson() {
    return showListsAsJson;
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void ShowStatusBar(boolean paramBoolean) {
    if (paramBoolean != this.showStatusBar) {
      if (paramBoolean) {
        getWindow().addFlags(2048);
        getWindow().clearFlags(1024);
      } else {
        getWindow().addFlags(1024);
        getWindow().clearFlags(2048);
      } 
      this.showStatusBar = paramBoolean;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The status bar is the topmost bar on the screen. This property reports whether the status bar is visible.")
  public boolean ShowStatusBar() {
    return this.showStatusBar;
  }
  
  @DesignerProperty(alwaysSend = true, defaultValue = "Responsive", editorType = "sizing")
  @SimpleProperty(description = "If set to fixed,  screen layouts will be created for a single fixed-size screen and autoscaled. If set to responsive, screen layouts will use the actual resolution of the device.  See the documentation on responsive design in App Inventor for more information. This property appears on Screen1 only and controls the sizing for all screens in the app.", userVisible = false)
  public void Sizing(String paramString) {
    float f;
    Log.d("Form", "Sizing(" + paramString + ")");
    this.formWidth = (int)((getResources().getDisplayMetrics()).widthPixels / this.deviceDensity);
    this.formHeight = (int)((getResources().getDisplayMetrics()).heightPixels / this.deviceDensity);
    if (paramString.equals("Fixed")) {
      sCompatibilityMode = true;
      this.formWidth = (int)(this.formWidth / this.compatScalingFactor);
      this.formHeight = (int)(this.formHeight / this.compatScalingFactor);
    } else {
      sCompatibilityMode = false;
    } 
    ScaledFrameLayout scaledFrameLayout = this.scaleLayout;
    if (sCompatibilityMode) {
      f = this.compatScalingFactor;
    } else {
      f = 1.0F;
    } 
    scaledFrameLayout.setScale(f);
    if (this.frameLayout != null)
      this.frameLayout.invalidate(); 
    Log.d("Form", "formWidth = " + this.formWidth + " formHeight = " + this.formHeight);
  }
  
  @DesignerProperty(defaultValue = "Classic", editorType = "theme")
  @SimpleProperty(description = "Sets the theme used by the application.", userVisible = false)
  public void Theme(String paramString) {
    if (SdkLevel.getLevel() < 11) {
      this.backgroundColor = -1;
      setBackground((View)this.frameLayout);
      return;
    } 
    if (this.usesDefaultBackground) {
      if (paramString.equalsIgnoreCase("AppTheme") && !isClassicMode()) {
        this.backgroundColor = -16777216;
      } else {
        this.backgroundColor = -1;
      } 
      setBackground((View)this.frameLayout);
    } 
    this.usesDarkTheme = false;
    if (paramString.equals("Classic")) {
      setAppInventorTheme(AppInventorCompatActivity.Theme.CLASSIC);
      return;
    } 
    if (paramString.equals("AppTheme.Light.DarkActionBar")) {
      setAppInventorTheme(AppInventorCompatActivity.Theme.DEVICE_DEFAULT);
      return;
    } 
    if (paramString.equals("AppTheme.Light")) {
      setAppInventorTheme(AppInventorCompatActivity.Theme.BLACK_TITLE_TEXT);
      return;
    } 
    if (paramString.equals("AppTheme")) {
      this.usesDarkTheme = true;
      setAppInventorTheme(AppInventorCompatActivity.Theme.DARK);
      return;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The caption for the form, which apears in the title bar")
  public String Title() {
    return getTitle().toString();
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void Title(String paramString) {
    this.title = paramString;
    if (this.titleBar != null)
      this.titleBar.setText(paramString); 
    setTitle(paramString);
    updateTitle();
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void TitleVisible(boolean paramBoolean) {
    if (paramBoolean != this.showTitle) {
      this.showTitle = paramBoolean;
      if (this.actionBarEnabled) {
        this.actionBarEnabled = this.themeHelper.setActionBarVisible(paramBoolean);
        return;
      } 
    } else {
      return;
    } 
    maybeShowTitleBar();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The title bar is the top gray bar on the screen. This property reports whether the title bar is visible.")
  public boolean TitleVisible() {
    return this.showTitle;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty(description = "A URL to use to populate the Tutorial Sidebar while editing a project. Used as a teaching aid.", userVisible = false)
  public void TutorialURL(String paramString) {}
  
  @DesignerProperty(defaultValue = "1", editorType = "non_negative_integer")
  @SimpleProperty(description = "An integer value which must be incremented each time a new Android Application Package File (APK) is created for the Google Play Store.", userVisible = false)
  public void VersionCode(int paramInt) {}
  
  @DesignerProperty(defaultValue = "1.0", editorType = "string")
  @SimpleProperty(description = "A string which can be changed to allow Google Play Store users to distinguish between different versions of the App.", userVisible = false)
  public void VersionName(String paramString) {}
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Screen width (x-size).")
  public int Width() {
    Log.d("Form", "Form.Width = " + this.formWidth);
    return this.formWidth;
  }
  
  public void addAboutInfoToMenu(Menu paramMenu) {
    paramMenu.add(0, 0, 2, "About this application").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
          public boolean onMenuItemClick(MenuItem param1MenuItem) {
            Form.this.showAboutApplicationNotification();
            return true;
          }
        }).setIcon(17301651);
  }
  
  public void addExitButtonToMenu(Menu paramMenu) {
    paramMenu.add(0, 0, 1, "Stop this application").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
          public boolean onMenuItemClick(MenuItem param1MenuItem) {
            Form.this.showExitApplicationNotification();
            return true;
          }
        }).setIcon(17301594);
  }
  
  public void askPermission(final BulkPermissionRequest request) {
    final List permissionsToAsk = request.getPermissions();
    Iterator<String> iterator = list.iterator();
    while (iterator.hasNext()) {
      if (!isDeniedPermission(iterator.next()))
        iterator.remove(); 
    } 
    if (list.size() == 0) {
      request.onGranted();
      return;
    } 
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            final Iterator<String> it = permissionsToAsk.iterator();
            PermissionResultHandler permissionResultHandler = new PermissionResultHandler() {
                final List<String> deniedPermissions = new ArrayList<String>();
                
                public void HandlePermissionResponse(String param2String, boolean param2Boolean) {
                  if (!param2Boolean)
                    this.deniedPermissions.add(param2String); 
                  if (it.hasNext()) {
                    Form.this.askPermission(it.next(), this);
                    return;
                  } 
                  if (this.deniedPermissions.size() == 0) {
                    request.onGranted();
                    return;
                  } 
                  request.onDenied(this.deniedPermissions.<String>toArray(new String[0]));
                }
              };
            Form.this.askPermission(iterator.next(), permissionResultHandler);
          }
        });
  }
  
  public void askPermission(final String permission, final PermissionResultHandler responseRequestor) {
    if (!isDeniedPermission(permission)) {
      responseRequestor.HandlePermissionResponse(permission, true);
      return;
    } 
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            int i = Form.this.permissionRandom.nextInt(100000);
            Log.d("Form", "askPermission: permission = " + permission + " requestCode = " + i);
            Form.this.permissionHandlers.put(Integer.valueOf(i), responseRequestor);
            ActivityCompat.requestPermissions(form, new String[] { this.val$permission }, i);
          }
        });
  }
  
  public void assertPermission(String paramString) {
    if (isDeniedPermission(paramString))
      throw new PermissionException(paramString); 
  }
  
  public void callInitialize(Object paramObject) throws Throwable {
    try {
      Method method = paramObject.getClass().getMethod("Initialize", (Class[])null);
      try {
        Log.i("Form", "calling Initialize method for Object " + paramObject.toString());
        method.invoke(paramObject, (Object[])null);
        return;
      } catch (InvocationTargetException invocationTargetException) {
        Log.i("Form", "invoke exception: " + invocationTargetException.getMessage());
        throw invocationTargetException.getTargetException();
      } 
    } catch (SecurityException securityException) {
      Log.i("Form", "Security exception " + securityException.getMessage());
      return;
    } catch (NoSuchMethodException noSuchMethodException) {
      return;
    } 
  }
  
  public boolean canDispatchEvent(Component paramComponent, String paramString) {
    boolean bool;
    if (this.screenInitialized || (paramComponent == this && paramString.equals("Initialize"))) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool)
      activeForm = this; 
    return bool;
  }
  
  public void clear() {
    Log.d("Form", "Form " + this.formName + " clear called");
    this.viewLayout.getLayoutManager().removeAllViews();
    if (this.frameLayout != null) {
      this.frameLayout.removeAllViews();
      this.frameLayout = null;
    } 
    defaultPropertyValues();
    this.onStopListeners.clear();
    this.onNewIntentListeners.clear();
    this.onResumeListeners.clear();
    this.onOrientationChangeListeners.clear();
    this.onPauseListeners.clear();
    this.onDestroyListeners.clear();
    this.onInitializeListeners.clear();
    this.onCreateOptionsMenuListeners.clear();
    this.onOptionsItemSelectedListeners.clear();
    this.screenInitialized = false;
    Iterator<OnClearListener> iterator = this.onClearListeners.iterator();
    while (iterator.hasNext())
      ((OnClearListener)iterator.next()).onClear(); 
    this.onClearListeners.clear();
    System.err.println("Form.clear() About to do moby GC!");
    System.gc();
    this.dimChanges.clear();
  }
  
  protected void closeApplicationFromBlocks() {
    closeApplication();
  }
  
  protected void closeForm(Intent paramIntent) {
    if (paramIntent != null)
      setResult(-1, paramIntent); 
    finish();
    AnimationUtil.ApplyCloseScreenAnimation(this, this.closeAnimType);
  }
  
  public float compatScalingFactor() {
    return this.compatScalingFactor;
  }
  
  public void deleteComponent(Object paramObject) {
    if (paramObject instanceof OnStopListener)
      this.onStopListeners.remove(paramObject); 
    if (paramObject instanceof OnNewIntentListener)
      this.onNewIntentListeners.remove(paramObject); 
    if (paramObject instanceof OnResumeListener)
      this.onResumeListeners.remove(paramObject); 
    if (paramObject instanceof OnOrientationChangeListener)
      this.onOrientationChangeListeners.remove(paramObject); 
    if (paramObject instanceof OnPauseListener)
      this.onPauseListeners.remove(paramObject); 
    if (paramObject instanceof OnDestroyListener)
      this.onDestroyListeners.remove(paramObject); 
    if (paramObject instanceof OnInitializeListener)
      this.onInitializeListeners.remove(paramObject); 
    if (paramObject instanceof OnCreateOptionsMenuListener)
      this.onCreateOptionsMenuListeners.remove(paramObject); 
    if (paramObject instanceof OnOptionsItemSelectedListener)
      this.onOptionsItemSelectedListeners.remove(paramObject); 
    if (paramObject instanceof Deleteable)
      ((Deleteable)paramObject).onDelete(); 
  }
  
  public float deviceDensity() {
    return this.deviceDensity;
  }
  
  public void dispatchErrorOccurredEvent(final Component component, final String functionName, final int errorNumber, Object... messageArgs) {
    runOnUiThread(new Runnable() {
          public void run() {
            String str = ErrorMessages.formatMessage(errorNumber, messageArgs);
            Form.this.ErrorOccurred(component, functionName, errorNumber, str);
          }
        });
  }
  
  public void dispatchErrorOccurredEventDialog(final Component component, final String functionName, final int errorNumber, Object... messageArgs) {
    runOnUiThread(new Runnable() {
          public void run() {
            String str = ErrorMessages.formatMessage(errorNumber, messageArgs);
            Form.this.ErrorOccurredDialog(component, functionName, errorNumber, str, "Error in " + functionName, "Dismiss");
          }
        });
  }
  
  public boolean dispatchEvent(Component paramComponent, String paramString1, String paramString2, Object[] paramArrayOfObject) {
    throw new UnsupportedOperationException();
  }
  
  public void dispatchGenericEvent(Component paramComponent, String paramString, boolean paramBoolean, Object[] paramArrayOfObject) {
    throw new UnsupportedOperationException();
  }
  
  public void dispatchPermissionDeniedEvent(Component paramComponent, String paramString, PermissionException paramPermissionException) {
    paramPermissionException.printStackTrace();
    dispatchPermissionDeniedEvent(paramComponent, paramString, paramPermissionException.getPermissionNeeded());
  }
  
  public void dispatchPermissionDeniedEvent(final Component component, final String functionName, final String permissionName) {
    runOnUiThread(new Runnable() {
          public void run() {
            Form.this.PermissionDenied(component, functionName, permissionName);
          }
        });
  }
  
  public boolean doesAppDeclarePermission(String paramString) {
    return this.permissions.contains(paramString);
  }
  
  public void dontGrabTouchEventsForComponent() {
    this.frameLayout.requestDisallowInterceptTouchEvent(true);
  }
  
  public Bundle fullScreenVideoAction(int paramInt, VideoPlayer paramVideoPlayer, Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield fullScreenVideoUtil : Lcom/google/appinventor/components/runtime/util/FullScreenVideoUtil;
    //   6: iload_1
    //   7: aload_2
    //   8: aload_3
    //   9: invokevirtual performAction : (ILcom/google/appinventor/components/runtime/VideoPlayer;Ljava/lang/Object;)Landroid/os/Bundle;
    //   12: astore_2
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_2
    //   16: areturn
    //   17: astore_2
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_2
    //   21: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	17	finally
  }
  
  public String getAssetPath(String paramString) {
    return "file:///android_asset/" + paramString;
  }
  
  public String getAssetPathForExtension(Component paramComponent, String paramString) throws FileNotFoundException {
    String str = paramComponent.getClass().getPackage().getName();
    return "file:///android_asset/" + str + "/" + paramString;
  }
  
  public HandlesEventDispatching getDispatchDelegate() {
    return this;
  }
  
  public String getOpenAnimType() {
    return this.openAnimType;
  }
  
  public boolean isDarkTheme() {
    return this.usesDarkTheme;
  }
  
  public boolean isDeniedPermission(String paramString) {
    return (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission((Context)this, paramString) == -1);
  }
  
  protected void maybeShowTitleBar() {
    if (this.showTitle) {
      super.maybeShowTitleBar();
      return;
    } 
    hideTitleBar();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    Object object;
    int i = 0;
    Log.i("Form", "Form " + this.formName + " got onActivityResult, requestCode = " + paramInt1 + ", resultCode = " + paramInt2);
    if (paramInt1 == 1) {
      String str;
      if (paramIntent != null && paramIntent.hasExtra("APP_INVENTOR_RESULT")) {
        str = paramIntent.getStringExtra("APP_INVENTOR_RESULT");
      } else {
        str = "";
      } 
      object = decodeJSONStringForForm(str, "other screen closed");
      OtherScreenClosed(this.nextFormName, object);
      return;
    } 
    ActivityResultListener activityResultListener = this.activityResultMap.get(Integer.valueOf(paramInt1));
    if (activityResultListener != null)
      activityResultListener.resultReturned(paramInt1, paramInt2, (Intent)object); 
    Set set = this.activityResultMultiMap.get(Integer.valueOf(paramInt1));
    if (set != null) {
      ActivityResultListener[] arrayOfActivityResultListener = (ActivityResultListener[])set.toArray((Object[])new ActivityResultListener[0]);
      int j = arrayOfActivityResultListener.length;
      while (true) {
        if (i < j) {
          arrayOfActivityResultListener[i].resultReturned(paramInt1, paramInt2, (Intent)object);
          i++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public void onBackPressed() {
    if (!BackPressed()) {
      AnimationUtil.ApplyCloseScreenAnimation(this, this.closeAnimType);
      super.onBackPressed();
    } 
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    Log.d("Form", "onConfigurationChanged() called");
    final int newOrientation = paramConfiguration.orientation;
    if (i == 2 || i == 1)
      this.androidUIHandler.post(new Runnable() {
            public void run() {
              boolean bool2 = false;
              boolean bool1 = bool2;
              if (Form.this.frameLayout != null)
                if (newOrientation == 2) {
                  bool1 = bool2;
                  if (Form.this.frameLayout.getWidth() >= Form.this.frameLayout.getHeight())
                    bool1 = true; 
                } else {
                  bool1 = bool2;
                  if (Form.this.frameLayout.getHeight() >= Form.this.frameLayout.getWidth())
                    bool1 = true; 
                }  
              if (bool1) {
                Form.this.recomputeLayout();
                final FrameLayout savedLayout = Form.this.frameLayout;
                Form.this.androidUIHandler.postDelayed(new Runnable() {
                      public void run() {
                        if (savedLayout != null)
                          savedLayout.invalidate(); 
                      }
                    },  100L);
                Form.this.ScreenOrientationChanged();
                return;
              } 
              Form.this.androidUIHandler.post(this);
            }
          }); 
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    String str = getClass().getName();
    this.formName = str.substring(str.lastIndexOf('.') + 1);
    Log.d("Form", "Form " + this.formName + " got onCreate");
    activeForm = this;
    Log.i("Form", "activeForm is now " + activeForm.formName);
    this.deviceDensity = (getResources().getDisplayMetrics()).density;
    Log.d("Form", "deviceDensity = " + this.deviceDensity);
    this.compatScalingFactor = ScreenDensityUtil.computeCompatibleScaling((Context)this);
    Log.i("Form", "compatScalingFactor = " + this.compatScalingFactor);
    this.viewLayout = new LinearLayout((Context)this, 1);
    this.alignmentSetter = new AlignmentUtil(this.viewLayout);
    this.progress = null;
    if (!_initialized && this.formName.equals("Screen1")) {
      Log.d("Form", "MULTI: _initialized = " + _initialized + " formName = " + this.formName);
      _initialized = true;
      if (ReplApplication.installed) {
        Log.d("Form", "MultiDex already installed.");
        onCreateFinish();
        return;
      } 
      this.progress = ProgressDialog.show((Context)this, "Please Wait...", "Installation Finishing");
      this.progress.show();
      (new MultiDexInstaller()).execute((Object[])new Form[] { this });
      return;
    } 
    Log.d("Form", "NO MULTI: _initialized = " + _initialized + " formName = " + this.formName);
    _initialized = true;
    onCreateFinish();
  }
  
  public Dialog onCreateDialog(int paramInt) {
    switch (paramInt) {
      default:
        return super.onCreateDialog(paramInt);
      case 189:
        break;
    } 
    return this.fullScreenVideoUtil.createFullScreenVideoDialog();
  }
  
  void onCreateFinish() {
    boolean bool;
    Log.d("Form", "onCreateFinish called " + System.currentTimeMillis());
    if (this.progress != null)
      this.progress.dismiss(); 
    populatePermissions();
    if (doesAppDeclarePermission("android.permission.WRITE_EXTERNAL_STORAGE") && isRepl() && !AppInventorFeatures.doCompanionSplashScreen()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      askPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public void HandlePermissionResponse(String param1String, boolean param1Boolean) {
              if (param1Boolean) {
                Form.this.onCreateFinish2();
                return;
              } 
              Log.i("Form", "WRITE_EXTERNAL_STORAGE Permission denied by user");
              Form.this.onCreateFinish2();
              Form.this.androidUIHandler.post(new Runnable() {
                    public void run() {
                      Form.this.PermissionDenied(Form.this, "Initialize", "android.permission.WRITE_EXTERNAL_STORAGE");
                    }
                  });
            }
          });
      return;
    } 
    onCreateFinish2();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu) {
    super.onCreateOptionsMenu(paramMenu);
    addExitButtonToMenu(paramMenu);
    addAboutInfoToMenu(paramMenu);
    Iterator<OnCreateOptionsMenuListener> iterator = this.onCreateOptionsMenuListeners.iterator();
    while (iterator.hasNext())
      ((OnCreateOptionsMenuListener)iterator.next()).onCreateOptionsMenu(paramMenu); 
    return true;
  }
  
  protected void onDestroy() {
    Log.i("Form", "Form " + this.formName + " got onDestroy");
    EventDispatcher.removeDispatchDelegate(this);
    Iterator<OnDestroyListener> iterator = this.onDestroyListeners.iterator();
    while (iterator.hasNext())
      ((OnDestroyListener)iterator.next()).onDestroy(); 
    super.onDestroy();
  }
  
  public void onGlobalLayout() {
    int i = this.scaleLayout.getRootView().getHeight();
    float f = (i - this.scaleLayout.getHeight()) / i;
    Log.d("Form", "onGlobalLayout(): diffPercent = " + f);
    if (f < 0.25D) {
      Log.d("Form", "keyboard hidden!");
      if (this.keyboardShown) {
        this.keyboardShown = false;
        if (sCompatibilityMode) {
          this.scaleLayout.setScale(this.compatScalingFactor);
          this.scaleLayout.invalidate();
        } 
      } 
      return;
    } 
    Log.d("Form", "keyboard shown!");
    this.keyboardShown = true;
    if (this.scaleLayout != null) {
      this.scaleLayout.setScale(1.0F);
      this.scaleLayout.invalidate();
      return;
    } 
  }
  
  protected void onNewIntent(Intent paramIntent) {
    super.onNewIntent(paramIntent);
    Log.d("Form", "Form " + this.formName + " got onNewIntent " + paramIntent);
    Iterator<OnNewIntentListener> iterator = this.onNewIntentListeners.iterator();
    while (iterator.hasNext())
      ((OnNewIntentListener)iterator.next()).onNewIntent(paramIntent); 
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    Iterator<OnOptionsItemSelectedListener> iterator = this.onOptionsItemSelectedListeners.iterator();
    while (iterator.hasNext()) {
      if (((OnOptionsItemSelectedListener)iterator.next()).onOptionsItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  protected void onPause() {
    super.onPause();
    Log.i("Form", "Form " + this.formName + " got onPause");
    Iterator<OnPauseListener> iterator = this.onPauseListeners.iterator();
    while (iterator.hasNext())
      ((OnPauseListener)iterator.next()).onPause(); 
  }
  
  public void onPrepareDialog(int paramInt, Dialog paramDialog) {
    switch (paramInt) {
      default:
        super.onPrepareDialog(paramInt, paramDialog);
        return;
      case 189:
        break;
    } 
    this.fullScreenVideoUtil.prepareFullScreenVideoDialog(paramDialog);
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {
    PermissionResultHandler permissionResultHandler = this.permissionHandlers.get(Integer.valueOf(paramInt));
    if (permissionResultHandler == null) {
      Log.e("Form", "Received permission response which we cannot match.");
      return;
    } 
    if (paramArrayOfint.length > 0) {
      if (paramArrayOfint[0] == 0) {
        permissionResultHandler.HandlePermissionResponse(paramArrayOfString[0], true);
      } else {
        permissionResultHandler.HandlePermissionResponse(paramArrayOfString[0], false);
      } 
    } else {
      Log.d("Form", "onRequestPermissionsResult: grantResults.length = " + paramArrayOfint.length + " requestCode = " + paramInt);
    } 
    this.permissionHandlers.remove(Integer.valueOf(paramInt));
  }
  
  protected void onResume() {
    super.onResume();
    Log.i("Form", "Form " + this.formName + " got onResume");
    activeForm = this;
    if (applicationIsBeingClosed) {
      closeApplication();
      return;
    } 
    Iterator<OnResumeListener> iterator = this.onResumeListeners.iterator();
    while (true) {
      if (iterator.hasNext()) {
        ((OnResumeListener)iterator.next()).onResume();
        continue;
      } 
      return;
    } 
  }
  
  protected void onStop() {
    super.onStop();
    Log.i("Form", "Form " + this.formName + " got onStop");
    Iterator<OnStopListener> iterator = this.onStopListeners.iterator();
    while (iterator.hasNext())
      ((OnStopListener)iterator.next()).onStop(); 
  }
  
  public InputStream openAsset(String paramString) throws IOException {
    return openAssetInternal(getAssetPath(paramString));
  }
  
  public InputStream openAssetForExtension(Component paramComponent, String paramString) throws IOException {
    return openAssetInternal(getAssetPathForExtension(paramComponent, paramString));
  }
  
  @VisibleForTesting
  InputStream openAssetInternal(String paramString) throws IOException {
    return paramString.startsWith("file:///android_asset/") ? getAssets().open(paramString.substring("file:///android_asset/".length())) : (paramString.startsWith("file:") ? FileUtil.openFile(this, URI.create(paramString)) : FileUtil.openFile(this, paramString));
  }
  
  public int registerForActivityResult(ActivityResultListener paramActivityResultListener) {
    int i = generateNewRequestCode();
    this.activityResultMap.put(Integer.valueOf(i), paramActivityResultListener);
    return i;
  }
  
  public void registerForActivityResult(ActivityResultListener paramActivityResultListener, int paramInt) {
    Set<ActivityResultListener> set2 = this.activityResultMultiMap.get(Integer.valueOf(paramInt));
    Set<ActivityResultListener> set1 = set2;
    if (set2 == null) {
      set1 = Sets.newHashSet();
      this.activityResultMultiMap.put(Integer.valueOf(paramInt), set1);
    } 
    set1.add(paramActivityResultListener);
  }
  
  public void registerForOnClear(OnClearListener paramOnClearListener) {
    this.onClearListeners.add(paramOnClearListener);
  }
  
  public void registerForOnCreateOptionsMenu(OnCreateOptionsMenuListener paramOnCreateOptionsMenuListener) {
    this.onCreateOptionsMenuListeners.add(paramOnCreateOptionsMenuListener);
  }
  
  public void registerForOnDestroy(OnDestroyListener paramOnDestroyListener) {
    this.onDestroyListeners.add(paramOnDestroyListener);
  }
  
  public void registerForOnInitialize(OnInitializeListener paramOnInitializeListener) {
    this.onInitializeListeners.add(paramOnInitializeListener);
  }
  
  public void registerForOnNewIntent(OnNewIntentListener paramOnNewIntentListener) {
    this.onNewIntentListeners.add(paramOnNewIntentListener);
  }
  
  public void registerForOnOptionsItemSelected(OnOptionsItemSelectedListener paramOnOptionsItemSelectedListener) {
    this.onOptionsItemSelectedListeners.add(paramOnOptionsItemSelectedListener);
  }
  
  public void registerForOnOrientationChange(OnOrientationChangeListener paramOnOrientationChangeListener) {
    this.onOrientationChangeListeners.add(paramOnOrientationChangeListener);
  }
  
  public void registerForOnPause(OnPauseListener paramOnPauseListener) {
    this.onPauseListeners.add(paramOnPauseListener);
  }
  
  public void registerForOnResume(OnResumeListener paramOnResumeListener) {
    this.onResumeListeners.add(paramOnResumeListener);
  }
  
  public void registerForOnStop(OnStopListener paramOnStopListener) {
    this.onStopListeners.add(paramOnStopListener);
  }
  
  public void registerPercentLength(AndroidViewComponent paramAndroidViewComponent, int paramInt, PercentStorageRecord.Dim paramDim) {
    PercentStorageRecord percentStorageRecord = new PercentStorageRecord(paramAndroidViewComponent, paramInt, paramDim);
    Integer integer = generateHashCode(paramAndroidViewComponent, paramDim);
    this.dimChanges.put(integer, percentStorageRecord);
  }
  
  public void runtimeFormErrorOccurredEvent(String paramString1, int paramInt, String paramString2) {
    Log.d("FORM_RUNTIME_ERROR", "functionName is " + paramString1);
    Log.d("FORM_RUNTIME_ERROR", "errorNumber is " + paramInt);
    Log.d("FORM_RUNTIME_ERROR", "message is " + paramString2);
    dispatchErrorOccurredEvent(activeForm, paramString1, paramInt, new Object[] { paramString2 });
  }
  
  public void setChildHeight(final AndroidViewComponent component, final int fHeight) {
    if (Height() == 0)
      this.androidUIHandler.postDelayed(new Runnable() {
            public void run() {
              System.err.println("(Form)Height not stable yet... trying again");
              Form.this.setChildHeight(component, fHeight);
            }
          }100L); 
    int i = fHeight;
    if (fHeight <= -1000)
      i = Height() * -(fHeight + 1000) / 100; 
    component.setLastHeight(i);
    ViewUtil.setChildHeightForVerticalLayout(component.getView(), i);
  }
  
  public void setChildWidth(final AndroidViewComponent component, final int fWidth) {
    int j = Width();
    if (j == 0)
      this.androidUIHandler.postDelayed(new Runnable() {
            public void run() {
              System.err.println("(Form)Width not stable yet... trying again");
              Form.this.setChildWidth(component, fWidth);
            }
          }100L); 
    System.err.println("Form.setChildWidth(): width = " + fWidth + " parent Width = " + j + " child = " + component);
    int i = fWidth;
    if (fWidth <= -1000)
      i = -(fWidth + 1000) * j / 100; 
    component.setLastWidth(i);
    ViewUtil.setChildWidthForVerticalLayout(component.getView(), i);
  }
  
  void setYandexTranslateTagline() {
    this.yandexTranslateTagline = "<p><small>Language translation powered by Yandex.Translate</small></p>";
  }
  
  protected void startNewForm(String paramString, Object paramObject) {
    String str;
    Log.i("Form", "startNewForm:" + paramString);
    Intent intent = new Intent();
    intent.setClassName((Context)this, getPackageName() + "." + paramString);
    if (paramObject == null) {
      str = "open another screen";
    } else {
      str = "open another screen with start value";
    } 
    if (paramObject != null) {
      Log.i("Form", "StartNewForm about to JSON encode:" + paramObject);
      paramObject = jsonEncodeForForm(paramObject, str);
      Log.i("Form", "StartNewForm got JSON encoding:" + paramObject);
    } else {
      paramObject = "";
    } 
    intent.putExtra("APP_INVENTOR_START", (String)paramObject);
    this.nextFormName = paramString;
    Log.i("Form", "about to start new form" + paramString);
    try {
      Log.i("Form", "startNewForm starting activity:" + intent);
      startActivityForResult(intent, 1);
      AnimationUtil.ApplyOpenScreenAnimation(this, this.openAnimType);
      return;
    } catch (ActivityNotFoundException activityNotFoundException) {
      dispatchErrorOccurredEvent(this, str, 902, new Object[] { paramString });
      return;
    } 
  }
  
  protected boolean toastAllowed() {
    long l = System.nanoTime();
    if (l > this.lastToastTime + minimumToastWait) {
      this.lastToastTime = l;
      return true;
    } 
    return false;
  }
  
  public void unregisterForActivityResult(ActivityResultListener paramActivityResultListener) {
    ArrayList arrayList = Lists.newArrayList();
    for (Map.Entry<Integer, ActivityResultListener> entry : this.activityResultMap.entrySet()) {
      if (paramActivityResultListener.equals(entry.getValue()))
        arrayList.add(entry.getKey()); 
    } 
    for (Integer integer : arrayList)
      this.activityResultMap.remove(integer); 
    Iterator<Map.Entry> iterator = this.activityResultMultiMap.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry entry = iterator.next();
      ((Set)entry.getValue()).remove(paramActivityResultListener);
      if (((Set)entry.getValue()).size() == 0)
        iterator.remove(); 
    } 
  }
  
  public void unregisterPercentLength(AndroidViewComponent paramAndroidViewComponent, PercentStorageRecord.Dim paramDim) {
    this.dimChanges.remove(generateHashCode(paramAndroidViewComponent, paramDim));
  }
  
  protected void updateTitle() {
    this.themeHelper.setTitle(this.title);
  }
  
  private static class MultiDexInstaller extends AsyncTask<Form, Void, Boolean> {
    Form ourForm;
    
    private MultiDexInstaller() {}
    
    protected Boolean doInBackground(Form... param1VarArgs) {
      this.ourForm = param1VarArgs[0];
      Log.d("Form", "Doing Full MultiDex Install");
      MultiDex.install((Context)this.ourForm, true);
      return Boolean.valueOf(true);
    }
    
    protected void onPostExecute(Boolean param1Boolean) {
      this.ourForm.onCreateFinish();
    }
  }
  
  public static class PercentStorageRecord {
    AndroidViewComponent component;
    
    Dim dim;
    
    int length;
    
    public PercentStorageRecord(AndroidViewComponent param1AndroidViewComponent, int param1Int, Dim param1Dim) {
      this.component = param1AndroidViewComponent;
      this.length = param1Int;
      this.dim = param1Dim;
    }
    
    public enum Dim {
      HEIGHT, WIDTH;
      
      static {
      
      }
    }
  }
  
  public enum Dim {
    HEIGHT, WIDTH;
    
    static {
    
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Form.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
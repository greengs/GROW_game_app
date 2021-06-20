package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesBroadcastReceivers;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.annotations.androidmanifest.ActionElement;
import com.google.appinventor.components.annotations.androidmanifest.IntentFilterElement;
import com.google.appinventor.components.annotations.androidmanifest.ReceiverElement;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.OAuth2Helper;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

@DesignerComponent(category = ComponentCategory.SOCIAL, description = "<p>A component that will, when the <code>SendMessage</code> method is called, send the text message specified in the <code>Message</code> property to the phone number specified in the <code>PhoneNumber</code> property.</p> <p>If the <code>ReceivingEnabled</code> property is set to 1 messages will <b>not</b> be received. If <code>ReceivingEnabled</code> is set to 2 messages will be received only when the application is running. Finally if <code>ReceivingEnabled</code> is set to 3, messages will be received when the application is running <b>and</b> when the application is not running they will be queued and a notification displayed to the user.</p> <p>When a message arrives, the <code>MessageReceived</code> event is raised and provides the sending number and message.</p> <p> An app that includes this component will receive messages even when it is in the background (i.e. when it's not visible on the screen) and, moreso, even if the app is not running, so long as it's installed on the phone. If the phone receives a text message when the app is not in the foreground, the phone will show a notification in the notification bar.  Selecting the notification will bring up the app.  As an app developer, you'll probably want to give your users the ability to control ReceivingEnabled so that they can make the phone ignore text messages.</p> <p>If the GoogleVoiceEnabled property is true, messages can be sent over Wifi using Google Voice. This option requires that the user have a Google Voice account and that the mobile Voice app is installed on the phone. The Google Voice option works only on phones that support Android 2.0 (Eclair) or higher.</p> <p>To specify the phone number (e.g., 650-555-1212), set the <code>PhoneNumber</code> property to a Text string with the specified digits (e.g., 6505551212).  Dashes, dots, and parentheses may be included (e.g., (650)-555-1212) but will be ignored; spaces may not be included.</p> <p>Another way for an app to specify a phone number would be to include a <code>PhoneNumberPicker</code> component, which lets the users select a phone numbers from the ones stored in the the phone's contacts.</p>", iconName = "images/texting.png", nonVisible = true, version = 4)
@SimpleObject
@UsesLibraries(libraries = "google-api-client-beta.jar,google-api-client-android2-beta.jar,google-http-client-beta.jar,google-http-client-android2-beta.jar,google-http-client-android3-beta.jar,google-oauth-client-beta.jar,guava-14.0.1.jar")
@UsesPermissions(permissionNames = "com.google.android.apps.googlevoice.permission.RECEIVE_SMS, com.google.android.apps.googlevoice.permission.SEND_SMS, android.permission.ACCOUNT_MANAGER, android.permission.MANAGE_ACCOUNTS, android.permission.GET_ACCOUNTS, android.permission.USE_CREDENTIALS")
public class Texting extends AndroidNonvisibleComponent implements Component, OnResumeListener, OnPauseListener, OnInitializeListener, OnStopListener, Deleteable, ActivityResultListener {
  private static final String CACHE_FILE = "textingmsgcache";
  
  public static final String GV_INTENT_FILTER = "com.google.android.apps.googlevoice.SMS_RECEIVED";
  
  public static final String GV_PACKAGE_NAME = "com.google.android.apps.googlevoice";
  
  private static final String GV_SERVICE = "grandcentral";
  
  public static final String GV_SMS_RECEIVED = "com.google.android.apps.googlevoice.SMS_RECEIVED";
  
  public static final String GV_SMS_SEND_URL = "https://www.google.com/voice/b/0/sms/send/";
  
  public static final String GV_URL = "https://www.google.com/voice/b/0/redirection/voice";
  
  private static final String MESSAGE_DELIMITER = "\001";
  
  public static final String MESSAGE_TAG = "com.google.android.apps.googlevoice.TEXT";
  
  public static final String META_DATA_SMS_KEY = "sms_handler_component";
  
  public static final String META_DATA_SMS_VALUE = "Texting";
  
  public static final String PHONE_NUMBER_TAG = "com.google.android.apps.googlevoice.PHONE_NUMBER";
  
  private static final String PREF_FILE = "TextingState";
  
  private static final String PREF_GVENABLED = "gvenabled";
  
  private static final String PREF_RCVENABLED = "receiving2";
  
  private static final String PREF_RCVENABLED_LEGACY = "receiving";
  
  private static final String SENT = "SMS_SENT";
  
  private static final int SERVER_TIMEOUT_MS = 30000;
  
  public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
  
  public static final String TAG = "Texting Component";
  
  public static final String TELEPHONY_INTENT_FILTER = "android.provider.Telephony.SMS_RECEIVED";
  
  public static final int TEXTING_REQUEST_CODE = 1413830740;
  
  private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13";
  
  private static final String UTF8 = "UTF-8";
  
  private static Activity activity;
  
  private static Object cacheLock;
  
  private static Component component;
  
  private static boolean isRunning;
  
  private static int messagesCached;
  
  private static int receivingEnabled = 2;
  
  private String authToken;
  
  private ComponentContainer container;
  
  private boolean googleVoiceEnabled;
  
  private GoogleVoiceUtil gvHelper;
  
  private boolean havePermission = false;
  
  private boolean haveReceivePermission = false;
  
  private boolean isInitialized;
  
  private String message;
  
  private Queue<String> pendingQueue = new ConcurrentLinkedQueue<String>();
  
  private String phoneNumber;
  
  private SmsManager smsManager;
  
  static {
    cacheLock = new Object();
  }
  
  public Texting(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    Log.d("Texting Component", "Texting constructor");
    this.container = paramComponentContainer;
    component = this;
    activity = paramComponentContainer.$context();
    SharedPreferences sharedPreferences = activity.getSharedPreferences("TextingState", 0);
    if (sharedPreferences != null) {
      receivingEnabled = sharedPreferences.getInt("receiving2", -1);
      if (receivingEnabled == -1)
        if (sharedPreferences.getBoolean("receiving", true)) {
          receivingEnabled = 2;
        } else {
          receivingEnabled = 1;
        }  
      this.googleVoiceEnabled = sharedPreferences.getBoolean("gvenabled", false);
      Log.i("Texting Component", "Starting with receiving Enabled=" + receivingEnabled + " GV enabled=" + this.googleVoiceEnabled);
    } else {
      receivingEnabled = 1;
      this.googleVoiceEnabled = false;
    } 
    if (this.googleVoiceEnabled)
      (new AsyncAuthenticate()).execute((Object[])new Void[0]); 
    this.smsManager = SmsManager.getDefault();
    PhoneNumber("");
    this.isInitialized = false;
    isRunning = false;
    paramComponentContainer.$form().registerForOnInitialize(this);
    paramComponentContainer.$form().registerForOnResume(this);
    paramComponentContainer.$form().registerForOnPause(this);
    paramComponentContainer.$form().registerForOnStop(this);
  }
  
  @SimpleEvent
  public static void MessageReceived(String paramString1, String paramString2) {
    if (receivingEnabled > 1) {
      Log.i("Texting Component", "MessageReceived from " + paramString1 + ":" + paramString2);
      if (EventDispatcher.dispatchEvent(component, "MessageReceived", new Object[] { paramString1, paramString2 })) {
        Log.i("Texting Component", "Dispatch successful");
        return;
      } 
    } else {
      return;
    } 
    Log.i("Texting Component", "Dispatch failed, caching");
    synchronized (cacheLock) {
      addMessageToCache((Context)activity, paramString1, paramString2);
      return;
    } 
  }
  
  private static void addMessageToCache(Context paramContext, String paramString1, String paramString2) {
    try {
      paramString1 = paramString1 + ":" + paramString2 + "\001";
      Log.i("Texting Component", "Caching " + paramString1);
      FileOutputStream fileOutputStream = paramContext.openFileOutput("textingmsgcache", 32768);
      fileOutputStream.write(paramString1.getBytes());
      fileOutputStream.close();
      messagesCached++;
      Log.i("Texting Component", "Cached " + paramString1);
      return;
    } catch (FileNotFoundException fileNotFoundException) {
      Log.e("Texting Component", "File not found error writing to cache file");
      fileNotFoundException.printStackTrace();
      return;
    } catch (IOException iOException) {
      Log.e("Texting Component", "I/O Error writing to cache file");
      iOException.printStackTrace();
      return;
    } 
  }
  
  public static int getCachedMsgCount() {
    return messagesCached;
  }
  
  public static SmsMessage[] getMessagesFromIntent(Intent paramIntent) {
    Object[] arrayOfObject = (Object[])paramIntent.getSerializableExtra("pdus");
    byte[][] arrayOfByte1 = new byte[arrayOfObject.length][];
    int i;
    for (i = 0; i < arrayOfObject.length; i++)
      arrayOfByte1[i] = (byte[])arrayOfObject[i]; 
    byte[][] arrayOfByte2 = new byte[arrayOfByte1.length][];
    int j = arrayOfByte2.length;
    SmsMessage[] arrayOfSmsMessage = new SmsMessage[j];
    for (i = 0; i < j; i++) {
      arrayOfByte2[i] = arrayOfByte1[i];
      arrayOfSmsMessage[i] = SmsMessage.createFromPdu(arrayOfByte2[i]);
    } 
    return arrayOfSmsMessage;
  }
  
  private void handleSentMessage(Context paramContext, BroadcastReceiver paramBroadcastReceiver, int paramInt, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_3
    //   3: tableswitch default -> 40, -1 -> 43, 0 -> 40, 1 -> 91, 2 -> 220, 3 -> 177, 4 -> 134
    //   40: aload_0
    //   41: monitorexit
    //   42: return
    //   43: ldc 'Texting Component'
    //   45: new java/lang/StringBuilder
    //   48: dup
    //   49: invokespecial <init> : ()V
    //   52: ldc_w 'Received OK, msg:'
    //   55: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: aload #4
    //   60: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: invokevirtual toString : ()Ljava/lang/String;
    //   66: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   69: pop
    //   70: getstatic com/google/appinventor/components/runtime/Texting.activity : Landroid/app/Activity;
    //   73: ldc_w 'Message sent'
    //   76: iconst_0
    //   77: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   80: invokevirtual show : ()V
    //   83: goto -> 40
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    //   91: ldc 'Texting Component'
    //   93: new java/lang/StringBuilder
    //   96: dup
    //   97: invokespecial <init> : ()V
    //   100: ldc_w 'Received generic failure, msg:'
    //   103: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload #4
    //   108: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: invokevirtual toString : ()Ljava/lang/String;
    //   114: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   117: pop
    //   118: getstatic com/google/appinventor/components/runtime/Texting.activity : Landroid/app/Activity;
    //   121: ldc_w 'Generic failure: message not sent'
    //   124: iconst_0
    //   125: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   128: invokevirtual show : ()V
    //   131: goto -> 40
    //   134: ldc 'Texting Component'
    //   136: new java/lang/StringBuilder
    //   139: dup
    //   140: invokespecial <init> : ()V
    //   143: ldc_w 'Received no service error, msg:'
    //   146: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: aload #4
    //   151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: invokevirtual toString : ()Ljava/lang/String;
    //   157: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   160: pop
    //   161: getstatic com/google/appinventor/components/runtime/Texting.activity : Landroid/app/Activity;
    //   164: ldc_w 'No Sms service available. Message not sent.'
    //   167: iconst_0
    //   168: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   171: invokevirtual show : ()V
    //   174: goto -> 40
    //   177: ldc 'Texting Component'
    //   179: new java/lang/StringBuilder
    //   182: dup
    //   183: invokespecial <init> : ()V
    //   186: ldc_w 'Received null PDU error, msg:'
    //   189: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: aload #4
    //   194: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: invokevirtual toString : ()Ljava/lang/String;
    //   200: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   203: pop
    //   204: getstatic com/google/appinventor/components/runtime/Texting.activity : Landroid/app/Activity;
    //   207: ldc_w 'Received null PDU error. Message not sent.'
    //   210: iconst_0
    //   211: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   214: invokevirtual show : ()V
    //   217: goto -> 40
    //   220: ldc 'Texting Component'
    //   222: new java/lang/StringBuilder
    //   225: dup
    //   226: invokespecial <init> : ()V
    //   229: ldc_w 'Received radio off error, msg:'
    //   232: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: aload #4
    //   237: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   240: invokevirtual toString : ()Ljava/lang/String;
    //   243: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   246: pop
    //   247: getstatic com/google/appinventor/components/runtime/Texting.activity : Landroid/app/Activity;
    //   250: ldc_w 'Could not send SMS message: radio off.'
    //   253: iconst_1
    //   254: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   257: invokevirtual show : ()V
    //   260: goto -> 40
    // Exception table:
    //   from	to	target	type
    //   43	83	86	finally
    //   91	131	86	finally
    //   134	174	86	finally
    //   177	217	86	finally
    //   220	260	86	finally
  }
  
  public static void handledReceivedMessage(Context paramContext, String paramString1, String paramString2) {
    if (isRunning) {
      MessageReceived(paramString1, paramString2);
      return;
    } 
    synchronized (cacheLock) {
      addMessageToCache(paramContext, paramString1, paramString2);
      return;
    } 
  }
  
  public static int isReceivingEnabled(Context paramContext) {
    SharedPreferences sharedPreferences = paramContext.getSharedPreferences("TextingState", 0);
    int j = sharedPreferences.getInt("receiving2", -1);
    int i = j;
    if (j == -1) {
      if (sharedPreferences.getBoolean("receiving", true))
        return 2; 
    } else {
      return i;
    } 
    return 1;
  }
  
  public static boolean isRunning() {
    return isRunning;
  }
  
  private void processCachedMessages() {
    synchronized (cacheLock) {
      String[] arrayOfString = retrieveCachedMessages();
      if (arrayOfString == null)
        return; 
    } 
    Log.i("Texting Component", "processing " + SYNTHETIC_LOCAL_VARIABLE_4.length + " cached messages ");
    for (int i = 0; i < SYNTHETIC_LOCAL_VARIABLE_4.length; i++) {
      Object object = SYNTHETIC_LOCAL_VARIABLE_4[i];
      Log.i("Texting Component", "Message + " + i + " " + object);
      int j = object.indexOf(":");
      if (receivingEnabled > 1 && j != -1)
        MessageReceived(object.substring(0, j), object.substring(j + 1)); 
    } 
  }
  
  private void processPendingQueue() {
    while (this.pendingQueue.size() != 0) {
      String str2 = this.pendingQueue.remove();
      String str1 = str2.substring(0, str2.indexOf(":::"));
      str2 = str2.substring(str2.indexOf(":::") + 3);
      Log.i("Texting Component", "Sending queued message " + str1 + " " + str2);
      (new AsyncSendMessage()).execute((Object[])new String[] { str1, str2 });
    } 
  }
  
  private void requestReceiveSmsPermission(final String caller) {
    this.form.runOnUiThread(new Runnable() {
          public void run() {
            Texting.this.form.askPermission("android.permission.RECEIVE_SMS", new PermissionResultHandler() {
                  public void HandlePermissionResponse(String param2String, boolean param2Boolean) {
                    if (param2Boolean) {
                      Texting.access$502(Texting.this, true);
                      return;
                    } 
                    Texting.this.form.dispatchPermissionDeniedEvent(Texting.this, caller, "android.permission.RECEIVE_SMS");
                  }
                });
          }
        });
  }
  
  private String[] retrieveCachedMessages() {
    Log.i("Texting Component", "Retrieving cached messages");
    try {
      String str = new String(FileUtil.readFile(this.form, "textingmsgcache"));
      try {
        activity.deleteFile("textingmsgcache");
        messagesCached = 0;
        Log.i("Texting Component", "Retrieved cache " + str);
        return str.split("\001");
      } catch (FileNotFoundException null) {
      
      } catch (IOException null) {}
    } catch (FileNotFoundException null) {
      Log.e("Texting Component", "No Cache file found -- this is not (usually) an error");
      return null;
    } catch (IOException iOException) {}
    Log.e("Texting Component", "I/O Error reading from cache file");
    iOException.printStackTrace();
    return null;
  }
  
  private void sendViaSms(final String caller) {
    Log.i("Texting Component", "Sending via built-in Sms");
    if (!this.havePermission) {
      final Form form = this.container.$form();
      form.runOnUiThread(new Runnable() {
            public void run() {
              form.askPermission("android.permission.SEND_SMS", new PermissionResultHandler() {
                    public void HandlePermissionResponse(String param2String, boolean param2Boolean) {
                      if (param2Boolean) {
                        Texting.access$002(me, true);
                        me.sendViaSms(caller);
                        return;
                      } 
                      form.dispatchPermissionDeniedEvent(me, caller, "android.permission.SEND_SMS");
                    }
                  });
            }
          });
      return;
    } 
    ArrayList arrayList = this.smsManager.divideMessage(this.message);
    int j = arrayList.size();
    ArrayList<PendingIntent> arrayList1 = new ArrayList();
    for (int i = 0; i < j; i++)
      arrayList1.add(PendingIntent.getBroadcast((Context)activity, 0, new Intent("SMS_SENT"), 0)); 
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context param1Context, Intent param1Intent) {
          // Byte code:
          //   0: aload_0
          //   1: monitorenter
          //   2: aload_0
          //   3: getfield this$0 : Lcom/google/appinventor/components/runtime/Texting;
          //   6: aload_1
          //   7: aconst_null
          //   8: aload_0
          //   9: invokevirtual getResultCode : ()I
          //   12: aload_0
          //   13: getfield this$0 : Lcom/google/appinventor/components/runtime/Texting;
          //   16: invokestatic access$200 : (Lcom/google/appinventor/components/runtime/Texting;)Ljava/lang/String;
          //   19: invokestatic access$300 : (Lcom/google/appinventor/components/runtime/Texting;Landroid/content/Context;Landroid/content/BroadcastReceiver;ILjava/lang/String;)V
          //   22: invokestatic access$400 : ()Landroid/app/Activity;
          //   25: aload_0
          //   26: invokevirtual unregisterReceiver : (Landroid/content/BroadcastReceiver;)V
          //   29: aload_0
          //   30: monitorexit
          //   31: return
          //   32: astore_1
          //   33: ldc 'BroadcastReceiver'
          //   35: new java/lang/StringBuilder
          //   38: dup
          //   39: invokespecial <init> : ()V
          //   42: ldc 'Error in onReceive for msgId '
          //   44: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   47: aload_2
          //   48: invokevirtual getAction : ()Ljava/lang/String;
          //   51: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   54: invokevirtual toString : ()Ljava/lang/String;
          //   57: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
          //   60: pop
          //   61: ldc 'BroadcastReceiver'
          //   63: aload_1
          //   64: invokevirtual getMessage : ()Ljava/lang/String;
          //   67: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
          //   70: pop
          //   71: aload_1
          //   72: invokevirtual printStackTrace : ()V
          //   75: goto -> 29
          //   78: astore_1
          //   79: aload_0
          //   80: monitorexit
          //   81: aload_1
          //   82: athrow
          // Exception table:
          //   from	to	target	type
          //   2	29	32	java/lang/Exception
          //   2	29	78	finally
          //   33	75	78	finally
        }
      };
    activity.registerReceiver(broadcastReceiver, new IntentFilter("SMS_SENT"));
    this.smsManager.sendMultipartTextMessage(this.phoneNumber, null, arrayList, arrayList1, null);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  @UsesBroadcastReceivers(receivers = {@ReceiverElement(intentFilters = {@IntentFilterElement(actionElements = {@ActionElement(name = "com.google.android.apps.googlevoice.SMS_RECEIVED")})}, name = "com.google.appinventor.components.runtime.util.SmsBroadcastReceiver")})
  public void GoogleVoiceEnabled(boolean paramBoolean) {
    if (SdkLevel.getLevel() >= 5) {
      this.googleVoiceEnabled = paramBoolean;
      SharedPreferences.Editor editor = activity.getSharedPreferences("TextingState", 0).edit();
      editor.putBoolean("gvenabled", paramBoolean);
      editor.commit();
      return;
    } 
    Toast.makeText((Context)activity, "Sorry, your phone's system does not support this option.", 1).show();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, then SendMessage will attempt to send messages over Wifi using Google Voice.  This requires that the Google Voice app must be installed and set up on the phone or tablet, with a Google Voice account.  If GoogleVoiceEnabled is false, the device must have phone and texting service in order to send or receive messages with this component.")
  public boolean GoogleVoiceEnabled() {
    return this.googleVoiceEnabled;
  }
  
  public void Initialize() {
    if (receivingEnabled > 1 && !this.haveReceivePermission)
      requestReceiveSmsPermission("Initialize"); 
  }
  
  @SimpleProperty
  public String Message() {
    return this.message;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The message that will be sent when the SendMessage method is called.")
  public void Message(String paramString) {
    Log.i("Texting Component", "Message set: " + paramString);
    this.message = paramString;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The number that the message will be sent to when the SendMessage method is called. The number is a text string with the specified digits (e.g., 6505551212).  Dashes, dots, and parentheses may be included (e.g., (650)-555-1212) but will be ignored; spaces should not be included.")
  public String PhoneNumber() {
    return this.phoneNumber;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public void PhoneNumber(String paramString) {
    Log.i("Texting Component", "PhoneNumber set: " + paramString);
    this.phoneNumber = paramString;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If set to 1 (OFF) no messages will be received.  If set to 2 (FOREGROUND) or3 (ALWAYS) the component will respond to messages if it is running. If the app is not running then the message will be discarded if set to 2 (FOREGROUND). If set to 3 (ALWAYS) and the app is not running the phone will show a notification.  Selecting the notification will bring up the app and signal the MessageReceived event.  Messages received when the app is dormant will be queued, and so several MessageReceived events might appear when the app awakens.  As an app developer, it would be a good idea to give your users control over this property, so they can make their phones ignore text messages when your app is installed.")
  public int ReceivingEnabled() {
    return receivingEnabled;
  }
  
  @DesignerProperty(alwaysSend = true, defaultValue = "1", editorType = "text_receiving")
  @SimpleProperty
  @UsesBroadcastReceivers(receivers = {@ReceiverElement(intentFilters = {@IntentFilterElement(actionElements = {@ActionElement(name = "android.provider.Telephony.SMS_RECEIVED")})}, name = "com.google.appinventor.components.runtime.util.SmsBroadcastReceiver")})
  @UsesPermissions({"android.permission.RECEIVE_SMS"})
  public void ReceivingEnabled(int paramInt) {
    if (paramInt < 1 || paramInt > 3) {
      this.container.$form().dispatchErrorOccurredEvent(this, "Texting", 1701, new Object[] { Integer.valueOf(paramInt) });
      return;
    } 
    receivingEnabled = paramInt;
    SharedPreferences.Editor editor = activity.getSharedPreferences("TextingState", 0).edit();
    editor.putInt("receiving2", paramInt);
    editor.remove("receiving");
    editor.commit();
    if (paramInt > 1 && !this.haveReceivePermission) {
      requestReceiveSmsPermission("ReceivingEnabled");
      return;
    } 
  }
  
  @SimpleFunction
  public void SendMessage() {
    String str2 = this.phoneNumber;
    String str1 = this.message;
    Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str2));
    intent.putExtra("sms_body", str1);
    if (intent.resolveActivity(this.form.getPackageManager()) != null) {
      this.form.registerForActivityResult(this, 1413830740);
      this.form.startActivityForResult(intent, 1413830740);
    } 
  }
  
  @SimpleFunction
  @UsesPermissions({"android.permission.SEND_SMS", "android.permission.READ_PHONE_STATE"})
  public void SendMessageDirect() {
    Log.i("Texting Component", "Sending message " + this.message + " to " + this.phoneNumber);
    String str1 = this.phoneNumber;
    String str2 = this.message;
    if (this.googleVoiceEnabled) {
      if (this.authToken == null) {
        Log.i("Texting Component", "Need to get an authToken -- enqueing " + str1 + " " + str2);
        if (!this.pendingQueue.offer(str1 + ":::" + str2)) {
          Toast.makeText((Context)activity, "Pending message queue full. Can't send message", 0).show();
          return;
        } 
        if (this.pendingQueue.size() == 1) {
          (new AsyncAuthenticate()).execute((Object[])new Void[0]);
          return;
        } 
        return;
      } 
      Log.i("Texting Component", "Creating AsyncSendMessage");
      (new AsyncSendMessage()).execute((Object[])new String[] { str1, str2 });
      return;
    } 
    Log.i("Texting Component", "Sending via SMS");
    sendViaSms("SendMessage");
  }
  
  public void onDelete() {
    this.form.unregisterForActivityResult(this);
  }
  
  public void onInitialize() {
    Log.i("Texting Component", "onInitialize()");
    this.isInitialized = true;
    isRunning = true;
    processCachedMessages();
    ((NotificationManager)activity.getSystemService("notification")).cancel(8647);
  }
  
  public void onPause() {
    Log.i("Texting Component", "onPause()");
    isRunning = false;
  }
  
  public void onResume() {
    Log.i("Texting Component", "onResume()");
    isRunning = true;
    if (this.isInitialized) {
      processCachedMessages();
      ((NotificationManager)activity.getSystemService("notification")).cancel(8647);
    } 
  }
  
  public void onStop() {
    SharedPreferences.Editor editor = activity.getSharedPreferences("TextingState", 0).edit();
    editor.putInt("receiving2", receivingEnabled);
    editor.putBoolean("gvenabled", this.googleVoiceEnabled);
    editor.commit();
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent) {
    if (paramInt1 == 1413830740) {
      String str;
      Form form = this.form;
      if (paramIntent == null) {
        str = "";
      } else {
        str = str.getStringExtra("sms_body");
      } 
      handleSentMessage((Context)form, null, paramInt2, str);
      this.form.unregisterForActivityResult(this);
    } 
  }
  
  class AsyncAuthenticate extends AsyncTask<Void, Void, String> {
    protected String doInBackground(Void... param1VarArgs) {
      Log.i("Texting Component", "Authenticating");
      return (new OAuth2Helper()).getRefreshedAuthToken(Texting.activity, "grandcentral");
    }
    
    protected void onPostExecute(String param1String) {
      Log.i("Texting Component", "authToken = " + param1String);
      Texting.access$602(Texting.this, param1String);
      Toast.makeText((Context)Texting.activity, "Finished authentication", 0).show();
      Texting.this.processPendingQueue();
    }
  }
  
  class AsyncSendMessage extends AsyncTask<String, Void, String> {
    protected String doInBackground(String... param1VarArgs) {
      String str3 = param1VarArgs[0];
      String str4 = param1VarArgs[1];
      String str2 = "";
      Log.i("Texting Component", "Async sending phoneNumber = " + str3 + " message = " + str4);
      String str1 = str2;
      try {
        str3 = URLEncoder.encode("phoneNumber", "UTF-8") + "=" + URLEncoder.encode(str3, "UTF-8") + "&" + URLEncoder.encode("text", "UTF-8") + "=" + URLEncoder.encode(str4, "UTF-8");
        str1 = str2;
        if (Texting.this.gvHelper == null) {
          str1 = str2;
          Texting.access$802(Texting.this, new Texting.GoogleVoiceUtil(Texting.this.authToken));
        } 
        str1 = str2;
        if (Texting.this.gvHelper.isInitialized()) {
          str1 = str2;
          str2 = Texting.this.gvHelper.sendGvSms(str3);
          str1 = str2;
          Log.i("Texting Component", "Sent SMS, response = " + str2);
          str1 = str2;
        } else {
          return "IO Error: unable to create GvHelper";
        } 
      } catch (Exception exception) {
        exception.printStackTrace();
      } 
      return str1;
    }
    
    protected void onPostExecute(String param1String) {
      super.onPostExecute(param1String);
      boolean bool2 = false;
      int i = 0;
      boolean bool1 = bool2;
      try {
        JSONObject jSONObject = new JSONObject(param1String);
        bool1 = bool2;
        bool2 = jSONObject.getBoolean("ok");
        bool1 = bool2;
        int j = jSONObject.getJSONObject("data").getInt("code");
        i = j;
        bool1 = bool2;
        if (bool1) {
          Toast.makeText((Context)Texting.activity, "Message sent", 0).show();
          return;
        } 
      } catch (JSONException jSONException) {
        jSONException.printStackTrace();
        if (bool1) {
          Toast.makeText((Context)Texting.activity, "Message sent", 0).show();
          return;
        } 
      } 
      if (i == 58) {
        Toast.makeText((Context)Texting.activity, "Errcode 58: SMS limit reached", 0).show();
        return;
      } 
      if (param1String.contains("IO Error")) {
        Toast.makeText((Context)Texting.activity, param1String, 0).show();
        return;
      } 
    }
  }
  
  class GoogleVoiceUtil {
    private static final String COOKIES_HEADER = "Set-Cookie";
    
    private final int MAX_REDIRECTS = 5;
    
    String authToken;
    
    CookieManager cookies = new CookieManager();
    
    String general;
    
    private boolean isInitialized;
    
    int redirectCounter;
    
    String rnrSEE;
    
    public GoogleVoiceUtil(String param1String) {
      Log.i("Texting Component", "Creating GV Util");
      this.authToken = param1String;
      try {
        this.general = getGeneral();
        Log.i("Texting Component", "general = " + this.general);
        setRNRSEE();
        this.isInitialized = true;
        return;
      } catch (IOException iOException) {
        iOException.printStackTrace();
        return;
      } 
    }
    
    private String sendGvSms(String param1String) {
      Log.i("Texting Component", "sendGvSms()");
      StringBuilder stringBuilder = new StringBuilder();
      try {
        String str = param1String + "&" + URLEncoder.encode("_rnr_se", "UTF-8") + "=" + URLEncoder.encode(this.rnrSEE, "UTF-8");
        Log.i("Texting Component", "smsData = " + str);
        HttpURLConnection httpURLConnection = (HttpURLConnection)(new URL("https://www.google.com/voice/b/0/sms/send/")).openConnection();
        httpURLConnection.setRequestProperty("Authorization", "GoogleLogin auth=" + this.authToken);
        httpURLConnection.setRequestProperty("User-agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13");
        setCookies(httpURLConnection);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setConnectTimeout(30000);
        Log.i("Texting Component", "sms request = " + httpURLConnection);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
        outputStreamWriter.write(str);
        outputStreamWriter.flush();
        processCookies(httpURLConnection);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        while (true) {
          String str1 = bufferedReader.readLine();
          if (str1 != null) {
            stringBuilder.append(str1);
            stringBuilder.append("\n");
            continue;
          } 
          Log.i("Texting Component", "sendGvSms:  Sent SMS, response = " + stringBuilder);
          outputStreamWriter.close();
          bufferedReader.close();
          if (stringBuilder.length() == 0)
            throw new IOException("No Response Data Received."); 
          return stringBuilder.toString();
        } 
      } catch (IOException iOException) {
        Log.i("Texting Component", "IO Error on Send " + iOException.getMessage(), iOException);
        return "IO Error Message not sent";
      } 
    }
    
    private void setRNRSEE() throws IOException {
      Log.i("Texting Component", "setRNRSEE()");
      if (this.general != null) {
        if (this.general.contains("'_rnr_se': '")) {
          this.rnrSEE = this.general.split("'_rnr_se': '", 2)[1].split("',", 2)[0];
          Log.i("Texting Component", "Successfully Received rnr_se.");
          return;
        } 
        Log.i("Texting Component", "Answer did not contain rnr_se! " + this.general);
        throw new IOException("Answer did not contain rnr_se! " + this.general);
      } 
      Log.i("Texting Component", "setRNRSEE(): Answer was null!");
      throw new IOException("setRNRSEE(): Answer was null!");
    }
    
    String get(String param1String) throws IOException {
      HttpURLConnection httpURLConnection = (HttpURLConnection)(new URL(param1String)).openConnection();
      int j = 0;
      int i = j;
      try {
        InputStream inputStream;
        httpURLConnection.setRequestProperty("Authorization", "GoogleLogin auth=" + this.authToken);
        i = j;
        httpURLConnection.setRequestProperty("User-agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13");
        i = j;
        httpURLConnection.setInstanceFollowRedirects(false);
        i = j;
        setCookies(httpURLConnection);
        i = j;
        httpURLConnection.connect();
        i = j;
        j = httpURLConnection.getResponseCode();
        i = j;
        Log.i("Texting Component", param1String + " - " + httpURLConnection.getResponseMessage());
        processCookies(httpURLConnection);
        if (j == 200) {
          inputStream = httpURLConnection.getInputStream();
        } else {
          if (j == 301 || j == 302 || j == 303 || j == 307) {
            this.redirectCounter++;
            if (this.redirectCounter > 5) {
              this.redirectCounter = 0;
              throw new IOException(param1String + " : " + httpURLConnection.getResponseMessage() + "(" + j + ") : Too many redirects. exiting.");
            } 
            String str = httpURLConnection.getHeaderField("Location");
            if (str != null && !str.equals("")) {
              System.out.println(param1String + " - " + j + " - new URL: " + str);
              return get(str);
            } 
            throw new IOException(param1String + " : " + httpURLConnection.getResponseMessage() + "(" + j + ") : Received moved answer but no Location. exiting.");
          } 
          inputStream = httpURLConnection.getErrorStream();
        } 
        this.redirectCounter = 0;
        if (inputStream == null)
          throw new IOException(param1String + " : " + httpURLConnection.getResponseMessage() + "(" + j + ") : InputStream was null : exiting."); 
      } catch (Exception exception) {
        throw new IOException(param1String + " : " + httpURLConnection.getResponseMessage() + "(" + i + ") : IO Error.");
      } 
      try {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream)exception));
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
          String str = bufferedReader.readLine();
          if (str != null) {
            stringBuffer.append(str + "\n\r");
            continue;
          } 
          bufferedReader.close();
          return stringBuffer.toString();
        } 
      } catch (Exception exception1) {
        throw new IOException(param1String + " - " + httpURLConnection.getResponseMessage() + "(" + j + ") - " + exception1.getLocalizedMessage());
      } 
    }
    
    public String getGeneral() throws IOException {
      Log.i("Texting Component", "getGeneral()");
      return get("https://www.google.com/voice/b/0/redirection/voice");
    }
    
    public boolean isInitialized() {
      return this.isInitialized;
    }
    
    void processCookies(HttpURLConnection param1HttpURLConnection) {
      List list = param1HttpURLConnection.getHeaderFields().get("Set-Cookie");
      if (list != null)
        for (String str : list)
          this.cookies.getCookieStore().add(null, HttpCookie.parse(str).get(0));  
    }
    
    void setCookies(HttpURLConnection param1HttpURLConnection) {
      if (this.cookies.getCookieStore().getCookies().size() > 0)
        param1HttpURLConnection.setRequestProperty("Cookie", TextUtils.join(";", this.cookies.getCookieStore().getCookies())); 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Texting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
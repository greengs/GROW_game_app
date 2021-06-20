package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesNativeLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AppInvHTTPD;
import com.google.appinventor.components.runtime.util.EclairUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.WebRTCNativeMgr;
import java.security.MessageDigest;
import java.util.Formatter;
import org.acra.util.Installation;

@DesignerComponent(category = ComponentCategory.INTERNAL, description = "Component that returns information about the phone.", iconName = "images/phoneip.png", nonVisible = true, version = 1)
@SimpleObject
@UsesLibraries(libraries = "webrtc.jar,google-http-client-beta.jar,google-http-client-android2-beta.jar,google-http-client-android3-beta.jar")
@UsesNativeLibraries(v7aLibraries = "libjingle_peerconnection_so.so", v8aLibraries = "libjingle_peerconnection_so.so", x86_64Libraries = "libjingle_peerconnection_so.so")
public class PhoneStatus extends AndroidNonvisibleComponent implements Component {
  private static final String LOG_TAG = "PhoneStatus";
  
  private static Activity activity;
  
  private static PhoneStatus mainInstance = null;
  
  private static boolean useWebRTC = false;
  
  private String firstHmacSeed = null;
  
  private String firstSeed = null;
  
  private final Form form;
  
  public PhoneStatus(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.form = paramComponentContainer.$form();
    activity = paramComponentContainer.$context();
    if (mainInstance == null)
      mainInstance = this; 
  }
  
  @SimpleFunction(description = "Returns the IP address of the phone in the form of a String")
  public static String GetWifiIpAddress() {
    int i = (((WifiManager)activity.getSystemService("wifi")).getDhcpInfo()).ipAddress;
    return isConnected() ? intToIp(i) : "Error: No Wifi Connection";
  }
  
  @SimpleFunction(description = "Causes an Exception, used to debug exception processing.")
  public static void doFault() throws Exception {
    throw new Exception("doFault called!");
  }
  
  static void doSettings() {
    Log.d("PhoneStatus", "doSettings called.");
    if (mainInstance != null) {
      mainInstance.OnSettings();
      return;
    } 
    Log.d("PhoneStatus", "mainStance is null on doSettings");
  }
  
  public static boolean getUseWebRTC() {
    return useWebRTC;
  }
  
  public static String intToIp(int paramInt) {
    return (paramInt & 0xFF) + "." + (paramInt >> 8 & 0xFF) + "." + (paramInt >> 16 & 0xFF) + "." + (paramInt >> 24 & 0xFF);
  }
  
  @SimpleFunction(description = "Returns TRUE if the phone is on Wifi, FALSE otherwise")
  public static boolean isConnected() {
    ConnectivityManager connectivityManager = (ConnectivityManager)activity.getSystemService("connectivity");
    NetworkInfo networkInfo = null;
    if (connectivityManager != null)
      networkInfo = connectivityManager.getNetworkInfo(1); 
    return (networkInfo == null) ? false : networkInfo.isConnected();
  }
  
  @SimpleFunction(description = "Return the app that installed us")
  public String GetInstaller() {
    if (SdkLevel.getLevel() >= 5) {
      String str2 = EclairUtil.getInstallerPackageName("edu.mit.appinventor.aicompanion3", this.form);
      String str1 = str2;
      if (str2 == null)
        str1 = "sideloaded"; 
      return str1;
    } 
    return "unknown";
  }
  
  @SimpleFunction(description = "Return the our VersionName property")
  public String GetVersionName() {
    try {
      null = this.form.getPackageName();
      return (this.form.getPackageManager().getPackageInfo(null, 0)).versionName;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.e("PhoneStatus", "Unable to get VersionName", (Throwable)nameNotFoundException);
      return "UNKNOWN";
    } 
  }
  
  @SimpleFunction(description = "Return the ACRA Installation ID")
  public String InstallationId() {
    return Installation.id((Context)this.form);
  }
  
  @SimpleEvent
  public void OnSettings() {
    EventDispatcher.dispatchEvent(this, "OnSettings", new Object[0]);
  }
  
  @SimpleFunction(description = "Get the current Android SDK Level")
  public int SdkLevel() {
    return SdkLevel.getLevel();
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void WebRTC(boolean paramBoolean) {
    useWebRTC = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If True we are using WebRTC to talk to the server.")
  public boolean WebRTC() {
    return useWebRTC;
  }
  
  @SimpleFunction(description = "Downloads the URL and installs it as an Android Package via the installed browser")
  public void installURL(String paramString) {
    try {
      Class<?> clazz = Class.forName("edu.mit.appinventor.companionextras.CompanionExtras");
      Object object = clazz.getConstructor(new Class[] { Form.class }).newInstance(new Object[] { this.form });
      clazz.getMethod("Extra1", new Class[] { String.class }).invoke(object, new Object[] { paramString });
      return;
    } catch (Exception exception) {
      Uri uri = Uri.parse(paramString + "?store=1");
      Intent intent = (new Intent("android.intent.action.VIEW")).setData(uri);
      this.form.startActivity(intent);
      return;
    } 
  }
  
  @SimpleFunction(description = "Returns true if we are running in the emulator or USB Connection")
  public boolean isDirect() {
    Log.d("PhoneStatus", "android.os.Build.VERSION.RELEASE = " + Build.VERSION.RELEASE);
    Log.d("PhoneStatus", "android.os.Build.PRODUCT = " + Build.PRODUCT);
    return ReplForm.isEmulator() ? true : ((this.form instanceof ReplForm) ? ((ReplForm)this.form).isDirect() : false);
  }
  
  @SimpleFunction(description = "Declare that we have loaded our initial assets and other assets should come from the sdcard")
  public void setAssetsLoaded() {
    if (this.form instanceof ReplForm)
      ((ReplForm)this.form).setAssetsLoaded(); 
  }
  
  @SimpleFunction(description = "Establish the secret seed for HOTP generation. Return the SHA1 of the provided seed, this will be used to contact the rendezvous server. Note: This code also starts the connection negotiation process if we are using WebRTC. This is a bit of a kludge...")
  public String setHmacSeedReturnCode(String paramString1, String paramString2) {
    StringBuffer stringBuffer;
    if (paramString1.equals(""))
      return ""; 
    if (this.firstSeed != null) {
      if (!this.firstSeed.equals(paramString1))
        Notifier.oneButtonAlert(this.form, "You cannot use two codes with one start up of the Companion. You should restart the Companion and try again.", "Warning", "OK", new Runnable() {
              public void run() {
                PhoneStatus.this.form.finish();
                System.exit(0);
              }
            }); 
      return this.firstHmacSeed;
    } 
    this.firstSeed = paramString1;
    if (!useWebRTC)
      AppInvHTTPD.setHmacKey(paramString1); 
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
      messageDigest.update(paramString1.getBytes());
      byte[] arrayOfByte = messageDigest.digest();
      stringBuffer = new StringBuffer(arrayOfByte.length * 2);
      Formatter formatter = new Formatter(stringBuffer);
      int j = arrayOfByte.length;
      for (int i = 0; i < j; i++) {
        formatter.format("%02x", new Object[] { Byte.valueOf(arrayOfByte[i]) });
      } 
    } catch (Exception exception) {
      Log.e("PhoneStatus", "Exception getting SHA1 Instance", exception);
      return "";
    } 
    Log.d("PhoneStatus", "Seed = " + exception);
    Log.d("PhoneStatus", "Code = " + stringBuffer.toString());
    this.firstHmacSeed = stringBuffer.toString();
    return this.firstHmacSeed;
  }
  
  @SimpleFunction(description = "Really Exit the Application")
  public void shutdown() {
    this.form.finish();
    System.exit(0);
  }
  
  @SimpleFunction(description = "Start the internal AppInvHTTPD to listen for incoming forms. FOR REPL USE ONLY!")
  public void startHTTPD(boolean paramBoolean) {
    if (this.form.isRepl())
      ((ReplForm)this.form).startHTTPD(paramBoolean); 
  }
  
  @SimpleFunction(description = "Start the WebRTC engine")
  public void startWebRTC(String paramString1, String paramString2) {
    if (!useWebRTC)
      return; 
    WebRTCNativeMgr webRTCNativeMgr = new WebRTCNativeMgr(paramString1, paramString2);
    webRTCNativeMgr.initiate((ReplForm)this.form, (Context)activity, this.firstSeed);
    ((ReplForm)this.form).setWebRTCMgr(webRTCNativeMgr);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/PhoneStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
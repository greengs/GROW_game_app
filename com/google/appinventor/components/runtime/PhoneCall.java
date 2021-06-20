package com.google.appinventor.components.runtime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.telephony.TelephonyManager;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.PhoneCallUtil;

@DesignerComponent(category = ComponentCategory.SOCIAL, description = "<p>A non-visible component that makes a phone call to the number specified in the <code>PhoneNumber</code> property, which can be set either in the Designer or Blocks Editor. The component has a <code>MakePhoneCall</code> method, enabling the program to launch a phone call.</p><p>Often, this component is used with the <code>ContactPicker</code> component, which lets the user select a contact from the ones stored on the phone and sets the <code>PhoneNumber</code> property to the contact's phone number.</p><p>To directly specify the phone number (e.g., 650-555-1212), set the <code>PhoneNumber</code> property to a Text with the specified digits (e.g., \"6505551212\").  Dashes, dots, and parentheses may be included (e.g., \"(650)-555-1212\") but will be ignored; spaces may not be included.</p>", iconName = "images/phoneCall.png", nonVisible = true, version = 3)
@SimpleObject
public class PhoneCall extends AndroidNonvisibleComponent implements Component, OnDestroyListener, ActivityResultListener {
  private static final int PHONECALL_REQUEST_CODE = 1346916174;
  
  private final CallStateReceiver callStateReceiver;
  
  private final Context context;
  
  private boolean didRegisterReceiver = false;
  
  private boolean havePermission = false;
  
  private String phoneNumber;
  
  public PhoneCall(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.context = (Context)paramComponentContainer.$context();
    this.form.registerForOnDestroy(this);
    this.form.registerForActivityResult(this, 1346916174);
    PhoneNumber("");
    this.callStateReceiver = new CallStateReceiver();
  }
  
  private void registerCallStateMonitor() {
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
    intentFilter.addAction("android.intent.action.PHONE_STATE");
    this.context.registerReceiver(this.callStateReceiver, intentFilter);
    this.didRegisterReceiver = true;
  }
  
  private void unregisterCallStateMonitor() {
    if (this.didRegisterReceiver) {
      this.context.unregisterReceiver(this.callStateReceiver);
      this.didRegisterReceiver = false;
    } 
  }
  
  @SimpleEvent(description = "Event indicating that an incoming phone call is answered. phoneNumber is the incoming call phone number.")
  @UsesPermissions({"android.permission.PROCESS_OUTGOING_CALLS", "android.permission.READ_CALL_LOG", "android.permission.READ_PHONE_STATE"})
  public void IncomingCallAnswered(String paramString) {
    EventDispatcher.dispatchEvent(this, "IncomingCallAnswered", new Object[] { paramString });
  }
  
  public void Initialize() {
    if (this.form.doesAppDeclarePermission("android.permission.READ_CALL_LOG"))
      this.form.askPermission(new BulkPermissionRequest(this, "Initialize", new String[] { "android.permission.PROCESS_OUTGOING_CALLS", "android.permission.READ_PHONE_STATE", "android.permission.READ_CALL_LOG" }) {
            public void onGranted() {
              PhoneCall.this.registerCallStateMonitor();
            }
          }); 
  }
  
  @SimpleFunction(description = "Launches the default dialer app set to start a phone call usingthe number in the PhoneNumber property.")
  public void MakePhoneCall() {
    Intent intent = new Intent("android.intent.action.DIAL", Uri.fromParts("tel", this.phoneNumber, null));
    if (intent.resolveActivity(this.form.getPackageManager()) != null)
      this.form.startActivityForResult(intent, 1346916174); 
  }
  
  @SimpleFunction(description = "Directly initiates a phone call using the number in the PhoneNumber property.")
  @UsesPermissions({"android.permission.CALL_PHONE"})
  public void MakePhoneCallDirect() {
    if (!this.havePermission) {
      this.form.askPermission("android.permission.CALL_PHONE", new PermissionResultHandler() {
            public void HandlePermissionResponse(String param1String, boolean param1Boolean) {
              if (param1Boolean) {
                PhoneCall.access$102(PhoneCall.this, true);
                PhoneCall.this.MakePhoneCallDirect();
                return;
              } 
              PhoneCall.this.form.dispatchPermissionDeniedEvent(PhoneCall.this, "MakePhoneCall", "android.permission.CALL_PHONE");
            }
          });
      return;
    } 
    PhoneCallUtil.makePhoneCall(this.context, this.phoneNumber);
  }
  
  @SimpleEvent(description = "Event indicating that a phone call has ended. If status is 1, incoming call is missed or rejected; if status is 2, incoming call is answered before hanging up; if status is 3, outgoing call is hung up. phoneNumber is the ended call phone number.")
  @UsesPermissions({"android.permission.PROCESS_OUTGOING_CALLS", "android.permission.READ_CALL_LOG", "android.permission.READ_PHONE_STATE"})
  public void PhoneCallEnded(int paramInt, String paramString) {
    EventDispatcher.dispatchEvent(this, "PhoneCallEnded", new Object[] { Integer.valueOf(paramInt), paramString });
  }
  
  @SimpleEvent(description = "Event indicating that a phonecall has started. If status is 1, incoming call is ringing; if status is 2, outgoing call is dialled. phoneNumber is the incoming/outgoing phone number.")
  @UsesPermissions({"android.permission.PROCESS_OUTGOING_CALLS", "android.permission.READ_CALL_LOG", "android.permission.READ_PHONE_STATE"})
  public void PhoneCallStarted(int paramInt, String paramString) {
    EventDispatcher.dispatchEvent(this, "PhoneCallStarted", new Object[] { Integer.valueOf(paramInt), paramString });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String PhoneNumber() {
    return this.phoneNumber;
  }
  
  @DesignerProperty(editorType = "string")
  @SimpleProperty
  public void PhoneNumber(String paramString) {
    this.phoneNumber = paramString;
  }
  
  public void onDestroy() {
    unregisterCallStateMonitor();
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent) {
    if (paramInt1 == 1346916174)
      PhoneCallStarted(2, ""); 
  }
  
  private class CallStateReceiver extends BroadcastReceiver {
    private String number = "";
    
    private int status = 0;
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      String str = param1Intent.getAction();
      if ("android.intent.action.PHONE_STATE".equals(str)) {
        str = param1Intent.getStringExtra("state");
        if (TelephonyManager.EXTRA_STATE_RINGING.equals(str)) {
          this.status = 1;
          this.number = param1Intent.getStringExtra("incoming_number");
          if (this.number != null) {
            PhoneCall.this.PhoneCallStarted(1, this.number);
            return;
          } 
          return;
        } 
        if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(str)) {
          if (this.status == 1) {
            this.status = 3;
            PhoneCall.this.IncomingCallAnswered(this.number);
            return;
          } 
          return;
        } 
        if (TelephonyManager.EXTRA_STATE_IDLE.equals(str)) {
          if (this.status == 1) {
            PhoneCall.this.PhoneCallEnded(1, this.number);
          } else if (this.status == 3) {
            PhoneCall.this.PhoneCallEnded(2, this.number);
          } else if (this.status == 2) {
            PhoneCall.this.PhoneCallEnded(3, this.number);
          } 
          this.status = 0;
          this.number = "";
          return;
        } 
        return;
      } 
      if ("android.intent.action.NEW_OUTGOING_CALL".equals(str)) {
        this.status = 2;
        this.number = param1Intent.getStringExtra("android.intent.extra.PHONE_NUMBER");
        PhoneCall.this.PhoneCallStarted(2, this.number);
        return;
      } 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/PhoneCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
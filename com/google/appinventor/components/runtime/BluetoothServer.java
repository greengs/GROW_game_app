package com.google.appinventor.components.runtime;

import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Bluetooth server component", iconName = "images/bluetooth.png", nonVisible = true, version = 5)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.BLUETOOTH, android.permission.BLUETOOTH_ADMIN")
public final class BluetoothServer extends BluetoothConnectionBase {
  private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
  
  private final Handler androidUIHandler = new Handler();
  
  private final AtomicReference<Object> arBluetoothServerSocket = new AtomicReference();
  
  public BluetoothServer(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "BluetoothServer");
  }
  
  private void accept(final String functionName, String paramString2, String paramString3) {
    Object object = BluetoothReflection.getBluetoothAdapter();
    if (object == null) {
      this.form.dispatchErrorOccurredEvent(this, functionName, 501, new Object[0]);
      return;
    } 
    if (!BluetoothReflection.isBluetoothEnabled(object)) {
      this.form.dispatchErrorOccurredEvent(this, functionName, 502, new Object[0]);
      return;
    } 
    try {
      UUID uUID = UUID.fromString(paramString3);
      try {
        Object object1;
        if (!this.secure && SdkLevel.getLevel() >= 10) {
          object1 = BluetoothReflection.listenUsingInsecureRfcommWithServiceRecord(object, paramString2, uUID);
        } else {
          object1 = BluetoothReflection.listenUsingRfcommWithServiceRecord(object, (String)object1, uUID);
        } 
        this.arBluetoothServerSocket.set(object1);
        AsynchUtil.runAsynchronously(new Runnable() {
              public void run() {
                final Object bluetoothSocket = null;
                Object object1 = BluetoothServer.this.arBluetoothServerSocket.get();
                if (object1 != null)
                  try {
                    object = BluetoothReflection.accept(object1);
                    BluetoothServer.this.StopAccepting();
                    return;
                  } catch (IOException iOException) {
                    BluetoothServer.this.androidUIHandler.post(new Runnable() {
                          public void run() {
                            BluetoothServer.this.form.dispatchErrorOccurredEvent(BluetoothServer.this, functionName, 509, new Object[0]);
                          }
                        });
                    return;
                  } finally {
                    BluetoothServer.this.StopAccepting();
                  }  
                if (object != null)
                  BluetoothServer.this.androidUIHandler.post(new Runnable() {
                        public void run() {
                          try {
                            BluetoothServer.this.setConnection(bluetoothSocket);
                            BluetoothServer.this.ConnectionAccepted();
                            return;
                          } catch (IOException iOException) {
                            BluetoothServer.this.Disconnect();
                            BluetoothServer.this.form.dispatchErrorOccurredEvent(BluetoothServer.this, functionName, 509, new Object[0]);
                            return;
                          } 
                        }
                      }); 
              }
            });
        return;
      } catch (IOException iOException) {
        this.form.dispatchErrorOccurredEvent(this, functionName, 508, new Object[0]);
      } 
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, functionName, 506, new Object[] { paramString3 });
      return;
    } 
  }
  
  @SimpleFunction(description = "Accept an incoming connection with the Serial Port Profile (SPP).")
  public void AcceptConnection(String paramString) {
    accept("AcceptConnection", paramString, "00001101-0000-1000-8000-00805F9B34FB");
  }
  
  @SimpleFunction(description = "Accept an incoming connection with a specific UUID.")
  public void AcceptConnectionWithUUID(String paramString1, String paramString2) {
    accept("AcceptConnectionWithUUID", paramString1, paramString2);
  }
  
  @SimpleEvent(description = "Indicates that a bluetooth connection has been accepted.")
  public void ConnectionAccepted() {
    Log.i(this.logTag, "Successfullly accepted bluetooth connection.");
    EventDispatcher.dispatchEvent(this, "ConnectionAccepted", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public final boolean IsAccepting() {
    return (this.arBluetoothServerSocket.get() != null);
  }
  
  @SimpleFunction(description = "Stop accepting an incoming connection.")
  public void StopAccepting() {
    Object object = this.arBluetoothServerSocket.getAndSet(null);
    if (object != null)
      try {
        BluetoothReflection.closeBluetoothServerSocket(object);
        return;
      } catch (IOException iOException) {
        Log.w(this.logTag, "Error while closing bluetooth server socket: " + iOException.getMessage());
        return;
      }  
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/BluetoothServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
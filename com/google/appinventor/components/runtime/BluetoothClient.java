package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Bluetooth client component", iconName = "images/bluetooth.png", nonVisible = true, version = 6)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.BLUETOOTH, android.permission.BLUETOOTH_ADMIN")
public final class BluetoothClient extends BluetoothConnectionBase {
  private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
  
  private Set<Integer> acceptableDeviceClasses;
  
  private final List<Component> attachedComponents = new ArrayList<Component>();
  
  public BluetoothClient(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "BluetoothClient");
    DisconnectOnError(false);
  }
  
  private void connect(Object paramObject, UUID paramUUID) throws IOException {
    Object object;
    if (!this.secure && SdkLevel.getLevel() >= 10) {
      object = BluetoothReflection.createInsecureRfcommSocketToServiceRecord(paramObject, paramUUID);
    } else {
      object = BluetoothReflection.createRfcommSocketToServiceRecord(paramObject, (UUID)object);
    } 
    BluetoothReflection.connectToBluetoothSocket(object);
    setConnection(object);
    Log.i(this.logTag, "Connected to Bluetooth device " + BluetoothReflection.getBluetoothDeviceAddress(paramObject) + " " + BluetoothReflection.getBluetoothDeviceName(paramObject) + ".");
  }
  
  private boolean connect(String paramString1, String paramString2, String paramString3) {
    Object object2 = BluetoothReflection.getBluetoothAdapter();
    if (object2 == null) {
      this.form.dispatchErrorOccurredEvent(this, paramString1, 501, new Object[0]);
      return false;
    } 
    if (!BluetoothReflection.isBluetoothEnabled(object2)) {
      this.form.dispatchErrorOccurredEvent(this, paramString1, 502, new Object[0]);
      return false;
    } 
    int i = paramString2.indexOf(" ");
    String str = paramString2;
    if (i != -1)
      str = paramString2.substring(0, i); 
    if (!BluetoothReflection.checkBluetoothAddress(object2, str)) {
      this.form.dispatchErrorOccurredEvent(this, paramString1, 503, new Object[0]);
      return false;
    } 
    Object object1 = BluetoothReflection.getRemoteDevice(object2, str);
    if (!BluetoothReflection.isBonded(object1)) {
      this.form.dispatchErrorOccurredEvent(this, paramString1, 504, new Object[0]);
      return false;
    } 
    if (!isDeviceClassAcceptable(object1)) {
      this.form.dispatchErrorOccurredEvent(this, paramString1, 505, new Object[0]);
      return false;
    } 
    try {
      UUID uUID = UUID.fromString(paramString3);
      Disconnect();
      try {
        connect(object1, uUID);
        return true;
      } catch (IOException iOException) {
        Disconnect();
        this.form.dispatchErrorOccurredEvent(this, paramString1, 507, new Object[0]);
        return false;
      } 
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, paramString1, 506, new Object[] { paramString3 });
      return false;
    } 
  }
  
  private boolean isDeviceClassAcceptable(Object paramObject) {
    if (this.acceptableDeviceClasses == null)
      return true; 
    paramObject = BluetoothReflection.getBluetoothClass(paramObject);
    if (paramObject == null)
      return false; 
    int i = BluetoothReflection.getDeviceClass(paramObject);
    return this.acceptableDeviceClasses.contains(Integer.valueOf(i));
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The addresses and names of paired Bluetooth devices")
  public List<String> AddressesAndNames() {
    ArrayList<String> arrayList = new ArrayList();
    Object object = BluetoothReflection.getBluetoothAdapter();
    if (object != null && BluetoothReflection.isBluetoothEnabled(object))
      for (String str : BluetoothReflection.getBondedDevices(object)) {
        if (isDeviceClassAcceptable(str)) {
          String str1 = BluetoothReflection.getBluetoothDeviceName(str);
          str = BluetoothReflection.getBluetoothDeviceAddress(str);
          arrayList.add(str + " " + str1);
        } 
      }  
    return arrayList;
  }
  
  @SimpleFunction(description = "Connect to the Bluetooth device with the specified address and the Serial Port Profile (SPP). Returns true if the connection was successful.")
  public boolean Connect(String paramString) {
    return connect("Connect", paramString, "00001101-0000-1000-8000-00805F9B34FB");
  }
  
  @SimpleFunction(description = "Connect to the Bluetooth device with the specified address and UUID. Returns true if the connection was successful.")
  public boolean ConnectWithUUID(String paramString1, String paramString2) {
    return connect("ConnectWithUUID", paramString1, paramString2);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void DisconnectOnError(boolean paramBoolean) {
    this.disconnectOnError = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Disconnects BluetoothClient automatically when an error occurs.")
  public boolean DisconnectOnError() {
    return this.disconnectOnError;
  }
  
  @SimpleFunction(description = "Checks whether the Bluetooth device with the specified address is paired.")
  public boolean IsDevicePaired(String paramString) {
    Object object = BluetoothReflection.getBluetoothAdapter();
    if (object == null) {
      this.form.dispatchErrorOccurredEvent(this, "IsDevicePaired", 501, new Object[0]);
      return false;
    } 
    if (!BluetoothReflection.isBluetoothEnabled(object)) {
      this.form.dispatchErrorOccurredEvent(this, "IsDevicePaired", 502, new Object[0]);
      return false;
    } 
    int i = paramString.indexOf(" ");
    String str = paramString;
    if (i != -1)
      str = paramString.substring(0, i); 
    if (!BluetoothReflection.checkBluetoothAddress(object, str)) {
      this.form.dispatchErrorOccurredEvent(this, "IsDevicePaired", 503, new Object[0]);
      return false;
    } 
    return BluetoothReflection.isBonded(BluetoothReflection.getRemoteDevice(object, str));
  }
  
  boolean attachComponent(Component paramComponent, Set<Integer> paramSet) {
    boolean bool = false;
    if (this.attachedComponents.isEmpty()) {
      if (paramSet == null) {
        paramSet = null;
      } else {
        paramSet = new HashSet<Integer>(paramSet);
      } 
      this.acceptableDeviceClasses = paramSet;
    } else if (this.acceptableDeviceClasses == null) {
      if (paramSet != null)
        return false; 
    } else {
      boolean bool1 = bool;
      if (paramSet != null) {
        bool1 = bool;
        if (this.acceptableDeviceClasses.containsAll(paramSet)) {
          if (!paramSet.containsAll(this.acceptableDeviceClasses))
            return false; 
        } else {
          return bool1;
        } 
      } else {
        return bool1;
      } 
    } 
    this.attachedComponents.add(paramComponent);
    return true;
  }
  
  void detachComponent(Component paramComponent) {
    this.attachedComponents.remove(paramComponent);
    if (this.attachedComponents.isEmpty())
      this.acceptableDeviceClasses = null; 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/BluetoothClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
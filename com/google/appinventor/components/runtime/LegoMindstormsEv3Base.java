package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;
import java.util.Collections;

@SimpleObject
public class LegoMindstormsEv3Base extends AndroidNonvisibleComponent implements BluetoothConnectionListener, Component, Deleteable {
  private static final int TOY_ROBOT = 2052;
  
  protected BluetoothClient bluetooth;
  
  protected int commandCount;
  
  protected final String logTag = null;
  
  protected LegoMindstormsEv3Base() {
    super(null);
  }
  
  protected LegoMindstormsEv3Base(ComponentContainer paramComponentContainer, String paramString) {
    super(paramComponentContainer.$form());
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The BluetoothClient component that should be used for communication.")
  public BluetoothClient BluetoothClient() {
    return this.bluetooth;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "BluetoothClient")
  @SimpleProperty
  public void BluetoothClient(BluetoothClient paramBluetoothClient) {
    if (this.bluetooth != null) {
      this.bluetooth.removeBluetoothConnectionListener(this);
      this.bluetooth.detachComponent(this);
      this.bluetooth = null;
    } 
    if (paramBluetoothClient != null) {
      this.bluetooth = paramBluetoothClient;
      this.bluetooth.attachComponent(this, Collections.singleton(Integer.valueOf(2052)));
      this.bluetooth.addBluetoothConnectionListener(this);
      if (this.bluetooth.IsConnected())
        afterConnect(this.bluetooth); 
    } 
  }
  
  public void afterConnect(BluetoothConnectionBase paramBluetoothConnectionBase) {}
  
  public void beforeDisconnect(BluetoothConnectionBase paramBluetoothConnectionBase) {}
  
  protected final String bitFieldToMotorPortLetters(int paramInt) {
    if (paramInt < 0 || paramInt > 15)
      throw new IllegalArgumentException("Invalid bit field number " + paramInt); 
    String str2 = "";
    if ((paramInt & 0x1) != 0)
      str2 = "" + "A"; 
    String str1 = str2;
    if ((paramInt & 0x2) != 0)
      str1 = str2 + "B"; 
    str2 = str1;
    if ((paramInt & 0x4) != 0)
      str2 = str1 + "C"; 
    str1 = str2;
    if ((paramInt & 0x8) != 0)
      str1 = str2 + "D"; 
    return str1;
  }
  
  protected final boolean isBluetoothConnected(String paramString) {
    if (this.bluetooth == null) {
      this.form.dispatchErrorOccurredEvent(this, paramString, 3100, new Object[0]);
      return false;
    } 
    if (!this.bluetooth.IsConnected()) {
      this.form.dispatchErrorOccurredEvent(this, paramString, 3101, new Object[0]);
      return false;
    } 
    return true;
  }
  
  protected final int motorPortLettersToBitField(String paramString) {
    if (paramString.length() > 4)
      throw new IllegalArgumentException("Malformed motor port letters \"" + paramString + "\""); 
    byte b4 = 0;
    byte b3 = 0;
    byte b2 = 0;
    byte b1 = 0;
    int i = 0;
    while (i < paramString.length()) {
      switch (paramString.charAt(i)) {
        case 'A':
          if (b4)
            throw new IllegalArgumentException("Malformed motor port letters \"" + paramString + "\""); 
          b4 = 1;
          i++;
          break;
        case 'B':
          if (b3)
            throw new IllegalArgumentException("Malformed motor port letters \"" + paramString + "\""); 
          b3 = 2;
          i++;
          break;
        case 'C':
          if (b2)
            throw new IllegalArgumentException("Malformed motor port letters \"" + paramString + "\""); 
          b2 = 4;
          i++;
          break;
        case 'D':
          if (b1)
            throw new IllegalArgumentException("Malformed motor port letters \"" + paramString + "\""); 
          b1 = 8;
          i++;
          break;
      } 
    } 
    return b4 | b3 | b2 | b1;
  }
  
  public void onDelete() {
    if (this.bluetooth != null) {
      this.bluetooth.removeBluetoothConnectionListener(this);
      this.bluetooth.detachComponent(this);
      this.bluetooth = null;
    } 
  }
  
  protected final String portNumberToSensorPortLetter(int paramInt) {
    if (paramInt < 0 || paramInt > 3)
      throw new IllegalArgumentException(paramInt + " is not a valid port number"); 
    return "" + (paramInt + 49);
  }
  
  protected final byte[] sendCommand(String paramString, byte[] paramArrayOfbyte, boolean paramBoolean) {
    if (!isBluetoothConnected(paramString))
      return null; 
    byte[] arrayOfByte = Ev3BinaryParser.pack("hh", new Object[] { Short.valueOf((short)(paramArrayOfbyte.length + 2)), Short.valueOf((short)this.commandCount) });
    this.commandCount++;
    this.bluetooth.write(paramString, arrayOfByte);
    this.bluetooth.write(paramString, paramArrayOfbyte);
    if (paramBoolean) {
      paramArrayOfbyte = this.bluetooth.read(paramString, 4);
      if (paramArrayOfbyte.length == 4) {
        Object[] arrayOfObject = Ev3BinaryParser.unpack("hh", paramArrayOfbyte);
        int i = ((Short)arrayOfObject[0]).shortValue() - 2;
        ((Short)arrayOfObject[1]).shortValue();
        arrayOfByte = this.bluetooth.read(paramString, i);
        byte[] arrayOfByte1 = arrayOfByte;
        if (arrayOfByte.length != i) {
          this.form.dispatchErrorOccurredEvent(this, paramString, 3102, new Object[0]);
          return null;
        } 
        return arrayOfByte1;
      } 
      this.form.dispatchErrorOccurredEvent(this, paramString, 3102, new Object[0]);
      return null;
    } 
    return null;
  }
  
  protected final int sensorPortLetterToPortNumber(String paramString) {
    if (paramString.length() != 1)
      throw new IllegalArgumentException("String \"" + paramString + "\" is not a valid sensor port letter"); 
    int i = paramString.charAt(0) - 49;
    if (i < 0 || i > 3)
      throw new IllegalArgumentException("String \"" + paramString + "\" is not a valid sensor port letter"); 
    return i;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/LegoMindstormsEv3Base.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
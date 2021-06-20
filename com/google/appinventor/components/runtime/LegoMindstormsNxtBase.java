package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SimpleObject
public class LegoMindstormsNxtBase extends AndroidNonvisibleComponent implements BluetoothConnectionListener, Component, Deleteable {
  private static final Map<Integer, String> ERROR_MESSAGES = new HashMap<Integer, String>();
  
  private static final int TOY_ROBOT = 2052;
  
  protected BluetoothClient bluetooth;
  
  protected final String logTag = null;
  
  static {
    ERROR_MESSAGES.put(Integer.valueOf(32), "Pending communication transaction in progress");
    ERROR_MESSAGES.put(Integer.valueOf(64), "Specified mailbox queue is empty");
    ERROR_MESSAGES.put(Integer.valueOf(129), "No more handles");
    ERROR_MESSAGES.put(Integer.valueOf(130), "No space");
    ERROR_MESSAGES.put(Integer.valueOf(131), "No more files");
    ERROR_MESSAGES.put(Integer.valueOf(132), "End of file expected");
    ERROR_MESSAGES.put(Integer.valueOf(133), "End of file");
    ERROR_MESSAGES.put(Integer.valueOf(134), "Not a linear file");
    ERROR_MESSAGES.put(Integer.valueOf(135), "File not found");
    ERROR_MESSAGES.put(Integer.valueOf(136), "Handle already closed");
    ERROR_MESSAGES.put(Integer.valueOf(137), "No linear space");
    ERROR_MESSAGES.put(Integer.valueOf(138), "Undefined error");
    ERROR_MESSAGES.put(Integer.valueOf(139), "File is busy");
    ERROR_MESSAGES.put(Integer.valueOf(140), "No write buffers");
    ERROR_MESSAGES.put(Integer.valueOf(141), "Append not possible");
    ERROR_MESSAGES.put(Integer.valueOf(142), "File is full");
    ERROR_MESSAGES.put(Integer.valueOf(143), "File exists");
    ERROR_MESSAGES.put(Integer.valueOf(144), "Module not found");
    ERROR_MESSAGES.put(Integer.valueOf(145), "Out of boundary");
    ERROR_MESSAGES.put(Integer.valueOf(146), "Illegal file name");
    ERROR_MESSAGES.put(Integer.valueOf(147), "Illegal handle");
    ERROR_MESSAGES.put(Integer.valueOf(189), "Request failed (i.e. specified file not found)");
    ERROR_MESSAGES.put(Integer.valueOf(190), "Unknown command opcode");
    ERROR_MESSAGES.put(Integer.valueOf(191), "Insane packet");
    ERROR_MESSAGES.put(Integer.valueOf(192), "Data contains out-of-range values");
    ERROR_MESSAGES.put(Integer.valueOf(221), "Communication bus error");
    ERROR_MESSAGES.put(Integer.valueOf(222), "No free memory in communication buffer");
    ERROR_MESSAGES.put(Integer.valueOf(223), "Specified channel/connection is not valid");
    ERROR_MESSAGES.put(Integer.valueOf(224), "Specified channel/connection not configured or busy");
    ERROR_MESSAGES.put(Integer.valueOf(236), "No active program");
    ERROR_MESSAGES.put(Integer.valueOf(237), "Illegal size specified");
    ERROR_MESSAGES.put(Integer.valueOf(238), "Illegal mailbox queue ID specified");
    ERROR_MESSAGES.put(Integer.valueOf(239), "Attempted to access invalid field of a structure");
    ERROR_MESSAGES.put(Integer.valueOf(240), "Bad input or output specified");
    ERROR_MESSAGES.put(Integer.valueOf(251), "Insufficient memory available");
    ERROR_MESSAGES.put(Integer.valueOf(255), "Bad arguments");
  }
  
  protected LegoMindstormsNxtBase() {
    super(null);
  }
  
  protected LegoMindstormsNxtBase(ComponentContainer paramComponentContainer, String paramString) {
    super(paramComponentContainer.$form());
  }
  
  private void handleError(String paramString, int paramInt) {
    if (paramInt < 0)
      return; 
    String str = ERROR_MESSAGES.get(Integer.valueOf(paramInt));
    if (str != null) {
      this.form.dispatchErrorOccurredEvent(this, paramString, 404, new Object[] { str });
      return;
    } 
    this.form.dispatchErrorOccurredEvent(this, paramString, 404, new Object[] { "Error code 0x" + Integer.toHexString(paramInt & 0xFF) });
  }
  
  private byte[] receiveReturnPackage(String paramString) {
    byte[] arrayOfByte = this.bluetooth.read(paramString, 2);
    if (arrayOfByte.length == 2) {
      int i = getUWORDValueFromBytes(arrayOfByte, 0);
      arrayOfByte = this.bluetooth.read(paramString, i);
      if (arrayOfByte.length >= 3)
        return arrayOfByte; 
    } 
    this.form.dispatchErrorOccurredEvent(this, paramString, 403, new Object[0]);
    return new byte[0];
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The BluetoothClient component that should be used for communication.", userVisible = false)
  public BluetoothClient BluetoothClient() {
    return this.bluetooth;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "BluetoothClient")
  @SimpleProperty(userVisible = false)
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
  
  public final void Initialize() {}
  
  public void afterConnect(BluetoothConnectionBase paramBluetoothConnectionBase) {}
  
  public void beforeDisconnect(BluetoothConnectionBase paramBluetoothConnectionBase) {}
  
  protected final boolean checkBluetooth(String paramString) {
    if (this.bluetooth == null) {
      this.form.dispatchErrorOccurredEvent(this, paramString, 401, new Object[0]);
      return false;
    } 
    if (!this.bluetooth.IsConnected()) {
      this.form.dispatchErrorOccurredEvent(this, paramString, 402, new Object[0]);
      return false;
    } 
    return true;
  }
  
  protected final int convertMotorPortLetterToNumber(char paramChar) {
    if (paramChar == 'A' || paramChar == 'a')
      return 0; 
    if (paramChar == 'B' || paramChar == 'b')
      return 1; 
    if (paramChar == 'C' || paramChar == 'c')
      return 2; 
    throw new IllegalArgumentException("Illegal motor port letter " + paramChar);
  }
  
  protected final int convertMotorPortLetterToNumber(String paramString) {
    if (paramString.length() == 1)
      return convertMotorPortLetterToNumber(paramString.charAt(0)); 
    throw new IllegalArgumentException("Illegal motor port letter " + paramString);
  }
  
  protected final int convertSensorPortLetterToNumber(char paramChar) {
    if (paramChar == '1')
      return 0; 
    if (paramChar == '2')
      return 1; 
    if (paramChar == '3')
      return 2; 
    if (paramChar == '4')
      return 3; 
    throw new IllegalArgumentException("Illegal sensor port letter " + paramChar);
  }
  
  protected final int convertSensorPortLetterToNumber(String paramString) {
    if (paramString.length() == 1)
      return convertSensorPortLetterToNumber(paramString.charAt(0)); 
    throw new IllegalArgumentException("Illegal sensor port letter " + paramString);
  }
  
  protected final void copyBooleanValueToBytes(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt) {
    boolean bool;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    paramArrayOfbyte[paramInt] = bool;
  }
  
  protected final void copySBYTEValueToBytes(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    paramArrayOfbyte[paramInt2] = (byte)paramInt1;
  }
  
  protected final void copySLONGValueToBytes(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    paramArrayOfbyte[paramInt2] = (byte)(paramInt1 & 0xFF);
    paramInt1 >>= 8;
    paramArrayOfbyte[paramInt2 + 1] = (byte)(paramInt1 & 0xFF);
    paramInt1 >>= 8;
    paramArrayOfbyte[paramInt2 + 2] = (byte)(paramInt1 & 0xFF);
    paramArrayOfbyte[paramInt2 + 3] = (byte)(paramInt1 >> 8 & 0xFF);
  }
  
  protected final void copySWORDValueToBytes(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    paramArrayOfbyte[paramInt2] = (byte)(paramInt1 & 0xFF);
    paramArrayOfbyte[paramInt2 + 1] = (byte)(paramInt1 >> 8 & 0xFF);
  }
  
  protected final void copyStringValueToBytes(String paramString, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    byte[] arrayOfByte;
    String str = paramString;
    if (paramString.length() > paramInt2)
      str = paramString.substring(0, paramInt2); 
    try {
      arrayOfByte = str.getBytes("ISO-8859-1");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      Log.w(this.logTag, "UnsupportedEncodingException: " + unsupportedEncodingException.getMessage());
      arrayOfByte = str.getBytes();
    } 
    System.arraycopy(arrayOfByte, 0, paramArrayOfbyte, paramInt1, Math.min(paramInt2, arrayOfByte.length));
  }
  
  protected final void copyUBYTEValueToBytes(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    paramArrayOfbyte[paramInt2] = (byte)paramInt1;
  }
  
  protected final void copyULONGValueToBytes(long paramLong, byte[] paramArrayOfbyte, int paramInt) {
    paramArrayOfbyte[paramInt] = (byte)(int)(paramLong & 0xFFL);
    paramLong >>= 8L;
    paramArrayOfbyte[paramInt + 1] = (byte)(int)(paramLong & 0xFFL);
    paramLong >>= 8L;
    paramArrayOfbyte[paramInt + 2] = (byte)(int)(paramLong & 0xFFL);
    paramArrayOfbyte[paramInt + 3] = (byte)(int)(paramLong >> 8L & 0xFFL);
  }
  
  protected final void copyUWORDValueToBytes(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    paramArrayOfbyte[paramInt2] = (byte)(paramInt1 & 0xFF);
    paramArrayOfbyte[paramInt2 + 1] = (byte)(paramInt1 >> 8 & 0xFF);
  }
  
  protected final boolean evaluateStatus(String paramString, byte[] paramArrayOfbyte, byte paramByte) {
    int i = getStatus(paramString, paramArrayOfbyte, paramByte);
    if (i == 0)
      return true; 
    handleError(paramString, i);
    return false;
  }
  
  protected final boolean getBooleanValueFromBytes(byte[] paramArrayOfbyte, int paramInt) {
    return (paramArrayOfbyte[paramInt] != 0);
  }
  
  protected final byte[] getInputValues(String paramString, int paramInt) {
    byte[] arrayOfByte1 = new byte[3];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 7;
    copyUBYTEValueToBytes(paramInt, arrayOfByte1, 2);
    byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage(paramString, arrayOfByte1);
    if (evaluateStatus(paramString, arrayOfByte2, arrayOfByte1[1])) {
      if (arrayOfByte2.length == 16)
        return arrayOfByte2; 
      Log.w(this.logTag, paramString + ": unexpected return package length " + arrayOfByte2.length + " (expected 16)");
    } 
    return null;
  }
  
  protected final int getSBYTEValueFromBytes(byte[] paramArrayOfbyte, int paramInt) {
    return paramArrayOfbyte[paramInt];
  }
  
  protected final int getSLONGValueFromBytes(byte[] paramArrayOfbyte, int paramInt) {
    return paramArrayOfbyte[paramInt] & 0xFF | (paramArrayOfbyte[paramInt + 1] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 2] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 3] << 24;
  }
  
  protected final int getSWORDValueFromBytes(byte[] paramArrayOfbyte, int paramInt) {
    return paramArrayOfbyte[paramInt] & 0xFF | paramArrayOfbyte[paramInt + 1] << 8;
  }
  
  protected final int getStatus(String paramString, byte[] paramArrayOfbyte, byte paramByte) {
    if (paramArrayOfbyte.length >= 3) {
      if (paramArrayOfbyte[0] != 2)
        Log.w(this.logTag, paramString + ": unexpected return package byte 0: 0x" + Integer.toHexString(paramArrayOfbyte[0] & 0xFF) + " (expected 0x02)"); 
      if (paramArrayOfbyte[1] != paramByte)
        Log.w(this.logTag, paramString + ": unexpected return package byte 1: 0x" + Integer.toHexString(paramArrayOfbyte[1] & 0xFF) + " (expected 0x" + Integer.toHexString(paramByte & 0xFF) + ")"); 
      return getUBYTEValueFromBytes(paramArrayOfbyte, 2);
    } 
    Log.w(this.logTag, paramString + ": unexpected return package length " + paramArrayOfbyte.length + " (expected >= 3)");
    return -1;
  }
  
  protected final String getStringValueFromBytes(byte[] paramArrayOfbyte, int paramInt) {
    byte b = 0;
    for (int i = paramInt;; i++) {
      int j = b;
      if (i < paramArrayOfbyte.length) {
        if (paramArrayOfbyte[i] == 0) {
          j = i - paramInt;
          return getStringValueFromBytes(paramArrayOfbyte, paramInt, j);
        } 
      } else {
        return getStringValueFromBytes(paramArrayOfbyte, paramInt, j);
      } 
    } 
  }
  
  protected final String getStringValueFromBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    try {
      return new String(paramArrayOfbyte, paramInt1, paramInt2, "ISO-8859-1");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      Log.w(this.logTag, "UnsupportedEncodingException: " + unsupportedEncodingException.getMessage());
      return new String(paramArrayOfbyte, paramInt1, paramInt2);
    } 
  }
  
  protected final int getUBYTEValueFromBytes(byte[] paramArrayOfbyte, int paramInt) {
    return paramArrayOfbyte[paramInt] & 0xFF;
  }
  
  protected final long getULONGValueFromBytes(byte[] paramArrayOfbyte, int paramInt) {
    return paramArrayOfbyte[paramInt] & 0xFFL | (paramArrayOfbyte[paramInt + 1] & 0xFFL) << 8L | (paramArrayOfbyte[paramInt + 2] & 0xFFL) << 16L | (paramArrayOfbyte[paramInt + 3] & 0xFFL) << 24L;
  }
  
  protected final int getUWORDValueFromBytes(byte[] paramArrayOfbyte, int paramInt) {
    return paramArrayOfbyte[paramInt] & 0xFF | (paramArrayOfbyte[paramInt + 1] & 0xFF) << 8;
  }
  
  protected final int lsGetStatus(String paramString, int paramInt) {
    boolean bool = false;
    byte[] arrayOfByte1 = new byte[3];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 14;
    copyUBYTEValueToBytes(paramInt, arrayOfByte1, 2);
    byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage(paramString, arrayOfByte1);
    paramInt = bool;
    if (evaluateStatus(paramString, arrayOfByte2, arrayOfByte1[1])) {
      if (arrayOfByte2.length == 4)
        return getUBYTEValueFromBytes(arrayOfByte2, 3); 
    } else {
      return paramInt;
    } 
    Log.w(this.logTag, paramString + ": unexpected return package length " + arrayOfByte2.length + " (expected 4)");
    return 0;
  }
  
  protected final byte[] lsRead(String paramString, int paramInt) {
    byte[] arrayOfByte1 = new byte[3];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 16;
    copyUBYTEValueToBytes(paramInt, arrayOfByte1, 2);
    byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage(paramString, arrayOfByte1);
    if (evaluateStatus(paramString, arrayOfByte2, arrayOfByte1[1])) {
      if (arrayOfByte2.length == 20)
        return arrayOfByte2; 
      Log.w(this.logTag, paramString + ": unexpected return package length " + arrayOfByte2.length + " (expected 20)");
    } 
    return null;
  }
  
  protected final void lsWrite(String paramString, int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    if (paramArrayOfbyte.length > 16)
      throw new IllegalArgumentException("length must be <= 16"); 
    byte[] arrayOfByte = new byte[paramArrayOfbyte.length + 5];
    arrayOfByte[0] = 0;
    arrayOfByte[1] = 15;
    copyUBYTEValueToBytes(paramInt1, arrayOfByte, 2);
    copyUBYTEValueToBytes(paramArrayOfbyte.length, arrayOfByte, 3);
    copyUBYTEValueToBytes(paramInt2, arrayOfByte, 4);
    System.arraycopy(paramArrayOfbyte, 0, arrayOfByte, 5, paramArrayOfbyte.length);
    evaluateStatus(paramString, sendCommandAndReceiveReturnPackage(paramString, arrayOfByte), arrayOfByte[1]);
  }
  
  public void onDelete() {
    if (this.bluetooth != null) {
      this.bluetooth.removeBluetoothConnectionListener(this);
      this.bluetooth.detachComponent(this);
      this.bluetooth = null;
    } 
  }
  
  protected final void resetInputScaledValue(String paramString, int paramInt) {
    byte[] arrayOfByte = new byte[3];
    arrayOfByte[0] = Byte.MIN_VALUE;
    arrayOfByte[1] = 8;
    copyUBYTEValueToBytes(paramInt, arrayOfByte, 2);
    sendCommand(paramString, arrayOfByte);
  }
  
  protected final int sanitizePower(int paramInt) {
    int i = paramInt;
    if (paramInt < -100) {
      Log.w(this.logTag, "power " + paramInt + " is invalid, using -100.");
      i = -100;
    } 
    paramInt = i;
    if (i > 100) {
      Log.w(this.logTag, "power " + i + " is invalid, using 100.");
      paramInt = 100;
    } 
    return paramInt;
  }
  
  protected final int sanitizeTurnRatio(int paramInt) {
    int i = paramInt;
    if (paramInt < -100) {
      Log.w(this.logTag, "turnRatio " + paramInt + " is invalid, using -100.");
      i = -100;
    } 
    paramInt = i;
    if (i > 100) {
      Log.w(this.logTag, "turnRatio " + i + " is invalid, using 100.");
      paramInt = 100;
    } 
    return paramInt;
  }
  
  protected final void sendCommand(String paramString, byte[] paramArrayOfbyte) {
    byte[] arrayOfByte = new byte[2];
    copyUWORDValueToBytes(paramArrayOfbyte.length, arrayOfByte, 0);
    this.bluetooth.write(paramString, arrayOfByte);
    this.bluetooth.write(paramString, paramArrayOfbyte);
  }
  
  protected final byte[] sendCommandAndReceiveReturnPackage(String paramString, byte[] paramArrayOfbyte) {
    sendCommand(paramString, paramArrayOfbyte);
    return receiveReturnPackage(paramString);
  }
  
  protected final void setInputMode(String paramString, int paramInt1, int paramInt2, int paramInt3) {
    byte[] arrayOfByte = new byte[5];
    arrayOfByte[0] = Byte.MIN_VALUE;
    arrayOfByte[1] = 5;
    copyUBYTEValueToBytes(paramInt1, arrayOfByte, 2);
    copyUBYTEValueToBytes(paramInt2, arrayOfByte, 3);
    copyUBYTEValueToBytes(paramInt3, arrayOfByte, 4);
    sendCommand(paramString, arrayOfByte);
  }
  
  protected final void setOutputState(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong) {
    paramInt2 = sanitizePower(paramInt2);
    byte[] arrayOfByte = new byte[12];
    arrayOfByte[0] = Byte.MIN_VALUE;
    arrayOfByte[1] = 4;
    copyUBYTEValueToBytes(paramInt1, arrayOfByte, 2);
    copySBYTEValueToBytes(paramInt2, arrayOfByte, 3);
    copyUBYTEValueToBytes(paramInt3, arrayOfByte, 4);
    copyUBYTEValueToBytes(paramInt4, arrayOfByte, 5);
    copySBYTEValueToBytes(paramInt5, arrayOfByte, 6);
    copyUBYTEValueToBytes(paramInt6, arrayOfByte, 7);
    copyULONGValueToBytes(paramLong, arrayOfByte, 8);
    sendCommand(paramString, arrayOfByte);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/LegoMindstormsNxtBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
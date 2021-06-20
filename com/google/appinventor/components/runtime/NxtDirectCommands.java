package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a low-level interface to a LEGO MINDSTORMS NXT robot, with functions to send NXT Direct Commands.", iconName = "images/legoMindstormsNxt.png", nonVisible = true, version = 1)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public class NxtDirectCommands extends LegoMindstormsNxtBase {
  public NxtDirectCommands(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "NxtDirectCommands");
  }
  
  private void closeHandle(String paramString, int paramInt) {
    byte[] arrayOfByte = new byte[3];
    arrayOfByte[0] = 1;
    arrayOfByte[1] = -124;
    copyUBYTEValueToBytes(paramInt, arrayOfByte, 2);
    evaluateStatus(paramString, sendCommandAndReceiveReturnPackage(paramString, arrayOfByte), arrayOfByte[1]);
  }
  
  private byte[] getOutputState(String paramString, int paramInt) {
    byte[] arrayOfByte1 = new byte[3];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 6;
    copyUBYTEValueToBytes(paramInt, arrayOfByte1, 2);
    byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage(paramString, arrayOfByte1);
    if (evaluateStatus(paramString, arrayOfByte2, arrayOfByte1[1])) {
      if (arrayOfByte2.length == 25)
        return arrayOfByte2; 
      Log.w(this.logTag, paramString + ": unexpected return package length " + arrayOfByte2.length + " (expected 25)");
    } 
    return null;
  }
  
  private Integer openWrite(String paramString1, String paramString2, long paramLong) {
    byte[] arrayOfByte2 = new byte[26];
    arrayOfByte2[0] = 1;
    arrayOfByte2[1] = -127;
    copyStringValueToBytes(paramString2, arrayOfByte2, 2, 19);
    copyULONGValueToBytes(paramLong, arrayOfByte2, 22);
    byte[] arrayOfByte1 = sendCommandAndReceiveReturnPackage(paramString1, arrayOfByte2);
    if (evaluateStatus(paramString1, arrayOfByte1, arrayOfByte2[1])) {
      if (arrayOfByte1.length == 4)
        return Integer.valueOf(getUBYTEValueFromBytes(arrayOfByte1, 3)); 
      Log.w(this.logTag, paramString1 + ": unexpected return package length " + arrayOfByte1.length + " (expected 4)");
    } 
    return null;
  }
  
  private Integer openWriteLinear(String paramString1, String paramString2, long paramLong) {
    byte[] arrayOfByte2 = new byte[26];
    arrayOfByte2[0] = 1;
    arrayOfByte2[1] = -119;
    copyStringValueToBytes(paramString2, arrayOfByte2, 2, 19);
    copyULONGValueToBytes(paramLong, arrayOfByte2, 22);
    byte[] arrayOfByte1 = sendCommandAndReceiveReturnPackage(paramString1, arrayOfByte2);
    if (evaluateStatus(paramString1, arrayOfByte1, arrayOfByte2[1])) {
      if (arrayOfByte1.length == 4)
        return Integer.valueOf(getUBYTEValueFromBytes(arrayOfByte1, 3)); 
      Log.w(this.logTag, paramString1 + ": unexpected return package length " + arrayOfByte1.length + " (expected 4)");
    } 
    return null;
  }
  
  private int writeChunk(String paramString, int paramInt1, byte[] paramArrayOfbyte, int paramInt2) throws IOException {
    int i = 0;
    if (paramInt2 > 32)
      throw new IllegalArgumentException("length must be <= 32"); 
    byte[] arrayOfByte = new byte[paramInt2 + 3];
    arrayOfByte[0] = 1;
    arrayOfByte[1] = -125;
    copyUBYTEValueToBytes(paramInt1, arrayOfByte, 2);
    System.arraycopy(paramArrayOfbyte, 0, arrayOfByte, 3, paramInt2);
    paramArrayOfbyte = sendCommandAndReceiveReturnPackage(paramString, arrayOfByte);
    paramInt1 = i;
    if (evaluateStatus(paramString, paramArrayOfbyte, arrayOfByte[1]))
      if (paramArrayOfbyte.length == 6) {
        i = getUWORDValueFromBytes(paramArrayOfbyte, 4);
        paramInt1 = i;
        if (i != paramInt2) {
          Log.e(this.logTag, paramString + ": only " + i + " bytes were written (expected " + paramInt2 + ")");
          throw new IOException("Unable to write file on robot");
        } 
      } else {
        Log.w(this.logTag, paramString + ": unexpected return package length " + paramArrayOfbyte.length + " (expected 6)");
        paramInt1 = i;
      }  
    return paramInt1;
  }
  
  @SimpleFunction(description = "Delete a file on the robot.")
  public void DeleteFile(String paramString) {
    if (!checkBluetooth("DeleteFile"))
      return; 
    if (paramString.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "DeleteFile", 406, new Object[0]);
      return;
    } 
    byte[] arrayOfByte = new byte[22];
    arrayOfByte[0] = 1;
    arrayOfByte[1] = -123;
    copyStringValueToBytes(paramString, arrayOfByte, 2, 19);
    evaluateStatus("DeleteFile", sendCommandAndReceiveReturnPackage("DeleteFile", arrayOfByte), arrayOfByte[1]);
  }
  
  @SimpleFunction(description = "Download a file to the robot.")
  public void DownloadFile(String paramString1, String paramString2) {
    File file;
    if (!checkBluetooth("DownloadFile"))
      return; 
    if (paramString1.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "DownloadFile", 414, new Object[0]);
      return;
    } 
    if (paramString2.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "DownloadFile", 415, new Object[0]);
      return;
    } 
    try {
      file = MediaUtil.copyMediaToTempFile(this.form, paramString1);
      try {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(FileUtil.openFile(this.form, file), 1024);
        try {
          Integer integer;
          long l = file.length();
          if (paramString2.endsWith(".rxe") || paramString2.endsWith(".ric")) {
            integer = openWriteLinear("DownloadFile", paramString2, l);
          } else {
            integer = openWrite("DownloadFile", paramString2, l);
          } 
          if (integer == null) {
            bufferedInputStream.close();
            return;
          } 
        } finally {
          bufferedInputStream.close();
        } 
      } finally {}
    } catch (IOException iOException) {
      this.form.dispatchErrorOccurredEvent(this, "DownloadFile", 416, new Object[] { iOException.getMessage() });
      return;
    } 
    file.delete();
    throw iOException;
  }
  
  @SimpleFunction(description = "Get the battery level for the robot. Returns the voltage in millivolts.")
  public int GetBatteryLevel() {
    if (checkBluetooth("GetBatteryLevel")) {
      byte[] arrayOfByte1 = new byte[2];
      arrayOfByte1[0] = 0;
      arrayOfByte1[1] = 11;
      byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage("GetBatteryLevel", arrayOfByte1);
      if (evaluateStatus("GetBatteryLevel", arrayOfByte2, arrayOfByte1[1])) {
        if (arrayOfByte2.length == 5)
          return getUWORDValueFromBytes(arrayOfByte2, 3); 
        Log.w(this.logTag, "GetBatteryLevel: unexpected return package length " + arrayOfByte2.length + " (expected 5)");
        return 0;
      } 
    } 
    return 0;
  }
  
  @SimpleFunction(description = "Get the brick name of the robot.")
  public String GetBrickName() {
    if (!checkBluetooth("GetBrickName"))
      return ""; 
    byte[] arrayOfByte1 = new byte[2];
    arrayOfByte1[0] = 1;
    arrayOfByte1[1] = -101;
    byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage("GetBrickName", arrayOfByte1);
    return evaluateStatus("GetBrickName", arrayOfByte2, arrayOfByte1[1]) ? getStringValueFromBytes(arrayOfByte2, 3) : "";
  }
  
  @SimpleFunction(description = "Get the name of currently running program on the robot.")
  public String GetCurrentProgramName() {
    if (!checkBluetooth("GetCurrentProgramName"))
      return ""; 
    byte[] arrayOfByte1 = new byte[2];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 17;
    byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage("GetCurrentProgramName", arrayOfByte1);
    int i = getStatus("GetCurrentProgramName", arrayOfByte2, arrayOfByte1[1]);
    if (i == 0)
      return getStringValueFromBytes(arrayOfByte2, 3); 
    if (i == 236)
      return ""; 
    evaluateStatus("GetCurrentProgramName", arrayOfByte2, arrayOfByte1[1]);
    return "";
  }
  
  @SimpleFunction(description = "Get the firmware and protocol version numbers for the robot as a list where the first element is the firmware version number and the second element is the protocol version number.")
  public List<String> GetFirmwareVersion() {
    if (!checkBluetooth("GetFirmwareVersion"))
      return new ArrayList<String>(); 
    byte[] arrayOfByte2 = new byte[2];
    arrayOfByte2[0] = 1;
    arrayOfByte2[1] = -120;
    byte[] arrayOfByte1 = sendCommandAndReceiveReturnPackage("GetFirmwareVersion", arrayOfByte2);
    if (evaluateStatus("GetFirmwareVersion", arrayOfByte1, arrayOfByte2[1])) {
      ArrayList<String> arrayList = new ArrayList();
      arrayList.add(arrayOfByte1[6] + "." + arrayOfByte1[5]);
      arrayList.add(arrayOfByte1[4] + "." + arrayOfByte1[3]);
      return arrayList;
    } 
    return new ArrayList<String>();
  }
  
  @SimpleFunction(description = "Reads the values of an input sensor on the robot. Assumes sensor type has been configured via SetInputMode.")
  public List<Object> GetInputValues(String paramString) {
    byte[] arrayOfByte;
    if (!checkBluetooth("GetInputValues"))
      return new ArrayList(); 
    try {
      int i = convertSensorPortLetterToNumber(paramString);
      arrayOfByte = getInputValues("GetInputValues", i);
      if (arrayOfByte != null) {
        ArrayList<Boolean> arrayList = new ArrayList();
        arrayList.add(Boolean.valueOf(getBooleanValueFromBytes(arrayOfByte, 4)));
        arrayList.add(Boolean.valueOf(getBooleanValueFromBytes(arrayOfByte, 5)));
        arrayList.add(Integer.valueOf(getUBYTEValueFromBytes(arrayOfByte, 6)));
        arrayList.add(Integer.valueOf(getUBYTEValueFromBytes(arrayOfByte, 7)));
        arrayList.add(Integer.valueOf(getUWORDValueFromBytes(arrayOfByte, 8)));
        arrayList.add(Integer.valueOf(getUWORDValueFromBytes(arrayOfByte, 10)));
        arrayList.add(Integer.valueOf(getSWORDValueFromBytes(arrayOfByte, 12)));
        arrayList.add(Integer.valueOf(getSWORDValueFromBytes(arrayOfByte, 14)));
        return (List)arrayList;
      } 
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "GetInputValues", 408, new Object[] { arrayOfByte });
      return new ArrayList();
    } 
    return new ArrayList();
  }
  
  @SimpleFunction(description = "Reads the output state of a motor on the robot.")
  public List<Number> GetOutputState(String paramString) {
    byte[] arrayOfByte;
    if (!checkBluetooth("GetOutputState"))
      return new ArrayList<Number>(); 
    try {
      int i = convertMotorPortLetterToNumber(paramString);
      arrayOfByte = getOutputState("GetOutputState", i);
      if (arrayOfByte != null) {
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(getSBYTEValueFromBytes(arrayOfByte, 4)));
        arrayList.add(Integer.valueOf(getUBYTEValueFromBytes(arrayOfByte, 5)));
        arrayList.add(Integer.valueOf(getUBYTEValueFromBytes(arrayOfByte, 6)));
        arrayList.add(Integer.valueOf(getSBYTEValueFromBytes(arrayOfByte, 7)));
        arrayList.add(Integer.valueOf(getUBYTEValueFromBytes(arrayOfByte, 8)));
        arrayList.add(Long.valueOf(getULONGValueFromBytes(arrayOfByte, 9)));
        arrayList.add(Integer.valueOf(getSLONGValueFromBytes(arrayOfByte, 13)));
        arrayList.add(Integer.valueOf(getSLONGValueFromBytes(arrayOfByte, 17)));
        arrayList.add(Integer.valueOf(getSLONGValueFromBytes(arrayOfByte, 21)));
        return (List)arrayList;
      } 
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "GetOutputState", 407, new Object[] { arrayOfByte });
      return new ArrayList<Number>();
    } 
    return new ArrayList<Number>();
  }
  
  @SimpleFunction(description = "Keep Alive. Returns the current sleep time limit in milliseconds.")
  public long KeepAlive() {
    if (checkBluetooth("KeepAlive")) {
      byte[] arrayOfByte1 = new byte[2];
      arrayOfByte1[0] = 0;
      arrayOfByte1[1] = 13;
      byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage("KeepAlive", arrayOfByte1);
      if (evaluateStatus("KeepAlive", arrayOfByte2, arrayOfByte1[1])) {
        if (arrayOfByte2.length == 7)
          return getULONGValueFromBytes(arrayOfByte2, 3); 
        Log.w(this.logTag, "KeepAlive: unexpected return package length " + arrayOfByte2.length + " (expected 7)");
        return 0L;
      } 
    } 
    return 0L;
  }
  
  @SimpleFunction(description = "Returns a list containing the names of matching files found on the robot.")
  public List<String> ListFiles(String paramString) {
    if (!checkBluetooth("ListFiles"))
      return new ArrayList(); 
    ArrayList<String> arrayList = new ArrayList();
    String str = paramString;
    if (paramString.length() == 0)
      str = "*.*"; 
    byte[] arrayOfByte1 = new byte[22];
    arrayOfByte1[0] = 1;
    arrayOfByte1[1] = -122;
    copyStringValueToBytes(str, arrayOfByte1, 2, 19);
    byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage("ListFiles", arrayOfByte1);
    int i = getStatus("ListFiles", arrayOfByte2, arrayOfByte1[1]);
    while (true) {
      byte[] arrayOfByte;
      ArrayList<String> arrayList1 = arrayList;
      if (i == 0) {
        i = getUBYTEValueFromBytes(arrayOfByte2, 3);
        arrayList.add(getStringValueFromBytes(arrayOfByte2, 4));
        arrayOfByte = new byte[3];
        arrayOfByte[0] = 1;
        arrayOfByte[1] = -121;
        copyUBYTEValueToBytes(i, arrayOfByte, 2);
        arrayOfByte2 = sendCommandAndReceiveReturnPackage("ListFiles", arrayOfByte);
        i = getStatus("ListFiles", arrayOfByte2, arrayOfByte[1]);
        continue;
      } 
      return (List<String>)arrayOfByte;
    } 
  }
  
  @SimpleFunction(description = "Returns the count of available bytes to read.")
  public int LsGetStatus(String paramString) {
    if (!checkBluetooth("LsGetStatus"))
      return 0; 
    try {
      int i = convertSensorPortLetterToNumber(paramString);
      return lsGetStatus("LsGetStatus", i);
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "LsGetStatus", 408, new Object[] { paramString });
      return 0;
    } 
  }
  
  @SimpleFunction(description = "Reads unsigned low speed data from an input sensor on the robot. Assumes sensor type has been configured via SetInputMode.")
  public List<Integer> LsRead(String paramString) {
    ArrayList<Integer> arrayList;
    if (!checkBluetooth("LsRead"))
      return new ArrayList(); 
    try {
      int i = convertSensorPortLetterToNumber(paramString);
      byte[] arrayOfByte = lsRead("LsRead", i);
      if (arrayOfByte != null) {
        ArrayList<Integer> arrayList1 = new ArrayList();
        int j = getUBYTEValueFromBytes(arrayOfByte, 3);
        i = 0;
        while (true) {
          arrayList = arrayList1;
          if (i < j) {
            arrayList1.add(Integer.valueOf(arrayOfByte[i + 4] & 0xFF));
            i++;
            continue;
          } 
          return arrayList;
        } 
      } 
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "LsRead", 408, new Object[] { arrayList });
      return new ArrayList<Integer>();
    } 
    return new ArrayList<Integer>();
  }
  
  @SimpleFunction(description = "Writes low speed data to an input sensor on the robot. Assumes sensor type has been configured via SetInputMode.")
  public void LsWrite(String paramString, YailList paramYailList, int paramInt) {
    int j;
    if (!checkBluetooth("LsWrite"))
      return; 
    try {
      j = convertSensorPortLetterToNumber(paramString);
      if (paramYailList.size() > 16) {
        this.form.dispatchErrorOccurredEvent(this, "LsWrite", 411, new Object[0]);
        return;
      } 
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "LsWrite", 408, new Object[] { paramString });
      return;
    } 
    Object[] arrayOfObject = illegalArgumentException.toArray();
    byte[] arrayOfByte = new byte[arrayOfObject.length];
    int i;
    for (i = 0; i < arrayOfObject.length; i++) {
      String str = arrayOfObject[i].toString();
      try {
        int k = Integer.decode(str).intValue();
        arrayOfByte[i] = (byte)(k & 0xFF);
        k >>= 8;
        if (k != 0 && k != -1) {
          this.form.dispatchErrorOccurredEvent(this, "LsWrite", 413, new Object[] { Integer.valueOf(i + 1) });
          return;
        } 
      } catch (NumberFormatException numberFormatException) {
        this.form.dispatchErrorOccurredEvent(this, "LsWrite", 412, new Object[] { Integer.valueOf(i + 1) });
        return;
      } 
    } 
    lsWrite("LsWrite", j, arrayOfByte, paramInt);
  }
  
  @SimpleFunction(description = "Read a message from a mailbox (1-10) on the robot.")
  public String MessageRead(int paramInt) {
    if (!checkBluetooth("MessageRead"))
      return ""; 
    if (paramInt < 1 || paramInt > 10) {
      this.form.dispatchErrorOccurredEvent(this, "MessageRead", 409, new Object[] { Integer.valueOf(paramInt) });
      return "";
    } 
    paramInt--;
    byte[] arrayOfByte1 = new byte[5];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 19;
    copyUBYTEValueToBytes(0, arrayOfByte1, 2);
    copyUBYTEValueToBytes(paramInt, arrayOfByte1, 3);
    copyBooleanValueToBytes(true, arrayOfByte1, 4);
    byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage("MessageRead", arrayOfByte1);
    if (evaluateStatus("MessageRead", arrayOfByte2, arrayOfByte1[1])) {
      if (arrayOfByte2.length == 64) {
        int i = getUBYTEValueFromBytes(arrayOfByte2, 3);
        if (i != paramInt)
          Log.w(this.logTag, "MessageRead: unexpected return mailbox: " + i + " (expected " + paramInt + ")"); 
        return getStringValueFromBytes(arrayOfByte2, 5, getUBYTEValueFromBytes(arrayOfByte2, 4) - 1);
      } 
      Log.w(this.logTag, "MessageRead: unexpected return package length " + arrayOfByte2.length + " (expected 64)");
    } 
    return "";
  }
  
  @SimpleFunction(description = "Write a message to a mailbox (1-10) on the robot.")
  public void MessageWrite(int paramInt, String paramString) {
    if (!checkBluetooth("MessageWrite"))
      return; 
    if (paramInt < 1 || paramInt > 10) {
      this.form.dispatchErrorOccurredEvent(this, "MessageWrite", 409, new Object[] { Integer.valueOf(paramInt) });
      return;
    } 
    int i = paramString.length();
    if (i > 58) {
      this.form.dispatchErrorOccurredEvent(this, "MessageWrite", 410, new Object[0]);
      return;
    } 
    byte[] arrayOfByte = new byte[i + 4 + 1];
    arrayOfByte[0] = Byte.MIN_VALUE;
    arrayOfByte[1] = 9;
    copyUBYTEValueToBytes(paramInt - 1, arrayOfByte, 2);
    copyUBYTEValueToBytes(i + 1, arrayOfByte, 3);
    copyStringValueToBytes(paramString, arrayOfByte, 4, i);
    sendCommand("MessageWrite", arrayOfByte);
  }
  
  @SimpleFunction(description = "Play a sound file on the robot.")
  public void PlaySoundFile(String paramString) {
    if (!checkBluetooth("PlaySoundFile"))
      return; 
    if (paramString.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "PlaySoundFile", 406, new Object[0]);
      return;
    } 
    String str = paramString;
    if (paramString.indexOf(".") == -1)
      str = paramString + ".rso"; 
    byte[] arrayOfByte = new byte[23];
    arrayOfByte[0] = Byte.MIN_VALUE;
    arrayOfByte[1] = 2;
    copyBooleanValueToBytes(false, arrayOfByte, 2);
    copyStringValueToBytes(str, arrayOfByte, 3, 19);
    sendCommand("PlaySoundFile", arrayOfByte);
  }
  
  @SimpleFunction(description = "Make the robot play a tone.")
  public void PlayTone(int paramInt1, int paramInt2) {
    if (!checkBluetooth("PlayTone"))
      return; 
    int i = paramInt1;
    if (paramInt1 < 200) {
      Log.w(this.logTag, "frequencyHz " + paramInt1 + " is invalid, using 200.");
      i = 200;
    } 
    paramInt1 = i;
    if (i > 14000) {
      Log.w(this.logTag, "frequencyHz " + i + " is invalid, using 14000.");
      paramInt1 = 14000;
    } 
    byte[] arrayOfByte = new byte[6];
    arrayOfByte[0] = Byte.MIN_VALUE;
    arrayOfByte[1] = 3;
    copyUWORDValueToBytes(paramInt1, arrayOfByte, 2);
    copyUWORDValueToBytes(paramInt2, arrayOfByte, 4);
    sendCommand("PlayTone", arrayOfByte);
  }
  
  @SimpleFunction(description = "Reset the scaled value of an input sensor on the robot.")
  public void ResetInputScaledValue(String paramString) {
    byte[] arrayOfByte;
    if (!checkBluetooth("ResetInputScaledValue"))
      return; 
    try {
      int i = convertSensorPortLetterToNumber(paramString);
      resetInputScaledValue("ResetInputScaledValue", i);
      arrayOfByte = new byte[3];
      arrayOfByte[0] = Byte.MIN_VALUE;
      arrayOfByte[1] = 8;
      copyUBYTEValueToBytes(i, arrayOfByte, 2);
      sendCommand("ResetInputScaledValue", arrayOfByte);
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "ResetInputScaledValue", 408, new Object[] { arrayOfByte });
      return;
    } 
  }
  
  @SimpleFunction(description = "Reset motor position.")
  public void ResetMotorPosition(String paramString, boolean paramBoolean) {
    byte[] arrayOfByte;
    if (!checkBluetooth("ResetMotorPosition"))
      return; 
    try {
      int i = convertMotorPortLetterToNumber(paramString);
      arrayOfByte = new byte[4];
      arrayOfByte[0] = Byte.MIN_VALUE;
      arrayOfByte[1] = 10;
      copyUBYTEValueToBytes(i, arrayOfByte, 2);
      copyBooleanValueToBytes(paramBoolean, arrayOfByte, 3);
      sendCommand("ResetMotorPosition", arrayOfByte);
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "ResetMotorPosition", 407, new Object[] { arrayOfByte });
      return;
    } 
  }
  
  @SimpleFunction(description = "Set the brick name of the robot.")
  public void SetBrickName(String paramString) {
    if (!checkBluetooth("SetBrickName"))
      return; 
    byte[] arrayOfByte = new byte[18];
    arrayOfByte[0] = 1;
    arrayOfByte[1] = -104;
    copyStringValueToBytes(paramString, arrayOfByte, 2, 15);
    evaluateStatus("SetBrickName", sendCommandAndReceiveReturnPackage("SetBrickName", arrayOfByte), arrayOfByte[1]);
  }
  
  @SimpleFunction(description = "Configure an input sensor on the robot.")
  public void SetInputMode(String paramString, int paramInt1, int paramInt2) {
    if (!checkBluetooth("SetInputMode"))
      return; 
    try {
      int i = convertSensorPortLetterToNumber(paramString);
      setInputMode("SetInputMode", i, paramInt1, paramInt2);
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "SetInputMode", 408, new Object[] { paramString });
      return;
    } 
  }
  
  @SimpleFunction(description = "Sets the output state of a motor on the robot.")
  public void SetOutputState(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong) {
    if (!checkBluetooth("SetOutputState"))
      return; 
    try {
      int i = convertMotorPortLetterToNumber(paramString);
      setOutputState("SetOutputState", i, paramInt1, paramInt2, paramInt3, sanitizeTurnRatio(paramInt4), paramInt5, paramLong);
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "SetOutputState", 407, new Object[] { paramString });
      return;
    } 
  }
  
  @SimpleFunction(description = "Start execution of a previously downloaded program on the robot.")
  public void StartProgram(String paramString) {
    if (!checkBluetooth("StartProgram"))
      return; 
    if (paramString.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "StartProgram", 405, new Object[0]);
      return;
    } 
    String str = paramString;
    if (paramString.indexOf(".") == -1)
      str = paramString + ".rxe"; 
    byte[] arrayOfByte = new byte[22];
    arrayOfByte[0] = Byte.MIN_VALUE;
    arrayOfByte[1] = 0;
    copyStringValueToBytes(str, arrayOfByte, 2, 19);
    sendCommand("StartProgram", arrayOfByte);
  }
  
  @SimpleFunction(description = "Stop execution of the currently running program on the robot.")
  public void StopProgram() {
    if (!checkBluetooth("StopProgram"))
      return; 
    sendCommand("StopProgram", new byte[] { Byte.MIN_VALUE, 1 });
  }
  
  @SimpleFunction(description = "Stop sound playback.")
  public void StopSoundPlayback() {
    if (!checkBluetooth("StopSoundPlayback"))
      return; 
    sendCommand("StopSoundPlayback", new byte[] { Byte.MIN_VALUE, 12 });
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/NxtDirectCommands.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
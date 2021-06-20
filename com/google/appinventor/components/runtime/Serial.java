package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.physicaloid.lib.Physicaloid;
import java.io.UnsupportedEncodingException;

@DesignerComponent(androidMinSdk = 12, category = ComponentCategory.CONNECTIVITY, description = "Serial component which can be used to connect to devices like Arduino", iconName = "images/arduino.png", nonVisible = true, version = 1)
@SimpleObject
@UsesLibraries(libraries = "physicaloid.jar")
public class Serial extends AndroidNonvisibleComponent implements Component {
  private static final String LOG_TAG = "Serial Component";
  
  private int baudRate = 9600;
  
  private int bytes = 256;
  
  private Context context;
  
  private Physicaloid mPhysicaloid;
  
  public Serial(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.context = (Context)paramComponentContainer.$context();
    Log.d("Serial Component", "Created");
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the current baud rate")
  public int BaudRate() {
    return this.baudRate;
  }
  
  @DesignerProperty(defaultValue = "9600", editorType = "integer")
  @SimpleProperty
  public void BaudRate(int paramInt) {
    this.baudRate = paramInt;
    Log.d("Serial Component", "Baud Rate: " + paramInt);
    if (this.mPhysicaloid != null) {
      this.mPhysicaloid.setBaudrate(paramInt);
      return;
    } 
    Log.w("Serial Component", "Could not set Serial Baud Rate to " + paramInt + ". Just saved, not applied to serial! Maybe you forgot to initialize it?");
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the buffer size in bytes")
  public int BufferSize() {
    return this.bytes;
  }
  
  @DesignerProperty(defaultValue = "256", editorType = "integer")
  @SimpleProperty
  public void BufferSize(int paramInt) {
    this.bytes = paramInt;
    Log.d("Serial Component", "Buffer Size: " + paramInt);
  }
  
  @SimpleFunction(description = "Closes serial connection. Returns true when closed.")
  public boolean CloseSerial() {
    Log.d("Serial Component", "Closing connection");
    if (this.mPhysicaloid == null) {
      this.form.dispatchErrorOccurredEvent(this, "CloseSerial", 3901, new Object[0]);
      return false;
    } 
    return this.mPhysicaloid.close();
  }
  
  @SimpleFunction(description = "Initializes serial connection.")
  public void InitializeSerial() {
    this.mPhysicaloid = new Physicaloid(this.context);
    BaudRate(this.baudRate);
    Log.d("Serial Component", "Initialized");
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns true when the Serial has been initialized.")
  public boolean IsInitialized() {
    return (this.mPhysicaloid != null);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns true when the Serial connection is open.")
  public boolean IsOpen() {
    if (this.mPhysicaloid == null) {
      this.form.dispatchErrorOccurredEvent(this, "IsOpen", 3901, new Object[0]);
      return false;
    } 
    return this.mPhysicaloid.isOpened();
  }
  
  @SimpleFunction(description = "Opens serial connection. Returns true when opened.")
  public boolean OpenSerial() {
    Log.d("Serial Component", "Opening connection");
    if (this.mPhysicaloid == null) {
      this.form.dispatchErrorOccurredEvent(this, "OpenSerial", 3901, new Object[0]);
      return false;
    } 
    return this.mPhysicaloid.open();
  }
  
  @SimpleFunction(description = "Writes given data to serial, and appends a new line at the end.")
  public void PrintSerial(String paramString) {
    if (!paramString.isEmpty())
      WriteSerial(paramString + "\n"); 
  }
  
  @SimpleFunction(description = "Reads data from serial.")
  public String ReadSerial() {
    if (this.mPhysicaloid == null) {
      this.form.dispatchErrorOccurredEvent(this, "ReadSerial", 3901, new Object[0]);
      return "";
    } 
    byte[] arrayOfByte = new byte[this.bytes];
    if (this.mPhysicaloid.read(arrayOfByte) > 0)
      try {
        return new String(arrayOfByte, "UTF-8");
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        Log.e("Serial Component", unsupportedEncodingException.getMessage());
        return "";
      }  
    return "";
  }
  
  @SimpleFunction(description = "Writes given data to serial.")
  public void WriteSerial(String paramString) {
    if (!paramString.isEmpty() && this.mPhysicaloid != null) {
      byte[] arrayOfByte = paramString.getBytes();
      if (this.mPhysicaloid.write(arrayOfByte) == -1)
        this.form.dispatchErrorOccurredEvent(this, "WriteSerial", 3902, new Object[0]); 
      return;
    } 
    if (this.mPhysicaloid == null) {
      this.form.dispatchErrorOccurredEvent(this, "WriteSerial", 3901, new Object[0]);
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Serial.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
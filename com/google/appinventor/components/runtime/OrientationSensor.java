package com.google.appinventor.components.runtime;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.WindowManager;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.FroyoUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "<p>Non-visible component providing information about the device's physical orientation in three dimensions: <ul> <li> <strong>Roll</strong>: 0 degrees when the device is level, increases to      90 degrees as the device is tilted up on its left side, and      decreases to -90 degrees when the device is tilted up on its right side.      </li> <li> <strong>Pitch</strong>: 0 degrees when the device is level, up to      90 degrees as the device is tilted so its top is pointing down,      up to 180 degrees as it gets turned over.  Similarly, as the device      is tilted so its bottom points down, pitch decreases to -90      degrees, then further decreases to -180 degrees as it gets turned all the way      over.</li> <li> <strong>Azimuth</strong>: 0 degrees when the top of the device is      pointing north, 90 degrees when it is pointing east, 180 degrees      when it is pointing south, 270 degrees when it is pointing west,      etc.</li></ul>     These measurements assume that the device itself is not moving.</p>", iconName = "images/orientationsensor.png", nonVisible = true, version = 2)
@SimpleObject
public class OrientationSensor extends AndroidNonvisibleComponent implements SensorEventListener, Deleteable, OnPauseListener, OnResumeListener {
  private static final int AZIMUTH = 0;
  
  private static final int DIMENSIONS = 3;
  
  private static final String LOG_TAG = "OrientationSensor";
  
  private static final int PITCH = 1;
  
  private static final int ROLL = 2;
  
  private final Sensor accelerometerSensor;
  
  private final float[] accels = new float[3];
  
  private boolean accelsFilled;
  
  private int accuracy;
  
  private float azimuth;
  
  private boolean enabled;
  
  private final float[] inclinationMatrix = new float[9];
  
  private boolean listening;
  
  private final Sensor magneticFieldSensor;
  
  private final float[] mags = new float[3];
  
  private boolean magsFilled;
  
  private float pitch;
  
  private float roll;
  
  private final float[] rotationMatrix = new float[9];
  
  private final SensorManager sensorManager;
  
  private final float[] values = new float[3];
  
  public OrientationSensor(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.sensorManager = (SensorManager)paramComponentContainer.$context().getSystemService("sensor");
    this.accelerometerSensor = this.sensorManager.getDefaultSensor(1);
    this.magneticFieldSensor = this.sensorManager.getDefaultSensor(2);
    this.form.registerForOnResume(this);
    this.form.registerForOnPause(this);
    Enabled(true);
  }
  
  static float computeAngle(float paramFloat1, float paramFloat2) {
    return (float)Math.toDegrees(Math.atan2(Math.toRadians(paramFloat1), -Math.toRadians(paramFloat2)));
  }
  
  private int getScreenRotation() {
    Display display = ((WindowManager)this.form.getSystemService("window")).getDefaultDisplay();
    return (SdkLevel.getLevel() >= 8) ? FroyoUtil.getRotation(display) : display.getOrientation();
  }
  
  private void startListening() {
    if (!this.listening) {
      this.sensorManager.registerListener(this, this.accelerometerSensor, 3);
      this.sensorManager.registerListener(this, this.magneticFieldSensor, 3);
      this.listening = true;
    } 
  }
  
  private void stopListening() {
    if (this.listening) {
      this.sensorManager.unregisterListener(this);
      this.listening = false;
      this.accelsFilled = false;
      this.magsFilled = false;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public float Angle() {
    return computeAngle(this.pitch, this.roll);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public boolean Available() {
    return (this.sensorManager.getSensorList(1).size() > 0 && this.sensorManager.getSensorList(2).size() > 0);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public float Azimuth() {
    return this.azimuth;
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean) {
    if (this.enabled != paramBoolean) {
      this.enabled = paramBoolean;
      if (paramBoolean) {
        startListening();
        return;
      } 
    } else {
      return;
    } 
    stopListening();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public boolean Enabled() {
    return this.enabled;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public float Magnitude() {
    double d1 = Math.toRadians(Math.min(90.0F, Math.abs(this.pitch)));
    double d2 = Math.toRadians(Math.min(90.0F, Math.abs(this.roll)));
    return (float)(1.0D - Math.cos(d1) * Math.cos(d2));
  }
  
  @SimpleEvent(description = "Called when the orientation has changed.")
  public void OrientationChanged(float paramFloat1, float paramFloat2, float paramFloat3) {
    EventDispatcher.dispatchEvent(this, "OrientationChanged", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3) });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public float Pitch() {
    return this.pitch;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public float Roll() {
    return this.roll;
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onDelete() {
    stopListening();
  }
  
  public void onPause() {
    stopListening();
  }
  
  public void onResume() {
    if (this.enabled)
      startListening(); 
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield enabled : Z
    //   4: ifeq -> 66
    //   7: aload_1
    //   8: getfield sensor : Landroid/hardware/Sensor;
    //   11: invokevirtual getType : ()I
    //   14: istore_3
    //   15: iload_3
    //   16: tableswitch default -> 40, 1 -> 67, 2 -> 275
    //   40: ldc 'OrientationSensor'
    //   42: new java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial <init> : ()V
    //   49: ldc_w 'Unexpected sensor type: '
    //   52: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: iload_3
    //   56: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   59: invokevirtual toString : ()Ljava/lang/String;
    //   62: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   65: pop
    //   66: return
    //   67: aload_1
    //   68: getfield values : [F
    //   71: iconst_0
    //   72: aload_0
    //   73: getfield accels : [F
    //   76: iconst_0
    //   77: iconst_3
    //   78: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   81: aload_0
    //   82: iconst_1
    //   83: putfield accelsFilled : Z
    //   86: aload_0
    //   87: aload_1
    //   88: getfield accuracy : I
    //   91: putfield accuracy : I
    //   94: aload_0
    //   95: getfield accelsFilled : Z
    //   98: ifeq -> 66
    //   101: aload_0
    //   102: getfield magsFilled : Z
    //   105: ifeq -> 66
    //   108: aload_0
    //   109: getfield rotationMatrix : [F
    //   112: aload_0
    //   113: getfield inclinationMatrix : [F
    //   116: aload_0
    //   117: getfield accels : [F
    //   120: aload_0
    //   121: getfield mags : [F
    //   124: invokestatic getRotationMatrix : ([F[F[F[F)Z
    //   127: pop
    //   128: aload_0
    //   129: getfield rotationMatrix : [F
    //   132: aload_0
    //   133: getfield values : [F
    //   136: invokestatic getOrientation : ([F[F)[F
    //   139: pop
    //   140: aload_0
    //   141: aload_0
    //   142: getfield values : [F
    //   145: iconst_0
    //   146: faload
    //   147: f2d
    //   148: invokestatic toDegrees : (D)D
    //   151: d2f
    //   152: invokestatic normalizeAzimuth : (F)F
    //   155: putfield azimuth : F
    //   158: aload_0
    //   159: aload_0
    //   160: getfield values : [F
    //   163: iconst_1
    //   164: faload
    //   165: f2d
    //   166: invokestatic toDegrees : (D)D
    //   169: d2f
    //   170: invokestatic normalizePitch : (F)F
    //   173: putfield pitch : F
    //   176: aload_0
    //   177: aload_0
    //   178: getfield values : [F
    //   181: iconst_2
    //   182: faload
    //   183: f2d
    //   184: invokestatic toDegrees : (D)D
    //   187: dneg
    //   188: d2f
    //   189: invokestatic normalizeRoll : (F)F
    //   192: putfield roll : F
    //   195: aload_0
    //   196: invokespecial getScreenRotation : ()I
    //   199: istore_3
    //   200: iload_3
    //   201: tableswitch default -> 232, 0 -> 258, 1 -> 297, 2 -> 320, 3 -> 332
    //   232: ldc 'OrientationSensor'
    //   234: new java/lang/StringBuilder
    //   237: dup
    //   238: invokespecial <init> : ()V
    //   241: ldc_w 'Illegal value for getScreenRotation(): '
    //   244: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: iload_3
    //   248: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   251: invokevirtual toString : ()Ljava/lang/String;
    //   254: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   257: pop
    //   258: aload_0
    //   259: aload_0
    //   260: getfield azimuth : F
    //   263: aload_0
    //   264: getfield pitch : F
    //   267: aload_0
    //   268: getfield roll : F
    //   271: invokevirtual OrientationChanged : (FFF)V
    //   274: return
    //   275: aload_1
    //   276: getfield values : [F
    //   279: iconst_0
    //   280: aload_0
    //   281: getfield mags : [F
    //   284: iconst_0
    //   285: iconst_3
    //   286: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   289: aload_0
    //   290: iconst_1
    //   291: putfield magsFilled : Z
    //   294: goto -> 94
    //   297: aload_0
    //   298: getfield pitch : F
    //   301: fneg
    //   302: fstore_2
    //   303: aload_0
    //   304: aload_0
    //   305: getfield roll : F
    //   308: fneg
    //   309: putfield pitch : F
    //   312: aload_0
    //   313: fload_2
    //   314: putfield roll : F
    //   317: goto -> 258
    //   320: aload_0
    //   321: aload_0
    //   322: getfield roll : F
    //   325: fneg
    //   326: putfield roll : F
    //   329: goto -> 258
    //   332: aload_0
    //   333: getfield pitch : F
    //   336: fstore_2
    //   337: aload_0
    //   338: aload_0
    //   339: getfield roll : F
    //   342: putfield pitch : F
    //   345: aload_0
    //   346: fload_2
    //   347: putfield roll : F
    //   350: goto -> 258
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/OrientationSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
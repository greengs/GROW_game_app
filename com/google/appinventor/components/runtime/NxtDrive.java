package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a high-level interface to a LEGO MINDSTORMS NXT robot, with functions that can move and turn the robot.", iconName = "images/legoMindstormsNxt.png", nonVisible = true, version = 1)
@SimpleObject
public class NxtDrive extends LegoMindstormsNxtBase {
  private static final int MODE_BRAKE = 2;
  
  private static final int MODE_MOTORON = 1;
  
  private static final int MODE_REGULATED = 4;
  
  private static final int MOTOR_RUN_STATE_IDLE = 0;
  
  private static final int MOTOR_RUN_STATE_RAMPDOWN = 64;
  
  private static final int MOTOR_RUN_STATE_RAMPUP = 16;
  
  private static final int MOTOR_RUN_STATE_RUNNING = 32;
  
  private static final int REGULATION_MODE_IDLE = 0;
  
  private static final int REGULATION_MODE_MOTOR_SPEED = 1;
  
  private static final int REGULATION_MODE_MOTOR_SYNC = 2;
  
  private List<Integer> driveMotorPorts;
  
  private String driveMotors;
  
  private boolean stopBeforeDisconnect;
  
  private double wheelDiameter;
  
  public NxtDrive(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "NxtDrive");
    DriveMotors("CB");
    WheelDiameter(4.32F);
    StopBeforeDisconnect(true);
  }
  
  private void move(String paramString, int paramInt, long paramLong) {
    if (checkBluetooth(paramString)) {
      Iterator<Integer> iterator = this.driveMotorPorts.iterator();
      while (true) {
        if (iterator.hasNext()) {
          setOutputState(paramString, ((Integer)iterator.next()).intValue(), paramInt, 1, 1, 0, 32, paramLong);
          continue;
        } 
        return;
      } 
    } 
  }
  
  private void turnIndefinitely(String paramString, int paramInt1, int paramInt2, int paramInt3) {
    if (!checkBluetooth(paramString))
      return; 
    setOutputState(paramString, ((Integer)this.driveMotorPorts.get(paramInt2)).intValue(), paramInt1, 1, 1, 0, 32, 0L);
    setOutputState(paramString, ((Integer)this.driveMotorPorts.get(paramInt3)).intValue(), -paramInt1, 1, 1, 0, 32, 0L);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The motor ports that are used for driving: the left wheel's motor port followed by the right wheel's motor port.", userVisible = false)
  public String DriveMotors() {
    return this.driveMotors;
  }
  
  @DesignerProperty(defaultValue = "CB", editorType = "string")
  @SimpleProperty
  public void DriveMotors(String paramString) {
    this.driveMotors = paramString;
    this.driveMotorPorts = new ArrayList<Integer>();
    for (int i = 0; i < paramString.length(); i++) {
      char c = paramString.charAt(i);
      try {
        this.driveMotorPorts.add(Integer.valueOf(convertMotorPortLetterToNumber(c)));
      } catch (IllegalArgumentException illegalArgumentException) {
        this.form.dispatchErrorOccurredEvent(this, "DriveMotors", 407, new Object[] { Character.valueOf(c) });
      } 
    } 
  }
  
  @SimpleFunction(description = "Move the robot backward the given distance, with the specified percentage of maximum power, by powering both drive motors backward.")
  public void MoveBackward(int paramInt, double paramDouble) {
    long l = (long)(360.0D * paramDouble / this.wheelDiameter * Math.PI);
    move("MoveBackward", -paramInt, l);
  }
  
  @SimpleFunction(description = "Move the robot backward indefinitely, with the specified percentage of maximum power, by powering both drive motors backward.")
  public void MoveBackwardIndefinitely(int paramInt) {
    move("MoveBackwardIndefinitely", -paramInt, 0L);
  }
  
  @SimpleFunction(description = "Move the robot forward the given distance, with the specified percentage of maximum power, by powering both drive motors forward.")
  public void MoveForward(int paramInt, double paramDouble) {
    move("MoveForward", paramInt, (long)(360.0D * paramDouble / this.wheelDiameter * Math.PI));
  }
  
  @SimpleFunction(description = "Move the robot forward indefinitely, with the specified percentage of maximum power, by powering both drive motors forward.")
  public void MoveForwardIndefinitely(int paramInt) {
    move("MoveForwardIndefinitely", paramInt, 0L);
  }
  
  @SimpleFunction(description = "Stop the drive motors of the robot.")
  public void Stop() {
    if (checkBluetooth("Stop")) {
      Iterator<Integer> iterator = this.driveMotorPorts.iterator();
      while (true) {
        if (iterator.hasNext()) {
          setOutputState("Stop", ((Integer)iterator.next()).intValue(), 0, 2, 0, 0, 0, 0L);
          continue;
        } 
        return;
      } 
    } 
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void StopBeforeDisconnect(boolean paramBoolean) {
    this.stopBeforeDisconnect = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether to stop the drive motors before disconnecting.")
  public boolean StopBeforeDisconnect() {
    return this.stopBeforeDisconnect;
  }
  
  @SimpleFunction(description = "Turn the robot clockwise indefinitely, with the specified percentage of maximum power, by powering the left drive motor forward and the right drive motor backward.")
  public void TurnClockwiseIndefinitely(int paramInt) {
    int i = this.driveMotorPorts.size();
    if (i >= 2)
      turnIndefinitely("TurnClockwiseIndefinitely", paramInt, 0, i - 1); 
  }
  
  @SimpleFunction(description = "Turn the robot counterclockwise indefinitely, with the specified percentage of maximum power, by powering the right drive motor forward and the left drive motor backward.")
  public void TurnCounterClockwiseIndefinitely(int paramInt) {
    int i = this.driveMotorPorts.size();
    if (i >= 2)
      turnIndefinitely("TurnCounterClockwiseIndefinitely", paramInt, i - 1, 0); 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The diameter of the wheels used for driving.", userVisible = false)
  public float WheelDiameter() {
    return (float)this.wheelDiameter;
  }
  
  @DesignerProperty(defaultValue = "4.32", editorType = "float")
  @SimpleProperty
  public void WheelDiameter(float paramFloat) {
    this.wheelDiameter = paramFloat;
  }
  
  public void beforeDisconnect(BluetoothConnectionBase paramBluetoothConnectionBase) {
    if (this.stopBeforeDisconnect) {
      Iterator<Integer> iterator = this.driveMotorPorts.iterator();
      while (iterator.hasNext())
        setOutputState("Disconnect", ((Integer)iterator.next()).intValue(), 0, 2, 0, 0, 0, 0L); 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/NxtDrive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
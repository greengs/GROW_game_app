package com.google.appinventor.components.runtime;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.PaintUtil;

@DesignerComponent(category = ComponentCategory.ANIMATION, description = "<p>A round 'sprite' that can be placed on a <code>Canvas</code>, where it can react to touches and drags, interact with other sprites (<code>ImageSprite</code>s and other <code>Ball</code>s) and the edge of the Canvas, and move according to its property values.</p><p>For example, to have a <code>Ball</code> move 4 pixels toward the top of a <code>Canvas</code> every 500 milliseconds (half second), you would set the <code>Speed</code> property to 4 [pixels], the <code>Interval</code> property to 500 [milliseconds], the <code>Heading</code> property to 90 [degrees], and the <code>Enabled</code> property to <code>True</code>.</p><p>The difference between a <code>Ball</code> and an <code>ImageSprite</code> is that the latter can get its appearance from an image file, while a <code>Ball</code>'s appearance can be changed only by varying its <code>PaintColor</code> and <code>Radius</code> properties.</p>", version = 6)
@SimpleObject
public final class Ball extends Sprite {
  static final int DEFAULT_RADIUS = 5;
  
  private Paint paint = new Paint();
  
  private int paintColor;
  
  private int radius;
  
  public Ball(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    PaintColor(-16777216);
    Radius(5);
  }
  
  public int Height() {
    return this.radius * 2;
  }
  
  public void Height(int paramInt) {}
  
  public void HeightPercent(int paramInt) {}
  
  @SimpleFunction(description = "Sets the x and y coordinates of the Ball. If CenterAtOrigin is true, the center of the Ball will be placed here. Otherwise, the top left edge of the Ball will be placed at the specified coordinates.")
  public void MoveTo(double paramDouble1, double paramDouble2) {
    super.MoveTo(paramDouble1, paramDouble2);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(description = "Whether the x- and y-coordinates should represent the center of the Ball (true) or its left and top edges (false).", userVisible = false)
  public void OriginAtCenter(boolean paramBoolean) {
    super.OriginAtCenter(paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of the Ball.")
  public int PaintColor() {
    return this.paintColor;
  }
  
  @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
  @SimpleProperty
  public void PaintColor(int paramInt) {
    this.paintColor = paramInt;
    if (paramInt != 0) {
      PaintUtil.changePaint(this.paint, paramInt);
    } else {
      PaintUtil.changePaint(this.paint, -16777216);
    } 
    registerChange();
  }
  
  @SimpleProperty
  public int Radius() {
    return this.radius;
  }
  
  @DesignerProperty(defaultValue = "5", editorType = "non_negative_integer")
  @SimpleProperty(description = "The distance from the edge of the Ball to its center.")
  public void Radius(int paramInt) {
    int i = paramInt - this.radius;
    if (this.originAtCenter) {
      this.xLeft -= i;
      this.yTop -= i;
    } 
    this.radius = paramInt;
    registerChange();
  }
  
  public int Width() {
    return this.radius * 2;
  }
  
  public void Width(int paramInt) {}
  
  public void WidthPercent(int paramInt) {}
  
  @SimpleProperty(description = "The horizontal coordinate of the Ball, increasing as the Ball moves right. If the property OriginAtCenter is true, the coordinate is for the center of the Ball; otherwise, it is for the leftmost point of the Ball.")
  public double X() {
    return super.X();
  }
  
  @SimpleProperty(description = "The vertical coordinate of the Ball, increasing as the Ball moves down. If the property OriginAtCenter is true, the coordinate is for the center of the Ball; otherwise, it is for the uppermost point of the Ball.")
  public double Y() {
    return super.Y();
  }
  
  public boolean containsPoint(double paramDouble1, double paramDouble2) {
    return ((paramDouble1 - this.xCenter) * (paramDouble1 - this.xCenter) + (paramDouble2 - this.yCenter) * (paramDouble2 - this.yCenter) <= (this.radius * this.radius));
  }
  
  protected void onDraw(Canvas paramCanvas) {
    if (this.visible) {
      float f1 = (float)(this.xLeft * this.form.deviceDensity());
      float f2 = (float)(this.yTop * this.form.deviceDensity());
      float f3 = this.radius * this.form.deviceDensity();
      paramCanvas.drawCircle(f1 + f3, f2 + f3, f3, this.paint);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Ball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
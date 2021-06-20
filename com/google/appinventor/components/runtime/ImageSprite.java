package com.google.appinventor.components.runtime;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;
import java.io.IOException;

@DesignerComponent(category = ComponentCategory.ANIMATION, description = "<p>A 'sprite' that can be placed on a <code>Canvas</code>, where it can react to touches and drags, interact with other sprites (<code>Ball</code>s and other <code>ImageSprite</code>s) and the edge of the Canvas, and move according to its property values.  Its appearance is that of the image specified in its <code>Picture</code> property (unless its <code>Visible</code> property is <code>False</code>).</p> <p>To have an <code>ImageSprite</code> move 10 pixels to the left every 1000 milliseconds (one second), for example, you would set the <code>Speed</code> property to 10 [pixels], the <code>Interval</code> property to 1000 [milliseconds], the <code>Heading</code> property to 180 [degrees], and the <code>Enabled</code> property to <code>True</code>.  A sprite whose <code>Rotates</code> property is <code>True</code> will rotate its image as the sprite's <code>Heading</code> changes.  Checking for collisions with a rotated sprite currently checks the sprite's unrotated position so that collision checking will be inaccurate for tall narrow or short wide sprites that are rotated.  Any of the sprite properties can be changed at any time under program control.</p> ", version = 6)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class ImageSprite extends Sprite {
  private BitmapDrawable drawable;
  
  private final Form form;
  
  private int heightHint = -1;
  
  private String picturePath = "";
  
  private boolean rotates;
  
  private int widthHint = -1;
  
  public ImageSprite(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    this.form = paramComponentContainer.$form();
    this.rotates = true;
  }
  
  @SimpleProperty(description = "The height of the ImageSprite in pixels.")
  public int Height() {
    return (this.heightHint == -1 || this.heightHint == -2 || this.heightHint <= -1000) ? ((this.drawable == null) ? 0 : (int)(this.drawable.getBitmap().getHeight() / this.form.deviceDensity())) : this.heightHint;
  }
  
  @SimpleProperty
  public void Height(int paramInt) {
    this.heightHint = paramInt;
    registerChange();
  }
  
  public void HeightPercent(int paramInt) {}
  
  @SimpleFunction(description = "Moves the ImageSprite so that its left top corner is at the specified x and y coordinates.")
  public void MoveTo(double paramDouble1, double paramDouble2) {
    super.MoveTo(paramDouble1, paramDouble2);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The picture that determines the ImageSprite's appearance.")
  public String Picture() {
    return this.picturePath;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "asset")
  @SimpleProperty
  public void Picture(String paramString) {
    String str = paramString;
    if (paramString == null)
      str = ""; 
    this.picturePath = str;
    try {
      this.drawable = MediaUtil.getBitmapDrawable(this.form, this.picturePath);
    } catch (IOException iOException) {
      Log.e("ImageSprite", "Unable to load " + this.picturePath);
      this.drawable = null;
    } 
    registerChange();
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void Rotates(boolean paramBoolean) {
    this.rotates = paramBoolean;
    registerChange();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the image should rotate to match the ImageSprite's heading. The sprite rotates around its centerpoint.")
  public boolean Rotates() {
    return this.rotates;
  }
  
  @SimpleProperty(description = "The width of the ImageSprite in pixels.")
  public int Width() {
    return (this.widthHint == -1 || this.widthHint == -2 || this.widthHint <= -1000) ? ((this.drawable == null) ? 0 : (int)(this.drawable.getBitmap().getWidth() / this.form.deviceDensity())) : this.widthHint;
  }
  
  @SimpleProperty
  public void Width(int paramInt) {
    this.widthHint = paramInt;
    registerChange();
  }
  
  public void WidthPercent(int paramInt) {}
  
  @SimpleProperty(description = "The horizontal coordinate of the left edge of the ImageSprite, increasing as the ImageSprite moves right.")
  public double X() {
    return super.X();
  }
  
  @SimpleProperty(description = "The vertical coordinate of the top edge of the ImageSprite, increasing as the ImageSprite moves down.")
  public double Y() {
    return super.Y();
  }
  
  public void onDraw(Canvas paramCanvas) {
    int i;
    int j;
    int k;
    int m;
    if (this.drawable != null && this.visible) {
      i = (int)((float)Math.round(this.xLeft) * this.form.deviceDensity());
      j = (int)((float)Math.round(this.yTop) * this.form.deviceDensity());
      k = (int)(Width() * this.form.deviceDensity());
      m = (int)(Height() * this.form.deviceDensity());
      this.drawable.setBounds(i, j, i + k, j + m);
      if (!this.rotates) {
        this.drawable.draw(paramCanvas);
        return;
      } 
    } else {
      return;
    } 
    paramCanvas.save();
    paramCanvas.rotate((float)-Heading(), (k / 2 + i), (m / 2 + j));
    this.drawable.draw(paramCanvas);
    paramCanvas.restore();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/ImageSprite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
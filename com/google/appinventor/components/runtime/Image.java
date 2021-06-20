package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.HoneycombUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.IOException;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Component for displaying images.  The picture to display, and other aspects of the Image's appearance, can be specified in the Designer or in the Blocks Editor.", version = 4)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.READ_EXTERNAL_STORAGE")
public final class Image extends AndroidViewComponent {
  private boolean clickable = false;
  
  private String picturePath = "";
  
  private double rotationAngle = 0.0D;
  
  private int scalingMode = 0;
  
  private final ImageView view;
  
  public Image(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    this.view = new ImageView((Context)paramComponentContainer.$context()) {
        public boolean verifyDrawable(Drawable param1Drawable) {
          super.verifyDrawable(param1Drawable);
          return true;
        }
      };
    this.view.setFocusable(true);
    paramComponentContainer.$add(this);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is a limited form of animation that can attach a small number of motion types to images.  The allowable motions are ScrollRightSlow, ScrollRight, ScrollRightFast, ScrollLeftSlow, ScrollLeft, ScrollLeftFast, and Stop")
  public void Animation(String paramString) {
    AnimationUtil.ApplyAnimation((View)this.view, paramString);
  }
  
  @SimpleEvent(description = "An event that occurs when an image is clicked.")
  public void Click() {
    EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(description = "Specifies whether the image should be clickable or not.")
  public void Clickable(boolean paramBoolean) {
    this.clickable = paramBoolean;
    this.view.setClickable(this.clickable);
    if (this.clickable) {
      this.view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
              Image.this.Click();
            }
          });
      return;
    } 
    this.view.setOnClickListener(null);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies whether the image should be clickable or not.")
  public boolean Clickable() {
    return this.clickable;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public String Picture() {
    return this.picturePath;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "asset")
  @SimpleProperty
  public void Picture(final String path) {
    if (MediaUtil.isExternalFile((Context)this.container.$context(), path) && this.container.$form().isDeniedPermission("android.permission.READ_EXTERNAL_STORAGE")) {
      this.container.$form().askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public void HandlePermissionResponse(String param1String, boolean param1Boolean) {
              if (param1Boolean) {
                Image.this.Picture(path);
                return;
              } 
              Image.this.container.$form().dispatchPermissionDeniedEvent(Image.this, "Picture", param1String);
            }
          });
      return;
    } 
    String str = path;
    if (path == null)
      str = ""; 
    this.picturePath = str;
    try {
      BitmapDrawable bitmapDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), this.picturePath);
    } catch (IOException iOException) {
      Log.e("Image", "Unable to load " + this.picturePath);
      iOException = null;
    } 
    ViewUtil.setImage(this.view, (Drawable)iOException);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The angle at which the image picture appears rotated. This rotation does not appear on the designer screen, only on the device.")
  public double RotationAngle() {
    return this.rotationAngle;
  }
  
  @DesignerProperty(defaultValue = "0.0", editorType = "float")
  @SimpleProperty
  public void RotationAngle(double paramDouble) {
    if (this.rotationAngle == paramDouble)
      return; 
    if (SdkLevel.getLevel() < 11) {
      this.container.$form().dispatchErrorOccurredEvent(this, "RotationAngle", 3001, new Object[0]);
      return;
    } 
    HoneycombUtil.viewSetRotate((View)this.view, paramDouble);
    this.rotationAngle = paramDouble;
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(description = "Specifies whether the image should be resized to match the size of the ImageView.")
  public void ScalePictureToFit(boolean paramBoolean) {
    if (paramBoolean) {
      this.view.setScaleType(ImageView.ScaleType.FIT_XY);
      return;
    } 
    this.view.setScaleType(ImageView.ScaleType.FIT_CENTER);
  }
  
  @SimpleProperty
  public int Scaling() {
    return this.scalingMode;
  }
  
  @SimpleProperty(description = "This property determines how the picture scales according to the Height or Width of the Image. Scale proportionally (0) preserves the picture aspect ratio. Scale to fit (1) matches the Image area, even if the aspect ratio changes.")
  @Deprecated
  public void Scaling(int paramInt) {
    switch (paramInt) {
      default:
        throw new IllegalArgumentError("Illegal scaling mode: " + paramInt);
      case 0:
        this.view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.scalingMode = paramInt;
        return;
      case 1:
        break;
    } 
    this.view.setScaleType(ImageView.ScaleType.FIT_XY);
    this.scalingMode = paramInt;
  }
  
  public View getView() {
    return (View)this.view;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Image.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
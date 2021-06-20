package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.IOException;

@SimpleObject
public class HVArrangement extends AndroidViewComponent implements Component, ComponentContainer {
  private static final String LOG_TAG = "HVArrangement";
  
  private AlignmentUtil alignmentSetter;
  
  private final Handler androidUIHandler = new Handler();
  
  private int backgroundColor;
  
  private Drawable backgroundImageDrawable;
  
  private final Activity context;
  
  private Drawable defaultButtonDrawable;
  
  private ViewGroup frameContainer;
  
  private int horizontalAlignment;
  
  private String imagePath = "";
  
  private final int orientation;
  
  private boolean scrollable = false;
  
  private int verticalAlignment;
  
  private final LinearLayout viewLayout;
  
  public HVArrangement(ComponentContainer paramComponentContainer, int paramInt, boolean paramBoolean) {
    super(paramComponentContainer);
    this.context = paramComponentContainer.$context();
    this.orientation = paramInt;
    this.scrollable = paramBoolean;
    this.viewLayout = new LinearLayout((Context)this.context, paramInt, Integer.valueOf(100), Integer.valueOf(100));
    this.viewLayout.setBaselineAligned(false);
    this.alignmentSetter = new AlignmentUtil(this.viewLayout);
    this.horizontalAlignment = 1;
    this.verticalAlignment = 1;
    this.alignmentSetter.setHorizontalAlignment(this.horizontalAlignment);
    this.alignmentSetter.setVerticalAlignment(this.verticalAlignment);
    if (paramBoolean) {
      switch (paramInt) {
        default:
          this.frameContainer.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
          this.frameContainer.addView((View)this.viewLayout.getLayoutManager(), new ViewGroup.LayoutParams(-1, -1));
          this.defaultButtonDrawable = getView().getBackground();
          paramComponentContainer.$add(this);
          BackgroundColor(0);
          return;
        case 1:
          Log.d("HVArrangement", "Setting up frameContainer = ScrollView()");
          this.frameContainer = (ViewGroup)new ScrollView((Context)this.context);
        case 0:
          break;
      } 
      Log.d("HVArrangement", "Setting up frameContainer = HorizontalScrollView()");
      this.frameContainer = (ViewGroup)new HorizontalScrollView((Context)this.context);
    } 
    Log.d("HVArrangement", "Setting up frameContainer = FrameLayout()");
    this.frameContainer = (ViewGroup)new FrameLayout((Context)this.context);
  }
  
  private void updateAppearance() {
    if (this.backgroundImageDrawable == null) {
      if (this.backgroundColor == 0) {
        ViewUtil.setBackgroundDrawable((View)this.viewLayout.getLayoutManager(), this.defaultButtonDrawable);
        return;
      } 
      ViewUtil.setBackgroundDrawable((View)this.viewLayout.getLayoutManager(), null);
      this.viewLayout.getLayoutManager().setBackgroundColor(this.backgroundColor);
      return;
    } 
    ViewUtil.setBackgroundImage((View)this.viewLayout.getLayoutManager(), this.backgroundImageDrawable);
  }
  
  public void $add(AndroidViewComponent paramAndroidViewComponent) {
    this.viewLayout.add(paramAndroidViewComponent);
  }
  
  public Activity $context() {
    return this.context;
  }
  
  public Form $form() {
    return this.container.$form();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the %type% are aligned  horizontally. The choices are: 1 = left aligned, 2 = right aligned,  3 = horizontally centered.  Alignment has no effect if the arrangement's width is automatic.")
  public int AlignHorizontal() {
    return this.horizontalAlignment;
  }
  
  @DesignerProperty(defaultValue = "1", editorType = "horizontal_alignment")
  @SimpleProperty
  public void AlignHorizontal(int paramInt) {
    try {
      this.alignmentSetter.setHorizontalAlignment(paramInt);
      this.horizontalAlignment = paramInt;
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.container.$form().dispatchErrorOccurredEvent(this, "HorizontalAlignment", 1401, new Object[] { Integer.valueOf(paramInt) });
      return;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how the contents of the %type% are aligned  vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom.  Alignment has no effect if the arrangement's height is automatic.")
  public int AlignVertical() {
    return this.verticalAlignment;
  }
  
  @DesignerProperty(defaultValue = "1", editorType = "vertical_alignment")
  @SimpleProperty
  public void AlignVertical(int paramInt) {
    try {
      this.alignmentSetter.setVerticalAlignment(paramInt);
      this.verticalAlignment = paramInt;
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.container.$form().dispatchErrorOccurredEvent(this, "VerticalAlignment", 1402, new Object[] { Integer.valueOf(paramInt) });
      return;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the background color of the %type%")
  public int BackgroundColor() {
    return this.backgroundColor;
  }
  
  @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
  @SimpleProperty(description = "Specifies the background color of the %type%. The background color will not be visible if an Image is being displayed.")
  public void BackgroundColor(int paramInt) {
    this.backgroundColor = paramInt;
    updateAppearance();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public String Image() {
    return this.imagePath;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "asset")
  @SimpleProperty(description = "Specifies the path of the background image for the %type%.  If there is both an Image and a BackgroundColor, only the Image will be visible.")
  public void Image(String paramString) {
    if (paramString.equals(this.imagePath) && this.backgroundImageDrawable != null)
      return; 
    String str = paramString;
    if (paramString == null)
      str = ""; 
    this.imagePath = str;
    this.backgroundImageDrawable = null;
    if (this.imagePath.length() > 0)
      try {
        this.backgroundImageDrawable = (Drawable)MediaUtil.getBitmapDrawable(this.container.$form(), this.imagePath);
      } catch (IOException iOException) {} 
    updateAppearance();
  }
  
  public View getView() {
    return (View)this.frameContainer;
  }
  
  public void setChildHeight(final AndroidViewComponent component, final int fHeight) {
    int j = this.container.$form().Height();
    if (j == 0)
      this.androidUIHandler.postDelayed(new Runnable() {
            public void run() {
              Log.d("HVArrangement", "(HVArrangement)Height not stable yet... trying again");
              HVArrangement.this.setChildHeight(component, fHeight);
            }
          }100L); 
    int i = fHeight;
    if (fHeight <= -1000)
      i = -(fHeight + 1000) * j / 100; 
    component.setLastHeight(i);
    if (this.orientation == 0) {
      ViewUtil.setChildHeightForHorizontalLayout(component.getView(), i);
      return;
    } 
    ViewUtil.setChildHeightForVerticalLayout(component.getView(), i);
  }
  
  public void setChildWidth(AndroidViewComponent paramAndroidViewComponent, int paramInt) {
    setChildWidth(paramAndroidViewComponent, paramInt, 0);
  }
  
  public void setChildWidth(final AndroidViewComponent component, final int fWidth, final int trycount) {
    int i = this.container.$form().Width();
    if (i == 0 && trycount < 2)
      this.androidUIHandler.postDelayed(new Runnable() {
            public void run() {
              Log.d("HVArrangement", "(HVArrangement)Width not stable yet... trying again");
              HVArrangement.this.setChildWidth(component, fWidth, trycount + 1);
            }
          }100L); 
    trycount = fWidth;
    if (fWidth <= -1000) {
      Log.d("HVArrangement", "HVArrangement.setChildWidth(): width = " + fWidth + " parent Width = " + i + " child = " + component);
      trycount = -(fWidth + 1000) * i / 100;
    } 
    component.setLastWidth(trycount);
    if (this.orientation == 0) {
      ViewUtil.setChildWidthForHorizontalLayout(component.getView(), trycount);
      return;
    } 
    ViewUtil.setChildWidthForVerticalLayout(component.getView(), trycount);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/HVArrangement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
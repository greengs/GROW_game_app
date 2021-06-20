package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.runtime.util.IceCreamSandwichUtil;
import com.google.appinventor.components.runtime.util.KitkatUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.IOException;

@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public abstract class ButtonBase extends AndroidViewComponent implements View.OnClickListener, View.OnFocusChangeListener, View.OnLongClickListener, View.OnTouchListener {
  private static final String LOG_TAG = "ButtonBase";
  
  private static final float[] ROUNDED_CORNERS_ARRAY = new float[] { 10.0F, 10.0F, 10.0F, 10.0F, 10.0F, 10.0F, 10.0F, 10.0F };
  
  private static final float ROUNDED_CORNERS_RADIUS = 10.0F;
  
  private static final int SHAPED_DEFAULT_BACKGROUND_COLOR = -3355444;
  
  private static int defaultButtonMinHeight;
  
  private static int defaultButtonMinWidth = 0;
  
  private int backgroundColor;
  
  private Drawable backgroundImageDrawable;
  
  private boolean bold;
  
  private Drawable defaultButtonDrawable;
  
  private ColorStateList defaultColorStateList;
  
  private int fontTypeface;
  
  private String imagePath = "";
  
  private boolean italic;
  
  private Drawable myBackgroundDrawable = null;
  
  private int shape;
  
  private boolean showFeedback = true;
  
  private int textAlignment;
  
  private int textColor;
  
  private final Button view;
  
  static {
    defaultButtonMinHeight = 0;
  }
  
  public ButtonBase(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    this.view = new Button((Context)paramComponentContainer.$context());
    this.defaultButtonDrawable = this.view.getBackground();
    this.defaultColorStateList = this.view.getTextColors();
    defaultButtonMinWidth = KitkatUtil.getMinWidth((TextView)this.view);
    defaultButtonMinHeight = KitkatUtil.getMinHeight((TextView)this.view);
    paramComponentContainer.$add(this);
    this.view.setOnClickListener(this);
    this.view.setOnFocusChangeListener(this);
    this.view.setOnLongClickListener(this);
    this.view.setOnTouchListener(this);
    IceCreamSandwichUtil.setAllCaps((TextView)this.view, false);
    TextAlignment(1);
    BackgroundColor(0);
    Image("");
    Enabled(true);
    this.fontTypeface = 0;
    TextViewUtil.setFontTypeface((TextView)this.view, this.fontTypeface, this.bold, this.italic);
    FontSize(14.0F);
    Text("");
    TextColor(0);
    Shape(0);
  }
  
  private ColorStateList createRippleState() {
    int i = this.defaultColorStateList.getColorForState(this.view.getDrawableState(), 16842910);
    i = Color.argb(70, Color.red(i), Color.green(i), Color.blue(i));
    return new ColorStateList(new int[][] { { 16842910 },  }, new int[] { i });
  }
  
  private Drawable getSafeBackgroundDrawable() {
    if (this.myBackgroundDrawable == null) {
      Drawable.ConstantState constantState = this.defaultButtonDrawable.getConstantState();
      if (constantState != null && Build.VERSION.SDK_INT >= 10) {
        try {
          this.myBackgroundDrawable = constantState.newDrawable().mutate();
        } catch (NullPointerException nullPointerException) {
          Log.e("ButtonBase", "Unable to clone button drawable", nullPointerException);
          this.myBackgroundDrawable = this.defaultButtonDrawable;
        } 
        return this.myBackgroundDrawable;
      } 
    } else {
      return this.myBackgroundDrawable;
    } 
    this.myBackgroundDrawable = this.defaultButtonDrawable;
    return this.myBackgroundDrawable;
  }
  
  private void setShape() {
    // Byte code:
    //   0: new android/graphics/drawable/ShapeDrawable
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: aload_0
    //   9: getfield shape : I
    //   12: tableswitch default -> 40, 1 -> 48, 2 -> 133, 3 -> 147
    //   40: new java/lang/IllegalArgumentException
    //   43: dup
    //   44: invokespecial <init> : ()V
    //   47: athrow
    //   48: aload_1
    //   49: new android/graphics/drawable/shapes/RoundRectShape
    //   52: dup
    //   53: getstatic com/google/appinventor/components/runtime/ButtonBase.ROUNDED_CORNERS_ARRAY : [F
    //   56: aconst_null
    //   57: aconst_null
    //   58: invokespecial <init> : ([FLandroid/graphics/RectF;[F)V
    //   61: invokevirtual setShape : (Landroid/graphics/drawable/shapes/Shape;)V
    //   64: invokestatic isClassicMode : ()Z
    //   67: ifne -> 161
    //   70: getstatic android/os/Build$VERSION.SDK_INT : I
    //   73: bipush #21
    //   75: if_icmplt -> 161
    //   78: aload_0
    //   79: getfield view : Landroid/widget/Button;
    //   82: new android/graphics/drawable/RippleDrawable
    //   85: dup
    //   86: aload_0
    //   87: invokespecial createRippleState : ()Landroid/content/res/ColorStateList;
    //   90: aload_1
    //   91: aload_1
    //   92: invokespecial <init> : (Landroid/content/res/ColorStateList;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V
    //   95: invokestatic setBackgroundDrawable : (Landroid/view/View;Landroid/graphics/drawable/Drawable;)V
    //   98: aload_0
    //   99: getfield backgroundColor : I
    //   102: ldc_w 16777215
    //   105: if_icmpne -> 172
    //   108: aload_0
    //   109: getfield view : Landroid/widget/Button;
    //   112: invokevirtual getBackground : ()Landroid/graphics/drawable/Drawable;
    //   115: aload_0
    //   116: getfield backgroundColor : I
    //   119: getstatic android/graphics/PorterDuff$Mode.CLEAR : Landroid/graphics/PorterDuff$Mode;
    //   122: invokevirtual setColorFilter : (ILandroid/graphics/PorterDuff$Mode;)V
    //   125: aload_0
    //   126: getfield view : Landroid/widget/Button;
    //   129: invokevirtual invalidate : ()V
    //   132: return
    //   133: aload_1
    //   134: new android/graphics/drawable/shapes/RectShape
    //   137: dup
    //   138: invokespecial <init> : ()V
    //   141: invokevirtual setShape : (Landroid/graphics/drawable/shapes/Shape;)V
    //   144: goto -> 64
    //   147: aload_1
    //   148: new android/graphics/drawable/shapes/OvalShape
    //   151: dup
    //   152: invokespecial <init> : ()V
    //   155: invokevirtual setShape : (Landroid/graphics/drawable/shapes/Shape;)V
    //   158: goto -> 64
    //   161: aload_0
    //   162: getfield view : Landroid/widget/Button;
    //   165: aload_1
    //   166: invokestatic setBackgroundDrawable : (Landroid/view/View;Landroid/graphics/drawable/Drawable;)V
    //   169: goto -> 98
    //   172: aload_0
    //   173: getfield backgroundColor : I
    //   176: ifne -> 197
    //   179: aload_0
    //   180: getfield view : Landroid/widget/Button;
    //   183: invokevirtual getBackground : ()Landroid/graphics/drawable/Drawable;
    //   186: ldc -3355444
    //   188: getstatic android/graphics/PorterDuff$Mode.SRC_ATOP : Landroid/graphics/PorterDuff$Mode;
    //   191: invokevirtual setColorFilter : (ILandroid/graphics/PorterDuff$Mode;)V
    //   194: goto -> 125
    //   197: aload_0
    //   198: getfield view : Landroid/widget/Button;
    //   201: invokevirtual getBackground : ()Landroid/graphics/drawable/Drawable;
    //   204: aload_0
    //   205: getfield backgroundColor : I
    //   208: getstatic android/graphics/PorterDuff$Mode.SRC_ATOP : Landroid/graphics/PorterDuff$Mode;
    //   211: invokevirtual setColorFilter : (ILandroid/graphics/PorterDuff$Mode;)V
    //   214: goto -> 125
  }
  
  private void updateAppearance() {
    if (this.backgroundImageDrawable == null) {
      if (this.shape == 0) {
        if (this.backgroundColor == 0) {
          ViewUtil.setBackgroundDrawable((View)this.view, this.defaultButtonDrawable);
        } else if (this.backgroundColor == 16777215) {
          ViewUtil.setBackgroundDrawable((View)this.view, null);
          ViewUtil.setBackgroundDrawable((View)this.view, getSafeBackgroundDrawable());
          this.view.getBackground().setColorFilter(this.backgroundColor, PorterDuff.Mode.CLEAR);
        } else {
          ViewUtil.setBackgroundDrawable((View)this.view, null);
          ViewUtil.setBackgroundDrawable((View)this.view, getSafeBackgroundDrawable());
          this.view.getBackground().setColorFilter(this.backgroundColor, PorterDuff.Mode.SRC_ATOP);
        } 
      } else {
        setShape();
      } 
      TextViewUtil.setMinSize((TextView)this.view, defaultButtonMinWidth, defaultButtonMinHeight);
      return;
    } 
    ViewUtil.setBackgroundImage((View)this.view, this.backgroundImageDrawable);
    TextViewUtil.setMinSize((TextView)this.view, 0, 0);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the button's background color")
  public int BackgroundColor() {
    return this.backgroundColor;
  }
  
  @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
  @SimpleProperty(description = "Specifies the background color of the %type%. The background color will not be visible if an Image is being displayed.")
  public void BackgroundColor(int paramInt) {
    this.backgroundColor = paramInt;
    updateAppearance();
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean) {
    TextViewUtil.setEnabled((TextView)this.view, paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If set, user can tap %type% to cause action.")
  public boolean Enabled() {
    return TextViewUtil.isEnabled((TextView)this.view);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void FontBold(boolean paramBoolean) {
    this.bold = paramBoolean;
    TextViewUtil.setFontTypeface((TextView)this.view, this.fontTypeface, paramBoolean, this.italic);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If set, %type% text is displayed in bold.")
  public boolean FontBold() {
    return this.bold;
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void FontItalic(boolean paramBoolean) {
    this.italic = paramBoolean;
    TextViewUtil.setFontTypeface((TextView)this.view, this.fontTypeface, this.bold, paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If set, %type% text is displayed in italics.")
  public boolean FontItalic() {
    return this.italic;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Point size for %type% text.")
  public float FontSize() {
    return TextViewUtil.getFontSize((TextView)this.view, (Context)this.container.$context());
  }
  
  @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float")
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void FontSize(float paramFloat) {
    TextViewUtil.setFontSize((TextView)this.view, paramFloat);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Font family for %type% text.", userVisible = false)
  public int FontTypeface() {
    return this.fontTypeface;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "typeface")
  @SimpleProperty(userVisible = false)
  public void FontTypeface(int paramInt) {
    this.fontTypeface = paramInt;
    TextViewUtil.setFontTypeface((TextView)this.view, this.fontTypeface, this.bold, this.italic);
  }
  
  @SimpleEvent(description = "Indicates the cursor moved over the %type% so it is now possible to click it.")
  public void GotFocus() {
    EventDispatcher.dispatchEvent(this, "GotFocus", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Image to display on button.")
  public String Image() {
    return this.imagePath;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "asset")
  @SimpleProperty(description = "Specifies the path of the image of the %type%.  If there is both an Image and a BackgroundColor, only the Image will be visible.")
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
      } catch (IOException iOException) {
        Log.e("ButtonBase", "Unable to load " + this.imagePath);
      }  
    updateAppearance();
  }
  
  @SimpleEvent(description = "Indicates the cursor moved away from the %type% so it is now no longer possible to click it.")
  public void LostFocus() {
    EventDispatcher.dispatchEvent(this, "LostFocus", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
  public int Shape() {
    return this.shape;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "button_shape")
  @SimpleProperty(description = "Specifies the shape of the %type% (default, rounded, rectangular, oval). The shape will not be visible if an Image is being displayed.", userVisible = false)
  public void Shape(int paramInt) {
    this.shape = paramInt;
    updateAppearance();
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty(description = "Specifies if a visual feedback should be shown  for a %type% that has an image as background.")
  public void ShowFeedback(boolean paramBoolean) {
    this.showFeedback = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the visual feedback state of the %type%")
  public boolean ShowFeedback() {
    return this.showFeedback;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Text to display on %type%.")
  public String Text() {
    return TextViewUtil.getText((TextView)this.view);
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void Text(String paramString) {
    TextViewUtil.setText((TextView)this.view, paramString);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Left, center, or right.", userVisible = false)
  public int TextAlignment() {
    return this.textAlignment;
  }
  
  @DesignerProperty(defaultValue = "1", editorType = "textalignment")
  @SimpleProperty(userVisible = false)
  public void TextAlignment(int paramInt) {
    this.textAlignment = paramInt;
    TextViewUtil.setAlignment((TextView)this.view, paramInt, true);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Color for button text.")
  public int TextColor() {
    return this.textColor;
  }
  
  @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
  @SimpleProperty
  public void TextColor(int paramInt) {
    this.textColor = paramInt;
    if (paramInt != 0) {
      TextViewUtil.setTextColor((TextView)this.view, paramInt);
      return;
    } 
    TextViewUtil.setTextColors((TextView)this.view, this.defaultColorStateList);
  }
  
  @SimpleEvent(description = "Indicates that the %type% was pressed down.")
  public void TouchDown() {
    EventDispatcher.dispatchEvent(this, "TouchDown", new Object[0]);
  }
  
  @SimpleEvent(description = "Indicates that the %type% has been released.")
  public void TouchUp() {
    EventDispatcher.dispatchEvent(this, "TouchUp", new Object[0]);
  }
  
  public abstract void click();
  
  public View getView() {
    return (View)this.view;
  }
  
  public boolean longClick() {
    return false;
  }
  
  public void onClick(View paramView) {
    click();
  }
  
  public void onFocusChange(View paramView, boolean paramBoolean) {
    if (paramBoolean) {
      GotFocus();
      return;
    } 
    LostFocus();
  }
  
  public boolean onLongClick(View paramView) {
    return longClick();
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
    if (paramMotionEvent.getAction() == 0) {
      if (ShowFeedback() && (AppInventorCompatActivity.isClassicMode() || Build.VERSION.SDK_INT < 21)) {
        paramView.getBackground().setAlpha(70);
        paramView.invalidate();
      } 
      TouchDown();
      return false;
    } 
    if (paramMotionEvent.getAction() == 1 || paramMotionEvent.getAction() == 3) {
      if (ShowFeedback() && (AppInventorCompatActivity.isClassicMode() || Build.VERSION.SDK_INT < 21)) {
        paramView.getBackground().setAlpha(255);
        paramView.invalidate();
      } 
      TouchUp();
    } 
    return false;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/ButtonBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
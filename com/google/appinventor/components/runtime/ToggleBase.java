package com.google.appinventor.components.runtime;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.TextViewUtil;

@SimpleObject
public abstract class ToggleBase<T extends CompoundButton> extends AndroidViewComponent implements CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener {
  private int backgroundColor;
  
  private boolean bold;
  
  private int fontTypeface;
  
  private boolean italic;
  
  private int textColor;
  
  protected T view;
  
  public ToggleBase(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public int BackgroundColor() {
    return this.backgroundColor;
  }
  
  @DesignerProperty(defaultValue = "&H00FFFFFF", editorType = "color")
  @SimpleProperty(description = "The background color of the %type% as an alpha-red-green-blue integer.")
  public void BackgroundColor(int paramInt) {
    this.backgroundColor = paramInt;
    if (paramInt != 0) {
      TextViewUtil.setBackgroundColor((TextView)this.view, paramInt);
      return;
    } 
    TextViewUtil.setBackgroundColor((TextView)this.view, 16777215);
  }
  
  @SimpleEvent(description = "User tapped and released the %type%.")
  public void Changed() {
    EventDispatcher.dispatchEvent(this, "Changed", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty(description = "True if the %type% is active and clickable.")
  public void Enabled(boolean paramBoolean) {
    TextViewUtil.setEnabled((TextView)this.view, paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public boolean Enabled() {
    return this.view.isEnabled();
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(description = "Set to true if the text of the %type% should be bold.", userVisible = false)
  public void FontBold(boolean paramBoolean) {
    this.bold = paramBoolean;
    TextViewUtil.setFontTypeface((TextView)this.view, this.fontTypeface, paramBoolean, this.italic);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
  public boolean FontBold() {
    return this.bold;
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(description = "Set to true if the text of the %type% should be italic.", userVisible = false)
  public void FontItalic(boolean paramBoolean) {
    this.italic = paramBoolean;
    TextViewUtil.setFontTypeface((TextView)this.view, this.fontTypeface, this.bold, paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
  public boolean FontItalic() {
    return this.italic;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public float FontSize() {
    return TextViewUtil.getFontSize((TextView)this.view, (Context)this.container.$context());
  }
  
  @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float")
  @SimpleProperty(description = "Specifies the text font size of the %type% in scale-independent pixels.")
  public void FontSize(float paramFloat) {
    TextViewUtil.setFontSize((TextView)this.view, paramFloat);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
  public int FontTypeface() {
    return this.fontTypeface;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "typeface")
  @SimpleProperty(description = "Specifies the text font face of the %type%.", userVisible = false)
  public void FontTypeface(int paramInt) {
    this.fontTypeface = paramInt;
    TextViewUtil.setFontTypeface((TextView)this.view, this.fontTypeface, this.bold, this.italic);
  }
  
  @SimpleEvent(description = "%type% became the focused component.")
  public void GotFocus() {
    EventDispatcher.dispatchEvent(this, "GotFocus", new Object[0]);
  }
  
  @SimpleEvent(description = "%type% stopped being the focused component.")
  public void LostFocus() {
    EventDispatcher.dispatchEvent(this, "LostFocus", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public String Text() {
    return TextViewUtil.getText((TextView)this.view);
  }
  
  @DesignerProperty(editorType = "string")
  @SimpleProperty(description = "Specifies the text displayed by the %type%.")
  public void Text(String paramString) {
    TextViewUtil.setText((TextView)this.view, paramString);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public int TextColor() {
    return this.textColor;
  }
  
  @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
  @SimpleProperty(description = "Specifies the text color of the %type% as an alpha-red-green-blue integer.")
  public void TextColor(int paramInt) {
    this.textColor = paramInt;
    if (paramInt != 0) {
      TextViewUtil.setTextColor((TextView)this.view, paramInt);
      return;
    } 
    T t = this.view;
    if (this.container.$form().isDarkTheme()) {
      paramInt = -1;
    } else {
      paramInt = -16777216;
    } 
    TextViewUtil.setTextColor((TextView)t, paramInt);
  }
  
  public View getView() {
    return (View)this.view;
  }
  
  protected void initToggle() {
    this.view.setOnFocusChangeListener(this);
    this.view.setOnCheckedChangeListener(this);
    this.container.$add(this);
    BackgroundColor(16777215);
    Enabled(true);
    this.fontTypeface = 0;
    TextViewUtil.setFontTypeface((TextView)this.view, this.fontTypeface, this.bold, this.italic);
    FontSize(14.0F);
    Text("");
    TextColor(0);
  }
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean) {
    Changed();
  }
  
  public void onFocusChange(View paramView, boolean paramBoolean) {
    if (paramBoolean) {
      GotFocus();
      return;
    } 
    LostFocus();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/ToggleBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.EclairUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;

@SimpleObject
public abstract class TextBoxBase extends AndroidViewComponent implements View.OnFocusChangeListener {
  private int backgroundColor;
  
  private boolean bold;
  
  private Drawable defaultTextBoxDrawable;
  
  private int fontTypeface;
  
  private String hint;
  
  private boolean italic;
  
  private int textAlignment;
  
  private int textColor;
  
  protected final EditText view;
  
  public TextBoxBase(ComponentContainer paramComponentContainer, EditText paramEditText) {
    super(paramComponentContainer);
    this.view = paramEditText;
    if (Build.VERSION.SDK_INT >= 24)
      EclairUtil.disableSuggestions(paramEditText); 
    this.view.setOnFocusChangeListener(this);
    this.defaultTextBoxDrawable = this.view.getBackground();
    paramComponentContainer.$add(this);
    paramComponentContainer.setChildWidth(this, 160);
    TextAlignment(0);
    Enabled(true);
    this.fontTypeface = 0;
    TextViewUtil.setFontTypeface((TextView)this.view, this.fontTypeface, this.bold, this.italic);
    FontSize(14.0F);
    Hint("");
    Text("");
    TextColor(0);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The background color of the input box.  You can choose a color by name in the Designer or in the Blocks Editor.  The default background color is 'default' (shaded 3-D look).")
  public int BackgroundColor() {
    return this.backgroundColor;
  }
  
  @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
  @SimpleProperty
  public void BackgroundColor(int paramInt) {
    this.backgroundColor = paramInt;
    if (paramInt != 0) {
      TextViewUtil.setBackgroundColor((TextView)this.view, paramInt);
      return;
    } 
    ViewUtil.setBackgroundDrawable((View)this.view, this.defaultTextBoxDrawable);
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean) {
    TextViewUtil.setEnabled((TextView)this.view, paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the user can enter text into the %type%.  By default, this is true.")
  public boolean Enabled() {
    return TextViewUtil.isEnabled((TextView)this.view);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(userVisible = false)
  public void FontBold(boolean paramBoolean) {
    this.bold = paramBoolean;
    TextViewUtil.setFontTypeface((TextView)this.view, this.fontTypeface, paramBoolean, this.italic);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Whether the font for the text should be bold.  By default, it is not.", userVisible = false)
  public boolean FontBold() {
    return this.bold;
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(userVisible = false)
  public void FontItalic(boolean paramBoolean) {
    this.italic = paramBoolean;
    TextViewUtil.setFontTypeface((TextView)this.view, this.fontTypeface, this.bold, paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Whether the text should appear in italics.  By default, it does not.", userVisible = false)
  public boolean FontItalic() {
    return this.italic;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The font size for the text.  By default, it is 14.0 points.")
  public float FontSize() {
    return TextViewUtil.getFontSize((TextView)this.view, (Context)this.container.$context());
  }
  
  @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float")
  @SimpleProperty
  public void FontSize(float paramFloat) {
    TextViewUtil.setFontSize((TextView)this.view, paramFloat);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The font for the text.  The value can be changed in the Designer.", userVisible = false)
  public int FontTypeface() {
    return this.fontTypeface;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "typeface")
  @SimpleProperty(userVisible = false)
  public void FontTypeface(int paramInt) {
    this.fontTypeface = paramInt;
    TextViewUtil.setFontTypeface((TextView)this.view, this.fontTypeface, this.bold, this.italic);
  }
  
  @SimpleEvent(description = "Event raised when the %type% is selected for input, such as by the user touching it.")
  public void GotFocus() {
    EventDispatcher.dispatchEvent(this, "GotFocus", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Text that should appear faintly in the %type% to provide a hint as to what the user should enter.  This can only be seen if the Text property is empty.")
  public String Hint() {
    return this.hint;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void Hint(String paramString) {
    this.hint = paramString;
    this.view.setHint(paramString);
    this.view.invalidate();
  }
  
  @SimpleEvent(description = "Event raised when the %type% is no longer selected for input, such as if the user touches a different text box.")
  public void LostFocus() {
    EventDispatcher.dispatchEvent(this, "LostFocus", new Object[0]);
  }
  
  @SimpleFunction(description = "Sets the %type% active.")
  public void RequestFocus() {
    this.view.requestFocus();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String Text() {
    return TextViewUtil.getText((TextView)this.view);
  }
  
  @DesignerProperty(defaultValue = "", editorType = "textArea")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The text in the %type%, which can be set by the programmer in the Designer or Blocks Editor, or it can be entered by the user (unless the <code>Enabled</code> property is false).")
  public void Text(String paramString) {
    TextViewUtil.setText((TextView)this.view, paramString);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Whether the text should be left justified, centered, or right justified.  By default, text is left justified.", userVisible = false)
  public int TextAlignment() {
    return this.textAlignment;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "textalignment")
  @SimpleProperty(userVisible = false)
  public void TextAlignment(int paramInt) {
    this.textAlignment = paramInt;
    TextViewUtil.setAlignment((TextView)this.view, paramInt, false);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color for the text.  You can choose a color by name in the Designer or in the Blocks Editor.  The default text color is black.")
  public int TextColor() {
    return this.textColor;
  }
  
  @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
  @SimpleProperty
  public void TextColor(int paramInt) {
    this.textColor = paramInt;
    if (paramInt != 0) {
      TextViewUtil.setTextColor((TextView)this.view, paramInt);
      return;
    } 
    EditText editText = this.view;
    if (this.container.$form().isDarkTheme()) {
      paramInt = -1;
    } else {
      paramInt = -16777216;
    } 
    TextViewUtil.setTextColor((TextView)editText, paramInt);
  }
  
  public View getView() {
    return (View)this.view;
  }
  
  public void onFocusChange(View paramView, boolean paramBoolean) {
    if (paramBoolean) {
      GotFocus();
      return;
    } 
    LostFocus();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/TextBoxBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
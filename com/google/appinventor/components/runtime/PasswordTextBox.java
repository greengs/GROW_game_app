package com.google.appinventor.components.runtime;

import android.content.Context;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.widget.EditText;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "<p>A box for entering passwords.  This is the same as the ordinary <code>TextBox</code> component except this does not display the characters typed by the user.</p><p>The value of the text in the box can be found or set through the <code>Text</code> property. If blank, the <code>Hint</code> property, which appears as faint text in the box, can provide the user with guidance as to what to type.</p> <p>Text boxes are usually used with the <code>Button</code> component, with the user clicking on the button when text entry is complete.</p>", version = 4)
@SimpleObject
public final class PasswordTextBox extends TextBoxBase {
  private boolean passwordVisible;
  
  public PasswordTextBox(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, new EditText((Context)paramComponentContainer.$context()));
    this.view.setSingleLine(true);
    this.view.setTransformationMethod((TransformationMethod)new PasswordTransformationMethod());
    this.view.setImeOptions(6);
    PasswordVisible(false);
  }
  
  @SimpleProperty(description = "Visibility of password.")
  public void PasswordVisible(boolean paramBoolean) {
    this.passwordVisible = paramBoolean;
    if (paramBoolean) {
      this.view.setInputType(145);
      return;
    } 
    this.view.setInputType(129);
  }
  
  @SimpleProperty(description = "Visibility of password.")
  public boolean PasswordVisible() {
    return this.passwordVisible;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/PasswordTextBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
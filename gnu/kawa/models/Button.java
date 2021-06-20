package gnu.kawa.models;

import java.awt.Color;

public class Button extends Model {
  Object action;
  
  Color background;
  
  boolean disabled;
  
  Color foreground;
  
  String text;
  
  Object width;
  
  public Object getAction() {
    return this.action;
  }
  
  public Color getBackground() {
    return this.background;
  }
  
  public Color getForeground() {
    return this.foreground;
  }
  
  public String getText() {
    return this.text;
  }
  
  public boolean isDisabled() {
    return this.disabled;
  }
  
  public void makeView(Display paramDisplay, Object paramObject) {
    paramDisplay.addButton(this, paramObject);
  }
  
  public void setAction(Object paramObject) {
    this.action = paramObject;
  }
  
  public void setBackground(Color paramColor) {
    this.background = paramColor;
    notifyListeners("background");
  }
  
  public void setDisabled(boolean paramBoolean) {
    this.disabled = paramBoolean;
  }
  
  public void setForeground(Color paramColor) {
    this.foreground = paramColor;
    notifyListeners("foreground");
  }
  
  public void setText(String paramString) {
    this.text = paramString;
    notifyListeners("text");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/models/Button.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
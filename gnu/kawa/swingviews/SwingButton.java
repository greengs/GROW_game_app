package gnu.kawa.swingviews;

import gnu.kawa.models.Button;
import gnu.kawa.models.Model;
import gnu.kawa.models.ModelListener;
import java.awt.Color;
import javax.swing.JButton;

public class SwingButton extends JButton implements ModelListener {
  Button model;
  
  public SwingButton(Button paramButton) {
    super(paramButton.getText());
    setModel(new SwModel(paramButton));
    this.model = paramButton;
    Object object = paramButton.getAction();
    if (object != null)
      addActionListener(SwingDisplay.makeActionListener(object)); 
    paramButton.addListener(this);
    object = paramButton.getForeground();
    if (object != null)
      super.setBackground((Color)object); 
    Color color = paramButton.getBackground();
    if (color != null)
      super.setBackground(color); 
  }
  
  public void modelUpdated(Model paramModel, Object paramObject) {
    if (paramObject == "text" && paramModel == this.model) {
      super.setText(this.model.getText());
      return;
    } 
    if (paramObject == "foreground" && paramModel == this.model) {
      super.setForeground(this.model.getForeground());
      return;
    } 
    if (paramObject == "background" && paramModel == this.model) {
      super.setBackground(this.model.getBackground());
      return;
    } 
  }
  
  public void setBackground(Color paramColor) {
    if (this.model == null) {
      super.setBackground(paramColor);
      return;
    } 
    this.model.setBackground(paramColor);
  }
  
  public void setForeground(Color paramColor) {
    if (this.model == null) {
      super.setForeground(paramColor);
      return;
    } 
    this.model.setForeground(paramColor);
  }
  
  public void setText(String paramString) {
    if (this.model == null) {
      super.setText(paramString);
      return;
    } 
    this.model.setText(paramString);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/swingviews/SwingButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
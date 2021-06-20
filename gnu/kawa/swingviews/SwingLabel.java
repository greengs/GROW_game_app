package gnu.kawa.swingviews;

import gnu.kawa.models.Label;
import gnu.kawa.models.Model;
import gnu.kawa.models.ModelListener;
import javax.swing.JLabel;

class SwingLabel extends JLabel implements ModelListener {
  Label model;
  
  public SwingLabel(Label paramLabel) {
    this.model = paramLabel;
    String str = paramLabel.getText();
    if (str != null)
      super.setText(str); 
    paramLabel.addListener(this);
  }
  
  public void modelUpdated(Model paramModel, Object paramObject) {
    if (paramObject == "text" && paramModel == this.model)
      super.setText(this.model.getText()); 
  }
  
  public void setText(String paramString) {
    if (this.model == null) {
      super.setText(paramString);
      return;
    } 
    this.model.setText(paramString);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/swingviews/SwingLabel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
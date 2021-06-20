package gnu.kawa.swingviews;

import gnu.kawa.models.Box;
import gnu.kawa.models.Display;
import gnu.kawa.models.Model;
import gnu.kawa.models.ModelListener;
import gnu.kawa.models.Viewable;
import javax.swing.Box;

class SwingBox extends Box implements ModelListener {
  Box model;
  
  public SwingBox(Box paramBox, Display paramDisplay) {
    super(paramBox.getAxis());
    paramBox.addListener(this);
    Viewable viewable = paramBox.getCellSpacing();
    int j = paramBox.getComponentCount();
    for (int i = 0; i < j; i++) {
      if (i > 0 && viewable != null)
        viewable.makeView(paramDisplay, this); 
      paramBox.getComponent(i).makeView(paramDisplay, this);
    } 
  }
  
  public void modelUpdated(Model paramModel, Object paramObject) {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/swingviews/SwingBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.kawa.models;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.io.Serializable;

public abstract class Box extends Model implements Viewable, Serializable {
  Viewable cellSpacing;
  
  Viewable[] components;
  
  int numComponents;
  
  public void add(Viewable paramViewable) {
    Viewable[] arrayOfViewable = this.components;
    int i = this.numComponents;
    if (i == 0) {
      this.components = new Viewable[4];
    } else if (arrayOfViewable.length <= i) {
      this.components = new Viewable[i * 2];
      System.arraycopy(arrayOfViewable, 0, this.components, 0, i);
      arrayOfViewable = this.components;
    } 
    this.components[i] = paramViewable;
    this.numComponents = i + 1;
  }
  
  public abstract int getAxis();
  
  public Viewable getCellSpacing() {
    return this.cellSpacing;
  }
  
  public final Viewable getComponent(int paramInt) {
    return this.components[paramInt];
  }
  
  public final int getComponentCount() {
    return this.numComponents;
  }
  
  public void makeView(Display paramDisplay, Object paramObject) {
    paramDisplay.addBox(this, paramObject);
  }
  
  public void setCellSpacing(Object paramObject) {
    if (paramObject instanceof gnu.math.IntNum || paramObject instanceof Integer) {
      int i = ((Number)paramObject).intValue();
      if (getAxis() == 0) {
        paramObject = new Dimension(i, 0);
      } else {
        paramObject = new Dimension(0, i);
      } 
      this.cellSpacing = Spacer.rigidArea((Dimension2D)paramObject);
      return;
    } 
    this.cellSpacing = (Viewable)paramObject;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/models/Box.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
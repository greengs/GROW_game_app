package gnu.kawa.swingviews;

import gnu.kawa.models.Paintable;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class SwingPaintable extends JPanel {
  Dimension dim;
  
  Paintable paintable;
  
  public SwingPaintable(Paintable paramPaintable) {
    this.paintable = paramPaintable;
    Rectangle2D rectangle2D = paramPaintable.getBounds2D();
    int i = (int)Math.ceil(rectangle2D.getHeight());
    this.dim = new Dimension((int)Math.ceil(rectangle2D.getWidth()), i);
  }
  
  public Dimension getPreferredSize() {
    return this.dim;
  }
  
  public void paint(Graphics paramGraphics) {
    this.paintable.paint((Graphics2D)paramGraphics);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/swingviews/SwingPaintable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
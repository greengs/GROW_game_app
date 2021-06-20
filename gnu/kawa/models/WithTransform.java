package gnu.kawa.models;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class WithTransform implements Paintable {
  Paintable paintable;
  
  AffineTransform transform;
  
  public WithTransform(Paintable paramPaintable, AffineTransform paramAffineTransform) {
    this.paintable = paramPaintable;
    this.transform = paramAffineTransform;
  }
  
  public Rectangle2D getBounds2D() {
    return this.transform.createTransformedShape(this.paintable.getBounds2D()).getBounds2D();
  }
  
  public void paint(Graphics2D paramGraphics2D) {
    AffineTransform affineTransform = paramGraphics2D.getTransform();
    try {
      paramGraphics2D.transform(this.transform);
      this.paintable.paint(paramGraphics2D);
      return;
    } finally {
      paramGraphics2D.setTransform(affineTransform);
    } 
  }
  
  public Paintable transform(AffineTransform paramAffineTransform) {
    AffineTransform affineTransform = new AffineTransform(this.transform);
    affineTransform.concatenate(paramAffineTransform);
    return new WithTransform(this.paintable, affineTransform);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/models/WithTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
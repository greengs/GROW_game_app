package gnu.kawa.models;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public interface Paintable {
  Rectangle2D getBounds2D();
  
  void paint(Graphics2D paramGraphics2D);
  
  Paintable transform(AffineTransform paramAffineTransform);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/models/Paintable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
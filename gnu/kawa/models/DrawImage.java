package gnu.kawa.models;

import gnu.mapping.WrappedException;
import gnu.text.Path;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class DrawImage extends Model implements Paintable, Serializable {
  String description;
  
  BufferedImage image;
  
  Path src;
  
  public DrawImage() {}
  
  public DrawImage(BufferedImage paramBufferedImage) {
    this.image = paramBufferedImage;
  }
  
  public Rectangle2D getBounds2D() {
    loadImage();
    int i = this.image.getWidth();
    int j = this.image.getHeight();
    return new Rectangle2D.Float(0.0F, 0.0F, i, j);
  }
  
  public Image getImage() {
    loadImage();
    return this.image;
  }
  
  public Path getSrc() {
    return this.src;
  }
  
  void loadImage() {
    if (this.image == null)
      try {
        this.image = ImageIO.read(this.src.openInputStream());
        return;
      } catch (Throwable throwable) {
        throw WrappedException.wrapIfNeeded(throwable);
      }  
  }
  
  public void makeView(Display paramDisplay, Object paramObject) {
    paramDisplay.addImage(this, paramObject);
  }
  
  public void paint(Graphics2D paramGraphics2D) {
    loadImage();
    paramGraphics2D.drawImage(this.image, null, null);
  }
  
  public void setSrc(Path paramPath) {
    this.src = paramPath;
  }
  
  public Paintable transform(AffineTransform paramAffineTransform) {
    return new WithTransform(this, paramAffineTransform);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/models/DrawImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
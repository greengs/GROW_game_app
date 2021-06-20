package gnu.kawa.models;

import gnu.mapping.ThreadLocation;
import gnu.mapping.WrappedException;
import java.awt.Dimension;
import java.awt.geom.Dimension2D;

public abstract class Display {
  public static ThreadLocation myDisplay = new ThreadLocation("my-display");
  
  public static Dimension asDimension(Dimension2D paramDimension2D) {
    return (paramDimension2D instanceof Dimension || paramDimension2D == null) ? (Dimension)paramDimension2D : new Dimension((int)(paramDimension2D.getWidth() + 0.5D), (int)(paramDimension2D.getHeight() + 0.5D));
  }
  
  public static Display getInstance() {
    String str;
    Object object = myDisplay.get(null);
    if (object instanceof Display)
      return (Display)object; 
    if (object == null) {
      str = "swing";
    } else {
      str = object.toString();
    } 
    while (true) {
      int i = str.indexOf(',');
      String str1 = null;
      String str2 = str;
      if (i >= 0) {
        str1 = str.substring(i + 1);
        str2 = str.substring(0, i);
      } 
      if (str2.equals("swing")) {
        str = "gnu.kawa.swingviews.SwingDisplay";
      } else if (str2.equals("swt")) {
        str = "gnu.kawa.swtviews.SwtDisplay";
      } else {
        str = str2;
        if (str2.equals("echo2"))
          str = "gnu.kawa.echo2.Echo2Display"; 
      } 
      try {
        return (Display)Class.forName(str).getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
      } catch (ClassNotFoundException classNotFoundException) {
        if (str1 == null)
          throw new RuntimeException("no display toolkit: " + object); 
        String str3 = str1;
      } catch (Throwable throwable) {
        throw WrappedException.wrapIfNeeded(throwable);
      } 
    } 
  }
  
  public abstract void addBox(Box paramBox, Object paramObject);
  
  public abstract void addButton(Button paramButton, Object paramObject);
  
  public abstract void addImage(DrawImage paramDrawImage, Object paramObject);
  
  public abstract void addLabel(Label paramLabel, Object paramObject);
  
  public void addSpacer(Spacer paramSpacer, Object paramObject) {
    throw new Error("makeView called on Spacer");
  }
  
  public void addText(Text paramText, Object paramObject) {
    throw new Error("makeView called on Text");
  }
  
  public abstract void addView(Object paramObject1, Object paramObject2);
  
  public Model coerceToModel(Object paramObject) {
    return (paramObject instanceof gnu.lists.FString || paramObject instanceof String) ? new Label(paramObject.toString()) : (Model)paramObject;
  }
  
  public abstract Window makeWindow();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/models/Display.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
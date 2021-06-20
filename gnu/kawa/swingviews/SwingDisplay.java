package gnu.kawa.swingviews;

import gnu.kawa.models.Box;
import gnu.kawa.models.Button;
import gnu.kawa.models.Display;
import gnu.kawa.models.DrawImage;
import gnu.kawa.models.Label;
import gnu.kawa.models.Model;
import gnu.kawa.models.Paintable;
import gnu.kawa.models.Spacer;
import gnu.kawa.models.Text;
import gnu.kawa.models.Window;
import gnu.mapping.Procedure;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.WeakHashMap;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.Document;

public class SwingDisplay extends Display {
  private static WeakHashMap documents;
  
  static final SwingDisplay instance = new SwingDisplay();
  
  static {
    documents = null;
  }
  
  public static Display getInstance() {
    return instance;
  }
  
  static Document getSwingDocument(Text paramText) {
    // Byte code:
    //   0: ldc gnu/kawa/swingviews/SwingDisplay
    //   2: monitorenter
    //   3: getstatic gnu/kawa/swingviews/SwingDisplay.documents : Ljava/util/WeakHashMap;
    //   6: ifnonnull -> 19
    //   9: new java/util/WeakHashMap
    //   12: dup
    //   13: invokespecial <init> : ()V
    //   16: putstatic gnu/kawa/swingviews/SwingDisplay.documents : Ljava/util/WeakHashMap;
    //   19: getstatic gnu/kawa/swingviews/SwingDisplay.documents : Ljava/util/WeakHashMap;
    //   22: aload_0
    //   23: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   26: astore_1
    //   27: aload_1
    //   28: ifnull -> 41
    //   31: aload_1
    //   32: checkcast javax/swing/text/Document
    //   35: astore_0
    //   36: ldc gnu/kawa/swingviews/SwingDisplay
    //   38: monitorexit
    //   39: aload_0
    //   40: areturn
    //   41: new javax/swing/text/PlainDocument
    //   44: dup
    //   45: new gnu/kawa/swingviews/SwingContent
    //   48: dup
    //   49: aload_0
    //   50: getfield buffer : Lgnu/lists/CharBuffer;
    //   53: invokespecial <init> : (Lgnu/lists/CharBuffer;)V
    //   56: invokespecial <init> : (Ljavax/swing/text/AbstractDocument$Content;)V
    //   59: astore_1
    //   60: getstatic gnu/kawa/swingviews/SwingDisplay.documents : Ljava/util/WeakHashMap;
    //   63: aload_0
    //   64: aload_1
    //   65: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   68: pop
    //   69: aload_1
    //   70: astore_0
    //   71: goto -> 36
    //   74: astore_0
    //   75: ldc gnu/kawa/swingviews/SwingDisplay
    //   77: monitorexit
    //   78: aload_0
    //   79: athrow
    // Exception table:
    //   from	to	target	type
    //   3	19	74	finally
    //   19	27	74	finally
    //   31	36	74	finally
    //   41	69	74	finally
  }
  
  public static ActionListener makeActionListener(Object paramObject) {
    return (paramObject instanceof ActionListener) ? (ActionListener)paramObject : new ProcActionListener((Procedure)paramObject);
  }
  
  public void addBox(Box paramBox, Object paramObject) {
    addView(new SwingBox(paramBox, this), paramObject);
  }
  
  public void addButton(Button paramButton, Object paramObject) {
    addView(new SwingButton(paramButton), paramObject);
  }
  
  public void addImage(DrawImage paramDrawImage, Object paramObject) {
    addView(new JLabel(new ImageIcon(paramDrawImage.getImage())), paramObject);
  }
  
  public void addLabel(Label paramLabel, Object paramObject) {
    addView(new SwingLabel(paramLabel), paramObject);
  }
  
  public void addSpacer(Spacer paramSpacer, Object paramObject) {
    addView(new Box.Filler(paramSpacer.getMinimumSize(), paramSpacer.getPreferredSize(), paramSpacer.getMaximumSize()), paramObject);
  }
  
  public void addText(Text paramText, Object paramObject) {
    addView(new JTextField(getSwingDocument(paramText), paramText.getText(), 50), paramObject);
  }
  
  public void addView(Object paramObject1, Object paramObject2) {
    ((Container)paramObject2).add((Component)paramObject1);
  }
  
  public Model coerceToModel(Object paramObject) {
    return (paramObject instanceof Component) ? new ComponentModel((Component)paramObject) : ((paramObject instanceof Paintable) ? new ComponentModel(new SwingPaintable((Paintable)paramObject)) : super.coerceToModel(paramObject));
  }
  
  public Window makeWindow() {
    SwingFrame swingFrame = new SwingFrame(null, null, null);
    swingFrame.display = this;
    return swingFrame;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/swingviews/SwingDisplay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
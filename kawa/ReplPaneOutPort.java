package kawa;

import gnu.mapping.OutPort;
import gnu.text.Path;
import java.awt.Component;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;

public class ReplPaneOutPort extends OutPort {
  ReplDocument document;
  
  String str = "";
  
  AttributeSet style;
  
  TextPaneWriter tout;
  
  public ReplPaneOutPort(ReplDocument paramReplDocument, String paramString, AttributeSet paramAttributeSet) {
    this(new TextPaneWriter(paramReplDocument, paramAttributeSet), paramReplDocument, paramString, paramAttributeSet);
  }
  
  ReplPaneOutPort(TextPaneWriter paramTextPaneWriter, ReplDocument paramReplDocument, String paramString, AttributeSet paramAttributeSet) {
    super(paramTextPaneWriter, true, true, Path.valueOf(paramString));
    this.tout = paramTextPaneWriter;
    this.document = paramReplDocument;
    this.style = paramAttributeSet;
  }
  
  public void print(Object paramObject) {
    if (paramObject instanceof Component) {
      write((Component)paramObject);
      return;
    } 
    if (paramObject instanceof gnu.kawa.models.Paintable) {
      SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
      simpleAttributeSet.addAttribute("$ename", "Paintable");
      simpleAttributeSet.addAttribute(ReplPane.PaintableAttribute, paramObject);
      write(" ", simpleAttributeSet);
      return;
    } 
    if (paramObject instanceof gnu.kawa.models.Viewable) {
      SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
      simpleAttributeSet.addAttribute("$ename", "Viewable");
      simpleAttributeSet.addAttribute(ReplPane.ViewableAttribute, paramObject);
      write(" ", simpleAttributeSet);
      return;
    } 
    super.print(paramObject);
  }
  
  public void write(Component paramComponent) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new javax/swing/text/SimpleAttributeSet
    //   5: dup
    //   6: invokespecial <init> : ()V
    //   9: astore_2
    //   10: aload_2
    //   11: aload_1
    //   12: invokestatic setComponent : (Ljavax/swing/text/MutableAttributeSet;Ljava/awt/Component;)V
    //   15: aload_0
    //   16: ldc ' '
    //   18: aload_2
    //   19: invokevirtual write : (Ljava/lang/String;Ljavax/swing/text/MutableAttributeSet;)V
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	25	finally
  }
  
  public void write(String paramString, MutableAttributeSet paramMutableAttributeSet) {
    flush();
    this.document.write(paramString, paramMutableAttributeSet);
    setColumnNumber(1);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/ReplPaneOutPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
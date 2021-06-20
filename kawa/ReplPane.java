package kawa;

import gnu.lists.CharBuffer;
import gnu.mapping.OutPort;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.EditorKit;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;

public class ReplPane extends JTextPane implements KeyListener {
  public static final Object PaintableAttribute;
  
  public static final String PaintableElementName = "Paintable";
  
  public static final Object ViewableAttribute = new String("Viewable");
  
  public static final String ViewableElementName = "Viewable";
  
  ReplDocument document;
  
  static {
    PaintableAttribute = new String("Paintable");
  }
  
  public ReplPane(ReplDocument paramReplDocument) {
    super(paramReplDocument);
    this.document = paramReplDocument;
    paramReplDocument.pane = this;
    paramReplDocument.paneCount++;
    addKeyListener(this);
    addFocusListener(paramReplDocument);
    getEditorKit();
    setCaretPosition(paramReplDocument.outputMark);
  }
  
  protected EditorKit createDefaultEditorKit() {
    return new ReplEditorKit(this);
  }
  
  void enter() {
    int i;
    String str;
    int m = getCaretPosition();
    CharBuffer charBuffer = this.document.content.buffer;
    int k = charBuffer.length() - 1;
    this.document.endMark = -1;
    if (m >= this.document.outputMark) {
      int n = charBuffer.indexOf(10, m);
      i = n;
      if (n == k)
        if (k > this.document.outputMark && charBuffer.charAt(k - 1) == '\n') {
          i = n - 1;
        } else {
          this.document.insertString(k, "\n", (AttributeSet)null);
          i = n;
        }  
      this.document.endMark = i;
      synchronized (this.document.in_r) {
        this.document.in_r.notifyAll();
        if (m <= i)
          setCaretPosition(i + 1); 
        return;
      } 
    } 
    if (m == 0) {
      i = 0;
    } else {
      i = charBuffer.lastIndexOf(10, m - 1) + 1;
    } 
    Element element = this.document.getCharacterElement(i);
    int j = charBuffer.indexOf(10, m);
    if (element.getAttributes().isEqual(ReplDocument.promptStyle))
      i = element.getEndOffset(); 
    if (j < 0) {
      str = charBuffer.substring(i, k) + '\n';
    } else {
      str = str.substring(i, j + 1);
    } 
    setCaretPosition(this.document.outputMark);
    this.document.write(str, ReplDocument.inputStyle);
    if (this.document.in_r != null) {
      this.document.in_r.append(str, 0, str.length());
      return;
    } 
  }
  
  public MutableAttributeSet getInputAttributes() {
    return ReplDocument.inputStyle;
  }
  
  public OutPort getStderr() {
    return this.document.err_stream;
  }
  
  public OutPort getStdout() {
    return this.document.out_stream;
  }
  
  public void keyPressed(KeyEvent paramKeyEvent) {
    if (paramKeyEvent.getKeyCode() == 10) {
      enter();
      paramKeyEvent.consume();
    } 
  }
  
  public void keyReleased(KeyEvent paramKeyEvent) {}
  
  public void keyTyped(KeyEvent paramKeyEvent) {}
  
  public void removeNotify() {
    super.removeNotify();
    ReplDocument replDocument = this.document;
    int i = replDocument.paneCount - 1;
    replDocument.paneCount = i;
    if (i == 0)
      this.document.close(); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/ReplPane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
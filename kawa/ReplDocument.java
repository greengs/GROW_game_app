package kawa;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.kawa.swingviews.SwingContent;
import gnu.lists.CharBuffer;
import gnu.mapping.Environment;
import gnu.mapping.Future;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.text.QueueReader;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Reader;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class ReplDocument extends DefaultStyledDocument implements DocumentListener, FocusListener {
  static Style blueStyle;
  
  public static Style defaultStyle;
  
  public static Style inputStyle;
  
  static Style promptStyle;
  
  public static Style redStyle;
  
  public static StyleContext styles = new StyleContext();
  
  Object closeListeners;
  
  SwingContent content;
  
  public int endMark = -1;
  
  Environment environment;
  
  final ReplPaneOutPort err_stream;
  
  final GuiInPort in_p;
  
  final QueueReader in_r;
  
  Language language;
  
  int length = 0;
  
  final ReplPaneOutPort out_stream;
  
  public int outputMark = 0;
  
  JTextPane pane;
  
  int paneCount;
  
  Future thread;
  
  static {
    defaultStyle = styles.addStyle("default", null);
    inputStyle = styles.addStyle("input", null);
    redStyle = styles.addStyle("red", null);
    blueStyle = styles.addStyle("blue", null);
    promptStyle = styles.addStyle("prompt", null);
    StyleConstants.setForeground(redStyle, Color.red);
    StyleConstants.setForeground(blueStyle, Color.blue);
    StyleConstants.setForeground(promptStyle, Color.green);
    StyleConstants.setBold(inputStyle, true);
  }
  
  public ReplDocument(Language paramLanguage, Environment paramEnvironment, boolean paramBoolean) {
    this(new SwingContent(), paramLanguage, paramEnvironment, paramBoolean);
  }
  
  private ReplDocument(SwingContent paramSwingContent, Language paramLanguage, Environment paramEnvironment, final boolean shared) {
    super((AbstractDocument.Content)paramSwingContent, styles);
    this.content = paramSwingContent;
    ModuleBody.exitIncrement();
    addDocumentListener(this);
    this.language = paramLanguage;
    this.in_r = new QueueReader() {
        public void checkAvailable() {
          ReplDocument.this.checkingPendingInput();
        }
      };
    this.out_stream = new ReplPaneOutPort(this, "/dev/stdout", defaultStyle);
    this.err_stream = new ReplPaneOutPort(this, "/dev/stderr", redStyle);
    this.in_p = new GuiInPort((Reader)this.in_r, Path.valueOf("/dev/stdin"), this.out_stream, this);
    this.thread = Future.make((Procedure)new repl(paramLanguage) {
          public Object apply0() {
            Environment environment = Environment.getCurrent();
            if (shared)
              environment.setIndirectDefines(); 
            ReplDocument.this.environment = environment;
            Shell.run(this.language, environment);
            SwingUtilities.invokeLater(new Runnable() {
                  public void run() {
                    ReplDocument.this.fireDocumentClosed();
                  }
                });
            return Values.empty;
          }
        }paramEnvironment, (InPort)this.in_p, this.out_stream, this.err_stream);
    this.thread.start();
  }
  
  public void addDocumentCloseListener(DocumentCloseListener paramDocumentCloseListener) {
    ArrayList<Object> arrayList;
    if (this.closeListeners == null) {
      this.closeListeners = paramDocumentCloseListener;
      return;
    } 
    if (this.closeListeners instanceof ArrayList) {
      arrayList = (ArrayList)this.closeListeners;
    } else {
      arrayList = new ArrayList(10);
      arrayList.add(this.closeListeners);
      this.closeListeners = arrayList;
    } 
    arrayList.add(paramDocumentCloseListener);
  }
  
  public void changedUpdate(DocumentEvent paramDocumentEvent) {
    textValueChanged(paramDocumentEvent);
  }
  
  public void checkingPendingInput() {
    SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            int i = ReplDocument.this.outputMark;
            if (i <= ReplDocument.this.endMark) {
              CharBuffer charBuffer = ReplDocument.this.content.buffer;
              int j = charBuffer.indexOf(10, i);
              if (j == ReplDocument.this.endMark)
                ReplDocument.this.endMark = -1; 
              if (i == ReplDocument.this.outputMark)
                ReplDocument.this.outputMark = j + 1; 
              if (ReplDocument.this.in_r != null)
                synchronized (ReplDocument.this.in_r) {
                  ReplDocument.this.in_r.append((CharSequence)charBuffer, i, j + 1);
                  ReplDocument.this.in_r.notifyAll();
                  return;
                }  
            } 
          }
        });
  }
  
  void close() {
    this.in_r.appendEOF();
    try {
      Thread.sleep(100L);
    } catch (InterruptedException interruptedException) {}
    this.thread.stop();
    fireDocumentClosed();
    ModuleBody.exitDecrement();
  }
  
  public void deleteOldText() {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: iconst_0
    //   6: aload_0
    //   7: getfield outputMark : I
    //   10: invokevirtual getText : (II)Ljava/lang/String;
    //   13: astore_2
    //   14: aload_0
    //   15: getfield outputMark : I
    //   18: ifgt -> 30
    //   21: aload_0
    //   22: iconst_0
    //   23: iload_1
    //   24: invokevirtual remove : (II)V
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: aload_2
    //   31: bipush #10
    //   33: aload_0
    //   34: getfield outputMark : I
    //   37: iconst_1
    //   38: isub
    //   39: invokevirtual lastIndexOf : (II)I
    //   42: istore_1
    //   43: iload_1
    //   44: iconst_1
    //   45: iadd
    //   46: istore_1
    //   47: goto -> 21
    //   50: astore_2
    //   51: new java/lang/Error
    //   54: dup
    //   55: aload_2
    //   56: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   59: athrow
    //   60: astore_2
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_2
    //   64: athrow
    // Exception table:
    //   from	to	target	type
    //   4	21	50	javax/swing/text/BadLocationException
    //   4	21	60	finally
    //   21	27	50	javax/swing/text/BadLocationException
    //   21	27	60	finally
    //   30	43	50	javax/swing/text/BadLocationException
    //   30	43	60	finally
    //   51	60	60	finally
  }
  
  void fireDocumentClosed() {
    if (this.closeListeners instanceof DocumentCloseListener) {
      ((DocumentCloseListener)this.closeListeners).closed(this);
      return;
    } 
    if (this.closeListeners != null) {
      ArrayList<DocumentCloseListener> arrayList = (ArrayList)this.closeListeners;
      int i = arrayList.size();
      while (true) {
        if (--i >= 0) {
          ((DocumentCloseListener)arrayList.get(i)).closed(this);
          continue;
        } 
        return;
      } 
    } 
  }
  
  public void focusGained(FocusEvent paramFocusEvent) {
    Object object = paramFocusEvent.getSource();
    if (object instanceof ReplPane) {
      this.pane = (ReplPane)object;
    } else {
      this.pane = null;
    } 
    if (object instanceof ReplPane) {
      object = object;
    } else {
      object = null;
    } 
    this.pane = (JTextPane)object;
  }
  
  public void focusLost(FocusEvent paramFocusEvent) {
    this.pane = null;
  }
  
  public void insertString(int paramInt, String paramString, AttributeSet paramAttributeSet) {
    try {
      super.insertString(paramInt, paramString, paramAttributeSet);
      return;
    } catch (BadLocationException badLocationException) {
      throw new Error(badLocationException);
    } 
  }
  
  public void insertUpdate(DocumentEvent paramDocumentEvent) {
    textValueChanged(paramDocumentEvent);
  }
  
  public void removeDocumentCloseListener(DocumentCloseListener paramDocumentCloseListener) {
    if (this.closeListeners instanceof DocumentCloseListener) {
      if (this.closeListeners == paramDocumentCloseListener)
        this.closeListeners = null; 
      return;
    } 
    if (this.closeListeners != null) {
      ArrayList<DocumentCloseListener> arrayList = (ArrayList)this.closeListeners;
      int i = arrayList.size();
      while (true) {
        int j = i - 1;
        if (j >= 0) {
          i = j;
          if (arrayList.get(j) == paramDocumentCloseListener) {
            arrayList.remove(j);
            i = j;
          } 
          continue;
        } 
        if (arrayList.size() == 0) {
          this.closeListeners = null;
          return;
        } 
        // Byte code: goto -> 23
      } 
    } 
  }
  
  public void removeUpdate(DocumentEvent paramDocumentEvent) {
    textValueChanged(paramDocumentEvent);
  }
  
  public void textValueChanged(DocumentEvent paramDocumentEvent) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokeinterface getOffset : ()I
    //   8: istore_2
    //   9: aload_0
    //   10: invokevirtual getLength : ()I
    //   13: aload_0
    //   14: getfield length : I
    //   17: isub
    //   18: istore_3
    //   19: aload_0
    //   20: aload_0
    //   21: getfield length : I
    //   24: iload_3
    //   25: iadd
    //   26: putfield length : I
    //   29: iload_2
    //   30: aload_0
    //   31: getfield outputMark : I
    //   34: if_icmpge -> 75
    //   37: aload_0
    //   38: aload_0
    //   39: getfield outputMark : I
    //   42: iload_3
    //   43: iadd
    //   44: putfield outputMark : I
    //   47: aload_0
    //   48: getfield endMark : I
    //   51: iflt -> 72
    //   54: iload_2
    //   55: aload_0
    //   56: getfield endMark : I
    //   59: if_icmpge -> 98
    //   62: aload_0
    //   63: aload_0
    //   64: getfield endMark : I
    //   67: iload_3
    //   68: iadd
    //   69: putfield endMark : I
    //   72: aload_0
    //   73: monitorexit
    //   74: return
    //   75: iload_2
    //   76: iload_3
    //   77: isub
    //   78: aload_0
    //   79: getfield outputMark : I
    //   82: if_icmpge -> 47
    //   85: aload_0
    //   86: iload_2
    //   87: putfield outputMark : I
    //   90: goto -> 47
    //   93: astore_1
    //   94: aload_0
    //   95: monitorexit
    //   96: aload_1
    //   97: athrow
    //   98: iload_2
    //   99: iload_3
    //   100: isub
    //   101: aload_0
    //   102: getfield endMark : I
    //   105: if_icmpge -> 72
    //   108: aload_0
    //   109: iload_2
    //   110: putfield endMark : I
    //   113: goto -> 72
    // Exception table:
    //   from	to	target	type
    //   2	47	93	finally
    //   47	72	93	finally
    //   75	90	93	finally
    //   98	113	93	finally
  }
  
  public void write(final String str, final AttributeSet style) {
    SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            boolean bool;
            if (ReplDocument.this.pane != null && ReplDocument.this.pane.getCaretPosition() == ReplDocument.this.outputMark) {
              bool = true;
            } else {
              bool = false;
            } 
            ReplDocument.this.insertString(ReplDocument.this.outputMark, str, style);
            int i = str.length();
            ReplDocument replDocument = ReplDocument.this;
            replDocument.outputMark += i;
            if (bool)
              ReplDocument.this.pane.setCaretPosition(ReplDocument.this.outputMark); 
          }
        });
  }
  
  public static interface DocumentCloseListener {
    void closed(ReplDocument param1ReplDocument);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/ReplDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
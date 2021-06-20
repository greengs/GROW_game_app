package gnu.kawa.swingviews;

import gnu.lists.CharBuffer;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import javax.swing.undo.UndoableEdit;

public class SwingContent implements AbstractDocument.Content {
  public final CharBuffer buffer;
  
  public SwingContent() {
    this(100);
  }
  
  public SwingContent(int paramInt) {
    CharBuffer charBuffer = new CharBuffer(paramInt);
    charBuffer.gapEnd = paramInt - 1;
    charBuffer.getArray()[charBuffer.gapEnd] = '\n';
    this.buffer = charBuffer;
  }
  
  public SwingContent(CharBuffer paramCharBuffer) {
    this.buffer = paramCharBuffer;
  }
  
  public Position createPosition(int paramInt) throws BadLocationException {
    CharBuffer charBuffer = this.buffer;
    if (paramInt < 0 || paramInt > charBuffer.length())
      throw new BadLocationException("bad offset to createPosition", paramInt); 
    return new GapPosition(charBuffer, paramInt);
  }
  
  public void getChars(int paramInt1, int paramInt2, Segment paramSegment) throws BadLocationException {
    CharBuffer charBuffer = this.buffer;
    int i = charBuffer.getSegment(paramInt1, paramInt2);
    if (i < 0)
      throw new BadLocationException("invalid offset", paramInt1); 
    paramSegment.offset = i;
    paramSegment.array = charBuffer.getArray();
    paramSegment.count = paramInt2;
  }
  
  public String getString(int paramInt1, int paramInt2) throws BadLocationException {
    CharBuffer charBuffer = this.buffer;
    int i = charBuffer.getSegment(paramInt1, paramInt2);
    if (i < 0)
      throw new BadLocationException("invalid offset", paramInt1); 
    return new String(charBuffer.getArray(), i, paramInt2);
  }
  
  public UndoableEdit insertString(int paramInt, String paramString) throws BadLocationException {
    return insertString(paramInt, paramString, false);
  }
  
  public UndoableEdit insertString(int paramInt, String paramString, boolean paramBoolean) throws BadLocationException {
    CharBuffer charBuffer = this.buffer;
    if (paramInt < 0 || paramInt > charBuffer.length())
      throw new BadLocationException("bad insert", paramInt); 
    charBuffer.insert(paramInt, paramString, paramBoolean);
    GapUndoableEdit gapUndoableEdit = new GapUndoableEdit(paramInt);
    gapUndoableEdit.content = this;
    gapUndoableEdit.data = paramString;
    gapUndoableEdit.nitems = paramString.length();
    gapUndoableEdit.isInsertion = true;
    return gapUndoableEdit;
  }
  
  public int length() {
    return this.buffer.length();
  }
  
  public UndoableEdit remove(int paramInt1, int paramInt2) throws BadLocationException {
    CharBuffer charBuffer = this.buffer;
    if (paramInt2 < 0 || paramInt1 < 0 || paramInt1 + paramInt2 > charBuffer.length())
      throw new BadLocationException("invalid remove", paramInt1); 
    charBuffer.delete(paramInt1, paramInt2);
    GapUndoableEdit gapUndoableEdit = new GapUndoableEdit(paramInt1);
    gapUndoableEdit.content = this;
    gapUndoableEdit.data = new String(charBuffer.getArray(), charBuffer.gapEnd - paramInt2, paramInt2);
    gapUndoableEdit.nitems = paramInt2;
    gapUndoableEdit.isInsertion = false;
    return gapUndoableEdit;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/swingviews/SwingContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
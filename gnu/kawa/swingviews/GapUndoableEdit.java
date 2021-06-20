package gnu.kawa.swingviews;

import javax.swing.text.BadLocationException;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

class GapUndoableEdit extends AbstractUndoableEdit {
  SwingContent content;
  
  String data;
  
  boolean isInsertion;
  
  int nitems;
  
  int startOffset;
  
  GapUndoableEdit(int paramInt) {
    this.startOffset = paramInt;
  }
  
  private void doit(boolean paramBoolean) throws BadLocationException {
    if (paramBoolean) {
      this.content.insertString(this.startOffset, this.data);
      return;
    } 
    this.content.remove(this.startOffset, this.nitems);
  }
  
  public void redo() throws CannotUndoException {
    super.redo();
    try {
      doit(this.isInsertion);
      return;
    } catch (BadLocationException badLocationException) {
      throw new CannotRedoException();
    } 
  }
  
  public void undo() throws CannotUndoException {
    super.undo();
    try {
      boolean bool;
      if (!this.isInsertion) {
        bool = true;
      } else {
        bool = false;
      } 
      doit(bool);
      return;
    } catch (BadLocationException badLocationException) {
      throw new CannotUndoException();
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/swingviews/GapUndoableEdit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
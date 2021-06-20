package gnu.bytecode;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class VarEnumerator implements Enumeration {
  Scope currentScope;
  
  Variable next;
  
  Scope topScope;
  
  public VarEnumerator(Scope paramScope) {
    this.topScope = paramScope;
    reset();
  }
  
  private void fixup() {
    while (true) {
      if (this.next == null) {
        if (this.currentScope.firstChild != null) {
          this.currentScope = this.currentScope.firstChild;
        } else {
          while (this.currentScope.nextSibling == null) {
            if (this.currentScope == this.topScope)
              continue; 
            this.currentScope = this.currentScope.parent;
          } 
          this.currentScope = this.currentScope.nextSibling;
        } 
        this.next = this.currentScope.firstVar();
        continue;
      } 
      return;
    } 
  }
  
  public final boolean hasMoreElements() {
    return (this.next != null);
  }
  
  public Object nextElement() {
    Variable variable = nextVar();
    if (variable == null)
      throw new NoSuchElementException("VarEnumerator"); 
    return variable;
  }
  
  public final Variable nextVar() {
    Variable variable = this.next;
    if (variable != null) {
      this.next = variable.nextVar();
      if (this.next == null)
        fixup(); 
    } 
    return variable;
  }
  
  public final void reset() {
    this.currentScope = this.topScope;
    if (this.topScope != null) {
      this.next = this.currentScope.firstVar();
      if (this.next == null)
        fixup(); 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/VarEnumerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
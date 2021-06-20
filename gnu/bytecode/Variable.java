package gnu.bytecode;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class Variable extends Location implements Enumeration {
  private static final int LIVE_FLAG = 4;
  
  private static final int PARAMETER_FLAG = 2;
  
  private static final int SIMPLE_FLAG = 1;
  
  static final int UNASSIGNED = -1;
  
  private int flags = 1;
  
  Variable next;
  
  int offset = -1;
  
  Scope scope;
  
  public Variable() {}
  
  public Variable(String paramString) {
    setName(paramString);
  }
  
  public Variable(String paramString, Type paramType) {
    setName(paramString);
    setType(paramType);
  }
  
  private void setFlag(boolean paramBoolean, int paramInt) {
    if (paramBoolean) {
      this.flags |= paramInt;
      return;
    } 
    this.flags &= paramInt ^ 0xFFFFFFFF;
  }
  
  public void allocateLocal(CodeAttr paramCodeAttr) {
    if (this.offset == -1) {
      int i = 0;
      while (true) {
        if (!reserveLocal(i, paramCodeAttr)) {
          i++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public final boolean dead() {
    return ((this.flags & 0x4) == 0);
  }
  
  public void freeLocal(CodeAttr paramCodeAttr) {
    this.flags &= 0xFFFFFFFB;
    if ((getType()).size > 4) {
      i = 2;
    } else {
      i = 1;
    } 
    int i = this.offset + i;
    while (true) {
      int j = i - 1;
      if (j >= this.offset) {
        paramCodeAttr.locals.used[j] = null;
        Type[] arrayOfType = paramCodeAttr.local_types;
        i = j;
        if (arrayOfType != null) {
          arrayOfType[j] = null;
          i = j;
        } 
        continue;
      } 
      break;
    } 
  }
  
  public final boolean hasMoreElements() {
    return (this.next != null);
  }
  
  public final boolean isAssigned() {
    return (this.offset != -1);
  }
  
  public final boolean isParameter() {
    return ((this.flags & 0x2) != 0);
  }
  
  public final boolean isSimple() {
    return ((this.flags & 0x1) != 0);
  }
  
  public Object nextElement() {
    if (this.next == null)
      throw new NoSuchElementException("Variable enumeration"); 
    return this.next;
  }
  
  public final Variable nextVar() {
    return this.next;
  }
  
  public boolean reserveLocal(int paramInt, CodeAttr paramCodeAttr) {
    int j = getType().getSizeInWords();
    if (paramCodeAttr.locals.used == null) {
      paramCodeAttr.locals.used = new Variable[j + 20];
    } else if (paramCodeAttr.getMaxLocals() + j >= paramCodeAttr.locals.used.length) {
      Variable[] arrayOfVariable = new Variable[paramCodeAttr.locals.used.length * 2 + j];
      System.arraycopy(paramCodeAttr.locals.used, 0, arrayOfVariable, 0, paramCodeAttr.getMaxLocals());
      paramCodeAttr.locals.used = arrayOfVariable;
    } 
    int i;
    for (i = 0; i < j; i++) {
      if (paramCodeAttr.locals.used[paramInt + i] != null)
        return false; 
    } 
    for (i = 0; i < j; i++)
      paramCodeAttr.locals.used[paramInt + i] = this; 
    if (paramInt + j > paramCodeAttr.getMaxLocals())
      paramCodeAttr.setMaxLocals(paramInt + j); 
    this.offset = paramInt;
    this.flags |= 0x4;
    if (this.offset == 0 && "<init>".equals(paramCodeAttr.getMethod().getName()))
      setType(paramCodeAttr.local_types[0]); 
    return true;
  }
  
  public final void setParameter(boolean paramBoolean) {
    setFlag(paramBoolean, 2);
  }
  
  public final void setSimple(boolean paramBoolean) {
    setFlag(paramBoolean, 1);
  }
  
  boolean shouldEmit() {
    Scope scope = this.scope;
    if (isSimple() && this.name != null && scope != null) {
      Label label = scope.start;
      if (label != null) {
        int i = label.position;
        if (i >= 0) {
          Label label1 = scope.end;
          if (label1 != null && label1.position > i)
            return true; 
        } 
      } 
    } 
    return false;
  }
  
  public String toString() {
    return "Variable[" + getName() + " offset:" + this.offset + ']';
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/Variable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
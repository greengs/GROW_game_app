package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.Compilation;
import gnu.lists.TreePosition;
import gnu.math.IntNum;

public final class Focus extends TreePosition {
  public static final ClassType TYPE;
  
  static ThreadLocal current = new ThreadLocal();
  
  static final Method getCurrentFocusMethod;
  
  IntNum contextPosition;
  
  public long position;
  
  static {
    TYPE = ClassType.make("gnu.kawa.xml.Focus");
    getCurrentFocusMethod = TYPE.getDeclaredMethod("getCurrent", 0);
  }
  
  public static void compileGetCurrent(Compilation paramCompilation) {
    paramCompilation.getCode().emitInvoke(getCurrentFocusMethod);
  }
  
  public static Focus getCurrent() {
    Focus focus2 = (Focus)current.get();
    Focus focus1 = focus2;
    if (focus2 == null) {
      focus1 = new Focus();
      current.set(focus1);
    } 
    return focus1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/Focus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
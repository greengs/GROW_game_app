package gnu.expr;

import gnu.mapping.InPort;
import gnu.mapping.Procedure1;

class SimplePrompter extends Procedure1 {
  public String prefix = "[";
  
  public String suffix = "] ";
  
  public Object apply1(Object paramObject) {
    if (paramObject instanceof InPort) {
      int i = ((InPort)paramObject).getLineNumber() + 1;
      if (i >= 0)
        return this.prefix + i + this.suffix; 
    } 
    return this.suffix;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/SimplePrompter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
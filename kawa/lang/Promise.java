package kawa.lang;

import gnu.lists.Consumer;
import gnu.mapping.Future;
import gnu.mapping.Procedure;
import gnu.text.Printable;
import java.util.concurrent.Future;

public class Promise implements Printable {
  Object result;
  
  Procedure thunk;
  
  public Promise(Procedure paramProcedure) {
    this.thunk = paramProcedure;
  }
  
  public static Object force(Object paramObject) throws Throwable {
    if (paramObject instanceof Promise)
      return ((Promise)paramObject).force(); 
    if (paramObject instanceof Future)
      return ((Future)paramObject).waitForResult(); 
    Object object = paramObject;
    return (paramObject instanceof Future) ? ((Future)paramObject).get() : object;
  }
  
  public Object force() throws Throwable {
    if (this.result == null) {
      Object object = this.thunk.apply0();
      if (this.result == null)
        this.result = object; 
    } 
    return this.result;
  }
  
  public void print(Consumer paramConsumer) {
    if (this.result == null) {
      paramConsumer.write("#<promise - not forced yet>");
      return;
    } 
    paramConsumer.write("#<promise - forced to a ");
    paramConsumer.write(this.result.getClass().getName());
    paramConsumer.write(62);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/Promise.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
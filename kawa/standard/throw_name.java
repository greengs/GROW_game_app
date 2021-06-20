package kawa.standard;

import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import kawa.lang.GenericError;
import kawa.lang.NamedException;

public class throw_name extends ProcedureN {
  public static final throw_name throwName = new throw_name();
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    Object object;
    if (paramArrayOfObject.length > 0) {
      object = paramArrayOfObject[0];
      if (object instanceof Throwable) {
        if (paramArrayOfObject.length == 1)
          prim_throw.throw_it(object); 
        throw new GenericError("bad arguments to throw");
      } 
    } else {
      throw new GenericError("bad arguments to throw");
    } 
    if (object instanceof Symbol)
      throw new NamedException((Symbol)object, paramArrayOfObject); 
    throw new GenericError("bad arguments to throw");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/throw_name.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
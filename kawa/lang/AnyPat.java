package kawa.lang;

import gnu.lists.Consumer;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class AnyPat extends Pattern implements Printable, Externalizable {
  public static AnyPat make() {
    return new AnyPat();
  }
  
  public boolean match(Object paramObject, Object[] paramArrayOfObject, int paramInt) {
    paramArrayOfObject[paramInt] = paramObject;
    return true;
  }
  
  public void print(Consumer paramConsumer) {
    paramConsumer.write("#<match any>");
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {}
  
  public int varCount() {
    return 1;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/AnyPat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class EofClass implements Externalizable {
  public static final EofClass eofValue = new EofClass();
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {}
  
  public Object readResolve() throws ObjectStreamException {
    return Sequence.eofValue;
  }
  
  public final String toString() {
    return "#!eof";
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/EofClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.mapping;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class SimpleSymbol extends Symbol {
  public SimpleSymbol() {}
  
  public SimpleSymbol(String paramString) {
    super(Namespace.EmptyNamespace, paramString);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.name = ((String)paramObjectInput.readObject()).intern();
  }
  
  public Object readResolve() throws ObjectStreamException {
    return Namespace.EmptyNamespace.getSymbol(getName().intern());
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(getName());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/SimpleSymbol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
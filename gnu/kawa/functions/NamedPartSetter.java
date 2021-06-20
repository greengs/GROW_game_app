package gnu.kawa.functions;

import gnu.mapping.Procedure;
import gnu.mapping.Setter;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

class NamedPartSetter extends Setter implements Externalizable {
  public NamedPartSetter(NamedPart paramNamedPart) {
    super((Procedure)paramNamedPart);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateNamedPartSetter");
  }
  
  Procedure getGetter() {
    return this.getter;
  }
  
  public int numArgs() {
    return (((NamedPart)this.getter).kind == 'D') ? 8193 : -4096;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.getter = (Procedure)paramObjectInput.readObject();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.getter);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/NamedPartSetter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
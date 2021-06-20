package gnu.kawa.functions;

import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class GetNamedInstancePart extends ProcedureN implements Externalizable, HasSetter {
  boolean isField;
  
  String pname;
  
  public GetNamedInstancePart() {
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateGetNamedInstancePart");
  }
  
  public GetNamedInstancePart(String paramString) {
    this();
    setPartName(paramString);
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    checkArgCount((Procedure)this, paramArrayOfObject.length);
    if (this.isField)
      return SlotGet.field(paramArrayOfObject[0], this.pname); 
    Object[] arrayOfObject = new Object[paramArrayOfObject.length + 1];
    arrayOfObject[0] = paramArrayOfObject[0];
    arrayOfObject[1] = this.pname;
    System.arraycopy(paramArrayOfObject, 1, arrayOfObject, 2, paramArrayOfObject.length - 1);
    return Invoke.invoke.applyN(arrayOfObject);
  }
  
  public Procedure getSetter() {
    if (!this.isField)
      throw new RuntimeException("no setter for instance method call"); 
    return (Procedure)new SetNamedInstancePart(this.pname);
  }
  
  public int numArgs() {
    return this.isField ? 4097 : -4095;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    setPartName((String)paramObjectInput.readObject());
  }
  
  public void setPartName(String paramString) {
    setName("get-instance-part:" + paramString);
    if (paramString.length() > 1 && paramString.charAt(0) == '.') {
      this.isField = true;
      this.pname = paramString.substring(1);
      return;
    } 
    this.isField = false;
    this.pname = paramString;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    String str;
    if (this.isField) {
      str = "." + this.pname;
    } else {
      str = this.pname;
    } 
    paramObjectOutput.writeObject(str);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/GetNamedInstancePart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
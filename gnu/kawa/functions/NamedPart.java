package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.CallContext;
import gnu.mapping.HasSetter;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import kawa.standard.Scheme;

class NamedPart extends ProcedureN implements HasSetter, Externalizable {
  Object container;
  
  char kind;
  
  Object member;
  
  MethodProc methods;
  
  public NamedPart(Object paramObject1, Object paramObject2, char paramChar) {
    this.container = paramObject1;
    this.member = paramObject2;
    this.kind = paramChar;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateNamedPart");
  }
  
  public NamedPart(Object paramObject, String paramString, char paramChar, MethodProc paramMethodProc) {
    this(paramObject, paramString, paramChar);
    this.methods = paramMethodProc;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    apply(paramCallContext.getArgs(), paramCallContext);
  }
  
  public void apply(Object[] paramArrayOfObject, CallContext paramCallContext) throws Throwable {
    if (this.kind == 'S') {
      this.methods.checkN(paramArrayOfObject, paramCallContext);
      return;
    } 
    if (this.kind == 'M') {
      int i = paramArrayOfObject.length;
      Object[] arrayOfObject = new Object[i + 1];
      arrayOfObject[0] = this.container;
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 1, i);
      this.methods.checkN(arrayOfObject, paramCallContext);
      return;
    } 
    paramCallContext.writeValue(applyN(paramArrayOfObject));
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    Object[] arrayOfObject;
    switch (this.kind) {
      default:
        throw new Error("unknown part " + this.member + " in " + this.container);
      case 'I':
        return Scheme.instanceOf.apply2(paramArrayOfObject[0], this.container);
      case 'C':
        return Convert.as.apply2(this.container, paramArrayOfObject[0]);
      case 'N':
        arrayOfObject = new Object[paramArrayOfObject.length + 1];
        arrayOfObject[0] = this.container;
        System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 1, paramArrayOfObject.length);
        return Invoke.make.applyN(arrayOfObject);
      case 'S':
        return this.methods.applyN(paramArrayOfObject);
      case 'M':
        arrayOfObject = new Object[paramArrayOfObject.length + 1];
        arrayOfObject[0] = this.container;
        System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 1, paramArrayOfObject.length);
        return this.methods.applyN(arrayOfObject);
      case 'D':
        break;
    } 
    String str = this.member.toString().substring(1);
    return (paramArrayOfObject.length == 0) ? SlotGet.staticField(this.container, str) : SlotGet.field(((Type)this.container).coerceFromObject(paramArrayOfObject[0]), str);
  }
  
  public Procedure getSetter() {
    if (this.kind == 'D')
      return (Procedure)new NamedPartSetter(this); 
    throw new RuntimeException("procedure '" + getName() + "' has no setter");
  }
  
  public int numArgs() {
    return (this.kind == 'I' || this.kind == 'C') ? 4097 : ((this.kind == 'D') ? 4096 : -4096);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.kind = paramObjectInput.readChar();
    this.container = paramObjectInput.readObject();
    this.member = paramObjectInput.readObject();
  }
  
  public void set0(Object paramObject) throws Throwable {
    switch (this.kind) {
      default:
        throw new Error("invalid setter for " + this);
      case 'D':
        break;
    } 
    String str = this.member.toString().substring(1);
    SlotSet.setStaticField(this.container, str, paramObject);
  }
  
  public void set1(Object paramObject1, Object paramObject2) throws Throwable {
    switch (this.kind) {
      default:
        throw new Error("invalid setter for " + this);
      case 'D':
        break;
    } 
    String str = this.member.toString().substring(1);
    SlotSet.setField(((Type)this.container).coerceFromObject(paramObject1), str, paramObject2);
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.container);
    paramObjectOutput.writeObject(this.member);
    paramObjectOutput.writeChar(this.kind);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/NamedPart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
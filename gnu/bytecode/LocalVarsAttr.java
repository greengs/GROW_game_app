package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class LocalVarsAttr extends Attribute {
  public Scope current_scope;
  
  private Method method;
  
  Scope parameter_scope;
  
  Variable[] used;
  
  public LocalVarsAttr(CodeAttr paramCodeAttr) {
    super("LocalVariableTable");
    addToFrontOf(paramCodeAttr);
    this.method = (Method)paramCodeAttr.getContainer();
    paramCodeAttr.locals = this;
  }
  
  public LocalVarsAttr(Method paramMethod) {
    super("LocalVariableTable");
    CodeAttr codeAttr = paramMethod.code;
    this.method = paramMethod;
    codeAttr.locals = this;
  }
  
  public VarEnumerator allVars() {
    return new VarEnumerator(this.parameter_scope);
  }
  
  public void assignConstants(ClassType paramClassType) {
    super.assignConstants(paramClassType);
    VarEnumerator varEnumerator = allVars();
    while (true) {
      Variable variable = varEnumerator.nextVar();
      if (variable != null) {
        if (variable.isSimple() && variable.name != null) {
          if (variable.name_index == 0)
            variable.name_index = (paramClassType.getConstants().addUtf8(variable.getName())).index; 
          if (variable.signature_index == 0)
            variable.signature_index = (paramClassType.getConstants().addUtf8(variable.getType().getSignature())).index; 
        } 
        continue;
      } 
      break;
    } 
  }
  
  public void enterScope(Scope paramScope) {
    paramScope.linkChild(this.current_scope);
    this.current_scope = paramScope;
    CodeAttr codeAttr = this.method.getCode();
    for (Variable variable = paramScope.firstVar(); variable != null; variable = variable.nextVar()) {
      if (variable.isSimple())
        if (!variable.isAssigned()) {
          variable.allocateLocal(codeAttr);
        } else if (this.used[variable.offset] == null) {
          this.used[variable.offset] = variable;
        } else if (this.used[variable.offset] != variable) {
          throw new Error("inconsistent local variable assignments for " + variable + " != " + this.used[variable.offset]);
        }  
    } 
  }
  
  public final int getCount() {
    int i = 0;
    VarEnumerator varEnumerator = allVars();
    while (true) {
      Variable variable = varEnumerator.nextVar();
      if (variable != null) {
        if (variable.shouldEmit())
          i++; 
        continue;
      } 
      return i;
    } 
  }
  
  public final int getLength() {
    return getCount() * 10 + 2;
  }
  
  public final Method getMethod() {
    return this.method;
  }
  
  public final boolean isEmpty() {
    VarEnumerator varEnumerator = allVars();
    while (true) {
      Variable variable = varEnumerator.nextVar();
      if (variable != null) {
        if (variable.isSimple() && variable.name != null)
          return false; 
        continue;
      } 
      return true;
    } 
  }
  
  public void preserveVariablesUpto(Scope paramScope) {
    for (Scope scope = this.current_scope; scope != paramScope; scope = scope.parent)
      scope.preserved = true; 
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter) {
    VarEnumerator varEnumerator = allVars();
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", count: ");
    paramClassTypeWriter.println(getCount());
    varEnumerator.reset();
    while (true) {
      Variable variable = varEnumerator.nextVar();
      if (variable != null) {
        if (variable.isSimple() && variable.name != null) {
          paramClassTypeWriter.print("  slot#");
          paramClassTypeWriter.print(variable.offset);
          paramClassTypeWriter.print(": name: ");
          paramClassTypeWriter.printOptionalIndex(variable.name_index);
          paramClassTypeWriter.print(variable.getName());
          paramClassTypeWriter.print(", type: ");
          paramClassTypeWriter.printOptionalIndex(variable.signature_index);
          paramClassTypeWriter.printSignature(variable.getType());
          paramClassTypeWriter.print(" (pc: ");
          Scope scope = variable.scope;
          if (scope != null && scope.start != null && scope.end != null) {
            int i = scope.start.position;
            if (i >= 0) {
              int j = scope.end.position;
              if (j >= 0) {
                paramClassTypeWriter.print(i);
                paramClassTypeWriter.print(" length: ");
                paramClassTypeWriter.print(j - i);
                continue;
              } 
            } 
          } 
          paramClassTypeWriter.print("unknown");
          continue;
        } 
        continue;
      } 
      break;
      paramClassTypeWriter.println(')');
    } 
  }
  
  public void write(DataOutputStream paramDataOutputStream) throws IOException {
    VarEnumerator varEnumerator = allVars();
    paramDataOutputStream.writeShort(getCount());
    varEnumerator.reset();
    while (true) {
      Variable variable = varEnumerator.nextVar();
      if (variable != null) {
        if (variable.shouldEmit()) {
          Scope scope = variable.scope;
          int i = scope.start.position;
          int j = scope.end.position;
          paramDataOutputStream.writeShort(i);
          paramDataOutputStream.writeShort(j - i);
          paramDataOutputStream.writeShort(variable.name_index);
          paramDataOutputStream.writeShort(variable.signature_index);
          paramDataOutputStream.writeShort(variable.offset);
        } 
        continue;
      } 
      break;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/LocalVarsAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
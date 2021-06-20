package gnu.bytecode;

public class Scope {
  Label end;
  
  Scope firstChild;
  
  Scope lastChild;
  
  Variable last_var;
  
  Scope nextSibling;
  
  Scope parent;
  
  boolean preserved;
  
  Label start;
  
  Variable vars;
  
  public Scope() {}
  
  public Scope(Label paramLabel1, Label paramLabel2) {
    this.start = paramLabel1;
    this.end = paramLabel2;
  }
  
  static boolean equals(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    if (paramArrayOfbyte1.length != paramArrayOfbyte2.length)
      return false; 
    if (paramArrayOfbyte1 == paramArrayOfbyte2)
      return true; 
    int i = paramArrayOfbyte1.length;
    while (true) {
      int j = i - 1;
      if (j >= 0) {
        i = j;
        if (paramArrayOfbyte1[j] != paramArrayOfbyte2[j])
          return false; 
        continue;
      } 
      return true;
    } 
  }
  
  public Variable addVariable(CodeAttr paramCodeAttr, Type paramType, String paramString) {
    Variable variable = new Variable(paramString, paramType);
    addVariable(paramCodeAttr, variable);
    return variable;
  }
  
  public void addVariable(CodeAttr paramCodeAttr, Variable paramVariable) {
    addVariable(paramVariable);
    if (paramVariable.isSimple() && paramCodeAttr != null)
      paramVariable.allocateLocal(paramCodeAttr); 
  }
  
  public void addVariable(Variable paramVariable) {
    if (this.last_var == null) {
      this.vars = paramVariable;
    } else {
      this.last_var.next = paramVariable;
    } 
    this.last_var = paramVariable;
    paramVariable.scope = this;
  }
  
  public void addVariableAfter(Variable paramVariable1, Variable paramVariable2) {
    if (paramVariable1 == null) {
      paramVariable2.next = this.vars;
      this.vars = paramVariable2;
    } else {
      paramVariable2.next = paramVariable1.next;
      paramVariable1.next = paramVariable2;
    } 
    if (this.last_var == paramVariable1)
      this.last_var = paramVariable2; 
    if (paramVariable2.next == paramVariable2)
      throw new Error("cycle"); 
    paramVariable2.scope = this;
  }
  
  public VarEnumerator allVars() {
    return new VarEnumerator(this);
  }
  
  public final Variable firstVar() {
    return this.vars;
  }
  
  void freeLocals(CodeAttr paramCodeAttr) {
    if (!this.preserved) {
      for (Variable variable = this.vars; variable != null; variable = variable.next) {
        if (variable.isSimple() && !variable.dead())
          variable.freeLocal(paramCodeAttr); 
      } 
      Scope scope = this.firstChild;
      while (true) {
        if (scope != null) {
          if (scope.preserved) {
            scope.preserved = false;
            scope.freeLocals(paramCodeAttr);
          } 
          scope = scope.nextSibling;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public Variable getVariable(int paramInt) {
    Variable variable = this.vars;
    while (true) {
      if (--paramInt >= 0) {
        variable = variable.next;
        continue;
      } 
      return variable;
    } 
  }
  
  public void linkChild(Scope paramScope) {
    this.parent = paramScope;
    if (paramScope == null)
      return; 
    if (paramScope.lastChild == null) {
      paramScope.firstChild = this;
    } else {
      paramScope.lastChild.nextSibling = this;
    } 
    paramScope.lastChild = this;
  }
  
  public Variable lookup(String paramString) {
    for (Variable variable = this.vars; variable != null; variable = variable.next) {
      if (paramString.equals(variable.name))
        return variable; 
    } 
    return null;
  }
  
  public void noteStartFunction(CodeAttr paramCodeAttr) {
    setStartPC(paramCodeAttr);
    this.start.setTypes(paramCodeAttr);
  }
  
  public void setStartPC(CodeAttr paramCodeAttr) {
    if (this.start == null)
      this.start = new Label(); 
    boolean bool = paramCodeAttr.reachableHere();
    this.start.define(paramCodeAttr);
    paramCodeAttr.setReachable(bool);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/Scope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
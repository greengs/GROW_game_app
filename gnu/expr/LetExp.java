package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class LetExp extends ScopeExp {
  public Expression body;
  
  public Expression[] inits;
  
  public LetExp(Expression[] paramArrayOfExpression) {
    this.inits = paramArrayOfExpression;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    Object[][] arrayOfObject1;
    setIndexes();
    int j = ScopeExp.nesting(this);
    Object[] arrayOfObject3 = new Object[this.frameSize];
    Object[][] arrayOfObject2 = paramCallContext.evalFrames;
    if (arrayOfObject2 == null) {
      arrayOfObject1 = new Object[j + 10][];
      paramCallContext.evalFrames = arrayOfObject1;
    } else {
      arrayOfObject1 = arrayOfObject2;
      if (j >= arrayOfObject2.length) {
        Object[][] arrayOfObject = new Object[j + 10][];
        System.arraycopy(arrayOfObject2, 0, arrayOfObject, 0, arrayOfObject2.length);
        arrayOfObject1 = arrayOfObject;
        paramCallContext.evalFrames = arrayOfObject;
      } 
    } 
    Object[] arrayOfObject4 = arrayOfObject1[j];
    arrayOfObject1[j] = arrayOfObject3;
    int i = 0;
    try {
      Declaration declaration = firstDecl();
    } finally {
      arrayOfObject1[j] = arrayOfObject4;
    } 
    this.body.apply(paramCallContext);
    arrayOfObject1[j] = arrayOfObject4;
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    CodeAttr codeAttr = paramCompilation.getCode();
    Declaration declaration = firstDecl();
    int i = 0;
    while (i < this.inits.length) {
      Expression expression1;
      Target target;
      Expression expression2 = this.inits[i];
      boolean bool = declaration.needsInit();
      if (bool && declaration.isSimple())
        declaration.allocateVariable(codeAttr); 
      if (!bool || (declaration.isIndirectBinding() && expression2 == QuoteExp.undefined_exp)) {
        target = Target.Ignore;
        expression1 = expression2;
      } else {
        Type type = declaration.getType();
        Target target1 = CheckedTarget.getInstance(declaration);
        expression1 = expression2;
        target = target1;
        if (expression2 == QuoteExp.undefined_exp)
          if (type instanceof gnu.bytecode.PrimType) {
            expression1 = new QuoteExp(new Byte((byte)0));
            target = target1;
          } else {
            expression1 = expression2;
            target = target1;
            if (type != null) {
              expression1 = expression2;
              target = target1;
              if (type != Type.pointer_type) {
                expression1 = QuoteExp.nullExp;
                target = target1;
              } 
            } 
          }  
      } 
      expression1.compileWithPosition(paramCompilation, target);
      i++;
      declaration = declaration.nextDecl();
    } 
    codeAttr.enterScope(getVarScope());
    store_rest(paramCompilation, 0, firstDecl());
    this.body.compileWithPosition(paramCompilation, paramTarget);
    popScope(codeAttr);
  }
  
  protected Object evalVariable(int paramInt, CallContext paramCallContext) throws Throwable {
    return this.inits[paramInt].eval(paramCallContext);
  }
  
  public Expression getBody() {
    return this.body;
  }
  
  public final Type getType() {
    return this.body.getType();
  }
  
  protected boolean mustCompile() {
    return false;
  }
  
  public void print(OutPort paramOutPort) {
    print(paramOutPort, "(Let", ")");
  }
  
  public void print(OutPort paramOutPort, String paramString1, String paramString2) {
    paramOutPort.startLogicalBlock(paramString1 + "#" + this.id, paramString2, 2);
    paramOutPort.writeSpaceFill();
    printLineColumn(paramOutPort);
    paramOutPort.startLogicalBlock("(", false, ")");
    Declaration declaration = firstDecl();
    for (int i = 0; declaration != null; i = j) {
      if (i)
        paramOutPort.writeSpaceFill(); 
      paramOutPort.startLogicalBlock("(", false, ")");
      declaration.printInfo(paramOutPort);
      int j = i;
      if (this.inits != null) {
        paramOutPort.writeSpaceFill();
        paramOutPort.print('=');
        paramOutPort.writeSpaceFill();
        if (i >= this.inits.length) {
          paramOutPort.print("<missing init>");
        } else if (this.inits[i] == null) {
          paramOutPort.print("<null>");
        } else {
          this.inits[i].print(paramOutPort);
        } 
        j = i + 1;
      } 
      paramOutPort.endLogicalBlock(")");
      declaration = declaration.nextDecl();
    } 
    paramOutPort.endLogicalBlock(")");
    paramOutPort.writeSpaceLinear();
    if (this.body == null) {
      paramOutPort.print("<null body>");
    } else {
      this.body.print(paramOutPort);
    } 
    paramOutPort.endLogicalBlock(paramString2);
  }
  
  public void setBody(Expression paramExpression) {
    this.body = paramExpression;
  }
  
  void store_rest(Compilation paramCompilation, int paramInt, Declaration paramDeclaration) {
    if (paramDeclaration != null) {
      store_rest(paramCompilation, paramInt + 1, paramDeclaration.nextDecl());
      if (paramDeclaration.needsInit()) {
        if (paramDeclaration.isIndirectBinding()) {
          CodeAttr codeAttr = paramCompilation.getCode();
          if (this.inits[paramInt] == QuoteExp.undefined_exp) {
            Object object = paramDeclaration.getSymbol();
            paramCompilation.compileConstant(object, Target.pushObject);
            codeAttr.emitInvokeStatic(BindingInitializer.makeLocationMethod(object));
          } else {
            paramDeclaration.pushIndirectBinding(paramCompilation);
          } 
        } 
        paramDeclaration.compileStore(paramCompilation);
      } 
    } 
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitLetExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    visitInitializers(paramExpVisitor, paramD);
    if (paramExpVisitor.exitValue == null)
      this.body = paramExpVisitor.visitAndUpdate(this.body, paramD); 
  }
  
  public <R, D> void visitInitializers(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    Declaration declaration = firstDecl();
    int i = 0;
    while (i < this.inits.length) {
      Expression expression1 = this.inits[i];
      if (expression1 == null)
        throw new Error("null1 init for " + this + " i:" + i + " d:" + declaration); 
      Expression expression2 = paramExpVisitor.visitAndUpdate(expression1, paramD);
      if (expression2 == null)
        throw new Error("null2 init for " + this + " was:" + expression1); 
      this.inits[i] = expression2;
      if (declaration.value == expression1)
        declaration.value = expression2; 
      i++;
      declaration = declaration.nextDecl();
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/LetExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
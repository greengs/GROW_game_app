package gnu.expr;

import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.lists.AbstractFormat;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.mapping.WrongArguments;
import gnu.text.SourceLocator;

public class QuoteExp extends Expression {
  public static final int EXPLICITLY_TYPED = 2;
  
  public static final int SHARED_CONSTANT = 4;
  
  public static QuoteExp abstractExp;
  
  public static final QuoteExp classObjectExp;
  
  public static QuoteExp falseExp;
  
  public static QuoteExp nullExp;
  
  public static QuoteExp trueExp;
  
  public static QuoteExp undefined_exp = makeShared(Special.undefined);
  
  public static QuoteExp voidExp;
  
  protected Type type;
  
  Object value;
  
  static {
    abstractExp = makeShared(Special.abstractSpecial);
    voidExp = makeShared(Values.empty, (Type)Type.voidType);
    trueExp = makeShared(Boolean.TRUE);
    falseExp = makeShared(Boolean.FALSE);
    nullExp = makeShared((Object)null, (Type)Type.nullType);
    classObjectExp = makeShared(Type.objectType);
  }
  
  public QuoteExp(Object paramObject) {
    this.value = paramObject;
  }
  
  public QuoteExp(Object paramObject, Type paramType) {
    this.value = paramObject;
    setType(paramType);
  }
  
  public static QuoteExp getInstance(Object paramObject) {
    return getInstance(paramObject, (SourceLocator)null);
  }
  
  public static QuoteExp getInstance(Object paramObject, SourceLocator paramSourceLocator) {
    if (paramObject == null)
      return nullExp; 
    if (paramObject == Type.pointer_type)
      return classObjectExp; 
    if (paramObject == Special.undefined)
      return undefined_exp; 
    if (paramObject == Values.empty)
      return voidExp; 
    if (paramObject instanceof Boolean)
      return ((Boolean)paramObject).booleanValue() ? trueExp : falseExp; 
    paramObject = new QuoteExp(paramObject);
    if (paramSourceLocator != null)
      paramObject.setLocation(paramSourceLocator); 
    return (QuoteExp)paramObject;
  }
  
  static QuoteExp makeShared(Object paramObject) {
    paramObject = new QuoteExp(paramObject);
    paramObject.setFlag(4);
    return (QuoteExp)paramObject;
  }
  
  static QuoteExp makeShared(Object paramObject, Type paramType) {
    paramObject = new QuoteExp(paramObject, paramType);
    paramObject.setFlag(4);
    return (QuoteExp)paramObject;
  }
  
  public void apply(CallContext paramCallContext) {
    paramCallContext.writeValue(this.value);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    if (this.type == null || this.type == Type.pointer_type || paramTarget instanceof IgnoreTarget || (this.type instanceof gnu.bytecode.ObjectType && this.type.isInstance(this.value))) {
      paramCompilation.compileConstant(this.value, paramTarget);
      return;
    } 
    paramCompilation.compileConstant(this.value, StackTarget.getInstance(this.type));
    paramTarget.compileFromStack(paramCompilation, this.type);
  }
  
  public Expression deepCopy(IdentityHashTable paramIdentityHashTable) {
    return this;
  }
  
  public final Type getRawType() {
    return this.type;
  }
  
  public final Type getType() {
    return (Type)((this.type != null) ? this.type : ((this.value == Values.empty) ? Type.voidType : ((this.value == null) ? Type.nullType : ((this == undefined_exp) ? Type.pointer_type : Type.make(this.value.getClass())))));
  }
  
  public final Object getValue() {
    return this.value;
  }
  
  public boolean isExplicitlyTyped() {
    return getFlag(2);
  }
  
  public boolean isSharedConstant() {
    return getFlag(4);
  }
  
  protected boolean mustCompile() {
    return false;
  }
  
  public void print(OutPort paramOutPort) {
    paramOutPort.startLogicalBlock("(Quote", ")", 2);
    paramOutPort.writeSpaceLinear();
    Object object = this.value;
    null = object;
    if (object instanceof Expression)
      null = object.toString(); 
    object = paramOutPort.objectFormat;
    try {
      paramOutPort.objectFormat = Language.getDefaultLanguage().getFormat(true);
      paramOutPort.print(null);
      if (this.type != null) {
        paramOutPort.print(" ::");
        paramOutPort.print(this.type.getName());
      } 
      paramOutPort.objectFormat = (AbstractFormat)object;
      return;
    } finally {
      paramOutPort.objectFormat = (AbstractFormat)object;
    } 
  }
  
  public void setType(Type paramType) {
    this.type = paramType;
    setFlag(2);
  }
  
  public boolean side_effects() {
    return false;
  }
  
  public String toString() {
    return "QuoteExp[" + this.value + "]";
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration) {
    String str;
    if (this != undefined_exp) {
      MethodProc methodProc;
      Object object = getValue();
      if (!(object instanceof Procedure)) {
        if (paramDeclaration == null || object == null) {
          String str1 = "called value is not a procedure";
          return paramInlineCalls.noteError(str1);
        } 
        str = "calling " + paramDeclaration.getName() + " which is a " + object.getClass().getName();
        return paramInlineCalls.noteError(str);
      } 
      Procedure procedure = (Procedure)object;
      int j = str.getArgCount();
      object = WrongArguments.checkArgCount(procedure, j);
      if (object != null)
        return paramInlineCalls.noteError((String)object); 
      object = paramInlineCalls.maybeInline((ApplyExp)str, paramType, procedure);
      if (object != null)
        return (Expression)object; 
      Expression[] arrayOfExpression = ((ApplyExp)str).args;
      if (procedure instanceof MethodProc) {
        methodProc = (MethodProc)procedure;
      } else {
        methodProc = null;
      } 
      for (int i = 0; i < j; i++) {
        if (methodProc != null) {
          object = methodProc.getParameterType(i);
        } else {
          object = null;
        } 
        Object object1 = object;
        if (i == j - 1) {
          object1 = object;
          if (object != null) {
            object1 = object;
            if (methodProc.maxArgs() < 0) {
              object1 = object;
              if (i == methodProc.minArgs())
                object1 = null; 
            } 
          } 
        } 
        arrayOfExpression[i] = paramInlineCalls.visit(arrayOfExpression[i], (Type)object1);
      } 
      if (str.getFlag(4)) {
        object = str.inlineIfConstant(procedure, paramInlineCalls);
        if (object != str)
          return paramInlineCalls.visit((Expression)object, paramType); 
      } 
      Compilation compilation = paramInlineCalls.getCompilation();
      if (compilation.inlineOk(procedure)) {
        if (ApplyExp.asInlineable(procedure) != null)
          return (Expression)((str.getFunction() != this) ? (new ApplyExp(this, str.getArgs())).setLine((Expression)str) : str); 
        PrimProcedure primProcedure = PrimProcedure.getMethodFor(procedure, paramDeclaration, ((ApplyExp)str).args, compilation.getLanguage());
        if (primProcedure != null) {
          ApplyExp applyExp;
          if (primProcedure.getStaticFlag() || paramDeclaration == null) {
            applyExp = new ApplyExp((Procedure)primProcedure, ((ApplyExp)str).args);
            return applyExp.setLine((Expression)str);
          } 
          if (paramDeclaration.base != null) {
            Expression[] arrayOfExpression1 = new Expression[j + 1];
            System.arraycopy(str.getArgs(), 0, arrayOfExpression1, 1, j);
            arrayOfExpression1[0] = new ReferenceExp(paramDeclaration.base);
            applyExp = new ApplyExp((Procedure)applyExp, arrayOfExpression1);
            return applyExp.setLine((Expression)str);
          } 
        } 
      } 
    } 
    return (Expression)str;
  }
  
  public final Object valueIfConstant() {
    return this.value;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitQuoteExp(this, paramD);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/QuoteExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
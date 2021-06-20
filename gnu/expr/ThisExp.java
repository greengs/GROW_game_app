package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;

public class ThisExp extends ReferenceExp {
  static int EVAL_TO_CONTEXT;
  
  public static final String THIS_NAME = new String("$this$");
  
  ScopeExp context;
  
  static {
    EVAL_TO_CONTEXT = 2;
  }
  
  public ThisExp() {
    super(THIS_NAME);
  }
  
  public ThisExp(ClassType paramClassType) {
    this(new Declaration(THIS_NAME, (Type)paramClassType));
  }
  
  public ThisExp(Declaration paramDeclaration) {
    super(THIS_NAME, paramDeclaration);
  }
  
  public ThisExp(ScopeExp paramScopeExp) {
    super(THIS_NAME);
    this.context = paramScopeExp;
  }
  
  public static ThisExp makeGivingContext(ScopeExp paramScopeExp) {
    ThisExp thisExp = new ThisExp(paramScopeExp);
    thisExp.flags |= EVAL_TO_CONTEXT;
    return thisExp;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    if (isForContext()) {
      paramCallContext.writeValue(this.context);
      return;
    } 
    super.apply(paramCallContext);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    if (paramTarget instanceof IgnoreTarget)
      return; 
    if (isForContext()) {
      CodeAttr codeAttr = paramCompilation.getCode();
      if (paramCompilation.method.getStaticFlag()) {
        codeAttr.emitGetStatic(paramCompilation.moduleInstanceMainField);
      } else {
        codeAttr.emitPushThis();
      } 
      paramTarget.compileFromStack(paramCompilation, getType());
      return;
    } 
    super.compile(paramCompilation, paramTarget);
  }
  
  public ScopeExp getContextScope() {
    return this.context;
  }
  
  public final Type getType() {
    return (Type)((this.binding != null) ? this.binding.getType() : ((this.context instanceof ClassExp || this.context instanceof ModuleExp) ? this.context.getType() : Type.pointer_type));
  }
  
  public final boolean isForContext() {
    return ((this.flags & EVAL_TO_CONTEXT) != 0);
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitThisExp(this, paramD);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ThisExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
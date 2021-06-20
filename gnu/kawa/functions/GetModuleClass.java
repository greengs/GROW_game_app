package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BindingInitializer;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.Target;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.text.URLPath;

public class GetModuleClass extends ProcedureN implements Inlineable {
  private static Symbol CLASS_RESOURCE_NAME;
  
  public static final GetModuleClass getModuleClass = new GetModuleClass();
  
  public static final GetModuleClass getModuleUri = new GetModuleClass();
  
  public static final GetModuleClass getModuleUriDummy = new GetModuleClass();
  
  static final Method maker;
  
  static final ClassType typeURLPath = ClassType.make("gnu.text.URLPath");
  
  static {
    maker = typeURLPath.getDeclaredMethod("classResourcePath", 1);
    CLASS_RESOURCE_NAME = Namespace.getDefaultSymbol("$class_resource_URL$");
  }
  
  public static Expression getModuleClassURI(Compilation paramCompilation) {
    Declaration declaration2 = paramCompilation.mainLambda.lookup(CLASS_RESOURCE_NAME);
    Declaration declaration1 = declaration2;
    if (declaration2 == null) {
      ApplyExp applyExp;
      Declaration declaration = new Declaration(CLASS_RESOURCE_NAME, (Type)typeURLPath);
      declaration.setFlag(536889344L);
      if (paramCompilation.immediate) {
        URLPath uRLPath;
        Path path2 = paramCompilation.minfo.getSourceAbsPath();
        Path path1 = path2;
        if (path2 == null)
          path1 = Path.currentPath(); 
        path2 = path1;
        if (!(path1 instanceof URLPath))
          uRLPath = URLPath.valueOf(path1.toURL()); 
        QuoteExp quoteExp = QuoteExp.getInstance(uRLPath);
      } else {
        applyExp = new ApplyExp((Procedure)getModuleClass, Expression.noExpressions);
        applyExp = new ApplyExp(maker, new Expression[] { (Expression)applyExp });
      } 
      declaration.setValue((Expression)applyExp);
      paramCompilation.mainLambda.add(null, declaration);
      declaration1 = declaration;
    } 
    ReferenceExp referenceExp = new ReferenceExp(declaration1);
    return (Expression)(paramCompilation.immediate ? referenceExp : new ApplyExp((Procedure)getModuleUriDummy, new Expression[] { (Expression)referenceExp }));
  }
  
  public Object applyN(Object[] paramArrayOfObject) {
    throw new Error("get-module-class must be inlined");
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Declaration declaration;
    Expression expression;
    if (this == getModuleUriDummy) {
      ReferenceExp referenceExp = (ReferenceExp)paramApplyExp.getArgs()[0];
      referenceExp.compile(paramCompilation, paramTarget);
      declaration = referenceExp.getBinding();
      expression = declaration.getValue();
      if (expression != null) {
        BindingInitializer.create(declaration, expression, paramCompilation);
        declaration.setValue(null);
      } 
      return;
    } 
    paramCompilation.loadClassRef((ObjectType)paramCompilation.mainClass);
    if (this == getModuleUri)
      paramCompilation.getCode().emitInvoke(maker); 
    expression.compileFromStack(paramCompilation, declaration.getType());
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)((this == getModuleClass) ? Type.javalangClassType : typeURLPath);
  }
  
  public int numArgs() {
    return (this == getModuleUriDummy) ? 4097 : 0;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/GetModuleClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
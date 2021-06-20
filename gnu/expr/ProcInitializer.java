package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.mapping.Environment;
import gnu.mapping.PropertySet;
import gnu.mapping.Symbol;

public class ProcInitializer extends Initializer {
  LambdaExp proc;
  
  public ProcInitializer(LambdaExp paramLambdaExp, Compilation paramCompilation, Field paramField) {
    this.field = paramField;
    this.proc = paramLambdaExp;
    if (paramField.getStaticFlag()) {
      paramLambdaExp = paramCompilation.getModule();
    } else {
      paramLambdaExp = paramLambdaExp.getOwningLambda();
    } 
    if (paramLambdaExp instanceof ModuleExp && paramCompilation.isStatic()) {
      this.next = paramCompilation.clinitChain;
      paramCompilation.clinitChain = this;
      return;
    } 
    this.next = paramLambdaExp.initChain;
    paramLambdaExp.initChain = this;
  }
  
  public static void emitLoadModuleMethod(LambdaExp paramLambdaExp, Compilation paramCompilation) {
    int i;
    Object object;
    ModuleMethod moduleMethod;
    String str;
    LambdaExp lambdaExp;
    Declaration declaration = paramLambdaExp.nameDecl;
    if (declaration == null) {
      object = paramLambdaExp.getName();
    } else {
      object = declaration.getSymbol();
    } 
    Symbol symbol2 = null;
    Symbol symbol1 = symbol2;
    if (paramCompilation.immediate) {
      symbol1 = symbol2;
      if (object != null) {
        symbol1 = symbol2;
        if (declaration != null) {
          Environment environment = Environment.getCurrent();
          if (object instanceof Symbol) {
            symbol1 = (Symbol)object;
          } else {
            symbol1 = Symbol.make("", object.toString().intern());
          } 
          Object object1 = environment.get(symbol1, paramCompilation.getLanguage().getEnvPropertyFor(paramLambdaExp.nameDecl), null);
          symbol1 = symbol2;
          if (object1 instanceof ModuleMethod)
            moduleMethod = (ModuleMethod)object1; 
        } 
      } 
    } 
    CodeAttr codeAttr = paramCompilation.getCode();
    ClassType classType = Compilation.typeModuleMethod;
    if (moduleMethod == null) {
      codeAttr.emitNew(classType);
      codeAttr.emitDup(1);
      str = "<init>";
    } else {
      paramCompilation.compileConstant(str, Target.pushValue((Type)classType));
      str = "init";
    } 
    Method method = classType.getDeclaredMethod(str, 4);
    if (paramLambdaExp.getNeedsClosureEnv()) {
      lambdaExp = paramLambdaExp.getOwningLambda();
    } else {
      lambdaExp = paramCompilation.getModule();
    } 
    if (lambdaExp instanceof ClassExp && lambdaExp.staticLinkField != null) {
      codeAttr.emitLoad(codeAttr.getCurrentScope().getVariable(1));
    } else if (!(lambdaExp instanceof ModuleExp) || (paramCompilation.moduleClass == paramCompilation.mainClass && !paramCompilation.method.getStaticFlag())) {
      codeAttr.emitPushThis();
    } else {
      if (paramCompilation.moduleInstanceVar == null) {
        paramCompilation.moduleInstanceVar = codeAttr.locals.current_scope.addVariable(codeAttr, (Type)paramCompilation.moduleClass, "$instance");
        if (paramCompilation.moduleClass != paramCompilation.mainClass && !paramCompilation.isStatic()) {
          codeAttr.emitNew(paramCompilation.moduleClass);
          codeAttr.emitDup((Type)paramCompilation.moduleClass);
          codeAttr.emitInvokeSpecial(paramCompilation.moduleClass.constructor);
          paramCompilation.moduleInstanceMainField = paramCompilation.moduleClass.addField("$main", (Type)paramCompilation.mainClass, 0);
          codeAttr.emitDup((Type)paramCompilation.moduleClass);
          codeAttr.emitPushThis();
          codeAttr.emitPutField(paramCompilation.moduleInstanceMainField);
        } else {
          codeAttr.emitGetStatic(paramCompilation.moduleInstanceMainField);
        } 
        codeAttr.emitStore(paramCompilation.moduleInstanceVar);
      } 
      codeAttr.emitLoad(paramCompilation.moduleInstanceVar);
    } 
    codeAttr.emitPushInt(paramLambdaExp.getSelectorValue(paramCompilation));
    paramCompilation.compileConstant(object, Target.pushObject);
    int j = paramLambdaExp.min_args;
    if (paramLambdaExp.keywords == null) {
      i = paramLambdaExp.max_args;
    } else {
      i = -1;
    } 
    codeAttr.emitPushInt(i << 12 | j);
    codeAttr.emitInvoke(method);
    if (paramLambdaExp.properties != null) {
      j = paramLambdaExp.properties.length;
      for (i = 0; i < j; i += 2) {
        Object object1 = paramLambdaExp.properties[i];
        if (object1 != null && object1 != PropertySet.nameKey) {
          object = paramLambdaExp.properties[i + 1];
          codeAttr.emitDup(1);
          paramCompilation.compileConstant(object1);
          object1 = Target.pushObject;
          if (object instanceof Expression) {
            ((Expression)object).compile(paramCompilation, (Target)object1);
          } else {
            paramCompilation.compileConstant(object, (Target)object1);
          } 
          codeAttr.emitInvokeVirtual(ClassType.make("gnu.mapping.PropertySet").getDeclaredMethod("setProperty", 2));
        } 
      } 
    } 
  }
  
  public void emit(Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    if (!this.field.getStaticFlag())
      codeAttr.emitPushThis(); 
    emitLoadModuleMethod(this.proc, paramCompilation);
    if (this.field.getStaticFlag()) {
      codeAttr.emitPutStatic(this.field);
      return;
    } 
    codeAttr.emitPutField(this.field);
  }
  
  public void reportError(String paramString, Compilation paramCompilation) {
    String str1 = paramCompilation.getFileName();
    int i = paramCompilation.getLineNumber();
    int j = paramCompilation.getColumnNumber();
    paramCompilation.setLocation(this.proc);
    String str2 = this.proc.getName();
    StringBuffer stringBuffer = new StringBuffer(paramString);
    if (str2 == null) {
      stringBuffer.append("unnamed procedure");
    } else {
      stringBuffer.append("procedure ");
      stringBuffer.append(str2);
    } 
    paramCompilation.error('e', stringBuffer.toString());
    paramCompilation.setLine(str1, i, j);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ProcInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
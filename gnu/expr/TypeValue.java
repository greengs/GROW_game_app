package gnu.expr;

import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.Procedure;
import java.lang.reflect.Type;

public interface TypeValue extends Type {
  Expression convertValue(Expression paramExpression);
  
  void emitIsInstance(Variable paramVariable, Compilation paramCompilation, Target paramTarget);
  
  void emitTestIf(Variable paramVariable, Declaration paramDeclaration, Compilation paramCompilation);
  
  Procedure getConstructor();
  
  Type getImplementationType();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/TypeValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
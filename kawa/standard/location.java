package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ReferenceExp;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.mapping.Location;
import gnu.mapping.LocationProc;
import gnu.mapping.ProcLocation;
import gnu.mapping.Procedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class location extends Syntax {
  public static final location location = new location();
  
  private static ClassType thisType = ClassType.make("kawa.standard.location");
  
  public static Procedure makeLocationProc(Location paramLocation) {
    return (Procedure)new LocationProc(paramLocation);
  }
  
  public static Location makeProcLocation$V(Procedure paramProcedure, Object[] paramArrayOfObject) {
    int i = paramArrayOfObject.length;
    if (paramProcedure instanceof gnu.kawa.functions.ApplyToArgs && i > 0 && paramArrayOfObject[0] instanceof Procedure) {
      paramProcedure = (Procedure)paramArrayOfObject[0];
      if (paramProcedure instanceof LocationProc && i == 1)
        return ((LocationProc)paramProcedure).getLocation(); 
      Object[] arrayOfObject = new Object[i - 1];
      System.arraycopy(paramArrayOfObject, 1, arrayOfObject, 0, arrayOfObject.length);
      return (Location)new ProcLocation(paramProcedure, arrayOfObject);
    } 
    return (Location)((paramProcedure instanceof LocationProc && i == 0) ? ((LocationProc)paramProcedure).getLocation() : new ProcLocation(paramProcedure, paramArrayOfObject));
  }
  
  public static Expression rewrite(Expression paramExpression, Translator paramTranslator) {
    ReferenceExp referenceExp;
    Expression[] arrayOfExpression;
    if (paramExpression instanceof ReferenceExp) {
      referenceExp = (ReferenceExp)paramExpression;
      referenceExp.setDontDereference(true);
      Declaration declaration = referenceExp.getBinding();
      if (declaration != null) {
        declaration.maybeIndirectBinding((Compilation)paramTranslator);
        Declaration declaration1 = Declaration.followAliases(declaration);
        declaration1.setCanRead(true);
        declaration1.setCanWrite(true);
      } 
      return (Expression)referenceExp;
    } 
    if (referenceExp instanceof ApplyExp) {
      ApplyExp applyExp = (ApplyExp)referenceExp;
      arrayOfExpression = new Expression[(applyExp.getArgs()).length + 1];
      arrayOfExpression[0] = applyExp.getFunction();
      System.arraycopy(applyExp.getArgs(), 0, arrayOfExpression, 1, arrayOfExpression.length - 1);
      return (Expression)Invoke.makeInvokeStatic(thisType, "makeProcLocation", arrayOfExpression);
    } 
    return arrayOfExpression.syntaxError("invalid argument to location");
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    if (!(paramObject instanceof gnu.lists.Pair))
      return paramTranslator.syntaxError("missing argument to location"); 
    paramObject = paramObject;
    if (paramObject.getCdr() != LList.Empty)
      return paramTranslator.syntaxError("extra arguments to location"); 
    location location1 = location;
    paramObject = rewrite(paramTranslator.rewrite(paramObject.getCar()), paramTranslator);
    return (Expression)Invoke.makeInvokeStatic(thisType, "makeLocationProc", new Expression[] { (Expression)paramObject });
  }
  
  static {
    location.setName("location");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/location.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;

public class IsEqv extends Procedure2 {
  IsEq isEq;
  
  Language language;
  
  public IsEqv(Language paramLanguage, String paramString, IsEq paramIsEq) {
    this.language = paramLanguage;
    this.isEq = paramIsEq;
    setName(paramString);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompilationHelpers:validateIsEqv");
  }
  
  public static boolean apply(Object paramObject1, Object paramObject2) {
    return (paramObject1 == paramObject2) ? true : ((paramObject1 instanceof Number && paramObject2 instanceof Number) ? IsEqual.numberEquals((Number)paramObject1, (Number)paramObject2) : ((paramObject1 instanceof gnu.text.Char || paramObject1 instanceof gnu.mapping.Symbol) ? paramObject1.equals(paramObject2) : false));
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    return this.language.booleanObject(apply(paramObject1, paramObject2));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/IsEqv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
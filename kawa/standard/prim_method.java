package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Expression;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.ListPat;
import kawa.lang.Pattern;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class prim_method extends Syntax {
  public static final prim_method interface_method;
  
  public static final prim_method op1;
  
  private static Pattern pattern3;
  
  private static Pattern pattern4;
  
  public static final prim_method static_method;
  
  public static final prim_method virtual_method = new prim_method(182);
  
  int op_code;
  
  static {
    virtual_method.setName("primitive-virtual-method");
    static_method = new prim_method(184);
    static_method.setName("primitive-static-method");
    interface_method = new prim_method(185);
    interface_method.setName("primitive-interface-method");
    op1 = new prim_method();
    op1.setName("primitive-op1");
    pattern3 = (Pattern)new ListPat(3);
    pattern4 = (Pattern)new ListPat(4);
  }
  
  public prim_method() {}
  
  public prim_method(int paramInt) {
    this.op_code = paramInt;
  }
  
  int opcode() {
    return this.op_code;
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    Object[] arrayOfObject = new Object[4];
    if ((this.op_code == 0) ? pattern3.match(paramObject, arrayOfObject, 1) : pattern4.match(paramObject, arrayOfObject, 0)) {
      if (!(arrayOfObject[3] instanceof LList))
        return paramTranslator.syntaxError("missing/invalid parameter list in " + getName()); 
    } else {
      return paramTranslator.syntaxError("wrong number of arguments to " + getName() + "(opcode:" + this.op_code + ")");
    } 
    LList lList = (LList)arrayOfObject[3];
    int j = lList.size();
    Type[] arrayOfType = new Type[j];
    int i;
    for (i = 0; i < j; i++) {
      Pair pair = (Pair)lList;
      arrayOfType[i] = paramTranslator.exp2Type(pair);
      LList lList1 = (LList)pair.getCdr();
    } 
    Type type = paramTranslator.exp2Type(new Pair(arrayOfObject[2], null));
    if (this.op_code == 0) {
      paramObject = new PrimProcedure(((Number)arrayOfObject[1]).intValue(), type, arrayOfType);
      return (Expression)new QuoteExp(paramObject);
    } 
    Object object1 = null;
    paramObject = paramTranslator.exp2Type((Pair)paramObject);
    Object object = paramObject;
    if (paramObject != null)
      object = paramObject.getImplementationType(); 
    paramObject = object1;
    try {
      object = object;
      paramObject = object;
      object.getReflectClass();
      paramObject = object;
      if (arrayOfObject[1] instanceof Pair) {
        Pair pair = (Pair)arrayOfObject[1];
        if (pair.getCar() == "quote")
          arrayOfObject[1] = ((Pair)pair.getCdr()).getCar(); 
      } 
      paramObject = new PrimProcedure(this.op_code, (ClassType)paramObject, arrayOfObject[1].toString(), type, arrayOfType);
    } catch (Exception exception) {}
    return (Expression)new QuoteExp(paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/prim_method.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.mapping.ProcedureN;
import gnu.math.IntNum;

public abstract class ArithOp extends ProcedureN {
  static final int ADD = 1;
  
  public static final int AND = 13;
  
  public static final int ASHIFT_GENERAL = 9;
  
  public static final int ASHIFT_LEFT = 10;
  
  public static final int ASHIFT_RIGHT = 11;
  
  public static final int DIVIDE_GENERIC = 4;
  
  public static final int DIVIDE_INEXACT = 5;
  
  public static final int IOR = 14;
  
  public static final int LSHIFT_RIGHT = 12;
  
  public static final int MODULO = 8;
  
  static final int MUL = 3;
  
  public static final int NOT = 16;
  
  public static final int QUOTIENT = 6;
  
  public static final int QUOTIENT_EXACT = 7;
  
  static final int SUB = 2;
  
  public static final int XOR = 15;
  
  final int op;
  
  public ArithOp(String paramString, int paramInt) {
    super(paramString);
    this.op = paramInt;
  }
  
  public static int classify(Type paramType) {
    byte b = 4;
    if (paramType instanceof gnu.bytecode.PrimType) {
      char c = paramType.getSignature().charAt(0);
      return (c == 'V' || c == 'Z' || c == 'C') ? 0 : ((c == 'D' || c == 'F') ? 3 : b);
    } 
    return !paramType.isSubtype((Type)Arithmetic.typeIntNum) ? (paramType.isSubtype((Type)Arithmetic.typeDFloNum) ? 3 : (paramType.isSubtype((Type)Arithmetic.typeRealNum) ? 2 : (paramType.isSubtype((Type)Arithmetic.typeNumeric) ? 1 : 0))) : b;
  }
  
  public Object defaultResult() {
    return IntNum.zero();
  }
  
  public boolean isSideEffectFree() {
    return true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/ArithOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
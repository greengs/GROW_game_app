package gnu.kawa.functions;

import gnu.kawa.lispexpr.LangObjType;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.WrongType;
import gnu.math.BitOps;
import gnu.math.IntNum;
import java.math.BigInteger;

public class BitwiseOp extends ArithOp {
  public static final BitwiseOp and = new BitwiseOp("bitwise-and", 13);
  
  public static final BitwiseOp ashift;
  
  public static final BitwiseOp ashiftl;
  
  public static final BitwiseOp ashiftr;
  
  public static final BitwiseOp ior = new BitwiseOp("bitwise-ior", 14);
  
  public static final BitwiseOp not;
  
  public static final BitwiseOp xor = new BitwiseOp("bitwise-xor", 15);
  
  static {
    ashift = new BitwiseOp("bitwise-arithmetic-shift", 9);
    ashiftl = new BitwiseOp("bitwise-arithmetic-shift-left", 10);
    ashiftr = new BitwiseOp("bitwise-arithmetic-shift-right", 11);
    not = new BitwiseOp("bitwise-not", 16);
  }
  
  public BitwiseOp(String paramString, int paramInt) {
    super(paramString, paramInt);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
    Procedure.compilerKey.set((PropertySet)this, "*gnu.kawa.functions.CompileArith:forBitwise");
  }
  
  public static int checkNonNegativeShift(Procedure paramProcedure, int paramInt) {
    if (paramInt < 0)
      throw new WrongType(paramProcedure, 2, Integer.valueOf(paramInt), "non-negative integer"); 
    return paramInt;
  }
  
  public static IntNum shiftLeft(IntNum paramIntNum, int paramInt) {
    return IntNum.shift(paramIntNum, checkNonNegativeShift((Procedure)ashiftl, paramInt));
  }
  
  public static IntNum shiftRight(IntNum paramIntNum, int paramInt) {
    return IntNum.shift(paramIntNum, -checkNonNegativeShift((Procedure)ashiftr, paramInt));
  }
  
  public Object adjustResult(IntNum paramIntNum, int paramInt) {
    switch (paramInt) {
      default:
        return paramIntNum;
      case 1:
        return Integer.valueOf(paramIntNum.intValue());
      case 2:
        return Long.valueOf(paramIntNum.longValue());
      case 3:
        break;
    } 
    return new BigInteger(paramIntNum.toString());
  }
  
  public Object apply1(Object paramObject) {
    if (this.op == 16) {
      int i = Arithmetic.classifyValue(paramObject);
      return adjustResult(BitOps.not(LangObjType.coerceIntNum(paramObject)), i);
    } 
    return apply2(defaultResult(), paramObject);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic classifyValue : (Ljava/lang/Object;)I
    //   4: istore_3
    //   5: aload_2
    //   6: invokestatic classifyValue : (Ljava/lang/Object;)I
    //   9: istore #4
    //   11: aload_0
    //   12: getfield op : I
    //   15: bipush #9
    //   17: if_icmplt -> 29
    //   20: aload_0
    //   21: getfield op : I
    //   24: bipush #12
    //   26: if_icmple -> 44
    //   29: iload_3
    //   30: ifle -> 44
    //   33: iload_3
    //   34: iload #4
    //   36: if_icmple -> 108
    //   39: iload #4
    //   41: ifle -> 108
    //   44: aload_1
    //   45: invokestatic coerceIntNum : (Ljava/lang/Object;)Lgnu/math/IntNum;
    //   48: astore_1
    //   49: aload_2
    //   50: invokestatic coerceIntNum : (Ljava/lang/Object;)Lgnu/math/IntNum;
    //   53: astore_2
    //   54: aload_0
    //   55: getfield op : I
    //   58: tableswitch default -> 100, 9 -> 145, 10 -> 145, 11 -> 145, 12 -> 100, 13 -> 114, 14 -> 127, 15 -> 136
    //   100: new java/lang/Error
    //   103: dup
    //   104: invokespecial <init> : ()V
    //   107: athrow
    //   108: iload #4
    //   110: istore_3
    //   111: goto -> 44
    //   114: aload_1
    //   115: aload_2
    //   116: invokestatic and : (Lgnu/math/IntNum;Lgnu/math/IntNum;)Lgnu/math/IntNum;
    //   119: astore_1
    //   120: aload_0
    //   121: aload_1
    //   122: iload_3
    //   123: invokevirtual adjustResult : (Lgnu/math/IntNum;I)Ljava/lang/Object;
    //   126: areturn
    //   127: aload_1
    //   128: aload_2
    //   129: invokestatic ior : (Lgnu/math/IntNum;Lgnu/math/IntNum;)Lgnu/math/IntNum;
    //   132: astore_1
    //   133: goto -> 120
    //   136: aload_1
    //   137: aload_2
    //   138: invokestatic xor : (Lgnu/math/IntNum;Lgnu/math/IntNum;)Lgnu/math/IntNum;
    //   141: astore_1
    //   142: goto -> 120
    //   145: aload_2
    //   146: invokevirtual intValue : ()I
    //   149: istore #5
    //   151: aload_0
    //   152: getfield op : I
    //   155: bipush #11
    //   157: if_icmpeq -> 173
    //   160: iload #5
    //   162: istore #4
    //   164: aload_0
    //   165: getfield op : I
    //   168: bipush #10
    //   170: if_icmpne -> 198
    //   173: aload_0
    //   174: iload #5
    //   176: invokestatic checkNonNegativeShift : (Lgnu/mapping/Procedure;I)I
    //   179: pop
    //   180: iload #5
    //   182: istore #4
    //   184: aload_0
    //   185: getfield op : I
    //   188: bipush #11
    //   190: if_icmpne -> 198
    //   193: iload #5
    //   195: ineg
    //   196: istore #4
    //   198: aload_1
    //   199: iload #4
    //   201: invokestatic shift : (Lgnu/math/IntNum;I)Lgnu/math/IntNum;
    //   204: astore_1
    //   205: goto -> 120
  }
  
  public Object applyN(Object[] paramArrayOfObject) {
    int j = paramArrayOfObject.length;
    if (j == 0)
      return defaultResult(); 
    if (j == 1)
      return apply1(paramArrayOfObject[0]); 
    Object object = paramArrayOfObject[0];
    int i = 1;
    while (true) {
      Object object1 = object;
      if (i < j) {
        object = apply2(object, paramArrayOfObject[i]);
        i++;
        continue;
      } 
      return object1;
    } 
  }
  
  public Object defaultResult() {
    return (this.op == 13) ? IntNum.minusOne() : IntNum.zero();
  }
  
  public int numArgs() {
    return (this.op >= 9 && this.op <= 12) ? 8194 : ((this.op == 16) ? 4097 : -4096);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/BitwiseOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
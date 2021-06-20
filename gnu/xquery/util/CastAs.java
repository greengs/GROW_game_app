package gnu.xquery.util;

import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Target;
import gnu.kawa.functions.Convert;
import gnu.mapping.Procedure;

public class CastAs extends Convert {
  public static final CastAs castAs = new CastAs();
  
  public CastAs() {
    setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateApplyCastAs");
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    // Byte code:
    //   0: aload_1
    //   1: checkcast gnu/bytecode/Type
    //   4: astore #7
    //   6: aload #7
    //   8: instanceof gnu/kawa/xml/XDataType
    //   11: ifeq -> 28
    //   14: aload #7
    //   16: checkcast gnu/kawa/xml/XDataType
    //   19: aload_2
    //   20: invokevirtual cast : (Ljava/lang/Object;)Ljava/lang/Object;
    //   23: astore #7
    //   25: aload #7
    //   27: areturn
    //   28: aload #7
    //   30: instanceof gnu/kawa/reflect/OccurrenceType
    //   33: ifeq -> 243
    //   36: aload #7
    //   38: checkcast gnu/kawa/reflect/OccurrenceType
    //   41: astore #7
    //   43: aload #7
    //   45: invokevirtual getBase : ()Lgnu/bytecode/Type;
    //   48: astore #8
    //   50: aload #8
    //   52: instanceof gnu/kawa/xml/XDataType
    //   55: ifeq -> 243
    //   58: aload #7
    //   60: invokevirtual minOccurs : ()I
    //   63: istore #5
    //   65: aload #7
    //   67: invokevirtual maxOccurs : ()I
    //   70: istore #6
    //   72: aload_2
    //   73: instanceof gnu/mapping/Values
    //   76: ifeq -> 186
    //   79: aload_2
    //   80: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   83: if_acmpne -> 94
    //   86: aload_2
    //   87: astore #7
    //   89: iload #5
    //   91: ifeq -> 25
    //   94: aload_2
    //   95: checkcast gnu/mapping/Values
    //   98: astore #7
    //   100: aload #7
    //   102: invokevirtual startPos : ()I
    //   105: istore #4
    //   107: iconst_0
    //   108: istore_3
    //   109: new gnu/mapping/Values
    //   112: dup
    //   113: invokespecial <init> : ()V
    //   116: astore #9
    //   118: aload #7
    //   120: iload #4
    //   122: invokevirtual nextPos : (I)I
    //   125: istore #4
    //   127: iload #4
    //   129: ifne -> 155
    //   132: iload_3
    //   133: iload #5
    //   135: if_icmplt -> 207
    //   138: iload #6
    //   140: iflt -> 149
    //   143: iload_3
    //   144: iload #6
    //   146: if_icmpgt -> 207
    //   149: aload #9
    //   151: invokevirtual canonicalize : ()Ljava/lang/Object;
    //   154: areturn
    //   155: aload #7
    //   157: iload #4
    //   159: invokevirtual getPosPrevious : (I)Ljava/lang/Object;
    //   162: astore #10
    //   164: aload #9
    //   166: aload #8
    //   168: checkcast gnu/kawa/xml/XDataType
    //   171: aload #10
    //   173: invokevirtual cast : (Ljava/lang/Object;)Ljava/lang/Object;
    //   176: invokevirtual writeObject : (Ljava/lang/Object;)V
    //   179: iload_3
    //   180: iconst_1
    //   181: iadd
    //   182: istore_3
    //   183: goto -> 118
    //   186: iload #5
    //   188: iconst_1
    //   189: if_icmpgt -> 207
    //   192: iload #6
    //   194: ifeq -> 207
    //   197: aload #8
    //   199: checkcast gnu/kawa/xml/XDataType
    //   202: aload_2
    //   203: invokevirtual cast : (Ljava/lang/Object;)Ljava/lang/Object;
    //   206: areturn
    //   207: new java/lang/ClassCastException
    //   210: dup
    //   211: new java/lang/StringBuilder
    //   214: dup
    //   215: invokespecial <init> : ()V
    //   218: ldc 'cannot cast '
    //   220: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: aload_2
    //   224: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   227: ldc ' to '
    //   229: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: aload_1
    //   233: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   236: invokevirtual toString : ()Ljava/lang/String;
    //   239: invokespecial <init> : (Ljava/lang/String;)V
    //   242: athrow
    //   243: aload_0
    //   244: aload_1
    //   245: aload_2
    //   246: invokespecial apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   249: areturn
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/CastAs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
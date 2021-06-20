package gnu.kawa.xml;

import gnu.mapping.Procedure2;

public class NodeCompare extends Procedure2 {
  public static final NodeCompare $Eq = make("is", 8);
  
  public static final NodeCompare $Gr;
  
  public static final NodeCompare $Ls;
  
  public static final NodeCompare $Ne = make("isnot", 20);
  
  static final int RESULT_EQU = 0;
  
  static final int RESULT_GRT = 1;
  
  static final int RESULT_LSS = -1;
  
  static final int TRUE_IF_EQU = 8;
  
  static final int TRUE_IF_GRT = 16;
  
  static final int TRUE_IF_LSS = 4;
  
  int flags;
  
  static {
    $Gr = make(">>", 16);
    $Ls = make("<<", 4);
  }
  
  public static NodeCompare make(String paramString, int paramInt) {
    NodeCompare nodeCompare = new NodeCompare();
    nodeCompare.setName(paramString);
    nodeCompare.flags = paramInt;
    return nodeCompare;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 8
    //   4: aload_2
    //   5: ifnonnull -> 14
    //   8: aconst_null
    //   9: astore #5
    //   11: aload #5
    //   13: areturn
    //   14: aload_1
    //   15: astore #5
    //   17: aload_1
    //   18: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   21: if_acmpeq -> 11
    //   24: aload_2
    //   25: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   28: if_acmpne -> 33
    //   31: aload_2
    //   32: areturn
    //   33: aload_1
    //   34: instanceof gnu/lists/AbstractSequence
    //   37: ifeq -> 98
    //   40: aload_1
    //   41: checkcast gnu/lists/AbstractSequence
    //   44: astore_1
    //   45: aload_1
    //   46: invokevirtual startPos : ()I
    //   49: istore_3
    //   50: aload_2
    //   51: instanceof gnu/lists/AbstractSequence
    //   54: ifeq -> 132
    //   57: aload_2
    //   58: checkcast gnu/lists/AbstractSequence
    //   61: astore_2
    //   62: aload_2
    //   63: invokevirtual startPos : ()I
    //   66: istore #4
    //   68: aload_1
    //   69: aload_2
    //   70: if_acmpne -> 167
    //   73: aload_1
    //   74: iload_3
    //   75: iload #4
    //   77: invokevirtual compare : (II)I
    //   80: istore_3
    //   81: iconst_1
    //   82: iload_3
    //   83: iconst_3
    //   84: iadd
    //   85: ishl
    //   86: aload_0
    //   87: getfield flags : I
    //   90: iand
    //   91: ifeq -> 198
    //   94: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   97: areturn
    //   98: aload_1
    //   99: checkcast gnu/lists/SeqPosition
    //   102: astore #6
    //   104: aload #6
    //   106: getfield sequence : Lgnu/lists/AbstractSequence;
    //   109: astore #5
    //   111: aload #6
    //   113: invokevirtual getPos : ()I
    //   116: istore_3
    //   117: aload #5
    //   119: astore_1
    //   120: goto -> 50
    //   123: astore_2
    //   124: aload_2
    //   125: aload_0
    //   126: iconst_1
    //   127: aload_1
    //   128: invokestatic make : (Ljava/lang/ClassCastException;Lgnu/mapping/Procedure;ILjava/lang/Object;)Lgnu/mapping/WrongType;
    //   131: athrow
    //   132: aload_2
    //   133: checkcast gnu/lists/SeqPosition
    //   136: astore #6
    //   138: aload #6
    //   140: getfield sequence : Lgnu/lists/AbstractSequence;
    //   143: astore #5
    //   145: aload #6
    //   147: invokevirtual getPos : ()I
    //   150: istore #4
    //   152: aload #5
    //   154: astore_2
    //   155: goto -> 68
    //   158: astore_1
    //   159: aload_1
    //   160: aload_0
    //   161: iconst_2
    //   162: aload_2
    //   163: invokestatic make : (Ljava/lang/ClassCastException;Lgnu/mapping/Procedure;ILjava/lang/Object;)Lgnu/mapping/WrongType;
    //   166: athrow
    //   167: aload_0
    //   168: getstatic gnu/kawa/xml/NodeCompare.$Eq : Lgnu/kawa/xml/NodeCompare;
    //   171: if_acmpne -> 178
    //   174: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   177: areturn
    //   178: aload_0
    //   179: getstatic gnu/kawa/xml/NodeCompare.$Ne : Lgnu/kawa/xml/NodeCompare;
    //   182: if_acmpne -> 189
    //   185: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   188: areturn
    //   189: aload_1
    //   190: aload_2
    //   191: invokevirtual stableCompare : (Lgnu/lists/AbstractSequence;)I
    //   194: istore_3
    //   195: goto -> 81
    //   198: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   201: areturn
    // Exception table:
    //   from	to	target	type
    //   98	117	123	java/lang/ClassCastException
    //   132	152	158	java/lang/ClassCastException
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/NodeCompare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Filter;
import gnu.bytecode.ObjectType;

class MethodFilter implements Filter {
  ClassType caller;
  
  int modifiers;
  
  int modmask;
  
  String name;
  
  int nlen;
  
  ObjectType receiver;
  
  public MethodFilter(String paramString, int paramInt1, int paramInt2, ClassType paramClassType, ObjectType paramObjectType) {
    this.name = paramString;
    this.nlen = paramString.length();
    this.modifiers = paramInt1;
    this.modmask = paramInt2;
    this.caller = paramClassType;
    this.receiver = paramObjectType;
  }
  
  public boolean select(Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: checkcast gnu/bytecode/Method
    //   4: astore #5
    //   6: aload #5
    //   8: invokevirtual getName : ()Ljava/lang/String;
    //   11: astore_1
    //   12: aload #5
    //   14: invokevirtual getModifiers : ()I
    //   17: istore_2
    //   18: aload_0
    //   19: getfield modmask : I
    //   22: iload_2
    //   23: iand
    //   24: aload_0
    //   25: getfield modifiers : I
    //   28: if_icmpne -> 50
    //   31: iload_2
    //   32: sipush #4096
    //   35: iand
    //   36: ifne -> 50
    //   39: aload_1
    //   40: aload_0
    //   41: getfield name : Ljava/lang/String;
    //   44: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   47: ifne -> 52
    //   50: iconst_0
    //   51: ireturn
    //   52: aload_1
    //   53: invokevirtual length : ()I
    //   56: istore_2
    //   57: iload_2
    //   58: aload_0
    //   59: getfield nlen : I
    //   62: if_icmpeq -> 130
    //   65: iload_2
    //   66: aload_0
    //   67: getfield nlen : I
    //   70: iconst_2
    //   71: iadd
    //   72: if_icmpne -> 111
    //   75: aload_1
    //   76: aload_0
    //   77: getfield nlen : I
    //   80: invokevirtual charAt : (I)C
    //   83: bipush #36
    //   85: if_icmpne -> 111
    //   88: aload_1
    //   89: aload_0
    //   90: getfield nlen : I
    //   93: iconst_1
    //   94: iadd
    //   95: invokevirtual charAt : (I)C
    //   98: istore_3
    //   99: iload_3
    //   100: bipush #86
    //   102: if_icmpeq -> 130
    //   105: iload_3
    //   106: bipush #88
    //   108: if_icmpeq -> 130
    //   111: iload_2
    //   112: aload_0
    //   113: getfield nlen : I
    //   116: iconst_4
    //   117: iadd
    //   118: if_icmpne -> 50
    //   121: aload_1
    //   122: ldc '$V$X'
    //   124: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   127: ifeq -> 50
    //   130: aload_0
    //   131: getfield receiver : Lgnu/bytecode/ObjectType;
    //   134: instanceof gnu/bytecode/ClassType
    //   137: ifeq -> 181
    //   140: aload_0
    //   141: getfield receiver : Lgnu/bytecode/ObjectType;
    //   144: checkcast gnu/bytecode/ClassType
    //   147: astore_1
    //   148: aload_0
    //   149: getfield caller : Lgnu/bytecode/ClassType;
    //   152: ifnull -> 175
    //   155: aload_0
    //   156: getfield caller : Lgnu/bytecode/ClassType;
    //   159: aload_1
    //   160: aload_0
    //   161: getfield receiver : Lgnu/bytecode/ObjectType;
    //   164: aload #5
    //   166: invokevirtual getModifiers : ()I
    //   169: invokevirtual isAccessible : (Lgnu/bytecode/ClassType;Lgnu/bytecode/ObjectType;I)Z
    //   172: ifeq -> 190
    //   175: iconst_1
    //   176: istore #4
    //   178: iload #4
    //   180: ireturn
    //   181: aload #5
    //   183: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   186: astore_1
    //   187: goto -> 148
    //   190: iconst_0
    //   191: istore #4
    //   193: goto -> 178
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/MethodFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
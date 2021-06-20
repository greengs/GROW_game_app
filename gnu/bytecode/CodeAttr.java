package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;

public class CodeAttr extends Attribute implements AttrContainer {
  public static final int DONT_USE_JSR = 2;
  
  static final int FIXUP_CASE = 3;
  
  static final int FIXUP_DEFINE = 1;
  
  static final int FIXUP_DELETE3 = 8;
  
  static final int FIXUP_GOTO = 4;
  
  static final int FIXUP_JSR = 5;
  
  static final int FIXUP_LINE_NUMBER = 15;
  
  static final int FIXUP_LINE_PC = 14;
  
  static final int FIXUP_MOVE = 9;
  
  static final int FIXUP_MOVE_TO_END = 10;
  
  static final int FIXUP_NONE = 0;
  
  static final int FIXUP_SWITCH = 2;
  
  static final int FIXUP_TRANSFER = 6;
  
  static final int FIXUP_TRANSFER2 = 7;
  
  static final int FIXUP_TRY = 11;
  
  static final int FIXUP_TRY_END = 12;
  
  static final int FIXUP_TRY_HANDLER = 13;
  
  public static final int GENERATE_STACK_MAP_TABLE = 1;
  
  public static boolean instructionLineMode = false;
  
  int PC;
  
  int SP;
  
  Attribute attributes;
  
  byte[] code;
  
  ExitableBlock currentExitableBlock;
  
  short[] exception_table;
  
  int exception_table_length;
  
  int exitableBlockLevel;
  
  int fixup_count;
  
  Label[] fixup_labels;
  
  int[] fixup_offsets;
  
  int flags;
  
  IfState if_stack;
  
  LineNumbersAttr lines;
  
  Type[] local_types;
  
  public LocalVarsAttr locals;
  
  private int max_locals;
  
  private int max_stack;
  
  Label previousLabel;
  
  SourceDebugExtAttr sourceDbgExt;
  
  public StackMapTableAttr stackMap;
  
  public Type[] stack_types;
  
  TryState try_stack;
  
  private boolean unreachable_here;
  
  boolean[] varsSetInCurrentBlock;
  
  public CodeAttr(Method paramMethod) {
    super("Code");
    addToFrontOf(paramMethod);
    paramMethod.code = this;
    if (paramMethod.getDeclaringClass().getClassfileMajorVersion() >= 50)
      this.flags |= 0x3; 
  }
  
  private int adjustTypedOp(char paramChar) {
    switch (paramChar) {
      default:
        return 4;
      case 'I':
        return 0;
      case 'J':
        return 1;
      case 'F':
        return 2;
      case 'D':
        return 3;
      case 'B':
      case 'Z':
        return 5;
      case 'C':
        return 6;
      case 'S':
        break;
    } 
    return 7;
  }
  
  private int adjustTypedOp(Type paramType) {
    return adjustTypedOp(paramType.getSignature().charAt(0));
  }
  
  public static final String calculateSplit(String paramString) {
    int m = paramString.length();
    StringBuffer stringBuffer = new StringBuffer(20);
    int k = 0;
    int j = 0;
    int i = 0;
    while (i < m) {
      char c = paramString.charAt(i);
      if (c >= 'ࠀ') {
        c = '\003';
      } else if (c >= '' || c == '\000') {
        c = '\002';
      } else {
        c = '\001';
      } 
      int i1 = j;
      int n = k;
      if (j + c > 65535) {
        stringBuffer.append((char)(i - k));
        n = i;
        i1 = 0;
      } 
      j = i1 + c;
      i++;
      k = n;
    } 
    stringBuffer.append((char)(m - k));
    return stringBuffer.toString();
  }
  
  public static boolean castNeeded(Type paramType1, Type paramType2) {
    Type type1 = paramType1;
    Type type2 = paramType2;
    if (paramType1 instanceof UninitializedType) {
      type1 = ((UninitializedType)paramType1).getImplementationType();
      type2 = paramType2;
    } 
    while (true) {
      if (type1 == type2)
        return false; 
      if (type2 instanceof ClassType && type1 instanceof ClassType && ((ClassType)type1).isSubclass((ClassType)type2))
        return false; 
      if (type2 instanceof ArrayType && type1 instanceof ArrayType) {
        type2 = ((ArrayType)type2).getComponentType();
        type1 = ((ArrayType)type1).getComponentType();
        continue;
      } 
      break;
    } 
    return true;
  }
  
  private void emitBinop(int paramInt) {
    Type type1 = popType().promote();
    Type type2 = popType();
    Type type3 = type2.promote();
    if (type3 != type1 || !(type3 instanceof PrimType))
      throw new Error("non-matching or bad types in binary operation"); 
    emitTypedOp(paramInt, type3);
    pushType(type2);
  }
  
  private void emitBinop(int paramInt, char paramChar) {
    popType();
    popType();
    emitTypedOp(paramInt, paramChar);
    pushType(Type.signatureToPrimitive(paramChar));
  }
  
  private void emitCheckcast(Type paramType, int paramInt) {
    reserve(3);
    popType();
    put1(paramInt);
    if (paramType instanceof ObjectType) {
      putIndex2(getConstants().addClass((ObjectType)paramType));
      return;
    } 
    throw new Error("unimplemented type " + paramType + " in emitCheckcast/emitInstanceof");
  }
  
  private final void emitFieldop(Field paramField, int paramInt) {
    reserve(3);
    put1(paramInt);
    putIndex2(getConstants().addFieldRef(paramField));
  }
  
  private void emitShift(int paramInt) {
    Type type1 = popType().promote();
    Type type2 = popType();
    Type type3 = type2.promote();
    if (type3 != Type.intType && type3 != Type.longType)
      throw new Error("the value shifted must be an int or a long"); 
    if (type1 != Type.intType)
      throw new Error("the amount of shift must be an int"); 
    emitTypedOp(paramInt, type3);
    pushType(type2);
  }
  
  private void emitTryEnd(boolean paramBoolean) {
    if (this.try_stack.tryClauseDone)
      return; 
    this.try_stack.tryClauseDone = true;
    if (this.try_stack.finally_subr != null)
      this.try_stack.exception = addLocal(Type.javalangThrowableType); 
    gotoFinallyOrEnd(paramBoolean);
    this.try_stack.end_try = getLabel();
  }
  
  private void emitTypedOp(int paramInt, char paramChar) {
    reserve(1);
    put1(adjustTypedOp(paramChar) + paramInt);
  }
  
  private void emitTypedOp(int paramInt, Type paramType) {
    reserve(1);
    put1(adjustTypedOp(paramType) + paramInt);
  }
  
  private final int fixupKind(int paramInt) {
    return this.fixup_offsets[paramInt] & 0xF;
  }
  
  private final int fixupOffset(int paramInt) {
    return this.fixup_offsets[paramInt] >> 4;
  }
  
  private void gotoFinallyOrEnd(boolean paramBoolean) {
    if (reachableHere()) {
      if (this.try_stack.saved_result != null)
        emitStore(this.try_stack.saved_result); 
      if (this.try_stack.end_label == null)
        this.try_stack.end_label = new Label(); 
      if (this.try_stack.finally_subr == null || useJsr()) {
        if (this.try_stack.finally_subr != null)
          emitJsr(this.try_stack.finally_subr); 
        emitGoto(this.try_stack.end_label);
        return;
      } 
    } else {
      return;
    } 
    if (this.try_stack.exitCases != null)
      emitPushInt(0); 
    emitPushNull();
    if (!paramBoolean) {
      emitGoto(this.try_stack.finally_subr);
      return;
    } 
  }
  
  private Label mergeLabels(Label paramLabel1, Label paramLabel2) {
    if (paramLabel1 != null)
      paramLabel2.setTypes(paramLabel1); 
    return paramLabel2;
  }
  
  private void print(String paramString, int paramInt, PrintWriter paramPrintWriter) {
    int i = 0;
    int j = -1;
    while (paramInt >= 0) {
      i = j + 1;
      j = paramString.indexOf(';', i);
      paramInt--;
    } 
    paramPrintWriter.write(paramString, i, j - i);
  }
  
  private int readInt(int paramInt) {
    return readUnsignedShort(paramInt) << 16 | readUnsignedShort(paramInt + 2);
  }
  
  private int readUnsignedShort(int paramInt) {
    return (this.code[paramInt] & 0xFF) << 8 | this.code[paramInt + 1] & 0xFF;
  }
  
  private int words(Type[] paramArrayOfType) {
    int i = 0;
    int j = paramArrayOfType.length;
    while (true) {
      if (--j >= 0) {
        if ((paramArrayOfType[j]).size > 4) {
          i += 2;
          continue;
        } 
        i++;
        continue;
      } 
      return i;
    } 
  }
  
  public void addHandler(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int j = this.exception_table_length * 4;
    if (this.exception_table == null) {
      this.exception_table = new short[20];
    } else if (this.exception_table.length <= j) {
      short[] arrayOfShort1 = new short[this.exception_table.length * 2];
      System.arraycopy(this.exception_table, 0, arrayOfShort1, 0, j);
      this.exception_table = arrayOfShort1;
    } 
    short[] arrayOfShort = this.exception_table;
    int i = j + 1;
    arrayOfShort[j] = (short)paramInt1;
    arrayOfShort = this.exception_table;
    paramInt1 = i + 1;
    arrayOfShort[i] = (short)paramInt2;
    arrayOfShort = this.exception_table;
    paramInt2 = paramInt1 + 1;
    arrayOfShort[paramInt1] = (short)paramInt3;
    this.exception_table[paramInt2] = (short)paramInt4;
    this.exception_table_length++;
  }
  
  public void addHandler(Label paramLabel1, Label paramLabel2, ClassType paramClassType) {
    ClassType classType;
    int i;
    ConstantPool constantPool = getConstants();
    if (paramClassType == null) {
      i = 0;
    } else {
      i = (constantPool.addClass(paramClassType)).index;
    } 
    fixupAdd(11, paramLabel1);
    fixupAdd(12, i, paramLabel2);
    paramLabel2 = new Label();
    paramLabel2.localTypes = paramLabel1.localTypes;
    paramLabel2.stackTypes = new Type[1];
    if (paramClassType == null) {
      classType = Type.javalangThrowableType;
    } else {
      classType = paramClassType;
    } 
    paramLabel2.stackTypes[0] = classType;
    setTypes(paramLabel2);
    fixupAdd(13, 0, paramLabel2);
  }
  
  public Variable addLocal(Type paramType) {
    return this.locals.current_scope.addVariable(this, paramType, null);
  }
  
  public Variable addLocal(Type paramType, String paramString) {
    return this.locals.current_scope.addVariable(this, paramType, paramString);
  }
  
  public void addParamLocals() {
    Method method = getMethod();
    if ((method.access_flags & 0x8) == 0)
      addLocal(method.classfile).setParameter(true); 
    int j = method.arg_types.length;
    for (int i = 0; i < j; i++)
      addLocal(method.arg_types[i]).setParameter(true); 
  }
  
  public void assignConstants(ClassType paramClassType) {
    if (this.locals != null && this.locals.container == null && !this.locals.isEmpty())
      this.locals.addToFrontOf(this); 
    processFixups();
    if (this.stackMap != null && this.stackMap.numEntries > 0)
      this.stackMap.addToFrontOf(this); 
    if (instructionLineMode) {
      if (this.lines == null)
        this.lines = new LineNumbersAttr(this); 
      this.lines.linenumber_count = 0;
      int j = getCodeLength();
      for (int i = 0; i < j; i++)
        this.lines.put(i, i); 
    } 
    super.assignConstants(paramClassType);
    Attribute.assignConstants(this, paramClassType);
  }
  
  public int beginFragment(Label paramLabel) {
    return beginFragment(new Label(), paramLabel);
  }
  
  public int beginFragment(Label paramLabel1, Label paramLabel2) {
    int i = this.fixup_count;
    fixupAdd(10, paramLabel2);
    paramLabel1.define(this);
    return i;
  }
  
  public void disAssemble(ClassTypeWriter paramClassTypeWriter, int paramInt1, int paramInt2) {
    boolean bool = false;
    for (int i = paramInt1; i < paramInt2; i = paramInt1) {
      paramInt1 = i + 1;
      int k = this.code[i] & 0xFF;
      String str = Integer.toString(i);
      boolean bool1 = false;
      int j = str.length();
      while (true)
        paramClassTypeWriter.print(' '); 
      continue;
      paramClassTypeWriter.println();
    } 
  }
  
  public final void emitAdd() {
    emitBinop(96);
  }
  
  public final void emitAdd(char paramChar) {
    emitBinop(96, paramChar);
  }
  
  public final void emitAdd(PrimType paramPrimType) {
    emitBinop(96, paramPrimType);
  }
  
  public final void emitAnd() {
    emitBinop(126);
  }
  
  public final void emitArrayLength() {
    if (!(popType() instanceof ArrayType))
      throw new Error("non-array type in emitArrayLength"); 
    reserve(1);
    put1(190);
    pushType(Type.intType);
  }
  
  public void emitArrayLoad() {
    popType();
    Type type = ((ArrayType)popType().getImplementationType()).getComponentType();
    emitTypedOp(46, type);
    pushType(type);
  }
  
  public void emitArrayLoad(Type paramType) {
    popType();
    popType();
    emitTypedOp(46, paramType);
    pushType(paramType);
  }
  
  public void emitArrayStore() {
    popType();
    popType();
    emitTypedOp(79, ((ArrayType)popType().getImplementationType()).getComponentType());
  }
  
  public void emitArrayStore(Type paramType) {
    popType();
    popType();
    popType();
    emitTypedOp(79, paramType);
  }
  
  public void emitBinop(int paramInt, Type paramType) {
    popType();
    popType();
    emitTypedOp(paramInt, paramType);
    pushType(paramType);
  }
  
  public void emitCatchEnd() {
    gotoFinallyOrEnd(false);
    this.try_stack.try_type = null;
  }
  
  public void emitCatchStart(Variable paramVariable) {
    ClassType classType;
    emitTryEnd(false);
    setTypes(this.try_stack.start_try.localTypes, Type.typeArray0);
    if (this.try_stack.try_type != null)
      emitCatchEnd(); 
    if (paramVariable == null) {
      classType = null;
    } else {
      classType = (ClassType)paramVariable.getType();
    } 
    this.try_stack.try_type = classType;
    addHandler(this.try_stack.start_try, this.try_stack.end_try, classType);
    if (paramVariable != null)
      emitStore(paramVariable); 
  }
  
  public void emitCheckcast(Type paramType) {
    if (castNeeded(topType(), paramType)) {
      emitCheckcast(paramType, 192);
      pushType(paramType);
    } 
  }
  
  public final void emitConvert(Type paramType1, Type paramType2) {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual getSignature : ()Ljava/lang/String;
    //   4: astore #7
    //   6: aload_1
    //   7: invokevirtual getSignature : ()Ljava/lang/String;
    //   10: astore #8
    //   12: iconst_m1
    //   13: istore #5
    //   15: aload #7
    //   17: invokevirtual length : ()I
    //   20: iconst_1
    //   21: if_icmpeq -> 36
    //   24: iload #5
    //   26: istore_3
    //   27: aload #8
    //   29: invokevirtual length : ()I
    //   32: iconst_1
    //   33: if_icmpne -> 151
    //   36: aload #7
    //   38: iconst_0
    //   39: invokevirtual charAt : (I)C
    //   42: istore #6
    //   44: aload #8
    //   46: iconst_0
    //   47: invokevirtual charAt : (I)C
    //   50: istore_3
    //   51: iload_3
    //   52: iload #6
    //   54: if_icmpne -> 58
    //   57: return
    //   58: aload_1
    //   59: getfield size : I
    //   62: iconst_4
    //   63: if_icmpge -> 69
    //   66: bipush #73
    //   68: istore_3
    //   69: iload_3
    //   70: istore #4
    //   72: aload_2
    //   73: getfield size : I
    //   76: iconst_4
    //   77: if_icmpge -> 92
    //   80: aload_0
    //   81: aload_1
    //   82: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   85: invokevirtual emitConvert : (Lgnu/bytecode/Type;Lgnu/bytecode/Type;)V
    //   88: bipush #73
    //   90: istore #4
    //   92: iload #4
    //   94: iload #6
    //   96: if_icmpeq -> 57
    //   99: iload #5
    //   101: istore_3
    //   102: iload #4
    //   104: tableswitch default -> 148, 68 -> 407, 69 -> 151, 70 -> 343, 71 -> 151, 72 -> 151, 73 -> 166, 74 -> 276
    //   148: iload #5
    //   150: istore_3
    //   151: iload_3
    //   152: ifge -> 471
    //   155: new java/lang/Error
    //   158: dup
    //   159: ldc_w 'unsupported CodeAttr.emitConvert'
    //   162: invokespecial <init> : (Ljava/lang/String;)V
    //   165: athrow
    //   166: iload #6
    //   168: lookupswitch default -> 228, 66 -> 234, 67 -> 241, 68 -> 269, 70 -> 262, 74 -> 255, 83 -> 248
    //   228: iload #5
    //   230: istore_3
    //   231: goto -> 151
    //   234: sipush #145
    //   237: istore_3
    //   238: goto -> 151
    //   241: sipush #146
    //   244: istore_3
    //   245: goto -> 151
    //   248: sipush #147
    //   251: istore_3
    //   252: goto -> 151
    //   255: sipush #133
    //   258: istore_3
    //   259: goto -> 151
    //   262: sipush #134
    //   265: istore_3
    //   266: goto -> 151
    //   269: sipush #135
    //   272: istore_3
    //   273: goto -> 151
    //   276: iload #6
    //   278: tableswitch default -> 316, 68 -> 322, 69 -> 316, 70 -> 336, 71 -> 316, 72 -> 316, 73 -> 329
    //   316: iload #5
    //   318: istore_3
    //   319: goto -> 151
    //   322: sipush #138
    //   325: istore_3
    //   326: goto -> 151
    //   329: sipush #136
    //   332: istore_3
    //   333: goto -> 151
    //   336: sipush #137
    //   339: istore_3
    //   340: goto -> 151
    //   343: iload #6
    //   345: lookupswitch default -> 380, 68 -> 386, 73 -> 393, 74 -> 400
    //   380: iload #5
    //   382: istore_3
    //   383: goto -> 151
    //   386: sipush #141
    //   389: istore_3
    //   390: goto -> 151
    //   393: sipush #139
    //   396: istore_3
    //   397: goto -> 151
    //   400: sipush #140
    //   403: istore_3
    //   404: goto -> 151
    //   407: iload #6
    //   409: tableswitch default -> 444, 70 -> 450, 71 -> 444, 72 -> 444, 73 -> 457, 74 -> 464
    //   444: iload #5
    //   446: istore_3
    //   447: goto -> 151
    //   450: sipush #144
    //   453: istore_3
    //   454: goto -> 151
    //   457: sipush #142
    //   460: istore_3
    //   461: goto -> 151
    //   464: sipush #143
    //   467: istore_3
    //   468: goto -> 151
    //   471: aload_0
    //   472: iconst_1
    //   473: invokevirtual reserve : (I)V
    //   476: aload_0
    //   477: invokevirtual popType : ()Lgnu/bytecode/Type;
    //   480: pop
    //   481: aload_0
    //   482: iload_3
    //   483: invokevirtual put1 : (I)V
    //   486: aload_0
    //   487: aload_2
    //   488: invokevirtual pushType : (Lgnu/bytecode/Type;)V
    //   491: return
  }
  
  public final void emitDiv() {
    emitBinop(108);
  }
  
  public void emitDup() {
    byte b;
    reserve(1);
    Type type = topType();
    if (type.size <= 4) {
      b = 89;
    } else {
      b = 92;
    } 
    put1(b);
    pushType(type);
  }
  
  public void emitDup(int paramInt) {
    emitDup(paramInt, 0);
  }
  
  public void emitDup(int paramInt1, int paramInt2) {
    if (paramInt1 == 0)
      return; 
    reserve(1);
    Type type4 = popType();
    Type type2 = null;
    if (paramInt1 == 1) {
      if (type4.size > 4)
        throw new Error("using dup for 2-word type"); 
    } else {
      if (paramInt1 != 2)
        throw new Error("invalid size to emitDup"); 
      if (type4.size <= 4) {
        Type type = popType();
        type2 = type;
        if (type.size > 4)
          throw new Error("dup will cause invalid types on stack"); 
      } 
    } 
    Type type1 = null;
    Type type3 = null;
    if (paramInt2 == 0) {
      if (paramInt1 == 1) {
        paramInt1 = 89;
      } else {
        paramInt1 = 92;
      } 
    } else if (paramInt2 == 1) {
      if (paramInt1 == 1) {
        paramInt1 = 90;
      } else {
        paramInt1 = 93;
      } 
      Type type = popType();
      type1 = type;
      if (type.size > 4)
        throw new Error("dup will cause invalid types on stack"); 
    } else if (paramInt2 == 2) {
      if (paramInt1 == 1) {
        paramInt2 = 91;
      } else {
        paramInt2 = 94;
      } 
      Type type = popType();
      paramInt1 = paramInt2;
      type1 = type;
      if (type.size <= 4) {
        Type type5 = popType();
        paramInt1 = paramInt2;
        type1 = type;
        type3 = type5;
        if (type5.size > 4)
          throw new Error("dup will cause invalid types on stack"); 
      } 
    } else {
      throw new Error("emitDup:  invalid offset");
    } 
    put1(paramInt1);
    if (type2 != null)
      pushType(type2); 
    pushType(type4);
    if (type3 != null)
      pushType(type3); 
    if (type1 != null)
      pushType(type1); 
    if (type2 != null)
      pushType(type2); 
    pushType(type4);
  }
  
  public void emitDup(Type paramType) {
    boolean bool;
    if (paramType.size > 4) {
      bool = true;
    } else {
      bool = true;
    } 
    emitDup(bool, 0);
  }
  
  public void emitDupX() {
    reserve(1);
    Type type1 = popType();
    Type type2 = popType();
    if (type2.size <= 4) {
      byte b;
      if (type1.size <= 4) {
        b = 90;
      } else {
        b = 93;
      } 
      put1(b);
    } else {
      byte b;
      if (type1.size <= 4) {
        b = 91;
      } else {
        b = 94;
      } 
      put1(b);
    } 
    pushType(type1);
    pushType(type2);
    pushType(type1);
  }
  
  public final void emitElse() {
    Label label = this.if_stack.end_label;
    if (reachableHere()) {
      Label label1 = new Label(this);
      this.if_stack.end_label = label1;
      int i = this.SP - this.if_stack.start_stack_size;
      this.if_stack.stack_growth = i;
      if (i > 0) {
        this.if_stack.then_stacked_types = new Type[i];
        System.arraycopy(this.stack_types, this.if_stack.start_stack_size, this.if_stack.then_stacked_types, 0, i);
      } else {
        this.if_stack.then_stacked_types = new Type[0];
      } 
      emitGoto(label1);
    } else {
      this.if_stack.end_label = null;
    } 
    while (this.SP > this.if_stack.start_stack_size)
      popType(); 
    this.SP = this.if_stack.start_stack_size;
    if (label != null)
      label.define(this); 
    this.if_stack.doing_else = true;
  }
  
  public final void emitFi() {
    boolean bool1;
    boolean bool2 = false;
    if (!this.if_stack.doing_else) {
      bool1 = bool2;
      if (reachableHere()) {
        bool1 = bool2;
        if (this.SP != this.if_stack.start_stack_size)
          throw new Error("at PC " + this.PC + " then clause grows stack with no else clause"); 
      } 
    } else if (this.if_stack.then_stacked_types != null) {
      int i = this.if_stack.start_stack_size + this.if_stack.stack_growth;
      if (!reachableHere()) {
        if (this.if_stack.stack_growth > 0)
          System.arraycopy(this.if_stack.then_stacked_types, 0, this.stack_types, this.if_stack.start_stack_size, this.if_stack.stack_growth); 
        this.SP = i;
        bool1 = bool2;
      } else {
        bool1 = bool2;
        if (this.SP != i)
          throw new Error("at PC " + this.PC + ": SP at end of 'then' was " + i + " while SP at end of 'else' was " + this.SP); 
      } 
    } else {
      bool1 = bool2;
      if (this.unreachable_here)
        bool1 = true; 
    } 
    if (this.if_stack.end_label != null)
      this.if_stack.end_label.define(this); 
    if (bool1)
      setUnreachable(); 
    this.if_stack = this.if_stack.previous;
  }
  
  public void emitFinallyEnd() {
    if (useJsr()) {
      emitRet(this.try_stack.finally_ret_addr);
    } else if (this.try_stack.end_label == null && this.try_stack.exitCases == null) {
      emitThrow();
    } else {
      emitStore(this.try_stack.exception);
      emitLoad(this.try_stack.exception);
      emitIfNotNull();
      emitLoad(this.try_stack.exception);
      emitThrow();
      emitElse();
      ExitableBlock exitableBlock = this.try_stack.exitCases;
      if (exitableBlock != null) {
        SwitchState switchState = startSwitch();
        while (exitableBlock != null) {
          ExitableBlock exitableBlock1 = exitableBlock.nextCase;
          exitableBlock.nextCase = null;
          exitableBlock.currentTryState = null;
          TryState tryState = TryState.outerHandler(this.try_stack.previous, exitableBlock.initialTryState);
          if (tryState == exitableBlock.initialTryState) {
            switchState.addCaseGoto(exitableBlock.switchCase, this, exitableBlock.endLabel);
          } else {
            switchState.addCase(exitableBlock.switchCase, this);
            exitableBlock.exit(tryState);
          } 
          exitableBlock = exitableBlock1;
        } 
        this.try_stack.exitCases = null;
        switchState.addDefault(this);
        switchState.finish(this);
      } 
      emitFi();
      setUnreachable();
    } 
    popScope();
    this.try_stack.finally_subr = null;
  }
  
  public void emitFinallyStart() {
    emitTryEnd(true);
    if (this.try_stack.try_type != null)
      emitCatchEnd(); 
    this.try_stack.end_try = getLabel();
    pushScope();
    if (useJsr()) {
      this.SP = 0;
      emitCatchStart((Variable)null);
      emitStore(this.try_stack.exception);
      emitJsr(this.try_stack.finally_subr);
      emitLoad(this.try_stack.exception);
      emitThrow();
    } else {
      setUnreachable();
      int i = beginFragment(new Label(this));
      addHandler(this.try_stack.start_try, this.try_stack.end_try, Type.javalangThrowableType);
      if (this.try_stack.saved_result != null)
        emitStoreDefaultValue(this.try_stack.saved_result); 
      if (this.try_stack.exitCases != null) {
        emitPushInt(-1);
        emitSwap();
      } 
      emitGoto(this.try_stack.finally_subr);
      endFragment(i);
    } 
    this.try_stack.finally_subr.define(this);
    if (useJsr()) {
      ClassType classType = Type.objectType;
      this.try_stack.finally_ret_addr = addLocal(classType);
      pushType(classType);
      emitStore(this.try_stack.finally_ret_addr);
    } 
  }
  
  public final void emitGetField(Field paramField) {
    popType();
    pushType(paramField.type);
    emitFieldop(paramField, 180);
  }
  
  public final void emitGetStatic(Field paramField) {
    pushType(paramField.type);
    emitFieldop(paramField, 178);
  }
  
  public final void emitGoto(Label paramLabel) {
    paramLabel.setTypes(this);
    fixupAdd(4, paramLabel);
    reserve(3);
    put1(167);
    this.PC += 2;
    setUnreachable();
  }
  
  public final void emitGotoIfCompare1(Label paramLabel, int paramInt) {
    popType();
    reserve(3);
    emitTransfer(paramLabel, paramInt);
  }
  
  public final void emitGotoIfCompare2(Label paramLabel, int paramInt) {
    char c = Character.MIN_VALUE;
    if (paramInt < 153 || paramInt > 158)
      throw new Error("emitGotoIfCompare2: logop must be one of ifeq...ifle"); 
    Type type1 = popType().promote();
    Type type2 = popType().promote();
    reserve(4);
    char c1 = type2.getSignature().charAt(0);
    char c2 = type1.getSignature().charAt(0);
    if (paramInt == 155 || paramInt == 158)
      c = '\001'; 
    if (c1 == 'I' && c2 == 'I') {
      paramInt += 6;
    } else if (c1 == 'J' && c2 == 'J') {
      put1(148);
    } else if (c1 == 'F' && c2 == 'F') {
      if (c) {
        c = '';
      } else {
        c = '';
      } 
      put1(c);
    } else if (c1 == 'D' && c2 == 'D') {
      if (c != '\000') {
        c = '';
      } else {
        c = '';
      } 
      put1(c);
    } else if ((c1 == 'L' || c1 == '[') && (c2 == 'L' || c2 == '[') && paramInt <= 154) {
      paramInt += 12;
    } else {
      throw new Error("invalid types to emitGotoIfCompare2");
    } 
    emitTransfer(paramLabel, paramInt);
  }
  
  public final void emitGotoIfEq(Label paramLabel) {
    emitGotoIfCompare2(paramLabel, 153);
  }
  
  public final void emitGotoIfEq(Label paramLabel, boolean paramBoolean) {
    char c;
    if (paramBoolean) {
      c = '';
    } else {
      c = '';
    } 
    emitGotoIfCompare2(paramLabel, c);
  }
  
  public final void emitGotoIfGe(Label paramLabel) {
    emitGotoIfCompare2(paramLabel, 156);
  }
  
  public final void emitGotoIfGt(Label paramLabel) {
    emitGotoIfCompare2(paramLabel, 157);
  }
  
  public final void emitGotoIfIntEqZero(Label paramLabel) {
    emitGotoIfCompare1(paramLabel, 153);
  }
  
  public final void emitGotoIfIntGeZero(Label paramLabel) {
    emitGotoIfCompare1(paramLabel, 156);
  }
  
  public final void emitGotoIfIntGtZero(Label paramLabel) {
    emitGotoIfCompare1(paramLabel, 157);
  }
  
  public final void emitGotoIfIntLeZero(Label paramLabel) {
    emitGotoIfCompare1(paramLabel, 158);
  }
  
  public final void emitGotoIfIntLtZero(Label paramLabel) {
    emitGotoIfCompare1(paramLabel, 155);
  }
  
  public final void emitGotoIfIntNeZero(Label paramLabel) {
    emitGotoIfCompare1(paramLabel, 154);
  }
  
  public final void emitGotoIfLe(Label paramLabel) {
    emitGotoIfCompare2(paramLabel, 158);
  }
  
  public final void emitGotoIfLt(Label paramLabel) {
    emitGotoIfCompare2(paramLabel, 155);
  }
  
  public final void emitGotoIfNE(Label paramLabel) {
    emitGotoIfCompare2(paramLabel, 154);
  }
  
  public final void emitIOr() {
    emitBinop(128);
  }
  
  public final void emitIfCompare1(int paramInt) {
    IfState ifState = new IfState(this);
    if (popType().promote() != Type.intType)
      throw new Error("non-int type to emitIfCompare1"); 
    reserve(3);
    emitTransfer(ifState.end_label, paramInt);
    ifState.start_stack_size = this.SP;
  }
  
  public final void emitIfEq() {
    IfState ifState = new IfState(this);
    emitGotoIfNE(ifState.end_label);
    ifState.start_stack_size = this.SP;
  }
  
  public final void emitIfGe() {
    IfState ifState = new IfState(this);
    emitGotoIfLt(ifState.end_label);
    ifState.start_stack_size = this.SP;
  }
  
  public final void emitIfGt() {
    IfState ifState = new IfState(this);
    emitGotoIfLe(ifState.end_label);
    ifState.start_stack_size = this.SP;
  }
  
  public final void emitIfIntCompare(int paramInt) {
    IfState ifState = new IfState(this);
    popType();
    popType();
    reserve(3);
    emitTransfer(ifState.end_label, paramInt);
    ifState.start_stack_size = this.SP;
  }
  
  public final void emitIfIntEqZero() {
    emitIfCompare1(154);
  }
  
  public final void emitIfIntLEqZero() {
    emitIfCompare1(157);
  }
  
  public final void emitIfIntLt() {
    emitIfIntCompare(162);
  }
  
  public final void emitIfIntNotZero() {
    emitIfCompare1(153);
  }
  
  public final void emitIfLe() {
    IfState ifState = new IfState(this);
    emitGotoIfGt(ifState.end_label);
    ifState.start_stack_size = this.SP;
  }
  
  public final void emitIfLt() {
    IfState ifState = new IfState(this);
    emitGotoIfGe(ifState.end_label);
    ifState.start_stack_size = this.SP;
  }
  
  public final void emitIfNEq() {
    IfState ifState = new IfState(this);
    emitGotoIfEq(ifState.end_label);
    ifState.start_stack_size = this.SP;
  }
  
  public final void emitIfNotNull() {
    emitIfRefCompare1(198);
  }
  
  public final void emitIfNull() {
    emitIfRefCompare1(199);
  }
  
  public final void emitIfRefCompare1(int paramInt) {
    IfState ifState = new IfState(this);
    if (!(popType() instanceof ObjectType))
      throw new Error("non-ref type to emitIfRefCompare1"); 
    reserve(3);
    emitTransfer(ifState.end_label, paramInt);
    ifState.start_stack_size = this.SP;
  }
  
  public final void emitIfThen() {
    new IfState(this, null);
  }
  
  public void emitInc(Variable paramVariable, short paramShort) {
    boolean bool;
    if (paramVariable.dead())
      throw new Error("attempting to increment dead variable"); 
    int i = paramVariable.offset;
    if (i < 0 || !paramVariable.isSimple())
      throw new Error("attempting to increment unassigned variable" + paramVariable.getName() + " simple:" + paramVariable.isSimple() + ", offset: " + i); 
    reserve(6);
    if (paramVariable.getType().getImplementationType().promote() != Type.intType)
      throw new Error("attempting to increment non-int variable"); 
    if (i > 255 || paramShort > 255 || paramShort < -256) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      put1(196);
      put1(132);
      put2(i);
      put2(paramShort);
      return;
    } 
    put1(132);
    put1(i);
    put1(paramShort);
  }
  
  public void emitInstanceof(Type paramType) {
    emitCheckcast(paramType, 193);
    pushType(Type.booleanType);
  }
  
  public void emitInvoke(Method paramMethod) {
    char c;
    if ((paramMethod.access_flags & 0x8) != 0) {
      c = '¸';
    } else if (paramMethod.classfile.isInterface()) {
      c = '¹';
    } else if ("<init>".equals(paramMethod.getName())) {
      c = '·';
    } else {
      c = '¶';
    } 
    emitInvokeMethod(paramMethod, c);
  }
  
  public void emitInvokeInterface(Method paramMethod) {
    emitInvokeMethod(paramMethod, 185);
  }
  
  public void emitInvokeMethod(Method paramMethod, int paramInt) {
    boolean bool;
    byte b = 1;
    if (paramInt == 185) {
      i = 5;
    } else {
      i = 3;
    } 
    reserve(i);
    int k = paramMethod.arg_types.length;
    if (paramInt == 184) {
      j = 1;
    } else {
      j = 0;
    } 
    if (paramInt == 183 && "<init>".equals(paramMethod.getName())) {
      bool = true;
    } else {
      bool = false;
    } 
    if ((paramMethod.access_flags & 0x8) != 0) {
      i = b;
    } else {
      i = 0;
    } 
    if (j != i)
      throw new Error("emitInvokeXxx static flag mis-match method.flags=" + paramMethod.access_flags); 
    int i = k;
    if (j == 0) {
      i = k;
      if (!bool)
        i = k + 1; 
    } 
    put1(paramInt);
    putIndex2(getConstants().addMethodRef(paramMethod));
    int j = i;
    if (paramInt == 185) {
      put1(words(paramMethod.arg_types) + 1);
      put1(0);
      j = i;
    } 
    while (true) {
      if (--j >= 0) {
        Type type = popType();
        if (type instanceof UninitializedType)
          throw new Error("passing " + type + " as parameter"); 
        continue;
      } 
      if (bool) {
        Type type = popType();
        if (!(type instanceof UninitializedType))
          throw new Error("calling <init> on already-initialized object"); 
        ClassType classType = ((UninitializedType)type).ctype;
        for (paramInt = 0; paramInt < this.SP; paramInt++) {
          if (this.stack_types[paramInt] == type)
            this.stack_types[paramInt] = classType; 
        } 
        Variable[] arrayOfVariable = this.locals.used;
        if (arrayOfVariable == null) {
          paramInt = 0;
        } else {
          paramInt = arrayOfVariable.length;
        } 
        while (true) {
          i = paramInt - 1;
          if (i >= 0) {
            Variable variable = arrayOfVariable[i];
            paramInt = i;
            if (variable != null) {
              paramInt = i;
              if (variable.type == type) {
                variable.type = classType;
                paramInt = i;
              } 
            } 
            continue;
          } 
          if (this.local_types == null) {
            paramInt = 0;
          } else {
            paramInt = this.local_types.length;
          } 
          while (true) {
            i = paramInt - 1;
            if (i >= 0) {
              paramInt = i;
              if (this.local_types[i] == type) {
                this.local_types[i] = classType;
                paramInt = i;
              } 
              continue;
            } 
            break;
          } 
          break;
        } 
      } 
      if (paramMethod.return_type.size != 0)
        pushType(paramMethod.return_type); 
      return;
    } 
  }
  
  public void emitInvokeSpecial(Method paramMethod) {
    emitInvokeMethod(paramMethod, 183);
  }
  
  public void emitInvokeStatic(Method paramMethod) {
    emitInvokeMethod(paramMethod, 184);
  }
  
  public void emitInvokeVirtual(Method paramMethod) {
    emitInvokeMethod(paramMethod, 182);
  }
  
  public final void emitJsr(Label paramLabel) {
    fixupAdd(5, paramLabel);
    reserve(3);
    put1(168);
    this.PC += 2;
  }
  
  public final void emitLoad(Variable paramVariable) {
    if (paramVariable.dead())
      throw new Error("attempting to push dead variable"); 
    int i = paramVariable.offset;
    if (i < 0 || !paramVariable.isSimple())
      throw new Error("attempting to load from unassigned variable " + paramVariable + " simple:" + paramVariable.isSimple() + ", offset: " + i); 
    Type type = paramVariable.getType().promote();
    reserve(4);
    int j = adjustTypedOp(type);
    if (i <= 3) {
      put1(j * 4 + 26 + i);
    } else {
      emitMaybeWide(j + 21, i);
    } 
    pushType(paramVariable.getType());
  }
  
  void emitMaybeWide(int paramInt1, int paramInt2) {
    if (paramInt2 >= 256) {
      put1(196);
      put1(paramInt1);
      put2(paramInt2);
      return;
    } 
    put1(paramInt1);
    put1(paramInt2);
  }
  
  public final void emitMonitorEnter() {
    popType();
    reserve(1);
    put1(194);
  }
  
  public final void emitMonitorExit() {
    popType();
    reserve(1);
    put1(195);
  }
  
  public final void emitMul() {
    emitBinop(104);
  }
  
  public void emitNew(ClassType paramClassType) {
    reserve(3);
    Label label = new Label(this);
    label.defineRaw(this);
    put1(187);
    putIndex2(getConstants().addClass(paramClassType));
    pushType(new UninitializedType(paramClassType, label));
  }
  
  void emitNewArray(int paramInt) {
    reserve(2);
    put1(188);
    put1(paramInt);
  }
  
  public void emitNewArray(Type paramType) {
    emitNewArray(paramType, 1);
  }
  
  public void emitNewArray(Type paramType, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual popType : ()Lgnu/bytecode/Type;
    //   4: invokevirtual promote : ()Lgnu/bytecode/Type;
    //   7: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   10: if_acmpeq -> 24
    //   13: new java/lang/Error
    //   16: dup
    //   17: ldc_w 'non-int dim. spec. in emitNewArray'
    //   20: invokespecial <init> : (Ljava/lang/String;)V
    //   23: athrow
    //   24: aload_1
    //   25: instanceof gnu/bytecode/PrimType
    //   28: ifeq -> 184
    //   31: aload_1
    //   32: invokevirtual getSignature : ()Ljava/lang/String;
    //   35: iconst_0
    //   36: invokevirtual charAt : (I)C
    //   39: lookupswitch default -> 112, 66 -> 123, 67 -> 179, 68 -> 168, 70 -> 162, 73 -> 150, 74 -> 156, 83 -> 144, 90 -> 174
    //   112: new java/lang/Error
    //   115: dup
    //   116: ldc_w 'bad PrimType in emitNewArray'
    //   119: invokespecial <init> : (Ljava/lang/String;)V
    //   122: athrow
    //   123: bipush #8
    //   125: istore_2
    //   126: aload_0
    //   127: iload_2
    //   128: invokevirtual emitNewArray : (I)V
    //   131: aload_0
    //   132: new gnu/bytecode/ArrayType
    //   135: dup
    //   136: aload_1
    //   137: invokespecial <init> : (Lgnu/bytecode/Type;)V
    //   140: invokevirtual pushType : (Lgnu/bytecode/Type;)V
    //   143: return
    //   144: bipush #9
    //   146: istore_2
    //   147: goto -> 126
    //   150: bipush #10
    //   152: istore_2
    //   153: goto -> 126
    //   156: bipush #11
    //   158: istore_2
    //   159: goto -> 126
    //   162: bipush #6
    //   164: istore_2
    //   165: goto -> 126
    //   168: bipush #7
    //   170: istore_2
    //   171: goto -> 126
    //   174: iconst_4
    //   175: istore_2
    //   176: goto -> 126
    //   179: iconst_5
    //   180: istore_2
    //   181: goto -> 126
    //   184: aload_1
    //   185: instanceof gnu/bytecode/ObjectType
    //   188: ifeq -> 221
    //   191: aload_0
    //   192: iconst_3
    //   193: invokevirtual reserve : (I)V
    //   196: aload_0
    //   197: sipush #189
    //   200: invokevirtual put1 : (I)V
    //   203: aload_0
    //   204: aload_0
    //   205: invokevirtual getConstants : ()Lgnu/bytecode/ConstantPool;
    //   208: aload_1
    //   209: checkcast gnu/bytecode/ObjectType
    //   212: invokevirtual addClass : (Lgnu/bytecode/ObjectType;)Lgnu/bytecode/CpoolClass;
    //   215: invokevirtual putIndex2 : (Lgnu/bytecode/CpoolEntry;)V
    //   218: goto -> 131
    //   221: aload_1
    //   222: instanceof gnu/bytecode/ArrayType
    //   225: ifeq -> 319
    //   228: aload_0
    //   229: iconst_4
    //   230: invokevirtual reserve : (I)V
    //   233: aload_0
    //   234: sipush #197
    //   237: invokevirtual put1 : (I)V
    //   240: aload_0
    //   241: aload_0
    //   242: invokevirtual getConstants : ()Lgnu/bytecode/ConstantPool;
    //   245: new gnu/bytecode/ArrayType
    //   248: dup
    //   249: aload_1
    //   250: invokespecial <init> : (Lgnu/bytecode/Type;)V
    //   253: invokevirtual addClass : (Lgnu/bytecode/ObjectType;)Lgnu/bytecode/CpoolClass;
    //   256: invokevirtual putIndex2 : (Lgnu/bytecode/CpoolEntry;)V
    //   259: iload_2
    //   260: iconst_1
    //   261: if_icmplt -> 271
    //   264: iload_2
    //   265: sipush #255
    //   268: if_icmple -> 282
    //   271: new java/lang/Error
    //   274: dup
    //   275: ldc_w 'dims out of range in emitNewArray'
    //   278: invokespecial <init> : (Ljava/lang/String;)V
    //   281: athrow
    //   282: aload_0
    //   283: iload_2
    //   284: invokevirtual put1 : (I)V
    //   287: iload_2
    //   288: iconst_1
    //   289: isub
    //   290: istore_2
    //   291: iload_2
    //   292: ifle -> 131
    //   295: aload_0
    //   296: invokevirtual popType : ()Lgnu/bytecode/Type;
    //   299: invokevirtual promote : ()Lgnu/bytecode/Type;
    //   302: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   305: if_acmpeq -> 287
    //   308: new java/lang/Error
    //   311: dup
    //   312: ldc_w 'non-int dim. spec. in emitNewArray'
    //   315: invokespecial <init> : (Ljava/lang/String;)V
    //   318: athrow
    //   319: new java/lang/Error
    //   322: dup
    //   323: ldc_w 'unimplemented type in emitNewArray'
    //   326: invokespecial <init> : (Ljava/lang/String;)V
    //   329: athrow
  }
  
  public final void emitNot(Type paramType) {
    emitPushConstant(1, paramType);
    emitAdd();
    emitPushConstant(1, paramType);
    emitAnd();
  }
  
  public void emitPop(int paramInt) {
    while (paramInt > 0) {
      reserve(1);
      if ((popType()).size > 4) {
        put1(88);
      } else if (paramInt > 1) {
        if ((popType()).size > 4) {
          put1(87);
          reserve(1);
        } 
        put1(88);
        paramInt--;
      } else {
        put1(87);
      } 
      paramInt--;
    } 
  }
  
  public void emitPrimop(int paramInt1, int paramInt2, Type paramType) {
    reserve(1);
    while (true) {
      if (--paramInt2 >= 0) {
        popType();
        continue;
      } 
      put1(paramInt1);
      pushType(paramType);
      return;
    } 
  }
  
  public final void emitPushClass(ObjectType paramObjectType) {
    emitPushConstant(getConstants().addClass(paramObjectType));
    pushType(Type.javalangClassType);
  }
  
  public final void emitPushConstant(int paramInt, Type paramType) {
    switch (paramType.getSignature().charAt(0)) {
      default:
        throw new Error("bad type to emitPushConstant");
      case 'B':
      case 'C':
      case 'I':
      case 'S':
      case 'Z':
        emitPushInt(paramInt);
        return;
      case 'J':
        emitPushLong(paramInt);
        return;
      case 'F':
        emitPushFloat(paramInt);
        return;
      case 'D':
        break;
    } 
    emitPushDouble(paramInt);
  }
  
  public final void emitPushConstant(CpoolEntry paramCpoolEntry) {
    reserve(3);
    int i = paramCpoolEntry.index;
    if (paramCpoolEntry instanceof CpoolValue2) {
      put1(20);
      put2(i);
      return;
    } 
    if (i < 256) {
      put1(18);
      put1(i);
      return;
    } 
    put1(19);
    put2(i);
  }
  
  public void emitPushDefaultValue(Type paramType) {
    paramType = paramType.getImplementationType();
    if (paramType instanceof PrimType) {
      emitPushConstant(0, paramType);
      return;
    } 
    emitPushNull();
  }
  
  public void emitPushDouble(double paramDouble) {
    int i = (int)paramDouble;
    if (i == paramDouble && i >= -128 && i < 128) {
      if (i == 0 || i == 1) {
        reserve(1);
        put1(i + 14);
        if (i == 0 && Double.doubleToLongBits(paramDouble) != 0L) {
          reserve(1);
          put1(119);
        } 
      } else {
        emitPushInt(i);
        reserve(1);
        popType();
        put1(135);
      } 
    } else {
      emitPushConstant(getConstants().addDouble(paramDouble));
    } 
    pushType(Type.doubleType);
  }
  
  public void emitPushFloat(float paramFloat) {
    int i = (int)paramFloat;
    if (i == paramFloat && i >= -128 && i < 128) {
      if (i >= 0 && i <= 2) {
        reserve(1);
        put1(i + 11);
        if (i == 0 && Float.floatToIntBits(paramFloat) != 0) {
          reserve(1);
          put1(118);
        } 
      } else {
        emitPushInt(i);
        reserve(1);
        popType();
        put1(134);
      } 
    } else {
      emitPushConstant(getConstants().addFloat(paramFloat));
    } 
    pushType(Type.floatType);
  }
  
  public final void emitPushInt(int paramInt) {
    reserve(3);
    if (paramInt >= -1 && paramInt <= 5) {
      put1(paramInt + 3);
    } else if (paramInt >= -128 && paramInt < 128) {
      put1(16);
      put1(paramInt);
    } else if (paramInt >= -32768 && paramInt < 32768) {
      put1(17);
      put2(paramInt);
    } else {
      emitPushConstant(getConstants().addInt(paramInt));
    } 
    pushType(Type.intType);
  }
  
  public void emitPushLong(long paramLong) {
    if (paramLong == 0L || paramLong == 1L) {
      reserve(1);
      put1((int)paramLong + 9);
    } else if ((int)paramLong == paramLong) {
      emitPushInt((int)paramLong);
      reserve(1);
      popType();
      put1(133);
    } else {
      emitPushConstant(getConstants().addLong(paramLong));
    } 
    pushType(Type.longType);
  }
  
  public void emitPushNull() {
    reserve(1);
    put1(1);
    pushType(Type.nullType);
  }
  
  public final void emitPushPrimArray(Object paramObject, ArrayType paramArrayType) {
    Type type = paramArrayType.getComponentType();
    int j = Array.getLength(paramObject);
    emitPushInt(j);
    emitNewArray(type);
    char c = type.getSignature().charAt(0);
    for (int i = 0; i < j; i++) {
      double d2;
      float f2;
      long l2;
      long l1 = 0L;
      float f1 = 0.0F;
      double d1 = 0.0D;
      switch (c) {
        default:
          emitDup(paramArrayType);
          emitPushInt(i);
          switch (c) {
            default:
              emitArrayStore(type);
              continue;
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z':
              emitPushInt((int)l1);
            case 'J':
              emitPushLong(l1);
            case 'F':
              emitPushFloat(f1);
            case 'D':
              break;
          } 
          break;
        case 'J':
          l2 = ((long[])paramObject)[i];
          l1 = l2;
        case 'I':
          l2 = ((int[])paramObject)[i];
          l1 = l2;
        case 'S':
          l2 = ((short[])paramObject)[i];
          l1 = l2;
        case 'C':
          l2 = ((char[])paramObject)[i];
          l1 = l2;
        case 'B':
          l2 = ((byte[])paramObject)[i];
          l1 = l2;
        case 'Z':
        
        case 'F':
          f2 = ((float[])paramObject)[i];
          f1 = f2;
        case 'D':
          d2 = ((double[])paramObject)[i];
          d1 = d2;
      } 
      emitPushDouble(d1);
    } 
  }
  
  public final void emitPushString(String paramString) {
    if (paramString == null) {
      emitPushNull();
      return;
    } 
    int i = paramString.length();
    String str = calculateSplit(paramString);
    int j = str.length();
    if (j <= 1) {
      emitPushConstant(getConstants().addString(paramString));
      pushType(Type.javalangStringType);
      return;
    } 
    if (j == 2) {
      i = str.charAt(0);
      emitPushString(paramString.substring(0, i));
      emitPushString(paramString.substring(i));
      emitInvokeVirtual(Type.javalangStringType.getDeclaredMethod("concat", 1));
    } else {
      ClassType classType = ClassType.make("java.lang.StringBuffer");
      emitNew(classType);
      emitDup(classType);
      emitPushInt(i);
      emitInvokeSpecial(classType.getDeclaredMethod("<init>", new Type[] { Type.intType }));
      Method method = classType.getDeclaredMethod("append", new Type[] { Type.javalangStringType });
      int k = 0;
      for (i = 0; i < j; i++) {
        emitDup(classType);
        int m = k + str.charAt(i);
        emitPushString(paramString.substring(k, m));
        emitInvokeVirtual(method);
        k = m;
      } 
      emitInvokeVirtual(Type.toString_method);
    } 
    if (paramString == paramString.intern()) {
      emitInvokeVirtual(Type.javalangStringType.getDeclaredMethod("intern", 0));
      return;
    } 
  }
  
  public final void emitPushThis() {
    emitLoad(this.locals.used[0]);
  }
  
  public final void emitPutField(Field paramField) {
    popType();
    popType();
    emitFieldop(paramField, 181);
  }
  
  public final void emitPutStatic(Field paramField) {
    popType();
    emitFieldop(paramField, 179);
  }
  
  final void emitRawReturn() {
    if ((getMethod().getReturnType()).size == 0) {
      reserve(1);
      put1(177);
    } else {
      emitTypedOp(172, popType().promote());
    } 
    setUnreachable();
  }
  
  public final void emitRem() {
    emitBinop(112);
  }
  
  public void emitRet(Variable paramVariable) {
    int i = paramVariable.offset;
    if (i < 256) {
      reserve(2);
      put1(169);
      put1(i);
      return;
    } 
    reserve(4);
    put1(196);
    put1(169);
    put2(i);
  }
  
  public final void emitReturn() {
    if (this.try_stack != null)
      new Error(); 
    emitRawReturn();
  }
  
  public final void emitShl() {
    emitShift(120);
  }
  
  public final void emitShr() {
    emitShift(122);
  }
  
  public void emitStore(Variable paramVariable) {
    int i = paramVariable.offset;
    if (i < 0 || !paramVariable.isSimple())
      throw new Error("attempting to store in unassigned " + paramVariable + " simple:" + paramVariable.isSimple() + ", offset: " + i); 
    Type type = paramVariable.getType().promote();
    noteVarType(i, type);
    reserve(4);
    popType();
    int j = adjustTypedOp(type);
    if (i <= 3) {
      put1(j * 4 + 59 + i);
      return;
    } 
    emitMaybeWide(j + 54, i);
  }
  
  public void emitStoreDefaultValue(Variable paramVariable) {
    emitPushDefaultValue(paramVariable.getType());
    emitStore(paramVariable);
  }
  
  public final void emitSub() {
    emitBinop(100);
  }
  
  public final void emitSub(char paramChar) {
    emitBinop(100, paramChar);
  }
  
  public final void emitSub(PrimType paramPrimType) {
    emitBinop(100, paramPrimType);
  }
  
  public void emitSwap() {
    reserve(1);
    Type type1 = popType();
    Type type2 = popType();
    if (type1.size > 4 || type2.size > 4) {
      pushType(type2);
      pushType(type1);
      emitDupX();
      emitPop(1);
      return;
    } 
    pushType(type1);
    put1(95);
    pushType(type2);
  }
  
  public void emitTailCall(boolean paramBoolean, Scope paramScope) {
    if (paramBoolean) {
      int i;
      Method method = getMethod();
      if ((method.access_flags & 0x8) != 0) {
        i = 0;
      } else {
        i = 1;
      } 
      int j = method.arg_types.length;
      while (true) {
        int k = j - 1;
        if (k >= 0) {
          if ((method.arg_types[k]).size > 4) {
            j = 2;
          } else {
            j = 1;
          } 
          i += j;
          j = k;
          continue;
        } 
        j = method.arg_types.length;
        while (true) {
          k = j - 1;
          if (k >= 0) {
            if ((method.arg_types[k]).size > 4) {
              j = 2;
            } else {
              j = 1;
            } 
            i -= j;
            emitStore(this.locals.used[i]);
            j = k;
            continue;
          } 
          break;
        } 
        break;
      } 
    } 
    emitGoto(paramScope.start);
  }
  
  public final void emitThen() {
    this.if_stack.start_stack_size = this.SP;
  }
  
  public final void emitThrow() {
    popType();
    reserve(1);
    put1(191);
    setUnreachable();
  }
  
  final void emitTransfer(Label paramLabel, int paramInt) {
    paramLabel.setTypes(this);
    fixupAdd(6, paramLabel);
    put1(paramInt);
    this.PC += 2;
  }
  
  public void emitTryCatchEnd() {
    if (this.try_stack.finally_subr != null)
      emitFinallyEnd(); 
    Variable[] arrayOfVariable = this.try_stack.savedStack;
    if (this.try_stack.end_label == null) {
      setUnreachable();
    } else {
      setTypes(this.try_stack.start_try.localTypes, Type.typeArray0);
      this.try_stack.end_label.define(this);
      if (arrayOfVariable != null) {
        int i = arrayOfVariable.length;
        while (true) {
          int j = i - 1;
          if (j >= 0) {
            Variable variable = arrayOfVariable[j];
            i = j;
            if (variable != null) {
              emitLoad(variable);
              i = j;
            } 
            continue;
          } 
          break;
        } 
      } 
      if (this.try_stack.saved_result != null)
        emitLoad(this.try_stack.saved_result); 
    } 
    if (this.try_stack.saved_result != null || arrayOfVariable != null)
      popScope(); 
    this.try_stack = this.try_stack.previous;
  }
  
  public void emitTryEnd() {
    emitTryEnd(false);
  }
  
  public void emitTryStart(boolean paramBoolean, Type paramType) {
    Variable variable;
    int i;
    Type type = paramType;
    if (paramType != null) {
      type = paramType;
      if (paramType.isVoid())
        type = null; 
    } 
    paramType = null;
    if (type != null || this.SP > 0)
      pushScope(); 
    if (this.SP > 0) {
      Variable[] arrayOfVariable = new Variable[this.SP];
      i = 0;
      while (true) {
        Variable[] arrayOfVariable1 = arrayOfVariable;
        if (this.SP > 0) {
          variable = addLocal(topType());
          emitStore(variable);
          arrayOfVariable[i] = variable;
          i++;
          continue;
        } 
        break;
      } 
    } 
    TryState tryState = new TryState(this);
    tryState.savedStack = (Variable[])variable;
    if (this.local_types == null) {
      i = 0;
    } else {
      i = this.local_types.length;
    } 
    while (true) {
      if (i <= 0 || this.local_types[i - 1] != null) {
        Type[] arrayOfType;
        if (i == 0) {
          arrayOfType = Type.typeArray0;
        } else {
          arrayOfType = new Type[i];
          System.arraycopy(this.local_types, 0, arrayOfType, 0, i);
        } 
        tryState.start_try.localTypes = arrayOfType;
        if (type != null)
          tryState.saved_result = addLocal(type); 
        if (paramBoolean)
          tryState.finally_subr = new Label(); 
        return;
      } 
      i--;
    } 
  }
  
  public final void emitUshr() {
    emitShift(124);
  }
  
  public void emitWithCleanupCatch(Variable paramVariable) {
    Type[] arrayOfType;
    emitTryEnd();
    if (this.SP > 0) {
      arrayOfType = new Type[this.SP];
      System.arraycopy(this.stack_types, 0, arrayOfType, 0, this.SP);
      this.SP = 0;
    } else {
      arrayOfType = null;
    } 
    this.try_stack.savedTypes = arrayOfType;
    this.try_stack.saved_result = paramVariable;
    int i = this.SP;
    emitCatchStart(paramVariable);
  }
  
  public void emitWithCleanupDone() {
    Variable variable = this.try_stack.saved_result;
    this.try_stack.saved_result = null;
    if (variable != null)
      emitLoad(variable); 
    emitThrow();
    emitCatchEnd();
    Type[] arrayOfType = this.try_stack.savedTypes;
    emitTryCatchEnd();
    if (arrayOfType != null) {
      this.SP = arrayOfType.length;
      if (this.SP >= this.stack_types.length) {
        this.stack_types = arrayOfType;
        return;
      } 
      System.arraycopy(arrayOfType, 0, this.stack_types, 0, this.SP);
      return;
    } 
    this.SP = 0;
  }
  
  public void emitWithCleanupStart() {
    int i = this.SP;
    this.SP = 0;
    emitTryStart(false, (Type)null);
    this.SP = i;
  }
  
  public final void emitXOr() {
    emitBinop(130);
  }
  
  public void endExitableBlock() {
    ExitableBlock exitableBlock = this.currentExitableBlock;
    exitableBlock.finish();
    this.currentExitableBlock = exitableBlock.outer;
  }
  
  public void endFragment(int paramInt) {
    this.fixup_offsets[paramInt] = this.fixup_count << 4 | 0xA;
    Label label = this.fixup_labels[paramInt];
    fixupAdd(9, 0, (Label)null);
    label.define(this);
  }
  
  public void enterScope(Scope paramScope) {
    paramScope.setStartPC(this);
    this.locals.enterScope(paramScope);
  }
  
  final void fixupAdd(int paramInt1, int paramInt2, Label paramLabel) {
    if (paramLabel != null && paramInt1 != 1 && paramInt1 != 0 && paramInt1 != 2 && paramInt1 != 11)
      paramLabel.needsStackMapEntry = true; 
    int i = this.fixup_count;
    if (i == 0) {
      this.fixup_offsets = new int[30];
      this.fixup_labels = new Label[30];
    } else if (this.fixup_count == this.fixup_offsets.length) {
      int j = i * 2;
      Label[] arrayOfLabel = new Label[j];
      System.arraycopy(this.fixup_labels, 0, arrayOfLabel, 0, i);
      this.fixup_labels = arrayOfLabel;
      int[] arrayOfInt = new int[j];
      System.arraycopy(this.fixup_offsets, 0, arrayOfInt, 0, i);
      this.fixup_offsets = arrayOfInt;
    } 
    this.fixup_offsets[i] = paramInt2 << 4 | paramInt1;
    this.fixup_labels[i] = paramLabel;
    this.fixup_count = i + 1;
  }
  
  public final void fixupAdd(int paramInt, Label paramLabel) {
    fixupAdd(paramInt, this.PC, paramLabel);
  }
  
  public final void fixupChain(Label paramLabel1, Label paramLabel2) {
    fixupAdd(9, 0, paramLabel2);
    paramLabel1.defineRaw(this);
  }
  
  public Variable getArg(int paramInt) {
    return this.locals.parameter_scope.getVariable(paramInt);
  }
  
  public final Attribute getAttributes() {
    return this.attributes;
  }
  
  public byte[] getCode() {
    return this.code;
  }
  
  public int getCodeLength() {
    return this.PC;
  }
  
  public final ConstantPool getConstants() {
    return (getMethod()).classfile.constants;
  }
  
  public Scope getCurrentScope() {
    return this.locals.current_scope;
  }
  
  public final TryState getCurrentTry() {
    return this.try_stack;
  }
  
  public Label getLabel() {
    Label label = new Label();
    label.defineRaw(this);
    return label;
  }
  
  public final int getLength() {
    return getCodeLength() + 12 + this.exception_table_length * 8 + Attribute.getLengthAll(this);
  }
  
  public int getMaxLocals() {
    return this.max_locals;
  }
  
  public int getMaxStack() {
    return this.max_stack;
  }
  
  public final Method getMethod() {
    return (Method)getContainer();
  }
  
  public final int getPC() {
    return this.PC;
  }
  
  public final int getSP() {
    return this.SP;
  }
  
  byte invert_opcode(byte paramByte) {
    int i = paramByte & 0xFF;
    if ((i >= 153 && i <= 166) || (i >= 198 && i <= 199))
      return (byte)(i ^ 0x1); 
    throw new Error("unknown opcode to invert_opcode");
  }
  
  public final boolean isInTry() {
    return (this.try_stack != null);
  }
  
  public Variable lookup(String paramString) {
    for (Scope scope = this.locals.current_scope; scope != null; scope = scope.parent) {
      Variable variable = scope.lookup(paramString);
      if (variable != null)
        return variable; 
    } 
    return null;
  }
  
  void noteParamTypes() {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getMethod : ()Lgnu/bytecode/Method;
    //   4: astore #8
    //   6: iconst_0
    //   7: istore_1
    //   8: aload #8
    //   10: getfield access_flags : I
    //   13: bipush #8
    //   15: iand
    //   16: ifne -> 83
    //   19: aload #8
    //   21: getfield classfile : Lgnu/bytecode/ClassType;
    //   24: astore #7
    //   26: aload #7
    //   28: astore #6
    //   30: ldc_w '<init>'
    //   33: aload #8
    //   35: invokevirtual getName : ()Ljava/lang/String;
    //   38: invokevirtual equals : (Ljava/lang/Object;)Z
    //   41: ifeq -> 72
    //   44: aload #7
    //   46: astore #6
    //   48: ldc_w 'java.lang.Object'
    //   51: aload #7
    //   53: invokevirtual getName : ()Ljava/lang/String;
    //   56: invokevirtual equals : (Ljava/lang/Object;)Z
    //   59: ifne -> 72
    //   62: aload #7
    //   64: checkcast gnu/bytecode/ClassType
    //   67: invokestatic uninitializedThis : (Lgnu/bytecode/ClassType;)Lgnu/bytecode/UninitializedType;
    //   70: astore #6
    //   72: aload_0
    //   73: iconst_0
    //   74: aload #6
    //   76: invokevirtual noteVarType : (ILgnu/bytecode/Type;)V
    //   79: iconst_0
    //   80: iconst_1
    //   81: iadd
    //   82: istore_1
    //   83: aload #8
    //   85: getfield arg_types : [Lgnu/bytecode/Type;
    //   88: arraylength
    //   89: istore #4
    //   91: iconst_0
    //   92: istore_3
    //   93: iload_1
    //   94: istore_2
    //   95: iload_3
    //   96: iload #4
    //   98: if_icmpge -> 151
    //   101: aload #8
    //   103: getfield arg_types : [Lgnu/bytecode/Type;
    //   106: iload_3
    //   107: aaload
    //   108: astore #6
    //   110: iload_2
    //   111: iconst_1
    //   112: iadd
    //   113: istore_1
    //   114: aload_0
    //   115: iload_2
    //   116: aload #6
    //   118: invokevirtual noteVarType : (ILgnu/bytecode/Type;)V
    //   121: aload #6
    //   123: invokevirtual getSizeInWords : ()I
    //   126: istore_2
    //   127: iload_2
    //   128: iconst_1
    //   129: isub
    //   130: istore_2
    //   131: iload_2
    //   132: ifle -> 142
    //   135: iload_1
    //   136: iconst_1
    //   137: iadd
    //   138: istore_1
    //   139: goto -> 127
    //   142: iload_3
    //   143: iconst_1
    //   144: iadd
    //   145: istore_3
    //   146: iload_1
    //   147: istore_2
    //   148: goto -> 95
    //   151: aload_0
    //   152: getfield flags : I
    //   155: iconst_1
    //   156: iand
    //   157: ifeq -> 286
    //   160: aload_0
    //   161: new gnu/bytecode/StackMapTableAttr
    //   164: dup
    //   165: invokespecial <init> : ()V
    //   168: putfield stackMap : Lgnu/bytecode/StackMapTableAttr;
    //   171: iload_2
    //   172: bipush #20
    //   174: iadd
    //   175: newarray int
    //   177: astore #6
    //   179: iconst_0
    //   180: istore_1
    //   181: iconst_0
    //   182: istore_3
    //   183: iload_1
    //   184: iload_2
    //   185: if_icmpge -> 250
    //   188: aload_0
    //   189: getfield stackMap : Lgnu/bytecode/StackMapTableAttr;
    //   192: aload_0
    //   193: getfield local_types : [Lgnu/bytecode/Type;
    //   196: iload_1
    //   197: aaload
    //   198: aload_0
    //   199: invokevirtual encodeVerificationType : (Lgnu/bytecode/Type;Lgnu/bytecode/CodeAttr;)I
    //   202: istore #4
    //   204: aload #6
    //   206: iload_3
    //   207: iload #4
    //   209: iastore
    //   210: iload #4
    //   212: sipush #255
    //   215: iand
    //   216: istore #5
    //   218: iload #5
    //   220: iconst_3
    //   221: if_icmpeq -> 233
    //   224: iload_1
    //   225: istore #4
    //   227: iload #5
    //   229: iconst_4
    //   230: if_icmpne -> 238
    //   233: iload_1
    //   234: iconst_1
    //   235: iadd
    //   236: istore #4
    //   238: iload #4
    //   240: iconst_1
    //   241: iadd
    //   242: istore_1
    //   243: iload_3
    //   244: iconst_1
    //   245: iadd
    //   246: istore_3
    //   247: goto -> 183
    //   250: aload_0
    //   251: getfield stackMap : Lgnu/bytecode/StackMapTableAttr;
    //   254: aload #6
    //   256: putfield encodedLocals : [I
    //   259: aload_0
    //   260: getfield stackMap : Lgnu/bytecode/StackMapTableAttr;
    //   263: iload_3
    //   264: putfield countLocals : I
    //   267: aload_0
    //   268: getfield stackMap : Lgnu/bytecode/StackMapTableAttr;
    //   271: bipush #10
    //   273: newarray int
    //   275: putfield encodedStack : [I
    //   278: aload_0
    //   279: getfield stackMap : Lgnu/bytecode/StackMapTableAttr;
    //   282: iconst_0
    //   283: putfield countStack : I
    //   286: return
  }
  
  public void noteVarType(int paramInt, Type paramType) {
    int k = paramType.getSizeInWords();
    if (this.local_types == null) {
      this.local_types = new Type[paramInt + k + 20];
    } else if (paramInt + k > this.local_types.length) {
      Type[] arrayOfType = new Type[(paramInt + k) * 2];
      System.arraycopy(this.local_types, 0, arrayOfType, 0, this.local_types.length);
      this.local_types = arrayOfType;
    } 
    this.local_types[paramInt] = paramType;
    if (this.varsSetInCurrentBlock == null) {
      this.varsSetInCurrentBlock = new boolean[this.local_types.length];
    } else if (this.varsSetInCurrentBlock.length <= paramInt) {
      boolean[] arrayOfBoolean = new boolean[this.local_types.length];
      System.arraycopy(this.varsSetInCurrentBlock, 0, arrayOfBoolean, 0, this.varsSetInCurrentBlock.length);
      this.varsSetInCurrentBlock = arrayOfBoolean;
    } 
    this.varsSetInCurrentBlock[paramInt] = true;
    int i = k;
    int j = paramInt;
    if (paramInt > 0) {
      paramType = this.local_types[paramInt - 1];
      i = k;
      j = paramInt;
      if (paramType != null) {
        i = k;
        j = paramInt;
        if (paramType.getSizeInWords() == 2) {
          this.local_types[paramInt - 1] = null;
          j = paramInt;
          i = k;
        } 
      } 
    } 
    while (true) {
      if (--i > 0) {
        Type[] arrayOfType = this.local_types;
        arrayOfType[++j] = null;
        continue;
      } 
      break;
    } 
  }
  
  public Scope popScope() {
    Scope scope = this.locals.current_scope;
    this.locals.current_scope = scope.parent;
    scope.freeLocals(this);
    scope.end = getLabel();
    return scope;
  }
  
  public final Type popType() {
    if (this.SP <= 0)
      throw new Error("popType called with empty stack " + getMethod()); 
    Type[] arrayOfType = this.stack_types;
    int i = this.SP - 1;
    this.SP = i;
    Type type = arrayOfType[i];
    if (type.size == 8 && !popType().isVoid())
      throw new Error("missing void type on stack"); 
    return type;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter) {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", max_stack:");
    paramClassTypeWriter.print(this.max_stack);
    paramClassTypeWriter.print(", max_locals:");
    paramClassTypeWriter.print(this.max_locals);
    paramClassTypeWriter.print(", code_length:");
    int i = getCodeLength();
    paramClassTypeWriter.println(i);
    disAssemble(paramClassTypeWriter, 0, i);
    if (this.exception_table_length > 0) {
      paramClassTypeWriter.print("Exceptions (count: ");
      paramClassTypeWriter.print(this.exception_table_length);
      paramClassTypeWriter.println("):");
      int j = this.exception_table_length;
      i = 0;
      while (true) {
        if (--j >= 0) {
          paramClassTypeWriter.print("  start: ");
          paramClassTypeWriter.print(this.exception_table[i] & 0xFFFF);
          paramClassTypeWriter.print(", end: ");
          paramClassTypeWriter.print(this.exception_table[i + 1] & 0xFFFF);
          paramClassTypeWriter.print(", handler: ");
          paramClassTypeWriter.print(this.exception_table[i + 2] & 0xFFFF);
          paramClassTypeWriter.print(", type: ");
          int k = this.exception_table[i + 3] & 0xFFFF;
          if (k == 0) {
            paramClassTypeWriter.print("0 /* finally */");
          } else {
            paramClassTypeWriter.printOptionalIndex(k);
            paramClassTypeWriter.printConstantTersely(k, 7);
          } 
          paramClassTypeWriter.println();
          i += 4;
          continue;
        } 
        break;
      } 
    } 
    paramClassTypeWriter.printAttributes(this);
  }
  
  public void processFixups() {
    // Byte code:
    //   0: aload_0
    //   1: getfield fixup_count : I
    //   4: ifgt -> 8
    //   7: return
    //   8: iconst_0
    //   9: istore #4
    //   11: aload_0
    //   12: getfield fixup_count : I
    //   15: istore #6
    //   17: aload_0
    //   18: bipush #9
    //   20: iconst_0
    //   21: aconst_null
    //   22: invokevirtual fixupAdd : (IILgnu/bytecode/Label;)V
    //   25: iconst_0
    //   26: istore_3
    //   27: aload_0
    //   28: getfield fixup_offsets : [I
    //   31: iload_3
    //   32: iaload
    //   33: istore #9
    //   35: iload #9
    //   37: iconst_4
    //   38: ishr
    //   39: istore #8
    //   41: aload_0
    //   42: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   45: iload_3
    //   46: aaload
    //   47: astore #12
    //   49: iload #4
    //   51: istore_2
    //   52: iload_3
    //   53: istore #5
    //   55: iload #6
    //   57: istore #7
    //   59: iload #9
    //   61: bipush #15
    //   63: iand
    //   64: tableswitch default -> 140, 0 -> 159, 1 -> 181, 2 -> 203, 3 -> 159, 4 -> 214, 5 -> 270, 6 -> 297, 7 -> 140, 8 -> 159, 9 -> 343, 10 -> 324, 11 -> 151, 12 -> 140, 13 -> 140, 14 -> 170
    //   140: new java/lang/Error
    //   143: dup
    //   144: ldc_w 'unexpected fixup'
    //   147: invokespecial <init> : (Ljava/lang/String;)V
    //   150: athrow
    //   151: iload_3
    //   152: iconst_2
    //   153: iadd
    //   154: istore #5
    //   156: iload #4
    //   158: istore_2
    //   159: iload #5
    //   161: iconst_1
    //   162: iadd
    //   163: istore_3
    //   164: iload_2
    //   165: istore #4
    //   167: goto -> 27
    //   170: iload_3
    //   171: iconst_1
    //   172: iadd
    //   173: istore #5
    //   175: iload #4
    //   177: istore_2
    //   178: goto -> 159
    //   181: aload #12
    //   183: aload #12
    //   185: getfield position : I
    //   188: iload #4
    //   190: iadd
    //   191: putfield position : I
    //   194: iload #4
    //   196: istore_2
    //   197: iload_3
    //   198: istore #5
    //   200: goto -> 159
    //   203: iload #4
    //   205: iconst_3
    //   206: iadd
    //   207: istore_2
    //   208: iload_3
    //   209: istore #5
    //   211: goto -> 159
    //   214: aload #12
    //   216: getfield first_fixup : I
    //   219: iload_3
    //   220: iconst_1
    //   221: iadd
    //   222: if_icmpne -> 270
    //   225: aload_0
    //   226: iload_3
    //   227: iconst_1
    //   228: iadd
    //   229: invokespecial fixupOffset : (I)I
    //   232: iload #8
    //   234: iconst_3
    //   235: iadd
    //   236: if_icmpne -> 270
    //   239: aload_0
    //   240: getfield fixup_offsets : [I
    //   243: iload_3
    //   244: iload #8
    //   246: iconst_4
    //   247: ishl
    //   248: bipush #8
    //   250: ior
    //   251: iastore
    //   252: aload_0
    //   253: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   256: iload_3
    //   257: aconst_null
    //   258: aastore
    //   259: iload #4
    //   261: iconst_3
    //   262: isub
    //   263: istore_2
    //   264: iload_3
    //   265: istore #5
    //   267: goto -> 159
    //   270: iload #4
    //   272: istore_2
    //   273: iload_3
    //   274: istore #5
    //   276: aload_0
    //   277: getfield PC : I
    //   280: ldc_w 32768
    //   283: if_icmplt -> 159
    //   286: iload #4
    //   288: iconst_2
    //   289: iadd
    //   290: istore_2
    //   291: iload_3
    //   292: istore #5
    //   294: goto -> 159
    //   297: iload #4
    //   299: istore_2
    //   300: iload_3
    //   301: istore #5
    //   303: aload_0
    //   304: getfield PC : I
    //   307: ldc_w 32768
    //   310: if_icmplt -> 159
    //   313: iload #4
    //   315: iconst_5
    //   316: iadd
    //   317: istore_2
    //   318: iload_3
    //   319: istore #5
    //   321: goto -> 159
    //   324: aload_0
    //   325: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   328: iload #6
    //   330: aload_0
    //   331: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   334: iload_3
    //   335: iconst_1
    //   336: iadd
    //   337: aaload
    //   338: aastore
    //   339: iload #8
    //   341: istore #7
    //   343: iload_3
    //   344: iconst_1
    //   345: iadd
    //   346: aload_0
    //   347: getfield fixup_count : I
    //   350: if_icmplt -> 465
    //   353: aload_0
    //   354: getfield PC : I
    //   357: istore_2
    //   358: aload_0
    //   359: getfield fixup_offsets : [I
    //   362: iload_3
    //   363: iload_2
    //   364: iconst_4
    //   365: ishl
    //   366: bipush #9
    //   368: ior
    //   369: iastore
    //   370: aload #12
    //   372: ifnonnull -> 484
    //   375: aload_0
    //   376: getfield PC : I
    //   379: istore #9
    //   381: iconst_0
    //   382: istore #5
    //   384: iconst_0
    //   385: istore_2
    //   386: iload_2
    //   387: aload_0
    //   388: getfield fixup_count : I
    //   391: if_icmpge -> 928
    //   394: aload_0
    //   395: getfield fixup_offsets : [I
    //   398: iload_2
    //   399: iaload
    //   400: istore_3
    //   401: iload_3
    //   402: bipush #15
    //   404: iand
    //   405: istore #7
    //   407: aload_0
    //   408: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   411: iload_2
    //   412: aaload
    //   413: astore #13
    //   415: aload #13
    //   417: astore #12
    //   419: aload #13
    //   421: ifnull -> 509
    //   424: aload #13
    //   426: astore #12
    //   428: aload #13
    //   430: getfield position : I
    //   433: ifge -> 509
    //   436: new java/lang/Error
    //   439: dup
    //   440: new java/lang/StringBuilder
    //   443: dup
    //   444: invokespecial <init> : ()V
    //   447: ldc_w 'undefined label '
    //   450: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: aload #13
    //   455: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   458: invokevirtual toString : ()Ljava/lang/String;
    //   461: invokespecial <init> : (Ljava/lang/String;)V
    //   464: athrow
    //   465: aload_0
    //   466: aload_0
    //   467: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   470: iload_3
    //   471: iconst_1
    //   472: iadd
    //   473: aaload
    //   474: getfield first_fixup : I
    //   477: invokespecial fixupOffset : (I)I
    //   480: istore_2
    //   481: goto -> 358
    //   484: aload #12
    //   486: getfield first_fixup : I
    //   489: istore_3
    //   490: iload_2
    //   491: iload #4
    //   493: iadd
    //   494: aload_0
    //   495: iload_3
    //   496: invokespecial fixupOffset : (I)I
    //   499: isub
    //   500: istore #4
    //   502: iload #7
    //   504: istore #6
    //   506: goto -> 27
    //   509: aload #12
    //   511: ifnull -> 596
    //   514: iload #7
    //   516: iconst_4
    //   517: if_icmplt -> 596
    //   520: iload #7
    //   522: bipush #7
    //   524: if_icmpgt -> 596
    //   527: aload #12
    //   529: getfield first_fixup : I
    //   532: iconst_1
    //   533: iadd
    //   534: aload_0
    //   535: getfield fixup_count : I
    //   538: if_icmpge -> 596
    //   541: aload_0
    //   542: getfield fixup_offsets : [I
    //   545: aload #12
    //   547: getfield first_fixup : I
    //   550: iconst_1
    //   551: iadd
    //   552: iaload
    //   553: aload_0
    //   554: getfield fixup_offsets : [I
    //   557: aload #12
    //   559: getfield first_fixup : I
    //   562: iaload
    //   563: bipush #15
    //   565: iand
    //   566: iconst_4
    //   567: ior
    //   568: if_icmpne -> 596
    //   571: aload_0
    //   572: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   575: aload #12
    //   577: getfield first_fixup : I
    //   580: iconst_1
    //   581: iadd
    //   582: aaload
    //   583: astore #12
    //   585: aload_0
    //   586: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   589: iload_2
    //   590: aload #12
    //   592: aastore
    //   593: goto -> 509
    //   596: iload_3
    //   597: iconst_4
    //   598: ishr
    //   599: istore #8
    //   601: iload #5
    //   603: istore_3
    //   604: iload_2
    //   605: istore #6
    //   607: iload #9
    //   609: istore #4
    //   611: iload #7
    //   613: tableswitch default -> 688, 0 -> 726, 1 -> 773, 2 -> 796, 3 -> 726, 4 -> 826, 5 -> 826, 6 -> 826, 7 -> 688, 8 -> 756, 9 -> 923, 10 -> 688, 11 -> 699, 12 -> 688, 13 -> 688, 14 -> 741
    //   688: new java/lang/Error
    //   691: dup
    //   692: ldc_w 'unexpected fixup'
    //   695: invokespecial <init> : (Ljava/lang/String;)V
    //   698: athrow
    //   699: iload_2
    //   700: iconst_2
    //   701: iadd
    //   702: istore #6
    //   704: aload_0
    //   705: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   708: iload #6
    //   710: aaload
    //   711: iload #8
    //   713: iload #5
    //   715: iadd
    //   716: putfield position : I
    //   719: iload #9
    //   721: istore #4
    //   723: iload #5
    //   725: istore_3
    //   726: iload #6
    //   728: iconst_1
    //   729: iadd
    //   730: istore_2
    //   731: iload_3
    //   732: istore #5
    //   734: iload #4
    //   736: istore #9
    //   738: goto -> 386
    //   741: iload_2
    //   742: iconst_1
    //   743: iadd
    //   744: istore #6
    //   746: iload #5
    //   748: istore_3
    //   749: iload #9
    //   751: istore #4
    //   753: goto -> 726
    //   756: iload #5
    //   758: iconst_3
    //   759: isub
    //   760: istore_3
    //   761: iload #9
    //   763: iconst_3
    //   764: isub
    //   765: istore #4
    //   767: iload_2
    //   768: istore #6
    //   770: goto -> 726
    //   773: aload #12
    //   775: iload #8
    //   777: iload #5
    //   779: iadd
    //   780: putfield position : I
    //   783: iload #5
    //   785: istore_3
    //   786: iload_2
    //   787: istore #6
    //   789: iload #9
    //   791: istore #4
    //   793: goto -> 726
    //   796: iconst_3
    //   797: iload #8
    //   799: iload #5
    //   801: iadd
    //   802: isub
    //   803: iconst_3
    //   804: iand
    //   805: istore #4
    //   807: iload #5
    //   809: iload #4
    //   811: iadd
    //   812: istore_3
    //   813: iload #9
    //   815: iload #4
    //   817: iadd
    //   818: istore #4
    //   820: iload_2
    //   821: istore #6
    //   823: goto -> 726
    //   826: aload #12
    //   828: getfield position : I
    //   831: iload #8
    //   833: iload #5
    //   835: iadd
    //   836: isub
    //   837: istore_3
    //   838: iload_3
    //   839: i2s
    //   840: iload_3
    //   841: if_icmpne -> 870
    //   844: aload_0
    //   845: getfield fixup_offsets : [I
    //   848: iload_2
    //   849: iload #8
    //   851: iconst_4
    //   852: ishl
    //   853: bipush #7
    //   855: ior
    //   856: iastore
    //   857: iload #5
    //   859: istore_3
    //   860: iload_2
    //   861: istore #6
    //   863: iload #9
    //   865: istore #4
    //   867: goto -> 726
    //   870: iload #7
    //   872: bipush #6
    //   874: if_icmpne -> 913
    //   877: iconst_5
    //   878: istore_3
    //   879: iload #5
    //   881: iload_3
    //   882: iadd
    //   883: istore #4
    //   885: iload #7
    //   887: bipush #6
    //   889: if_icmpne -> 918
    //   892: iconst_5
    //   893: istore_3
    //   894: iload #9
    //   896: iload_3
    //   897: iadd
    //   898: istore #5
    //   900: iload #4
    //   902: istore_3
    //   903: iload_2
    //   904: istore #6
    //   906: iload #5
    //   908: istore #4
    //   910: goto -> 726
    //   913: iconst_2
    //   914: istore_3
    //   915: goto -> 879
    //   918: iconst_2
    //   919: istore_3
    //   920: goto -> 894
    //   923: aload #12
    //   925: ifnonnull -> 981
    //   928: iload #9
    //   930: newarray byte
    //   932: astore #14
    //   934: iconst_m1
    //   935: istore #10
    //   937: iconst_0
    //   938: istore #5
    //   940: aload_0
    //   941: iconst_0
    //   942: invokespecial fixupOffset : (I)I
    //   945: istore #4
    //   947: aconst_null
    //   948: astore #13
    //   950: iconst_0
    //   951: istore_3
    //   952: iconst_0
    //   953: istore_2
    //   954: iload_3
    //   955: iload #4
    //   957: if_icmpge -> 1003
    //   960: aload #14
    //   962: iload_2
    //   963: aload_0
    //   964: getfield code : [B
    //   967: iload_3
    //   968: baload
    //   969: bastore
    //   970: iload_3
    //   971: iconst_1
    //   972: iadd
    //   973: istore_3
    //   974: iload_2
    //   975: iconst_1
    //   976: iadd
    //   977: istore_2
    //   978: goto -> 954
    //   981: aload #12
    //   983: getfield first_fixup : I
    //   986: istore_2
    //   987: iload #8
    //   989: iload #5
    //   991: iadd
    //   992: aload_0
    //   993: iload_2
    //   994: invokespecial fixupOffset : (I)I
    //   997: isub
    //   998: istore #5
    //   1000: goto -> 386
    //   1003: aload_0
    //   1004: getfield fixup_offsets : [I
    //   1007: iload #5
    //   1009: iaload
    //   1010: bipush #15
    //   1012: iand
    //   1013: istore #6
    //   1015: aload_0
    //   1016: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   1019: iload #5
    //   1021: aaload
    //   1022: astore #15
    //   1024: aload #13
    //   1026: astore #12
    //   1028: aload #13
    //   1030: ifnull -> 1059
    //   1033: aload #13
    //   1035: astore #12
    //   1037: aload #13
    //   1039: getfield position : I
    //   1042: iload_2
    //   1043: if_icmpge -> 1059
    //   1046: aload_0
    //   1047: getfield stackMap : Lgnu/bytecode/StackMapTableAttr;
    //   1050: aload #13
    //   1052: aload_0
    //   1053: invokevirtual emitStackMapEntry : (Lgnu/bytecode/Label;Lgnu/bytecode/CodeAttr;)V
    //   1056: aconst_null
    //   1057: astore #12
    //   1059: aload #12
    //   1061: ifnull -> 1084
    //   1064: aload #12
    //   1066: getfield position : I
    //   1069: iload_2
    //   1070: if_icmple -> 1084
    //   1073: new java/lang/Error
    //   1076: dup
    //   1077: ldc_w 'labels out of order'
    //   1080: invokespecial <init> : (Ljava/lang/String;)V
    //   1083: athrow
    //   1084: iload #6
    //   1086: tableswitch default -> 1160, 0 -> 1171, 1 -> 1218, 2 -> 1525, 3 -> 1160, 4 -> 1362, 5 -> 1362, 6 -> 1362, 7 -> 1286, 8 -> 1268, 9 -> 1927, 10 -> 1160, 11 -> 1776, 12 -> 1160, 13 -> 1160, 14 -> 1865
    //   1160: new java/lang/Error
    //   1163: dup
    //   1164: ldc_w 'unexpected fixup'
    //   1167: invokespecial <init> : (Ljava/lang/String;)V
    //   1170: athrow
    //   1171: iload #10
    //   1173: istore #11
    //   1175: aload #12
    //   1177: astore #13
    //   1179: iload_3
    //   1180: istore #4
    //   1182: iload_2
    //   1183: istore_3
    //   1184: iload #5
    //   1186: iconst_1
    //   1187: iadd
    //   1188: istore #5
    //   1190: aload_0
    //   1191: iload #5
    //   1193: invokespecial fixupOffset : (I)I
    //   1196: istore #7
    //   1198: iload #4
    //   1200: istore #6
    //   1202: iload_3
    //   1203: istore_2
    //   1204: iload #7
    //   1206: istore #4
    //   1208: iload #6
    //   1210: istore_3
    //   1211: iload #11
    //   1213: istore #10
    //   1215: goto -> 954
    //   1218: aload_0
    //   1219: getfield stackMap : Lgnu/bytecode/StackMapTableAttr;
    //   1222: ifnull -> 2059
    //   1225: aload #15
    //   1227: ifnull -> 2059
    //   1230: aload #15
    //   1232: getfield stackTypes : [Lgnu/bytecode/Type;
    //   1235: ifnull -> 2059
    //   1238: aload #15
    //   1240: getfield needsStackMapEntry : Z
    //   1243: ifeq -> 2059
    //   1246: aload_0
    //   1247: aload #12
    //   1249: aload #15
    //   1251: invokespecial mergeLabels : (Lgnu/bytecode/Label;Lgnu/bytecode/Label;)Lgnu/bytecode/Label;
    //   1254: astore #13
    //   1256: iload_3
    //   1257: istore #4
    //   1259: iload_2
    //   1260: istore_3
    //   1261: iload #10
    //   1263: istore #11
    //   1265: goto -> 1184
    //   1268: iload_3
    //   1269: iconst_3
    //   1270: iadd
    //   1271: istore #4
    //   1273: iload_2
    //   1274: istore_3
    //   1275: aload #12
    //   1277: astore #13
    //   1279: iload #10
    //   1281: istore #11
    //   1283: goto -> 1184
    //   1286: aload #15
    //   1288: getfield position : I
    //   1291: iload_2
    //   1292: isub
    //   1293: istore #4
    //   1295: iload_2
    //   1296: iconst_1
    //   1297: iadd
    //   1298: istore #6
    //   1300: aload #14
    //   1302: iload_2
    //   1303: aload_0
    //   1304: getfield code : [B
    //   1307: iload_3
    //   1308: baload
    //   1309: bastore
    //   1310: iload #6
    //   1312: iconst_1
    //   1313: iadd
    //   1314: istore #7
    //   1316: aload #14
    //   1318: iload #6
    //   1320: iload #4
    //   1322: bipush #8
    //   1324: ishr
    //   1325: i2b
    //   1326: bastore
    //   1327: iload #7
    //   1329: iconst_1
    //   1330: iadd
    //   1331: istore_2
    //   1332: aload #14
    //   1334: iload #7
    //   1336: iload #4
    //   1338: sipush #255
    //   1341: iand
    //   1342: i2b
    //   1343: bastore
    //   1344: iload_3
    //   1345: iconst_3
    //   1346: iadd
    //   1347: istore #4
    //   1349: iload_2
    //   1350: istore_3
    //   1351: aload #12
    //   1353: astore #13
    //   1355: iload #10
    //   1357: istore #11
    //   1359: goto -> 1184
    //   1362: aload #15
    //   1364: getfield position : I
    //   1367: iload_2
    //   1368: isub
    //   1369: istore #4
    //   1371: aload_0
    //   1372: getfield code : [B
    //   1375: iload_3
    //   1376: baload
    //   1377: istore_1
    //   1378: iload #6
    //   1380: bipush #6
    //   1382: if_icmpne -> 1516
    //   1385: aload_0
    //   1386: iload_1
    //   1387: invokevirtual invert_opcode : (B)B
    //   1390: istore_1
    //   1391: iload_2
    //   1392: iconst_1
    //   1393: iadd
    //   1394: istore #6
    //   1396: aload #14
    //   1398: iload_2
    //   1399: iload_1
    //   1400: bastore
    //   1401: iload #6
    //   1403: iconst_1
    //   1404: iadd
    //   1405: istore #7
    //   1407: aload #14
    //   1409: iload #6
    //   1411: iconst_0
    //   1412: bastore
    //   1413: iload #7
    //   1415: iconst_1
    //   1416: iadd
    //   1417: istore_2
    //   1418: aload #14
    //   1420: iload #7
    //   1422: bipush #8
    //   1424: bastore
    //   1425: bipush #-56
    //   1427: istore_1
    //   1428: iload_2
    //   1429: iconst_1
    //   1430: iadd
    //   1431: istore #6
    //   1433: aload #14
    //   1435: iload_2
    //   1436: iload_1
    //   1437: bastore
    //   1438: iload #6
    //   1440: iconst_1
    //   1441: iadd
    //   1442: istore_2
    //   1443: aload #14
    //   1445: iload #6
    //   1447: iload #4
    //   1449: bipush #24
    //   1451: ishr
    //   1452: i2b
    //   1453: bastore
    //   1454: iload_2
    //   1455: iconst_1
    //   1456: iadd
    //   1457: istore #6
    //   1459: aload #14
    //   1461: iload_2
    //   1462: iload #4
    //   1464: bipush #16
    //   1466: ishr
    //   1467: i2b
    //   1468: bastore
    //   1469: iload #6
    //   1471: iconst_1
    //   1472: iadd
    //   1473: istore_2
    //   1474: aload #14
    //   1476: iload #6
    //   1478: iload #4
    //   1480: bipush #8
    //   1482: ishr
    //   1483: i2b
    //   1484: bastore
    //   1485: aload #14
    //   1487: iload_2
    //   1488: iload #4
    //   1490: sipush #255
    //   1493: iand
    //   1494: i2b
    //   1495: bastore
    //   1496: iload_3
    //   1497: iconst_3
    //   1498: iadd
    //   1499: istore #4
    //   1501: iload_2
    //   1502: iconst_1
    //   1503: iadd
    //   1504: istore_3
    //   1505: aload #12
    //   1507: astore #13
    //   1509: iload #10
    //   1511: istore #11
    //   1513: goto -> 1184
    //   1516: iload_1
    //   1517: bipush #33
    //   1519: iadd
    //   1520: i2b
    //   1521: istore_1
    //   1522: goto -> 1428
    //   1525: iconst_3
    //   1526: iload_2
    //   1527: isub
    //   1528: iconst_3
    //   1529: iand
    //   1530: istore #4
    //   1532: aload_0
    //   1533: getfield code : [B
    //   1536: astore #13
    //   1538: iload_3
    //   1539: iconst_1
    //   1540: iadd
    //   1541: istore #7
    //   1543: aload #14
    //   1545: iload_2
    //   1546: aload #13
    //   1548: iload_3
    //   1549: baload
    //   1550: bastore
    //   1551: iload_2
    //   1552: iconst_1
    //   1553: iadd
    //   1554: istore_3
    //   1555: iload #4
    //   1557: iconst_1
    //   1558: isub
    //   1559: istore #4
    //   1561: iload #4
    //   1563: iflt -> 2049
    //   1566: aload #14
    //   1568: iload_3
    //   1569: iconst_0
    //   1570: bastore
    //   1571: iload_3
    //   1572: iconst_1
    //   1573: iadd
    //   1574: istore_3
    //   1575: goto -> 1555
    //   1578: aload_0
    //   1579: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   1582: iload #8
    //   1584: aaload
    //   1585: getfield position : I
    //   1588: iload_2
    //   1589: isub
    //   1590: istore #5
    //   1592: iload #4
    //   1594: iconst_1
    //   1595: iadd
    //   1596: istore #6
    //   1598: aload #14
    //   1600: iload #4
    //   1602: iload #5
    //   1604: bipush #24
    //   1606: ishr
    //   1607: i2b
    //   1608: bastore
    //   1609: iload #6
    //   1611: iconst_1
    //   1612: iadd
    //   1613: istore #4
    //   1615: aload #14
    //   1617: iload #6
    //   1619: iload #5
    //   1621: bipush #16
    //   1623: ishr
    //   1624: i2b
    //   1625: bastore
    //   1626: iload #4
    //   1628: iconst_1
    //   1629: iadd
    //   1630: istore #6
    //   1632: aload #14
    //   1634: iload #4
    //   1636: iload #5
    //   1638: bipush #8
    //   1640: ishr
    //   1641: i2b
    //   1642: bastore
    //   1643: aload #14
    //   1645: iload #6
    //   1647: iload #5
    //   1649: sipush #255
    //   1652: iand
    //   1653: i2b
    //   1654: bastore
    //   1655: iload_3
    //   1656: iconst_4
    //   1657: iadd
    //   1658: istore #7
    //   1660: iload #6
    //   1662: iconst_1
    //   1663: iadd
    //   1664: istore #6
    //   1666: iload #6
    //   1668: istore_3
    //   1669: iload #8
    //   1671: istore #5
    //   1673: iload #7
    //   1675: istore #4
    //   1677: aload #12
    //   1679: astore #13
    //   1681: iload #10
    //   1683: istore #11
    //   1685: iload #8
    //   1687: aload_0
    //   1688: getfield fixup_count : I
    //   1691: if_icmpge -> 1184
    //   1694: iload #6
    //   1696: istore_3
    //   1697: iload #8
    //   1699: istore #5
    //   1701: iload #7
    //   1703: istore #4
    //   1705: aload #12
    //   1707: astore #13
    //   1709: iload #10
    //   1711: istore #11
    //   1713: aload_0
    //   1714: iload #8
    //   1716: iconst_1
    //   1717: iadd
    //   1718: invokespecial fixupKind : (I)I
    //   1721: iconst_3
    //   1722: if_icmpne -> 1184
    //   1725: iload #8
    //   1727: iconst_1
    //   1728: iadd
    //   1729: istore #8
    //   1731: aload_0
    //   1732: iload #8
    //   1734: invokespecial fixupOffset : (I)I
    //   1737: istore #5
    //   1739: iload #7
    //   1741: istore_3
    //   1742: iload #6
    //   1744: istore #4
    //   1746: iload_3
    //   1747: iload #5
    //   1749: if_icmpge -> 1578
    //   1752: aload #14
    //   1754: iload #4
    //   1756: aload_0
    //   1757: getfield code : [B
    //   1760: iload_3
    //   1761: baload
    //   1762: bastore
    //   1763: iload_3
    //   1764: iconst_1
    //   1765: iadd
    //   1766: istore_3
    //   1767: iload #4
    //   1769: iconst_1
    //   1770: iadd
    //   1771: istore #4
    //   1773: goto -> 1746
    //   1776: aload_0
    //   1777: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   1780: iload #5
    //   1782: iconst_2
    //   1783: iadd
    //   1784: aaload
    //   1785: astore #15
    //   1787: aload_0
    //   1788: iload #5
    //   1790: iconst_1
    //   1791: iadd
    //   1792: invokespecial fixupOffset : (I)I
    //   1795: istore #4
    //   1797: aload #12
    //   1799: astore #13
    //   1801: aload_0
    //   1802: getfield stackMap : Lgnu/bytecode/StackMapTableAttr;
    //   1805: ifnull -> 1818
    //   1808: aload_0
    //   1809: aload #12
    //   1811: aload #15
    //   1813: invokespecial mergeLabels : (Lgnu/bytecode/Label;Lgnu/bytecode/Label;)Lgnu/bytecode/Label;
    //   1816: astore #13
    //   1818: aload_0
    //   1819: aload_0
    //   1820: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   1823: iload #5
    //   1825: aaload
    //   1826: getfield position : I
    //   1829: aload_0
    //   1830: getfield fixup_labels : [Lgnu/bytecode/Label;
    //   1833: iload #5
    //   1835: iconst_1
    //   1836: iadd
    //   1837: aaload
    //   1838: getfield position : I
    //   1841: iload_2
    //   1842: iload #4
    //   1844: invokevirtual addHandler : (IIII)V
    //   1847: iload #5
    //   1849: iconst_2
    //   1850: iadd
    //   1851: istore #5
    //   1853: iload_3
    //   1854: istore #4
    //   1856: iload_2
    //   1857: istore_3
    //   1858: iload #10
    //   1860: istore #11
    //   1862: goto -> 1184
    //   1865: aload_0
    //   1866: getfield lines : Lgnu/bytecode/LineNumbersAttr;
    //   1869: ifnonnull -> 1884
    //   1872: aload_0
    //   1873: new gnu/bytecode/LineNumbersAttr
    //   1876: dup
    //   1877: aload_0
    //   1878: invokespecial <init> : (Lgnu/bytecode/CodeAttr;)V
    //   1881: putfield lines : Lgnu/bytecode/LineNumbersAttr;
    //   1884: iload #5
    //   1886: iconst_1
    //   1887: iadd
    //   1888: istore #5
    //   1890: aload_0
    //   1891: iload #5
    //   1893: invokespecial fixupOffset : (I)I
    //   1896: istore #11
    //   1898: iload #11
    //   1900: iload #10
    //   1902: if_icmpeq -> 1915
    //   1905: aload_0
    //   1906: getfield lines : Lgnu/bytecode/LineNumbersAttr;
    //   1909: iload #11
    //   1911: iload_2
    //   1912: invokevirtual put : (II)V
    //   1915: iload_3
    //   1916: istore #4
    //   1918: iload_2
    //   1919: istore_3
    //   1920: aload #12
    //   1922: astore #13
    //   1924: goto -> 1184
    //   1927: aload #15
    //   1929: ifnonnull -> 1977
    //   1932: iload #9
    //   1934: iload_2
    //   1935: if_icmpeq -> 2014
    //   1938: new java/lang/Error
    //   1941: dup
    //   1942: new java/lang/StringBuilder
    //   1945: dup
    //   1946: invokespecial <init> : ()V
    //   1949: ldc_w 'PC confusion new_pc:'
    //   1952: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1955: iload_2
    //   1956: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1959: ldc_w ' new_size:'
    //   1962: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1965: iload #9
    //   1967: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1970: invokevirtual toString : ()Ljava/lang/String;
    //   1973: invokespecial <init> : (Ljava/lang/String;)V
    //   1976: athrow
    //   1977: aload #15
    //   1979: getfield first_fixup : I
    //   1982: istore #5
    //   1984: aload_0
    //   1985: iload #5
    //   1987: invokespecial fixupOffset : (I)I
    //   1990: istore_3
    //   1991: iload_3
    //   1992: istore #4
    //   1994: aload #15
    //   1996: getfield position : I
    //   1999: iload_2
    //   2000: if_icmpeq -> 2042
    //   2003: new java/lang/Error
    //   2006: dup
    //   2007: ldc_w 'bad pc'
    //   2010: invokespecial <init> : (Ljava/lang/String;)V
    //   2013: athrow
    //   2014: aload_0
    //   2015: iload #9
    //   2017: putfield PC : I
    //   2020: aload_0
    //   2021: aload #14
    //   2023: putfield code : [B
    //   2026: aload_0
    //   2027: iconst_0
    //   2028: putfield fixup_count : I
    //   2031: aload_0
    //   2032: aconst_null
    //   2033: putfield fixup_labels : [Lgnu/bytecode/Label;
    //   2036: aload_0
    //   2037: aconst_null
    //   2038: putfield fixup_offsets : [I
    //   2041: return
    //   2042: aload #12
    //   2044: astore #13
    //   2046: goto -> 954
    //   2049: iload_3
    //   2050: istore #6
    //   2052: iload #5
    //   2054: istore #8
    //   2056: goto -> 1666
    //   2059: iload_3
    //   2060: istore #4
    //   2062: iload_2
    //   2063: istore_3
    //   2064: aload #12
    //   2066: astore #13
    //   2068: iload #10
    //   2070: istore #11
    //   2072: goto -> 1184
  }
  
  public Scope pushScope() {
    Scope scope = new Scope();
    if (this.locals == null)
      this.locals = new LocalVarsAttr(getMethod()); 
    enterScope(scope);
    if (this.locals.parameter_scope == null)
      this.locals.parameter_scope = scope; 
    return scope;
  }
  
  public final void pushType(Type paramType) {
    if (paramType.size == 0)
      throw new Error("pushing void type onto stack"); 
    if (this.stack_types == null || this.stack_types.length == 0) {
      this.stack_types = new Type[20];
    } else if (this.SP + 1 >= this.stack_types.length) {
      Type[] arrayOfType1 = new Type[this.stack_types.length * 2];
      System.arraycopy(this.stack_types, 0, arrayOfType1, 0, this.SP);
      this.stack_types = arrayOfType1;
    } 
    if (paramType.size == 8) {
      Type[] arrayOfType1 = this.stack_types;
      int j = this.SP;
      this.SP = j + 1;
      arrayOfType1[j] = Type.voidType;
    } 
    Type[] arrayOfType = this.stack_types;
    int i = this.SP;
    this.SP = i + 1;
    arrayOfType[i] = paramType;
    if (this.SP > this.max_stack)
      this.max_stack = this.SP; 
  }
  
  public final void put1(int paramInt) {
    byte[] arrayOfByte = this.code;
    int i = this.PC;
    this.PC = i + 1;
    arrayOfByte[i] = (byte)paramInt;
    this.unreachable_here = false;
  }
  
  public final void put2(int paramInt) {
    byte[] arrayOfByte = this.code;
    int i = this.PC;
    this.PC = i + 1;
    arrayOfByte[i] = (byte)(paramInt >> 8);
    arrayOfByte = this.code;
    i = this.PC;
    this.PC = i + 1;
    arrayOfByte[i] = (byte)paramInt;
    this.unreachable_here = false;
  }
  
  public final void put4(int paramInt) {
    byte[] arrayOfByte = this.code;
    int i = this.PC;
    this.PC = i + 1;
    arrayOfByte[i] = (byte)(paramInt >> 24);
    arrayOfByte = this.code;
    i = this.PC;
    this.PC = i + 1;
    arrayOfByte[i] = (byte)(paramInt >> 16);
    arrayOfByte = this.code;
    i = this.PC;
    this.PC = i + 1;
    arrayOfByte[i] = (byte)(paramInt >> 8);
    arrayOfByte = this.code;
    i = this.PC;
    this.PC = i + 1;
    arrayOfByte[i] = (byte)paramInt;
    this.unreachable_here = false;
  }
  
  public final void putIndex2(CpoolEntry paramCpoolEntry) {
    put2(paramCpoolEntry.index);
  }
  
  public final void putLineNumber(int paramInt) {
    int i = paramInt;
    if (this.sourceDbgExt != null)
      i = this.sourceDbgExt.fixLine(paramInt); 
    fixupAdd(14, (Label)null);
    fixupAdd(15, i, (Label)null);
  }
  
  public final void putLineNumber(String paramString, int paramInt) {
    if (paramString != null)
      (getMethod()).classfile.setSourceFile(paramString); 
    putLineNumber(paramInt);
  }
  
  public final boolean reachableHere() {
    return !this.unreachable_here;
  }
  
  public final void reserve(int paramInt) {
    if (this.code == null) {
      this.code = new byte[paramInt + 100];
      return;
    } 
    if (this.PC + paramInt > this.code.length) {
      byte[] arrayOfByte = new byte[this.code.length * 2 + paramInt];
      System.arraycopy(this.code, 0, arrayOfByte, 0, this.PC);
      this.code = arrayOfByte;
      return;
    } 
  }
  
  public final void setAttributes(Attribute paramAttribute) {
    this.attributes = paramAttribute;
  }
  
  public void setCode(byte[] paramArrayOfbyte) {
    this.code = paramArrayOfbyte;
    this.PC = paramArrayOfbyte.length;
  }
  
  public void setCodeLength(int paramInt) {
    this.PC = paramInt;
  }
  
  public void setMaxLocals(int paramInt) {
    this.max_locals = paramInt;
  }
  
  public void setMaxStack(int paramInt) {
    this.max_stack = paramInt;
  }
  
  public final void setReachable(boolean paramBoolean) {
    if (!paramBoolean) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    this.unreachable_here = paramBoolean;
  }
  
  public final void setTypes(Label paramLabel) {
    setTypes(paramLabel.localTypes, paramLabel.stackTypes);
  }
  
  public final void setTypes(Type[] paramArrayOfType1, Type[] paramArrayOfType2) {
    int j = paramArrayOfType2.length;
    int i = paramArrayOfType1.length;
    if (this.local_types != null) {
      if (i > 0)
        System.arraycopy(paramArrayOfType1, 0, this.local_types, 0, i); 
      while (i < this.local_types.length) {
        this.local_types[i] = null;
        i++;
      } 
    } 
    if (this.stack_types == null || j > this.stack_types.length) {
      this.stack_types = new Type[j];
    } else {
      i = j;
      while (true) {
        if (i < this.stack_types.length) {
          this.stack_types[i] = null;
          i++;
          continue;
        } 
        System.arraycopy(paramArrayOfType2, 0, this.stack_types, 0, j);
        this.SP = j;
        return;
      } 
    } 
    System.arraycopy(paramArrayOfType2, 0, this.stack_types, 0, j);
    this.SP = j;
  }
  
  public final void setUnreachable() {
    this.unreachable_here = true;
  }
  
  public ExitableBlock startExitableBlock(Type paramType, boolean paramBoolean) {
    ExitableBlock exitableBlock = new ExitableBlock(paramType, this, paramBoolean);
    exitableBlock.outer = this.currentExitableBlock;
    this.currentExitableBlock = exitableBlock;
    return exitableBlock;
  }
  
  public SwitchState startSwitch() {
    SwitchState switchState = new SwitchState(this);
    switchState.switchValuePushed(this);
    return switchState;
  }
  
  public final Type topType() {
    return this.stack_types[this.SP - 1];
  }
  
  boolean useJsr() {
    return ((this.flags & 0x2) == 0);
  }
  
  public void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeShort(this.max_stack);
    paramDataOutputStream.writeShort(this.max_locals);
    paramDataOutputStream.writeInt(this.PC);
    paramDataOutputStream.write(this.code, 0, this.PC);
    paramDataOutputStream.writeShort(this.exception_table_length);
    int j = this.exception_table_length;
    int i = 0;
    while (true) {
      if (--j >= 0) {
        paramDataOutputStream.writeShort(this.exception_table[i]);
        paramDataOutputStream.writeShort(this.exception_table[i + 1]);
        paramDataOutputStream.writeShort(this.exception_table[i + 2]);
        paramDataOutputStream.writeShort(this.exception_table[i + 3]);
        i += 4;
        continue;
      } 
      Attribute.writeAll(this, paramDataOutputStream);
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/CodeAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
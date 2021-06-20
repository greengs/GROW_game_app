package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;

public class genwrite extends ModuleBody {
  public static final genwrite $instance;
  
  static final Char Lit0;
  
  static final IntNum Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit13;
  
  static final SimpleSymbol Lit14;
  
  static final IntNum Lit15;
  
  static final IntNum Lit16;
  
  static final IntNum Lit17;
  
  static final IntNum Lit18;
  
  static final IntNum Lit19;
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SimpleSymbol Lit22;
  
  static final SimpleSymbol Lit23;
  
  static final SimpleSymbol Lit24;
  
  static final SimpleSymbol Lit25;
  
  static final SimpleSymbol Lit26;
  
  static final SimpleSymbol Lit27;
  
  static final SimpleSymbol Lit28;
  
  static final SimpleSymbol Lit29;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit30;
  
  static final SimpleSymbol Lit31;
  
  static final SimpleSymbol Lit32;
  
  static final SimpleSymbol Lit33;
  
  static final SimpleSymbol Lit34;
  
  static final SimpleSymbol Lit35 = (SimpleSymbol)(new SimpleSymbol("reverse-string-append")).readResolve();
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod generic$Mnwrite;
  
  public static final ModuleMethod reverse$Mnstring$Mnappend;
  
  static {
    Lit34 = (SimpleSymbol)(new SimpleSymbol("generic-write")).readResolve();
    Lit33 = (SimpleSymbol)(new SimpleSymbol("unquote-splicing")).readResolve();
    Lit32 = (SimpleSymbol)(new SimpleSymbol("unquote")).readResolve();
    Lit31 = (SimpleSymbol)(new SimpleSymbol("quasiquote")).readResolve();
    Lit30 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
    Lit29 = (SimpleSymbol)(new SimpleSymbol("pp-DO")).readResolve();
    Lit28 = (SimpleSymbol)(new SimpleSymbol("pp-BEGIN")).readResolve();
    Lit27 = (SimpleSymbol)(new SimpleSymbol("pp-LET")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("pp-AND")).readResolve();
    Lit25 = (SimpleSymbol)(new SimpleSymbol("pp-CASE")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("pp-COND")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("pp-IF")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("pp-LAMBDA")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("pp-expr-list")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("pp-expr")).readResolve();
    Lit19 = IntNum.make(2);
    Lit18 = IntNum.make(50);
    Lit17 = IntNum.make(1);
    Lit16 = IntNum.make(8);
    Lit15 = IntNum.make(7);
    Lit14 = (SimpleSymbol)(new SimpleSymbol("do")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("or")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("and")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("case")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("cond")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("set!")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("if")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("define")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("letrec")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("let*")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
    Lit1 = IntNum.make(0);
    Lit0 = Char.make(10);
    $instance = new genwrite();
    genwrite genwrite1 = $instance;
    generic$Mnwrite = new ModuleMethod(genwrite1, 12, Lit34, 16388);
    reverse$Mnstring$Mnappend = new ModuleMethod(genwrite1, 13, Lit35, 4097);
    $instance.run();
  }
  
  public genwrite() {
    ModuleInfo.register(this);
  }
  
  public static Object genericWrite(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    frame frame = new frame();
    frame.display$Qu = paramObject2;
    frame.width = paramObject3;
    frame.output = paramObject4;
    if (frame.width != Boolean.FALSE) {
      paramObject2 = strings.makeString(1, Lit0);
      paramObject3 = Lit1;
      paramObject4 = new frame0();
      ((frame0)paramObject4).staticLink = frame;
      Procedure procedure1 = ((frame0)paramObject4).pp$Mnexpr;
      Procedure procedure2 = ((frame0)paramObject4).pp$Mnexpr$Mnlist;
      Procedure procedure3 = ((frame0)paramObject4).pp$MnLAMBDA;
      Procedure procedure4 = ((frame0)paramObject4).pp$MnIF;
      Procedure procedure5 = ((frame0)paramObject4).pp$MnCOND;
      Procedure procedure6 = ((frame0)paramObject4).pp$MnCASE;
      Procedure procedure7 = ((frame0)paramObject4).pp$MnAND;
      Procedure procedure8 = ((frame0)paramObject4).pp$MnLET;
      Procedure procedure9 = ((frame0)paramObject4).pp$MnBEGIN;
      ((frame0)paramObject4).pp$MnDO = ((frame0)paramObject4).pp$MnDO;
      ((frame0)paramObject4).pp$MnBEGIN = procedure9;
      ((frame0)paramObject4).pp$MnLET = procedure8;
      ((frame0)paramObject4).pp$MnAND = procedure7;
      ((frame0)paramObject4).pp$MnCASE = procedure6;
      ((frame0)paramObject4).pp$MnCOND = procedure5;
      ((frame0)paramObject4).pp$MnIF = procedure4;
      ((frame0)paramObject4).pp$MnLAMBDA = procedure3;
      ((frame0)paramObject4).pp$Mnexpr$Mnlist = procedure2;
      ((frame0)paramObject4).pp$Mnexpr = procedure1;
      return frame.lambda4out(paramObject2, paramObject4.lambda7pr(paramObject1, paramObject3, Lit1, ((frame0)paramObject4).pp$Mnexpr));
    } 
    return frame.lambda5wr(paramObject1, Lit1);
  }
  
  public static Object lambda23revStringAppend(Object paramObject1, Object paramObject2) {
    if (lists.isPair(paramObject1)) {
      Object object = lists.car.apply1(paramObject1);
      try {
        CharSequence charSequence = (CharSequence)object;
        int i = strings.stringLength(charSequence);
        Object object1 = lambda23revStringAppend(lists.cdr.apply1(paramObject1), AddOp.$Pl.apply2(paramObject2, Integer.valueOf(i)));
        IntNum intNum = Lit1;
        paramObject1 = AddOp.$Mn;
        AddOp addOp = AddOp.$Mn;
        try {
          CharSequence charSequence1 = (CharSequence)object1;
          paramObject1 = paramObject1.apply2(addOp.apply2(Integer.valueOf(strings.stringLength(charSequence1)), paramObject2), Integer.valueOf(i));
          paramObject2 = intNum;
          while (true) {
            Object object2 = object1;
            if (Scheme.numLss.apply2(paramObject2, Integer.valueOf(i)) != Boolean.FALSE)
              try {
                object2 = object1;
                try {
                  int j = ((Number)paramObject1).intValue();
                  try {
                    CharSequence charSequence2 = (CharSequence)object;
                    try {
                      int k = ((Number)paramObject2).intValue();
                      strings.stringSet$Ex((CharSeq)object2, j, strings.stringRef(charSequence2, k));
                      paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit17);
                      paramObject1 = AddOp.$Pl.apply2(paramObject1, Lit17);
                    } catch (ClassCastException null) {
                      throw new WrongType(classCastException1, "string-ref", 2, paramObject2);
                    } 
                  } catch (ClassCastException classCastException1) {
                    throw new WrongType(classCastException1, "string-ref", 1, object);
                  } 
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string-set!", 2, classCastException1);
                } 
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string-set!", 1, object1);
              }  
            return object2;
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "string-length", 1, object1);
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "string-length", 1, object);
      } 
    } 
    try {
      int i = ((Number)classCastException).intValue();
      return strings.makeString(i);
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "make-string", 1, classCastException);
    } 
  }
  
  public static Object reverseStringAppend(Object paramObject) {
    return lambda23revStringAppend(paramObject, Lit1);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    return (paramModuleMethod.selector == 13) ? reverseStringAppend(paramObject) : super.apply1(paramModuleMethod, paramObject);
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    return (paramModuleMethod.selector == 12) ? genericWrite(paramObject1, paramObject2, paramObject3, paramObject4) : super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 13) {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    } 
    return super.match1(paramModuleMethod, paramObject, paramCallContext);
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 12) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 4;
      return 0;
    } 
    return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
  
  public class frame extends ModuleBody {
    Object display$Qu;
    
    Object output;
    
    Object width;
    
    public static Object lambda1isReadMacro(Object param1Object) {
      Object object1 = lists.car.apply1(param1Object);
      param1Object = lists.cdr.apply1(param1Object);
      Object object2 = Scheme.isEqv.apply2(object1, genwrite.Lit30);
      if (object2 != Boolean.FALSE) {
        if (object2 != Boolean.FALSE)
          boolean bool = lists.isPair(param1Object); 
      } else {
        object2 = Scheme.isEqv.apply2(object1, genwrite.Lit31);
        if (object2 != Boolean.FALSE) {
          if (object2 == Boolean.FALSE)
            return Boolean.FALSE; 
        } else {
          object2 = Scheme.isEqv.apply2(object1, genwrite.Lit32);
          if ((object2 != Boolean.FALSE) ? (object2 != Boolean.FALSE) : (Scheme.isEqv.apply2(object1, genwrite.Lit33) != Boolean.FALSE))
            boolean bool1 = lists.isPair(param1Object); 
          return Boolean.FALSE;
        } 
        boolean bool = lists.isPair(param1Object);
      } 
      return Boolean.FALSE;
    }
    
    public static Object lambda2readMacroBody(Object param1Object) {
      return lists.cadr.apply1(param1Object);
    }
    
    public static Object lambda3readMacroPrefix(Object param1Object) {
      Object object = lists.car.apply1(param1Object);
      lists.cdr.apply1(param1Object);
      return (Scheme.isEqv.apply2(object, genwrite.Lit30) != Boolean.FALSE) ? "'" : ((Scheme.isEqv.apply2(object, genwrite.Lit31) != Boolean.FALSE) ? "`" : ((Scheme.isEqv.apply2(object, genwrite.Lit32) != Boolean.FALSE) ? "," : ((Scheme.isEqv.apply2(object, genwrite.Lit33) != Boolean.FALSE) ? ",@" : Values.empty)));
    }
    
    public Object lambda4out(Object param1Object1, Object param1Object2) {
      if (param1Object2 != Boolean.FALSE) {
        Object object2 = Scheme.applyToArgs.apply2(this.output, param1Object1);
        Object object1 = object2;
        if (object2 != Boolean.FALSE) {
          object1 = AddOp.$Pl;
          try {
            object2 = param1Object1;
            return object1.apply2(param1Object2, Integer.valueOf(strings.stringLength((CharSequence)object2)));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-length", 1, param1Object1);
          } 
        } 
        return object1;
      } 
      return classCastException;
    }
    
    public Object lambda5wr(Object param1Object1, Object param1Object2) {
      // Byte code:
      //   0: aload_1
      //   1: invokestatic isPair : (Ljava/lang/Object;)Z
      //   4: ifeq -> 186
      //   7: aload_1
      //   8: invokestatic lambda1isReadMacro : (Ljava/lang/Object;)Ljava/lang/Object;
      //   11: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   14: if_acmpeq -> 37
      //   17: aload_0
      //   18: aload_1
      //   19: invokestatic lambda2readMacroBody : (Ljava/lang/Object;)Ljava/lang/Object;
      //   22: aload_0
      //   23: aload_1
      //   24: invokestatic lambda3readMacroPrefix : (Ljava/lang/Object;)Ljava/lang/Object;
      //   27: aload_2
      //   28: invokevirtual lambda4out : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   31: invokevirtual lambda5wr : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   34: astore_3
      //   35: aload_3
      //   36: areturn
      //   37: aload_2
      //   38: astore #4
      //   40: aload_1
      //   41: astore_3
      //   42: aload_3
      //   43: invokestatic isPair : (Ljava/lang/Object;)Z
      //   46: ifeq -> 177
      //   49: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
      //   52: aload_3
      //   53: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   56: astore #5
      //   58: aload #5
      //   60: astore_2
      //   61: aload #4
      //   63: astore_1
      //   64: aload #4
      //   66: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   69: if_acmpeq -> 95
      //   72: aload_0
      //   73: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
      //   76: aload_3
      //   77: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   80: aload_0
      //   81: ldc '('
      //   83: aload #4
      //   85: invokevirtual lambda4out : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   88: invokevirtual lambda5wr : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   91: astore_1
      //   92: aload #5
      //   94: astore_2
      //   95: aload_1
      //   96: astore_3
      //   97: aload_1
      //   98: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   101: if_acmpeq -> 35
      //   104: aload_2
      //   105: invokestatic isPair : (Ljava/lang/Object;)Z
      //   108: ifeq -> 143
      //   111: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
      //   114: aload_2
      //   115: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   118: astore_3
      //   119: aload_0
      //   120: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
      //   123: aload_2
      //   124: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   127: aload_0
      //   128: ldc ' '
      //   130: aload_1
      //   131: invokevirtual lambda4out : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   134: invokevirtual lambda5wr : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   137: astore_1
      //   138: aload_3
      //   139: astore_2
      //   140: goto -> 95
      //   143: aload_2
      //   144: invokestatic isNull : (Ljava/lang/Object;)Z
      //   147: ifeq -> 158
      //   150: aload_0
      //   151: ldc ')'
      //   153: aload_1
      //   154: invokevirtual lambda4out : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   157: areturn
      //   158: aload_0
      //   159: ldc ')'
      //   161: aload_0
      //   162: aload_2
      //   163: aload_0
      //   164: ldc ' . '
      //   166: aload_1
      //   167: invokevirtual lambda4out : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   170: invokevirtual lambda5wr : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   173: invokevirtual lambda4out : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   176: areturn
      //   177: aload_0
      //   178: ldc '()'
      //   180: aload #4
      //   182: invokevirtual lambda4out : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   185: areturn
      //   186: aload_1
      //   187: astore_3
      //   188: aload_2
      //   189: astore #4
      //   191: aload_1
      //   192: invokestatic isNull : (Ljava/lang/Object;)Z
      //   195: ifne -> 42
      //   198: aload_1
      //   199: invokestatic isVector : (Ljava/lang/Object;)Z
      //   202: ifeq -> 227
      //   205: aload_1
      //   206: checkcast gnu/lists/FVector
      //   209: astore_3
      //   210: aload_3
      //   211: invokestatic vector$To$List : (Lgnu/lists/FVector;)Lgnu/lists/LList;
      //   214: astore_3
      //   215: aload_0
      //   216: ldc '#'
      //   218: aload_2
      //   219: invokevirtual lambda4out : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   222: astore #4
      //   224: goto -> 42
      //   227: aload_0
      //   228: getfield display$Qu : Ljava/lang/Object;
      //   231: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   234: if_acmpeq -> 262
      //   237: ldc '~a'
      //   239: astore_3
      //   240: aload_0
      //   241: iconst_0
      //   242: iconst_2
      //   243: anewarray java/lang/Object
      //   246: dup
      //   247: iconst_0
      //   248: aload_3
      //   249: aastore
      //   250: dup
      //   251: iconst_1
      //   252: aload_1
      //   253: aastore
      //   254: invokestatic formatToString : (I[Ljava/lang/Object;)Ljava/lang/String;
      //   257: aload_2
      //   258: invokevirtual lambda4out : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   261: areturn
      //   262: ldc '~s'
      //   264: astore_3
      //   265: goto -> 240
      //   268: astore_2
      //   269: new gnu/mapping/WrongType
      //   272: dup
      //   273: aload_2
      //   274: ldc 'vector->list'
      //   276: iconst_1
      //   277: aload_1
      //   278: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   281: athrow
      // Exception table:
      //   from	to	target	type
      //   205	210	268	java/lang/ClassCastException
    }
  }
  
  public class frame0 extends ModuleBody {
    Procedure pp$MnAND = (Procedure)new ModuleMethod(this, 8, genwrite.Lit26, 12291);
    
    Procedure pp$MnBEGIN = (Procedure)new ModuleMethod(this, 10, genwrite.Lit28, 12291);
    
    Procedure pp$MnCASE = (Procedure)new ModuleMethod(this, 7, genwrite.Lit25, 12291);
    
    Procedure pp$MnCOND = (Procedure)new ModuleMethod(this, 6, genwrite.Lit24, 12291);
    
    Procedure pp$MnDO = (Procedure)new ModuleMethod(this, 11, genwrite.Lit29, 12291);
    
    Procedure pp$MnIF = (Procedure)new ModuleMethod(this, 5, genwrite.Lit23, 12291);
    
    Procedure pp$MnLAMBDA = (Procedure)new ModuleMethod(this, 4, genwrite.Lit22, 12291);
    
    Procedure pp$MnLET = (Procedure)new ModuleMethod(this, 9, genwrite.Lit27, 12291);
    
    Procedure pp$Mnexpr = (Procedure)new ModuleMethod(this, 2, genwrite.Lit20, 12291);
    
    Procedure pp$Mnexpr$Mnlist = (Procedure)new ModuleMethod(this, 3, genwrite.Lit21, 12291);
    
    genwrite.frame staticLink;
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
        case 2:
          return lambda8ppExpr(param1Object1, param1Object2, param1Object3);
        case 3:
          return lambda13ppExprList(param1Object1, param1Object2, param1Object3);
        case 4:
          return lambda14pp$MnLAMBDA(param1Object1, param1Object2, param1Object3);
        case 5:
          return lambda15pp$MnIF(param1Object1, param1Object2, param1Object3);
        case 6:
          return lambda16pp$MnCOND(param1Object1, param1Object2, param1Object3);
        case 7:
          return lambda17pp$MnCASE(param1Object1, param1Object2, param1Object3);
        case 8:
          return lambda18pp$MnAND(param1Object1, param1Object2, param1Object3);
        case 9:
          return lambda19pp$MnLET(param1Object1, param1Object2, param1Object3);
        case 10:
          return lambda20pp$MnBEGIN(param1Object1, param1Object2, param1Object3);
        case 11:
          break;
      } 
      return lambda21pp$MnDO(param1Object1, param1Object2, param1Object3);
    }
    
    public Object lambda10ppList(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4) {
      param1Object2 = this.staticLink.lambda4out("(", param1Object2);
      return lambda11ppDown(param1Object1, param1Object2, param1Object2, param1Object3, param1Object4);
    }
    
    public Object lambda11ppDown(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4, Object param1Object5) {
      while (param1Object2 != Boolean.FALSE) {
        if (lists.isPair(param1Object1)) {
          Object object1;
          Object object2 = lists.cdr.apply1(param1Object1);
          if (lists.isNull(object2)) {
            object1 = AddOp.$Pl.apply2(param1Object4, genwrite.Lit17);
          } else {
            object1 = genwrite.Lit1;
          } 
          param1Object2 = lambda7pr(lists.car.apply1(param1Object1), lambda6indent(param1Object3, param1Object2), object1, param1Object5);
          param1Object1 = object2;
          continue;
        } 
        return lists.isNull(param1Object1) ? this.staticLink.lambda4out(")", param1Object2) : this.staticLink.lambda4out(")", lambda7pr(param1Object1, lambda6indent(param1Object3, this.staticLink.lambda4out(".", lambda6indent(param1Object3, param1Object2))), AddOp.$Pl.apply2(param1Object4, genwrite.Lit17), param1Object5));
      } 
      return param1Object2;
    }
    
    public Object lambda12ppGeneral(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4, Object param1Object5, Object param1Object6, Object param1Object7) {
      // Byte code:
      //   0: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
      //   3: aload_1
      //   4: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   7: astore #9
      //   9: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
      //   12: aload_1
      //   13: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   16: astore #8
      //   18: aload_0
      //   19: getfield staticLink : Lgnu/kawa/slib/genwrite$frame;
      //   22: aload #9
      //   24: aload_0
      //   25: getfield staticLink : Lgnu/kawa/slib/genwrite$frame;
      //   28: ldc '('
      //   30: aload_2
      //   31: invokevirtual lambda4out : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   34: invokevirtual lambda5wr : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   37: astore_1
      //   38: aload #4
      //   40: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   43: if_acmpeq -> 283
      //   46: aload #8
      //   48: invokestatic isPair : (Ljava/lang/Object;)Z
      //   51: ifeq -> 291
      //   54: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
      //   57: aload #8
      //   59: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   62: astore #9
      //   64: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
      //   67: aload #8
      //   69: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   72: astore #4
      //   74: aload_0
      //   75: getfield staticLink : Lgnu/kawa/slib/genwrite$frame;
      //   78: aload #9
      //   80: aload_0
      //   81: getfield staticLink : Lgnu/kawa/slib/genwrite$frame;
      //   84: ldc ' '
      //   86: aload_1
      //   87: invokevirtual lambda4out : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   90: invokevirtual lambda5wr : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   93: astore_1
      //   94: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   97: aload_2
      //   98: getstatic gnu/kawa/slib/genwrite.Lit19 : Lgnu/math/IntNum;
      //   101: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   104: astore #9
      //   106: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   109: aload_1
      //   110: getstatic gnu/kawa/slib/genwrite.Lit17 : Lgnu/math/IntNum;
      //   113: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   116: astore #10
      //   118: aload #5
      //   120: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   123: if_acmpeq -> 322
      //   126: aload_1
      //   127: astore_2
      //   128: aload #4
      //   130: astore #8
      //   132: aload #4
      //   134: invokestatic isPair : (Ljava/lang/Object;)Z
      //   137: ifeq -> 196
      //   140: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
      //   143: aload #4
      //   145: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   148: astore #11
      //   150: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
      //   153: aload #4
      //   155: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   158: astore #8
      //   160: aload #8
      //   162: invokestatic isNull : (Ljava/lang/Object;)Z
      //   165: ifeq -> 339
      //   168: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   171: aload_3
      //   172: getstatic gnu/kawa/slib/genwrite.Lit17 : Lgnu/math/IntNum;
      //   175: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   178: astore_2
      //   179: aload_0
      //   180: aload #11
      //   182: aload_0
      //   183: aload #10
      //   185: aload_1
      //   186: invokevirtual lambda6indent : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   189: aload_2
      //   190: aload #5
      //   192: invokevirtual lambda7pr : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   195: astore_2
      //   196: aload #6
      //   198: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   201: if_acmpeq -> 346
      //   204: aload #8
      //   206: invokestatic isPair : (Ljava/lang/Object;)Z
      //   209: ifeq -> 354
      //   212: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
      //   215: aload #8
      //   217: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   220: astore #5
      //   222: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
      //   225: aload #8
      //   227: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   230: astore #4
      //   232: aload #4
      //   234: invokestatic isNull : (Ljava/lang/Object;)Z
      //   237: ifeq -> 360
      //   240: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   243: aload_3
      //   244: getstatic gnu/kawa/slib/genwrite.Lit17 : Lgnu/math/IntNum;
      //   247: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   250: astore_1
      //   251: aload_0
      //   252: aload #5
      //   254: aload_0
      //   255: aload #10
      //   257: aload_2
      //   258: invokevirtual lambda6indent : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   261: aload_1
      //   262: aload #6
      //   264: invokevirtual lambda7pr : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   267: astore_2
      //   268: aload #4
      //   270: astore_1
      //   271: aload_0
      //   272: aload_1
      //   273: aload_2
      //   274: aload #9
      //   276: aload_3
      //   277: aload #7
      //   279: invokevirtual lambda11ppDown : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   282: areturn
      //   283: aload #4
      //   285: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   288: if_acmpne -> 54
      //   291: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   294: aload_2
      //   295: getstatic gnu/kawa/slib/genwrite.Lit19 : Lgnu/math/IntNum;
      //   298: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   301: astore #9
      //   303: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   306: aload_1
      //   307: getstatic gnu/kawa/slib/genwrite.Lit17 : Lgnu/math/IntNum;
      //   310: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   313: astore #10
      //   315: aload #8
      //   317: astore #4
      //   319: goto -> 118
      //   322: aload_1
      //   323: astore_2
      //   324: aload #4
      //   326: astore #8
      //   328: aload #5
      //   330: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   333: if_acmpeq -> 196
      //   336: goto -> 140
      //   339: getstatic gnu/kawa/slib/genwrite.Lit1 : Lgnu/math/IntNum;
      //   342: astore_2
      //   343: goto -> 179
      //   346: aload #6
      //   348: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   351: if_acmpne -> 212
      //   354: aload #8
      //   356: astore_1
      //   357: goto -> 271
      //   360: getstatic gnu/kawa/slib/genwrite.Lit1 : Lgnu/math/IntNum;
      //   363: astore_1
      //   364: goto -> 251
    }
    
    public Object lambda13ppExprList(Object param1Object1, Object param1Object2, Object param1Object3) {
      return lambda10ppList(param1Object1, param1Object2, param1Object3, this.pp$Mnexpr);
    }
    
    public Object lambda14pp$MnLAMBDA(Object param1Object1, Object param1Object2, Object param1Object3) {
      return lambda12ppGeneral(param1Object1, param1Object2, param1Object3, Boolean.FALSE, this.pp$Mnexpr$Mnlist, Boolean.FALSE, this.pp$Mnexpr);
    }
    
    public Object lambda15pp$MnIF(Object param1Object1, Object param1Object2, Object param1Object3) {
      return lambda12ppGeneral(param1Object1, param1Object2, param1Object3, Boolean.FALSE, this.pp$Mnexpr, Boolean.FALSE, this.pp$Mnexpr);
    }
    
    public Object lambda16pp$MnCOND(Object param1Object1, Object param1Object2, Object param1Object3) {
      return lambda9ppCall(param1Object1, param1Object2, param1Object3, this.pp$Mnexpr$Mnlist);
    }
    
    public Object lambda17pp$MnCASE(Object param1Object1, Object param1Object2, Object param1Object3) {
      return lambda12ppGeneral(param1Object1, param1Object2, param1Object3, Boolean.FALSE, this.pp$Mnexpr, Boolean.FALSE, this.pp$Mnexpr$Mnlist);
    }
    
    public Object lambda18pp$MnAND(Object param1Object1, Object param1Object2, Object param1Object3) {
      return lambda9ppCall(param1Object1, param1Object2, param1Object3, this.pp$Mnexpr);
    }
    
    public Object lambda19pp$MnLET(Object param1Object1, Object param1Object2, Object param1Object3) {
      Object object = lists.cdr.apply1(param1Object1);
      boolean bool = lists.isPair(object);
      if (bool)
        bool = misc.isSymbol(lists.car.apply1(object)); 
      if (bool) {
        object = Boolean.TRUE;
        return lambda12ppGeneral(param1Object1, param1Object2, param1Object3, object, this.pp$Mnexpr$Mnlist, Boolean.FALSE, this.pp$Mnexpr);
      } 
      object = Boolean.FALSE;
      return lambda12ppGeneral(param1Object1, param1Object2, param1Object3, object, this.pp$Mnexpr$Mnlist, Boolean.FALSE, this.pp$Mnexpr);
    }
    
    public Object lambda20pp$MnBEGIN(Object param1Object1, Object param1Object2, Object param1Object3) {
      return lambda12ppGeneral(param1Object1, param1Object2, param1Object3, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, this.pp$Mnexpr);
    }
    
    public Object lambda21pp$MnDO(Object param1Object1, Object param1Object2, Object param1Object3) {
      return lambda12ppGeneral(param1Object1, param1Object2, param1Object3, Boolean.FALSE, this.pp$Mnexpr$Mnlist, this.pp$Mnexpr$Mnlist, this.pp$Mnexpr);
    }
    
    public Object lambda6indent(Object param1Object1, Object param1Object2) {
      if (param1Object2 != Boolean.FALSE) {
        Object object;
        if (Scheme.numLss.apply2(param1Object1, param1Object2) != Boolean.FALSE) {
          object = this.staticLink.lambda4out(strings.makeString(1, genwrite.Lit0), param1Object2);
          param1Object2 = object;
          if (object != Boolean.FALSE) {
            param1Object2 = genwrite.Lit1;
          } else {
            return param1Object2;
          } 
        } else {
          param1Object1 = AddOp.$Mn.apply2(param1Object1, param1Object2);
        } 
        while (true) {
          object = param1Object2;
          if (Scheme.numGrt.apply2(param1Object1, genwrite.Lit1) != Boolean.FALSE) {
            if (Scheme.numGrt.apply2(param1Object1, genwrite.Lit15) != Boolean.FALSE) {
              param1Object1 = AddOp.$Mn.apply2(param1Object1, genwrite.Lit16);
              param1Object2 = this.staticLink.lambda4out("        ", param1Object2);
              continue;
            } 
            object = this.staticLink;
            try {
              int i = ((Number)param1Object1).intValue();
              object = object.lambda4out(strings.substring("        ", 0, i), param1Object2);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "substring", 3, param1Object1);
            } 
          } 
          break;
        } 
        return object;
      } 
      return param1Object2;
    }
    
    public Object lambda7pr(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4) {
      frame1 frame1 = new frame1();
      frame1.staticLink = this;
      boolean bool = lists.isPair(param1Object1);
      if (bool ? bool : vectors.isVector(param1Object1)) {
        LList lList = LList.Empty;
        frame1.left = numbers.min(new Object[] { AddOp.$Pl.apply2(AddOp.$Mn.apply2(AddOp.$Mn.apply2(this.staticLink.width, param1Object2), param1Object3), genwrite.Lit17), genwrite.Lit18 });
        frame1.result = lList;
        genwrite.genericWrite(param1Object1, this.staticLink.display$Qu, Boolean.FALSE, frame1.lambda$Fn1);
        if (Scheme.numGrt.apply2(frame1.left, genwrite.Lit1) != Boolean.FALSE)
          return this.staticLink.lambda4out(genwrite.reverseStringAppend(frame1.result), param1Object2); 
      } else {
        return this.staticLink.lambda5wr(param1Object1, param1Object2);
      } 
      if (lists.isPair(param1Object1))
        return Scheme.applyToArgs.apply4(param1Object4, param1Object1, param1Object2, param1Object3); 
      try {
        param1Object4 = param1Object1;
        return lambda10ppList(vectors.vector$To$List((FVector)param1Object4), this.staticLink.lambda4out("#", param1Object2), param1Object3, this.pp$Mnexpr);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "vector->list", 1, param1Object1);
      } 
    }
    
    public Object lambda8ppExpr(Object param1Object1, Object param1Object2, Object param1Object3) {
      // Byte code:
      //   0: aload_1
      //   1: invokestatic lambda1isReadMacro : (Ljava/lang/Object;)Ljava/lang/Object;
      //   4: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   7: if_acmpeq -> 36
      //   10: aload_0
      //   11: aload_1
      //   12: invokestatic lambda2readMacroBody : (Ljava/lang/Object;)Ljava/lang/Object;
      //   15: aload_0
      //   16: getfield staticLink : Lgnu/kawa/slib/genwrite$frame;
      //   19: aload_1
      //   20: invokestatic lambda3readMacroPrefix : (Ljava/lang/Object;)Ljava/lang/Object;
      //   23: aload_2
      //   24: invokevirtual lambda4out : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   27: aload_3
      //   28: aload_0
      //   29: getfield pp$Mnexpr : Lgnu/mapping/Procedure;
      //   32: invokevirtual lambda7pr : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   35: areturn
      //   36: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
      //   39: aload_1
      //   40: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   43: astore #5
      //   45: aload #5
      //   47: invokestatic isSymbol : (Ljava/lang/Object;)Z
      //   50: ifeq -> 489
      //   53: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   56: aload #5
      //   58: getstatic gnu/kawa/slib/genwrite.Lit2 : Lgnu/mapping/SimpleSymbol;
      //   61: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   64: astore #4
      //   66: aload #4
      //   68: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   71: if_acmpeq -> 108
      //   74: aload #4
      //   76: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   79: if_acmpeq -> 137
      //   82: aload_0
      //   83: getfield pp$MnLAMBDA : Lgnu/mapping/Procedure;
      //   86: astore #4
      //   88: aload #4
      //   90: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   93: if_acmpeq -> 437
      //   96: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   99: aload #4
      //   101: aload_1
      //   102: aload_2
      //   103: aload_3
      //   104: invokevirtual apply4 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   107: areturn
      //   108: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   111: aload #5
      //   113: getstatic gnu/kawa/slib/genwrite.Lit3 : Lgnu/mapping/SimpleSymbol;
      //   116: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   119: astore #4
      //   121: aload #4
      //   123: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   126: if_acmpeq -> 175
      //   129: aload #4
      //   131: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   134: if_acmpne -> 82
      //   137: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   140: aload #5
      //   142: getstatic gnu/kawa/slib/genwrite.Lit6 : Lgnu/mapping/SimpleSymbol;
      //   145: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   148: astore #4
      //   150: aload #4
      //   152: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   155: if_acmpeq -> 227
      //   158: aload #4
      //   160: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   163: if_acmpeq -> 244
      //   166: aload_0
      //   167: getfield pp$MnIF : Lgnu/mapping/Procedure;
      //   170: astore #4
      //   172: goto -> 88
      //   175: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   178: aload #5
      //   180: getstatic gnu/kawa/slib/genwrite.Lit4 : Lgnu/mapping/SimpleSymbol;
      //   183: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   186: astore #4
      //   188: aload #4
      //   190: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   193: if_acmpeq -> 207
      //   196: aload #4
      //   198: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   201: if_acmpeq -> 137
      //   204: goto -> 82
      //   207: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   210: aload #5
      //   212: getstatic gnu/kawa/slib/genwrite.Lit5 : Lgnu/mapping/SimpleSymbol;
      //   215: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   218: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   221: if_acmpeq -> 137
      //   224: goto -> 82
      //   227: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   230: aload #5
      //   232: getstatic gnu/kawa/slib/genwrite.Lit7 : Lgnu/mapping/SimpleSymbol;
      //   235: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   238: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   241: if_acmpne -> 166
      //   244: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   247: aload #5
      //   249: getstatic gnu/kawa/slib/genwrite.Lit8 : Lgnu/mapping/SimpleSymbol;
      //   252: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   255: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   258: if_acmpeq -> 270
      //   261: aload_0
      //   262: getfield pp$MnCOND : Lgnu/mapping/Procedure;
      //   265: astore #4
      //   267: goto -> 88
      //   270: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   273: aload #5
      //   275: getstatic gnu/kawa/slib/genwrite.Lit9 : Lgnu/mapping/SimpleSymbol;
      //   278: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   281: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   284: if_acmpeq -> 296
      //   287: aload_0
      //   288: getfield pp$MnCASE : Lgnu/mapping/Procedure;
      //   291: astore #4
      //   293: goto -> 88
      //   296: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   299: aload #5
      //   301: getstatic gnu/kawa/slib/genwrite.Lit10 : Lgnu/mapping/SimpleSymbol;
      //   304: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   307: astore #4
      //   309: aload #4
      //   311: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   314: if_acmpeq -> 334
      //   317: aload #4
      //   319: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   322: if_acmpeq -> 351
      //   325: aload_0
      //   326: getfield pp$MnAND : Lgnu/mapping/Procedure;
      //   329: astore #4
      //   331: goto -> 88
      //   334: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   337: aload #5
      //   339: getstatic gnu/kawa/slib/genwrite.Lit11 : Lgnu/mapping/SimpleSymbol;
      //   342: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   345: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   348: if_acmpne -> 325
      //   351: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   354: aload #5
      //   356: getstatic gnu/kawa/slib/genwrite.Lit12 : Lgnu/mapping/SimpleSymbol;
      //   359: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   362: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   365: if_acmpeq -> 377
      //   368: aload_0
      //   369: getfield pp$MnLET : Lgnu/mapping/Procedure;
      //   372: astore #4
      //   374: goto -> 88
      //   377: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   380: aload #5
      //   382: getstatic gnu/kawa/slib/genwrite.Lit13 : Lgnu/mapping/SimpleSymbol;
      //   385: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   388: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   391: if_acmpeq -> 403
      //   394: aload_0
      //   395: getfield pp$MnBEGIN : Lgnu/mapping/Procedure;
      //   398: astore #4
      //   400: goto -> 88
      //   403: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   406: aload #5
      //   408: getstatic gnu/kawa/slib/genwrite.Lit14 : Lgnu/mapping/SimpleSymbol;
      //   411: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   414: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   417: if_acmpeq -> 429
      //   420: aload_0
      //   421: getfield pp$MnDO : Lgnu/mapping/Procedure;
      //   424: astore #4
      //   426: goto -> 88
      //   429: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   432: astore #4
      //   434: goto -> 88
      //   437: aload #5
      //   439: checkcast gnu/mapping/Symbol
      //   442: astore #4
      //   444: aload #4
      //   446: invokestatic symbol$To$String : (Lgnu/mapping/Symbol;)Ljava/lang/String;
      //   449: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   452: iconst_5
      //   453: if_icmple -> 477
      //   456: aload_0
      //   457: aload_1
      //   458: aload_2
      //   459: aload_3
      //   460: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   463: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   466: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   469: aload_0
      //   470: getfield pp$Mnexpr : Lgnu/mapping/Procedure;
      //   473: invokevirtual lambda12ppGeneral : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   476: areturn
      //   477: aload_0
      //   478: aload_1
      //   479: aload_2
      //   480: aload_3
      //   481: aload_0
      //   482: getfield pp$Mnexpr : Lgnu/mapping/Procedure;
      //   485: invokevirtual lambda9ppCall : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   488: areturn
      //   489: aload_0
      //   490: aload_1
      //   491: aload_2
      //   492: aload_3
      //   493: aload_0
      //   494: getfield pp$Mnexpr : Lgnu/mapping/Procedure;
      //   497: invokevirtual lambda10ppList : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   500: areturn
      //   501: astore_1
      //   502: new gnu/mapping/WrongType
      //   505: dup
      //   506: aload_1
      //   507: ldc_w 'symbol->string'
      //   510: iconst_1
      //   511: aload #5
      //   513: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   516: athrow
      // Exception table:
      //   from	to	target	type
      //   437	444	501	java/lang/ClassCastException
    }
    
    public Object lambda9ppCall(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4) {
      Object object2 = this.staticLink.lambda5wr(lists.car.apply1(param1Object1), this.staticLink.lambda4out("(", param1Object2));
      Object object1 = param1Object2;
      if (param1Object2 != Boolean.FALSE)
        object1 = lambda11ppDown(lists.cdr.apply1(param1Object1), object2, AddOp.$Pl.apply2(object2, genwrite.Lit17), param1Object3, param1Object4); 
      return object1;
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
        case 11:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.value3 = param1Object3;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 3;
          return 0;
        case 10:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.value3 = param1Object3;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 3;
          return 0;
        case 9:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.value3 = param1Object3;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 3;
          return 0;
        case 8:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.value3 = param1Object3;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 3;
          return 0;
        case 7:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.value3 = param1Object3;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 3;
          return 0;
        case 6:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.value3 = param1Object3;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 3;
          return 0;
        case 5:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.value3 = param1Object3;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 3;
          return 0;
        case 4:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.value3 = param1Object3;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 3;
          return 0;
        case 3:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.value3 = param1Object3;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 3;
          return 0;
        case 2:
          break;
      } 
      param1CallContext.value1 = param1Object1;
      param1CallContext.value2 = param1Object2;
      param1CallContext.value3 = param1Object3;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 3;
      return 0;
    }
    
    public class frame1 extends ModuleBody {
      final ModuleMethod lambda$Fn1;
      
      Object left;
      
      Object result;
      
      genwrite.frame0 staticLink;
      
      public frame1() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/genwrite.scm:72");
        this.lambda$Fn1 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 1) ? (lambda22(param2Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      boolean lambda22(Object param2Object) {
        this.result = lists.cons(param2Object, this.result);
        AddOp addOp = AddOp.$Mn;
        Object object = this.left;
        try {
          CharSequence charSequence = (CharSequence)param2Object;
          this.left = addOp.apply2(object, Integer.valueOf(strings.stringLength(charSequence)));
          return ((Boolean)Scheme.numGrt.apply2(this.left, genwrite.Lit1)).booleanValue();
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-length", 1, param2Object);
        } 
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 1) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
    }
  }
  
  public class frame1 extends ModuleBody {
    final ModuleMethod lambda$Fn1;
    
    Object left;
    
    Object result;
    
    genwrite.frame0 staticLink;
    
    public frame1() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/genwrite.scm:72");
      this.lambda$Fn1 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 1) ? (lambda22(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda22(Object param1Object) {
      this.result = lists.cons(param1Object, this.result);
      AddOp addOp = AddOp.$Mn;
      Object object = this.left;
      try {
        CharSequence charSequence = (CharSequence)param1Object;
        this.left = addOp.apply2(object, Integer.valueOf(strings.stringLength(charSequence)));
        return ((Boolean)Scheme.numGrt.apply2(this.left, genwrite.Lit1)).booleanValue();
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-length", 1, param1Object);
      } 
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 1) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/genwrite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
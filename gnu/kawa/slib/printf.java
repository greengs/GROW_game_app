package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Complex;
import gnu.math.IntNum;
import gnu.math.RealNum;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;
import kawa.standard.append;

public class printf extends ModuleBody {
  public static final printf $instance;
  
  static final IntNum Lit0;
  
  static final IntNum Lit1;
  
  static final PairWithPosition Lit10;
  
  static final Char Lit11;
  
  static final Char Lit12;
  
  static final Char Lit13;
  
  static final IntNum Lit14;
  
  static final IntNum Lit15;
  
  static final IntNum Lit16;
  
  static final IntNum Lit17;
  
  static final Char Lit18;
  
  static final Char Lit19;
  
  static final PairWithPosition Lit2;
  
  static final Char Lit20;
  
  static final Char Lit21;
  
  static final Char Lit22;
  
  static final Char Lit23;
  
  static final Char Lit24;
  
  static final Char Lit25;
  
  static final Char Lit26;
  
  static final Char Lit27;
  
  static final Char Lit28;
  
  static final Char Lit29;
  
  static final Char Lit3;
  
  static final Char Lit30;
  
  static final Char Lit31;
  
  static final Char Lit32;
  
  static final PairWithPosition Lit33;
  
  static final SimpleSymbol Lit34;
  
  static final Char Lit35;
  
  static final Char Lit36;
  
  static final Char Lit37;
  
  static final Char Lit38;
  
  static final Char Lit39;
  
  static final Char Lit4;
  
  static final Char Lit40;
  
  static final Char Lit41;
  
  static final Char Lit42;
  
  static final Char Lit43;
  
  static final Char Lit44;
  
  static final IntNum Lit45;
  
  static final Char Lit46;
  
  static final Char Lit47;
  
  static final IntNum Lit48;
  
  static final Char Lit49;
  
  static final Char Lit5;
  
  static final IntNum Lit50;
  
  static final Char Lit51;
  
  static final Char Lit52;
  
  static final Char Lit53;
  
  static final Char Lit54;
  
  static final Char Lit55;
  
  static final Char Lit56;
  
  static final Char Lit57;
  
  static final Char Lit58;
  
  static final IntNum Lit59;
  
  static final Char Lit6;
  
  static final IntNum Lit60;
  
  static final IntNum Lit61;
  
  static final FVector Lit62;
  
  static final PairWithPosition Lit63;
  
  static final SimpleSymbol Lit64;
  
  static final Char Lit65;
  
  static final Char Lit66;
  
  static final SimpleSymbol Lit67;
  
  static final SimpleSymbol Lit68;
  
  static final SimpleSymbol Lit69;
  
  static final IntNum Lit7;
  
  static final SimpleSymbol Lit70;
  
  static final SimpleSymbol Lit71;
  
  static final SimpleSymbol Lit72 = (SimpleSymbol)(new SimpleSymbol("fprintf")).readResolve();
  
  static final Char Lit8;
  
  static final Char Lit9;
  
  public static final ModuleMethod fprintf;
  
  public static final ModuleMethod printf;
  
  public static final ModuleMethod sprintf;
  
  public static final boolean stdio$Clhex$Mnupper$Mncase$Qu = false;
  
  public static final ModuleMethod stdio$Cliprintf;
  
  public static final ModuleMethod stdio$Clparse$Mnfloat;
  
  public static final ModuleMethod stdio$Clround$Mnstring;
  
  static {
    Lit71 = (SimpleSymbol)(new SimpleSymbol("stdio:iprintf")).readResolve();
    Lit70 = (SimpleSymbol)(new SimpleSymbol("stdio:round-string")).readResolve();
    Lit69 = (SimpleSymbol)(new SimpleSymbol("stdio:parse-float")).readResolve();
    Lit68 = (SimpleSymbol)(new SimpleSymbol("sprintf")).readResolve();
    Lit67 = (SimpleSymbol)(new SimpleSymbol("pad")).readResolve();
    Lit66 = Char.make(42);
    Lit65 = Char.make(63);
    Lit64 = (SimpleSymbol)(new SimpleSymbol("format-real")).readResolve();
    Lit63 = PairWithPosition.make("i", LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1634315);
    Lit62 = FVector.make(new Object[] { 
          "y", "z", "a", "f", "p", "n", "u", "m", "", "k", 
          "M", "G", "T", "P", "E", "Z", "Y" });
    Lit61 = IntNum.make(3);
    Lit60 = IntNum.make(-10);
    Lit59 = IntNum.make(6);
    Lit58 = Char.make(75);
    Lit57 = Char.make(107);
    Lit56 = Char.make(71);
    Lit55 = Char.make(103);
    Lit54 = Char.make(69);
    Lit53 = Char.make(66);
    Lit52 = Char.make(98);
    Lit51 = Char.make(88);
    Lit50 = IntNum.make(16);
    Lit49 = Char.make(120);
    Lit48 = IntNum.make(8);
    Lit47 = Char.make(79);
    Lit46 = Char.make(111);
    Lit45 = IntNum.make(10);
    Lit44 = Char.make(85);
    Lit43 = Char.make(117);
    Lit42 = Char.make(73);
    Lit41 = Char.make(68);
    Lit40 = Char.make(65);
    Lit39 = Char.make(97);
    Lit38 = Char.make(83);
    Lit37 = Char.make(115);
    Lit36 = Char.make(67);
    Lit35 = Char.make(99);
    Lit34 = (SimpleSymbol)(new SimpleSymbol("printf")).readResolve();
    Char char_1 = Lit35;
    Char char_2 = Lit37;
    Char char_3 = Lit39;
    Char char_4 = Char.make(100);
    Lit12 = char_4;
    Char char_5 = Char.make(105);
    Lit3 = char_5;
    Char char_6 = Lit43;
    Char char_7 = Lit46;
    Char char_8 = Lit49;
    Char char_9 = Lit52;
    Char char_10 = Char.make(102);
    Lit25 = char_10;
    Char char_11 = Char.make(101);
    Lit13 = char_11;
    Lit33 = PairWithPosition.make(char_1, PairWithPosition.make(char_2, PairWithPosition.make(char_3, PairWithPosition.make(char_4, PairWithPosition.make(char_5, PairWithPosition.make(char_6, PairWithPosition.make(char_7, PairWithPosition.make(char_8, PairWithPosition.make(char_9, PairWithPosition.make(char_10, PairWithPosition.make(char_11, PairWithPosition.make(Lit55, PairWithPosition.make(Lit57, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781780), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781776), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781772), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781768), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777704), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777700), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777696), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777692), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777688), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777684), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777680), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777676), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777671);
    Lit32 = Char.make(104);
    Lit31 = Char.make(76);
    Lit30 = Char.make(108);
    Lit29 = Char.make(32);
    Lit28 = Char.make(37);
    Lit27 = Char.make(12);
    Lit26 = Char.make(70);
    Lit24 = Char.make(9);
    Lit23 = Char.make(84);
    Lit22 = Char.make(116);
    Lit21 = Char.make(10);
    Lit20 = Char.make(78);
    Lit19 = Char.make(110);
    Lit18 = Char.make(92);
    Lit17 = IntNum.make(-1);
    Lit16 = IntNum.make(9);
    Lit15 = IntNum.make(5);
    Lit14 = IntNum.make(2);
    Lit11 = Char.make(46);
    Lit10 = PairWithPosition.make(Lit13, PairWithPosition.make(Lit37, PairWithPosition.make(Lit25, PairWithPosition.make(Lit12, PairWithPosition.make(Lit30, PairWithPosition.make(Lit54, PairWithPosition.make(Lit38, PairWithPosition.make(Lit26, PairWithPosition.make(Lit41, PairWithPosition.make(Lit31, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266284), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266280), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266276), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266272), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266268), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266264), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266260), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266256), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266252), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266247);
    Lit9 = Char.make(48);
    Lit8 = Char.make(35);
    Lit7 = IntNum.make(1);
    Lit6 = Char.make(43);
    Lit5 = Char.make(45);
    Lit4 = Char.make(64);
    Lit2 = PairWithPosition.make(Lit6, PairWithPosition.make(Lit5, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 446503), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 446498);
    Lit1 = IntNum.make(0);
    Lit0 = IntNum.make(-15);
    $instance = new printf();
    printf printf1 = $instance;
    stdio$Clparse$Mnfloat = new ModuleMethod(printf1, 22, Lit69, 8194);
    stdio$Clround$Mnstring = new ModuleMethod(printf1, 23, Lit70, 12291);
    stdio$Cliprintf = new ModuleMethod(printf1, 24, Lit71, -4094);
    fprintf = new ModuleMethod(printf1, 25, Lit72, -4094);
    printf = new ModuleMethod(printf1, 26, Lit34, -4095);
    sprintf = new ModuleMethod(printf1, 27, Lit68, -4094);
    $instance.run();
  }
  
  public printf() {
    ModuleInfo.register(this);
  }
  
  public static Object fprintf$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame12 frame12 = new frame12();
    frame12.port = paramObject1;
    paramObject1 = LList.makeList(paramArrayOfObject, 0);
    frame12.cnt = Lit1;
    Scheme.apply.apply4(stdio$Cliprintf, frame12.lambda$Fn18, paramObject2, paramObject1);
    return frame12.cnt;
  }
  
  public static Object printf$V(Object paramObject, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    return Scheme.apply.apply4(fprintf, ports.current$Mnoutput$Mnport.apply0(), paramObject, lList);
  }
  
  public static Object sprintf$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    // Byte code:
    //   0: new gnu/kawa/slib/printf$frame13
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #4
    //   9: aload #4
    //   11: aload_0
    //   12: putfield str : Ljava/lang/Object;
    //   15: aload_2
    //   16: iconst_0
    //   17: invokestatic makeList : ([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   20: astore_2
    //   21: aload #4
    //   23: getstatic gnu/kawa/slib/printf.Lit1 : Lgnu/math/IntNum;
    //   26: putfield cnt : Ljava/lang/Object;
    //   29: aload #4
    //   31: getfield str : Ljava/lang/Object;
    //   34: invokestatic isString : (Ljava/lang/Object;)Z
    //   37: ifeq -> 111
    //   40: aload #4
    //   42: getfield str : Ljava/lang/Object;
    //   45: astore_0
    //   46: aload #4
    //   48: aload_0
    //   49: putfield s : Ljava/lang/Object;
    //   52: aload #4
    //   54: getfield s : Ljava/lang/Object;
    //   57: astore_0
    //   58: aload_0
    //   59: checkcast java/lang/CharSequence
    //   62: astore #5
    //   64: aload #4
    //   66: aload #5
    //   68: invokestatic stringLength : (Ljava/lang/CharSequence;)I
    //   71: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   74: putfield end : Ljava/lang/Object;
    //   77: getstatic kawa/standard/Scheme.apply : Lgnu/kawa/functions/Apply;
    //   80: getstatic gnu/kawa/slib/printf.stdio$Cliprintf : Lgnu/expr/ModuleMethod;
    //   83: aload #4
    //   85: getfield lambda$Fn19 : Lgnu/expr/ModuleMethod;
    //   88: aload_1
    //   89: aload_2
    //   90: invokevirtual apply4 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   93: pop
    //   94: aload #4
    //   96: getfield str : Ljava/lang/Object;
    //   99: invokestatic isString : (Ljava/lang/Object;)Z
    //   102: ifeq -> 192
    //   105: aload #4
    //   107: getfield cnt : Ljava/lang/Object;
    //   110: areturn
    //   111: aload #4
    //   113: getfield str : Ljava/lang/Object;
    //   116: invokestatic isNumber : (Ljava/lang/Object;)Z
    //   119: ifeq -> 144
    //   122: aload #4
    //   124: getfield str : Ljava/lang/Object;
    //   127: astore_0
    //   128: aload_0
    //   129: checkcast java/lang/Number
    //   132: invokevirtual intValue : ()I
    //   135: istore_3
    //   136: iload_3
    //   137: invokestatic makeString : (I)Ljava/lang/CharSequence;
    //   140: astore_0
    //   141: goto -> 46
    //   144: aload #4
    //   146: getfield str : Ljava/lang/Object;
    //   149: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   152: if_acmpne -> 164
    //   155: bipush #100
    //   157: invokestatic makeString : (I)Ljava/lang/CharSequence;
    //   160: astore_0
    //   161: goto -> 46
    //   164: getstatic gnu/kawa/slib/printf.Lit68 : Lgnu/mapping/SimpleSymbol;
    //   167: iconst_2
    //   168: anewarray java/lang/Object
    //   171: dup
    //   172: iconst_0
    //   173: ldc_w 'first argument not understood'
    //   176: aastore
    //   177: dup
    //   178: iconst_1
    //   179: aload #4
    //   181: getfield str : Ljava/lang/Object;
    //   184: aastore
    //   185: invokestatic error$V : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   188: astore_0
    //   189: goto -> 46
    //   192: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   195: aload #4
    //   197: getfield end : Ljava/lang/Object;
    //   200: aload #4
    //   202: getfield cnt : Ljava/lang/Object;
    //   205: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   208: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   211: if_acmpeq -> 220
    //   214: aload #4
    //   216: getfield s : Ljava/lang/Object;
    //   219: areturn
    //   220: aload #4
    //   222: getfield s : Ljava/lang/Object;
    //   225: astore_0
    //   226: aload_0
    //   227: checkcast java/lang/CharSequence
    //   230: astore_1
    //   231: aload #4
    //   233: getfield cnt : Ljava/lang/Object;
    //   236: astore_0
    //   237: aload_0
    //   238: checkcast java/lang/Number
    //   241: invokevirtual intValue : ()I
    //   244: istore_3
    //   245: aload_1
    //   246: iconst_0
    //   247: iload_3
    //   248: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
    //   251: areturn
    //   252: astore_1
    //   253: new gnu/mapping/WrongType
    //   256: dup
    //   257: aload_1
    //   258: ldc_w 'make-string'
    //   261: iconst_1
    //   262: aload_0
    //   263: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   266: athrow
    //   267: astore_1
    //   268: new gnu/mapping/WrongType
    //   271: dup
    //   272: aload_1
    //   273: ldc_w 'string-length'
    //   276: iconst_1
    //   277: aload_0
    //   278: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   281: athrow
    //   282: astore_1
    //   283: new gnu/mapping/WrongType
    //   286: dup
    //   287: aload_1
    //   288: ldc_w 'substring'
    //   291: iconst_1
    //   292: aload_0
    //   293: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   296: athrow
    //   297: astore_1
    //   298: new gnu/mapping/WrongType
    //   301: dup
    //   302: aload_1
    //   303: ldc_w 'substring'
    //   306: iconst_3
    //   307: aload_0
    //   308: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   311: athrow
    // Exception table:
    //   from	to	target	type
    //   58	64	267	java/lang/ClassCastException
    //   128	136	252	java/lang/ClassCastException
    //   226	231	282	java/lang/ClassCastException
    //   237	245	297	java/lang/ClassCastException
  }
  
  public static Object stdio$ClIprintf$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame9 frame9 = new frame9();
    frame9.out = paramObject1;
    frame9.format$Mnstring = paramObject2;
    frame9.args = LList.makeList(paramArrayOfObject, 0);
    if (!IsEqual.apply("", frame9.format$Mnstring)) {
      paramObject1 = Lit17;
      paramObject2 = frame9.format$Mnstring;
      try {
        CharSequence charSequence = (CharSequence)paramObject2;
        int i = strings.stringLength(charSequence);
        paramObject2 = frame9.format$Mnstring;
        try {
          charSequence = (CharSequence)paramObject2;
          frame9.fc = Char.make(strings.stringRef(charSequence, 0));
          frame9.fl = i;
          frame9.pos = paramObject1;
          paramObject1 = frame9.args;
          while (true) {
            frame10 frame10 = new frame10();
            frame10.staticLink = frame9;
            frame10.args = paramObject1;
            frame9.pos = AddOp.$Pl.apply2(Lit7, frame9.pos);
            if (Scheme.numGEq.apply2(frame9.pos, Integer.valueOf(frame9.fl)) != Boolean.FALSE) {
              frame9.fc = Boolean.FALSE;
            } else {
              paramObject1 = frame9.format$Mnstring;
              try {
                paramObject2 = paramObject1;
                paramObject1 = frame9.pos;
                try {
                  i = ((Number)paramObject1).intValue();
                  frame9.fc = Char.make(strings.stringRef((CharSequence)paramObject2, i));
                  boolean bool1 = frame9.lambda19isEndOfFormat();
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException, "string-ref", 2, paramObject1);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 1, paramObject1);
              } 
            } 
            boolean bool = frame9.lambda19isEndOfFormat();
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "string-ref", 1, classCastException);
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "string-length", 1, classCastException);
      } 
    } 
    return Values.empty;
  }
  
  public static Object stdio$ClParseFloat(Object paramObject1, Object paramObject2) {
    frame frame = new frame();
    frame.str = paramObject1;
    frame.proc = paramObject2;
    paramObject1 = frame.str;
    try {
      paramObject2 = paramObject1;
      frame.n = strings.stringLength((CharSequence)paramObject2);
      return frame.lambda4real(Lit1, frame.lambda$Fn1);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "string-length", 1, paramObject1);
    } 
  }
  
  public static Object stdio$ClRoundString(CharSequence paramCharSequence, Object paramObject1, Object paramObject2) {
    // Byte code:
    //   0: new gnu/kawa/slib/printf$frame8
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #7
    //   9: aload #7
    //   11: aload_0
    //   12: putfield str : Ljava/lang/CharSequence;
    //   15: aload #7
    //   17: getfield str : Ljava/lang/CharSequence;
    //   20: invokestatic stringLength : (Ljava/lang/CharSequence;)I
    //   23: iconst_1
    //   24: isub
    //   25: istore_3
    //   26: getstatic kawa/standard/Scheme.numLss : Lgnu/kawa/functions/NumberCompare;
    //   29: aload_1
    //   30: getstatic gnu/kawa/slib/printf.Lit1 : Lgnu/math/IntNum;
    //   33: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   36: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   39: if_acmpeq -> 132
    //   42: ldc ''
    //   44: astore_0
    //   45: aload_0
    //   46: astore_1
    //   47: aload_2
    //   48: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   51: if_acmpeq -> 130
    //   54: aload_0
    //   55: checkcast java/lang/CharSequence
    //   58: astore_1
    //   59: aload_1
    //   60: invokestatic stringLength : (Ljava/lang/CharSequence;)I
    //   63: iconst_1
    //   64: isub
    //   65: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   68: astore_1
    //   69: getstatic kawa/standard/Scheme.numLEq : Lgnu/kawa/functions/NumberCompare;
    //   72: aload_1
    //   73: aload_2
    //   74: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   77: astore #6
    //   79: aload #6
    //   81: checkcast java/lang/Boolean
    //   84: invokevirtual booleanValue : ()Z
    //   87: istore #5
    //   89: iload #5
    //   91: ifeq -> 638
    //   94: iload #5
    //   96: ifeq -> 674
    //   99: aload_0
    //   100: checkcast java/lang/CharSequence
    //   103: astore_2
    //   104: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   107: aload_1
    //   108: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
    //   111: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: astore_0
    //   115: aload_0
    //   116: checkcast java/lang/Number
    //   119: invokevirtual intValue : ()I
    //   122: istore_3
    //   123: aload_2
    //   124: iconst_0
    //   125: iload_3
    //   126: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
    //   129: astore_1
    //   130: aload_1
    //   131: areturn
    //   132: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
    //   135: iload_3
    //   136: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   139: aload_1
    //   140: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   143: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   146: if_acmpeq -> 158
    //   149: aload #7
    //   151: getfield str : Ljava/lang/CharSequence;
    //   154: astore_0
    //   155: goto -> 45
    //   158: getstatic kawa/standard/Scheme.numLss : Lgnu/kawa/functions/NumberCompare;
    //   161: iload_3
    //   162: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   165: aload_1
    //   166: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   169: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   172: if_acmpeq -> 311
    //   175: getstatic gnu/kawa/slib/printf.Lit1 : Lgnu/math/IntNum;
    //   178: astore #6
    //   180: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
    //   183: astore #8
    //   185: aload_1
    //   186: astore_0
    //   187: aload_2
    //   188: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   191: if_acmpeq -> 196
    //   194: aload_2
    //   195: astore_0
    //   196: iconst_2
    //   197: anewarray java/lang/Object
    //   200: dup
    //   201: iconst_0
    //   202: aload #6
    //   204: aastore
    //   205: dup
    //   206: iconst_1
    //   207: aload #8
    //   209: aload_0
    //   210: iload_3
    //   211: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   214: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   217: aastore
    //   218: invokestatic max : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   221: astore_0
    //   222: aload_0
    //   223: checkcast java/lang/Number
    //   226: astore_1
    //   227: aload_1
    //   228: invokestatic isZero : (Ljava/lang/Number;)Z
    //   231: ifeq -> 243
    //   234: aload #7
    //   236: getfield str : Ljava/lang/CharSequence;
    //   239: astore_0
    //   240: goto -> 45
    //   243: aload #7
    //   245: getfield str : Ljava/lang/CharSequence;
    //   248: astore_1
    //   249: aload_0
    //   250: checkcast java/lang/Number
    //   253: invokevirtual intValue : ()I
    //   256: istore #4
    //   258: aload #7
    //   260: getfield str : Ljava/lang/CharSequence;
    //   263: iload_3
    //   264: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   267: invokestatic make : (I)Lgnu/text/Char;
    //   270: invokestatic isCharNumeric : (Lgnu/text/Char;)Z
    //   273: ifeq -> 304
    //   276: getstatic gnu/kawa/slib/printf.Lit9 : Lgnu/text/Char;
    //   279: astore_0
    //   280: iconst_2
    //   281: anewarray java/lang/Object
    //   284: dup
    //   285: iconst_0
    //   286: aload_1
    //   287: aastore
    //   288: dup
    //   289: iconst_1
    //   290: iload #4
    //   292: aload_0
    //   293: invokestatic makeString : (ILjava/lang/Object;)Ljava/lang/CharSequence;
    //   296: aastore
    //   297: invokestatic stringAppend : ([Ljava/lang/Object;)Lgnu/lists/FString;
    //   300: astore_0
    //   301: goto -> 240
    //   304: getstatic gnu/kawa/slib/printf.Lit8 : Lgnu/text/Char;
    //   307: astore_0
    //   308: goto -> 280
    //   311: aload #7
    //   313: getfield str : Ljava/lang/CharSequence;
    //   316: astore #6
    //   318: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   321: aload_1
    //   322: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
    //   325: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   328: astore_0
    //   329: aload_0
    //   330: checkcast java/lang/Number
    //   333: invokevirtual intValue : ()I
    //   336: istore #4
    //   338: aload #6
    //   340: iconst_0
    //   341: iload #4
    //   343: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
    //   346: astore #6
    //   348: aload #7
    //   350: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   353: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
    //   356: aload_1
    //   357: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   360: invokevirtual lambda17dig : (Ljava/lang/Object;)Ljava/lang/Object;
    //   363: astore #8
    //   365: getstatic kawa/standard/Scheme.numGrt : Lgnu/kawa/functions/NumberCompare;
    //   368: aload #8
    //   370: getstatic gnu/kawa/slib/printf.Lit15 : Lgnu/math/IntNum;
    //   373: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   376: astore_0
    //   377: aload_0
    //   378: checkcast java/lang/Boolean
    //   381: invokevirtual booleanValue : ()Z
    //   384: istore #5
    //   386: iload #5
    //   388: ifeq -> 478
    //   391: aload #6
    //   393: astore_0
    //   394: iload #5
    //   396: ifeq -> 45
    //   399: aload_1
    //   400: astore_0
    //   401: aload #7
    //   403: aload_0
    //   404: invokevirtual lambda17dig : (Ljava/lang/Object;)Ljava/lang/Object;
    //   407: astore #8
    //   409: getstatic kawa/standard/Scheme.numLss : Lgnu/kawa/functions/NumberCompare;
    //   412: aload #8
    //   414: getstatic gnu/kawa/slib/printf.Lit16 : Lgnu/math/IntNum;
    //   417: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   420: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   423: if_acmpeq -> 603
    //   426: aload #6
    //   428: checkcast gnu/lists/CharSeq
    //   431: astore_1
    //   432: aload_0
    //   433: checkcast java/lang/Number
    //   436: invokevirtual intValue : ()I
    //   439: istore_3
    //   440: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   443: aload #8
    //   445: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
    //   448: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   451: astore_0
    //   452: aload_0
    //   453: checkcast java/lang/Number
    //   456: astore #7
    //   458: aload_1
    //   459: iload_3
    //   460: aload #7
    //   462: invokestatic number$To$String : (Ljava/lang/Number;)Ljava/lang/CharSequence;
    //   465: iconst_0
    //   466: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   469: invokestatic stringSet$Ex : (Lgnu/lists/CharSeq;IC)V
    //   472: aload #6
    //   474: astore_0
    //   475: goto -> 45
    //   478: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
    //   481: aload #8
    //   483: getstatic gnu/kawa/slib/printf.Lit15 : Lgnu/math/IntNum;
    //   486: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   489: astore_0
    //   490: aload_0
    //   491: checkcast java/lang/Boolean
    //   494: invokevirtual booleanValue : ()Z
    //   497: istore #5
    //   499: iload #5
    //   501: ifeq -> 592
    //   504: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   507: getstatic gnu/kawa/slib/printf.Lit14 : Lgnu/math/IntNum;
    //   510: aload_1
    //   511: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   514: astore_0
    //   515: getstatic kawa/standard/Scheme.numGrt : Lgnu/kawa/functions/NumberCompare;
    //   518: aload_0
    //   519: iload_3
    //   520: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   523: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   526: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   529: if_acmpeq -> 555
    //   532: aload #6
    //   534: astore_0
    //   535: aload #7
    //   537: aload_1
    //   538: invokevirtual lambda17dig : (Ljava/lang/Object;)Ljava/lang/Object;
    //   541: checkcast java/lang/Number
    //   544: invokevirtual intValue : ()I
    //   547: iconst_1
    //   548: iand
    //   549: ifeq -> 45
    //   552: goto -> 399
    //   555: aload #7
    //   557: aload_0
    //   558: invokevirtual lambda17dig : (Ljava/lang/Object;)Ljava/lang/Object;
    //   561: astore #8
    //   563: aload #8
    //   565: checkcast java/lang/Number
    //   568: astore #9
    //   570: aload #9
    //   572: invokestatic isZero : (Ljava/lang/Number;)Z
    //   575: ifeq -> 399
    //   578: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   581: aload_0
    //   582: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
    //   585: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   588: astore_0
    //   589: goto -> 515
    //   592: aload #6
    //   594: astore_0
    //   595: iload #5
    //   597: ifeq -> 45
    //   600: goto -> 399
    //   603: aload #6
    //   605: checkcast gnu/lists/CharSeq
    //   608: astore_1
    //   609: aload_0
    //   610: checkcast java/lang/Number
    //   613: invokevirtual intValue : ()I
    //   616: istore_3
    //   617: aload_1
    //   618: iload_3
    //   619: bipush #48
    //   621: invokestatic stringSet$Ex : (Lgnu/lists/CharSeq;IC)V
    //   624: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
    //   627: aload_0
    //   628: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
    //   631: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   634: astore_0
    //   635: goto -> 401
    //   638: getstatic gnu/kawa/slib/printf.Lit9 : Lgnu/text/Char;
    //   641: astore #6
    //   643: aload_0
    //   644: checkcast java/lang/CharSequence
    //   647: astore #7
    //   649: aload_1
    //   650: checkcast java/lang/Number
    //   653: invokevirtual intValue : ()I
    //   656: istore_3
    //   657: aload #6
    //   659: aload #7
    //   661: iload_3
    //   662: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   665: invokestatic make : (I)Lgnu/text/Char;
    //   668: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
    //   671: ifeq -> 99
    //   674: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
    //   677: aload_1
    //   678: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
    //   681: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   684: astore_1
    //   685: goto -> 69
    //   688: astore_1
    //   689: new gnu/mapping/WrongType
    //   692: dup
    //   693: aload_1
    //   694: ldc_w 'zero?'
    //   697: iconst_1
    //   698: aload_0
    //   699: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   702: athrow
    //   703: astore_1
    //   704: new gnu/mapping/WrongType
    //   707: dup
    //   708: aload_1
    //   709: ldc_w 'make-string'
    //   712: iconst_1
    //   713: aload_0
    //   714: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   717: athrow
    //   718: astore_1
    //   719: new gnu/mapping/WrongType
    //   722: dup
    //   723: aload_1
    //   724: ldc_w 'substring'
    //   727: iconst_3
    //   728: aload_0
    //   729: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   732: athrow
    //   733: astore_1
    //   734: new gnu/mapping/WrongType
    //   737: dup
    //   738: aload_1
    //   739: ldc_w 'x'
    //   742: bipush #-2
    //   744: aload_0
    //   745: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   748: athrow
    //   749: astore_1
    //   750: new gnu/mapping/WrongType
    //   753: dup
    //   754: aload_1
    //   755: ldc_w 'x'
    //   758: bipush #-2
    //   760: aload_0
    //   761: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   764: athrow
    //   765: astore_0
    //   766: new gnu/mapping/WrongType
    //   769: dup
    //   770: aload_0
    //   771: ldc_w 'zero?'
    //   774: iconst_1
    //   775: aload #8
    //   777: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   780: athrow
    //   781: astore_0
    //   782: new gnu/mapping/WrongType
    //   785: dup
    //   786: aload_0
    //   787: ldc_w 'string-set!'
    //   790: iconst_1
    //   791: aload #6
    //   793: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   796: athrow
    //   797: astore_1
    //   798: new gnu/mapping/WrongType
    //   801: dup
    //   802: aload_1
    //   803: ldc_w 'string-set!'
    //   806: iconst_2
    //   807: aload_0
    //   808: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   811: athrow
    //   812: astore_1
    //   813: new gnu/mapping/WrongType
    //   816: dup
    //   817: aload_1
    //   818: ldc_w 'number->string'
    //   821: iconst_1
    //   822: aload_0
    //   823: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   826: athrow
    //   827: astore_0
    //   828: new gnu/mapping/WrongType
    //   831: dup
    //   832: aload_0
    //   833: ldc_w 'string-set!'
    //   836: iconst_1
    //   837: aload #6
    //   839: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   842: athrow
    //   843: astore_1
    //   844: new gnu/mapping/WrongType
    //   847: dup
    //   848: aload_1
    //   849: ldc_w 'string-set!'
    //   852: iconst_2
    //   853: aload_0
    //   854: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   857: athrow
    //   858: astore_1
    //   859: new gnu/mapping/WrongType
    //   862: dup
    //   863: aload_1
    //   864: ldc_w 'string-length'
    //   867: iconst_1
    //   868: aload_0
    //   869: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   872: athrow
    //   873: astore_0
    //   874: new gnu/mapping/WrongType
    //   877: dup
    //   878: aload_0
    //   879: ldc_w 'x'
    //   882: bipush #-2
    //   884: aload #6
    //   886: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   889: athrow
    //   890: astore_1
    //   891: new gnu/mapping/WrongType
    //   894: dup
    //   895: aload_1
    //   896: ldc_w 'string-ref'
    //   899: iconst_1
    //   900: aload_0
    //   901: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   904: athrow
    //   905: astore_0
    //   906: new gnu/mapping/WrongType
    //   909: dup
    //   910: aload_0
    //   911: ldc_w 'string-ref'
    //   914: iconst_2
    //   915: aload_1
    //   916: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   919: athrow
    //   920: astore_1
    //   921: new gnu/mapping/WrongType
    //   924: dup
    //   925: aload_1
    //   926: ldc_w 'substring'
    //   929: iconst_1
    //   930: aload_0
    //   931: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   934: athrow
    //   935: astore_1
    //   936: new gnu/mapping/WrongType
    //   939: dup
    //   940: aload_1
    //   941: ldc_w 'substring'
    //   944: iconst_3
    //   945: aload_0
    //   946: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   949: athrow
    // Exception table:
    //   from	to	target	type
    //   54	59	858	java/lang/ClassCastException
    //   79	89	873	java/lang/ClassCastException
    //   99	104	920	java/lang/ClassCastException
    //   115	123	935	java/lang/ClassCastException
    //   222	227	688	java/lang/ClassCastException
    //   249	258	703	java/lang/ClassCastException
    //   329	338	718	java/lang/ClassCastException
    //   377	386	733	java/lang/ClassCastException
    //   426	432	781	java/lang/ClassCastException
    //   432	440	797	java/lang/ClassCastException
    //   452	458	812	java/lang/ClassCastException
    //   490	499	749	java/lang/ClassCastException
    //   563	570	765	java/lang/ClassCastException
    //   603	609	827	java/lang/ClassCastException
    //   609	617	843	java/lang/ClassCastException
    //   643	649	890	java/lang/ClassCastException
    //   649	657	905	java/lang/ClassCastException
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    return (paramModuleMethod.selector == 22) ? stdio$ClParseFloat(paramObject1, paramObject2) : super.apply2(paramModuleMethod, paramObject1, paramObject2);
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    if (paramModuleMethod.selector == 23)
      try {
        CharSequence charSequence = (CharSequence)paramObject1;
        return stdio$ClRoundString(charSequence, paramObject2, paramObject3);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "stdio:round-string", 1, paramObject1);
      }  
    return super.apply3((ModuleMethod)classCastException, paramObject1, paramObject2, paramObject3);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 24:
        object1 = paramArrayOfObject[0];
        object2 = paramArrayOfObject[1];
        i = paramArrayOfObject.length - 2;
        arrayOfObject = new Object[i];
        while (true) {
          if (--i < 0)
            return stdio$ClIprintf$V(object1, object2, arrayOfObject); 
          arrayOfObject[i] = paramArrayOfObject[i + 2];
        } 
      case 25:
        object1 = paramArrayOfObject[0];
        object2 = paramArrayOfObject[1];
        i = paramArrayOfObject.length - 2;
        arrayOfObject = new Object[i];
        while (true) {
          if (--i < 0)
            return fprintf$V(object1, object2, arrayOfObject); 
          arrayOfObject[i] = paramArrayOfObject[i + 2];
        } 
      case 26:
        object1 = paramArrayOfObject[0];
        i = paramArrayOfObject.length - 1;
        object2 = new Object[i];
        while (true) {
          if (--i < 0)
            return printf$V(object1, (Object[])object2); 
          object2[i] = paramArrayOfObject[i + 1];
        } 
      case 27:
        break;
    } 
    Object object1 = paramArrayOfObject[0];
    Object object2 = paramArrayOfObject[1];
    int i = paramArrayOfObject.length - 2;
    Object[] arrayOfObject = new Object[i];
    while (true) {
      if (--i < 0)
        return sprintf$V(object1, object2, arrayOfObject); 
      arrayOfObject[i] = paramArrayOfObject[i + 2];
    } 
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 22) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 23) {
      if (paramObject1 instanceof CharSequence) {
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      } 
      return -786431;
    } 
    return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 27:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 26:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 25:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 24:
        break;
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 5;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    stdio$Clhex$Mnupper$Mncase$Qu = strings.isString$Eq("-F", numbers.number$To$String((Number)Lit0, 16));
  }
  
  public class frame extends ModuleBody {
    final ModuleMethod lambda$Fn1;
    
    int n;
    
    Object proc;
    
    Object str;
    
    public frame() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 12, null, 16388);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:106");
      this.lambda$Fn1 = moduleMethod;
    }
    
    public static Boolean lambda1parseError() {
      return Boolean.FALSE;
    }
    
    public Object apply4(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4) {
      return (param1ModuleMethod.selector == 12) ? lambda5(param1Object1, param1Object2, param1Object3, param1Object4) : super.apply4(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1Object4);
    }
    
    public Object lambda2sign(Object param1Object1, Object param1Object2) {
      if (Scheme.numLss.apply2(param1Object1, Integer.valueOf(this.n)) != Boolean.FALSE) {
        Object object = this.str;
        try {
          CharSequence charSequence = (CharSequence)object;
          try {
            int i = ((Number)param1Object1).intValue();
            i = strings.stringRef(charSequence, i);
            object = Scheme.isEqv.apply2(Char.make(i), printf.Lit5);
            return ((object != Boolean.FALSE) ? (object != Boolean.FALSE) : (Scheme.isEqv.apply2(Char.make(i), printf.Lit6) != Boolean.FALSE)) ? Scheme.applyToArgs.apply3(param1Object2, AddOp.$Pl.apply2(param1Object1, printf.Lit7), Char.make(i)) : Scheme.applyToArgs.apply3(param1Object2, param1Object1, printf.Lit6);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, param1Object1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 1, object);
        } 
      } 
      return Values.empty;
    }
    
    public Object lambda3digits(Object param1Object1, Object param1Object2) {
      // Byte code:
      //   0: aload_1
      //   1: astore #6
      //   3: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
      //   6: aload #6
      //   8: aload_0
      //   9: getfield n : I
      //   12: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   15: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   18: astore #7
      //   20: aload #7
      //   22: checkcast java/lang/Boolean
      //   25: invokevirtual booleanValue : ()Z
      //   28: istore #5
      //   30: iload #5
      //   32: ifeq -> 56
      //   35: iload #5
      //   37: ifne -> 102
      //   40: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   43: aload #6
      //   45: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
      //   48: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   51: astore #6
      //   53: goto -> 3
      //   56: aload_0
      //   57: getfield str : Ljava/lang/Object;
      //   60: astore #7
      //   62: aload #7
      //   64: checkcast java/lang/CharSequence
      //   67: astore #8
      //   69: aload #6
      //   71: checkcast java/lang/Number
      //   74: invokevirtual intValue : ()I
      //   77: istore_3
      //   78: aload #8
      //   80: iload_3
      //   81: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
      //   84: invokestatic make : (I)Lgnu/text/Char;
      //   87: invokestatic isCharNumeric : (Lgnu/text/Char;)Z
      //   90: istore #5
      //   92: iload #5
      //   94: ifeq -> 135
      //   97: iload #5
      //   99: ifne -> 40
      //   102: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   105: astore #7
      //   107: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
      //   110: aload_1
      //   111: aload #6
      //   113: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   116: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   119: if_acmpeq -> 182
      //   122: ldc '0'
      //   124: astore_1
      //   125: aload #7
      //   127: aload_2
      //   128: aload #6
      //   130: aload_1
      //   131: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   134: areturn
      //   135: getstatic gnu/kawa/slib/printf.Lit8 : Lgnu/text/Char;
      //   138: astore #8
      //   140: aload_0
      //   141: getfield str : Ljava/lang/Object;
      //   144: astore #7
      //   146: aload #7
      //   148: checkcast java/lang/CharSequence
      //   151: astore #9
      //   153: aload #6
      //   155: checkcast java/lang/Number
      //   158: invokevirtual intValue : ()I
      //   161: istore_3
      //   162: aload #8
      //   164: aload #9
      //   166: iload_3
      //   167: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
      //   170: invokestatic make : (I)Lgnu/text/Char;
      //   173: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
      //   176: ifeq -> 102
      //   179: goto -> 40
      //   182: aload_0
      //   183: getfield str : Ljava/lang/Object;
      //   186: astore #8
      //   188: aload #8
      //   190: checkcast java/lang/CharSequence
      //   193: astore #9
      //   195: aload_1
      //   196: checkcast java/lang/Number
      //   199: invokevirtual intValue : ()I
      //   202: istore_3
      //   203: aload #6
      //   205: checkcast java/lang/Number
      //   208: invokevirtual intValue : ()I
      //   211: istore #4
      //   213: aload #9
      //   215: iload_3
      //   216: iload #4
      //   218: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   221: astore_1
      //   222: goto -> 125
      //   225: astore_1
      //   226: new gnu/mapping/WrongType
      //   229: dup
      //   230: aload_1
      //   231: ldc 'x'
      //   233: bipush #-2
      //   235: aload #7
      //   237: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   240: athrow
      //   241: astore_1
      //   242: new gnu/mapping/WrongType
      //   245: dup
      //   246: aload_1
      //   247: ldc 'string-ref'
      //   249: iconst_1
      //   250: aload #7
      //   252: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   255: athrow
      //   256: astore_1
      //   257: new gnu/mapping/WrongType
      //   260: dup
      //   261: aload_1
      //   262: ldc 'string-ref'
      //   264: iconst_2
      //   265: aload #6
      //   267: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   270: athrow
      //   271: astore_1
      //   272: new gnu/mapping/WrongType
      //   275: dup
      //   276: aload_1
      //   277: ldc 'string-ref'
      //   279: iconst_1
      //   280: aload #7
      //   282: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   285: athrow
      //   286: astore_1
      //   287: new gnu/mapping/WrongType
      //   290: dup
      //   291: aload_1
      //   292: ldc 'string-ref'
      //   294: iconst_2
      //   295: aload #6
      //   297: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   300: athrow
      //   301: astore_1
      //   302: new gnu/mapping/WrongType
      //   305: dup
      //   306: aload_1
      //   307: ldc 'substring'
      //   309: iconst_1
      //   310: aload #8
      //   312: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   315: athrow
      //   316: astore_2
      //   317: new gnu/mapping/WrongType
      //   320: dup
      //   321: aload_2
      //   322: ldc 'substring'
      //   324: iconst_2
      //   325: aload_1
      //   326: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   329: athrow
      //   330: astore_1
      //   331: new gnu/mapping/WrongType
      //   334: dup
      //   335: aload_1
      //   336: ldc 'substring'
      //   338: iconst_3
      //   339: aload #6
      //   341: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   344: athrow
      // Exception table:
      //   from	to	target	type
      //   20	30	225	java/lang/ClassCastException
      //   62	69	241	java/lang/ClassCastException
      //   69	78	256	java/lang/ClassCastException
      //   146	153	271	java/lang/ClassCastException
      //   153	162	286	java/lang/ClassCastException
      //   188	195	301	java/lang/ClassCastException
      //   195	203	316	java/lang/ClassCastException
      //   203	213	330	java/lang/ClassCastException
    }
    
    public Object lambda4real(Object param1Object1, Object param1Object2) {
      // Byte code:
      //   0: new gnu/kawa/slib/printf$frame2
      //   3: dup
      //   4: invokespecial <init> : ()V
      //   7: astore #5
      //   9: aload #5
      //   11: aload_0
      //   12: putfield staticLink : Lgnu/kawa/slib/printf$frame;
      //   15: aload #5
      //   17: aload_2
      //   18: putfield cont : Ljava/lang/Object;
      //   21: aload #5
      //   23: getfield lambda$Fn5 : Lgnu/expr/ModuleMethod;
      //   26: astore_2
      //   27: getstatic kawa/standard/Scheme.numLss : Lgnu/kawa/functions/NumberCompare;
      //   30: aload_1
      //   31: aload_0
      //   32: getfield n : I
      //   35: iconst_1
      //   36: isub
      //   37: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   40: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   43: astore #5
      //   45: aload #5
      //   47: checkcast java/lang/Boolean
      //   50: invokevirtual booleanValue : ()Z
      //   53: istore #4
      //   55: iload #4
      //   57: ifeq -> 189
      //   60: getstatic gnu/kawa/slib/printf.Lit8 : Lgnu/text/Char;
      //   63: astore #6
      //   65: aload_0
      //   66: getfield str : Ljava/lang/Object;
      //   69: astore #5
      //   71: aload #5
      //   73: checkcast java/lang/CharSequence
      //   76: astore #7
      //   78: aload_1
      //   79: checkcast java/lang/Number
      //   82: invokevirtual intValue : ()I
      //   85: istore_3
      //   86: aload #6
      //   88: aload #7
      //   90: iload_3
      //   91: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
      //   94: invokestatic make : (I)Lgnu/text/Char;
      //   97: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
      //   100: ifeq -> 194
      //   103: aload_0
      //   104: getfield str : Ljava/lang/Object;
      //   107: astore #5
      //   109: aload #5
      //   111: checkcast java/lang/CharSequence
      //   114: astore #6
      //   116: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   119: aload_1
      //   120: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
      //   123: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   126: astore #5
      //   128: aload #5
      //   130: checkcast java/lang/Number
      //   133: invokevirtual intValue : ()I
      //   136: istore_3
      //   137: aload #6
      //   139: iload_3
      //   140: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
      //   143: istore_3
      //   144: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   147: iload_3
      //   148: invokestatic make : (I)Lgnu/text/Char;
      //   151: getstatic gnu/kawa/slib/printf.Lit12 : Lgnu/text/Char;
      //   154: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   157: astore #5
      //   159: aload #5
      //   161: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   164: if_acmpeq -> 203
      //   167: aload #5
      //   169: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   172: if_acmpeq -> 234
      //   175: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   178: aload_1
      //   179: getstatic gnu/kawa/slib/printf.Lit14 : Lgnu/math/IntNum;
      //   182: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   185: astore_1
      //   186: goto -> 27
      //   189: iload #4
      //   191: ifne -> 103
      //   194: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   197: aload_2
      //   198: aload_1
      //   199: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   202: areturn
      //   203: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   206: iload_3
      //   207: invokestatic make : (I)Lgnu/text/Char;
      //   210: getstatic gnu/kawa/slib/printf.Lit3 : Lgnu/text/Char;
      //   213: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   216: astore #5
      //   218: aload #5
      //   220: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   223: if_acmpeq -> 262
      //   226: aload #5
      //   228: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   231: if_acmpne -> 175
      //   234: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   237: iload_3
      //   238: invokestatic make : (I)Lgnu/text/Char;
      //   241: getstatic gnu/kawa/slib/printf.Lit11 : Lgnu/text/Char;
      //   244: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   247: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   250: if_acmpeq -> 284
      //   253: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   256: aload_2
      //   257: aload_1
      //   258: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   261: areturn
      //   262: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   265: iload_3
      //   266: invokestatic make : (I)Lgnu/text/Char;
      //   269: getstatic gnu/kawa/slib/printf.Lit13 : Lgnu/text/Char;
      //   272: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   275: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   278: if_acmpeq -> 234
      //   281: goto -> 175
      //   284: invokestatic lambda1parseError : ()Ljava/lang/Boolean;
      //   287: areturn
      //   288: astore_1
      //   289: new gnu/mapping/WrongType
      //   292: dup
      //   293: aload_1
      //   294: ldc 'x'
      //   296: bipush #-2
      //   298: aload #5
      //   300: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   303: athrow
      //   304: astore_1
      //   305: new gnu/mapping/WrongType
      //   308: dup
      //   309: aload_1
      //   310: ldc 'string-ref'
      //   312: iconst_1
      //   313: aload #5
      //   315: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   318: athrow
      //   319: astore_2
      //   320: new gnu/mapping/WrongType
      //   323: dup
      //   324: aload_2
      //   325: ldc 'string-ref'
      //   327: iconst_2
      //   328: aload_1
      //   329: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   332: athrow
      //   333: astore_1
      //   334: new gnu/mapping/WrongType
      //   337: dup
      //   338: aload_1
      //   339: ldc 'string-ref'
      //   341: iconst_1
      //   342: aload #5
      //   344: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   347: athrow
      //   348: astore_1
      //   349: new gnu/mapping/WrongType
      //   352: dup
      //   353: aload_1
      //   354: ldc 'string-ref'
      //   356: iconst_2
      //   357: aload #5
      //   359: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   362: athrow
      // Exception table:
      //   from	to	target	type
      //   45	55	288	java/lang/ClassCastException
      //   71	78	304	java/lang/ClassCastException
      //   78	86	319	java/lang/ClassCastException
      //   109	116	333	java/lang/ClassCastException
      //   128	137	348	java/lang/ClassCastException
    }
    
    Object lambda5(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4) {
      frame0 frame0 = new frame0();
      frame0.staticLink = this;
      frame0.sgn = param1Object2;
      frame0.digs = param1Object3;
      frame0.ex = param1Object4;
      if (Scheme.numEqu.apply2(param1Object1, Integer.valueOf(this.n)) != Boolean.FALSE)
        return Scheme.applyToArgs.apply4(this.proc, frame0.sgn, frame0.digs, frame0.ex); 
      param1Object2 = this.str;
      try {
        param1Object3 = param1Object2;
        try {
          int i = ((Number)param1Object1).intValue();
          if (lists.memv(Char.make(strings.stringRef((CharSequence)param1Object3, i)), printf.Lit2) != Boolean.FALSE)
            return lambda4real(param1Object1, frame0.lambda$Fn2); 
          param1Object3 = Scheme.isEqv;
          param1Object2 = this.str;
          try {
            param1Object4 = param1Object2;
            try {
              i = ((Number)param1Object1).intValue();
              if (param1Object3.apply2(Char.make(strings.stringRef((CharSequence)param1Object4, i)), printf.Lit4) != Boolean.FALSE) {
                param1Object1 = this.str;
                try {
                  param1Object2 = param1Object1;
                  frame0.num = numbers.string$To$Number((CharSequence)param1Object2);
                  if (frame0.num != Boolean.FALSE) {
                    param1Object1 = frame0.num;
                    try {
                      param1Object2 = param1Object1;
                      return printf.stdio$ClParseFloat(numbers.number$To$String((Number)numbers.realPart((Complex)param1Object2)), frame0.lambda$Fn3);
                    } catch (ClassCastException null) {
                      throw new WrongType(classCastException, "real-part", 1, param1Object1);
                    } 
                  } 
                  return lambda1parseError();
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException, "string->number", 1, param1Object1);
                } 
              } 
              return Boolean.FALSE;
            } catch (ClassCastException null) {
              throw new WrongType(classCastException, "string-ref", 2, param1Object1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-ref", 1, classCastException);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 2, classCastException1);
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "string-ref", 1, classCastException);
      } 
    }
    
    public int match4(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 12) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.value4 = param1Object4;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 4;
        return 0;
      } 
      return super.match4(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1Object4, param1CallContext);
    }
    
    public class frame0 extends ModuleBody {
      Object digs;
      
      Object ex;
      
      final ModuleMethod lambda$Fn2;
      
      final ModuleMethod lambda$Fn3;
      
      Object num;
      
      Object sgn;
      
      printf.frame staticLink;
      
      public frame0() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 16388);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:111");
        this.lambda$Fn2 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 3, null, 12291);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:123");
        this.lambda$Fn3 = moduleMethod;
      }
      
      public Object apply3(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, Object param2Object3) {
        return (param2ModuleMethod.selector == 3) ? lambda7(param2Object1, param2Object2, param2Object3) : super.apply3(param2ModuleMethod, param2Object1, param2Object2, param2Object3);
      }
      
      public Object apply4(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, Object param2Object3, Object param2Object4) {
        return (param2ModuleMethod.selector == 2) ? lambda6(param2Object1, param2Object2, param2Object3, param2Object4) : super.apply4(param2ModuleMethod, param2Object1, param2Object2, param2Object3, param2Object4);
      }
      
      Object lambda6(Object param2Object1, Object param2Object2, Object param2Object3, Object param2Object4) {
        Object object = Scheme.numEqu.apply2(param2Object1, Integer.valueOf(this.staticLink.n - 1));
        try {
          boolean bool = ((Boolean)object).booleanValue();
          if (bool) {
            Char char_ = printf.Lit3;
            object = this.staticLink.str;
            try {
              CharSequence charSequence = (CharSequence)object;
              try {
                int i = ((Number)param2Object1).intValue();
                return unicode.isCharCi$Eq(char_, Char.make(strings.stringRef(charSequence, i))) ? Scheme.applyToArgs.applyN(new Object[] { this.staticLink.proc, this.sgn, this.digs, this.ex, param2Object2, param2Object3, param2Object4 }) : printf.frame.lambda1parseError();
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 2, param2Object1);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string-ref", 1, object);
            } 
          } 
          return bool ? Scheme.applyToArgs.applyN(new Object[] { this.staticLink.proc, this.sgn, this.digs, this.ex, classCastException, param2Object3, param2Object4 }) : printf.frame.lambda1parseError();
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "x", -2, object);
        } 
      }
      
      Object lambda7(Object param2Object1, Object param2Object2, Object param2Object3) {
        frame1 frame1 = new frame1();
        frame1.staticLink = this;
        frame1.sgn = param2Object1;
        frame1.digs = param2Object2;
        frame1.ex = param2Object3;
        param2Object1 = this.num;
        try {
          param2Object2 = param2Object1;
          return printf.stdio$ClParseFloat(numbers.number$To$String((Number)numbers.imagPart((Complex)param2Object2)), frame1.lambda$Fn4);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "imag-part", 1, param2Object1);
        } 
      }
      
      public int match3(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, Object param2Object3, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 3) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.value3 = param2Object3;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 3;
          return 0;
        } 
        return super.match3(param2ModuleMethod, param2Object1, param2Object2, param2Object3, param2CallContext);
      }
      
      public int match4(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, Object param2Object3, Object param2Object4, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 2) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.value3 = param2Object3;
          param2CallContext.value4 = param2Object4;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 4;
          return 0;
        } 
        return super.match4(param2ModuleMethod, param2Object1, param2Object2, param2Object3, param2Object4, param2CallContext);
      }
      
      public class frame1 extends ModuleBody {
        Object digs;
        
        Object ex;
        
        final ModuleMethod lambda$Fn4;
        
        Object sgn;
        
        printf.frame.frame0 staticLink;
        
        public frame1() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 12291);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:126");
          this.lambda$Fn4 = moduleMethod;
        }
        
        public Object apply3(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, Object param3Object3) {
          return (param3ModuleMethod.selector == 1) ? lambda8(param3Object1, param3Object2, param3Object3) : super.apply3(param3ModuleMethod, param3Object1, param3Object2, param3Object3);
        }
        
        Object lambda8(Object param3Object1, Object param3Object2, Object param3Object3) {
          return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.proc, this.sgn, this.digs, this.ex, param3Object1, param3Object2, param3Object3 });
        }
        
        public int match3(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, Object param3Object3, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 1) {
            param3CallContext.value1 = param3Object1;
            param3CallContext.value2 = param3Object2;
            param3CallContext.value3 = param3Object3;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 3;
            return 0;
          } 
          return super.match3(param3ModuleMethod, param3Object1, param3Object2, param3Object3, param3CallContext);
        }
      }
    }
    
    public class frame1 extends ModuleBody {
      Object digs;
      
      Object ex;
      
      final ModuleMethod lambda$Fn4;
      
      Object sgn;
      
      printf.frame.frame0 staticLink;
      
      public frame1() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 12291);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:126");
        this.lambda$Fn4 = moduleMethod;
      }
      
      public Object apply3(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, Object param2Object3) {
        return (param2ModuleMethod.selector == 1) ? lambda8(param2Object1, param2Object2, param2Object3) : super.apply3(param2ModuleMethod, param2Object1, param2Object2, param2Object3);
      }
      
      Object lambda8(Object param2Object1, Object param2Object2, Object param2Object3) {
        return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.proc, this.sgn, this.digs, this.ex, param2Object1, param2Object2, param2Object3 });
      }
      
      public int match3(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, Object param2Object3, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 1) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.value3 = param2Object3;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 3;
          return 0;
        } 
        return super.match3(param2ModuleMethod, param2Object1, param2Object2, param2Object3, param2CallContext);
      }
    }
    
    public class frame2 extends ModuleBody {
      Object cont;
      
      final ModuleMethod lambda$Fn5;
      
      final ModuleMethod lambda$Fn6;
      
      printf.frame staticLink;
      
      public frame2() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 10, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:81");
        this.lambda$Fn6 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 11, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:78");
        this.lambda$Fn5 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 11) ? lambda9(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 10) ? lambda10(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda10(Object param2Object1, Object param2Object2) {
        frame3 frame3 = new frame3();
        frame3.staticLink = this;
        frame3.sgn = param2Object2;
        return this.staticLink.lambda3digits(param2Object1, frame3.lambda$Fn7);
      }
      
      Object lambda9(Object param2Object) {
        return this.staticLink.lambda2sign(param2Object, this.lambda$Fn6);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 11) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 10) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
      
      public class frame3 extends ModuleBody {
        final ModuleMethod lambda$Fn7;
        
        Object sgn;
        
        printf.frame.frame2 staticLink;
        
        public frame3() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 9, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:84");
          this.lambda$Fn7 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 9) ? lambda11(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda11(Object param3Object1, Object param3Object2) {
          frame4 frame4 = new frame4();
          frame4.staticLink = this;
          frame4.idigs = param3Object2;
          param3Object2 = frame4.lambda$Fn8;
          Object object = Scheme.numLss.apply2(param3Object1, Integer.valueOf(this.staticLink.staticLink.n));
          try {
            boolean bool = ((Boolean)object).booleanValue();
            if (bool) {
              Char char_ = printf.Lit11;
              object = this.staticLink.staticLink.str;
              try {
                CharSequence charSequence = (CharSequence)object;
                try {
                  int i = ((Number)param3Object1).intValue();
                  return characters.isChar$Eq(char_, Char.make(strings.stringRef(charSequence, i))) ? Scheme.applyToArgs.apply2(param3Object2, AddOp.$Pl.apply2(param3Object1, printf.Lit7)) : Scheme.applyToArgs.apply2(param3Object2, param3Object1);
                } catch (ClassCastException classCastException2) {
                  throw new WrongType(classCastException2, "string-ref", 2, param3Object1);
                } 
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string-ref", 1, object);
              } 
            } 
            return bool ? Scheme.applyToArgs.apply2(classCastException2, AddOp.$Pl.apply2(classCastException1, printf.Lit7)) : Scheme.applyToArgs.apply2(classCastException2, classCastException1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "x", -2, object);
          } 
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 9) {
            param3CallContext.value1 = param3Object1;
            param3CallContext.value2 = param3Object2;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
        }
        
        public class frame4 extends ModuleBody {
          Object idigs;
          
          final ModuleMethod lambda$Fn8;
          
          final ModuleMethod lambda$Fn9;
          
          printf.frame.frame2.frame3 staticLink;
          
          public frame4() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 7, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:90");
            this.lambda$Fn9 = moduleMethod;
            moduleMethod = new ModuleMethod(this, 8, null, 4097);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:87");
            this.lambda$Fn8 = moduleMethod;
          }
          
          public Object apply1(ModuleMethod param4ModuleMethod, Object param4Object) {
            return (param4ModuleMethod.selector == 8) ? lambda12(param4Object) : super.apply1(param4ModuleMethod, param4Object);
          }
          
          public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
            return (param4ModuleMethod.selector == 7) ? lambda13(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
          }
          
          Object lambda12(Object param4Object) {
            return this.staticLink.staticLink.staticLink.lambda3digits(param4Object, this.lambda$Fn9);
          }
          
          Object lambda13(Object param4Object1, Object param4Object2) {
            frame5 frame5 = new frame5();
            frame5.staticLink = this;
            frame5.fdigs = param4Object2;
            ModuleMethod moduleMethod = frame5.lambda$Fn10;
            printf.frame frame = this.staticLink.staticLink.staticLink;
            param4Object2 = new frame6();
            ((frame6)param4Object2).staticLink = frame;
            ((frame6)param4Object2).cont = moduleMethod;
            if (Scheme.numGEq.apply2(param4Object1, Integer.valueOf(this.staticLink.staticLink.staticLink.n)) != Boolean.FALSE)
              return Scheme.applyToArgs.apply3(((frame6)param4Object2).cont, param4Object1, printf.Lit1); 
            Object object = this.staticLink.staticLink.staticLink.str;
            try {
              CharSequence charSequence = (CharSequence)object;
              try {
                int i = ((Number)param4Object1).intValue();
                return (lists.memv(Char.make(strings.stringRef(charSequence, i)), printf.Lit10) != Boolean.FALSE) ? this.staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(param4Object1, printf.Lit7), ((frame6)param4Object2).lambda$Fn11) : Scheme.applyToArgs.apply3(((frame6)param4Object2).cont, param4Object1, printf.Lit1);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 2, param4Object1);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 1, object);
            } 
          }
          
          public int match1(ModuleMethod param4ModuleMethod, Object param4Object, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 8) {
              param4CallContext.value1 = param4Object;
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 1;
              return 0;
            } 
            return super.match1(param4ModuleMethod, param4Object, param4CallContext);
          }
          
          public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 7) {
              param4CallContext.value1 = param4Object1;
              param4CallContext.value2 = param4Object2;
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
          }
          
          public class frame5 extends ModuleBody {
            Object fdigs;
            
            final ModuleMethod lambda$Fn10;
            
            printf.frame.frame2.frame3.frame4 staticLink;
            
            public frame5() {
              ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
              moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
              this.lambda$Fn10 = moduleMethod;
            }
            
            public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
              return (param5ModuleMethod.selector == 6) ? lambda14(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
            }
            
            Object lambda14(Object param5Object1, Object param5Object2) {
              FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
              int i = strings.stringLength((CharSequence)fString);
              IntNum intNum = printf.Lit7;
              AddOp addOp = AddOp.$Pl;
              Object object = this.staticLink.idigs;
              try {
                CharSequence charSequence = (CharSequence)object;
                param5Object2 = addOp.apply2(param5Object2, Integer.valueOf(strings.stringLength(charSequence)));
                while (true) {
                  Object object1;
                  if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
                    return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param5Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
                  object = printf.Lit9;
                  try {
                    int j = ((Number)intNum).intValue();
                    if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                      object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                      param5Object2 = AddOp.$Mn.apply2(param5Object2, printf.Lit7);
                      continue;
                    } 
                    object = Scheme.applyToArgs;
                    Object object2 = this.staticLink.staticLink.staticLink.cont;
                    Object object3 = this.staticLink.staticLink.sgn;
                    object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
                    try {
                      j = ((Number)object1).intValue();
                      return object.applyN(new Object[] { object2, param5Object1, object3, strings.substring((CharSequence)fString, j, i), param5Object2 });
                    } catch (ClassCastException classCastException) {
                      throw new WrongType(classCastException, "substring", 2, object1);
                    } 
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "string-ref", 2, object1);
                  } 
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-length", 1, object);
              } 
            }
            
            public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
              if (param5ModuleMethod.selector == 6) {
                param5CallContext.value1 = param5Object1;
                param5CallContext.value2 = param5Object2;
                param5CallContext.proc = (Procedure)param5ModuleMethod;
                param5CallContext.pc = 2;
                return 0;
              } 
              return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
            }
          }
          
          public class frame6 extends ModuleBody {
            Object cont;
            
            final ModuleMethod lambda$Fn11;
            
            printf.frame staticLink;
            
            public frame6() {
              ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
              moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
              this.lambda$Fn11 = moduleMethod;
            }
            
            public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
              return (param5ModuleMethod.selector == 5) ? lambda15(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
            }
            
            Object lambda15(Object param5Object1, Object param5Object2) {
              frame7 frame7 = new frame7();
              frame7.staticLink = this;
              frame7.sgn = param5Object2;
              return this.staticLink.lambda3digits(param5Object1, frame7.lambda$Fn12);
            }
            
            public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
              if (param5ModuleMethod.selector == 5) {
                param5CallContext.value1 = param5Object1;
                param5CallContext.value2 = param5Object2;
                param5CallContext.proc = (Procedure)param5ModuleMethod;
                param5CallContext.pc = 2;
                return 0;
              } 
              return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
            }
            
            public class frame7 extends ModuleBody {
              final ModuleMethod lambda$Fn12;
              
              Object sgn;
              
              printf.frame.frame2.frame3.frame4.frame6 staticLink;
              
              public frame7() {
                ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
                moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
                this.lambda$Fn12 = moduleMethod;
              }
              
              public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
                return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
              }
              
              Object lambda16(Object param6Object1, Object param6Object2) {
                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                Object object1 = this.staticLink.cont;
                Char char_ = printf.Lit5;
                Object object2 = this.sgn;
                try {
                  Char char_1 = (Char)object2;
                  if (characters.isChar$Eq(char_, char_1)) {
                    object2 = AddOp.$Mn;
                    try {
                      CharSequence charSequence = (CharSequence)param6Object2;
                      param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                      return applyToArgs.apply3(object1, param6Object1, param6Object2);
                    } catch (ClassCastException classCastException) {
                      throw new WrongType(classCastException, "string->number", 1, param6Object2);
                    } 
                  } 
                  try {
                    object2 = param6Object2;
                    param6Object2 = numbers.string$To$Number((CharSequence)object2);
                    return applyToArgs.apply3(object1, classCastException, param6Object2);
                  } catch (ClassCastException classCastException1) {
                    throw new WrongType(classCastException1, "string->number", 1, param6Object2);
                  } 
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "char=?", 2, object2);
                } 
              }
              
              public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
                if (param6ModuleMethod.selector == 4) {
                  param6CallContext.value1 = param6Object1;
                  param6CallContext.value2 = param6Object2;
                  param6CallContext.proc = (Procedure)param6ModuleMethod;
                  param6CallContext.pc = 2;
                  return 0;
                } 
                return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
              }
            }
          }
          
          public class frame7 extends ModuleBody {
            final ModuleMethod lambda$Fn12;
            
            Object sgn;
            
            printf.frame.frame2.frame3.frame4.frame6 staticLink;
            
            public frame7() {
              ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
              moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
              this.lambda$Fn12 = moduleMethod;
            }
            
            public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
              return (param5ModuleMethod.selector == 4) ? lambda16(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
            }
            
            Object lambda16(Object param5Object1, Object param5Object2) {
              ApplyToArgs applyToArgs = Scheme.applyToArgs;
              Object object1 = this.staticLink.cont;
              Char char_ = printf.Lit5;
              Object object2 = this.sgn;
              try {
                Char char_1 = (Char)object2;
                if (characters.isChar$Eq(char_, char_1)) {
                  object2 = AddOp.$Mn;
                  try {
                    CharSequence charSequence = (CharSequence)param5Object2;
                    param5Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                    return applyToArgs.apply3(object1, param5Object1, param5Object2);
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "string->number", 1, param5Object2);
                  } 
                } 
                try {
                  object2 = param5Object2;
                  param5Object2 = numbers.string$To$Number((CharSequence)object2);
                  return applyToArgs.apply3(object1, classCastException, param5Object2);
                } catch (ClassCastException classCastException1) {
                  throw new WrongType(classCastException1, "string->number", 1, param5Object2);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "char=?", 2, object2);
              } 
            }
            
            public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
              if (param5ModuleMethod.selector == 4) {
                param5CallContext.value1 = param5Object1;
                param5CallContext.value2 = param5Object2;
                param5CallContext.proc = (Procedure)param5ModuleMethod;
                param5CallContext.pc = 2;
                return 0;
              } 
              return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
            }
          }
        }
        
        public class frame5 extends ModuleBody {
          Object fdigs;
          
          final ModuleMethod lambda$Fn10;
          
          printf.frame.frame2.frame3.frame4 staticLink;
          
          public frame5() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
            this.lambda$Fn10 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
            return (param4ModuleMethod.selector == 6) ? lambda14(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
          }
          
          Object lambda14(Object param4Object1, Object param4Object2) {
            FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
            int i = strings.stringLength((CharSequence)fString);
            IntNum intNum = printf.Lit7;
            AddOp addOp = AddOp.$Pl;
            Object object = this.staticLink.idigs;
            try {
              CharSequence charSequence = (CharSequence)object;
              param4Object2 = addOp.apply2(param4Object2, Integer.valueOf(strings.stringLength(charSequence)));
              while (true) {
                Object object1;
                if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
                  return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param4Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
                object = printf.Lit9;
                try {
                  int j = ((Number)intNum).intValue();
                  if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                    object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                    param4Object2 = AddOp.$Mn.apply2(param4Object2, printf.Lit7);
                    continue;
                  } 
                  object = Scheme.applyToArgs;
                  Object object2 = this.staticLink.staticLink.staticLink.cont;
                  Object object3 = this.staticLink.staticLink.sgn;
                  object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
                  try {
                    j = ((Number)object1).intValue();
                    return object.applyN(new Object[] { object2, param4Object1, object3, strings.substring((CharSequence)fString, j, i), param4Object2 });
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "substring", 2, object1);
                  } 
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string-ref", 2, object1);
                } 
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-length", 1, object);
            } 
          }
          
          public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 6) {
              param4CallContext.value1 = param4Object1;
              param4CallContext.value2 = param4Object2;
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
          }
        }
        
        public class frame6 extends ModuleBody {
          Object cont;
          
          final ModuleMethod lambda$Fn11;
          
          printf.frame staticLink;
          
          public frame6() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
            this.lambda$Fn11 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
            return (param4ModuleMethod.selector == 5) ? lambda15(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
          }
          
          Object lambda15(Object param4Object1, Object param4Object2) {
            frame7 frame7 = new frame7();
            frame7.staticLink = this;
            frame7.sgn = param4Object2;
            return this.staticLink.lambda3digits(param4Object1, frame7.lambda$Fn12);
          }
          
          public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 5) {
              param4CallContext.value1 = param4Object1;
              param4CallContext.value2 = param4Object2;
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
          }
          
          public class frame7 extends ModuleBody {
            final ModuleMethod lambda$Fn12;
            
            Object sgn;
            
            printf.frame.frame2.frame3.frame4.frame6 staticLink;
            
            public frame7() {
              ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
              moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
              this.lambda$Fn12 = moduleMethod;
            }
            
            public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
              return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
            }
            
            Object lambda16(Object param6Object1, Object param6Object2) {
              ApplyToArgs applyToArgs = Scheme.applyToArgs;
              Object object1 = this.staticLink.cont;
              Char char_ = printf.Lit5;
              Object object2 = this.sgn;
              try {
                Char char_1 = (Char)object2;
                if (characters.isChar$Eq(char_, char_1)) {
                  object2 = AddOp.$Mn;
                  try {
                    CharSequence charSequence = (CharSequence)param6Object2;
                    param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                    return applyToArgs.apply3(object1, param6Object1, param6Object2);
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "string->number", 1, param6Object2);
                  } 
                } 
                try {
                  object2 = param6Object2;
                  param6Object2 = numbers.string$To$Number((CharSequence)object2);
                  return applyToArgs.apply3(object1, classCastException, param6Object2);
                } catch (ClassCastException classCastException1) {
                  throw new WrongType(classCastException1, "string->number", 1, param6Object2);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "char=?", 2, object2);
              } 
            }
            
            public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
              if (param6ModuleMethod.selector == 4) {
                param6CallContext.value1 = param6Object1;
                param6CallContext.value2 = param6Object2;
                param6CallContext.proc = (Procedure)param6ModuleMethod;
                param6CallContext.pc = 2;
                return 0;
              } 
              return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
            }
          }
        }
        
        public class frame7 extends ModuleBody {
          final ModuleMethod lambda$Fn12;
          
          Object sgn;
          
          printf.frame.frame2.frame3.frame4.frame6 staticLink;
          
          public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
            return (param4ModuleMethod.selector == 4) ? lambda16(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
          }
          
          Object lambda16(Object param4Object1, Object param4Object2) {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object object1 = this.staticLink.cont;
            Char char_ = printf.Lit5;
            Object object2 = this.sgn;
            try {
              Char char_1 = (Char)object2;
              if (characters.isChar$Eq(char_, char_1)) {
                object2 = AddOp.$Mn;
                try {
                  CharSequence charSequence = (CharSequence)param4Object2;
                  param4Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                  return applyToArgs.apply3(object1, param4Object1, param4Object2);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string->number", 1, param4Object2);
                } 
              } 
              try {
                object2 = param4Object2;
                param4Object2 = numbers.string$To$Number((CharSequence)object2);
                return applyToArgs.apply3(object1, classCastException, param4Object2);
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string->number", 1, param4Object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "char=?", 2, object2);
            } 
          }
          
          public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 4) {
              param4CallContext.value1 = param4Object1;
              param4CallContext.value2 = param4Object2;
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
          }
        }
      }
      
      public class frame4 extends ModuleBody {
        Object idigs;
        
        final ModuleMethod lambda$Fn8;
        
        final ModuleMethod lambda$Fn9;
        
        printf.frame.frame2.frame3 staticLink;
        
        public frame4() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 7, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:90");
          this.lambda$Fn9 = moduleMethod;
          moduleMethod = new ModuleMethod(this, 8, null, 4097);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:87");
          this.lambda$Fn8 = moduleMethod;
        }
        
        public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
          return (param3ModuleMethod.selector == 8) ? lambda12(param3Object) : super.apply1(param3ModuleMethod, param3Object);
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 7) ? lambda13(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda12(Object param3Object) {
          return this.staticLink.staticLink.staticLink.lambda3digits(param3Object, this.lambda$Fn9);
        }
        
        Object lambda13(Object param3Object1, Object param3Object2) {
          frame5 frame5 = new frame5();
          frame5.staticLink = this;
          frame5.fdigs = param3Object2;
          ModuleMethod moduleMethod = frame5.lambda$Fn10;
          printf.frame frame = this.staticLink.staticLink.staticLink;
          param3Object2 = new frame6();
          ((frame6)param3Object2).staticLink = frame;
          ((frame6)param3Object2).cont = moduleMethod;
          if (Scheme.numGEq.apply2(param3Object1, Integer.valueOf(this.staticLink.staticLink.staticLink.n)) != Boolean.FALSE)
            return Scheme.applyToArgs.apply3(((frame6)param3Object2).cont, param3Object1, printf.Lit1); 
          Object object = this.staticLink.staticLink.staticLink.str;
          try {
            CharSequence charSequence = (CharSequence)object;
            try {
              int i = ((Number)param3Object1).intValue();
              return (lists.memv(Char.make(strings.stringRef(charSequence, i)), printf.Lit10) != Boolean.FALSE) ? this.staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(param3Object1, printf.Lit7), ((frame6)param3Object2).lambda$Fn11) : Scheme.applyToArgs.apply3(((frame6)param3Object2).cont, param3Object1, printf.Lit1);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 2, param3Object1);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 1, object);
          } 
        }
        
        public int match1(ModuleMethod param3ModuleMethod, Object param3Object, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 8) {
            param3CallContext.value1 = param3Object;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 1;
            return 0;
          } 
          return super.match1(param3ModuleMethod, param3Object, param3CallContext);
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 7) {
            param3CallContext.value1 = param3Object1;
            param3CallContext.value2 = param3Object2;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
        }
        
        public class frame5 extends ModuleBody {
          Object fdigs;
          
          final ModuleMethod lambda$Fn10;
          
          printf.frame.frame2.frame3.frame4 staticLink;
          
          public frame5() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
            this.lambda$Fn10 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
            return (param5ModuleMethod.selector == 6) ? lambda14(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
          }
          
          Object lambda14(Object param5Object1, Object param5Object2) {
            FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
            int i = strings.stringLength((CharSequence)fString);
            IntNum intNum = printf.Lit7;
            AddOp addOp = AddOp.$Pl;
            Object object = this.staticLink.idigs;
            try {
              CharSequence charSequence = (CharSequence)object;
              param5Object2 = addOp.apply2(param5Object2, Integer.valueOf(strings.stringLength(charSequence)));
              while (true) {
                Object object1;
                if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
                  return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param5Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
                object = printf.Lit9;
                try {
                  int j = ((Number)intNum).intValue();
                  if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                    object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                    param5Object2 = AddOp.$Mn.apply2(param5Object2, printf.Lit7);
                    continue;
                  } 
                  object = Scheme.applyToArgs;
                  Object object2 = this.staticLink.staticLink.staticLink.cont;
                  Object object3 = this.staticLink.staticLink.sgn;
                  object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
                  try {
                    j = ((Number)object1).intValue();
                    return object.applyN(new Object[] { object2, param5Object1, object3, strings.substring((CharSequence)fString, j, i), param5Object2 });
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "substring", 2, object1);
                  } 
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string-ref", 2, object1);
                } 
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-length", 1, object);
            } 
          }
          
          public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 6) {
              param5CallContext.value1 = param5Object1;
              param5CallContext.value2 = param5Object2;
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
          }
        }
        
        public class frame6 extends ModuleBody {
          Object cont;
          
          final ModuleMethod lambda$Fn11;
          
          printf.frame staticLink;
          
          public frame6() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
            this.lambda$Fn11 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
            return (param5ModuleMethod.selector == 5) ? lambda15(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
          }
          
          Object lambda15(Object param5Object1, Object param5Object2) {
            frame7 frame7 = new frame7();
            frame7.staticLink = this;
            frame7.sgn = param5Object2;
            return this.staticLink.lambda3digits(param5Object1, frame7.lambda$Fn12);
          }
          
          public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 5) {
              param5CallContext.value1 = param5Object1;
              param5CallContext.value2 = param5Object2;
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
          }
          
          public class frame7 extends ModuleBody {
            final ModuleMethod lambda$Fn12;
            
            Object sgn;
            
            printf.frame.frame2.frame3.frame4.frame6 staticLink;
            
            public frame7() {
              ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
              moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
              this.lambda$Fn12 = moduleMethod;
            }
            
            public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
              return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
            }
            
            Object lambda16(Object param6Object1, Object param6Object2) {
              ApplyToArgs applyToArgs = Scheme.applyToArgs;
              Object object1 = this.staticLink.cont;
              Char char_ = printf.Lit5;
              Object object2 = this.sgn;
              try {
                Char char_1 = (Char)object2;
                if (characters.isChar$Eq(char_, char_1)) {
                  object2 = AddOp.$Mn;
                  try {
                    CharSequence charSequence = (CharSequence)param6Object2;
                    param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                    return applyToArgs.apply3(object1, param6Object1, param6Object2);
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "string->number", 1, param6Object2);
                  } 
                } 
                try {
                  object2 = param6Object2;
                  param6Object2 = numbers.string$To$Number((CharSequence)object2);
                  return applyToArgs.apply3(object1, classCastException, param6Object2);
                } catch (ClassCastException classCastException1) {
                  throw new WrongType(classCastException1, "string->number", 1, param6Object2);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "char=?", 2, object2);
              } 
            }
            
            public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
              if (param6ModuleMethod.selector == 4) {
                param6CallContext.value1 = param6Object1;
                param6CallContext.value2 = param6Object2;
                param6CallContext.proc = (Procedure)param6ModuleMethod;
                param6CallContext.pc = 2;
                return 0;
              } 
              return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
            }
          }
        }
        
        public class frame7 extends ModuleBody {
          final ModuleMethod lambda$Fn12;
          
          Object sgn;
          
          printf.frame.frame2.frame3.frame4.frame6 staticLink;
          
          public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
            return (param5ModuleMethod.selector == 4) ? lambda16(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
          }
          
          Object lambda16(Object param5Object1, Object param5Object2) {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object object1 = this.staticLink.cont;
            Char char_ = printf.Lit5;
            Object object2 = this.sgn;
            try {
              Char char_1 = (Char)object2;
              if (characters.isChar$Eq(char_, char_1)) {
                object2 = AddOp.$Mn;
                try {
                  CharSequence charSequence = (CharSequence)param5Object2;
                  param5Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                  return applyToArgs.apply3(object1, param5Object1, param5Object2);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string->number", 1, param5Object2);
                } 
              } 
              try {
                object2 = param5Object2;
                param5Object2 = numbers.string$To$Number((CharSequence)object2);
                return applyToArgs.apply3(object1, classCastException, param5Object2);
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string->number", 1, param5Object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "char=?", 2, object2);
            } 
          }
          
          public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 4) {
              param5CallContext.value1 = param5Object1;
              param5CallContext.value2 = param5Object2;
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
          }
        }
      }
      
      public class frame5 extends ModuleBody {
        Object fdigs;
        
        final ModuleMethod lambda$Fn10;
        
        printf.frame.frame2.frame3.frame4 staticLink;
        
        public frame5() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
          this.lambda$Fn10 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 6) ? lambda14(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda14(Object param3Object1, Object param3Object2) {
          FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
          int i = strings.stringLength((CharSequence)fString);
          IntNum intNum = printf.Lit7;
          AddOp addOp = AddOp.$Pl;
          Object object = this.staticLink.idigs;
          try {
            CharSequence charSequence = (CharSequence)object;
            param3Object2 = addOp.apply2(param3Object2, Integer.valueOf(strings.stringLength(charSequence)));
            while (true) {
              Object object1;
              if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
                return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param3Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
              object = printf.Lit9;
              try {
                int j = ((Number)intNum).intValue();
                if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                  object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                  param3Object2 = AddOp.$Mn.apply2(param3Object2, printf.Lit7);
                  continue;
                } 
                object = Scheme.applyToArgs;
                Object object2 = this.staticLink.staticLink.staticLink.cont;
                Object object3 = this.staticLink.staticLink.sgn;
                object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
                try {
                  j = ((Number)object1).intValue();
                  return object.applyN(new Object[] { object2, param3Object1, object3, strings.substring((CharSequence)fString, j, i), param3Object2 });
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "substring", 2, object1);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 2, object1);
              } 
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-length", 1, object);
          } 
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 6) {
            param3CallContext.value1 = param3Object1;
            param3CallContext.value2 = param3Object2;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
        }
      }
      
      public class frame6 extends ModuleBody {
        Object cont;
        
        final ModuleMethod lambda$Fn11;
        
        printf.frame staticLink;
        
        public frame6() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
          this.lambda$Fn11 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 5) ? lambda15(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda15(Object param3Object1, Object param3Object2) {
          frame7 frame7 = new frame7();
          frame7.staticLink = this;
          frame7.sgn = param3Object2;
          return this.staticLink.lambda3digits(param3Object1, frame7.lambda$Fn12);
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 5) {
            param3CallContext.value1 = param3Object1;
            param3CallContext.value2 = param3Object2;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
        }
        
        public class frame7 extends ModuleBody {
          final ModuleMethod lambda$Fn12;
          
          Object sgn;
          
          printf.frame.frame2.frame3.frame4.frame6 staticLink;
          
          public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
            return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
          }
          
          Object lambda16(Object param6Object1, Object param6Object2) {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object object1 = this.staticLink.cont;
            Char char_ = printf.Lit5;
            Object object2 = this.sgn;
            try {
              Char char_1 = (Char)object2;
              if (characters.isChar$Eq(char_, char_1)) {
                object2 = AddOp.$Mn;
                try {
                  CharSequence charSequence = (CharSequence)param6Object2;
                  param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                  return applyToArgs.apply3(object1, param6Object1, param6Object2);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string->number", 1, param6Object2);
                } 
              } 
              try {
                object2 = param6Object2;
                param6Object2 = numbers.string$To$Number((CharSequence)object2);
                return applyToArgs.apply3(object1, classCastException, param6Object2);
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string->number", 1, param6Object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "char=?", 2, object2);
            } 
          }
          
          public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
            if (param6ModuleMethod.selector == 4) {
              param6CallContext.value1 = param6Object1;
              param6CallContext.value2 = param6Object2;
              param6CallContext.proc = (Procedure)param6ModuleMethod;
              param6CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
          }
        }
      }
      
      public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        
        Object sgn;
        
        printf.frame.frame2.frame3.frame4.frame6 staticLink;
        
        public frame7() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
          this.lambda$Fn12 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 4) ? lambda16(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda16(Object param3Object1, Object param3Object2) {
          ApplyToArgs applyToArgs = Scheme.applyToArgs;
          Object object1 = this.staticLink.cont;
          Char char_ = printf.Lit5;
          Object object2 = this.sgn;
          try {
            Char char_1 = (Char)object2;
            if (characters.isChar$Eq(char_, char_1)) {
              object2 = AddOp.$Mn;
              try {
                CharSequence charSequence = (CharSequence)param3Object2;
                param3Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                return applyToArgs.apply3(object1, param3Object1, param3Object2);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string->number", 1, param3Object2);
              } 
            } 
            try {
              object2 = param3Object2;
              param3Object2 = numbers.string$To$Number((CharSequence)object2);
              return applyToArgs.apply3(object1, classCastException, param3Object2);
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string->number", 1, param3Object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char=?", 2, object2);
          } 
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 4) {
            param3CallContext.value1 = param3Object1;
            param3CallContext.value2 = param3Object2;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
        }
      }
    }
    
    public class frame3 extends ModuleBody {
      final ModuleMethod lambda$Fn7;
      
      Object sgn;
      
      printf.frame.frame2 staticLink;
      
      public frame3() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 9, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:84");
        this.lambda$Fn7 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 9) ? lambda11(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda11(Object param2Object1, Object param2Object2) {
        frame4 frame4 = new frame4();
        frame4.staticLink = this;
        frame4.idigs = param2Object2;
        param2Object2 = frame4.lambda$Fn8;
        Object object = Scheme.numLss.apply2(param2Object1, Integer.valueOf(this.staticLink.staticLink.n));
        try {
          boolean bool = ((Boolean)object).booleanValue();
          if (bool) {
            Char char_ = printf.Lit11;
            object = this.staticLink.staticLink.str;
            try {
              CharSequence charSequence = (CharSequence)object;
              try {
                int i = ((Number)param2Object1).intValue();
                return characters.isChar$Eq(char_, Char.make(strings.stringRef(charSequence, i))) ? Scheme.applyToArgs.apply2(param2Object2, AddOp.$Pl.apply2(param2Object1, printf.Lit7)) : Scheme.applyToArgs.apply2(param2Object2, param2Object1);
              } catch (ClassCastException classCastException2) {
                throw new WrongType(classCastException2, "string-ref", 2, param2Object1);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string-ref", 1, object);
            } 
          } 
          return bool ? Scheme.applyToArgs.apply2(classCastException2, AddOp.$Pl.apply2(classCastException1, printf.Lit7)) : Scheme.applyToArgs.apply2(classCastException2, classCastException1);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "x", -2, object);
        } 
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 9) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
      
      public class frame4 extends ModuleBody {
        Object idigs;
        
        final ModuleMethod lambda$Fn8;
        
        final ModuleMethod lambda$Fn9;
        
        printf.frame.frame2.frame3 staticLink;
        
        public frame4() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 7, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:90");
          this.lambda$Fn9 = moduleMethod;
          moduleMethod = new ModuleMethod(this, 8, null, 4097);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:87");
          this.lambda$Fn8 = moduleMethod;
        }
        
        public Object apply1(ModuleMethod param4ModuleMethod, Object param4Object) {
          return (param4ModuleMethod.selector == 8) ? lambda12(param4Object) : super.apply1(param4ModuleMethod, param4Object);
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 7) ? lambda13(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda12(Object param4Object) {
          return this.staticLink.staticLink.staticLink.lambda3digits(param4Object, this.lambda$Fn9);
        }
        
        Object lambda13(Object param4Object1, Object param4Object2) {
          frame5 frame5 = new frame5();
          frame5.staticLink = this;
          frame5.fdigs = param4Object2;
          ModuleMethod moduleMethod = frame5.lambda$Fn10;
          printf.frame frame = this.staticLink.staticLink.staticLink;
          param4Object2 = new frame6();
          ((frame6)param4Object2).staticLink = frame;
          ((frame6)param4Object2).cont = moduleMethod;
          if (Scheme.numGEq.apply2(param4Object1, Integer.valueOf(this.staticLink.staticLink.staticLink.n)) != Boolean.FALSE)
            return Scheme.applyToArgs.apply3(((frame6)param4Object2).cont, param4Object1, printf.Lit1); 
          Object object = this.staticLink.staticLink.staticLink.str;
          try {
            CharSequence charSequence = (CharSequence)object;
            try {
              int i = ((Number)param4Object1).intValue();
              return (lists.memv(Char.make(strings.stringRef(charSequence, i)), printf.Lit10) != Boolean.FALSE) ? this.staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(param4Object1, printf.Lit7), ((frame6)param4Object2).lambda$Fn11) : Scheme.applyToArgs.apply3(((frame6)param4Object2).cont, param4Object1, printf.Lit1);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 2, param4Object1);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 1, object);
          } 
        }
        
        public int match1(ModuleMethod param4ModuleMethod, Object param4Object, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 8) {
            param4CallContext.value1 = param4Object;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 1;
            return 0;
          } 
          return super.match1(param4ModuleMethod, param4Object, param4CallContext);
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 7) {
            param4CallContext.value1 = param4Object1;
            param4CallContext.value2 = param4Object2;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
        }
        
        public class frame5 extends ModuleBody {
          Object fdigs;
          
          final ModuleMethod lambda$Fn10;
          
          printf.frame.frame2.frame3.frame4 staticLink;
          
          public frame5() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
            this.lambda$Fn10 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
            return (param5ModuleMethod.selector == 6) ? lambda14(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
          }
          
          Object lambda14(Object param5Object1, Object param5Object2) {
            FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
            int i = strings.stringLength((CharSequence)fString);
            IntNum intNum = printf.Lit7;
            AddOp addOp = AddOp.$Pl;
            Object object = this.staticLink.idigs;
            try {
              CharSequence charSequence = (CharSequence)object;
              param5Object2 = addOp.apply2(param5Object2, Integer.valueOf(strings.stringLength(charSequence)));
              while (true) {
                Object object1;
                if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
                  return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param5Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
                object = printf.Lit9;
                try {
                  int j = ((Number)intNum).intValue();
                  if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                    object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                    param5Object2 = AddOp.$Mn.apply2(param5Object2, printf.Lit7);
                    continue;
                  } 
                  object = Scheme.applyToArgs;
                  Object object2 = this.staticLink.staticLink.staticLink.cont;
                  Object object3 = this.staticLink.staticLink.sgn;
                  object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
                  try {
                    j = ((Number)object1).intValue();
                    return object.applyN(new Object[] { object2, param5Object1, object3, strings.substring((CharSequence)fString, j, i), param5Object2 });
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "substring", 2, object1);
                  } 
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string-ref", 2, object1);
                } 
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-length", 1, object);
            } 
          }
          
          public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 6) {
              param5CallContext.value1 = param5Object1;
              param5CallContext.value2 = param5Object2;
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
          }
        }
        
        public class frame6 extends ModuleBody {
          Object cont;
          
          final ModuleMethod lambda$Fn11;
          
          printf.frame staticLink;
          
          public frame6() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
            this.lambda$Fn11 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
            return (param5ModuleMethod.selector == 5) ? lambda15(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
          }
          
          Object lambda15(Object param5Object1, Object param5Object2) {
            frame7 frame7 = new frame7();
            frame7.staticLink = this;
            frame7.sgn = param5Object2;
            return this.staticLink.lambda3digits(param5Object1, frame7.lambda$Fn12);
          }
          
          public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 5) {
              param5CallContext.value1 = param5Object1;
              param5CallContext.value2 = param5Object2;
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
          }
          
          public class frame7 extends ModuleBody {
            final ModuleMethod lambda$Fn12;
            
            Object sgn;
            
            printf.frame.frame2.frame3.frame4.frame6 staticLink;
            
            public frame7() {
              ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
              moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
              this.lambda$Fn12 = moduleMethod;
            }
            
            public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
              return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
            }
            
            Object lambda16(Object param6Object1, Object param6Object2) {
              ApplyToArgs applyToArgs = Scheme.applyToArgs;
              Object object1 = this.staticLink.cont;
              Char char_ = printf.Lit5;
              Object object2 = this.sgn;
              try {
                Char char_1 = (Char)object2;
                if (characters.isChar$Eq(char_, char_1)) {
                  object2 = AddOp.$Mn;
                  try {
                    CharSequence charSequence = (CharSequence)param6Object2;
                    param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                    return applyToArgs.apply3(object1, param6Object1, param6Object2);
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "string->number", 1, param6Object2);
                  } 
                } 
                try {
                  object2 = param6Object2;
                  param6Object2 = numbers.string$To$Number((CharSequence)object2);
                  return applyToArgs.apply3(object1, classCastException, param6Object2);
                } catch (ClassCastException classCastException1) {
                  throw new WrongType(classCastException1, "string->number", 1, param6Object2);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "char=?", 2, object2);
              } 
            }
            
            public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
              if (param6ModuleMethod.selector == 4) {
                param6CallContext.value1 = param6Object1;
                param6CallContext.value2 = param6Object2;
                param6CallContext.proc = (Procedure)param6ModuleMethod;
                param6CallContext.pc = 2;
                return 0;
              } 
              return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
            }
          }
        }
        
        public class frame7 extends ModuleBody {
          final ModuleMethod lambda$Fn12;
          
          Object sgn;
          
          printf.frame.frame2.frame3.frame4.frame6 staticLink;
          
          public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
            return (param5ModuleMethod.selector == 4) ? lambda16(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
          }
          
          Object lambda16(Object param5Object1, Object param5Object2) {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object object1 = this.staticLink.cont;
            Char char_ = printf.Lit5;
            Object object2 = this.sgn;
            try {
              Char char_1 = (Char)object2;
              if (characters.isChar$Eq(char_, char_1)) {
                object2 = AddOp.$Mn;
                try {
                  CharSequence charSequence = (CharSequence)param5Object2;
                  param5Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                  return applyToArgs.apply3(object1, param5Object1, param5Object2);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string->number", 1, param5Object2);
                } 
              } 
              try {
                object2 = param5Object2;
                param5Object2 = numbers.string$To$Number((CharSequence)object2);
                return applyToArgs.apply3(object1, classCastException, param5Object2);
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string->number", 1, param5Object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "char=?", 2, object2);
            } 
          }
          
          public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 4) {
              param5CallContext.value1 = param5Object1;
              param5CallContext.value2 = param5Object2;
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
          }
        }
      }
      
      public class frame5 extends ModuleBody {
        Object fdigs;
        
        final ModuleMethod lambda$Fn10;
        
        printf.frame.frame2.frame3.frame4 staticLink;
        
        public frame5() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
          this.lambda$Fn10 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 6) ? lambda14(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda14(Object param4Object1, Object param4Object2) {
          FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
          int i = strings.stringLength((CharSequence)fString);
          IntNum intNum = printf.Lit7;
          AddOp addOp = AddOp.$Pl;
          Object object = this.staticLink.idigs;
          try {
            CharSequence charSequence = (CharSequence)object;
            param4Object2 = addOp.apply2(param4Object2, Integer.valueOf(strings.stringLength(charSequence)));
            while (true) {
              Object object1;
              if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
                return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param4Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
              object = printf.Lit9;
              try {
                int j = ((Number)intNum).intValue();
                if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                  object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                  param4Object2 = AddOp.$Mn.apply2(param4Object2, printf.Lit7);
                  continue;
                } 
                object = Scheme.applyToArgs;
                Object object2 = this.staticLink.staticLink.staticLink.cont;
                Object object3 = this.staticLink.staticLink.sgn;
                object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
                try {
                  j = ((Number)object1).intValue();
                  return object.applyN(new Object[] { object2, param4Object1, object3, strings.substring((CharSequence)fString, j, i), param4Object2 });
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "substring", 2, object1);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 2, object1);
              } 
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-length", 1, object);
          } 
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 6) {
            param4CallContext.value1 = param4Object1;
            param4CallContext.value2 = param4Object2;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
        }
      }
      
      public class frame6 extends ModuleBody {
        Object cont;
        
        final ModuleMethod lambda$Fn11;
        
        printf.frame staticLink;
        
        public frame6() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
          this.lambda$Fn11 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 5) ? lambda15(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda15(Object param4Object1, Object param4Object2) {
          frame7 frame7 = new frame7();
          frame7.staticLink = this;
          frame7.sgn = param4Object2;
          return this.staticLink.lambda3digits(param4Object1, frame7.lambda$Fn12);
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 5) {
            param4CallContext.value1 = param4Object1;
            param4CallContext.value2 = param4Object2;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
        }
        
        public class frame7 extends ModuleBody {
          final ModuleMethod lambda$Fn12;
          
          Object sgn;
          
          printf.frame.frame2.frame3.frame4.frame6 staticLink;
          
          public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
            return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
          }
          
          Object lambda16(Object param6Object1, Object param6Object2) {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object object1 = this.staticLink.cont;
            Char char_ = printf.Lit5;
            Object object2 = this.sgn;
            try {
              Char char_1 = (Char)object2;
              if (characters.isChar$Eq(char_, char_1)) {
                object2 = AddOp.$Mn;
                try {
                  CharSequence charSequence = (CharSequence)param6Object2;
                  param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                  return applyToArgs.apply3(object1, param6Object1, param6Object2);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string->number", 1, param6Object2);
                } 
              } 
              try {
                object2 = param6Object2;
                param6Object2 = numbers.string$To$Number((CharSequence)object2);
                return applyToArgs.apply3(object1, classCastException, param6Object2);
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string->number", 1, param6Object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "char=?", 2, object2);
            } 
          }
          
          public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
            if (param6ModuleMethod.selector == 4) {
              param6CallContext.value1 = param6Object1;
              param6CallContext.value2 = param6Object2;
              param6CallContext.proc = (Procedure)param6ModuleMethod;
              param6CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
          }
        }
      }
      
      public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        
        Object sgn;
        
        printf.frame.frame2.frame3.frame4.frame6 staticLink;
        
        public frame7() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
          this.lambda$Fn12 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 4) ? lambda16(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda16(Object param4Object1, Object param4Object2) {
          ApplyToArgs applyToArgs = Scheme.applyToArgs;
          Object object1 = this.staticLink.cont;
          Char char_ = printf.Lit5;
          Object object2 = this.sgn;
          try {
            Char char_1 = (Char)object2;
            if (characters.isChar$Eq(char_, char_1)) {
              object2 = AddOp.$Mn;
              try {
                CharSequence charSequence = (CharSequence)param4Object2;
                param4Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                return applyToArgs.apply3(object1, param4Object1, param4Object2);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string->number", 1, param4Object2);
              } 
            } 
            try {
              object2 = param4Object2;
              param4Object2 = numbers.string$To$Number((CharSequence)object2);
              return applyToArgs.apply3(object1, classCastException, param4Object2);
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string->number", 1, param4Object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char=?", 2, object2);
          } 
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 4) {
            param4CallContext.value1 = param4Object1;
            param4CallContext.value2 = param4Object2;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
        }
      }
    }
    
    public class frame4 extends ModuleBody {
      Object idigs;
      
      final ModuleMethod lambda$Fn8;
      
      final ModuleMethod lambda$Fn9;
      
      printf.frame.frame2.frame3 staticLink;
      
      public frame4() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 7, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:90");
        this.lambda$Fn9 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 8, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:87");
        this.lambda$Fn8 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 8) ? lambda12(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 7) ? lambda13(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda12(Object param2Object) {
        return this.staticLink.staticLink.staticLink.lambda3digits(param2Object, this.lambda$Fn9);
      }
      
      Object lambda13(Object param2Object1, Object param2Object2) {
        frame5 frame5 = new frame5();
        frame5.staticLink = this;
        frame5.fdigs = param2Object2;
        ModuleMethod moduleMethod = frame5.lambda$Fn10;
        printf.frame frame = this.staticLink.staticLink.staticLink;
        param2Object2 = new frame6();
        ((frame6)param2Object2).staticLink = frame;
        ((frame6)param2Object2).cont = moduleMethod;
        if (Scheme.numGEq.apply2(param2Object1, Integer.valueOf(this.staticLink.staticLink.staticLink.n)) != Boolean.FALSE)
          return Scheme.applyToArgs.apply3(((frame6)param2Object2).cont, param2Object1, printf.Lit1); 
        Object object = this.staticLink.staticLink.staticLink.str;
        try {
          CharSequence charSequence = (CharSequence)object;
          try {
            int i = ((Number)param2Object1).intValue();
            return (lists.memv(Char.make(strings.stringRef(charSequence, i)), printf.Lit10) != Boolean.FALSE) ? this.staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(param2Object1, printf.Lit7), ((frame6)param2Object2).lambda$Fn11) : Scheme.applyToArgs.apply3(((frame6)param2Object2).cont, param2Object1, printf.Lit1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, param2Object1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 1, object);
        } 
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 8) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 7) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
      
      public class frame5 extends ModuleBody {
        Object fdigs;
        
        final ModuleMethod lambda$Fn10;
        
        printf.frame.frame2.frame3.frame4 staticLink;
        
        public frame5() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
          this.lambda$Fn10 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
          return (param5ModuleMethod.selector == 6) ? lambda14(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
        }
        
        Object lambda14(Object param5Object1, Object param5Object2) {
          FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
          int i = strings.stringLength((CharSequence)fString);
          IntNum intNum = printf.Lit7;
          AddOp addOp = AddOp.$Pl;
          Object object = this.staticLink.idigs;
          try {
            CharSequence charSequence = (CharSequence)object;
            param5Object2 = addOp.apply2(param5Object2, Integer.valueOf(strings.stringLength(charSequence)));
            while (true) {
              Object object1;
              if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
                return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param5Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
              object = printf.Lit9;
              try {
                int j = ((Number)intNum).intValue();
                if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                  object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                  param5Object2 = AddOp.$Mn.apply2(param5Object2, printf.Lit7);
                  continue;
                } 
                object = Scheme.applyToArgs;
                Object object2 = this.staticLink.staticLink.staticLink.cont;
                Object object3 = this.staticLink.staticLink.sgn;
                object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
                try {
                  j = ((Number)object1).intValue();
                  return object.applyN(new Object[] { object2, param5Object1, object3, strings.substring((CharSequence)fString, j, i), param5Object2 });
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "substring", 2, object1);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 2, object1);
              } 
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-length", 1, object);
          } 
        }
        
        public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 6) {
            param5CallContext.value1 = param5Object1;
            param5CallContext.value2 = param5Object2;
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
        }
      }
      
      public class frame6 extends ModuleBody {
        Object cont;
        
        final ModuleMethod lambda$Fn11;
        
        printf.frame staticLink;
        
        public frame6() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
          this.lambda$Fn11 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
          return (param5ModuleMethod.selector == 5) ? lambda15(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
        }
        
        Object lambda15(Object param5Object1, Object param5Object2) {
          frame7 frame7 = new frame7();
          frame7.staticLink = this;
          frame7.sgn = param5Object2;
          return this.staticLink.lambda3digits(param5Object1, frame7.lambda$Fn12);
        }
        
        public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 5) {
            param5CallContext.value1 = param5Object1;
            param5CallContext.value2 = param5Object2;
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
        }
        
        public class frame7 extends ModuleBody {
          final ModuleMethod lambda$Fn12;
          
          Object sgn;
          
          printf.frame.frame2.frame3.frame4.frame6 staticLink;
          
          public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
            return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
          }
          
          Object lambda16(Object param6Object1, Object param6Object2) {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object object1 = this.staticLink.cont;
            Char char_ = printf.Lit5;
            Object object2 = this.sgn;
            try {
              Char char_1 = (Char)object2;
              if (characters.isChar$Eq(char_, char_1)) {
                object2 = AddOp.$Mn;
                try {
                  CharSequence charSequence = (CharSequence)param6Object2;
                  param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                  return applyToArgs.apply3(object1, param6Object1, param6Object2);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string->number", 1, param6Object2);
                } 
              } 
              try {
                object2 = param6Object2;
                param6Object2 = numbers.string$To$Number((CharSequence)object2);
                return applyToArgs.apply3(object1, classCastException, param6Object2);
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string->number", 1, param6Object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "char=?", 2, object2);
            } 
          }
          
          public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
            if (param6ModuleMethod.selector == 4) {
              param6CallContext.value1 = param6Object1;
              param6CallContext.value2 = param6Object2;
              param6CallContext.proc = (Procedure)param6ModuleMethod;
              param6CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
          }
        }
      }
      
      public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        
        Object sgn;
        
        printf.frame.frame2.frame3.frame4.frame6 staticLink;
        
        public frame7() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
          this.lambda$Fn12 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
          return (param5ModuleMethod.selector == 4) ? lambda16(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
        }
        
        Object lambda16(Object param5Object1, Object param5Object2) {
          ApplyToArgs applyToArgs = Scheme.applyToArgs;
          Object object1 = this.staticLink.cont;
          Char char_ = printf.Lit5;
          Object object2 = this.sgn;
          try {
            Char char_1 = (Char)object2;
            if (characters.isChar$Eq(char_, char_1)) {
              object2 = AddOp.$Mn;
              try {
                CharSequence charSequence = (CharSequence)param5Object2;
                param5Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                return applyToArgs.apply3(object1, param5Object1, param5Object2);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string->number", 1, param5Object2);
              } 
            } 
            try {
              object2 = param5Object2;
              param5Object2 = numbers.string$To$Number((CharSequence)object2);
              return applyToArgs.apply3(object1, classCastException, param5Object2);
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string->number", 1, param5Object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char=?", 2, object2);
          } 
        }
        
        public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 4) {
            param5CallContext.value1 = param5Object1;
            param5CallContext.value2 = param5Object2;
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
        }
      }
    }
    
    public class frame5 extends ModuleBody {
      Object fdigs;
      
      final ModuleMethod lambda$Fn10;
      
      printf.frame.frame2.frame3.frame4 staticLink;
      
      public frame5() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
        this.lambda$Fn10 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 6) ? lambda14(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda14(Object param2Object1, Object param2Object2) {
        FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
        int i = strings.stringLength((CharSequence)fString);
        IntNum intNum = printf.Lit7;
        AddOp addOp = AddOp.$Pl;
        Object object = this.staticLink.idigs;
        try {
          CharSequence charSequence = (CharSequence)object;
          param2Object2 = addOp.apply2(param2Object2, Integer.valueOf(strings.stringLength(charSequence)));
          while (true) {
            Object object1;
            if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
              return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param2Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
            object = printf.Lit9;
            try {
              int j = ((Number)intNum).intValue();
              if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                param2Object2 = AddOp.$Mn.apply2(param2Object2, printf.Lit7);
                continue;
              } 
              object = Scheme.applyToArgs;
              Object object2 = this.staticLink.staticLink.staticLink.cont;
              Object object3 = this.staticLink.staticLink.sgn;
              object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
              try {
                j = ((Number)object1).intValue();
                return object.applyN(new Object[] { object2, param2Object1, object3, strings.substring((CharSequence)fString, j, i), param2Object2 });
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "substring", 2, object1);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 2, object1);
            } 
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-length", 1, object);
        } 
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 6) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
    }
    
    public class frame6 extends ModuleBody {
      Object cont;
      
      final ModuleMethod lambda$Fn11;
      
      printf.frame staticLink;
      
      public frame6() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
        this.lambda$Fn11 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 5) ? lambda15(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda15(Object param2Object1, Object param2Object2) {
        frame7 frame7 = new frame7();
        frame7.staticLink = this;
        frame7.sgn = param2Object2;
        return this.staticLink.lambda3digits(param2Object1, frame7.lambda$Fn12);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 5) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
      
      public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        
        Object sgn;
        
        printf.frame.frame2.frame3.frame4.frame6 staticLink;
        
        public frame7() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
          this.lambda$Fn12 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
          return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
        }
        
        Object lambda16(Object param6Object1, Object param6Object2) {
          ApplyToArgs applyToArgs = Scheme.applyToArgs;
          Object object1 = this.staticLink.cont;
          Char char_ = printf.Lit5;
          Object object2 = this.sgn;
          try {
            Char char_1 = (Char)object2;
            if (characters.isChar$Eq(char_, char_1)) {
              object2 = AddOp.$Mn;
              try {
                CharSequence charSequence = (CharSequence)param6Object2;
                param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                return applyToArgs.apply3(object1, param6Object1, param6Object2);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string->number", 1, param6Object2);
              } 
            } 
            try {
              object2 = param6Object2;
              param6Object2 = numbers.string$To$Number((CharSequence)object2);
              return applyToArgs.apply3(object1, classCastException, param6Object2);
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string->number", 1, param6Object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char=?", 2, object2);
          } 
        }
        
        public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
          if (param6ModuleMethod.selector == 4) {
            param6CallContext.value1 = param6Object1;
            param6CallContext.value2 = param6Object2;
            param6CallContext.proc = (Procedure)param6ModuleMethod;
            param6CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
        }
      }
    }
    
    public class frame7 extends ModuleBody {
      final ModuleMethod lambda$Fn12;
      
      Object sgn;
      
      printf.frame.frame2.frame3.frame4.frame6 staticLink;
      
      public frame7() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
        this.lambda$Fn12 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 4) ? lambda16(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda16(Object param2Object1, Object param2Object2) {
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Object object1 = this.staticLink.cont;
        Char char_ = printf.Lit5;
        Object object2 = this.sgn;
        try {
          Char char_1 = (Char)object2;
          if (characters.isChar$Eq(char_, char_1)) {
            object2 = AddOp.$Mn;
            try {
              CharSequence charSequence = (CharSequence)param2Object2;
              param2Object2 = object2.apply1(numbers.string$To$Number(charSequence));
              return applyToArgs.apply3(object1, param2Object1, param2Object2);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string->number", 1, param2Object2);
            } 
          } 
          try {
            object2 = param2Object2;
            param2Object2 = numbers.string$To$Number((CharSequence)object2);
            return applyToArgs.apply3(object1, classCastException, param2Object2);
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string->number", 1, param2Object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char=?", 2, object2);
        } 
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 4) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
    }
  }
  
  public class frame0 extends ModuleBody {
    Object digs;
    
    Object ex;
    
    final ModuleMethod lambda$Fn2;
    
    final ModuleMethod lambda$Fn3;
    
    Object num;
    
    Object sgn;
    
    printf.frame staticLink;
    
    public frame0() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 16388);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:111");
      this.lambda$Fn2 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 3, null, 12291);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:123");
      this.lambda$Fn3 = moduleMethod;
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 3) ? lambda7(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    public Object apply4(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4) {
      return (param1ModuleMethod.selector == 2) ? lambda6(param1Object1, param1Object2, param1Object3, param1Object4) : super.apply4(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1Object4);
    }
    
    Object lambda6(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4) {
      Object object = Scheme.numEqu.apply2(param1Object1, Integer.valueOf(this.staticLink.n - 1));
      try {
        boolean bool = ((Boolean)object).booleanValue();
        if (bool) {
          Char char_ = printf.Lit3;
          object = this.staticLink.str;
          try {
            CharSequence charSequence = (CharSequence)object;
            try {
              int i = ((Number)param1Object1).intValue();
              return unicode.isCharCi$Eq(char_, Char.make(strings.stringRef(charSequence, i))) ? Scheme.applyToArgs.applyN(new Object[] { this.staticLink.proc, this.sgn, this.digs, this.ex, param1Object2, param1Object3, param1Object4 }) : printf.frame.lambda1parseError();
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 2, param1Object1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-ref", 1, object);
          } 
        } 
        return bool ? Scheme.applyToArgs.applyN(new Object[] { this.staticLink.proc, this.sgn, this.digs, this.ex, classCastException, param1Object3, param1Object4 }) : printf.frame.lambda1parseError();
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "x", -2, object);
      } 
    }
    
    Object lambda7(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame1 frame1 = new frame1();
      frame1.staticLink = this;
      frame1.sgn = param1Object1;
      frame1.digs = param1Object2;
      frame1.ex = param1Object3;
      param1Object1 = this.num;
      try {
        param1Object2 = param1Object1;
        return printf.stdio$ClParseFloat(numbers.number$To$String((Number)numbers.imagPart((Complex)param1Object2)), frame1.lambda$Fn4);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "imag-part", 1, param1Object1);
      } 
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 3) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public int match4(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 2) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.value4 = param1Object4;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 4;
        return 0;
      } 
      return super.match4(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1Object4, param1CallContext);
    }
    
    public class frame1 extends ModuleBody {
      Object digs;
      
      Object ex;
      
      final ModuleMethod lambda$Fn4;
      
      Object sgn;
      
      printf.frame.frame0 staticLink;
      
      public frame1() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 12291);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:126");
        this.lambda$Fn4 = moduleMethod;
      }
      
      public Object apply3(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, Object param3Object3) {
        return (param3ModuleMethod.selector == 1) ? lambda8(param3Object1, param3Object2, param3Object3) : super.apply3(param3ModuleMethod, param3Object1, param3Object2, param3Object3);
      }
      
      Object lambda8(Object param3Object1, Object param3Object2, Object param3Object3) {
        return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.proc, this.sgn, this.digs, this.ex, param3Object1, param3Object2, param3Object3 });
      }
      
      public int match3(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, Object param3Object3, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 1) {
          param3CallContext.value1 = param3Object1;
          param3CallContext.value2 = param3Object2;
          param3CallContext.value3 = param3Object3;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 3;
          return 0;
        } 
        return super.match3(param3ModuleMethod, param3Object1, param3Object2, param3Object3, param3CallContext);
      }
    }
  }
  
  public class frame1 extends ModuleBody {
    Object digs;
    
    Object ex;
    
    final ModuleMethod lambda$Fn4;
    
    Object sgn;
    
    printf.frame.frame0 staticLink;
    
    public frame1() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 12291);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:126");
      this.lambda$Fn4 = moduleMethod;
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 1) ? lambda8(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda8(Object param1Object1, Object param1Object2, Object param1Object3) {
      return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.proc, this.sgn, this.digs, this.ex, param1Object1, param1Object2, param1Object3 });
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 1) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
  }
  
  public class frame10 extends ModuleBody {
    Object alternate$Mnform;
    
    Object args;
    
    Object blank;
    
    final ModuleMethod lambda$Fn13;
    
    final ModuleMethod lambda$Fn14;
    
    final ModuleMethod lambda$Fn15;
    
    final ModuleMethod lambda$Fn16;
    
    Object leading$Mn0s;
    
    Object left$Mnadjust;
    
    Object os;
    
    Procedure pad = (Procedure)new ModuleMethod(this, 15, printf.Lit67, -4095);
    
    Object pr;
    
    Object precision;
    
    Object signed;
    
    printf.frame9 staticLink;
    
    Object width;
    
    public frame10() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 16, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:472");
      this.lambda$Fn13 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 17, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:476");
      this.lambda$Fn14 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 18, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:484");
      this.lambda$Fn15 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 19, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:494");
      this.lambda$Fn16 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply1(param1ModuleMethod, param1Object);
        case 16:
          return lambda25(param1Object);
        case 17:
          return lambda26(param1Object) ? Boolean.TRUE : Boolean.FALSE;
        case 18:
          return lambda27(param1Object);
        case 19:
          break;
      } 
      return lambda28(param1Object) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public Object applyN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject) {
      Object object;
      if (param1ModuleMethod.selector == 15) {
        object = param1ArrayOfObject[0];
        int i = param1ArrayOfObject.length - 1;
        Object[] arrayOfObject = new Object[i];
        while (true) {
          if (--i < 0)
            return lambda23pad$V(object, arrayOfObject); 
          arrayOfObject[i] = param1ArrayOfObject[i + 1];
        } 
      } 
      return super.applyN((ModuleMethod)object, param1ArrayOfObject);
    }
    
    public Object lambda22readFormatNumber() {
      if (Scheme.isEqv.apply2(printf.Lit66, this.staticLink.fc) != Boolean.FALSE) {
        this.staticLink.lambda18mustAdvance();
        Object object1 = lists.car.apply1(this.args);
        this.args = lists.cdr.apply1(this.args);
        return object1;
      } 
      Object object = this.staticLink.fc;
      IntNum intNum = printf.Lit1;
      while (true) {
        Object object1 = this.staticLink.fc;
        try {
          Object object2;
          Char char_ = (Char)object1;
          if (unicode.isCharNumeric(char_)) {
            this.staticLink.lambda18mustAdvance();
            object1 = this.staticLink.fc;
            AddOp addOp = AddOp.$Pl;
            object2 = MultiplyOp.$St.apply2(intNum, printf.Lit45);
            if (object instanceof Object[]) {
              object = object;
            } else {
              object = new Object[] { object };
            } 
            object2 = addOp.apply2(object2, numbers.string$To$Number(strings.$make$string$((Object[])object)));
            object = object1;
            continue;
          } 
          return object2;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-numeric?", 1, object1);
        } 
      } 
    }
    
    public Object lambda23pad$V(Object param1Object, Object[] param1ArrayOfObject) {
      LList lList = LList.makeList(param1ArrayOfObject, 0);
      try {
        CharSequence charSequence = (CharSequence)param1Object;
        Integer integer = Integer.valueOf(strings.stringLength(charSequence));
        LList lList1 = lList;
        while (true) {
          Object object1;
          if (Scheme.numGEq.apply2(integer, this.width) != Boolean.FALSE)
            return lists.cons(param1Object, lList); 
          if (lists.isNull(lList1)) {
            if (this.left$Mnadjust != Boolean.FALSE) {
              Object object = AddOp.$Mn.apply2(this.width, integer);
              try {
                int i = ((Number)object).intValue();
                return lists.cons(param1Object, append.append$V(new Object[] { lList, LList.list1(strings.makeString(i, printf.Lit29)) }));
              } catch (ClassCastException null) {
                throw new WrongType(classCastException, "make-string", 1, object);
              } 
            } 
            if (this.leading$Mn0s != Boolean.FALSE) {
              Object object = AddOp.$Mn.apply2(this.width, integer);
              try {
                int i = ((Number)object).intValue();
                return lists.cons(classCastException, lists.cons(strings.makeString(i, printf.Lit9), lList));
              } catch (ClassCastException null) {
                throw new WrongType(classCastException, "make-string", 1, object);
              } 
            } 
            object1 = AddOp.$Mn.apply2(this.width, integer);
            try {
              int i = ((Number)object1).intValue();
              return lists.cons(strings.makeString(i, printf.Lit29), lists.cons(classCastException, lList));
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "make-string", 1, object1);
            } 
          } 
          AddOp addOp = AddOp.$Pl;
          Object object2 = lists.car.apply1(object1);
          try {
            CharSequence charSequence1 = (CharSequence)object2;
            Object object = addOp.apply2(integer, Integer.valueOf(strings.stringLength(charSequence1)));
            object1 = lists.cdr.apply1(object1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-length", 1, object2);
          } 
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "string-length", 1, classCastException);
      } 
    }
    
    public Object lambda24integerConvert(Object param1Object1, Object param1Object2, Object param1Object3) {
      // Byte code:
      //   0: aload_0
      //   1: getfield precision : Ljava/lang/Object;
      //   4: astore #6
      //   6: aload #6
      //   8: invokestatic coerceRealNum : (Ljava/lang/Object;)Lgnu/math/RealNum;
      //   11: astore #7
      //   13: aload #7
      //   15: invokestatic isNegative : (Lgnu/math/RealNum;)Z
      //   18: ifne -> 709
      //   21: aload_0
      //   22: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   25: putfield leading$Mn0s : Ljava/lang/Object;
      //   28: aload_0
      //   29: getfield precision : Ljava/lang/Object;
      //   32: astore #6
      //   34: aload #6
      //   36: checkcast java/lang/Number
      //   39: astore #7
      //   41: aload #7
      //   43: invokestatic isZero : (Ljava/lang/Number;)Z
      //   46: istore #5
      //   48: iload #5
      //   50: ifeq -> 232
      //   53: aload_1
      //   54: astore #6
      //   56: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   59: getstatic gnu/kawa/slib/printf.Lit1 : Lgnu/math/IntNum;
      //   62: aload_1
      //   63: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   66: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   69: if_acmpeq -> 77
      //   72: ldc_w ''
      //   75: astore #6
      //   77: aload #6
      //   79: astore_1
      //   80: aload_1
      //   81: invokestatic isSymbol : (Ljava/lang/Object;)Z
      //   84: ifeq -> 243
      //   87: aload_1
      //   88: checkcast gnu/mapping/Symbol
      //   91: astore #6
      //   93: aload #6
      //   95: invokestatic symbol$To$String : (Lgnu/mapping/Symbol;)Ljava/lang/String;
      //   98: astore_1
      //   99: aload_1
      //   100: astore #6
      //   102: aload_3
      //   103: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   106: if_acmpeq -> 119
      //   109: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   112: aload_3
      //   113: aload_1
      //   114: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   117: astore #6
      //   119: ldc_w ''
      //   122: aload #6
      //   124: invokestatic apply : (Ljava/lang/Object;Ljava/lang/Object;)Z
      //   127: ifeq -> 345
      //   130: ldc_w ''
      //   133: astore_1
      //   134: getstatic kawa/standard/Scheme.numLss : Lgnu/kawa/functions/NumberCompare;
      //   137: astore_2
      //   138: aload #6
      //   140: checkcast java/lang/CharSequence
      //   143: astore_3
      //   144: aload_2
      //   145: aload_3
      //   146: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   149: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   152: aload_0
      //   153: getfield precision : Ljava/lang/Object;
      //   156: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   159: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   162: if_acmpeq -> 514
      //   165: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   168: astore_2
      //   169: aload_0
      //   170: getfield precision : Ljava/lang/Object;
      //   173: astore_3
      //   174: aload #6
      //   176: checkcast java/lang/CharSequence
      //   179: astore #7
      //   181: aload_2
      //   182: aload_3
      //   183: aload #7
      //   185: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   188: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   191: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   194: astore_2
      //   195: aload_2
      //   196: checkcast java/lang/Number
      //   199: invokevirtual intValue : ()I
      //   202: istore #4
      //   204: iload #4
      //   206: getstatic gnu/kawa/slib/printf.Lit9 : Lgnu/text/Char;
      //   209: invokestatic makeString : (ILjava/lang/Object;)Ljava/lang/CharSequence;
      //   212: astore_2
      //   213: aload_0
      //   214: aload_1
      //   215: iconst_2
      //   216: anewarray java/lang/Object
      //   219: dup
      //   220: iconst_0
      //   221: aload_2
      //   222: aastore
      //   223: dup
      //   224: iconst_1
      //   225: aload #6
      //   227: aastore
      //   228: invokevirtual lambda23pad$V : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   231: areturn
      //   232: aload_1
      //   233: astore #6
      //   235: iload #5
      //   237: ifeq -> 77
      //   240: goto -> 72
      //   243: aload_1
      //   244: invokestatic isNumber : (Ljava/lang/Object;)Z
      //   247: ifeq -> 276
      //   250: aload_1
      //   251: checkcast java/lang/Number
      //   254: astore #6
      //   256: aload_2
      //   257: checkcast java/lang/Number
      //   260: invokevirtual intValue : ()I
      //   263: istore #4
      //   265: aload #6
      //   267: iload #4
      //   269: invokestatic number$To$String : (Ljava/lang/Number;I)Ljava/lang/CharSequence;
      //   272: astore_1
      //   273: goto -> 99
      //   276: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   279: astore #6
      //   281: aload_1
      //   282: aload #6
      //   284: if_acmpeq -> 315
      //   287: iconst_1
      //   288: istore #4
      //   290: iload #4
      //   292: iconst_1
      //   293: iadd
      //   294: iconst_1
      //   295: iand
      //   296: istore #4
      //   298: iload #4
      //   300: ifeq -> 321
      //   303: iload #4
      //   305: ifeq -> 328
      //   308: ldc_w '0'
      //   311: astore_1
      //   312: goto -> 99
      //   315: iconst_0
      //   316: istore #4
      //   318: goto -> 290
      //   321: aload_1
      //   322: invokestatic isNull : (Ljava/lang/Object;)Z
      //   325: ifne -> 308
      //   328: aload_1
      //   329: invokestatic isString : (Ljava/lang/Object;)Z
      //   332: ifeq -> 338
      //   335: goto -> 99
      //   338: ldc_w '1'
      //   341: astore_1
      //   342: goto -> 99
      //   345: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   348: astore_1
      //   349: getstatic gnu/kawa/slib/printf.Lit5 : Lgnu/text/Char;
      //   352: astore_3
      //   353: aload #6
      //   355: checkcast java/lang/CharSequence
      //   358: astore #7
      //   360: aload_1
      //   361: aload_3
      //   362: aload #7
      //   364: iconst_0
      //   365: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
      //   368: invokestatic make : (I)Lgnu/text/Char;
      //   371: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   374: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   377: if_acmpeq -> 410
      //   380: aload #6
      //   382: checkcast java/lang/CharSequence
      //   385: astore_1
      //   386: aload #6
      //   388: checkcast java/lang/CharSequence
      //   391: astore_2
      //   392: aload_1
      //   393: iconst_1
      //   394: aload_2
      //   395: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   398: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   401: astore #6
      //   403: ldc_w '-'
      //   406: astore_1
      //   407: goto -> 134
      //   410: aload_0
      //   411: getfield signed : Ljava/lang/Object;
      //   414: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   417: if_acmpeq -> 427
      //   420: ldc_w '+'
      //   423: astore_1
      //   424: goto -> 134
      //   427: aload_0
      //   428: getfield blank : Ljava/lang/Object;
      //   431: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   434: if_acmpeq -> 444
      //   437: ldc_w ' '
      //   440: astore_1
      //   441: goto -> 134
      //   444: aload_0
      //   445: getfield alternate$Mnform : Ljava/lang/Object;
      //   448: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   451: if_acmpeq -> 507
      //   454: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   457: aload_2
      //   458: getstatic gnu/kawa/slib/printf.Lit48 : Lgnu/math/IntNum;
      //   461: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   464: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   467: if_acmpeq -> 477
      //   470: ldc_w '0'
      //   473: astore_1
      //   474: goto -> 134
      //   477: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
      //   480: aload_2
      //   481: getstatic gnu/kawa/slib/printf.Lit50 : Lgnu/math/IntNum;
      //   484: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   487: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   490: if_acmpeq -> 500
      //   493: ldc_w '0x'
      //   496: astore_1
      //   497: goto -> 474
      //   500: ldc_w ''
      //   503: astore_1
      //   504: goto -> 474
      //   507: ldc_w ''
      //   510: astore_1
      //   511: goto -> 134
      //   514: ldc_w ''
      //   517: astore_2
      //   518: goto -> 213
      //   521: astore_1
      //   522: new gnu/mapping/WrongType
      //   525: dup
      //   526: aload_1
      //   527: ldc_w 'negative?'
      //   530: iconst_1
      //   531: aload #6
      //   533: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   536: athrow
      //   537: astore_1
      //   538: new gnu/mapping/WrongType
      //   541: dup
      //   542: aload_1
      //   543: ldc_w 'zero?'
      //   546: iconst_1
      //   547: aload #6
      //   549: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   552: athrow
      //   553: astore_2
      //   554: new gnu/mapping/WrongType
      //   557: dup
      //   558: aload_2
      //   559: ldc_w 'symbol->string'
      //   562: iconst_1
      //   563: aload_1
      //   564: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   567: athrow
      //   568: astore_2
      //   569: new gnu/mapping/WrongType
      //   572: dup
      //   573: aload_2
      //   574: ldc_w 'number->string'
      //   577: iconst_1
      //   578: aload_1
      //   579: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   582: athrow
      //   583: astore_1
      //   584: new gnu/mapping/WrongType
      //   587: dup
      //   588: aload_1
      //   589: ldc_w 'number->string'
      //   592: iconst_2
      //   593: aload_2
      //   594: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   597: athrow
      //   598: astore_2
      //   599: new gnu/mapping/WrongType
      //   602: dup
      //   603: aload_2
      //   604: ldc_w 'x'
      //   607: bipush #-2
      //   609: aload_1
      //   610: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   613: athrow
      //   614: astore_1
      //   615: new gnu/mapping/WrongType
      //   618: dup
      //   619: aload_1
      //   620: ldc_w 'string-ref'
      //   623: iconst_1
      //   624: aload #6
      //   626: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   629: athrow
      //   630: astore_1
      //   631: new gnu/mapping/WrongType
      //   634: dup
      //   635: aload_1
      //   636: ldc_w 'substring'
      //   639: iconst_1
      //   640: aload #6
      //   642: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   645: athrow
      //   646: astore_1
      //   647: new gnu/mapping/WrongType
      //   650: dup
      //   651: aload_1
      //   652: ldc_w 'string-length'
      //   655: iconst_1
      //   656: aload #6
      //   658: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   661: athrow
      //   662: astore_1
      //   663: new gnu/mapping/WrongType
      //   666: dup
      //   667: aload_1
      //   668: ldc_w 'string-length'
      //   671: iconst_1
      //   672: aload #6
      //   674: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   677: athrow
      //   678: astore_1
      //   679: new gnu/mapping/WrongType
      //   682: dup
      //   683: aload_1
      //   684: ldc_w 'string-length'
      //   687: iconst_1
      //   688: aload #6
      //   690: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   693: athrow
      //   694: astore_1
      //   695: new gnu/mapping/WrongType
      //   698: dup
      //   699: aload_1
      //   700: ldc_w 'make-string'
      //   703: iconst_1
      //   704: aload_2
      //   705: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   708: athrow
      //   709: goto -> 80
      // Exception table:
      //   from	to	target	type
      //   6	13	521	java/lang/ClassCastException
      //   34	41	537	java/lang/ClassCastException
      //   87	93	553	java/lang/ClassCastException
      //   138	144	662	java/lang/ClassCastException
      //   174	181	678	java/lang/ClassCastException
      //   195	204	694	java/lang/ClassCastException
      //   250	256	568	java/lang/ClassCastException
      //   256	265	583	java/lang/ClassCastException
      //   276	281	598	java/lang/ClassCastException
      //   353	360	614	java/lang/ClassCastException
      //   380	386	630	java/lang/ClassCastException
      //   386	392	646	java/lang/ClassCastException
    }
    
    Object lambda25(Object param1Object) {
      AddOp addOp = AddOp.$Pl;
      Object object = this.pr;
      try {
        CharSequence charSequence = (CharSequence)param1Object;
        this.pr = addOp.apply2(object, Integer.valueOf(strings.stringLength(charSequence)));
        return Scheme.applyToArgs.apply2(this.staticLink.out, param1Object);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-length", 1, param1Object);
      } 
    }
    
    boolean lambda26(Object param1Object) {
      // Byte code:
      //   0: getstatic gnu/expr/Special.undefined : Lgnu/expr/Special;
      //   3: astore_3
      //   4: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   7: astore_3
      //   8: aload_0
      //   9: getfield pr : Ljava/lang/Object;
      //   12: astore #4
      //   14: aload_1
      //   15: checkcast java/lang/CharSequence
      //   18: astore #5
      //   20: aload_3
      //   21: aload #4
      //   23: aload #5
      //   25: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   28: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   31: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   34: astore_3
      //   35: aload_3
      //   36: invokestatic coerceRealNum : (Ljava/lang/Object;)Lgnu/math/RealNum;
      //   39: astore #4
      //   41: aload #4
      //   43: invokestatic isNegative : (Lgnu/math/RealNum;)Z
      //   46: ifeq -> 116
      //   49: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   52: astore #4
      //   54: aload_0
      //   55: getfield staticLink : Lgnu/kawa/slib/printf$frame9;
      //   58: getfield out : Ljava/lang/Object;
      //   61: astore #5
      //   63: aload_1
      //   64: checkcast java/lang/CharSequence
      //   67: astore #6
      //   69: aload_0
      //   70: getfield pr : Ljava/lang/Object;
      //   73: astore_1
      //   74: aload_1
      //   75: checkcast java/lang/Number
      //   78: invokevirtual intValue : ()I
      //   81: istore_2
      //   82: aload #4
      //   84: aload #5
      //   86: aload #6
      //   88: iconst_0
      //   89: iload_2
      //   90: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   93: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   96: pop
      //   97: getstatic gnu/kawa/slib/printf.Lit1 : Lgnu/math/IntNum;
      //   100: astore_1
      //   101: aload_0
      //   102: aload_1
      //   103: putfield pr : Ljava/lang/Object;
      //   106: aload_3
      //   107: invokestatic coerceRealNum : (Ljava/lang/Object;)Lgnu/math/RealNum;
      //   110: astore_1
      //   111: aload_1
      //   112: invokestatic isPositive : (Lgnu/math/RealNum;)Z
      //   115: ireturn
      //   116: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   119: aload_0
      //   120: getfield staticLink : Lgnu/kawa/slib/printf$frame9;
      //   123: getfield out : Ljava/lang/Object;
      //   126: aload_1
      //   127: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   130: pop
      //   131: aload_3
      //   132: astore_1
      //   133: goto -> 101
      //   136: astore_3
      //   137: new gnu/mapping/WrongType
      //   140: dup
      //   141: aload_3
      //   142: ldc_w 'string-length'
      //   145: iconst_1
      //   146: aload_1
      //   147: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   150: athrow
      //   151: astore_1
      //   152: new gnu/mapping/WrongType
      //   155: dup
      //   156: aload_1
      //   157: ldc_w 'negative?'
      //   160: iconst_1
      //   161: aload_3
      //   162: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   165: athrow
      //   166: astore_3
      //   167: new gnu/mapping/WrongType
      //   170: dup
      //   171: aload_3
      //   172: ldc_w 'substring'
      //   175: iconst_1
      //   176: aload_1
      //   177: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   180: athrow
      //   181: astore_3
      //   182: new gnu/mapping/WrongType
      //   185: dup
      //   186: aload_3
      //   187: ldc_w 'substring'
      //   190: iconst_3
      //   191: aload_1
      //   192: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   195: athrow
      //   196: astore_1
      //   197: new gnu/mapping/WrongType
      //   200: dup
      //   201: aload_1
      //   202: ldc_w 'positive?'
      //   205: iconst_1
      //   206: aload_3
      //   207: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   210: athrow
      // Exception table:
      //   from	to	target	type
      //   14	20	136	java/lang/ClassCastException
      //   35	41	151	java/lang/ClassCastException
      //   63	69	166	java/lang/ClassCastException
      //   74	82	181	java/lang/ClassCastException
      //   106	111	196	java/lang/ClassCastException
    }
    
    Boolean lambda27(Object param1Object) {
      AddOp addOp = AddOp.$Mn;
      Object object = this.pr;
      try {
        CharSequence charSequence = (CharSequence)param1Object;
        this.pr = addOp.apply2(object, Integer.valueOf(strings.stringLength(charSequence)));
        if (this.os == Boolean.FALSE) {
          Scheme.applyToArgs.apply2(this.staticLink.out, param1Object);
          return Boolean.TRUE;
        } 
        Object object1 = this.pr;
        try {
          object = LangObjType.coerceRealNum(object1);
          if (numbers.isNegative((RealNum)object)) {
            Scheme.applyToArgs.apply2(this.staticLink.out, this.os);
            this.os = Boolean.FALSE;
            Scheme.applyToArgs.apply2(this.staticLink.out, param1Object);
            return Boolean.TRUE;
          } 
          this.os = strings.stringAppend(new Object[] { this.os, param1Object });
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "negative?", 1, object1);
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "string-length", 1, classCastException);
      } 
    }
    
    boolean lambda28(Object param1Object) {
      // Byte code:
      //   0: getstatic gnu/expr/Special.undefined : Lgnu/expr/Special;
      //   3: astore_3
      //   4: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   7: astore_3
      //   8: aload_0
      //   9: getfield pr : Ljava/lang/Object;
      //   12: astore #4
      //   14: aload_1
      //   15: checkcast java/lang/CharSequence
      //   18: astore #5
      //   20: aload_3
      //   21: aload #4
      //   23: aload #5
      //   25: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   28: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   31: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   34: astore_3
      //   35: aload_3
      //   36: invokestatic coerceRealNum : (Ljava/lang/Object;)Lgnu/math/RealNum;
      //   39: astore #4
      //   41: aload #4
      //   43: invokestatic isNegative : (Lgnu/math/RealNum;)Z
      //   46: ifeq -> 115
      //   49: aload_0
      //   50: getfield os : Ljava/lang/Object;
      //   53: astore #4
      //   55: aload_1
      //   56: checkcast java/lang/CharSequence
      //   59: astore #5
      //   61: aload_0
      //   62: getfield pr : Ljava/lang/Object;
      //   65: astore_1
      //   66: aload_1
      //   67: checkcast java/lang/Number
      //   70: invokevirtual intValue : ()I
      //   73: istore_2
      //   74: aload_0
      //   75: iconst_2
      //   76: anewarray java/lang/Object
      //   79: dup
      //   80: iconst_0
      //   81: aload #4
      //   83: aastore
      //   84: dup
      //   85: iconst_1
      //   86: aload #5
      //   88: iconst_0
      //   89: iload_2
      //   90: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   93: aastore
      //   94: invokestatic stringAppend : ([Ljava/lang/Object;)Lgnu/lists/FString;
      //   97: putfield os : Ljava/lang/Object;
      //   100: aload_0
      //   101: aload_3
      //   102: putfield pr : Ljava/lang/Object;
      //   105: aload_3
      //   106: invokestatic coerceRealNum : (Ljava/lang/Object;)Lgnu/math/RealNum;
      //   109: astore_1
      //   110: aload_1
      //   111: invokestatic isPositive : (Lgnu/math/RealNum;)Z
      //   114: ireturn
      //   115: aload_0
      //   116: iconst_2
      //   117: anewarray java/lang/Object
      //   120: dup
      //   121: iconst_0
      //   122: aload_0
      //   123: getfield os : Ljava/lang/Object;
      //   126: aastore
      //   127: dup
      //   128: iconst_1
      //   129: aload_1
      //   130: aastore
      //   131: invokestatic stringAppend : ([Ljava/lang/Object;)Lgnu/lists/FString;
      //   134: putfield os : Ljava/lang/Object;
      //   137: goto -> 100
      //   140: astore_3
      //   141: new gnu/mapping/WrongType
      //   144: dup
      //   145: aload_3
      //   146: ldc_w 'string-length'
      //   149: iconst_1
      //   150: aload_1
      //   151: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   154: athrow
      //   155: astore_1
      //   156: new gnu/mapping/WrongType
      //   159: dup
      //   160: aload_1
      //   161: ldc_w 'negative?'
      //   164: iconst_1
      //   165: aload_3
      //   166: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   169: athrow
      //   170: astore_3
      //   171: new gnu/mapping/WrongType
      //   174: dup
      //   175: aload_3
      //   176: ldc_w 'substring'
      //   179: iconst_1
      //   180: aload_1
      //   181: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   184: athrow
      //   185: astore_3
      //   186: new gnu/mapping/WrongType
      //   189: dup
      //   190: aload_3
      //   191: ldc_w 'substring'
      //   194: iconst_3
      //   195: aload_1
      //   196: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   199: athrow
      //   200: astore_1
      //   201: new gnu/mapping/WrongType
      //   204: dup
      //   205: aload_1
      //   206: ldc_w 'positive?'
      //   209: iconst_1
      //   210: aload_3
      //   211: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   214: athrow
      // Exception table:
      //   from	to	target	type
      //   14	20	140	java/lang/ClassCastException
      //   35	41	155	java/lang/ClassCastException
      //   55	61	170	java/lang/ClassCastException
      //   66	74	185	java/lang/ClassCastException
      //   105	110	200	java/lang/ClassCastException
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match1(param1ModuleMethod, param1Object, param1CallContext);
        case 19:
          param1CallContext.value1 = param1Object;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 1;
          return 0;
        case 18:
          param1CallContext.value1 = param1Object;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 1;
          return 0;
        case 17:
          param1CallContext.value1 = param1Object;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 1;
          return 0;
        case 16:
          break;
      } 
      param1CallContext.value1 = param1Object;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 1;
      return 0;
    }
    
    public int matchN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 15) {
        param1CallContext.values = param1ArrayOfObject;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 5;
        return 0;
      } 
      return super.matchN(param1ModuleMethod, param1ArrayOfObject, param1CallContext);
    }
  }
  
  public class frame11 extends ModuleBody {
    Object fc;
    
    Procedure format$Mnreal = (Procedure)new ModuleMethod(this, 13, printf.Lit64, -4092);
    
    final ModuleMethod lambda$Fn17;
    
    printf.frame10 staticLink;
    
    public frame11() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 14, null, -4093);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:401");
      this.lambda$Fn17 = moduleMethod;
    }
    
    public Object applyN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject) {
      Object[] arrayOfObject;
      switch (param1ModuleMethod.selector) {
        default:
          return super.applyN(param1ModuleMethod, param1ArrayOfObject);
        case 13:
          object1 = param1ArrayOfObject[0];
          object2 = param1ArrayOfObject[1];
          object3 = param1ArrayOfObject[2];
          object4 = param1ArrayOfObject[3];
          i = param1ArrayOfObject.length - 4;
          arrayOfObject = new Object[i];
          while (true) {
            if (--i < 0)
              return lambda30formatReal$V(object1, object2, object3, object4, arrayOfObject); 
            arrayOfObject[i] = param1ArrayOfObject[i + 4];
          } 
        case 14:
          break;
      } 
      Object object1 = param1ArrayOfObject[0];
      Object object2 = param1ArrayOfObject[1];
      Object object3 = param1ArrayOfObject[2];
      int i = param1ArrayOfObject.length - 3;
      Object object4 = new Object[i];
      while (true) {
        if (--i < 0)
          return lambda31$V(object1, object2, object3, (Object[])object4); 
        object4[i] = param1ArrayOfObject[i + 3];
      } 
    }
    
    public Object lambda29f(Object param1Object1, Object param1Object2, Object param1Object3) {
      // Byte code:
      //   0: aload_1
      //   1: checkcast java/lang/CharSequence
      //   4: astore #7
      //   6: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   9: aload_2
      //   10: aload_0
      //   11: getfield staticLink : Lgnu/kawa/slib/printf$frame10;
      //   14: getfield precision : Ljava/lang/Object;
      //   17: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   20: astore #8
      //   22: aload_3
      //   23: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   26: if_acmpeq -> 200
      //   29: aload_2
      //   30: astore_1
      //   31: aload #7
      //   33: aload #8
      //   35: aload_1
      //   36: invokestatic stdio$ClRoundString : (Ljava/lang/CharSequence;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   39: astore #7
      //   41: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
      //   44: aload_2
      //   45: getstatic gnu/kawa/slib/printf.Lit1 : Lgnu/math/IntNum;
      //   48: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   51: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   54: if_acmpeq -> 259
      //   57: aload_2
      //   58: checkcast java/lang/Number
      //   61: astore_1
      //   62: aload_1
      //   63: invokestatic isZero : (Ljava/lang/Number;)Z
      //   66: ifeq -> 205
      //   69: getstatic gnu/kawa/slib/printf.Lit1 : Lgnu/math/IntNum;
      //   72: astore_1
      //   73: iconst_2
      //   74: anewarray java/lang/Object
      //   77: dup
      //   78: iconst_0
      //   79: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
      //   82: aastore
      //   83: dup
      //   84: iconst_1
      //   85: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   88: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
      //   91: aload_2
      //   92: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   95: aastore
      //   96: invokestatic max : ([Ljava/lang/Object;)Ljava/lang/Object;
      //   99: astore_3
      //   100: aload #7
      //   102: checkcast java/lang/CharSequence
      //   105: astore_2
      //   106: aload_1
      //   107: invokevirtual intValue : ()I
      //   110: istore #4
      //   112: aload_3
      //   113: checkcast java/lang/Number
      //   116: invokevirtual intValue : ()I
      //   119: istore #5
      //   121: aload_2
      //   122: iload #4
      //   124: iload #5
      //   126: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   129: astore_2
      //   130: aload #7
      //   132: checkcast java/lang/CharSequence
      //   135: astore_1
      //   136: aload_3
      //   137: checkcast java/lang/Number
      //   140: invokevirtual intValue : ()I
      //   143: istore #4
      //   145: aload #7
      //   147: checkcast java/lang/CharSequence
      //   150: astore_3
      //   151: aload_1
      //   152: iload #4
      //   154: aload_3
      //   155: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   158: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   161: astore_1
      //   162: aload_1
      //   163: ldc ''
      //   165: invokestatic isString$Eq : (Ljava/lang/Object;Ljava/lang/Object;)Z
      //   168: istore #6
      //   170: iload #6
      //   172: ifeq -> 244
      //   175: aload_0
      //   176: getfield staticLink : Lgnu/kawa/slib/printf$frame10;
      //   179: getfield alternate$Mnform : Ljava/lang/Object;
      //   182: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   185: if_acmpne -> 249
      //   188: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
      //   191: astore_1
      //   192: aload_2
      //   193: aload_1
      //   194: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
      //   197: astore_3
      //   198: aload_3
      //   199: areturn
      //   200: aload_3
      //   201: astore_1
      //   202: goto -> 31
      //   205: getstatic gnu/kawa/slib/printf.Lit9 : Lgnu/text/Char;
      //   208: astore_1
      //   209: aload #7
      //   211: checkcast java/lang/CharSequence
      //   214: astore_3
      //   215: aload_1
      //   216: aload_3
      //   217: iconst_0
      //   218: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
      //   221: invokestatic make : (I)Lgnu/text/Char;
      //   224: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
      //   227: ifeq -> 237
      //   230: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
      //   233: astore_1
      //   234: goto -> 73
      //   237: getstatic gnu/kawa/slib/printf.Lit1 : Lgnu/math/IntNum;
      //   240: astore_1
      //   241: goto -> 73
      //   244: iload #6
      //   246: ifne -> 188
      //   249: ldc '.'
      //   251: aload_1
      //   252: invokestatic list2 : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
      //   255: astore_1
      //   256: goto -> 192
      //   259: aload_0
      //   260: getfield staticLink : Lgnu/kawa/slib/printf$frame10;
      //   263: getfield precision : Ljava/lang/Object;
      //   266: astore_1
      //   267: aload_1
      //   268: checkcast java/lang/Number
      //   271: astore #8
      //   273: aload #8
      //   275: invokestatic isZero : (Ljava/lang/Number;)Z
      //   278: ifeq -> 308
      //   281: aload_0
      //   282: getfield staticLink : Lgnu/kawa/slib/printf$frame10;
      //   285: getfield alternate$Mnform : Ljava/lang/Object;
      //   288: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   291: if_acmpeq -> 302
      //   294: ldc '0.'
      //   296: astore_1
      //   297: aload_1
      //   298: invokestatic list1 : (Ljava/lang/Object;)Lgnu/lists/Pair;
      //   301: areturn
      //   302: ldc '0'
      //   304: astore_1
      //   305: goto -> 297
      //   308: aload_3
      //   309: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   312: if_acmpeq -> 419
      //   315: aload #7
      //   317: ldc ''
      //   319: invokestatic isString$Eq : (Ljava/lang/Object;Ljava/lang/Object;)Z
      //   322: istore #6
      //   324: iload #6
      //   326: ifeq -> 400
      //   329: ldc '0'
      //   331: invokestatic list1 : (Ljava/lang/Object;)Lgnu/lists/Pair;
      //   334: astore_1
      //   335: aload_1
      //   336: astore_3
      //   337: aload_1
      //   338: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   341: if_acmpne -> 198
      //   344: iconst_2
      //   345: anewarray java/lang/Object
      //   348: dup
      //   349: iconst_0
      //   350: aload_0
      //   351: getfield staticLink : Lgnu/kawa/slib/printf$frame10;
      //   354: getfield precision : Ljava/lang/Object;
      //   357: aastore
      //   358: dup
      //   359: iconst_1
      //   360: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   363: getstatic gnu/kawa/slib/printf.Lit17 : Lgnu/math/IntNum;
      //   366: aload_2
      //   367: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   370: aastore
      //   371: invokestatic min : ([Ljava/lang/Object;)Ljava/lang/Object;
      //   374: astore_1
      //   375: aload_1
      //   376: checkcast java/lang/Number
      //   379: invokevirtual intValue : ()I
      //   382: istore #4
      //   384: ldc '0.'
      //   386: iload #4
      //   388: getstatic gnu/kawa/slib/printf.Lit9 : Lgnu/text/Char;
      //   391: invokestatic makeString : (ILjava/lang/Object;)Ljava/lang/CharSequence;
      //   394: aload #7
      //   396: invokestatic list3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
      //   399: areturn
      //   400: iload #6
      //   402: ifeq -> 412
      //   405: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
      //   408: astore_1
      //   409: goto -> 335
      //   412: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   415: astore_1
      //   416: goto -> 335
      //   419: aload_3
      //   420: astore_1
      //   421: goto -> 335
      //   424: astore_2
      //   425: new gnu/mapping/WrongType
      //   428: dup
      //   429: aload_2
      //   430: ldc 'stdio:round-string'
      //   432: iconst_0
      //   433: aload_1
      //   434: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   437: athrow
      //   438: astore_1
      //   439: new gnu/mapping/WrongType
      //   442: dup
      //   443: aload_1
      //   444: ldc 'zero?'
      //   446: iconst_1
      //   447: aload_2
      //   448: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   451: athrow
      //   452: astore_1
      //   453: new gnu/mapping/WrongType
      //   456: dup
      //   457: aload_1
      //   458: ldc 'string-ref'
      //   460: iconst_1
      //   461: aload #7
      //   463: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   466: athrow
      //   467: astore_1
      //   468: new gnu/mapping/WrongType
      //   471: dup
      //   472: aload_1
      //   473: ldc 'substring'
      //   475: iconst_1
      //   476: aload #7
      //   478: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   481: athrow
      //   482: astore_2
      //   483: new gnu/mapping/WrongType
      //   486: dup
      //   487: aload_2
      //   488: ldc 'substring'
      //   490: iconst_2
      //   491: aload_1
      //   492: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   495: athrow
      //   496: astore_1
      //   497: new gnu/mapping/WrongType
      //   500: dup
      //   501: aload_1
      //   502: ldc 'substring'
      //   504: iconst_3
      //   505: aload_3
      //   506: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   509: athrow
      //   510: astore_1
      //   511: new gnu/mapping/WrongType
      //   514: dup
      //   515: aload_1
      //   516: ldc 'substring'
      //   518: iconst_1
      //   519: aload #7
      //   521: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   524: athrow
      //   525: astore_1
      //   526: new gnu/mapping/WrongType
      //   529: dup
      //   530: aload_1
      //   531: ldc 'substring'
      //   533: iconst_2
      //   534: aload_3
      //   535: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   538: athrow
      //   539: astore_1
      //   540: new gnu/mapping/WrongType
      //   543: dup
      //   544: aload_1
      //   545: ldc 'string-length'
      //   547: iconst_1
      //   548: aload #7
      //   550: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   553: athrow
      //   554: astore_2
      //   555: new gnu/mapping/WrongType
      //   558: dup
      //   559: aload_2
      //   560: ldc 'zero?'
      //   562: iconst_1
      //   563: aload_1
      //   564: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   567: athrow
      //   568: astore_2
      //   569: new gnu/mapping/WrongType
      //   572: dup
      //   573: aload_2
      //   574: ldc 'make-string'
      //   576: iconst_1
      //   577: aload_1
      //   578: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   581: athrow
      // Exception table:
      //   from	to	target	type
      //   0	6	424	java/lang/ClassCastException
      //   57	62	438	java/lang/ClassCastException
      //   100	106	467	java/lang/ClassCastException
      //   106	112	482	java/lang/ClassCastException
      //   112	121	496	java/lang/ClassCastException
      //   130	136	510	java/lang/ClassCastException
      //   136	145	525	java/lang/ClassCastException
      //   145	151	539	java/lang/ClassCastException
      //   209	215	452	java/lang/ClassCastException
      //   267	273	554	java/lang/ClassCastException
      //   375	384	568	java/lang/ClassCastException
    }
    
    public Object lambda30formatReal$V(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4, Object[] param1ArrayOfObject) {
      CharSequence charSequence;
      LList lList = LList.makeList(param1ArrayOfObject, 0);
      if (lists.isNull(lList)) {
        Char char_ = printf.Lit5;
        try {
          Char char_1 = (Char)param1Object2;
          if (characters.isChar$Eq(char_, char_1)) {
            param1Object2 = "-";
          } else if (param1Object1 != Boolean.FALSE) {
            param1Object2 = "+";
          } else if (this.staticLink.blank != Boolean.FALSE) {
            param1Object2 = " ";
          } else {
            param1Object2 = "";
          } 
          param1Object1 = Scheme.isEqv.apply2(this.fc, printf.Lit13);
          if ((param1Object1 != Boolean.FALSE) ? (param1Object1 != Boolean.FALSE) : (Scheme.isEqv.apply2(this.fc, printf.Lit54) != Boolean.FALSE)) {
            param1Object1 = Boolean.FALSE;
          } else {
            param1Object1 = Scheme.isEqv.apply2(this.fc, printf.Lit25);
            if ((param1Object1 != Boolean.FALSE) ? (param1Object1 != Boolean.FALSE) : (Scheme.isEqv.apply2(this.fc, printf.Lit26) != Boolean.FALSE)) {
              param1Object1 = lambda29f(param1Object3, param1Object4, Boolean.FALSE);
              return lists.cons(param1Object2, param1Object1);
            } 
            param1Object1 = Scheme.isEqv.apply2(this.fc, printf.Lit55);
            if ((param1Object1 != Boolean.FALSE) ? (param1Object1 != Boolean.FALSE) : (Scheme.isEqv.apply2(this.fc, printf.Lit56) != Boolean.FALSE)) {
              if (Scheme.isEqv.apply2(this.fc, printf.Lit57) != Boolean.FALSE) {
                charSequence = "";
              } else if (Scheme.isEqv.apply2(this.fc, printf.Lit58) != Boolean.FALSE) {
                charSequence = " ";
              } else {
                param1Object1 = Values.empty;
                return lists.cons(param1Object2, param1Object1);
              } 
              try {
                param1Object1 = LangObjType.coerceRealNum(param1Object4);
                if (numbers.isNegative((RealNum)param1Object1)) {
                  param1Object1 = DivideOp.quotient.apply2(AddOp.$Mn.apply2(param1Object4, printf.Lit61), printf.Lit61);
                } else {
                  param1Object1 = DivideOp.quotient.apply2(AddOp.$Mn.apply2(param1Object4, printf.Lit7), printf.Lit61);
                } 
                Object object = Scheme.numLss.apply3(printf.Lit17, AddOp.$Pl.apply2(param1Object1, printf.Lit48), Integer.valueOf(vectors.vectorLength(printf.Lit62)));
                try {
                  boolean bool = ((Boolean)object).booleanValue();
                  if (!bool)
                    if (bool) {
                      param1Object1 = Boolean.TRUE;
                    } else {
                      param1Object1 = Boolean.FALSE;
                    }  
                  if (param1Object1 != Boolean.FALSE) {
                    param1Object4 = AddOp.$Mn.apply2(param1Object4, MultiplyOp.$St.apply2(printf.Lit61, param1Object1));
                    this.staticLink.precision = numbers.max(new Object[] { printf.Lit1, AddOp.$Mn.apply2(this.staticLink.precision, param1Object4) });
                    param1Object3 = lambda29f(param1Object3, param1Object4, Boolean.FALSE);
                    param1Object4 = printf.Lit62;
                    param1Object1 = AddOp.$Pl.apply2(param1Object1, printf.Lit48);
                    try {
                      int i = ((Number)param1Object1).intValue();
                      param1Object1 = append.append$V(new Object[] { param1Object3, LList.list2(charSequence, vectors.vectorRef((FVector)param1Object4, i)) });
                    } catch (ClassCastException null) {
                      throw new WrongType(classCastException2, "vector-ref", 2, param1Object1);
                    } 
                    return lists.cons(classCastException2, param1Object1);
                  } 
                  param1Object1 = this.staticLink.alternate$Mnform;
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException1, "x", -2, object);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException1, "negative?", 1, param1Object4);
              } 
            } 
            param1Object1 = this.staticLink.alternate$Mnform;
          } 
          try {
            charSequence = (CharSequence)param1Object3;
            Object object = AddOp.$Pl.apply2(printf.Lit7, this.staticLink.precision);
            param1Object3 = param1Object1;
            if (param1Object1 != Boolean.FALSE)
              param1Object3 = printf.Lit1; 
            param1Object3 = printf.stdio$ClRoundString(charSequence, object, param1Object3);
            param1Object1 = printf.Lit9;
            try {
              charSequence = (CharSequence)param1Object3;
              if (characters.isChar$Eq((Char)param1Object1, Char.make(strings.stringRef(charSequence, 0)))) {
                param1Object1 = printf.Lit7;
              } else {
                param1Object1 = printf.Lit1;
              } 
              try {
                charSequence = (CharSequence)param1Object3;
                int i = param1Object1.intValue();
                try {
                  object = param1Object3;
                  CharSequence charSequence1 = strings.substring(charSequence, i + 1, strings.stringLength((CharSequence)object));
                  if (!numbers.isZero((Number)param1Object1))
                    param1Object4 = AddOp.$Mn.apply2(param1Object4, printf.Lit7); 
                  try {
                    charSequence = (CharSequence)param1Object3;
                    try {
                      i = param1Object1.intValue();
                      object = LList.list1(strings.substring(charSequence, i, param1Object1.intValue() + 1));
                      boolean bool = strings.isString$Eq(charSequence1, "");
                      if (bool ? (this.staticLink.alternate$Mnform == Boolean.FALSE) : bool) {
                        param1Object1 = "";
                      } else {
                        param1Object1 = ".";
                      } 
                      param1Object3 = this.fc;
                      try {
                        Char char_2 = (Char)param1Object3;
                        if (unicode.isCharUpperCase(char_2)) {
                          param1Object3 = "E";
                        } else {
                          param1Object3 = "e";
                        } 
                        try {
                          RealNum realNum = LangObjType.coerceRealNum(param1Object4);
                          if (numbers.isNegative(realNum)) {
                            charSequence = "-";
                          } else {
                            charSequence = "+";
                          } 
                          param1Object3 = LList.chain4((Pair)object, param1Object1, charSequence1, param1Object3, charSequence);
                          if (Scheme.numLss.apply3(printf.Lit60, param1Object4, printf.Lit45) != Boolean.FALSE) {
                            param1Object1 = "0";
                          } else {
                            param1Object1 = "";
                          } 
                          param1Object1 = LList.chain1((Pair)param1Object3, param1Object1);
                          try {
                            param1Object3 = param1Object4;
                            LList.chain1((Pair)param1Object1, numbers.number$To$String(numbers.abs((Number)param1Object3)));
                            param1Object1 = object;
                            return lists.cons(classCastException2, param1Object1);
                          } catch (ClassCastException null) {
                            throw new WrongType(classCastException1, "abs", 1, param1Object4);
                          } 
                        } catch (ClassCastException null) {
                          throw new WrongType(classCastException1, "negative?", 1, param1Object4);
                        } 
                      } catch (ClassCastException null) {
                        throw new WrongType(classCastException1, "char-upper-case?", 1, param1Object3);
                      } 
                    } catch (ClassCastException classCastException2) {
                      throw new WrongType(classCastException2, "substring", 2, classCastException1);
                    } 
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException1, "substring", 1, param1Object3);
                  } 
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException1, "string-length", 1, param1Object3);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException1, "substring", 1, param1Object3);
              } 
            } catch (ClassCastException null) {
              throw new WrongType(classCastException1, "string-ref", 1, param1Object3);
            } 
          } catch (ClassCastException null) {
            throw new WrongType(classCastException1, "stdio:round-string", 0, param1Object3);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "char=?", 2, classCastException2);
        } 
      } 
      return append.append$V(new Object[] { lambda30formatReal$V(classCastException1, classCastException2, param1Object3, param1Object4, new Object[0]), Scheme.apply.apply3(this.format$Mnreal, Boolean.TRUE, charSequence), printf.Lit63 });
    }
    
    Object lambda31$V(Object param1Object1, Object param1Object2, Object param1Object3, Object[] param1ArrayOfObject) {
      LList lList = LList.makeList(param1ArrayOfObject, 0);
      return Scheme.apply.apply2(this.staticLink.pad, Scheme.apply.applyN(new Object[] { this.format$Mnreal, this.staticLink.signed, param1Object1, param1Object2, param1Object3, lList }));
    }
    
    public int matchN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.matchN(param1ModuleMethod, param1ArrayOfObject, param1CallContext);
        case 14:
          param1CallContext.values = param1ArrayOfObject;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 5;
          return 0;
        case 13:
          break;
      } 
      param1CallContext.values = param1ArrayOfObject;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 5;
      return 0;
    }
  }
  
  public class frame12 extends ModuleBody {
    Object cnt;
    
    final ModuleMethod lambda$Fn18;
    
    Object port;
    
    public frame12() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 20, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:546");
      this.lambda$Fn18 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 20) ? lambda32(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Boolean lambda32(Object param1Object) {
      if (strings.isString(param1Object)) {
        AddOp addOp = AddOp.$Pl;
        try {
          CharSequence charSequence = (CharSequence)param1Object;
          this.cnt = addOp.apply2(Integer.valueOf(strings.stringLength(charSequence)), this.cnt);
          ports.display(param1Object, this.port);
          return Boolean.TRUE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-length", 1, param1Object);
        } 
      } 
      this.cnt = AddOp.$Pl.apply2(printf.Lit7, this.cnt);
      ports.display(param1Object, this.port);
      return Boolean.TRUE;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 20) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame13 extends ModuleBody {
    Object cnt;
    
    Object end;
    
    final ModuleMethod lambda$Fn19;
    
    Object s;
    
    Object str;
    
    public frame13() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 21, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:564");
      this.lambda$Fn19 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 21) ? (lambda33(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda33(Object param1Object) {
      // Byte code:
      //   0: aload_1
      //   1: invokestatic isString : (Ljava/lang/Object;)Z
      //   4: ifeq -> 341
      //   7: aload_0
      //   8: getfield str : Ljava/lang/Object;
      //   11: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   14: if_acmpne -> 65
      //   17: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
      //   20: astore #5
      //   22: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   25: aload_0
      //   26: getfield end : Ljava/lang/Object;
      //   29: aload_0
      //   30: getfield cnt : Ljava/lang/Object;
      //   33: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   36: astore #6
      //   38: aload_1
      //   39: checkcast java/lang/CharSequence
      //   42: astore #7
      //   44: aload #5
      //   46: aload #6
      //   48: aload #7
      //   50: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   53: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   56: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   59: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   62: if_acmpeq -> 219
      //   65: aload_1
      //   66: checkcast java/lang/CharSequence
      //   69: astore #5
      //   71: iconst_2
      //   72: anewarray java/lang/Object
      //   75: dup
      //   76: iconst_0
      //   77: aload #5
      //   79: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   82: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   85: aastore
      //   86: dup
      //   87: iconst_1
      //   88: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   91: aload_0
      //   92: getfield end : Ljava/lang/Object;
      //   95: aload_0
      //   96: getfield cnt : Ljava/lang/Object;
      //   99: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   102: aastore
      //   103: invokestatic min : ([Ljava/lang/Object;)Ljava/lang/Object;
      //   106: astore #6
      //   108: getstatic gnu/kawa/slib/printf.Lit1 : Lgnu/math/IntNum;
      //   111: astore #5
      //   113: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
      //   116: aload #5
      //   118: aload #6
      //   120: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   123: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   126: if_acmpne -> 303
      //   129: aload_0
      //   130: getfield s : Ljava/lang/Object;
      //   133: astore #8
      //   135: aload #8
      //   137: checkcast gnu/lists/CharSeq
      //   140: astore #7
      //   142: aload_0
      //   143: getfield cnt : Ljava/lang/Object;
      //   146: astore #8
      //   148: aload #8
      //   150: checkcast java/lang/Number
      //   153: invokevirtual intValue : ()I
      //   156: istore_3
      //   157: aload_1
      //   158: checkcast java/lang/CharSequence
      //   161: astore #8
      //   163: aload #5
      //   165: checkcast java/lang/Number
      //   168: invokevirtual intValue : ()I
      //   171: istore #4
      //   173: aload #7
      //   175: iload_3
      //   176: aload #8
      //   178: iload #4
      //   180: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
      //   183: invokestatic stringSet$Ex : (Lgnu/lists/CharSeq;IC)V
      //   186: aload_0
      //   187: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   190: aload_0
      //   191: getfield cnt : Ljava/lang/Object;
      //   194: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
      //   197: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   200: putfield cnt : Ljava/lang/Object;
      //   203: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   206: aload #5
      //   208: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
      //   211: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   214: astore #5
      //   216: goto -> 113
      //   219: aload_0
      //   220: getfield s : Ljava/lang/Object;
      //   223: astore #5
      //   225: aload #5
      //   227: checkcast java/lang/CharSequence
      //   230: astore #6
      //   232: aload_0
      //   233: getfield cnt : Ljava/lang/Object;
      //   236: astore #5
      //   238: aload #5
      //   240: checkcast java/lang/Number
      //   243: invokevirtual intValue : ()I
      //   246: istore_3
      //   247: aload_0
      //   248: iconst_2
      //   249: anewarray java/lang/Object
      //   252: dup
      //   253: iconst_0
      //   254: aload #6
      //   256: iconst_0
      //   257: iload_3
      //   258: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   261: aastore
      //   262: dup
      //   263: iconst_1
      //   264: aload_1
      //   265: aastore
      //   266: invokestatic stringAppend : ([Ljava/lang/Object;)Lgnu/lists/FString;
      //   269: putfield s : Ljava/lang/Object;
      //   272: aload_0
      //   273: getfield s : Ljava/lang/Object;
      //   276: astore_1
      //   277: aload_1
      //   278: checkcast java/lang/CharSequence
      //   281: astore #5
      //   283: aload_0
      //   284: aload #5
      //   286: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   289: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   292: putfield cnt : Ljava/lang/Object;
      //   295: aload_0
      //   296: aload_0
      //   297: getfield cnt : Ljava/lang/Object;
      //   300: putfield end : Ljava/lang/Object;
      //   303: aload_0
      //   304: getfield str : Ljava/lang/Object;
      //   307: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   310: if_acmpeq -> 578
      //   313: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
      //   316: aload_0
      //   317: getfield cnt : Ljava/lang/Object;
      //   320: aload_0
      //   321: getfield end : Ljava/lang/Object;
      //   324: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   327: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   330: if_acmpeq -> 573
      //   333: iconst_1
      //   334: istore_3
      //   335: iload_3
      //   336: iconst_1
      //   337: iadd
      //   338: iconst_1
      //   339: iand
      //   340: ireturn
      //   341: aload_0
      //   342: getfield str : Ljava/lang/Object;
      //   345: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   348: if_acmpeq -> 546
      //   351: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
      //   354: aload_0
      //   355: getfield cnt : Ljava/lang/Object;
      //   358: aload_0
      //   359: getfield end : Ljava/lang/Object;
      //   362: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   365: astore #5
      //   367: aload #5
      //   369: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   372: if_acmpne -> 303
      //   375: aload_0
      //   376: getfield str : Ljava/lang/Object;
      //   379: astore #5
      //   381: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   384: astore #6
      //   386: aload #5
      //   388: aload #6
      //   390: if_acmpeq -> 555
      //   393: iconst_1
      //   394: istore_3
      //   395: iload_3
      //   396: iconst_1
      //   397: iadd
      //   398: iconst_1
      //   399: iand
      //   400: istore_3
      //   401: iload_3
      //   402: ifeq -> 560
      //   405: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
      //   408: aload_0
      //   409: getfield cnt : Ljava/lang/Object;
      //   412: aload_0
      //   413: getfield end : Ljava/lang/Object;
      //   416: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   419: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   422: if_acmpeq -> 476
      //   425: aload_0
      //   426: iconst_2
      //   427: anewarray java/lang/Object
      //   430: dup
      //   431: iconst_0
      //   432: aload_0
      //   433: getfield s : Ljava/lang/Object;
      //   436: aastore
      //   437: dup
      //   438: iconst_1
      //   439: bipush #100
      //   441: invokestatic makeString : (I)Ljava/lang/CharSequence;
      //   444: aastore
      //   445: invokestatic stringAppend : ([Ljava/lang/Object;)Lgnu/lists/FString;
      //   448: putfield s : Ljava/lang/Object;
      //   451: aload_0
      //   452: getfield s : Ljava/lang/Object;
      //   455: astore #5
      //   457: aload #5
      //   459: checkcast java/lang/CharSequence
      //   462: astore #6
      //   464: aload_0
      //   465: aload #6
      //   467: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   470: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   473: putfield end : Ljava/lang/Object;
      //   476: aload_0
      //   477: getfield s : Ljava/lang/Object;
      //   480: astore #5
      //   482: aload #5
      //   484: checkcast gnu/lists/CharSeq
      //   487: astore #6
      //   489: aload_0
      //   490: getfield cnt : Ljava/lang/Object;
      //   493: astore #5
      //   495: aload #5
      //   497: checkcast java/lang/Number
      //   500: invokevirtual intValue : ()I
      //   503: istore_3
      //   504: aload_1
      //   505: invokestatic isChar : (Ljava/lang/Object;)Z
      //   508: ifeq -> 567
      //   511: aload_1
      //   512: checkcast gnu/text/Char
      //   515: invokevirtual charValue : ()C
      //   518: istore_2
      //   519: aload #6
      //   521: iload_3
      //   522: iload_2
      //   523: invokestatic stringSet$Ex : (Lgnu/lists/CharSeq;IC)V
      //   526: aload_0
      //   527: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   530: aload_0
      //   531: getfield cnt : Ljava/lang/Object;
      //   534: getstatic gnu/kawa/slib/printf.Lit7 : Lgnu/math/IntNum;
      //   537: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   540: putfield cnt : Ljava/lang/Object;
      //   543: goto -> 303
      //   546: aload_0
      //   547: getfield str : Ljava/lang/Object;
      //   550: astore #5
      //   552: goto -> 367
      //   555: iconst_0
      //   556: istore_3
      //   557: goto -> 395
      //   560: iload_3
      //   561: ifeq -> 476
      //   564: goto -> 425
      //   567: bipush #63
      //   569: istore_2
      //   570: goto -> 519
      //   573: iconst_0
      //   574: istore_3
      //   575: goto -> 335
      //   578: aload_0
      //   579: getfield str : Ljava/lang/Object;
      //   582: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   585: if_acmpeq -> 593
      //   588: iconst_1
      //   589: istore_3
      //   590: goto -> 335
      //   593: iconst_0
      //   594: istore_3
      //   595: goto -> 335
      //   598: astore #5
      //   600: new gnu/mapping/WrongType
      //   603: dup
      //   604: aload #5
      //   606: ldc 'string-length'
      //   608: iconst_1
      //   609: aload_1
      //   610: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   613: athrow
      //   614: astore #5
      //   616: new gnu/mapping/WrongType
      //   619: dup
      //   620: aload #5
      //   622: ldc 'string-length'
      //   624: iconst_1
      //   625: aload_1
      //   626: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   629: athrow
      //   630: astore_1
      //   631: new gnu/mapping/WrongType
      //   634: dup
      //   635: aload_1
      //   636: ldc 'string-set!'
      //   638: iconst_1
      //   639: aload #8
      //   641: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   644: athrow
      //   645: astore_1
      //   646: new gnu/mapping/WrongType
      //   649: dup
      //   650: aload_1
      //   651: ldc 'string-set!'
      //   653: iconst_2
      //   654: aload #8
      //   656: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   659: athrow
      //   660: astore #5
      //   662: new gnu/mapping/WrongType
      //   665: dup
      //   666: aload #5
      //   668: ldc 'string-ref'
      //   670: iconst_1
      //   671: aload_1
      //   672: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   675: athrow
      //   676: astore_1
      //   677: new gnu/mapping/WrongType
      //   680: dup
      //   681: aload_1
      //   682: ldc 'string-ref'
      //   684: iconst_2
      //   685: aload #5
      //   687: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   690: athrow
      //   691: astore_1
      //   692: new gnu/mapping/WrongType
      //   695: dup
      //   696: aload_1
      //   697: ldc 'substring'
      //   699: iconst_1
      //   700: aload #5
      //   702: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   705: athrow
      //   706: astore_1
      //   707: new gnu/mapping/WrongType
      //   710: dup
      //   711: aload_1
      //   712: ldc 'substring'
      //   714: iconst_3
      //   715: aload #5
      //   717: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   720: athrow
      //   721: astore #5
      //   723: new gnu/mapping/WrongType
      //   726: dup
      //   727: aload #5
      //   729: ldc 'string-length'
      //   731: iconst_1
      //   732: aload_1
      //   733: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   736: athrow
      //   737: astore_1
      //   738: new gnu/mapping/WrongType
      //   741: dup
      //   742: aload_1
      //   743: ldc 'x'
      //   745: bipush #-2
      //   747: aload #5
      //   749: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   752: athrow
      //   753: astore_1
      //   754: new gnu/mapping/WrongType
      //   757: dup
      //   758: aload_1
      //   759: ldc 'string-length'
      //   761: iconst_1
      //   762: aload #5
      //   764: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   767: athrow
      //   768: astore_1
      //   769: new gnu/mapping/WrongType
      //   772: dup
      //   773: aload_1
      //   774: ldc 'string-set!'
      //   776: iconst_1
      //   777: aload #5
      //   779: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   782: athrow
      //   783: astore_1
      //   784: new gnu/mapping/WrongType
      //   787: dup
      //   788: aload_1
      //   789: ldc 'string-set!'
      //   791: iconst_2
      //   792: aload #5
      //   794: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   797: athrow
      //   798: astore #5
      //   800: new gnu/mapping/WrongType
      //   803: dup
      //   804: aload #5
      //   806: ldc 'string-set!'
      //   808: iconst_3
      //   809: aload_1
      //   810: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   813: athrow
      // Exception table:
      //   from	to	target	type
      //   38	44	598	java/lang/ClassCastException
      //   65	71	614	java/lang/ClassCastException
      //   135	142	630	java/lang/ClassCastException
      //   148	157	645	java/lang/ClassCastException
      //   157	163	660	java/lang/ClassCastException
      //   163	173	676	java/lang/ClassCastException
      //   225	232	691	java/lang/ClassCastException
      //   238	247	706	java/lang/ClassCastException
      //   277	283	721	java/lang/ClassCastException
      //   381	386	737	java/lang/ClassCastException
      //   457	464	753	java/lang/ClassCastException
      //   482	489	768	java/lang/ClassCastException
      //   495	504	783	java/lang/ClassCastException
      //   511	519	798	java/lang/ClassCastException
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 21) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame2 extends ModuleBody {
    Object cont;
    
    final ModuleMethod lambda$Fn5;
    
    final ModuleMethod lambda$Fn6;
    
    printf.frame staticLink;
    
    public frame2() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 10, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:81");
      this.lambda$Fn6 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 11, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:78");
      this.lambda$Fn5 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 11) ? lambda9(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 10) ? lambda10(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda10(Object param1Object1, Object param1Object2) {
      frame3 frame3 = new frame3();
      frame3.staticLink = this;
      frame3.sgn = param1Object2;
      return this.staticLink.lambda3digits(param1Object1, frame3.lambda$Fn7);
    }
    
    Object lambda9(Object param1Object) {
      return this.staticLink.lambda2sign(param1Object, this.lambda$Fn6);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 11) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 10) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame3 extends ModuleBody {
      final ModuleMethod lambda$Fn7;
      
      Object sgn;
      
      printf.frame.frame2 staticLink;
      
      public frame3() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 9, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:84");
        this.lambda$Fn7 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 9) ? lambda11(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda11(Object param3Object1, Object param3Object2) {
        frame4 frame4 = new frame4();
        frame4.staticLink = this;
        frame4.idigs = param3Object2;
        param3Object2 = frame4.lambda$Fn8;
        Object object = Scheme.numLss.apply2(param3Object1, Integer.valueOf(this.staticLink.staticLink.n));
        try {
          boolean bool = ((Boolean)object).booleanValue();
          if (bool) {
            Char char_ = printf.Lit11;
            object = this.staticLink.staticLink.str;
            try {
              CharSequence charSequence = (CharSequence)object;
              try {
                int i = ((Number)param3Object1).intValue();
                return characters.isChar$Eq(char_, Char.make(strings.stringRef(charSequence, i))) ? Scheme.applyToArgs.apply2(param3Object2, AddOp.$Pl.apply2(param3Object1, printf.Lit7)) : Scheme.applyToArgs.apply2(param3Object2, param3Object1);
              } catch (ClassCastException classCastException2) {
                throw new WrongType(classCastException2, "string-ref", 2, param3Object1);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string-ref", 1, object);
            } 
          } 
          return bool ? Scheme.applyToArgs.apply2(classCastException2, AddOp.$Pl.apply2(classCastException1, printf.Lit7)) : Scheme.applyToArgs.apply2(classCastException2, classCastException1);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "x", -2, object);
        } 
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 9) {
          param3CallContext.value1 = param3Object1;
          param3CallContext.value2 = param3Object2;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
      }
      
      public class frame4 extends ModuleBody {
        Object idigs;
        
        final ModuleMethod lambda$Fn8;
        
        final ModuleMethod lambda$Fn9;
        
        printf.frame.frame2.frame3 staticLink;
        
        public frame4() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 7, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:90");
          this.lambda$Fn9 = moduleMethod;
          moduleMethod = new ModuleMethod(this, 8, null, 4097);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:87");
          this.lambda$Fn8 = moduleMethod;
        }
        
        public Object apply1(ModuleMethod param4ModuleMethod, Object param4Object) {
          return (param4ModuleMethod.selector == 8) ? lambda12(param4Object) : super.apply1(param4ModuleMethod, param4Object);
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 7) ? lambda13(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda12(Object param4Object) {
          return this.staticLink.staticLink.staticLink.lambda3digits(param4Object, this.lambda$Fn9);
        }
        
        Object lambda13(Object param4Object1, Object param4Object2) {
          frame5 frame5 = new frame5();
          frame5.staticLink = this;
          frame5.fdigs = param4Object2;
          ModuleMethod moduleMethod = frame5.lambda$Fn10;
          printf.frame frame = this.staticLink.staticLink.staticLink;
          param4Object2 = new frame6();
          ((frame6)param4Object2).staticLink = frame;
          ((frame6)param4Object2).cont = moduleMethod;
          if (Scheme.numGEq.apply2(param4Object1, Integer.valueOf(this.staticLink.staticLink.staticLink.n)) != Boolean.FALSE)
            return Scheme.applyToArgs.apply3(((frame6)param4Object2).cont, param4Object1, printf.Lit1); 
          Object object = this.staticLink.staticLink.staticLink.str;
          try {
            CharSequence charSequence = (CharSequence)object;
            try {
              int i = ((Number)param4Object1).intValue();
              return (lists.memv(Char.make(strings.stringRef(charSequence, i)), printf.Lit10) != Boolean.FALSE) ? this.staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(param4Object1, printf.Lit7), ((frame6)param4Object2).lambda$Fn11) : Scheme.applyToArgs.apply3(((frame6)param4Object2).cont, param4Object1, printf.Lit1);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 2, param4Object1);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 1, object);
          } 
        }
        
        public int match1(ModuleMethod param4ModuleMethod, Object param4Object, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 8) {
            param4CallContext.value1 = param4Object;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 1;
            return 0;
          } 
          return super.match1(param4ModuleMethod, param4Object, param4CallContext);
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 7) {
            param4CallContext.value1 = param4Object1;
            param4CallContext.value2 = param4Object2;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
        }
        
        public class frame5 extends ModuleBody {
          Object fdigs;
          
          final ModuleMethod lambda$Fn10;
          
          printf.frame.frame2.frame3.frame4 staticLink;
          
          public frame5() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
            this.lambda$Fn10 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
            return (param5ModuleMethod.selector == 6) ? lambda14(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
          }
          
          Object lambda14(Object param5Object1, Object param5Object2) {
            FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
            int i = strings.stringLength((CharSequence)fString);
            IntNum intNum = printf.Lit7;
            AddOp addOp = AddOp.$Pl;
            Object object = this.staticLink.idigs;
            try {
              CharSequence charSequence = (CharSequence)object;
              param5Object2 = addOp.apply2(param5Object2, Integer.valueOf(strings.stringLength(charSequence)));
              while (true) {
                Object object1;
                if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
                  return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param5Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
                object = printf.Lit9;
                try {
                  int j = ((Number)intNum).intValue();
                  if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                    object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                    param5Object2 = AddOp.$Mn.apply2(param5Object2, printf.Lit7);
                    continue;
                  } 
                  object = Scheme.applyToArgs;
                  Object object2 = this.staticLink.staticLink.staticLink.cont;
                  Object object3 = this.staticLink.staticLink.sgn;
                  object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
                  try {
                    j = ((Number)object1).intValue();
                    return object.applyN(new Object[] { object2, param5Object1, object3, strings.substring((CharSequence)fString, j, i), param5Object2 });
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "substring", 2, object1);
                  } 
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string-ref", 2, object1);
                } 
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-length", 1, object);
            } 
          }
          
          public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 6) {
              param5CallContext.value1 = param5Object1;
              param5CallContext.value2 = param5Object2;
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
          }
        }
        
        public class frame6 extends ModuleBody {
          Object cont;
          
          final ModuleMethod lambda$Fn11;
          
          printf.frame staticLink;
          
          public frame6() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
            this.lambda$Fn11 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
            return (param5ModuleMethod.selector == 5) ? lambda15(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
          }
          
          Object lambda15(Object param5Object1, Object param5Object2) {
            frame7 frame7 = new frame7();
            frame7.staticLink = this;
            frame7.sgn = param5Object2;
            return this.staticLink.lambda3digits(param5Object1, frame7.lambda$Fn12);
          }
          
          public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 5) {
              param5CallContext.value1 = param5Object1;
              param5CallContext.value2 = param5Object2;
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
          }
          
          public class frame7 extends ModuleBody {
            final ModuleMethod lambda$Fn12;
            
            Object sgn;
            
            printf.frame.frame2.frame3.frame4.frame6 staticLink;
            
            public frame7() {
              ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
              moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
              this.lambda$Fn12 = moduleMethod;
            }
            
            public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
              return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
            }
            
            Object lambda16(Object param6Object1, Object param6Object2) {
              ApplyToArgs applyToArgs = Scheme.applyToArgs;
              Object object1 = this.staticLink.cont;
              Char char_ = printf.Lit5;
              Object object2 = this.sgn;
              try {
                Char char_1 = (Char)object2;
                if (characters.isChar$Eq(char_, char_1)) {
                  object2 = AddOp.$Mn;
                  try {
                    CharSequence charSequence = (CharSequence)param6Object2;
                    param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                    return applyToArgs.apply3(object1, param6Object1, param6Object2);
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "string->number", 1, param6Object2);
                  } 
                } 
                try {
                  object2 = param6Object2;
                  param6Object2 = numbers.string$To$Number((CharSequence)object2);
                  return applyToArgs.apply3(object1, classCastException, param6Object2);
                } catch (ClassCastException classCastException1) {
                  throw new WrongType(classCastException1, "string->number", 1, param6Object2);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "char=?", 2, object2);
              } 
            }
            
            public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
              if (param6ModuleMethod.selector == 4) {
                param6CallContext.value1 = param6Object1;
                param6CallContext.value2 = param6Object2;
                param6CallContext.proc = (Procedure)param6ModuleMethod;
                param6CallContext.pc = 2;
                return 0;
              } 
              return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
            }
          }
        }
        
        public class frame7 extends ModuleBody {
          final ModuleMethod lambda$Fn12;
          
          Object sgn;
          
          printf.frame.frame2.frame3.frame4.frame6 staticLink;
          
          public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
            return (param5ModuleMethod.selector == 4) ? lambda16(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
          }
          
          Object lambda16(Object param5Object1, Object param5Object2) {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object object1 = this.staticLink.cont;
            Char char_ = printf.Lit5;
            Object object2 = this.sgn;
            try {
              Char char_1 = (Char)object2;
              if (characters.isChar$Eq(char_, char_1)) {
                object2 = AddOp.$Mn;
                try {
                  CharSequence charSequence = (CharSequence)param5Object2;
                  param5Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                  return applyToArgs.apply3(object1, param5Object1, param5Object2);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string->number", 1, param5Object2);
                } 
              } 
              try {
                object2 = param5Object2;
                param5Object2 = numbers.string$To$Number((CharSequence)object2);
                return applyToArgs.apply3(object1, classCastException, param5Object2);
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string->number", 1, param5Object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "char=?", 2, object2);
            } 
          }
          
          public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 4) {
              param5CallContext.value1 = param5Object1;
              param5CallContext.value2 = param5Object2;
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
          }
        }
      }
      
      public class frame5 extends ModuleBody {
        Object fdigs;
        
        final ModuleMethod lambda$Fn10;
        
        printf.frame.frame2.frame3.frame4 staticLink;
        
        public frame5() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
          this.lambda$Fn10 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 6) ? lambda14(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda14(Object param4Object1, Object param4Object2) {
          FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
          int i = strings.stringLength((CharSequence)fString);
          IntNum intNum = printf.Lit7;
          AddOp addOp = AddOp.$Pl;
          Object object = this.staticLink.idigs;
          try {
            CharSequence charSequence = (CharSequence)object;
            param4Object2 = addOp.apply2(param4Object2, Integer.valueOf(strings.stringLength(charSequence)));
            while (true) {
              Object object1;
              if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
                return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param4Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
              object = printf.Lit9;
              try {
                int j = ((Number)intNum).intValue();
                if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                  object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                  param4Object2 = AddOp.$Mn.apply2(param4Object2, printf.Lit7);
                  continue;
                } 
                object = Scheme.applyToArgs;
                Object object2 = this.staticLink.staticLink.staticLink.cont;
                Object object3 = this.staticLink.staticLink.sgn;
                object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
                try {
                  j = ((Number)object1).intValue();
                  return object.applyN(new Object[] { object2, param4Object1, object3, strings.substring((CharSequence)fString, j, i), param4Object2 });
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "substring", 2, object1);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 2, object1);
              } 
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-length", 1, object);
          } 
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 6) {
            param4CallContext.value1 = param4Object1;
            param4CallContext.value2 = param4Object2;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
        }
      }
      
      public class frame6 extends ModuleBody {
        Object cont;
        
        final ModuleMethod lambda$Fn11;
        
        printf.frame staticLink;
        
        public frame6() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
          this.lambda$Fn11 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 5) ? lambda15(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda15(Object param4Object1, Object param4Object2) {
          frame7 frame7 = new frame7();
          frame7.staticLink = this;
          frame7.sgn = param4Object2;
          return this.staticLink.lambda3digits(param4Object1, frame7.lambda$Fn12);
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 5) {
            param4CallContext.value1 = param4Object1;
            param4CallContext.value2 = param4Object2;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
        }
        
        public class frame7 extends ModuleBody {
          final ModuleMethod lambda$Fn12;
          
          Object sgn;
          
          printf.frame.frame2.frame3.frame4.frame6 staticLink;
          
          public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
            return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
          }
          
          Object lambda16(Object param6Object1, Object param6Object2) {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object object1 = this.staticLink.cont;
            Char char_ = printf.Lit5;
            Object object2 = this.sgn;
            try {
              Char char_1 = (Char)object2;
              if (characters.isChar$Eq(char_, char_1)) {
                object2 = AddOp.$Mn;
                try {
                  CharSequence charSequence = (CharSequence)param6Object2;
                  param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                  return applyToArgs.apply3(object1, param6Object1, param6Object2);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string->number", 1, param6Object2);
                } 
              } 
              try {
                object2 = param6Object2;
                param6Object2 = numbers.string$To$Number((CharSequence)object2);
                return applyToArgs.apply3(object1, classCastException, param6Object2);
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string->number", 1, param6Object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "char=?", 2, object2);
            } 
          }
          
          public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
            if (param6ModuleMethod.selector == 4) {
              param6CallContext.value1 = param6Object1;
              param6CallContext.value2 = param6Object2;
              param6CallContext.proc = (Procedure)param6ModuleMethod;
              param6CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
          }
        }
      }
      
      public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        
        Object sgn;
        
        printf.frame.frame2.frame3.frame4.frame6 staticLink;
        
        public frame7() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
          this.lambda$Fn12 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 4) ? lambda16(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda16(Object param4Object1, Object param4Object2) {
          ApplyToArgs applyToArgs = Scheme.applyToArgs;
          Object object1 = this.staticLink.cont;
          Char char_ = printf.Lit5;
          Object object2 = this.sgn;
          try {
            Char char_1 = (Char)object2;
            if (characters.isChar$Eq(char_, char_1)) {
              object2 = AddOp.$Mn;
              try {
                CharSequence charSequence = (CharSequence)param4Object2;
                param4Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                return applyToArgs.apply3(object1, param4Object1, param4Object2);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string->number", 1, param4Object2);
              } 
            } 
            try {
              object2 = param4Object2;
              param4Object2 = numbers.string$To$Number((CharSequence)object2);
              return applyToArgs.apply3(object1, classCastException, param4Object2);
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string->number", 1, param4Object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char=?", 2, object2);
          } 
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 4) {
            param4CallContext.value1 = param4Object1;
            param4CallContext.value2 = param4Object2;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
        }
      }
    }
    
    public class frame4 extends ModuleBody {
      Object idigs;
      
      final ModuleMethod lambda$Fn8;
      
      final ModuleMethod lambda$Fn9;
      
      printf.frame.frame2.frame3 staticLink;
      
      public frame4() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 7, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:90");
        this.lambda$Fn9 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 8, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:87");
        this.lambda$Fn8 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
        return (param3ModuleMethod.selector == 8) ? lambda12(param3Object) : super.apply1(param3ModuleMethod, param3Object);
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 7) ? lambda13(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda12(Object param3Object) {
        return this.staticLink.staticLink.staticLink.lambda3digits(param3Object, this.lambda$Fn9);
      }
      
      Object lambda13(Object param3Object1, Object param3Object2) {
        frame5 frame5 = new frame5();
        frame5.staticLink = this;
        frame5.fdigs = param3Object2;
        ModuleMethod moduleMethod = frame5.lambda$Fn10;
        printf.frame frame = this.staticLink.staticLink.staticLink;
        param3Object2 = new frame6();
        ((frame6)param3Object2).staticLink = frame;
        ((frame6)param3Object2).cont = moduleMethod;
        if (Scheme.numGEq.apply2(param3Object1, Integer.valueOf(this.staticLink.staticLink.staticLink.n)) != Boolean.FALSE)
          return Scheme.applyToArgs.apply3(((frame6)param3Object2).cont, param3Object1, printf.Lit1); 
        Object object = this.staticLink.staticLink.staticLink.str;
        try {
          CharSequence charSequence = (CharSequence)object;
          try {
            int i = ((Number)param3Object1).intValue();
            return (lists.memv(Char.make(strings.stringRef(charSequence, i)), printf.Lit10) != Boolean.FALSE) ? this.staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(param3Object1, printf.Lit7), ((frame6)param3Object2).lambda$Fn11) : Scheme.applyToArgs.apply3(((frame6)param3Object2).cont, param3Object1, printf.Lit1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, param3Object1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 1, object);
        } 
      }
      
      public int match1(ModuleMethod param3ModuleMethod, Object param3Object, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 8) {
          param3CallContext.value1 = param3Object;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param3ModuleMethod, param3Object, param3CallContext);
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 7) {
          param3CallContext.value1 = param3Object1;
          param3CallContext.value2 = param3Object2;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
      }
      
      public class frame5 extends ModuleBody {
        Object fdigs;
        
        final ModuleMethod lambda$Fn10;
        
        printf.frame.frame2.frame3.frame4 staticLink;
        
        public frame5() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
          this.lambda$Fn10 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
          return (param5ModuleMethod.selector == 6) ? lambda14(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
        }
        
        Object lambda14(Object param5Object1, Object param5Object2) {
          FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
          int i = strings.stringLength((CharSequence)fString);
          IntNum intNum = printf.Lit7;
          AddOp addOp = AddOp.$Pl;
          Object object = this.staticLink.idigs;
          try {
            CharSequence charSequence = (CharSequence)object;
            param5Object2 = addOp.apply2(param5Object2, Integer.valueOf(strings.stringLength(charSequence)));
            while (true) {
              Object object1;
              if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
                return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param5Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
              object = printf.Lit9;
              try {
                int j = ((Number)intNum).intValue();
                if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                  object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                  param5Object2 = AddOp.$Mn.apply2(param5Object2, printf.Lit7);
                  continue;
                } 
                object = Scheme.applyToArgs;
                Object object2 = this.staticLink.staticLink.staticLink.cont;
                Object object3 = this.staticLink.staticLink.sgn;
                object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
                try {
                  j = ((Number)object1).intValue();
                  return object.applyN(new Object[] { object2, param5Object1, object3, strings.substring((CharSequence)fString, j, i), param5Object2 });
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "substring", 2, object1);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 2, object1);
              } 
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-length", 1, object);
          } 
        }
        
        public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 6) {
            param5CallContext.value1 = param5Object1;
            param5CallContext.value2 = param5Object2;
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
        }
      }
      
      public class frame6 extends ModuleBody {
        Object cont;
        
        final ModuleMethod lambda$Fn11;
        
        printf.frame staticLink;
        
        public frame6() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
          this.lambda$Fn11 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
          return (param5ModuleMethod.selector == 5) ? lambda15(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
        }
        
        Object lambda15(Object param5Object1, Object param5Object2) {
          frame7 frame7 = new frame7();
          frame7.staticLink = this;
          frame7.sgn = param5Object2;
          return this.staticLink.lambda3digits(param5Object1, frame7.lambda$Fn12);
        }
        
        public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 5) {
            param5CallContext.value1 = param5Object1;
            param5CallContext.value2 = param5Object2;
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
        }
        
        public class frame7 extends ModuleBody {
          final ModuleMethod lambda$Fn12;
          
          Object sgn;
          
          printf.frame.frame2.frame3.frame4.frame6 staticLink;
          
          public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
            return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
          }
          
          Object lambda16(Object param6Object1, Object param6Object2) {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object object1 = this.staticLink.cont;
            Char char_ = printf.Lit5;
            Object object2 = this.sgn;
            try {
              Char char_1 = (Char)object2;
              if (characters.isChar$Eq(char_, char_1)) {
                object2 = AddOp.$Mn;
                try {
                  CharSequence charSequence = (CharSequence)param6Object2;
                  param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                  return applyToArgs.apply3(object1, param6Object1, param6Object2);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string->number", 1, param6Object2);
                } 
              } 
              try {
                object2 = param6Object2;
                param6Object2 = numbers.string$To$Number((CharSequence)object2);
                return applyToArgs.apply3(object1, classCastException, param6Object2);
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string->number", 1, param6Object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "char=?", 2, object2);
            } 
          }
          
          public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
            if (param6ModuleMethod.selector == 4) {
              param6CallContext.value1 = param6Object1;
              param6CallContext.value2 = param6Object2;
              param6CallContext.proc = (Procedure)param6ModuleMethod;
              param6CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
          }
        }
      }
      
      public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        
        Object sgn;
        
        printf.frame.frame2.frame3.frame4.frame6 staticLink;
        
        public frame7() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
          this.lambda$Fn12 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
          return (param5ModuleMethod.selector == 4) ? lambda16(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
        }
        
        Object lambda16(Object param5Object1, Object param5Object2) {
          ApplyToArgs applyToArgs = Scheme.applyToArgs;
          Object object1 = this.staticLink.cont;
          Char char_ = printf.Lit5;
          Object object2 = this.sgn;
          try {
            Char char_1 = (Char)object2;
            if (characters.isChar$Eq(char_, char_1)) {
              object2 = AddOp.$Mn;
              try {
                CharSequence charSequence = (CharSequence)param5Object2;
                param5Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                return applyToArgs.apply3(object1, param5Object1, param5Object2);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string->number", 1, param5Object2);
              } 
            } 
            try {
              object2 = param5Object2;
              param5Object2 = numbers.string$To$Number((CharSequence)object2);
              return applyToArgs.apply3(object1, classCastException, param5Object2);
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string->number", 1, param5Object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char=?", 2, object2);
          } 
        }
        
        public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 4) {
            param5CallContext.value1 = param5Object1;
            param5CallContext.value2 = param5Object2;
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
        }
      }
    }
    
    public class frame5 extends ModuleBody {
      Object fdigs;
      
      final ModuleMethod lambda$Fn10;
      
      printf.frame.frame2.frame3.frame4 staticLink;
      
      public frame5() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
        this.lambda$Fn10 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 6) ? lambda14(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda14(Object param3Object1, Object param3Object2) {
        FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
        int i = strings.stringLength((CharSequence)fString);
        IntNum intNum = printf.Lit7;
        AddOp addOp = AddOp.$Pl;
        Object object = this.staticLink.idigs;
        try {
          CharSequence charSequence = (CharSequence)object;
          param3Object2 = addOp.apply2(param3Object2, Integer.valueOf(strings.stringLength(charSequence)));
          while (true) {
            Object object1;
            if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
              return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param3Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
            object = printf.Lit9;
            try {
              int j = ((Number)intNum).intValue();
              if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                param3Object2 = AddOp.$Mn.apply2(param3Object2, printf.Lit7);
                continue;
              } 
              object = Scheme.applyToArgs;
              Object object2 = this.staticLink.staticLink.staticLink.cont;
              Object object3 = this.staticLink.staticLink.sgn;
              object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
              try {
                j = ((Number)object1).intValue();
                return object.applyN(new Object[] { object2, param3Object1, object3, strings.substring((CharSequence)fString, j, i), param3Object2 });
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "substring", 2, object1);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 2, object1);
            } 
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-length", 1, object);
        } 
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 6) {
          param3CallContext.value1 = param3Object1;
          param3CallContext.value2 = param3Object2;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
      }
    }
    
    public class frame6 extends ModuleBody {
      Object cont;
      
      final ModuleMethod lambda$Fn11;
      
      printf.frame staticLink;
      
      public frame6() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
        this.lambda$Fn11 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 5) ? lambda15(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda15(Object param3Object1, Object param3Object2) {
        frame7 frame7 = new frame7();
        frame7.staticLink = this;
        frame7.sgn = param3Object2;
        return this.staticLink.lambda3digits(param3Object1, frame7.lambda$Fn12);
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 5) {
          param3CallContext.value1 = param3Object1;
          param3CallContext.value2 = param3Object2;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
      }
      
      public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        
        Object sgn;
        
        printf.frame.frame2.frame3.frame4.frame6 staticLink;
        
        public frame7() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
          this.lambda$Fn12 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
          return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
        }
        
        Object lambda16(Object param6Object1, Object param6Object2) {
          ApplyToArgs applyToArgs = Scheme.applyToArgs;
          Object object1 = this.staticLink.cont;
          Char char_ = printf.Lit5;
          Object object2 = this.sgn;
          try {
            Char char_1 = (Char)object2;
            if (characters.isChar$Eq(char_, char_1)) {
              object2 = AddOp.$Mn;
              try {
                CharSequence charSequence = (CharSequence)param6Object2;
                param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                return applyToArgs.apply3(object1, param6Object1, param6Object2);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string->number", 1, param6Object2);
              } 
            } 
            try {
              object2 = param6Object2;
              param6Object2 = numbers.string$To$Number((CharSequence)object2);
              return applyToArgs.apply3(object1, classCastException, param6Object2);
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string->number", 1, param6Object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char=?", 2, object2);
          } 
        }
        
        public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
          if (param6ModuleMethod.selector == 4) {
            param6CallContext.value1 = param6Object1;
            param6CallContext.value2 = param6Object2;
            param6CallContext.proc = (Procedure)param6ModuleMethod;
            param6CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
        }
      }
    }
    
    public class frame7 extends ModuleBody {
      final ModuleMethod lambda$Fn12;
      
      Object sgn;
      
      printf.frame.frame2.frame3.frame4.frame6 staticLink;
      
      public frame7() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
        this.lambda$Fn12 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 4) ? lambda16(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda16(Object param3Object1, Object param3Object2) {
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Object object1 = this.staticLink.cont;
        Char char_ = printf.Lit5;
        Object object2 = this.sgn;
        try {
          Char char_1 = (Char)object2;
          if (characters.isChar$Eq(char_, char_1)) {
            object2 = AddOp.$Mn;
            try {
              CharSequence charSequence = (CharSequence)param3Object2;
              param3Object2 = object2.apply1(numbers.string$To$Number(charSequence));
              return applyToArgs.apply3(object1, param3Object1, param3Object2);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string->number", 1, param3Object2);
            } 
          } 
          try {
            object2 = param3Object2;
            param3Object2 = numbers.string$To$Number((CharSequence)object2);
            return applyToArgs.apply3(object1, classCastException, param3Object2);
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string->number", 1, param3Object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char=?", 2, object2);
        } 
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 4) {
          param3CallContext.value1 = param3Object1;
          param3CallContext.value2 = param3Object2;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
      }
    }
  }
  
  public class frame3 extends ModuleBody {
    final ModuleMethod lambda$Fn7;
    
    Object sgn;
    
    printf.frame.frame2 staticLink;
    
    public frame3() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 9, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:84");
      this.lambda$Fn7 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 9) ? lambda11(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda11(Object param1Object1, Object param1Object2) {
      frame4 frame4 = new frame4();
      frame4.staticLink = this;
      frame4.idigs = param1Object2;
      param1Object2 = frame4.lambda$Fn8;
      Object object = Scheme.numLss.apply2(param1Object1, Integer.valueOf(this.staticLink.staticLink.n));
      try {
        boolean bool = ((Boolean)object).booleanValue();
        if (bool) {
          Char char_ = printf.Lit11;
          object = this.staticLink.staticLink.str;
          try {
            CharSequence charSequence = (CharSequence)object;
            try {
              int i = ((Number)param1Object1).intValue();
              return characters.isChar$Eq(char_, Char.make(strings.stringRef(charSequence, i))) ? Scheme.applyToArgs.apply2(param1Object2, AddOp.$Pl.apply2(param1Object1, printf.Lit7)) : Scheme.applyToArgs.apply2(param1Object2, param1Object1);
            } catch (ClassCastException classCastException2) {
              throw new WrongType(classCastException2, "string-ref", 2, param1Object1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-ref", 1, object);
          } 
        } 
        return bool ? Scheme.applyToArgs.apply2(classCastException2, AddOp.$Pl.apply2(classCastException1, printf.Lit7)) : Scheme.applyToArgs.apply2(classCastException2, classCastException1);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "x", -2, object);
      } 
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 9) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame4 extends ModuleBody {
      Object idigs;
      
      final ModuleMethod lambda$Fn8;
      
      final ModuleMethod lambda$Fn9;
      
      printf.frame.frame2.frame3 staticLink;
      
      public frame4() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 7, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:90");
        this.lambda$Fn9 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 8, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:87");
        this.lambda$Fn8 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param4ModuleMethod, Object param4Object) {
        return (param4ModuleMethod.selector == 8) ? lambda12(param4Object) : super.apply1(param4ModuleMethod, param4Object);
      }
      
      public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
        return (param4ModuleMethod.selector == 7) ? lambda13(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
      }
      
      Object lambda12(Object param4Object) {
        return this.staticLink.staticLink.staticLink.lambda3digits(param4Object, this.lambda$Fn9);
      }
      
      Object lambda13(Object param4Object1, Object param4Object2) {
        frame5 frame5 = new frame5();
        frame5.staticLink = this;
        frame5.fdigs = param4Object2;
        ModuleMethod moduleMethod = frame5.lambda$Fn10;
        printf.frame frame = this.staticLink.staticLink.staticLink;
        param4Object2 = new frame6();
        ((frame6)param4Object2).staticLink = frame;
        ((frame6)param4Object2).cont = moduleMethod;
        if (Scheme.numGEq.apply2(param4Object1, Integer.valueOf(this.staticLink.staticLink.staticLink.n)) != Boolean.FALSE)
          return Scheme.applyToArgs.apply3(((frame6)param4Object2).cont, param4Object1, printf.Lit1); 
        Object object = this.staticLink.staticLink.staticLink.str;
        try {
          CharSequence charSequence = (CharSequence)object;
          try {
            int i = ((Number)param4Object1).intValue();
            return (lists.memv(Char.make(strings.stringRef(charSequence, i)), printf.Lit10) != Boolean.FALSE) ? this.staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(param4Object1, printf.Lit7), ((frame6)param4Object2).lambda$Fn11) : Scheme.applyToArgs.apply3(((frame6)param4Object2).cont, param4Object1, printf.Lit1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, param4Object1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 1, object);
        } 
      }
      
      public int match1(ModuleMethod param4ModuleMethod, Object param4Object, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 8) {
          param4CallContext.value1 = param4Object;
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param4ModuleMethod, param4Object, param4CallContext);
      }
      
      public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 7) {
          param4CallContext.value1 = param4Object1;
          param4CallContext.value2 = param4Object2;
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
      }
      
      public class frame5 extends ModuleBody {
        Object fdigs;
        
        final ModuleMethod lambda$Fn10;
        
        printf.frame.frame2.frame3.frame4 staticLink;
        
        public frame5() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
          this.lambda$Fn10 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
          return (param5ModuleMethod.selector == 6) ? lambda14(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
        }
        
        Object lambda14(Object param5Object1, Object param5Object2) {
          FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
          int i = strings.stringLength((CharSequence)fString);
          IntNum intNum = printf.Lit7;
          AddOp addOp = AddOp.$Pl;
          Object object = this.staticLink.idigs;
          try {
            CharSequence charSequence = (CharSequence)object;
            param5Object2 = addOp.apply2(param5Object2, Integer.valueOf(strings.stringLength(charSequence)));
            while (true) {
              Object object1;
              if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
                return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param5Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
              object = printf.Lit9;
              try {
                int j = ((Number)intNum).intValue();
                if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                  object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                  param5Object2 = AddOp.$Mn.apply2(param5Object2, printf.Lit7);
                  continue;
                } 
                object = Scheme.applyToArgs;
                Object object2 = this.staticLink.staticLink.staticLink.cont;
                Object object3 = this.staticLink.staticLink.sgn;
                object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
                try {
                  j = ((Number)object1).intValue();
                  return object.applyN(new Object[] { object2, param5Object1, object3, strings.substring((CharSequence)fString, j, i), param5Object2 });
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "substring", 2, object1);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 2, object1);
              } 
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-length", 1, object);
          } 
        }
        
        public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 6) {
            param5CallContext.value1 = param5Object1;
            param5CallContext.value2 = param5Object2;
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
        }
      }
      
      public class frame6 extends ModuleBody {
        Object cont;
        
        final ModuleMethod lambda$Fn11;
        
        printf.frame staticLink;
        
        public frame6() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
          this.lambda$Fn11 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
          return (param5ModuleMethod.selector == 5) ? lambda15(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
        }
        
        Object lambda15(Object param5Object1, Object param5Object2) {
          frame7 frame7 = new frame7();
          frame7.staticLink = this;
          frame7.sgn = param5Object2;
          return this.staticLink.lambda3digits(param5Object1, frame7.lambda$Fn12);
        }
        
        public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 5) {
            param5CallContext.value1 = param5Object1;
            param5CallContext.value2 = param5Object2;
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
        }
        
        public class frame7 extends ModuleBody {
          final ModuleMethod lambda$Fn12;
          
          Object sgn;
          
          printf.frame.frame2.frame3.frame4.frame6 staticLink;
          
          public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod;
          }
          
          public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
            return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
          }
          
          Object lambda16(Object param6Object1, Object param6Object2) {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object object1 = this.staticLink.cont;
            Char char_ = printf.Lit5;
            Object object2 = this.sgn;
            try {
              Char char_1 = (Char)object2;
              if (characters.isChar$Eq(char_, char_1)) {
                object2 = AddOp.$Mn;
                try {
                  CharSequence charSequence = (CharSequence)param6Object2;
                  param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                  return applyToArgs.apply3(object1, param6Object1, param6Object2);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string->number", 1, param6Object2);
                } 
              } 
              try {
                object2 = param6Object2;
                param6Object2 = numbers.string$To$Number((CharSequence)object2);
                return applyToArgs.apply3(object1, classCastException, param6Object2);
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string->number", 1, param6Object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "char=?", 2, object2);
            } 
          }
          
          public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
            if (param6ModuleMethod.selector == 4) {
              param6CallContext.value1 = param6Object1;
              param6CallContext.value2 = param6Object2;
              param6CallContext.proc = (Procedure)param6ModuleMethod;
              param6CallContext.pc = 2;
              return 0;
            } 
            return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
          }
        }
      }
      
      public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        
        Object sgn;
        
        printf.frame.frame2.frame3.frame4.frame6 staticLink;
        
        public frame7() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
          this.lambda$Fn12 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
          return (param5ModuleMethod.selector == 4) ? lambda16(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
        }
        
        Object lambda16(Object param5Object1, Object param5Object2) {
          ApplyToArgs applyToArgs = Scheme.applyToArgs;
          Object object1 = this.staticLink.cont;
          Char char_ = printf.Lit5;
          Object object2 = this.sgn;
          try {
            Char char_1 = (Char)object2;
            if (characters.isChar$Eq(char_, char_1)) {
              object2 = AddOp.$Mn;
              try {
                CharSequence charSequence = (CharSequence)param5Object2;
                param5Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                return applyToArgs.apply3(object1, param5Object1, param5Object2);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string->number", 1, param5Object2);
              } 
            } 
            try {
              object2 = param5Object2;
              param5Object2 = numbers.string$To$Number((CharSequence)object2);
              return applyToArgs.apply3(object1, classCastException, param5Object2);
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string->number", 1, param5Object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char=?", 2, object2);
          } 
        }
        
        public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 4) {
            param5CallContext.value1 = param5Object1;
            param5CallContext.value2 = param5Object2;
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
        }
      }
    }
    
    public class frame5 extends ModuleBody {
      Object fdigs;
      
      final ModuleMethod lambda$Fn10;
      
      printf.frame.frame2.frame3.frame4 staticLink;
      
      public frame5() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
        this.lambda$Fn10 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
        return (param4ModuleMethod.selector == 6) ? lambda14(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
      }
      
      Object lambda14(Object param4Object1, Object param4Object2) {
        FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
        int i = strings.stringLength((CharSequence)fString);
        IntNum intNum = printf.Lit7;
        AddOp addOp = AddOp.$Pl;
        Object object = this.staticLink.idigs;
        try {
          CharSequence charSequence = (CharSequence)object;
          param4Object2 = addOp.apply2(param4Object2, Integer.valueOf(strings.stringLength(charSequence)));
          while (true) {
            Object object1;
            if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
              return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param4Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
            object = printf.Lit9;
            try {
              int j = ((Number)intNum).intValue();
              if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                param4Object2 = AddOp.$Mn.apply2(param4Object2, printf.Lit7);
                continue;
              } 
              object = Scheme.applyToArgs;
              Object object2 = this.staticLink.staticLink.staticLink.cont;
              Object object3 = this.staticLink.staticLink.sgn;
              object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
              try {
                j = ((Number)object1).intValue();
                return object.applyN(new Object[] { object2, param4Object1, object3, strings.substring((CharSequence)fString, j, i), param4Object2 });
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "substring", 2, object1);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 2, object1);
            } 
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-length", 1, object);
        } 
      }
      
      public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 6) {
          param4CallContext.value1 = param4Object1;
          param4CallContext.value2 = param4Object2;
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
      }
    }
    
    public class frame6 extends ModuleBody {
      Object cont;
      
      final ModuleMethod lambda$Fn11;
      
      printf.frame staticLink;
      
      public frame6() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
        this.lambda$Fn11 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
        return (param4ModuleMethod.selector == 5) ? lambda15(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
      }
      
      Object lambda15(Object param4Object1, Object param4Object2) {
        frame7 frame7 = new frame7();
        frame7.staticLink = this;
        frame7.sgn = param4Object2;
        return this.staticLink.lambda3digits(param4Object1, frame7.lambda$Fn12);
      }
      
      public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 5) {
          param4CallContext.value1 = param4Object1;
          param4CallContext.value2 = param4Object2;
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
      }
      
      public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        
        Object sgn;
        
        printf.frame.frame2.frame3.frame4.frame6 staticLink;
        
        public frame7() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
          this.lambda$Fn12 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
          return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
        }
        
        Object lambda16(Object param6Object1, Object param6Object2) {
          ApplyToArgs applyToArgs = Scheme.applyToArgs;
          Object object1 = this.staticLink.cont;
          Char char_ = printf.Lit5;
          Object object2 = this.sgn;
          try {
            Char char_1 = (Char)object2;
            if (characters.isChar$Eq(char_, char_1)) {
              object2 = AddOp.$Mn;
              try {
                CharSequence charSequence = (CharSequence)param6Object2;
                param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                return applyToArgs.apply3(object1, param6Object1, param6Object2);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string->number", 1, param6Object2);
              } 
            } 
            try {
              object2 = param6Object2;
              param6Object2 = numbers.string$To$Number((CharSequence)object2);
              return applyToArgs.apply3(object1, classCastException, param6Object2);
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string->number", 1, param6Object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char=?", 2, object2);
          } 
        }
        
        public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
          if (param6ModuleMethod.selector == 4) {
            param6CallContext.value1 = param6Object1;
            param6CallContext.value2 = param6Object2;
            param6CallContext.proc = (Procedure)param6ModuleMethod;
            param6CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
        }
      }
    }
    
    public class frame7 extends ModuleBody {
      final ModuleMethod lambda$Fn12;
      
      Object sgn;
      
      printf.frame.frame2.frame3.frame4.frame6 staticLink;
      
      public frame7() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
        this.lambda$Fn12 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
        return (param4ModuleMethod.selector == 4) ? lambda16(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
      }
      
      Object lambda16(Object param4Object1, Object param4Object2) {
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Object object1 = this.staticLink.cont;
        Char char_ = printf.Lit5;
        Object object2 = this.sgn;
        try {
          Char char_1 = (Char)object2;
          if (characters.isChar$Eq(char_, char_1)) {
            object2 = AddOp.$Mn;
            try {
              CharSequence charSequence = (CharSequence)param4Object2;
              param4Object2 = object2.apply1(numbers.string$To$Number(charSequence));
              return applyToArgs.apply3(object1, param4Object1, param4Object2);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string->number", 1, param4Object2);
            } 
          } 
          try {
            object2 = param4Object2;
            param4Object2 = numbers.string$To$Number((CharSequence)object2);
            return applyToArgs.apply3(object1, classCastException, param4Object2);
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string->number", 1, param4Object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char=?", 2, object2);
        } 
      }
      
      public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 4) {
          param4CallContext.value1 = param4Object1;
          param4CallContext.value2 = param4Object2;
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param4ModuleMethod, param4Object1, param4Object2, param4CallContext);
      }
    }
  }
  
  public class frame4 extends ModuleBody {
    Object idigs;
    
    final ModuleMethod lambda$Fn8;
    
    final ModuleMethod lambda$Fn9;
    
    printf.frame.frame2.frame3 staticLink;
    
    public frame4() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 7, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:90");
      this.lambda$Fn9 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 8, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:87");
      this.lambda$Fn8 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 8) ? lambda12(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 7) ? lambda13(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda12(Object param1Object) {
      return this.staticLink.staticLink.staticLink.lambda3digits(param1Object, this.lambda$Fn9);
    }
    
    Object lambda13(Object param1Object1, Object param1Object2) {
      frame5 frame5 = new frame5();
      frame5.staticLink = this;
      frame5.fdigs = param1Object2;
      ModuleMethod moduleMethod = frame5.lambda$Fn10;
      printf.frame frame = this.staticLink.staticLink.staticLink;
      param1Object2 = new frame6();
      ((frame6)param1Object2).staticLink = frame;
      ((frame6)param1Object2).cont = moduleMethod;
      if (Scheme.numGEq.apply2(param1Object1, Integer.valueOf(this.staticLink.staticLink.staticLink.n)) != Boolean.FALSE)
        return Scheme.applyToArgs.apply3(((frame6)param1Object2).cont, param1Object1, printf.Lit1); 
      Object object = this.staticLink.staticLink.staticLink.str;
      try {
        CharSequence charSequence = (CharSequence)object;
        try {
          int i = ((Number)param1Object1).intValue();
          return (lists.memv(Char.make(strings.stringRef(charSequence, i)), printf.Lit10) != Boolean.FALSE) ? this.staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(param1Object1, printf.Lit7), ((frame6)param1Object2).lambda$Fn11) : Scheme.applyToArgs.apply3(((frame6)param1Object2).cont, param1Object1, printf.Lit1);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 2, param1Object1);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-ref", 1, object);
      } 
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 8) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 7) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame5 extends ModuleBody {
      Object fdigs;
      
      final ModuleMethod lambda$Fn10;
      
      printf.frame.frame2.frame3.frame4 staticLink;
      
      public frame5() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
        this.lambda$Fn10 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
        return (param5ModuleMethod.selector == 6) ? lambda14(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
      }
      
      Object lambda14(Object param5Object1, Object param5Object2) {
        FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
        int i = strings.stringLength((CharSequence)fString);
        IntNum intNum = printf.Lit7;
        AddOp addOp = AddOp.$Pl;
        Object object = this.staticLink.idigs;
        try {
          CharSequence charSequence = (CharSequence)object;
          param5Object2 = addOp.apply2(param5Object2, Integer.valueOf(strings.stringLength(charSequence)));
          while (true) {
            Object object1;
            if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
              return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param5Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
            object = printf.Lit9;
            try {
              int j = ((Number)intNum).intValue();
              if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
                object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
                param5Object2 = AddOp.$Mn.apply2(param5Object2, printf.Lit7);
                continue;
              } 
              object = Scheme.applyToArgs;
              Object object2 = this.staticLink.staticLink.staticLink.cont;
              Object object3 = this.staticLink.staticLink.sgn;
              object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
              try {
                j = ((Number)object1).intValue();
                return object.applyN(new Object[] { object2, param5Object1, object3, strings.substring((CharSequence)fString, j, i), param5Object2 });
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "substring", 2, object1);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 2, object1);
            } 
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-length", 1, object);
        } 
      }
      
      public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
        if (param5ModuleMethod.selector == 6) {
          param5CallContext.value1 = param5Object1;
          param5CallContext.value2 = param5Object2;
          param5CallContext.proc = (Procedure)param5ModuleMethod;
          param5CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
      }
    }
    
    public class frame6 extends ModuleBody {
      Object cont;
      
      final ModuleMethod lambda$Fn11;
      
      printf.frame staticLink;
      
      public frame6() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
        this.lambda$Fn11 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
        return (param5ModuleMethod.selector == 5) ? lambda15(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
      }
      
      Object lambda15(Object param5Object1, Object param5Object2) {
        frame7 frame7 = new frame7();
        frame7.staticLink = this;
        frame7.sgn = param5Object2;
        return this.staticLink.lambda3digits(param5Object1, frame7.lambda$Fn12);
      }
      
      public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
        if (param5ModuleMethod.selector == 5) {
          param5CallContext.value1 = param5Object1;
          param5CallContext.value2 = param5Object2;
          param5CallContext.proc = (Procedure)param5ModuleMethod;
          param5CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
      }
      
      public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        
        Object sgn;
        
        printf.frame.frame2.frame3.frame4.frame6 staticLink;
        
        public frame7() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
          this.lambda$Fn12 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
          return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
        }
        
        Object lambda16(Object param6Object1, Object param6Object2) {
          ApplyToArgs applyToArgs = Scheme.applyToArgs;
          Object object1 = this.staticLink.cont;
          Char char_ = printf.Lit5;
          Object object2 = this.sgn;
          try {
            Char char_1 = (Char)object2;
            if (characters.isChar$Eq(char_, char_1)) {
              object2 = AddOp.$Mn;
              try {
                CharSequence charSequence = (CharSequence)param6Object2;
                param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
                return applyToArgs.apply3(object1, param6Object1, param6Object2);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string->number", 1, param6Object2);
              } 
            } 
            try {
              object2 = param6Object2;
              param6Object2 = numbers.string$To$Number((CharSequence)object2);
              return applyToArgs.apply3(object1, classCastException, param6Object2);
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string->number", 1, param6Object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char=?", 2, object2);
          } 
        }
        
        public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
          if (param6ModuleMethod.selector == 4) {
            param6CallContext.value1 = param6Object1;
            param6CallContext.value2 = param6Object2;
            param6CallContext.proc = (Procedure)param6ModuleMethod;
            param6CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
        }
      }
    }
    
    public class frame7 extends ModuleBody {
      final ModuleMethod lambda$Fn12;
      
      Object sgn;
      
      printf.frame.frame2.frame3.frame4.frame6 staticLink;
      
      public frame7() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
        this.lambda$Fn12 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2) {
        return (param5ModuleMethod.selector == 4) ? lambda16(param5Object1, param5Object2) : super.apply2(param5ModuleMethod, param5Object1, param5Object2);
      }
      
      Object lambda16(Object param5Object1, Object param5Object2) {
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Object object1 = this.staticLink.cont;
        Char char_ = printf.Lit5;
        Object object2 = this.sgn;
        try {
          Char char_1 = (Char)object2;
          if (characters.isChar$Eq(char_, char_1)) {
            object2 = AddOp.$Mn;
            try {
              CharSequence charSequence = (CharSequence)param5Object2;
              param5Object2 = object2.apply1(numbers.string$To$Number(charSequence));
              return applyToArgs.apply3(object1, param5Object1, param5Object2);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string->number", 1, param5Object2);
            } 
          } 
          try {
            object2 = param5Object2;
            param5Object2 = numbers.string$To$Number((CharSequence)object2);
            return applyToArgs.apply3(object1, classCastException, param5Object2);
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string->number", 1, param5Object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char=?", 2, object2);
        } 
      }
      
      public int match2(ModuleMethod param5ModuleMethod, Object param5Object1, Object param5Object2, CallContext param5CallContext) {
        if (param5ModuleMethod.selector == 4) {
          param5CallContext.value1 = param5Object1;
          param5CallContext.value2 = param5Object2;
          param5CallContext.proc = (Procedure)param5ModuleMethod;
          param5CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param5ModuleMethod, param5Object1, param5Object2, param5CallContext);
      }
    }
  }
  
  public class frame5 extends ModuleBody {
    Object fdigs;
    
    final ModuleMethod lambda$Fn10;
    
    printf.frame.frame2.frame3.frame4 staticLink;
    
    public frame5() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
      this.lambda$Fn10 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 6) ? lambda14(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda14(Object param1Object1, Object param1Object2) {
      FString fString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
      int i = strings.stringLength((CharSequence)fString);
      IntNum intNum = printf.Lit7;
      AddOp addOp = AddOp.$Pl;
      Object object = this.staticLink.idigs;
      try {
        CharSequence charSequence = (CharSequence)object;
        param1Object2 = addOp.apply2(param1Object2, Integer.valueOf(strings.stringLength(charSequence)));
        while (true) {
          Object object1;
          if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
            return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, param1Object1, this.staticLink.staticLink.sgn, "0", printf.Lit7 }); 
          object = printf.Lit9;
          try {
            int j = ((Number)intNum).intValue();
            if (characters.isChar$Eq((Char)object, Char.make(strings.stringRef((CharSequence)fString, j)))) {
              object1 = AddOp.$Pl.apply2(intNum, printf.Lit7);
              param1Object2 = AddOp.$Mn.apply2(param1Object2, printf.Lit7);
              continue;
            } 
            object = Scheme.applyToArgs;
            Object object2 = this.staticLink.staticLink.staticLink.cont;
            Object object3 = this.staticLink.staticLink.sgn;
            object1 = AddOp.$Mn.apply2(object1, printf.Lit7);
            try {
              j = ((Number)object1).intValue();
              return object.applyN(new Object[] { object2, param1Object1, object3, strings.substring((CharSequence)fString, j, i), param1Object2 });
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "substring", 2, object1);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, object1);
          } 
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-length", 1, object);
      } 
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 6) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame6 extends ModuleBody {
    Object cont;
    
    final ModuleMethod lambda$Fn11;
    
    printf.frame staticLink;
    
    public frame6() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
      this.lambda$Fn11 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 5) ? lambda15(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda15(Object param1Object1, Object param1Object2) {
      frame7 frame7 = new frame7();
      frame7.staticLink = this;
      frame7.sgn = param1Object2;
      return this.staticLink.lambda3digits(param1Object1, frame7.lambda$Fn12);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 5) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame7 extends ModuleBody {
      final ModuleMethod lambda$Fn12;
      
      Object sgn;
      
      printf.frame.frame2.frame3.frame4.frame6 staticLink;
      
      public frame7() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
        this.lambda$Fn12 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2) {
        return (param6ModuleMethod.selector == 4) ? lambda16(param6Object1, param6Object2) : super.apply2(param6ModuleMethod, param6Object1, param6Object2);
      }
      
      Object lambda16(Object param6Object1, Object param6Object2) {
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Object object1 = this.staticLink.cont;
        Char char_ = printf.Lit5;
        Object object2 = this.sgn;
        try {
          Char char_1 = (Char)object2;
          if (characters.isChar$Eq(char_, char_1)) {
            object2 = AddOp.$Mn;
            try {
              CharSequence charSequence = (CharSequence)param6Object2;
              param6Object2 = object2.apply1(numbers.string$To$Number(charSequence));
              return applyToArgs.apply3(object1, param6Object1, param6Object2);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string->number", 1, param6Object2);
            } 
          } 
          try {
            object2 = param6Object2;
            param6Object2 = numbers.string$To$Number((CharSequence)object2);
            return applyToArgs.apply3(object1, classCastException, param6Object2);
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string->number", 1, param6Object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char=?", 2, object2);
        } 
      }
      
      public int match2(ModuleMethod param6ModuleMethod, Object param6Object1, Object param6Object2, CallContext param6CallContext) {
        if (param6ModuleMethod.selector == 4) {
          param6CallContext.value1 = param6Object1;
          param6CallContext.value2 = param6Object2;
          param6CallContext.proc = (Procedure)param6ModuleMethod;
          param6CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param6ModuleMethod, param6Object1, param6Object2, param6CallContext);
      }
    }
  }
  
  public class frame7 extends ModuleBody {
    final ModuleMethod lambda$Fn12;
    
    Object sgn;
    
    printf.frame.frame2.frame3.frame4.frame6 staticLink;
    
    public frame7() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
      this.lambda$Fn12 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 4) ? lambda16(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda16(Object param1Object1, Object param1Object2) {
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Object object1 = this.staticLink.cont;
      Char char_ = printf.Lit5;
      Object object2 = this.sgn;
      try {
        Char char_1 = (Char)object2;
        if (characters.isChar$Eq(char_, char_1)) {
          object2 = AddOp.$Mn;
          try {
            CharSequence charSequence = (CharSequence)param1Object2;
            param1Object2 = object2.apply1(numbers.string$To$Number(charSequence));
            return applyToArgs.apply3(object1, param1Object1, param1Object2);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string->number", 1, param1Object2);
          } 
        } 
        try {
          object2 = param1Object2;
          param1Object2 = numbers.string$To$Number((CharSequence)object2);
          return applyToArgs.apply3(object1, classCastException, param1Object2);
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "string->number", 1, param1Object2);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "char=?", 2, object2);
      } 
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 4) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame8 extends ModuleBody {
    CharSequence str;
    
    public Object lambda17dig(Object param1Object) {
      CharSequence charSequence = this.str;
      try {
        int i = ((Number)param1Object).intValue();
        i = strings.stringRef(charSequence, i);
        return unicode.isCharNumeric(Char.make(i)) ? numbers.string$To$Number(strings.$make$string$(new Object[] { Char.make(i) })) : printf.Lit1;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-ref", 2, param1Object);
      } 
    }
  }
  
  public class frame9 extends ModuleBody {
    LList args;
    
    Object fc;
    
    int fl;
    
    Object format$Mnstring;
    
    Object out;
    
    Object pos;
    
    public Object lambda18mustAdvance() {
      this.pos = AddOp.$Pl.apply2(printf.Lit7, this.pos);
      if (Scheme.numGEq.apply2(this.pos, Integer.valueOf(this.fl)) != Boolean.FALSE)
        return lambda20incomplete(); 
      Object object = this.format$Mnstring;
      try {
        CharSequence charSequence = (CharSequence)object;
        object = this.pos;
        try {
          int i = ((Number)object).intValue();
          this.fc = Char.make(strings.stringRef(charSequence, i));
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 2, object);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-ref", 1, object);
      } 
    }
    
    public boolean lambda19isEndOfFormat() {
      return ((Boolean)Scheme.numGEq.apply2(this.pos, Integer.valueOf(this.fl))).booleanValue();
    }
    
    public Object lambda20incomplete() {
      return misc.error$V(printf.Lit34, new Object[] { "conversion specification incomplete", this.format$Mnstring });
    }
    
    public Object lambda21out$St(Object param1Object) {
      Object object = param1Object;
      if (strings.isString(param1Object))
        return Scheme.applyToArgs.apply2(this.out, param1Object); 
      while (true) {
        boolean bool = lists.isNull(object);
        if (bool)
          return bool ? Boolean.TRUE : Boolean.FALSE; 
        param1Object = Scheme.applyToArgs.apply2(this.out, lists.car.apply1(object));
        if (param1Object != Boolean.FALSE) {
          object = lists.cdr.apply1(object);
          continue;
        } 
        return param1Object;
      } 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/printf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
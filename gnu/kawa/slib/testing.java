package gnu.kawa.slib;

import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.IsEqual;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Eval;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.parameters;
import kawa.lib.ports;
import kawa.lib.std_syntax;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.readchar;
import kawa.standard.syntax_case;

public class testing extends ModuleBody {
  public static final ModuleMethod $Pctest$Mnbegin;
  
  static final ModuleMethod $Pctest$Mnnull$Mncallback;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnapproximimate$Eq;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnas$Mnspecifier;
  
  public static final Macro $Prvt$$Pctest$Mncomp1body;
  
  public static final Macro $Prvt$$Pctest$Mncomp2body;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnend;
  
  public static final Macro $Prvt$$Pctest$Mnerror;
  
  public static final Macro $Prvt$$Pctest$Mnevaluate$Mnwith$Mncatch;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnmatch$Mnall;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnmatch$Mnany;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnmatch$Mnnth;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnon$Mntest$Mnbegin;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnon$Mntest$Mnend;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnreport$Mnresult;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist$Ex;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist$Ex;
  
  public static final ModuleMethod $Prvt$$Pctest$Mnshould$Mnexecute;
  
  public static final Macro $Prvt$test$Mngroup;
  
  public static final testing $instance;
  
  static final IntNum Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final PairWithPosition Lit10;
  
  static final SyntaxPattern Lit100;
  
  static final SyntaxTemplate Lit101;
  
  static final SyntaxPattern Lit102;
  
  static final SyntaxTemplate Lit103;
  
  static final SimpleSymbol Lit104;
  
  static final SyntaxTemplate Lit105;
  
  static final SimpleSymbol Lit106;
  
  static final SyntaxTemplate Lit107;
  
  static final SimpleSymbol Lit108;
  
  static final SyntaxTemplate Lit109;
  
  static final PairWithPosition Lit11;
  
  static final SimpleSymbol Lit110;
  
  static final SyntaxPattern Lit111;
  
  static final SyntaxTemplate Lit112;
  
  static final SyntaxPattern Lit113;
  
  static final SyntaxTemplate Lit114;
  
  static final SimpleSymbol Lit115;
  
  static final SyntaxRules Lit116;
  
  static final SimpleSymbol Lit117;
  
  static final SyntaxPattern Lit118;
  
  static final SyntaxTemplate Lit119;
  
  static final SimpleSymbol Lit12;
  
  static final SyntaxPattern Lit120;
  
  static final SyntaxTemplate Lit121;
  
  static final SyntaxPattern Lit122;
  
  static final SyntaxTemplate Lit123;
  
  static final SimpleSymbol Lit124;
  
  static final SimpleSymbol Lit125;
  
  static final SyntaxRules Lit126;
  
  static final SimpleSymbol Lit127;
  
  static final SimpleSymbol Lit128;
  
  static final SyntaxRules Lit129;
  
  static final IntNum Lit13;
  
  static final SimpleSymbol Lit130;
  
  static final SimpleSymbol Lit131;
  
  static final SyntaxRules Lit132;
  
  static final SimpleSymbol Lit133;
  
  static final SimpleSymbol Lit134;
  
  static final SyntaxRules Lit135;
  
  static final SimpleSymbol Lit136;
  
  static final SimpleSymbol Lit137;
  
  static final SyntaxRules Lit138;
  
  static final SimpleSymbol Lit139;
  
  static final SimpleSymbol Lit14;
  
  static final SyntaxRules Lit140;
  
  static final SimpleSymbol Lit141;
  
  static final SimpleSymbol Lit142;
  
  static final SimpleSymbol Lit143;
  
  static final SimpleSymbol Lit144;
  
  static final SimpleSymbol Lit145;
  
  static final SimpleSymbol Lit146;
  
  static final SimpleSymbol Lit147;
  
  static final SimpleSymbol Lit148;
  
  static final SimpleSymbol Lit149;
  
  static final SimpleSymbol Lit15;
  
  static final SimpleSymbol Lit150;
  
  static final SimpleSymbol Lit151;
  
  static final SimpleSymbol Lit152;
  
  static final SimpleSymbol Lit153;
  
  static final SimpleSymbol Lit154;
  
  static final SimpleSymbol Lit155;
  
  static final SimpleSymbol Lit156;
  
  static final SimpleSymbol Lit157;
  
  static final SimpleSymbol Lit158;
  
  static final SimpleSymbol Lit159;
  
  static final SyntaxPattern Lit16;
  
  static final SimpleSymbol Lit160;
  
  static final SimpleSymbol Lit161;
  
  static final SimpleSymbol Lit162;
  
  static final SimpleSymbol Lit163;
  
  static final SimpleSymbol Lit164;
  
  static final SimpleSymbol Lit165 = (SimpleSymbol)(new SimpleSymbol("dynamic-wind")).readResolve();
  
  static final SyntaxTemplate Lit17;
  
  static final SyntaxPattern Lit18;
  
  static final SyntaxTemplate Lit19;
  
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
  
  static final SimpleSymbol Lit35;
  
  static final SimpleSymbol Lit36;
  
  static final SimpleSymbol Lit37;
  
  static final SimpleSymbol Lit38;
  
  static final SimpleSymbol Lit39;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit40;
  
  static final SimpleSymbol Lit41;
  
  static final SimpleSymbol Lit42;
  
  static final SimpleSymbol Lit43;
  
  static final SimpleSymbol Lit44;
  
  static final SimpleSymbol Lit45;
  
  static final SimpleSymbol Lit46;
  
  static final SimpleSymbol Lit47;
  
  static final SimpleSymbol Lit48;
  
  static final SimpleSymbol Lit49;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit50;
  
  static final SimpleSymbol Lit51;
  
  static final SimpleSymbol Lit52;
  
  static final SimpleSymbol Lit53;
  
  static final SimpleSymbol Lit54;
  
  static final SimpleSymbol Lit55;
  
  static final SimpleSymbol Lit56;
  
  static final SimpleSymbol Lit57;
  
  static final SimpleSymbol Lit58;
  
  static final SimpleSymbol Lit59;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit60;
  
  static final SimpleSymbol Lit61;
  
  static final SimpleSymbol Lit62;
  
  static final SimpleSymbol Lit63;
  
  static final SimpleSymbol Lit64;
  
  static final SimpleSymbol Lit65;
  
  static final SimpleSymbol Lit66;
  
  static final SimpleSymbol Lit67;
  
  static final SimpleSymbol Lit68;
  
  static final SimpleSymbol Lit69;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit70;
  
  static final SyntaxRules Lit71;
  
  static final SimpleSymbol Lit72;
  
  static final SyntaxRules Lit73;
  
  static final SimpleSymbol Lit74;
  
  static final SimpleSymbol Lit75;
  
  static final SyntaxRules Lit76;
  
  static final SimpleSymbol Lit77;
  
  static final SimpleSymbol Lit78;
  
  static final SimpleSymbol Lit79;
  
  static final PairWithPosition Lit8;
  
  static final SimpleSymbol Lit80;
  
  static final SimpleSymbol Lit81;
  
  static final SimpleSymbol Lit82;
  
  static final SimpleSymbol Lit83;
  
  static final SimpleSymbol Lit84;
  
  static final SyntaxRules Lit85;
  
  static final SimpleSymbol Lit86;
  
  static final SimpleSymbol Lit87;
  
  static final SimpleSymbol Lit88;
  
  static final SimpleSymbol Lit89;
  
  static final SimpleSymbol Lit9;
  
  static final SyntaxRules Lit90;
  
  static final SimpleSymbol Lit91;
  
  static final SimpleSymbol Lit92;
  
  static final SyntaxRules Lit93;
  
  static final SimpleSymbol Lit94;
  
  static final SyntaxPattern Lit95;
  
  static final SyntaxTemplate Lit96;
  
  static final SyntaxPattern Lit97;
  
  static final SyntaxTemplate Lit98;
  
  static final SimpleSymbol Lit99;
  
  static final ModuleMethod lambda$Fn1;
  
  static final ModuleMethod lambda$Fn2;
  
  static final ModuleMethod lambda$Fn3;
  
  public static final ModuleMethod test$Mnapply;
  
  public static final Macro test$Mnapproximate;
  
  public static final Macro test$Mnassert;
  
  public static final Macro test$Mnend;
  
  public static final Macro test$Mneq;
  
  public static final Macro test$Mnequal;
  
  public static final Macro test$Mneqv;
  
  public static final Macro test$Mnerror;
  
  public static final Macro test$Mnexpect$Mnfail;
  
  public static final Macro test$Mngroup$Mnwith$Mncleanup;
  
  public static Boolean test$Mnlog$Mnto$Mnfile;
  
  public static final Macro test$Mnmatch$Mnall;
  
  public static final Macro test$Mnmatch$Mnany;
  
  public static final ModuleMethod test$Mnmatch$Mnname;
  
  public static final Macro test$Mnmatch$Mnnth;
  
  public static final ModuleMethod test$Mnon$Mnbad$Mncount$Mnsimple;
  
  public static final ModuleMethod test$Mnon$Mnbad$Mnend$Mnname$Mnsimple;
  
  public static final ModuleMethod test$Mnon$Mnfinal$Mnsimple;
  
  public static final ModuleMethod test$Mnon$Mngroup$Mnbegin$Mnsimple;
  
  public static final ModuleMethod test$Mnon$Mngroup$Mnend$Mnsimple;
  
  static final ModuleMethod test$Mnon$Mntest$Mnbegin$Mnsimple;
  
  public static final ModuleMethod test$Mnon$Mntest$Mnend$Mnsimple;
  
  public static final ModuleMethod test$Mnpassed$Qu;
  
  public static final ModuleMethod test$Mnread$Mneval$Mnstring;
  
  public static final ModuleMethod test$Mnresult$Mnalist;
  
  public static final ModuleMethod test$Mnresult$Mnalist$Ex;
  
  public static final ModuleMethod test$Mnresult$Mnclear;
  
  public static final ModuleMethod test$Mnresult$Mnkind;
  
  public static final Macro test$Mnresult$Mnref;
  
  public static final ModuleMethod test$Mnresult$Mnremove;
  
  public static final ModuleMethod test$Mnresult$Mnset$Ex;
  
  static final Class test$Mnrunner;
  
  public static final ModuleMethod test$Mnrunner$Mnaux$Mnvalue;
  
  public static final ModuleMethod test$Mnrunner$Mnaux$Mnvalue$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mncreate;
  
  public static Object test$Mnrunner$Mncurrent;
  
  public static Object test$Mnrunner$Mnfactory;
  
  public static final ModuleMethod test$Mnrunner$Mnfail$Mncount;
  
  public static final ModuleMethod test$Mnrunner$Mnfail$Mncount$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mnget;
  
  public static final ModuleMethod test$Mnrunner$Mngroup$Mnpath;
  
  public static final ModuleMethod test$Mnrunner$Mngroup$Mnstack;
  
  public static final ModuleMethod test$Mnrunner$Mngroup$Mnstack$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mnnull;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mncount;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mncount$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mnend$Mnname;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mnend$Mnname$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mnfinal;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mnfinal$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnbegin;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnbegin$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnend;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnend$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnbegin;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnbegin$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnend;
  
  public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnend$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mnpass$Mncount;
  
  public static final ModuleMethod test$Mnrunner$Mnpass$Mncount$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mnreset;
  
  public static final ModuleMethod test$Mnrunner$Mnsimple;
  
  public static final ModuleMethod test$Mnrunner$Mnskip$Mncount;
  
  public static final ModuleMethod test$Mnrunner$Mnskip$Mncount$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mntest$Mnname;
  
  public static final ModuleMethod test$Mnrunner$Mnxfail$Mncount;
  
  public static final ModuleMethod test$Mnrunner$Mnxfail$Mncount$Ex;
  
  public static final ModuleMethod test$Mnrunner$Mnxpass$Mncount;
  
  public static final ModuleMethod test$Mnrunner$Mnxpass$Mncount$Ex;
  
  public static final ModuleMethod test$Mnrunner$Qu;
  
  public static final Macro test$Mnskip;
  
  public static final Macro test$Mnwith$Mnrunner;
  
  static Object $PcTestAnySpecifierMatches(Object paramObject1, Object paramObject2) {
    Boolean bool = Boolean.FALSE;
    while (true) {
      if (lists.isNull(paramObject1))
        return bool; 
      if ($PcTestSpecificierMatches(lists.car.apply1(paramObject1), paramObject2) != Boolean.FALSE)
        bool = Boolean.TRUE; 
      paramObject1 = lists.cdr.apply1(paramObject1);
    } 
  }
  
  public static Procedure $PcTestApproximimate$Eq(Object paramObject) {
    frame0 frame0 = new frame0();
    frame0.error = paramObject;
    return (Procedure)frame0.lambda$Fn4;
  }
  
  public static Object $PcTestAsSpecifier(Object paramObject) {
    return misc.isProcedure(paramObject) ? paramObject : (numbers.isInteger(paramObject) ? $PcTestMatchNth(Lit13, paramObject) : (strings.isString(paramObject) ? testMatchName(paramObject) : misc.error$V("not a valid test specifier", new Object[0])));
  }
  
  public static void $PcTestBegin(Object paramObject1, Object paramObject2) {
    if (((Procedure)test$Mnrunner$Mncurrent).apply0() == Boolean.FALSE)
      ((Procedure)test$Mnrunner$Mncurrent).apply1(testRunnerCreate()); 
    Object object = ((Procedure)test$Mnrunner$Mncurrent).apply0();
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    try {
      unner unner = (unner)object;
      applyToArgs.apply4(testRunnerOnGroupBegin(unner), object, paramObject1, paramObject2);
      try {
        unner unner1 = (unner)object;
        try {
          unner = (unner)object;
          Object object1 = $PcTestRunnerSkipList(unner);
          try {
            unner unner2 = (unner)object;
            $PcTestRunnerSkipSave$Ex(unner1, lists.cons(object1, $PcTestRunnerSkipSave(unner2)));
            try {
              unner1 = (unner)object;
              try {
                object1 = object;
                object1 = $PcTestRunnerFailList((unner)object1);
                try {
                  unner2 = (unner)object;
                  $PcTestRunnerFailSave$Ex(unner1, lists.cons(object1, $PcTestRunnerFailSave(unner2)));
                  try {
                    unner1 = (unner)object;
                    try {
                      object1 = object;
                      paramObject2 = lists.cons($PcTestRunnerTotalCount((unner)object1), paramObject2);
                      try {
                        object1 = object;
                        $PcTestRunnerCountList$Ex(unner1, lists.cons(paramObject2, $PcTestRunnerCountList((unner)object1)));
                        try {
                          paramObject2 = object;
                          try {
                            unner1 = (unner)object;
                            testRunnerGroupStack$Ex((unner)paramObject2, lists.cons(paramObject1, testRunnerGroupStack(unner1)));
                            return;
                          } catch (ClassCastException classCastException) {
                            throw new WrongType(classCastException, "test-runner-group-stack", 0, object);
                          } 
                        } catch (ClassCastException classCastException) {
                          throw new WrongType(classCastException, "test-runner-group-stack!", 0, object);
                        } 
                      } catch (ClassCastException classCastException) {
                        throw new WrongType(classCastException, "%test-runner-count-list", 0, object);
                      } 
                    } catch (ClassCastException classCastException) {
                      throw new WrongType(classCastException, "%test-runner-total-count", 0, object);
                    } 
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "%test-runner-count-list!", 0, object);
                  } 
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "%test-runner-fail-save", 0, object);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "%test-runner-fail-list", 0, object);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%test-runner-fail-save!", 0, object);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%test-runner-skip-save", 0, object);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%test-runner-skip-list", 0, object);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "%test-runner-skip-save!", 0, object);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-runner-on-group-begin", 0, object);
    } 
  }
  
  static Object $PcTestComp2(Object paramObject1, Object paramObject2) {
    paramObject2 = LList.list3(paramObject2, LList.list2(Lit15, $PcTestSourceLine2(paramObject2)), paramObject1);
    paramObject1 = SyntaxPattern.allocVars(6, null);
    if (Lit16.match(paramObject2, (Object[])paramObject1, 0)) {
      paramObject2 = TemplateScope.make();
      return Lit17.execute((Object[])paramObject1, (TemplateScope)paramObject2);
    } 
    if (Lit18.match(paramObject2, (Object[])paramObject1, 0)) {
      paramObject2 = TemplateScope.make();
      return Lit19.execute((Object[])paramObject1, (TemplateScope)paramObject2);
    } 
    return syntax_case.error("syntax-case", paramObject2);
  }
  
  public static Object $PcTestEnd(Object paramObject1, Object paramObject2) {
    // Byte code:
    //   0: invokestatic testRunnerGet : ()Ljava/lang/Object;
    //   3: astore_2
    //   4: aload_2
    //   5: checkcast gnu/kawa/slib/test$Mnrunner
    //   8: astore_3
    //   9: aload_3
    //   10: invokestatic testRunnerGroupStack : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   13: astore_3
    //   14: aload_2
    //   15: invokestatic $PcTestFormatLine : (Ljava/lang/Object;)Ljava/lang/Object;
    //   18: astore #4
    //   20: aload_2
    //   21: checkcast gnu/kawa/slib/test$Mnrunner
    //   24: astore #5
    //   26: aload #5
    //   28: aload_1
    //   29: invokestatic testResultAlist$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   32: aload_3
    //   33: invokestatic isNull : (Ljava/lang/Object;)Z
    //   36: ifeq -> 65
    //   39: iconst_2
    //   40: anewarray java/lang/Object
    //   43: dup
    //   44: iconst_0
    //   45: aload #4
    //   47: aastore
    //   48: dup
    //   49: iconst_1
    //   50: ldc_w 'test-end not in a group'
    //   53: aastore
    //   54: invokestatic stringAppend : ([Ljava/lang/Object;)Lgnu/lists/FString;
    //   57: iconst_0
    //   58: anewarray java/lang/Object
    //   61: invokestatic error$V : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   64: pop
    //   65: aload_0
    //   66: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   69: if_acmpeq -> 419
    //   72: aload_0
    //   73: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   76: aload_3
    //   77: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   80: invokestatic apply : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   83: ifne -> 115
    //   86: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   89: astore_1
    //   90: aload_2
    //   91: checkcast gnu/kawa/slib/test$Mnrunner
    //   94: astore #4
    //   96: aload_1
    //   97: aload #4
    //   99: invokestatic testRunnerOnBadEndName : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   102: aload_2
    //   103: aload_0
    //   104: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   107: aload_3
    //   108: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   111: invokevirtual apply4 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: pop
    //   115: aload_2
    //   116: checkcast gnu/kawa/slib/test$Mnrunner
    //   119: astore_0
    //   120: aload_0
    //   121: invokestatic $PcTestRunnerCountList : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   124: astore_0
    //   125: getstatic kawa/lib/lists.cdar : Lgnu/expr/GenericProc;
    //   128: aload_0
    //   129: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   132: astore_1
    //   133: getstatic kawa/lib/lists.caar : Lgnu/expr/GenericProc;
    //   136: aload_0
    //   137: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   140: astore_3
    //   141: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
    //   144: astore #4
    //   146: aload_2
    //   147: checkcast gnu/kawa/slib/test$Mnrunner
    //   150: astore #5
    //   152: aload #4
    //   154: aload #5
    //   156: invokestatic $PcTestRunnerTotalCount : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   159: aload_3
    //   160: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   163: astore_3
    //   164: aload_1
    //   165: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   168: if_acmpeq -> 429
    //   171: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
    //   174: aload_1
    //   175: aload_3
    //   176: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   179: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   182: if_acmpne -> 210
    //   185: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   188: astore #4
    //   190: aload_2
    //   191: checkcast gnu/kawa/slib/test$Mnrunner
    //   194: astore #5
    //   196: aload #4
    //   198: aload #5
    //   200: invokestatic testRunnerOnBadCount : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   203: aload_2
    //   204: aload_3
    //   205: aload_1
    //   206: invokevirtual apply4 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   209: pop
    //   210: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   213: astore_1
    //   214: aload_2
    //   215: checkcast gnu/kawa/slib/test$Mnrunner
    //   218: astore_3
    //   219: aload_1
    //   220: aload_3
    //   221: invokestatic testRunnerOnGroupEnd : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   224: aload_2
    //   225: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   228: pop
    //   229: aload_2
    //   230: checkcast gnu/kawa/slib/test$Mnrunner
    //   233: astore_1
    //   234: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   237: astore_3
    //   238: aload_2
    //   239: checkcast gnu/kawa/slib/test$Mnrunner
    //   242: astore #4
    //   244: aload_1
    //   245: aload_3
    //   246: aload #4
    //   248: invokestatic testRunnerGroupStack : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   251: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   254: invokestatic testRunnerGroupStack$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   257: aload_2
    //   258: checkcast gnu/kawa/slib/test$Mnrunner
    //   261: astore_1
    //   262: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   265: astore_3
    //   266: aload_2
    //   267: checkcast gnu/kawa/slib/test$Mnrunner
    //   270: astore #4
    //   272: aload_1
    //   273: aload_3
    //   274: aload #4
    //   276: invokestatic $PcTestRunnerSkipSave : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   279: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   282: invokestatic $PcTestRunnerSkipList$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   285: aload_2
    //   286: checkcast gnu/kawa/slib/test$Mnrunner
    //   289: astore_1
    //   290: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   293: astore_3
    //   294: aload_2
    //   295: checkcast gnu/kawa/slib/test$Mnrunner
    //   298: astore #4
    //   300: aload_1
    //   301: aload_3
    //   302: aload #4
    //   304: invokestatic $PcTestRunnerSkipSave : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   307: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   310: invokestatic $PcTestRunnerSkipSave$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   313: aload_2
    //   314: checkcast gnu/kawa/slib/test$Mnrunner
    //   317: astore_1
    //   318: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   321: astore_3
    //   322: aload_2
    //   323: checkcast gnu/kawa/slib/test$Mnrunner
    //   326: astore #4
    //   328: aload_1
    //   329: aload_3
    //   330: aload #4
    //   332: invokestatic $PcTestRunnerFailSave : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   335: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   338: invokestatic $PcTestRunnerFailList$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   341: aload_2
    //   342: checkcast gnu/kawa/slib/test$Mnrunner
    //   345: astore_1
    //   346: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   349: astore_3
    //   350: aload_2
    //   351: checkcast gnu/kawa/slib/test$Mnrunner
    //   354: astore #4
    //   356: aload_1
    //   357: aload_3
    //   358: aload #4
    //   360: invokestatic $PcTestRunnerFailSave : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   363: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   366: invokestatic $PcTestRunnerFailSave$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   369: aload_2
    //   370: checkcast gnu/kawa/slib/test$Mnrunner
    //   373: astore_1
    //   374: aload_1
    //   375: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   378: aload_0
    //   379: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   382: invokestatic $PcTestRunnerCountList$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   385: aload_2
    //   386: checkcast gnu/kawa/slib/test$Mnrunner
    //   389: astore_0
    //   390: aload_0
    //   391: invokestatic testRunnerGroupStack : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   394: invokestatic isNull : (Ljava/lang/Object;)Z
    //   397: ifeq -> 439
    //   400: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   403: astore_0
    //   404: aload_2
    //   405: checkcast gnu/kawa/slib/test$Mnrunner
    //   408: astore_1
    //   409: aload_0
    //   410: aload_1
    //   411: invokestatic testRunnerOnFinal : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   414: aload_2
    //   415: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   418: areturn
    //   419: aload_0
    //   420: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   423: if_acmpeq -> 115
    //   426: goto -> 86
    //   429: aload_1
    //   430: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   433: if_acmpeq -> 210
    //   436: goto -> 185
    //   439: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   442: areturn
    //   443: astore_0
    //   444: new gnu/mapping/WrongType
    //   447: dup
    //   448: aload_0
    //   449: ldc_w 'test-runner-group-stack'
    //   452: iconst_0
    //   453: aload_2
    //   454: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   457: athrow
    //   458: astore_0
    //   459: new gnu/mapping/WrongType
    //   462: dup
    //   463: aload_0
    //   464: ldc_w 'test-result-alist!'
    //   467: iconst_0
    //   468: aload_2
    //   469: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   472: athrow
    //   473: astore_0
    //   474: new gnu/mapping/WrongType
    //   477: dup
    //   478: aload_0
    //   479: ldc_w 'test-runner-on-bad-end-name'
    //   482: iconst_0
    //   483: aload_2
    //   484: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   487: athrow
    //   488: astore_0
    //   489: new gnu/mapping/WrongType
    //   492: dup
    //   493: aload_0
    //   494: ldc_w '%test-runner-count-list'
    //   497: iconst_0
    //   498: aload_2
    //   499: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   502: athrow
    //   503: astore_0
    //   504: new gnu/mapping/WrongType
    //   507: dup
    //   508: aload_0
    //   509: ldc_w '%test-runner-total-count'
    //   512: iconst_0
    //   513: aload_2
    //   514: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   517: athrow
    //   518: astore_0
    //   519: new gnu/mapping/WrongType
    //   522: dup
    //   523: aload_0
    //   524: ldc_w 'test-runner-on-bad-count'
    //   527: iconst_0
    //   528: aload_2
    //   529: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   532: athrow
    //   533: astore_0
    //   534: new gnu/mapping/WrongType
    //   537: dup
    //   538: aload_0
    //   539: ldc_w 'test-runner-on-group-end'
    //   542: iconst_0
    //   543: aload_2
    //   544: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   547: athrow
    //   548: astore_0
    //   549: new gnu/mapping/WrongType
    //   552: dup
    //   553: aload_0
    //   554: ldc_w 'test-runner-group-stack!'
    //   557: iconst_0
    //   558: aload_2
    //   559: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   562: athrow
    //   563: astore_0
    //   564: new gnu/mapping/WrongType
    //   567: dup
    //   568: aload_0
    //   569: ldc_w 'test-runner-group-stack'
    //   572: iconst_0
    //   573: aload_2
    //   574: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   577: athrow
    //   578: astore_0
    //   579: new gnu/mapping/WrongType
    //   582: dup
    //   583: aload_0
    //   584: ldc_w '%test-runner-skip-list!'
    //   587: iconst_0
    //   588: aload_2
    //   589: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   592: athrow
    //   593: astore_0
    //   594: new gnu/mapping/WrongType
    //   597: dup
    //   598: aload_0
    //   599: ldc_w '%test-runner-skip-save'
    //   602: iconst_0
    //   603: aload_2
    //   604: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   607: athrow
    //   608: astore_0
    //   609: new gnu/mapping/WrongType
    //   612: dup
    //   613: aload_0
    //   614: ldc_w '%test-runner-skip-save!'
    //   617: iconst_0
    //   618: aload_2
    //   619: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   622: athrow
    //   623: astore_0
    //   624: new gnu/mapping/WrongType
    //   627: dup
    //   628: aload_0
    //   629: ldc_w '%test-runner-skip-save'
    //   632: iconst_0
    //   633: aload_2
    //   634: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   637: athrow
    //   638: astore_0
    //   639: new gnu/mapping/WrongType
    //   642: dup
    //   643: aload_0
    //   644: ldc_w '%test-runner-fail-list!'
    //   647: iconst_0
    //   648: aload_2
    //   649: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   652: athrow
    //   653: astore_0
    //   654: new gnu/mapping/WrongType
    //   657: dup
    //   658: aload_0
    //   659: ldc_w '%test-runner-fail-save'
    //   662: iconst_0
    //   663: aload_2
    //   664: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   667: athrow
    //   668: astore_0
    //   669: new gnu/mapping/WrongType
    //   672: dup
    //   673: aload_0
    //   674: ldc_w '%test-runner-fail-save!'
    //   677: iconst_0
    //   678: aload_2
    //   679: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   682: athrow
    //   683: astore_0
    //   684: new gnu/mapping/WrongType
    //   687: dup
    //   688: aload_0
    //   689: ldc_w '%test-runner-fail-save'
    //   692: iconst_0
    //   693: aload_2
    //   694: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   697: athrow
    //   698: astore_0
    //   699: new gnu/mapping/WrongType
    //   702: dup
    //   703: aload_0
    //   704: ldc_w '%test-runner-count-list!'
    //   707: iconst_0
    //   708: aload_2
    //   709: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   712: athrow
    //   713: astore_0
    //   714: new gnu/mapping/WrongType
    //   717: dup
    //   718: aload_0
    //   719: ldc_w 'test-runner-group-stack'
    //   722: iconst_0
    //   723: aload_2
    //   724: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   727: athrow
    //   728: astore_0
    //   729: new gnu/mapping/WrongType
    //   732: dup
    //   733: aload_0
    //   734: ldc_w 'test-runner-on-final'
    //   737: iconst_0
    //   738: aload_2
    //   739: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   742: athrow
    // Exception table:
    //   from	to	target	type
    //   4	9	443	java/lang/ClassCastException
    //   20	26	458	java/lang/ClassCastException
    //   90	96	473	java/lang/ClassCastException
    //   115	120	488	java/lang/ClassCastException
    //   146	152	503	java/lang/ClassCastException
    //   190	196	518	java/lang/ClassCastException
    //   214	219	533	java/lang/ClassCastException
    //   229	234	548	java/lang/ClassCastException
    //   238	244	563	java/lang/ClassCastException
    //   257	262	578	java/lang/ClassCastException
    //   266	272	593	java/lang/ClassCastException
    //   285	290	608	java/lang/ClassCastException
    //   294	300	623	java/lang/ClassCastException
    //   313	318	638	java/lang/ClassCastException
    //   322	328	653	java/lang/ClassCastException
    //   341	346	668	java/lang/ClassCastException
    //   350	356	683	java/lang/ClassCastException
    //   369	374	698	java/lang/ClassCastException
    //   385	390	713	java/lang/ClassCastException
    //   404	409	728	java/lang/ClassCastException
  }
  
  static void $PcTestFinalReport1(Object paramObject1, Object paramObject2, Object paramObject3) {
    if (Scheme.numGrt.apply2(paramObject1, Lit0) != Boolean.FALSE) {
      ports.display(paramObject2, paramObject3);
      ports.display(paramObject1, paramObject3);
      ports.newline(paramObject3);
    } 
  }
  
  static void $PcTestFinalReportSimple(Object paramObject1, Object paramObject2) {
    try {
      unner unner = (unner)paramObject1;
      $PcTestFinalReport1(testRunnerPassCount(unner), "# of expected passes      ", paramObject2);
      try {
        unner = (unner)paramObject1;
        $PcTestFinalReport1(testRunnerXfailCount(unner), "# of expected failures    ", paramObject2);
        try {
          unner = (unner)paramObject1;
          $PcTestFinalReport1(testRunnerXpassCount(unner), "# of unexpected successes ", paramObject2);
          try {
            unner = (unner)paramObject1;
            $PcTestFinalReport1(testRunnerFailCount(unner), "# of unexpected failures  ", paramObject2);
            try {
              unner = (unner)paramObject1;
              $PcTestFinalReport1(testRunnerSkipCount(unner), "# of skipped tests        ", paramObject2);
              return;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "test-runner-skip-count", 0, paramObject1);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "test-runner-fail-count", 0, paramObject1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-xpass-count", 0, paramObject1);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "test-runner-xfail-count", 0, paramObject1);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-runner-pass-count", 0, paramObject1);
    } 
  }
  
  static Object $PcTestFormatLine(Object paramObject) {
    try {
      unner unner = (unner)paramObject;
      Object object = testResultAlist(unner);
      paramObject = lists.assq(Lit4, object);
      object = lists.assq(Lit5, object);
      if (paramObject != Boolean.FALSE) {
        paramObject = lists.cdr.apply1(paramObject);
      } else {
        paramObject = "";
      } 
      if (object != Boolean.FALSE) {
        object = lists.cdr.apply1(object);
        try {
          Number number = (Number)object;
          return strings.stringAppend(new Object[] { paramObject, ":", numbers.number$To$String(number), ": " });
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "number->string", 1, object);
        } 
      } 
      return "";
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "test-result-alist", 0, classCastException);
    } 
  }
  
  public static Procedure $PcTestMatchAll$V(Object[] paramArrayOfObject) {
    frame3 frame3 = new frame3();
    frame3.pred$Mnlist = LList.makeList(paramArrayOfObject, 0);
    return (Procedure)frame3.lambda$Fn12;
  }
  
  public static Procedure $PcTestMatchAny$V(Object[] paramArrayOfObject) {
    frame4 frame4 = new frame4();
    frame4.pred$Mnlist = LList.makeList(paramArrayOfObject, 0);
    return (Procedure)frame4.lambda$Fn13;
  }
  
  public static Procedure $PcTestMatchNth(Object paramObject1, Object paramObject2) {
    frame2 frame2 = new frame2();
    frame2.n = paramObject1;
    frame2.count = paramObject2;
    frame2.i = Lit0;
    return (Procedure)frame2.lambda$Fn11;
  }
  
  static Boolean $PcTestNullCallback(Object paramObject) {
    return Boolean.FALSE;
  }
  
  static void $PcTestOnBadCountWrite(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    ports.display("*** Total number of tests was ", paramObject4);
    ports.display(paramObject2, paramObject4);
    ports.display(" but should be ", paramObject4);
    ports.display(paramObject3, paramObject4);
    ports.display(". ***", paramObject4);
    ports.newline(paramObject4);
    ports.display("*** Discrepancy indicates testsuite error or exceptions. ***", paramObject4);
    ports.newline(paramObject4);
  }
  
  public static boolean $PcTestOnTestBegin(Object paramObject) {
    $PcTestShouldExecute(paramObject);
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    try {
      unner unner = (unner)paramObject;
      applyToArgs.apply2(testRunnerOnTestBegin(unner), paramObject);
      SimpleSymbol simpleSymbol1 = Lit2;
      SimpleSymbol simpleSymbol2 = Lit1;
      try {
        unner unner1 = (unner)paramObject;
        paramObject = lists.assq(simpleSymbol2, testResultAlist(unner1));
        if (paramObject != Boolean.FALSE) {
          paramObject = lists.cdr.apply1(paramObject);
        } else {
          paramObject = Boolean.FALSE;
        } 
        if (simpleSymbol1 == paramObject) {
          byte b1 = 1;
          return b1 + 1 & 0x1;
        } 
        byte b = 0;
        return b + 1 & 0x1;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "test-result-alist", 0, paramObject);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-runner-on-test-begin", 0, paramObject);
    } 
  }
  
  public static Object $PcTestOnTestEnd(Object paramObject1, Object paramObject2) {
    SimpleSymbol simpleSymbol2 = Lit1;
    SimpleSymbol simpleSymbol1 = Lit1;
    try {
      unner unner = (unner)paramObject1;
      Object object = lists.assq(simpleSymbol1, testResultAlist(unner));
      if (object != Boolean.FALSE) {
        object = lists.cdr.apply1(object);
      } else {
        object = Boolean.FALSE;
      } 
      if (object == Lit3) {
        if (paramObject2 != Boolean.FALSE) {
          paramObject2 = Lit9;
          return testResultSet$Ex(paramObject1, simpleSymbol2, paramObject2);
        } 
        paramObject2 = Lit3;
        return testResultSet$Ex(paramObject1, simpleSymbol2, paramObject2);
      } 
      if (paramObject2 != Boolean.FALSE) {
        paramObject2 = Lit12;
        return testResultSet$Ex(paramObject1, simpleSymbol2, paramObject2);
      } 
      paramObject2 = Lit14;
      return testResultSet$Ex(paramObject1, simpleSymbol2, paramObject2);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-result-alist", 0, paramObject1);
    } 
  }
  
  public static Object $PcTestReportResult() {
    // Byte code:
    //   0: invokestatic testRunnerGet : ()Ljava/lang/Object;
    //   3: astore_0
    //   4: iconst_1
    //   5: anewarray java/lang/Object
    //   8: dup
    //   9: iconst_0
    //   10: aload_0
    //   11: aastore
    //   12: invokestatic testResultKind$V : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   15: astore_1
    //   16: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   19: aload_1
    //   20: getstatic gnu/kawa/slib/testing.Lit12 : Lgnu/mapping/SimpleSymbol;
    //   23: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   26: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   29: if_acmpeq -> 117
    //   32: aload_0
    //   33: checkcast gnu/kawa/slib/test$Mnrunner
    //   36: astore_1
    //   37: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   40: astore_2
    //   41: getstatic gnu/kawa/slib/testing.Lit13 : Lgnu/math/IntNum;
    //   44: astore_3
    //   45: aload_0
    //   46: checkcast gnu/kawa/slib/test$Mnrunner
    //   49: astore #4
    //   51: aload_1
    //   52: aload_2
    //   53: aload_3
    //   54: aload #4
    //   56: invokestatic testRunnerPassCount : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   59: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   62: invokestatic testRunnerPassCount$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   65: aload_0
    //   66: checkcast gnu/kawa/slib/test$Mnrunner
    //   69: astore_1
    //   70: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   73: astore_2
    //   74: getstatic gnu/kawa/slib/testing.Lit13 : Lgnu/math/IntNum;
    //   77: astore_3
    //   78: aload_0
    //   79: checkcast gnu/kawa/slib/test$Mnrunner
    //   82: astore #4
    //   84: aload_1
    //   85: aload_2
    //   86: aload_3
    //   87: aload #4
    //   89: invokestatic $PcTestRunnerTotalCount : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   92: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   95: invokestatic $PcTestRunnerTotalCount$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   98: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   101: astore_1
    //   102: aload_0
    //   103: checkcast gnu/kawa/slib/test$Mnrunner
    //   106: astore_2
    //   107: aload_1
    //   108: aload_2
    //   109: invokestatic testRunnerOnTestEnd : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   112: aload_0
    //   113: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   116: areturn
    //   117: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   120: aload_1
    //   121: getstatic gnu/kawa/slib/testing.Lit14 : Lgnu/mapping/SimpleSymbol;
    //   124: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   127: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   130: if_acmpeq -> 169
    //   133: aload_0
    //   134: checkcast gnu/kawa/slib/test$Mnrunner
    //   137: astore_1
    //   138: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   141: astore_2
    //   142: getstatic gnu/kawa/slib/testing.Lit13 : Lgnu/math/IntNum;
    //   145: astore_3
    //   146: aload_0
    //   147: checkcast gnu/kawa/slib/test$Mnrunner
    //   150: astore #4
    //   152: aload_1
    //   153: aload_2
    //   154: aload_3
    //   155: aload #4
    //   157: invokestatic testRunnerFailCount : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   160: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   163: invokestatic testRunnerFailCount$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   166: goto -> 65
    //   169: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   172: aload_1
    //   173: getstatic gnu/kawa/slib/testing.Lit9 : Lgnu/mapping/SimpleSymbol;
    //   176: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   179: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   182: if_acmpeq -> 221
    //   185: aload_0
    //   186: checkcast gnu/kawa/slib/test$Mnrunner
    //   189: astore_1
    //   190: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   193: astore_2
    //   194: getstatic gnu/kawa/slib/testing.Lit13 : Lgnu/math/IntNum;
    //   197: astore_3
    //   198: aload_0
    //   199: checkcast gnu/kawa/slib/test$Mnrunner
    //   202: astore #4
    //   204: aload_1
    //   205: aload_2
    //   206: aload_3
    //   207: aload #4
    //   209: invokestatic testRunnerXpassCount : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   212: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   215: invokestatic testRunnerXpassCount$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   218: goto -> 65
    //   221: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   224: aload_1
    //   225: getstatic gnu/kawa/slib/testing.Lit3 : Lgnu/mapping/SimpleSymbol;
    //   228: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   231: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   234: if_acmpeq -> 273
    //   237: aload_0
    //   238: checkcast gnu/kawa/slib/test$Mnrunner
    //   241: astore_1
    //   242: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   245: astore_2
    //   246: getstatic gnu/kawa/slib/testing.Lit13 : Lgnu/math/IntNum;
    //   249: astore_3
    //   250: aload_0
    //   251: checkcast gnu/kawa/slib/test$Mnrunner
    //   254: astore #4
    //   256: aload_1
    //   257: aload_2
    //   258: aload_3
    //   259: aload #4
    //   261: invokestatic testRunnerXfailCount : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   264: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   267: invokestatic testRunnerXfailCount$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   270: goto -> 65
    //   273: aload_0
    //   274: checkcast gnu/kawa/slib/test$Mnrunner
    //   277: astore_1
    //   278: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   281: astore_2
    //   282: getstatic gnu/kawa/slib/testing.Lit13 : Lgnu/math/IntNum;
    //   285: astore_3
    //   286: aload_0
    //   287: checkcast gnu/kawa/slib/test$Mnrunner
    //   290: astore #4
    //   292: aload_1
    //   293: aload_2
    //   294: aload_3
    //   295: aload #4
    //   297: invokestatic testRunnerSkipCount : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   300: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   303: invokestatic testRunnerSkipCount$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   306: goto -> 65
    //   309: astore_1
    //   310: new gnu/mapping/WrongType
    //   313: dup
    //   314: aload_1
    //   315: ldc_w 'test-runner-pass-count!'
    //   318: iconst_0
    //   319: aload_0
    //   320: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   323: athrow
    //   324: astore_1
    //   325: new gnu/mapping/WrongType
    //   328: dup
    //   329: aload_1
    //   330: ldc_w 'test-runner-pass-count'
    //   333: iconst_0
    //   334: aload_0
    //   335: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   338: athrow
    //   339: astore_1
    //   340: new gnu/mapping/WrongType
    //   343: dup
    //   344: aload_1
    //   345: ldc_w 'test-runner-fail-count!'
    //   348: iconst_0
    //   349: aload_0
    //   350: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   353: athrow
    //   354: astore_1
    //   355: new gnu/mapping/WrongType
    //   358: dup
    //   359: aload_1
    //   360: ldc_w 'test-runner-fail-count'
    //   363: iconst_0
    //   364: aload_0
    //   365: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   368: athrow
    //   369: astore_1
    //   370: new gnu/mapping/WrongType
    //   373: dup
    //   374: aload_1
    //   375: ldc_w 'test-runner-xpass-count!'
    //   378: iconst_0
    //   379: aload_0
    //   380: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   383: athrow
    //   384: astore_1
    //   385: new gnu/mapping/WrongType
    //   388: dup
    //   389: aload_1
    //   390: ldc_w 'test-runner-xpass-count'
    //   393: iconst_0
    //   394: aload_0
    //   395: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   398: athrow
    //   399: astore_1
    //   400: new gnu/mapping/WrongType
    //   403: dup
    //   404: aload_1
    //   405: ldc_w 'test-runner-xfail-count!'
    //   408: iconst_0
    //   409: aload_0
    //   410: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   413: athrow
    //   414: astore_1
    //   415: new gnu/mapping/WrongType
    //   418: dup
    //   419: aload_1
    //   420: ldc_w 'test-runner-xfail-count'
    //   423: iconst_0
    //   424: aload_0
    //   425: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   428: athrow
    //   429: astore_1
    //   430: new gnu/mapping/WrongType
    //   433: dup
    //   434: aload_1
    //   435: ldc_w 'test-runner-skip-count!'
    //   438: iconst_0
    //   439: aload_0
    //   440: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   443: athrow
    //   444: astore_1
    //   445: new gnu/mapping/WrongType
    //   448: dup
    //   449: aload_1
    //   450: ldc_w 'test-runner-skip-count'
    //   453: iconst_0
    //   454: aload_0
    //   455: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   458: athrow
    //   459: astore_1
    //   460: new gnu/mapping/WrongType
    //   463: dup
    //   464: aload_1
    //   465: ldc_w '%test-runner-total-count!'
    //   468: iconst_0
    //   469: aload_0
    //   470: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   473: athrow
    //   474: astore_1
    //   475: new gnu/mapping/WrongType
    //   478: dup
    //   479: aload_1
    //   480: ldc_w '%test-runner-total-count'
    //   483: iconst_0
    //   484: aload_0
    //   485: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   488: athrow
    //   489: astore_1
    //   490: new gnu/mapping/WrongType
    //   493: dup
    //   494: aload_1
    //   495: ldc_w 'test-runner-on-test-end'
    //   498: iconst_0
    //   499: aload_0
    //   500: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   503: athrow
    // Exception table:
    //   from	to	target	type
    //   32	37	309	java/lang/ClassCastException
    //   45	51	324	java/lang/ClassCastException
    //   65	70	459	java/lang/ClassCastException
    //   78	84	474	java/lang/ClassCastException
    //   102	107	489	java/lang/ClassCastException
    //   133	138	339	java/lang/ClassCastException
    //   146	152	354	java/lang/ClassCastException
    //   185	190	369	java/lang/ClassCastException
    //   198	204	384	java/lang/ClassCastException
    //   237	242	399	java/lang/ClassCastException
    //   250	256	414	java/lang/ClassCastException
    //   273	278	429	java/lang/ClassCastException
    //   286	292	444	java/lang/ClassCastException
  }
  
  static unner $PcTestRunnerAlloc() {
    return new unner();
  }
  
  static Object $PcTestRunnerCountList(unner paramunner) {
    return paramunner.count$Mnlist;
  }
  
  static void $PcTestRunnerCountList$Ex(unner paramunner, Object paramObject) {
    paramunner.count$Mnlist = paramObject;
  }
  
  public static Object $PcTestRunnerFailList(unner paramunner) {
    return paramunner.fail$Mnlist;
  }
  
  public static void $PcTestRunnerFailList$Ex(unner paramunner, Object paramObject) {
    paramunner.fail$Mnlist = paramObject;
  }
  
  static Object $PcTestRunnerFailSave(unner paramunner) {
    return paramunner.fail$Mnsave;
  }
  
  static void $PcTestRunnerFailSave$Ex(unner paramunner, Object paramObject) {
    paramunner.fail$Mnsave = paramObject;
  }
  
  static Object $PcTestRunnerRunList(unner paramunner) {
    return paramunner.run$Mnlist;
  }
  
  static void $PcTestRunnerRunList$Ex(unner paramunner, Object paramObject) {
    paramunner.run$Mnlist = paramObject;
  }
  
  public static Object $PcTestRunnerSkipList(unner paramunner) {
    return paramunner.skip$Mnlist;
  }
  
  public static void $PcTestRunnerSkipList$Ex(unner paramunner, Object paramObject) {
    paramunner.skip$Mnlist = paramObject;
  }
  
  static Object $PcTestRunnerSkipSave(unner paramunner) {
    return paramunner.skip$Mnsave;
  }
  
  static void $PcTestRunnerSkipSave$Ex(unner paramunner, Object paramObject) {
    paramunner.skip$Mnsave = paramObject;
  }
  
  static Object $PcTestRunnerTotalCount(unner paramunner) {
    return paramunner.total$Mncount;
  }
  
  static void $PcTestRunnerTotalCount$Ex(unner paramunner, Object paramObject) {
    paramunner.total$Mncount = paramObject;
  }
  
  public static Object $PcTestShouldExecute(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: checkcast gnu/kawa/slib/test$Mnrunner
    //   4: astore_2
    //   5: aload_2
    //   6: invokestatic $PcTestRunnerRunList : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   9: astore_2
    //   10: aload_2
    //   11: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   14: if_acmpne -> 52
    //   17: iconst_1
    //   18: istore_1
    //   19: iload_1
    //   20: ifeq -> 57
    //   23: iload_1
    //   24: iconst_1
    //   25: iadd
    //   26: iconst_1
    //   27: iand
    //   28: istore_1
    //   29: iload_1
    //   30: ifeq -> 82
    //   33: iload_1
    //   34: ifeq -> 101
    //   37: aload_0
    //   38: getstatic gnu/kawa/slib/testing.Lit1 : Lgnu/mapping/SimpleSymbol;
    //   41: getstatic gnu/kawa/slib/testing.Lit2 : Lgnu/mapping/SimpleSymbol;
    //   44: invokestatic testResultSet$Ex : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   47: pop
    //   48: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   51: areturn
    //   52: iconst_0
    //   53: istore_1
    //   54: goto -> 19
    //   57: aload_2
    //   58: aload_0
    //   59: invokestatic $PcTestAnySpecifierMatches : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   62: astore_2
    //   63: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   66: astore_3
    //   67: aload_2
    //   68: aload_3
    //   69: if_acmpeq -> 77
    //   72: iconst_1
    //   73: istore_1
    //   74: goto -> 23
    //   77: iconst_0
    //   78: istore_1
    //   79: goto -> 23
    //   82: aload_0
    //   83: checkcast gnu/kawa/slib/test$Mnrunner
    //   86: astore_2
    //   87: aload_2
    //   88: invokestatic $PcTestRunnerSkipList : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   91: aload_0
    //   92: invokestatic $PcTestAnySpecifierMatches : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   95: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   98: if_acmpne -> 37
    //   101: aload_0
    //   102: checkcast gnu/kawa/slib/test$Mnrunner
    //   105: astore_2
    //   106: aload_2
    //   107: invokestatic $PcTestRunnerFailList : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   110: aload_0
    //   111: invokestatic $PcTestAnySpecifierMatches : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   117: if_acmpeq -> 135
    //   120: aload_0
    //   121: getstatic gnu/kawa/slib/testing.Lit1 : Lgnu/mapping/SimpleSymbol;
    //   124: getstatic gnu/kawa/slib/testing.Lit3 : Lgnu/mapping/SimpleSymbol;
    //   127: invokestatic testResultSet$Ex : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   130: pop
    //   131: getstatic gnu/kawa/slib/testing.Lit3 : Lgnu/mapping/SimpleSymbol;
    //   134: areturn
    //   135: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   138: areturn
    //   139: astore_2
    //   140: new gnu/mapping/WrongType
    //   143: dup
    //   144: aload_2
    //   145: ldc_w '%test-runner-run-list'
    //   148: iconst_0
    //   149: aload_0
    //   150: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   153: athrow
    //   154: astore_0
    //   155: new gnu/mapping/WrongType
    //   158: dup
    //   159: aload_0
    //   160: ldc_w 'x'
    //   163: bipush #-2
    //   165: aload_2
    //   166: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   169: athrow
    //   170: astore_2
    //   171: new gnu/mapping/WrongType
    //   174: dup
    //   175: aload_2
    //   176: ldc_w '%test-runner-skip-list'
    //   179: iconst_0
    //   180: aload_0
    //   181: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   184: athrow
    //   185: astore_2
    //   186: new gnu/mapping/WrongType
    //   189: dup
    //   190: aload_2
    //   191: ldc_w '%test-runner-fail-list'
    //   194: iconst_0
    //   195: aload_0
    //   196: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   199: athrow
    // Exception table:
    //   from	to	target	type
    //   0	5	139	java/lang/ClassCastException
    //   63	67	154	java/lang/ClassCastException
    //   82	87	170	java/lang/ClassCastException
    //   101	106	185	java/lang/ClassCastException
  }
  
  static Pair $PcTestSourceLine2(Object paramObject) {
    Object object1 = std_syntax.syntaxLine(paramObject);
    Object object2 = $PcTestSyntaxFile(paramObject);
    if (object1 != Boolean.FALSE) {
      object1 = LList.list1(lists.cons(Lit5, object1));
    } else {
      object1 = LList.Empty;
    } 
    Pair pair = lists.cons(Lit6, std_syntax.syntaxObject$To$Datum(paramObject));
    paramObject = object1;
    if (object2 != Boolean.FALSE)
      paramObject = lists.cons(lists.cons(Lit4, object2), object1); 
    return lists.cons(pair, paramObject);
  }
  
  static Object $PcTestSpecificierMatches(Object paramObject1, Object paramObject2) {
    return Scheme.applyToArgs.apply2(paramObject1, paramObject2);
  }
  
  static Object $PcTestSyntaxFile(Object paramObject) {
    return std_syntax.syntaxSource(paramObject);
  }
  
  static Object $PcTestWriteResult1(Object paramObject1, Object paramObject2) {
    ports.display("  ", paramObject2);
    ports.display(lists.car.apply1(paramObject1), paramObject2);
    ports.display(": ", paramObject2);
    ports.write(lists.cdr.apply1(paramObject1), paramObject2);
    ports.newline(paramObject2);
    return Values.empty;
  }
  
  static {
    Lit164 = (SimpleSymbol)(new SimpleSymbol("p")).readResolve();
    Lit163 = (SimpleSymbol)(new SimpleSymbol("exp")).readResolve();
    Lit162 = (SimpleSymbol)(new SimpleSymbol("res")).readResolve();
    Lit161 = (SimpleSymbol)(new SimpleSymbol("if")).readResolve();
    Lit160 = (SimpleSymbol)(new SimpleSymbol("name")).readResolve();
    Lit159 = (SimpleSymbol)(new SimpleSymbol("instance?")).readResolve();
    Lit158 = (SimpleSymbol)(new SimpleSymbol("cond")).readResolve();
    Lit157 = (SimpleSymbol)(new SimpleSymbol("actual-error")).readResolve();
    Lit156 = (SimpleSymbol)(new SimpleSymbol("<java.lang.Throwable>")).readResolve();
    Lit155 = (SimpleSymbol)(new SimpleSymbol("actual-value")).readResolve();
    Lit154 = (SimpleSymbol)(new SimpleSymbol("try-catch")).readResolve();
    Lit153 = (SimpleSymbol)(new SimpleSymbol("et")).readResolve();
    Lit152 = (SimpleSymbol)(new SimpleSymbol("expected-error")).readResolve();
    Lit151 = (SimpleSymbol)(new SimpleSymbol("ex")).readResolve();
    Lit150 = (SimpleSymbol)(new SimpleSymbol("let*")).readResolve();
    Lit149 = (SimpleSymbol)(new SimpleSymbol("r")).readResolve();
    Lit148 = (SimpleSymbol)(new SimpleSymbol("saved-runner")).readResolve();
    Lit147 = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
    Lit146 = (SimpleSymbol)(new SimpleSymbol("test-runner-current")).readResolve();
    Lit145 = (SimpleSymbol)(new SimpleSymbol("cons")).readResolve();
    Lit144 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
    Lit143 = (SimpleSymbol)(new SimpleSymbol("runner")).readResolve();
    Lit142 = (SimpleSymbol)(new SimpleSymbol("test-read-eval-string")).readResolve();
    Lit141 = (SimpleSymbol)(new SimpleSymbol("test-match-name")).readResolve();
    SimpleSymbol simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("test-expect-fail")).readResolve();
    Lit139 = simpleSymbol1;
    SyntaxPattern syntaxPattern9 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
    SimpleSymbol simpleSymbol8 = Lit144;
    SimpleSymbol simpleSymbol11 = Lit143;
    SimpleSymbol simpleSymbol12 = (SimpleSymbol)(new SimpleSymbol("test-runner-get")).readResolve();
    Lit60 = simpleSymbol12;
    PairWithPosition pairWithPosition4 = PairWithPosition.make(PairWithPosition.make(simpleSymbol11, PairWithPosition.make(PairWithPosition.make(simpleSymbol12, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3952660), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3952660), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3952652), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3952651);
    simpleSymbol12 = (SimpleSymbol)(new SimpleSymbol("%test-runner-fail-list!")).readResolve();
    Lit34 = simpleSymbol12;
    SimpleSymbol simpleSymbol14 = Lit143;
    SimpleSymbol simpleSymbol15 = Lit145;
    SimpleSymbol simpleSymbol16 = (SimpleSymbol)(new SimpleSymbol("test-match-all")).readResolve();
    Lit131 = simpleSymbol16;
    SimpleSymbol simpleSymbol17 = (SimpleSymbol)(new SimpleSymbol("%test-as-specifier")).readResolve();
    Lit136 = simpleSymbol17;
    SimpleSymbol simpleSymbol19 = (SimpleSymbol)(new SimpleSymbol("%test-runner-fail-list")).readResolve();
    Lit33 = simpleSymbol19;
    SyntaxRule syntaxRule9 = new SyntaxRule(syntaxPattern9, "\003", "\021\030\004\021\030\f\b\021\030\024\021\030\034\b\021\030$Q\021\030,\b\005\021\0304\b\003\030<", new Object[] { simpleSymbol8, pairWithPosition4, simpleSymbol12, simpleSymbol14, simpleSymbol15, simpleSymbol16, simpleSymbol17, PairWithPosition.make(PairWithPosition.make(simpleSymbol19, PairWithPosition.make(Lit143, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3964958), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3964934), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3964934) }1);
    Lit140 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule9 }, 1);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("test-skip")).readResolve();
    Lit137 = simpleSymbol1;
    SyntaxPattern syntaxPattern8 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
    simpleSymbol8 = Lit144;
    pairWithPosition4 = PairWithPosition.make(PairWithPosition.make(Lit143, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3919892), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3919892), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3919884), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3919883);
    simpleSymbol12 = (SimpleSymbol)(new SimpleSymbol("%test-runner-skip-list!")).readResolve();
    Lit32 = simpleSymbol12;
    simpleSymbol14 = Lit143;
    simpleSymbol15 = Lit145;
    simpleSymbol16 = Lit131;
    simpleSymbol17 = Lit136;
    simpleSymbol19 = (SimpleSymbol)(new SimpleSymbol("%test-runner-skip-list")).readResolve();
    Lit31 = simpleSymbol19;
    SyntaxRule syntaxRule8 = new SyntaxRule(syntaxPattern8, "\003", "\021\030\004\021\030\f\b\021\030\024\021\030\034\b\021\030$Q\021\030,\b\005\021\0304\b\003\030<", new Object[] { simpleSymbol8, pairWithPosition4, simpleSymbol12, simpleSymbol14, simpleSymbol15, simpleSymbol16, simpleSymbol17, PairWithPosition.make(PairWithPosition.make(simpleSymbol19, PairWithPosition.make(Lit143, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3932190), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3932166), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3932166) }1);
    Lit138 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule8 }, 1);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("test-match-any")).readResolve();
    Lit134 = simpleSymbol1;
    SyntaxPattern syntaxPattern7 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
    simpleSymbol8 = (SimpleSymbol)(new SimpleSymbol("%test-match-any")).readResolve();
    Lit133 = simpleSymbol8;
    SyntaxRule syntaxRule7 = new SyntaxRule(syntaxPattern7, "\003", "\021\030\004\b\005\021\030\f\b\003", new Object[] { simpleSymbol8, Lit136 }, 1);
    Lit135 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule7 }, 1);
    simpleSymbol1 = Lit131;
    SyntaxPattern syntaxPattern6 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
    simpleSymbol8 = (SimpleSymbol)(new SimpleSymbol("%test-match-all")).readResolve();
    Lit130 = simpleSymbol8;
    SyntaxRule syntaxRule6 = new SyntaxRule(syntaxPattern6, "\003", "\021\030\004\b\005\021\030\f\b\003", new Object[] { simpleSymbol8, Lit136 }, 1);
    Lit132 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule6 }, 1);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("test-match-nth")).readResolve();
    Lit128 = simpleSymbol1;
    SyntaxPattern syntaxPattern5 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
    simpleSymbol8 = Lit128;
    IntNum intNum = IntNum.make(1);
    Lit13 = intNum;
    SyntaxRule syntaxRule5 = new SyntaxRule(syntaxPattern5, "\001", "\021\030\004\t\003\030\f", new Object[] { simpleSymbol8, PairWithPosition.make(intNum, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3727384) }0);
    SyntaxPattern syntaxPattern11 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
    SimpleSymbol simpleSymbol10 = (SimpleSymbol)(new SimpleSymbol("%test-match-nth")).readResolve();
    Lit127 = simpleSymbol10;
    SyntaxRule syntaxRule13 = new SyntaxRule(syntaxPattern11, "\001\001", "\021\030\004\t\003\b\013", new Object[] { simpleSymbol10 }, 0);
    Lit129 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule5, syntaxRule13 }, 2);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("test-with-runner")).readResolve();
    Lit125 = simpleSymbol1;
    syntaxRule5 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2), "\001\003", "\021\030\004\021\030\f\b\021\030\024Y\021\030\034\t\020\b\021\030$\b\003A\021\030\034\t\020\b\r\013\030,", new Object[] { Lit144, PairWithPosition.make(PairWithPosition.make(Lit148, PairWithPosition.make(PairWithPosition.make(Lit146, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3657754), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3657754), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3657740), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3657739), Lit165, Lit147, Lit146, PairWithPosition.make(PairWithPosition.make(Lit147, PairWithPosition.make(LList.Empty, PairWithPosition.make(PairWithPosition.make(Lit146, PairWithPosition.make(Lit148, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3674156), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3674135), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3674135), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3674132), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3674124), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3674124) }1);
    Lit126 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule5 }, 2);
    Lit124 = (SimpleSymbol)(new SimpleSymbol("test-apply")).readResolve();
    simpleSymbol1 = Lit150;
    PairWithPosition pairWithPosition2 = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3514382), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3514382), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3514379), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3514378);
    SimpleSymbol simpleSymbol7 = (SimpleSymbol)(new SimpleSymbol("test-result-alist!")).readResolve();
    Lit52 = simpleSymbol7;
    simpleSymbol10 = Lit149;
    simpleSymbol12 = (SimpleSymbol)(new SimpleSymbol("%test-error")).readResolve();
    Lit115 = simpleSymbol12;
    Lit123 = new SyntaxTemplate("\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b\023\b\021\030$\021\030\034\021\030,\b\013", new Object[] { simpleSymbol1, pairWithPosition2, simpleSymbol7, simpleSymbol10, simpleSymbol12, Boolean.TRUE }, 0);
    Lit122 = new SyntaxPattern(",\f\007\f\017\b\f\027\b", new Object[0], 3);
    Lit121 = new SyntaxTemplate("\001\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b\033\b\021\030$\021\030\034\t\013\b\023", new Object[] { Lit150, PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3493902), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3493902), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3493899), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3493898), Lit52, Lit149, Lit115 }0);
    Lit120 = new SyntaxPattern("<\f\007\f\017\f\027\b\f\037\b", new Object[0], 4);
    simpleSymbol1 = Lit150;
    pairWithPosition2 = PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3469326), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3469326), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3469323);
    simpleSymbol7 = Lit160;
    simpleSymbol10 = Lit52;
    simpleSymbol12 = Lit149;
    simpleSymbol14 = Lit145;
    simpleSymbol15 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
    Lit15 = simpleSymbol15;
    simpleSymbol16 = (SimpleSymbol)(new SimpleSymbol("test-name")).readResolve();
    Lit7 = simpleSymbol16;
    Lit119 = new SyntaxTemplate("\001\001\001\001\001", "\021\030\004I\021\030\f\b\021\030\024\b\013©\021\030\034\021\030$\b\021\030,A\021\030,\021\0304\b\013\b#\b\021\030<\021\030$\t\023\b\033", new Object[] { simpleSymbol1, pairWithPosition2, simpleSymbol7, simpleSymbol10, simpleSymbol12, simpleSymbol14, PairWithPosition.make(simpleSymbol15, PairWithPosition.make(simpleSymbol16, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3477545), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3477545), Lit115 }0);
    Lit118 = new SyntaxPattern("L\f\007\f\017\f\027\f\037\b\f'\b", new Object[0], 5);
    Lit117 = (SimpleSymbol)(new SimpleSymbol("test-error")).readResolve();
    simpleSymbol1 = Lit115;
    SyntaxPattern syntaxPattern4 = new SyntaxPattern("\f\030\f\007\f\002\f\017\b", new Object[] { Boolean.TRUE }, 2);
    simpleSymbol7 = Lit158;
    simpleSymbol10 = (SimpleSymbol)(new SimpleSymbol("%test-on-test-begin")).readResolve();
    Lit86 = simpleSymbol10;
    simpleSymbol12 = (SimpleSymbol)(new SimpleSymbol("test-result-set!")).readResolve();
    Lit78 = simpleSymbol12;
    PairWithPosition pairWithPosition5 = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit152, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3223581), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3223581), PairWithPosition.make(Boolean.TRUE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3223596), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3223580);
    simpleSymbol15 = (SimpleSymbol)(new SimpleSymbol("%test-on-test-end")).readResolve();
    Lit87 = simpleSymbol15;
    simpleSymbol16 = Lit154;
    simpleSymbol17 = Lit144;
    PairWithPosition pairWithPosition7 = PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3239966), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3239966);
    PairWithPosition pairWithPosition8 = PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3244041);
    SimpleSymbol simpleSymbol21 = Lit151;
    SimpleSymbol simpleSymbol22 = Lit156;
    PairWithPosition pairWithPosition9 = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit157, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3252256), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3252256), PairWithPosition.make(Lit151, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3252269), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3252255);
    PairWithPosition pairWithPosition10 = PairWithPosition.make(Boolean.TRUE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3256331);
    SimpleSymbol simpleSymbol23 = (SimpleSymbol)(new SimpleSymbol("%test-report-result")).readResolve();
    Lit83 = simpleSymbol23;
    SyntaxRule syntaxRule4 = new SyntaxRule(syntaxPattern4, "\001\001", "\021\030\004\b)\021\030\f\b\0039\021\030\024\t\003\030\034ũ\021\030$\t\003\b\021\030,\021\0304\t\020Q\021\030\024\t\003\021\030<\b\013\030D\b\021\030L\021\030T9\021\030\024\t\003\030\\\030d\030l", new Object[] { 
          simpleSymbol7, simpleSymbol10, simpleSymbol12, pairWithPosition5, simpleSymbol15, simpleSymbol16, simpleSymbol17, pairWithPosition7, pairWithPosition8, simpleSymbol21, 
          simpleSymbol22, pairWithPosition9, pairWithPosition10, PairWithPosition.make(PairWithPosition.make(simpleSymbol23, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3260424), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3260424) }0);
    SyntaxRule syntaxRule12 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3), "\001\001\001", "\021\030\004)\021\030\f\b\003\b\021\030\0241\b\021\030\034\b\0139\021\030$\t\003\030,ũ\021\0304\t\003\b\021\030<\021\030\024\t\020Q\021\030$\t\003\021\030D\b\023\030L\b\021\030T\021\030\\9\021\030$\t\003\030d\030l\030t", new Object[] { 
          Lit161, Lit86, Lit144, Lit153, Lit78, PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit152, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3276828), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3276828), PairWithPosition.make(Lit153, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3276843), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3276827), Lit87, Lit154, PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3293213), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3293213), PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3297288), 
          Lit151, Lit156, PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit157, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3305503), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3305503), PairWithPosition.make(Lit151, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3305516), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3305502), PairWithPosition.make(PairWithPosition.make(Lit158, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make((new SimpleSymbol("and")).readResolve(), PairWithPosition.make(PairWithPosition.make(Lit159, PairWithPosition.make(Lit153, PairWithPosition.make((new SimpleSymbol("<gnu.bytecode.ClassType>")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309604), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309601), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309590), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make((new SimpleSymbol("$lookup$")).readResolve(), Pair.make((new SimpleSymbol("gnu.bytecode.ClassType")).readResolve(), Pair.make(Pair.make((new SimpleSymbol("quasiquote")).readResolve(), Pair.make((new SimpleSymbol("isSubclass")).readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3313673), PairWithPosition.make(Lit153, PairWithPosition.make(Lit156, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3313710), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3313707), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3313672), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3313672), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309590), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309585), PairWithPosition.make(PairWithPosition.make(Lit159, PairWithPosition.make(Lit151, PairWithPosition.make(Lit153, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3317784), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3317781), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3317770), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3317770), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309584), PairWithPosition.make(PairWithPosition.make((new SimpleSymbol("else")).readResolve(), PairWithPosition.make(Boolean.TRUE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3321871), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3321865), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3321865), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309584), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309578), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309578), PairWithPosition.make(PairWithPosition.make(Lit83, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3325959), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3325959) }0);
    Lit116 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule4, syntaxRule12 }, 3);
    simpleSymbol1 = Lit150;
    PairWithPosition pairWithPosition1 = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2916364), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2916364), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2916361), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2916360);
    SimpleSymbol simpleSymbol6 = Lit52;
    simpleSymbol10 = Lit149;
    simpleSymbol12 = (SimpleSymbol)(new SimpleSymbol("%test-comp2body")).readResolve();
    Lit89 = simpleSymbol12;
    SimpleSymbol simpleSymbol13 = (SimpleSymbol)(new SimpleSymbol("%test-approximimate=")).readResolve();
    Lit91 = simpleSymbol13;
    Lit114 = new SyntaxTemplate("\001\001\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b#\b\021\030$\021\030\034)\021\030,\b\033\t\013\b\023", new Object[] { simpleSymbol1, pairWithPosition1, simpleSymbol6, simpleSymbol10, simpleSymbol12, simpleSymbol13 }, 0);
    Lit113 = new SyntaxPattern("L\f\007\f\017\f\027\f\037\b\f'\b", new Object[0], 5);
    Lit112 = new SyntaxTemplate("\001\001\001\001\001\001", "\021\030\004I\021\030\f\b\021\030\024\b\013©\021\030\034\021\030$\b\021\030,A\021\030,\021\0304\b\013\b+\b\021\030<\021\030$)\021\030D\b#\t\023\b\033", new Object[] { Lit150, PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2891788), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2891788), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2891785), Lit160, Lit52, Lit149, Lit145, PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2900007), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2900007), Lit89, Lit91 }0);
    Lit111 = new SyntaxPattern("\\\f\007\f\017\f\027\f\037\f'\b\f/\b", new Object[0], 6);
    Lit110 = (SimpleSymbol)(new SimpleSymbol("test-approximate")).readResolve();
    Lit109 = new SyntaxTemplate("", "\030\004", new Object[] { (new SimpleSymbol("equal?")).readResolve() }, 0);
    Lit108 = (SimpleSymbol)(new SimpleSymbol("test-equal")).readResolve();
    Lit107 = new SyntaxTemplate("", "\030\004", new Object[] { (new SimpleSymbol("eq?")).readResolve() }, 0);
    Lit106 = (SimpleSymbol)(new SimpleSymbol("test-eq")).readResolve();
    Lit105 = new SyntaxTemplate("", "\030\004", new Object[] { (new SimpleSymbol("eqv?")).readResolve() }, 0);
    Lit104 = (SimpleSymbol)(new SimpleSymbol("test-eqv")).readResolve();
    simpleSymbol1 = Lit150;
    pairWithPosition1 = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2781198), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2781198), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2781195), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2781194);
    simpleSymbol6 = Lit52;
    simpleSymbol10 = Lit149;
    simpleSymbol12 = (SimpleSymbol)(new SimpleSymbol("%test-comp1body")).readResolve();
    Lit92 = simpleSymbol12;
    Lit103 = new SyntaxTemplate("\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b\023\b\021\030$\021\030\034\b\013", new Object[] { simpleSymbol1, pairWithPosition1, simpleSymbol6, simpleSymbol10, simpleSymbol12 }, 0);
    Lit102 = new SyntaxPattern(",\f\007\f\017\b\f\027\b", new Object[0], 3);
    Lit101 = new SyntaxTemplate("\001\001\001\001", "\021\030\004I\021\030\f\b\021\030\024\b\013©\021\030\034\021\030$\b\021\030,A\021\030,\021\0304\b\013\b\033\b\021\030<\021\030$\b\023", new Object[] { Lit150, PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2756622), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2756622), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2756619), Lit160, Lit52, Lit149, Lit145, PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2764841), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2764841), Lit92 }0);
    Lit100 = new SyntaxPattern("<\f\007\f\017\f\027\b\f\037\b", new Object[0], 4);
    Lit99 = (SimpleSymbol)(new SimpleSymbol("test-assert")).readResolve();
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("%test-end")).readResolve();
    Lit69 = simpleSymbol1;
    Lit98 = new SyntaxTemplate("\001\001", "\021\030\004\021\030\f\b\013", new Object[] { simpleSymbol1, Boolean.FALSE }, 0);
    Lit97 = new SyntaxPattern("\034\f\007\b\f\017\b", new Object[0], 2);
    Lit96 = new SyntaxTemplate("\001\001\001", "\021\030\004\t\013\b\023", new Object[] { Lit69 }, 0);
    Lit95 = new SyntaxPattern(",\f\007\f\017\b\f\027\b", new Object[0], 3);
    Lit94 = (SimpleSymbol)(new SimpleSymbol("test-end")).readResolve();
    simpleSymbol1 = Lit92;
    SyntaxPattern syntaxPattern3 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
    simpleSymbol6 = Lit144;
    simpleSymbol10 = Lit161;
    simpleSymbol12 = Lit86;
    simpleSymbol13 = Lit162;
    simpleSymbol15 = (SimpleSymbol)(new SimpleSymbol("%test-evaluate-with-catch")).readResolve();
    Lit84 = simpleSymbol15;
    SyntaxRule syntaxRule3 = new SyntaxRule(syntaxPattern3, "\001\001", "\021\030\004\t\020ű\021\030\f)\021\030\024\b\003\b\021\030\004\t\020\b\021\030\004Q\b\021\030\034\b\021\030$\b\0139\021\030,\t\003\0304\b\021\030<\t\003\030D\030L", new Object[] { simpleSymbol6, simpleSymbol10, simpleSymbol12, simpleSymbol13, simpleSymbol15, Lit78, PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2666526), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2666526), PairWithPosition.make(Lit162, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2666539), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2666525), Lit87, PairWithPosition.make(Lit162, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2670622), PairWithPosition.make(PairWithPosition.make(Lit83, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2674696), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2674696) }0);
    Lit93 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule3 }, 2);
    simpleSymbol1 = Lit89;
    syntaxRule3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\f\037\b", new Object[0], 4), "\001\001\001\001", "\021\030\004\t\020Ǳ\021\030\f)\021\030\024\b\003\b\021\030\0041\b\021\030\034\b\0239\021\030$\t\003\030,\b\021\030\004Q\b\021\0304\b\021\030<\b\0339\021\030$\t\003\030D\b\021\030L\t\003\b\t\013\030T\030\\", new Object[] { 
          Lit144, Lit161, Lit86, Lit163, Lit78, PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make((new SimpleSymbol("expected-value")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2592794), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2592794), PairWithPosition.make(Lit163, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2592809), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2592793), Lit162, Lit84, PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2600988), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2600988), PairWithPosition.make(Lit162, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2601001), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2600987), Lit87, 
          PairWithPosition.make(Lit163, PairWithPosition.make(Lit162, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2605094), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2605090), PairWithPosition.make(PairWithPosition.make(Lit83, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2609158), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2609158) }0);
    Lit90 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule3 }, 4);
    Lit88 = (SimpleSymbol)(new SimpleSymbol("test-runner-test-name")).readResolve();
    simpleSymbol1 = Lit84;
    syntaxRule3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\t\003\030\f", new Object[] { Lit154, PairWithPosition.make(PairWithPosition.make(Lit151, PairWithPosition.make(Lit156, PairWithPosition.make(PairWithPosition.make(Lit78, PairWithPosition.make(PairWithPosition.make(Lit146, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347035), PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit157, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347058), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347058), PairWithPosition.make(Lit151, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347071), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347057), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347035), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347017), PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2351113), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347017), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2342921), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2342917), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2342917) }0);
    Lit85 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule3 }, 1);
    Lit82 = (SimpleSymbol)(new SimpleSymbol("test-passed?")).readResolve();
    Lit81 = (SimpleSymbol)(new SimpleSymbol("test-result-kind")).readResolve();
    Lit80 = (SimpleSymbol)(new SimpleSymbol("test-result-remove")).readResolve();
    Lit79 = (SimpleSymbol)(new SimpleSymbol("test-result-clear")).readResolve();
    Lit77 = (SimpleSymbol)(new SimpleSymbol("test-on-test-end-simple")).readResolve();
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("test-result-ref")).readResolve();
    Lit75 = simpleSymbol1;
    syntaxRule3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\t\013\030\f", new Object[] { Lit75, PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1933348) }0);
    SyntaxPattern syntaxPattern10 = new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3);
    simpleSymbol10 = Lit144;
    simpleSymbol12 = Lit164;
    simpleSymbol13 = (SimpleSymbol)(new SimpleSymbol("assq")).readResolve();
    simpleSymbol15 = (SimpleSymbol)(new SimpleSymbol("test-result-alist")).readResolve();
    Lit51 = simpleSymbol15;
    SyntaxRule syntaxRule11 = new SyntaxRule(syntaxPattern10, "\001\001\001", "\021\030\004\b\021\030\f\b\021\030\024\t\013\b\021\030\034\b\003\b\021\030$\021\030\f\021\030,\b\023", new Object[] { simpleSymbol10, simpleSymbol12, simpleSymbol13, simpleSymbol15, Lit161, PairWithPosition.make((new SimpleSymbol("cdr")).readResolve(), PairWithPosition.make(Lit164, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1945619), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1945614) }0);
    Lit76 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule3, syntaxRule11 }, 3);
    Lit74 = (SimpleSymbol)(new SimpleSymbol("test-on-test-begin-simple")).readResolve();
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("test-group-with-cleanup")).readResolve();
    Lit72 = simpleSymbol1;
    SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3);
    SimpleSymbol simpleSymbol5 = (SimpleSymbol)(new SimpleSymbol("test-group")).readResolve();
    Lit70 = simpleSymbol5;
    SyntaxRule syntaxRule2 = new SyntaxRule(syntaxPattern2, "\001\001\001", "\021\030\004\t\003\b\021\030\f\021\030\0249\021\030\034\t\020\b\013\b\021\030\034\t\020\b\023", new Object[] { simpleSymbol5, Lit165, PairWithPosition.make(Lit147, PairWithPosition.make(LList.Empty, PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1826831), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1826828), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1826820), Lit147 }0);
    SyntaxRule syntaxRule10 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\021\030\f\b\013", new Object[] { Lit72, Boolean.FALSE }, 0);
    SyntaxRule syntaxRule14 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\f\037#", new Object[0], 5), "\001\001\001\001\000", "\021\030\004\t\0039\021\030\f\t\013\b\023\t\033\"", new Object[] { Lit72, (new SimpleSymbol("begin")).readResolve() }, 0);
    Lit73 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule2, syntaxRule10, syntaxRule14 }, 5);
    simpleSymbol1 = Lit70;
    SyntaxPattern syntaxPattern1 = new SyntaxPattern("\f\030\f\007\013", new Object[0], 2);
    SimpleSymbol simpleSymbol4 = Lit144;
    PairWithPosition pairWithPosition3 = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit146, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1769487), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1769487), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1769484), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1769483);
    simpleSymbol12 = Lit52;
    simpleSymbol13 = Lit149;
    simpleSymbol15 = (SimpleSymbol)(new SimpleSymbol("list")).readResolve();
    simpleSymbol16 = Lit145;
    PairWithPosition pairWithPosition6 = PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1777707), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1777707);
    SimpleSymbol simpleSymbol18 = Lit161;
    SimpleSymbol simpleSymbol20 = (SimpleSymbol)(new SimpleSymbol("%test-should-execute")).readResolve();
    Lit62 = simpleSymbol20;
    SyntaxRule syntaxRule1 = new SyntaxRule(syntaxPattern1, "\001\000", "\021\030\004\021\030\f\021\030\024\021\030\034\b\021\030$\b\021\030,\021\0304\b\003\b\021\030<\021\030D\b\021\030LY\021\030T\t\020\b\021\030\\\b\0031\021\030T\t\020\n\b\021\030T\t\020\b\021\030d\b\003", new Object[] { 
          simpleSymbol4, pairWithPosition3, simpleSymbol12, simpleSymbol13, simpleSymbol15, simpleSymbol16, pairWithPosition6, simpleSymbol18, PairWithPosition.make(simpleSymbol20, PairWithPosition.make(Lit149, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1781794), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1781772), Lit165, 
          Lit147, (new SimpleSymbol("test-begin")).readResolve(), Lit94 }0);
    Lit71 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1 }, 2);
    Lit68 = (SimpleSymbol)(new SimpleSymbol("test-on-final-simple")).readResolve();
    Lit67 = (SimpleSymbol)(new SimpleSymbol("test-on-bad-end-name-simple")).readResolve();
    Lit66 = (SimpleSymbol)(new SimpleSymbol("test-on-bad-count-simple")).readResolve();
    Lit65 = (SimpleSymbol)(new SimpleSymbol("test-on-group-end-simple")).readResolve();
    Lit64 = (SimpleSymbol)(new SimpleSymbol("test-on-group-begin-simple")).readResolve();
    Lit63 = (SimpleSymbol)(new SimpleSymbol("%test-begin")).readResolve();
    Lit61 = (SimpleSymbol)(new SimpleSymbol("test-runner-create")).readResolve();
    Lit59 = (SimpleSymbol)(new SimpleSymbol("test-runner-simple")).readResolve();
    Lit58 = (SimpleSymbol)(new SimpleSymbol("test-runner-null")).readResolve();
    Lit57 = (SimpleSymbol)(new SimpleSymbol("%test-null-callback")).readResolve();
    Lit56 = (SimpleSymbol)(new SimpleSymbol("test-runner-group-path")).readResolve();
    Lit55 = (SimpleSymbol)(new SimpleSymbol("test-runner-reset")).readResolve();
    Lit54 = (SimpleSymbol)(new SimpleSymbol("test-runner-aux-value!")).readResolve();
    Lit53 = (SimpleSymbol)(new SimpleSymbol("test-runner-aux-value")).readResolve();
    Lit50 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-bad-end-name!")).readResolve();
    Lit49 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-bad-end-name")).readResolve();
    Lit48 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-bad-count!")).readResolve();
    Lit47 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-bad-count")).readResolve();
    Lit46 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-final!")).readResolve();
    Lit45 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-final")).readResolve();
    Lit44 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-group-end!")).readResolve();
    Lit43 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-group-end")).readResolve();
    Lit42 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-group-begin!")).readResolve();
    Lit41 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-group-begin")).readResolve();
    Lit40 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-test-end!")).readResolve();
    Lit39 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-test-end")).readResolve();
    Lit38 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-test-begin!")).readResolve();
    Lit37 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-test-begin")).readResolve();
    Lit36 = (SimpleSymbol)(new SimpleSymbol("test-runner-group-stack!")).readResolve();
    Lit35 = (SimpleSymbol)(new SimpleSymbol("test-runner-group-stack")).readResolve();
    Lit30 = (SimpleSymbol)(new SimpleSymbol("test-runner-skip-count!")).readResolve();
    Lit29 = (SimpleSymbol)(new SimpleSymbol("test-runner-skip-count")).readResolve();
    Lit28 = (SimpleSymbol)(new SimpleSymbol("test-runner-xfail-count!")).readResolve();
    Lit27 = (SimpleSymbol)(new SimpleSymbol("test-runner-xfail-count")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("test-runner-xpass-count!")).readResolve();
    Lit25 = (SimpleSymbol)(new SimpleSymbol("test-runner-xpass-count")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("test-runner-fail-count!")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("test-runner-fail-count")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("test-runner-pass-count!")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("test-runner-pass-count")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("test-runner?")).readResolve();
    Lit19 = new SyntaxTemplate("\001\001\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b\033\b\021\030$\021\030\034\t#\t\013\b\023", new Object[] { Lit150, PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2834444), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2834444), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2834441), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2834440), Lit52, Lit149, Lit89 }0);
    Lit18 = new SyntaxPattern("<\f\007\f\017\f\027\b\f\037\f'\b", new Object[0], 5);
    Lit17 = new SyntaxTemplate("\001\001\001\001\001\001", "\021\030\004I\021\030\f\b\021\030\024\b\013©\021\030\034\021\030$\b\021\030,A\021\030,\021\0304\b\013\b#\b\021\030<\021\030$\t+\t\023\b\033", new Object[] { Lit150, PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2809868), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2809868), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2809865), Lit160, Lit52, Lit149, Lit145, PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2818087), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2818087), Lit89 }0);
    Lit16 = new SyntaxPattern("L\f\007\f\017\f\027\f\037\b\f'\f/\b", new Object[0], 6);
    Lit14 = (SimpleSymbol)(new SimpleSymbol("fail")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("pass")).readResolve();
    simpleSymbol1 = Lit12;
    SimpleSymbol simpleSymbol3 = (SimpleSymbol)(new SimpleSymbol("xpass")).readResolve();
    Lit9 = simpleSymbol3;
    Lit11 = PairWithPosition.make(simpleSymbol1, PairWithPosition.make(simpleSymbol3, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2220088), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2220082);
    simpleSymbol1 = Lit7;
    simpleSymbol3 = (SimpleSymbol)(new SimpleSymbol("source-file")).readResolve();
    Lit4 = simpleSymbol3;
    simpleSymbol4 = (SimpleSymbol)(new SimpleSymbol("source-line")).readResolve();
    Lit5 = simpleSymbol4;
    SimpleSymbol simpleSymbol9 = (SimpleSymbol)(new SimpleSymbol("source-form")).readResolve();
    Lit6 = simpleSymbol9;
    Lit10 = PairWithPosition.make(simpleSymbol1, PairWithPosition.make(simpleSymbol3, PairWithPosition.make(simpleSymbol4, PairWithPosition.make(simpleSymbol9, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2072618), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2072606), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2072594), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2072583);
    Lit8 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit9, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1966107), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1966101);
    Lit3 = (SimpleSymbol)(new SimpleSymbol("xfail")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("skip")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("result-kind")).readResolve();
    Lit0 = IntNum.make(0);
    $instance = new testing();
    test$Mnrunner = unner.class;
    testing testing1 = $instance;
    test$Mnrunner$Qu = new ModuleMethod(testing1, 12, Lit20, 4097);
    test$Mnrunner$Mnpass$Mncount = new ModuleMethod(testing1, 13, Lit21, 4097);
    test$Mnrunner$Mnpass$Mncount$Ex = new ModuleMethod(testing1, 14, Lit22, 8194);
    test$Mnrunner$Mnfail$Mncount = new ModuleMethod(testing1, 15, Lit23, 4097);
    test$Mnrunner$Mnfail$Mncount$Ex = new ModuleMethod(testing1, 16, Lit24, 8194);
    test$Mnrunner$Mnxpass$Mncount = new ModuleMethod(testing1, 17, Lit25, 4097);
    test$Mnrunner$Mnxpass$Mncount$Ex = new ModuleMethod(testing1, 18, Lit26, 8194);
    test$Mnrunner$Mnxfail$Mncount = new ModuleMethod(testing1, 19, Lit27, 4097);
    test$Mnrunner$Mnxfail$Mncount$Ex = new ModuleMethod(testing1, 20, Lit28, 8194);
    test$Mnrunner$Mnskip$Mncount = new ModuleMethod(testing1, 21, Lit29, 4097);
    test$Mnrunner$Mnskip$Mncount$Ex = new ModuleMethod(testing1, 22, Lit30, 8194);
    $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist = new ModuleMethod(testing1, 23, Lit31, 4097);
    $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist$Ex = new ModuleMethod(testing1, 24, Lit32, 8194);
    $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist = new ModuleMethod(testing1, 25, Lit33, 4097);
    $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist$Ex = new ModuleMethod(testing1, 26, Lit34, 8194);
    test$Mnrunner$Mngroup$Mnstack = new ModuleMethod(testing1, 27, Lit35, 4097);
    test$Mnrunner$Mngroup$Mnstack$Ex = new ModuleMethod(testing1, 28, Lit36, 8194);
    test$Mnrunner$Mnon$Mntest$Mnbegin = new ModuleMethod(testing1, 29, Lit37, 4097);
    test$Mnrunner$Mnon$Mntest$Mnbegin$Ex = new ModuleMethod(testing1, 30, Lit38, 8194);
    test$Mnrunner$Mnon$Mntest$Mnend = new ModuleMethod(testing1, 31, Lit39, 4097);
    test$Mnrunner$Mnon$Mntest$Mnend$Ex = new ModuleMethod(testing1, 32, Lit40, 8194);
    test$Mnrunner$Mnon$Mngroup$Mnbegin = new ModuleMethod(testing1, 33, Lit41, 4097);
    test$Mnrunner$Mnon$Mngroup$Mnbegin$Ex = new ModuleMethod(testing1, 34, Lit42, 8194);
    test$Mnrunner$Mnon$Mngroup$Mnend = new ModuleMethod(testing1, 35, Lit43, 4097);
    test$Mnrunner$Mnon$Mngroup$Mnend$Ex = new ModuleMethod(testing1, 36, Lit44, 8194);
    test$Mnrunner$Mnon$Mnfinal = new ModuleMethod(testing1, 37, Lit45, 4097);
    test$Mnrunner$Mnon$Mnfinal$Ex = new ModuleMethod(testing1, 38, Lit46, 8194);
    test$Mnrunner$Mnon$Mnbad$Mncount = new ModuleMethod(testing1, 39, Lit47, 4097);
    test$Mnrunner$Mnon$Mnbad$Mncount$Ex = new ModuleMethod(testing1, 40, Lit48, 8194);
    test$Mnrunner$Mnon$Mnbad$Mnend$Mnname = new ModuleMethod(testing1, 41, Lit49, 4097);
    test$Mnrunner$Mnon$Mnbad$Mnend$Mnname$Ex = new ModuleMethod(testing1, 42, Lit50, 8194);
    test$Mnresult$Mnalist = new ModuleMethod(testing1, 43, Lit51, 4097);
    test$Mnresult$Mnalist$Ex = new ModuleMethod(testing1, 44, Lit52, 8194);
    test$Mnrunner$Mnaux$Mnvalue = new ModuleMethod(testing1, 45, Lit53, 4097);
    test$Mnrunner$Mnaux$Mnvalue$Ex = new ModuleMethod(testing1, 46, Lit54, 8194);
    test$Mnrunner$Mnreset = new ModuleMethod(testing1, 47, Lit55, 4097);
    test$Mnrunner$Mngroup$Mnpath = new ModuleMethod(testing1, 48, Lit56, 4097);
    $Pctest$Mnnull$Mncallback = new ModuleMethod(testing1, 49, Lit57, 4097);
    ModuleMethod moduleMethod1 = new ModuleMethod(testing1, 50, null, 12291);
    moduleMethod1.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:182");
    lambda$Fn1 = moduleMethod1;
    moduleMethod1 = new ModuleMethod(testing1, 51, null, 12291);
    moduleMethod1.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:187");
    lambda$Fn2 = moduleMethod1;
    moduleMethod1 = new ModuleMethod(testing1, 52, null, 12291);
    moduleMethod1.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:188");
    lambda$Fn3 = moduleMethod1;
    test$Mnrunner$Mnnull = new ModuleMethod(testing1, 53, Lit58, 0);
    test$Mnrunner$Mnsimple = new ModuleMethod(testing1, 54, Lit59, 0);
    test$Mnrunner$Mnget = new ModuleMethod(testing1, 55, Lit60, 0);
    test$Mnrunner$Mncreate = new ModuleMethod(testing1, 56, Lit61, 0);
    $Prvt$$Pctest$Mnshould$Mnexecute = new ModuleMethod(testing1, 57, Lit62, 4097);
    $Pctest$Mnbegin = new ModuleMethod(testing1, 58, Lit63, 8194);
    test$Mnon$Mngroup$Mnbegin$Mnsimple = new ModuleMethod(testing1, 59, Lit64, 12291);
    test$Mnon$Mngroup$Mnend$Mnsimple = new ModuleMethod(testing1, 60, Lit65, 4097);
    test$Mnon$Mnbad$Mncount$Mnsimple = new ModuleMethod(testing1, 61, Lit66, 12291);
    test$Mnon$Mnbad$Mnend$Mnname$Mnsimple = new ModuleMethod(testing1, 62, Lit67, 12291);
    test$Mnon$Mnfinal$Mnsimple = new ModuleMethod(testing1, 63, Lit68, 4097);
    $Prvt$$Pctest$Mnend = new ModuleMethod(testing1, 64, Lit69, 8194);
    $Prvt$test$Mngroup = Macro.make(Lit70, (Procedure)Lit71, $instance);
    test$Mngroup$Mnwith$Mncleanup = Macro.make(Lit72, (Procedure)Lit73, $instance);
    test$Mnon$Mntest$Mnbegin$Mnsimple = new ModuleMethod(testing1, 65, Lit74, 4097);
    test$Mnresult$Mnref = Macro.make(Lit75, (Procedure)Lit76, $instance);
    test$Mnon$Mntest$Mnend$Mnsimple = new ModuleMethod(testing1, 66, Lit77, 4097);
    test$Mnresult$Mnset$Ex = new ModuleMethod(testing1, 67, Lit78, 12291);
    test$Mnresult$Mnclear = new ModuleMethod(testing1, 68, Lit79, 4097);
    test$Mnresult$Mnremove = new ModuleMethod(testing1, 69, Lit80, 8194);
    test$Mnresult$Mnkind = new ModuleMethod(testing1, 70, Lit81, -4096);
    test$Mnpassed$Qu = new ModuleMethod(testing1, 71, Lit82, -4096);
    $Prvt$$Pctest$Mnreport$Mnresult = new ModuleMethod(testing1, 72, Lit83, 0);
    $Prvt$$Pctest$Mnevaluate$Mnwith$Mncatch = Macro.make(Lit84, (Procedure)Lit85, $instance);
    $Prvt$$Pctest$Mnon$Mntest$Mnbegin = new ModuleMethod(testing1, 73, Lit86, 4097);
    $Prvt$$Pctest$Mnon$Mntest$Mnend = new ModuleMethod(testing1, 74, Lit87, 8194);
    test$Mnrunner$Mntest$Mnname = new ModuleMethod(testing1, 75, Lit88, 4097);
    $Prvt$$Pctest$Mncomp2body = Macro.make(Lit89, (Procedure)Lit90, $instance);
    $Prvt$$Pctest$Mnapproximimate$Eq = new ModuleMethod(testing1, 76, Lit91, 4097);
    $Prvt$$Pctest$Mncomp1body = Macro.make(Lit92, (Procedure)Lit93, $instance);
    SimpleSymbol simpleSymbol2 = Lit94;
    ModuleMethod moduleMethod2 = new ModuleMethod(testing1, 77, null, 4097);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:660");
    test$Mnend = Macro.make(simpleSymbol2, (Procedure)moduleMethod2, $instance);
    simpleSymbol2 = Lit99;
    moduleMethod2 = new ModuleMethod(testing1, 78, null, 4097);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:669");
    test$Mnassert = Macro.make(simpleSymbol2, (Procedure)moduleMethod2, $instance);
    simpleSymbol2 = Lit104;
    moduleMethod2 = new ModuleMethod(testing1, 79, null, 4097);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:696");
    test$Mneqv = Macro.make(simpleSymbol2, (Procedure)moduleMethod2, $instance);
    simpleSymbol2 = Lit106;
    moduleMethod2 = new ModuleMethod(testing1, 80, null, 4097);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:698");
    test$Mneq = Macro.make(simpleSymbol2, (Procedure)moduleMethod2, $instance);
    simpleSymbol2 = Lit108;
    moduleMethod2 = new ModuleMethod(testing1, 81, null, 4097);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:700");
    test$Mnequal = Macro.make(simpleSymbol2, (Procedure)moduleMethod2, $instance);
    simpleSymbol2 = Lit110;
    moduleMethod2 = new ModuleMethod(testing1, 82, null, 4097);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:702");
    test$Mnapproximate = Macro.make(simpleSymbol2, (Procedure)moduleMethod2, $instance);
    $Prvt$$Pctest$Mnerror = Macro.make(Lit115, (Procedure)Lit116, $instance);
    simpleSymbol2 = Lit117;
    moduleMethod2 = new ModuleMethod(testing1, 83, null, 4097);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:843");
    test$Mnerror = Macro.make(simpleSymbol2, (Procedure)moduleMethod2, $instance);
    test$Mnapply = new ModuleMethod(testing1, 84, Lit124, -4095);
    test$Mnwith$Mnrunner = Macro.make(Lit125, (Procedure)Lit126, $instance);
    $Prvt$$Pctest$Mnmatch$Mnnth = new ModuleMethod(testing1, 85, Lit127, 8194);
    test$Mnmatch$Mnnth = Macro.make(Lit128, (Procedure)Lit129, $instance);
    $Prvt$$Pctest$Mnmatch$Mnall = new ModuleMethod(testing1, 86, Lit130, -4096);
    test$Mnmatch$Mnall = Macro.make(Lit131, (Procedure)Lit132, $instance);
    $Prvt$$Pctest$Mnmatch$Mnany = new ModuleMethod(testing1, 87, Lit133, -4096);
    test$Mnmatch$Mnany = Macro.make(Lit134, (Procedure)Lit135, $instance);
    $Prvt$$Pctest$Mnas$Mnspecifier = new ModuleMethod(testing1, 88, Lit136, 4097);
    test$Mnskip = Macro.make(Lit137, (Procedure)Lit138, $instance);
    test$Mnexpect$Mnfail = Macro.make(Lit139, (Procedure)Lit140, $instance);
    test$Mnmatch$Mnname = new ModuleMethod(testing1, 89, Lit141, 4097);
    test$Mnread$Mneval$Mnstring = new ModuleMethod(testing1, 90, Lit142, 4097);
    $instance.run();
  }
  
  public testing() {
    ModuleInfo.register(this);
  }
  
  public static Object isTestPassed$V(Object[] paramArrayOfObject) {
    Object object = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(object)) {
      Object object1 = lists.car.apply1(object);
    } else {
      object = testRunnerGet();
    } 
    SimpleSymbol simpleSymbol = Lit1;
    try {
      unner unner = (unner)object;
      object = lists.assq(simpleSymbol, testResultAlist(unner));
      if (object != Boolean.FALSE) {
        object = lists.cdr.apply1(object);
        return lists.memq(object, Lit11);
      } 
      object = Boolean.FALSE;
      return lists.memq(object, Lit11);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-result-alist", 0, object);
    } 
  }
  
  public static boolean isTestRunner(Object paramObject) {
    return paramObject instanceof unner;
  }
  
  static Boolean lambda1(Object paramObject1, Object paramObject2, Object paramObject3) {
    return Boolean.FALSE;
  }
  
  static Object lambda16(Object paramObject) {
    TemplateScope templateScope;
    Pair pair = LList.list2(paramObject, LList.list2(Lit15, $PcTestSourceLine2(paramObject)));
    paramObject = SyntaxPattern.allocVars(3, null);
    if (Lit95.match(pair, (Object[])paramObject, 0)) {
      templateScope = TemplateScope.make();
      return Lit96.execute((Object[])paramObject, templateScope);
    } 
    if (Lit97.match(templateScope, (Object[])paramObject, 0)) {
      templateScope = TemplateScope.make();
      return Lit98.execute((Object[])paramObject, templateScope);
    } 
    return syntax_case.error("syntax-case", templateScope);
  }
  
  static Object lambda17(Object paramObject) {
    TemplateScope templateScope;
    Pair pair = LList.list2(paramObject, LList.list2(Lit15, $PcTestSourceLine2(paramObject)));
    paramObject = SyntaxPattern.allocVars(4, null);
    if (Lit100.match(pair, (Object[])paramObject, 0)) {
      templateScope = TemplateScope.make();
      return Lit101.execute((Object[])paramObject, templateScope);
    } 
    if (Lit102.match(templateScope, (Object[])paramObject, 0)) {
      templateScope = TemplateScope.make();
      return Lit103.execute((Object[])paramObject, templateScope);
    } 
    return syntax_case.error("syntax-case", templateScope);
  }
  
  static Object lambda18(Object paramObject) {
    TemplateScope templateScope = TemplateScope.make();
    return $PcTestComp2(Lit105.execute(null, templateScope), paramObject);
  }
  
  static Object lambda19(Object paramObject) {
    TemplateScope templateScope = TemplateScope.make();
    return $PcTestComp2(Lit107.execute(null, templateScope), paramObject);
  }
  
  static Boolean lambda2(Object paramObject1, Object paramObject2, Object paramObject3) {
    return Boolean.FALSE;
  }
  
  static Object lambda20(Object paramObject) {
    TemplateScope templateScope = TemplateScope.make();
    return $PcTestComp2(Lit109.execute(null, templateScope), paramObject);
  }
  
  static Object lambda21(Object paramObject) {
    TemplateScope templateScope;
    Pair pair = LList.list2(paramObject, LList.list2(Lit15, $PcTestSourceLine2(paramObject)));
    paramObject = SyntaxPattern.allocVars(6, null);
    if (Lit111.match(pair, (Object[])paramObject, 0)) {
      templateScope = TemplateScope.make();
      return Lit112.execute((Object[])paramObject, templateScope);
    } 
    if (Lit113.match(templateScope, (Object[])paramObject, 0)) {
      templateScope = TemplateScope.make();
      return Lit114.execute((Object[])paramObject, templateScope);
    } 
    return syntax_case.error("syntax-case", templateScope);
  }
  
  static Object lambda22(Object paramObject) {
    TemplateScope templateScope;
    Pair pair = LList.list2(paramObject, LList.list2(Lit15, $PcTestSourceLine2(paramObject)));
    paramObject = SyntaxPattern.allocVars(5, null);
    if (Lit118.match(pair, (Object[])paramObject, 0)) {
      templateScope = TemplateScope.make();
      return Lit119.execute((Object[])paramObject, templateScope);
    } 
    if (Lit120.match(templateScope, (Object[])paramObject, 0)) {
      templateScope = TemplateScope.make();
      return Lit121.execute((Object[])paramObject, templateScope);
    } 
    if (Lit122.match(templateScope, (Object[])paramObject, 0)) {
      templateScope = TemplateScope.make();
      return Lit123.execute((Object[])paramObject, templateScope);
    } 
    return syntax_case.error("syntax-case", templateScope);
  }
  
  static Boolean lambda3(Object paramObject1, Object paramObject2, Object paramObject3) {
    return Boolean.FALSE;
  }
  
  public static Object testApply$V(Object paramObject, Object[] paramArrayOfObject) {
    frame1 frame1 = new frame1();
    frame1.first = paramObject;
    frame1.rest = LList.makeList(paramArrayOfObject, 0);
    if (isTestRunner(frame1.first)) {
      frame1.saved$Mnrunner$1 = ((Procedure)test$Mnrunner$Mncurrent).apply0();
      return misc.dynamicWind(frame1.lambda$Fn5, frame1.lambda$Fn6, frame1.lambda$Fn7);
    } 
    Object object = ((Procedure)test$Mnrunner$Mncurrent).apply0();
    if (object != Boolean.FALSE)
      try {
        paramObject = object;
        Object object1 = $PcTestRunnerRunList((unner)paramObject);
        if (lists.isNull(frame1.rest))
          try {
            paramObject = object;
            try {
              object = object1;
              $PcTestRunnerRunList$Ex((unner)paramObject, lists.reverse$Ex((LList)object));
              return Scheme.applyToArgs.apply1(frame1.first);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "reverse!", 1, object1);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%test-runner-run-list!", 0, object);
          }  
        try {
          unner unner = (unner)object;
          if (object1 == Boolean.TRUE) {
            paramObject = LList.list1(frame1.first);
          } else {
            paramObject = lists.cons(frame1.first, object1);
          } 
          $PcTestRunnerRunList$Ex(unner, paramObject);
          Scheme.apply.apply2(test$Mnapply, frame1.rest);
          try {
            paramObject = object;
            $PcTestRunnerRunList$Ex((unner)paramObject, object1);
            return Values.empty;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%test-runner-run-list!", 0, object);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%test-runner-run-list!", 0, object);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "%test-runner-run-list", 0, object);
      }  
    frame1.r = testRunnerCreate();
    frame1.saved$Mnrunner = ((Procedure)test$Mnrunner$Mncurrent).apply0();
    misc.dynamicWind(frame1.lambda$Fn8, frame1.lambda$Fn9, frame1.lambda$Fn10);
    object = Scheme.applyToArgs;
    paramObject = frame1.r;
    try {
      unner unner = (unner)paramObject;
      return object.apply2(testRunnerOnFinal(unner), frame1.r);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-runner-on-final", 0, paramObject);
    } 
  }
  
  public static Procedure testMatchName(Object paramObject) {
    frame5 frame5 = new frame5();
    frame5.name = paramObject;
    return (Procedure)frame5.lambda$Fn14;
  }
  
  public static void testOnBadCountSimple(Object paramObject1, Object paramObject2, Object paramObject3) {
    $PcTestOnBadCountWrite(paramObject1, paramObject2, paramObject3, ports.current$Mnoutput$Mnport.apply0());
    try {
      unner unner = (unner)paramObject1;
      Object object = testRunnerAuxValue(unner);
      if (ports.isOutputPort(object))
        $PcTestOnBadCountWrite(paramObject1, paramObject2, paramObject3, object); 
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-runner-aux-value", 0, paramObject1);
    } 
  }
  
  public static Object testOnBadEndNameSimple(Object paramObject1, Object paramObject2, Object paramObject3) {
    return misc.error$V(strings.stringAppend(new Object[] { $PcTestFormatLine(paramObject1), "test-end ", paramObject2, " does not match test-begin ", paramObject3 }, ), new Object[0]);
  }
  
  public static void testOnFinalSimple(Object paramObject) {
    $PcTestFinalReportSimple(paramObject, ports.current$Mnoutput$Mnport.apply0());
    try {
      unner unner = (unner)paramObject;
      Object object = testRunnerAuxValue(unner);
      if (ports.isOutputPort(object))
        $PcTestFinalReportSimple(paramObject, object); 
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-runner-aux-value", 0, paramObject);
    } 
  }
  
  public static Boolean testOnGroupBeginSimple(Object paramObject1, Object paramObject2, Object paramObject3) {
    // Byte code:
    //   0: aload_0
    //   1: checkcast gnu/kawa/slib/test$Mnrunner
    //   4: astore_2
    //   5: aload_2
    //   6: invokestatic testRunnerGroupStack : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   9: invokestatic isNull : (Ljava/lang/Object;)Z
    //   12: ifeq -> 95
    //   15: ldc_w '%%%% Starting test '
    //   18: invokestatic display : (Ljava/lang/Object;)V
    //   21: aload_1
    //   22: invokestatic display : (Ljava/lang/Object;)V
    //   25: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   28: invokestatic isString : (Ljava/lang/Object;)Z
    //   31: ifeq -> 132
    //   34: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   37: astore_2
    //   38: aload_2
    //   39: invokestatic valueOf : (Ljava/lang/Object;)Lgnu/text/Path;
    //   42: astore_3
    //   43: aload_3
    //   44: invokestatic openOutputFile : (Lgnu/text/Path;)Lgnu/mapping/OutPort;
    //   47: astore_3
    //   48: ldc_w '%%%% Starting test '
    //   51: aload_3
    //   52: invokestatic display : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   55: aload_1
    //   56: aload_3
    //   57: invokestatic display : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   60: aload_3
    //   61: invokestatic newline : (Ljava/lang/Object;)V
    //   64: aload_0
    //   65: checkcast gnu/kawa/slib/test$Mnrunner
    //   68: astore #4
    //   70: aload #4
    //   72: aload_3
    //   73: invokestatic testRunnerAuxValue$Ex : (Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   76: ldc_w '  (Writing full log to "'
    //   79: invokestatic display : (Ljava/lang/Object;)V
    //   82: aload_2
    //   83: invokestatic display : (Ljava/lang/Object;)V
    //   86: ldc_w '")'
    //   89: invokestatic display : (Ljava/lang/Object;)V
    //   92: invokestatic newline : ()V
    //   95: aload_0
    //   96: checkcast gnu/kawa/slib/test$Mnrunner
    //   99: astore_2
    //   100: aload_2
    //   101: invokestatic testRunnerAuxValue : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   104: astore_0
    //   105: aload_0
    //   106: invokestatic isOutputPort : (Ljava/lang/Object;)Z
    //   109: ifeq -> 128
    //   112: ldc_w 'Group begin: '
    //   115: aload_0
    //   116: invokestatic display : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   119: aload_1
    //   120: aload_0
    //   121: invokestatic display : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   124: aload_0
    //   125: invokestatic newline : (Ljava/lang/Object;)V
    //   128: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   131: areturn
    //   132: iconst_2
    //   133: anewarray java/lang/Object
    //   136: dup
    //   137: iconst_0
    //   138: aload_1
    //   139: aastore
    //   140: dup
    //   141: iconst_1
    //   142: ldc_w '.log'
    //   145: aastore
    //   146: invokestatic stringAppend : ([Ljava/lang/Object;)Lgnu/lists/FString;
    //   149: astore_2
    //   150: goto -> 38
    //   153: astore_1
    //   154: new gnu/mapping/WrongType
    //   157: dup
    //   158: aload_1
    //   159: ldc_w 'test-runner-group-stack'
    //   162: iconst_0
    //   163: aload_0
    //   164: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   167: athrow
    //   168: astore_0
    //   169: new gnu/mapping/WrongType
    //   172: dup
    //   173: aload_0
    //   174: ldc_w 'open-output-file'
    //   177: iconst_1
    //   178: aload_2
    //   179: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   182: athrow
    //   183: astore_1
    //   184: new gnu/mapping/WrongType
    //   187: dup
    //   188: aload_1
    //   189: ldc_w 'test-runner-aux-value!'
    //   192: iconst_0
    //   193: aload_0
    //   194: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   197: athrow
    //   198: astore_1
    //   199: new gnu/mapping/WrongType
    //   202: dup
    //   203: aload_1
    //   204: ldc_w 'test-runner-aux-value'
    //   207: iconst_0
    //   208: aload_0
    //   209: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   212: athrow
    // Exception table:
    //   from	to	target	type
    //   0	5	153	java/lang/ClassCastException
    //   38	43	168	java/lang/ClassCastException
    //   64	70	183	java/lang/ClassCastException
    //   95	100	198	java/lang/ClassCastException
  }
  
  public static Boolean testOnGroupEndSimple(Object paramObject) {
    try {
      unner unner = (unner)paramObject;
      Object object = testRunnerAuxValue(unner);
      if (ports.isOutputPort(object)) {
        ports.display("Group end: ", object);
        GenericProc genericProc = lists.car;
        try {
          unner unner1 = (unner)paramObject;
          ports.display(genericProc.apply1(testRunnerGroupStack(unner1)), object);
          ports.newline(object);
          return Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-group-stack", 0, paramObject);
        } 
      } 
      return Boolean.FALSE;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-runner-aux-value", 0, paramObject);
    } 
  }
  
  static Object testOnTestBeginSimple(Object paramObject) {
    try {
      unner unner = (unner)paramObject;
      Object object = testRunnerAuxValue(unner);
      if (ports.isOutputPort(object))
        try {
          unner unner1 = (unner)paramObject;
          Object object3 = testResultAlist(unner1);
          paramObject = lists.assq(Lit4, object3);
          Object object1 = lists.assq(Lit5, object3);
          Object object2 = lists.assq(Lit6, object3);
          object3 = lists.assq(Lit7, object3);
          ports.display("Test begin:", object);
          ports.newline(object);
          if (object3 != Boolean.FALSE)
            $PcTestWriteResult1(object3, object); 
          if (paramObject != Boolean.FALSE)
            $PcTestWriteResult1(paramObject, object); 
          if (object1 != Boolean.FALSE)
            $PcTestWriteResult1(object1, object); 
          return (paramObject != Boolean.FALSE) ? $PcTestWriteResult1(object2, object) : Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-result-alist", 0, paramObject);
        }  
      return Values.empty;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-runner-aux-value", 0, paramObject);
    } 
  }
  
  public static Object testOnTestEndSimple(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: checkcast gnu/kawa/slib/test$Mnrunner
    //   4: astore_1
    //   5: aload_1
    //   6: invokestatic testRunnerAuxValue : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   9: astore_2
    //   10: getstatic gnu/kawa/slib/testing.Lit1 : Lgnu/mapping/SimpleSymbol;
    //   13: astore_1
    //   14: aload_0
    //   15: checkcast gnu/kawa/slib/test$Mnrunner
    //   18: astore_3
    //   19: aload_1
    //   20: aload_3
    //   21: invokestatic testResultAlist : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   24: invokestatic assq : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   27: astore_1
    //   28: aload_1
    //   29: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   32: if_acmpeq -> 278
    //   35: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   38: aload_1
    //   39: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   42: astore_1
    //   43: aload_1
    //   44: getstatic gnu/kawa/slib/testing.Lit8 : Lgnu/lists/PairWithPosition;
    //   47: invokestatic memq : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   50: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   53: if_acmpeq -> 199
    //   56: aload_0
    //   57: checkcast gnu/kawa/slib/test$Mnrunner
    //   60: astore_3
    //   61: aload_3
    //   62: invokestatic testResultAlist : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   65: astore_3
    //   66: getstatic gnu/kawa/slib/testing.Lit4 : Lgnu/mapping/SimpleSymbol;
    //   69: aload_3
    //   70: invokestatic assq : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   73: astore #4
    //   75: getstatic gnu/kawa/slib/testing.Lit5 : Lgnu/mapping/SimpleSymbol;
    //   78: aload_3
    //   79: invokestatic assq : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   82: astore #5
    //   84: getstatic gnu/kawa/slib/testing.Lit7 : Lgnu/mapping/SimpleSymbol;
    //   87: aload_3
    //   88: invokestatic assq : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   91: astore_3
    //   92: aload #4
    //   94: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   97: if_acmpne -> 108
    //   100: aload #5
    //   102: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   105: if_acmpeq -> 158
    //   108: aload #4
    //   110: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   113: if_acmpeq -> 127
    //   116: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   119: aload #4
    //   121: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   124: invokestatic display : (Ljava/lang/Object;)V
    //   127: ldc_w ':'
    //   130: invokestatic display : (Ljava/lang/Object;)V
    //   133: aload #5
    //   135: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   138: if_acmpeq -> 152
    //   141: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   144: aload #5
    //   146: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   149: invokestatic display : (Ljava/lang/Object;)V
    //   152: ldc_w ': '
    //   155: invokestatic display : (Ljava/lang/Object;)V
    //   158: aload_1
    //   159: getstatic gnu/kawa/slib/testing.Lit9 : Lgnu/mapping/SimpleSymbol;
    //   162: if_acmpne -> 285
    //   165: ldc_w 'XPASS'
    //   168: astore_1
    //   169: aload_1
    //   170: invokestatic display : (Ljava/lang/Object;)V
    //   173: aload_3
    //   174: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   177: if_acmpeq -> 196
    //   180: ldc_w ' '
    //   183: invokestatic display : (Ljava/lang/Object;)V
    //   186: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   189: aload_3
    //   190: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   193: invokestatic display : (Ljava/lang/Object;)V
    //   196: invokestatic newline : ()V
    //   199: aload_2
    //   200: invokestatic isOutputPort : (Ljava/lang/Object;)Z
    //   203: ifeq -> 296
    //   206: ldc_w 'Test end:'
    //   209: aload_2
    //   210: invokestatic display : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   213: aload_2
    //   214: invokestatic newline : (Ljava/lang/Object;)V
    //   217: aload_0
    //   218: checkcast gnu/kawa/slib/test$Mnrunner
    //   221: astore_1
    //   222: aload_1
    //   223: invokestatic testResultAlist : (Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   226: astore_0
    //   227: aload_0
    //   228: invokestatic isPair : (Ljava/lang/Object;)Z
    //   231: ifeq -> 292
    //   234: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   237: aload_0
    //   238: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   241: astore_1
    //   242: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   245: aload_1
    //   246: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   249: getstatic gnu/kawa/slib/testing.Lit10 : Lgnu/lists/PairWithPosition;
    //   252: invokestatic memq : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   255: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   258: if_acmpne -> 267
    //   261: aload_1
    //   262: aload_2
    //   263: invokestatic $PcTestWriteResult1 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   266: pop
    //   267: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   270: aload_0
    //   271: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   274: astore_0
    //   275: goto -> 227
    //   278: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   281: astore_1
    //   282: goto -> 43
    //   285: ldc_w 'FAIL'
    //   288: astore_1
    //   289: goto -> 169
    //   292: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   295: areturn
    //   296: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   299: areturn
    //   300: astore_1
    //   301: new gnu/mapping/WrongType
    //   304: dup
    //   305: aload_1
    //   306: ldc_w 'test-runner-aux-value'
    //   309: iconst_0
    //   310: aload_0
    //   311: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   314: athrow
    //   315: astore_1
    //   316: new gnu/mapping/WrongType
    //   319: dup
    //   320: aload_1
    //   321: ldc_w 'test-result-alist'
    //   324: iconst_0
    //   325: aload_0
    //   326: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   329: athrow
    //   330: astore_1
    //   331: new gnu/mapping/WrongType
    //   334: dup
    //   335: aload_1
    //   336: ldc_w 'test-result-alist'
    //   339: iconst_0
    //   340: aload_0
    //   341: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   344: athrow
    //   345: astore_1
    //   346: new gnu/mapping/WrongType
    //   349: dup
    //   350: aload_1
    //   351: ldc_w 'test-result-alist'
    //   354: iconst_0
    //   355: aload_0
    //   356: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   359: athrow
    // Exception table:
    //   from	to	target	type
    //   0	5	300	java/lang/ClassCastException
    //   14	19	315	java/lang/ClassCastException
    //   56	61	330	java/lang/ClassCastException
    //   217	222	345	java/lang/ClassCastException
  }
  
  public static Object testReadEvalString(Object paramObject) {
    try {
      CharSequence charSequence = (CharSequence)paramObject;
      paramObject = ports.openInputString(charSequence);
      Object object = ports.read((InPort)paramObject);
      return ports.isEofObject(readchar.readChar.apply1(paramObject)) ? Eval.eval.apply1(object) : misc.error$V("(not at eof)", new Object[0]);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "open-input-string", 1, paramObject);
    } 
  }
  
  public static Object testResultAlist(unner paramunner) {
    return paramunner.result$Mnalist;
  }
  
  public static void testResultAlist$Ex(unner paramunner, Object paramObject) {
    paramunner.result$Mnalist = paramObject;
  }
  
  public static void testResultClear(Object paramObject) {
    try {
      unner unner = (unner)paramObject;
      testResultAlist$Ex(unner, LList.Empty);
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-result-alist!", 0, paramObject);
    } 
  }
  
  public static Object testResultKind$V(Object[] paramArrayOfObject) {
    Object object = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(object)) {
      Object object1 = lists.car.apply1(object);
    } else {
      object = ((Procedure)test$Mnrunner$Mncurrent).apply0();
    } 
    SimpleSymbol simpleSymbol = Lit1;
    try {
      unner unner = (unner)object;
      object = lists.assq(simpleSymbol, testResultAlist(unner));
      return (object != Boolean.FALSE) ? lists.cdr.apply1(object) : Boolean.FALSE;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-result-alist", 0, object);
    } 
  }
  
  public static void testResultRemove(Object paramObject1, Object paramObject2) {
    frame frame = new frame();
    try {
      unner unner = (unner)paramObject1;
      Object object = testResultAlist(unner);
      frame.p = lists.assq(paramObject2, object);
      if (frame.p != Boolean.FALSE)
        try {
          paramObject2 = paramObject1;
          testResultAlist$Ex((unner)paramObject2, frame.lambda4loop(object));
          return;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-result-alist!", 0, paramObject1);
        }  
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-result-alist", 0, paramObject1);
    } 
  }
  
  public static Object testResultSet$Ex(Object paramObject1, Object paramObject2, Object paramObject3) {
    try {
      unner unner = (unner)paramObject1;
      Object object2 = testResultAlist(unner);
      Object object1 = lists.assq(paramObject2, object2);
      if (object1 != Boolean.FALSE)
        try {
          paramObject1 = object1;
          lists.setCdr$Ex((Pair)paramObject1, paramObject3);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-cdr!", 1, object1);
        }  
      try {
        object1 = classCastException;
        testResultAlist$Ex((unner)object1, lists.cons(lists.cons(paramObject2, paramObject3), object2));
        return Values.empty;
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "test-result-alist!", 0, classCastException);
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "test-result-alist", 0, classCastException);
    } 
  }
  
  public static Object testRunnerAuxValue(unner paramunner) {
    return paramunner.aux$Mnvalue;
  }
  
  public static void testRunnerAuxValue$Ex(unner paramunner, Object paramObject) {
    paramunner.aux$Mnvalue = paramObject;
  }
  
  public static Object testRunnerCreate() {
    return Scheme.applyToArgs.apply1(((Procedure)test$Mnrunner$Mnfactory).apply0());
  }
  
  public static Object testRunnerFailCount(unner paramunner) {
    return paramunner.fail$Mncount;
  }
  
  public static void testRunnerFailCount$Ex(unner paramunner, Object paramObject) {
    paramunner.fail$Mncount = paramObject;
  }
  
  public static Object testRunnerGet() {
    Object object = ((Procedure)test$Mnrunner$Mncurrent).apply0();
    if (object == Boolean.FALSE)
      misc.error$V("test-runner not initialized - test-begin missing?", new Object[0]); 
    return object;
  }
  
  public static LList testRunnerGroupPath(Object paramObject) {
    try {
      unner unner = (unner)paramObject;
      paramObject = testRunnerGroupStack(unner);
      try {
        LList lList = (LList)paramObject;
        return lists.reverse(lList);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "reverse", 1, paramObject);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-runner-group-stack", 0, paramObject);
    } 
  }
  
  public static Object testRunnerGroupStack(unner paramunner) {
    return paramunner.group$Mnstack;
  }
  
  public static void testRunnerGroupStack$Ex(unner paramunner, Object paramObject) {
    paramunner.group$Mnstack = paramObject;
  }
  
  public static unner testRunnerNull() {
    unner unner = $PcTestRunnerAlloc();
    testRunnerReset(unner);
    testRunnerOnGroupBegin$Ex(unner, lambda$Fn1);
    testRunnerOnGroupEnd$Ex(unner, $Pctest$Mnnull$Mncallback);
    testRunnerOnFinal$Ex(unner, $Pctest$Mnnull$Mncallback);
    testRunnerOnTestBegin$Ex(unner, $Pctest$Mnnull$Mncallback);
    testRunnerOnTestEnd$Ex(unner, $Pctest$Mnnull$Mncallback);
    testRunnerOnBadCount$Ex(unner, lambda$Fn2);
    testRunnerOnBadEndName$Ex(unner, lambda$Fn3);
    return unner;
  }
  
  public static Object testRunnerOnBadCount(unner paramunner) {
    return paramunner.on$Mnbad$Mncount;
  }
  
  public static void testRunnerOnBadCount$Ex(unner paramunner, Object paramObject) {
    paramunner.on$Mnbad$Mncount = paramObject;
  }
  
  public static Object testRunnerOnBadEndName(unner paramunner) {
    return paramunner.on$Mnbad$Mnend$Mnname;
  }
  
  public static void testRunnerOnBadEndName$Ex(unner paramunner, Object paramObject) {
    paramunner.on$Mnbad$Mnend$Mnname = paramObject;
  }
  
  public static Object testRunnerOnFinal(unner paramunner) {
    return paramunner.on$Mnfinal;
  }
  
  public static void testRunnerOnFinal$Ex(unner paramunner, Object paramObject) {
    paramunner.on$Mnfinal = paramObject;
  }
  
  public static Object testRunnerOnGroupBegin(unner paramunner) {
    return paramunner.on$Mngroup$Mnbegin;
  }
  
  public static void testRunnerOnGroupBegin$Ex(unner paramunner, Object paramObject) {
    paramunner.on$Mngroup$Mnbegin = paramObject;
  }
  
  public static Object testRunnerOnGroupEnd(unner paramunner) {
    return paramunner.on$Mngroup$Mnend;
  }
  
  public static void testRunnerOnGroupEnd$Ex(unner paramunner, Object paramObject) {
    paramunner.on$Mngroup$Mnend = paramObject;
  }
  
  public static Object testRunnerOnTestBegin(unner paramunner) {
    return paramunner.on$Mntest$Mnbegin;
  }
  
  public static void testRunnerOnTestBegin$Ex(unner paramunner, Object paramObject) {
    paramunner.on$Mntest$Mnbegin = paramObject;
  }
  
  public static Object testRunnerOnTestEnd(unner paramunner) {
    return paramunner.on$Mntest$Mnend;
  }
  
  public static void testRunnerOnTestEnd$Ex(unner paramunner, Object paramObject) {
    paramunner.on$Mntest$Mnend = paramObject;
  }
  
  public static Object testRunnerPassCount(unner paramunner) {
    return paramunner.pass$Mncount;
  }
  
  public static void testRunnerPassCount$Ex(unner paramunner, Object paramObject) {
    paramunner.pass$Mncount = paramObject;
  }
  
  public static void testRunnerReset(Object paramObject) {
    try {
      unner unner = (unner)paramObject;
      testResultAlist$Ex(unner, LList.Empty);
      try {
        unner = (unner)paramObject;
        testRunnerPassCount$Ex(unner, Lit0);
        try {
          unner = (unner)paramObject;
          testRunnerFailCount$Ex(unner, Lit0);
          try {
            unner = (unner)paramObject;
            testRunnerXpassCount$Ex(unner, Lit0);
            try {
              unner = (unner)paramObject;
              testRunnerXfailCount$Ex(unner, Lit0);
              try {
                unner = (unner)paramObject;
                testRunnerSkipCount$Ex(unner, Lit0);
                try {
                  unner = (unner)paramObject;
                  $PcTestRunnerTotalCount$Ex(unner, Lit0);
                  try {
                    unner = (unner)paramObject;
                    $PcTestRunnerCountList$Ex(unner, LList.Empty);
                    try {
                      unner = (unner)paramObject;
                      $PcTestRunnerRunList$Ex(unner, Boolean.TRUE);
                      try {
                        unner = (unner)paramObject;
                        $PcTestRunnerSkipList$Ex(unner, LList.Empty);
                        try {
                          unner = (unner)paramObject;
                          $PcTestRunnerFailList$Ex(unner, LList.Empty);
                          try {
                            unner = (unner)paramObject;
                            $PcTestRunnerSkipSave$Ex(unner, LList.Empty);
                            try {
                              unner = (unner)paramObject;
                              $PcTestRunnerFailSave$Ex(unner, LList.Empty);
                              try {
                                unner = (unner)paramObject;
                                testRunnerGroupStack$Ex(unner, LList.Empty);
                                return;
                              } catch (ClassCastException classCastException) {
                                throw new WrongType(classCastException, "test-runner-group-stack!", 0, paramObject);
                              } 
                            } catch (ClassCastException classCastException) {
                              throw new WrongType(classCastException, "%test-runner-fail-save!", 0, paramObject);
                            } 
                          } catch (ClassCastException classCastException) {
                            throw new WrongType(classCastException, "%test-runner-skip-save!", 0, paramObject);
                          } 
                        } catch (ClassCastException classCastException) {
                          throw new WrongType(classCastException, "%test-runner-fail-list!", 0, paramObject);
                        } 
                      } catch (ClassCastException classCastException) {
                        throw new WrongType(classCastException, "%test-runner-skip-list!", 0, paramObject);
                      } 
                    } catch (ClassCastException classCastException) {
                      throw new WrongType(classCastException, "%test-runner-run-list!", 0, paramObject);
                    } 
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "%test-runner-count-list!", 0, paramObject);
                  } 
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "%test-runner-total-count!", 0, paramObject);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "test-runner-skip-count!", 0, paramObject);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "test-runner-xfail-count!", 0, paramObject);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "test-runner-xpass-count!", 0, paramObject);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-fail-count!", 0, paramObject);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "test-runner-pass-count!", 0, paramObject);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-result-alist!", 0, paramObject);
    } 
  }
  
  public static unner testRunnerSimple() {
    unner unner = $PcTestRunnerAlloc();
    testRunnerReset(unner);
    testRunnerOnGroupBegin$Ex(unner, test$Mnon$Mngroup$Mnbegin$Mnsimple);
    testRunnerOnGroupEnd$Ex(unner, test$Mnon$Mngroup$Mnend$Mnsimple);
    testRunnerOnFinal$Ex(unner, test$Mnon$Mnfinal$Mnsimple);
    testRunnerOnTestBegin$Ex(unner, test$Mnon$Mntest$Mnbegin$Mnsimple);
    testRunnerOnTestEnd$Ex(unner, test$Mnon$Mntest$Mnend$Mnsimple);
    testRunnerOnBadCount$Ex(unner, test$Mnon$Mnbad$Mncount$Mnsimple);
    testRunnerOnBadEndName$Ex(unner, test$Mnon$Mnbad$Mnend$Mnname$Mnsimple);
    return unner;
  }
  
  public static Object testRunnerSkipCount(unner paramunner) {
    return paramunner.skip$Mncount;
  }
  
  public static void testRunnerSkipCount$Ex(unner paramunner, Object paramObject) {
    paramunner.skip$Mncount = paramObject;
  }
  
  public static Object testRunnerTestName(Object paramObject) {
    SimpleSymbol simpleSymbol = Lit7;
    try {
      unner unner = (unner)paramObject;
      paramObject = lists.assq(simpleSymbol, testResultAlist(unner));
      return (paramObject != Boolean.FALSE) ? lists.cdr.apply1(paramObject) : "";
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "test-result-alist", 0, paramObject);
    } 
  }
  
  public static Object testRunnerXfailCount(unner paramunner) {
    return paramunner.xfail$Mncount;
  }
  
  public static void testRunnerXfailCount$Ex(unner paramunner, Object paramObject) {
    paramunner.xfail$Mncount = paramObject;
  }
  
  public static Object testRunnerXpassCount(unner paramunner) {
    return paramunner.xpass$Mncount;
  }
  
  public static void testRunnerXpassCount$Ex(unner paramunner, Object paramObject) {
    paramunner.xpass$Mncount = paramObject;
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply0(paramModuleMethod);
      case 53:
        return testRunnerNull();
      case 54:
        return testRunnerSimple();
      case 55:
        return testRunnerGet();
      case 56:
        return testRunnerCreate();
      case 72:
        break;
    } 
    return $PcTestReportResult();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 12:
        return isTestRunner(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 13:
        try {
          unner unner = (unner)paramObject;
          return testRunnerPassCount(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-pass-count", 1, paramObject);
        } 
      case 15:
        try {
          unner unner = (unner)paramObject;
          return testRunnerFailCount(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-fail-count", 1, paramObject);
        } 
      case 17:
        try {
          unner unner = (unner)paramObject;
          return testRunnerXpassCount(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-xpass-count", 1, paramObject);
        } 
      case 19:
        try {
          unner unner = (unner)paramObject;
          return testRunnerXfailCount(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-xfail-count", 1, paramObject);
        } 
      case 21:
        try {
          unner unner = (unner)paramObject;
          return testRunnerSkipCount(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-skip-count", 1, paramObject);
        } 
      case 23:
        try {
          unner unner = (unner)paramObject;
          return $PcTestRunnerSkipList(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%test-runner-skip-list", 1, paramObject);
        } 
      case 25:
        try {
          unner unner = (unner)paramObject;
          return $PcTestRunnerFailList(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%test-runner-fail-list", 1, paramObject);
        } 
      case 27:
        try {
          unner unner = (unner)paramObject;
          return testRunnerGroupStack(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-group-stack", 1, paramObject);
        } 
      case 29:
        try {
          unner unner = (unner)paramObject;
          return testRunnerOnTestBegin(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-test-begin", 1, paramObject);
        } 
      case 31:
        try {
          unner unner = (unner)paramObject;
          return testRunnerOnTestEnd(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-test-end", 1, paramObject);
        } 
      case 33:
        try {
          unner unner = (unner)paramObject;
          return testRunnerOnGroupBegin(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-group-begin", 1, paramObject);
        } 
      case 35:
        try {
          unner unner = (unner)paramObject;
          return testRunnerOnGroupEnd(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-group-end", 1, paramObject);
        } 
      case 37:
        try {
          unner unner = (unner)paramObject;
          return testRunnerOnFinal(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-final", 1, paramObject);
        } 
      case 39:
        try {
          unner unner = (unner)paramObject;
          return testRunnerOnBadCount(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-bad-count", 1, paramObject);
        } 
      case 41:
        try {
          unner unner = (unner)paramObject;
          return testRunnerOnBadEndName(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-bad-end-name", 1, paramObject);
        } 
      case 43:
        try {
          unner unner = (unner)paramObject;
          return testResultAlist(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-result-alist", 1, paramObject);
        } 
      case 45:
        try {
          unner unner = (unner)paramObject;
          return testRunnerAuxValue(unner);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-aux-value", 1, paramObject);
        } 
      case 47:
        testRunnerReset(paramObject);
        return Values.empty;
      case 48:
        return testRunnerGroupPath(paramObject);
      case 49:
        return $PcTestNullCallback(paramObject);
      case 57:
        return $PcTestShouldExecute(paramObject);
      case 60:
        return testOnGroupEndSimple(paramObject);
      case 63:
        testOnFinalSimple(paramObject);
        return Values.empty;
      case 65:
        return testOnTestBeginSimple(paramObject);
      case 66:
        return testOnTestEndSimple(paramObject);
      case 68:
        testResultClear(paramObject);
        return Values.empty;
      case 73:
        return $PcTestOnTestBegin(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 75:
        return testRunnerTestName(paramObject);
      case 76:
        return $PcTestApproximimate$Eq(paramObject);
      case 88:
        return $PcTestAsSpecifier(paramObject);
      case 89:
        return testMatchName(paramObject);
      case 90:
        return testReadEvalString(paramObject);
      case 77:
        return lambda16(paramObject);
      case 78:
        return lambda17(paramObject);
      case 79:
        return lambda18(paramObject);
      case 80:
        return lambda19(paramObject);
      case 81:
        return lambda20(paramObject);
      case 82:
        return lambda21(paramObject);
      case 83:
        break;
    } 
    return lambda22(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 14:
        try {
          unner unner = (unner)paramObject1;
          testRunnerPassCount$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-pass-count!", 1, paramObject1);
        } 
      case 16:
        try {
          unner unner = (unner)paramObject1;
          testRunnerFailCount$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-fail-count!", 1, paramObject1);
        } 
      case 18:
        try {
          unner unner = (unner)paramObject1;
          testRunnerXpassCount$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-xpass-count!", 1, paramObject1);
        } 
      case 20:
        try {
          unner unner = (unner)paramObject1;
          testRunnerXfailCount$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-xfail-count!", 1, paramObject1);
        } 
      case 22:
        try {
          unner unner = (unner)paramObject1;
          testRunnerSkipCount$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-skip-count!", 1, paramObject1);
        } 
      case 24:
        try {
          unner unner = (unner)paramObject1;
          $PcTestRunnerSkipList$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%test-runner-skip-list!", 1, paramObject1);
        } 
      case 26:
        try {
          unner unner = (unner)paramObject1;
          $PcTestRunnerFailList$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%test-runner-fail-list!", 1, paramObject1);
        } 
      case 28:
        try {
          unner unner = (unner)paramObject1;
          testRunnerGroupStack$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-group-stack!", 1, paramObject1);
        } 
      case 30:
        try {
          unner unner = (unner)paramObject1;
          testRunnerOnTestBegin$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-test-begin!", 1, paramObject1);
        } 
      case 32:
        try {
          unner unner = (unner)paramObject1;
          testRunnerOnTestEnd$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-test-end!", 1, paramObject1);
        } 
      case 34:
        try {
          unner unner = (unner)paramObject1;
          testRunnerOnGroupBegin$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-group-begin!", 1, paramObject1);
        } 
      case 36:
        try {
          unner unner = (unner)paramObject1;
          testRunnerOnGroupEnd$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-group-end!", 1, paramObject1);
        } 
      case 38:
        try {
          unner unner = (unner)paramObject1;
          testRunnerOnFinal$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-final!", 1, paramObject1);
        } 
      case 40:
        try {
          unner unner = (unner)paramObject1;
          testRunnerOnBadCount$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-bad-count!", 1, paramObject1);
        } 
      case 42:
        try {
          unner unner = (unner)paramObject1;
          testRunnerOnBadEndName$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-on-bad-end-name!", 1, paramObject1);
        } 
      case 44:
        try {
          unner unner = (unner)paramObject1;
          testResultAlist$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-result-alist!", 1, paramObject1);
        } 
      case 46:
        try {
          unner unner = (unner)paramObject1;
          testRunnerAuxValue$Ex(unner, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "test-runner-aux-value!", 1, paramObject1);
        } 
      case 58:
        $PcTestBegin(paramObject1, paramObject2);
        return Values.empty;
      case 64:
        return $PcTestEnd(paramObject1, paramObject2);
      case 69:
        testResultRemove(paramObject1, paramObject2);
        return Values.empty;
      case 74:
        return $PcTestOnTestEnd(paramObject1, paramObject2);
      case 85:
        break;
    } 
    return $PcTestMatchNth(paramObject1, paramObject2);
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 50:
        return lambda1(paramObject1, paramObject2, paramObject3);
      case 51:
        return lambda2(paramObject1, paramObject2, paramObject3);
      case 52:
        return lambda3(paramObject1, paramObject2, paramObject3);
      case 59:
        return testOnGroupBeginSimple(paramObject1, paramObject2, paramObject3);
      case 61:
        testOnBadCountSimple(paramObject1, paramObject2, paramObject3);
        return Values.empty;
      case 62:
        return testOnBadEndNameSimple(paramObject1, paramObject2, paramObject3);
      case 67:
        break;
    } 
    return testResultSet$Ex(paramObject1, paramObject2, paramObject3);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    Object object;
    int i;
    Object[] arrayOfObject;
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 70:
        return testResultKind$V(paramArrayOfObject);
      case 71:
        return isTestPassed$V(paramArrayOfObject);
      case 84:
        object = paramArrayOfObject[0];
        i = paramArrayOfObject.length - 1;
        arrayOfObject = new Object[i];
        while (true) {
          if (--i < 0)
            return testApply$V(object, arrayOfObject); 
          arrayOfObject[i] = paramArrayOfObject[i + 1];
        } 
      case 86:
        return $PcTestMatchAll$V(paramArrayOfObject);
      case 87:
        break;
    } 
    return $PcTestMatchAny$V(paramArrayOfObject);
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match0(paramModuleMethod, paramCallContext);
      case 72:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 56:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 55:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 54:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 53:
        break;
    } 
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 0;
    return 0;
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 83:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 82:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 81:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 80:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 79:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 78:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 77:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 90:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 89:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 88:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 76:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 75:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 73:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 68:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 66:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 65:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 63:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 60:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 57:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 49:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 48:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 47:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 45:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 43:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 41:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 39:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 37:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 35:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 33:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 31:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 29:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 27:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 25:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 23:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 21:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 19:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 17:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 15:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 13:
        if (!(paramObject instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 12:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 85:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 74:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 69:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 64:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 58:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 46:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 44:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 42:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 40:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 38:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 36:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 34:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 32:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 30:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 28:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 26:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 24:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 22:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 20:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 18:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 16:
        if (!(paramObject1 instanceof unner))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 14:
        break;
    } 
    if (!(paramObject1 instanceof unner))
      return -786431; 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
      case 67:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 62:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 61:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 59:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 52:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 51:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 50:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.value3 = paramObject3;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 3;
    return 0;
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 87:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 86:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 84:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 71:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 70:
        break;
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 5;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    test$Mnlog$Mnto$Mnfile = Boolean.TRUE;
    test$Mnrunner$Mncurrent = parameters.makeParameter(Boolean.FALSE);
    test$Mnrunner$Mnfactory = parameters.makeParameter(test$Mnrunner$Mnsimple);
  }
  
  public class unner {
    public Object aux$Mnvalue;
    
    public Object count$Mnlist;
    
    public Object fail$Mncount;
    
    public Object fail$Mnlist;
    
    public Object fail$Mnsave;
    
    public Object group$Mnstack;
    
    public Object on$Mnbad$Mncount;
    
    public Object on$Mnbad$Mnend$Mnname;
    
    public Object on$Mnfinal;
    
    public Object on$Mngroup$Mnbegin;
    
    public Object on$Mngroup$Mnend;
    
    public Object on$Mntest$Mnbegin;
    
    public Object on$Mntest$Mnend;
    
    public Object pass$Mncount;
    
    public Object result$Mnalist;
    
    public Object run$Mnlist;
    
    public Object skip$Mncount;
    
    public Object skip$Mnlist;
    
    public Object skip$Mnsave;
    
    public Object total$Mncount;
    
    public Object xfail$Mncount;
    
    public Object xpass$Mncount;
  }
  
  public class frame extends ModuleBody {
    Object p;
    
    public Object lambda4loop(Object param1Object) {
      return (param1Object == this.p) ? lists.cdr.apply1(param1Object) : lists.cons(lists.car.apply1(param1Object), lambda4loop(lists.cdr.apply1(param1Object)));
    }
  }
  
  public class frame0 extends ModuleBody {
    Object error;
    
    final ModuleMethod lambda$Fn4;
    
    public frame0() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:640");
      this.lambda$Fn4 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 1) ? (lambda5(param1Object1, param1Object2) ? Boolean.TRUE : Boolean.FALSE) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    boolean lambda5(Object param1Object1, Object param1Object2) {
      Object object = Scheme.numGEq.apply2(param1Object1, AddOp.$Mn.apply2(param1Object2, this.error));
      try {
        boolean bool1 = ((Boolean)object).booleanValue();
        boolean bool2 = bool1;
        if (bool1)
          bool2 = ((Boolean)Scheme.numLEq.apply2(param1Object1, AddOp.$Pl.apply2(param1Object2, this.error))).booleanValue(); 
        return bool2;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "x", -2, object);
      } 
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 1) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame1 extends ModuleBody {
    Object first;
    
    final ModuleMethod lambda$Fn10;
    
    final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 2, null, 0);
    
    final ModuleMethod lambda$Fn6 = new ModuleMethod(this, 3, null, 0);
    
    final ModuleMethod lambda$Fn7;
    
    final ModuleMethod lambda$Fn8;
    
    final ModuleMethod lambda$Fn9;
    
    Object r;
    
    LList rest;
    
    Object saved$Mnrunner;
    
    Object saved$Mnrunner$1;
    
    public frame1() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 0);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:897");
      this.lambda$Fn7 = moduleMethod;
      this.lambda$Fn8 = new ModuleMethod(this, 5, null, 0);
      this.lambda$Fn9 = new ModuleMethod(this, 6, null, 0);
      moduleMethod = new ModuleMethod(this, 7, null, 0);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:897");
      this.lambda$Fn10 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply0(param1ModuleMethod);
        case 2:
          return lambda6();
        case 3:
          return lambda7();
        case 4:
          return lambda8();
        case 5:
          return lambda9();
        case 6:
          return lambda10();
        case 7:
          break;
      } 
      return lambda11();
    }
    
    Object lambda10() {
      return Scheme.apply.apply3(testing.test$Mnapply, this.first, this.rest);
    }
    
    Object lambda11() {
      return ((Procedure)testing.test$Mnrunner$Mncurrent).apply1(this.saved$Mnrunner);
    }
    
    Object lambda6() {
      return ((Procedure)testing.test$Mnrunner$Mncurrent).apply1(this.first);
    }
    
    Object lambda7() {
      return Scheme.apply.apply2(testing.test$Mnapply, this.rest);
    }
    
    Object lambda8() {
      return ((Procedure)testing.test$Mnrunner$Mncurrent).apply1(this.saved$Mnrunner$1);
    }
    
    Object lambda9() {
      return ((Procedure)testing.test$Mnrunner$Mncurrent).apply1(this.r);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match0(param1ModuleMethod, param1CallContext);
        case 7:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 6:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 5:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 4:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 3:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 2:
          break;
      } 
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 0;
      return 0;
    }
  }
  
  public class frame2 extends ModuleBody {
    Object count;
    
    Object i;
    
    final ModuleMethod lambda$Fn11;
    
    Object n;
    
    public frame2() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 8, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:903");
      this.lambda$Fn11 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 8) ? (lambda12(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda12(Object param1Object) {
      this.i = AddOp.$Pl.apply2(this.i, testing.Lit13);
      param1Object = Scheme.numGEq.apply2(this.i, this.n);
      try {
        boolean bool1 = ((Boolean)param1Object).booleanValue();
        boolean bool2 = bool1;
        if (bool1)
          bool2 = ((Boolean)Scheme.numLss.apply2(this.i, AddOp.$Pl.apply2(this.n, this.count))).booleanValue(); 
        return bool2;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "x", -2, param1Object);
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
  }
  
  public class frame3 extends ModuleBody {
    final ModuleMethod lambda$Fn12;
    
    LList pred$Mnlist;
    
    public frame3() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 9, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:915");
      this.lambda$Fn12 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 9) ? lambda13(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda13(Object param1Object) {
      Boolean bool = Boolean.TRUE;
      LList lList = this.pred$Mnlist;
      while (true) {
        if (lists.isNull(lList))
          return bool; 
        if (Scheme.applyToArgs.apply2(lists.car.apply1(lList), param1Object) == Boolean.FALSE)
          bool = Boolean.FALSE; 
        Object object = lists.cdr.apply1(lList);
      } 
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 9) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame4 extends ModuleBody {
    final ModuleMethod lambda$Fn13;
    
    LList pred$Mnlist;
    
    public frame4() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 10, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:931");
      this.lambda$Fn13 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 10) ? lambda14(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda14(Object param1Object) {
      Boolean bool = Boolean.FALSE;
      LList lList = this.pred$Mnlist;
      while (true) {
        if (lists.isNull(lList))
          return bool; 
        if (Scheme.applyToArgs.apply2(lists.car.apply1(lList), param1Object) != Boolean.FALSE)
          bool = Boolean.TRUE; 
        Object object = lists.cdr.apply1(lList);
      } 
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 10) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame5 extends ModuleBody {
    final ModuleMethod lambda$Fn14;
    
    Object name;
    
    public frame5() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 11, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:971");
      this.lambda$Fn14 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 11) ? (lambda15(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda15(Object param1Object) {
      return IsEqual.apply(this.name, testing.testRunnerTestName(param1Object));
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
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/testing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
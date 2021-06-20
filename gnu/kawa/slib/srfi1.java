package gnu.kawa.slib;

import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Map;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.Numeric;
import kawa.lang.Continuation;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.standard.Scheme;
import kawa.standard.append;
import kawa.standard.call_with_values;

public class srfi1 extends ModuleBody {
  public static final Macro $Pcevery;
  
  public static final int $Pcprovide$Pclist$Mnlib = 123;
  
  public static final int $Pcprovide$Pcsrfi$Mn1 = 123;
  
  public static final srfi1 $instance;
  
  static final IntNum Lit0;
  
  static final IntNum Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit100;
  
  static final SimpleSymbol Lit101;
  
  static final SimpleSymbol Lit102;
  
  static final SimpleSymbol Lit103;
  
  static final SimpleSymbol Lit104 = (SimpleSymbol)(new SimpleSymbol("cdr")).readResolve();
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit13;
  
  static final SimpleSymbol Lit14;
  
  static final SimpleSymbol Lit15;
  
  static final SimpleSymbol Lit16;
  
  static final SimpleSymbol Lit17;
  
  static final SimpleSymbol Lit18;
  
  static final SimpleSymbol Lit19;
  
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
  
  static final SimpleSymbol Lit71;
  
  static final SimpleSymbol Lit72;
  
  static final SimpleSymbol Lit73;
  
  static final SimpleSymbol Lit74;
  
  static final SimpleSymbol Lit75;
  
  static final SimpleSymbol Lit76;
  
  static final SimpleSymbol Lit77;
  
  static final SimpleSymbol Lit78;
  
  static final SimpleSymbol Lit79;
  
  static final SimpleSymbol Lit8;
  
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
  
  static final SimpleSymbol Lit90;
  
  static final SimpleSymbol Lit91;
  
  static final SimpleSymbol Lit92;
  
  static final SimpleSymbol Lit93;
  
  static final SimpleSymbol Lit94;
  
  static final SimpleSymbol Lit95;
  
  static final SimpleSymbol Lit96;
  
  static final SimpleSymbol Lit97;
  
  static final SimpleSymbol Lit98;
  
  static final SimpleSymbol Lit99;
  
  public static final ModuleMethod alist$Mncons;
  
  public static final ModuleMethod alist$Mncopy;
  
  public static final ModuleMethod alist$Mndelete;
  
  public static final ModuleMethod alist$Mndelete$Ex;
  
  public static final ModuleMethod any;
  
  public static final ModuleMethod append$Ex;
  
  public static final ModuleMethod append$Mnmap;
  
  public static final ModuleMethod append$Mnmap$Ex;
  
  public static final ModuleMethod append$Mnreverse;
  
  public static final ModuleMethod append$Mnreverse$Ex;
  
  public static final ModuleMethod break;
  
  public static final ModuleMethod break$Ex;
  
  public static final ModuleMethod car$Plcdr;
  
  public static final ModuleMethod circular$Mnlist;
  
  public static final ModuleMethod circular$Mnlist$Qu;
  
  public static final ModuleMethod concatenate;
  
  public static final ModuleMethod concatenate$Ex;
  
  public static final ModuleMethod cons$St;
  
  public static final ModuleMethod count;
  
  public static final ModuleMethod delete;
  
  public static final ModuleMethod delete$Ex;
  
  public static final ModuleMethod delete$Mnduplicates;
  
  public static final ModuleMethod delete$Mnduplicates$Ex;
  
  public static final ModuleMethod dotted$Mnlist$Qu;
  
  public static final ModuleMethod drop;
  
  public static final ModuleMethod drop$Mnright;
  
  public static final ModuleMethod drop$Mnright$Ex;
  
  public static final ModuleMethod drop$Mnwhile;
  
  public static final ModuleMethod eighth;
  
  public static final ModuleMethod every;
  
  public static final ModuleMethod fifth;
  
  public static final ModuleMethod filter;
  
  public static final ModuleMethod filter$Ex;
  
  public static final ModuleMethod filter$Mnmap;
  
  public static final ModuleMethod find;
  
  public static final ModuleMethod find$Mntail;
  
  public static GenericProc first;
  
  public static final ModuleMethod fold;
  
  public static final ModuleMethod fold$Mnright;
  
  public static GenericProc fourth;
  
  public static final ModuleMethod iota;
  
  static final ModuleMethod lambda$Fn64;
  
  static final ModuleMethod lambda$Fn78;
  
  public static final ModuleMethod last;
  
  public static final ModuleMethod last$Mnpair;
  
  public static final ModuleMethod length$Pl;
  
  public static final ModuleMethod list$Eq;
  
  public static final ModuleMethod list$Mncopy;
  
  public static final ModuleMethod list$Mnindex;
  
  public static final ModuleMethod list$Mntabulate;
  
  public static final ModuleMethod lset$Eq;
  
  public static final ModuleMethod lset$Ls$Eq;
  
  public static final ModuleMethod lset$Mnadjoin;
  
  public static final ModuleMethod lset$Mndiff$Plintersection;
  
  public static final ModuleMethod lset$Mndiff$Plintersection$Ex;
  
  public static final ModuleMethod lset$Mndifference;
  
  public static final ModuleMethod lset$Mndifference$Ex;
  
  public static final ModuleMethod lset$Mnintersection;
  
  public static final ModuleMethod lset$Mnintersection$Ex;
  
  public static final ModuleMethod lset$Mnunion;
  
  public static final ModuleMethod lset$Mnunion$Ex;
  
  public static final ModuleMethod lset$Mnxor;
  
  public static final ModuleMethod lset$Mnxor$Ex;
  
  public static final ModuleMethod make$Mnlist;
  
  public static final ModuleMethod map$Ex;
  
  public static Map map$Mnin$Mnorder;
  
  public static final ModuleMethod ninth;
  
  public static final ModuleMethod not$Mnpair$Qu;
  
  public static final ModuleMethod null$Mnlist$Qu;
  
  public static final ModuleMethod pair$Mnfold;
  
  public static final ModuleMethod pair$Mnfold$Mnright;
  
  public static final ModuleMethod pair$Mnfor$Mneach;
  
  public static final ModuleMethod partition;
  
  public static final ModuleMethod partition$Ex;
  
  public static final ModuleMethod proper$Mnlist$Qu;
  
  public static final ModuleMethod reduce;
  
  public static final ModuleMethod reduce$Mnright;
  
  public static final ModuleMethod remove;
  
  public static final ModuleMethod remove$Ex;
  
  public static GenericProc second;
  
  public static final ModuleMethod seventh;
  
  public static final ModuleMethod sixth;
  
  public static final ModuleMethod span;
  
  public static final ModuleMethod span$Ex;
  
  public static final ModuleMethod split$Mnat;
  
  public static final ModuleMethod split$Mnat$Ex;
  
  public static final ModuleMethod take;
  
  public static final ModuleMethod take$Ex;
  
  public static final ModuleMethod take$Mnright;
  
  public static final ModuleMethod take$Mnwhile;
  
  public static final ModuleMethod take$Mnwhile$Ex;
  
  public static final ModuleMethod tenth;
  
  public static GenericProc third;
  
  public static final ModuleMethod unfold;
  
  public static final ModuleMethod unfold$Mnright;
  
  public static final ModuleMethod unzip1;
  
  public static final ModuleMethod unzip2;
  
  public static final ModuleMethod unzip3;
  
  public static final ModuleMethod unzip4;
  
  public static final ModuleMethod unzip5;
  
  public static final ModuleMethod xcons;
  
  public static final ModuleMethod zip;
  
  static Object $PcCars$Pl(Object paramObject1, Object paramObject2) {
    frame56 frame56 = new frame56();
    frame56.last$Mnelt = paramObject2;
    return frame56.lambda75recur(paramObject1);
  }
  
  static Object $PcCars$PlCdrs(Object paramObject) {
    Continuation continuation = new Continuation(CallContext.getInstance());
    try {
      frame57 frame57 = new frame57();
      frame57.abort = continuation;
      paramObject = frame57.lambda76recur(paramObject);
      return paramObject;
    } finally {
      paramObject = null;
    } 
  }
  
  static Object $PcCars$PlCdrs$Pl(Object paramObject1, Object paramObject2) {
    frame62 frame62 = new frame62();
    frame62.cars$Mnfinal = paramObject2;
    paramObject2 = new Continuation(CallContext.getInstance());
    try {
      frame63 frame63 = new frame63();
      frame63.staticLink = frame62;
      frame63.abort = (Continuation)paramObject2;
      paramObject1 = frame63.lambda85recur(paramObject1);
      return paramObject1;
    } finally {
      paramObject1 = null;
    } 
  }
  
  static Object $PcCars$PlCdrs$SlNoTest(Object paramObject) {
    new frame67();
    return frame67.lambda92recur(paramObject);
  }
  
  static Object $PcCars$PlCdrs$SlNoTest$SlPair(Object paramObject) {
    frame71 frame71 = new frame71();
    frame71.lists = paramObject;
    return call_with_values.callWithValues((Procedure)frame71.lambda$Fn77, (Procedure)lambda$Fn78);
  }
  
  static Object $PcCars$PlCdrs$SlPair(Object paramObject) {
    frame61 frame61 = new frame61();
    frame61.lists = paramObject;
    return call_with_values.callWithValues((Procedure)frame61.lambda$Fn63, (Procedure)lambda$Fn64);
  }
  
  static Object $PcCdrs(Object paramObject) {
    Continuation continuation = new Continuation(CallContext.getInstance());
    try {
      frame55 frame55 = new frame55();
      frame55.abort = continuation;
      paramObject = frame55.lambda74recur(paramObject);
      return paramObject;
    } finally {
      paramObject = null;
    } 
  }
  
  static Object $PcLset2$Ls$Eq(Object paramObject1, Object paramObject2, Object paramObject3) {
    frame72 frame72 = new frame72();
    frame72.$Eq = paramObject1;
    frame72.lis2 = paramObject3;
    return every$V((Procedure)frame72.lambda$Fn79, paramObject2, new Object[0]);
  }
  
  static {
    Lit103 = (SimpleSymbol)(new SimpleSymbol("car")).readResolve();
    Lit102 = (SimpleSymbol)(new SimpleSymbol("lp")).readResolve();
    Lit101 = (SimpleSymbol)(new SimpleSymbol("head")).readResolve();
    Lit100 = (SimpleSymbol)(new SimpleSymbol("tail")).readResolve();
    Lit99 = (SimpleSymbol)(new SimpleSymbol("lset-diff+intersection!")).readResolve();
    Lit98 = (SimpleSymbol)(new SimpleSymbol("lset-diff+intersection")).readResolve();
    Lit97 = (SimpleSymbol)(new SimpleSymbol("lset-xor!")).readResolve();
    Lit96 = (SimpleSymbol)(new SimpleSymbol("lset-xor")).readResolve();
    Lit95 = (SimpleSymbol)(new SimpleSymbol("lset-difference!")).readResolve();
    Lit94 = (SimpleSymbol)(new SimpleSymbol("lset-difference")).readResolve();
    Lit93 = (SimpleSymbol)(new SimpleSymbol("lset-intersection!")).readResolve();
    Lit92 = (SimpleSymbol)(new SimpleSymbol("lset-intersection")).readResolve();
    Lit91 = (SimpleSymbol)(new SimpleSymbol("lset-union!")).readResolve();
    Lit90 = (SimpleSymbol)(new SimpleSymbol("lset-union")).readResolve();
    Lit89 = (SimpleSymbol)(new SimpleSymbol("lset-adjoin")).readResolve();
    Lit88 = (SimpleSymbol)(new SimpleSymbol("lset=")).readResolve();
    Lit87 = (SimpleSymbol)(new SimpleSymbol("lset<=")).readResolve();
    Lit86 = (SimpleSymbol)(new SimpleSymbol("list-index")).readResolve();
    SimpleSymbol simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("%every")).readResolve();
    Lit84 = simpleSymbol1;
    SyntaxPattern syntaxPattern = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
    SimpleSymbol simpleSymbol2 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
    SimpleSymbol simpleSymbol3 = Lit102;
    SimpleSymbol simpleSymbol4 = Lit101;
    SimpleSymbol simpleSymbol5 = Lit103;
    SimpleSymbol simpleSymbol6 = Lit100;
    SimpleSymbol simpleSymbol7 = Lit104;
    SimpleSymbol simpleSymbol8 = (SimpleSymbol)(new SimpleSymbol("and")).readResolve();
    SimpleSymbol simpleSymbol9 = (SimpleSymbol)(new SimpleSymbol("null-list?")).readResolve();
    Lit14 = simpleSymbol9;
    SyntaxRule syntaxRule = new SyntaxRule(syntaxPattern, "\001\001", "\021\030\004\021\030\f¡I\021\030\024\b\021\030\034\b\013\b\021\030$\b\021\030,\b\013\b\021\0304\021\030<!\t\003\030D\030L", new Object[] { simpleSymbol2, simpleSymbol3, simpleSymbol4, simpleSymbol5, simpleSymbol6, simpleSymbol7, simpleSymbol8, PairWithPosition.make(simpleSymbol9, PairWithPosition.make(Lit100, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722136), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722124), PairWithPosition.make(Lit101, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722148), PairWithPosition.make(PairWithPosition.make(Lit102, PairWithPosition.make(PairWithPosition.make(Lit103, PairWithPosition.make(Lit100, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722163), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722158), PairWithPosition.make(PairWithPosition.make(Lit104, PairWithPosition.make(Lit100, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722174), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722169), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722169), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722158), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722154), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722154) }0);
    Lit85 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule }, 2);
    Lit83 = (SimpleSymbol)(new SimpleSymbol("every")).readResolve();
    Lit82 = (SimpleSymbol)(new SimpleSymbol("any")).readResolve();
    Lit81 = (SimpleSymbol)(new SimpleSymbol("break!")).readResolve();
    Lit80 = (SimpleSymbol)(new SimpleSymbol("break")).readResolve();
    Lit79 = (SimpleSymbol)(new SimpleSymbol("span!")).readResolve();
    Lit78 = (SimpleSymbol)(new SimpleSymbol("span")).readResolve();
    Lit77 = (SimpleSymbol)(new SimpleSymbol("take-while!")).readResolve();
    Lit76 = (SimpleSymbol)(new SimpleSymbol("drop-while")).readResolve();
    Lit75 = (SimpleSymbol)(new SimpleSymbol("take-while")).readResolve();
    Lit74 = (SimpleSymbol)(new SimpleSymbol("find-tail")).readResolve();
    Lit73 = (SimpleSymbol)(new SimpleSymbol("find")).readResolve();
    Lit72 = (SimpleSymbol)(new SimpleSymbol("alist-delete!")).readResolve();
    Lit71 = (SimpleSymbol)(new SimpleSymbol("alist-delete")).readResolve();
    Lit70 = (SimpleSymbol)(new SimpleSymbol("alist-copy")).readResolve();
    Lit69 = (SimpleSymbol)(new SimpleSymbol("alist-cons")).readResolve();
    Lit68 = (SimpleSymbol)(new SimpleSymbol("delete-duplicates!")).readResolve();
    Lit67 = (SimpleSymbol)(new SimpleSymbol("delete-duplicates")).readResolve();
    Lit66 = (SimpleSymbol)(new SimpleSymbol("delete!")).readResolve();
    Lit65 = (SimpleSymbol)(new SimpleSymbol("delete")).readResolve();
    Lit64 = (SimpleSymbol)(new SimpleSymbol("remove!")).readResolve();
    Lit63 = (SimpleSymbol)(new SimpleSymbol("remove")).readResolve();
    Lit62 = (SimpleSymbol)(new SimpleSymbol("partition!")).readResolve();
    Lit61 = (SimpleSymbol)(new SimpleSymbol("partition")).readResolve();
    Lit60 = (SimpleSymbol)(new SimpleSymbol("filter!")).readResolve();
    Lit59 = (SimpleSymbol)(new SimpleSymbol("filter")).readResolve();
    Lit58 = (SimpleSymbol)(new SimpleSymbol("filter-map")).readResolve();
    Lit57 = (SimpleSymbol)(new SimpleSymbol("map!")).readResolve();
    Lit56 = (SimpleSymbol)(new SimpleSymbol("pair-for-each")).readResolve();
    Lit55 = (SimpleSymbol)(new SimpleSymbol("append-map!")).readResolve();
    Lit54 = (SimpleSymbol)(new SimpleSymbol("append-map")).readResolve();
    Lit53 = (SimpleSymbol)(new SimpleSymbol("reduce-right")).readResolve();
    Lit52 = (SimpleSymbol)(new SimpleSymbol("reduce")).readResolve();
    Lit51 = (SimpleSymbol)(new SimpleSymbol("pair-fold")).readResolve();
    Lit50 = (SimpleSymbol)(new SimpleSymbol("pair-fold-right")).readResolve();
    Lit49 = (SimpleSymbol)(new SimpleSymbol("fold-right")).readResolve();
    Lit48 = (SimpleSymbol)(new SimpleSymbol("fold")).readResolve();
    Lit47 = (SimpleSymbol)(new SimpleSymbol("unfold")).readResolve();
    Lit46 = (SimpleSymbol)(new SimpleSymbol("unfold-right")).readResolve();
    Lit45 = (SimpleSymbol)(new SimpleSymbol("count")).readResolve();
    Lit44 = (SimpleSymbol)(new SimpleSymbol("concatenate!")).readResolve();
    Lit43 = (SimpleSymbol)(new SimpleSymbol("concatenate")).readResolve();
    Lit42 = (SimpleSymbol)(new SimpleSymbol("append-reverse!")).readResolve();
    Lit41 = (SimpleSymbol)(new SimpleSymbol("append-reverse")).readResolve();
    Lit40 = (SimpleSymbol)(new SimpleSymbol("append!")).readResolve();
    Lit39 = (SimpleSymbol)(new SimpleSymbol("unzip5")).readResolve();
    Lit38 = (SimpleSymbol)(new SimpleSymbol("unzip4")).readResolve();
    Lit37 = (SimpleSymbol)(new SimpleSymbol("unzip3")).readResolve();
    Lit36 = (SimpleSymbol)(new SimpleSymbol("unzip2")).readResolve();
    Lit35 = (SimpleSymbol)(new SimpleSymbol("unzip1")).readResolve();
    Lit34 = (SimpleSymbol)(new SimpleSymbol("last-pair")).readResolve();
    Lit33 = (SimpleSymbol)(new SimpleSymbol("last")).readResolve();
    Lit32 = (SimpleSymbol)(new SimpleSymbol("split-at!")).readResolve();
    Lit31 = (SimpleSymbol)(new SimpleSymbol("split-at")).readResolve();
    Lit30 = (SimpleSymbol)(new SimpleSymbol("drop-right!")).readResolve();
    Lit29 = (SimpleSymbol)(new SimpleSymbol("drop-right")).readResolve();
    Lit28 = (SimpleSymbol)(new SimpleSymbol("take-right")).readResolve();
    Lit27 = (SimpleSymbol)(new SimpleSymbol("take!")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("drop")).readResolve();
    Lit25 = (SimpleSymbol)(new SimpleSymbol("take")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("car+cdr")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("tenth")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("ninth")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("eighth")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("seventh")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("sixth")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("fifth")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("zip")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("length+")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("list=")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("not-pair?")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("circular-list?")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("dotted-list?")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("proper-list?")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("circular-list")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("iota")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("list-copy")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("cons*")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("list-tabulate")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("make-list")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("xcons")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("tmp")).readResolve();
    Lit1 = IntNum.make(1);
    Lit0 = IntNum.make(0);
    $instance = new srfi1();
    $Pcprovide$Pcsrfi$Mn1 = 123;
    $Pcprovide$Pclist$Mnlib = 123;
    srfi1 srfi11 = $instance;
    xcons = new ModuleMethod(srfi11, 78, Lit3, 8194);
    make$Mnlist = new ModuleMethod(srfi11, 79, Lit4, -4095);
    list$Mntabulate = new ModuleMethod(srfi11, 80, Lit5, 8194);
    cons$St = new ModuleMethod(srfi11, 81, Lit6, -4096);
    list$Mncopy = new ModuleMethod(srfi11, 82, Lit7, 4097);
    iota = new ModuleMethod(srfi11, 83, Lit8, 12289);
    circular$Mnlist = new ModuleMethod(srfi11, 86, Lit9, -4095);
    proper$Mnlist$Qu = new ModuleMethod(srfi11, 87, Lit10, 4097);
    dotted$Mnlist$Qu = new ModuleMethod(srfi11, 88, Lit11, 4097);
    circular$Mnlist$Qu = new ModuleMethod(srfi11, 89, Lit12, 4097);
    not$Mnpair$Qu = new ModuleMethod(srfi11, 90, Lit13, 4097);
    null$Mnlist$Qu = new ModuleMethod(srfi11, 91, Lit14, 4097);
    list$Eq = new ModuleMethod(srfi11, 92, Lit15, -4095);
    length$Pl = new ModuleMethod(srfi11, 93, Lit16, 4097);
    zip = new ModuleMethod(srfi11, 94, Lit17, -4095);
    fifth = new ModuleMethod(srfi11, 95, Lit18, 4097);
    sixth = new ModuleMethod(srfi11, 96, Lit19, 4097);
    seventh = new ModuleMethod(srfi11, 97, Lit20, 4097);
    eighth = new ModuleMethod(srfi11, 98, Lit21, 4097);
    ninth = new ModuleMethod(srfi11, 99, Lit22, 4097);
    tenth = new ModuleMethod(srfi11, 100, Lit23, 4097);
    car$Plcdr = new ModuleMethod(srfi11, 101, Lit24, 4097);
    take = new ModuleMethod(srfi11, 102, Lit25, 8194);
    drop = new ModuleMethod(srfi11, 103, Lit26, 8194);
    take$Ex = new ModuleMethod(srfi11, 104, Lit27, 8194);
    take$Mnright = new ModuleMethod(srfi11, 105, Lit28, 8194);
    drop$Mnright = new ModuleMethod(srfi11, 106, Lit29, 8194);
    drop$Mnright$Ex = new ModuleMethod(srfi11, 107, Lit30, 8194);
    split$Mnat = new ModuleMethod(srfi11, 108, Lit31, 8194);
    split$Mnat$Ex = new ModuleMethod(srfi11, 109, Lit32, 8194);
    last = new ModuleMethod(srfi11, 110, Lit33, 4097);
    last$Mnpair = new ModuleMethod(srfi11, 111, Lit34, 4097);
    unzip1 = new ModuleMethod(srfi11, 112, Lit35, 4097);
    unzip2 = new ModuleMethod(srfi11, 113, Lit36, 4097);
    unzip3 = new ModuleMethod(srfi11, 114, Lit37, 4097);
    unzip4 = new ModuleMethod(srfi11, 115, Lit38, 4097);
    unzip5 = new ModuleMethod(srfi11, 116, Lit39, 4097);
    append$Ex = new ModuleMethod(srfi11, 117, Lit40, -4096);
    append$Mnreverse = new ModuleMethod(srfi11, 118, Lit41, 8194);
    append$Mnreverse$Ex = new ModuleMethod(srfi11, 119, Lit42, 8194);
    concatenate = new ModuleMethod(srfi11, 120, Lit43, 4097);
    concatenate$Ex = new ModuleMethod(srfi11, 121, Lit44, 4097);
    count = new ModuleMethod(srfi11, 122, Lit45, -4094);
    unfold$Mnright = new ModuleMethod(srfi11, 123, Lit46, 20484);
    unfold = new ModuleMethod(srfi11, 125, Lit47, -4092);
    fold = new ModuleMethod(srfi11, 126, Lit48, -4093);
    fold$Mnright = new ModuleMethod(srfi11, 127, Lit49, -4093);
    pair$Mnfold$Mnright = new ModuleMethod(srfi11, 128, Lit50, -4093);
    pair$Mnfold = new ModuleMethod(srfi11, 129, Lit51, -4093);
    reduce = new ModuleMethod(srfi11, 130, Lit52, 12291);
    reduce$Mnright = new ModuleMethod(srfi11, 131, Lit53, 12291);
    append$Mnmap = new ModuleMethod(srfi11, 132, Lit54, -4094);
    append$Mnmap$Ex = new ModuleMethod(srfi11, 133, Lit55, -4094);
    pair$Mnfor$Mneach = new ModuleMethod(srfi11, 134, Lit56, -4094);
    map$Ex = new ModuleMethod(srfi11, 135, Lit57, -4094);
    filter$Mnmap = new ModuleMethod(srfi11, 136, Lit58, -4094);
    filter = new ModuleMethod(srfi11, 137, Lit59, 8194);
    filter$Ex = new ModuleMethod(srfi11, 138, Lit60, 8194);
    partition = new ModuleMethod(srfi11, 139, Lit61, 8194);
    partition$Ex = new ModuleMethod(srfi11, 140, Lit62, 8194);
    remove = new ModuleMethod(srfi11, 141, Lit63, 8194);
    remove$Ex = new ModuleMethod(srfi11, 142, Lit64, 8194);
    delete = new ModuleMethod(srfi11, 143, Lit65, 12290);
    delete$Ex = new ModuleMethod(srfi11, 145, Lit66, 12290);
    delete$Mnduplicates = new ModuleMethod(srfi11, 147, Lit67, 8193);
    delete$Mnduplicates$Ex = new ModuleMethod(srfi11, 149, Lit68, 8193);
    alist$Mncons = new ModuleMethod(srfi11, 151, Lit69, 12291);
    alist$Mncopy = new ModuleMethod(srfi11, 152, Lit70, 4097);
    alist$Mndelete = new ModuleMethod(srfi11, 153, Lit71, 12290);
    alist$Mndelete$Ex = new ModuleMethod(srfi11, 155, Lit72, 12290);
    find = new ModuleMethod(srfi11, 157, Lit73, 8194);
    find$Mntail = new ModuleMethod(srfi11, 158, Lit74, 8194);
    take$Mnwhile = new ModuleMethod(srfi11, 159, Lit75, 8194);
    drop$Mnwhile = new ModuleMethod(srfi11, 160, Lit76, 8194);
    take$Mnwhile$Ex = new ModuleMethod(srfi11, 161, Lit77, 8194);
    span = new ModuleMethod(srfi11, 162, Lit78, 8194);
    span$Ex = new ModuleMethod(srfi11, 163, Lit79, 8194);
    break = new ModuleMethod(srfi11, 164, Lit80, 8194);
    break$Ex = new ModuleMethod(srfi11, 165, Lit81, 8194);
    any = new ModuleMethod(srfi11, 166, Lit82, -4094);
    every = new ModuleMethod(srfi11, 167, Lit83, -4094);
    $Pcevery = Macro.make(Lit84, (Procedure)Lit85, $instance);
    list$Mnindex = new ModuleMethod(srfi11, 168, Lit86, -4094);
    lset$Ls$Eq = new ModuleMethod(srfi11, 169, Lit87, -4095);
    lset$Eq = new ModuleMethod(srfi11, 170, Lit88, -4095);
    lset$Mnadjoin = new ModuleMethod(srfi11, 171, Lit89, -4094);
    lset$Mnunion = new ModuleMethod(srfi11, 172, Lit90, -4095);
    lset$Mnunion$Ex = new ModuleMethod(srfi11, 173, Lit91, -4095);
    lset$Mnintersection = new ModuleMethod(srfi11, 174, Lit92, -4094);
    lset$Mnintersection$Ex = new ModuleMethod(srfi11, 175, Lit93, -4094);
    lset$Mndifference = new ModuleMethod(srfi11, 176, Lit94, -4094);
    lset$Mndifference$Ex = new ModuleMethod(srfi11, 177, Lit95, -4094);
    lset$Mnxor = new ModuleMethod(srfi11, 178, Lit96, -4095);
    lset$Mnxor$Ex = new ModuleMethod(srfi11, 179, Lit97, -4095);
    lset$Mndiff$Plintersection = new ModuleMethod(srfi11, 180, Lit98, -4094);
    lset$Mndiff$Plintersection$Ex = new ModuleMethod(srfi11, 181, Lit99, -4094);
    lambda$Fn64 = new ModuleMethod(srfi11, 182, null, 8194);
    lambda$Fn78 = new ModuleMethod(srfi11, 183, null, 8194);
    $instance.run();
  }
  
  public srfi1() {
    ModuleInfo.register(this);
  }
  
  public static Pair alistCons(Object paramObject1, Object paramObject2, Object paramObject3) {
    return lists.cons(lists.cons(paramObject1, paramObject2), paramObject3);
  }
  
  public static LList alistCopy(Object paramObject) {
    LList lList = LList.Empty;
    Object object = paramObject;
    paramObject = lList;
    while (true) {
      if (object == LList.Empty)
        return LList.reverseInPlace(paramObject); 
      try {
        Pair pair = (Pair)object;
        object = pair.getCdr();
        Object object1 = pair.getCar();
        paramObject = Pair.make(lists.cons(lists.car.apply1(object1), lists.cdr.apply1(object1)), paramObject);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, object);
      } 
    } 
  }
  
  public static Object alistDelete(Object paramObject1, Object paramObject2) {
    return alistDelete(paramObject1, paramObject2, Scheme.isEqual);
  }
  
  public static Object alistDelete(Object paramObject1, Object paramObject2, Object paramObject3) {
    frame21 frame21 = new frame21();
    frame21.key = paramObject1;
    frame21.maybe$Mn$Eq = paramObject3;
    return filter((Procedure)frame21.lambda$Fn18, paramObject2);
  }
  
  public static Object alistDelete$Ex(Object paramObject1, Object paramObject2) {
    return alistDelete$Ex(paramObject1, paramObject2, Scheme.isEqual);
  }
  
  public static Object alistDelete$Ex(Object paramObject1, Object paramObject2, Object paramObject3) {
    frame22 frame22 = new frame22();
    frame22.key = paramObject1;
    frame22.maybe$Mn$Eq = paramObject3;
    return filter$Ex((Procedure)frame22.lambda$Fn19, paramObject2);
  }
  
  public static Object any$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    int i = 0;
    frame26 frame26 = new frame26();
    frame26.pred = paramProcedure;
    frame26.lis1 = paramObject;
    frame26.lists = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(frame26.lists))
      return call_with_values.callWithValues((Procedure)frame26.lambda$Fn22, (Procedure)frame26.lambda$Fn23); 
    Object object = isNullList(frame26.lis1);
    try {
      paramObject = Boolean.FALSE;
      if (object != paramObject)
        i = 1; 
      i = i + 1 & 0x1;
      if (i != 0) {
        paramObject = lists.car.apply1(frame26.lis1);
        object = lists.cdr.apply1(frame26.lis1);
        while (true) {
          if (isNullList(object) != Boolean.FALSE)
            return frame26.pred.apply1(paramObject); 
          Object object1 = frame26.pred.apply1(paramObject);
          paramObject = object1;
          if (object1 == Boolean.FALSE) {
            paramObject = lists.car.apply1(object);
            object = lists.cdr.apply1(object);
            continue;
          } 
          return paramObject;
        } 
      } 
      return (i != 0) ? Boolean.TRUE : Boolean.FALSE;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "x", -2, object);
    } 
  }
  
  public static Object append$Ex$V(Object[] paramArrayOfObject) {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: invokestatic makeList : ([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   5: astore_0
    //   6: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   9: astore_2
    //   10: aload_0
    //   11: invokestatic isPair : (Ljava/lang/Object;)Z
    //   14: ifne -> 19
    //   17: aload_2
    //   18: areturn
    //   19: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   22: aload_0
    //   23: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   26: astore_2
    //   27: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   30: aload_0
    //   31: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   34: astore_1
    //   35: aload_2
    //   36: invokestatic isPair : (Ljava/lang/Object;)Z
    //   39: ifne -> 47
    //   42: aload_1
    //   43: astore_0
    //   44: goto -> 10
    //   47: aload_2
    //   48: checkcast gnu/lists/Pair
    //   51: astore_0
    //   52: aload_0
    //   53: invokestatic lastPair : (Lgnu/lists/Pair;)Ljava/lang/Object;
    //   56: astore_0
    //   57: aload_1
    //   58: invokestatic isPair : (Ljava/lang/Object;)Z
    //   61: ifeq -> 112
    //   64: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   67: aload_1
    //   68: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   71: astore_3
    //   72: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   75: aload_1
    //   76: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   79: astore_1
    //   80: aload_0
    //   81: checkcast gnu/lists/Pair
    //   84: astore #4
    //   86: aload #4
    //   88: aload_3
    //   89: invokestatic setCdr$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   92: aload_3
    //   93: invokestatic isPair : (Ljava/lang/Object;)Z
    //   96: ifeq -> 109
    //   99: aload_3
    //   100: checkcast gnu/lists/Pair
    //   103: astore_0
    //   104: aload_0
    //   105: invokestatic lastPair : (Lgnu/lists/Pair;)Ljava/lang/Object;
    //   108: astore_0
    //   109: goto -> 57
    //   112: aload_2
    //   113: areturn
    //   114: astore_0
    //   115: new gnu/mapping/WrongType
    //   118: dup
    //   119: aload_0
    //   120: ldc_w 'last-pair'
    //   123: iconst_0
    //   124: aload_2
    //   125: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   128: athrow
    //   129: astore_1
    //   130: new gnu/mapping/WrongType
    //   133: dup
    //   134: aload_1
    //   135: ldc_w 'set-cdr!'
    //   138: iconst_1
    //   139: aload_0
    //   140: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   143: athrow
    //   144: astore_0
    //   145: new gnu/mapping/WrongType
    //   148: dup
    //   149: aload_0
    //   150: ldc_w 'last-pair'
    //   153: iconst_0
    //   154: aload_3
    //   155: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   158: athrow
    // Exception table:
    //   from	to	target	type
    //   47	52	114	java/lang/ClassCastException
    //   80	86	129	java/lang/ClassCastException
    //   99	104	144	java/lang/ClassCastException
  }
  
  public static Object appendMap$Ex$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(lList))
      return Scheme.apply.apply2(append$Ex, Scheme.apply.apply4(Scheme.map, paramObject1, paramObject2, lList)); 
    Apply apply = Scheme.apply;
    ModuleMethod moduleMethod = append$Ex;
    lList = LList.Empty;
    while (true) {
      if (paramObject2 == LList.Empty)
        return apply.apply2(moduleMethod, LList.reverseInPlace(lList)); 
      try {
        Pair pair2 = (Pair)paramObject2;
        paramObject2 = pair2.getCdr();
        Pair pair1 = Pair.make(Scheme.applyToArgs.apply2(paramObject1, pair2.getCar()), lList);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, paramObject2);
      } 
    } 
  }
  
  public static Object appendMap$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(lList))
      return Scheme.apply.apply2(append.append, Scheme.apply.apply4(Scheme.map, paramObject1, paramObject2, lList)); 
    Apply apply = Scheme.apply;
    append append = append.append;
    lList = LList.Empty;
    while (true) {
      if (paramObject2 == LList.Empty)
        return apply.apply2(append, LList.reverseInPlace(lList)); 
      try {
        Pair pair2 = (Pair)paramObject2;
        paramObject2 = pair2.getCdr();
        Pair pair1 = Pair.make(Scheme.applyToArgs.apply2(paramObject1, pair2.getCar()), lList);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, paramObject2);
      } 
    } 
  }
  
  public static Object appendReverse(Object paramObject1, Object paramObject2) {
    while (true) {
      if (isNullList(paramObject1) != Boolean.FALSE)
        return paramObject2; 
      Object object = lists.cdr.apply1(paramObject1);
      paramObject2 = lists.cons(lists.car.apply1(paramObject1), paramObject2);
      paramObject1 = object;
    } 
  }
  
  public static Object appendReverse$Ex(Object paramObject1, Object paramObject2) {
    while (true) {
      if (isNullList(paramObject1) != Boolean.FALSE)
        return paramObject2; 
      Object object = lists.cdr.apply1(paramObject1);
      try {
        Pair pair = (Pair)paramObject1;
        lists.setCdr$Ex(pair, paramObject2);
        paramObject2 = paramObject1;
        paramObject1 = object;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "set-cdr!", 1, paramObject1);
      } 
    } 
  }
  
  public static Object break(Object paramObject1, Object paramObject2) {
    frame24 frame24 = new frame24();
    frame24.pred = paramObject1;
    return span((Procedure)frame24.lambda$Fn20, paramObject2);
  }
  
  public static Object break$Ex(Object paramObject1, Object paramObject2) {
    frame25 frame25 = new frame25();
    frame25.pred = paramObject1;
    return span$Ex((Procedure)frame25.lambda$Fn21, paramObject2);
  }
  
  public static Object car$PlCdr(Object paramObject) {
    return misc.values(new Object[] { lists.car.apply1(paramObject), lists.cdr.apply1(paramObject) });
  }
  
  public static Pair circularList$V(Object paramObject, Object[] paramArrayOfObject) {
    Pair pair = lists.cons(paramObject, LList.makeList(paramArrayOfObject, 0));
    paramObject = lastPair(pair);
    try {
      Pair pair1 = (Pair)paramObject;
      lists.setCdr$Ex(pair1, pair);
      return pair;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "set-cdr!", 1, paramObject);
    } 
  }
  
  public static Object concatenate(Object paramObject) {
    return reduceRight((Procedure)append.append, LList.Empty, paramObject);
  }
  
  public static Object concatenate$Ex(Object paramObject) {
    return reduceRight((Procedure)append$Ex, LList.Empty, paramObject);
  }
  
  public static Object cons$St(Object... paramVarArgs) {
    return LList.consX(paramVarArgs);
  }
  
  public static Object count$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(lList)) {
      IntNum intNum1 = Lit0;
      Object object1 = paramObject;
      paramObject = intNum1;
      while (true) {
        if (isNullList(object1) != Boolean.FALSE)
          return paramObject; 
        Object object2 = $PcCars$PlCdrs$SlPair(lList);
        Object object4 = lists.car.apply1(object2);
        Object object3 = lists.cdr.apply1(object2);
        object2 = paramObject;
        if (!lists.isNull(object4)) {
          Object object5 = lists.cdr.apply1(object1);
          object2 = paramObject;
          if (Scheme.apply.apply3(paramProcedure, lists.car.apply1(object1), object4) != Boolean.FALSE)
            object2 = AddOp.$Pl.apply2(paramObject, Lit1); 
          object1 = object3;
          object3 = object5;
          paramObject = object2;
          object2 = object1;
          object1 = object3;
          continue;
        } 
        return object2;
      } 
    } 
    IntNum intNum = Lit0;
    Object object = paramObject;
    paramObject = intNum;
    while (true) {
      Object object1 = paramObject;
      if (isNullList(object) == Boolean.FALSE) {
        Object object2 = lists.cdr.apply1(object);
        object1 = paramObject;
        if (paramProcedure.apply1(lists.car.apply1(object)) != Boolean.FALSE)
          object1 = AddOp.$Pl.apply2(paramObject, Lit1); 
        object = object2;
        paramObject = object1;
        continue;
      } 
      return object1;
    } 
  }
  
  public static Object delete(Object paramObject1, Object paramObject2) {
    return delete(paramObject1, paramObject2, Scheme.isEqual);
  }
  
  public static Object delete(Object paramObject1, Object paramObject2, Object paramObject3) {
    frame17 frame17 = new frame17();
    frame17.x = paramObject1;
    frame17.maybe$Mn$Eq = paramObject3;
    return filter((Procedure)frame17.lambda$Fn16, paramObject2);
  }
  
  public static Object delete$Ex(Object paramObject1, Object paramObject2) {
    return delete$Ex(paramObject1, paramObject2, Scheme.isEqual);
  }
  
  public static Object delete$Ex(Object paramObject1, Object paramObject2, Object paramObject3) {
    frame18 frame18 = new frame18();
    frame18.x = paramObject1;
    frame18.maybe$Mn$Eq = paramObject3;
    return filter$Ex((Procedure)frame18.lambda$Fn17, paramObject2);
  }
  
  public static Object deleteDuplicates(Object paramObject) {
    return deleteDuplicates(paramObject, (Procedure)Scheme.isEqual);
  }
  
  public static Object deleteDuplicates(Object paramObject, Procedure paramProcedure) {
    frame19 frame19 = new frame19();
    frame19.maybe$Mn$Eq = paramProcedure;
    return frame19.lambda30recur(paramObject);
  }
  
  public static Object deleteDuplicates$Ex(Object paramObject) {
    return deleteDuplicates$Ex(paramObject, (Procedure)Scheme.isEqual);
  }
  
  public static Object deleteDuplicates$Ex(Object paramObject, Procedure paramProcedure) {
    frame20 frame20 = new frame20();
    frame20.maybe$Mn$Eq = paramProcedure;
    return frame20.lambda31recur(paramObject);
  }
  
  public static Object drop(Object paramObject, IntNum paramIntNum) {
    Object object;
    try {
      while (true) {
        Number number = (Number)paramIntNum;
        if (numbers.isZero(number))
          return paramObject; 
        paramObject = lists.cdr.apply1(paramObject);
        object = AddOp.$Mn.apply2(paramIntNum, Lit1);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "zero?", 1, object);
    } 
  }
  
  public static Object dropRight(Object paramObject, IntNum paramIntNum) {
    return lambda1recur(paramObject, drop(paramObject, paramIntNum));
  }
  
  public static Object dropRight$Ex(Object paramObject, IntNum paramIntNum) {
    Object object = drop(paramObject, paramIntNum);
    if (lists.isPair(object)) {
      Object object1 = lists.cdr.apply1(object);
      object = paramObject;
      while (lists.isPair(object1)) {
        object = lists.cdr.apply1(object);
        object1 = lists.cdr.apply1(object1);
      } 
      try {
        object1 = object;
        lists.setCdr$Ex((Pair)object1, LList.Empty);
        return paramObject;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "set-cdr!", 1, object);
      } 
    } 
    return LList.Empty;
  }
  
  public static Object dropWhile(Procedure paramProcedure, Object paramObject) {
    while (true) {
      if (isNullList(paramObject) != Boolean.FALSE)
        return LList.Empty; 
      Object object = paramObject;
      if (paramProcedure.apply1(lists.car.apply1(paramObject)) != Boolean.FALSE) {
        paramObject = lists.cdr.apply1(paramObject);
        continue;
      } 
      return object;
    } 
  }
  
  public static Object eighth(Object paramObject) {
    return lists.cadddr.apply1(lists.cddddr.apply1(paramObject));
  }
  
  public static Object every$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    frame27 frame27 = new frame27();
    frame27.pred = paramProcedure;
    frame27.lis1 = paramObject;
    frame27.lists = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(frame27.lists))
      return call_with_values.callWithValues((Procedure)frame27.lambda$Fn24, (Procedure)frame27.lambda$Fn25); 
    paramObject = isNullList(frame27.lis1);
    Object object = paramObject;
    if (paramObject == Boolean.FALSE) {
      object = lists.car.apply1(frame27.lis1);
      paramObject = lists.cdr.apply1(frame27.lis1);
      while (true) {
        if (isNullList(paramObject) != Boolean.FALSE)
          return frame27.pred.apply1(object); 
        Object object1 = frame27.pred.apply1(object);
        object = object1;
        if (object1 != Boolean.FALSE) {
          object = lists.car.apply1(paramObject);
          paramObject = lists.cdr.apply1(paramObject);
          continue;
        } 
        return object;
      } 
    } 
    return object;
  }
  
  public static Object fifth(Object paramObject) {
    return lists.car.apply1(lists.cddddr.apply1(paramObject));
  }
  
  public static Object filter(Procedure paramProcedure, Object paramObject) {
    LList lList = LList.Empty;
    while (true) {
      if (isNullList(paramObject) != Boolean.FALSE)
        try {
          LList lList1 = lList;
          return lists.reverse$Ex(lList1);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "reverse!", 1, lList);
        }  
      Object object = lists.car.apply1(paramObject);
      paramObject = lists.cdr.apply1(paramObject);
      if (classCastException.apply1(object) != Boolean.FALSE)
        Pair pair = lists.cons(object, lList); 
    } 
  }
  
  public static Object filter$Ex(Procedure paramProcedure, Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: astore_2
    //   2: aload_2
    //   3: invokestatic isNullList : (Ljava/lang/Object;)Ljava/lang/Object;
    //   6: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   9: if_acmpeq -> 14
    //   12: aload_2
    //   13: areturn
    //   14: aload_0
    //   15: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   18: aload_2
    //   19: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   22: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   25: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   28: if_acmpne -> 42
    //   31: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   34: aload_2
    //   35: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   38: astore_2
    //   39: goto -> 2
    //   42: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   45: aload_2
    //   46: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   49: astore_1
    //   50: aload_2
    //   51: astore_3
    //   52: aload_1
    //   53: invokestatic isPair : (Ljava/lang/Object;)Z
    //   56: ifeq -> 12
    //   59: aload_0
    //   60: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   63: aload_1
    //   64: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   67: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   70: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   73: if_acmpeq -> 93
    //   76: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   79: aload_1
    //   80: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   83: astore #4
    //   85: aload_1
    //   86: astore_3
    //   87: aload #4
    //   89: astore_1
    //   90: goto -> 52
    //   93: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   96: aload_1
    //   97: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   100: astore_1
    //   101: aload_1
    //   102: invokestatic isPair : (Ljava/lang/Object;)Z
    //   105: ifeq -> 165
    //   108: aload_0
    //   109: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   112: aload_1
    //   113: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   116: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   119: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   122: if_acmpeq -> 154
    //   125: aload_3
    //   126: checkcast gnu/lists/Pair
    //   129: astore #4
    //   131: aload #4
    //   133: aload_1
    //   134: invokestatic setCdr$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   137: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   140: aload_1
    //   141: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   144: astore #4
    //   146: aload_1
    //   147: astore_3
    //   148: aload #4
    //   150: astore_1
    //   151: goto -> 52
    //   154: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   157: aload_1
    //   158: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   161: astore_1
    //   162: goto -> 101
    //   165: aload_3
    //   166: checkcast gnu/lists/Pair
    //   169: astore_0
    //   170: aload_0
    //   171: aload_1
    //   172: invokestatic setCdr$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   175: aload_2
    //   176: areturn
    //   177: astore_0
    //   178: new gnu/mapping/WrongType
    //   181: dup
    //   182: aload_0
    //   183: ldc_w 'set-cdr!'
    //   186: iconst_1
    //   187: aload_3
    //   188: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   191: athrow
    //   192: astore_0
    //   193: new gnu/mapping/WrongType
    //   196: dup
    //   197: aload_0
    //   198: ldc_w 'set-cdr!'
    //   201: iconst_1
    //   202: aload_3
    //   203: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   206: athrow
    // Exception table:
    //   from	to	target	type
    //   125	131	177	java/lang/ClassCastException
    //   165	170	192	java/lang/ClassCastException
  }
  
  public static Object filterMap$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    frame13 frame13 = new frame13();
    frame13.f = paramProcedure;
    LList lList1 = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(lList1))
      return frame13.lambda23recur(lists.cons(paramObject, lList1), LList.Empty); 
    LList lList2 = LList.Empty;
    Object object = paramObject;
    paramObject = lList2;
    while (true) {
      if (isNullList(object) != Boolean.FALSE)
        try {
          object = paramObject;
          return lists.reverse$Ex((LList)object);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "reverse!", 1, paramObject);
        }  
      Object object2 = frame13.f.apply1(lists.car.apply1(classCastException));
      Object object1 = lists.cdr.apply1(classCastException);
      if (object2 != Boolean.FALSE)
        paramObject = lists.cons(object2, paramObject); 
    } 
  }
  
  public static Object find(Object paramObject1, Object paramObject2) {
    try {
      Procedure procedure = (Procedure)paramObject1;
      paramObject1 = findTail(procedure, paramObject2);
      return (paramObject1 != Boolean.FALSE) ? lists.car.apply1(paramObject1) : Boolean.FALSE;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "find-tail", 0, paramObject1);
    } 
  }
  
  public static Object findTail(Procedure paramProcedure, Object paramObject) {
    while (true) {
      Object object = isNullList(paramObject);
      try {
        Boolean bool = Boolean.FALSE;
        if (object != bool) {
          i = 1;
        } else {
          i = 0;
        } 
        int i = i + 1 & 0x1;
        if (i != 0) {
          if (paramProcedure.apply1(lists.car.apply1(paramObject)) != Boolean.FALSE)
            return paramObject; 
          paramObject = lists.cdr.apply1(paramObject);
          continue;
        } 
        return (i != 0) ? Boolean.TRUE : Boolean.FALSE;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "x", -2, object);
      } 
    } 
  }
  
  public static Object fold$V(Procedure paramProcedure, Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame7 frame7 = new frame7();
    frame7.kons = paramProcedure;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(lList))
      return frame7.lambda14lp(lists.cons(paramObject2, lList), paramObject1); 
    Object object = paramObject2;
    while (true) {
      paramObject2 = paramObject1;
      if (isNullList(object) == Boolean.FALSE) {
        paramObject2 = lists.cdr.apply1(object);
        paramObject1 = frame7.kons.apply2(lists.car.apply1(object), paramObject1);
        object = paramObject2;
        continue;
      } 
      return paramObject2;
    } 
  }
  
  public static Object foldRight$V(Procedure paramProcedure, Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame9 frame9 = new frame9();
    frame9.kons = paramProcedure;
    frame9.knil = paramObject1;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    return lists.isPair(lList) ? frame9.lambda17recur(lists.cons(paramObject2, lList)) : frame9.lambda18recur(paramObject2);
  }
  
  public static Object iota(IntNum paramIntNum) {
    return iota(paramIntNum, (Numeric)Lit0, (Numeric)Lit1);
  }
  
  public static Object iota(IntNum paramIntNum, Numeric paramNumeric) {
    return iota(paramIntNum, paramNumeric, (Numeric)Lit1);
  }
  
  public static Object iota(IntNum paramIntNum, Numeric paramNumeric1, Numeric paramNumeric2) {
    if (IntNum.compare(paramIntNum, 0L) < 0)
      misc.error$V("Negative step count", new Object[] { iota, paramIntNum }); 
    Object object = AddOp.$Pl.apply2(paramNumeric1, MultiplyOp.$St.apply2(IntNum.add(paramIntNum, -1), paramNumeric2));
    try {
      paramNumeric1 = (Numeric)object;
      LList lList2 = LList.Empty;
      object = paramIntNum;
      LList lList1 = lList2;
      while (Scheme.numLEq.apply2(object, Lit0) == Boolean.FALSE) {
        object = AddOp.$Mn.apply2(object, Lit1);
        Object object2 = AddOp.$Mn.apply2(paramNumeric1, paramNumeric2);
        Pair pair = lists.cons(paramNumeric1, lList1);
        Object object1 = object2;
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "last-val", -2, object);
    } 
    return classCastException;
  }
  
  public static Object isCircularList(Object paramObject) {
    Object object1 = paramObject;
    Object object2 = paramObject;
    paramObject = object1;
    while (true) {
      boolean bool = lists.isPair(object2);
      if (bool) {
        object1 = lists.cdr.apply1(object2);
        bool = lists.isPair(object1);
        if (bool) {
          boolean bool1;
          object2 = lists.cdr.apply1(object1);
          paramObject = lists.cdr.apply1(paramObject);
          if (object2 == paramObject) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          if (bool1)
            return bool1 ? Boolean.TRUE : Boolean.FALSE; 
          continue;
        } 
        return bool ? Boolean.TRUE : Boolean.FALSE;
      } 
      return bool ? Boolean.TRUE : Boolean.FALSE;
    } 
  }
  
  public static Object isDottedList(Object paramObject) {
    Object object1 = paramObject;
    Object object2 = paramObject;
    paramObject = object1;
    while (lists.isPair(object2)) {
      object1 = lists.cdr.apply1(object2);
      if (lists.isPair(object1)) {
        object2 = lists.cdr.apply1(object1);
        paramObject = lists.cdr.apply1(paramObject);
        if (object2 == paramObject) {
          i = 1;
        } else {
          i = 0;
        } 
        int i = i + 1 & 0x1;
        if (i != 0)
          continue; 
        return (i != 0) ? Boolean.TRUE : Boolean.FALSE;
      } 
      return lists.isNull(object1) ? Boolean.FALSE : Boolean.TRUE;
    } 
    return lists.isNull(object2) ? Boolean.FALSE : Boolean.TRUE;
  }
  
  public static boolean isNotPair(Object paramObject) {
    throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static Object isNullList(Object paramObject) {
    return (paramObject instanceof Pair) ? Boolean.FALSE : ((paramObject == LList.Empty) ? Boolean.TRUE : misc.error$V("null-list?: argument out of domain", new Object[] { paramObject }));
  }
  
  public static Object isProperList(Object paramObject) {
    Object object1 = paramObject;
    Object object2 = paramObject;
    paramObject = object1;
    while (lists.isPair(object2)) {
      object1 = lists.cdr.apply1(object2);
      if (lists.isPair(object1)) {
        object2 = lists.cdr.apply1(object1);
        paramObject = lists.cdr.apply1(paramObject);
        if (object2 == paramObject) {
          i = 1;
        } else {
          i = 0;
        } 
        int i = i + 1 & 0x1;
        if (i != 0)
          continue; 
        return (i != 0) ? Boolean.TRUE : Boolean.FALSE;
      } 
      return lists.isNull(object1) ? Boolean.TRUE : Boolean.FALSE;
    } 
    return lists.isNull(object2) ? Boolean.TRUE : Boolean.FALSE;
  }
  
  public static Object lambda1recur(Object paramObject1, Object paramObject2) {
    return lists.isPair(paramObject2) ? lists.cons(lists.car.apply1(paramObject1), lambda1recur(lists.cdr.apply1(paramObject1), lists.cdr.apply1(paramObject2))) : LList.Empty;
  }
  
  public static Object last(Object paramObject) {
    GenericProc genericProc = lists.car;
    try {
      Pair pair = (Pair)paramObject;
      return genericProc.apply1(lastPair(pair));
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "last-pair", 0, paramObject);
    } 
  }
  
  public static Object lastPair(Pair paramPair) {
    while (true) {
      Object object1;
      Object object2 = lists.cdr.apply1(paramPair);
      if (lists.isPair(object2)) {
        object1 = object2;
        continue;
      } 
      return object1;
    } 
  }
  
  public static Object length$Pl(Object paramObject) {
    Object object1 = Lit0;
    Object object2 = paramObject;
    Object object3 = paramObject;
    paramObject = object2;
    while (lists.isPair(object3)) {
      object2 = lists.cdr.apply1(object3);
      object1 = AddOp.$Pl.apply2(object1, Lit1);
      if (lists.isPair(object2)) {
        object3 = lists.cdr.apply1(object2);
        paramObject = lists.cdr.apply1(paramObject);
        object1 = AddOp.$Pl.apply2(object1, Lit1);
        if (object3 == paramObject) {
          i = 1;
        } else {
          i = 0;
        } 
        int i = i + 1 & 0x1;
        if (i != 0)
          continue; 
        return (i != 0) ? Boolean.TRUE : Boolean.FALSE;
      } 
      return object1;
    } 
    return object1;
  }
  
  public static Object list$Eq$V(Object paramObject, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    boolean bool = lists.isNull(lList);
    if (bool)
      return bool ? Boolean.TRUE : Boolean.FALSE; 
    Object object1 = lists.car.apply1(lList);
    Object object2 = lists.cdr.apply1(lList);
    label43: while (true) {
      bool = lists.isNull(object2);
      if (bool)
        return bool ? Boolean.TRUE : Boolean.FALSE; 
      Object object3 = lists.car.apply1(object2);
      Object object4 = lists.cdr.apply1(object2);
      if (object1 == object3) {
        object1 = object3;
        object2 = object4;
        continue;
      } 
      object2 = object3;
      while (true) {
        if (isNullList(object1) != Boolean.FALSE) {
          object1 = isNullList(object2);
          if (object1 != Boolean.FALSE) {
            object1 = object2;
            object2 = object4;
            continue label43;
          } 
          return object1;
        } 
        object3 = isNullList(object2);
        try {
          Boolean bool1 = Boolean.FALSE;
          if (object3 != bool1) {
            i = 1;
          } else {
            i = 0;
          } 
          int i = i + 1 & 0x1;
          if (i != 0) {
            object3 = Scheme.applyToArgs.apply3(paramObject, lists.car.apply1(object1), lists.car.apply1(object2));
            if (object3 != Boolean.FALSE) {
              object1 = lists.cdr.apply1(object1);
              object2 = lists.cdr.apply1(object2);
              continue;
            } 
            return object3;
          } 
          return (i != 0) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "x", -2, object3);
        } 
      } 
      break;
    } 
  }
  
  public static LList listCopy(LList paramLList) {
    Pair pair;
    LList lList2 = LList.Empty;
    LList lList1 = LList.Empty;
    while (true) {
      Pair pair2;
      if (lists.isPair(paramLList)) {
        pair2 = lists.cons(lists.car.apply1(paramLList), LList.Empty);
        if (lList1 == LList.Empty) {
          pair = pair2;
        } else {
          Pair pair3;
          try {
            Pair pair4 = (Pair)lList1;
            lists.setCdr$Ex(pair4, pair2);
            pair3 = pair2;
            paramLList = (LList)lists.cdr.apply1(paramLList);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "set-cdr!", 1, pair3);
          } 
          continue;
        } 
      } else {
        break;
      } 
      Pair pair1 = pair2;
      LList lList = (LList)lists.cdr.apply1(classCastException);
    } 
    return (LList)pair;
  }
  
  public static Object listIndex$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    frame30 frame30 = new frame30();
    frame30.pred = paramProcedure;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(lList))
      return frame30.lambda44lp(lists.cons(paramObject, lList), Lit0); 
    IntNum intNum = Lit0;
    while (true) {
      Object object = isNullList(paramObject);
      try {
        Boolean bool = Boolean.FALSE;
        if (object != bool) {
          i = 1;
        } else {
          i = 0;
        } 
        int i = i + 1 & 0x1;
        if (i != 0) {
          object = intNum;
          if (frame30.pred.apply1(lists.car.apply1(paramObject)) == Boolean.FALSE) {
            paramObject = lists.cdr.apply1(paramObject);
            Object object1 = AddOp.$Pl.apply2(intNum, Lit1);
            continue;
          } 
          return object;
        } 
        return (i != 0) ? Boolean.TRUE : Boolean.FALSE;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "x", -2, object);
      } 
    } 
  }
  
  public static Object listTabulate(Object paramObject, Procedure paramProcedure) {
    throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static Object lset$Eq$V(Procedure paramProcedure, Object[] paramArrayOfObject) {
    throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static Object lset$Ls$Eq$V(Procedure paramProcedure, Object[] paramArrayOfObject) {
    throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static Object lsetAdjoin$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    frame32 frame32 = new frame32();
    frame32.$Eq = paramProcedure;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    return fold$V((Procedure)frame32.lambda$Fn30, paramObject, lList, new Object[0]);
  }
  
  public static Object lsetDiff$PlIntersection$Ex$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    frame53 frame53 = new frame53();
    frame53.$Eq = paramProcedure;
    frame53.lists = LList.makeList(paramArrayOfObject, 0);
    return (every$V((Procedure)null$Mnlist$Qu, frame53.lists, new Object[0]) != Boolean.FALSE) ? misc.values(new Object[] { paramObject, LList.Empty }) : ((lists.memq(paramObject, frame53.lists) != Boolean.FALSE) ? misc.values(new Object[] { LList.Empty, paramObject }) : partition$Ex((Procedure)frame53.lambda$Fn55, paramObject));
  }
  
  public static Object lsetDiff$PlIntersection$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    frame51 frame51 = new frame51();
    frame51.$Eq = paramProcedure;
    frame51.lists = LList.makeList(paramArrayOfObject, 0);
    return (every$V((Procedure)null$Mnlist$Qu, frame51.lists, new Object[0]) != Boolean.FALSE) ? misc.values(new Object[] { paramObject, LList.Empty }) : ((lists.memq(paramObject, frame51.lists) != Boolean.FALSE) ? misc.values(new Object[] { LList.Empty, paramObject }) : partition((Procedure)frame51.lambda$Fn53, paramObject));
  }
  
  public static Object lsetDifference$Ex$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    frame43 frame43 = new frame43();
    frame43.$Eq = paramProcedure;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    frame43.lists = filter((Procedure)lists.pair$Qu, lList);
    return lists.isNull(frame43.lists) ? paramObject : ((lists.memq(paramObject, frame43.lists) != Boolean.FALSE) ? LList.Empty : filter$Ex((Procedure)frame43.lambda$Fn43, paramObject));
  }
  
  public static Object lsetDifference$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    frame41 frame41 = new frame41();
    frame41.$Eq = paramProcedure;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    frame41.lists = filter((Procedure)lists.pair$Qu, lList);
    return lists.isNull(frame41.lists) ? paramObject : ((lists.memq(paramObject, frame41.lists) != Boolean.FALSE) ? LList.Empty : filter((Procedure)frame41.lambda$Fn41, paramObject));
  }
  
  public static Object lsetIntersection$Ex$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    frame39 frame39 = new frame39();
    frame39.$Eq = paramProcedure;
    frame39.lists = delete(paramObject, LList.makeList(paramArrayOfObject, 0), Scheme.isEq);
    if (any$V((Procedure)null$Mnlist$Qu, frame39.lists, new Object[0]) != Boolean.FALSE)
      return LList.Empty; 
    Object object = paramObject;
    return !lists.isNull(frame39.lists) ? filter$Ex((Procedure)frame39.lambda$Fn39, paramObject) : object;
  }
  
  public static Object lsetIntersection$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    frame37 frame37 = new frame37();
    frame37.$Eq = paramProcedure;
    frame37.lists = delete(paramObject, LList.makeList(paramArrayOfObject, 0), Scheme.isEq);
    if (any$V((Procedure)null$Mnlist$Qu, frame37.lists, new Object[0]) != Boolean.FALSE)
      return LList.Empty; 
    Object object = paramObject;
    return !lists.isNull(frame37.lists) ? filter((Procedure)frame37.lambda$Fn37, paramObject) : object;
  }
  
  public static Object lsetUnion$Ex$V(Procedure paramProcedure, Object[] paramArrayOfObject) {
    frame35 frame35 = new frame35();
    frame35.$Eq = paramProcedure;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    return reduce((Procedure)frame35.lambda$Fn34, LList.Empty, lList);
  }
  
  public static Object lsetUnion$V(Procedure paramProcedure, Object[] paramArrayOfObject) {
    frame33 frame33 = new frame33();
    frame33.$Eq = paramProcedure;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    return reduce((Procedure)frame33.lambda$Fn31, LList.Empty, lList);
  }
  
  public static Object lsetXor$Ex$V(Procedure paramProcedure, Object[] paramArrayOfObject) {
    frame48 frame48 = new frame48();
    frame48.$Eq = paramProcedure;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    return reduce((Procedure)frame48.lambda$Fn49, LList.Empty, lList);
  }
  
  public static Object lsetXor$V(Procedure paramProcedure, Object[] paramArrayOfObject) {
    frame45 frame45 = new frame45();
    frame45.$Eq = paramProcedure;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    return reduce((Procedure)frame45.lambda$Fn45, LList.Empty, lList);
  }
  
  public static Object makeList$V(Object paramObject, Object[] paramArrayOfObject) {
    throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static Object map$Ex$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    frame12 frame12 = new frame12();
    frame12.f = paramProcedure;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(lList)) {
      Object object = paramObject;
      while (true) {
        if (isNullList(object) == Boolean.FALSE) {
          Object object1 = $PcCars$PlCdrs$SlNoTest$SlPair(lList);
          Object object2 = lists.car.apply1(object1);
          object1 = lists.cdr.apply1(object1);
          try {
            Pair pair = (Pair)object;
            lists.setCar$Ex(pair, Scheme.apply.apply3(frame12.f, lists.car.apply1(object), object2));
            object = lists.cdr.apply1(object);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "set-car!", 1, object);
          } 
          continue;
        } 
        return classCastException;
      } 
    } 
    pairForEach$V((Procedure)frame12.lambda$Fn11, classCastException, new Object[0]);
    return classCastException;
  }
  
  public static Object ninth(Object paramObject) {
    return lists.car.apply1(lists.cddddr.apply1(lists.cddddr.apply1(paramObject)));
  }
  
  public static Object pairFold$V(Procedure paramProcedure, Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(lList))
      for (paramObject2 = lists.cons(paramObject2, lList);; paramObject2 = object) {
        Object object = $PcCdrs(paramObject2);
        if (lists.isNull(object))
          return paramObject1; 
        paramObject1 = Scheme.apply.apply2(paramProcedure, append$Ex$V(new Object[] { paramObject2, LList.list1(paramObject1) }));
      }  
    while (true) {
      Object object = paramObject1;
      if (isNullList(paramObject2) == Boolean.FALSE) {
        object = lists.cdr.apply1(paramObject2);
        paramObject1 = paramProcedure.apply2(paramObject2, paramObject1);
        paramObject2 = object;
        continue;
      } 
      return object;
    } 
  }
  
  public static Object pairFoldRight$V(Procedure paramProcedure, Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame10 frame10 = new frame10();
    frame10.f = paramProcedure;
    frame10.zero = paramObject1;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    return lists.isPair(lList) ? frame10.lambda19recur(lists.cons(paramObject2, lList)) : frame10.lambda20recur(paramObject2);
  }
  
  public static Object pairForEach$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(lList)) {
      paramObject = lists.cons(paramObject, lList);
      while (true) {
        Object object = $PcCdrs(paramObject);
        if (lists.isPair(object)) {
          Scheme.apply.apply2(paramProcedure, paramObject);
          paramObject = object;
          continue;
        } 
        return Values.empty;
      } 
    } 
    while (isNullList(paramObject) == Boolean.FALSE) {
      Object object = lists.cdr.apply1(paramObject);
      paramProcedure.apply1(paramObject);
      paramObject = object;
    } 
    return Values.empty;
  }
  
  public static Object partition(Procedure paramProcedure, Object paramObject) {
    LList lList2 = LList.Empty;
    LList lList1 = LList.Empty;
    while (true) {
      if (isNullList(paramObject) != Boolean.FALSE)
        try {
          LList lList = lList2;
          lList = lists.reverse$Ex(lList);
          try {
            paramObject = lList1;
            return misc.values(new Object[] { lList, lists.reverse$Ex((LList)paramObject) });
          } catch (ClassCastException null) {
            throw new WrongType(classCastException, "reverse!", 1, lList1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "reverse!", 1, lList2);
        }  
      Object object = lists.car.apply1(paramObject);
      paramObject = lists.cdr.apply1(paramObject);
      if (classCastException.apply1(object) != Boolean.FALSE) {
        Pair pair1 = lists.cons(object, lList2);
        continue;
      } 
      Pair pair = lists.cons(object, lList1);
    } 
  }
  
  public static Object partition$Ex(Procedure paramProcedure, Object paramObject) {
    Pair pair3 = lists.cons(Lit2, LList.Empty);
    Pair pair4 = lists.cons(Lit2, LList.Empty);
    Pair pair2 = pair3;
    Pair pair1 = pair4;
    while (true) {
      Object object;
      if (isNotPair(paramObject))
        try {
          Pair pair = pair2;
          lists.setCdr$Ex(pair, LList.Empty);
          try {
            pair = pair1;
            lists.setCdr$Ex(pair, LList.Empty);
            return misc.values(new Object[] { lists.cdr.apply1(pair3), lists.cdr.apply1(pair4) });
          } catch (ClassCastException null) {
            throw new WrongType(classCastException, "set-cdr!", 1, pair1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-cdr!", 1, pair2);
        }  
      if (classCastException.apply1(lists.car.apply1(paramObject)) != Boolean.FALSE) {
        Object object1;
        try {
          Pair pair = pair2;
          lists.setCdr$Ex(pair, paramObject);
          Object object2 = lists.cdr.apply1(paramObject);
          object1 = paramObject;
          paramObject = object2;
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "set-cdr!", 1, object1);
        } 
        continue;
      } 
      try {
        Pair pair = pair1;
        lists.setCdr$Ex(pair, paramObject);
        Object object1 = lists.cdr.apply1(paramObject);
        object = paramObject;
        paramObject = object1;
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "set-cdr!", 1, object);
      } 
    } 
  }
  
  public static Object reduce(Procedure paramProcedure, Object paramObject1, Object paramObject2) {
    return (isNullList(paramObject2) != Boolean.FALSE) ? paramObject1 : fold$V(paramProcedure, lists.car.apply1(paramObject2), lists.cdr.apply1(paramObject2), new Object[0]);
  }
  
  public static Object reduceRight(Procedure paramProcedure, Object paramObject1, Object paramObject2) {
    frame11 frame11 = new frame11();
    frame11.f = paramProcedure;
    return (isNullList(paramObject2) != Boolean.FALSE) ? paramObject1 : frame11.lambda21recur(lists.car.apply1(paramObject2), lists.cdr.apply1(paramObject2));
  }
  
  public static Object remove(Object paramObject1, Object paramObject2) {
    frame15 frame15 = new frame15();
    frame15.pred = paramObject1;
    return filter((Procedure)frame15.lambda$Fn14, paramObject2);
  }
  
  public static Object remove$Ex(Object paramObject1, Object paramObject2) {
    frame16 frame16 = new frame16();
    frame16.pred = paramObject1;
    return filter$Ex((Procedure)frame16.lambda$Fn15, paramObject2);
  }
  
  public static Object seventh(Object paramObject) {
    return lists.caddr.apply1(lists.cddddr.apply1(paramObject));
  }
  
  public static Object sixth(Object paramObject) {
    return lists.cadr.apply1(lists.cddddr.apply1(paramObject));
  }
  
  public static Object span(Procedure paramProcedure, Object paramObject) {
    LList lList = LList.Empty;
    Object object = paramObject;
    paramObject = lList;
    while (true) {
      if (isNullList(object) != Boolean.FALSE)
        try {
          LList lList1 = (LList)paramObject;
          return misc.values(new Object[] { lists.reverse$Ex(lList1), object });
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "reverse!", 1, paramObject);
        }  
      Object object1 = lists.car.apply1(object);
      if (classCastException.apply1(object1) != Boolean.FALSE) {
        object = lists.cdr.apply1(object);
        paramObject = lists.cons(object1, paramObject);
        continue;
      } 
      try {
        LList lList1 = (LList)paramObject;
        return misc.values(new Object[] { lists.reverse$Ex(lList1), object });
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "reverse!", 1, paramObject);
      } 
    } 
  }
  
  public static Object span$Ex(Procedure paramProcedure, Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic isNullList : (Ljava/lang/Object;)Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   9: if_acmpeq -> 37
    //   12: aload_2
    //   13: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   16: if_acmpeq -> 54
    //   19: iconst_2
    //   20: anewarray java/lang/Object
    //   23: dup
    //   24: iconst_0
    //   25: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   28: aastore
    //   29: dup
    //   30: iconst_1
    //   31: aload_1
    //   32: aastore
    //   33: invokestatic values : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   36: areturn
    //   37: aload_0
    //   38: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   41: aload_1
    //   42: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   45: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   48: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   51: if_acmpeq -> 19
    //   54: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   57: aload_1
    //   58: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   61: astore_2
    //   62: aload_1
    //   63: astore_3
    //   64: aload_2
    //   65: invokestatic isNullList : (Ljava/lang/Object;)Ljava/lang/Object;
    //   68: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   71: if_acmpeq -> 90
    //   74: iconst_2
    //   75: anewarray java/lang/Object
    //   78: dup
    //   79: iconst_0
    //   80: aload_1
    //   81: aastore
    //   82: dup
    //   83: iconst_1
    //   84: aload_2
    //   85: aastore
    //   86: invokestatic values : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   89: areturn
    //   90: aload_0
    //   91: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   94: aload_2
    //   95: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   98: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   101: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   104: if_acmpeq -> 124
    //   107: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   110: aload_2
    //   111: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   114: astore #4
    //   116: aload_2
    //   117: astore_3
    //   118: aload #4
    //   120: astore_2
    //   121: goto -> 64
    //   124: aload_3
    //   125: checkcast gnu/lists/Pair
    //   128: astore_0
    //   129: aload_0
    //   130: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   133: invokestatic setCdr$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   136: goto -> 74
    //   139: astore_0
    //   140: new gnu/mapping/WrongType
    //   143: dup
    //   144: aload_0
    //   145: ldc_w 'set-cdr!'
    //   148: iconst_1
    //   149: aload_3
    //   150: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   153: athrow
    // Exception table:
    //   from	to	target	type
    //   124	129	139	java/lang/ClassCastException
  }
  
  public static Object splitAt(Object paramObject, IntNum paramIntNum) {
    Object object1;
    LList lList = LList.Empty;
    Object object2 = paramObject;
    paramObject = lList;
    try {
      while (true) {
        Number number = (Number)paramIntNum;
        if (numbers.isZero(number))
          try {
            LList lList1 = (LList)paramObject;
            return misc.values(new Object[] { lists.reverse$Ex(lList1), object2 });
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "reverse!", 1, paramObject);
          }  
        paramObject = lists.cons(lists.car.apply1(object2), paramObject);
        object2 = lists.cdr.apply1(object2);
        object1 = AddOp.$Mn.apply2(classCastException, Lit1);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "zero?", 1, object1);
    } 
  }
  
  public static Object splitAt$Ex(Object paramObject, IntNum paramIntNum) {
    if (numbers.isZero((Number)paramIntNum))
      return misc.values(new Object[] { LList.Empty, paramObject }); 
    Object object1 = drop(paramObject, IntNum.add(paramIntNum, -1));
    Object object2 = lists.cdr.apply1(object1);
    try {
      Pair pair = (Pair)object1;
      lists.setCdr$Ex(pair, LList.Empty);
      return misc.values(new Object[] { paramObject, object2 });
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "set-cdr!", 1, object1);
    } 
  }
  
  public static Object take(Object paramObject, IntNum paramIntNum) {
    Object object1;
    LList lList = LList.Empty;
    Object object2 = paramObject;
    paramObject = lList;
    try {
      while (true) {
        Number number = (Number)paramIntNum;
        if (numbers.isZero(number))
          try {
            LList lList1 = (LList)paramObject;
            return lists.reverse$Ex(lList1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "reverse!", 1, paramObject);
          }  
        Object object = lists.cdr.apply1(object2);
        object1 = AddOp.$Mn.apply2(classCastException, Lit1);
        paramObject = lists.cons(lists.car.apply1(object2), paramObject);
        object2 = object;
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "zero?", 1, object1);
    } 
  }
  
  public static Object take$Ex(Object paramObject, IntNum paramIntNum) {
    if (numbers.isZero((Number)paramIntNum))
      return LList.Empty; 
    Object object = drop(paramObject, IntNum.add(paramIntNum, -1));
    try {
      Pair pair = (Pair)object;
      lists.setCdr$Ex(pair, LList.Empty);
      return paramObject;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "set-cdr!", 1, object);
    } 
  }
  
  public static Object takeRight(Object paramObject, IntNum paramIntNum) {
    Object object2 = drop(paramObject, paramIntNum);
    Object object1 = paramObject;
    for (paramObject = object2; lists.isPair(paramObject); paramObject = lists.cdr.apply1(paramObject))
      object1 = lists.cdr.apply1(object1); 
    return object1;
  }
  
  public static Object takeWhile(Procedure paramProcedure, Object paramObject) {
    frame23 frame23 = new frame23();
    frame23.pred = paramProcedure;
    return frame23.lambda34recur(paramObject);
  }
  
  public static Object takeWhile$Ex(Procedure paramProcedure, Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic isNullList : (Ljava/lang/Object;)Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   9: if_acmpeq -> 27
    //   12: aload_2
    //   13: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   16: if_acmpeq -> 44
    //   19: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   22: astore #4
    //   24: aload #4
    //   26: areturn
    //   27: aload_0
    //   28: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   31: aload_1
    //   32: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   35: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   38: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   41: if_acmpeq -> 19
    //   44: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   47: aload_1
    //   48: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   51: astore_2
    //   52: aload_1
    //   53: astore_3
    //   54: aload_1
    //   55: astore #4
    //   57: aload_2
    //   58: invokestatic isPair : (Ljava/lang/Object;)Z
    //   61: ifeq -> 24
    //   64: aload_0
    //   65: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   68: aload_2
    //   69: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   72: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   75: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   78: if_acmpeq -> 98
    //   81: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   84: aload_2
    //   85: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   88: astore #4
    //   90: aload_2
    //   91: astore_3
    //   92: aload #4
    //   94: astore_2
    //   95: goto -> 54
    //   98: aload_3
    //   99: checkcast gnu/lists/Pair
    //   102: astore_0
    //   103: aload_0
    //   104: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   107: invokestatic setCdr$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   110: aload_1
    //   111: areturn
    //   112: astore_0
    //   113: new gnu/mapping/WrongType
    //   116: dup
    //   117: aload_0
    //   118: ldc_w 'set-cdr!'
    //   121: iconst_1
    //   122: aload_3
    //   123: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   126: athrow
    // Exception table:
    //   from	to	target	type
    //   98	103	112	java/lang/ClassCastException
  }
  
  public static Object tenth(Object paramObject) {
    return lists.cadr.apply1(lists.cddddr.apply1(lists.cddddr.apply1(paramObject)));
  }
  
  public static Object unfold$V(Procedure paramProcedure1, Procedure paramProcedure2, Procedure paramProcedure3, Object paramObject, Object[] paramArrayOfObject) {
    LList lList1 = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(lList1)) {
      Object object2 = lists.car.apply1(lList1);
      if (lists.isPair(lists.cdr.apply1(lList1)))
        return Scheme.apply.applyN(new Object[] { misc.error, "Too many arguments", unfold, paramProcedure1, paramProcedure2, paramProcedure3, paramObject, lList1 }); 
      LList lList = LList.Empty;
      Object object1 = paramObject;
      paramObject = lList;
      while (true) {
        if (paramProcedure1.apply1(object1) != Boolean.FALSE)
          return appendReverse$Ex(paramObject, Scheme.applyToArgs.apply2(object2, object1)); 
        Object object3 = paramProcedure3.apply1(object1);
        paramObject = lists.cons(paramProcedure2.apply1(object1), paramObject);
        object1 = object3;
      } 
    } 
    LList lList2 = LList.Empty;
    Object object = paramObject;
    paramObject = lList2;
    while (true) {
      if (paramProcedure1.apply1(object) != Boolean.FALSE)
        try {
          LList lList = (LList)paramObject;
          return lists.reverse$Ex(lList);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "reverse!", 1, paramObject);
        }  
      Object object1 = paramProcedure3.apply1(object);
      paramObject = lists.cons(paramProcedure2.apply1(object), paramObject);
      object = object1;
    } 
  }
  
  public static Object unfoldRight(Procedure paramProcedure1, Procedure paramProcedure2, Procedure paramProcedure3, Object paramObject) {
    return unfoldRight(paramProcedure1, paramProcedure2, paramProcedure3, paramObject, LList.Empty);
  }
  
  public static Object unfoldRight(Procedure paramProcedure1, Procedure paramProcedure2, Procedure paramProcedure3, Object paramObject1, Object paramObject2) {
    while (true) {
      if (paramProcedure1.apply1(paramObject1) != Boolean.FALSE)
        return paramObject2; 
      Object object = paramProcedure3.apply1(paramObject1);
      paramObject2 = lists.cons(paramProcedure2.apply1(paramObject1), paramObject2);
      paramObject1 = object;
    } 
  }
  
  public static LList unzip1(Object paramObject) {
    LList lList = LList.Empty;
    while (true) {
      if (paramObject == LList.Empty)
        return LList.reverseInPlace(lList); 
      try {
        Pair pair2 = (Pair)paramObject;
        paramObject = pair2.getCdr();
        Pair pair1 = Pair.make(lists.car.apply1(pair2.getCar()), lList);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, paramObject);
      } 
    } 
  }
  
  public static Object unzip2(Object paramObject) {
    new frame();
    return frame.lambda2recur(paramObject);
  }
  
  public static Object unzip3(Object paramObject) {
    new frame1();
    return frame1.lambda5recur(paramObject);
  }
  
  public static Object unzip4(Object paramObject) {
    new frame3();
    return frame3.lambda8recur(paramObject);
  }
  
  public static Object unzip5(Object paramObject) {
    new frame5();
    return frame5.lambda11recur(paramObject);
  }
  
  public static Pair xcons(Object paramObject1, Object paramObject2) {
    return lists.cons(paramObject2, paramObject1);
  }
  
  public static Object zip$V(Object paramObject, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    return Scheme.apply.apply4(Scheme.map, LangObjType.listType, paramObject, lList);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 82:
        try {
          LList lList = (LList)paramObject;
          return listCopy(lList);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "list-copy", 1, paramObject);
        } 
      case 83:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject);
          return iota(intNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "iota", 1, paramObject);
        } 
      case 87:
        return isProperList(paramObject);
      case 88:
        return isDottedList(paramObject);
      case 89:
        return isCircularList(paramObject);
      case 90:
        return isNotPair(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 91:
        return isNullList(paramObject);
      case 93:
        return length$Pl(paramObject);
      case 95:
        return fifth(paramObject);
      case 96:
        return sixth(paramObject);
      case 97:
        return seventh(paramObject);
      case 98:
        return eighth(paramObject);
      case 99:
        return ninth(paramObject);
      case 100:
        return tenth(paramObject);
      case 101:
        return car$PlCdr(paramObject);
      case 110:
        return last(paramObject);
      case 111:
        try {
          Pair pair = (Pair)paramObject;
          return lastPair(pair);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "last-pair", 1, paramObject);
        } 
      case 112:
        return unzip1(paramObject);
      case 113:
        return unzip2(paramObject);
      case 114:
        return unzip3(paramObject);
      case 115:
        return unzip4(paramObject);
      case 116:
        return unzip5(paramObject);
      case 120:
        return concatenate(paramObject);
      case 121:
        return concatenate$Ex(paramObject);
      case 147:
        return deleteDuplicates(paramObject);
      case 149:
        return deleteDuplicates$Ex(paramObject);
      case 152:
        break;
    } 
    return alistCopy(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 78:
        return xcons(paramObject1, paramObject2);
      case 80:
        try {
          Procedure procedure = (Procedure)paramObject2;
          return listTabulate(paramObject1, procedure);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "list-tabulate", 2, paramObject2);
        } 
      case 83:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject1);
          try {
            paramObject1 = LangObjType.coerceNumeric(paramObject2);
            return iota(intNum, (Numeric)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "iota", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "iota", 1, paramObject1);
        } 
      case 102:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject2);
          return take(paramObject1, intNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "take", 2, paramObject2);
        } 
      case 103:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject2);
          return drop(paramObject1, intNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "drop", 2, paramObject2);
        } 
      case 104:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject2);
          return take$Ex(paramObject1, intNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "take!", 2, paramObject2);
        } 
      case 105:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject2);
          return takeRight(paramObject1, intNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "take-right", 2, paramObject2);
        } 
      case 106:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject2);
          return dropRight(paramObject1, intNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "drop-right", 2, paramObject2);
        } 
      case 107:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject2);
          return dropRight$Ex(paramObject1, intNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "drop-right!", 2, paramObject2);
        } 
      case 108:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject2);
          return splitAt(paramObject1, intNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "split-at", 2, paramObject2);
        } 
      case 109:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject2);
          return splitAt$Ex(paramObject1, intNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "split-at!", 2, paramObject2);
        } 
      case 118:
        return appendReverse(paramObject1, paramObject2);
      case 119:
        return appendReverse$Ex(paramObject1, paramObject2);
      case 137:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return filter(procedure, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "filter", 1, paramObject1);
        } 
      case 138:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return filter$Ex(procedure, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "filter!", 1, paramObject1);
        } 
      case 139:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return partition(procedure, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "partition", 1, paramObject1);
        } 
      case 140:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return partition$Ex(procedure, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "partition!", 1, paramObject1);
        } 
      case 141:
        return remove(paramObject1, paramObject2);
      case 142:
        return remove$Ex(paramObject1, paramObject2);
      case 143:
        return delete(paramObject1, paramObject2);
      case 145:
        return delete$Ex(paramObject1, paramObject2);
      case 147:
        try {
          Procedure procedure = (Procedure)paramObject2;
          return deleteDuplicates(paramObject1, procedure);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "delete-duplicates", 2, paramObject2);
        } 
      case 149:
        try {
          Procedure procedure = (Procedure)paramObject2;
          return deleteDuplicates$Ex(paramObject1, procedure);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "delete-duplicates!", 2, paramObject2);
        } 
      case 153:
        return alistDelete(paramObject1, paramObject2);
      case 155:
        return alistDelete$Ex(paramObject1, paramObject2);
      case 157:
        return find(paramObject1, paramObject2);
      case 158:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return findTail(procedure, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "find-tail", 1, paramObject1);
        } 
      case 159:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return takeWhile(procedure, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "take-while", 1, paramObject1);
        } 
      case 160:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return dropWhile(procedure, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "drop-while", 1, paramObject1);
        } 
      case 161:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return takeWhile$Ex(procedure, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "take-while!", 1, paramObject1);
        } 
      case 162:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return span(procedure, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "span", 1, paramObject1);
        } 
      case 163:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return span$Ex(procedure, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "span!", 1, paramObject1);
        } 
      case 164:
        return break(paramObject1, paramObject2);
      case 165:
        return break$Ex(paramObject1, paramObject2);
      case 182:
        return frame61.lambda84(paramObject1, paramObject2);
      case 183:
        break;
    } 
    return frame71.lambda100(paramObject1, paramObject2);
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 83:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject1);
          try {
            paramObject1 = LangObjType.coerceNumeric(paramObject2);
            try {
              paramObject2 = LangObjType.coerceNumeric(paramObject3);
              return iota(intNum, (Numeric)paramObject1, (Numeric)paramObject2);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "iota", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "iota", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "iota", 1, paramObject1);
        } 
      case 130:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return reduce(procedure, paramObject2, paramObject3);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "reduce", 1, paramObject1);
        } 
      case 131:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return reduceRight(procedure, paramObject2, paramObject3);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "reduce-right", 1, paramObject1);
        } 
      case 143:
        return delete(paramObject1, paramObject2, paramObject3);
      case 145:
        return delete$Ex(paramObject1, paramObject2, paramObject3);
      case 151:
        return alistCons(paramObject1, paramObject2, paramObject3);
      case 153:
        return alistDelete(paramObject1, paramObject2, paramObject3);
      case 155:
        break;
    } 
    return alistDelete$Ex(paramObject1, paramObject2, paramObject3);
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    if (paramModuleMethod.selector == 123)
      try {
        Procedure procedure = (Procedure)paramObject1;
        try {
          paramObject1 = paramObject2;
          try {
            paramObject2 = paramObject3;
            return unfoldRight(procedure, (Procedure)paramObject1, (Procedure)paramObject2, paramObject4);
          } catch (ClassCastException null) {
            throw new WrongType(classCastException, "unfold-right", 3, paramObject3);
          } 
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "unfold-right", 2, paramObject2);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "unfold-right", 1, paramObject1);
      }  
    return super.apply4((ModuleMethod)classCastException, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    Object object1;
    int i;
    Object[] arrayOfObject1;
    Object[] arrayOfObject2;
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 79:
        object1 = paramArrayOfObject[0];
        i = paramArrayOfObject.length - 1;
        arrayOfObject1 = new Object[i];
        while (true) {
          if (--i < 0)
            return makeList$V(object1, arrayOfObject1); 
          arrayOfObject1[i] = paramArrayOfObject[i + 1];
        } 
      case 81:
        return cons$St(paramArrayOfObject);
      case 86:
        object1 = paramArrayOfObject[0];
        i = paramArrayOfObject.length - 1;
        arrayOfObject1 = new Object[i];
        while (true) {
          if (--i < 0)
            return circularList$V(object1, arrayOfObject1); 
          arrayOfObject1[i] = paramArrayOfObject[i + 1];
        } 
      case 92:
        object1 = paramArrayOfObject[0];
        i = paramArrayOfObject.length - 1;
        arrayOfObject1 = new Object[i];
        while (true) {
          if (--i < 0)
            return list$Eq$V(object1, arrayOfObject1); 
          arrayOfObject1[i] = paramArrayOfObject[i + 1];
        } 
      case 94:
        object1 = paramArrayOfObject[0];
        i = paramArrayOfObject.length - 1;
        arrayOfObject1 = new Object[i];
        while (true) {
          if (--i < 0)
            return zip$V(object1, arrayOfObject1); 
          arrayOfObject1[i] = paramArrayOfObject[i + 1];
        } 
      case 117:
        return append$Ex$V(paramArrayOfObject);
      case 122:
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          object2 = paramArrayOfObject[1];
          i = paramArrayOfObject.length - 2;
          Object[] arrayOfObject = new Object[i];
          while (true) {
            if (--i < 0)
              return count$V((Procedure)object1, object2, arrayOfObject); 
            arrayOfObject[i] = paramArrayOfObject[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "count", 1, object2);
        } 
      case 123:
        i = paramArrayOfObject.length - 4;
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          Object object = paramArrayOfObject[1];
          try {
            object2 = object;
            object = paramArrayOfObject[2];
            try {
              Procedure procedure = (Procedure)object;
              object = paramArrayOfObject[3];
              return (i <= 0) ? unfoldRight((Procedure)object1, (Procedure)object2, procedure, object) : unfoldRight((Procedure)object1, (Procedure)object2, procedure, object, paramArrayOfObject[4]);
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "unfold-right", 3, object);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "unfold-right", 2, object);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "unfold-right", 1, object2);
        } 
      case 125:
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          Object object = paramArrayOfObject[1];
          try {
            object2 = object;
            Object object3 = paramArrayOfObject[2];
            try {
              object = object3;
              object3 = paramArrayOfObject[3];
              i = paramArrayOfObject.length - 4;
              Object[] arrayOfObject = new Object[i];
              while (true) {
                if (--i < 0)
                  return unfold$V((Procedure)object1, (Procedure)object2, (Procedure)object, object3, arrayOfObject); 
                arrayOfObject[i] = paramArrayOfObject[i + 4];
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "unfold", 3, object3);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "unfold", 2, object);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "unfold", 1, object2);
        } 
      case 126:
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          object2 = paramArrayOfObject[1];
          Object object = paramArrayOfObject[2];
          i = paramArrayOfObject.length - 3;
          Object[] arrayOfObject = new Object[i];
          while (true) {
            if (--i < 0)
              return fold$V((Procedure)object1, object2, object, arrayOfObject); 
            arrayOfObject[i] = paramArrayOfObject[i + 3];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "fold", 1, object2);
        } 
      case 127:
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          object2 = paramArrayOfObject[1];
          Object object = paramArrayOfObject[2];
          i = paramArrayOfObject.length - 3;
          Object[] arrayOfObject = new Object[i];
          while (true) {
            if (--i < 0)
              return foldRight$V((Procedure)object1, object2, object, arrayOfObject); 
            arrayOfObject[i] = paramArrayOfObject[i + 3];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "fold-right", 1, object2);
        } 
      case 128:
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          object2 = paramArrayOfObject[1];
          Object object = paramArrayOfObject[2];
          i = paramArrayOfObject.length - 3;
          Object[] arrayOfObject = new Object[i];
          while (true) {
            if (--i < 0)
              return pairFoldRight$V((Procedure)object1, object2, object, arrayOfObject); 
            arrayOfObject[i] = paramArrayOfObject[i + 3];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "pair-fold-right", 1, object2);
        } 
      case 129:
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          object2 = paramArrayOfObject[1];
          Object object = paramArrayOfObject[2];
          i = paramArrayOfObject.length - 3;
          Object[] arrayOfObject = new Object[i];
          while (true) {
            if (--i < 0)
              return pairFold$V((Procedure)object1, object2, object, arrayOfObject); 
            arrayOfObject[i] = paramArrayOfObject[i + 3];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "pair-fold", 1, object2);
        } 
      case 132:
        object1 = paramArrayOfObject[0];
        object2 = paramArrayOfObject[1];
        i = paramArrayOfObject.length - 2;
        arrayOfObject2 = new Object[i];
        while (true) {
          if (--i < 0)
            return appendMap$V(object1, object2, arrayOfObject2); 
          arrayOfObject2[i] = paramArrayOfObject[i + 2];
        } 
      case 133:
        object1 = paramArrayOfObject[0];
        object2 = paramArrayOfObject[1];
        i = paramArrayOfObject.length - 2;
        arrayOfObject2 = new Object[i];
        while (true) {
          if (--i < 0)
            return appendMap$Ex$V(object1, object2, arrayOfObject2); 
          arrayOfObject2[i] = paramArrayOfObject[i + 2];
        } 
      case 134:
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          object2 = paramArrayOfObject[1];
          i = paramArrayOfObject.length - 2;
          arrayOfObject2 = new Object[i];
          while (true) {
            if (--i < 0)
              return pairForEach$V((Procedure)object1, object2, arrayOfObject2); 
            arrayOfObject2[i] = paramArrayOfObject[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "pair-for-each", 1, object2);
        } 
      case 135:
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          object2 = paramArrayOfObject[1];
          i = paramArrayOfObject.length - 2;
          arrayOfObject2 = new Object[i];
          while (true) {
            if (--i < 0)
              return map$Ex$V((Procedure)object1, object2, arrayOfObject2); 
            arrayOfObject2[i] = paramArrayOfObject[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "map!", 1, object2);
        } 
      case 136:
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          object2 = paramArrayOfObject[1];
          i = paramArrayOfObject.length - 2;
          arrayOfObject2 = new Object[i];
          while (true) {
            if (--i < 0)
              return filterMap$V((Procedure)object1, object2, arrayOfObject2); 
            arrayOfObject2[i] = paramArrayOfObject[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "filter-map", 1, object2);
        } 
      case 166:
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          object2 = paramArrayOfObject[1];
          i = paramArrayOfObject.length - 2;
          arrayOfObject2 = new Object[i];
          while (true) {
            if (--i < 0)
              return any$V((Procedure)object1, object2, arrayOfObject2); 
            arrayOfObject2[i] = paramArrayOfObject[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "any", 1, object2);
        } 
      case 167:
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          object2 = paramArrayOfObject[1];
          i = paramArrayOfObject.length - 2;
          arrayOfObject2 = new Object[i];
          while (true) {
            if (--i < 0)
              return every$V((Procedure)object1, object2, arrayOfObject2); 
            arrayOfObject2[i] = paramArrayOfObject[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "every", 1, object2);
        } 
      case 168:
        object2 = paramArrayOfObject[0];
        try {
          object1 = object2;
          object2 = paramArrayOfObject[1];
          i = paramArrayOfObject.length - 2;
          arrayOfObject2 = new Object[i];
          while (true) {
            if (--i < 0)
              return listIndex$V((Procedure)object1, object2, arrayOfObject2); 
            arrayOfObject2[i] = paramArrayOfObject[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "list-index", 1, object2);
        } 
      case 169:
        object1 = paramArrayOfObject[0];
        try {
          object2 = object1;
          i = paramArrayOfObject.length - 1;
          object1 = new Object[i];
          while (true) {
            if (--i < 0)
              return lset$Ls$Eq$V((Procedure)object2, (Object[])object1); 
            object1[i] = paramArrayOfObject[i + 1];
          } 
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "lset<=", 1, object1);
        } 
      case 170:
        object1 = classCastException[0];
        try {
          object2 = object1;
          i = classCastException.length - 1;
          object1 = new Object[i];
          while (true) {
            if (--i < 0)
              return lset$Eq$V((Procedure)object2, (Object[])object1); 
            object1[i] = classCastException[i + 1];
          } 
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "lset=", 1, object1);
        } 
      case 171:
        object2 = classCastException[0];
        try {
          object1 = object2;
          object2 = classCastException[1];
          i = classCastException.length - 2;
          arrayOfObject2 = new Object[i];
          while (true) {
            if (--i < 0)
              return lsetAdjoin$V((Procedure)object1, object2, arrayOfObject2); 
            arrayOfObject2[i] = classCastException[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "lset-adjoin", 1, object2);
        } 
      case 172:
        object1 = classCastException[0];
        try {
          object2 = object1;
          i = classCastException.length - 1;
          object1 = new Object[i];
          while (true) {
            if (--i < 0)
              return lsetUnion$V((Procedure)object2, (Object[])object1); 
            object1[i] = classCastException[i + 1];
          } 
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "lset-union", 1, object1);
        } 
      case 173:
        object1 = classCastException[0];
        try {
          object2 = object1;
          i = classCastException.length - 1;
          object1 = new Object[i];
          while (true) {
            if (--i < 0)
              return lsetUnion$Ex$V((Procedure)object2, (Object[])object1); 
            object1[i] = classCastException[i + 1];
          } 
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "lset-union!", 1, object1);
        } 
      case 174:
        object2 = classCastException[0];
        try {
          object1 = object2;
          object2 = classCastException[1];
          i = classCastException.length - 2;
          arrayOfObject2 = new Object[i];
          while (true) {
            if (--i < 0)
              return lsetIntersection$V((Procedure)object1, object2, arrayOfObject2); 
            arrayOfObject2[i] = classCastException[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "lset-intersection", 1, object2);
        } 
      case 175:
        object2 = classCastException[0];
        try {
          object1 = object2;
          object2 = classCastException[1];
          i = classCastException.length - 2;
          arrayOfObject2 = new Object[i];
          while (true) {
            if (--i < 0)
              return lsetIntersection$Ex$V((Procedure)object1, object2, arrayOfObject2); 
            arrayOfObject2[i] = classCastException[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "lset-intersection!", 1, object2);
        } 
      case 176:
        object2 = classCastException[0];
        try {
          object1 = object2;
          object2 = classCastException[1];
          i = classCastException.length - 2;
          arrayOfObject2 = new Object[i];
          while (true) {
            if (--i < 0)
              return lsetDifference$V((Procedure)object1, object2, arrayOfObject2); 
            arrayOfObject2[i] = classCastException[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "lset-difference", 1, object2);
        } 
      case 177:
        object2 = classCastException[0];
        try {
          object1 = object2;
          object2 = classCastException[1];
          i = classCastException.length - 2;
          arrayOfObject2 = new Object[i];
          while (true) {
            if (--i < 0)
              return lsetDifference$Ex$V((Procedure)object1, object2, arrayOfObject2); 
            arrayOfObject2[i] = classCastException[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "lset-difference!", 1, object2);
        } 
      case 178:
        object1 = classCastException[0];
        try {
          object2 = object1;
          i = classCastException.length - 1;
          object1 = new Object[i];
          while (true) {
            if (--i < 0)
              return lsetXor$V((Procedure)object2, (Object[])object1); 
            object1[i] = classCastException[i + 1];
          } 
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "lset-xor", 1, object1);
        } 
      case 179:
        object1 = classCastException[0];
        try {
          object2 = object1;
          i = classCastException.length - 1;
          object1 = new Object[i];
          while (true) {
            if (--i < 0)
              return lsetXor$Ex$V((Procedure)object2, (Object[])object1); 
            object1[i] = classCastException[i + 1];
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lset-xor!", 1, object1);
        } 
      case 180:
        object2 = classCastException[0];
        try {
          object1 = object2;
          object2 = classCastException[1];
          i = classCastException.length - 2;
          arrayOfObject2 = new Object[i];
          while (true) {
            if (--i < 0)
              return lsetDiff$PlIntersection$V((Procedure)object1, object2, arrayOfObject2); 
            arrayOfObject2[i] = classCastException[i + 2];
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "lset-diff+intersection", 1, object2);
        } 
      case 181:
        break;
    } 
    Object object2 = classCastException[0];
    try {
      object1 = object2;
      object2 = classCastException[1];
      i = classCastException.length - 2;
      arrayOfObject2 = new Object[i];
      while (true) {
        if (--i < 0)
          return lsetDiff$PlIntersection$Ex$V((Procedure)object1, object2, arrayOfObject2); 
        arrayOfObject2[i] = classCastException[i + 2];
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "lset-diff+intersection!", 1, object2);
    } 
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 152:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 149:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 147:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 121:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 120:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 116:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 115:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 114:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 113:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 112:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 111:
        if (!(paramObject instanceof Pair))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 110:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 101:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 100:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 99:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 98:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 97:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 96:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 95:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 93:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 91:
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
      case 87:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 83:
        if (IntNum.asIntNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 82:
        break;
    } 
    if (paramObject instanceof LList) {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    } 
    return -786431;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 183:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 182:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 165:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 164:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 163:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 162:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 161:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 160:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 159:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 158:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 157:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 155:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 153:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 149:
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof Procedure))
          return -786430; 
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 147:
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof Procedure))
          return -786430; 
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 145:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 143:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 142:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 141:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 140:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 139:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 138:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 137:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 119:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 118:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 109:
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 108:
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 107:
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 106:
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 105:
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 104:
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 103:
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 102:
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 83:
        if (IntNum.asIntNumOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          if (Numeric.asNumericOrNull(paramObject2) != null) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 80:
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof Procedure))
          return -786430; 
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 78:
        break;
    } 
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
      case 155:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 153:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 151:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 145:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 143:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 131:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 130:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 83:
        break;
    } 
    if (IntNum.asIntNumOrNull(paramObject1) != null) {
      paramCallContext.value1 = paramObject1;
      if (Numeric.asNumericOrNull(paramObject2) != null) {
        paramCallContext.value2 = paramObject2;
        if (Numeric.asNumericOrNull(paramObject3) != null) {
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
      } else {
        return -786430;
      } 
    } else {
      return -786431;
    } 
    return -786429;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 123) {
      if (!(paramObject1 instanceof Procedure))
        return -786431; 
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure))
        return -786430; 
      paramCallContext.value2 = paramObject2;
      if (!(paramObject3 instanceof Procedure))
        return -786429; 
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 4;
      return 0;
    } 
    return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 181:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 180:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 179:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 178:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 177:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 176:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 175:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 174:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 173:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 172:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 171:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 170:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 169:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 168:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 167:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 166:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 136:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 135:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 134:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 133:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 132:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 129:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 128:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 127:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 126:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 125:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 123:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 122:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 117:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 94:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 92:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 86:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 81:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 79:
        break;
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 5;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    first = lists.car;
    second = lists.cadr;
    third = lists.caddr;
    fourth = lists.cadddr;
    map$Mnin$Mnorder = Scheme.map;
  }
  
  public class frame extends ModuleBody {
    public static Object lambda2recur(Object param1Object) {
      frame0 frame0 = new frame0();
      frame0.lis = param1Object;
      if (srfi1.isNullList(frame0.lis) != Boolean.FALSE)
        return misc.values(new Object[] { frame0.lis, frame0.lis }); 
      frame0.elt = lists.car.apply1(frame0.lis);
      return call_with_values.callWithValues((Procedure)frame0.lambda$Fn1, (Procedure)frame0.lambda$Fn2);
    }
    
    public class frame0 extends ModuleBody {
      Object elt;
      
      final ModuleMethod lambda$Fn1 = new ModuleMethod(this, 1, null, 0);
      
      final ModuleMethod lambda$Fn2;
      
      Object lis;
      
      public frame0() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:627");
        this.lambda$Fn2 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 1) ? lambda3() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 2) ? lambda4(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda3() {
        return srfi1.frame.lambda2recur(lists.cdr.apply1(this.lis));
      }
      
      Object lambda4(Object param2Object1, Object param2Object2) {
        return misc.values(new Object[] { lists.cons(lists.car.apply1(this.elt), param2Object1), lists.cons(lists.cadr.apply1(this.elt), param2Object2) });
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 1) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 2) {
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
    Object elt;
    
    final ModuleMethod lambda$Fn1 = new ModuleMethod(this, 1, null, 0);
    
    final ModuleMethod lambda$Fn2;
    
    Object lis;
    
    public frame0() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:627");
      this.lambda$Fn2 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 1) ? lambda3() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 2) ? lambda4(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda3() {
      return srfi1.frame.lambda2recur(lists.cdr.apply1(this.lis));
    }
    
    Object lambda4(Object param1Object1, Object param1Object2) {
      return misc.values(new Object[] { lists.cons(lists.car.apply1(this.elt), param1Object1), lists.cons(lists.cadr.apply1(this.elt), param1Object2) });
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 1) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 2) {
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
    public static Object lambda5recur(Object param1Object) {
      frame2 frame2 = new frame2();
      frame2.lis = param1Object;
      if (srfi1.isNullList(frame2.lis) != Boolean.FALSE)
        return misc.values(new Object[] { frame2.lis, frame2.lis, frame2.lis }); 
      frame2.elt = lists.car.apply1(frame2.lis);
      return call_with_values.callWithValues((Procedure)frame2.lambda$Fn3, (Procedure)frame2.lambda$Fn4);
    }
    
    public class frame2 extends ModuleBody {
      Object elt;
      
      final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, null, 0);
      
      final ModuleMethod lambda$Fn4;
      
      Object lis;
      
      public frame2() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 12291);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:635");
        this.lambda$Fn4 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 3) ? lambda6() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply3(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, Object param2Object3) {
        return (param2ModuleMethod.selector == 4) ? lambda7(param2Object1, param2Object2, param2Object3) : super.apply3(param2ModuleMethod, param2Object1, param2Object2, param2Object3);
      }
      
      Object lambda6() {
        return srfi1.frame1.lambda5recur(lists.cdr.apply1(this.lis));
      }
      
      Object lambda7(Object param2Object1, Object param2Object2, Object param2Object3) {
        return misc.values(new Object[] { lists.cons(lists.car.apply1(this.elt), param2Object1), lists.cons(lists.cadr.apply1(this.elt), param2Object2), lists.cons(lists.caddr.apply1(this.elt), param2Object3) });
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 3) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match3(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, Object param2Object3, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 4) {
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
  }
  
  public class frame10 extends ModuleBody {
    Procedure f;
    
    Object zero;
    
    public Object lambda19recur(Object param1Object) {
      Object object = srfi1.$PcCdrs(param1Object);
      return lists.isNull(object) ? this.zero : Scheme.apply.apply2(this.f, srfi1.append$Ex$V(new Object[] { param1Object, LList.list1(lambda19recur(object)) }));
    }
    
    public Object lambda20recur(Object param1Object) {
      return (srfi1.isNullList(param1Object) != Boolean.FALSE) ? this.zero : this.f.apply2(param1Object, lambda20recur(lists.cdr.apply1(param1Object)));
    }
  }
  
  public class frame11 extends ModuleBody {
    Procedure f;
    
    public Object lambda21recur(Object param1Object1, Object param1Object2) {
      Object object = param1Object1;
      if (lists.isPair(param1Object2))
        object = this.f.apply2(param1Object1, lambda21recur(lists.car.apply1(param1Object2), lists.cdr.apply1(param1Object2))); 
      return object;
    }
  }
  
  public class frame12 extends ModuleBody {
    Procedure f;
    
    final ModuleMethod lambda$Fn11;
    
    public frame12() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 11, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:961");
      this.lambda$Fn11 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      if (param1ModuleMethod.selector == 11) {
        lambda22(param1Object);
        return Values.empty;
      } 
      return super.apply1(param1ModuleMethod, param1Object);
    }
    
    void lambda22(Object param1Object) {
      try {
        Pair pair = (Pair)param1Object;
        lists.setCar$Ex(pair, this.f.apply1(lists.car.apply1(param1Object)));
        return;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "set-car!", 1, param1Object);
      } 
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
  
  public class frame13 extends ModuleBody {
    Procedure f;
    
    public Object lambda23recur(Object param1Object1, Object param1Object2) {
      frame14 frame14 = new frame14();
      frame14.staticLink = this;
      frame14.lists = param1Object1;
      frame14.res = param1Object2;
      return call_with_values.callWithValues((Procedure)frame14.lambda$Fn12, (Procedure)frame14.lambda$Fn13);
    }
    
    public class frame14 extends ModuleBody {
      final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 12, null, 0);
      
      final ModuleMethod lambda$Fn13;
      
      Object lists;
      
      Object res;
      
      srfi1.frame13 staticLink;
      
      public frame14() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 13, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:969");
        this.lambda$Fn13 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 12) ? lambda24() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 13) ? lambda25(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda24() {
        return srfi1.$PcCars$PlCdrs(this.lists);
      }
      
      Object lambda25(Object param2Object1, Object param2Object2) {
        if (srfi1.isNotPair(param2Object1)) {
          param2Object1 = this.res;
          try {
            param2Object2 = param2Object1;
            return lists.reverse$Ex((LList)param2Object2);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "reverse!", 1, param2Object1);
          } 
        } 
        param2Object1 = Scheme.apply.apply2(this.staticLink.f, param2Object1);
        return (param2Object1 != Boolean.FALSE) ? this.staticLink.lambda23recur(classCastException, lists.cons(param2Object1, this.res)) : this.staticLink.lambda23recur(classCastException, this.res);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 12) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 13) {
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
  
  public class frame14 extends ModuleBody {
    final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 12, null, 0);
    
    final ModuleMethod lambda$Fn13;
    
    Object lists;
    
    Object res;
    
    srfi1.frame13 staticLink;
    
    public frame14() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 13, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:969");
      this.lambda$Fn13 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 12) ? lambda24() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 13) ? lambda25(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda24() {
      return srfi1.$PcCars$PlCdrs(this.lists);
    }
    
    Object lambda25(Object param1Object1, Object param1Object2) {
      if (srfi1.isNotPair(param1Object1)) {
        param1Object1 = this.res;
        try {
          param1Object2 = param1Object1;
          return lists.reverse$Ex((LList)param1Object2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "reverse!", 1, param1Object1);
        } 
      } 
      param1Object1 = Scheme.apply.apply2(this.staticLink.f, param1Object1);
      return (param1Object1 != Boolean.FALSE) ? this.staticLink.lambda23recur(classCastException, lists.cons(param1Object1, this.res)) : this.staticLink.lambda23recur(classCastException, this.res);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 12) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 13) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame15 extends ModuleBody {
    final ModuleMethod lambda$Fn14;
    
    Object pred;
    
    public frame15() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 14, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1199");
      this.lambda$Fn14 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 14) ? (lambda26(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda26(Object param1Object) {
      if (Scheme.applyToArgs.apply2(this.pred, param1Object) != Boolean.FALSE) {
        byte b1 = 1;
        return b1 + 1 & 0x1;
      } 
      byte b = 0;
      return b + 1 & 0x1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 14) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame16 extends ModuleBody {
    final ModuleMethod lambda$Fn15;
    
    Object pred;
    
    public frame16() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 15, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1200");
      this.lambda$Fn15 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 15) ? (lambda27(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda27(Object param1Object) {
      if (Scheme.applyToArgs.apply2(this.pred, param1Object) != Boolean.FALSE) {
        byte b1 = 1;
        return b1 + 1 & 0x1;
      } 
      byte b = 0;
      return b + 1 & 0x1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 15) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame17 extends ModuleBody {
    final ModuleMethod lambda$Fn16;
    
    Object maybe$Mn$Eq;
    
    Object x;
    
    public frame17() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 16, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1222");
      this.lambda$Fn16 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 16) ? (lambda28(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda28(Object param1Object) {
      if (Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.x, param1Object) != Boolean.FALSE) {
        byte b1 = 1;
        return b1 + 1 & 0x1;
      } 
      byte b = 0;
      return b + 1 & 0x1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 16) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame18 extends ModuleBody {
    final ModuleMethod lambda$Fn17;
    
    Object maybe$Mn$Eq;
    
    Object x;
    
    public frame18() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 17, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1225");
      this.lambda$Fn17 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 17) ? (lambda29(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda29(Object param1Object) {
      if (Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.x, param1Object) != Boolean.FALSE) {
        byte b1 = 1;
        return b1 + 1 & 0x1;
      } 
      byte b = 0;
      return b + 1 & 0x1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 17) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame19 extends ModuleBody {
    Procedure maybe$Mn$Eq;
    
    public Object lambda30recur(Object param1Object) {
      if (srfi1.isNullList(param1Object) == Boolean.FALSE) {
        Object object1 = lists.car.apply1(param1Object);
        Object object2 = lists.cdr.apply1(param1Object);
        Object object3 = lambda30recur(srfi1.delete(object1, object2, this.maybe$Mn$Eq));
        if (object2 != object3)
          return lists.cons(object1, object3); 
      } 
      return param1Object;
    }
  }
  
  public class frame2 extends ModuleBody {
    Object elt;
    
    final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, null, 0);
    
    final ModuleMethod lambda$Fn4;
    
    Object lis;
    
    public frame2() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 12291);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:635");
      this.lambda$Fn4 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 3) ? lambda6() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 4) ? lambda7(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda6() {
      return srfi1.frame1.lambda5recur(lists.cdr.apply1(this.lis));
    }
    
    Object lambda7(Object param1Object1, Object param1Object2, Object param1Object3) {
      return misc.values(new Object[] { lists.cons(lists.car.apply1(this.elt), param1Object1), lists.cons(lists.cadr.apply1(this.elt), param1Object2), lists.cons(lists.caddr.apply1(this.elt), param1Object3) });
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 3) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 4) {
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
  
  public class frame20 extends ModuleBody {
    Procedure maybe$Mn$Eq;
    
    public Object lambda31recur(Object param1Object) {
      if (srfi1.isNullList(param1Object) == Boolean.FALSE) {
        Object object1 = lists.car.apply1(param1Object);
        Object object2 = lists.cdr.apply1(param1Object);
        Object object3 = lambda31recur(srfi1.delete$Ex(object1, object2, this.maybe$Mn$Eq));
        if (object2 != object3)
          return lists.cons(object1, object3); 
      } 
      return param1Object;
    }
  }
  
  public class frame21 extends ModuleBody {
    Object key;
    
    final ModuleMethod lambda$Fn18;
    
    Object maybe$Mn$Eq;
    
    public frame21() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 18, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1280");
      this.lambda$Fn18 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 18) ? (lambda32(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda32(Object param1Object) {
      if (Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.key, lists.car.apply1(param1Object)) != Boolean.FALSE) {
        byte b1 = 1;
        return b1 + 1 & 0x1;
      } 
      byte b = 0;
      return b + 1 & 0x1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 18) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame22 extends ModuleBody {
    Object key;
    
    final ModuleMethod lambda$Fn19;
    
    Object maybe$Mn$Eq;
    
    public frame22() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 19, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1283");
      this.lambda$Fn19 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 19) ? (lambda33(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda33(Object param1Object) {
      if (Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.key, lists.car.apply1(param1Object)) != Boolean.FALSE) {
        byte b1 = 1;
        return b1 + 1 & 0x1;
      } 
      byte b = 0;
      return b + 1 & 0x1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 19) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame23 extends ModuleBody {
    Procedure pred;
    
    public Object lambda34recur(Object param1Object) {
      if (srfi1.isNullList(param1Object) != Boolean.FALSE)
        return LList.Empty; 
      Object object = lists.car.apply1(param1Object);
      return (this.pred.apply1(object) != Boolean.FALSE) ? lists.cons(object, lambda34recur(lists.cdr.apply1(param1Object))) : LList.Empty;
    }
  }
  
  public class frame24 extends ModuleBody {
    final ModuleMethod lambda$Fn20;
    
    Object pred;
    
    public frame24() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 20, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1343");
      this.lambda$Fn20 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 20) ? (lambda35(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda35(Object param1Object) {
      if (Scheme.applyToArgs.apply2(this.pred, param1Object) != Boolean.FALSE) {
        byte b1 = 1;
        return b1 + 1 & 0x1;
      } 
      byte b = 0;
      return b + 1 & 0x1;
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
  
  public class frame25 extends ModuleBody {
    final ModuleMethod lambda$Fn21;
    
    Object pred;
    
    public frame25() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 21, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1344");
      this.lambda$Fn21 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 21) ? (lambda36(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda36(Object param1Object) {
      if (Scheme.applyToArgs.apply2(this.pred, param1Object) != Boolean.FALSE) {
        byte b1 = 1;
        return b1 + 1 & 0x1;
      } 
      byte b = 0;
      return b + 1 & 0x1;
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
  
  public class frame26 extends ModuleBody {
    final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 22, null, 0);
    
    final ModuleMethod lambda$Fn23;
    
    Object lis1;
    
    LList lists;
    
    Procedure pred;
    
    public frame26() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 23, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1350");
      this.lambda$Fn23 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 22) ? lambda37() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 23) ? lambda38(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda37() {
      return srfi1.$PcCars$PlCdrs(lists.cons(this.lis1, this.lists));
    }
    
    Object lambda38(Object param1Object1, Object param1Object2) {
      boolean bool = lists.isPair(param1Object1);
      if (bool)
        while (true) {
          param1Object2 = srfi1.$PcCars$PlCdrs$SlPair(param1Object2);
          Object object = lists.car.apply1(param1Object2);
          param1Object2 = lists.cdr.apply1(param1Object2);
          if (lists.isPair(object)) {
            param1Object1 = Scheme.apply.apply2(this.pred, param1Object1);
            if (param1Object1 != Boolean.FALSE)
              return param1Object1; 
            param1Object1 = object;
            continue;
          } 
          return Scheme.apply.apply2(this.pred, param1Object1);
        }  
      return bool ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 22) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 23) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame27 extends ModuleBody {
    final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 26, null, 0);
    
    final ModuleMethod lambda$Fn25;
    
    Object lis1;
    
    LList lists;
    
    Procedure pred;
    
    public frame27() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 27, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1378");
      this.lambda$Fn25 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 26) ? lambda39() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 27) ? lambda40(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda39() {
      return srfi1.$PcCars$PlCdrs(lists.cons(this.lis1, this.lists));
    }
    
    Object lambda40(Object param1Object1, Object param1Object2) {
      throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 26) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 27) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame28 extends ModuleBody {
      srfi1.frame27 staticLink;
      
      public Object lambda41lp(Object param2Object1, Object param2Object2) {
        frame29 frame29 = new frame29();
        frame29.staticLink = this;
        frame29.heads = param2Object1;
        frame29.tails = param2Object2;
        return call_with_values.callWithValues((Procedure)frame29.lambda$Fn26, (Procedure)frame29.lambda$Fn27);
      }
      
      public class frame29 extends ModuleBody {
        Object heads;
        
        final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 24, null, 0);
        
        final ModuleMethod lambda$Fn27;
        
        srfi1.frame27.frame28 staticLink;
        
        Object tails;
        
        public frame29() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 25, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1381");
          this.lambda$Fn27 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 24) ? lambda42() : super.apply0(param3ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 25) ? lambda43(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda42() {
          return srfi1.$PcCars$PlCdrs(this.tails);
        }
        
        Object lambda43(Object param3Object1, Object param3Object2) {
          if (lists.isPair(param3Object1)) {
            Object object2 = Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
            Object object1 = object2;
            if (object2 != Boolean.FALSE)
              object1 = this.staticLink.lambda41lp(param3Object1, param3Object2); 
            return object1;
          } 
          return Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 24) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 25) {
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
    
    public class frame29 extends ModuleBody {
      Object heads;
      
      final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 24, null, 0);
      
      final ModuleMethod lambda$Fn27;
      
      srfi1.frame27.frame28 staticLink;
      
      Object tails;
      
      public frame29() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 25, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1381");
        this.lambda$Fn27 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 24) ? lambda42() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 25) ? lambda43(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda42() {
        return srfi1.$PcCars$PlCdrs(this.tails);
      }
      
      Object lambda43(Object param2Object1, Object param2Object2) {
        if (lists.isPair(param2Object1)) {
          Object object2 = Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
          Object object1 = object2;
          if (object2 != Boolean.FALSE)
            object1 = this.staticLink.lambda41lp(param2Object1, param2Object2); 
          return object1;
        } 
        return Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 24) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 25) {
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
  
  public class frame28 extends ModuleBody {
    srfi1.frame27 staticLink;
    
    public Object lambda41lp(Object param1Object1, Object param1Object2) {
      frame29 frame29 = new frame29();
      frame29.staticLink = this;
      frame29.heads = param1Object1;
      frame29.tails = param1Object2;
      return call_with_values.callWithValues((Procedure)frame29.lambda$Fn26, (Procedure)frame29.lambda$Fn27);
    }
    
    public class frame29 extends ModuleBody {
      Object heads;
      
      final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 24, null, 0);
      
      final ModuleMethod lambda$Fn27;
      
      srfi1.frame27.frame28 staticLink;
      
      Object tails;
      
      public frame29() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 25, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1381");
        this.lambda$Fn27 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 24) ? lambda42() : super.apply0(param3ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 25) ? lambda43(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda42() {
        return srfi1.$PcCars$PlCdrs(this.tails);
      }
      
      Object lambda43(Object param3Object1, Object param3Object2) {
        if (lists.isPair(param3Object1)) {
          Object object2 = Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
          Object object1 = object2;
          if (object2 != Boolean.FALSE)
            object1 = this.staticLink.lambda41lp(param3Object1, param3Object2); 
          return object1;
        } 
        return Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 24) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 25) {
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
  
  public class frame29 extends ModuleBody {
    Object heads;
    
    final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 24, null, 0);
    
    final ModuleMethod lambda$Fn27;
    
    srfi1.frame27.frame28 staticLink;
    
    Object tails;
    
    public frame29() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 25, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1381");
      this.lambda$Fn27 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 24) ? lambda42() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 25) ? lambda43(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda42() {
      return srfi1.$PcCars$PlCdrs(this.tails);
    }
    
    Object lambda43(Object param1Object1, Object param1Object2) {
      if (lists.isPair(param1Object1)) {
        Object object2 = Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
        Object object1 = object2;
        if (object2 != Boolean.FALSE)
          object1 = this.staticLink.lambda41lp(param1Object1, param1Object2); 
        return object1;
      } 
      return Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 24) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 25) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame3 extends ModuleBody {
    public static Object lambda8recur(Object param1Object) {
      frame4 frame4 = new frame4();
      frame4.lis = param1Object;
      if (srfi1.isNullList(frame4.lis) != Boolean.FALSE)
        return misc.values(new Object[] { frame4.lis, frame4.lis, frame4.lis, frame4.lis }); 
      frame4.elt = lists.car.apply1(frame4.lis);
      return call_with_values.callWithValues((Procedure)frame4.lambda$Fn5, (Procedure)frame4.lambda$Fn6);
    }
    
    public class frame4 extends ModuleBody {
      Object elt;
      
      final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 5, null, 0);
      
      final ModuleMethod lambda$Fn6;
      
      Object lis;
      
      public frame4() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 16388);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:644");
        this.lambda$Fn6 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 5) ? lambda9() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply4(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, Object param2Object3, Object param2Object4) {
        return (param2ModuleMethod.selector == 6) ? lambda10(param2Object1, param2Object2, param2Object3, param2Object4) : super.apply4(param2ModuleMethod, param2Object1, param2Object2, param2Object3, param2Object4);
      }
      
      Object lambda10(Object param2Object1, Object param2Object2, Object param2Object3, Object param2Object4) {
        return misc.values(new Object[] { lists.cons(lists.car.apply1(this.elt), param2Object1), lists.cons(lists.cadr.apply1(this.elt), param2Object2), lists.cons(lists.caddr.apply1(this.elt), param2Object3), lists.cons(lists.cadddr.apply1(this.elt), param2Object4) });
      }
      
      Object lambda9() {
        return srfi1.frame3.lambda8recur(lists.cdr.apply1(this.lis));
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 5) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match4(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, Object param2Object3, Object param2Object4, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 6) {
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
    }
  }
  
  public class frame30 extends ModuleBody {
    Procedure pred;
    
    public Object lambda44lp(Object param1Object1, Object param1Object2) {
      frame31 frame31 = new frame31();
      frame31.staticLink = this;
      frame31.lists = param1Object1;
      frame31.n = param1Object2;
      return call_with_values.callWithValues((Procedure)frame31.lambda$Fn28, (Procedure)frame31.lambda$Fn29);
    }
    
    public class frame31 extends ModuleBody {
      final ModuleMethod lambda$Fn28 = new ModuleMethod(this, 28, null, 0);
      
      final ModuleMethod lambda$Fn29;
      
      Object lists;
      
      Object n;
      
      srfi1.frame30 staticLink;
      
      public frame31() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 29, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1404");
        this.lambda$Fn29 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 28) ? lambda45() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 29) ? lambda46(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda45() {
        return srfi1.$PcCars$PlCdrs(this.lists);
      }
      
      Object lambda46(Object param2Object1, Object param2Object2) {
        boolean bool = lists.isPair(param2Object1);
        return bool ? ((Scheme.apply.apply2(this.staticLink.pred, param2Object1) != Boolean.FALSE) ? this.n : this.staticLink.lambda44lp(param2Object2, AddOp.$Pl.apply2(this.n, srfi1.Lit1))) : (bool ? Boolean.TRUE : Boolean.FALSE);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 28) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 29) {
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
  
  public class frame31 extends ModuleBody {
    final ModuleMethod lambda$Fn28 = new ModuleMethod(this, 28, null, 0);
    
    final ModuleMethod lambda$Fn29;
    
    Object lists;
    
    Object n;
    
    srfi1.frame30 staticLink;
    
    public frame31() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 29, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1404");
      this.lambda$Fn29 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 28) ? lambda45() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 29) ? lambda46(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda45() {
      return srfi1.$PcCars$PlCdrs(this.lists);
    }
    
    Object lambda46(Object param1Object1, Object param1Object2) {
      boolean bool = lists.isPair(param1Object1);
      return bool ? ((Scheme.apply.apply2(this.staticLink.pred, param1Object1) != Boolean.FALSE) ? this.n : this.staticLink.lambda44lp(param1Object2, AddOp.$Pl.apply2(this.n, srfi1.Lit1))) : (bool ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 28) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 29) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame32 extends ModuleBody {
    Procedure $Eq;
    
    final ModuleMethod lambda$Fn30;
    
    public frame32() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 30, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1466");
      this.lambda$Fn30 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 30) ? lambda47(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda47(Object param1Object1, Object param1Object2) {
      return (lists.member(param1Object1, param1Object2, this.$Eq) != Boolean.FALSE) ? param1Object2 : lists.cons(param1Object1, param1Object2);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 30) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame33 extends ModuleBody {
    Procedure $Eq;
    
    final ModuleMethod lambda$Fn31;
    
    final ModuleMethod lambda$Fn32;
    
    public frame33() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 32, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1476");
      this.lambda$Fn32 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 33, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1471");
      this.lambda$Fn31 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply2(param1ModuleMethod, param1Object1, param1Object2);
        case 32:
          return lambda49(param1Object1, param1Object2);
        case 33:
          break;
      } 
      return lambda48(param1Object1, param1Object2);
    }
    
    Object lambda48(Object param1Object1, Object param1Object2) {
      if (!lists.isNull(param1Object1)) {
        if (lists.isNull(param1Object2))
          return param1Object1; 
        if (param1Object1 != param1Object2)
          return srfi1.fold$V((Procedure)this.lambda$Fn32, param1Object2, param1Object1, new Object[0]); 
      } 
      return param1Object2;
    }
    
    Object lambda49(Object param1Object1, Object param1Object2) {
      frame34 frame34 = new frame34();
      frame34.staticLink = this;
      frame34.elt = param1Object1;
      return (srfi1.any$V((Procedure)frame34.lambda$Fn33, param1Object2, new Object[0]) != Boolean.FALSE) ? param1Object2 : lists.cons(frame34.elt, param1Object2);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
        case 33:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 2;
          return 0;
        case 32:
          break;
      } 
      param1CallContext.value1 = param1Object1;
      param1CallContext.value2 = param1Object2;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 2;
      return 0;
    }
    
    public class frame34 extends ModuleBody {
      Object elt;
      
      final ModuleMethod lambda$Fn33;
      
      srfi1.frame33 staticLink;
      
      public frame34() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 31, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1476");
        this.lambda$Fn33 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 31) ? lambda50(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      Object lambda50(Object param2Object) {
        return this.staticLink.$Eq.apply2(param2Object, this.elt);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 31) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
    }
  }
  
  public class frame34 extends ModuleBody {
    Object elt;
    
    final ModuleMethod lambda$Fn33;
    
    srfi1.frame33 staticLink;
    
    public frame34() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 31, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1476");
      this.lambda$Fn33 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 31) ? lambda50(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda50(Object param1Object) {
      return this.staticLink.$Eq.apply2(param1Object, this.elt);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 31) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame35 extends ModuleBody {
    Procedure $Eq;
    
    final ModuleMethod lambda$Fn34;
    
    final ModuleMethod lambda$Fn35;
    
    public frame35() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 35, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1488");
      this.lambda$Fn35 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 36, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1483");
      this.lambda$Fn34 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply2(param1ModuleMethod, param1Object1, param1Object2);
        case 35:
          return lambda52(param1Object1, param1Object2);
        case 36:
          break;
      } 
      return lambda51(param1Object1, param1Object2);
    }
    
    Object lambda51(Object param1Object1, Object param1Object2) {
      if (!lists.isNull(param1Object1)) {
        if (lists.isNull(param1Object2))
          return param1Object1; 
        if (param1Object1 != param1Object2)
          return srfi1.pairFold$V((Procedure)this.lambda$Fn35, param1Object2, param1Object1, new Object[0]); 
      } 
      return param1Object2;
    }
    
    Object lambda52(Object param1Object1, Object param1Object2) {
      frame36 frame36 = new frame36();
      frame36.staticLink = this;
      frame36.elt = lists.car.apply1(param1Object1);
      if (srfi1.any$V((Procedure)frame36.lambda$Fn36, param1Object2, new Object[0]) != Boolean.FALSE)
        return param1Object2; 
      try {
        Pair pair = (Pair)param1Object1;
        lists.setCdr$Ex(pair, param1Object2);
        return param1Object1;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "set-cdr!", 1, param1Object1);
      } 
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
        case 36:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 2;
          return 0;
        case 35:
          break;
      } 
      param1CallContext.value1 = param1Object1;
      param1CallContext.value2 = param1Object2;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 2;
      return 0;
    }
    
    public class frame36 extends ModuleBody {
      Object elt;
      
      final ModuleMethod lambda$Fn36;
      
      srfi1.frame35 staticLink;
      
      public frame36() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 34, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1490");
        this.lambda$Fn36 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 34) ? lambda53(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      Object lambda53(Object param2Object) {
        return this.staticLink.$Eq.apply2(param2Object, this.elt);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 34) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
    }
  }
  
  public class frame36 extends ModuleBody {
    Object elt;
    
    final ModuleMethod lambda$Fn36;
    
    srfi1.frame35 staticLink;
    
    public frame36() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 34, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1490");
      this.lambda$Fn36 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 34) ? lambda53(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda53(Object param1Object) {
      return this.staticLink.$Eq.apply2(param1Object, this.elt);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 34) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame37 extends ModuleBody {
    Procedure $Eq;
    
    final ModuleMethod lambda$Fn37;
    
    Object lists;
    
    public frame37() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 38, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1501");
      this.lambda$Fn37 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 38) ? lambda54(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda54(Object param1Object) {
      frame38 frame38 = new frame38();
      frame38.staticLink = this;
      frame38.x = param1Object;
      return srfi1.every$V((Procedure)frame38.lambda$Fn38, this.lists, new Object[0]);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 38) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public class frame38 extends ModuleBody {
      final ModuleMethod lambda$Fn38;
      
      srfi1.frame37 staticLink;
      
      Object x;
      
      public frame38() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 37, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1502");
        this.lambda$Fn38 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 37) ? lambda55(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      Object lambda55(Object param2Object) {
        return lists.member(this.x, param2Object, this.staticLink.$Eq);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 37) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
    }
  }
  
  public class frame38 extends ModuleBody {
    final ModuleMethod lambda$Fn38;
    
    srfi1.frame37 staticLink;
    
    Object x;
    
    public frame38() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 37, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1502");
      this.lambda$Fn38 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 37) ? lambda55(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda55(Object param1Object) {
      return lists.member(this.x, param1Object, this.staticLink.$Eq);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 37) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame39 extends ModuleBody {
    Procedure $Eq;
    
    final ModuleMethod lambda$Fn39;
    
    Object lists;
    
    public frame39() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 40, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1509");
      this.lambda$Fn39 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 40) ? lambda56(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda56(Object param1Object) {
      frame40 frame40 = new frame40();
      frame40.staticLink = this;
      frame40.x = param1Object;
      return srfi1.every$V((Procedure)frame40.lambda$Fn40, this.lists, new Object[0]);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 40) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public class frame40 extends ModuleBody {
      final ModuleMethod lambda$Fn40;
      
      srfi1.frame39 staticLink;
      
      Object x;
      
      public frame40() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 39, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1510");
        this.lambda$Fn40 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 39) ? lambda57(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      Object lambda57(Object param2Object) {
        return lists.member(this.x, param2Object, this.staticLink.$Eq);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 39) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
    }
  }
  
  public class frame4 extends ModuleBody {
    Object elt;
    
    final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 5, null, 0);
    
    final ModuleMethod lambda$Fn6;
    
    Object lis;
    
    public frame4() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 16388);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:644");
      this.lambda$Fn6 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 5) ? lambda9() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply4(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4) {
      return (param1ModuleMethod.selector == 6) ? lambda10(param1Object1, param1Object2, param1Object3, param1Object4) : super.apply4(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1Object4);
    }
    
    Object lambda10(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4) {
      return misc.values(new Object[] { lists.cons(lists.car.apply1(this.elt), param1Object1), lists.cons(lists.cadr.apply1(this.elt), param1Object2), lists.cons(lists.caddr.apply1(this.elt), param1Object3), lists.cons(lists.cadddr.apply1(this.elt), param1Object4) });
    }
    
    Object lambda9() {
      return srfi1.frame3.lambda8recur(lists.cdr.apply1(this.lis));
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 5) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match4(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 6) {
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
  }
  
  public class frame40 extends ModuleBody {
    final ModuleMethod lambda$Fn40;
    
    srfi1.frame39 staticLink;
    
    Object x;
    
    public frame40() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 39, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1510");
      this.lambda$Fn40 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 39) ? lambda57(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda57(Object param1Object) {
      return lists.member(this.x, param1Object, this.staticLink.$Eq);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 39) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame41 extends ModuleBody {
    Procedure $Eq;
    
    final ModuleMethod lambda$Fn41;
    
    Object lists;
    
    public frame41() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 42, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1518");
      this.lambda$Fn41 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 42) ? lambda58(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda58(Object param1Object) {
      frame42 frame42 = new frame42();
      frame42.staticLink = this;
      frame42.x = param1Object;
      return srfi1.every$V((Procedure)frame42.lambda$Fn42, this.lists, new Object[0]);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 42) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public class frame42 extends ModuleBody {
      final ModuleMethod lambda$Fn42;
      
      srfi1.frame41 staticLink;
      
      Object x;
      
      public frame42() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 41, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1519");
        this.lambda$Fn42 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 41) ? (lambda59(param2Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      boolean lambda59(Object param2Object) {
        if (lists.member(this.x, param2Object, this.staticLink.$Eq) != Boolean.FALSE) {
          byte b1 = 1;
          return b1 + 1 & 0x1;
        } 
        byte b = 0;
        return b + 1 & 0x1;
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 41) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
    }
  }
  
  public class frame42 extends ModuleBody {
    final ModuleMethod lambda$Fn42;
    
    srfi1.frame41 staticLink;
    
    Object x;
    
    public frame42() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 41, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1519");
      this.lambda$Fn42 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 41) ? (lambda59(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda59(Object param1Object) {
      if (lists.member(this.x, param1Object, this.staticLink.$Eq) != Boolean.FALSE) {
        byte b1 = 1;
        return b1 + 1 & 0x1;
      } 
      byte b = 0;
      return b + 1 & 0x1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 41) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame43 extends ModuleBody {
    Procedure $Eq;
    
    final ModuleMethod lambda$Fn43;
    
    Object lists;
    
    public frame43() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 44, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1527");
      this.lambda$Fn43 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 44) ? lambda60(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda60(Object param1Object) {
      frame44 frame44 = new frame44();
      frame44.staticLink = this;
      frame44.x = param1Object;
      return srfi1.every$V((Procedure)frame44.lambda$Fn44, this.lists, new Object[0]);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 44) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public class frame44 extends ModuleBody {
      final ModuleMethod lambda$Fn44;
      
      srfi1.frame43 staticLink;
      
      Object x;
      
      public frame44() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 43, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1528");
        this.lambda$Fn44 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 43) ? (lambda61(param2Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      boolean lambda61(Object param2Object) {
        if (lists.member(this.x, param2Object, this.staticLink.$Eq) != Boolean.FALSE) {
          byte b1 = 1;
          return b1 + 1 & 0x1;
        } 
        byte b = 0;
        return b + 1 & 0x1;
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 43) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
    }
  }
  
  public class frame44 extends ModuleBody {
    final ModuleMethod lambda$Fn44;
    
    srfi1.frame43 staticLink;
    
    Object x;
    
    public frame44() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 43, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1528");
      this.lambda$Fn44 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 43) ? (lambda61(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda61(Object param1Object) {
      if (lists.member(this.x, param1Object, this.staticLink.$Eq) != Boolean.FALSE) {
        byte b1 = 1;
        return b1 + 1 & 0x1;
      } 
      byte b = 0;
      return b + 1 & 0x1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 43) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame45 extends ModuleBody {
    Procedure $Eq;
    
    final ModuleMethod lambda$Fn45;
    
    public frame45() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 48, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1534");
      this.lambda$Fn45 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 48) ? lambda62(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda62(Object param1Object1, Object param1Object2) {
      frame46 frame46 = new frame46();
      frame46.staticLink = this;
      frame46.b = param1Object1;
      frame46.a = param1Object2;
      return call_with_values.callWithValues((Procedure)frame46.lambda$Fn46, (Procedure)frame46.lambda$Fn47);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 48) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame46 extends ModuleBody {
      Object a;
      
      Object b;
      
      final ModuleMethod lambda$Fn46 = new ModuleMethod(this, 46, null, 0);
      
      final ModuleMethod lambda$Fn47;
      
      srfi1.frame45 staticLink;
      
      public frame46() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 47, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1544");
        this.lambda$Fn47 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 46) ? lambda63() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 47) ? lambda64(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda63() {
        return srfi1.lsetDiff$PlIntersection$V(this.staticLink.$Eq, this.a, new Object[] { this.b });
      }
      
      Object lambda64(Object param2Object1, Object param2Object2) {
        frame47 frame47 = new frame47();
        frame47.staticLink = this;
        frame47.a$Mnint$Mnb = param2Object2;
        return lists.isNull(param2Object1) ? srfi1.lsetDifference$V(this.staticLink.$Eq, this.b, new Object[] { this.a }) : (lists.isNull(frame47.a$Mnint$Mnb) ? append.append$V(new Object[] { this.b, this.a }) : srfi1.fold$V((Procedure)frame47.lambda$Fn48, param2Object1, this.b, new Object[0]));
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 46) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 47) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
      
      public class frame47 extends ModuleBody {
        Object a$Mnint$Mnb;
        
        final ModuleMethod lambda$Fn48;
        
        srfi1.frame45.frame46 staticLink;
        
        public frame47() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 45, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1547");
          this.lambda$Fn48 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 45) ? lambda65(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda65(Object param3Object1, Object param3Object2) {
          return (lists.member(param3Object1, this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE) ? param3Object2 : lists.cons(param3Object1, param3Object2);
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 45) {
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
    
    public class frame47 extends ModuleBody {
      Object a$Mnint$Mnb;
      
      final ModuleMethod lambda$Fn48;
      
      srfi1.frame45.frame46 staticLink;
      
      public frame47() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 45, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1547");
        this.lambda$Fn48 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 45) ? lambda65(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda65(Object param2Object1, Object param2Object2) {
        return (lists.member(param2Object1, this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE) ? param2Object2 : lists.cons(param2Object1, param2Object2);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 45) {
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
  
  public class frame46 extends ModuleBody {
    Object a;
    
    Object b;
    
    final ModuleMethod lambda$Fn46 = new ModuleMethod(this, 46, null, 0);
    
    final ModuleMethod lambda$Fn47;
    
    srfi1.frame45 staticLink;
    
    public frame46() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 47, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1544");
      this.lambda$Fn47 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 46) ? lambda63() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 47) ? lambda64(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda63() {
      return srfi1.lsetDiff$PlIntersection$V(this.staticLink.$Eq, this.a, new Object[] { this.b });
    }
    
    Object lambda64(Object param1Object1, Object param1Object2) {
      frame47 frame47 = new frame47();
      frame47.staticLink = this;
      frame47.a$Mnint$Mnb = param1Object2;
      return lists.isNull(param1Object1) ? srfi1.lsetDifference$V(this.staticLink.$Eq, this.b, new Object[] { this.a }) : (lists.isNull(frame47.a$Mnint$Mnb) ? append.append$V(new Object[] { this.b, this.a }) : srfi1.fold$V((Procedure)frame47.lambda$Fn48, param1Object1, this.b, new Object[0]));
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 46) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 47) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame47 extends ModuleBody {
      Object a$Mnint$Mnb;
      
      final ModuleMethod lambda$Fn48;
      
      srfi1.frame45.frame46 staticLink;
      
      public frame47() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 45, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1547");
        this.lambda$Fn48 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 45) ? lambda65(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda65(Object param3Object1, Object param3Object2) {
        return (lists.member(param3Object1, this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE) ? param3Object2 : lists.cons(param3Object1, param3Object2);
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 45) {
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
  
  public class frame47 extends ModuleBody {
    Object a$Mnint$Mnb;
    
    final ModuleMethod lambda$Fn48;
    
    srfi1.frame45.frame46 staticLink;
    
    public frame47() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 45, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1547");
      this.lambda$Fn48 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 45) ? lambda65(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda65(Object param1Object1, Object param1Object2) {
      return (lists.member(param1Object1, this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE) ? param1Object2 : lists.cons(param1Object1, param1Object2);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 45) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame48 extends ModuleBody {
    Procedure $Eq;
    
    final ModuleMethod lambda$Fn49;
    
    public frame48() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 52, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1555");
      this.lambda$Fn49 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 52) ? lambda66(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda66(Object param1Object1, Object param1Object2) {
      frame49 frame49 = new frame49();
      frame49.staticLink = this;
      frame49.b = param1Object1;
      frame49.a = param1Object2;
      return call_with_values.callWithValues((Procedure)frame49.lambda$Fn50, (Procedure)frame49.lambda$Fn51);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 52) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame49 extends ModuleBody {
      Object a;
      
      Object b;
      
      final ModuleMethod lambda$Fn50 = new ModuleMethod(this, 50, null, 0);
      
      final ModuleMethod lambda$Fn51;
      
      srfi1.frame48 staticLink;
      
      public frame49() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 51, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1565");
        this.lambda$Fn51 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 50) ? lambda67() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 51) ? lambda68(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda67() {
        return srfi1.lsetDiff$PlIntersection$Ex$V(this.staticLink.$Eq, this.a, new Object[] { this.b });
      }
      
      Object lambda68(Object param2Object1, Object param2Object2) {
        frame50 frame50 = new frame50();
        frame50.staticLink = this;
        frame50.a$Mnint$Mnb = param2Object2;
        return lists.isNull(param2Object1) ? srfi1.lsetDifference$Ex$V(this.staticLink.$Eq, this.b, new Object[] { this.a }) : (lists.isNull(frame50.a$Mnint$Mnb) ? srfi1.append$Ex$V(new Object[] { this.b, this.a }) : srfi1.pairFold$V((Procedure)frame50.lambda$Fn52, param2Object1, this.b, new Object[0]));
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 50) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 51) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
      
      public class frame50 extends ModuleBody {
        Object a$Mnint$Mnb;
        
        final ModuleMethod lambda$Fn52;
        
        srfi1.frame48.frame49 staticLink;
        
        public frame50() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 49, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1568");
          this.lambda$Fn52 = moduleMethod;
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 49) ? lambda69(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda69(Object param3Object1, Object param3Object2) {
          if (lists.member(lists.car.apply1(param3Object1), this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE)
            return param3Object2; 
          try {
            Pair pair = (Pair)param3Object1;
            lists.setCdr$Ex(pair, param3Object2);
            return param3Object1;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "set-cdr!", 1, param3Object1);
          } 
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 49) {
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
    
    public class frame50 extends ModuleBody {
      Object a$Mnint$Mnb;
      
      final ModuleMethod lambda$Fn52;
      
      srfi1.frame48.frame49 staticLink;
      
      public frame50() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 49, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1568");
        this.lambda$Fn52 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 49) ? lambda69(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda69(Object param2Object1, Object param2Object2) {
        if (lists.member(lists.car.apply1(param2Object1), this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE)
          return param2Object2; 
        try {
          Pair pair = (Pair)param2Object1;
          lists.setCdr$Ex(pair, param2Object2);
          return param2Object1;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-cdr!", 1, param2Object1);
        } 
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 49) {
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
  
  public class frame49 extends ModuleBody {
    Object a;
    
    Object b;
    
    final ModuleMethod lambda$Fn50 = new ModuleMethod(this, 50, null, 0);
    
    final ModuleMethod lambda$Fn51;
    
    srfi1.frame48 staticLink;
    
    public frame49() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 51, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1565");
      this.lambda$Fn51 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 50) ? lambda67() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 51) ? lambda68(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda67() {
      return srfi1.lsetDiff$PlIntersection$Ex$V(this.staticLink.$Eq, this.a, new Object[] { this.b });
    }
    
    Object lambda68(Object param1Object1, Object param1Object2) {
      frame50 frame50 = new frame50();
      frame50.staticLink = this;
      frame50.a$Mnint$Mnb = param1Object2;
      return lists.isNull(param1Object1) ? srfi1.lsetDifference$Ex$V(this.staticLink.$Eq, this.b, new Object[] { this.a }) : (lists.isNull(frame50.a$Mnint$Mnb) ? srfi1.append$Ex$V(new Object[] { this.b, this.a }) : srfi1.pairFold$V((Procedure)frame50.lambda$Fn52, param1Object1, this.b, new Object[0]));
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 50) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 51) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame50 extends ModuleBody {
      Object a$Mnint$Mnb;
      
      final ModuleMethod lambda$Fn52;
      
      srfi1.frame48.frame49 staticLink;
      
      public frame50() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 49, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1568");
        this.lambda$Fn52 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 49) ? lambda69(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda69(Object param3Object1, Object param3Object2) {
        if (lists.member(lists.car.apply1(param3Object1), this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE)
          return param3Object2; 
        try {
          Pair pair = (Pair)param3Object1;
          lists.setCdr$Ex(pair, param3Object2);
          return param3Object1;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-cdr!", 1, param3Object1);
        } 
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 49) {
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
  
  public class frame5 extends ModuleBody {
    public static Object lambda11recur(Object param1Object) {
      frame6 frame6 = new frame6();
      frame6.lis = param1Object;
      if (srfi1.isNullList(frame6.lis) != Boolean.FALSE)
        return misc.values(new Object[] { frame6.lis, frame6.lis, frame6.lis, frame6.lis, frame6.lis }); 
      frame6.elt = lists.car.apply1(frame6.lis);
      return call_with_values.callWithValues((Procedure)frame6.lambda$Fn7, (Procedure)frame6.lambda$Fn8);
    }
    
    public class frame6 extends ModuleBody {
      Object elt;
      
      final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 7, null, 0);
      
      final ModuleMethod lambda$Fn8;
      
      Object lis;
      
      public frame6() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 8, null, 20485);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:654");
        this.lambda$Fn8 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 7) ? lambda12() : super.apply0(param2ModuleMethod);
      }
      
      public Object applyN(ModuleMethod param2ModuleMethod, Object[] param2ArrayOfObject) {
        return (param2ModuleMethod.selector == 8) ? lambda13(param2ArrayOfObject[0], param2ArrayOfObject[1], param2ArrayOfObject[2], param2ArrayOfObject[3], param2ArrayOfObject[4]) : super.applyN(param2ModuleMethod, param2ArrayOfObject);
      }
      
      Object lambda12() {
        return srfi1.frame5.lambda11recur(lists.cdr.apply1(this.lis));
      }
      
      Object lambda13(Object param2Object1, Object param2Object2, Object param2Object3, Object param2Object4, Object param2Object5) {
        return misc.values(new Object[] { lists.cons(lists.car.apply1(this.elt), param2Object1), lists.cons(lists.cadr.apply1(this.elt), param2Object2), lists.cons(lists.caddr.apply1(this.elt), param2Object3), lists.cons(lists.cadddr.apply1(this.elt), param2Object4), lists.cons(lists.car.apply1(lists.cddddr.apply1(this.elt)), param2Object5) });
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 7) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int matchN(ModuleMethod param2ModuleMethod, Object[] param2ArrayOfObject, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 8) {
          param2CallContext.values = param2ArrayOfObject;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 5;
          return 0;
        } 
        return super.matchN(param2ModuleMethod, param2ArrayOfObject, param2CallContext);
      }
    }
  }
  
  public class frame50 extends ModuleBody {
    Object a$Mnint$Mnb;
    
    final ModuleMethod lambda$Fn52;
    
    srfi1.frame48.frame49 staticLink;
    
    public frame50() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 49, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1568");
      this.lambda$Fn52 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 49) ? lambda69(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda69(Object param1Object1, Object param1Object2) {
      if (lists.member(lists.car.apply1(param1Object1), this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE)
        return param1Object2; 
      try {
        Pair pair = (Pair)param1Object1;
        lists.setCdr$Ex(pair, param1Object2);
        return param1Object1;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "set-cdr!", 1, param1Object1);
      } 
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 49) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame51 extends ModuleBody {
    Procedure $Eq;
    
    final ModuleMethod lambda$Fn53;
    
    LList lists;
    
    public frame51() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 54, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1579");
      this.lambda$Fn53 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 54) ? (lambda70(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda70(Object param1Object) {
      byte b = 0;
      frame52 frame52 = new frame52();
      frame52.staticLink = this;
      frame52.elt = param1Object;
      if (srfi1.any$V((Procedure)frame52.lambda$Fn54, this.lists, new Object[0]) != Boolean.FALSE)
        b = 1; 
      return b + 1 & 0x1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 54) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public class frame52 extends ModuleBody {
      Object elt;
      
      final ModuleMethod lambda$Fn54;
      
      srfi1.frame51 staticLink;
      
      public frame52() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 53, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1580");
        this.lambda$Fn54 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 53) ? lambda71(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      Object lambda71(Object param2Object) {
        return lists.member(this.elt, param2Object, this.staticLink.$Eq);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 53) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
    }
  }
  
  public class frame52 extends ModuleBody {
    Object elt;
    
    final ModuleMethod lambda$Fn54;
    
    srfi1.frame51 staticLink;
    
    public frame52() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 53, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1580");
      this.lambda$Fn54 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 53) ? lambda71(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda71(Object param1Object) {
      return lists.member(this.elt, param1Object, this.staticLink.$Eq);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 53) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame53 extends ModuleBody {
    Procedure $Eq;
    
    final ModuleMethod lambda$Fn55;
    
    LList lists;
    
    public frame53() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 56, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1587");
      this.lambda$Fn55 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 56) ? (lambda72(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda72(Object param1Object) {
      byte b = 0;
      frame54 frame54 = new frame54();
      frame54.staticLink = this;
      frame54.elt = param1Object;
      if (srfi1.any$V((Procedure)frame54.lambda$Fn56, this.lists, new Object[0]) != Boolean.FALSE)
        b = 1; 
      return b + 1 & 0x1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 56) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public class frame54 extends ModuleBody {
      Object elt;
      
      final ModuleMethod lambda$Fn56;
      
      srfi1.frame53 staticLink;
      
      public frame54() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 55, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1588");
        this.lambda$Fn56 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 55) ? lambda73(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      Object lambda73(Object param2Object) {
        return lists.member(this.elt, param2Object, this.staticLink.$Eq);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 55) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
    }
  }
  
  public class frame54 extends ModuleBody {
    Object elt;
    
    final ModuleMethod lambda$Fn56;
    
    srfi1.frame53 staticLink;
    
    public frame54() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 55, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1588");
      this.lambda$Fn56 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 55) ? lambda73(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda73(Object param1Object) {
      return lists.member(this.elt, param1Object, this.staticLink.$Eq);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 55) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame55 extends ModuleBody {
    Continuation abort;
    
    public Object lambda74recur(Object param1Object) {
      if (lists.isPair(param1Object)) {
        Object object = lists.car.apply1(param1Object);
        return (srfi1.isNullList(object) != Boolean.FALSE) ? this.abort.apply1(LList.Empty) : lists.cons(lists.cdr.apply1(object), lambda74recur(lists.cdr.apply1(param1Object)));
      } 
      return LList.Empty;
    }
  }
  
  public class frame56 extends ModuleBody {
    Object last$Mnelt;
    
    public Object lambda75recur(Object param1Object) {
      return lists.isPair(param1Object) ? lists.cons(lists.caar.apply1(param1Object), lambda75recur(lists.cdr.apply1(param1Object))) : LList.list1(this.last$Mnelt);
    }
  }
  
  public class frame57 extends ModuleBody {
    Continuation abort;
    
    public Object lambda76recur(Object param1Object) {
      frame58 frame58 = new frame58();
      frame58.staticLink = this;
      frame58.lists = param1Object;
      return lists.isPair(frame58.lists) ? call_with_values.callWithValues((Procedure)frame58.lambda$Fn57, (Procedure)frame58.lambda$Fn58) : misc.values(new Object[] { LList.Empty, LList.Empty });
    }
    
    public class frame58 extends ModuleBody {
      final ModuleMethod lambda$Fn57 = new ModuleMethod(this, 61, null, 0);
      
      final ModuleMethod lambda$Fn58;
      
      Object lists;
      
      srfi1.frame57 staticLink;
      
      public frame58() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 62, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:762");
        this.lambda$Fn58 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 61) ? lambda77() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 62) ? lambda78(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda77() {
        return srfi1.car$PlCdr(this.lists);
      }
      
      Object lambda78(Object param2Object1, Object param2Object2) {
        frame59 frame59 = new frame59();
        frame59.staticLink = this;
        frame59.list = param2Object1;
        frame59.other$Mnlists = param2Object2;
        return (srfi1.isNullList(frame59.list) != Boolean.FALSE) ? this.staticLink.abort.apply2(LList.Empty, LList.Empty) : call_with_values.callWithValues((Procedure)frame59.lambda$Fn59, (Procedure)frame59.lambda$Fn60);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 61) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 62) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
      
      public class frame59 extends ModuleBody {
        final ModuleMethod lambda$Fn59 = new ModuleMethod(this, 59, null, 0);
        
        final ModuleMethod lambda$Fn60;
        
        Object list;
        
        Object other$Mnlists;
        
        srfi1.frame57.frame58 staticLink;
        
        public frame59() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 60, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:764");
          this.lambda$Fn60 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 59) ? lambda79() : super.apply0(param3ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 60) ? lambda80(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda79() {
          return srfi1.car$PlCdr(this.list);
        }
        
        Object lambda80(Object param3Object1, Object param3Object2) {
          frame60 frame60 = new frame60();
          frame60.staticLink = this;
          frame60.a = param3Object1;
          frame60.d = param3Object2;
          return call_with_values.callWithValues((Procedure)frame60.lambda$Fn61, (Procedure)frame60.lambda$Fn62);
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 59) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 60) {
            param3CallContext.value1 = param3Object1;
            param3CallContext.value2 = param3Object2;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
        }
        
        public class frame60 extends ModuleBody {
          Object a;
          
          Object d;
          
          final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 57, null, 0);
          
          final ModuleMethod lambda$Fn62;
          
          srfi1.frame57.frame58.frame59 staticLink;
          
          public frame60() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 58, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:765");
            this.lambda$Fn62 = moduleMethod;
          }
          
          public Object apply0(ModuleMethod param4ModuleMethod) {
            return (param4ModuleMethod.selector == 57) ? lambda81() : super.apply0(param4ModuleMethod);
          }
          
          public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
            return (param4ModuleMethod.selector == 58) ? lambda82(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
          }
          
          Object lambda81() {
            return this.staticLink.staticLink.staticLink.lambda76recur(this.staticLink.other$Mnlists);
          }
          
          Object lambda82(Object param4Object1, Object param4Object2) {
            return misc.values(new Object[] { lists.cons(this.a, param4Object1), lists.cons(this.d, param4Object2) });
          }
          
          public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 57) {
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 0;
              return 0;
            } 
            return super.match0(param4ModuleMethod, param4CallContext);
          }
          
          public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 58) {
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
      
      public class frame60 extends ModuleBody {
        Object a;
        
        Object d;
        
        final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 57, null, 0);
        
        final ModuleMethod lambda$Fn62;
        
        srfi1.frame57.frame58.frame59 staticLink;
        
        public frame60() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 58, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:765");
          this.lambda$Fn62 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 57) ? lambda81() : super.apply0(param3ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 58) ? lambda82(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda81() {
          return this.staticLink.staticLink.staticLink.lambda76recur(this.staticLink.other$Mnlists);
        }
        
        Object lambda82(Object param3Object1, Object param3Object2) {
          return misc.values(new Object[] { lists.cons(this.a, param3Object1), lists.cons(this.d, param3Object2) });
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 57) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 58) {
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
    
    public class frame59 extends ModuleBody {
      final ModuleMethod lambda$Fn59 = new ModuleMethod(this, 59, null, 0);
      
      final ModuleMethod lambda$Fn60;
      
      Object list;
      
      Object other$Mnlists;
      
      srfi1.frame57.frame58 staticLink;
      
      public frame59() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 60, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:764");
        this.lambda$Fn60 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 59) ? lambda79() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 60) ? lambda80(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda79() {
        return srfi1.car$PlCdr(this.list);
      }
      
      Object lambda80(Object param2Object1, Object param2Object2) {
        frame60 frame60 = new frame60();
        frame60.staticLink = this;
        frame60.a = param2Object1;
        frame60.d = param2Object2;
        return call_with_values.callWithValues((Procedure)frame60.lambda$Fn61, (Procedure)frame60.lambda$Fn62);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 59) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 60) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
      
      public class frame60 extends ModuleBody {
        Object a;
        
        Object d;
        
        final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 57, null, 0);
        
        final ModuleMethod lambda$Fn62;
        
        srfi1.frame57.frame58.frame59 staticLink;
        
        public frame60() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 58, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:765");
          this.lambda$Fn62 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param4ModuleMethod) {
          return (param4ModuleMethod.selector == 57) ? lambda81() : super.apply0(param4ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 58) ? lambda82(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda81() {
          return this.staticLink.staticLink.staticLink.lambda76recur(this.staticLink.other$Mnlists);
        }
        
        Object lambda82(Object param4Object1, Object param4Object2) {
          return misc.values(new Object[] { lists.cons(this.a, param4Object1), lists.cons(this.d, param4Object2) });
        }
        
        public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 57) {
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param4ModuleMethod, param4CallContext);
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 58) {
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
    
    public class frame60 extends ModuleBody {
      Object a;
      
      Object d;
      
      final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 57, null, 0);
      
      final ModuleMethod lambda$Fn62;
      
      srfi1.frame57.frame58.frame59 staticLink;
      
      public frame60() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 58, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:765");
        this.lambda$Fn62 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 57) ? lambda81() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 58) ? lambda82(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda81() {
        return this.staticLink.staticLink.staticLink.lambda76recur(this.staticLink.other$Mnlists);
      }
      
      Object lambda82(Object param2Object1, Object param2Object2) {
        return misc.values(new Object[] { lists.cons(this.a, param2Object1), lists.cons(this.d, param2Object2) });
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 57) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 58) {
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
  
  public class frame58 extends ModuleBody {
    final ModuleMethod lambda$Fn57 = new ModuleMethod(this, 61, null, 0);
    
    final ModuleMethod lambda$Fn58;
    
    Object lists;
    
    srfi1.frame57 staticLink;
    
    public frame58() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 62, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:762");
      this.lambda$Fn58 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 61) ? lambda77() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 62) ? lambda78(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda77() {
      return srfi1.car$PlCdr(this.lists);
    }
    
    Object lambda78(Object param1Object1, Object param1Object2) {
      frame59 frame59 = new frame59();
      frame59.staticLink = this;
      frame59.list = param1Object1;
      frame59.other$Mnlists = param1Object2;
      return (srfi1.isNullList(frame59.list) != Boolean.FALSE) ? this.staticLink.abort.apply2(LList.Empty, LList.Empty) : call_with_values.callWithValues((Procedure)frame59.lambda$Fn59, (Procedure)frame59.lambda$Fn60);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 61) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 62) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame59 extends ModuleBody {
      final ModuleMethod lambda$Fn59 = new ModuleMethod(this, 59, null, 0);
      
      final ModuleMethod lambda$Fn60;
      
      Object list;
      
      Object other$Mnlists;
      
      srfi1.frame57.frame58 staticLink;
      
      public frame59() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 60, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:764");
        this.lambda$Fn60 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 59) ? lambda79() : super.apply0(param3ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 60) ? lambda80(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda79() {
        return srfi1.car$PlCdr(this.list);
      }
      
      Object lambda80(Object param3Object1, Object param3Object2) {
        frame60 frame60 = new frame60();
        frame60.staticLink = this;
        frame60.a = param3Object1;
        frame60.d = param3Object2;
        return call_with_values.callWithValues((Procedure)frame60.lambda$Fn61, (Procedure)frame60.lambda$Fn62);
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 59) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 60) {
          param3CallContext.value1 = param3Object1;
          param3CallContext.value2 = param3Object2;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
      }
      
      public class frame60 extends ModuleBody {
        Object a;
        
        Object d;
        
        final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 57, null, 0);
        
        final ModuleMethod lambda$Fn62;
        
        srfi1.frame57.frame58.frame59 staticLink;
        
        public frame60() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 58, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:765");
          this.lambda$Fn62 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param4ModuleMethod) {
          return (param4ModuleMethod.selector == 57) ? lambda81() : super.apply0(param4ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 58) ? lambda82(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda81() {
          return this.staticLink.staticLink.staticLink.lambda76recur(this.staticLink.other$Mnlists);
        }
        
        Object lambda82(Object param4Object1, Object param4Object2) {
          return misc.values(new Object[] { lists.cons(this.a, param4Object1), lists.cons(this.d, param4Object2) });
        }
        
        public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 57) {
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param4ModuleMethod, param4CallContext);
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 58) {
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
    
    public class frame60 extends ModuleBody {
      Object a;
      
      Object d;
      
      final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 57, null, 0);
      
      final ModuleMethod lambda$Fn62;
      
      srfi1.frame57.frame58.frame59 staticLink;
      
      public frame60() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 58, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:765");
        this.lambda$Fn62 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 57) ? lambda81() : super.apply0(param3ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 58) ? lambda82(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda81() {
        return this.staticLink.staticLink.staticLink.lambda76recur(this.staticLink.other$Mnlists);
      }
      
      Object lambda82(Object param3Object1, Object param3Object2) {
        return misc.values(new Object[] { lists.cons(this.a, param3Object1), lists.cons(this.d, param3Object2) });
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 57) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 58) {
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
  
  public class frame59 extends ModuleBody {
    final ModuleMethod lambda$Fn59 = new ModuleMethod(this, 59, null, 0);
    
    final ModuleMethod lambda$Fn60;
    
    Object list;
    
    Object other$Mnlists;
    
    srfi1.frame57.frame58 staticLink;
    
    public frame59() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 60, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:764");
      this.lambda$Fn60 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 59) ? lambda79() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 60) ? lambda80(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda79() {
      return srfi1.car$PlCdr(this.list);
    }
    
    Object lambda80(Object param1Object1, Object param1Object2) {
      frame60 frame60 = new frame60();
      frame60.staticLink = this;
      frame60.a = param1Object1;
      frame60.d = param1Object2;
      return call_with_values.callWithValues((Procedure)frame60.lambda$Fn61, (Procedure)frame60.lambda$Fn62);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 59) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 60) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame60 extends ModuleBody {
      Object a;
      
      Object d;
      
      final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 57, null, 0);
      
      final ModuleMethod lambda$Fn62;
      
      srfi1.frame57.frame58.frame59 staticLink;
      
      public frame60() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 58, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:765");
        this.lambda$Fn62 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param4ModuleMethod) {
        return (param4ModuleMethod.selector == 57) ? lambda81() : super.apply0(param4ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
        return (param4ModuleMethod.selector == 58) ? lambda82(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
      }
      
      Object lambda81() {
        return this.staticLink.staticLink.staticLink.lambda76recur(this.staticLink.other$Mnlists);
      }
      
      Object lambda82(Object param4Object1, Object param4Object2) {
        return misc.values(new Object[] { lists.cons(this.a, param4Object1), lists.cons(this.d, param4Object2) });
      }
      
      public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 57) {
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param4ModuleMethod, param4CallContext);
      }
      
      public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 58) {
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
  
  public class frame6 extends ModuleBody {
    Object elt;
    
    final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 7, null, 0);
    
    final ModuleMethod lambda$Fn8;
    
    Object lis;
    
    public frame6() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 8, null, 20485);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:654");
      this.lambda$Fn8 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 7) ? lambda12() : super.apply0(param1ModuleMethod);
    }
    
    public Object applyN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject) {
      return (param1ModuleMethod.selector == 8) ? lambda13(param1ArrayOfObject[0], param1ArrayOfObject[1], param1ArrayOfObject[2], param1ArrayOfObject[3], param1ArrayOfObject[4]) : super.applyN(param1ModuleMethod, param1ArrayOfObject);
    }
    
    Object lambda12() {
      return srfi1.frame5.lambda11recur(lists.cdr.apply1(this.lis));
    }
    
    Object lambda13(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4, Object param1Object5) {
      return misc.values(new Object[] { lists.cons(lists.car.apply1(this.elt), param1Object1), lists.cons(lists.cadr.apply1(this.elt), param1Object2), lists.cons(lists.caddr.apply1(this.elt), param1Object3), lists.cons(lists.cadddr.apply1(this.elt), param1Object4), lists.cons(lists.car.apply1(lists.cddddr.apply1(this.elt)), param1Object5) });
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 7) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int matchN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 8) {
        param1CallContext.values = param1ArrayOfObject;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 5;
        return 0;
      } 
      return super.matchN(param1ModuleMethod, param1ArrayOfObject, param1CallContext);
    }
  }
  
  public class frame60 extends ModuleBody {
    Object a;
    
    Object d;
    
    final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 57, null, 0);
    
    final ModuleMethod lambda$Fn62;
    
    srfi1.frame57.frame58.frame59 staticLink;
    
    public frame60() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 58, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:765");
      this.lambda$Fn62 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 57) ? lambda81() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 58) ? lambda82(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda81() {
      return this.staticLink.staticLink.staticLink.lambda76recur(this.staticLink.other$Mnlists);
    }
    
    Object lambda82(Object param1Object1, Object param1Object2) {
      return misc.values(new Object[] { lists.cons(this.a, param1Object1), lists.cons(this.d, param1Object2) });
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 57) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 58) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame61 extends ModuleBody {
    final ModuleMethod lambda$Fn63 = new ModuleMethod(this, 63, null, 0);
    
    Object lists;
    
    static Pair lambda84(Object param1Object1, Object param1Object2) {
      return lists.cons(param1Object1, param1Object2);
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 63) ? lambda83() : super.apply0(param1ModuleMethod);
    }
    
    Object lambda83() {
      return srfi1.$PcCars$PlCdrs(this.lists);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 63) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
  }
  
  public class frame62 extends ModuleBody {
    Object cars$Mnfinal;
  }
  
  public class frame63 extends ModuleBody {
    Continuation abort;
    
    srfi1.frame62 staticLink;
    
    public Object lambda85recur(Object param1Object) {
      frame64 frame64 = new frame64();
      frame64.staticLink = this;
      frame64.lists = param1Object;
      return lists.isPair(frame64.lists) ? call_with_values.callWithValues((Procedure)frame64.lambda$Fn65, (Procedure)frame64.lambda$Fn66) : misc.values(new Object[] { LList.list1(this.staticLink.cars$Mnfinal), LList.Empty });
    }
    
    public class frame64 extends ModuleBody {
      final ModuleMethod lambda$Fn65 = new ModuleMethod(this, 68, null, 0);
      
      final ModuleMethod lambda$Fn66;
      
      Object lists;
      
      srfi1.frame63 staticLink;
      
      public frame64() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 69, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:783");
        this.lambda$Fn66 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 68) ? lambda86() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 69) ? lambda87(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda86() {
        return srfi1.car$PlCdr(this.lists);
      }
      
      Object lambda87(Object param2Object1, Object param2Object2) {
        frame65 frame65 = new frame65();
        frame65.staticLink = this;
        frame65.list = param2Object1;
        frame65.other$Mnlists = param2Object2;
        return (srfi1.isNullList(frame65.list) != Boolean.FALSE) ? this.staticLink.abort.apply2(LList.Empty, LList.Empty) : call_with_values.callWithValues((Procedure)frame65.lambda$Fn67, (Procedure)frame65.lambda$Fn68);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 68) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 69) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
      
      public class frame65 extends ModuleBody {
        final ModuleMethod lambda$Fn67 = new ModuleMethod(this, 66, null, 0);
        
        final ModuleMethod lambda$Fn68;
        
        Object list;
        
        Object other$Mnlists;
        
        srfi1.frame63.frame64 staticLink;
        
        public frame65() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 67, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:785");
          this.lambda$Fn68 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 66) ? lambda88() : super.apply0(param3ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 67) ? lambda89(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda88() {
          return srfi1.car$PlCdr(this.list);
        }
        
        Object lambda89(Object param3Object1, Object param3Object2) {
          frame66 frame66 = new frame66();
          frame66.staticLink = this;
          frame66.a = param3Object1;
          frame66.d = param3Object2;
          return call_with_values.callWithValues((Procedure)frame66.lambda$Fn69, (Procedure)frame66.lambda$Fn70);
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 66) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 67) {
            param3CallContext.value1 = param3Object1;
            param3CallContext.value2 = param3Object2;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
        }
        
        public class frame66 extends ModuleBody {
          Object a;
          
          Object d;
          
          final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 64, null, 0);
          
          final ModuleMethod lambda$Fn70;
          
          srfi1.frame63.frame64.frame65 staticLink;
          
          public frame66() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 65, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:786");
            this.lambda$Fn70 = moduleMethod;
          }
          
          public Object apply0(ModuleMethod param4ModuleMethod) {
            return (param4ModuleMethod.selector == 64) ? lambda90() : super.apply0(param4ModuleMethod);
          }
          
          public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
            return (param4ModuleMethod.selector == 65) ? lambda91(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
          }
          
          Object lambda90() {
            return this.staticLink.staticLink.staticLink.lambda85recur(this.staticLink.other$Mnlists);
          }
          
          Object lambda91(Object param4Object1, Object param4Object2) {
            return misc.values(new Object[] { lists.cons(this.a, param4Object1), lists.cons(this.d, param4Object2) });
          }
          
          public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 64) {
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 0;
              return 0;
            } 
            return super.match0(param4ModuleMethod, param4CallContext);
          }
          
          public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 65) {
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
      
      public class frame66 extends ModuleBody {
        Object a;
        
        Object d;
        
        final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 64, null, 0);
        
        final ModuleMethod lambda$Fn70;
        
        srfi1.frame63.frame64.frame65 staticLink;
        
        public frame66() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 65, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:786");
          this.lambda$Fn70 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 64) ? lambda90() : super.apply0(param3ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 65) ? lambda91(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda90() {
          return this.staticLink.staticLink.staticLink.lambda85recur(this.staticLink.other$Mnlists);
        }
        
        Object lambda91(Object param3Object1, Object param3Object2) {
          return misc.values(new Object[] { lists.cons(this.a, param3Object1), lists.cons(this.d, param3Object2) });
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 64) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 65) {
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
    
    public class frame65 extends ModuleBody {
      final ModuleMethod lambda$Fn67 = new ModuleMethod(this, 66, null, 0);
      
      final ModuleMethod lambda$Fn68;
      
      Object list;
      
      Object other$Mnlists;
      
      srfi1.frame63.frame64 staticLink;
      
      public frame65() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 67, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:785");
        this.lambda$Fn68 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 66) ? lambda88() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 67) ? lambda89(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda88() {
        return srfi1.car$PlCdr(this.list);
      }
      
      Object lambda89(Object param2Object1, Object param2Object2) {
        frame66 frame66 = new frame66();
        frame66.staticLink = this;
        frame66.a = param2Object1;
        frame66.d = param2Object2;
        return call_with_values.callWithValues((Procedure)frame66.lambda$Fn69, (Procedure)frame66.lambda$Fn70);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 66) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 67) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
      
      public class frame66 extends ModuleBody {
        Object a;
        
        Object d;
        
        final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 64, null, 0);
        
        final ModuleMethod lambda$Fn70;
        
        srfi1.frame63.frame64.frame65 staticLink;
        
        public frame66() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 65, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:786");
          this.lambda$Fn70 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param4ModuleMethod) {
          return (param4ModuleMethod.selector == 64) ? lambda90() : super.apply0(param4ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 65) ? lambda91(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda90() {
          return this.staticLink.staticLink.staticLink.lambda85recur(this.staticLink.other$Mnlists);
        }
        
        Object lambda91(Object param4Object1, Object param4Object2) {
          return misc.values(new Object[] { lists.cons(this.a, param4Object1), lists.cons(this.d, param4Object2) });
        }
        
        public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 64) {
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param4ModuleMethod, param4CallContext);
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 65) {
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
    
    public class frame66 extends ModuleBody {
      Object a;
      
      Object d;
      
      final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 64, null, 0);
      
      final ModuleMethod lambda$Fn70;
      
      srfi1.frame63.frame64.frame65 staticLink;
      
      public frame66() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 65, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:786");
        this.lambda$Fn70 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 64) ? lambda90() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 65) ? lambda91(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda90() {
        return this.staticLink.staticLink.staticLink.lambda85recur(this.staticLink.other$Mnlists);
      }
      
      Object lambda91(Object param2Object1, Object param2Object2) {
        return misc.values(new Object[] { lists.cons(this.a, param2Object1), lists.cons(this.d, param2Object2) });
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 64) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 65) {
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
  
  public class frame64 extends ModuleBody {
    final ModuleMethod lambda$Fn65 = new ModuleMethod(this, 68, null, 0);
    
    final ModuleMethod lambda$Fn66;
    
    Object lists;
    
    srfi1.frame63 staticLink;
    
    public frame64() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 69, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:783");
      this.lambda$Fn66 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 68) ? lambda86() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 69) ? lambda87(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda86() {
      return srfi1.car$PlCdr(this.lists);
    }
    
    Object lambda87(Object param1Object1, Object param1Object2) {
      frame65 frame65 = new frame65();
      frame65.staticLink = this;
      frame65.list = param1Object1;
      frame65.other$Mnlists = param1Object2;
      return (srfi1.isNullList(frame65.list) != Boolean.FALSE) ? this.staticLink.abort.apply2(LList.Empty, LList.Empty) : call_with_values.callWithValues((Procedure)frame65.lambda$Fn67, (Procedure)frame65.lambda$Fn68);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 68) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 69) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame65 extends ModuleBody {
      final ModuleMethod lambda$Fn67 = new ModuleMethod(this, 66, null, 0);
      
      final ModuleMethod lambda$Fn68;
      
      Object list;
      
      Object other$Mnlists;
      
      srfi1.frame63.frame64 staticLink;
      
      public frame65() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 67, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:785");
        this.lambda$Fn68 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 66) ? lambda88() : super.apply0(param3ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 67) ? lambda89(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda88() {
        return srfi1.car$PlCdr(this.list);
      }
      
      Object lambda89(Object param3Object1, Object param3Object2) {
        frame66 frame66 = new frame66();
        frame66.staticLink = this;
        frame66.a = param3Object1;
        frame66.d = param3Object2;
        return call_with_values.callWithValues((Procedure)frame66.lambda$Fn69, (Procedure)frame66.lambda$Fn70);
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 66) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 67) {
          param3CallContext.value1 = param3Object1;
          param3CallContext.value2 = param3Object2;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
      }
      
      public class frame66 extends ModuleBody {
        Object a;
        
        Object d;
        
        final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 64, null, 0);
        
        final ModuleMethod lambda$Fn70;
        
        srfi1.frame63.frame64.frame65 staticLink;
        
        public frame66() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 65, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:786");
          this.lambda$Fn70 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param4ModuleMethod) {
          return (param4ModuleMethod.selector == 64) ? lambda90() : super.apply0(param4ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 65) ? lambda91(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda90() {
          return this.staticLink.staticLink.staticLink.lambda85recur(this.staticLink.other$Mnlists);
        }
        
        Object lambda91(Object param4Object1, Object param4Object2) {
          return misc.values(new Object[] { lists.cons(this.a, param4Object1), lists.cons(this.d, param4Object2) });
        }
        
        public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 64) {
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param4ModuleMethod, param4CallContext);
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 65) {
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
    
    public class frame66 extends ModuleBody {
      Object a;
      
      Object d;
      
      final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 64, null, 0);
      
      final ModuleMethod lambda$Fn70;
      
      srfi1.frame63.frame64.frame65 staticLink;
      
      public frame66() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 65, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:786");
        this.lambda$Fn70 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 64) ? lambda90() : super.apply0(param3ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 65) ? lambda91(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda90() {
        return this.staticLink.staticLink.staticLink.lambda85recur(this.staticLink.other$Mnlists);
      }
      
      Object lambda91(Object param3Object1, Object param3Object2) {
        return misc.values(new Object[] { lists.cons(this.a, param3Object1), lists.cons(this.d, param3Object2) });
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 64) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 65) {
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
  
  public class frame65 extends ModuleBody {
    final ModuleMethod lambda$Fn67 = new ModuleMethod(this, 66, null, 0);
    
    final ModuleMethod lambda$Fn68;
    
    Object list;
    
    Object other$Mnlists;
    
    srfi1.frame63.frame64 staticLink;
    
    public frame65() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 67, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:785");
      this.lambda$Fn68 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 66) ? lambda88() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 67) ? lambda89(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda88() {
      return srfi1.car$PlCdr(this.list);
    }
    
    Object lambda89(Object param1Object1, Object param1Object2) {
      frame66 frame66 = new frame66();
      frame66.staticLink = this;
      frame66.a = param1Object1;
      frame66.d = param1Object2;
      return call_with_values.callWithValues((Procedure)frame66.lambda$Fn69, (Procedure)frame66.lambda$Fn70);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 66) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 67) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame66 extends ModuleBody {
      Object a;
      
      Object d;
      
      final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 64, null, 0);
      
      final ModuleMethod lambda$Fn70;
      
      srfi1.frame63.frame64.frame65 staticLink;
      
      public frame66() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 65, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:786");
        this.lambda$Fn70 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param4ModuleMethod) {
        return (param4ModuleMethod.selector == 64) ? lambda90() : super.apply0(param4ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
        return (param4ModuleMethod.selector == 65) ? lambda91(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
      }
      
      Object lambda90() {
        return this.staticLink.staticLink.staticLink.lambda85recur(this.staticLink.other$Mnlists);
      }
      
      Object lambda91(Object param4Object1, Object param4Object2) {
        return misc.values(new Object[] { lists.cons(this.a, param4Object1), lists.cons(this.d, param4Object2) });
      }
      
      public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 64) {
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param4ModuleMethod, param4CallContext);
      }
      
      public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 65) {
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
  
  public class frame66 extends ModuleBody {
    Object a;
    
    Object d;
    
    final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 64, null, 0);
    
    final ModuleMethod lambda$Fn70;
    
    srfi1.frame63.frame64.frame65 staticLink;
    
    public frame66() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 65, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:786");
      this.lambda$Fn70 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 64) ? lambda90() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 65) ? lambda91(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda90() {
      return this.staticLink.staticLink.staticLink.lambda85recur(this.staticLink.other$Mnlists);
    }
    
    Object lambda91(Object param1Object1, Object param1Object2) {
      return misc.values(new Object[] { lists.cons(this.a, param1Object1), lists.cons(this.d, param1Object2) });
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 64) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 65) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame67 extends ModuleBody {
    public static Object lambda92recur(Object param1Object) {
      frame68 frame68 = new frame68();
      frame68.lists = param1Object;
      return lists.isPair(frame68.lists) ? call_with_values.callWithValues((Procedure)frame68.lambda$Fn71, (Procedure)frame68.lambda$Fn72) : misc.values(new Object[] { LList.Empty, LList.Empty });
    }
    
    public class frame68 extends ModuleBody {
      final ModuleMethod lambda$Fn71 = new ModuleMethod(this, 74, null, 0);
      
      final ModuleMethod lambda$Fn72;
      
      Object lists;
      
      public frame68() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 75, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:794");
        this.lambda$Fn72 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 74) ? lambda93() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 75) ? lambda94(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda93() {
        return srfi1.car$PlCdr(this.lists);
      }
      
      Object lambda94(Object param2Object1, Object param2Object2) {
        frame69 frame69 = new frame69();
        frame69.staticLink = this;
        frame69.list = param2Object1;
        frame69.other$Mnlists = param2Object2;
        return call_with_values.callWithValues((Procedure)frame69.lambda$Fn73, (Procedure)frame69.lambda$Fn74);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 74) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 75) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
      
      public class frame69 extends ModuleBody {
        final ModuleMethod lambda$Fn73 = new ModuleMethod(this, 72, null, 0);
        
        final ModuleMethod lambda$Fn74;
        
        Object list;
        
        Object other$Mnlists;
        
        srfi1.frame67.frame68 staticLink;
        
        public frame69() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 73, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:795");
          this.lambda$Fn74 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 72) ? lambda95() : super.apply0(param3ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 73) ? lambda96(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda95() {
          return srfi1.car$PlCdr(this.list);
        }
        
        Object lambda96(Object param3Object1, Object param3Object2) {
          frame70 frame70 = new frame70();
          frame70.staticLink = this;
          frame70.a = param3Object1;
          frame70.d = param3Object2;
          return call_with_values.callWithValues((Procedure)frame70.lambda$Fn75, (Procedure)frame70.lambda$Fn76);
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 72) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 73) {
            param3CallContext.value1 = param3Object1;
            param3CallContext.value2 = param3Object2;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 2;
            return 0;
          } 
          return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
        }
        
        public class frame70 extends ModuleBody {
          Object a;
          
          Object d;
          
          final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 70, null, 0);
          
          final ModuleMethod lambda$Fn76;
          
          srfi1.frame67.frame68.frame69 staticLink;
          
          public frame70() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 71, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:796");
            this.lambda$Fn76 = moduleMethod;
          }
          
          public Object apply0(ModuleMethod param4ModuleMethod) {
            return (param4ModuleMethod.selector == 70) ? lambda97() : super.apply0(param4ModuleMethod);
          }
          
          public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
            return (param4ModuleMethod.selector == 71) ? lambda98(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
          }
          
          Object lambda97() {
            return srfi1.frame67.lambda92recur(this.staticLink.other$Mnlists);
          }
          
          Object lambda98(Object param4Object1, Object param4Object2) {
            return misc.values(new Object[] { lists.cons(this.a, param4Object1), lists.cons(this.d, param4Object2) });
          }
          
          public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 70) {
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 0;
              return 0;
            } 
            return super.match0(param4ModuleMethod, param4CallContext);
          }
          
          public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 71) {
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
      
      public class frame70 extends ModuleBody {
        Object a;
        
        Object d;
        
        final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 70, null, 0);
        
        final ModuleMethod lambda$Fn76;
        
        srfi1.frame67.frame68.frame69 staticLink;
        
        public frame70() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 71, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:796");
          this.lambda$Fn76 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 70) ? lambda97() : super.apply0(param3ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
          return (param3ModuleMethod.selector == 71) ? lambda98(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
        }
        
        Object lambda97() {
          return srfi1.frame67.lambda92recur(this.staticLink.other$Mnlists);
        }
        
        Object lambda98(Object param3Object1, Object param3Object2) {
          return misc.values(new Object[] { lists.cons(this.a, param3Object1), lists.cons(this.d, param3Object2) });
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 70) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
        
        public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 71) {
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
    
    public class frame69 extends ModuleBody {
      final ModuleMethod lambda$Fn73 = new ModuleMethod(this, 72, null, 0);
      
      final ModuleMethod lambda$Fn74;
      
      Object list;
      
      Object other$Mnlists;
      
      srfi1.frame67.frame68 staticLink;
      
      public frame69() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 73, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:795");
        this.lambda$Fn74 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 72) ? lambda95() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 73) ? lambda96(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda95() {
        return srfi1.car$PlCdr(this.list);
      }
      
      Object lambda96(Object param2Object1, Object param2Object2) {
        frame70 frame70 = new frame70();
        frame70.staticLink = this;
        frame70.a = param2Object1;
        frame70.d = param2Object2;
        return call_with_values.callWithValues((Procedure)frame70.lambda$Fn75, (Procedure)frame70.lambda$Fn76);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 72) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 73) {
          param2CallContext.value1 = param2Object1;
          param2CallContext.value2 = param2Object2;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
      }
      
      public class frame70 extends ModuleBody {
        Object a;
        
        Object d;
        
        final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 70, null, 0);
        
        final ModuleMethod lambda$Fn76;
        
        srfi1.frame67.frame68.frame69 staticLink;
        
        public frame70() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 71, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:796");
          this.lambda$Fn76 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param4ModuleMethod) {
          return (param4ModuleMethod.selector == 70) ? lambda97() : super.apply0(param4ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 71) ? lambda98(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda97() {
          return srfi1.frame67.lambda92recur(this.staticLink.other$Mnlists);
        }
        
        Object lambda98(Object param4Object1, Object param4Object2) {
          return misc.values(new Object[] { lists.cons(this.a, param4Object1), lists.cons(this.d, param4Object2) });
        }
        
        public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 70) {
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param4ModuleMethod, param4CallContext);
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 71) {
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
    
    public class frame70 extends ModuleBody {
      Object a;
      
      Object d;
      
      final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 70, null, 0);
      
      final ModuleMethod lambda$Fn76;
      
      srfi1.frame67.frame68.frame69 staticLink;
      
      public frame70() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 71, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:796");
        this.lambda$Fn76 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 70) ? lambda97() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 71) ? lambda98(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda97() {
        return srfi1.frame67.lambda92recur(this.staticLink.other$Mnlists);
      }
      
      Object lambda98(Object param2Object1, Object param2Object2) {
        return misc.values(new Object[] { lists.cons(this.a, param2Object1), lists.cons(this.d, param2Object2) });
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 70) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 71) {
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
  
  public class frame68 extends ModuleBody {
    final ModuleMethod lambda$Fn71 = new ModuleMethod(this, 74, null, 0);
    
    final ModuleMethod lambda$Fn72;
    
    Object lists;
    
    public frame68() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 75, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:794");
      this.lambda$Fn72 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 74) ? lambda93() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 75) ? lambda94(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda93() {
      return srfi1.car$PlCdr(this.lists);
    }
    
    Object lambda94(Object param1Object1, Object param1Object2) {
      frame69 frame69 = new frame69();
      frame69.staticLink = this;
      frame69.list = param1Object1;
      frame69.other$Mnlists = param1Object2;
      return call_with_values.callWithValues((Procedure)frame69.lambda$Fn73, (Procedure)frame69.lambda$Fn74);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 74) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 75) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame69 extends ModuleBody {
      final ModuleMethod lambda$Fn73 = new ModuleMethod(this, 72, null, 0);
      
      final ModuleMethod lambda$Fn74;
      
      Object list;
      
      Object other$Mnlists;
      
      srfi1.frame67.frame68 staticLink;
      
      public frame69() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 73, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:795");
        this.lambda$Fn74 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 72) ? lambda95() : super.apply0(param3ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 73) ? lambda96(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda95() {
        return srfi1.car$PlCdr(this.list);
      }
      
      Object lambda96(Object param3Object1, Object param3Object2) {
        frame70 frame70 = new frame70();
        frame70.staticLink = this;
        frame70.a = param3Object1;
        frame70.d = param3Object2;
        return call_with_values.callWithValues((Procedure)frame70.lambda$Fn75, (Procedure)frame70.lambda$Fn76);
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 72) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 73) {
          param3CallContext.value1 = param3Object1;
          param3CallContext.value2 = param3Object2;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 2;
          return 0;
        } 
        return super.match2(param3ModuleMethod, param3Object1, param3Object2, param3CallContext);
      }
      
      public class frame70 extends ModuleBody {
        Object a;
        
        Object d;
        
        final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 70, null, 0);
        
        final ModuleMethod lambda$Fn76;
        
        srfi1.frame67.frame68.frame69 staticLink;
        
        public frame70() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 71, null, 8194);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:796");
          this.lambda$Fn76 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param4ModuleMethod) {
          return (param4ModuleMethod.selector == 70) ? lambda97() : super.apply0(param4ModuleMethod);
        }
        
        public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
          return (param4ModuleMethod.selector == 71) ? lambda98(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
        }
        
        Object lambda97() {
          return srfi1.frame67.lambda92recur(this.staticLink.other$Mnlists);
        }
        
        Object lambda98(Object param4Object1, Object param4Object2) {
          return misc.values(new Object[] { lists.cons(this.a, param4Object1), lists.cons(this.d, param4Object2) });
        }
        
        public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 70) {
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param4ModuleMethod, param4CallContext);
        }
        
        public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 71) {
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
    
    public class frame70 extends ModuleBody {
      Object a;
      
      Object d;
      
      final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 70, null, 0);
      
      final ModuleMethod lambda$Fn76;
      
      srfi1.frame67.frame68.frame69 staticLink;
      
      public frame70() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 71, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:796");
        this.lambda$Fn76 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 70) ? lambda97() : super.apply0(param3ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2) {
        return (param3ModuleMethod.selector == 71) ? lambda98(param3Object1, param3Object2) : super.apply2(param3ModuleMethod, param3Object1, param3Object2);
      }
      
      Object lambda97() {
        return srfi1.frame67.lambda92recur(this.staticLink.other$Mnlists);
      }
      
      Object lambda98(Object param3Object1, Object param3Object2) {
        return misc.values(new Object[] { lists.cons(this.a, param3Object1), lists.cons(this.d, param3Object2) });
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 70) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
      
      public int match2(ModuleMethod param3ModuleMethod, Object param3Object1, Object param3Object2, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 71) {
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
  
  public class frame69 extends ModuleBody {
    final ModuleMethod lambda$Fn73 = new ModuleMethod(this, 72, null, 0);
    
    final ModuleMethod lambda$Fn74;
    
    Object list;
    
    Object other$Mnlists;
    
    srfi1.frame67.frame68 staticLink;
    
    public frame69() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 73, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:795");
      this.lambda$Fn74 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 72) ? lambda95() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 73) ? lambda96(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda95() {
      return srfi1.car$PlCdr(this.list);
    }
    
    Object lambda96(Object param1Object1, Object param1Object2) {
      frame70 frame70 = new frame70();
      frame70.staticLink = this;
      frame70.a = param1Object1;
      frame70.d = param1Object2;
      return call_with_values.callWithValues((Procedure)frame70.lambda$Fn75, (Procedure)frame70.lambda$Fn76);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 72) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 73) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame70 extends ModuleBody {
      Object a;
      
      Object d;
      
      final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 70, null, 0);
      
      final ModuleMethod lambda$Fn76;
      
      srfi1.frame67.frame68.frame69 staticLink;
      
      public frame70() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 71, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:796");
        this.lambda$Fn76 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param4ModuleMethod) {
        return (param4ModuleMethod.selector == 70) ? lambda97() : super.apply0(param4ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2) {
        return (param4ModuleMethod.selector == 71) ? lambda98(param4Object1, param4Object2) : super.apply2(param4ModuleMethod, param4Object1, param4Object2);
      }
      
      Object lambda97() {
        return srfi1.frame67.lambda92recur(this.staticLink.other$Mnlists);
      }
      
      Object lambda98(Object param4Object1, Object param4Object2) {
        return misc.values(new Object[] { lists.cons(this.a, param4Object1), lists.cons(this.d, param4Object2) });
      }
      
      public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 70) {
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param4ModuleMethod, param4CallContext);
      }
      
      public int match2(ModuleMethod param4ModuleMethod, Object param4Object1, Object param4Object2, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 71) {
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
  
  public class frame7 extends ModuleBody {
    Procedure kons;
    
    public Object lambda14lp(Object param1Object1, Object param1Object2) {
      frame8 frame8 = new frame8();
      frame8.staticLink = this;
      frame8.lists = param1Object1;
      frame8.ans = param1Object2;
      return call_with_values.callWithValues((Procedure)frame8.lambda$Fn9, (Procedure)frame8.lambda$Fn10);
    }
    
    public class frame8 extends ModuleBody {
      Object ans;
      
      final ModuleMethod lambda$Fn10;
      
      final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 9, null, 0);
      
      Object lists;
      
      srfi1.frame7 staticLink;
      
      public frame8() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 10, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:859");
        this.lambda$Fn10 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 9) ? lambda15() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 10) ? lambda16(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda15() {
        return srfi1.$PcCars$PlCdrs$Pl(this.lists, this.ans);
      }
      
      Object lambda16(Object param2Object1, Object param2Object2) {
        return lists.isNull(param2Object1) ? this.ans : this.staticLink.lambda14lp(param2Object2, Scheme.apply.apply2(this.staticLink.kons, param2Object1));
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 9) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
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
    }
  }
  
  public class frame70 extends ModuleBody {
    Object a;
    
    Object d;
    
    final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 70, null, 0);
    
    final ModuleMethod lambda$Fn76;
    
    srfi1.frame67.frame68.frame69 staticLink;
    
    public frame70() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 71, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:796");
      this.lambda$Fn76 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 70) ? lambda97() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 71) ? lambda98(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda97() {
      return srfi1.frame67.lambda92recur(this.staticLink.other$Mnlists);
    }
    
    Object lambda98(Object param1Object1, Object param1Object2) {
      return misc.values(new Object[] { lists.cons(this.a, param1Object1), lists.cons(this.d, param1Object2) });
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 70) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 71) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame71 extends ModuleBody {
    final ModuleMethod lambda$Fn77 = new ModuleMethod(this, 76, null, 0);
    
    Object lists;
    
    static Pair lambda100(Object param1Object1, Object param1Object2) {
      return lists.cons(param1Object1, param1Object2);
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 76) ? lambda99() : super.apply0(param1ModuleMethod);
    }
    
    Object lambda99() {
      return srfi1.$PcCars$PlCdrs$SlNoTest(this.lists);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 76) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
  }
  
  public class frame72 extends ModuleBody {
    Object $Eq;
    
    final ModuleMethod lambda$Fn79;
    
    Object lis2;
    
    public frame72() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 77, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1443");
      this.lambda$Fn79 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 77) ? lambda101(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda101(Object param1Object) {
      Object object2 = this.lis2;
      Object object1 = this.$Eq;
      try {
        Procedure procedure = (Procedure)object1;
        return lists.member(param1Object, object2, procedure);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "member", 3, object1);
      } 
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 77) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame8 extends ModuleBody {
    Object ans;
    
    final ModuleMethod lambda$Fn10;
    
    final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 9, null, 0);
    
    Object lists;
    
    srfi1.frame7 staticLink;
    
    public frame8() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 10, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:859");
      this.lambda$Fn10 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 9) ? lambda15() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 10) ? lambda16(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda15() {
      return srfi1.$PcCars$PlCdrs$Pl(this.lists, this.ans);
    }
    
    Object lambda16(Object param1Object1, Object param1Object2) {
      return lists.isNull(param1Object1) ? this.ans : this.staticLink.lambda14lp(param1Object2, Scheme.apply.apply2(this.staticLink.kons, param1Object1));
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 9) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
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
  }
  
  public class frame9 extends ModuleBody {
    Object knil;
    
    Procedure kons;
    
    public Object lambda17recur(Object param1Object) {
      Object object = srfi1.$PcCdrs(param1Object);
      return lists.isNull(object) ? this.knil : Scheme.apply.apply2(this.kons, srfi1.$PcCars$Pl(param1Object, lambda17recur(object)));
    }
    
    public Object lambda18recur(Object param1Object) {
      if (srfi1.isNullList(param1Object) != Boolean.FALSE)
        return this.knil; 
      Object object = lists.car.apply1(param1Object);
      return this.kons.apply2(object, lambda18recur(lists.cdr.apply1(param1Object)));
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/srfi1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
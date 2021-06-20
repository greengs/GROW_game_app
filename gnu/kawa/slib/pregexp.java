package gnu.kawa.slib;

import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.append;

public class pregexp extends ModuleBody {
  public static Char $Stpregexp$Mncomment$Mnchar$St;
  
  public static Object $Stpregexp$Mnnul$Mnchar$Mnint$St;
  
  public static Object $Stpregexp$Mnreturn$Mnchar$St;
  
  public static Object $Stpregexp$Mnspace$Mnsensitive$Qu$St;
  
  public static Object $Stpregexp$Mntab$Mnchar$St;
  
  public static IntNum $Stpregexp$Mnversion$St;
  
  public static final pregexp $instance;
  
  static final IntNum Lit0;
  
  static final Char Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit100;
  
  static final SimpleSymbol Lit101;
  
  static final SimpleSymbol Lit102;
  
  static final SimpleSymbol Lit103;
  
  static final SimpleSymbol Lit104;
  
  static final SimpleSymbol Lit105;
  
  static final PairWithPosition Lit106;
  
  static final SimpleSymbol Lit107;
  
  static final PairWithPosition Lit108;
  
  static final SimpleSymbol Lit109;
  
  static final Char Lit11;
  
  static final SimpleSymbol Lit110;
  
  static final SimpleSymbol Lit111;
  
  static final SimpleSymbol Lit112;
  
  static final Char Lit113;
  
  static final SimpleSymbol Lit114;
  
  static final SimpleSymbol Lit115;
  
  static final PairWithPosition Lit116;
  
  static final SimpleSymbol Lit117;
  
  static final SimpleSymbol Lit118;
  
  static final SimpleSymbol Lit119;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit120;
  
  static final SimpleSymbol Lit121;
  
  static final SimpleSymbol Lit122;
  
  static final SimpleSymbol Lit123;
  
  static final SimpleSymbol Lit124;
  
  static final SimpleSymbol Lit125;
  
  static final SimpleSymbol Lit126;
  
  static final SimpleSymbol Lit127;
  
  static final SimpleSymbol Lit128;
  
  static final SimpleSymbol Lit129;
  
  static final Char Lit13;
  
  static final SimpleSymbol Lit130;
  
  static final SimpleSymbol Lit131;
  
  static final SimpleSymbol Lit132;
  
  static final SimpleSymbol Lit133;
  
  static final SimpleSymbol Lit134;
  
  static final SimpleSymbol Lit135 = (SimpleSymbol)(new SimpleSymbol("pregexp-quote")).readResolve();
  
  static final SimpleSymbol Lit14;
  
  static final Char Lit15;
  
  static final IntNum Lit16;
  
  static final SimpleSymbol Lit17;
  
  static final Char Lit18;
  
  static final Char Lit19;
  
  static final Char Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SimpleSymbol Lit22;
  
  static final SimpleSymbol Lit23;
  
  static final Char Lit24;
  
  static final Char Lit25;
  
  static final SimpleSymbol Lit26;
  
  static final Char Lit27;
  
  static final SimpleSymbol Lit28;
  
  static final Char Lit29;
  
  static final Char Lit3;
  
  static final SimpleSymbol Lit30;
  
  static final Char Lit31;
  
  static final PairWithPosition Lit32;
  
  static final Char Lit33;
  
  static final Char Lit34;
  
  static final Char Lit35;
  
  static final SimpleSymbol Lit36;
  
  static final Char Lit37;
  
  static final PairWithPosition Lit38;
  
  static final Char Lit39;
  
  static final SimpleSymbol Lit4;
  
  static final Char Lit40;
  
  static final SimpleSymbol Lit41;
  
  static final Char Lit42;
  
  static final PairWithPosition Lit43;
  
  static final Char Lit44;
  
  static final SimpleSymbol Lit45;
  
  static final Char Lit46;
  
  static final Char Lit47;
  
  static final Char Lit48;
  
  static final PairWithPosition Lit49;
  
  static final SimpleSymbol Lit5;
  
  static final Char Lit50;
  
  static final PairWithPosition Lit51;
  
  static final Char Lit52;
  
  static final PairWithPosition Lit53;
  
  static final Char Lit54;
  
  static final PairWithPosition Lit55;
  
  static final PairWithPosition Lit56;
  
  static final SimpleSymbol Lit57;
  
  static final Char Lit58;
  
  static final Char Lit59;
  
  static final Char Lit6;
  
  static final SimpleSymbol Lit60;
  
  static final SimpleSymbol Lit61;
  
  static final Char Lit62;
  
  static final PairWithPosition Lit63;
  
  static final SimpleSymbol Lit64;
  
  static final Char Lit65;
  
  static final Char Lit66;
  
  static final Char Lit67;
  
  static final SimpleSymbol Lit68;
  
  static final SimpleSymbol Lit69;
  
  static final Char Lit7;
  
  static final SimpleSymbol Lit70;
  
  static final SimpleSymbol Lit71;
  
  static final SimpleSymbol Lit72;
  
  static final IntNum Lit73;
  
  static final SimpleSymbol Lit74;
  
  static final SimpleSymbol Lit75;
  
  static final SimpleSymbol Lit76;
  
  static final Char Lit77;
  
  static final Char Lit78;
  
  static final SimpleSymbol Lit79;
  
  static final IntNum Lit8;
  
  static final SimpleSymbol Lit80;
  
  static final SimpleSymbol Lit81;
  
  static final SimpleSymbol Lit82;
  
  static final SimpleSymbol Lit83;
  
  static final Char Lit84;
  
  static final SimpleSymbol Lit85;
  
  static final SimpleSymbol Lit86;
  
  static final SimpleSymbol Lit87;
  
  static final SimpleSymbol Lit88;
  
  static final SimpleSymbol Lit89;
  
  static final Char Lit9;
  
  static final SimpleSymbol Lit90;
  
  static final SimpleSymbol Lit91;
  
  static final SimpleSymbol Lit92;
  
  static final SimpleSymbol Lit93;
  
  static final SimpleSymbol Lit94;
  
  static final SimpleSymbol Lit95;
  
  static final Char Lit96;
  
  static final Char Lit97;
  
  static final Char Lit98;
  
  static final SimpleSymbol Lit99;
  
  static final ModuleMethod lambda$Fn1;
  
  static final ModuleMethod lambda$Fn10;
  
  static final ModuleMethod lambda$Fn6;
  
  static final ModuleMethod lambda$Fn7;
  
  static final ModuleMethod lambda$Fn8;
  
  static final ModuleMethod lambda$Fn9;
  
  public static final ModuleMethod pregexp;
  
  public static final ModuleMethod pregexp$Mnat$Mnword$Mnboundary$Qu;
  
  public static final ModuleMethod pregexp$Mnchar$Mnword$Qu;
  
  public static final ModuleMethod pregexp$Mncheck$Mnif$Mnin$Mnchar$Mnclass$Qu;
  
  public static final ModuleMethod pregexp$Mnerror;
  
  public static final ModuleMethod pregexp$Mninvert$Mnchar$Mnlist;
  
  public static final ModuleMethod pregexp$Mnlist$Mnref;
  
  public static final ModuleMethod pregexp$Mnmake$Mnbackref$Mnlist;
  
  public static final ModuleMethod pregexp$Mnmatch;
  
  public static final ModuleMethod pregexp$Mnmatch$Mnpositions;
  
  public static final ModuleMethod pregexp$Mnmatch$Mnpositions$Mnaux;
  
  public static final ModuleMethod pregexp$Mnquote;
  
  public static final ModuleMethod pregexp$Mnread$Mnbranch;
  
  public static final ModuleMethod pregexp$Mnread$Mnchar$Mnlist;
  
  public static final ModuleMethod pregexp$Mnread$Mncluster$Mntype;
  
  public static final ModuleMethod pregexp$Mnread$Mnescaped$Mnchar;
  
  public static final ModuleMethod pregexp$Mnread$Mnescaped$Mnnumber;
  
  public static final ModuleMethod pregexp$Mnread$Mnnums;
  
  public static final ModuleMethod pregexp$Mnread$Mnpattern;
  
  public static final ModuleMethod pregexp$Mnread$Mnpiece;
  
  public static final ModuleMethod pregexp$Mnread$Mnposix$Mnchar$Mnclass;
  
  public static final ModuleMethod pregexp$Mnread$Mnsubpattern;
  
  public static final ModuleMethod pregexp$Mnreplace;
  
  public static final ModuleMethod pregexp$Mnreplace$Mnaux;
  
  public static final ModuleMethod pregexp$Mnreplace$St;
  
  public static final ModuleMethod pregexp$Mnreverse$Ex;
  
  public static final ModuleMethod pregexp$Mnsplit;
  
  public static final ModuleMethod pregexp$Mnstring$Mnmatch;
  
  public static final ModuleMethod pregexp$Mnwrap$Mnquantifier$Mnif$Mnany;
  
  static {
    Lit134 = (SimpleSymbol)(new SimpleSymbol("pregexp-replace*")).readResolve();
    Lit133 = (SimpleSymbol)(new SimpleSymbol("pregexp-replace")).readResolve();
    Lit132 = (SimpleSymbol)(new SimpleSymbol("pregexp-split")).readResolve();
    Lit131 = (SimpleSymbol)(new SimpleSymbol("pregexp-match")).readResolve();
    Lit130 = (SimpleSymbol)(new SimpleSymbol("pregexp")).readResolve();
    Lit129 = (SimpleSymbol)(new SimpleSymbol("pregexp-replace-aux")).readResolve();
    Lit128 = (SimpleSymbol)(new SimpleSymbol("pregexp-make-backref-list")).readResolve();
    Lit127 = (SimpleSymbol)(new SimpleSymbol("pregexp-list-ref")).readResolve();
    Lit126 = (SimpleSymbol)(new SimpleSymbol("pregexp-at-word-boundary?")).readResolve();
    Lit125 = (SimpleSymbol)(new SimpleSymbol("pregexp-char-word?")).readResolve();
    Lit124 = (SimpleSymbol)(new SimpleSymbol("pregexp-string-match")).readResolve();
    Lit123 = (SimpleSymbol)(new SimpleSymbol("pregexp-invert-char-list")).readResolve();
    Lit122 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-escaped-char")).readResolve();
    Lit121 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-escaped-number")).readResolve();
    Lit120 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-branch")).readResolve();
    Lit119 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-pattern")).readResolve();
    Lit118 = (SimpleSymbol)(new SimpleSymbol("pregexp-error")).readResolve();
    Lit117 = (SimpleSymbol)(new SimpleSymbol("pregexp-reverse!")).readResolve();
    Char char_1 = Char.make(92);
    Lit19 = char_1;
    Char char_2 = Char.make(46);
    Lit13 = char_2;
    Char char_3 = Char.make(63);
    Lit47 = char_3;
    Char char_4 = Char.make(42);
    Lit65 = char_4;
    Char char_5 = Char.make(43);
    Lit66 = char_5;
    Char char_6 = Char.make(124);
    Lit7 = char_6;
    Char char_7 = Char.make(94);
    Lit9 = char_7;
    Char char_8 = Char.make(36);
    Lit11 = char_8;
    Char char_9 = Char.make(91);
    Lit15 = char_9;
    Char char_10 = Char.make(93);
    Lit46 = char_10;
    Char char_11 = Char.make(123);
    Lit67 = char_11;
    Char char_12 = Char.make(125);
    Lit78 = char_12;
    Char char_13 = Char.make(40);
    Lit18 = char_13;
    Char char_14 = Char.make(41);
    Lit6 = char_14;
    Lit116 = PairWithPosition.make(char_1, PairWithPosition.make(char_2, PairWithPosition.make(char_3, PairWithPosition.make(char_4, PairWithPosition.make(char_5, PairWithPosition.make(char_6, PairWithPosition.make(char_7, PairWithPosition.make(char_8, PairWithPosition.make(char_9, PairWithPosition.make(char_10, PairWithPosition.make(char_11, PairWithPosition.make(char_12, PairWithPosition.make(char_13, PairWithPosition.make(char_14, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153977), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153973), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153969), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153965), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153961), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153957), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149885), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149881), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149877), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149873), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149869), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149865), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149861), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149856);
    Lit115 = (SimpleSymbol)(new SimpleSymbol("pattern-must-be-compiled-or-string-regexp")).readResolve();
    Lit114 = (SimpleSymbol)(new SimpleSymbol("pregexp-match-positions")).readResolve();
    Lit113 = Char.make(38);
    Lit112 = (SimpleSymbol)(new SimpleSymbol("identity")).readResolve();
    Lit111 = (SimpleSymbol)(new SimpleSymbol("fk")).readResolve();
    Lit110 = (SimpleSymbol)(new SimpleSymbol("greedy-quantifier-operand-could-be-empty")).readResolve();
    Lit109 = (SimpleSymbol)(new SimpleSymbol(":no-backtrack")).readResolve();
    SimpleSymbol simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol(":between")).readResolve();
    Lit68 = simpleSymbol1;
    Boolean bool1 = Boolean.FALSE;
    IntNum intNum = IntNum.make(0);
    Lit73 = intNum;
    Boolean bool2 = Boolean.FALSE;
    SimpleSymbol simpleSymbol3 = (SimpleSymbol)(new SimpleSymbol(":any")).readResolve();
    Lit14 = simpleSymbol3;
    Lit108 = PairWithPosition.make(simpleSymbol1, PairWithPosition.make(bool1, PairWithPosition.make(intNum, PairWithPosition.make(bool2, PairWithPosition.make(simpleSymbol3, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338881), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338878), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338876), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338873), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338863);
    Lit107 = (SimpleSymbol)(new SimpleSymbol(":neg-lookbehind")).readResolve();
    Lit106 = PairWithPosition.make(Lit68, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make(Lit73, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make(Lit14, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2302017), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2302014), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2302012), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2302009), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2301999);
    Lit105 = (SimpleSymbol)(new SimpleSymbol(":lookbehind")).readResolve();
    Lit104 = (SimpleSymbol)(new SimpleSymbol(":neg-lookahead")).readResolve();
    Lit103 = (SimpleSymbol)(new SimpleSymbol(":lookahead")).readResolve();
    Lit102 = (SimpleSymbol)(new SimpleSymbol("non-existent-backref")).readResolve();
    Lit101 = (SimpleSymbol)(new SimpleSymbol("pregexp-match-positions-aux")).readResolve();
    Lit100 = (SimpleSymbol)(new SimpleSymbol(":sub")).readResolve();
    Lit99 = (SimpleSymbol)(new SimpleSymbol("pregexp-check-if-in-char-class?")).readResolve();
    Lit98 = Char.make(102);
    Lit97 = Char.make(101);
    Lit96 = Char.make(99);
    Lit95 = (SimpleSymbol)(new SimpleSymbol(":xdigit")).readResolve();
    Lit94 = (SimpleSymbol)(new SimpleSymbol(":upper")).readResolve();
    Lit93 = (SimpleSymbol)(new SimpleSymbol(":punct")).readResolve();
    Lit92 = (SimpleSymbol)(new SimpleSymbol(":print")).readResolve();
    Lit91 = (SimpleSymbol)(new SimpleSymbol(":lower")).readResolve();
    Lit90 = (SimpleSymbol)(new SimpleSymbol(":graph")).readResolve();
    Lit89 = (SimpleSymbol)(new SimpleSymbol(":cntrl")).readResolve();
    Lit88 = (SimpleSymbol)(new SimpleSymbol(":blank")).readResolve();
    Lit87 = (SimpleSymbol)(new SimpleSymbol(":ascii")).readResolve();
    Lit86 = (SimpleSymbol)(new SimpleSymbol(":alpha")).readResolve();
    Lit85 = (SimpleSymbol)(new SimpleSymbol(":alnum")).readResolve();
    Lit84 = Char.make(95);
    Lit83 = (SimpleSymbol)(new SimpleSymbol(":char-range")).readResolve();
    Lit82 = (SimpleSymbol)(new SimpleSymbol(":one-of-chars")).readResolve();
    Lit81 = (SimpleSymbol)(new SimpleSymbol("character-class-ended-too-soon")).readResolve();
    Lit80 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-char-list")).readResolve();
    Lit79 = (SimpleSymbol)(new SimpleSymbol(":none-of-chars")).readResolve();
    Lit77 = Char.make(44);
    Lit76 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-nums")).readResolve();
    Lit75 = (SimpleSymbol)(new SimpleSymbol("left-brace-must-be-followed-by-number")).readResolve();
    Lit74 = (SimpleSymbol)(new SimpleSymbol("pregexp-wrap-quantifier-if-any")).readResolve();
    Lit72 = (SimpleSymbol)(new SimpleSymbol("next-i")).readResolve();
    Lit71 = (SimpleSymbol)(new SimpleSymbol("at-most")).readResolve();
    Lit70 = (SimpleSymbol)(new SimpleSymbol("at-least")).readResolve();
    Lit69 = (SimpleSymbol)(new SimpleSymbol("minimal?")).readResolve();
    Lit64 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-subpattern")).readResolve();
    Lit63 = PairWithPosition.make(Lit100, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 942102);
    Lit62 = Char.make(120);
    Lit61 = (SimpleSymbol)(new SimpleSymbol(":case-insensitive")).readResolve();
    Lit60 = (SimpleSymbol)(new SimpleSymbol(":case-sensitive")).readResolve();
    Lit59 = Char.make(105);
    Lit58 = Char.make(45);
    Lit57 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-cluster-type")).readResolve();
    Lit56 = PairWithPosition.make(Lit107, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 876575);
    Lit55 = PairWithPosition.make(Lit105, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 872479);
    Lit54 = Char.make(60);
    Lit53 = PairWithPosition.make(Lit109, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 860188);
    Lit52 = Char.make(62);
    Lit51 = PairWithPosition.make(Lit104, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 856092);
    Lit50 = Char.make(33);
    Lit49 = PairWithPosition.make(Lit103, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 851996);
    Lit48 = Char.make(61);
    Lit45 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-posix-char-class")).readResolve();
    Lit44 = Char.make(58);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol(":neg-char")).readResolve();
    Lit17 = simpleSymbol1;
    SimpleSymbol simpleSymbol2 = (SimpleSymbol)(new SimpleSymbol(":word")).readResolve();
    Lit41 = simpleSymbol2;
    Lit43 = PairWithPosition.make(simpleSymbol1, PairWithPosition.make(simpleSymbol2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 696359), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 696348);
    Lit42 = Char.make(87);
    Lit40 = Char.make(119);
    Lit39 = Char.make(116);
    simpleSymbol1 = Lit17;
    simpleSymbol2 = (SimpleSymbol)(new SimpleSymbol(":space")).readResolve();
    Lit36 = simpleSymbol2;
    Lit38 = PairWithPosition.make(simpleSymbol1, PairWithPosition.make(simpleSymbol2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 684071), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 684060);
    Lit37 = Char.make(83);
    Lit35 = Char.make(115);
    Lit34 = Char.make(114);
    Lit33 = Char.make(110);
    simpleSymbol1 = Lit17;
    simpleSymbol2 = (SimpleSymbol)(new SimpleSymbol(":digit")).readResolve();
    Lit30 = simpleSymbol2;
    Lit32 = PairWithPosition.make(simpleSymbol1, PairWithPosition.make(simpleSymbol2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 667687), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 667676);
    Lit31 = Char.make(68);
    Lit29 = Char.make(100);
    Lit28 = (SimpleSymbol)(new SimpleSymbol(":not-wbdry")).readResolve();
    Lit27 = Char.make(66);
    Lit26 = (SimpleSymbol)(new SimpleSymbol(":wbdry")).readResolve();
    Lit25 = Char.make(98);
    Lit24 = Char.make(10);
    Lit23 = (SimpleSymbol)(new SimpleSymbol(":empty")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("backslash")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-piece")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol(":backref")).readResolve();
    Lit16 = IntNum.make(2);
    Lit12 = (SimpleSymbol)(new SimpleSymbol(":eos")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol(":bos")).readResolve();
    Lit8 = IntNum.make(1);
    Lit5 = (SimpleSymbol)(new SimpleSymbol(":seq")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol(":or")).readResolve();
    Lit3 = Char.make(32);
    Lit2 = Char.make(97);
    Lit1 = Char.make(59);
    Lit0 = IntNum.make(20050502);
    $instance = new pregexp();
    pregexp pregexp1 = $instance;
    ModuleMethod moduleMethod2 = new ModuleMethod(pregexp1, 16, Lit117, 4097);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:47");
    pregexp$Mnreverse$Ex = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 17, Lit118, -4096);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:57");
    pregexp$Mnerror = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 18, Lit119, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:65");
    pregexp$Mnread$Mnpattern = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 19, Lit120, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:79");
    pregexp$Mnread$Mnbranch = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 20, Lit21, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:91");
    pregexp$Mnread$Mnpiece = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 21, Lit121, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:138");
    pregexp$Mnread$Mnescaped$Mnnumber = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 22, Lit122, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:155");
    pregexp$Mnread$Mnescaped$Mnchar = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 23, Lit45, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:174");
    pregexp$Mnread$Mnposix$Mnchar$Mnclass = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 24, Lit57, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:200");
    pregexp$Mnread$Mncluster$Mntype = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 25, Lit64, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:233");
    pregexp$Mnread$Mnsubpattern = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 26, Lit74, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:254");
    pregexp$Mnwrap$Mnquantifier$Mnif$Mnany = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 27, Lit76, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:300");
    pregexp$Mnread$Mnnums = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 28, Lit123, 4097);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:323");
    pregexp$Mninvert$Mnchar$Mnlist = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 29, Lit80, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:330");
    pregexp$Mnread$Mnchar$Mnlist = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 30, Lit124, 24582);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:368");
    pregexp$Mnstring$Mnmatch = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 31, Lit125, 4097);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:379");
    pregexp$Mnchar$Mnword$Qu = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 32, Lit126, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:387");
    pregexp$Mnat$Mnword$Mnboundary$Qu = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 33, Lit99, 8194);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:399");
    pregexp$Mncheck$Mnif$Mnin$Mnchar$Mnclass$Qu = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 34, Lit127, 8194);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:429");
    pregexp$Mnlist$Mnref = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 35, Lit128, 4097);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:448");
    pregexp$Mnmake$Mnbackref$Mnlist = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 36, null, 0);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:463");
    lambda$Fn1 = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 37, null, 0);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:551");
    lambda$Fn6 = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 38, null, 0);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:556");
    lambda$Fn7 = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 39, null, 0);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:564");
    lambda$Fn8 = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 40, null, 0);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:573");
    lambda$Fn9 = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 41, null, 0);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:578");
    lambda$Fn10 = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 42, Lit101, 24582);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:459");
    pregexp$Mnmatch$Mnpositions$Mnaux = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 43, Lit129, 16388);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:639");
    pregexp$Mnreplace$Mnaux = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 44, Lit130, 4097);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:665");
    pregexp = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 45, Lit114, -4094);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:670");
    pregexp$Mnmatch$Mnpositions = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 46, Lit131, -4094);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:690");
    pregexp$Mnmatch = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 47, Lit132, 8194);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:700");
    pregexp$Mnsplit = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 48, Lit133, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:723");
    pregexp$Mnreplace = moduleMethod2;
    moduleMethod2 = new ModuleMethod(pregexp1, 49, Lit134, 12291);
    moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:736");
    pregexp$Mnreplace$St = moduleMethod2;
    ModuleMethod moduleMethod1 = new ModuleMethod(pregexp1, 50, Lit135, 4097);
    moduleMethod1.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:764");
    pregexp$Mnquote = moduleMethod1;
    $instance.run();
  }
  
  public pregexp() {
    ModuleInfo.register(this);
  }
  
  public static Object isPregexpAtWordBoundary(Object paramObject1, Object paramObject2, Object paramObject3) {
    Object object = Scheme.numEqu.apply2(paramObject2, Lit73);
    try {
      boolean bool = ((Boolean)object).booleanValue();
      if (bool)
        return bool ? Boolean.TRUE : Boolean.FALSE; 
      paramObject3 = Scheme.numGEq.apply2(paramObject2, paramObject3);
      try {
        bool = ((Boolean)paramObject3).booleanValue();
        if (bool)
          return bool ? Boolean.TRUE : Boolean.FALSE; 
        try {
          paramObject3 = paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            i = strings.stringRef((CharSequence)paramObject3, i);
            try {
              paramObject3 = paramObject1;
              paramObject1 = AddOp.$Mn.apply2(paramObject2, Lit8);
              try {
                int j = ((Number)paramObject1).intValue();
                j = strings.stringRef((CharSequence)paramObject3, j);
                paramObject2 = isPregexpCheckIfInCharClass(Char.make(i), Lit41);
                paramObject3 = isPregexpCheckIfInCharClass(Char.make(j), Lit41);
                if (paramObject2 != Boolean.FALSE) {
                  if (paramObject3 != Boolean.FALSE) {
                    paramObject1 = Boolean.FALSE;
                  } else {
                    paramObject1 = Boolean.TRUE;
                  } 
                } else {
                  paramObject1 = paramObject2;
                } 
                if (paramObject1 != Boolean.FALSE)
                  return paramObject1; 
                try {
                  paramObject1 = Boolean.FALSE;
                  if (paramObject2 != paramObject1) {
                    i = 1;
                  } else {
                    i = 0;
                  } 
                  i = i + 1 & 0x1;
                  return (i != 0) ? paramObject3 : ((i != 0) ? Boolean.TRUE : Boolean.FALSE);
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException, "x", -2, paramObject2);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException1, "string-ref", 2, classCastException);
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
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "x", -2, paramObject3);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "x", -2, object);
    } 
  }
  
  public static boolean isPregexpCharWord(Object paramObject) {
    try {
      Char char_ = (Char)paramObject;
      boolean bool = unicode.isCharAlphabetic(char_);
      if (!bool)
        try {
          char_ = (Char)paramObject;
          boolean bool1 = unicode.isCharNumeric(char_);
          bool = bool1;
          if (!bool1)
            try {
              char_ = (Char)paramObject;
              return characters.isChar$Eq(char_, Lit84);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "char=?", 1, paramObject);
            }  
          return bool;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-numeric?", 1, paramObject);
        }  
      return bool;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "char-alphabetic?", 1, paramObject);
    } 
  }
  
  public static Object isPregexpCheckIfInCharClass(Object paramObject1, Object paramObject2) {
    throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static Object lambda1sub(Object paramObject) {
    if (lists.isPair(paramObject)) {
      Object object1 = lists.car.apply1(paramObject);
      Object object2 = lambda1sub(lists.cdr.apply1(paramObject));
      return (Scheme.isEqv.apply2(object1, Lit100) != Boolean.FALSE) ? lists.cons(lists.cons(paramObject, Boolean.FALSE), object2) : append.append$V(new Object[] { lambda1sub(object1), object2 });
    } 
    return LList.Empty;
  }
  
  public static Pair pregexp(Object paramObject) {
    $Stpregexp$Mnspace$Mnsensitive$Qu$St = Boolean.TRUE;
    SimpleSymbol simpleSymbol = Lit100;
    GenericProc genericProc = lists.car;
    IntNum intNum = Lit73;
    try {
      CharSequence charSequence = (CharSequence)paramObject;
      return LList.list2(simpleSymbol, genericProc.apply1(pregexpReadPattern(paramObject, intNum, Integer.valueOf(strings.stringLength(charSequence)))));
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "string-length", 1, paramObject);
    } 
  }
  
  public static Object pregexpError$V(Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    ports.display("Error:");
    while (true) {
      Object object;
      if (lList == LList.Empty) {
        ports.newline();
        return misc.error$V("pregexp-error", new Object[0]);
      } 
      try {
        Pair pair = (Pair)lList;
        object = pair.getCar();
        ports.display(Lit3);
        ports.write(object);
        object = pair.getCdr();
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, object);
      } 
    } 
  }
  
  public static Object pregexpInvertCharList(Object paramObject) {
    Object object = lists.car.apply1(paramObject);
    try {
      Pair pair = (Pair)object;
      lists.setCar$Ex(pair, Lit79);
      return paramObject;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "set-car!", 1, object);
    } 
  }
  
  public static Object pregexpListRef(Object paramObject1, Object paramObject2) {
    IntNum intNum = Lit73;
    Object object = paramObject1;
    for (paramObject1 = intNum;; paramObject1 = AddOp.$Pl.apply2(paramObject1, Lit8)) {
      if (lists.isNull(object))
        return Boolean.FALSE; 
      if (Scheme.numEqu.apply2(paramObject1, paramObject2) != Boolean.FALSE)
        return lists.car.apply1(object); 
      object = lists.cdr.apply1(object);
    } 
  }
  
  public static Object pregexpMakeBackrefList(Object paramObject) {
    return lambda1sub(paramObject);
  }
  
  public static Object pregexpMatch$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.apply.apply4(pregexp$Mnmatch$Mnpositions, paramObject1, paramObject2, lList);
    Object object = paramObject1;
    if (paramObject1 != Boolean.FALSE) {
      LList lList1 = LList.Empty;
      object = paramObject1;
      paramObject1 = lList1;
      while (true) {
        if (object == LList.Empty)
          return LList.reverseInPlace(paramObject1); 
        try {
          Pair pair2 = (Pair)object;
          Object object1 = pair2.getCdr();
          Object object2 = pair2.getCar();
          object = object2;
          if (object2 != Boolean.FALSE)
            try {
              object = paramObject2;
              Object object3 = lists.car.apply1(object2);
              try {
                int i = ((Number)object3).intValue();
                object2 = lists.cdr.apply1(object2);
                try {
                  int j = ((Number)object2).intValue();
                  object = strings.substring((CharSequence)object, i, j);
                  paramObject1 = Pair.make(object, paramObject1);
                  object = object1;
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException, "substring", 3, object2);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException, "substring", 2, object3);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "substring", 1, paramObject2);
            }  
          Pair pair1 = Pair.make(object, classCastException);
          object = object1;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "arg0", -2, object);
        } 
      } 
    } 
    return object;
  }
  
  public static Object pregexpMatchPositions$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    // Byte code:
    //   0: aload_2
    //   1: iconst_0
    //   2: invokestatic makeList : ([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   5: astore #5
    //   7: aload_0
    //   8: invokestatic isString : (Ljava/lang/Object;)Z
    //   11: ifeq -> 112
    //   14: aload_0
    //   15: invokestatic pregexp : (Ljava/lang/Object;)Lgnu/lists/Pair;
    //   18: astore_2
    //   19: aload_1
    //   20: checkcast java/lang/CharSequence
    //   23: astore_0
    //   24: aload_0
    //   25: invokestatic stringLength : (Ljava/lang/CharSequence;)I
    //   28: istore_3
    //   29: aload #5
    //   31: invokestatic isNull : (Ljava/lang/Object;)Z
    //   34: ifeq -> 150
    //   37: getstatic gnu/kawa/slib/pregexp.Lit73 : Lgnu/math/IntNum;
    //   40: astore_0
    //   41: aload #5
    //   43: invokestatic isNull : (Ljava/lang/Object;)Z
    //   46: ifeq -> 179
    //   49: iload_3
    //   50: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   53: astore #5
    //   55: aload_0
    //   56: astore #6
    //   58: getstatic kawa/standard/Scheme.numLEq : Lgnu/kawa/functions/NumberCompare;
    //   61: aload #6
    //   63: aload #5
    //   65: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   68: astore #7
    //   70: aload #7
    //   72: checkcast java/lang/Boolean
    //   75: invokevirtual booleanValue : ()Z
    //   78: istore #4
    //   80: iload #4
    //   82: ifeq -> 208
    //   85: aload_2
    //   86: aload_1
    //   87: iload_3
    //   88: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   91: aload_0
    //   92: aload #5
    //   94: aload #6
    //   96: invokestatic pregexpMatchPositionsAux : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   99: astore #7
    //   101: aload #7
    //   103: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   106: if_acmpeq -> 192
    //   109: aload #7
    //   111: areturn
    //   112: aload_0
    //   113: astore_2
    //   114: aload_0
    //   115: invokestatic isPair : (Ljava/lang/Object;)Z
    //   118: ifne -> 19
    //   121: iconst_3
    //   122: anewarray java/lang/Object
    //   125: dup
    //   126: iconst_0
    //   127: getstatic gnu/kawa/slib/pregexp.Lit114 : Lgnu/mapping/SimpleSymbol;
    //   130: aastore
    //   131: dup
    //   132: iconst_1
    //   133: getstatic gnu/kawa/slib/pregexp.Lit115 : Lgnu/mapping/SimpleSymbol;
    //   136: aastore
    //   137: dup
    //   138: iconst_2
    //   139: aload_0
    //   140: aastore
    //   141: invokestatic pregexpError$V : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   144: pop
    //   145: aload_0
    //   146: astore_2
    //   147: goto -> 19
    //   150: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   153: aload #5
    //   155: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   158: astore_0
    //   159: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   162: aload #5
    //   164: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   167: astore #6
    //   169: aload #6
    //   171: checkcast gnu/lists/LList
    //   174: astore #5
    //   176: goto -> 41
    //   179: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   182: aload #5
    //   184: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   187: astore #5
    //   189: goto -> 55
    //   192: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   195: aload #6
    //   197: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   200: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   203: astore #6
    //   205: goto -> 58
    //   208: iload #4
    //   210: ifeq -> 217
    //   213: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   216: areturn
    //   217: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   220: areturn
    //   221: astore_0
    //   222: new gnu/mapping/WrongType
    //   225: dup
    //   226: aload_0
    //   227: ldc_w 'string-length'
    //   230: iconst_1
    //   231: aload_1
    //   232: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   235: athrow
    //   236: astore_0
    //   237: new gnu/mapping/WrongType
    //   240: dup
    //   241: aload_0
    //   242: ldc_w 'opt-args'
    //   245: bipush #-2
    //   247: aload #6
    //   249: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   252: athrow
    //   253: astore_0
    //   254: new gnu/mapping/WrongType
    //   257: dup
    //   258: aload_0
    //   259: ldc_w 'x'
    //   262: bipush #-2
    //   264: aload #7
    //   266: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   269: athrow
    // Exception table:
    //   from	to	target	type
    //   19	24	221	java/lang/ClassCastException
    //   70	80	253	java/lang/ClassCastException
    //   169	176	236	java/lang/ClassCastException
  }
  
  public static Object pregexpMatchPositionsAux(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6) {
    frame frame = new frame();
    frame.s = paramObject2;
    frame.sn = paramObject3;
    frame.start = paramObject4;
    frame.n = paramObject5;
    paramObject2 = frame.identity;
    paramObject3 = pregexpMakeBackrefList(paramObject1);
    frame.case$Mnsensitive$Qu = Boolean.TRUE;
    frame.backrefs = paramObject3;
    frame.identity = (Procedure)paramObject2;
    frame.lambda3sub(paramObject1, paramObject6, frame.identity, lambda$Fn1);
    paramObject1 = frame.backrefs;
    paramObject2 = LList.Empty;
    while (true) {
      if (paramObject1 == LList.Empty) {
        paramObject1 = LList.reverseInPlace(paramObject2);
        paramObject2 = lists.car.apply1(paramObject1);
        if (paramObject2 != Boolean.FALSE)
          return paramObject1; 
      } else {
        try {
          paramObject3 = paramObject1;
          paramObject1 = paramObject3.getCdr();
          paramObject2 = Pair.make(lists.cdr.apply1(paramObject3.getCar()), paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "arg0", -2, paramObject1);
        } 
        continue;
      } 
      return classCastException;
    } 
  }
  
  public static Object pregexpQuote(Object paramObject) {
    try {
      CharSequence charSequence = (CharSequence)paramObject;
      Integer integer = Integer.valueOf(strings.stringLength(charSequence) - 1);
      LList lList = LList.Empty;
      while (true) {
        if (Scheme.numLss.apply2(integer, Lit73) != Boolean.FALSE)
          try {
            paramObject = lList;
            return strings.list$To$String((LList)paramObject);
          } catch (ClassCastException null) {
            throw new WrongType(classCastException, "list->string", 1, lList);
          }  
        Object object = AddOp.$Mn.apply2(integer, Lit8);
        try {
          Object object1;
          CharSequence charSequence1 = (CharSequence)classCastException;
          try {
            Pair pair;
            int i = integer.intValue();
            i = strings.stringRef(charSequence1, i);
            if (lists.memv(Char.make(i), Lit116) != Boolean.FALSE) {
              pair = lists.cons(Lit19, lists.cons(Char.make(i), lList));
            } else {
              pair = lists.cons(Char.make(i), pair);
            } 
            object1 = object;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, object1);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "string-ref", 1, classCastException);
        } 
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "string-length", 1, classCastException);
    } 
  }
  
  public static Object pregexpReadBranch(Object paramObject1, Object paramObject2, Object paramObject3) {
    LList lList = LList.Empty;
    Object object = paramObject2;
    paramObject2 = lList;
    while (true) {
      if (Scheme.numGEq.apply2(object, paramObject3) != Boolean.FALSE)
        return LList.list2(lists.cons(Lit5, pregexpReverse$Ex(paramObject2)), object); 
      try {
        CharSequence charSequence = (CharSequence)paramObject1;
        try {
          int i = ((Number)object).intValue();
          i = strings.stringRef(charSequence, i);
          boolean bool = characters.isChar$Eq(Char.make(i), Lit7);
          if (bool ? bool : characters.isChar$Eq(Char.make(i), Lit6))
            return LList.list2(lists.cons(Lit5, pregexpReverse$Ex(paramObject2)), object); 
          object = pregexpReadPiece(paramObject1, object, paramObject3);
          paramObject2 = lists.cons(lists.car.apply1(object), paramObject2);
          object = lists.cadr.apply1(object);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 2, object);
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "string-ref", 1, classCastException);
      } 
    } 
  }
  
  public static Object pregexpReadCharList(Object paramObject1, Object paramObject2, Object paramObject3) {
    // Byte code:
    //   0: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   3: astore #7
    //   5: aload_1
    //   6: astore #6
    //   8: aload #7
    //   10: astore_1
    //   11: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
    //   14: aload #6
    //   16: aload_2
    //   17: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   20: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   23: if_acmpeq -> 46
    //   26: iconst_2
    //   27: anewarray java/lang/Object
    //   30: dup
    //   31: iconst_0
    //   32: getstatic gnu/kawa/slib/pregexp.Lit80 : Lgnu/mapping/SimpleSymbol;
    //   35: aastore
    //   36: dup
    //   37: iconst_1
    //   38: getstatic gnu/kawa/slib/pregexp.Lit81 : Lgnu/mapping/SimpleSymbol;
    //   41: aastore
    //   42: invokestatic pregexpError$V : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   45: areturn
    //   46: aload_0
    //   47: checkcast java/lang/CharSequence
    //   50: astore #7
    //   52: aload #6
    //   54: checkcast java/lang/Number
    //   57: invokevirtual intValue : ()I
    //   60: istore_3
    //   61: aload #7
    //   63: iload_3
    //   64: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   67: istore_3
    //   68: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   71: iload_3
    //   72: invokestatic make : (I)Lgnu/text/Char;
    //   75: getstatic gnu/kawa/slib/pregexp.Lit46 : Lgnu/text/Char;
    //   78: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   81: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   84: if_acmpeq -> 144
    //   87: aload_1
    //   88: invokestatic isNull : (Ljava/lang/Object;)Z
    //   91: ifeq -> 119
    //   94: iload_3
    //   95: invokestatic make : (I)Lgnu/text/Char;
    //   98: aload_1
    //   99: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   102: astore_1
    //   103: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   106: aload #6
    //   108: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   111: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: astore #6
    //   116: goto -> 11
    //   119: getstatic gnu/kawa/slib/pregexp.Lit82 : Lgnu/mapping/SimpleSymbol;
    //   122: aload_1
    //   123: invokestatic pregexpReverse$Ex : (Ljava/lang/Object;)Ljava/lang/Object;
    //   126: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   129: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   132: aload #6
    //   134: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   137: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   140: invokestatic list2 : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   143: areturn
    //   144: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   147: iload_3
    //   148: invokestatic make : (I)Lgnu/text/Char;
    //   151: getstatic gnu/kawa/slib/pregexp.Lit19 : Lgnu/text/Char;
    //   154: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   157: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   160: if_acmpeq -> 226
    //   163: aload_0
    //   164: aload #6
    //   166: aload_2
    //   167: invokestatic pregexpReadEscapedChar : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   170: astore #6
    //   172: aload #6
    //   174: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   177: if_acmpeq -> 206
    //   180: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   183: aload #6
    //   185: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   188: aload_1
    //   189: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   192: astore_1
    //   193: getstatic kawa/lib/lists.cadr : Lgnu/expr/GenericProc;
    //   196: aload #6
    //   198: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   201: astore #6
    //   203: goto -> 11
    //   206: iconst_2
    //   207: anewarray java/lang/Object
    //   210: dup
    //   211: iconst_0
    //   212: getstatic gnu/kawa/slib/pregexp.Lit80 : Lgnu/mapping/SimpleSymbol;
    //   215: aastore
    //   216: dup
    //   217: iconst_1
    //   218: getstatic gnu/kawa/slib/pregexp.Lit22 : Lgnu/mapping/SimpleSymbol;
    //   221: aastore
    //   222: invokestatic pregexpError$V : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   225: areturn
    //   226: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   229: iload_3
    //   230: invokestatic make : (I)Lgnu/text/Char;
    //   233: getstatic gnu/kawa/slib/pregexp.Lit58 : Lgnu/text/Char;
    //   236: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   239: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   242: if_acmpeq -> 486
    //   245: aload_1
    //   246: invokestatic isNull : (Ljava/lang/Object;)Z
    //   249: istore #5
    //   251: iload #5
    //   253: ifeq -> 286
    //   256: iload #5
    //   258: ifeq -> 360
    //   261: iload_3
    //   262: invokestatic make : (I)Lgnu/text/Char;
    //   265: aload_1
    //   266: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   269: astore_1
    //   270: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   273: aload #6
    //   275: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   278: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   281: astore #6
    //   283: goto -> 11
    //   286: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   289: aload #6
    //   291: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   294: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   297: astore #7
    //   299: getstatic kawa/standard/Scheme.numLss : Lgnu/kawa/functions/NumberCompare;
    //   302: aload #7
    //   304: aload_2
    //   305: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   308: astore #8
    //   310: aload #8
    //   312: checkcast java/lang/Boolean
    //   315: invokevirtual booleanValue : ()Z
    //   318: istore #5
    //   320: iload #5
    //   322: ifeq -> 453
    //   325: aload_0
    //   326: checkcast java/lang/CharSequence
    //   329: astore #8
    //   331: aload #7
    //   333: checkcast java/lang/Number
    //   336: invokevirtual intValue : ()I
    //   339: istore #4
    //   341: aload #8
    //   343: iload #4
    //   345: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   348: invokestatic make : (I)Lgnu/text/Char;
    //   351: getstatic gnu/kawa/slib/pregexp.Lit46 : Lgnu/text/Char;
    //   354: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
    //   357: ifne -> 261
    //   360: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   363: aload_1
    //   364: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   367: astore #8
    //   369: aload #8
    //   371: invokestatic isChar : (Ljava/lang/Object;)Z
    //   374: ifeq -> 461
    //   377: getstatic gnu/kawa/slib/pregexp.Lit83 : Lgnu/mapping/SimpleSymbol;
    //   380: astore #9
    //   382: aload_0
    //   383: checkcast java/lang/CharSequence
    //   386: astore #10
    //   388: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   391: aload #6
    //   393: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   396: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   399: astore #7
    //   401: aload #7
    //   403: checkcast java/lang/Number
    //   406: invokevirtual intValue : ()I
    //   409: istore_3
    //   410: aload #9
    //   412: aload #8
    //   414: aload #10
    //   416: iload_3
    //   417: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   420: invokestatic make : (I)Lgnu/text/Char;
    //   423: invokestatic list3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   426: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   429: aload_1
    //   430: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   433: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   436: astore_1
    //   437: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   440: aload #6
    //   442: getstatic gnu/kawa/slib/pregexp.Lit16 : Lgnu/math/IntNum;
    //   445: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   448: astore #6
    //   450: goto -> 11
    //   453: iload #5
    //   455: ifeq -> 360
    //   458: goto -> 261
    //   461: iload_3
    //   462: invokestatic make : (I)Lgnu/text/Char;
    //   465: aload_1
    //   466: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   469: astore_1
    //   470: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   473: aload #6
    //   475: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   478: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   481: astore #6
    //   483: goto -> 11
    //   486: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   489: iload_3
    //   490: invokestatic make : (I)Lgnu/text/Char;
    //   493: getstatic gnu/kawa/slib/pregexp.Lit15 : Lgnu/text/Char;
    //   496: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   499: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   502: if_acmpeq -> 622
    //   505: aload_0
    //   506: checkcast java/lang/CharSequence
    //   509: astore #8
    //   511: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   514: aload #6
    //   516: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   519: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   522: astore #7
    //   524: aload #7
    //   526: checkcast java/lang/Number
    //   529: invokevirtual intValue : ()I
    //   532: istore #4
    //   534: aload #8
    //   536: iload #4
    //   538: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   541: invokestatic make : (I)Lgnu/text/Char;
    //   544: getstatic gnu/kawa/slib/pregexp.Lit44 : Lgnu/text/Char;
    //   547: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
    //   550: ifeq -> 597
    //   553: aload_0
    //   554: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   557: aload #6
    //   559: getstatic gnu/kawa/slib/pregexp.Lit16 : Lgnu/math/IntNum;
    //   562: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   565: aload_2
    //   566: invokestatic pregexpReadPosixCharClass : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   569: astore #6
    //   571: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   574: aload #6
    //   576: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   579: aload_1
    //   580: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   583: astore_1
    //   584: getstatic kawa/lib/lists.cadr : Lgnu/expr/GenericProc;
    //   587: aload #6
    //   589: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   592: astore #6
    //   594: goto -> 11
    //   597: iload_3
    //   598: invokestatic make : (I)Lgnu/text/Char;
    //   601: aload_1
    //   602: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   605: astore_1
    //   606: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   609: aload #6
    //   611: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   614: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   617: astore #6
    //   619: goto -> 11
    //   622: iload_3
    //   623: invokestatic make : (I)Lgnu/text/Char;
    //   626: aload_1
    //   627: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   630: astore_1
    //   631: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   634: aload #6
    //   636: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   639: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   642: astore #6
    //   644: goto -> 11
    //   647: astore_1
    //   648: new gnu/mapping/WrongType
    //   651: dup
    //   652: aload_1
    //   653: ldc_w 'string-ref'
    //   656: iconst_1
    //   657: aload_0
    //   658: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   661: athrow
    //   662: astore_0
    //   663: new gnu/mapping/WrongType
    //   666: dup
    //   667: aload_0
    //   668: ldc_w 'string-ref'
    //   671: iconst_2
    //   672: aload #6
    //   674: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   677: athrow
    //   678: astore_0
    //   679: new gnu/mapping/WrongType
    //   682: dup
    //   683: aload_0
    //   684: ldc_w 'x'
    //   687: bipush #-2
    //   689: aload #8
    //   691: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   694: athrow
    //   695: astore_1
    //   696: new gnu/mapping/WrongType
    //   699: dup
    //   700: aload_1
    //   701: ldc_w 'string-ref'
    //   704: iconst_1
    //   705: aload_0
    //   706: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   709: athrow
    //   710: astore_0
    //   711: new gnu/mapping/WrongType
    //   714: dup
    //   715: aload_0
    //   716: ldc_w 'string-ref'
    //   719: iconst_2
    //   720: aload #7
    //   722: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   725: athrow
    //   726: astore_1
    //   727: new gnu/mapping/WrongType
    //   730: dup
    //   731: aload_1
    //   732: ldc_w 'string-ref'
    //   735: iconst_1
    //   736: aload_0
    //   737: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   740: athrow
    //   741: astore_0
    //   742: new gnu/mapping/WrongType
    //   745: dup
    //   746: aload_0
    //   747: ldc_w 'string-ref'
    //   750: iconst_2
    //   751: aload #7
    //   753: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   756: athrow
    //   757: astore_1
    //   758: new gnu/mapping/WrongType
    //   761: dup
    //   762: aload_1
    //   763: ldc_w 'string-ref'
    //   766: iconst_1
    //   767: aload_0
    //   768: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   771: athrow
    //   772: astore_0
    //   773: new gnu/mapping/WrongType
    //   776: dup
    //   777: aload_0
    //   778: ldc_w 'string-ref'
    //   781: iconst_2
    //   782: aload #7
    //   784: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   787: athrow
    // Exception table:
    //   from	to	target	type
    //   46	52	647	java/lang/ClassCastException
    //   52	61	662	java/lang/ClassCastException
    //   310	320	678	java/lang/ClassCastException
    //   325	331	695	java/lang/ClassCastException
    //   331	341	710	java/lang/ClassCastException
    //   382	388	726	java/lang/ClassCastException
    //   401	410	741	java/lang/ClassCastException
    //   505	511	757	java/lang/ClassCastException
    //   524	534	772	java/lang/ClassCastException
  }
  
  public static Object pregexpReadClusterType(Object paramObject1, Object paramObject2, Object paramObject3) {
    try {
      paramObject3 = paramObject1;
      try {
        int i = ((Number)paramObject2).intValue();
        i = strings.stringRef((CharSequence)paramObject3, i);
        if (Scheme.isEqv.apply2(Char.make(i), Lit47) != Boolean.FALSE) {
          paramObject3 = AddOp.$Pl.apply2(paramObject2, Lit8);
          try {
            paramObject2 = paramObject1;
            try {
              i = ((Number)paramObject3).intValue();
              i = strings.stringRef((CharSequence)paramObject2, i);
              if (Scheme.isEqv.apply2(Char.make(i), Lit44) != Boolean.FALSE)
                return LList.list2(LList.Empty, AddOp.$Pl.apply2(paramObject3, Lit8)); 
              if (Scheme.isEqv.apply2(Char.make(i), Lit48) != Boolean.FALSE)
                return LList.list2(Lit49, AddOp.$Pl.apply2(paramObject3, Lit8)); 
              if (Scheme.isEqv.apply2(Char.make(i), Lit50) != Boolean.FALSE)
                return LList.list2(Lit51, AddOp.$Pl.apply2(paramObject3, Lit8)); 
              if (Scheme.isEqv.apply2(Char.make(i), Lit52) != Boolean.FALSE)
                return LList.list2(Lit53, AddOp.$Pl.apply2(paramObject3, Lit8)); 
              if (Scheme.isEqv.apply2(Char.make(i), Lit54) != Boolean.FALSE)
                try {
                  paramObject2 = paramObject1;
                  paramObject1 = AddOp.$Pl.apply2(paramObject3, Lit8);
                  try {
                    i = ((Number)paramObject1).intValue();
                    i = strings.stringRef((CharSequence)paramObject2, i);
                    if (Scheme.isEqv.apply2(Char.make(i), Lit48) != Boolean.FALSE) {
                      paramObject1 = Lit55;
                      return LList.list2(paramObject1, AddOp.$Pl.apply2(paramObject3, Lit16));
                    } 
                    if (Scheme.isEqv.apply2(Char.make(i), Lit50) != Boolean.FALSE) {
                      paramObject1 = Lit56;
                      return LList.list2(paramObject1, AddOp.$Pl.apply2(paramObject3, Lit16));
                    } 
                    paramObject1 = pregexpError$V(new Object[] { Lit57 });
                    return LList.list2(paramObject1, AddOp.$Pl.apply2(paramObject3, Lit16));
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException1, "string-ref", 2, paramObject1);
                  } 
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException1, "string-ref", 1, paramObject1);
                }  
              LList lList = LList.Empty;
              paramObject2 = Boolean.FALSE;
              try {
                while (true) {
                  CharSequence charSequence = (CharSequence)paramObject1;
                  try {
                    Pair pair;
                    i = ((Number)paramObject3).intValue();
                    i = strings.stringRef(charSequence, i);
                    if (Scheme.isEqv.apply2(Char.make(i), Lit58) != Boolean.FALSE) {
                      paramObject3 = AddOp.$Pl.apply2(paramObject3, Lit8);
                      paramObject2 = Boolean.TRUE;
                      continue;
                    } 
                    if (Scheme.isEqv.apply2(Char.make(i), Lit59) != Boolean.FALSE) {
                      paramObject3 = AddOp.$Pl.apply2(paramObject3, Lit8);
                      if (paramObject2 != Boolean.FALSE) {
                        paramObject2 = Lit60;
                      } else {
                        paramObject2 = Lit61;
                      } 
                      pair = lists.cons(paramObject2, lList);
                      paramObject2 = Boolean.FALSE;
                      continue;
                    } 
                    if (Scheme.isEqv.apply2(Char.make(i), Lit62) != Boolean.FALSE) {
                      $Stpregexp$Mnspace$Mnsensitive$Qu$St = paramObject2;
                      paramObject3 = AddOp.$Pl.apply2(paramObject3, Lit8);
                      paramObject2 = Boolean.FALSE;
                      continue;
                    } 
                    return (Scheme.isEqv.apply2(Char.make(i), Lit44) != Boolean.FALSE) ? LList.list2(pair, AddOp.$Pl.apply2(paramObject3, Lit8)) : pregexpError$V(new Object[] { Lit57 });
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException, "string-ref", 2, paramObject3);
                  } 
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException1, "string-ref", 1, classCastException);
              } 
            } catch (ClassCastException null) {
              throw new WrongType(classCastException, "string-ref", 2, paramObject3);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-ref", 1, classCastException);
          } 
        } 
        return LList.list2(Lit63, classCastException1);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-ref", 2, classCastException1);
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "string-ref", 1, classCastException);
    } 
  }
  
  public static Object pregexpReadEscapedChar(Object paramObject1, Object paramObject2, Object paramObject3) {
    paramObject3 = Scheme.numLss.apply2(AddOp.$Pl.apply2(paramObject2, Lit8), paramObject3);
    try {
      boolean bool = ((Boolean)paramObject3).booleanValue();
      if (bool)
        try {
          paramObject3 = paramObject1;
          paramObject1 = AddOp.$Pl.apply2(paramObject2, Lit8);
          try {
            int i = ((Number)paramObject1).intValue();
            i = strings.stringRef((CharSequence)paramObject3, i);
            return (Scheme.isEqv.apply2(Char.make(i), Lit25) != Boolean.FALSE) ? LList.list2(Lit26, AddOp.$Pl.apply2(paramObject2, Lit16)) : ((Scheme.isEqv.apply2(Char.make(i), Lit27) != Boolean.FALSE) ? LList.list2(Lit28, AddOp.$Pl.apply2(paramObject2, Lit16)) : ((Scheme.isEqv.apply2(Char.make(i), Lit29) != Boolean.FALSE) ? LList.list2(Lit30, AddOp.$Pl.apply2(paramObject2, Lit16)) : ((Scheme.isEqv.apply2(Char.make(i), Lit31) != Boolean.FALSE) ? LList.list2(Lit32, AddOp.$Pl.apply2(paramObject2, Lit16)) : ((Scheme.isEqv.apply2(Char.make(i), Lit33) != Boolean.FALSE) ? LList.list2(Lit24, AddOp.$Pl.apply2(paramObject2, Lit16)) : ((Scheme.isEqv.apply2(Char.make(i), Lit34) != Boolean.FALSE) ? LList.list2($Stpregexp$Mnreturn$Mnchar$St, AddOp.$Pl.apply2(paramObject2, Lit16)) : ((Scheme.isEqv.apply2(Char.make(i), Lit35) != Boolean.FALSE) ? LList.list2(Lit36, AddOp.$Pl.apply2(paramObject2, Lit16)) : ((Scheme.isEqv.apply2(Char.make(i), Lit37) != Boolean.FALSE) ? LList.list2(Lit38, AddOp.$Pl.apply2(paramObject2, Lit16)) : ((Scheme.isEqv.apply2(Char.make(i), Lit39) != Boolean.FALSE) ? LList.list2($Stpregexp$Mntab$Mnchar$St, AddOp.$Pl.apply2(paramObject2, Lit16)) : ((Scheme.isEqv.apply2(Char.make(i), Lit40) != Boolean.FALSE) ? LList.list2(Lit41, AddOp.$Pl.apply2(paramObject2, Lit16)) : ((Scheme.isEqv.apply2(Char.make(i), Lit42) != Boolean.FALSE) ? LList.list2(Lit43, AddOp.$Pl.apply2(paramObject2, Lit16)) : LList.list2(Char.make(i), AddOp.$Pl.apply2(paramObject2, Lit16))))))))))));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, paramObject1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 1, paramObject1);
        }  
      return bool ? Boolean.TRUE : Boolean.FALSE;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "x", -2, paramObject3);
    } 
  }
  
  public static Object pregexpReadEscapedNumber(Object paramObject1, Object paramObject2, Object paramObject3) {
    Object object = Scheme.numLss.apply2(AddOp.$Pl.apply2(paramObject2, Lit8), paramObject3);
    try {
      boolean bool = ((Boolean)object).booleanValue();
      if (bool)
        try {
          CharSequence charSequence = (CharSequence)paramObject1;
          object = AddOp.$Pl.apply2(paramObject2, Lit8);
          try {
            int i = ((Number)object).intValue();
            i = strings.stringRef(charSequence, i);
            bool = unicode.isCharNumeric(Char.make(i));
            if (bool) {
              paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit16);
              object = LList.list1(Char.make(i));
              while (true) {
                if (Scheme.numGEq.apply2(paramObject2, paramObject3) != Boolean.FALSE) {
                  paramObject1 = pregexpReverse$Ex(object);
                  try {
                    paramObject3 = paramObject1;
                    return LList.list2(numbers.string$To$Number(strings.list$To$String((LList)paramObject3)), paramObject2);
                  } catch (ClassCastException classCastException1) {
                    throw new WrongType(classCastException1, "list->string", 1, paramObject1);
                  } 
                } 
                try {
                  charSequence = (CharSequence)paramObject1;
                  try {
                    i = ((Number)classCastException1).intValue();
                    i = strings.stringRef(charSequence, i);
                    if (unicode.isCharNumeric(Char.make(i))) {
                      object1 = AddOp.$Pl.apply2(classCastException1, Lit8);
                      object = lists.cons(Char.make(i), object);
                      continue;
                    } 
                    paramObject1 = pregexpReverse$Ex(object);
                    try {
                      paramObject3 = paramObject1;
                      return LList.list2(numbers.string$To$Number(strings.list$To$String((LList)paramObject3)), object1);
                    } catch (ClassCastException object1) {
                      throw new WrongType(object1, "list->string", 1, paramObject1);
                    } 
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException, "string-ref", 2, object1);
                  } 
                } catch (ClassCastException classCastException2) {
                  throw new WrongType(classCastException2, "string-ref", 1, classCastException);
                } 
              } 
            } 
            return bool ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, object);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "string-ref", 1, classCastException);
        }  
      return bool ? Boolean.TRUE : Boolean.FALSE;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "x", -2, object);
    } 
  }
  
  public static Object pregexpReadNums(Object paramObject1, Object paramObject2, Object paramObject3) {
    LList lList2 = LList.Empty;
    LList lList1 = LList.Empty;
    IntNum intNum = Lit8;
    while (true) {
      if (Scheme.numGEq.apply2(paramObject2, paramObject3) != Boolean.FALSE)
        pregexpError$V(new Object[] { Lit76 }); 
      try {
        CharSequence charSequence = (CharSequence)paramObject1;
        try {
          Pair pair1;
          Pair pair2;
          int i = ((Number)paramObject2).intValue();
          i = strings.stringRef(charSequence, i);
          if (unicode.isCharNumeric(Char.make(i))) {
            if (Scheme.numEqu.apply2(intNum, Lit8) != Boolean.FALSE) {
              pair2 = lists.cons(Char.make(i), lList2);
              paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit8);
              intNum = Lit8;
              continue;
            } 
            pair1 = lists.cons(Char.make(i), lList1);
            paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit8);
            intNum = Lit16;
            continue;
          } 
          boolean bool = unicode.isCharWhitespace(Char.make(i));
          if (bool ? ($Stpregexp$Mnspace$Mnsensitive$Qu$St == Boolean.FALSE) : bool) {
            paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit8);
            continue;
          } 
          bool = characters.isChar$Eq(Char.make(i), Lit77);
          if (bool ? (Scheme.numEqu.apply2(intNum, Lit8) != Boolean.FALSE) : bool) {
            paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit8);
            intNum = Lit16;
            continue;
          } 
          if (characters.isChar$Eq(Char.make(i), Lit78)) {
            paramObject1 = pregexpReverse$Ex(pair2);
            try {
              paramObject3 = paramObject1;
              paramObject1 = numbers.string$To$Number(strings.list$To$String((LList)paramObject3));
              paramObject3 = pregexpReverse$Ex(pair1);
              try {
                LList lList = (LList)paramObject3;
                paramObject3 = numbers.string$To$Number(strings.list$To$String(lList));
                try {
                  Boolean bool1 = Boolean.FALSE;
                  if (paramObject1 != bool1) {
                    i = 1;
                  } else {
                    i = 0;
                  } 
                  i = i + 1 & 0x1;
                  return ((i != 0) ? (Scheme.numEqu.apply2(intNum, Lit8) != Boolean.FALSE) : (i != 0)) ? LList.list3(Lit73, Boolean.FALSE, paramObject2) : ((Scheme.numEqu.apply2(intNum, Lit8) != Boolean.FALSE) ? LList.list3(paramObject1, paramObject1, paramObject2) : LList.list3(paramObject1, paramObject3, paramObject2));
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException1, "x", -2, paramObject1);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException, "list->string", 1, paramObject3);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "list->string", 1, classCastException);
            } 
          } 
          return Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 2, classCastException1);
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "string-ref", 1, classCastException);
      } 
    } 
  }
  
  public static Object pregexpReadPattern(Object paramObject1, Object paramObject2, Object paramObject3) {
    // Byte code:
    //   0: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   8: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   11: if_acmpeq -> 31
    //   14: getstatic gnu/kawa/slib/pregexp.Lit4 : Lgnu/mapping/SimpleSymbol;
    //   17: getstatic gnu/kawa/slib/pregexp.Lit5 : Lgnu/mapping/SimpleSymbol;
    //   20: invokestatic list1 : (Ljava/lang/Object;)Lgnu/lists/Pair;
    //   23: invokestatic list2 : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   26: aload_1
    //   27: invokestatic list2 : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   30: areturn
    //   31: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   34: astore #5
    //   36: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
    //   39: aload_1
    //   40: aload_2
    //   41: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   44: astore #6
    //   46: aload #6
    //   48: checkcast java/lang/Boolean
    //   51: invokevirtual booleanValue : ()Z
    //   54: istore #4
    //   56: iload #4
    //   58: ifeq -> 82
    //   61: iload #4
    //   63: ifeq -> 114
    //   66: getstatic gnu/kawa/slib/pregexp.Lit4 : Lgnu/mapping/SimpleSymbol;
    //   69: aload #5
    //   71: invokestatic pregexpReverse$Ex : (Ljava/lang/Object;)Ljava/lang/Object;
    //   74: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   77: aload_1
    //   78: invokestatic list2 : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   81: areturn
    //   82: aload_0
    //   83: checkcast java/lang/CharSequence
    //   86: astore #6
    //   88: aload_1
    //   89: checkcast java/lang/Number
    //   92: invokevirtual intValue : ()I
    //   95: istore_3
    //   96: aload #6
    //   98: iload_3
    //   99: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   102: invokestatic make : (I)Lgnu/text/Char;
    //   105: getstatic gnu/kawa/slib/pregexp.Lit6 : Lgnu/text/Char;
    //   108: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
    //   111: ifne -> 66
    //   114: aload_0
    //   115: checkcast java/lang/CharSequence
    //   118: astore #7
    //   120: aload_1
    //   121: checkcast java/lang/Number
    //   124: invokevirtual intValue : ()I
    //   127: istore_3
    //   128: aload_1
    //   129: astore #6
    //   131: aload #7
    //   133: iload_3
    //   134: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   137: invokestatic make : (I)Lgnu/text/Char;
    //   140: getstatic gnu/kawa/slib/pregexp.Lit7 : Lgnu/text/Char;
    //   143: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
    //   146: ifeq -> 161
    //   149: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   152: aload_1
    //   153: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   156: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   159: astore #6
    //   161: aload_0
    //   162: aload #6
    //   164: aload_2
    //   165: invokestatic pregexpReadBranch : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   168: astore_1
    //   169: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   172: aload_1
    //   173: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   176: aload #5
    //   178: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   181: astore #5
    //   183: getstatic kawa/lib/lists.cadr : Lgnu/expr/GenericProc;
    //   186: aload_1
    //   187: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   190: astore_1
    //   191: goto -> 36
    //   194: astore_0
    //   195: new gnu/mapping/WrongType
    //   198: dup
    //   199: aload_0
    //   200: ldc_w 'x'
    //   203: bipush #-2
    //   205: aload #6
    //   207: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   210: athrow
    //   211: astore_1
    //   212: new gnu/mapping/WrongType
    //   215: dup
    //   216: aload_1
    //   217: ldc_w 'string-ref'
    //   220: iconst_1
    //   221: aload_0
    //   222: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   225: athrow
    //   226: astore_0
    //   227: new gnu/mapping/WrongType
    //   230: dup
    //   231: aload_0
    //   232: ldc_w 'string-ref'
    //   235: iconst_2
    //   236: aload_1
    //   237: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   240: athrow
    //   241: astore_1
    //   242: new gnu/mapping/WrongType
    //   245: dup
    //   246: aload_1
    //   247: ldc_w 'string-ref'
    //   250: iconst_1
    //   251: aload_0
    //   252: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   255: athrow
    //   256: astore_0
    //   257: new gnu/mapping/WrongType
    //   260: dup
    //   261: aload_0
    //   262: ldc_w 'string-ref'
    //   265: iconst_2
    //   266: aload_1
    //   267: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   270: athrow
    // Exception table:
    //   from	to	target	type
    //   46	56	194	java/lang/ClassCastException
    //   82	88	211	java/lang/ClassCastException
    //   88	96	226	java/lang/ClassCastException
    //   114	120	241	java/lang/ClassCastException
    //   120	128	256	java/lang/ClassCastException
  }
  
  public static Object pregexpReadPiece(Object paramObject1, Object paramObject2, Object paramObject3) {
    throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static Object pregexpReadPosixCharClass(Object paramObject1, Object paramObject2, Object paramObject3) {
    // Byte code:
    //   0: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   3: astore #5
    //   5: getstatic gnu/kawa/slib/pregexp.Lit44 : Lgnu/text/Char;
    //   8: invokestatic list1 : (Ljava/lang/Object;)Lgnu/lists/Pair;
    //   11: astore #6
    //   13: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
    //   16: aload_1
    //   17: aload_2
    //   18: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   21: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   24: if_acmpeq -> 41
    //   27: iconst_1
    //   28: anewarray java/lang/Object
    //   31: dup
    //   32: iconst_0
    //   33: getstatic gnu/kawa/slib/pregexp.Lit45 : Lgnu/mapping/SimpleSymbol;
    //   36: aastore
    //   37: invokestatic pregexpError$V : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   40: areturn
    //   41: aload_0
    //   42: checkcast java/lang/CharSequence
    //   45: astore #7
    //   47: aload_1
    //   48: checkcast java/lang/Number
    //   51: invokevirtual intValue : ()I
    //   54: istore_3
    //   55: aload #7
    //   57: iload_3
    //   58: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   61: istore_3
    //   62: iload_3
    //   63: invokestatic make : (I)Lgnu/text/Char;
    //   66: getstatic gnu/kawa/slib/pregexp.Lit9 : Lgnu/text/Char;
    //   69: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
    //   72: ifeq -> 94
    //   75: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   78: astore #5
    //   80: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   83: aload_1
    //   84: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   87: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   90: astore_1
    //   91: goto -> 13
    //   94: iload_3
    //   95: invokestatic make : (I)Lgnu/text/Char;
    //   98: invokestatic isCharAlphabetic : (Lgnu/text/Char;)Z
    //   101: ifeq -> 129
    //   104: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   107: aload_1
    //   108: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   111: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: astore_1
    //   115: iload_3
    //   116: invokestatic make : (I)Lgnu/text/Char;
    //   119: aload #6
    //   121: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   124: astore #6
    //   126: goto -> 13
    //   129: iload_3
    //   130: invokestatic make : (I)Lgnu/text/Char;
    //   133: getstatic gnu/kawa/slib/pregexp.Lit44 : Lgnu/text/Char;
    //   136: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
    //   139: ifeq -> 286
    //   142: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
    //   145: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   148: aload_1
    //   149: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   152: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   155: aload_2
    //   156: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   159: astore_2
    //   160: aload_2
    //   161: checkcast java/lang/Boolean
    //   164: invokevirtual booleanValue : ()Z
    //   167: istore #4
    //   169: iload #4
    //   171: ifeq -> 193
    //   174: iload #4
    //   176: ifeq -> 234
    //   179: iconst_1
    //   180: anewarray java/lang/Object
    //   183: dup
    //   184: iconst_0
    //   185: getstatic gnu/kawa/slib/pregexp.Lit45 : Lgnu/mapping/SimpleSymbol;
    //   188: aastore
    //   189: invokestatic pregexpError$V : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   192: areturn
    //   193: aload_0
    //   194: checkcast java/lang/CharSequence
    //   197: astore_2
    //   198: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   201: aload_1
    //   202: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   205: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   208: astore_0
    //   209: aload_0
    //   210: checkcast java/lang/Number
    //   213: invokevirtual intValue : ()I
    //   216: istore_3
    //   217: aload_2
    //   218: iload_3
    //   219: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   222: invokestatic make : (I)Lgnu/text/Char;
    //   225: getstatic gnu/kawa/slib/pregexp.Lit46 : Lgnu/text/Char;
    //   228: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
    //   231: ifeq -> 179
    //   234: aload #6
    //   236: invokestatic pregexpReverse$Ex : (Ljava/lang/Object;)Ljava/lang/Object;
    //   239: astore_0
    //   240: aload_0
    //   241: checkcast gnu/lists/LList
    //   244: astore_2
    //   245: aload_2
    //   246: invokestatic list$To$String : (Lgnu/lists/LList;)Ljava/lang/CharSequence;
    //   249: invokestatic string$To$Symbol : (Ljava/lang/CharSequence;)Lgnu/mapping/SimpleSymbol;
    //   252: astore_2
    //   253: aload_2
    //   254: astore_0
    //   255: aload #5
    //   257: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   260: if_acmpeq -> 271
    //   263: getstatic gnu/kawa/slib/pregexp.Lit17 : Lgnu/mapping/SimpleSymbol;
    //   266: aload_2
    //   267: invokestatic list2 : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   270: astore_0
    //   271: aload_0
    //   272: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   275: aload_1
    //   276: getstatic gnu/kawa/slib/pregexp.Lit16 : Lgnu/math/IntNum;
    //   279: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   282: invokestatic list2 : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   285: areturn
    //   286: iconst_1
    //   287: anewarray java/lang/Object
    //   290: dup
    //   291: iconst_0
    //   292: getstatic gnu/kawa/slib/pregexp.Lit45 : Lgnu/mapping/SimpleSymbol;
    //   295: aastore
    //   296: invokestatic pregexpError$V : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   299: areturn
    //   300: astore_1
    //   301: new gnu/mapping/WrongType
    //   304: dup
    //   305: aload_1
    //   306: ldc_w 'string-ref'
    //   309: iconst_1
    //   310: aload_0
    //   311: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   314: athrow
    //   315: astore_0
    //   316: new gnu/mapping/WrongType
    //   319: dup
    //   320: aload_0
    //   321: ldc_w 'string-ref'
    //   324: iconst_2
    //   325: aload_1
    //   326: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   329: athrow
    //   330: astore_0
    //   331: new gnu/mapping/WrongType
    //   334: dup
    //   335: aload_0
    //   336: ldc_w 'x'
    //   339: bipush #-2
    //   341: aload_2
    //   342: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   345: athrow
    //   346: astore_1
    //   347: new gnu/mapping/WrongType
    //   350: dup
    //   351: aload_1
    //   352: ldc_w 'string-ref'
    //   355: iconst_1
    //   356: aload_0
    //   357: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   360: athrow
    //   361: astore_1
    //   362: new gnu/mapping/WrongType
    //   365: dup
    //   366: aload_1
    //   367: ldc_w 'string-ref'
    //   370: iconst_2
    //   371: aload_0
    //   372: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   375: athrow
    //   376: astore_1
    //   377: new gnu/mapping/WrongType
    //   380: dup
    //   381: aload_1
    //   382: ldc_w 'list->string'
    //   385: iconst_1
    //   386: aload_0
    //   387: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   390: athrow
    // Exception table:
    //   from	to	target	type
    //   41	47	300	java/lang/ClassCastException
    //   47	55	315	java/lang/ClassCastException
    //   160	169	330	java/lang/ClassCastException
    //   193	198	346	java/lang/ClassCastException
    //   209	217	361	java/lang/ClassCastException
    //   240	245	376	java/lang/ClassCastException
  }
  
  public static Object pregexpReadSubpattern(Object paramObject1, Object paramObject2, Object paramObject3) {
    Object object2 = $Stpregexp$Mnspace$Mnsensitive$Qu$St;
    paramObject2 = pregexpReadClusterType(paramObject1, paramObject2, paramObject3);
    Object object1 = lists.car.apply1(paramObject2);
    Object object3 = pregexpReadPattern(paramObject1, lists.cadr.apply1(paramObject2), paramObject3);
    $Stpregexp$Mnspace$Mnsensitive$Qu$St = object2;
    paramObject2 = lists.car.apply1(object3);
    object2 = lists.cadr.apply1(object3);
    paramObject3 = Scheme.numLss.apply2(object2, paramObject3);
    try {
      boolean bool = ((Boolean)paramObject3).booleanValue();
      if (bool)
        try {
          paramObject3 = paramObject1;
          try {
            int i = ((Number)object2).intValue();
            if (characters.isChar$Eq(Char.make(strings.stringRef((CharSequence)paramObject3, i)), Lit6)) {
              paramObject1 = object1;
            } else {
              return pregexpError$V(new Object[] { Lit64 });
            } 
            while (true) {
              if (lists.isNull(paramObject1))
                return LList.list2(paramObject2, AddOp.$Pl.apply2(object2, Lit8)); 
              paramObject3 = lists.cdr.apply1(paramObject1);
              paramObject2 = LList.list2(lists.car.apply1(paramObject1), paramObject2);
              paramObject1 = paramObject3;
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-ref", 2, object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 1, classCastException1);
        }  
      paramObject1 = object1;
      if (!bool)
        return pregexpError$V(new Object[] { Lit64 }); 
      while (true) {
        if (lists.isNull(paramObject1))
          return LList.list2(classCastException, AddOp.$Pl.apply2(object2, Lit8)); 
        paramObject3 = lists.cdr.apply1(paramObject1);
        Pair pair = LList.list2(lists.car.apply1(paramObject1), classCastException);
        paramObject1 = paramObject3;
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "x", -2, paramObject3);
    } 
  }
  
  public static Object pregexpReplace(Object paramObject1, Object paramObject2, Object paramObject3) {
    try {
      CharSequence charSequence = (CharSequence)paramObject2;
      int i = strings.stringLength(charSequence);
      Object object = pregexpMatchPositions$V(paramObject1, paramObject2, new Object[] { Lit73, Integer.valueOf(i) });
      if (object == Boolean.FALSE)
        return paramObject2; 
      try {
        paramObject1 = paramObject3;
        int j = strings.stringLength((CharSequence)paramObject1);
        Object object1 = lists.caar.apply1(object);
        paramObject1 = lists.cdar.apply1(object);
        try {
          CharSequence charSequence1 = (CharSequence)paramObject2;
          try {
            int k = ((Number)object1).intValue();
            object1 = strings.substring(charSequence1, 0, k);
            paramObject3 = pregexpReplaceAux(paramObject2, paramObject3, Integer.valueOf(j), object);
            try {
              object = paramObject2;
              try {
                j = ((Number)paramObject1).intValue();
                return strings.stringAppend(new Object[] { object1, paramObject3, strings.substring((CharSequence)object, j, i) });
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "substring", 2, paramObject1);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "substring", 1, classCastException);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "substring", 3, object1);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "substring", 1, classCastException);
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "string-length", 1, paramObject3);
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "string-length", 1, classCastException);
    } 
  }
  
  public static Object pregexpReplace$St(Object paramObject1, Object paramObject2, Object paramObject3) {
    Object object = paramObject1;
    if (strings.isString(paramObject1))
      object = pregexp(paramObject1); 
    try {
      paramObject1 = paramObject2;
      int i = strings.stringLength((CharSequence)paramObject1);
      try {
        paramObject1 = paramObject3;
        int j = strings.stringLength((CharSequence)paramObject1);
        paramObject1 = Lit73;
        String str = "";
        while (true) {
          if (Scheme.numGEq.apply2(paramObject1, Integer.valueOf(i)) != Boolean.FALSE)
            return str; 
          Object object2 = pregexpMatchPositions$V(object, paramObject2, new Object[] { paramObject1, Integer.valueOf(i) });
          if (object2 == Boolean.FALSE) {
            if (Scheme.numEqu.apply2(paramObject1, Lit73) == Boolean.FALSE)
              try {
                paramObject3 = paramObject2;
                try {
                  j = ((Number)paramObject1).intValue();
                  paramObject2 = strings.stringAppend(new Object[] { str, strings.substring((CharSequence)paramObject3, j, i) });
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException, "substring", 2, paramObject1);
                } 
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "substring", 1, classCastException);
              }  
            return classCastException;
          } 
          Object object1 = lists.cdar.apply1(object2);
          try {
            Object object3;
            CharSequence charSequence = (CharSequence)classCastException;
            try {
              int k = ((Number)classCastException1).intValue();
              object3 = lists.caar.apply1(object2);
              try {
                int m = ((Number)object3).intValue();
                FString fString = strings.stringAppend(new Object[] { str, strings.substring(charSequence, k, m), pregexpReplaceAux(classCastException, paramObject3, Integer.valueOf(j), object2) });
                object3 = object1;
              } catch (ClassCastException classCastException2) {
                throw new WrongType(classCastException2, "substring", 3, object3);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "substring", 2, object3);
            } 
          } catch (ClassCastException classCastException2) {
            throw new WrongType(classCastException2, "substring", 1, classCastException);
          } 
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "string-length", 1, paramObject3);
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "string-length", 1, classCastException);
    } 
  }
  
  public static Object pregexpReplaceAux(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    // Byte code:
    //   0: getstatic gnu/kawa/slib/pregexp.Lit73 : Lgnu/math/IntNum;
    //   3: astore #7
    //   5: ldc_w ''
    //   8: astore #6
    //   10: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
    //   13: aload #7
    //   15: aload_2
    //   16: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   19: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   22: if_acmpeq -> 28
    //   25: aload #6
    //   27: areturn
    //   28: aload_1
    //   29: checkcast java/lang/CharSequence
    //   32: astore #8
    //   34: aload #7
    //   36: checkcast java/lang/Number
    //   39: invokevirtual intValue : ()I
    //   42: istore #4
    //   44: aload #8
    //   46: iload #4
    //   48: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   51: istore #4
    //   53: iload #4
    //   55: invokestatic make : (I)Lgnu/text/Char;
    //   58: getstatic gnu/kawa/slib/pregexp.Lit19 : Lgnu/text/Char;
    //   61: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
    //   64: ifeq -> 413
    //   67: aload_1
    //   68: aload #7
    //   70: aload_2
    //   71: invokestatic pregexpReadEscapedNumber : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   74: astore #9
    //   76: aload #9
    //   78: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   81: if_acmpeq -> 175
    //   84: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   87: aload #9
    //   89: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   92: astore #8
    //   94: aload #9
    //   96: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   99: if_acmpeq -> 239
    //   102: getstatic kawa/lib/lists.cadr : Lgnu/expr/GenericProc;
    //   105: aload #9
    //   107: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   110: astore #7
    //   112: aload #8
    //   114: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   117: if_acmpne -> 314
    //   120: aload_1
    //   121: checkcast java/lang/CharSequence
    //   124: astore #8
    //   126: aload #7
    //   128: checkcast java/lang/Number
    //   131: invokevirtual intValue : ()I
    //   134: istore #4
    //   136: aload #8
    //   138: iload #4
    //   140: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   143: istore #4
    //   145: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   148: aload #7
    //   150: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   153: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   156: astore #7
    //   158: iload #4
    //   160: invokestatic make : (I)Lgnu/text/Char;
    //   163: getstatic gnu/kawa/slib/pregexp.Lit11 : Lgnu/text/Char;
    //   166: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
    //   169: ifeq -> 279
    //   172: goto -> 10
    //   175: aload_1
    //   176: checkcast java/lang/CharSequence
    //   179: astore #10
    //   181: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   184: aload #7
    //   186: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   189: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   192: astore #8
    //   194: aload #8
    //   196: checkcast java/lang/Number
    //   199: invokevirtual intValue : ()I
    //   202: istore #4
    //   204: aload #10
    //   206: iload #4
    //   208: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   211: invokestatic make : (I)Lgnu/text/Char;
    //   214: getstatic gnu/kawa/slib/pregexp.Lit113 : Lgnu/text/Char;
    //   217: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
    //   220: ifeq -> 231
    //   223: getstatic gnu/kawa/slib/pregexp.Lit73 : Lgnu/math/IntNum;
    //   226: astore #8
    //   228: goto -> 94
    //   231: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   234: astore #8
    //   236: goto -> 94
    //   239: aload #8
    //   241: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   244: if_acmpeq -> 263
    //   247: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   250: aload #7
    //   252: getstatic gnu/kawa/slib/pregexp.Lit16 : Lgnu/math/IntNum;
    //   255: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   258: astore #7
    //   260: goto -> 112
    //   263: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   266: aload #7
    //   268: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   271: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   274: astore #7
    //   276: goto -> 112
    //   279: iconst_2
    //   280: anewarray java/lang/Object
    //   283: dup
    //   284: iconst_0
    //   285: aload #6
    //   287: aastore
    //   288: dup
    //   289: iconst_1
    //   290: iconst_1
    //   291: anewarray java/lang/Object
    //   294: dup
    //   295: iconst_0
    //   296: iload #4
    //   298: invokestatic make : (I)Lgnu/text/Char;
    //   301: aastore
    //   302: invokestatic $make$string$ : ([Ljava/lang/Object;)Ljava/lang/CharSequence;
    //   305: aastore
    //   306: invokestatic stringAppend : ([Ljava/lang/Object;)Lgnu/lists/FString;
    //   309: astore #6
    //   311: goto -> 172
    //   314: aload_3
    //   315: aload #8
    //   317: invokestatic pregexpListRef : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   320: astore #9
    //   322: aload #6
    //   324: astore #8
    //   326: aload #9
    //   328: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   331: if_acmpeq -> 406
    //   334: aload_0
    //   335: checkcast java/lang/CharSequence
    //   338: astore #8
    //   340: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   343: aload #9
    //   345: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   348: astore #10
    //   350: aload #10
    //   352: checkcast java/lang/Number
    //   355: invokevirtual intValue : ()I
    //   358: istore #4
    //   360: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   363: aload #9
    //   365: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   368: astore #9
    //   370: aload #9
    //   372: checkcast java/lang/Number
    //   375: invokevirtual intValue : ()I
    //   378: istore #5
    //   380: iconst_2
    //   381: anewarray java/lang/Object
    //   384: dup
    //   385: iconst_0
    //   386: aload #6
    //   388: aastore
    //   389: dup
    //   390: iconst_1
    //   391: aload #8
    //   393: iload #4
    //   395: iload #5
    //   397: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
    //   400: aastore
    //   401: invokestatic stringAppend : ([Ljava/lang/Object;)Lgnu/lists/FString;
    //   404: astore #8
    //   406: aload #8
    //   408: astore #6
    //   410: goto -> 10
    //   413: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   416: aload #7
    //   418: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   421: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   424: astore #7
    //   426: iconst_2
    //   427: anewarray java/lang/Object
    //   430: dup
    //   431: iconst_0
    //   432: aload #6
    //   434: aastore
    //   435: dup
    //   436: iconst_1
    //   437: iconst_1
    //   438: anewarray java/lang/Object
    //   441: dup
    //   442: iconst_0
    //   443: iload #4
    //   445: invokestatic make : (I)Lgnu/text/Char;
    //   448: aastore
    //   449: invokestatic $make$string$ : ([Ljava/lang/Object;)Ljava/lang/CharSequence;
    //   452: aastore
    //   453: invokestatic stringAppend : ([Ljava/lang/Object;)Lgnu/lists/FString;
    //   456: astore #6
    //   458: goto -> 10
    //   461: astore_0
    //   462: new gnu/mapping/WrongType
    //   465: dup
    //   466: aload_0
    //   467: ldc_w 'string-ref'
    //   470: iconst_1
    //   471: aload_1
    //   472: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   475: athrow
    //   476: astore_0
    //   477: new gnu/mapping/WrongType
    //   480: dup
    //   481: aload_0
    //   482: ldc_w 'string-ref'
    //   485: iconst_2
    //   486: aload #7
    //   488: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   491: athrow
    //   492: astore_0
    //   493: new gnu/mapping/WrongType
    //   496: dup
    //   497: aload_0
    //   498: ldc_w 'string-ref'
    //   501: iconst_1
    //   502: aload_1
    //   503: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   506: athrow
    //   507: astore_0
    //   508: new gnu/mapping/WrongType
    //   511: dup
    //   512: aload_0
    //   513: ldc_w 'string-ref'
    //   516: iconst_2
    //   517: aload #8
    //   519: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   522: athrow
    //   523: astore_0
    //   524: new gnu/mapping/WrongType
    //   527: dup
    //   528: aload_0
    //   529: ldc_w 'string-ref'
    //   532: iconst_1
    //   533: aload_1
    //   534: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   537: athrow
    //   538: astore_0
    //   539: new gnu/mapping/WrongType
    //   542: dup
    //   543: aload_0
    //   544: ldc_w 'string-ref'
    //   547: iconst_2
    //   548: aload #7
    //   550: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   553: athrow
    //   554: astore_1
    //   555: new gnu/mapping/WrongType
    //   558: dup
    //   559: aload_1
    //   560: ldc_w 'substring'
    //   563: iconst_1
    //   564: aload_0
    //   565: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   568: athrow
    //   569: astore_0
    //   570: new gnu/mapping/WrongType
    //   573: dup
    //   574: aload_0
    //   575: ldc_w 'substring'
    //   578: iconst_2
    //   579: aload #10
    //   581: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   584: athrow
    //   585: astore_0
    //   586: new gnu/mapping/WrongType
    //   589: dup
    //   590: aload_0
    //   591: ldc_w 'substring'
    //   594: iconst_3
    //   595: aload #9
    //   597: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   600: athrow
    // Exception table:
    //   from	to	target	type
    //   28	34	461	java/lang/ClassCastException
    //   34	44	476	java/lang/ClassCastException
    //   120	126	523	java/lang/ClassCastException
    //   126	136	538	java/lang/ClassCastException
    //   175	181	492	java/lang/ClassCastException
    //   194	204	507	java/lang/ClassCastException
    //   334	340	554	java/lang/ClassCastException
    //   350	360	569	java/lang/ClassCastException
    //   370	380	585	java/lang/ClassCastException
  }
  
  public static Object pregexpReverse$Ex(Object paramObject) {
    LList lList = LList.Empty;
    while (true) {
      if (lists.isNull(paramObject))
        return lList; 
      Object object = lists.cdr.apply1(paramObject);
      try {
        Pair pair = (Pair)paramObject;
        lists.setCdr$Ex(pair, lList);
        Object object1 = paramObject;
        paramObject = object;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "set-cdr!", 1, paramObject);
      } 
    } 
  }
  
  public static Object pregexpSplit(Object paramObject1, Object paramObject2) {
    try {
      CharSequence charSequence = (CharSequence)paramObject2;
      int i = strings.stringLength(charSequence);
      IntNum intNum = Lit73;
      LList lList = LList.Empty;
      Boolean bool = Boolean.FALSE;
      while (true) {
        Object object1;
        Pair pair;
        if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
          return pregexpReverse$Ex(lList); 
        Object object2 = pregexpMatchPositions$V(paramObject1, paramObject2, new Object[] { intNum, Integer.valueOf(i) });
        if (object2 != Boolean.FALSE) {
          Object object3;
          object2 = lists.car.apply1(object2);
          Object object4 = lists.car.apply1(object2);
          object2 = lists.cdr.apply1(object2);
          if (Scheme.numEqu.apply2(object4, object2) != Boolean.FALSE) {
            object3 = AddOp.$Pl.apply2(object2, Lit8);
            try {
              object2 = paramObject2;
              try {
                int j = ((Number)intNum).intValue();
                object1 = AddOp.$Pl.apply2(object4, Lit8);
                try {
                  int k = ((Number)object1).intValue();
                  pair = lists.cons(strings.substring((CharSequence)object2, j, k), lList);
                  object2 = Boolean.TRUE;
                  object1 = object3;
                  object3 = object2;
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "substring", 3, object1);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "substring", 2, object1);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "substring", 1, paramObject2);
            } 
          } 
          Object object5 = Scheme.numEqu.apply2(object4, object1);
          try {
            boolean bool1 = ((Boolean)object5).booleanValue();
            if (bool1 ? (object3 != Boolean.FALSE) : bool1) {
              object3 = Boolean.FALSE;
              object1 = object2;
              continue;
            } 
            try {
              object3 = paramObject2;
              try {
                int j = ((Number)object1).intValue();
                try {
                  int k = ((Number)object4).intValue();
                  pair = lists.cons(strings.substring((CharSequence)object3, j, k), pair);
                  object3 = Boolean.FALSE;
                  object1 = object2;
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "substring", 3, object4);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "substring", 2, object1);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "substring", 1, paramObject2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "x", -2, object5);
          } 
        } 
        try {
          CharSequence charSequence1 = (CharSequence)paramObject2;
          try {
            int j = ((Number)object1).intValue();
            pair = lists.cons(strings.substring(charSequence1, j, i), pair);
            Boolean bool1 = Boolean.FALSE;
            object1 = Integer.valueOf(i);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "substring", 2, object1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "substring", 1, paramObject2);
        } 
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "string-length", 1, paramObject2);
    } 
  }
  
  public static Object pregexpStringMatch(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6) {
    try {
      CharSequence charSequence = (CharSequence)paramObject1;
      int i = strings.stringLength(charSequence);
      if (Scheme.numGrt.apply2(Integer.valueOf(i), paramObject4) != Boolean.FALSE)
        return Scheme.applyToArgs.apply1(paramObject6); 
      IntNum intNum = Lit73;
      while (true) {
        if (Scheme.numGEq.apply2(intNum, Integer.valueOf(i)) != Boolean.FALSE)
          return Scheme.applyToArgs.apply2(paramObject5, paramObject3); 
        if (Scheme.numGEq.apply2(paramObject3, paramObject4) != Boolean.FALSE)
          return Scheme.applyToArgs.apply1(paramObject6); 
        try {
          Object object;
          CharSequence charSequence1 = (CharSequence)paramObject1;
          try {
            int j = ((Number)intNum).intValue();
            Char char_ = Char.make(strings.stringRef(charSequence1, j));
            try {
              CharSequence charSequence2 = (CharSequence)paramObject2;
              try {
                j = ((Number)paramObject3).intValue();
                if (characters.isChar$Eq(char_, Char.make(strings.stringRef(charSequence2, j)))) {
                  object = AddOp.$Pl.apply2(intNum, Lit8);
                  paramObject3 = AddOp.$Pl.apply2(paramObject3, Lit8);
                  continue;
                } 
                return Scheme.applyToArgs.apply1(paramObject6);
              } catch (ClassCastException null) {
                throw new WrongType(classCastException, "string-ref", 2, paramObject3);
              } 
            } catch (ClassCastException null) {
              throw new WrongType(classCastException, "string-ref", 1, paramObject2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, object);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "string-ref", 1, classCastException);
        } 
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "string-length", 1, classCastException);
    } 
  }
  
  public static Object pregexpWrapQuantifierIfAny(Object paramObject1, Object paramObject2, Object paramObject3) {
    // Byte code:
    //   0: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   3: aload_0
    //   4: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   7: astore #6
    //   9: getstatic kawa/lib/lists.cadr : Lgnu/expr/GenericProc;
    //   12: aload_0
    //   13: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   16: astore #5
    //   18: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
    //   21: aload #5
    //   23: aload_2
    //   24: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   27: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   30: if_acmpeq -> 35
    //   33: aload_0
    //   34: areturn
    //   35: aload_1
    //   36: checkcast java/lang/CharSequence
    //   39: astore #7
    //   41: aload #5
    //   43: checkcast java/lang/Number
    //   46: invokevirtual intValue : ()I
    //   49: istore_3
    //   50: aload #7
    //   52: iload_3
    //   53: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   56: istore_3
    //   57: iload_3
    //   58: invokestatic make : (I)Lgnu/text/Char;
    //   61: invokestatic isCharWhitespace : (Lgnu/text/Char;)Z
    //   64: istore #4
    //   66: iload #4
    //   68: ifeq -> 96
    //   71: getstatic gnu/kawa/slib/pregexp.$Stpregexp$Mnspace$Mnsensitive$Qu$St : Ljava/lang/Object;
    //   74: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   77: if_acmpne -> 101
    //   80: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   83: aload #5
    //   85: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   88: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   91: astore #5
    //   93: goto -> 18
    //   96: iload #4
    //   98: ifne -> 80
    //   101: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   104: iload_3
    //   105: invokestatic make : (I)Lgnu/text/Char;
    //   108: getstatic gnu/kawa/slib/pregexp.Lit65 : Lgnu/text/Char;
    //   111: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: astore #7
    //   116: aload #7
    //   118: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   121: if_acmpeq -> 303
    //   124: aload #7
    //   126: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   129: if_acmpeq -> 33
    //   132: getstatic gnu/kawa/slib/pregexp.Lit68 : Lgnu/mapping/SimpleSymbol;
    //   135: invokestatic list1 : (Ljava/lang/Object;)Lgnu/lists/Pair;
    //   138: astore #7
    //   140: aload #7
    //   142: getstatic gnu/kawa/slib/pregexp.Lit69 : Lgnu/mapping/SimpleSymbol;
    //   145: getstatic gnu/kawa/slib/pregexp.Lit70 : Lgnu/mapping/SimpleSymbol;
    //   148: getstatic gnu/kawa/slib/pregexp.Lit71 : Lgnu/mapping/SimpleSymbol;
    //   151: aload #6
    //   153: invokestatic chain4 : (Lgnu/lists/Pair;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   156: pop
    //   157: aload #7
    //   159: getstatic gnu/kawa/slib/pregexp.Lit72 : Lgnu/mapping/SimpleSymbol;
    //   162: invokestatic list2 : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   165: astore #6
    //   167: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   170: iload_3
    //   171: invokestatic make : (I)Lgnu/text/Char;
    //   174: getstatic gnu/kawa/slib/pregexp.Lit65 : Lgnu/text/Char;
    //   177: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   180: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   183: if_acmpeq -> 393
    //   186: getstatic kawa/lib/lists.cddr : Lgnu/expr/GenericProc;
    //   189: aload #7
    //   191: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   194: astore_0
    //   195: aload_0
    //   196: checkcast gnu/lists/Pair
    //   199: astore #8
    //   201: aload #8
    //   203: getstatic gnu/kawa/slib/pregexp.Lit73 : Lgnu/math/IntNum;
    //   206: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   209: getstatic kawa/lib/lists.cdddr : Lgnu/expr/GenericProc;
    //   212: aload #7
    //   214: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   217: astore_0
    //   218: aload_0
    //   219: checkcast gnu/lists/Pair
    //   222: astore #8
    //   224: aload #8
    //   226: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   229: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   232: aload #5
    //   234: astore_0
    //   235: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   238: aload_0
    //   239: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   242: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   245: astore_0
    //   246: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
    //   249: aload_0
    //   250: aload_2
    //   251: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   254: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   257: if_acmpeq -> 670
    //   260: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   263: aload #7
    //   265: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   268: astore_1
    //   269: aload_1
    //   270: checkcast gnu/lists/Pair
    //   273: astore_2
    //   274: aload_2
    //   275: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   278: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   281: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   284: aload #6
    //   286: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   289: astore_1
    //   290: aload_1
    //   291: checkcast gnu/lists/Pair
    //   294: astore_2
    //   295: aload_2
    //   296: aload_0
    //   297: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   300: aload #6
    //   302: areturn
    //   303: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   306: iload_3
    //   307: invokestatic make : (I)Lgnu/text/Char;
    //   310: getstatic gnu/kawa/slib/pregexp.Lit66 : Lgnu/text/Char;
    //   313: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   316: astore #7
    //   318: aload #7
    //   320: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   323: if_acmpeq -> 337
    //   326: aload #7
    //   328: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   331: if_acmpeq -> 33
    //   334: goto -> 132
    //   337: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   340: iload_3
    //   341: invokestatic make : (I)Lgnu/text/Char;
    //   344: getstatic gnu/kawa/slib/pregexp.Lit47 : Lgnu/text/Char;
    //   347: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   350: astore #7
    //   352: aload #7
    //   354: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   357: if_acmpeq -> 371
    //   360: aload #7
    //   362: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   365: if_acmpeq -> 33
    //   368: goto -> 132
    //   371: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   374: iload_3
    //   375: invokestatic make : (I)Lgnu/text/Char;
    //   378: getstatic gnu/kawa/slib/pregexp.Lit67 : Lgnu/text/Char;
    //   381: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   384: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   387: if_acmpeq -> 33
    //   390: goto -> 132
    //   393: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   396: iload_3
    //   397: invokestatic make : (I)Lgnu/text/Char;
    //   400: getstatic gnu/kawa/slib/pregexp.Lit66 : Lgnu/text/Char;
    //   403: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   406: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   409: if_acmpeq -> 464
    //   412: getstatic kawa/lib/lists.cddr : Lgnu/expr/GenericProc;
    //   415: aload #7
    //   417: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   420: astore_0
    //   421: aload_0
    //   422: checkcast gnu/lists/Pair
    //   425: astore #8
    //   427: aload #8
    //   429: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   432: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   435: getstatic kawa/lib/lists.cdddr : Lgnu/expr/GenericProc;
    //   438: aload #7
    //   440: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   443: astore_0
    //   444: aload_0
    //   445: checkcast gnu/lists/Pair
    //   448: astore #8
    //   450: aload #8
    //   452: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   455: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   458: aload #5
    //   460: astore_0
    //   461: goto -> 235
    //   464: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   467: iload_3
    //   468: invokestatic make : (I)Lgnu/text/Char;
    //   471: getstatic gnu/kawa/slib/pregexp.Lit47 : Lgnu/text/Char;
    //   474: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   477: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   480: if_acmpeq -> 535
    //   483: getstatic kawa/lib/lists.cddr : Lgnu/expr/GenericProc;
    //   486: aload #7
    //   488: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   491: astore_0
    //   492: aload_0
    //   493: checkcast gnu/lists/Pair
    //   496: astore #8
    //   498: aload #8
    //   500: getstatic gnu/kawa/slib/pregexp.Lit73 : Lgnu/math/IntNum;
    //   503: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   506: getstatic kawa/lib/lists.cdddr : Lgnu/expr/GenericProc;
    //   509: aload #7
    //   511: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   514: astore_0
    //   515: aload_0
    //   516: checkcast gnu/lists/Pair
    //   519: astore #8
    //   521: aload #8
    //   523: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   526: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   529: aload #5
    //   531: astore_0
    //   532: goto -> 235
    //   535: aload #5
    //   537: astore_0
    //   538: getstatic kawa/standard/Scheme.isEqv : Lgnu/kawa/functions/IsEqv;
    //   541: iload_3
    //   542: invokestatic make : (I)Lgnu/text/Char;
    //   545: getstatic gnu/kawa/slib/pregexp.Lit67 : Lgnu/text/Char;
    //   548: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   551: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   554: if_acmpeq -> 235
    //   557: aload_1
    //   558: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   561: aload #5
    //   563: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   566: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   569: aload_2
    //   570: invokestatic pregexpReadNums : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   573: astore_0
    //   574: aload_0
    //   575: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   578: if_acmpne -> 601
    //   581: iconst_2
    //   582: anewarray java/lang/Object
    //   585: dup
    //   586: iconst_0
    //   587: getstatic gnu/kawa/slib/pregexp.Lit74 : Lgnu/mapping/SimpleSymbol;
    //   590: aastore
    //   591: dup
    //   592: iconst_1
    //   593: getstatic gnu/kawa/slib/pregexp.Lit75 : Lgnu/mapping/SimpleSymbol;
    //   596: aastore
    //   597: invokestatic pregexpError$V : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   600: pop
    //   601: getstatic kawa/lib/lists.cddr : Lgnu/expr/GenericProc;
    //   604: aload #7
    //   606: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   609: astore #5
    //   611: aload #5
    //   613: checkcast gnu/lists/Pair
    //   616: astore #8
    //   618: aload #8
    //   620: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   623: aload_0
    //   624: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   627: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   630: getstatic kawa/lib/lists.cdddr : Lgnu/expr/GenericProc;
    //   633: aload #7
    //   635: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   638: astore #5
    //   640: aload #5
    //   642: checkcast gnu/lists/Pair
    //   645: astore #8
    //   647: aload #8
    //   649: getstatic kawa/lib/lists.cadr : Lgnu/expr/GenericProc;
    //   652: aload_0
    //   653: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   656: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   659: getstatic kawa/lib/lists.caddr : Lgnu/expr/GenericProc;
    //   662: aload_0
    //   663: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   666: astore_0
    //   667: goto -> 235
    //   670: aload_1
    //   671: checkcast java/lang/CharSequence
    //   674: astore #5
    //   676: aload_0
    //   677: checkcast java/lang/Number
    //   680: invokevirtual intValue : ()I
    //   683: istore_3
    //   684: aload #5
    //   686: iload_3
    //   687: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   690: istore_3
    //   691: iload_3
    //   692: invokestatic make : (I)Lgnu/text/Char;
    //   695: invokestatic isCharWhitespace : (Lgnu/text/Char;)Z
    //   698: istore #4
    //   700: iload #4
    //   702: ifeq -> 728
    //   705: getstatic gnu/kawa/slib/pregexp.$Stpregexp$Mnspace$Mnsensitive$Qu$St : Ljava/lang/Object;
    //   708: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   711: if_acmpne -> 733
    //   714: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   717: aload_0
    //   718: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   721: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   724: astore_0
    //   725: goto -> 246
    //   728: iload #4
    //   730: ifne -> 714
    //   733: iload_3
    //   734: invokestatic make : (I)Lgnu/text/Char;
    //   737: getstatic gnu/kawa/slib/pregexp.Lit47 : Lgnu/text/Char;
    //   740: invokestatic isChar$Eq : (Lgnu/text/Char;Lgnu/text/Char;)Z
    //   743: ifeq -> 798
    //   746: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   749: aload #7
    //   751: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   754: astore_1
    //   755: aload_1
    //   756: checkcast gnu/lists/Pair
    //   759: astore_2
    //   760: aload_2
    //   761: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   764: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   767: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   770: aload #6
    //   772: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   775: astore_1
    //   776: aload_1
    //   777: checkcast gnu/lists/Pair
    //   780: astore_2
    //   781: aload_2
    //   782: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   785: aload_0
    //   786: getstatic gnu/kawa/slib/pregexp.Lit8 : Lgnu/math/IntNum;
    //   789: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   792: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   795: goto -> 300
    //   798: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   801: aload #7
    //   803: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   806: astore_1
    //   807: aload_1
    //   808: checkcast gnu/lists/Pair
    //   811: astore_2
    //   812: aload_2
    //   813: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   816: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   819: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   822: aload #6
    //   824: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   827: astore_1
    //   828: aload_1
    //   829: checkcast gnu/lists/Pair
    //   832: astore_2
    //   833: aload_2
    //   834: aload_0
    //   835: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   838: goto -> 300
    //   841: astore_0
    //   842: new gnu/mapping/WrongType
    //   845: dup
    //   846: aload_0
    //   847: ldc_w 'string-ref'
    //   850: iconst_1
    //   851: aload_1
    //   852: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   855: athrow
    //   856: astore_0
    //   857: new gnu/mapping/WrongType
    //   860: dup
    //   861: aload_0
    //   862: ldc_w 'string-ref'
    //   865: iconst_2
    //   866: aload #5
    //   868: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   871: athrow
    //   872: astore_1
    //   873: new gnu/mapping/WrongType
    //   876: dup
    //   877: aload_1
    //   878: ldc_w 'set-car!'
    //   881: iconst_1
    //   882: aload_0
    //   883: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   886: athrow
    //   887: astore_1
    //   888: new gnu/mapping/WrongType
    //   891: dup
    //   892: aload_1
    //   893: ldc_w 'set-car!'
    //   896: iconst_1
    //   897: aload_0
    //   898: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   901: athrow
    //   902: astore_1
    //   903: new gnu/mapping/WrongType
    //   906: dup
    //   907: aload_1
    //   908: ldc_w 'set-car!'
    //   911: iconst_1
    //   912: aload_0
    //   913: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   916: athrow
    //   917: astore_1
    //   918: new gnu/mapping/WrongType
    //   921: dup
    //   922: aload_1
    //   923: ldc_w 'set-car!'
    //   926: iconst_1
    //   927: aload_0
    //   928: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   931: athrow
    //   932: astore_1
    //   933: new gnu/mapping/WrongType
    //   936: dup
    //   937: aload_1
    //   938: ldc_w 'set-car!'
    //   941: iconst_1
    //   942: aload_0
    //   943: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   946: athrow
    //   947: astore_1
    //   948: new gnu/mapping/WrongType
    //   951: dup
    //   952: aload_1
    //   953: ldc_w 'set-car!'
    //   956: iconst_1
    //   957: aload_0
    //   958: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   961: athrow
    //   962: astore_0
    //   963: new gnu/mapping/WrongType
    //   966: dup
    //   967: aload_0
    //   968: ldc_w 'set-car!'
    //   971: iconst_1
    //   972: aload #5
    //   974: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   977: athrow
    //   978: astore_0
    //   979: new gnu/mapping/WrongType
    //   982: dup
    //   983: aload_0
    //   984: ldc_w 'set-car!'
    //   987: iconst_1
    //   988: aload #5
    //   990: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   993: athrow
    //   994: astore_0
    //   995: new gnu/mapping/WrongType
    //   998: dup
    //   999: aload_0
    //   1000: ldc_w 'set-car!'
    //   1003: iconst_1
    //   1004: aload_1
    //   1005: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1008: athrow
    //   1009: astore_0
    //   1010: new gnu/mapping/WrongType
    //   1013: dup
    //   1014: aload_0
    //   1015: ldc_w 'set-car!'
    //   1018: iconst_1
    //   1019: aload_1
    //   1020: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1023: athrow
    //   1024: astore_0
    //   1025: new gnu/mapping/WrongType
    //   1028: dup
    //   1029: aload_0
    //   1030: ldc_w 'string-ref'
    //   1033: iconst_1
    //   1034: aload_1
    //   1035: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1038: athrow
    //   1039: astore_1
    //   1040: new gnu/mapping/WrongType
    //   1043: dup
    //   1044: aload_1
    //   1045: ldc_w 'string-ref'
    //   1048: iconst_2
    //   1049: aload_0
    //   1050: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1053: athrow
    //   1054: astore_0
    //   1055: new gnu/mapping/WrongType
    //   1058: dup
    //   1059: aload_0
    //   1060: ldc_w 'set-car!'
    //   1063: iconst_1
    //   1064: aload_1
    //   1065: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1068: athrow
    //   1069: astore_0
    //   1070: new gnu/mapping/WrongType
    //   1073: dup
    //   1074: aload_0
    //   1075: ldc_w 'set-car!'
    //   1078: iconst_1
    //   1079: aload_1
    //   1080: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1083: athrow
    //   1084: astore_0
    //   1085: new gnu/mapping/WrongType
    //   1088: dup
    //   1089: aload_0
    //   1090: ldc_w 'set-car!'
    //   1093: iconst_1
    //   1094: aload_1
    //   1095: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1098: athrow
    //   1099: astore_0
    //   1100: new gnu/mapping/WrongType
    //   1103: dup
    //   1104: aload_0
    //   1105: ldc_w 'set-car!'
    //   1108: iconst_1
    //   1109: aload_1
    //   1110: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1113: athrow
    // Exception table:
    //   from	to	target	type
    //   35	41	841	java/lang/ClassCastException
    //   41	50	856	java/lang/ClassCastException
    //   195	201	872	java/lang/ClassCastException
    //   218	224	887	java/lang/ClassCastException
    //   269	274	994	java/lang/ClassCastException
    //   290	295	1009	java/lang/ClassCastException
    //   421	427	902	java/lang/ClassCastException
    //   444	450	917	java/lang/ClassCastException
    //   492	498	932	java/lang/ClassCastException
    //   515	521	947	java/lang/ClassCastException
    //   611	618	962	java/lang/ClassCastException
    //   640	647	978	java/lang/ClassCastException
    //   670	676	1024	java/lang/ClassCastException
    //   676	684	1039	java/lang/ClassCastException
    //   755	760	1054	java/lang/ClassCastException
    //   776	781	1069	java/lang/ClassCastException
    //   807	812	1084	java/lang/ClassCastException
    //   828	833	1099	java/lang/ClassCastException
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply0(paramModuleMethod);
      case 36:
        return frame.lambda4();
      case 37:
        return frame.frame0.lambda13();
      case 38:
        return frame.frame0.lambda14();
      case 39:
        return frame.frame0.lambda15();
      case 40:
        return frame.frame0.lambda16();
      case 41:
        break;
    } 
    return frame.frame0.lambda17();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 16:
        return pregexpReverse$Ex(paramObject);
      case 28:
        return pregexpInvertCharList(paramObject);
      case 31:
        return isPregexpCharWord(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 35:
        return pregexpMakeBackrefList(paramObject);
      case 44:
        return pregexp(paramObject);
      case 50:
        break;
    } 
    return pregexpQuote(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 33:
        return isPregexpCheckIfInCharClass(paramObject1, paramObject2);
      case 34:
        return pregexpListRef(paramObject1, paramObject2);
      case 47:
        break;
    } 
    return pregexpSplit(paramObject1, paramObject2);
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 18:
        return pregexpReadPattern(paramObject1, paramObject2, paramObject3);
      case 19:
        return pregexpReadBranch(paramObject1, paramObject2, paramObject3);
      case 20:
        return pregexpReadPiece(paramObject1, paramObject2, paramObject3);
      case 21:
        return pregexpReadEscapedNumber(paramObject1, paramObject2, paramObject3);
      case 22:
        return pregexpReadEscapedChar(paramObject1, paramObject2, paramObject3);
      case 23:
        return pregexpReadPosixCharClass(paramObject1, paramObject2, paramObject3);
      case 24:
        return pregexpReadClusterType(paramObject1, paramObject2, paramObject3);
      case 25:
        return pregexpReadSubpattern(paramObject1, paramObject2, paramObject3);
      case 26:
        return pregexpWrapQuantifierIfAny(paramObject1, paramObject2, paramObject3);
      case 27:
        return pregexpReadNums(paramObject1, paramObject2, paramObject3);
      case 29:
        return pregexpReadCharList(paramObject1, paramObject2, paramObject3);
      case 32:
        return isPregexpAtWordBoundary(paramObject1, paramObject2, paramObject3);
      case 48:
        return pregexpReplace(paramObject1, paramObject2, paramObject3);
      case 49:
        break;
    } 
    return pregexpReplace$St(paramObject1, paramObject2, paramObject3);
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    return (paramModuleMethod.selector == 43) ? pregexpReplaceAux(paramObject1, paramObject2, paramObject3, paramObject4) : super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 17:
        return pregexpError$V(paramArrayOfObject);
      case 30:
        return pregexpStringMatch(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4], paramArrayOfObject[5]);
      case 42:
        return pregexpMatchPositionsAux(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4], paramArrayOfObject[5]);
      case 45:
        object1 = paramArrayOfObject[0];
        object2 = paramArrayOfObject[1];
        i = paramArrayOfObject.length - 2;
        arrayOfObject = new Object[i];
        while (true) {
          if (--i < 0)
            return pregexpMatchPositions$V(object1, object2, arrayOfObject); 
          arrayOfObject[i] = paramArrayOfObject[i + 2];
        } 
      case 46:
        break;
    } 
    Object object1 = paramArrayOfObject[0];
    Object object2 = paramArrayOfObject[1];
    int i = paramArrayOfObject.length - 2;
    Object[] arrayOfObject = new Object[i];
    while (true) {
      if (--i < 0)
        return pregexpMatch$V(object1, object2, arrayOfObject); 
      arrayOfObject[i] = paramArrayOfObject[i + 2];
    } 
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match0(paramModuleMethod, paramCallContext);
      case 41:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 40:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 39:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 38:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 37:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 36:
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
      case 50:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 44:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 35:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 31:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 28:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 16:
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
      case 47:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 34:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 33:
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
      case 49:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 48:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 32:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 29:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 27:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 26:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 25:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 24:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 23:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 22:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 21:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 20:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 19:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 18:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.value3 = paramObject3;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 3;
    return 0;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 43) {
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
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 46:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 45:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 42:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 30:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 17:
        break;
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 5;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    $Stpregexp$Mnversion$St = Lit0;
    $Stpregexp$Mncomment$Mnchar$St = Lit1;
    $Stpregexp$Mnnul$Mnchar$Mnint$St = Integer.valueOf(characters.char$To$Integer(Lit2) - 97);
    $Stpregexp$Mnreturn$Mnchar$St = characters.integer$To$Char(((Number)$Stpregexp$Mnnul$Mnchar$Mnint$St).intValue() + 13);
    $Stpregexp$Mntab$Mnchar$St = characters.integer$To$Char(((Number)$Stpregexp$Mnnul$Mnchar$Mnint$St).intValue() + 9);
    $Stpregexp$Mnspace$Mnsensitive$Qu$St = Boolean.TRUE;
  }
  
  public class frame extends ModuleBody {
    Object backrefs;
    
    Object case$Mnsensitive$Qu;
    
    Procedure identity;
    
    Object n;
    
    Object s;
    
    Object sn;
    
    Object start;
    
    public frame() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 15, pregexp.Lit112, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:460");
      this.identity = (Procedure)moduleMethod;
    }
    
    public static Object lambda2identity(Object param1Object) {
      return param1Object;
    }
    
    static Boolean lambda4() {
      return Boolean.FALSE;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 15) ? lambda2identity(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    public Object lambda3sub(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4) {
      throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
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
    
    public class frame0 extends ModuleBody {
      boolean could$Mnloop$Mninfinitely$Qu;
      
      Object fk;
      
      Object i;
      
      final ModuleMethod lambda$Fn11;
      
      final ModuleMethod lambda$Fn12;
      
      final ModuleMethod lambda$Fn2;
      
      final ModuleMethod lambda$Fn3;
      
      final ModuleMethod lambda$Fn4;
      
      final ModuleMethod lambda$Fn5;
      
      boolean maximal$Qu;
      
      Object old;
      
      Object p;
      
      Object q;
      
      Object re;
      
      Object re$1;
      
      Object sk;
      
      pregexp.frame staticLink;
      
      public frame0() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 9, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:513");
        this.lambda$Fn2 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 10, null, 0);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:514");
        this.lambda$Fn3 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 11, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:541");
        this.lambda$Fn4 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 12, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:545");
        this.lambda$Fn5 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 13, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:587");
        this.lambda$Fn11 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 14, null, 0);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:590");
        this.lambda$Fn12 = moduleMethod;
      }
      
      static Boolean lambda13() {
        return Boolean.FALSE;
      }
      
      static Boolean lambda14() {
        return Boolean.FALSE;
      }
      
      static Boolean lambda15() {
        return Boolean.FALSE;
      }
      
      static Boolean lambda16() {
        return Boolean.FALSE;
      }
      
      static Boolean lambda17() {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.apply0(param2ModuleMethod);
          case 10:
            return lambda10();
          case 14:
            break;
        } 
        return lambda19();
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.apply1(param2ModuleMethod, param2Object);
          case 9:
            return lambda9(param2Object);
          case 11:
            return lambda11(param2Object);
          case 12:
            return lambda12(param2Object);
          case 13:
            break;
        } 
        return lambda18(param2Object);
      }
      
      Object lambda10() {
        return Scheme.applyToArgs.apply2(this.sk, AddOp.$Pl.apply2(this.i, pregexp.Lit8));
      }
      
      Object lambda11(Object param2Object) {
        return Scheme.applyToArgs.apply2(this.sk, param2Object);
      }
      
      Object lambda12(Object param2Object) {
        Object object = lists.assv(this.re$1, this.staticLink.backrefs);
        try {
          Pair pair = (Pair)object;
          lists.setCdr$Ex(pair, lists.cons(this.i, param2Object));
          return Scheme.applyToArgs.apply2(this.sk, param2Object);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-cdr!", 1, object);
        } 
      }
      
      Object lambda18(Object param2Object) {
        this.staticLink.case$Mnsensitive$Qu = this.old;
        return Scheme.applyToArgs.apply2(this.sk, param2Object);
      }
      
      Object lambda19() {
        this.staticLink.case$Mnsensitive$Qu = this.old;
        return Scheme.applyToArgs.apply1(this.fk);
      }
      
      public Object lambda5loupOneOfChars(Object param2Object) {
        frame1 frame1 = new frame1();
        frame1.staticLink = this;
        frame1.chars = param2Object;
        return lists.isNull(frame1.chars) ? Scheme.applyToArgs.apply1(this.fk) : this.staticLink.lambda3sub(lists.car.apply1(frame1.chars), this.i, this.sk, frame1.lambda$Fn13);
      }
      
      public Object lambda6loupSeq(Object param2Object1, Object param2Object2) {
        frame2 frame2 = new frame2();
        frame2.staticLink = this;
        frame2.res = param2Object1;
        return lists.isNull(frame2.res) ? Scheme.applyToArgs.apply2(this.sk, param2Object2) : this.staticLink.lambda3sub(lists.car.apply1(frame2.res), param2Object2, frame2.lambda$Fn14, this.fk);
      }
      
      public Object lambda7loupOr(Object param2Object) {
        frame3 frame3 = new frame3();
        frame3.staticLink = this;
        frame3.res = param2Object;
        return lists.isNull(frame3.res) ? Scheme.applyToArgs.apply1(this.fk) : this.staticLink.lambda3sub(lists.car.apply1(frame3.res), this.i, frame3.lambda$Fn15, frame3.lambda$Fn16);
      }
      
      public Object lambda8loupP(Object param2Object1, Object param2Object2) {
        frame4 frame4 = new frame4();
        frame4.staticLink = this;
        frame4.k = param2Object1;
        frame4.i = param2Object2;
        if (Scheme.numLss.apply2(frame4.k, this.p) != Boolean.FALSE)
          return this.staticLink.lambda3sub(this.re, frame4.i, frame4.lambda$Fn17, this.fk); 
        if (this.q != Boolean.FALSE) {
          param2Object1 = AddOp.$Mn.apply2(this.q, this.p);
          frame4.q = param2Object1;
          return frame4.lambda24loupQ(pregexp.Lit73, frame4.i);
        } 
        param2Object1 = this.q;
        frame4.q = param2Object1;
        return frame4.lambda24loupQ(pregexp.Lit73, frame4.i);
      }
      
      Object lambda9(Object param2Object) {
        return Scheme.applyToArgs.apply1(this.fk);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.match0(param2ModuleMethod, param2CallContext);
          case 14:
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 0;
            return 0;
          case 10:
            break;
        } 
        param2CallContext.proc = (Procedure)param2ModuleMethod;
        param2CallContext.pc = 0;
        return 0;
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.match1(param2ModuleMethod, param2Object, param2CallContext);
          case 13:
            param2CallContext.value1 = param2Object;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 1;
            return 0;
          case 12:
            param2CallContext.value1 = param2Object;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 1;
            return 0;
          case 11:
            param2CallContext.value1 = param2Object;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 1;
            return 0;
          case 9:
            break;
        } 
        param2CallContext.value1 = param2Object;
        param2CallContext.proc = (Procedure)param2ModuleMethod;
        param2CallContext.pc = 1;
        return 0;
      }
      
      public class frame1 extends ModuleBody {
        Object chars;
        
        final ModuleMethod lambda$Fn13;
        
        pregexp.frame.frame0 staticLink;
        
        public frame1() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 0);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:508");
          this.lambda$Fn13 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 1) ? lambda20() : super.apply0(param3ModuleMethod);
        }
        
        Object lambda20() {
          return this.staticLink.lambda5loupOneOfChars(lists.cdr.apply1(this.chars));
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 1) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
      }
      
      public class frame2 extends ModuleBody {
        final ModuleMethod lambda$Fn14;
        
        Object res;
        
        pregexp.frame.frame0 staticLink;
        
        public frame2() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 4097);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:519");
          this.lambda$Fn14 = moduleMethod;
        }
        
        public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
          return (param3ModuleMethod.selector == 2) ? lambda21(param3Object) : super.apply1(param3ModuleMethod, param3Object);
        }
        
        Object lambda21(Object param3Object) {
          return this.staticLink.lambda6loupSeq(lists.cdr.apply1(this.res), param3Object);
        }
        
        public int match1(ModuleMethod param3ModuleMethod, Object param3Object, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 2) {
            param3CallContext.value1 = param3Object;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 1;
            return 0;
          } 
          return super.match1(param3ModuleMethod, param3Object, param3CallContext);
        }
      }
      
      public class frame3 extends ModuleBody {
        final ModuleMethod lambda$Fn15;
        
        final ModuleMethod lambda$Fn16;
        
        Object res;
        
        pregexp.frame.frame0 staticLink;
        
        public frame3() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 3, null, 4097);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:526");
          this.lambda$Fn15 = moduleMethod;
          moduleMethod = new ModuleMethod(this, 4, null, 0);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:529");
          this.lambda$Fn16 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 4) ? lambda23() : super.apply0(param3ModuleMethod);
        }
        
        public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
          return (param3ModuleMethod.selector == 3) ? lambda22(param3Object) : super.apply1(param3ModuleMethod, param3Object);
        }
        
        Object lambda22(Object param3Object) {
          param3Object = Scheme.applyToArgs.apply2(this.staticLink.sk, param3Object);
          return (param3Object != Boolean.FALSE) ? param3Object : this.staticLink.lambda7loupOr(lists.cdr.apply1(this.res));
        }
        
        Object lambda23() {
          return this.staticLink.lambda7loupOr(lists.cdr.apply1(this.res));
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 4) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
        
        public int match1(ModuleMethod param3ModuleMethod, Object param3Object, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 3) {
            param3CallContext.value1 = param3Object;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 1;
            return 0;
          } 
          return super.match1(param3ModuleMethod, param3Object, param3CallContext);
        }
      }
      
      public class frame4 extends ModuleBody {
        Object i;
        
        Object k;
        
        final ModuleMethod lambda$Fn17;
        
        Object q;
        
        pregexp.frame.frame0 staticLink;
        
        public frame4() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 8, null, 4097);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:602");
          this.lambda$Fn17 = moduleMethod;
        }
        
        public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
          return (param3ModuleMethod.selector == 8) ? lambda25(param3Object) : super.apply1(param3ModuleMethod, param3Object);
        }
        
        public Object lambda24loupQ(Object param3Object1, Object param3Object2) {
          frame5 frame5 = new frame5();
          frame5.staticLink = this;
          frame5.k = param3Object1;
          frame5.i = param3Object2;
          frame5.fk = frame5.fk;
          if ((this.q != Boolean.FALSE) ? (Scheme.numGEq.apply2(frame5.k, this.q) != Boolean.FALSE) : (this.q != Boolean.FALSE))
            return frame5.lambda26fk(); 
          if (this.staticLink.maximal$Qu)
            return this.staticLink.staticLink.lambda3sub(this.staticLink.re, frame5.i, frame5.lambda$Fn18, frame5.fk); 
          param3Object2 = frame5.lambda26fk();
          param3Object1 = param3Object2;
          return (param3Object2 == Boolean.FALSE) ? this.staticLink.staticLink.lambda3sub(this.staticLink.re, frame5.i, frame5.lambda$Fn19, frame5.fk) : param3Object1;
        }
        
        Object lambda25(Object param3Object) {
          if (this.staticLink.could$Mnloop$Mninfinitely$Qu ? (Scheme.numEqu.apply2(param3Object, this.i) != Boolean.FALSE) : this.staticLink.could$Mnloop$Mninfinitely$Qu)
            pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 }); 
          return this.staticLink.lambda8loupP(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param3Object);
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
        
        public class frame5 extends ModuleBody {
          Procedure fk;
          
          Object i;
          
          Object k;
          
          final ModuleMethod lambda$Fn18;
          
          final ModuleMethod lambda$Fn19;
          
          pregexp.frame.frame0.frame4 staticLink;
          
          public frame5() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 5, pregexp.Lit111, 0);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:612");
            this.fk = (Procedure)moduleMethod;
            moduleMethod = new ModuleMethod(this, 6, null, 4097);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:617");
            this.lambda$Fn18 = moduleMethod;
            moduleMethod = new ModuleMethod(this, 7, null, 4097);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:628");
            this.lambda$Fn19 = moduleMethod;
          }
          
          public Object apply0(ModuleMethod param4ModuleMethod) {
            return (param4ModuleMethod.selector == 5) ? lambda26fk() : super.apply0(param4ModuleMethod);
          }
          
          public Object apply1(ModuleMethod param4ModuleMethod, Object param4Object) {
            switch (param4ModuleMethod.selector) {
              default:
                return super.apply1(param4ModuleMethod, param4Object);
              case 6:
                return lambda27(param4Object);
              case 7:
                break;
            } 
            return lambda28(param4Object);
          }
          
          public Object lambda26fk() {
            return Scheme.applyToArgs.apply2(this.staticLink.staticLink.sk, this.i);
          }
          
          Object lambda27(Object param4Object) {
            if (this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu ? (Scheme.numEqu.apply2(param4Object, this.i) != Boolean.FALSE) : this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu)
              pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 }); 
            param4Object = this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param4Object);
            return (param4Object != Boolean.FALSE) ? param4Object : lambda26fk();
          }
          
          Object lambda28(Object param4Object) {
            return this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param4Object);
          }
          
          public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 5) {
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 0;
              return 0;
            } 
            return super.match0(param4ModuleMethod, param4CallContext);
          }
          
          public int match1(ModuleMethod param4ModuleMethod, Object param4Object, CallContext param4CallContext) {
            switch (param4ModuleMethod.selector) {
              default:
                return super.match1(param4ModuleMethod, param4Object, param4CallContext);
              case 7:
                param4CallContext.value1 = param4Object;
                param4CallContext.proc = (Procedure)param4ModuleMethod;
                param4CallContext.pc = 1;
                return 0;
              case 6:
                break;
            } 
            param4CallContext.value1 = param4Object;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 1;
            return 0;
          }
        }
      }
      
      public class frame5 extends ModuleBody {
        Procedure fk;
        
        Object i;
        
        Object k;
        
        final ModuleMethod lambda$Fn18;
        
        final ModuleMethod lambda$Fn19;
        
        pregexp.frame.frame0.frame4 staticLink;
        
        public frame5() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 5, pregexp.Lit111, 0);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:612");
          this.fk = (Procedure)moduleMethod;
          moduleMethod = new ModuleMethod(this, 6, null, 4097);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:617");
          this.lambda$Fn18 = moduleMethod;
          moduleMethod = new ModuleMethod(this, 7, null, 4097);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:628");
          this.lambda$Fn19 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 5) ? lambda26fk() : super.apply0(param3ModuleMethod);
        }
        
        public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
          switch (param3ModuleMethod.selector) {
            default:
              return super.apply1(param3ModuleMethod, param3Object);
            case 6:
              return lambda27(param3Object);
            case 7:
              break;
          } 
          return lambda28(param3Object);
        }
        
        public Object lambda26fk() {
          return Scheme.applyToArgs.apply2(this.staticLink.staticLink.sk, this.i);
        }
        
        Object lambda27(Object param3Object) {
          if (this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu ? (Scheme.numEqu.apply2(param3Object, this.i) != Boolean.FALSE) : this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu)
            pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 }); 
          param3Object = this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param3Object);
          return (param3Object != Boolean.FALSE) ? param3Object : lambda26fk();
        }
        
        Object lambda28(Object param3Object) {
          return this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param3Object);
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 5) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
        
        public int match1(ModuleMethod param3ModuleMethod, Object param3Object, CallContext param3CallContext) {
          switch (param3ModuleMethod.selector) {
            default:
              return super.match1(param3ModuleMethod, param3Object, param3CallContext);
            case 7:
              param3CallContext.value1 = param3Object;
              param3CallContext.proc = (Procedure)param3ModuleMethod;
              param3CallContext.pc = 1;
              return 0;
            case 6:
              break;
          } 
          param3CallContext.value1 = param3Object;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 1;
          return 0;
        }
      }
    }
    
    public class frame1 extends ModuleBody {
      Object chars;
      
      final ModuleMethod lambda$Fn13;
      
      pregexp.frame.frame0 staticLink;
      
      public frame1() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 0);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:508");
        this.lambda$Fn13 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 1) ? lambda20() : super.apply0(param2ModuleMethod);
      }
      
      Object lambda20() {
        return this.staticLink.lambda5loupOneOfChars(lists.cdr.apply1(this.chars));
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 1) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
    }
    
    public class frame2 extends ModuleBody {
      final ModuleMethod lambda$Fn14;
      
      Object res;
      
      pregexp.frame.frame0 staticLink;
      
      public frame2() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:519");
        this.lambda$Fn14 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 2) ? lambda21(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      Object lambda21(Object param2Object) {
        return this.staticLink.lambda6loupSeq(lists.cdr.apply1(this.res), param2Object);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 2) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
    }
    
    public class frame3 extends ModuleBody {
      final ModuleMethod lambda$Fn15;
      
      final ModuleMethod lambda$Fn16;
      
      Object res;
      
      pregexp.frame.frame0 staticLink;
      
      public frame3() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 3, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:526");
        this.lambda$Fn15 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 4, null, 0);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:529");
        this.lambda$Fn16 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 4) ? lambda23() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 3) ? lambda22(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      Object lambda22(Object param2Object) {
        param2Object = Scheme.applyToArgs.apply2(this.staticLink.sk, param2Object);
        return (param2Object != Boolean.FALSE) ? param2Object : this.staticLink.lambda7loupOr(lists.cdr.apply1(this.res));
      }
      
      Object lambda23() {
        return this.staticLink.lambda7loupOr(lists.cdr.apply1(this.res));
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 4) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 3) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
    }
    
    public class frame4 extends ModuleBody {
      Object i;
      
      Object k;
      
      final ModuleMethod lambda$Fn17;
      
      Object q;
      
      pregexp.frame.frame0 staticLink;
      
      public frame4() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 8, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:602");
        this.lambda$Fn17 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 8) ? lambda25(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      public Object lambda24loupQ(Object param2Object1, Object param2Object2) {
        frame5 frame5 = new frame5();
        frame5.staticLink = this;
        frame5.k = param2Object1;
        frame5.i = param2Object2;
        frame5.fk = frame5.fk;
        if ((this.q != Boolean.FALSE) ? (Scheme.numGEq.apply2(frame5.k, this.q) != Boolean.FALSE) : (this.q != Boolean.FALSE))
          return frame5.lambda26fk(); 
        if (this.staticLink.maximal$Qu)
          return this.staticLink.staticLink.lambda3sub(this.staticLink.re, frame5.i, frame5.lambda$Fn18, frame5.fk); 
        param2Object2 = frame5.lambda26fk();
        param2Object1 = param2Object2;
        return (param2Object2 == Boolean.FALSE) ? this.staticLink.staticLink.lambda3sub(this.staticLink.re, frame5.i, frame5.lambda$Fn19, frame5.fk) : param2Object1;
      }
      
      Object lambda25(Object param2Object) {
        if (this.staticLink.could$Mnloop$Mninfinitely$Qu ? (Scheme.numEqu.apply2(param2Object, this.i) != Boolean.FALSE) : this.staticLink.could$Mnloop$Mninfinitely$Qu)
          pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 }); 
        return this.staticLink.lambda8loupP(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param2Object);
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
      
      public class frame5 extends ModuleBody {
        Procedure fk;
        
        Object i;
        
        Object k;
        
        final ModuleMethod lambda$Fn18;
        
        final ModuleMethod lambda$Fn19;
        
        pregexp.frame.frame0.frame4 staticLink;
        
        public frame5() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 5, pregexp.Lit111, 0);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:612");
          this.fk = (Procedure)moduleMethod;
          moduleMethod = new ModuleMethod(this, 6, null, 4097);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:617");
          this.lambda$Fn18 = moduleMethod;
          moduleMethod = new ModuleMethod(this, 7, null, 4097);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:628");
          this.lambda$Fn19 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param4ModuleMethod) {
          return (param4ModuleMethod.selector == 5) ? lambda26fk() : super.apply0(param4ModuleMethod);
        }
        
        public Object apply1(ModuleMethod param4ModuleMethod, Object param4Object) {
          switch (param4ModuleMethod.selector) {
            default:
              return super.apply1(param4ModuleMethod, param4Object);
            case 6:
              return lambda27(param4Object);
            case 7:
              break;
          } 
          return lambda28(param4Object);
        }
        
        public Object lambda26fk() {
          return Scheme.applyToArgs.apply2(this.staticLink.staticLink.sk, this.i);
        }
        
        Object lambda27(Object param4Object) {
          if (this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu ? (Scheme.numEqu.apply2(param4Object, this.i) != Boolean.FALSE) : this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu)
            pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 }); 
          param4Object = this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param4Object);
          return (param4Object != Boolean.FALSE) ? param4Object : lambda26fk();
        }
        
        Object lambda28(Object param4Object) {
          return this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param4Object);
        }
        
        public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 5) {
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param4ModuleMethod, param4CallContext);
        }
        
        public int match1(ModuleMethod param4ModuleMethod, Object param4Object, CallContext param4CallContext) {
          switch (param4ModuleMethod.selector) {
            default:
              return super.match1(param4ModuleMethod, param4Object, param4CallContext);
            case 7:
              param4CallContext.value1 = param4Object;
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 1;
              return 0;
            case 6:
              break;
          } 
          param4CallContext.value1 = param4Object;
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 1;
          return 0;
        }
      }
    }
    
    public class frame5 extends ModuleBody {
      Procedure fk;
      
      Object i;
      
      Object k;
      
      final ModuleMethod lambda$Fn18;
      
      final ModuleMethod lambda$Fn19;
      
      pregexp.frame.frame0.frame4 staticLink;
      
      public frame5() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 5, pregexp.Lit111, 0);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:612");
        this.fk = (Procedure)moduleMethod;
        moduleMethod = new ModuleMethod(this, 6, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:617");
        this.lambda$Fn18 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 7, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:628");
        this.lambda$Fn19 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 5) ? lambda26fk() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.apply1(param2ModuleMethod, param2Object);
          case 6:
            return lambda27(param2Object);
          case 7:
            break;
        } 
        return lambda28(param2Object);
      }
      
      public Object lambda26fk() {
        return Scheme.applyToArgs.apply2(this.staticLink.staticLink.sk, this.i);
      }
      
      Object lambda27(Object param2Object) {
        if (this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu ? (Scheme.numEqu.apply2(param2Object, this.i) != Boolean.FALSE) : this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu)
          pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 }); 
        param2Object = this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param2Object);
        return (param2Object != Boolean.FALSE) ? param2Object : lambda26fk();
      }
      
      Object lambda28(Object param2Object) {
        return this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param2Object);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 5) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.match1(param2ModuleMethod, param2Object, param2CallContext);
          case 7:
            param2CallContext.value1 = param2Object;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 1;
            return 0;
          case 6:
            break;
        } 
        param2CallContext.value1 = param2Object;
        param2CallContext.proc = (Procedure)param2ModuleMethod;
        param2CallContext.pc = 1;
        return 0;
      }
    }
  }
  
  public class frame0 extends ModuleBody {
    boolean could$Mnloop$Mninfinitely$Qu;
    
    Object fk;
    
    Object i;
    
    final ModuleMethod lambda$Fn11;
    
    final ModuleMethod lambda$Fn12;
    
    final ModuleMethod lambda$Fn2;
    
    final ModuleMethod lambda$Fn3;
    
    final ModuleMethod lambda$Fn4;
    
    final ModuleMethod lambda$Fn5;
    
    boolean maximal$Qu;
    
    Object old;
    
    Object p;
    
    Object q;
    
    Object re;
    
    Object re$1;
    
    Object sk;
    
    pregexp.frame staticLink;
    
    public frame0() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 9, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:513");
      this.lambda$Fn2 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 10, null, 0);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:514");
      this.lambda$Fn3 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 11, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:541");
      this.lambda$Fn4 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 12, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:545");
      this.lambda$Fn5 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 13, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:587");
      this.lambda$Fn11 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 14, null, 0);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:590");
      this.lambda$Fn12 = moduleMethod;
    }
    
    static Boolean lambda13() {
      return Boolean.FALSE;
    }
    
    static Boolean lambda14() {
      return Boolean.FALSE;
    }
    
    static Boolean lambda15() {
      return Boolean.FALSE;
    }
    
    static Boolean lambda16() {
      return Boolean.FALSE;
    }
    
    static Boolean lambda17() {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply0(param1ModuleMethod);
        case 10:
          return lambda10();
        case 14:
          break;
      } 
      return lambda19();
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply1(param1ModuleMethod, param1Object);
        case 9:
          return lambda9(param1Object);
        case 11:
          return lambda11(param1Object);
        case 12:
          return lambda12(param1Object);
        case 13:
          break;
      } 
      return lambda18(param1Object);
    }
    
    Object lambda10() {
      return Scheme.applyToArgs.apply2(this.sk, AddOp.$Pl.apply2(this.i, pregexp.Lit8));
    }
    
    Object lambda11(Object param1Object) {
      return Scheme.applyToArgs.apply2(this.sk, param1Object);
    }
    
    Object lambda12(Object param1Object) {
      Object object = lists.assv(this.re$1, this.staticLink.backrefs);
      try {
        Pair pair = (Pair)object;
        lists.setCdr$Ex(pair, lists.cons(this.i, param1Object));
        return Scheme.applyToArgs.apply2(this.sk, param1Object);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "set-cdr!", 1, object);
      } 
    }
    
    Object lambda18(Object param1Object) {
      this.staticLink.case$Mnsensitive$Qu = this.old;
      return Scheme.applyToArgs.apply2(this.sk, param1Object);
    }
    
    Object lambda19() {
      this.staticLink.case$Mnsensitive$Qu = this.old;
      return Scheme.applyToArgs.apply1(this.fk);
    }
    
    public Object lambda5loupOneOfChars(Object param1Object) {
      frame1 frame1 = new frame1();
      frame1.staticLink = this;
      frame1.chars = param1Object;
      return lists.isNull(frame1.chars) ? Scheme.applyToArgs.apply1(this.fk) : this.staticLink.lambda3sub(lists.car.apply1(frame1.chars), this.i, this.sk, frame1.lambda$Fn13);
    }
    
    public Object lambda6loupSeq(Object param1Object1, Object param1Object2) {
      frame2 frame2 = new frame2();
      frame2.staticLink = this;
      frame2.res = param1Object1;
      return lists.isNull(frame2.res) ? Scheme.applyToArgs.apply2(this.sk, param1Object2) : this.staticLink.lambda3sub(lists.car.apply1(frame2.res), param1Object2, frame2.lambda$Fn14, this.fk);
    }
    
    public Object lambda7loupOr(Object param1Object) {
      frame3 frame3 = new frame3();
      frame3.staticLink = this;
      frame3.res = param1Object;
      return lists.isNull(frame3.res) ? Scheme.applyToArgs.apply1(this.fk) : this.staticLink.lambda3sub(lists.car.apply1(frame3.res), this.i, frame3.lambda$Fn15, frame3.lambda$Fn16);
    }
    
    public Object lambda8loupP(Object param1Object1, Object param1Object2) {
      frame4 frame4 = new frame4();
      frame4.staticLink = this;
      frame4.k = param1Object1;
      frame4.i = param1Object2;
      if (Scheme.numLss.apply2(frame4.k, this.p) != Boolean.FALSE)
        return this.staticLink.lambda3sub(this.re, frame4.i, frame4.lambda$Fn17, this.fk); 
      if (this.q != Boolean.FALSE) {
        param1Object1 = AddOp.$Mn.apply2(this.q, this.p);
        frame4.q = param1Object1;
        return frame4.lambda24loupQ(pregexp.Lit73, frame4.i);
      } 
      param1Object1 = this.q;
      frame4.q = param1Object1;
      return frame4.lambda24loupQ(pregexp.Lit73, frame4.i);
    }
    
    Object lambda9(Object param1Object) {
      return Scheme.applyToArgs.apply1(this.fk);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match0(param1ModuleMethod, param1CallContext);
        case 14:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 10:
          break;
      } 
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 0;
      return 0;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match1(param1ModuleMethod, param1Object, param1CallContext);
        case 13:
          param1CallContext.value1 = param1Object;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 1;
          return 0;
        case 12:
          param1CallContext.value1 = param1Object;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 1;
          return 0;
        case 11:
          param1CallContext.value1 = param1Object;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 1;
          return 0;
        case 9:
          break;
      } 
      param1CallContext.value1 = param1Object;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 1;
      return 0;
    }
    
    public class frame1 extends ModuleBody {
      Object chars;
      
      final ModuleMethod lambda$Fn13;
      
      pregexp.frame.frame0 staticLink;
      
      public frame1() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 0);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:508");
        this.lambda$Fn13 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 1) ? lambda20() : super.apply0(param3ModuleMethod);
      }
      
      Object lambda20() {
        return this.staticLink.lambda5loupOneOfChars(lists.cdr.apply1(this.chars));
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 1) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
    }
    
    public class frame2 extends ModuleBody {
      final ModuleMethod lambda$Fn14;
      
      Object res;
      
      pregexp.frame.frame0 staticLink;
      
      public frame2() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:519");
        this.lambda$Fn14 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
        return (param3ModuleMethod.selector == 2) ? lambda21(param3Object) : super.apply1(param3ModuleMethod, param3Object);
      }
      
      Object lambda21(Object param3Object) {
        return this.staticLink.lambda6loupSeq(lists.cdr.apply1(this.res), param3Object);
      }
      
      public int match1(ModuleMethod param3ModuleMethod, Object param3Object, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 2) {
          param3CallContext.value1 = param3Object;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param3ModuleMethod, param3Object, param3CallContext);
      }
    }
    
    public class frame3 extends ModuleBody {
      final ModuleMethod lambda$Fn15;
      
      final ModuleMethod lambda$Fn16;
      
      Object res;
      
      pregexp.frame.frame0 staticLink;
      
      public frame3() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 3, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:526");
        this.lambda$Fn15 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 4, null, 0);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:529");
        this.lambda$Fn16 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 4) ? lambda23() : super.apply0(param3ModuleMethod);
      }
      
      public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
        return (param3ModuleMethod.selector == 3) ? lambda22(param3Object) : super.apply1(param3ModuleMethod, param3Object);
      }
      
      Object lambda22(Object param3Object) {
        param3Object = Scheme.applyToArgs.apply2(this.staticLink.sk, param3Object);
        return (param3Object != Boolean.FALSE) ? param3Object : this.staticLink.lambda7loupOr(lists.cdr.apply1(this.res));
      }
      
      Object lambda23() {
        return this.staticLink.lambda7loupOr(lists.cdr.apply1(this.res));
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 4) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
      
      public int match1(ModuleMethod param3ModuleMethod, Object param3Object, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 3) {
          param3CallContext.value1 = param3Object;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param3ModuleMethod, param3Object, param3CallContext);
      }
    }
    
    public class frame4 extends ModuleBody {
      Object i;
      
      Object k;
      
      final ModuleMethod lambda$Fn17;
      
      Object q;
      
      pregexp.frame.frame0 staticLink;
      
      public frame4() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 8, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:602");
        this.lambda$Fn17 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
        return (param3ModuleMethod.selector == 8) ? lambda25(param3Object) : super.apply1(param3ModuleMethod, param3Object);
      }
      
      public Object lambda24loupQ(Object param3Object1, Object param3Object2) {
        frame5 frame5 = new frame5();
        frame5.staticLink = this;
        frame5.k = param3Object1;
        frame5.i = param3Object2;
        frame5.fk = frame5.fk;
        if ((this.q != Boolean.FALSE) ? (Scheme.numGEq.apply2(frame5.k, this.q) != Boolean.FALSE) : (this.q != Boolean.FALSE))
          return frame5.lambda26fk(); 
        if (this.staticLink.maximal$Qu)
          return this.staticLink.staticLink.lambda3sub(this.staticLink.re, frame5.i, frame5.lambda$Fn18, frame5.fk); 
        param3Object2 = frame5.lambda26fk();
        param3Object1 = param3Object2;
        return (param3Object2 == Boolean.FALSE) ? this.staticLink.staticLink.lambda3sub(this.staticLink.re, frame5.i, frame5.lambda$Fn19, frame5.fk) : param3Object1;
      }
      
      Object lambda25(Object param3Object) {
        if (this.staticLink.could$Mnloop$Mninfinitely$Qu ? (Scheme.numEqu.apply2(param3Object, this.i) != Boolean.FALSE) : this.staticLink.could$Mnloop$Mninfinitely$Qu)
          pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 }); 
        return this.staticLink.lambda8loupP(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param3Object);
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
      
      public class frame5 extends ModuleBody {
        Procedure fk;
        
        Object i;
        
        Object k;
        
        final ModuleMethod lambda$Fn18;
        
        final ModuleMethod lambda$Fn19;
        
        pregexp.frame.frame0.frame4 staticLink;
        
        public frame5() {
          ModuleMethod moduleMethod = new ModuleMethod(this, 5, pregexp.Lit111, 0);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:612");
          this.fk = (Procedure)moduleMethod;
          moduleMethod = new ModuleMethod(this, 6, null, 4097);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:617");
          this.lambda$Fn18 = moduleMethod;
          moduleMethod = new ModuleMethod(this, 7, null, 4097);
          moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:628");
          this.lambda$Fn19 = moduleMethod;
        }
        
        public Object apply0(ModuleMethod param4ModuleMethod) {
          return (param4ModuleMethod.selector == 5) ? lambda26fk() : super.apply0(param4ModuleMethod);
        }
        
        public Object apply1(ModuleMethod param4ModuleMethod, Object param4Object) {
          switch (param4ModuleMethod.selector) {
            default:
              return super.apply1(param4ModuleMethod, param4Object);
            case 6:
              return lambda27(param4Object);
            case 7:
              break;
          } 
          return lambda28(param4Object);
        }
        
        public Object lambda26fk() {
          return Scheme.applyToArgs.apply2(this.staticLink.staticLink.sk, this.i);
        }
        
        Object lambda27(Object param4Object) {
          if (this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu ? (Scheme.numEqu.apply2(param4Object, this.i) != Boolean.FALSE) : this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu)
            pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 }); 
          param4Object = this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param4Object);
          return (param4Object != Boolean.FALSE) ? param4Object : lambda26fk();
        }
        
        Object lambda28(Object param4Object) {
          return this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param4Object);
        }
        
        public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 5) {
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param4ModuleMethod, param4CallContext);
        }
        
        public int match1(ModuleMethod param4ModuleMethod, Object param4Object, CallContext param4CallContext) {
          switch (param4ModuleMethod.selector) {
            default:
              return super.match1(param4ModuleMethod, param4Object, param4CallContext);
            case 7:
              param4CallContext.value1 = param4Object;
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 1;
              return 0;
            case 6:
              break;
          } 
          param4CallContext.value1 = param4Object;
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 1;
          return 0;
        }
      }
    }
    
    public class frame5 extends ModuleBody {
      Procedure fk;
      
      Object i;
      
      Object k;
      
      final ModuleMethod lambda$Fn18;
      
      final ModuleMethod lambda$Fn19;
      
      pregexp.frame.frame0.frame4 staticLink;
      
      public frame5() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 5, pregexp.Lit111, 0);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:612");
        this.fk = (Procedure)moduleMethod;
        moduleMethod = new ModuleMethod(this, 6, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:617");
        this.lambda$Fn18 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 7, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:628");
        this.lambda$Fn19 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 5) ? lambda26fk() : super.apply0(param3ModuleMethod);
      }
      
      public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
        switch (param3ModuleMethod.selector) {
          default:
            return super.apply1(param3ModuleMethod, param3Object);
          case 6:
            return lambda27(param3Object);
          case 7:
            break;
        } 
        return lambda28(param3Object);
      }
      
      public Object lambda26fk() {
        return Scheme.applyToArgs.apply2(this.staticLink.staticLink.sk, this.i);
      }
      
      Object lambda27(Object param3Object) {
        if (this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu ? (Scheme.numEqu.apply2(param3Object, this.i) != Boolean.FALSE) : this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu)
          pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 }); 
        param3Object = this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param3Object);
        return (param3Object != Boolean.FALSE) ? param3Object : lambda26fk();
      }
      
      Object lambda28(Object param3Object) {
        return this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param3Object);
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 5) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
      
      public int match1(ModuleMethod param3ModuleMethod, Object param3Object, CallContext param3CallContext) {
        switch (param3ModuleMethod.selector) {
          default:
            return super.match1(param3ModuleMethod, param3Object, param3CallContext);
          case 7:
            param3CallContext.value1 = param3Object;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 1;
            return 0;
          case 6:
            break;
        } 
        param3CallContext.value1 = param3Object;
        param3CallContext.proc = (Procedure)param3ModuleMethod;
        param3CallContext.pc = 1;
        return 0;
      }
    }
  }
  
  public class frame1 extends ModuleBody {
    Object chars;
    
    final ModuleMethod lambda$Fn13;
    
    pregexp.frame.frame0 staticLink;
    
    public frame1() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 0);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:508");
      this.lambda$Fn13 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 1) ? lambda20() : super.apply0(param1ModuleMethod);
    }
    
    Object lambda20() {
      return this.staticLink.lambda5loupOneOfChars(lists.cdr.apply1(this.chars));
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 1) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
  }
  
  public class frame2 extends ModuleBody {
    final ModuleMethod lambda$Fn14;
    
    Object res;
    
    pregexp.frame.frame0 staticLink;
    
    public frame2() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:519");
      this.lambda$Fn14 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 2) ? lambda21(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda21(Object param1Object) {
      return this.staticLink.lambda6loupSeq(lists.cdr.apply1(this.res), param1Object);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 2) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame3 extends ModuleBody {
    final ModuleMethod lambda$Fn15;
    
    final ModuleMethod lambda$Fn16;
    
    Object res;
    
    pregexp.frame.frame0 staticLink;
    
    public frame3() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 3, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:526");
      this.lambda$Fn15 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 4, null, 0);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:529");
      this.lambda$Fn16 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 4) ? lambda23() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 3) ? lambda22(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda22(Object param1Object) {
      param1Object = Scheme.applyToArgs.apply2(this.staticLink.sk, param1Object);
      return (param1Object != Boolean.FALSE) ? param1Object : this.staticLink.lambda7loupOr(lists.cdr.apply1(this.res));
    }
    
    Object lambda23() {
      return this.staticLink.lambda7loupOr(lists.cdr.apply1(this.res));
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 4) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 3) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame4 extends ModuleBody {
    Object i;
    
    Object k;
    
    final ModuleMethod lambda$Fn17;
    
    Object q;
    
    pregexp.frame.frame0 staticLink;
    
    public frame4() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 8, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:602");
      this.lambda$Fn17 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 8) ? lambda25(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    public Object lambda24loupQ(Object param1Object1, Object param1Object2) {
      frame5 frame5 = new frame5();
      frame5.staticLink = this;
      frame5.k = param1Object1;
      frame5.i = param1Object2;
      frame5.fk = frame5.fk;
      if ((this.q != Boolean.FALSE) ? (Scheme.numGEq.apply2(frame5.k, this.q) != Boolean.FALSE) : (this.q != Boolean.FALSE))
        return frame5.lambda26fk(); 
      if (this.staticLink.maximal$Qu)
        return this.staticLink.staticLink.lambda3sub(this.staticLink.re, frame5.i, frame5.lambda$Fn18, frame5.fk); 
      param1Object2 = frame5.lambda26fk();
      param1Object1 = param1Object2;
      return (param1Object2 == Boolean.FALSE) ? this.staticLink.staticLink.lambda3sub(this.staticLink.re, frame5.i, frame5.lambda$Fn19, frame5.fk) : param1Object1;
    }
    
    Object lambda25(Object param1Object) {
      if (this.staticLink.could$Mnloop$Mninfinitely$Qu ? (Scheme.numEqu.apply2(param1Object, this.i) != Boolean.FALSE) : this.staticLink.could$Mnloop$Mninfinitely$Qu)
        pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 }); 
      return this.staticLink.lambda8loupP(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param1Object);
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
    
    public class frame5 extends ModuleBody {
      Procedure fk;
      
      Object i;
      
      Object k;
      
      final ModuleMethod lambda$Fn18;
      
      final ModuleMethod lambda$Fn19;
      
      pregexp.frame.frame0.frame4 staticLink;
      
      public frame5() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 5, pregexp.Lit111, 0);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:612");
        this.fk = (Procedure)moduleMethod;
        moduleMethod = new ModuleMethod(this, 6, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:617");
        this.lambda$Fn18 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 7, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:628");
        this.lambda$Fn19 = moduleMethod;
      }
      
      public Object apply0(ModuleMethod param4ModuleMethod) {
        return (param4ModuleMethod.selector == 5) ? lambda26fk() : super.apply0(param4ModuleMethod);
      }
      
      public Object apply1(ModuleMethod param4ModuleMethod, Object param4Object) {
        switch (param4ModuleMethod.selector) {
          default:
            return super.apply1(param4ModuleMethod, param4Object);
          case 6:
            return lambda27(param4Object);
          case 7:
            break;
        } 
        return lambda28(param4Object);
      }
      
      public Object lambda26fk() {
        return Scheme.applyToArgs.apply2(this.staticLink.staticLink.sk, this.i);
      }
      
      Object lambda27(Object param4Object) {
        if (this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu ? (Scheme.numEqu.apply2(param4Object, this.i) != Boolean.FALSE) : this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu)
          pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 }); 
        param4Object = this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param4Object);
        return (param4Object != Boolean.FALSE) ? param4Object : lambda26fk();
      }
      
      Object lambda28(Object param4Object) {
        return this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param4Object);
      }
      
      public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 5) {
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param4ModuleMethod, param4CallContext);
      }
      
      public int match1(ModuleMethod param4ModuleMethod, Object param4Object, CallContext param4CallContext) {
        switch (param4ModuleMethod.selector) {
          default:
            return super.match1(param4ModuleMethod, param4Object, param4CallContext);
          case 7:
            param4CallContext.value1 = param4Object;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 1;
            return 0;
          case 6:
            break;
        } 
        param4CallContext.value1 = param4Object;
        param4CallContext.proc = (Procedure)param4ModuleMethod;
        param4CallContext.pc = 1;
        return 0;
      }
    }
  }
  
  public class frame5 extends ModuleBody {
    Procedure fk;
    
    Object i;
    
    Object k;
    
    final ModuleMethod lambda$Fn18;
    
    final ModuleMethod lambda$Fn19;
    
    pregexp.frame.frame0.frame4 staticLink;
    
    public frame5() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 5, pregexp.Lit111, 0);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:612");
      this.fk = (Procedure)moduleMethod;
      moduleMethod = new ModuleMethod(this, 6, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:617");
      this.lambda$Fn18 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 7, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:628");
      this.lambda$Fn19 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 5) ? lambda26fk() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply1(param1ModuleMethod, param1Object);
        case 6:
          return lambda27(param1Object);
        case 7:
          break;
      } 
      return lambda28(param1Object);
    }
    
    public Object lambda26fk() {
      return Scheme.applyToArgs.apply2(this.staticLink.staticLink.sk, this.i);
    }
    
    Object lambda27(Object param1Object) {
      if (this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu ? (Scheme.numEqu.apply2(param1Object, this.i) != Boolean.FALSE) : this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu)
        pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 }); 
      param1Object = this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param1Object);
      return (param1Object != Boolean.FALSE) ? param1Object : lambda26fk();
    }
    
    Object lambda28(Object param1Object) {
      return this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), param1Object);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 5) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match1(param1ModuleMethod, param1Object, param1CallContext);
        case 7:
          param1CallContext.value1 = param1Object;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 1;
          return 0;
        case 6:
          break;
      } 
      param1CallContext.value1 = param1Object;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 1;
      return 0;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/pregexp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
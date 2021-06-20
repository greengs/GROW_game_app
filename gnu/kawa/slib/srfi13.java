package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.reflect.Invoke;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.RealNum;
import gnu.text.Char;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;
import kawa.standard.call_with_values;

public class srfi13 extends ModuleBody {
  public static final ModuleMethod $Pccheck$Mnbounds;
  
  public static final ModuleMethod $Pcfinish$Mnstring$Mnconcatenate$Mnreverse;
  
  public static final ModuleMethod $Pckmp$Mnsearch;
  
  public static final ModuleMethod $Pcmultispan$Mnrepcopy$Ex;
  
  public static final ModuleMethod $Pcstring$Mncompare;
  
  public static final ModuleMethod $Pcstring$Mncompare$Mnci;
  
  public static final ModuleMethod $Pcstring$Mncopy$Ex;
  
  public static final ModuleMethod $Pcstring$Mnhash;
  
  public static final ModuleMethod $Pcstring$Mnmap;
  
  public static final ModuleMethod $Pcstring$Mnmap$Ex;
  
  public static final ModuleMethod $Pcstring$Mnprefix$Mnci$Qu;
  
  public static final ModuleMethod $Pcstring$Mnprefix$Mnlength;
  
  public static final ModuleMethod $Pcstring$Mnprefix$Mnlength$Mnci;
  
  public static final ModuleMethod $Pcstring$Mnprefix$Qu;
  
  public static final ModuleMethod $Pcstring$Mnsuffix$Mnci$Qu;
  
  public static final ModuleMethod $Pcstring$Mnsuffix$Mnlength;
  
  public static final ModuleMethod $Pcstring$Mnsuffix$Mnlength$Mnci;
  
  public static final ModuleMethod $Pcstring$Mnsuffix$Qu;
  
  public static final ModuleMethod $Pcstring$Mntitlecase$Ex;
  
  public static final ModuleMethod $Pcsubstring$Slshared;
  
  public static final srfi13 $instance;
  
  static final IntNum Lit0;
  
  static final IntNum Lit1;
  
  static final IntNum Lit10;
  
  static final SimpleSymbol Lit100;
  
  static final SimpleSymbol Lit101;
  
  static final SimpleSymbol Lit102;
  
  static final SimpleSymbol Lit103;
  
  static final SimpleSymbol Lit104;
  
  static final SimpleSymbol Lit105;
  
  static final SimpleSymbol Lit106;
  
  static final SimpleSymbol Lit107;
  
  static final SimpleSymbol Lit108;
  
  static final SimpleSymbol Lit109;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit110;
  
  static final SimpleSymbol Lit111;
  
  static final SimpleSymbol Lit112;
  
  static final SimpleSymbol Lit113;
  
  static final SimpleSymbol Lit114;
  
  static final SimpleSymbol Lit115;
  
  static final SimpleSymbol Lit116;
  
  static final SimpleSymbol Lit117;
  
  static final SimpleSymbol Lit118;
  
  static final SimpleSymbol Lit119;
  
  static final Char Lit12;
  
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
  
  static final IntNum Lit13;
  
  static final SimpleSymbol Lit130;
  
  static final SimpleSymbol Lit131;
  
  static final SimpleSymbol Lit132;
  
  static final SimpleSymbol Lit133;
  
  static final SimpleSymbol Lit134;
  
  static final SimpleSymbol Lit135;
  
  static final SimpleSymbol Lit136;
  
  static final SimpleSymbol Lit137;
  
  static final SimpleSymbol Lit138;
  
  static final SimpleSymbol Lit139;
  
  static final SimpleSymbol Lit14;
  
  static final SimpleSymbol Lit140;
  
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
  
  static final SimpleSymbol Lit150 = (SimpleSymbol)(new SimpleSymbol("receive")).readResolve();
  
  static final SimpleSymbol Lit16;
  
  static final SimpleSymbol Lit17;
  
  static final SimpleSymbol Lit18;
  
  static final SimpleSymbol Lit19;
  
  static final IntNum Lit2;
  
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
  
  static final IntNum Lit3;
  
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
  
  static final IntNum Lit4;
  
  static final SimpleSymbol Lit40;
  
  static final SimpleSymbol Lit41;
  
  static final SyntaxRules Lit42;
  
  static final SimpleSymbol Lit43;
  
  static final SyntaxRules Lit44;
  
  static final SimpleSymbol Lit45;
  
  static final SimpleSymbol Lit46;
  
  static final SimpleSymbol Lit47;
  
  static final SimpleSymbol Lit48;
  
  static final SimpleSymbol Lit49;
  
  static final IntNum Lit5;
  
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
  
  static final IntNum Lit6;
  
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
  
  static final IntNum Lit7;
  
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
  
  static final IntNum Lit8;
  
  static final SimpleSymbol Lit80;
  
  static final SimpleSymbol Lit81;
  
  static final SimpleSymbol Lit82;
  
  static final SimpleSymbol Lit83;
  
  static final SimpleSymbol Lit84;
  
  static final SimpleSymbol Lit85;
  
  static final SimpleSymbol Lit86;
  
  static final SimpleSymbol Lit87;
  
  static final SimpleSymbol Lit88;
  
  static final SimpleSymbol Lit89;
  
  static final IntNum Lit9;
  
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
  
  public static final ModuleMethod check$Mnsubstring$Mnspec;
  
  public static final ModuleMethod kmp$Mnstep;
  
  static final ModuleMethod lambda$Fn100;
  
  static final ModuleMethod lambda$Fn105;
  
  static final ModuleMethod lambda$Fn106;
  
  static final ModuleMethod lambda$Fn111;
  
  static final ModuleMethod lambda$Fn116;
  
  static final ModuleMethod lambda$Fn117;
  
  static final ModuleMethod lambda$Fn122;
  
  static final ModuleMethod lambda$Fn123;
  
  static final ModuleMethod lambda$Fn128;
  
  static final ModuleMethod lambda$Fn133;
  
  static final ModuleMethod lambda$Fn138;
  
  static final ModuleMethod lambda$Fn163;
  
  static final ModuleMethod lambda$Fn166;
  
  static final ModuleMethod lambda$Fn17;
  
  static final ModuleMethod lambda$Fn18;
  
  static final ModuleMethod lambda$Fn210;
  
  static final ModuleMethod lambda$Fn216;
  
  static final ModuleMethod lambda$Fn220;
  
  static final ModuleMethod lambda$Fn27;
  
  static final ModuleMethod lambda$Fn5;
  
  static final ModuleMethod lambda$Fn72;
  
  static final ModuleMethod lambda$Fn73;
  
  static final ModuleMethod lambda$Fn78;
  
  static final ModuleMethod lambda$Fn83;
  
  static final ModuleMethod lambda$Fn84;
  
  static final ModuleMethod lambda$Fn89;
  
  static final ModuleMethod lambda$Fn90;
  
  static final ModuleMethod lambda$Fn95;
  
  public static final Macro let$Mnstring$Mnstart$Plend;
  
  public static final Macro let$Mnstring$Mnstart$Plend2;
  
  static final Location loc$$Cloptional;
  
  static final Location loc$base;
  
  static final Location loc$bound;
  
  static final Location loc$c$Eq;
  
  static final Location loc$char$Mncased$Qu;
  
  static final Location loc$char$Mnset;
  
  static final Location loc$char$Mnset$Mncontains$Qu;
  
  static final Location loc$char$Mnset$Qu;
  
  static final Location loc$check$Mnarg;
  
  static final Location loc$criterion;
  
  static final Location loc$delim;
  
  static final Location loc$end;
  
  static final Location loc$final;
  
  static final Location loc$grammar;
  
  static final Location loc$let$Mnoptionals$St;
  
  static final Location loc$make$Mnfinal;
  
  static final Location loc$p$Mnstart;
  
  static final Location loc$rest;
  
  static final Location loc$s$Mnend;
  
  static final Location loc$s$Mnstart;
  
  static final Location loc$start;
  
  static final Location loc$token$Mnchars;
  
  public static final ModuleMethod make$Mnkmp$Mnrestart$Mnvector;
  
  public static final ModuleMethod reverse$Mnlist$Mn$Grstring;
  
  public static final ModuleMethod string$Eq;
  
  public static final ModuleMethod string$Gr;
  
  public static final ModuleMethod string$Gr$Eq;
  
  public static final ModuleMethod string$Ls;
  
  public static final ModuleMethod string$Ls$Eq;
  
  public static final ModuleMethod string$Ls$Gr;
  
  public static final ModuleMethod string$Mn$Grlist;
  
  public static final ModuleMethod string$Mnany;
  
  public static final ModuleMethod string$Mnappend$Slshared;
  
  public static final ModuleMethod string$Mnci$Eq;
  
  public static final ModuleMethod string$Mnci$Gr;
  
  public static final ModuleMethod string$Mnci$Gr$Eq;
  
  public static final ModuleMethod string$Mnci$Ls;
  
  public static final ModuleMethod string$Mnci$Ls$Eq;
  
  public static final ModuleMethod string$Mnci$Ls$Gr;
  
  public static final ModuleMethod string$Mncompare;
  
  public static final ModuleMethod string$Mncompare$Mnci;
  
  public static final ModuleMethod string$Mnconcatenate;
  
  public static final ModuleMethod string$Mnconcatenate$Mnreverse;
  
  public static final ModuleMethod string$Mnconcatenate$Mnreverse$Slshared;
  
  public static final ModuleMethod string$Mnconcatenate$Slshared;
  
  public static final ModuleMethod string$Mncontains;
  
  public static final ModuleMethod string$Mncontains$Mnci;
  
  public static final ModuleMethod string$Mncopy;
  
  public static final ModuleMethod string$Mncopy$Ex;
  
  public static final ModuleMethod string$Mncount;
  
  public static final ModuleMethod string$Mndelete;
  
  public static final ModuleMethod string$Mndowncase;
  
  public static final ModuleMethod string$Mndowncase$Ex;
  
  public static final ModuleMethod string$Mndrop;
  
  public static final ModuleMethod string$Mndrop$Mnright;
  
  public static final ModuleMethod string$Mnevery;
  
  public static final ModuleMethod string$Mnfill$Ex;
  
  public static final ModuleMethod string$Mnfilter;
  
  public static final ModuleMethod string$Mnfold;
  
  public static final ModuleMethod string$Mnfold$Mnright;
  
  public static final ModuleMethod string$Mnfor$Mneach;
  
  public static final ModuleMethod string$Mnfor$Mneach$Mnindex;
  
  public static final ModuleMethod string$Mnhash;
  
  public static final ModuleMethod string$Mnhash$Mnci;
  
  public static final ModuleMethod string$Mnindex;
  
  public static final ModuleMethod string$Mnindex$Mnright;
  
  public static final ModuleMethod string$Mnjoin;
  
  public static final ModuleMethod string$Mnkmp$Mnpartial$Mnsearch;
  
  public static final ModuleMethod string$Mnmap;
  
  public static final ModuleMethod string$Mnmap$Ex;
  
  public static final ModuleMethod string$Mnnull$Qu;
  
  public static final ModuleMethod string$Mnpad;
  
  public static final ModuleMethod string$Mnpad$Mnright;
  
  public static final ModuleMethod string$Mnparse$Mnfinal$Mnstart$Plend;
  
  public static final ModuleMethod string$Mnparse$Mnstart$Plend;
  
  public static final ModuleMethod string$Mnprefix$Mnci$Qu;
  
  public static final ModuleMethod string$Mnprefix$Mnlength;
  
  public static final ModuleMethod string$Mnprefix$Mnlength$Mnci;
  
  public static final ModuleMethod string$Mnprefix$Qu;
  
  public static final ModuleMethod string$Mnreplace;
  
  public static final ModuleMethod string$Mnreverse;
  
  public static final ModuleMethod string$Mnreverse$Ex;
  
  public static final ModuleMethod string$Mnskip;
  
  public static final ModuleMethod string$Mnskip$Mnright;
  
  public static final ModuleMethod string$Mnsuffix$Mnci$Qu;
  
  public static final ModuleMethod string$Mnsuffix$Mnlength;
  
  public static final ModuleMethod string$Mnsuffix$Mnlength$Mnci;
  
  public static final ModuleMethod string$Mnsuffix$Qu;
  
  public static final ModuleMethod string$Mntabulate;
  
  public static final ModuleMethod string$Mntake;
  
  public static final ModuleMethod string$Mntake$Mnright;
  
  public static final ModuleMethod string$Mntitlecase;
  
  public static final ModuleMethod string$Mntitlecase$Ex;
  
  public static final ModuleMethod string$Mntokenize;
  
  public static final ModuleMethod string$Mntrim;
  
  public static final ModuleMethod string$Mntrim$Mnboth;
  
  public static final ModuleMethod string$Mntrim$Mnright;
  
  public static final ModuleMethod string$Mnunfold;
  
  public static final ModuleMethod string$Mnunfold$Mnright;
  
  public static final ModuleMethod string$Mnupcase;
  
  public static final ModuleMethod string$Mnupcase$Ex;
  
  public static final ModuleMethod string$Mnxcopy$Ex;
  
  public static final ModuleMethod substring$Mnspec$Mnok$Qu;
  
  public static final ModuleMethod substring$Slshared;
  
  public static final ModuleMethod xsubstring;
  
  public static Object $PcCheckBounds(Object paramObject, CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    return (paramInt1 < 0) ? misc.error$V("Illegal substring START spec", new Object[] { paramObject, Integer.valueOf(paramInt1), paramCharSequence }) : ((paramInt1 > paramInt2) ? misc.error$V("Illegal substring START/END spec", new Object[0]) : ((paramInt2 > strings.stringLength(paramCharSequence)) ? misc.error$V("Illegal substring END spec", new Object[] { paramObject, Integer.valueOf(paramInt2), paramCharSequence }) : Values.empty));
  }
  
  static Object $PcCheckSubstringSpec(Object paramObject, CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    boolean bool;
    if (paramInt1 < 0) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      if (bool)
        return misc.error$V("Illegal substring spec.", new Object[] { paramObject, paramCharSequence, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }); 
    } else {
      if (paramInt1 > paramInt2) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool ? !bool : (paramInt2 <= strings.stringLength(paramCharSequence)))
        return misc.error$V("Illegal substring spec.", new Object[] { paramObject, paramCharSequence, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }); 
    } 
    return Values.empty;
  }
  
  public static Object $PcFinishStringConcatenateReverse(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    Object object = AddOp.$Pl.apply2(paramObject4, paramObject1);
    try {
      int i = ((Number)object).intValue();
      object = strings.makeString(i);
      try {
        i = ((Number)paramObject1).intValue();
        try {
          CharSequence charSequence = (CharSequence)paramObject3;
          try {
            int j = ((Number)paramObject4).intValue();
            $PcStringCopy$Ex((CharSequence)object, i, charSequence, 0, j);
            while (lists.isPair(paramObject2)) {
              paramObject3 = lists.car.apply1(paramObject2);
              paramObject2 = lists.cdr.apply1(paramObject2);
              try {
                paramObject4 = paramObject3;
                i = strings.stringLength((CharSequence)paramObject4);
                paramObject1 = AddOp.$Mn.apply2(paramObject1, Integer.valueOf(i));
                try {
                  j = ((Number)paramObject1).intValue();
                  try {
                    paramObject4 = paramObject3;
                    $PcStringCopy$Ex((CharSequence)object, j, (CharSequence)paramObject4, 0, i);
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException, "%string-copy!", 2, paramObject3);
                  } 
                } catch (ClassCastException classCastException1) {
                  throw new WrongType(classCastException1, "%string-copy!", 1, classCastException);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException, "string-length", 1, paramObject3);
              } 
            } 
          } catch (ClassCastException null) {
            throw new WrongType(classCastException, "%string-copy!", 4, paramObject4);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%string-copy!", 2, paramObject3);
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "%string-copy!", 1, classCastException);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "make-string", 1, object);
    } 
  }
  
  public static Object $PcKmpSearch(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7) {
    Object object2 = AddOp.$Mn.apply2(paramObject5, paramObject4);
    Object object3 = makeKmpRestartVector$V(paramObject1, new Object[] { paramObject3, paramObject4, paramObject5 });
    IntNum intNum = Lit0;
    paramObject7 = AddOp.$Mn.apply2(paramObject7, paramObject6);
    Object object1 = paramObject6;
    paramObject5 = object2;
    paramObject6 = intNum;
    while (true) {
      if (Scheme.numEqu.apply2(paramObject6, object2) != Boolean.FALSE)
        return AddOp.$Mn.apply2(object1, object2); 
      Object object = Scheme.numLEq.apply2(paramObject5, paramObject7);
      try {
        boolean bool = ((Boolean)object).booleanValue();
        if (bool) {
          object = Scheme.applyToArgs;
          try {
            CharSequence charSequence = (CharSequence)paramObject2;
            try {
              int i = ((Number)object1).intValue();
              Char char_ = Char.make(strings.stringRef(charSequence, i));
              try {
                CharSequence charSequence1 = (CharSequence)paramObject1;
                Object object4 = AddOp.$Pl.apply2(paramObject4, paramObject6);
                try {
                  i = ((Number)object4).intValue();
                  if (object.apply3(paramObject3, char_, Char.make(strings.stringRef(charSequence1, i))) != Boolean.FALSE) {
                    object1 = AddOp.$Pl.apply2(Lit1, object1);
                    paramObject6 = AddOp.$Pl.apply2(Lit1, paramObject6);
                    paramObject7 = AddOp.$Mn.apply2(paramObject7, Lit1);
                    paramObject5 = AddOp.$Mn.apply2(paramObject5, Lit1);
                    continue;
                  } 
                  try {
                    paramObject5 = object3;
                    try {
                      i = ((Number)paramObject6).intValue();
                      paramObject6 = vectors.vectorRef((FVector)paramObject5, i);
                      if (Scheme.numEqu.apply2(paramObject6, Lit13) != Boolean.FALSE) {
                        object1 = AddOp.$Pl.apply2(object1, Lit1);
                        paramObject6 = Lit0;
                        paramObject7 = AddOp.$Mn.apply2(paramObject7, Lit1);
                        paramObject5 = object2;
                        continue;
                      } 
                      paramObject5 = AddOp.$Mn.apply2(object2, paramObject6);
                    } catch (ClassCastException null) {
                      throw new WrongType(classCastException1, "vector-ref", 2, paramObject6);
                    } 
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException1, "vector-ref", 1, object3);
                  } 
                } catch (ClassCastException classCastException1) {
                  throw new WrongType(classCastException1, "string-ref", 2, object4);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 1, classCastException1);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string-ref", 2, object1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-ref", 1, classCastException);
          } 
        } 
        return bool ? Boolean.TRUE : Boolean.FALSE;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "x", -2, object);
      } 
    } 
  }
  
  public static Object $PcMultispanRepcopy$Ex(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7) {
    Object object1 = AddOp.$Mn.apply2(paramObject7, paramObject6);
    Object object3 = AddOp.$Pl.apply2(paramObject6, DivideOp.modulo.apply2(paramObject4, object1));
    Object object2 = AddOp.$Mn.apply2(paramObject5, paramObject4);
    try {
      paramObject4 = paramObject1;
      try {
        int i = ((Number)paramObject2).intValue();
        try {
          paramObject5 = paramObject3;
          try {
            int j = ((Number)object3).intValue();
            try {
              int k = ((Number)paramObject7).intValue();
              $PcStringCopy$Ex((CharSequence)paramObject4, i, (CharSequence)paramObject5, j, k);
              paramObject4 = AddOp.$Mn.apply2(paramObject7, object3);
              paramObject5 = AddOp.$Mn.apply2(object2, paramObject4);
              paramObject5 = DivideOp.quotient.apply2(paramObject5, object1);
              paramObject4 = AddOp.$Pl.apply2(paramObject2, paramObject4);
              try {
                while (true) {
                  object3 = paramObject5;
                  if (!numbers.isZero((Number)object3))
                    try {
                      object3 = paramObject1;
                      try {
                        i = ((Number)paramObject4).intValue();
                        try {
                          CharSequence charSequence = (CharSequence)paramObject3;
                          try {
                            j = ((Number)paramObject6).intValue();
                            try {
                              k = ((Number)paramObject7).intValue();
                              $PcStringCopy$Ex((CharSequence)object3, i, charSequence, j, k);
                              paramObject4 = AddOp.$Pl.apply2(paramObject4, object1);
                              paramObject5 = AddOp.$Mn.apply2(paramObject5, Lit1);
                            } catch (ClassCastException null) {
                              throw new WrongType(classCastException, "%string-copy!", 4, paramObject7);
                            } 
                          } catch (ClassCastException null) {
                            throw new WrongType(classCastException, "%string-copy!", 3, paramObject6);
                          } 
                        } catch (ClassCastException null) {
                          throw new WrongType(classCastException, "%string-copy!", 2, paramObject3);
                        } 
                      } catch (ClassCastException null) {
                        throw new WrongType(classCastException, "%string-copy!", 1, paramObject4);
                      } 
                    } catch (ClassCastException null) {
                      throw new WrongType(classCastException1, "%string-copy!", 0, classCastException);
                    }  
                  try {
                    paramObject5 = classCastException;
                    try {
                      i = ((Number)paramObject4).intValue();
                      try {
                        CharSequence charSequence = (CharSequence)paramObject3;
                        try {
                          j = ((Number)paramObject6).intValue();
                          Object object = AddOp.$Pl.apply2(paramObject6, AddOp.$Mn.apply2(object2, AddOp.$Mn.apply2(paramObject4, classCastException1)));
                          try {
                            k = ((Number)object).intValue();
                            return $PcStringCopy$Ex((CharSequence)paramObject5, i, charSequence, j, k);
                          } catch (ClassCastException classCastException2) {
                            throw new WrongType(classCastException2, "%string-copy!", 4, object);
                          } 
                        } catch (ClassCastException classCastException2) {
                          throw new WrongType(classCastException2, "%string-copy!", 3, paramObject6);
                        } 
                      } catch (ClassCastException classCastException2) {
                        throw new WrongType(classCastException2, "%string-copy!", 2, paramObject3);
                      } 
                    } catch (ClassCastException null) {
                      throw new WrongType(classCastException, "%string-copy!", 1, paramObject4);
                    } 
                  } catch (ClassCastException classCastException1) {
                    throw new WrongType(classCastException1, "%string-copy!", 0, classCastException);
                  } 
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException, "zero?", 1, paramObject5);
              } 
            } catch (ClassCastException null) {
              throw new WrongType(classCastException, "%string-copy!", 4, paramObject7);
            } 
          } catch (ClassCastException null) {
            throw new WrongType(classCastException, "%string-copy!", 3, object3);
          } 
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "%string-copy!", 2, paramObject3);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "%string-copy!", 1, classCastException1);
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "%string-copy!", 0, classCastException);
    } 
  }
  
  public static Object $PcStringCompare(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8, Object paramObject9) {
    Object object2 = AddOp.$Mn.apply2(paramObject3, paramObject2);
    Object object1 = AddOp.$Mn.apply2(paramObject6, paramObject5);
    paramObject6 = $PcStringPrefixLength(paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6);
    if (Scheme.numEqu.apply2(paramObject6, object2) != Boolean.FALSE) {
      paramObject1 = Scheme.applyToArgs;
      if (Scheme.numEqu.apply2(paramObject6, object1) == Boolean.FALSE)
        paramObject8 = paramObject7; 
      return paramObject1.apply2(paramObject8, paramObject3);
    } 
    paramObject3 = Scheme.applyToArgs;
    if (Scheme.numEqu.apply2(paramObject6, object1) == Boolean.FALSE)
      try {
        paramObject8 = paramObject1;
        paramObject1 = AddOp.$Pl.apply2(paramObject2, paramObject6);
        try {
          int i = ((Number)paramObject1).intValue();
          paramObject1 = Char.make(strings.stringRef((CharSequence)paramObject8, i));
          try {
            paramObject8 = paramObject4;
            paramObject4 = AddOp.$Pl.apply2(paramObject5, paramObject6);
            try {
              i = ((Number)paramObject4).intValue();
              if (characters.isChar$Ls((Char)paramObject1, Char.make(strings.stringRef((CharSequence)paramObject8, i))))
                paramObject9 = paramObject7; 
            } catch (ClassCastException null) {
              throw new WrongType(classCastException1, "string-ref", 2, paramObject4);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-ref", 1, paramObject4);
          } 
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "string-ref", 2, classCastException1);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-ref", 1, classCastException1);
      }  
    return paramObject3.apply2(paramObject9, AddOp.$Pl.apply2(paramObject6, classCastException));
  }
  
  public static Object $PcStringCompareCi(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8, Object paramObject9) {
    Object object2 = AddOp.$Mn.apply2(paramObject3, paramObject2);
    Object object1 = AddOp.$Mn.apply2(paramObject6, paramObject5);
    try {
      int i = ((Number)paramObject2).intValue();
      try {
        int j = ((Number)paramObject3).intValue();
        try {
          int k = ((Number)paramObject5).intValue();
          try {
            int m = ((Number)paramObject6).intValue();
            i = $PcStringPrefixLengthCi(paramObject1, i, j, paramObject4, k, m);
            if (Scheme.numEqu.apply2(Integer.valueOf(i), object2) != Boolean.FALSE) {
              paramObject1 = Scheme.applyToArgs;
              if (Scheme.numEqu.apply2(Integer.valueOf(i), object1) == Boolean.FALSE)
                paramObject8 = paramObject7; 
              return paramObject1.apply2(paramObject8, paramObject3);
            } 
            paramObject3 = Scheme.applyToArgs;
            if (Scheme.numEqu.apply2(Integer.valueOf(i), object1) == Boolean.FALSE)
              try {
                paramObject6 = paramObject1;
                paramObject1 = AddOp.$Pl.apply2(paramObject2, Integer.valueOf(i));
                try {
                  j = ((Number)paramObject1).intValue();
                  paramObject1 = Char.make(strings.stringRef((CharSequence)paramObject6, j));
                  try {
                    paramObject6 = paramObject4;
                    paramObject4 = AddOp.$Pl.apply2(paramObject5, Integer.valueOf(i));
                    try {
                      j = ((Number)paramObject4).intValue();
                      if (unicode.isCharCi$Ls((Char)paramObject1, Char.make(strings.stringRef((CharSequence)paramObject6, j))))
                        paramObject9 = paramObject7; 
                    } catch (ClassCastException null) {
                      throw new WrongType(classCastException1, "string-ref", 2, paramObject4);
                    } 
                  } catch (ClassCastException classCastException1) {
                    throw new WrongType(classCastException1, "string-ref", 1, paramObject4);
                  } 
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException, "string-ref", 2, classCastException1);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 1, classCastException1);
              }  
            return paramObject3.apply2(paramObject9, AddOp.$Pl.apply2(classCastException, Integer.valueOf(i)));
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "%string-prefix-length-ci", 5, paramObject6);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "%string-prefix-length-ci", 4, paramObject5);
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "%string-prefix-length-ci", 2, paramObject3);
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "%string-prefix-length-ci", 1, classCastException);
    } 
  }
  
  public static Object $PcStringCopy$Ex(CharSequence paramCharSequence1, int paramInt1, CharSequence paramCharSequence2, int paramInt2, int paramInt3) {
    if (paramInt2 > paramInt1)
      while (true) {
        if (paramInt2 < paramInt3) {
          try {
            CharSeq charSeq = (CharSeq)paramCharSequence1;
            strings.stringSet$Ex(charSeq, paramInt1, strings.stringRef(paramCharSequence2, paramInt2));
            paramInt1++;
            paramInt2++;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-set!", 1, paramCharSequence1);
          } 
          continue;
        } 
        return Values.empty;
      }  
    paramInt1 = paramInt1 - 1 + paramInt3 - paramInt2;
    int i = paramInt3 - 1;
    paramInt3 = paramInt1;
    paramInt1 = i;
    while (true) {
      if (paramInt1 >= paramInt2) {
        try {
          CharSeq charSeq = (CharSeq)paramCharSequence1;
          strings.stringSet$Ex(charSeq, paramInt3, strings.stringRef((CharSequence)classCastException, paramInt1));
          paramInt3--;
          paramInt1--;
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "string-set!", 1, paramCharSequence1);
        } 
        continue;
      } 
      return Values.empty;
    } 
  }
  
  public static Object $PcStringHash(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5) {
    // Byte code:
    //   0: new gnu/kawa/slib/srfi13$frame55
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #7
    //   9: aload #7
    //   11: aload_1
    //   12: putfield char$Mn$Grint : Ljava/lang/Object;
    //   15: getstatic gnu/kawa/slib/srfi13.Lit5 : Lgnu/math/IntNum;
    //   18: astore_1
    //   19: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
    //   22: aload_1
    //   23: aload_2
    //   24: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   27: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   30: if_acmpeq -> 79
    //   33: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
    //   36: aload_1
    //   37: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
    //   40: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   43: astore #8
    //   45: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   48: astore #6
    //   50: aload_3
    //   51: astore_1
    //   52: aload #6
    //   54: astore_3
    //   55: getstatic kawa/standard/Scheme.numGEq : Lgnu/kawa/functions/NumberCompare;
    //   58: aload_1
    //   59: aload #4
    //   61: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   64: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   67: if_acmpeq -> 91
    //   70: getstatic gnu/kawa/functions/DivideOp.modulo : Lgnu/kawa/functions/DivideOp;
    //   73: aload_3
    //   74: aload_2
    //   75: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   78: areturn
    //   79: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   82: aload_1
    //   83: aload_1
    //   84: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   87: astore_1
    //   88: goto -> 19
    //   91: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   94: aload_1
    //   95: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
    //   98: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   101: astore #6
    //   103: getstatic gnu/kawa/functions/BitwiseOp.and : Lgnu/kawa/functions/BitwiseOp;
    //   106: astore #9
    //   108: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   111: astore #10
    //   113: getstatic gnu/kawa/functions/MultiplyOp.$St : Lgnu/kawa/functions/MultiplyOp;
    //   116: getstatic gnu/kawa/slib/srfi13.Lit6 : Lgnu/math/IntNum;
    //   119: aload_3
    //   120: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   123: astore_3
    //   124: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   127: astore #11
    //   129: aload #7
    //   131: getfield char$Mn$Grint : Ljava/lang/Object;
    //   134: astore #12
    //   136: aload_0
    //   137: checkcast java/lang/CharSequence
    //   140: astore #13
    //   142: aload_1
    //   143: checkcast java/lang/Number
    //   146: invokevirtual intValue : ()I
    //   149: istore #5
    //   151: aload #9
    //   153: aload #8
    //   155: aload #10
    //   157: aload_3
    //   158: aload #11
    //   160: aload #12
    //   162: aload #13
    //   164: iload #5
    //   166: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   169: invokestatic make : (I)Lgnu/text/Char;
    //   172: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   175: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   178: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   181: astore_3
    //   182: aload #6
    //   184: astore_1
    //   185: goto -> 55
    //   188: astore_1
    //   189: new gnu/mapping/WrongType
    //   192: dup
    //   193: aload_1
    //   194: ldc_w 'string-ref'
    //   197: iconst_1
    //   198: aload_0
    //   199: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   202: athrow
    //   203: astore_0
    //   204: new gnu/mapping/WrongType
    //   207: dup
    //   208: aload_0
    //   209: ldc_w 'string-ref'
    //   212: iconst_2
    //   213: aload_1
    //   214: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   217: athrow
    // Exception table:
    //   from	to	target	type
    //   136	142	188	java/lang/ClassCastException
    //   142	151	203	java/lang/ClassCastException
  }
  
  public static Object $PcStringMap(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    CharSequence charSequence;
    paramObject3 = AddOp.$Mn.apply2(paramObject4, paramObject3);
    try {
      int i = ((Number)paramObject3).intValue();
      charSequence = strings.makeString(i);
      paramObject4 = AddOp.$Mn.apply2(paramObject4, Lit1);
      paramObject3 = AddOp.$Mn.apply2(paramObject3, Lit1);
      while (Scheme.numLss.apply2(paramObject3, Lit0) == Boolean.FALSE) {
        try {
          CharSeq charSeq = (CharSeq)charSequence;
          try {
            i = ((Number)paramObject3).intValue();
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            try {
              CharSequence charSequence1 = (CharSequence)paramObject2;
              try {
                int j = ((Number)paramObject4).intValue();
                Object object = applyToArgs.apply2(paramObject1, Char.make(strings.stringRef(charSequence1, j)));
                try {
                  char c = ((Char)object).charValue();
                  strings.stringSet$Ex(charSeq, i, c);
                  paramObject4 = AddOp.$Mn.apply2(paramObject4, Lit1);
                  paramObject3 = AddOp.$Mn.apply2(paramObject3, Lit1);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string-set!", 3, object);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 2, paramObject4);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 1, paramObject2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-set!", 2, paramObject3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-set!", 1, charSequence);
        } 
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "make-string", 1, paramObject3);
    } 
    return charSequence;
  }
  
  public static Object $PcStringMap$Ex(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    paramObject4 = AddOp.$Mn.apply2(paramObject4, Lit1);
    while (true) {
      if (Scheme.numLss.apply2(paramObject4, paramObject3) == Boolean.FALSE)
        try {
          CharSeq charSeq = (CharSeq)paramObject2;
          try {
            int i = ((Number)paramObject4).intValue();
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            try {
              CharSequence charSequence = (CharSequence)paramObject2;
              try {
                int j = ((Number)paramObject4).intValue();
                Object object = applyToArgs.apply2(paramObject1, Char.make(strings.stringRef(charSequence, j)));
                try {
                  char c = ((Char)object).charValue();
                  strings.stringSet$Ex(charSeq, i, c);
                  paramObject4 = AddOp.$Mn.apply2(paramObject4, Lit1);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string-set!", 3, object);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-ref", 2, paramObject4);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 1, paramObject2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-set!", 2, paramObject4);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-set!", 1, paramObject2);
        }  
      return Values.empty;
    } 
  }
  
  public static Object $PcStringPrefix$Qu(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6) {
    Object object2 = AddOp.$Mn.apply2(paramObject3, paramObject2);
    Object object1 = Scheme.numLEq.apply2(object2, AddOp.$Mn.apply2(paramObject6, paramObject5));
    try {
      boolean bool = ((Boolean)object1).booleanValue();
      return bool ? Scheme.numEqu.apply2($PcStringPrefixLength(paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6), object2) : (bool ? Boolean.TRUE : Boolean.FALSE);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "x", -2, object1);
    } 
  }
  
  public static Object $PcStringPrefixCi$Qu(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6) {
    Object object1 = AddOp.$Mn.apply2(paramObject3, paramObject2);
    Object object2 = Scheme.numLEq.apply2(object1, AddOp.$Mn.apply2(paramObject6, paramObject5));
    try {
      boolean bool = ((Boolean)object2).booleanValue();
      if (bool) {
        object2 = Scheme.numEqu;
        try {
          int i = ((Number)paramObject2).intValue();
          try {
            int j = ((Number)paramObject3).intValue();
            try {
              int k = ((Number)paramObject5).intValue();
              try {
                int m = ((Number)paramObject6).intValue();
                return object2.apply2(object1, Integer.valueOf($PcStringPrefixLengthCi(paramObject1, i, j, paramObject4, k, m)));
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "%string-prefix-length-ci", 5, paramObject6);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%string-prefix-length-ci", 4, paramObject5);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%string-prefix-length-ci", 2, paramObject3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%string-prefix-length-ci", 1, paramObject2);
        } 
      } 
      return bool ? Boolean.TRUE : Boolean.FALSE;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "x", -2, object2);
    } 
  }
  
  public static Object $PcStringPrefixLength(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6) {
    int i = 0;
    paramObject3 = numbers.min(new Object[] { AddOp.$Mn.apply2(paramObject3, paramObject2), AddOp.$Mn.apply2(paramObject6, paramObject5) });
    Object object = AddOp.$Pl.apply2(paramObject2, paramObject3);
    if (paramObject1 == paramObject4)
      i = 1; 
    if (i ? (Scheme.numEqu.apply2(paramObject2, paramObject5) != Boolean.FALSE) : i)
      return paramObject3; 
    paramObject6 = paramObject2;
    paramObject3 = paramObject5;
    paramObject5 = paramObject6;
    while (true) {
      paramObject6 = Scheme.numGEq.apply2(paramObject5, object);
      try {
        boolean bool = ((Boolean)paramObject6).booleanValue();
        if (bool) {
          if (bool)
            return AddOp.$Mn.apply2(paramObject5, paramObject2); 
        } else {
          try {
            paramObject6 = paramObject1;
            try {
              i = ((Number)paramObject5).intValue();
              paramObject6 = Char.make(strings.stringRef((CharSequence)paramObject6, i));
              try {
                CharSequence charSequence = (CharSequence)paramObject4;
                try {
                  i = ((Number)paramObject3).intValue();
                  if (!characters.isChar$Eq((Char)paramObject6, Char.make(strings.stringRef(charSequence, i))))
                    return AddOp.$Mn.apply2(paramObject5, paramObject2); 
                  paramObject5 = AddOp.$Pl.apply2(paramObject5, Lit1);
                  paramObject3 = AddOp.$Pl.apply2(paramObject3, Lit1);
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException, "string-ref", 2, paramObject3);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException, "string-ref", 1, paramObject4);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 2, paramObject5);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-ref", 1, classCastException);
          } 
        } 
        paramObject5 = AddOp.$Pl.apply2(paramObject5, Lit1);
        paramObject3 = AddOp.$Pl.apply2(paramObject3, Lit1);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "x", -2, paramObject6);
      } 
    } 
  }
  
  public static int $PcStringPrefixLengthCi(Object paramObject1, int paramInt1, int paramInt2, Object paramObject2, int paramInt3, int paramInt4) {
    Object object = numbers.min(new Object[] { Integer.valueOf(paramInt2 - paramInt1), Integer.valueOf(paramInt4 - paramInt3) });
    try {
      int i = ((Number)object).intValue();
      if (paramObject1 == paramObject2) {
        paramInt2 = 1;
      } else {
        paramInt2 = 0;
      } 
      if ((paramInt2 != 0) ? (paramInt1 == paramInt3) : (paramInt2 != 0))
        return i; 
      paramInt2 = paramInt1;
      while (true) {
        if (paramInt2 >= paramInt1 + i) {
          paramInt4 = 1;
        } else {
          paramInt4 = 0;
        } 
        if (paramInt4 != 0) {
          if (paramInt4 != 0)
            return paramInt2 - paramInt1; 
        } else {
          try {
            object = paramObject1;
            object = Char.make(strings.stringRef((CharSequence)object, paramInt2));
            try {
              CharSequence charSequence = (CharSequence)paramObject2;
              if (!unicode.isCharCi$Eq((Char)object, Char.make(strings.stringRef(charSequence, paramInt3))))
                return paramInt2 - paramInt1; 
              paramInt2++;
              paramInt3++;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 1, paramObject2);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-ref", 1, classCastException);
          } 
        } 
        paramInt2++;
        paramInt3++;
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "delta", -2, object);
    } 
  }
  
  public static Object $PcStringSuffix$Qu(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6) {
    Object object2 = AddOp.$Mn.apply2(paramObject3, paramObject2);
    Object object1 = Scheme.numLEq.apply2(object2, AddOp.$Mn.apply2(paramObject6, paramObject5));
    try {
      boolean bool = ((Boolean)object1).booleanValue();
      return bool ? Scheme.numEqu.apply2(object2, $PcStringSuffixLength(paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6)) : (bool ? Boolean.TRUE : Boolean.FALSE);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "x", -2, object1);
    } 
  }
  
  public static Object $PcStringSuffixCi$Qu(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6) {
    Object object1 = AddOp.$Mn.apply2(paramObject3, paramObject2);
    Object object2 = Scheme.numLEq.apply2(object1, AddOp.$Mn.apply2(paramObject6, paramObject5));
    try {
      boolean bool = ((Boolean)object2).booleanValue();
      if (bool) {
        object2 = Scheme.numEqu;
        try {
          int i = ((Number)paramObject2).intValue();
          try {
            int j = ((Number)paramObject3).intValue();
            try {
              int k = ((Number)paramObject5).intValue();
              try {
                int m = ((Number)paramObject6).intValue();
                return object2.apply2(object1, Integer.valueOf($PcStringSuffixLengthCi(paramObject1, i, j, paramObject4, k, m)));
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "%string-suffix-length-ci", 5, paramObject6);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%string-suffix-length-ci", 4, paramObject5);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%string-suffix-length-ci", 2, paramObject3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%string-suffix-length-ci", 1, paramObject2);
        } 
      } 
      return bool ? Boolean.TRUE : Boolean.FALSE;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "x", -2, object2);
    } 
  }
  
  public static Object $PcStringSuffixLength(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6) {
    int i = 0;
    paramObject2 = numbers.min(new Object[] { AddOp.$Mn.apply2(paramObject3, paramObject2), AddOp.$Mn.apply2(paramObject6, paramObject5) });
    Object object = AddOp.$Mn.apply2(paramObject3, paramObject2);
    if (paramObject1 == paramObject4)
      i = 1; 
    if (i ? (Scheme.numEqu.apply2(paramObject3, paramObject6) != Boolean.FALSE) : i)
      return paramObject2; 
    paramObject5 = AddOp.$Mn.apply2(paramObject3, Lit1);
    paramObject2 = AddOp.$Mn.apply2(paramObject6, Lit1);
    while (true) {
      paramObject6 = Scheme.numLss.apply2(paramObject5, object);
      try {
        boolean bool = ((Boolean)paramObject6).booleanValue();
        if (bool) {
          if (bool)
            return AddOp.$Mn.apply2(AddOp.$Mn.apply2(paramObject3, paramObject5), Lit1); 
        } else {
          try {
            paramObject6 = paramObject1;
            try {
              i = ((Number)paramObject5).intValue();
              paramObject6 = Char.make(strings.stringRef((CharSequence)paramObject6, i));
              try {
                CharSequence charSequence = (CharSequence)paramObject4;
                try {
                  i = ((Number)paramObject2).intValue();
                  if (!characters.isChar$Eq((Char)paramObject6, Char.make(strings.stringRef(charSequence, i))))
                    return AddOp.$Mn.apply2(AddOp.$Mn.apply2(paramObject3, paramObject5), Lit1); 
                  paramObject5 = AddOp.$Mn.apply2(paramObject5, Lit1);
                  paramObject2 = AddOp.$Mn.apply2(paramObject2, Lit1);
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException1, "string-ref", 2, paramObject2);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException1, "string-ref", 1, paramObject4);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string-ref", 2, paramObject5);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 1, classCastException1);
          } 
        } 
        paramObject5 = AddOp.$Mn.apply2(paramObject5, Lit1);
        Object object1 = AddOp.$Mn.apply2(classCastException, Lit1);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "x", -2, paramObject6);
      } 
    } 
  }
  
  public static int $PcStringSuffixLengthCi(Object paramObject1, int paramInt1, int paramInt2, Object paramObject2, int paramInt3, int paramInt4) {
    Object object = numbers.min(new Object[] { Integer.valueOf(paramInt2 - paramInt1), Integer.valueOf(paramInt4 - paramInt3) });
    try {
      int i = ((Number)object).intValue();
      if (paramObject1 == paramObject2) {
        paramInt1 = 1;
      } else {
        paramInt1 = 0;
      } 
      if ((paramInt1 != 0) ? (paramInt2 == paramInt4) : (paramInt1 != 0))
        return i; 
      paramInt1 = paramInt2 - 1;
      paramInt3 = paramInt4 - 1;
      while (true) {
        if (paramInt1 < paramInt2 - i) {
          paramInt4 = 1;
        } else {
          paramInt4 = 0;
        } 
        if (paramInt4 != 0) {
          if (paramInt4 != 0)
            return paramInt2 - paramInt1 - 1; 
        } else {
          try {
            object = paramObject1;
            object = Char.make(strings.stringRef((CharSequence)object, paramInt1));
            try {
              CharSequence charSequence = (CharSequence)paramObject2;
              if (!unicode.isCharCi$Eq((Char)object, Char.make(strings.stringRef(charSequence, paramInt3))))
                return paramInt2 - paramInt1 - 1; 
              paramInt3--;
              paramInt1--;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 1, paramObject2);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-ref", 1, classCastException);
          } 
        } 
        paramInt3--;
        paramInt1--;
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "delta", -2, object);
    } 
  }
  
  public static Object $PcStringTitlecase$Ex(Object paramObject1, Object paramObject2, Object paramObject3) {
    while (true) {
      Location location = loc$char$Mncased$Qu;
      try {
        Object object = location.get();
        paramObject2 = stringIndex$V(paramObject1, object, new Object[] { paramObject2, paramObject3 });
        if (paramObject2 != Boolean.FALSE)
          try {
            object = paramObject1;
            try {
              int i = ((Number)paramObject2).intValue();
              try {
                CharSequence charSequence = (CharSequence)paramObject1;
                try {
                  int j = ((Number)paramObject2).intValue();
                  Char char_ = unicode.charTitlecase(Char.make(strings.stringRef(charSequence, j)));
                  try {
                    char c = char_.charValue();
                    strings.stringSet$Ex((CharSeq)object, i, c);
                    paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit1);
                    object = loc$char$Mncased$Qu;
                    try {
                      object = object.get();
                      object = stringSkip$V(paramObject1, object, new Object[] { paramObject2, paramObject3 });
                      if (object != Boolean.FALSE) {
                        stringDowncase$Ex$V(paramObject1, new Object[] { paramObject2, object });
                        paramObject2 = AddOp.$Pl.apply2(object, Lit1);
                        continue;
                      } 
                      return stringDowncase$Ex$V(paramObject1, new Object[] { paramObject2, paramObject3 });
                    } catch (UnboundLocationException unboundLocationException) {
                      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 959, 31);
                      throw unboundLocationException;
                    } 
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException, "string-set!", 3, char_);
                  } 
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException, "string-ref", 2, paramObject2);
                } 
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string-ref", 1, classCastException);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-set!", 2, classCastException1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-set!", 1, classCastException);
          }  
        return Values.empty;
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 955, 28);
        throw unboundLocationException;
      } 
    } 
  }
  
  public static Object $PcSubstring$SlShared(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    boolean bool = numbers.isZero(Integer.valueOf(paramInt1));
    return (bool ? (paramInt2 == strings.stringLength(paramCharSequence)) : bool) ? paramCharSequence : strings.substring(paramCharSequence, paramInt1, paramInt2);
  }
  
  static {
    Lit149 = (SimpleSymbol)(new SimpleSymbol("string-join")).readResolve();
    Lit148 = (SimpleSymbol)(new SimpleSymbol("%multispan-repcopy!")).readResolve();
    Lit147 = (SimpleSymbol)(new SimpleSymbol("string-xcopy!")).readResolve();
    Lit146 = (SimpleSymbol)(new SimpleSymbol("xsubstring")).readResolve();
    Lit145 = (SimpleSymbol)(new SimpleSymbol("string-tokenize")).readResolve();
    Lit144 = (SimpleSymbol)(new SimpleSymbol("string-replace")).readResolve();
    Lit143 = (SimpleSymbol)(new SimpleSymbol("%finish-string-concatenate-reverse")).readResolve();
    Lit142 = (SimpleSymbol)(new SimpleSymbol("string-concatenate-reverse/shared")).readResolve();
    Lit141 = (SimpleSymbol)(new SimpleSymbol("string-concatenate-reverse")).readResolve();
    Lit140 = (SimpleSymbol)(new SimpleSymbol("string-concatenate")).readResolve();
    Lit139 = (SimpleSymbol)(new SimpleSymbol("string-concatenate/shared")).readResolve();
    Lit138 = (SimpleSymbol)(new SimpleSymbol("string-append/shared")).readResolve();
    Lit137 = (SimpleSymbol)(new SimpleSymbol("string->list")).readResolve();
    Lit136 = (SimpleSymbol)(new SimpleSymbol("reverse-list->string")).readResolve();
    Lit135 = (SimpleSymbol)(new SimpleSymbol("string-reverse!")).readResolve();
    Lit134 = (SimpleSymbol)(new SimpleSymbol("string-reverse")).readResolve();
    Lit133 = (SimpleSymbol)(new SimpleSymbol("string-null?")).readResolve();
    Lit132 = (SimpleSymbol)(new SimpleSymbol("string-kmp-partial-search")).readResolve();
    Lit131 = (SimpleSymbol)(new SimpleSymbol("kmp-step")).readResolve();
    Lit130 = (SimpleSymbol)(new SimpleSymbol("make-kmp-restart-vector")).readResolve();
    Lit129 = (SimpleSymbol)(new SimpleSymbol("%kmp-search")).readResolve();
    Lit128 = (SimpleSymbol)(new SimpleSymbol("string-contains-ci")).readResolve();
    Lit127 = (SimpleSymbol)(new SimpleSymbol("string-contains")).readResolve();
    Lit126 = (SimpleSymbol)(new SimpleSymbol("%string-copy!")).readResolve();
    Lit125 = (SimpleSymbol)(new SimpleSymbol("string-copy!")).readResolve();
    Lit124 = (SimpleSymbol)(new SimpleSymbol("string-fill!")).readResolve();
    Lit123 = (SimpleSymbol)(new SimpleSymbol("string-count")).readResolve();
    Lit122 = (SimpleSymbol)(new SimpleSymbol("string-skip-right")).readResolve();
    Lit121 = (SimpleSymbol)(new SimpleSymbol("string-skip")).readResolve();
    Lit120 = (SimpleSymbol)(new SimpleSymbol("string-index-right")).readResolve();
    Lit119 = (SimpleSymbol)(new SimpleSymbol("string-index")).readResolve();
    Lit118 = (SimpleSymbol)(new SimpleSymbol("string-filter")).readResolve();
    Lit117 = (SimpleSymbol)(new SimpleSymbol("string-delete")).readResolve();
    Lit116 = (SimpleSymbol)(new SimpleSymbol("string-pad")).readResolve();
    Lit115 = (SimpleSymbol)(new SimpleSymbol("string-pad-right")).readResolve();
    Lit114 = (SimpleSymbol)(new SimpleSymbol("string-trim-both")).readResolve();
    Lit113 = (SimpleSymbol)(new SimpleSymbol("string-trim-right")).readResolve();
    Lit112 = (SimpleSymbol)(new SimpleSymbol("string-trim")).readResolve();
    Lit111 = (SimpleSymbol)(new SimpleSymbol("string-drop-right")).readResolve();
    Lit110 = (SimpleSymbol)(new SimpleSymbol("string-drop")).readResolve();
    Lit109 = (SimpleSymbol)(new SimpleSymbol("string-take-right")).readResolve();
    Lit108 = (SimpleSymbol)(new SimpleSymbol("string-take")).readResolve();
    Lit107 = (SimpleSymbol)(new SimpleSymbol("string-titlecase")).readResolve();
    Lit106 = (SimpleSymbol)(new SimpleSymbol("string-titlecase!")).readResolve();
    Lit105 = (SimpleSymbol)(new SimpleSymbol("%string-titlecase!")).readResolve();
    Lit104 = (SimpleSymbol)(new SimpleSymbol("string-downcase!")).readResolve();
    Lit103 = (SimpleSymbol)(new SimpleSymbol("string-downcase")).readResolve();
    Lit102 = (SimpleSymbol)(new SimpleSymbol("string-upcase!")).readResolve();
    Lit101 = (SimpleSymbol)(new SimpleSymbol("string-upcase")).readResolve();
    Lit100 = (SimpleSymbol)(new SimpleSymbol("string-hash-ci")).readResolve();
    Lit99 = (SimpleSymbol)(new SimpleSymbol("string-hash")).readResolve();
    Lit98 = (SimpleSymbol)(new SimpleSymbol("%string-hash")).readResolve();
    Lit97 = (SimpleSymbol)(new SimpleSymbol("string-ci>=")).readResolve();
    Lit96 = (SimpleSymbol)(new SimpleSymbol("string-ci<=")).readResolve();
    Lit95 = (SimpleSymbol)(new SimpleSymbol("string-ci>")).readResolve();
    Lit94 = (SimpleSymbol)(new SimpleSymbol("string-ci<")).readResolve();
    Lit93 = (SimpleSymbol)(new SimpleSymbol("string-ci<>")).readResolve();
    Lit92 = (SimpleSymbol)(new SimpleSymbol("string-ci=")).readResolve();
    Lit91 = (SimpleSymbol)(new SimpleSymbol("string>=")).readResolve();
    Lit90 = (SimpleSymbol)(new SimpleSymbol("string<=")).readResolve();
    Lit89 = (SimpleSymbol)(new SimpleSymbol("string>")).readResolve();
    Lit88 = (SimpleSymbol)(new SimpleSymbol("string<")).readResolve();
    Lit87 = (SimpleSymbol)(new SimpleSymbol("string<>")).readResolve();
    Lit86 = (SimpleSymbol)(new SimpleSymbol("string=")).readResolve();
    Lit85 = (SimpleSymbol)(new SimpleSymbol("string-compare-ci")).readResolve();
    Lit84 = (SimpleSymbol)(new SimpleSymbol("string-compare")).readResolve();
    Lit83 = (SimpleSymbol)(new SimpleSymbol("%string-compare-ci")).readResolve();
    Lit82 = (SimpleSymbol)(new SimpleSymbol("%string-compare")).readResolve();
    Lit81 = (SimpleSymbol)(new SimpleSymbol("%string-suffix-ci?")).readResolve();
    Lit80 = (SimpleSymbol)(new SimpleSymbol("%string-prefix-ci?")).readResolve();
    Lit79 = (SimpleSymbol)(new SimpleSymbol("%string-suffix?")).readResolve();
    Lit78 = (SimpleSymbol)(new SimpleSymbol("%string-prefix?")).readResolve();
    Lit77 = (SimpleSymbol)(new SimpleSymbol("string-suffix-ci?")).readResolve();
    Lit76 = (SimpleSymbol)(new SimpleSymbol("string-prefix-ci?")).readResolve();
    Lit75 = (SimpleSymbol)(new SimpleSymbol("string-suffix?")).readResolve();
    Lit74 = (SimpleSymbol)(new SimpleSymbol("string-prefix?")).readResolve();
    Lit73 = (SimpleSymbol)(new SimpleSymbol("string-suffix-length-ci")).readResolve();
    Lit72 = (SimpleSymbol)(new SimpleSymbol("string-prefix-length-ci")).readResolve();
    Lit71 = (SimpleSymbol)(new SimpleSymbol("string-suffix-length")).readResolve();
    Lit70 = (SimpleSymbol)(new SimpleSymbol("string-prefix-length")).readResolve();
    Lit69 = (SimpleSymbol)(new SimpleSymbol("%string-suffix-length-ci")).readResolve();
    Lit68 = (SimpleSymbol)(new SimpleSymbol("%string-prefix-length-ci")).readResolve();
    Lit67 = (SimpleSymbol)(new SimpleSymbol("%string-suffix-length")).readResolve();
    Lit66 = (SimpleSymbol)(new SimpleSymbol("%string-prefix-length")).readResolve();
    Lit65 = (SimpleSymbol)(new SimpleSymbol("string-tabulate")).readResolve();
    Lit64 = (SimpleSymbol)(new SimpleSymbol("string-any")).readResolve();
    Lit63 = (SimpleSymbol)(new SimpleSymbol("string-every")).readResolve();
    Lit62 = (SimpleSymbol)(new SimpleSymbol("string-for-each-index")).readResolve();
    Lit61 = (SimpleSymbol)(new SimpleSymbol("string-for-each")).readResolve();
    Lit60 = (SimpleSymbol)(new SimpleSymbol("string-unfold-right")).readResolve();
    Lit59 = (SimpleSymbol)(new SimpleSymbol("string-unfold")).readResolve();
    Lit58 = (SimpleSymbol)(new SimpleSymbol("string-fold-right")).readResolve();
    Lit57 = (SimpleSymbol)(new SimpleSymbol("string-fold")).readResolve();
    Lit56 = (SimpleSymbol)(new SimpleSymbol("%string-map!")).readResolve();
    Lit55 = (SimpleSymbol)(new SimpleSymbol("string-map!")).readResolve();
    Lit54 = (SimpleSymbol)(new SimpleSymbol("%string-map")).readResolve();
    Lit53 = (SimpleSymbol)(new SimpleSymbol("string-map")).readResolve();
    Lit52 = (SimpleSymbol)(new SimpleSymbol("string-copy")).readResolve();
    Lit51 = (SimpleSymbol)(new SimpleSymbol("%substring/shared")).readResolve();
    Lit50 = (SimpleSymbol)(new SimpleSymbol("substring/shared")).readResolve();
    Lit49 = (SimpleSymbol)(new SimpleSymbol("check-substring-spec")).readResolve();
    Lit48 = (SimpleSymbol)(new SimpleSymbol("substring-spec-ok?")).readResolve();
    Lit47 = (SimpleSymbol)(new SimpleSymbol("string-parse-final-start+end")).readResolve();
    Lit46 = (SimpleSymbol)(new SimpleSymbol("%check-bounds")).readResolve();
    Lit45 = (SimpleSymbol)(new SimpleSymbol("string-parse-start+end")).readResolve();
    SimpleSymbol simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("l-s-s+e2")).readResolve();
    SyntaxPattern syntaxPattern = new SyntaxPattern("\f\030L\f\007\f\017\f\027\f\037\b\f'\f/\f7\f?\rG@\b\b", new Object[0], 9);
    SimpleSymbol simpleSymbol2 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
    SimpleSymbol simpleSymbol3 = (SimpleSymbol)(new SimpleSymbol("procv")).readResolve();
    SimpleSymbol simpleSymbol4 = (SimpleSymbol)(new SimpleSymbol("let-string-start+end")).readResolve();
    Lit41 = simpleSymbol4;
    SimpleSymbol simpleSymbol5 = (SimpleSymbol)(new SimpleSymbol("rest")).readResolve();
    Lit27 = simpleSymbol5;
    SyntaxRule syntaxRule1 = new SyntaxRule(syntaxPattern, "\001\001\001\001\001\001\001\001\003", "\021\030\0041\b\021\030\f\b#\b\021\030\0241\t\003\t\013\030\034\021\030\f\t+\t;\b\021\030\024!\t\023\b\033\021\030\f\t3\021\030$\bEC", new Object[] { simpleSymbol2, simpleSymbol3, simpleSymbol4, PairWithPosition.make(simpleSymbol5, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 553003), Lit27 }1);
    Lit44 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1 }, 9);
    Lit43 = (SimpleSymbol)(new SimpleSymbol("let-string-start+end2")).readResolve();
    simpleSymbol1 = Lit41;
    syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030,\f\007\f\017\b\f\027\f\037\f'\r/(\b\b", new Object[0], 6), "\001\001\001\001\001\003", "\021\030\004!\t\003\b\013I\021\030\f\t\023\t\033\b#\b-+", new Object[] { Lit150, Lit47 }, 1);
    SyntaxRule syntaxRule2 = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\017\f\027\b\f\037\f'\f/\r70\b\b", new Object[0], 7), "\001\001\001\001\001\001\003", "\021\030\0041\t\023\t\003\b\013I\021\030\f\t\033\t#\b+\b53", new Object[] { Lit150, Lit45 }, 1);
    Lit42 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1, syntaxRule2 }, 7);
    Lit40 = (SimpleSymbol)(new SimpleSymbol("grammar")).readResolve();
    Lit39 = (SimpleSymbol)(new SimpleSymbol("delim")).readResolve();
    Lit38 = (SimpleSymbol)(new SimpleSymbol("token-chars")).readResolve();
    Lit37 = (SimpleSymbol)(new SimpleSymbol("final")).readResolve();
    Lit36 = (SimpleSymbol)(new SimpleSymbol("s-end")).readResolve();
    Lit35 = (SimpleSymbol)(new SimpleSymbol("s-start")).readResolve();
    Lit34 = (SimpleSymbol)(new SimpleSymbol("p-start")).readResolve();
    Lit33 = (SimpleSymbol)(new SimpleSymbol("end")).readResolve();
    Lit32 = (SimpleSymbol)(new SimpleSymbol("start")).readResolve();
    Lit31 = (SimpleSymbol)(new SimpleSymbol("c=")).readResolve();
    Lit30 = (SimpleSymbol)(new SimpleSymbol("char-set")).readResolve();
    Lit29 = (SimpleSymbol)(new SimpleSymbol("criterion")).readResolve();
    Lit28 = (SimpleSymbol)(new SimpleSymbol("char-cased?")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("bound")).readResolve();
    Lit25 = (SimpleSymbol)(new SimpleSymbol("char-set-contains?")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("char-set?")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("make-final")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("base")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("let-optionals*")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol(":optional")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("check-arg")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("suffix")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("prefix")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("strict-infix")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("infix")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("graphic")).readResolve();
    Lit13 = IntNum.make(-1);
    Lit12 = Char.make(32);
    Lit11 = (SimpleSymbol)(new SimpleSymbol("whitespace")).readResolve();
    Lit10 = IntNum.make(4194304);
    Lit9 = IntNum.make(4194304);
    Lit8 = IntNum.make(4194304);
    Lit7 = IntNum.make(4194304);
    Lit6 = IntNum.make(37);
    Lit5 = IntNum.make(65536);
    Lit4 = IntNum.make(4096);
    Lit3 = IntNum.make(40);
    Lit2 = IntNum.make(4096);
    Lit1 = IntNum.make(1);
    Lit0 = IntNum.make(0);
    $instance = new srfi13();
    loc$check$Mnarg = (Location)ThreadLocation.getInstance((Symbol)Lit19, null);
    loc$$Cloptional = (Location)ThreadLocation.getInstance((Symbol)Lit20, null);
    loc$let$Mnoptionals$St = (Location)ThreadLocation.getInstance((Symbol)Lit21, null);
    loc$base = (Location)ThreadLocation.getInstance((Symbol)Lit22, null);
    loc$make$Mnfinal = (Location)ThreadLocation.getInstance((Symbol)Lit23, null);
    loc$char$Mnset$Qu = (Location)ThreadLocation.getInstance((Symbol)Lit24, null);
    loc$char$Mnset$Mncontains$Qu = (Location)ThreadLocation.getInstance((Symbol)Lit25, null);
    loc$bound = (Location)ThreadLocation.getInstance((Symbol)Lit26, null);
    loc$rest = (Location)ThreadLocation.getInstance((Symbol)Lit27, null);
    loc$char$Mncased$Qu = (Location)ThreadLocation.getInstance((Symbol)Lit28, null);
    loc$criterion = (Location)ThreadLocation.getInstance((Symbol)Lit29, null);
    loc$char$Mnset = (Location)ThreadLocation.getInstance((Symbol)Lit30, null);
    loc$c$Eq = (Location)ThreadLocation.getInstance((Symbol)Lit31, null);
    loc$start = (Location)ThreadLocation.getInstance((Symbol)Lit32, null);
    loc$end = (Location)ThreadLocation.getInstance((Symbol)Lit33, null);
    loc$p$Mnstart = (Location)ThreadLocation.getInstance((Symbol)Lit34, null);
    loc$s$Mnstart = (Location)ThreadLocation.getInstance((Symbol)Lit35, null);
    loc$s$Mnend = (Location)ThreadLocation.getInstance((Symbol)Lit36, null);
    loc$final = (Location)ThreadLocation.getInstance((Symbol)Lit37, null);
    loc$token$Mnchars = (Location)ThreadLocation.getInstance((Symbol)Lit38, null);
    loc$delim = (Location)ThreadLocation.getInstance((Symbol)Lit39, null);
    loc$grammar = (Location)ThreadLocation.getInstance((Symbol)Lit40, null);
    let$Mnstring$Mnstart$Plend = Macro.make(Lit41, (Procedure)Lit42, $instance);
    let$Mnstring$Mnstart$Plend2 = Macro.make(Lit43, (Procedure)Lit44, $instance);
    srfi13 srfi131 = $instance;
    string$Mnparse$Mnstart$Plend = new ModuleMethod(srfi131, 194, Lit45, 12291);
    $Pccheck$Mnbounds = new ModuleMethod(srfi131, 195, Lit46, 16388);
    string$Mnparse$Mnfinal$Mnstart$Plend = new ModuleMethod(srfi131, 196, Lit47, 12291);
    substring$Mnspec$Mnok$Qu = new ModuleMethod(srfi131, 197, Lit48, 12291);
    check$Mnsubstring$Mnspec = new ModuleMethod(srfi131, 198, Lit49, 16388);
    ModuleMethod moduleMethod = new ModuleMethod(srfi131, 199, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:223");
    lambda$Fn5 = moduleMethod;
    substring$Slshared = new ModuleMethod(srfi131, 200, Lit50, -4094);
    $Pcsubstring$Slshared = new ModuleMethod(srfi131, 201, Lit51, 12291);
    string$Mncopy = new ModuleMethod(srfi131, 202, Lit52, -4095);
    string$Mnmap = new ModuleMethod(srfi131, 203, Lit53, -4094);
    $Pcstring$Mnmap = new ModuleMethod(srfi131, 204, Lit54, 16388);
    string$Mnmap$Ex = new ModuleMethod(srfi131, 205, Lit55, -4094);
    $Pcstring$Mnmap$Ex = new ModuleMethod(srfi131, 206, Lit56, 16388);
    string$Mnfold = new ModuleMethod(srfi131, 207, Lit57, -4093);
    string$Mnfold$Mnright = new ModuleMethod(srfi131, 208, Lit58, -4093);
    moduleMethod = new ModuleMethod(srfi131, 209, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:377");
    lambda$Fn17 = moduleMethod;
    string$Mnunfold = new ModuleMethod(srfi131, 210, Lit59, -4092);
    moduleMethod = new ModuleMethod(srfi131, 211, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:422");
    lambda$Fn18 = moduleMethod;
    string$Mnunfold$Mnright = new ModuleMethod(srfi131, 212, Lit60, -4092);
    string$Mnfor$Mneach = new ModuleMethod(srfi131, 213, Lit61, -4094);
    string$Mnfor$Mneach$Mnindex = new ModuleMethod(srfi131, 214, Lit62, -4094);
    string$Mnevery = new ModuleMethod(srfi131, 215, Lit63, -4094);
    string$Mnany = new ModuleMethod(srfi131, 216, Lit64, -4094);
    moduleMethod = new ModuleMethod(srfi131, 217, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:535");
    lambda$Fn27 = moduleMethod;
    string$Mntabulate = new ModuleMethod(srfi131, 218, Lit65, 8194);
    $Pcstring$Mnprefix$Mnlength = new ModuleMethod(srfi131, 219, Lit66, 24582);
    $Pcstring$Mnsuffix$Mnlength = new ModuleMethod(srfi131, 220, Lit67, 24582);
    $Pcstring$Mnprefix$Mnlength$Mnci = new ModuleMethod(srfi131, 221, Lit68, 24582);
    $Pcstring$Mnsuffix$Mnlength$Mnci = new ModuleMethod(srfi131, 222, Lit69, 24582);
    string$Mnprefix$Mnlength = new ModuleMethod(srfi131, 223, Lit70, -4094);
    string$Mnsuffix$Mnlength = new ModuleMethod(srfi131, 224, Lit71, -4094);
    string$Mnprefix$Mnlength$Mnci = new ModuleMethod(srfi131, 225, Lit72, -4094);
    string$Mnsuffix$Mnlength$Mnci = new ModuleMethod(srfi131, 226, Lit73, -4094);
    string$Mnprefix$Qu = new ModuleMethod(srfi131, 227, Lit74, -4094);
    string$Mnsuffix$Qu = new ModuleMethod(srfi131, 228, Lit75, -4094);
    string$Mnprefix$Mnci$Qu = new ModuleMethod(srfi131, 229, Lit76, -4094);
    string$Mnsuffix$Mnci$Qu = new ModuleMethod(srfi131, 230, Lit77, -4094);
    $Pcstring$Mnprefix$Qu = new ModuleMethod(srfi131, 231, Lit78, 24582);
    $Pcstring$Mnsuffix$Qu = new ModuleMethod(srfi131, 232, Lit79, 24582);
    $Pcstring$Mnprefix$Mnci$Qu = new ModuleMethod(srfi131, 233, Lit80, 24582);
    $Pcstring$Mnsuffix$Mnci$Qu = new ModuleMethod(srfi131, 234, Lit81, 24582);
    $Pcstring$Mncompare = new ModuleMethod(srfi131, 235, Lit82, 36873);
    $Pcstring$Mncompare$Mnci = new ModuleMethod(srfi131, 236, Lit83, 36873);
    string$Mncompare = new ModuleMethod(srfi131, 237, Lit84, -4091);
    string$Mncompare$Mnci = new ModuleMethod(srfi131, 238, Lit85, -4091);
    moduleMethod = new ModuleMethod(srfi131, 239, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:756");
    lambda$Fn72 = moduleMethod;
    moduleMethod = new ModuleMethod(srfi131, 240, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:758");
    lambda$Fn73 = moduleMethod;
    string$Eq = new ModuleMethod(srfi131, 241, Lit86, -4094);
    moduleMethod = new ModuleMethod(srfi131, 242, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:767");
    lambda$Fn78 = moduleMethod;
    string$Ls$Gr = new ModuleMethod(srfi131, 243, Lit87, -4094);
    moduleMethod = new ModuleMethod(srfi131, 244, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:778");
    lambda$Fn83 = moduleMethod;
    moduleMethod = new ModuleMethod(srfi131, 245, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:779");
    lambda$Fn84 = moduleMethod;
    string$Ls = new ModuleMethod(srfi131, 246, Lit88, -4094);
    moduleMethod = new ModuleMethod(srfi131, 247, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:788");
    lambda$Fn89 = moduleMethod;
    moduleMethod = new ModuleMethod(srfi131, 248, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:789");
    lambda$Fn90 = moduleMethod;
    string$Gr = new ModuleMethod(srfi131, 249, Lit89, -4094);
    moduleMethod = new ModuleMethod(srfi131, 250, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:801");
    lambda$Fn95 = moduleMethod;
    string$Ls$Eq = new ModuleMethod(srfi131, 251, Lit90, -4094);
    moduleMethod = new ModuleMethod(srfi131, 252, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:810");
    lambda$Fn100 = moduleMethod;
    string$Gr$Eq = new ModuleMethod(srfi131, 253, Lit91, -4094);
    moduleMethod = new ModuleMethod(srfi131, 254, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:820");
    lambda$Fn105 = moduleMethod;
    moduleMethod = new ModuleMethod(srfi131, 255, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:822");
    lambda$Fn106 = moduleMethod;
    string$Mnci$Eq = new ModuleMethod(srfi131, 256, Lit92, -4094);
    moduleMethod = new ModuleMethod(srfi131, 257, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:831");
    lambda$Fn111 = moduleMethod;
    string$Mnci$Ls$Gr = new ModuleMethod(srfi131, 258, Lit93, -4094);
    moduleMethod = new ModuleMethod(srfi131, 259, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:842");
    lambda$Fn116 = moduleMethod;
    moduleMethod = new ModuleMethod(srfi131, 260, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:843");
    lambda$Fn117 = moduleMethod;
    string$Mnci$Ls = new ModuleMethod(srfi131, 261, Lit94, -4094);
    moduleMethod = new ModuleMethod(srfi131, 262, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:852");
    lambda$Fn122 = moduleMethod;
    moduleMethod = new ModuleMethod(srfi131, 263, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:853");
    lambda$Fn123 = moduleMethod;
    string$Mnci$Gr = new ModuleMethod(srfi131, 264, Lit95, -4094);
    moduleMethod = new ModuleMethod(srfi131, 265, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:865");
    lambda$Fn128 = moduleMethod;
    string$Mnci$Ls$Eq = new ModuleMethod(srfi131, 266, Lit96, -4094);
    moduleMethod = new ModuleMethod(srfi131, 267, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:874");
    lambda$Fn133 = moduleMethod;
    string$Mnci$Gr$Eq = new ModuleMethod(srfi131, 268, Lit97, -4094);
    $Pcstring$Mnhash = new ModuleMethod(srfi131, 269, Lit98, 20485);
    string$Mnhash = new ModuleMethod(srfi131, 270, Lit99, -4095);
    moduleMethod = new ModuleMethod(srfi131, 271, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:922");
    lambda$Fn138 = moduleMethod;
    string$Mnhash$Mnci = new ModuleMethod(srfi131, 272, Lit100, -4095);
    string$Mnupcase = new ModuleMethod(srfi131, 273, Lit101, -4095);
    string$Mnupcase$Ex = new ModuleMethod(srfi131, 274, Lit102, -4095);
    string$Mndowncase = new ModuleMethod(srfi131, 275, Lit103, -4095);
    string$Mndowncase$Ex = new ModuleMethod(srfi131, 276, Lit104, -4095);
    $Pcstring$Mntitlecase$Ex = new ModuleMethod(srfi131, 277, Lit105, 12291);
    string$Mntitlecase$Ex = new ModuleMethod(srfi131, 278, Lit106, -4095);
    string$Mntitlecase = new ModuleMethod(srfi131, 279, Lit107, -4095);
    string$Mntake = new ModuleMethod(srfi131, 280, Lit108, 8194);
    string$Mntake$Mnright = new ModuleMethod(srfi131, 281, Lit109, 8194);
    string$Mndrop = new ModuleMethod(srfi131, 282, Lit110, 8194);
    string$Mndrop$Mnright = new ModuleMethod(srfi131, 283, Lit111, 8194);
    string$Mntrim = new ModuleMethod(srfi131, 284, Lit112, -4095);
    string$Mntrim$Mnright = new ModuleMethod(srfi131, 285, Lit113, -4095);
    string$Mntrim$Mnboth = new ModuleMethod(srfi131, 286, Lit114, -4095);
    moduleMethod = new ModuleMethod(srfi131, 287, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1047");
    lambda$Fn163 = moduleMethod;
    string$Mnpad$Mnright = new ModuleMethod(srfi131, 288, Lit115, -4094);
    moduleMethod = new ModuleMethod(srfi131, 289, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1059");
    lambda$Fn166 = moduleMethod;
    string$Mnpad = new ModuleMethod(srfi131, 290, Lit116, -4094);
    string$Mndelete = new ModuleMethod(srfi131, 291, Lit117, -4094);
    string$Mnfilter = new ModuleMethod(srfi131, 292, Lit118, -4094);
    string$Mnindex = new ModuleMethod(srfi131, 293, Lit119, -4094);
    string$Mnindex$Mnright = new ModuleMethod(srfi131, 294, Lit120, -4094);
    string$Mnskip = new ModuleMethod(srfi131, 295, Lit121, -4094);
    string$Mnskip$Mnright = new ModuleMethod(srfi131, 296, Lit122, -4094);
    string$Mncount = new ModuleMethod(srfi131, 297, Lit123, -4094);
    string$Mnfill$Ex = new ModuleMethod(srfi131, 298, Lit124, -4094);
    string$Mncopy$Ex = new ModuleMethod(srfi131, 299, Lit125, 20483);
    $Pcstring$Mncopy$Ex = new ModuleMethod(srfi131, 302, Lit126, 20485);
    string$Mncontains = new ModuleMethod(srfi131, 303, Lit127, -4094);
    string$Mncontains$Mnci = new ModuleMethod(srfi131, 304, Lit128, -4094);
    $Pckmp$Mnsearch = new ModuleMethod(srfi131, 305, Lit129, 28679);
    make$Mnkmp$Mnrestart$Mnvector = new ModuleMethod(srfi131, 306, Lit130, -4095);
    kmp$Mnstep = new ModuleMethod(srfi131, 307, Lit131, 24582);
    string$Mnkmp$Mnpartial$Mnsearch = new ModuleMethod(srfi131, 308, Lit132, -4092);
    string$Mnnull$Qu = new ModuleMethod(srfi131, 309, Lit133, 4097);
    string$Mnreverse = new ModuleMethod(srfi131, 310, Lit134, -4095);
    string$Mnreverse$Ex = new ModuleMethod(srfi131, 311, Lit135, -4095);
    reverse$Mnlist$Mn$Grstring = new ModuleMethod(srfi131, 312, Lit136, 4097);
    string$Mn$Grlist = new ModuleMethod(srfi131, 313, Lit137, -4095);
    string$Mnappend$Slshared = new ModuleMethod(srfi131, 314, Lit138, -4096);
    string$Mnconcatenate$Slshared = new ModuleMethod(srfi131, 315, Lit139, 4097);
    string$Mnconcatenate = new ModuleMethod(srfi131, 316, Lit140, 4097);
    string$Mnconcatenate$Mnreverse = new ModuleMethod(srfi131, 317, Lit141, -4095);
    string$Mnconcatenate$Mnreverse$Slshared = new ModuleMethod(srfi131, 318, Lit142, -4095);
    $Pcfinish$Mnstring$Mnconcatenate$Mnreverse = new ModuleMethod(srfi131, 319, Lit143, 16388);
    string$Mnreplace = new ModuleMethod(srfi131, 320, Lit144, -4092);
    string$Mntokenize = new ModuleMethod(srfi131, 321, Lit145, -4095);
    moduleMethod = new ModuleMethod(srfi131, 322, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1738");
    lambda$Fn210 = moduleMethod;
    xsubstring = new ModuleMethod(srfi131, 323, Lit146, -4094);
    moduleMethod = new ModuleMethod(srfi131, 324, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1779");
    lambda$Fn216 = moduleMethod;
    moduleMethod = new ModuleMethod(srfi131, 325, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1785");
    lambda$Fn220 = moduleMethod;
    string$Mnxcopy$Ex = new ModuleMethod(srfi131, 326, Lit147, -4092);
    $Pcmultispan$Mnrepcopy$Ex = new ModuleMethod(srfi131, 327, Lit148, 28679);
    string$Mnjoin = new ModuleMethod(srfi131, 328, Lit149, -4095);
    $instance.run();
  }
  
  public srfi13() {
    ModuleInfo.register(this);
  }
  
  public static Object checkSubstringSpec(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    return !isSubstringSpecOk(paramObject2, paramObject3, paramObject4) ? misc.error$V("Illegal substring spec.", new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 }) : Values.empty;
  }
  
  public static boolean isStringNull(Object paramObject) {
    try {
      CharSequence charSequence = (CharSequence)paramObject;
      return numbers.isZero(Integer.valueOf(strings.stringLength(charSequence)));
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "string-length", 1, paramObject);
    } 
  }
  
  public static Object isStringPrefix$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame19 frame19 = new frame19();
    frame19.s1 = paramObject1;
    frame19.s2 = paramObject2;
    frame19.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame19.lambda$Fn44, (Procedure)frame19.lambda$Fn45);
  }
  
  public static Object isStringPrefixCi$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame23 frame23 = new frame23();
    frame23.s1 = paramObject1;
    frame23.s2 = paramObject2;
    frame23.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame23.lambda$Fn52, (Procedure)frame23.lambda$Fn53);
  }
  
  public static Object isStringSuffix$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame21 frame21 = new frame21();
    frame21.s1 = paramObject1;
    frame21.s2 = paramObject2;
    frame21.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame21.lambda$Fn48, (Procedure)frame21.lambda$Fn49);
  }
  
  public static Object isStringSuffixCi$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame25 frame25 = new frame25();
    frame25.s1 = paramObject1;
    frame25.s2 = paramObject2;
    frame25.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame25.lambda$Fn56, (Procedure)frame25.lambda$Fn57);
  }
  
  public static boolean isSubstringSpecOk(Object paramObject1, Object paramObject2, Object paramObject3) {
    boolean bool2 = strings.isString(paramObject1);
    boolean bool1 = bool2;
    if (bool2) {
      bool2 = numbers.isInteger(paramObject2);
      bool1 = bool2;
      if (bool2) {
        bool2 = numbers.isExact(paramObject2);
        bool1 = bool2;
        if (bool2) {
          bool2 = numbers.isInteger(paramObject3);
          bool1 = bool2;
          if (bool2) {
            bool2 = numbers.isExact(paramObject3);
            bool1 = bool2;
            if (bool2) {
              Object object = Scheme.numLEq.apply2(Lit0, paramObject2);
              try {
                bool2 = ((Boolean)object).booleanValue();
                bool1 = bool2;
                if (bool2) {
                  paramObject2 = Scheme.numLEq.apply2(paramObject2, paramObject3);
                  try {
                    bool2 = ((Boolean)paramObject2).booleanValue();
                    bool1 = bool2;
                    if (bool2) {
                      paramObject2 = Scheme.numLEq;
                      try {
                        object = paramObject1;
                        return ((Boolean)paramObject2.apply2(paramObject3, Integer.valueOf(strings.stringLength((CharSequence)object)))).booleanValue();
                      } catch (ClassCastException classCastException) {
                        throw new WrongType(classCastException, "string-length", 1, paramObject1);
                      } 
                    } 
                    return bool1;
                  } catch (ClassCastException classCastException1) {
                    throw new WrongType(classCastException1, "x", -2, classCastException);
                  } 
                } 
                return bool1;
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "x", -2, object);
              } 
            } 
          } 
        } 
      } 
    } 
    return bool1;
  }
  
  public static Object kmpStep(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6) {
    while (true) {
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      try {
        CharSequence charSequence = (CharSequence)paramObject1;
        Object object = AddOp.$Pl.apply2(paramObject4, paramObject6);
        try {
          int i = ((Number)object).intValue();
          if (applyToArgs.apply3(paramObject5, paramObject3, Char.make(strings.stringRef(charSequence, i))) != Boolean.FALSE)
            return AddOp.$Pl.apply2(paramObject4, Lit1); 
          try {
            object = paramObject2;
            try {
              i = ((Number)paramObject4).intValue();
              object = vectors.vectorRef((FVector)object, i);
              paramObject4 = object;
              if (Scheme.numEqu.apply2(object, Lit13) != Boolean.FALSE)
                return Lit0; 
            } catch (ClassCastException null) {
              throw new WrongType(classCastException, "vector-ref", 2, paramObject4);
            } 
          } catch (ClassCastException null) {
            throw new WrongType(classCastException, "vector-ref", 1, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 2, object);
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "string-ref", 1, classCastException);
      } 
    } 
  }
  
  static String lambda17(Object paramObject) {
    return "";
  }
  
  static String lambda18(Object paramObject) {
    return "";
  }
  
  public static Object lambda222buildit(Object paramObject1, Object paramObject2) {
    frame96 frame96 = new frame96();
    frame96.final = paramObject2;
    return frame96.lambda223recur(paramObject1);
  }
  
  static boolean lambda27(Object paramObject) {
    boolean bool2 = numbers.isInteger(paramObject);
    boolean bool1 = bool2;
    if (bool2) {
      bool2 = numbers.isExact(paramObject);
      bool1 = bool2;
      if (bool2)
        bool1 = ((Boolean)Scheme.numLEq.apply2(Lit0, paramObject)).booleanValue(); 
    } 
    return bool1;
  }
  
  public static Object makeKmpRestartVector$V(Object paramObject, Object[] paramArrayOfObject) {
    // Byte code:
    //   0: new gnu/kawa/slib/srfi13$frame87
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #5
    //   9: aload #5
    //   11: aload_0
    //   12: putfield pattern : Ljava/lang/Object;
    //   15: aload_1
    //   16: iconst_0
    //   17: invokestatic makeList : ([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   20: astore #6
    //   22: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   25: astore #7
    //   27: getstatic gnu/kawa/slib/srfi13.loc$let$Mnoptionals$St : Lgnu/mapping/Location;
    //   30: astore_0
    //   31: aload_0
    //   32: invokevirtual get : ()Ljava/lang/Object;
    //   35: astore #8
    //   37: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   40: astore_1
    //   41: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   44: astore #4
    //   46: getstatic gnu/kawa/slib/srfi13.loc$c$Eq : Lgnu/mapping/Location;
    //   49: astore_0
    //   50: aload_0
    //   51: invokevirtual get : ()Ljava/lang/Object;
    //   54: astore #9
    //   56: getstatic kawa/lib/characters.char$Eq$Qu : Lgnu/expr/ModuleMethod;
    //   59: astore #10
    //   61: getstatic gnu/kawa/slib/srfi13.loc$c$Eq : Lgnu/mapping/Location;
    //   64: astore_0
    //   65: aload_0
    //   66: invokevirtual get : ()Ljava/lang/Object;
    //   69: astore_0
    //   70: aload_0
    //   71: invokestatic isProcedure : (Ljava/lang/Object;)Z
    //   74: ifeq -> 439
    //   77: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   80: astore_0
    //   81: aload #4
    //   83: aload #9
    //   85: aload #10
    //   87: aload_0
    //   88: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   91: astore_0
    //   92: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   95: astore #4
    //   97: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   100: astore #9
    //   102: getstatic gnu/kawa/slib/srfi13.loc$start : Lgnu/mapping/Location;
    //   105: astore #10
    //   107: aload #10
    //   109: invokevirtual get : ()Ljava/lang/Object;
    //   112: astore #10
    //   114: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   117: astore #11
    //   119: aload #11
    //   121: invokevirtual get : ()Ljava/lang/Object;
    //   124: astore #11
    //   126: aload_1
    //   127: aload_0
    //   128: aload #4
    //   130: aload #9
    //   132: aload #10
    //   134: aload #11
    //   136: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   139: aload #5
    //   141: getfield lambda$Fn197 : Lgnu/expr/ModuleMethod;
    //   144: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   147: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   150: astore #9
    //   152: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
    //   155: astore_0
    //   156: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   159: astore_1
    //   160: aload_1
    //   161: invokevirtual get : ()Ljava/lang/Object;
    //   164: astore_1
    //   165: getstatic gnu/kawa/slib/srfi13.loc$start : Lgnu/mapping/Location;
    //   168: astore #4
    //   170: aload #4
    //   172: invokevirtual get : ()Ljava/lang/Object;
    //   175: astore #4
    //   177: aload_0
    //   178: aload_1
    //   179: aload #4
    //   181: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   184: astore_0
    //   185: aload_0
    //   186: checkcast java/lang/Number
    //   189: invokevirtual intValue : ()I
    //   192: istore_2
    //   193: iload_2
    //   194: getstatic gnu/kawa/slib/srfi13.Lit13 : Lgnu/math/IntNum;
    //   197: invokestatic makeVector : (ILjava/lang/Object;)Lgnu/lists/FVector;
    //   200: astore #10
    //   202: getstatic kawa/standard/Scheme.numGrt : Lgnu/kawa/functions/NumberCompare;
    //   205: aload_0
    //   206: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   209: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   212: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   215: if_acmpeq -> 642
    //   218: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
    //   221: aload_0
    //   222: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
    //   225: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   228: astore #11
    //   230: aload #5
    //   232: getfield pattern : Ljava/lang/Object;
    //   235: astore_1
    //   236: aload_1
    //   237: checkcast java/lang/CharSequence
    //   240: astore_0
    //   241: getstatic gnu/kawa/slib/srfi13.loc$start : Lgnu/mapping/Location;
    //   244: astore_1
    //   245: aload_1
    //   246: invokevirtual get : ()Ljava/lang/Object;
    //   249: astore_1
    //   250: aload_1
    //   251: checkcast java/lang/Number
    //   254: invokevirtual intValue : ()I
    //   257: istore_2
    //   258: aload_0
    //   259: iload_2
    //   260: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   263: istore_2
    //   264: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   267: astore #4
    //   269: getstatic gnu/kawa/slib/srfi13.Lit13 : Lgnu/math/IntNum;
    //   272: astore_0
    //   273: getstatic gnu/kawa/slib/srfi13.loc$start : Lgnu/mapping/Location;
    //   276: astore_1
    //   277: aload_1
    //   278: invokevirtual get : ()Ljava/lang/Object;
    //   281: astore_1
    //   282: getstatic kawa/standard/Scheme.numLss : Lgnu/kawa/functions/NumberCompare;
    //   285: aload #4
    //   287: aload #11
    //   289: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   292: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   295: if_acmpeq -> 642
    //   298: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
    //   301: aload_0
    //   302: getstatic gnu/kawa/slib/srfi13.Lit13 : Lgnu/math/IntNum;
    //   305: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   308: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   311: if_acmpeq -> 446
    //   314: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   317: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
    //   320: aload #4
    //   322: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   325: astore #4
    //   327: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   330: astore_0
    //   331: getstatic gnu/kawa/slib/srfi13.loc$c$Eq : Lgnu/mapping/Location;
    //   334: astore #12
    //   336: aload #12
    //   338: invokevirtual get : ()Ljava/lang/Object;
    //   341: astore #12
    //   343: aload #5
    //   345: getfield pattern : Ljava/lang/Object;
    //   348: astore #13
    //   350: aload #13
    //   352: checkcast java/lang/CharSequence
    //   355: astore #14
    //   357: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   360: aload_1
    //   361: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
    //   364: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   367: astore #13
    //   369: aload #13
    //   371: checkcast java/lang/Number
    //   374: invokevirtual intValue : ()I
    //   377: istore_3
    //   378: aload_0
    //   379: aload #12
    //   381: aload #14
    //   383: iload_3
    //   384: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   387: invokestatic make : (I)Lgnu/text/Char;
    //   390: iload_2
    //   391: invokestatic make : (I)Lgnu/text/Char;
    //   394: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   397: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   400: if_acmpne -> 421
    //   403: aload #4
    //   405: checkcast java/lang/Number
    //   408: invokevirtual intValue : ()I
    //   411: istore_3
    //   412: aload #10
    //   414: iload_3
    //   415: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   418: invokestatic vectorSet$Ex : (Lgnu/lists/FVector;ILjava/lang/Object;)V
    //   421: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   424: astore_0
    //   425: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   428: aload_1
    //   429: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
    //   432: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   435: astore_1
    //   436: goto -> 282
    //   439: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   442: astore_0
    //   443: goto -> 81
    //   446: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   449: astore #12
    //   451: getstatic gnu/kawa/slib/srfi13.loc$c$Eq : Lgnu/mapping/Location;
    //   454: astore #13
    //   456: aload #13
    //   458: invokevirtual get : ()Ljava/lang/Object;
    //   461: astore #13
    //   463: aload #5
    //   465: getfield pattern : Ljava/lang/Object;
    //   468: astore #14
    //   470: aload #14
    //   472: checkcast java/lang/CharSequence
    //   475: astore #15
    //   477: aload_1
    //   478: checkcast java/lang/Number
    //   481: invokevirtual intValue : ()I
    //   484: istore_3
    //   485: aload #15
    //   487: iload_3
    //   488: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   491: invokestatic make : (I)Lgnu/text/Char;
    //   494: astore #14
    //   496: aload #5
    //   498: getfield pattern : Ljava/lang/Object;
    //   501: astore #16
    //   503: aload #16
    //   505: checkcast java/lang/CharSequence
    //   508: astore #15
    //   510: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   513: astore #16
    //   515: getstatic gnu/kawa/slib/srfi13.loc$start : Lgnu/mapping/Location;
    //   518: astore #17
    //   520: aload #17
    //   522: invokevirtual get : ()Ljava/lang/Object;
    //   525: astore #17
    //   527: aload #16
    //   529: aload_0
    //   530: aload #17
    //   532: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   535: astore #16
    //   537: aload #16
    //   539: checkcast java/lang/Number
    //   542: invokevirtual intValue : ()I
    //   545: istore_3
    //   546: aload #12
    //   548: aload #13
    //   550: aload #14
    //   552: aload #15
    //   554: iload_3
    //   555: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   558: invokestatic make : (I)Lgnu/text/Char;
    //   561: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   564: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   567: if_acmpeq -> 624
    //   570: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   573: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
    //   576: aload #4
    //   578: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   581: astore #4
    //   583: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   586: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
    //   589: aload_0
    //   590: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   593: astore_0
    //   594: aload #4
    //   596: checkcast java/lang/Number
    //   599: invokevirtual intValue : ()I
    //   602: istore_3
    //   603: aload #10
    //   605: iload_3
    //   606: aload_0
    //   607: invokestatic vectorSet$Ex : (Lgnu/lists/FVector;ILjava/lang/Object;)V
    //   610: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   613: aload_1
    //   614: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
    //   617: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   620: astore_1
    //   621: goto -> 282
    //   624: aload_0
    //   625: checkcast java/lang/Number
    //   628: invokevirtual intValue : ()I
    //   631: istore_3
    //   632: aload #10
    //   634: iload_3
    //   635: invokestatic vectorRef : (Lgnu/lists/FVector;I)Ljava/lang/Object;
    //   638: astore_0
    //   639: goto -> 298
    //   642: aload #7
    //   644: aload #8
    //   646: aload #6
    //   648: aload #9
    //   650: aload #10
    //   652: invokevirtual apply4 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   655: areturn
    //   656: astore_0
    //   657: aload_0
    //   658: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   661: sipush #1397
    //   664: iconst_3
    //   665: invokevirtual setLine : (Ljava/lang/String;II)V
    //   668: aload_0
    //   669: athrow
    //   670: astore_0
    //   671: aload_0
    //   672: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   675: sipush #1398
    //   678: bipush #20
    //   680: invokevirtual setLine : (Ljava/lang/String;II)V
    //   683: aload_0
    //   684: athrow
    //   685: astore_0
    //   686: aload_0
    //   687: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   690: sipush #1398
    //   693: bipush #43
    //   695: invokevirtual setLine : (Ljava/lang/String;II)V
    //   698: aload_0
    //   699: athrow
    //   700: astore_0
    //   701: aload_0
    //   702: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   705: sipush #1399
    //   708: bipush #7
    //   710: invokevirtual setLine : (Ljava/lang/String;II)V
    //   713: aload_0
    //   714: athrow
    //   715: astore_0
    //   716: aload_0
    //   717: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   720: sipush #1399
    //   723: bipush #14
    //   725: invokevirtual setLine : (Ljava/lang/String;II)V
    //   728: aload_0
    //   729: athrow
    //   730: astore_0
    //   731: aload_0
    //   732: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   735: sipush #1402
    //   738: bipush #22
    //   740: invokevirtual setLine : (Ljava/lang/String;II)V
    //   743: aload_0
    //   744: athrow
    //   745: astore_0
    //   746: aload_0
    //   747: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   750: sipush #1402
    //   753: bipush #26
    //   755: invokevirtual setLine : (Ljava/lang/String;II)V
    //   758: aload_0
    //   759: athrow
    //   760: astore_1
    //   761: new gnu/mapping/WrongType
    //   764: dup
    //   765: aload_1
    //   766: ldc_w 'make-vector'
    //   769: iconst_1
    //   770: aload_0
    //   771: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   774: athrow
    //   775: astore_0
    //   776: new gnu/mapping/WrongType
    //   779: dup
    //   780: aload_0
    //   781: ldc_w 'string-ref'
    //   784: iconst_1
    //   785: aload_1
    //   786: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   789: athrow
    //   790: astore_0
    //   791: aload_0
    //   792: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   795: sipush #1406
    //   798: bipush #27
    //   800: invokevirtual setLine : (Ljava/lang/String;II)V
    //   803: aload_0
    //   804: athrow
    //   805: astore_0
    //   806: new gnu/mapping/WrongType
    //   809: dup
    //   810: aload_0
    //   811: ldc_w 'string-ref'
    //   814: iconst_2
    //   815: aload_1
    //   816: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   819: athrow
    //   820: astore_0
    //   821: aload_0
    //   822: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   825: sipush #1410
    //   828: bipush #6
    //   830: invokevirtual setLine : (Ljava/lang/String;II)V
    //   833: aload_0
    //   834: athrow
    //   835: astore_0
    //   836: aload_0
    //   837: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   840: sipush #1418
    //   843: bipush #18
    //   845: invokevirtual setLine : (Ljava/lang/String;II)V
    //   848: aload_0
    //   849: athrow
    //   850: astore_0
    //   851: new gnu/mapping/WrongType
    //   854: dup
    //   855: aload_0
    //   856: ldc_w 'string-ref'
    //   859: iconst_1
    //   860: aload #13
    //   862: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   865: athrow
    //   866: astore_0
    //   867: new gnu/mapping/WrongType
    //   870: dup
    //   871: aload_0
    //   872: ldc_w 'string-ref'
    //   875: iconst_2
    //   876: aload #13
    //   878: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   881: athrow
    //   882: astore_0
    //   883: new gnu/mapping/WrongType
    //   886: dup
    //   887: aload_0
    //   888: ldc_w 'vector-set!'
    //   891: iconst_2
    //   892: aload #4
    //   894: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   897: athrow
    //   898: astore_0
    //   899: aload_0
    //   900: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   903: sipush #1422
    //   906: bipush #7
    //   908: invokevirtual setLine : (Ljava/lang/String;II)V
    //   911: aload_0
    //   912: athrow
    //   913: astore_0
    //   914: new gnu/mapping/WrongType
    //   917: dup
    //   918: aload_0
    //   919: ldc_w 'string-ref'
    //   922: iconst_1
    //   923: aload #14
    //   925: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   928: athrow
    //   929: astore_0
    //   930: new gnu/mapping/WrongType
    //   933: dup
    //   934: aload_0
    //   935: ldc_w 'string-ref'
    //   938: iconst_2
    //   939: aload_1
    //   940: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   943: athrow
    //   944: astore_0
    //   945: new gnu/mapping/WrongType
    //   948: dup
    //   949: aload_0
    //   950: ldc_w 'string-ref'
    //   953: iconst_1
    //   954: aload #16
    //   956: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   959: athrow
    //   960: astore_0
    //   961: aload_0
    //   962: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   965: sipush #1422
    //   968: bipush #59
    //   970: invokevirtual setLine : (Ljava/lang/String;II)V
    //   973: aload_0
    //   974: athrow
    //   975: astore_0
    //   976: new gnu/mapping/WrongType
    //   979: dup
    //   980: aload_0
    //   981: ldc_w 'string-ref'
    //   984: iconst_2
    //   985: aload #16
    //   987: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   990: athrow
    //   991: astore_0
    //   992: new gnu/mapping/WrongType
    //   995: dup
    //   996: aload_0
    //   997: ldc_w 'vector-set!'
    //   1000: iconst_2
    //   1001: aload #4
    //   1003: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1006: athrow
    //   1007: astore_1
    //   1008: new gnu/mapping/WrongType
    //   1011: dup
    //   1012: aload_1
    //   1013: ldc_w 'vector-ref'
    //   1016: iconst_2
    //   1017: aload_0
    //   1018: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1021: athrow
    // Exception table:
    //   from	to	target	type
    //   31	37	656	gnu/mapping/UnboundLocationException
    //   50	56	670	gnu/mapping/UnboundLocationException
    //   65	70	685	gnu/mapping/UnboundLocationException
    //   107	114	700	gnu/mapping/UnboundLocationException
    //   119	126	715	gnu/mapping/UnboundLocationException
    //   160	165	730	gnu/mapping/UnboundLocationException
    //   170	177	745	gnu/mapping/UnboundLocationException
    //   185	193	760	java/lang/ClassCastException
    //   236	241	775	java/lang/ClassCastException
    //   245	250	790	gnu/mapping/UnboundLocationException
    //   250	258	805	java/lang/ClassCastException
    //   277	282	820	gnu/mapping/UnboundLocationException
    //   336	343	835	gnu/mapping/UnboundLocationException
    //   350	357	850	java/lang/ClassCastException
    //   369	378	866	java/lang/ClassCastException
    //   403	412	882	java/lang/ClassCastException
    //   456	463	898	gnu/mapping/UnboundLocationException
    //   470	477	913	java/lang/ClassCastException
    //   477	485	929	java/lang/ClassCastException
    //   503	510	944	java/lang/ClassCastException
    //   520	527	960	gnu/mapping/UnboundLocationException
    //   537	546	975	java/lang/ClassCastException
    //   594	603	991	java/lang/ClassCastException
    //   624	632	1007	java/lang/ClassCastException
  }
  
  public static CharSequence reverseList$To$String(Object paramObject) {
    CharSequence charSequence;
    try {
      LList lList = (LList)paramObject;
      int i = lists.length(lList);
      charSequence = strings.makeString(i);
      Integer integer = Integer.valueOf(i - 1);
      Object object = paramObject;
      paramObject = integer;
      while (lists.isPair(object)) {
        try {
          CharSeq charSeq = (CharSeq)charSequence;
          try {
            i = ((Number)paramObject).intValue();
            Object object1 = lists.car.apply1(object);
            try {
              char c = ((Char)object1).charValue();
              strings.stringSet$Ex(charSeq, i, c);
              paramObject = AddOp.$Mn.apply2(paramObject, Lit1);
              object = lists.cdr.apply1(object);
            } catch (ClassCastException null) {
              throw new WrongType(classCastException, "string-set!", 3, object1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-set!", 2, classCastException);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-set!", 1, charSequence);
        } 
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "length", 1, classCastException);
    } 
    return charSequence;
  }
  
  public static Object string$Eq$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame31 frame31 = new frame31();
    frame31.s1 = paramObject1;
    frame31.s2 = paramObject2;
    frame31.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame31.lambda$Fn68, (Procedure)frame31.lambda$Fn69);
  }
  
  public static Object string$Gr$Eq$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame41 frame41 = new frame41();
    frame41.s1 = paramObject1;
    frame41.s2 = paramObject2;
    frame41.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame41.lambda$Fn96, (Procedure)frame41.lambda$Fn97);
  }
  
  public static Object string$Gr$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame37 frame37 = new frame37();
    frame37.s1 = paramObject1;
    frame37.s2 = paramObject2;
    frame37.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame37.lambda$Fn85, (Procedure)frame37.lambda$Fn86);
  }
  
  public static Object string$Ls$Eq$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame39 frame39 = new frame39();
    frame39.s1 = paramObject1;
    frame39.s2 = paramObject2;
    frame39.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame39.lambda$Fn91, (Procedure)frame39.lambda$Fn92);
  }
  
  public static Object string$Ls$Gr$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame33 frame33 = new frame33();
    frame33.s1 = paramObject1;
    frame33.s2 = paramObject2;
    frame33.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame33.lambda$Fn74, (Procedure)frame33.lambda$Fn75);
  }
  
  public static Object string$Ls$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame35 frame35 = new frame35();
    frame35.s1 = paramObject1;
    frame35.s2 = paramObject2;
    frame35.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame35.lambda$Fn79, (Procedure)frame35.lambda$Fn80);
  }
  
  public static Object string$To$List$V(Object paramObject, Object[] paramArrayOfObject) {
    frame91 frame91 = new frame91();
    frame91.s = paramObject;
    frame91.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame91.lambda$Fn204, (Procedure)frame91.lambda$Fn205);
  }
  
  public static Object stringAny$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame10 frame10 = new frame10();
    frame10.criterion = paramObject1;
    frame10.s = paramObject2;
    frame10.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame10.lambda$Fn25, (Procedure)frame10.lambda$Fn26);
  }
  
  public static Object stringAppend$SlShared$V(Object[] paramArrayOfObject) {
    return stringConcatenate$SlShared(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static Object stringCi$Eq$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame43 frame43 = new frame43();
    frame43.s1 = paramObject1;
    frame43.s2 = paramObject2;
    frame43.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame43.lambda$Fn101, (Procedure)frame43.lambda$Fn102);
  }
  
  public static Object stringCi$Gr$Eq$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame53 frame53 = new frame53();
    frame53.s1 = paramObject1;
    frame53.s2 = paramObject2;
    frame53.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame53.lambda$Fn129, (Procedure)frame53.lambda$Fn130);
  }
  
  public static Object stringCi$Gr$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame49 frame49 = new frame49();
    frame49.s1 = paramObject1;
    frame49.s2 = paramObject2;
    frame49.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame49.lambda$Fn118, (Procedure)frame49.lambda$Fn119);
  }
  
  public static Object stringCi$Ls$Eq$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame51 frame51 = new frame51();
    frame51.s1 = paramObject1;
    frame51.s2 = paramObject2;
    frame51.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame51.lambda$Fn124, (Procedure)frame51.lambda$Fn125);
  }
  
  public static Object stringCi$Ls$Gr$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame45 frame45 = new frame45();
    frame45.s1 = paramObject1;
    frame45.s2 = paramObject2;
    frame45.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame45.lambda$Fn107, (Procedure)frame45.lambda$Fn108);
  }
  
  public static Object stringCi$Ls$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame47 frame47 = new frame47();
    frame47.s1 = paramObject1;
    frame47.s2 = paramObject2;
    frame47.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame47.lambda$Fn112, (Procedure)frame47.lambda$Fn113);
  }
  
  public static Object stringCompare$V(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object[] paramArrayOfObject) {
    frame27 frame27 = new frame27();
    frame27.s1 = paramObject1;
    frame27.s2 = paramObject2;
    frame27.proc$Ls = paramObject3;
    frame27.proc$Eq = paramObject4;
    frame27.proc$Gr = paramObject5;
    frame27.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.applyToArgs;
    paramObject2 = loc$check$Mnarg;
    try {
      paramObject2 = paramObject2.get();
      paramObject1.apply4(paramObject2, misc.procedure$Qu, frame27.proc$Ls, string$Mncompare);
      paramObject1 = Scheme.applyToArgs;
      paramObject2 = loc$check$Mnarg;
      try {
        paramObject2 = paramObject2.get();
        paramObject1.apply4(paramObject2, misc.procedure$Qu, frame27.proc$Eq, string$Mncompare);
        paramObject1 = Scheme.applyToArgs;
        paramObject2 = loc$check$Mnarg;
        try {
          paramObject2 = paramObject2.get();
          paramObject1.apply4(paramObject2, misc.procedure$Qu, frame27.proc$Gr, string$Mncompare);
          return call_with_values.callWithValues((Procedure)frame27.lambda$Fn60, (Procedure)frame27.lambda$Fn61);
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 728, 3);
          throw unboundLocationException;
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 727, 3);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 726, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringCompareCi$V(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object[] paramArrayOfObject) {
    frame29 frame29 = new frame29();
    frame29.s1 = paramObject1;
    frame29.s2 = paramObject2;
    frame29.proc$Ls = paramObject3;
    frame29.proc$Eq = paramObject4;
    frame29.proc$Gr = paramObject5;
    frame29.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.applyToArgs;
    paramObject2 = loc$check$Mnarg;
    try {
      paramObject2 = paramObject2.get();
      paramObject1.apply4(paramObject2, misc.procedure$Qu, frame29.proc$Ls, string$Mncompare$Mnci);
      paramObject1 = Scheme.applyToArgs;
      paramObject2 = loc$check$Mnarg;
      try {
        paramObject2 = paramObject2.get();
        paramObject1.apply4(paramObject2, misc.procedure$Qu, frame29.proc$Eq, string$Mncompare$Mnci);
        paramObject1 = Scheme.applyToArgs;
        paramObject2 = loc$check$Mnarg;
        try {
          paramObject2 = paramObject2.get();
          paramObject1.apply4(paramObject2, misc.procedure$Qu, frame29.proc$Gr, string$Mncompare$Mnci);
          return call_with_values.callWithValues((Procedure)frame29.lambda$Fn64, (Procedure)frame29.lambda$Fn65);
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 736, 3);
          throw unboundLocationException;
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 735, 3);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 734, 3);
      throw unboundLocationException;
    } 
  }
  
  public static CharSequence stringConcatenate(Object paramObject) {
    IntNum intNum = Lit0;
    Object object = paramObject;
    while (true) {
      Object object2;
      if (lists.isPair(object)) {
        object2 = lists.cdr.apply1(object);
        AddOp addOp = AddOp.$Pl;
        object = lists.car.apply1(object);
        try {
          CharSequence charSequence = (CharSequence)object;
          object1 = addOp.apply2(intNum, Integer.valueOf(strings.stringLength(charSequence)));
          object = object2;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-length", 1, object);
        } 
        continue;
      } 
      try {
        int i = ((Number)object1).intValue();
        object2 = strings.makeString(i);
        object = Lit0;
        object1 = classCastException;
        Object object3 = object;
        while (lists.isPair(object1)) {
          object = lists.car.apply1(object1);
          try {
            CharSequence charSequence = (CharSequence)object;
            i = strings.stringLength(charSequence);
            try {
              int j = ((Number)object3).intValue();
              try {
                charSequence = (CharSequence)object;
                $PcStringCopy$Ex((CharSequence)object2, j, charSequence, 0, i);
                object3 = AddOp.$Pl.apply2(object3, Integer.valueOf(i));
                object1 = lists.cdr.apply1(object1);
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "%string-copy!", 2, object);
              } 
            } catch (ClassCastException object1) {
              throw new WrongType(object1, "%string-copy!", 1, classCastException1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-length", 1, object);
          } 
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "make-string", 1, object1);
      } 
      return (CharSequence)object2;
    } 
  }
  
  public static Object stringConcatenate$SlShared(Object paramObject) {
    IntNum intNum = Lit0;
    Boolean bool = Boolean.FALSE;
    while (true) {
      Object object1;
      Object object2;
      if (lists.isPair(paramObject)) {
        Object object4 = lists.car.apply1(paramObject);
        Object object3 = lists.cdr.apply1(paramObject);
        try {
          CharSequence charSequence = (CharSequence)object4;
          int i = strings.stringLength(charSequence);
          if (numbers.isZero(Integer.valueOf(i))) {
            paramObject = object3;
            continue;
          } 
          object2 = AddOp.$Pl.apply2(intNum, Integer.valueOf(i));
          if (bool == Boolean.FALSE)
            object1 = paramObject; 
          paramObject = object3;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-length", 1, object4);
        } 
        continue;
      } 
      try {
        paramObject = object2;
        if (numbers.isZero((Number)paramObject)) {
          object2 = "";
          continue;
        } 
        NumberCompare numberCompare = Scheme.numEqu;
        paramObject = lists.car.apply1(object1);
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          if (numberCompare.apply2(object2, Integer.valueOf(strings.stringLength(charSequence))) != Boolean.FALSE)
            return lists.car.apply1(object1); 
          try {
            int i = ((Number)object2).intValue();
            CharSequence charSequence1 = strings.makeString(i);
            paramObject = Lit0;
            while (true) {
              object2 = charSequence1;
              if (lists.isPair(object1)) {
                object2 = lists.car.apply1(object1);
                try {
                  charSequence = (CharSequence)object2;
                  i = strings.stringLength(charSequence);
                  try {
                    int j = ((Number)paramObject).intValue();
                    try {
                      charSequence = (CharSequence)object2;
                      $PcStringCopy$Ex(charSequence1, j, charSequence, 0, i);
                      object1 = lists.cdr.apply1(object1);
                      paramObject = AddOp.$Pl.apply2(paramObject, Integer.valueOf(i));
                    } catch (ClassCastException null) {
                      throw new WrongType(classCastException, "%string-copy!", 2, object2);
                    } 
                  } catch (ClassCastException classCastException1) {
                    throw new WrongType(classCastException1, "%string-copy!", 1, classCastException);
                  } 
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException, "string-length", 1, object2);
                } 
              } 
              return object2;
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-string", 1, object2);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "string-length", 1, classCastException);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "zero?", 1, object2);
      } 
    } 
  }
  
  public static Object stringConcatenateReverse$SlShared$V(Object paramObject, Object[] paramArrayOfObject) {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: invokestatic makeList : ([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   5: astore #6
    //   7: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   10: astore #7
    //   12: getstatic gnu/kawa/slib/srfi13.loc$let$Mnoptionals$St : Lgnu/mapping/Location;
    //   15: astore_1
    //   16: aload_1
    //   17: invokevirtual get : ()Ljava/lang/Object;
    //   20: astore #8
    //   22: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   25: astore #4
    //   27: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   30: astore #5
    //   32: getstatic gnu/kawa/slib/srfi13.loc$final : Lgnu/mapping/Location;
    //   35: astore_1
    //   36: aload_1
    //   37: invokevirtual get : ()Ljava/lang/Object;
    //   40: astore #9
    //   42: getstatic gnu/kawa/slib/srfi13.loc$final : Lgnu/mapping/Location;
    //   45: astore_1
    //   46: aload_1
    //   47: invokevirtual get : ()Ljava/lang/Object;
    //   50: astore_1
    //   51: aload_1
    //   52: invokestatic isString : (Ljava/lang/Object;)Z
    //   55: ifeq -> 314
    //   58: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   61: astore_1
    //   62: aload #5
    //   64: aload #9
    //   66: ldc_w ''
    //   69: aload_1
    //   70: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   73: astore #5
    //   75: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   78: astore #9
    //   80: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   83: astore_1
    //   84: aload_1
    //   85: invokevirtual get : ()Ljava/lang/Object;
    //   88: astore #10
    //   90: getstatic gnu/kawa/slib/srfi13.loc$final : Lgnu/mapping/Location;
    //   93: astore_1
    //   94: aload_1
    //   95: invokevirtual get : ()Ljava/lang/Object;
    //   98: astore_1
    //   99: aload_1
    //   100: checkcast java/lang/CharSequence
    //   103: astore #11
    //   105: aload #11
    //   107: invokestatic stringLength : (Ljava/lang/CharSequence;)I
    //   110: istore_2
    //   111: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   114: astore_1
    //   115: aload_1
    //   116: invokevirtual get : ()Ljava/lang/Object;
    //   119: astore_1
    //   120: aload_1
    //   121: invokestatic isInteger : (Ljava/lang/Object;)Z
    //   124: istore_3
    //   125: iload_3
    //   126: ifeq -> 339
    //   129: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   132: astore_1
    //   133: aload_1
    //   134: invokevirtual get : ()Ljava/lang/Object;
    //   137: astore_1
    //   138: aload_1
    //   139: invokestatic isExact : (Ljava/lang/Object;)Z
    //   142: istore_3
    //   143: iload_3
    //   144: ifeq -> 321
    //   147: getstatic kawa/standard/Scheme.numLEq : Lgnu/kawa/functions/NumberCompare;
    //   150: astore_1
    //   151: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   154: astore #11
    //   156: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   159: astore #12
    //   161: aload #12
    //   163: invokevirtual get : ()Ljava/lang/Object;
    //   166: astore #12
    //   168: getstatic gnu/kawa/slib/srfi13.loc$final : Lgnu/mapping/Location;
    //   171: astore #13
    //   173: aload #13
    //   175: invokevirtual get : ()Ljava/lang/Object;
    //   178: astore #13
    //   180: aload #13
    //   182: checkcast java/lang/CharSequence
    //   185: astore #14
    //   187: aload_1
    //   188: aload #11
    //   190: aload #12
    //   192: aload #14
    //   194: invokestatic stringLength : (Ljava/lang/CharSequence;)I
    //   197: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   200: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   203: astore_1
    //   204: aload #4
    //   206: aload #5
    //   208: aload #9
    //   210: aload #10
    //   212: iload_2
    //   213: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   216: aload_1
    //   217: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   220: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   223: astore #9
    //   225: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   228: astore #4
    //   230: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   233: astore_1
    //   234: aload_0
    //   235: invokestatic isPair : (Ljava/lang/Object;)Z
    //   238: ifeq -> 363
    //   241: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   244: aload_0
    //   245: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   248: astore #5
    //   250: aload #5
    //   252: checkcast java/lang/CharSequence
    //   255: astore #10
    //   257: aload #10
    //   259: invokestatic stringLength : (Ljava/lang/CharSequence;)I
    //   262: istore_2
    //   263: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   266: aload #4
    //   268: iload_2
    //   269: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   272: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   275: astore #4
    //   277: aload_1
    //   278: astore #5
    //   280: aload_1
    //   281: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   284: if_acmpne -> 300
    //   287: iload_2
    //   288: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   291: invokestatic isZero : (Ljava/lang/Number;)Z
    //   294: ifeq -> 357
    //   297: aload_1
    //   298: astore #5
    //   300: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   303: aload_0
    //   304: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   307: astore_0
    //   308: aload #5
    //   310: astore_1
    //   311: goto -> 234
    //   314: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   317: astore_1
    //   318: goto -> 62
    //   321: iload_3
    //   322: ifeq -> 332
    //   325: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   328: astore_1
    //   329: goto -> 204
    //   332: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   335: astore_1
    //   336: goto -> 204
    //   339: iload_3
    //   340: ifeq -> 350
    //   343: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   346: astore_1
    //   347: goto -> 204
    //   350: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   353: astore_1
    //   354: goto -> 204
    //   357: aload_0
    //   358: astore #5
    //   360: goto -> 300
    //   363: aload #4
    //   365: checkcast java/lang/Number
    //   368: astore_0
    //   369: aload_0
    //   370: invokestatic isZero : (Ljava/lang/Number;)Z
    //   373: ifeq -> 429
    //   376: getstatic gnu/kawa/slib/srfi13.loc$final : Lgnu/mapping/Location;
    //   379: astore_0
    //   380: aload_0
    //   381: invokevirtual get : ()Ljava/lang/Object;
    //   384: astore_0
    //   385: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   388: astore_1
    //   389: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   392: astore #4
    //   394: aload #4
    //   396: invokevirtual get : ()Ljava/lang/Object;
    //   399: astore #4
    //   401: aload_0
    //   402: aload_1
    //   403: iconst_1
    //   404: anewarray java/lang/Object
    //   407: dup
    //   408: iconst_0
    //   409: aload #4
    //   411: aastore
    //   412: invokestatic substring$SlShared$V : (Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   415: astore_0
    //   416: aload #7
    //   418: aload #8
    //   420: aload #6
    //   422: aload #9
    //   424: aload_0
    //   425: invokevirtual apply4 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   428: areturn
    //   429: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   432: astore_0
    //   433: aload_0
    //   434: invokevirtual get : ()Ljava/lang/Object;
    //   437: astore_0
    //   438: aload_0
    //   439: checkcast java/lang/Number
    //   442: astore #5
    //   444: aload #5
    //   446: invokestatic isZero : (Ljava/lang/Number;)Z
    //   449: istore_3
    //   450: iload_3
    //   451: ifeq -> 505
    //   454: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
    //   457: astore #5
    //   459: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   462: aload_1
    //   463: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   466: astore_0
    //   467: aload_0
    //   468: checkcast java/lang/CharSequence
    //   471: astore #10
    //   473: aload #5
    //   475: aload #4
    //   477: aload #10
    //   479: invokestatic stringLength : (Ljava/lang/CharSequence;)I
    //   482: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   485: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   488: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   491: if_acmpeq -> 509
    //   494: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   497: aload_1
    //   498: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   501: astore_0
    //   502: goto -> 416
    //   505: iload_3
    //   506: ifne -> 494
    //   509: getstatic gnu/kawa/slib/srfi13.loc$final : Lgnu/mapping/Location;
    //   512: astore_0
    //   513: aload_0
    //   514: invokevirtual get : ()Ljava/lang/Object;
    //   517: astore_0
    //   518: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   521: astore #5
    //   523: aload #5
    //   525: invokevirtual get : ()Ljava/lang/Object;
    //   528: astore #5
    //   530: aload #4
    //   532: aload_1
    //   533: aload_0
    //   534: aload #5
    //   536: invokestatic $PcFinishStringConcatenateReverse : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   539: astore_0
    //   540: goto -> 416
    //   543: astore_0
    //   544: aload_0
    //   545: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   548: sipush #1630
    //   551: iconst_3
    //   552: invokevirtual setLine : (Ljava/lang/String;II)V
    //   555: aload_0
    //   556: athrow
    //   557: astore_0
    //   558: aload_0
    //   559: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   562: sipush #1630
    //   565: bipush #36
    //   567: invokevirtual setLine : (Ljava/lang/String;II)V
    //   570: aload_0
    //   571: athrow
    //   572: astore_0
    //   573: aload_0
    //   574: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   577: sipush #1630
    //   580: bipush #55
    //   582: invokevirtual setLine : (Ljava/lang/String;II)V
    //   585: aload_0
    //   586: athrow
    //   587: astore_0
    //   588: aload_0
    //   589: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   592: sipush #1631
    //   595: bipush #8
    //   597: invokevirtual setLine : (Ljava/lang/String;II)V
    //   600: aload_0
    //   601: athrow
    //   602: astore_0
    //   603: aload_0
    //   604: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   607: sipush #1631
    //   610: bipush #28
    //   612: invokevirtual setLine : (Ljava/lang/String;II)V
    //   615: aload_0
    //   616: athrow
    //   617: astore_0
    //   618: new gnu/mapping/WrongType
    //   621: dup
    //   622: aload_0
    //   623: ldc_w 'string-length'
    //   626: iconst_1
    //   627: aload_1
    //   628: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   631: athrow
    //   632: astore_0
    //   633: aload_0
    //   634: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   637: sipush #1632
    //   640: bipush #21
    //   642: invokevirtual setLine : (Ljava/lang/String;II)V
    //   645: aload_0
    //   646: athrow
    //   647: astore_0
    //   648: aload_0
    //   649: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   652: sipush #1633
    //   655: bipush #19
    //   657: invokevirtual setLine : (Ljava/lang/String;II)V
    //   660: aload_0
    //   661: athrow
    //   662: astore_0
    //   663: aload_0
    //   664: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   667: sipush #1634
    //   670: bipush #17
    //   672: invokevirtual setLine : (Ljava/lang/String;II)V
    //   675: aload_0
    //   676: athrow
    //   677: astore_0
    //   678: aload_0
    //   679: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   682: sipush #1634
    //   685: bipush #36
    //   687: invokevirtual setLine : (Ljava/lang/String;II)V
    //   690: aload_0
    //   691: athrow
    //   692: astore_0
    //   693: new gnu/mapping/WrongType
    //   696: dup
    //   697: aload_0
    //   698: ldc_w 'string-length'
    //   701: iconst_1
    //   702: aload #13
    //   704: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   707: athrow
    //   708: astore_0
    //   709: new gnu/mapping/WrongType
    //   712: dup
    //   713: aload_0
    //   714: ldc_w 'string-length'
    //   717: iconst_1
    //   718: aload #5
    //   720: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   723: athrow
    //   724: astore_0
    //   725: new gnu/mapping/WrongType
    //   728: dup
    //   729: aload_0
    //   730: ldc_w 'zero?'
    //   733: iconst_1
    //   734: aload #4
    //   736: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   739: athrow
    //   740: astore_0
    //   741: aload_0
    //   742: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   745: sipush #1645
    //   748: bipush #41
    //   750: invokevirtual setLine : (Ljava/lang/String;II)V
    //   753: aload_0
    //   754: athrow
    //   755: astore_0
    //   756: aload_0
    //   757: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   760: sipush #1645
    //   763: bipush #49
    //   765: invokevirtual setLine : (Ljava/lang/String;II)V
    //   768: aload_0
    //   769: athrow
    //   770: astore_0
    //   771: aload_0
    //   772: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   775: sipush #1649
    //   778: bipush #16
    //   780: invokevirtual setLine : (Ljava/lang/String;II)V
    //   783: aload_0
    //   784: athrow
    //   785: astore_1
    //   786: new gnu/mapping/WrongType
    //   789: dup
    //   790: aload_1
    //   791: ldc_w 'zero?'
    //   794: iconst_1
    //   795: aload_0
    //   796: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   799: athrow
    //   800: astore_1
    //   801: new gnu/mapping/WrongType
    //   804: dup
    //   805: aload_1
    //   806: ldc_w 'string-length'
    //   809: iconst_1
    //   810: aload_0
    //   811: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   814: athrow
    //   815: astore_0
    //   816: aload_0
    //   817: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   820: sipush #1652
    //   823: bipush #56
    //   825: invokevirtual setLine : (Ljava/lang/String;II)V
    //   828: aload_0
    //   829: athrow
    //   830: astore_0
    //   831: aload_0
    //   832: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   835: sipush #1652
    //   838: bipush #62
    //   840: invokevirtual setLine : (Ljava/lang/String;II)V
    //   843: aload_0
    //   844: athrow
    // Exception table:
    //   from	to	target	type
    //   16	22	543	gnu/mapping/UnboundLocationException
    //   36	42	557	gnu/mapping/UnboundLocationException
    //   46	51	572	gnu/mapping/UnboundLocationException
    //   84	90	587	gnu/mapping/UnboundLocationException
    //   94	99	602	gnu/mapping/UnboundLocationException
    //   99	105	617	java/lang/ClassCastException
    //   115	120	632	gnu/mapping/UnboundLocationException
    //   133	138	647	gnu/mapping/UnboundLocationException
    //   161	168	662	gnu/mapping/UnboundLocationException
    //   173	180	677	gnu/mapping/UnboundLocationException
    //   180	187	692	java/lang/ClassCastException
    //   250	257	708	java/lang/ClassCastException
    //   363	369	724	java/lang/ClassCastException
    //   380	385	740	gnu/mapping/UnboundLocationException
    //   394	401	755	gnu/mapping/UnboundLocationException
    //   433	438	770	gnu/mapping/UnboundLocationException
    //   438	444	785	java/lang/ClassCastException
    //   467	473	800	java/lang/ClassCastException
    //   513	518	815	gnu/mapping/UnboundLocationException
    //   523	530	830	gnu/mapping/UnboundLocationException
  }
  
  public static Object stringConcatenateReverse$V(Object paramObject, Object[] paramArrayOfObject) {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: invokestatic makeList : ([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   5: astore #5
    //   7: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   10: astore #6
    //   12: getstatic gnu/kawa/slib/srfi13.loc$let$Mnoptionals$St : Lgnu/mapping/Location;
    //   15: astore_1
    //   16: aload_1
    //   17: invokevirtual get : ()Ljava/lang/Object;
    //   20: astore #7
    //   22: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   25: astore #4
    //   27: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   30: astore #8
    //   32: getstatic gnu/kawa/slib/srfi13.loc$final : Lgnu/mapping/Location;
    //   35: astore_1
    //   36: aload_1
    //   37: invokevirtual get : ()Ljava/lang/Object;
    //   40: astore #9
    //   42: getstatic gnu/kawa/slib/srfi13.loc$final : Lgnu/mapping/Location;
    //   45: astore_1
    //   46: aload_1
    //   47: invokevirtual get : ()Ljava/lang/Object;
    //   50: astore_1
    //   51: aload_1
    //   52: invokestatic isString : (Ljava/lang/Object;)Z
    //   55: ifeq -> 290
    //   58: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   61: astore_1
    //   62: aload #8
    //   64: aload #9
    //   66: ldc_w ''
    //   69: aload_1
    //   70: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   73: astore #8
    //   75: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   78: astore #9
    //   80: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   83: astore_1
    //   84: aload_1
    //   85: invokevirtual get : ()Ljava/lang/Object;
    //   88: astore #10
    //   90: getstatic gnu/kawa/slib/srfi13.loc$final : Lgnu/mapping/Location;
    //   93: astore_1
    //   94: aload_1
    //   95: invokevirtual get : ()Ljava/lang/Object;
    //   98: astore_1
    //   99: aload_1
    //   100: checkcast java/lang/CharSequence
    //   103: astore #11
    //   105: aload #11
    //   107: invokestatic stringLength : (Ljava/lang/CharSequence;)I
    //   110: istore_2
    //   111: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   114: astore_1
    //   115: aload_1
    //   116: invokevirtual get : ()Ljava/lang/Object;
    //   119: astore_1
    //   120: aload_1
    //   121: invokestatic isInteger : (Ljava/lang/Object;)Z
    //   124: istore_3
    //   125: iload_3
    //   126: ifeq -> 315
    //   129: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   132: astore_1
    //   133: aload_1
    //   134: invokevirtual get : ()Ljava/lang/Object;
    //   137: astore_1
    //   138: aload_1
    //   139: invokestatic isExact : (Ljava/lang/Object;)Z
    //   142: istore_3
    //   143: iload_3
    //   144: ifeq -> 297
    //   147: getstatic kawa/standard/Scheme.numLEq : Lgnu/kawa/functions/NumberCompare;
    //   150: astore_1
    //   151: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   154: astore #11
    //   156: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   159: astore #12
    //   161: aload #12
    //   163: invokevirtual get : ()Ljava/lang/Object;
    //   166: astore #12
    //   168: getstatic gnu/kawa/slib/srfi13.loc$final : Lgnu/mapping/Location;
    //   171: astore #13
    //   173: aload #13
    //   175: invokevirtual get : ()Ljava/lang/Object;
    //   178: astore #13
    //   180: aload #13
    //   182: checkcast java/lang/CharSequence
    //   185: astore #14
    //   187: aload_1
    //   188: aload #11
    //   190: aload #12
    //   192: aload #14
    //   194: invokestatic stringLength : (Ljava/lang/CharSequence;)I
    //   197: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   200: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   203: astore_1
    //   204: aload #4
    //   206: aload #8
    //   208: aload #9
    //   210: aload #10
    //   212: iload_2
    //   213: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   216: aload_1
    //   217: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   220: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   223: astore #8
    //   225: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   228: astore_1
    //   229: aload_0
    //   230: astore #4
    //   232: aload #4
    //   234: invokestatic isPair : (Ljava/lang/Object;)Z
    //   237: ifeq -> 333
    //   240: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   243: astore #10
    //   245: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
    //   248: aload #4
    //   250: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   253: astore #9
    //   255: aload #9
    //   257: checkcast java/lang/CharSequence
    //   260: astore #11
    //   262: aload #10
    //   264: aload_1
    //   265: aload #11
    //   267: invokestatic stringLength : (Ljava/lang/CharSequence;)I
    //   270: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   273: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   276: astore_1
    //   277: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
    //   280: aload #4
    //   282: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   285: astore #4
    //   287: goto -> 232
    //   290: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   293: astore_1
    //   294: goto -> 62
    //   297: iload_3
    //   298: ifeq -> 308
    //   301: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   304: astore_1
    //   305: goto -> 204
    //   308: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   311: astore_1
    //   312: goto -> 204
    //   315: iload_3
    //   316: ifeq -> 326
    //   319: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   322: astore_1
    //   323: goto -> 204
    //   326: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   329: astore_1
    //   330: goto -> 204
    //   333: getstatic gnu/kawa/slib/srfi13.loc$final : Lgnu/mapping/Location;
    //   336: astore #4
    //   338: aload #4
    //   340: invokevirtual get : ()Ljava/lang/Object;
    //   343: astore #4
    //   345: getstatic gnu/kawa/slib/srfi13.loc$end : Lgnu/mapping/Location;
    //   348: astore #9
    //   350: aload #9
    //   352: invokevirtual get : ()Ljava/lang/Object;
    //   355: astore #9
    //   357: aload #6
    //   359: aload #7
    //   361: aload #5
    //   363: aload #8
    //   365: aload_1
    //   366: aload_0
    //   367: aload #4
    //   369: aload #9
    //   371: invokestatic $PcFinishStringConcatenateReverse : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   374: invokevirtual apply4 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   377: areturn
    //   378: astore_0
    //   379: aload_0
    //   380: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   383: sipush #1617
    //   386: iconst_3
    //   387: invokevirtual setLine : (Ljava/lang/String;II)V
    //   390: aload_0
    //   391: athrow
    //   392: astore_0
    //   393: aload_0
    //   394: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   397: sipush #1617
    //   400: bipush #36
    //   402: invokevirtual setLine : (Ljava/lang/String;II)V
    //   405: aload_0
    //   406: athrow
    //   407: astore_0
    //   408: aload_0
    //   409: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   412: sipush #1617
    //   415: bipush #55
    //   417: invokevirtual setLine : (Ljava/lang/String;II)V
    //   420: aload_0
    //   421: athrow
    //   422: astore_0
    //   423: aload_0
    //   424: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   427: sipush #1618
    //   430: bipush #8
    //   432: invokevirtual setLine : (Ljava/lang/String;II)V
    //   435: aload_0
    //   436: athrow
    //   437: astore_0
    //   438: aload_0
    //   439: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   442: sipush #1618
    //   445: bipush #28
    //   447: invokevirtual setLine : (Ljava/lang/String;II)V
    //   450: aload_0
    //   451: athrow
    //   452: astore_0
    //   453: new gnu/mapping/WrongType
    //   456: dup
    //   457: aload_0
    //   458: ldc_w 'string-length'
    //   461: iconst_1
    //   462: aload_1
    //   463: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   466: athrow
    //   467: astore_0
    //   468: aload_0
    //   469: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   472: sipush #1619
    //   475: bipush #21
    //   477: invokevirtual setLine : (Ljava/lang/String;II)V
    //   480: aload_0
    //   481: athrow
    //   482: astore_0
    //   483: aload_0
    //   484: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   487: sipush #1620
    //   490: bipush #19
    //   492: invokevirtual setLine : (Ljava/lang/String;II)V
    //   495: aload_0
    //   496: athrow
    //   497: astore_0
    //   498: aload_0
    //   499: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   502: sipush #1621
    //   505: bipush #17
    //   507: invokevirtual setLine : (Ljava/lang/String;II)V
    //   510: aload_0
    //   511: athrow
    //   512: astore_0
    //   513: aload_0
    //   514: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   517: sipush #1621
    //   520: bipush #36
    //   522: invokevirtual setLine : (Ljava/lang/String;II)V
    //   525: aload_0
    //   526: athrow
    //   527: astore_0
    //   528: new gnu/mapping/WrongType
    //   531: dup
    //   532: aload_0
    //   533: ldc_w 'string-length'
    //   536: iconst_1
    //   537: aload #13
    //   539: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   542: athrow
    //   543: astore_0
    //   544: new gnu/mapping/WrongType
    //   547: dup
    //   548: aload_0
    //   549: ldc_w 'string-length'
    //   552: iconst_1
    //   553: aload #9
    //   555: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   558: athrow
    //   559: astore_0
    //   560: aload_0
    //   561: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   564: sipush #1627
    //   567: bipush #59
    //   569: invokevirtual setLine : (Ljava/lang/String;II)V
    //   572: aload_0
    //   573: athrow
    //   574: astore_0
    //   575: aload_0
    //   576: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   579: sipush #1627
    //   582: bipush #65
    //   584: invokevirtual setLine : (Ljava/lang/String;II)V
    //   587: aload_0
    //   588: athrow
    // Exception table:
    //   from	to	target	type
    //   16	22	378	gnu/mapping/UnboundLocationException
    //   36	42	392	gnu/mapping/UnboundLocationException
    //   46	51	407	gnu/mapping/UnboundLocationException
    //   84	90	422	gnu/mapping/UnboundLocationException
    //   94	99	437	gnu/mapping/UnboundLocationException
    //   99	105	452	java/lang/ClassCastException
    //   115	120	467	gnu/mapping/UnboundLocationException
    //   133	138	482	gnu/mapping/UnboundLocationException
    //   161	168	497	gnu/mapping/UnboundLocationException
    //   173	180	512	gnu/mapping/UnboundLocationException
    //   180	187	527	java/lang/ClassCastException
    //   255	262	543	java/lang/ClassCastException
    //   338	345	559	gnu/mapping/UnboundLocationException
    //   350	357	574	gnu/mapping/UnboundLocationException
  }
  
  public static Object stringContains$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame83 frame83 = new frame83();
    frame83.text = paramObject1;
    frame83.pattern = paramObject2;
    frame83.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame83.lambda$Fn189, (Procedure)frame83.lambda$Fn190);
  }
  
  public static Object stringContainsCi$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame85 frame85 = new frame85();
    frame85.text = paramObject1;
    frame85.pattern = paramObject2;
    frame85.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame85.lambda$Fn193, (Procedure)frame85.lambda$Fn194);
  }
  
  public static Object stringCopy$Ex(Object paramObject, int paramInt, CharSequence paramCharSequence) {
    return stringCopy$Ex(paramObject, paramInt, paramCharSequence, 0);
  }
  
  public static Object stringCopy$Ex(Object paramObject, int paramInt1, CharSequence paramCharSequence, int paramInt2) {
    return stringCopy$Ex(paramObject, paramInt1, paramCharSequence, paramInt2, paramCharSequence.length());
  }
  
  public static Object stringCopy$Ex(Object paramObject, int paramInt1, CharSequence paramCharSequence, int paramInt2, int paramInt3) {
    $PcCheckBounds(string$Mncopy$Ex, paramCharSequence, paramInt2, paramInt3);
    ModuleMethod moduleMethod = string$Mncopy$Ex;
    try {
      CharSequence charSequence = (CharSequence)paramObject;
      $PcCheckSubstringSpec(moduleMethod, charSequence, paramInt1, paramInt3 - paramInt2 + paramInt1);
      try {
        CharSequence charSequence1 = (CharSequence)paramObject;
        return $PcStringCopy$Ex(charSequence1, paramInt1, paramCharSequence, paramInt2, paramInt3);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "%string-copy!", 0, paramObject);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "%check-substring-spec", 1, paramObject);
    } 
  }
  
  public static Object stringCopy$V(Object paramObject, Object[] paramArrayOfObject) {
    frame2 frame2 = new frame2();
    frame2.s = paramObject;
    frame2.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame2.lambda$Fn7, (Procedure)frame2.lambda$Fn8);
  }
  
  public static Object stringCount$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame81 frame81 = new frame81();
    frame81.s = paramObject1;
    frame81.criterion = paramObject2;
    frame81.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame81.lambda$Fn185, (Procedure)frame81.lambda$Fn186);
  }
  
  public static Object stringDelete$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame73 frame73 = new frame73();
    frame73.criterion = paramObject1;
    frame73.s = paramObject2;
    frame73.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame73.lambda$Fn167, (Procedure)frame73.lambda$Fn168);
  }
  
  public static Object stringDowncase$Ex$V(Object paramObject, Object[] paramArrayOfObject) {
    frame61 frame61 = new frame61();
    frame61.s = paramObject;
    frame61.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame61.lambda$Fn145, (Procedure)frame61.lambda$Fn146);
  }
  
  public static Object stringDowncase$V(Object paramObject, Object[] paramArrayOfObject) {
    frame60 frame60 = new frame60();
    frame60.s = paramObject;
    frame60.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame60.lambda$Fn143, (Procedure)frame60.lambda$Fn144);
  }
  
  public static Object stringDrop(CharSequence paramCharSequence, Object paramObject) {
    frame66 frame66 = new frame66();
    frame66.n = paramObject;
    frame66.len = strings.stringLength(paramCharSequence);
    paramObject = Scheme.applyToArgs;
    Location location = loc$check$Mnarg;
    try {
      Object object = location.get();
      paramObject.apply4(object, frame66.lambda$Fn153, frame66.n, string$Mndrop);
      paramObject = frame66.n;
      try {
        int i = ((Number)paramObject).intValue();
        return $PcSubstring$SlShared(paramCharSequence, i, frame66.len);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "%substring/shared", 1, paramObject);
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1010, 5);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringDropRight(CharSequence paramCharSequence, Object paramObject) {
    frame67 frame67 = new frame67();
    frame67.n = paramObject;
    frame67.len = strings.stringLength(paramCharSequence);
    paramObject = Scheme.applyToArgs;
    Location location = loc$check$Mnarg;
    try {
      Object object = location.get();
      paramObject.apply4(object, frame67.lambda$Fn154, frame67.n, string$Mndrop$Mnright);
      paramObject = AddOp.$Mn.apply2(Integer.valueOf(frame67.len), frame67.n);
      try {
        int i = ((Number)paramObject).intValue();
        return $PcSubstring$SlShared(paramCharSequence, 0, i);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "%substring/shared", 2, paramObject);
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1016, 5);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringEvery$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame9 frame9 = new frame9();
    frame9.criterion = paramObject1;
    frame9.s = paramObject2;
    frame9.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame9.lambda$Fn23, (Procedure)frame9.lambda$Fn24);
  }
  
  public static Object stringFill$Ex$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame82 frame82 = new frame82();
    frame82.s = paramObject1;
    frame82.char = paramObject2;
    frame82.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.applyToArgs;
    paramObject2 = loc$check$Mnarg;
    try {
      paramObject2 = paramObject2.get();
      paramObject1.apply4(paramObject2, characters.char$Qu, frame82.char, string$Mnfill$Ex);
      return call_with_values.callWithValues((Procedure)frame82.lambda$Fn187, (Procedure)frame82.lambda$Fn188);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1270, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringFilter$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame75 frame75 = new frame75();
    frame75.criterion = paramObject1;
    frame75.s = paramObject2;
    frame75.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame75.lambda$Fn172, (Procedure)frame75.lambda$Fn173);
  }
  
  public static Object stringFold$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject) {
    frame5 frame5 = new frame5();
    frame5.kons = paramObject1;
    frame5.knil = paramObject2;
    frame5.s = paramObject3;
    frame5.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.applyToArgs;
    paramObject2 = loc$check$Mnarg;
    try {
      paramObject2 = paramObject2.get();
      paramObject1.apply4(paramObject2, misc.procedure$Qu, frame5.kons, string$Mnfold);
      return call_with_values.callWithValues((Procedure)frame5.lambda$Fn13, (Procedure)frame5.lambda$Fn14);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 295, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringFoldRight$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject) {
    frame6 frame6 = new frame6();
    frame6.kons = paramObject1;
    frame6.knil = paramObject2;
    frame6.s = paramObject3;
    frame6.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.applyToArgs;
    paramObject2 = loc$check$Mnarg;
    try {
      paramObject2 = paramObject2.get();
      paramObject1.apply4(paramObject2, misc.procedure$Qu, frame6.kons, string$Mnfold$Mnright);
      return call_with_values.callWithValues((Procedure)frame6.lambda$Fn15, (Procedure)frame6.lambda$Fn16);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 302, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringForEach$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame7 frame7 = new frame7();
    frame7.proc = paramObject1;
    frame7.s = paramObject2;
    frame7.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.applyToArgs;
    paramObject2 = loc$check$Mnarg;
    try {
      paramObject2 = paramObject2.get();
      paramObject1.apply4(paramObject2, misc.procedure$Qu, frame7.proc, string$Mnfor$Mneach);
      return call_with_values.callWithValues((Procedure)frame7.lambda$Fn19, (Procedure)frame7.lambda$Fn20);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 468, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringForEachIndex$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame8 frame8 = new frame8();
    frame8.proc = paramObject1;
    frame8.s = paramObject2;
    frame8.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.applyToArgs;
    paramObject2 = loc$check$Mnarg;
    try {
      paramObject2 = paramObject2.get();
      paramObject1.apply4(paramObject2, misc.procedure$Qu, frame8.proc, string$Mnfor$Mneach$Mnindex);
      return call_with_values.callWithValues((Procedure)frame8.lambda$Fn21, (Procedure)frame8.lambda$Fn22);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 476, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringHash$V(Object paramObject, Object[] paramArrayOfObject) {
    // Byte code:
    //   0: new gnu/kawa/slib/srfi13$frame56
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_3
    //   8: aload_3
    //   9: aload_0
    //   10: putfield s : Ljava/lang/Object;
    //   13: aload_1
    //   14: iconst_0
    //   15: invokestatic makeList : ([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   18: astore_1
    //   19: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   22: astore #4
    //   24: getstatic gnu/kawa/slib/srfi13.loc$let$Mnoptionals$St : Lgnu/mapping/Location;
    //   27: astore_0
    //   28: aload_0
    //   29: invokevirtual get : ()Ljava/lang/Object;
    //   32: astore #5
    //   34: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   37: astore #6
    //   39: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   42: astore #7
    //   44: getstatic gnu/kawa/slib/srfi13.loc$bound : Lgnu/mapping/Location;
    //   47: astore_0
    //   48: aload_0
    //   49: invokevirtual get : ()Ljava/lang/Object;
    //   52: astore #8
    //   54: getstatic gnu/kawa/slib/srfi13.Lit7 : Lgnu/math/IntNum;
    //   57: astore #9
    //   59: getstatic gnu/kawa/slib/srfi13.loc$bound : Lgnu/mapping/Location;
    //   62: astore_0
    //   63: aload_0
    //   64: invokevirtual get : ()Ljava/lang/Object;
    //   67: astore_0
    //   68: aload_0
    //   69: invokestatic isInteger : (Ljava/lang/Object;)Z
    //   72: istore_2
    //   73: iload_2
    //   74: ifeq -> 230
    //   77: getstatic gnu/kawa/slib/srfi13.loc$bound : Lgnu/mapping/Location;
    //   80: astore_0
    //   81: aload_0
    //   82: invokevirtual get : ()Ljava/lang/Object;
    //   85: astore_0
    //   86: aload_0
    //   87: invokestatic isExact : (Ljava/lang/Object;)Z
    //   90: istore_2
    //   91: iload_2
    //   92: ifeq -> 212
    //   95: getstatic kawa/standard/Scheme.numLEq : Lgnu/kawa/functions/NumberCompare;
    //   98: astore_0
    //   99: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   102: astore #10
    //   104: getstatic gnu/kawa/slib/srfi13.loc$bound : Lgnu/mapping/Location;
    //   107: astore #11
    //   109: aload #11
    //   111: invokevirtual get : ()Ljava/lang/Object;
    //   114: astore #11
    //   116: aload_0
    //   117: aload #10
    //   119: aload #11
    //   121: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   124: astore_0
    //   125: aload #7
    //   127: aload #8
    //   129: aload #9
    //   131: aload_0
    //   132: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   135: astore_0
    //   136: getstatic gnu/kawa/slib/srfi13.loc$rest : Lgnu/mapping/Location;
    //   139: astore #7
    //   141: aload #7
    //   143: invokevirtual get : ()Ljava/lang/Object;
    //   146: astore #7
    //   148: aload #6
    //   150: aload_0
    //   151: aload #7
    //   153: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   156: astore #6
    //   158: getstatic gnu/kawa/slib/srfi13.loc$bound : Lgnu/mapping/Location;
    //   161: astore_0
    //   162: aload_0
    //   163: invokevirtual get : ()Ljava/lang/Object;
    //   166: astore_0
    //   167: aload_0
    //   168: checkcast java/lang/Number
    //   171: astore #7
    //   173: aload #7
    //   175: invokestatic isZero : (Ljava/lang/Number;)Z
    //   178: ifeq -> 248
    //   181: getstatic gnu/kawa/slib/srfi13.Lit8 : Lgnu/math/IntNum;
    //   184: astore_0
    //   185: aload_3
    //   186: aload_0
    //   187: putfield bound : Ljava/lang/Object;
    //   190: aload #4
    //   192: aload #5
    //   194: aload_1
    //   195: aload #6
    //   197: aload_3
    //   198: getfield lambda$Fn134 : Lgnu/expr/ModuleMethod;
    //   201: aload_3
    //   202: getfield lambda$Fn135 : Lgnu/expr/ModuleMethod;
    //   205: invokestatic callWithValues : (Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;)Ljava/lang/Object;
    //   208: invokevirtual apply4 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   211: areturn
    //   212: iload_2
    //   213: ifeq -> 223
    //   216: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   219: astore_0
    //   220: goto -> 125
    //   223: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   226: astore_0
    //   227: goto -> 125
    //   230: iload_2
    //   231: ifeq -> 241
    //   234: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   237: astore_0
    //   238: goto -> 125
    //   241: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   244: astore_0
    //   245: goto -> 125
    //   248: getstatic gnu/kawa/slib/srfi13.loc$bound : Lgnu/mapping/Location;
    //   251: astore_0
    //   252: aload_0
    //   253: invokevirtual get : ()Ljava/lang/Object;
    //   256: astore_0
    //   257: goto -> 185
    //   260: astore_0
    //   261: aload_0
    //   262: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   265: sipush #907
    //   268: iconst_3
    //   269: invokevirtual setLine : (Ljava/lang/String;II)V
    //   272: aload_0
    //   273: athrow
    //   274: astore_0
    //   275: aload_0
    //   276: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   279: sipush #907
    //   282: bipush #42
    //   284: invokevirtual setLine : (Ljava/lang/String;II)V
    //   287: aload_0
    //   288: athrow
    //   289: astore_0
    //   290: aload_0
    //   291: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   294: sipush #907
    //   297: bipush #72
    //   299: invokevirtual setLine : (Ljava/lang/String;II)V
    //   302: aload_0
    //   303: athrow
    //   304: astore_0
    //   305: aload_0
    //   306: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   309: sipush #908
    //   312: bipush #21
    //   314: invokevirtual setLine : (Ljava/lang/String;II)V
    //   317: aload_0
    //   318: athrow
    //   319: astore_0
    //   320: aload_0
    //   321: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   324: sipush #909
    //   327: bipush #19
    //   329: invokevirtual setLine : (Ljava/lang/String;II)V
    //   332: aload_0
    //   333: athrow
    //   334: astore_0
    //   335: aload_0
    //   336: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   339: sipush #910
    //   342: bipush #7
    //   344: invokevirtual setLine : (Ljava/lang/String;II)V
    //   347: aload_0
    //   348: athrow
    //   349: astore_0
    //   350: aload_0
    //   351: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   354: sipush #911
    //   357: bipush #29
    //   359: invokevirtual setLine : (Ljava/lang/String;II)V
    //   362: aload_0
    //   363: athrow
    //   364: astore_1
    //   365: new gnu/mapping/WrongType
    //   368: dup
    //   369: aload_1
    //   370: ldc_w 'zero?'
    //   373: iconst_1
    //   374: aload_0
    //   375: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   378: athrow
    //   379: astore_0
    //   380: aload_0
    //   381: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   384: sipush #911
    //   387: bipush #18
    //   389: invokevirtual setLine : (Ljava/lang/String;II)V
    //   392: aload_0
    //   393: athrow
    // Exception table:
    //   from	to	target	type
    //   28	34	260	gnu/mapping/UnboundLocationException
    //   48	54	274	gnu/mapping/UnboundLocationException
    //   63	68	289	gnu/mapping/UnboundLocationException
    //   81	86	304	gnu/mapping/UnboundLocationException
    //   109	116	319	gnu/mapping/UnboundLocationException
    //   141	148	334	gnu/mapping/UnboundLocationException
    //   162	167	349	gnu/mapping/UnboundLocationException
    //   167	173	364	java/lang/ClassCastException
    //   252	257	379	gnu/mapping/UnboundLocationException
  }
  
  public static Object stringHashCi$V(Object paramObject, Object[] paramArrayOfObject) {
    // Byte code:
    //   0: new gnu/kawa/slib/srfi13$frame57
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_3
    //   8: aload_3
    //   9: aload_0
    //   10: putfield s : Ljava/lang/Object;
    //   13: aload_1
    //   14: iconst_0
    //   15: invokestatic makeList : ([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   18: astore_1
    //   19: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   22: astore #4
    //   24: getstatic gnu/kawa/slib/srfi13.loc$let$Mnoptionals$St : Lgnu/mapping/Location;
    //   27: astore_0
    //   28: aload_0
    //   29: invokevirtual get : ()Ljava/lang/Object;
    //   32: astore #5
    //   34: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   37: astore #6
    //   39: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   42: astore #7
    //   44: getstatic gnu/kawa/slib/srfi13.loc$bound : Lgnu/mapping/Location;
    //   47: astore_0
    //   48: aload_0
    //   49: invokevirtual get : ()Ljava/lang/Object;
    //   52: astore #8
    //   54: getstatic gnu/kawa/slib/srfi13.Lit9 : Lgnu/math/IntNum;
    //   57: astore #9
    //   59: getstatic gnu/kawa/slib/srfi13.loc$bound : Lgnu/mapping/Location;
    //   62: astore_0
    //   63: aload_0
    //   64: invokevirtual get : ()Ljava/lang/Object;
    //   67: astore_0
    //   68: aload_0
    //   69: invokestatic isInteger : (Ljava/lang/Object;)Z
    //   72: istore_2
    //   73: iload_2
    //   74: ifeq -> 230
    //   77: getstatic gnu/kawa/slib/srfi13.loc$bound : Lgnu/mapping/Location;
    //   80: astore_0
    //   81: aload_0
    //   82: invokevirtual get : ()Ljava/lang/Object;
    //   85: astore_0
    //   86: aload_0
    //   87: invokestatic isExact : (Ljava/lang/Object;)Z
    //   90: istore_2
    //   91: iload_2
    //   92: ifeq -> 212
    //   95: getstatic kawa/standard/Scheme.numLEq : Lgnu/kawa/functions/NumberCompare;
    //   98: astore_0
    //   99: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   102: astore #10
    //   104: getstatic gnu/kawa/slib/srfi13.loc$bound : Lgnu/mapping/Location;
    //   107: astore #11
    //   109: aload #11
    //   111: invokevirtual get : ()Ljava/lang/Object;
    //   114: astore #11
    //   116: aload_0
    //   117: aload #10
    //   119: aload #11
    //   121: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   124: astore_0
    //   125: aload #7
    //   127: aload #8
    //   129: aload #9
    //   131: aload_0
    //   132: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   135: astore_0
    //   136: getstatic gnu/kawa/slib/srfi13.loc$rest : Lgnu/mapping/Location;
    //   139: astore #7
    //   141: aload #7
    //   143: invokevirtual get : ()Ljava/lang/Object;
    //   146: astore #7
    //   148: aload #6
    //   150: aload_0
    //   151: aload #7
    //   153: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   156: astore #6
    //   158: getstatic gnu/kawa/slib/srfi13.loc$bound : Lgnu/mapping/Location;
    //   161: astore_0
    //   162: aload_0
    //   163: invokevirtual get : ()Ljava/lang/Object;
    //   166: astore_0
    //   167: aload_0
    //   168: checkcast java/lang/Number
    //   171: astore #7
    //   173: aload #7
    //   175: invokestatic isZero : (Ljava/lang/Number;)Z
    //   178: ifeq -> 248
    //   181: getstatic gnu/kawa/slib/srfi13.Lit10 : Lgnu/math/IntNum;
    //   184: astore_0
    //   185: aload_3
    //   186: aload_0
    //   187: putfield bound : Ljava/lang/Object;
    //   190: aload #4
    //   192: aload #5
    //   194: aload_1
    //   195: aload #6
    //   197: aload_3
    //   198: getfield lambda$Fn136 : Lgnu/expr/ModuleMethod;
    //   201: aload_3
    //   202: getfield lambda$Fn137 : Lgnu/expr/ModuleMethod;
    //   205: invokestatic callWithValues : (Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;)Ljava/lang/Object;
    //   208: invokevirtual apply4 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   211: areturn
    //   212: iload_2
    //   213: ifeq -> 223
    //   216: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   219: astore_0
    //   220: goto -> 125
    //   223: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   226: astore_0
    //   227: goto -> 125
    //   230: iload_2
    //   231: ifeq -> 241
    //   234: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   237: astore_0
    //   238: goto -> 125
    //   241: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   244: astore_0
    //   245: goto -> 125
    //   248: getstatic gnu/kawa/slib/srfi13.loc$bound : Lgnu/mapping/Location;
    //   251: astore_0
    //   252: aload_0
    //   253: invokevirtual get : ()Ljava/lang/Object;
    //   256: astore_0
    //   257: goto -> 185
    //   260: astore_0
    //   261: aload_0
    //   262: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   265: sipush #916
    //   268: iconst_3
    //   269: invokevirtual setLine : (Ljava/lang/String;II)V
    //   272: aload_0
    //   273: athrow
    //   274: astore_0
    //   275: aload_0
    //   276: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   279: sipush #916
    //   282: bipush #42
    //   284: invokevirtual setLine : (Ljava/lang/String;II)V
    //   287: aload_0
    //   288: athrow
    //   289: astore_0
    //   290: aload_0
    //   291: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   294: sipush #916
    //   297: bipush #72
    //   299: invokevirtual setLine : (Ljava/lang/String;II)V
    //   302: aload_0
    //   303: athrow
    //   304: astore_0
    //   305: aload_0
    //   306: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   309: sipush #917
    //   312: bipush #21
    //   314: invokevirtual setLine : (Ljava/lang/String;II)V
    //   317: aload_0
    //   318: athrow
    //   319: astore_0
    //   320: aload_0
    //   321: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   324: sipush #918
    //   327: bipush #19
    //   329: invokevirtual setLine : (Ljava/lang/String;II)V
    //   332: aload_0
    //   333: athrow
    //   334: astore_0
    //   335: aload_0
    //   336: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   339: sipush #919
    //   342: bipush #7
    //   344: invokevirtual setLine : (Ljava/lang/String;II)V
    //   347: aload_0
    //   348: athrow
    //   349: astore_0
    //   350: aload_0
    //   351: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   354: sipush #920
    //   357: bipush #29
    //   359: invokevirtual setLine : (Ljava/lang/String;II)V
    //   362: aload_0
    //   363: athrow
    //   364: astore_1
    //   365: new gnu/mapping/WrongType
    //   368: dup
    //   369: aload_1
    //   370: ldc_w 'zero?'
    //   373: iconst_1
    //   374: aload_0
    //   375: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   378: athrow
    //   379: astore_0
    //   380: aload_0
    //   381: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   384: sipush #920
    //   387: bipush #18
    //   389: invokevirtual setLine : (Ljava/lang/String;II)V
    //   392: aload_0
    //   393: athrow
    // Exception table:
    //   from	to	target	type
    //   28	34	260	gnu/mapping/UnboundLocationException
    //   48	54	274	gnu/mapping/UnboundLocationException
    //   63	68	289	gnu/mapping/UnboundLocationException
    //   81	86	304	gnu/mapping/UnboundLocationException
    //   109	116	319	gnu/mapping/UnboundLocationException
    //   141	148	334	gnu/mapping/UnboundLocationException
    //   162	167	349	gnu/mapping/UnboundLocationException
    //   167	173	364	java/lang/ClassCastException
    //   252	257	379	gnu/mapping/UnboundLocationException
  }
  
  public static Object stringIndex$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame77 frame77 = new frame77();
    frame77.str = paramObject1;
    frame77.criterion = paramObject2;
    frame77.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame77.lambda$Fn177, (Procedure)frame77.lambda$Fn178);
  }
  
  public static Object stringIndexRight$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame78 frame78 = new frame78();
    frame78.str = paramObject1;
    frame78.criterion = paramObject2;
    frame78.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame78.lambda$Fn179, (Procedure)frame78.lambda$Fn180);
  }
  
  public static Object stringJoin$V(Object paramObject, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    Location location = loc$let$Mnoptionals$St;
    try {
      Object object = location.get();
      ApplyToArgs applyToArgs1 = Scheme.applyToArgs;
      ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
      location = loc$delim;
      try {
        Object object1 = location.get();
        location = loc$delim;
        try {
          Object object2 = location.get();
          if (strings.isString(object2)) {
            object2 = Boolean.TRUE;
          } else {
            object2 = Boolean.FALSE;
          } 
          object2 = applyToArgs2.apply3(object1, " ", object2);
          applyToArgs2 = Scheme.applyToArgs;
          object1 = loc$grammar;
          try {
            object1 = object1.get();
            object2 = applyToArgs1.apply2(object2, applyToArgs2.apply2(object1, Lit15));
            if (lists.isPair(paramObject)) {
              Location location2 = loc$grammar;
              try {
                Object object3 = location2.get();
                Object object4 = Scheme.isEqv.apply2(object3, Lit15);
                if ((object4 != Boolean.FALSE) ? (object4 != Boolean.FALSE) : (Scheme.isEqv.apply2(object3, Lit16) != Boolean.FALSE)) {
                  paramObject = lists.cons(lists.car.apply1(paramObject), lambda222buildit(lists.cdr.apply1(paramObject), LList.Empty));
                } else if (Scheme.isEqv.apply2(object3, Lit17) != Boolean.FALSE) {
                  paramObject = lambda222buildit(paramObject, LList.Empty);
                } else {
                  if (Scheme.isEqv.apply2(object3, Lit18) != Boolean.FALSE) {
                    object3 = lists.car.apply1(paramObject);
                    paramObject = lists.cdr.apply1(paramObject);
                    object4 = loc$delim;
                    try {
                      object4 = object4.get();
                      paramObject = lists.cons(object3, lambda222buildit(paramObject, LList.list1(object4)));
                      paramObject = stringConcatenate(paramObject);
                    } catch (UnboundLocationException null) {
                      unboundLocationException1.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1870, 53);
                      throw unboundLocationException1;
                    } 
                    return applyToArgs.apply4(object, lList, object2, unboundLocationException1);
                  } 
                  paramObject = loc$grammar;
                  try {
                    paramObject = paramObject.get();
                    paramObject = misc.error$V("Illegal join grammar", new Object[] { paramObject, string$Mnjoin });
                    paramObject = stringConcatenate(paramObject);
                  } catch (UnboundLocationException unboundLocationException1) {
                    unboundLocationException1.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1873, 9);
                    throw unboundLocationException1;
                  } 
                } 
                CharSequence charSequence = stringConcatenate(unboundLocationException1);
              } catch (UnboundLocationException unboundLocationException) {
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1862, 14);
                throw unboundLocationException;
              } 
            } 
            if (!lists.isNull(unboundLocationException)) {
              Object object3 = misc.error$V("STRINGS parameter not list.", new Object[] { unboundLocationException, string$Mnjoin });
              return applyToArgs.apply4(object, lList, object2, object3);
            } 
            Location location1 = loc$grammar;
            try {
              Object object3 = location1.get();
              if (object3 == Lit16) {
                object3 = misc.error$V("Empty list cannot be joined with STRICT-INFIX grammar.", new Object[] { string$Mnjoin });
                return applyToArgs.apply4(object, lList, object2, object3);
              } 
              object3 = "";
            } catch (UnboundLocationException unboundLocationException1) {
              unboundLocationException1.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1880, 13);
              throw unboundLocationException1;
            } 
          } catch (UnboundLocationException unboundLocationException) {
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1853, 6);
            throw unboundLocationException;
          } 
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1852, 54);
          throw unboundLocationException;
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1852, 34);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1852, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringKmpPartialSearch$V(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object[] paramArrayOfObject) {
    // Byte code:
    //   0: new gnu/kawa/slib/srfi13$frame88
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #8
    //   9: aload #8
    //   11: aload_2
    //   12: putfield s : Ljava/lang/Object;
    //   15: aload #4
    //   17: iconst_0
    //   18: invokestatic makeList : ([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   21: astore #9
    //   23: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   26: astore_2
    //   27: getstatic gnu/kawa/slib/srfi13.loc$check$Mnarg : Lgnu/mapping/Location;
    //   30: astore #4
    //   32: aload #4
    //   34: invokevirtual get : ()Ljava/lang/Object;
    //   37: astore #4
    //   39: aload_2
    //   40: aload #4
    //   42: getstatic kawa/lib/vectors.vector$Qu : Lgnu/expr/ModuleMethod;
    //   45: aload_1
    //   46: getstatic gnu/kawa/slib/srfi13.string$Mnkmp$Mnpartial$Mnsearch : Lgnu/expr/ModuleMethod;
    //   49: invokevirtual apply4 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   52: pop
    //   53: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   56: astore #10
    //   58: getstatic gnu/kawa/slib/srfi13.loc$let$Mnoptionals$St : Lgnu/mapping/Location;
    //   61: astore_2
    //   62: aload_2
    //   63: invokevirtual get : ()Ljava/lang/Object;
    //   66: astore #11
    //   68: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   71: astore #4
    //   73: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   76: astore #12
    //   78: getstatic gnu/kawa/slib/srfi13.loc$c$Eq : Lgnu/mapping/Location;
    //   81: astore_2
    //   82: aload_2
    //   83: invokevirtual get : ()Ljava/lang/Object;
    //   86: astore #13
    //   88: getstatic kawa/lib/characters.char$Eq$Qu : Lgnu/expr/ModuleMethod;
    //   91: astore #14
    //   93: getstatic gnu/kawa/slib/srfi13.loc$c$Eq : Lgnu/mapping/Location;
    //   96: astore_2
    //   97: aload_2
    //   98: invokevirtual get : ()Ljava/lang/Object;
    //   101: astore_2
    //   102: aload_2
    //   103: invokestatic isProcedure : (Ljava/lang/Object;)Z
    //   106: ifeq -> 394
    //   109: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   112: astore_2
    //   113: aload #12
    //   115: aload #13
    //   117: aload #14
    //   119: aload_2
    //   120: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   123: astore #12
    //   125: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   128: astore #13
    //   130: getstatic gnu/kawa/slib/srfi13.loc$p$Mnstart : Lgnu/mapping/Location;
    //   133: astore_2
    //   134: aload_2
    //   135: invokevirtual get : ()Ljava/lang/Object;
    //   138: astore #14
    //   140: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   143: astore #15
    //   145: getstatic gnu/kawa/slib/srfi13.loc$p$Mnstart : Lgnu/mapping/Location;
    //   148: astore_2
    //   149: aload_2
    //   150: invokevirtual get : ()Ljava/lang/Object;
    //   153: astore_2
    //   154: aload_2
    //   155: invokestatic isInteger : (Ljava/lang/Object;)Z
    //   158: istore #7
    //   160: iload #7
    //   162: ifeq -> 420
    //   165: getstatic gnu/kawa/slib/srfi13.loc$p$Mnstart : Lgnu/mapping/Location;
    //   168: astore_2
    //   169: aload_2
    //   170: invokevirtual get : ()Ljava/lang/Object;
    //   173: astore_2
    //   174: aload_2
    //   175: invokestatic isExact : (Ljava/lang/Object;)Z
    //   178: istore #7
    //   180: iload #7
    //   182: ifeq -> 401
    //   185: getstatic kawa/standard/Scheme.numLEq : Lgnu/kawa/functions/NumberCompare;
    //   188: astore_2
    //   189: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   192: astore #16
    //   194: getstatic gnu/kawa/slib/srfi13.loc$p$Mnstart : Lgnu/mapping/Location;
    //   197: astore #17
    //   199: aload #17
    //   201: invokevirtual get : ()Ljava/lang/Object;
    //   204: astore #17
    //   206: aload_2
    //   207: aload #16
    //   209: aload #17
    //   211: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   214: astore_2
    //   215: aload #13
    //   217: aload #14
    //   219: aload #15
    //   221: aload_2
    //   222: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   225: astore_2
    //   226: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   229: astore #13
    //   231: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   234: astore #14
    //   236: getstatic gnu/kawa/slib/srfi13.loc$s$Mnstart : Lgnu/mapping/Location;
    //   239: astore #15
    //   241: aload #15
    //   243: invokevirtual get : ()Ljava/lang/Object;
    //   246: astore #15
    //   248: getstatic gnu/kawa/slib/srfi13.loc$s$Mnend : Lgnu/mapping/Location;
    //   251: astore #16
    //   253: aload #16
    //   255: invokevirtual get : ()Ljava/lang/Object;
    //   258: astore #16
    //   260: aload #4
    //   262: aload #12
    //   264: aload_2
    //   265: aload #13
    //   267: aload #14
    //   269: aload #15
    //   271: aload #16
    //   273: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   276: aload #8
    //   278: getfield lambda$Fn198 : Lgnu/expr/ModuleMethod;
    //   281: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   284: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   287: astore #12
    //   289: aload_1
    //   290: checkcast gnu/lists/FVector
    //   293: astore_2
    //   294: aload #8
    //   296: aload_2
    //   297: invokestatic vectorLength : (Lgnu/lists/FVector;)I
    //   300: putfield patlen : I
    //   303: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   306: astore_2
    //   307: getstatic gnu/kawa/slib/srfi13.loc$check$Mnarg : Lgnu/mapping/Location;
    //   310: astore #4
    //   312: aload #4
    //   314: invokevirtual get : ()Ljava/lang/Object;
    //   317: astore #4
    //   319: aload_2
    //   320: aload #4
    //   322: aload #8
    //   324: getfield lambda$Fn199 : Lgnu/expr/ModuleMethod;
    //   327: aload_3
    //   328: getstatic gnu/kawa/slib/srfi13.string$Mnkmp$Mnpartial$Mnsearch : Lgnu/expr/ModuleMethod;
    //   331: invokevirtual apply4 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   334: pop
    //   335: getstatic gnu/kawa/slib/srfi13.loc$s$Mnstart : Lgnu/mapping/Location;
    //   338: astore_2
    //   339: aload_2
    //   340: invokevirtual get : ()Ljava/lang/Object;
    //   343: astore #4
    //   345: aload_3
    //   346: astore_2
    //   347: aload #4
    //   349: astore_3
    //   350: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
    //   353: aload_2
    //   354: aload #8
    //   356: getfield patlen : I
    //   359: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   362: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   365: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   368: if_acmpeq -> 439
    //   371: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
    //   374: aload_3
    //   375: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   378: astore #4
    //   380: aload #10
    //   382: aload #11
    //   384: aload #9
    //   386: aload #12
    //   388: aload #4
    //   390: invokevirtual apply4 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   393: areturn
    //   394: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   397: astore_2
    //   398: goto -> 113
    //   401: iload #7
    //   403: ifeq -> 413
    //   406: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   409: astore_2
    //   410: goto -> 215
    //   413: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   416: astore_2
    //   417: goto -> 215
    //   420: iload #7
    //   422: ifeq -> 432
    //   425: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   428: astore_2
    //   429: goto -> 215
    //   432: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   435: astore_2
    //   436: goto -> 215
    //   439: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
    //   442: astore #13
    //   444: getstatic gnu/kawa/slib/srfi13.loc$s$Mnend : Lgnu/mapping/Location;
    //   447: astore #4
    //   449: aload #4
    //   451: invokevirtual get : ()Ljava/lang/Object;
    //   454: astore #14
    //   456: aload_2
    //   457: astore #4
    //   459: aload #13
    //   461: aload_3
    //   462: aload #14
    //   464: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   467: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   470: if_acmpne -> 380
    //   473: aload #8
    //   475: getfield s : Ljava/lang/Object;
    //   478: astore #4
    //   480: aload #4
    //   482: checkcast java/lang/CharSequence
    //   485: astore #13
    //   487: aload_3
    //   488: checkcast java/lang/Number
    //   491: invokevirtual intValue : ()I
    //   494: istore #5
    //   496: aload #13
    //   498: iload #5
    //   500: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   503: istore #5
    //   505: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   508: aload_3
    //   509: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
    //   512: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   515: astore_3
    //   516: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   519: astore #4
    //   521: getstatic gnu/kawa/slib/srfi13.loc$c$Eq : Lgnu/mapping/Location;
    //   524: astore #13
    //   526: aload #13
    //   528: invokevirtual get : ()Ljava/lang/Object;
    //   531: astore #13
    //   533: iload #5
    //   535: invokestatic make : (I)Lgnu/text/Char;
    //   538: astore #14
    //   540: aload_0
    //   541: checkcast java/lang/CharSequence
    //   544: astore #15
    //   546: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   549: astore #16
    //   551: getstatic gnu/kawa/slib/srfi13.loc$p$Mnstart : Lgnu/mapping/Location;
    //   554: astore #17
    //   556: aload #17
    //   558: invokevirtual get : ()Ljava/lang/Object;
    //   561: astore #17
    //   563: aload #16
    //   565: aload_2
    //   566: aload #17
    //   568: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   571: astore #16
    //   573: aload #16
    //   575: checkcast java/lang/Number
    //   578: invokevirtual intValue : ()I
    //   581: istore #6
    //   583: aload #4
    //   585: aload #13
    //   587: aload #14
    //   589: aload #15
    //   591: iload #6
    //   593: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
    //   596: invokestatic make : (I)Lgnu/text/Char;
    //   599: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   602: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   605: if_acmpeq -> 622
    //   608: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
    //   611: aload_2
    //   612: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
    //   615: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   618: astore_2
    //   619: goto -> 350
    //   622: aload_1
    //   623: checkcast gnu/lists/FVector
    //   626: astore #4
    //   628: aload_2
    //   629: checkcast java/lang/Number
    //   632: invokevirtual intValue : ()I
    //   635: istore #6
    //   637: aload #4
    //   639: iload #6
    //   641: invokestatic vectorRef : (Lgnu/lists/FVector;I)Ljava/lang/Object;
    //   644: astore #4
    //   646: aload #4
    //   648: astore_2
    //   649: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
    //   652: aload #4
    //   654: getstatic gnu/kawa/slib/srfi13.Lit13 : Lgnu/math/IntNum;
    //   657: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   660: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   663: if_acmpeq -> 516
    //   666: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
    //   669: astore_2
    //   670: goto -> 619
    //   673: astore_0
    //   674: aload_0
    //   675: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   678: sipush #1464
    //   681: iconst_3
    //   682: invokevirtual setLine : (Ljava/lang/String;II)V
    //   685: aload_0
    //   686: athrow
    //   687: astore_0
    //   688: aload_0
    //   689: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   692: sipush #1465
    //   695: iconst_3
    //   696: invokevirtual setLine : (Ljava/lang/String;II)V
    //   699: aload_0
    //   700: athrow
    //   701: astore_0
    //   702: aload_0
    //   703: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   706: sipush #1466
    //   709: bipush #6
    //   711: invokevirtual setLine : (Ljava/lang/String;II)V
    //   714: aload_0
    //   715: athrow
    //   716: astore_0
    //   717: aload_0
    //   718: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   721: sipush #1466
    //   724: bipush #34
    //   726: invokevirtual setLine : (Ljava/lang/String;II)V
    //   729: aload_0
    //   730: athrow
    //   731: astore_0
    //   732: aload_0
    //   733: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   736: sipush #1467
    //   739: bipush #6
    //   741: invokevirtual setLine : (Ljava/lang/String;II)V
    //   744: aload_0
    //   745: athrow
    //   746: astore_0
    //   747: aload_0
    //   748: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   751: sipush #1467
    //   754: bipush #32
    //   756: invokevirtual setLine : (Ljava/lang/String;II)V
    //   759: aload_0
    //   760: athrow
    //   761: astore_0
    //   762: aload_0
    //   763: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   766: sipush #1467
    //   769: bipush #49
    //   771: invokevirtual setLine : (Ljava/lang/String;II)V
    //   774: aload_0
    //   775: athrow
    //   776: astore_0
    //   777: aload_0
    //   778: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   781: sipush #1467
    //   784: bipush #64
    //   786: invokevirtual setLine : (Ljava/lang/String;II)V
    //   789: aload_0
    //   790: athrow
    //   791: astore_0
    //   792: aload_0
    //   793: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   796: sipush #1468
    //   799: bipush #7
    //   801: invokevirtual setLine : (Ljava/lang/String;II)V
    //   804: aload_0
    //   805: athrow
    //   806: astore_0
    //   807: aload_0
    //   808: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   811: sipush #1468
    //   814: bipush #16
    //   816: invokevirtual setLine : (Ljava/lang/String;II)V
    //   819: aload_0
    //   820: athrow
    //   821: astore_0
    //   822: new gnu/mapping/WrongType
    //   825: dup
    //   826: aload_0
    //   827: ldc_w 'vector-length'
    //   830: iconst_1
    //   831: aload_1
    //   832: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   835: athrow
    //   836: astore_0
    //   837: aload_0
    //   838: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   841: sipush #1472
    //   844: bipush #7
    //   846: invokevirtual setLine : (Ljava/lang/String;II)V
    //   849: aload_0
    //   850: athrow
    //   851: astore_0
    //   852: aload_0
    //   853: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   856: sipush #1476
    //   859: bipush #7
    //   861: invokevirtual setLine : (Ljava/lang/String;II)V
    //   864: aload_0
    //   865: athrow
    //   866: astore_0
    //   867: aload_0
    //   868: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   871: sipush #1479
    //   874: bipush #15
    //   876: invokevirtual setLine : (Ljava/lang/String;II)V
    //   879: aload_0
    //   880: athrow
    //   881: astore_0
    //   882: new gnu/mapping/WrongType
    //   885: dup
    //   886: aload_0
    //   887: ldc_w 'string-ref'
    //   890: iconst_1
    //   891: aload #4
    //   893: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   896: athrow
    //   897: astore_0
    //   898: new gnu/mapping/WrongType
    //   901: dup
    //   902: aload_0
    //   903: ldc_w 'string-ref'
    //   906: iconst_2
    //   907: aload_3
    //   908: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   911: athrow
    //   912: astore_0
    //   913: aload_0
    //   914: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   917: sipush #1484
    //   920: bipush #14
    //   922: invokevirtual setLine : (Ljava/lang/String;II)V
    //   925: aload_0
    //   926: athrow
    //   927: astore_1
    //   928: new gnu/mapping/WrongType
    //   931: dup
    //   932: aload_1
    //   933: ldc_w 'string-ref'
    //   936: iconst_1
    //   937: aload_0
    //   938: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   941: athrow
    //   942: astore_0
    //   943: aload_0
    //   944: ldc_w '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
    //   947: sipush #1484
    //   950: bipush #42
    //   952: invokevirtual setLine : (Ljava/lang/String;II)V
    //   955: aload_0
    //   956: athrow
    //   957: astore_0
    //   958: new gnu/mapping/WrongType
    //   961: dup
    //   962: aload_0
    //   963: ldc_w 'string-ref'
    //   966: iconst_2
    //   967: aload #16
    //   969: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   972: athrow
    //   973: astore_0
    //   974: new gnu/mapping/WrongType
    //   977: dup
    //   978: aload_0
    //   979: ldc_w 'vector-ref'
    //   982: iconst_1
    //   983: aload_1
    //   984: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   987: athrow
    //   988: astore_0
    //   989: new gnu/mapping/WrongType
    //   992: dup
    //   993: aload_0
    //   994: ldc_w 'vector-ref'
    //   997: iconst_2
    //   998: aload_2
    //   999: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1002: athrow
    // Exception table:
    //   from	to	target	type
    //   32	39	673	gnu/mapping/UnboundLocationException
    //   62	68	687	gnu/mapping/UnboundLocationException
    //   82	88	701	gnu/mapping/UnboundLocationException
    //   97	102	716	gnu/mapping/UnboundLocationException
    //   134	140	731	gnu/mapping/UnboundLocationException
    //   149	154	746	gnu/mapping/UnboundLocationException
    //   169	174	761	gnu/mapping/UnboundLocationException
    //   199	206	776	gnu/mapping/UnboundLocationException
    //   241	248	791	gnu/mapping/UnboundLocationException
    //   253	260	806	gnu/mapping/UnboundLocationException
    //   289	294	821	java/lang/ClassCastException
    //   312	319	836	gnu/mapping/UnboundLocationException
    //   339	345	851	gnu/mapping/UnboundLocationException
    //   449	456	866	gnu/mapping/UnboundLocationException
    //   480	487	881	java/lang/ClassCastException
    //   487	496	897	java/lang/ClassCastException
    //   526	533	912	gnu/mapping/UnboundLocationException
    //   540	546	927	java/lang/ClassCastException
    //   556	563	942	gnu/mapping/UnboundLocationException
    //   573	583	957	java/lang/ClassCastException
    //   622	628	973	java/lang/ClassCastException
    //   628	637	988	java/lang/ClassCastException
  }
  
  public static Object stringMap$Ex$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame4 frame4 = new frame4();
    frame4.proc = paramObject1;
    frame4.s = paramObject2;
    frame4.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.applyToArgs;
    paramObject2 = loc$check$Mnarg;
    try {
      paramObject2 = paramObject2.get();
      paramObject1.apply4(paramObject2, misc.procedure$Qu, frame4.proc, string$Mnmap$Ex);
      return call_with_values.callWithValues((Procedure)frame4.lambda$Fn11, (Procedure)frame4.lambda$Fn12);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 285, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringMap$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame3 frame3 = new frame3();
    frame3.proc = paramObject1;
    frame3.s = paramObject2;
    frame3.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.applyToArgs;
    paramObject2 = loc$check$Mnarg;
    try {
      paramObject2 = paramObject2.get();
      paramObject1.apply4(paramObject2, misc.procedure$Qu, frame3.proc, string$Mnmap);
      return call_with_values.callWithValues((Procedure)frame3.lambda$Fn9, (Procedure)frame3.lambda$Fn10);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 271, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringPad$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame72 frame72 = new frame72();
    frame72.s = paramObject1;
    frame72.n = paramObject2;
    paramObject2 = LList.makeList(paramArrayOfObject, 0);
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    paramObject1 = loc$let$Mnoptionals$St;
    try {
      Object object = paramObject1.get();
      ApplyToArgs applyToArgs1 = Scheme.applyToArgs;
      Invoke invoke = Invoke.make;
      LangPrimType langPrimType = LangPrimType.charType;
      Char char_ = Lit12;
      if (characters.isChar(LangPrimType.charType)) {
        paramObject1 = Boolean.TRUE;
      } else {
        paramObject1 = Boolean.FALSE;
      } 
      paramObject1 = invoke.apply3(langPrimType, char_, paramObject1);
      Location location = loc$rest;
      try {
        Object object1 = location.get();
        return applyToArgs.apply4(object, paramObject2, applyToArgs1.apply2(paramObject1, object1), call_with_values.callWithValues((Procedure)frame72.lambda$Fn164, (Procedure)frame72.lambda$Fn165));
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1057, 63);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1057, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringPadRight$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame71 frame71 = new frame71();
    frame71.s = paramObject1;
    frame71.n = paramObject2;
    paramObject2 = LList.makeList(paramArrayOfObject, 0);
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    paramObject1 = loc$let$Mnoptionals$St;
    try {
      Object object = paramObject1.get();
      ApplyToArgs applyToArgs1 = Scheme.applyToArgs;
      Invoke invoke = Invoke.make;
      LangPrimType langPrimType = LangPrimType.charType;
      Char char_ = Lit12;
      if (characters.isChar(LangPrimType.charType)) {
        paramObject1 = Boolean.TRUE;
      } else {
        paramObject1 = Boolean.FALSE;
      } 
      paramObject1 = invoke.apply3(langPrimType, char_, paramObject1);
      Location location = loc$rest;
      try {
        Object object1 = location.get();
        return applyToArgs.apply4(object, paramObject2, applyToArgs1.apply2(paramObject1, object1), call_with_values.callWithValues((Procedure)frame71.lambda$Fn161, (Procedure)frame71.lambda$Fn162));
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1045, 63);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1045, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringParseFinalStart$PlEnd(Object paramObject1, Object paramObject2, Object paramObject3) {
    frame0 frame0 = new frame0();
    frame0.proc = paramObject1;
    frame0.s = paramObject2;
    frame0.args = paramObject3;
    return call_with_values.callWithValues((Procedure)frame0.lambda$Fn3, (Procedure)frame0.lambda$Fn4);
  }
  
  public static Object stringParseStart$PlEnd(Object paramObject1, Object paramObject2, Object paramObject3) {
    frame frame = new frame();
    frame.proc = paramObject1;
    frame.s = paramObject2;
    if (!strings.isString(frame.s))
      misc.error$V("Non-string value", new Object[] { frame.proc, frame.s }); 
    paramObject1 = frame.s;
    try {
      paramObject2 = paramObject1;
      frame.slen = strings.stringLength((CharSequence)paramObject2);
      if (lists.isPair(paramObject3)) {
        paramObject1 = lists.car.apply1(paramObject3);
        frame.args = lists.cdr.apply1(paramObject3);
        frame.start = paramObject1;
        boolean bool = numbers.isInteger(frame.start);
        if (bool) {
          bool = numbers.isExact(frame.start);
          return (bool ? (Scheme.numGEq.apply2(frame.start, Lit0) != Boolean.FALSE) : bool) ? call_with_values.callWithValues((Procedure)frame.lambda$Fn1, (Procedure)frame.lambda$Fn2) : misc.error$V("Illegal substring START spec", new Object[] { frame.proc, frame.start, frame.s });
        } 
        return bool ? call_with_values.callWithValues((Procedure)frame.lambda$Fn1, (Procedure)frame.lambda$Fn2) : misc.error$V("Illegal substring START spec", new Object[] { frame.proc, frame.start, frame.s });
      } 
      return misc.values(new Object[] { LList.Empty, Lit0, Integer.valueOf(frame.slen) });
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "string-length", 1, paramObject1);
    } 
  }
  
  public static Object stringPrefixLength$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame11 frame11 = new frame11();
    frame11.s1 = paramObject1;
    frame11.s2 = paramObject2;
    frame11.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame11.lambda$Fn28, (Procedure)frame11.lambda$Fn29);
  }
  
  public static Object stringPrefixLengthCi$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame15 frame15 = new frame15();
    frame15.s1 = paramObject1;
    frame15.s2 = paramObject2;
    frame15.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame15.lambda$Fn36, (Procedure)frame15.lambda$Fn37);
  }
  
  public static Object stringReplace$V(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object[] paramArrayOfObject) {
    frame92 frame92 = new frame92();
    frame92.s1 = paramObject1;
    frame92.s2 = paramObject2;
    frame92.start1 = paramObject3;
    frame92.end1 = paramObject4;
    frame92.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    checkSubstringSpec(string$Mnreplace, frame92.s1, frame92.start1, frame92.end1);
    return call_with_values.callWithValues((Procedure)frame92.lambda$Fn206, (Procedure)frame92.lambda$Fn207);
  }
  
  public static Object stringReverse$Ex$V(Object paramObject, Object[] paramArrayOfObject) {
    frame90 frame90 = new frame90();
    frame90.s = paramObject;
    frame90.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame90.lambda$Fn202, (Procedure)frame90.lambda$Fn203);
  }
  
  public static Object stringReverse$V(Object paramObject, Object[] paramArrayOfObject) {
    frame89 frame89 = new frame89();
    frame89.s = paramObject;
    frame89.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame89.lambda$Fn200, (Procedure)frame89.lambda$Fn201);
  }
  
  public static Object stringSkip$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame79 frame79 = new frame79();
    frame79.str = paramObject1;
    frame79.criterion = paramObject2;
    frame79.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame79.lambda$Fn181, (Procedure)frame79.lambda$Fn182);
  }
  
  public static Object stringSkipRight$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame80 frame80 = new frame80();
    frame80.str = paramObject1;
    frame80.criterion = paramObject2;
    frame80.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame80.lambda$Fn183, (Procedure)frame80.lambda$Fn184);
  }
  
  public static Object stringSuffixLength$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame13 frame13 = new frame13();
    frame13.s1 = paramObject1;
    frame13.s2 = paramObject2;
    frame13.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame13.lambda$Fn32, (Procedure)frame13.lambda$Fn33);
  }
  
  public static Object stringSuffixLengthCi$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame17 frame17 = new frame17();
    frame17.s1 = paramObject1;
    frame17.s2 = paramObject2;
    frame17.maybe$Mnstarts$Plends = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame17.lambda$Fn40, (Procedure)frame17.lambda$Fn41);
  }
  
  public static CharSequence stringTabulate(Object paramObject, int paramInt) {
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    Location location = loc$check$Mnarg;
    try {
      Object object = location.get();
      applyToArgs.apply4(object, misc.procedure$Qu, paramObject, string$Mntabulate);
      applyToArgs = Scheme.applyToArgs;
      object = loc$check$Mnarg;
      try {
        object = object.get();
        applyToArgs.apply4(object, lambda$Fn27, Integer.valueOf(paramInt), string$Mntabulate);
        CharSequence charSequence = strings.makeString(paramInt);
        while (--paramInt >= 0) {
          try {
            CharSeq charSeq = (CharSeq)charSequence;
            object = Scheme.applyToArgs.apply2(paramObject, Integer.valueOf(paramInt));
            try {
              char c = ((Char)object).charValue();
              strings.stringSet$Ex(charSeq, paramInt, c);
              paramInt--;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-set!", 3, object);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-set!", 1, charSequence);
          } 
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 535, 3);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 534, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringTake(Object paramObject1, Object paramObject2) {
    frame64 frame64 = new frame64();
    frame64.s = paramObject1;
    frame64.n = paramObject2;
    paramObject1 = Scheme.applyToArgs;
    paramObject2 = loc$check$Mnarg;
    try {
      paramObject2 = paramObject2.get();
      paramObject1.apply4(paramObject2, strings.string$Qu, frame64.s, string$Mntake);
      paramObject1 = Scheme.applyToArgs;
      paramObject2 = loc$check$Mnarg;
      try {
        paramObject2 = paramObject2.get();
        paramObject1.apply4(paramObject2, frame64.lambda$Fn151, frame64.n, string$Mntake);
        paramObject1 = frame64.s;
        try {
          paramObject2 = paramObject1;
          paramObject1 = frame64.n;
          try {
            int i = ((Number)paramObject1).intValue();
            return $PcSubstring$SlShared((CharSequence)paramObject2, 0, i);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%substring/shared", 2, paramObject1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%substring/shared", 0, paramObject1);
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 996, 3);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 995, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringTakeRight(Object paramObject1, Object paramObject2) {
    frame65 frame65 = new frame65();
    frame65.n = paramObject2;
    paramObject2 = Scheme.applyToArgs;
    Location location = loc$check$Mnarg;
    try {
      Object object = location.get();
      paramObject2.apply4(object, strings.string$Qu, paramObject1, string$Mntake$Mnright);
      try {
        paramObject2 = paramObject1;
        frame65.len = strings.stringLength((CharSequence)paramObject2);
        paramObject2 = Scheme.applyToArgs;
        object = loc$check$Mnarg;
        try {
          object = object.get();
          paramObject2.apply4(object, frame65.lambda$Fn152, frame65.n, string$Mntake$Mnright);
          try {
            paramObject2 = paramObject1;
            paramObject1 = AddOp.$Mn.apply2(Integer.valueOf(frame65.len), frame65.n);
            try {
              int i = ((Number)paramObject1).intValue();
              return $PcSubstring$SlShared((CharSequence)paramObject2, i, frame65.len);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%substring/shared", 1, paramObject1);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%substring/shared", 0, paramObject1);
          } 
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1004, 5);
          throw unboundLocationException;
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-length", 1, unboundLocationException);
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1002, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringTitlecase$Ex$V(Object paramObject, Object[] paramArrayOfObject) {
    frame62 frame62 = new frame62();
    frame62.s = paramObject;
    frame62.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame62.lambda$Fn147, (Procedure)frame62.lambda$Fn148);
  }
  
  public static Object stringTitlecase$V(Object paramObject, Object[] paramArrayOfObject) {
    frame63 frame63 = new frame63();
    frame63.s = paramObject;
    frame63.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame63.lambda$Fn149, (Procedure)frame63.lambda$Fn150);
  }
  
  public static Object stringTokenize$V(Object paramObject, Object[] paramArrayOfObject) {
    frame93 frame93 = new frame93();
    frame93.s = paramObject;
    paramObject = LList.makeList(paramArrayOfObject, 0);
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    Location location = loc$let$Mnoptionals$St;
    try {
      Object object = location.get();
      ApplyToArgs applyToArgs1 = Scheme.applyToArgs;
      ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
      Location location1 = loc$token$Mnchars;
      try {
        Object object1 = location1.get();
        GetNamedPart getNamedPart = GetNamedPart.getNamedPart;
        Location location2 = loc$char$Mnset;
        try {
          Object object3 = location2.get();
          Object object2 = getNamedPart.apply2(object3, Lit14);
          object3 = Scheme.applyToArgs;
          Location location3 = loc$char$Mnset$Qu;
          try {
            Object object4 = location3.get();
            Location location4 = loc$token$Mnchars;
            try {
              Object object6 = location4.get();
              Object object5 = applyToArgs2.apply3(object1, object2, object3.apply2(object4, object6));
              object1 = loc$rest;
              try {
                object1 = object1.get();
                return applyToArgs.apply4(object, paramObject, applyToArgs1.apply2(object5, object1), call_with_values.callWithValues((Procedure)frame93.lambda$Fn208, (Procedure)frame93.lambda$Fn209));
              } catch (UnboundLocationException unboundLocationException) {
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 75);
                throw unboundLocationException;
              } 
            } catch (UnboundLocationException unboundLocationException) {
              unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 61);
              throw unboundLocationException;
            } 
          } catch (UnboundLocationException unboundLocationException) {
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 50);
            throw unboundLocationException;
          } 
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 33);
          throw unboundLocationException;
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 20);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1694, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringTrim$V(Object paramObject, Object[] paramArrayOfObject) {
    frame68 frame68 = new frame68();
    frame68.s = paramObject;
    paramObject = LList.makeList(paramArrayOfObject, 0);
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    Location location = loc$let$Mnoptionals$St;
    try {
      Object object = location.get();
      ApplyToArgs applyToArgs1 = Scheme.applyToArgs;
      ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
      Location location1 = loc$criterion;
      try {
        Object object1 = location1.get();
        GetNamedPart getNamedPart = GetNamedPart.getNamedPart;
        Location location2 = loc$char$Mnset;
        try {
          Object object3 = location2.get();
          Object object2 = applyToArgs2.apply2(object1, getNamedPart.apply2(object3, Lit11));
          object1 = loc$rest;
          try {
            object1 = object1.get();
            return applyToArgs.apply4(object, paramObject, applyToArgs1.apply2(object2, object1), call_with_values.callWithValues((Procedure)frame68.lambda$Fn155, (Procedure)frame68.lambda$Fn156));
          } catch (UnboundLocationException unboundLocationException) {
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1022, 72);
            throw unboundLocationException;
          } 
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1022, 51);
          throw unboundLocationException;
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1022, 40);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1022, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringTrimBoth$V(Object paramObject, Object[] paramArrayOfObject) {
    frame70 frame70 = new frame70();
    frame70.s = paramObject;
    paramObject = LList.makeList(paramArrayOfObject, 0);
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    Location location = loc$let$Mnoptionals$St;
    try {
      Object object = location.get();
      ApplyToArgs applyToArgs1 = Scheme.applyToArgs;
      ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
      Location location1 = loc$criterion;
      try {
        Object object1 = location1.get();
        GetNamedPart getNamedPart = GetNamedPart.getNamedPart;
        Location location2 = loc$char$Mnset;
        try {
          Object object3 = location2.get();
          Object object2 = applyToArgs2.apply2(object1, getNamedPart.apply2(object3, Lit11));
          object1 = loc$rest;
          try {
            object1 = object1.get();
            return applyToArgs.apply4(object, paramObject, applyToArgs1.apply2(object2, object1), call_with_values.callWithValues((Procedure)frame70.lambda$Fn159, (Procedure)frame70.lambda$Fn160));
          } catch (UnboundLocationException unboundLocationException) {
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1036, 72);
            throw unboundLocationException;
          } 
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1036, 51);
          throw unboundLocationException;
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1036, 40);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1036, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringTrimRight$V(Object paramObject, Object[] paramArrayOfObject) {
    frame69 frame69 = new frame69();
    frame69.s = paramObject;
    paramObject = LList.makeList(paramArrayOfObject, 0);
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    Location location = loc$let$Mnoptionals$St;
    try {
      Object object = location.get();
      ApplyToArgs applyToArgs1 = Scheme.applyToArgs;
      ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
      Location location1 = loc$criterion;
      try {
        Object object1 = location1.get();
        GetNamedPart getNamedPart = GetNamedPart.getNamedPart;
        Location location2 = loc$char$Mnset;
        try {
          Object object3 = location2.get();
          Object object2 = applyToArgs2.apply2(object1, getNamedPart.apply2(object3, Lit11));
          object1 = loc$rest;
          try {
            object1 = object1.get();
            return applyToArgs.apply4(object, paramObject, applyToArgs1.apply2(object2, object1), call_with_values.callWithValues((Procedure)frame69.lambda$Fn157, (Procedure)frame69.lambda$Fn158));
          } catch (UnboundLocationException unboundLocationException) {
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1029, 72);
            throw unboundLocationException;
          } 
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1029, 51);
          throw unboundLocationException;
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1029, 40);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1029, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringUnfold$V(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    Location location = loc$check$Mnarg;
    try {
      Object object = location.get();
      applyToArgs.apply4(object, misc.procedure$Qu, paramObject1, string$Mnunfold);
      applyToArgs = Scheme.applyToArgs;
      object = loc$check$Mnarg;
      try {
        object = object.get();
        applyToArgs.apply4(object, misc.procedure$Qu, paramObject2, string$Mnunfold);
        applyToArgs = Scheme.applyToArgs;
        object = loc$check$Mnarg;
        try {
          object = object.get();
          applyToArgs.apply4(object, misc.procedure$Qu, paramObject3, string$Mnunfold);
          ApplyToArgs applyToArgs1 = Scheme.applyToArgs;
          Location location1 = loc$let$Mnoptionals$St;
          try {
            Object object1 = location1.get();
            object = Scheme.applyToArgs;
            ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
            location1 = loc$base;
            try {
              Object object2 = location1.get();
              location1 = loc$base;
              try {
                Object object3 = location1.get();
                if (strings.isString(object3)) {
                  object3 = Boolean.TRUE;
                } else {
                  object3 = Boolean.FALSE;
                } 
                Object object4 = applyToArgs2.apply3(object2, "", object3);
                object2 = Scheme.applyToArgs;
                object3 = loc$make$Mnfinal;
                try {
                  Object object5 = object3.get();
                  ModuleMethod moduleMethod = lambda$Fn17;
                  object3 = loc$make$Mnfinal;
                  try {
                    object3 = object3.get();
                    if (misc.isProcedure(object3)) {
                      object3 = Boolean.TRUE;
                    } else {
                      object3 = Boolean.FALSE;
                    } 
                    object5 = object.apply2(object4, object2.apply3(object5, moduleMethod, object3));
                    object4 = LList.Empty;
                    int i = 0;
                    object = strings.makeString(40);
                    int j = 40;
                    int k = 0;
                    object3 = paramObject4;
                    paramObject4 = object4;
                    while (true) {
                      object2 = Integer.valueOf(k);
                      object4 = object3;
                      object3 = object2;
                      while (true) {
                        if (Scheme.applyToArgs.apply2(paramObject1, object4) == Boolean.FALSE) {
                          object2 = Scheme.applyToArgs.apply2(paramObject2, object4);
                          object4 = Scheme.applyToArgs.apply2(paramObject3, object4);
                          if (Scheme.numLss.apply2(object3, Integer.valueOf(j)) != Boolean.FALSE)
                            try {
                              CharSeq charSeq = (CharSeq)object;
                              try {
                                k = ((Number)object3).intValue();
                                try {
                                  char c = ((Char)object2).charValue();
                                  strings.stringSet$Ex(charSeq, k, c);
                                  object3 = AddOp.$Pl.apply2(object3, Lit1);
                                } catch (ClassCastException classCastException) {
                                  throw new WrongType(classCastException, "string-set!", 3, object2);
                                } 
                              } catch (ClassCastException classCastException) {
                                throw new WrongType(classCastException, "string-set!", 2, object3);
                              } 
                            } catch (ClassCastException classCastException) {
                              throw new WrongType(classCastException, "string-set!", 1, object);
                            }  
                          object3 = numbers.min(new Object[] { Lit2, Integer.valueOf(j + i) });
                          try {
                            k = ((Number)object3).intValue();
                            object3 = strings.makeString(k);
                            try {
                              CharSeq charSeq = (CharSeq)object3;
                              try {
                                char c = ((Char)object2).charValue();
                                strings.stringSet$Ex(charSeq, 0, c);
                                paramObject4 = lists.cons(object, paramObject4);
                                i += j;
                                boolean bool = true;
                                j = k;
                                object = object3;
                                k = bool;
                                object3 = object4;
                              } catch (ClassCastException classCastException) {
                                throw new WrongType(classCastException, "string-set!", 3, object2);
                              } 
                            } catch (ClassCastException classCastException) {
                              throw new WrongType(classCastException, "string-set!", 1, object3);
                            } 
                          } catch (ClassCastException classCastException) {
                            throw new WrongType(classCastException, "chunk-len2", -2, object3);
                          } 
                        } 
                        paramObject1 = Scheme.applyToArgs;
                        paramObject2 = loc$make$Mnfinal;
                        try {
                          paramObject2 = paramObject2.get();
                          paramObject1 = paramObject1.apply2(paramObject2, object4);
                          try {
                            paramObject2 = paramObject1;
                            k = strings.stringLength((CharSequence)paramObject2);
                            paramObject2 = loc$base;
                            try {
                              paramObject2 = paramObject2.get();
                              try {
                                paramObject3 = paramObject2;
                                j = strings.stringLength((CharSequence)paramObject3);
                                paramObject2 = AddOp.$Pl.apply2(Integer.valueOf(j + i), object3);
                                try {
                                  i = ((Number)paramObject2).intValue();
                                  paramObject2 = strings.makeString(i + k);
                                  try {
                                    paramObject3 = paramObject1;
                                    $PcStringCopy$Ex((CharSequence)paramObject2, i, (CharSequence)paramObject3, 0, k);
                                    paramObject1 = AddOp.$Mn.apply2(Integer.valueOf(i), object3);
                                    try {
                                      i = ((Number)paramObject1).intValue();
                                      try {
                                        paramObject1 = object;
                                        try {
                                          k = ((Number)object3).intValue();
                                          $PcStringCopy$Ex((CharSequence)paramObject2, i, (CharSequence)paramObject1, 0, k);
                                          paramObject1 = Integer.valueOf(i);
                                          while (true) {
                                            if (lists.isPair(paramObject4)) {
                                              paramObject3 = lists.car.apply1(paramObject4);
                                              paramObject4 = lists.cdr.apply1(paramObject4);
                                              try {
                                                object3 = paramObject3;
                                                i = strings.stringLength((CharSequence)object3);
                                                paramObject1 = AddOp.$Mn.apply2(paramObject1, Integer.valueOf(i));
                                                try {
                                                  k = ((Number)paramObject1).intValue();
                                                  try {
                                                    object3 = paramObject3;
                                                    $PcStringCopy$Ex((CharSequence)paramObject2, k, (CharSequence)object3, 0, i);
                                                  } catch (ClassCastException null) {
                                                    throw new WrongType(classCastException1, "%string-copy!", 2, paramObject3);
                                                  } 
                                                } catch (ClassCastException null) {
                                                  throw new WrongType(classCastException, "%string-copy!", 1, classCastException1);
                                                } 
                                              } catch (ClassCastException null) {
                                                throw new WrongType(classCastException1, "string-length", 1, paramObject3);
                                              } 
                                            } 
                                            paramObject1 = loc$base;
                                            try {
                                              paramObject1 = paramObject1.get();
                                              try {
                                                paramObject3 = paramObject1;
                                                $PcStringCopy$Ex((CharSequence)classCastException, 0, (CharSequence)paramObject3, 0, j);
                                                return applyToArgs1.apply4(object1, lList, object5, classCastException);
                                              } catch (ClassCastException classCastException2) {
                                                throw new WrongType(classCastException2, "%string-copy!", 2, paramObject1);
                                              } 
                                            } catch (UnboundLocationException null) {
                                              unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 416, 29);
                                              throw unboundLocationException;
                                            } 
                                          } 
                                        } catch (ClassCastException null) {
                                          throw new WrongType(classCastException1, "%string-copy!", 4, object3);
                                        } 
                                      } catch (ClassCastException classCastException1) {
                                        throw new WrongType(classCastException1, "%string-copy!", 2, object);
                                      } 
                                    } catch (ClassCastException null) {
                                      throw new WrongType(classCastException, "j", -2, classCastException1);
                                    } 
                                  } catch (ClassCastException classCastException) {
                                    throw new WrongType(classCastException, "%string-copy!", 2, classCastException1);
                                  } 
                                } catch (ClassCastException classCastException1) {
                                  throw new WrongType(classCastException1, "j", -2, classCastException);
                                } 
                              } catch (ClassCastException classCastException1) {
                                throw new WrongType(classCastException1, "string-length", 1, classCastException);
                              } 
                            } catch (UnboundLocationException unboundLocationException) {
                              unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 402, 38);
                              throw unboundLocationException;
                            } 
                          } catch (ClassCastException classCastException) {
                            throw new WrongType(classCastException, "string-length", 1, unboundLocationException);
                          } 
                        } catch (UnboundLocationException unboundLocationException) {
                          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 400, 20);
                          throw unboundLocationException;
                        } 
                      } 
                      break;
                    } 
                  } catch (UnboundLocationException unboundLocationException) {
                    unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 377, 46);
                    throw unboundLocationException;
                  } 
                } catch (UnboundLocationException unboundLocationException) {
                  unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 377, 6);
                  throw unboundLocationException;
                } 
              } catch (UnboundLocationException unboundLocationException) {
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 376, 57);
                throw unboundLocationException;
              } 
            } catch (UnboundLocationException unboundLocationException) {
              unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 376, 20);
              throw unboundLocationException;
            } 
          } catch (UnboundLocationException unboundLocationException) {
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 375, 3);
            throw unboundLocationException;
          } 
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 374, 3);
          throw unboundLocationException;
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 373, 3);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 372, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringUnfoldRight$V(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    Location location = loc$let$Mnoptionals$St;
    try {
      Object object = location.get();
      ApplyToArgs applyToArgs1 = Scheme.applyToArgs;
      ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
      location = loc$base;
      try {
        Object object1 = location.get();
        location = loc$base;
        try {
          Object object2 = location.get();
          if (strings.isString(object2)) {
            object2 = Boolean.TRUE;
          } else {
            object2 = Boolean.FALSE;
          } 
          Object object3 = applyToArgs2.apply3(object1, "", object2);
          object1 = Scheme.applyToArgs;
          object2 = loc$make$Mnfinal;
          try {
            Object object4 = object2.get();
            ModuleMethod moduleMethod = lambda$Fn18;
            object2 = loc$make$Mnfinal;
            try {
              object2 = object2.get();
              if (misc.isProcedure(object2)) {
                object2 = Boolean.TRUE;
              } else {
                object2 = Boolean.FALSE;
              } 
              Object object5 = applyToArgs1.apply2(object3, object1.apply3(object4, moduleMethod, object2));
              LList lList1 = LList.Empty;
              object1 = Lit0;
              CharSequence charSequence = strings.makeString(40);
              object3 = Lit3;
              object2 = Lit3;
              object4 = paramObject4;
              paramObject4 = lList1;
              while (true) {
                if (Scheme.applyToArgs.apply2(paramObject1, object4) == Boolean.FALSE) {
                  Object object7 = Scheme.applyToArgs.apply2(paramObject2, object4);
                  object4 = Scheme.applyToArgs.apply2(paramObject3, object4);
                  if (Scheme.numGrt.apply2(object2, Lit0) != Boolean.FALSE) {
                    object2 = AddOp.$Mn.apply2(object2, Lit1);
                    try {
                      CharSeq charSeq = (CharSeq)charSequence;
                      try {
                        int i = ((Number)object2).intValue();
                        try {
                          char c = ((Char)object7).charValue();
                          strings.stringSet$Ex(charSeq, i, c);
                        } catch (ClassCastException classCastException) {
                          throw new WrongType(classCastException, "string-set!", 3, object7);
                        } 
                      } catch (ClassCastException classCastException) {
                        throw new WrongType(classCastException, "string-set!", 2, object2);
                      } 
                    } catch (ClassCastException classCastException) {
                      throw new WrongType(classCastException, "string-set!", 1, charSequence);
                    } 
                  } 
                  object2 = AddOp.$Pl.apply2(object3, object1);
                  Object object6 = numbers.min(new Object[] { Lit4, object2 });
                  try {
                    int i = ((Number)object6).intValue();
                    CharSequence charSequence1 = strings.makeString(i);
                    object2 = AddOp.$Mn.apply2(object6, Lit1);
                    try {
                      CharSeq charSeq = (CharSeq)charSequence1;
                      try {
                        i = ((Number)object2).intValue();
                        try {
                          char c = ((Char)object7).charValue();
                          strings.stringSet$Ex(charSeq, i, c);
                          paramObject4 = lists.cons(charSequence, paramObject4);
                          object1 = AddOp.$Pl.apply2(object1, object3);
                          object3 = object6;
                          charSequence = charSequence1;
                        } catch (ClassCastException classCastException) {
                          throw new WrongType(classCastException, "string-set!", 3, object7);
                        } 
                      } catch (ClassCastException classCastException) {
                        throw new WrongType(classCastException, "string-set!", 2, object2);
                      } 
                    } catch (ClassCastException classCastException) {
                      throw new WrongType(classCastException, "string-set!", 1, charSequence1);
                    } 
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "make-string", 1, object6);
                  } 
                } 
                paramObject1 = Scheme.applyToArgs;
                paramObject2 = loc$make$Mnfinal;
                try {
                  paramObject2 = paramObject2.get();
                  paramObject1 = paramObject1.apply2(paramObject2, object4);
                  try {
                    paramObject2 = paramObject1;
                    int i = strings.stringLength((CharSequence)paramObject2);
                    paramObject2 = loc$base;
                    try {
                      paramObject2 = paramObject2.get();
                      try {
                        paramObject3 = paramObject2;
                        int j = strings.stringLength((CharSequence)paramObject3);
                        paramObject3 = AddOp.$Mn.apply2(object3, object2);
                        paramObject2 = AddOp.$Pl.apply2(AddOp.$Pl.apply2(Integer.valueOf(j), object1), paramObject3);
                        paramObject2 = AddOp.$Pl.apply2(paramObject2, Integer.valueOf(i));
                        try {
                          int k = ((Number)paramObject2).intValue();
                          paramObject2 = strings.makeString(k);
                          try {
                            object1 = paramObject1;
                            $PcStringCopy$Ex((CharSequence)paramObject2, 0, (CharSequence)object1, 0, i);
                            try {
                              paramObject1 = charSequence;
                              try {
                                k = ((Number)object2).intValue();
                                try {
                                  int m = ((Number)object3).intValue();
                                  $PcStringCopy$Ex((CharSequence)paramObject2, i, (CharSequence)paramObject1, k, m);
                                  paramObject1 = AddOp.$Pl.apply2(Integer.valueOf(i), paramObject3);
                                  while (true) {
                                    if (lists.isPair(paramObject4)) {
                                      paramObject3 = lists.car.apply1(paramObject4);
                                      paramObject4 = lists.cdr.apply1(paramObject4);
                                      try {
                                        object2 = paramObject3;
                                        i = strings.stringLength((CharSequence)object2);
                                        try {
                                          k = ((Number)paramObject1).intValue();
                                          try {
                                            object2 = paramObject3;
                                            $PcStringCopy$Ex((CharSequence)paramObject2, k, (CharSequence)object2, 0, i);
                                            paramObject1 = AddOp.$Pl.apply2(paramObject1, Integer.valueOf(i));
                                          } catch (ClassCastException null) {
                                            throw new WrongType(classCastException1, "%string-copy!", 2, paramObject3);
                                          } 
                                        } catch (ClassCastException null) {
                                          throw new WrongType(classCastException, "%string-copy!", 1, classCastException1);
                                        } 
                                      } catch (ClassCastException classCastException1) {
                                        throw new WrongType(classCastException1, "string-length", 1, paramObject3);
                                      } 
                                    } 
                                    try {
                                      i = ((Number)classCastException1).intValue();
                                      Location location1 = loc$base;
                                      try {
                                        object6 = location1.get();
                                        try {
                                          paramObject3 = object6;
                                          $PcStringCopy$Ex((CharSequence)classCastException, i, (CharSequence)paramObject3, 0, j);
                                          return applyToArgs.apply4(object, lList, object5, classCastException);
                                        } catch (ClassCastException classCastException2) {
                                          throw new WrongType(classCastException2, "%string-copy!", 2, object6);
                                        } 
                                      } catch (UnboundLocationException null) {
                                        object6.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 463, 30);
                                        throw object6;
                                      } 
                                    } catch (ClassCastException classCastException2) {
                                      throw new WrongType(classCastException2, "%string-copy!", 1, object6);
                                    } 
                                  } 
                                } catch (ClassCastException null) {
                                  throw new WrongType(object6, "%string-copy!", 4, object3);
                                } 
                              } catch (ClassCastException null) {
                                throw new WrongType(object6, "%string-copy!", 3, object2);
                              } 
                            } catch (ClassCastException null) {
                              throw new WrongType(object6, "%string-copy!", 2, charSequence);
                            } 
                          } catch (ClassCastException classCastException) {
                            throw new WrongType(classCastException, "%string-copy!", 2, object6);
                          } 
                        } catch (ClassCastException null) {
                          throw new WrongType(object6, "make-string", 1, classCastException);
                        } 
                      } catch (ClassCastException null) {
                        throw new WrongType(object6, "string-length", 1, classCastException);
                      } 
                    } catch (UnboundLocationException object6) {
                      object6.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 449, 31);
                      throw object6;
                    } 
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "string-length", 1, object6);
                  } 
                } catch (UnboundLocationException unboundLocationException) {
                  unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 447, 20);
                  throw unboundLocationException;
                } 
              } 
            } catch (UnboundLocationException unboundLocationException) {
              unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 422, 46);
              throw unboundLocationException;
            } 
          } catch (UnboundLocationException unboundLocationException) {
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 422, 6);
            throw unboundLocationException;
          } 
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 421, 57);
          throw unboundLocationException;
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 421, 20);
        throw unboundLocationException;
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 420, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object stringUpcase$Ex$V(Object paramObject, Object[] paramArrayOfObject) {
    frame59 frame59 = new frame59();
    frame59.s = paramObject;
    frame59.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame59.lambda$Fn141, (Procedure)frame59.lambda$Fn142);
  }
  
  public static Object stringUpcase$V(Object paramObject, Object[] paramArrayOfObject) {
    frame58 frame58 = new frame58();
    frame58.s = paramObject;
    frame58.maybe$Mnstart$Plend = LList.makeList(paramArrayOfObject, 0);
    return call_with_values.callWithValues((Procedure)frame58.lambda$Fn139, (Procedure)frame58.lambda$Fn140);
  }
  
  public static Object stringXcopy$Ex$V(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object[] paramArrayOfObject) {
    frame95 frame95 = new frame95();
    frame95.target = paramObject1;
    frame95.tstart = paramObject2;
    frame95.s = paramObject3;
    frame95.sfrom = paramObject4;
    frame95.maybe$Mnsto$Plstart$Plend = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.applyToArgs;
    paramObject2 = loc$check$Mnarg;
    try {
      paramObject2 = paramObject2.get();
      paramObject1.apply4(paramObject2, lambda$Fn216, frame95.sfrom, string$Mnxcopy$Ex);
      return call_with_values.callWithValues((Procedure)frame95.lambda$Fn217, (Procedure)frame95.lambda$Fn221);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1779, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object substring$SlShared$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame1 frame1 = new frame1();
    frame1.start = paramObject2;
    paramObject2 = LList.makeList(paramArrayOfObject, 0);
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    Location location = loc$check$Mnarg;
    try {
      Object object = location.get();
      applyToArgs.apply4(object, strings.string$Qu, paramObject1, substring$Slshared);
      try {
        CharSequence charSequence = (CharSequence)paramObject1;
        frame1.slen = strings.stringLength(charSequence);
        ApplyToArgs applyToArgs1 = Scheme.applyToArgs;
        object = loc$check$Mnarg;
        try {
          object = object.get();
          applyToArgs1.apply4(object, lambda$Fn5, frame1.start, substring$Slshared);
          try {
            CharSequence charSequence1 = (CharSequence)paramObject1;
            paramObject1 = frame1.start;
            try {
              int i = ((Number)paramObject1).intValue();
              paramObject1 = Scheme.applyToArgs;
              object = loc$$Cloptional;
              try {
                object = object.get();
                paramObject1 = paramObject1.apply4(object, paramObject2, Integer.valueOf(frame1.slen), frame1.lambda$Fn6);
                try {
                  int j = ((Number)paramObject1).intValue();
                  return $PcSubstring$SlShared(charSequence1, i, j);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "%substring/shared", 2, paramObject1);
                } 
              } catch (UnboundLocationException null) {
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 226, 10);
                throw unboundLocationException;
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%substring/shared", 1, unboundLocationException);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%substring/shared", 0, unboundLocationException);
          } 
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 223, 5);
          throw unboundLocationException;
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-length", 1, unboundLocationException);
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 221, 3);
      throw unboundLocationException;
    } 
  }
  
  public static Object xsubstring$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame94 frame94 = new frame94();
    frame94.s = paramObject1;
    frame94.from = paramObject2;
    frame94.maybe$Mnto$Plstart$Plend = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.applyToArgs;
    paramObject2 = loc$check$Mnarg;
    try {
      paramObject2 = paramObject2.get();
      paramObject1.apply4(paramObject2, lambda$Fn210, frame94.from, xsubstring);
      return call_with_values.callWithValues((Procedure)frame94.lambda$Fn211, (Procedure)frame94.lambda$Fn215);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1738, 3);
      throw unboundLocationException;
    } 
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 199:
        return frame1.lambda5(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 209:
        return lambda17(paramObject);
      case 211:
        return lambda18(paramObject);
      case 217:
        return lambda27(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 239:
        return frame31.frame32.lambda72(paramObject);
      case 240:
        return frame31.frame32.lambda73(paramObject);
      case 242:
        return frame33.frame34.lambda78(paramObject);
      case 244:
        return frame35.frame36.lambda83(paramObject);
      case 245:
        return frame35.frame36.lambda84(paramObject);
      case 247:
        return frame37.frame38.lambda89(paramObject);
      case 248:
        return frame37.frame38.lambda90(paramObject);
      case 250:
        return frame39.frame40.lambda95(paramObject);
      case 252:
        return frame41.frame42.lambda100(paramObject);
      case 254:
        return frame43.frame44.lambda105(paramObject);
      case 255:
        return frame43.frame44.lambda106(paramObject);
      case 257:
        return frame45.frame46.lambda111(paramObject);
      case 259:
        return frame47.frame48.lambda116(paramObject);
      case 260:
        return frame47.frame48.lambda117(paramObject);
      case 262:
        return frame49.frame50.lambda122(paramObject);
      case 263:
        return frame49.frame50.lambda123(paramObject);
      case 265:
        return frame51.frame52.lambda128(paramObject);
      case 267:
        return frame53.frame54.lambda133(paramObject);
      case 271:
        return Integer.valueOf(frame57.lambda138(paramObject));
      case 287:
        return frame71.lambda163(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 289:
        return frame72.lambda166(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 309:
        return isStringNull(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 312:
        return reverseList$To$String(paramObject);
      case 315:
        return stringConcatenate$SlShared(paramObject);
      case 316:
        return stringConcatenate(paramObject);
      case 322:
        return frame94.lambda210(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 324:
        return frame95.lambda216(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 325:
        break;
    } 
    return frame95.lambda220(paramObject) ? Boolean.TRUE : Boolean.FALSE;
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 218:
        try {
          int i = ((Number)paramObject2).intValue();
          return stringTabulate(paramObject1, i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-tabulate", 2, paramObject2);
        } 
      case 280:
        return stringTake(paramObject1, paramObject2);
      case 281:
        return stringTakeRight(paramObject1, paramObject2);
      case 282:
        try {
          CharSequence charSequence = (CharSequence)paramObject1;
          return stringDrop(charSequence, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-drop", 1, paramObject1);
        } 
      case 283:
        break;
    } 
    try {
      CharSequence charSequence = (CharSequence)paramObject1;
      return stringDropRight(charSequence, paramObject2);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "string-drop-right", 1, paramObject1);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 194:
        return stringParseStart$PlEnd(paramObject1, paramObject2, paramObject3);
      case 196:
        return stringParseFinalStart$PlEnd(paramObject1, paramObject2, paramObject3);
      case 197:
        return isSubstringSpecOk(paramObject1, paramObject2, paramObject3) ? Boolean.TRUE : Boolean.FALSE;
      case 201:
        try {
          CharSequence charSequence = (CharSequence)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              int j = ((Number)paramObject3).intValue();
              return $PcSubstring$SlShared(charSequence, i, j);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%substring/shared", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%substring/shared", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%substring/shared", 1, paramObject1);
        } 
      case 277:
        return $PcStringTitlecase$Ex(paramObject1, paramObject2, paramObject3);
      case 299:
        break;
    } 
    try {
      int i = ((Number)paramObject2).intValue();
      try {
        CharSequence charSequence = (CharSequence)paramObject3;
        return stringCopy$Ex(paramObject1, i, charSequence);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-copy!", 3, paramObject3);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "string-copy!", 2, paramObject2);
    } 
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
      case 195:
        try {
          CharSequence charSequence = (CharSequence)paramObject2;
          try {
            int i = ((Number)paramObject3).intValue();
            try {
              int j = ((Number)paramObject4).intValue();
              return $PcCheckBounds(paramObject1, charSequence, i, j);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%check-bounds", 4, paramObject4);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%check-bounds", 3, paramObject3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%check-bounds", 2, paramObject2);
        } 
      case 198:
        return checkSubstringSpec(paramObject1, paramObject2, paramObject3, paramObject4);
      case 204:
        return $PcStringMap(paramObject1, paramObject2, paramObject3, paramObject4);
      case 206:
        return $PcStringMap$Ex(paramObject1, paramObject2, paramObject3, paramObject4);
      case 299:
        try {
          int i = ((Number)paramObject2).intValue();
          try {
            CharSequence charSequence = (CharSequence)paramObject3;
            try {
              int j = ((Number)paramObject4).intValue();
              return stringCopy$Ex(paramObject1, i, charSequence, j);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-copy!", 4, paramObject4);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-copy!", 3, paramObject3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-copy!", 2, paramObject2);
        } 
      case 319:
        break;
    } 
    return $PcFinishStringConcatenateReverse(paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    Object object2;
    int j;
    Object[] arrayOfObject1;
    Object object4;
    Object[] arrayOfObject2;
    Object object5;
    Object[] arrayOfObject3;
    Object object6;
    Object[] arrayOfObject4;
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 200:
        object1 = paramArrayOfObject[0];
        object3 = paramArrayOfObject[1];
        i = paramArrayOfObject.length - 2;
        arrayOfObject1 = new Object[i];
        while (true) {
          if (--i < 0)
            return substring$SlShared$V(object1, object3, arrayOfObject1); 
          arrayOfObject1[i] = paramArrayOfObject[i + 2];
        } 
      case 202:
        object1 = paramArrayOfObject[0];
        i = paramArrayOfObject.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringCopy$V(object1, (Object[])object3); 
          object3[i] = paramArrayOfObject[i + 1];
        } 
      case 203:
        object1 = paramArrayOfObject[0];
        object3 = paramArrayOfObject[1];
        i = paramArrayOfObject.length - 2;
        arrayOfObject1 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringMap$V(object1, object3, arrayOfObject1); 
          arrayOfObject1[i] = paramArrayOfObject[i + 2];
        } 
      case 205:
        object1 = paramArrayOfObject[0];
        object3 = paramArrayOfObject[1];
        i = paramArrayOfObject.length - 2;
        arrayOfObject1 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringMap$Ex$V(object1, object3, arrayOfObject1); 
          arrayOfObject1[i] = paramArrayOfObject[i + 2];
        } 
      case 207:
        object1 = paramArrayOfObject[0];
        object3 = paramArrayOfObject[1];
        object4 = paramArrayOfObject[2];
        i = paramArrayOfObject.length - 3;
        arrayOfObject2 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringFold$V(object1, object3, object4, arrayOfObject2); 
          arrayOfObject2[i] = paramArrayOfObject[i + 3];
        } 
      case 208:
        object1 = paramArrayOfObject[0];
        object3 = paramArrayOfObject[1];
        object4 = paramArrayOfObject[2];
        i = paramArrayOfObject.length - 3;
        arrayOfObject2 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringFoldRight$V(object1, object3, object4, arrayOfObject2); 
          arrayOfObject2[i] = paramArrayOfObject[i + 3];
        } 
      case 210:
        object1 = paramArrayOfObject[0];
        object3 = paramArrayOfObject[1];
        object4 = paramArrayOfObject[2];
        object5 = paramArrayOfObject[3];
        i = paramArrayOfObject.length - 4;
        arrayOfObject3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringUnfold$V(object1, object3, object4, object5, arrayOfObject3); 
          arrayOfObject3[i] = paramArrayOfObject[i + 4];
        } 
      case 212:
        object1 = paramArrayOfObject[0];
        object3 = paramArrayOfObject[1];
        object4 = paramArrayOfObject[2];
        object5 = paramArrayOfObject[3];
        i = paramArrayOfObject.length - 4;
        arrayOfObject3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringUnfoldRight$V(object1, object3, object4, object5, arrayOfObject3); 
          arrayOfObject3[i] = paramArrayOfObject[i + 4];
        } 
      case 213:
        object1 = paramArrayOfObject[0];
        object3 = paramArrayOfObject[1];
        i = paramArrayOfObject.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringForEach$V(object1, object3, (Object[])object4); 
          object4[i] = paramArrayOfObject[i + 2];
        } 
      case 214:
        object1 = paramArrayOfObject[0];
        object3 = paramArrayOfObject[1];
        i = paramArrayOfObject.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringForEachIndex$V(object1, object3, (Object[])object4); 
          object4[i] = paramArrayOfObject[i + 2];
        } 
      case 215:
        object1 = paramArrayOfObject[0];
        object3 = paramArrayOfObject[1];
        i = paramArrayOfObject.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringEvery$V(object1, object3, (Object[])object4); 
          object4[i] = paramArrayOfObject[i + 2];
        } 
      case 216:
        object1 = paramArrayOfObject[0];
        object3 = paramArrayOfObject[1];
        i = paramArrayOfObject.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringAny$V(object1, object3, (Object[])object4); 
          object4[i] = paramArrayOfObject[i + 2];
        } 
      case 219:
        return $PcStringPrefixLength(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4], paramArrayOfObject[5]);
      case 220:
        return $PcStringSuffixLength(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4], paramArrayOfObject[5]);
      case 221:
        object1 = paramArrayOfObject[0];
        object3 = paramArrayOfObject[1];
        try {
          i = ((Number)object3).intValue();
          object3 = paramArrayOfObject[2];
          try {
            int k = ((Number)object3).intValue();
            object3 = paramArrayOfObject[3];
            object4 = paramArrayOfObject[4];
            try {
              int m = ((Number)object4).intValue();
              object2 = paramArrayOfObject[5];
              try {
                int n = ((Number)object2).intValue();
                return Integer.valueOf($PcStringPrefixLengthCi(object1, i, k, object3, m, n));
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "%string-prefix-length-ci", 6, object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%string-prefix-length-ci", 5, object4);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%string-prefix-length-ci", 3, object3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%string-prefix-length-ci", 2, object3);
        } 
      case 222:
        object1 = object2[0];
        object3 = object2[1];
        try {
          i = ((Number)object3).intValue();
          object3 = object2[2];
          try {
            int k = ((Number)object3).intValue();
            object3 = object2[3];
            object4 = object2[4];
            try {
              int m = ((Number)object4).intValue();
              object2 = object2[5];
              try {
                int n = ((Number)object2).intValue();
                return Integer.valueOf($PcStringSuffixLengthCi(object1, i, k, object3, m, n));
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "%string-suffix-length-ci", 6, object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%string-suffix-length-ci", 5, object4);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%string-suffix-length-ci", 3, object3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%string-suffix-length-ci", 2, object3);
        } 
      case 223:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringPrefixLength$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 224:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringSuffixLength$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 225:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringPrefixLengthCi$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 226:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringSuffixLengthCi$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 227:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return isStringPrefix$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 228:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return isStringSuffix$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 229:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return isStringPrefixCi$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 230:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return isStringSuffixCi$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 231:
        return $PcStringPrefix$Qu(object2[0], object2[1], object2[2], object2[3], object2[4], object2[5]);
      case 232:
        return $PcStringSuffix$Qu(object2[0], object2[1], object2[2], object2[3], object2[4], object2[5]);
      case 233:
        return $PcStringPrefixCi$Qu(object2[0], object2[1], object2[2], object2[3], object2[4], object2[5]);
      case 234:
        return $PcStringSuffixCi$Qu(object2[0], object2[1], object2[2], object2[3], object2[4], object2[5]);
      case 235:
        return $PcStringCompare(object2[0], object2[1], object2[2], object2[3], object2[4], object2[5], object2[6], object2[7], object2[8]);
      case 236:
        return $PcStringCompareCi(object2[0], object2[1], object2[2], object2[3], object2[4], object2[5], object2[6], object2[7], object2[8]);
      case 237:
        object1 = object2[0];
        object3 = object2[1];
        object4 = object2[2];
        object5 = object2[3];
        object6 = object2[4];
        i = object2.length - 5;
        arrayOfObject4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringCompare$V(object1, object3, object4, object5, object6, arrayOfObject4); 
          arrayOfObject4[i] = object2[i + 5];
        } 
      case 238:
        object1 = object2[0];
        object3 = object2[1];
        object4 = object2[2];
        object5 = object2[3];
        object6 = object2[4];
        i = object2.length - 5;
        arrayOfObject4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringCompareCi$V(object1, object3, object4, object5, object6, arrayOfObject4); 
          arrayOfObject4[i] = object2[i + 5];
        } 
      case 241:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return string$Eq$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 243:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return string$Ls$Gr$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 246:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return string$Ls$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 249:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return string$Gr$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 251:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return string$Ls$Eq$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 253:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return string$Gr$Eq$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 256:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringCi$Eq$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 258:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringCi$Ls$Gr$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 261:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringCi$Ls$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 264:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringCi$Gr$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 266:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringCi$Ls$Eq$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 268:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringCi$Gr$Eq$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 269:
        return $PcStringHash(object2[0], object2[1], object2[2], object2[3], object2[4]);
      case 270:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringHash$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 272:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringHashCi$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 273:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringUpcase$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 274:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringUpcase$Ex$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 275:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringDowncase$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 276:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringDowncase$Ex$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 278:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringTitlecase$Ex$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 279:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringTitlecase$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 284:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringTrim$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 285:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringTrimRight$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 286:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringTrimBoth$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 288:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringPadRight$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 290:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringPad$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 291:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringDelete$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 292:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringFilter$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 293:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringIndex$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 294:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringIndexRight$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 295:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringSkip$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 296:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringSkipRight$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 297:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringCount$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 298:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringFill$Ex$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 299:
        j = object2.length - 3;
        object1 = object2[0];
        object3 = object2[1];
        try {
          i = ((Number)object3).intValue();
          object4 = object2[2];
          try {
            object3 = object4;
            if (j <= 0)
              return stringCopy$Ex(object1, i, (CharSequence)object3); 
            int k = j - 1;
            object4 = object2[3];
            try {
              j = ((Number)object4).intValue();
              if (k <= 0)
                return stringCopy$Ex(object1, i, (CharSequence)object3, j); 
              object2 = object2[4];
              try {
                k = ((Number)object2).intValue();
                return stringCopy$Ex(object1, i, (CharSequence)object3, j, k);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-copy!", 5, object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-copy!", 4, object4);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-copy!", 3, object4);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-copy!", 2, object3);
        } 
      case 302:
        object3 = object2[0];
        try {
          object1 = object3;
          object3 = object2[1];
          try {
            i = ((Number)object3).intValue();
            object4 = object2[2];
            try {
              object3 = object4;
              object4 = object2[3];
              try {
                j = ((Number)object4).intValue();
                object2 = object2[4];
                try {
                  int k = ((Number)object2).intValue();
                  return $PcStringCopy$Ex((CharSequence)object1, i, (CharSequence)object3, j, k);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "%string-copy!", 5, object2);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "%string-copy!", 4, object4);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%string-copy!", 3, object4);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%string-copy!", 2, object3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%string-copy!", 1, object3);
        } 
      case 303:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringContains$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 304:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringContainsCi$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 305:
        return $PcKmpSearch(object2[0], object2[1], object2[2], object2[3], object2[4], object2[5], object2[6]);
      case 306:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return makeKmpRestartVector$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 307:
        return kmpStep(object2[0], object2[1], object2[2], object2[3], object2[4], object2[5]);
      case 308:
        object1 = object2[0];
        object3 = object2[1];
        object4 = object2[2];
        object5 = object2[3];
        i = object2.length - 4;
        object6 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringKmpPartialSearch$V(object1, object3, object4, object5, (Object[])object6); 
          object6[i] = object2[i + 4];
        } 
      case 310:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringReverse$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 311:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringReverse$Ex$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 313:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return string$To$List$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 314:
        return stringAppend$SlShared$V((Object[])object2);
      case 317:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringConcatenateReverse$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 318:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringConcatenateReverse$SlShared$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 320:
        object1 = object2[0];
        object3 = object2[1];
        object4 = object2[2];
        object5 = object2[3];
        i = object2.length - 4;
        object6 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringReplace$V(object1, object3, object4, object5, (Object[])object6); 
          object6[i] = object2[i + 4];
        } 
      case 321:
        object1 = object2[0];
        i = object2.length - 1;
        object3 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringTokenize$V(object1, (Object[])object3); 
          object3[i] = object2[i + 1];
        } 
      case 323:
        object1 = object2[0];
        object3 = object2[1];
        i = object2.length - 2;
        object4 = new Object[i];
        while (true) {
          if (--i < 0)
            return xsubstring$V(object1, object3, (Object[])object4); 
          object4[i] = object2[i + 2];
        } 
      case 326:
        object1 = object2[0];
        object3 = object2[1];
        object4 = object2[2];
        object5 = object2[3];
        i = object2.length - 4;
        object6 = new Object[i];
        while (true) {
          if (--i < 0)
            return stringXcopy$Ex$V(object1, object3, object4, object5, (Object[])object6); 
          object6[i] = object2[i + 4];
        } 
      case 327:
        return $PcMultispanRepcopy$Ex(object2[0], object2[1], object2[2], object2[3], object2[4], object2[5], object2[6]);
      case 328:
        break;
    } 
    Object object1 = object2[0];
    int i = object2.length - 1;
    Object object3 = new Object[i];
    while (true) {
      if (--i < 0)
        return stringJoin$V(object1, (Object[])object3); 
      object3[i] = object2[i + 1];
    } 
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 325:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 324:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 322:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 316:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 315:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 312:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 309:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 289:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 287:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 271:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 267:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 265:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 263:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 262:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 260:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 259:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 257:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 255:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 254:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 252:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 250:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 248:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 247:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 245:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 244:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 242:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 240:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 239:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 217:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 211:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 209:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 199:
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
      case 283:
        if (paramObject1 instanceof CharSequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786431;
      case 282:
        if (paramObject1 instanceof CharSequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786431;
      case 281:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 280:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 218:
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
      case 299:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        if (paramObject3 instanceof CharSequence) {
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return -786429;
      case 277:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 201:
        if (paramObject1 instanceof CharSequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return -786431;
      case 197:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 196:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 194:
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
    switch (paramModuleMethod.selector) {
      default:
        return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
      case 319:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      case 299:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        if (paramObject3 instanceof CharSequence) {
          paramCallContext.value3 = paramObject3;
          paramCallContext.value4 = paramObject4;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 4;
          return 0;
        } 
        return -786429;
      case 206:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      case 204:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      case 198:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      case 195:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    if (paramObject2 instanceof CharSequence) {
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 4;
      return 0;
    } 
    return -786430;
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 328:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 327:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 326:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 323:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 321:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 320:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 318:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 317:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 314:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 313:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 311:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 310:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 308:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 307:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 306:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 305:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 304:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 303:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 302:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 299:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 298:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 297:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 296:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 295:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 294:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 293:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 292:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 291:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 290:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 288:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 286:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 285:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 284:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 279:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 278:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 276:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 275:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 274:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 273:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 272:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 270:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 269:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 268:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 266:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 264:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 261:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 258:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 256:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 253:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 251:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 249:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 246:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 243:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 241:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 238:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 237:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 236:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 235:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 234:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 233:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 232:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 231:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 230:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 229:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 228:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 227:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 226:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 225:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 224:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 223:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 222:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 221:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 220:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 219:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 216:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 215:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 214:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 213:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 212:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 210:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 208:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 207:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 205:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 203:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 202:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 200:
        break;
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 5;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
  
  public class frame extends ModuleBody {
    Object args;
    
    final ModuleMethod lambda$Fn1 = new ModuleMethod(this, 1, null, 0);
    
    final ModuleMethod lambda$Fn2;
    
    Object proc;
    
    Object s;
    
    int slen;
    
    Object start;
    
    public frame() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:150");
      this.lambda$Fn2 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 1) ? lambda1() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 2) ? lambda2(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda1() {
      if (lists.isPair(this.args)) {
        Object object1 = lists.car.apply1(this.args);
        Object object2 = lists.cdr.apply1(this.args);
        boolean bool = numbers.isInteger(object1);
        if (bool) {
          bool = numbers.isExact(object1);
          return (bool ? (Scheme.numLEq.apply2(object1, Integer.valueOf(this.slen)) != Boolean.FALSE) : bool) ? misc.values(new Object[] { object1, object2 }) : misc.error$V("Illegal substring END spec", new Object[] { this.proc, object1, this.s });
        } 
        return bool ? misc.values(new Object[] { object1, object2 }) : misc.error$V("Illegal substring END spec", new Object[] { this.proc, object1, this.s });
      } 
      return misc.values(new Object[] { Integer.valueOf(this.slen), this.args });
    }
    
    Object lambda2(Object param1Object1, Object param1Object2) {
      return (Scheme.numLEq.apply2(this.start, param1Object1) != Boolean.FALSE) ? misc.values(new Object[] { param1Object2, this.start, param1Object1 }) : misc.error$V("Illegal substring START/END spec", new Object[] { this.proc, this.start, param1Object1, this.s });
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
  
  public class frame0 extends ModuleBody {
    Object args;
    
    final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, null, 0);
    
    final ModuleMethod lambda$Fn4;
    
    Object proc;
    
    Object s;
    
    public frame0() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 12291);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:174");
      this.lambda$Fn4 = moduleMethod;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 3) ? lambda3() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 4) ? lambda4(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda3() {
      return srfi13.stringParseStart$PlEnd(this.proc, this.s, this.args);
    }
    
    Object lambda4(Object param1Object1, Object param1Object2, Object param1Object3) {
      return lists.isPair(param1Object1) ? misc.error$V("Extra arguments to procedure", new Object[] { this.proc, param1Object1 }) : misc.values(new Object[] { param1Object2, param1Object3 });
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
  
  public class frame1 extends ModuleBody {
    final ModuleMethod lambda$Fn6;
    
    int slen;
    
    Object start;
    
    public frame1() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:227");
      this.lambda$Fn6 = moduleMethod;
    }
    
    static boolean lambda5(Object param1Object) {
      boolean bool2 = numbers.isInteger(param1Object);
      boolean bool1 = bool2;
      if (bool2) {
        bool2 = numbers.isExact(param1Object);
        bool1 = bool2;
        if (bool2)
          bool1 = ((Boolean)Scheme.numLEq.apply2(srfi13.Lit0, param1Object)).booleanValue(); 
      } 
      return bool1;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 5) ? (lambda6(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda6(Object param1Object) {
      boolean bool2 = numbers.isInteger(param1Object);
      boolean bool1 = bool2;
      if (bool2) {
        bool2 = numbers.isExact(param1Object);
        bool1 = bool2;
        if (bool2) {
          Object object = Scheme.numLEq.apply2(this.start, param1Object);
          try {
            bool2 = ((Boolean)object).booleanValue();
            bool1 = bool2;
            if (bool2)
              bool1 = ((Boolean)Scheme.numLEq.apply2(param1Object, Integer.valueOf(this.slen))).booleanValue(); 
            return bool1;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "x", -2, object);
          } 
        } 
      } 
      return bool1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 5) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame10 extends ModuleBody {
    Object criterion;
    
    final ModuleMethod lambda$Fn25 = new ModuleMethod(this, 22, null, 0);
    
    final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 23, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 22) ? lambda25() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 23) ? lambda26(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda25() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnany, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda26(Object param1Object1, Object param1Object2) {
      if (characters.isChar(this.criterion))
        while (true) {
          Object object = Scheme.numLss.apply2(param1Object1, param1Object2);
          try {
            boolean bool = ((Boolean)object).booleanValue();
            if (bool) {
              Object object1 = this.criterion;
              try {
                object = object1;
                object1 = this.s;
                try {
                  CharSequence charSequence = (CharSequence)object1;
                  try {
                    int i = ((Number)param1Object1).intValue();
                    bool = characters.isChar$Eq((Char)object, Char.make(strings.stringRef(charSequence, i)));
                    if (bool)
                      return bool ? Boolean.TRUE : Boolean.FALSE; 
                    param1Object1 = AddOp.$Pl.apply2(param1Object1, srfi13.Lit1);
                  } catch (ClassCastException classCastException2) {
                    throw new WrongType(classCastException2, "string-ref", 2, param1Object1);
                  } 
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException1, "string-ref", 1, object1);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException1, "char=?", 1, object1);
              } 
            } 
            return bool ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "x", -2, object);
          } 
        }  
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$char$Mnset$Qu;
      try {
        Object object2 = location.get();
        if (applyToArgs.apply2(object2, this.criterion) != Boolean.FALSE) {
          ClassCastException classCastException3 = classCastException1;
          while (true) {
            object1 = Scheme.numLss.apply2(classCastException3, classCastException2);
            try {
              boolean bool = ((Boolean)object1).booleanValue();
              if (bool) {
                object1 = Scheme.applyToArgs;
                object2 = srfi13.loc$char$Mnset$Mncontains$Qu;
                try {
                  Object object3 = object2.get();
                  Object object4 = this.criterion;
                  object2 = this.s;
                  try {
                    Object object;
                    CharSequence charSequence = (CharSequence)object2;
                    try {
                      int i = ((Number)classCastException3).intValue();
                      object2 = object1.apply3(object3, object4, Char.make(strings.stringRef(charSequence, i)));
                      object1 = object2;
                      if (object2 == Boolean.FALSE) {
                        object = AddOp.$Pl.apply2(classCastException3, srfi13.Lit1);
                        continue;
                      } 
                    } catch (ClassCastException classCastException4) {
                      throw new WrongType(classCastException4, "string-ref", 2, object);
                    } 
                  } catch (ClassCastException classCastException4) {
                    throw new WrongType(classCastException4, "string-ref", 1, object2);
                  } 
                } catch (UnboundLocationException object1) {
                  object1.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 518, 9);
                  throw object1;
                } 
              } 
              return bool ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "x", -2, object1);
            } 
          } 
        } 
        if (misc.isProcedure(this.criterion)) {
          Object object = Scheme.numLss.apply2(object1, classCastException);
          try {
            boolean bool = ((Boolean)object).booleanValue();
            if (bool)
              while (true) {
                object = this.s;
                try {
                  object2 = object;
                  try {
                    int i = ((Number)object1).intValue();
                    i = strings.stringRef((CharSequence)object2, i);
                    object = AddOp.$Pl.apply2(object1, srfi13.Lit1);
                    if (Scheme.numEqu.apply2(object, classCastException) != Boolean.FALSE)
                      return Scheme.applyToArgs.apply2(this.criterion, Char.make(i)); 
                    object2 = Scheme.applyToArgs.apply2(this.criterion, Char.make(i));
                    object1 = object2;
                    if (object2 == Boolean.FALSE)
                      object1 = object; 
                  } catch (ClassCastException classCastException3) {
                    throw new WrongType(classCastException3, "string-ref", 2, object1);
                  } 
                } catch (ClassCastException classCastException3) {
                  throw new WrongType(classCastException3, "string-ref", 1, object);
                } 
              }  
            return bool ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException3) {
            throw new WrongType(classCastException3, "x", -2, object);
          } 
        } 
        return misc.error$V("Second param is neither char-set, char, or predicate procedure.", new Object[] { srfi13.string$Mnany, this.criterion });
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 515, 5);
        throw unboundLocationException;
      } 
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
  
  public class frame11 extends ModuleBody {
    final ModuleMethod lambda$Fn28 = new ModuleMethod(this, 26, null, 0);
    
    final ModuleMethod lambda$Fn29 = new ModuleMethod(this, 27, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 26) ? lambda28() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 27) ? lambda29(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda28() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Mnlength, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda29(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame12 frame12 = new frame12();
      frame12.staticLink = this;
      frame12.rest = param1Object1;
      frame12.start1 = param1Object2;
      frame12.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame12.lambda$Fn30, (Procedure)frame12.lambda$Fn31);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 26) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 27) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame12 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn30 = new ModuleMethod(this, 24, null, 0);
      
      final ModuleMethod lambda$Fn31 = new ModuleMethod(this, 25, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame11 staticLink;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 24) ? lambda30() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 25) ? lambda31(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda30() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnlength, this.staticLink.s2, this.rest);
      }
      
      Object lambda31(Object param2Object1, Object param2Object2) {
        return srfi13.$PcStringPrefixLength(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2);
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
  
  public class frame12 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn30 = new ModuleMethod(this, 24, null, 0);
    
    final ModuleMethod lambda$Fn31 = new ModuleMethod(this, 25, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame11 staticLink;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 24) ? lambda30() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 25) ? lambda31(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda30() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnlength, this.staticLink.s2, this.rest);
    }
    
    Object lambda31(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringPrefixLength(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2);
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
  
  public class frame13 extends ModuleBody {
    final ModuleMethod lambda$Fn32 = new ModuleMethod(this, 30, null, 0);
    
    final ModuleMethod lambda$Fn33 = new ModuleMethod(this, 31, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 30) ? lambda32() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 31) ? lambda33(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda32() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Mnlength, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda33(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame14 frame14 = new frame14();
      frame14.staticLink = this;
      frame14.rest = param1Object1;
      frame14.start1 = param1Object2;
      frame14.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame14.lambda$Fn34, (Procedure)frame14.lambda$Fn35);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 30) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 31) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame14 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn34 = new ModuleMethod(this, 28, null, 0);
      
      final ModuleMethod lambda$Fn35 = new ModuleMethod(this, 29, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame13 staticLink;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 28) ? lambda34() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 29) ? lambda35(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda34() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnlength, this.staticLink.s2, this.rest);
      }
      
      Object lambda35(Object param2Object1, Object param2Object2) {
        return srfi13.$PcStringSuffixLength(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2);
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
  
  public class frame14 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn34 = new ModuleMethod(this, 28, null, 0);
    
    final ModuleMethod lambda$Fn35 = new ModuleMethod(this, 29, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame13 staticLink;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 28) ? lambda34() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 29) ? lambda35(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda34() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnlength, this.staticLink.s2, this.rest);
    }
    
    Object lambda35(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringSuffixLength(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2);
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
  
  public class frame15 extends ModuleBody {
    final ModuleMethod lambda$Fn36 = new ModuleMethod(this, 34, null, 0);
    
    final ModuleMethod lambda$Fn37 = new ModuleMethod(this, 35, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 34) ? lambda36() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 35) ? lambda37(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda36() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Mnlength$Mnci, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda37(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame16 frame16 = new frame16();
      frame16.staticLink = this;
      frame16.rest = param1Object1;
      frame16.start1 = param1Object2;
      frame16.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame16.lambda$Fn38, (Procedure)frame16.lambda$Fn39);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 34) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 35) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame16 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn38 = new ModuleMethod(this, 32, null, 0);
      
      final ModuleMethod lambda$Fn39 = new ModuleMethod(this, 33, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame15 staticLink;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 32) ? lambda38() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 33) ? Integer.valueOf(lambda39(param2Object1, param2Object2)) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda38() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnlength$Mnci, this.staticLink.s2, this.rest);
      }
      
      int lambda39(Object param2Object1, Object param2Object2) {
        Object object1 = this.staticLink.s1;
        Object object2 = this.start1;
        try {
          int i = ((Number)object2).intValue();
          object2 = this.end1;
          try {
            int j = ((Number)object2).intValue();
            object2 = this.staticLink.s2;
            try {
              int k = ((Number)param2Object1).intValue();
              try {
                int m = ((Number)param2Object2).intValue();
                return srfi13.$PcStringPrefixLengthCi(object1, i, j, object2, k, m);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "%string-prefix-length-ci", 5, param2Object2);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "%string-prefix-length-ci", 4, classCastException);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%string-prefix-length-ci", 2, object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%string-prefix-length-ci", 1, object2);
        } 
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 32) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 33) {
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
  
  public class frame16 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn38 = new ModuleMethod(this, 32, null, 0);
    
    final ModuleMethod lambda$Fn39 = new ModuleMethod(this, 33, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame15 staticLink;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 32) ? lambda38() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 33) ? Integer.valueOf(lambda39(param1Object1, param1Object2)) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda38() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnlength$Mnci, this.staticLink.s2, this.rest);
    }
    
    int lambda39(Object param1Object1, Object param1Object2) {
      Object object1 = this.staticLink.s1;
      Object object2 = this.start1;
      try {
        int i = ((Number)object2).intValue();
        object2 = this.end1;
        try {
          int j = ((Number)object2).intValue();
          object2 = this.staticLink.s2;
          try {
            int k = ((Number)param1Object1).intValue();
            try {
              int m = ((Number)param1Object2).intValue();
              return srfi13.$PcStringPrefixLengthCi(object1, i, j, object2, k, m);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%string-prefix-length-ci", 5, param1Object2);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "%string-prefix-length-ci", 4, classCastException);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%string-prefix-length-ci", 2, object2);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "%string-prefix-length-ci", 1, object2);
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 32) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 33) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame17 extends ModuleBody {
    final ModuleMethod lambda$Fn40 = new ModuleMethod(this, 38, null, 0);
    
    final ModuleMethod lambda$Fn41 = new ModuleMethod(this, 39, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 38) ? lambda40() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 39) ? lambda41(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda40() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Mnlength$Mnci, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda41(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame18 frame18 = new frame18();
      frame18.staticLink = this;
      frame18.rest = param1Object1;
      frame18.start1 = param1Object2;
      frame18.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame18.lambda$Fn42, (Procedure)frame18.lambda$Fn43);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 38) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 39) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame18 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn42 = new ModuleMethod(this, 36, null, 0);
      
      final ModuleMethod lambda$Fn43 = new ModuleMethod(this, 37, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame17 staticLink;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 36) ? lambda42() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 37) ? Integer.valueOf(lambda43(param2Object1, param2Object2)) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda42() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnlength$Mnci, this.staticLink.s2, this.rest);
      }
      
      int lambda43(Object param2Object1, Object param2Object2) {
        Object object1 = this.staticLink.s1;
        Object object2 = this.start1;
        try {
          int i = ((Number)object2).intValue();
          object2 = this.end1;
          try {
            int j = ((Number)object2).intValue();
            object2 = this.staticLink.s2;
            try {
              int k = ((Number)param2Object1).intValue();
              try {
                int m = ((Number)param2Object2).intValue();
                return srfi13.$PcStringSuffixLengthCi(object1, i, j, object2, k, m);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "%string-suffix-length-ci", 5, param2Object2);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "%string-suffix-length-ci", 4, classCastException);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%string-suffix-length-ci", 2, object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%string-suffix-length-ci", 1, object2);
        } 
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 36) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 37) {
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
  
  public class frame18 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn42 = new ModuleMethod(this, 36, null, 0);
    
    final ModuleMethod lambda$Fn43 = new ModuleMethod(this, 37, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame17 staticLink;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 36) ? lambda42() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 37) ? Integer.valueOf(lambda43(param1Object1, param1Object2)) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda42() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnlength$Mnci, this.staticLink.s2, this.rest);
    }
    
    int lambda43(Object param1Object1, Object param1Object2) {
      Object object1 = this.staticLink.s1;
      Object object2 = this.start1;
      try {
        int i = ((Number)object2).intValue();
        object2 = this.end1;
        try {
          int j = ((Number)object2).intValue();
          object2 = this.staticLink.s2;
          try {
            int k = ((Number)param1Object1).intValue();
            try {
              int m = ((Number)param1Object2).intValue();
              return srfi13.$PcStringSuffixLengthCi(object1, i, j, object2, k, m);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%string-suffix-length-ci", 5, param1Object2);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "%string-suffix-length-ci", 4, classCastException);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%string-suffix-length-ci", 2, object2);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "%string-suffix-length-ci", 1, object2);
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 36) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 37) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame19 extends ModuleBody {
    final ModuleMethod lambda$Fn44 = new ModuleMethod(this, 42, null, 0);
    
    final ModuleMethod lambda$Fn45 = new ModuleMethod(this, 43, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 42) ? lambda44() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 43) ? lambda45(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda44() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Qu, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda45(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame20 frame20 = new frame20();
      frame20.staticLink = this;
      frame20.rest = param1Object1;
      frame20.start1 = param1Object2;
      frame20.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame20.lambda$Fn46, (Procedure)frame20.lambda$Fn47);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 42) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 43) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame20 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn46 = new ModuleMethod(this, 40, null, 0);
      
      final ModuleMethod lambda$Fn47 = new ModuleMethod(this, 41, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame19 staticLink;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 40) ? lambda46() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 41) ? lambda47(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda46() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Qu, this.staticLink.s2, this.rest);
      }
      
      Object lambda47(Object param2Object1, Object param2Object2) {
        return srfi13.$PcStringPrefix$Qu(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 40) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 41) {
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
  
  public class frame2 extends ModuleBody {
    final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 6, null, 0);
    
    final ModuleMethod lambda$Fn8 = new ModuleMethod(this, 7, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 6) ? lambda7() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 7) ? lambda8(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda7() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncopy, this.s, this.maybe$Mnstart$Plend);
    }
    
    CharSequence lambda8(Object param1Object1, Object param1Object2) {
      Object object = this.s;
      try {
        CharSequence charSequence = (CharSequence)object;
        try {
          int i = ((Number)param1Object1).intValue();
          try {
            int j = ((Number)param1Object2).intValue();
            return strings.substring(charSequence, i, j);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "substring", 3, param1Object2);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "substring", 2, classCastException);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "substring", 1, object);
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 6) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
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
  }
  
  public class frame20 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn46 = new ModuleMethod(this, 40, null, 0);
    
    final ModuleMethod lambda$Fn47 = new ModuleMethod(this, 41, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame19 staticLink;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 40) ? lambda46() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 41) ? lambda47(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda46() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Qu, this.staticLink.s2, this.rest);
    }
    
    Object lambda47(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringPrefix$Qu(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 40) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 41) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame21 extends ModuleBody {
    final ModuleMethod lambda$Fn48 = new ModuleMethod(this, 46, null, 0);
    
    final ModuleMethod lambda$Fn49 = new ModuleMethod(this, 47, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 46) ? lambda48() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 47) ? lambda49(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda48() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Qu, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda49(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame22 frame22 = new frame22();
      frame22.staticLink = this;
      frame22.rest = param1Object1;
      frame22.start1 = param1Object2;
      frame22.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame22.lambda$Fn50, (Procedure)frame22.lambda$Fn51);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 46) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 47) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame22 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn50 = new ModuleMethod(this, 44, null, 0);
      
      final ModuleMethod lambda$Fn51 = new ModuleMethod(this, 45, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame21 staticLink;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 44) ? lambda50() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 45) ? lambda51(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda50() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Qu, this.staticLink.s2, this.rest);
      }
      
      Object lambda51(Object param2Object1, Object param2Object2) {
        return srfi13.$PcStringSuffix$Qu(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 44) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
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
  
  public class frame22 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn50 = new ModuleMethod(this, 44, null, 0);
    
    final ModuleMethod lambda$Fn51 = new ModuleMethod(this, 45, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame21 staticLink;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 44) ? lambda50() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 45) ? lambda51(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda50() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Qu, this.staticLink.s2, this.rest);
    }
    
    Object lambda51(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringSuffix$Qu(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 44) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
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
  
  public class frame23 extends ModuleBody {
    final ModuleMethod lambda$Fn52 = new ModuleMethod(this, 50, null, 0);
    
    final ModuleMethod lambda$Fn53 = new ModuleMethod(this, 51, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 50) ? lambda52() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 51) ? lambda53(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda52() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Mnci$Qu, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda53(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame24 frame24 = new frame24();
      frame24.staticLink = this;
      frame24.rest = param1Object1;
      frame24.start1 = param1Object2;
      frame24.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame24.lambda$Fn54, (Procedure)frame24.lambda$Fn55);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 50) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 51) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame24 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn54 = new ModuleMethod(this, 48, null, 0);
      
      final ModuleMethod lambda$Fn55 = new ModuleMethod(this, 49, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame23 staticLink;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 48) ? lambda54() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 49) ? lambda55(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda54() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnci$Qu, this.staticLink.s2, this.rest);
      }
      
      Object lambda55(Object param2Object1, Object param2Object2) {
        return srfi13.$PcStringPrefixCi$Qu(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 48) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
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
  
  public class frame24 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn54 = new ModuleMethod(this, 48, null, 0);
    
    final ModuleMethod lambda$Fn55 = new ModuleMethod(this, 49, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame23 staticLink;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 48) ? lambda54() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 49) ? lambda55(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda54() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnci$Qu, this.staticLink.s2, this.rest);
    }
    
    Object lambda55(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringPrefixCi$Qu(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 48) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
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
  
  public class frame25 extends ModuleBody {
    final ModuleMethod lambda$Fn56 = new ModuleMethod(this, 54, null, 0);
    
    final ModuleMethod lambda$Fn57 = new ModuleMethod(this, 55, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 54) ? lambda56() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 55) ? lambda57(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda56() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Mnci$Qu, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda57(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame26 frame26 = new frame26();
      frame26.staticLink = this;
      frame26.rest = param1Object1;
      frame26.start1 = param1Object2;
      frame26.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame26.lambda$Fn58, (Procedure)frame26.lambda$Fn59);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 54) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 55) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame26 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn58 = new ModuleMethod(this, 52, null, 0);
      
      final ModuleMethod lambda$Fn59 = new ModuleMethod(this, 53, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame25 staticLink;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 52) ? lambda58() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 53) ? lambda59(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda58() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnci$Qu, this.staticLink.s2, this.rest);
      }
      
      Object lambda59(Object param2Object1, Object param2Object2) {
        return srfi13.$PcStringSuffixCi$Qu(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 52) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 53) {
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
  
  public class frame26 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn58 = new ModuleMethod(this, 52, null, 0);
    
    final ModuleMethod lambda$Fn59 = new ModuleMethod(this, 53, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame25 staticLink;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 52) ? lambda58() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 53) ? lambda59(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda58() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnci$Qu, this.staticLink.s2, this.rest);
    }
    
    Object lambda59(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringSuffixCi$Qu(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 52) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 53) {
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
    final ModuleMethod lambda$Fn60 = new ModuleMethod(this, 58, null, 0);
    
    final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 59, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object proc$Eq;
    
    Object proc$Gr;
    
    Object proc$Ls;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 58) ? lambda60() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 59) ? lambda61(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda60() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mncompare, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda61(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame28 frame28 = new frame28();
      frame28.staticLink = this;
      frame28.rest = param1Object1;
      frame28.start1 = param1Object2;
      frame28.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame28.lambda$Fn62, (Procedure)frame28.lambda$Fn63);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 58) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 59) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame28 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn62 = new ModuleMethod(this, 56, null, 0);
      
      final ModuleMethod lambda$Fn63 = new ModuleMethod(this, 57, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame27 staticLink;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 56) ? lambda62() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 57) ? lambda63(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda62() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncompare, this.staticLink.s2, this.rest);
      }
      
      Object lambda63(Object param2Object1, Object param2Object2) {
        return srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2, this.staticLink.proc$Ls, this.staticLink.proc$Eq, this.staticLink.proc$Gr);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 56) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 57) {
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
    Object end1;
    
    final ModuleMethod lambda$Fn62 = new ModuleMethod(this, 56, null, 0);
    
    final ModuleMethod lambda$Fn63 = new ModuleMethod(this, 57, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame27 staticLink;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 56) ? lambda62() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 57) ? lambda63(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda62() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncompare, this.staticLink.s2, this.rest);
    }
    
    Object lambda63(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2, this.staticLink.proc$Ls, this.staticLink.proc$Eq, this.staticLink.proc$Gr);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 56) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 57) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame29 extends ModuleBody {
    final ModuleMethod lambda$Fn64 = new ModuleMethod(this, 62, null, 0);
    
    final ModuleMethod lambda$Fn65 = new ModuleMethod(this, 63, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object proc$Eq;
    
    Object proc$Gr;
    
    Object proc$Ls;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 62) ? lambda64() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 63) ? lambda65(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda64() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mncompare$Mnci, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda65(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame30 frame30 = new frame30();
      frame30.staticLink = this;
      frame30.rest = param1Object1;
      frame30.start1 = param1Object2;
      frame30.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame30.lambda$Fn66, (Procedure)frame30.lambda$Fn67);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 62) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 63) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame30 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn66 = new ModuleMethod(this, 60, null, 0);
      
      final ModuleMethod lambda$Fn67 = new ModuleMethod(this, 61, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame29 staticLink;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 60) ? lambda66() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 61) ? lambda67(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda66() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncompare$Mnci, this.staticLink.s2, this.rest);
      }
      
      Object lambda67(Object param2Object1, Object param2Object2) {
        return srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2, this.staticLink.proc$Ls, this.staticLink.proc$Eq, this.staticLink.proc$Gr);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 60) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 61) {
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
  
  public class frame3 extends ModuleBody {
    final ModuleMethod lambda$Fn10 = new ModuleMethod(this, 9, null, 8194);
    
    final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 8, null, 0);
    
    LList maybe$Mnstart$Plend;
    
    Object proc;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 8) ? lambda9() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 9) ? lambda10(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda10(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringMap(this.proc, this.s, param1Object1, param1Object2);
    }
    
    Object lambda9() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnmap, this.s, this.maybe$Mnstart$Plend);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 8) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
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
  }
  
  public class frame30 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn66 = new ModuleMethod(this, 60, null, 0);
    
    final ModuleMethod lambda$Fn67 = new ModuleMethod(this, 61, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame29 staticLink;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 60) ? lambda66() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 61) ? lambda67(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda66() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncompare$Mnci, this.staticLink.s2, this.rest);
    }
    
    Object lambda67(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2, this.staticLink.proc$Ls, this.staticLink.proc$Eq, this.staticLink.proc$Gr);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 60) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 61) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame31 extends ModuleBody {
    final ModuleMethod lambda$Fn68 = new ModuleMethod(this, 66, null, 0);
    
    final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 67, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 66) ? lambda68() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 67) ? lambda69(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda68() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Eq, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda69(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame32 frame32 = new frame32();
      frame32.staticLink = this;
      frame32.rest = param1Object1;
      frame32.start1 = param1Object2;
      frame32.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame32.lambda$Fn70, (Procedure)frame32.lambda$Fn71);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 66) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 67) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame32 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn70 = new ModuleMethod(this, 64, null, 0);
      
      final ModuleMethod lambda$Fn71 = new ModuleMethod(this, 65, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame31 staticLink;
      
      static Boolean lambda72(Object param2Object) {
        return Boolean.FALSE;
      }
      
      static Boolean lambda73(Object param2Object) {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 64) ? lambda70() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 65) ? lambda71(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda70() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Eq, this.staticLink.s2, this.rest);
      }
      
      Object lambda71(Object param2Object1, Object param2Object2) {
        Object object = Scheme.numEqu.apply2(AddOp.$Mn.apply2(this.end1, this.start1), AddOp.$Mn.apply2(param2Object2, param2Object1));
        try {
          boolean bool2;
          boolean bool1 = ((Boolean)object).booleanValue();
          if (bool1) {
            if (this.staticLink.s1 == this.staticLink.s2) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            bool2 = bool1;
            if (bool1) {
              object = Scheme.numEqu.apply2(this.start1, param2Object1);
              try {
                bool2 = ((Boolean)object).booleanValue();
                return bool2 ? (bool2 ? Boolean.TRUE : Boolean.FALSE) : srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2, srfi13.lambda$Fn72, misc.values, srfi13.lambda$Fn73);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "x", -2, object);
              } 
            } 
          } else {
            return bool1 ? Boolean.TRUE : Boolean.FALSE;
          } 
          return bool2 ? (bool2 ? Boolean.TRUE : Boolean.FALSE) : srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, classCastException, param2Object2, srfi13.lambda$Fn72, misc.values, srfi13.lambda$Fn73);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "x", -2, object);
        } 
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
  
  public class frame32 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn70 = new ModuleMethod(this, 64, null, 0);
    
    final ModuleMethod lambda$Fn71 = new ModuleMethod(this, 65, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame31 staticLink;
    
    static Boolean lambda72(Object param1Object) {
      return Boolean.FALSE;
    }
    
    static Boolean lambda73(Object param1Object) {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 64) ? lambda70() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 65) ? lambda71(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda70() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Eq, this.staticLink.s2, this.rest);
    }
    
    Object lambda71(Object param1Object1, Object param1Object2) {
      Object object = Scheme.numEqu.apply2(AddOp.$Mn.apply2(this.end1, this.start1), AddOp.$Mn.apply2(param1Object2, param1Object1));
      try {
        boolean bool2;
        boolean bool1 = ((Boolean)object).booleanValue();
        if (bool1) {
          if (this.staticLink.s1 == this.staticLink.s2) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          bool2 = bool1;
          if (bool1) {
            object = Scheme.numEqu.apply2(this.start1, param1Object1);
            try {
              bool2 = ((Boolean)object).booleanValue();
              return bool2 ? (bool2 ? Boolean.TRUE : Boolean.FALSE) : srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2, srfi13.lambda$Fn72, misc.values, srfi13.lambda$Fn73);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "x", -2, object);
            } 
          } 
        } else {
          return bool1 ? Boolean.TRUE : Boolean.FALSE;
        } 
        return bool2 ? (bool2 ? Boolean.TRUE : Boolean.FALSE) : srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, classCastException, param1Object2, srfi13.lambda$Fn72, misc.values, srfi13.lambda$Fn73);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "x", -2, object);
      } 
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
  
  public class frame33 extends ModuleBody {
    final ModuleMethod lambda$Fn74 = new ModuleMethod(this, 70, null, 0);
    
    final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 71, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 70) ? lambda74() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 71) ? lambda75(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda74() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Ls$Gr, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda75(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame34 frame34 = new frame34();
      frame34.staticLink = this;
      frame34.rest = param1Object1;
      frame34.start1 = param1Object2;
      frame34.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame34.lambda$Fn76, (Procedure)frame34.lambda$Fn77);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 70) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 71) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame34 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn76 = new ModuleMethod(this, 68, null, 0);
      
      final ModuleMethod lambda$Fn77 = new ModuleMethod(this, 69, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame33 staticLink;
      
      static Boolean lambda78(Object param2Object) {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 68) ? lambda76() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 69) ? lambda77(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda76() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls$Gr, this.staticLink.s2, this.rest);
      }
      
      Object lambda77(Object param2Object1, Object param2Object2) {
        // Byte code:
        //   0: iconst_1
        //   1: istore #4
        //   3: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
        //   6: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
        //   9: aload_0
        //   10: getfield end1 : Ljava/lang/Object;
        //   13: aload_0
        //   14: getfield start1 : Ljava/lang/Object;
        //   17: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   20: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
        //   23: aload_2
        //   24: aload_1
        //   25: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   28: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   31: astore #5
        //   33: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
        //   36: astore #6
        //   38: aload #5
        //   40: aload #6
        //   42: if_acmpeq -> 65
        //   45: iconst_1
        //   46: istore_3
        //   47: iload_3
        //   48: iconst_1
        //   49: iadd
        //   50: iconst_1
        //   51: iand
        //   52: istore_3
        //   53: iload_3
        //   54: ifeq -> 74
        //   57: iload_3
        //   58: ifeq -> 70
        //   61: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
        //   64: areturn
        //   65: iconst_0
        //   66: istore_3
        //   67: goto -> 47
        //   70: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
        //   73: areturn
        //   74: aload_0
        //   75: getfield staticLink : Lgnu/kawa/slib/srfi13$frame33;
        //   78: getfield s1 : Ljava/lang/Object;
        //   81: aload_0
        //   82: getfield staticLink : Lgnu/kawa/slib/srfi13$frame33;
        //   85: getfield s2 : Ljava/lang/Object;
        //   88: if_acmpne -> 172
        //   91: iconst_1
        //   92: istore_3
        //   93: iload_3
        //   94: ifeq -> 182
        //   97: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
        //   100: aload_0
        //   101: getfield start1 : Ljava/lang/Object;
        //   104: aload_1
        //   105: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   108: astore #5
        //   110: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
        //   113: astore #6
        //   115: aload #5
        //   117: aload #6
        //   119: if_acmpeq -> 177
        //   122: iload #4
        //   124: istore_3
        //   125: iload_3
        //   126: iconst_1
        //   127: iadd
        //   128: iconst_1
        //   129: iand
        //   130: istore_3
        //   131: iload_3
        //   132: ifeq -> 185
        //   135: aload_0
        //   136: getfield staticLink : Lgnu/kawa/slib/srfi13$frame33;
        //   139: getfield s1 : Ljava/lang/Object;
        //   142: aload_0
        //   143: getfield start1 : Ljava/lang/Object;
        //   146: aload_0
        //   147: getfield end1 : Ljava/lang/Object;
        //   150: aload_0
        //   151: getfield staticLink : Lgnu/kawa/slib/srfi13$frame33;
        //   154: getfield s2 : Ljava/lang/Object;
        //   157: aload_1
        //   158: aload_2
        //   159: getstatic kawa/lib/misc.values : Lgnu/expr/ModuleMethod;
        //   162: getstatic gnu/kawa/slib/srfi13.lambda$Fn78 : Lgnu/expr/ModuleMethod;
        //   165: getstatic kawa/lib/misc.values : Lgnu/expr/ModuleMethod;
        //   168: invokestatic $PcStringCompare : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   171: areturn
        //   172: iconst_0
        //   173: istore_3
        //   174: goto -> 93
        //   177: iconst_0
        //   178: istore_3
        //   179: goto -> 125
        //   182: goto -> 125
        //   185: iload_3
        //   186: ifeq -> 193
        //   189: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
        //   192: areturn
        //   193: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
        //   196: areturn
        //   197: astore_1
        //   198: new gnu/mapping/WrongType
        //   201: dup
        //   202: aload_1
        //   203: ldc 'x'
        //   205: bipush #-2
        //   207: aload #5
        //   209: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
        //   212: athrow
        //   213: astore_1
        //   214: new gnu/mapping/WrongType
        //   217: dup
        //   218: aload_1
        //   219: ldc 'x'
        //   221: bipush #-2
        //   223: aload #5
        //   225: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
        //   228: athrow
        // Exception table:
        //   from	to	target	type
        //   33	38	197	java/lang/ClassCastException
        //   110	115	213	java/lang/ClassCastException
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
    }
  }
  
  public class frame34 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn76 = new ModuleMethod(this, 68, null, 0);
    
    final ModuleMethod lambda$Fn77 = new ModuleMethod(this, 69, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame33 staticLink;
    
    static Boolean lambda78(Object param1Object) {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 68) ? lambda76() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 69) ? lambda77(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda76() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls$Gr, this.staticLink.s2, this.rest);
    }
    
    Object lambda77(Object param1Object1, Object param1Object2) {
      // Byte code:
      //   0: iconst_1
      //   1: istore #4
      //   3: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
      //   6: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   9: aload_0
      //   10: getfield end1 : Ljava/lang/Object;
      //   13: aload_0
      //   14: getfield start1 : Ljava/lang/Object;
      //   17: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   20: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   23: aload_2
      //   24: aload_1
      //   25: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   28: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   31: astore #5
      //   33: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   36: astore #6
      //   38: aload #5
      //   40: aload #6
      //   42: if_acmpeq -> 65
      //   45: iconst_1
      //   46: istore_3
      //   47: iload_3
      //   48: iconst_1
      //   49: iadd
      //   50: iconst_1
      //   51: iand
      //   52: istore_3
      //   53: iload_3
      //   54: ifeq -> 74
      //   57: iload_3
      //   58: ifeq -> 70
      //   61: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
      //   64: areturn
      //   65: iconst_0
      //   66: istore_3
      //   67: goto -> 47
      //   70: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   73: areturn
      //   74: aload_0
      //   75: getfield staticLink : Lgnu/kawa/slib/srfi13$frame33;
      //   78: getfield s1 : Ljava/lang/Object;
      //   81: aload_0
      //   82: getfield staticLink : Lgnu/kawa/slib/srfi13$frame33;
      //   85: getfield s2 : Ljava/lang/Object;
      //   88: if_acmpne -> 172
      //   91: iconst_1
      //   92: istore_3
      //   93: iload_3
      //   94: ifeq -> 182
      //   97: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
      //   100: aload_0
      //   101: getfield start1 : Ljava/lang/Object;
      //   104: aload_1
      //   105: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   108: astore #5
      //   110: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   113: astore #6
      //   115: aload #5
      //   117: aload #6
      //   119: if_acmpeq -> 177
      //   122: iload #4
      //   124: istore_3
      //   125: iload_3
      //   126: iconst_1
      //   127: iadd
      //   128: iconst_1
      //   129: iand
      //   130: istore_3
      //   131: iload_3
      //   132: ifeq -> 185
      //   135: aload_0
      //   136: getfield staticLink : Lgnu/kawa/slib/srfi13$frame33;
      //   139: getfield s1 : Ljava/lang/Object;
      //   142: aload_0
      //   143: getfield start1 : Ljava/lang/Object;
      //   146: aload_0
      //   147: getfield end1 : Ljava/lang/Object;
      //   150: aload_0
      //   151: getfield staticLink : Lgnu/kawa/slib/srfi13$frame33;
      //   154: getfield s2 : Ljava/lang/Object;
      //   157: aload_1
      //   158: aload_2
      //   159: getstatic kawa/lib/misc.values : Lgnu/expr/ModuleMethod;
      //   162: getstatic gnu/kawa/slib/srfi13.lambda$Fn78 : Lgnu/expr/ModuleMethod;
      //   165: getstatic kawa/lib/misc.values : Lgnu/expr/ModuleMethod;
      //   168: invokestatic $PcStringCompare : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   171: areturn
      //   172: iconst_0
      //   173: istore_3
      //   174: goto -> 93
      //   177: iconst_0
      //   178: istore_3
      //   179: goto -> 125
      //   182: goto -> 125
      //   185: iload_3
      //   186: ifeq -> 193
      //   189: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
      //   192: areturn
      //   193: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   196: areturn
      //   197: astore_1
      //   198: new gnu/mapping/WrongType
      //   201: dup
      //   202: aload_1
      //   203: ldc 'x'
      //   205: bipush #-2
      //   207: aload #5
      //   209: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   212: athrow
      //   213: astore_1
      //   214: new gnu/mapping/WrongType
      //   217: dup
      //   218: aload_1
      //   219: ldc 'x'
      //   221: bipush #-2
      //   223: aload #5
      //   225: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   228: athrow
      // Exception table:
      //   from	to	target	type
      //   33	38	197	java/lang/ClassCastException
      //   110	115	213	java/lang/ClassCastException
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
  }
  
  public class frame35 extends ModuleBody {
    final ModuleMethod lambda$Fn79 = new ModuleMethod(this, 74, null, 0);
    
    final ModuleMethod lambda$Fn80 = new ModuleMethod(this, 75, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 74) ? lambda79() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 75) ? lambda80(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda79() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Ls, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda80(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame36 frame36 = new frame36();
      frame36.staticLink = this;
      frame36.rest = param1Object1;
      frame36.start1 = param1Object2;
      frame36.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame36.lambda$Fn81, (Procedure)frame36.lambda$Fn82);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 74) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 75) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame36 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn81 = new ModuleMethod(this, 72, null, 0);
      
      final ModuleMethod lambda$Fn82 = new ModuleMethod(this, 73, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame35 staticLink;
      
      static Boolean lambda83(Object param2Object) {
        return Boolean.FALSE;
      }
      
      static Boolean lambda84(Object param2Object) {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 72) ? lambda81() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 73) ? lambda82(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda81() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls, this.staticLink.s2, this.rest);
      }
      
      Object lambda82(Object param2Object1, Object param2Object2) {
        boolean bool;
        if (this.staticLink.s1 == this.staticLink.s2) {
          bool = true;
        } else {
          bool = false;
        } 
        return (bool ? (Scheme.numEqu.apply2(this.start1, param2Object1) != Boolean.FALSE) : bool) ? Scheme.numLss.apply2(this.end1, param2Object2) : srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2, misc.values, srfi13.lambda$Fn83, srfi13.lambda$Fn84);
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
    }
  }
  
  public class frame36 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn81 = new ModuleMethod(this, 72, null, 0);
    
    final ModuleMethod lambda$Fn82 = new ModuleMethod(this, 73, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame35 staticLink;
    
    static Boolean lambda83(Object param1Object) {
      return Boolean.FALSE;
    }
    
    static Boolean lambda84(Object param1Object) {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 72) ? lambda81() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 73) ? lambda82(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda81() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls, this.staticLink.s2, this.rest);
    }
    
    Object lambda82(Object param1Object1, Object param1Object2) {
      boolean bool;
      if (this.staticLink.s1 == this.staticLink.s2) {
        bool = true;
      } else {
        bool = false;
      } 
      return (bool ? (Scheme.numEqu.apply2(this.start1, param1Object1) != Boolean.FALSE) : bool) ? Scheme.numLss.apply2(this.end1, param1Object2) : srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2, misc.values, srfi13.lambda$Fn83, srfi13.lambda$Fn84);
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
  }
  
  public class frame37 extends ModuleBody {
    final ModuleMethod lambda$Fn85 = new ModuleMethod(this, 78, null, 0);
    
    final ModuleMethod lambda$Fn86 = new ModuleMethod(this, 79, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 78) ? lambda85() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 79) ? lambda86(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda85() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Gr, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda86(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame38 frame38 = new frame38();
      frame38.staticLink = this;
      frame38.rest = param1Object1;
      frame38.start1 = param1Object2;
      frame38.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame38.lambda$Fn87, (Procedure)frame38.lambda$Fn88);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 78) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 79) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame38 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn87 = new ModuleMethod(this, 76, null, 0);
      
      final ModuleMethod lambda$Fn88 = new ModuleMethod(this, 77, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame37 staticLink;
      
      static Boolean lambda89(Object param2Object) {
        return Boolean.FALSE;
      }
      
      static Boolean lambda90(Object param2Object) {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 76) ? lambda87() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 77) ? lambda88(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda87() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Gr, this.staticLink.s2, this.rest);
      }
      
      Object lambda88(Object param2Object1, Object param2Object2) {
        boolean bool;
        if (this.staticLink.s1 == this.staticLink.s2) {
          bool = true;
        } else {
          bool = false;
        } 
        return (bool ? (Scheme.numEqu.apply2(this.start1, param2Object1) != Boolean.FALSE) : bool) ? Scheme.numGrt.apply2(this.end1, param2Object2) : srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2, srfi13.lambda$Fn89, srfi13.lambda$Fn90, misc.values);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 76) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 77) {
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
  
  public class frame38 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn87 = new ModuleMethod(this, 76, null, 0);
    
    final ModuleMethod lambda$Fn88 = new ModuleMethod(this, 77, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame37 staticLink;
    
    static Boolean lambda89(Object param1Object) {
      return Boolean.FALSE;
    }
    
    static Boolean lambda90(Object param1Object) {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 76) ? lambda87() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 77) ? lambda88(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda87() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Gr, this.staticLink.s2, this.rest);
    }
    
    Object lambda88(Object param1Object1, Object param1Object2) {
      boolean bool;
      if (this.staticLink.s1 == this.staticLink.s2) {
        bool = true;
      } else {
        bool = false;
      } 
      return (bool ? (Scheme.numEqu.apply2(this.start1, param1Object1) != Boolean.FALSE) : bool) ? Scheme.numGrt.apply2(this.end1, param1Object2) : srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2, srfi13.lambda$Fn89, srfi13.lambda$Fn90, misc.values);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 76) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 77) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame39 extends ModuleBody {
    final ModuleMethod lambda$Fn91 = new ModuleMethod(this, 82, null, 0);
    
    final ModuleMethod lambda$Fn92 = new ModuleMethod(this, 83, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 82) ? lambda91() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 83) ? lambda92(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda91() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Ls$Eq, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda92(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame40 frame40 = new frame40();
      frame40.staticLink = this;
      frame40.rest = param1Object1;
      frame40.start1 = param1Object2;
      frame40.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame40.lambda$Fn93, (Procedure)frame40.lambda$Fn94);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 82) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 83) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame40 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn93 = new ModuleMethod(this, 80, null, 0);
      
      final ModuleMethod lambda$Fn94 = new ModuleMethod(this, 81, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame39 staticLink;
      
      static Boolean lambda95(Object param2Object) {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 80) ? lambda93() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 81) ? lambda94(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda93() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls$Eq, this.staticLink.s2, this.rest);
      }
      
      Object lambda94(Object param2Object1, Object param2Object2) {
        boolean bool;
        if (this.staticLink.s1 == this.staticLink.s2) {
          bool = true;
        } else {
          bool = false;
        } 
        return (bool ? (Scheme.numEqu.apply2(this.start1, param2Object1) != Boolean.FALSE) : bool) ? Scheme.numLEq.apply2(this.end1, param2Object2) : srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2, misc.values, misc.values, srfi13.lambda$Fn95);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 80) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 81) {
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
  
  public class frame4 extends ModuleBody {
    final ModuleMethod lambda$Fn11 = new ModuleMethod(this, 10, null, 0);
    
    final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 11, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object proc;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 10) ? lambda11() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 11) ? lambda12(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda11() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnmap$Ex, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda12(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringMap$Ex(this.proc, this.s, param1Object1, param1Object2);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 10) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 11) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame40 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn93 = new ModuleMethod(this, 80, null, 0);
    
    final ModuleMethod lambda$Fn94 = new ModuleMethod(this, 81, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame39 staticLink;
    
    static Boolean lambda95(Object param1Object) {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 80) ? lambda93() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 81) ? lambda94(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda93() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls$Eq, this.staticLink.s2, this.rest);
    }
    
    Object lambda94(Object param1Object1, Object param1Object2) {
      boolean bool;
      if (this.staticLink.s1 == this.staticLink.s2) {
        bool = true;
      } else {
        bool = false;
      } 
      return (bool ? (Scheme.numEqu.apply2(this.start1, param1Object1) != Boolean.FALSE) : bool) ? Scheme.numLEq.apply2(this.end1, param1Object2) : srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2, misc.values, misc.values, srfi13.lambda$Fn95);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 80) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 81) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame41 extends ModuleBody {
    final ModuleMethod lambda$Fn96 = new ModuleMethod(this, 86, null, 0);
    
    final ModuleMethod lambda$Fn97 = new ModuleMethod(this, 87, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 86) ? lambda96() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 87) ? lambda97(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda96() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Gr$Eq, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda97(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame42 frame42 = new frame42();
      frame42.staticLink = this;
      frame42.rest = param1Object1;
      frame42.start1 = param1Object2;
      frame42.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame42.lambda$Fn98, (Procedure)frame42.lambda$Fn99);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 86) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 87) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame42 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn98 = new ModuleMethod(this, 84, null, 0);
      
      final ModuleMethod lambda$Fn99 = new ModuleMethod(this, 85, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame41 staticLink;
      
      static Boolean lambda100(Object param2Object) {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 84) ? lambda98() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 85) ? lambda99(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda98() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Gr$Eq, this.staticLink.s2, this.rest);
      }
      
      Object lambda99(Object param2Object1, Object param2Object2) {
        boolean bool;
        if (this.staticLink.s1 == this.staticLink.s2) {
          bool = true;
        } else {
          bool = false;
        } 
        return (bool ? (Scheme.numEqu.apply2(this.start1, param2Object1) != Boolean.FALSE) : bool) ? Scheme.numGEq.apply2(this.end1, param2Object2) : srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2, srfi13.lambda$Fn100, misc.values, misc.values);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 84) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 85) {
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
  
  public class frame42 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn98 = new ModuleMethod(this, 84, null, 0);
    
    final ModuleMethod lambda$Fn99 = new ModuleMethod(this, 85, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame41 staticLink;
    
    static Boolean lambda100(Object param1Object) {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 84) ? lambda98() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 85) ? lambda99(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda98() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Gr$Eq, this.staticLink.s2, this.rest);
    }
    
    Object lambda99(Object param1Object1, Object param1Object2) {
      boolean bool;
      if (this.staticLink.s1 == this.staticLink.s2) {
        bool = true;
      } else {
        bool = false;
      } 
      return (bool ? (Scheme.numEqu.apply2(this.start1, param1Object1) != Boolean.FALSE) : bool) ? Scheme.numGEq.apply2(this.end1, param1Object2) : srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2, srfi13.lambda$Fn100, misc.values, misc.values);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 84) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 85) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame43 extends ModuleBody {
    final ModuleMethod lambda$Fn101 = new ModuleMethod(this, 90, null, 0);
    
    final ModuleMethod lambda$Fn102 = new ModuleMethod(this, 91, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 90) ? lambda101() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 91) ? lambda102(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda101() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Eq, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda102(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame44 frame44 = new frame44();
      frame44.staticLink = this;
      frame44.rest = param1Object1;
      frame44.start1 = param1Object2;
      frame44.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame44.lambda$Fn103, (Procedure)frame44.lambda$Fn104);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 90) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 91) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame44 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn103 = new ModuleMethod(this, 88, null, 0);
      
      final ModuleMethod lambda$Fn104 = new ModuleMethod(this, 89, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame43 staticLink;
      
      static Boolean lambda105(Object param2Object) {
        return Boolean.FALSE;
      }
      
      static Boolean lambda106(Object param2Object) {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 88) ? lambda103() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 89) ? lambda104(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda103() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Eq, this.staticLink.s2, this.rest);
      }
      
      Object lambda104(Object param2Object1, Object param2Object2) {
        Object object = Scheme.numEqu.apply2(AddOp.$Mn.apply2(this.end1, this.start1), AddOp.$Mn.apply2(param2Object2, param2Object1));
        try {
          boolean bool2;
          boolean bool1 = ((Boolean)object).booleanValue();
          if (bool1) {
            if (this.staticLink.s1 == this.staticLink.s2) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            bool2 = bool1;
            if (bool1) {
              object = Scheme.numEqu.apply2(this.start1, param2Object1);
              try {
                bool2 = ((Boolean)object).booleanValue();
                return bool2 ? (bool2 ? Boolean.TRUE : Boolean.FALSE) : srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2, srfi13.lambda$Fn105, misc.values, srfi13.lambda$Fn106);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "x", -2, object);
              } 
            } 
          } else {
            return bool1 ? Boolean.TRUE : Boolean.FALSE;
          } 
          return bool2 ? (bool2 ? Boolean.TRUE : Boolean.FALSE) : srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, classCastException, param2Object2, srfi13.lambda$Fn105, misc.values, srfi13.lambda$Fn106);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "x", -2, object);
        } 
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 88) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 89) {
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
  
  public class frame44 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn103 = new ModuleMethod(this, 88, null, 0);
    
    final ModuleMethod lambda$Fn104 = new ModuleMethod(this, 89, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame43 staticLink;
    
    static Boolean lambda105(Object param1Object) {
      return Boolean.FALSE;
    }
    
    static Boolean lambda106(Object param1Object) {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 88) ? lambda103() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 89) ? lambda104(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda103() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Eq, this.staticLink.s2, this.rest);
    }
    
    Object lambda104(Object param1Object1, Object param1Object2) {
      Object object = Scheme.numEqu.apply2(AddOp.$Mn.apply2(this.end1, this.start1), AddOp.$Mn.apply2(param1Object2, param1Object1));
      try {
        boolean bool2;
        boolean bool1 = ((Boolean)object).booleanValue();
        if (bool1) {
          if (this.staticLink.s1 == this.staticLink.s2) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          bool2 = bool1;
          if (bool1) {
            object = Scheme.numEqu.apply2(this.start1, param1Object1);
            try {
              bool2 = ((Boolean)object).booleanValue();
              return bool2 ? (bool2 ? Boolean.TRUE : Boolean.FALSE) : srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2, srfi13.lambda$Fn105, misc.values, srfi13.lambda$Fn106);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "x", -2, object);
            } 
          } 
        } else {
          return bool1 ? Boolean.TRUE : Boolean.FALSE;
        } 
        return bool2 ? (bool2 ? Boolean.TRUE : Boolean.FALSE) : srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, classCastException, param1Object2, srfi13.lambda$Fn105, misc.values, srfi13.lambda$Fn106);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "x", -2, object);
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 88) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 89) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame45 extends ModuleBody {
    final ModuleMethod lambda$Fn107 = new ModuleMethod(this, 94, null, 0);
    
    final ModuleMethod lambda$Fn108 = new ModuleMethod(this, 95, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 94) ? lambda107() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 95) ? lambda108(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda107() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Ls$Gr, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda108(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame46 frame46 = new frame46();
      frame46.staticLink = this;
      frame46.rest = param1Object1;
      frame46.start1 = param1Object2;
      frame46.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame46.lambda$Fn109, (Procedure)frame46.lambda$Fn110);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 94) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 95) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame46 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn109 = new ModuleMethod(this, 92, null, 0);
      
      final ModuleMethod lambda$Fn110 = new ModuleMethod(this, 93, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame45 staticLink;
      
      static Boolean lambda111(Object param2Object) {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 92) ? lambda109() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 93) ? lambda110(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda109() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls$Gr, this.staticLink.s2, this.rest);
      }
      
      Object lambda110(Object param2Object1, Object param2Object2) {
        // Byte code:
        //   0: iconst_1
        //   1: istore #4
        //   3: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
        //   6: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
        //   9: aload_0
        //   10: getfield end1 : Ljava/lang/Object;
        //   13: aload_0
        //   14: getfield start1 : Ljava/lang/Object;
        //   17: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   20: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
        //   23: aload_2
        //   24: aload_1
        //   25: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   28: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   31: astore #5
        //   33: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
        //   36: astore #6
        //   38: aload #5
        //   40: aload #6
        //   42: if_acmpeq -> 65
        //   45: iconst_1
        //   46: istore_3
        //   47: iload_3
        //   48: iconst_1
        //   49: iadd
        //   50: iconst_1
        //   51: iand
        //   52: istore_3
        //   53: iload_3
        //   54: ifeq -> 74
        //   57: iload_3
        //   58: ifeq -> 70
        //   61: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
        //   64: areturn
        //   65: iconst_0
        //   66: istore_3
        //   67: goto -> 47
        //   70: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
        //   73: areturn
        //   74: aload_0
        //   75: getfield staticLink : Lgnu/kawa/slib/srfi13$frame45;
        //   78: getfield s1 : Ljava/lang/Object;
        //   81: aload_0
        //   82: getfield staticLink : Lgnu/kawa/slib/srfi13$frame45;
        //   85: getfield s2 : Ljava/lang/Object;
        //   88: if_acmpne -> 172
        //   91: iconst_1
        //   92: istore_3
        //   93: iload_3
        //   94: ifeq -> 182
        //   97: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
        //   100: aload_0
        //   101: getfield start1 : Ljava/lang/Object;
        //   104: aload_1
        //   105: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   108: astore #5
        //   110: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
        //   113: astore #6
        //   115: aload #5
        //   117: aload #6
        //   119: if_acmpeq -> 177
        //   122: iload #4
        //   124: istore_3
        //   125: iload_3
        //   126: iconst_1
        //   127: iadd
        //   128: iconst_1
        //   129: iand
        //   130: istore_3
        //   131: iload_3
        //   132: ifeq -> 185
        //   135: aload_0
        //   136: getfield staticLink : Lgnu/kawa/slib/srfi13$frame45;
        //   139: getfield s1 : Ljava/lang/Object;
        //   142: aload_0
        //   143: getfield start1 : Ljava/lang/Object;
        //   146: aload_0
        //   147: getfield end1 : Ljava/lang/Object;
        //   150: aload_0
        //   151: getfield staticLink : Lgnu/kawa/slib/srfi13$frame45;
        //   154: getfield s2 : Ljava/lang/Object;
        //   157: aload_1
        //   158: aload_2
        //   159: getstatic kawa/lib/misc.values : Lgnu/expr/ModuleMethod;
        //   162: getstatic gnu/kawa/slib/srfi13.lambda$Fn111 : Lgnu/expr/ModuleMethod;
        //   165: getstatic kawa/lib/misc.values : Lgnu/expr/ModuleMethod;
        //   168: invokestatic $PcStringCompareCi : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   171: areturn
        //   172: iconst_0
        //   173: istore_3
        //   174: goto -> 93
        //   177: iconst_0
        //   178: istore_3
        //   179: goto -> 125
        //   182: goto -> 125
        //   185: iload_3
        //   186: ifeq -> 193
        //   189: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
        //   192: areturn
        //   193: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
        //   196: areturn
        //   197: astore_1
        //   198: new gnu/mapping/WrongType
        //   201: dup
        //   202: aload_1
        //   203: ldc 'x'
        //   205: bipush #-2
        //   207: aload #5
        //   209: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
        //   212: athrow
        //   213: astore_1
        //   214: new gnu/mapping/WrongType
        //   217: dup
        //   218: aload_1
        //   219: ldc 'x'
        //   221: bipush #-2
        //   223: aload #5
        //   225: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
        //   228: athrow
        // Exception table:
        //   from	to	target	type
        //   33	38	197	java/lang/ClassCastException
        //   110	115	213	java/lang/ClassCastException
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 92) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 93) {
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
    Object end1;
    
    final ModuleMethod lambda$Fn109 = new ModuleMethod(this, 92, null, 0);
    
    final ModuleMethod lambda$Fn110 = new ModuleMethod(this, 93, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame45 staticLink;
    
    static Boolean lambda111(Object param1Object) {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 92) ? lambda109() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 93) ? lambda110(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda109() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls$Gr, this.staticLink.s2, this.rest);
    }
    
    Object lambda110(Object param1Object1, Object param1Object2) {
      // Byte code:
      //   0: iconst_1
      //   1: istore #4
      //   3: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
      //   6: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   9: aload_0
      //   10: getfield end1 : Ljava/lang/Object;
      //   13: aload_0
      //   14: getfield start1 : Ljava/lang/Object;
      //   17: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   20: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   23: aload_2
      //   24: aload_1
      //   25: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   28: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   31: astore #5
      //   33: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   36: astore #6
      //   38: aload #5
      //   40: aload #6
      //   42: if_acmpeq -> 65
      //   45: iconst_1
      //   46: istore_3
      //   47: iload_3
      //   48: iconst_1
      //   49: iadd
      //   50: iconst_1
      //   51: iand
      //   52: istore_3
      //   53: iload_3
      //   54: ifeq -> 74
      //   57: iload_3
      //   58: ifeq -> 70
      //   61: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
      //   64: areturn
      //   65: iconst_0
      //   66: istore_3
      //   67: goto -> 47
      //   70: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   73: areturn
      //   74: aload_0
      //   75: getfield staticLink : Lgnu/kawa/slib/srfi13$frame45;
      //   78: getfield s1 : Ljava/lang/Object;
      //   81: aload_0
      //   82: getfield staticLink : Lgnu/kawa/slib/srfi13$frame45;
      //   85: getfield s2 : Ljava/lang/Object;
      //   88: if_acmpne -> 172
      //   91: iconst_1
      //   92: istore_3
      //   93: iload_3
      //   94: ifeq -> 182
      //   97: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
      //   100: aload_0
      //   101: getfield start1 : Ljava/lang/Object;
      //   104: aload_1
      //   105: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   108: astore #5
      //   110: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   113: astore #6
      //   115: aload #5
      //   117: aload #6
      //   119: if_acmpeq -> 177
      //   122: iload #4
      //   124: istore_3
      //   125: iload_3
      //   126: iconst_1
      //   127: iadd
      //   128: iconst_1
      //   129: iand
      //   130: istore_3
      //   131: iload_3
      //   132: ifeq -> 185
      //   135: aload_0
      //   136: getfield staticLink : Lgnu/kawa/slib/srfi13$frame45;
      //   139: getfield s1 : Ljava/lang/Object;
      //   142: aload_0
      //   143: getfield start1 : Ljava/lang/Object;
      //   146: aload_0
      //   147: getfield end1 : Ljava/lang/Object;
      //   150: aload_0
      //   151: getfield staticLink : Lgnu/kawa/slib/srfi13$frame45;
      //   154: getfield s2 : Ljava/lang/Object;
      //   157: aload_1
      //   158: aload_2
      //   159: getstatic kawa/lib/misc.values : Lgnu/expr/ModuleMethod;
      //   162: getstatic gnu/kawa/slib/srfi13.lambda$Fn111 : Lgnu/expr/ModuleMethod;
      //   165: getstatic kawa/lib/misc.values : Lgnu/expr/ModuleMethod;
      //   168: invokestatic $PcStringCompareCi : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   171: areturn
      //   172: iconst_0
      //   173: istore_3
      //   174: goto -> 93
      //   177: iconst_0
      //   178: istore_3
      //   179: goto -> 125
      //   182: goto -> 125
      //   185: iload_3
      //   186: ifeq -> 193
      //   189: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
      //   192: areturn
      //   193: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   196: areturn
      //   197: astore_1
      //   198: new gnu/mapping/WrongType
      //   201: dup
      //   202: aload_1
      //   203: ldc 'x'
      //   205: bipush #-2
      //   207: aload #5
      //   209: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   212: athrow
      //   213: astore_1
      //   214: new gnu/mapping/WrongType
      //   217: dup
      //   218: aload_1
      //   219: ldc 'x'
      //   221: bipush #-2
      //   223: aload #5
      //   225: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   228: athrow
      // Exception table:
      //   from	to	target	type
      //   33	38	197	java/lang/ClassCastException
      //   110	115	213	java/lang/ClassCastException
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 92) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 93) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame47 extends ModuleBody {
    final ModuleMethod lambda$Fn112 = new ModuleMethod(this, 98, null, 0);
    
    final ModuleMethod lambda$Fn113 = new ModuleMethod(this, 99, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 98) ? lambda112() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 99) ? lambda113(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda112() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Ls, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda113(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame48 frame48 = new frame48();
      frame48.staticLink = this;
      frame48.rest = param1Object1;
      frame48.start1 = param1Object2;
      frame48.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame48.lambda$Fn114, (Procedure)frame48.lambda$Fn115);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 98) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 99) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame48 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn114 = new ModuleMethod(this, 96, null, 0);
      
      final ModuleMethod lambda$Fn115 = new ModuleMethod(this, 97, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame47 staticLink;
      
      static Boolean lambda116(Object param2Object) {
        return Boolean.FALSE;
      }
      
      static Boolean lambda117(Object param2Object) {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 96) ? lambda114() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 97) ? lambda115(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda114() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls, this.staticLink.s2, this.rest);
      }
      
      Object lambda115(Object param2Object1, Object param2Object2) {
        boolean bool;
        if (this.staticLink.s1 == this.staticLink.s2) {
          bool = true;
        } else {
          bool = false;
        } 
        return (bool ? (Scheme.numEqu.apply2(this.start1, param2Object1) != Boolean.FALSE) : bool) ? Scheme.numLss.apply2(this.end1, param2Object2) : srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2, misc.values, srfi13.lambda$Fn116, srfi13.lambda$Fn117);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 96) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 97) {
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
  
  public class frame48 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn114 = new ModuleMethod(this, 96, null, 0);
    
    final ModuleMethod lambda$Fn115 = new ModuleMethod(this, 97, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame47 staticLink;
    
    static Boolean lambda116(Object param1Object) {
      return Boolean.FALSE;
    }
    
    static Boolean lambda117(Object param1Object) {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 96) ? lambda114() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 97) ? lambda115(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda114() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls, this.staticLink.s2, this.rest);
    }
    
    Object lambda115(Object param1Object1, Object param1Object2) {
      boolean bool;
      if (this.staticLink.s1 == this.staticLink.s2) {
        bool = true;
      } else {
        bool = false;
      } 
      return (bool ? (Scheme.numEqu.apply2(this.start1, param1Object1) != Boolean.FALSE) : bool) ? Scheme.numLss.apply2(this.end1, param1Object2) : srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2, misc.values, srfi13.lambda$Fn116, srfi13.lambda$Fn117);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 96) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 97) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame49 extends ModuleBody {
    final ModuleMethod lambda$Fn118 = new ModuleMethod(this, 102, null, 0);
    
    final ModuleMethod lambda$Fn119 = new ModuleMethod(this, 103, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 102) ? lambda118() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 103) ? lambda119(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda118() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Gr, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda119(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame50 frame50 = new frame50();
      frame50.staticLink = this;
      frame50.rest = param1Object1;
      frame50.start1 = param1Object2;
      frame50.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame50.lambda$Fn120, (Procedure)frame50.lambda$Fn121);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 102) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 103) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame50 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn120 = new ModuleMethod(this, 100, null, 0);
      
      final ModuleMethod lambda$Fn121 = new ModuleMethod(this, 101, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame49 staticLink;
      
      static Boolean lambda122(Object param2Object) {
        return Boolean.FALSE;
      }
      
      static Boolean lambda123(Object param2Object) {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 100) ? lambda120() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 101) ? lambda121(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda120() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Gr, this.staticLink.s2, this.rest);
      }
      
      Object lambda121(Object param2Object1, Object param2Object2) {
        boolean bool;
        if (this.staticLink.s1 == this.staticLink.s2) {
          bool = true;
        } else {
          bool = false;
        } 
        return (bool ? (Scheme.numEqu.apply2(this.start1, param2Object1) != Boolean.FALSE) : bool) ? Scheme.numGrt.apply2(this.end1, param2Object2) : srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2, srfi13.lambda$Fn122, srfi13.lambda$Fn123, misc.values);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 100) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 101) {
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
  
  public class frame5 extends ModuleBody {
    Object knil;
    
    Object kons;
    
    final ModuleMethod lambda$Fn13 = new ModuleMethod(this, 12, null, 0);
    
    final ModuleMethod lambda$Fn14 = new ModuleMethod(this, 13, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 12) ? lambda13() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 13) ? lambda14(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda13() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfold, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda14(Object param1Object1, Object param1Object2) {
      Object object = this.knil;
      while (Scheme.numLss.apply2(param1Object1, param1Object2) != Boolean.FALSE) {
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Object object2 = this.kons;
        Object object1 = this.s;
        try {
          CharSequence charSequence = (CharSequence)object1;
          try {
            int i = ((Number)param1Object1).intValue();
            object = applyToArgs.apply3(object2, Char.make(strings.stringRef(charSequence, i)), object);
            param1Object1 = AddOp.$Pl.apply2(param1Object1, srfi13.Lit1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, param1Object1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 1, object1);
        } 
      } 
      return object;
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
  
  public class frame50 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn120 = new ModuleMethod(this, 100, null, 0);
    
    final ModuleMethod lambda$Fn121 = new ModuleMethod(this, 101, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame49 staticLink;
    
    static Boolean lambda122(Object param1Object) {
      return Boolean.FALSE;
    }
    
    static Boolean lambda123(Object param1Object) {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 100) ? lambda120() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 101) ? lambda121(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda120() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Gr, this.staticLink.s2, this.rest);
    }
    
    Object lambda121(Object param1Object1, Object param1Object2) {
      boolean bool;
      if (this.staticLink.s1 == this.staticLink.s2) {
        bool = true;
      } else {
        bool = false;
      } 
      return (bool ? (Scheme.numEqu.apply2(this.start1, param1Object1) != Boolean.FALSE) : bool) ? Scheme.numGrt.apply2(this.end1, param1Object2) : srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2, srfi13.lambda$Fn122, srfi13.lambda$Fn123, misc.values);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 100) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 101) {
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
    final ModuleMethod lambda$Fn124 = new ModuleMethod(this, 106, null, 0);
    
    final ModuleMethod lambda$Fn125 = new ModuleMethod(this, 107, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 106) ? lambda124() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 107) ? lambda125(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda124() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Ls$Eq, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda125(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame52 frame52 = new frame52();
      frame52.staticLink = this;
      frame52.rest = param1Object1;
      frame52.start1 = param1Object2;
      frame52.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame52.lambda$Fn126, (Procedure)frame52.lambda$Fn127);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 106) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 107) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame52 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn126 = new ModuleMethod(this, 104, null, 0);
      
      final ModuleMethod lambda$Fn127 = new ModuleMethod(this, 105, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame51 staticLink;
      
      static Boolean lambda128(Object param2Object) {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 104) ? lambda126() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 105) ? lambda127(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda126() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls$Eq, this.staticLink.s2, this.rest);
      }
      
      Object lambda127(Object param2Object1, Object param2Object2) {
        boolean bool;
        if (this.staticLink.s1 == this.staticLink.s2) {
          bool = true;
        } else {
          bool = false;
        } 
        return (bool ? (Scheme.numEqu.apply2(this.start1, param2Object1) != Boolean.FALSE) : bool) ? Scheme.numLEq.apply2(this.end1, param2Object2) : srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2, misc.values, misc.values, srfi13.lambda$Fn128);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 104) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 105) {
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
  
  public class frame52 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn126 = new ModuleMethod(this, 104, null, 0);
    
    final ModuleMethod lambda$Fn127 = new ModuleMethod(this, 105, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame51 staticLink;
    
    static Boolean lambda128(Object param1Object) {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 104) ? lambda126() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 105) ? lambda127(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda126() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls$Eq, this.staticLink.s2, this.rest);
    }
    
    Object lambda127(Object param1Object1, Object param1Object2) {
      boolean bool;
      if (this.staticLink.s1 == this.staticLink.s2) {
        bool = true;
      } else {
        bool = false;
      } 
      return (bool ? (Scheme.numEqu.apply2(this.start1, param1Object1) != Boolean.FALSE) : bool) ? Scheme.numLEq.apply2(this.end1, param1Object2) : srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2, misc.values, misc.values, srfi13.lambda$Fn128);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 104) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 105) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame53 extends ModuleBody {
    final ModuleMethod lambda$Fn129 = new ModuleMethod(this, 110, null, 0);
    
    final ModuleMethod lambda$Fn130 = new ModuleMethod(this, 111, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object s1;
    
    Object s2;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 110) ? lambda129() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 111) ? lambda130(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda129() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Gr$Eq, this.s1, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda130(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame54 frame54 = new frame54();
      frame54.staticLink = this;
      frame54.rest = param1Object1;
      frame54.start1 = param1Object2;
      frame54.end1 = param1Object3;
      return call_with_values.callWithValues((Procedure)frame54.lambda$Fn131, (Procedure)frame54.lambda$Fn132);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 110) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 111) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame54 extends ModuleBody {
      Object end1;
      
      final ModuleMethod lambda$Fn131 = new ModuleMethod(this, 108, null, 0);
      
      final ModuleMethod lambda$Fn132 = new ModuleMethod(this, 109, null, 8194);
      
      Object rest;
      
      Object start1;
      
      srfi13.frame53 staticLink;
      
      static Boolean lambda133(Object param2Object) {
        return Boolean.FALSE;
      }
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 108) ? lambda131() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 109) ? lambda132(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda131() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Gr$Eq, this.staticLink.s2, this.rest);
      }
      
      Object lambda132(Object param2Object1, Object param2Object2) {
        boolean bool;
        if (this.staticLink.s1 == this.staticLink.s2) {
          bool = true;
        } else {
          bool = false;
        } 
        return (bool ? (Scheme.numEqu.apply2(this.start1, param2Object1) != Boolean.FALSE) : bool) ? Scheme.numGEq.apply2(this.end1, param2Object2) : srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param2Object1, param2Object2, srfi13.lambda$Fn133, misc.values, misc.values);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 108) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 109) {
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
  
  public class frame54 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn131 = new ModuleMethod(this, 108, null, 0);
    
    final ModuleMethod lambda$Fn132 = new ModuleMethod(this, 109, null, 8194);
    
    Object rest;
    
    Object start1;
    
    srfi13.frame53 staticLink;
    
    static Boolean lambda133(Object param1Object) {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 108) ? lambda131() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 109) ? lambda132(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda131() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Gr$Eq, this.staticLink.s2, this.rest);
    }
    
    Object lambda132(Object param1Object1, Object param1Object2) {
      boolean bool;
      if (this.staticLink.s1 == this.staticLink.s2) {
        bool = true;
      } else {
        bool = false;
      } 
      return (bool ? (Scheme.numEqu.apply2(this.start1, param1Object1) != Boolean.FALSE) : bool) ? Scheme.numGEq.apply2(this.end1, param1Object2) : srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, param1Object1, param1Object2, srfi13.lambda$Fn133, misc.values, misc.values);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 108) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 109) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame55 extends ModuleBody {
    Object char$Mn$Grint;
  }
  
  public class frame56 extends ModuleBody {
    Object bound;
    
    final ModuleMethod lambda$Fn134 = new ModuleMethod(this, 112, null, 0);
    
    final ModuleMethod lambda$Fn135 = new ModuleMethod(this, 113, null, 8194);
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 112) ? lambda134() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 113) ? lambda135(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda134() {
      ModuleMethod moduleMethod = srfi13.string$Mnhash;
      Object object = this.s;
      Location location = srfi13.loc$rest;
      try {
        Object object1 = location.get();
        return srfi13.stringParseFinalStart$PlEnd(moduleMethod, object, object1);
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 912, 55);
        throw unboundLocationException;
      } 
    }
    
    Object lambda135(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringHash(this.s, characters.char$Mn$Grinteger, this.bound, param1Object1, param1Object2);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 112) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 113) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame57 extends ModuleBody {
    Object bound;
    
    final ModuleMethod lambda$Fn136 = new ModuleMethod(this, 114, null, 0);
    
    final ModuleMethod lambda$Fn137 = new ModuleMethod(this, 115, null, 8194);
    
    Object s;
    
    static int lambda138(Object param1Object) {
      try {
        Char char_ = (Char)param1Object;
        return characters.char$To$Integer(unicode.charDowncase(char_));
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "char-downcase", 1, param1Object);
      } 
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 114) ? lambda136() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 115) ? lambda137(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda136() {
      ModuleMethod moduleMethod = srfi13.string$Mnhash$Mnci;
      Object object = this.s;
      Location location = srfi13.loc$rest;
      try {
        Object object1 = location.get();
        return srfi13.stringParseFinalStart$PlEnd(moduleMethod, object, object1);
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 921, 58);
        throw unboundLocationException;
      } 
    }
    
    Object lambda137(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringHash(this.s, srfi13.lambda$Fn138, this.bound, param1Object1, param1Object2);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 114) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 115) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame58 extends ModuleBody {
    final ModuleMethod lambda$Fn139 = new ModuleMethod(this, 116, null, 0);
    
    final ModuleMethod lambda$Fn140 = new ModuleMethod(this, 117, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 116) ? lambda139() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 117) ? lambda140(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda139() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnupcase, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda140(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringMap(unicode.char$Mnupcase, this.s, param1Object1, param1Object2);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 116) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 117) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame59 extends ModuleBody {
    final ModuleMethod lambda$Fn141 = new ModuleMethod(this, 118, null, 0);
    
    final ModuleMethod lambda$Fn142 = new ModuleMethod(this, 119, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 118) ? lambda141() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 119) ? lambda142(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda141() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnupcase$Ex, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda142(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringMap$Ex(unicode.char$Mnupcase, this.s, param1Object1, param1Object2);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 118) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 119) {
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
    Object knil;
    
    Object kons;
    
    final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 14, null, 0);
    
    final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 15, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 14) ? lambda15() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 15) ? lambda16(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda15() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfold$Mnright, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda16(Object param1Object1, Object param1Object2) {
      Object object = this.knil;
      param1Object2 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
      while (Scheme.numGEq.apply2(param1Object2, param1Object1) != Boolean.FALSE) {
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Object object2 = this.kons;
        Object object1 = this.s;
        try {
          CharSequence charSequence = (CharSequence)object1;
          try {
            int i = ((Number)param1Object2).intValue();
            object = applyToArgs.apply3(object2, Char.make(strings.stringRef(charSequence, i)), object);
            param1Object2 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, param1Object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 1, object1);
        } 
      } 
      return object;
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 14) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 15) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame60 extends ModuleBody {
    final ModuleMethod lambda$Fn143 = new ModuleMethod(this, 120, null, 0);
    
    final ModuleMethod lambda$Fn144 = new ModuleMethod(this, 121, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 120) ? lambda143() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 121) ? lambda144(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda143() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mndowncase, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda144(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringMap(unicode.char$Mndowncase, this.s, param1Object1, param1Object2);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 120) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 121) {
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
    final ModuleMethod lambda$Fn145 = new ModuleMethod(this, 122, null, 0);
    
    final ModuleMethod lambda$Fn146 = new ModuleMethod(this, 123, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 122) ? lambda145() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 123) ? lambda146(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda145() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mndowncase$Ex, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda146(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringMap$Ex(unicode.char$Mndowncase, this.s, param1Object1, param1Object2);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 122) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 123) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame62 extends ModuleBody {
    final ModuleMethod lambda$Fn147 = new ModuleMethod(this, 124, null, 0);
    
    final ModuleMethod lambda$Fn148 = new ModuleMethod(this, 125, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 124) ? lambda147() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 125) ? lambda148(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda147() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntitlecase$Ex, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda148(Object param1Object1, Object param1Object2) {
      return srfi13.$PcStringTitlecase$Ex(this.s, param1Object1, param1Object2);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 124) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 125) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame63 extends ModuleBody {
    final ModuleMethod lambda$Fn149 = new ModuleMethod(this, 126, null, 0);
    
    final ModuleMethod lambda$Fn150 = new ModuleMethod(this, 127, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 126) ? lambda149() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 127) ? lambda150(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda149() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntitlecase$Ex, this.s, this.maybe$Mnstart$Plend);
    }
    
    CharSequence lambda150(Object param1Object1, Object param1Object2) {
      Object object = this.s;
      try {
        CharSequence charSequence = (CharSequence)object;
        try {
          int i = ((Number)param1Object1).intValue();
          try {
            int j = ((Number)param1Object2).intValue();
            object = strings.substring(charSequence, i, j);
            srfi13.$PcStringTitlecase$Ex(object, srfi13.Lit0, AddOp.$Mn.apply2(param1Object2, param1Object1));
            return (CharSequence)object;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "substring", 3, param1Object2);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "substring", 2, classCastException);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "substring", 1, object);
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 126) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 127) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame64 extends ModuleBody {
    final ModuleMethod lambda$Fn151;
    
    Object n;
    
    Object s;
    
    public frame64() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 128, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:996");
      this.lambda$Fn151 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 128) ? (lambda151(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda151(Object param1Object) {
      boolean bool2 = numbers.isInteger(this.n);
      boolean bool1 = bool2;
      if (bool2) {
        bool2 = numbers.isExact(this.n);
        bool1 = bool2;
        if (bool2) {
          NumberCompare numberCompare = Scheme.numLEq;
          IntNum intNum = srfi13.Lit0;
          Object object = this.n;
          param1Object = this.s;
          try {
            CharSequence charSequence = (CharSequence)param1Object;
            return ((Boolean)numberCompare.apply3(intNum, object, Integer.valueOf(strings.stringLength(charSequence)))).booleanValue();
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-length", 1, param1Object);
          } 
        } 
      } 
      return bool1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 128) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame65 extends ModuleBody {
    final ModuleMethod lambda$Fn152;
    
    int len;
    
    Object n;
    
    public frame65() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 129, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1004");
      this.lambda$Fn152 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 129) ? (lambda152(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda152(Object param1Object) {
      boolean bool2 = numbers.isInteger(this.n);
      boolean bool1 = bool2;
      if (bool2) {
        bool2 = numbers.isExact(this.n);
        bool1 = bool2;
        if (bool2)
          bool1 = ((Boolean)Scheme.numLEq.apply3(srfi13.Lit0, this.n, Integer.valueOf(this.len))).booleanValue(); 
      } 
      return bool1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 129) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame66 extends ModuleBody {
    final ModuleMethod lambda$Fn153;
    
    int len;
    
    Object n;
    
    public frame66() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 130, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1010");
      this.lambda$Fn153 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 130) ? (lambda153(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda153(Object param1Object) {
      boolean bool2 = numbers.isInteger(this.n);
      boolean bool1 = bool2;
      if (bool2) {
        bool2 = numbers.isExact(this.n);
        bool1 = bool2;
        if (bool2)
          bool1 = ((Boolean)Scheme.numLEq.apply3(srfi13.Lit0, this.n, Integer.valueOf(this.len))).booleanValue(); 
      } 
      return bool1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 130) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame67 extends ModuleBody {
    final ModuleMethod lambda$Fn154;
    
    int len;
    
    Object n;
    
    public frame67() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 131, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1016");
      this.lambda$Fn154 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 131) ? (lambda154(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda154(Object param1Object) {
      boolean bool2 = numbers.isInteger(this.n);
      boolean bool1 = bool2;
      if (bool2) {
        bool2 = numbers.isExact(this.n);
        bool1 = bool2;
        if (bool2)
          bool1 = ((Boolean)Scheme.numLEq.apply3(srfi13.Lit0, this.n, Integer.valueOf(this.len))).booleanValue(); 
      } 
      return bool1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 131) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame68 extends ModuleBody {
    final ModuleMethod lambda$Fn155 = new ModuleMethod(this, 132, null, 0);
    
    final ModuleMethod lambda$Fn156 = new ModuleMethod(this, 133, null, 8194);
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 132) ? lambda155() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 133) ? lambda156(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda155() {
      ModuleMethod moduleMethod = srfi13.string$Mntrim;
      Object object = this.s;
      Location location = srfi13.loc$rest;
      try {
        Object object1 = location.get();
        return srfi13.stringParseFinalStart$PlEnd(moduleMethod, object, object1);
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1023, 53);
        throw unboundLocationException;
      } 
    }
    
    Object lambda156(Object param1Object1, Object param1Object2) {
      Object object = this.s;
      Location location = srfi13.loc$criterion;
      try {
        Object object1 = location.get();
        param1Object1 = srfi13.stringSkip$V(object, object1, new Object[] { param1Object1, param1Object2 });
        if (param1Object1 != Boolean.FALSE) {
          object = this.s;
          try {
            object1 = object;
            try {
              int i = ((Number)param1Object1).intValue();
              try {
                int j = ((Number)param1Object2).intValue();
                return srfi13.$PcSubstring$SlShared((CharSequence)object1, i, j);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "%substring/shared", 2, param1Object2);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "%substring/shared", 1, classCastException);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%substring/shared", 0, object);
          } 
        } 
        return "";
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1024, 29);
        throw unboundLocationException;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 132) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 133) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame69 extends ModuleBody {
    final ModuleMethod lambda$Fn157 = new ModuleMethod(this, 134, null, 0);
    
    final ModuleMethod lambda$Fn158 = new ModuleMethod(this, 135, null, 8194);
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 134) ? lambda157() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 135) ? lambda158(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda157() {
      ModuleMethod moduleMethod = srfi13.string$Mntrim$Mnright;
      Object object = this.s;
      Location location = srfi13.loc$rest;
      try {
        Object object1 = location.get();
        return srfi13.stringParseFinalStart$PlEnd(moduleMethod, object, object1);
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1030, 59);
        throw unboundLocationException;
      } 
    }
    
    Object lambda158(Object param1Object1, Object param1Object2) {
      Object object = this.s;
      Location location = srfi13.loc$criterion;
      try {
        Object object1 = location.get();
        object = srfi13.stringSkipRight$V(object, object1, new Object[] { param1Object1, param1Object2 });
        if (object != Boolean.FALSE) {
          param1Object1 = this.s;
          try {
            param1Object2 = param1Object1;
            param1Object1 = AddOp.$Pl.apply2(srfi13.Lit1, object);
            try {
              int i = ((Number)param1Object1).intValue();
              return srfi13.$PcSubstring$SlShared((CharSequence)param1Object2, 0, i);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%substring/shared", 2, param1Object1);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%substring/shared", 0, param1Object1);
          } 
        } 
        return "";
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1031, 35);
        throw unboundLocationException;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 134) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 135) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame7 extends ModuleBody {
    final ModuleMethod lambda$Fn19 = new ModuleMethod(this, 16, null, 0);
    
    final ModuleMethod lambda$Fn20 = new ModuleMethod(this, 17, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object proc;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 16) ? lambda19() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 17) ? lambda20(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda19() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfor$Mneach, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda20(Object param1Object1, Object param1Object2) {
      while (true) {
        if (Scheme.numLss.apply2(param1Object1, param1Object2) != Boolean.FALSE) {
          ApplyToArgs applyToArgs = Scheme.applyToArgs;
          Object object2 = this.proc;
          Object object1 = this.s;
          try {
            CharSequence charSequence = (CharSequence)object1;
            try {
              int i = ((Number)param1Object1).intValue();
              applyToArgs.apply2(object2, Char.make(strings.stringRef(charSequence, i)));
              param1Object1 = AddOp.$Pl.apply2(param1Object1, srfi13.Lit1);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-ref", 2, param1Object1);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 1, object1);
          } 
        } 
        return Values.empty;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 16) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 17) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame70 extends ModuleBody {
    final ModuleMethod lambda$Fn159 = new ModuleMethod(this, 136, null, 0);
    
    final ModuleMethod lambda$Fn160 = new ModuleMethod(this, 137, null, 8194);
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 136) ? lambda159() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 137) ? lambda160(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda159() {
      ModuleMethod moduleMethod = srfi13.string$Mntrim$Mnboth;
      Object object = this.s;
      Location location = srfi13.loc$rest;
      try {
        Object object1 = location.get();
        return srfi13.stringParseFinalStart$PlEnd(moduleMethod, object, object1);
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1037, 58);
        throw unboundLocationException;
      } 
    }
    
    Object lambda160(Object param1Object1, Object param1Object2) {
      Object object = this.s;
      Location location = srfi13.loc$criterion;
      try {
        Object object1 = location.get();
        param1Object1 = srfi13.stringSkip$V(object, object1, new Object[] { param1Object1, param1Object2 });
        if (param1Object1 != Boolean.FALSE) {
          object1 = this.s;
          try {
            object = object1;
            try {
              int i = ((Number)param1Object1).intValue();
              object1 = AddOp.$Pl;
              IntNum intNum = srfi13.Lit1;
              Object object2 = this.s;
              Location location1 = srfi13.loc$criterion;
              try {
                Object object3 = location1.get();
                param1Object1 = object1.apply2(intNum, srfi13.stringSkipRight$V(object2, object3, new Object[] { param1Object1, param1Object2 }));
                try {
                  int j = ((Number)param1Object1).intValue();
                  return srfi13.$PcSubstring$SlShared((CharSequence)object, i, j);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "%substring/shared", 2, param1Object1);
                } 
              } catch (UnboundLocationException unboundLocationException) {
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1040, 58);
                throw unboundLocationException;
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%substring/shared", 1, unboundLocationException);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%substring/shared", 0, object1);
          } 
        } 
        return "";
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1038, 29);
        throw unboundLocationException;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 136) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 137) {
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
    final ModuleMethod lambda$Fn161 = new ModuleMethod(this, 138, null, 0);
    
    final ModuleMethod lambda$Fn162 = new ModuleMethod(this, 139, null, 8194);
    
    Object n;
    
    Object s;
    
    static boolean lambda163(Object param1Object) {
      boolean bool2 = numbers.isInteger(param1Object);
      boolean bool1 = bool2;
      if (bool2) {
        bool2 = numbers.isExact(param1Object);
        bool1 = bool2;
        if (bool2)
          bool1 = ((Boolean)Scheme.numLEq.apply2(srfi13.Lit0, param1Object)).booleanValue(); 
      } 
      return bool1;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 138) ? lambda161() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 139) ? lambda162(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda161() {
      ModuleMethod moduleMethod = srfi13.string$Mnpad$Mnright;
      Object object = this.s;
      Location location = srfi13.loc$rest;
      try {
        Object object1 = location.get();
        return srfi13.stringParseFinalStart$PlEnd(moduleMethod, object, object1);
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1046, 58);
        throw unboundLocationException;
      } 
    }
    
    Object lambda162(Object param1Object1, Object param1Object2) {
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$check$Mnarg;
      try {
        Object object2 = location.get();
        applyToArgs.apply4(object2, srfi13.lambda$Fn163, this.n, srfi13.string$Mnpad$Mnright);
        Object object1 = AddOp.$Mn.apply2(param1Object2, param1Object1);
        if (Scheme.numLEq.apply2(this.n, object1) != Boolean.FALSE) {
          param1Object2 = this.s;
          try {
            object1 = param1Object2;
            try {
              int i = ((Number)param1Object1).intValue();
              param1Object1 = AddOp.$Pl.apply2(param1Object1, this.n);
              try {
                int j = ((Number)param1Object1).intValue();
                return srfi13.$PcSubstring$SlShared((CharSequence)object1, i, j);
              } catch (ClassCastException null) {
                throw new WrongType(classCastException2, "%substring/shared", 2, param1Object1);
              } 
            } catch (ClassCastException classCastException2) {
              throw new WrongType(classCastException2, "%substring/shared", 1, param1Object1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "%substring/shared", 0, classCastException2);
          } 
        } 
        object1 = this.n;
        try {
          int i = ((Number)object1).intValue();
          object2 = strings.makeString(i, LangPrimType.charType);
          object1 = this.s;
          try {
            CharSequence charSequence = (CharSequence)object1;
            try {
              i = ((Number)classCastException1).intValue();
              try {
                int j = ((Number)classCastException2).intValue();
                srfi13.$PcStringCopy$Ex((CharSequence)object2, 0, charSequence, i, j);
                return object2;
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "%string-copy!", 4, classCastException2);
              } 
            } catch (ClassCastException classCastException3) {
              throw new WrongType(classCastException3, "%string-copy!", 3, classCastException);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%string-copy!", 2, object1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-string", 1, object1);
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1047, 7);
        throw unboundLocationException;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 138) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 139) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame72 extends ModuleBody {
    final ModuleMethod lambda$Fn164 = new ModuleMethod(this, 140, null, 0);
    
    final ModuleMethod lambda$Fn165 = new ModuleMethod(this, 141, null, 8194);
    
    Object n;
    
    Object s;
    
    static boolean lambda166(Object param1Object) {
      boolean bool2 = numbers.isInteger(param1Object);
      boolean bool1 = bool2;
      if (bool2) {
        bool2 = numbers.isExact(param1Object);
        bool1 = bool2;
        if (bool2)
          bool1 = ((Boolean)Scheme.numLEq.apply2(srfi13.Lit0, param1Object)).booleanValue(); 
      } 
      return bool1;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 140) ? lambda164() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 141) ? lambda165(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda164() {
      ModuleMethod moduleMethod = srfi13.string$Mnpad;
      Object object = this.s;
      Location location = srfi13.loc$rest;
      try {
        Object object1 = location.get();
        return srfi13.stringParseFinalStart$PlEnd(moduleMethod, object, object1);
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1058, 52);
        throw unboundLocationException;
      } 
    }
    
    Object lambda165(Object param1Object1, Object param1Object2) {
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$check$Mnarg;
      try {
        Object object2 = location.get();
        applyToArgs.apply4(object2, srfi13.lambda$Fn166, this.n, srfi13.string$Mnpad);
        object2 = AddOp.$Mn.apply2(param1Object2, param1Object1);
        if (Scheme.numLEq.apply2(this.n, object2) != Boolean.FALSE) {
          param1Object1 = this.s;
          try {
            CharSequence charSequence = (CharSequence)param1Object1;
            param1Object1 = AddOp.$Mn.apply2(param1Object2, this.n);
            try {
              int i = ((Number)param1Object1).intValue();
              try {
                int j = ((Number)param1Object2).intValue();
                return srfi13.$PcSubstring$SlShared(charSequence, i, j);
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "%substring/shared", 2, param1Object2);
              } 
            } catch (ClassCastException null) {
              throw new WrongType(classCastException2, "%substring/shared", 1, classCastException1);
            } 
          } catch (ClassCastException classCastException2) {
            throw new WrongType(classCastException2, "%substring/shared", 0, classCastException1);
          } 
        } 
        Object object1 = this.n;
        try {
          int i = ((Number)object1).intValue();
          object1 = strings.makeString(i, LangPrimType.charType);
          object2 = AddOp.$Mn.apply2(this.n, object2);
          try {
            i = ((Number)object2).intValue();
            object2 = this.s;
            try {
              CharSequence charSequence = (CharSequence)object2;
              try {
                int j = ((Number)classCastException1).intValue();
                try {
                  int k = ((Number)classCastException2).intValue();
                  srfi13.$PcStringCopy$Ex((CharSequence)object1, i, charSequence, j, k);
                  return object1;
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "%string-copy!", 4, classCastException2);
                } 
              } catch (ClassCastException classCastException3) {
                throw new WrongType(classCastException3, "%string-copy!", 3, classCastException);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%string-copy!", 2, object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%string-copy!", 1, object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-string", 1, object1);
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1059, 7);
        throw unboundLocationException;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 140) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 141) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame73 extends ModuleBody {
    Object criterion;
    
    final ModuleMethod lambda$Fn167 = new ModuleMethod(this, 145, null, 0);
    
    final ModuleMethod lambda$Fn168 = new ModuleMethod(this, 146, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 145) ? lambda167() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 146) ? lambda168(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda167() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mndelete, this.s, this.maybe$Mnstart$Plend);
    }
    
    CharSequence lambda168(Object param1Object1, Object param1Object2) {
      // Byte code:
      //   0: new gnu/kawa/slib/srfi13$frame74
      //   3: dup
      //   4: invokespecial <init> : ()V
      //   7: astore #5
      //   9: aload #5
      //   11: aload_0
      //   12: putfield staticLink : Lgnu/kawa/slib/srfi13$frame73;
      //   15: aload_0
      //   16: getfield criterion : Ljava/lang/Object;
      //   19: invokestatic isProcedure : (Ljava/lang/Object;)Z
      //   22: ifeq -> 123
      //   25: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   28: aload_2
      //   29: aload_1
      //   30: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   33: astore #4
      //   35: aload #4
      //   37: checkcast java/lang/Number
      //   40: invokevirtual intValue : ()I
      //   43: istore_3
      //   44: aload #5
      //   46: iload_3
      //   47: invokestatic makeString : (I)Ljava/lang/CharSequence;
      //   50: putfield temp : Ljava/lang/CharSequence;
      //   53: aload #5
      //   55: getfield lambda$Fn169 : Lgnu/expr/ModuleMethod;
      //   58: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
      //   61: aload_0
      //   62: getfield s : Ljava/lang/Object;
      //   65: iconst_2
      //   66: anewarray java/lang/Object
      //   69: dup
      //   70: iconst_0
      //   71: aload_1
      //   72: aastore
      //   73: dup
      //   74: iconst_1
      //   75: aload_2
      //   76: aastore
      //   77: invokestatic stringFold$V : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   80: astore_1
      //   81: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
      //   84: aload_1
      //   85: aload #4
      //   87: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   90: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   93: if_acmpeq -> 102
      //   96: aload #5
      //   98: getfield temp : Ljava/lang/CharSequence;
      //   101: areturn
      //   102: aload #5
      //   104: getfield temp : Ljava/lang/CharSequence;
      //   107: astore_2
      //   108: aload_1
      //   109: checkcast java/lang/Number
      //   112: invokevirtual intValue : ()I
      //   115: istore_3
      //   116: aload_2
      //   117: iconst_0
      //   118: iload_3
      //   119: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   122: areturn
      //   123: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   126: astore #4
      //   128: getstatic gnu/kawa/slib/srfi13.loc$char$Mnset$Qu : Lgnu/mapping/Location;
      //   131: astore #6
      //   133: aload #6
      //   135: invokevirtual get : ()Ljava/lang/Object;
      //   138: astore #6
      //   140: aload #4
      //   142: aload #6
      //   144: aload_0
      //   145: getfield criterion : Ljava/lang/Object;
      //   148: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   151: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   154: if_acmpeq -> 251
      //   157: aload_0
      //   158: getfield criterion : Ljava/lang/Object;
      //   161: astore #4
      //   163: aload #5
      //   165: aload #4
      //   167: putfield cset : Ljava/lang/Object;
      //   170: aload #5
      //   172: getfield lambda$Fn170 : Lgnu/expr/ModuleMethod;
      //   175: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
      //   178: aload_0
      //   179: getfield s : Ljava/lang/Object;
      //   182: iconst_2
      //   183: anewarray java/lang/Object
      //   186: dup
      //   187: iconst_0
      //   188: aload_1
      //   189: aastore
      //   190: dup
      //   191: iconst_1
      //   192: aload_2
      //   193: aastore
      //   194: invokestatic stringFold$V : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   197: astore #4
      //   199: aload #4
      //   201: checkcast java/lang/Number
      //   204: invokevirtual intValue : ()I
      //   207: istore_3
      //   208: aload #5
      //   210: iload_3
      //   211: invokestatic makeString : (I)Ljava/lang/CharSequence;
      //   214: putfield ans : Ljava/lang/CharSequence;
      //   217: aload #5
      //   219: getfield lambda$Fn171 : Lgnu/expr/ModuleMethod;
      //   222: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
      //   225: aload_0
      //   226: getfield s : Ljava/lang/Object;
      //   229: iconst_2
      //   230: anewarray java/lang/Object
      //   233: dup
      //   234: iconst_0
      //   235: aload_1
      //   236: aastore
      //   237: dup
      //   238: iconst_1
      //   239: aload_2
      //   240: aastore
      //   241: invokestatic stringFold$V : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   244: pop
      //   245: aload #5
      //   247: getfield ans : Ljava/lang/CharSequence;
      //   250: areturn
      //   251: aload_0
      //   252: getfield criterion : Ljava/lang/Object;
      //   255: invokestatic isChar : (Ljava/lang/Object;)Z
      //   258: ifeq -> 294
      //   261: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   264: astore #4
      //   266: getstatic gnu/kawa/slib/srfi13.loc$char$Mnset : Lgnu/mapping/Location;
      //   269: astore #6
      //   271: aload #6
      //   273: invokevirtual get : ()Ljava/lang/Object;
      //   276: astore #6
      //   278: aload #4
      //   280: aload #6
      //   282: aload_0
      //   283: getfield criterion : Ljava/lang/Object;
      //   286: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   289: astore #4
      //   291: goto -> 163
      //   294: ldc 'string-delete criterion not predicate, char or char-set'
      //   296: iconst_1
      //   297: anewarray java/lang/Object
      //   300: dup
      //   301: iconst_0
      //   302: aload_0
      //   303: getfield criterion : Ljava/lang/Object;
      //   306: aastore
      //   307: invokestatic error$V : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   310: astore #4
      //   312: goto -> 163
      //   315: astore_1
      //   316: new gnu/mapping/WrongType
      //   319: dup
      //   320: aload_1
      //   321: ldc 'make-string'
      //   323: iconst_1
      //   324: aload #4
      //   326: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   329: athrow
      //   330: astore_2
      //   331: new gnu/mapping/WrongType
      //   334: dup
      //   335: aload_2
      //   336: ldc 'substring'
      //   338: iconst_3
      //   339: aload_1
      //   340: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   343: athrow
      //   344: astore_1
      //   345: aload_1
      //   346: ldc '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
      //   348: sipush #1096
      //   351: bipush #22
      //   353: invokevirtual setLine : (Ljava/lang/String;II)V
      //   356: aload_1
      //   357: athrow
      //   358: astore_1
      //   359: aload_1
      //   360: ldc '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
      //   362: sipush #1097
      //   365: bipush #26
      //   367: invokevirtual setLine : (Ljava/lang/String;II)V
      //   370: aload_1
      //   371: athrow
      //   372: astore_1
      //   373: new gnu/mapping/WrongType
      //   376: dup
      //   377: aload_1
      //   378: ldc 'make-string'
      //   380: iconst_1
      //   381: aload #4
      //   383: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   386: athrow
      // Exception table:
      //   from	to	target	type
      //   35	44	315	java/lang/ClassCastException
      //   108	116	330	java/lang/ClassCastException
      //   133	140	344	gnu/mapping/UnboundLocationException
      //   199	208	372	java/lang/ClassCastException
      //   271	278	358	gnu/mapping/UnboundLocationException
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 145) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 146) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame74 extends ModuleBody {
      CharSequence ans;
      
      Object cset;
      
      final ModuleMethod lambda$Fn169;
      
      final ModuleMethod lambda$Fn170;
      
      final ModuleMethod lambda$Fn171;
      
      srfi13.frame73 staticLink;
      
      CharSequence temp;
      
      public frame74() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 142, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1089");
        this.lambda$Fn169 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 143, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1099");
        this.lambda$Fn170 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 144, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1104");
        this.lambda$Fn171 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.apply2(param2ModuleMethod, param2Object1, param2Object2);
          case 142:
            return lambda169(param2Object1, param2Object2);
          case 143:
            return lambda170(param2Object1, param2Object2);
          case 144:
            break;
        } 
        return lambda171(param2Object1, param2Object2);
      }
      
      Object lambda169(Object param2Object1, Object param2Object2) {
        if (Scheme.applyToArgs.apply2(this.staticLink.criterion, param2Object1) != Boolean.FALSE)
          return param2Object2; 
        CharSequence charSequence = this.temp;
        try {
          CharSeq charSeq = (CharSeq)charSequence;
          try {
            int i = ((Number)param2Object2).intValue();
            try {
              char c = ((Char)param2Object1).charValue();
              strings.stringSet$Ex(charSeq, i, c);
              return AddOp.$Pl.apply2(param2Object2, srfi13.Lit1);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-set!", 3, param2Object1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-set!", 2, classCastException);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-set!", 1, charSequence);
        } 
      }
      
      Object lambda170(Object param2Object1, Object param2Object2) {
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
        try {
          Object object = location.get();
          return (applyToArgs.apply3(object, this.cset, param2Object1) != Boolean.FALSE) ? param2Object2 : AddOp.$Pl.apply2(param2Object2, srfi13.Lit1);
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1099, 45);
          throw unboundLocationException;
        } 
      }
      
      Object lambda171(Object param2Object1, Object param2Object2) {
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
        try {
          Object object = location.get();
          if (applyToArgs.apply3(object, this.cset, param2Object1) != Boolean.FALSE)
            return param2Object2; 
          CharSequence charSequence = this.ans;
          try {
            object = charSequence;
            try {
              int i = ((Number)param2Object2).intValue();
              try {
                char c = ((Char)param2Object1).charValue();
                strings.stringSet$Ex((CharSeq)object, i, c);
                return AddOp.$Pl.apply2(param2Object2, srfi13.Lit1);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-set!", 3, param2Object1);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string-set!", 2, classCastException);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-set!", 1, charSequence);
          } 
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1104, 35);
          throw unboundLocationException;
        } 
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
          case 144:
            param2CallContext.value1 = param2Object1;
            param2CallContext.value2 = param2Object2;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 2;
            return 0;
          case 143:
            param2CallContext.value1 = param2Object1;
            param2CallContext.value2 = param2Object2;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 2;
            return 0;
          case 142:
            break;
        } 
        param2CallContext.value1 = param2Object1;
        param2CallContext.value2 = param2Object2;
        param2CallContext.proc = (Procedure)param2ModuleMethod;
        param2CallContext.pc = 2;
        return 0;
      }
    }
  }
  
  public class frame74 extends ModuleBody {
    CharSequence ans;
    
    Object cset;
    
    final ModuleMethod lambda$Fn169;
    
    final ModuleMethod lambda$Fn170;
    
    final ModuleMethod lambda$Fn171;
    
    srfi13.frame73 staticLink;
    
    CharSequence temp;
    
    public frame74() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 142, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1089");
      this.lambda$Fn169 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 143, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1099");
      this.lambda$Fn170 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 144, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1104");
      this.lambda$Fn171 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply2(param1ModuleMethod, param1Object1, param1Object2);
        case 142:
          return lambda169(param1Object1, param1Object2);
        case 143:
          return lambda170(param1Object1, param1Object2);
        case 144:
          break;
      } 
      return lambda171(param1Object1, param1Object2);
    }
    
    Object lambda169(Object param1Object1, Object param1Object2) {
      if (Scheme.applyToArgs.apply2(this.staticLink.criterion, param1Object1) != Boolean.FALSE)
        return param1Object2; 
      CharSequence charSequence = this.temp;
      try {
        CharSeq charSeq = (CharSeq)charSequence;
        try {
          int i = ((Number)param1Object2).intValue();
          try {
            char c = ((Char)param1Object1).charValue();
            strings.stringSet$Ex(charSeq, i, c);
            return AddOp.$Pl.apply2(param1Object2, srfi13.Lit1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-set!", 3, param1Object1);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "string-set!", 2, classCastException);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-set!", 1, charSequence);
      } 
    }
    
    Object lambda170(Object param1Object1, Object param1Object2) {
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
      try {
        Object object = location.get();
        return (applyToArgs.apply3(object, this.cset, param1Object1) != Boolean.FALSE) ? param1Object2 : AddOp.$Pl.apply2(param1Object2, srfi13.Lit1);
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1099, 45);
        throw unboundLocationException;
      } 
    }
    
    Object lambda171(Object param1Object1, Object param1Object2) {
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
      try {
        Object object = location.get();
        if (applyToArgs.apply3(object, this.cset, param1Object1) != Boolean.FALSE)
          return param1Object2; 
        CharSequence charSequence = this.ans;
        try {
          object = charSequence;
          try {
            int i = ((Number)param1Object2).intValue();
            try {
              char c = ((Char)param1Object1).charValue();
              strings.stringSet$Ex((CharSeq)object, i, c);
              return AddOp.$Pl.apply2(param1Object2, srfi13.Lit1);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-set!", 3, param1Object1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-set!", 2, classCastException);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-set!", 1, charSequence);
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1104, 35);
        throw unboundLocationException;
      } 
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
        case 144:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 2;
          return 0;
        case 143:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 2;
          return 0;
        case 142:
          break;
      } 
      param1CallContext.value1 = param1Object1;
      param1CallContext.value2 = param1Object2;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 2;
      return 0;
    }
  }
  
  public class frame75 extends ModuleBody {
    Object criterion;
    
    final ModuleMethod lambda$Fn172 = new ModuleMethod(this, 150, null, 0);
    
    final ModuleMethod lambda$Fn173 = new ModuleMethod(this, 151, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 150) ? lambda172() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 151) ? lambda173(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda172() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfilter, this.s, this.maybe$Mnstart$Plend);
    }
    
    CharSequence lambda173(Object param1Object1, Object param1Object2) {
      // Byte code:
      //   0: new gnu/kawa/slib/srfi13$frame76
      //   3: dup
      //   4: invokespecial <init> : ()V
      //   7: astore #5
      //   9: aload #5
      //   11: aload_0
      //   12: putfield staticLink : Lgnu/kawa/slib/srfi13$frame75;
      //   15: aload_0
      //   16: getfield criterion : Ljava/lang/Object;
      //   19: invokestatic isProcedure : (Ljava/lang/Object;)Z
      //   22: ifeq -> 123
      //   25: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   28: aload_2
      //   29: aload_1
      //   30: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   33: astore #4
      //   35: aload #4
      //   37: checkcast java/lang/Number
      //   40: invokevirtual intValue : ()I
      //   43: istore_3
      //   44: aload #5
      //   46: iload_3
      //   47: invokestatic makeString : (I)Ljava/lang/CharSequence;
      //   50: putfield temp : Ljava/lang/CharSequence;
      //   53: aload #5
      //   55: getfield lambda$Fn174 : Lgnu/expr/ModuleMethod;
      //   58: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
      //   61: aload_0
      //   62: getfield s : Ljava/lang/Object;
      //   65: iconst_2
      //   66: anewarray java/lang/Object
      //   69: dup
      //   70: iconst_0
      //   71: aload_1
      //   72: aastore
      //   73: dup
      //   74: iconst_1
      //   75: aload_2
      //   76: aastore
      //   77: invokestatic stringFold$V : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   80: astore_1
      //   81: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
      //   84: aload_1
      //   85: aload #4
      //   87: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   90: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   93: if_acmpeq -> 102
      //   96: aload #5
      //   98: getfield temp : Ljava/lang/CharSequence;
      //   101: areturn
      //   102: aload #5
      //   104: getfield temp : Ljava/lang/CharSequence;
      //   107: astore_2
      //   108: aload_1
      //   109: checkcast java/lang/Number
      //   112: invokevirtual intValue : ()I
      //   115: istore_3
      //   116: aload_2
      //   117: iconst_0
      //   118: iload_3
      //   119: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   122: areturn
      //   123: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   126: astore #4
      //   128: getstatic gnu/kawa/slib/srfi13.loc$char$Mnset$Qu : Lgnu/mapping/Location;
      //   131: astore #6
      //   133: aload #6
      //   135: invokevirtual get : ()Ljava/lang/Object;
      //   138: astore #6
      //   140: aload #4
      //   142: aload #6
      //   144: aload_0
      //   145: getfield criterion : Ljava/lang/Object;
      //   148: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   151: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   154: if_acmpeq -> 251
      //   157: aload_0
      //   158: getfield criterion : Ljava/lang/Object;
      //   161: astore #4
      //   163: aload #5
      //   165: aload #4
      //   167: putfield cset : Ljava/lang/Object;
      //   170: aload #5
      //   172: getfield lambda$Fn175 : Lgnu/expr/ModuleMethod;
      //   175: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
      //   178: aload_0
      //   179: getfield s : Ljava/lang/Object;
      //   182: iconst_2
      //   183: anewarray java/lang/Object
      //   186: dup
      //   187: iconst_0
      //   188: aload_1
      //   189: aastore
      //   190: dup
      //   191: iconst_1
      //   192: aload_2
      //   193: aastore
      //   194: invokestatic stringFold$V : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   197: astore #4
      //   199: aload #4
      //   201: checkcast java/lang/Number
      //   204: invokevirtual intValue : ()I
      //   207: istore_3
      //   208: aload #5
      //   210: iload_3
      //   211: invokestatic makeString : (I)Ljava/lang/CharSequence;
      //   214: putfield ans : Ljava/lang/CharSequence;
      //   217: aload #5
      //   219: getfield lambda$Fn176 : Lgnu/expr/ModuleMethod;
      //   222: getstatic gnu/kawa/slib/srfi13.Lit0 : Lgnu/math/IntNum;
      //   225: aload_0
      //   226: getfield s : Ljava/lang/Object;
      //   229: iconst_2
      //   230: anewarray java/lang/Object
      //   233: dup
      //   234: iconst_0
      //   235: aload_1
      //   236: aastore
      //   237: dup
      //   238: iconst_1
      //   239: aload_2
      //   240: aastore
      //   241: invokestatic stringFold$V : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   244: pop
      //   245: aload #5
      //   247: getfield ans : Ljava/lang/CharSequence;
      //   250: areturn
      //   251: aload_0
      //   252: getfield criterion : Ljava/lang/Object;
      //   255: invokestatic isChar : (Ljava/lang/Object;)Z
      //   258: ifeq -> 294
      //   261: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   264: astore #4
      //   266: getstatic gnu/kawa/slib/srfi13.loc$char$Mnset : Lgnu/mapping/Location;
      //   269: astore #6
      //   271: aload #6
      //   273: invokevirtual get : ()Ljava/lang/Object;
      //   276: astore #6
      //   278: aload #4
      //   280: aload #6
      //   282: aload_0
      //   283: getfield criterion : Ljava/lang/Object;
      //   286: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   289: astore #4
      //   291: goto -> 163
      //   294: ldc 'string-delete criterion not predicate, char or char-set'
      //   296: iconst_1
      //   297: anewarray java/lang/Object
      //   300: dup
      //   301: iconst_0
      //   302: aload_0
      //   303: getfield criterion : Ljava/lang/Object;
      //   306: aastore
      //   307: invokestatic error$V : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   310: astore #4
      //   312: goto -> 163
      //   315: astore_1
      //   316: new gnu/mapping/WrongType
      //   319: dup
      //   320: aload_1
      //   321: ldc 'make-string'
      //   323: iconst_1
      //   324: aload #4
      //   326: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   329: athrow
      //   330: astore_2
      //   331: new gnu/mapping/WrongType
      //   334: dup
      //   335: aload_2
      //   336: ldc 'substring'
      //   338: iconst_3
      //   339: aload_1
      //   340: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   343: athrow
      //   344: astore_1
      //   345: aload_1
      //   346: ldc '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
      //   348: sipush #1124
      //   351: bipush #22
      //   353: invokevirtual setLine : (Ljava/lang/String;II)V
      //   356: aload_1
      //   357: athrow
      //   358: astore_1
      //   359: aload_1
      //   360: ldc '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
      //   362: sipush #1125
      //   365: bipush #26
      //   367: invokevirtual setLine : (Ljava/lang/String;II)V
      //   370: aload_1
      //   371: athrow
      //   372: astore_1
      //   373: new gnu/mapping/WrongType
      //   376: dup
      //   377: aload_1
      //   378: ldc 'make-string'
      //   380: iconst_1
      //   381: aload #4
      //   383: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   386: athrow
      // Exception table:
      //   from	to	target	type
      //   35	44	315	java/lang/ClassCastException
      //   108	116	330	java/lang/ClassCastException
      //   133	140	344	gnu/mapping/UnboundLocationException
      //   199	208	372	java/lang/ClassCastException
      //   271	278	358	gnu/mapping/UnboundLocationException
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 150) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 151) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public class frame76 extends ModuleBody {
      CharSequence ans;
      
      Object cset;
      
      final ModuleMethod lambda$Fn174;
      
      final ModuleMethod lambda$Fn175;
      
      final ModuleMethod lambda$Fn176;
      
      srfi13.frame75 staticLink;
      
      CharSequence temp;
      
      public frame76() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 147, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1116");
        this.lambda$Fn174 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 148, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1128");
        this.lambda$Fn175 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 149, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1133");
        this.lambda$Fn176 = moduleMethod;
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.apply2(param2ModuleMethod, param2Object1, param2Object2);
          case 147:
            return lambda174(param2Object1, param2Object2);
          case 148:
            return lambda175(param2Object1, param2Object2);
          case 149:
            break;
        } 
        return lambda176(param2Object1, param2Object2);
      }
      
      Object lambda174(Object param2Object1, Object param2Object2) {
        Object object = param2Object2;
        if (Scheme.applyToArgs.apply2(this.staticLink.criterion, param2Object1) != Boolean.FALSE) {
          object = this.temp;
          try {
            CharSeq charSeq = (CharSeq)object;
            try {
              int i = ((Number)param2Object2).intValue();
              try {
                char c = ((Char)param2Object1).charValue();
                strings.stringSet$Ex(charSeq, i, c);
                return AddOp.$Pl.apply2(param2Object2, srfi13.Lit1);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-set!", 3, param2Object1);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string-set!", 2, classCastException);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-set!", 1, object);
          } 
        } 
        return object;
      }
      
      Object lambda175(Object param2Object1, Object param2Object2) {
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
        try {
          Object object2 = location.get();
          Object object1 = param2Object2;
          if (applyToArgs.apply3(object2, this.cset, param2Object1) != Boolean.FALSE)
            object1 = AddOp.$Pl.apply2(param2Object2, srfi13.Lit1); 
          return object1;
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1128, 45);
          throw unboundLocationException;
        } 
      }
      
      Object lambda176(Object param2Object1, Object param2Object2) {
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
        try {
          Object object2 = location.get();
          Object object1 = param2Object2;
          if (applyToArgs.apply3(object2, this.cset, param2Object1) != Boolean.FALSE) {
            object1 = this.ans;
            try {
              CharSeq charSeq = (CharSeq)object1;
              try {
                int i = ((Number)param2Object2).intValue();
                try {
                  char c = ((Char)param2Object1).charValue();
                  strings.stringSet$Ex(charSeq, i, c);
                  return AddOp.$Pl.apply2(param2Object2, srfi13.Lit1);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string-set!", 3, param2Object1);
                } 
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string-set!", 2, classCastException);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-set!", 1, object1);
            } 
          } 
          return object1;
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1133, 35);
          throw unboundLocationException;
        } 
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.match2(param2ModuleMethod, param2Object1, param2Object2, param2CallContext);
          case 149:
            param2CallContext.value1 = param2Object1;
            param2CallContext.value2 = param2Object2;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 2;
            return 0;
          case 148:
            param2CallContext.value1 = param2Object1;
            param2CallContext.value2 = param2Object2;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 2;
            return 0;
          case 147:
            break;
        } 
        param2CallContext.value1 = param2Object1;
        param2CallContext.value2 = param2Object2;
        param2CallContext.proc = (Procedure)param2ModuleMethod;
        param2CallContext.pc = 2;
        return 0;
      }
    }
  }
  
  public class frame76 extends ModuleBody {
    CharSequence ans;
    
    Object cset;
    
    final ModuleMethod lambda$Fn174;
    
    final ModuleMethod lambda$Fn175;
    
    final ModuleMethod lambda$Fn176;
    
    srfi13.frame75 staticLink;
    
    CharSequence temp;
    
    public frame76() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 147, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1116");
      this.lambda$Fn174 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 148, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1128");
      this.lambda$Fn175 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 149, null, 8194);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1133");
      this.lambda$Fn176 = moduleMethod;
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply2(param1ModuleMethod, param1Object1, param1Object2);
        case 147:
          return lambda174(param1Object1, param1Object2);
        case 148:
          return lambda175(param1Object1, param1Object2);
        case 149:
          break;
      } 
      return lambda176(param1Object1, param1Object2);
    }
    
    Object lambda174(Object param1Object1, Object param1Object2) {
      Object object = param1Object2;
      if (Scheme.applyToArgs.apply2(this.staticLink.criterion, param1Object1) != Boolean.FALSE) {
        object = this.temp;
        try {
          CharSeq charSeq = (CharSeq)object;
          try {
            int i = ((Number)param1Object2).intValue();
            try {
              char c = ((Char)param1Object1).charValue();
              strings.stringSet$Ex(charSeq, i, c);
              return AddOp.$Pl.apply2(param1Object2, srfi13.Lit1);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-set!", 3, param1Object1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-set!", 2, classCastException);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-set!", 1, object);
        } 
      } 
      return object;
    }
    
    Object lambda175(Object param1Object1, Object param1Object2) {
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
      try {
        Object object2 = location.get();
        Object object1 = param1Object2;
        if (applyToArgs.apply3(object2, this.cset, param1Object1) != Boolean.FALSE)
          object1 = AddOp.$Pl.apply2(param1Object2, srfi13.Lit1); 
        return object1;
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1128, 45);
        throw unboundLocationException;
      } 
    }
    
    Object lambda176(Object param1Object1, Object param1Object2) {
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
      try {
        Object object2 = location.get();
        Object object1 = param1Object2;
        if (applyToArgs.apply3(object2, this.cset, param1Object1) != Boolean.FALSE) {
          object1 = this.ans;
          try {
            CharSeq charSeq = (CharSeq)object1;
            try {
              int i = ((Number)param1Object2).intValue();
              try {
                char c = ((Char)param1Object1).charValue();
                strings.stringSet$Ex(charSeq, i, c);
                return AddOp.$Pl.apply2(param1Object2, srfi13.Lit1);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-set!", 3, param1Object1);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string-set!", 2, classCastException);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-set!", 1, object1);
          } 
        } 
        return object1;
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1133, 35);
        throw unboundLocationException;
      } 
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
        case 149:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 2;
          return 0;
        case 148:
          param1CallContext.value1 = param1Object1;
          param1CallContext.value2 = param1Object2;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 2;
          return 0;
        case 147:
          break;
      } 
      param1CallContext.value1 = param1Object1;
      param1CallContext.value2 = param1Object2;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 2;
      return 0;
    }
  }
  
  public class frame77 extends ModuleBody {
    Object criterion;
    
    final ModuleMethod lambda$Fn177 = new ModuleMethod(this, 152, null, 0);
    
    final ModuleMethod lambda$Fn178 = new ModuleMethod(this, 153, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object str;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 152) ? lambda177() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 153) ? lambda178(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda177() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnindex, this.str, this.maybe$Mnstart$Plend);
    }
    
    Object lambda178(Object param1Object1, Object param1Object2) {
      if (characters.isChar(this.criterion))
        while (true) {
          Object object = Scheme.numLss.apply2(param1Object1, param1Object2);
          try {
            boolean bool = ((Boolean)object).booleanValue();
            if (bool) {
              Object object1 = this.criterion;
              try {
                object = object1;
                object1 = this.str;
                try {
                  CharSequence charSequence = (CharSequence)object1;
                  try {
                    int i = ((Number)param1Object1).intValue();
                    if (!characters.isChar$Eq((Char)object, Char.make(strings.stringRef(charSequence, i)))) {
                      param1Object1 = AddOp.$Pl.apply2(param1Object1, srfi13.Lit1);
                      continue;
                    } 
                    return param1Object1;
                  } catch (ClassCastException classCastException2) {
                    throw new WrongType(classCastException2, "string-ref", 2, param1Object1);
                  } 
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException1, "string-ref", 1, object1);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException1, "char=?", 1, object1);
              } 
            } 
            return bool ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "x", -2, object);
          } 
        }  
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$char$Mnset$Qu;
      try {
        Object object2 = location.get();
        if (applyToArgs.apply2(object2, this.criterion) != Boolean.FALSE) {
          ClassCastException classCastException3 = classCastException1;
          while (true) {
            object1 = Scheme.numLss.apply2(classCastException3, classCastException2);
            try {
              boolean bool = ((Boolean)object1).booleanValue();
              if (bool) {
                object2 = Scheme.applyToArgs;
                object1 = srfi13.loc$char$Mnset$Mncontains$Qu;
                try {
                  Object object3 = object1.get();
                  Object object4 = this.criterion;
                  object1 = this.str;
                  try {
                    Object object;
                    CharSequence charSequence = (CharSequence)object1;
                    try {
                      int i = ((Number)classCastException3).intValue();
                      object1 = classCastException3;
                      if (object2.apply3(object3, object4, Char.make(strings.stringRef(charSequence, i))) == Boolean.FALSE) {
                        object = AddOp.$Pl.apply2(classCastException3, srfi13.Lit1);
                        continue;
                      } 
                    } catch (ClassCastException classCastException4) {
                      throw new WrongType(classCastException4, "string-ref", 2, object);
                    } 
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException, "string-ref", 1, classCastException4);
                  } 
                } catch (UnboundLocationException object1) {
                  object1.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1162, 9);
                  throw object1;
                } 
              } 
              return bool ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "x", -2, object1);
            } 
          } 
        } 
        if (misc.isProcedure(this.criterion)) {
          Object object = object1;
          while (true) {
            object1 = Scheme.numLss.apply2(object, classCastException);
            try {
              boolean bool = ((Boolean)object1).booleanValue();
              if (bool) {
                object2 = Scheme.applyToArgs;
                Object object3 = this.criterion;
                object1 = this.str;
                try {
                  CharSequence charSequence = (CharSequence)object1;
                  try {
                    int i = ((Number)object).intValue();
                    object1 = object;
                    if (object2.apply2(object3, Char.make(strings.stringRef(charSequence, i))) == Boolean.FALSE) {
                      object = AddOp.$Pl.apply2(object, srfi13.Lit1);
                      continue;
                    } 
                  } catch (ClassCastException classCastException3) {
                    throw new WrongType(classCastException3, "string-ref", 2, object);
                  } 
                } catch (ClassCastException classCastException4) {
                  throw new WrongType(classCastException4, "string-ref", 1, classCastException3);
                } 
              } 
              return bool ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException classCastException4) {
              throw new WrongType(classCastException4, "x", -2, classCastException3);
            } 
          } 
        } 
        return misc.error$V("Second param is neither char-set, char, or predicate procedure.", new Object[] { srfi13.string$Mnindex, this.criterion });
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1159, 5);
        throw unboundLocationException;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 152) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 153) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame78 extends ModuleBody {
    Object criterion;
    
    final ModuleMethod lambda$Fn179 = new ModuleMethod(this, 154, null, 0);
    
    final ModuleMethod lambda$Fn180 = new ModuleMethod(this, 155, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object str;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 154) ? lambda179() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 155) ? lambda180(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda179() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnindex$Mnright, this.str, this.maybe$Mnstart$Plend);
    }
    
    Object lambda180(Object param1Object1, Object param1Object2) {
      if (characters.isChar(this.criterion)) {
        param1Object2 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
        while (true) {
          Object object = Scheme.numGEq.apply2(param1Object2, param1Object1);
          try {
            boolean bool = ((Boolean)object).booleanValue();
            if (bool) {
              Object object1 = this.criterion;
              try {
                object = object1;
                object1 = this.str;
                try {
                  CharSequence charSequence = (CharSequence)object1;
                  try {
                    int i = ((Number)param1Object2).intValue();
                    if (!characters.isChar$Eq((Char)object, Char.make(strings.stringRef(charSequence, i)))) {
                      param1Object2 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
                      continue;
                    } 
                    return param1Object2;
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException, "string-ref", 2, param1Object2);
                  } 
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException, "string-ref", 1, object1);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException, "char=?", 1, object1);
              } 
            } 
            return bool ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "x", -2, object);
          } 
        } 
      } 
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$char$Mnset$Qu;
      try {
        Object object = location.get();
        if (applyToArgs.apply2(object, this.criterion) != Boolean.FALSE) {
          Object object1 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
          while (true) {
            param1Object2 = Scheme.numGEq.apply2(object1, classCastException);
            try {
              boolean bool = ((Boolean)param1Object2).booleanValue();
              if (bool) {
                object = Scheme.applyToArgs;
                param1Object2 = srfi13.loc$char$Mnset$Mncontains$Qu;
                try {
                  Object object2 = param1Object2.get();
                  Object object3 = this.criterion;
                  param1Object2 = this.str;
                  try {
                    CharSequence charSequence = (CharSequence)param1Object2;
                    try {
                      int i = ((Number)object1).intValue();
                      param1Object2 = object1;
                      if (object.apply3(object2, object3, Char.make(strings.stringRef(charSequence, i))) == Boolean.FALSE) {
                        object1 = AddOp.$Mn.apply2(object1, srfi13.Lit1);
                        continue;
                      } 
                    } catch (ClassCastException null) {
                      throw new WrongType(classCastException1, "string-ref", 2, object1);
                    } 
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException1, "string-ref", 1, param1Object2);
                  } 
                } catch (UnboundLocationException unboundLocationException) {
                  unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1182, 9);
                  throw unboundLocationException;
                } 
              } 
              return bool ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "x", -2, param1Object2);
            } 
          } 
        } 
        if (misc.isProcedure(this.criterion)) {
          Object object1 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
          while (true) {
            param1Object2 = Scheme.numGEq.apply2(object1, classCastException1);
            try {
              boolean bool = ((Boolean)param1Object2).booleanValue();
              if (bool) {
                object = Scheme.applyToArgs;
                Object object2 = this.criterion;
                param1Object2 = this.str;
                try {
                  CharSequence charSequence = (CharSequence)param1Object2;
                  try {
                    int i = ((Number)object1).intValue();
                    param1Object2 = object1;
                    if (object.apply2(object2, Char.make(strings.stringRef(charSequence, i))) == Boolean.FALSE) {
                      object1 = AddOp.$Mn.apply2(object1, srfi13.Lit1);
                      continue;
                    } 
                  } catch (ClassCastException classCastException2) {
                    throw new WrongType(classCastException2, "string-ref", 2, object1);
                  } 
                } catch (ClassCastException classCastException2) {
                  throw new WrongType(classCastException2, "string-ref", 1, param1Object2);
                } 
              } 
              return bool ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException classCastException2) {
              throw new WrongType(classCastException2, "x", -2, param1Object2);
            } 
          } 
        } 
        return misc.error$V("Second param is neither char-set, char, or predicate procedure.", new Object[] { srfi13.string$Mnindex$Mnright, this.criterion });
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1179, 5);
        throw unboundLocationException;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 154) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 155) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame79 extends ModuleBody {
    Object criterion;
    
    final ModuleMethod lambda$Fn181 = new ModuleMethod(this, 156, null, 0);
    
    final ModuleMethod lambda$Fn182 = new ModuleMethod(this, 157, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object str;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 156) ? lambda181() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 157) ? lambda182(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda181() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnskip, this.str, this.maybe$Mnstart$Plend);
    }
    
    Object lambda182(Object param1Object1, Object param1Object2) {
      if (characters.isChar(this.criterion)) {
        Object object = param1Object1;
        while (true) {
          param1Object1 = Scheme.numLss.apply2(object, param1Object2);
          try {
            boolean bool = ((Boolean)param1Object1).booleanValue();
            if (bool) {
              param1Object1 = this.criterion;
              try {
                Char char_ = (Char)param1Object1;
                param1Object1 = this.str;
                try {
                  CharSequence charSequence = (CharSequence)param1Object1;
                  try {
                    int i = ((Number)object).intValue();
                    param1Object1 = object;
                    if (characters.isChar$Eq(char_, Char.make(strings.stringRef(charSequence, i)))) {
                      object = AddOp.$Pl.apply2(object, srfi13.Lit1);
                      continue;
                    } 
                    return param1Object1;
                  } catch (ClassCastException classCastException1) {
                    throw new WrongType(classCastException1, "string-ref", 2, object);
                  } 
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException, "string-ref", 1, classCastException1);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException, "char=?", 1, classCastException1);
              } 
            } 
            return bool ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "x", -2, param1Object1);
          } 
        } 
      } 
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$char$Mnset$Qu;
      try {
        Object object = location.get();
        if (applyToArgs.apply2(object, this.criterion) != Boolean.FALSE) {
          Object object1 = param1Object1;
          while (true) {
            param1Object1 = Scheme.numLss.apply2(object1, classCastException);
            try {
              boolean bool = ((Boolean)param1Object1).booleanValue();
              if (bool) {
                object = Scheme.applyToArgs;
                param1Object1 = srfi13.loc$char$Mnset$Mncontains$Qu;
                try {
                  Object object2 = param1Object1.get();
                  Object object3 = this.criterion;
                  param1Object1 = this.str;
                  try {
                    CharSequence charSequence = (CharSequence)param1Object1;
                    try {
                      int i = ((Number)object1).intValue();
                      param1Object1 = object1;
                      if (object.apply3(object2, object3, Char.make(strings.stringRef(charSequence, i))) != Boolean.FALSE) {
                        object1 = AddOp.$Pl.apply2(object1, srfi13.Lit1);
                        continue;
                      } 
                    } catch (ClassCastException classCastException2) {
                      throw new WrongType(classCastException2, "string-ref", 2, object1);
                    } 
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException1, "string-ref", 1, classCastException2);
                  } 
                } catch (UnboundLocationException unboundLocationException) {
                  unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1203, 9);
                  throw unboundLocationException;
                } 
              } 
              return bool ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "x", -2, unboundLocationException);
            } 
          } 
        } 
        if (misc.isProcedure(this.criterion)) {
          UnboundLocationException unboundLocationException1 = unboundLocationException;
          while (true) {
            object1 = Scheme.numLss.apply2(unboundLocationException1, classCastException1);
            try {
              boolean bool = ((Boolean)object1).booleanValue();
              if (bool) {
                object = Scheme.applyToArgs;
                Object object2 = this.criterion;
                object1 = this.str;
                try {
                  Object object3;
                  CharSequence charSequence = (CharSequence)object1;
                  try {
                    int i = ((Number)unboundLocationException1).intValue();
                    object1 = unboundLocationException1;
                    if (object.apply2(object2, Char.make(strings.stringRef(charSequence, i))) != Boolean.FALSE) {
                      object3 = AddOp.$Pl.apply2(unboundLocationException1, srfi13.Lit1);
                      continue;
                    } 
                  } catch (ClassCastException object1) {
                    throw new WrongType(object1, "string-ref", 2, object3);
                  } 
                } catch (ClassCastException classCastException2) {
                  throw new WrongType(classCastException2, "string-ref", 1, object1);
                } 
              } 
              return bool ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException classCastException2) {
              throw new WrongType(classCastException2, "x", -2, object1);
            } 
          } 
        } 
        return misc.error$V("Second param is neither char-set, char, or predicate procedure.", new Object[] { srfi13.string$Mnskip, this.criterion });
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1200, 5);
        throw unboundLocationException;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 156) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 157) {
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
    final ModuleMethod lambda$Fn21 = new ModuleMethod(this, 18, null, 0);
    
    final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 19, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object proc;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 18) ? lambda21() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 19) ? lambda22(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda21() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfor$Mneach$Mnindex, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda22(Object param1Object1, Object param1Object2) {
      while (Scheme.numLss.apply2(param1Object1, param1Object2) != Boolean.FALSE) {
        Scheme.applyToArgs.apply2(this.proc, param1Object1);
        param1Object1 = AddOp.$Pl.apply2(param1Object1, srfi13.Lit1);
      } 
      return Values.empty;
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 18) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 19) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame80 extends ModuleBody {
    Object criterion;
    
    final ModuleMethod lambda$Fn183 = new ModuleMethod(this, 158, null, 0);
    
    final ModuleMethod lambda$Fn184 = new ModuleMethod(this, 159, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object str;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 158) ? lambda183() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 159) ? lambda184(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda183() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnskip$Mnright, this.str, this.maybe$Mnstart$Plend);
    }
    
    Object lambda184(Object param1Object1, Object param1Object2) {
      if (characters.isChar(this.criterion)) {
        Object object = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
        while (true) {
          param1Object2 = Scheme.numGEq.apply2(object, param1Object1);
          try {
            boolean bool = ((Boolean)param1Object2).booleanValue();
            if (bool) {
              param1Object2 = this.criterion;
              try {
                Char char_ = (Char)param1Object2;
                param1Object2 = this.str;
                try {
                  CharSequence charSequence = (CharSequence)param1Object2;
                  try {
                    int i = ((Number)object).intValue();
                    param1Object2 = object;
                    if (characters.isChar$Eq(char_, Char.make(strings.stringRef(charSequence, i)))) {
                      object = AddOp.$Mn.apply2(object, srfi13.Lit1);
                      continue;
                    } 
                    return param1Object2;
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException, "string-ref", 2, object);
                  } 
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException, "string-ref", 1, param1Object2);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException, "char=?", 1, param1Object2);
              } 
            } 
            return bool ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "x", -2, param1Object2);
          } 
        } 
      } 
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$char$Mnset$Qu;
      try {
        Object object = location.get();
        if (applyToArgs.apply2(object, this.criterion) != Boolean.FALSE) {
          Object object1 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
          while (true) {
            param1Object2 = Scheme.numGEq.apply2(object1, classCastException);
            try {
              boolean bool = ((Boolean)param1Object2).booleanValue();
              if (bool) {
                object = Scheme.applyToArgs;
                param1Object2 = srfi13.loc$char$Mnset$Mncontains$Qu;
                try {
                  Object object2 = param1Object2.get();
                  Object object3 = this.criterion;
                  param1Object2 = this.str;
                  try {
                    CharSequence charSequence = (CharSequence)param1Object2;
                    try {
                      int i = ((Number)object1).intValue();
                      param1Object2 = object1;
                      if (object.apply3(object2, object3, Char.make(strings.stringRef(charSequence, i))) != Boolean.FALSE) {
                        object1 = AddOp.$Mn.apply2(object1, srfi13.Lit1);
                        continue;
                      } 
                    } catch (ClassCastException null) {
                      throw new WrongType(classCastException1, "string-ref", 2, object1);
                    } 
                  } catch (ClassCastException null) {
                    throw new WrongType(classCastException1, "string-ref", 1, param1Object2);
                  } 
                } catch (UnboundLocationException unboundLocationException) {
                  unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1225, 9);
                  throw unboundLocationException;
                } 
              } 
              return bool ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "x", -2, param1Object2);
            } 
          } 
        } 
        if (misc.isProcedure(this.criterion)) {
          Object object1 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
          while (true) {
            param1Object2 = Scheme.numGEq.apply2(object1, classCastException1);
            try {
              boolean bool = ((Boolean)param1Object2).booleanValue();
              if (bool) {
                object = Scheme.applyToArgs;
                Object object2 = this.criterion;
                param1Object2 = this.str;
                try {
                  CharSequence charSequence = (CharSequence)param1Object2;
                  try {
                    int i = ((Number)object1).intValue();
                    param1Object2 = object1;
                    if (object.apply2(object2, Char.make(strings.stringRef(charSequence, i))) != Boolean.FALSE) {
                      object1 = AddOp.$Mn.apply2(object1, srfi13.Lit1);
                      continue;
                    } 
                  } catch (ClassCastException classCastException2) {
                    throw new WrongType(classCastException2, "string-ref", 2, object1);
                  } 
                } catch (ClassCastException classCastException2) {
                  throw new WrongType(classCastException2, "string-ref", 1, param1Object2);
                } 
              } 
              return bool ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException classCastException2) {
              throw new WrongType(classCastException2, "x", -2, param1Object2);
            } 
          } 
        } 
        return misc.error$V("CRITERION param is neither char-set or char.", new Object[] { srfi13.string$Mnskip$Mnright, this.criterion });
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1222, 5);
        throw unboundLocationException;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 158) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 159) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame81 extends ModuleBody {
    Object criterion;
    
    final ModuleMethod lambda$Fn185 = new ModuleMethod(this, 160, null, 0);
    
    final ModuleMethod lambda$Fn186 = new ModuleMethod(this, 161, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 160) ? lambda185() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 161) ? lambda186(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda185() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncount, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda186(Object param1Object1, Object param1Object2) {
      if (characters.isChar(this.criterion)) {
        IntNum intNum = srfi13.Lit0;
        Object object = param1Object1;
        while (true) {
          param1Object1 = intNum;
          if (Scheme.numGEq.apply2(object, param1Object2) == Boolean.FALSE) {
            Object object1 = AddOp.$Pl.apply2(object, srfi13.Lit1);
            param1Object1 = this.criterion;
            try {
              Char char_ = (Char)param1Object1;
              param1Object1 = this.s;
              try {
                CharSequence charSequence = (CharSequence)param1Object1;
                try {
                  int i = ((Number)object).intValue();
                  param1Object1 = intNum;
                  if (characters.isChar$Eq(char_, Char.make(strings.stringRef(charSequence, i))))
                    param1Object1 = AddOp.$Pl.apply2(intNum, srfi13.Lit1); 
                  object = object1;
                  Object object2 = param1Object1;
                } catch (ClassCastException classCastException1) {
                  throw new WrongType(classCastException1, "string-ref", 2, object);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException2, "string-ref", 1, classCastException1);
              } 
            } catch (ClassCastException classCastException2) {
              throw new WrongType(classCastException2, "char=?", 1, classCastException1);
            } 
          } 
          return classCastException1;
        } 
      } 
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$char$Mnset$Qu;
      try {
        Object object = location.get();
        if (applyToArgs.apply2(object, this.criterion) != Boolean.FALSE) {
          IntNum intNum = srfi13.Lit0;
          object = classCastException1;
          while (true) {
            IntNum intNum1 = intNum;
            if (Scheme.numGEq.apply2(object, classCastException2) == Boolean.FALSE) {
              Object object1 = AddOp.$Pl.apply2(object, srfi13.Lit1);
              ApplyToArgs applyToArgs1 = Scheme.applyToArgs;
              Location location1 = srfi13.loc$char$Mnset$Mncontains$Qu;
              try {
                Object object2 = location1.get();
                Object object3 = this.criterion;
                null = this.s;
                try {
                  CharSequence charSequence = (CharSequence)null;
                  try {
                    int i = ((Number)object).intValue();
                    null = intNum;
                    if (applyToArgs1.apply3(object2, object3, Char.make(strings.stringRef(charSequence, i))) != Boolean.FALSE)
                      null = AddOp.$Pl.apply2(intNum, srfi13.Lit1); 
                    object = object1;
                    Object object4 = null;
                  } catch (ClassCastException null) {
                    throw new WrongType(null, "string-ref", 2, object);
                  } 
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "string-ref", 1, null);
                } 
              } catch (UnboundLocationException null) {
                null.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1248, 16);
                throw null;
              } 
            } 
            return null;
          } 
        } 
        if (misc.isProcedure(this.criterion)) {
          IntNum intNum = srfi13.Lit0;
          object = null;
          while (true) {
            null = intNum;
            if (Scheme.numGEq.apply2(object, classCastException) == Boolean.FALSE) {
              Object object1 = AddOp.$Pl.apply2(object, srfi13.Lit1);
              ApplyToArgs applyToArgs1 = Scheme.applyToArgs;
              Object object2 = this.criterion;
              null = this.s;
              try {
                CharSequence charSequence = (CharSequence)null;
                try {
                  int i = ((Number)object).intValue();
                  null = intNum;
                  if (applyToArgs1.apply2(object2, Char.make(strings.stringRef(charSequence, i))) != Boolean.FALSE)
                    null = AddOp.$Pl.apply2(intNum, srfi13.Lit1); 
                  object = object1;
                  Object object3 = null;
                } catch (ClassCastException classCastException3) {
                  throw new WrongType(classCastException3, "string-ref", 2, object);
                } 
              } catch (ClassCastException classCastException4) {
                throw new WrongType(classCastException4, "string-ref", 1, classCastException3);
              } 
            } 
            return classCastException3;
          } 
        } 
        return misc.error$V("CRITERION param is neither char-set or char.", new Object[] { srfi13.string$Mncount, this.criterion });
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1246, 5);
        throw unboundLocationException;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 160) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 161) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame82 extends ModuleBody {
    Object char;
    
    final ModuleMethod lambda$Fn187 = new ModuleMethod(this, 162, null, 0);
    
    final ModuleMethod lambda$Fn188 = new ModuleMethod(this, 163, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 162) ? lambda187() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 163) ? lambda188(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda187() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfill$Ex, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda188(Object param1Object1, Object param1Object2) {
      param1Object2 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
      while (true) {
        if (Scheme.numLss.apply2(param1Object2, param1Object1) == Boolean.FALSE) {
          Object object = this.s;
          try {
            CharSeq charSeq = (CharSeq)object;
            try {
              int i = ((Number)param1Object2).intValue();
              object = this.char;
              try {
                char c = ((Char)object).charValue();
                strings.stringSet$Ex(charSeq, i, c);
                param1Object2 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-set!", 3, object);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-set!", 2, param1Object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-set!", 1, object);
          } 
        } 
        return Values.empty;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 162) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 163) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame83 extends ModuleBody {
    final ModuleMethod lambda$Fn189 = new ModuleMethod(this, 166, null, 0);
    
    final ModuleMethod lambda$Fn190 = new ModuleMethod(this, 167, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object pattern;
    
    Object text;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 166) ? lambda189() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 167) ? lambda190(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda189() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mncontains, this.text, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda190(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame84 frame84 = new frame84();
      frame84.staticLink = this;
      frame84.rest = param1Object1;
      frame84.t$Mnstart = param1Object2;
      frame84.t$Mnend = param1Object3;
      return call_with_values.callWithValues((Procedure)frame84.lambda$Fn191, (Procedure)frame84.lambda$Fn192);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 166) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 167) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame84 extends ModuleBody {
      final ModuleMethod lambda$Fn191 = new ModuleMethod(this, 164, null, 0);
      
      final ModuleMethod lambda$Fn192 = new ModuleMethod(this, 165, null, 8194);
      
      Object rest;
      
      srfi13.frame83 staticLink;
      
      Object t$Mnend;
      
      Object t$Mnstart;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 164) ? lambda191() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 165) ? lambda192(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda191() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncontains, this.staticLink.pattern, this.rest);
      }
      
      Object lambda192(Object param2Object1, Object param2Object2) {
        return srfi13.$PcKmpSearch(this.staticLink.pattern, this.staticLink.text, characters.char$Eq$Qu, param2Object1, param2Object2, this.t$Mnstart, this.t$Mnend);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 164) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 165) {
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
  
  public class frame84 extends ModuleBody {
    final ModuleMethod lambda$Fn191 = new ModuleMethod(this, 164, null, 0);
    
    final ModuleMethod lambda$Fn192 = new ModuleMethod(this, 165, null, 8194);
    
    Object rest;
    
    srfi13.frame83 staticLink;
    
    Object t$Mnend;
    
    Object t$Mnstart;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 164) ? lambda191() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 165) ? lambda192(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda191() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncontains, this.staticLink.pattern, this.rest);
    }
    
    Object lambda192(Object param1Object1, Object param1Object2) {
      return srfi13.$PcKmpSearch(this.staticLink.pattern, this.staticLink.text, characters.char$Eq$Qu, param1Object1, param1Object2, this.t$Mnstart, this.t$Mnend);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 164) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 165) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame85 extends ModuleBody {
    final ModuleMethod lambda$Fn193 = new ModuleMethod(this, 170, null, 0);
    
    final ModuleMethod lambda$Fn194 = new ModuleMethod(this, 171, null, 12291);
    
    LList maybe$Mnstarts$Plends;
    
    Object pattern;
    
    Object text;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 170) ? lambda193() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 171) ? lambda194(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda193() {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mncontains$Mnci, this.text, this.maybe$Mnstarts$Plends);
    }
    
    Object lambda194(Object param1Object1, Object param1Object2, Object param1Object3) {
      frame86 frame86 = new frame86();
      frame86.staticLink = this;
      frame86.rest = param1Object1;
      frame86.t$Mnstart = param1Object2;
      frame86.t$Mnend = param1Object3;
      return call_with_values.callWithValues((Procedure)frame86.lambda$Fn195, (Procedure)frame86.lambda$Fn196);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 170) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 171) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.value3 = param1Object3;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 3;
        return 0;
      } 
      return super.match3(param1ModuleMethod, param1Object1, param1Object2, param1Object3, param1CallContext);
    }
    
    public class frame86 extends ModuleBody {
      final ModuleMethod lambda$Fn195 = new ModuleMethod(this, 168, null, 0);
      
      final ModuleMethod lambda$Fn196 = new ModuleMethod(this, 169, null, 8194);
      
      Object rest;
      
      srfi13.frame85 staticLink;
      
      Object t$Mnend;
      
      Object t$Mnstart;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 168) ? lambda195() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2) {
        return (param2ModuleMethod.selector == 169) ? lambda196(param2Object1, param2Object2) : super.apply2(param2ModuleMethod, param2Object1, param2Object2);
      }
      
      Object lambda195() {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncontains$Mnci, this.staticLink.pattern, this.rest);
      }
      
      Object lambda196(Object param2Object1, Object param2Object2) {
        return srfi13.$PcKmpSearch(this.staticLink.pattern, this.staticLink.text, unicode.char$Mnci$Eq$Qu, param2Object1, param2Object2, this.t$Mnstart, this.t$Mnend);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 168) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match2(ModuleMethod param2ModuleMethod, Object param2Object1, Object param2Object2, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 169) {
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
  
  public class frame86 extends ModuleBody {
    final ModuleMethod lambda$Fn195 = new ModuleMethod(this, 168, null, 0);
    
    final ModuleMethod lambda$Fn196 = new ModuleMethod(this, 169, null, 8194);
    
    Object rest;
    
    srfi13.frame85 staticLink;
    
    Object t$Mnend;
    
    Object t$Mnstart;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 168) ? lambda195() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 169) ? lambda196(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda195() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncontains$Mnci, this.staticLink.pattern, this.rest);
    }
    
    Object lambda196(Object param1Object1, Object param1Object2) {
      return srfi13.$PcKmpSearch(this.staticLink.pattern, this.staticLink.text, unicode.char$Mnci$Eq$Qu, param1Object1, param1Object2, this.t$Mnstart, this.t$Mnend);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 168) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 169) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame87 extends ModuleBody {
    final ModuleMethod lambda$Fn197;
    
    Object pattern;
    
    public frame87() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 172, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1399");
      this.lambda$Fn197 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 172) ? lambda197(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda197(Object param1Object) {
      return srfi13.stringParseStart$PlEnd(srfi13.make$Mnkmp$Mnrestart$Mnvector, this.pattern, param1Object);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 172) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame88 extends ModuleBody {
    final ModuleMethod lambda$Fn198;
    
    final ModuleMethod lambda$Fn199;
    
    int patlen;
    
    Object s;
    
    public frame88() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 173, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1468");
      this.lambda$Fn198 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 174, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1472");
      this.lambda$Fn199 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply1(param1ModuleMethod, param1Object);
        case 173:
          return lambda198(param1Object);
        case 174:
          break;
      } 
      return lambda199(param1Object) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    Object lambda198(Object param1Object) {
      return srfi13.stringParseStart$PlEnd(srfi13.string$Mnkmp$Mnpartial$Mnsearch, this.s, param1Object);
    }
    
    boolean lambda199(Object param1Object) {
      boolean bool2 = numbers.isInteger(param1Object);
      boolean bool1 = bool2;
      if (bool2) {
        bool2 = numbers.isExact(param1Object);
        bool1 = bool2;
        if (bool2) {
          Object object = Scheme.numLEq.apply2(srfi13.Lit0, param1Object);
          try {
            bool2 = ((Boolean)object).booleanValue();
            bool1 = bool2;
            if (bool2)
              bool1 = ((Boolean)Scheme.numLss.apply2(param1Object, Integer.valueOf(this.patlen))).booleanValue(); 
            return bool1;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "x", -2, object);
          } 
        } 
      } 
      return bool1;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match1(param1ModuleMethod, param1Object, param1CallContext);
        case 174:
          param1CallContext.value1 = param1Object;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 1;
          return 0;
        case 173:
          break;
      } 
      param1CallContext.value1 = param1Object;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 1;
      return 0;
    }
  }
  
  public class frame89 extends ModuleBody {
    final ModuleMethod lambda$Fn200 = new ModuleMethod(this, 175, null, 0);
    
    final ModuleMethod lambda$Fn201 = new ModuleMethod(this, 176, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 175) ? lambda200() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 176) ? lambda201(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda200() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreverse, this.s, this.maybe$Mnstart$Plend);
    }
    
    CharSequence lambda201(Object param1Object1, Object param1Object2) {
      CharSequence charSequence;
      param1Object2 = AddOp.$Mn.apply2(param1Object2, param1Object1);
      try {
        int i = ((Number)param1Object2).intValue();
        charSequence = strings.makeString(i);
        Object object = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
        param1Object2 = param1Object1;
        param1Object1 = object;
        while (Scheme.numLss.apply2(param1Object1, srfi13.Lit0) == Boolean.FALSE) {
          try {
            CharSeq charSeq = (CharSeq)charSequence;
            try {
              i = ((Number)param1Object1).intValue();
              object = this.s;
              try {
                CharSequence charSequence1 = (CharSequence)object;
                try {
                  int j = ((Number)param1Object2).intValue();
                  strings.stringSet$Ex(charSeq, i, strings.stringRef(charSequence1, j));
                  param1Object2 = AddOp.$Pl.apply2(param1Object2, srfi13.Lit1);
                  param1Object1 = AddOp.$Mn.apply2(param1Object1, srfi13.Lit1);
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException1, "string-ref", 2, param1Object2);
                } 
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string-ref", 1, object);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-set!", 2, classCastException1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "string-set!", 1, charSequence);
          } 
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "make-string", 1, classCastException);
      } 
      return charSequence;
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 175) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 176) {
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
    Object criterion;
    
    final ModuleMethod lambda$Fn23 = new ModuleMethod(this, 20, null, 0);
    
    final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 21, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 20) ? lambda23() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 21) ? lambda24(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda23() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnevery, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda24(Object param1Object1, Object param1Object2) {
      if (characters.isChar(this.criterion))
        while (true) {
          Object object = Scheme.numGEq.apply2(param1Object1, param1Object2);
          try {
            boolean bool = ((Boolean)object).booleanValue();
            if (bool)
              return bool ? Boolean.TRUE : Boolean.FALSE; 
            Object object1 = this.criterion;
            try {
              object = object1;
              object1 = this.s;
              try {
                CharSequence charSequence = (CharSequence)object1;
                try {
                  int i = ((Number)param1Object1).intValue();
                  bool = characters.isChar$Eq((Char)object, Char.make(strings.stringRef(charSequence, i)));
                  if (bool) {
                    param1Object1 = AddOp.$Pl.apply2(param1Object1, srfi13.Lit1);
                    continue;
                  } 
                  return bool ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException classCastException2) {
                  throw new WrongType(classCastException2, "string-ref", 2, param1Object1);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException1, "string-ref", 1, object1);
              } 
            } catch (ClassCastException null) {
              throw new WrongType(classCastException1, "char=?", 1, object1);
            } 
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "x", -2, object);
          } 
        }  
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$char$Mnset$Qu;
      try {
        Object object2 = location.get();
        if (applyToArgs.apply2(object2, this.criterion) != Boolean.FALSE)
          while (true) {
            Object object = Scheme.numGEq.apply2(classCastException1, classCastException2);
            try {
              boolean bool = ((Boolean)object).booleanValue();
              if (bool)
                return bool ? Boolean.TRUE : Boolean.FALSE; 
              object = Scheme.applyToArgs;
              object2 = srfi13.loc$char$Mnset$Mncontains$Qu;
              try {
                Object object3 = object2.get();
                Object object4 = this.criterion;
                object2 = this.s;
                try {
                  CharSequence charSequence = (CharSequence)object2;
                  try {
                    int i = ((Number)classCastException1).intValue();
                    object = object.apply3(object3, object4, Char.make(strings.stringRef(charSequence, i)));
                    if (object != Boolean.FALSE) {
                      object1 = AddOp.$Pl.apply2(classCastException1, srfi13.Lit1);
                      continue;
                    } 
                    return object;
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "string-ref", 2, object1);
                  } 
                } catch (ClassCastException null) {
                  throw new WrongType(object1, "string-ref", 1, object2);
                } 
              } catch (UnboundLocationException null) {
                object1.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 492, 9);
                throw object1;
              } 
            } catch (ClassCastException object1) {
              throw new WrongType(object1, "x", -2, object);
            } 
          }  
        if (misc.isProcedure(this.criterion)) {
          Object object = Scheme.numEqu.apply2(object1, classCastException);
          try {
            boolean bool = ((Boolean)object).booleanValue();
            if (bool)
              return bool ? Boolean.TRUE : Boolean.FALSE; 
            while (true) {
              object = this.s;
              try {
                object2 = object;
                try {
                  int i = ((Number)object1).intValue();
                  i = strings.stringRef((CharSequence)object2, i);
                  object1 = AddOp.$Pl.apply2(object1, srfi13.Lit1);
                  if (Scheme.numEqu.apply2(object1, classCastException) != Boolean.FALSE)
                    return Scheme.applyToArgs.apply2(this.criterion, Char.make(i)); 
                  object = Scheme.applyToArgs.apply2(this.criterion, Char.make(i));
                  if (object != Boolean.FALSE)
                    continue; 
                  return object;
                } catch (ClassCastException classCastException3) {
                  throw new WrongType(classCastException3, "string-ref", 2, object1);
                } 
              } catch (ClassCastException classCastException3) {
                throw new WrongType(classCastException3, "string-ref", 1, object);
              } 
            } 
          } catch (ClassCastException classCastException3) {
            throw new WrongType(classCastException3, "x", -2, object);
          } 
        } 
        return misc.error$V("Second param is neither char-set, char, or predicate procedure.", new Object[] { srfi13.string$Mnevery, this.criterion });
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 489, 5);
        throw unboundLocationException;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 20) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 21) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame90 extends ModuleBody {
    final ModuleMethod lambda$Fn202 = new ModuleMethod(this, 177, null, 0);
    
    final ModuleMethod lambda$Fn203 = new ModuleMethod(this, 178, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 177) ? lambda202() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 178) ? lambda203(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda202() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreverse$Ex, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda203(Object param1Object1, Object param1Object2) {
      param1Object2 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
      while (true) {
        if (Scheme.numLEq.apply2(param1Object2, param1Object1) == Boolean.FALSE) {
          Object object = this.s;
          try {
            CharSequence charSequence = (CharSequence)object;
            try {
              int i = ((Number)param1Object2).intValue();
              char c = strings.stringRef(charSequence, i);
              Object object1 = this.s;
              try {
                object = object1;
                try {
                  i = ((Number)param1Object2).intValue();
                  object1 = this.s;
                  try {
                    CharSequence charSequence1 = (CharSequence)object1;
                    try {
                      int j = ((Number)param1Object1).intValue();
                      strings.stringSet$Ex((CharSeq)object, i, strings.stringRef(charSequence1, j));
                      object = this.s;
                      try {
                        object1 = object;
                        try {
                          i = ((Number)param1Object1).intValue();
                          strings.stringSet$Ex((CharSeq)object1, i, c);
                          param1Object2 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
                          param1Object1 = AddOp.$Pl.apply2(param1Object1, srfi13.Lit1);
                        } catch (ClassCastException null) {
                          throw new WrongType(classCastException, "string-set!", 2, param1Object1);
                        } 
                      } catch (ClassCastException classCastException1) {
                        throw new WrongType(classCastException1, "string-set!", 1, object);
                      } 
                    } catch (ClassCastException classCastException) {
                      throw new WrongType(classCastException, "string-ref", 2, classCastException1);
                    } 
                  } catch (ClassCastException classCastException1) {
                    throw new WrongType(classCastException1, "string-ref", 1, object1);
                  } 
                } catch (ClassCastException classCastException1) {
                  throw new WrongType(classCastException1, "string-set!", 2, classCastException);
                } 
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string-set!", 1, object1);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "string-ref", 2, classCastException);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 1, object);
          } 
        } 
        return Values.empty;
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 177) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 178) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame91 extends ModuleBody {
    final ModuleMethod lambda$Fn204 = new ModuleMethod(this, 179, null, 0);
    
    final ModuleMethod lambda$Fn205 = new ModuleMethod(this, 180, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 179) ? lambda204() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 180) ? lambda205(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda204() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mn$Grlist, this.s, this.maybe$Mnstart$Plend);
    }
    
    Object lambda205(Object param1Object1, Object param1Object2) {
      Pair pair;
      param1Object2 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
      LList lList = LList.Empty;
      while (Scheme.numLss.apply2(param1Object2, param1Object1) == Boolean.FALSE) {
        Object object1 = AddOp.$Mn.apply2(param1Object2, srfi13.Lit1);
        Object object2 = this.s;
        try {
          CharSequence charSequence = (CharSequence)object2;
          try {
            int i = ((Number)param1Object2).intValue();
            pair = lists.cons(Char.make(strings.stringRef(charSequence, i)), lList);
            param1Object2 = object1;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, param1Object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 1, object2);
        } 
      } 
      return pair;
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 179) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 180) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame92 extends ModuleBody {
    Object end1;
    
    final ModuleMethod lambda$Fn206 = new ModuleMethod(this, 181, null, 0);
    
    final ModuleMethod lambda$Fn207 = new ModuleMethod(this, 182, null, 8194);
    
    LList maybe$Mnstart$Plend;
    
    Object s1;
    
    Object s2;
    
    Object start1;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 181) ? lambda206() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 182) ? lambda207(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda206() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreplace, this.s2, this.maybe$Mnstart$Plend);
    }
    
    CharSequence lambda207(Object param1Object1, Object param1Object2) {
      Object object = this.s1;
      try {
        CharSequence charSequence = (CharSequence)object;
        int i = strings.stringLength(charSequence);
        Object object1 = AddOp.$Mn.apply2(param1Object2, param1Object1);
        object = AddOp.$Pl.apply2(AddOp.$Mn.apply2(Integer.valueOf(i), AddOp.$Mn.apply2(this.end1, this.start1)), object1);
        try {
          int j = ((Number)object).intValue();
          object = strings.makeString(j);
          Object object2 = this.s1;
          try {
            CharSequence charSequence1 = (CharSequence)object2;
            object2 = this.start1;
            try {
              j = ((Number)object2).intValue();
              srfi13.$PcStringCopy$Ex((CharSequence)object, 0, charSequence1, 0, j);
              object2 = this.start1;
              try {
                j = ((Number)object2).intValue();
                object2 = this.s2;
                try {
                  charSequence1 = (CharSequence)object2;
                  try {
                    int k = ((Number)param1Object1).intValue();
                    try {
                      int m = ((Number)param1Object2).intValue();
                      srfi13.$PcStringCopy$Ex((CharSequence)object, j, charSequence1, k, m);
                      param1Object1 = AddOp.$Pl.apply2(this.start1, object1);
                      try {
                        j = ((Number)param1Object1).intValue();
                        param1Object1 = this.s1;
                        try {
                          param1Object2 = param1Object1;
                          param1Object1 = this.end1;
                          try {
                            k = ((Number)param1Object1).intValue();
                            srfi13.$PcStringCopy$Ex((CharSequence)object, j, (CharSequence)param1Object2, k, i);
                            return (CharSequence)object;
                          } catch (ClassCastException null) {
                            throw new WrongType(classCastException1, "%string-copy!", 3, param1Object1);
                          } 
                        } catch (ClassCastException null) {
                          throw new WrongType(classCastException1, "%string-copy!", 2, param1Object1);
                        } 
                      } catch (ClassCastException classCastException1) {
                        throw new WrongType(classCastException1, "%string-copy!", 1, param1Object1);
                      } 
                    } catch (ClassCastException classCastException) {
                      throw new WrongType(classCastException, "%string-copy!", 4, classCastException1);
                    } 
                  } catch (ClassCastException classCastException1) {
                    throw new WrongType(classCastException1, "%string-copy!", 3, classCastException);
                  } 
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "%string-copy!", 2, object2);
                } 
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "%string-copy!", 1, object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "%string-copy!", 4, object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "%string-copy!", 2, object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-string", 1, object);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-length", 1, object);
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 181) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 182) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame93 extends ModuleBody {
    final ModuleMethod lambda$Fn208 = new ModuleMethod(this, 183, null, 0);
    
    final ModuleMethod lambda$Fn209 = new ModuleMethod(this, 184, null, 8194);
    
    Object s;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 183) ? lambda208() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 184) ? lambda209(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    Object lambda208() {
      ModuleMethod moduleMethod = srfi13.string$Mntokenize;
      Object object = this.s;
      Location location = srfi13.loc$rest;
      try {
        Object object1 = location.get();
        return srfi13.stringParseFinalStart$PlEnd(moduleMethod, object, object1);
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1696, 57);
        throw unboundLocationException;
      } 
    }
    
    Object lambda209(Object param1Object1, Object param1Object2) {
      // Byte code:
      //   0: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
      //   3: astore #7
      //   5: aload_2
      //   6: astore #6
      //   8: aload #7
      //   10: astore_2
      //   11: getstatic kawa/standard/Scheme.numLss : Lgnu/kawa/functions/NumberCompare;
      //   14: aload_1
      //   15: aload #6
      //   17: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   20: astore #7
      //   22: aload #7
      //   24: checkcast java/lang/Boolean
      //   27: invokevirtual booleanValue : ()Z
      //   30: istore #5
      //   32: iload #5
      //   34: ifeq -> 210
      //   37: aload_0
      //   38: getfield s : Ljava/lang/Object;
      //   41: astore #7
      //   43: getstatic gnu/kawa/slib/srfi13.loc$token$Mnchars : Lgnu/mapping/Location;
      //   46: astore #8
      //   48: aload #8
      //   50: invokevirtual get : ()Ljava/lang/Object;
      //   53: astore #8
      //   55: aload #7
      //   57: aload #8
      //   59: iconst_2
      //   60: anewarray java/lang/Object
      //   63: dup
      //   64: iconst_0
      //   65: aload_1
      //   66: aastore
      //   67: dup
      //   68: iconst_1
      //   69: aload #6
      //   71: aastore
      //   72: invokestatic stringIndexRight$V : (Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   75: astore #6
      //   77: aload_2
      //   78: astore #7
      //   80: aload #6
      //   82: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   85: if_acmpeq -> 276
      //   88: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   91: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
      //   94: aload #6
      //   96: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   99: astore #7
      //   101: aload_0
      //   102: getfield s : Ljava/lang/Object;
      //   105: astore #8
      //   107: getstatic gnu/kawa/slib/srfi13.loc$token$Mnchars : Lgnu/mapping/Location;
      //   110: astore #9
      //   112: aload #9
      //   114: invokevirtual get : ()Ljava/lang/Object;
      //   117: astore #9
      //   119: aload #8
      //   121: aload #9
      //   123: iconst_2
      //   124: anewarray java/lang/Object
      //   127: dup
      //   128: iconst_0
      //   129: aload_1
      //   130: aastore
      //   131: dup
      //   132: iconst_1
      //   133: aload #6
      //   135: aastore
      //   136: invokestatic stringSkipRight$V : (Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   139: astore #6
      //   141: aload #6
      //   143: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   146: if_acmpeq -> 231
      //   149: aload_0
      //   150: getfield s : Ljava/lang/Object;
      //   153: astore #8
      //   155: aload #8
      //   157: checkcast java/lang/CharSequence
      //   160: astore #9
      //   162: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   165: getstatic gnu/kawa/slib/srfi13.Lit1 : Lgnu/math/IntNum;
      //   168: aload #6
      //   170: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   173: astore #8
      //   175: aload #8
      //   177: checkcast java/lang/Number
      //   180: invokevirtual intValue : ()I
      //   183: istore_3
      //   184: aload #7
      //   186: checkcast java/lang/Number
      //   189: invokevirtual intValue : ()I
      //   192: istore #4
      //   194: aload #9
      //   196: iload_3
      //   197: iload #4
      //   199: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   202: aload_2
      //   203: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
      //   206: astore_2
      //   207: goto -> 11
      //   210: iload #5
      //   212: ifeq -> 223
      //   215: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
      //   218: astore #6
      //   220: goto -> 77
      //   223: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   226: astore #6
      //   228: goto -> 77
      //   231: aload_0
      //   232: getfield s : Ljava/lang/Object;
      //   235: astore #6
      //   237: aload #6
      //   239: checkcast java/lang/CharSequence
      //   242: astore #8
      //   244: aload_1
      //   245: checkcast java/lang/Number
      //   248: invokevirtual intValue : ()I
      //   251: istore_3
      //   252: aload #7
      //   254: checkcast java/lang/Number
      //   257: invokevirtual intValue : ()I
      //   260: istore #4
      //   262: aload #8
      //   264: iload_3
      //   265: iload #4
      //   267: invokestatic substring : (Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   270: aload_2
      //   271: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
      //   274: astore #7
      //   276: aload #7
      //   278: areturn
      //   279: astore_1
      //   280: new gnu/mapping/WrongType
      //   283: dup
      //   284: aload_1
      //   285: ldc 'x'
      //   287: bipush #-2
      //   289: aload #7
      //   291: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   294: athrow
      //   295: astore_1
      //   296: aload_1
      //   297: ldc '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
      //   299: sipush #1698
      //   302: bipush #48
      //   304: invokevirtual setLine : (Ljava/lang/String;II)V
      //   307: aload_1
      //   308: athrow
      //   309: astore_1
      //   310: aload_1
      //   311: ldc '/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm'
      //   313: sipush #1701
      //   316: bipush #34
      //   318: invokevirtual setLine : (Ljava/lang/String;II)V
      //   321: aload_1
      //   322: athrow
      //   323: astore_1
      //   324: new gnu/mapping/WrongType
      //   327: dup
      //   328: aload_1
      //   329: ldc 'substring'
      //   331: iconst_1
      //   332: aload #8
      //   334: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   337: athrow
      //   338: astore_1
      //   339: new gnu/mapping/WrongType
      //   342: dup
      //   343: aload_1
      //   344: ldc 'substring'
      //   346: iconst_2
      //   347: aload #8
      //   349: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   352: athrow
      //   353: astore_1
      //   354: new gnu/mapping/WrongType
      //   357: dup
      //   358: aload_1
      //   359: ldc 'substring'
      //   361: iconst_3
      //   362: aload #7
      //   364: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   367: athrow
      //   368: astore_1
      //   369: new gnu/mapping/WrongType
      //   372: dup
      //   373: aload_1
      //   374: ldc 'substring'
      //   376: iconst_1
      //   377: aload #6
      //   379: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   382: athrow
      //   383: astore_2
      //   384: new gnu/mapping/WrongType
      //   387: dup
      //   388: aload_2
      //   389: ldc 'substring'
      //   391: iconst_2
      //   392: aload_1
      //   393: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   396: athrow
      //   397: astore_1
      //   398: new gnu/mapping/WrongType
      //   401: dup
      //   402: aload_1
      //   403: ldc 'substring'
      //   405: iconst_3
      //   406: aload #7
      //   408: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   411: athrow
      // Exception table:
      //   from	to	target	type
      //   22	32	279	java/lang/ClassCastException
      //   48	55	295	gnu/mapping/UnboundLocationException
      //   112	119	309	gnu/mapping/UnboundLocationException
      //   155	162	323	java/lang/ClassCastException
      //   175	184	338	java/lang/ClassCastException
      //   184	194	353	java/lang/ClassCastException
      //   237	244	368	java/lang/ClassCastException
      //   244	252	383	java/lang/ClassCastException
      //   252	262	397	java/lang/ClassCastException
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 183) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 184) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
  }
  
  public class frame94 extends ModuleBody {
    Object from;
    
    final ModuleMethod lambda$Fn211;
    
    final ModuleMethod lambda$Fn212 = new ModuleMethod(this, 185, null, 0);
    
    final ModuleMethod lambda$Fn213;
    
    final ModuleMethod lambda$Fn214;
    
    final ModuleMethod lambda$Fn215;
    
    LList maybe$Mnto$Plstart$Plend;
    
    Object s;
    
    public frame94() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 186, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1744");
      this.lambda$Fn214 = moduleMethod;
      this.lambda$Fn213 = new ModuleMethod(this, 187, null, 8194);
      this.lambda$Fn211 = new ModuleMethod(this, 188, null, 0);
      moduleMethod = new ModuleMethod(this, 189, null, 12291);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1740");
      this.lambda$Fn215 = moduleMethod;
    }
    
    static boolean lambda210(Object param1Object) {
      boolean bool2 = numbers.isInteger(param1Object);
      boolean bool1 = bool2;
      if (bool2)
        bool1 = numbers.isExact(param1Object); 
      return bool1;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply0(param1ModuleMethod);
        case 185:
          return lambda212();
        case 188:
          break;
      } 
      return lambda211();
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 186) ? (lambda214(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 187) ? lambda213(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 189) ? lambda215(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda211() {
      if (lists.isPair(this.maybe$Mnto$Plstart$Plend))
        return call_with_values.callWithValues((Procedure)this.lambda$Fn212, (Procedure)this.lambda$Fn213); 
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$check$Mnarg;
      try {
        Object object2 = location.get();
        Object object1 = applyToArgs.apply4(object2, strings.string$Qu, this.s, srfi13.xsubstring);
        try {
          object2 = object1;
          int i = strings.stringLength((CharSequence)object2);
          return misc.values(new Object[] { AddOp.$Pl.apply2(this.from, Integer.valueOf(i)), srfi13.Lit0, Integer.valueOf(i) });
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-length", 1, object1);
        } 
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1749, 36);
        throw unboundLocationException;
      } 
    }
    
    Object lambda212() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.xsubstring, this.s, lists.cdr.apply1(this.maybe$Mnto$Plstart$Plend));
    }
    
    Object lambda213(Object param1Object1, Object param1Object2) {
      Object object = lists.car.apply1(this.maybe$Mnto$Plstart$Plend);
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$check$Mnarg;
      try {
        Object object1 = location.get();
        applyToArgs.apply4(object1, this.lambda$Fn214, object, srfi13.xsubstring);
        return misc.values(new Object[] { object, param1Object1, param1Object2 });
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1744, 6);
        throw unboundLocationException;
      } 
    }
    
    boolean lambda214(Object param1Object) {
      boolean bool2 = numbers.isInteger(param1Object);
      boolean bool1 = bool2;
      if (bool2) {
        bool2 = numbers.isExact(param1Object);
        bool1 = bool2;
        if (bool2)
          bool1 = ((Boolean)Scheme.numLEq.apply2(this.from, param1Object)).booleanValue(); 
      } 
      return bool1;
    }
    
    Object lambda215(Object param1Object1, Object param1Object2, Object param1Object3) {
      Object object1 = AddOp.$Mn.apply2(param1Object3, param1Object2);
      Object object2 = AddOp.$Mn.apply2(param1Object1, this.from);
      try {
        Number number = (Number)object2;
        if (numbers.isZero(number))
          return ""; 
        try {
          number = (Number)object1;
          if (numbers.isZero(number))
            return misc.error$V("Cannot replicate empty (sub)string", new Object[] { srfi13.xsubstring, this.s, this.from, param1Object1, param1Object2, param1Object3 }); 
          if (Scheme.numEqu.apply2(srfi13.Lit1, object1) != Boolean.FALSE)
            try {
              int i = ((Number)object2).intValue();
              param1Object1 = this.s;
              try {
                param1Object3 = param1Object1;
                try {
                  int j = ((Number)param1Object2).intValue();
                  return strings.makeString(i, Char.make(strings.stringRef((CharSequence)param1Object3, j)));
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException1, "string-ref", 2, param1Object2);
                } 
              } catch (ClassCastException classCastException2) {
                throw new WrongType(classCastException2, "string-ref", 1, classCastException1);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "make-string", 1, object2);
            }  
          Object object = DivideOp.$Sl.apply2(this.from, object1);
          try {
            RealNum realNum = LangObjType.coerceRealNum(object);
            double d = numbers.floor(realNum).doubleValue();
            object = DivideOp.$Sl.apply2(classCastException1, object1);
            try {
              realNum = LangObjType.coerceRealNum(object);
              if (d == numbers.floor(realNum).doubleValue()) {
                object2 = this.s;
                try {
                  param1Object3 = object2;
                  object2 = AddOp.$Pl.apply2(classCastException2, DivideOp.modulo.apply2(this.from, object1));
                  try {
                    int i = ((Number)object2).intValue();
                    object3 = AddOp.$Pl.apply2(classCastException2, DivideOp.modulo.apply2(classCastException1, object1));
                    try {
                      int j = ((Number)object3).intValue();
                      return strings.substring((CharSequence)param1Object3, i, j);
                    } catch (ClassCastException classCastException) {
                      throw new WrongType(classCastException, "substring", 3, object3);
                    } 
                  } catch (ClassCastException null) {
                    throw new WrongType(object3, "substring", 2, object2);
                  } 
                } catch (ClassCastException object3) {
                  throw new WrongType(object3, "substring", 1, object2);
                } 
              } 
              try {
                int i = ((Number)object2).intValue();
                object1 = strings.makeString(i);
                srfi13.$PcMultispanRepcopy$Ex(object1, srfi13.Lit0, this.s, this.from, object3, classCastException, param1Object3);
                return object1;
              } catch (ClassCastException classCastException3) {
                throw new WrongType(classCastException3, "make-string", 1, object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "floor", 1, object);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "floor", 1, object);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "zero?", 1, object1);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "zero?", 1, object2);
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match0(param1ModuleMethod, param1CallContext);
        case 188:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 185:
          break;
      } 
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 0;
      return 0;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 186) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 187) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 189) {
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
  
  public class frame95 extends ModuleBody {
    final ModuleMethod lambda$Fn217 = new ModuleMethod(this, 192, null, 0);
    
    final ModuleMethod lambda$Fn218 = new ModuleMethod(this, 190, null, 0);
    
    final ModuleMethod lambda$Fn219 = new ModuleMethod(this, 191, null, 8194);
    
    final ModuleMethod lambda$Fn221;
    
    LList maybe$Mnsto$Plstart$Plend;
    
    Object s;
    
    Object sfrom;
    
    Object target;
    
    Object tstart;
    
    public frame95() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 193, null, 12291);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1781");
      this.lambda$Fn221 = moduleMethod;
    }
    
    static boolean lambda216(Object param1Object) {
      boolean bool2 = numbers.isInteger(param1Object);
      boolean bool1 = bool2;
      if (bool2)
        bool1 = numbers.isExact(param1Object); 
      return bool1;
    }
    
    static boolean lambda220(Object param1Object) {
      boolean bool2 = numbers.isInteger(param1Object);
      boolean bool1 = bool2;
      if (bool2)
        bool1 = numbers.isExact(param1Object); 
      return bool1;
    }
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply0(param1ModuleMethod);
        case 190:
          return lambda218();
        case 192:
          break;
      } 
      return lambda217();
    }
    
    public Object apply2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2) {
      return (param1ModuleMethod.selector == 191) ? lambda219(param1Object1, param1Object2) : super.apply2(param1ModuleMethod, param1Object1, param1Object2);
    }
    
    public Object apply3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3) {
      return (param1ModuleMethod.selector == 193) ? lambda221(param1Object1, param1Object2, param1Object3) : super.apply3(param1ModuleMethod, param1Object1, param1Object2, param1Object3);
    }
    
    Object lambda217() {
      if (lists.isPair(this.maybe$Mnsto$Plstart$Plend))
        return call_with_values.callWithValues((Procedure)this.lambda$Fn218, (Procedure)this.lambda$Fn219); 
      Object object = this.s;
      try {
        CharSequence charSequence = (CharSequence)object;
        int i = strings.stringLength(charSequence);
        return misc.values(new Object[] { AddOp.$Pl.apply2(this.sfrom, Integer.valueOf(i)), srfi13.Lit0, Integer.valueOf(i) });
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-length", 1, object);
      } 
    }
    
    Object lambda218() {
      return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnxcopy$Ex, this.s, lists.cdr.apply1(this.maybe$Mnsto$Plstart$Plend));
    }
    
    Object lambda219(Object param1Object1, Object param1Object2) {
      Object object = lists.car.apply1(this.maybe$Mnsto$Plstart$Plend);
      ApplyToArgs applyToArgs = Scheme.applyToArgs;
      Location location = srfi13.loc$check$Mnarg;
      try {
        Object object1 = location.get();
        applyToArgs.apply4(object1, srfi13.lambda$Fn220, object, srfi13.string$Mnxcopy$Ex);
        return misc.values(new Object[] { object, param1Object1, param1Object2 });
      } catch (UnboundLocationException unboundLocationException) {
        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1785, 6);
        throw unboundLocationException;
      } 
    }
    
    Object lambda221(Object param1Object1, Object param1Object2, Object param1Object3) {
      Object object3 = AddOp.$Mn.apply2(param1Object1, this.sfrom);
      Object object2 = AddOp.$Pl.apply2(this.tstart, object3);
      Object object1 = AddOp.$Mn.apply2(param1Object3, param1Object2);
      srfi13.checkSubstringSpec(srfi13.string$Mnxcopy$Ex, this.target, this.tstart, object2);
      try {
        Number number = (Number)object3;
        boolean bool = numbers.isZero(number);
        if (bool)
          return bool ? Boolean.TRUE : Boolean.FALSE; 
        try {
          object3 = object1;
          if (numbers.isZero((Number)object3))
            return misc.error$V("Cannot replicate empty (sub)string", new Object[] { srfi13.string$Mnxcopy$Ex, this.target, this.tstart, this.s, this.sfrom, param1Object1, param1Object2, param1Object3 }); 
          if (Scheme.numEqu.apply2(srfi13.Lit1, object1) != Boolean.FALSE) {
            param1Object3 = this.target;
            param1Object1 = this.s;
            try {
              object1 = param1Object1;
              try {
                int i = ((Number)param1Object2).intValue();
                return srfi13.stringFill$Ex$V(param1Object3, Char.make(strings.stringRef((CharSequence)object1, i)), new Object[] { this.tstart, object2 });
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "string-ref", 2, param1Object2);
              } 
            } catch (ClassCastException classCastException2) {
              throw new WrongType(classCastException2, "string-ref", 1, classCastException1);
            } 
          } 
          object2 = DivideOp.$Sl.apply2(this.sfrom, object1);
          try {
            object3 = LangObjType.coerceRealNum(object2);
            double d = numbers.floor((RealNum)object3).doubleValue();
            object2 = DivideOp.$Sl.apply2(classCastException1, object1);
            try {
              object3 = LangObjType.coerceRealNum(object2);
              if (d == numbers.floor((RealNum)object3).doubleValue()) {
                object2 = this.target;
                try {
                  param1Object3 = object2;
                  object2 = this.tstart;
                  try {
                    int i = ((Number)object2).intValue();
                    object3 = this.s;
                    try {
                      object2 = object3;
                      object3 = AddOp.$Pl.apply2(classCastException2, DivideOp.modulo.apply2(this.sfrom, object1));
                      try {
                        int j = ((Number)object3).intValue();
                        object = AddOp.$Pl.apply2(classCastException2, DivideOp.modulo.apply2(classCastException1, object1));
                        try {
                          int k = ((Number)object).intValue();
                          return srfi13.$PcStringCopy$Ex((CharSequence)param1Object3, i, (CharSequence)object2, j, k);
                        } catch (ClassCastException classCastException) {
                          throw new WrongType(classCastException, "%string-copy!", 4, object);
                        } 
                      } catch (ClassCastException null) {
                        throw new WrongType(object, "%string-copy!", 3, object3);
                      } 
                    } catch (ClassCastException null) {
                      throw new WrongType(object, "%string-copy!", 2, object3);
                    } 
                  } catch (ClassCastException null) {
                    throw new WrongType(object, "%string-copy!", 1, object2);
                  } 
                } catch (ClassCastException object) {
                  throw new WrongType(object, "%string-copy!", 0, object2);
                } 
              } 
              return srfi13.$PcMultispanRepcopy$Ex(this.target, this.tstart, this.s, this.sfrom, object, classCastException, param1Object3);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "floor", 1, object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "floor", 1, object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "zero?", 1, object1);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "zero?", 1, object3);
      } 
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match0(param1ModuleMethod, param1CallContext);
        case 192:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 190:
          break;
      } 
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 0;
      return 0;
    }
    
    public int match2(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 191) {
        param1CallContext.value1 = param1Object1;
        param1CallContext.value2 = param1Object2;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 2;
        return 0;
      } 
      return super.match2(param1ModuleMethod, param1Object1, param1Object2, param1CallContext);
    }
    
    public int match3(ModuleMethod param1ModuleMethod, Object param1Object1, Object param1Object2, Object param1Object3, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 193) {
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
  
  public class frame96 extends ModuleBody {
    Object final;
    
    public Object lambda223recur(Object param1Object) {
      if (lists.isPair(param1Object)) {
        Location location = srfi13.loc$delim;
        try {
          Object object = location.get();
          return lists.cons(object, lists.cons(lists.car.apply1(param1Object), lambda223recur(lists.cdr.apply1(param1Object))));
        } catch (UnboundLocationException unboundLocationException) {
          unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1857, 13);
          throw unboundLocationException;
        } 
      } 
      return this.final;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/srfi13.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.F32Vector;
import gnu.lists.F64Vector;
import gnu.lists.LList;
import gnu.lists.S16Vector;
import gnu.lists.S32Vector;
import gnu.lists.S64Vector;
import gnu.lists.S8Vector;
import gnu.lists.Sequence;
import gnu.lists.U16Vector;
import gnu.lists.U32Vector;
import gnu.lists.U64Vector;
import gnu.lists.U8Vector;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import java.util.List;

public class uniform extends ModuleBody {
  public static final uniform $instance;
  
  static final IntNum Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
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
  
  static final SimpleSymbol Lit80 = (SimpleSymbol)(new SimpleSymbol("list->f64vector")).readResolve();
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod f32vector;
  
  public static final ModuleMethod f32vector$Mn$Grlist;
  
  public static final ModuleMethod f32vector$Mnlength;
  
  public static final ModuleMethod f32vector$Mnref;
  
  public static final ModuleMethod f32vector$Mnset$Ex;
  
  public static final ModuleMethod f32vector$Qu;
  
  public static final ModuleMethod f64vector;
  
  public static final ModuleMethod f64vector$Mn$Grlist;
  
  public static final ModuleMethod f64vector$Mnlength;
  
  public static final ModuleMethod f64vector$Mnref;
  
  public static final ModuleMethod f64vector$Mnset$Ex;
  
  public static final ModuleMethod f64vector$Qu;
  
  public static final ModuleMethod list$Mn$Grf32vector;
  
  public static final ModuleMethod list$Mn$Grf64vector;
  
  public static final ModuleMethod list$Mn$Grs16vector;
  
  public static final ModuleMethod list$Mn$Grs32vector;
  
  public static final ModuleMethod list$Mn$Grs64vector;
  
  public static final ModuleMethod list$Mn$Grs8vector;
  
  public static final ModuleMethod list$Mn$Gru16vector;
  
  public static final ModuleMethod list$Mn$Gru32vector;
  
  public static final ModuleMethod list$Mn$Gru64vector;
  
  public static final ModuleMethod list$Mn$Gru8vector;
  
  public static final ModuleMethod make$Mnf32vector;
  
  public static final ModuleMethod make$Mnf64vector;
  
  public static final ModuleMethod make$Mns16vector;
  
  public static final ModuleMethod make$Mns32vector;
  
  public static final ModuleMethod make$Mns64vector;
  
  public static final ModuleMethod make$Mns8vector;
  
  public static final ModuleMethod make$Mnu16vector;
  
  public static final ModuleMethod make$Mnu32vector;
  
  public static final ModuleMethod make$Mnu64vector;
  
  public static final ModuleMethod make$Mnu8vector;
  
  public static final ModuleMethod s16vector;
  
  public static final ModuleMethod s16vector$Mn$Grlist;
  
  public static final ModuleMethod s16vector$Mnlength;
  
  public static final ModuleMethod s16vector$Mnref;
  
  public static final ModuleMethod s16vector$Mnset$Ex;
  
  public static final ModuleMethod s16vector$Qu;
  
  public static final ModuleMethod s32vector;
  
  public static final ModuleMethod s32vector$Mn$Grlist;
  
  public static final ModuleMethod s32vector$Mnlength;
  
  public static final ModuleMethod s32vector$Mnref;
  
  public static final ModuleMethod s32vector$Mnset$Ex;
  
  public static final ModuleMethod s32vector$Qu;
  
  public static final ModuleMethod s64vector;
  
  public static final ModuleMethod s64vector$Mn$Grlist;
  
  public static final ModuleMethod s64vector$Mnlength;
  
  public static final ModuleMethod s64vector$Mnref;
  
  public static final ModuleMethod s64vector$Mnset$Ex;
  
  public static final ModuleMethod s64vector$Qu;
  
  public static final ModuleMethod s8vector;
  
  public static final ModuleMethod s8vector$Mn$Grlist;
  
  public static final ModuleMethod s8vector$Mnlength;
  
  public static final ModuleMethod s8vector$Mnref;
  
  public static final ModuleMethod s8vector$Mnset$Ex;
  
  public static final ModuleMethod s8vector$Qu;
  
  public static final ModuleMethod u16vector;
  
  public static final ModuleMethod u16vector$Mn$Grlist;
  
  public static final ModuleMethod u16vector$Mnlength;
  
  public static final ModuleMethod u16vector$Mnref;
  
  public static final ModuleMethod u16vector$Mnset$Ex;
  
  public static final ModuleMethod u16vector$Qu;
  
  public static final ModuleMethod u32vector;
  
  public static final ModuleMethod u32vector$Mn$Grlist;
  
  public static final ModuleMethod u32vector$Mnlength;
  
  public static final ModuleMethod u32vector$Mnref;
  
  public static final ModuleMethod u32vector$Mnset$Ex;
  
  public static final ModuleMethod u32vector$Qu;
  
  public static final ModuleMethod u64vector;
  
  public static final ModuleMethod u64vector$Mn$Grlist;
  
  public static final ModuleMethod u64vector$Mnlength;
  
  public static final ModuleMethod u64vector$Mnref;
  
  public static final ModuleMethod u64vector$Mnset$Ex;
  
  public static final ModuleMethod u64vector$Qu;
  
  public static final ModuleMethod u8vector;
  
  public static final ModuleMethod u8vector$Mn$Grlist;
  
  public static final ModuleMethod u8vector$Mnlength;
  
  public static final ModuleMethod u8vector$Mnref;
  
  public static final ModuleMethod u8vector$Mnset$Ex;
  
  public static final ModuleMethod u8vector$Qu;
  
  static {
    Lit79 = (SimpleSymbol)(new SimpleSymbol("f64vector->list")).readResolve();
    Lit78 = (SimpleSymbol)(new SimpleSymbol("f64vector-set!")).readResolve();
    Lit77 = (SimpleSymbol)(new SimpleSymbol("f64vector-ref")).readResolve();
    Lit76 = (SimpleSymbol)(new SimpleSymbol("f64vector-length")).readResolve();
    Lit75 = (SimpleSymbol)(new SimpleSymbol("f64vector")).readResolve();
    Lit74 = (SimpleSymbol)(new SimpleSymbol("make-f64vector")).readResolve();
    Lit73 = (SimpleSymbol)(new SimpleSymbol("f64vector?")).readResolve();
    Lit72 = (SimpleSymbol)(new SimpleSymbol("list->f32vector")).readResolve();
    Lit71 = (SimpleSymbol)(new SimpleSymbol("f32vector->list")).readResolve();
    Lit70 = (SimpleSymbol)(new SimpleSymbol("f32vector-set!")).readResolve();
    Lit69 = (SimpleSymbol)(new SimpleSymbol("f32vector-ref")).readResolve();
    Lit68 = (SimpleSymbol)(new SimpleSymbol("f32vector-length")).readResolve();
    Lit67 = (SimpleSymbol)(new SimpleSymbol("f32vector")).readResolve();
    Lit66 = (SimpleSymbol)(new SimpleSymbol("make-f32vector")).readResolve();
    Lit65 = (SimpleSymbol)(new SimpleSymbol("f32vector?")).readResolve();
    Lit64 = (SimpleSymbol)(new SimpleSymbol("list->u64vector")).readResolve();
    Lit63 = (SimpleSymbol)(new SimpleSymbol("u64vector->list")).readResolve();
    Lit62 = (SimpleSymbol)(new SimpleSymbol("u64vector-set!")).readResolve();
    Lit61 = (SimpleSymbol)(new SimpleSymbol("u64vector-ref")).readResolve();
    Lit60 = (SimpleSymbol)(new SimpleSymbol("u64vector-length")).readResolve();
    Lit59 = (SimpleSymbol)(new SimpleSymbol("u64vector")).readResolve();
    Lit58 = (SimpleSymbol)(new SimpleSymbol("make-u64vector")).readResolve();
    Lit57 = (SimpleSymbol)(new SimpleSymbol("u64vector?")).readResolve();
    Lit56 = (SimpleSymbol)(new SimpleSymbol("list->s64vector")).readResolve();
    Lit55 = (SimpleSymbol)(new SimpleSymbol("s64vector->list")).readResolve();
    Lit54 = (SimpleSymbol)(new SimpleSymbol("s64vector-set!")).readResolve();
    Lit53 = (SimpleSymbol)(new SimpleSymbol("s64vector-ref")).readResolve();
    Lit52 = (SimpleSymbol)(new SimpleSymbol("s64vector-length")).readResolve();
    Lit51 = (SimpleSymbol)(new SimpleSymbol("s64vector")).readResolve();
    Lit50 = (SimpleSymbol)(new SimpleSymbol("make-s64vector")).readResolve();
    Lit49 = (SimpleSymbol)(new SimpleSymbol("s64vector?")).readResolve();
    Lit48 = (SimpleSymbol)(new SimpleSymbol("list->u32vector")).readResolve();
    Lit47 = (SimpleSymbol)(new SimpleSymbol("u32vector->list")).readResolve();
    Lit46 = (SimpleSymbol)(new SimpleSymbol("u32vector-set!")).readResolve();
    Lit45 = (SimpleSymbol)(new SimpleSymbol("u32vector-ref")).readResolve();
    Lit44 = (SimpleSymbol)(new SimpleSymbol("u32vector-length")).readResolve();
    Lit43 = (SimpleSymbol)(new SimpleSymbol("u32vector")).readResolve();
    Lit42 = (SimpleSymbol)(new SimpleSymbol("make-u32vector")).readResolve();
    Lit41 = (SimpleSymbol)(new SimpleSymbol("u32vector?")).readResolve();
    Lit40 = (SimpleSymbol)(new SimpleSymbol("list->s32vector")).readResolve();
    Lit39 = (SimpleSymbol)(new SimpleSymbol("s32vector->list")).readResolve();
    Lit38 = (SimpleSymbol)(new SimpleSymbol("s32vector-set!")).readResolve();
    Lit37 = (SimpleSymbol)(new SimpleSymbol("s32vector-ref")).readResolve();
    Lit36 = (SimpleSymbol)(new SimpleSymbol("s32vector-length")).readResolve();
    Lit35 = (SimpleSymbol)(new SimpleSymbol("s32vector")).readResolve();
    Lit34 = (SimpleSymbol)(new SimpleSymbol("make-s32vector")).readResolve();
    Lit33 = (SimpleSymbol)(new SimpleSymbol("s32vector?")).readResolve();
    Lit32 = (SimpleSymbol)(new SimpleSymbol("list->u16vector")).readResolve();
    Lit31 = (SimpleSymbol)(new SimpleSymbol("u16vector->list")).readResolve();
    Lit30 = (SimpleSymbol)(new SimpleSymbol("u16vector-set!")).readResolve();
    Lit29 = (SimpleSymbol)(new SimpleSymbol("u16vector-ref")).readResolve();
    Lit28 = (SimpleSymbol)(new SimpleSymbol("u16vector-length")).readResolve();
    Lit27 = (SimpleSymbol)(new SimpleSymbol("u16vector")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("make-u16vector")).readResolve();
    Lit25 = (SimpleSymbol)(new SimpleSymbol("u16vector?")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("list->s16vector")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("s16vector->list")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("s16vector-set!")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("s16vector-ref")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("s16vector-length")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("s16vector")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("make-s16vector")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("s16vector?")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("list->u8vector")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("u8vector->list")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("u8vector-set!")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("u8vector-ref")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("u8vector-length")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("u8vector")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("make-u8vector")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("u8vector?")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("list->s8vector")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("s8vector->list")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("s8vector-set!")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("s8vector-ref")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("s8vector-length")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("s8vector")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("make-s8vector")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("s8vector?")).readResolve();
    Lit0 = IntNum.make(0);
    $instance = new uniform();
    uniform uniform1 = $instance;
    s8vector$Qu = new ModuleMethod(uniform1, 1, Lit1, 4097);
    make$Mns8vector = new ModuleMethod(uniform1, 2, Lit2, 8193);
    s8vector = new ModuleMethod(uniform1, 4, Lit3, -4096);
    s8vector$Mnlength = new ModuleMethod(uniform1, 5, Lit4, 4097);
    s8vector$Mnref = new ModuleMethod(uniform1, 6, Lit5, 8194);
    s8vector$Mnset$Ex = new ModuleMethod(uniform1, 7, Lit6, 12291);
    s8vector$Mn$Grlist = new ModuleMethod(uniform1, 8, Lit7, 4097);
    list$Mn$Grs8vector = new ModuleMethod(uniform1, 9, Lit8, 4097);
    u8vector$Qu = new ModuleMethod(uniform1, 10, Lit9, 4097);
    make$Mnu8vector = new ModuleMethod(uniform1, 11, Lit10, 8193);
    u8vector = new ModuleMethod(uniform1, 13, Lit11, -4096);
    u8vector$Mnlength = new ModuleMethod(uniform1, 14, Lit12, 4097);
    u8vector$Mnref = new ModuleMethod(uniform1, 15, Lit13, 8194);
    u8vector$Mnset$Ex = new ModuleMethod(uniform1, 16, Lit14, 12291);
    u8vector$Mn$Grlist = new ModuleMethod(uniform1, 17, Lit15, 4097);
    list$Mn$Gru8vector = new ModuleMethod(uniform1, 18, Lit16, 4097);
    s16vector$Qu = new ModuleMethod(uniform1, 19, Lit17, 4097);
    make$Mns16vector = new ModuleMethod(uniform1, 20, Lit18, 8193);
    s16vector = new ModuleMethod(uniform1, 22, Lit19, -4096);
    s16vector$Mnlength = new ModuleMethod(uniform1, 23, Lit20, 4097);
    s16vector$Mnref = new ModuleMethod(uniform1, 24, Lit21, 8194);
    s16vector$Mnset$Ex = new ModuleMethod(uniform1, 25, Lit22, 12291);
    s16vector$Mn$Grlist = new ModuleMethod(uniform1, 26, Lit23, 4097);
    list$Mn$Grs16vector = new ModuleMethod(uniform1, 27, Lit24, 4097);
    u16vector$Qu = new ModuleMethod(uniform1, 28, Lit25, 4097);
    make$Mnu16vector = new ModuleMethod(uniform1, 29, Lit26, 8193);
    u16vector = new ModuleMethod(uniform1, 31, Lit27, -4096);
    u16vector$Mnlength = new ModuleMethod(uniform1, 32, Lit28, 4097);
    u16vector$Mnref = new ModuleMethod(uniform1, 33, Lit29, 8194);
    u16vector$Mnset$Ex = new ModuleMethod(uniform1, 34, Lit30, 12291);
    u16vector$Mn$Grlist = new ModuleMethod(uniform1, 35, Lit31, 4097);
    list$Mn$Gru16vector = new ModuleMethod(uniform1, 36, Lit32, 4097);
    s32vector$Qu = new ModuleMethod(uniform1, 37, Lit33, 4097);
    make$Mns32vector = new ModuleMethod(uniform1, 38, Lit34, 8193);
    s32vector = new ModuleMethod(uniform1, 40, Lit35, -4096);
    s32vector$Mnlength = new ModuleMethod(uniform1, 41, Lit36, 4097);
    s32vector$Mnref = new ModuleMethod(uniform1, 42, Lit37, 8194);
    s32vector$Mnset$Ex = new ModuleMethod(uniform1, 43, Lit38, 12291);
    s32vector$Mn$Grlist = new ModuleMethod(uniform1, 44, Lit39, 4097);
    list$Mn$Grs32vector = new ModuleMethod(uniform1, 45, Lit40, 4097);
    u32vector$Qu = new ModuleMethod(uniform1, 46, Lit41, 4097);
    make$Mnu32vector = new ModuleMethod(uniform1, 47, Lit42, 8193);
    u32vector = new ModuleMethod(uniform1, 49, Lit43, -4096);
    u32vector$Mnlength = new ModuleMethod(uniform1, 50, Lit44, 4097);
    u32vector$Mnref = new ModuleMethod(uniform1, 51, Lit45, 8194);
    u32vector$Mnset$Ex = new ModuleMethod(uniform1, 52, Lit46, 12291);
    u32vector$Mn$Grlist = new ModuleMethod(uniform1, 53, Lit47, 4097);
    list$Mn$Gru32vector = new ModuleMethod(uniform1, 54, Lit48, 4097);
    s64vector$Qu = new ModuleMethod(uniform1, 55, Lit49, 4097);
    make$Mns64vector = new ModuleMethod(uniform1, 56, Lit50, 8193);
    s64vector = new ModuleMethod(uniform1, 58, Lit51, -4096);
    s64vector$Mnlength = new ModuleMethod(uniform1, 59, Lit52, 4097);
    s64vector$Mnref = new ModuleMethod(uniform1, 60, Lit53, 8194);
    s64vector$Mnset$Ex = new ModuleMethod(uniform1, 61, Lit54, 12291);
    s64vector$Mn$Grlist = new ModuleMethod(uniform1, 62, Lit55, 4097);
    list$Mn$Grs64vector = new ModuleMethod(uniform1, 63, Lit56, 4097);
    u64vector$Qu = new ModuleMethod(uniform1, 64, Lit57, 4097);
    make$Mnu64vector = new ModuleMethod(uniform1, 65, Lit58, 8193);
    u64vector = new ModuleMethod(uniform1, 67, Lit59, -4096);
    u64vector$Mnlength = new ModuleMethod(uniform1, 68, Lit60, 4097);
    u64vector$Mnref = new ModuleMethod(uniform1, 69, Lit61, 8194);
    u64vector$Mnset$Ex = new ModuleMethod(uniform1, 70, Lit62, 12291);
    u64vector$Mn$Grlist = new ModuleMethod(uniform1, 71, Lit63, 4097);
    list$Mn$Gru64vector = new ModuleMethod(uniform1, 72, Lit64, 4097);
    f32vector$Qu = new ModuleMethod(uniform1, 73, Lit65, 4097);
    make$Mnf32vector = new ModuleMethod(uniform1, 74, Lit66, 8193);
    f32vector = new ModuleMethod(uniform1, 76, Lit67, -4096);
    f32vector$Mnlength = new ModuleMethod(uniform1, 77, Lit68, 4097);
    f32vector$Mnref = new ModuleMethod(uniform1, 78, Lit69, 8194);
    f32vector$Mnset$Ex = new ModuleMethod(uniform1, 79, Lit70, 12291);
    f32vector$Mn$Grlist = new ModuleMethod(uniform1, 80, Lit71, 4097);
    list$Mn$Grf32vector = new ModuleMethod(uniform1, 81, Lit72, 4097);
    f64vector$Qu = new ModuleMethod(uniform1, 82, Lit73, 4097);
    make$Mnf64vector = new ModuleMethod(uniform1, 83, Lit74, 8193);
    f64vector = new ModuleMethod(uniform1, 85, Lit75, -4096);
    f64vector$Mnlength = new ModuleMethod(uniform1, 86, Lit76, 4097);
    f64vector$Mnref = new ModuleMethod(uniform1, 87, Lit77, 8194);
    f64vector$Mnset$Ex = new ModuleMethod(uniform1, 88, Lit78, 12291);
    f64vector$Mn$Grlist = new ModuleMethod(uniform1, 89, Lit79, 4097);
    list$Mn$Grf64vector = new ModuleMethod(uniform1, 90, Lit80, 4097);
    $instance.run();
  }
  
  public uniform() {
    ModuleInfo.register(this);
  }
  
  public static LList f32vector$To$List(F32Vector paramF32Vector) {
    return LList.makeList((List)paramF32Vector);
  }
  
  public static F32Vector f32vector$V(Object[] paramArrayOfObject) {
    return list$To$F32vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int f32vectorLength(F32Vector paramF32Vector) {
    return paramF32Vector.size();
  }
  
  public static float f32vectorRef(F32Vector paramF32Vector, int paramInt) {
    return paramF32Vector.floatAt(paramInt);
  }
  
  public static void f32vectorSet$Ex(F32Vector paramF32Vector, int paramInt, float paramFloat) {
    paramF32Vector.setFloatAt(paramInt, paramFloat);
  }
  
  public static LList f64vector$To$List(F64Vector paramF64Vector) {
    return LList.makeList((List)paramF64Vector);
  }
  
  public static F64Vector f64vector$V(Object[] paramArrayOfObject) {
    return list$To$F64vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int f64vectorLength(F64Vector paramF64Vector) {
    return paramF64Vector.size();
  }
  
  public static double f64vectorRef(F64Vector paramF64Vector, int paramInt) {
    return paramF64Vector.doubleAt(paramInt);
  }
  
  public static void f64vectorSet$Ex(F64Vector paramF64Vector, int paramInt, double paramDouble) {
    paramF64Vector.setDoubleAt(paramInt, paramDouble);
  }
  
  public static boolean isF32vector(Object paramObject) {
    return paramObject instanceof F32Vector;
  }
  
  public static boolean isF64vector(Object paramObject) {
    return paramObject instanceof F64Vector;
  }
  
  public static boolean isS16vector(Object paramObject) {
    return paramObject instanceof S16Vector;
  }
  
  public static boolean isS32vector(Object paramObject) {
    return paramObject instanceof S32Vector;
  }
  
  public static boolean isS64vector(Object paramObject) {
    return paramObject instanceof S64Vector;
  }
  
  public static boolean isS8vector(Object paramObject) {
    return paramObject instanceof S8Vector;
  }
  
  public static boolean isU16vector(Object paramObject) {
    return paramObject instanceof U16Vector;
  }
  
  public static boolean isU32vector(Object paramObject) {
    return paramObject instanceof U32Vector;
  }
  
  public static boolean isU64vector(Object paramObject) {
    return paramObject instanceof U64Vector;
  }
  
  public static boolean isU8vector(Object paramObject) {
    return paramObject instanceof U8Vector;
  }
  
  public static F32Vector list$To$F32vector(LList paramLList) {
    return new F32Vector((Sequence)paramLList);
  }
  
  public static F64Vector list$To$F64vector(LList paramLList) {
    return new F64Vector((Sequence)paramLList);
  }
  
  public static S16Vector list$To$S16vector(LList paramLList) {
    return new S16Vector((Sequence)paramLList);
  }
  
  public static S32Vector list$To$S32vector(LList paramLList) {
    return new S32Vector((Sequence)paramLList);
  }
  
  public static S64Vector list$To$S64vector(LList paramLList) {
    return new S64Vector((Sequence)paramLList);
  }
  
  public static S8Vector list$To$S8vector(LList paramLList) {
    return new S8Vector((Sequence)paramLList);
  }
  
  public static U16Vector list$To$U16vector(LList paramLList) {
    return new U16Vector((Sequence)paramLList);
  }
  
  public static U32Vector list$To$U32vector(LList paramLList) {
    return new U32Vector((Sequence)paramLList);
  }
  
  public static U64Vector list$To$U64vector(LList paramLList) {
    return new U64Vector((Sequence)paramLList);
  }
  
  public static U8Vector list$To$U8vector(LList paramLList) {
    return new U8Vector((Sequence)paramLList);
  }
  
  public static F32Vector makeF32vector(int paramInt) {
    return makeF32vector(paramInt, 0.0F);
  }
  
  public static F32Vector makeF32vector(int paramInt, float paramFloat) {
    return new F32Vector(paramInt, paramFloat);
  }
  
  public static F64Vector makeF64vector(int paramInt) {
    return makeF64vector(paramInt, 0.0D);
  }
  
  public static F64Vector makeF64vector(int paramInt, double paramDouble) {
    return new F64Vector(paramInt, paramDouble);
  }
  
  public static S16Vector makeS16vector(int paramInt) {
    return makeS16vector(paramInt, 0);
  }
  
  public static S16Vector makeS16vector(int paramInt1, int paramInt2) {
    return new S16Vector(paramInt1, (short)paramInt2);
  }
  
  public static S32Vector makeS32vector(int paramInt) {
    return makeS32vector(paramInt, 0);
  }
  
  public static S32Vector makeS32vector(int paramInt1, int paramInt2) {
    return new S32Vector(paramInt1, paramInt2);
  }
  
  public static S64Vector makeS64vector(int paramInt) {
    return makeS64vector(paramInt, 0L);
  }
  
  public static S64Vector makeS64vector(int paramInt, long paramLong) {
    return new S64Vector(paramInt, paramLong);
  }
  
  public static S8Vector makeS8vector(int paramInt) {
    return makeS8vector(paramInt, 0);
  }
  
  public static S8Vector makeS8vector(int paramInt1, int paramInt2) {
    return new S8Vector(paramInt1, (byte)paramInt2);
  }
  
  public static U16Vector makeU16vector(int paramInt) {
    return makeU16vector(paramInt, 0);
  }
  
  public static U16Vector makeU16vector(int paramInt1, int paramInt2) {
    return new U16Vector(paramInt1, (short)paramInt2);
  }
  
  public static U32Vector makeU32vector(int paramInt) {
    return makeU32vector(paramInt, 0L);
  }
  
  public static U32Vector makeU32vector(int paramInt, long paramLong) {
    return new U32Vector(paramInt, (int)paramLong);
  }
  
  public static U64Vector makeU64vector(int paramInt) {
    return makeU64vector(paramInt, Lit0);
  }
  
  public static U64Vector makeU64vector(int paramInt, IntNum paramIntNum) {
    try {
      long l = paramIntNum.longValue();
      return new U64Vector(paramInt, l);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "gnu.lists.U64Vector.<init>(int,long)", 2, paramIntNum);
    } 
  }
  
  public static U8Vector makeU8vector(int paramInt) {
    return makeU8vector(paramInt, 0);
  }
  
  public static U8Vector makeU8vector(int paramInt1, int paramInt2) {
    return new U8Vector(paramInt1, (byte)paramInt2);
  }
  
  public static LList s16vector$To$List(S16Vector paramS16Vector) {
    return LList.makeList((List)paramS16Vector);
  }
  
  public static S16Vector s16vector$V(Object[] paramArrayOfObject) {
    return list$To$S16vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int s16vectorLength(S16Vector paramS16Vector) {
    return paramS16Vector.size();
  }
  
  public static int s16vectorRef(S16Vector paramS16Vector, int paramInt) {
    return paramS16Vector.intAt(paramInt);
  }
  
  public static void s16vectorSet$Ex(S16Vector paramS16Vector, int paramInt1, int paramInt2) {
    paramS16Vector.setShortAt(paramInt1, (short)paramInt2);
  }
  
  public static LList s32vector$To$List(S32Vector paramS32Vector) {
    return LList.makeList((List)paramS32Vector);
  }
  
  public static S32Vector s32vector$V(Object[] paramArrayOfObject) {
    return list$To$S32vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int s32vectorLength(S32Vector paramS32Vector) {
    return paramS32Vector.size();
  }
  
  public static int s32vectorRef(S32Vector paramS32Vector, int paramInt) {
    return paramS32Vector.intAt(paramInt);
  }
  
  public static void s32vectorSet$Ex(S32Vector paramS32Vector, int paramInt1, int paramInt2) {
    paramS32Vector.setIntAt(paramInt1, paramInt2);
  }
  
  public static LList s64vector$To$List(S64Vector paramS64Vector) {
    return LList.makeList((List)paramS64Vector);
  }
  
  public static S64Vector s64vector$V(Object[] paramArrayOfObject) {
    return list$To$S64vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int s64vectorLength(S64Vector paramS64Vector) {
    return paramS64Vector.size();
  }
  
  public static long s64vectorRef(S64Vector paramS64Vector, int paramInt) {
    return paramS64Vector.longAt(paramInt);
  }
  
  public static void s64vectorSet$Ex(S64Vector paramS64Vector, int paramInt, long paramLong) {
    paramS64Vector.setLongAt(paramInt, paramLong);
  }
  
  public static LList s8vector$To$List(S8Vector paramS8Vector) {
    return LList.makeList((List)paramS8Vector);
  }
  
  public static S8Vector s8vector$V(Object[] paramArrayOfObject) {
    return list$To$S8vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int s8vectorLength(S8Vector paramS8Vector) {
    return paramS8Vector.size();
  }
  
  public static int s8vectorRef(S8Vector paramS8Vector, int paramInt) {
    return paramS8Vector.intAt(paramInt);
  }
  
  public static void s8vectorSet$Ex(S8Vector paramS8Vector, int paramInt1, int paramInt2) {
    paramS8Vector.setByteAt(paramInt1, (byte)paramInt2);
  }
  
  public static LList u16vector$To$List(U16Vector paramU16Vector) {
    return LList.makeList((List)paramU16Vector);
  }
  
  public static U16Vector u16vector$V(Object[] paramArrayOfObject) {
    return list$To$U16vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int u16vectorLength(U16Vector paramU16Vector) {
    return paramU16Vector.size();
  }
  
  public static int u16vectorRef(U16Vector paramU16Vector, int paramInt) {
    return paramU16Vector.intAt(paramInt);
  }
  
  public static void u16vectorSet$Ex(U16Vector paramU16Vector, int paramInt1, int paramInt2) {
    paramU16Vector.setShortAt(paramInt1, (short)paramInt2);
  }
  
  public static LList u32vector$To$List(U32Vector paramU32Vector) {
    return LList.makeList((List)paramU32Vector);
  }
  
  public static U32Vector u32vector$V(Object[] paramArrayOfObject) {
    return list$To$U32vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int u32vectorLength(U32Vector paramU32Vector) {
    return paramU32Vector.size();
  }
  
  public static long u32vectorRef(U32Vector paramU32Vector, int paramInt) {
    return ((Number)paramU32Vector.get(paramInt)).longValue();
  }
  
  public static void u32vectorSet$Ex(U32Vector paramU32Vector, int paramInt, long paramLong) {
    paramU32Vector.setIntAt(paramInt, (int)paramLong);
  }
  
  public static LList u64vector$To$List(U64Vector paramU64Vector) {
    return LList.makeList((List)paramU64Vector);
  }
  
  public static U64Vector u64vector$V(Object[] paramArrayOfObject) {
    return list$To$U64vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int u64vectorLength(U64Vector paramU64Vector) {
    return paramU64Vector.size();
  }
  
  public static IntNum u64vectorRef(U64Vector paramU64Vector, int paramInt) {
    return LangObjType.coerceIntNum(paramU64Vector.get(paramInt));
  }
  
  public static void u64vectorSet$Ex(U64Vector paramU64Vector, int paramInt, IntNum paramIntNum) {
    try {
      long l = paramIntNum.longValue();
      paramU64Vector.setLongAt(paramInt, l);
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "gnu.lists.U64Vector.setLongAt(int,long)", 3, paramIntNum);
    } 
  }
  
  public static LList u8vector$To$List(U8Vector paramU8Vector) {
    return LList.makeList((List)paramU8Vector);
  }
  
  public static U8Vector u8vector$V(Object[] paramArrayOfObject) {
    return list$To$U8vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int u8vectorLength(U8Vector paramU8Vector) {
    return paramU8Vector.size();
  }
  
  public static int u8vectorRef(U8Vector paramU8Vector, int paramInt) {
    return paramU8Vector.intAt(paramInt);
  }
  
  public static void u8vectorSet$Ex(U8Vector paramU8Vector, int paramInt1, int paramInt2) {
    paramU8Vector.setByteAt(paramInt1, (byte)paramInt2);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return isS8vector(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 2:
        try {
          int i = ((Number)paramObject).intValue();
          return makeS8vector(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-s8vector", 1, paramObject);
        } 
      case 5:
        try {
          S8Vector s8Vector = (S8Vector)paramObject;
          return Integer.valueOf(s8vectorLength(s8Vector));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s8vector-length", 1, paramObject);
        } 
      case 8:
        try {
          S8Vector s8Vector = (S8Vector)paramObject;
          return s8vector$To$List(s8Vector);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s8vector->list", 1, paramObject);
        } 
      case 9:
        try {
          LList lList = (LList)paramObject;
          return list$To$S8vector(lList);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "list->s8vector", 1, paramObject);
        } 
      case 10:
        return isU8vector(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 11:
        try {
          int i = ((Number)paramObject).intValue();
          return makeU8vector(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-u8vector", 1, paramObject);
        } 
      case 14:
        try {
          U8Vector u8Vector = (U8Vector)paramObject;
          return Integer.valueOf(u8vectorLength(u8Vector));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u8vector-length", 1, paramObject);
        } 
      case 17:
        try {
          U8Vector u8Vector = (U8Vector)paramObject;
          return u8vector$To$List(u8Vector);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u8vector->list", 1, paramObject);
        } 
      case 18:
        try {
          LList lList = (LList)paramObject;
          return list$To$U8vector(lList);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "list->u8vector", 1, paramObject);
        } 
      case 19:
        return isS16vector(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 20:
        try {
          int i = ((Number)paramObject).intValue();
          return makeS16vector(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-s16vector", 1, paramObject);
        } 
      case 23:
        try {
          S16Vector s16Vector = (S16Vector)paramObject;
          return Integer.valueOf(s16vectorLength(s16Vector));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s16vector-length", 1, paramObject);
        } 
      case 26:
        try {
          S16Vector s16Vector = (S16Vector)paramObject;
          return s16vector$To$List(s16Vector);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s16vector->list", 1, paramObject);
        } 
      case 27:
        try {
          LList lList = (LList)paramObject;
          return list$To$S16vector(lList);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "list->s16vector", 1, paramObject);
        } 
      case 28:
        return isU16vector(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 29:
        try {
          int i = ((Number)paramObject).intValue();
          return makeU16vector(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-u16vector", 1, paramObject);
        } 
      case 32:
        try {
          U16Vector u16Vector = (U16Vector)paramObject;
          return Integer.valueOf(u16vectorLength(u16Vector));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u16vector-length", 1, paramObject);
        } 
      case 35:
        try {
          U16Vector u16Vector = (U16Vector)paramObject;
          return u16vector$To$List(u16Vector);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u16vector->list", 1, paramObject);
        } 
      case 36:
        try {
          LList lList = (LList)paramObject;
          return list$To$U16vector(lList);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "list->u16vector", 1, paramObject);
        } 
      case 37:
        return isS32vector(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 38:
        try {
          int i = ((Number)paramObject).intValue();
          return makeS32vector(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-s32vector", 1, paramObject);
        } 
      case 41:
        try {
          S32Vector s32Vector = (S32Vector)paramObject;
          return Integer.valueOf(s32vectorLength(s32Vector));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s32vector-length", 1, paramObject);
        } 
      case 44:
        try {
          S32Vector s32Vector = (S32Vector)paramObject;
          return s32vector$To$List(s32Vector);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s32vector->list", 1, paramObject);
        } 
      case 45:
        try {
          LList lList = (LList)paramObject;
          return list$To$S32vector(lList);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "list->s32vector", 1, paramObject);
        } 
      case 46:
        return isU32vector(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 47:
        try {
          int i = ((Number)paramObject).intValue();
          return makeU32vector(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-u32vector", 1, paramObject);
        } 
      case 50:
        try {
          U32Vector u32Vector = (U32Vector)paramObject;
          return Integer.valueOf(u32vectorLength(u32Vector));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u32vector-length", 1, paramObject);
        } 
      case 53:
        try {
          U32Vector u32Vector = (U32Vector)paramObject;
          return u32vector$To$List(u32Vector);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u32vector->list", 1, paramObject);
        } 
      case 54:
        try {
          LList lList = (LList)paramObject;
          return list$To$U32vector(lList);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "list->u32vector", 1, paramObject);
        } 
      case 55:
        return isS64vector(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 56:
        try {
          int i = ((Number)paramObject).intValue();
          return makeS64vector(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-s64vector", 1, paramObject);
        } 
      case 59:
        try {
          S64Vector s64Vector = (S64Vector)paramObject;
          return Integer.valueOf(s64vectorLength(s64Vector));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s64vector-length", 1, paramObject);
        } 
      case 62:
        try {
          S64Vector s64Vector = (S64Vector)paramObject;
          return s64vector$To$List(s64Vector);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s64vector->list", 1, paramObject);
        } 
      case 63:
        try {
          LList lList = (LList)paramObject;
          return list$To$S64vector(lList);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "list->s64vector", 1, paramObject);
        } 
      case 64:
        return isU64vector(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 65:
        try {
          int i = ((Number)paramObject).intValue();
          return makeU64vector(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-u64vector", 1, paramObject);
        } 
      case 68:
        try {
          U64Vector u64Vector = (U64Vector)paramObject;
          return Integer.valueOf(u64vectorLength(u64Vector));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u64vector-length", 1, paramObject);
        } 
      case 71:
        try {
          U64Vector u64Vector = (U64Vector)paramObject;
          return u64vector$To$List(u64Vector);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u64vector->list", 1, paramObject);
        } 
      case 72:
        try {
          LList lList = (LList)paramObject;
          return list$To$U64vector(lList);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "list->u64vector", 1, paramObject);
        } 
      case 73:
        return isF32vector(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 74:
        try {
          int i = ((Number)paramObject).intValue();
          return makeF32vector(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-f32vector", 1, paramObject);
        } 
      case 77:
        try {
          F32Vector f32Vector = (F32Vector)paramObject;
          return Integer.valueOf(f32vectorLength(f32Vector));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "f32vector-length", 1, paramObject);
        } 
      case 80:
        try {
          F32Vector f32Vector = (F32Vector)paramObject;
          return f32vector$To$List(f32Vector);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "f32vector->list", 1, paramObject);
        } 
      case 81:
        try {
          LList lList = (LList)paramObject;
          return list$To$F32vector(lList);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "list->f32vector", 1, paramObject);
        } 
      case 82:
        return isF64vector(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 83:
        try {
          int i = ((Number)paramObject).intValue();
          return makeF64vector(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-f64vector", 1, paramObject);
        } 
      case 86:
        try {
          F64Vector f64Vector = (F64Vector)paramObject;
          return Integer.valueOf(f64vectorLength(f64Vector));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "f64vector-length", 1, paramObject);
        } 
      case 89:
        try {
          F64Vector f64Vector = (F64Vector)paramObject;
          return f64vector$To$List(f64Vector);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "f64vector->list", 1, paramObject);
        } 
      case 90:
        break;
    } 
    try {
      LList lList = (LList)paramObject;
      return list$To$F64vector(lList);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "list->f64vector", 1, paramObject);
    } 
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 2:
        try {
          int i = ((Number)paramObject1).intValue();
          try {
            int j = ((Number)paramObject2).intValue();
            return makeS8vector(i, j);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-s8vector", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-s8vector", 1, paramObject1);
        } 
      case 6:
        try {
          S8Vector s8Vector = (S8Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return Integer.valueOf(s8vectorRef(s8Vector, i));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "s8vector-ref", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s8vector-ref", 1, paramObject1);
        } 
      case 11:
        try {
          int i = ((Number)paramObject1).intValue();
          try {
            int j = ((Number)paramObject2).intValue();
            return makeU8vector(i, j);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-u8vector", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-u8vector", 1, paramObject1);
        } 
      case 15:
        try {
          U8Vector u8Vector = (U8Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return Integer.valueOf(u8vectorRef(u8Vector, i));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "u8vector-ref", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u8vector-ref", 1, paramObject1);
        } 
      case 20:
        try {
          int i = ((Number)paramObject1).intValue();
          try {
            int j = ((Number)paramObject2).intValue();
            return makeS16vector(i, j);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-s16vector", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-s16vector", 1, paramObject1);
        } 
      case 24:
        try {
          S16Vector s16Vector = (S16Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return Integer.valueOf(s16vectorRef(s16Vector, i));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "s16vector-ref", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s16vector-ref", 1, paramObject1);
        } 
      case 29:
        try {
          int i = ((Number)paramObject1).intValue();
          try {
            int j = ((Number)paramObject2).intValue();
            return makeU16vector(i, j);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-u16vector", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-u16vector", 1, paramObject1);
        } 
      case 33:
        try {
          U16Vector u16Vector = (U16Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return Integer.valueOf(u16vectorRef(u16Vector, i));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "u16vector-ref", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u16vector-ref", 1, paramObject1);
        } 
      case 38:
        try {
          int i = ((Number)paramObject1).intValue();
          try {
            int j = ((Number)paramObject2).intValue();
            return makeS32vector(i, j);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-s32vector", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-s32vector", 1, paramObject1);
        } 
      case 42:
        try {
          S32Vector s32Vector = (S32Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return Integer.valueOf(s32vectorRef(s32Vector, i));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "s32vector-ref", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s32vector-ref", 1, paramObject1);
        } 
      case 47:
        try {
          int i = ((Number)paramObject1).intValue();
          try {
            long l = ((Number)paramObject2).longValue();
            return makeU32vector(i, l);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-u32vector", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-u32vector", 1, paramObject1);
        } 
      case 51:
        try {
          U32Vector u32Vector = (U32Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return Long.valueOf(u32vectorRef(u32Vector, i));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "u32vector-ref", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u32vector-ref", 1, paramObject1);
        } 
      case 56:
        try {
          int i = ((Number)paramObject1).intValue();
          try {
            long l = ((Number)paramObject2).longValue();
            return makeS64vector(i, l);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-s64vector", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-s64vector", 1, paramObject1);
        } 
      case 60:
        try {
          S64Vector s64Vector = (S64Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return Long.valueOf(s64vectorRef(s64Vector, i));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "s64vector-ref", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s64vector-ref", 1, paramObject1);
        } 
      case 65:
        try {
          int i = ((Number)paramObject1).intValue();
          try {
            IntNum intNum = LangObjType.coerceIntNum(paramObject2);
            return makeU64vector(i, intNum);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-u64vector", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-u64vector", 1, paramObject1);
        } 
      case 69:
        try {
          U64Vector u64Vector = (U64Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return u64vectorRef(u64Vector, i);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "u64vector-ref", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u64vector-ref", 1, paramObject1);
        } 
      case 74:
        try {
          int i = ((Number)paramObject1).intValue();
          try {
            float f = ((Number)paramObject2).floatValue();
            return makeF32vector(i, f);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-f32vector", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-f32vector", 1, paramObject1);
        } 
      case 78:
        try {
          F32Vector f32Vector = (F32Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return Float.valueOf(f32vectorRef(f32Vector, i));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "f32vector-ref", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "f32vector-ref", 1, paramObject1);
        } 
      case 83:
        try {
          int i = ((Number)paramObject1).intValue();
          try {
            double d = ((Number)paramObject2).doubleValue();
            return makeF64vector(i, d);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-f64vector", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-f64vector", 1, paramObject1);
        } 
      case 87:
        break;
    } 
    try {
      F64Vector f64Vector = (F64Vector)paramObject1;
      try {
        int i = ((Number)paramObject2).intValue();
        return Double.valueOf(f64vectorRef(f64Vector, i));
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "f64vector-ref", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "f64vector-ref", 1, paramObject1);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 7:
        try {
          S8Vector s8Vector = (S8Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              int j = ((Number)paramObject3).intValue();
              s8vectorSet$Ex(s8Vector, i, j);
              return Values.empty;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "s8vector-set!", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "s8vector-set!", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s8vector-set!", 1, paramObject1);
        } 
      case 16:
        try {
          U8Vector u8Vector = (U8Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              int j = ((Number)paramObject3).intValue();
              u8vectorSet$Ex(u8Vector, i, j);
              return Values.empty;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "u8vector-set!", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "u8vector-set!", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u8vector-set!", 1, paramObject1);
        } 
      case 25:
        try {
          S16Vector s16Vector = (S16Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              int j = ((Number)paramObject3).intValue();
              s16vectorSet$Ex(s16Vector, i, j);
              return Values.empty;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "s16vector-set!", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "s16vector-set!", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s16vector-set!", 1, paramObject1);
        } 
      case 34:
        try {
          U16Vector u16Vector = (U16Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              int j = ((Number)paramObject3).intValue();
              u16vectorSet$Ex(u16Vector, i, j);
              return Values.empty;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "u16vector-set!", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "u16vector-set!", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u16vector-set!", 1, paramObject1);
        } 
      case 43:
        try {
          S32Vector s32Vector = (S32Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              int j = ((Number)paramObject3).intValue();
              s32vectorSet$Ex(s32Vector, i, j);
              return Values.empty;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "s32vector-set!", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "s32vector-set!", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s32vector-set!", 1, paramObject1);
        } 
      case 52:
        try {
          U32Vector u32Vector = (U32Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              long l = ((Number)paramObject3).longValue();
              u32vectorSet$Ex(u32Vector, i, l);
              return Values.empty;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "u32vector-set!", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "u32vector-set!", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u32vector-set!", 1, paramObject1);
        } 
      case 61:
        try {
          S64Vector s64Vector = (S64Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              long l = ((Number)paramObject3).longValue();
              s64vectorSet$Ex(s64Vector, i, l);
              return Values.empty;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "s64vector-set!", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "s64vector-set!", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "s64vector-set!", 1, paramObject1);
        } 
      case 70:
        try {
          U64Vector u64Vector = (U64Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              paramObject1 = LangObjType.coerceIntNum(paramObject3);
              u64vectorSet$Ex(u64Vector, i, (IntNum)paramObject1);
              return Values.empty;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "u64vector-set!", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "u64vector-set!", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "u64vector-set!", 1, paramObject1);
        } 
      case 79:
        try {
          F32Vector f32Vector = (F32Vector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              float f = ((Number)paramObject3).floatValue();
              f32vectorSet$Ex(f32Vector, i, f);
              return Values.empty;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "f32vector-set!", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "f32vector-set!", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "f32vector-set!", 1, paramObject1);
        } 
      case 88:
        break;
    } 
    try {
      F64Vector f64Vector = (F64Vector)paramObject1;
      try {
        int i = ((Number)paramObject2).intValue();
        try {
          double d = ((Number)paramObject3).doubleValue();
          f64vectorSet$Ex(f64Vector, i, d);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "f64vector-set!", 3, paramObject3);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "f64vector-set!", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "f64vector-set!", 1, paramObject1);
    } 
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 4:
        return s8vector$V(paramArrayOfObject);
      case 13:
        return u8vector$V(paramArrayOfObject);
      case 22:
        return s16vector$V(paramArrayOfObject);
      case 31:
        return u16vector$V(paramArrayOfObject);
      case 40:
        return s32vector$V(paramArrayOfObject);
      case 49:
        return u32vector$V(paramArrayOfObject);
      case 58:
        return s64vector$V(paramArrayOfObject);
      case 67:
        return u64vector$V(paramArrayOfObject);
      case 76:
        return f32vector$V(paramArrayOfObject);
      case 85:
        break;
    } 
    return f64vector$V(paramArrayOfObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 90:
        if (paramObject instanceof LList) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 89:
        if (!(paramObject instanceof F64Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 86:
        if (!(paramObject instanceof F64Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
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
        if (paramObject instanceof LList) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 80:
        if (!(paramObject instanceof F32Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 77:
        if (!(paramObject instanceof F32Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 74:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 73:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 72:
        if (paramObject instanceof LList) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 71:
        if (!(paramObject instanceof U64Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 68:
        if (!(paramObject instanceof U64Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 65:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 64:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 63:
        if (paramObject instanceof LList) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 62:
        if (!(paramObject instanceof S64Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 59:
        if (!(paramObject instanceof S64Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 56:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 55:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 54:
        if (paramObject instanceof LList) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 53:
        if (!(paramObject instanceof U32Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 50:
        if (!(paramObject instanceof U32Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 47:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 46:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 45:
        if (paramObject instanceof LList) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 44:
        if (!(paramObject instanceof S32Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 41:
        if (!(paramObject instanceof S32Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 38:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 37:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 36:
        if (paramObject instanceof LList) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 35:
        if (!(paramObject instanceof U16Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 32:
        if (!(paramObject instanceof U16Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 29:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 28:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 27:
        if (paramObject instanceof LList) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 26:
        if (!(paramObject instanceof S16Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 23:
        if (!(paramObject instanceof S16Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 20:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 19:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 18:
        if (paramObject instanceof LList) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 17:
        if (!(paramObject instanceof U8Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 14:
        if (!(paramObject instanceof U8Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 11:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 10:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 9:
        if (paramObject instanceof LList) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 8:
        if (!(paramObject instanceof S8Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 5:
        if (!(paramObject instanceof S8Vector))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 2:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 1:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 87:
        if (paramObject1 instanceof F64Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 83:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 78:
        if (paramObject1 instanceof F32Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 74:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 69:
        if (paramObject1 instanceof U64Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 65:
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 60:
        if (paramObject1 instanceof S64Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 56:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 51:
        if (paramObject1 instanceof U32Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 47:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 42:
        if (paramObject1 instanceof S32Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 38:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 33:
        if (paramObject1 instanceof U16Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 29:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 24:
        if (paramObject1 instanceof S16Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 20:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 15:
        if (paramObject1 instanceof U8Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 11:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 6:
        if (paramObject1 instanceof S8Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 2:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
      case 88:
        if (paramObject1 instanceof F64Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 79:
        if (paramObject1 instanceof F32Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 70:
        if (paramObject1 instanceof U64Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          if (IntNum.asIntNumOrNull(paramObject3) != null) {
            paramCallContext.value3 = paramObject3;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 3;
            return 0;
          } 
          return -786429;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 61:
        if (paramObject1 instanceof S64Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 52:
        if (paramObject1 instanceof U32Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 43:
        if (paramObject1 instanceof S32Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 34:
        if (paramObject1 instanceof U16Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 25:
        if (paramObject1 instanceof S16Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 16:
        if (paramObject1 instanceof U8Vector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 7:
        break;
    } 
    if (paramObject1 instanceof S8Vector) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    } 
    return SYNTHETIC_LOCAL_VARIABLE_6;
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 85:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 76:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 67:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 58:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 49:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 40:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 31:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 22:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 13:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 4:
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
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/uniform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
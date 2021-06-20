package gnu.commonlisp.lang;

import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.kawa.functions.DisplayFormat;
import gnu.kawa.functions.IsEq;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.Not;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.AbstractFormat;
import gnu.mapping.Environment;
import gnu.mapping.LocationEnumeration;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.standard.Scheme;
import kawa.standard.begin;

public class CommonLisp extends Lisp2 {
  static boolean charIsInt = false;
  
  public static final Environment clispEnvironment = (Environment)Environment.make("clisp-environment");
  
  static final AbstractFormat displayFormat;
  
  public static final CommonLisp instance = new CommonLisp();
  
  public static final NumberCompare numEqu = NumberCompare.make((Language)instance, "=", 8);
  
  public static final NumberCompare numGEq;
  
  public static final NumberCompare numGrt = NumberCompare.make((Language)instance, ">", 16);
  
  public static final NumberCompare numLEq;
  
  public static final NumberCompare numLss;
  
  static final AbstractFormat writeFormat;
  
  LangPrimType booleanType;
  
  static {
    numGEq = NumberCompare.make((Language)instance, ">=", 24);
    numLss = NumberCompare.make((Language)instance, "<", 4);
    numLEq = NumberCompare.make((Language)instance, "<=", 12);
    Environment environment = Environment.setSaveCurrent(clispEnvironment);
    try {
      instance.initLisp();
      Environment.restoreCurrent(environment);
      writeFormat = (AbstractFormat)new DisplayFormat(true, 'C');
      return;
    } finally {
      Environment.restoreCurrent(environment);
    } 
  }
  
  public static char asChar(Object paramObject) {
    byte b;
    if (paramObject instanceof Char)
      return ((Char)paramObject).charValue(); 
    if (paramObject instanceof Numeric) {
      b = ((Numeric)paramObject).intValue();
    } else {
      b = -1;
    } 
    if (b < 0 || b > 65535)
      throw new ClassCastException("not a character value"); 
    return (char)b;
  }
  
  public static Numeric asNumber(Object paramObject) {
    return (Numeric)((paramObject instanceof Char) ? IntNum.make(((Char)paramObject).intValue()) : paramObject);
  }
  
  public static Object getCharacter(int paramInt) {
    return charIsInt ? IntNum.make(paramInt) : Char.make((char)paramInt);
  }
  
  public static CommonLisp getInstance() {
    return instance;
  }
  
  public static void registerEnvironment() {
    Language.setDefaults((Language)instance);
  }
  
  public AbstractFormat getFormat(boolean paramBoolean) {
    return paramBoolean ? writeFormat : displayFormat;
  }
  
  public String getName() {
    return "CommonLisp";
  }
  
  public Type getTypeFor(Class paramClass) {
    String str;
    if (paramClass.isPrimitive()) {
      str = paramClass.getName();
      if (str.equals("boolean")) {
        if (this.booleanType == null)
          this.booleanType = new LangPrimType(Type.booleanType, (Language)this); 
        return (Type)this.booleanType;
      } 
      return Scheme.getNamedType(str);
    } 
    return Type.make((Class)str);
  }
  
  public Type getTypeFor(String paramString) {
    String str = paramString;
    if (paramString == "t")
      str = "java.lang.Object"; 
    return Scheme.string2Type(str);
  }
  
  void initLisp() {
    LocationEnumeration locationEnumeration = Scheme.builtin().enumerateAllLocations();
    while (locationEnumeration.hasMoreElements())
      importLocation(locationEnumeration.nextLocation()); 
    try {
      loadClass("kawa.lib.prim_syntax");
      loadClass("kawa.lib.std_syntax");
      loadClass("kawa.lib.lists");
      loadClass("kawa.lib.strings");
      loadClass("gnu.commonlisp.lisp.PrimOps");
    } catch (ClassNotFoundException classNotFoundException) {}
    Lambda lambda = new Lambda();
    lambda.setKeywords(asSymbol("&optional"), asSymbol("&rest"), asSymbol("&key"));
    lambda.defaultDefault = nilExpr;
    defun("lambda", lambda);
    defun("defun", new defun(lambda));
    defun("defvar", new defvar(false));
    defun("defconst", new defvar(true));
    defun("defsubst", new defun(lambda));
    defun("function", new function((Syntax)lambda));
    defun("setq", new setq());
    defun("prog1", new prog1("prog1", 1));
    defun("prog2", prog1.prog2);
    defun("progn", new begin());
    defun("unwind-protect", new UnwindProtect());
    Not not = new Not((Language)this);
    defun("not", not);
    defun("null", not);
    defun("eq", new IsEq((Language)this, "eq"));
    defun("equal", new IsEqual((Language)this, "equal"));
    defun("typep", new InstanceOf((Language)this));
    defun("princ", displayFormat);
    defun("prin1", writeFormat);
    defProcStFld("=", "gnu.commonlisp.lang.CommonLisp", "numEqu");
    defProcStFld("<", "gnu.commonlisp.lang.CommonLisp", "numLss");
    defProcStFld(">", "gnu.commonlisp.lang.CommonLisp", "numGrt");
    defProcStFld("<=", "gnu.commonlisp.lang.CommonLisp", "numLEq");
    defProcStFld(">=", "gnu.commonlisp.lang.CommonLisp", "numGEq");
    defProcStFld("functionp", "gnu.commonlisp.lisp.PrimOps");
  }
  
  static {
    instance.define("t", TRUE);
    instance.define("nil", FALSE);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/commonlisp/lang/CommonLisp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
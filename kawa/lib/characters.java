package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.text.Char;

public class characters extends ModuleBody {
  public static final characters $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7 = (SimpleSymbol)(new SimpleSymbol("char>=?")).readResolve();
  
  public static final ModuleMethod char$Eq$Qu;
  
  public static final ModuleMethod char$Gr$Eq$Qu;
  
  public static final ModuleMethod char$Gr$Qu;
  
  public static final ModuleMethod char$Ls$Eq$Qu;
  
  public static final ModuleMethod char$Ls$Qu;
  
  public static final ModuleMethod char$Mn$Grinteger;
  
  public static final ModuleMethod char$Qu;
  
  public static final ModuleMethod integer$Mn$Grchar;
  
  static {
    Lit6 = (SimpleSymbol)(new SimpleSymbol("char<=?")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("char>?")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("char<?")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("char=?")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("integer->char")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("char->integer")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("char?")).readResolve();
    $instance = new characters();
    characters characters1 = $instance;
    char$Qu = new ModuleMethod(characters1, 1, Lit0, 4097);
    char$Mn$Grinteger = new ModuleMethod(characters1, 2, Lit1, 4097);
    integer$Mn$Grchar = new ModuleMethod(characters1, 3, Lit2, 4097);
    char$Eq$Qu = new ModuleMethod(characters1, 4, Lit3, 8194);
    char$Ls$Qu = new ModuleMethod(characters1, 5, Lit4, 8194);
    char$Gr$Qu = new ModuleMethod(characters1, 6, Lit5, 8194);
    char$Ls$Eq$Qu = new ModuleMethod(characters1, 7, Lit6, 8194);
    char$Gr$Eq$Qu = new ModuleMethod(characters1, 8, Lit7, 8194);
    $instance.run();
  }
  
  public characters() {
    ModuleInfo.register(this);
  }
  
  public static int char$To$Integer(Char paramChar) {
    return paramChar.intValue();
  }
  
  public static Char integer$To$Char(int paramInt) {
    return Char.make(paramInt);
  }
  
  public static boolean isChar(Object paramObject) {
    return paramObject instanceof Char;
  }
  
  public static boolean isChar$Eq(Char paramChar1, Char paramChar2) {
    return (paramChar1.intValue() == paramChar2.intValue());
  }
  
  public static boolean isChar$Gr(Char paramChar1, Char paramChar2) {
    return (paramChar1.intValue() > paramChar2.intValue());
  }
  
  public static boolean isChar$Gr$Eq(Char paramChar1, Char paramChar2) {
    return (paramChar1.intValue() >= paramChar2.intValue());
  }
  
  public static boolean isChar$Ls(Char paramChar1, Char paramChar2) {
    return (paramChar1.intValue() < paramChar2.intValue());
  }
  
  public static boolean isChar$Ls$Eq(Char paramChar1, Char paramChar2) {
    return (paramChar1.intValue() <= paramChar2.intValue());
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return isChar(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 2:
        try {
          Char char_ = (Char)paramObject;
          return Integer.valueOf(char$To$Integer(char_));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char->integer", 1, paramObject);
        } 
      case 3:
        break;
    } 
    try {
      int i = ((Number)paramObject).intValue();
      return integer$To$Char(i);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "integer->char", 1, paramObject);
    } 
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 4:
        try {
          Char char_ = (Char)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isChar$Eq(char_, (Char)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char=?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char=?", 1, paramObject1);
        } 
      case 5:
        try {
          Char char_ = (Char)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isChar$Ls(char_, (Char)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char<?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char<?", 1, paramObject1);
        } 
      case 6:
        try {
          Char char_ = (Char)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isChar$Gr(char_, (Char)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char>?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char>?", 1, paramObject1);
        } 
      case 7:
        try {
          Char char_ = (Char)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isChar$Ls$Eq(char_, (Char)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char<=?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char<=?", 1, paramObject1);
        } 
      case 8:
        break;
    } 
    try {
      Char char_ = (Char)paramObject1;
      try {
        paramObject1 = paramObject2;
        return isChar$Gr$Eq(char_, (Char)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "char>=?", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "char>=?", 1, paramObject1);
    } 
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 3:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 2:
        if (!(paramObject instanceof Char))
          return -786431; 
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
      case 8:
        if (paramObject1 instanceof Char) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Char))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 7:
        if (paramObject1 instanceof Char) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Char))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 6:
        if (paramObject1 instanceof Char) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Char))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 5:
        if (paramObject1 instanceof Char) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Char))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 4:
        break;
    } 
    if (paramObject1 instanceof Char) {
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Char))
        return -786430; 
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return SYNTHETIC_LOCAL_VARIABLE_5;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/characters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
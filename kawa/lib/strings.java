package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Strings;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Char;

public class strings extends ModuleBody {
  public static final strings $instance;
  
  public static final ModuleMethod $make$string$;
  
  static final Char Lit0;
  
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
  
  static final SimpleSymbol Lit22 = (SimpleSymbol)(new SimpleSymbol("string-append/shared")).readResolve();
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod list$Mn$Grstring;
  
  public static final ModuleMethod make$Mnstring;
  
  public static final ModuleMethod string$Eq$Qu;
  
  public static final ModuleMethod string$Gr$Eq$Qu;
  
  public static final ModuleMethod string$Gr$Qu;
  
  public static final ModuleMethod string$Ls$Eq$Qu;
  
  public static final ModuleMethod string$Ls$Qu;
  
  public static final ModuleMethod string$Mn$Grlist;
  
  public static final ModuleMethod string$Mnappend;
  
  public static final ModuleMethod string$Mnappend$Slshared;
  
  public static final ModuleMethod string$Mncapitalize;
  
  public static final ModuleMethod string$Mncapitalize$Ex;
  
  public static final ModuleMethod string$Mncopy;
  
  public static final ModuleMethod string$Mndowncase$Ex;
  
  public static final ModuleMethod string$Mnfill$Ex;
  
  public static final ModuleMethod string$Mnlength;
  
  public static final ModuleMethod string$Mnref;
  
  public static final ModuleMethod string$Mnset$Ex;
  
  public static final ModuleMethod string$Mnupcase$Ex;
  
  public static final ModuleMethod string$Qu;
  
  public static final ModuleMethod substring;
  
  public static CharSequence $make$string$(Object... paramVarArgs) {
    int j = paramVarArgs.length;
    FString fString = new FString(j);
    for (int i = 0; i < j; i++)
      fString.setCharAt(i, ((Char)paramVarArgs[i]).charValue()); 
    return (CharSequence)fString;
  }
  
  static {
    Lit21 = (SimpleSymbol)(new SimpleSymbol("string-append")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("string-capitalize")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("string-capitalize!")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("string-downcase!")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("string-upcase!")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("string-fill!")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("string-copy")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("list->string")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("string->list")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("substring")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("string>=?")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("string<=?")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("string>?")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("string<?")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("string=?")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("string-set!")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("string-ref")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("string-length")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("$make$string$")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("make-string")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("string?")).readResolve();
    Lit0 = Char.make(32);
    $instance = new strings();
    strings strings1 = $instance;
    string$Qu = new ModuleMethod(strings1, 1, Lit1, 4097);
    make$Mnstring = new ModuleMethod(strings1, 2, Lit2, 8193);
    $make$string$ = new ModuleMethod(strings1, 4, Lit3, -4096);
    string$Mnlength = new ModuleMethod(strings1, 5, Lit4, 4097);
    string$Mnref = new ModuleMethod(strings1, 6, Lit5, 8194);
    string$Mnset$Ex = new ModuleMethod(strings1, 7, Lit6, 12291);
    string$Eq$Qu = new ModuleMethod(strings1, 8, Lit7, 8194);
    string$Ls$Qu = new ModuleMethod(strings1, 9, Lit8, 8194);
    string$Gr$Qu = new ModuleMethod(strings1, 10, Lit9, 8194);
    string$Ls$Eq$Qu = new ModuleMethod(strings1, 11, Lit10, 8194);
    string$Gr$Eq$Qu = new ModuleMethod(strings1, 12, Lit11, 8194);
    substring = new ModuleMethod(strings1, 13, Lit12, 12291);
    string$Mn$Grlist = new ModuleMethod(strings1, 14, Lit13, 4097);
    list$Mn$Grstring = new ModuleMethod(strings1, 15, Lit14, 4097);
    string$Mncopy = new ModuleMethod(strings1, 16, Lit15, 4097);
    string$Mnfill$Ex = new ModuleMethod(strings1, 17, Lit16, 8194);
    string$Mnupcase$Ex = new ModuleMethod(strings1, 18, Lit17, 4097);
    string$Mndowncase$Ex = new ModuleMethod(strings1, 19, Lit18, 4097);
    string$Mncapitalize$Ex = new ModuleMethod(strings1, 20, Lit19, 4097);
    string$Mncapitalize = new ModuleMethod(strings1, 21, Lit20, 4097);
    string$Mnappend = new ModuleMethod(strings1, 22, Lit21, -4096);
    string$Mnappend$Slshared = new ModuleMethod(strings1, 23, Lit22, -4096);
    $instance.run();
  }
  
  public strings() {
    ModuleInfo.register(this);
  }
  
  public static boolean isString(Object paramObject) {
    return paramObject instanceof CharSequence;
  }
  
  public static boolean isString$Eq(Object paramObject1, Object paramObject2) {
    return paramObject1.toString().equals(paramObject2.toString());
  }
  
  public static boolean isString$Gr(Object paramObject1, Object paramObject2) {
    return (paramObject1.toString().compareTo(paramObject2.toString()) > 0);
  }
  
  public static boolean isString$Gr$Eq(Object paramObject1, Object paramObject2) {
    return (paramObject1.toString().compareTo(paramObject2.toString()) >= 0);
  }
  
  public static boolean isString$Ls(Object paramObject1, Object paramObject2) {
    return (paramObject1.toString().compareTo(paramObject2.toString()) < 0);
  }
  
  public static boolean isString$Ls$Eq(Object paramObject1, Object paramObject2) {
    return (paramObject1.toString().compareTo(paramObject2.toString()) <= 0);
  }
  
  public static CharSequence list$To$String(LList paramLList) {
    int j = lists.length(paramLList);
    FString fString = new FString(j);
    int i = 0;
    while (i < j) {
      try {
        Pair pair = (Pair)paramLList;
        try {
          CharSeq charSeq = (CharSeq)fString;
          object = pair.getCar();
          try {
            char c = ((Char)object).charValue();
            stringSet$Ex(charSeq, i, c);
            Object object1 = pair.getCdr();
            try {
              object = object1;
              i++;
            } catch (ClassCastException null) {
              throw new WrongType(object, "list", -2, object1);
            } 
          } catch (ClassCastException null) {
            throw new WrongType(classCastException, "string-set!", 2, object);
          } 
        } catch (ClassCastException object) {
          throw new WrongType(object, "string-set!", 0, classCastException);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "pair", -2, object);
      } 
    } 
    return (CharSequence)classCastException;
  }
  
  public static CharSequence makeString(int paramInt) {
    return makeString(paramInt, Lit0);
  }
  
  public static CharSequence makeString(int paramInt, Object paramObject) {
    try {
      char c = ((Char)paramObject).charValue();
      return (CharSequence)new FString(paramInt, c);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "gnu.lists.FString.<init>(int,char)", 2, paramObject);
    } 
  }
  
  public static LList string$To$List(CharSequence paramCharSequence) {
    LList lList = LList.Empty;
    int i = stringLength(paramCharSequence);
    while (true) {
      if (--i < 0)
        return lList; 
      Pair pair = new Pair(Char.make(stringRef(paramCharSequence, i)), lList);
    } 
  }
  
  public static FString stringAppend(Object... paramVarArgs) {
    FString fString = new FString();
    fString.addAllStrings(paramVarArgs, 0);
    return fString;
  }
  
  public static CharSequence stringAppend$SlShared(Object... paramVarArgs) {
    if (paramVarArgs.length == 0)
      return (CharSequence)new FString(); 
    Object object = paramVarArgs[0];
    if (object instanceof FString)
      try {
        FString fString = (FString)object;
        fString.addAllStrings(paramVarArgs, 1);
        return (CharSequence)fString;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "fstr", -2, object);
      }  
    try {
      CharSequence charSequence = (CharSequence)object;
      FString fString = stringCopy(charSequence);
      fString.addAllStrings((Object[])classCastException, 1);
      return (CharSequence)fString;
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "string-copy", 0, object);
    } 
  }
  
  public static CharSequence stringCapitalize(CharSequence paramCharSequence) {
    FString fString = stringCopy(paramCharSequence);
    Strings.makeCapitalize((CharSeq)fString);
    return (CharSequence)fString;
  }
  
  public static CharSequence stringCapitalize$Ex(CharSeq paramCharSeq) {
    Strings.makeCapitalize(paramCharSeq);
    return (CharSequence)paramCharSeq;
  }
  
  public static FString stringCopy(CharSequence paramCharSequence) {
    return new FString(paramCharSequence);
  }
  
  public static CharSequence stringDowncase$Ex(CharSeq paramCharSeq) {
    Strings.makeLowerCase(paramCharSeq);
    return (CharSequence)paramCharSeq;
  }
  
  public static void stringFill$Ex(CharSeq paramCharSeq, char paramChar) {
    paramCharSeq.fill(paramChar);
  }
  
  public static int stringLength(CharSequence paramCharSequence) {
    return paramCharSequence.length();
  }
  
  public static char stringRef(CharSequence paramCharSequence, int paramInt) {
    return paramCharSequence.charAt(paramInt);
  }
  
  public static void stringSet$Ex(CharSeq paramCharSeq, int paramInt, char paramChar) {
    paramCharSeq.setCharAt(paramInt, paramChar);
  }
  
  public static CharSequence stringUpcase$Ex(CharSeq paramCharSeq) {
    Strings.makeUpperCase(paramCharSeq);
    return (CharSequence)paramCharSeq;
  }
  
  public static CharSequence substring(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    return (CharSequence)new FString(paramCharSequence, paramInt1, paramInt2 - paramInt1);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return isString(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 2:
        try {
          int i = ((Number)paramObject).intValue();
          return makeString(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-string", 1, paramObject);
        } 
      case 5:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return Integer.valueOf(stringLength(charSequence));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-length", 1, paramObject);
        } 
      case 14:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return string$To$List(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string->list", 1, paramObject);
        } 
      case 15:
        try {
          LList lList = (LList)paramObject;
          return list$To$String(lList);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "list->string", 1, paramObject);
        } 
      case 16:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return stringCopy(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-copy", 1, paramObject);
        } 
      case 18:
        try {
          CharSeq charSeq = (CharSeq)paramObject;
          return stringUpcase$Ex(charSeq);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-upcase!", 1, paramObject);
        } 
      case 19:
        try {
          CharSeq charSeq = (CharSeq)paramObject;
          return stringDowncase$Ex(charSeq);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-downcase!", 1, paramObject);
        } 
      case 20:
        try {
          CharSeq charSeq = (CharSeq)paramObject;
          return stringCapitalize$Ex(charSeq);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-capitalize!", 1, paramObject);
        } 
      case 21:
        break;
    } 
    try {
      CharSequence charSequence = (CharSequence)paramObject;
      return stringCapitalize(charSequence);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "string-capitalize", 1, paramObject);
    } 
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 2:
        try {
          int i = ((Number)paramObject1).intValue();
          return makeString(i, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-string", 1, paramObject1);
        } 
      case 6:
        try {
          CharSequence charSequence = (CharSequence)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return Char.make(stringRef(charSequence, i));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ref", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ref", 1, paramObject1);
        } 
      case 8:
        return isString$Eq(paramObject1, paramObject2) ? Boolean.TRUE : Boolean.FALSE;
      case 9:
        return isString$Ls(paramObject1, paramObject2) ? Boolean.TRUE : Boolean.FALSE;
      case 10:
        return isString$Gr(paramObject1, paramObject2) ? Boolean.TRUE : Boolean.FALSE;
      case 11:
        return isString$Ls$Eq(paramObject1, paramObject2) ? Boolean.TRUE : Boolean.FALSE;
      case 12:
        return isString$Gr$Eq(paramObject1, paramObject2) ? Boolean.TRUE : Boolean.FALSE;
      case 17:
        break;
    } 
    try {
      CharSeq charSeq = (CharSeq)paramObject1;
      try {
        char c = ((Char)paramObject2).charValue();
        stringFill$Ex(charSeq, c);
        return Values.empty;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-fill!", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "string-fill!", 1, paramObject1);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 7:
        try {
          CharSeq charSeq = (CharSeq)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              char c = ((Char)paramObject3).charValue();
              stringSet$Ex(charSeq, i, c);
              return Values.empty;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-set!", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-set!", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-set!", 1, paramObject1);
        } 
      case 13:
        break;
    } 
    try {
      CharSequence charSequence = (CharSequence)paramObject1;
      try {
        int i = ((Number)paramObject2).intValue();
        try {
          int j = ((Number)paramObject3).intValue();
          return substring(charSequence, i, j);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "substring", 3, paramObject3);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "substring", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "substring", 1, paramObject1);
    } 
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 4:
        return $make$string$(paramArrayOfObject);
      case 22:
        return stringAppend(paramArrayOfObject);
      case 23:
        break;
    } 
    return stringAppend$SlShared(paramArrayOfObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 21:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 20:
        if (!(paramObject instanceof CharSeq))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 19:
        if (!(paramObject instanceof CharSeq))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 18:
        if (!(paramObject instanceof CharSeq))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 16:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 15:
        if (paramObject instanceof LList) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 14:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 5:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
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
      case 17:
        if (paramObject1 instanceof CharSeq) {
          paramCallContext.value1 = paramObject1;
          if (paramObject2 instanceof Char) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
          return -786430;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 12:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 11:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 10:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 9:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 8:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 6:
        if (paramObject1 instanceof CharSequence) {
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
    switch (paramModuleMethod.selector) {
      default:
        return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
      case 13:
        if (paramObject1 instanceof CharSequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return -786431;
      case 7:
        break;
    } 
    if (!(paramObject1 instanceof CharSeq))
      return -786431; 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    if (paramObject3 instanceof Char) {
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    } 
    return -786429;
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 23:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 22:
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/strings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package kawa.lib.kawa;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kawa.standard.Scheme;

public class regex extends ModuleBody {
  public static final regex $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7 = (SimpleSymbol)(new SimpleSymbol("regex-replace*")).readResolve();
  
  public static final ModuleMethod regex$Mnmatch;
  
  public static final ModuleMethod regex$Mnmatch$Mnpositions;
  
  public static final ModuleMethod regex$Mnmatch$Qu;
  
  public static final ModuleMethod regex$Mnquote;
  
  public static final ModuleMethod regex$Mnreplace;
  
  public static final ModuleMethod regex$Mnreplace$St;
  
  public static final ModuleMethod regex$Mnsplit;
  
  static {
    Lit6 = (SimpleSymbol)(new SimpleSymbol("regex-replace")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("regex-split")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("regex-match-positions")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("regex-match")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("regex-match?")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("regex-quote")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("loop")).readResolve();
    $instance = new regex();
    regex regex1 = $instance;
    regex$Mnquote = new ModuleMethod(regex1, 2, Lit1, 4097);
    regex$Mnmatch$Qu = new ModuleMethod(regex1, 3, Lit2, 16386);
    regex$Mnmatch = new ModuleMethod(regex1, 6, Lit3, 16386);
    regex$Mnmatch$Mnpositions = new ModuleMethod(regex1, 9, Lit4, 16386);
    regex$Mnsplit = new ModuleMethod(regex1, 12, Lit5, 8194);
    regex$Mnreplace = new ModuleMethod(regex1, 13, Lit6, 12291);
    regex$Mnreplace$St = new ModuleMethod(regex1, 14, Lit7, 12291);
    $instance.run();
  }
  
  public regex() {
    ModuleInfo.register(this);
  }
  
  public static boolean isRegexMatch(Object paramObject, CharSequence paramCharSequence) {
    return isRegexMatch(paramObject, paramCharSequence, 0);
  }
  
  public static boolean isRegexMatch(Object paramObject, CharSequence paramCharSequence, int paramInt) {
    return isRegexMatch(paramObject, paramCharSequence, paramInt, paramCharSequence.length());
  }
  
  public static boolean isRegexMatch(Object paramObject, CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    if (paramObject instanceof Pattern)
      try {
        Pattern pattern = (Pattern)paramObject;
        paramObject = pattern;
        paramObject = paramObject.matcher(paramCharSequence);
        paramObject.region(paramInt1, paramInt2);
        return paramObject.find();
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "rex", -2, paramObject);
      }  
    paramObject = Pattern.compile(paramObject.toString());
    paramObject = paramObject.matcher((CharSequence)classCastException);
    paramObject.region(paramInt1, paramInt2);
    return paramObject.find();
  }
  
  public static Object regexMatch(Object paramObject, CharSequence paramCharSequence) {
    return regexMatch(paramObject, paramCharSequence, 0);
  }
  
  public static Object regexMatch(Object paramObject, CharSequence paramCharSequence, int paramInt) {
    return regexMatch(paramObject, paramCharSequence, paramInt, paramCharSequence.length());
  }
  
  public static Object regexMatch(Object paramObject, CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: instanceof java/util/regex/Pattern
    //   4: ifeq -> 55
    //   7: aload_0
    //   8: checkcast java/util/regex/Pattern
    //   11: astore #4
    //   13: aload #4
    //   15: astore_0
    //   16: aload_0
    //   17: aload_1
    //   18: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   21: astore #5
    //   23: aload #5
    //   25: iload_2
    //   26: iload_3
    //   27: invokevirtual region : (II)Ljava/util/regex/Matcher;
    //   30: pop
    //   31: aload #5
    //   33: invokevirtual find : ()Z
    //   36: ifeq -> 114
    //   39: aload #5
    //   41: invokevirtual groupCount : ()I
    //   44: istore_2
    //   45: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   48: astore_0
    //   49: iload_2
    //   50: ifge -> 66
    //   53: aload_0
    //   54: areturn
    //   55: aload_0
    //   56: invokevirtual toString : ()Ljava/lang/String;
    //   59: invokestatic compile : (Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   62: astore_0
    //   63: goto -> 16
    //   66: aload #5
    //   68: iload_2
    //   69: invokevirtual start : (I)I
    //   72: istore_3
    //   73: iload_3
    //   74: ifge -> 96
    //   77: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   80: astore #4
    //   82: aload #4
    //   84: aload_0
    //   85: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   88: astore_0
    //   89: iload_2
    //   90: iconst_1
    //   91: isub
    //   92: istore_2
    //   93: goto -> 49
    //   96: aload_1
    //   97: iload_3
    //   98: aload #5
    //   100: iload_2
    //   101: invokevirtual end : (I)I
    //   104: invokeinterface subSequence : (II)Ljava/lang/CharSequence;
    //   109: astore #4
    //   111: goto -> 82
    //   114: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   117: areturn
    //   118: astore_1
    //   119: new gnu/mapping/WrongType
    //   122: dup
    //   123: aload_1
    //   124: ldc 'rex'
    //   126: bipush #-2
    //   128: aload_0
    //   129: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   132: athrow
    // Exception table:
    //   from	to	target	type
    //   7	13	118	java/lang/ClassCastException
  }
  
  public static Object regexMatchPositions(Object paramObject, CharSequence paramCharSequence) {
    return regexMatchPositions(paramObject, paramCharSequence, 0);
  }
  
  public static Object regexMatchPositions(Object paramObject, CharSequence paramCharSequence, int paramInt) {
    return regexMatchPositions(paramObject, paramCharSequence, paramInt, paramCharSequence.length());
  }
  
  public static Object regexMatchPositions(Object paramObject, CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: instanceof java/util/regex/Pattern
    //   4: ifeq -> 55
    //   7: aload_0
    //   8: checkcast java/util/regex/Pattern
    //   11: astore #4
    //   13: aload #4
    //   15: astore_0
    //   16: aload_0
    //   17: aload_1
    //   18: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   21: astore #4
    //   23: aload #4
    //   25: iload_2
    //   26: iload_3
    //   27: invokevirtual region : (II)Ljava/util/regex/Matcher;
    //   30: pop
    //   31: aload #4
    //   33: invokevirtual find : ()Z
    //   36: ifeq -> 114
    //   39: aload #4
    //   41: invokevirtual groupCount : ()I
    //   44: istore_2
    //   45: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   48: astore_0
    //   49: iload_2
    //   50: ifge -> 66
    //   53: aload_0
    //   54: areturn
    //   55: aload_0
    //   56: invokevirtual toString : ()Ljava/lang/String;
    //   59: invokestatic compile : (Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   62: astore_0
    //   63: goto -> 16
    //   66: aload #4
    //   68: iload_2
    //   69: invokevirtual start : (I)I
    //   72: istore_3
    //   73: iload_3
    //   74: ifge -> 94
    //   77: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   80: astore_1
    //   81: aload_1
    //   82: aload_0
    //   83: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   86: astore_0
    //   87: iload_2
    //   88: iconst_1
    //   89: isub
    //   90: istore_2
    //   91: goto -> 49
    //   94: iload_3
    //   95: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   98: aload #4
    //   100: iload_2
    //   101: invokevirtual end : (I)I
    //   104: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   107: invokestatic cons : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   110: astore_1
    //   111: goto -> 81
    //   114: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   117: areturn
    //   118: astore_1
    //   119: new gnu/mapping/WrongType
    //   122: dup
    //   123: aload_1
    //   124: ldc 'rex'
    //   126: bipush #-2
    //   128: aload_0
    //   129: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   132: athrow
    // Exception table:
    //   from	to	target	type
    //   7	13	118	java/lang/ClassCastException
  }
  
  public static String regexQuote(CharSequence paramCharSequence) {
    if (paramCharSequence == null) {
      paramCharSequence = null;
      return Pattern.quote((String)paramCharSequence);
    } 
    paramCharSequence = paramCharSequence.toString();
    return Pattern.quote((String)paramCharSequence);
  }
  
  public static CharSequence regexReplace(Object paramObject1, CharSequence paramCharSequence, Object paramObject2) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #4
    //   3: aconst_null
    //   4: astore_3
    //   5: aload_0
    //   6: instanceof java/util/regex/Pattern
    //   9: ifeq -> 97
    //   12: aload_0
    //   13: checkcast java/util/regex/Pattern
    //   16: astore #5
    //   18: aload #5
    //   20: astore_0
    //   21: aload_0
    //   22: aload_1
    //   23: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   26: astore #5
    //   28: aload #5
    //   30: invokevirtual find : ()Z
    //   33: ifeq -> 95
    //   36: new java/lang/StringBuffer
    //   39: dup
    //   40: invokespecial <init> : ()V
    //   43: astore_1
    //   44: aload_2
    //   45: invokestatic isProcedure : (Ljava/lang/Object;)Z
    //   48: ifeq -> 116
    //   51: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
    //   54: aload_2
    //   55: aload #5
    //   57: invokevirtual group : ()Ljava/lang/String;
    //   60: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   63: astore_0
    //   64: aload_0
    //   65: ifnonnull -> 108
    //   68: aload_3
    //   69: astore_0
    //   70: aload_0
    //   71: invokestatic quoteReplacement : (Ljava/lang/String;)Ljava/lang/String;
    //   74: astore_0
    //   75: aload #5
    //   77: aload_1
    //   78: aload_0
    //   79: invokevirtual appendReplacement : (Ljava/lang/StringBuffer;Ljava/lang/String;)Ljava/util/regex/Matcher;
    //   82: pop
    //   83: aload #5
    //   85: aload_1
    //   86: invokevirtual appendTail : (Ljava/lang/StringBuffer;)Ljava/lang/StringBuffer;
    //   89: pop
    //   90: aload_1
    //   91: invokevirtual toString : ()Ljava/lang/String;
    //   94: astore_1
    //   95: aload_1
    //   96: areturn
    //   97: aload_0
    //   98: invokevirtual toString : ()Ljava/lang/String;
    //   101: invokestatic compile : (Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   104: astore_0
    //   105: goto -> 21
    //   108: aload_0
    //   109: invokevirtual toString : ()Ljava/lang/String;
    //   112: astore_0
    //   113: goto -> 70
    //   116: aload #4
    //   118: astore_0
    //   119: aload_2
    //   120: ifnull -> 75
    //   123: aload_2
    //   124: invokevirtual toString : ()Ljava/lang/String;
    //   127: astore_0
    //   128: goto -> 75
    //   131: astore_1
    //   132: new gnu/mapping/WrongType
    //   135: dup
    //   136: aload_1
    //   137: ldc 'rex'
    //   139: bipush #-2
    //   141: aload_0
    //   142: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   145: athrow
    // Exception table:
    //   from	to	target	type
    //   12	18	131	java/lang/ClassCastException
  }
  
  public static CharSequence regexReplace$St(Object paramObject1, CharSequence paramCharSequence, Object paramObject2) {
    // Byte code:
    //   0: new kawa/lib/kawa/regex$frame
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_3
    //   8: aload_3
    //   9: aload_2
    //   10: putfield repl : Ljava/lang/Object;
    //   13: aload_0
    //   14: instanceof java/util/regex/Pattern
    //   17: ifeq -> 70
    //   20: aload_0
    //   21: checkcast java/util/regex/Pattern
    //   24: astore_2
    //   25: aload_2
    //   26: astore_0
    //   27: aload_3
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   33: putfield matcher : Ljava/util/regex/Matcher;
    //   36: aload_3
    //   37: new java/lang/StringBuffer
    //   40: dup
    //   41: invokespecial <init> : ()V
    //   44: putfield sbuf : Ljava/lang/StringBuffer;
    //   47: aload_3
    //   48: getfield repl : Ljava/lang/Object;
    //   51: invokestatic isProcedure : (Ljava/lang/Object;)Z
    //   54: ifeq -> 81
    //   57: aload_3
    //   58: aload_3
    //   59: getfield loop : Ljava/lang/Object;
    //   62: putfield loop : Ljava/lang/Object;
    //   65: aload_3
    //   66: invokevirtual lambda1loop : ()Ljava/lang/String;
    //   69: areturn
    //   70: aload_0
    //   71: invokevirtual toString : ()Ljava/lang/String;
    //   74: invokestatic compile : (Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   77: astore_0
    //   78: goto -> 27
    //   81: aload_3
    //   82: getfield matcher : Ljava/util/regex/Matcher;
    //   85: astore_1
    //   86: aload_3
    //   87: getfield repl : Ljava/lang/Object;
    //   90: astore_0
    //   91: aload_0
    //   92: ifnonnull -> 103
    //   95: aconst_null
    //   96: astore_0
    //   97: aload_1
    //   98: aload_0
    //   99: invokevirtual replaceAll : (Ljava/lang/String;)Ljava/lang/String;
    //   102: areturn
    //   103: aload_0
    //   104: invokevirtual toString : ()Ljava/lang/String;
    //   107: astore_0
    //   108: goto -> 97
    //   111: astore_1
    //   112: new gnu/mapping/WrongType
    //   115: dup
    //   116: aload_1
    //   117: ldc 'rex'
    //   119: bipush #-2
    //   121: aload_0
    //   122: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   125: athrow
    // Exception table:
    //   from	to	target	type
    //   20	25	111	java/lang/ClassCastException
  }
  
  public static LList regexSplit(Object paramObject, CharSequence paramCharSequence) {
    if (paramObject instanceof Pattern)
      try {
        Pattern pattern = (Pattern)paramObject;
        paramObject = pattern;
        return LList.makeList((Object[])paramObject.split(paramCharSequence, -1), 0);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "rex", -2, paramObject);
      }  
    paramObject = Pattern.compile(paramObject.toString());
    return LList.makeList((Object[])paramObject.split((CharSequence)classCastException, -1), 0);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    if (paramModuleMethod.selector == 2)
      try {
        CharSequence charSequence = (CharSequence)paramObject;
        return regexQuote(charSequence);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "regex-quote", 1, paramObject);
      }  
    return super.apply1((ModuleMethod)classCastException, paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 3:
        try {
          CharSequence charSequence = (CharSequence)paramObject2;
          return isRegexMatch(paramObject1, charSequence) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "regex-match?", 2, paramObject2);
        } 
      case 6:
        try {
          CharSequence charSequence = (CharSequence)paramObject2;
          return regexMatch(paramObject1, charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "regex-match", 2, paramObject2);
        } 
      case 9:
        try {
          CharSequence charSequence = (CharSequence)paramObject2;
          return regexMatchPositions(paramObject1, charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "regex-match-positions", 2, paramObject2);
        } 
      case 12:
        break;
    } 
    try {
      CharSequence charSequence = (CharSequence)paramObject2;
      return regexSplit(paramObject1, charSequence);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "regex-split", 2, paramObject2);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 3:
        try {
          CharSequence charSequence = (CharSequence)paramObject2;
          try {
            int i = ((Number)paramObject3).intValue();
            return isRegexMatch(paramObject1, charSequence, i) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "regex-match?", 3, paramObject3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "regex-match?", 2, paramObject2);
        } 
      case 6:
        try {
          CharSequence charSequence = (CharSequence)paramObject2;
          try {
            int i = ((Number)paramObject3).intValue();
            return regexMatch(paramObject1, charSequence, i);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "regex-match", 3, paramObject3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "regex-match", 2, paramObject2);
        } 
      case 9:
        try {
          CharSequence charSequence = (CharSequence)paramObject2;
          try {
            int i = ((Number)paramObject3).intValue();
            return regexMatchPositions(paramObject1, charSequence, i);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "regex-match-positions", 3, paramObject3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "regex-match-positions", 2, paramObject2);
        } 
      case 13:
        try {
          CharSequence charSequence = (CharSequence)paramObject2;
          return regexReplace(paramObject1, charSequence, paramObject3);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "regex-replace", 2, paramObject2);
        } 
      case 14:
        break;
    } 
    try {
      CharSequence charSequence = (CharSequence)paramObject2;
      return regexReplace$St(paramObject1, charSequence, paramObject3);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "regex-replace*", 2, paramObject2);
    } 
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
      case 3:
        try {
          CharSequence charSequence = (CharSequence)paramObject2;
          try {
            int i = ((Number)paramObject3).intValue();
            try {
              int j = ((Number)paramObject4).intValue();
              return isRegexMatch(paramObject1, charSequence, i, j) ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "regex-match?", 4, paramObject4);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "regex-match?", 3, paramObject3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "regex-match?", 2, paramObject2);
        } 
      case 6:
        try {
          CharSequence charSequence = (CharSequence)paramObject2;
          try {
            int i = ((Number)paramObject3).intValue();
            try {
              int j = ((Number)paramObject4).intValue();
              return regexMatch(paramObject1, charSequence, i, j);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "regex-match", 4, paramObject4);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "regex-match", 3, paramObject3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "regex-match", 2, paramObject2);
        } 
      case 9:
        break;
    } 
    try {
      CharSequence charSequence = (CharSequence)paramObject2;
      try {
        int i = ((Number)paramObject3).intValue();
        try {
          int j = ((Number)paramObject4).intValue();
          return regexMatchPositions(paramObject1, charSequence, i, j);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "regex-match-positions", 4, paramObject4);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "regex-match-positions", 3, paramObject3);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "regex-match-positions", 2, paramObject2);
    } 
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 2) {
      if (paramObject instanceof CharSequence) {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      } 
      return -786431;
    } 
    return super.match1(paramModuleMethod, paramObject, paramCallContext);
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 12:
        paramCallContext.value1 = paramObject1;
        if (paramObject2 instanceof CharSequence) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 9:
        paramCallContext.value1 = paramObject1;
        if (paramObject2 instanceof CharSequence) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 6:
        paramCallContext.value1 = paramObject1;
        if (paramObject2 instanceof CharSequence) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 3:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    if (paramObject2 instanceof CharSequence) {
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return -786430;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
      case 14:
        paramCallContext.value1 = paramObject1;
        if (paramObject2 instanceof CharSequence) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return -786430;
      case 13:
        paramCallContext.value1 = paramObject1;
        if (paramObject2 instanceof CharSequence) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return -786430;
      case 9:
        paramCallContext.value1 = paramObject1;
        if (paramObject2 instanceof CharSequence) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return -786430;
      case 6:
        paramCallContext.value1 = paramObject1;
        if (paramObject2 instanceof CharSequence) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return -786430;
      case 3:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    if (paramObject2 instanceof CharSequence) {
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    } 
    return -786430;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
      case 9:
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
      case 6:
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
      case 3:
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
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
  
  public class frame extends ModuleBody {
    Object loop = new ModuleMethod(this, 1, regex.Lit0, 0);
    
    Matcher matcher;
    
    Object repl;
    
    StringBuffer sbuf;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 1) ? lambda1loop() : super.apply0(param1ModuleMethod);
    }
    
    public String lambda1loop() {
      if (this.matcher.find()) {
        Matcher matcher = this.matcher;
        StringBuffer stringBuffer = this.sbuf;
        Object object = Scheme.applyToArgs.apply2(this.repl, this.matcher.group());
        if (object == null) {
          object = null;
        } else {
          object = object.toString();
        } 
        matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement((String)object));
      } 
      this.matcher.appendTail(this.sbuf);
      return this.sbuf.toString();
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
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/kawa/regex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
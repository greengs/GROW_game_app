package kawa.lib.rnrs;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.UnicodeUtils;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import gnu.text.Char;
import java.util.Locale;
import kawa.lib.misc;

public class unicode extends ModuleBody {
  public static final unicode $instance;
  
  static final SimpleSymbol Lit0;
  
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
  
  static final SimpleSymbol Lit28 = (SimpleSymbol)(new SimpleSymbol("string-normalize-nfkc")).readResolve();
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod char$Mnalphabetic$Qu;
  
  public static final ModuleMethod char$Mnci$Eq$Qu;
  
  public static final ModuleMethod char$Mnci$Gr$Eq$Qu;
  
  public static final ModuleMethod char$Mnci$Gr$Qu;
  
  public static final ModuleMethod char$Mnci$Ls$Eq$Qu;
  
  public static final ModuleMethod char$Mnci$Ls$Qu;
  
  public static final ModuleMethod char$Mndowncase;
  
  public static final ModuleMethod char$Mnfoldcase;
  
  public static final ModuleMethod char$Mngeneral$Mncategory;
  
  public static final ModuleMethod char$Mnlower$Mncase$Qu;
  
  public static final ModuleMethod char$Mnnumeric$Qu;
  
  public static final ModuleMethod char$Mntitle$Mncase$Qu;
  
  public static final ModuleMethod char$Mntitlecase;
  
  public static final ModuleMethod char$Mnupcase;
  
  public static final ModuleMethod char$Mnupper$Mncase$Qu;
  
  public static final ModuleMethod char$Mnwhitespace$Qu;
  
  public static final ModuleMethod string$Mnci$Eq$Qu;
  
  public static final ModuleMethod string$Mnci$Gr$Eq$Qu;
  
  public static final ModuleMethod string$Mnci$Gr$Qu;
  
  public static final ModuleMethod string$Mnci$Ls$Eq$Qu;
  
  public static final ModuleMethod string$Mnci$Ls$Qu;
  
  public static final ModuleMethod string$Mndowncase;
  
  public static final ModuleMethod string$Mnfoldcase;
  
  public static final ModuleMethod string$Mnnormalize$Mnnfc;
  
  public static final ModuleMethod string$Mnnormalize$Mnnfd;
  
  public static final ModuleMethod string$Mnnormalize$Mnnfkc;
  
  public static final ModuleMethod string$Mnnormalize$Mnnfkd;
  
  public static final ModuleMethod string$Mntitlecase;
  
  public static final ModuleMethod string$Mnupcase;
  
  static {
    Lit27 = (SimpleSymbol)(new SimpleSymbol("string-normalize-nfc")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("string-normalize-nfkd")).readResolve();
    Lit25 = (SimpleSymbol)(new SimpleSymbol("string-normalize-nfd")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("string-ci>=?")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("string-ci<=?")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("string-ci>?")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("string-ci<?")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("string-ci=?")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("string-foldcase")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("string-titlecase")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("string-downcase")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("string-upcase")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("char-general-category")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("char-ci>=?")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("char-ci<=?")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("char-ci>?")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("char-ci<?")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("char-ci=?")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("char-foldcase")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("char-title-case?")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("char-lower-case?")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("char-upper-case?")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("char-whitespace?")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("char-numeric?")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("char-alphabetic?")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("char-titlecase")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("char-downcase")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("char-upcase")).readResolve();
    $instance = new unicode();
    unicode unicode1 = $instance;
    char$Mnupcase = new ModuleMethod(unicode1, 1, Lit0, 4097);
    char$Mndowncase = new ModuleMethod(unicode1, 2, Lit1, 4097);
    char$Mntitlecase = new ModuleMethod(unicode1, 3, Lit2, 4097);
    char$Mnalphabetic$Qu = new ModuleMethod(unicode1, 4, Lit3, 4097);
    char$Mnnumeric$Qu = new ModuleMethod(unicode1, 5, Lit4, 4097);
    char$Mnwhitespace$Qu = new ModuleMethod(unicode1, 6, Lit5, 4097);
    char$Mnupper$Mncase$Qu = new ModuleMethod(unicode1, 7, Lit6, 4097);
    char$Mnlower$Mncase$Qu = new ModuleMethod(unicode1, 8, Lit7, 4097);
    char$Mntitle$Mncase$Qu = new ModuleMethod(unicode1, 9, Lit8, 4097);
    char$Mnfoldcase = new ModuleMethod(unicode1, 10, Lit9, 4097);
    char$Mnci$Eq$Qu = new ModuleMethod(unicode1, 11, Lit10, 8194);
    char$Mnci$Ls$Qu = new ModuleMethod(unicode1, 12, Lit11, 8194);
    char$Mnci$Gr$Qu = new ModuleMethod(unicode1, 13, Lit12, 8194);
    char$Mnci$Ls$Eq$Qu = new ModuleMethod(unicode1, 14, Lit13, 8194);
    char$Mnci$Gr$Eq$Qu = new ModuleMethod(unicode1, 15, Lit14, 8194);
    char$Mngeneral$Mncategory = new ModuleMethod(unicode1, 16, Lit15, 4097);
    string$Mnupcase = new ModuleMethod(unicode1, 17, Lit16, 4097);
    string$Mndowncase = new ModuleMethod(unicode1, 18, Lit17, 4097);
    string$Mntitlecase = new ModuleMethod(unicode1, 19, Lit18, 4097);
    string$Mnfoldcase = new ModuleMethod(unicode1, 20, Lit19, 4097);
    string$Mnci$Eq$Qu = new ModuleMethod(unicode1, 21, Lit20, 8194);
    string$Mnci$Ls$Qu = new ModuleMethod(unicode1, 22, Lit21, 8194);
    string$Mnci$Gr$Qu = new ModuleMethod(unicode1, 23, Lit22, 8194);
    string$Mnci$Ls$Eq$Qu = new ModuleMethod(unicode1, 24, Lit23, 8194);
    string$Mnci$Gr$Eq$Qu = new ModuleMethod(unicode1, 25, Lit24, 8194);
    string$Mnnormalize$Mnnfd = new ModuleMethod(unicode1, 26, Lit25, 4097);
    string$Mnnormalize$Mnnfkd = new ModuleMethod(unicode1, 27, Lit26, 4097);
    string$Mnnormalize$Mnnfc = new ModuleMethod(unicode1, 28, Lit27, 4097);
    string$Mnnormalize$Mnnfkc = new ModuleMethod(unicode1, 29, Lit28, 4097);
    $instance.run();
  }
  
  public unicode() {
    ModuleInfo.register(this);
  }
  
  public static Char charDowncase(Char paramChar) {
    return Char.make(Character.toLowerCase(paramChar.intValue()));
  }
  
  public static Char charFoldcase(Char paramChar) {
    boolean bool;
    int i = paramChar.intValue();
    if (i == 304) {
      bool = true;
    } else {
      bool = false;
    } 
    return (bool ? bool : (i == 305)) ? paramChar : Char.make(Character.toLowerCase(Character.toUpperCase(i)));
  }
  
  public static Symbol charGeneralCategory(Char paramChar) {
    return UnicodeUtils.generalCategory(paramChar.intValue());
  }
  
  public static Char charTitlecase(Char paramChar) {
    return Char.make(Character.toTitleCase(paramChar.intValue()));
  }
  
  public static Char charUpcase(Char paramChar) {
    return Char.make(Character.toUpperCase(paramChar.intValue()));
  }
  
  public static boolean isCharAlphabetic(Char paramChar) {
    return Character.isLetter(paramChar.intValue());
  }
  
  public static boolean isCharCi$Eq(Char paramChar1, Char paramChar2) {
    return (Character.toUpperCase(paramChar1.intValue()) == Character.toUpperCase(paramChar2.intValue()));
  }
  
  public static boolean isCharCi$Gr(Char paramChar1, Char paramChar2) {
    return (Character.toUpperCase(paramChar1.intValue()) > Character.toUpperCase(paramChar2.intValue()));
  }
  
  public static boolean isCharCi$Gr$Eq(Char paramChar1, Char paramChar2) {
    return (Character.toUpperCase(paramChar1.intValue()) >= Character.toUpperCase(paramChar2.intValue()));
  }
  
  public static boolean isCharCi$Ls(Char paramChar1, Char paramChar2) {
    return (Character.toUpperCase(paramChar1.intValue()) < Character.toUpperCase(paramChar2.intValue()));
  }
  
  public static boolean isCharCi$Ls$Eq(Char paramChar1, Char paramChar2) {
    return (Character.toUpperCase(paramChar1.intValue()) <= Character.toUpperCase(paramChar2.intValue()));
  }
  
  public static boolean isCharLowerCase(Char paramChar) {
    return Character.isLowerCase(paramChar.intValue());
  }
  
  public static boolean isCharNumeric(Char paramChar) {
    return Character.isDigit(paramChar.intValue());
  }
  
  public static boolean isCharTitleCase(Char paramChar) {
    return Character.isTitleCase(paramChar.intValue());
  }
  
  public static boolean isCharUpperCase(Char paramChar) {
    return Character.isUpperCase(paramChar.intValue());
  }
  
  public static boolean isCharWhitespace(Char paramChar) {
    return UnicodeUtils.isWhitespace(paramChar.intValue());
  }
  
  public static boolean isStringCi$Eq(CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    return UnicodeUtils.foldCase(paramCharSequence1).equals(UnicodeUtils.foldCase(paramCharSequence2));
  }
  
  public static boolean isStringCi$Gr(CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    return (UnicodeUtils.foldCase(paramCharSequence1).compareTo(UnicodeUtils.foldCase(paramCharSequence2)) > 0);
  }
  
  public static boolean isStringCi$Gr$Eq(CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    return (UnicodeUtils.foldCase(paramCharSequence1).compareTo(UnicodeUtils.foldCase(paramCharSequence2)) >= 0);
  }
  
  public static boolean isStringCi$Ls(CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    return (UnicodeUtils.foldCase(paramCharSequence1).compareTo(UnicodeUtils.foldCase(paramCharSequence2)) < 0);
  }
  
  public static boolean isStringCi$Ls$Eq(CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    return (UnicodeUtils.foldCase(paramCharSequence1).compareTo(UnicodeUtils.foldCase(paramCharSequence2)) <= 0);
  }
  
  public static CharSequence stringDowncase(CharSequence paramCharSequence) {
    return (CharSequence)new FString(paramCharSequence.toString().toLowerCase(Locale.ENGLISH));
  }
  
  public static CharSequence stringFoldcase(CharSequence paramCharSequence) {
    return (CharSequence)new FString(UnicodeUtils.foldCase(paramCharSequence));
  }
  
  public static CharSequence stringNormalizeNfc(CharSequence paramCharSequence) {
    return (CharSequence)misc.error$V("unicode string normalization not available", new Object[0]);
  }
  
  public static CharSequence stringNormalizeNfd(CharSequence paramCharSequence) {
    return (CharSequence)misc.error$V("unicode string normalization not available", new Object[0]);
  }
  
  public static CharSequence stringNormalizeNfkc(CharSequence paramCharSequence) {
    return (CharSequence)misc.error$V("unicode string normalization not available", new Object[0]);
  }
  
  public static CharSequence stringNormalizeNfkd(CharSequence paramCharSequence) {
    return (CharSequence)misc.error$V("unicode string normalization not available", new Object[0]);
  }
  
  public static CharSequence stringTitlecase(CharSequence paramCharSequence) {
    if (paramCharSequence == null) {
      paramCharSequence = null;
      return (CharSequence)new FString(UnicodeUtils.capitalize((String)paramCharSequence));
    } 
    paramCharSequence = paramCharSequence.toString();
    return (CharSequence)new FString(UnicodeUtils.capitalize((String)paramCharSequence));
  }
  
  public static CharSequence stringUpcase(CharSequence paramCharSequence) {
    return (CharSequence)new FString(paramCharSequence.toString().toUpperCase(Locale.ENGLISH));
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        try {
          Char char_ = (Char)paramObject;
          return charUpcase(char_);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-upcase", 1, paramObject);
        } 
      case 2:
        try {
          Char char_ = (Char)paramObject;
          return charDowncase(char_);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-downcase", 1, paramObject);
        } 
      case 3:
        try {
          Char char_ = (Char)paramObject;
          return charTitlecase(char_);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-titlecase", 1, paramObject);
        } 
      case 4:
        try {
          Char char_ = (Char)paramObject;
          return isCharAlphabetic(char_) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-alphabetic?", 1, paramObject);
        } 
      case 5:
        try {
          Char char_ = (Char)paramObject;
          return isCharNumeric(char_) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-numeric?", 1, paramObject);
        } 
      case 6:
        try {
          Char char_ = (Char)paramObject;
          return isCharWhitespace(char_) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-whitespace?", 1, paramObject);
        } 
      case 7:
        try {
          Char char_ = (Char)paramObject;
          return isCharUpperCase(char_) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-upper-case?", 1, paramObject);
        } 
      case 8:
        try {
          Char char_ = (Char)paramObject;
          return isCharLowerCase(char_) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-lower-case?", 1, paramObject);
        } 
      case 9:
        try {
          Char char_ = (Char)paramObject;
          return isCharTitleCase(char_) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-title-case?", 1, paramObject);
        } 
      case 10:
        try {
          Char char_ = (Char)paramObject;
          return charFoldcase(char_);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-foldcase", 1, paramObject);
        } 
      case 16:
        try {
          Char char_ = (Char)paramObject;
          return charGeneralCategory(char_);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-general-category", 1, paramObject);
        } 
      case 17:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return stringUpcase(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-upcase", 1, paramObject);
        } 
      case 18:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return stringDowncase(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-downcase", 1, paramObject);
        } 
      case 19:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return stringTitlecase(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-titlecase", 1, paramObject);
        } 
      case 20:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return stringFoldcase(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-foldcase", 1, paramObject);
        } 
      case 26:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return stringNormalizeNfd(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-normalize-nfd", 1, paramObject);
        } 
      case 27:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return stringNormalizeNfkd(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-normalize-nfkd", 1, paramObject);
        } 
      case 28:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return stringNormalizeNfc(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-normalize-nfc", 1, paramObject);
        } 
      case 29:
        break;
    } 
    try {
      CharSequence charSequence = (CharSequence)paramObject;
      return stringNormalizeNfkc(charSequence);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "string-normalize-nfkc", 1, paramObject);
    } 
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 11:
        try {
          Char char_ = (Char)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isCharCi$Eq(char_, (Char)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char-ci=?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-ci=?", 1, paramObject1);
        } 
      case 12:
        try {
          Char char_ = (Char)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isCharCi$Ls(char_, (Char)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char-ci<?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-ci<?", 1, paramObject1);
        } 
      case 13:
        try {
          Char char_ = (Char)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isCharCi$Gr(char_, (Char)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char-ci>?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-ci>?", 1, paramObject1);
        } 
      case 14:
        try {
          Char char_ = (Char)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isCharCi$Ls$Eq(char_, (Char)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char-ci<=?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-ci<=?", 1, paramObject1);
        } 
      case 15:
        try {
          Char char_ = (Char)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isCharCi$Gr$Eq(char_, (Char)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char-ci>=?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-ci>=?", 1, paramObject1);
        } 
      case 21:
        try {
          CharSequence charSequence = (CharSequence)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isStringCi$Eq(charSequence, (CharSequence)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ci=?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ci=?", 1, paramObject1);
        } 
      case 22:
        try {
          CharSequence charSequence = (CharSequence)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isStringCi$Ls(charSequence, (CharSequence)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ci<?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ci<?", 1, paramObject1);
        } 
      case 23:
        try {
          CharSequence charSequence = (CharSequence)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isStringCi$Gr(charSequence, (CharSequence)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ci>?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ci>?", 1, paramObject1);
        } 
      case 24:
        try {
          CharSequence charSequence = (CharSequence)paramObject1;
          try {
            paramObject1 = paramObject2;
            return isStringCi$Ls$Eq(charSequence, (CharSequence)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-ci<=?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ci<=?", 1, paramObject1);
        } 
      case 25:
        break;
    } 
    try {
      CharSequence charSequence = (CharSequence)paramObject1;
      try {
        paramObject1 = paramObject2;
        return isStringCi$Gr$Eq(charSequence, (CharSequence)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "string-ci>=?", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "string-ci>=?", 1, paramObject1);
    } 
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 29:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 28:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 27:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 26:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 20:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 19:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 18:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 17:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 16:
        if (!(paramObject instanceof Char))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 10:
        if (!(paramObject instanceof Char))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 9:
        if (!(paramObject instanceof Char))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 8:
        if (!(paramObject instanceof Char))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 7:
        if (!(paramObject instanceof Char))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 6:
        if (!(paramObject instanceof Char))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 5:
        if (!(paramObject instanceof Char))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 4:
        if (!(paramObject instanceof Char))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 3:
        if (!(paramObject instanceof Char))
          return -786431; 
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
    if (!(paramObject instanceof Char))
      return -786431; 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 25:
        if (paramObject1 instanceof CharSequence) {
          paramCallContext.value1 = paramObject1;
          if (paramObject2 instanceof CharSequence) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 24:
        if (paramObject1 instanceof CharSequence) {
          paramCallContext.value1 = paramObject1;
          if (paramObject2 instanceof CharSequence) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 23:
        if (paramObject1 instanceof CharSequence) {
          paramCallContext.value1 = paramObject1;
          if (paramObject2 instanceof CharSequence) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 22:
        if (paramObject1 instanceof CharSequence) {
          paramCallContext.value1 = paramObject1;
          if (paramObject2 instanceof CharSequence) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 21:
        if (paramObject1 instanceof CharSequence) {
          paramCallContext.value1 = paramObject1;
          if (paramObject2 instanceof CharSequence) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 15:
        if (!(paramObject1 instanceof Char))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof Char))
          return -786430; 
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 14:
        if (!(paramObject1 instanceof Char))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof Char))
          return -786430; 
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 13:
        if (!(paramObject1 instanceof Char))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof Char))
          return -786430; 
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 12:
        if (!(paramObject1 instanceof Char))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof Char))
          return -786430; 
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 11:
        break;
    } 
    if (!(paramObject1 instanceof Char))
      return -786431; 
    paramCallContext.value1 = paramObject1;
    if (!(paramObject2 instanceof Char))
      return -786430; 
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/rnrs/unicode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
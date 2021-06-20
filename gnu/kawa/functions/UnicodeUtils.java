package gnu.kawa.functions;

import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.text.BreakIterator;

public class UnicodeUtils {
  static final Symbol Cc;
  
  static final Symbol Cf;
  
  static final Symbol Cn;
  
  static final Symbol Co;
  
  static final Symbol Cs;
  
  static final Symbol Ll;
  
  static final Symbol Lm;
  
  static final Symbol Lo;
  
  static final Symbol Lt;
  
  static final Symbol Lu;
  
  static final Symbol Mc;
  
  static final Symbol Me;
  
  static final Symbol Mn;
  
  static final Symbol Nd;
  
  static final Symbol Nl;
  
  static final Symbol No;
  
  static final Symbol Pc;
  
  static final Symbol Pd;
  
  static final Symbol Pe;
  
  static final Symbol Pf;
  
  static final Symbol Pi;
  
  static final Symbol Po;
  
  static final Symbol Ps;
  
  static final Symbol Sc;
  
  static final Symbol Sk;
  
  static final Symbol Sm;
  
  static final Symbol So;
  
  static final Symbol Zl;
  
  static final Symbol Zp;
  
  static final Symbol Zs;
  
  static {
    Namespace namespace = Namespace.EmptyNamespace;
    Mc = namespace.getSymbol("Mc");
    Pc = namespace.getSymbol("Pc");
    Cc = namespace.getSymbol("Cc");
    Sc = namespace.getSymbol("Sc");
    Pd = namespace.getSymbol("Pd");
    Nd = namespace.getSymbol("Nd");
    Me = namespace.getSymbol("Me");
    Pe = namespace.getSymbol("Pe");
    Pf = namespace.getSymbol("Pf");
    Cf = namespace.getSymbol("Cf");
    Pi = namespace.getSymbol("Pi");
    Nl = namespace.getSymbol("Nl");
    Zl = namespace.getSymbol("Zl");
    Ll = namespace.getSymbol("Ll");
    Sm = namespace.getSymbol("Sm");
    Lm = namespace.getSymbol("Lm");
    Sk = namespace.getSymbol("Sk");
    Mn = namespace.getSymbol("Mn");
    Lo = namespace.getSymbol("Lo");
    No = namespace.getSymbol("No");
    Po = namespace.getSymbol("Po");
    So = namespace.getSymbol("So");
    Zp = namespace.getSymbol("Zp");
    Co = namespace.getSymbol("Co");
    Zs = namespace.getSymbol("Zs");
    Ps = namespace.getSymbol("Ps");
    Cs = namespace.getSymbol("Cs");
    Lt = namespace.getSymbol("Lt");
    Cn = namespace.getSymbol("Cn");
    Lu = namespace.getSymbol("Lu");
  }
  
  public static String capitalize(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    BreakIterator breakIterator = BreakIterator.getWordInstance();
    breakIterator.setText(paramString);
    int j = breakIterator.first();
    int i = breakIterator.next();
    label19: while (i != -1) {
      boolean bool = false;
      int k = j;
      while (true) {
        boolean bool1 = bool;
        if (k < i)
          if (Character.isLetter(paramString.codePointAt(k))) {
            bool1 = true;
          } else {
            k++;
            continue;
          }  
        if (!bool1) {
          stringBuilder.append(paramString, j, i);
        } else {
          stringBuilder.append(Character.toTitleCase(paramString.charAt(j)));
          stringBuilder.append(paramString.substring(j + 1, i).toLowerCase());
        } 
        j = i;
        i = breakIterator.next();
        continue label19;
      } 
    } 
    return stringBuilder.toString();
  }
  
  public static String foldCase(CharSequence paramCharSequence) {
    // Byte code:
    //   0: aload_0
    //   1: invokeinterface length : ()I
    //   6: istore #6
    //   8: iload #6
    //   10: ifne -> 20
    //   13: ldc ''
    //   15: astore #8
    //   17: aload #8
    //   19: areturn
    //   20: aconst_null
    //   21: astore #8
    //   23: iconst_0
    //   24: istore_3
    //   25: iconst_0
    //   26: istore_2
    //   27: iload_2
    //   28: iload #6
    //   30: if_icmpne -> 168
    //   33: iconst_m1
    //   34: istore_1
    //   35: iload_1
    //   36: sipush #931
    //   39: if_icmpeq -> 56
    //   42: iload_1
    //   43: sipush #963
    //   46: if_icmpeq -> 56
    //   49: iload_1
    //   50: sipush #962
    //   53: if_icmpne -> 179
    //   56: iconst_1
    //   57: istore #4
    //   59: iload_1
    //   60: iflt -> 89
    //   63: iload_1
    //   64: sipush #304
    //   67: if_icmpeq -> 89
    //   70: iload_1
    //   71: sipush #305
    //   74: if_icmpeq -> 89
    //   77: aload #8
    //   79: astore #7
    //   81: iload_3
    //   82: istore #5
    //   84: iload #4
    //   86: ifeq -> 207
    //   89: aload #8
    //   91: astore #7
    //   93: aload #8
    //   95: ifnonnull -> 115
    //   98: aload #8
    //   100: astore #7
    //   102: iload_1
    //   103: iflt -> 115
    //   106: new java/lang/StringBuilder
    //   109: dup
    //   110: invokespecial <init> : ()V
    //   113: astore #7
    //   115: iload_2
    //   116: iload_3
    //   117: if_icmple -> 158
    //   120: aload_0
    //   121: iload_3
    //   122: iload_2
    //   123: invokeinterface subSequence : (II)Ljava/lang/CharSequence;
    //   128: invokeinterface toString : ()Ljava/lang/String;
    //   133: invokevirtual toUpperCase : ()Ljava/lang/String;
    //   136: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   139: astore #9
    //   141: aload #9
    //   143: astore #8
    //   145: aload #7
    //   147: ifnull -> 17
    //   150: aload #7
    //   152: aload #9
    //   154: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: pop
    //   158: iload_1
    //   159: ifge -> 185
    //   162: aload #7
    //   164: invokevirtual toString : ()Ljava/lang/String;
    //   167: areturn
    //   168: aload_0
    //   169: iload_2
    //   170: invokeinterface charAt : (I)C
    //   175: istore_1
    //   176: goto -> 35
    //   179: iconst_0
    //   180: istore #4
    //   182: goto -> 59
    //   185: iload #4
    //   187: ifeq -> 194
    //   190: sipush #963
    //   193: istore_1
    //   194: aload #7
    //   196: iload_1
    //   197: i2c
    //   198: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   201: pop
    //   202: iload_2
    //   203: iconst_1
    //   204: iadd
    //   205: istore #5
    //   207: iload_2
    //   208: iconst_1
    //   209: iadd
    //   210: istore_2
    //   211: aload #7
    //   213: astore #8
    //   215: iload #5
    //   217: istore_3
    //   218: goto -> 27
  }
  
  public static Symbol generalCategory(int paramInt) {
    switch (Character.getType(paramInt)) {
      default:
        return Cn;
      case 8:
        return Mc;
      case 23:
        return Pc;
      case 15:
        return Cc;
      case 26:
        return Sc;
      case 20:
        return Pd;
      case 9:
        return Nd;
      case 7:
        return Me;
      case 22:
        return Pe;
      case 30:
        return Pf;
      case 16:
        return Cf;
      case 29:
        return Pi;
      case 10:
        return Nl;
      case 13:
        return Zl;
      case 2:
        return Ll;
      case 25:
        return Sm;
      case 4:
        return Lm;
      case 27:
        return Sk;
      case 6:
        return Mn;
      case 5:
        return Lo;
      case 11:
        return No;
      case 24:
        return Po;
      case 28:
        return So;
      case 14:
        return Zp;
      case 18:
        return Co;
      case 12:
        return Zs;
      case 21:
        return Ps;
      case 19:
        return Cs;
      case 3:
        return Lt;
      case 1:
        break;
    } 
    return Lu;
  }
  
  public static boolean isWhitespace(int paramInt) {
    boolean bool2 = false;
    if (paramInt == 32 || (paramInt >= 9 && paramInt <= 13))
      return true; 
    boolean bool1 = bool2;
    if (paramInt >= 133) {
      if (paramInt == 133 || paramInt == 160 || paramInt == 5760 || paramInt == 6158)
        return true; 
      bool1 = bool2;
      if (paramInt >= 8192) {
        bool1 = bool2;
        if (paramInt <= 12288) {
          if (paramInt > 8202 && paramInt != 8232 && paramInt != 8233 && paramInt != 8239 && paramInt != 8287) {
            bool1 = bool2;
            return (paramInt == 12288) ? true : bool1;
          } 
          return true;
        } 
      } 
    } 
    return bool1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/UnicodeUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
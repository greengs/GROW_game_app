package androidx.core.graphics;

import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.core.util.Pair;

public final class PaintCompat {
  private static final String EM_STRING = "m";
  
  private static final String TOFU_STRING = "󟿽";
  
  private static final ThreadLocal<Pair<Rect, Rect>> sRectThreadLocal = new ThreadLocal<Pair<Rect, Rect>>();
  
  public static boolean hasGlyph(@NonNull Paint paramPaint, @NonNull String paramString) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #9
    //   3: getstatic android/os/Build$VERSION.SDK_INT : I
    //   6: bipush #23
    //   8: if_icmplt -> 21
    //   11: aload_0
    //   12: aload_1
    //   13: invokevirtual hasGlyph : (Ljava/lang/String;)Z
    //   16: istore #8
    //   18: iload #8
    //   20: ireturn
    //   21: aload_1
    //   22: invokevirtual length : ()I
    //   25: istore #6
    //   27: iload #6
    //   29: iconst_1
    //   30: if_icmpne -> 46
    //   33: aload_1
    //   34: iconst_0
    //   35: invokevirtual charAt : (I)C
    //   38: invokestatic isWhitespace : (C)Z
    //   41: ifeq -> 46
    //   44: iconst_1
    //   45: ireturn
    //   46: aload_0
    //   47: ldc '󟿽'
    //   49: invokevirtual measureText : (Ljava/lang/String;)F
    //   52: fstore_3
    //   53: aload_0
    //   54: ldc 'm'
    //   56: invokevirtual measureText : (Ljava/lang/String;)F
    //   59: fstore_2
    //   60: aload_0
    //   61: aload_1
    //   62: invokevirtual measureText : (Ljava/lang/String;)F
    //   65: fstore #4
    //   67: iload #9
    //   69: istore #8
    //   71: fload #4
    //   73: fconst_0
    //   74: fcmpl
    //   75: ifeq -> 18
    //   78: aload_1
    //   79: iconst_0
    //   80: aload_1
    //   81: invokevirtual length : ()I
    //   84: invokevirtual codePointCount : (II)I
    //   87: iconst_1
    //   88: if_icmple -> 163
    //   91: iload #9
    //   93: istore #8
    //   95: fload #4
    //   97: fconst_2
    //   98: fload_2
    //   99: fmul
    //   100: fcmpl
    //   101: ifgt -> 18
    //   104: fconst_0
    //   105: fstore_2
    //   106: iconst_0
    //   107: istore #5
    //   109: iload #5
    //   111: iload #6
    //   113: if_icmpge -> 152
    //   116: aload_1
    //   117: iload #5
    //   119: invokevirtual codePointAt : (I)I
    //   122: invokestatic charCount : (I)I
    //   125: istore #7
    //   127: fload_2
    //   128: aload_0
    //   129: aload_1
    //   130: iload #5
    //   132: iload #5
    //   134: iload #7
    //   136: iadd
    //   137: invokevirtual measureText : (Ljava/lang/String;II)F
    //   140: fadd
    //   141: fstore_2
    //   142: iload #5
    //   144: iload #7
    //   146: iadd
    //   147: istore #5
    //   149: goto -> 109
    //   152: iload #9
    //   154: istore #8
    //   156: fload #4
    //   158: fload_2
    //   159: fcmpl
    //   160: ifge -> 18
    //   163: fload #4
    //   165: fload_3
    //   166: fcmpl
    //   167: ifeq -> 172
    //   170: iconst_1
    //   171: ireturn
    //   172: invokestatic obtainEmptyRects : ()Landroidx/core/util/Pair;
    //   175: astore #10
    //   177: aload_0
    //   178: ldc '󟿽'
    //   180: iconst_0
    //   181: ldc '󟿽'
    //   183: invokevirtual length : ()I
    //   186: aload #10
    //   188: getfield first : Ljava/lang/Object;
    //   191: checkcast android/graphics/Rect
    //   194: invokevirtual getTextBounds : (Ljava/lang/String;IILandroid/graphics/Rect;)V
    //   197: aload_0
    //   198: aload_1
    //   199: iconst_0
    //   200: iload #6
    //   202: aload #10
    //   204: getfield second : Ljava/lang/Object;
    //   207: checkcast android/graphics/Rect
    //   210: invokevirtual getTextBounds : (Ljava/lang/String;IILandroid/graphics/Rect;)V
    //   213: aload #10
    //   215: getfield first : Ljava/lang/Object;
    //   218: checkcast android/graphics/Rect
    //   221: aload #10
    //   223: getfield second : Ljava/lang/Object;
    //   226: invokevirtual equals : (Ljava/lang/Object;)Z
    //   229: ifne -> 238
    //   232: iconst_1
    //   233: istore #8
    //   235: iload #8
    //   237: ireturn
    //   238: iconst_0
    //   239: istore #8
    //   241: goto -> 235
  }
  
  private static Pair<Rect, Rect> obtainEmptyRects() {
    Pair<Rect, Rect> pair = sRectThreadLocal.get();
    if (pair == null) {
      pair = new Pair(new Rect(), new Rect());
      sRectThreadLocal.set(pair);
      return pair;
    } 
    ((Rect)pair.first).setEmpty();
    ((Rect)pair.second).setEmpty();
    return pair;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/PaintCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
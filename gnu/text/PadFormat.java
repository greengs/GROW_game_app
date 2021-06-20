package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

public class PadFormat extends ReportFormat {
  Format fmt;
  
  int minWidth;
  
  char padChar;
  
  int where;
  
  public PadFormat(Format paramFormat, int paramInt) {
    this(paramFormat, paramInt, ' ', 100);
  }
  
  public PadFormat(Format paramFormat, int paramInt1, char paramChar, int paramInt2) {
    this.fmt = paramFormat;
    this.minWidth = paramInt1;
    this.padChar = paramChar;
    this.where = paramInt2;
  }
  
  public static int format(Format paramFormat, Object[] paramArrayOfObject, int paramInt1, Writer paramWriter, char paramChar, int paramInt2, int paramInt3, int paramInt4, int paramInt5, FieldPosition paramFieldPosition) throws IOException {
    // Byte code:
    //   0: new java/lang/StringBuffer
    //   3: dup
    //   4: sipush #200
    //   7: invokespecial <init> : (I)V
    //   10: astore #11
    //   12: aload_0
    //   13: instanceof gnu/text/ReportFormat
    //   16: ifeq -> 250
    //   19: aload_0
    //   20: checkcast gnu/text/ReportFormat
    //   23: aload_1
    //   24: iload_2
    //   25: aload #11
    //   27: aload #9
    //   29: invokevirtual format : ([Ljava/lang/Object;ILjava/lang/StringBuffer;Ljava/text/FieldPosition;)I
    //   32: istore_2
    //   33: aload #11
    //   35: invokevirtual length : ()I
    //   38: istore #10
    //   40: iload #10
    //   42: iload #5
    //   44: iload #6
    //   46: iload #7
    //   48: invokestatic padNeeded : (IIII)I
    //   51: istore #7
    //   53: iconst_0
    //   54: istore #6
    //   56: aload #11
    //   58: invokevirtual toString : ()Ljava/lang/String;
    //   61: astore_1
    //   62: iload #7
    //   64: ifle -> 321
    //   67: aload_1
    //   68: astore_0
    //   69: iload #8
    //   71: istore #5
    //   73: iload #8
    //   75: iconst_m1
    //   76: if_icmpne -> 213
    //   79: aload_1
    //   80: astore_0
    //   81: iload #10
    //   83: ifle -> 210
    //   86: aload_1
    //   87: iconst_0
    //   88: invokevirtual charAt : (I)C
    //   91: istore #5
    //   93: iload #5
    //   95: bipush #45
    //   97: if_icmpeq -> 107
    //   100: iload #5
    //   102: bipush #43
    //   104: if_icmpne -> 118
    //   107: iconst_0
    //   108: iconst_1
    //   109: iadd
    //   110: istore #6
    //   112: aload_3
    //   113: iload #5
    //   115: invokevirtual write : (I)V
    //   118: iload #6
    //   120: istore #5
    //   122: iload #10
    //   124: iload #6
    //   126: isub
    //   127: iconst_2
    //   128: if_icmple -> 196
    //   131: iload #6
    //   133: istore #5
    //   135: aload_1
    //   136: iload #6
    //   138: invokevirtual charAt : (I)C
    //   141: bipush #48
    //   143: if_icmpne -> 196
    //   146: aload_3
    //   147: bipush #48
    //   149: invokevirtual write : (I)V
    //   152: iload #6
    //   154: iconst_1
    //   155: iadd
    //   156: istore #6
    //   158: aload_1
    //   159: iload #6
    //   161: invokevirtual charAt : (I)C
    //   164: istore #8
    //   166: iload #8
    //   168: bipush #120
    //   170: if_icmpeq -> 184
    //   173: iload #6
    //   175: istore #5
    //   177: iload #8
    //   179: bipush #88
    //   181: if_icmpne -> 196
    //   184: iload #6
    //   186: iconst_1
    //   187: iadd
    //   188: istore #5
    //   190: aload_3
    //   191: iload #8
    //   193: invokevirtual write : (I)V
    //   196: aload_1
    //   197: astore_0
    //   198: iload #5
    //   200: ifle -> 210
    //   203: aload_1
    //   204: iload #5
    //   206: invokevirtual substring : (I)Ljava/lang/String;
    //   209: astore_0
    //   210: iconst_0
    //   211: istore #5
    //   213: iload #7
    //   215: iload #5
    //   217: imul
    //   218: bipush #100
    //   220: idiv
    //   221: istore #6
    //   223: iload #7
    //   225: iload #6
    //   227: isub
    //   228: istore #5
    //   230: iload #5
    //   232: iconst_1
    //   233: isub
    //   234: istore #5
    //   236: iload #5
    //   238: iflt -> 292
    //   241: aload_3
    //   242: iload #4
    //   244: invokevirtual write : (I)V
    //   247: goto -> 230
    //   250: aload_0
    //   251: instanceof java/text/MessageFormat
    //   254: ifeq -> 273
    //   257: aload_0
    //   258: aload_1
    //   259: aload #11
    //   261: aload #9
    //   263: invokevirtual format : (Ljava/lang/Object;Ljava/lang/StringBuffer;Ljava/text/FieldPosition;)Ljava/lang/StringBuffer;
    //   266: pop
    //   267: aload_1
    //   268: arraylength
    //   269: istore_2
    //   270: goto -> 33
    //   273: aload_0
    //   274: aload_1
    //   275: iload_2
    //   276: aaload
    //   277: aload #11
    //   279: aload #9
    //   281: invokevirtual format : (Ljava/lang/Object;Ljava/lang/StringBuffer;Ljava/text/FieldPosition;)Ljava/lang/StringBuffer;
    //   284: pop
    //   285: iload_2
    //   286: iconst_1
    //   287: iadd
    //   288: istore_2
    //   289: goto -> 33
    //   292: aload_3
    //   293: aload_0
    //   294: invokevirtual write : (Ljava/lang/String;)V
    //   297: iload #6
    //   299: istore #5
    //   301: iload #5
    //   303: iconst_1
    //   304: isub
    //   305: istore #5
    //   307: iload #5
    //   309: iflt -> 326
    //   312: aload_3
    //   313: iload #4
    //   315: invokevirtual write : (I)V
    //   318: goto -> 301
    //   321: aload_3
    //   322: aload_1
    //   323: invokevirtual write : (Ljava/lang/String;)V
    //   326: iload_2
    //   327: ireturn
  }
  
  public static int padNeeded(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int j = paramInt1 + paramInt4;
    int i = j;
    paramInt4 = paramInt3;
    if (paramInt3 <= 1) {
      paramInt4 = paramInt2 - j;
      i = j;
    } 
    while (i < paramInt2)
      i += paramInt4; 
    return i - paramInt1;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    return format(this.fmt, paramArrayOfObject, paramInt, paramWriter, this.padChar, this.minWidth, 1, 0, this.where, paramFieldPosition);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/PadFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
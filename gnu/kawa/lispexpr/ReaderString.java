package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderString extends ReadTableEntry {
  public static String readString(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    // Byte code:
    //   0: aload_0
    //   1: getfield tokenBufferLength : I
    //   4: istore #5
    //   6: aload_0
    //   7: invokevirtual getPort : ()Lgnu/text/LineBufferedReader;
    //   10: astore #6
    //   12: iconst_0
    //   13: istore_3
    //   14: iload_1
    //   15: istore #4
    //   17: iload #4
    //   19: istore_2
    //   20: aload #6
    //   22: instanceof gnu/mapping/InPort
    //   25: ifeq -> 50
    //   28: aload #6
    //   30: checkcast gnu/mapping/InPort
    //   33: getfield readState : C
    //   36: istore_3
    //   37: aload #6
    //   39: checkcast gnu/mapping/InPort
    //   42: iload_1
    //   43: i2c
    //   44: putfield readState : C
    //   47: iload #4
    //   49: istore_2
    //   50: iload_2
    //   51: bipush #13
    //   53: if_icmpne -> 132
    //   56: aload #6
    //   58: invokevirtual read : ()I
    //   61: istore #4
    //   63: iload #4
    //   65: istore_2
    //   66: iload #4
    //   68: bipush #10
    //   70: if_icmpeq -> 50
    //   73: iload #4
    //   75: istore_2
    //   76: iload_2
    //   77: iload_1
    //   78: if_icmpne -> 285
    //   81: new java/lang/String
    //   84: dup
    //   85: aload_0
    //   86: getfield tokenBuffer : [C
    //   89: iload #5
    //   91: aload_0
    //   92: getfield tokenBufferLength : I
    //   95: iload #5
    //   97: isub
    //   98: invokespecial <init> : ([CII)V
    //   101: invokevirtual intern : ()Ljava/lang/String;
    //   104: astore #7
    //   106: aload_0
    //   107: iload #5
    //   109: putfield tokenBufferLength : I
    //   112: aload #6
    //   114: instanceof gnu/mapping/InPort
    //   117: ifeq -> 129
    //   120: aload #6
    //   122: checkcast gnu/mapping/InPort
    //   125: iload_3
    //   126: putfield readState : C
    //   129: aload #7
    //   131: areturn
    //   132: aload #6
    //   134: getfield pos : I
    //   137: aload #6
    //   139: getfield limit : I
    //   142: if_icmpge -> 180
    //   145: iload_2
    //   146: bipush #10
    //   148: if_icmpeq -> 180
    //   151: aload #6
    //   153: getfield buffer : [C
    //   156: astore #7
    //   158: aload #6
    //   160: getfield pos : I
    //   163: istore_2
    //   164: aload #6
    //   166: iload_2
    //   167: iconst_1
    //   168: iadd
    //   169: putfield pos : I
    //   172: aload #7
    //   174: iload_2
    //   175: caload
    //   176: istore_2
    //   177: goto -> 76
    //   180: aload #6
    //   182: invokevirtual read : ()I
    //   185: istore_2
    //   186: goto -> 76
    //   189: iload_2
    //   190: ifge -> 199
    //   193: aload_0
    //   194: ldc 'unexpected EOF in string literal'
    //   196: invokevirtual eofError : (Ljava/lang/String;)V
    //   199: aload_0
    //   200: iload_2
    //   201: invokevirtual tokenBufferAppend : (I)V
    //   204: goto -> 50
    //   207: astore #7
    //   209: aload_0
    //   210: iload #5
    //   212: putfield tokenBufferLength : I
    //   215: aload #6
    //   217: instanceof gnu/mapping/InPort
    //   220: ifeq -> 232
    //   223: aload #6
    //   225: checkcast gnu/mapping/InPort
    //   228: iload_3
    //   229: putfield readState : C
    //   232: aload #7
    //   234: athrow
    //   235: aload #6
    //   237: invokevirtual getConvertCR : ()Z
    //   240: ifeq -> 315
    //   243: bipush #10
    //   245: istore #4
    //   247: aload_0
    //   248: iload #4
    //   250: invokevirtual tokenBufferAppend : (I)V
    //   253: goto -> 50
    //   256: aload_0
    //   257: instanceof gnu/kawa/lispexpr/LispReader
    //   260: ifeq -> 275
    //   263: aload_0
    //   264: checkcast gnu/kawa/lispexpr/LispReader
    //   267: invokevirtual readEscape : ()I
    //   270: istore #4
    //   272: goto -> 325
    //   275: aload #6
    //   277: invokevirtual read : ()I
    //   280: istore #4
    //   282: goto -> 325
    //   285: iload_2
    //   286: lookupswitch default -> 312, 13 -> 235, 92 -> 256
    //   312: goto -> 189
    //   315: bipush #13
    //   317: istore #4
    //   319: bipush #32
    //   321: istore_2
    //   322: goto -> 247
    //   325: iload #4
    //   327: istore_2
    //   328: iload #4
    //   330: bipush #-2
    //   332: if_icmpne -> 189
    //   335: bipush #10
    //   337: istore_2
    //   338: goto -> 50
    // Exception table:
    //   from	to	target	type
    //   56	63	207	finally
    //   81	106	207	finally
    //   132	145	207	finally
    //   151	172	207	finally
    //   180	186	207	finally
    //   193	199	207	finally
    //   199	204	207	finally
    //   235	243	207	finally
    //   247	253	207	finally
    //   256	272	207	finally
    //   275	282	207	finally
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    return readString(paramLexer, paramInt1, paramInt2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReaderString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
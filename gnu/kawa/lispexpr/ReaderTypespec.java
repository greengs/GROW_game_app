package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderTypespec extends ReadTableEntry {
  public int getKind() {
    return 6;
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    // Byte code:
    //   0: aload_1
    //   1: getfield tokenBufferLength : I
    //   4: istore #6
    //   6: aload_1
    //   7: invokevirtual getPort : ()Lgnu/text/LineBufferedReader;
    //   10: astore #7
    //   12: invokestatic getCurrent : ()Lgnu/kawa/lispexpr/ReadTable;
    //   15: astore #8
    //   17: iconst_0
    //   18: istore #4
    //   20: aload_1
    //   21: iload_2
    //   22: invokevirtual tokenBufferAppend : (I)V
    //   25: iload_2
    //   26: istore_3
    //   27: aload #7
    //   29: instanceof gnu/mapping/InPort
    //   32: ifeq -> 55
    //   35: aload #7
    //   37: checkcast gnu/mapping/InPort
    //   40: getfield readState : C
    //   43: istore #4
    //   45: aload #7
    //   47: checkcast gnu/mapping/InPort
    //   50: iload_2
    //   51: i2c
    //   52: putfield readState : C
    //   55: iconst_0
    //   56: istore_2
    //   57: aload #7
    //   59: getfield pos : I
    //   62: aload #7
    //   64: getfield limit : I
    //   67: if_icmpge -> 126
    //   70: iload_3
    //   71: bipush #10
    //   73: if_icmpeq -> 126
    //   76: aload #7
    //   78: getfield buffer : [C
    //   81: astore #9
    //   83: aload #7
    //   85: getfield pos : I
    //   88: istore_3
    //   89: aload #7
    //   91: iload_3
    //   92: iconst_1
    //   93: iadd
    //   94: putfield pos : I
    //   97: aload #9
    //   99: iload_3
    //   100: caload
    //   101: istore_3
    //   102: iload_3
    //   103: bipush #92
    //   105: if_icmpne -> 251
    //   108: aload_1
    //   109: instanceof gnu/kawa/lispexpr/LispReader
    //   112: ifeq -> 135
    //   115: aload_1
    //   116: checkcast gnu/kawa/lispexpr/LispReader
    //   119: invokevirtual readEscape : ()I
    //   122: istore_3
    //   123: goto -> 57
    //   126: aload #7
    //   128: invokevirtual read : ()I
    //   131: istore_3
    //   132: goto -> 102
    //   135: aload #7
    //   137: invokevirtual read : ()I
    //   140: istore_3
    //   141: goto -> 57
    //   144: aload #8
    //   146: iload_3
    //   147: invokevirtual lookup : (I)Lgnu/kawa/lispexpr/ReadTableEntry;
    //   150: invokevirtual getKind : ()I
    //   153: iconst_2
    //   154: if_icmpne -> 194
    //   157: aload_1
    //   158: iload_3
    //   159: invokevirtual tokenBufferAppend : (I)V
    //   162: goto -> 57
    //   165: astore #8
    //   167: aload_1
    //   168: iload #6
    //   170: putfield tokenBufferLength : I
    //   173: aload #7
    //   175: instanceof gnu/mapping/InPort
    //   178: ifeq -> 191
    //   181: aload #7
    //   183: checkcast gnu/mapping/InPort
    //   186: iload #4
    //   188: putfield readState : C
    //   191: aload #8
    //   193: athrow
    //   194: aload_1
    //   195: iload_3
    //   196: invokevirtual unread : (I)V
    //   199: new java/lang/String
    //   202: dup
    //   203: aload_1
    //   204: getfield tokenBuffer : [C
    //   207: iload #6
    //   209: aload_1
    //   210: getfield tokenBufferLength : I
    //   213: iload #6
    //   215: isub
    //   216: invokespecial <init> : ([CII)V
    //   219: invokevirtual intern : ()Ljava/lang/String;
    //   222: astore #8
    //   224: aload_1
    //   225: iload #6
    //   227: putfield tokenBufferLength : I
    //   230: aload #7
    //   232: instanceof gnu/mapping/InPort
    //   235: ifeq -> 248
    //   238: aload #7
    //   240: checkcast gnu/mapping/InPort
    //   243: iload #4
    //   245: putfield readState : C
    //   248: aload #8
    //   250: areturn
    //   251: iload_2
    //   252: istore #5
    //   254: iload_2
    //   255: ifne -> 277
    //   258: iload_2
    //   259: istore #5
    //   261: iload_3
    //   262: bipush #91
    //   264: if_icmpne -> 277
    //   267: iconst_1
    //   268: istore #5
    //   270: iconst_1
    //   271: istore_2
    //   272: iconst_1
    //   273: iconst_1
    //   274: if_icmpeq -> 157
    //   277: iload #5
    //   279: istore_2
    //   280: iload #5
    //   282: ifeq -> 144
    //   285: iload #5
    //   287: istore_2
    //   288: iload_3
    //   289: bipush #93
    //   291: if_icmpne -> 144
    //   294: iconst_0
    //   295: istore_2
    //   296: iconst_0
    //   297: istore #5
    //   299: iconst_0
    //   300: ifeq -> 157
    //   303: iload #5
    //   305: istore_2
    //   306: goto -> 144
    // Exception table:
    //   from	to	target	type
    //   57	70	165	finally
    //   76	97	165	finally
    //   108	123	165	finally
    //   126	132	165	finally
    //   135	141	165	finally
    //   144	157	165	finally
    //   157	162	165	finally
    //   194	224	165	finally
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReaderTypespec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
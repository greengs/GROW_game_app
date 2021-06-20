package gnu.kawa.xml;

public class Base64Binary extends BinaryObject {
  public static final String ENCODING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
  
  public Base64Binary(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_1
    //   5: invokevirtual length : ()I
    //   8: istore #9
    //   10: iconst_0
    //   11: istore #4
    //   13: iconst_0
    //   14: istore_3
    //   15: iload_3
    //   16: iload #9
    //   18: if_icmpge -> 65
    //   21: aload_1
    //   22: iload_3
    //   23: invokevirtual charAt : (I)C
    //   26: istore_2
    //   27: iload #4
    //   29: istore #5
    //   31: iload_2
    //   32: invokestatic isWhitespace : (C)Z
    //   35: ifne -> 54
    //   38: iload #4
    //   40: istore #5
    //   42: iload_2
    //   43: bipush #61
    //   45: if_icmpeq -> 54
    //   48: iload #4
    //   50: iconst_1
    //   51: iadd
    //   52: istore #5
    //   54: iload_3
    //   55: iconst_1
    //   56: iadd
    //   57: istore_3
    //   58: iload #5
    //   60: istore #4
    //   62: goto -> 15
    //   65: iload #4
    //   67: iconst_3
    //   68: imul
    //   69: iconst_4
    //   70: idiv
    //   71: newarray byte
    //   73: astore #10
    //   75: iconst_0
    //   76: istore #8
    //   78: iconst_0
    //   79: istore #5
    //   81: iconst_0
    //   82: istore #7
    //   84: iconst_0
    //   85: istore #6
    //   87: iconst_0
    //   88: istore #4
    //   90: iload #6
    //   92: iload #9
    //   94: if_icmpge -> 344
    //   97: aload_1
    //   98: iload #6
    //   100: invokevirtual charAt : (I)C
    //   103: istore_2
    //   104: iload_2
    //   105: bipush #65
    //   107: if_icmplt -> 158
    //   110: iload_2
    //   111: bipush #90
    //   113: if_icmpgt -> 158
    //   116: iload_2
    //   117: bipush #65
    //   119: isub
    //   120: istore_3
    //   121: iload_3
    //   122: iflt -> 130
    //   125: iload #7
    //   127: ifle -> 273
    //   130: new java/lang/IllegalArgumentException
    //   133: dup
    //   134: new java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial <init> : ()V
    //   141: ldc 'illegal character in base64Binary string at position '
    //   143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: iload #6
    //   148: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   151: invokevirtual toString : ()Ljava/lang/String;
    //   154: invokespecial <init> : (Ljava/lang/String;)V
    //   157: athrow
    //   158: iload_2
    //   159: bipush #97
    //   161: if_icmplt -> 181
    //   164: iload_2
    //   165: bipush #122
    //   167: if_icmpgt -> 181
    //   170: iload_2
    //   171: bipush #97
    //   173: isub
    //   174: bipush #26
    //   176: iadd
    //   177: istore_3
    //   178: goto -> 121
    //   181: iload_2
    //   182: bipush #48
    //   184: if_icmplt -> 204
    //   187: iload_2
    //   188: bipush #57
    //   190: if_icmpgt -> 204
    //   193: iload_2
    //   194: bipush #48
    //   196: isub
    //   197: bipush #52
    //   199: iadd
    //   200: istore_3
    //   201: goto -> 121
    //   204: iload_2
    //   205: bipush #43
    //   207: if_icmpne -> 216
    //   210: bipush #62
    //   212: istore_3
    //   213: goto -> 121
    //   216: iload_2
    //   217: bipush #47
    //   219: if_icmpne -> 228
    //   222: bipush #63
    //   224: istore_3
    //   225: goto -> 121
    //   228: iload_2
    //   229: invokestatic isWhitespace : (C)Z
    //   232: ifeq -> 250
    //   235: iload #4
    //   237: istore_3
    //   238: iload #6
    //   240: iconst_1
    //   241: iadd
    //   242: istore #6
    //   244: iload_3
    //   245: istore #4
    //   247: goto -> 90
    //   250: iload_2
    //   251: bipush #61
    //   253: if_icmpne -> 268
    //   256: iload #7
    //   258: iconst_1
    //   259: iadd
    //   260: istore #7
    //   262: iload #4
    //   264: istore_3
    //   265: goto -> 238
    //   268: iconst_m1
    //   269: istore_3
    //   270: goto -> 121
    //   273: iload #8
    //   275: bipush #6
    //   277: ishl
    //   278: iload_3
    //   279: iadd
    //   280: istore #8
    //   282: iload #5
    //   284: iconst_1
    //   285: iadd
    //   286: istore #5
    //   288: iload #5
    //   290: iconst_4
    //   291: if_icmpne -> 476
    //   294: iload #4
    //   296: iconst_1
    //   297: iadd
    //   298: istore_3
    //   299: aload #10
    //   301: iload #4
    //   303: iload #8
    //   305: bipush #16
    //   307: ishr
    //   308: i2b
    //   309: bastore
    //   310: iload_3
    //   311: iconst_1
    //   312: iadd
    //   313: istore #4
    //   315: aload #10
    //   317: iload_3
    //   318: iload #8
    //   320: bipush #8
    //   322: ishr
    //   323: i2b
    //   324: bastore
    //   325: iload #4
    //   327: iconst_1
    //   328: iadd
    //   329: istore_3
    //   330: aload #10
    //   332: iload #4
    //   334: iload #8
    //   336: i2b
    //   337: bastore
    //   338: iconst_0
    //   339: istore #5
    //   341: goto -> 238
    //   344: iload #5
    //   346: iload #7
    //   348: iadd
    //   349: ifle -> 394
    //   352: iload #5
    //   354: iload #7
    //   356: iadd
    //   357: iconst_4
    //   358: if_icmpne -> 386
    //   361: iconst_1
    //   362: iload #7
    //   364: ishl
    //   365: iconst_1
    //   366: isub
    //   367: iload #8
    //   369: iand
    //   370: ifne -> 386
    //   373: iload #4
    //   375: iconst_3
    //   376: iadd
    //   377: iload #7
    //   379: isub
    //   380: aload #10
    //   382: arraylength
    //   383: if_icmpeq -> 402
    //   386: new java/lang/IllegalArgumentException
    //   389: dup
    //   390: invokespecial <init> : ()V
    //   393: athrow
    //   394: iload #4
    //   396: aload #10
    //   398: arraylength
    //   399: if_icmpne -> 386
    //   402: iload #7
    //   404: tableswitch default -> 428, 1 -> 435, 2 -> 463
    //   428: aload_0
    //   429: aload #10
    //   431: putfield data : [B
    //   434: return
    //   435: iload #4
    //   437: iconst_1
    //   438: iadd
    //   439: istore_3
    //   440: aload #10
    //   442: iload #4
    //   444: iload #8
    //   446: bipush #10
    //   448: ishl
    //   449: i2b
    //   450: bastore
    //   451: aload #10
    //   453: iload_3
    //   454: iload #8
    //   456: iconst_2
    //   457: ishr
    //   458: i2b
    //   459: bastore
    //   460: goto -> 428
    //   463: aload #10
    //   465: iload #4
    //   467: iload #8
    //   469: iconst_4
    //   470: ishr
    //   471: i2b
    //   472: bastore
    //   473: goto -> 428
    //   476: iload #4
    //   478: istore_3
    //   479: goto -> 238
  }
  
  public Base64Binary(byte[] paramArrayOfbyte) {
    this.data = paramArrayOfbyte;
  }
  
  public static Base64Binary valueOf(String paramString) {
    return new Base64Binary(paramString);
  }
  
  public String toString() {
    return toString(new StringBuffer()).toString();
  }
  
  public StringBuffer toString(StringBuffer paramStringBuffer) {
    byte[] arrayOfByte = this.data;
    int k = arrayOfByte.length;
    int i = 0;
    int j = 0;
    while (j < k) {
      int m = i << 8 | arrayOfByte[j] & 0xFF;
      int n = j + 1;
      j = n;
      i = m;
      if (n % 3 == 0) {
        paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(m >> 18 & 0x3F));
        paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(m >> 12 & 0x3F));
        paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(m >> 6 & 0x3F));
        paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(m & 0x3F));
        j = n;
        i = m;
      } 
    } 
    switch (k % 3) {
      default:
        return paramStringBuffer;
      case 1:
        paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(i >> 2 & 0x3F));
        paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(i << 4 & 0x3F));
        paramStringBuffer.append("==");
        return paramStringBuffer;
      case 2:
        break;
    } 
    paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(i >> 10 & 0x3F));
    paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(i >> 4 & 0x3F));
    paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(i << 2 & 0x3F));
    paramStringBuffer.append('=');
    return paramStringBuffer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/Base64Binary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
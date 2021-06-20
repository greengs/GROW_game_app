package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class StackMapTableAttr extends MiscAttr {
  public static boolean compressStackMapTable = true;
  
  int countLocals;
  
  int countStack;
  
  int[] encodedLocals;
  
  int[] encodedStack;
  
  int numEntries;
  
  int prevPosition = -1;
  
  public StackMapTableAttr() {
    super("StackMapTable", (byte[])null, 0, 0);
    put2(0);
  }
  
  public StackMapTableAttr(byte[] paramArrayOfbyte, CodeAttr paramCodeAttr) {
    super("StackMapTable", paramArrayOfbyte, 0, paramArrayOfbyte.length);
    addToFrontOf(paramCodeAttr);
    this.numEntries = u2(0);
  }
  
  static int[] reallocBuffer(int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfint == null)
      return new int[paramInt + 10]; 
    int[] arrayOfInt = paramArrayOfint;
    if (paramInt > paramArrayOfint.length) {
      arrayOfInt = new int[paramInt + 10];
      System.arraycopy(paramArrayOfint, 0, arrayOfInt, 0, paramArrayOfint.length);
      return arrayOfInt;
    } 
    return arrayOfInt;
  }
  
  public void emitStackMapEntry(Label paramLabel, CodeAttr paramCodeAttr) {
    // Byte code:
    //   0: aload_1
    //   1: getfield position : I
    //   4: aload_0
    //   5: getfield prevPosition : I
    //   8: isub
    //   9: iconst_1
    //   10: isub
    //   11: istore #8
    //   13: aload_1
    //   14: getfield localTypes : [Lgnu/bytecode/Type;
    //   17: arraylength
    //   18: istore #7
    //   20: iload #7
    //   22: aload_0
    //   23: getfield encodedLocals : [I
    //   26: arraylength
    //   27: if_icmple -> 63
    //   30: aload_0
    //   31: getfield encodedLocals : [I
    //   34: arraylength
    //   35: iload #7
    //   37: iadd
    //   38: newarray int
    //   40: astore #12
    //   42: aload_0
    //   43: getfield encodedLocals : [I
    //   46: iconst_0
    //   47: aload #12
    //   49: iconst_0
    //   50: aload_0
    //   51: getfield countLocals : I
    //   54: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   57: aload_0
    //   58: aload #12
    //   60: putfield encodedLocals : [I
    //   63: aload_1
    //   64: getfield stackTypes : [Lgnu/bytecode/Type;
    //   67: arraylength
    //   68: istore #9
    //   70: iload #9
    //   72: aload_0
    //   73: getfield encodedStack : [I
    //   76: arraylength
    //   77: if_icmple -> 113
    //   80: aload_0
    //   81: getfield encodedStack : [I
    //   84: arraylength
    //   85: iload #9
    //   87: iadd
    //   88: newarray int
    //   90: astore #12
    //   92: aload_0
    //   93: getfield encodedStack : [I
    //   96: iconst_0
    //   97: aload #12
    //   99: iconst_0
    //   100: aload_0
    //   101: getfield countStack : I
    //   104: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   107: aload_0
    //   108: aload #12
    //   110: putfield encodedStack : [I
    //   113: iconst_0
    //   114: istore #5
    //   116: iconst_0
    //   117: istore #4
    //   119: iconst_0
    //   120: istore_3
    //   121: iload #4
    //   123: iload #7
    //   125: if_icmpge -> 681
    //   128: aload_0
    //   129: getfield encodedLocals : [I
    //   132: iload_3
    //   133: iaload
    //   134: istore #11
    //   136: aload_0
    //   137: aload_1
    //   138: getfield localTypes : [Lgnu/bytecode/Type;
    //   141: iload #4
    //   143: aaload
    //   144: aload_2
    //   145: invokevirtual encodeVerificationType : (Lgnu/bytecode/Type;Lgnu/bytecode/CodeAttr;)I
    //   148: istore #10
    //   150: iload #5
    //   152: istore #6
    //   154: iload #11
    //   156: iload #10
    //   158: if_icmpne -> 176
    //   161: iload #5
    //   163: istore #6
    //   165: iload #5
    //   167: iload_3
    //   168: if_icmpne -> 176
    //   171: iload_3
    //   172: iconst_1
    //   173: iadd
    //   174: istore #6
    //   176: aload_0
    //   177: getfield encodedLocals : [I
    //   180: iload_3
    //   181: iload #10
    //   183: iastore
    //   184: iload #10
    //   186: iconst_3
    //   187: if_icmpeq -> 200
    //   190: iload #4
    //   192: istore #5
    //   194: iload #10
    //   196: iconst_4
    //   197: if_icmpne -> 206
    //   200: iload #4
    //   202: iconst_1
    //   203: iadd
    //   204: istore #5
    //   206: iload #5
    //   208: iconst_1
    //   209: iadd
    //   210: istore #4
    //   212: iload_3
    //   213: iconst_1
    //   214: iadd
    //   215: istore_3
    //   216: iload #6
    //   218: istore #5
    //   220: goto -> 121
    //   223: iload #4
    //   225: ifle -> 249
    //   228: aload_0
    //   229: getfield encodedLocals : [I
    //   232: iload #4
    //   234: iconst_1
    //   235: isub
    //   236: iaload
    //   237: ifne -> 249
    //   240: iload #4
    //   242: iconst_1
    //   243: isub
    //   244: istore #4
    //   246: goto -> 223
    //   249: iconst_0
    //   250: istore_3
    //   251: iconst_0
    //   252: istore #6
    //   254: iload_3
    //   255: iload #9
    //   257: if_icmpge -> 340
    //   260: aload_0
    //   261: getfield encodedStack : [I
    //   264: iload #6
    //   266: iaload
    //   267: istore #7
    //   269: aload_1
    //   270: getfield stackTypes : [Lgnu/bytecode/Type;
    //   273: iload_3
    //   274: aaload
    //   275: astore #13
    //   277: iload_3
    //   278: istore #7
    //   280: aload #13
    //   282: astore #12
    //   284: aload #13
    //   286: getstatic gnu/bytecode/Type.voidType : Lgnu/bytecode/PrimType;
    //   289: if_acmpne -> 310
    //   292: aload_1
    //   293: getfield stackTypes : [Lgnu/bytecode/Type;
    //   296: astore #12
    //   298: iload_3
    //   299: iconst_1
    //   300: iadd
    //   301: istore #7
    //   303: aload #12
    //   305: iload #7
    //   307: aaload
    //   308: astore #12
    //   310: aload_0
    //   311: aload #12
    //   313: aload_2
    //   314: invokevirtual encodeVerificationType : (Lgnu/bytecode/Type;Lgnu/bytecode/CodeAttr;)I
    //   317: istore_3
    //   318: aload_0
    //   319: getfield encodedStack : [I
    //   322: iload #6
    //   324: iload_3
    //   325: iastore
    //   326: iload #7
    //   328: iconst_1
    //   329: iadd
    //   330: istore_3
    //   331: iload #6
    //   333: iconst_1
    //   334: iadd
    //   335: istore #6
    //   337: goto -> 254
    //   340: iload #4
    //   342: aload_0
    //   343: getfield countLocals : I
    //   346: isub
    //   347: istore #7
    //   349: getstatic gnu/bytecode/StackMapTableAttr.compressStackMapTable : Z
    //   352: ifeq -> 483
    //   355: iload #7
    //   357: ifne -> 483
    //   360: iload #4
    //   362: iload #5
    //   364: if_icmpne -> 483
    //   367: iload #6
    //   369: iconst_1
    //   370: if_icmpgt -> 483
    //   373: iload #6
    //   375: ifne -> 438
    //   378: iload #8
    //   380: bipush #63
    //   382: if_icmpgt -> 422
    //   385: aload_0
    //   386: iload #8
    //   388: invokevirtual put1 : (I)V
    //   391: aload_0
    //   392: iload #4
    //   394: putfield countLocals : I
    //   397: aload_0
    //   398: iload #6
    //   400: putfield countStack : I
    //   403: aload_0
    //   404: aload_1
    //   405: getfield position : I
    //   408: putfield prevPosition : I
    //   411: aload_0
    //   412: aload_0
    //   413: getfield numEntries : I
    //   416: iconst_1
    //   417: iadd
    //   418: putfield numEntries : I
    //   421: return
    //   422: aload_0
    //   423: sipush #251
    //   426: invokevirtual put1 : (I)V
    //   429: aload_0
    //   430: iload #8
    //   432: invokevirtual put2 : (I)V
    //   435: goto -> 391
    //   438: iload #8
    //   440: bipush #63
    //   442: if_icmpgt -> 467
    //   445: aload_0
    //   446: iload #8
    //   448: bipush #64
    //   450: iadd
    //   451: invokevirtual put1 : (I)V
    //   454: aload_0
    //   455: aload_0
    //   456: getfield encodedStack : [I
    //   459: iconst_0
    //   460: iaload
    //   461: invokevirtual emitVerificationType : (I)V
    //   464: goto -> 391
    //   467: aload_0
    //   468: sipush #247
    //   471: invokevirtual put1 : (I)V
    //   474: aload_0
    //   475: iload #8
    //   477: invokevirtual put2 : (I)V
    //   480: goto -> 454
    //   483: getstatic gnu/bytecode/StackMapTableAttr.compressStackMapTable : Z
    //   486: ifeq -> 536
    //   489: iload #6
    //   491: ifne -> 536
    //   494: iload #4
    //   496: aload_0
    //   497: getfield countLocals : I
    //   500: if_icmpge -> 536
    //   503: iload #5
    //   505: iload #4
    //   507: if_icmpne -> 536
    //   510: iload #7
    //   512: bipush #-3
    //   514: if_icmplt -> 536
    //   517: aload_0
    //   518: iload #7
    //   520: sipush #251
    //   523: iadd
    //   524: invokevirtual put1 : (I)V
    //   527: aload_0
    //   528: iload #8
    //   530: invokevirtual put2 : (I)V
    //   533: goto -> 391
    //   536: getstatic gnu/bytecode/StackMapTableAttr.compressStackMapTable : Z
    //   539: ifeq -> 606
    //   542: iload #6
    //   544: ifne -> 606
    //   547: aload_0
    //   548: getfield countLocals : I
    //   551: iload #5
    //   553: if_icmpne -> 606
    //   556: iload #7
    //   558: iconst_3
    //   559: if_icmpgt -> 606
    //   562: aload_0
    //   563: iload #7
    //   565: sipush #251
    //   568: iadd
    //   569: invokevirtual put1 : (I)V
    //   572: aload_0
    //   573: iload #8
    //   575: invokevirtual put2 : (I)V
    //   578: iconst_0
    //   579: istore_3
    //   580: iload_3
    //   581: iload #7
    //   583: if_icmpge -> 391
    //   586: aload_0
    //   587: aload_0
    //   588: getfield encodedLocals : [I
    //   591: iload #5
    //   593: iload_3
    //   594: iadd
    //   595: iaload
    //   596: invokevirtual emitVerificationType : (I)V
    //   599: iload_3
    //   600: iconst_1
    //   601: iadd
    //   602: istore_3
    //   603: goto -> 580
    //   606: aload_0
    //   607: sipush #255
    //   610: invokevirtual put1 : (I)V
    //   613: aload_0
    //   614: iload #8
    //   616: invokevirtual put2 : (I)V
    //   619: aload_0
    //   620: iload #4
    //   622: invokevirtual put2 : (I)V
    //   625: iconst_0
    //   626: istore_3
    //   627: iload_3
    //   628: iload #4
    //   630: if_icmpge -> 650
    //   633: aload_0
    //   634: aload_0
    //   635: getfield encodedLocals : [I
    //   638: iload_3
    //   639: iaload
    //   640: invokevirtual emitVerificationType : (I)V
    //   643: iload_3
    //   644: iconst_1
    //   645: iadd
    //   646: istore_3
    //   647: goto -> 627
    //   650: aload_0
    //   651: iload #6
    //   653: invokevirtual put2 : (I)V
    //   656: iconst_0
    //   657: istore_3
    //   658: iload_3
    //   659: iload #6
    //   661: if_icmpge -> 391
    //   664: aload_0
    //   665: aload_0
    //   666: getfield encodedStack : [I
    //   669: iload_3
    //   670: iaload
    //   671: invokevirtual emitVerificationType : (I)V
    //   674: iload_3
    //   675: iconst_1
    //   676: iadd
    //   677: istore_3
    //   678: goto -> 658
    //   681: iload_3
    //   682: istore #4
    //   684: goto -> 223
  }
  
  void emitVerificationType(int paramInt) {
    int i = paramInt & 0xFF;
    put1(i);
    if (i >= 7)
      put2(paramInt >> 8); 
  }
  
  int encodeVerificationType(Type paramType, CodeAttr paramCodeAttr) {
    Label label;
    if (paramType == null)
      return 0; 
    if (paramType instanceof UninitializedType) {
      label = ((UninitializedType)paramType).label;
      return (label == null) ? 6 : (label.position << 8 | 0x8);
    } 
    Type type = label.getImplementationType();
    if (type instanceof PrimType) {
      switch (type.signature.charAt(0)) {
        default:
          return 0;
        case 'B':
        case 'C':
        case 'I':
        case 'S':
        case 'Z':
          return 1;
        case 'J':
          return 4;
        case 'F':
          return 2;
        case 'D':
          break;
      } 
      return 3;
    } 
    return (type == Type.nullType) ? 5 : ((paramCodeAttr.getConstants().addClass((ObjectType)type)).index << 8 | 0x7);
  }
  
  int extractVerificationType(int paramInt1, int paramInt2) {
    if (paramInt2 != 7) {
      int i = paramInt2;
      return (paramInt2 == 8) ? (paramInt2 | u2(paramInt1 + 1) << 8) : i;
    } 
    return paramInt2 | u2(paramInt1 + 1) << 8;
  }
  
  int extractVerificationTypes(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfint) {
    int i = paramInt1;
    paramInt1 = paramInt3;
    paramInt3 = paramInt2;
    paramInt2 = i;
    while (true) {
      i = paramInt3 - 1;
      if (i >= 0) {
        if (paramInt2 >= this.dataLength) {
          paramInt3 = -1;
        } else {
          paramInt3 = this.data[paramInt2];
          int j = extractVerificationType(paramInt2, paramInt3);
          if (paramInt3 == 7 || paramInt3 == 8) {
            paramInt3 = 3;
          } else {
            paramInt3 = 1;
          } 
          paramInt2 += paramInt3;
          paramInt3 = j;
        } 
        paramArrayOfint[paramInt1] = paramInt3;
        paramInt1++;
        paramInt3 = i;
        continue;
      } 
      return paramInt2;
    } 
  }
  
  public Method getMethod() {
    return ((CodeAttr)this.container).getMethod();
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter) {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", number of entries: ");
    paramClassTypeWriter.println(this.numEntries);
    int j = 2;
    int k = -1;
    Method method = getMethod();
    int[] arrayOfInt = null;
    if (method.getStaticFlag()) {
      i = 0;
    } else {
      i = 1;
    } 
    int n = i + method.arg_types.length;
    int m = 0;
    int i = j;
    j = n;
    while (true) {
      if (m >= this.numEntries || i >= this.dataLength)
        return; 
      n = i + 1;
      int i1 = u1(i);
      i = k + 1;
      if (i1 <= 127) {
        k = i + (i1 & 0x3F);
        i = n;
      } else {
        if (n + 1 >= this.dataLength)
          return; 
        k = i + u2(n);
        i = n + 2;
      } 
      paramClassTypeWriter.print("  offset: ");
      paramClassTypeWriter.print(k);
      if (i1 <= 63) {
        paramClassTypeWriter.println(" - same_frame");
        continue;
      } 
      if (i1 <= 127 || i1 == 247) {
        String str;
        if (i1 <= 127) {
          str = " - same_locals_1_stack_item_frame";
        } else {
          str = " - same_locals_1_stack_item_frame_extended";
        } 
        paramClassTypeWriter.println(str);
        arrayOfInt = reallocBuffer(arrayOfInt, 1);
        i = extractVerificationTypes(i, 1, 0, arrayOfInt);
        printVerificationTypes(arrayOfInt, 0, 1, paramClassTypeWriter);
        continue;
      } 
      if (i1 <= 246) {
        paramClassTypeWriter.print(" - tag reserved for future use - ");
        paramClassTypeWriter.println(i1);
        return;
      } 
      if (i1 <= 250) {
        n = 251 - i1;
        paramClassTypeWriter.print(" - chop_frame - undefine ");
        paramClassTypeWriter.print(n);
        paramClassTypeWriter.println(" locals");
        j -= n;
        continue;
      } 
      if (i1 == 251) {
        paramClassTypeWriter.println(" - same_frame_extended");
        continue;
      } 
      if (i1 <= 254) {
        n = i1 - 251;
        paramClassTypeWriter.print(" - append_frame - define ");
        paramClassTypeWriter.print(n);
        paramClassTypeWriter.println(" more locals");
        arrayOfInt = reallocBuffer(arrayOfInt, j + n);
        i = extractVerificationTypes(i, n, j, arrayOfInt);
        printVerificationTypes(arrayOfInt, j, n, paramClassTypeWriter);
        j += n;
        continue;
      } 
      if (i + 1 >= this.dataLength)
        return; 
      j = u2(i);
      paramClassTypeWriter.print(" - full_frame.  Locals count: ");
      paramClassTypeWriter.println(j);
      arrayOfInt = reallocBuffer(arrayOfInt, j);
      n = extractVerificationTypes(i + 2, j, 0, arrayOfInt);
      printVerificationTypes(arrayOfInt, 0, j, paramClassTypeWriter);
      if (n + 1 >= this.dataLength)
        return; 
      i1 = u2(n);
      paramClassTypeWriter.print("    (end of locals)");
      i = Integer.toString(k).length();
      while (true) {
        if (--i >= 0) {
          paramClassTypeWriter.print(' ');
          continue;
        } 
        paramClassTypeWriter.print("       Stack count: ");
        paramClassTypeWriter.println(i1);
        arrayOfInt = reallocBuffer(arrayOfInt, i1);
        i = extractVerificationTypes(n + 2, i1, 0, arrayOfInt);
        printVerificationTypes(arrayOfInt, 0, i1, paramClassTypeWriter);
        if (i < 0) {
          paramClassTypeWriter.println("<ERROR - missing data>");
          return;
        } 
        break;
      } 
      m++;
    } 
  }
  
  void printVerificationType(int paramInt, ClassTypeWriter paramClassTypeWriter) {
    int i = paramInt & 0xFF;
    switch (i) {
      default:
        paramClassTypeWriter.print("<bad verification type tag " + i + '>');
        return;
      case 0:
        paramClassTypeWriter.print("top/unavailable");
        return;
      case 1:
        paramClassTypeWriter.print("integer");
        return;
      case 2:
        paramClassTypeWriter.print("float");
        return;
      case 3:
        paramClassTypeWriter.print("double");
        return;
      case 4:
        paramClassTypeWriter.print("long");
        return;
      case 5:
        paramClassTypeWriter.print("null");
        return;
      case 6:
        paramClassTypeWriter.print("uninitialized this");
        return;
      case 7:
        paramInt >>= 8;
        paramClassTypeWriter.printOptionalIndex(paramInt);
        paramClassTypeWriter.printConstantTersely(paramInt, 7);
        return;
      case 8:
        break;
    } 
    paramClassTypeWriter.print("uninitialized object created at ");
    paramClassTypeWriter.print(paramInt >> 8);
  }
  
  void printVerificationTypes(int[] paramArrayOfint, int paramInt1, int paramInt2, ClassTypeWriter paramClassTypeWriter) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #5
    //   3: iconst_0
    //   4: istore #6
    //   6: iload #6
    //   8: iload_2
    //   9: iload_3
    //   10: iadd
    //   11: if_icmpge -> 138
    //   14: aload_1
    //   15: iload #6
    //   17: iaload
    //   18: istore #7
    //   20: iload #7
    //   22: sipush #255
    //   25: iand
    //   26: istore #8
    //   28: iload #6
    //   30: iload_2
    //   31: if_icmplt -> 77
    //   34: aload #4
    //   36: ldc_w '  '
    //   39: invokevirtual print : (Ljava/lang/String;)V
    //   42: iload #5
    //   44: bipush #100
    //   46: if_icmplt -> 114
    //   49: aload #4
    //   51: iload #5
    //   53: invokevirtual print : (I)V
    //   56: aload #4
    //   58: ldc_w ': '
    //   61: invokevirtual print : (Ljava/lang/String;)V
    //   64: aload_0
    //   65: iload #7
    //   67: aload #4
    //   69: invokevirtual printVerificationType : (ILgnu/bytecode/ClassTypeWriter;)V
    //   72: aload #4
    //   74: invokevirtual println : ()V
    //   77: iload #5
    //   79: iconst_1
    //   80: iadd
    //   81: istore #7
    //   83: iload #8
    //   85: iconst_3
    //   86: if_icmpeq -> 99
    //   89: iload #7
    //   91: istore #5
    //   93: iload #8
    //   95: iconst_4
    //   96: if_icmpne -> 105
    //   99: iload #7
    //   101: iconst_1
    //   102: iadd
    //   103: istore #5
    //   105: iload #6
    //   107: iconst_1
    //   108: iadd
    //   109: istore #6
    //   111: goto -> 6
    //   114: iload #5
    //   116: bipush #10
    //   118: if_icmpge -> 128
    //   121: aload #4
    //   123: bipush #32
    //   125: invokevirtual print : (C)V
    //   128: aload #4
    //   130: bipush #32
    //   132: invokevirtual print : (C)V
    //   135: goto -> 49
    //   138: return
  }
  
  public void write(DataOutputStream paramDataOutputStream) throws IOException {
    put2(0, this.numEntries);
    super.write(paramDataOutputStream);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/StackMapTableAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.lists;

import gnu.text.Char;
import java.io.IOException;
import java.io.PrintWriter;

public class TreeList extends AbstractSequence implements Appendable, XConsumer, PositionConsumer, Consumable {
  protected static final int BEGIN_ATTRIBUTE_LONG = 61705;
  
  public static final int BEGIN_ATTRIBUTE_LONG_SIZE = 5;
  
  protected static final int BEGIN_DOCUMENT = 61712;
  
  protected static final int BEGIN_ELEMENT_LONG = 61704;
  
  protected static final int BEGIN_ELEMENT_SHORT = 40960;
  
  protected static final int BEGIN_ELEMENT_SHORT_INDEX_MAX = 4095;
  
  public static final int BEGIN_ENTITY = 61714;
  
  public static final int BEGIN_ENTITY_SIZE = 5;
  
  static final char BOOL_FALSE = '';
  
  static final char BOOL_TRUE = '';
  
  static final int BYTE_PREFIX = 61440;
  
  static final int CDATA_SECTION = 61717;
  
  static final int CHAR_FOLLOWS = 61702;
  
  static final int COMMENT = 61719;
  
  protected static final int DOCUMENT_URI = 61720;
  
  static final int DOUBLE_FOLLOWS = 61701;
  
  static final int END_ATTRIBUTE = 61706;
  
  public static final int END_ATTRIBUTE_SIZE = 1;
  
  protected static final int END_DOCUMENT = 61713;
  
  protected static final int END_ELEMENT_LONG = 61708;
  
  protected static final int END_ELEMENT_SHORT = 61707;
  
  protected static final int END_ENTITY = 61715;
  
  static final int FLOAT_FOLLOWS = 61700;
  
  public static final int INT_FOLLOWS = 61698;
  
  static final int INT_SHORT_ZERO = 49152;
  
  static final int JOINER = 61718;
  
  static final int LONG_FOLLOWS = 61699;
  
  public static final int MAX_CHAR_SHORT = 40959;
  
  static final int MAX_INT_SHORT = 8191;
  
  static final int MIN_INT_SHORT = -4096;
  
  static final char OBJECT_REF_FOLLOWS = '';
  
  static final int OBJECT_REF_SHORT = 57344;
  
  static final int OBJECT_REF_SHORT_INDEX_MAX = 4095;
  
  protected static final char POSITION_PAIR_FOLLOWS = '';
  
  static final char POSITION_REF_FOLLOWS = '';
  
  protected static final int PROCESSING_INSTRUCTION = 61716;
  
  public int attrStart;
  
  int currentParent = -1;
  
  public char[] data;
  
  public int docStart;
  
  public int gapEnd;
  
  public int gapStart;
  
  public Object[] objects;
  
  public int oindex;
  
  public TreeList() {
    resizeObjects();
    this.gapEnd = 200;
    this.data = new char[this.gapEnd];
  }
  
  public TreeList(TreeList paramTreeList) {
    this(paramTreeList, 0, paramTreeList.data.length);
  }
  
  public TreeList(TreeList paramTreeList, int paramInt1, int paramInt2) {
    this();
    paramTreeList.consumeIRange(paramInt1, paramInt2, this);
  }
  
  private Object copyToList(int paramInt1, int paramInt2) {
    return new TreeList(this, paramInt1, paramInt2);
  }
  
  public Consumer append(char paramChar) {
    write(paramChar);
    return this;
  }
  
  public Consumer append(CharSequence paramCharSequence) {
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence == null)
      charSequence = "null"; 
    return append(charSequence, 0, charSequence.length());
  }
  
  public Consumer append(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence == null)
      charSequence = "null"; 
    while (paramInt1 < paramInt2) {
      append(charSequence.charAt(paramInt1));
      paramInt1++;
    } 
    return this;
  }
  
  public void beginEntity(Object paramObject) {
    int i = -1;
    if (this.gapStart != 0)
      return; 
    ensureSpace(6);
    this.gapEnd--;
    int j = this.gapStart;
    this.data[j] = '';
    setIntN(j + 1, find(paramObject));
    if (this.currentParent != -1)
      i = this.currentParent - j; 
    setIntN(j + 3, i);
    this.gapStart = j + 5;
    this.currentParent = j;
    this.data[this.gapEnd] = '';
  }
  
  public void clear() {
    this.gapStart = 0;
    this.gapEnd = this.data.length;
    this.attrStart = 0;
    if (this.gapEnd > 1500) {
      this.gapEnd = 200;
      this.data = new char[this.gapEnd];
    } 
    this.objects = null;
    this.oindex = 0;
    resizeObjects();
  }
  
  public int compare(int paramInt1, int paramInt2) {
    paramInt1 = posToDataIndex(paramInt1);
    paramInt2 = posToDataIndex(paramInt2);
    return (paramInt1 < paramInt2) ? -1 : ((paramInt1 > paramInt2) ? 1 : 0);
  }
  
  public void consume(Consumer paramConsumer) {
    consumeIRange(0, this.data.length, paramConsumer);
  }
  
  public void consume(SeqPosition paramSeqPosition) {
    ensureSpace(3);
    int i = find(paramSeqPosition.copy());
    char[] arrayOfChar = this.data;
    int j = this.gapStart;
    this.gapStart = j + 1;
    arrayOfChar[j] = '';
    setIntN(this.gapStart, i);
    this.gapStart += 2;
  }
  
  public int consumeIRange(int paramInt1, int paramInt2, Consumer paramConsumer) {
    int i;
    int j = paramInt1;
    if (paramInt1 <= this.gapStart && paramInt2 > this.gapStart) {
      i = this.gapStart;
      paramInt1 = j;
    } else {
      i = paramInt2;
      paramInt1 = j;
    } 
    label120: while (true) {
      boolean bool;
      String str;
      AbstractSequence abstractSequence;
      j = i;
      int k = paramInt1;
      if (paramInt1 >= i)
        if (paramInt1 == this.gapStart && paramInt2 > this.gapEnd) {
          k = this.gapEnd;
          j = paramInt2;
        } else {
          return paramInt1;
        }  
      char[] arrayOfChar = this.data;
      paramInt1 = k + 1;
      i = arrayOfChar[k];
      if (i <= 40959) {
        k = paramInt1 - 1;
        while (true) {
          if (paramInt1 < j) {
            arrayOfChar = this.data;
            i = paramInt1 + 1;
            if (arrayOfChar[paramInt1] > '鿿') {
              paramInt1 = i - 1;
            } else {
              paramInt1 = i;
              continue;
            } 
          } 
          paramConsumer.write(this.data, k, paramInt1 - k);
          i = j;
          continue label120;
        } 
        break;
      } 
      if (i >= 57344 && i <= 61439) {
        paramConsumer.writeObject(this.objects[i - 57344]);
        i = j;
        continue;
      } 
      if (i >= 40960 && i <= 45055) {
        paramConsumer.startElement(this.objects[i - 40960]);
        paramInt1 += 2;
        i = j;
        continue;
      } 
      if (i >= 45056 && i <= 57343) {
        paramConsumer.writeInt(i - 49152);
        i = j;
        continue;
      } 
      switch (i) {
        default:
          throw new Error("unknown code:" + i);
        case 61712:
          paramConsumer.startDocument();
          paramInt1 += 4;
          i = j;
          continue;
        case 61713:
          paramConsumer.endDocument();
          i = j;
          continue;
        case 61714:
          if (paramConsumer instanceof TreeList)
            ((TreeList)paramConsumer).beginEntity(this.objects[getIntN(paramInt1)]); 
          paramInt1 += 4;
          i = j;
          continue;
        case 61715:
          if (paramConsumer instanceof TreeList) {
            ((TreeList)paramConsumer).endEntity();
            i = j;
            continue;
          } 
          break;
        case 61720:
          if (paramConsumer instanceof TreeList)
            ((TreeList)paramConsumer).writeDocumentUri(this.objects[getIntN(paramInt1)]); 
          paramInt1 += 2;
          i = j;
          continue;
        case 61719:
          i = getIntN(paramInt1);
          paramInt1 += 2;
          if (paramConsumer instanceof XConsumer)
            ((XConsumer)paramConsumer).writeComment(this.data, paramInt1, i); 
          paramInt1 += i;
          i = j;
          continue;
        case 61717:
          i = getIntN(paramInt1);
          paramInt1 += 2;
          if (paramConsumer instanceof XConsumer) {
            ((XConsumer)paramConsumer).writeCDATA(this.data, paramInt1, i);
          } else {
            paramConsumer.write(this.data, paramInt1, i);
          } 
          paramInt1 += i;
          i = j;
          continue;
        case 61716:
          str = (String)this.objects[getIntN(paramInt1)];
          i = getIntN(paramInt1 + 2);
          paramInt1 += 4;
          if (paramConsumer instanceof XConsumer)
            ((XConsumer)paramConsumer).writeProcessingInstruction(str, this.data, paramInt1, i); 
          paramInt1 += i;
          i = j;
          continue;
        case 61696:
        case 61697:
          if (i != 61696) {
            bool = true;
          } else {
            bool = false;
          } 
          paramConsumer.writeBoolean(bool);
          i = j;
          continue;
        case 61718:
          paramConsumer.write("");
          i = j;
          continue;
        case 61702:
          paramConsumer.write(this.data, paramInt1, i + 1 - 61702);
          paramInt1++;
          i = j;
          continue;
        case 61711:
          abstractSequence = (AbstractSequence)this.objects[getIntN(paramInt1)];
          i = getIntN(paramInt1 + 2);
          if (paramConsumer instanceof PositionConsumer) {
            ((PositionConsumer)paramConsumer).writePosition(abstractSequence, i);
          } else {
            paramConsumer.writeObject(abstractSequence.getIteratorAtPos(i));
          } 
          paramInt1 += 4;
          i = j;
          continue;
        case 61710:
          if (paramConsumer instanceof PositionConsumer) {
            ((PositionConsumer)paramConsumer).consume((SeqPosition)this.objects[getIntN(paramInt1)]);
            paramInt1 += 2;
            i = j;
            continue;
          } 
        case 61709:
          paramConsumer.writeObject(this.objects[getIntN(paramInt1)]);
          paramInt1 += 2;
          i = j;
          continue;
        case 61707:
          paramInt1++;
          paramConsumer.endElement();
          i = j;
          continue;
        case 61704:
          k = getIntN(paramInt1);
          if (k >= 0) {
            i = paramInt1 - 1;
          } else {
            i = this.data.length;
          } 
          paramInt1 += 2;
          i = getIntN(k + i + 1);
          paramConsumer.startElement(this.objects[i]);
          i = j;
          continue;
        case 61708:
          getIntN(paramInt1);
          paramConsumer.endElement();
          paramInt1 += 6;
          i = j;
          continue;
        case 61705:
          i = getIntN(paramInt1);
          paramConsumer.startAttribute(this.objects[i]);
          paramInt1 += 4;
          i = j;
          continue;
        case 61706:
          paramConsumer.endAttribute();
          i = j;
          continue;
        case 61698:
          paramConsumer.writeInt(getIntN(paramInt1));
          paramInt1 += 2;
          i = j;
          continue;
        case 61700:
          paramConsumer.writeFloat(Float.intBitsToFloat(getIntN(paramInt1)));
          paramInt1 += 2;
          i = j;
          continue;
        case 61699:
          paramConsumer.writeLong(getLongN(paramInt1));
          paramInt1 += 4;
          i = j;
          continue;
        case 61701:
          paramConsumer.writeDouble(Double.longBitsToDouble(getLongN(paramInt1)));
          paramInt1 += 4;
          i = j;
          continue;
      } 
      i = j;
    } 
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer) {
    if (!hasNext(paramInt))
      return false; 
    int j = posToDataIndex(paramInt);
    int i = nextNodeIndex(j, 2147483647);
    paramInt = i;
    if (i == j)
      paramInt = nextDataIndex(j); 
    if (paramInt >= 0)
      consumeIRange(j, paramInt, paramConsumer); 
    return true;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer) {
    consumeIRange(posToDataIndex(paramInt1), posToDataIndex(paramInt2), paramConsumer);
  }
  
  public int createPos(int paramInt, boolean paramBoolean) {
    return createRelativePos(0, paramInt, paramBoolean);
  }
  
  public int createRelativePos(int paramInt1, int paramInt2, boolean paramBoolean) {
    int i = paramInt2;
    if (paramBoolean) {
      if (paramInt2 == 0) {
        if ((paramInt1 & 0x1) != 0)
          return paramInt1; 
        if (paramInt1 == 0)
          return 1; 
      } 
      i = paramInt2 - 1;
    } 
    if (i < 0)
      throw unsupported("backwards createRelativePos"); 
    paramInt1 = posToDataIndex(paramInt1);
    while (true) {
      if (--i >= 0) {
        paramInt2 = nextDataIndex(paramInt1);
        paramInt1 = paramInt2;
        if (paramInt2 < 0)
          throw new IndexOutOfBoundsException(); 
        continue;
      } 
      paramInt2 = paramInt1;
      if (paramInt1 >= this.gapEnd)
        paramInt2 = paramInt1 - this.gapEnd - this.gapStart; 
      return paramBoolean ? (paramInt2 + 1 << 1 | 0x1) : (paramInt2 << 1);
    } 
  }
  
  public Object documentUriOfPos(int paramInt) {
    paramInt = posToDataIndex(paramInt);
    if (paramInt != this.data.length && this.data[paramInt] == '') {
      int i = paramInt + 5;
      paramInt = i;
      if (i == this.gapStart)
        paramInt = this.gapEnd; 
      if (paramInt < this.data.length && this.data[paramInt] == '')
        return this.objects[getIntN(paramInt + 1)]; 
    } 
    return null;
  }
  
  public void dump() {
    PrintWriter printWriter = new PrintWriter(System.out);
    dump(printWriter);
    printWriter.flush();
  }
  
  public void dump(PrintWriter paramPrintWriter) {
    paramPrintWriter.println(getClass().getName() + " @" + Integer.toHexString(System.identityHashCode(this)) + " gapStart:" + this.gapStart + " gapEnd:" + this.gapEnd + " length:" + this.data.length);
    dump(paramPrintWriter, 0, this.data.length);
  }
  
  public void dump(PrintWriter paramPrintWriter, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #5
    //   3: iload_2
    //   4: istore #4
    //   6: iload #5
    //   8: istore_2
    //   9: iload #4
    //   11: iload_3
    //   12: if_icmpge -> 1772
    //   15: iload #4
    //   17: aload_0
    //   18: getfield gapStart : I
    //   21: if_icmplt -> 40
    //   24: iload #4
    //   26: istore #6
    //   28: iload_2
    //   29: istore #5
    //   31: iload #4
    //   33: aload_0
    //   34: getfield gapEnd : I
    //   37: if_icmplt -> 203
    //   40: aload_0
    //   41: getfield data : [C
    //   44: iload #4
    //   46: caload
    //   47: istore #6
    //   49: aload_1
    //   50: new java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial <init> : ()V
    //   57: ldc_w ''
    //   60: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: iload #4
    //   65: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   68: ldc_w ': 0x'
    //   71: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: iload #6
    //   76: invokestatic toHexString : (I)Ljava/lang/String;
    //   79: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: bipush #61
    //   84: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   87: iload #6
    //   89: i2s
    //   90: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   93: invokevirtual toString : ()Ljava/lang/String;
    //   96: invokevirtual print : (Ljava/lang/String;)V
    //   99: iload_2
    //   100: iconst_1
    //   101: isub
    //   102: istore #5
    //   104: iload #5
    //   106: istore_2
    //   107: iload #5
    //   109: ifge -> 168
    //   112: iload #6
    //   114: ldc 40959
    //   116: if_icmpgt -> 275
    //   119: iload #6
    //   121: bipush #32
    //   123: if_icmplt -> 215
    //   126: iload #6
    //   128: bipush #127
    //   130: if_icmpge -> 215
    //   133: aload_1
    //   134: new java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial <init> : ()V
    //   141: ldc_w '=''
    //   144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: iload #6
    //   149: i2c
    //   150: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   153: ldc_w '''
    //   156: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: invokevirtual toString : ()Ljava/lang/String;
    //   162: invokevirtual print : (Ljava/lang/String;)V
    //   165: iload #5
    //   167: istore_2
    //   168: aload_1
    //   169: invokevirtual println : ()V
    //   172: iload #4
    //   174: istore #6
    //   176: iload_2
    //   177: istore #5
    //   179: iconst_1
    //   180: ifeq -> 203
    //   183: iload #4
    //   185: istore #6
    //   187: iload_2
    //   188: istore #5
    //   190: iload_2
    //   191: ifle -> 203
    //   194: iload #4
    //   196: iload_2
    //   197: iadd
    //   198: istore #6
    //   200: iconst_0
    //   201: istore #5
    //   203: iload #6
    //   205: iconst_1
    //   206: iadd
    //   207: istore #4
    //   209: iload #5
    //   211: istore_2
    //   212: goto -> 9
    //   215: iload #6
    //   217: bipush #10
    //   219: if_icmpne -> 235
    //   222: aload_1
    //   223: ldc_w '='\n''
    //   226: invokevirtual print : (Ljava/lang/String;)V
    //   229: iload #5
    //   231: istore_2
    //   232: goto -> 168
    //   235: aload_1
    //   236: new java/lang/StringBuilder
    //   239: dup
    //   240: invokespecial <init> : ()V
    //   243: ldc_w '='\u'
    //   246: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   249: iload #6
    //   251: invokestatic toHexString : (I)Ljava/lang/String;
    //   254: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: ldc_w '''
    //   260: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: invokevirtual toString : ()Ljava/lang/String;
    //   266: invokevirtual print : (Ljava/lang/String;)V
    //   269: iload #5
    //   271: istore_2
    //   272: goto -> 168
    //   275: iload #6
    //   277: ldc 57344
    //   279: if_icmplt -> 375
    //   282: iload #6
    //   284: ldc 61439
    //   286: if_icmpgt -> 375
    //   289: iload #6
    //   291: ldc 57344
    //   293: isub
    //   294: istore_2
    //   295: aload_0
    //   296: getfield objects : [Ljava/lang/Object;
    //   299: iload_2
    //   300: aaload
    //   301: astore #9
    //   303: aload_1
    //   304: new java/lang/StringBuilder
    //   307: dup
    //   308: invokespecial <init> : ()V
    //   311: ldc_w '=Object#'
    //   314: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: iload_2
    //   318: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   321: bipush #61
    //   323: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   326: aload #9
    //   328: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   331: bipush #58
    //   333: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   336: aload #9
    //   338: invokevirtual getClass : ()Ljava/lang/Class;
    //   341: invokevirtual getName : ()Ljava/lang/String;
    //   344: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: bipush #64
    //   349: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   352: aload #9
    //   354: invokestatic identityHashCode : (Ljava/lang/Object;)I
    //   357: invokestatic toHexString : (I)Ljava/lang/String;
    //   360: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   363: invokevirtual toString : ()Ljava/lang/String;
    //   366: invokevirtual print : (Ljava/lang/String;)V
    //   369: iload #5
    //   371: istore_2
    //   372: goto -> 168
    //   375: iload #6
    //   377: ldc 40960
    //   379: if_icmplt -> 469
    //   382: iload #6
    //   384: ldc 45055
    //   386: if_icmpgt -> 469
    //   389: iload #6
    //   391: ldc 40960
    //   393: isub
    //   394: istore_2
    //   395: aload_0
    //   396: getfield data : [C
    //   399: iload #4
    //   401: iconst_1
    //   402: iadd
    //   403: caload
    //   404: istore #5
    //   406: aload_1
    //   407: new java/lang/StringBuilder
    //   410: dup
    //   411: invokespecial <init> : ()V
    //   414: ldc_w '=BEGIN_ELEMENT_SHORT end:'
    //   417: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: iload #5
    //   422: iload #4
    //   424: iadd
    //   425: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   428: ldc_w ' index#'
    //   431: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   434: iload_2
    //   435: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   438: ldc_w '=<'
    //   441: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: aload_0
    //   445: getfield objects : [Ljava/lang/Object;
    //   448: iload_2
    //   449: aaload
    //   450: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   453: bipush #62
    //   455: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   458: invokevirtual toString : ()Ljava/lang/String;
    //   461: invokevirtual print : (Ljava/lang/String;)V
    //   464: iconst_2
    //   465: istore_2
    //   466: goto -> 168
    //   469: iload #6
    //   471: ldc 45056
    //   473: if_icmplt -> 517
    //   476: iload #6
    //   478: ldc 57343
    //   480: if_icmpgt -> 517
    //   483: aload_1
    //   484: new java/lang/StringBuilder
    //   487: dup
    //   488: invokespecial <init> : ()V
    //   491: ldc_w '= INT_SHORT:'
    //   494: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   497: iload #6
    //   499: ldc 49152
    //   501: isub
    //   502: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   505: invokevirtual toString : ()Ljava/lang/String;
    //   508: invokevirtual print : (Ljava/lang/String;)V
    //   511: iload #5
    //   513: istore_2
    //   514: goto -> 168
    //   517: iload #6
    //   519: tableswitch default -> 632, 61696 -> 638, 61697 -> 1133, 61698 -> 651, 61699 -> 689, 61700 -> 729, 61701 -> 770, 61702 -> 1159, 61703 -> 632, 61704 -> 1251, 61705 -> 1534, 61706 -> 1641, 61707 -> 1176, 61708 -> 1379, 61709 -> 1171, 61710 -> 1171, 61711 -> 1654, 61712 -> 813, 61713 -> 1120, 61714 -> 879, 61715 -> 924, 61716 -> 1052, 61717 -> 1010, 61718 -> 1146, 61719 -> 968, 61720 -> 937
    //   632: iload #5
    //   634: istore_2
    //   635: goto -> 168
    //   638: aload_1
    //   639: ldc_w '= false'
    //   642: invokevirtual print : (Ljava/lang/String;)V
    //   645: iload #5
    //   647: istore_2
    //   648: goto -> 168
    //   651: aload_0
    //   652: iload #4
    //   654: iconst_1
    //   655: iadd
    //   656: invokevirtual getIntN : (I)I
    //   659: istore_2
    //   660: aload_1
    //   661: new java/lang/StringBuilder
    //   664: dup
    //   665: invokespecial <init> : ()V
    //   668: ldc_w '=INT_FOLLOWS value:'
    //   671: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   674: iload_2
    //   675: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   678: invokevirtual toString : ()Ljava/lang/String;
    //   681: invokevirtual print : (Ljava/lang/String;)V
    //   684: iconst_2
    //   685: istore_2
    //   686: goto -> 168
    //   689: aload_0
    //   690: iload #4
    //   692: iconst_1
    //   693: iadd
    //   694: invokevirtual getLongN : (I)J
    //   697: lstore #7
    //   699: aload_1
    //   700: new java/lang/StringBuilder
    //   703: dup
    //   704: invokespecial <init> : ()V
    //   707: ldc_w '=LONG_FOLLOWS value:'
    //   710: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   713: lload #7
    //   715: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   718: invokevirtual toString : ()Ljava/lang/String;
    //   721: invokevirtual print : (Ljava/lang/String;)V
    //   724: iconst_4
    //   725: istore_2
    //   726: goto -> 168
    //   729: aload_0
    //   730: iload #4
    //   732: iconst_1
    //   733: iadd
    //   734: invokevirtual getIntN : (I)I
    //   737: istore_2
    //   738: aload_1
    //   739: new java/lang/StringBuilder
    //   742: dup
    //   743: invokespecial <init> : ()V
    //   746: ldc_w '=FLOAT_FOLLOWS value:'
    //   749: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   752: iload_2
    //   753: invokestatic intBitsToFloat : (I)F
    //   756: invokevirtual append : (F)Ljava/lang/StringBuilder;
    //   759: invokevirtual toString : ()Ljava/lang/String;
    //   762: invokevirtual write : (Ljava/lang/String;)V
    //   765: iconst_2
    //   766: istore_2
    //   767: goto -> 168
    //   770: aload_0
    //   771: iload #4
    //   773: iconst_1
    //   774: iadd
    //   775: invokevirtual getLongN : (I)J
    //   778: lstore #7
    //   780: aload_1
    //   781: new java/lang/StringBuilder
    //   784: dup
    //   785: invokespecial <init> : ()V
    //   788: ldc_w '=DOUBLE_FOLLOWS value:'
    //   791: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   794: lload #7
    //   796: invokestatic longBitsToDouble : (J)D
    //   799: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   802: invokevirtual toString : ()Ljava/lang/String;
    //   805: invokevirtual print : (Ljava/lang/String;)V
    //   808: iconst_4
    //   809: istore_2
    //   810: goto -> 168
    //   813: aload_0
    //   814: iload #4
    //   816: iconst_1
    //   817: iadd
    //   818: invokevirtual getIntN : (I)I
    //   821: istore #5
    //   823: iload #5
    //   825: ifge -> 873
    //   828: aload_0
    //   829: getfield data : [C
    //   832: arraylength
    //   833: istore_2
    //   834: aload_1
    //   835: ldc_w '=BEGIN_DOCUMENT end:'
    //   838: invokevirtual print : (Ljava/lang/String;)V
    //   841: aload_1
    //   842: iload #5
    //   844: iload_2
    //   845: iadd
    //   846: invokevirtual print : (I)V
    //   849: aload_1
    //   850: ldc_w ' parent:'
    //   853: invokevirtual print : (Ljava/lang/String;)V
    //   856: aload_1
    //   857: aload_0
    //   858: iload #4
    //   860: iconst_3
    //   861: iadd
    //   862: invokevirtual getIntN : (I)I
    //   865: invokevirtual print : (I)V
    //   868: iconst_4
    //   869: istore_2
    //   870: goto -> 168
    //   873: iload #4
    //   875: istore_2
    //   876: goto -> 834
    //   879: aload_0
    //   880: iload #4
    //   882: iconst_1
    //   883: iadd
    //   884: invokevirtual getIntN : (I)I
    //   887: istore_2
    //   888: aload_1
    //   889: ldc_w '=BEGIN_ENTITY base:'
    //   892: invokevirtual print : (Ljava/lang/String;)V
    //   895: aload_1
    //   896: iload_2
    //   897: invokevirtual print : (I)V
    //   900: aload_1
    //   901: ldc_w ' parent:'
    //   904: invokevirtual print : (Ljava/lang/String;)V
    //   907: aload_1
    //   908: aload_0
    //   909: iload #4
    //   911: iconst_3
    //   912: iadd
    //   913: invokevirtual getIntN : (I)I
    //   916: invokevirtual print : (I)V
    //   919: iconst_4
    //   920: istore_2
    //   921: goto -> 168
    //   924: aload_1
    //   925: ldc_w '=END_ENTITY'
    //   928: invokevirtual print : (Ljava/lang/String;)V
    //   931: iload #5
    //   933: istore_2
    //   934: goto -> 168
    //   937: aload_1
    //   938: ldc_w '=DOCUMENT_URI: '
    //   941: invokevirtual print : (Ljava/lang/String;)V
    //   944: aload_0
    //   945: iload #4
    //   947: iconst_1
    //   948: iadd
    //   949: invokevirtual getIntN : (I)I
    //   952: istore_2
    //   953: aload_1
    //   954: aload_0
    //   955: getfield objects : [Ljava/lang/Object;
    //   958: iload_2
    //   959: aaload
    //   960: invokevirtual print : (Ljava/lang/Object;)V
    //   963: iconst_2
    //   964: istore_2
    //   965: goto -> 168
    //   968: aload_1
    //   969: ldc_w '=COMMENT: ''
    //   972: invokevirtual print : (Ljava/lang/String;)V
    //   975: aload_0
    //   976: iload #4
    //   978: iconst_1
    //   979: iadd
    //   980: invokevirtual getIntN : (I)I
    //   983: istore_2
    //   984: aload_1
    //   985: aload_0
    //   986: getfield data : [C
    //   989: iload #4
    //   991: iconst_3
    //   992: iadd
    //   993: iload_2
    //   994: invokevirtual write : ([CII)V
    //   997: aload_1
    //   998: bipush #39
    //   1000: invokevirtual print : (C)V
    //   1003: iload_2
    //   1004: iconst_2
    //   1005: iadd
    //   1006: istore_2
    //   1007: goto -> 168
    //   1010: aload_1
    //   1011: ldc_w '=CDATA: ''
    //   1014: invokevirtual print : (Ljava/lang/String;)V
    //   1017: aload_0
    //   1018: iload #4
    //   1020: iconst_1
    //   1021: iadd
    //   1022: invokevirtual getIntN : (I)I
    //   1025: istore_2
    //   1026: aload_1
    //   1027: aload_0
    //   1028: getfield data : [C
    //   1031: iload #4
    //   1033: iconst_3
    //   1034: iadd
    //   1035: iload_2
    //   1036: invokevirtual write : ([CII)V
    //   1039: aload_1
    //   1040: bipush #39
    //   1042: invokevirtual print : (C)V
    //   1045: iload_2
    //   1046: iconst_2
    //   1047: iadd
    //   1048: istore_2
    //   1049: goto -> 168
    //   1052: aload_1
    //   1053: ldc_w '=PROCESSING_INSTRUCTION: '
    //   1056: invokevirtual print : (Ljava/lang/String;)V
    //   1059: aload_0
    //   1060: iload #4
    //   1062: iconst_1
    //   1063: iadd
    //   1064: invokevirtual getIntN : (I)I
    //   1067: istore_2
    //   1068: aload_1
    //   1069: aload_0
    //   1070: getfield objects : [Ljava/lang/Object;
    //   1073: iload_2
    //   1074: aaload
    //   1075: invokevirtual print : (Ljava/lang/Object;)V
    //   1078: aload_1
    //   1079: ldc_w ' ''
    //   1082: invokevirtual print : (Ljava/lang/String;)V
    //   1085: aload_0
    //   1086: iload #4
    //   1088: iconst_3
    //   1089: iadd
    //   1090: invokevirtual getIntN : (I)I
    //   1093: istore_2
    //   1094: aload_1
    //   1095: aload_0
    //   1096: getfield data : [C
    //   1099: iload #4
    //   1101: iconst_5
    //   1102: iadd
    //   1103: iload_2
    //   1104: invokevirtual write : ([CII)V
    //   1107: aload_1
    //   1108: bipush #39
    //   1110: invokevirtual print : (C)V
    //   1113: iload_2
    //   1114: iconst_4
    //   1115: iadd
    //   1116: istore_2
    //   1117: goto -> 168
    //   1120: aload_1
    //   1121: ldc_w '=END_DOCUMENT'
    //   1124: invokevirtual print : (Ljava/lang/String;)V
    //   1127: iload #5
    //   1129: istore_2
    //   1130: goto -> 168
    //   1133: aload_1
    //   1134: ldc_w '= true'
    //   1137: invokevirtual print : (Ljava/lang/String;)V
    //   1140: iload #5
    //   1142: istore_2
    //   1143: goto -> 168
    //   1146: aload_1
    //   1147: ldc_w '= joiner'
    //   1150: invokevirtual print : (Ljava/lang/String;)V
    //   1153: iload #5
    //   1155: istore_2
    //   1156: goto -> 168
    //   1159: aload_1
    //   1160: ldc_w '=CHAR_FOLLOWS'
    //   1163: invokevirtual print : (Ljava/lang/String;)V
    //   1166: iconst_1
    //   1167: istore_2
    //   1168: goto -> 168
    //   1171: iconst_2
    //   1172: istore_2
    //   1173: goto -> 168
    //   1176: aload_1
    //   1177: ldc_w '=END_ELEMENT_SHORT begin:'
    //   1180: invokevirtual print : (Ljava/lang/String;)V
    //   1183: iload #4
    //   1185: aload_0
    //   1186: getfield data : [C
    //   1189: iload #4
    //   1191: iconst_1
    //   1192: iadd
    //   1193: caload
    //   1194: isub
    //   1195: istore_2
    //   1196: aload_1
    //   1197: iload_2
    //   1198: invokevirtual print : (I)V
    //   1201: aload_0
    //   1202: getfield data : [C
    //   1205: iload_2
    //   1206: caload
    //   1207: ldc 40960
    //   1209: isub
    //   1210: istore_2
    //   1211: aload_1
    //   1212: ldc_w ' -> #'
    //   1215: invokevirtual print : (Ljava/lang/String;)V
    //   1218: aload_1
    //   1219: iload_2
    //   1220: invokevirtual print : (I)V
    //   1223: aload_1
    //   1224: ldc_w '=<'
    //   1227: invokevirtual print : (Ljava/lang/String;)V
    //   1230: aload_1
    //   1231: aload_0
    //   1232: getfield objects : [Ljava/lang/Object;
    //   1235: iload_2
    //   1236: aaload
    //   1237: invokevirtual print : (Ljava/lang/Object;)V
    //   1240: aload_1
    //   1241: bipush #62
    //   1243: invokevirtual print : (C)V
    //   1246: iconst_1
    //   1247: istore_2
    //   1248: goto -> 168
    //   1251: aload_0
    //   1252: iload #4
    //   1254: iconst_1
    //   1255: iadd
    //   1256: invokevirtual getIntN : (I)I
    //   1259: istore #5
    //   1261: iload #5
    //   1263: ifge -> 1363
    //   1266: aload_0
    //   1267: getfield data : [C
    //   1270: arraylength
    //   1271: istore_2
    //   1272: iload #5
    //   1274: iload_2
    //   1275: iadd
    //   1276: istore_2
    //   1277: aload_1
    //   1278: ldc_w '=BEGIN_ELEMENT_LONG end:'
    //   1281: invokevirtual print : (Ljava/lang/String;)V
    //   1284: aload_1
    //   1285: iload_2
    //   1286: invokevirtual print : (I)V
    //   1289: aload_0
    //   1290: iload_2
    //   1291: iconst_1
    //   1292: iadd
    //   1293: invokevirtual getIntN : (I)I
    //   1296: istore_2
    //   1297: aload_1
    //   1298: ldc_w ' -> #'
    //   1301: invokevirtual print : (Ljava/lang/String;)V
    //   1304: aload_1
    //   1305: iload_2
    //   1306: invokevirtual print : (I)V
    //   1309: iload_2
    //   1310: iflt -> 1369
    //   1313: iload_2
    //   1314: iconst_1
    //   1315: iadd
    //   1316: aload_0
    //   1317: getfield objects : [Ljava/lang/Object;
    //   1320: arraylength
    //   1321: if_icmpge -> 1369
    //   1324: aload_1
    //   1325: new java/lang/StringBuilder
    //   1328: dup
    //   1329: invokespecial <init> : ()V
    //   1332: ldc_w '=<'
    //   1335: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1338: aload_0
    //   1339: getfield objects : [Ljava/lang/Object;
    //   1342: iload_2
    //   1343: aaload
    //   1344: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1347: bipush #62
    //   1349: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   1352: invokevirtual toString : ()Ljava/lang/String;
    //   1355: invokevirtual print : (Ljava/lang/String;)V
    //   1358: iconst_2
    //   1359: istore_2
    //   1360: goto -> 168
    //   1363: iload #4
    //   1365: istore_2
    //   1366: goto -> 1272
    //   1369: aload_1
    //   1370: ldc_w '=<out-of-bounds>'
    //   1373: invokevirtual print : (Ljava/lang/String;)V
    //   1376: goto -> 1358
    //   1379: aload_0
    //   1380: iload #4
    //   1382: iconst_1
    //   1383: iadd
    //   1384: invokevirtual getIntN : (I)I
    //   1387: istore_2
    //   1388: aload_1
    //   1389: new java/lang/StringBuilder
    //   1392: dup
    //   1393: invokespecial <init> : ()V
    //   1396: ldc_w '=END_ELEMENT_LONG name:'
    //   1399: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1402: iload_2
    //   1403: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1406: ldc_w '=<'
    //   1409: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1412: aload_0
    //   1413: getfield objects : [Ljava/lang/Object;
    //   1416: iload_2
    //   1417: aaload
    //   1418: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1421: bipush #62
    //   1423: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   1426: invokevirtual toString : ()Ljava/lang/String;
    //   1429: invokevirtual print : (Ljava/lang/String;)V
    //   1432: aload_0
    //   1433: iload #4
    //   1435: iconst_3
    //   1436: iadd
    //   1437: invokevirtual getIntN : (I)I
    //   1440: istore #5
    //   1442: iload #5
    //   1444: istore_2
    //   1445: iload #5
    //   1447: ifge -> 1456
    //   1450: iload #5
    //   1452: iload #4
    //   1454: iadd
    //   1455: istore_2
    //   1456: aload_1
    //   1457: new java/lang/StringBuilder
    //   1460: dup
    //   1461: invokespecial <init> : ()V
    //   1464: ldc_w ' begin:'
    //   1467: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1470: iload_2
    //   1471: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1474: invokevirtual toString : ()Ljava/lang/String;
    //   1477: invokevirtual print : (Ljava/lang/String;)V
    //   1480: aload_0
    //   1481: iload #4
    //   1483: iconst_5
    //   1484: iadd
    //   1485: invokevirtual getIntN : (I)I
    //   1488: istore #5
    //   1490: iload #5
    //   1492: istore_2
    //   1493: iload #5
    //   1495: ifge -> 1504
    //   1498: iload #5
    //   1500: iload #4
    //   1502: iadd
    //   1503: istore_2
    //   1504: aload_1
    //   1505: new java/lang/StringBuilder
    //   1508: dup
    //   1509: invokespecial <init> : ()V
    //   1512: ldc_w ' parent:'
    //   1515: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1518: iload_2
    //   1519: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1522: invokevirtual toString : ()Ljava/lang/String;
    //   1525: invokevirtual print : (Ljava/lang/String;)V
    //   1528: bipush #6
    //   1530: istore_2
    //   1531: goto -> 168
    //   1534: aload_0
    //   1535: iload #4
    //   1537: iconst_1
    //   1538: iadd
    //   1539: invokevirtual getIntN : (I)I
    //   1542: istore_2
    //   1543: aload_1
    //   1544: new java/lang/StringBuilder
    //   1547: dup
    //   1548: invokespecial <init> : ()V
    //   1551: ldc_w '=BEGIN_ATTRIBUTE name:'
    //   1554: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1557: iload_2
    //   1558: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1561: ldc_w '='
    //   1564: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1567: aload_0
    //   1568: getfield objects : [Ljava/lang/Object;
    //   1571: iload_2
    //   1572: aaload
    //   1573: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1576: invokevirtual toString : ()Ljava/lang/String;
    //   1579: invokevirtual print : (Ljava/lang/String;)V
    //   1582: aload_0
    //   1583: iload #4
    //   1585: iconst_3
    //   1586: iadd
    //   1587: invokevirtual getIntN : (I)I
    //   1590: istore #5
    //   1592: iload #5
    //   1594: ifge -> 1635
    //   1597: aload_0
    //   1598: getfield data : [C
    //   1601: arraylength
    //   1602: istore_2
    //   1603: aload_1
    //   1604: new java/lang/StringBuilder
    //   1607: dup
    //   1608: invokespecial <init> : ()V
    //   1611: ldc_w ' end:'
    //   1614: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1617: iload #5
    //   1619: iload_2
    //   1620: iadd
    //   1621: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1624: invokevirtual toString : ()Ljava/lang/String;
    //   1627: invokevirtual print : (Ljava/lang/String;)V
    //   1630: iconst_4
    //   1631: istore_2
    //   1632: goto -> 168
    //   1635: iload #4
    //   1637: istore_2
    //   1638: goto -> 1603
    //   1641: aload_1
    //   1642: ldc_w '=END_ATTRIBUTE'
    //   1645: invokevirtual print : (Ljava/lang/String;)V
    //   1648: iload #5
    //   1650: istore_2
    //   1651: goto -> 168
    //   1654: aload_1
    //   1655: ldc_w '=POSITION_PAIR_FOLLOWS seq:'
    //   1658: invokevirtual print : (Ljava/lang/String;)V
    //   1661: aload_0
    //   1662: iload #4
    //   1664: iconst_1
    //   1665: iadd
    //   1666: invokevirtual getIntN : (I)I
    //   1669: istore_2
    //   1670: aload_1
    //   1671: iload_2
    //   1672: invokevirtual print : (I)V
    //   1675: aload_1
    //   1676: bipush #61
    //   1678: invokevirtual print : (C)V
    //   1681: aload_0
    //   1682: getfield objects : [Ljava/lang/Object;
    //   1685: iload_2
    //   1686: aaload
    //   1687: astore #10
    //   1689: aload #10
    //   1691: ifnonnull -> 1744
    //   1694: aconst_null
    //   1695: astore #9
    //   1697: aload_1
    //   1698: aload #9
    //   1700: invokevirtual print : (Ljava/lang/String;)V
    //   1703: aload_1
    //   1704: bipush #64
    //   1706: invokevirtual print : (C)V
    //   1709: aload #10
    //   1711: ifnonnull -> 1757
    //   1714: aload_1
    //   1715: ldc 'null'
    //   1717: invokevirtual print : (Ljava/lang/String;)V
    //   1720: aload_1
    //   1721: ldc_w ' ipos:'
    //   1724: invokevirtual print : (Ljava/lang/String;)V
    //   1727: aload_1
    //   1728: aload_0
    //   1729: iload #4
    //   1731: iconst_3
    //   1732: iadd
    //   1733: invokevirtual getIntN : (I)I
    //   1736: invokevirtual print : (I)V
    //   1739: iconst_4
    //   1740: istore_2
    //   1741: goto -> 168
    //   1744: aload #10
    //   1746: invokevirtual getClass : ()Ljava/lang/Class;
    //   1749: invokevirtual getName : ()Ljava/lang/String;
    //   1752: astore #9
    //   1754: goto -> 1697
    //   1757: aload_1
    //   1758: aload #10
    //   1760: invokestatic identityHashCode : (Ljava/lang/Object;)I
    //   1763: invokestatic toHexString : (I)Ljava/lang/String;
    //   1766: invokevirtual print : (Ljava/lang/String;)V
    //   1769: goto -> 1720
    //   1772: return
  }
  
  public void endAttribute() {
    if (this.attrStart <= 0)
      return; 
    if (this.data[this.gapEnd] != '')
      throw new Error("unexpected endAttribute"); 
    this.gapEnd++;
    setIntN(this.attrStart + 2, this.gapStart - this.attrStart + 1);
    this.attrStart = 0;
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = '';
  }
  
  public void endDocument() {
    if (this.data[this.gapEnd] != '' || this.docStart <= 0 || this.data[this.currentParent] != '')
      throw new Error("unexpected endDocument"); 
    this.gapEnd++;
    setIntN(this.docStart, this.gapStart - this.docStart + 1);
    this.docStart = 0;
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = '';
    i = getIntN(this.currentParent + 3);
    if (i < -1)
      i += this.currentParent; 
    this.currentParent = i;
  }
  
  public void endElement() {
    // Byte code:
    //   0: aload_0
    //   1: getfield data : [C
    //   4: aload_0
    //   5: getfield gapEnd : I
    //   8: caload
    //   9: ldc 61708
    //   11: if_icmpeq -> 25
    //   14: new java/lang/Error
    //   17: dup
    //   18: ldc_w 'unexpected endElement'
    //   21: invokespecial <init> : (Ljava/lang/String;)V
    //   24: athrow
    //   25: aload_0
    //   26: aload_0
    //   27: getfield gapEnd : I
    //   30: iconst_1
    //   31: iadd
    //   32: invokevirtual getIntN : (I)I
    //   35: istore_1
    //   36: aload_0
    //   37: aload_0
    //   38: getfield gapEnd : I
    //   41: iconst_3
    //   42: iadd
    //   43: invokevirtual getIntN : (I)I
    //   46: istore_3
    //   47: aload_0
    //   48: aload_0
    //   49: getfield gapEnd : I
    //   52: iconst_5
    //   53: iadd
    //   54: invokevirtual getIntN : (I)I
    //   57: istore_2
    //   58: aload_0
    //   59: iload_2
    //   60: putfield currentParent : I
    //   63: aload_0
    //   64: aload_0
    //   65: getfield gapEnd : I
    //   68: bipush #7
    //   70: iadd
    //   71: putfield gapEnd : I
    //   74: aload_0
    //   75: getfield gapStart : I
    //   78: iload_3
    //   79: isub
    //   80: istore #4
    //   82: iload_3
    //   83: iload_2
    //   84: isub
    //   85: istore #5
    //   87: iload_1
    //   88: sipush #4095
    //   91: if_icmpge -> 179
    //   94: iload #4
    //   96: ldc_w 65536
    //   99: if_icmpge -> 179
    //   102: iload #5
    //   104: ldc_w 65536
    //   107: if_icmpge -> 179
    //   110: aload_0
    //   111: getfield data : [C
    //   114: iload_3
    //   115: ldc 40960
    //   117: iload_1
    //   118: ior
    //   119: i2c
    //   120: castore
    //   121: aload_0
    //   122: getfield data : [C
    //   125: iload_3
    //   126: iconst_1
    //   127: iadd
    //   128: iload #4
    //   130: i2c
    //   131: castore
    //   132: aload_0
    //   133: getfield data : [C
    //   136: iload_3
    //   137: iconst_2
    //   138: iadd
    //   139: iload #5
    //   141: i2c
    //   142: castore
    //   143: aload_0
    //   144: getfield data : [C
    //   147: aload_0
    //   148: getfield gapStart : I
    //   151: ldc 61707
    //   153: castore
    //   154: aload_0
    //   155: getfield data : [C
    //   158: aload_0
    //   159: getfield gapStart : I
    //   162: iconst_1
    //   163: iadd
    //   164: iload #4
    //   166: i2c
    //   167: castore
    //   168: aload_0
    //   169: aload_0
    //   170: getfield gapStart : I
    //   173: iconst_2
    //   174: iadd
    //   175: putfield gapStart : I
    //   178: return
    //   179: aload_0
    //   180: getfield data : [C
    //   183: iload_3
    //   184: ldc 61704
    //   186: castore
    //   187: aload_0
    //   188: iload_3
    //   189: iconst_1
    //   190: iadd
    //   191: iload #4
    //   193: invokevirtual setIntN : (II)V
    //   196: aload_0
    //   197: getfield data : [C
    //   200: aload_0
    //   201: getfield gapStart : I
    //   204: ldc 61708
    //   206: castore
    //   207: aload_0
    //   208: aload_0
    //   209: getfield gapStart : I
    //   212: iconst_1
    //   213: iadd
    //   214: iload_1
    //   215: invokevirtual setIntN : (II)V
    //   218: aload_0
    //   219: aload_0
    //   220: getfield gapStart : I
    //   223: iconst_3
    //   224: iadd
    //   225: iload #4
    //   227: ineg
    //   228: invokevirtual setIntN : (II)V
    //   231: iload_2
    //   232: aload_0
    //   233: getfield gapStart : I
    //   236: if_icmpge -> 249
    //   239: iload_2
    //   240: istore_1
    //   241: iload_3
    //   242: aload_0
    //   243: getfield gapStart : I
    //   246: if_icmpgt -> 256
    //   249: iload_2
    //   250: aload_0
    //   251: getfield gapStart : I
    //   254: isub
    //   255: istore_1
    //   256: aload_0
    //   257: aload_0
    //   258: getfield gapStart : I
    //   261: iconst_5
    //   262: iadd
    //   263: iload_1
    //   264: invokevirtual setIntN : (II)V
    //   267: aload_0
    //   268: aload_0
    //   269: getfield gapStart : I
    //   272: bipush #7
    //   274: iadd
    //   275: putfield gapStart : I
    //   278: return
  }
  
  public void endEntity() {
    if (this.gapEnd + 1 != this.data.length || this.data[this.gapEnd] != '')
      return; 
    if (this.data[this.currentParent] != '')
      throw new Error("unexpected endEntity"); 
    this.gapEnd++;
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = '';
    i = getIntN(this.currentParent + 3);
    if (i < -1)
      i += this.currentParent; 
    this.currentParent = i;
  }
  
  public void ensureSpace(int paramInt) {
    int i = this.gapEnd - this.gapStart;
    if (paramInt > i) {
      int k = this.data.length;
      i = k - i + paramInt;
      int j = k * 2;
      paramInt = j;
      if (j < i)
        paramInt = i; 
      char[] arrayOfChar = new char[paramInt];
      if (this.gapStart > 0)
        System.arraycopy(this.data, 0, arrayOfChar, 0, this.gapStart); 
      i = k - this.gapEnd;
      if (i > 0)
        System.arraycopy(this.data, this.gapEnd, arrayOfChar, paramInt - i, i); 
      this.gapEnd = paramInt - i;
      this.data = arrayOfChar;
    } 
  }
  
  public int find(Object paramObject) {
    if (this.oindex == this.objects.length)
      resizeObjects(); 
    this.objects[this.oindex] = paramObject;
    int i = this.oindex;
    this.oindex = i + 1;
    return i;
  }
  
  public int firstAttributePos(int paramInt) {
    paramInt = gotoAttributesStart(posToDataIndex(paramInt));
    return (paramInt < 0) ? 0 : (paramInt << 1);
  }
  
  public int firstChildPos(int paramInt) {
    paramInt = gotoChildrenStart(posToDataIndex(paramInt));
    return (paramInt < 0) ? 0 : (paramInt << 1);
  }
  
  public Object get(int paramInt) {
    int j = 0;
    int i = paramInt;
    paramInt = j;
    while (true) {
      if (--i >= 0) {
        j = nextPos(paramInt);
        paramInt = j;
        if (j == 0)
          throw new IndexOutOfBoundsException(); 
        continue;
      } 
      return getPosNext(paramInt);
    } 
  }
  
  public int getAttributeCount(int paramInt) {
    int i = 0;
    for (paramInt = firstAttributePos(paramInt); paramInt != 0 && getNextKind(paramInt) == 35; paramInt = nextPos(paramInt))
      i++; 
    return i;
  }
  
  protected int getIndexDifference(int paramInt1, int paramInt2) {
    int j = posToDataIndex(paramInt2);
    int k = posToDataIndex(paramInt1);
    paramInt2 = 0;
    paramInt1 = j;
    int i = k;
    if (j > k) {
      paramInt2 = 1;
      i = j;
      paramInt1 = k;
    } 
    k = 0;
    j = paramInt1;
    for (paramInt1 = k; j < i; paramInt1++)
      j = nextDataIndex(j); 
    i = paramInt1;
    if (paramInt2 != 0)
      i = -paramInt1; 
    return i;
  }
  
  protected final int getIntN(int paramInt) {
    return this.data[paramInt] << 16 | this.data[paramInt + 1] & Character.MAX_VALUE;
  }
  
  protected final long getLongN(int paramInt) {
    char[] arrayOfChar = this.data;
    return (arrayOfChar[paramInt] & 0xFFFFL) << 48L | (arrayOfChar[paramInt + 1] & 0xFFFFL) << 32L | (arrayOfChar[paramInt + 2] & 0xFFFFL) << 16L | arrayOfChar[paramInt + 3] & 0xFFFFL;
  }
  
  public int getNextKind(int paramInt) {
    return getNextKindI(posToDataIndex(paramInt));
  }
  
  public int getNextKindI(int paramInt) {
    if (paramInt == this.data.length);
    char c = this.data[paramInt];
    if (c <= '鿿')
      return 29; 
    if (c >= '' && c <= '')
      return 32; 
    if (c >= 'ꀀ' && c <= '꿿')
      return 33; 
    if ((0xFF00 & c) == 61440)
      return 28; 
    if (c >= '뀀' && c <= '?')
      return 22; 
    switch (c) {
      case '':
      case '':
      case '':
      case '':
      case '':
        return 0;
      default:
        return 32;
      case '':
      case '':
        return 27;
      case '':
        return 22;
      case '':
        return 24;
      case '':
        return 25;
      case '':
        return 26;
      case '':
      case '':
        return 34;
      case '':
        return getNextKind(paramInt + 5 << 1);
      case '':
        return 33;
      case '':
        return 35;
      case '':
        return 31;
      case '':
        return 36;
      case '':
        break;
    } 
    return 37;
  }
  
  public String getNextTypeName(int paramInt) {
    Object object = getNextTypeObject(paramInt);
    return (object == null) ? null : object.toString();
  }
  
  public Object getNextTypeObject(int paramInt) {
    int i;
    paramInt = posToDataIndex(paramInt);
    while (true) {
      if (paramInt == this.data.length)
        return null; 
      i = this.data[paramInt];
      if (i != 61714) {
        if (i >= 40960 && i <= 45055) {
          paramInt = i - 40960;
        } else {
          break;
        } 
      } else {
        paramInt += 5;
        continue;
      } 
      return (paramInt >= 0) ? this.objects[paramInt] : null;
    } 
    if (i == 61704) {
      i = getIntN(paramInt + 1);
      if (i < 0)
        paramInt = this.data.length; 
      paramInt = getIntN(i + paramInt + 1);
    } else if (i == 61705) {
      paramInt = getIntN(paramInt + 1);
    } else if (i == 61716) {
      paramInt = getIntN(paramInt + 1);
    } else {
      return null;
    } 
    return (paramInt >= 0) ? this.objects[paramInt] : null;
  }
  
  public Object getPosNext(int paramInt) {
    int i;
    int j;
    boolean bool;
    paramInt = posToDataIndex(paramInt);
    if (paramInt == this.data.length)
      return Sequence.eofValue; 
    char c = this.data[paramInt];
    if (c <= '鿿')
      return Convert.toObject(c); 
    if (c >= '' && c <= '')
      return this.objects[c - 57344]; 
    if (c >= 'ꀀ' && c <= '꿿')
      return copyToList(paramInt, this.data[paramInt + 1] + paramInt + 2); 
    if (c >= '뀀' && c <= '?')
      return Convert.toObject(c - 49152); 
    switch (c) {
      default:
        throw unsupported("getPosNext, code=" + Integer.toHexString(c));
      case '':
        j = getIntN(paramInt + 1);
        if (j < 0) {
          int k = this.data.length;
          return copyToList(paramInt, j + k + 1);
        } 
        i = paramInt;
        return copyToList(paramInt, j + i + 1);
      case '':
      case '':
        if (c != '') {
          boolean bool1 = true;
          return Convert.toObject(bool1);
        } 
        bool = false;
        return Convert.toObject(bool);
      case '':
        return Convert.toObject(getIntN(paramInt + 1));
      case '':
        return Convert.toObject(getLongN(paramInt + 1));
      case '':
        return Convert.toObject(Float.intBitsToFloat(getIntN(paramInt + 1)));
      case '':
        return Convert.toObject(Double.longBitsToDouble(getLongN(paramInt + 1)));
      case '':
        return Convert.toObject(this.data[paramInt + 1]);
      case '':
        j = getIntN(paramInt + 3);
        if (j < 0) {
          i = this.data.length;
          return copyToList(paramInt, j + i + 1);
        } 
        i = paramInt;
        return copyToList(paramInt, j + i + 1);
      case '':
        j = getIntN(paramInt + 1);
        if (j < 0) {
          i = this.data.length;
          return copyToList(paramInt, j + i + 7);
        } 
        i = paramInt;
        return copyToList(paramInt, j + i + 7);
      case '':
      case '':
      case '':
      case '':
        return Sequence.eofValue;
      case '':
      case '':
        return this.objects[getIntN(paramInt + 1)];
      case '':
        return "";
      case '':
        break;
    } 
    return ((AbstractSequence)this.objects[getIntN(paramInt + 1)]).getIteratorAtPos(getIntN(paramInt + 3));
  }
  
  public int getPosNextInt(int paramInt) {
    int i = posToDataIndex(paramInt);
    if (i < this.data.length) {
      char c = this.data[i];
      if (c >= '뀀' && c <= '?')
        return c - 49152; 
      if (c == '')
        return getIntN(i + 1); 
    } 
    return ((Number)getPosNext(paramInt)).intValue();
  }
  
  public Object getPosPrevious(int paramInt) {
    return ((paramInt & 0x1) != 0 && paramInt != -1) ? getPosNext(paramInt - 3) : super.getPosPrevious(paramInt);
  }
  
  public int gotoAttributesStart(int paramInt) {
    int i = paramInt;
    if (paramInt >= this.gapStart)
      i = paramInt + this.gapEnd - this.gapStart; 
    if (i != this.data.length) {
      paramInt = this.data[i];
      if ((paramInt >= 40960 && paramInt <= 45055) || paramInt == 61704)
        return i + 3; 
    } 
    return -1;
  }
  
  public boolean gotoAttributesStart(TreePosition paramTreePosition) {
    int i = gotoAttributesStart(paramTreePosition.ipos >> 1);
    if (i < 0)
      return false; 
    paramTreePosition.push(this, i << 1);
    return true;
  }
  
  public final int gotoChildrenStart(int paramInt) {
    if (paramInt != this.data.length) {
      char c = this.data[paramInt];
      if ((c >= 'ꀀ' && c <= '꿿') || c == '') {
        paramInt += 3;
      } else if (c == '' || c == '') {
        paramInt += 5;
      } else {
        return -1;
      } 
      while (true) {
        int i = paramInt;
        if (paramInt >= this.gapStart)
          i = paramInt + this.gapEnd - this.gapStart; 
        paramInt = this.data[i];
        if (paramInt == 61705) {
          paramInt = getIntN(i + 3);
          if (paramInt < 0)
            i = this.data.length; 
          paramInt = i + paramInt;
          continue;
        } 
        if (paramInt == 61706 || paramInt == 61718) {
          paramInt = i + 1;
          continue;
        } 
        if (paramInt == 61720) {
          paramInt = i + 3;
          continue;
        } 
        return i;
      } 
    } 
    return -1;
  }
  
  public boolean hasNext(int paramInt) {
    paramInt = posToDataIndex(paramInt);
    if (paramInt != this.data.length) {
      paramInt = this.data[paramInt];
      if (paramInt != 61706 && paramInt != 61707 && paramInt != 61708 && paramInt != 61713)
        return true; 
    } 
    return false;
  }
  
  public int hashCode() {
    return System.identityHashCode(this);
  }
  
  public boolean ignoring() {
    return false;
  }
  
  public boolean isEmpty() {
    boolean bool1;
    boolean bool2 = false;
    if (this.gapStart == 0) {
      bool1 = this.gapEnd;
    } else {
      bool1 = false;
    } 
    if (bool1 == this.data.length)
      bool2 = true; 
    return bool2;
  }
  
  public final int nextDataIndex(int paramInt) {
    int i = paramInt;
    if (paramInt == this.gapStart)
      i = this.gapEnd; 
    if (i == this.data.length)
      return -1; 
    char[] arrayOfChar = this.data;
    paramInt = i + 1;
    i = arrayOfChar[i];
    if (i <= 40959 || (i >= 57344 && i <= 61439) || (i >= 45056 && i <= 57343))
      return paramInt; 
    if (i >= 40960 && i <= 45055)
      return this.data[paramInt] + paramInt + 1; 
    switch (i) {
      default:
        throw new Error("unknown code:" + Integer.toHexString(i));
      case 61712:
        i = getIntN(paramInt);
        if (i < 0) {
          paramInt = this.data.length;
          return i + paramInt + 1;
        } 
        return i + --paramInt + 1;
      case 61714:
        for (paramInt += 4;; paramInt = nextDataIndex(i)) {
          i = paramInt;
          if (paramInt == this.gapStart)
            i = this.gapEnd; 
          if (i == this.data.length)
            return -1; 
          if (this.data[i] == '')
            return i + 1; 
        } 
      case 61696:
      case 61697:
      case 61718:
        return paramInt;
      case 61702:
        return paramInt + 1;
      case 61698:
      case 61700:
      case 61709:
      case 61710:
        return paramInt + 2;
      case 61711:
        return paramInt + 4;
      case 61706:
      case 61707:
      case 61708:
      case 61713:
      case 61715:
        return -1;
      case 61704:
        i = getIntN(paramInt);
        if (i < 0) {
          paramInt = this.data.length;
          return i + paramInt + 7;
        } 
        return i + --paramInt + 7;
      case 61705:
        i = getIntN(paramInt + 2);
        if (i < 0) {
          paramInt = this.data.length;
          return i + paramInt + 1;
        } 
        return i + --paramInt + 1;
      case 61699:
      case 61701:
        return paramInt + 4;
      case 61716:
        paramInt += 2;
        break;
      case 61717:
      case 61719:
        break;
    } 
    return paramInt + 2 + getIntN(paramInt);
  }
  
  public int nextMatching(int paramInt1, ItemPredicate paramItemPredicate, int paramInt2, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: invokevirtual posToDataIndex : (I)I
    //   5: istore #8
    //   7: aload_0
    //   8: iload_3
    //   9: invokevirtual posToDataIndex : (I)I
    //   12: istore #9
    //   14: iload #8
    //   16: istore_3
    //   17: iload_3
    //   18: istore_1
    //   19: aload_2
    //   20: instanceof gnu/lists/NodePredicate
    //   23: ifeq -> 34
    //   26: aload_0
    //   27: iload_3
    //   28: iload #9
    //   30: invokevirtual nextNodeIndex : (II)I
    //   33: istore_1
    //   34: aload_2
    //   35: instanceof gnu/lists/ElementPredicate
    //   38: ifeq -> 79
    //   41: iconst_1
    //   42: istore #6
    //   44: iconst_0
    //   45: istore #7
    //   47: iload_1
    //   48: istore #5
    //   50: iload_1
    //   51: aload_0
    //   52: getfield gapStart : I
    //   55: if_icmpne -> 64
    //   58: aload_0
    //   59: getfield gapEnd : I
    //   62: istore #5
    //   64: iload #5
    //   66: iload #9
    //   68: if_icmplt -> 104
    //   71: iload #9
    //   73: iconst_m1
    //   74: if_icmpeq -> 104
    //   77: iconst_0
    //   78: ireturn
    //   79: aload_2
    //   80: instanceof gnu/lists/AttributePredicate
    //   83: ifeq -> 95
    //   86: iconst_0
    //   87: istore #6
    //   89: iconst_0
    //   90: istore #7
    //   92: goto -> 47
    //   95: iconst_1
    //   96: istore #6
    //   98: iconst_1
    //   99: istore #7
    //   101: goto -> 47
    //   104: aload_0
    //   105: getfield data : [C
    //   108: iload #5
    //   110: caload
    //   111: istore_1
    //   112: iload_1
    //   113: ldc 40959
    //   115: if_icmple -> 142
    //   118: iload_1
    //   119: ldc 57344
    //   121: if_icmplt -> 130
    //   124: iload_1
    //   125: ldc 61439
    //   127: if_icmple -> 142
    //   130: iload_1
    //   131: ldc 45056
    //   133: if_icmplt -> 198
    //   136: iload_1
    //   137: ldc 57343
    //   139: if_icmpgt -> 198
    //   142: iload #7
    //   144: ifeq -> 190
    //   147: aload_2
    //   148: aload_0
    //   149: iload #5
    //   151: iconst_1
    //   152: ishl
    //   153: invokeinterface isInstancePos : (Lgnu/lists/AbstractSequence;I)Z
    //   158: ifeq -> 190
    //   161: iload #5
    //   163: istore_1
    //   164: iload #5
    //   166: aload_0
    //   167: getfield gapEnd : I
    //   170: if_icmplt -> 186
    //   173: iload #5
    //   175: aload_0
    //   176: getfield gapEnd : I
    //   179: aload_0
    //   180: getfield gapStart : I
    //   183: isub
    //   184: isub
    //   185: istore_1
    //   186: iload_1
    //   187: iconst_1
    //   188: ishl
    //   189: ireturn
    //   190: iload #5
    //   192: iconst_1
    //   193: iadd
    //   194: istore_1
    //   195: goto -> 47
    //   198: iload_1
    //   199: tableswitch default -> 312, 61696 -> 561, 61697 -> 561, 61698 -> 425, 61699 -> 584, 61700 -> 425, 61701 -> 584, 61702 -> 440, 61703 -> 312, 61704 -> 669, 61705 -> 509, 61706 -> 494, 61707 -> 448, 61708 -> 478, 61709 -> 425, 61710 -> 425, 61711 -> 463, 61712 -> 403, 61713 -> 494, 61714 -> 417, 61715 -> 501, 61716 -> 599, 61717 -> 645, 61718 -> 576, 61719 -> 622, 61720 -> 395
    //   312: iload_1
    //   313: ldc 40960
    //   315: if_icmplt -> 742
    //   318: iload_1
    //   319: ldc 45055
    //   321: if_icmpgt -> 742
    //   324: iload #4
    //   326: ifeq -> 724
    //   329: iload #5
    //   331: iconst_3
    //   332: iadd
    //   333: istore_3
    //   334: iload_3
    //   335: istore_1
    //   336: iload #6
    //   338: ifeq -> 195
    //   341: iload_3
    //   342: istore_1
    //   343: iload #5
    //   345: iload #8
    //   347: if_icmple -> 195
    //   350: iload_3
    //   351: istore_1
    //   352: aload_2
    //   353: aload_0
    //   354: iload #5
    //   356: iconst_1
    //   357: ishl
    //   358: invokeinterface isInstancePos : (Lgnu/lists/AbstractSequence;I)Z
    //   363: ifeq -> 195
    //   366: iload #5
    //   368: istore_1
    //   369: iload #5
    //   371: aload_0
    //   372: getfield gapEnd : I
    //   375: if_icmplt -> 391
    //   378: iload #5
    //   380: aload_0
    //   381: getfield gapEnd : I
    //   384: aload_0
    //   385: getfield gapStart : I
    //   388: isub
    //   389: isub
    //   390: istore_1
    //   391: iload_1
    //   392: iconst_1
    //   393: ishl
    //   394: ireturn
    //   395: iload #5
    //   397: iconst_3
    //   398: iadd
    //   399: istore_1
    //   400: goto -> 195
    //   403: iload #5
    //   405: iconst_5
    //   406: iadd
    //   407: istore_3
    //   408: iload_3
    //   409: istore_1
    //   410: iconst_1
    //   411: ifeq -> 195
    //   414: goto -> 341
    //   417: iload #5
    //   419: iconst_5
    //   420: iadd
    //   421: istore_1
    //   422: goto -> 195
    //   425: iload #5
    //   427: iconst_3
    //   428: iadd
    //   429: istore_3
    //   430: iload_3
    //   431: istore_1
    //   432: iload #7
    //   434: ifeq -> 195
    //   437: goto -> 341
    //   440: iload #5
    //   442: iconst_2
    //   443: iadd
    //   444: istore_1
    //   445: goto -> 195
    //   448: iload #4
    //   450: ifne -> 455
    //   453: iconst_0
    //   454: ireturn
    //   455: iload #5
    //   457: iconst_2
    //   458: iadd
    //   459: istore_1
    //   460: goto -> 195
    //   463: iload #5
    //   465: iconst_5
    //   466: iadd
    //   467: istore_3
    //   468: iload_3
    //   469: istore_1
    //   470: iload #7
    //   472: ifeq -> 195
    //   475: goto -> 341
    //   478: iload #4
    //   480: ifne -> 485
    //   483: iconst_0
    //   484: ireturn
    //   485: iload #5
    //   487: bipush #7
    //   489: iadd
    //   490: istore_1
    //   491: goto -> 195
    //   494: iload #4
    //   496: ifne -> 501
    //   499: iconst_0
    //   500: ireturn
    //   501: iload #5
    //   503: iconst_1
    //   504: iadd
    //   505: istore_1
    //   506: goto -> 195
    //   509: iconst_1
    //   510: ifeq -> 553
    //   513: aload_0
    //   514: iload #5
    //   516: iconst_3
    //   517: iadd
    //   518: invokevirtual getIntN : (I)I
    //   521: istore_3
    //   522: iload_3
    //   523: ifge -> 547
    //   526: aload_0
    //   527: getfield data : [C
    //   530: arraylength
    //   531: istore_1
    //   532: iload_3
    //   533: iconst_1
    //   534: iadd
    //   535: iload_1
    //   536: iadd
    //   537: istore_3
    //   538: iload_3
    //   539: istore_1
    //   540: iconst_0
    //   541: ifeq -> 195
    //   544: goto -> 341
    //   547: iload #5
    //   549: istore_1
    //   550: goto -> 532
    //   553: iload #5
    //   555: iconst_5
    //   556: iadd
    //   557: istore_3
    //   558: goto -> 538
    //   561: iload #5
    //   563: iconst_1
    //   564: iadd
    //   565: istore_3
    //   566: iload_3
    //   567: istore_1
    //   568: iload #7
    //   570: ifeq -> 195
    //   573: goto -> 341
    //   576: iload #5
    //   578: iconst_1
    //   579: iadd
    //   580: istore_1
    //   581: goto -> 195
    //   584: iload #5
    //   586: iconst_5
    //   587: iadd
    //   588: istore_3
    //   589: iload_3
    //   590: istore_1
    //   591: iload #7
    //   593: ifeq -> 195
    //   596: goto -> 341
    //   599: iload #5
    //   601: iconst_5
    //   602: iadd
    //   603: aload_0
    //   604: iload #5
    //   606: iconst_3
    //   607: iadd
    //   608: invokevirtual getIntN : (I)I
    //   611: iadd
    //   612: istore_3
    //   613: iload_3
    //   614: istore_1
    //   615: iconst_1
    //   616: ifeq -> 195
    //   619: goto -> 341
    //   622: iload #5
    //   624: iconst_3
    //   625: iadd
    //   626: aload_0
    //   627: iload #5
    //   629: iconst_1
    //   630: iadd
    //   631: invokevirtual getIntN : (I)I
    //   634: iadd
    //   635: istore_3
    //   636: iload_3
    //   637: istore_1
    //   638: iconst_1
    //   639: ifeq -> 195
    //   642: goto -> 341
    //   645: iload #5
    //   647: iconst_3
    //   648: iadd
    //   649: aload_0
    //   650: iload #5
    //   652: iconst_1
    //   653: iadd
    //   654: invokevirtual getIntN : (I)I
    //   657: iadd
    //   658: istore_3
    //   659: iload_3
    //   660: istore_1
    //   661: iload #7
    //   663: ifeq -> 195
    //   666: goto -> 341
    //   669: iload #4
    //   671: ifeq -> 689
    //   674: iload #5
    //   676: iconst_3
    //   677: iadd
    //   678: istore_3
    //   679: iload_3
    //   680: istore_1
    //   681: iload #6
    //   683: ifeq -> 195
    //   686: goto -> 341
    //   689: aload_0
    //   690: iload #5
    //   692: iconst_1
    //   693: iadd
    //   694: invokevirtual getIntN : (I)I
    //   697: istore_3
    //   698: iload_3
    //   699: ifge -> 718
    //   702: aload_0
    //   703: getfield data : [C
    //   706: arraylength
    //   707: istore_1
    //   708: iload_1
    //   709: iload_3
    //   710: iadd
    //   711: bipush #7
    //   713: iadd
    //   714: istore_3
    //   715: goto -> 679
    //   718: iload #5
    //   720: istore_1
    //   721: goto -> 708
    //   724: aload_0
    //   725: getfield data : [C
    //   728: iload #5
    //   730: iconst_1
    //   731: iadd
    //   732: caload
    //   733: iload #5
    //   735: iadd
    //   736: iconst_2
    //   737: iadd
    //   738: istore_3
    //   739: goto -> 334
    //   742: new java/lang/Error
    //   745: dup
    //   746: new java/lang/StringBuilder
    //   749: dup
    //   750: invokespecial <init> : ()V
    //   753: ldc 'unknown code:'
    //   755: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   758: iload_1
    //   759: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   762: invokevirtual toString : ()Ljava/lang/String;
    //   765: invokespecial <init> : (Ljava/lang/String;)V
    //   768: athrow
  }
  
  public final int nextNodeIndex(int paramInt1, int paramInt2) {
    int i = paramInt1;
    int j = paramInt2;
    if ((Integer.MIN_VALUE | paramInt2) == -1) {
      j = this.data.length;
      i = paramInt1;
    } 
    while (true) {
      paramInt1 = i;
      if (i == this.gapStart)
        paramInt1 = this.gapEnd; 
      if (paramInt1 >= j);
      paramInt2 = this.data[paramInt1];
      if (paramInt2 <= 40959 || (paramInt2 >= 57344 && paramInt2 <= 61439) || (paramInt2 >= 45056 && paramInt2 <= 57343) || (0xFF00 & paramInt2) == 61440) {
        i = paramInt1 + 1;
        continue;
      } 
      if (paramInt2 < 40960 || paramInt2 > 45055) {
        switch (paramInt2) {
          case 61704:
          case 61705:
          case 61706:
          case 61707:
          case 61708:
          case 61712:
          case 61713:
          case 61715:
          case 61716:
          case 61719:
            return paramInt1;
          default:
            i = nextDataIndex(paramInt1);
            continue;
          case 61720:
            i = paramInt1 + 3;
            continue;
          case 61718:
            i = paramInt1 + 1;
            continue;
          case 61714:
            break;
        } 
        i = paramInt1 + 5;
      } 
    } 
  }
  
  public int nextPos(int paramInt) {
    int i = posToDataIndex(paramInt);
    if (i == this.data.length)
      return 0; 
    paramInt = i;
    if (i >= this.gapEnd)
      paramInt = i - this.gapEnd - this.gapStart; 
    return (paramInt << 1) + 3;
  }
  
  public int parentOrEntityI(int paramInt) {
    if (paramInt == this.data.length);
    char c = this.data[paramInt];
    if (c == '' || c == '') {
      int j = getIntN(paramInt + 3);
      return (j >= -1) ? j : (paramInt + j);
    } 
    if (c >= 'ꀀ' && c <= '꿿') {
      char c1 = this.data[paramInt + 2];
      if (c1 != '\000')
        return paramInt - c1; 
    } 
    int i = paramInt;
    if (c == '') {
      i = getIntN(paramInt + 1);
      if (i < 0)
        paramInt = this.data.length; 
      int j = i + paramInt;
      i = getIntN(j + 5);
      if (i != 0) {
        paramInt = i;
        if (i < 0)
          paramInt = i + j; 
        return paramInt;
      } 
    } 
    while (true) {
      paramInt = i;
      if (i == this.gapStart)
        paramInt = this.gapEnd; 
      if (paramInt != this.data.length) {
        switch (this.data[paramInt]) {
          case '':
            return -1;
          case '':
            i = paramInt + 1;
            continue;
          default:
            paramInt = nextDataIndex(paramInt);
            i = paramInt;
            if (paramInt < 0)
              return -1; 
            continue;
          case '':
            return paramInt - this.data[paramInt + 1];
          case '':
            break;
        } 
        int j = getIntN(paramInt + 3);
        i = j;
        if (j < 0)
          i = j + paramInt; 
        return i;
      } 
    } 
  }
  
  public int parentOrEntityPos(int paramInt) {
    paramInt = parentOrEntityI(posToDataIndex(paramInt));
    return (paramInt < 0) ? -1 : (paramInt << 1);
  }
  
  public int parentPos(int paramInt) {
    paramInt = posToDataIndex(paramInt);
    while (true) {
      int i = parentOrEntityI(paramInt);
      if (i == -1)
        return -1; 
      paramInt = i;
      if (this.data[i] != '')
        return i << 1; 
    } 
  }
  
  public final int posToDataIndex(int paramInt) {
    if (paramInt == -1)
      return this.data.length; 
    int j = paramInt >>> 1;
    int i = j;
    if ((paramInt & 0x1) != 0)
      i = j - 1; 
    j = i;
    if (i >= this.gapStart)
      j = i + this.gapEnd - this.gapStart; 
    i = j;
    if ((paramInt & 0x1) != 0) {
      paramInt = nextDataIndex(j);
      if (paramInt < 0)
        return this.data.length; 
      i = paramInt;
      if (paramInt == this.gapStart)
        return paramInt + this.gapEnd - this.gapStart; 
    } 
    return i;
  }
  
  public final void resizeObjects() {
    Object[] arrayOfObject;
    if (this.objects == null) {
      arrayOfObject = new Object[100];
    } else {
      int i = this.objects.length;
      arrayOfObject = new Object[i * 2];
      System.arraycopy(this.objects, 0, arrayOfObject, 0, i);
    } 
    this.objects = arrayOfObject;
  }
  
  public void setAttributeName(int paramInt1, int paramInt2) {
    setIntN(paramInt1 + 1, paramInt2);
  }
  
  public void setElementName(int paramInt1, int paramInt2) {
    int i = paramInt1;
    if (this.data[paramInt1] == '') {
      i = getIntN(paramInt1 + 1);
      if (i < 0)
        paramInt1 = this.data.length; 
      i = paramInt1 + i;
    } 
    if (i < this.gapEnd)
      throw new Error("setElementName before gapEnd"); 
    setIntN(i + 1, paramInt2);
  }
  
  public final void setIntN(int paramInt1, int paramInt2) {
    this.data[paramInt1] = (char)(paramInt2 >> 16);
    this.data[paramInt1 + 1] = (char)paramInt2;
  }
  
  public int size() {
    int i = 0;
    int j = 0;
    while (true) {
      j = nextPos(j);
      if (j == 0)
        return i; 
      i++;
    } 
  }
  
  public void startAttribute(int paramInt) {
    ensureSpace(6);
    this.gapEnd--;
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = '';
    if (this.attrStart != 0)
      throw new Error("nested attribute"); 
    this.attrStart = this.gapStart;
    setIntN(this.gapStart, paramInt);
    setIntN(this.gapStart + 2, this.gapEnd - this.data.length);
    this.gapStart += 4;
    this.data[this.gapEnd] = '';
  }
  
  public void startAttribute(Object paramObject) {
    startAttribute(find(paramObject));
  }
  
  public void startDocument() {
    int i = -1;
    ensureSpace(6);
    this.gapEnd--;
    int j = this.gapStart;
    this.data[j] = '';
    if (this.docStart != 0)
      throw new Error("nested document"); 
    this.docStart = j + 1;
    setIntN(j + 1, this.gapEnd - this.data.length);
    if (this.currentParent != -1)
      i = this.currentParent - j; 
    setIntN(j + 3, i);
    this.currentParent = j;
    this.gapStart = j + 5;
    this.currentParent = j;
    this.data[this.gapEnd] = '';
  }
  
  public void startElement(int paramInt) {
    ensureSpace(10);
    this.gapEnd -= 7;
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = '';
    setIntN(this.gapStart, this.gapEnd - this.data.length);
    this.gapStart += 2;
    this.data[this.gapEnd] = '';
    setIntN(this.gapEnd + 1, paramInt);
    setIntN(this.gapEnd + 3, this.gapStart - 3);
    setIntN(this.gapEnd + 5, this.currentParent);
    this.currentParent = this.gapStart - 3;
  }
  
  public void startElement(Object paramObject) {
    startElement(find(paramObject));
  }
  
  public void statistics() {
    PrintWriter printWriter = new PrintWriter(System.out);
    statistics(printWriter);
    printWriter.flush();
  }
  
  public void statistics(PrintWriter paramPrintWriter) {
    paramPrintWriter.print("data array length: ");
    paramPrintWriter.println(this.data.length);
    paramPrintWriter.print("data array gap: ");
    paramPrintWriter.println(this.gapEnd - this.gapStart);
    paramPrintWriter.print("object array length: ");
    paramPrintWriter.println(this.objects.length);
  }
  
  public int stringValue(int paramInt, StringBuffer paramStringBuffer) {
    int i = nextNodeIndex(paramInt, 2147483647);
    if (i > paramInt) {
      stringValue(paramInt, i, paramStringBuffer);
      return paramInt;
    } 
    return stringValue(false, paramInt, paramStringBuffer);
  }
  
  public int stringValue(boolean paramBoolean, int paramInt, StringBuffer paramStringBuffer) {
    Object object = null;
    int k = 0;
    int j = 0;
    int i = paramInt;
    if (paramInt >= this.gapStart)
      i = paramInt + this.gapEnd - this.gapStart; 
    if (i == this.data.length)
      return -1; 
    char c = this.data[i];
    i++;
    if (c <= '鿿') {
      paramStringBuffer.append(c);
      return i;
    } 
    if (c >= '' && c <= '') {
      if (false)
        paramStringBuffer.append(' '); 
      object = this.objects[c - 57344];
      paramInt = j;
    } else if (c >= 'ꀀ' && c <= '꿿') {
      paramInt = i + 2;
      i = this.data[i] + i + 1;
    } else {
      if ((0xFF00 & c) == 61440) {
        paramStringBuffer.append(c & 0xFF);
        return i;
      } 
      if (c >= '뀀' && c <= '?') {
        paramStringBuffer.append(c - 49152);
        return i;
      } 
      paramInt = i;
      switch (c) {
        default:
          throw new Error("unimplemented: " + Integer.toHexString(c) + " at:" + i);
        case '':
          return i + 2;
        case '':
          paramInt = i + 2;
        case '':
        case '':
          i = getIntN(paramInt);
          paramInt += 2;
          if (!paramBoolean || c == '')
            paramStringBuffer.append(this.data, paramInt, i); 
          return paramInt + i;
        case '':
        case '':
          if (false)
            paramStringBuffer.append(' '); 
          if (c != '') {
            paramBoolean = true;
            paramStringBuffer.append(paramBoolean);
            return i;
          } 
          paramBoolean = false;
          paramStringBuffer.append(paramBoolean);
          return i;
        case '':
          if (false)
            paramStringBuffer.append(' '); 
          paramStringBuffer.append(getIntN(i));
          return i + 2;
        case '':
          if (false)
            paramStringBuffer.append(' '); 
          paramStringBuffer.append(getLongN(i));
          return i + 4;
        case '':
          if (false)
            paramStringBuffer.append(' '); 
          paramStringBuffer.append(Float.intBitsToFloat(getIntN(i)));
          return i + 2;
        case '':
          if (false)
            paramStringBuffer.append(' '); 
          paramStringBuffer.append(Double.longBitsToDouble(getLongN(i)));
          return i + 4;
        case '':
          paramStringBuffer.append(this.data[i]);
          return i + 1;
        case '':
        case '':
          paramInt = i + 4;
          i = nextDataIndex(i - 1);
          if (object != null)
            paramStringBuffer.append(object); 
          if (paramInt > 0)
            do {
              j = stringValue(true, paramInt, paramStringBuffer);
              paramInt = j;
            } while (j >= 0); 
          return i;
        case '':
          j = i + 2;
          k = getIntN(i);
          if (k < 0) {
            paramInt = this.data.length;
          } else {
            paramInt = i - 1;
          } 
          i = k + paramInt + 7;
          paramInt = j;
          if (object != null)
            paramStringBuffer.append(object); 
          if (paramInt > 0)
            while (true) {
              j = stringValue(true, paramInt, paramStringBuffer);
              paramInt = j;
            }  
          return i;
        case '':
          paramInt = j;
          if (object != null)
            paramStringBuffer.append(object); 
          if (paramInt > 0)
            while (true) {
              j = stringValue(true, paramInt, paramStringBuffer);
              paramInt = j;
            }  
          return i;
        case '':
        case '':
        case '':
        case '':
        case '':
          return -1;
        case '':
          paramInt = k;
          if (!paramBoolean)
            paramInt = i + 4; 
          j = getIntN(i + 2);
          if (j < 0)
            i = this.data.length + 1; 
          i += j;
          if (object != null)
            paramStringBuffer.append(object); 
          if (paramInt > 0)
            while (true) {
              j = stringValue(true, paramInt, paramStringBuffer);
              paramInt = j;
            }  
          return i;
        case '':
          break;
      } 
      AbstractSequence abstractSequence = (AbstractSequence)this.objects[getIntN(i)];
      paramInt = getIntN(i + 2);
      ((TreeList)abstractSequence).stringValue(paramBoolean, paramInt >> 1, paramStringBuffer);
      i += 4;
      paramInt = j;
    } 
    if (object != null)
      paramStringBuffer.append(object); 
    if (paramInt > 0)
      while (true) {
        j = stringValue(true, paramInt, paramStringBuffer);
        paramInt = j;
      }  
    return i;
  }
  
  public void stringValue(int paramInt1, int paramInt2, StringBuffer paramStringBuffer) {
    while (paramInt1 < paramInt2 && paramInt1 >= 0)
      paramInt1 = stringValue(false, paramInt1, paramStringBuffer); 
  }
  
  public void toString(String paramString, StringBuffer paramStringBuffer) {
    int i = 0;
    int m = this.gapStart;
    int k = 0;
    int j = 0;
    label182: while (true) {
      boolean bool;
      int n = m;
      int i1 = i;
      if (i >= m)
        if (i == this.gapStart) {
          i = this.gapEnd;
          m = this.data.length;
          n = m;
          i1 = i;
          if (i == m)
            return; 
        } else {
          return;
        }  
      char[] arrayOfChar = this.data;
      i = i1 + 1;
      i1 = arrayOfChar[i1];
      if (i1 <= 40959) {
        m = i - 1;
        while (true) {
          if (i < n) {
            arrayOfChar = this.data;
            k = i + 1;
            if (arrayOfChar[i] > '鿿') {
              i = k - 1;
            } else {
              i = k;
              continue;
            } 
          } 
          k = j;
          if (j) {
            paramStringBuffer.append('>');
            k = 0;
          } 
          paramStringBuffer.append(this.data, m, i - m);
          i1 = 0;
          j = k;
          m = n;
          k = i1;
          continue label182;
        } 
        break;
      } 
      if (i1 >= 57344 && i1 <= 61439) {
        m = j;
        if (j != 0) {
          paramStringBuffer.append('>');
          m = 0;
        } 
        if (k != 0) {
          paramStringBuffer.append(paramString);
        } else {
          k = 1;
        } 
        paramStringBuffer.append(this.objects[i1 - 57344]);
        j = m;
        m = n;
        continue;
      } 
      if (i1 >= 40960 && i1 <= 45055) {
        if (j != 0)
          paramStringBuffer.append('>'); 
        if (k != 0)
          paramStringBuffer.append(paramString); 
        paramStringBuffer.append('<');
        paramStringBuffer.append(this.objects[i1 - 40960].toString());
        i += 2;
        k = 0;
        j = 1;
        m = n;
        continue;
      } 
      if (i1 >= 45056 && i1 <= 57343) {
        m = j;
        if (j != 0) {
          paramStringBuffer.append('>');
          m = 0;
        } 
        if (k != 0) {
          paramStringBuffer.append(paramString);
        } else {
          k = 1;
        } 
        paramStringBuffer.append(i1 - 49152);
        j = m;
        m = n;
        continue;
      } 
      switch (i1) {
        default:
          throw new Error("unknown code:" + i1);
        case 61712:
        case 61714:
          i += 4;
          m = n;
          continue;
        case 61720:
          i += 2;
          m = n;
          continue;
        case 61719:
          m = j;
          if (j != 0) {
            paramStringBuffer.append('>');
            m = 0;
          } 
          j = getIntN(i);
          i += 2;
          paramStringBuffer.append("<!--");
          paramStringBuffer.append(this.data, i, j);
          paramStringBuffer.append("-->");
          i += j;
          j = m;
          m = n;
          continue;
        case 61717:
          m = j;
          if (j != 0) {
            paramStringBuffer.append('>');
            m = 0;
          } 
          j = getIntN(i);
          i += 2;
          paramStringBuffer.append("<![CDATA[");
          paramStringBuffer.append(this.data, i, j);
          paramStringBuffer.append("]]>");
          i += j;
          j = m;
          m = n;
          continue;
        case 61716:
          m = j;
          if (j != 0) {
            paramStringBuffer.append('>');
            m = 0;
          } 
          paramStringBuffer.append("<?");
          j = getIntN(i);
          i += 2;
          paramStringBuffer.append(this.objects[j]);
          i1 = getIntN(i);
          j = i + 2;
          i = j;
          if (i1 > 0) {
            paramStringBuffer.append(' ');
            paramStringBuffer.append(this.data, j, i1);
            i = j + i1;
          } 
          paramStringBuffer.append("?>");
          j = m;
          m = n;
          continue;
        case 61713:
        case 61715:
          m = n;
          continue;
        case 61696:
        case 61697:
          m = j;
          if (j != 0) {
            paramStringBuffer.append('>');
            m = 0;
          } 
          if (k != 0) {
            paramStringBuffer.append(paramString);
          } else {
            k = 1;
          } 
          if (i1 != 61696) {
            bool = true;
          } else {
            bool = false;
          } 
          paramStringBuffer.append(bool);
          j = m;
          m = n;
          continue;
        case 61718:
          m = n;
          continue;
        case 61702:
          k = j;
          if (j != 0) {
            paramStringBuffer.append('>');
            k = 0;
          } 
          paramStringBuffer.append(this.data, i, i1 + 1 - 61702);
          i1 = 0;
          i++;
          j = k;
          m = n;
          k = i1;
          continue;
        case 61711:
          m = j;
          if (j != 0) {
            paramStringBuffer.append('>');
            m = 0;
          } 
          if (k != 0) {
            paramStringBuffer.append(paramString);
          } else {
            k = 1;
          } 
          paramStringBuffer.append(((AbstractSequence)this.objects[getIntN(i)]).getIteratorAtPos(getIntN(i + 2)));
          i += 4;
          j = m;
          m = n;
          continue;
        case 61709:
        case 61710:
          m = j;
          if (j != 0) {
            paramStringBuffer.append('>');
            m = 0;
          } 
          if (k != 0) {
            paramStringBuffer.append(paramString);
          } else {
            k = 1;
          } 
          paramStringBuffer.append(this.objects[getIntN(i)]);
          i += 2;
          j = m;
          m = n;
          continue;
        case 61704:
          i1 = getIntN(i);
          if (i1 >= 0) {
            m = i - 1;
          } else {
            m = this.data.length;
          } 
          i += 2;
          m = getIntN(i1 + m + 1);
          if (j != 0) {
            paramStringBuffer.append('>');
          } else if (k != 0) {
            paramStringBuffer.append(paramString);
          } 
          paramStringBuffer.append('<');
          paramStringBuffer.append(this.objects[m]);
          k = 0;
          j = 1;
          m = n;
          continue;
        case 61707:
        case 61708:
          if (i1 == 61707) {
            arrayOfChar = this.data;
            k = i + 1;
            i = arrayOfChar[i];
            m = this.data[k - 2 - i] - 40960;
            i = k;
            k = m;
          } else {
            k = getIntN(i);
            i += 6;
          } 
          if (j != 0) {
            paramStringBuffer.append("/>");
          } else {
            paramStringBuffer.append("</");
            paramStringBuffer.append(this.objects[k]);
            paramStringBuffer.append('>');
          } 
          j = 0;
          k = 1;
          m = n;
          continue;
        case 61705:
          j = getIntN(i);
          paramStringBuffer.append(' ');
          paramStringBuffer.append(this.objects[j]);
          paramStringBuffer.append("=\"");
          j = 0;
          i += 4;
          m = n;
          continue;
        case 61706:
          paramStringBuffer.append('"');
          j = 1;
          k = 0;
          m = n;
          continue;
        case 61698:
          m = j;
          if (j != 0) {
            paramStringBuffer.append('>');
            m = 0;
          } 
          if (k != 0) {
            paramStringBuffer.append(paramString);
          } else {
            k = 1;
          } 
          paramStringBuffer.append(getIntN(i));
          i += 2;
          j = m;
          m = n;
          continue;
        case 61700:
          m = j;
          if (j != 0) {
            paramStringBuffer.append('>');
            m = 0;
          } 
          if (k != 0) {
            paramStringBuffer.append(paramString);
          } else {
            k = 1;
          } 
          paramStringBuffer.append(Float.intBitsToFloat(getIntN(i)));
          i += 2;
          j = m;
          m = n;
          continue;
        case 61699:
          m = j;
          if (j != 0) {
            paramStringBuffer.append('>');
            m = 0;
          } 
          if (k != 0) {
            paramStringBuffer.append(paramString);
          } else {
            k = 1;
          } 
          paramStringBuffer.append(getLongN(i));
          i += 4;
          j = m;
          m = n;
          continue;
        case 61701:
          break;
      } 
      m = j;
      if (j != 0) {
        paramStringBuffer.append('>');
        m = 0;
      } 
      if (k != 0) {
        paramStringBuffer.append(paramString);
      } else {
        k = 1;
      } 
      paramStringBuffer.append(Double.longBitsToDouble(getLongN(i)));
      i += 4;
      j = m;
      m = n;
    } 
  }
  
  public void write(int paramInt) {
    ensureSpace(3);
    if (paramInt <= 40959) {
      char[] arrayOfChar = this.data;
      int i = this.gapStart;
      this.gapStart = i + 1;
      arrayOfChar[i] = (char)paramInt;
      return;
    } 
    if (paramInt < 65536) {
      char[] arrayOfChar = this.data;
      int i = this.gapStart;
      this.gapStart = i + 1;
      arrayOfChar[i] = '';
      arrayOfChar = this.data;
      i = this.gapStart;
      this.gapStart = i + 1;
      arrayOfChar[i] = (char)paramInt;
      return;
    } 
    Char.print(paramInt, this);
  }
  
  public void write(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    if (paramInt2 == 0)
      writeJoiner(); 
    ensureSpace(paramInt2);
    while (paramInt2 > 0) {
      char c = paramCharSequence.charAt(paramInt1);
      paramInt2--;
      if (c <= '鿿') {
        char[] arrayOfChar = this.data;
        int i = this.gapStart;
        this.gapStart = i + 1;
        arrayOfChar[i] = c;
      } else {
        write(c);
        ensureSpace(paramInt2);
      } 
      paramInt1++;
    } 
  }
  
  public void write(String paramString) {
    write(paramString, 0, paramString.length());
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (paramInt2 == 0)
      writeJoiner(); 
    ensureSpace(paramInt2);
    while (paramInt2 > 0) {
      char c = paramArrayOfchar[paramInt1];
      paramInt2--;
      if (c <= '鿿') {
        char[] arrayOfChar = this.data;
        int i = this.gapStart;
        this.gapStart = i + 1;
        arrayOfChar[i] = c;
      } else {
        write(c);
        ensureSpace(paramInt2);
      } 
      paramInt1++;
    } 
  }
  
  public void writeBoolean(boolean paramBoolean) {
    char c;
    ensureSpace(1);
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = i + 1;
    if (paramBoolean) {
      c = '';
    } else {
      c = '';
    } 
    arrayOfChar[i] = c;
  }
  
  public void writeByte(int paramInt) {
    ensureSpace(1);
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = (char)(61440 + (paramInt & 0xFF));
  }
  
  public void writeCDATA(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    ensureSpace(paramInt2 + 3);
    int i = this.gapStart;
    char[] arrayOfChar = this.data;
    int j = i + 1;
    arrayOfChar[i] = '';
    setIntN(j, paramInt2);
    i = j + 2;
    System.arraycopy(paramArrayOfchar, paramInt1, this.data, i, paramInt2);
    this.gapStart = i + paramInt2;
  }
  
  public void writeComment(String paramString, int paramInt1, int paramInt2) {
    ensureSpace(paramInt2 + 3);
    int i = this.gapStart;
    char[] arrayOfChar = this.data;
    int j = i + 1;
    arrayOfChar[i] = '';
    setIntN(j, paramInt2);
    i = j + 2;
    paramString.getChars(paramInt1, paramInt1 + paramInt2, this.data, i);
    this.gapStart = i + paramInt2;
  }
  
  public void writeComment(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    ensureSpace(paramInt2 + 3);
    int i = this.gapStart;
    char[] arrayOfChar = this.data;
    int j = i + 1;
    arrayOfChar[i] = '';
    setIntN(j, paramInt2);
    i = j + 2;
    System.arraycopy(paramArrayOfchar, paramInt1, this.data, i, paramInt2);
    this.gapStart = i + paramInt2;
  }
  
  public void writeDocumentUri(Object paramObject) {
    ensureSpace(3);
    int i = find(paramObject);
    paramObject = this.data;
    int j = this.gapStart;
    this.gapStart = j + 1;
    paramObject[j] = '';
    setIntN(this.gapStart, i);
    this.gapStart += 2;
  }
  
  public void writeDouble(double paramDouble) {
    ensureSpace(5);
    long l = Double.doubleToLongBits(paramDouble);
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = '';
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = (char)(int)(l >>> 48L);
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = (char)(int)(l >>> 32L);
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = (char)(int)(l >>> 16L);
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = (char)(int)l;
  }
  
  public void writeFloat(float paramFloat) {
    ensureSpace(3);
    int i = Float.floatToIntBits(paramFloat);
    char[] arrayOfChar = this.data;
    int j = this.gapStart;
    this.gapStart = j + 1;
    arrayOfChar[j] = '';
    arrayOfChar = this.data;
    j = this.gapStart;
    this.gapStart = j + 1;
    arrayOfChar[j] = (char)(i >>> 16);
    arrayOfChar = this.data;
    j = this.gapStart;
    this.gapStart = j + 1;
    arrayOfChar[j] = (char)i;
  }
  
  public void writeInt(int paramInt) {
    ensureSpace(3);
    if (paramInt >= -4096 && paramInt <= 8191) {
      char[] arrayOfChar1 = this.data;
      int j = this.gapStart;
      this.gapStart = j + 1;
      arrayOfChar1[j] = (char)(49152 + paramInt);
      return;
    } 
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = '';
    setIntN(this.gapStart, paramInt);
    this.gapStart += 2;
  }
  
  public void writeJoiner() {
    ensureSpace(1);
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = '';
  }
  
  public void writeLong(long paramLong) {
    ensureSpace(5);
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = '';
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = (char)(int)(paramLong >>> 48L);
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = (char)(int)(paramLong >>> 32L);
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = (char)(int)(paramLong >>> 16L);
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = i + 1;
    arrayOfChar[i] = (char)(int)paramLong;
  }
  
  public void writeObject(Object paramObject) {
    ensureSpace(3);
    int i = find(paramObject);
    if (i < 4096) {
      paramObject = this.data;
      int k = this.gapStart;
      this.gapStart = k + 1;
      paramObject[k] = (char)(0xE000 | i);
      return;
    } 
    paramObject = this.data;
    int j = this.gapStart;
    this.gapStart = j + 1;
    paramObject[j] = '';
    setIntN(this.gapStart, i);
    this.gapStart += 2;
  }
  
  public void writePosition(AbstractSequence paramAbstractSequence, int paramInt) {
    ensureSpace(5);
    this.data[this.gapStart] = '';
    int i = find(paramAbstractSequence);
    setIntN(this.gapStart + 1, i);
    setIntN(this.gapStart + 3, paramInt);
    this.gapStart += 5;
  }
  
  public void writeProcessingInstruction(String paramString1, String paramString2, int paramInt1, int paramInt2) {
    ensureSpace(paramInt2 + 5);
    int i = this.gapStart;
    char[] arrayOfChar = this.data;
    int j = i + 1;
    arrayOfChar[i] = '';
    setIntN(j, find(paramString1));
    setIntN(j + 2, paramInt2);
    i = j + 4;
    paramString2.getChars(paramInt1, paramInt1 + paramInt2, this.data, i);
    this.gapStart = i + paramInt2;
  }
  
  public void writeProcessingInstruction(String paramString, char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    ensureSpace(paramInt2 + 5);
    int i = this.gapStart;
    char[] arrayOfChar = this.data;
    int j = i + 1;
    arrayOfChar[i] = '';
    setIntN(j, find(paramString));
    setIntN(j + 2, paramInt2);
    i = j + 4;
    System.arraycopy(paramArrayOfchar, paramInt1, this.data, i, paramInt2);
    this.gapStart = i + paramInt2;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/TreeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
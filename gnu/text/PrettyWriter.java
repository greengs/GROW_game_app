package gnu.text;

import gnu.lists.LList;
import gnu.mapping.ThreadLocation;
import java.io.IOException;
import java.io.Writer;

public class PrettyWriter extends Writer {
  private static final int BLOCK_PER_LINE_PREFIX_END = -3;
  
  private static final int BLOCK_PREFIX_LENGTH = -4;
  
  private static final int BLOCK_SECTION_COLUMN = -2;
  
  private static final int BLOCK_SECTION_START_LINE = -6;
  
  private static final int BLOCK_START_COLUMN = -1;
  
  private static final int BLOCK_SUFFIX_LENGTH = -5;
  
  private static final int LOGICAL_BLOCK_LENGTH = 6;
  
  public static final int NEWLINE_FILL = 70;
  
  public static final int NEWLINE_LINEAR = 78;
  
  public static final int NEWLINE_LITERAL = 76;
  
  public static final int NEWLINE_MANDATORY = 82;
  
  public static final int NEWLINE_MISER = 77;
  
  public static final int NEWLINE_SPACE = 83;
  
  static final int QITEM_BASE_SIZE = 2;
  
  static final int QITEM_BLOCK_END_SIZE = 2;
  
  static final int QITEM_BLOCK_END_TYPE = 5;
  
  static final int QITEM_BLOCK_START_BLOCK_END = 4;
  
  static final int QITEM_BLOCK_START_PREFIX = 5;
  
  static final int QITEM_BLOCK_START_SIZE = 7;
  
  static final int QITEM_BLOCK_START_SUFFIX = 6;
  
  static final int QITEM_BLOCK_START_TYPE = 4;
  
  static final int QITEM_INDENTATION_AMOUNT = 3;
  
  static final char QITEM_INDENTATION_BLOCK = 'B';
  
  static final char QITEM_INDENTATION_CURRENT = 'C';
  
  static final int QITEM_INDENTATION_KIND = 2;
  
  static final int QITEM_INDENTATION_SIZE = 4;
  
  static final int QITEM_INDENTATION_TYPE = 3;
  
  static final int QITEM_NEWLINE_KIND = 4;
  
  static final int QITEM_NEWLINE_SIZE = 5;
  
  static final int QITEM_NEWLINE_TYPE = 2;
  
  static final int QITEM_NOP_TYPE = 0;
  
  static final int QITEM_POSN = 1;
  
  static final int QITEM_SECTION_START_DEPTH = 2;
  
  static final int QITEM_SECTION_START_SECTION_END = 3;
  
  static final int QITEM_SECTION_START_SIZE = 4;
  
  static final int QITEM_TAB_COLINC = 4;
  
  static final int QITEM_TAB_COLNUM = 3;
  
  static final int QITEM_TAB_FLAGS = 2;
  
  static final int QITEM_TAB_IS_RELATIVE = 2;
  
  static final int QITEM_TAB_IS_SECTION = 1;
  
  static final int QITEM_TAB_SIZE = 5;
  
  static final int QITEM_TAB_TYPE = 6;
  
  static final int QITEM_TYPE_AND_SIZE = 0;
  
  static final int QUEUE_INIT_ALLOC_SIZE = 300;
  
  public static ThreadLocation indentLoc;
  
  public static int initialBufferSize;
  
  public static ThreadLocation lineLengthLoc = new ThreadLocation("line-length");
  
  public static ThreadLocation miserWidthLoc = new ThreadLocation("miser-width");
  
  int blockDepth;
  
  int[] blocks;
  
  public char[] buffer;
  
  public int bufferFillPointer;
  
  int bufferOffset;
  
  int bufferStartColumn;
  
  int currentBlock;
  
  int lineLength;
  
  int lineNumber;
  
  int miserWidth;
  
  protected Writer out;
  
  public int pendingBlocksCount;
  
  char[] prefix;
  
  int prettyPrintingMode;
  
  int[] queueInts;
  
  int queueSize;
  
  String[] queueStrings;
  
  int queueTail;
  
  char[] suffix;
  
  boolean wordEndSeen;
  
  static {
    indentLoc = new ThreadLocation("indent");
    initialBufferSize = 126;
  }
  
  public PrettyWriter(Writer paramWriter) {
    this.lineLength = 80;
    this.miserWidth = 40;
    this.buffer = new char[initialBufferSize];
    this.blocks = new int[60];
    this.blockDepth = 6;
    this.prefix = new char[initialBufferSize];
    this.suffix = new char[initialBufferSize];
    this.queueInts = new int[300];
    this.queueStrings = new String[300];
    this.currentBlock = -1;
    this.out = paramWriter;
    this.prettyPrintingMode = 1;
  }
  
  public PrettyWriter(Writer paramWriter, int paramInt) {
    this.lineLength = 80;
    this.miserWidth = 40;
    this.buffer = new char[initialBufferSize];
    this.blocks = new int[60];
    this.blockDepth = 6;
    this.prefix = new char[initialBufferSize];
    this.suffix = new char[initialBufferSize];
    this.queueInts = new int[300];
    this.queueStrings = new String[300];
    this.currentBlock = -1;
    this.out = paramWriter;
    this.lineLength = paramInt;
    if (paramInt > 1) {
      paramInt = bool;
    } else {
      paramInt = 0;
    } 
    this.prettyPrintingMode = paramInt;
  }
  
  public PrettyWriter(Writer paramWriter, boolean paramBoolean) {
    boolean bool;
    this.lineLength = 80;
    this.miserWidth = 40;
    this.buffer = new char[initialBufferSize];
    this.blocks = new int[60];
    this.blockDepth = 6;
    this.prefix = new char[initialBufferSize];
    this.suffix = new char[initialBufferSize];
    this.queueInts = new int[300];
    this.queueStrings = new String[300];
    this.currentBlock = -1;
    this.out = paramWriter;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    this.prettyPrintingMode = bool;
  }
  
  private static int enoughSpace(int paramInt1, int paramInt2) {
    int i = paramInt1 * 2;
    paramInt1 += paramInt2 * 5 >> 2;
    return (i > paramInt1) ? i : paramInt1;
  }
  
  private int getPerLinePrefixEnd() {
    return this.blocks[this.blockDepth - 3];
  }
  
  private int getPrefixLength() {
    return this.blocks[this.blockDepth - 4];
  }
  
  private int getQueueSize(int paramInt) {
    return this.queueInts[paramInt] >> 16;
  }
  
  private int getQueueType(int paramInt) {
    return this.queueInts[paramInt] & 0xFF;
  }
  
  private int getSectionColumn() {
    return this.blocks[this.blockDepth - 2];
  }
  
  private int getSectionStartLine() {
    return this.blocks[this.blockDepth - 6];
  }
  
  private int getStartColumn() {
    return this.blocks[this.blockDepth - 1];
  }
  
  private int getSuffixLength() {
    return this.blocks[this.blockDepth - 5];
  }
  
  private int indexPosn(int paramInt) {
    return this.bufferOffset + paramInt;
  }
  
  private int posnColumn(int paramInt) {
    return indexColumn(posnIndex(paramInt));
  }
  
  private int posnIndex(int paramInt) {
    return paramInt - this.bufferOffset;
  }
  
  private void pushLogicalBlock(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    int i = this.blockDepth + 6;
    if (i >= this.blocks.length) {
      int[] arrayOfInt = new int[this.blocks.length * 2];
      System.arraycopy(this.blocks, 0, arrayOfInt, 0, this.blockDepth);
      this.blocks = arrayOfInt;
    } 
    this.blockDepth = i;
    this.blocks[this.blockDepth - 1] = paramInt1;
    this.blocks[this.blockDepth - 2] = paramInt1;
    this.blocks[this.blockDepth - 3] = paramInt2;
    this.blocks[this.blockDepth - 4] = paramInt3;
    this.blocks[this.blockDepth - 5] = paramInt4;
    this.blocks[this.blockDepth - 6] = paramInt5;
  }
  
  public void addIndentation(int paramInt, boolean paramBoolean) {
    if (this.prettyPrintingMode > 0) {
      byte b;
      if (paramBoolean) {
        b = 67;
      } else {
        b = 66;
      } 
      enqueueIndent(b, paramInt);
    } 
  }
  
  public void clearBuffer() {
    this.bufferStartColumn = 0;
    this.bufferFillPointer = 0;
    this.lineNumber = 0;
    this.bufferOffset = 0;
    this.blockDepth = 6;
    this.queueTail = 0;
    this.queueSize = 0;
    this.pendingBlocksCount = 0;
  }
  
  public void clearWordEnd() {
    this.wordEndSeen = false;
  }
  
  public void close() throws IOException {
    if (this.out != null) {
      forcePrettyOutput();
      this.out.close();
      this.out = null;
    } 
    this.buffer = null;
  }
  
  public void closeThis() throws IOException {
    if (this.out != null) {
      forcePrettyOutput();
      this.out = null;
    } 
    this.buffer = null;
  }
  
  int computeTabSize(int paramInt1, int paramInt2, int paramInt3) {
    int k = 0;
    int j = this.queueInts[paramInt1 + 2];
    if ((j & 0x1) != 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if ((j & 0x2) != 0) {
      j = 1;
    } else {
      j = 0;
    } 
    if (i)
      k = paramInt2; 
    paramInt2 = this.queueInts[paramInt1 + 3];
    int i = this.queueInts[paramInt1 + 4];
    if (j != 0) {
      paramInt1 = paramInt2;
      if (i > 1) {
        paramInt3 = (paramInt3 + paramInt2) % i;
        paramInt1 = paramInt2;
        if (paramInt3 != 0)
          paramInt1 = paramInt2 + paramInt3; 
      } 
      return paramInt1;
    } 
    return (paramInt3 <= paramInt2 + k) ? (paramInt3 + k - paramInt3) : (i - (paramInt3 - k) % i);
  }
  
  public void endLogicalBlock() {
    int m = enqueue(5, 2);
    this.pendingBlocksCount--;
    if (this.currentBlock < 0) {
      int i1 = this.blocks[this.blockDepth - 5];
      int i2 = this.blocks[this.blockDepth - 6 - 5];
      if (i1 > i2)
        write(this.suffix, this.suffix.length - i1, i1 - i2); 
      this.currentBlock = -1;
      return;
    } 
    int k = this.currentBlock;
    int n = this.queueInts[k + 4];
    if (n == 0) {
      this.currentBlock = -1;
    } else {
      int i2 = this.queueTail - k;
      int i1 = i2;
      if (i2 > 0)
        i1 = i2 - this.queueInts.length; 
      if (n < i1) {
        this.currentBlock = -1;
      } else {
        i2 = n + k;
        i1 = i2;
        if (i2 < 0)
          i1 = i2 + this.queueInts.length; 
        this.currentBlock = i1;
      } 
    } 
    String str = this.queueStrings[k + 6];
    if (str != null)
      write(str); 
    int j = m - k;
    int i = j;
    if (j < 0)
      i = j + this.queueInts.length; 
    this.queueInts[k + 4] = i;
  }
  
  public void endLogicalBlock(String paramString) {
    if (this.prettyPrintingMode > 0) {
      endLogicalBlock();
      return;
    } 
    if (paramString != null) {
      write(paramString);
      return;
    } 
  }
  
  public int enqueue(int paramInt1, int paramInt2) {
    int i = this.queueInts.length;
    int j = i - this.queueTail - this.queueSize;
    if (j > 0 && paramInt2 > j)
      enqueue(0, j); 
    if (this.queueSize + paramInt2 > i) {
      j = enoughSpace(i, paramInt2);
      int[] arrayOfInt = new int[j];
      String[] arrayOfString = new String[j];
      int k = this.queueTail + this.queueSize - i;
      if (k > 0) {
        System.arraycopy(this.queueInts, 0, arrayOfInt, 0, k);
        System.arraycopy(this.queueStrings, 0, arrayOfString, 0, k);
      } 
      k = i - this.queueTail;
      i = j - i;
      System.arraycopy(this.queueInts, this.queueTail, arrayOfInt, this.queueTail + i, k);
      System.arraycopy(this.queueStrings, this.queueTail, arrayOfString, this.queueTail + i, k);
      this.queueInts = arrayOfInt;
      this.queueStrings = arrayOfString;
      if (this.currentBlock >= this.queueTail)
        this.currentBlock += i; 
      this.queueTail += i;
    } 
    j = this.queueTail + this.queueSize;
    i = j;
    if (j >= this.queueInts.length)
      i = j - this.queueInts.length; 
    this.queueInts[i + 0] = paramInt2 << 16 | paramInt1;
    if (paramInt2 > 1)
      this.queueInts[i + 1] = indexPosn(this.bufferFillPointer); 
    this.queueSize += paramInt2;
    return i;
  }
  
  public int enqueueIndent(char paramChar, int paramInt) {
    int i = enqueue(3, 4);
    this.queueInts[i + 2] = paramChar;
    this.queueInts[i + 3] = paramInt;
    return i;
  }
  
  public void enqueueNewline(int paramInt) {
    this.wordEndSeen = false;
    int k = this.pendingBlocksCount;
    int m = enqueue(2, 5);
    this.queueInts[m + 4] = paramInt;
    this.queueInts[m + 2] = this.pendingBlocksCount;
    this.queueInts[m + 3] = 0;
    int i = this.queueTail;
    int j = this.queueSize;
    while (true) {
      boolean bool;
      if (j > 0) {
        int n = i;
        if (i == this.queueInts.length)
          n = 0; 
        if (n != m) {
          i = getQueueType(n);
          if ((i == 2 || i == 4) && this.queueInts[n + 3] == 0 && k <= this.queueInts[n + 2]) {
            int i1 = m - n;
            i = i1;
            if (i1 < 0)
              i = i1 + this.queueInts.length; 
            this.queueInts[n + 3] = i;
          } 
          i = getQueueSize(n);
          j -= i;
          i = n + i;
          continue;
        } 
      } 
      if (paramInt == 76 || paramInt == 82) {
        bool = true;
      } else {
        bool = false;
      } 
      maybeOutput(bool, false);
      return;
    } 
  }
  
  int enqueueTab(int paramInt1, int paramInt2, int paramInt3) {
    int i = enqueue(6, 5);
    this.queueInts[i + 2] = paramInt1;
    this.queueInts[i + 3] = paramInt2;
    this.queueInts[i + 4] = paramInt3;
    return i;
  }
  
  int ensureSpaceInBuffer(int paramInt) {
    char[] arrayOfChar1 = this.buffer;
    int j = arrayOfChar1.length;
    int i = this.bufferFillPointer;
    int k = j - i;
    if (k > 0)
      return k; 
    if (this.prettyPrintingMode > 0 && i > this.lineLength) {
      if (!maybeOutput(false, false))
        outputPartialLine(); 
      return ensureSpaceInBuffer(paramInt);
    } 
    j = enoughSpace(j, paramInt);
    char[] arrayOfChar2 = new char[j];
    this.buffer = arrayOfChar2;
    paramInt = i;
    while (true) {
      if (--paramInt >= 0) {
        arrayOfChar2[paramInt] = arrayOfChar1[paramInt];
        continue;
      } 
      return j - i;
    } 
  }
  
  void expandTabs(int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iconst_0
    //   3: istore_2
    //   4: aload_0
    //   5: getfield bufferStartColumn : I
    //   8: istore #7
    //   10: aload_0
    //   11: invokespecial getSectionColumn : ()I
    //   14: istore #6
    //   16: aload_0
    //   17: getfield queueTail : I
    //   20: istore #4
    //   22: aload_0
    //   23: getfield queueSize : I
    //   26: istore #5
    //   28: aload_0
    //   29: getfield pendingBlocksCount : I
    //   32: bipush #6
    //   34: imul
    //   35: istore #12
    //   37: iload #5
    //   39: ifle -> 65
    //   42: iload #4
    //   44: istore #8
    //   46: iload #4
    //   48: aload_0
    //   49: getfield queueInts : [I
    //   52: arraylength
    //   53: if_icmpne -> 59
    //   56: iconst_0
    //   57: istore #8
    //   59: iload #8
    //   61: iload_1
    //   62: if_icmpne -> 220
    //   65: iload_3
    //   66: ifle -> 504
    //   69: aload_0
    //   70: getfield bufferFillPointer : I
    //   73: istore #4
    //   75: iload #4
    //   77: iload_2
    //   78: iadd
    //   79: istore #5
    //   81: aload_0
    //   82: getfield buffer : [C
    //   85: astore #16
    //   87: aload #16
    //   89: astore #15
    //   91: aload #16
    //   93: arraylength
    //   94: istore #6
    //   96: iload #4
    //   98: istore_1
    //   99: iload #5
    //   101: iload #6
    //   103: if_icmple -> 122
    //   106: iload #4
    //   108: iload_2
    //   109: invokestatic enoughSpace : (II)I
    //   112: newarray char
    //   114: astore #15
    //   116: aload_0
    //   117: aload #15
    //   119: putfield buffer : [C
    //   122: aload_0
    //   123: iload #5
    //   125: putfield bufferFillPointer : I
    //   128: aload_0
    //   129: aload_0
    //   130: getfield bufferOffset : I
    //   133: iload_2
    //   134: isub
    //   135: putfield bufferOffset : I
    //   138: iload_3
    //   139: iconst_1
    //   140: isub
    //   141: istore_3
    //   142: iload_3
    //   143: iflt -> 487
    //   146: aload_0
    //   147: getfield blocks : [I
    //   150: iload_3
    //   151: iconst_2
    //   152: imul
    //   153: iload #12
    //   155: iadd
    //   156: iaload
    //   157: istore #4
    //   159: aload_0
    //   160: getfield blocks : [I
    //   163: iload_3
    //   164: iconst_2
    //   165: imul
    //   166: iload #12
    //   168: iadd
    //   169: iconst_1
    //   170: iadd
    //   171: iaload
    //   172: istore #5
    //   174: iload #4
    //   176: iload_2
    //   177: iadd
    //   178: istore #6
    //   180: aload #16
    //   182: iload #4
    //   184: aload #15
    //   186: iload #6
    //   188: iload_1
    //   189: iload #4
    //   191: isub
    //   192: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   195: iload #6
    //   197: iload #5
    //   199: isub
    //   200: istore_1
    //   201: iload_1
    //   202: iload #6
    //   204: if_icmpge -> 476
    //   207: aload #15
    //   209: iload_1
    //   210: bipush #32
    //   212: castore
    //   213: iload_1
    //   214: iconst_1
    //   215: iadd
    //   216: istore_1
    //   217: goto -> 201
    //   220: aload_0
    //   221: iload #8
    //   223: invokespecial getQueueType : (I)I
    //   226: bipush #6
    //   228: if_icmpne -> 419
    //   231: aload_0
    //   232: aload_0
    //   233: getfield queueInts : [I
    //   236: iload #8
    //   238: iconst_1
    //   239: iadd
    //   240: iaload
    //   241: invokespecial posnIndex : (I)I
    //   244: istore #14
    //   246: aload_0
    //   247: iload #8
    //   249: iload #6
    //   251: iload #7
    //   253: iload #14
    //   255: iadd
    //   256: invokevirtual computeTabSize : (III)I
    //   259: istore #13
    //   261: iload_2
    //   262: istore #11
    //   264: iload #7
    //   266: istore #10
    //   268: iload_3
    //   269: istore #4
    //   271: iload #6
    //   273: istore #9
    //   275: iload #13
    //   277: ifeq -> 379
    //   280: iload_3
    //   281: iconst_2
    //   282: imul
    //   283: iload #12
    //   285: iadd
    //   286: iconst_1
    //   287: iadd
    //   288: aload_0
    //   289: getfield blocks : [I
    //   292: arraylength
    //   293: if_icmplt -> 329
    //   296: aload_0
    //   297: getfield blocks : [I
    //   300: arraylength
    //   301: iconst_2
    //   302: imul
    //   303: newarray int
    //   305: astore #15
    //   307: aload_0
    //   308: getfield blocks : [I
    //   311: iconst_0
    //   312: aload #15
    //   314: iconst_0
    //   315: aload_0
    //   316: getfield blocks : [I
    //   319: arraylength
    //   320: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   323: aload_0
    //   324: aload #15
    //   326: putfield blocks : [I
    //   329: aload_0
    //   330: getfield blocks : [I
    //   333: iload_3
    //   334: iconst_2
    //   335: imul
    //   336: iload #12
    //   338: iadd
    //   339: iload #14
    //   341: iastore
    //   342: aload_0
    //   343: getfield blocks : [I
    //   346: iload_3
    //   347: iconst_2
    //   348: imul
    //   349: iload #12
    //   351: iadd
    //   352: iconst_1
    //   353: iadd
    //   354: iload #13
    //   356: iastore
    //   357: iload_3
    //   358: iconst_1
    //   359: iadd
    //   360: istore #4
    //   362: iload_2
    //   363: iload #13
    //   365: iadd
    //   366: istore #11
    //   368: iload #7
    //   370: iload #13
    //   372: iadd
    //   373: istore #10
    //   375: iload #6
    //   377: istore #9
    //   379: aload_0
    //   380: iload #8
    //   382: invokespecial getQueueSize : (I)I
    //   385: istore_2
    //   386: iload #5
    //   388: iload_2
    //   389: isub
    //   390: istore #5
    //   392: iload #8
    //   394: iload_2
    //   395: iadd
    //   396: istore #6
    //   398: iload #11
    //   400: istore_2
    //   401: iload #10
    //   403: istore #7
    //   405: iload #4
    //   407: istore_3
    //   408: iload #6
    //   410: istore #4
    //   412: iload #9
    //   414: istore #6
    //   416: goto -> 37
    //   419: iload #8
    //   421: iconst_2
    //   422: if_icmpeq -> 445
    //   425: iload_2
    //   426: istore #11
    //   428: iload #7
    //   430: istore #10
    //   432: iload_3
    //   433: istore #4
    //   435: iload #6
    //   437: istore #9
    //   439: iload #8
    //   441: iconst_4
    //   442: if_icmpne -> 379
    //   445: iload #7
    //   447: aload_0
    //   448: aload_0
    //   449: getfield queueInts : [I
    //   452: iload #8
    //   454: iconst_1
    //   455: iadd
    //   456: iaload
    //   457: invokespecial posnIndex : (I)I
    //   460: iadd
    //   461: istore #9
    //   463: iload_2
    //   464: istore #11
    //   466: iload #7
    //   468: istore #10
    //   470: iload_3
    //   471: istore #4
    //   473: goto -> 379
    //   476: iload_2
    //   477: iload #5
    //   479: isub
    //   480: istore_2
    //   481: iload #4
    //   483: istore_1
    //   484: goto -> 138
    //   487: aload #15
    //   489: aload #16
    //   491: if_acmpeq -> 504
    //   494: aload #16
    //   496: iconst_0
    //   497: aload #15
    //   499: iconst_0
    //   500: iload_1
    //   501: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   504: return
  }
  
  int fitsOnLine(int paramInt, boolean paramBoolean) {
    byte b = -1;
    int j = this.lineLength;
    int i = j;
    if (!printReadably()) {
      i = j;
      if (getMaxLines() == this.lineNumber)
        i = j - 3 - getSuffixLength(); 
    } 
    if (paramInt >= 0) {
      j = b;
      if (posnColumn(this.queueInts[paramInt + 1]) <= i)
        j = 1; 
      return j;
    } 
    j = b;
    if (!paramBoolean) {
      j = b;
      if (indexColumn(this.bufferFillPointer) <= i)
        return 0; 
    } 
    return j;
  }
  
  public void flush() {
    if (this.out == null)
      return; 
    try {
      forcePrettyOutput();
      this.out.flush();
      return;
    } catch (IOException iOException) {
      throw new RuntimeException(iOException.toString());
    } 
  }
  
  public void forcePrettyOutput() throws IOException {
    maybeOutput(false, true);
    if (this.bufferFillPointer > 0)
      outputPartialLine(); 
    expandTabs(-1);
    this.bufferStartColumn = getColumnNumber();
    this.out.write(this.buffer, 0, this.bufferFillPointer);
    this.bufferFillPointer = 0;
    this.queueSize = 0;
  }
  
  public int getColumnNumber() {
    int j;
    int i = this.bufferFillPointer;
    while (true) {
      j = i - 1;
      if (j < 0)
        return this.bufferStartColumn + this.bufferFillPointer; 
      char c = this.buffer[j];
      if (c != '\n') {
        i = j;
        if (c == '\r')
          break; 
        continue;
      } 
      break;
    } 
    return this.bufferFillPointer - j + 1;
  }
  
  int getMaxLines() {
    return -1;
  }
  
  protected int getMiserWidth() {
    return this.miserWidth;
  }
  
  public int getPrettyPrintingMode() {
    return this.prettyPrintingMode;
  }
  
  int indexColumn(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield bufferStartColumn : I
    //   4: istore #5
    //   6: aload_0
    //   7: invokespecial getSectionColumn : ()I
    //   10: istore #4
    //   12: aload_0
    //   13: iload_1
    //   14: invokespecial indexPosn : (I)I
    //   17: istore #8
    //   19: aload_0
    //   20: getfield queueTail : I
    //   23: istore_2
    //   24: aload_0
    //   25: getfield queueSize : I
    //   28: istore_3
    //   29: iload_3
    //   30: ifle -> 84
    //   33: iload_2
    //   34: istore #6
    //   36: iload_2
    //   37: aload_0
    //   38: getfield queueInts : [I
    //   41: arraylength
    //   42: if_icmplt -> 48
    //   45: iconst_0
    //   46: istore #6
    //   48: aload_0
    //   49: iload #6
    //   51: invokespecial getQueueType : (I)I
    //   54: istore #9
    //   56: iload #5
    //   58: istore_2
    //   59: iload #4
    //   61: istore #7
    //   63: iload #9
    //   65: ifeq -> 120
    //   68: aload_0
    //   69: getfield queueInts : [I
    //   72: iload #6
    //   74: iconst_1
    //   75: iadd
    //   76: iaload
    //   77: istore_2
    //   78: iload_2
    //   79: iload #8
    //   81: if_icmplt -> 89
    //   84: iload #5
    //   86: iload_1
    //   87: iadd
    //   88: ireturn
    //   89: iload #9
    //   91: bipush #6
    //   93: if_icmpne -> 153
    //   96: iload #5
    //   98: aload_0
    //   99: iload #6
    //   101: iload #4
    //   103: aload_0
    //   104: iload_2
    //   105: invokespecial posnIndex : (I)I
    //   108: iload #5
    //   110: iadd
    //   111: invokevirtual computeTabSize : (III)I
    //   114: iadd
    //   115: istore_2
    //   116: iload #4
    //   118: istore #7
    //   120: aload_0
    //   121: iload #6
    //   123: invokespecial getQueueSize : (I)I
    //   126: istore #4
    //   128: iload_3
    //   129: iload #4
    //   131: isub
    //   132: istore_3
    //   133: iload #6
    //   135: iload #4
    //   137: iadd
    //   138: istore #4
    //   140: iload_2
    //   141: istore #5
    //   143: iload #4
    //   145: istore_2
    //   146: iload #7
    //   148: istore #4
    //   150: goto -> 29
    //   153: iload #9
    //   155: iconst_2
    //   156: if_icmpeq -> 172
    //   159: iload #5
    //   161: istore_2
    //   162: iload #4
    //   164: istore #7
    //   166: iload #9
    //   168: iconst_4
    //   169: if_icmpne -> 120
    //   172: iload #5
    //   174: aload_0
    //   175: aload_0
    //   176: getfield queueInts : [I
    //   179: iload #6
    //   181: iconst_1
    //   182: iadd
    //   183: iaload
    //   184: invokespecial posnIndex : (I)I
    //   187: iadd
    //   188: istore #7
    //   190: iload #5
    //   192: istore_2
    //   193: goto -> 120
  }
  
  boolean isMisering() {
    int i = getMiserWidth();
    return (i > 0 && this.lineLength - getStartColumn() <= i);
  }
  
  public boolean isPrettyPrinting() {
    return (this.prettyPrintingMode > 0);
  }
  
  public void lineAbbreviationHappened() {}
  
  boolean maybeOutput(boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #7
    //   3: aload_0
    //   4: getfield queueSize : I
    //   7: ifle -> 626
    //   10: aload_0
    //   11: getfield queueTail : I
    //   14: aload_0
    //   15: getfield queueInts : [I
    //   18: arraylength
    //   19: if_icmplt -> 27
    //   22: aload_0
    //   23: iconst_0
    //   24: putfield queueTail : I
    //   27: aload_0
    //   28: getfield queueTail : I
    //   31: istore #4
    //   33: aload_0
    //   34: iload #4
    //   36: invokespecial getQueueType : (I)I
    //   39: tableswitch default -> 72, 2 -> 115, 3 -> 335, 4 -> 419, 5 -> 596, 6 -> 610
    //   72: iload #7
    //   74: istore #8
    //   76: iload #4
    //   78: istore_3
    //   79: aload_0
    //   80: aload_0
    //   81: getfield queueTail : I
    //   84: invokespecial getQueueSize : (I)I
    //   87: istore #4
    //   89: aload_0
    //   90: aload_0
    //   91: getfield queueSize : I
    //   94: iload #4
    //   96: isub
    //   97: putfield queueSize : I
    //   100: aload_0
    //   101: iload_3
    //   102: iload #4
    //   104: iadd
    //   105: putfield queueTail : I
    //   108: iload #8
    //   110: istore #7
    //   112: goto -> 3
    //   115: iconst_m1
    //   116: istore #5
    //   118: aload_0
    //   119: getfield queueInts : [I
    //   122: iload #4
    //   124: iconst_4
    //   125: iadd
    //   126: iaload
    //   127: lookupswitch default -> 160, 70 -> 218, 77 -> 209, 83 -> 242
    //   160: iconst_1
    //   161: istore #6
    //   163: iload #4
    //   165: istore_3
    //   166: iload #7
    //   168: istore #8
    //   170: iload #6
    //   172: ifeq -> 79
    //   175: iconst_1
    //   176: istore #8
    //   178: iload_2
    //   179: ifeq -> 323
    //   182: iload #5
    //   184: ifne -> 323
    //   187: aload_0
    //   188: invokevirtual outputPartialLine : ()V
    //   191: iload #4
    //   193: istore_3
    //   194: goto -> 79
    //   197: astore #9
    //   199: new java/lang/RuntimeException
    //   202: dup
    //   203: aload #9
    //   205: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   208: athrow
    //   209: aload_0
    //   210: invokevirtual isMisering : ()Z
    //   213: istore #6
    //   215: goto -> 163
    //   218: aload_0
    //   219: invokevirtual isMisering : ()Z
    //   222: ifne -> 236
    //   225: aload_0
    //   226: getfield lineNumber : I
    //   229: aload_0
    //   230: invokespecial getSectionStartLine : ()I
    //   233: if_icmple -> 242
    //   236: iconst_1
    //   237: istore #6
    //   239: goto -> 163
    //   242: aload_0
    //   243: getfield queueInts : [I
    //   246: iload #4
    //   248: iconst_3
    //   249: iadd
    //   250: iaload
    //   251: istore_3
    //   252: iload_3
    //   253: ifne -> 277
    //   256: iconst_m1
    //   257: istore_3
    //   258: aload_0
    //   259: iload_3
    //   260: iload_1
    //   261: invokevirtual fitsOnLine : (IZ)I
    //   264: istore #5
    //   266: iload #5
    //   268: ifle -> 308
    //   271: iconst_0
    //   272: istore #6
    //   274: goto -> 163
    //   277: iload_3
    //   278: iload #4
    //   280: iadd
    //   281: istore #5
    //   283: iload #5
    //   285: istore_3
    //   286: iload #5
    //   288: aload_0
    //   289: getfield queueInts : [I
    //   292: arraylength
    //   293: if_icmplt -> 258
    //   296: iload #5
    //   298: aload_0
    //   299: getfield queueInts : [I
    //   302: arraylength
    //   303: isub
    //   304: istore_3
    //   305: goto -> 258
    //   308: iload #5
    //   310: iflt -> 317
    //   313: iload_2
    //   314: ifeq -> 626
    //   317: iconst_1
    //   318: istore #6
    //   320: goto -> 163
    //   323: aload_0
    //   324: iload #4
    //   326: invokevirtual outputLine : (I)V
    //   329: iload #4
    //   331: istore_3
    //   332: goto -> 79
    //   335: iload #4
    //   337: istore_3
    //   338: iload #7
    //   340: istore #8
    //   342: aload_0
    //   343: invokevirtual isMisering : ()Z
    //   346: ifne -> 79
    //   349: aload_0
    //   350: getfield queueInts : [I
    //   353: iload #4
    //   355: iconst_2
    //   356: iadd
    //   357: iaload
    //   358: istore_3
    //   359: aload_0
    //   360: getfield queueInts : [I
    //   363: iload #4
    //   365: iconst_3
    //   366: iadd
    //   367: iaload
    //   368: istore #5
    //   370: iload_3
    //   371: bipush #66
    //   373: if_icmpne -> 399
    //   376: iload #5
    //   378: aload_0
    //   379: invokespecial getStartColumn : ()I
    //   382: iadd
    //   383: istore_3
    //   384: aload_0
    //   385: iload_3
    //   386: invokevirtual setIndentation : (I)V
    //   389: iload #4
    //   391: istore_3
    //   392: iload #7
    //   394: istore #8
    //   396: goto -> 79
    //   399: iload #5
    //   401: aload_0
    //   402: aload_0
    //   403: getfield queueInts : [I
    //   406: iload #4
    //   408: iconst_1
    //   409: iadd
    //   410: iaload
    //   411: invokespecial posnColumn : (I)I
    //   414: iadd
    //   415: istore_3
    //   416: goto -> 384
    //   419: aload_0
    //   420: getfield queueInts : [I
    //   423: iload #4
    //   425: iconst_3
    //   426: iadd
    //   427: iaload
    //   428: istore_3
    //   429: iload_3
    //   430: ifle -> 533
    //   433: iload_3
    //   434: iload #4
    //   436: iadd
    //   437: aload_0
    //   438: getfield queueInts : [I
    //   441: arraylength
    //   442: irem
    //   443: istore_3
    //   444: aload_0
    //   445: iload_3
    //   446: iload_1
    //   447: invokevirtual fitsOnLine : (IZ)I
    //   450: istore_3
    //   451: iload_3
    //   452: ifle -> 538
    //   455: aload_0
    //   456: getfield queueInts : [I
    //   459: iload #4
    //   461: iconst_4
    //   462: iadd
    //   463: iaload
    //   464: istore #5
    //   466: iload #5
    //   468: iload #4
    //   470: iadd
    //   471: aload_0
    //   472: getfield queueInts : [I
    //   475: arraylength
    //   476: irem
    //   477: istore_3
    //   478: aload_0
    //   479: iload_3
    //   480: invokevirtual expandTabs : (I)V
    //   483: aload_0
    //   484: iload_3
    //   485: putfield queueTail : I
    //   488: aload_0
    //   489: aload_0
    //   490: getfield queueSize : I
    //   493: iload #5
    //   495: isub
    //   496: putfield queueSize : I
    //   499: iload_3
    //   500: istore #5
    //   502: iload #5
    //   504: istore_3
    //   505: iload #7
    //   507: istore #8
    //   509: aload_0
    //   510: getfield currentBlock : I
    //   513: iload #4
    //   515: if_icmpne -> 79
    //   518: aload_0
    //   519: iconst_m1
    //   520: putfield currentBlock : I
    //   523: iload #5
    //   525: istore_3
    //   526: iload #7
    //   528: istore #8
    //   530: goto -> 79
    //   533: iconst_m1
    //   534: istore_3
    //   535: goto -> 444
    //   538: iload_3
    //   539: iflt -> 546
    //   542: iload_2
    //   543: ifeq -> 626
    //   546: aload_0
    //   547: getfield queueStrings : [Ljava/lang/String;
    //   550: iload #4
    //   552: iconst_5
    //   553: iadd
    //   554: aaload
    //   555: astore #9
    //   557: aload_0
    //   558: getfield queueStrings : [Ljava/lang/String;
    //   561: iload #4
    //   563: bipush #6
    //   565: iadd
    //   566: aaload
    //   567: astore #10
    //   569: aload_0
    //   570: aload_0
    //   571: aload_0
    //   572: getfield queueInts : [I
    //   575: iload #4
    //   577: iconst_1
    //   578: iadd
    //   579: iaload
    //   580: invokespecial posnColumn : (I)I
    //   583: aload #9
    //   585: aload #10
    //   587: invokevirtual reallyStartLogicalBlock : (ILjava/lang/String;Ljava/lang/String;)V
    //   590: iload #4
    //   592: istore_3
    //   593: goto -> 499
    //   596: aload_0
    //   597: invokevirtual reallyEndLogicalBlock : ()V
    //   600: iload #4
    //   602: istore_3
    //   603: iload #7
    //   605: istore #8
    //   607: goto -> 79
    //   610: aload_0
    //   611: iload #4
    //   613: invokevirtual expandTabs : (I)V
    //   616: iload #4
    //   618: istore_3
    //   619: iload #7
    //   621: istore #8
    //   623: goto -> 79
    //   626: iload #7
    //   628: ireturn
    // Exception table:
    //   from	to	target	type
    //   187	191	197	java/io/IOException
    //   323	329	197	java/io/IOException
  }
  
  void outputLine(int paramInt) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: getfield buffer : [C
    //   4: astore #10
    //   6: aload_0
    //   7: getfield queueInts : [I
    //   10: iload_1
    //   11: iconst_4
    //   12: iadd
    //   13: iaload
    //   14: bipush #76
    //   16: if_icmpne -> 285
    //   19: iconst_1
    //   20: istore_2
    //   21: aload_0
    //   22: aload_0
    //   23: getfield queueInts : [I
    //   26: iload_1
    //   27: iconst_1
    //   28: iadd
    //   29: iaload
    //   30: invokespecial posnIndex : (I)I
    //   33: istore_3
    //   34: iload_2
    //   35: ifeq -> 290
    //   38: iload_3
    //   39: istore_1
    //   40: aload_0
    //   41: getfield out : Ljava/io/Writer;
    //   44: aload #10
    //   46: iconst_0
    //   47: iload_1
    //   48: invokevirtual write : ([CII)V
    //   51: aload_0
    //   52: getfield lineNumber : I
    //   55: iconst_1
    //   56: iadd
    //   57: istore #4
    //   59: aload_0
    //   60: invokevirtual printReadably : ()Z
    //   63: ifne -> 129
    //   66: aload_0
    //   67: invokevirtual getMaxLines : ()I
    //   70: istore_1
    //   71: iload_1
    //   72: ifle -> 129
    //   75: iload #4
    //   77: iload_1
    //   78: if_icmplt -> 129
    //   81: aload_0
    //   82: getfield out : Ljava/io/Writer;
    //   85: ldc_w ' ..'
    //   88: invokevirtual write : (Ljava/lang/String;)V
    //   91: aload_0
    //   92: invokespecial getSuffixLength : ()I
    //   95: istore_1
    //   96: iload_1
    //   97: ifeq -> 125
    //   100: aload_0
    //   101: getfield suffix : [C
    //   104: astore #9
    //   106: aload #9
    //   108: arraylength
    //   109: istore #5
    //   111: aload_0
    //   112: getfield out : Ljava/io/Writer;
    //   115: aload #9
    //   117: iload #5
    //   119: iload_1
    //   120: isub
    //   121: iload_1
    //   122: invokevirtual write : ([CII)V
    //   125: aload_0
    //   126: invokevirtual lineAbbreviationHappened : ()V
    //   129: aload_0
    //   130: iload #4
    //   132: putfield lineNumber : I
    //   135: aload_0
    //   136: getfield out : Ljava/io/Writer;
    //   139: bipush #10
    //   141: invokevirtual write : (I)V
    //   144: aload_0
    //   145: iconst_0
    //   146: putfield bufferStartColumn : I
    //   149: aload_0
    //   150: getfield bufferFillPointer : I
    //   153: istore #5
    //   155: iload_2
    //   156: ifeq -> 328
    //   159: aload_0
    //   160: invokespecial getPerLinePrefixEnd : ()I
    //   163: istore_1
    //   164: iload_3
    //   165: iload_1
    //   166: isub
    //   167: istore #6
    //   169: iload #5
    //   171: iload #6
    //   173: isub
    //   174: istore #7
    //   176: aload #10
    //   178: astore #9
    //   180: aload #10
    //   182: arraylength
    //   183: istore #8
    //   185: iload #7
    //   187: iload #8
    //   189: if_icmple -> 212
    //   192: iload #8
    //   194: iload #7
    //   196: iload #8
    //   198: isub
    //   199: invokestatic enoughSpace : (II)I
    //   202: newarray char
    //   204: astore #9
    //   206: aload_0
    //   207: aload #9
    //   209: putfield buffer : [C
    //   212: aload #10
    //   214: iload_3
    //   215: aload #9
    //   217: iload_1
    //   218: iload #5
    //   220: iload_3
    //   221: isub
    //   222: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   225: aload_0
    //   226: getfield prefix : [C
    //   229: iconst_0
    //   230: aload #9
    //   232: iconst_0
    //   233: iload_1
    //   234: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   237: aload_0
    //   238: iload #7
    //   240: putfield bufferFillPointer : I
    //   243: aload_0
    //   244: aload_0
    //   245: getfield bufferOffset : I
    //   248: iload #6
    //   250: iadd
    //   251: putfield bufferOffset : I
    //   254: iload_2
    //   255: ifne -> 284
    //   258: aload_0
    //   259: getfield blocks : [I
    //   262: aload_0
    //   263: getfield blockDepth : I
    //   266: iconst_2
    //   267: isub
    //   268: iload_1
    //   269: iastore
    //   270: aload_0
    //   271: getfield blocks : [I
    //   274: aload_0
    //   275: getfield blockDepth : I
    //   278: bipush #6
    //   280: isub
    //   281: iload #4
    //   283: iastore
    //   284: return
    //   285: iconst_0
    //   286: istore_2
    //   287: goto -> 21
    //   290: iload_3
    //   291: istore_1
    //   292: iload_1
    //   293: iconst_1
    //   294: isub
    //   295: istore #4
    //   297: iload #4
    //   299: ifge -> 307
    //   302: iconst_0
    //   303: istore_1
    //   304: goto -> 40
    //   307: iload #4
    //   309: istore_1
    //   310: aload #10
    //   312: iload #4
    //   314: caload
    //   315: bipush #32
    //   317: if_icmpeq -> 292
    //   320: iload #4
    //   322: iconst_1
    //   323: iadd
    //   324: istore_1
    //   325: goto -> 40
    //   328: aload_0
    //   329: invokespecial getPrefixLength : ()I
    //   332: istore_1
    //   333: goto -> 164
  }
  
  void outputPartialLine() {
    int i = this.queueTail;
    while (this.queueSize > 0 && getQueueType(i) == 0) {
      int k = getQueueSize(i);
      this.queueSize -= k;
      k = i + k;
      i = k;
      if (k == this.queueInts.length)
        i = 0; 
      this.queueTail = i;
    } 
    int j = this.bufferFillPointer;
    if (this.queueSize > 0) {
      i = posnIndex(this.queueInts[i + 1]);
    } else {
      i = j;
    } 
    j -= i;
    if (i <= 0)
      throw new Error("outputPartialLine called when nothing can be output."); 
    try {
      this.out.write(this.buffer, 0, i);
      this.bufferFillPointer = i;
      this.bufferStartColumn = getColumnNumber();
      System.arraycopy(this.buffer, i, this.buffer, 0, j);
      this.bufferFillPointer = j;
      this.bufferOffset += i;
      return;
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  boolean printReadably() {
    return true;
  }
  
  void reallyEndLogicalBlock() {
    int i = getPrefixLength();
    this.blockDepth -= 6;
    int j = getPrefixLength();
    if (j > i)
      while (i < j) {
        this.prefix[i] = ' ';
        i++;
      }  
  }
  
  void reallyStartLogicalBlock(int paramInt, String paramString1, String paramString2) {
    int i = getPerLinePrefixEnd();
    int k = getPrefixLength();
    int j = getSuffixLength();
    pushLogicalBlock(paramInt, i, k, j, this.lineNumber);
    setIndentation(paramInt);
    if (paramString1 != null) {
      this.blocks[this.blockDepth - 3] = paramInt;
      i = paramString1.length();
      paramString1.getChars(0, i, this.suffix, paramInt - i);
    } 
    if (paramString2 != null) {
      char[] arrayOfChar = this.suffix;
      i = arrayOfChar.length;
      k = paramString2.length();
      int m = j + k;
      paramInt = i;
      if (m > i) {
        paramInt = enoughSpace(i, k);
        this.suffix = new char[paramInt];
        System.arraycopy(arrayOfChar, i - j, this.suffix, paramInt - j, j);
      } 
      paramString2.getChars(0, k, arrayOfChar, paramInt - m);
      this.blocks[this.blockDepth - 5] = m;
    } 
  }
  
  public void setColumnNumber(int paramInt) {
    this.bufferStartColumn += paramInt - getColumnNumber();
  }
  
  public void setIndentation(int paramInt) {
    char[] arrayOfChar = this.prefix;
    int m = arrayOfChar.length;
    int j = getPrefixLength();
    int k = getPerLinePrefixEnd();
    int i = paramInt;
    if (k > paramInt)
      i = k; 
    if (i > m) {
      arrayOfChar = new char[enoughSpace(m, i - m)];
      System.arraycopy(this.prefix, 0, arrayOfChar, 0, j);
      this.prefix = arrayOfChar;
    } 
    if (i > j)
      for (paramInt = j; paramInt < i; paramInt++)
        arrayOfChar[paramInt] = ' ';  
    this.blocks[this.blockDepth - 4] = i;
  }
  
  public void setPrettyPrinting(boolean paramBoolean) {
    boolean bool;
    if (paramBoolean) {
      bool = false;
    } else {
      bool = true;
    } 
    this.prettyPrintingMode = bool;
  }
  
  public void setPrettyPrintingMode(int paramInt) {
    this.prettyPrintingMode = paramInt;
  }
  
  public void startLogicalBlock(String paramString1, boolean paramBoolean, String paramString2) {
    if (this.queueSize == 0 && this.bufferFillPointer == 0) {
      Object object = lineLengthLoc.get(null);
      if (object == null) {
        this.lineLength = 80;
      } else {
        this.lineLength = Integer.parseInt(object.toString());
      } 
      object = miserWidthLoc.get(null);
      if (object == null || object == Boolean.FALSE || object == LList.Empty) {
        this.miserWidth = -1;
      } else {
        this.miserWidth = Integer.parseInt(object.toString());
      } 
      indentLoc.get(null);
    } 
    if (paramString1 != null)
      write(paramString1); 
    if (this.prettyPrintingMode == 0)
      return; 
    int j = enqueue(4, 7);
    this.queueInts[j + 2] = this.pendingBlocksCount;
    String[] arrayOfString = this.queueStrings;
    if (!paramBoolean)
      paramString1 = null; 
    arrayOfString[j + 5] = paramString1;
    this.queueStrings[j + 6] = paramString2;
    this.pendingBlocksCount++;
    int i = this.currentBlock;
    if (i < 0) {
      i = 0;
    } else {
      int k = i - j;
      i = k;
      if (k > 0)
        i = k - this.queueInts.length; 
    } 
    this.queueInts[j + 4] = i;
    this.queueInts[j + 3] = 0;
    this.currentBlock = j;
  }
  
  public void write(int paramInt) {
    this.wordEndSeen = false;
    if (paramInt == 10 && this.prettyPrintingMode > 0) {
      enqueueNewline(76);
      return;
    } 
    ensureSpaceInBuffer(1);
    int i = this.bufferFillPointer;
    this.buffer[i] = (char)paramInt;
    this.bufferFillPointer = i + 1;
    if (paramInt == 32 && this.prettyPrintingMode > 1 && this.currentBlock < 0) {
      enqueueNewline(83);
      return;
    } 
  }
  
  public void write(String paramString) {
    write(paramString, 0, paramString.length());
  }
  
  public void write(String paramString, int paramInt1, int paramInt2) {
    this.wordEndSeen = false;
    int i = paramInt2;
    paramInt2 = paramInt1;
    while (i > 0) {
      int j = i;
      int k = ensureSpaceInBuffer(i);
      paramInt1 = j;
      if (j > k)
        paramInt1 = k; 
      k = this.bufferFillPointer;
      j = i - paramInt1;
      for (i = k;; i = j) {
        k = paramInt1 - 1;
        if (k >= 0) {
          char c = paramString.charAt(paramInt2);
          if (c == '\n' && this.prettyPrintingMode > 0) {
            this.bufferFillPointer = i;
            enqueueNewline(76);
            paramInt1 = this.bufferFillPointer;
          } else {
            char[] arrayOfChar = this.buffer;
            int m = i + 1;
            arrayOfChar[i] = c;
            paramInt1 = m;
            if (c == ' ') {
              paramInt1 = m;
              if (this.prettyPrintingMode > 1) {
                paramInt1 = m;
                if (this.currentBlock < 0) {
                  this.bufferFillPointer = m;
                  enqueueNewline(83);
                  paramInt1 = this.bufferFillPointer;
                } 
              } 
            } 
          } 
          i = paramInt1;
          paramInt2++;
          paramInt1 = k;
          continue;
        } 
        this.bufferFillPointer = i;
      } 
    } 
  }
  
  public void write(char[] paramArrayOfchar) {
    write(paramArrayOfchar, 0, paramArrayOfchar.length);
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    this.wordEndSeen = false;
    int i = paramInt1 + paramInt2;
    label33: while (paramInt2 > 0) {
      int j;
      int k;
      int m = paramInt1;
      while (true) {
        k = paramInt1;
        j = paramInt2;
        if (m < i) {
          if (this.prettyPrintingMode > 0) {
            j = paramArrayOfchar[m];
            if (j == 10 || (j == 32 && this.currentBlock < 0)) {
              write(paramArrayOfchar, paramInt1, m - paramInt1);
              write(j);
              paramInt1 = m + 1;
              paramInt2 = i - paramInt1;
              continue label33;
            } 
          } 
          m++;
          continue;
        } 
        break;
      } 
      while (true) {
        paramInt2 = ensureSpaceInBuffer(j);
        if (paramInt2 >= j)
          paramInt2 = j; 
        m = this.bufferFillPointer;
        int n = m + paramInt2;
        for (paramInt1 = k; m < n; paramInt1++) {
          this.buffer[m] = paramArrayOfchar[paramInt1];
          m++;
        } 
        this.bufferFillPointer = n;
        paramInt2 = j - paramInt2;
        if (paramInt2 == 0)
          continue label33; 
        j = paramInt2;
        k = paramInt1;
      } 
    } 
  }
  
  public final void writeBreak(int paramInt) {
    if (this.prettyPrintingMode > 0)
      enqueueNewline(paramInt); 
  }
  
  public void writeWordEnd() {
    this.wordEndSeen = true;
  }
  
  public void writeWordStart() {
    if (this.wordEndSeen)
      write(32); 
    this.wordEndSeen = false;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/PrettyWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
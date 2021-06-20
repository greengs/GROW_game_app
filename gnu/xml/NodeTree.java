package gnu.xml;

import gnu.kawa.xml.ElementType;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.ItemPredicate;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Symbol;
import gnu.text.Path;
import java.io.Writer;

public class NodeTree extends TreeList {
  static int counter;
  
  int id;
  
  int idCount;
  
  String[] idNames;
  
  int[] idOffsets;
  
  public static NodeTree make() {
    return new NodeTree();
  }
  
  public int ancestorAttribute(int paramInt, String paramString1, String paramString2) {
    while (true) {
      if (paramInt == -1)
        return 0; 
      int j = getAttributeI(paramInt, paramString1, paramString2);
      int i = j;
      if (j == 0) {
        paramInt = parentPos(paramInt);
        continue;
      } 
      return i;
    } 
  }
  
  public Path baseUriOfPos(int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #6
    //   3: aload_0
    //   4: iload_1
    //   5: invokevirtual posToDataIndex : (I)I
    //   8: istore #4
    //   10: iload_1
    //   11: istore_3
    //   12: iload #4
    //   14: istore_1
    //   15: iload_1
    //   16: aload_0
    //   17: getfield data : [C
    //   20: arraylength
    //   21: if_icmpne -> 26
    //   24: aconst_null
    //   25: areturn
    //   26: aload_0
    //   27: getfield data : [C
    //   30: iload_1
    //   31: caload
    //   32: istore #4
    //   34: aconst_null
    //   35: astore #7
    //   37: iload #4
    //   39: ldc 61714
    //   41: if_icmpne -> 108
    //   44: aload_0
    //   45: iload_1
    //   46: iconst_1
    //   47: iadd
    //   48: invokevirtual getIntN : (I)I
    //   51: istore_3
    //   52: aload #7
    //   54: astore #5
    //   56: iload_3
    //   57: iflt -> 71
    //   60: aload_0
    //   61: getfield objects : [Ljava/lang/Object;
    //   64: iload_3
    //   65: aaload
    //   66: invokestatic makeURI : (Ljava/lang/Object;)Lgnu/text/URIPath;
    //   69: astore #5
    //   71: aload #6
    //   73: astore #7
    //   75: aload #5
    //   77: ifnull -> 176
    //   80: aload #6
    //   82: ifnull -> 89
    //   85: iload_2
    //   86: ifne -> 164
    //   89: aload #5
    //   91: invokevirtual isAbsolute : ()Z
    //   94: ifne -> 105
    //   97: aload #5
    //   99: astore #7
    //   101: iload_2
    //   102: ifne -> 176
    //   105: aload #5
    //   107: areturn
    //   108: iload #4
    //   110: ldc 40960
    //   112: if_icmplt -> 122
    //   115: iload #4
    //   117: ldc 45055
    //   119: if_icmple -> 133
    //   122: aload #7
    //   124: astore #5
    //   126: iload #4
    //   128: ldc 61704
    //   130: if_icmpne -> 71
    //   133: aload_0
    //   134: iload_3
    //   135: ldc 'http://www.w3.org/XML/1998/namespace'
    //   137: ldc 'base'
    //   139: invokevirtual getAttributeI : (ILjava/lang/String;Ljava/lang/String;)I
    //   142: istore_3
    //   143: aload #7
    //   145: astore #5
    //   147: iload_3
    //   148: ifeq -> 71
    //   151: aload_0
    //   152: iload_3
    //   153: invokestatic getNodeValue : (Lgnu/xml/NodeTree;I)Ljava/lang/String;
    //   156: invokestatic valueOf : (Ljava/lang/String;)Lgnu/text/URIPath;
    //   159: astore #5
    //   161: goto -> 71
    //   164: aload #5
    //   166: aload #6
    //   168: invokevirtual resolve : (Lgnu/text/Path;)Lgnu/text/Path;
    //   171: astore #5
    //   173: goto -> 89
    //   176: aload_0
    //   177: iload_1
    //   178: invokevirtual parentOrEntityI : (I)I
    //   181: istore_1
    //   182: iload_1
    //   183: iconst_m1
    //   184: if_icmpne -> 190
    //   187: aload #7
    //   189: areturn
    //   190: iload_1
    //   191: iconst_1
    //   192: ishl
    //   193: istore_3
    //   194: aload #7
    //   196: astore #6
    //   198: goto -> 15
  }
  
  void enterID(String paramString, int paramInt) {
    String[] arrayOfString1;
    int[] arrayOfInt1;
    String[] arrayOfString2 = this.idNames;
    int[] arrayOfInt2 = this.idOffsets;
    if (arrayOfString2 == null) {
      i = 64;
      this.idNames = new String[64];
      this.idOffsets = new int[64];
      arrayOfInt1 = arrayOfInt2;
      arrayOfString1 = arrayOfString2;
    } else {
      int n = this.idCount;
      int m = this.idNames.length;
      i = m;
      arrayOfString1 = arrayOfString2;
      arrayOfInt1 = arrayOfInt2;
      if (n * 4 >= m * 3) {
        this.idNames = new String[m * 2];
        this.idOffsets = new int[m * 2];
        this.idCount = 0;
        i = m;
        while (true) {
          n = i - 1;
          if (n >= 0) {
            String str = arrayOfString2[n];
            i = n;
            if (str != null) {
              enterID(str, arrayOfInt2[n]);
              i = n;
            } 
            continue;
          } 
          arrayOfString1 = this.idNames;
          arrayOfInt1 = this.idOffsets;
          i = m * 2;
          // Byte code: goto -> 45
        } 
      } 
    } 
    int j = paramString.hashCode();
    int k = i - 1;
    int i = j & k;
    while (true) {
      String str = arrayOfString1[i];
      if (str == null) {
        arrayOfString1[i] = paramString;
        arrayOfInt1[i] = paramInt;
        this.idCount++;
        return;
      } 
      if (!str.equals(paramString)) {
        i = i + ((j ^ 0xFFFFFFFF) << 1 | 0x1) & k;
        continue;
      } 
      return;
    } 
  }
  
  public int getAttribute(int paramInt, String paramString1, String paramString2) {
    String str = null;
    if (paramString1 == null) {
      paramString1 = null;
    } else {
      paramString1 = paramString1.intern();
    } 
    if (paramString2 == null) {
      paramString2 = str;
      return getAttributeI(paramInt, paramString1, paramString2);
    } 
    paramString2 = paramString2.intern();
    return getAttributeI(paramInt, paramString1, paramString2);
  }
  
  public int getAttributeI(int paramInt, String paramString1, String paramString2) {
    for (paramInt = firstAttributePos(paramInt);; paramInt = nextPos(paramInt)) {
      if (paramInt == 0 || getNextKind(paramInt) != 35)
        return 0; 
      if (paramString2 == null || posLocalName(paramInt) == paramString2) {
        int i = paramInt;
        if (paramString1 != null) {
          i = paramInt;
          if (posNamespaceURI(paramInt) != paramString1)
            continue; 
        } 
        return i;
      } 
      continue;
    } 
  }
  
  public int getId() {
    if (this.id == 0) {
      int i = counter + 1;
      counter = i;
      this.id = i;
    } 
    return this.id;
  }
  
  public SeqPosition getIteratorAtPos(int paramInt) {
    return (SeqPosition)KNode.make(this, paramInt);
  }
  
  public int lookupID(String paramString) {
    String[] arrayOfString = this.idNames;
    int[] arrayOfInt = this.idOffsets;
    int i = this.idNames.length;
    int j = paramString.hashCode();
    int k = i - 1;
    for (i = j & k;; i = i + ((j ^ 0xFFFFFFFF) << 1 | 0x1) & k) {
      String str = arrayOfString[i];
      if (str == null)
        return -1; 
      if (str.equals(paramString))
        return arrayOfInt[i]; 
    } 
  }
  
  public void makeIDtableIfNeeded() {
    if (this.idNames == null) {
      this.idNames = new String[64];
      this.idOffsets = new int[64];
      int j = endPos();
      int i = 0;
      while (true) {
        int k = nextMatching(i, (ItemPredicate)ElementType.anyElement, j, true);
        if (k != 0) {
          int m = getAttributeI(k, "http://www.w3.org/XML/1998/namespace", "id");
          i = k;
          if (m != 0) {
            enterID(KNode.getNodeValue(this, m), k);
            i = k;
          } 
          continue;
        } 
        return;
      } 
    } 
  }
  
  public int nextPos(int paramInt) {
    boolean bool = false;
    if ((paramInt & 0x1) != 0);
    int i = posToDataIndex(paramInt);
    paramInt = nextNodeIndex(i, 2147483647);
    if (paramInt != i) {
      paramInt <<= 1;
      return paramInt;
    } 
    paramInt = bool;
    return (i != this.data.length) ? ((i << 1) + 3) : paramInt;
  }
  
  public int posFirstChild(int paramInt) {
    paramInt = gotoChildrenStart(posToDataIndex(paramInt));
    if (paramInt >= 0) {
      char c = this.data[paramInt];
      if (c != '' && c != '' && c != '')
        return paramInt << 1; 
    } 
    return -1;
  }
  
  public boolean posHasAttributes(int paramInt) {
    paramInt = gotoAttributesStart(posToDataIndex(paramInt));
    return (paramInt >= 0 && paramInt >= 0 && this.data[paramInt] == '');
  }
  
  public boolean posIsDefaultNamespace(int paramInt, String paramString) {
    throw new Error("posIsDefaultNamespace not implemented");
  }
  
  public String posLocalName(int paramInt) {
    Object object = getNextTypeObject(paramInt);
    return (object instanceof XName) ? ((XName)object).getLocalPart() : ((object instanceof Symbol) ? ((Symbol)object).getLocalName() : getNextTypeName(paramInt));
  }
  
  public String posLookupNamespaceURI(int paramInt, String paramString) {
    if (getNextKind(paramInt) != 33)
      throw new IllegalArgumentException("argument must be an element"); 
    Object object = getNextTypeObject(paramInt);
    return (object instanceof XName) ? ((XName)object).lookupNamespaceURI(paramString) : null;
  }
  
  public String posLookupPrefix(int paramInt, String paramString) {
    throw new Error("posLookupPrefix not implemented");
  }
  
  public String posNamespaceURI(int paramInt) {
    Object object = getNextTypeObject(paramInt);
    return (object instanceof XName) ? ((XName)object).getNamespaceURI() : ((object instanceof Symbol) ? ((Symbol)object).getNamespaceURI() : null);
  }
  
  public String posPrefix(int paramInt) {
    String str = getNextTypeName(paramInt);
    if (str != null) {
      paramInt = str.indexOf(':');
      if (paramInt >= 0)
        return str.substring(0, paramInt); 
    } 
    return null;
  }
  
  public String posTarget(int paramInt) {
    paramInt = posToDataIndex(paramInt);
    if (this.data[paramInt] != '')
      throw new ClassCastException("expected process-instruction"); 
    return (String)this.objects[getIntN(paramInt + 1)];
  }
  
  public int stableCompare(AbstractSequence paramAbstractSequence) {
    if (this == paramAbstractSequence)
      return 0; 
    int i = super.stableCompare(paramAbstractSequence);
    null = i;
    if (i == 0) {
      null = i;
      if (paramAbstractSequence instanceof NodeTree) {
        null = getId();
        i = ((NodeTree)paramAbstractSequence).getId();
        if (null < i)
          return -1; 
      } else {
        return null;
      } 
    } else {
      return null;
    } 
    return (null > i) ? 1 : 0;
  }
  
  public String toString() {
    CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
    consume((Consumer)new XMLPrinter((Writer)charArrayOutPort));
    charArrayOutPort.close();
    return charArrayOutPort.toString();
  }
  
  public Object typedValue(int paramInt) {
    StringBuffer stringBuffer = new StringBuffer();
    stringValue(posToDataIndex(paramInt), stringBuffer);
    String str = stringBuffer.toString();
    paramInt = getNextKind(paramInt);
    return (paramInt == 37 || paramInt == 36) ? str : new UntypedAtomic(str);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xml/NodeTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
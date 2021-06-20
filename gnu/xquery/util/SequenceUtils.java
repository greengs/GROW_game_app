package gnu.xquery.util;

import gnu.kawa.xml.KAttr;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.NodeType;
import gnu.lists.Consumer;
import gnu.lists.ItemPredicate;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.xml.NodeTree;

public class SequenceUtils {
  public static final NodeType textOrElement = new NodeType("element-or-text", 3);
  
  static Object coerceToZeroOrOne(Object paramObject, String paramString, int paramInt) {
    if (isZeroOrOne(paramObject))
      return paramObject; 
    throw new WrongType(paramString, paramInt, paramObject, "xs:item()?");
  }
  
  public static boolean deepEqual(NodeTree paramNodeTree1, int paramInt1, NodeTree paramNodeTree2, int paramInt2, NamedCollator paramNamedCollator) {
    int i = paramNodeTree1.getNextKind(paramInt1);
    int j = paramNodeTree2.getNextKind(paramInt2);
    switch (i) {
      default:
        return (i != j) ? false : KNode.getNodeValue(paramNodeTree1, paramInt1).equals(KNode.getNodeValue(paramNodeTree2, paramInt2));
      case 33:
        if (i != j)
          return false; 
        if (paramNodeTree1.posLocalName(paramInt1) != paramNodeTree2.posLocalName(paramInt2))
          return false; 
        if (paramNodeTree1.posNamespaceURI(paramInt1) != paramNodeTree2.posNamespaceURI(paramInt2))
          return false; 
        i = paramNodeTree1.firstAttributePos(paramInt1);
        j = 0;
        while (true) {
          if (i == 0 || paramNodeTree1.getNextKind(i) != 35) {
            if (j != paramNodeTree2.getAttributeCount(paramInt2))
              return false; 
            break;
          } 
          j++;
          String str = paramNodeTree1.posLocalName(i);
          int k = paramNodeTree2.getAttributeI(paramInt2, paramNodeTree1.posNamespaceURI(i), str);
          if (k == 0)
            return false; 
          if (!deepEqualItems(KNode.getNodeValue(paramNodeTree1, i), KNode.getNodeValue(paramNodeTree2, k), paramNamedCollator))
            return false; 
          i = paramNodeTree1.nextPos(i);
        } 
      case 34:
        return deepEqualChildren(paramNodeTree1, paramInt1, paramNodeTree2, paramInt2, paramNamedCollator);
      case 35:
        return (paramNodeTree1.posLocalName(paramInt1) != paramNodeTree2.posLocalName(paramInt2) || paramNodeTree1.posNamespaceURI(paramInt1) != paramNodeTree2.posNamespaceURI(paramInt2)) ? false : deepEqualItems(KAttr.getObjectValue(paramNodeTree1, paramInt1), KAttr.getObjectValue(paramNodeTree2, paramInt2), paramNamedCollator);
      case 37:
        break;
    } 
    return !paramNodeTree1.posTarget(paramInt1).equals(paramNodeTree2.posTarget(paramInt2)) ? false : KNode.getNodeValue(paramNodeTree1, paramInt1).equals(KNode.getNodeValue(paramNodeTree2, paramInt2));
  }
  
  public static boolean deepEqual(Object paramObject1, Object paramObject2, NamedCollator paramNamedCollator) {
    Values values1;
    Values values2;
    if (paramObject1 == paramObject2)
      return true; 
    if (paramObject1 == null || paramObject1 == Values.empty)
      return (paramObject2 == null || paramObject2 == Values.empty); 
    if (paramObject2 == null || paramObject2 == Values.empty)
      return false; 
    int j = 1;
    int i = 1;
    boolean bool1 = paramObject1 instanceof Values;
    boolean bool2 = paramObject2 instanceof Values;
    if (bool1) {
      values1 = (Values)paramObject1;
    } else {
      values1 = null;
    } 
    if (bool2) {
      values2 = (Values)paramObject2;
    } else {
      values2 = null;
    } 
    boolean bool = true;
    while (true) {
      Object object1;
      Object object2;
      int k = j;
      if (bool1) {
        if (bool)
          j = values1.startPos(); 
        k = values1.nextPos(j);
      } 
      int m = i;
      if (bool2) {
        if (bool)
          i = values2.startPos(); 
        m = values2.nextPos(i);
      } 
      if (k == 0 || m == 0)
        return (k == m); 
      if (bool1) {
        object1 = values1.getPosPrevious(k);
      } else {
        object1 = paramObject1;
      } 
      if (bool2) {
        object2 = values2.getPosPrevious(m);
      } else {
        object2 = paramObject2;
      } 
      if (!(object1 instanceof KNode) && !(object2 instanceof KNode)) {
        try {
          boolean bool3 = deepEqualItems(paramObject1, paramObject2, paramNamedCollator);
          if (!bool3)
            return false; 
        } catch (Throwable throwable) {
          return false;
        } 
      } else if (object1 instanceof KNode && object2 instanceof KNode) {
        object1 = object1;
        object2 = object2;
        if (!deepEqual((NodeTree)((KNode)object1).sequence, ((KNode)object1).ipos, (NodeTree)((KNode)object2).sequence, ((KNode)object2).ipos, paramNamedCollator))
          return false; 
      } else {
        return false;
      } 
      j = k;
      i = m;
      if (bool) {
        boolean bool3 = false;
        if (!bool1)
          k = 0; 
        bool = bool3;
        j = k;
        i = m;
        if (!bool2) {
          i = 0;
          bool = bool3;
          j = k;
        } 
      } 
    } 
  }
  
  public static boolean deepEqualChildren(NodeTree paramNodeTree1, int paramInt1, NodeTree paramNodeTree2, int paramInt2, NamedCollator paramNamedCollator) {
    boolean bool = false;
    NodeType nodeType = textOrElement;
    int i = paramNodeTree1.firstChildPos(paramInt1, (ItemPredicate)nodeType);
    paramInt1 = paramNodeTree2.firstChildPos(paramInt2, (ItemPredicate)nodeType);
    paramInt2 = i;
    while (true) {
      if (paramInt2 == 0 || paramInt1 == 0) {
        boolean bool2 = bool;
        if (paramInt2 == paramInt1)
          bool2 = true; 
        return bool2;
      } 
      boolean bool1 = bool;
      if (deepEqual(paramNodeTree1, paramInt2, paramNodeTree2, paramInt1, paramNamedCollator)) {
        paramInt2 = paramNodeTree1.nextMatching(paramInt2, (ItemPredicate)nodeType, -1, false);
        paramInt1 = paramNodeTree2.nextMatching(paramInt1, (ItemPredicate)nodeType, -1, false);
        continue;
      } 
      return bool1;
    } 
  }
  
  public static boolean deepEqualItems(Object paramObject1, Object paramObject2, NamedCollator paramNamedCollator) {
    return (NumberValue.isNaN(paramObject1) && NumberValue.isNaN(paramObject2)) ? true : Compare.atomicCompare(8, paramObject1, paramObject2, paramNamedCollator);
  }
  
  public static Object exactlyOne(Object paramObject) {
    if (paramObject instanceof Values)
      throw new IllegalArgumentException(); 
    return paramObject;
  }
  
  public static boolean exists(Object paramObject) {
    return (!(paramObject instanceof Values) || !((Values)paramObject).isEmpty());
  }
  
  public static void indexOf$X(Object paramObject1, Object paramObject2, NamedCollator paramNamedCollator, CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    if (paramObject1 instanceof Values) {
      paramObject1 = paramObject1;
      int j = paramObject1.startPos();
      int i = 1;
      while (true) {
        j = paramObject1.nextPos(j);
        if (j != 0) {
          if (Compare.apply(72, paramObject1.getPosPrevious(j), paramObject2, paramNamedCollator))
            consumer.writeInt(i); 
          i++;
          continue;
        } 
        break;
      } 
    } else if (Compare.apply(72, paramObject1, paramObject2, paramNamedCollator)) {
      consumer.writeInt(1);
    } 
  }
  
  public static void insertBefore$X(Object paramObject1, long paramLong, Object paramObject2, CallContext paramCallContext) {
    // Byte code:
    //   0: aload #4
    //   2: getfield consumer : Lgnu/lists/Consumer;
    //   5: astore #4
    //   7: iconst_0
    //   8: istore #6
    //   10: lload_1
    //   11: lstore #10
    //   13: lload_1
    //   14: lconst_0
    //   15: lcmp
    //   16: ifgt -> 22
    //   19: lconst_1
    //   20: lstore #10
    //   22: aload_0
    //   23: instanceof gnu/mapping/Values
    //   26: ifeq -> 111
    //   29: aload_0
    //   30: checkcast gnu/mapping/Values
    //   33: astore_0
    //   34: iconst_0
    //   35: istore #5
    //   37: lconst_0
    //   38: lstore_1
    //   39: aload_0
    //   40: iload #5
    //   42: invokevirtual nextPos : (I)I
    //   45: istore #7
    //   47: iload #7
    //   49: ifne -> 60
    //   52: lload_1
    //   53: lstore #8
    //   55: iload #6
    //   57: ifeq -> 76
    //   60: lload_1
    //   61: lconst_1
    //   62: ladd
    //   63: lstore #8
    //   65: lload #8
    //   67: lstore_1
    //   68: lload #8
    //   70: lload #10
    //   72: lcmp
    //   73: ifne -> 88
    //   76: aload_3
    //   77: aload #4
    //   79: invokestatic writeValues : (Ljava/lang/Object;Lgnu/lists/Consumer;)V
    //   82: iconst_1
    //   83: istore #6
    //   85: lload #8
    //   87: lstore_1
    //   88: iload #7
    //   90: ifne -> 94
    //   93: return
    //   94: aload_0
    //   95: iload #5
    //   97: iload #7
    //   99: aload #4
    //   101: invokevirtual consumePosRange : (IILgnu/lists/Consumer;)V
    //   104: iload #7
    //   106: istore #5
    //   108: goto -> 39
    //   111: lload #10
    //   113: lconst_1
    //   114: lcmp
    //   115: ifgt -> 124
    //   118: aload_3
    //   119: aload #4
    //   121: invokestatic writeValues : (Ljava/lang/Object;Lgnu/lists/Consumer;)V
    //   124: aload #4
    //   126: aload_0
    //   127: invokeinterface writeObject : (Ljava/lang/Object;)V
    //   132: lload #10
    //   134: lconst_1
    //   135: lcmp
    //   136: ifle -> 93
    //   139: aload_3
    //   140: aload #4
    //   142: invokestatic writeValues : (Ljava/lang/Object;Lgnu/lists/Consumer;)V
    //   145: return
  }
  
  public static boolean isEmptySequence(Object paramObject) {
    return (paramObject instanceof Values && ((Values)paramObject).isEmpty());
  }
  
  public static boolean isZeroOrOne(Object paramObject) {
    return (!(paramObject instanceof Values) || ((Values)paramObject).isEmpty());
  }
  
  public static Object oneOrMore(Object paramObject) {
    if (paramObject instanceof Values && ((Values)paramObject).isEmpty())
      throw new IllegalArgumentException(); 
    return paramObject;
  }
  
  public static void remove$X(Object paramObject, long paramLong, CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    if (paramObject instanceof Values) {
      paramObject = paramObject;
      int i = 0;
      long l = 0L;
      while (true) {
        int j = paramObject.nextPos(i);
        if (j != 0) {
          l++;
          if (l != paramLong)
            paramObject.consumePosRange(i, j, consumer); 
          i = j;
          continue;
        } 
        return;
      } 
    } 
    if (paramLong != 1L) {
      consumer.writeObject(paramObject);
      return;
    } 
  }
  
  public static void reverse$X(Object paramObject, CallContext paramCallContext) {
    // Byte code:
    //   0: aload_1
    //   1: getfield consumer : Lgnu/lists/Consumer;
    //   4: astore #5
    //   6: aload_0
    //   7: instanceof gnu/mapping/Values
    //   10: ifne -> 22
    //   13: aload #5
    //   15: aload_0
    //   16: invokeinterface writeObject : (Ljava/lang/Object;)V
    //   21: return
    //   22: aload_0
    //   23: checkcast gnu/mapping/Values
    //   26: astore #6
    //   28: iconst_0
    //   29: istore_2
    //   30: bipush #100
    //   32: newarray int
    //   34: astore_0
    //   35: iconst_0
    //   36: istore_3
    //   37: aload_0
    //   38: astore_1
    //   39: iload_3
    //   40: aload_0
    //   41: arraylength
    //   42: if_icmplt -> 59
    //   45: iload_3
    //   46: iconst_2
    //   47: imul
    //   48: newarray int
    //   50: astore_1
    //   51: aload_0
    //   52: iconst_0
    //   53: aload_1
    //   54: iconst_0
    //   55: iload_3
    //   56: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   59: iload_3
    //   60: iconst_1
    //   61: iadd
    //   62: istore #4
    //   64: aload_1
    //   65: iload_3
    //   66: iload_2
    //   67: iastore
    //   68: aload #6
    //   70: iload_2
    //   71: invokevirtual nextPos : (I)I
    //   74: istore_2
    //   75: iload_2
    //   76: ifne -> 110
    //   79: iload #4
    //   81: iconst_1
    //   82: isub
    //   83: istore_2
    //   84: iload_2
    //   85: iconst_1
    //   86: isub
    //   87: istore_2
    //   88: iload_2
    //   89: iflt -> 21
    //   92: aload #6
    //   94: aload_1
    //   95: iload_2
    //   96: iaload
    //   97: aload_1
    //   98: iload_2
    //   99: iconst_1
    //   100: iadd
    //   101: iaload
    //   102: aload #5
    //   104: invokevirtual consumePosRange : (IILgnu/lists/Consumer;)V
    //   107: goto -> 84
    //   110: iload #4
    //   112: istore_3
    //   113: aload_1
    //   114: astore_0
    //   115: goto -> 37
  }
  
  public static Object zeroOrOne(Object paramObject) {
    return coerceToZeroOrOne(paramObject, "zero-or-one", 1);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/SequenceUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
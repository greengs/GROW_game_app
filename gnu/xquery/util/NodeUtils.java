package gnu.xquery.util;

import gnu.kawa.xml.Document;
import gnu.kawa.xml.KDocument;
import gnu.kawa.xml.KElement;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.Nodes;
import gnu.kawa.xml.SortedNodes;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Path;
import gnu.xml.NamespaceBinding;
import gnu.xml.NodeTree;
import gnu.xml.TextUtils;
import gnu.xml.XName;
import java.util.Stack;

public class NodeUtils {
  static String collectionNamespace = "http://gnu.org/kawa/cached-collections";
  
  public static final Symbol collectionResolverSymbol = Symbol.make("http://www.w3.org/2005/xquery-local-functions", "collection-resolver", "qexo");
  
  public static boolean availableCached(Object paramObject1, Object paramObject2) throws Throwable {
    paramObject1 = resolve(paramObject1, paramObject2, "doc-available");
    if (paramObject1 == null)
      return false; 
    try {
      Document.parseCached(paramObject1);
      return true;
    } catch (Throwable throwable) {
      return false;
    } 
  }
  
  public static Object baseUri(Object paramObject) {
    if (paramObject != null && paramObject != Values.empty) {
      if (!(paramObject instanceof KNode))
        throw new WrongType("base-uri", 1, paramObject, "node()?"); 
      Path path = ((KNode)paramObject).baseURI();
      paramObject = path;
      if (path == null)
        return Values.empty; 
    } 
    return paramObject;
  }
  
  public static Object collection(Object paramObject1, Object paramObject2) throws Throwable {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: ldc 'collection'
    //   4: invokestatic resolve : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   7: astore_3
    //   8: invokestatic getCurrent : ()Lgnu/mapping/Environment;
    //   11: astore #4
    //   13: getstatic gnu/xquery/util/NodeUtils.collectionResolverSymbol : Lgnu/mapping/Symbol;
    //   16: astore #5
    //   18: aload #4
    //   20: aload #5
    //   22: aconst_null
    //   23: aconst_null
    //   24: invokevirtual get : (Lgnu/mapping/Symbol;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   27: astore_1
    //   28: aload_1
    //   29: astore_0
    //   30: aload_1
    //   31: ifnonnull -> 55
    //   34: aload #4
    //   36: aload #5
    //   38: invokevirtual getLocalName : ()Ljava/lang/String;
    //   41: aload #5
    //   43: invokevirtual getPrefix : ()Ljava/lang/String;
    //   46: invokestatic makeWithUnknownNamespace : (Ljava/lang/String;Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   49: aconst_null
    //   50: aconst_null
    //   51: invokevirtual get : (Lgnu/mapping/Symbol;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   54: astore_0
    //   55: aload_0
    //   56: ifnonnull -> 64
    //   59: aload_3
    //   60: invokestatic getSavedCollection : (Ljava/lang/Object;)Ljava/lang/Object;
    //   63: areturn
    //   64: aload_0
    //   65: instanceof java/lang/String
    //   68: ifne -> 80
    //   71: aload_0
    //   72: astore_1
    //   73: aload_0
    //   74: instanceof gnu/kawa/xml/UntypedAtomic
    //   77: ifeq -> 248
    //   80: aload_0
    //   81: invokevirtual toString : ()Ljava/lang/String;
    //   84: astore #5
    //   86: aload #5
    //   88: bipush #58
    //   90: invokevirtual indexOf : (I)I
    //   93: istore_2
    //   94: aload_0
    //   95: astore_1
    //   96: iload_2
    //   97: ifle -> 248
    //   100: aload #5
    //   102: iconst_0
    //   103: iload_2
    //   104: invokevirtual substring : (II)Ljava/lang/String;
    //   107: astore #4
    //   109: aload #5
    //   111: iload_2
    //   112: iconst_1
    //   113: iadd
    //   114: invokevirtual substring : (I)Ljava/lang/String;
    //   117: astore #5
    //   119: aload #4
    //   121: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   124: astore_0
    //   125: aload_0
    //   126: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   129: checkcast gnu/bytecode/ClassType
    //   132: aload #5
    //   134: iconst_0
    //   135: getstatic gnu/xquery/lang/XQuery.instance : Lgnu/xquery/lang/XQuery;
    //   138: invokestatic apply : (Lgnu/bytecode/ObjectType;Ljava/lang/String;CLgnu/expr/Language;)Lgnu/mapping/MethodProc;
    //   141: astore_0
    //   142: aload_0
    //   143: astore_1
    //   144: aload_0
    //   145: ifnonnull -> 248
    //   148: new java/lang/RuntimeException
    //   151: dup
    //   152: new java/lang/StringBuilder
    //   155: dup
    //   156: invokespecial <init> : ()V
    //   159: ldc 'invalid collection-resolver: no method '
    //   161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: aload #5
    //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: ldc ' in '
    //   171: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: aload #4
    //   176: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: invokevirtual toString : ()Ljava/lang/String;
    //   182: invokespecial <init> : (Ljava/lang/String;)V
    //   185: athrow
    //   186: astore_0
    //   187: new java/lang/RuntimeException
    //   190: dup
    //   191: new java/lang/StringBuilder
    //   194: dup
    //   195: invokespecial <init> : ()V
    //   198: ldc 'invalid collection-resolver: class '
    //   200: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: aload #4
    //   205: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: ldc ' not found'
    //   210: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: invokevirtual toString : ()Ljava/lang/String;
    //   216: invokespecial <init> : (Ljava/lang/String;)V
    //   219: athrow
    //   220: astore_0
    //   221: new java/lang/RuntimeException
    //   224: dup
    //   225: new java/lang/StringBuilder
    //   228: dup
    //   229: invokespecial <init> : ()V
    //   232: ldc 'invalid collection-resolver: '
    //   234: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   237: aload_0
    //   238: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   241: invokevirtual toString : ()Ljava/lang/String;
    //   244: invokespecial <init> : (Ljava/lang/String;)V
    //   247: athrow
    //   248: aload_1
    //   249: instanceof gnu/mapping/Procedure
    //   252: ifne -> 282
    //   255: new java/lang/RuntimeException
    //   258: dup
    //   259: new java/lang/StringBuilder
    //   262: dup
    //   263: invokespecial <init> : ()V
    //   266: ldc 'invalid collection-resolver: '
    //   268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: aload_1
    //   272: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   275: invokevirtual toString : ()Ljava/lang/String;
    //   278: invokespecial <init> : (Ljava/lang/String;)V
    //   281: athrow
    //   282: aload_1
    //   283: checkcast gnu/mapping/Procedure
    //   286: aload_3
    //   287: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   290: areturn
    // Exception table:
    //   from	to	target	type
    //   119	125	186	java/lang/ClassNotFoundException
    //   119	125	220	java/lang/Throwable
  }
  
  public static void data$X(Object paramObject, CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    if (paramObject instanceof Values) {
      paramObject = paramObject;
      int i = paramObject.startPos();
      while (true) {
        i = paramObject.nextPos(i);
        if (i != 0) {
          consumer.writeObject(KNode.atomicValue(paramObject.getPosPrevious(i)));
          continue;
        } 
        break;
      } 
    } else {
      consumer.writeObject(KNode.atomicValue(paramObject));
    } 
  }
  
  public static Object docCached(Object paramObject1, Object paramObject2) throws Throwable {
    paramObject1 = resolve(paramObject1, paramObject2, "doc");
    return (paramObject1 == null) ? Values.empty : Document.parseCached(paramObject1);
  }
  
  public static Object documentUri(Object paramObject) {
    if (paramObject != null && paramObject != Values.empty) {
      if (!(paramObject instanceof KNode))
        throw new WrongType("xs:document-uri", 1, paramObject, "node()?"); 
      paramObject = paramObject;
      Object object = ((NodeTree)((KNode)paramObject).sequence).documentUriOfPos(((KNode)paramObject).ipos);
      paramObject = object;
      if (object == null)
        return Values.empty; 
    } 
    return paramObject;
  }
  
  static Object getIDs(Object paramObject1, Object paramObject2) {
    Object object = paramObject1;
    if (paramObject1 instanceof KNode)
      object = KNode.atomicValue(paramObject1); 
    if (object instanceof Values) {
      object = ((Values)object).getValues();
      int i = object.length;
      while (true) {
        i--;
        paramObject1 = paramObject2;
        if (i >= 0) {
          paramObject2 = getIDs(object[i], paramObject2);
          continue;
        } 
        break;
      } 
    } else {
      String str = StringUtils.coerceToString(object, "fn:id", 1, "");
      int j = str.length();
      int i = 0;
      paramObject1 = paramObject2;
      label46: while (i < j) {
        int k = i + 1;
        char c = str.charAt(i);
        if (Character.isWhitespace(c)) {
          i = k;
          continue;
        } 
        if (XName.isNameStart(c)) {
          i = k - 1;
        } else {
          i = j;
        } 
        while (true) {
          if (k < j) {
            c = str.charAt(k);
            if (!Character.isWhitespace(c)) {
              int m = k + 1;
              k = m;
              if (i < j) {
                k = m;
                if (!XName.isNamePart(c)) {
                  i = j;
                  k = m;
                } 
              } 
              continue;
            } 
          } 
          paramObject2 = paramObject1;
          if (i < j) {
            object = str.substring(i, k);
            if (paramObject1 == null) {
              paramObject2 = object;
            } else {
              if (paramObject1 instanceof Stack) {
                paramObject2 = paramObject1;
              } else {
                paramObject2 = new Stack();
                paramObject2.push(paramObject1);
                paramObject1 = paramObject2;
              } 
              paramObject2.push(object);
              paramObject2 = paramObject1;
            } 
          } 
          i = k + 1;
          paramObject1 = paramObject2;
          continue label46;
        } 
      } 
    } 
    return paramObject1;
  }
  
  public static String getLang(KNode paramKNode) {
    NodeTree nodeTree = (NodeTree)paramKNode.sequence;
    int i = nodeTree.ancestorAttribute(paramKNode.ipos, "http://www.w3.org/XML/1998/namespace", "lang");
    return (i == 0) ? null : KNode.getNodeValue(nodeTree, i);
  }
  
  public static Object getSavedCollection(Object paramObject) {
    return getSavedCollection(paramObject, Environment.getCurrent());
  }
  
  public static Object getSavedCollection(Object paramObject, Environment paramEnvironment) {
    Object object = paramObject;
    if (paramObject == null)
      object = "#default"; 
    paramObject = paramEnvironment.get(Symbol.make(collectionNamespace, object.toString()), null, null);
    if (paramObject == null)
      throw new RuntimeException("collection '" + object + "' not found"); 
    return paramObject;
  }
  
  public static void id$X(Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    KNode kNode = (KNode)paramObject2;
    paramObject2 = kNode.sequence;
    KDocument kDocument = (KDocument)Nodes.root((NodeTree)paramObject2, kNode.ipos);
    Consumer consumer = paramCallContext.consumer;
    paramObject1 = getIDs(paramObject1, null);
    if (paramObject1 != null) {
      paramObject2.makeIDtableIfNeeded();
      if (consumer instanceof PositionConsumer && (paramObject1 instanceof String || consumer instanceof SortedNodes)) {
        idScan(paramObject1, (NodeTree)paramObject2, (PositionConsumer)consumer);
        return;
      } 
      if (paramObject1 instanceof String) {
        int i = paramObject2.lookupID((String)paramObject1);
        if (i != -1) {
          consumer.writeObject(KNode.make((NodeTree)paramObject2, i));
          return;
        } 
        return;
      } 
      SortedNodes sortedNodes = new SortedNodes();
      idScan(paramObject1, (NodeTree)paramObject2, (PositionConsumer)sortedNodes);
      Values.writeValues(sortedNodes, consumer);
      return;
    } 
  }
  
  private static void idScan(Object paramObject, NodeTree paramNodeTree, PositionConsumer paramPositionConsumer) {
    if (paramObject instanceof String) {
      int i = paramNodeTree.lookupID((String)paramObject);
      if (i != -1)
        paramPositionConsumer.writePosition((AbstractSequence)paramNodeTree, i); 
      return;
    } 
    if (paramObject instanceof Stack) {
      paramObject = paramObject;
      int j = paramObject.size();
      int i = 0;
      while (true) {
        if (i < j) {
          idScan(paramObject.elementAt(i), paramNodeTree, paramPositionConsumer);
          i++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public static Object idref(Object paramObject1, Object paramObject2) {
    paramObject1 = paramObject2;
    paramObject1 = Nodes.root((NodeTree)((KNode)paramObject1).sequence, paramObject1.getPos());
    return Values.empty;
  }
  
  public static void inScopePrefixes$X(Object paramObject, CallContext paramCallContext) {
    paramObject = ((KElement)paramObject).getNodeNameObject();
    if (paramObject instanceof XName) {
      prefixesFromNodetype((XName)paramObject, paramCallContext.consumer);
      return;
    } 
    paramCallContext.consumer.writeObject("xml");
  }
  
  public static boolean lang(Object paramObject1, Object paramObject2) {
    if (paramObject1 == null || paramObject1 == Values.empty) {
      paramObject1 = "";
    } else {
      paramObject1 = TextUtils.stringValue(paramObject1);
    } 
    String str = getLang((KNode)paramObject2);
    if (str == null)
      return false; 
    int i = str.length();
    int j = paramObject1.length();
    paramObject2 = str;
    if (i > j) {
      paramObject2 = str;
      if (str.charAt(j) == '-')
        paramObject2 = str.substring(0, j); 
    } 
    return paramObject2.equalsIgnoreCase((String)paramObject1);
  }
  
  public static String localName(Object paramObject) {
    if (paramObject == Values.empty || paramObject == null)
      return ""; 
    if (!(paramObject instanceof KNode))
      throw new WrongType("local-name", 1, paramObject, "node()?"); 
    paramObject = ((KNode)paramObject).getNodeNameObject();
    return (paramObject == null || paramObject == Values.empty) ? "" : ((paramObject instanceof Symbol) ? ((Symbol)paramObject).getName() : paramObject.toString());
  }
  
  public static String name(Object paramObject) {
    if (paramObject == Values.empty || paramObject == null)
      return ""; 
    paramObject = ((KNode)paramObject).getNodeNameObject();
    return (paramObject == null || paramObject == Values.empty) ? "" : paramObject.toString();
  }
  
  public static Object namespaceURI(Object paramObject) {
    if (paramObject != Values.empty && paramObject != null) {
      if (!(paramObject instanceof KNode))
        throw new WrongType("namespace-uri", 1, paramObject, "node()?"); 
      paramObject = ((KNode)paramObject).getNodeNameObject();
      if (paramObject instanceof Symbol)
        return QNameUtils.namespaceURIFromQName(paramObject); 
    } 
    return "";
  }
  
  public static Object nilled(Object paramObject) {
    if (paramObject == null || paramObject == Values.empty)
      return paramObject; 
    if (!(paramObject instanceof KNode))
      throw new WrongType("nilled", 1, paramObject, "node()?"); 
    return !(paramObject instanceof KElement) ? Values.empty : Boolean.FALSE;
  }
  
  public static Object nodeName(Object paramObject) {
    if (paramObject != Values.empty && paramObject != null) {
      if (!(paramObject instanceof KNode))
        throw new WrongType("node-name", 1, paramObject, "node()?"); 
      Symbol symbol = ((KNode)paramObject).getNodeSymbol();
      paramObject = symbol;
      if (symbol == null)
        return Values.empty; 
    } 
    return paramObject;
  }
  
  public static void prefixesFromNodetype(XName paramXName, Consumer paramConsumer) {
    NamespaceBinding namespaceBinding1 = paramXName.getNamespaceNodes();
    NamespaceBinding namespaceBinding2 = namespaceBinding1;
    label19: while (namespaceBinding2 != null) {
      if (namespaceBinding2.getUri() == null)
        continue; 
      String str = namespaceBinding2.getPrefix();
      NamespaceBinding namespaceBinding = namespaceBinding1;
      while (true) {
        String str1;
        if (namespaceBinding == namespaceBinding2) {
          str1 = str;
          if (str == null)
            str1 = ""; 
          paramConsumer.writeObject(str1);
        } else if (str1.getPrefix() != str) {
          NamespaceBinding namespaceBinding3 = str1.getNext();
          continue;
        } 
        namespaceBinding2 = namespaceBinding2.getNext();
        continue label19;
      } 
    } 
  }
  
  static Object resolve(Object paramObject1, Object paramObject2, String paramString) throws Throwable {
    paramObject2 = paramObject1;
    if (!(paramObject1 instanceof java.io.File)) {
      paramObject2 = paramObject1;
      if (!(paramObject1 instanceof Path)) {
        paramObject2 = paramObject1;
        if (!(paramObject1 instanceof java.net.URI)) {
          paramObject2 = paramObject1;
          if (!(paramObject1 instanceof java.net.URL))
            paramObject2 = StringUtils.coerceToString(paramObject1, paramString, 1, null); 
        } 
      } 
    } 
    return (paramObject2 == Values.empty || paramObject2 == null) ? null : Path.currentPath().resolve(Path.valueOf(paramObject2));
  }
  
  public static Object root(Object paramObject) {
    if (paramObject == null || paramObject == Values.empty)
      return paramObject; 
    if (!(paramObject instanceof KNode))
      throw new WrongType("root", 1, paramObject, "node()?"); 
    paramObject = paramObject;
    return Nodes.root((NodeTree)((KNode)paramObject).sequence, paramObject.getPos());
  }
  
  public static KDocument rootDocument(Object paramObject) {
    if (!(paramObject instanceof KNode))
      throw new WrongType("root-document", 1, paramObject, "node()?"); 
    KNode kNode = (KNode)paramObject;
    kNode = Nodes.root((NodeTree)kNode.sequence, kNode.getPos());
    if (!(kNode instanceof KDocument))
      throw new WrongType("root-document", 1, paramObject, "document()"); 
    return (KDocument)kNode;
  }
  
  public static void setSavedCollection(Object paramObject1, Object paramObject2) {
    setSavedCollection(paramObject1, paramObject2, Environment.getCurrent());
  }
  
  public static void setSavedCollection(Object paramObject1, Object paramObject2, Environment paramEnvironment) {
    Object object = paramObject1;
    if (paramObject1 == null)
      object = "#default"; 
    paramEnvironment.put(Symbol.make(collectionNamespace, object.toString()), null, paramObject2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/NodeUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
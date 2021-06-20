package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.lists.XConsumer;
import gnu.mapping.ThreadLocation;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.xml.NodeTree;
import gnu.xml.XMLParser;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class Document {
  private static HashMap cache;
  
  private static ThreadLocation docMapLocation;
  
  public static final Document document = new Document();
  
  static {
    docMapLocation = new ThreadLocation("document-map");
    cache = new HashMap<Object, Object>();
  }
  
  public static void clearLocalCache() {
    docMapLocation.getLocation().set(null);
  }
  
  public static void clearSoftCache() {
    cache = new HashMap<Object, Object>();
  }
  
  public static KDocument parse(Object paramObject) throws Throwable {
    NodeTree nodeTree = new NodeTree();
    parse(paramObject, (Consumer)nodeTree);
    return new KDocument(nodeTree, 10);
  }
  
  public static void parse(Object paramObject, Consumer paramConsumer) throws Throwable {
    SourceMessages sourceMessages = new SourceMessages();
    if (paramConsumer instanceof XConsumer)
      ((XConsumer)paramConsumer).beginEntity(paramObject); 
    XMLParser.parse(paramObject, sourceMessages, paramConsumer);
    if (sourceMessages.seenErrors())
      throw new SyntaxException("document function read invalid XML", sourceMessages); 
    if (paramConsumer instanceof XConsumer)
      ((XConsumer)paramConsumer).endEntity(); 
  }
  
  public static KDocument parseCached(Path paramPath) throws Throwable {
    // Byte code:
    //   0: ldc gnu/kawa/xml/Document
    //   2: monitorenter
    //   3: getstatic gnu/kawa/xml/Document$DocReference.queue : Ljava/lang/ref/ReferenceQueue;
    //   6: invokevirtual poll : ()Ljava/lang/ref/Reference;
    //   9: checkcast gnu/kawa/xml/Document$DocReference
    //   12: astore_1
    //   13: aload_1
    //   14: ifnonnull -> 72
    //   17: getstatic gnu/kawa/xml/Document.docMapLocation : Lgnu/mapping/ThreadLocation;
    //   20: invokevirtual getLocation : ()Lgnu/mapping/NamedLocation;
    //   23: astore_3
    //   24: aload_3
    //   25: aconst_null
    //   26: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   29: checkcast java/util/Hashtable
    //   32: astore_2
    //   33: aload_2
    //   34: astore_1
    //   35: aload_2
    //   36: ifnonnull -> 52
    //   39: new java/util/Hashtable
    //   42: dup
    //   43: invokespecial <init> : ()V
    //   46: astore_1
    //   47: aload_3
    //   48: aload_1
    //   49: invokevirtual set : (Ljava/lang/Object;)V
    //   52: aload_1
    //   53: aload_0
    //   54: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   57: checkcast gnu/kawa/xml/KDocument
    //   60: astore_2
    //   61: aload_2
    //   62: ifnull -> 92
    //   65: aload_2
    //   66: astore_0
    //   67: ldc gnu/kawa/xml/Document
    //   69: monitorexit
    //   70: aload_0
    //   71: areturn
    //   72: getstatic gnu/kawa/xml/Document.cache : Ljava/util/HashMap;
    //   75: aload_1
    //   76: getfield key : Lgnu/text/Path;
    //   79: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   82: pop
    //   83: goto -> 3
    //   86: astore_0
    //   87: ldc gnu/kawa/xml/Document
    //   89: monitorexit
    //   90: aload_0
    //   91: athrow
    //   92: getstatic gnu/kawa/xml/Document.cache : Ljava/util/HashMap;
    //   95: aload_0
    //   96: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   99: checkcast gnu/kawa/xml/Document$DocReference
    //   102: astore_2
    //   103: aload_2
    //   104: ifnull -> 127
    //   107: aload_2
    //   108: invokevirtual get : ()Ljava/lang/Object;
    //   111: checkcast gnu/kawa/xml/KDocument
    //   114: astore_2
    //   115: aload_2
    //   116: ifnonnull -> 161
    //   119: getstatic gnu/kawa/xml/Document.cache : Ljava/util/HashMap;
    //   122: aload_0
    //   123: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   126: pop
    //   127: aload_0
    //   128: invokestatic parse : (Ljava/lang/Object;)Lgnu/kawa/xml/KDocument;
    //   131: astore_2
    //   132: aload_1
    //   133: aload_0
    //   134: aload_2
    //   135: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   138: pop
    //   139: getstatic gnu/kawa/xml/Document.cache : Ljava/util/HashMap;
    //   142: aload_0
    //   143: new gnu/kawa/xml/Document$DocReference
    //   146: dup
    //   147: aload_0
    //   148: aload_2
    //   149: invokespecial <init> : (Lgnu/text/Path;Lgnu/kawa/xml/KDocument;)V
    //   152: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   155: pop
    //   156: aload_2
    //   157: astore_0
    //   158: goto -> 67
    //   161: aload_1
    //   162: aload_0
    //   163: aload_2
    //   164: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   167: pop
    //   168: aload_2
    //   169: astore_0
    //   170: goto -> 67
    // Exception table:
    //   from	to	target	type
    //   3	13	86	finally
    //   17	33	86	finally
    //   39	52	86	finally
    //   52	61	86	finally
    //   72	83	86	finally
    //   92	103	86	finally
    //   107	115	86	finally
    //   119	127	86	finally
    //   127	156	86	finally
    //   161	168	86	finally
  }
  
  public static KDocument parseCached(Object paramObject) throws Throwable {
    return parseCached(Path.valueOf(paramObject));
  }
  
  private static class DocReference extends SoftReference {
    static ReferenceQueue queue = new ReferenceQueue();
    
    Path key;
    
    public DocReference(Path param1Path, KDocument param1KDocument) {
      super((T)param1KDocument, queue);
      this.key = param1Path;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/Document.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
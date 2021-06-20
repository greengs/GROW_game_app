package gnu.xquery.util;

import gnu.mapping.OutPort;

public class Debug {
  public static String traceFilename;
  
  public static OutPort tracePort;
  
  public static String tracePrefix = "XQuery-trace: ";
  
  public static boolean traceShouldAppend;
  
  public static boolean traceShouldFlush;
  
  static {
    tracePort = null;
    traceFilename = "XQuery-trace.log";
    traceShouldFlush = true;
    traceShouldAppend = false;
  }
  
  public static Object trace(Object paramObject1, Object paramObject2) {
    // Byte code:
    //   0: ldc gnu/xquery/util/Debug
    //   2: monitorenter
    //   3: getstatic gnu/xquery/util/Debug.tracePort : Lgnu/mapping/OutPort;
    //   6: astore_2
    //   7: aload_2
    //   8: astore_3
    //   9: aload_2
    //   10: ifnonnull -> 42
    //   13: new gnu/mapping/OutPort
    //   16: dup
    //   17: new java/io/FileOutputStream
    //   20: dup
    //   21: getstatic gnu/xquery/util/Debug.traceFilename : Ljava/lang/String;
    //   24: getstatic gnu/xquery/util/Debug.traceShouldAppend : Z
    //   27: invokespecial <init> : (Ljava/lang/String;Z)V
    //   30: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   33: astore_3
    //   34: aload_3
    //   35: astore_2
    //   36: aload_2
    //   37: putstatic gnu/xquery/util/Debug.tracePort : Lgnu/mapping/OutPort;
    //   40: aload_2
    //   41: astore_3
    //   42: aload_3
    //   43: getstatic gnu/xquery/util/Debug.tracePrefix : Ljava/lang/String;
    //   46: invokevirtual print : (Ljava/lang/String;)V
    //   49: aload_3
    //   50: aload_1
    //   51: invokevirtual print : (Ljava/lang/Object;)V
    //   54: aload_3
    //   55: bipush #32
    //   57: invokevirtual print : (C)V
    //   60: new gnu/xml/XMLPrinter
    //   63: dup
    //   64: aload_3
    //   65: iconst_0
    //   66: invokespecial <init> : (Lgnu/mapping/OutPort;Z)V
    //   69: aload_0
    //   70: invokevirtual writeObject : (Ljava/lang/Object;)V
    //   73: aload_3
    //   74: invokevirtual println : ()V
    //   77: getstatic gnu/xquery/util/Debug.traceShouldFlush : Z
    //   80: ifeq -> 87
    //   83: aload_3
    //   84: invokevirtual flush : ()V
    //   87: ldc gnu/xquery/util/Debug
    //   89: monitorexit
    //   90: aload_0
    //   91: areturn
    //   92: astore_3
    //   93: new gnu/mapping/WrappedException
    //   96: dup
    //   97: new java/lang/StringBuilder
    //   100: dup
    //   101: invokespecial <init> : ()V
    //   104: ldc 'Could not open ''
    //   106: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: getstatic gnu/xquery/util/Debug.traceFilename : Ljava/lang/String;
    //   112: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: ldc '' for fn:trace output'
    //   117: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: invokevirtual toString : ()Ljava/lang/String;
    //   123: aload_3
    //   124: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: pop
    //   128: goto -> 36
    //   131: astore_0
    //   132: ldc gnu/xquery/util/Debug
    //   134: monitorexit
    //   135: aload_0
    //   136: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	131	finally
    //   13	34	92	java/lang/Throwable
    //   13	34	131	finally
    //   36	40	131	finally
    //   42	87	131	finally
    //   93	128	131	finally
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/Debug.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
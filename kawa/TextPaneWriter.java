package kawa;

import java.io.Writer;
import javax.swing.text.AttributeSet;

class TextPaneWriter extends Writer {
  ReplDocument document;
  
  String str = "";
  
  AttributeSet style;
  
  public TextPaneWriter(ReplDocument paramReplDocument, AttributeSet paramAttributeSet) {
    this.document = paramReplDocument;
    this.style = paramAttributeSet;
  }
  
  public void close() {
    flush();
  }
  
  public void flush() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield str : Ljava/lang/String;
    //   6: astore_1
    //   7: aload_1
    //   8: ldc ''
    //   10: invokevirtual equals : (Ljava/lang/Object;)Z
    //   13: ifne -> 27
    //   16: aload_0
    //   17: ldc ''
    //   19: putfield str : Ljava/lang/String;
    //   22: aload_0
    //   23: aload_1
    //   24: invokevirtual write : (Ljava/lang/String;)V
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   2	27	30	finally
  }
  
  public void write(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: new java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial <init> : ()V
    //   10: aload_0
    //   11: getfield str : Ljava/lang/String;
    //   14: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: iload_1
    //   18: i2c
    //   19: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   22: invokevirtual toString : ()Ljava/lang/String;
    //   25: putfield str : Ljava/lang/String;
    //   28: iload_1
    //   29: bipush #10
    //   31: if_icmpne -> 38
    //   34: aload_0
    //   35: invokevirtual flush : ()V
    //   38: aload_0
    //   39: monitorexit
    //   40: return
    //   41: astore_2
    //   42: aload_0
    //   43: monitorexit
    //   44: aload_2
    //   45: athrow
    // Exception table:
    //   from	to	target	type
    //   2	28	41	finally
    //   34	38	41	finally
  }
  
  public void write(String paramString) {
    this.document.write(paramString, this.style);
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual flush : ()V
    //   6: iload_3
    //   7: ifeq -> 24
    //   10: aload_0
    //   11: new java/lang/String
    //   14: dup
    //   15: aload_1
    //   16: iload_2
    //   17: iload_3
    //   18: invokespecial <init> : ([CII)V
    //   21: invokevirtual write : (Ljava/lang/String;)V
    //   24: aload_0
    //   25: monitorexit
    //   26: return
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: athrow
    // Exception table:
    //   from	to	target	type
    //   2	6	27	finally
    //   10	24	27	finally
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/TextPaneWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
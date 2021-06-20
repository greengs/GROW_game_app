package kawa.standard;

import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.Procedure0or1;
import gnu.mapping.WrongType;
import gnu.text.Char;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class readchar extends Procedure0or1 {
  public static final readchar peekChar;
  
  public static final readchar readChar = new readchar(false);
  
  boolean peeking;
  
  static {
    peekChar = new readchar(true);
  }
  
  public readchar(boolean paramBoolean) {
    super(str);
    String str;
    this.peeking = paramBoolean;
  }
  
  public final Object apply0() {
    return readChar(InPort.inDefault());
  }
  
  public final Object apply1(Object paramObject) {
    if (paramObject instanceof InPort)
      return readChar((InPort)paramObject); 
    if (paramObject instanceof Reader)
      return readChar((Reader)paramObject); 
    if (paramObject instanceof InputStream)
      return readChar((InputStream)paramObject); 
    throw new WrongType(this, 1, paramObject, "<input-port>");
  }
  
  final Object readChar(InPort paramInPort) {
    try {
      int i;
      if (this.peeking) {
        i = paramInPort.peek();
      } else {
        i = paramInPort.read();
      } 
      return (i < 0) ? Sequence.eofValue : Char.make(i);
    } catch (IOException iOException) {
      throw new RuntimeException("IO Exception caught");
    } 
  }
  
  final Object readChar(InputStream paramInputStream) {
    try {
      int i;
      if (this.peeking) {
        paramInputStream.mark(1);
        i = paramInputStream.read();
        paramInputStream.reset();
      } else {
        i = paramInputStream.read();
      } 
      return (i < 0) ? Sequence.eofValue : Char.make(i);
    } catch (IOException iOException) {
      throw new RuntimeException("IO Exception caught");
    } 
  }
  
  final Object readChar(Reader paramReader) {
    try {
      int i;
      if (this.peeking) {
        paramReader.mark(1);
        i = paramReader.read();
        paramReader.reset();
      } else {
        i = paramReader.read();
      } 
      return (i < 0) ? Sequence.eofValue : Char.make(i);
    } catch (IOException iOException) {
      throw new RuntimeException("IO Exception caught");
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/readchar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
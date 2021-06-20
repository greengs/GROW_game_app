package gnu.lists;

import java.io.IOException;

public interface CharSeq extends CharSequence, Sequence {
  char charAt(int paramInt);
  
  void consume(int paramInt1, int paramInt2, Consumer paramConsumer);
  
  void fill(char paramChar);
  
  void fill(int paramInt1, int paramInt2, char paramChar);
  
  void getChars(int paramInt1, int paramInt2, char[] paramArrayOfchar, int paramInt3);
  
  int length();
  
  void setCharAt(int paramInt, char paramChar);
  
  CharSequence subSequence(int paramInt1, int paramInt2);
  
  String toString();
  
  void writeTo(int paramInt1, int paramInt2, Appendable paramAppendable) throws IOException;
  
  void writeTo(Appendable paramAppendable) throws IOException;
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/CharSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
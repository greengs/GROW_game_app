package gnu.lists;

public interface Consumer extends Appendable {
  Consumer append(char paramChar);
  
  Consumer append(CharSequence paramCharSequence);
  
  Consumer append(CharSequence paramCharSequence, int paramInt1, int paramInt2);
  
  void endAttribute();
  
  void endDocument();
  
  void endElement();
  
  boolean ignoring();
  
  void startAttribute(Object paramObject);
  
  void startDocument();
  
  void startElement(Object paramObject);
  
  void write(int paramInt);
  
  void write(CharSequence paramCharSequence, int paramInt1, int paramInt2);
  
  void write(String paramString);
  
  void write(char[] paramArrayOfchar, int paramInt1, int paramInt2);
  
  void writeBoolean(boolean paramBoolean);
  
  void writeDouble(double paramDouble);
  
  void writeFloat(float paramFloat);
  
  void writeInt(int paramInt);
  
  void writeLong(long paramLong);
  
  void writeObject(Object paramObject);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/Consumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
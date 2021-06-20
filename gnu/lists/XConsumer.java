package gnu.lists;

public interface XConsumer extends Consumer {
  void beginEntity(Object paramObject);
  
  void endEntity();
  
  void writeCDATA(char[] paramArrayOfchar, int paramInt1, int paramInt2);
  
  void writeComment(char[] paramArrayOfchar, int paramInt1, int paramInt2);
  
  void writeProcessingInstruction(String paramString, char[] paramArrayOfchar, int paramInt1, int paramInt2);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/XConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
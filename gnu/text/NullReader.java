package gnu.text;

import java.io.Reader;

public class NullReader extends Reader {
  public static final NullReader nullReader = new NullReader();
  
  public void close() {}
  
  public int read(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    return -1;
  }
  
  public boolean ready() {
    return true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/NullReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
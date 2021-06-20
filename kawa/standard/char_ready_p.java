package kawa.standard;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class char_ready_p {
  public static boolean ready(Object paramObject) {
    try {
      if (paramObject instanceof InputStream) {
        if (((InputStream)paramObject).available() > 0)
          return true; 
      } else {
        if (paramObject instanceof Reader)
          return ((Reader)paramObject).ready(); 
        throw new ClassCastException("invalid argument to char-ready?");
      } 
    } catch (IOException iOException) {}
    return false;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/char_ready_p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
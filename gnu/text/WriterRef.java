package gnu.text;

import java.io.Writer;
import java.lang.ref.WeakReference;

class WriterRef extends WeakReference {
  WriterRef next;
  
  WriterRef prev;
  
  public WriterRef(Writer paramWriter) {
    super((T)paramWriter);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/WriterRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
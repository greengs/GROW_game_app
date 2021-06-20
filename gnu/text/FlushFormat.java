package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

public class FlushFormat extends ReportFormat {
  private static FlushFormat flush;
  
  public static FlushFormat getInstance() {
    if (flush == null)
      flush = new FlushFormat(); 
    return flush;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    paramWriter.flush();
    return paramInt;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/FlushFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
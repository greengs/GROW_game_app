package androidx.core.util;

import android.util.Log;
import androidx.annotation.RestrictTo;
import java.io.Writer;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class LogWriter extends Writer {
  private StringBuilder mBuilder = new StringBuilder(128);
  
  private final String mTag;
  
  public LogWriter(String paramString) {
    this.mTag = paramString;
  }
  
  private void flushBuilder() {
    if (this.mBuilder.length() > 0) {
      Log.d(this.mTag, this.mBuilder.toString());
      this.mBuilder.delete(0, this.mBuilder.length());
    } 
  }
  
  public void close() {
    flushBuilder();
  }
  
  public void flush() {
    flushBuilder();
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    for (int i = 0; i < paramInt2; i++) {
      char c = paramArrayOfchar[paramInt1 + i];
      if (c == '\n') {
        flushBuilder();
      } else {
        this.mBuilder.append(c);
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/util/LogWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
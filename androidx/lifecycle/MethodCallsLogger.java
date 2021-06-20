package androidx.lifecycle;

import androidx.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class MethodCallsLogger {
  private Map<String, Integer> mCalledMethods = new HashMap<String, Integer>();
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public boolean approveCall(String paramString, int paramInt) {
    int i;
    boolean bool;
    Integer integer = this.mCalledMethods.get(paramString);
    if (integer != null) {
      i = integer.intValue();
    } else {
      i = 0;
    } 
    if ((i & paramInt) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mCalledMethods.put(paramString, Integer.valueOf(i | paramInt));
    return !bool;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/MethodCallsLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
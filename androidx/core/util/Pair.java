package androidx.core.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Pair<F, S> {
  @Nullable
  public final F first;
  
  @Nullable
  public final S second;
  
  public Pair(@Nullable F paramF, @Nullable S paramS) {
    this.first = paramF;
    this.second = paramS;
  }
  
  @NonNull
  public static <A, B> Pair<A, B> create(@Nullable A paramA, @Nullable B paramB) {
    return new Pair<A, B>(paramA, paramB);
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof Pair) {
      paramObject = paramObject;
      if (ObjectsCompat.equals(((Pair)paramObject).first, this.first) && ObjectsCompat.equals(((Pair)paramObject).second, this.second))
        return true; 
    } 
    return false;
  }
  
  public int hashCode() {
    int i;
    int j = 0;
    if (this.first == null) {
      i = 0;
    } else {
      i = this.first.hashCode();
    } 
    if (this.second != null)
      j = this.second.hashCode(); 
    return i ^ j;
  }
  
  public String toString() {
    return "Pair{" + String.valueOf(this.first) + " " + String.valueOf(this.second) + "}";
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/util/Pair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
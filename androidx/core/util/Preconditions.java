package androidx.core.util;

import android.text.TextUtils;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class Preconditions {
  public static void checkArgument(boolean paramBoolean) {
    if (!paramBoolean)
      throw new IllegalArgumentException(); 
  }
  
  public static void checkArgument(boolean paramBoolean, Object paramObject) {
    if (!paramBoolean)
      throw new IllegalArgumentException(String.valueOf(paramObject)); 
  }
  
  public static float checkArgumentFinite(float paramFloat, String paramString) {
    if (Float.isNaN(paramFloat))
      throw new IllegalArgumentException(paramString + " must not be NaN"); 
    if (Float.isInfinite(paramFloat))
      throw new IllegalArgumentException(paramString + " must not be infinite"); 
    return paramFloat;
  }
  
  public static float checkArgumentInRange(float paramFloat1, float paramFloat2, float paramFloat3, String paramString) {
    if (Float.isNaN(paramFloat1))
      throw new IllegalArgumentException(paramString + " must not be NaN"); 
    if (paramFloat1 < paramFloat2)
      throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", new Object[] { paramString, Float.valueOf(paramFloat2), Float.valueOf(paramFloat3) })); 
    if (paramFloat1 > paramFloat3)
      throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", new Object[] { paramString, Float.valueOf(paramFloat2), Float.valueOf(paramFloat3) })); 
    return paramFloat1;
  }
  
  public static int checkArgumentInRange(int paramInt1, int paramInt2, int paramInt3, String paramString) {
    if (paramInt1 < paramInt2)
      throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", new Object[] { paramString, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) })); 
    if (paramInt1 > paramInt3)
      throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", new Object[] { paramString, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) })); 
    return paramInt1;
  }
  
  public static long checkArgumentInRange(long paramLong1, long paramLong2, long paramLong3, String paramString) {
    if (paramLong1 < paramLong2)
      throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", new Object[] { paramString, Long.valueOf(paramLong2), Long.valueOf(paramLong3) })); 
    if (paramLong1 > paramLong3)
      throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", new Object[] { paramString, Long.valueOf(paramLong2), Long.valueOf(paramLong3) })); 
    return paramLong1;
  }
  
  @IntRange(from = 0L)
  public static int checkArgumentNonnegative(int paramInt) {
    if (paramInt < 0)
      throw new IllegalArgumentException(); 
    return paramInt;
  }
  
  @IntRange(from = 0L)
  public static int checkArgumentNonnegative(int paramInt, String paramString) {
    if (paramInt < 0)
      throw new IllegalArgumentException(paramString); 
    return paramInt;
  }
  
  public static long checkArgumentNonnegative(long paramLong) {
    if (paramLong < 0L)
      throw new IllegalArgumentException(); 
    return paramLong;
  }
  
  public static long checkArgumentNonnegative(long paramLong, String paramString) {
    if (paramLong < 0L)
      throw new IllegalArgumentException(paramString); 
    return paramLong;
  }
  
  public static int checkArgumentPositive(int paramInt, String paramString) {
    if (paramInt <= 0)
      throw new IllegalArgumentException(paramString); 
    return paramInt;
  }
  
  public static float[] checkArrayElementsInRange(float[] paramArrayOffloat, float paramFloat1, float paramFloat2, String paramString) {
    checkNotNull(paramArrayOffloat, paramString + " must not be null");
    int i;
    for (i = 0; i < paramArrayOffloat.length; i++) {
      float f = paramArrayOffloat[i];
      if (Float.isNaN(f))
        throw new IllegalArgumentException(paramString + "[" + i + "] must not be NaN"); 
      if (f < paramFloat1)
        throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too low)", new Object[] { paramString, Integer.valueOf(i), Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) })); 
      if (f > paramFloat2)
        throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too high)", new Object[] { paramString, Integer.valueOf(i), Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) })); 
    } 
    return paramArrayOffloat;
  }
  
  public static <T> T[] checkArrayElementsNotNull(T[] paramArrayOfT, String paramString) {
    if (paramArrayOfT == null)
      throw new NullPointerException(paramString + " must not be null"); 
    for (int i = 0; i < paramArrayOfT.length; i++) {
      if (paramArrayOfT[i] == null)
        throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", new Object[] { paramString, Integer.valueOf(i) })); 
    } 
    return paramArrayOfT;
  }
  
  @NonNull
  public static <C extends Collection<T>, T> C checkCollectionElementsNotNull(C paramC, String paramString) {
    if (paramC == null)
      throw new NullPointerException(paramString + " must not be null"); 
    long l = 0L;
    Iterator iterator = paramC.iterator();
    while (iterator.hasNext()) {
      if (iterator.next() == null)
        throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", new Object[] { paramString, Long.valueOf(l) })); 
      l++;
    } 
    return paramC;
  }
  
  public static <T> Collection<T> checkCollectionNotEmpty(Collection<T> paramCollection, String paramString) {
    if (paramCollection == null)
      throw new NullPointerException(paramString + " must not be null"); 
    if (paramCollection.isEmpty())
      throw new IllegalArgumentException(paramString + " is empty"); 
    return paramCollection;
  }
  
  public static int checkFlagsArgument(int paramInt1, int paramInt2) {
    if ((paramInt1 & paramInt2) != paramInt1)
      throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(paramInt1) + ", but only 0x" + Integer.toHexString(paramInt2) + " are allowed"); 
    return paramInt1;
  }
  
  @NonNull
  public static <T> T checkNotNull(T paramT) {
    if (paramT == null)
      throw new NullPointerException(); 
    return paramT;
  }
  
  @NonNull
  public static <T> T checkNotNull(T paramT, Object paramObject) {
    if (paramT == null)
      throw new NullPointerException(String.valueOf(paramObject)); 
    return paramT;
  }
  
  public static void checkState(boolean paramBoolean) {
    checkState(paramBoolean, null);
  }
  
  public static void checkState(boolean paramBoolean, String paramString) {
    if (!paramBoolean)
      throw new IllegalStateException(paramString); 
  }
  
  @NonNull
  public static <T extends CharSequence> T checkStringNotEmpty(T paramT) {
    if (TextUtils.isEmpty((CharSequence)paramT))
      throw new IllegalArgumentException(); 
    return paramT;
  }
  
  @NonNull
  public static <T extends CharSequence> T checkStringNotEmpty(T paramT, Object paramObject) {
    if (TextUtils.isEmpty((CharSequence)paramT))
      throw new IllegalArgumentException(String.valueOf(paramObject)); 
    return paramT;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/util/Preconditions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package androidx.core.os;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.Locale;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
interface LocaleListInterface {
  boolean equals(Object paramObject);
  
  Locale get(int paramInt);
  
  @Nullable
  Locale getFirstMatch(String[] paramArrayOfString);
  
  Object getLocaleList();
  
  int hashCode();
  
  @IntRange(from = -1L)
  int indexOf(Locale paramLocale);
  
  boolean isEmpty();
  
  void setLocaleList(@NonNull Locale... paramVarArgs);
  
  @IntRange(from = 0L)
  int size();
  
  String toLanguageTags();
  
  String toString();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/os/LocaleListInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
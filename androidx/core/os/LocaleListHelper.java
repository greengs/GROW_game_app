package androidx.core.os;

import android.os.Build;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
final class LocaleListHelper {
  private static final Locale EN_LATN;
  
  private static final Locale LOCALE_AR_XB;
  
  private static final Locale LOCALE_EN_XA;
  
  private static final int NUM_PSEUDO_LOCALES = 2;
  
  private static final String STRING_AR_XB = "ar-XB";
  
  private static final String STRING_EN_XA = "en-XA";
  
  @GuardedBy("sLock")
  private static LocaleListHelper sDefaultAdjustedLocaleList;
  
  @GuardedBy("sLock")
  private static LocaleListHelper sDefaultLocaleList;
  
  private static final Locale[] sEmptyList = new Locale[0];
  
  private static final LocaleListHelper sEmptyLocaleList = new LocaleListHelper(new Locale[0]);
  
  @GuardedBy("sLock")
  private static Locale sLastDefaultLocale;
  
  @GuardedBy("sLock")
  private static LocaleListHelper sLastExplicitlySetLocaleList;
  
  private static final Object sLock;
  
  private final Locale[] mList;
  
  @NonNull
  private final String mStringRepresentation;
  
  static {
    LOCALE_EN_XA = new Locale("en", "XA");
    LOCALE_AR_XB = new Locale("ar", "XB");
    EN_LATN = LocaleHelper.forLanguageTag("en-Latn");
    sLock = new Object();
    sLastExplicitlySetLocaleList = null;
    sDefaultLocaleList = null;
    sDefaultAdjustedLocaleList = null;
    sLastDefaultLocale = null;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  LocaleListHelper(@NonNull Locale paramLocale, LocaleListHelper paramLocaleListHelper) {
    int j;
    Locale[] arrayOfLocale;
    if (paramLocale == null)
      throw new NullPointerException("topLocale is null"); 
    if (paramLocaleListHelper == null) {
      j = 0;
    } else {
      j = paramLocaleListHelper.mList.length;
    } 
    int k = -1;
    int i = 0;
    while (true) {
      int m = k;
      if (i < j)
        if (paramLocale.equals(paramLocaleListHelper.mList[i])) {
          m = i;
        } else {
          i++;
          continue;
        }  
      if (m == -1) {
        i = 1;
      } else {
        i = 0;
      } 
      k = j + i;
      arrayOfLocale = new Locale[k];
      arrayOfLocale[0] = (Locale)paramLocale.clone();
      if (m == -1) {
        for (i = 0; i < j; i++)
          arrayOfLocale[i + 1] = (Locale)paramLocaleListHelper.mList[i].clone(); 
        break;
      } 
      for (i = 0; i < m; i++)
        arrayOfLocale[i + 1] = (Locale)paramLocaleListHelper.mList[i].clone(); 
      for (i = m + 1; i < j; i++)
        arrayOfLocale[i] = (Locale)paramLocaleListHelper.mList[i].clone(); 
      break;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    for (i = 0; i < k; i++) {
      stringBuilder.append(LocaleHelper.toLanguageTag(arrayOfLocale[i]));
      if (i < k - 1)
        stringBuilder.append(','); 
    } 
    this.mList = arrayOfLocale;
    this.mStringRepresentation = stringBuilder.toString();
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  LocaleListHelper(@NonNull Locale... paramVarArgs) {
    if (paramVarArgs.length == 0) {
      this.mList = sEmptyList;
      this.mStringRepresentation = "";
      return;
    } 
    Locale[] arrayOfLocale = new Locale[paramVarArgs.length];
    HashSet<Locale> hashSet = new HashSet();
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < paramVarArgs.length; i++) {
      Locale locale = paramVarArgs[i];
      if (locale == null)
        throw new NullPointerException("list[" + i + "] is null"); 
      if (hashSet.contains(locale))
        throw new IllegalArgumentException("list[" + i + "] is a repetition"); 
      locale = (Locale)locale.clone();
      arrayOfLocale[i] = locale;
      stringBuilder.append(LocaleHelper.toLanguageTag(locale));
      if (i < paramVarArgs.length - 1)
        stringBuilder.append(','); 
      hashSet.add(locale);
    } 
    this.mList = arrayOfLocale;
    this.mStringRepresentation = stringBuilder.toString();
  }
  
  private Locale computeFirstMatch(Collection<String> paramCollection, boolean paramBoolean) {
    int i = computeFirstMatchIndex(paramCollection, paramBoolean);
    return (i == -1) ? null : this.mList[i];
  }
  
  private int computeFirstMatchIndex(Collection<String> paramCollection, boolean paramBoolean) {
    if (this.mList.length == 1)
      return 0; 
    if (this.mList.length == 0)
      return -1; 
    int j = Integer.MAX_VALUE;
    int i = j;
    if (paramBoolean) {
      int k = findFirstMatchIndex(EN_LATN);
      if (k == 0)
        return 0; 
      i = j;
      if (k < Integer.MAX_VALUE)
        i = k; 
    } 
    Iterator<String> iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      j = findFirstMatchIndex(LocaleHelper.forLanguageTag(iterator.next()));
      if (j == 0)
        return 0; 
      if (j < i)
        i = j; 
    } 
    j = i;
    return (i == Integer.MAX_VALUE) ? 0 : j;
  }
  
  private int findFirstMatchIndex(Locale paramLocale) {
    for (int i = 0; i < this.mList.length; i++) {
      if (matchScore(paramLocale, this.mList[i]) > 0)
        return i; 
    } 
    return Integer.MAX_VALUE;
  }
  
  @NonNull
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static LocaleListHelper forLanguageTags(@Nullable String paramString) {
    if (paramString == null || paramString.isEmpty())
      return getEmptyLocaleList(); 
    String[] arrayOfString = paramString.split(",", -1);
    Locale[] arrayOfLocale = new Locale[arrayOfString.length];
    for (int i = 0; i < arrayOfLocale.length; i++)
      arrayOfLocale[i] = LocaleHelper.forLanguageTag(arrayOfString[i]); 
    return new LocaleListHelper(arrayOfLocale);
  }
  
  @NonNull
  @Size(min = 1L)
  static LocaleListHelper getAdjustedDefault() {
    getDefault();
    synchronized (sLock) {
      return sDefaultAdjustedLocaleList;
    } 
  }
  
  @NonNull
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  @Size(min = 1L)
  static LocaleListHelper getDefault() {
    null = Locale.getDefault();
    synchronized (sLock) {
      if (!null.equals(sLastDefaultLocale)) {
        LocaleListHelper localeListHelper;
        sLastDefaultLocale = null;
        if (sDefaultLocaleList != null && null.equals(sDefaultLocaleList.get(0))) {
          localeListHelper = sDefaultLocaleList;
          return localeListHelper;
        } 
        sDefaultLocaleList = new LocaleListHelper((Locale)localeListHelper, sLastExplicitlySetLocaleList);
        sDefaultAdjustedLocaleList = sDefaultLocaleList;
      } 
      return sDefaultLocaleList;
    } 
  }
  
  @NonNull
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static LocaleListHelper getEmptyLocaleList() {
    return sEmptyLocaleList;
  }
  
  private static String getLikelyScript(Locale paramLocale) {
    if (Build.VERSION.SDK_INT >= 21) {
      String str = paramLocale.getScript();
      return !str.isEmpty() ? str : "";
    } 
    return "";
  }
  
  private static boolean isPseudoLocale(String paramString) {
    return ("en-XA".equals(paramString) || "ar-XB".equals(paramString));
  }
  
  private static boolean isPseudoLocale(Locale paramLocale) {
    return (LOCALE_EN_XA.equals(paramLocale) || LOCALE_AR_XB.equals(paramLocale));
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static boolean isPseudoLocalesOnly(@Nullable String[] paramArrayOfString) {
    if (paramArrayOfString != null) {
      if (paramArrayOfString.length > 3)
        return false; 
      int j = paramArrayOfString.length;
      int i = 0;
      while (true) {
        if (i < j) {
          String str = paramArrayOfString[i];
          if (!str.isEmpty() && !isPseudoLocale(str))
            return false; 
          i++;
          continue;
        } 
        return true;
      } 
    } 
    return true;
  }
  
  @IntRange(from = 0L, to = 1L)
  private static int matchScore(Locale paramLocale1, Locale paramLocale2) {
    boolean bool2 = true;
    boolean bool3 = false;
    if (paramLocale1.equals(paramLocale2))
      return 1; 
    boolean bool1 = bool3;
    if (paramLocale1.getLanguage().equals(paramLocale2.getLanguage())) {
      bool1 = bool3;
      if (!isPseudoLocale(paramLocale1)) {
        bool1 = bool3;
        if (!isPseudoLocale(paramLocale2)) {
          String str = getLikelyScript(paramLocale1);
          if (str.isEmpty()) {
            String str1 = paramLocale1.getCountry();
            if (!str1.isEmpty()) {
              bool1 = bool3;
              return str1.equals(paramLocale2.getCountry()) ? 1 : bool1;
            } 
            return 1;
          } 
          return str.equals(getLikelyScript(paramLocale2)) ? bool2 : 0;
        } 
      } 
    } 
    return bool1;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static void setDefault(@NonNull @Size(min = 1L) LocaleListHelper paramLocaleListHelper) {
    setDefault(paramLocaleListHelper, 0);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static void setDefault(@NonNull @Size(min = 1L) LocaleListHelper paramLocaleListHelper, int paramInt) {
    if (paramLocaleListHelper == null)
      throw new NullPointerException("locales is null"); 
    if (paramLocaleListHelper.isEmpty())
      throw new IllegalArgumentException("locales is empty"); 
    synchronized (sLock) {
      sLastDefaultLocale = paramLocaleListHelper.get(paramInt);
      Locale.setDefault(sLastDefaultLocale);
      sLastExplicitlySetLocaleList = paramLocaleListHelper;
      sDefaultLocaleList = paramLocaleListHelper;
      if (paramInt == 0) {
        sDefaultAdjustedLocaleList = sDefaultLocaleList;
      } else {
        sDefaultAdjustedLocaleList = new LocaleListHelper(sLastDefaultLocale, sDefaultLocaleList);
      } 
      return;
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject != this) {
      if (!(paramObject instanceof LocaleListHelper))
        return false; 
      paramObject = ((LocaleListHelper)paramObject).mList;
      if (this.mList.length != paramObject.length)
        return false; 
      int i = 0;
      while (true) {
        if (i < this.mList.length) {
          if (!this.mList[i].equals(paramObject[i]))
            return false; 
          i++;
          continue;
        } 
        return true;
      } 
    } 
    return true;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  Locale get(int paramInt) {
    return (paramInt >= 0 && paramInt < this.mList.length) ? this.mList[paramInt] : null;
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  Locale getFirstMatch(String[] paramArrayOfString) {
    return computeFirstMatch(Arrays.asList(paramArrayOfString), false);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getFirstMatchIndex(String[] paramArrayOfString) {
    return computeFirstMatchIndex(Arrays.asList(paramArrayOfString), false);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getFirstMatchIndexWithEnglishSupported(Collection<String> paramCollection) {
    return computeFirstMatchIndex(paramCollection, true);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getFirstMatchIndexWithEnglishSupported(String[] paramArrayOfString) {
    return getFirstMatchIndexWithEnglishSupported(Arrays.asList(paramArrayOfString));
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  Locale getFirstMatchWithEnglishSupported(String[] paramArrayOfString) {
    return computeFirstMatch(Arrays.asList(paramArrayOfString), true);
  }
  
  public int hashCode() {
    int j = 1;
    for (int i = 0; i < this.mList.length; i++)
      j = j * 31 + this.mList[i].hashCode(); 
    return j;
  }
  
  @IntRange(from = -1L)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int indexOf(Locale paramLocale) {
    for (int i = 0; i < this.mList.length; i++) {
      if (this.mList[i].equals(paramLocale))
        return i; 
    } 
    return -1;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  boolean isEmpty() {
    return (this.mList.length == 0);
  }
  
  @IntRange(from = 0L)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int size() {
    return this.mList.length;
  }
  
  @NonNull
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  String toLanguageTags() {
    return this.mStringRepresentation;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    for (int i = 0; i < this.mList.length; i++) {
      stringBuilder.append(this.mList[i]);
      if (i < this.mList.length - 1)
        stringBuilder.append(','); 
    } 
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/os/LocaleListHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
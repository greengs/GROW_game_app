package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class TypefaceCompatBaseImpl {
  private static final String CACHE_FILE_PREFIX = "cached_font_";
  
  private static final String TAG = "TypefaceCompatBaseImpl";
  
  private FontResourcesParserCompat.FontFileResourceEntry findBestEntry(FontResourcesParserCompat.FontFamilyFilesResourceEntry paramFontFamilyFilesResourceEntry, int paramInt) {
    return findBestFont(paramFontFamilyFilesResourceEntry.getEntries(), paramInt, new StyleExtractor<FontResourcesParserCompat.FontFileResourceEntry>() {
          public int getWeight(FontResourcesParserCompat.FontFileResourceEntry param1FontFileResourceEntry) {
            return param1FontFileResourceEntry.getWeight();
          }
          
          public boolean isItalic(FontResourcesParserCompat.FontFileResourceEntry param1FontFileResourceEntry) {
            return param1FontFileResourceEntry.isItalic();
          }
        });
  }
  
  private static <T> T findBestFont(T[] paramArrayOfT, int paramInt, StyleExtractor<T> paramStyleExtractor) {
    // Byte code:
    //   0: iload_1
    //   1: iconst_1
    //   2: iand
    //   3: ifne -> 118
    //   6: sipush #400
    //   9: istore_3
    //   10: iload_1
    //   11: iconst_2
    //   12: iand
    //   13: ifeq -> 125
    //   16: iconst_1
    //   17: istore #8
    //   19: aconst_null
    //   20: astore #9
    //   22: ldc 2147483647
    //   24: istore #4
    //   26: aload_0
    //   27: arraylength
    //   28: istore #7
    //   30: iconst_0
    //   31: istore_1
    //   32: iload_1
    //   33: iload #7
    //   35: if_icmpge -> 137
    //   38: aload_0
    //   39: iload_1
    //   40: aaload
    //   41: astore #10
    //   43: aload_2
    //   44: aload #10
    //   46: invokeinterface getWeight : (Ljava/lang/Object;)I
    //   51: iload_3
    //   52: isub
    //   53: invokestatic abs : (I)I
    //   56: istore #6
    //   58: aload_2
    //   59: aload #10
    //   61: invokeinterface isItalic : (Ljava/lang/Object;)Z
    //   66: iload #8
    //   68: if_icmpne -> 131
    //   71: iconst_0
    //   72: istore #5
    //   74: iload #6
    //   76: iconst_2
    //   77: imul
    //   78: iload #5
    //   80: iadd
    //   81: istore #6
    //   83: aload #9
    //   85: ifnull -> 99
    //   88: iload #4
    //   90: istore #5
    //   92: iload #4
    //   94: iload #6
    //   96: if_icmple -> 107
    //   99: aload #10
    //   101: astore #9
    //   103: iload #6
    //   105: istore #5
    //   107: iload_1
    //   108: iconst_1
    //   109: iadd
    //   110: istore_1
    //   111: iload #5
    //   113: istore #4
    //   115: goto -> 32
    //   118: sipush #700
    //   121: istore_3
    //   122: goto -> 10
    //   125: iconst_0
    //   126: istore #8
    //   128: goto -> 19
    //   131: iconst_1
    //   132: istore #5
    //   134: goto -> 74
    //   137: aload #9
    //   139: areturn
  }
  
  @Nullable
  public Typeface createFromFontFamilyFilesResourceEntry(Context paramContext, FontResourcesParserCompat.FontFamilyFilesResourceEntry paramFontFamilyFilesResourceEntry, Resources paramResources, int paramInt) {
    FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry = findBestEntry(paramFontFamilyFilesResourceEntry, paramInt);
    return (fontFileResourceEntry == null) ? null : TypefaceCompat.createFromResourcesFontFile(paramContext, paramResources, fontFileResourceEntry.getResourceId(), fontFileResourceEntry.getFileName(), paramInt);
  }
  
  public Typeface createFromFontInfo(Context paramContext, @Nullable CancellationSignal paramCancellationSignal, @NonNull FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt) {
    InputStream inputStream1;
    InputStream inputStream2;
    if (paramArrayOfFontInfo.length < 1)
      return null; 
    FontsContractCompat.FontInfo fontInfo = findBestInfo(paramArrayOfFontInfo, paramInt);
    paramArrayOfFontInfo = null;
    paramCancellationSignal = null;
    try {
      InputStream inputStream = paramContext.getContentResolver().openInputStream(fontInfo.getUri());
      inputStream1 = inputStream;
      inputStream2 = inputStream;
      return createFromInputStream(paramContext, inputStream);
    } catch (IOException iOException) {
      return null;
    } finally {
      TypefaceCompatUtil.closeQuietly(inputStream2);
    } 
  }
  
  protected Typeface createFromInputStream(Context paramContext, InputStream paramInputStream) {
    File file = TypefaceCompatUtil.getTempFile(paramContext);
    if (file == null)
      return null; 
    try {
      boolean bool = TypefaceCompatUtil.copyToFile(file, paramInputStream);
      if (!bool)
        return null; 
      return Typeface.createFromFile(file.getPath());
    } catch (RuntimeException runtimeException) {
      return null;
    } finally {
      file.delete();
    } 
  }
  
  @Nullable
  public Typeface createFromResourcesFontFile(Context paramContext, Resources paramResources, int paramInt1, String paramString, int paramInt2) {
    File file = TypefaceCompatUtil.getTempFile(paramContext);
    if (file == null)
      return null; 
    try {
      boolean bool = TypefaceCompatUtil.copyToFile(file, paramResources, paramInt1);
      if (!bool)
        return null; 
      return Typeface.createFromFile(file.getPath());
    } catch (RuntimeException runtimeException) {
      return null;
    } finally {
      file.delete();
    } 
  }
  
  protected FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt) {
    return findBestFont(paramArrayOfFontInfo, paramInt, new StyleExtractor<FontsContractCompat.FontInfo>() {
          public int getWeight(FontsContractCompat.FontInfo param1FontInfo) {
            return param1FontInfo.getWeight();
          }
          
          public boolean isItalic(FontsContractCompat.FontInfo param1FontInfo) {
            return param1FontInfo.isItalic();
          }
        });
  }
  
  private static interface StyleExtractor<T> {
    int getWeight(T param1T);
    
    boolean isItalic(T param1T);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/TypefaceCompatBaseImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package androidx.core.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class FontsContractCompat {
  private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static final String PARCEL_FONT_RESULTS = "font_results";
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
  
  private static final String TAG = "FontsContractCompat";
  
  private static final SelfDestructiveThread sBackgroundThread;
  
  private static final Comparator<byte[]> sByteArrayComparator;
  
  static final Object sLock;
  
  @GuardedBy("sLock")
  static final SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>> sPendingReplies;
  
  static final LruCache<String, Typeface> sTypefaceCache = new LruCache(16);
  
  static {
    sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
    sLock = new Object();
    sPendingReplies = new SimpleArrayMap();
    sByteArrayComparator = new Comparator<byte[]>() {
        public int compare(byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2) {
          if (param1ArrayOfbyte1.length != param1ArrayOfbyte2.length)
            return param1ArrayOfbyte1.length - param1ArrayOfbyte2.length; 
          for (int i = 0; i < param1ArrayOfbyte1.length; i++) {
            if (param1ArrayOfbyte1[i] != param1ArrayOfbyte2[i])
              return param1ArrayOfbyte1[i] - param1ArrayOfbyte2[i]; 
          } 
          return 0;
        }
      };
  }
  
  @Nullable
  public static Typeface buildTypeface(@NonNull Context paramContext, @Nullable CancellationSignal paramCancellationSignal, @NonNull FontInfo[] paramArrayOfFontInfo) {
    return TypefaceCompat.createFromFontInfo(paramContext, paramCancellationSignal, paramArrayOfFontInfo, 0);
  }
  
  private static List<byte[]> convertToByteArrayList(Signature[] paramArrayOfSignature) {
    ArrayList<byte[]> arrayList = new ArrayList();
    for (int i = 0; i < paramArrayOfSignature.length; i++)
      arrayList.add(paramArrayOfSignature[i].toByteArray()); 
    return (List<byte[]>)arrayList;
  }
  
  private static boolean equalsByteArrayList(List<byte[]> paramList1, List<byte[]> paramList2) {
    if (paramList1.size() != paramList2.size())
      return false; 
    for (int i = 0; i < paramList1.size(); i++) {
      if (!Arrays.equals(paramList1.get(i), paramList2.get(i)))
        return false; 
    } 
    return true;
  }
  
  @NonNull
  public static FontFamilyResult fetchFonts(@NonNull Context paramContext, @Nullable CancellationSignal paramCancellationSignal, @NonNull FontRequest paramFontRequest) throws PackageManager.NameNotFoundException {
    ProviderInfo providerInfo = getProvider(paramContext.getPackageManager(), paramFontRequest, paramContext.getResources());
    return (providerInfo == null) ? new FontFamilyResult(1, null) : new FontFamilyResult(0, getFontFromProvider(paramContext, paramFontRequest, providerInfo.authority, paramCancellationSignal));
  }
  
  private static List<List<byte[]>> getCertificates(FontRequest paramFontRequest, Resources paramResources) {
    return (paramFontRequest.getCertificates() != null) ? paramFontRequest.getCertificates() : FontResourcesParserCompat.readCerts(paramResources, paramFontRequest.getCertificatesArrayResId());
  }
  
  @NonNull
  @VisibleForTesting
  static FontInfo[] getFontFromProvider(Context paramContext, FontRequest paramFontRequest, String paramString, CancellationSignal paramCancellationSignal) {
    Cursor cursor;
    ArrayList<FontInfo> arrayList = new ArrayList();
    Uri uri1 = (new Uri.Builder()).scheme("content").authority(paramString).build();
    Uri uri2 = (new Uri.Builder()).scheme("content").authority(paramString).appendPath("file").build();
    String str = null;
    paramString = str;
    try {
      Cursor cursor1;
      ArrayList<FontInfo> arrayList1;
      String str1;
      if (Build.VERSION.SDK_INT > 16) {
        paramString = str;
        ContentResolver contentResolver = paramContext.getContentResolver();
        paramString = str;
        str1 = paramFontRequest.getQuery();
        paramString = str;
        cursor1 = contentResolver.query(uri1, new String[] { "_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code" }, "query = ?", new String[] { str1 }, null, paramCancellationSignal);
      } else {
        paramString = str;
        ContentResolver contentResolver = cursor1.getContentResolver();
        paramString = str;
        str1 = str1.getQuery();
        paramString = str;
        cursor1 = contentResolver.query(uri1, new String[] { "_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code" }, "query = ?", new String[] { str1 }, null);
      } 
      ArrayList<FontInfo> arrayList2 = arrayList;
      if (cursor1 != null) {
        arrayList2 = arrayList;
        cursor = cursor1;
        if (cursor1.getCount() > 0) {
          cursor = cursor1;
          int i = cursor1.getColumnIndex("result_code");
          cursor = cursor1;
          ArrayList<FontInfo> arrayList3 = new ArrayList();
          try {
            int j = cursor1.getColumnIndex("_id");
            int k = cursor1.getColumnIndex("file_id");
          } finally {
            arrayList2 = null;
            cursor = cursor1;
            arrayList1 = arrayList2;
            if (cursor != null)
              cursor.close(); 
          } 
          arrayList2 = arrayList3;
        } 
      } 
      if (arrayList1 != null)
        arrayList1.close(); 
      return arrayList2.<FontInfo>toArray(new FontInfo[0]);
    } finally {}
    if (cursor != null)
      cursor.close(); 
    throw paramContext;
  }
  
  @NonNull
  static TypefaceResult getFontInternal(Context paramContext, FontRequest paramFontRequest, int paramInt) {
    FontFamilyResult fontFamilyResult;
    byte b = -3;
    try {
      fontFamilyResult = fetchFonts(paramContext, null, paramFontRequest);
      if (fontFamilyResult.getStatusCode() == 0) {
        Typeface typeface = TypefaceCompat.createFromFontInfo(paramContext, null, fontFamilyResult.getFonts(), paramInt);
        paramInt = b;
        if (typeface != null)
          paramInt = 0; 
        return new TypefaceResult(typeface, paramInt);
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return new TypefaceResult(null, -1);
    } 
    if (fontFamilyResult.getStatusCode() == 1) {
      paramInt = -2;
      return new TypefaceResult(null, paramInt);
    } 
    paramInt = -3;
    return new TypefaceResult(null, paramInt);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static Typeface getFontSync(Context paramContext, final FontRequest request, @Nullable final ResourcesCompat.FontCallback fontCallback, @Nullable final Handler handler, boolean paramBoolean, int paramInt1, final int style) {
    final TypefaceResult context;
    final String id = request.getIdentifier() + "-" + style;
    Typeface typeface = (Typeface)sTypefaceCache.get(str);
    if (typeface != null) {
      if (fontCallback != null)
        fontCallback.onFontRetrieved(typeface); 
      return typeface;
    } 
    if (paramBoolean && paramInt1 == -1) {
      typefaceResult = getFontInternal(paramContext, request, style);
      if (fontCallback != null) {
        if (typefaceResult.mResult == 0) {
          fontCallback.callbackSuccessAsync(typefaceResult.mTypeface, handler);
          return typefaceResult.mTypeface;
        } 
      } else {
        return typefaceResult.mTypeface;
      } 
      fontCallback.callbackFailAsync(typefaceResult.mResult, handler);
      return typefaceResult.mTypeface;
    } 
    Callable<TypefaceResult> callable = new Callable<TypefaceResult>() {
        public FontsContractCompat.TypefaceResult call() throws Exception {
          FontsContractCompat.TypefaceResult typefaceResult = FontsContractCompat.getFontInternal(context, request, style);
          if (typefaceResult.mTypeface != null)
            FontsContractCompat.sTypefaceCache.put(id, typefaceResult.mTypeface); 
          return typefaceResult;
        }
      };
    if (paramBoolean)
      try {
        return ((TypefaceResult)sBackgroundThread.postAndWait((Callable)callable, paramInt1)).mTypeface;
      } catch (InterruptedException interruptedException) {
        return null;
      }  
    if (fontCallback == null) {
      typefaceResult = null;
    } else {
      null = new SelfDestructiveThread.ReplyCallback<TypefaceResult>() {
          public void onReply(FontsContractCompat.TypefaceResult param1TypefaceResult) {
            if (param1TypefaceResult == null) {
              fontCallback.callbackFailAsync(1, handler);
              return;
            } 
            if (param1TypefaceResult.mResult == 0) {
              fontCallback.callbackSuccessAsync(param1TypefaceResult.mTypeface, handler);
              return;
            } 
            fontCallback.callbackFailAsync(param1TypefaceResult.mResult, handler);
          }
        };
    } 
    synchronized (sLock) {
      if (sPendingReplies.containsKey(str)) {
        if (null != null)
          ((ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>)sPendingReplies.get(str)).add(null); 
        return null;
      } 
    } 
    if (typefaceResult != null) {
      ArrayList<TypefaceResult> arrayList = new ArrayList();
      arrayList.add(typefaceResult);
      sPendingReplies.put(str, arrayList);
    } 
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=InnerObjectType{ObjectType{androidx/core/content/res/ResourcesCompat}.Landroidx/core/content/res/ResourcesCompat$FontCallback;}, name=paramFontCallback} */
    sBackgroundThread.postAndReply(callable, new SelfDestructiveThread.ReplyCallback<TypefaceResult>() {
          public void onReply(FontsContractCompat.TypefaceResult param1TypefaceResult) {
            synchronized (FontsContractCompat.sLock) {
              ArrayList<SelfDestructiveThread.ReplyCallback<FontsContractCompat.TypefaceResult>> arrayList = (ArrayList)FontsContractCompat.sPendingReplies.get(id);
              if (arrayList == null)
                return; 
              FontsContractCompat.sPendingReplies.remove(id);
              for (int i = 0; i < arrayList.size(); i++)
                ((SelfDestructiveThread.ReplyCallback<FontsContractCompat.TypefaceResult>)arrayList.get(i)).onReply(param1TypefaceResult); 
            } 
          }
        });
    return null;
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  @VisibleForTesting
  public static ProviderInfo getProvider(@NonNull PackageManager paramPackageManager, @NonNull FontRequest paramFontRequest, @Nullable Resources paramResources) throws PackageManager.NameNotFoundException {
    String str = paramFontRequest.getProviderAuthority();
    ProviderInfo providerInfo = paramPackageManager.resolveContentProvider(str, 0);
    if (providerInfo == null)
      throw new PackageManager.NameNotFoundException("No package found for authority: " + str); 
    if (!providerInfo.packageName.equals(paramFontRequest.getProviderPackage()))
      throw new PackageManager.NameNotFoundException("Found content provider " + str + ", but package was not " + paramFontRequest.getProviderPackage()); 
    List<byte[]> list = convertToByteArrayList((paramPackageManager.getPackageInfo(providerInfo.packageName, 64)).signatures);
    Collections.sort((List)list, (Comparator)sByteArrayComparator);
    List<List<byte[]>> list1 = getCertificates(paramFontRequest, paramResources);
    for (int i = 0; i < list1.size(); i++) {
      ArrayList<byte> arrayList = new ArrayList(list1.get(i));
      Collections.sort(arrayList, (Comparator)sByteArrayComparator);
      if (equalsByteArrayList(list, (List)arrayList))
        return providerInfo; 
    } 
    return null;
  }
  
  @RequiresApi(19)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static Map<Uri, ByteBuffer> prepareFontData(Context paramContext, FontInfo[] paramArrayOfFontInfo, CancellationSignal paramCancellationSignal) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    int j = paramArrayOfFontInfo.length;
    for (int i = 0; i < j; i++) {
      FontInfo fontInfo = paramArrayOfFontInfo[i];
      if (fontInfo.getResultCode() == 0) {
        Uri uri = fontInfo.getUri();
        if (!hashMap.containsKey(uri))
          hashMap.put(uri, TypefaceCompatUtil.mmap(paramContext, paramCancellationSignal, uri)); 
      } 
    } 
    return (Map)Collections.unmodifiableMap(hashMap);
  }
  
  public static void requestFont(@NonNull final Context context, @NonNull final FontRequest request, @NonNull final FontRequestCallback callback, @NonNull Handler paramHandler) {
    paramHandler.post(new Runnable() {
          public void run() {
            try {
              FontsContractCompat.FontFamilyResult fontFamilyResult = FontsContractCompat.fetchFonts(context, null, request);
              if (fontFamilyResult.getStatusCode() != 0) {
                switch (fontFamilyResult.getStatusCode()) {
                  default:
                    callerThreadHandler.post(new Runnable() {
                          public void run() {
                            callback.onTypefaceRequestFailed(-3);
                          }
                        });
                    return;
                  case 1:
                    callerThreadHandler.post(new Runnable() {
                          public void run() {
                            callback.onTypefaceRequestFailed(-2);
                          }
                        });
                    return;
                  case 2:
                    break;
                } 
                callerThreadHandler.post(new Runnable() {
                      public void run() {
                        callback.onTypefaceRequestFailed(-3);
                      }
                    });
                return;
              } 
            } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
              callerThreadHandler.post(new Runnable() {
                    public void run() {
                      callback.onTypefaceRequestFailed(-1);
                    }
                  });
              return;
            } 
            FontsContractCompat.FontInfo[] arrayOfFontInfo = nameNotFoundException.getFonts();
            if (arrayOfFontInfo == null || arrayOfFontInfo.length == 0) {
              callerThreadHandler.post(new Runnable() {
                    public void run() {
                      callback.onTypefaceRequestFailed(1);
                    }
                  });
              return;
            } 
            int j = arrayOfFontInfo.length;
            for (final int resultCode = 0; i < j; i++) {
              FontsContractCompat.FontInfo fontInfo = arrayOfFontInfo[i];
              if (fontInfo.getResultCode() != 0) {
                i = fontInfo.getResultCode();
                if (i < 0) {
                  callerThreadHandler.post(new Runnable() {
                        public void run() {
                          callback.onTypefaceRequestFailed(-3);
                        }
                      });
                  return;
                } 
                callerThreadHandler.post(new Runnable() {
                      public void run() {
                        callback.onTypefaceRequestFailed(resultCode);
                      }
                    });
                return;
              } 
            } 
            final Typeface typeface = FontsContractCompat.buildTypeface(context, null, arrayOfFontInfo);
            if (typeface == null) {
              callerThreadHandler.post(new Runnable() {
                    public void run() {
                      callback.onTypefaceRequestFailed(-3);
                    }
                  });
              return;
            } 
            callerThreadHandler.post(new Runnable() {
                  public void run() {
                    callback.onTypefaceRetrieved(typeface);
                  }
                });
          }
        });
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static void resetCache() {
    sTypefaceCache.evictAll();
  }
  
  public static final class Columns implements BaseColumns {
    public static final String FILE_ID = "file_id";
    
    public static final String ITALIC = "font_italic";
    
    public static final String RESULT_CODE = "result_code";
    
    public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
    
    public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
    
    public static final int RESULT_CODE_MALFORMED_QUERY = 3;
    
    public static final int RESULT_CODE_OK = 0;
    
    public static final String TTC_INDEX = "font_ttc_index";
    
    public static final String VARIATION_SETTINGS = "font_variation_settings";
    
    public static final String WEIGHT = "font_weight";
  }
  
  public static class FontFamilyResult {
    public static final int STATUS_OK = 0;
    
    public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
    
    public static final int STATUS_WRONG_CERTIFICATES = 1;
    
    private final FontsContractCompat.FontInfo[] mFonts;
    
    private final int mStatusCode;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public FontFamilyResult(int param1Int, @Nullable FontsContractCompat.FontInfo[] param1ArrayOfFontInfo) {
      this.mStatusCode = param1Int;
      this.mFonts = param1ArrayOfFontInfo;
    }
    
    public FontsContractCompat.FontInfo[] getFonts() {
      return this.mFonts;
    }
    
    public int getStatusCode() {
      return this.mStatusCode;
    }
  }
  
  public static class FontInfo {
    private final boolean mItalic;
    
    private final int mResultCode;
    
    private final int mTtcIndex;
    
    private final Uri mUri;
    
    private final int mWeight;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public FontInfo(@NonNull Uri param1Uri, @IntRange(from = 0L) int param1Int1, @IntRange(from = 1L, to = 1000L) int param1Int2, boolean param1Boolean, int param1Int3) {
      this.mUri = (Uri)Preconditions.checkNotNull(param1Uri);
      this.mTtcIndex = param1Int1;
      this.mWeight = param1Int2;
      this.mItalic = param1Boolean;
      this.mResultCode = param1Int3;
    }
    
    public int getResultCode() {
      return this.mResultCode;
    }
    
    @IntRange(from = 0L)
    public int getTtcIndex() {
      return this.mTtcIndex;
    }
    
    @NonNull
    public Uri getUri() {
      return this.mUri;
    }
    
    @IntRange(from = 1L, to = 1000L)
    public int getWeight() {
      return this.mWeight;
    }
    
    public boolean isItalic() {
      return this.mItalic;
    }
  }
  
  public static class FontRequestCallback {
    public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
    
    public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
    
    public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
    
    public static final int FAIL_REASON_MALFORMED_QUERY = 3;
    
    public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
    
    public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
    
    public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int RESULT_OK = 0;
    
    public void onTypefaceRequestFailed(int param1Int) {}
    
    public void onTypefaceRetrieved(Typeface param1Typeface) {}
    
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static @interface FontRequestFailReason {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface FontRequestFailReason {}
  
  private static final class TypefaceResult {
    final int mResult;
    
    final Typeface mTypeface;
    
    TypefaceResult(@Nullable Typeface param1Typeface, int param1Int) {
      this.mTypeface = param1Typeface;
      this.mResult = param1Int;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/provider/FontsContractCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
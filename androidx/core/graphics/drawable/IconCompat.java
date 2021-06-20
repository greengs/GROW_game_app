package androidx.core.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.Preconditions;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

public class IconCompat extends CustomVersionedParcelable {
  private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25F;
  
  private static final int AMBIENT_SHADOW_ALPHA = 30;
  
  private static final float BLUR_FACTOR = 0.010416667F;
  
  static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
  
  private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667F;
  
  private static final String EXTRA_INT1 = "int1";
  
  private static final String EXTRA_INT2 = "int2";
  
  private static final String EXTRA_OBJ = "obj";
  
  private static final String EXTRA_TINT_LIST = "tint_list";
  
  private static final String EXTRA_TINT_MODE = "tint_mode";
  
  private static final String EXTRA_TYPE = "type";
  
  private static final float ICON_DIAMETER_FACTOR = 0.9166667F;
  
  private static final int KEY_SHADOW_ALPHA = 61;
  
  private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833334F;
  
  private static final String TAG = "IconCompat";
  
  public static final int TYPE_UNKNOWN = -1;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  public byte[] mData;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  public int mInt1;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  public int mInt2;
  
  Object mObj1;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  public Parcelable mParcelable;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  public ColorStateList mTintList = null;
  
  PorterDuff.Mode mTintMode = DEFAULT_TINT_MODE;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  public String mTintModeStr;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  public int mType;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  public IconCompat() {}
  
  private IconCompat(int paramInt) {
    this.mType = paramInt;
  }
  
  @Nullable
  public static IconCompat createFromBundle(@NonNull Bundle paramBundle) {
    int i = paramBundle.getInt("type");
    IconCompat iconCompat = new IconCompat(i);
    iconCompat.mInt1 = paramBundle.getInt("int1");
    iconCompat.mInt2 = paramBundle.getInt("int2");
    if (paramBundle.containsKey("tint_list"))
      iconCompat.mTintList = (ColorStateList)paramBundle.getParcelable("tint_list"); 
    if (paramBundle.containsKey("tint_mode"))
      iconCompat.mTintMode = PorterDuff.Mode.valueOf(paramBundle.getString("tint_mode")); 
    switch (i) {
      default:
        Log.w("IconCompat", "Unknown type " + i);
        return null;
      case -1:
      case 1:
      case 5:
        iconCompat.mObj1 = paramBundle.getParcelable("obj");
        return iconCompat;
      case 2:
      case 4:
        iconCompat.mObj1 = paramBundle.getString("obj");
        return iconCompat;
      case 3:
        break;
    } 
    iconCompat.mObj1 = paramBundle.getByteArray("obj");
    return iconCompat;
  }
  
  @Nullable
  @RequiresApi(23)
  public static IconCompat createFromIcon(@NonNull Context paramContext, @NonNull Icon paramIcon) {
    IconCompat iconCompat;
    String str;
    Preconditions.checkNotNull(paramIcon);
    switch (getType(paramIcon)) {
      default:
        iconCompat = new IconCompat(-1);
        iconCompat.mObj1 = paramIcon;
        return iconCompat;
      case 2:
        str = getResPackage(paramIcon);
        try {
          return createWithResource(getResources((Context)iconCompat, str), str, getResId(paramIcon));
        } catch (android.content.res.Resources.NotFoundException notFoundException) {
          throw new IllegalArgumentException("Icon resource cannot be found");
        } 
      case 4:
        break;
    } 
    return createWithContentUri(getUri(paramIcon));
  }
  
  @Nullable
  @RequiresApi(23)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static IconCompat createFromIcon(@NonNull Icon paramIcon) {
    IconCompat iconCompat;
    Preconditions.checkNotNull(paramIcon);
    switch (getType(paramIcon)) {
      default:
        iconCompat = new IconCompat(-1);
        iconCompat.mObj1 = paramIcon;
        return iconCompat;
      case 2:
        return createWithResource(null, getResPackage(paramIcon), getResId(paramIcon));
      case 4:
        break;
    } 
    return createWithContentUri(getUri(paramIcon));
  }
  
  @VisibleForTesting
  static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap paramBitmap, boolean paramBoolean) {
    int i = (int)(0.6666667F * Math.min(paramBitmap.getWidth(), paramBitmap.getHeight()));
    Bitmap bitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    Paint paint = new Paint(3);
    float f1 = i * 0.5F;
    float f2 = f1 * 0.9166667F;
    if (paramBoolean) {
      float f = 0.010416667F * i;
      paint.setColor(0);
      paint.setShadowLayer(f, 0.0F, 0.020833334F * i, 1023410176);
      canvas.drawCircle(f1, f1, f2, paint);
      paint.setShadowLayer(f, 0.0F, 0.0F, 503316480);
      canvas.drawCircle(f1, f1, f2, paint);
      paint.clearShadowLayer();
    } 
    paint.setColor(-16777216);
    BitmapShader bitmapShader = new BitmapShader(paramBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    Matrix matrix = new Matrix();
    matrix.setTranslate((-(paramBitmap.getWidth() - i) / 2), (-(paramBitmap.getHeight() - i) / 2));
    bitmapShader.setLocalMatrix(matrix);
    paint.setShader((Shader)bitmapShader);
    canvas.drawCircle(f1, f1, f2, paint);
    canvas.setBitmap(null);
    return bitmap;
  }
  
  public static IconCompat createWithAdaptiveBitmap(Bitmap paramBitmap) {
    if (paramBitmap == null)
      throw new IllegalArgumentException("Bitmap must not be null."); 
    IconCompat iconCompat = new IconCompat(5);
    iconCompat.mObj1 = paramBitmap;
    return iconCompat;
  }
  
  public static IconCompat createWithBitmap(Bitmap paramBitmap) {
    if (paramBitmap == null)
      throw new IllegalArgumentException("Bitmap must not be null."); 
    IconCompat iconCompat = new IconCompat(1);
    iconCompat.mObj1 = paramBitmap;
    return iconCompat;
  }
  
  public static IconCompat createWithContentUri(Uri paramUri) {
    if (paramUri == null)
      throw new IllegalArgumentException("Uri must not be null."); 
    return createWithContentUri(paramUri.toString());
  }
  
  public static IconCompat createWithContentUri(String paramString) {
    if (paramString == null)
      throw new IllegalArgumentException("Uri must not be null."); 
    IconCompat iconCompat = new IconCompat(4);
    iconCompat.mObj1 = paramString;
    return iconCompat;
  }
  
  public static IconCompat createWithData(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramArrayOfbyte == null)
      throw new IllegalArgumentException("Data must not be null."); 
    IconCompat iconCompat = new IconCompat(3);
    iconCompat.mObj1 = paramArrayOfbyte;
    iconCompat.mInt1 = paramInt1;
    iconCompat.mInt2 = paramInt2;
    return iconCompat;
  }
  
  public static IconCompat createWithResource(Context paramContext, @DrawableRes int paramInt) {
    if (paramContext == null)
      throw new IllegalArgumentException("Context must not be null."); 
    return createWithResource(paramContext.getResources(), paramContext.getPackageName(), paramInt);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  public static IconCompat createWithResource(Resources paramResources, String paramString, @DrawableRes int paramInt) {
    if (paramString == null)
      throw new IllegalArgumentException("Package must not be null."); 
    if (paramInt == 0)
      throw new IllegalArgumentException("Drawable resource ID must not be 0"); 
    IconCompat iconCompat = new IconCompat(2);
    iconCompat.mInt1 = paramInt;
    if (paramResources != null)
      try {
        iconCompat.mObj1 = paramResources.getResourceName(paramInt);
        return iconCompat;
      } catch (android.content.res.Resources.NotFoundException notFoundException) {
        throw new IllegalArgumentException("Icon resource cannot be found");
      }  
    iconCompat.mObj1 = paramString;
    return iconCompat;
  }
  
  @DrawableRes
  @IdRes
  @RequiresApi(23)
  private static int getResId(@NonNull Icon paramIcon) {
    if (Build.VERSION.SDK_INT >= 28)
      return paramIcon.getResId(); 
    try {
      return ((Integer)paramIcon.getClass().getMethod("getResId", new Class[0]).invoke(paramIcon, new Object[0])).intValue();
    } catch (IllegalAccessException illegalAccessException) {
      Log.e("IconCompat", "Unable to get icon resource", illegalAccessException);
      return 0;
    } catch (InvocationTargetException invocationTargetException) {
      Log.e("IconCompat", "Unable to get icon resource", invocationTargetException);
      return 0;
    } catch (NoSuchMethodException noSuchMethodException) {
      Log.e("IconCompat", "Unable to get icon resource", noSuchMethodException);
      return 0;
    } 
  }
  
  @Nullable
  @RequiresApi(23)
  private static String getResPackage(@NonNull Icon paramIcon) {
    if (Build.VERSION.SDK_INT >= 28)
      return paramIcon.getResPackage(); 
    try {
      return (String)paramIcon.getClass().getMethod("getResPackage", new Class[0]).invoke(paramIcon, new Object[0]);
    } catch (IllegalAccessException illegalAccessException) {
      Log.e("IconCompat", "Unable to get icon package", illegalAccessException);
      return null;
    } catch (InvocationTargetException invocationTargetException) {
      Log.e("IconCompat", "Unable to get icon package", invocationTargetException);
      return null;
    } catch (NoSuchMethodException noSuchMethodException) {
      Log.e("IconCompat", "Unable to get icon package", noSuchMethodException);
      return null;
    } 
  }
  
  private static Resources getResources(Context paramContext, String paramString) {
    Context context = null;
    if ("android".equals(paramString))
      return Resources.getSystem(); 
    PackageManager packageManager = paramContext.getPackageManager();
    try {
      ApplicationInfo applicationInfo = packageManager.getApplicationInfo(paramString, 8192);
      paramContext = context;
      if (applicationInfo != null)
        return packageManager.getResourcesForApplication(applicationInfo); 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.e("IconCompat", String.format("Unable to find pkg=%s for icon", new Object[] { paramString }), (Throwable)nameNotFoundException);
      return null;
    } 
    return (Resources)nameNotFoundException;
  }
  
  @RequiresApi(23)
  private static int getType(@NonNull Icon paramIcon) {
    if (Build.VERSION.SDK_INT >= 28)
      return paramIcon.getType(); 
    try {
      return ((Integer)paramIcon.getClass().getMethod("getType", new Class[0]).invoke(paramIcon, new Object[0])).intValue();
    } catch (IllegalAccessException illegalAccessException) {
      Log.e("IconCompat", "Unable to get icon type " + paramIcon, illegalAccessException);
      return -1;
    } catch (InvocationTargetException invocationTargetException) {
      Log.e("IconCompat", "Unable to get icon type " + paramIcon, invocationTargetException);
      return -1;
    } catch (NoSuchMethodException noSuchMethodException) {
      Log.e("IconCompat", "Unable to get icon type " + paramIcon, noSuchMethodException);
      return -1;
    } 
  }
  
  @Nullable
  @RequiresApi(23)
  private static Uri getUri(@NonNull Icon paramIcon) {
    if (Build.VERSION.SDK_INT >= 28)
      return paramIcon.getUri(); 
    try {
      return (Uri)paramIcon.getClass().getMethod("getUri", new Class[0]).invoke(paramIcon, new Object[0]);
    } catch (IllegalAccessException illegalAccessException) {
      Log.e("IconCompat", "Unable to get icon uri", illegalAccessException);
      return null;
    } catch (InvocationTargetException invocationTargetException) {
      Log.e("IconCompat", "Unable to get icon uri", invocationTargetException);
      return null;
    } catch (NoSuchMethodException noSuchMethodException) {
      Log.e("IconCompat", "Unable to get icon uri", noSuchMethodException);
      return null;
    } 
  }
  
  private Drawable loadDrawableInner(Context paramContext) {
    String str1;
    InputStream inputStream;
    switch (this.mType) {
      default:
        return null;
      case 1:
        return (Drawable)new BitmapDrawable(paramContext.getResources(), (Bitmap)this.mObj1);
      case 5:
        return (Drawable)new BitmapDrawable(paramContext.getResources(), createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, false));
      case 2:
        str2 = getResPackage();
        str1 = str2;
        if (TextUtils.isEmpty(str2))
          str1 = paramContext.getPackageName(); 
        resources = getResources(paramContext, str1);
        try {
          return ResourcesCompat.getDrawable(resources, this.mInt1, paramContext.getTheme());
        } catch (RuntimeException runtimeException) {
          Log.e("IconCompat", String.format("Unable to load resource 0x%08x from pkg=%s", new Object[] { Integer.valueOf(this.mInt1), this.mObj1 }), runtimeException);
        } 
      case 3:
        return (Drawable)new BitmapDrawable(runtimeException.getResources(), BitmapFactory.decodeByteArray((byte[])this.mObj1, this.mInt1, this.mInt2));
      case 4:
        break;
    } 
    Uri uri = Uri.parse((String)this.mObj1);
    String str2 = uri.getScheme();
    Resources resources = null;
    if ("content".equals(str2) || "file".equals(str2)) {
      try {
        InputStream inputStream1 = runtimeException.getContentResolver().openInputStream(uri);
        inputStream = inputStream1;
      } catch (Exception exception) {
        Log.w("IconCompat", "Unable to load image from URI: " + uri, exception);
      } 
    } else {
      try {
        FileInputStream fileInputStream = new FileInputStream(new File((String)this.mObj1));
        inputStream = fileInputStream;
      } catch (FileNotFoundException fileNotFoundException) {
        Log.w("IconCompat", "Unable to load image from path: " + uri, fileNotFoundException);
      } 
    } 
    if (inputStream != null)
      return (Drawable)new BitmapDrawable(runtimeException.getResources(), BitmapFactory.decodeStream(inputStream)); 
  }
  
  private static String typeToString(int paramInt) {
    switch (paramInt) {
      default:
        return "UNKNOWN";
      case 1:
        return "BITMAP";
      case 5:
        return "BITMAP_MASKABLE";
      case 3:
        return "DATA";
      case 2:
        return "RESOURCE";
      case 4:
        break;
    } 
    return "URI";
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void addToShortcutIntent(@NonNull Intent paramIntent, @Nullable Drawable paramDrawable, @NonNull Context paramContext) {
    Bitmap bitmap2;
    Context context;
    Bitmap bitmap1;
    Bitmap bitmap3;
    checkResource(paramContext);
    switch (this.mType) {
      default:
        throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
      case 1:
        bitmap3 = (Bitmap)this.mObj1;
        bitmap2 = bitmap3;
        if (paramDrawable != null)
          bitmap2 = bitmap3.copy(bitmap3.getConfig(), true); 
        if (paramDrawable != null) {
          int i = bitmap2.getWidth();
          int j = bitmap2.getHeight();
          paramDrawable.setBounds(i / 2, j / 2, i, j);
          paramDrawable.draw(new Canvas(bitmap2));
        } 
        paramIntent.putExtra("android.intent.extra.shortcut.ICON", (Parcelable)bitmap2);
        return;
      case 5:
        bitmap2 = createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, true);
        if (paramDrawable != null) {
          int i = bitmap2.getWidth();
          int j = bitmap2.getHeight();
          paramDrawable.setBounds(i / 2, j / 2, i, j);
          paramDrawable.draw(new Canvas(bitmap2));
        } 
        paramIntent.putExtra("android.intent.extra.shortcut.ICON", (Parcelable)bitmap2);
        return;
      case 2:
        break;
    } 
    try {
      context = bitmap2.createPackageContext(getResPackage(), 0);
      if (paramDrawable == null) {
        paramIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", (Parcelable)Intent.ShortcutIconResource.fromContext(context, this.mInt1));
        return;
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      throw new IllegalArgumentException("Can't find package " + this.mObj1, nameNotFoundException);
    } 
    Drawable drawable = ContextCompat.getDrawable(context, this.mInt1);
    if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
      int i = ((ActivityManager)context.getSystemService("activity")).getLauncherLargeIconSize();
      bitmap1 = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    } else {
      bitmap1 = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    } 
    drawable.setBounds(0, 0, bitmap1.getWidth(), bitmap1.getHeight());
    drawable.draw(new Canvas(bitmap1));
    if (paramDrawable != null) {
      int i = bitmap1.getWidth();
      int j = bitmap1.getHeight();
      paramDrawable.setBounds(i / 2, j / 2, i, j);
      paramDrawable.draw(new Canvas(bitmap1));
    } 
    nameNotFoundException.putExtra("android.intent.extra.shortcut.ICON", (Parcelable)bitmap1);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void checkResource(Context paramContext) {
    if (this.mType == 2) {
      String str = (String)this.mObj1;
      if (str.contains(":")) {
        String str2 = str.split(":", -1)[1];
        String str1 = str2.split("/", -1)[0];
        str2 = str2.split("/", -1)[1];
        str = str.split(":", -1)[0];
        int i = getResources(paramContext, str).getIdentifier(str2, str1, str);
        if (this.mInt1 != i) {
          Log.i("IconCompat", "Id has changed for " + str + "/" + str2);
          this.mInt1 = i;
          return;
        } 
      } 
    } 
  }
  
  @IdRes
  public int getResId() {
    if (this.mType == -1 && Build.VERSION.SDK_INT >= 23)
      return getResId((Icon)this.mObj1); 
    if (this.mType != 2)
      throw new IllegalStateException("called getResId() on " + this); 
    return this.mInt1;
  }
  
  @NonNull
  public String getResPackage() {
    if (this.mType == -1 && Build.VERSION.SDK_INT >= 23)
      return getResPackage((Icon)this.mObj1); 
    if (this.mType != 2)
      throw new IllegalStateException("called getResPackage() on " + this); 
    return ((String)this.mObj1).split(":", -1)[0];
  }
  
  public int getType() {
    return (this.mType == -1 && Build.VERSION.SDK_INT >= 23) ? getType((Icon)this.mObj1) : this.mType;
  }
  
  @NonNull
  public Uri getUri() {
    return (this.mType == -1 && Build.VERSION.SDK_INT >= 23) ? getUri((Icon)this.mObj1) : Uri.parse((String)this.mObj1);
  }
  
  public Drawable loadDrawable(Context paramContext) {
    checkResource(paramContext);
    if (Build.VERSION.SDK_INT >= 23)
      return toIcon().loadDrawable(paramContext); 
    Drawable drawable2 = loadDrawableInner(paramContext);
    Drawable drawable1 = drawable2;
    if (drawable2 != null) {
      if (this.mTintList == null) {
        drawable1 = drawable2;
        if (this.mTintMode != DEFAULT_TINT_MODE) {
          drawable2.mutate();
          DrawableCompat.setTintList(drawable2, this.mTintList);
          DrawableCompat.setTintMode(drawable2, this.mTintMode);
          return drawable2;
        } 
        return drawable1;
      } 
      drawable2.mutate();
      DrawableCompat.setTintList(drawable2, this.mTintList);
      DrawableCompat.setTintMode(drawable2, this.mTintMode);
      return drawable2;
    } 
    return drawable1;
  }
  
  public void onPostParceling() {
    this.mTintMode = PorterDuff.Mode.valueOf(this.mTintModeStr);
    switch (this.mType) {
      default:
        return;
      case -1:
        if (this.mParcelable != null) {
          this.mObj1 = this.mParcelable;
          return;
        } 
        throw new IllegalArgumentException("Invalid icon");
      case 1:
      case 5:
        if (this.mParcelable != null) {
          this.mObj1 = this.mParcelable;
          return;
        } 
        this.mObj1 = this.mData;
        this.mType = 3;
        this.mInt1 = 0;
        this.mInt2 = this.mData.length;
        return;
      case 2:
      case 4:
        this.mObj1 = new String(this.mData, Charset.forName("UTF-16"));
        return;
      case 3:
        break;
    } 
    this.mObj1 = this.mData;
  }
  
  public void onPreParceling(boolean paramBoolean) {
    this.mTintModeStr = this.mTintMode.name();
    switch (this.mType) {
      default:
        return;
      case -1:
        if (paramBoolean)
          throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon"); 
        this.mParcelable = (Parcelable)this.mObj1;
        return;
      case 1:
      case 5:
        if (paramBoolean) {
          Bitmap bitmap = (Bitmap)this.mObj1;
          ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
          bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
          this.mData = byteArrayOutputStream.toByteArray();
          return;
        } 
        this.mParcelable = (Parcelable)this.mObj1;
        return;
      case 4:
        this.mData = this.mObj1.toString().getBytes(Charset.forName("UTF-16"));
        return;
      case 2:
        this.mData = ((String)this.mObj1).getBytes(Charset.forName("UTF-16"));
        return;
      case 3:
        break;
    } 
    this.mData = (byte[])this.mObj1;
  }
  
  public IconCompat setTint(@ColorInt int paramInt) {
    return setTintList(ColorStateList.valueOf(paramInt));
  }
  
  public IconCompat setTintList(ColorStateList paramColorStateList) {
    this.mTintList = paramColorStateList;
    return this;
  }
  
  public IconCompat setTintMode(PorterDuff.Mode paramMode) {
    this.mTintMode = paramMode;
    return this;
  }
  
  public Bundle toBundle() {
    // Byte code:
    //   0: new android/os/Bundle
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: aload_0
    //   9: getfield mType : I
    //   12: tableswitch default -> 56, -1 -> 152, 0 -> 56, 1 -> 67, 2 -> 168, 3 -> 184, 4 -> 168, 5 -> 67
    //   56: new java/lang/IllegalArgumentException
    //   59: dup
    //   60: ldc_w 'Invalid icon'
    //   63: invokespecial <init> : (Ljava/lang/String;)V
    //   66: athrow
    //   67: aload_1
    //   68: ldc 'obj'
    //   70: aload_0
    //   71: getfield mObj1 : Ljava/lang/Object;
    //   74: checkcast android/graphics/Bitmap
    //   77: invokevirtual putParcelable : (Ljava/lang/String;Landroid/os/Parcelable;)V
    //   80: aload_1
    //   81: ldc 'type'
    //   83: aload_0
    //   84: getfield mType : I
    //   87: invokevirtual putInt : (Ljava/lang/String;I)V
    //   90: aload_1
    //   91: ldc 'int1'
    //   93: aload_0
    //   94: getfield mInt1 : I
    //   97: invokevirtual putInt : (Ljava/lang/String;I)V
    //   100: aload_1
    //   101: ldc 'int2'
    //   103: aload_0
    //   104: getfield mInt2 : I
    //   107: invokevirtual putInt : (Ljava/lang/String;I)V
    //   110: aload_0
    //   111: getfield mTintList : Landroid/content/res/ColorStateList;
    //   114: ifnull -> 127
    //   117: aload_1
    //   118: ldc 'tint_list'
    //   120: aload_0
    //   121: getfield mTintList : Landroid/content/res/ColorStateList;
    //   124: invokevirtual putParcelable : (Ljava/lang/String;Landroid/os/Parcelable;)V
    //   127: aload_0
    //   128: getfield mTintMode : Landroid/graphics/PorterDuff$Mode;
    //   131: getstatic androidx/core/graphics/drawable/IconCompat.DEFAULT_TINT_MODE : Landroid/graphics/PorterDuff$Mode;
    //   134: if_acmpeq -> 150
    //   137: aload_1
    //   138: ldc 'tint_mode'
    //   140: aload_0
    //   141: getfield mTintMode : Landroid/graphics/PorterDuff$Mode;
    //   144: invokevirtual name : ()Ljava/lang/String;
    //   147: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   150: aload_1
    //   151: areturn
    //   152: aload_1
    //   153: ldc 'obj'
    //   155: aload_0
    //   156: getfield mObj1 : Ljava/lang/Object;
    //   159: checkcast android/os/Parcelable
    //   162: invokevirtual putParcelable : (Ljava/lang/String;Landroid/os/Parcelable;)V
    //   165: goto -> 80
    //   168: aload_1
    //   169: ldc 'obj'
    //   171: aload_0
    //   172: getfield mObj1 : Ljava/lang/Object;
    //   175: checkcast java/lang/String
    //   178: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   181: goto -> 80
    //   184: aload_1
    //   185: ldc 'obj'
    //   187: aload_0
    //   188: getfield mObj1 : Ljava/lang/Object;
    //   191: checkcast [B
    //   194: checkcast [B
    //   197: invokevirtual putByteArray : (Ljava/lang/String;[B)V
    //   200: goto -> 80
  }
  
  @RequiresApi(23)
  public Icon toIcon() {
    switch (this.mType) {
      default:
        throw new IllegalArgumentException("Unknown type");
      case -1:
        return (Icon)this.mObj1;
      case 1:
        icon = Icon.createWithBitmap((Bitmap)this.mObj1);
        if (this.mTintList != null)
          icon.setTintList(this.mTintList); 
        if (this.mTintMode != DEFAULT_TINT_MODE)
          icon.setTintMode(this.mTintMode); 
        return icon;
      case 5:
        if (Build.VERSION.SDK_INT >= 26) {
          icon = Icon.createWithAdaptiveBitmap((Bitmap)this.mObj1);
        } else {
          icon = Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, false));
        } 
        if (this.mTintList != null)
          icon.setTintList(this.mTintList); 
        if (this.mTintMode != DEFAULT_TINT_MODE)
          icon.setTintMode(this.mTintMode); 
        return icon;
      case 2:
        icon = Icon.createWithResource(getResPackage(), this.mInt1);
        if (this.mTintList != null)
          icon.setTintList(this.mTintList); 
        if (this.mTintMode != DEFAULT_TINT_MODE)
          icon.setTintMode(this.mTintMode); 
        return icon;
      case 3:
        icon = Icon.createWithData((byte[])this.mObj1, this.mInt1, this.mInt2);
        if (this.mTintList != null)
          icon.setTintList(this.mTintList); 
        if (this.mTintMode != DEFAULT_TINT_MODE)
          icon.setTintMode(this.mTintMode); 
        return icon;
      case 4:
        break;
    } 
    Icon icon = Icon.createWithContentUri((String)this.mObj1);
    if (this.mTintList != null)
      icon.setTintList(this.mTintList); 
    if (this.mTintMode != DEFAULT_TINT_MODE)
      icon.setTintMode(this.mTintMode); 
    return icon;
  }
  
  public String toString() {
    if (this.mType == -1)
      return String.valueOf(this.mObj1); 
    StringBuilder stringBuilder = (new StringBuilder("Icon(typ=")).append(typeToString(this.mType));
    switch (this.mType) {
      default:
        if (this.mTintList != null) {
          stringBuilder.append(" tint=");
          stringBuilder.append(this.mTintList);
        } 
        if (this.mTintMode != DEFAULT_TINT_MODE)
          stringBuilder.append(" mode=").append(this.mTintMode); 
        stringBuilder.append(")");
        return stringBuilder.toString();
      case 1:
      case 5:
        stringBuilder.append(" size=").append(((Bitmap)this.mObj1).getWidth()).append("x").append(((Bitmap)this.mObj1).getHeight());
      case 2:
        stringBuilder.append(" pkg=").append(getResPackage()).append(" id=").append(String.format("0x%08x", new Object[] { Integer.valueOf(getResId()) }));
      case 3:
        stringBuilder.append(" len=").append(this.mInt1);
        if (this.mInt2 != 0)
          stringBuilder.append(" off=").append(this.mInt2); 
      case 4:
        break;
    } 
    stringBuilder.append(" uri=").append(this.mObj1);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  public static @interface IconType {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/drawable/IconCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
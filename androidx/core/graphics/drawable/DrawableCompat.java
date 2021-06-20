package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class DrawableCompat {
  private static final String TAG = "DrawableCompat";
  
  private static Method sGetLayoutDirectionMethod;
  
  private static boolean sGetLayoutDirectionMethodFetched;
  
  private static Method sSetLayoutDirectionMethod;
  
  private static boolean sSetLayoutDirectionMethodFetched;
  
  public static void applyTheme(@NonNull Drawable paramDrawable, @NonNull Resources.Theme paramTheme) {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.applyTheme(paramTheme); 
  }
  
  public static boolean canApplyTheme(@NonNull Drawable paramDrawable) {
    return (Build.VERSION.SDK_INT >= 21) ? paramDrawable.canApplyTheme() : false;
  }
  
  public static void clearColorFilter(@NonNull Drawable paramDrawable) {
    DrawableContainer.DrawableContainerState drawableContainerState;
    if (Build.VERSION.SDK_INT >= 23) {
      paramDrawable.clearColorFilter();
      return;
    } 
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.clearColorFilter();
      if (paramDrawable instanceof InsetDrawable) {
        clearColorFilter(((InsetDrawable)paramDrawable).getDrawable());
        return;
      } 
      if (paramDrawable instanceof WrappedDrawable) {
        clearColorFilter(((WrappedDrawable)paramDrawable).getWrappedDrawable());
        return;
      } 
      if (paramDrawable instanceof DrawableContainer) {
        drawableContainerState = (DrawableContainer.DrawableContainerState)((DrawableContainer)paramDrawable).getConstantState();
        if (drawableContainerState != null) {
          int i = 0;
          int j = drawableContainerState.getChildCount();
          while (true) {
            if (i < j) {
              Drawable drawable = drawableContainerState.getChild(i);
              if (drawable != null)
                clearColorFilter(drawable); 
              i++;
              continue;
            } 
            return;
          } 
        } 
      } 
      return;
    } 
    drawableContainerState.clearColorFilter();
  }
  
  public static int getAlpha(@NonNull Drawable paramDrawable) {
    return (Build.VERSION.SDK_INT >= 19) ? paramDrawable.getAlpha() : 0;
  }
  
  public static ColorFilter getColorFilter(@NonNull Drawable paramDrawable) {
    return (Build.VERSION.SDK_INT >= 21) ? paramDrawable.getColorFilter() : null;
  }
  
  public static int getLayoutDirection(@NonNull Drawable paramDrawable) {
    // Byte code:
    //   0: getstatic android/os/Build$VERSION.SDK_INT : I
    //   3: bipush #23
    //   5: if_icmplt -> 13
    //   8: aload_0
    //   9: invokevirtual getLayoutDirection : ()I
    //   12: ireturn
    //   13: getstatic android/os/Build$VERSION.SDK_INT : I
    //   16: bipush #17
    //   18: if_icmplt -> 107
    //   21: getstatic androidx/core/graphics/drawable/DrawableCompat.sGetLayoutDirectionMethodFetched : Z
    //   24: ifne -> 52
    //   27: ldc android/graphics/drawable/Drawable
    //   29: ldc 'getLayoutDirection'
    //   31: iconst_0
    //   32: anewarray java/lang/Class
    //   35: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   38: putstatic androidx/core/graphics/drawable/DrawableCompat.sGetLayoutDirectionMethod : Ljava/lang/reflect/Method;
    //   41: getstatic androidx/core/graphics/drawable/DrawableCompat.sGetLayoutDirectionMethod : Ljava/lang/reflect/Method;
    //   44: iconst_1
    //   45: invokevirtual setAccessible : (Z)V
    //   48: iconst_1
    //   49: putstatic androidx/core/graphics/drawable/DrawableCompat.sGetLayoutDirectionMethodFetched : Z
    //   52: getstatic androidx/core/graphics/drawable/DrawableCompat.sGetLayoutDirectionMethod : Ljava/lang/reflect/Method;
    //   55: ifnull -> 105
    //   58: getstatic androidx/core/graphics/drawable/DrawableCompat.sGetLayoutDirectionMethod : Ljava/lang/reflect/Method;
    //   61: aload_0
    //   62: iconst_0
    //   63: anewarray java/lang/Object
    //   66: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   69: checkcast java/lang/Integer
    //   72: invokevirtual intValue : ()I
    //   75: istore_1
    //   76: iload_1
    //   77: ireturn
    //   78: astore_2
    //   79: ldc 'DrawableCompat'
    //   81: ldc 'Failed to retrieve getLayoutDirection() method'
    //   83: aload_2
    //   84: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   87: pop
    //   88: goto -> 48
    //   91: astore_0
    //   92: ldc 'DrawableCompat'
    //   94: ldc 'Failed to invoke getLayoutDirection() via reflection'
    //   96: aload_0
    //   97: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   100: pop
    //   101: aconst_null
    //   102: putstatic androidx/core/graphics/drawable/DrawableCompat.sGetLayoutDirectionMethod : Ljava/lang/reflect/Method;
    //   105: iconst_0
    //   106: ireturn
    //   107: iconst_0
    //   108: ireturn
    // Exception table:
    //   from	to	target	type
    //   27	48	78	java/lang/NoSuchMethodException
    //   58	76	91	java/lang/Exception
  }
  
  public static void inflate(@NonNull Drawable paramDrawable, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return;
    } 
    paramDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet);
  }
  
  public static boolean isAutoMirrored(@NonNull Drawable paramDrawable) {
    return (Build.VERSION.SDK_INT >= 19) ? paramDrawable.isAutoMirrored() : false;
  }
  
  @Deprecated
  public static void jumpToCurrentState(@NonNull Drawable paramDrawable) {
    paramDrawable.jumpToCurrentState();
  }
  
  public static void setAutoMirrored(@NonNull Drawable paramDrawable, boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 19)
      paramDrawable.setAutoMirrored(paramBoolean); 
  }
  
  public static void setHotspot(@NonNull Drawable paramDrawable, float paramFloat1, float paramFloat2) {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.setHotspot(paramFloat1, paramFloat2); 
  }
  
  public static void setHotspotBounds(@NonNull Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4); 
  }
  
  public static boolean setLayoutDirection(@NonNull Drawable paramDrawable, int paramInt) {
    if (Build.VERSION.SDK_INT >= 23)
      return paramDrawable.setLayoutDirection(paramInt); 
    if (Build.VERSION.SDK_INT >= 17) {
      if (!sSetLayoutDirectionMethodFetched) {
        try {
          sSetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("setLayoutDirection", new Class[] { int.class });
          sSetLayoutDirectionMethod.setAccessible(true);
        } catch (NoSuchMethodException noSuchMethodException) {
          Log.i("DrawableCompat", "Failed to retrieve setLayoutDirection(int) method", noSuchMethodException);
        } 
        sSetLayoutDirectionMethodFetched = true;
      } 
      if (sSetLayoutDirectionMethod != null)
        try {
          sSetLayoutDirectionMethod.invoke(paramDrawable, new Object[] { Integer.valueOf(paramInt) });
          return true;
        } catch (Exception exception) {
          Log.i("DrawableCompat", "Failed to invoke setLayoutDirection(int) via reflection", exception);
          sSetLayoutDirectionMethod = null;
        }  
      return false;
    } 
    return false;
  }
  
  public static void setTint(@NonNull Drawable paramDrawable, @ColorInt int paramInt) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.setTint(paramInt);
      return;
    } 
    if (paramDrawable instanceof TintAwareDrawable) {
      ((TintAwareDrawable)paramDrawable).setTint(paramInt);
      return;
    } 
  }
  
  public static void setTintList(@NonNull Drawable paramDrawable, @Nullable ColorStateList paramColorStateList) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.setTintList(paramColorStateList);
      return;
    } 
    if (paramDrawable instanceof TintAwareDrawable) {
      ((TintAwareDrawable)paramDrawable).setTintList(paramColorStateList);
      return;
    } 
  }
  
  public static void setTintMode(@NonNull Drawable paramDrawable, @NonNull PorterDuff.Mode paramMode) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.setTintMode(paramMode);
      return;
    } 
    if (paramDrawable instanceof TintAwareDrawable) {
      ((TintAwareDrawable)paramDrawable).setTintMode(paramMode);
      return;
    } 
  }
  
  public static <T extends Drawable> T unwrap(@NonNull Drawable paramDrawable) {
    Drawable drawable = paramDrawable;
    if (paramDrawable instanceof WrappedDrawable)
      drawable = ((WrappedDrawable)paramDrawable).getWrappedDrawable(); 
    return (T)drawable;
  }
  
  public static Drawable wrap(@NonNull Drawable paramDrawable) {
    if (Build.VERSION.SDK_INT < 23) {
      if (Build.VERSION.SDK_INT >= 21)
        return !(paramDrawable instanceof TintAwareDrawable) ? new WrappedDrawableApi21(paramDrawable) : paramDrawable; 
      if (!(paramDrawable instanceof TintAwareDrawable))
        return new WrappedDrawableApi14(paramDrawable); 
    } 
    return paramDrawable;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/drawable/DrawableCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
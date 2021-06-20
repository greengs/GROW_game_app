package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Field;

public final class CompoundButtonCompat {
  private static final String TAG = "CompoundButtonCompat";
  
  private static Field sButtonDrawableField;
  
  private static boolean sButtonDrawableFieldFetched;
  
  @Nullable
  public static Drawable getButtonDrawable(@NonNull CompoundButton paramCompoundButton) {
    // Byte code:
    //   0: getstatic android/os/Build$VERSION.SDK_INT : I
    //   3: bipush #23
    //   5: if_icmplt -> 13
    //   8: aload_0
    //   9: invokevirtual getButtonDrawable : ()Landroid/graphics/drawable/Drawable;
    //   12: areturn
    //   13: getstatic androidx/core/widget/CompoundButtonCompat.sButtonDrawableFieldFetched : Z
    //   16: ifne -> 40
    //   19: ldc android/widget/CompoundButton
    //   21: ldc 'mButtonDrawable'
    //   23: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   26: putstatic androidx/core/widget/CompoundButtonCompat.sButtonDrawableField : Ljava/lang/reflect/Field;
    //   29: getstatic androidx/core/widget/CompoundButtonCompat.sButtonDrawableField : Ljava/lang/reflect/Field;
    //   32: iconst_1
    //   33: invokevirtual setAccessible : (Z)V
    //   36: iconst_1
    //   37: putstatic androidx/core/widget/CompoundButtonCompat.sButtonDrawableFieldFetched : Z
    //   40: getstatic androidx/core/widget/CompoundButtonCompat.sButtonDrawableField : Ljava/lang/reflect/Field;
    //   43: ifnull -> 86
    //   46: getstatic androidx/core/widget/CompoundButtonCompat.sButtonDrawableField : Ljava/lang/reflect/Field;
    //   49: aload_0
    //   50: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   53: checkcast android/graphics/drawable/Drawable
    //   56: astore_0
    //   57: aload_0
    //   58: areturn
    //   59: astore_1
    //   60: ldc 'CompoundButtonCompat'
    //   62: ldc 'Failed to retrieve mButtonDrawable field'
    //   64: aload_1
    //   65: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   68: pop
    //   69: goto -> 36
    //   72: astore_0
    //   73: ldc 'CompoundButtonCompat'
    //   75: ldc 'Failed to get button drawable via reflection'
    //   77: aload_0
    //   78: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   81: pop
    //   82: aconst_null
    //   83: putstatic androidx/core/widget/CompoundButtonCompat.sButtonDrawableField : Ljava/lang/reflect/Field;
    //   86: aconst_null
    //   87: areturn
    // Exception table:
    //   from	to	target	type
    //   19	36	59	java/lang/NoSuchFieldException
    //   46	57	72	java/lang/IllegalAccessException
  }
  
  @Nullable
  public static ColorStateList getButtonTintList(@NonNull CompoundButton paramCompoundButton) {
    return (Build.VERSION.SDK_INT >= 21) ? paramCompoundButton.getButtonTintList() : ((paramCompoundButton instanceof TintableCompoundButton) ? ((TintableCompoundButton)paramCompoundButton).getSupportButtonTintList() : null);
  }
  
  @Nullable
  public static PorterDuff.Mode getButtonTintMode(@NonNull CompoundButton paramCompoundButton) {
    return (Build.VERSION.SDK_INT >= 21) ? paramCompoundButton.getButtonTintMode() : ((paramCompoundButton instanceof TintableCompoundButton) ? ((TintableCompoundButton)paramCompoundButton).getSupportButtonTintMode() : null);
  }
  
  public static void setButtonTintList(@NonNull CompoundButton paramCompoundButton, @Nullable ColorStateList paramColorStateList) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramCompoundButton.setButtonTintList(paramColorStateList);
      return;
    } 
    if (paramCompoundButton instanceof TintableCompoundButton) {
      ((TintableCompoundButton)paramCompoundButton).setSupportButtonTintList(paramColorStateList);
      return;
    } 
  }
  
  public static void setButtonTintMode(@NonNull CompoundButton paramCompoundButton, @Nullable PorterDuff.Mode paramMode) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramCompoundButton.setButtonTintMode(paramMode);
      return;
    } 
    if (paramCompoundButton instanceof TintableCompoundButton) {
      ((TintableCompoundButton)paramCompoundButton).setSupportButtonTintMode(paramMode);
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/widget/CompoundButtonCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
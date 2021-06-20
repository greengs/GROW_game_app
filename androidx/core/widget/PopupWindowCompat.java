package androidx.core.widget;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat {
  private static final String TAG = "PopupWindowCompatApi21";
  
  private static Method sGetWindowLayoutTypeMethod;
  
  private static boolean sGetWindowLayoutTypeMethodAttempted;
  
  private static Field sOverlapAnchorField;
  
  private static boolean sOverlapAnchorFieldAttempted;
  
  private static Method sSetWindowLayoutTypeMethod;
  
  private static boolean sSetWindowLayoutTypeMethodAttempted;
  
  public static boolean getOverlapAnchor(@NonNull PopupWindow paramPopupWindow) {
    // Byte code:
    //   0: getstatic android/os/Build$VERSION.SDK_INT : I
    //   3: bipush #23
    //   5: if_icmplt -> 13
    //   8: aload_0
    //   9: invokevirtual getOverlapAnchor : ()Z
    //   12: ireturn
    //   13: getstatic android/os/Build$VERSION.SDK_INT : I
    //   16: bipush #21
    //   18: if_icmplt -> 93
    //   21: getstatic androidx/core/widget/PopupWindowCompat.sOverlapAnchorFieldAttempted : Z
    //   24: ifne -> 48
    //   27: ldc android/widget/PopupWindow
    //   29: ldc 'mOverlapAnchor'
    //   31: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   34: putstatic androidx/core/widget/PopupWindowCompat.sOverlapAnchorField : Ljava/lang/reflect/Field;
    //   37: getstatic androidx/core/widget/PopupWindowCompat.sOverlapAnchorField : Ljava/lang/reflect/Field;
    //   40: iconst_1
    //   41: invokevirtual setAccessible : (Z)V
    //   44: iconst_1
    //   45: putstatic androidx/core/widget/PopupWindowCompat.sOverlapAnchorFieldAttempted : Z
    //   48: getstatic androidx/core/widget/PopupWindowCompat.sOverlapAnchorField : Ljava/lang/reflect/Field;
    //   51: ifnull -> 93
    //   54: getstatic androidx/core/widget/PopupWindowCompat.sOverlapAnchorField : Ljava/lang/reflect/Field;
    //   57: aload_0
    //   58: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   61: checkcast java/lang/Boolean
    //   64: invokevirtual booleanValue : ()Z
    //   67: istore_1
    //   68: iload_1
    //   69: ireturn
    //   70: astore_2
    //   71: ldc 'PopupWindowCompatApi21'
    //   73: ldc 'Could not fetch mOverlapAnchor field from PopupWindow'
    //   75: aload_2
    //   76: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   79: pop
    //   80: goto -> 44
    //   83: astore_0
    //   84: ldc 'PopupWindowCompatApi21'
    //   86: ldc 'Could not get overlap anchor field in PopupWindow'
    //   88: aload_0
    //   89: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   92: pop
    //   93: iconst_0
    //   94: ireturn
    // Exception table:
    //   from	to	target	type
    //   27	44	70	java/lang/NoSuchFieldException
    //   54	68	83	java/lang/IllegalAccessException
  }
  
  public static int getWindowLayoutType(@NonNull PopupWindow paramPopupWindow) {
    if (Build.VERSION.SDK_INT >= 23)
      return paramPopupWindow.getWindowLayoutType(); 
    if (!sGetWindowLayoutTypeMethodAttempted) {
      try {
        sGetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("getWindowLayoutType", new Class[0]);
        sGetWindowLayoutTypeMethod.setAccessible(true);
      } catch (Exception exception) {}
      sGetWindowLayoutTypeMethodAttempted = true;
    } 
    if (sGetWindowLayoutTypeMethod != null)
      try {
        return ((Integer)sGetWindowLayoutTypeMethod.invoke(paramPopupWindow, new Object[0])).intValue();
      } catch (Exception exception) {} 
    return 0;
  }
  
  public static void setOverlapAnchor(@NonNull PopupWindow paramPopupWindow, boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 23) {
      paramPopupWindow.setOverlapAnchor(paramBoolean);
      return;
    } 
    if (Build.VERSION.SDK_INT >= 21) {
      if (!sOverlapAnchorFieldAttempted) {
        try {
          sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
          sOverlapAnchorField.setAccessible(true);
        } catch (NoSuchFieldException noSuchFieldException) {
          Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", noSuchFieldException);
        } 
        sOverlapAnchorFieldAttempted = true;
      } 
      if (sOverlapAnchorField != null)
        try {
          sOverlapAnchorField.set(paramPopupWindow, Boolean.valueOf(paramBoolean));
          return;
        } catch (IllegalAccessException illegalAccessException) {
          Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", illegalAccessException);
          return;
        }  
    } 
  }
  
  public static void setWindowLayoutType(@NonNull PopupWindow paramPopupWindow, int paramInt) {
    if (Build.VERSION.SDK_INT >= 23) {
      paramPopupWindow.setWindowLayoutType(paramInt);
      return;
    } 
    if (!sSetWindowLayoutTypeMethodAttempted) {
      try {
        sSetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", new Class[] { int.class });
        sSetWindowLayoutTypeMethod.setAccessible(true);
      } catch (Exception exception) {}
      sSetWindowLayoutTypeMethodAttempted = true;
    } 
    if (sSetWindowLayoutTypeMethod != null)
      try {
        sSetWindowLayoutTypeMethod.invoke(paramPopupWindow, new Object[] { Integer.valueOf(paramInt) });
        return;
      } catch (Exception exception) {
        return;
      }  
  }
  
  public static void showAsDropDown(@NonNull PopupWindow paramPopupWindow, @NonNull View paramView, int paramInt1, int paramInt2, int paramInt3) {
    if (Build.VERSION.SDK_INT >= 19) {
      paramPopupWindow.showAsDropDown(paramView, paramInt1, paramInt2, paramInt3);
      return;
    } 
    int i = paramInt1;
    if ((GravityCompat.getAbsoluteGravity(paramInt3, ViewCompat.getLayoutDirection(paramView)) & 0x7) == 5)
      i = paramInt1 - paramPopupWindow.getWidth() - paramView.getWidth(); 
    paramPopupWindow.showAsDropDown(paramView, i, paramInt2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/widget/PopupWindowCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
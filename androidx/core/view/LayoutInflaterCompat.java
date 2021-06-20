package androidx.core.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import java.lang.reflect.Field;

public final class LayoutInflaterCompat {
  private static final String TAG = "LayoutInflaterCompatHC";
  
  private static boolean sCheckedField;
  
  private static Field sLayoutInflaterFactory2Field;
  
  private static void forceSetFactory2(LayoutInflater paramLayoutInflater, LayoutInflater.Factory2 paramFactory2) {
    if (!sCheckedField) {
      try {
        sLayoutInflaterFactory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
        sLayoutInflaterFactory2Field.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.e("LayoutInflaterCompatHC", "forceSetFactory2 Could not find field 'mFactory2' on class " + LayoutInflater.class.getName() + "; inflation may have unexpected results.", noSuchFieldException);
      } 
      sCheckedField = true;
    } 
    if (sLayoutInflaterFactory2Field != null)
      try {
        sLayoutInflaterFactory2Field.set(paramLayoutInflater, paramFactory2);
        return;
      } catch (IllegalAccessException illegalAccessException) {
        Log.e("LayoutInflaterCompatHC", "forceSetFactory2 could not set the Factory2 on LayoutInflater " + paramLayoutInflater + "; inflation may have unexpected results.", illegalAccessException);
        return;
      }  
  }
  
  @Deprecated
  public static LayoutInflaterFactory getFactory(LayoutInflater paramLayoutInflater) {
    LayoutInflater.Factory factory = paramLayoutInflater.getFactory();
    return (factory instanceof Factory2Wrapper) ? ((Factory2Wrapper)factory).mDelegateFactory : null;
  }
  
  @Deprecated
  public static void setFactory(@NonNull LayoutInflater paramLayoutInflater, @NonNull LayoutInflaterFactory paramLayoutInflaterFactory) {
    Factory2Wrapper factory2Wrapper = null;
    if (Build.VERSION.SDK_INT >= 21) {
      if (paramLayoutInflaterFactory != null)
        factory2Wrapper = new Factory2Wrapper(paramLayoutInflaterFactory); 
      paramLayoutInflater.setFactory2(factory2Wrapper);
      return;
    } 
    if (paramLayoutInflaterFactory != null) {
      Factory2Wrapper factory2Wrapper1 = new Factory2Wrapper(paramLayoutInflaterFactory);
    } else {
      paramLayoutInflaterFactory = null;
    } 
    paramLayoutInflater.setFactory2((LayoutInflater.Factory2)paramLayoutInflaterFactory);
    LayoutInflater.Factory factory = paramLayoutInflater.getFactory();
    if (factory instanceof LayoutInflater.Factory2) {
      forceSetFactory2(paramLayoutInflater, (LayoutInflater.Factory2)factory);
      return;
    } 
    forceSetFactory2(paramLayoutInflater, (LayoutInflater.Factory2)paramLayoutInflaterFactory);
  }
  
  public static void setFactory2(@NonNull LayoutInflater paramLayoutInflater, @NonNull LayoutInflater.Factory2 paramFactory2) {
    paramLayoutInflater.setFactory2(paramFactory2);
    if (Build.VERSION.SDK_INT < 21) {
      LayoutInflater.Factory factory = paramLayoutInflater.getFactory();
      if (factory instanceof LayoutInflater.Factory2) {
        forceSetFactory2(paramLayoutInflater, (LayoutInflater.Factory2)factory);
        return;
      } 
    } else {
      return;
    } 
    forceSetFactory2(paramLayoutInflater, paramFactory2);
  }
  
  static class Factory2Wrapper implements LayoutInflater.Factory2 {
    final LayoutInflaterFactory mDelegateFactory;
    
    Factory2Wrapper(LayoutInflaterFactory param1LayoutInflaterFactory) {
      this.mDelegateFactory = param1LayoutInflaterFactory;
    }
    
    public View onCreateView(View param1View, String param1String, Context param1Context, AttributeSet param1AttributeSet) {
      return this.mDelegateFactory.onCreateView(param1View, param1String, param1Context, param1AttributeSet);
    }
    
    public View onCreateView(String param1String, Context param1Context, AttributeSet param1AttributeSet) {
      return this.mDelegateFactory.onCreateView(null, param1String, param1Context, param1AttributeSet);
    }
    
    public String toString() {
      return getClass().getName() + "{" + this.mDelegateFactory + "}";
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/LayoutInflaterCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
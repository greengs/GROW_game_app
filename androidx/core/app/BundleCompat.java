package androidx.core.app;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Method;

public final class BundleCompat {
  @Nullable
  public static IBinder getBinder(@NonNull Bundle paramBundle, @Nullable String paramString) {
    return (Build.VERSION.SDK_INT >= 18) ? paramBundle.getBinder(paramString) : BundleCompatBaseImpl.getBinder(paramBundle, paramString);
  }
  
  public static void putBinder(@NonNull Bundle paramBundle, @Nullable String paramString, @Nullable IBinder paramIBinder) {
    if (Build.VERSION.SDK_INT >= 18) {
      paramBundle.putBinder(paramString, paramIBinder);
      return;
    } 
    BundleCompatBaseImpl.putBinder(paramBundle, paramString, paramIBinder);
  }
  
  static class BundleCompatBaseImpl {
    private static final String TAG = "BundleCompatBaseImpl";
    
    private static Method sGetIBinderMethod;
    
    private static boolean sGetIBinderMethodFetched;
    
    private static Method sPutIBinderMethod;
    
    private static boolean sPutIBinderMethodFetched;
    
    public static IBinder getBinder(Bundle param1Bundle, String param1String) {
      // Byte code:
      //   0: getstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sGetIBinderMethodFetched : Z
      //   3: ifne -> 36
      //   6: ldc android/os/Bundle
      //   8: ldc 'getIBinder'
      //   10: iconst_1
      //   11: anewarray java/lang/Class
      //   14: dup
      //   15: iconst_0
      //   16: ldc java/lang/String
      //   18: aastore
      //   19: invokevirtual getMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   22: putstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sGetIBinderMethod : Ljava/lang/reflect/Method;
      //   25: getstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sGetIBinderMethod : Ljava/lang/reflect/Method;
      //   28: iconst_1
      //   29: invokevirtual setAccessible : (Z)V
      //   32: iconst_1
      //   33: putstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sGetIBinderMethodFetched : Z
      //   36: getstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sGetIBinderMethod : Ljava/lang/reflect/Method;
      //   39: ifnull -> 90
      //   42: getstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sGetIBinderMethod : Ljava/lang/reflect/Method;
      //   45: aload_0
      //   46: iconst_1
      //   47: anewarray java/lang/Object
      //   50: dup
      //   51: iconst_0
      //   52: aload_1
      //   53: aastore
      //   54: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   57: checkcast android/os/IBinder
      //   60: astore_0
      //   61: aload_0
      //   62: areturn
      //   63: astore_2
      //   64: ldc 'BundleCompatBaseImpl'
      //   66: ldc 'Failed to retrieve getIBinder method'
      //   68: aload_2
      //   69: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   72: pop
      //   73: goto -> 32
      //   76: astore_0
      //   77: ldc 'BundleCompatBaseImpl'
      //   79: ldc 'Failed to invoke getIBinder via reflection'
      //   81: aload_0
      //   82: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   85: pop
      //   86: aconst_null
      //   87: putstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sGetIBinderMethod : Ljava/lang/reflect/Method;
      //   90: aconst_null
      //   91: areturn
      //   92: astore_0
      //   93: goto -> 77
      //   96: astore_0
      //   97: goto -> 77
      // Exception table:
      //   from	to	target	type
      //   6	32	63	java/lang/NoSuchMethodException
      //   42	61	96	java/lang/reflect/InvocationTargetException
      //   42	61	76	java/lang/IllegalAccessException
      //   42	61	92	java/lang/IllegalArgumentException
    }
    
    public static void putBinder(Bundle param1Bundle, String param1String, IBinder param1IBinder) {
      // Byte code:
      //   0: getstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sPutIBinderMethodFetched : Z
      //   3: ifne -> 41
      //   6: ldc android/os/Bundle
      //   8: ldc 'putIBinder'
      //   10: iconst_2
      //   11: anewarray java/lang/Class
      //   14: dup
      //   15: iconst_0
      //   16: ldc java/lang/String
      //   18: aastore
      //   19: dup
      //   20: iconst_1
      //   21: ldc android/os/IBinder
      //   23: aastore
      //   24: invokevirtual getMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   27: putstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sPutIBinderMethod : Ljava/lang/reflect/Method;
      //   30: getstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sPutIBinderMethod : Ljava/lang/reflect/Method;
      //   33: iconst_1
      //   34: invokevirtual setAccessible : (Z)V
      //   37: iconst_1
      //   38: putstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sPutIBinderMethodFetched : Z
      //   41: getstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sPutIBinderMethod : Ljava/lang/reflect/Method;
      //   44: ifnull -> 67
      //   47: getstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sPutIBinderMethod : Ljava/lang/reflect/Method;
      //   50: aload_0
      //   51: iconst_2
      //   52: anewarray java/lang/Object
      //   55: dup
      //   56: iconst_0
      //   57: aload_1
      //   58: aastore
      //   59: dup
      //   60: iconst_1
      //   61: aload_2
      //   62: aastore
      //   63: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   66: pop
      //   67: return
      //   68: astore_3
      //   69: ldc 'BundleCompatBaseImpl'
      //   71: ldc 'Failed to retrieve putIBinder method'
      //   73: aload_3
      //   74: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   77: pop
      //   78: goto -> 37
      //   81: astore_0
      //   82: ldc 'BundleCompatBaseImpl'
      //   84: ldc 'Failed to invoke putIBinder via reflection'
      //   86: aload_0
      //   87: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   90: pop
      //   91: aconst_null
      //   92: putstatic androidx/core/app/BundleCompat$BundleCompatBaseImpl.sPutIBinderMethod : Ljava/lang/reflect/Method;
      //   95: return
      //   96: astore_0
      //   97: goto -> 82
      //   100: astore_0
      //   101: goto -> 82
      // Exception table:
      //   from	to	target	type
      //   6	37	68	java/lang/NoSuchMethodException
      //   47	67	100	java/lang/reflect/InvocationTargetException
      //   47	67	81	java/lang/IllegalAccessException
      //   47	67	96	java/lang/IllegalArgumentException
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/app/BundleCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
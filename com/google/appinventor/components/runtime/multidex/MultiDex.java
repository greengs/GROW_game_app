package com.google.appinventor.components.runtime.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

public final class MultiDex {
  private static final boolean IS_VM_MULTIDEX_CAPABLE;
  
  private static final int MAX_SUPPORTED_SDK_VERSION = 20;
  
  private static final int MIN_SDK_VERSION = 4;
  
  private static final String OLD_SECONDARY_FOLDER_NAME = "secondary-dexes";
  
  private static final String SECONDARY_FOLDER_NAME = "code_cache" + File.separator + "secondary-dexes";
  
  static final String TAG = "MultiDex";
  
  private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
  
  private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;
  
  private static final Set<String> installedApk = new HashSet<String>();
  
  static {
    IS_VM_MULTIDEX_CAPABLE = isVMMultidexCapable(System.getProperty("java.vm.version"));
  }
  
  private static boolean checkValidZipFiles(List<File> paramList) {
    Iterator<File> iterator = paramList.iterator();
    while (iterator.hasNext()) {
      if (!MultiDexExtractor.verifyZipFile(iterator.next()))
        return false; 
    } 
    return true;
  }
  
  private static void clearOldDexDir(Context paramContext) throws Exception {
    File[] arrayOfFile;
    File file = new File(paramContext.getFilesDir(), "secondary-dexes");
    if (file.isDirectory()) {
      Log.i("MultiDex", "Clearing old secondary dex dir (" + file.getPath() + ").");
      arrayOfFile = file.listFiles();
      if (arrayOfFile == null) {
        Log.w("MultiDex", "Failed to list secondary dex dir content (" + file.getPath() + ").");
        return;
      } 
    } else {
      return;
    } 
    int j = arrayOfFile.length;
    for (int i = 0; i < j; i++) {
      File file1 = arrayOfFile[i];
      Log.i("MultiDex", "Trying to delete old file " + file1.getPath() + " of size " + file1.length());
      if (!file1.delete()) {
        Log.w("MultiDex", "Failed to delete old file " + file1.getPath());
      } else {
        Log.i("MultiDex", "Deleted old file " + file1.getPath());
      } 
    } 
    if (!file.delete()) {
      Log.w("MultiDex", "Failed to delete secondary dex dir " + file.getPath());
      return;
    } 
    Log.i("MultiDex", "Deleted old secondary dex dir " + file.getPath());
  }
  
  private static void expandFieldArray(Object paramObject, String paramString, Object[] paramArrayOfObject) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    Field field = findField(paramObject, paramString);
    Object[] arrayOfObject1 = (Object[])field.get(paramObject);
    Object[] arrayOfObject2 = (Object[])Array.newInstance(arrayOfObject1.getClass().getComponentType(), arrayOfObject1.length + paramArrayOfObject.length);
    System.arraycopy(arrayOfObject1, 0, arrayOfObject2, 0, arrayOfObject1.length);
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject2, arrayOfObject1.length, paramArrayOfObject.length);
    field.set(paramObject, arrayOfObject2);
  }
  
  private static Field findField(Object paramObject, String paramString) throws NoSuchFieldException {
    Class<?> clazz = paramObject.getClass();
    while (clazz != null) {
      try {
        Field field = clazz.getDeclaredField(paramString);
        if (!field.isAccessible())
          field.setAccessible(true); 
        return field;
      } catch (NoSuchFieldException noSuchFieldException) {
        clazz = clazz.getSuperclass();
      } 
    } 
    throw new NoSuchFieldException("Field " + paramString + " not found in " + paramObject.getClass());
  }
  
  private static Method findMethod(Object paramObject, String paramString, Class<?>... paramVarArgs) throws NoSuchMethodException {
    Class<?> clazz = paramObject.getClass();
    while (clazz != null) {
      try {
        Method method = clazz.getDeclaredMethod(paramString, paramVarArgs);
        if (!method.isAccessible())
          method.setAccessible(true); 
        return method;
      } catch (NoSuchMethodException noSuchMethodException) {
        clazz = clazz.getSuperclass();
      } 
    } 
    throw new NoSuchMethodException("Method " + paramString + " with parameters " + Arrays.asList(paramVarArgs) + " not found in " + paramObject.getClass());
  }
  
  private static ApplicationInfo getApplicationInfo(Context paramContext) throws PackageManager.NameNotFoundException {
    PackageManager packageManager;
    try {
      packageManager = paramContext.getPackageManager();
      String str = paramContext.getPackageName();
      if (packageManager == null || str == null)
        return null; 
    } catch (RuntimeException runtimeException) {
      Log.w("MultiDex", "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", runtimeException);
      return null;
    } 
    return packageManager.getApplicationInfo((String)runtimeException, 128);
  }
  
  public static boolean install(Context paramContext, boolean paramBoolean) {
    // Byte code:
    //   0: getstatic com/google/appinventor/components/runtime/multidex/MultiDex.installedApk : Ljava/util/Set;
    //   3: invokeinterface clear : ()V
    //   8: ldc 'MultiDex'
    //   10: new java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial <init> : ()V
    //   17: ldc_w 'install: doIt = '
    //   20: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: iload_1
    //   24: invokevirtual append : (Z)Ljava/lang/StringBuilder;
    //   27: invokevirtual toString : ()Ljava/lang/String;
    //   30: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   33: pop
    //   34: getstatic com/google/appinventor/components/runtime/multidex/MultiDex.IS_VM_MULTIDEX_CAPABLE : Z
    //   37: ifeq -> 51
    //   40: ldc 'MultiDex'
    //   42: ldc_w 'VM has multidex support, MultiDex support library is disabled.'
    //   45: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   48: pop
    //   49: iconst_1
    //   50: ireturn
    //   51: getstatic android/os/Build$VERSION.SDK_INT : I
    //   54: iconst_4
    //   55: if_icmpge -> 104
    //   58: new java/lang/RuntimeException
    //   61: dup
    //   62: new java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial <init> : ()V
    //   69: ldc_w 'Multi dex installation failed. SDK '
    //   72: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: getstatic android/os/Build$VERSION.SDK_INT : I
    //   78: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   81: ldc_w ' is unsupported. Min SDK version is '
    //   84: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: iconst_4
    //   88: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   91: ldc_w '.'
    //   94: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: invokevirtual toString : ()Ljava/lang/String;
    //   100: invokespecial <init> : (Ljava/lang/String;)V
    //   103: athrow
    //   104: aload_0
    //   105: invokestatic getApplicationInfo : (Landroid/content/Context;)Landroid/content/pm/ApplicationInfo;
    //   108: astore_3
    //   109: aload_3
    //   110: ifnonnull -> 171
    //   113: ldc 'MultiDex'
    //   115: ldc_w 'applicationInfo is null, returning'
    //   118: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   121: pop
    //   122: iconst_1
    //   123: ireturn
    //   124: astore_0
    //   125: ldc 'MultiDex'
    //   127: ldc_w 'Multidex installation failure'
    //   130: aload_0
    //   131: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   134: pop
    //   135: new java/lang/RuntimeException
    //   138: dup
    //   139: new java/lang/StringBuilder
    //   142: dup
    //   143: invokespecial <init> : ()V
    //   146: ldc_w 'Multi dex installation failed ('
    //   149: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: aload_0
    //   153: invokevirtual getMessage : ()Ljava/lang/String;
    //   156: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: ldc ').'
    //   161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: invokevirtual toString : ()Ljava/lang/String;
    //   167: invokespecial <init> : (Ljava/lang/String;)V
    //   170: athrow
    //   171: getstatic com/google/appinventor/components/runtime/multidex/MultiDex.installedApk : Ljava/util/Set;
    //   174: astore_2
    //   175: aload_2
    //   176: monitorenter
    //   177: aload_3
    //   178: getfield sourceDir : Ljava/lang/String;
    //   181: astore #4
    //   183: getstatic com/google/appinventor/components/runtime/multidex/MultiDex.installedApk : Ljava/util/Set;
    //   186: aload #4
    //   188: invokeinterface contains : (Ljava/lang/Object;)Z
    //   193: ifeq -> 205
    //   196: aload_2
    //   197: monitorexit
    //   198: iconst_1
    //   199: ireturn
    //   200: astore_0
    //   201: aload_2
    //   202: monitorexit
    //   203: aload_0
    //   204: athrow
    //   205: getstatic com/google/appinventor/components/runtime/multidex/MultiDex.installedApk : Ljava/util/Set;
    //   208: aload #4
    //   210: invokeinterface add : (Ljava/lang/Object;)Z
    //   215: pop
    //   216: getstatic android/os/Build$VERSION.SDK_INT : I
    //   219: bipush #20
    //   221: if_icmple -> 283
    //   224: ldc 'MultiDex'
    //   226: new java/lang/StringBuilder
    //   229: dup
    //   230: invokespecial <init> : ()V
    //   233: ldc_w 'MultiDex is not guaranteed to work in SDK version '
    //   236: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: getstatic android/os/Build$VERSION.SDK_INT : I
    //   242: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   245: ldc_w ': SDK version higher than '
    //   248: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: bipush #20
    //   253: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   256: ldc_w ' should be backed by runtime with built-in multidex capabilty but it's not the case here: java.vm.version="'
    //   259: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: ldc 'java.vm.version'
    //   264: invokestatic getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   267: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: ldc_w '"'
    //   273: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: invokevirtual toString : ()Ljava/lang/String;
    //   279: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   282: pop
    //   283: aload_0
    //   284: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   287: astore #4
    //   289: aload #4
    //   291: ifnonnull -> 322
    //   294: ldc 'MultiDex'
    //   296: ldc_w 'Context class loader is null. Must be running in test mode. Skip patching.'
    //   299: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   302: pop
    //   303: aload_2
    //   304: monitorexit
    //   305: iconst_1
    //   306: ireturn
    //   307: astore_0
    //   308: ldc 'MultiDex'
    //   310: ldc_w 'Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching.'
    //   313: aload_0
    //   314: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   317: pop
    //   318: aload_2
    //   319: monitorexit
    //   320: iconst_1
    //   321: ireturn
    //   322: aload_0
    //   323: invokestatic clearOldDexDir : (Landroid/content/Context;)V
    //   326: new java/io/File
    //   329: dup
    //   330: aload_3
    //   331: getfield dataDir : Ljava/lang/String;
    //   334: getstatic com/google/appinventor/components/runtime/multidex/MultiDex.SECONDARY_FOLDER_NAME : Ljava/lang/String;
    //   337: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   340: astore #5
    //   342: iload_1
    //   343: ifne -> 383
    //   346: aload_0
    //   347: aload_3
    //   348: invokestatic mustLoad : (Landroid/content/Context;Landroid/content/pm/ApplicationInfo;)Z
    //   351: ifeq -> 383
    //   354: ldc 'MultiDex'
    //   356: ldc_w 'Returning because of mustLoad'
    //   359: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   362: pop
    //   363: aload_2
    //   364: monitorexit
    //   365: iconst_0
    //   366: ireturn
    //   367: astore #5
    //   369: ldc 'MultiDex'
    //   371: ldc_w 'Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning.'
    //   374: aload #5
    //   376: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   379: pop
    //   380: goto -> 326
    //   383: ldc 'MultiDex'
    //   385: ldc_w 'Proceeding with installation...'
    //   388: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   391: pop
    //   392: aload_0
    //   393: aload_3
    //   394: aload #5
    //   396: iconst_0
    //   397: invokestatic load : (Landroid/content/Context;Landroid/content/pm/ApplicationInfo;Ljava/io/File;Z)Ljava/util/List;
    //   400: astore #6
    //   402: aload #6
    //   404: invokestatic checkValidZipFiles : (Ljava/util/List;)Z
    //   407: ifeq -> 432
    //   410: aload #4
    //   412: aload #5
    //   414: aload #6
    //   416: invokestatic installSecondaryDexes : (Ljava/lang/ClassLoader;Ljava/io/File;Ljava/util/List;)V
    //   419: aload_2
    //   420: monitorexit
    //   421: ldc 'MultiDex'
    //   423: ldc_w 'install done'
    //   426: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   429: pop
    //   430: iconst_1
    //   431: ireturn
    //   432: ldc 'MultiDex'
    //   434: ldc_w 'Files were not valid zip files.  Forcing a reload.'
    //   437: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   440: pop
    //   441: aload_0
    //   442: aload_3
    //   443: aload #5
    //   445: iconst_1
    //   446: invokestatic load : (Landroid/content/Context;Landroid/content/pm/ApplicationInfo;Ljava/io/File;Z)Ljava/util/List;
    //   449: astore_0
    //   450: aload_0
    //   451: invokestatic checkValidZipFiles : (Ljava/util/List;)Z
    //   454: ifeq -> 468
    //   457: aload #4
    //   459: aload #5
    //   461: aload_0
    //   462: invokestatic installSecondaryDexes : (Ljava/lang/ClassLoader;Ljava/io/File;Ljava/util/List;)V
    //   465: goto -> 419
    //   468: new java/lang/RuntimeException
    //   471: dup
    //   472: ldc_w 'Zip files were not valid.'
    //   475: invokespecial <init> : (Ljava/lang/String;)V
    //   478: athrow
    // Exception table:
    //   from	to	target	type
    //   104	109	124	java/lang/Exception
    //   113	122	124	java/lang/Exception
    //   171	177	124	java/lang/Exception
    //   177	198	200	finally
    //   201	203	200	finally
    //   203	205	124	java/lang/Exception
    //   205	283	200	finally
    //   283	289	307	java/lang/RuntimeException
    //   283	289	200	finally
    //   294	305	200	finally
    //   308	320	200	finally
    //   322	326	367	java/lang/Throwable
    //   322	326	200	finally
    //   326	342	200	finally
    //   346	365	200	finally
    //   369	380	200	finally
    //   383	419	200	finally
    //   419	421	200	finally
    //   432	465	200	finally
    //   468	479	200	finally
  }
  
  private static void installSecondaryDexes(ClassLoader paramClassLoader, File paramFile, List<File> paramList) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
    if (!paramList.isEmpty()) {
      if (Build.VERSION.SDK_INT >= 19) {
        V19.install(paramClassLoader, paramList, paramFile);
        return;
      } 
    } else {
      return;
    } 
    if (Build.VERSION.SDK_INT >= 14) {
      V14.install(paramClassLoader, paramList, paramFile);
      return;
    } 
    V4.install(paramClassLoader, paramList);
  }
  
  static boolean isVMMultidexCapable(String paramString) {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString != null) {
      Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(paramString);
      bool1 = bool2;
      if (matcher.matches())
        try {
          int i = Integer.parseInt(matcher.group(1));
          int j = Integer.parseInt(matcher.group(2));
          if (i > 2 || (i == 2 && j >= 1)) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
        } catch (NumberFormatException numberFormatException) {
          bool1 = bool2;
        }  
    } 
    StringBuilder stringBuilder = (new StringBuilder()).append("VM with version ").append(paramString);
    if (bool1) {
      paramString = " has multidex support";
      Log.i("MultiDex", stringBuilder.append(paramString).toString());
      return bool1;
    } 
    paramString = " does not have multidex support";
    Log.i("MultiDex", stringBuilder.append(paramString).toString());
    return bool1;
  }
  
  private static final class V14 {
    private static void install(ClassLoader param1ClassLoader, List<File> param1List, File param1File) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
      Object object = MultiDex.findField(param1ClassLoader, "pathList").get(param1ClassLoader);
      MultiDex.expandFieldArray(object, "dexElements", makeDexElements(object, new ArrayList<File>(param1List), param1File));
    }
    
    private static Object[] makeDexElements(Object param1Object, ArrayList<File> param1ArrayList, File param1File) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
      return (Object[])MultiDex.findMethod(param1Object, "makeDexElements", new Class[] { ArrayList.class, File.class }).invoke(param1Object, new Object[] { param1ArrayList, param1File });
    }
  }
  
  private static final class V19 {
    private static void install(ClassLoader param1ClassLoader, List<File> param1List, File param1File) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
      Object object = MultiDex.findField(param1ClassLoader, "pathList").get(param1ClassLoader);
      ArrayList<IOException> arrayList = new ArrayList();
      MultiDex.expandFieldArray(object, "dexElements", makeDexElements(object, new ArrayList<File>(param1List), param1File, arrayList));
      if (arrayList.size() > 0) {
        IOException[] arrayOfIOException;
        Iterator<IOException> iterator = arrayList.iterator();
        while (iterator.hasNext())
          Log.w("MultiDex", "Exception in makeDexElement", iterator.next()); 
        Field field = MultiDex.findField(param1ClassLoader, "dexElementsSuppressedExceptions");
        object = field.get(param1ClassLoader);
        if (object == null) {
          arrayOfIOException = arrayList.<IOException>toArray(new IOException[arrayList.size()]);
        } else {
          arrayOfIOException = new IOException[arrayList.size() + object.length];
          arrayList.toArray(arrayOfIOException);
          System.arraycopy(object, 0, arrayOfIOException, arrayList.size(), object.length);
        } 
        field.set(param1ClassLoader, arrayOfIOException);
      } 
    }
    
    private static Object[] makeDexElements(Object param1Object, ArrayList<File> param1ArrayList, File param1File, ArrayList<IOException> param1ArrayList1) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
      return (Object[])MultiDex.findMethod(param1Object, "makeDexElements", new Class[] { ArrayList.class, File.class, ArrayList.class }).invoke(param1Object, new Object[] { param1ArrayList, param1File, param1ArrayList1 });
    }
  }
  
  private static final class V4 {
    private static void install(ClassLoader param1ClassLoader, List<File> param1List) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
      int i = param1List.size();
      Field field = MultiDex.findField(param1ClassLoader, "path");
      StringBuilder stringBuilder = new StringBuilder((String)field.get(param1ClassLoader));
      String[] arrayOfString = new String[i];
      File[] arrayOfFile = new File[i];
      ZipFile[] arrayOfZipFile = new ZipFile[i];
      DexFile[] arrayOfDexFile = new DexFile[i];
      ListIterator<File> listIterator = param1List.listIterator();
      while (listIterator.hasNext()) {
        File file = listIterator.next();
        String str = file.getAbsolutePath();
        stringBuilder.append(':').append(str);
        i = listIterator.previousIndex();
        arrayOfString[i] = str;
        arrayOfFile[i] = file;
        arrayOfZipFile[i] = new ZipFile(file);
        arrayOfDexFile[i] = DexFile.loadDex(str, str + ".dex", 0);
      } 
      field.set(param1ClassLoader, stringBuilder.toString());
      MultiDex.expandFieldArray(param1ClassLoader, "mPaths", (Object[])arrayOfString);
      MultiDex.expandFieldArray(param1ClassLoader, "mFiles", (Object[])arrayOfFile);
      MultiDex.expandFieldArray(param1ClassLoader, "mZips", (Object[])arrayOfZipFile);
      MultiDex.expandFieldArray(param1ClassLoader, "mDexs", (Object[])arrayOfDexFile);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/multidex/MultiDex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.bytecode;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

public class ArrayClassLoader extends ClassLoader {
  Hashtable cmap = new Hashtable<Object, Object>(100);
  
  URL context;
  
  Hashtable map = new Hashtable<Object, Object>(100);
  
  public ArrayClassLoader() {}
  
  public ArrayClassLoader(ClassLoader paramClassLoader) {
    super(paramClassLoader);
  }
  
  public ArrayClassLoader(String[] paramArrayOfString, byte[][] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    while (true) {
      if (--i >= 0) {
        addClass(paramArrayOfString[i], paramArrayOfbyte[i]);
        continue;
      } 
      break;
    } 
  }
  
  public ArrayClassLoader(byte[][] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    while (true) {
      if (--i >= 0) {
        addClass("lambda" + i, paramArrayOfbyte[i]);
        continue;
      } 
      break;
    } 
  }
  
  public static Package getContextPackage(String paramString) {
    try {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      if (classLoader instanceof ArrayClassLoader)
        return ((ArrayClassLoader)classLoader).getPackage(paramString); 
    } catch (SecurityException securityException) {}
    return Package.getPackage(paramString);
  }
  
  public void addClass(ClassType paramClassType) {
    this.map.put(paramClassType.getName(), paramClassType);
  }
  
  public void addClass(Class<?> paramClass) {
    this.cmap.put(paramClass.getName(), paramClass);
  }
  
  public void addClass(String paramString, byte[] paramArrayOfbyte) {
    this.map.put(paramString, paramArrayOfbyte);
  }
  
  protected URL findResource(String paramString) {
    if (this.context != null)
      try {
        URL uRL = new URL(this.context, paramString);
        uRL.openConnection().connect();
        return uRL;
      } catch (Throwable throwable) {} 
    return super.findResource(paramString);
  }
  
  public InputStream getResourceAsStream(String paramString) {
    InputStream inputStream2 = super.getResourceAsStream(paramString);
    InputStream inputStream1 = inputStream2;
    if (inputStream2 == null) {
      inputStream1 = inputStream2;
      if (paramString.endsWith(".class")) {
        paramString = paramString.substring(0, paramString.length() - 6).replace('/', '.');
        paramString = (String)this.map.get(paramString);
        inputStream1 = inputStream2;
        if (paramString instanceof byte[])
          inputStream1 = new ByteArrayInputStream((byte[])paramString); 
      } 
    } 
    return inputStream1;
  }
  
  public URL getResourceContext() {
    return this.context;
  }
  
  public Class loadClass(String paramString) throws ClassNotFoundException {
    // Byte code:
    //   0: aload_0
    //   1: getfield cmap : Ljava/util/Hashtable;
    //   4: aload_1
    //   5: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   8: astore_2
    //   9: aload_2
    //   10: ifnull -> 18
    //   13: aload_2
    //   14: checkcast java/lang/Class
    //   17: areturn
    //   18: aload_0
    //   19: getfield map : Ljava/util/Hashtable;
    //   22: aload_1
    //   23: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   26: astore_3
    //   27: aload_3
    //   28: astore_2
    //   29: aload_3
    //   30: instanceof gnu/bytecode/ClassType
    //   33: ifeq -> 53
    //   36: aload_3
    //   37: checkcast gnu/bytecode/ClassType
    //   40: astore_2
    //   41: aload_2
    //   42: invokevirtual isExisting : ()Z
    //   45: ifeq -> 112
    //   48: aload_2
    //   49: getfield reflectClass : Ljava/lang/Class;
    //   52: astore_2
    //   53: aload_2
    //   54: instanceof [B
    //   57: ifeq -> 133
    //   60: aload_0
    //   61: monitorenter
    //   62: aload_0
    //   63: getfield map : Ljava/util/Hashtable;
    //   66: aload_1
    //   67: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   70: astore_2
    //   71: aload_2
    //   72: instanceof [B
    //   75: ifeq -> 120
    //   78: aload_2
    //   79: checkcast [B
    //   82: checkcast [B
    //   85: astore_2
    //   86: aload_0
    //   87: aload_1
    //   88: aload_2
    //   89: iconst_0
    //   90: aload_2
    //   91: arraylength
    //   92: invokevirtual defineClass : (Ljava/lang/String;[BII)Ljava/lang/Class;
    //   95: astore_2
    //   96: aload_0
    //   97: getfield cmap : Ljava/util/Hashtable;
    //   100: aload_1
    //   101: aload_2
    //   102: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   105: pop
    //   106: aload_2
    //   107: astore_1
    //   108: aload_0
    //   109: monitorexit
    //   110: aload_1
    //   111: areturn
    //   112: aload_2
    //   113: invokevirtual writeToArray : ()[B
    //   116: astore_2
    //   117: goto -> 53
    //   120: aload_2
    //   121: checkcast java/lang/Class
    //   124: astore_1
    //   125: goto -> 108
    //   128: astore_1
    //   129: aload_0
    //   130: monitorexit
    //   131: aload_1
    //   132: athrow
    //   133: aload_2
    //   134: ifnonnull -> 149
    //   137: aload_0
    //   138: invokevirtual getParent : ()Ljava/lang/ClassLoader;
    //   141: aload_1
    //   142: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   145: astore_1
    //   146: goto -> 110
    //   149: aload_2
    //   150: checkcast java/lang/Class
    //   153: astore_1
    //   154: goto -> 110
    // Exception table:
    //   from	to	target	type
    //   62	106	128	finally
    //   108	110	128	finally
    //   120	125	128	finally
    //   129	131	128	finally
  }
  
  public Class loadClass(String paramString, boolean paramBoolean) throws ClassNotFoundException {
    Class<?> clazz = loadClass(paramString);
    if (paramBoolean)
      resolveClass(clazz); 
    return clazz;
  }
  
  public void setResourceContext(URL paramURL) {
    this.context = paramURL;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/ArrayClassLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
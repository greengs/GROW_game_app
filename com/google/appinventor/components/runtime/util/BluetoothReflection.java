package com.google.appinventor.components.runtime.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

public class BluetoothReflection {
  private static final int BOND_BONDED = 12;
  
  public static Object accept(Object paramObject) throws IOException {
    return invokeMethodThrowsIOException(getMethod(paramObject.getClass(), "accept"), paramObject, new Object[0]);
  }
  
  public static boolean checkBluetoothAddress(Object paramObject, String paramString) {
    return ((Boolean)invokeMethod(getMethod(paramObject.getClass(), "checkBluetoothAddress", new Class[] { String.class }), paramObject, new Object[] { paramString })).booleanValue();
  }
  
  public static void closeBluetoothServerSocket(Object paramObject) throws IOException {
    invokeMethodThrowsIOException(getMethod(paramObject.getClass(), "close"), paramObject, new Object[0]);
  }
  
  public static void closeBluetoothSocket(Object paramObject) throws IOException {
    invokeMethodThrowsIOException(getMethod(paramObject.getClass(), "close"), paramObject, new Object[0]);
  }
  
  public static void connectToBluetoothSocket(Object paramObject) throws IOException {
    invokeMethodThrowsIOException(getMethod(paramObject.getClass(), "connect"), paramObject, new Object[0]);
  }
  
  public static Object createInsecureRfcommSocketToServiceRecord(Object paramObject, UUID paramUUID) throws IOException {
    return invokeMethodThrowsIOException(getMethod(paramObject.getClass(), "createInsecureRfcommSocketToServiceRecord", new Class[] { UUID.class }), paramObject, new Object[] { paramUUID });
  }
  
  public static Object createRfcommSocketToServiceRecord(Object paramObject, UUID paramUUID) throws IOException {
    return invokeMethodThrowsIOException(getMethod(paramObject.getClass(), "createRfcommSocketToServiceRecord", new Class[] { UUID.class }), paramObject, new Object[] { paramUUID });
  }
  
  public static Object getBluetoothAdapter() {
    try {
      Class<?> clazz = Class.forName("android.bluetooth.BluetoothAdapter");
      return invokeStaticMethod(getMethod(clazz, "getDefaultAdapter"));
    } catch (ClassNotFoundException classNotFoundException) {
      return null;
    } 
  }
  
  public static Object getBluetoothClass(Object paramObject) {
    return invokeMethod(getMethod(paramObject.getClass(), "getBluetoothClass"), paramObject, new Object[0]);
  }
  
  public static String getBluetoothDeviceAddress(Object paramObject) {
    return (String)invokeMethod(getMethod(paramObject.getClass(), "getAddress"), paramObject, new Object[0]);
  }
  
  public static String getBluetoothDeviceName(Object paramObject) {
    return (String)invokeMethod(getMethod(paramObject.getClass(), "getName"), paramObject, new Object[0]);
  }
  
  public static Set getBondedDevices(Object paramObject) {
    return (Set)invokeMethod(getMethod(paramObject.getClass(), "getBondedDevices"), paramObject, new Object[0]);
  }
  
  public static int getDeviceClass(Object paramObject) {
    return ((Integer)invokeMethod(getMethod(paramObject.getClass(), "getDeviceClass"), paramObject, new Object[0])).intValue();
  }
  
  public static InputStream getInputStream(Object paramObject) throws IOException {
    return (InputStream)invokeMethodThrowsIOException(getMethod(paramObject.getClass(), "getInputStream"), paramObject, new Object[0]);
  }
  
  private static Method getMethod(Class paramClass, String paramString) {
    try {
      return paramClass.getMethod(paramString, new Class[0]);
    } catch (NoSuchMethodException noSuchMethodException) {
      throw new RuntimeException(noSuchMethodException);
    } 
  }
  
  private static Method getMethod(Class paramClass, String paramString, Class<?>... paramVarArgs) {
    try {
      return paramClass.getMethod(paramString, paramVarArgs);
    } catch (NoSuchMethodException noSuchMethodException) {
      throw new RuntimeException(noSuchMethodException);
    } 
  }
  
  public static OutputStream getOutputStream(Object paramObject) throws IOException {
    return (OutputStream)invokeMethodThrowsIOException(getMethod(paramObject.getClass(), "getOutputStream"), paramObject, new Object[0]);
  }
  
  public static Object getRemoteDevice(Object paramObject, String paramString) throws IllegalArgumentException {
    return invokeMethodThrowsIllegalArgumentException(getMethod(paramObject.getClass(), "getRemoteDevice", new Class[] { String.class }), paramObject, new Object[] { paramString });
  }
  
  private static Object invokeMethod(Method paramMethod, Object paramObject, Object... paramVarArgs) {
    try {
      return paramMethod.invoke(paramObject, paramVarArgs);
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException(illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      Throwable throwable = invocationTargetException.getCause();
      throwable.printStackTrace();
      if (throwable instanceof RuntimeException)
        throw (RuntimeException)throwable; 
      throw new RuntimeException(throwable);
    } 
  }
  
  private static Object invokeMethodThrowsIOException(Method paramMethod, Object paramObject, Object... paramVarArgs) throws IOException {
    try {
      return paramMethod.invoke(paramObject, paramVarArgs);
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException(illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      paramObject = invocationTargetException.getCause();
      paramObject.printStackTrace();
      if (paramObject instanceof IOException)
        throw (IOException)paramObject; 
      if (paramObject instanceof RuntimeException)
        throw (RuntimeException)paramObject; 
      throw new RuntimeException(invocationTargetException);
    } 
  }
  
  private static Object invokeMethodThrowsIllegalArgumentException(Method paramMethod, Object paramObject, Object... paramVarArgs) throws IllegalArgumentException {
    try {
      return paramMethod.invoke(paramObject, paramVarArgs);
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException(illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      paramObject = invocationTargetException.getCause();
      paramObject.printStackTrace();
      if (paramObject instanceof IllegalArgumentException)
        throw (IllegalArgumentException)paramObject; 
      if (paramObject instanceof RuntimeException)
        throw (RuntimeException)paramObject; 
      throw new RuntimeException(invocationTargetException);
    } 
  }
  
  private static Object invokeStaticMethod(Method paramMethod) {
    try {
      return paramMethod.invoke(null, new Object[0]);
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException(illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      Throwable throwable = invocationTargetException.getCause();
      throwable.printStackTrace();
      if (throwable instanceof RuntimeException)
        throw (RuntimeException)throwable; 
      throw new RuntimeException(throwable);
    } 
  }
  
  public static boolean isBluetoothEnabled(Object paramObject) {
    return ((Boolean)invokeMethod(getMethod(paramObject.getClass(), "isEnabled"), paramObject, new Object[0])).booleanValue();
  }
  
  public static boolean isBluetoothSocketConnected(Object paramObject) {
    return ((Boolean)invokeMethod(getMethod(paramObject.getClass(), "isConnected"), paramObject, new Object[0])).booleanValue();
  }
  
  public static boolean isBonded(Object paramObject) {
    return (((Integer)invokeMethod(getMethod(paramObject.getClass(), "getBondState"), paramObject, new Object[0])).intValue() == 12);
  }
  
  public static Object listenUsingInsecureRfcommWithServiceRecord(Object paramObject, String paramString, UUID paramUUID) throws IOException {
    return invokeMethodThrowsIOException(getMethod(paramObject.getClass(), "listenUsingInsecureRfcommWithServiceRecord", new Class[] { String.class, UUID.class }), paramObject, new Object[] { paramString, paramUUID });
  }
  
  public static Object listenUsingRfcommWithServiceRecord(Object paramObject, String paramString, UUID paramUUID) throws IOException {
    return invokeMethodThrowsIOException(getMethod(paramObject.getClass(), "listenUsingRfcommWithServiceRecord", new Class[] { String.class, UUID.class }), paramObject, new Object[] { paramString, paramUUID });
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/BluetoothReflection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
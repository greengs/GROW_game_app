package androidx.versionedparcelable;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.NetworkOnMainThreadException;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseBooleanArray;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.ArraySet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class VersionedParcel {
  private static final int EX_BAD_PARCELABLE = -2;
  
  private static final int EX_ILLEGAL_ARGUMENT = -3;
  
  private static final int EX_ILLEGAL_STATE = -5;
  
  private static final int EX_NETWORK_MAIN_THREAD = -6;
  
  private static final int EX_NULL_POINTER = -4;
  
  private static final int EX_PARCELABLE = -9;
  
  private static final int EX_SECURITY = -1;
  
  private static final int EX_UNSUPPORTED_OPERATION = -7;
  
  private static final String TAG = "VersionedParcel";
  
  private static final int TYPE_BINDER = 5;
  
  private static final int TYPE_PARCELABLE = 2;
  
  private static final int TYPE_SERIALIZABLE = 3;
  
  private static final int TYPE_STRING = 4;
  
  private static final int TYPE_VERSIONED_PARCELABLE = 1;
  
  private Exception createException(int paramInt, String paramString) {
    switch (paramInt) {
      default:
        return new RuntimeException("Unknown exception code: " + paramInt + " msg " + paramString);
      case -9:
        return readParcelable();
      case -1:
        return new SecurityException(paramString);
      case -2:
        return (Exception)new BadParcelableException(paramString);
      case -3:
        return new IllegalArgumentException(paramString);
      case -4:
        return new NullPointerException(paramString);
      case -5:
        return new IllegalStateException(paramString);
      case -6:
        return (Exception)new NetworkOnMainThreadException();
      case -7:
        break;
    } 
    return new UnsupportedOperationException(paramString);
  }
  
  private static <T extends VersionedParcelable> Class findParcelClass(T paramT) throws ClassNotFoundException {
    return findParcelClass((Class)paramT.getClass());
  }
  
  private static Class findParcelClass(Class<? extends VersionedParcelable> paramClass) throws ClassNotFoundException {
    return Class.forName(String.format("%s.%sParcelizer", new Object[] { paramClass.getPackage().getName(), paramClass.getSimpleName() }), false, paramClass.getClassLoader());
  }
  
  @NonNull
  protected static Throwable getRootCause(@NonNull Throwable paramThrowable) {
    while (paramThrowable.getCause() != null)
      paramThrowable = paramThrowable.getCause(); 
    return paramThrowable;
  }
  
  private <T> int getType(T paramT) {
    if (paramT instanceof String)
      return 4; 
    if (paramT instanceof Parcelable)
      return 2; 
    if (paramT instanceof VersionedParcelable)
      return 1; 
    if (paramT instanceof Serializable)
      return 3; 
    if (paramT instanceof IBinder)
      return 5; 
    throw new IllegalArgumentException(paramT.getClass().getName() + " cannot be VersionedParcelled");
  }
  
  private <T, S extends Collection<T>> S readCollection(int paramInt, S paramS) {
    paramInt = readInt();
    if (paramInt < 0)
      return null; 
    S s = paramS;
    if (paramInt != 0) {
      int n = readInt();
      if (paramInt < 0)
        return null; 
      int i = paramInt;
      int j = paramInt;
      int k = paramInt;
      int m = paramInt;
      switch (n) {
        default:
          return paramS;
        case 1:
          while (true) {
            s = paramS;
            if (i > 0) {
              paramS.add(readVersionedParcelable());
              i--;
              continue;
            } 
            return s;
          } 
        case 4:
          while (true) {
            s = paramS;
            if (j > 0) {
              paramS.add(readString());
              j--;
              continue;
            } 
            return s;
          } 
        case 2:
          while (true) {
            s = paramS;
            if (k > 0) {
              paramS.add(readParcelable());
              k--;
              continue;
            } 
            return s;
          } 
        case 3:
          while (true) {
            s = paramS;
            if (m > 0) {
              paramS.add(readSerializable());
              m--;
              continue;
            } 
            return s;
          } 
        case 5:
          break;
      } 
      while (true) {
        s = paramS;
        if (paramInt > 0) {
          paramS.add(readStrongBinder());
          paramInt--;
          continue;
        } 
        return s;
      } 
    } 
    return s;
  }
  
  private Exception readException(int paramInt, String paramString) {
    return createException(paramInt, paramString);
  }
  
  private int readExceptionCode() {
    return readInt();
  }
  
  protected static <T extends VersionedParcelable> T readFromParcel(String paramString, VersionedParcel paramVersionedParcel) {
    try {
      return (T)Class.forName(paramString, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", new Class[] { VersionedParcel.class }).invoke(null, new Object[] { paramVersionedParcel });
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException("VersionedParcel encountered IllegalAccessException", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      if (invocationTargetException.getCause() instanceof RuntimeException)
        throw (RuntimeException)invocationTargetException.getCause(); 
      throw new RuntimeException("VersionedParcel encountered InvocationTargetException", invocationTargetException);
    } catch (NoSuchMethodException noSuchMethodException) {
      throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", noSuchMethodException);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", classNotFoundException);
    } 
  }
  
  private <T> void writeCollection(Collection<T> paramCollection, int paramInt) {
    setOutputField(paramInt);
    if (paramCollection == null) {
      writeInt(-1);
      return;
    } 
    paramInt = paramCollection.size();
    writeInt(paramInt);
    if (paramInt > 0) {
      paramInt = getType(paramCollection.iterator().next());
      writeInt(paramInt);
      switch (paramInt) {
        default:
          return;
        case 1:
          iterator = paramCollection.iterator();
          while (true) {
            if (iterator.hasNext()) {
              writeVersionedParcelable((VersionedParcelable)iterator.next());
              continue;
            } 
            return;
          } 
        case 4:
          iterator = iterator.iterator();
          while (true) {
            if (iterator.hasNext()) {
              writeString((String)iterator.next());
              continue;
            } 
            return;
          } 
        case 2:
          iterator = iterator.iterator();
          while (true) {
            if (iterator.hasNext()) {
              writeParcelable((Parcelable)iterator.next());
              continue;
            } 
            return;
          } 
        case 3:
          iterator = iterator.iterator();
          while (true) {
            if (iterator.hasNext()) {
              writeSerializable((Serializable)iterator.next());
              continue;
            } 
            return;
          } 
        case 5:
          break;
      } 
      Iterator<T> iterator = iterator.iterator();
      while (true) {
        if (iterator.hasNext()) {
          writeStrongBinder((IBinder)iterator.next());
          continue;
        } 
        return;
      } 
    } 
  }
  
  private void writeSerializable(Serializable paramSerializable) {
    if (paramSerializable == null) {
      writeString(null);
      return;
    } 
    String str = paramSerializable.getClass().getName();
    writeString(str);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
      objectOutputStream.writeObject(paramSerializable);
      objectOutputStream.close();
      writeByteArray(byteArrayOutputStream.toByteArray());
      return;
    } catch (IOException iOException) {
      throw new RuntimeException("VersionedParcelable encountered IOException writing serializable object (name = " + str + ")", iOException);
    } 
  }
  
  protected static <T extends VersionedParcelable> void writeToParcel(T paramT, VersionedParcel paramVersionedParcel) {
    try {
      findParcelClass(paramT).getDeclaredMethod("write", new Class[] { paramT.getClass(), VersionedParcel.class }).invoke(null, new Object[] { paramT, paramVersionedParcel });
      return;
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException("VersionedParcel encountered IllegalAccessException", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      if (invocationTargetException.getCause() instanceof RuntimeException)
        throw (RuntimeException)invocationTargetException.getCause(); 
      throw new RuntimeException("VersionedParcel encountered InvocationTargetException", invocationTargetException);
    } catch (NoSuchMethodException noSuchMethodException) {
      throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", noSuchMethodException);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", classNotFoundException);
    } 
  }
  
  private void writeVersionedParcelableCreator(VersionedParcelable paramVersionedParcelable) {
    try {
      Class clazz = findParcelClass((Class)paramVersionedParcelable.getClass());
      writeString(clazz.getName());
      return;
    } catch (ClassNotFoundException classNotFoundException) {
      throw new RuntimeException(paramVersionedParcelable.getClass().getSimpleName() + " does not have a Parcelizer", classNotFoundException);
    } 
  }
  
  protected abstract void closeField();
  
  protected abstract VersionedParcel createSubParcel();
  
  public boolean isStream() {
    return false;
  }
  
  protected <T> T[] readArray(T[] paramArrayOfT) {
    int i = readInt();
    if (i >= 0) {
      ArrayList<String> arrayList = new ArrayList(i);
      if (i != 0) {
        int j = readInt();
        if (i >= 0) {
          int k = i;
          int m = i;
          int n = i;
          int i1 = i;
          switch (j) {
            default:
              return (T[])arrayList.toArray((Object[])paramArrayOfT);
            case 4:
              while (true) {
                if (k > 0) {
                  arrayList.add(readString());
                  k--;
                } 
              } 
            case 2:
              while (true) {
                if (m > 0) {
                  arrayList.add(readParcelable());
                  m--;
                } 
              } 
            case 1:
              while (true) {
                if (n > 0) {
                  arrayList.add(readVersionedParcelable());
                  n--;
                } 
              } 
            case 3:
              while (true) {
                if (i1 > 0) {
                  arrayList.add(readSerializable());
                  i1--;
                } 
              } 
            case 5:
              break;
          } 
          while (true) {
            if (i > 0) {
              arrayList.add(readStrongBinder());
              i--;
            } 
          } 
        } 
        return null;
      } 
    } 
    return null;
  }
  
  public <T> T[] readArray(T[] paramArrayOfT, int paramInt) {
    return !readField(paramInt) ? paramArrayOfT : readArray(paramArrayOfT);
  }
  
  protected abstract boolean readBoolean();
  
  public boolean readBoolean(boolean paramBoolean, int paramInt) {
    return !readField(paramInt) ? paramBoolean : readBoolean();
  }
  
  protected boolean[] readBooleanArray() {
    int j = readInt();
    if (j < 0)
      return null; 
    boolean[] arrayOfBoolean = new boolean[j];
    int i = 0;
    while (true) {
      boolean[] arrayOfBoolean1 = arrayOfBoolean;
      if (i < j) {
        boolean bool;
        if (readInt() != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        arrayOfBoolean[i] = bool;
        i++;
        continue;
      } 
      return arrayOfBoolean1;
    } 
  }
  
  public boolean[] readBooleanArray(boolean[] paramArrayOfboolean, int paramInt) {
    return !readField(paramInt) ? paramArrayOfboolean : readBooleanArray();
  }
  
  protected abstract Bundle readBundle();
  
  public Bundle readBundle(Bundle paramBundle, int paramInt) {
    return !readField(paramInt) ? paramBundle : readBundle();
  }
  
  public byte readByte(byte paramByte, int paramInt) {
    return !readField(paramInt) ? paramByte : (byte)(readInt() & 0xFF);
  }
  
  protected abstract byte[] readByteArray();
  
  public byte[] readByteArray(byte[] paramArrayOfbyte, int paramInt) {
    return !readField(paramInt) ? paramArrayOfbyte : readByteArray();
  }
  
  public char[] readCharArray(char[] paramArrayOfchar, int paramInt) {
    if (!readField(paramInt))
      return paramArrayOfchar; 
    int i = readInt();
    if (i < 0)
      return null; 
    paramArrayOfchar = new char[i];
    for (paramInt = 0; paramInt < i; paramInt++)
      paramArrayOfchar[paramInt] = (char)readInt(); 
    return paramArrayOfchar;
  }
  
  protected abstract double readDouble();
  
  public double readDouble(double paramDouble, int paramInt) {
    return !readField(paramInt) ? paramDouble : readDouble();
  }
  
  protected double[] readDoubleArray() {
    int j = readInt();
    if (j < 0)
      return null; 
    double[] arrayOfDouble = new double[j];
    int i = 0;
    while (true) {
      double[] arrayOfDouble1 = arrayOfDouble;
      if (i < j) {
        arrayOfDouble[i] = readDouble();
        i++;
        continue;
      } 
      return arrayOfDouble1;
    } 
  }
  
  public double[] readDoubleArray(double[] paramArrayOfdouble, int paramInt) {
    return !readField(paramInt) ? paramArrayOfdouble : readDoubleArray();
  }
  
  public Exception readException(Exception paramException, int paramInt) {
    if (readField(paramInt)) {
      paramInt = readExceptionCode();
      if (paramInt != 0)
        return readException(paramInt, readString()); 
    } 
    return paramException;
  }
  
  protected abstract boolean readField(int paramInt);
  
  protected abstract float readFloat();
  
  public float readFloat(float paramFloat, int paramInt) {
    return !readField(paramInt) ? paramFloat : readFloat();
  }
  
  protected float[] readFloatArray() {
    int j = readInt();
    if (j < 0)
      return null; 
    float[] arrayOfFloat = new float[j];
    int i = 0;
    while (true) {
      float[] arrayOfFloat1 = arrayOfFloat;
      if (i < j) {
        arrayOfFloat[i] = readFloat();
        i++;
        continue;
      } 
      return arrayOfFloat1;
    } 
  }
  
  public float[] readFloatArray(float[] paramArrayOffloat, int paramInt) {
    return !readField(paramInt) ? paramArrayOffloat : readFloatArray();
  }
  
  protected abstract int readInt();
  
  public int readInt(int paramInt1, int paramInt2) {
    return !readField(paramInt2) ? paramInt1 : readInt();
  }
  
  protected int[] readIntArray() {
    int j = readInt();
    if (j < 0)
      return null; 
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (true) {
      int[] arrayOfInt1 = arrayOfInt;
      if (i < j) {
        arrayOfInt[i] = readInt();
        i++;
        continue;
      } 
      return arrayOfInt1;
    } 
  }
  
  public int[] readIntArray(int[] paramArrayOfint, int paramInt) {
    return !readField(paramInt) ? paramArrayOfint : readIntArray();
  }
  
  public <T> List<T> readList(List<T> paramList, int paramInt) {
    return !readField(paramInt) ? paramList : readCollection(paramInt, new ArrayList<T>());
  }
  
  protected abstract long readLong();
  
  public long readLong(long paramLong, int paramInt) {
    return !readField(paramInt) ? paramLong : readLong();
  }
  
  protected long[] readLongArray() {
    int j = readInt();
    if (j < 0)
      return null; 
    long[] arrayOfLong = new long[j];
    int i = 0;
    while (true) {
      long[] arrayOfLong1 = arrayOfLong;
      if (i < j) {
        arrayOfLong[i] = readLong();
        i++;
        continue;
      } 
      return arrayOfLong1;
    } 
  }
  
  public long[] readLongArray(long[] paramArrayOflong, int paramInt) {
    return !readField(paramInt) ? paramArrayOflong : readLongArray();
  }
  
  protected abstract <T extends Parcelable> T readParcelable();
  
  public <T extends Parcelable> T readParcelable(T paramT, int paramInt) {
    return !readField(paramInt) ? paramT : readParcelable();
  }
  
  protected Serializable readSerializable() {
    String str = readString();
    if (str == null)
      return null; 
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(readByteArray());
    try {
      return (Serializable)(new ObjectInputStream(byteArrayInputStream) {
          protected Class<?> resolveClass(ObjectStreamClass param1ObjectStreamClass) throws IOException, ClassNotFoundException {
            Class<?> clazz = Class.forName(param1ObjectStreamClass.getName(), false, getClass().getClassLoader());
            return (clazz != null) ? clazz : super.resolveClass(param1ObjectStreamClass);
          }
        }).readObject();
    } catch (IOException iOException) {
      throw new RuntimeException("VersionedParcelable encountered IOException reading a Serializable object (name = " + str + ")", iOException);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new RuntimeException("VersionedParcelable encountered ClassNotFoundException reading a Serializable object (name = " + str + ")", classNotFoundException);
    } 
  }
  
  public <T> Set<T> readSet(Set<T> paramSet, int paramInt) {
    return !readField(paramInt) ? paramSet : (Set<T>)readCollection(paramInt, new ArraySet());
  }
  
  @RequiresApi(api = 21)
  public Size readSize(Size paramSize, int paramInt) {
    return !readField(paramInt) ? paramSize : (readBoolean() ? new Size(readInt(), readInt()) : null);
  }
  
  @RequiresApi(api = 21)
  public SizeF readSizeF(SizeF paramSizeF, int paramInt) {
    return !readField(paramInt) ? paramSizeF : (readBoolean() ? new SizeF(readFloat(), readFloat()) : null);
  }
  
  public SparseBooleanArray readSparseBooleanArray(SparseBooleanArray paramSparseBooleanArray, int paramInt) {
    if (!readField(paramInt))
      return paramSparseBooleanArray; 
    int i = readInt();
    if (i < 0)
      return null; 
    paramSparseBooleanArray = new SparseBooleanArray(i);
    for (paramInt = 0; paramInt < i; paramInt++)
      paramSparseBooleanArray.put(readInt(), readBoolean()); 
    return paramSparseBooleanArray;
  }
  
  protected abstract String readString();
  
  public String readString(String paramString, int paramInt) {
    return !readField(paramInt) ? paramString : readString();
  }
  
  protected abstract IBinder readStrongBinder();
  
  public IBinder readStrongBinder(IBinder paramIBinder, int paramInt) {
    return !readField(paramInt) ? paramIBinder : readStrongBinder();
  }
  
  protected <T extends VersionedParcelable> T readVersionedParcelable() {
    String str = readString();
    return (str == null) ? null : readFromParcel(str, createSubParcel());
  }
  
  public <T extends VersionedParcelable> T readVersionedParcelable(T paramT, int paramInt) {
    return !readField(paramInt) ? paramT : readVersionedParcelable();
  }
  
  protected abstract void setOutputField(int paramInt);
  
  public void setSerializationFlags(boolean paramBoolean1, boolean paramBoolean2) {}
  
  protected <T> void writeArray(T[] paramArrayOfT) {
    if (paramArrayOfT == null) {
      writeInt(-1);
      return;
    } 
    int i1 = paramArrayOfT.length;
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    int i = 0;
    writeInt(i1);
    if (i1 > 0) {
      int i2 = getType(paramArrayOfT[0]);
      writeInt(i2);
      switch (i2) {
        default:
          return;
        case 1:
          while (true) {
            if (i < i1) {
              writeVersionedParcelable((VersionedParcelable)paramArrayOfT[i]);
              i++;
              continue;
            } 
            return;
          } 
        case 4:
          while (true) {
            if (j < i1) {
              writeString((String)paramArrayOfT[j]);
              j++;
              continue;
            } 
            return;
          } 
        case 2:
          while (true) {
            if (k < i1) {
              writeParcelable((Parcelable)paramArrayOfT[k]);
              k++;
              continue;
            } 
            return;
          } 
        case 3:
          while (true) {
            if (m < i1) {
              writeSerializable((Serializable)paramArrayOfT[m]);
              m++;
              continue;
            } 
            return;
          } 
        case 5:
          break;
      } 
      while (true) {
        if (n < i1) {
          writeStrongBinder((IBinder)paramArrayOfT[n]);
          n++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public <T> void writeArray(T[] paramArrayOfT, int paramInt) {
    setOutputField(paramInt);
    writeArray(paramArrayOfT);
  }
  
  protected abstract void writeBoolean(boolean paramBoolean);
  
  public void writeBoolean(boolean paramBoolean, int paramInt) {
    setOutputField(paramInt);
    writeBoolean(paramBoolean);
  }
  
  protected void writeBooleanArray(boolean[] paramArrayOfboolean) {
    if (paramArrayOfboolean != null) {
      int j = paramArrayOfboolean.length;
      writeInt(j);
      for (int i = 0; i < j; i++) {
        boolean bool;
        if (paramArrayOfboolean[i]) {
          bool = true;
        } else {
          bool = false;
        } 
        writeInt(bool);
      } 
    } else {
      writeInt(-1);
    } 
  }
  
  public void writeBooleanArray(boolean[] paramArrayOfboolean, int paramInt) {
    setOutputField(paramInt);
    writeBooleanArray(paramArrayOfboolean);
  }
  
  protected abstract void writeBundle(Bundle paramBundle);
  
  public void writeBundle(Bundle paramBundle, int paramInt) {
    setOutputField(paramInt);
    writeBundle(paramBundle);
  }
  
  public void writeByte(byte paramByte, int paramInt) {
    setOutputField(paramInt);
    writeInt(paramByte);
  }
  
  protected abstract void writeByteArray(byte[] paramArrayOfbyte);
  
  public void writeByteArray(byte[] paramArrayOfbyte, int paramInt) {
    setOutputField(paramInt);
    writeByteArray(paramArrayOfbyte);
  }
  
  protected abstract void writeByteArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  public void writeByteArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    setOutputField(paramInt3);
    writeByteArray(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void writeCharArray(char[] paramArrayOfchar, int paramInt) {
    setOutputField(paramInt);
    if (paramArrayOfchar != null) {
      int i = paramArrayOfchar.length;
      writeInt(i);
      for (paramInt = 0; paramInt < i; paramInt++)
        writeInt(paramArrayOfchar[paramInt]); 
    } else {
      writeInt(-1);
    } 
  }
  
  protected abstract void writeDouble(double paramDouble);
  
  public void writeDouble(double paramDouble, int paramInt) {
    setOutputField(paramInt);
    writeDouble(paramDouble);
  }
  
  protected void writeDoubleArray(double[] paramArrayOfdouble) {
    if (paramArrayOfdouble != null) {
      int j = paramArrayOfdouble.length;
      writeInt(j);
      for (int i = 0; i < j; i++)
        writeDouble(paramArrayOfdouble[i]); 
    } else {
      writeInt(-1);
    } 
  }
  
  public void writeDoubleArray(double[] paramArrayOfdouble, int paramInt) {
    setOutputField(paramInt);
    writeDoubleArray(paramArrayOfdouble);
  }
  
  public void writeException(Exception paramException, int paramInt) {
    setOutputField(paramInt);
    if (paramException == null) {
      writeNoException();
      return;
    } 
    paramInt = 0;
    if (paramException instanceof Parcelable && paramException.getClass().getClassLoader() == Parcelable.class.getClassLoader()) {
      paramInt = -9;
    } else if (paramException instanceof SecurityException) {
      paramInt = -1;
    } else if (paramException instanceof BadParcelableException) {
      paramInt = -2;
    } else if (paramException instanceof IllegalArgumentException) {
      paramInt = -3;
    } else if (paramException instanceof NullPointerException) {
      paramInt = -4;
    } else if (paramException instanceof IllegalStateException) {
      paramInt = -5;
    } else if (paramException instanceof NetworkOnMainThreadException) {
      paramInt = -6;
    } else if (paramException instanceof UnsupportedOperationException) {
      paramInt = -7;
    } 
    writeInt(paramInt);
    if (paramInt == 0) {
      if (paramException instanceof RuntimeException)
        throw (RuntimeException)paramException; 
      throw new RuntimeException(paramException);
    } 
    writeString(paramException.getMessage());
    switch (paramInt) {
      default:
        return;
      case -9:
        break;
    } 
    writeParcelable((Parcelable)paramException);
  }
  
  protected abstract void writeFloat(float paramFloat);
  
  public void writeFloat(float paramFloat, int paramInt) {
    setOutputField(paramInt);
    writeFloat(paramFloat);
  }
  
  protected void writeFloatArray(float[] paramArrayOffloat) {
    if (paramArrayOffloat != null) {
      int j = paramArrayOffloat.length;
      writeInt(j);
      for (int i = 0; i < j; i++)
        writeFloat(paramArrayOffloat[i]); 
    } else {
      writeInt(-1);
    } 
  }
  
  public void writeFloatArray(float[] paramArrayOffloat, int paramInt) {
    setOutputField(paramInt);
    writeFloatArray(paramArrayOffloat);
  }
  
  protected abstract void writeInt(int paramInt);
  
  public void writeInt(int paramInt1, int paramInt2) {
    setOutputField(paramInt2);
    writeInt(paramInt1);
  }
  
  protected void writeIntArray(int[] paramArrayOfint) {
    if (paramArrayOfint != null) {
      int j = paramArrayOfint.length;
      writeInt(j);
      for (int i = 0; i < j; i++)
        writeInt(paramArrayOfint[i]); 
    } else {
      writeInt(-1);
    } 
  }
  
  public void writeIntArray(int[] paramArrayOfint, int paramInt) {
    setOutputField(paramInt);
    writeIntArray(paramArrayOfint);
  }
  
  public <T> void writeList(List<T> paramList, int paramInt) {
    writeCollection(paramList, paramInt);
  }
  
  protected abstract void writeLong(long paramLong);
  
  public void writeLong(long paramLong, int paramInt) {
    setOutputField(paramInt);
    writeLong(paramLong);
  }
  
  protected void writeLongArray(long[] paramArrayOflong) {
    if (paramArrayOflong != null) {
      int j = paramArrayOflong.length;
      writeInt(j);
      for (int i = 0; i < j; i++)
        writeLong(paramArrayOflong[i]); 
    } else {
      writeInt(-1);
    } 
  }
  
  public void writeLongArray(long[] paramArrayOflong, int paramInt) {
    setOutputField(paramInt);
    writeLongArray(paramArrayOflong);
  }
  
  protected void writeNoException() {
    writeInt(0);
  }
  
  protected abstract void writeParcelable(Parcelable paramParcelable);
  
  public void writeParcelable(Parcelable paramParcelable, int paramInt) {
    setOutputField(paramInt);
    writeParcelable(paramParcelable);
  }
  
  public void writeSerializable(Serializable paramSerializable, int paramInt) {
    setOutputField(paramInt);
    writeSerializable(paramSerializable);
  }
  
  public <T> void writeSet(Set<T> paramSet, int paramInt) {
    writeCollection(paramSet, paramInt);
  }
  
  @RequiresApi(api = 21)
  public void writeSize(Size paramSize, int paramInt) {
    boolean bool;
    setOutputField(paramInt);
    if (paramSize != null) {
      bool = true;
    } else {
      bool = false;
    } 
    writeBoolean(bool);
    if (paramSize != null) {
      writeInt(paramSize.getWidth());
      writeInt(paramSize.getHeight());
    } 
  }
  
  @RequiresApi(api = 21)
  public void writeSizeF(SizeF paramSizeF, int paramInt) {
    boolean bool;
    setOutputField(paramInt);
    if (paramSizeF != null) {
      bool = true;
    } else {
      bool = false;
    } 
    writeBoolean(bool);
    if (paramSizeF != null) {
      writeFloat(paramSizeF.getWidth());
      writeFloat(paramSizeF.getHeight());
    } 
  }
  
  public void writeSparseBooleanArray(SparseBooleanArray paramSparseBooleanArray, int paramInt) {
    setOutputField(paramInt);
    if (paramSparseBooleanArray == null) {
      writeInt(-1);
      return;
    } 
    int i = paramSparseBooleanArray.size();
    writeInt(i);
    paramInt = 0;
    while (true) {
      if (paramInt < i) {
        writeInt(paramSparseBooleanArray.keyAt(paramInt));
        writeBoolean(paramSparseBooleanArray.valueAt(paramInt));
        paramInt++;
        continue;
      } 
      return;
    } 
  }
  
  protected abstract void writeString(String paramString);
  
  public void writeString(String paramString, int paramInt) {
    setOutputField(paramInt);
    writeString(paramString);
  }
  
  protected abstract void writeStrongBinder(IBinder paramIBinder);
  
  public void writeStrongBinder(IBinder paramIBinder, int paramInt) {
    setOutputField(paramInt);
    writeStrongBinder(paramIBinder);
  }
  
  protected abstract void writeStrongInterface(IInterface paramIInterface);
  
  public void writeStrongInterface(IInterface paramIInterface, int paramInt) {
    setOutputField(paramInt);
    writeStrongInterface(paramIInterface);
  }
  
  protected void writeVersionedParcelable(VersionedParcelable paramVersionedParcelable) {
    if (paramVersionedParcelable == null) {
      writeString(null);
      return;
    } 
    writeVersionedParcelableCreator(paramVersionedParcelable);
    VersionedParcel versionedParcel = createSubParcel();
    writeToParcel(paramVersionedParcelable, versionedParcel);
    versionedParcel.closeField();
  }
  
  public void writeVersionedParcelable(VersionedParcelable paramVersionedParcelable, int paramInt) {
    setOutputField(paramInt);
    writeVersionedParcelable(paramVersionedParcelable);
  }
  
  public static class ParcelException extends RuntimeException {
    public ParcelException(Throwable param1Throwable) {
      super(param1Throwable);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/versionedparcelable/VersionedParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
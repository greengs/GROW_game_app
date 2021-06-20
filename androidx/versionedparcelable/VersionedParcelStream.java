package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.annotation.RestrictTo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY})
class VersionedParcelStream extends VersionedParcel {
  private static final int TYPE_BOOLEAN = 5;
  
  private static final int TYPE_BOOLEAN_ARRAY = 6;
  
  private static final int TYPE_DOUBLE = 7;
  
  private static final int TYPE_DOUBLE_ARRAY = 8;
  
  private static final int TYPE_FLOAT = 13;
  
  private static final int TYPE_FLOAT_ARRAY = 14;
  
  private static final int TYPE_INT = 9;
  
  private static final int TYPE_INT_ARRAY = 10;
  
  private static final int TYPE_LONG = 11;
  
  private static final int TYPE_LONG_ARRAY = 12;
  
  private static final int TYPE_NULL = 0;
  
  private static final int TYPE_STRING = 3;
  
  private static final int TYPE_STRING_ARRAY = 4;
  
  private static final int TYPE_SUB_BUNDLE = 1;
  
  private static final int TYPE_SUB_PERSISTABLE_BUNDLE = 2;
  
  private static final Charset UTF_16 = Charset.forName("UTF-16");
  
  private final SparseArray<InputBuffer> mCachedFields;
  
  private DataInputStream mCurrentInput;
  
  private DataOutputStream mCurrentOutput;
  
  private FieldBuffer mFieldBuffer;
  
  private boolean mIgnoreParcelables;
  
  private final DataInputStream mMasterInput;
  
  private final DataOutputStream mMasterOutput;
  
  public VersionedParcelStream(InputStream paramInputStream, OutputStream paramOutputStream) {
    DataOutputStream dataOutputStream;
    this.mCachedFields = new SparseArray();
    if (paramInputStream != null) {
      paramInputStream = new DataInputStream(paramInputStream);
    } else {
      paramInputStream = null;
    } 
    this.mMasterInput = (DataInputStream)paramInputStream;
    paramInputStream = inputStream;
    if (paramOutputStream != null)
      dataOutputStream = new DataOutputStream(paramOutputStream); 
    this.mMasterOutput = dataOutputStream;
    this.mCurrentInput = this.mMasterInput;
    this.mCurrentOutput = this.mMasterOutput;
  }
  
  private void readObject(int paramInt, String paramString, Bundle paramBundle) {
    switch (paramInt) {
      default:
        throw new RuntimeException("Unknown type " + paramInt);
      case 0:
        paramBundle.putParcelable(paramString, null);
        return;
      case 1:
        paramBundle.putBundle(paramString, readBundle());
        return;
      case 2:
        paramBundle.putBundle(paramString, readBundle());
        return;
      case 3:
        paramBundle.putString(paramString, readString());
        return;
      case 4:
        paramBundle.putStringArray(paramString, readArray(new String[0]));
        return;
      case 5:
        paramBundle.putBoolean(paramString, readBoolean());
        return;
      case 6:
        paramBundle.putBooleanArray(paramString, readBooleanArray());
        return;
      case 7:
        paramBundle.putDouble(paramString, readDouble());
        return;
      case 8:
        paramBundle.putDoubleArray(paramString, readDoubleArray());
        return;
      case 9:
        paramBundle.putInt(paramString, readInt());
        return;
      case 10:
        paramBundle.putIntArray(paramString, readIntArray());
        return;
      case 11:
        paramBundle.putLong(paramString, readLong());
        return;
      case 12:
        paramBundle.putLongArray(paramString, readLongArray());
        return;
      case 13:
        paramBundle.putFloat(paramString, readFloat());
        return;
      case 14:
        break;
    } 
    paramBundle.putFloatArray(paramString, readFloatArray());
  }
  
  private void writeObject(Object paramObject) {
    if (paramObject == null) {
      writeInt(0);
      return;
    } 
    if (paramObject instanceof Bundle) {
      writeInt(1);
      writeBundle((Bundle)paramObject);
      return;
    } 
    if (paramObject instanceof String) {
      writeInt(3);
      writeString((String)paramObject);
      return;
    } 
    if (paramObject instanceof String[]) {
      writeInt(4);
      writeArray((String[])paramObject);
      return;
    } 
    if (paramObject instanceof Boolean) {
      writeInt(5);
      writeBoolean(((Boolean)paramObject).booleanValue());
      return;
    } 
    if (paramObject instanceof boolean[]) {
      writeInt(6);
      writeBooleanArray((boolean[])paramObject);
      return;
    } 
    if (paramObject instanceof Double) {
      writeInt(7);
      writeDouble(((Double)paramObject).doubleValue());
      return;
    } 
    if (paramObject instanceof double[]) {
      writeInt(8);
      writeDoubleArray((double[])paramObject);
      return;
    } 
    if (paramObject instanceof Integer) {
      writeInt(9);
      writeInt(((Integer)paramObject).intValue());
      return;
    } 
    if (paramObject instanceof int[]) {
      writeInt(10);
      writeIntArray((int[])paramObject);
      return;
    } 
    if (paramObject instanceof Long) {
      writeInt(11);
      writeLong(((Long)paramObject).longValue());
      return;
    } 
    if (paramObject instanceof long[]) {
      writeInt(12);
      writeLongArray((long[])paramObject);
      return;
    } 
    if (paramObject instanceof Float) {
      writeInt(13);
      writeFloat(((Float)paramObject).floatValue());
      return;
    } 
    if (paramObject instanceof float[]) {
      writeInt(14);
      writeFloatArray((float[])paramObject);
      return;
    } 
    throw new IllegalArgumentException("Unsupported type " + paramObject.getClass());
  }
  
  public void closeField() {
    if (this.mFieldBuffer != null)
      try {
        if (this.mFieldBuffer.mOutput.size() != 0)
          this.mFieldBuffer.flushField(); 
        this.mFieldBuffer = null;
        return;
      } catch (IOException iOException) {
        throw new VersionedParcel.ParcelException(iOException);
      }  
  }
  
  protected VersionedParcel createSubParcel() {
    return new VersionedParcelStream(this.mCurrentInput, this.mCurrentOutput);
  }
  
  public boolean isStream() {
    return true;
  }
  
  public boolean readBoolean() {
    try {
      return this.mCurrentInput.readBoolean();
    } catch (IOException iOException) {
      throw new VersionedParcel.ParcelException(iOException);
    } 
  }
  
  public Bundle readBundle() {
    int j = readInt();
    if (j < 0)
      return null; 
    Bundle bundle = new Bundle();
    int i = 0;
    while (true) {
      String str;
      Bundle bundle1 = bundle;
      if (i < j) {
        str = readString();
        readObject(readInt(), str, bundle);
        i++;
        continue;
      } 
      return (Bundle)str;
    } 
  }
  
  public byte[] readByteArray() {
    try {
      int i = this.mCurrentInput.readInt();
      if (i > 0) {
        byte[] arrayOfByte = new byte[i];
        this.mCurrentInput.readFully(arrayOfByte);
        return arrayOfByte;
      } 
      return null;
    } catch (IOException iOException) {
      throw new VersionedParcel.ParcelException(iOException);
    } 
  }
  
  public double readDouble() {
    try {
      return this.mCurrentInput.readDouble();
    } catch (IOException iOException) {
      throw new VersionedParcel.ParcelException(iOException);
    } 
  }
  
  public boolean readField(int paramInt) {
    InputBuffer inputBuffer = (InputBuffer)this.mCachedFields.get(paramInt);
    if (inputBuffer != null) {
      this.mCachedFields.remove(paramInt);
      this.mCurrentInput = inputBuffer.mInputStream;
      return true;
    } 
    try {
      while (true) {
        int k = this.mMasterInput.readInt();
        int j = k & 0xFFFF;
        int i = j;
        if (j == 65535)
          i = this.mMasterInput.readInt(); 
        inputBuffer = new InputBuffer(k >> 16 & 0xFFFF, i, this.mMasterInput);
        if (inputBuffer.mFieldId == paramInt) {
          this.mCurrentInput = inputBuffer.mInputStream;
          return true;
        } 
        try {
          this.mCachedFields.put(inputBuffer.mFieldId, inputBuffer);
        } catch (IOException iOException) {
          break;
        } 
      } 
    } catch (IOException iOException) {}
    return false;
  }
  
  public float readFloat() {
    try {
      return this.mCurrentInput.readFloat();
    } catch (IOException iOException) {
      throw new VersionedParcel.ParcelException(iOException);
    } 
  }
  
  public int readInt() {
    try {
      return this.mCurrentInput.readInt();
    } catch (IOException iOException) {
      throw new VersionedParcel.ParcelException(iOException);
    } 
  }
  
  public long readLong() {
    try {
      return this.mCurrentInput.readLong();
    } catch (IOException iOException) {
      throw new VersionedParcel.ParcelException(iOException);
    } 
  }
  
  public <T extends Parcelable> T readParcelable() {
    return null;
  }
  
  public String readString() {
    try {
      int i = this.mCurrentInput.readInt();
      if (i > 0) {
        byte[] arrayOfByte = new byte[i];
        this.mCurrentInput.readFully(arrayOfByte);
        return new String(arrayOfByte, UTF_16);
      } 
      return null;
    } catch (IOException iOException) {
      throw new VersionedParcel.ParcelException(iOException);
    } 
  }
  
  public IBinder readStrongBinder() {
    return null;
  }
  
  public void setOutputField(int paramInt) {
    closeField();
    this.mFieldBuffer = new FieldBuffer(paramInt, this.mMasterOutput);
    this.mCurrentOutput = this.mFieldBuffer.mDataStream;
  }
  
  public void setSerializationFlags(boolean paramBoolean1, boolean paramBoolean2) {
    if (!paramBoolean1)
      throw new RuntimeException("Serialization of this object is not allowed"); 
    this.mIgnoreParcelables = paramBoolean2;
  }
  
  public void writeBoolean(boolean paramBoolean) {
    try {
      this.mCurrentOutput.writeBoolean(paramBoolean);
      return;
    } catch (IOException iOException) {
      throw new VersionedParcel.ParcelException(iOException);
    } 
  }
  
  public void writeBundle(Bundle paramBundle) {
    if (paramBundle != null) {
      try {
        Set set = paramBundle.keySet();
        this.mCurrentOutput.writeInt(set.size());
        for (String str : set) {
          writeString(str);
          writeObject(paramBundle.get(str));
        } 
      } catch (IOException iOException) {
        throw new VersionedParcel.ParcelException(iOException);
      } 
    } else {
      this.mCurrentOutput.writeInt(-1);
    } 
  }
  
  public void writeByteArray(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte != null)
      try {
        this.mCurrentOutput.writeInt(paramArrayOfbyte.length);
        this.mCurrentOutput.write(paramArrayOfbyte);
        return;
      } catch (IOException iOException) {
        throw new VersionedParcel.ParcelException(iOException);
      }  
    this.mCurrentOutput.writeInt(-1);
  }
  
  public void writeByteArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramArrayOfbyte != null)
      try {
        this.mCurrentOutput.writeInt(paramInt2);
        this.mCurrentOutput.write(paramArrayOfbyte, paramInt1, paramInt2);
        return;
      } catch (IOException iOException) {
        throw new VersionedParcel.ParcelException(iOException);
      }  
    this.mCurrentOutput.writeInt(-1);
  }
  
  public void writeDouble(double paramDouble) {
    try {
      this.mCurrentOutput.writeDouble(paramDouble);
      return;
    } catch (IOException iOException) {
      throw new VersionedParcel.ParcelException(iOException);
    } 
  }
  
  public void writeFloat(float paramFloat) {
    try {
      this.mCurrentOutput.writeFloat(paramFloat);
      return;
    } catch (IOException iOException) {
      throw new VersionedParcel.ParcelException(iOException);
    } 
  }
  
  public void writeInt(int paramInt) {
    try {
      this.mCurrentOutput.writeInt(paramInt);
      return;
    } catch (IOException iOException) {
      throw new VersionedParcel.ParcelException(iOException);
    } 
  }
  
  public void writeLong(long paramLong) {
    try {
      this.mCurrentOutput.writeLong(paramLong);
      return;
    } catch (IOException iOException) {
      throw new VersionedParcel.ParcelException(iOException);
    } 
  }
  
  public void writeParcelable(Parcelable paramParcelable) {
    if (!this.mIgnoreParcelables)
      throw new RuntimeException("Parcelables cannot be written to an OutputStream"); 
  }
  
  public void writeString(String paramString) {
    if (paramString != null)
      try {
        byte[] arrayOfByte = paramString.getBytes(UTF_16);
        this.mCurrentOutput.writeInt(arrayOfByte.length);
        this.mCurrentOutput.write(arrayOfByte);
        return;
      } catch (IOException iOException) {
        throw new VersionedParcel.ParcelException(iOException);
      }  
    this.mCurrentOutput.writeInt(-1);
  }
  
  public void writeStrongBinder(IBinder paramIBinder) {
    if (!this.mIgnoreParcelables)
      throw new RuntimeException("Binders cannot be written to an OutputStream"); 
  }
  
  public void writeStrongInterface(IInterface paramIInterface) {
    if (!this.mIgnoreParcelables)
      throw new RuntimeException("Binders cannot be written to an OutputStream"); 
  }
  
  private static class FieldBuffer {
    final DataOutputStream mDataStream = new DataOutputStream(this.mOutput);
    
    private final int mFieldId;
    
    final ByteArrayOutputStream mOutput = new ByteArrayOutputStream();
    
    private final DataOutputStream mTarget;
    
    FieldBuffer(int param1Int, DataOutputStream param1DataOutputStream) {
      this.mFieldId = param1Int;
      this.mTarget = param1DataOutputStream;
    }
    
    void flushField() throws IOException {
      int i;
      this.mDataStream.flush();
      int j = this.mOutput.size();
      int k = this.mFieldId;
      if (j >= 65535) {
        i = 65535;
      } else {
        i = j;
      } 
      this.mTarget.writeInt(k << 16 | i);
      if (j >= 65535)
        this.mTarget.writeInt(j); 
      this.mOutput.writeTo(this.mTarget);
    }
  }
  
  private static class InputBuffer {
    final int mFieldId;
    
    final DataInputStream mInputStream;
    
    private final int mSize;
    
    InputBuffer(int param1Int1, int param1Int2, DataInputStream param1DataInputStream) throws IOException {
      this.mSize = param1Int2;
      this.mFieldId = param1Int1;
      byte[] arrayOfByte = new byte[this.mSize];
      param1DataInputStream.readFully(arrayOfByte);
      this.mInputStream = new DataInputStream(new ByteArrayInputStream(arrayOfByte));
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/versionedparcelable/VersionedParcelStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package androidx.core.os;

import android.os.Parcel;

public final class ParcelCompat {
  public static boolean readBoolean(Parcel paramParcel) {
    return (paramParcel.readInt() != 0);
  }
  
  public static void writeBoolean(Parcel paramParcel, boolean paramBoolean) {
    boolean bool;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    paramParcel.writeInt(bool);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/os/ParcelCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
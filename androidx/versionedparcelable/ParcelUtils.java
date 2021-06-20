package androidx.versionedparcelable;

import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import java.io.InputStream;
import java.io.OutputStream;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ParcelUtils {
  public static <T extends VersionedParcelable> T fromInputStream(InputStream paramInputStream) {
    return (new VersionedParcelStream(paramInputStream, null)).readVersionedParcelable();
  }
  
  public static <T extends VersionedParcelable> T fromParcelable(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof ParcelImpl))
      throw new IllegalArgumentException("Invalid parcel"); 
    return ((ParcelImpl)paramParcelable).getVersionedParcel();
  }
  
  public static void toOutputStream(VersionedParcelable paramVersionedParcelable, OutputStream paramOutputStream) {
    VersionedParcelStream versionedParcelStream = new VersionedParcelStream(null, paramOutputStream);
    versionedParcelStream.writeVersionedParcelable(paramVersionedParcelable);
    versionedParcelStream.closeField();
  }
  
  public static Parcelable toParcelable(VersionedParcelable paramVersionedParcelable) {
    return new ParcelImpl(paramVersionedParcelable);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/versionedparcelable/ParcelUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
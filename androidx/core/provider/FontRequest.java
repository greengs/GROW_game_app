package androidx.core.provider;

import android.util.Base64;
import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.util.List;

public final class FontRequest {
  private final List<List<byte[]>> mCertificates;
  
  private final int mCertificatesArray;
  
  private final String mIdentifier;
  
  private final String mProviderAuthority;
  
  private final String mProviderPackage;
  
  private final String mQuery;
  
  public FontRequest(@NonNull String paramString1, @NonNull String paramString2, @NonNull String paramString3, @ArrayRes int paramInt) {
    boolean bool;
    this.mProviderAuthority = (String)Preconditions.checkNotNull(paramString1);
    this.mProviderPackage = (String)Preconditions.checkNotNull(paramString2);
    this.mQuery = (String)Preconditions.checkNotNull(paramString3);
    this.mCertificates = null;
    if (paramInt != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool);
    this.mCertificatesArray = paramInt;
    this.mIdentifier = this.mProviderAuthority + "-" + this.mProviderPackage + "-" + this.mQuery;
  }
  
  public FontRequest(@NonNull String paramString1, @NonNull String paramString2, @NonNull String paramString3, @NonNull List<List<byte[]>> paramList) {
    this.mProviderAuthority = (String)Preconditions.checkNotNull(paramString1);
    this.mProviderPackage = (String)Preconditions.checkNotNull(paramString2);
    this.mQuery = (String)Preconditions.checkNotNull(paramString3);
    this.mCertificates = (List<List<byte[]>>)Preconditions.checkNotNull(paramList);
    this.mCertificatesArray = 0;
    this.mIdentifier = this.mProviderAuthority + "-" + this.mProviderPackage + "-" + this.mQuery;
  }
  
  @Nullable
  public List<List<byte[]>> getCertificates() {
    return this.mCertificates;
  }
  
  @ArrayRes
  public int getCertificatesArrayResId() {
    return this.mCertificatesArray;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public String getIdentifier() {
    return this.mIdentifier;
  }
  
  @NonNull
  public String getProviderAuthority() {
    return this.mProviderAuthority;
  }
  
  @NonNull
  public String getProviderPackage() {
    return this.mProviderPackage;
  }
  
  @NonNull
  public String getQuery() {
    return this.mQuery;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("FontRequest {mProviderAuthority: " + this.mProviderAuthority + ", mProviderPackage: " + this.mProviderPackage + ", mQuery: " + this.mQuery + ", mCertificates:");
    for (int i = 0; i < this.mCertificates.size(); i++) {
      stringBuilder.append(" [");
      List<byte[]> list = this.mCertificates.get(i);
      for (int j = 0; j < list.size(); j++) {
        stringBuilder.append(" \"");
        stringBuilder.append(Base64.encodeToString(list.get(j), 0));
        stringBuilder.append("\"");
      } 
      stringBuilder.append(" ]");
    } 
    stringBuilder.append("}");
    stringBuilder.append("mCertificatesArray: " + this.mCertificatesArray);
    return stringBuilder.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/provider/FontRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package androidx.core.hardware.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.core.os.CancellationSignal;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

public final class FingerprintManagerCompat {
  private final Context mContext;
  
  private FingerprintManagerCompat(Context paramContext) {
    this.mContext = paramContext;
  }
  
  @NonNull
  public static FingerprintManagerCompat from(@NonNull Context paramContext) {
    return new FingerprintManagerCompat(paramContext);
  }
  
  @Nullable
  @RequiresApi(23)
  private static FingerprintManager getFingerprintManagerOrNull(@NonNull Context paramContext) {
    return paramContext.getPackageManager().hasSystemFeature("android.hardware.fingerprint") ? (FingerprintManager)paramContext.getSystemService(FingerprintManager.class) : null;
  }
  
  @RequiresApi(23)
  static CryptoObject unwrapCryptoObject(FingerprintManager.CryptoObject paramCryptoObject) {
    if (paramCryptoObject != null) {
      if (paramCryptoObject.getCipher() != null)
        return new CryptoObject(paramCryptoObject.getCipher()); 
      if (paramCryptoObject.getSignature() != null)
        return new CryptoObject(paramCryptoObject.getSignature()); 
      if (paramCryptoObject.getMac() != null)
        return new CryptoObject(paramCryptoObject.getMac()); 
    } 
    return null;
  }
  
  @RequiresApi(23)
  private static FingerprintManager.AuthenticationCallback wrapCallback(final AuthenticationCallback callback) {
    return new FingerprintManager.AuthenticationCallback() {
        public void onAuthenticationError(int param1Int, CharSequence param1CharSequence) {
          callback.onAuthenticationError(param1Int, param1CharSequence);
        }
        
        public void onAuthenticationFailed() {
          callback.onAuthenticationFailed();
        }
        
        public void onAuthenticationHelp(int param1Int, CharSequence param1CharSequence) {
          callback.onAuthenticationHelp(param1Int, param1CharSequence);
        }
        
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult param1AuthenticationResult) {
          callback.onAuthenticationSucceeded(new FingerprintManagerCompat.AuthenticationResult(FingerprintManagerCompat.unwrapCryptoObject(param1AuthenticationResult.getCryptoObject())));
        }
      };
  }
  
  @RequiresApi(23)
  private static FingerprintManager.CryptoObject wrapCryptoObject(CryptoObject paramCryptoObject) {
    if (paramCryptoObject != null) {
      if (paramCryptoObject.getCipher() != null)
        return new FingerprintManager.CryptoObject(paramCryptoObject.getCipher()); 
      if (paramCryptoObject.getSignature() != null)
        return new FingerprintManager.CryptoObject(paramCryptoObject.getSignature()); 
      if (paramCryptoObject.getMac() != null)
        return new FingerprintManager.CryptoObject(paramCryptoObject.getMac()); 
    } 
    return null;
  }
  
  @RequiresPermission("android.permission.USE_FINGERPRINT")
  public void authenticate(@Nullable CryptoObject paramCryptoObject, int paramInt, @Nullable CancellationSignal paramCancellationSignal, @NonNull AuthenticationCallback paramAuthenticationCallback, @Nullable Handler paramHandler) {
    if (Build.VERSION.SDK_INT >= 23) {
      FingerprintManager fingerprintManager = getFingerprintManagerOrNull(this.mContext);
      if (fingerprintManager != null) {
        if (paramCancellationSignal != null) {
          CancellationSignal cancellationSignal = (CancellationSignal)paramCancellationSignal.getCancellationSignalObject();
        } else {
          paramCancellationSignal = null;
        } 
        fingerprintManager.authenticate(wrapCryptoObject(paramCryptoObject), (CancellationSignal)paramCancellationSignal, paramInt, wrapCallback(paramAuthenticationCallback), paramHandler);
      } 
    } 
  }
  
  @RequiresPermission("android.permission.USE_FINGERPRINT")
  public boolean hasEnrolledFingerprints() {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (Build.VERSION.SDK_INT >= 23) {
      FingerprintManager fingerprintManager = getFingerprintManagerOrNull(this.mContext);
      bool1 = bool2;
      if (fingerprintManager != null) {
        bool1 = bool2;
        if (fingerprintManager.hasEnrolledFingerprints())
          bool1 = true; 
      } 
    } 
    return bool1;
  }
  
  @RequiresPermission("android.permission.USE_FINGERPRINT")
  public boolean isHardwareDetected() {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (Build.VERSION.SDK_INT >= 23) {
      FingerprintManager fingerprintManager = getFingerprintManagerOrNull(this.mContext);
      bool1 = bool2;
      if (fingerprintManager != null) {
        bool1 = bool2;
        if (fingerprintManager.isHardwareDetected())
          bool1 = true; 
      } 
    } 
    return bool1;
  }
  
  public static abstract class AuthenticationCallback {
    public void onAuthenticationError(int param1Int, CharSequence param1CharSequence) {}
    
    public void onAuthenticationFailed() {}
    
    public void onAuthenticationHelp(int param1Int, CharSequence param1CharSequence) {}
    
    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult param1AuthenticationResult) {}
  }
  
  public static final class AuthenticationResult {
    private final FingerprintManagerCompat.CryptoObject mCryptoObject;
    
    public AuthenticationResult(FingerprintManagerCompat.CryptoObject param1CryptoObject) {
      this.mCryptoObject = param1CryptoObject;
    }
    
    public FingerprintManagerCompat.CryptoObject getCryptoObject() {
      return this.mCryptoObject;
    }
  }
  
  public static class CryptoObject {
    private final Cipher mCipher;
    
    private final Mac mMac;
    
    private final Signature mSignature;
    
    public CryptoObject(@NonNull Signature param1Signature) {
      this.mSignature = param1Signature;
      this.mCipher = null;
      this.mMac = null;
    }
    
    public CryptoObject(@NonNull Cipher param1Cipher) {
      this.mCipher = param1Cipher;
      this.mSignature = null;
      this.mMac = null;
    }
    
    public CryptoObject(@NonNull Mac param1Mac) {
      this.mMac = param1Mac;
      this.mCipher = null;
      this.mSignature = null;
    }
    
    @Nullable
    public Cipher getCipher() {
      return this.mCipher;
    }
    
    @Nullable
    public Mac getMac() {
      return this.mMac;
    }
    
    @Nullable
    public Signature getSignature() {
      return this.mSignature;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/hardware/fingerprint/FingerprintManagerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
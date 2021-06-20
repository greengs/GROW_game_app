package androidx.core.content.res;

import android.content.res.Resources;
import android.os.Build;
import androidx.annotation.NonNull;

public final class ConfigurationHelper {
  public static int getDensityDpi(@NonNull Resources paramResources) {
    return (Build.VERSION.SDK_INT >= 17) ? (paramResources.getConfiguration()).densityDpi : (paramResources.getDisplayMetrics()).densityDpi;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/content/res/ConfigurationHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
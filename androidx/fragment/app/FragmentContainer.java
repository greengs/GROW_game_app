package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

public abstract class FragmentContainer {
  public Fragment instantiate(Context paramContext, String paramString, Bundle paramBundle) {
    return Fragment.instantiate(paramContext, paramString, paramBundle);
  }
  
  @Nullable
  public abstract View onFindViewById(@IdRes int paramInt);
  
  public abstract boolean onHasView();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/fragment/app/FragmentContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
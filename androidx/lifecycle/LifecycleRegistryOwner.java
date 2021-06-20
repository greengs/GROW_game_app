package androidx.lifecycle;

import androidx.annotation.NonNull;

@Deprecated
public interface LifecycleRegistryOwner extends LifecycleOwner {
  @NonNull
  LifecycleRegistry getLifecycle();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/LifecycleRegistryOwner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
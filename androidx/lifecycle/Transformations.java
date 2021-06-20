package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;

public class Transformations {
  @MainThread
  public static <X, Y> LiveData<Y> map(@NonNull LiveData<X> paramLiveData, @NonNull final Function<X, Y> mapFunction) {
    final MediatorLiveData<Y> result = new MediatorLiveData();
    mediatorLiveData.addSource(paramLiveData, new Observer<X>() {
          public void onChanged(@Nullable X param1X) {
            result.setValue(mapFunction.apply(param1X));
          }
        });
    return mediatorLiveData;
  }
  
  @MainThread
  public static <X, Y> LiveData<Y> switchMap(@NonNull LiveData<X> paramLiveData, @NonNull final Function<X, LiveData<Y>> switchMapFunction) {
    final MediatorLiveData<Y> result = new MediatorLiveData();
    mediatorLiveData.addSource(paramLiveData, new Observer<X>() {
          LiveData<Y> mSource;
          
          public void onChanged(@Nullable X param1X) {
            LiveData<Y> liveData = (LiveData)switchMapFunction.apply(param1X);
            if (this.mSource != liveData) {
              if (this.mSource != null)
                result.removeSource(this.mSource); 
              this.mSource = liveData;
              if (this.mSource != null) {
                result.addSource(this.mSource, new Observer() {
                      public void onChanged(@Nullable Y param2Y) {
                        result.setValue(param2Y);
                      }
                    });
                return;
              } 
            } 
          }
        });
    return mediatorLiveData;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/Transformations.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
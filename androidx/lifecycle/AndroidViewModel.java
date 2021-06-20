package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.annotation.NonNull;

public class AndroidViewModel extends ViewModel {
  @SuppressLint({"StaticFieldLeak"})
  private Application mApplication;
  
  public AndroidViewModel(@NonNull Application paramApplication) {
    this.mApplication = paramApplication;
  }
  
  @NonNull
  public <T extends Application> T getApplication() {
    return (T)this.mApplication;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/AndroidViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
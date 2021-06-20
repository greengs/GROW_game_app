package androidx.vectordrawable.graphics.drawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public interface Animatable2Compat extends Animatable {
  void clearAnimationCallbacks();
  
  void registerAnimationCallback(@NonNull AnimationCallback paramAnimationCallback);
  
  boolean unregisterAnimationCallback(@NonNull AnimationCallback paramAnimationCallback);
  
  public static abstract class AnimationCallback {
    Animatable2.AnimationCallback mPlatformCallback;
    
    @RequiresApi(23)
    Animatable2.AnimationCallback getPlatformCallback() {
      if (this.mPlatformCallback == null)
        this.mPlatformCallback = new Animatable2.AnimationCallback() {
            public void onAnimationEnd(Drawable param2Drawable) {
              Animatable2Compat.AnimationCallback.this.onAnimationEnd(param2Drawable);
            }
            
            public void onAnimationStart(Drawable param2Drawable) {
              Animatable2Compat.AnimationCallback.this.onAnimationStart(param2Drawable);
            }
          }; 
      return this.mPlatformCallback;
    }
    
    public void onAnimationEnd(Drawable param1Drawable) {}
    
    public void onAnimationStart(Drawable param1Drawable) {}
  }
  
  class null extends Animatable2.AnimationCallback {
    public void onAnimationEnd(Drawable param1Drawable) {
      this.this$0.onAnimationEnd(param1Drawable);
    }
    
    public void onAnimationStart(Drawable param1Drawable) {
      this.this$0.onAnimationStart(param1Drawable);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/vectordrawable/graphics/drawable/Animatable2Compat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
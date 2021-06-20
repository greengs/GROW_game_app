package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class TooltipCompatHandler implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
  private static final long HOVER_HIDE_TIMEOUT_MS = 15000L;
  
  private static final long HOVER_HIDE_TIMEOUT_SHORT_MS = 3000L;
  
  private static final long LONG_CLICK_HIDE_TIMEOUT_MS = 2500L;
  
  private static final String TAG = "TooltipCompatHandler";
  
  private static TooltipCompatHandler sActiveHandler;
  
  private static TooltipCompatHandler sPendingHandler;
  
  private final View mAnchor;
  
  private int mAnchorX;
  
  private int mAnchorY;
  
  private boolean mFromTouch;
  
  private final Runnable mHideRunnable = new Runnable() {
      public void run() {
        TooltipCompatHandler.this.hide();
      }
    };
  
  private final int mHoverSlop;
  
  private TooltipPopup mPopup;
  
  private final Runnable mShowRunnable = new Runnable() {
      public void run() {
        TooltipCompatHandler.this.show(false);
      }
    };
  
  private final CharSequence mTooltipText;
  
  private TooltipCompatHandler(View paramView, CharSequence paramCharSequence) {
    this.mAnchor = paramView;
    this.mTooltipText = paramCharSequence;
    this.mHoverSlop = ViewConfigurationCompat.getScaledHoverSlop(ViewConfiguration.get(this.mAnchor.getContext()));
    clearAnchorPos();
    this.mAnchor.setOnLongClickListener(this);
    this.mAnchor.setOnHoverListener(this);
  }
  
  private void cancelPendingShow() {
    this.mAnchor.removeCallbacks(this.mShowRunnable);
  }
  
  private void clearAnchorPos() {
    this.mAnchorX = Integer.MAX_VALUE;
    this.mAnchorY = Integer.MAX_VALUE;
  }
  
  private void scheduleShow() {
    this.mAnchor.postDelayed(this.mShowRunnable, ViewConfiguration.getLongPressTimeout());
  }
  
  private static void setPendingHandler(TooltipCompatHandler paramTooltipCompatHandler) {
    if (sPendingHandler != null)
      sPendingHandler.cancelPendingShow(); 
    sPendingHandler = paramTooltipCompatHandler;
    if (sPendingHandler != null)
      sPendingHandler.scheduleShow(); 
  }
  
  public static void setTooltipText(View paramView, CharSequence paramCharSequence) {
    if (sPendingHandler != null && sPendingHandler.mAnchor == paramView)
      setPendingHandler(null); 
    if (TextUtils.isEmpty(paramCharSequence)) {
      if (sActiveHandler != null && sActiveHandler.mAnchor == paramView)
        sActiveHandler.hide(); 
      paramView.setOnLongClickListener(null);
      paramView.setLongClickable(false);
      paramView.setOnHoverListener(null);
      return;
    } 
    new TooltipCompatHandler(paramView, paramCharSequence);
  }
  
  private boolean updateAnchorPos(MotionEvent paramMotionEvent) {
    int i = (int)paramMotionEvent.getX();
    int j = (int)paramMotionEvent.getY();
    if (Math.abs(i - this.mAnchorX) <= this.mHoverSlop && Math.abs(j - this.mAnchorY) <= this.mHoverSlop)
      return false; 
    this.mAnchorX = i;
    this.mAnchorY = j;
    return true;
  }
  
  void hide() {
    if (sActiveHandler == this) {
      sActiveHandler = null;
      if (this.mPopup != null) {
        this.mPopup.hide();
        this.mPopup = null;
        clearAnchorPos();
        this.mAnchor.removeOnAttachStateChangeListener(this);
      } else {
        Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
      } 
    } 
    if (sPendingHandler == this)
      setPendingHandler(null); 
    this.mAnchor.removeCallbacks(this.mHideRunnable);
  }
  
  public boolean onHover(View paramView, MotionEvent paramMotionEvent) {
    if (this.mPopup == null || !this.mFromTouch) {
      AccessibilityManager accessibilityManager = (AccessibilityManager)this.mAnchor.getContext().getSystemService("accessibility");
      if (!accessibilityManager.isEnabled() || !accessibilityManager.isTouchExplorationEnabled()) {
        switch (paramMotionEvent.getAction()) {
          default:
            return false;
          case 7:
            if (this.mAnchor.isEnabled() && this.mPopup == null && updateAnchorPos(paramMotionEvent)) {
              setPendingHandler(this);
              return false;
            } 
            return false;
          case 10:
            break;
        } 
        clearAnchorPos();
        hide();
        return false;
      } 
    } 
    return false;
  }
  
  public boolean onLongClick(View paramView) {
    this.mAnchorX = paramView.getWidth() / 2;
    this.mAnchorY = paramView.getHeight() / 2;
    show(true);
    return true;
  }
  
  public void onViewAttachedToWindow(View paramView) {}
  
  public void onViewDetachedFromWindow(View paramView) {
    hide();
  }
  
  void show(boolean paramBoolean) {
    long l;
    if (!ViewCompat.isAttachedToWindow(this.mAnchor))
      return; 
    setPendingHandler(null);
    if (sActiveHandler != null)
      sActiveHandler.hide(); 
    sActiveHandler = this;
    this.mFromTouch = paramBoolean;
    this.mPopup = new TooltipPopup(this.mAnchor.getContext());
    this.mPopup.show(this.mAnchor, this.mAnchorX, this.mAnchorY, this.mFromTouch, this.mTooltipText);
    this.mAnchor.addOnAttachStateChangeListener(this);
    if (this.mFromTouch) {
      l = 2500L;
    } else if ((ViewCompat.getWindowSystemUiVisibility(this.mAnchor) & 0x1) == 1) {
      l = 3000L - ViewConfiguration.getLongPressTimeout();
    } else {
      l = 15000L - ViewConfiguration.getLongPressTimeout();
    } 
    this.mAnchor.removeCallbacks(this.mHideRunnable);
    this.mAnchor.postDelayed(this.mHideRunnable, l);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/TooltipCompatHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
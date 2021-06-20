package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.appcompat.R;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;

class AppCompatSeekBarHelper extends AppCompatProgressBarHelper {
  private boolean mHasTickMarkTint = false;
  
  private boolean mHasTickMarkTintMode = false;
  
  private Drawable mTickMark;
  
  private ColorStateList mTickMarkTintList = null;
  
  private PorterDuff.Mode mTickMarkTintMode = null;
  
  private final SeekBar mView;
  
  AppCompatSeekBarHelper(SeekBar paramSeekBar) {
    super((ProgressBar)paramSeekBar);
    this.mView = paramSeekBar;
  }
  
  private void applyTickMarkTint() {
    if (this.mTickMark != null && (this.mHasTickMarkTint || this.mHasTickMarkTintMode)) {
      this.mTickMark = DrawableCompat.wrap(this.mTickMark.mutate());
      if (this.mHasTickMarkTint)
        DrawableCompat.setTintList(this.mTickMark, this.mTickMarkTintList); 
      if (this.mHasTickMarkTintMode)
        DrawableCompat.setTintMode(this.mTickMark, this.mTickMarkTintMode); 
      if (this.mTickMark.isStateful())
        this.mTickMark.setState(this.mView.getDrawableState()); 
    } 
  }
  
  void drawTickMarks(Canvas paramCanvas) {
    int i = 1;
    if (this.mTickMark != null) {
      int j = this.mView.getMax();
      if (j > 1) {
        int k = this.mTickMark.getIntrinsicWidth();
        int m = this.mTickMark.getIntrinsicHeight();
        if (k >= 0) {
          k /= 2;
        } else {
          k = 1;
        } 
        if (m >= 0)
          i = m / 2; 
        this.mTickMark.setBounds(-k, -i, k, i);
        float f = (this.mView.getWidth() - this.mView.getPaddingLeft() - this.mView.getPaddingRight()) / j;
        i = paramCanvas.save();
        paramCanvas.translate(this.mView.getPaddingLeft(), (this.mView.getHeight() / 2));
        for (k = 0; k <= j; k++) {
          this.mTickMark.draw(paramCanvas);
          paramCanvas.translate(f, 0.0F);
        } 
        paramCanvas.restoreToCount(i);
      } 
    } 
  }
  
  void drawableStateChanged() {
    Drawable drawable = this.mTickMark;
    if (drawable != null && drawable.isStateful() && drawable.setState(this.mView.getDrawableState()))
      this.mView.invalidateDrawable(drawable); 
  }
  
  @Nullable
  Drawable getTickMark() {
    return this.mTickMark;
  }
  
  @Nullable
  ColorStateList getTickMarkTintList() {
    return this.mTickMarkTintList;
  }
  
  @Nullable
  PorterDuff.Mode getTickMarkTintMode() {
    return this.mTickMarkTintMode;
  }
  
  void jumpDrawablesToCurrentState() {
    if (this.mTickMark != null)
      this.mTickMark.jumpToCurrentState(); 
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt) {
    super.loadFromAttributes(paramAttributeSet, paramInt);
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), paramAttributeSet, R.styleable.AppCompatSeekBar, paramInt, 0);
    Drawable drawable = tintTypedArray.getDrawableIfKnown(R.styleable.AppCompatSeekBar_android_thumb);
    if (drawable != null)
      this.mView.setThumb(drawable); 
    setTickMark(tintTypedArray.getDrawable(R.styleable.AppCompatSeekBar_tickMark));
    if (tintTypedArray.hasValue(R.styleable.AppCompatSeekBar_tickMarkTintMode)) {
      this.mTickMarkTintMode = DrawableUtils.parseTintMode(tintTypedArray.getInt(R.styleable.AppCompatSeekBar_tickMarkTintMode, -1), this.mTickMarkTintMode);
      this.mHasTickMarkTintMode = true;
    } 
    if (tintTypedArray.hasValue(R.styleable.AppCompatSeekBar_tickMarkTint)) {
      this.mTickMarkTintList = tintTypedArray.getColorStateList(R.styleable.AppCompatSeekBar_tickMarkTint);
      this.mHasTickMarkTint = true;
    } 
    tintTypedArray.recycle();
    applyTickMarkTint();
  }
  
  void setTickMark(@Nullable Drawable paramDrawable) {
    if (this.mTickMark != null)
      this.mTickMark.setCallback(null); 
    this.mTickMark = paramDrawable;
    if (paramDrawable != null) {
      paramDrawable.setCallback((Drawable.Callback)this.mView);
      DrawableCompat.setLayoutDirection(paramDrawable, ViewCompat.getLayoutDirection((View)this.mView));
      if (paramDrawable.isStateful())
        paramDrawable.setState(this.mView.getDrawableState()); 
      applyTickMarkTint();
    } 
    this.mView.invalidate();
  }
  
  void setTickMarkTintList(@Nullable ColorStateList paramColorStateList) {
    this.mTickMarkTintList = paramColorStateList;
    this.mHasTickMarkTint = true;
    applyTickMarkTint();
  }
  
  void setTickMarkTintMode(@Nullable PorterDuff.Mode paramMode) {
    this.mTickMarkTintMode = paramMode;
    this.mHasTickMarkTintMode = true;
    applyTickMarkTint();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/AppCompatSeekBarHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
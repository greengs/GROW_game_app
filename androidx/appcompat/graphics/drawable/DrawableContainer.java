package androidx.appcompat.graphics.drawable;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.DrawableCompat;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class DrawableContainer extends Drawable implements Drawable.Callback {
  private static final boolean DEBUG = false;
  
  private static final boolean DEFAULT_DITHER = true;
  
  private static final String TAG = "DrawableContainer";
  
  private int mAlpha = 255;
  
  private Runnable mAnimationRunnable;
  
  private BlockInvalidateCallback mBlockInvalidateCallback;
  
  private int mCurIndex = -1;
  
  private Drawable mCurrDrawable;
  
  private DrawableContainerState mDrawableContainerState;
  
  private long mEnterAnimationEnd;
  
  private long mExitAnimationEnd;
  
  private boolean mHasAlpha;
  
  private Rect mHotspotBounds;
  
  private Drawable mLastDrawable;
  
  private int mLastIndex = -1;
  
  private boolean mMutated;
  
  private void initializeDrawableForDisplay(Drawable paramDrawable) {
    if (this.mBlockInvalidateCallback == null)
      this.mBlockInvalidateCallback = new BlockInvalidateCallback(); 
    paramDrawable.setCallback(this.mBlockInvalidateCallback.wrap(paramDrawable.getCallback()));
    try {
      if (this.mDrawableContainerState.mEnterFadeDuration <= 0 && this.mHasAlpha)
        paramDrawable.setAlpha(this.mAlpha); 
      if (this.mDrawableContainerState.mHasColorFilter) {
        paramDrawable.setColorFilter(this.mDrawableContainerState.mColorFilter);
      } else {
        if (this.mDrawableContainerState.mHasTintList)
          DrawableCompat.setTintList(paramDrawable, this.mDrawableContainerState.mTintList); 
        if (this.mDrawableContainerState.mHasTintMode)
          DrawableCompat.setTintMode(paramDrawable, this.mDrawableContainerState.mTintMode); 
      } 
      paramDrawable.setVisible(isVisible(), true);
      paramDrawable.setDither(this.mDrawableContainerState.mDither);
      paramDrawable.setState(getState());
      paramDrawable.setLevel(getLevel());
      paramDrawable.setBounds(getBounds());
      if (Build.VERSION.SDK_INT >= 23)
        paramDrawable.setLayoutDirection(getLayoutDirection()); 
      if (Build.VERSION.SDK_INT >= 19)
        paramDrawable.setAutoMirrored(this.mDrawableContainerState.mAutoMirrored); 
      Rect rect = this.mHotspotBounds;
      if (Build.VERSION.SDK_INT >= 21 && rect != null)
        paramDrawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom); 
      return;
    } finally {
      paramDrawable.setCallback(this.mBlockInvalidateCallback.unwrap());
    } 
  }
  
  @SuppressLint({"WrongConstant"})
  @TargetApi(23)
  private boolean needsMirroring() {
    return (isAutoMirrored() && getLayoutDirection() == 1);
  }
  
  static int resolveDensity(@Nullable Resources paramResources, int paramInt) {
    if (paramResources != null)
      paramInt = (paramResources.getDisplayMetrics()).densityDpi; 
    int i = paramInt;
    if (paramInt == 0)
      i = 160; 
    return i;
  }
  
  void animate(boolean paramBoolean) {
    int i;
    this.mHasAlpha = true;
    long l = SystemClock.uptimeMillis();
    int j = 0;
    if (this.mCurrDrawable != null) {
      i = j;
      if (this.mEnterAnimationEnd != 0L)
        if (this.mEnterAnimationEnd <= l) {
          this.mCurrDrawable.setAlpha(this.mAlpha);
          this.mEnterAnimationEnd = 0L;
          i = j;
        } else {
          i = (int)((this.mEnterAnimationEnd - l) * 255L) / this.mDrawableContainerState.mEnterFadeDuration;
          this.mCurrDrawable.setAlpha((255 - i) * this.mAlpha / 255);
          i = 1;
        }  
    } else {
      this.mEnterAnimationEnd = 0L;
      i = j;
    } 
    if (this.mLastDrawable != null) {
      j = i;
      if (this.mExitAnimationEnd != 0L)
        if (this.mExitAnimationEnd <= l) {
          this.mLastDrawable.setVisible(false, false);
          this.mLastDrawable = null;
          this.mLastIndex = -1;
          this.mExitAnimationEnd = 0L;
          j = i;
        } else {
          i = (int)((this.mExitAnimationEnd - l) * 255L) / this.mDrawableContainerState.mExitFadeDuration;
          this.mLastDrawable.setAlpha(this.mAlpha * i / 255);
          j = 1;
        }  
    } else {
      this.mExitAnimationEnd = 0L;
      j = i;
    } 
    if (paramBoolean && j != 0)
      scheduleSelf(this.mAnimationRunnable, 16L + l); 
  }
  
  @RequiresApi(21)
  public void applyTheme(@NonNull Resources.Theme paramTheme) {
    this.mDrawableContainerState.applyTheme(paramTheme);
  }
  
  @RequiresApi(21)
  public boolean canApplyTheme() {
    return this.mDrawableContainerState.canApplyTheme();
  }
  
  void clearMutated() {
    this.mDrawableContainerState.clearMutated();
    this.mMutated = false;
  }
  
  DrawableContainerState cloneConstantState() {
    return this.mDrawableContainerState;
  }
  
  public void draw(@NonNull Canvas paramCanvas) {
    if (this.mCurrDrawable != null)
      this.mCurrDrawable.draw(paramCanvas); 
    if (this.mLastDrawable != null)
      this.mLastDrawable.draw(paramCanvas); 
  }
  
  public int getAlpha() {
    return this.mAlpha;
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mDrawableContainerState.getChangingConfigurations();
  }
  
  public final Drawable.ConstantState getConstantState() {
    if (this.mDrawableContainerState.canConstantState()) {
      this.mDrawableContainerState.mChangingConfigurations = getChangingConfigurations();
      return this.mDrawableContainerState;
    } 
    return null;
  }
  
  @NonNull
  public Drawable getCurrent() {
    return this.mCurrDrawable;
  }
  
  int getCurrentIndex() {
    return this.mCurIndex;
  }
  
  public void getHotspotBounds(@NonNull Rect paramRect) {
    if (this.mHotspotBounds != null) {
      paramRect.set(this.mHotspotBounds);
      return;
    } 
    super.getHotspotBounds(paramRect);
  }
  
  public int getIntrinsicHeight() {
    return this.mDrawableContainerState.isConstantSize() ? this.mDrawableContainerState.getConstantHeight() : ((this.mCurrDrawable != null) ? this.mCurrDrawable.getIntrinsicHeight() : -1);
  }
  
  public int getIntrinsicWidth() {
    return this.mDrawableContainerState.isConstantSize() ? this.mDrawableContainerState.getConstantWidth() : ((this.mCurrDrawable != null) ? this.mCurrDrawable.getIntrinsicWidth() : -1);
  }
  
  public int getMinimumHeight() {
    return this.mDrawableContainerState.isConstantSize() ? this.mDrawableContainerState.getConstantMinimumHeight() : ((this.mCurrDrawable != null) ? this.mCurrDrawable.getMinimumHeight() : 0);
  }
  
  public int getMinimumWidth() {
    return this.mDrawableContainerState.isConstantSize() ? this.mDrawableContainerState.getConstantMinimumWidth() : ((this.mCurrDrawable != null) ? this.mCurrDrawable.getMinimumWidth() : 0);
  }
  
  public int getOpacity() {
    return (this.mCurrDrawable == null || !this.mCurrDrawable.isVisible()) ? -2 : this.mDrawableContainerState.getOpacity();
  }
  
  @RequiresApi(21)
  public void getOutline(@NonNull Outline paramOutline) {
    if (this.mCurrDrawable != null)
      this.mCurrDrawable.getOutline(paramOutline); 
  }
  
  public boolean getPadding(@NonNull Rect paramRect) {
    boolean bool;
    Rect rect = this.mDrawableContainerState.getConstantPadding();
    if (rect != null) {
      paramRect.set(rect);
      if ((rect.left | rect.top | rect.bottom | rect.right) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
    } else if (this.mCurrDrawable != null) {
      bool = this.mCurrDrawable.getPadding(paramRect);
    } else {
      bool = super.getPadding(paramRect);
    } 
    if (needsMirroring()) {
      int i = paramRect.left;
      paramRect.left = paramRect.right;
      paramRect.right = i;
    } 
    return bool;
  }
  
  public void invalidateDrawable(@NonNull Drawable paramDrawable) {
    if (this.mDrawableContainerState != null)
      this.mDrawableContainerState.invalidateCache(); 
    if (paramDrawable == this.mCurrDrawable && getCallback() != null)
      getCallback().invalidateDrawable(this); 
  }
  
  public boolean isAutoMirrored() {
    return this.mDrawableContainerState.mAutoMirrored;
  }
  
  public boolean isStateful() {
    return this.mDrawableContainerState.isStateful();
  }
  
  public void jumpToCurrentState() {
    boolean bool = false;
    if (this.mLastDrawable != null) {
      this.mLastDrawable.jumpToCurrentState();
      this.mLastDrawable = null;
      this.mLastIndex = -1;
      bool = true;
    } 
    if (this.mCurrDrawable != null) {
      this.mCurrDrawable.jumpToCurrentState();
      if (this.mHasAlpha)
        this.mCurrDrawable.setAlpha(this.mAlpha); 
    } 
    if (this.mExitAnimationEnd != 0L) {
      this.mExitAnimationEnd = 0L;
      bool = true;
    } 
    if (this.mEnterAnimationEnd != 0L) {
      this.mEnterAnimationEnd = 0L;
      bool = true;
    } 
    if (bool)
      invalidateSelf(); 
  }
  
  @NonNull
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      DrawableContainerState drawableContainerState = cloneConstantState();
      drawableContainerState.mutate();
      setConstantState(drawableContainerState);
      this.mMutated = true;
    } 
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    if (this.mLastDrawable != null)
      this.mLastDrawable.setBounds(paramRect); 
    if (this.mCurrDrawable != null)
      this.mCurrDrawable.setBounds(paramRect); 
  }
  
  public boolean onLayoutDirectionChanged(int paramInt) {
    return this.mDrawableContainerState.setLayoutDirection(paramInt, getCurrentIndex());
  }
  
  protected boolean onLevelChange(int paramInt) {
    return (this.mLastDrawable != null) ? this.mLastDrawable.setLevel(paramInt) : ((this.mCurrDrawable != null) ? this.mCurrDrawable.setLevel(paramInt) : false);
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    return (this.mLastDrawable != null) ? this.mLastDrawable.setState(paramArrayOfint) : ((this.mCurrDrawable != null) ? this.mCurrDrawable.setState(paramArrayOfint) : false);
  }
  
  public void scheduleDrawable(@NonNull Drawable paramDrawable, @NonNull Runnable paramRunnable, long paramLong) {
    if (paramDrawable == this.mCurrDrawable && getCallback() != null)
      getCallback().scheduleDrawable(this, paramRunnable, paramLong); 
  }
  
  boolean selectDrawable(int paramInt) {
    if (paramInt == this.mCurIndex)
      return false; 
    long l = SystemClock.uptimeMillis();
    if (this.mDrawableContainerState.mExitFadeDuration > 0) {
      if (this.mLastDrawable != null)
        this.mLastDrawable.setVisible(false, false); 
      if (this.mCurrDrawable != null) {
        this.mLastDrawable = this.mCurrDrawable;
        this.mLastIndex = this.mCurIndex;
        this.mExitAnimationEnd = this.mDrawableContainerState.mExitFadeDuration + l;
      } else {
        this.mLastDrawable = null;
        this.mLastIndex = -1;
        this.mExitAnimationEnd = 0L;
      } 
    } else if (this.mCurrDrawable != null) {
      this.mCurrDrawable.setVisible(false, false);
    } 
    if (paramInt >= 0 && paramInt < this.mDrawableContainerState.mNumChildren) {
      Drawable drawable = this.mDrawableContainerState.getChild(paramInt);
      this.mCurrDrawable = drawable;
      this.mCurIndex = paramInt;
      if (drawable != null) {
        if (this.mDrawableContainerState.mEnterFadeDuration > 0)
          this.mEnterAnimationEnd = this.mDrawableContainerState.mEnterFadeDuration + l; 
        initializeDrawableForDisplay(drawable);
      } 
    } else {
      this.mCurrDrawable = null;
      this.mCurIndex = -1;
    } 
    if (this.mEnterAnimationEnd != 0L || this.mExitAnimationEnd != 0L) {
      if (this.mAnimationRunnable == null) {
        this.mAnimationRunnable = new Runnable() {
            public void run() {
              DrawableContainer.this.animate(true);
              DrawableContainer.this.invalidateSelf();
            }
          };
      } else {
        unscheduleSelf(this.mAnimationRunnable);
      } 
      animate(true);
    } 
    invalidateSelf();
    return true;
  }
  
  public void setAlpha(int paramInt) {
    if (!this.mHasAlpha || this.mAlpha != paramInt) {
      this.mHasAlpha = true;
      this.mAlpha = paramInt;
      if (this.mCurrDrawable != null) {
        if (this.mEnterAnimationEnd == 0L) {
          this.mCurrDrawable.setAlpha(paramInt);
          return;
        } 
      } else {
        return;
      } 
    } else {
      return;
    } 
    animate(false);
  }
  
  public void setAutoMirrored(boolean paramBoolean) {
    if (this.mDrawableContainerState.mAutoMirrored != paramBoolean) {
      this.mDrawableContainerState.mAutoMirrored = paramBoolean;
      if (this.mCurrDrawable != null)
        DrawableCompat.setAutoMirrored(this.mCurrDrawable, this.mDrawableContainerState.mAutoMirrored); 
    } 
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    this.mDrawableContainerState.mHasColorFilter = true;
    if (this.mDrawableContainerState.mColorFilter != paramColorFilter) {
      this.mDrawableContainerState.mColorFilter = paramColorFilter;
      if (this.mCurrDrawable != null)
        this.mCurrDrawable.setColorFilter(paramColorFilter); 
    } 
  }
  
  protected void setConstantState(DrawableContainerState paramDrawableContainerState) {
    this.mDrawableContainerState = paramDrawableContainerState;
    if (this.mCurIndex >= 0) {
      this.mCurrDrawable = paramDrawableContainerState.getChild(this.mCurIndex);
      if (this.mCurrDrawable != null)
        initializeDrawableForDisplay(this.mCurrDrawable); 
    } 
    this.mLastIndex = -1;
    this.mLastDrawable = null;
  }
  
  void setCurrentIndex(int paramInt) {
    selectDrawable(paramInt);
  }
  
  public void setDither(boolean paramBoolean) {
    if (this.mDrawableContainerState.mDither != paramBoolean) {
      this.mDrawableContainerState.mDither = paramBoolean;
      if (this.mCurrDrawable != null)
        this.mCurrDrawable.setDither(this.mDrawableContainerState.mDither); 
    } 
  }
  
  public void setEnterFadeDuration(int paramInt) {
    this.mDrawableContainerState.mEnterFadeDuration = paramInt;
  }
  
  public void setExitFadeDuration(int paramInt) {
    this.mDrawableContainerState.mExitFadeDuration = paramInt;
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2) {
    if (this.mCurrDrawable != null)
      DrawableCompat.setHotspot(this.mCurrDrawable, paramFloat1, paramFloat2); 
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.mHotspotBounds == null) {
      this.mHotspotBounds = new Rect(paramInt1, paramInt2, paramInt3, paramInt4);
    } else {
      this.mHotspotBounds.set(paramInt1, paramInt2, paramInt3, paramInt4);
    } 
    if (this.mCurrDrawable != null)
      DrawableCompat.setHotspotBounds(this.mCurrDrawable, paramInt1, paramInt2, paramInt3, paramInt4); 
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    this.mDrawableContainerState.mHasTintList = true;
    if (this.mDrawableContainerState.mTintList != paramColorStateList) {
      this.mDrawableContainerState.mTintList = paramColorStateList;
      DrawableCompat.setTintList(this.mCurrDrawable, paramColorStateList);
    } 
  }
  
  public void setTintMode(@NonNull PorterDuff.Mode paramMode) {
    this.mDrawableContainerState.mHasTintMode = true;
    if (this.mDrawableContainerState.mTintMode != paramMode) {
      this.mDrawableContainerState.mTintMode = paramMode;
      DrawableCompat.setTintMode(this.mCurrDrawable, paramMode);
    } 
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    if (this.mLastDrawable != null)
      this.mLastDrawable.setVisible(paramBoolean1, paramBoolean2); 
    if (this.mCurrDrawable != null)
      this.mCurrDrawable.setVisible(paramBoolean1, paramBoolean2); 
    return bool;
  }
  
  public void unscheduleDrawable(@NonNull Drawable paramDrawable, @NonNull Runnable paramRunnable) {
    if (paramDrawable == this.mCurrDrawable && getCallback() != null)
      getCallback().unscheduleDrawable(this, paramRunnable); 
  }
  
  final void updateDensity(Resources paramResources) {
    this.mDrawableContainerState.updateDensity(paramResources);
  }
  
  static class BlockInvalidateCallback implements Drawable.Callback {
    private Drawable.Callback mCallback;
    
    public void invalidateDrawable(@NonNull Drawable param1Drawable) {}
    
    public void scheduleDrawable(@NonNull Drawable param1Drawable, @NonNull Runnable param1Runnable, long param1Long) {
      if (this.mCallback != null)
        this.mCallback.scheduleDrawable(param1Drawable, param1Runnable, param1Long); 
    }
    
    public void unscheduleDrawable(@NonNull Drawable param1Drawable, @NonNull Runnable param1Runnable) {
      if (this.mCallback != null)
        this.mCallback.unscheduleDrawable(param1Drawable, param1Runnable); 
    }
    
    public Drawable.Callback unwrap() {
      Drawable.Callback callback = this.mCallback;
      this.mCallback = null;
      return callback;
    }
    
    public BlockInvalidateCallback wrap(Drawable.Callback param1Callback) {
      this.mCallback = param1Callback;
      return this;
    }
  }
  
  static abstract class DrawableContainerState extends Drawable.ConstantState {
    boolean mAutoMirrored;
    
    boolean mCanConstantState;
    
    int mChangingConfigurations;
    
    boolean mCheckedConstantSize;
    
    boolean mCheckedConstantState;
    
    boolean mCheckedOpacity;
    
    boolean mCheckedPadding;
    
    boolean mCheckedStateful;
    
    int mChildrenChangingConfigurations;
    
    ColorFilter mColorFilter;
    
    int mConstantHeight;
    
    int mConstantMinimumHeight;
    
    int mConstantMinimumWidth;
    
    Rect mConstantPadding;
    
    boolean mConstantSize;
    
    int mConstantWidth;
    
    int mDensity;
    
    boolean mDither;
    
    SparseArray<Drawable.ConstantState> mDrawableFutures;
    
    Drawable[] mDrawables;
    
    int mEnterFadeDuration;
    
    int mExitFadeDuration;
    
    boolean mHasColorFilter;
    
    boolean mHasTintList;
    
    boolean mHasTintMode;
    
    int mLayoutDirection;
    
    boolean mMutated;
    
    int mNumChildren;
    
    int mOpacity;
    
    final DrawableContainer mOwner;
    
    Resources mSourceRes;
    
    boolean mStateful;
    
    ColorStateList mTintList;
    
    PorterDuff.Mode mTintMode;
    
    boolean mVariablePadding;
    
    DrawableContainerState(DrawableContainerState param1DrawableContainerState, DrawableContainer param1DrawableContainer, Resources param1Resources) {
      int i;
      this.mDensity = 160;
      this.mVariablePadding = false;
      this.mConstantSize = false;
      this.mDither = true;
      this.mEnterFadeDuration = 0;
      this.mExitFadeDuration = 0;
      this.mOwner = param1DrawableContainer;
      if (param1Resources != null) {
        Resources resources = param1Resources;
      } else if (param1DrawableContainerState != null) {
        Resources resources = param1DrawableContainerState.mSourceRes;
      } else {
        param1DrawableContainer = null;
      } 
      this.mSourceRes = (Resources)param1DrawableContainer;
      if (param1DrawableContainerState != null) {
        i = param1DrawableContainerState.mDensity;
      } else {
        i = 0;
      } 
      this.mDensity = DrawableContainer.resolveDensity(param1Resources, i);
      if (param1DrawableContainerState != null) {
        this.mChangingConfigurations = param1DrawableContainerState.mChangingConfigurations;
        this.mChildrenChangingConfigurations = param1DrawableContainerState.mChildrenChangingConfigurations;
        this.mCheckedConstantState = true;
        this.mCanConstantState = true;
        this.mVariablePadding = param1DrawableContainerState.mVariablePadding;
        this.mConstantSize = param1DrawableContainerState.mConstantSize;
        this.mDither = param1DrawableContainerState.mDither;
        this.mMutated = param1DrawableContainerState.mMutated;
        this.mLayoutDirection = param1DrawableContainerState.mLayoutDirection;
        this.mEnterFadeDuration = param1DrawableContainerState.mEnterFadeDuration;
        this.mExitFadeDuration = param1DrawableContainerState.mExitFadeDuration;
        this.mAutoMirrored = param1DrawableContainerState.mAutoMirrored;
        this.mColorFilter = param1DrawableContainerState.mColorFilter;
        this.mHasColorFilter = param1DrawableContainerState.mHasColorFilter;
        this.mTintList = param1DrawableContainerState.mTintList;
        this.mTintMode = param1DrawableContainerState.mTintMode;
        this.mHasTintList = param1DrawableContainerState.mHasTintList;
        this.mHasTintMode = param1DrawableContainerState.mHasTintMode;
        if (param1DrawableContainerState.mDensity == this.mDensity) {
          if (param1DrawableContainerState.mCheckedPadding) {
            this.mConstantPadding = new Rect(param1DrawableContainerState.mConstantPadding);
            this.mCheckedPadding = true;
          } 
          if (param1DrawableContainerState.mCheckedConstantSize) {
            this.mConstantWidth = param1DrawableContainerState.mConstantWidth;
            this.mConstantHeight = param1DrawableContainerState.mConstantHeight;
            this.mConstantMinimumWidth = param1DrawableContainerState.mConstantMinimumWidth;
            this.mConstantMinimumHeight = param1DrawableContainerState.mConstantMinimumHeight;
            this.mCheckedConstantSize = true;
          } 
        } 
        if (param1DrawableContainerState.mCheckedOpacity) {
          this.mOpacity = param1DrawableContainerState.mOpacity;
          this.mCheckedOpacity = true;
        } 
        if (param1DrawableContainerState.mCheckedStateful) {
          this.mStateful = param1DrawableContainerState.mStateful;
          this.mCheckedStateful = true;
        } 
        Drawable[] arrayOfDrawable = param1DrawableContainerState.mDrawables;
        this.mDrawables = new Drawable[arrayOfDrawable.length];
        this.mNumChildren = param1DrawableContainerState.mNumChildren;
        SparseArray<Drawable.ConstantState> sparseArray = param1DrawableContainerState.mDrawableFutures;
        if (sparseArray != null) {
          this.mDrawableFutures = sparseArray.clone();
        } else {
          this.mDrawableFutures = new SparseArray(this.mNumChildren);
        } 
        int j = this.mNumChildren;
        for (i = 0; i < j; i++) {
          if (arrayOfDrawable[i] != null) {
            Drawable.ConstantState constantState = arrayOfDrawable[i].getConstantState();
            if (constantState != null) {
              this.mDrawableFutures.put(i, constantState);
            } else {
              this.mDrawables[i] = arrayOfDrawable[i];
            } 
          } 
        } 
      } else {
        this.mDrawables = new Drawable[10];
        this.mNumChildren = 0;
      } 
    }
    
    private void createAllFutures() {
      if (this.mDrawableFutures != null) {
        int j = this.mDrawableFutures.size();
        for (int i = 0; i < j; i++) {
          int k = this.mDrawableFutures.keyAt(i);
          Drawable.ConstantState constantState = (Drawable.ConstantState)this.mDrawableFutures.valueAt(i);
          this.mDrawables[k] = prepareDrawable(constantState.newDrawable(this.mSourceRes));
        } 
        this.mDrawableFutures = null;
      } 
    }
    
    private Drawable prepareDrawable(Drawable param1Drawable) {
      if (Build.VERSION.SDK_INT >= 23)
        param1Drawable.setLayoutDirection(this.mLayoutDirection); 
      param1Drawable = param1Drawable.mutate();
      param1Drawable.setCallback(this.mOwner);
      return param1Drawable;
    }
    
    public final int addChild(Drawable param1Drawable) {
      int i = this.mNumChildren;
      if (i >= this.mDrawables.length)
        growArray(i, i + 10); 
      param1Drawable.mutate();
      param1Drawable.setVisible(false, true);
      param1Drawable.setCallback(this.mOwner);
      this.mDrawables[i] = param1Drawable;
      this.mNumChildren++;
      this.mChildrenChangingConfigurations |= param1Drawable.getChangingConfigurations();
      invalidateCache();
      this.mConstantPadding = null;
      this.mCheckedPadding = false;
      this.mCheckedConstantSize = false;
      this.mCheckedConstantState = false;
      return i;
    }
    
    @RequiresApi(21)
    final void applyTheme(Resources.Theme param1Theme) {
      if (param1Theme != null) {
        createAllFutures();
        int j = this.mNumChildren;
        Drawable[] arrayOfDrawable = this.mDrawables;
        for (int i = 0; i < j; i++) {
          if (arrayOfDrawable[i] != null && arrayOfDrawable[i].canApplyTheme()) {
            arrayOfDrawable[i].applyTheme(param1Theme);
            this.mChildrenChangingConfigurations |= arrayOfDrawable[i].getChangingConfigurations();
          } 
        } 
        updateDensity(param1Theme.getResources());
      } 
    }
    
    @RequiresApi(21)
    public boolean canApplyTheme() {
      int j = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      for (int i = 0; i < j; i++) {
        Drawable drawable = arrayOfDrawable[i];
        if (drawable != null) {
          if (drawable.canApplyTheme())
            return true; 
        } else {
          Drawable.ConstantState constantState = (Drawable.ConstantState)this.mDrawableFutures.get(i);
          if (constantState != null && constantState.canApplyTheme())
            return true; 
        } 
      } 
      return false;
    }
    
    public boolean canConstantState() {
      // Byte code:
      //   0: iconst_0
      //   1: istore_3
      //   2: aload_0
      //   3: monitorenter
      //   4: aload_0
      //   5: getfield mCheckedConstantState : Z
      //   8: ifeq -> 20
      //   11: aload_0
      //   12: getfield mCanConstantState : Z
      //   15: istore_3
      //   16: aload_0
      //   17: monitorexit
      //   18: iload_3
      //   19: ireturn
      //   20: aload_0
      //   21: invokespecial createAllFutures : ()V
      //   24: aload_0
      //   25: iconst_1
      //   26: putfield mCheckedConstantState : Z
      //   29: aload_0
      //   30: getfield mNumChildren : I
      //   33: istore_2
      //   34: aload_0
      //   35: getfield mDrawables : [Landroid/graphics/drawable/Drawable;
      //   38: astore #4
      //   40: iconst_0
      //   41: istore_1
      //   42: iload_1
      //   43: iload_2
      //   44: if_icmpge -> 79
      //   47: aload #4
      //   49: iload_1
      //   50: aaload
      //   51: invokevirtual getConstantState : ()Landroid/graphics/drawable/Drawable$ConstantState;
      //   54: ifnonnull -> 72
      //   57: aload_0
      //   58: iconst_0
      //   59: putfield mCanConstantState : Z
      //   62: goto -> 16
      //   65: astore #4
      //   67: aload_0
      //   68: monitorexit
      //   69: aload #4
      //   71: athrow
      //   72: iload_1
      //   73: iconst_1
      //   74: iadd
      //   75: istore_1
      //   76: goto -> 42
      //   79: aload_0
      //   80: iconst_1
      //   81: putfield mCanConstantState : Z
      //   84: iconst_1
      //   85: istore_3
      //   86: goto -> 16
      // Exception table:
      //   from	to	target	type
      //   4	16	65	finally
      //   20	40	65	finally
      //   47	62	65	finally
      //   79	84	65	finally
    }
    
    final void clearMutated() {
      this.mMutated = false;
    }
    
    protected void computeConstantSize() {
      this.mCheckedConstantSize = true;
      createAllFutures();
      int j = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      this.mConstantHeight = -1;
      this.mConstantWidth = -1;
      this.mConstantMinimumHeight = 0;
      this.mConstantMinimumWidth = 0;
      for (int i = 0; i < j; i++) {
        Drawable drawable = arrayOfDrawable[i];
        int k = drawable.getIntrinsicWidth();
        if (k > this.mConstantWidth)
          this.mConstantWidth = k; 
        k = drawable.getIntrinsicHeight();
        if (k > this.mConstantHeight)
          this.mConstantHeight = k; 
        k = drawable.getMinimumWidth();
        if (k > this.mConstantMinimumWidth)
          this.mConstantMinimumWidth = k; 
        k = drawable.getMinimumHeight();
        if (k > this.mConstantMinimumHeight)
          this.mConstantMinimumHeight = k; 
      } 
    }
    
    final int getCapacity() {
      return this.mDrawables.length;
    }
    
    public int getChangingConfigurations() {
      return this.mChangingConfigurations | this.mChildrenChangingConfigurations;
    }
    
    public final Drawable getChild(int param1Int) {
      Drawable drawable = this.mDrawables[param1Int];
      if (drawable != null)
        return drawable; 
      if (this.mDrawableFutures != null) {
        int i = this.mDrawableFutures.indexOfKey(param1Int);
        if (i >= 0) {
          drawable = prepareDrawable(((Drawable.ConstantState)this.mDrawableFutures.valueAt(i)).newDrawable(this.mSourceRes));
          this.mDrawables[param1Int] = drawable;
          this.mDrawableFutures.removeAt(i);
          if (this.mDrawableFutures.size() == 0)
            this.mDrawableFutures = null; 
          return drawable;
        } 
      } 
      return null;
    }
    
    public final int getChildCount() {
      return this.mNumChildren;
    }
    
    public final int getConstantHeight() {
      if (!this.mCheckedConstantSize)
        computeConstantSize(); 
      return this.mConstantHeight;
    }
    
    public final int getConstantMinimumHeight() {
      if (!this.mCheckedConstantSize)
        computeConstantSize(); 
      return this.mConstantMinimumHeight;
    }
    
    public final int getConstantMinimumWidth() {
      if (!this.mCheckedConstantSize)
        computeConstantSize(); 
      return this.mConstantMinimumWidth;
    }
    
    public final Rect getConstantPadding() {
      if (this.mVariablePadding)
        return null; 
      if (this.mConstantPadding != null || this.mCheckedPadding)
        return this.mConstantPadding; 
      createAllFutures();
      Rect rect1 = null;
      Rect rect2 = new Rect();
      int j = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      int i = 0;
      while (i < j) {
        Rect rect = rect1;
        if (arrayOfDrawable[i].getPadding(rect2)) {
          Rect rect3 = rect1;
          if (rect1 == null)
            rect3 = new Rect(0, 0, 0, 0); 
          if (rect2.left > rect3.left)
            rect3.left = rect2.left; 
          if (rect2.top > rect3.top)
            rect3.top = rect2.top; 
          if (rect2.right > rect3.right)
            rect3.right = rect2.right; 
          rect = rect3;
          if (rect2.bottom > rect3.bottom) {
            rect3.bottom = rect2.bottom;
            rect = rect3;
          } 
        } 
        i++;
        rect1 = rect;
      } 
      this.mCheckedPadding = true;
      this.mConstantPadding = rect1;
      return rect1;
    }
    
    public final int getConstantWidth() {
      if (!this.mCheckedConstantSize)
        computeConstantSize(); 
      return this.mConstantWidth;
    }
    
    public final int getEnterFadeDuration() {
      return this.mEnterFadeDuration;
    }
    
    public final int getExitFadeDuration() {
      return this.mExitFadeDuration;
    }
    
    public final int getOpacity() {
      if (this.mCheckedOpacity)
        return this.mOpacity; 
      createAllFutures();
      int k = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      if (k > 0) {
        i = arrayOfDrawable[0].getOpacity();
      } else {
        i = -2;
      } 
      byte b = 1;
      int j = i;
      int i;
      for (i = b; i < k; i++)
        j = Drawable.resolveOpacity(j, arrayOfDrawable[i].getOpacity()); 
      this.mOpacity = j;
      this.mCheckedOpacity = true;
      return j;
    }
    
    public void growArray(int param1Int1, int param1Int2) {
      Drawable[] arrayOfDrawable = new Drawable[param1Int2];
      System.arraycopy(this.mDrawables, 0, arrayOfDrawable, 0, param1Int1);
      this.mDrawables = arrayOfDrawable;
    }
    
    void invalidateCache() {
      this.mCheckedOpacity = false;
      this.mCheckedStateful = false;
    }
    
    public final boolean isConstantSize() {
      return this.mConstantSize;
    }
    
    public final boolean isStateful() {
      if (this.mCheckedStateful)
        return this.mStateful; 
      createAllFutures();
      int j = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      boolean bool = false;
      for (int i = 0;; i++) {
        boolean bool1 = bool;
        if (i < j) {
          if (arrayOfDrawable[i].isStateful()) {
            bool1 = true;
            this.mStateful = bool1;
            this.mCheckedStateful = true;
            return bool1;
          } 
        } else {
          this.mStateful = bool1;
          this.mCheckedStateful = true;
          return bool1;
        } 
      } 
    }
    
    void mutate() {
      int j = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      for (int i = 0; i < j; i++) {
        if (arrayOfDrawable[i] != null)
          arrayOfDrawable[i].mutate(); 
      } 
      this.mMutated = true;
    }
    
    public final void setConstantSize(boolean param1Boolean) {
      this.mConstantSize = param1Boolean;
    }
    
    public final void setEnterFadeDuration(int param1Int) {
      this.mEnterFadeDuration = param1Int;
    }
    
    public final void setExitFadeDuration(int param1Int) {
      this.mExitFadeDuration = param1Int;
    }
    
    final boolean setLayoutDirection(int param1Int1, int param1Int2) {
      boolean bool = false;
      int j = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      int i = 0;
      while (i < j) {
        boolean bool1 = bool;
        if (arrayOfDrawable[i] != null) {
          boolean bool2 = false;
          if (Build.VERSION.SDK_INT >= 23)
            bool2 = arrayOfDrawable[i].setLayoutDirection(param1Int1); 
          bool1 = bool;
          if (i == param1Int2)
            bool1 = bool2; 
        } 
        i++;
        bool = bool1;
      } 
      this.mLayoutDirection = param1Int1;
      return bool;
    }
    
    public final void setVariablePadding(boolean param1Boolean) {
      this.mVariablePadding = param1Boolean;
    }
    
    final void updateDensity(Resources param1Resources) {
      if (param1Resources != null) {
        this.mSourceRes = param1Resources;
        int i = DrawableContainer.resolveDensity(param1Resources, this.mDensity);
        int j = this.mDensity;
        this.mDensity = i;
        if (j != i) {
          this.mCheckedConstantSize = false;
          this.mCheckedPadding = false;
        } 
      } 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/graphics/drawable/DrawableContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import androidx.core.util.DebugUtils;
import androidx.core.util.LogWriter;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelStore;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class FragmentManagerImpl extends FragmentManager implements LayoutInflater.Factory2 {
  static final Interpolator ACCELERATE_CUBIC;
  
  static final Interpolator ACCELERATE_QUINT;
  
  static final int ANIM_DUR = 220;
  
  public static final int ANIM_STYLE_CLOSE_ENTER = 3;
  
  public static final int ANIM_STYLE_CLOSE_EXIT = 4;
  
  public static final int ANIM_STYLE_FADE_ENTER = 5;
  
  public static final int ANIM_STYLE_FADE_EXIT = 6;
  
  public static final int ANIM_STYLE_OPEN_ENTER = 1;
  
  public static final int ANIM_STYLE_OPEN_EXIT = 2;
  
  static boolean DEBUG = false;
  
  static final Interpolator DECELERATE_CUBIC;
  
  static final Interpolator DECELERATE_QUINT;
  
  static final String TAG = "FragmentManager";
  
  static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
  
  static final String TARGET_STATE_TAG = "android:target_state";
  
  static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
  
  static final String VIEW_STATE_TAG = "android:view_state";
  
  static Field sAnimationListenerField = null;
  
  SparseArray<Fragment> mActive;
  
  final ArrayList<Fragment> mAdded = new ArrayList<Fragment>();
  
  ArrayList<Integer> mAvailBackStackIndices;
  
  ArrayList<BackStackRecord> mBackStack;
  
  ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
  
  ArrayList<BackStackRecord> mBackStackIndices;
  
  FragmentContainer mContainer;
  
  ArrayList<Fragment> mCreatedMenus;
  
  int mCurState = 0;
  
  boolean mDestroyed;
  
  Runnable mExecCommit = new Runnable() {
      public void run() {
        FragmentManagerImpl.this.execPendingActions();
      }
    };
  
  boolean mExecutingActions;
  
  boolean mHavePendingDeferredStart;
  
  FragmentHostCallback mHost;
  
  private final CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> mLifecycleCallbacks = new CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder>();
  
  boolean mNeedMenuInvalidate;
  
  int mNextFragmentIndex = 0;
  
  String mNoTransactionsBecause;
  
  Fragment mParent;
  
  ArrayList<OpGenerator> mPendingActions;
  
  ArrayList<StartEnterTransitionListener> mPostponedTransactions;
  
  @Nullable
  Fragment mPrimaryNav;
  
  FragmentManagerNonConfig mSavedNonConfig;
  
  SparseArray<Parcelable> mStateArray = null;
  
  Bundle mStateBundle = null;
  
  boolean mStateSaved;
  
  boolean mStopped;
  
  ArrayList<Fragment> mTmpAddedFragments;
  
  ArrayList<Boolean> mTmpIsPop;
  
  ArrayList<BackStackRecord> mTmpRecords;
  
  static {
    DECELERATE_QUINT = (Interpolator)new DecelerateInterpolator(2.5F);
    DECELERATE_CUBIC = (Interpolator)new DecelerateInterpolator(1.5F);
    ACCELERATE_QUINT = (Interpolator)new AccelerateInterpolator(2.5F);
    ACCELERATE_CUBIC = (Interpolator)new AccelerateInterpolator(1.5F);
  }
  
  private void addAddedFragments(ArraySet<Fragment> paramArraySet) {
    if (this.mCurState >= 1) {
      int j = Math.min(this.mCurState, 3);
      int k = this.mAdded.size();
      int i = 0;
      while (true) {
        if (i < k) {
          Fragment fragment = this.mAdded.get(i);
          if (fragment.mState < j) {
            moveToState(fragment, j, fragment.getNextAnim(), fragment.getNextTransition(), false);
            if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded)
              paramArraySet.add(fragment); 
          } 
          i++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  private void animateRemoveFragment(@NonNull final Fragment fragment, @NonNull AnimationOrAnimator paramAnimationOrAnimator, int paramInt) {
    final View viewToAnimate = fragment.mView;
    final ViewGroup container = fragment.mContainer;
    viewGroup.startViewTransition(view);
    fragment.setStateAfterAnimating(paramInt);
    if (paramAnimationOrAnimator.animation != null) {
      EndViewTransitionAnimator endViewTransitionAnimator = new EndViewTransitionAnimator(paramAnimationOrAnimator.animation, viewGroup, view);
      fragment.setAnimatingAway(fragment.mView);
      endViewTransitionAnimator.setAnimationListener(new AnimationListenerWrapper(getAnimationListener((Animation)endViewTransitionAnimator)) {
            public void onAnimationEnd(Animation param1Animation) {
              super.onAnimationEnd(param1Animation);
              container.post(new Runnable() {
                    public void run() {
                      if (fragment.getAnimatingAway() != null) {
                        fragment.setAnimatingAway(null);
                        FragmentManagerImpl.this.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                      } 
                    }
                  });
            }
          });
      setHWLayerAnimListenerIfAlpha(view, paramAnimationOrAnimator);
      fragment.mView.startAnimation((Animation)endViewTransitionAnimator);
      return;
    } 
    Animator animator = paramAnimationOrAnimator.animator;
    fragment.setAnimator(paramAnimationOrAnimator.animator);
    animator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
          public void onAnimationEnd(Animator param1Animator) {
            container.endViewTransition(viewToAnimate);
            param1Animator = fragment.getAnimator();
            fragment.setAnimator(null);
            if (param1Animator != null && container.indexOfChild(viewToAnimate) < 0)
              FragmentManagerImpl.this.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false); 
          }
        });
    animator.setTarget(fragment.mView);
    setHWLayerAnimListenerIfAlpha(fragment.mView, paramAnimationOrAnimator);
    animator.start();
  }
  
  private void burpActive() {
    if (this.mActive != null)
      for (int i = this.mActive.size() - 1; i >= 0; i--) {
        if (this.mActive.valueAt(i) == null)
          this.mActive.delete(this.mActive.keyAt(i)); 
      }  
  }
  
  private void checkStateLoss() {
    if (isStateSaved())
      throw new IllegalStateException("Can not perform this action after onSaveInstanceState"); 
    if (this.mNoTransactionsBecause != null)
      throw new IllegalStateException("Can not perform this action inside of " + this.mNoTransactionsBecause); 
  }
  
  private void cleanupExec() {
    this.mExecutingActions = false;
    this.mTmpIsPop.clear();
    this.mTmpRecords.clear();
  }
  
  private void dispatchStateChange(int paramInt) {
    try {
      this.mExecutingActions = true;
      moveToState(paramInt, false);
      this.mExecutingActions = false;
      return;
    } finally {
      this.mExecutingActions = false;
    } 
  }
  
  private void endAnimatingAwayFragments() {
    int i;
    if (this.mActive == null) {
      i = 0;
    } else {
      i = this.mActive.size();
    } 
    for (int j = 0; j < i; j++) {
      Fragment fragment = (Fragment)this.mActive.valueAt(j);
      if (fragment != null)
        if (fragment.getAnimatingAway() != null) {
          int k = fragment.getStateAfterAnimating();
          View view = fragment.getAnimatingAway();
          Animation animation = view.getAnimation();
          if (animation != null) {
            animation.cancel();
            view.clearAnimation();
          } 
          fragment.setAnimatingAway(null);
          moveToState(fragment, k, 0, 0, false);
        } else if (fragment.getAnimator() != null) {
          fragment.getAnimator().end();
        }  
    } 
  }
  
  private void ensureExecReady(boolean paramBoolean) {
    if (this.mExecutingActions)
      throw new IllegalStateException("FragmentManager is already executing transactions"); 
    if (this.mHost == null)
      throw new IllegalStateException("Fragment host has been destroyed"); 
    if (Looper.myLooper() != this.mHost.getHandler().getLooper())
      throw new IllegalStateException("Must be called from main thread of fragment host"); 
    if (!paramBoolean)
      checkStateLoss(); 
    if (this.mTmpRecords == null) {
      this.mTmpRecords = new ArrayList<BackStackRecord>();
      this.mTmpIsPop = new ArrayList<Boolean>();
    } 
    this.mExecutingActions = true;
    try {
      executePostponedTransaction(null, null);
      return;
    } finally {
      this.mExecutingActions = false;
    } 
  }
  
  private static void executeOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2) {
    while (paramInt1 < paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(paramInt1);
      if (((Boolean)paramArrayList1.get(paramInt1)).booleanValue()) {
        boolean bool;
        backStackRecord.bumpBackStackNesting(-1);
        if (paramInt1 == paramInt2 - 1) {
          bool = true;
        } else {
          bool = false;
        } 
        backStackRecord.executePopOps(bool);
      } else {
        backStackRecord.bumpBackStackNesting(1);
        backStackRecord.executeOps();
      } 
      paramInt1++;
    } 
  }
  
  private void executeOpsTogether(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2) {
    boolean bool1 = ((BackStackRecord)paramArrayList.get(paramInt1)).mReorderingAllowed;
    boolean bool = false;
    if (this.mTmpAddedFragments == null) {
      this.mTmpAddedFragments = new ArrayList<Fragment>();
    } else {
      this.mTmpAddedFragments.clear();
    } 
    this.mTmpAddedFragments.addAll(this.mAdded);
    Fragment fragment = getPrimaryNavigationFragment();
    int i;
    for (i = paramInt1; i < paramInt2; i++) {
      BackStackRecord backStackRecord = paramArrayList.get(i);
      if (!((Boolean)paramArrayList1.get(i)).booleanValue()) {
        fragment = backStackRecord.expandOps(this.mTmpAddedFragments, fragment);
      } else {
        fragment = backStackRecord.trackAddedFragmentsInPop(this.mTmpAddedFragments, fragment);
      } 
      if (bool || backStackRecord.mAddToBackStack) {
        bool = true;
      } else {
        bool = false;
      } 
    } 
    this.mTmpAddedFragments.clear();
    if (!bool1)
      FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, paramInt1, paramInt2, false); 
    executeOps(paramArrayList, paramArrayList1, paramInt1, paramInt2);
    i = paramInt2;
    if (bool1) {
      ArraySet<Fragment> arraySet = new ArraySet();
      addAddedFragments(arraySet);
      i = postponePostponableTransactions(paramArrayList, paramArrayList1, paramInt1, paramInt2, arraySet);
      makeRemovedFragmentsInvisible(arraySet);
    } 
    if (i != paramInt1 && bool1) {
      FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, paramInt1, i, true);
      moveToState(this.mCurState, true);
    } 
    while (paramInt1 < paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(paramInt1);
      if (((Boolean)paramArrayList1.get(paramInt1)).booleanValue() && backStackRecord.mIndex >= 0) {
        freeBackStackIndex(backStackRecord.mIndex);
        backStackRecord.mIndex = -1;
      } 
      backStackRecord.runOnCommitRunnables();
      paramInt1++;
    } 
    if (bool)
      reportBackStackChanged(); 
  }
  
  private void executePostponedTransaction(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   4: ifnonnull -> 105
    //   7: iconst_0
    //   8: istore_3
    //   9: iconst_0
    //   10: istore #5
    //   12: iload_3
    //   13: istore #4
    //   15: iload #5
    //   17: istore_3
    //   18: iload_3
    //   19: iload #4
    //   21: if_icmpge -> 236
    //   24: aload_0
    //   25: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   28: iload_3
    //   29: invokevirtual get : (I)Ljava/lang/Object;
    //   32: checkcast androidx/fragment/app/FragmentManagerImpl$StartEnterTransitionListener
    //   35: astore #7
    //   37: aload_1
    //   38: ifnull -> 116
    //   41: aload #7
    //   43: getfield mIsBack : Z
    //   46: ifne -> 116
    //   49: aload_1
    //   50: aload #7
    //   52: getfield mRecord : Landroidx/fragment/app/BackStackRecord;
    //   55: invokevirtual indexOf : (Ljava/lang/Object;)I
    //   58: istore #5
    //   60: iload #5
    //   62: iconst_m1
    //   63: if_icmpeq -> 116
    //   66: aload_2
    //   67: iload #5
    //   69: invokevirtual get : (I)Ljava/lang/Object;
    //   72: checkcast java/lang/Boolean
    //   75: invokevirtual booleanValue : ()Z
    //   78: ifeq -> 116
    //   81: aload #7
    //   83: invokevirtual cancelTransaction : ()V
    //   86: iload #4
    //   88: istore #6
    //   90: iload_3
    //   91: istore #5
    //   93: iload #5
    //   95: iconst_1
    //   96: iadd
    //   97: istore_3
    //   98: iload #6
    //   100: istore #4
    //   102: goto -> 18
    //   105: aload_0
    //   106: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   109: invokevirtual size : ()I
    //   112: istore_3
    //   113: goto -> 9
    //   116: aload #7
    //   118: invokevirtual isReady : ()Z
    //   121: ifne -> 159
    //   124: iload_3
    //   125: istore #5
    //   127: iload #4
    //   129: istore #6
    //   131: aload_1
    //   132: ifnull -> 93
    //   135: iload_3
    //   136: istore #5
    //   138: iload #4
    //   140: istore #6
    //   142: aload #7
    //   144: getfield mRecord : Landroidx/fragment/app/BackStackRecord;
    //   147: aload_1
    //   148: iconst_0
    //   149: aload_1
    //   150: invokevirtual size : ()I
    //   153: invokevirtual interactsWith : (Ljava/util/ArrayList;II)Z
    //   156: ifeq -> 93
    //   159: aload_0
    //   160: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   163: iload_3
    //   164: invokevirtual remove : (I)Ljava/lang/Object;
    //   167: pop
    //   168: iload_3
    //   169: iconst_1
    //   170: isub
    //   171: istore #5
    //   173: iload #4
    //   175: iconst_1
    //   176: isub
    //   177: istore #6
    //   179: aload_1
    //   180: ifnull -> 228
    //   183: aload #7
    //   185: getfield mIsBack : Z
    //   188: ifne -> 228
    //   191: aload_1
    //   192: aload #7
    //   194: getfield mRecord : Landroidx/fragment/app/BackStackRecord;
    //   197: invokevirtual indexOf : (Ljava/lang/Object;)I
    //   200: istore_3
    //   201: iload_3
    //   202: iconst_m1
    //   203: if_icmpeq -> 228
    //   206: aload_2
    //   207: iload_3
    //   208: invokevirtual get : (I)Ljava/lang/Object;
    //   211: checkcast java/lang/Boolean
    //   214: invokevirtual booleanValue : ()Z
    //   217: ifeq -> 228
    //   220: aload #7
    //   222: invokevirtual cancelTransaction : ()V
    //   225: goto -> 93
    //   228: aload #7
    //   230: invokevirtual completeTransaction : ()V
    //   233: goto -> 93
    //   236: return
  }
  
  private Fragment findFragmentUnder(Fragment paramFragment) {
    ViewGroup viewGroup = paramFragment.mContainer;
    View view = paramFragment.mView;
    if (viewGroup == null || view == null)
      return null; 
    for (int i = this.mAdded.indexOf(paramFragment) - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment.mContainer == viewGroup) {
        paramFragment = fragment;
        if (fragment.mView == null)
          continue; 
        return paramFragment;
      } 
      continue;
    } 
    return null;
  }
  
  private void forcePostponedTransactions() {
    if (this.mPostponedTransactions != null)
      while (!this.mPostponedTransactions.isEmpty())
        ((StartEnterTransitionListener)this.mPostponedTransactions.remove(0)).completeTransaction();  
  }
  
  private boolean generateOpsForPendingActions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #5
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: getfield mPendingActions : Ljava/util/ArrayList;
    //   9: ifnull -> 22
    //   12: aload_0
    //   13: getfield mPendingActions : Ljava/util/ArrayList;
    //   16: invokevirtual size : ()I
    //   19: ifne -> 26
    //   22: aload_0
    //   23: monitorexit
    //   24: iconst_0
    //   25: ireturn
    //   26: aload_0
    //   27: getfield mPendingActions : Ljava/util/ArrayList;
    //   30: invokevirtual size : ()I
    //   33: istore #4
    //   35: iconst_0
    //   36: istore_3
    //   37: iload_3
    //   38: iload #4
    //   40: if_icmpge -> 73
    //   43: iload #5
    //   45: aload_0
    //   46: getfield mPendingActions : Ljava/util/ArrayList;
    //   49: iload_3
    //   50: invokevirtual get : (I)Ljava/lang/Object;
    //   53: checkcast androidx/fragment/app/FragmentManagerImpl$OpGenerator
    //   56: aload_1
    //   57: aload_2
    //   58: invokeinterface generateOps : (Ljava/util/ArrayList;Ljava/util/ArrayList;)Z
    //   63: ior
    //   64: istore #5
    //   66: iload_3
    //   67: iconst_1
    //   68: iadd
    //   69: istore_3
    //   70: goto -> 37
    //   73: aload_0
    //   74: getfield mPendingActions : Ljava/util/ArrayList;
    //   77: invokevirtual clear : ()V
    //   80: aload_0
    //   81: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   84: invokevirtual getHandler : ()Landroid/os/Handler;
    //   87: aload_0
    //   88: getfield mExecCommit : Ljava/lang/Runnable;
    //   91: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
    //   94: aload_0
    //   95: monitorexit
    //   96: iload #5
    //   98: ireturn
    //   99: astore_1
    //   100: aload_0
    //   101: monitorexit
    //   102: aload_1
    //   103: athrow
    // Exception table:
    //   from	to	target	type
    //   5	22	99	finally
    //   22	24	99	finally
    //   26	35	99	finally
    //   43	66	99	finally
    //   73	96	99	finally
    //   100	102	99	finally
  }
  
  private static Animation.AnimationListener getAnimationListener(Animation paramAnimation) {
    try {
      if (sAnimationListenerField == null) {
        sAnimationListenerField = Animation.class.getDeclaredField("mListener");
        sAnimationListenerField.setAccessible(true);
      } 
      return (Animation.AnimationListener)sAnimationListenerField.get(paramAnimation);
    } catch (NoSuchFieldException noSuchFieldException) {
      Log.e("FragmentManager", "No field with the name mListener is found in Animation class", noSuchFieldException);
      return null;
    } catch (IllegalAccessException illegalAccessException) {
      Log.e("FragmentManager", "Cannot access Animation's mListener field", illegalAccessException);
      return null;
    } 
  }
  
  static AnimationOrAnimator makeFadeAnimation(Context paramContext, float paramFloat1, float paramFloat2) {
    AlphaAnimation alphaAnimation = new AlphaAnimation(paramFloat1, paramFloat2);
    alphaAnimation.setInterpolator(DECELERATE_CUBIC);
    alphaAnimation.setDuration(220L);
    return new AnimationOrAnimator((Animation)alphaAnimation);
  }
  
  static AnimationOrAnimator makeOpenCloseAnimation(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    AnimationSet animationSet = new AnimationSet(false);
    ScaleAnimation scaleAnimation = new ScaleAnimation(paramFloat1, paramFloat2, paramFloat1, paramFloat2, 1, 0.5F, 1, 0.5F);
    scaleAnimation.setInterpolator(DECELERATE_QUINT);
    scaleAnimation.setDuration(220L);
    animationSet.addAnimation((Animation)scaleAnimation);
    AlphaAnimation alphaAnimation = new AlphaAnimation(paramFloat3, paramFloat4);
    alphaAnimation.setInterpolator(DECELERATE_CUBIC);
    alphaAnimation.setDuration(220L);
    animationSet.addAnimation((Animation)alphaAnimation);
    return new AnimationOrAnimator((Animation)animationSet);
  }
  
  private void makeRemovedFragmentsInvisible(ArraySet<Fragment> paramArraySet) {
    int j = paramArraySet.size();
    for (int i = 0; i < j; i++) {
      Fragment fragment = (Fragment)paramArraySet.valueAt(i);
      if (!fragment.mAdded) {
        View view = fragment.getView();
        fragment.mPostponedAlpha = view.getAlpha();
        view.setAlpha(0.0F);
      } 
    } 
  }
  
  static boolean modifiesAlpha(Animator paramAnimator) {
    PropertyValuesHolder[] arrayOfPropertyValuesHolder;
    if (paramAnimator == null)
      return false; 
    if (paramAnimator instanceof ValueAnimator) {
      arrayOfPropertyValuesHolder = ((ValueAnimator)paramAnimator).getValues();
      for (int i = 0; i < arrayOfPropertyValuesHolder.length; i++) {
        if ("alpha".equals(arrayOfPropertyValuesHolder[i].getPropertyName()))
          return true; 
      } 
    } else if (arrayOfPropertyValuesHolder instanceof AnimatorSet) {
      ArrayList<Animator> arrayList = ((AnimatorSet)arrayOfPropertyValuesHolder).getChildAnimations();
      for (int i = 0; i < arrayList.size(); i++) {
        if (modifiesAlpha(arrayList.get(i)))
          return true; 
      } 
    } 
    return false;
  }
  
  static boolean modifiesAlpha(AnimationOrAnimator paramAnimationOrAnimator) {
    List list;
    if (paramAnimationOrAnimator.animation instanceof AlphaAnimation)
      return true; 
    if (paramAnimationOrAnimator.animation instanceof AnimationSet) {
      list = ((AnimationSet)paramAnimationOrAnimator.animation).getAnimations();
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i) instanceof AlphaAnimation)
          return true; 
      } 
      return false;
    } 
    return modifiesAlpha(((AnimationOrAnimator)list).animator);
  }
  
  private boolean popBackStackImmediate(String paramString, int paramInt1, int paramInt2) {
    execPendingActions();
    ensureExecReady(true);
    if (this.mPrimaryNav != null && paramInt1 < 0 && paramString == null) {
      FragmentManager fragmentManager = this.mPrimaryNav.peekChildFragmentManager();
      if (fragmentManager != null && fragmentManager.popBackStackImmediate())
        return true; 
    } 
    boolean bool = popBackStackState(this.mTmpRecords, this.mTmpIsPop, paramString, paramInt1, paramInt2);
    if (bool) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
        cleanupExec();
        doPendingDeferredStart();
        return bool;
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
    return bool;
  }
  
  private int postponePostponableTransactions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, ArraySet<Fragment> paramArraySet) {
    int j = paramInt2;
    int i = paramInt2 - 1;
    while (i >= paramInt1) {
      boolean bool;
      BackStackRecord backStackRecord = paramArrayList.get(i);
      boolean bool1 = ((Boolean)paramArrayList1.get(i)).booleanValue();
      if (backStackRecord.isPostponed() && !backStackRecord.interactsWith(paramArrayList, i + 1, paramInt2)) {
        bool = true;
      } else {
        bool = false;
      } 
      int k = j;
      if (bool) {
        if (this.mPostponedTransactions == null)
          this.mPostponedTransactions = new ArrayList<StartEnterTransitionListener>(); 
        StartEnterTransitionListener startEnterTransitionListener = new StartEnterTransitionListener(backStackRecord, bool1);
        this.mPostponedTransactions.add(startEnterTransitionListener);
        backStackRecord.setOnStartPostponedListener(startEnterTransitionListener);
        if (bool1) {
          backStackRecord.executeOps();
        } else {
          backStackRecord.executePopOps(false);
        } 
        k = j - 1;
        if (i != k) {
          paramArrayList.remove(i);
          paramArrayList.add(k, backStackRecord);
        } 
        addAddedFragments(paramArraySet);
      } 
      i--;
      j = k;
    } 
    return j;
  }
  
  private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    if (paramArrayList != null && !paramArrayList.isEmpty()) {
      if (paramArrayList1 == null || paramArrayList.size() != paramArrayList1.size())
        throw new IllegalStateException("Internal error with the back stack records"); 
      executePostponedTransaction(paramArrayList, paramArrayList1);
      int k = paramArrayList.size();
      int j = 0;
      int i = 0;
      while (i < k) {
        int n = i;
        int m = j;
        if (!((BackStackRecord)paramArrayList.get(i)).mReorderingAllowed) {
          if (j != i)
            executeOpsTogether(paramArrayList, paramArrayList1, j, i); 
          j = i + 1;
          m = j;
          if (((Boolean)paramArrayList1.get(i)).booleanValue())
            while (true) {
              m = j;
              if (j < k) {
                m = j;
                if (((Boolean)paramArrayList1.get(j)).booleanValue()) {
                  m = j;
                  if (!((BackStackRecord)paramArrayList.get(j)).mReorderingAllowed) {
                    j++;
                    continue;
                  } 
                } 
              } 
              break;
            }  
          executeOpsTogether(paramArrayList, paramArrayList1, i, m);
          i = m;
          n = m - 1;
          m = i;
        } 
        i = n + 1;
        j = m;
      } 
      if (j != k) {
        executeOpsTogether(paramArrayList, paramArrayList1, j, k);
        return;
      } 
    } 
  }
  
  public static int reverseTransit(int paramInt) {
    switch (paramInt) {
      default:
        return 0;
      case 4097:
        return 8194;
      case 8194:
        return 4097;
      case 4099:
        break;
    } 
    return 4099;
  }
  
  private static void setHWLayerAnimListenerIfAlpha(View paramView, AnimationOrAnimator paramAnimationOrAnimator) {
    if (paramView != null && paramAnimationOrAnimator != null && shouldRunOnHWLayer(paramView, paramAnimationOrAnimator)) {
      if (paramAnimationOrAnimator.animator != null) {
        paramAnimationOrAnimator.animator.addListener((Animator.AnimatorListener)new AnimatorOnHWLayerIfNeededListener(paramView));
        return;
      } 
      Animation.AnimationListener animationListener = getAnimationListener(paramAnimationOrAnimator.animation);
      paramView.setLayerType(2, null);
      paramAnimationOrAnimator.animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(paramView, animationListener));
      return;
    } 
  }
  
  private static void setRetaining(FragmentManagerNonConfig paramFragmentManagerNonConfig) {
    if (paramFragmentManagerNonConfig != null) {
      List<Fragment> list1 = paramFragmentManagerNonConfig.getFragments();
      if (list1 != null) {
        Iterator<Fragment> iterator = list1.iterator();
        while (iterator.hasNext())
          ((Fragment)iterator.next()).mRetaining = true; 
      } 
      List<FragmentManagerNonConfig> list = paramFragmentManagerNonConfig.getChildNonConfigs();
      if (list != null) {
        Iterator<FragmentManagerNonConfig> iterator = list.iterator();
        while (true) {
          if (iterator.hasNext()) {
            setRetaining(iterator.next());
            continue;
          } 
          return;
        } 
      } 
    } 
  }
  
  static boolean shouldRunOnHWLayer(View paramView, AnimationOrAnimator paramAnimationOrAnimator) {
    return (paramView != null && paramAnimationOrAnimator != null && Build.VERSION.SDK_INT >= 19 && paramView.getLayerType() == 0 && ViewCompat.hasOverlappingRendering(paramView) && modifiesAlpha(paramAnimationOrAnimator));
  }
  
  private void throwException(RuntimeException paramRuntimeException) {
    Log.e("FragmentManager", paramRuntimeException.getMessage());
    Log.e("FragmentManager", "Activity state:");
    PrintWriter printWriter = new PrintWriter((Writer)new LogWriter("FragmentManager"));
    if (this.mHost != null) {
      try {
        this.mHost.onDump("  ", null, printWriter, new String[0]);
      } catch (Exception exception) {
        Log.e("FragmentManager", "Failed dumping state", exception);
      } 
      throw paramRuntimeException;
    } 
    try {
      dump("  ", null, (PrintWriter)exception, new String[0]);
    } catch (Exception exception1) {
      Log.e("FragmentManager", "Failed dumping state", exception1);
    } 
    throw paramRuntimeException;
  }
  
  public static int transitToStyleIndex(int paramInt, boolean paramBoolean) {
    switch (paramInt) {
      default:
        return -1;
      case 4097:
        return paramBoolean ? 1 : 2;
      case 8194:
        return paramBoolean ? 3 : 4;
      case 4099:
        break;
    } 
    return paramBoolean ? 5 : 6;
  }
  
  void addBackStackState(BackStackRecord paramBackStackRecord) {
    if (this.mBackStack == null)
      this.mBackStack = new ArrayList<BackStackRecord>(); 
    this.mBackStack.add(paramBackStackRecord);
  }
  
  public void addFragment(Fragment paramFragment, boolean paramBoolean) {
    if (DEBUG)
      Log.v("FragmentManager", "add: " + paramFragment); 
    makeActive(paramFragment);
    if (!paramFragment.mDetached) {
      if (this.mAdded.contains(paramFragment))
        throw new IllegalStateException("Fragment already added: " + paramFragment); 
      synchronized (this.mAdded) {
        this.mAdded.add(paramFragment);
        paramFragment.mAdded = true;
        paramFragment.mRemoving = false;
        if (paramFragment.mView == null)
          paramFragment.mHiddenChanged = false; 
        if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
          this.mNeedMenuInvalidate = true; 
        if (paramBoolean)
          moveToState(paramFragment); 
        return;
      } 
    } 
  }
  
  public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener) {
    if (this.mBackStackChangeListeners == null)
      this.mBackStackChangeListeners = new ArrayList<FragmentManager.OnBackStackChangedListener>(); 
    this.mBackStackChangeListeners.add(paramOnBackStackChangedListener);
  }
  
  public int allocBackStackIndex(BackStackRecord paramBackStackRecord) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   6: ifnull -> 19
    //   9: aload_0
    //   10: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   13: invokevirtual size : ()I
    //   16: ifgt -> 100
    //   19: aload_0
    //   20: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   23: ifnonnull -> 37
    //   26: aload_0
    //   27: new java/util/ArrayList
    //   30: dup
    //   31: invokespecial <init> : ()V
    //   34: putfield mBackStackIndices : Ljava/util/ArrayList;
    //   37: aload_0
    //   38: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   41: invokevirtual size : ()I
    //   44: istore_2
    //   45: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   48: ifeq -> 87
    //   51: ldc 'FragmentManager'
    //   53: new java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial <init> : ()V
    //   60: ldc_w 'Setting back stack index '
    //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: iload_2
    //   67: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   70: ldc_w ' to '
    //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: aload_1
    //   77: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   80: invokevirtual toString : ()Ljava/lang/String;
    //   83: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   86: pop
    //   87: aload_0
    //   88: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   91: aload_1
    //   92: invokevirtual add : (Ljava/lang/Object;)Z
    //   95: pop
    //   96: aload_0
    //   97: monitorexit
    //   98: iload_2
    //   99: ireturn
    //   100: aload_0
    //   101: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   104: aload_0
    //   105: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   108: invokevirtual size : ()I
    //   111: iconst_1
    //   112: isub
    //   113: invokevirtual remove : (I)Ljava/lang/Object;
    //   116: checkcast java/lang/Integer
    //   119: invokevirtual intValue : ()I
    //   122: istore_2
    //   123: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   126: ifeq -> 165
    //   129: ldc 'FragmentManager'
    //   131: new java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial <init> : ()V
    //   138: ldc_w 'Adding back stack index '
    //   141: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: iload_2
    //   145: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   148: ldc_w ' with '
    //   151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: aload_1
    //   155: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   158: invokevirtual toString : ()Ljava/lang/String;
    //   161: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   164: pop
    //   165: aload_0
    //   166: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   169: iload_2
    //   170: aload_1
    //   171: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   174: pop
    //   175: aload_0
    //   176: monitorexit
    //   177: iload_2
    //   178: ireturn
    //   179: astore_1
    //   180: aload_0
    //   181: monitorexit
    //   182: aload_1
    //   183: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	179	finally
    //   19	37	179	finally
    //   37	87	179	finally
    //   87	98	179	finally
    //   100	165	179	finally
    //   165	177	179	finally
    //   180	182	179	finally
  }
  
  public void attachController(FragmentHostCallback paramFragmentHostCallback, FragmentContainer paramFragmentContainer, Fragment paramFragment) {
    if (this.mHost != null)
      throw new IllegalStateException("Already attached"); 
    this.mHost = paramFragmentHostCallback;
    this.mContainer = paramFragmentContainer;
    this.mParent = paramFragment;
  }
  
  public void attachFragment(Fragment paramFragment) {
    if (DEBUG)
      Log.v("FragmentManager", "attach: " + paramFragment); 
    if (paramFragment.mDetached) {
      paramFragment.mDetached = false;
      if (!paramFragment.mAdded) {
        if (this.mAdded.contains(paramFragment))
          throw new IllegalStateException("Fragment already added: " + paramFragment); 
        if (DEBUG)
          Log.v("FragmentManager", "add from attach: " + paramFragment); 
        synchronized (this.mAdded) {
          this.mAdded.add(paramFragment);
          paramFragment.mAdded = true;
          if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
            this.mNeedMenuInvalidate = true; 
          return;
        } 
      } 
    } 
  }
  
  public FragmentTransaction beginTransaction() {
    return new BackStackRecord(this);
  }
  
  void completeExecute(BackStackRecord paramBackStackRecord, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    if (paramBoolean1) {
      paramBackStackRecord.executePopOps(paramBoolean3);
    } else {
      paramBackStackRecord.executeOps();
    } 
    ArrayList<BackStackRecord> arrayList = new ArrayList(1);
    ArrayList<Boolean> arrayList1 = new ArrayList(1);
    arrayList.add(paramBackStackRecord);
    arrayList1.add(Boolean.valueOf(paramBoolean1));
    if (paramBoolean2)
      FragmentTransition.startTransitions(this, arrayList, arrayList1, 0, 1, true); 
    if (paramBoolean3)
      moveToState(this.mCurState, true); 
    if (this.mActive != null) {
      int j = this.mActive.size();
      for (int i = 0; i < j; i++) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && paramBackStackRecord.interactsWith(fragment.mContainerId)) {
          if (fragment.mPostponedAlpha > 0.0F)
            fragment.mView.setAlpha(fragment.mPostponedAlpha); 
          if (paramBoolean3) {
            fragment.mPostponedAlpha = 0.0F;
          } else {
            fragment.mPostponedAlpha = -1.0F;
            fragment.mIsNewlyAdded = false;
          } 
        } 
      } 
    } 
  }
  
  void completeShowHideFragment(final Fragment fragment) {
    if (fragment.mView != null) {
      boolean bool;
      int i = fragment.getNextTransition();
      if (!fragment.mHidden) {
        bool = true;
      } else {
        bool = false;
      } 
      AnimationOrAnimator animationOrAnimator = loadAnimation(fragment, i, bool, fragment.getNextTransitionStyle());
      if (animationOrAnimator != null && animationOrAnimator.animator != null) {
        animationOrAnimator.animator.setTarget(fragment.mView);
        if (fragment.mHidden) {
          if (fragment.isHideReplaced()) {
            fragment.setHideReplaced(false);
          } else {
            final ViewGroup container = fragment.mContainer;
            final View animatingView = fragment.mView;
            viewGroup.startViewTransition(view);
            animationOrAnimator.animator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
                  public void onAnimationEnd(Animator param1Animator) {
                    container.endViewTransition(animatingView);
                    param1Animator.removeListener((Animator.AnimatorListener)this);
                    if (fragment.mView != null)
                      fragment.mView.setVisibility(8); 
                  }
                });
          } 
        } else {
          fragment.mView.setVisibility(0);
        } 
        setHWLayerAnimListenerIfAlpha(fragment.mView, animationOrAnimator);
        animationOrAnimator.animator.start();
      } else {
        if (animationOrAnimator != null) {
          setHWLayerAnimListenerIfAlpha(fragment.mView, animationOrAnimator);
          fragment.mView.startAnimation(animationOrAnimator.animation);
          animationOrAnimator.animation.start();
        } 
        if (fragment.mHidden && !fragment.isHideReplaced()) {
          i = 8;
        } else {
          i = 0;
        } 
        fragment.mView.setVisibility(i);
        if (fragment.isHideReplaced())
          fragment.setHideReplaced(false); 
      } 
    } 
    if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible)
      this.mNeedMenuInvalidate = true; 
    fragment.mHiddenChanged = false;
    fragment.onHiddenChanged(fragment.mHidden);
  }
  
  public void detachFragment(Fragment paramFragment) {
    if (DEBUG)
      Log.v("FragmentManager", "detach: " + paramFragment); 
    if (!paramFragment.mDetached) {
      paramFragment.mDetached = true;
      if (paramFragment.mAdded) {
        if (DEBUG)
          Log.v("FragmentManager", "remove from detach: " + paramFragment); 
        synchronized (this.mAdded) {
          this.mAdded.remove(paramFragment);
          if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
            this.mNeedMenuInvalidate = true; 
          paramFragment.mAdded = false;
          return;
        } 
      } 
    } 
  }
  
  public void dispatchActivityCreated() {
    this.mStateSaved = false;
    this.mStopped = false;
    dispatchStateChange(2);
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration) {
    for (int i = 0; i < this.mAdded.size(); i++) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performConfigurationChanged(paramConfiguration); 
    } 
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem) {
    if (this.mCurState >= 1) {
      int i = 0;
      while (true) {
        if (i < this.mAdded.size()) {
          Fragment fragment = this.mAdded.get(i);
          if (fragment != null && fragment.performContextItemSelected(paramMenuItem))
            return true; 
          i++;
          continue;
        } 
        return false;
      } 
    } 
    return false;
  }
  
  public void dispatchCreate() {
    this.mStateSaved = false;
    this.mStopped = false;
    dispatchStateChange(1);
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {
    if (this.mCurState < 1)
      return false; 
    boolean bool = false;
    ArrayList<Fragment> arrayList = null;
    int i = 0;
    while (i < this.mAdded.size()) {
      Fragment fragment = this.mAdded.get(i);
      ArrayList<Fragment> arrayList1 = arrayList;
      boolean bool1 = bool;
      if (fragment != null) {
        arrayList1 = arrayList;
        bool1 = bool;
        if (fragment.performCreateOptionsMenu(paramMenu, paramMenuInflater)) {
          bool1 = true;
          arrayList1 = arrayList;
          if (arrayList == null)
            arrayList1 = new ArrayList(); 
          arrayList1.add(fragment);
        } 
      } 
      i++;
      arrayList = arrayList1;
      bool = bool1;
    } 
    if (this.mCreatedMenus != null)
      for (i = 0; i < this.mCreatedMenus.size(); i++) {
        Fragment fragment = this.mCreatedMenus.get(i);
        if (arrayList == null || !arrayList.contains(fragment))
          fragment.onDestroyOptionsMenu(); 
      }  
    this.mCreatedMenus = arrayList;
    return bool;
  }
  
  public void dispatchDestroy() {
    this.mDestroyed = true;
    execPendingActions();
    dispatchStateChange(0);
    this.mHost = null;
    this.mContainer = null;
    this.mParent = null;
  }
  
  public void dispatchDestroyView() {
    dispatchStateChange(1);
  }
  
  public void dispatchLowMemory() {
    for (int i = 0; i < this.mAdded.size(); i++) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performLowMemory(); 
    } 
  }
  
  public void dispatchMultiWindowModeChanged(boolean paramBoolean) {
    for (int i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performMultiWindowModeChanged(paramBoolean); 
    } 
  }
  
  void dispatchOnFragmentActivityCreated(@NonNull Fragment paramFragment, @Nullable Bundle paramBundle, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentActivityCreated(paramFragment, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentActivityCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentAttached(@NonNull Fragment paramFragment, @NonNull Context paramContext, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentAttached(paramFragment, paramContext, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentAttached(this, paramFragment, paramContext); 
    } 
  }
  
  void dispatchOnFragmentCreated(@NonNull Fragment paramFragment, @Nullable Bundle paramBundle, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentCreated(paramFragment, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentDestroyed(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentDestroyed(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentDestroyed(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentDetached(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentDetached(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentDetached(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentPaused(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPaused(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentPaused(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentPreAttached(@NonNull Fragment paramFragment, @NonNull Context paramContext, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPreAttached(paramFragment, paramContext, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentPreAttached(this, paramFragment, paramContext); 
    } 
  }
  
  void dispatchOnFragmentPreCreated(@NonNull Fragment paramFragment, @Nullable Bundle paramBundle, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPreCreated(paramFragment, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentPreCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentResumed(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentResumed(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentResumed(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentSaveInstanceState(@NonNull Fragment paramFragment, @NonNull Bundle paramBundle, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentSaveInstanceState(paramFragment, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentSaveInstanceState(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentStarted(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentStarted(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentStarted(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentStopped(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentStopped(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentStopped(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentViewCreated(@NonNull Fragment paramFragment, @NonNull View paramView, @Nullable Bundle paramBundle, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentViewCreated(paramFragment, paramView, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentViewCreated(this, paramFragment, paramView, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentViewDestroyed(@NonNull Fragment paramFragment, boolean paramBoolean) {
    if (this.mParent != null) {
      FragmentManager fragmentManager = this.mParent.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentViewDestroyed(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentViewDestroyed(this, paramFragment); 
    } 
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem) {
    if (this.mCurState >= 1) {
      int i = 0;
      while (true) {
        if (i < this.mAdded.size()) {
          Fragment fragment = this.mAdded.get(i);
          if (fragment != null && fragment.performOptionsItemSelected(paramMenuItem))
            return true; 
          i++;
          continue;
        } 
        return false;
      } 
    } 
    return false;
  }
  
  public void dispatchOptionsMenuClosed(Menu paramMenu) {
    if (this.mCurState >= 1) {
      int i = 0;
      while (true) {
        if (i < this.mAdded.size()) {
          Fragment fragment = this.mAdded.get(i);
          if (fragment != null)
            fragment.performOptionsMenuClosed(paramMenu); 
          i++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public void dispatchPause() {
    dispatchStateChange(3);
  }
  
  public void dispatchPictureInPictureModeChanged(boolean paramBoolean) {
    for (int i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performPictureInPictureModeChanged(paramBoolean); 
    } 
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu) {
    if (this.mCurState < 1)
      return false; 
    boolean bool = false;
    int i = 0;
    while (true) {
      boolean bool1 = bool;
      if (i < this.mAdded.size()) {
        Fragment fragment = this.mAdded.get(i);
        bool1 = bool;
        if (fragment != null) {
          bool1 = bool;
          if (fragment.performPrepareOptionsMenu(paramMenu))
            bool1 = true; 
        } 
        i++;
        bool = bool1;
        continue;
      } 
      return bool1;
    } 
  }
  
  public void dispatchResume() {
    this.mStateSaved = false;
    this.mStopped = false;
    dispatchStateChange(4);
  }
  
  public void dispatchStart() {
    this.mStateSaved = false;
    this.mStopped = false;
    dispatchStateChange(3);
  }
  
  public void dispatchStop() {
    this.mStopped = true;
    dispatchStateChange(2);
  }
  
  void doPendingDeferredStart() {
    if (this.mHavePendingDeferredStart) {
      this.mHavePendingDeferredStart = false;
      startPendingDeferredFragments();
    } 
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: aload_1
    //   8: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   11: ldc_w '    '
    //   14: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: invokevirtual toString : ()Ljava/lang/String;
    //   20: astore #7
    //   22: aload_0
    //   23: getfield mActive : Landroid/util/SparseArray;
    //   26: ifnull -> 153
    //   29: aload_0
    //   30: getfield mActive : Landroid/util/SparseArray;
    //   33: invokevirtual size : ()I
    //   36: istore #6
    //   38: iload #6
    //   40: ifle -> 153
    //   43: aload_3
    //   44: aload_1
    //   45: invokevirtual print : (Ljava/lang/String;)V
    //   48: aload_3
    //   49: ldc_w 'Active Fragments in '
    //   52: invokevirtual print : (Ljava/lang/String;)V
    //   55: aload_3
    //   56: aload_0
    //   57: invokestatic identityHashCode : (Ljava/lang/Object;)I
    //   60: invokestatic toHexString : (I)Ljava/lang/String;
    //   63: invokevirtual print : (Ljava/lang/String;)V
    //   66: aload_3
    //   67: ldc_w ':'
    //   70: invokevirtual println : (Ljava/lang/String;)V
    //   73: iconst_0
    //   74: istore #5
    //   76: iload #5
    //   78: iload #6
    //   80: if_icmpge -> 153
    //   83: aload_0
    //   84: getfield mActive : Landroid/util/SparseArray;
    //   87: iload #5
    //   89: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   92: checkcast androidx/fragment/app/Fragment
    //   95: astore #8
    //   97: aload_3
    //   98: aload_1
    //   99: invokevirtual print : (Ljava/lang/String;)V
    //   102: aload_3
    //   103: ldc_w '  #'
    //   106: invokevirtual print : (Ljava/lang/String;)V
    //   109: aload_3
    //   110: iload #5
    //   112: invokevirtual print : (I)V
    //   115: aload_3
    //   116: ldc_w ': '
    //   119: invokevirtual print : (Ljava/lang/String;)V
    //   122: aload_3
    //   123: aload #8
    //   125: invokevirtual println : (Ljava/lang/Object;)V
    //   128: aload #8
    //   130: ifnull -> 144
    //   133: aload #8
    //   135: aload #7
    //   137: aload_2
    //   138: aload_3
    //   139: aload #4
    //   141: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   144: iload #5
    //   146: iconst_1
    //   147: iadd
    //   148: istore #5
    //   150: goto -> 76
    //   153: aload_0
    //   154: getfield mAdded : Ljava/util/ArrayList;
    //   157: invokevirtual size : ()I
    //   160: istore #6
    //   162: iload #6
    //   164: ifle -> 246
    //   167: aload_3
    //   168: aload_1
    //   169: invokevirtual print : (Ljava/lang/String;)V
    //   172: aload_3
    //   173: ldc_w 'Added Fragments:'
    //   176: invokevirtual println : (Ljava/lang/String;)V
    //   179: iconst_0
    //   180: istore #5
    //   182: iload #5
    //   184: iload #6
    //   186: if_icmpge -> 246
    //   189: aload_0
    //   190: getfield mAdded : Ljava/util/ArrayList;
    //   193: iload #5
    //   195: invokevirtual get : (I)Ljava/lang/Object;
    //   198: checkcast androidx/fragment/app/Fragment
    //   201: astore #8
    //   203: aload_3
    //   204: aload_1
    //   205: invokevirtual print : (Ljava/lang/String;)V
    //   208: aload_3
    //   209: ldc_w '  #'
    //   212: invokevirtual print : (Ljava/lang/String;)V
    //   215: aload_3
    //   216: iload #5
    //   218: invokevirtual print : (I)V
    //   221: aload_3
    //   222: ldc_w ': '
    //   225: invokevirtual print : (Ljava/lang/String;)V
    //   228: aload_3
    //   229: aload #8
    //   231: invokevirtual toString : ()Ljava/lang/String;
    //   234: invokevirtual println : (Ljava/lang/String;)V
    //   237: iload #5
    //   239: iconst_1
    //   240: iadd
    //   241: istore #5
    //   243: goto -> 182
    //   246: aload_0
    //   247: getfield mCreatedMenus : Ljava/util/ArrayList;
    //   250: ifnull -> 346
    //   253: aload_0
    //   254: getfield mCreatedMenus : Ljava/util/ArrayList;
    //   257: invokevirtual size : ()I
    //   260: istore #6
    //   262: iload #6
    //   264: ifle -> 346
    //   267: aload_3
    //   268: aload_1
    //   269: invokevirtual print : (Ljava/lang/String;)V
    //   272: aload_3
    //   273: ldc_w 'Fragments Created Menus:'
    //   276: invokevirtual println : (Ljava/lang/String;)V
    //   279: iconst_0
    //   280: istore #5
    //   282: iload #5
    //   284: iload #6
    //   286: if_icmpge -> 346
    //   289: aload_0
    //   290: getfield mCreatedMenus : Ljava/util/ArrayList;
    //   293: iload #5
    //   295: invokevirtual get : (I)Ljava/lang/Object;
    //   298: checkcast androidx/fragment/app/Fragment
    //   301: astore #8
    //   303: aload_3
    //   304: aload_1
    //   305: invokevirtual print : (Ljava/lang/String;)V
    //   308: aload_3
    //   309: ldc_w '  #'
    //   312: invokevirtual print : (Ljava/lang/String;)V
    //   315: aload_3
    //   316: iload #5
    //   318: invokevirtual print : (I)V
    //   321: aload_3
    //   322: ldc_w ': '
    //   325: invokevirtual print : (Ljava/lang/String;)V
    //   328: aload_3
    //   329: aload #8
    //   331: invokevirtual toString : ()Ljava/lang/String;
    //   334: invokevirtual println : (Ljava/lang/String;)V
    //   337: iload #5
    //   339: iconst_1
    //   340: iadd
    //   341: istore #5
    //   343: goto -> 282
    //   346: aload_0
    //   347: getfield mBackStack : Ljava/util/ArrayList;
    //   350: ifnull -> 457
    //   353: aload_0
    //   354: getfield mBackStack : Ljava/util/ArrayList;
    //   357: invokevirtual size : ()I
    //   360: istore #6
    //   362: iload #6
    //   364: ifle -> 457
    //   367: aload_3
    //   368: aload_1
    //   369: invokevirtual print : (Ljava/lang/String;)V
    //   372: aload_3
    //   373: ldc_w 'Back Stack:'
    //   376: invokevirtual println : (Ljava/lang/String;)V
    //   379: iconst_0
    //   380: istore #5
    //   382: iload #5
    //   384: iload #6
    //   386: if_icmpge -> 457
    //   389: aload_0
    //   390: getfield mBackStack : Ljava/util/ArrayList;
    //   393: iload #5
    //   395: invokevirtual get : (I)Ljava/lang/Object;
    //   398: checkcast androidx/fragment/app/BackStackRecord
    //   401: astore #8
    //   403: aload_3
    //   404: aload_1
    //   405: invokevirtual print : (Ljava/lang/String;)V
    //   408: aload_3
    //   409: ldc_w '  #'
    //   412: invokevirtual print : (Ljava/lang/String;)V
    //   415: aload_3
    //   416: iload #5
    //   418: invokevirtual print : (I)V
    //   421: aload_3
    //   422: ldc_w ': '
    //   425: invokevirtual print : (Ljava/lang/String;)V
    //   428: aload_3
    //   429: aload #8
    //   431: invokevirtual toString : ()Ljava/lang/String;
    //   434: invokevirtual println : (Ljava/lang/String;)V
    //   437: aload #8
    //   439: aload #7
    //   441: aload_2
    //   442: aload_3
    //   443: aload #4
    //   445: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   448: iload #5
    //   450: iconst_1
    //   451: iadd
    //   452: istore #5
    //   454: goto -> 382
    //   457: aload_0
    //   458: monitorenter
    //   459: aload_0
    //   460: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   463: ifnull -> 554
    //   466: aload_0
    //   467: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   470: invokevirtual size : ()I
    //   473: istore #6
    //   475: iload #6
    //   477: ifle -> 554
    //   480: aload_3
    //   481: aload_1
    //   482: invokevirtual print : (Ljava/lang/String;)V
    //   485: aload_3
    //   486: ldc_w 'Back Stack Indices:'
    //   489: invokevirtual println : (Ljava/lang/String;)V
    //   492: iconst_0
    //   493: istore #5
    //   495: iload #5
    //   497: iload #6
    //   499: if_icmpge -> 554
    //   502: aload_0
    //   503: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   506: iload #5
    //   508: invokevirtual get : (I)Ljava/lang/Object;
    //   511: checkcast androidx/fragment/app/BackStackRecord
    //   514: astore_2
    //   515: aload_3
    //   516: aload_1
    //   517: invokevirtual print : (Ljava/lang/String;)V
    //   520: aload_3
    //   521: ldc_w '  #'
    //   524: invokevirtual print : (Ljava/lang/String;)V
    //   527: aload_3
    //   528: iload #5
    //   530: invokevirtual print : (I)V
    //   533: aload_3
    //   534: ldc_w ': '
    //   537: invokevirtual print : (Ljava/lang/String;)V
    //   540: aload_3
    //   541: aload_2
    //   542: invokevirtual println : (Ljava/lang/Object;)V
    //   545: iload #5
    //   547: iconst_1
    //   548: iadd
    //   549: istore #5
    //   551: goto -> 495
    //   554: aload_0
    //   555: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   558: ifnull -> 597
    //   561: aload_0
    //   562: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   565: invokevirtual size : ()I
    //   568: ifle -> 597
    //   571: aload_3
    //   572: aload_1
    //   573: invokevirtual print : (Ljava/lang/String;)V
    //   576: aload_3
    //   577: ldc_w 'mAvailBackStackIndices: '
    //   580: invokevirtual print : (Ljava/lang/String;)V
    //   583: aload_3
    //   584: aload_0
    //   585: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   588: invokevirtual toArray : ()[Ljava/lang/Object;
    //   591: invokestatic toString : ([Ljava/lang/Object;)Ljava/lang/String;
    //   594: invokevirtual println : (Ljava/lang/String;)V
    //   597: aload_0
    //   598: monitorexit
    //   599: aload_0
    //   600: getfield mPendingActions : Ljava/util/ArrayList;
    //   603: ifnull -> 699
    //   606: aload_0
    //   607: getfield mPendingActions : Ljava/util/ArrayList;
    //   610: invokevirtual size : ()I
    //   613: istore #6
    //   615: iload #6
    //   617: ifle -> 699
    //   620: aload_3
    //   621: aload_1
    //   622: invokevirtual print : (Ljava/lang/String;)V
    //   625: aload_3
    //   626: ldc_w 'Pending Actions:'
    //   629: invokevirtual println : (Ljava/lang/String;)V
    //   632: iconst_0
    //   633: istore #5
    //   635: iload #5
    //   637: iload #6
    //   639: if_icmpge -> 699
    //   642: aload_0
    //   643: getfield mPendingActions : Ljava/util/ArrayList;
    //   646: iload #5
    //   648: invokevirtual get : (I)Ljava/lang/Object;
    //   651: checkcast androidx/fragment/app/FragmentManagerImpl$OpGenerator
    //   654: astore_2
    //   655: aload_3
    //   656: aload_1
    //   657: invokevirtual print : (Ljava/lang/String;)V
    //   660: aload_3
    //   661: ldc_w '  #'
    //   664: invokevirtual print : (Ljava/lang/String;)V
    //   667: aload_3
    //   668: iload #5
    //   670: invokevirtual print : (I)V
    //   673: aload_3
    //   674: ldc_w ': '
    //   677: invokevirtual print : (Ljava/lang/String;)V
    //   680: aload_3
    //   681: aload_2
    //   682: invokevirtual println : (Ljava/lang/Object;)V
    //   685: iload #5
    //   687: iconst_1
    //   688: iadd
    //   689: istore #5
    //   691: goto -> 635
    //   694: astore_1
    //   695: aload_0
    //   696: monitorexit
    //   697: aload_1
    //   698: athrow
    //   699: aload_3
    //   700: aload_1
    //   701: invokevirtual print : (Ljava/lang/String;)V
    //   704: aload_3
    //   705: ldc_w 'FragmentManager misc state:'
    //   708: invokevirtual println : (Ljava/lang/String;)V
    //   711: aload_3
    //   712: aload_1
    //   713: invokevirtual print : (Ljava/lang/String;)V
    //   716: aload_3
    //   717: ldc_w '  mHost='
    //   720: invokevirtual print : (Ljava/lang/String;)V
    //   723: aload_3
    //   724: aload_0
    //   725: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   728: invokevirtual println : (Ljava/lang/Object;)V
    //   731: aload_3
    //   732: aload_1
    //   733: invokevirtual print : (Ljava/lang/String;)V
    //   736: aload_3
    //   737: ldc_w '  mContainer='
    //   740: invokevirtual print : (Ljava/lang/String;)V
    //   743: aload_3
    //   744: aload_0
    //   745: getfield mContainer : Landroidx/fragment/app/FragmentContainer;
    //   748: invokevirtual println : (Ljava/lang/Object;)V
    //   751: aload_0
    //   752: getfield mParent : Landroidx/fragment/app/Fragment;
    //   755: ifnull -> 778
    //   758: aload_3
    //   759: aload_1
    //   760: invokevirtual print : (Ljava/lang/String;)V
    //   763: aload_3
    //   764: ldc_w '  mParent='
    //   767: invokevirtual print : (Ljava/lang/String;)V
    //   770: aload_3
    //   771: aload_0
    //   772: getfield mParent : Landroidx/fragment/app/Fragment;
    //   775: invokevirtual println : (Ljava/lang/Object;)V
    //   778: aload_3
    //   779: aload_1
    //   780: invokevirtual print : (Ljava/lang/String;)V
    //   783: aload_3
    //   784: ldc_w '  mCurState='
    //   787: invokevirtual print : (Ljava/lang/String;)V
    //   790: aload_3
    //   791: aload_0
    //   792: getfield mCurState : I
    //   795: invokevirtual print : (I)V
    //   798: aload_3
    //   799: ldc_w ' mStateSaved='
    //   802: invokevirtual print : (Ljava/lang/String;)V
    //   805: aload_3
    //   806: aload_0
    //   807: getfield mStateSaved : Z
    //   810: invokevirtual print : (Z)V
    //   813: aload_3
    //   814: ldc_w ' mStopped='
    //   817: invokevirtual print : (Ljava/lang/String;)V
    //   820: aload_3
    //   821: aload_0
    //   822: getfield mStopped : Z
    //   825: invokevirtual print : (Z)V
    //   828: aload_3
    //   829: ldc_w ' mDestroyed='
    //   832: invokevirtual print : (Ljava/lang/String;)V
    //   835: aload_3
    //   836: aload_0
    //   837: getfield mDestroyed : Z
    //   840: invokevirtual println : (Z)V
    //   843: aload_0
    //   844: getfield mNeedMenuInvalidate : Z
    //   847: ifeq -> 870
    //   850: aload_3
    //   851: aload_1
    //   852: invokevirtual print : (Ljava/lang/String;)V
    //   855: aload_3
    //   856: ldc_w '  mNeedMenuInvalidate='
    //   859: invokevirtual print : (Ljava/lang/String;)V
    //   862: aload_3
    //   863: aload_0
    //   864: getfield mNeedMenuInvalidate : Z
    //   867: invokevirtual println : (Z)V
    //   870: aload_0
    //   871: getfield mNoTransactionsBecause : Ljava/lang/String;
    //   874: ifnull -> 897
    //   877: aload_3
    //   878: aload_1
    //   879: invokevirtual print : (Ljava/lang/String;)V
    //   882: aload_3
    //   883: ldc_w '  mNoTransactionsBecause='
    //   886: invokevirtual print : (Ljava/lang/String;)V
    //   889: aload_3
    //   890: aload_0
    //   891: getfield mNoTransactionsBecause : Ljava/lang/String;
    //   894: invokevirtual println : (Ljava/lang/String;)V
    //   897: return
    // Exception table:
    //   from	to	target	type
    //   459	475	694	finally
    //   480	492	694	finally
    //   502	545	694	finally
    //   554	597	694	finally
    //   597	599	694	finally
    //   695	697	694	finally
  }
  
  public void enqueueAction(OpGenerator paramOpGenerator, boolean paramBoolean) {
    // Byte code:
    //   0: iload_2
    //   1: ifne -> 8
    //   4: aload_0
    //   5: invokespecial checkStateLoss : ()V
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield mDestroyed : Z
    //   14: ifne -> 24
    //   17: aload_0
    //   18: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   21: ifnonnull -> 47
    //   24: iload_2
    //   25: ifeq -> 31
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: new java/lang/IllegalStateException
    //   34: dup
    //   35: ldc_w 'Activity has been destroyed'
    //   38: invokespecial <init> : (Ljava/lang/String;)V
    //   41: athrow
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: athrow
    //   47: aload_0
    //   48: getfield mPendingActions : Ljava/util/ArrayList;
    //   51: ifnonnull -> 65
    //   54: aload_0
    //   55: new java/util/ArrayList
    //   58: dup
    //   59: invokespecial <init> : ()V
    //   62: putfield mPendingActions : Ljava/util/ArrayList;
    //   65: aload_0
    //   66: getfield mPendingActions : Ljava/util/ArrayList;
    //   69: aload_1
    //   70: invokevirtual add : (Ljava/lang/Object;)Z
    //   73: pop
    //   74: aload_0
    //   75: invokevirtual scheduleCommit : ()V
    //   78: aload_0
    //   79: monitorexit
    //   80: return
    // Exception table:
    //   from	to	target	type
    //   10	24	42	finally
    //   28	30	42	finally
    //   31	42	42	finally
    //   43	45	42	finally
    //   47	65	42	finally
    //   65	80	42	finally
  }
  
  void ensureInflatedFragmentView(Fragment paramFragment) {
    if (paramFragment.mFromLayout && !paramFragment.mPerformedCreateView) {
      paramFragment.performCreateView(paramFragment.performGetLayoutInflater(paramFragment.mSavedFragmentState), null, paramFragment.mSavedFragmentState);
      if (paramFragment.mView != null) {
        paramFragment.mInnerView = paramFragment.mView;
        paramFragment.mView.setSaveFromParentEnabled(false);
        if (paramFragment.mHidden)
          paramFragment.mView.setVisibility(8); 
        paramFragment.onViewCreated(paramFragment.mView, paramFragment.mSavedFragmentState);
        dispatchOnFragmentViewCreated(paramFragment, paramFragment.mView, paramFragment.mSavedFragmentState, false);
        return;
      } 
    } else {
      return;
    } 
    paramFragment.mInnerView = null;
  }
  
  public boolean execPendingActions() {
    ensureExecReady(true);
    boolean bool = false;
    while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
        cleanupExec();
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
    return bool;
  }
  
  public void execSingleAction(OpGenerator paramOpGenerator, boolean paramBoolean) {
    if (paramBoolean && (this.mHost == null || this.mDestroyed))
      return; 
    ensureExecReady(paramBoolean);
    if (paramOpGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
        cleanupExec();
        doPendingDeferredStart();
        return;
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
  }
  
  public boolean executePendingTransactions() {
    boolean bool = execPendingActions();
    forcePostponedTransactions();
    return bool;
  }
  
  @Nullable
  public Fragment findFragmentById(int paramInt) {
    int i = this.mAdded.size() - 1;
    while (i >= 0) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment == null || fragment.mFragmentId != paramInt) {
        i--;
        continue;
      } 
      return fragment;
    } 
    if (this.mActive != null)
      for (i = this.mActive.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null) {
          Fragment fragment1 = fragment;
          if (fragment.mFragmentId != paramInt)
            continue; 
          return fragment1;
        } 
        continue;
      }  
    return null;
  }
  
  @Nullable
  public Fragment findFragmentByTag(@Nullable String paramString) {
    if (paramString != null) {
      int i = this.mAdded.size() - 1;
      while (i >= 0) {
        Fragment fragment = this.mAdded.get(i);
        if (fragment == null || !paramString.equals(fragment.mTag)) {
          i--;
          continue;
        } 
        return fragment;
      } 
    } 
    if (this.mActive != null && paramString != null)
      for (int i = this.mActive.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null) {
          Fragment fragment1 = fragment;
          if (!paramString.equals(fragment.mTag))
            continue; 
          return fragment1;
        } 
        continue;
      }  
    return null;
  }
  
  public Fragment findFragmentByWho(String paramString) {
    if (this.mActive != null && paramString != null)
      for (int i = this.mActive.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null) {
          fragment = fragment.findFragmentByWho(paramString);
          if (fragment != null)
            return fragment; 
        } 
      }  
    return null;
  }
  
  public void freeBackStackIndex(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   6: iload_1
    //   7: aconst_null
    //   8: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   11: pop
    //   12: aload_0
    //   13: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   16: ifnonnull -> 30
    //   19: aload_0
    //   20: new java/util/ArrayList
    //   23: dup
    //   24: invokespecial <init> : ()V
    //   27: putfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   30: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   33: ifeq -> 62
    //   36: ldc 'FragmentManager'
    //   38: new java/lang/StringBuilder
    //   41: dup
    //   42: invokespecial <init> : ()V
    //   45: ldc_w 'Freeing back stack index '
    //   48: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: iload_1
    //   52: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   55: invokevirtual toString : ()Ljava/lang/String;
    //   58: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   61: pop
    //   62: aload_0
    //   63: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   66: iload_1
    //   67: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   70: invokevirtual add : (Ljava/lang/Object;)Z
    //   73: pop
    //   74: aload_0
    //   75: monitorexit
    //   76: return
    //   77: astore_2
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_2
    //   81: athrow
    // Exception table:
    //   from	to	target	type
    //   2	30	77	finally
    //   30	62	77	finally
    //   62	76	77	finally
    //   78	80	77	finally
  }
  
  int getActiveFragmentCount() {
    return (this.mActive == null) ? 0 : this.mActive.size();
  }
  
  List<Fragment> getActiveFragments() {
    if (this.mActive == null)
      return null; 
    int j = this.mActive.size();
    ArrayList<Object> arrayList = new ArrayList(j);
    int i = 0;
    while (true) {
      ArrayList<Object> arrayList1 = arrayList;
      if (i < j) {
        arrayList.add(this.mActive.valueAt(i));
        i++;
        continue;
      } 
      return arrayList1;
    } 
  }
  
  public FragmentManager.BackStackEntry getBackStackEntryAt(int paramInt) {
    return this.mBackStack.get(paramInt);
  }
  
  public int getBackStackEntryCount() {
    return (this.mBackStack != null) ? this.mBackStack.size() : 0;
  }
  
  @Nullable
  public Fragment getFragment(Bundle paramBundle, String paramString) {
    int i = paramBundle.getInt(paramString, -1);
    if (i == -1)
      return null; 
    Fragment fragment2 = (Fragment)this.mActive.get(i);
    Fragment fragment1 = fragment2;
    if (fragment2 == null) {
      throwException(new IllegalStateException("Fragment no longer exists for key " + paramString + ": index " + i));
      return fragment2;
    } 
    return fragment1;
  }
  
  public List<Fragment> getFragments() {
    if (this.mAdded.isEmpty())
      return Collections.emptyList(); 
    synchronized (this.mAdded) {
      return (List)this.mAdded.clone();
    } 
  }
  
  LayoutInflater.Factory2 getLayoutInflaterFactory() {
    return this;
  }
  
  @Nullable
  public Fragment getPrimaryNavigationFragment() {
    return this.mPrimaryNav;
  }
  
  public void hideFragment(Fragment paramFragment) {
    boolean bool = true;
    if (DEBUG)
      Log.v("FragmentManager", "hide: " + paramFragment); 
    if (!paramFragment.mHidden) {
      paramFragment.mHidden = true;
      if (paramFragment.mHiddenChanged)
        bool = false; 
      paramFragment.mHiddenChanged = bool;
    } 
  }
  
  public boolean isDestroyed() {
    return this.mDestroyed;
  }
  
  boolean isStateAtLeast(int paramInt) {
    return (this.mCurState >= paramInt);
  }
  
  public boolean isStateSaved() {
    return (this.mStateSaved || this.mStopped);
  }
  
  AnimationOrAnimator loadAnimation(Fragment paramFragment, int paramInt1, boolean paramBoolean, int paramInt2) {
    int i = paramFragment.getNextAnim();
    Animation animation = paramFragment.onCreateAnimation(paramInt1, paramBoolean, i);
    if (animation != null)
      return new AnimationOrAnimator(animation); 
    Animator animator = paramFragment.onCreateAnimator(paramInt1, paramBoolean, i);
    if (animator != null)
      return new AnimationOrAnimator(animator); 
    if (i != 0) {
      boolean bool = "anim".equals(this.mHost.getContext().getResources().getResourceTypeName(i));
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (bool)
        try {
          Animation animation1 = AnimationUtils.loadAnimation(this.mHost.getContext(), i);
          if (animation1 != null)
            return new AnimationOrAnimator(animation1); 
          bool1 = true;
        } catch (android.content.res.Resources.NotFoundException notFoundException) {
          throw notFoundException;
        } catch (RuntimeException runtimeException) {
          bool1 = bool2;
        }  
      if (!bool1)
        try {
          animator = AnimatorInflater.loadAnimator(this.mHost.getContext(), i);
          if (animator != null)
            return new AnimationOrAnimator(animator); 
        } catch (RuntimeException runtimeException) {
          if (bool)
            throw runtimeException; 
          Animation animation1 = AnimationUtils.loadAnimation(this.mHost.getContext(), i);
          if (animation1 != null)
            return new AnimationOrAnimator(animation1); 
        }  
    } 
    if (paramInt1 == 0)
      return null; 
    paramInt1 = transitToStyleIndex(paramInt1, paramBoolean);
    if (paramInt1 < 0)
      return null; 
    switch (paramInt1) {
      default:
        paramInt1 = paramInt2;
        if (paramInt2 == 0) {
          paramInt1 = paramInt2;
          if (this.mHost.onHasWindowAnimations())
            paramInt1 = this.mHost.onGetWindowAnimations(); 
        } 
        return (AnimationOrAnimator)((paramInt1 == 0) ? null : null);
      case 1:
        return makeOpenCloseAnimation(this.mHost.getContext(), 1.125F, 1.0F, 0.0F, 1.0F);
      case 2:
        return makeOpenCloseAnimation(this.mHost.getContext(), 1.0F, 0.975F, 1.0F, 0.0F);
      case 3:
        return makeOpenCloseAnimation(this.mHost.getContext(), 0.975F, 1.0F, 0.0F, 1.0F);
      case 4:
        return makeOpenCloseAnimation(this.mHost.getContext(), 1.0F, 1.075F, 1.0F, 0.0F);
      case 5:
        return makeFadeAnimation(this.mHost.getContext(), 0.0F, 1.0F);
      case 6:
        break;
    } 
    return makeFadeAnimation(this.mHost.getContext(), 1.0F, 0.0F);
  }
  
  void makeActive(Fragment paramFragment) {
    if (paramFragment.mIndex < 0) {
      int i = this.mNextFragmentIndex;
      this.mNextFragmentIndex = i + 1;
      paramFragment.setIndex(i, this.mParent);
      if (this.mActive == null)
        this.mActive = new SparseArray(); 
      this.mActive.put(paramFragment.mIndex, paramFragment);
      if (DEBUG) {
        Log.v("FragmentManager", "Allocated fragment index " + paramFragment);
        return;
      } 
    } 
  }
  
  void makeInactive(Fragment paramFragment) {
    if (paramFragment.mIndex < 0)
      return; 
    if (DEBUG)
      Log.v("FragmentManager", "Freeing fragment index " + paramFragment); 
    this.mActive.put(paramFragment.mIndex, null);
    paramFragment.initState();
  }
  
  void moveFragmentToExpectedState(Fragment paramFragment) {
    if (paramFragment != null) {
      int j = this.mCurState;
      int i = j;
      if (paramFragment.mRemoving)
        if (paramFragment.isInBackStack()) {
          i = Math.min(j, 1);
        } else {
          i = Math.min(j, 0);
        }  
      moveToState(paramFragment, i, paramFragment.getNextTransition(), paramFragment.getNextTransitionStyle(), false);
      if (paramFragment.mView != null) {
        Fragment fragment = findFragmentUnder(paramFragment);
        if (fragment != null) {
          View view = fragment.mView;
          ViewGroup viewGroup = paramFragment.mContainer;
          i = viewGroup.indexOfChild(view);
          j = viewGroup.indexOfChild(paramFragment.mView);
          if (j < i) {
            viewGroup.removeViewAt(j);
            viewGroup.addView(paramFragment.mView, i);
          } 
        } 
        if (paramFragment.mIsNewlyAdded && paramFragment.mContainer != null) {
          if (paramFragment.mPostponedAlpha > 0.0F)
            paramFragment.mView.setAlpha(paramFragment.mPostponedAlpha); 
          paramFragment.mPostponedAlpha = 0.0F;
          paramFragment.mIsNewlyAdded = false;
          AnimationOrAnimator animationOrAnimator = loadAnimation(paramFragment, paramFragment.getNextTransition(), true, paramFragment.getNextTransitionStyle());
          if (animationOrAnimator != null) {
            setHWLayerAnimListenerIfAlpha(paramFragment.mView, animationOrAnimator);
            if (animationOrAnimator.animation != null) {
              paramFragment.mView.startAnimation(animationOrAnimator.animation);
            } else {
              animationOrAnimator.animator.setTarget(paramFragment.mView);
              animationOrAnimator.animator.start();
            } 
          } 
        } 
      } 
      if (paramFragment.mHiddenChanged) {
        completeShowHideFragment(paramFragment);
        return;
      } 
    } 
  }
  
  void moveToState(int paramInt, boolean paramBoolean) {
    if (this.mHost == null && paramInt != 0)
      throw new IllegalStateException("No activity"); 
    if (paramBoolean || paramInt != this.mCurState) {
      this.mCurState = paramInt;
      if (this.mActive != null) {
        int i = this.mAdded.size();
        for (paramInt = 0; paramInt < i; paramInt++)
          moveFragmentToExpectedState(this.mAdded.get(paramInt)); 
        i = this.mActive.size();
        for (paramInt = 0; paramInt < i; paramInt++) {
          Fragment fragment = (Fragment)this.mActive.valueAt(paramInt);
          if (fragment != null && (fragment.mRemoving || fragment.mDetached) && !fragment.mIsNewlyAdded)
            moveFragmentToExpectedState(fragment); 
        } 
        startPendingDeferredFragments();
        if (this.mNeedMenuInvalidate && this.mHost != null && this.mCurState == 4) {
          this.mHost.onSupportInvalidateOptionsMenu();
          this.mNeedMenuInvalidate = false;
          return;
        } 
      } 
    } 
  }
  
  void moveToState(Fragment paramFragment) {
    moveToState(paramFragment, this.mCurState, 0, 0, false);
  }
  
  void moveToState(Fragment paramFragment, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    // Byte code:
    //   0: aload_1
    //   1: getfield mAdded : Z
    //   4: ifeq -> 17
    //   7: iload_2
    //   8: istore #7
    //   10: aload_1
    //   11: getfield mDetached : Z
    //   14: ifeq -> 28
    //   17: iload_2
    //   18: istore #7
    //   20: iload_2
    //   21: iconst_1
    //   22: if_icmple -> 28
    //   25: iconst_1
    //   26: istore #7
    //   28: iload #7
    //   30: istore #6
    //   32: aload_1
    //   33: getfield mRemoving : Z
    //   36: ifeq -> 69
    //   39: iload #7
    //   41: istore #6
    //   43: iload #7
    //   45: aload_1
    //   46: getfield mState : I
    //   49: if_icmple -> 69
    //   52: aload_1
    //   53: getfield mState : I
    //   56: ifne -> 124
    //   59: aload_1
    //   60: invokevirtual isInBackStack : ()Z
    //   63: ifeq -> 124
    //   66: iconst_1
    //   67: istore #6
    //   69: iload #6
    //   71: istore_2
    //   72: aload_1
    //   73: getfield mDeferStart : Z
    //   76: ifeq -> 101
    //   79: iload #6
    //   81: istore_2
    //   82: aload_1
    //   83: getfield mState : I
    //   86: iconst_3
    //   87: if_icmpge -> 101
    //   90: iload #6
    //   92: istore_2
    //   93: iload #6
    //   95: iconst_2
    //   96: if_icmple -> 101
    //   99: iconst_2
    //   100: istore_2
    //   101: aload_1
    //   102: getfield mState : I
    //   105: iload_2
    //   106: if_icmpgt -> 1315
    //   109: aload_1
    //   110: getfield mFromLayout : Z
    //   113: ifeq -> 133
    //   116: aload_1
    //   117: getfield mInLayout : Z
    //   120: ifne -> 133
    //   123: return
    //   124: aload_1
    //   125: getfield mState : I
    //   128: istore #6
    //   130: goto -> 69
    //   133: aload_1
    //   134: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   137: ifnonnull -> 147
    //   140: aload_1
    //   141: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   144: ifnull -> 169
    //   147: aload_1
    //   148: aconst_null
    //   149: invokevirtual setAnimatingAway : (Landroid/view/View;)V
    //   152: aload_1
    //   153: aconst_null
    //   154: invokevirtual setAnimator : (Landroid/animation/Animator;)V
    //   157: aload_0
    //   158: aload_1
    //   159: aload_1
    //   160: invokevirtual getStateAfterAnimating : ()I
    //   163: iconst_0
    //   164: iconst_0
    //   165: iconst_1
    //   166: invokevirtual moveToState : (Landroidx/fragment/app/Fragment;IIIZ)V
    //   169: iload_2
    //   170: istore #4
    //   172: iload_2
    //   173: istore #6
    //   175: iload_2
    //   176: istore_3
    //   177: aload_1
    //   178: getfield mState : I
    //   181: tableswitch default -> 212, 0 -> 287, 1 -> 753, 2 -> 1144, 3 -> 1198
    //   212: iload_2
    //   213: istore #6
    //   215: aload_1
    //   216: getfield mState : I
    //   219: iload #6
    //   221: if_icmpeq -> 123
    //   224: ldc 'FragmentManager'
    //   226: new java/lang/StringBuilder
    //   229: dup
    //   230: invokespecial <init> : ()V
    //   233: ldc_w 'moveToState: Fragment state for '
    //   236: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: aload_1
    //   240: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   243: ldc_w ' not updated inline; '
    //   246: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   249: ldc_w 'expected state '
    //   252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: iload #6
    //   257: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   260: ldc_w ' found '
    //   263: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: aload_1
    //   267: getfield mState : I
    //   270: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   273: invokevirtual toString : ()Ljava/lang/String;
    //   276: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   279: pop
    //   280: aload_1
    //   281: iload #6
    //   283: putfield mState : I
    //   286: return
    //   287: iload_2
    //   288: istore #4
    //   290: iload_2
    //   291: ifle -> 753
    //   294: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   297: ifeq -> 326
    //   300: ldc 'FragmentManager'
    //   302: new java/lang/StringBuilder
    //   305: dup
    //   306: invokespecial <init> : ()V
    //   309: ldc_w 'moveto CREATED: '
    //   312: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: aload_1
    //   316: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   319: invokevirtual toString : ()Ljava/lang/String;
    //   322: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   325: pop
    //   326: iload_2
    //   327: istore #4
    //   329: aload_1
    //   330: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   333: ifnull -> 450
    //   336: aload_1
    //   337: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   340: aload_0
    //   341: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   344: invokevirtual getContext : ()Landroid/content/Context;
    //   347: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   350: invokevirtual setClassLoader : (Ljava/lang/ClassLoader;)V
    //   353: aload_1
    //   354: aload_1
    //   355: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   358: ldc 'android:view_state'
    //   360: invokevirtual getSparseParcelableArray : (Ljava/lang/String;)Landroid/util/SparseArray;
    //   363: putfield mSavedViewState : Landroid/util/SparseArray;
    //   366: aload_1
    //   367: aload_0
    //   368: aload_1
    //   369: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   372: ldc 'android:target_state'
    //   374: invokevirtual getFragment : (Landroid/os/Bundle;Ljava/lang/String;)Landroidx/fragment/app/Fragment;
    //   377: putfield mTarget : Landroidx/fragment/app/Fragment;
    //   380: aload_1
    //   381: getfield mTarget : Landroidx/fragment/app/Fragment;
    //   384: ifnull -> 401
    //   387: aload_1
    //   388: aload_1
    //   389: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   392: ldc 'android:target_req_state'
    //   394: iconst_0
    //   395: invokevirtual getInt : (Ljava/lang/String;I)I
    //   398: putfield mTargetRequestCode : I
    //   401: aload_1
    //   402: getfield mSavedUserVisibleHint : Ljava/lang/Boolean;
    //   405: ifnull -> 563
    //   408: aload_1
    //   409: aload_1
    //   410: getfield mSavedUserVisibleHint : Ljava/lang/Boolean;
    //   413: invokevirtual booleanValue : ()Z
    //   416: putfield mUserVisibleHint : Z
    //   419: aload_1
    //   420: aconst_null
    //   421: putfield mSavedUserVisibleHint : Ljava/lang/Boolean;
    //   424: iload_2
    //   425: istore #4
    //   427: aload_1
    //   428: getfield mUserVisibleHint : Z
    //   431: ifne -> 450
    //   434: aload_1
    //   435: iconst_1
    //   436: putfield mDeferStart : Z
    //   439: iload_2
    //   440: istore #4
    //   442: iload_2
    //   443: iconst_2
    //   444: if_icmple -> 450
    //   447: iconst_2
    //   448: istore #4
    //   450: aload_1
    //   451: aload_0
    //   452: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   455: putfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   458: aload_1
    //   459: aload_0
    //   460: getfield mParent : Landroidx/fragment/app/Fragment;
    //   463: putfield mParentFragment : Landroidx/fragment/app/Fragment;
    //   466: aload_0
    //   467: getfield mParent : Landroidx/fragment/app/Fragment;
    //   470: ifnull -> 580
    //   473: aload_0
    //   474: getfield mParent : Landroidx/fragment/app/Fragment;
    //   477: getfield mChildFragmentManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   480: astore #8
    //   482: aload_1
    //   483: aload #8
    //   485: putfield mFragmentManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   488: aload_1
    //   489: getfield mTarget : Landroidx/fragment/app/Fragment;
    //   492: ifnull -> 615
    //   495: aload_0
    //   496: getfield mActive : Landroid/util/SparseArray;
    //   499: aload_1
    //   500: getfield mTarget : Landroidx/fragment/app/Fragment;
    //   503: getfield mIndex : I
    //   506: invokevirtual get : (I)Ljava/lang/Object;
    //   509: aload_1
    //   510: getfield mTarget : Landroidx/fragment/app/Fragment;
    //   513: if_acmpeq -> 592
    //   516: new java/lang/IllegalStateException
    //   519: dup
    //   520: new java/lang/StringBuilder
    //   523: dup
    //   524: invokespecial <init> : ()V
    //   527: ldc_w 'Fragment '
    //   530: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   533: aload_1
    //   534: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   537: ldc_w ' declared target fragment '
    //   540: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   543: aload_1
    //   544: getfield mTarget : Landroidx/fragment/app/Fragment;
    //   547: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   550: ldc_w ' that does not belong to this FragmentManager!'
    //   553: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   556: invokevirtual toString : ()Ljava/lang/String;
    //   559: invokespecial <init> : (Ljava/lang/String;)V
    //   562: athrow
    //   563: aload_1
    //   564: aload_1
    //   565: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   568: ldc 'android:user_visible_hint'
    //   570: iconst_1
    //   571: invokevirtual getBoolean : (Ljava/lang/String;Z)Z
    //   574: putfield mUserVisibleHint : Z
    //   577: goto -> 424
    //   580: aload_0
    //   581: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   584: invokevirtual getFragmentManagerImpl : ()Landroidx/fragment/app/FragmentManagerImpl;
    //   587: astore #8
    //   589: goto -> 482
    //   592: aload_1
    //   593: getfield mTarget : Landroidx/fragment/app/Fragment;
    //   596: getfield mState : I
    //   599: iconst_1
    //   600: if_icmpge -> 615
    //   603: aload_0
    //   604: aload_1
    //   605: getfield mTarget : Landroidx/fragment/app/Fragment;
    //   608: iconst_1
    //   609: iconst_0
    //   610: iconst_0
    //   611: iconst_1
    //   612: invokevirtual moveToState : (Landroidx/fragment/app/Fragment;IIIZ)V
    //   615: aload_0
    //   616: aload_1
    //   617: aload_0
    //   618: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   621: invokevirtual getContext : ()Landroid/content/Context;
    //   624: iconst_0
    //   625: invokevirtual dispatchOnFragmentPreAttached : (Landroidx/fragment/app/Fragment;Landroid/content/Context;Z)V
    //   628: aload_1
    //   629: iconst_0
    //   630: putfield mCalled : Z
    //   633: aload_1
    //   634: aload_0
    //   635: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   638: invokevirtual getContext : ()Landroid/content/Context;
    //   641: invokevirtual onAttach : (Landroid/content/Context;)V
    //   644: aload_1
    //   645: getfield mCalled : Z
    //   648: ifne -> 685
    //   651: new androidx/fragment/app/SuperNotCalledException
    //   654: dup
    //   655: new java/lang/StringBuilder
    //   658: dup
    //   659: invokespecial <init> : ()V
    //   662: ldc_w 'Fragment '
    //   665: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   668: aload_1
    //   669: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   672: ldc_w ' did not call through to super.onAttach()'
    //   675: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   678: invokevirtual toString : ()Ljava/lang/String;
    //   681: invokespecial <init> : (Ljava/lang/String;)V
    //   684: athrow
    //   685: aload_1
    //   686: getfield mParentFragment : Landroidx/fragment/app/Fragment;
    //   689: ifnonnull -> 1264
    //   692: aload_0
    //   693: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   696: aload_1
    //   697: invokevirtual onAttachFragment : (Landroidx/fragment/app/Fragment;)V
    //   700: aload_0
    //   701: aload_1
    //   702: aload_0
    //   703: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   706: invokevirtual getContext : ()Landroid/content/Context;
    //   709: iconst_0
    //   710: invokevirtual dispatchOnFragmentAttached : (Landroidx/fragment/app/Fragment;Landroid/content/Context;Z)V
    //   713: aload_1
    //   714: getfield mIsCreated : Z
    //   717: ifne -> 1275
    //   720: aload_0
    //   721: aload_1
    //   722: aload_1
    //   723: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   726: iconst_0
    //   727: invokevirtual dispatchOnFragmentPreCreated : (Landroidx/fragment/app/Fragment;Landroid/os/Bundle;Z)V
    //   730: aload_1
    //   731: aload_1
    //   732: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   735: invokevirtual performCreate : (Landroid/os/Bundle;)V
    //   738: aload_0
    //   739: aload_1
    //   740: aload_1
    //   741: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   744: iconst_0
    //   745: invokevirtual dispatchOnFragmentCreated : (Landroidx/fragment/app/Fragment;Landroid/os/Bundle;Z)V
    //   748: aload_1
    //   749: iconst_0
    //   750: putfield mRetaining : Z
    //   753: aload_0
    //   754: aload_1
    //   755: invokevirtual ensureInflatedFragmentView : (Landroidx/fragment/app/Fragment;)V
    //   758: iload #4
    //   760: istore #6
    //   762: iload #4
    //   764: iconst_1
    //   765: if_icmple -> 1144
    //   768: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   771: ifeq -> 800
    //   774: ldc 'FragmentManager'
    //   776: new java/lang/StringBuilder
    //   779: dup
    //   780: invokespecial <init> : ()V
    //   783: ldc_w 'moveto ACTIVITY_CREATED: '
    //   786: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   789: aload_1
    //   790: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   793: invokevirtual toString : ()Ljava/lang/String;
    //   796: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   799: pop
    //   800: aload_1
    //   801: getfield mFromLayout : Z
    //   804: ifne -> 1102
    //   807: aconst_null
    //   808: astore #8
    //   810: aload_1
    //   811: getfield mContainerId : I
    //   814: ifeq -> 973
    //   817: aload_1
    //   818: getfield mContainerId : I
    //   821: iconst_m1
    //   822: if_icmpne -> 862
    //   825: aload_0
    //   826: new java/lang/IllegalArgumentException
    //   829: dup
    //   830: new java/lang/StringBuilder
    //   833: dup
    //   834: invokespecial <init> : ()V
    //   837: ldc_w 'Cannot create fragment '
    //   840: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   843: aload_1
    //   844: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   847: ldc_w ' for a container view with no id'
    //   850: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   853: invokevirtual toString : ()Ljava/lang/String;
    //   856: invokespecial <init> : (Ljava/lang/String;)V
    //   859: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   862: aload_0
    //   863: getfield mContainer : Landroidx/fragment/app/FragmentContainer;
    //   866: aload_1
    //   867: getfield mContainerId : I
    //   870: invokevirtual onFindViewById : (I)Landroid/view/View;
    //   873: checkcast android/view/ViewGroup
    //   876: astore #9
    //   878: aload #9
    //   880: astore #8
    //   882: aload #9
    //   884: ifnonnull -> 973
    //   887: aload #9
    //   889: astore #8
    //   891: aload_1
    //   892: getfield mRestored : Z
    //   895: ifne -> 973
    //   898: aload_1
    //   899: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   902: aload_1
    //   903: getfield mContainerId : I
    //   906: invokevirtual getResourceName : (I)Ljava/lang/String;
    //   909: astore #8
    //   911: aload_0
    //   912: new java/lang/IllegalArgumentException
    //   915: dup
    //   916: new java/lang/StringBuilder
    //   919: dup
    //   920: invokespecial <init> : ()V
    //   923: ldc_w 'No view found for id 0x'
    //   926: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   929: aload_1
    //   930: getfield mContainerId : I
    //   933: invokestatic toHexString : (I)Ljava/lang/String;
    //   936: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   939: ldc_w ' ('
    //   942: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   945: aload #8
    //   947: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   950: ldc_w ') for fragment '
    //   953: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   956: aload_1
    //   957: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   960: invokevirtual toString : ()Ljava/lang/String;
    //   963: invokespecial <init> : (Ljava/lang/String;)V
    //   966: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   969: aload #9
    //   971: astore #8
    //   973: aload_1
    //   974: aload #8
    //   976: putfield mContainer : Landroid/view/ViewGroup;
    //   979: aload_1
    //   980: aload_1
    //   981: aload_1
    //   982: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   985: invokevirtual performGetLayoutInflater : (Landroid/os/Bundle;)Landroid/view/LayoutInflater;
    //   988: aload #8
    //   990: aload_1
    //   991: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   994: invokevirtual performCreateView : (Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)V
    //   997: aload_1
    //   998: getfield mView : Landroid/view/View;
    //   1001: ifnull -> 1307
    //   1004: aload_1
    //   1005: aload_1
    //   1006: getfield mView : Landroid/view/View;
    //   1009: putfield mInnerView : Landroid/view/View;
    //   1012: aload_1
    //   1013: getfield mView : Landroid/view/View;
    //   1016: iconst_0
    //   1017: invokevirtual setSaveFromParentEnabled : (Z)V
    //   1020: aload #8
    //   1022: ifnull -> 1034
    //   1025: aload #8
    //   1027: aload_1
    //   1028: getfield mView : Landroid/view/View;
    //   1031: invokevirtual addView : (Landroid/view/View;)V
    //   1034: aload_1
    //   1035: getfield mHidden : Z
    //   1038: ifeq -> 1050
    //   1041: aload_1
    //   1042: getfield mView : Landroid/view/View;
    //   1045: bipush #8
    //   1047: invokevirtual setVisibility : (I)V
    //   1050: aload_1
    //   1051: aload_1
    //   1052: getfield mView : Landroid/view/View;
    //   1055: aload_1
    //   1056: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1059: invokevirtual onViewCreated : (Landroid/view/View;Landroid/os/Bundle;)V
    //   1062: aload_0
    //   1063: aload_1
    //   1064: aload_1
    //   1065: getfield mView : Landroid/view/View;
    //   1068: aload_1
    //   1069: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1072: iconst_0
    //   1073: invokevirtual dispatchOnFragmentViewCreated : (Landroidx/fragment/app/Fragment;Landroid/view/View;Landroid/os/Bundle;Z)V
    //   1076: aload_1
    //   1077: getfield mView : Landroid/view/View;
    //   1080: invokevirtual getVisibility : ()I
    //   1083: ifne -> 1301
    //   1086: aload_1
    //   1087: getfield mContainer : Landroid/view/ViewGroup;
    //   1090: ifnull -> 1301
    //   1093: iconst_1
    //   1094: istore #5
    //   1096: aload_1
    //   1097: iload #5
    //   1099: putfield mIsNewlyAdded : Z
    //   1102: aload_1
    //   1103: aload_1
    //   1104: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1107: invokevirtual performActivityCreated : (Landroid/os/Bundle;)V
    //   1110: aload_0
    //   1111: aload_1
    //   1112: aload_1
    //   1113: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1116: iconst_0
    //   1117: invokevirtual dispatchOnFragmentActivityCreated : (Landroidx/fragment/app/Fragment;Landroid/os/Bundle;Z)V
    //   1120: aload_1
    //   1121: getfield mView : Landroid/view/View;
    //   1124: ifnull -> 1135
    //   1127: aload_1
    //   1128: aload_1
    //   1129: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1132: invokevirtual restoreViewState : (Landroid/os/Bundle;)V
    //   1135: aload_1
    //   1136: aconst_null
    //   1137: putfield mSavedFragmentState : Landroid/os/Bundle;
    //   1140: iload #4
    //   1142: istore #6
    //   1144: iload #6
    //   1146: istore_3
    //   1147: iload #6
    //   1149: iconst_2
    //   1150: if_icmple -> 1198
    //   1153: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   1156: ifeq -> 1185
    //   1159: ldc 'FragmentManager'
    //   1161: new java/lang/StringBuilder
    //   1164: dup
    //   1165: invokespecial <init> : ()V
    //   1168: ldc_w 'moveto STARTED: '
    //   1171: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1174: aload_1
    //   1175: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1178: invokevirtual toString : ()Ljava/lang/String;
    //   1181: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1184: pop
    //   1185: aload_1
    //   1186: invokevirtual performStart : ()V
    //   1189: aload_0
    //   1190: aload_1
    //   1191: iconst_0
    //   1192: invokevirtual dispatchOnFragmentStarted : (Landroidx/fragment/app/Fragment;Z)V
    //   1195: iload #6
    //   1197: istore_3
    //   1198: iload_3
    //   1199: istore #6
    //   1201: iload_3
    //   1202: iconst_3
    //   1203: if_icmple -> 215
    //   1206: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   1209: ifeq -> 1238
    //   1212: ldc 'FragmentManager'
    //   1214: new java/lang/StringBuilder
    //   1217: dup
    //   1218: invokespecial <init> : ()V
    //   1221: ldc_w 'moveto RESUMED: '
    //   1224: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1227: aload_1
    //   1228: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1231: invokevirtual toString : ()Ljava/lang/String;
    //   1234: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1237: pop
    //   1238: aload_1
    //   1239: invokevirtual performResume : ()V
    //   1242: aload_0
    //   1243: aload_1
    //   1244: iconst_0
    //   1245: invokevirtual dispatchOnFragmentResumed : (Landroidx/fragment/app/Fragment;Z)V
    //   1248: aload_1
    //   1249: aconst_null
    //   1250: putfield mSavedFragmentState : Landroid/os/Bundle;
    //   1253: aload_1
    //   1254: aconst_null
    //   1255: putfield mSavedViewState : Landroid/util/SparseArray;
    //   1258: iload_3
    //   1259: istore #6
    //   1261: goto -> 215
    //   1264: aload_1
    //   1265: getfield mParentFragment : Landroidx/fragment/app/Fragment;
    //   1268: aload_1
    //   1269: invokevirtual onAttachFragment : (Landroidx/fragment/app/Fragment;)V
    //   1272: goto -> 700
    //   1275: aload_1
    //   1276: aload_1
    //   1277: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1280: invokevirtual restoreChildFragmentState : (Landroid/os/Bundle;)V
    //   1283: aload_1
    //   1284: iconst_1
    //   1285: putfield mState : I
    //   1288: goto -> 748
    //   1291: astore #8
    //   1293: ldc_w 'unknown'
    //   1296: astore #8
    //   1298: goto -> 911
    //   1301: iconst_0
    //   1302: istore #5
    //   1304: goto -> 1096
    //   1307: aload_1
    //   1308: aconst_null
    //   1309: putfield mInnerView : Landroid/view/View;
    //   1312: goto -> 1102
    //   1315: iload_2
    //   1316: istore #6
    //   1318: aload_1
    //   1319: getfield mState : I
    //   1322: iload_2
    //   1323: if_icmple -> 215
    //   1326: aload_1
    //   1327: getfield mState : I
    //   1330: tableswitch default -> 1360, 1 -> 1366, 2 -> 1523, 3 -> 1476, 4 -> 1429
    //   1360: iload_2
    //   1361: istore #6
    //   1363: goto -> 215
    //   1366: iload_2
    //   1367: istore #6
    //   1369: iload_2
    //   1370: iconst_1
    //   1371: if_icmpge -> 215
    //   1374: aload_0
    //   1375: getfield mDestroyed : Z
    //   1378: ifeq -> 1404
    //   1381: aload_1
    //   1382: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   1385: ifnull -> 1760
    //   1388: aload_1
    //   1389: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   1392: astore #8
    //   1394: aload_1
    //   1395: aconst_null
    //   1396: invokevirtual setAnimatingAway : (Landroid/view/View;)V
    //   1399: aload #8
    //   1401: invokevirtual clearAnimation : ()V
    //   1404: aload_1
    //   1405: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   1408: ifnonnull -> 1418
    //   1411: aload_1
    //   1412: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   1415: ifnull -> 1786
    //   1418: aload_1
    //   1419: iload_2
    //   1420: invokevirtual setStateAfterAnimating : (I)V
    //   1423: iconst_1
    //   1424: istore #6
    //   1426: goto -> 215
    //   1429: iload_2
    //   1430: iconst_4
    //   1431: if_icmpge -> 1476
    //   1434: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   1437: ifeq -> 1466
    //   1440: ldc 'FragmentManager'
    //   1442: new java/lang/StringBuilder
    //   1445: dup
    //   1446: invokespecial <init> : ()V
    //   1449: ldc_w 'movefrom RESUMED: '
    //   1452: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1455: aload_1
    //   1456: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1459: invokevirtual toString : ()Ljava/lang/String;
    //   1462: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1465: pop
    //   1466: aload_1
    //   1467: invokevirtual performPause : ()V
    //   1470: aload_0
    //   1471: aload_1
    //   1472: iconst_0
    //   1473: invokevirtual dispatchOnFragmentPaused : (Landroidx/fragment/app/Fragment;Z)V
    //   1476: iload_2
    //   1477: iconst_3
    //   1478: if_icmpge -> 1523
    //   1481: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   1484: ifeq -> 1513
    //   1487: ldc 'FragmentManager'
    //   1489: new java/lang/StringBuilder
    //   1492: dup
    //   1493: invokespecial <init> : ()V
    //   1496: ldc_w 'movefrom STARTED: '
    //   1499: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1502: aload_1
    //   1503: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1506: invokevirtual toString : ()Ljava/lang/String;
    //   1509: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1512: pop
    //   1513: aload_1
    //   1514: invokevirtual performStop : ()V
    //   1517: aload_0
    //   1518: aload_1
    //   1519: iconst_0
    //   1520: invokevirtual dispatchOnFragmentStopped : (Landroidx/fragment/app/Fragment;Z)V
    //   1523: iload_2
    //   1524: iconst_2
    //   1525: if_icmpge -> 1366
    //   1528: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   1531: ifeq -> 1560
    //   1534: ldc 'FragmentManager'
    //   1536: new java/lang/StringBuilder
    //   1539: dup
    //   1540: invokespecial <init> : ()V
    //   1543: ldc_w 'movefrom ACTIVITY_CREATED: '
    //   1546: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1549: aload_1
    //   1550: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1553: invokevirtual toString : ()Ljava/lang/String;
    //   1556: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1559: pop
    //   1560: aload_1
    //   1561: getfield mView : Landroid/view/View;
    //   1564: ifnull -> 1590
    //   1567: aload_0
    //   1568: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   1571: aload_1
    //   1572: invokevirtual onShouldSaveFragmentState : (Landroidx/fragment/app/Fragment;)Z
    //   1575: ifeq -> 1590
    //   1578: aload_1
    //   1579: getfield mSavedViewState : Landroid/util/SparseArray;
    //   1582: ifnonnull -> 1590
    //   1585: aload_0
    //   1586: aload_1
    //   1587: invokevirtual saveFragmentViewState : (Landroidx/fragment/app/Fragment;)V
    //   1590: aload_1
    //   1591: invokevirtual performDestroyView : ()V
    //   1594: aload_0
    //   1595: aload_1
    //   1596: iconst_0
    //   1597: invokevirtual dispatchOnFragmentViewDestroyed : (Landroidx/fragment/app/Fragment;Z)V
    //   1600: aload_1
    //   1601: getfield mView : Landroid/view/View;
    //   1604: ifnull -> 1724
    //   1607: aload_1
    //   1608: getfield mContainer : Landroid/view/ViewGroup;
    //   1611: ifnull -> 1724
    //   1614: aload_1
    //   1615: getfield mContainer : Landroid/view/ViewGroup;
    //   1618: aload_1
    //   1619: getfield mView : Landroid/view/View;
    //   1622: invokevirtual endViewTransition : (Landroid/view/View;)V
    //   1625: aload_1
    //   1626: getfield mView : Landroid/view/View;
    //   1629: invokevirtual clearAnimation : ()V
    //   1632: aconst_null
    //   1633: astore #9
    //   1635: aload #9
    //   1637: astore #8
    //   1639: aload_0
    //   1640: getfield mCurState : I
    //   1643: ifle -> 1695
    //   1646: aload #9
    //   1648: astore #8
    //   1650: aload_0
    //   1651: getfield mDestroyed : Z
    //   1654: ifne -> 1695
    //   1657: aload #9
    //   1659: astore #8
    //   1661: aload_1
    //   1662: getfield mView : Landroid/view/View;
    //   1665: invokevirtual getVisibility : ()I
    //   1668: ifne -> 1695
    //   1671: aload #9
    //   1673: astore #8
    //   1675: aload_1
    //   1676: getfield mPostponedAlpha : F
    //   1679: fconst_0
    //   1680: fcmpl
    //   1681: iflt -> 1695
    //   1684: aload_0
    //   1685: aload_1
    //   1686: iload_3
    //   1687: iconst_0
    //   1688: iload #4
    //   1690: invokevirtual loadAnimation : (Landroidx/fragment/app/Fragment;IZI)Landroidx/fragment/app/FragmentManagerImpl$AnimationOrAnimator;
    //   1693: astore #8
    //   1695: aload_1
    //   1696: fconst_0
    //   1697: putfield mPostponedAlpha : F
    //   1700: aload #8
    //   1702: ifnull -> 1713
    //   1705: aload_0
    //   1706: aload_1
    //   1707: aload #8
    //   1709: iload_2
    //   1710: invokespecial animateRemoveFragment : (Landroidx/fragment/app/Fragment;Landroidx/fragment/app/FragmentManagerImpl$AnimationOrAnimator;I)V
    //   1713: aload_1
    //   1714: getfield mContainer : Landroid/view/ViewGroup;
    //   1717: aload_1
    //   1718: getfield mView : Landroid/view/View;
    //   1721: invokevirtual removeView : (Landroid/view/View;)V
    //   1724: aload_1
    //   1725: aconst_null
    //   1726: putfield mContainer : Landroid/view/ViewGroup;
    //   1729: aload_1
    //   1730: aconst_null
    //   1731: putfield mView : Landroid/view/View;
    //   1734: aload_1
    //   1735: aconst_null
    //   1736: putfield mViewLifecycleOwner : Landroidx/lifecycle/LifecycleOwner;
    //   1739: aload_1
    //   1740: getfield mViewLifecycleOwnerLiveData : Landroidx/lifecycle/MutableLiveData;
    //   1743: aconst_null
    //   1744: invokevirtual setValue : (Ljava/lang/Object;)V
    //   1747: aload_1
    //   1748: aconst_null
    //   1749: putfield mInnerView : Landroid/view/View;
    //   1752: aload_1
    //   1753: iconst_0
    //   1754: putfield mInLayout : Z
    //   1757: goto -> 1366
    //   1760: aload_1
    //   1761: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   1764: ifnull -> 1404
    //   1767: aload_1
    //   1768: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   1771: astore #8
    //   1773: aload_1
    //   1774: aconst_null
    //   1775: invokevirtual setAnimator : (Landroid/animation/Animator;)V
    //   1778: aload #8
    //   1780: invokevirtual cancel : ()V
    //   1783: goto -> 1404
    //   1786: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   1789: ifeq -> 1818
    //   1792: ldc 'FragmentManager'
    //   1794: new java/lang/StringBuilder
    //   1797: dup
    //   1798: invokespecial <init> : ()V
    //   1801: ldc_w 'movefrom CREATED: '
    //   1804: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1807: aload_1
    //   1808: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1811: invokevirtual toString : ()Ljava/lang/String;
    //   1814: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1817: pop
    //   1818: aload_1
    //   1819: getfield mRetaining : Z
    //   1822: ifne -> 1871
    //   1825: aload_1
    //   1826: invokevirtual performDestroy : ()V
    //   1829: aload_0
    //   1830: aload_1
    //   1831: iconst_0
    //   1832: invokevirtual dispatchOnFragmentDestroyed : (Landroidx/fragment/app/Fragment;Z)V
    //   1835: aload_1
    //   1836: invokevirtual performDetach : ()V
    //   1839: aload_0
    //   1840: aload_1
    //   1841: iconst_0
    //   1842: invokevirtual dispatchOnFragmentDetached : (Landroidx/fragment/app/Fragment;Z)V
    //   1845: iload_2
    //   1846: istore #6
    //   1848: iload #5
    //   1850: ifne -> 215
    //   1853: aload_1
    //   1854: getfield mRetaining : Z
    //   1857: ifne -> 1879
    //   1860: aload_0
    //   1861: aload_1
    //   1862: invokevirtual makeInactive : (Landroidx/fragment/app/Fragment;)V
    //   1865: iload_2
    //   1866: istore #6
    //   1868: goto -> 215
    //   1871: aload_1
    //   1872: iconst_0
    //   1873: putfield mState : I
    //   1876: goto -> 1835
    //   1879: aload_1
    //   1880: aconst_null
    //   1881: putfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   1884: aload_1
    //   1885: aconst_null
    //   1886: putfield mParentFragment : Landroidx/fragment/app/Fragment;
    //   1889: aload_1
    //   1890: aconst_null
    //   1891: putfield mFragmentManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   1894: iload_2
    //   1895: istore #6
    //   1897: goto -> 215
    // Exception table:
    //   from	to	target	type
    //   898	911	1291	android/content/res/Resources$NotFoundException
  }
  
  public void noteStateNotSaved() {
    this.mSavedNonConfig = null;
    this.mStateSaved = false;
    this.mStopped = false;
    int j = this.mAdded.size();
    for (int i = 0; i < j; i++) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.noteStateNotSaved(); 
    } 
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    Fragment fragment1;
    boolean bool;
    if (!"fragment".equals(paramString))
      return null; 
    paramString = paramAttributeSet.getAttributeValue(null, "class");
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, FragmentTag.Fragment);
    String str2 = paramString;
    if (paramString == null)
      str2 = typedArray.getString(0); 
    int i = typedArray.getResourceId(1, -1);
    String str3 = typedArray.getString(2);
    typedArray.recycle();
    if (!Fragment.isSupportFragmentClass(this.mHost.getContext(), str2))
      return null; 
    if (paramView != null) {
      bool = paramView.getId();
    } else {
      bool = false;
    } 
    if (bool == -1 && i == -1 && str3 == null)
      throw new IllegalArgumentException(paramAttributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2); 
    if (i != -1) {
      Fragment fragment = findFragmentById(i);
    } else {
      paramString = null;
    } 
    String str1 = paramString;
    if (paramString == null) {
      str1 = paramString;
      if (str3 != null)
        fragment1 = findFragmentByTag(str3); 
    } 
    Fragment fragment2 = fragment1;
    if (fragment1 == null) {
      fragment2 = fragment1;
      if (bool != -1)
        fragment2 = findFragmentById(bool); 
    } 
    if (DEBUG)
      Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(i) + " fname=" + str2 + " existing=" + fragment2); 
    if (fragment2 == null) {
      boolean bool1;
      fragment1 = this.mContainer.instantiate(paramContext, str2, null);
      fragment1.mFromLayout = true;
      if (i != 0) {
        bool1 = i;
      } else {
        bool1 = bool;
      } 
      fragment1.mFragmentId = bool1;
      fragment1.mContainerId = bool;
      fragment1.mTag = str3;
      fragment1.mInLayout = true;
      fragment1.mFragmentManager = this;
      fragment1.mHost = this.mHost;
      fragment1.onInflate(this.mHost.getContext(), paramAttributeSet, fragment1.mSavedFragmentState);
      addFragment(fragment1, true);
    } else {
      if (fragment2.mInLayout)
        throw new IllegalArgumentException(paramAttributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(i) + ", tag " + str3 + ", or parent id 0x" + Integer.toHexString(bool) + " with another fragment for " + str2); 
      fragment2.mInLayout = true;
      fragment2.mHost = this.mHost;
      fragment1 = fragment2;
      if (!fragment2.mRetaining) {
        fragment2.onInflate(this.mHost.getContext(), paramAttributeSet, fragment2.mSavedFragmentState);
        fragment1 = fragment2;
      } 
    } 
    if (this.mCurState < 1 && fragment1.mFromLayout) {
      moveToState(fragment1, 1, 0, 0, false);
    } else {
      moveToState(fragment1);
    } 
    if (fragment1.mView == null)
      throw new IllegalStateException("Fragment " + str2 + " did not create a view."); 
    if (i != 0)
      fragment1.mView.setId(i); 
    if (fragment1.mView.getTag() == null)
      fragment1.mView.setTag(str3); 
    return fragment1.mView;
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return onCreateView(null, paramString, paramContext, paramAttributeSet);
  }
  
  public void performPendingDeferredStart(Fragment paramFragment) {
    if (paramFragment.mDeferStart) {
      if (this.mExecutingActions) {
        this.mHavePendingDeferredStart = true;
        return;
      } 
    } else {
      return;
    } 
    paramFragment.mDeferStart = false;
    moveToState(paramFragment, this.mCurState, 0, 0, false);
  }
  
  public void popBackStack() {
    enqueueAction(new PopBackStackState(null, -1, 0), false);
  }
  
  public void popBackStack(int paramInt1, int paramInt2) {
    if (paramInt1 < 0)
      throw new IllegalArgumentException("Bad id: " + paramInt1); 
    enqueueAction(new PopBackStackState(null, paramInt1, paramInt2), false);
  }
  
  public void popBackStack(@Nullable String paramString, int paramInt) {
    enqueueAction(new PopBackStackState(paramString, -1, paramInt), false);
  }
  
  public boolean popBackStackImmediate() {
    checkStateLoss();
    return popBackStackImmediate(null, -1, 0);
  }
  
  public boolean popBackStackImmediate(int paramInt1, int paramInt2) {
    checkStateLoss();
    execPendingActions();
    if (paramInt1 < 0)
      throw new IllegalArgumentException("Bad id: " + paramInt1); 
    return popBackStackImmediate(null, paramInt1, paramInt2);
  }
  
  public boolean popBackStackImmediate(@Nullable String paramString, int paramInt) {
    checkStateLoss();
    return popBackStackImmediate(paramString, -1, paramInt);
  }
  
  boolean popBackStackState(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, String paramString, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mBackStack : Ljava/util/ArrayList;
    //   4: ifnonnull -> 9
    //   7: iconst_0
    //   8: ireturn
    //   9: aload_3
    //   10: ifnonnull -> 66
    //   13: iload #4
    //   15: ifge -> 66
    //   18: iload #5
    //   20: iconst_1
    //   21: iand
    //   22: ifne -> 66
    //   25: aload_0
    //   26: getfield mBackStack : Ljava/util/ArrayList;
    //   29: invokevirtual size : ()I
    //   32: iconst_1
    //   33: isub
    //   34: istore #4
    //   36: iload #4
    //   38: iflt -> 7
    //   41: aload_1
    //   42: aload_0
    //   43: getfield mBackStack : Ljava/util/ArrayList;
    //   46: iload #4
    //   48: invokevirtual remove : (I)Ljava/lang/Object;
    //   51: invokevirtual add : (Ljava/lang/Object;)Z
    //   54: pop
    //   55: aload_2
    //   56: iconst_1
    //   57: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   60: invokevirtual add : (Ljava/lang/Object;)Z
    //   63: pop
    //   64: iconst_1
    //   65: ireturn
    //   66: iconst_m1
    //   67: istore #6
    //   69: aload_3
    //   70: ifnonnull -> 78
    //   73: iload #4
    //   75: iflt -> 241
    //   78: aload_0
    //   79: getfield mBackStack : Ljava/util/ArrayList;
    //   82: invokevirtual size : ()I
    //   85: iconst_1
    //   86: isub
    //   87: istore #7
    //   89: iload #7
    //   91: iflt -> 124
    //   94: aload_0
    //   95: getfield mBackStack : Ljava/util/ArrayList;
    //   98: iload #7
    //   100: invokevirtual get : (I)Ljava/lang/Object;
    //   103: checkcast androidx/fragment/app/BackStackRecord
    //   106: astore #8
    //   108: aload_3
    //   109: ifnull -> 217
    //   112: aload_3
    //   113: aload #8
    //   115: invokevirtual getName : ()Ljava/lang/String;
    //   118: invokevirtual equals : (Ljava/lang/Object;)Z
    //   121: ifeq -> 217
    //   124: iload #7
    //   126: iflt -> 7
    //   129: iload #7
    //   131: istore #6
    //   133: iload #5
    //   135: iconst_1
    //   136: iand
    //   137: ifeq -> 241
    //   140: iload #7
    //   142: iconst_1
    //   143: isub
    //   144: istore #5
    //   146: iload #5
    //   148: istore #6
    //   150: iload #5
    //   152: iflt -> 241
    //   155: aload_0
    //   156: getfield mBackStack : Ljava/util/ArrayList;
    //   159: iload #5
    //   161: invokevirtual get : (I)Ljava/lang/Object;
    //   164: checkcast androidx/fragment/app/BackStackRecord
    //   167: astore #8
    //   169: aload_3
    //   170: ifnull -> 185
    //   173: aload_3
    //   174: aload #8
    //   176: invokevirtual getName : ()Ljava/lang/String;
    //   179: invokevirtual equals : (Ljava/lang/Object;)Z
    //   182: ifne -> 208
    //   185: iload #5
    //   187: istore #6
    //   189: iload #4
    //   191: iflt -> 241
    //   194: iload #5
    //   196: istore #6
    //   198: iload #4
    //   200: aload #8
    //   202: getfield mIndex : I
    //   205: if_icmpne -> 241
    //   208: iload #5
    //   210: iconst_1
    //   211: isub
    //   212: istore #5
    //   214: goto -> 146
    //   217: iload #4
    //   219: iflt -> 232
    //   222: iload #4
    //   224: aload #8
    //   226: getfield mIndex : I
    //   229: if_icmpeq -> 124
    //   232: iload #7
    //   234: iconst_1
    //   235: isub
    //   236: istore #7
    //   238: goto -> 89
    //   241: iload #6
    //   243: aload_0
    //   244: getfield mBackStack : Ljava/util/ArrayList;
    //   247: invokevirtual size : ()I
    //   250: iconst_1
    //   251: isub
    //   252: if_icmpeq -> 7
    //   255: aload_0
    //   256: getfield mBackStack : Ljava/util/ArrayList;
    //   259: invokevirtual size : ()I
    //   262: iconst_1
    //   263: isub
    //   264: istore #4
    //   266: iload #4
    //   268: iload #6
    //   270: if_icmple -> 64
    //   273: aload_1
    //   274: aload_0
    //   275: getfield mBackStack : Ljava/util/ArrayList;
    //   278: iload #4
    //   280: invokevirtual remove : (I)Ljava/lang/Object;
    //   283: invokevirtual add : (Ljava/lang/Object;)Z
    //   286: pop
    //   287: aload_2
    //   288: iconst_1
    //   289: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   292: invokevirtual add : (Ljava/lang/Object;)Z
    //   295: pop
    //   296: iload #4
    //   298: iconst_1
    //   299: isub
    //   300: istore #4
    //   302: goto -> 266
  }
  
  public void putFragment(Bundle paramBundle, String paramString, Fragment paramFragment) {
    if (paramFragment.mIndex < 0)
      throwException(new IllegalStateException("Fragment " + paramFragment + " is not currently in the FragmentManager")); 
    paramBundle.putInt(paramString, paramFragment.mIndex);
  }
  
  public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks, boolean paramBoolean) {
    this.mLifecycleCallbacks.add(new FragmentLifecycleCallbacksHolder(paramFragmentLifecycleCallbacks, paramBoolean));
  }
  
  public void removeFragment(Fragment paramFragment) {
    boolean bool;
    if (DEBUG)
      Log.v("FragmentManager", "remove: " + paramFragment + " nesting=" + paramFragment.mBackStackNesting); 
    if (!paramFragment.isInBackStack()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (!paramFragment.mDetached || bool)
      synchronized (this.mAdded) {
        this.mAdded.remove(paramFragment);
        if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
          this.mNeedMenuInvalidate = true; 
        paramFragment.mAdded = false;
        paramFragment.mRemoving = true;
        return;
      }  
  }
  
  public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener) {
    if (this.mBackStackChangeListeners != null)
      this.mBackStackChangeListeners.remove(paramOnBackStackChangedListener); 
  }
  
  void reportBackStackChanged() {
    if (this.mBackStackChangeListeners != null)
      for (int i = 0; i < this.mBackStackChangeListeners.size(); i++)
        ((FragmentManager.OnBackStackChangedListener)this.mBackStackChangeListeners.get(i)).onBackStackChanged();  
  }
  
  void restoreAllState(Parcelable paramParcelable, FragmentManagerNonConfig paramFragmentManagerNonConfig) {
    if (paramParcelable != null) {
      FragmentManagerState fragmentManagerState = (FragmentManagerState)paramParcelable;
      if (fragmentManagerState.mActive != null) {
        Fragment fragment;
        FragmentState fragmentState;
        paramParcelable = null;
        List<ViewModelStore> list = null;
        if (paramFragmentManagerNonConfig != null) {
          byte b;
          List<Fragment> list3 = paramFragmentManagerNonConfig.getFragments();
          List<FragmentManagerNonConfig> list1 = paramFragmentManagerNonConfig.getChildNonConfigs();
          List<ViewModelStore> list2 = paramFragmentManagerNonConfig.getViewModelStores();
          if (list3 != null) {
            b = list3.size();
          } else {
            b = 0;
          } 
          int j = 0;
          while (true) {
            List<FragmentManagerNonConfig> list4 = list1;
            list = list2;
            if (j < b) {
              fragment = list3.get(j);
              if (DEBUG)
                Log.v("FragmentManager", "restoreAllState: re-attaching retained " + fragment); 
              int k;
              for (k = 0; k < fragmentManagerState.mActive.length && (fragmentManagerState.mActive[k]).mIndex != fragment.mIndex; k++);
              if (k == fragmentManagerState.mActive.length)
                throwException(new IllegalStateException("Could not find active fragment with index " + fragment.mIndex)); 
              fragmentState = fragmentManagerState.mActive[k];
              fragmentState.mInstance = fragment;
              fragment.mSavedViewState = null;
              fragment.mBackStackNesting = 0;
              fragment.mInLayout = false;
              fragment.mAdded = false;
              fragment.mTarget = null;
              if (fragmentState.mSavedFragmentState != null) {
                fragmentState.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
                fragment.mSavedViewState = fragmentState.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                fragment.mSavedFragmentState = fragmentState.mSavedFragmentState;
              } 
              j++;
              continue;
            } 
            break;
          } 
        } 
        this.mActive = new SparseArray(fragmentManagerState.mActive.length);
        int i;
        for (i = 0; i < fragmentManagerState.mActive.length; i++) {
          FragmentState fragmentState1 = fragmentManagerState.mActive[i];
          if (fragmentState1 != null) {
            ViewModelStore viewModelStore;
            FragmentManagerNonConfig fragmentManagerNonConfig2 = null;
            FragmentManagerNonConfig fragmentManagerNonConfig1 = fragmentManagerNonConfig2;
            if (fragment != null) {
              fragmentManagerNonConfig1 = fragmentManagerNonConfig2;
              if (i < fragment.size())
                fragmentManagerNonConfig1 = fragment.get(i); 
            } 
            FragmentManagerNonConfig fragmentManagerNonConfig3 = null;
            fragmentManagerNonConfig2 = fragmentManagerNonConfig3;
            if (fragmentState != null) {
              fragmentManagerNonConfig2 = fragmentManagerNonConfig3;
              if (i < fragmentState.size())
                viewModelStore = fragmentState.get(i); 
            } 
            Fragment fragment1 = fragmentState1.instantiate(this.mHost, this.mContainer, this.mParent, fragmentManagerNonConfig1, viewModelStore);
            if (DEBUG)
              Log.v("FragmentManager", "restoreAllState: active #" + i + ": " + fragment1); 
            this.mActive.put(fragment1.mIndex, fragment1);
            fragmentState1.mInstance = null;
          } 
        } 
        if (paramFragmentManagerNonConfig != null) {
          List<Fragment> list1 = paramFragmentManagerNonConfig.getFragments();
          if (list1 != null) {
            i = list1.size();
          } else {
            i = 0;
          } 
          int j;
          for (j = 0; j < i; j++) {
            Fragment fragment1 = list1.get(j);
            if (fragment1.mTargetIndex >= 0) {
              fragment1.mTarget = (Fragment)this.mActive.get(fragment1.mTargetIndex);
              if (fragment1.mTarget == null)
                Log.w("FragmentManager", "Re-attaching retained fragment " + fragment1 + " target no longer exists: " + fragment1.mTargetIndex); 
            } 
          } 
        } 
        this.mAdded.clear();
        if (fragmentManagerState.mAdded != null) {
          i = 0;
          while (i < fragmentManagerState.mAdded.length) {
            null = (Fragment)this.mActive.get(fragmentManagerState.mAdded[i]);
            if (null == null)
              throwException(new IllegalStateException("No instantiated fragment for index #" + fragmentManagerState.mAdded[i])); 
            null.mAdded = true;
            if (DEBUG)
              Log.v("FragmentManager", "restoreAllState: added #" + i + ": " + null); 
            if (this.mAdded.contains(null))
              throw new IllegalStateException("Already added!"); 
            synchronized (this.mAdded) {
              this.mAdded.add(null);
              i++;
            } 
          } 
        } 
        if (fragmentManagerState.mBackStack != null) {
          this.mBackStack = new ArrayList<BackStackRecord>(fragmentManagerState.mBackStack.length);
          for (i = 0; i < fragmentManagerState.mBackStack.length; i++) {
            BackStackRecord backStackRecord = fragmentManagerState.mBackStack[i].instantiate(this);
            if (DEBUG) {
              Log.v("FragmentManager", "restoreAllState: back stack #" + i + " (index " + backStackRecord.mIndex + "): " + backStackRecord);
              PrintWriter printWriter = new PrintWriter((Writer)new LogWriter("FragmentManager"));
              backStackRecord.dump("  ", printWriter, false);
              printWriter.close();
            } 
            this.mBackStack.add(backStackRecord);
            if (backStackRecord.mIndex >= 0)
              setBackStackIndex(backStackRecord.mIndex, backStackRecord); 
          } 
        } else {
          this.mBackStack = null;
        } 
        if (fragmentManagerState.mPrimaryNavActiveIndex >= 0)
          this.mPrimaryNav = (Fragment)this.mActive.get(fragmentManagerState.mPrimaryNavActiveIndex); 
        this.mNextFragmentIndex = fragmentManagerState.mNextFragmentIndex;
        return;
      } 
    } 
  }
  
  FragmentManagerNonConfig retainNonConfig() {
    setRetaining(this.mSavedNonConfig);
    return this.mSavedNonConfig;
  }
  
  Parcelable saveAllState() {
    forcePostponedTransactions();
    endAnimatingAwayFragments();
    execPendingActions();
    this.mStateSaved = true;
    this.mSavedNonConfig = null;
    if (this.mActive != null && this.mActive.size() > 0) {
      int k = this.mActive.size();
      FragmentState[] arrayOfFragmentState = new FragmentState[k];
      int j = 0;
      int i;
      for (i = 0; i < k; i++) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null) {
          if (fragment.mIndex < 0)
            throwException(new IllegalStateException("Failure saving state: active " + fragment + " has cleared index: " + fragment.mIndex)); 
          byte b = 1;
          FragmentState fragmentState = new FragmentState(fragment);
          arrayOfFragmentState[i] = fragmentState;
          if (fragment.mState > 0 && fragmentState.mSavedFragmentState == null) {
            fragmentState.mSavedFragmentState = saveFragmentBasicState(fragment);
            if (fragment.mTarget != null) {
              if (fragment.mTarget.mIndex < 0)
                throwException(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.mTarget)); 
              if (fragmentState.mSavedFragmentState == null)
                fragmentState.mSavedFragmentState = new Bundle(); 
              putFragment(fragmentState.mSavedFragmentState, "android:target_state", fragment.mTarget);
              if (fragment.mTargetRequestCode != 0)
                fragmentState.mSavedFragmentState.putInt("android:target_req_state", fragment.mTargetRequestCode); 
            } 
          } else {
            fragmentState.mSavedFragmentState = fragment.mSavedFragmentState;
          } 
          j = b;
          if (DEBUG) {
            Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.mSavedFragmentState);
            j = b;
          } 
        } 
      } 
      if (!j) {
        if (DEBUG) {
          Log.v("FragmentManager", "saveAllState: no fragments!");
          return null;
        } 
        return null;
      } 
      int[] arrayOfInt = null;
      BackStackState[] arrayOfBackStackState2 = null;
      j = this.mAdded.size();
      if (j > 0) {
        int[] arrayOfInt1 = new int[j];
        i = 0;
        while (true) {
          arrayOfInt = arrayOfInt1;
          if (i < j) {
            arrayOfInt1[i] = ((Fragment)this.mAdded.get(i)).mIndex;
            if (arrayOfInt1[i] < 0)
              throwException(new IllegalStateException("Failure saving state: active " + this.mAdded.get(i) + " has cleared index: " + arrayOfInt1[i])); 
            if (DEBUG)
              Log.v("FragmentManager", "saveAllState: adding fragment #" + i + ": " + this.mAdded.get(i)); 
            i++;
            continue;
          } 
          break;
        } 
      } 
      BackStackState[] arrayOfBackStackState1 = arrayOfBackStackState2;
      if (this.mBackStack != null) {
        j = this.mBackStack.size();
        arrayOfBackStackState1 = arrayOfBackStackState2;
        if (j > 0) {
          arrayOfBackStackState2 = new BackStackState[j];
          i = 0;
          while (true) {
            arrayOfBackStackState1 = arrayOfBackStackState2;
            if (i < j) {
              arrayOfBackStackState2[i] = new BackStackState(this.mBackStack.get(i));
              if (DEBUG)
                Log.v("FragmentManager", "saveAllState: adding back stack #" + i + ": " + this.mBackStack.get(i)); 
              i++;
              continue;
            } 
            break;
          } 
        } 
      } 
      FragmentManagerState fragmentManagerState = new FragmentManagerState();
      fragmentManagerState.mActive = arrayOfFragmentState;
      fragmentManagerState.mAdded = arrayOfInt;
      fragmentManagerState.mBackStack = arrayOfBackStackState1;
      if (this.mPrimaryNav != null)
        fragmentManagerState.mPrimaryNavActiveIndex = this.mPrimaryNav.mIndex; 
      fragmentManagerState.mNextFragmentIndex = this.mNextFragmentIndex;
      saveNonConfig();
      return fragmentManagerState;
    } 
    return null;
  }
  
  Bundle saveFragmentBasicState(Fragment paramFragment) {
    Bundle bundle2 = null;
    if (this.mStateBundle == null)
      this.mStateBundle = new Bundle(); 
    paramFragment.performSaveInstanceState(this.mStateBundle);
    dispatchOnFragmentSaveInstanceState(paramFragment, this.mStateBundle, false);
    if (!this.mStateBundle.isEmpty()) {
      bundle2 = this.mStateBundle;
      this.mStateBundle = null;
    } 
    if (paramFragment.mView != null)
      saveFragmentViewState(paramFragment); 
    Bundle bundle1 = bundle2;
    if (paramFragment.mSavedViewState != null) {
      bundle1 = bundle2;
      if (bundle2 == null)
        bundle1 = new Bundle(); 
      bundle1.putSparseParcelableArray("android:view_state", paramFragment.mSavedViewState);
    } 
    bundle2 = bundle1;
    if (!paramFragment.mUserVisibleHint) {
      bundle2 = bundle1;
      if (bundle1 == null)
        bundle2 = new Bundle(); 
      bundle2.putBoolean("android:user_visible_hint", paramFragment.mUserVisibleHint);
    } 
    return bundle2;
  }
  
  @Nullable
  public Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment) {
    Fragment.SavedState savedState2 = null;
    if (paramFragment.mIndex < 0)
      throwException(new IllegalStateException("Fragment " + paramFragment + " is not currently in the FragmentManager")); 
    Fragment.SavedState savedState1 = savedState2;
    if (paramFragment.mState > 0) {
      Bundle bundle = saveFragmentBasicState(paramFragment);
      savedState1 = savedState2;
      if (bundle != null)
        savedState1 = new Fragment.SavedState(bundle); 
    } 
    return savedState1;
  }
  
  void saveFragmentViewState(Fragment paramFragment) {
    if (paramFragment.mInnerView != null) {
      if (this.mStateArray == null) {
        this.mStateArray = new SparseArray();
      } else {
        this.mStateArray.clear();
      } 
      paramFragment.mInnerView.saveHierarchyState(this.mStateArray);
      if (this.mStateArray.size() > 0) {
        paramFragment.mSavedViewState = this.mStateArray;
        this.mStateArray = null;
        return;
      } 
    } 
  }
  
  void saveNonConfig() {
    ArrayList<Fragment> arrayList5 = null;
    ArrayList<Fragment> arrayList1 = null;
    ArrayList<Fragment> arrayList4 = null;
    ArrayList<Fragment> arrayList3 = null;
    ArrayList<Fragment> arrayList6 = null;
    ArrayList<Fragment> arrayList2 = null;
    if (this.mActive != null) {
      int i = 0;
      while (true) {
        arrayList4 = arrayList3;
        arrayList5 = arrayList1;
        arrayList6 = arrayList2;
        if (i < this.mActive.size()) {
          Fragment fragment = (Fragment)this.mActive.valueAt(i);
          arrayList5 = arrayList3;
          arrayList6 = arrayList1;
          ArrayList<Fragment> arrayList = arrayList2;
          if (fragment != null) {
            FragmentManagerNonConfig fragmentManagerNonConfig;
            arrayList4 = arrayList1;
            if (fragment.mRetainInstance) {
              byte b;
              arrayList5 = arrayList1;
              if (arrayList1 == null)
                arrayList5 = new ArrayList(); 
              arrayList5.add(fragment);
              if (fragment.mTarget != null) {
                b = fragment.mTarget.mIndex;
              } else {
                b = -1;
              } 
              fragment.mTargetIndex = b;
              arrayList4 = arrayList5;
              if (DEBUG) {
                Log.v("FragmentManager", "retainNonConfig: keeping retained " + fragment);
                arrayList4 = arrayList5;
              } 
            } 
            if (fragment.mChildFragmentManager != null) {
              fragment.mChildFragmentManager.saveNonConfig();
              fragmentManagerNonConfig = fragment.mChildFragmentManager.mSavedNonConfig;
            } else {
              fragmentManagerNonConfig = fragment.mChildNonConfig;
            } 
            arrayList1 = arrayList3;
            if (arrayList3 == null) {
              arrayList1 = arrayList3;
              if (fragmentManagerNonConfig != null) {
                arrayList3 = new ArrayList<Fragment>(this.mActive.size());
                int j = 0;
                while (true) {
                  arrayList1 = arrayList3;
                  if (j < i) {
                    arrayList3.add(null);
                    j++;
                    continue;
                  } 
                  break;
                } 
              } 
            } 
            if (arrayList1 != null)
              arrayList1.add(fragmentManagerNonConfig); 
            arrayList3 = arrayList2;
            if (arrayList2 == null) {
              arrayList3 = arrayList2;
              if (fragment.mViewModelStore != null) {
                arrayList2 = new ArrayList<Fragment>(this.mActive.size());
                int j = 0;
                while (true) {
                  arrayList3 = arrayList2;
                  if (j < i) {
                    arrayList2.add(null);
                    j++;
                    continue;
                  } 
                  break;
                } 
              } 
            } 
            arrayList5 = arrayList1;
            arrayList6 = arrayList4;
            arrayList = arrayList3;
            if (arrayList3 != null) {
              arrayList3.add(fragment.mViewModelStore);
              arrayList = arrayList3;
              arrayList6 = arrayList4;
              arrayList5 = arrayList1;
            } 
          } 
          i++;
          arrayList3 = arrayList5;
          arrayList1 = arrayList6;
          arrayList2 = arrayList;
          continue;
        } 
        break;
      } 
    } 
    if (arrayList5 == null && arrayList4 == null && arrayList6 == null) {
      this.mSavedNonConfig = null;
      return;
    } 
    this.mSavedNonConfig = new FragmentManagerNonConfig(arrayList5, (List)arrayList4, (List)arrayList6);
  }
  
  void scheduleCommit() {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   8: ifnull -> 92
    //   11: aload_0
    //   12: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   15: invokevirtual isEmpty : ()Z
    //   18: ifne -> 92
    //   21: iconst_1
    //   22: istore_1
    //   23: aload_0
    //   24: getfield mPendingActions : Ljava/util/ArrayList;
    //   27: ifnull -> 97
    //   30: aload_0
    //   31: getfield mPendingActions : Ljava/util/ArrayList;
    //   34: invokevirtual size : ()I
    //   37: iconst_1
    //   38: if_icmpne -> 97
    //   41: goto -> 81
    //   44: aload_0
    //   45: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   48: invokevirtual getHandler : ()Landroid/os/Handler;
    //   51: aload_0
    //   52: getfield mExecCommit : Ljava/lang/Runnable;
    //   55: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
    //   58: aload_0
    //   59: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   62: invokevirtual getHandler : ()Landroid/os/Handler;
    //   65: aload_0
    //   66: getfield mExecCommit : Ljava/lang/Runnable;
    //   69: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   72: pop
    //   73: aload_0
    //   74: monitorexit
    //   75: return
    //   76: astore_3
    //   77: aload_0
    //   78: monitorexit
    //   79: aload_3
    //   80: athrow
    //   81: iload_1
    //   82: ifne -> 44
    //   85: iload_2
    //   86: ifeq -> 73
    //   89: goto -> 44
    //   92: iconst_0
    //   93: istore_1
    //   94: goto -> 23
    //   97: iconst_0
    //   98: istore_2
    //   99: goto -> 81
    // Exception table:
    //   from	to	target	type
    //   4	21	76	finally
    //   23	41	76	finally
    //   44	73	76	finally
    //   73	75	76	finally
    //   77	79	76	finally
  }
  
  public void setBackStackIndex(int paramInt, BackStackRecord paramBackStackRecord) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   6: ifnonnull -> 20
    //   9: aload_0
    //   10: new java/util/ArrayList
    //   13: dup
    //   14: invokespecial <init> : ()V
    //   17: putfield mBackStackIndices : Ljava/util/ArrayList;
    //   20: aload_0
    //   21: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   24: invokevirtual size : ()I
    //   27: istore #4
    //   29: iload #4
    //   31: istore_3
    //   32: iload_1
    //   33: iload #4
    //   35: if_icmpge -> 93
    //   38: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   41: ifeq -> 80
    //   44: ldc 'FragmentManager'
    //   46: new java/lang/StringBuilder
    //   49: dup
    //   50: invokespecial <init> : ()V
    //   53: ldc_w 'Setting back stack index '
    //   56: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: iload_1
    //   60: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   63: ldc_w ' to '
    //   66: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: aload_2
    //   70: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   73: invokevirtual toString : ()Ljava/lang/String;
    //   76: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   79: pop
    //   80: aload_0
    //   81: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   84: iload_1
    //   85: aload_2
    //   86: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   89: pop
    //   90: aload_0
    //   91: monitorexit
    //   92: return
    //   93: iload_3
    //   94: iload_1
    //   95: if_icmpge -> 176
    //   98: aload_0
    //   99: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   102: aconst_null
    //   103: invokevirtual add : (Ljava/lang/Object;)Z
    //   106: pop
    //   107: aload_0
    //   108: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   111: ifnonnull -> 125
    //   114: aload_0
    //   115: new java/util/ArrayList
    //   118: dup
    //   119: invokespecial <init> : ()V
    //   122: putfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   125: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   128: ifeq -> 157
    //   131: ldc 'FragmentManager'
    //   133: new java/lang/StringBuilder
    //   136: dup
    //   137: invokespecial <init> : ()V
    //   140: ldc_w 'Adding available back stack index '
    //   143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: iload_3
    //   147: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   150: invokevirtual toString : ()Ljava/lang/String;
    //   153: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   156: pop
    //   157: aload_0
    //   158: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   161: iload_3
    //   162: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   165: invokevirtual add : (Ljava/lang/Object;)Z
    //   168: pop
    //   169: iload_3
    //   170: iconst_1
    //   171: iadd
    //   172: istore_3
    //   173: goto -> 93
    //   176: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   179: ifeq -> 218
    //   182: ldc 'FragmentManager'
    //   184: new java/lang/StringBuilder
    //   187: dup
    //   188: invokespecial <init> : ()V
    //   191: ldc_w 'Adding back stack index '
    //   194: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: iload_1
    //   198: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   201: ldc_w ' with '
    //   204: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: aload_2
    //   208: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   211: invokevirtual toString : ()Ljava/lang/String;
    //   214: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   217: pop
    //   218: aload_0
    //   219: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   222: aload_2
    //   223: invokevirtual add : (Ljava/lang/Object;)Z
    //   226: pop
    //   227: goto -> 90
    //   230: astore_2
    //   231: aload_0
    //   232: monitorexit
    //   233: aload_2
    //   234: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	230	finally
    //   20	29	230	finally
    //   38	80	230	finally
    //   80	90	230	finally
    //   90	92	230	finally
    //   98	125	230	finally
    //   125	157	230	finally
    //   157	169	230	finally
    //   176	218	230	finally
    //   218	227	230	finally
    //   231	233	230	finally
  }
  
  public void setPrimaryNavigationFragment(Fragment paramFragment) {
    if (paramFragment != null && (this.mActive.get(paramFragment.mIndex) != paramFragment || (paramFragment.mHost != null && paramFragment.getFragmentManager() != this)))
      throw new IllegalArgumentException("Fragment " + paramFragment + " is not an active fragment of FragmentManager " + this); 
    this.mPrimaryNav = paramFragment;
  }
  
  public void showFragment(Fragment paramFragment) {
    boolean bool = false;
    if (DEBUG)
      Log.v("FragmentManager", "show: " + paramFragment); 
    if (paramFragment.mHidden) {
      paramFragment.mHidden = false;
      if (!paramFragment.mHiddenChanged)
        bool = true; 
      paramFragment.mHiddenChanged = bool;
    } 
  }
  
  void startPendingDeferredFragments() {
    if (this.mActive != null) {
      int i = 0;
      while (true) {
        if (i < this.mActive.size()) {
          Fragment fragment = (Fragment)this.mActive.valueAt(i);
          if (fragment != null)
            performPendingDeferredStart(fragment); 
          i++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("FragmentManager{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" in ");
    if (this.mParent != null) {
      DebugUtils.buildShortClassTag(this.mParent, stringBuilder);
      stringBuilder.append("}}");
      return stringBuilder.toString();
    } 
    DebugUtils.buildShortClassTag(this.mHost, stringBuilder);
    stringBuilder.append("}}");
    return stringBuilder.toString();
  }
  
  public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   4: astore #4
    //   6: aload #4
    //   8: monitorenter
    //   9: iconst_0
    //   10: istore_2
    //   11: aload_0
    //   12: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   15: invokevirtual size : ()I
    //   18: istore_3
    //   19: iload_2
    //   20: iload_3
    //   21: if_icmpge -> 51
    //   24: aload_0
    //   25: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   28: iload_2
    //   29: invokevirtual get : (I)Ljava/lang/Object;
    //   32: checkcast androidx/fragment/app/FragmentManagerImpl$FragmentLifecycleCallbacksHolder
    //   35: getfield mCallback : Landroidx/fragment/app/FragmentManager$FragmentLifecycleCallbacks;
    //   38: aload_1
    //   39: if_acmpne -> 61
    //   42: aload_0
    //   43: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   46: iload_2
    //   47: invokevirtual remove : (I)Ljava/lang/Object;
    //   50: pop
    //   51: aload #4
    //   53: monitorexit
    //   54: return
    //   55: astore_1
    //   56: aload #4
    //   58: monitorexit
    //   59: aload_1
    //   60: athrow
    //   61: iload_2
    //   62: iconst_1
    //   63: iadd
    //   64: istore_2
    //   65: goto -> 19
    // Exception table:
    //   from	to	target	type
    //   11	19	55	finally
    //   24	51	55	finally
    //   51	54	55	finally
    //   56	59	55	finally
  }
  
  private static class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper {
    View mView;
    
    AnimateOnHWLayerIfNeededListener(View param1View, Animation.AnimationListener param1AnimationListener) {
      super(param1AnimationListener);
      this.mView = param1View;
    }
    
    @CallSuper
    public void onAnimationEnd(Animation param1Animation) {
      if (ViewCompat.isAttachedToWindow(this.mView) || Build.VERSION.SDK_INT >= 24) {
        this.mView.post(new Runnable() {
              public void run() {
                FragmentManagerImpl.AnimateOnHWLayerIfNeededListener.this.mView.setLayerType(0, null);
              }
            });
      } else {
        this.mView.setLayerType(0, null);
      } 
      super.onAnimationEnd(param1Animation);
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$0.mView.setLayerType(0, null);
    }
  }
  
  private static class AnimationListenerWrapper implements Animation.AnimationListener {
    private final Animation.AnimationListener mWrapped;
    
    AnimationListenerWrapper(Animation.AnimationListener param1AnimationListener) {
      this.mWrapped = param1AnimationListener;
    }
    
    @CallSuper
    public void onAnimationEnd(Animation param1Animation) {
      if (this.mWrapped != null)
        this.mWrapped.onAnimationEnd(param1Animation); 
    }
    
    @CallSuper
    public void onAnimationRepeat(Animation param1Animation) {
      if (this.mWrapped != null)
        this.mWrapped.onAnimationRepeat(param1Animation); 
    }
    
    @CallSuper
    public void onAnimationStart(Animation param1Animation) {
      if (this.mWrapped != null)
        this.mWrapped.onAnimationStart(param1Animation); 
    }
  }
  
  private static class AnimationOrAnimator {
    public final Animation animation = null;
    
    public final Animator animator;
    
    AnimationOrAnimator(Animator param1Animator) {
      this.animator = param1Animator;
      if (param1Animator == null)
        throw new IllegalStateException("Animator cannot be null"); 
    }
    
    AnimationOrAnimator(Animation param1Animation) {
      this.animator = null;
      if (param1Animation == null)
        throw new IllegalStateException("Animation cannot be null"); 
    }
  }
  
  private static class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter {
    View mView;
    
    AnimatorOnHWLayerIfNeededListener(View param1View) {
      this.mView = param1View;
    }
    
    public void onAnimationEnd(Animator param1Animator) {
      this.mView.setLayerType(0, null);
      param1Animator.removeListener((Animator.AnimatorListener)this);
    }
    
    public void onAnimationStart(Animator param1Animator) {
      this.mView.setLayerType(2, null);
    }
  }
  
  private static class EndViewTransitionAnimator extends AnimationSet implements Runnable {
    private boolean mAnimating = true;
    
    private final View mChild;
    
    private boolean mEnded;
    
    private final ViewGroup mParent;
    
    private boolean mTransitionEnded;
    
    EndViewTransitionAnimator(@NonNull Animation param1Animation, @NonNull ViewGroup param1ViewGroup, @NonNull View param1View) {
      super(false);
      this.mParent = param1ViewGroup;
      this.mChild = param1View;
      addAnimation(param1Animation);
      this.mParent.post(this);
    }
    
    public boolean getTransformation(long param1Long, Transformation param1Transformation) {
      this.mAnimating = true;
      if (this.mEnded)
        return !this.mTransitionEnded; 
      if (!super.getTransformation(param1Long, param1Transformation)) {
        this.mEnded = true;
        OneShotPreDrawListener.add((View)this.mParent, this);
        return true;
      } 
      return true;
    }
    
    public boolean getTransformation(long param1Long, Transformation param1Transformation, float param1Float) {
      this.mAnimating = true;
      if (this.mEnded)
        return !this.mTransitionEnded; 
      if (!super.getTransformation(param1Long, param1Transformation, param1Float)) {
        this.mEnded = true;
        OneShotPreDrawListener.add((View)this.mParent, this);
        return true;
      } 
      return true;
    }
    
    public void run() {
      if (!this.mEnded && this.mAnimating) {
        this.mAnimating = false;
        this.mParent.post(this);
        return;
      } 
      this.mParent.endViewTransition(this.mChild);
      this.mTransitionEnded = true;
    }
  }
  
  private static final class FragmentLifecycleCallbacksHolder {
    final FragmentManager.FragmentLifecycleCallbacks mCallback;
    
    final boolean mRecursive;
    
    FragmentLifecycleCallbacksHolder(FragmentManager.FragmentLifecycleCallbacks param1FragmentLifecycleCallbacks, boolean param1Boolean) {
      this.mCallback = param1FragmentLifecycleCallbacks;
      this.mRecursive = param1Boolean;
    }
  }
  
  static class FragmentTag {
    public static final int[] Fragment = new int[] { 16842755, 16842960, 16842961 };
    
    public static final int Fragment_id = 1;
    
    public static final int Fragment_name = 0;
    
    public static final int Fragment_tag = 2;
  }
  
  static interface OpGenerator {
    boolean generateOps(ArrayList<BackStackRecord> param1ArrayList, ArrayList<Boolean> param1ArrayList1);
  }
  
  private class PopBackStackState implements OpGenerator {
    final int mFlags;
    
    final int mId;
    
    final String mName;
    
    PopBackStackState(String param1String, int param1Int1, int param1Int2) {
      this.mName = param1String;
      this.mId = param1Int1;
      this.mFlags = param1Int2;
    }
    
    public boolean generateOps(ArrayList<BackStackRecord> param1ArrayList, ArrayList<Boolean> param1ArrayList1) {
      if (FragmentManagerImpl.this.mPrimaryNav != null && this.mId < 0 && this.mName == null) {
        FragmentManager fragmentManager = FragmentManagerImpl.this.mPrimaryNav.peekChildFragmentManager();
        if (fragmentManager != null && fragmentManager.popBackStackImmediate())
          return false; 
      } 
      return FragmentManagerImpl.this.popBackStackState(param1ArrayList, param1ArrayList1, this.mName, this.mId, this.mFlags);
    }
  }
  
  static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
    final boolean mIsBack;
    
    private int mNumPostponed;
    
    final BackStackRecord mRecord;
    
    StartEnterTransitionListener(BackStackRecord param1BackStackRecord, boolean param1Boolean) {
      this.mIsBack = param1Boolean;
      this.mRecord = param1BackStackRecord;
    }
    
    public void cancelTransaction() {
      this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, false, false);
    }
    
    public void completeTransaction() {
      boolean bool1;
      boolean bool2 = false;
      if (this.mNumPostponed > 0) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      FragmentManagerImpl fragmentManagerImpl = this.mRecord.mManager;
      int j = fragmentManagerImpl.mAdded.size();
      for (int i = 0; i < j; i++) {
        Fragment fragment = fragmentManagerImpl.mAdded.get(i);
        fragment.setOnStartEnterTransitionListener(null);
        if (bool1 && fragment.isPostponed())
          fragment.startPostponedEnterTransition(); 
      } 
      fragmentManagerImpl = this.mRecord.mManager;
      BackStackRecord backStackRecord = this.mRecord;
      boolean bool = this.mIsBack;
      if (!bool1)
        bool2 = true; 
      fragmentManagerImpl.completeExecute(backStackRecord, bool, bool2, true);
    }
    
    public boolean isReady() {
      return (this.mNumPostponed == 0);
    }
    
    public void onStartEnterTransition() {
      this.mNumPostponed--;
      if (this.mNumPostponed != 0)
        return; 
      this.mRecord.mManager.scheduleCommit();
    }
    
    public void startListening() {
      this.mNumPostponed++;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/fragment/app/FragmentManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
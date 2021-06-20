package androidx.fragment.app;

import android.animation.Animator;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.util.DebugUtils;
import androidx.core.view.LayoutInflaterCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, LifecycleOwner, ViewModelStoreOwner {
  static final int ACTIVITY_CREATED = 2;
  
  static final int CREATED = 1;
  
  static final int INITIALIZING = 0;
  
  static final int RESUMED = 4;
  
  static final int STARTED = 3;
  
  static final Object USE_DEFAULT_TRANSITION;
  
  private static final SimpleArrayMap<String, Class<?>> sClassMap = new SimpleArrayMap();
  
  boolean mAdded;
  
  AnimationInfo mAnimationInfo;
  
  Bundle mArguments;
  
  int mBackStackNesting;
  
  boolean mCalled;
  
  FragmentManagerImpl mChildFragmentManager;
  
  FragmentManagerNonConfig mChildNonConfig;
  
  ViewGroup mContainer;
  
  int mContainerId;
  
  boolean mDeferStart;
  
  boolean mDetached;
  
  int mFragmentId;
  
  FragmentManagerImpl mFragmentManager;
  
  boolean mFromLayout;
  
  boolean mHasMenu;
  
  boolean mHidden;
  
  boolean mHiddenChanged;
  
  FragmentHostCallback mHost;
  
  boolean mInLayout;
  
  int mIndex = -1;
  
  View mInnerView;
  
  boolean mIsCreated;
  
  boolean mIsNewlyAdded;
  
  LayoutInflater mLayoutInflater;
  
  LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
  
  boolean mMenuVisible = true;
  
  Fragment mParentFragment;
  
  boolean mPerformedCreateView;
  
  float mPostponedAlpha;
  
  boolean mRemoving;
  
  boolean mRestored;
  
  boolean mRetainInstance;
  
  boolean mRetaining;
  
  Bundle mSavedFragmentState;
  
  @Nullable
  Boolean mSavedUserVisibleHint;
  
  SparseArray<Parcelable> mSavedViewState;
  
  int mState = 0;
  
  String mTag;
  
  Fragment mTarget;
  
  int mTargetIndex = -1;
  
  int mTargetRequestCode;
  
  boolean mUserVisibleHint = true;
  
  View mView;
  
  LifecycleOwner mViewLifecycleOwner;
  
  MutableLiveData<LifecycleOwner> mViewLifecycleOwnerLiveData = new MutableLiveData();
  
  LifecycleRegistry mViewLifecycleRegistry;
  
  ViewModelStore mViewModelStore;
  
  String mWho;
  
  static {
    USE_DEFAULT_TRANSITION = new Object();
  }
  
  private AnimationInfo ensureAnimationInfo() {
    if (this.mAnimationInfo == null)
      this.mAnimationInfo = new AnimationInfo(); 
    return this.mAnimationInfo;
  }
  
  public static Fragment instantiate(Context paramContext, String paramString) {
    return instantiate(paramContext, paramString, null);
  }
  
  public static Fragment instantiate(Context paramContext, String paramString, @Nullable Bundle paramBundle) {
    try {
      Class<?> clazz2 = (Class)sClassMap.get(paramString);
      Class<?> clazz1 = clazz2;
      if (clazz2 == null) {
        clazz1 = paramContext.getClassLoader().loadClass(paramString);
        sClassMap.put(paramString, clazz1);
      } 
      Fragment fragment = clazz1.getConstructor(new Class[0]).newInstance(new Object[0]);
      if (paramBundle != null) {
        paramBundle.setClassLoader(fragment.getClass().getClassLoader());
        fragment.setArguments(paramBundle);
      } 
      return fragment;
    } catch (ClassNotFoundException classNotFoundException) {
      throw new InstantiationException("Unable to instantiate fragment " + paramString + ": make sure class name exists, is public, and has an" + " empty constructor that is public", classNotFoundException);
    } catch (InstantiationException instantiationException) {
      throw new InstantiationException("Unable to instantiate fragment " + paramString + ": make sure class name exists, is public, and has an" + " empty constructor that is public", instantiationException);
    } catch (IllegalAccessException illegalAccessException) {
      throw new InstantiationException("Unable to instantiate fragment " + paramString + ": make sure class name exists, is public, and has an" + " empty constructor that is public", illegalAccessException);
    } catch (NoSuchMethodException noSuchMethodException) {
      throw new InstantiationException("Unable to instantiate fragment " + paramString + ": could not find Fragment constructor", noSuchMethodException);
    } catch (InvocationTargetException invocationTargetException) {
      throw new InstantiationException("Unable to instantiate fragment " + paramString + ": calling Fragment constructor caused an exception", invocationTargetException);
    } 
  }
  
  static boolean isSupportFragmentClass(Context paramContext, String paramString) {
    try {
      Class<?> clazz2 = (Class)sClassMap.get(paramString);
      Class<?> clazz1 = clazz2;
      if (clazz2 == null) {
        clazz1 = paramContext.getClassLoader().loadClass(paramString);
        sClassMap.put(paramString, clazz1);
      } 
      return Fragment.class.isAssignableFrom(clazz1);
    } catch (ClassNotFoundException classNotFoundException) {
      return false;
    } 
  }
  
  void callStartTransitionListener() {
    OnStartEnterTransitionListener onStartEnterTransitionListener;
    if (this.mAnimationInfo == null) {
      onStartEnterTransitionListener = null;
    } else {
      this.mAnimationInfo.mEnterTransitionPostponed = false;
      onStartEnterTransitionListener = this.mAnimationInfo.mStartEnterTransitionListener;
      this.mAnimationInfo.mStartEnterTransitionListener = null;
    } 
    if (onStartEnterTransitionListener != null)
      onStartEnterTransitionListener.onStartEnterTransition(); 
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mFragmentId=#");
    paramPrintWriter.print(Integer.toHexString(this.mFragmentId));
    paramPrintWriter.print(" mContainerId=#");
    paramPrintWriter.print(Integer.toHexString(this.mContainerId));
    paramPrintWriter.print(" mTag=");
    paramPrintWriter.println(this.mTag);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mState=");
    paramPrintWriter.print(this.mState);
    paramPrintWriter.print(" mIndex=");
    paramPrintWriter.print(this.mIndex);
    paramPrintWriter.print(" mWho=");
    paramPrintWriter.print(this.mWho);
    paramPrintWriter.print(" mBackStackNesting=");
    paramPrintWriter.println(this.mBackStackNesting);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mAdded=");
    paramPrintWriter.print(this.mAdded);
    paramPrintWriter.print(" mRemoving=");
    paramPrintWriter.print(this.mRemoving);
    paramPrintWriter.print(" mFromLayout=");
    paramPrintWriter.print(this.mFromLayout);
    paramPrintWriter.print(" mInLayout=");
    paramPrintWriter.println(this.mInLayout);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mHidden=");
    paramPrintWriter.print(this.mHidden);
    paramPrintWriter.print(" mDetached=");
    paramPrintWriter.print(this.mDetached);
    paramPrintWriter.print(" mMenuVisible=");
    paramPrintWriter.print(this.mMenuVisible);
    paramPrintWriter.print(" mHasMenu=");
    paramPrintWriter.println(this.mHasMenu);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mRetainInstance=");
    paramPrintWriter.print(this.mRetainInstance);
    paramPrintWriter.print(" mRetaining=");
    paramPrintWriter.print(this.mRetaining);
    paramPrintWriter.print(" mUserVisibleHint=");
    paramPrintWriter.println(this.mUserVisibleHint);
    if (this.mFragmentManager != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mFragmentManager=");
      paramPrintWriter.println(this.mFragmentManager);
    } 
    if (this.mHost != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mHost=");
      paramPrintWriter.println(this.mHost);
    } 
    if (this.mParentFragment != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mParentFragment=");
      paramPrintWriter.println(this.mParentFragment);
    } 
    if (this.mArguments != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mArguments=");
      paramPrintWriter.println(this.mArguments);
    } 
    if (this.mSavedFragmentState != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedFragmentState=");
      paramPrintWriter.println(this.mSavedFragmentState);
    } 
    if (this.mSavedViewState != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedViewState=");
      paramPrintWriter.println(this.mSavedViewState);
    } 
    if (this.mTarget != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mTarget=");
      paramPrintWriter.print(this.mTarget);
      paramPrintWriter.print(" mTargetRequestCode=");
      paramPrintWriter.println(this.mTargetRequestCode);
    } 
    if (getNextAnim() != 0) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mNextAnim=");
      paramPrintWriter.println(getNextAnim());
    } 
    if (this.mContainer != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mContainer=");
      paramPrintWriter.println(this.mContainer);
    } 
    if (this.mView != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mView=");
      paramPrintWriter.println(this.mView);
    } 
    if (this.mInnerView != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mInnerView=");
      paramPrintWriter.println(this.mView);
    } 
    if (getAnimatingAway() != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mAnimatingAway=");
      paramPrintWriter.println(getAnimatingAway());
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mStateAfterAnimating=");
      paramPrintWriter.println(getStateAfterAnimating());
    } 
    if (getContext() != null)
      LoaderManager.getInstance(this).dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString); 
    if (this.mChildFragmentManager != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Child " + this.mChildFragmentManager + ":");
      this.mChildFragmentManager.dump(paramString + "  ", paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    return super.equals(paramObject);
  }
  
  Fragment findFragmentByWho(String paramString) {
    return paramString.equals(this.mWho) ? this : ((this.mChildFragmentManager != null) ? this.mChildFragmentManager.findFragmentByWho(paramString) : null);
  }
  
  @Nullable
  public final FragmentActivity getActivity() {
    return (this.mHost == null) ? null : (FragmentActivity)this.mHost.getActivity();
  }
  
  public boolean getAllowEnterTransitionOverlap() {
    return (this.mAnimationInfo == null || this.mAnimationInfo.mAllowEnterTransitionOverlap == null) ? true : this.mAnimationInfo.mAllowEnterTransitionOverlap.booleanValue();
  }
  
  public boolean getAllowReturnTransitionOverlap() {
    return (this.mAnimationInfo == null || this.mAnimationInfo.mAllowReturnTransitionOverlap == null) ? true : this.mAnimationInfo.mAllowReturnTransitionOverlap.booleanValue();
  }
  
  View getAnimatingAway() {
    return (this.mAnimationInfo == null) ? null : this.mAnimationInfo.mAnimatingAway;
  }
  
  Animator getAnimator() {
    return (this.mAnimationInfo == null) ? null : this.mAnimationInfo.mAnimator;
  }
  
  @Nullable
  public final Bundle getArguments() {
    return this.mArguments;
  }
  
  @NonNull
  public final FragmentManager getChildFragmentManager() {
    if (this.mChildFragmentManager == null) {
      instantiateChildFragmentManager();
      if (this.mState >= 4) {
        this.mChildFragmentManager.dispatchResume();
        return this.mChildFragmentManager;
      } 
    } else {
      return this.mChildFragmentManager;
    } 
    if (this.mState >= 3) {
      this.mChildFragmentManager.dispatchStart();
      return this.mChildFragmentManager;
    } 
    if (this.mState >= 2) {
      this.mChildFragmentManager.dispatchActivityCreated();
      return this.mChildFragmentManager;
    } 
    if (this.mState >= 1)
      this.mChildFragmentManager.dispatchCreate(); 
    return this.mChildFragmentManager;
  }
  
  @Nullable
  public Context getContext() {
    return (this.mHost == null) ? null : this.mHost.getContext();
  }
  
  @Nullable
  public Object getEnterTransition() {
    return (this.mAnimationInfo == null) ? null : this.mAnimationInfo.mEnterTransition;
  }
  
  SharedElementCallback getEnterTransitionCallback() {
    return (this.mAnimationInfo == null) ? null : this.mAnimationInfo.mEnterTransitionCallback;
  }
  
  @Nullable
  public Object getExitTransition() {
    return (this.mAnimationInfo == null) ? null : this.mAnimationInfo.mExitTransition;
  }
  
  SharedElementCallback getExitTransitionCallback() {
    return (this.mAnimationInfo == null) ? null : this.mAnimationInfo.mExitTransitionCallback;
  }
  
  @Nullable
  public final FragmentManager getFragmentManager() {
    return this.mFragmentManager;
  }
  
  @Nullable
  public final Object getHost() {
    return (this.mHost == null) ? null : this.mHost.onGetHost();
  }
  
  public final int getId() {
    return this.mFragmentId;
  }
  
  public final LayoutInflater getLayoutInflater() {
    return (this.mLayoutInflater == null) ? performGetLayoutInflater(null) : this.mLayoutInflater;
  }
  
  @Deprecated
  @NonNull
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public LayoutInflater getLayoutInflater(@Nullable Bundle paramBundle) {
    if (this.mHost == null)
      throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager."); 
    LayoutInflater layoutInflater = this.mHost.onGetLayoutInflater();
    getChildFragmentManager();
    LayoutInflaterCompat.setFactory2(layoutInflater, this.mChildFragmentManager.getLayoutInflaterFactory());
    return layoutInflater;
  }
  
  public Lifecycle getLifecycle() {
    return (Lifecycle)this.mLifecycleRegistry;
  }
  
  @Deprecated
  public LoaderManager getLoaderManager() {
    return LoaderManager.getInstance(this);
  }
  
  int getNextAnim() {
    return (this.mAnimationInfo == null) ? 0 : this.mAnimationInfo.mNextAnim;
  }
  
  int getNextTransition() {
    return (this.mAnimationInfo == null) ? 0 : this.mAnimationInfo.mNextTransition;
  }
  
  int getNextTransitionStyle() {
    return (this.mAnimationInfo == null) ? 0 : this.mAnimationInfo.mNextTransitionStyle;
  }
  
  @Nullable
  public final Fragment getParentFragment() {
    return this.mParentFragment;
  }
  
  public Object getReenterTransition() {
    return (this.mAnimationInfo == null) ? null : ((this.mAnimationInfo.mReenterTransition == USE_DEFAULT_TRANSITION) ? getExitTransition() : this.mAnimationInfo.mReenterTransition);
  }
  
  @NonNull
  public final Resources getResources() {
    return requireContext().getResources();
  }
  
  public final boolean getRetainInstance() {
    return this.mRetainInstance;
  }
  
  @Nullable
  public Object getReturnTransition() {
    return (this.mAnimationInfo == null) ? null : ((this.mAnimationInfo.mReturnTransition == USE_DEFAULT_TRANSITION) ? getEnterTransition() : this.mAnimationInfo.mReturnTransition);
  }
  
  @Nullable
  public Object getSharedElementEnterTransition() {
    return (this.mAnimationInfo == null) ? null : this.mAnimationInfo.mSharedElementEnterTransition;
  }
  
  @Nullable
  public Object getSharedElementReturnTransition() {
    return (this.mAnimationInfo == null) ? null : ((this.mAnimationInfo.mSharedElementReturnTransition == USE_DEFAULT_TRANSITION) ? getSharedElementEnterTransition() : this.mAnimationInfo.mSharedElementReturnTransition);
  }
  
  int getStateAfterAnimating() {
    return (this.mAnimationInfo == null) ? 0 : this.mAnimationInfo.mStateAfterAnimating;
  }
  
  @NonNull
  public final String getString(@StringRes int paramInt) {
    return getResources().getString(paramInt);
  }
  
  @NonNull
  public final String getString(@StringRes int paramInt, Object... paramVarArgs) {
    return getResources().getString(paramInt, paramVarArgs);
  }
  
  @Nullable
  public final String getTag() {
    return this.mTag;
  }
  
  @Nullable
  public final Fragment getTargetFragment() {
    return this.mTarget;
  }
  
  public final int getTargetRequestCode() {
    return this.mTargetRequestCode;
  }
  
  @NonNull
  public final CharSequence getText(@StringRes int paramInt) {
    return getResources().getText(paramInt);
  }
  
  public boolean getUserVisibleHint() {
    return this.mUserVisibleHint;
  }
  
  @Nullable
  public View getView() {
    return this.mView;
  }
  
  @MainThread
  @NonNull
  public LifecycleOwner getViewLifecycleOwner() {
    if (this.mViewLifecycleOwner == null)
      throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()"); 
    return this.mViewLifecycleOwner;
  }
  
  @NonNull
  public LiveData<LifecycleOwner> getViewLifecycleOwnerLiveData() {
    return (LiveData<LifecycleOwner>)this.mViewLifecycleOwnerLiveData;
  }
  
  @NonNull
  public ViewModelStore getViewModelStore() {
    if (getContext() == null)
      throw new IllegalStateException("Can't access ViewModels from detached fragment"); 
    if (this.mViewModelStore == null)
      this.mViewModelStore = new ViewModelStore(); 
    return this.mViewModelStore;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public final boolean hasOptionsMenu() {
    return this.mHasMenu;
  }
  
  public final int hashCode() {
    return super.hashCode();
  }
  
  void initState() {
    this.mIndex = -1;
    this.mWho = null;
    this.mAdded = false;
    this.mRemoving = false;
    this.mFromLayout = false;
    this.mInLayout = false;
    this.mRestored = false;
    this.mBackStackNesting = 0;
    this.mFragmentManager = null;
    this.mChildFragmentManager = null;
    this.mHost = null;
    this.mFragmentId = 0;
    this.mContainerId = 0;
    this.mTag = null;
    this.mHidden = false;
    this.mDetached = false;
    this.mRetaining = false;
  }
  
  void instantiateChildFragmentManager() {
    if (this.mHost == null)
      throw new IllegalStateException("Fragment has not been attached yet."); 
    this.mChildFragmentManager = new FragmentManagerImpl();
    this.mChildFragmentManager.attachController(this.mHost, new FragmentContainer() {
          public Fragment instantiate(Context param1Context, String param1String, Bundle param1Bundle) {
            return Fragment.this.mHost.instantiate(param1Context, param1String, param1Bundle);
          }
          
          @Nullable
          public View onFindViewById(int param1Int) {
            if (Fragment.this.mView == null)
              throw new IllegalStateException("Fragment does not have a view"); 
            return Fragment.this.mView.findViewById(param1Int);
          }
          
          public boolean onHasView() {
            return (Fragment.this.mView != null);
          }
        }this);
  }
  
  public final boolean isAdded() {
    return (this.mHost != null && this.mAdded);
  }
  
  public final boolean isDetached() {
    return this.mDetached;
  }
  
  public final boolean isHidden() {
    return this.mHidden;
  }
  
  boolean isHideReplaced() {
    return (this.mAnimationInfo == null) ? false : this.mAnimationInfo.mIsHideReplaced;
  }
  
  final boolean isInBackStack() {
    return (this.mBackStackNesting > 0);
  }
  
  public final boolean isInLayout() {
    return this.mInLayout;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public final boolean isMenuVisible() {
    return this.mMenuVisible;
  }
  
  boolean isPostponed() {
    return (this.mAnimationInfo == null) ? false : this.mAnimationInfo.mEnterTransitionPostponed;
  }
  
  public final boolean isRemoving() {
    return this.mRemoving;
  }
  
  public final boolean isResumed() {
    return (this.mState >= 4);
  }
  
  public final boolean isStateSaved() {
    return (this.mFragmentManager == null) ? false : this.mFragmentManager.isStateSaved();
  }
  
  public final boolean isVisible() {
    return (isAdded() && !isHidden() && this.mView != null && this.mView.getWindowToken() != null && this.mView.getVisibility() == 0);
  }
  
  void noteStateNotSaved() {
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.noteStateNotSaved(); 
  }
  
  @CallSuper
  public void onActivityCreated(@Nullable Bundle paramBundle) {
    this.mCalled = true;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  @Deprecated
  @CallSuper
  public void onAttach(Activity paramActivity) {
    this.mCalled = true;
  }
  
  @CallSuper
  public void onAttach(Context paramContext) {
    Activity activity;
    this.mCalled = true;
    if (this.mHost == null) {
      paramContext = null;
    } else {
      activity = this.mHost.getActivity();
    } 
    if (activity != null) {
      this.mCalled = false;
      onAttach(activity);
    } 
  }
  
  public void onAttachFragment(Fragment paramFragment) {}
  
  @CallSuper
  public void onConfigurationChanged(Configuration paramConfiguration) {
    this.mCalled = true;
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem) {
    return false;
  }
  
  @CallSuper
  public void onCreate(@Nullable Bundle paramBundle) {
    this.mCalled = true;
    restoreChildFragmentState(paramBundle);
    if (this.mChildFragmentManager != null && !this.mChildFragmentManager.isStateAtLeast(1))
      this.mChildFragmentManager.dispatchCreate(); 
  }
  
  public Animation onCreateAnimation(int paramInt1, boolean paramBoolean, int paramInt2) {
    return null;
  }
  
  public Animator onCreateAnimator(int paramInt1, boolean paramBoolean, int paramInt2) {
    return null;
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo) {
    getActivity().onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {}
  
  @Nullable
  public View onCreateView(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
    return null;
  }
  
  @CallSuper
  public void onDestroy() {
    boolean bool = true;
    this.mCalled = true;
    FragmentActivity fragmentActivity = getActivity();
    if (fragmentActivity == null || !fragmentActivity.isChangingConfigurations())
      bool = false; 
    if (this.mViewModelStore != null && !bool)
      this.mViewModelStore.clear(); 
  }
  
  public void onDestroyOptionsMenu() {}
  
  @CallSuper
  public void onDestroyView() {
    this.mCalled = true;
  }
  
  @CallSuper
  public void onDetach() {
    this.mCalled = true;
  }
  
  @NonNull
  public LayoutInflater onGetLayoutInflater(@Nullable Bundle paramBundle) {
    return getLayoutInflater(paramBundle);
  }
  
  public void onHiddenChanged(boolean paramBoolean) {}
  
  @Deprecated
  @CallSuper
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle) {
    this.mCalled = true;
  }
  
  @CallSuper
  public void onInflate(Context paramContext, AttributeSet paramAttributeSet, Bundle paramBundle) {
    Activity activity;
    this.mCalled = true;
    if (this.mHost == null) {
      paramContext = null;
    } else {
      activity = this.mHost.getActivity();
    } 
    if (activity != null) {
      this.mCalled = false;
      onInflate(activity, paramAttributeSet, paramBundle);
    } 
  }
  
  @CallSuper
  public void onLowMemory() {
    this.mCalled = true;
  }
  
  public void onMultiWindowModeChanged(boolean paramBoolean) {}
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    return false;
  }
  
  public void onOptionsMenuClosed(Menu paramMenu) {}
  
  @CallSuper
  public void onPause() {
    this.mCalled = true;
  }
  
  public void onPictureInPictureModeChanged(boolean paramBoolean) {}
  
  public void onPrepareOptionsMenu(Menu paramMenu) {}
  
  public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfint) {}
  
  @CallSuper
  public void onResume() {
    this.mCalled = true;
  }
  
  public void onSaveInstanceState(@NonNull Bundle paramBundle) {}
  
  @CallSuper
  public void onStart() {
    this.mCalled = true;
  }
  
  @CallSuper
  public void onStop() {
    this.mCalled = true;
  }
  
  public void onViewCreated(@NonNull View paramView, @Nullable Bundle paramBundle) {}
  
  @CallSuper
  public void onViewStateRestored(@Nullable Bundle paramBundle) {
    this.mCalled = true;
  }
  
  @Nullable
  FragmentManager peekChildFragmentManager() {
    return this.mChildFragmentManager;
  }
  
  void performActivityCreated(Bundle paramBundle) {
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.noteStateNotSaved(); 
    this.mState = 2;
    this.mCalled = false;
    onActivityCreated(paramBundle);
    if (!this.mCalled)
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()"); 
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchActivityCreated(); 
  }
  
  void performConfigurationChanged(Configuration paramConfiguration) {
    onConfigurationChanged(paramConfiguration);
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchConfigurationChanged(paramConfiguration); 
  }
  
  boolean performContextItemSelected(MenuItem paramMenuItem) {
    return !(this.mHidden || (!onContextItemSelected(paramMenuItem) && (this.mChildFragmentManager == null || !this.mChildFragmentManager.dispatchContextItemSelected(paramMenuItem))));
  }
  
  void performCreate(Bundle paramBundle) {
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.noteStateNotSaved(); 
    this.mState = 1;
    this.mCalled = false;
    onCreate(paramBundle);
    this.mIsCreated = true;
    if (!this.mCalled)
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()"); 
    this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
  }
  
  boolean performCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {
    boolean bool1 = false;
    boolean bool2 = false;
    if (!this.mHidden) {
      boolean bool = bool2;
      if (this.mHasMenu) {
        bool = bool2;
        if (this.mMenuVisible) {
          bool = true;
          onCreateOptionsMenu(paramMenu, paramMenuInflater);
        } 
      } 
      bool1 = bool;
      if (this.mChildFragmentManager != null)
        bool1 = bool | this.mChildFragmentManager.dispatchCreateOptionsMenu(paramMenu, paramMenuInflater); 
    } 
    return bool1;
  }
  
  void performCreateView(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.noteStateNotSaved(); 
    this.mPerformedCreateView = true;
    this.mViewLifecycleOwner = new LifecycleOwner() {
        public Lifecycle getLifecycle() {
          if (Fragment.this.mViewLifecycleRegistry == null)
            Fragment.this.mViewLifecycleRegistry = new LifecycleRegistry(Fragment.this.mViewLifecycleOwner); 
          return (Lifecycle)Fragment.this.mViewLifecycleRegistry;
        }
      };
    this.mViewLifecycleRegistry = null;
    this.mView = onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    if (this.mView != null) {
      this.mViewLifecycleOwner.getLifecycle();
      this.mViewLifecycleOwnerLiveData.setValue(this.mViewLifecycleOwner);
      return;
    } 
    if (this.mViewLifecycleRegistry != null)
      throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null"); 
    this.mViewLifecycleOwner = null;
  }
  
  void performDestroy() {
    this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchDestroy(); 
    this.mState = 0;
    this.mCalled = false;
    this.mIsCreated = false;
    onDestroy();
    if (!this.mCalled)
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()"); 
    this.mChildFragmentManager = null;
  }
  
  void performDestroyView() {
    if (this.mView != null)
      this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY); 
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchDestroyView(); 
    this.mState = 1;
    this.mCalled = false;
    onDestroyView();
    if (!this.mCalled)
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()"); 
    LoaderManager.getInstance(this).markForRedelivery();
    this.mPerformedCreateView = false;
  }
  
  void performDetach() {
    this.mCalled = false;
    onDetach();
    this.mLayoutInflater = null;
    if (!this.mCalled)
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDetach()"); 
    if (this.mChildFragmentManager != null) {
      if (!this.mRetaining)
        throw new IllegalStateException("Child FragmentManager of " + this + " was not " + " destroyed and this fragment is not retaining instance"); 
      this.mChildFragmentManager.dispatchDestroy();
      this.mChildFragmentManager = null;
    } 
  }
  
  @NonNull
  LayoutInflater performGetLayoutInflater(@Nullable Bundle paramBundle) {
    this.mLayoutInflater = onGetLayoutInflater(paramBundle);
    return this.mLayoutInflater;
  }
  
  void performLowMemory() {
    onLowMemory();
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchLowMemory(); 
  }
  
  void performMultiWindowModeChanged(boolean paramBoolean) {
    onMultiWindowModeChanged(paramBoolean);
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchMultiWindowModeChanged(paramBoolean); 
  }
  
  boolean performOptionsItemSelected(MenuItem paramMenuItem) {
    return !(this.mHidden || ((!this.mHasMenu || !this.mMenuVisible || !onOptionsItemSelected(paramMenuItem)) && (this.mChildFragmentManager == null || !this.mChildFragmentManager.dispatchOptionsItemSelected(paramMenuItem))));
  }
  
  void performOptionsMenuClosed(Menu paramMenu) {
    if (!this.mHidden) {
      if (this.mHasMenu && this.mMenuVisible)
        onOptionsMenuClosed(paramMenu); 
      if (this.mChildFragmentManager != null)
        this.mChildFragmentManager.dispatchOptionsMenuClosed(paramMenu); 
    } 
  }
  
  void performPause() {
    if (this.mView != null)
      this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE); 
    this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchPause(); 
    this.mState = 3;
    this.mCalled = false;
    onPause();
    if (!this.mCalled)
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()"); 
  }
  
  void performPictureInPictureModeChanged(boolean paramBoolean) {
    onPictureInPictureModeChanged(paramBoolean);
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchPictureInPictureModeChanged(paramBoolean); 
  }
  
  boolean performPrepareOptionsMenu(Menu paramMenu) {
    boolean bool1 = false;
    boolean bool2 = false;
    if (!this.mHidden) {
      boolean bool = bool2;
      if (this.mHasMenu) {
        bool = bool2;
        if (this.mMenuVisible) {
          bool = true;
          onPrepareOptionsMenu(paramMenu);
        } 
      } 
      bool1 = bool;
      if (this.mChildFragmentManager != null)
        bool1 = bool | this.mChildFragmentManager.dispatchPrepareOptionsMenu(paramMenu); 
    } 
    return bool1;
  }
  
  void performResume() {
    if (this.mChildFragmentManager != null) {
      this.mChildFragmentManager.noteStateNotSaved();
      this.mChildFragmentManager.execPendingActions();
    } 
    this.mState = 4;
    this.mCalled = false;
    onResume();
    if (!this.mCalled)
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()"); 
    if (this.mChildFragmentManager != null) {
      this.mChildFragmentManager.dispatchResume();
      this.mChildFragmentManager.execPendingActions();
    } 
    this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    if (this.mView != null)
      this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME); 
  }
  
  void performSaveInstanceState(Bundle paramBundle) {
    onSaveInstanceState(paramBundle);
    if (this.mChildFragmentManager != null) {
      Parcelable parcelable = this.mChildFragmentManager.saveAllState();
      if (parcelable != null)
        paramBundle.putParcelable("android:support:fragments", parcelable); 
    } 
  }
  
  void performStart() {
    if (this.mChildFragmentManager != null) {
      this.mChildFragmentManager.noteStateNotSaved();
      this.mChildFragmentManager.execPendingActions();
    } 
    this.mState = 3;
    this.mCalled = false;
    onStart();
    if (!this.mCalled)
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()"); 
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchStart(); 
    this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
    if (this.mView != null)
      this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START); 
  }
  
  void performStop() {
    if (this.mView != null)
      this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP); 
    this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchStop(); 
    this.mState = 2;
    this.mCalled = false;
    onStop();
    if (!this.mCalled)
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()"); 
  }
  
  public void postponeEnterTransition() {
    (ensureAnimationInfo()).mEnterTransitionPostponed = true;
  }
  
  public void registerForContextMenu(View paramView) {
    paramView.setOnCreateContextMenuListener(this);
  }
  
  public final void requestPermissions(@NonNull String[] paramArrayOfString, int paramInt) {
    if (this.mHost == null)
      throw new IllegalStateException("Fragment " + this + " not attached to Activity"); 
    this.mHost.onRequestPermissionsFromFragment(this, paramArrayOfString, paramInt);
  }
  
  @NonNull
  public final FragmentActivity requireActivity() {
    FragmentActivity fragmentActivity = getActivity();
    if (fragmentActivity == null)
      throw new IllegalStateException("Fragment " + this + " not attached to an activity."); 
    return fragmentActivity;
  }
  
  @NonNull
  public final Context requireContext() {
    Context context = getContext();
    if (context == null)
      throw new IllegalStateException("Fragment " + this + " not attached to a context."); 
    return context;
  }
  
  @NonNull
  public final FragmentManager requireFragmentManager() {
    FragmentManager fragmentManager = getFragmentManager();
    if (fragmentManager == null)
      throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager."); 
    return fragmentManager;
  }
  
  @NonNull
  public final Object requireHost() {
    Object object = getHost();
    if (object == null)
      throw new IllegalStateException("Fragment " + this + " not attached to a host."); 
    return object;
  }
  
  void restoreChildFragmentState(@Nullable Bundle paramBundle) {
    if (paramBundle != null) {
      Parcelable parcelable = paramBundle.getParcelable("android:support:fragments");
      if (parcelable != null) {
        if (this.mChildFragmentManager == null)
          instantiateChildFragmentManager(); 
        this.mChildFragmentManager.restoreAllState(parcelable, this.mChildNonConfig);
        this.mChildNonConfig = null;
        this.mChildFragmentManager.dispatchCreate();
      } 
    } 
  }
  
  final void restoreViewState(Bundle paramBundle) {
    if (this.mSavedViewState != null) {
      this.mInnerView.restoreHierarchyState(this.mSavedViewState);
      this.mSavedViewState = null;
    } 
    this.mCalled = false;
    onViewStateRestored(paramBundle);
    if (!this.mCalled)
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()"); 
    if (this.mView != null)
      this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE); 
  }
  
  public void setAllowEnterTransitionOverlap(boolean paramBoolean) {
    (ensureAnimationInfo()).mAllowEnterTransitionOverlap = Boolean.valueOf(paramBoolean);
  }
  
  public void setAllowReturnTransitionOverlap(boolean paramBoolean) {
    (ensureAnimationInfo()).mAllowReturnTransitionOverlap = Boolean.valueOf(paramBoolean);
  }
  
  void setAnimatingAway(View paramView) {
    (ensureAnimationInfo()).mAnimatingAway = paramView;
  }
  
  void setAnimator(Animator paramAnimator) {
    (ensureAnimationInfo()).mAnimator = paramAnimator;
  }
  
  public void setArguments(@Nullable Bundle paramBundle) {
    if (this.mIndex >= 0 && isStateSaved())
      throw new IllegalStateException("Fragment already active and state has been saved"); 
    this.mArguments = paramBundle;
  }
  
  public void setEnterSharedElementCallback(SharedElementCallback paramSharedElementCallback) {
    (ensureAnimationInfo()).mEnterTransitionCallback = paramSharedElementCallback;
  }
  
  public void setEnterTransition(@Nullable Object paramObject) {
    (ensureAnimationInfo()).mEnterTransition = paramObject;
  }
  
  public void setExitSharedElementCallback(SharedElementCallback paramSharedElementCallback) {
    (ensureAnimationInfo()).mExitTransitionCallback = paramSharedElementCallback;
  }
  
  public void setExitTransition(@Nullable Object paramObject) {
    (ensureAnimationInfo()).mExitTransition = paramObject;
  }
  
  public void setHasOptionsMenu(boolean paramBoolean) {
    if (this.mHasMenu != paramBoolean) {
      this.mHasMenu = paramBoolean;
      if (isAdded() && !isHidden())
        this.mHost.onSupportInvalidateOptionsMenu(); 
    } 
  }
  
  void setHideReplaced(boolean paramBoolean) {
    (ensureAnimationInfo()).mIsHideReplaced = paramBoolean;
  }
  
  final void setIndex(int paramInt, Fragment paramFragment) {
    this.mIndex = paramInt;
    if (paramFragment != null) {
      paramFragment.mWho += ":" + this.mIndex;
      return;
    } 
    this.mWho = "android:fragment:" + this.mIndex;
  }
  
  public void setInitialSavedState(@Nullable SavedState paramSavedState) {
    if (this.mIndex >= 0)
      throw new IllegalStateException("Fragment already active"); 
    if (paramSavedState != null && paramSavedState.mState != null) {
      Bundle bundle = paramSavedState.mState;
    } else {
      paramSavedState = null;
    } 
    this.mSavedFragmentState = (Bundle)paramSavedState;
  }
  
  public void setMenuVisibility(boolean paramBoolean) {
    if (this.mMenuVisible != paramBoolean) {
      this.mMenuVisible = paramBoolean;
      if (this.mHasMenu && isAdded() && !isHidden())
        this.mHost.onSupportInvalidateOptionsMenu(); 
    } 
  }
  
  void setNextAnim(int paramInt) {
    if (this.mAnimationInfo == null && paramInt == 0)
      return; 
    (ensureAnimationInfo()).mNextAnim = paramInt;
  }
  
  void setNextTransition(int paramInt1, int paramInt2) {
    if (this.mAnimationInfo == null && paramInt1 == 0 && paramInt2 == 0)
      return; 
    ensureAnimationInfo();
    this.mAnimationInfo.mNextTransition = paramInt1;
    this.mAnimationInfo.mNextTransitionStyle = paramInt2;
  }
  
  void setOnStartEnterTransitionListener(OnStartEnterTransitionListener paramOnStartEnterTransitionListener) {
    ensureAnimationInfo();
    if (paramOnStartEnterTransitionListener != this.mAnimationInfo.mStartEnterTransitionListener) {
      if (paramOnStartEnterTransitionListener != null && this.mAnimationInfo.mStartEnterTransitionListener != null)
        throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this); 
      if (this.mAnimationInfo.mEnterTransitionPostponed)
        this.mAnimationInfo.mStartEnterTransitionListener = paramOnStartEnterTransitionListener; 
      if (paramOnStartEnterTransitionListener != null) {
        paramOnStartEnterTransitionListener.startListening();
        return;
      } 
    } 
  }
  
  public void setReenterTransition(@Nullable Object paramObject) {
    (ensureAnimationInfo()).mReenterTransition = paramObject;
  }
  
  public void setRetainInstance(boolean paramBoolean) {
    this.mRetainInstance = paramBoolean;
  }
  
  public void setReturnTransition(@Nullable Object paramObject) {
    (ensureAnimationInfo()).mReturnTransition = paramObject;
  }
  
  public void setSharedElementEnterTransition(@Nullable Object paramObject) {
    (ensureAnimationInfo()).mSharedElementEnterTransition = paramObject;
  }
  
  public void setSharedElementReturnTransition(@Nullable Object paramObject) {
    (ensureAnimationInfo()).mSharedElementReturnTransition = paramObject;
  }
  
  void setStateAfterAnimating(int paramInt) {
    (ensureAnimationInfo()).mStateAfterAnimating = paramInt;
  }
  
  public void setTargetFragment(@Nullable Fragment paramFragment, int paramInt) {
    FragmentManager fragmentManager1;
    FragmentManager fragmentManager2 = getFragmentManager();
    if (paramFragment != null) {
      fragmentManager1 = paramFragment.getFragmentManager();
    } else {
      fragmentManager1 = null;
    } 
    if (fragmentManager2 != null && fragmentManager1 != null && fragmentManager2 != fragmentManager1)
      throw new IllegalArgumentException("Fragment " + paramFragment + " must share the same FragmentManager to be set as a target fragment"); 
    for (Fragment fragment = paramFragment; fragment != null; fragment = fragment.getTargetFragment()) {
      if (fragment == this)
        throw new IllegalArgumentException("Setting " + paramFragment + " as the target of " + this + " would create a target cycle"); 
    } 
    this.mTarget = paramFragment;
    this.mTargetRequestCode = paramInt;
  }
  
  public void setUserVisibleHint(boolean paramBoolean) {
    boolean bool;
    if (!this.mUserVisibleHint && paramBoolean && this.mState < 3 && this.mFragmentManager != null && isAdded() && this.mIsCreated)
      this.mFragmentManager.performPendingDeferredStart(this); 
    this.mUserVisibleHint = paramBoolean;
    if (this.mState < 3 && !paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mDeferStart = bool;
    if (this.mSavedFragmentState != null)
      this.mSavedUserVisibleHint = Boolean.valueOf(paramBoolean); 
  }
  
  public boolean shouldShowRequestPermissionRationale(@NonNull String paramString) {
    return (this.mHost != null) ? this.mHost.onShouldShowRequestPermissionRationale(paramString) : false;
  }
  
  public void startActivity(Intent paramIntent) {
    startActivity(paramIntent, null);
  }
  
  public void startActivity(Intent paramIntent, @Nullable Bundle paramBundle) {
    if (this.mHost == null)
      throw new IllegalStateException("Fragment " + this + " not attached to Activity"); 
    this.mHost.onStartActivityFromFragment(this, paramIntent, -1, paramBundle);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt) {
    startActivityForResult(paramIntent, paramInt, null);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt, @Nullable Bundle paramBundle) {
    if (this.mHost == null)
      throw new IllegalStateException("Fragment " + this + " not attached to Activity"); 
    this.mHost.onStartActivityFromFragment(this, paramIntent, paramInt, paramBundle);
  }
  
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, @Nullable Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle) throws IntentSender.SendIntentException {
    if (this.mHost == null)
      throw new IllegalStateException("Fragment " + this + " not attached to Activity"); 
    this.mHost.onStartIntentSenderFromFragment(this, paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
  }
  
  public void startPostponedEnterTransition() {
    if (this.mFragmentManager == null || this.mFragmentManager.mHost == null) {
      (ensureAnimationInfo()).mEnterTransitionPostponed = false;
      return;
    } 
    if (Looper.myLooper() != this.mFragmentManager.mHost.getHandler().getLooper()) {
      this.mFragmentManager.mHost.getHandler().postAtFrontOfQueue(new Runnable() {
            public void run() {
              Fragment.this.callStartTransitionListener();
            }
          });
      return;
    } 
    callStartTransitionListener();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    DebugUtils.buildShortClassTag(this, stringBuilder);
    if (this.mIndex >= 0) {
      stringBuilder.append(" #");
      stringBuilder.append(this.mIndex);
    } 
    if (this.mFragmentId != 0) {
      stringBuilder.append(" id=0x");
      stringBuilder.append(Integer.toHexString(this.mFragmentId));
    } 
    if (this.mTag != null) {
      stringBuilder.append(" ");
      stringBuilder.append(this.mTag);
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void unregisterForContextMenu(View paramView) {
    paramView.setOnCreateContextMenuListener(null);
  }
  
  static class AnimationInfo {
    Boolean mAllowEnterTransitionOverlap;
    
    Boolean mAllowReturnTransitionOverlap;
    
    View mAnimatingAway;
    
    Animator mAnimator;
    
    Object mEnterTransition = null;
    
    SharedElementCallback mEnterTransitionCallback = null;
    
    boolean mEnterTransitionPostponed;
    
    Object mExitTransition = null;
    
    SharedElementCallback mExitTransitionCallback = null;
    
    boolean mIsHideReplaced;
    
    int mNextAnim;
    
    int mNextTransition;
    
    int mNextTransitionStyle;
    
    Object mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
    
    Object mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
    
    Object mSharedElementEnterTransition = null;
    
    Object mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
    
    Fragment.OnStartEnterTransitionListener mStartEnterTransitionListener;
    
    int mStateAfterAnimating;
  }
  
  public static class InstantiationException extends RuntimeException {
    public InstantiationException(String param1String, Exception param1Exception) {
      super(param1String, param1Exception);
    }
  }
  
  static interface OnStartEnterTransitionListener {
    void onStartEnterTransition();
    
    void startListening();
  }
  
  public static class SavedState implements Parcelable {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public Fragment.SavedState createFromParcel(Parcel param2Parcel) {
          return new Fragment.SavedState(param2Parcel, null);
        }
        
        public Fragment.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new Fragment.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public Fragment.SavedState[] newArray(int param2Int) {
          return new Fragment.SavedState[param2Int];
        }
      };
    
    final Bundle mState;
    
    SavedState(Bundle param1Bundle) {
      this.mState = param1Bundle;
    }
    
    SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      this.mState = param1Parcel.readBundle();
      if (param1ClassLoader != null && this.mState != null)
        this.mState.setClassLoader(param1ClassLoader); 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeBundle(this.mState);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public Fragment.SavedState createFromParcel(Parcel param1Parcel) {
      return new Fragment.SavedState(param1Parcel, null);
    }
    
    public Fragment.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new Fragment.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public Fragment.SavedState[] newArray(int param1Int) {
      return new Fragment.SavedState[param1Int];
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/fragment/app/Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
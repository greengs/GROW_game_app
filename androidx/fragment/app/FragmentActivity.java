package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.SparseArrayCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ComponentActivity;
import androidx.core.app.SharedElementCallback;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class FragmentActivity extends ComponentActivity implements ViewModelStoreOwner, ActivityCompat.OnRequestPermissionsResultCallback, ActivityCompat.RequestPermissionsRequestCodeValidator {
  static final String ALLOCATED_REQUEST_INDICIES_TAG = "android:support:request_indicies";
  
  static final String FRAGMENTS_TAG = "android:support:fragments";
  
  static final int MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS = 65534;
  
  static final int MSG_RESUME_PENDING = 2;
  
  static final String NEXT_CANDIDATE_REQUEST_INDEX_TAG = "android:support:next_request_index";
  
  static final String REQUEST_FRAGMENT_WHO_TAG = "android:support:request_fragment_who";
  
  private static final String TAG = "FragmentActivity";
  
  boolean mCreated;
  
  final FragmentController mFragments = FragmentController.createController(new HostCallbacks());
  
  final Handler mHandler = new Handler() {
      public void handleMessage(Message param1Message) {
        switch (param1Message.what) {
          default:
            super.handleMessage(param1Message);
            return;
          case 2:
            break;
        } 
        FragmentActivity.this.onResumeFragments();
        FragmentActivity.this.mFragments.execPendingActions();
      }
    };
  
  int mNextCandidateRequestIndex;
  
  SparseArrayCompat<String> mPendingFragmentActivityResults;
  
  boolean mRequestedPermissionsFromFragment;
  
  boolean mResumed;
  
  boolean mStartedActivityFromFragment;
  
  boolean mStartedIntentSenderFromFragment;
  
  boolean mStopped = true;
  
  private ViewModelStore mViewModelStore;
  
  private int allocateRequestIndex(Fragment paramFragment) {
    if (this.mPendingFragmentActivityResults.size() >= 65534)
      throw new IllegalStateException("Too many pending Fragment activity results."); 
    while (this.mPendingFragmentActivityResults.indexOfKey(this.mNextCandidateRequestIndex) >= 0)
      this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % 65534; 
    int i = this.mNextCandidateRequestIndex;
    this.mPendingFragmentActivityResults.put(i, paramFragment.mWho);
    this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % 65534;
    return i;
  }
  
  static void checkForValidRequestCode(int paramInt) {
    if ((0xFFFF0000 & paramInt) != 0)
      throw new IllegalArgumentException("Can only use lower 16 bits for requestCode"); 
  }
  
  private void markFragmentsCreated() {
    do {
    
    } while (markState(getSupportFragmentManager(), Lifecycle.State.CREATED));
  }
  
  private static boolean markState(FragmentManager paramFragmentManager, Lifecycle.State paramState) {
    boolean bool = false;
    for (Fragment fragment : paramFragmentManager.getFragments()) {
      if (fragment != null) {
        boolean bool1 = bool;
        if (fragment.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
          fragment.mLifecycleRegistry.markState(paramState);
          bool1 = true;
        } 
        FragmentManager fragmentManager = fragment.peekChildFragmentManager();
        bool = bool1;
        if (fragmentManager != null)
          bool = bool1 | markState(fragmentManager, paramState); 
      } 
    } 
    return bool;
  }
  
  final View dispatchFragmentsOnCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return this.mFragments.onCreateView(paramView, paramString, paramContext, paramAttributeSet);
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("Local FragmentActivity ");
    paramPrintWriter.print(Integer.toHexString(System.identityHashCode(this)));
    paramPrintWriter.println(" State:");
    String str = paramString + "  ";
    paramPrintWriter.print(str);
    paramPrintWriter.print("mCreated=");
    paramPrintWriter.print(this.mCreated);
    paramPrintWriter.print(" mResumed=");
    paramPrintWriter.print(this.mResumed);
    paramPrintWriter.print(" mStopped=");
    paramPrintWriter.print(this.mStopped);
    if (getApplication() != null)
      LoaderManager.getInstance((LifecycleOwner)this).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString); 
    this.mFragments.getSupportFragmentManager().dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  public Object getLastCustomNonConfigurationInstance() {
    NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances)getLastNonConfigurationInstance();
    return (nonConfigurationInstances != null) ? nonConfigurationInstances.custom : null;
  }
  
  public Lifecycle getLifecycle() {
    return super.getLifecycle();
  }
  
  public FragmentManager getSupportFragmentManager() {
    return this.mFragments.getSupportFragmentManager();
  }
  
  @Deprecated
  public LoaderManager getSupportLoaderManager() {
    return LoaderManager.getInstance((LifecycleOwner)this);
  }
  
  @NonNull
  public ViewModelStore getViewModelStore() {
    if (getApplication() == null)
      throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call."); 
    if (this.mViewModelStore == null) {
      NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances)getLastNonConfigurationInstance();
      if (nonConfigurationInstances != null)
        this.mViewModelStore = nonConfigurationInstances.viewModelStore; 
      if (this.mViewModelStore == null)
        this.mViewModelStore = new ViewModelStore(); 
    } 
    return this.mViewModelStore;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, @Nullable Intent paramIntent) {
    this.mFragments.noteStateNotSaved();
    int i = paramInt1 >> 16;
    if (i != 0) {
      String str = (String)this.mPendingFragmentActivityResults.get(--i);
      this.mPendingFragmentActivityResults.remove(i);
      if (str == null) {
        Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
        return;
      } 
      Fragment fragment = this.mFragments.findFragmentByWho(str);
      if (fragment == null) {
        Log.w("FragmentActivity", "Activity result no fragment exists for who: " + str);
        return;
      } 
      fragment.onActivityResult(0xFFFF & paramInt1, paramInt2, paramIntent);
      return;
    } 
    ActivityCompat.PermissionCompatDelegate permissionCompatDelegate = ActivityCompat.getPermissionCompatDelegate();
    if (permissionCompatDelegate == null || !permissionCompatDelegate.onActivityResult((Activity)this, paramInt1, paramInt2, paramIntent)) {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
    } 
  }
  
  public void onAttachFragment(Fragment paramFragment) {}
  
  public void onBackPressed() {
    FragmentManager fragmentManager = this.mFragments.getSupportFragmentManager();
    boolean bool = fragmentManager.isStateSaved();
    if ((!bool || Build.VERSION.SDK_INT > 25) && (bool || !fragmentManager.popBackStackImmediate())) {
      super.onBackPressed();
      return;
    } 
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    this.mFragments.noteStateNotSaved();
    this.mFragments.dispatchConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(@Nullable Bundle paramBundle) {
    FragmentManagerNonConfig fragmentManagerNonConfig = null;
    this.mFragments.attachHost(null);
    super.onCreate(paramBundle);
    NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances)getLastNonConfigurationInstance();
    if (nonConfigurationInstances != null && nonConfigurationInstances.viewModelStore != null && this.mViewModelStore == null)
      this.mViewModelStore = nonConfigurationInstances.viewModelStore; 
    if (paramBundle != null) {
      Parcelable parcelable = paramBundle.getParcelable("android:support:fragments");
      FragmentController fragmentController = this.mFragments;
      if (nonConfigurationInstances != null)
        fragmentManagerNonConfig = nonConfigurationInstances.fragments; 
      fragmentController.restoreAllState(parcelable, fragmentManagerNonConfig);
      if (paramBundle.containsKey("android:support:next_request_index")) {
        this.mNextCandidateRequestIndex = paramBundle.getInt("android:support:next_request_index");
        int[] arrayOfInt = paramBundle.getIntArray("android:support:request_indicies");
        String[] arrayOfString = paramBundle.getStringArray("android:support:request_fragment_who");
        if (arrayOfInt == null || arrayOfString == null || arrayOfInt.length != arrayOfString.length) {
          Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
        } else {
          this.mPendingFragmentActivityResults = new SparseArrayCompat(arrayOfInt.length);
          int i = 0;
          while (true) {
            if (i < arrayOfInt.length) {
              this.mPendingFragmentActivityResults.put(arrayOfInt[i], arrayOfString[i]);
              i++;
              continue;
            } 
            if (this.mPendingFragmentActivityResults == null) {
              this.mPendingFragmentActivityResults = new SparseArrayCompat();
              this.mNextCandidateRequestIndex = 0;
            } 
            this.mFragments.dispatchCreate();
            return;
          } 
        } 
      } 
    } 
    if (this.mPendingFragmentActivityResults == null) {
      this.mPendingFragmentActivityResults = new SparseArrayCompat();
      this.mNextCandidateRequestIndex = 0;
    } 
    this.mFragments.dispatchCreate();
  }
  
  public boolean onCreatePanelMenu(int paramInt, Menu paramMenu) {
    return (paramInt == 0) ? (super.onCreatePanelMenu(paramInt, paramMenu) | this.mFragments.dispatchCreateOptionsMenu(paramMenu, getMenuInflater())) : super.onCreatePanelMenu(paramInt, paramMenu);
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    View view2 = dispatchFragmentsOnCreateView(paramView, paramString, paramContext, paramAttributeSet);
    View view1 = view2;
    if (view2 == null)
      view1 = super.onCreateView(paramView, paramString, paramContext, paramAttributeSet); 
    return view1;
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    View view2 = dispatchFragmentsOnCreateView((View)null, paramString, paramContext, paramAttributeSet);
    View view1 = view2;
    if (view2 == null)
      view1 = super.onCreateView(paramString, paramContext, paramAttributeSet); 
    return view1;
  }
  
  protected void onDestroy() {
    super.onDestroy();
    if (this.mViewModelStore != null && !isChangingConfigurations())
      this.mViewModelStore.clear(); 
    this.mFragments.dispatchDestroy();
  }
  
  public void onLowMemory() {
    super.onLowMemory();
    this.mFragments.dispatchLowMemory();
  }
  
  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem) {
    if (super.onMenuItemSelected(paramInt, paramMenuItem))
      return true; 
    switch (paramInt) {
      default:
        return false;
      case 0:
        return this.mFragments.dispatchOptionsItemSelected(paramMenuItem);
      case 6:
        break;
    } 
    return this.mFragments.dispatchContextItemSelected(paramMenuItem);
  }
  
  @CallSuper
  public void onMultiWindowModeChanged(boolean paramBoolean) {
    this.mFragments.dispatchMultiWindowModeChanged(paramBoolean);
  }
  
  protected void onNewIntent(Intent paramIntent) {
    super.onNewIntent(paramIntent);
    this.mFragments.noteStateNotSaved();
  }
  
  public void onPanelClosed(int paramInt, Menu paramMenu) {
    switch (paramInt) {
      default:
        super.onPanelClosed(paramInt, paramMenu);
        return;
      case 0:
        break;
    } 
    this.mFragments.dispatchOptionsMenuClosed(paramMenu);
  }
  
  protected void onPause() {
    super.onPause();
    this.mResumed = false;
    if (this.mHandler.hasMessages(2)) {
      this.mHandler.removeMessages(2);
      onResumeFragments();
    } 
    this.mFragments.dispatchPause();
  }
  
  @CallSuper
  public void onPictureInPictureModeChanged(boolean paramBoolean) {
    this.mFragments.dispatchPictureInPictureModeChanged(paramBoolean);
  }
  
  protected void onPostResume() {
    super.onPostResume();
    this.mHandler.removeMessages(2);
    onResumeFragments();
    this.mFragments.execPendingActions();
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  protected boolean onPrepareOptionsPanel(View paramView, Menu paramMenu) {
    return super.onPreparePanel(0, paramView, paramMenu);
  }
  
  public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu) {
    return (paramInt == 0 && paramMenu != null) ? (onPrepareOptionsPanel(paramView, paramMenu) | this.mFragments.dispatchPrepareOptionsMenu(paramMenu)) : super.onPreparePanel(paramInt, paramView, paramMenu);
  }
  
  public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfint) {
    String str;
    this.mFragments.noteStateNotSaved();
    int i = paramInt >> 16 & 0xFFFF;
    if (i != 0) {
      str = (String)this.mPendingFragmentActivityResults.get(--i);
      this.mPendingFragmentActivityResults.remove(i);
      if (str == null) {
        Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
        return;
      } 
    } else {
      return;
    } 
    Fragment fragment = this.mFragments.findFragmentByWho(str);
    if (fragment == null) {
      Log.w("FragmentActivity", "Activity result no fragment exists for who: " + str);
      return;
    } 
    fragment.onRequestPermissionsResult(paramInt & 0xFFFF, paramArrayOfString, paramArrayOfint);
  }
  
  protected void onResume() {
    super.onResume();
    this.mHandler.sendEmptyMessage(2);
    this.mResumed = true;
    this.mFragments.execPendingActions();
  }
  
  protected void onResumeFragments() {
    this.mFragments.dispatchResume();
  }
  
  public Object onRetainCustomNonConfigurationInstance() {
    return null;
  }
  
  public final Object onRetainNonConfigurationInstance() {
    Object object = onRetainCustomNonConfigurationInstance();
    FragmentManagerNonConfig fragmentManagerNonConfig = this.mFragments.retainNestedNonConfig();
    if (fragmentManagerNonConfig == null && this.mViewModelStore == null && object == null)
      return null; 
    NonConfigurationInstances nonConfigurationInstances = new NonConfigurationInstances();
    nonConfigurationInstances.custom = object;
    nonConfigurationInstances.viewModelStore = this.mViewModelStore;
    nonConfigurationInstances.fragments = fragmentManagerNonConfig;
    return nonConfigurationInstances;
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    markFragmentsCreated();
    Parcelable parcelable = this.mFragments.saveAllState();
    if (parcelable != null)
      paramBundle.putParcelable("android:support:fragments", parcelable); 
    if (this.mPendingFragmentActivityResults.size() > 0) {
      paramBundle.putInt("android:support:next_request_index", this.mNextCandidateRequestIndex);
      int[] arrayOfInt = new int[this.mPendingFragmentActivityResults.size()];
      String[] arrayOfString = new String[this.mPendingFragmentActivityResults.size()];
      for (int i = 0; i < this.mPendingFragmentActivityResults.size(); i++) {
        arrayOfInt[i] = this.mPendingFragmentActivityResults.keyAt(i);
        arrayOfString[i] = (String)this.mPendingFragmentActivityResults.valueAt(i);
      } 
      paramBundle.putIntArray("android:support:request_indicies", arrayOfInt);
      paramBundle.putStringArray("android:support:request_fragment_who", arrayOfString);
    } 
  }
  
  protected void onStart() {
    super.onStart();
    this.mStopped = false;
    if (!this.mCreated) {
      this.mCreated = true;
      this.mFragments.dispatchActivityCreated();
    } 
    this.mFragments.noteStateNotSaved();
    this.mFragments.execPendingActions();
    this.mFragments.dispatchStart();
  }
  
  public void onStateNotSaved() {
    this.mFragments.noteStateNotSaved();
  }
  
  protected void onStop() {
    super.onStop();
    this.mStopped = true;
    markFragmentsCreated();
    this.mFragments.dispatchStop();
  }
  
  void requestPermissionsFromFragment(Fragment paramFragment, String[] paramArrayOfString, int paramInt) {
    if (paramInt == -1) {
      ActivityCompat.requestPermissions((Activity)this, paramArrayOfString, paramInt);
      return;
    } 
    checkForValidRequestCode(paramInt);
    try {
      this.mRequestedPermissionsFromFragment = true;
      ActivityCompat.requestPermissions((Activity)this, paramArrayOfString, (allocateRequestIndex(paramFragment) + 1 << 16) + (0xFFFF & paramInt));
      return;
    } finally {
      this.mRequestedPermissionsFromFragment = false;
    } 
  }
  
  public void setEnterSharedElementCallback(SharedElementCallback paramSharedElementCallback) {
    ActivityCompat.setEnterSharedElementCallback((Activity)this, paramSharedElementCallback);
  }
  
  public void setExitSharedElementCallback(SharedElementCallback paramSharedElementCallback) {
    ActivityCompat.setExitSharedElementCallback((Activity)this, paramSharedElementCallback);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt) {
    if (!this.mStartedActivityFromFragment && paramInt != -1)
      checkForValidRequestCode(paramInt); 
    super.startActivityForResult(paramIntent, paramInt);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt, @Nullable Bundle paramBundle) {
    if (!this.mStartedActivityFromFragment && paramInt != -1)
      checkForValidRequestCode(paramInt); 
    super.startActivityForResult(paramIntent, paramInt, paramBundle);
  }
  
  public void startActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt) {
    startActivityFromFragment(paramFragment, paramIntent, paramInt, (Bundle)null);
  }
  
  public void startActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, @Nullable Bundle paramBundle) {
    this.mStartedActivityFromFragment = true;
    if (paramInt == -1)
      try {
        ActivityCompat.startActivityForResult((Activity)this, paramIntent, -1, paramBundle);
        return;
      } finally {
        this.mStartedActivityFromFragment = false;
      }  
    checkForValidRequestCode(paramInt);
    ActivityCompat.startActivityForResult((Activity)this, paramIntent, (allocateRequestIndex(paramFragment) + 1 << 16) + (0xFFFF & paramInt), paramBundle);
    this.mStartedActivityFromFragment = false;
  }
  
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, @Nullable Intent paramIntent, int paramInt2, int paramInt3, int paramInt4) throws IntentSender.SendIntentException {
    if (!this.mStartedIntentSenderFromFragment && paramInt1 != -1)
      checkForValidRequestCode(paramInt1); 
    super.startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4);
  }
  
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, @Nullable Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle) throws IntentSender.SendIntentException {
    if (!this.mStartedIntentSenderFromFragment && paramInt1 != -1)
      checkForValidRequestCode(paramInt1); 
    super.startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
  }
  
  public void startIntentSenderFromFragment(Fragment paramFragment, IntentSender paramIntentSender, int paramInt1, @Nullable Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle) throws IntentSender.SendIntentException {
    this.mStartedIntentSenderFromFragment = true;
    if (paramInt1 == -1)
      try {
        ActivityCompat.startIntentSenderForResult((Activity)this, paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
        return;
      } finally {
        this.mStartedIntentSenderFromFragment = false;
      }  
    checkForValidRequestCode(paramInt1);
    ActivityCompat.startIntentSenderForResult((Activity)this, paramIntentSender, (allocateRequestIndex(paramFragment) + 1 << 16) + (0xFFFF & paramInt1), paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
    this.mStartedIntentSenderFromFragment = false;
  }
  
  public void supportFinishAfterTransition() {
    ActivityCompat.finishAfterTransition((Activity)this);
  }
  
  @Deprecated
  public void supportInvalidateOptionsMenu() {
    invalidateOptionsMenu();
  }
  
  public void supportPostponeEnterTransition() {
    ActivityCompat.postponeEnterTransition((Activity)this);
  }
  
  public void supportStartPostponedEnterTransition() {
    ActivityCompat.startPostponedEnterTransition((Activity)this);
  }
  
  public final void validateRequestPermissionsRequestCode(int paramInt) {
    if (!this.mRequestedPermissionsFromFragment && paramInt != -1)
      checkForValidRequestCode(paramInt); 
  }
  
  class HostCallbacks extends FragmentHostCallback<FragmentActivity> {
    public HostCallbacks() {
      super(FragmentActivity.this);
    }
    
    public void onAttachFragment(Fragment param1Fragment) {
      FragmentActivity.this.onAttachFragment(param1Fragment);
    }
    
    public void onDump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      FragmentActivity.this.dump(param1String, param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
    }
    
    @Nullable
    public View onFindViewById(int param1Int) {
      return FragmentActivity.this.findViewById(param1Int);
    }
    
    public FragmentActivity onGetHost() {
      return FragmentActivity.this;
    }
    
    public LayoutInflater onGetLayoutInflater() {
      return FragmentActivity.this.getLayoutInflater().cloneInContext((Context)FragmentActivity.this);
    }
    
    public int onGetWindowAnimations() {
      Window window = FragmentActivity.this.getWindow();
      return (window == null) ? 0 : (window.getAttributes()).windowAnimations;
    }
    
    public boolean onHasView() {
      Window window = FragmentActivity.this.getWindow();
      return (window != null && window.peekDecorView() != null);
    }
    
    public boolean onHasWindowAnimations() {
      return (FragmentActivity.this.getWindow() != null);
    }
    
    public void onRequestPermissionsFromFragment(@NonNull Fragment param1Fragment, @NonNull String[] param1ArrayOfString, int param1Int) {
      FragmentActivity.this.requestPermissionsFromFragment(param1Fragment, param1ArrayOfString, param1Int);
    }
    
    public boolean onShouldSaveFragmentState(Fragment param1Fragment) {
      return !FragmentActivity.this.isFinishing();
    }
    
    public boolean onShouldShowRequestPermissionRationale(@NonNull String param1String) {
      return ActivityCompat.shouldShowRequestPermissionRationale((Activity)FragmentActivity.this, param1String);
    }
    
    public void onStartActivityFromFragment(Fragment param1Fragment, Intent param1Intent, int param1Int) {
      FragmentActivity.this.startActivityFromFragment(param1Fragment, param1Intent, param1Int);
    }
    
    public void onStartActivityFromFragment(Fragment param1Fragment, Intent param1Intent, int param1Int, @Nullable Bundle param1Bundle) {
      FragmentActivity.this.startActivityFromFragment(param1Fragment, param1Intent, param1Int, param1Bundle);
    }
    
    public void onStartIntentSenderFromFragment(Fragment param1Fragment, IntentSender param1IntentSender, int param1Int1, @Nullable Intent param1Intent, int param1Int2, int param1Int3, int param1Int4, Bundle param1Bundle) throws IntentSender.SendIntentException {
      FragmentActivity.this.startIntentSenderFromFragment(param1Fragment, param1IntentSender, param1Int1, param1Intent, param1Int2, param1Int3, param1Int4, param1Bundle);
    }
    
    public void onSupportInvalidateOptionsMenu() {
      FragmentActivity.this.supportInvalidateOptionsMenu();
    }
  }
  
  static final class NonConfigurationInstances {
    Object custom;
    
    FragmentManagerNonConfig fragments;
    
    ViewModelStore viewModelStore;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/fragment/app/FragmentActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
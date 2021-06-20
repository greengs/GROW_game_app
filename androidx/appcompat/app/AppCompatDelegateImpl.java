package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.StandaloneActionMode;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.DecorContentParent;
import androidx.appcompat.widget.FitWindowsViewGroup;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.appcompat.widget.ViewUtils;
import androidx.core.app.NavUtils;
import androidx.core.view.KeyEventDispatcher;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.PopupWindowCompat;
import java.util.List;

class AppCompatDelegateImpl extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
  private static final boolean DEBUG = false;
  
  static final String EXCEPTION_HANDLER_MESSAGE_SUFFIX = ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.";
  
  private static final boolean IS_PRE_LOLLIPOP;
  
  private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
  
  private static boolean sInstalledExceptionHandler;
  
  private static final int[] sWindowBackgroundStyleable = new int[] { 16842836 };
  
  ActionBar mActionBar;
  
  private ActionMenuPresenterCallback mActionMenuPresenterCallback;
  
  ActionMode mActionMode;
  
  PopupWindow mActionModePopup;
  
  ActionBarContextView mActionModeView;
  
  final AppCompatCallback mAppCompatCallback;
  
  private AppCompatViewInflater mAppCompatViewInflater;
  
  final Window.Callback mAppCompatWindowCallback;
  
  private boolean mApplyDayNightCalled;
  
  private AutoNightModeManager mAutoNightModeManager;
  
  private boolean mClosingActionMenu;
  
  final Context mContext;
  
  private DecorContentParent mDecorContentParent;
  
  private boolean mEnableDefaultActionBarUp;
  
  ViewPropertyAnimatorCompat mFadeAnim = null;
  
  private boolean mFeatureIndeterminateProgress;
  
  private boolean mFeatureProgress;
  
  private boolean mHandleNativeActionModes = true;
  
  boolean mHasActionBar;
  
  int mInvalidatePanelMenuFeatures;
  
  boolean mInvalidatePanelMenuPosted;
  
  private final Runnable mInvalidatePanelMenuRunnable = new Runnable() {
      public void run() {
        if ((AppCompatDelegateImpl.this.mInvalidatePanelMenuFeatures & 0x1) != 0)
          AppCompatDelegateImpl.this.doInvalidatePanelMenu(0); 
        if ((AppCompatDelegateImpl.this.mInvalidatePanelMenuFeatures & 0x1000) != 0)
          AppCompatDelegateImpl.this.doInvalidatePanelMenu(108); 
        AppCompatDelegateImpl.this.mInvalidatePanelMenuPosted = false;
        AppCompatDelegateImpl.this.mInvalidatePanelMenuFeatures = 0;
      }
    };
  
  boolean mIsDestroyed;
  
  boolean mIsFloating;
  
  private int mLocalNightMode = -100;
  
  private boolean mLongPressBackDown;
  
  MenuInflater mMenuInflater;
  
  final Window.Callback mOriginalWindowCallback;
  
  boolean mOverlayActionBar;
  
  boolean mOverlayActionMode;
  
  private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
  
  private PanelFeatureState[] mPanels;
  
  private PanelFeatureState mPreparedPanel;
  
  Runnable mShowActionModePopup;
  
  private View mStatusGuard;
  
  private ViewGroup mSubDecor;
  
  private boolean mSubDecorInstalled;
  
  private Rect mTempRect1;
  
  private Rect mTempRect2;
  
  private CharSequence mTitle;
  
  private TextView mTitleView;
  
  final Window mWindow;
  
  boolean mWindowNoTitle;
  
  static {
    if (IS_PRE_LOLLIPOP && !sInstalledExceptionHandler) {
      Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(Thread.getDefaultUncaughtExceptionHandler()) {
            private boolean shouldWrapException(Throwable param1Throwable) {
              boolean bool = false;
              null = bool;
              if (param1Throwable instanceof Resources.NotFoundException) {
                String str = param1Throwable.getMessage();
                null = bool;
                if (str != null) {
                  if (!str.contains("drawable")) {
                    null = bool;
                    return str.contains("Drawable") ? true : null;
                  } 
                } else {
                  return null;
                } 
              } else {
                return null;
              } 
              return true;
            }
            
            public void uncaughtException(Thread param1Thread, Throwable param1Throwable) {
              if (shouldWrapException(param1Throwable)) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException(param1Throwable.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                notFoundException.initCause(param1Throwable.getCause());
                notFoundException.setStackTrace(param1Throwable.getStackTrace());
                defHandler.uncaughtException(param1Thread, (Throwable)notFoundException);
                return;
              } 
              defHandler.uncaughtException(param1Thread, param1Throwable);
            }
          });
      sInstalledExceptionHandler = true;
    } 
  }
  
  AppCompatDelegateImpl(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback) {
    this.mContext = paramContext;
    this.mWindow = paramWindow;
    this.mAppCompatCallback = paramAppCompatCallback;
    this.mOriginalWindowCallback = this.mWindow.getCallback();
    if (this.mOriginalWindowCallback instanceof AppCompatWindowCallback)
      throw new IllegalStateException("AppCompat has already installed itself into the Window"); 
    this.mAppCompatWindowCallback = (Window.Callback)new AppCompatWindowCallback(this.mOriginalWindowCallback);
    this.mWindow.setCallback(this.mAppCompatWindowCallback);
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, null, sWindowBackgroundStyleable);
    Drawable drawable = tintTypedArray.getDrawableIfKnown(0);
    if (drawable != null)
      this.mWindow.setBackgroundDrawable(drawable); 
    tintTypedArray.recycle();
  }
  
  private void applyFixedSizeWindow() {
    ContentFrameLayout contentFrameLayout = (ContentFrameLayout)this.mSubDecor.findViewById(16908290);
    View view = this.mWindow.getDecorView();
    contentFrameLayout.setDecorPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    TypedArray typedArray = this.mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
    typedArray.getValue(R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
    typedArray.getValue(R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
    if (typedArray.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMajor))
      typedArray.getValue(R.styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor()); 
    if (typedArray.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMinor))
      typedArray.getValue(R.styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor()); 
    if (typedArray.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMajor))
      typedArray.getValue(R.styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor()); 
    if (typedArray.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMinor))
      typedArray.getValue(R.styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor()); 
    typedArray.recycle();
    contentFrameLayout.requestLayout();
  }
  
  private ViewGroup createSubDecor() {
    ViewGroup viewGroup1;
    ViewGroup viewGroup2;
    TypedArray typedArray = this.mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
    if (!typedArray.hasValue(R.styleable.AppCompatTheme_windowActionBar)) {
      typedArray.recycle();
      throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    } 
    if (typedArray.getBoolean(R.styleable.AppCompatTheme_windowNoTitle, false)) {
      requestWindowFeature(1);
    } else if (typedArray.getBoolean(R.styleable.AppCompatTheme_windowActionBar, false)) {
      requestWindowFeature(108);
    } 
    if (typedArray.getBoolean(R.styleable.AppCompatTheme_windowActionBarOverlay, false))
      requestWindowFeature(109); 
    if (typedArray.getBoolean(R.styleable.AppCompatTheme_windowActionModeOverlay, false))
      requestWindowFeature(10); 
    this.mIsFloating = typedArray.getBoolean(R.styleable.AppCompatTheme_android_windowIsFloating, false);
    typedArray.recycle();
    this.mWindow.getDecorView();
    LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
    typedArray = null;
    if (!this.mWindowNoTitle) {
      if (this.mIsFloating) {
        viewGroup1 = (ViewGroup)layoutInflater.inflate(R.layout.abc_dialog_title_material, null);
        this.mOverlayActionBar = false;
        this.mHasActionBar = false;
      } else if (this.mHasActionBar) {
        Context context;
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R.attr.actionBarTheme, typedValue, true);
        if (typedValue.resourceId != 0) {
          ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, typedValue.resourceId);
        } else {
          context = this.mContext;
        } 
        viewGroup2 = (ViewGroup)LayoutInflater.from(context).inflate(R.layout.abc_screen_toolbar, null);
        this.mDecorContentParent = (DecorContentParent)viewGroup2.findViewById(R.id.decor_content_parent);
        this.mDecorContentParent.setWindowCallback(getWindowCallback());
        if (this.mOverlayActionBar)
          this.mDecorContentParent.initFeature(109); 
        if (this.mFeatureProgress)
          this.mDecorContentParent.initFeature(2); 
        viewGroup1 = viewGroup2;
        if (this.mFeatureIndeterminateProgress) {
          this.mDecorContentParent.initFeature(5);
          viewGroup1 = viewGroup2;
        } 
      } 
    } else {
      if (this.mOverlayActionMode) {
        viewGroup1 = (ViewGroup)viewGroup2.inflate(R.layout.abc_screen_simple_overlay_action_mode, null);
      } else {
        viewGroup1 = (ViewGroup)viewGroup2.inflate(R.layout.abc_screen_simple, null);
      } 
      if (Build.VERSION.SDK_INT >= 21) {
        ViewCompat.setOnApplyWindowInsetsListener((View)viewGroup1, new OnApplyWindowInsetsListener() {
              public WindowInsetsCompat onApplyWindowInsets(View param1View, WindowInsetsCompat param1WindowInsetsCompat) {
                int i = param1WindowInsetsCompat.getSystemWindowInsetTop();
                int j = AppCompatDelegateImpl.this.updateStatusGuard(i);
                WindowInsetsCompat windowInsetsCompat = param1WindowInsetsCompat;
                if (i != j)
                  windowInsetsCompat = param1WindowInsetsCompat.replaceSystemWindowInsets(param1WindowInsetsCompat.getSystemWindowInsetLeft(), j, param1WindowInsetsCompat.getSystemWindowInsetRight(), param1WindowInsetsCompat.getSystemWindowInsetBottom()); 
                return ViewCompat.onApplyWindowInsets(param1View, windowInsetsCompat);
              }
            });
      } else {
        ((FitWindowsViewGroup)viewGroup1).setOnFitSystemWindowsListener(new FitWindowsViewGroup.OnFitSystemWindowsListener() {
              public void onFitSystemWindows(Rect param1Rect) {
                param1Rect.top = AppCompatDelegateImpl.this.updateStatusGuard(param1Rect.top);
              }
            });
      } 
    } 
    if (viewGroup1 == null)
      throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.mHasActionBar + ", windowActionBarOverlay: " + this.mOverlayActionBar + ", android:windowIsFloating: " + this.mIsFloating + ", windowActionModeOverlay: " + this.mOverlayActionMode + ", windowNoTitle: " + this.mWindowNoTitle + " }"); 
    if (this.mDecorContentParent == null)
      this.mTitleView = (TextView)viewGroup1.findViewById(R.id.title); 
    ViewUtils.makeOptionalFitsSystemWindows((View)viewGroup1);
    ContentFrameLayout contentFrameLayout = (ContentFrameLayout)viewGroup1.findViewById(R.id.action_bar_activity_content);
    ViewGroup viewGroup3 = (ViewGroup)this.mWindow.findViewById(16908290);
    if (viewGroup3 != null) {
      while (viewGroup3.getChildCount() > 0) {
        View view = viewGroup3.getChildAt(0);
        viewGroup3.removeViewAt(0);
        contentFrameLayout.addView(view);
      } 
      viewGroup3.setId(-1);
      contentFrameLayout.setId(16908290);
      if (viewGroup3 instanceof FrameLayout)
        ((FrameLayout)viewGroup3).setForeground(null); 
    } 
    this.mWindow.setContentView((View)viewGroup1);
    contentFrameLayout.setAttachListener(new ContentFrameLayout.OnAttachListener() {
          public void onAttachedFromWindow() {}
          
          public void onDetachedFromWindow() {
            AppCompatDelegateImpl.this.dismissPopups();
          }
        });
    return viewGroup1;
  }
  
  private void ensureAutoNightModeManager() {
    if (this.mAutoNightModeManager == null)
      this.mAutoNightModeManager = new AutoNightModeManager(TwilightManager.getInstance(this.mContext)); 
  }
  
  private void ensureSubDecor() {
    if (!this.mSubDecorInstalled) {
      this.mSubDecor = createSubDecor();
      CharSequence charSequence = getTitle();
      if (!TextUtils.isEmpty(charSequence))
        if (this.mDecorContentParent != null) {
          this.mDecorContentParent.setWindowTitle(charSequence);
        } else if (peekSupportActionBar() != null) {
          peekSupportActionBar().setWindowTitle(charSequence);
        } else if (this.mTitleView != null) {
          this.mTitleView.setText(charSequence);
        }  
      applyFixedSizeWindow();
      onSubDecorInstalled(this.mSubDecor);
      this.mSubDecorInstalled = true;
      PanelFeatureState panelFeatureState = getPanelState(0, false);
      if (!this.mIsDestroyed && (panelFeatureState == null || panelFeatureState.menu == null))
        invalidatePanelMenu(108); 
    } 
  }
  
  private int getNightMode() {
    return (this.mLocalNightMode != -100) ? this.mLocalNightMode : getDefaultNightMode();
  }
  
  private void initWindowDecorActionBar() {
    ensureSubDecor();
    if (this.mHasActionBar && this.mActionBar == null) {
      if (this.mOriginalWindowCallback instanceof Activity) {
        this.mActionBar = new WindowDecorActionBar((Activity)this.mOriginalWindowCallback, this.mOverlayActionBar);
      } else if (this.mOriginalWindowCallback instanceof Dialog) {
        this.mActionBar = new WindowDecorActionBar((Dialog)this.mOriginalWindowCallback);
      } 
      if (this.mActionBar != null) {
        this.mActionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
        return;
      } 
    } 
  }
  
  private boolean initializePanelContent(PanelFeatureState paramPanelFeatureState) {
    if (paramPanelFeatureState.createdPanelView != null) {
      paramPanelFeatureState.shownPanelView = paramPanelFeatureState.createdPanelView;
      return true;
    } 
    if (paramPanelFeatureState.menu == null)
      return false; 
    if (this.mPanelMenuPresenterCallback == null)
      this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback(); 
    paramPanelFeatureState.shownPanelView = (View)paramPanelFeatureState.getListMenuView(this.mPanelMenuPresenterCallback);
    return !(paramPanelFeatureState.shownPanelView == null);
  }
  
  private boolean initializePanelDecor(PanelFeatureState paramPanelFeatureState) {
    paramPanelFeatureState.setStyle(getActionBarThemedContext());
    paramPanelFeatureState.decorView = (ViewGroup)new ListMenuDecorView(paramPanelFeatureState.listPresenterContext);
    paramPanelFeatureState.gravity = 81;
    return true;
  }
  
  private boolean initializePanelMenu(PanelFeatureState paramPanelFeatureState) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mContext : Landroid/content/Context;
    //   4: astore #4
    //   6: aload_1
    //   7: getfield featureId : I
    //   10: ifeq -> 25
    //   13: aload #4
    //   15: astore_2
    //   16: aload_1
    //   17: getfield featureId : I
    //   20: bipush #108
    //   22: if_icmpne -> 176
    //   25: aload #4
    //   27: astore_2
    //   28: aload_0
    //   29: getfield mDecorContentParent : Landroidx/appcompat/widget/DecorContentParent;
    //   32: ifnull -> 176
    //   35: new android/util/TypedValue
    //   38: dup
    //   39: invokespecial <init> : ()V
    //   42: astore #5
    //   44: aload #4
    //   46: invokevirtual getTheme : ()Landroid/content/res/Resources$Theme;
    //   49: astore #6
    //   51: aload #6
    //   53: getstatic androidx/appcompat/R$attr.actionBarTheme : I
    //   56: aload #5
    //   58: iconst_1
    //   59: invokevirtual resolveAttribute : (ILandroid/util/TypedValue;Z)Z
    //   62: pop
    //   63: aconst_null
    //   64: astore_2
    //   65: aload #5
    //   67: getfield resourceId : I
    //   70: ifeq -> 197
    //   73: aload #4
    //   75: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   78: invokevirtual newTheme : ()Landroid/content/res/Resources$Theme;
    //   81: astore_2
    //   82: aload_2
    //   83: aload #6
    //   85: invokevirtual setTo : (Landroid/content/res/Resources$Theme;)V
    //   88: aload_2
    //   89: aload #5
    //   91: getfield resourceId : I
    //   94: iconst_1
    //   95: invokevirtual applyStyle : (IZ)V
    //   98: aload_2
    //   99: getstatic androidx/appcompat/R$attr.actionBarWidgetTheme : I
    //   102: aload #5
    //   104: iconst_1
    //   105: invokevirtual resolveAttribute : (ILandroid/util/TypedValue;Z)Z
    //   108: pop
    //   109: aload_2
    //   110: astore_3
    //   111: aload #5
    //   113: getfield resourceId : I
    //   116: ifeq -> 150
    //   119: aload_2
    //   120: astore_3
    //   121: aload_2
    //   122: ifnonnull -> 140
    //   125: aload #4
    //   127: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   130: invokevirtual newTheme : ()Landroid/content/res/Resources$Theme;
    //   133: astore_3
    //   134: aload_3
    //   135: aload #6
    //   137: invokevirtual setTo : (Landroid/content/res/Resources$Theme;)V
    //   140: aload_3
    //   141: aload #5
    //   143: getfield resourceId : I
    //   146: iconst_1
    //   147: invokevirtual applyStyle : (IZ)V
    //   150: aload #4
    //   152: astore_2
    //   153: aload_3
    //   154: ifnull -> 176
    //   157: new androidx/appcompat/view/ContextThemeWrapper
    //   160: dup
    //   161: aload #4
    //   163: iconst_0
    //   164: invokespecial <init> : (Landroid/content/Context;I)V
    //   167: astore_2
    //   168: aload_2
    //   169: invokevirtual getTheme : ()Landroid/content/res/Resources$Theme;
    //   172: aload_3
    //   173: invokevirtual setTo : (Landroid/content/res/Resources$Theme;)V
    //   176: new androidx/appcompat/view/menu/MenuBuilder
    //   179: dup
    //   180: aload_2
    //   181: invokespecial <init> : (Landroid/content/Context;)V
    //   184: astore_2
    //   185: aload_2
    //   186: aload_0
    //   187: invokevirtual setCallback : (Landroidx/appcompat/view/menu/MenuBuilder$Callback;)V
    //   190: aload_1
    //   191: aload_2
    //   192: invokevirtual setMenu : (Landroidx/appcompat/view/menu/MenuBuilder;)V
    //   195: iconst_1
    //   196: ireturn
    //   197: aload #6
    //   199: getstatic androidx/appcompat/R$attr.actionBarWidgetTheme : I
    //   202: aload #5
    //   204: iconst_1
    //   205: invokevirtual resolveAttribute : (ILandroid/util/TypedValue;Z)Z
    //   208: pop
    //   209: goto -> 109
  }
  
  private void invalidatePanelMenu(int paramInt) {
    this.mInvalidatePanelMenuFeatures |= 1 << paramInt;
    if (!this.mInvalidatePanelMenuPosted) {
      ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
      this.mInvalidatePanelMenuPosted = true;
    } 
  }
  
  private boolean onKeyDownPanel(int paramInt, KeyEvent paramKeyEvent) {
    if (paramKeyEvent.getRepeatCount() == 0) {
      PanelFeatureState panelFeatureState = getPanelState(paramInt, true);
      if (!panelFeatureState.isOpen)
        return preparePanel(panelFeatureState, paramKeyEvent); 
    } 
    return false;
  }
  
  private boolean onKeyUpPanel(int paramInt, KeyEvent paramKeyEvent) {
    boolean bool1;
    if (this.mActionMode != null)
      return false; 
    boolean bool3 = false;
    PanelFeatureState panelFeatureState = getPanelState(paramInt, true);
    if (paramInt == 0 && this.mDecorContentParent != null && this.mDecorContentParent.canShowOverflowMenu() && !ViewConfiguration.get(this.mContext).hasPermanentMenuKey()) {
      if (!this.mDecorContentParent.isOverflowMenuShowing()) {
        bool1 = bool3;
        if (!this.mIsDestroyed) {
          bool1 = bool3;
          if (preparePanel(panelFeatureState, paramKeyEvent))
            bool1 = this.mDecorContentParent.showOverflowMenu(); 
        } 
      } else {
        bool1 = this.mDecorContentParent.hideOverflowMenu();
      } 
    } else if (panelFeatureState.isOpen || panelFeatureState.isHandled) {
      bool1 = panelFeatureState.isOpen;
      closePanel(panelFeatureState, true);
    } else {
      bool1 = bool3;
      if (panelFeatureState.isPrepared) {
        boolean bool = true;
        if (panelFeatureState.refreshMenuContent) {
          panelFeatureState.isPrepared = false;
          bool = preparePanel(panelFeatureState, paramKeyEvent);
        } 
        bool1 = bool3;
        if (bool) {
          openPanel(panelFeatureState, paramKeyEvent);
          bool1 = true;
        } 
      } 
    } 
    boolean bool2 = bool1;
    if (bool1) {
      AudioManager audioManager = (AudioManager)this.mContext.getSystemService("audio");
      if (audioManager != null) {
        audioManager.playSoundEffect(0);
        return bool1;
      } 
      Log.w("AppCompatDelegate", "Couldn't get audio manager");
      return bool1;
    } 
    return bool2;
  }
  
  private void openPanel(PanelFeatureState paramPanelFeatureState, KeyEvent paramKeyEvent) {
    // Byte code:
    //   0: aload_1
    //   1: getfield isOpen : Z
    //   4: ifne -> 14
    //   7: aload_0
    //   8: getfield mIsDestroyed : Z
    //   11: ifeq -> 15
    //   14: return
    //   15: aload_1
    //   16: getfield featureId : I
    //   19: ifne -> 48
    //   22: aload_0
    //   23: getfield mContext : Landroid/content/Context;
    //   26: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   29: invokevirtual getConfiguration : ()Landroid/content/res/Configuration;
    //   32: getfield screenLayout : I
    //   35: bipush #15
    //   37: iand
    //   38: iconst_4
    //   39: if_icmpne -> 84
    //   42: iconst_1
    //   43: istore_3
    //   44: iload_3
    //   45: ifne -> 14
    //   48: aload_0
    //   49: invokevirtual getWindowCallback : ()Landroid/view/Window$Callback;
    //   52: astore #5
    //   54: aload #5
    //   56: ifnull -> 89
    //   59: aload #5
    //   61: aload_1
    //   62: getfield featureId : I
    //   65: aload_1
    //   66: getfield menu : Landroidx/appcompat/view/menu/MenuBuilder;
    //   69: invokeinterface onMenuOpened : (ILandroid/view/Menu;)Z
    //   74: ifne -> 89
    //   77: aload_0
    //   78: aload_1
    //   79: iconst_1
    //   80: invokevirtual closePanel : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;Z)V
    //   83: return
    //   84: iconst_0
    //   85: istore_3
    //   86: goto -> 44
    //   89: aload_0
    //   90: getfield mContext : Landroid/content/Context;
    //   93: ldc_w 'window'
    //   96: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   99: checkcast android/view/WindowManager
    //   102: astore #6
    //   104: aload #6
    //   106: ifnull -> 14
    //   109: aload_0
    //   110: aload_1
    //   111: aload_2
    //   112: invokespecial preparePanel : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;Landroid/view/KeyEvent;)Z
    //   115: ifeq -> 14
    //   118: bipush #-2
    //   120: istore #4
    //   122: aload_1
    //   123: getfield decorView : Landroid/view/ViewGroup;
    //   126: ifnull -> 136
    //   129: aload_1
    //   130: getfield refreshDecorView : Z
    //   133: ifeq -> 378
    //   136: aload_1
    //   137: getfield decorView : Landroid/view/ViewGroup;
    //   140: ifnonnull -> 351
    //   143: aload_0
    //   144: aload_1
    //   145: invokespecial initializePanelDecor : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;)Z
    //   148: ifeq -> 14
    //   151: aload_1
    //   152: getfield decorView : Landroid/view/ViewGroup;
    //   155: ifnull -> 14
    //   158: aload_0
    //   159: aload_1
    //   160: invokespecial initializePanelContent : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;)Z
    //   163: ifeq -> 14
    //   166: aload_1
    //   167: invokevirtual hasPanelItems : ()Z
    //   170: ifeq -> 14
    //   173: aload_1
    //   174: getfield shownPanelView : Landroid/view/View;
    //   177: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   180: astore #5
    //   182: aload #5
    //   184: astore_2
    //   185: aload #5
    //   187: ifnonnull -> 202
    //   190: new android/view/ViewGroup$LayoutParams
    //   193: dup
    //   194: bipush #-2
    //   196: bipush #-2
    //   198: invokespecial <init> : (II)V
    //   201: astore_2
    //   202: aload_1
    //   203: getfield background : I
    //   206: istore_3
    //   207: aload_1
    //   208: getfield decorView : Landroid/view/ViewGroup;
    //   211: iload_3
    //   212: invokevirtual setBackgroundResource : (I)V
    //   215: aload_1
    //   216: getfield shownPanelView : Landroid/view/View;
    //   219: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   222: astore #5
    //   224: aload #5
    //   226: ifnull -> 249
    //   229: aload #5
    //   231: instanceof android/view/ViewGroup
    //   234: ifeq -> 249
    //   237: aload #5
    //   239: checkcast android/view/ViewGroup
    //   242: aload_1
    //   243: getfield shownPanelView : Landroid/view/View;
    //   246: invokevirtual removeView : (Landroid/view/View;)V
    //   249: aload_1
    //   250: getfield decorView : Landroid/view/ViewGroup;
    //   253: aload_1
    //   254: getfield shownPanelView : Landroid/view/View;
    //   257: aload_2
    //   258: invokevirtual addView : (Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   261: iload #4
    //   263: istore_3
    //   264: aload_1
    //   265: getfield shownPanelView : Landroid/view/View;
    //   268: invokevirtual hasFocus : ()Z
    //   271: ifne -> 285
    //   274: aload_1
    //   275: getfield shownPanelView : Landroid/view/View;
    //   278: invokevirtual requestFocus : ()Z
    //   281: pop
    //   282: iload #4
    //   284: istore_3
    //   285: aload_1
    //   286: iconst_0
    //   287: putfield isHandled : Z
    //   290: new android/view/WindowManager$LayoutParams
    //   293: dup
    //   294: iload_3
    //   295: bipush #-2
    //   297: aload_1
    //   298: getfield x : I
    //   301: aload_1
    //   302: getfield y : I
    //   305: sipush #1002
    //   308: ldc_w 8519680
    //   311: bipush #-3
    //   313: invokespecial <init> : (IIIIIII)V
    //   316: astore_2
    //   317: aload_2
    //   318: aload_1
    //   319: getfield gravity : I
    //   322: putfield gravity : I
    //   325: aload_2
    //   326: aload_1
    //   327: getfield windowAnimations : I
    //   330: putfield windowAnimations : I
    //   333: aload #6
    //   335: aload_1
    //   336: getfield decorView : Landroid/view/ViewGroup;
    //   339: aload_2
    //   340: invokeinterface addView : (Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   345: aload_1
    //   346: iconst_1
    //   347: putfield isOpen : Z
    //   350: return
    //   351: aload_1
    //   352: getfield refreshDecorView : Z
    //   355: ifeq -> 158
    //   358: aload_1
    //   359: getfield decorView : Landroid/view/ViewGroup;
    //   362: invokevirtual getChildCount : ()I
    //   365: ifle -> 158
    //   368: aload_1
    //   369: getfield decorView : Landroid/view/ViewGroup;
    //   372: invokevirtual removeAllViews : ()V
    //   375: goto -> 158
    //   378: iload #4
    //   380: istore_3
    //   381: aload_1
    //   382: getfield createdPanelView : Landroid/view/View;
    //   385: ifnull -> 285
    //   388: aload_1
    //   389: getfield createdPanelView : Landroid/view/View;
    //   392: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   395: astore_2
    //   396: iload #4
    //   398: istore_3
    //   399: aload_2
    //   400: ifnull -> 285
    //   403: iload #4
    //   405: istore_3
    //   406: aload_2
    //   407: getfield width : I
    //   410: iconst_m1
    //   411: if_icmpne -> 285
    //   414: iconst_m1
    //   415: istore_3
    //   416: goto -> 285
  }
  
  private boolean performPanelShortcut(PanelFeatureState paramPanelFeatureState, int paramInt1, KeyEvent paramKeyEvent, int paramInt2) {
    // Byte code:
    //   0: aload_3
    //   1: invokevirtual isSystem : ()Z
    //   4: ifeq -> 13
    //   7: iconst_0
    //   8: istore #6
    //   10: iload #6
    //   12: ireturn
    //   13: iconst_0
    //   14: istore #6
    //   16: aload_1
    //   17: getfield isPrepared : Z
    //   20: ifne -> 36
    //   23: iload #6
    //   25: istore #5
    //   27: aload_0
    //   28: aload_1
    //   29: aload_3
    //   30: invokespecial preparePanel : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;Landroid/view/KeyEvent;)Z
    //   33: ifeq -> 60
    //   36: iload #6
    //   38: istore #5
    //   40: aload_1
    //   41: getfield menu : Landroidx/appcompat/view/menu/MenuBuilder;
    //   44: ifnull -> 60
    //   47: aload_1
    //   48: getfield menu : Landroidx/appcompat/view/menu/MenuBuilder;
    //   51: iload_2
    //   52: aload_3
    //   53: iload #4
    //   55: invokevirtual performShortcut : (ILandroid/view/KeyEvent;I)Z
    //   58: istore #5
    //   60: iload #5
    //   62: istore #6
    //   64: iload #5
    //   66: ifeq -> 10
    //   69: iload #5
    //   71: istore #6
    //   73: iload #4
    //   75: iconst_1
    //   76: iand
    //   77: ifne -> 10
    //   80: iload #5
    //   82: istore #6
    //   84: aload_0
    //   85: getfield mDecorContentParent : Landroidx/appcompat/widget/DecorContentParent;
    //   88: ifnonnull -> 10
    //   91: aload_0
    //   92: aload_1
    //   93: iconst_1
    //   94: invokevirtual closePanel : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;Z)V
    //   97: iload #5
    //   99: ireturn
  }
  
  private boolean preparePanel(PanelFeatureState paramPanelFeatureState, KeyEvent paramKeyEvent) {
    if (!this.mIsDestroyed) {
      int i;
      if (paramPanelFeatureState.isPrepared)
        return true; 
      if (this.mPreparedPanel != null && this.mPreparedPanel != paramPanelFeatureState)
        closePanel(this.mPreparedPanel, false); 
      Window.Callback callback = getWindowCallback();
      if (callback != null)
        paramPanelFeatureState.createdPanelView = callback.onCreatePanelView(paramPanelFeatureState.featureId); 
      if (paramPanelFeatureState.featureId == 0 || paramPanelFeatureState.featureId == 108) {
        i = 1;
      } else {
        i = 0;
      } 
      if (i && this.mDecorContentParent != null)
        this.mDecorContentParent.setMenuPrepared(); 
      if (paramPanelFeatureState.createdPanelView == null && (!i || !(peekSupportActionBar() instanceof ToolbarActionBar))) {
        boolean bool;
        if (paramPanelFeatureState.menu == null || paramPanelFeatureState.refreshMenuContent)
          if (paramPanelFeatureState.menu != null || (initializePanelMenu(paramPanelFeatureState) && paramPanelFeatureState.menu != null)) {
            if (i && this.mDecorContentParent != null) {
              if (this.mActionMenuPresenterCallback == null)
                this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback(); 
              this.mDecorContentParent.setMenu((Menu)paramPanelFeatureState.menu, this.mActionMenuPresenterCallback);
            } 
            paramPanelFeatureState.menu.stopDispatchingItemsChanged();
            if (!callback.onCreatePanelMenu(paramPanelFeatureState.featureId, (Menu)paramPanelFeatureState.menu)) {
              paramPanelFeatureState.setMenu(null);
              if (i && this.mDecorContentParent != null) {
                this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
                return false;
              } 
              return false;
            } 
            paramPanelFeatureState.refreshMenuContent = false;
          } else {
            return false;
          }  
        paramPanelFeatureState.menu.stopDispatchingItemsChanged();
        if (paramPanelFeatureState.frozenActionViewState != null) {
          paramPanelFeatureState.menu.restoreActionViewStates(paramPanelFeatureState.frozenActionViewState);
          paramPanelFeatureState.frozenActionViewState = null;
        } 
        if (!callback.onPreparePanel(0, paramPanelFeatureState.createdPanelView, (Menu)paramPanelFeatureState.menu)) {
          if (i && this.mDecorContentParent != null)
            this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback); 
          paramPanelFeatureState.menu.startDispatchingItemsChanged();
          return false;
        } 
        if (paramKeyEvent != null) {
          i = paramKeyEvent.getDeviceId();
        } else {
          i = -1;
        } 
        if (KeyCharacterMap.load(i).getKeyboardType() != 1) {
          bool = true;
        } else {
          bool = false;
        } 
        paramPanelFeatureState.qwertyMode = bool;
        paramPanelFeatureState.menu.setQwertyMode(paramPanelFeatureState.qwertyMode);
        paramPanelFeatureState.menu.startDispatchingItemsChanged();
      } 
      paramPanelFeatureState.isPrepared = true;
      paramPanelFeatureState.isHandled = false;
      this.mPreparedPanel = paramPanelFeatureState;
      return true;
    } 
    return false;
  }
  
  private void reopenMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {
    if (this.mDecorContentParent != null && this.mDecorContentParent.canShowOverflowMenu() && (!ViewConfiguration.get(this.mContext).hasPermanentMenuKey() || this.mDecorContentParent.isOverflowMenuShowPending())) {
      Window.Callback callback = getWindowCallback();
      if (!this.mDecorContentParent.isOverflowMenuShowing() || !paramBoolean) {
        if (callback != null && !this.mIsDestroyed) {
          if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 0x1) != 0) {
            this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuRunnable.run();
          } 
          PanelFeatureState panelFeatureState1 = getPanelState(0, true);
          if (panelFeatureState1.menu != null && !panelFeatureState1.refreshMenuContent && callback.onPreparePanel(0, panelFeatureState1.createdPanelView, (Menu)panelFeatureState1.menu)) {
            callback.onMenuOpened(108, (Menu)panelFeatureState1.menu);
            this.mDecorContentParent.showOverflowMenu();
          } 
        } 
        return;
      } 
      this.mDecorContentParent.hideOverflowMenu();
      if (!this.mIsDestroyed) {
        callback.onPanelClosed(108, (Menu)(getPanelState(0, true)).menu);
        return;
      } 
      return;
    } 
    PanelFeatureState panelFeatureState = getPanelState(0, true);
    panelFeatureState.refreshDecorView = true;
    closePanel(panelFeatureState, false);
    openPanel(panelFeatureState, null);
  }
  
  private int sanitizeWindowFeatureId(int paramInt) {
    if (paramInt == 8) {
      Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
      return 108;
    } 
    int i = paramInt;
    if (paramInt == 9) {
      Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
      return 109;
    } 
    return i;
  }
  
  private boolean shouldInheritContext(ViewParent paramViewParent) {
    if (paramViewParent == null)
      return false; 
    View view = this.mWindow.getDecorView();
    while (true) {
      if (paramViewParent == null)
        return true; 
      if (paramViewParent == view || !(paramViewParent instanceof View) || ViewCompat.isAttachedToWindow((View)paramViewParent))
        return false; 
      paramViewParent = paramViewParent.getParent();
    } 
  }
  
  private boolean shouldRecreateOnNightModeChange() {
    if (this.mApplyDayNightCalled && this.mContext instanceof Activity) {
      PackageManager packageManager = this.mContext.getPackageManager();
      try {
        int i = (packageManager.getActivityInfo(new ComponentName(this.mContext, this.mContext.getClass()), 0)).configChanges;
        return ((i & 0x200) == 0);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", (Throwable)nameNotFoundException);
        return true;
      } 
    } 
    return false;
  }
  
  private void throwFeatureRequestIfSubDecorInstalled() {
    if (this.mSubDecorInstalled)
      throw new AndroidRuntimeException("Window feature must be requested before adding content"); 
  }
  
  private boolean updateForNightMode(int paramInt) {
    Resources resources = this.mContext.getResources();
    Configuration configuration = resources.getConfiguration();
    int i = configuration.uiMode;
    if (paramInt == 2) {
      paramInt = 32;
    } else {
      paramInt = 16;
    } 
    if ((i & 0x30) != paramInt) {
      if (shouldRecreateOnNightModeChange()) {
        ((Activity)this.mContext).recreate();
        return true;
      } 
      configuration = new Configuration(configuration);
      DisplayMetrics displayMetrics = resources.getDisplayMetrics();
      configuration.uiMode = configuration.uiMode & 0xFFFFFFCF | paramInt;
      resources.updateConfiguration(configuration, displayMetrics);
      if (Build.VERSION.SDK_INT < 26)
        ResourcesFlusher.flush(resources); 
      return true;
    } 
    return false;
  }
  
  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    ensureSubDecor();
    ((ViewGroup)this.mSubDecor.findViewById(16908290)).addView(paramView, paramLayoutParams);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public boolean applyDayNight() {
    boolean bool = false;
    int i = getNightMode();
    int j = mapNightMode(i);
    if (j != -1)
      bool = updateForNightMode(j); 
    if (i == 0) {
      ensureAutoNightModeManager();
      this.mAutoNightModeManager.setup();
    } 
    this.mApplyDayNightCalled = true;
    return bool;
  }
  
  void callOnPanelClosed(int paramInt, PanelFeatureState paramPanelFeatureState, Menu paramMenu) {
    MenuBuilder menuBuilder;
    PanelFeatureState panelFeatureState = paramPanelFeatureState;
    Menu menu = paramMenu;
    if (paramMenu == null) {
      PanelFeatureState panelFeatureState1 = paramPanelFeatureState;
      if (paramPanelFeatureState == null) {
        panelFeatureState1 = paramPanelFeatureState;
        if (paramInt >= 0) {
          panelFeatureState1 = paramPanelFeatureState;
          if (paramInt < this.mPanels.length)
            panelFeatureState1 = this.mPanels[paramInt]; 
        } 
      } 
      panelFeatureState = panelFeatureState1;
      menu = paramMenu;
      if (panelFeatureState1 != null) {
        menuBuilder = panelFeatureState1.menu;
        panelFeatureState = panelFeatureState1;
      } 
    } 
    if ((panelFeatureState == null || panelFeatureState.isOpen) && !this.mIsDestroyed) {
      this.mOriginalWindowCallback.onPanelClosed(paramInt, (Menu)menuBuilder);
      return;
    } 
  }
  
  void checkCloseActionMenu(MenuBuilder paramMenuBuilder) {
    if (this.mClosingActionMenu)
      return; 
    this.mClosingActionMenu = true;
    this.mDecorContentParent.dismissPopups();
    Window.Callback callback = getWindowCallback();
    if (callback != null && !this.mIsDestroyed)
      callback.onPanelClosed(108, (Menu)paramMenuBuilder); 
    this.mClosingActionMenu = false;
  }
  
  void closePanel(int paramInt) {
    closePanel(getPanelState(paramInt, true), true);
  }
  
  void closePanel(PanelFeatureState paramPanelFeatureState, boolean paramBoolean) {
    if (paramBoolean && paramPanelFeatureState.featureId == 0 && this.mDecorContentParent != null && this.mDecorContentParent.isOverflowMenuShowing()) {
      checkCloseActionMenu(paramPanelFeatureState.menu);
      return;
    } 
    WindowManager windowManager = (WindowManager)this.mContext.getSystemService("window");
    if (windowManager != null && paramPanelFeatureState.isOpen && paramPanelFeatureState.decorView != null) {
      windowManager.removeView((View)paramPanelFeatureState.decorView);
      if (paramBoolean)
        callOnPanelClosed(paramPanelFeatureState.featureId, paramPanelFeatureState, null); 
    } 
    paramPanelFeatureState.isPrepared = false;
    paramPanelFeatureState.isHandled = false;
    paramPanelFeatureState.isOpen = false;
    paramPanelFeatureState.shownPanelView = null;
    paramPanelFeatureState.refreshDecorView = true;
    if (this.mPreparedPanel == paramPanelFeatureState) {
      this.mPreparedPanel = null;
      return;
    } 
  }
  
  public View createView(View paramView, String paramString, @NonNull Context paramContext, @NonNull AttributeSet paramAttributeSet) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mAppCompatViewInflater : Landroidx/appcompat/app/AppCompatViewInflater;
    //   4: ifnonnull -> 55
    //   7: aload_0
    //   8: getfield mContext : Landroid/content/Context;
    //   11: getstatic androidx/appcompat/R$styleable.AppCompatTheme : [I
    //   14: invokevirtual obtainStyledAttributes : ([I)Landroid/content/res/TypedArray;
    //   17: getstatic androidx/appcompat/R$styleable.AppCompatTheme_viewInflaterClass : I
    //   20: invokevirtual getString : (I)Ljava/lang/String;
    //   23: astore #6
    //   25: aload #6
    //   27: ifnull -> 44
    //   30: ldc_w androidx/appcompat/app/AppCompatViewInflater
    //   33: invokevirtual getName : ()Ljava/lang/String;
    //   36: aload #6
    //   38: invokevirtual equals : (Ljava/lang/Object;)Z
    //   41: ifeq -> 111
    //   44: aload_0
    //   45: new androidx/appcompat/app/AppCompatViewInflater
    //   48: dup
    //   49: invokespecial <init> : ()V
    //   52: putfield mAppCompatViewInflater : Landroidx/appcompat/app/AppCompatViewInflater;
    //   55: iconst_0
    //   56: istore #5
    //   58: getstatic androidx/appcompat/app/AppCompatDelegateImpl.IS_PRE_LOLLIPOP : Z
    //   61: ifeq -> 89
    //   64: aload #4
    //   66: instanceof org/xmlpull/v1/XmlPullParser
    //   69: ifeq -> 198
    //   72: aload #4
    //   74: checkcast org/xmlpull/v1/XmlPullParser
    //   77: invokeinterface getDepth : ()I
    //   82: iconst_1
    //   83: if_icmple -> 192
    //   86: iconst_1
    //   87: istore #5
    //   89: aload_0
    //   90: getfield mAppCompatViewInflater : Landroidx/appcompat/app/AppCompatViewInflater;
    //   93: aload_1
    //   94: aload_2
    //   95: aload_3
    //   96: aload #4
    //   98: iload #5
    //   100: getstatic androidx/appcompat/app/AppCompatDelegateImpl.IS_PRE_LOLLIPOP : Z
    //   103: iconst_1
    //   104: invokestatic shouldBeUsed : ()Z
    //   107: invokevirtual createView : (Landroid/view/View;Ljava/lang/String;Landroid/content/Context;Landroid/util/AttributeSet;ZZZZ)Landroid/view/View;
    //   110: areturn
    //   111: aload_0
    //   112: aload #6
    //   114: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   117: iconst_0
    //   118: anewarray java/lang/Class
    //   121: invokevirtual getDeclaredConstructor : ([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   124: iconst_0
    //   125: anewarray java/lang/Object
    //   128: invokevirtual newInstance : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   131: checkcast androidx/appcompat/app/AppCompatViewInflater
    //   134: putfield mAppCompatViewInflater : Landroidx/appcompat/app/AppCompatViewInflater;
    //   137: goto -> 55
    //   140: astore #7
    //   142: ldc_w 'AppCompatDelegate'
    //   145: new java/lang/StringBuilder
    //   148: dup
    //   149: invokespecial <init> : ()V
    //   152: ldc_w 'Failed to instantiate custom view inflater '
    //   155: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: aload #6
    //   160: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: ldc_w '. Falling back to default.'
    //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: invokevirtual toString : ()Ljava/lang/String;
    //   172: aload #7
    //   174: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   177: pop
    //   178: aload_0
    //   179: new androidx/appcompat/app/AppCompatViewInflater
    //   182: dup
    //   183: invokespecial <init> : ()V
    //   186: putfield mAppCompatViewInflater : Landroidx/appcompat/app/AppCompatViewInflater;
    //   189: goto -> 55
    //   192: iconst_0
    //   193: istore #5
    //   195: goto -> 89
    //   198: aload_0
    //   199: aload_1
    //   200: checkcast android/view/ViewParent
    //   203: invokespecial shouldInheritContext : (Landroid/view/ViewParent;)Z
    //   206: istore #5
    //   208: goto -> 89
    // Exception table:
    //   from	to	target	type
    //   111	137	140	java/lang/Throwable
  }
  
  void dismissPopups() {
    if (this.mDecorContentParent != null)
      this.mDecorContentParent.dismissPopups(); 
    if (this.mActionModePopup != null) {
      this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
      if (this.mActionModePopup.isShowing())
        try {
          this.mActionModePopup.dismiss();
        } catch (IllegalArgumentException illegalArgumentException) {} 
      this.mActionModePopup = null;
    } 
    endOnGoingFadeAnimation();
    PanelFeatureState panelFeatureState = getPanelState(0, false);
    if (panelFeatureState != null && panelFeatureState.menu != null)
      panelFeatureState.menu.close(); 
  }
  
  boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
    if (this.mOriginalWindowCallback instanceof KeyEventDispatcher.Component || this.mOriginalWindowCallback instanceof AppCompatDialog) {
      View view = this.mWindow.getDecorView();
      if (view != null && KeyEventDispatcher.dispatchBeforeHierarchy(view, paramKeyEvent))
        return true; 
    } 
    if (paramKeyEvent.getKeyCode() != 82 || !this.mOriginalWindowCallback.dispatchKeyEvent(paramKeyEvent)) {
      boolean bool;
      int i = paramKeyEvent.getKeyCode();
      if (paramKeyEvent.getAction() == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool ? onKeyDown(i, paramKeyEvent) : onKeyUp(i, paramKeyEvent);
    } 
    return true;
  }
  
  void doInvalidatePanelMenu(int paramInt) {
    PanelFeatureState panelFeatureState = getPanelState(paramInt, true);
    if (panelFeatureState.menu != null) {
      Bundle bundle = new Bundle();
      panelFeatureState.menu.saveActionViewStates(bundle);
      if (bundle.size() > 0)
        panelFeatureState.frozenActionViewState = bundle; 
      panelFeatureState.menu.stopDispatchingItemsChanged();
      panelFeatureState.menu.clear();
    } 
    panelFeatureState.refreshMenuContent = true;
    panelFeatureState.refreshDecorView = true;
    if ((paramInt == 108 || paramInt == 0) && this.mDecorContentParent != null) {
      panelFeatureState = getPanelState(0, false);
      if (panelFeatureState != null) {
        panelFeatureState.isPrepared = false;
        preparePanel(panelFeatureState, null);
      } 
    } 
  }
  
  void endOnGoingFadeAnimation() {
    if (this.mFadeAnim != null)
      this.mFadeAnim.cancel(); 
  }
  
  PanelFeatureState findMenuPanel(Menu paramMenu) {
    byte b;
    PanelFeatureState[] arrayOfPanelFeatureState = this.mPanels;
    if (arrayOfPanelFeatureState != null) {
      b = arrayOfPanelFeatureState.length;
    } else {
      b = 0;
    } 
    for (int i = 0; i < b; i++) {
      PanelFeatureState panelFeatureState = arrayOfPanelFeatureState[i];
      if (panelFeatureState != null && panelFeatureState.menu == paramMenu)
        return panelFeatureState; 
    } 
    return null;
  }
  
  @Nullable
  public <T extends View> T findViewById(@IdRes int paramInt) {
    ensureSubDecor();
    return (T)this.mWindow.findViewById(paramInt);
  }
  
  final Context getActionBarThemedContext() {
    Context context1 = null;
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null)
      context1 = actionBar.getThemedContext(); 
    Context context2 = context1;
    if (context1 == null)
      context2 = this.mContext; 
    return context2;
  }
  
  @VisibleForTesting
  final AutoNightModeManager getAutoNightModeManager() {
    ensureAutoNightModeManager();
    return this.mAutoNightModeManager;
  }
  
  public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
    return new ActionBarDrawableToggleImpl();
  }
  
  public MenuInflater getMenuInflater() {
    if (this.mMenuInflater == null) {
      Context context;
      initWindowDecorActionBar();
      if (this.mActionBar != null) {
        context = this.mActionBar.getThemedContext();
      } else {
        context = this.mContext;
      } 
      this.mMenuInflater = (MenuInflater)new SupportMenuInflater(context);
    } 
    return this.mMenuInflater;
  }
  
  protected PanelFeatureState getPanelState(int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mPanels : [Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;
    //   4: astore #4
    //   6: aload #4
    //   8: ifnull -> 21
    //   11: aload #4
    //   13: astore_3
    //   14: aload #4
    //   16: arraylength
    //   17: iload_1
    //   18: if_icmpgt -> 55
    //   21: iload_1
    //   22: iconst_1
    //   23: iadd
    //   24: anewarray androidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState
    //   27: astore #5
    //   29: aload #4
    //   31: ifnull -> 46
    //   34: aload #4
    //   36: iconst_0
    //   37: aload #5
    //   39: iconst_0
    //   40: aload #4
    //   42: arraylength
    //   43: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   46: aload #5
    //   48: astore_3
    //   49: aload_0
    //   50: aload #5
    //   52: putfield mPanels : [Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;
    //   55: aload_3
    //   56: iload_1
    //   57: aaload
    //   58: astore #5
    //   60: aload #5
    //   62: astore #4
    //   64: aload #5
    //   66: ifnonnull -> 84
    //   69: new androidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState
    //   72: dup
    //   73: iload_1
    //   74: invokespecial <init> : (I)V
    //   77: astore #4
    //   79: aload_3
    //   80: iload_1
    //   81: aload #4
    //   83: aastore
    //   84: aload #4
    //   86: areturn
  }
  
  ViewGroup getSubDecor() {
    return this.mSubDecor;
  }
  
  public ActionBar getSupportActionBar() {
    initWindowDecorActionBar();
    return this.mActionBar;
  }
  
  final CharSequence getTitle() {
    return (this.mOriginalWindowCallback instanceof Activity) ? ((Activity)this.mOriginalWindowCallback).getTitle() : this.mTitle;
  }
  
  final Window.Callback getWindowCallback() {
    return this.mWindow.getCallback();
  }
  
  public boolean hasWindowFeature(int paramInt) {
    boolean bool = false;
    switch (sanitizeWindowFeatureId(paramInt)) {
      default:
        return (bool || this.mWindow.hasFeature(paramInt));
      case 108:
        bool = this.mHasActionBar;
      case 109:
        bool = this.mOverlayActionBar;
      case 10:
        bool = this.mOverlayActionMode;
      case 2:
        bool = this.mFeatureProgress;
      case 5:
        bool = this.mFeatureIndeterminateProgress;
      case 1:
        break;
    } 
    bool = this.mWindowNoTitle;
  }
  
  public void installViewFactory() {
    LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
    if (layoutInflater.getFactory() == null) {
      LayoutInflaterCompat.setFactory2(layoutInflater, this);
      return;
    } 
    if (!(layoutInflater.getFactory2() instanceof AppCompatDelegateImpl)) {
      Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
      return;
    } 
  }
  
  public void invalidateOptionsMenu() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null && actionBar.invalidateOptionsMenu())
      return; 
    invalidatePanelMenu(0);
  }
  
  public boolean isHandleNativeActionModesEnabled() {
    return this.mHandleNativeActionModes;
  }
  
  int mapNightMode(int paramInt) {
    switch (paramInt) {
      default:
        return paramInt;
      case 0:
        if (Build.VERSION.SDK_INT >= 23 && ((UiModeManager)this.mContext.getSystemService(UiModeManager.class)).getNightMode() == 0)
          return -1; 
        ensureAutoNightModeManager();
        return this.mAutoNightModeManager.getApplyableNightMode();
      case -100:
        break;
    } 
    return -1;
  }
  
  boolean onBackPressed() {
    if (this.mActionMode != null) {
      this.mActionMode.finish();
      return true;
    } 
    ActionBar actionBar = getSupportActionBar();
    return !(actionBar == null || !actionBar.collapseActionView());
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    if (this.mHasActionBar && this.mSubDecorInstalled) {
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null)
        actionBar.onConfigurationChanged(paramConfiguration); 
    } 
    AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
    applyDayNight();
  }
  
  public void onCreate(Bundle paramBundle) {
    if (this.mOriginalWindowCallback instanceof Activity) {
      String str = null;
      try {
        String str1 = NavUtils.getParentActivityName((Activity)this.mOriginalWindowCallback);
        str = str1;
      } catch (IllegalArgumentException illegalArgumentException) {}
      if (str != null) {
        ActionBar actionBar = peekSupportActionBar();
        if (actionBar == null) {
          this.mEnableDefaultActionBarUp = true;
        } else {
          actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        } 
      } 
    } 
    if (paramBundle != null && this.mLocalNightMode == -100)
      this.mLocalNightMode = paramBundle.getInt("appcompat:local_night_mode", -100); 
  }
  
  public final View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return createView(paramView, paramString, paramContext, paramAttributeSet);
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return onCreateView(null, paramString, paramContext, paramAttributeSet);
  }
  
  public void onDestroy() {
    if (this.mInvalidatePanelMenuPosted)
      this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable); 
    this.mIsDestroyed = true;
    if (this.mActionBar != null)
      this.mActionBar.onDestroy(); 
    if (this.mAutoNightModeManager != null)
      this.mAutoNightModeManager.cleanup(); 
  }
  
  boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    boolean bool = true;
    switch (paramInt) {
      default:
        return false;
      case 82:
        onKeyDownPanel(0, paramKeyEvent);
        return true;
      case 4:
        break;
    } 
    if ((paramKeyEvent.getFlags() & 0x80) == 0)
      bool = false; 
    this.mLongPressBackDown = bool;
  }
  
  boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent) {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar == null || !actionBar.onKeyShortcut(paramInt, paramKeyEvent)) {
      if (this.mPreparedPanel != null && performPanelShortcut(this.mPreparedPanel, paramKeyEvent.getKeyCode(), paramKeyEvent, 1)) {
        if (this.mPreparedPanel != null) {
          this.mPreparedPanel.isHandled = true;
          return true;
        } 
        return true;
      } 
      if (this.mPreparedPanel == null) {
        PanelFeatureState panelFeatureState = getPanelState(0, true);
        preparePanel(panelFeatureState, paramKeyEvent);
        boolean bool = performPanelShortcut(panelFeatureState, paramKeyEvent.getKeyCode(), paramKeyEvent, 1);
        panelFeatureState.isPrepared = false;
        return !!bool;
      } 
      return false;
    } 
    return true;
  }
  
  boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
    null = true;
    switch (paramInt) {
      default:
        return false;
      case 82:
        onKeyUpPanel(0, paramKeyEvent);
        return true;
      case 4:
        break;
    } 
    boolean bool = this.mLongPressBackDown;
    this.mLongPressBackDown = false;
    PanelFeatureState panelFeatureState = getPanelState(0, false);
    if (panelFeatureState != null && panelFeatureState.isOpen) {
      if (!bool) {
        closePanel(panelFeatureState, true);
        return true;
      } 
      return SYNTHETIC_LOCAL_VARIABLE_3;
    } 
    if (onBackPressed())
      return true; 
  }
  
  public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem) {
    Window.Callback callback = getWindowCallback();
    if (callback != null && !this.mIsDestroyed) {
      PanelFeatureState panelFeatureState = findMenuPanel((Menu)paramMenuBuilder.getRootMenu());
      if (panelFeatureState != null)
        return callback.onMenuItemSelected(panelFeatureState.featureId, paramMenuItem); 
    } 
    return false;
  }
  
  public void onMenuModeChange(MenuBuilder paramMenuBuilder) {
    reopenMenu(paramMenuBuilder, true);
  }
  
  void onMenuOpened(int paramInt) {
    if (paramInt == 108) {
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null)
        actionBar.dispatchMenuVisibilityChanged(true); 
    } 
  }
  
  void onPanelClosed(int paramInt) {
    if (paramInt == 108) {
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null)
        actionBar.dispatchMenuVisibilityChanged(false); 
      return;
    } 
    if (paramInt == 0) {
      PanelFeatureState panelFeatureState = getPanelState(paramInt, true);
      if (panelFeatureState.isOpen) {
        closePanel(panelFeatureState, false);
        return;
      } 
    } 
  }
  
  public void onPostCreate(Bundle paramBundle) {
    ensureSubDecor();
  }
  
  public void onPostResume() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null)
      actionBar.setShowHideAnimationEnabled(true); 
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    if (this.mLocalNightMode != -100)
      paramBundle.putInt("appcompat:local_night_mode", this.mLocalNightMode); 
  }
  
  public void onStart() {
    applyDayNight();
  }
  
  public void onStop() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null)
      actionBar.setShowHideAnimationEnabled(false); 
    if (this.mAutoNightModeManager != null)
      this.mAutoNightModeManager.cleanup(); 
  }
  
  void onSubDecorInstalled(ViewGroup paramViewGroup) {}
  
  final ActionBar peekSupportActionBar() {
    return this.mActionBar;
  }
  
  public boolean requestWindowFeature(int paramInt) {
    paramInt = sanitizeWindowFeatureId(paramInt);
    if (this.mWindowNoTitle && paramInt == 108)
      return false; 
    if (this.mHasActionBar && paramInt == 1)
      this.mHasActionBar = false; 
    switch (paramInt) {
      default:
        return this.mWindow.requestFeature(paramInt);
      case 108:
        throwFeatureRequestIfSubDecorInstalled();
        this.mHasActionBar = true;
        return true;
      case 109:
        throwFeatureRequestIfSubDecorInstalled();
        this.mOverlayActionBar = true;
        return true;
      case 10:
        throwFeatureRequestIfSubDecorInstalled();
        this.mOverlayActionMode = true;
        return true;
      case 2:
        throwFeatureRequestIfSubDecorInstalled();
        this.mFeatureProgress = true;
        return true;
      case 5:
        throwFeatureRequestIfSubDecorInstalled();
        this.mFeatureIndeterminateProgress = true;
        return true;
      case 1:
        break;
    } 
    throwFeatureRequestIfSubDecorInstalled();
    this.mWindowNoTitle = true;
    return true;
  }
  
  public void setContentView(int paramInt) {
    ensureSubDecor();
    ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
    viewGroup.removeAllViews();
    LayoutInflater.from(this.mContext).inflate(paramInt, viewGroup);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public void setContentView(View paramView) {
    ensureSubDecor();
    ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
    viewGroup.removeAllViews();
    viewGroup.addView(paramView);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    ensureSubDecor();
    ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
    viewGroup.removeAllViews();
    viewGroup.addView(paramView, paramLayoutParams);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public void setHandleNativeActionModesEnabled(boolean paramBoolean) {
    this.mHandleNativeActionModes = paramBoolean;
  }
  
  public void setLocalNightMode(int paramInt) {
    switch (paramInt) {
      default:
        Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
        return;
      case -1:
      case 0:
      case 1:
      case 2:
        break;
    } 
    if (this.mLocalNightMode != paramInt) {
      this.mLocalNightMode = paramInt;
      if (this.mApplyDayNightCalled) {
        applyDayNight();
        return;
      } 
    } 
  }
  
  public void setSupportActionBar(Toolbar paramToolbar) {
    if (!(this.mOriginalWindowCallback instanceof Activity))
      return; 
    ActionBar actionBar = getSupportActionBar();
    if (actionBar instanceof WindowDecorActionBar)
      throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead."); 
    this.mMenuInflater = null;
    if (actionBar != null)
      actionBar.onDestroy(); 
    if (paramToolbar != null) {
      ToolbarActionBar toolbarActionBar = new ToolbarActionBar(paramToolbar, ((Activity)this.mOriginalWindowCallback).getTitle(), this.mAppCompatWindowCallback);
      this.mActionBar = toolbarActionBar;
      this.mWindow.setCallback(toolbarActionBar.getWrappedWindowCallback());
    } else {
      this.mActionBar = null;
      this.mWindow.setCallback(this.mAppCompatWindowCallback);
    } 
    invalidateOptionsMenu();
  }
  
  public final void setTitle(CharSequence paramCharSequence) {
    this.mTitle = paramCharSequence;
    if (this.mDecorContentParent != null) {
      this.mDecorContentParent.setWindowTitle(paramCharSequence);
      return;
    } 
    if (peekSupportActionBar() != null) {
      peekSupportActionBar().setWindowTitle(paramCharSequence);
      return;
    } 
    if (this.mTitleView != null) {
      this.mTitleView.setText(paramCharSequence);
      return;
    } 
  }
  
  final boolean shouldAnimateActionModeView() {
    return (this.mSubDecorInstalled && this.mSubDecor != null && ViewCompat.isLaidOut((View)this.mSubDecor));
  }
  
  public ActionMode startSupportActionMode(@NonNull ActionMode.Callback paramCallback) {
    if (paramCallback == null)
      throw new IllegalArgumentException("ActionMode callback can not be null."); 
    if (this.mActionMode != null)
      this.mActionMode.finish(); 
    paramCallback = new ActionModeCallbackWrapperV9(paramCallback);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      this.mActionMode = actionBar.startActionMode(paramCallback);
      if (this.mActionMode != null && this.mAppCompatCallback != null)
        this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode); 
    } 
    if (this.mActionMode == null)
      this.mActionMode = startSupportActionModeFromWindow(paramCallback); 
    return this.mActionMode;
  }
  
  ActionMode startSupportActionModeFromWindow(@NonNull ActionMode.Callback paramCallback) {
    ActionMode.Callback callback1;
    endOnGoingFadeAnimation();
    if (this.mActionMode != null)
      this.mActionMode.finish(); 
    ActionMode.Callback callback2 = paramCallback;
    if (!(paramCallback instanceof ActionModeCallbackWrapperV9))
      callback2 = new ActionModeCallbackWrapperV9(paramCallback); 
    ActionMode.Callback callback3 = null;
    paramCallback = callback3;
    if (this.mAppCompatCallback != null) {
      paramCallback = callback3;
      if (!this.mIsDestroyed)
        try {
          ActionMode actionMode = this.mAppCompatCallback.onWindowStartingSupportActionMode(callback2);
        } catch (AbstractMethodError abstractMethodError) {
          callback1 = callback3;
        }  
    } 
    if (callback1 != null) {
      this.mActionMode = (ActionMode)callback1;
    } else {
      if (this.mActionModeView == null)
        if (this.mIsFloating) {
          Context context;
          TypedValue typedValue = new TypedValue();
          Resources.Theme theme = this.mContext.getTheme();
          theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
          if (typedValue.resourceId != 0) {
            Resources.Theme theme1 = this.mContext.getResources().newTheme();
            theme1.setTo(theme);
            theme1.applyStyle(typedValue.resourceId, true);
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, 0);
            contextThemeWrapper.getTheme().setTo(theme1);
          } else {
            context = this.mContext;
          } 
          this.mActionModeView = new ActionBarContextView(context);
          this.mActionModePopup = new PopupWindow(context, null, R.attr.actionModePopupWindowStyle);
          PopupWindowCompat.setWindowLayoutType(this.mActionModePopup, 2);
          this.mActionModePopup.setContentView((View)this.mActionModeView);
          this.mActionModePopup.setWidth(-1);
          context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
          int i = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
          this.mActionModeView.setContentHeight(i);
          this.mActionModePopup.setHeight(-2);
          this.mShowActionModePopup = new Runnable() {
              public void run() {
                AppCompatDelegateImpl.this.mActionModePopup.showAtLocation((View)AppCompatDelegateImpl.this.mActionModeView, 55, 0, 0);
                AppCompatDelegateImpl.this.endOnGoingFadeAnimation();
                if (AppCompatDelegateImpl.this.shouldAnimateActionModeView()) {
                  AppCompatDelegateImpl.this.mActionModeView.setAlpha(0.0F);
                  AppCompatDelegateImpl.this.mFadeAnim = ViewCompat.animate((View)AppCompatDelegateImpl.this.mActionModeView).alpha(1.0F);
                  AppCompatDelegateImpl.this.mFadeAnim.setListener((ViewPropertyAnimatorListener)new ViewPropertyAnimatorListenerAdapter() {
                        public void onAnimationEnd(View param2View) {
                          AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0F);
                          AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
                          AppCompatDelegateImpl.this.mFadeAnim = null;
                        }
                        
                        public void onAnimationStart(View param2View) {
                          AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
                        }
                      });
                  return;
                } 
                AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0F);
                AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
              }
            };
        } else {
          ViewStubCompat viewStubCompat = (ViewStubCompat)this.mSubDecor.findViewById(R.id.action_mode_bar_stub);
          if (viewStubCompat != null) {
            viewStubCompat.setLayoutInflater(LayoutInflater.from(getActionBarThemedContext()));
            this.mActionModeView = (ActionBarContextView)viewStubCompat.inflate();
          } 
        }  
      if (this.mActionModeView != null) {
        boolean bool;
        endOnGoingFadeAnimation();
        this.mActionModeView.killMode();
        Context context = this.mActionModeView.getContext();
        ActionBarContextView actionBarContextView = this.mActionModeView;
        if (this.mActionModePopup == null) {
          bool = true;
        } else {
          bool = false;
        } 
        StandaloneActionMode standaloneActionMode = new StandaloneActionMode(context, actionBarContextView, callback2, bool);
        if (callback2.onCreateActionMode((ActionMode)standaloneActionMode, standaloneActionMode.getMenu())) {
          standaloneActionMode.invalidate();
          this.mActionModeView.initForMode((ActionMode)standaloneActionMode);
          this.mActionMode = (ActionMode)standaloneActionMode;
          if (shouldAnimateActionModeView()) {
            this.mActionModeView.setAlpha(0.0F);
            this.mFadeAnim = ViewCompat.animate((View)this.mActionModeView).alpha(1.0F);
            this.mFadeAnim.setListener((ViewPropertyAnimatorListener)new ViewPropertyAnimatorListenerAdapter() {
                  public void onAnimationEnd(View param1View) {
                    AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0F);
                    AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
                    AppCompatDelegateImpl.this.mFadeAnim = null;
                  }
                  
                  public void onAnimationStart(View param1View) {
                    AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
                    AppCompatDelegateImpl.this.mActionModeView.sendAccessibilityEvent(32);
                    if (AppCompatDelegateImpl.this.mActionModeView.getParent() instanceof View)
                      ViewCompat.requestApplyInsets((View)AppCompatDelegateImpl.this.mActionModeView.getParent()); 
                  }
                });
          } else {
            this.mActionModeView.setAlpha(1.0F);
            this.mActionModeView.setVisibility(0);
            this.mActionModeView.sendAccessibilityEvent(32);
            if (this.mActionModeView.getParent() instanceof View)
              ViewCompat.requestApplyInsets((View)this.mActionModeView.getParent()); 
          } 
          if (this.mActionModePopup != null)
            this.mWindow.getDecorView().post(this.mShowActionModePopup); 
        } else {
          this.mActionMode = null;
        } 
      } 
    } 
    if (this.mActionMode != null && this.mAppCompatCallback != null)
      this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode); 
    return this.mActionMode;
  }
  
  int updateStatusGuard(int paramInt) {
    boolean bool1 = false;
    int j = 0;
    boolean bool2 = false;
    int k = j;
    int i = paramInt;
    if (this.mActionModeView != null) {
      k = j;
      i = paramInt;
      if (this.mActionModeView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
        boolean bool;
        int m;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)this.mActionModeView.getLayoutParams();
        j = 0;
        i = 0;
        if (this.mActionModeView.isShown()) {
          if (this.mTempRect1 == null) {
            this.mTempRect1 = new Rect();
            this.mTempRect2 = new Rect();
          } 
          Rect rect1 = this.mTempRect1;
          Rect rect2 = this.mTempRect2;
          rect1.set(0, paramInt, 0, 0);
          ViewUtils.computeFitSystemWindows((View)this.mSubDecor, rect1, rect2);
          if (rect2.top == 0) {
            j = paramInt;
          } else {
            j = 0;
          } 
          if (marginLayoutParams.topMargin != j) {
            j = 1;
            marginLayoutParams.topMargin = paramInt;
            if (this.mStatusGuard == null) {
              this.mStatusGuard = new View(this.mContext);
              this.mStatusGuard.setBackgroundColor(this.mContext.getResources().getColor(R.color.abc_input_method_navigation_guard));
              this.mSubDecor.addView(this.mStatusGuard, -1, new ViewGroup.LayoutParams(-1, paramInt));
              i = j;
            } else {
              ViewGroup.LayoutParams layoutParams = this.mStatusGuard.getLayoutParams();
              i = j;
              if (layoutParams.height != paramInt) {
                layoutParams.height = paramInt;
                this.mStatusGuard.setLayoutParams(layoutParams);
                i = j;
              } 
            } 
          } 
          if (this.mStatusGuard != null) {
            k = 1;
          } else {
            k = 0;
          } 
          j = i;
          bool = k;
          m = paramInt;
          if (!this.mOverlayActionMode) {
            j = i;
            bool = k;
            m = paramInt;
            if (k != 0) {
              m = 0;
              bool = k;
              j = i;
            } 
          } 
        } else {
          bool = bool2;
          m = paramInt;
          if (marginLayoutParams.topMargin != 0) {
            j = 1;
            marginLayoutParams.topMargin = 0;
            bool = bool2;
            m = paramInt;
          } 
        } 
        k = bool;
        i = m;
        if (j != 0) {
          this.mActionModeView.setLayoutParams((ViewGroup.LayoutParams)marginLayoutParams);
          i = m;
          k = bool;
        } 
      } 
    } 
    if (this.mStatusGuard != null) {
      View view = this.mStatusGuard;
      if (k != 0) {
        paramInt = bool1;
      } else {
        paramInt = 8;
      } 
      view.setVisibility(paramInt);
    } 
    return i;
  }
  
  static {
    boolean bool;
    if (Build.VERSION.SDK_INT < 21) {
      bool = true;
    } else {
      bool = false;
    } 
    IS_PRE_LOLLIPOP = bool;
  }
  
  private class ActionBarDrawableToggleImpl implements ActionBarDrawerToggle.Delegate {
    public Context getActionBarThemedContext() {
      return AppCompatDelegateImpl.this.getActionBarThemedContext();
    }
    
    public Drawable getThemeUpIndicator() {
      TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), null, new int[] { R.attr.homeAsUpIndicator });
      Drawable drawable = tintTypedArray.getDrawable(0);
      tintTypedArray.recycle();
      return drawable;
    }
    
    public boolean isNavigationVisible() {
      ActionBar actionBar = AppCompatDelegateImpl.this.getSupportActionBar();
      return (actionBar != null && (actionBar.getDisplayOptions() & 0x4) != 0);
    }
    
    public void setActionBarDescription(int param1Int) {
      ActionBar actionBar = AppCompatDelegateImpl.this.getSupportActionBar();
      if (actionBar != null)
        actionBar.setHomeActionContentDescription(param1Int); 
    }
    
    public void setActionBarUpIndicator(Drawable param1Drawable, int param1Int) {
      ActionBar actionBar = AppCompatDelegateImpl.this.getSupportActionBar();
      if (actionBar != null) {
        actionBar.setHomeAsUpIndicator(param1Drawable);
        actionBar.setHomeActionContentDescription(param1Int);
      } 
    }
  }
  
  private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
    public void onCloseMenu(MenuBuilder param1MenuBuilder, boolean param1Boolean) {
      AppCompatDelegateImpl.this.checkCloseActionMenu(param1MenuBuilder);
    }
    
    public boolean onOpenSubMenu(MenuBuilder param1MenuBuilder) {
      Window.Callback callback = AppCompatDelegateImpl.this.getWindowCallback();
      if (callback != null)
        callback.onMenuOpened(108, (Menu)param1MenuBuilder); 
      return true;
    }
  }
  
  class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
    private ActionMode.Callback mWrapped;
    
    public ActionModeCallbackWrapperV9(ActionMode.Callback param1Callback) {
      this.mWrapped = param1Callback;
    }
    
    public boolean onActionItemClicked(ActionMode param1ActionMode, MenuItem param1MenuItem) {
      return this.mWrapped.onActionItemClicked(param1ActionMode, param1MenuItem);
    }
    
    public boolean onCreateActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      return this.mWrapped.onCreateActionMode(param1ActionMode, param1Menu);
    }
    
    public void onDestroyActionMode(ActionMode param1ActionMode) {
      this.mWrapped.onDestroyActionMode(param1ActionMode);
      if (AppCompatDelegateImpl.this.mActionModePopup != null)
        AppCompatDelegateImpl.this.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.mShowActionModePopup); 
      if (AppCompatDelegateImpl.this.mActionModeView != null) {
        AppCompatDelegateImpl.this.endOnGoingFadeAnimation();
        AppCompatDelegateImpl.this.mFadeAnim = ViewCompat.animate((View)AppCompatDelegateImpl.this.mActionModeView).alpha(0.0F);
        AppCompatDelegateImpl.this.mFadeAnim.setListener((ViewPropertyAnimatorListener)new ViewPropertyAnimatorListenerAdapter() {
              public void onAnimationEnd(View param2View) {
                AppCompatDelegateImpl.this.mActionModeView.setVisibility(8);
                if (AppCompatDelegateImpl.this.mActionModePopup != null) {
                  AppCompatDelegateImpl.this.mActionModePopup.dismiss();
                } else if (AppCompatDelegateImpl.this.mActionModeView.getParent() instanceof View) {
                  ViewCompat.requestApplyInsets((View)AppCompatDelegateImpl.this.mActionModeView.getParent());
                } 
                AppCompatDelegateImpl.this.mActionModeView.removeAllViews();
                AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
                AppCompatDelegateImpl.this.mFadeAnim = null;
              }
            });
      } 
      if (AppCompatDelegateImpl.this.mAppCompatCallback != null)
        AppCompatDelegateImpl.this.mAppCompatCallback.onSupportActionModeFinished(AppCompatDelegateImpl.this.mActionMode); 
      AppCompatDelegateImpl.this.mActionMode = null;
    }
    
    public boolean onPrepareActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      return this.mWrapped.onPrepareActionMode(param1ActionMode, param1Menu);
    }
  }
  
  class null extends ViewPropertyAnimatorListenerAdapter {
    public void onAnimationEnd(View param1View) {
      AppCompatDelegateImpl.this.mActionModeView.setVisibility(8);
      if (AppCompatDelegateImpl.this.mActionModePopup != null) {
        AppCompatDelegateImpl.this.mActionModePopup.dismiss();
      } else if (AppCompatDelegateImpl.this.mActionModeView.getParent() instanceof View) {
        ViewCompat.requestApplyInsets((View)AppCompatDelegateImpl.this.mActionModeView.getParent());
      } 
      AppCompatDelegateImpl.this.mActionModeView.removeAllViews();
      AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
      AppCompatDelegateImpl.this.mFadeAnim = null;
    }
  }
  
  class AppCompatWindowCallback extends WindowCallbackWrapper {
    AppCompatWindowCallback(Window.Callback param1Callback) {
      super(param1Callback);
    }
    
    public boolean dispatchKeyEvent(KeyEvent param1KeyEvent) {
      return (AppCompatDelegateImpl.this.dispatchKeyEvent(param1KeyEvent) || super.dispatchKeyEvent(param1KeyEvent));
    }
    
    public boolean dispatchKeyShortcutEvent(KeyEvent param1KeyEvent) {
      return (super.dispatchKeyShortcutEvent(param1KeyEvent) || AppCompatDelegateImpl.this.onKeyShortcut(param1KeyEvent.getKeyCode(), param1KeyEvent));
    }
    
    public void onContentChanged() {}
    
    public boolean onCreatePanelMenu(int param1Int, Menu param1Menu) {
      return (param1Int == 0 && !(param1Menu instanceof MenuBuilder)) ? false : super.onCreatePanelMenu(param1Int, param1Menu);
    }
    
    public boolean onMenuOpened(int param1Int, Menu param1Menu) {
      super.onMenuOpened(param1Int, param1Menu);
      AppCompatDelegateImpl.this.onMenuOpened(param1Int);
      return true;
    }
    
    public void onPanelClosed(int param1Int, Menu param1Menu) {
      super.onPanelClosed(param1Int, param1Menu);
      AppCompatDelegateImpl.this.onPanelClosed(param1Int);
    }
    
    public boolean onPreparePanel(int param1Int, View param1View, Menu param1Menu) {
      MenuBuilder menuBuilder;
      if (param1Menu instanceof MenuBuilder) {
        menuBuilder = (MenuBuilder)param1Menu;
      } else {
        menuBuilder = null;
      } 
      if (param1Int == 0 && menuBuilder == null)
        return false; 
      if (menuBuilder != null)
        menuBuilder.setOverrideVisibleItems(true); 
      boolean bool2 = super.onPreparePanel(param1Int, param1View, param1Menu);
      boolean bool1 = bool2;
      if (menuBuilder != null) {
        menuBuilder.setOverrideVisibleItems(false);
        return bool2;
      } 
      return bool1;
    }
    
    @RequiresApi(24)
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> param1List, Menu param1Menu, int param1Int) {
      AppCompatDelegateImpl.PanelFeatureState panelFeatureState = AppCompatDelegateImpl.this.getPanelState(0, true);
      if (panelFeatureState != null && panelFeatureState.menu != null) {
        super.onProvideKeyboardShortcuts(param1List, (Menu)panelFeatureState.menu, param1Int);
        return;
      } 
      super.onProvideKeyboardShortcuts(param1List, param1Menu, param1Int);
    }
    
    public ActionMode onWindowStartingActionMode(ActionMode.Callback param1Callback) {
      return (Build.VERSION.SDK_INT >= 23) ? null : (AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled() ? startAsSupportActionMode(param1Callback) : super.onWindowStartingActionMode(param1Callback));
    }
    
    @RequiresApi(23)
    public ActionMode onWindowStartingActionMode(ActionMode.Callback param1Callback, int param1Int) {
      if (AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled()) {
        switch (param1Int) {
          default:
            return super.onWindowStartingActionMode(param1Callback, param1Int);
          case 0:
            break;
        } 
        return startAsSupportActionMode(param1Callback);
      } 
    }
    
    final ActionMode startAsSupportActionMode(ActionMode.Callback param1Callback) {
      SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImpl.this.mContext, param1Callback);
      ActionMode actionMode = AppCompatDelegateImpl.this.startSupportActionMode((ActionMode.Callback)callbackWrapper);
      return (actionMode != null) ? callbackWrapper.getActionModeWrapper(actionMode) : null;
    }
  }
  
  @VisibleForTesting
  final class AutoNightModeManager {
    private BroadcastReceiver mAutoTimeChangeReceiver;
    
    private IntentFilter mAutoTimeChangeReceiverFilter;
    
    private boolean mIsNight;
    
    private TwilightManager mTwilightManager;
    
    AutoNightModeManager(TwilightManager param1TwilightManager) {
      this.mTwilightManager = param1TwilightManager;
      this.mIsNight = param1TwilightManager.isNight();
    }
    
    void cleanup() {
      if (this.mAutoTimeChangeReceiver != null) {
        AppCompatDelegateImpl.this.mContext.unregisterReceiver(this.mAutoTimeChangeReceiver);
        this.mAutoTimeChangeReceiver = null;
      } 
    }
    
    void dispatchTimeChanged() {
      boolean bool = this.mTwilightManager.isNight();
      if (bool != this.mIsNight) {
        this.mIsNight = bool;
        AppCompatDelegateImpl.this.applyDayNight();
      } 
    }
    
    int getApplyableNightMode() {
      this.mIsNight = this.mTwilightManager.isNight();
      return this.mIsNight ? 2 : 1;
    }
    
    void setup() {
      cleanup();
      if (this.mAutoTimeChangeReceiver == null)
        this.mAutoTimeChangeReceiver = new BroadcastReceiver() {
            public void onReceive(Context param2Context, Intent param2Intent) {
              AppCompatDelegateImpl.AutoNightModeManager.this.dispatchTimeChanged();
            }
          }; 
      if (this.mAutoTimeChangeReceiverFilter == null) {
        this.mAutoTimeChangeReceiverFilter = new IntentFilter();
        this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
        this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
      } 
      AppCompatDelegateImpl.this.mContext.registerReceiver(this.mAutoTimeChangeReceiver, this.mAutoTimeChangeReceiverFilter);
    }
  }
  
  class null extends BroadcastReceiver {
    public void onReceive(Context param1Context, Intent param1Intent) {
      this.this$1.dispatchTimeChanged();
    }
  }
  
  private class ListMenuDecorView extends ContentFrameLayout {
    public ListMenuDecorView(Context param1Context) {
      super(param1Context);
    }
    
    private boolean isOutOfBounds(int param1Int1, int param1Int2) {
      return (param1Int1 < -5 || param1Int2 < -5 || param1Int1 > getWidth() + 5 || param1Int2 > getHeight() + 5);
    }
    
    public boolean dispatchKeyEvent(KeyEvent param1KeyEvent) {
      return (AppCompatDelegateImpl.this.dispatchKeyEvent(param1KeyEvent) || super.dispatchKeyEvent(param1KeyEvent));
    }
    
    public boolean onInterceptTouchEvent(MotionEvent param1MotionEvent) {
      if (param1MotionEvent.getAction() == 0 && isOutOfBounds((int)param1MotionEvent.getX(), (int)param1MotionEvent.getY())) {
        AppCompatDelegateImpl.this.closePanel(0);
        return true;
      } 
      return super.onInterceptTouchEvent(param1MotionEvent);
    }
    
    public void setBackgroundResource(int param1Int) {
      setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), param1Int));
    }
  }
  
  protected static final class PanelFeatureState {
    int background;
    
    View createdPanelView;
    
    ViewGroup decorView;
    
    int featureId;
    
    Bundle frozenActionViewState;
    
    Bundle frozenMenuState;
    
    int gravity;
    
    boolean isHandled;
    
    boolean isOpen;
    
    boolean isPrepared;
    
    ListMenuPresenter listMenuPresenter;
    
    Context listPresenterContext;
    
    MenuBuilder menu;
    
    public boolean qwertyMode;
    
    boolean refreshDecorView;
    
    boolean refreshMenuContent;
    
    View shownPanelView;
    
    boolean wasLastOpen;
    
    int windowAnimations;
    
    int x;
    
    int y;
    
    PanelFeatureState(int param1Int) {
      this.featureId = param1Int;
      this.refreshDecorView = false;
    }
    
    void applyFrozenState() {
      if (this.menu != null && this.frozenMenuState != null) {
        this.menu.restorePresenterStates(this.frozenMenuState);
        this.frozenMenuState = null;
      } 
    }
    
    public void clearMenuPresenters() {
      if (this.menu != null)
        this.menu.removeMenuPresenter((MenuPresenter)this.listMenuPresenter); 
      this.listMenuPresenter = null;
    }
    
    MenuView getListMenuView(MenuPresenter.Callback param1Callback) {
      if (this.menu == null)
        return null; 
      if (this.listMenuPresenter == null) {
        this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, R.layout.abc_list_menu_item_layout);
        this.listMenuPresenter.setCallback(param1Callback);
        this.menu.addMenuPresenter((MenuPresenter)this.listMenuPresenter);
      } 
      return this.listMenuPresenter.getMenuView(this.decorView);
    }
    
    public boolean hasPanelItems() {
      boolean bool2 = true;
      if (this.shownPanelView == null)
        return false; 
      boolean bool1 = bool2;
      if (this.createdPanelView == null) {
        bool1 = bool2;
        if (this.listMenuPresenter.getAdapter().getCount() <= 0)
          return false; 
      } 
      return bool1;
    }
    
    void onRestoreInstanceState(Parcelable param1Parcelable) {
      param1Parcelable = param1Parcelable;
      this.featureId = ((SavedState)param1Parcelable).featureId;
      this.wasLastOpen = ((SavedState)param1Parcelable).isOpen;
      this.frozenMenuState = ((SavedState)param1Parcelable).menuState;
      this.shownPanelView = null;
      this.decorView = null;
    }
    
    Parcelable onSaveInstanceState() {
      SavedState savedState = new SavedState();
      savedState.featureId = this.featureId;
      savedState.isOpen = this.isOpen;
      if (this.menu != null) {
        savedState.menuState = new Bundle();
        this.menu.savePresenterStates(savedState.menuState);
      } 
      return savedState;
    }
    
    void setMenu(MenuBuilder param1MenuBuilder) {
      if (param1MenuBuilder != this.menu) {
        if (this.menu != null)
          this.menu.removeMenuPresenter((MenuPresenter)this.listMenuPresenter); 
        this.menu = param1MenuBuilder;
        if (param1MenuBuilder != null && this.listMenuPresenter != null) {
          param1MenuBuilder.addMenuPresenter((MenuPresenter)this.listMenuPresenter);
          return;
        } 
      } 
    }
    
    void setStyle(Context param1Context) {
      TypedValue typedValue = new TypedValue();
      Resources.Theme theme = param1Context.getResources().newTheme();
      theme.setTo(param1Context.getTheme());
      theme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
      if (typedValue.resourceId != 0)
        theme.applyStyle(typedValue.resourceId, true); 
      theme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
      if (typedValue.resourceId != 0) {
        theme.applyStyle(typedValue.resourceId, true);
      } else {
        theme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
      } 
      ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(param1Context, 0);
      contextThemeWrapper.getTheme().setTo(theme);
      this.listPresenterContext = (Context)contextThemeWrapper;
      TypedArray typedArray = contextThemeWrapper.obtainStyledAttributes(R.styleable.AppCompatTheme);
      this.background = typedArray.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
      this.windowAnimations = typedArray.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
      typedArray.recycle();
    }
    
    private static class SavedState implements Parcelable {
      public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
          public AppCompatDelegateImpl.PanelFeatureState.SavedState createFromParcel(Parcel param3Parcel) {
            return AppCompatDelegateImpl.PanelFeatureState.SavedState.readFromParcel(param3Parcel, null);
          }
          
          public AppCompatDelegateImpl.PanelFeatureState.SavedState createFromParcel(Parcel param3Parcel, ClassLoader param3ClassLoader) {
            return AppCompatDelegateImpl.PanelFeatureState.SavedState.readFromParcel(param3Parcel, param3ClassLoader);
          }
          
          public AppCompatDelegateImpl.PanelFeatureState.SavedState[] newArray(int param3Int) {
            return new AppCompatDelegateImpl.PanelFeatureState.SavedState[param3Int];
          }
        };
      
      int featureId;
      
      boolean isOpen;
      
      Bundle menuState;
      
      static SavedState readFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
        boolean bool = true;
        SavedState savedState = new SavedState();
        savedState.featureId = param2Parcel.readInt();
        if (param2Parcel.readInt() != 1)
          bool = false; 
        savedState.isOpen = bool;
        if (savedState.isOpen)
          savedState.menuState = param2Parcel.readBundle(param2ClassLoader); 
        return savedState;
      }
      
      public int describeContents() {
        return 0;
      }
      
      public void writeToParcel(Parcel param2Parcel, int param2Int) {
        param2Parcel.writeInt(this.featureId);
        if (this.isOpen) {
          param2Int = 1;
        } else {
          param2Int = 0;
        } 
        param2Parcel.writeInt(param2Int);
        if (this.isOpen)
          param2Parcel.writeBundle(this.menuState); 
      }
    }
    
    static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
      public AppCompatDelegateImpl.PanelFeatureState.SavedState createFromParcel(Parcel param2Parcel) {
        return AppCompatDelegateImpl.PanelFeatureState.SavedState.readFromParcel(param2Parcel, null);
      }
      
      public AppCompatDelegateImpl.PanelFeatureState.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
        return AppCompatDelegateImpl.PanelFeatureState.SavedState.readFromParcel(param2Parcel, param2ClassLoader);
      }
      
      public AppCompatDelegateImpl.PanelFeatureState.SavedState[] newArray(int param2Int) {
        return new AppCompatDelegateImpl.PanelFeatureState.SavedState[param2Int];
      }
    }
  }
  
  private static class SavedState implements Parcelable {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public AppCompatDelegateImpl.PanelFeatureState.SavedState createFromParcel(Parcel param3Parcel) {
          return AppCompatDelegateImpl.PanelFeatureState.SavedState.readFromParcel(param3Parcel, null);
        }
        
        public AppCompatDelegateImpl.PanelFeatureState.SavedState createFromParcel(Parcel param3Parcel, ClassLoader param3ClassLoader) {
          return AppCompatDelegateImpl.PanelFeatureState.SavedState.readFromParcel(param3Parcel, param3ClassLoader);
        }
        
        public AppCompatDelegateImpl.PanelFeatureState.SavedState[] newArray(int param3Int) {
          return new AppCompatDelegateImpl.PanelFeatureState.SavedState[param3Int];
        }
      };
    
    int featureId;
    
    boolean isOpen;
    
    Bundle menuState;
    
    static SavedState readFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      boolean bool = true;
      SavedState savedState = new SavedState();
      savedState.featureId = param1Parcel.readInt();
      if (param1Parcel.readInt() != 1)
        bool = false; 
      savedState.isOpen = bool;
      if (savedState.isOpen)
        savedState.menuState = param1Parcel.readBundle(param1ClassLoader); 
      return savedState;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.featureId);
      if (this.isOpen) {
        param1Int = 1;
      } else {
        param1Int = 0;
      } 
      param1Parcel.writeInt(param1Int);
      if (this.isOpen)
        param1Parcel.writeBundle(this.menuState); 
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<PanelFeatureState.SavedState> {
    public AppCompatDelegateImpl.PanelFeatureState.SavedState createFromParcel(Parcel param1Parcel) {
      return AppCompatDelegateImpl.PanelFeatureState.SavedState.readFromParcel(param1Parcel, null);
    }
    
    public AppCompatDelegateImpl.PanelFeatureState.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return AppCompatDelegateImpl.PanelFeatureState.SavedState.readFromParcel(param1Parcel, param1ClassLoader);
    }
    
    public AppCompatDelegateImpl.PanelFeatureState.SavedState[] newArray(int param1Int) {
      return new AppCompatDelegateImpl.PanelFeatureState.SavedState[param1Int];
    }
  }
  
  private final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
    public void onCloseMenu(MenuBuilder param1MenuBuilder, boolean param1Boolean) {
      boolean bool;
      MenuBuilder menuBuilder = param1MenuBuilder.getRootMenu();
      if (menuBuilder != param1MenuBuilder) {
        bool = true;
      } else {
        bool = false;
      } 
      AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
      if (bool)
        param1MenuBuilder = menuBuilder; 
      AppCompatDelegateImpl.PanelFeatureState panelFeatureState = appCompatDelegateImpl.findMenuPanel((Menu)param1MenuBuilder);
      if (panelFeatureState != null) {
        if (bool) {
          AppCompatDelegateImpl.this.callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, (Menu)menuBuilder);
          AppCompatDelegateImpl.this.closePanel(panelFeatureState, true);
          return;
        } 
      } else {
        return;
      } 
      AppCompatDelegateImpl.this.closePanel(panelFeatureState, param1Boolean);
    }
    
    public boolean onOpenSubMenu(MenuBuilder param1MenuBuilder) {
      if (param1MenuBuilder == null && AppCompatDelegateImpl.this.mHasActionBar) {
        Window.Callback callback = AppCompatDelegateImpl.this.getWindowCallback();
        if (callback != null && !AppCompatDelegateImpl.this.mIsDestroyed)
          callback.onMenuOpened(108, (Menu)param1MenuBuilder); 
      } 
      return true;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/app/AppCompatDelegateImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
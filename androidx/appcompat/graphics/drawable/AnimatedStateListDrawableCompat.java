package androidx.appcompat.graphics.drawable;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.Xml;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.R;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedStateListDrawableCompat extends StateListDrawable {
  private static final String ELEMENT_ITEM = "item";
  
  private static final String ELEMENT_TRANSITION = "transition";
  
  private static final String ITEM_MISSING_DRAWABLE_ERROR = ": <item> tag requires a 'drawable' attribute or child tag defining a drawable";
  
  private static final String LOGTAG = AnimatedStateListDrawableCompat.class.getSimpleName();
  
  private static final String TRANSITION_MISSING_DRAWABLE_ERROR = ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable";
  
  private static final String TRANSITION_MISSING_FROM_TO_ID = ": <transition> tag requires 'fromId' & 'toId' attributes";
  
  private boolean mMutated;
  
  private AnimatedStateListState mState;
  
  private Transition mTransition;
  
  private int mTransitionFromIndex = -1;
  
  private int mTransitionToIndex = -1;
  
  public AnimatedStateListDrawableCompat() {
    this((AnimatedStateListState)null, (Resources)null);
  }
  
  AnimatedStateListDrawableCompat(@Nullable AnimatedStateListState paramAnimatedStateListState, @Nullable Resources paramResources) {
    super((StateListDrawable.StateListState)null);
    setConstantState(new AnimatedStateListState(paramAnimatedStateListState, this, paramResources));
    onStateChange(getState());
    jumpToCurrentState();
  }
  
  @Nullable
  public static AnimatedStateListDrawableCompat create(@NonNull Context paramContext, @DrawableRes int paramInt, @Nullable Resources.Theme paramTheme) {
    try {
      Resources resources = paramContext.getResources();
      XmlResourceParser xmlResourceParser = resources.getXml(paramInt);
      AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)xmlResourceParser);
      do {
        paramInt = xmlResourceParser.next();
      } while (paramInt != 2 && paramInt != 1);
      if (paramInt != 2)
        throw new XmlPullParserException("No start tag found"); 
      return createFromXmlInner(paramContext, resources, (XmlPullParser)xmlResourceParser, attributeSet, paramTheme);
    } catch (XmlPullParserException xmlPullParserException) {
      Log.e(LOGTAG, "parser error", (Throwable)xmlPullParserException);
    } catch (IOException iOException) {
      Log.e(LOGTAG, "parser error", iOException);
    } 
    return null;
  }
  
  public static AnimatedStateListDrawableCompat createFromXmlInner(@NonNull Context paramContext, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws IOException, XmlPullParserException {
    String str = paramXmlPullParser.getName();
    if (!str.equals("animated-selector"))
      throw new XmlPullParserException(paramXmlPullParser.getPositionDescription() + ": invalid animated-selector tag " + str); 
    AnimatedStateListDrawableCompat animatedStateListDrawableCompat = new AnimatedStateListDrawableCompat();
    animatedStateListDrawableCompat.inflate(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    return animatedStateListDrawableCompat;
  }
  
  private void inflateChildElements(@NonNull Context paramContext, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i = paramXmlPullParser.getDepth() + 1;
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          if (j == 2 && k <= i) {
            if (paramXmlPullParser.getName().equals("item")) {
              parseItem(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
              continue;
            } 
            if (paramXmlPullParser.getName().equals("transition"))
              parseTransition(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme); 
          } 
          continue;
        } 
      } 
      break;
    } 
  }
  
  private void init() {
    onStateChange(getState());
  }
  
  private int parseItem(@NonNull Context paramContext, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    // Byte code:
    //   0: aload_2
    //   1: aload #5
    //   3: aload #4
    //   5: getstatic androidx/appcompat/R$styleable.AnimatedStateListDrawableItem : [I
    //   8: invokestatic obtainAttributes : (Landroid/content/res/Resources;Landroid/content/res/Resources$Theme;Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
    //   11: astore #9
    //   13: aload #9
    //   15: getstatic androidx/appcompat/R$styleable.AnimatedStateListDrawableItem_android_id : I
    //   18: iconst_0
    //   19: invokevirtual getResourceId : (II)I
    //   22: istore #6
    //   24: aconst_null
    //   25: astore #8
    //   27: aload #9
    //   29: getstatic androidx/appcompat/R$styleable.AnimatedStateListDrawableItem_android_drawable : I
    //   32: iconst_m1
    //   33: invokevirtual getResourceId : (II)I
    //   36: istore #7
    //   38: iload #7
    //   40: ifle -> 51
    //   43: aload_1
    //   44: iload #7
    //   46: invokestatic getDrawable : (Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    //   49: astore #8
    //   51: aload #9
    //   53: invokevirtual recycle : ()V
    //   56: aload_0
    //   57: aload #4
    //   59: invokevirtual extractStateSet : (Landroid/util/AttributeSet;)[I
    //   62: astore #9
    //   64: aload #8
    //   66: astore_1
    //   67: aload #8
    //   69: ifnonnull -> 148
    //   72: aload_3
    //   73: invokeinterface next : ()I
    //   78: istore #7
    //   80: iload #7
    //   82: iconst_4
    //   83: if_icmpeq -> 72
    //   86: iload #7
    //   88: iconst_2
    //   89: if_icmpeq -> 124
    //   92: new org/xmlpull/v1/XmlPullParserException
    //   95: dup
    //   96: new java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial <init> : ()V
    //   103: aload_3
    //   104: invokeinterface getPositionDescription : ()Ljava/lang/String;
    //   109: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: ldc ': <item> tag requires a 'drawable' attribute or child tag defining a drawable'
    //   114: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: invokevirtual toString : ()Ljava/lang/String;
    //   120: invokespecial <init> : (Ljava/lang/String;)V
    //   123: athrow
    //   124: aload_3
    //   125: invokeinterface getName : ()Ljava/lang/String;
    //   130: ldc 'vector'
    //   132: invokevirtual equals : (Ljava/lang/Object;)Z
    //   135: ifeq -> 184
    //   138: aload_2
    //   139: aload_3
    //   140: aload #4
    //   142: aload #5
    //   144: invokestatic createFromXmlInner : (Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroidx/vectordrawable/graphics/drawable/VectorDrawableCompat;
    //   147: astore_1
    //   148: aload_1
    //   149: ifnonnull -> 216
    //   152: new org/xmlpull/v1/XmlPullParserException
    //   155: dup
    //   156: new java/lang/StringBuilder
    //   159: dup
    //   160: invokespecial <init> : ()V
    //   163: aload_3
    //   164: invokeinterface getPositionDescription : ()Ljava/lang/String;
    //   169: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: ldc ': <item> tag requires a 'drawable' attribute or child tag defining a drawable'
    //   174: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: invokevirtual toString : ()Ljava/lang/String;
    //   180: invokespecial <init> : (Ljava/lang/String;)V
    //   183: athrow
    //   184: getstatic android/os/Build$VERSION.SDK_INT : I
    //   187: bipush #21
    //   189: if_icmplt -> 205
    //   192: aload_2
    //   193: aload_3
    //   194: aload #4
    //   196: aload #5
    //   198: invokestatic createFromXmlInner : (Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;
    //   201: astore_1
    //   202: goto -> 148
    //   205: aload_2
    //   206: aload_3
    //   207: aload #4
    //   209: invokestatic createFromXmlInner : (Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;)Landroid/graphics/drawable/Drawable;
    //   212: astore_1
    //   213: goto -> 148
    //   216: aload_0
    //   217: getfield mState : Landroidx/appcompat/graphics/drawable/AnimatedStateListDrawableCompat$AnimatedStateListState;
    //   220: aload #9
    //   222: aload_1
    //   223: iload #6
    //   225: invokevirtual addStateSet : ([ILandroid/graphics/drawable/Drawable;I)I
    //   228: ireturn
  }
  
  private int parseTransition(@NonNull Context paramContext, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    // Byte code:
    //   0: aload_2
    //   1: aload #5
    //   3: aload #4
    //   5: getstatic androidx/appcompat/R$styleable.AnimatedStateListDrawableTransition : [I
    //   8: invokestatic obtainAttributes : (Landroid/content/res/Resources;Landroid/content/res/Resources$Theme;Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
    //   11: astore #10
    //   13: aload #10
    //   15: getstatic androidx/appcompat/R$styleable.AnimatedStateListDrawableTransition_android_fromId : I
    //   18: iconst_m1
    //   19: invokevirtual getResourceId : (II)I
    //   22: istore #6
    //   24: aload #10
    //   26: getstatic androidx/appcompat/R$styleable.AnimatedStateListDrawableTransition_android_toId : I
    //   29: iconst_m1
    //   30: invokevirtual getResourceId : (II)I
    //   33: istore #7
    //   35: aconst_null
    //   36: astore #11
    //   38: aload #10
    //   40: getstatic androidx/appcompat/R$styleable.AnimatedStateListDrawableTransition_android_drawable : I
    //   43: iconst_m1
    //   44: invokevirtual getResourceId : (II)I
    //   47: istore #8
    //   49: iload #8
    //   51: ifle -> 62
    //   54: aload_1
    //   55: iload #8
    //   57: invokestatic getDrawable : (Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    //   60: astore #11
    //   62: aload #10
    //   64: getstatic androidx/appcompat/R$styleable.AnimatedStateListDrawableTransition_android_reversible : I
    //   67: iconst_0
    //   68: invokevirtual getBoolean : (IZ)Z
    //   71: istore #9
    //   73: aload #10
    //   75: invokevirtual recycle : ()V
    //   78: aload #11
    //   80: astore #10
    //   82: aload #11
    //   84: ifnonnull -> 166
    //   87: aload_3
    //   88: invokeinterface next : ()I
    //   93: istore #8
    //   95: iload #8
    //   97: iconst_4
    //   98: if_icmpeq -> 87
    //   101: iload #8
    //   103: iconst_2
    //   104: if_icmpeq -> 139
    //   107: new org/xmlpull/v1/XmlPullParserException
    //   110: dup
    //   111: new java/lang/StringBuilder
    //   114: dup
    //   115: invokespecial <init> : ()V
    //   118: aload_3
    //   119: invokeinterface getPositionDescription : ()Ljava/lang/String;
    //   124: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: ldc ': <transition> tag requires a 'drawable' attribute or child tag defining a drawable'
    //   129: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: invokevirtual toString : ()Ljava/lang/String;
    //   135: invokespecial <init> : (Ljava/lang/String;)V
    //   138: athrow
    //   139: aload_3
    //   140: invokeinterface getName : ()Ljava/lang/String;
    //   145: ldc_w 'animated-vector'
    //   148: invokevirtual equals : (Ljava/lang/Object;)Z
    //   151: ifeq -> 203
    //   154: aload_1
    //   155: aload_2
    //   156: aload_3
    //   157: aload #4
    //   159: aload #5
    //   161: invokestatic createFromXmlInner : (Landroid/content/Context;Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroidx/vectordrawable/graphics/drawable/AnimatedVectorDrawableCompat;
    //   164: astore #10
    //   166: aload #10
    //   168: ifnonnull -> 237
    //   171: new org/xmlpull/v1/XmlPullParserException
    //   174: dup
    //   175: new java/lang/StringBuilder
    //   178: dup
    //   179: invokespecial <init> : ()V
    //   182: aload_3
    //   183: invokeinterface getPositionDescription : ()Ljava/lang/String;
    //   188: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: ldc ': <transition> tag requires a 'drawable' attribute or child tag defining a drawable'
    //   193: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: invokevirtual toString : ()Ljava/lang/String;
    //   199: invokespecial <init> : (Ljava/lang/String;)V
    //   202: athrow
    //   203: getstatic android/os/Build$VERSION.SDK_INT : I
    //   206: bipush #21
    //   208: if_icmplt -> 225
    //   211: aload_2
    //   212: aload_3
    //   213: aload #4
    //   215: aload #5
    //   217: invokestatic createFromXmlInner : (Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;
    //   220: astore #10
    //   222: goto -> 166
    //   225: aload_2
    //   226: aload_3
    //   227: aload #4
    //   229: invokestatic createFromXmlInner : (Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;)Landroid/graphics/drawable/Drawable;
    //   232: astore #10
    //   234: goto -> 166
    //   237: iload #6
    //   239: iconst_m1
    //   240: if_icmpeq -> 249
    //   243: iload #7
    //   245: iconst_m1
    //   246: if_icmpne -> 281
    //   249: new org/xmlpull/v1/XmlPullParserException
    //   252: dup
    //   253: new java/lang/StringBuilder
    //   256: dup
    //   257: invokespecial <init> : ()V
    //   260: aload_3
    //   261: invokeinterface getPositionDescription : ()Ljava/lang/String;
    //   266: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: ldc ': <transition> tag requires 'fromId' & 'toId' attributes'
    //   271: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: invokevirtual toString : ()Ljava/lang/String;
    //   277: invokespecial <init> : (Ljava/lang/String;)V
    //   280: athrow
    //   281: aload_0
    //   282: getfield mState : Landroidx/appcompat/graphics/drawable/AnimatedStateListDrawableCompat$AnimatedStateListState;
    //   285: iload #6
    //   287: iload #7
    //   289: aload #10
    //   291: iload #9
    //   293: invokevirtual addTransition : (IILandroid/graphics/drawable/Drawable;Z)I
    //   296: ireturn
  }
  
  private boolean selectTransition(int paramInt) {
    int i;
    Transition transition = this.mTransition;
    if (transition != null) {
      if (paramInt == this.mTransitionToIndex)
        return true; 
      if (paramInt == this.mTransitionFromIndex && transition.canReverse()) {
        transition.reverse();
        this.mTransitionToIndex = this.mTransitionFromIndex;
        this.mTransitionFromIndex = paramInt;
        return true;
      } 
      i = this.mTransitionToIndex;
      transition.stop();
    } else {
      i = getCurrentIndex();
    } 
    this.mTransition = null;
    this.mTransitionFromIndex = -1;
    this.mTransitionToIndex = -1;
    AnimatedStateListState animatedStateListState = this.mState;
    int j = animatedStateListState.getKeyframeIdAt(i);
    int k = animatedStateListState.getKeyframeIdAt(paramInt);
    if (k == 0 || j == 0)
      return false; 
    int m = animatedStateListState.indexOfTransition(j, k);
    if (m < 0)
      return false; 
    boolean bool = animatedStateListState.transitionHasReversibleFlag(j, k);
    selectDrawable(m);
    Drawable drawable = getCurrent();
    if (drawable instanceof AnimationDrawable) {
      boolean bool1 = animatedStateListState.isTransitionReversed(j, k);
      AnimationDrawableTransition animationDrawableTransition = new AnimationDrawableTransition((AnimationDrawable)drawable, bool1, bool);
      animationDrawableTransition.start();
      this.mTransition = animationDrawableTransition;
      this.mTransitionFromIndex = i;
      this.mTransitionToIndex = paramInt;
      return true;
    } 
    if (drawable instanceof AnimatedVectorDrawableCompat) {
      AnimatedVectorDrawableTransition animatedVectorDrawableTransition = new AnimatedVectorDrawableTransition((AnimatedVectorDrawableCompat)drawable);
      animatedVectorDrawableTransition.start();
      this.mTransition = animatedVectorDrawableTransition;
      this.mTransitionFromIndex = i;
      this.mTransitionToIndex = paramInt;
      return true;
    } 
    if (drawable instanceof Animatable) {
      AnimatableTransition animatableTransition = new AnimatableTransition((Animatable)drawable);
      animatableTransition.start();
      this.mTransition = animatableTransition;
      this.mTransitionFromIndex = i;
      this.mTransitionToIndex = paramInt;
      return true;
    } 
    return false;
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    AnimatedStateListState animatedStateListState = this.mState;
    if (Build.VERSION.SDK_INT >= 21)
      animatedStateListState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations(); 
    animatedStateListState.setVariablePadding(paramTypedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_variablePadding, animatedStateListState.mVariablePadding));
    animatedStateListState.setConstantSize(paramTypedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_constantSize, animatedStateListState.mConstantSize));
    animatedStateListState.setEnterFadeDuration(paramTypedArray.getInt(R.styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, animatedStateListState.mEnterFadeDuration));
    animatedStateListState.setExitFadeDuration(paramTypedArray.getInt(R.styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, animatedStateListState.mExitFadeDuration));
    setDither(paramTypedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_dither, animatedStateListState.mDither));
  }
  
  public void addState(@NonNull int[] paramArrayOfint, @NonNull Drawable paramDrawable, int paramInt) {
    if (paramDrawable == null)
      throw new IllegalArgumentException("Drawable must not be null"); 
    this.mState.addStateSet(paramArrayOfint, paramDrawable, paramInt);
    onStateChange(getState());
  }
  
  public <T extends Drawable & Animatable> void addTransition(int paramInt1, int paramInt2, @NonNull T paramT, boolean paramBoolean) {
    if (paramT == null)
      throw new IllegalArgumentException("Transition drawable must not be null"); 
    this.mState.addTransition(paramInt1, paramInt2, (Drawable)paramT, paramBoolean);
  }
  
  void clearMutated() {
    super.clearMutated();
    this.mMutated = false;
  }
  
  AnimatedStateListState cloneConstantState() {
    return new AnimatedStateListState(this.mState, this, null);
  }
  
  public void inflate(@NonNull Context paramContext, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedStateListDrawableCompat);
    setVisible(typedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
    updateStateFromTypedArray(typedArray);
    updateDensity(paramResources);
    typedArray.recycle();
    inflateChildElements(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    init();
  }
  
  public boolean isStateful() {
    return true;
  }
  
  public void jumpToCurrentState() {
    super.jumpToCurrentState();
    if (this.mTransition != null) {
      this.mTransition.stop();
      this.mTransition = null;
      selectDrawable(this.mTransitionToIndex);
      this.mTransitionToIndex = -1;
      this.mTransitionFromIndex = -1;
    } 
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mState.mutate();
      this.mMutated = true;
    } 
    return this;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    boolean bool1;
    int i = this.mState.indexOfKeyframe(paramArrayOfint);
    if (i != getCurrentIndex() && (selectTransition(i) || selectDrawable(i))) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    Drawable drawable = getCurrent();
    boolean bool2 = bool1;
    if (drawable != null)
      bool2 = bool1 | drawable.setState(paramArrayOfint); 
    return bool2;
  }
  
  protected void setConstantState(@NonNull DrawableContainer.DrawableContainerState paramDrawableContainerState) {
    super.setConstantState(paramDrawableContainerState);
    if (paramDrawableContainerState instanceof AnimatedStateListState)
      this.mState = (AnimatedStateListState)paramDrawableContainerState; 
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    if (this.mTransition != null && (bool || paramBoolean2)) {
      if (paramBoolean1) {
        this.mTransition.start();
        return bool;
      } 
    } else {
      return bool;
    } 
    jumpToCurrentState();
    return bool;
  }
  
  private static class AnimatableTransition extends Transition {
    private final Animatable mA;
    
    AnimatableTransition(Animatable param1Animatable) {
      this.mA = param1Animatable;
    }
    
    public void start() {
      this.mA.start();
    }
    
    public void stop() {
      this.mA.stop();
    }
  }
  
  static class AnimatedStateListState extends StateListDrawable.StateListState {
    private static final long REVERSED_BIT = 4294967296L;
    
    private static final long REVERSIBLE_FLAG_BIT = 8589934592L;
    
    SparseArrayCompat<Integer> mStateIds;
    
    LongSparseArray<Long> mTransitions;
    
    AnimatedStateListState(@Nullable AnimatedStateListState param1AnimatedStateListState, @NonNull AnimatedStateListDrawableCompat param1AnimatedStateListDrawableCompat, @Nullable Resources param1Resources) {
      super(param1AnimatedStateListState, param1AnimatedStateListDrawableCompat, param1Resources);
      if (param1AnimatedStateListState != null) {
        this.mTransitions = param1AnimatedStateListState.mTransitions;
        this.mStateIds = param1AnimatedStateListState.mStateIds;
        return;
      } 
      this.mTransitions = new LongSparseArray();
      this.mStateIds = new SparseArrayCompat();
    }
    
    private static long generateTransitionKey(int param1Int1, int param1Int2) {
      return param1Int1 << 32L | param1Int2;
    }
    
    int addStateSet(@NonNull int[] param1ArrayOfint, @NonNull Drawable param1Drawable, int param1Int) {
      int i = addStateSet(param1ArrayOfint, param1Drawable);
      this.mStateIds.put(i, Integer.valueOf(param1Int));
      return i;
    }
    
    int addTransition(int param1Int1, int param1Int2, @NonNull Drawable param1Drawable, boolean param1Boolean) {
      int i = addChild(param1Drawable);
      long l2 = generateTransitionKey(param1Int1, param1Int2);
      long l1 = 0L;
      if (param1Boolean)
        l1 = 8589934592L; 
      this.mTransitions.append(l2, Long.valueOf(i | l1));
      if (param1Boolean) {
        l2 = generateTransitionKey(param1Int2, param1Int1);
        this.mTransitions.append(l2, Long.valueOf(i | 0x100000000L | l1));
      } 
      return i;
    }
    
    int getKeyframeIdAt(int param1Int) {
      return (param1Int < 0) ? 0 : ((Integer)this.mStateIds.get(param1Int, Integer.valueOf(0))).intValue();
    }
    
    int indexOfKeyframe(@NonNull int[] param1ArrayOfint) {
      int i = indexOfStateSet(param1ArrayOfint);
      return (i >= 0) ? i : indexOfStateSet(StateSet.WILD_CARD);
    }
    
    int indexOfTransition(int param1Int1, int param1Int2) {
      long l = generateTransitionKey(param1Int1, param1Int2);
      return (int)((Long)this.mTransitions.get(l, Long.valueOf(-1L))).longValue();
    }
    
    boolean isTransitionReversed(int param1Int1, int param1Int2) {
      long l = generateTransitionKey(param1Int1, param1Int2);
      return ((((Long)this.mTransitions.get(l, Long.valueOf(-1L))).longValue() & 0x100000000L) != 0L);
    }
    
    void mutate() {
      this.mTransitions = this.mTransitions.clone();
      this.mStateIds = this.mStateIds.clone();
    }
    
    @NonNull
    public Drawable newDrawable() {
      return new AnimatedStateListDrawableCompat(this, null);
    }
    
    @NonNull
    public Drawable newDrawable(Resources param1Resources) {
      return new AnimatedStateListDrawableCompat(this, param1Resources);
    }
    
    boolean transitionHasReversibleFlag(int param1Int1, int param1Int2) {
      long l = generateTransitionKey(param1Int1, param1Int2);
      return ((((Long)this.mTransitions.get(l, Long.valueOf(-1L))).longValue() & 0x200000000L) != 0L);
    }
  }
  
  private static class AnimatedVectorDrawableTransition extends Transition {
    private final AnimatedVectorDrawableCompat mAvd;
    
    AnimatedVectorDrawableTransition(AnimatedVectorDrawableCompat param1AnimatedVectorDrawableCompat) {
      this.mAvd = param1AnimatedVectorDrawableCompat;
    }
    
    public void start() {
      this.mAvd.start();
    }
    
    public void stop() {
      this.mAvd.stop();
    }
  }
  
  private static class AnimationDrawableTransition extends Transition {
    private final ObjectAnimator mAnim;
    
    private final boolean mHasReversibleFlag;
    
    AnimationDrawableTransition(AnimationDrawable param1AnimationDrawable, boolean param1Boolean1, boolean param1Boolean2) {
      boolean bool;
      int i = param1AnimationDrawable.getNumberOfFrames();
      if (param1Boolean1) {
        bool = i - 1;
      } else {
        bool = false;
      } 
      if (param1Boolean1) {
        i = 0;
      } else {
        i--;
      } 
      AnimatedStateListDrawableCompat.FrameInterpolator frameInterpolator = new AnimatedStateListDrawableCompat.FrameInterpolator(param1AnimationDrawable, param1Boolean1);
      ObjectAnimator objectAnimator = ObjectAnimator.ofInt(param1AnimationDrawable, "currentIndex", new int[] { bool, i });
      if (Build.VERSION.SDK_INT >= 18)
        objectAnimator.setAutoCancel(true); 
      objectAnimator.setDuration(frameInterpolator.getTotalDuration());
      objectAnimator.setInterpolator(frameInterpolator);
      this.mHasReversibleFlag = param1Boolean2;
      this.mAnim = objectAnimator;
    }
    
    public boolean canReverse() {
      return this.mHasReversibleFlag;
    }
    
    public void reverse() {
      this.mAnim.reverse();
    }
    
    public void start() {
      this.mAnim.start();
    }
    
    public void stop() {
      this.mAnim.cancel();
    }
  }
  
  private static class FrameInterpolator implements TimeInterpolator {
    private int[] mFrameTimes;
    
    private int mFrames;
    
    private int mTotalDuration;
    
    FrameInterpolator(AnimationDrawable param1AnimationDrawable, boolean param1Boolean) {
      updateFrames(param1AnimationDrawable, param1Boolean);
    }
    
    public float getInterpolation(float param1Float) {
      int j = (int)(this.mTotalDuration * param1Float + 0.5F);
      int k = this.mFrames;
      int[] arrayOfInt = this.mFrameTimes;
      int i;
      for (i = 0; i < k && j >= arrayOfInt[i]; i++)
        j -= arrayOfInt[i]; 
      if (i < k) {
        param1Float = j / this.mTotalDuration;
        return i / k + param1Float;
      } 
      param1Float = 0.0F;
      return i / k + param1Float;
    }
    
    int getTotalDuration() {
      return this.mTotalDuration;
    }
    
    int updateFrames(AnimationDrawable param1AnimationDrawable, boolean param1Boolean) {
      int k = param1AnimationDrawable.getNumberOfFrames();
      this.mFrames = k;
      if (this.mFrameTimes == null || this.mFrameTimes.length < k)
        this.mFrameTimes = new int[k]; 
      int[] arrayOfInt = this.mFrameTimes;
      int j = 0;
      for (int i = 0; i < k; i++) {
        if (param1Boolean) {
          m = k - i - 1;
        } else {
          m = i;
        } 
        int m = param1AnimationDrawable.getDuration(m);
        arrayOfInt[i] = m;
        j += m;
      } 
      this.mTotalDuration = j;
      return j;
    }
  }
  
  private static abstract class Transition {
    private Transition() {}
    
    public boolean canReverse() {
      return false;
    }
    
    public void reverse() {}
    
    public abstract void start();
    
    public abstract void stop();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/graphics/drawable/AnimatedStateListDrawableCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
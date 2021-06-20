package androidx.appcompat.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.TypedArrayUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class StateListDrawable extends DrawableContainer {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "StateListDrawable";
  
  private boolean mMutated;
  
  private StateListState mStateListState;
  
  StateListDrawable() {
    this((StateListState)null, (Resources)null);
  }
  
  StateListDrawable(@Nullable StateListState paramStateListState) {
    if (paramStateListState != null)
      setConstantState(paramStateListState); 
  }
  
  StateListDrawable(StateListState paramStateListState, Resources paramResources) {
    setConstantState(new StateListState(paramStateListState, this, paramResources));
    onStateChange(getState());
  }
  
  private void inflateChildElements(Context paramContext, Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    StateListState stateListState = this.mStateListState;
    int i = paramXmlPullParser.getDepth() + 1;
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          if (j == 2 && k <= i && paramXmlPullParser.getName().equals("item")) {
            TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.StateListDrawableItem);
            Drawable drawable1 = null;
            j = typedArray.getResourceId(R.styleable.StateListDrawableItem_android_drawable, -1);
            if (j > 0)
              drawable1 = AppCompatResources.getDrawable(paramContext, j); 
            typedArray.recycle();
            int[] arrayOfInt = extractStateSet(paramAttributeSet);
            Drawable drawable2 = drawable1;
            if (drawable1 == null) {
              while (true) {
                j = paramXmlPullParser.next();
                if (j != 4) {
                  if (j != 2)
                    throw new XmlPullParserException(paramXmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or " + "child tag defining a drawable"); 
                  if (Build.VERSION.SDK_INT >= 21) {
                    drawable2 = Drawable.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
                    continue;
                  } 
                  break;
                } 
              } 
              drawable2 = Drawable.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet);
            } 
            continue;
          } 
          continue;
        } 
      } 
      break;
      stateListState.addStateSet((int[])SYNTHETIC_LOCAL_VARIABLE_12, (Drawable)SYNTHETIC_LOCAL_VARIABLE_10);
    } 
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    StateListState stateListState = this.mStateListState;
    if (Build.VERSION.SDK_INT >= 21)
      stateListState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations(); 
    stateListState.mVariablePadding = paramTypedArray.getBoolean(R.styleable.StateListDrawable_android_variablePadding, stateListState.mVariablePadding);
    stateListState.mConstantSize = paramTypedArray.getBoolean(R.styleable.StateListDrawable_android_constantSize, stateListState.mConstantSize);
    stateListState.mEnterFadeDuration = paramTypedArray.getInt(R.styleable.StateListDrawable_android_enterFadeDuration, stateListState.mEnterFadeDuration);
    stateListState.mExitFadeDuration = paramTypedArray.getInt(R.styleable.StateListDrawable_android_exitFadeDuration, stateListState.mExitFadeDuration);
    stateListState.mDither = paramTypedArray.getBoolean(R.styleable.StateListDrawable_android_dither, stateListState.mDither);
  }
  
  public void addState(int[] paramArrayOfint, Drawable paramDrawable) {
    if (paramDrawable != null) {
      this.mStateListState.addStateSet(paramArrayOfint, paramDrawable);
      onStateChange(getState());
    } 
  }
  
  @RequiresApi(21)
  public void applyTheme(@NonNull Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    onStateChange(getState());
  }
  
  void clearMutated() {
    super.clearMutated();
    this.mMutated = false;
  }
  
  StateListState cloneConstantState() {
    return new StateListState(this.mStateListState, this, null);
  }
  
  int[] extractStateSet(AttributeSet paramAttributeSet) {
    int k = paramAttributeSet.getAttributeCount();
    int[] arrayOfInt = new int[k];
    int j = 0;
    int i = 0;
    while (true) {
      int m;
      int n;
      if (j < k) {
        m = paramAttributeSet.getAttributeNameResource(j);
        switch (m) {
          default:
            n = i + 1;
          case 0:
          case 16842960:
          case 16843161:
            continue;
        } 
        m = -m;
      } else {
        break;
      } 
      arrayOfInt[i] = m;
      i = n;
      j++;
    } 
    return StateSet.trimStateSet(arrayOfInt, i);
  }
  
  int getStateCount() {
    return this.mStateListState.getChildCount();
  }
  
  Drawable getStateDrawable(int paramInt) {
    return this.mStateListState.getChild(paramInt);
  }
  
  int getStateDrawableIndex(int[] paramArrayOfint) {
    return this.mStateListState.indexOfStateSet(paramArrayOfint);
  }
  
  StateListState getStateListState() {
    return this.mStateListState;
  }
  
  int[] getStateSet(int paramInt) {
    return this.mStateListState.mStateSets[paramInt];
  }
  
  public void inflate(@NonNull Context paramContext, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.StateListDrawable);
    setVisible(typedArray.getBoolean(R.styleable.StateListDrawable_android_visible, true), true);
    updateStateFromTypedArray(typedArray);
    updateDensity(paramResources);
    typedArray.recycle();
    inflateChildElements(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    onStateChange(getState());
  }
  
  public boolean isStateful() {
    return true;
  }
  
  @NonNull
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mStateListState.mutate();
      this.mMutated = true;
    } 
    return this;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    boolean bool = super.onStateChange(paramArrayOfint);
    int j = this.mStateListState.indexOfStateSet(paramArrayOfint);
    int i = j;
    if (j < 0)
      i = this.mStateListState.indexOfStateSet(StateSet.WILD_CARD); 
    return (selectDrawable(i) || bool);
  }
  
  protected void setConstantState(@NonNull DrawableContainer.DrawableContainerState paramDrawableContainerState) {
    super.setConstantState(paramDrawableContainerState);
    if (paramDrawableContainerState instanceof StateListState)
      this.mStateListState = (StateListState)paramDrawableContainerState; 
  }
  
  static class StateListState extends DrawableContainer.DrawableContainerState {
    int[][] mStateSets;
    
    StateListState(StateListState param1StateListState, StateListDrawable param1StateListDrawable, Resources param1Resources) {
      super(param1StateListState, param1StateListDrawable, param1Resources);
      if (param1StateListState != null) {
        this.mStateSets = param1StateListState.mStateSets;
        return;
      } 
      this.mStateSets = new int[getCapacity()][];
    }
    
    int addStateSet(int[] param1ArrayOfint, Drawable param1Drawable) {
      int i = addChild(param1Drawable);
      this.mStateSets[i] = param1ArrayOfint;
      return i;
    }
    
    public void growArray(int param1Int1, int param1Int2) {
      super.growArray(param1Int1, param1Int2);
      int[][] arrayOfInt = new int[param1Int2][];
      System.arraycopy(this.mStateSets, 0, arrayOfInt, 0, param1Int1);
      this.mStateSets = arrayOfInt;
    }
    
    int indexOfStateSet(int[] param1ArrayOfint) {
      int[][] arrayOfInt = this.mStateSets;
      int j = getChildCount();
      for (int i = 0; i < j; i++) {
        if (StateSet.stateSetMatches(arrayOfInt[i], param1ArrayOfint))
          return i; 
      } 
      return -1;
    }
    
    void mutate() {
      int[][] arrayOfInt = new int[this.mStateSets.length][];
      for (int i = this.mStateSets.length - 1; i >= 0; i--) {
        int[] arrayOfInt1;
        if (this.mStateSets[i] != null) {
          arrayOfInt1 = (int[])this.mStateSets[i].clone();
        } else {
          arrayOfInt1 = null;
        } 
        arrayOfInt[i] = arrayOfInt1;
      } 
      this.mStateSets = arrayOfInt;
    }
    
    @NonNull
    public Drawable newDrawable() {
      return new StateListDrawable(this, null);
    }
    
    @NonNull
    public Drawable newDrawable(Resources param1Resources) {
      return new StateListDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/graphics/drawable/StateListDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
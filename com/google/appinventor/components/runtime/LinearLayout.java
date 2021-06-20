package com.google.appinventor.components.runtime;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public final class LinearLayout implements Layout {
  private final android.widget.LinearLayout layoutManager;
  
  LinearLayout(Context paramContext, int paramInt) {
    this(paramContext, paramInt, null, null);
  }
  
  LinearLayout(Context paramContext, int paramInt, final Integer preferredEmptyWidth, final Integer preferredEmptyHeight) {
    if ((preferredEmptyWidth == null && preferredEmptyHeight != null) || (preferredEmptyWidth != null && preferredEmptyHeight == null))
      throw new IllegalArgumentException("LinearLayout - preferredEmptyWidth and preferredEmptyHeight must be either both null or both not null"); 
    this.layoutManager = new android.widget.LinearLayout(paramContext) {
        private int getSize(int param1Int1, int param1Int2) {
          int j = View.MeasureSpec.getMode(param1Int1);
          int i = View.MeasureSpec.getSize(param1Int1);
          if (j == 1073741824)
            return i; 
          param1Int1 = param1Int2;
          return (j == Integer.MIN_VALUE) ? Math.min(param1Int2, i) : param1Int1;
        }
        
        protected void onMeasure(int param1Int1, int param1Int2) {
          if (preferredEmptyWidth == null || preferredEmptyHeight == null) {
            super.onMeasure(param1Int1, param1Int2);
            return;
          } 
          if (getChildCount() != 0) {
            super.onMeasure(param1Int1, param1Int2);
            return;
          } 
          setMeasuredDimension(getSize(param1Int1, preferredEmptyWidth.intValue()), getSize(param1Int2, preferredEmptyHeight.intValue()));
        }
      };
    android.widget.LinearLayout linearLayout = this.layoutManager;
    if (paramInt == 0) {
      paramInt = 0;
    } else {
      paramInt = 1;
    } 
    linearLayout.setOrientation(paramInt);
  }
  
  public void add(AndroidViewComponent paramAndroidViewComponent) {
    this.layoutManager.addView(paramAndroidViewComponent.getView(), (ViewGroup.LayoutParams)new android.widget.LinearLayout.LayoutParams(-2, -2, 0.0F));
  }
  
  public ViewGroup getLayoutManager() {
    return (ViewGroup)this.layoutManager;
  }
  
  public void setBaselineAligned(boolean paramBoolean) {
    this.layoutManager.setBaselineAligned(paramBoolean);
  }
  
  public void setHorizontalGravity(int paramInt) {
    this.layoutManager.setHorizontalGravity(paramInt);
  }
  
  public void setVerticalGravity(int paramInt) {
    this.layoutManager.setVerticalGravity(paramInt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/LinearLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
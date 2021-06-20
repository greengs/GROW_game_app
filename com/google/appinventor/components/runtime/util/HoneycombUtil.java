package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

public class HoneycombUtil {
  public static final int VIEWGROUP_MEASURED_HEIGHT_STATE_SHIFT = 16;
  
  public static int combineMeasuredStates(ViewGroup paramViewGroup, int paramInt1, int paramInt2) {
    return ViewGroup.combineMeasuredStates(paramInt1, paramInt2);
  }
  
  public static int getMeasuredState(View paramView) {
    return paramView.getMeasuredState();
  }
  
  public static Spinner makeSpinner(Context paramContext) {
    return new Spinner(paramContext, 0);
  }
  
  public static int resolveSizeAndState(ViewGroup paramViewGroup, int paramInt1, int paramInt2, int paramInt3) {
    return ViewGroup.resolveSizeAndState(paramInt1, paramInt2, paramInt3);
  }
  
  public static void viewSetRotate(View paramView, double paramDouble) {
    paramView.setRotation((float)paramDouble);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/HoneycombUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package com.google.appinventor.components.runtime.util;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

public final class ViewUtil {
  private static int calculatePixels(View paramView, int paramInt) {
    return (int)((paramView.getContext().getResources().getDisplayMetrics()).density * paramInt);
  }
  
  public static void setBackgroundDrawable(View paramView, Drawable paramDrawable) {
    paramView.setBackgroundDrawable(paramDrawable);
    paramView.invalidate();
  }
  
  public static void setBackgroundImage(View paramView, Drawable paramDrawable) {
    paramView.setBackgroundDrawable(paramDrawable);
    paramView.requestLayout();
  }
  
  public static void setChildHeightForHorizontalLayout(View paramView, int paramInt) {
    ViewGroup.LayoutParams layoutParams = paramView.getLayoutParams();
    if (layoutParams instanceof LinearLayout.LayoutParams) {
      LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams)layoutParams;
      switch (paramInt) {
        default:
          layoutParams1.height = calculatePixels(paramView, paramInt);
          paramView.requestLayout();
          return;
        case -1:
          layoutParams1.height = -2;
          paramView.requestLayout();
          return;
        case -2:
          break;
      } 
      layoutParams1.height = -1;
    } else {
      Log.e("ViewUtil", "The view does not have linear layout parameters");
      return;
    } 
    paramView.requestLayout();
  }
  
  public static void setChildHeightForTableLayout(View paramView, int paramInt) {
    ViewGroup.LayoutParams layoutParams = paramView.getLayoutParams();
    if (layoutParams instanceof TableRow.LayoutParams) {
      TableRow.LayoutParams layoutParams1 = (TableRow.LayoutParams)layoutParams;
      switch (paramInt) {
        default:
          layoutParams1.height = calculatePixels(paramView, paramInt);
          paramView.requestLayout();
          return;
        case -1:
          layoutParams1.height = -2;
          paramView.requestLayout();
          return;
        case -2:
          break;
      } 
      layoutParams1.height = -1;
    } else {
      Log.e("ViewUtil", "The view does not have table layout parameters");
      return;
    } 
    paramView.requestLayout();
  }
  
  public static void setChildHeightForVerticalLayout(View paramView, int paramInt) {
    ViewGroup.LayoutParams layoutParams = paramView.getLayoutParams();
    if (layoutParams instanceof LinearLayout.LayoutParams) {
      LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams)layoutParams;
      switch (paramInt) {
        default:
          layoutParams1.height = calculatePixels(paramView, paramInt);
          layoutParams1.weight = 0.0F;
          paramView.requestLayout();
          return;
        case -1:
          layoutParams1.height = -2;
          layoutParams1.weight = 0.0F;
          paramView.requestLayout();
          return;
        case -2:
          break;
      } 
      layoutParams1.height = 0;
      layoutParams1.weight = 1.0F;
    } else {
      Log.e("ViewUtil", "The view does not have linear layout parameters");
      return;
    } 
    paramView.requestLayout();
  }
  
  public static void setChildWidthForHorizontalLayout(View paramView, int paramInt) {
    ViewGroup.LayoutParams layoutParams = paramView.getLayoutParams();
    if (layoutParams instanceof LinearLayout.LayoutParams) {
      LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams)layoutParams;
      switch (paramInt) {
        default:
          layoutParams1.width = calculatePixels(paramView, paramInt);
          layoutParams1.weight = 0.0F;
          paramView.requestLayout();
          return;
        case -1:
          layoutParams1.width = -2;
          layoutParams1.weight = 0.0F;
          paramView.requestLayout();
          return;
        case -2:
          break;
      } 
      layoutParams1.width = 0;
      layoutParams1.weight = 1.0F;
    } else {
      Log.e("ViewUtil", "The view does not have linear layout parameters");
      return;
    } 
    paramView.requestLayout();
  }
  
  public static void setChildWidthForTableLayout(View paramView, int paramInt) {
    ViewGroup.LayoutParams layoutParams = paramView.getLayoutParams();
    if (layoutParams instanceof TableRow.LayoutParams) {
      TableRow.LayoutParams layoutParams1 = (TableRow.LayoutParams)layoutParams;
      switch (paramInt) {
        default:
          layoutParams1.width = calculatePixels(paramView, paramInt);
          paramView.requestLayout();
          return;
        case -1:
          layoutParams1.width = -2;
          paramView.requestLayout();
          return;
        case -2:
          break;
      } 
      layoutParams1.width = -1;
    } else {
      Log.e("ViewUtil", "The view does not have table layout parameters");
      return;
    } 
    paramView.requestLayout();
  }
  
  public static void setChildWidthForVerticalLayout(View paramView, int paramInt) {
    ViewGroup.LayoutParams layoutParams = paramView.getLayoutParams();
    if (layoutParams instanceof LinearLayout.LayoutParams) {
      LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams)layoutParams;
      switch (paramInt) {
        default:
          layoutParams1.width = calculatePixels(paramView, paramInt);
          paramView.requestLayout();
          return;
        case -1:
          layoutParams1.width = -2;
          paramView.requestLayout();
          return;
        case -2:
          break;
      } 
      layoutParams1.width = -1;
    } else {
      Log.e("ViewUtil", "The view does not have linear layout parameters");
      return;
    } 
    paramView.requestLayout();
  }
  
  public static void setImage(ImageView paramImageView, Drawable paramDrawable) {
    paramImageView.setImageDrawable(paramDrawable);
    if (paramDrawable != null)
      paramImageView.setAdjustViewBounds(true); 
    paramImageView.requestLayout();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/ViewUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
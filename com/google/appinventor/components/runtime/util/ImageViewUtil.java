package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.widget.ImageViewCompat;
import java.util.LinkedList;

public final class ImageViewUtil {
  private static ImageView findOverflowMenuView(Activity paramActivity) {
    ViewGroup viewGroup = (ViewGroup)paramActivity.getWindow().getDecorView();
    LinkedList<ViewGroup> linkedList = new LinkedList();
    linkedList.add(viewGroup);
    while (linkedList.size() > 0) {
      viewGroup = linkedList.poll();
      for (int i = 0; i < viewGroup.getChildCount(); i++) {
        View view = viewGroup.getChildAt(i);
        if (view instanceof ImageView)
          return (ImageView)view; 
        if (view instanceof ViewGroup)
          linkedList.add((ViewGroup)view); 
      } 
    } 
    return null;
  }
  
  public static void setMenuButtonColor(Activity paramActivity, int paramInt) {
    ColorStateList colorStateList = new ColorStateList(new int[][] { {} }, new int[] { paramInt });
    ImageView imageView = findOverflowMenuView(paramActivity);
    if (imageView != null) {
      ImageViewCompat.setImageTintMode(imageView, PorterDuff.Mode.MULTIPLY);
      ImageViewCompat.setImageTintList(imageView, colorStateList);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/ImageViewUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
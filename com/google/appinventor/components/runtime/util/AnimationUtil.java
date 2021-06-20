package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

public final class AnimationUtil {
  public static void ApplyAnimation(View paramView, String paramString) {
    if (paramString.equals("ScrollRightSlow")) {
      ApplyHorizontalScrollAnimation(paramView, false, 8000);
      return;
    } 
    if (paramString.equals("ScrollRight")) {
      ApplyHorizontalScrollAnimation(paramView, false, 4000);
      return;
    } 
    if (paramString.equals("ScrollRightFast")) {
      ApplyHorizontalScrollAnimation(paramView, false, 1000);
      return;
    } 
    if (paramString.equals("ScrollLeftSlow")) {
      ApplyHorizontalScrollAnimation(paramView, true, 8000);
      return;
    } 
    if (paramString.equals("ScrollLeft")) {
      ApplyHorizontalScrollAnimation(paramView, true, 4000);
      return;
    } 
    if (paramString.equals("ScrollLeftFast")) {
      ApplyHorizontalScrollAnimation(paramView, true, 1000);
      return;
    } 
    if (paramString.equals("Stop")) {
      paramView.clearAnimation();
      return;
    } 
  }
  
  public static void ApplyCloseScreenAnimation(Activity paramActivity, String paramString) {
    if (paramString == null)
      return; 
    if (SdkLevel.getLevel() <= 4) {
      Log.e("AnimationUtil", "Screen animations are not available on android versions less than 2.0.");
      return;
    } 
    int i = 0;
    int j = 0;
    if (paramString.equalsIgnoreCase("fade")) {
      j = paramActivity.getResources().getIdentifier("fadeout", "anim", paramActivity.getPackageName());
      i = paramActivity.getResources().getIdentifier("hold", "anim", paramActivity.getPackageName());
    } else if (paramString.equalsIgnoreCase("zoom")) {
      j = paramActivity.getResources().getIdentifier("zoom_exit_reverse", "anim", paramActivity.getPackageName());
      i = paramActivity.getResources().getIdentifier("zoom_enter_reverse", "anim", paramActivity.getPackageName());
    } else if (paramString.equalsIgnoreCase("slidehorizontal")) {
      j = paramActivity.getResources().getIdentifier("slide_exit_reverse", "anim", paramActivity.getPackageName());
      i = paramActivity.getResources().getIdentifier("slide_enter_reverse", "anim", paramActivity.getPackageName());
    } else if (paramString.equalsIgnoreCase("slidevertical")) {
      j = paramActivity.getResources().getIdentifier("slide_v_exit_reverse", "anim", paramActivity.getPackageName());
      i = paramActivity.getResources().getIdentifier("slide_v_enter_reverse", "anim", paramActivity.getPackageName());
    } else {
      if (paramString.equalsIgnoreCase("none")) {
        EclairUtil.overridePendingTransitions(paramActivity, i, j);
        return;
      } 
      return;
    } 
    EclairUtil.overridePendingTransitions(paramActivity, i, j);
  }
  
  private static void ApplyHorizontalScrollAnimation(View paramView, boolean paramBoolean, int paramInt) {
    float f;
    if (paramBoolean) {
      f = 1.0F;
    } else {
      f = -1.0F;
    } 
    AnimationSet animationSet = new AnimationSet(true);
    animationSet.setRepeatCount(-1);
    animationSet.setRepeatMode(1);
    TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.7F * f, 2, f * -0.7F, 2, 0.0F, 2, 0.0F);
    translateAnimation.setStartOffset(0L);
    translateAnimation.setDuration(paramInt);
    translateAnimation.setFillAfter(true);
    animationSet.addAnimation((Animation)translateAnimation);
    paramView.startAnimation((Animation)animationSet);
  }
  
  public static void ApplyOpenScreenAnimation(Activity paramActivity, String paramString) {
    if (paramString == null)
      return; 
    if (SdkLevel.getLevel() <= 4) {
      Log.e("AnimationUtil", "Screen animations are not available on android versions less than 2.0.");
      return;
    } 
    int i = 0;
    int j = 0;
    if (paramString.equalsIgnoreCase("fade")) {
      i = paramActivity.getResources().getIdentifier("fadein", "anim", paramActivity.getPackageName());
      j = paramActivity.getResources().getIdentifier("hold", "anim", paramActivity.getPackageName());
    } else if (paramString.equalsIgnoreCase("zoom")) {
      j = paramActivity.getResources().getIdentifier("zoom_exit", "anim", paramActivity.getPackageName());
      i = paramActivity.getResources().getIdentifier("zoom_enter", "anim", paramActivity.getPackageName());
    } else if (paramString.equalsIgnoreCase("slidehorizontal")) {
      j = paramActivity.getResources().getIdentifier("slide_exit", "anim", paramActivity.getPackageName());
      i = paramActivity.getResources().getIdentifier("slide_enter", "anim", paramActivity.getPackageName());
    } else if (paramString.equalsIgnoreCase("slidevertical")) {
      j = paramActivity.getResources().getIdentifier("slide_v_exit", "anim", paramActivity.getPackageName());
      i = paramActivity.getResources().getIdentifier("slide_v_enter", "anim", paramActivity.getPackageName());
    } else {
      if (paramString.equalsIgnoreCase("none")) {
        EclairUtil.overridePendingTransitions(paramActivity, i, j);
        return;
      } 
      return;
    } 
    EclairUtil.overridePendingTransitions(paramActivity, i, j);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/AnimationUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
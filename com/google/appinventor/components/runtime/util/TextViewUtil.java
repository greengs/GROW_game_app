package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Html;
import android.widget.TextView;

public class TextViewUtil {
  public static float getFontSize(TextView paramTextView, Context paramContext) {
    float f = (paramContext.getResources().getDisplayMetrics()).scaledDensity;
    return paramTextView.getTextSize() / f;
  }
  
  public static String getText(TextView paramTextView) {
    return paramTextView.getText().toString();
  }
  
  public static boolean isEnabled(TextView paramTextView) {
    return paramTextView.isEnabled();
  }
  
  public static void setAlignment(TextView paramTextView, int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: iload_1
    //   1: tableswitch default -> 28, 0 -> 36, 1 -> 57, 2 -> 62
    //   28: new java/lang/IllegalArgumentException
    //   31: dup
    //   32: invokespecial <init> : ()V
    //   35: athrow
    //   36: iconst_3
    //   37: istore_1
    //   38: iload_2
    //   39: ifeq -> 67
    //   42: bipush #16
    //   44: istore_3
    //   45: aload_0
    //   46: iload_1
    //   47: iload_3
    //   48: ior
    //   49: invokevirtual setGravity : (I)V
    //   52: aload_0
    //   53: invokevirtual invalidate : ()V
    //   56: return
    //   57: iconst_1
    //   58: istore_1
    //   59: goto -> 38
    //   62: iconst_5
    //   63: istore_1
    //   64: goto -> 38
    //   67: bipush #48
    //   69: istore_3
    //   70: goto -> 45
  }
  
  public static void setBackgroundColor(TextView paramTextView, int paramInt) {
    paramTextView.setBackgroundColor(paramInt);
    paramTextView.invalidate();
  }
  
  public static void setEnabled(TextView paramTextView, boolean paramBoolean) {
    paramTextView.setEnabled(paramBoolean);
    paramTextView.invalidate();
  }
  
  public static void setFontSize(TextView paramTextView, float paramFloat) {
    paramTextView.setTextSize(paramFloat);
    paramTextView.requestLayout();
  }
  
  public static void setFontTypeface(TextView paramTextView, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: iload_1
    //   1: tableswitch default -> 32, 0 -> 40, 1 -> 91, 2 -> 83, 3 -> 99
    //   32: new java/lang/IllegalArgumentException
    //   35: dup
    //   36: invokespecial <init> : ()V
    //   39: athrow
    //   40: getstatic android/graphics/Typeface.DEFAULT : Landroid/graphics/Typeface;
    //   43: astore #5
    //   45: iconst_0
    //   46: istore_1
    //   47: iload_2
    //   48: ifeq -> 55
    //   51: iconst_0
    //   52: iconst_1
    //   53: ior
    //   54: istore_1
    //   55: iload_1
    //   56: istore #4
    //   58: iload_3
    //   59: ifeq -> 67
    //   62: iload_1
    //   63: iconst_2
    //   64: ior
    //   65: istore #4
    //   67: aload_0
    //   68: aload #5
    //   70: iload #4
    //   72: invokestatic create : (Landroid/graphics/Typeface;I)Landroid/graphics/Typeface;
    //   75: invokevirtual setTypeface : (Landroid/graphics/Typeface;)V
    //   78: aload_0
    //   79: invokevirtual requestLayout : ()V
    //   82: return
    //   83: getstatic android/graphics/Typeface.SERIF : Landroid/graphics/Typeface;
    //   86: astore #5
    //   88: goto -> 45
    //   91: getstatic android/graphics/Typeface.SANS_SERIF : Landroid/graphics/Typeface;
    //   94: astore #5
    //   96: goto -> 45
    //   99: getstatic android/graphics/Typeface.MONOSPACE : Landroid/graphics/Typeface;
    //   102: astore #5
    //   104: goto -> 45
  }
  
  public static void setMinHeight(TextView paramTextView, int paramInt) {
    paramTextView.setMinHeight(paramInt);
    paramTextView.setMinimumHeight(paramInt);
  }
  
  public static void setMinSize(TextView paramTextView, int paramInt1, int paramInt2) {
    setMinWidth(paramTextView, paramInt1);
    setMinHeight(paramTextView, paramInt2);
  }
  
  public static void setMinWidth(TextView paramTextView, int paramInt) {
    paramTextView.setMinWidth(paramInt);
    paramTextView.setMinimumWidth(paramInt);
  }
  
  public static void setPadding(TextView paramTextView, int paramInt) {
    paramTextView.setPadding(paramInt, paramInt, 0, 0);
    paramTextView.requestLayout();
  }
  
  public static void setText(TextView paramTextView, String paramString) {
    paramTextView.setText(paramString);
    paramTextView.requestLayout();
  }
  
  public static void setTextColor(TextView paramTextView, int paramInt) {
    paramTextView.setTextColor(paramInt);
    paramTextView.invalidate();
  }
  
  public static void setTextColors(TextView paramTextView, ColorStateList paramColorStateList) {
    paramTextView.setTextColor(paramColorStateList);
  }
  
  public static void setTextHTML(TextView paramTextView, String paramString) {
    paramTextView.setText((CharSequence)Html.fromHtml(paramString));
    paramTextView.requestLayout();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/TextViewUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
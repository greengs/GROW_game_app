package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.os.Build;
import android.view.accessibility.AccessibilityWindowInfo;

public class AccessibilityWindowInfoCompat {
  public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
  
  public static final int TYPE_APPLICATION = 1;
  
  public static final int TYPE_INPUT_METHOD = 2;
  
  public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
  
  public static final int TYPE_SYSTEM = 3;
  
  private static final int UNDEFINED = -1;
  
  private Object mInfo;
  
  private AccessibilityWindowInfoCompat(Object paramObject) {
    this.mInfo = paramObject;
  }
  
  public static AccessibilityWindowInfoCompat obtain() {
    return (Build.VERSION.SDK_INT >= 21) ? wrapNonNullInstance(AccessibilityWindowInfo.obtain()) : null;
  }
  
  public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat paramAccessibilityWindowInfoCompat) {
    return (Build.VERSION.SDK_INT < 21 || paramAccessibilityWindowInfoCompat == null) ? null : wrapNonNullInstance(AccessibilityWindowInfo.obtain((AccessibilityWindowInfo)paramAccessibilityWindowInfoCompat.mInfo));
  }
  
  private static String typeToString(int paramInt) {
    switch (paramInt) {
      default:
        return "<UNKNOWN>";
      case 1:
        return "TYPE_APPLICATION";
      case 2:
        return "TYPE_INPUT_METHOD";
      case 3:
        return "TYPE_SYSTEM";
      case 4:
        break;
    } 
    return "TYPE_ACCESSIBILITY_OVERLAY";
  }
  
  static AccessibilityWindowInfoCompat wrapNonNullInstance(Object paramObject) {
    return (paramObject != null) ? new AccessibilityWindowInfoCompat(paramObject) : null;
  }
  
  public boolean equals(Object paramObject) {
    if (this != paramObject) {
      if (paramObject == null)
        return false; 
      if (getClass() != paramObject.getClass())
        return false; 
      paramObject = paramObject;
      if (this.mInfo == null)
        return !(((AccessibilityWindowInfoCompat)paramObject).mInfo != null); 
      if (!this.mInfo.equals(((AccessibilityWindowInfoCompat)paramObject).mInfo))
        return false; 
    } 
    return true;
  }
  
  public AccessibilityNodeInfoCompat getAnchor() {
    return (Build.VERSION.SDK_INT >= 24) ? AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getAnchor()) : null;
  }
  
  public void getBoundsInScreen(Rect paramRect) {
    if (Build.VERSION.SDK_INT >= 21)
      ((AccessibilityWindowInfo)this.mInfo).getBoundsInScreen(paramRect); 
  }
  
  public AccessibilityWindowInfoCompat getChild(int paramInt) {
    return (Build.VERSION.SDK_INT >= 21) ? wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getChild(paramInt)) : null;
  }
  
  public int getChildCount() {
    return (Build.VERSION.SDK_INT >= 21) ? ((AccessibilityWindowInfo)this.mInfo).getChildCount() : 0;
  }
  
  public int getId() {
    return (Build.VERSION.SDK_INT >= 21) ? ((AccessibilityWindowInfo)this.mInfo).getId() : -1;
  }
  
  public int getLayer() {
    return (Build.VERSION.SDK_INT >= 21) ? ((AccessibilityWindowInfo)this.mInfo).getLayer() : -1;
  }
  
  public AccessibilityWindowInfoCompat getParent() {
    return (Build.VERSION.SDK_INT >= 21) ? wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getParent()) : null;
  }
  
  public AccessibilityNodeInfoCompat getRoot() {
    return (Build.VERSION.SDK_INT >= 21) ? AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getRoot()) : null;
  }
  
  public CharSequence getTitle() {
    return (Build.VERSION.SDK_INT >= 24) ? ((AccessibilityWindowInfo)this.mInfo).getTitle() : null;
  }
  
  public int getType() {
    return (Build.VERSION.SDK_INT >= 21) ? ((AccessibilityWindowInfo)this.mInfo).getType() : -1;
  }
  
  public int hashCode() {
    return (this.mInfo == null) ? 0 : this.mInfo.hashCode();
  }
  
  public boolean isAccessibilityFocused() {
    return (Build.VERSION.SDK_INT >= 21) ? ((AccessibilityWindowInfo)this.mInfo).isAccessibilityFocused() : true;
  }
  
  public boolean isActive() {
    return (Build.VERSION.SDK_INT >= 21) ? ((AccessibilityWindowInfo)this.mInfo).isActive() : true;
  }
  
  public boolean isFocused() {
    return (Build.VERSION.SDK_INT >= 21) ? ((AccessibilityWindowInfo)this.mInfo).isFocused() : true;
  }
  
  public void recycle() {
    if (Build.VERSION.SDK_INT >= 21)
      ((AccessibilityWindowInfo)this.mInfo).recycle(); 
  }
  
  public String toString() {
    boolean bool2 = true;
    StringBuilder stringBuilder1 = new StringBuilder();
    Rect rect = new Rect();
    getBoundsInScreen(rect);
    stringBuilder1.append("AccessibilityWindowInfo[");
    stringBuilder1.append("id=").append(getId());
    stringBuilder1.append(", type=").append(typeToString(getType()));
    stringBuilder1.append(", layer=").append(getLayer());
    stringBuilder1.append(", bounds=").append(rect);
    stringBuilder1.append(", focused=").append(isFocused());
    stringBuilder1.append(", active=").append(isActive());
    StringBuilder stringBuilder2 = stringBuilder1.append(", hasParent=");
    if (getParent() != null) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    stringBuilder2.append(bool1);
    stringBuilder2 = stringBuilder1.append(", hasChildren=");
    if (getChildCount() > 0) {
      bool1 = bool2;
      stringBuilder2.append(bool1);
      stringBuilder1.append(']');
      return stringBuilder1.toString();
    } 
    boolean bool1 = false;
    stringBuilder2.append(bool1);
    stringBuilder1.append(']');
    return stringBuilder1.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/accessibility/AccessibilityWindowInfoCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
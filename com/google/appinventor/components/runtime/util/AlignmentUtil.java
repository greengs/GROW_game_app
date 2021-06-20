package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.LinearLayout;

public class AlignmentUtil {
  LinearLayout viewLayout;
  
  public AlignmentUtil(LinearLayout paramLinearLayout) {
    this.viewLayout = paramLinearLayout;
  }
  
  public void setHorizontalAlignment(int paramInt) throws IllegalArgumentException {
    switch (paramInt) {
      default:
        throw new IllegalArgumentException("Bad value to setHorizontalAlignment: " + paramInt);
      case 1:
        this.viewLayout.setHorizontalGravity(3);
        return;
      case 2:
        this.viewLayout.setHorizontalGravity(5);
        return;
      case 3:
        break;
    } 
    this.viewLayout.setHorizontalGravity(1);
  }
  
  public void setVerticalAlignment(int paramInt) throws IllegalArgumentException {
    switch (paramInt) {
      default:
        throw new IllegalArgumentException("Bad value to setVerticalAlignment: " + paramInt);
      case 1:
        this.viewLayout.setVerticalGravity(48);
        return;
      case 2:
        this.viewLayout.setVerticalGravity(16);
        return;
      case 3:
        break;
    } 
    this.viewLayout.setVerticalGravity(80);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/AlignmentUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
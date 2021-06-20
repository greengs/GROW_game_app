package androidx.core.view.accessibility;

import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;

public class AccessibilityNodeProviderCompat {
  public static final int HOST_VIEW_ID = -1;
  
  private final Object mProvider;
  
  public AccessibilityNodeProviderCompat() {
    if (Build.VERSION.SDK_INT >= 19) {
      this.mProvider = new AccessibilityNodeProviderApi19(this);
      return;
    } 
    if (Build.VERSION.SDK_INT >= 16) {
      this.mProvider = new AccessibilityNodeProviderApi16(this);
      return;
    } 
    this.mProvider = null;
  }
  
  public AccessibilityNodeProviderCompat(Object paramObject) {
    this.mProvider = paramObject;
  }
  
  @Nullable
  public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int paramInt) {
    return null;
  }
  
  @Nullable
  public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String paramString, int paramInt) {
    return null;
  }
  
  @Nullable
  public AccessibilityNodeInfoCompat findFocus(int paramInt) {
    return null;
  }
  
  public Object getProvider() {
    return this.mProvider;
  }
  
  public boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle) {
    return false;
  }
  
  @RequiresApi(16)
  static class AccessibilityNodeProviderApi16 extends AccessibilityNodeProvider {
    final AccessibilityNodeProviderCompat mCompat;
    
    AccessibilityNodeProviderApi16(AccessibilityNodeProviderCompat param1AccessibilityNodeProviderCompat) {
      this.mCompat = param1AccessibilityNodeProviderCompat;
    }
    
    public AccessibilityNodeInfo createAccessibilityNodeInfo(int param1Int) {
      AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.mCompat.createAccessibilityNodeInfo(param1Int);
      return (accessibilityNodeInfoCompat == null) ? null : accessibilityNodeInfoCompat.unwrap();
    }
    
    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String param1String, int param1Int) {
      List<AccessibilityNodeInfoCompat> list = this.mCompat.findAccessibilityNodeInfosByText(param1String, param1Int);
      if (list == null)
        return null; 
      ArrayList<AccessibilityNodeInfo> arrayList = new ArrayList();
      int i = list.size();
      param1Int = 0;
      while (true) {
        ArrayList<AccessibilityNodeInfo> arrayList1 = arrayList;
        if (param1Int < i) {
          arrayList.add(((AccessibilityNodeInfoCompat)list.get(param1Int)).unwrap());
          param1Int++;
          continue;
        } 
        return arrayList1;
      } 
    }
    
    public boolean performAction(int param1Int1, int param1Int2, Bundle param1Bundle) {
      return this.mCompat.performAction(param1Int1, param1Int2, param1Bundle);
    }
  }
  
  @RequiresApi(19)
  static class AccessibilityNodeProviderApi19 extends AccessibilityNodeProviderApi16 {
    AccessibilityNodeProviderApi19(AccessibilityNodeProviderCompat param1AccessibilityNodeProviderCompat) {
      super(param1AccessibilityNodeProviderCompat);
    }
    
    public AccessibilityNodeInfo findFocus(int param1Int) {
      AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.mCompat.findFocus(param1Int);
      return (accessibilityNodeInfoCompat == null) ? null : accessibilityNodeInfoCompat.unwrap();
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/accessibility/AccessibilityNodeProviderCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
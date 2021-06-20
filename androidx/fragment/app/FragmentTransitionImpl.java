package androidx.fragment.app;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class FragmentTransitionImpl {
  protected static void bfsAddViewChildren(List<View> paramList, View paramView) {
    int i = paramList.size();
    if (!containedBeforeIndex(paramList, paramView, i)) {
      paramList.add(paramView);
      int j = i;
      while (true) {
        if (j < paramList.size()) {
          paramView = paramList.get(j);
          if (paramView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup)paramView;
            int m = viewGroup.getChildCount();
            for (int k = 0; k < m; k++) {
              View view = viewGroup.getChildAt(k);
              if (!containedBeforeIndex(paramList, view, i))
                paramList.add(view); 
            } 
          } 
          j++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  private static boolean containedBeforeIndex(List<View> paramList, View paramView, int paramInt) {
    for (int i = 0; i < paramInt; i++) {
      if (paramList.get(i) == paramView)
        return true; 
    } 
    return false;
  }
  
  static String findKeyForValue(Map<String, String> paramMap, String paramString) {
    for (Map.Entry<String, String> entry : paramMap.entrySet()) {
      if (paramString.equals(entry.getValue()))
        return (String)entry.getKey(); 
    } 
    return null;
  }
  
  protected static boolean isNullOrEmpty(List paramList) {
    return (paramList == null || paramList.isEmpty());
  }
  
  public abstract void addTarget(Object paramObject, View paramView);
  
  public abstract void addTargets(Object paramObject, ArrayList<View> paramArrayList);
  
  public abstract void beginDelayedTransition(ViewGroup paramViewGroup, Object paramObject);
  
  public abstract boolean canHandle(Object paramObject);
  
  void captureTransitioningViews(ArrayList<View> paramArrayList, View paramView) {
    ViewGroup viewGroup;
    if (paramView.getVisibility() == 0) {
      if (paramView instanceof ViewGroup) {
        viewGroup = (ViewGroup)paramView;
        if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
          paramArrayList.add(viewGroup);
          return;
        } 
        int j = viewGroup.getChildCount();
        int i = 0;
        while (true) {
          if (i < j) {
            captureTransitioningViews(paramArrayList, viewGroup.getChildAt(i));
            i++;
            continue;
          } 
          return;
        } 
      } 
    } else {
      return;
    } 
    paramArrayList.add(viewGroup);
  }
  
  public abstract Object cloneTransition(Object paramObject);
  
  void findNamedViews(Map<String, View> paramMap, View paramView) {
    if (paramView.getVisibility() == 0) {
      String str = ViewCompat.getTransitionName(paramView);
      if (str != null)
        paramMap.put(str, paramView); 
      if (paramView instanceof ViewGroup) {
        ViewGroup viewGroup = (ViewGroup)paramView;
        int j = viewGroup.getChildCount();
        for (int i = 0; i < j; i++)
          findNamedViews(paramMap, viewGroup.getChildAt(i)); 
      } 
    } 
  }
  
  protected void getBoundsOnScreen(View paramView, Rect paramRect) {
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    paramRect.set(arrayOfInt[0], arrayOfInt[1], arrayOfInt[0] + paramView.getWidth(), arrayOfInt[1] + paramView.getHeight());
  }
  
  public abstract Object mergeTransitionsInSequence(Object paramObject1, Object paramObject2, Object paramObject3);
  
  public abstract Object mergeTransitionsTogether(Object paramObject1, Object paramObject2, Object paramObject3);
  
  ArrayList<String> prepareSetNameOverridesReordered(ArrayList<View> paramArrayList) {
    ArrayList<String> arrayList = new ArrayList();
    int j = paramArrayList.size();
    for (int i = 0; i < j; i++) {
      View view = paramArrayList.get(i);
      arrayList.add(ViewCompat.getTransitionName(view));
      ViewCompat.setTransitionName(view, null);
    } 
    return arrayList;
  }
  
  public abstract void removeTarget(Object paramObject, View paramView);
  
  public abstract void replaceTargets(Object paramObject, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2);
  
  public abstract void scheduleHideFragmentView(Object paramObject, View paramView, ArrayList<View> paramArrayList);
  
  void scheduleNameReset(ViewGroup paramViewGroup, final ArrayList<View> sharedElementsIn, final Map<String, String> nameOverrides) {
    OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
          public void run() {
            int j = sharedElementsIn.size();
            for (int i = 0; i < j; i++) {
              View view = sharedElementsIn.get(i);
              String str = ViewCompat.getTransitionName(view);
              ViewCompat.setTransitionName(view, (String)nameOverrides.get(str));
            } 
          }
        });
  }
  
  public abstract void scheduleRemoveTargets(Object paramObject1, Object paramObject2, ArrayList<View> paramArrayList1, Object paramObject3, ArrayList<View> paramArrayList2, Object paramObject4, ArrayList<View> paramArrayList3);
  
  public abstract void setEpicenter(Object paramObject, Rect paramRect);
  
  public abstract void setEpicenter(Object paramObject, View paramView);
  
  void setNameOverridesOrdered(View paramView, final ArrayList<View> sharedElementsIn, final Map<String, String> nameOverrides) {
    OneShotPreDrawListener.add(paramView, new Runnable() {
          public void run() {
            int j = sharedElementsIn.size();
            for (int i = 0; i < j; i++) {
              View view = sharedElementsIn.get(i);
              String str = ViewCompat.getTransitionName(view);
              if (str != null)
                ViewCompat.setTransitionName(view, FragmentTransitionImpl.findKeyForValue(nameOverrides, str)); 
            } 
          }
        });
  }
  
  void setNameOverridesReordered(View paramView, final ArrayList<View> sharedElementsOut, final ArrayList<View> sharedElementsIn, final ArrayList<String> inNames, Map<String, String> paramMap) {
    final int numSharedElements = sharedElementsIn.size();
    final ArrayList<String> outNames = new ArrayList();
    int i = 0;
    label18: while (i < j) {
      View view = sharedElementsOut.get(i);
      String str1 = ViewCompat.getTransitionName(view);
      arrayList.add(str1);
      if (str1 == null)
        continue; 
      ViewCompat.setTransitionName(view, null);
      String str2 = paramMap.get(str1);
      int k = 0;
      while (true) {
        if (k < j)
          if (str2.equals(inNames.get(k))) {
            ViewCompat.setTransitionName(sharedElementsIn.get(k), str1);
          } else {
            k++;
            continue;
          }  
        i++;
        continue label18;
      } 
    } 
    OneShotPreDrawListener.add(paramView, new Runnable() {
          public void run() {
            for (int i = 0; i < numSharedElements; i++) {
              ViewCompat.setTransitionName(sharedElementsIn.get(i), inNames.get(i));
              ViewCompat.setTransitionName(sharedElementsOut.get(i), outNames.get(i));
            } 
          }
        });
  }
  
  public abstract void setSharedElementTargets(Object paramObject, View paramView, ArrayList<View> paramArrayList);
  
  public abstract void swapSharedElementTargets(Object paramObject, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2);
  
  public abstract Object wrapTransitionInSet(Object paramObject);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/fragment/app/FragmentTransitionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
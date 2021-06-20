package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;

public abstract class FragmentStatePagerAdapter extends PagerAdapter {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "FragmentStatePagerAdapt";
  
  private FragmentTransaction mCurTransaction = null;
  
  private Fragment mCurrentPrimaryItem = null;
  
  private final FragmentManager mFragmentManager;
  
  private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
  
  private ArrayList<Fragment.SavedState> mSavedState = new ArrayList<Fragment.SavedState>();
  
  public FragmentStatePagerAdapter(FragmentManager paramFragmentManager) {
    this.mFragmentManager = paramFragmentManager;
  }
  
  public void destroyItem(@NonNull ViewGroup paramViewGroup, int paramInt, @NonNull Object paramObject) {
    paramObject = paramObject;
    if (this.mCurTransaction == null)
      this.mCurTransaction = this.mFragmentManager.beginTransaction(); 
    while (this.mSavedState.size() <= paramInt)
      this.mSavedState.add(null); 
    ArrayList<Fragment.SavedState> arrayList = this.mSavedState;
    if (paramObject.isAdded()) {
      Fragment.SavedState savedState = this.mFragmentManager.saveFragmentInstanceState((Fragment)paramObject);
    } else {
      paramViewGroup = null;
    } 
    arrayList.set(paramInt, paramViewGroup);
    this.mFragments.set(paramInt, null);
    this.mCurTransaction.remove((Fragment)paramObject);
  }
  
  public void finishUpdate(@NonNull ViewGroup paramViewGroup) {
    if (this.mCurTransaction != null) {
      this.mCurTransaction.commitNowAllowingStateLoss();
      this.mCurTransaction = null;
    } 
  }
  
  public abstract Fragment getItem(int paramInt);
  
  @NonNull
  public Object instantiateItem(@NonNull ViewGroup paramViewGroup, int paramInt) {
    if (this.mFragments.size() > paramInt) {
      Fragment fragment1 = this.mFragments.get(paramInt);
      if (fragment1 != null)
        return fragment1; 
    } 
    if (this.mCurTransaction == null)
      this.mCurTransaction = this.mFragmentManager.beginTransaction(); 
    Fragment fragment = getItem(paramInt);
    if (this.mSavedState.size() > paramInt) {
      Fragment.SavedState savedState = this.mSavedState.get(paramInt);
      if (savedState != null)
        fragment.setInitialSavedState(savedState); 
    } 
    while (this.mFragments.size() <= paramInt)
      this.mFragments.add(null); 
    fragment.setMenuVisibility(false);
    fragment.setUserVisibleHint(false);
    this.mFragments.set(paramInt, fragment);
    this.mCurTransaction.add(paramViewGroup.getId(), fragment);
    return fragment;
  }
  
  public boolean isViewFromObject(@NonNull View paramView, @NonNull Object paramObject) {
    return (((Fragment)paramObject).getView() == paramView);
  }
  
  public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader) {
    if (paramParcelable != null) {
      Bundle bundle = (Bundle)paramParcelable;
      bundle.setClassLoader(paramClassLoader);
      Parcelable[] arrayOfParcelable = bundle.getParcelableArray("states");
      this.mSavedState.clear();
      this.mFragments.clear();
      if (arrayOfParcelable != null)
        for (int i = 0; i < arrayOfParcelable.length; i++)
          this.mSavedState.add((Fragment.SavedState)arrayOfParcelable[i]);  
      for (String str : bundle.keySet()) {
        if (str.startsWith("f")) {
          int i = Integer.parseInt(str.substring(1));
          Fragment fragment = this.mFragmentManager.getFragment(bundle, str);
          if (fragment != null) {
            while (this.mFragments.size() <= i)
              this.mFragments.add(null); 
            fragment.setMenuVisibility(false);
            this.mFragments.set(i, fragment);
            continue;
          } 
          Log.w("FragmentStatePagerAdapt", "Bad fragment at key " + str);
        } 
      } 
    } 
  }
  
  public Parcelable saveState() {
    Bundle bundle = null;
    if (this.mSavedState.size() > 0) {
      bundle = new Bundle();
      Fragment.SavedState[] arrayOfSavedState = new Fragment.SavedState[this.mSavedState.size()];
      this.mSavedState.toArray(arrayOfSavedState);
      bundle.putParcelableArray("states", (Parcelable[])arrayOfSavedState);
    } 
    int i = 0;
    while (i < this.mFragments.size()) {
      Fragment fragment = this.mFragments.get(i);
      Bundle bundle1 = bundle;
      if (fragment != null) {
        bundle1 = bundle;
        if (fragment.isAdded()) {
          bundle1 = bundle;
          if (bundle == null)
            bundle1 = new Bundle(); 
          String str = "f" + i;
          this.mFragmentManager.putFragment(bundle1, str, fragment);
        } 
      } 
      i++;
      bundle = bundle1;
    } 
    return (Parcelable)bundle;
  }
  
  public void setPrimaryItem(@NonNull ViewGroup paramViewGroup, int paramInt, @NonNull Object paramObject) {
    Fragment fragment = (Fragment)paramObject;
    if (fragment != this.mCurrentPrimaryItem) {
      if (this.mCurrentPrimaryItem != null) {
        this.mCurrentPrimaryItem.setMenuVisibility(false);
        this.mCurrentPrimaryItem.setUserVisibleHint(false);
      } 
      fragment.setMenuVisibility(true);
      fragment.setUserVisibleHint(true);
      this.mCurrentPrimaryItem = fragment;
    } 
  }
  
  public void startUpdate(@NonNull ViewGroup paramViewGroup) {
    if (paramViewGroup.getId() == -1)
      throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id"); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/fragment/app/FragmentStatePagerAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
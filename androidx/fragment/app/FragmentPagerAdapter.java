package androidx.fragment.app;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public abstract class FragmentPagerAdapter extends PagerAdapter {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "FragmentPagerAdapter";
  
  private FragmentTransaction mCurTransaction = null;
  
  private Fragment mCurrentPrimaryItem = null;
  
  private final FragmentManager mFragmentManager;
  
  public FragmentPagerAdapter(FragmentManager paramFragmentManager) {
    this.mFragmentManager = paramFragmentManager;
  }
  
  private static String makeFragmentName(int paramInt, long paramLong) {
    return "android:switcher:" + paramInt + ":" + paramLong;
  }
  
  public void destroyItem(@NonNull ViewGroup paramViewGroup, int paramInt, @NonNull Object paramObject) {
    if (this.mCurTransaction == null)
      this.mCurTransaction = this.mFragmentManager.beginTransaction(); 
    this.mCurTransaction.detach((Fragment)paramObject);
  }
  
  public void finishUpdate(@NonNull ViewGroup paramViewGroup) {
    if (this.mCurTransaction != null) {
      this.mCurTransaction.commitNowAllowingStateLoss();
      this.mCurTransaction = null;
    } 
  }
  
  public abstract Fragment getItem(int paramInt);
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  @NonNull
  public Object instantiateItem(@NonNull ViewGroup paramViewGroup, int paramInt) {
    Fragment fragment1;
    if (this.mCurTransaction == null)
      this.mCurTransaction = this.mFragmentManager.beginTransaction(); 
    long l = getItemId(paramInt);
    String str = makeFragmentName(paramViewGroup.getId(), l);
    Fragment fragment2 = this.mFragmentManager.findFragmentByTag(str);
    if (fragment2 != null) {
      this.mCurTransaction.attach(fragment2);
      fragment1 = fragment2;
    } else {
      fragment2 = getItem(paramInt);
      this.mCurTransaction.add(fragment1.getId(), fragment2, makeFragmentName(fragment1.getId(), l));
      fragment1 = fragment2;
    } 
    if (fragment1 != this.mCurrentPrimaryItem) {
      fragment1.setMenuVisibility(false);
      fragment1.setUserVisibleHint(false);
    } 
    return fragment1;
  }
  
  public boolean isViewFromObject(@NonNull View paramView, @NonNull Object paramObject) {
    return (((Fragment)paramObject).getView() == paramView);
  }
  
  public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader) {}
  
  public Parcelable saveState() {
    return null;
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/fragment/app/FragmentPagerAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package androidx.fragment.app;

import androidx.lifecycle.ViewModelStore;
import java.util.List;

public class FragmentManagerNonConfig {
  private final List<FragmentManagerNonConfig> mChildNonConfigs;
  
  private final List<Fragment> mFragments;
  
  private final List<ViewModelStore> mViewModelStores;
  
  FragmentManagerNonConfig(List<Fragment> paramList, List<FragmentManagerNonConfig> paramList1, List<ViewModelStore> paramList2) {
    this.mFragments = paramList;
    this.mChildNonConfigs = paramList1;
    this.mViewModelStores = paramList2;
  }
  
  List<FragmentManagerNonConfig> getChildNonConfigs() {
    return this.mChildNonConfigs;
  }
  
  List<Fragment> getFragments() {
    return this.mFragments;
  }
  
  List<ViewModelStore> getViewModelStores() {
    return this.mViewModelStores;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/fragment/app/FragmentManagerNonConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
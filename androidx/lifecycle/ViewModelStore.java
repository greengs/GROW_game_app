package androidx.lifecycle;

import java.util.HashMap;
import java.util.Iterator;

public class ViewModelStore {
  private final HashMap<String, ViewModel> mMap = new HashMap<String, ViewModel>();
  
  public final void clear() {
    Iterator<ViewModel> iterator = this.mMap.values().iterator();
    while (iterator.hasNext())
      ((ViewModel)iterator.next()).onCleared(); 
    this.mMap.clear();
  }
  
  final ViewModel get(String paramString) {
    return this.mMap.get(paramString);
  }
  
  final void put(String paramString, ViewModel paramViewModel) {
    ViewModel viewModel = this.mMap.put(paramString, paramViewModel);
    if (viewModel != null)
      viewModel.onCleared(); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/ViewModelStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
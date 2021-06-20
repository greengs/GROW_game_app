package androidx.coordinatorlayout.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class DirectedAcyclicGraph<T> {
  private final SimpleArrayMap<T, ArrayList<T>> mGraph = new SimpleArrayMap();
  
  private final Pools.Pool<ArrayList<T>> mListPool = (Pools.Pool<ArrayList<T>>)new Pools.SimplePool(10);
  
  private final ArrayList<T> mSortResult = new ArrayList<T>();
  
  private final HashSet<T> mSortTmpMarked = new HashSet<T>();
  
  private void dfs(T paramT, ArrayList<T> paramArrayList, HashSet<T> paramHashSet) {
    if (paramArrayList.contains(paramT))
      return; 
    if (paramHashSet.contains(paramT))
      throw new RuntimeException("This graph contains cyclic dependencies"); 
    paramHashSet.add(paramT);
    ArrayList<T> arrayList = (ArrayList)this.mGraph.get(paramT);
    if (arrayList != null) {
      int i = 0;
      int j = arrayList.size();
      while (i < j) {
        dfs(arrayList.get(i), paramArrayList, paramHashSet);
        i++;
      } 
    } 
    paramHashSet.remove(paramT);
    paramArrayList.add(paramT);
  }
  
  @NonNull
  private ArrayList<T> getEmptyList() {
    ArrayList<T> arrayList2 = (ArrayList)this.mListPool.acquire();
    ArrayList<T> arrayList1 = arrayList2;
    if (arrayList2 == null)
      arrayList1 = new ArrayList(); 
    return arrayList1;
  }
  
  private void poolList(@NonNull ArrayList<T> paramArrayList) {
    paramArrayList.clear();
    this.mListPool.release(paramArrayList);
  }
  
  public void addEdge(@NonNull T paramT1, @NonNull T paramT2) {
    if (!this.mGraph.containsKey(paramT1) || !this.mGraph.containsKey(paramT2))
      throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge"); 
    ArrayList<T> arrayList2 = (ArrayList)this.mGraph.get(paramT1);
    ArrayList<T> arrayList1 = arrayList2;
    if (arrayList2 == null) {
      arrayList1 = getEmptyList();
      this.mGraph.put(paramT1, arrayList1);
    } 
    arrayList1.add(paramT2);
  }
  
  public void addNode(@NonNull T paramT) {
    if (!this.mGraph.containsKey(paramT))
      this.mGraph.put(paramT, null); 
  }
  
  public void clear() {
    int i = 0;
    int j = this.mGraph.size();
    while (i < j) {
      ArrayList<T> arrayList = (ArrayList)this.mGraph.valueAt(i);
      if (arrayList != null)
        poolList(arrayList); 
      i++;
    } 
    this.mGraph.clear();
  }
  
  public boolean contains(@NonNull T paramT) {
    return this.mGraph.containsKey(paramT);
  }
  
  @Nullable
  public List getIncomingEdges(@NonNull T paramT) {
    return (List)this.mGraph.get(paramT);
  }
  
  @Nullable
  public List<T> getOutgoingEdges(@NonNull T paramT) {
    ArrayList<Object> arrayList = null;
    int i = 0;
    int j = this.mGraph.size();
    while (i < j) {
      ArrayList arrayList2 = (ArrayList)this.mGraph.valueAt(i);
      ArrayList<Object> arrayList1 = arrayList;
      if (arrayList2 != null) {
        arrayList1 = arrayList;
        if (arrayList2.contains(paramT)) {
          arrayList1 = arrayList;
          if (arrayList == null)
            arrayList1 = new ArrayList(); 
          arrayList1.add(this.mGraph.keyAt(i));
        } 
      } 
      i++;
      arrayList = arrayList1;
    } 
    return arrayList;
  }
  
  @NonNull
  public ArrayList<T> getSortedList() {
    this.mSortResult.clear();
    this.mSortTmpMarked.clear();
    int i = 0;
    int j = this.mGraph.size();
    while (i < j) {
      dfs((T)this.mGraph.keyAt(i), this.mSortResult, this.mSortTmpMarked);
      i++;
    } 
    return this.mSortResult;
  }
  
  public boolean hasOutgoingEdges(@NonNull T paramT) {
    int i = 0;
    int j = this.mGraph.size();
    while (i < j) {
      ArrayList arrayList = (ArrayList)this.mGraph.valueAt(i);
      if (arrayList != null && arrayList.contains(paramT))
        return true; 
      i++;
    } 
    return false;
  }
  
  int size() {
    return this.mGraph.size();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/coordinatorlayout/widget/DirectedAcyclicGraph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.kawa.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class AbstractWeakHashTable<K, V> extends AbstractHashTable<AbstractWeakHashTable.WEntry<K, V>, K, V> {
  ReferenceQueue<V> rqueue = new ReferenceQueue<V>();
  
  public AbstractWeakHashTable() {
    super(64);
  }
  
  public AbstractWeakHashTable(int paramInt) {
    super(paramInt);
  }
  
  static <Entry extends Map.Entry<K, V>, K, V> void cleanup(AbstractHashTable<Entry, ?, ?> paramAbstractHashTable, ReferenceQueue<?> paramReferenceQueue) {
    label18: while (true) {
      Map.Entry entry = (Map.Entry)paramReferenceQueue.poll();
      if (entry == null)
        return; 
      int i = paramAbstractHashTable.hashToIndex(paramAbstractHashTable.getEntryHashCode((Entry)entry));
      Entry entry2 = null;
      Entry entry1 = paramAbstractHashTable.table[i];
      while (true) {
        if (entry1 != null) {
          Entry entry3 = paramAbstractHashTable.getEntryNext(entry1);
          if (entry1 == entry) {
            if (entry2 == null) {
              paramAbstractHashTable.table[i] = entry3;
            } else {
              paramAbstractHashTable.setEntryNext(entry2, entry3);
            } 
          } else {
            Entry entry4 = entry1;
            entry1 = entry3;
            continue;
          } 
        } 
        paramAbstractHashTable.num_bindings--;
        continue label18;
      } 
      break;
    } 
  }
  
  protected WEntry<K, V>[] allocEntries(int paramInt) {
    return (WEntry<K, V>[])new WEntry[paramInt];
  }
  
  protected void cleanup() {
    cleanup(this, this.rqueue);
  }
  
  public V get(Object paramObject, V paramV) {
    cleanup();
    return super.get(paramObject, paramV);
  }
  
  protected int getEntryHashCode(WEntry<K, V> paramWEntry) {
    return paramWEntry.hash;
  }
  
  protected WEntry<K, V> getEntryNext(WEntry<K, V> paramWEntry) {
    return paramWEntry.next;
  }
  
  protected abstract K getKeyFromValue(V paramV);
  
  protected V getValueIfMatching(WEntry<K, V> paramWEntry, Object paramObject) {
    paramWEntry = (WEntry<K, V>)paramWEntry.getValue();
    return (V)((paramWEntry != null && matches(getKeyFromValue((V)paramWEntry), paramObject)) ? paramWEntry : null);
  }
  
  public int hash(Object paramObject) {
    return System.identityHashCode(paramObject);
  }
  
  protected WEntry<K, V> makeEntry(K paramK, int paramInt, V paramV) {
    return new WEntry<K, V>(paramV, this, paramInt);
  }
  
  public V put(K paramK, V paramV) {
    cleanup();
    int j = hash(paramK);
    int i = hashToIndex(j);
    WEntry<K, V> wEntry2 = ((WEntry[])this.table)[i];
    WEntry<K, V> wEntry1 = wEntry2;
    WEntry<K, V> wEntry3 = null;
    V v = null;
    while (true) {
      if (wEntry1 == null) {
        int k = this.num_bindings + 1;
        this.num_bindings = k;
        if (k >= ((WEntry[])this.table).length) {
          rehash();
          i = hashToIndex(j);
          wEntry2 = ((WEntry[])this.table)[i];
        } 
        wEntry1 = makeEntry((K)null, j, paramV);
        wEntry1.next = wEntry2;
        ((WEntry[])this.table)[i] = wEntry1;
        return v;
      } 
      V v1 = wEntry1.getValue();
      if (v1 == paramV)
        return v1; 
      WEntry<K, V> wEntry = wEntry1.next;
      if (v1 != null && valuesEqual(v1, paramV)) {
        if (wEntry3 == null) {
          ((WEntry[])this.table)[i] = wEntry;
        } else {
          wEntry3.next = wEntry;
        } 
        V v2 = v1;
      } else {
        wEntry3 = wEntry1;
      } 
      wEntry1 = wEntry;
    } 
  }
  
  protected void setEntryNext(WEntry<K, V> paramWEntry1, WEntry<K, V> paramWEntry2) {
    paramWEntry1.next = paramWEntry2;
  }
  
  protected boolean valuesEqual(V paramV1, V paramV2) {
    return (paramV1 == paramV2);
  }
  
  public static class WEntry<K, V> extends WeakReference<V> implements Map.Entry<K, V> {
    public int hash;
    
    AbstractWeakHashTable<K, V> htable;
    
    public WEntry next;
    
    public WEntry(V param1V, AbstractWeakHashTable<K, V> param1AbstractWeakHashTable, int param1Int) {
      super(param1V, param1AbstractWeakHashTable.rqueue);
      this.htable = param1AbstractWeakHashTable;
      this.hash = param1Int;
    }
    
    public K getKey() {
      V v = get();
      return (v == null) ? null : this.htable.getKeyFromValue(v);
    }
    
    public V getValue() {
      return get();
    }
    
    public V setValue(V param1V) {
      throw new UnsupportedOperationException();
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/util/AbstractWeakHashTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
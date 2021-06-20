package gnu.kawa.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;

public class WeakHashNode<K, V> extends WeakReference<K> implements Map.Entry<K, V> {
  public int hash;
  
  public WeakHashNode<K, V> next;
  
  public V value;
  
  public WeakHashNode(K paramK, ReferenceQueue<K> paramReferenceQueue, int paramInt) {
    super(paramK, paramReferenceQueue);
    this.hash = paramInt;
  }
  
  public K getKey() {
    return get();
  }
  
  public V getValue() {
    return this.value;
  }
  
  public V setValue(V paramV) {
    V v = this.value;
    this.value = paramV;
    return v;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/util/WeakHashNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
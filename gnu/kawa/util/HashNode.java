package gnu.kawa.util;

import java.util.Map;

public class HashNode<K, V> implements Map.Entry<K, V> {
  int hash;
  
  K key;
  
  public HashNode<K, V> next;
  
  V value;
  
  public HashNode(K paramK, V paramV) {
    this.key = paramK;
    this.value = paramV;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof HashNode) {
      paramObject = paramObject;
      if (((this.key == null) ? (((HashNode)paramObject).key == null) : this.key.equals(((HashNode)paramObject).key)) && ((this.value == null) ? (((HashNode)paramObject).value == null) : this.value.equals(((HashNode)paramObject).value)))
        return true; 
    } 
    return false;
  }
  
  public V get(V paramV) {
    return getValue();
  }
  
  public K getKey() {
    return this.key;
  }
  
  public V getValue() {
    return this.value;
  }
  
  public int hashCode() {
    int i;
    int j = 0;
    if (this.key == null) {
      i = 0;
    } else {
      i = this.key.hashCode();
    } 
    if (this.value != null)
      j = this.value.hashCode(); 
    return i ^ j;
  }
  
  public V setValue(V paramV) {
    V v = this.value;
    this.value = paramV;
    return v;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/util/HashNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
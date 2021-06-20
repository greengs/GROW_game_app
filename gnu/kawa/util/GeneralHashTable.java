package gnu.kawa.util;

import java.util.Map;

public class GeneralHashTable<K, V> extends AbstractHashTable<HashNode<K, V>, K, V> {
  public GeneralHashTable() {}
  
  public GeneralHashTable(int paramInt) {
    super(paramInt);
  }
  
  protected HashNode<K, V>[] allocEntries(int paramInt) {
    return (HashNode<K, V>[])new HashNode[paramInt];
  }
  
  protected int getEntryHashCode(HashNode<K, V> paramHashNode) {
    return paramHashNode.hash;
  }
  
  protected HashNode<K, V> getEntryNext(HashNode<K, V> paramHashNode) {
    return paramHashNode.next;
  }
  
  public HashNode<K, V> getNode(Object paramObject) {
    return super.getNode(paramObject);
  }
  
  protected HashNode<K, V> makeEntry(K paramK, int paramInt, V paramV) {
    HashNode<K, V> hashNode = new HashNode<K, V>(paramK, paramV);
    hashNode.hash = paramInt;
    return hashNode;
  }
  
  protected void setEntryNext(HashNode<K, V> paramHashNode1, HashNode<K, V> paramHashNode2) {
    paramHashNode1.next = paramHashNode2;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/util/GeneralHashTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
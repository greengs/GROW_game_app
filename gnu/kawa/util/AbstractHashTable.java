package gnu.kawa.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class AbstractHashTable<Entry extends Map.Entry<K, V>, K, V> extends AbstractMap<K, V> {
  public static final int DEFAULT_INITIAL_SIZE = 64;
  
  protected int mask;
  
  protected int num_bindings;
  
  protected Entry[] table;
  
  public AbstractHashTable() {
    this(64);
  }
  
  public AbstractHashTable(int paramInt) {
    int i;
    for (i = 4; paramInt > 1 << i; i++);
    paramInt = 1 << i;
    this.table = allocEntries(paramInt);
    this.mask = paramInt - 1;
  }
  
  protected abstract Entry[] allocEntries(int paramInt);
  
  public void clear() {
    Entry[] arrayOfEntry = this.table;
    int i = arrayOfEntry.length;
    while (true) {
      if (--i >= 0) {
        for (Entry entry = arrayOfEntry[i]; entry != null; entry = entry1) {
          Entry entry1 = getEntryNext(entry);
          setEntryNext(entry, null);
        } 
        arrayOfEntry[i] = null;
        continue;
      } 
      this.num_bindings = 0;
      return;
    } 
  }
  
  public Set<Map.Entry<K, V>> entrySet() {
    return new AbstractEntrySet<Map.Entry<K, V>, Object, Object>(this);
  }
  
  public V get(Object paramObject) {
    return get(paramObject, null);
  }
  
  public V get(Object paramObject, V paramV) {
    paramObject = getNode(paramObject);
    return (V)((paramObject == null) ? (Object)paramV : paramObject.getValue());
  }
  
  protected abstract int getEntryHashCode(Entry paramEntry);
  
  protected abstract Entry getEntryNext(Entry paramEntry);
  
  public Entry getNode(Object paramObject) {
    int i = hash(paramObject);
    int j = hashToIndex(i);
    for (Entry entry = this.table[j]; entry != null; entry = getEntryNext(entry)) {
      if (matches(paramObject, i, entry))
        return entry; 
    } 
    return null;
  }
  
  public int hash(Object paramObject) {
    return (paramObject == null) ? 0 : paramObject.hashCode();
  }
  
  protected int hashToIndex(int paramInt) {
    return this.mask & (paramInt ^ paramInt >>> 15);
  }
  
  protected abstract Entry makeEntry(K paramK, int paramInt, V paramV);
  
  protected boolean matches(Object paramObject, int paramInt, Entry paramEntry) {
    return (getEntryHashCode(paramEntry) == paramInt && matches((K)paramEntry.getKey(), paramObject));
  }
  
  protected boolean matches(K paramK, Object paramObject) {
    return (paramK == paramObject || (paramK != null && paramK.equals(paramObject)));
  }
  
  public V put(K paramK, int paramInt, V paramV) {
    int i = hashToIndex(paramInt);
    Entry entry1 = this.table[i];
    for (Entry entry2 = entry1;; entry2 = getEntryNext(entry2)) {
      if (entry2 == null) {
        int j = this.num_bindings + 1;
        this.num_bindings = j;
        if (j >= this.table.length) {
          rehash();
          i = hashToIndex(paramInt);
          entry1 = this.table[i];
        } 
        paramK = (K)makeEntry(paramK, paramInt, paramV);
        setEntryNext((Entry)paramK, entry1);
        this.table[i] = (Entry)paramK;
        return null;
      } 
      if (matches(paramK, paramInt, entry2)) {
        paramK = (K)entry2.getValue();
        entry2.setValue(paramV);
        return (V)paramK;
      } 
    } 
  }
  
  public V put(K paramK, V paramV) {
    return put(paramK, hash(paramK), paramV);
  }
  
  protected void rehash() {
    Entry[] arrayOfEntry1 = this.table;
    int i = arrayOfEntry1.length;
    int j = i * 2;
    Entry[] arrayOfEntry2 = allocEntries(j);
    this.table = arrayOfEntry2;
    this.mask = j - 1;
    label19: while (true) {
      j = i - 1;
      if (j >= 0) {
        Entry entry2 = arrayOfEntry1[j];
        Entry entry1 = entry2;
        if (entry2 != null) {
          entry1 = entry2;
          if (getEntryNext(entry2) != null) {
            Entry entry4;
            Entry entry3 = null;
            do {
              entry1 = entry2;
              entry4 = getEntryNext(entry1);
              setEntryNext(entry1, entry3);
              entry2 = entry4;
              Entry entry = entry1;
            } while (entry4 != null);
          } 
        } 
        while (true) {
          i = j;
          if (entry1 != null) {
            entry2 = getEntryNext(entry1);
            i = hashToIndex(getEntryHashCode(entry1));
            setEntryNext(entry1, arrayOfEntry2[i]);
            arrayOfEntry2[i] = entry1;
            entry1 = entry2;
            continue;
          } 
          continue label19;
        } 
      } 
      break;
    } 
  }
  
  public V remove(Object paramObject) {
    int i = hash(paramObject);
    int j = hashToIndex(i);
    Entry entry2 = null;
    for (Entry entry1 = this.table[j]; entry1 != null; entry1 = entry4) {
      Entry entry4 = getEntryNext(entry1);
      if (matches(paramObject, i, entry1)) {
        if (entry2 == null) {
          this.table[j] = entry4;
          this.num_bindings--;
          return (V)entry1.getValue();
        } 
        setEntryNext(entry2, entry4);
        this.num_bindings--;
        return (V)entry1.getValue();
      } 
      Entry entry3 = entry1;
    } 
    return null;
  }
  
  protected abstract void setEntryNext(Entry paramEntry1, Entry paramEntry2);
  
  public int size() {
    return this.num_bindings;
  }
  
  static class AbstractEntrySet<Entry extends Map.Entry<K, V>, K, V> extends AbstractSet<Entry> {
    AbstractHashTable<Entry, K, V> htable;
    
    public AbstractEntrySet(AbstractHashTable<Entry, K, V> param1AbstractHashTable) {
      this.htable = param1AbstractHashTable;
    }
    
    public Iterator<Entry> iterator() {
      return new Iterator<Entry>() {
          int curIndex = -1;
          
          Entry currentEntry;
          
          Entry nextEntry;
          
          int nextIndex;
          
          Entry previousEntry;
          
          private void advance() {
            while (this.nextEntry == null) {
              int i = this.nextIndex - 1;
              this.nextIndex = i;
              if (i >= 0)
                this.nextEntry = AbstractHashTable.AbstractEntrySet.this.htable.table[this.nextIndex]; 
            } 
          }
          
          public boolean hasNext() {
            if (this.curIndex < 0) {
              this.nextIndex = AbstractHashTable.AbstractEntrySet.this.htable.table.length;
              this.curIndex = this.nextIndex;
              advance();
            } 
            return (this.nextEntry != null);
          }
          
          public Entry next() {
            if (this.nextEntry == null)
              throw new NoSuchElementException(); 
            this.previousEntry = this.currentEntry;
            this.currentEntry = this.nextEntry;
            this.curIndex = this.nextIndex;
            this.nextEntry = (Entry)AbstractHashTable.AbstractEntrySet.this.htable.getEntryNext(this.currentEntry);
            advance();
            return this.currentEntry;
          }
          
          public void remove() {
            if (this.previousEntry == this.currentEntry)
              throw new IllegalStateException(); 
            if (this.previousEntry == null) {
              AbstractHashTable.AbstractEntrySet.this.htable.table[this.curIndex] = this.nextEntry;
            } else {
              AbstractHashTable.AbstractEntrySet.this.htable.setEntryNext(this.previousEntry, this.nextEntry);
            } 
            AbstractHashTable abstractHashTable = AbstractHashTable.AbstractEntrySet.this.htable;
            abstractHashTable.num_bindings--;
            this.previousEntry = this.currentEntry;
          }
        };
    }
    
    public int size() {
      return this.htable.size();
    }
  }
  
  class null implements Iterator<Entry> {
    int curIndex = -1;
    
    Entry currentEntry;
    
    Entry nextEntry;
    
    int nextIndex;
    
    Entry previousEntry;
    
    private void advance() {
      while (this.nextEntry == null) {
        int i = this.nextIndex - 1;
        this.nextIndex = i;
        if (i >= 0)
          this.nextEntry = this.this$0.htable.table[this.nextIndex]; 
      } 
    }
    
    public boolean hasNext() {
      if (this.curIndex < 0) {
        this.nextIndex = this.this$0.htable.table.length;
        this.curIndex = this.nextIndex;
        advance();
      } 
      return (this.nextEntry != null);
    }
    
    public Entry next() {
      if (this.nextEntry == null)
        throw new NoSuchElementException(); 
      this.previousEntry = this.currentEntry;
      this.currentEntry = this.nextEntry;
      this.curIndex = this.nextIndex;
      this.nextEntry = (Entry)this.this$0.htable.getEntryNext(this.currentEntry);
      advance();
      return this.currentEntry;
    }
    
    public void remove() {
      if (this.previousEntry == this.currentEntry)
        throw new IllegalStateException(); 
      if (this.previousEntry == null) {
        this.this$0.htable.table[this.curIndex] = this.nextEntry;
      } else {
        this.this$0.htable.setEntryNext(this.previousEntry, this.nextEntry);
      } 
      AbstractHashTable abstractHashTable = this.this$0.htable;
      abstractHashTable.num_bindings--;
      this.previousEntry = this.currentEntry;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/util/AbstractHashTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
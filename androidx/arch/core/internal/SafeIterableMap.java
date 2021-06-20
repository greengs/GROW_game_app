package androidx.arch.core.internal;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SafeIterableMap<K, V> implements Iterable<Map.Entry<K, V>> {
  private Entry<K, V> mEnd;
  
  private WeakHashMap<SupportRemove<K, V>, Boolean> mIterators = new WeakHashMap<SupportRemove<K, V>, Boolean>();
  
  private int mSize = 0;
  
  Entry<K, V> mStart;
  
  public Iterator<Map.Entry<K, V>> descendingIterator() {
    DescendingIterator<K, V> descendingIterator = new DescendingIterator<K, V>(this.mEnd, this.mStart);
    this.mIterators.put(descendingIterator, Boolean.valueOf(false));
    return descendingIterator;
  }
  
  public Map.Entry<K, V> eldest() {
    return this.mStart;
  }
  
  public boolean equals(Object<Map.Entry<K, V>> paramObject) {
    boolean bool2 = true;
    boolean bool3 = false;
    if (paramObject == this)
      return true; 
    boolean bool1 = bool3;
    if (paramObject instanceof SafeIterableMap) {
      SafeIterableMap safeIterableMap = (SafeIterableMap)paramObject;
      bool1 = bool3;
      if (size() == safeIterableMap.size()) {
        paramObject = (Object<Map.Entry<K, V>>)iterator();
        Iterator<Object> iterator = safeIterableMap.iterator();
        while (paramObject.hasNext() && iterator.hasNext()) {
          Map.Entry entry = paramObject.next();
          Object object = iterator.next();
          if (entry == null) {
            bool1 = bool3;
            if (object == null)
              continue; 
            return bool1;
          } 
          continue;
          if (SYNTHETIC_LOCAL_VARIABLE_6 != null && !SYNTHETIC_LOCAL_VARIABLE_6.equals(SYNTHETIC_LOCAL_VARIABLE_7))
            return false; 
        } 
        return (!paramObject.hasNext() && !iterator.hasNext()) ? bool2 : false;
      } 
    } 
    return bool1;
  }
  
  protected Entry<K, V> get(K paramK) {
    for (Entry<K, V> entry = this.mStart;; entry = entry.mNext) {
      if (entry == null || entry.mKey.equals(paramK))
        return entry; 
    } 
  }
  
  public int hashCode() {
    int i = 0;
    Iterator<Map.Entry<K, V>> iterator = iterator();
    while (iterator.hasNext())
      i += ((Map.Entry)iterator.next()).hashCode(); 
    return i;
  }
  
  @NonNull
  public Iterator<Map.Entry<K, V>> iterator() {
    AscendingIterator<K, V> ascendingIterator = new AscendingIterator<K, V>(this.mStart, this.mEnd);
    this.mIterators.put(ascendingIterator, Boolean.valueOf(false));
    return ascendingIterator;
  }
  
  public IteratorWithAdditions iteratorWithAdditions() {
    IteratorWithAdditions iteratorWithAdditions = new IteratorWithAdditions();
    this.mIterators.put(iteratorWithAdditions, Boolean.valueOf(false));
    return iteratorWithAdditions;
  }
  
  public Map.Entry<K, V> newest() {
    return this.mEnd;
  }
  
  protected Entry<K, V> put(@NonNull K paramK, @NonNull V paramV) {
    Entry<K, V> entry = new Entry<K, V>(paramK, paramV);
    this.mSize++;
    if (this.mEnd == null) {
      this.mStart = entry;
      this.mEnd = this.mStart;
      return entry;
    } 
    this.mEnd.mNext = entry;
    entry.mPrevious = this.mEnd;
    this.mEnd = entry;
    return entry;
  }
  
  public V putIfAbsent(@NonNull K paramK, @NonNull V paramV) {
    Entry<K, V> entry = get(paramK);
    if (entry != null)
      return entry.mValue; 
    put(paramK, paramV);
    return null;
  }
  
  public V remove(@NonNull K paramK) {
    Entry<K, V> entry = get(paramK);
    if (entry == null)
      return null; 
    this.mSize--;
    if (!this.mIterators.isEmpty()) {
      Iterator<SupportRemove<K, V>> iterator = this.mIterators.keySet().iterator();
      while (iterator.hasNext())
        ((SupportRemove<K, V>)iterator.next()).supportRemove(entry); 
    } 
    if (entry.mPrevious != null) {
      entry.mPrevious.mNext = entry.mNext;
    } else {
      this.mStart = entry.mNext;
    } 
    if (entry.mNext != null) {
      entry.mNext.mPrevious = entry.mPrevious;
      entry.mNext = null;
      entry.mPrevious = null;
      return entry.mValue;
    } 
    this.mEnd = entry.mPrevious;
    entry.mNext = null;
    entry.mPrevious = null;
    return entry.mValue;
  }
  
  public int size() {
    return this.mSize;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    Iterator<Map.Entry<K, V>> iterator = iterator();
    while (iterator.hasNext()) {
      stringBuilder.append(((Map.Entry)iterator.next()).toString());
      if (iterator.hasNext())
        stringBuilder.append(", "); 
    } 
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  static class AscendingIterator<K, V> extends ListIterator<K, V> {
    AscendingIterator(SafeIterableMap.Entry<K, V> param1Entry1, SafeIterableMap.Entry<K, V> param1Entry2) {
      super(param1Entry1, param1Entry2);
    }
    
    SafeIterableMap.Entry<K, V> backward(SafeIterableMap.Entry<K, V> param1Entry) {
      return param1Entry.mPrevious;
    }
    
    SafeIterableMap.Entry<K, V> forward(SafeIterableMap.Entry<K, V> param1Entry) {
      return param1Entry.mNext;
    }
  }
  
  private static class DescendingIterator<K, V> extends ListIterator<K, V> {
    DescendingIterator(SafeIterableMap.Entry<K, V> param1Entry1, SafeIterableMap.Entry<K, V> param1Entry2) {
      super(param1Entry1, param1Entry2);
    }
    
    SafeIterableMap.Entry<K, V> backward(SafeIterableMap.Entry<K, V> param1Entry) {
      return param1Entry.mNext;
    }
    
    SafeIterableMap.Entry<K, V> forward(SafeIterableMap.Entry<K, V> param1Entry) {
      return param1Entry.mPrevious;
    }
  }
  
  static class Entry<K, V> implements Map.Entry<K, V> {
    @NonNull
    final K mKey;
    
    Entry<K, V> mNext;
    
    Entry<K, V> mPrevious;
    
    @NonNull
    final V mValue;
    
    Entry(@NonNull K param1K, @NonNull V param1V) {
      this.mKey = param1K;
      this.mValue = param1V;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object != this) {
        if (!(param1Object instanceof Entry))
          return false; 
        param1Object = param1Object;
        if (!this.mKey.equals(((Entry)param1Object).mKey) || !this.mValue.equals(((Entry)param1Object).mValue))
          return false; 
      } 
      return true;
    }
    
    @NonNull
    public K getKey() {
      return this.mKey;
    }
    
    @NonNull
    public V getValue() {
      return this.mValue;
    }
    
    public int hashCode() {
      return this.mKey.hashCode() ^ this.mValue.hashCode();
    }
    
    public V setValue(V param1V) {
      throw new UnsupportedOperationException("An entry modification is not supported");
    }
    
    public String toString() {
      return (new StringBuilder()).append(this.mKey).append("=").append(this.mValue).toString();
    }
  }
  
  private class IteratorWithAdditions implements Iterator<Map.Entry<K, V>>, SupportRemove<K, V> {
    private boolean mBeforeStart = true;
    
    private SafeIterableMap.Entry<K, V> mCurrent;
    
    public boolean hasNext() {
      return this.mBeforeStart ? (!(SafeIterableMap.this.mStart == null)) : (!(this.mCurrent == null || this.mCurrent.mNext == null));
    }
    
    public Map.Entry<K, V> next() {
      SafeIterableMap.Entry entry;
      if (this.mBeforeStart) {
        this.mBeforeStart = false;
        this.mCurrent = SafeIterableMap.this.mStart;
        return this.mCurrent;
      } 
      if (this.mCurrent != null) {
        entry = this.mCurrent.mNext;
      } else {
        entry = null;
      } 
      this.mCurrent = entry;
      return this.mCurrent;
    }
    
    public void supportRemove(@NonNull SafeIterableMap.Entry<K, V> param1Entry) {
      if (param1Entry == this.mCurrent) {
        boolean bool;
        this.mCurrent = this.mCurrent.mPrevious;
        if (this.mCurrent == null) {
          bool = true;
        } else {
          bool = false;
        } 
        this.mBeforeStart = bool;
      } 
    }
  }
  
  private static abstract class ListIterator<K, V> implements Iterator<Map.Entry<K, V>>, SupportRemove<K, V> {
    SafeIterableMap.Entry<K, V> mExpectedEnd;
    
    SafeIterableMap.Entry<K, V> mNext;
    
    ListIterator(SafeIterableMap.Entry<K, V> param1Entry1, SafeIterableMap.Entry<K, V> param1Entry2) {
      this.mExpectedEnd = param1Entry2;
      this.mNext = param1Entry1;
    }
    
    private SafeIterableMap.Entry<K, V> nextNode() {
      return (this.mNext == this.mExpectedEnd || this.mExpectedEnd == null) ? null : forward(this.mNext);
    }
    
    abstract SafeIterableMap.Entry<K, V> backward(SafeIterableMap.Entry<K, V> param1Entry);
    
    abstract SafeIterableMap.Entry<K, V> forward(SafeIterableMap.Entry<K, V> param1Entry);
    
    public boolean hasNext() {
      return (this.mNext != null);
    }
    
    public Map.Entry<K, V> next() {
      SafeIterableMap.Entry<K, V> entry = this.mNext;
      this.mNext = nextNode();
      return entry;
    }
    
    public void supportRemove(@NonNull SafeIterableMap.Entry<K, V> param1Entry) {
      if (this.mExpectedEnd == param1Entry && param1Entry == this.mNext) {
        this.mNext = null;
        this.mExpectedEnd = null;
      } 
      if (this.mExpectedEnd == param1Entry)
        this.mExpectedEnd = backward(this.mExpectedEnd); 
      if (this.mNext == param1Entry)
        this.mNext = nextNode(); 
    }
  }
  
  static interface SupportRemove<K, V> {
    void supportRemove(@NonNull SafeIterableMap.Entry<K, V> param1Entry);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/arch/core/internal/SafeIterableMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
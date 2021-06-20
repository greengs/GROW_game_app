package gnu.mapping;

import java.lang.ref.WeakReference;

public class Table2D {
  private static Table2D instance = new Table2D();
  
  int log2Size = 4;
  
  int mask;
  
  int num_bindings;
  
  Entry[] table;
  
  public Table2D() {
    this(64);
  }
  
  public Table2D(int paramInt) {
    while (paramInt > 1 << this.log2Size)
      this.log2Size++; 
    paramInt = 1 << this.log2Size;
    this.table = new Entry[paramInt];
    this.mask = paramInt - 1;
  }
  
  public static final Table2D getInstance() {
    return instance;
  }
  
  public Object get(Object paramObject1, Object paramObject2, Object paramObject3) {
    paramObject1 = lookup(paramObject1, paramObject2, System.identityHashCode(paramObject1), System.identityHashCode(paramObject2), false);
    return (paramObject1 == null || ((Entry)paramObject1).value == paramObject1) ? paramObject3 : ((Entry)paramObject1).value;
  }
  
  public boolean isBound(Object paramObject1, Object paramObject2) {
    boolean bool2 = false;
    paramObject1 = lookup(paramObject1, paramObject2, System.identityHashCode(paramObject1), System.identityHashCode(paramObject2), false);
    boolean bool1 = bool2;
    if (paramObject1 != null) {
      bool1 = bool2;
      if (((Entry)paramObject1).value != paramObject1)
        bool1 = true; 
    } 
    return bool1;
  }
  
  protected Entry lookup(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2, boolean paramBoolean) {
    paramInt2 = (paramInt1 ^ paramInt2) & this.mask;
    Entry entry2 = null;
    Entry entry3 = this.table[paramInt2];
    Entry entry1 = entry3;
    while (entry1 != null) {
      Object object3 = entry1.key1;
      Object object4 = entry1.key2;
      paramInt1 = 0;
      Object object2 = object3;
      if (object3 instanceof WeakReference) {
        object2 = ((WeakReference)object3).get();
        if (object2 == null) {
          paramInt1 = 1;
        } else {
          paramInt1 = 0;
        } 
      } 
      object3 = object4;
      if (object4 instanceof WeakReference) {
        object3 = ((WeakReference)object4).get();
        if (object3 == null);
        paramInt1 = 1;
      } 
      object4 = entry1.chain;
      if (paramInt1 != 0) {
        if (entry2 == null) {
          this.table[paramInt2] = (Entry)object4;
        } else {
          entry2.chain = (Entry)object4;
        } 
        this.num_bindings--;
        entry1.value = entry1;
      } else {
        if (object2 == paramObject1 && object3 == paramObject2)
          return entry1; 
        entry2 = entry1;
      } 
      Object object1 = object4;
    } 
    if (paramBoolean) {
      entry1 = new Entry();
      paramObject1 = wrapReference(paramObject1);
      paramObject2 = wrapReference(paramObject2);
      entry1.key1 = paramObject1;
      entry1.key2 = paramObject2;
      this.num_bindings++;
      entry1.chain = entry3;
      this.table[paramInt2] = entry1;
      entry1.value = entry1;
      return entry1;
    } 
    return null;
  }
  
  public Object put(Object paramObject1, Object paramObject2, Object paramObject3) {
    paramObject1 = lookup(paramObject1, paramObject2, System.identityHashCode(paramObject1), System.identityHashCode(paramObject2), true);
    paramObject2 = paramObject1.getValue();
    ((Entry)paramObject1).value = paramObject3;
    return paramObject2;
  }
  
  void rehash() {
    Entry[] arrayOfEntry1 = this.table;
    int i = arrayOfEntry1.length;
    int j = i * 2;
    Entry[] arrayOfEntry2 = new Entry[j];
    int k = j - 1;
    this.num_bindings = 0;
    label32: while (true) {
      j = i - 1;
      if (j >= 0) {
        Entry entry = arrayOfEntry1[j];
        while (true) {
          i = j;
          if (entry != null) {
            Object object3 = entry.key1;
            Object object4 = entry.key2;
            i = 0;
            Object object2 = object3;
            if (object3 instanceof WeakReference) {
              object2 = ((WeakReference)object3).get();
              if (object2 == null) {
                i = 1;
              } else {
                i = 0;
              } 
            } 
            object3 = object4;
            if (object4 instanceof WeakReference) {
              object3 = ((WeakReference)object4).get();
              if (object3 == null) {
                i = 1;
              } else {
                i = 0;
              } 
            } 
            object4 = entry.chain;
            if (i != 0) {
              entry.value = entry;
            } else {
              i = (System.identityHashCode(object2) ^ System.identityHashCode(object3)) & k;
              entry.chain = arrayOfEntry2[i];
              arrayOfEntry2[i] = entry;
              this.num_bindings++;
            } 
            Object object1 = object4;
            continue;
          } 
          continue label32;
        } 
        break;
      } 
      this.table = arrayOfEntry2;
      this.log2Size++;
      this.mask = k;
      return;
    } 
  }
  
  public Object remove(Object paramObject1, Object paramObject2) {
    return remove(paramObject1, paramObject2, System.identityHashCode(paramObject1) ^ System.identityHashCode(paramObject2));
  }
  
  public Object remove(Object paramObject1, Object paramObject2, int paramInt) {
    return remove(paramObject1, paramObject2, paramInt);
  }
  
  public Object remove(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2) {
    int i = (paramInt1 ^ paramInt2) & this.mask;
    Entry entry2 = null;
    Entry entry1 = this.table[i];
    while (entry1 != null) {
      Object object3 = entry1.key1;
      Object object4 = entry1.key2;
      paramInt1 = 0;
      Object object2 = object3;
      if (object3 instanceof WeakReference) {
        object2 = ((WeakReference)object3).get();
        if (object2 == null) {
          paramInt1 = 1;
        } else {
          paramInt1 = 0;
        } 
      } 
      object3 = object4;
      if (object4 instanceof WeakReference) {
        object3 = ((WeakReference)object4).get();
        if (object3 == null) {
          paramInt1 = 1;
        } else {
          paramInt1 = 0;
        } 
      } 
      object4 = entry1.chain;
      Object object5 = entry1.value;
      if (object2 == paramObject1 && object3 == paramObject2) {
        paramInt2 = 1;
      } else {
        paramInt2 = 0;
      } 
      if (paramInt1 != 0 || paramInt2 != 0) {
        if (entry2 == null) {
          this.table[i] = (Entry)object4;
        } else {
          entry2.chain = (Entry)object4;
        } 
        this.num_bindings--;
        entry1.value = entry1;
      } else {
        if (paramInt2 != 0)
          return object5; 
        entry2 = entry1;
      } 
      Object object1 = object4;
    } 
    return null;
  }
  
  protected Object wrapReference(Object paramObject) {
    return (paramObject == null || paramObject instanceof Symbol) ? paramObject : new WeakReference(paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Table2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
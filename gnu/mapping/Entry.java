package gnu.mapping;

import java.lang.ref.WeakReference;

class Entry {
  Entry chain;
  
  Object key1;
  
  Object key2;
  
  Object value;
  
  public Object getKey1() {
    return (this.key1 instanceof WeakReference) ? ((WeakReference)this.key1).get() : this.key1;
  }
  
  public Object getKey2() {
    return (this.key2 instanceof WeakReference) ? ((WeakReference)this.key2).get() : this.key2;
  }
  
  public Object getValue() {
    return (this.value == this) ? null : this.value;
  }
  
  public boolean matches(Object paramObject1, Object paramObject2) {
    return (paramObject1 == getKey1() && paramObject2 == getKey2());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Entry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.kawa.models;

import java.lang.ref.WeakReference;

public class WeakListener extends WeakReference {
  WeakListener next;
  
  public WeakListener(Object paramObject) {
    super((T)paramObject);
  }
  
  public WeakListener(Object paramObject, WeakListener paramWeakListener) {
    super((T)paramObject);
    this.next = paramWeakListener;
  }
  
  public void update(Object paramObject1, Model paramModel, Object paramObject2) {
    ((ModelListener)paramObject1).modelUpdated(paramModel, paramObject2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/models/WeakListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
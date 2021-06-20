package gnu.kawa.models;

public abstract class Model implements Viewable {
  transient WeakListener listeners;
  
  public void addListener(ModelListener paramModelListener) {
    this.listeners = new WeakListener(paramModelListener, this.listeners);
  }
  
  public void addListener(WeakListener paramWeakListener) {
    paramWeakListener.next = this.listeners;
    this.listeners = paramWeakListener;
  }
  
  public void notifyListeners(String paramString) {
    WeakListener weakListener2 = null;
    for (WeakListener weakListener1 = this.listeners; weakListener1 != null; weakListener1 = weakListener) {
      T t = weakListener1.get();
      WeakListener weakListener = weakListener1.next;
      if (t == null) {
        weakListener1 = weakListener2;
        if (weakListener2 != null) {
          weakListener2.next = weakListener;
          weakListener1 = weakListener2;
        } 
      } else {
        weakListener2 = weakListener1;
        weakListener1.update(t, this, paramString);
        weakListener1 = weakListener2;
      } 
      weakListener2 = weakListener1;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/models/Model.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
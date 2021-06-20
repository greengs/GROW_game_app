package androidx.appcompat.view.menu;

class BaseWrapper<T> {
  final T mWrappedObject;
  
  BaseWrapper(T paramT) {
    if (paramT == null)
      throw new IllegalArgumentException("Wrapped Object can not be null."); 
    this.mWrappedObject = paramT;
  }
  
  public T getWrappedObject() {
    return this.mWrappedObject;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/view/menu/BaseWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
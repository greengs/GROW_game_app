package gnu.mapping;

public class ConstrainedLocation extends Location {
  protected Location base;
  
  protected Procedure converter;
  
  public static ConstrainedLocation make(Location paramLocation, Procedure paramProcedure) {
    ConstrainedLocation constrainedLocation = new ConstrainedLocation();
    constrainedLocation.base = paramLocation;
    constrainedLocation.converter = paramProcedure;
    return constrainedLocation;
  }
  
  protected Object coerce(Object paramObject) {
    try {
      return this.converter.apply1(paramObject);
    } catch (Throwable throwable) {
      throw WrappedException.wrapIfNeeded(throwable);
    } 
  }
  
  public final Object get(Object paramObject) {
    return this.base.get(paramObject);
  }
  
  public Object getKeyProperty() {
    return this.base.getKeyProperty();
  }
  
  public Symbol getKeySymbol() {
    return this.base.getKeySymbol();
  }
  
  public boolean isBound() {
    return this.base.isBound();
  }
  
  public boolean isConstant() {
    return this.base.isConstant();
  }
  
  public final void set(Object paramObject) {
    this.base.set(coerce(paramObject));
  }
  
  public void setRestore(Object paramObject) {
    this.base.setRestore(paramObject);
  }
  
  public Object setWithSave(Object paramObject) {
    return this.base.setWithSave(coerce(paramObject));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/ConstrainedLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
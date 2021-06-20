package gnu.mapping;

public class PlainLocation extends NamedLocation {
  public PlainLocation(Symbol paramSymbol, Object paramObject) {
    super(paramSymbol, paramObject);
  }
  
  public PlainLocation(Symbol paramSymbol, Object paramObject1, Object paramObject2) {
    super(paramSymbol, paramObject1);
    this.value = paramObject2;
  }
  
  public final Object get(Object paramObject) {
    return (this.base != null) ? this.base.get(paramObject) : ((this.value != Location.UNBOUND) ? this.value : paramObject);
  }
  
  public boolean isBound() {
    return (this.base != null) ? this.base.isBound() : ((this.value != Location.UNBOUND));
  }
  
  public final void set(Object paramObject) {
    if (this.base == null) {
      this.value = paramObject;
      return;
    } 
    if (this.value == DIRECT_ON_SET) {
      this.base = null;
      this.value = paramObject;
      return;
    } 
    if (this.base.isConstant()) {
      getEnvironment().put(getKeySymbol(), getKeyProperty(), paramObject);
      return;
    } 
    this.base.set(paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/PlainLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
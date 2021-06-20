package gnu.mapping;

public abstract class IndirectableLocation extends Location {
  protected static final Object DIRECT_ON_SET = new String("(direct-on-set)");
  
  protected static final Object INDIRECT_FLUIDS = new String("(indirect-fluids)");
  
  protected Location base;
  
  protected Object value;
  
  public Location getBase() {
    return (this.base == null) ? this : this.base.getBase();
  }
  
  public Location getBaseForce() {
    return (this.base == null) ? new PlainLocation(getKeySymbol(), getKeyProperty(), this.value) : this.base;
  }
  
  public Environment getEnvironment() {
    return (this.base instanceof NamedLocation) ? ((NamedLocation)this.base).getEnvironment() : null;
  }
  
  public Object getKeyProperty() {
    return (this.base != null) ? this.base.getKeyProperty() : null;
  }
  
  public Symbol getKeySymbol() {
    return (this.base != null) ? this.base.getKeySymbol() : null;
  }
  
  public boolean isConstant() {
    return (this.base != null && this.base.isConstant());
  }
  
  public void setAlias(Location paramLocation) {
    this.base = paramLocation;
    this.value = INDIRECT_FLUIDS;
  }
  
  public void setBase(Location paramLocation) {
    this.base = paramLocation;
    this.value = null;
  }
  
  public void undefine() {
    this.base = null;
    this.value = UNBOUND;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/IndirectableLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
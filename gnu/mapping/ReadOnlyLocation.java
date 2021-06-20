package gnu.mapping;

public class ReadOnlyLocation extends ConstrainedLocation {
  public static ReadOnlyLocation make(Location paramLocation) {
    ReadOnlyLocation readOnlyLocation = new ReadOnlyLocation();
    readOnlyLocation.base = paramLocation;
    return readOnlyLocation;
  }
  
  protected Object coerce(Object paramObject) {
    paramObject = new StringBuffer("attempt to modify read-only location");
    Symbol symbol = getKeySymbol();
    if (symbol != null) {
      paramObject.append(": ");
      paramObject.append(symbol);
    } 
    throw new IllegalStateException(paramObject.toString());
  }
  
  public boolean isConstant() {
    return true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/ReadOnlyLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
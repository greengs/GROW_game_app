package gnu.mapping;

public class LocationProc extends Procedure0or1 implements HasSetter {
  Location loc;
  
  public LocationProc(Location paramLocation) {
    this.loc = paramLocation;
  }
  
  public LocationProc(Location paramLocation, Procedure paramProcedure) {
    this.loc = paramLocation;
    if (paramProcedure != null)
      pushConverter(paramProcedure); 
  }
  
  public static LocationProc makeNamed(Symbol paramSymbol, Location paramLocation) {
    LocationProc locationProc = new LocationProc(paramLocation);
    locationProc.setSymbol(paramSymbol);
    return locationProc;
  }
  
  public Object apply0() throws Throwable {
    return this.loc.get();
  }
  
  public Object apply1(Object paramObject) throws Throwable {
    set0(paramObject);
    return Values.empty;
  }
  
  public final Location getLocation() {
    return this.loc;
  }
  
  public Procedure getSetter() {
    return new Setter0(this);
  }
  
  public void pushConverter(Procedure paramProcedure) {
    this.loc = ConstrainedLocation.make(this.loc, paramProcedure);
  }
  
  public void set0(Object paramObject) throws Throwable {
    this.loc.set(paramObject);
  }
  
  public String toString() {
    return (getSymbol() != null) ? super.toString() : ("#<location-proc " + this.loc + ">");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/LocationProc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
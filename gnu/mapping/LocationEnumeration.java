package gnu.mapping;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LocationEnumeration implements Iterator<Location>, Enumeration<Location> {
  NamedLocation[] bindings;
  
  SimpleEnvironment env;
  
  int index;
  
  LocationEnumeration inherited;
  
  NamedLocation nextLoc;
  
  NamedLocation prevLoc;
  
  public LocationEnumeration(SimpleEnvironment paramSimpleEnvironment) {
    this(paramSimpleEnvironment.table, 1 << paramSimpleEnvironment.log2Size);
  }
  
  public LocationEnumeration(NamedLocation[] paramArrayOfNamedLocation, int paramInt) {
    this.bindings = paramArrayOfNamedLocation;
    this.index = paramInt;
  }
  
  public boolean hasMoreElements() {
    return this.env.hasMoreElements(this);
  }
  
  public boolean hasNext() {
    return hasMoreElements();
  }
  
  public Location next() {
    return nextElement();
  }
  
  public Location nextElement() {
    return nextLocation();
  }
  
  public Location nextLocation() {
    if (this.nextLoc == null && !hasMoreElements())
      throw new NoSuchElementException(); 
    NamedLocation namedLocation = this.prevLoc;
    if (this.prevLoc == null) {
      namedLocation = this.bindings[this.index];
      if (this.nextLoc != namedLocation)
        this.prevLoc = namedLocation; 
    } 
    while (this.prevLoc != null && this.prevLoc.next != this.nextLoc)
      this.prevLoc = this.prevLoc.next; 
    namedLocation = this.nextLoc;
    this.nextLoc = this.nextLoc.next;
    return namedLocation;
  }
  
  public void remove() {
    NamedLocation namedLocation;
    if (this.prevLoc != null) {
      namedLocation = this.prevLoc.next;
    } else {
      namedLocation = this.bindings[this.index];
    } 
    if (namedLocation == null || namedLocation.next != this.nextLoc)
      throw new IllegalStateException(); 
    namedLocation.next = null;
    if (this.prevLoc != null) {
      this.prevLoc.next = this.nextLoc;
    } else {
      this.bindings[this.index] = this.nextLoc;
    } 
    SimpleEnvironment simpleEnvironment = this.env;
    simpleEnvironment.num_bindings--;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/LocationEnumeration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
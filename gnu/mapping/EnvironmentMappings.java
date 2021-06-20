package gnu.mapping;

import java.util.AbstractSet;
import java.util.Iterator;

class EnvironmentMappings extends AbstractSet {
  SimpleEnvironment env;
  
  public EnvironmentMappings(SimpleEnvironment paramSimpleEnvironment) {
    this.env = paramSimpleEnvironment;
  }
  
  public Iterator iterator() {
    return new LocationEnumeration(this.env);
  }
  
  public int size() {
    return this.env.size();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/EnvironmentMappings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
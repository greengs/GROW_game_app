package gnu.expr;

import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.LocationEnumeration;
import gnu.mapping.NamedLocation;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;

public class BuiltinEnvironment extends Environment {
  static final BuiltinEnvironment instance = new BuiltinEnvironment();
  
  static {
    instance.setName("language-builtins");
  }
  
  public static BuiltinEnvironment getInstance() {
    return instance;
  }
  
  public NamedLocation addLocation(Symbol paramSymbol, Object paramObject, Location paramLocation) {
    throw new RuntimeException();
  }
  
  public void define(Symbol paramSymbol, Object paramObject1, Object paramObject2) {
    throw new RuntimeException();
  }
  
  public LocationEnumeration enumerateAllLocations() {
    return getLangEnvironment().enumerateAllLocations();
  }
  
  public LocationEnumeration enumerateLocations() {
    return getLangEnvironment().enumerateLocations();
  }
  
  public Environment getLangEnvironment() {
    Language language = Language.getDefaultLanguage();
    return (language == null) ? null : language.getLangEnvironment();
  }
  
  public NamedLocation getLocation(Symbol paramSymbol, Object paramObject, int paramInt, boolean paramBoolean) {
    throw new RuntimeException();
  }
  
  protected boolean hasMoreElements(LocationEnumeration paramLocationEnumeration) {
    throw new RuntimeException();
  }
  
  public NamedLocation lookup(Symbol paramSymbol, Object paramObject, int paramInt) {
    if (paramObject != ThreadLocation.ANONYMOUS) {
      Language language = Language.getDefaultLanguage();
      if (language != null)
        return language.lookupBuiltin(paramSymbol, paramObject, paramInt); 
    } 
    return null;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/BuiltinEnvironment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
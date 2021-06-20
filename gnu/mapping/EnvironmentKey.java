package gnu.mapping;

public interface EnvironmentKey {
  public static final Object FUNCTION = Symbol.FUNCTION;
  
  Object getKeyProperty();
  
  Symbol getKeySymbol();
  
  boolean matches(EnvironmentKey paramEnvironmentKey);
  
  boolean matches(Symbol paramSymbol, Object paramObject);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/EnvironmentKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
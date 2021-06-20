package gnu.mapping;

import java.io.PrintWriter;

public abstract class Location {
  public static final String UNBOUND = new String("(unbound)");
  
  public static IndirectableLocation make(Symbol paramSymbol) {
    PlainLocation plainLocation = new PlainLocation(paramSymbol, null);
    plainLocation.base = null;
    plainLocation.value = UNBOUND;
    return plainLocation;
  }
  
  public static IndirectableLocation make(String paramString) {
    PlainLocation plainLocation = new PlainLocation(Namespace.EmptyNamespace.getSymbol(paramString.intern()), null);
    plainLocation.base = null;
    plainLocation.value = UNBOUND;
    return plainLocation;
  }
  
  public static Location make(Object paramObject, String paramString) {
    ThreadLocation threadLocation = new ThreadLocation(paramString);
    threadLocation.setGlobal(paramObject);
    return threadLocation;
  }
  
  public boolean entered() {
    return false;
  }
  
  public final Object get() {
    String str = UNBOUND;
    Object object = get(str);
    if (object == str)
      throw new UnboundLocationException(this); 
    return object;
  }
  
  public abstract Object get(Object paramObject);
  
  public Location getBase() {
    return this;
  }
  
  public Object getKeyProperty() {
    return null;
  }
  
  public Symbol getKeySymbol() {
    return null;
  }
  
  public final Object getValue() {
    return get(null);
  }
  
  public boolean isBound() {
    String str = UNBOUND;
    return (get(str) != str);
  }
  
  public boolean isConstant() {
    return false;
  }
  
  public void print(PrintWriter paramPrintWriter) {
    paramPrintWriter.print("#<location ");
    Symbol symbol = getKeySymbol();
    if (symbol != null)
      paramPrintWriter.print(symbol); 
    String str = UNBOUND;
    Object object = get(str);
    if (object != str) {
      paramPrintWriter.print(" -> ");
      paramPrintWriter.print(object);
    } else {
      paramPrintWriter.print("(unbound)");
    } 
    paramPrintWriter.print('>');
  }
  
  public abstract void set(Object paramObject);
  
  public void setRestore(Object paramObject) {
    set(paramObject);
  }
  
  public final Object setValue(Object paramObject) {
    Object object = get(null);
    set(paramObject);
    return object;
  }
  
  public Object setWithSave(Object paramObject) {
    Object object = get(UNBOUND);
    set(paramObject);
    return object;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(getClass().getName());
    Symbol symbol = getKeySymbol();
    stringBuffer.append('[');
    if (symbol != null) {
      stringBuffer.append(symbol);
      Object object = getKeyProperty();
      if (object != null && object != this) {
        stringBuffer.append('/');
        stringBuffer.append(object);
      } 
    } 
    stringBuffer.append("]");
    return stringBuffer.toString();
  }
  
  public void undefine() {
    set(UNBOUND);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Location.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
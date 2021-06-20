package gnu.mapping;

public class LazyPropertyKey<T> extends PropertyKey<T> {
  public LazyPropertyKey(String paramString) {
    super(paramString);
  }
  
  public T get(PropertySet paramPropertySet, T paramT) {
    paramT = (T)paramPropertySet.getProperty(this, paramT);
    if (paramT instanceof String) {
      String str1;
      byte b;
      StringBuilder stringBuilder;
      String str3 = (String)paramT;
      if (str3.charAt(0) == '*') {
        b = 1;
      } else {
        b = 0;
      } 
      int i = str3.indexOf(':');
      if (i <= b || i >= str3.length() - 1)
        throw new RuntimeException("lazy property " + this + " must have the form \"ClassName:fieldName\" or \"ClassName:staticMethodName\""); 
      String str4 = str3.substring(b, i);
      String str2 = str3.substring(i + 1);
      try {
        Class<?> clazz = Class.forName(str4, true, paramPropertySet.getClass().getClassLoader());
        if (b == 0) {
          object = clazz.getField(str2).get(null);
          paramPropertySet.setProperty(this, object);
          return (T)object;
        } 
        object = clazz.getDeclaredMethod((String)object, new Class[] { Object.class }).invoke(null, new Object[] { paramPropertySet });
        paramPropertySet.setProperty(this, object);
        return (T)object;
      } catch (Throwable object) {
        stringBuilder = (new StringBuilder()).append("lazy property ").append(this).append(" has specifier \"").append(str3).append("\" but there is no such ");
        if (b == 0) {
          str1 = "field";
          throw new RuntimeException(stringBuilder.append(str1).toString(), object);
        } 
      } 
      throw new RuntimeException(stringBuilder.append(str1).toString(), object);
    } 
    return (T)object;
  }
  
  public void set(PropertySet paramPropertySet, String paramString) {
    paramPropertySet.setProperty(this, paramString);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/LazyPropertyKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.mapping;

public abstract class PropertySet implements Named {
  public static final Symbol nameKey = Namespace.EmptyNamespace.getSymbol("name");
  
  private Object[] properties;
  
  public static Object[] setProperty(Object[] paramArrayOfObject, Object paramObject1, Object paramObject2) {
    Object[] arrayOfObject = paramArrayOfObject;
    if (arrayOfObject == null) {
      Object[] arrayOfObject1 = new Object[10];
      paramArrayOfObject = arrayOfObject1;
      byte b = 0;
      arrayOfObject1[b] = paramObject1;
      arrayOfObject1[b + 1] = paramObject2;
      return paramArrayOfObject;
    } 
    int i = -1;
    int j = arrayOfObject.length;
    while (true) {
      int k = j - 2;
      if (k >= 0) {
        Object object = arrayOfObject[k];
        if (object == paramObject1) {
          paramObject1 = arrayOfObject[k + 1];
          arrayOfObject[k + 1] = paramObject2;
          return paramArrayOfObject;
        } 
        j = k;
        if (object == null) {
          i = k;
          j = k;
        } 
        continue;
      } 
      j = i;
      Object[] arrayOfObject1 = arrayOfObject;
      if (i < 0) {
        j = arrayOfObject.length;
        paramArrayOfObject = new Object[j * 2];
        System.arraycopy(arrayOfObject, 0, paramArrayOfObject, 0, j);
        arrayOfObject1 = paramArrayOfObject;
      } 
      arrayOfObject1[j] = paramObject1;
      arrayOfObject1[j + 1] = paramObject2;
      return paramArrayOfObject;
    } 
  }
  
  public String getName() {
    Object object = getProperty(nameKey, null);
    return (object == null) ? null : ((object instanceof Symbol) ? ((Symbol)object).getName() : object.toString());
  }
  
  public Object getProperty(Object paramObject1, Object paramObject2) {
    Object object = paramObject2;
    if (this.properties != null) {
      int i = this.properties.length;
      while (true) {
        int j = i - 2;
        object = paramObject2;
        if (j >= 0) {
          i = j;
          if (this.properties[j] == paramObject1) {
            object = this.properties[j + 1];
            break;
          } 
          continue;
        } 
        break;
      } 
    } 
    return object;
  }
  
  public Object getSymbol() {
    return getProperty(nameKey, null);
  }
  
  public Object removeProperty(Object paramObject) {
    Object[] arrayOfObject = this.properties;
    if (arrayOfObject == null)
      return null; 
    int i = arrayOfObject.length;
    while (true) {
      int j = i - 2;
      if (j >= 0) {
        i = j;
        if (arrayOfObject[j] == paramObject) {
          paramObject = arrayOfObject[j + 1];
          arrayOfObject[j] = null;
          arrayOfObject[j + 1] = null;
          return paramObject;
        } 
        continue;
      } 
      return null;
    } 
  }
  
  public final void setName(String paramString) {
    setProperty(nameKey, paramString);
  }
  
  public void setProperty(Object paramObject1, Object paramObject2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield properties : [Ljava/lang/Object;
    //   7: aload_1
    //   8: aload_2
    //   9: invokestatic setProperty : ([Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)[Ljava/lang/Object;
    //   12: putfield properties : [Ljava/lang/Object;
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	18	finally
  }
  
  public final void setSymbol(Object paramObject) {
    setProperty(nameKey, paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/PropertySet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
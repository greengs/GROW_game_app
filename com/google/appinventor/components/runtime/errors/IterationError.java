package com.google.appinventor.components.runtime.errors;

public class IterationError extends DispatchableError {
  public IterationError(int paramInt, Object[] paramArrayOfObject) {
    super(paramInt, paramArrayOfObject);
  }
  
  public static DispatchableError fromError(int paramInt, DispatchableError paramDispatchableError) {
    switch (paramDispatchableError.getErrorCode()) {
      default:
        return paramDispatchableError;
      case 3405:
        return new IterationError(3406, prepend(paramInt, paramDispatchableError.getArguments()));
      case 3410:
        return new IterationError(3407, prepend(paramInt, paramDispatchableError.getArguments()));
      case 3409:
        break;
    } 
    return new IterationError(3408, prepend(paramInt, paramDispatchableError.getArguments()));
  }
  
  private static Object[] prepend(int paramInt, Object[] paramArrayOfObject) {
    Object[] arrayOfObject = new Object[paramArrayOfObject.length + 1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 1, paramArrayOfObject.length);
    return arrayOfObject;
  }
  
  public String getExpected() {
    return (String)getArguments()[1];
  }
  
  public String getFound() {
    return (String)getArguments()[2];
  }
  
  public int getIndex() {
    return ((Integer)getArguments()[0]).intValue();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/errors/IterationError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
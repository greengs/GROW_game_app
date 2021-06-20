package com.google.appinventor.components.runtime.errors;

import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public abstract class RuntimeError extends RuntimeException {
  protected RuntimeError() {}
  
  protected RuntimeError(String paramString) {
    super(paramString);
  }
  
  public static RuntimeError convertToRuntimeError(Throwable paramThrowable) {
    if (paramThrowable instanceof RuntimeError)
      return (RuntimeError)paramThrowable; 
    if (paramThrowable instanceof ArrayIndexOutOfBoundsException)
      return new ArrayIndexOutOfBoundsError(); 
    if (paramThrowable instanceof IllegalArgumentException)
      return new IllegalArgumentError(); 
    if (paramThrowable instanceof NullPointerException)
      return new UninitializedInstanceError(); 
    throw new UnsupportedOperationException(paramThrowable);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/errors/RuntimeError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package com.google.appinventor.components.runtime.errors;

public class YailRuntimeError extends RuntimeError {
  private String errorType;
  
  public YailRuntimeError(String paramString1, String paramString2) {
    super(paramString1);
    this.errorType = paramString2;
  }
  
  public String getErrorType() {
    return this.errorType;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/errors/YailRuntimeError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
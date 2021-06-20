package com.google.appinventor.components.runtime.errors;

import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.util.Arrays;

public class DispatchableError extends RuntimeError {
  private final Object[] arguments;
  
  private final int errorCode;
  
  public DispatchableError(int paramInt) {
    super(ErrorMessages.formatMessage(paramInt, null));
    this.errorCode = paramInt;
    this.arguments = new Object[0];
  }
  
  public DispatchableError(int paramInt, Object... paramVarArgs) {
    super(ErrorMessages.formatMessage(paramInt, paramVarArgs));
    this.errorCode = paramInt;
    this.arguments = paramVarArgs;
  }
  
  public Object[] getArguments() {
    return Arrays.copyOf(this.arguments, this.arguments.length);
  }
  
  public int getErrorCode() {
    return this.errorCode;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/errors/DispatchableError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
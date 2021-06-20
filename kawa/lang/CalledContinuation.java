package kawa.lang;

import gnu.mapping.CallContext;

public class CalledContinuation extends RuntimeException {
  public Continuation continuation;
  
  public CallContext ctx;
  
  public Object[] values;
  
  CalledContinuation(Object[] paramArrayOfObject, Continuation paramContinuation, CallContext paramCallContext) {
    super("call/cc called");
    this.values = paramArrayOfObject;
    this.continuation = paramContinuation;
    this.ctx = paramCallContext;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/CalledContinuation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package kawa.lang;

import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;

public class Continuation extends MethodProc {
  static int counter;
  
  int id;
  
  public boolean invoked;
  
  public Continuation(CallContext paramCallContext) {}
  
  public static Object handleException(Throwable paramThrowable, Continuation paramContinuation) throws Throwable {
    if (paramThrowable instanceof CalledContinuation) {
      CalledContinuation calledContinuation = (CalledContinuation)paramThrowable;
      if (calledContinuation.continuation == paramContinuation) {
        paramContinuation.invoked = true;
        return Values.make(calledContinuation.values);
      } 
    } 
    throw paramThrowable;
  }
  
  public static void handleException$X(Throwable paramThrowable, Continuation paramContinuation, CallContext paramCallContext) throws Throwable {
    Object[] arrayOfObject;
    if (paramThrowable instanceof CalledContinuation) {
      CalledContinuation calledContinuation = (CalledContinuation)paramThrowable;
      if (calledContinuation.continuation == paramContinuation) {
        paramContinuation.invoked = true;
        arrayOfObject = calledContinuation.values;
        int j = arrayOfObject.length;
        for (int i = 0; i < j; i++)
          paramCallContext.consumer.writeObject(arrayOfObject[i]); 
        return;
      } 
    } 
    throw arrayOfObject;
  }
  
  public void apply(CallContext paramCallContext) {
    if (this.invoked)
      throw new GenericError("implementation restriction: continuation can only be used once"); 
    throw new CalledContinuation(paramCallContext.values, this, paramCallContext);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = (new StringBuilder()).append("#<continuation ").append(this.id);
    if (this.invoked) {
      String str1 = " (invoked)>";
      return stringBuilder.append(str1).toString();
    } 
    String str = ">";
    return stringBuilder.append(str).toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/Continuation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
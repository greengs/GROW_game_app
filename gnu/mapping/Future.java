package gnu.mapping;

public class Future extends Thread {
  public RunnableClosure closure;
  
  public Future(Procedure paramProcedure) {
    this.closure = new RunnableClosure(paramProcedure);
  }
  
  public Future(Procedure paramProcedure, CallContext paramCallContext) {
    this.closure = new RunnableClosure(paramProcedure, paramCallContext);
  }
  
  public Future(Procedure paramProcedure, InPort paramInPort, OutPort paramOutPort1, OutPort paramOutPort2) {
    this.closure = new RunnableClosure(paramProcedure, paramInPort, paramOutPort1, paramOutPort2);
  }
  
  public static Future make(Procedure paramProcedure, Environment paramEnvironment, InPort paramInPort, OutPort paramOutPort1, OutPort paramOutPort2) {
    paramEnvironment = Environment.setSaveCurrent(paramEnvironment);
    try {
      return new Future(paramProcedure, paramInPort, paramOutPort1, paramOutPort2);
    } finally {
      Environment.restoreCurrent(paramEnvironment);
    } 
  }
  
  public final CallContext getCallContext() {
    return this.closure.getCallContext();
  }
  
  public void run() {
    this.closure.run();
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("#<future ");
    stringBuffer.append(getName());
    stringBuffer.append(">");
    return stringBuffer.toString();
  }
  
  public Object waitForResult() throws Throwable {
    try {
      join();
      Throwable throwable = this.closure.exception;
      if (throwable != null)
        throw throwable; 
    } catch (InterruptedException interruptedException) {
      throw new RuntimeException("thread join [force] was interrupted");
    } 
    return this.closure.result;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Future.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
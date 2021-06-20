package gnu.mapping;

import java.util.concurrent.Callable;

public class RunnableClosure implements Callable<Object>, Runnable {
  static int nrunnables = 0;
  
  Procedure action;
  
  CallContext context;
  
  private OutPort err;
  
  Throwable exception;
  
  private InPort in;
  
  String name;
  
  private OutPort out;
  
  Object result;
  
  public RunnableClosure(Procedure paramProcedure) {
    this(paramProcedure, CallContext.getInstance());
  }
  
  public RunnableClosure(Procedure paramProcedure, CallContext paramCallContext) {
    StringBuilder stringBuilder = (new StringBuilder()).append("r");
    int i = nrunnables;
    nrunnables = i + 1;
    setName(stringBuilder.append(i).toString());
    this.action = paramProcedure;
  }
  
  public RunnableClosure(Procedure paramProcedure, InPort paramInPort, OutPort paramOutPort1, OutPort paramOutPort2) {
    this(paramProcedure, CallContext.getInstance());
    this.in = paramInPort;
    this.out = paramOutPort1;
    this.err = paramOutPort2;
  }
  
  public Object call() throws Exception {
    run();
    Throwable throwable = this.exception;
    if (throwable != null) {
      if (throwable instanceof Exception)
        throw (Exception)throwable; 
      if (throwable instanceof Error)
        throw (Error)throwable; 
      throw new RuntimeException(throwable);
    } 
    return this.result;
  }
  
  public final CallContext getCallContext() {
    return this.context;
  }
  
  public String getName() {
    return this.name;
  }
  
  Object getResult() throws Throwable {
    Throwable throwable = this.exception;
    if (throwable != null)
      throw throwable; 
    return this.result;
  }
  
  public void run() {
    try {
      Environment environment = Environment.getCurrent();
      String str = getName();
      if (environment != null && environment.getSymbol() == null && str != null)
        environment.setName(str); 
      if (this.context == null) {
        this.context = CallContext.getInstance();
      } else {
        CallContext.setInstance(this.context);
      } 
      if (this.in != null)
        InPort.setInDefault(this.in); 
      if (this.out != null)
        OutPort.setOutDefault(this.out); 
      if (this.err != null)
        OutPort.setErrDefault(this.err); 
      this.result = this.action.apply0();
      return;
    } catch (Throwable throwable) {
      this.exception = throwable;
      return;
    } 
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("#<runnable ");
    stringBuffer.append(getName());
    stringBuffer.append(">");
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/RunnableClosure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
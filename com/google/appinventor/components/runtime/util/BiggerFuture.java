package com.google.appinventor.components.runtime.util;

import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.RunnableClosure;

public class BiggerFuture extends Thread {
  public BiggerFuture(Procedure paramProcedure, InPort paramInPort, OutPort paramOutPort1, OutPort paramOutPort2, String paramString, long paramLong) {
    super(new ThreadGroup("biggerthreads"), (Runnable)new RunnableClosure(paramProcedure, paramInPort, paramOutPort1, paramOutPort2), paramString, paramLong);
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("#<future ");
    stringBuffer.append(getName());
    stringBuffer.append(">");
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/BiggerFuture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
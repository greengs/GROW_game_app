package kawa.standard;

import gnu.kawa.functions.ObjectFormat;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.math.IntNum;
import java.io.IOException;
import java.io.PrintWriter;

public class TracedProcedure extends ProcedureN {
  static Symbol curIndentSym;
  
  static int indentationStep = 2;
  
  boolean enabled;
  
  public Procedure proc;
  
  static {
    curIndentSym = Symbol.makeUninterned("current-indentation");
  }
  
  public TracedProcedure(Procedure paramProcedure, boolean paramBoolean) {
    this.proc = paramProcedure;
    this.enabled = paramBoolean;
    String str = paramProcedure.getName();
    if (str != null)
      setName(str); 
  }
  
  public static Procedure doTrace(Procedure paramProcedure, boolean paramBoolean) {
    if (paramProcedure instanceof TracedProcedure) {
      ((TracedProcedure)paramProcedure).enabled = paramBoolean;
      return paramProcedure;
    } 
    return (Procedure)new TracedProcedure(paramProcedure, paramBoolean);
  }
  
  static void indent(int paramInt, PrintWriter paramPrintWriter) {
    while (true) {
      if (--paramInt >= 0) {
        paramPrintWriter.print(' ');
        continue;
      } 
      break;
    } 
  }
  
  static void put(Object paramObject, PrintWriter paramPrintWriter) {
    try {
      if (!ObjectFormat.format(paramObject, paramPrintWriter, 50, true))
        paramPrintWriter.print("..."); 
      return;
    } catch (IOException iOException) {
      paramPrintWriter.print("<caught ");
      paramPrintWriter.print(iOException);
      paramPrintWriter.print('>');
      return;
    } 
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    if (this.enabled) {
      int i;
      Location location = Environment.getCurrent().getLocation(curIndentSym);
      Object object1 = location.get(null);
      if (!(object1 instanceof IntNum)) {
        i = 0;
        location.set(IntNum.zero());
      } else {
        i = ((IntNum)object1).intValue();
      } 
      OutPort outPort = OutPort.errDefault();
      String str = getName();
      object1 = str;
      if (str == null)
        object1 = "??"; 
      indent(i, (PrintWriter)outPort);
      outPort.print("call to ");
      outPort.print((String)object1);
      int k = paramArrayOfObject.length;
      outPort.print(" (");
      for (int j = 0; j < k; j++) {
        if (j > 0)
          outPort.print(' '); 
        put(paramArrayOfObject[j], (PrintWriter)outPort);
      } 
      outPort.println(")");
      Object object2 = location.setWithSave(IntNum.make(indentationStep + i));
      try {
        Object object = this.proc.applyN(paramArrayOfObject);
        location.setRestore(object2);
        indent(i, (PrintWriter)outPort);
        outPort.print("return from ");
        outPort.print((String)object1);
        outPort.print(" => ");
        put(object, (PrintWriter)outPort);
        return object;
      } catch (RuntimeException runtimeException) {
        indent(i, (PrintWriter)outPort);
        outPort.println("procedure " + object1 + " throws exception " + runtimeException);
        throw runtimeException;
      } finally {
        location.setRestore(object2);
      } 
    } 
    return this.proc.applyN(paramArrayOfObject);
  }
  
  public void print(PrintWriter paramPrintWriter) {
    paramPrintWriter.print("#<procedure ");
    String str = getName();
    if (str == null) {
      paramPrintWriter.print("<unnamed>");
    } else {
      paramPrintWriter.print(str);
    } 
    if (this.enabled) {
      str = ", traced>";
    } else {
      str = ">";
    } 
    paramPrintWriter.print(str);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/TracedProcedure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.mapping;

public class Setter extends ProcedureN {
  protected Procedure getter;
  
  public Setter(Procedure paramProcedure) {
    this.getter = paramProcedure;
    String str = paramProcedure.getName();
    if (str != null)
      setName("(setter " + str + ")"); 
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    this.getter.setN(paramArrayOfObject);
    return Values.empty;
  }
  
  public int numArgs() {
    int i = this.getter.numArgs();
    return (i < 0) ? (i + 1) : (i + 4097);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Setter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.mapping;

public abstract class ProcedureN extends Procedure {
  public static final Object[] noArgs = new Object[0];
  
  public ProcedureN() {}
  
  public ProcedureN(String paramString) {
    super(paramString);
  }
  
  public Object apply0() throws Throwable {
    return applyN(noArgs);
  }
  
  public Object apply1(Object paramObject) throws Throwable {
    return applyN(new Object[] { paramObject });
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    return applyN(new Object[] { paramObject1, paramObject2 });
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3) throws Throwable {
    return applyN(new Object[] { paramObject1, paramObject2, paramObject3 });
  }
  
  public Object apply4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) throws Throwable {
    return applyN(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 });
  }
  
  public abstract Object applyN(Object[] paramArrayOfObject) throws Throwable;
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/ProcedureN.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
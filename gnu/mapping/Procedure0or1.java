package gnu.mapping;

public abstract class Procedure0or1 extends Procedure {
  public Procedure0or1() {}
  
  public Procedure0or1(String paramString) {
    super(paramString);
  }
  
  public abstract Object apply0() throws Throwable;
  
  public abstract Object apply1(Object paramObject) throws Throwable;
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    throw new WrongArguments(this, 2);
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3) {
    throw new WrongArguments(this, 3);
  }
  
  public Object apply4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    throw new WrongArguments(this, 4);
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    if (paramArrayOfObject.length == 0)
      return apply0(); 
    if (paramArrayOfObject.length == 1)
      return apply1(paramArrayOfObject[0]); 
    throw new WrongArguments(this, paramArrayOfObject.length);
  }
  
  public int numArgs() {
    return 4096;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Procedure0or1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
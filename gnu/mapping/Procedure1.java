package gnu.mapping;

public abstract class Procedure1 extends Procedure {
  public Procedure1() {}
  
  public Procedure1(String paramString) {
    super(paramString);
  }
  
  public Object apply0() throws Throwable {
    throw new WrongArguments(this, 0);
  }
  
  public abstract Object apply1(Object paramObject) throws Throwable;
  
  public Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    throw new WrongArguments(this, 2);
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3) throws Throwable {
    throw new WrongArguments(this, 3);
  }
  
  public Object apply4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) throws Throwable {
    throw new WrongArguments(this, 4);
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    if (paramArrayOfObject.length != 1)
      throw new WrongArguments(this, paramArrayOfObject.length); 
    return apply1(paramArrayOfObject[0]);
  }
  
  public int numArgs() {
    return 4097;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Procedure1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.mapping;

public abstract class Procedure2 extends Procedure {
  public Procedure2() {}
  
  public Procedure2(String paramString) {
    super(paramString);
  }
  
  public Object apply0() throws Throwable {
    throw new WrongArguments(getName(), 2, "(?)");
  }
  
  public Object apply1(Object paramObject) throws Throwable {
    throw new WrongArguments(this, 1);
  }
  
  public abstract Object apply2(Object paramObject1, Object paramObject2) throws Throwable;
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3) {
    throw new WrongArguments(this, 3);
  }
  
  public Object apply4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) throws Throwable {
    throw new WrongArguments(this, 4);
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    if (paramArrayOfObject.length != 2)
      throw new WrongArguments(this, paramArrayOfObject.length); 
    return apply2(paramArrayOfObject[0], paramArrayOfObject[1]);
  }
  
  public int numArgs() {
    return 8194;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Procedure2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.mapping;

public abstract class Procedure3 extends Procedure {
  public Procedure3() {}
  
  public Procedure3(String paramString) {
    super(paramString);
  }
  
  public Object apply0() {
    throw new WrongArguments(this, 0);
  }
  
  public Object apply1(Object paramObject) {
    throw new WrongArguments(this, 1);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    throw new WrongArguments(this, 2);
  }
  
  public abstract Object apply3(Object paramObject1, Object paramObject2, Object paramObject3) throws Throwable;
  
  public Object apply4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    throw new WrongArguments(this, 4);
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    if (paramArrayOfObject.length != 3)
      throw new WrongArguments(this, paramArrayOfObject.length); 
    return apply3(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2]);
  }
  
  public int numArgs() {
    return 12291;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Procedure3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
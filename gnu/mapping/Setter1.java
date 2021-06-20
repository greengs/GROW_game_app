package gnu.mapping;

public class Setter1 extends Setter {
  public Setter1(Procedure paramProcedure) {
    super(paramProcedure);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    this.getter.set1(paramObject1, paramObject2);
    return Values.empty;
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    int i = paramArrayOfObject.length;
    if (i != 2)
      throw new WrongArguments(this, i); 
    this.getter.set1(paramArrayOfObject[0], paramArrayOfObject[1]);
    return Values.empty;
  }
  
  public int numArgs() {
    return 8194;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Setter1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
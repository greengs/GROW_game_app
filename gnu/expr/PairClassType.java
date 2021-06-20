package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;

public class PairClassType extends ClassType {
  public ClassType instanceType;
  
  Object staticLink;
  
  public PairClassType() {}
  
  PairClassType(Class paramClass1, Class paramClass2) {
    super(paramClass1.getName());
    setExisting(true);
    this.reflectClass = paramClass1;
    Type.registerTypeForClass(paramClass1, (Type)this);
    this.instanceType = (ClassType)Type.make(paramClass2);
  }
  
  public static Object extractStaticLink(ClassType paramClassType) {
    return ((PairClassType)paramClassType).staticLink;
  }
  
  public static PairClassType make(Class paramClass1, Class paramClass2) {
    return new PairClassType(paramClass1, paramClass2);
  }
  
  public static PairClassType make(Class paramClass1, Class paramClass2, Object paramObject) {
    PairClassType pairClassType = new PairClassType(paramClass1, paramClass2);
    pairClassType.staticLink = paramObject;
    return pairClassType;
  }
  
  public Object getStaticLink() {
    return this.staticLink;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/PairClassType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
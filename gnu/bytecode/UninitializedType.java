package gnu.bytecode;

public class UninitializedType extends ObjectType {
  ClassType ctype;
  
  Label label;
  
  UninitializedType(ClassType paramClassType) {
    super(paramClassType.getName());
    setSignature(paramClassType.getSignature());
    this.ctype = paramClassType;
  }
  
  UninitializedType(ClassType paramClassType, Label paramLabel) {
    this(paramClassType);
    this.label = paramLabel;
  }
  
  static UninitializedType uninitializedThis(ClassType paramClassType) {
    return new UninitializedType(paramClassType);
  }
  
  public Type getImplementationType() {
    return this.ctype;
  }
  
  public String toString() {
    return "Uninitialized<" + this.ctype.getName() + '>';
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/UninitializedType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
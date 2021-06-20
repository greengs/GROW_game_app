package gnu.kawa.xml;

import gnu.bytecode.Type;
import gnu.kawa.reflect.OccurrenceType;

public class NodeSetType extends OccurrenceType {
  public NodeSetType(Type paramType) {
    super(paramType, 0, -1);
  }
  
  public static Type getInstance(Type paramType) {
    return (Type)new NodeSetType(paramType);
  }
  
  public String toString() {
    return super.toString() + "node-set";
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/NodeSetType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
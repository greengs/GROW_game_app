package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.expr.Declaration;
import gnu.mapping.Environment;
import gnu.mapping.Symbol;
import java.lang.reflect.Field;

public class StaticFieldLocation extends FieldLocation {
  public StaticFieldLocation(ClassType paramClassType, String paramString) {
    super((Object)null, paramClassType, paramString);
  }
  
  public StaticFieldLocation(String paramString1, String paramString2) {
    super((Object)null, ClassType.make(paramString1), paramString2);
  }
  
  public StaticFieldLocation(Field paramField) {
    super(null, paramField);
  }
  
  public static StaticFieldLocation define(Environment paramEnvironment, Symbol paramSymbol, Object paramObject, String paramString1, String paramString2) {
    StaticFieldLocation staticFieldLocation = new StaticFieldLocation(paramString1, paramString2);
    paramEnvironment.addLocation(paramSymbol, paramObject, staticFieldLocation);
    return staticFieldLocation;
  }
  
  public static StaticFieldLocation make(Declaration paramDeclaration) {
    Field field = paramDeclaration.field;
    StaticFieldLocation staticFieldLocation = new StaticFieldLocation(field.getDeclaringClass(), field.getName());
    staticFieldLocation.setDeclaration(paramDeclaration);
    return staticFieldLocation;
  }
  
  public static StaticFieldLocation make(String paramString1, String paramString2) {
    return new StaticFieldLocation(paramString1, paramString2);
  }
  
  public Object get(Object paramObject) {
    paramObject = super.get(paramObject);
    if (paramObject instanceof kawa.lang.Macro)
      getDeclaration(); 
    return paramObject;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/StaticFieldLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
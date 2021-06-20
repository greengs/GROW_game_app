package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.Consumer;
import gnu.mapping.HasNamedParts;
import gnu.mapping.HasSetter;
import gnu.mapping.MethodProc;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Symbol;
import gnu.mapping.Values;

public class GetNamedPart extends Procedure2 implements HasSetter {
  public static final String CAST_METHOD_NAME = "@";
  
  public static final String CLASSTYPE_FOR = "<>";
  
  public static final String INSTANCEOF_METHOD_NAME = "instance?";
  
  public static final GetNamedPart getNamedPart = new GetNamedPart();
  
  static {
    getNamedPart.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateGetNamedPart");
  }
  
  public static Object getMemberPart(Object paramObject, String paramString) throws Throwable {
    try {
      return SlotGet.field(paramObject, paramString);
    } catch (Throwable throwable) {
      MethodProc methodProc = ClassMethods.apply((ObjectType)ClassType.make(paramObject.getClass()), Compilation.mangleName(paramString), false, Language.getDefaultLanguage());
      if (methodProc != null)
        return new NamedPart(paramObject, paramString, 'M', methodProc); 
      throw new RuntimeException("no part '" + paramString + "' in " + paramObject);
    } 
  }
  
  public static Object getNamedPart(Object paramObject, Symbol paramSymbol) throws Throwable {
    String str = paramSymbol.getName();
    if (paramObject instanceof HasNamedParts)
      return ((HasNamedParts)paramObject).get(str); 
    Object object = paramObject;
    if (paramObject instanceof Class)
      object = Type.make((Class)paramObject); 
    if (object instanceof Package)
      try {
        paramObject = ((Package)object).getName();
        return ClassType.getContextClass(paramObject + '.' + str);
      } catch (Throwable throwable) {} 
    return (object instanceof Type) ? getTypePart((Type)object, str) : getMemberPart(object, paramSymbol.toString());
  }
  
  public static Object getTypePart(Type paramType, String paramString) throws Throwable {
    if (paramString.equals("<>"))
      return paramType; 
    if (paramType instanceof ObjectType) {
      if (paramString.equals("instance?"))
        return new NamedPart(paramType, paramString, 'I'); 
      if (paramString.equals("@"))
        return new NamedPart(paramType, paramString, 'C'); 
      if (paramString.equals("new"))
        return new NamedPart(paramType, paramString, 'N'); 
      if (paramString.equals(".length") || (paramString.length() > 1 && paramString.charAt(0) == '.' && paramType instanceof ClassType))
        return new NamedPart(paramType, paramString, 'D'); 
    } 
    if (paramType instanceof ClassType)
      try {
        return SlotGet.staticField(paramType, paramString);
      } catch (Throwable throwable) {
        return ClassMethods.apply((Procedure)ClassMethods.classMethods, paramType, paramString);
      }  
    return getMemberPart(paramType, paramString);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    if (paramObject1 instanceof Values) {
      paramObject1 = ((Values)paramObject1).getValues();
      Values values = new Values();
      for (int i = 0; i < paramObject1.length; i++)
        Values.writeValues(apply2(paramObject1[i], paramObject2), (Consumer)values); 
      return values.canonicalize();
    } 
    if (paramObject2 instanceof Symbol) {
      paramObject2 = paramObject2;
      return getNamedPart(paramObject1, (Symbol)paramObject2);
    } 
    paramObject2 = Namespace.EmptyNamespace.getSymbol(paramObject2.toString().intern());
    return getNamedPart(paramObject1, (Symbol)paramObject2);
  }
  
  public Procedure getSetter() {
    return (Procedure)SetNamedPart.setNamedPart;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/GetNamedPart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
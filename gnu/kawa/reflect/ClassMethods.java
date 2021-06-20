package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.GenericProc;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import java.util.Vector;

public class ClassMethods extends Procedure2 {
  public static final ClassMethods classMethods = new ClassMethods();
  
  static {
    classMethods.setName("class-methods");
  }
  
  public static MethodProc apply(ObjectType paramObjectType, String paramString, char paramChar, Language paramLanguage) {
    PrimProcedure primProcedure;
    PrimProcedure[] arrayOfPrimProcedure = getMethods(paramObjectType, paramString, paramChar, (ClassType)null, paramLanguage);
    GenericProc genericProc = null;
    paramLanguage = null;
    int i = 0;
    while (i < arrayOfPrimProcedure.length) {
      PrimProcedure primProcedure1 = arrayOfPrimProcedure[i];
      GenericProc genericProc1 = genericProc;
      if (paramLanguage != null) {
        genericProc1 = genericProc;
        if (genericProc == null) {
          genericProc1 = new GenericProc();
          genericProc1.add((MethodProc)paramLanguage);
        } 
      } 
      primProcedure = primProcedure1;
      if (genericProc1 != null)
        genericProc1.add((MethodProc)primProcedure); 
      i++;
      genericProc = genericProc1;
    } 
    if (genericProc != null) {
      genericProc.setName(paramObjectType.getName() + "." + paramString);
      return (MethodProc)genericProc;
    } 
    return (MethodProc)primProcedure;
  }
  
  public static MethodProc apply(Procedure paramProcedure, Object paramObject1, Object paramObject2) {
    Object object1;
    Object object2 = paramObject1;
    if (paramObject1 instanceof Class)
      object2 = Type.make((Class)paramObject1); 
    if (object2 instanceof ClassType) {
      paramObject1 = object2;
    } else if (object2 instanceof String || object2 instanceof gnu.lists.FString || object2 instanceof Symbol) {
      paramObject1 = ClassType.make(object2.toString());
    } else {
      throw new WrongType(paramProcedure, 0, null);
    } 
    if (paramObject2 instanceof String || paramObject2 instanceof gnu.lists.FString || paramObject2 instanceof Symbol) {
      paramObject2 = paramObject2.toString();
      object1 = paramObject2;
      if (!"<init>".equals(paramObject2))
        object1 = Compilation.mangleName((String)paramObject2); 
      paramObject2 = apply((ObjectType)paramObject1, (String)object1, false, Language.getDefaultLanguage());
      if (paramObject2 == null)
        throw new RuntimeException("no applicable method named `" + object1 + "' in " + paramObject1.getName()); 
    } else {
      throw new WrongType(object1, 1, null);
    } 
    return (MethodProc)paramObject2;
  }
  
  static String checkName(Expression paramExpression) {
    Object object;
    String str2 = null;
    String str1 = str2;
    if (paramExpression instanceof QuoteExp) {
      object = ((QuoteExp)paramExpression).getValue();
      if (object instanceof gnu.lists.FString || object instanceof String)
        return object.toString(); 
    } else {
      return str1;
    } 
    str1 = str2;
    return (object instanceof Symbol) ? ((Symbol)object).getName() : str1;
  }
  
  static String checkName(Expression paramExpression, boolean paramBoolean) {
    Object object;
    String str2 = null;
    String str1 = str2;
    if (paramExpression instanceof QuoteExp) {
      object = ((QuoteExp)paramExpression).getValue();
      if (object instanceof gnu.lists.FString || object instanceof String) {
        object = object.toString();
      } else {
        str1 = str2;
        if (object instanceof Symbol) {
          object = ((Symbol)object).getName();
        } else {
          return str1;
        } 
      } 
      if (Compilation.isValidJavaName((String)object))
        return (String)object; 
    } else {
      return str1;
    } 
    return Compilation.mangleName((String)object, paramBoolean);
  }
  
  public static PrimProcedure[] getMethods(ObjectType paramObjectType, String paramString, char paramChar, ClassType paramClassType, Language paramLanguage) {
    ClassType classType1;
    boolean bool1;
    ClassType classType2;
    ObjectType objectType = paramObjectType;
    if (paramObjectType == Type.tostring_type)
      classType2 = Type.string_type; 
    if (paramChar == 'P') {
      paramObjectType = null;
    } else {
      classType1 = classType2;
    } 
    MethodFilter methodFilter = new MethodFilter(paramString, 0, 0, paramClassType, (ObjectType)classType1);
    if (paramChar == 'P' || "<init>".equals(paramString)) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    Vector<Method> vector = new Vector();
    if (bool1) {
      i = 0;
    } else {
      i = 2;
    } 
    classType2.getMethods(methodFilter, i, vector);
    if (!bool1 && (!(classType2 instanceof ClassType) || classType2.isInterface()))
      Type.pointer_type.getMethods(methodFilter, 0, vector); 
    if (bool1) {
      i = vector.size();
    } else {
      i = removeRedundantMethods(vector);
    } 
    PrimProcedure[] arrayOfPrimProcedure = new PrimProcedure[i];
    boolean bool2 = false;
    int j = i;
    int i = bool2;
    while (true) {
      if (--j >= 0) {
        Method method2 = vector.elementAt(j);
        Method method1 = method2;
        if (!bool1) {
          method1 = method2;
          if (method2.getDeclaringClass() != classType2) {
            Type type = classType2.getImplementationType();
            method1 = method2;
            if (type instanceof ClassType)
              method1 = new Method(method2, (ClassType)type); 
          } 
        } 
        arrayOfPrimProcedure[i] = new PrimProcedure(method1, paramChar, paramLanguage);
        i++;
        continue;
      } 
      return arrayOfPrimProcedure;
    } 
  }
  
  private static int removeRedundantMethods(Vector<Method> paramVector) {
    int i = paramVector.size();
    for (int j = 1; j < i; j++) {
      Method method = paramVector.elementAt(j);
      ClassType classType = method.getDeclaringClass();
      Type[] arrayOfType = method.getParameterTypes();
      int m = arrayOfType.length;
      for (int k = 0; k < j; k++) {
        Method method1 = paramVector.elementAt(k);
        Type[] arrayOfType1 = method1.getParameterTypes();
        if (m == arrayOfType1.length) {
          int i1;
          int n = m;
          while (true) {
            i1 = n - 1;
            if (i1 >= 0) {
              n = i1;
              if (arrayOfType[i1] != arrayOfType1[i1])
                break; 
              continue;
            } 
            break;
          } 
          if (i1 < 0) {
            if (classType.isSubtype((Type)method1.getDeclaringClass())) {
              paramVector.setElementAt(method, k);
              paramVector.setElementAt(paramVector.elementAt(i - 1), j);
              i--;
              continue;
            } 
            continue;
          } 
        } 
      } 
    } 
    return i;
  }
  
  public static int selectApplicable(PrimProcedure[] paramArrayOfPrimProcedure, int paramInt) {
    int i = paramArrayOfPrimProcedure.length;
    int m = 0;
    int n = 0;
    int j = 0;
    int k = 0;
    while (k < i) {
      int i1 = paramArrayOfPrimProcedure[k].numArgs();
      int i2 = Procedure.minArgs(i1);
      int i3 = Procedure.maxArgs(i1);
      i1 = 0;
      if (paramInt < i2) {
        n++;
      } else if (paramInt > i3 && i3 >= 0) {
        m++;
      } else {
        i1 = 1;
      } 
      if (i1 != 0) {
        j++;
        k++;
        continue;
      } 
      PrimProcedure primProcedure = paramArrayOfPrimProcedure[i - 1];
      paramArrayOfPrimProcedure[i - 1] = paramArrayOfPrimProcedure[k];
      paramArrayOfPrimProcedure[k] = primProcedure;
      i--;
    } 
    return (j > 0) ? j : ((n > 0) ? -983040 : ((m > 0) ? -917504 : 0));
  }
  
  public static long selectApplicable(PrimProcedure[] paramArrayOfPrimProcedure, Type[] paramArrayOfType) {
    int k = paramArrayOfPrimProcedure.length;
    int m = 0;
    int j = 0;
    for (int i = 0; i < k; i++) {
      int n = paramArrayOfPrimProcedure[i].isApplicable(paramArrayOfType);
      if (n < 0) {
        PrimProcedure primProcedure = paramArrayOfPrimProcedure[k - 1];
        paramArrayOfPrimProcedure[k - 1] = paramArrayOfPrimProcedure[i];
        paramArrayOfPrimProcedure[i] = primProcedure;
        k--;
        continue;
      } 
      if (n > 0) {
        PrimProcedure primProcedure = paramArrayOfPrimProcedure[m];
        paramArrayOfPrimProcedure[m] = paramArrayOfPrimProcedure[i];
        paramArrayOfPrimProcedure[i] = primProcedure;
        m++;
        i++;
        continue;
      } 
      j++;
    } 
    return (m << 32L) + j;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    return apply((Procedure)this, paramObject1, paramObject2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/ClassMethods.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
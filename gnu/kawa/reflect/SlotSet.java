package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.CheckedTarget;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure3;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kawa.standard.Scheme;

public class SlotSet extends Procedure3 implements Inlineable {
  public static final SlotSet set$Mnfield$Ex = new SlotSet("set-field!", false);
  
  public static final SlotSet set$Mnstatic$Mnfield$Ex = new SlotSet("set-static-field!", true);
  
  public static final SlotSet setFieldReturnObject = new SlotSet("set-field-return-object!", false);
  
  static final Type[] type1Array;
  
  boolean isStatic;
  
  boolean returnSelf;
  
  static {
    setFieldReturnObject.returnSelf = true;
    type1Array = new Type[1];
  }
  
  public SlotSet(String paramString, boolean paramBoolean) {
    super(paramString);
    this.isStatic = paramBoolean;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplySlotSet");
  }
  
  public static void apply(boolean paramBoolean, Object paramObject1, Object paramObject2, Object paramObject3) {
    Class clazz;
    String str1;
    String str2;
    Language language = Language.getDefaultLanguage();
    boolean bool = false;
    if (paramObject2 instanceof String || paramObject2 instanceof gnu.lists.FString || paramObject2 instanceof gnu.mapping.Symbol) {
      str2 = paramObject2.toString();
      str1 = Compilation.mangleNameIfNeeded(str2);
      if (paramBoolean) {
        clazz = SlotGet.coerceToClass(paramObject1);
      } else {
        clazz = paramObject1.getClass();
      } 
    } else {
      str2 = ((Member)paramObject2).getName();
      str1 = str2;
      clazz = null;
    } 
    try {
      Field field;
      if (paramObject2 instanceof Field) {
        field = ((Field)paramObject2).getReflectField();
      } else {
        field = clazz.getField(str1);
      } 
      field.set(paramObject1, language.coerceFromObject(field.getType(), paramObject3));
      return;
    } catch (NoSuchFieldException noSuchFieldException) {
    
    } catch (IllegalAccessException illegalAccessException) {
      bool = true;
    } 
    try {
      Method method;
      boolean bool1 = paramObject2 instanceof Method;
      if (bool1) {
        paramObject2 = str1;
      } else {
        paramObject2 = ClassExp.slotToMethodName("set", str2);
      } 
      paramBoolean = bool1;
      if (bool1) {
        boolean bool2 = paramObject2.startsWith("set");
        paramBoolean = bool1;
        if (!bool2)
          paramBoolean = false; 
      } 
      if (paramBoolean) {
        try {
          str1 = "get" + paramObject2.substring(3);
          method = clazz.getMethod(str1, SlotGet.noClasses);
        } catch (Exception exception) {}
      } else {
        str1 = ClassExp.slotToMethodName("get", str2);
        method = clazz.getMethod(str1, SlotGet.noClasses);
      } 
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = method.getReturnType();
      clazz.getMethod((String)paramObject2, arrayOfClass).invoke(paramObject1, new Object[] { language.coerceFromObject(arrayOfClass[0], paramObject3) });
      return;
    } catch (InvocationTargetException invocationTargetException) {
      throw WrappedException.wrapIfNeeded(invocationTargetException.getTargetException());
    } catch (IllegalAccessException illegalAccessException) {
      bool = true;
    } catch (NoSuchMethodException noSuchMethodException) {}
    if (bool)
      throw new RuntimeException("illegal access for field " + str2); 
    throw new RuntimeException("no such field " + str2 + " in " + clazz.getName());
  }
  
  static void compileSet(Procedure paramProcedure, ObjectType paramObjectType, Expression paramExpression, Object paramObject, Compilation paramCompilation) {
    boolean bool;
    Type type;
    CodeAttr codeAttr = paramCompilation.getCode();
    Language language = paramCompilation.getLanguage();
    if (paramProcedure instanceof SlotSet && ((SlotSet)paramProcedure).isStatic) {
      bool = true;
    } else {
      bool = false;
    } 
    if (paramObject instanceof Field) {
      paramObject = paramObject;
      boolean bool1 = paramObject.getStaticFlag();
      type = language.getLangTypeFor(paramObject.getType());
      if (bool && !bool1)
        paramCompilation.error('e', "cannot access non-static field `" + paramObject.getName() + "' using `" + paramProcedure.getName() + '\''); 
      paramExpression.compile(paramCompilation, CheckedTarget.getInstance(type));
      if (bool1) {
        codeAttr.emitPutStatic((Field)paramObject);
        return;
      } 
      codeAttr.emitPutField((Field)paramObject);
      return;
    } 
    if (paramObject instanceof Method) {
      paramObject = paramObject;
      boolean bool1 = paramObject.getStaticFlag();
      if (bool && !bool1)
        paramCompilation.error('e', "cannot call non-static getter method `" + paramObject.getName() + "' using `" + paramProcedure.getName() + '\''); 
      paramExpression.compile(paramCompilation, CheckedTarget.getInstance(type.getLangTypeFor(paramObject.getParameterTypes()[0])));
      if (bool1) {
        codeAttr.emitInvokeStatic((Method)paramObject);
      } else {
        codeAttr.emitInvoke((Method)paramObject);
      } 
      if (!paramObject.getReturnType().isVoid()) {
        codeAttr.emitPop(1);
        return;
      } 
    } 
  }
  
  public static Member lookupMember(ObjectType paramObjectType, String paramString, ClassType paramClassType) {
    Field field = paramObjectType.getField(Compilation.mangleNameIfNeeded(paramString), -1);
    if (field != null) {
      ClassType classType = paramClassType;
      if (paramClassType == null)
        classType = Type.pointer_type; 
      if (classType.isAccessible((Member)field, paramObjectType))
        return (Member)field; 
    } 
    Method method = paramObjectType.getMethod(ClassExp.slotToMethodName("set", paramString), type1Array);
    return (Member)((method != null) ? method : field);
  }
  
  public static void setField(Object paramObject1, String paramString, Object paramObject2) {
    apply(false, paramObject1, paramString, paramObject2);
  }
  
  public static void setStaticField(Object paramObject1, String paramString, Object paramObject2) {
    apply(true, paramObject1, paramString, paramObject2);
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3) {
    apply(this.isStatic, paramObject1, paramObject2, paramObject3);
    return this.returnSelf ? paramObject1 : Values.empty;
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Target target;
    Type type;
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int i = arrayOfExpression.length;
    if (i != 3) {
      String str;
      if (i < 3) {
        str = "too few";
      } else {
        str = "too many";
      } 
      paramCompilation.error('e', str + " arguments to `" + getName() + '\'');
      paramCompilation.compileConstant(null, paramTarget);
      return;
    } 
    Expression expression1 = arrayOfExpression[0];
    Expression expression2 = arrayOfExpression[1];
    Expression expression3 = arrayOfExpression[2];
    if (this.isStatic) {
      type = Scheme.exp2Type(expression1);
    } else {
      type = expression1.getType();
    } 
    expression1 = null;
    if (type instanceof ObjectType && expression2 instanceof QuoteExp) {
      Member member;
      ClassType classType;
      Object object = ((QuoteExp)expression2).getValue();
      ObjectType objectType = (ObjectType)type;
      if (paramCompilation.curClass != null) {
        classType = paramCompilation.curClass;
      } else {
        classType = paramCompilation.mainClass;
      } 
      if (object instanceof String || object instanceof gnu.lists.FString || object instanceof gnu.mapping.Symbol) {
        String str = object.toString();
        Member member1 = lookupMember(objectType, str, classType);
        object = str;
        member = member1;
        if (member1 == null) {
          object = str;
          member = member1;
          if (type != Type.pointer_type) {
            object = str;
            member = member1;
            if (paramCompilation.warnUnknownMember()) {
              paramCompilation.error('w', "no slot `" + str + "' in " + objectType.getName());
              member = member1;
              object = str;
            } 
          } 
        } 
      } else if (object instanceof Member) {
        member = (Member)object;
        object = member.getName();
      } else {
        object = null;
      } 
      if (member != null) {
        if ((member.getModifiers() & 0x8) != 0) {
          i = 1;
        } else {
          i = 0;
        } 
        if (classType != null && !classType.isAccessible(member, objectType))
          paramCompilation.error('e', "slot '" + object + "' in " + member.getDeclaringClass().getName() + " not accessible here"); 
        object = arrayOfExpression[0];
        if (i != 0) {
          target = Target.Ignore;
        } else {
          target = Target.pushValue((Type)objectType);
        } 
        object.compile(paramCompilation, target);
        if (this.returnSelf)
          paramCompilation.getCode().emitDup(objectType.getImplementationType()); 
        compileSet((Procedure)this, objectType, arrayOfExpression[2], member, paramCompilation);
        if (this.returnSelf) {
          paramTarget.compileFromStack(paramCompilation, (Type)objectType);
          return;
        } 
        paramCompilation.compileConstant(Values.empty, paramTarget);
        return;
      } 
    } 
    ApplyExp.compile((ApplyExp)target, paramCompilation, paramTarget);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/SlotSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
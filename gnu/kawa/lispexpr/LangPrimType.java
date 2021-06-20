package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.text.Char;

public class LangPrimType extends PrimType implements TypeValue {
  public static final PrimType byteType = Type.byteType;
  
  public static final LangPrimType charType;
  
  public static final PrimType doubleType;
  
  public static final PrimType floatType;
  
  public static final PrimType intType;
  
  public static final PrimType longType;
  
  public static final PrimType shortType = Type.shortType;
  
  public static final LangPrimType voidType;
  
  PrimType implementationType;
  
  Language language;
  
  static {
    intType = Type.intType;
    longType = Type.longType;
    floatType = Type.floatType;
    doubleType = Type.doubleType;
    charType = new LangPrimType(Type.charType);
    voidType = new LangPrimType(Type.voidType);
  }
  
  public LangPrimType(PrimType paramPrimType) {
    super(paramPrimType);
    this.implementationType = paramPrimType;
  }
  
  public LangPrimType(PrimType paramPrimType, Language paramLanguage) {
    super(paramPrimType);
    this.language = paramLanguage;
    this.implementationType = paramPrimType;
  }
  
  public LangPrimType(String paramString1, String paramString2, int paramInt, Class paramClass) {
    super(paramString1, paramString2, paramInt, paramClass);
  }
  
  public LangPrimType(String paramString1, String paramString2, int paramInt, Class paramClass, Language paramLanguage) {
    this(paramString1, paramString2, paramInt, paramClass);
    this.implementationType = Type.signatureToPrimitive(paramString2.charAt(0));
    this.language = paramLanguage;
  }
  
  public char charValue(Object paramObject) {
    return (paramObject instanceof Character) ? ((Character)paramObject).charValue() : ((Char)paramObject).charValue();
  }
  
  public Object coerceFromObject(Object paramObject) {
    if (paramObject.getClass() == this.reflectClass)
      return paramObject; 
    switch (getSignature().charAt(0)) {
      default:
        return super.coerceFromObject(paramObject);
      case 'Z':
        return this.language.isTrue(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 'C':
        return new Character(((Char)paramObject).charValue());
      case 'V':
        break;
    } 
    return Values.empty;
  }
  
  public Object coerceToObject(Object paramObject) {
    Object object;
    switch (getSignature().charAt(0)) {
      default:
        return super.coerceToObject(paramObject);
      case 'Z':
        return this.language.booleanObject(((Boolean)paramObject).booleanValue());
      case 'C':
        object = paramObject;
        return !(paramObject instanceof Char) ? Char.make(((Character)paramObject).charValue()) : object;
      case 'V':
        break;
    } 
    return Values.empty;
  }
  
  public int compare(Type paramType) {
    char c = getSignature().charAt(0);
    if (paramType instanceof PrimType) {
      char c1 = paramType.getSignature().charAt(0);
      if (c == c1)
        return 0; 
      if (c == 'V')
        return 1; 
      if (c1 == 'V' || c1 == 'Z')
        return -1; 
    } 
    return (c == 'V' || c == 'Z') ? 1 : ((c == 'C' && paramType.getName().equals("gnu.text.Char")) ? -1 : ((paramType instanceof LangObjType) ? swappedCompareResult(paramType.compare((Type)this)) : super.compare(paramType)));
  }
  
  public Expression convertValue(Expression paramExpression) {
    return null;
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr) {
    switch (getSignature().charAt(0)) {
      default:
        super.emitCoerceFromObject(paramCodeAttr);
        return;
      case 'Z':
        this.language.emitCoerceToBoolean(paramCodeAttr);
        return;
      case 'C':
        break;
    } 
    ClassType classType = ClassType.make("gnu.text.Char");
    Method method = classType.getDeclaredMethod("charValue", 0);
    paramCodeAttr.emitCheckcast((Type)classType);
    paramCodeAttr.emitInvokeVirtual(method);
  }
  
  public void emitCoerceToObject(CodeAttr paramCodeAttr) {
    switch (getSignature().charAt(0)) {
      default:
        super.emitCoerceToObject(paramCodeAttr);
        if (false)
          paramCodeAttr.emitInvokeStatic(ClassType.make(null).getDeclaredMethod("make", new Type[] { null })); 
        return;
      case 'Z':
        paramCodeAttr.emitIfIntNotZero();
        this.language.emitPushBoolean(true, paramCodeAttr);
        paramCodeAttr.emitElse();
        this.language.emitPushBoolean(false, paramCodeAttr);
        paramCodeAttr.emitFi();
        if (false)
          paramCodeAttr.emitInvokeStatic(ClassType.make(null).getDeclaredMethod("make", new Type[] { null })); 
        return;
      case 'C':
        break;
    } 
    paramCodeAttr.emitInvokeStatic(ClassType.make("gnu.text.Char").getDeclaredMethod("make", 1));
    if (false)
      paramCodeAttr.emitInvokeStatic(ClassType.make(null).getDeclaredMethod("make", new Type[] { null })); 
  }
  
  public void emitIsInstance(CodeAttr paramCodeAttr) {
    switch (getSignature().charAt(0)) {
      default:
        super.emitIsInstance(paramCodeAttr);
        return;
      case 'Z':
        paramCodeAttr.emitPop(1);
        paramCodeAttr.emitPushInt(1);
        return;
      case 'C':
        break;
    } 
    paramCodeAttr.emitInstanceof((Type)ClassType.make("gnu.text.Char"));
  }
  
  public void emitIsInstance(Variable paramVariable, Compilation paramCompilation, Target paramTarget) {
    InstanceOf.emitIsInstance(this, paramVariable, paramCompilation, paramTarget);
  }
  
  public void emitTestIf(Variable paramVariable, Declaration paramDeclaration, Compilation paramCompilation) {
    getSignature().charAt(0);
    CodeAttr codeAttr = paramCompilation.getCode();
    if (paramVariable != null)
      codeAttr.emitLoad(paramVariable); 
    if (paramDeclaration != null) {
      codeAttr.emitDup();
      paramDeclaration.compileStore(paramCompilation);
    } 
    emitIsInstance(codeAttr);
    codeAttr.emitIfIntNotZero();
  }
  
  public Procedure getConstructor() {
    return null;
  }
  
  public Type getImplementationType() {
    return (Type)this.implementationType;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/LangPrimType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
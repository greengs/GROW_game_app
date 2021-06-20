package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.Consumer;
import gnu.mapping.Procedure;
import gnu.math.DateTime;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.RealNum;
import gnu.math.Unit;
import gnu.text.Printable;
import gnu.text.URIPath;
import gnu.xml.TextUtils;
import java.math.BigDecimal;

public class XDataType extends Type implements TypeValue {
  public static final int ANY_ATOMIC_TYPE_CODE = 3;
  
  public static final int ANY_SIMPLE_TYPE_CODE = 2;
  
  public static final int ANY_URI_TYPE_CODE = 33;
  
  public static final int BASE64_BINARY_TYPE_CODE = 34;
  
  public static final int BOOLEAN_TYPE_CODE = 31;
  
  public static final int BYTE_TYPE_CODE = 11;
  
  public static final int DATE_TIME_TYPE_CODE = 20;
  
  public static final int DATE_TYPE_CODE = 21;
  
  public static final int DAY_TIME_DURATION_TYPE_CODE = 30;
  
  public static final BigDecimal DECIMAL_ONE;
  
  public static final int DECIMAL_TYPE_CODE = 4;
  
  public static final Double DOUBLE_ONE;
  
  public static final int DOUBLE_TYPE_CODE = 19;
  
  public static final Double DOUBLE_ZERO;
  
  public static final int DURATION_TYPE_CODE = 28;
  
  public static final int ENTITY_TYPE_CODE = 47;
  
  public static final Float FLOAT_ONE;
  
  public static final int FLOAT_TYPE_CODE = 18;
  
  public static final Float FLOAT_ZERO;
  
  public static final int G_DAY_TYPE_CODE = 26;
  
  public static final int G_MONTH_DAY_TYPE_CODE = 25;
  
  public static final int G_MONTH_TYPE_CODE = 27;
  
  public static final int G_YEAR_MONTH_TYPE_CODE = 23;
  
  public static final int G_YEAR_TYPE_CODE = 24;
  
  public static final int HEX_BINARY_TYPE_CODE = 35;
  
  public static final int IDREF_TYPE_CODE = 46;
  
  public static final int ID_TYPE_CODE = 45;
  
  public static final int INTEGER_TYPE_CODE = 5;
  
  public static final int INT_TYPE_CODE = 9;
  
  public static final int LANGUAGE_TYPE_CODE = 41;
  
  public static final int LONG_TYPE_CODE = 8;
  
  public static final int NAME_TYPE_CODE = 43;
  
  public static final int NCNAME_TYPE_CODE = 44;
  
  public static final int NEGATIVE_INTEGER_TYPE_CODE = 7;
  
  public static final int NMTOKEN_TYPE_CODE = 42;
  
  public static final int NONNEGATIVE_INTEGER_TYPE_CODE = 12;
  
  public static final int NON_POSITIVE_INTEGER_TYPE_CODE = 6;
  
  public static final int NORMALIZED_STRING_TYPE_CODE = 39;
  
  public static final int NOTATION_TYPE_CODE = 36;
  
  public static final XDataType NotationType;
  
  public static final int POSITIVE_INTEGER_TYPE_CODE = 17;
  
  public static final int QNAME_TYPE_CODE = 32;
  
  public static final int SHORT_TYPE_CODE = 10;
  
  public static final int STRING_TYPE_CODE = 38;
  
  public static final int TIME_TYPE_CODE = 22;
  
  public static final int TOKEN_TYPE_CODE = 40;
  
  public static final int UNSIGNED_BYTE_TYPE_CODE = 16;
  
  public static final int UNSIGNED_INT_TYPE_CODE = 14;
  
  public static final int UNSIGNED_LONG_TYPE_CODE = 13;
  
  public static final int UNSIGNED_SHORT_TYPE_CODE = 15;
  
  public static final int UNTYPED_ATOMIC_TYPE_CODE = 37;
  
  public static final int UNTYPED_TYPE_CODE = 48;
  
  public static final int YEAR_MONTH_DURATION_TYPE_CODE = 29;
  
  public static final XDataType anyAtomicType;
  
  public static final XDataType anySimpleType = new XDataType("anySimpleType", (Type)Type.objectType, 2);
  
  public static final XDataType anyURIType;
  
  public static final XDataType base64BinaryType;
  
  public static final XDataType booleanType;
  
  public static final XDataType dayTimeDurationType;
  
  public static final XDataType decimalType;
  
  public static final XDataType doubleType;
  
  public static final XDataType durationType;
  
  public static final XDataType floatType;
  
  public static final XDataType hexBinaryType;
  
  public static final XDataType stringStringType;
  
  public static final XDataType stringType;
  
  public static final XDataType untypedAtomicType;
  
  public static final XDataType untypedType;
  
  public static final XDataType yearMonthDurationType;
  
  XDataType baseType;
  
  Type implementationType;
  
  Object name;
  
  int typeCode;
  
  static {
    anyAtomicType = new XDataType("anyAtomicType", (Type)Type.objectType, 3);
    stringType = new XDataType("string", (Type)ClassType.make("java.lang.CharSequence"), 38);
    stringStringType = new XDataType("String", (Type)ClassType.make("java.lang.String"), 38);
    untypedAtomicType = new XDataType("string", (Type)ClassType.make("gnu.kawa.xml.UntypedAtomic"), 37);
    base64BinaryType = new XDataType("base64Binary", (Type)ClassType.make("gnu.kawa.xml.Base64Binary"), 34);
    hexBinaryType = new XDataType("hexBinary", (Type)ClassType.make("gnu.kawa.xml.HexBinary"), 35);
    booleanType = new XDataType("boolean", (Type)Type.booleanType, 31);
    anyURIType = new XDataType("anyURI", (Type)ClassType.make("gnu.text.Path"), 33);
    NotationType = new XDataType("NOTATION", (Type)ClassType.make("gnu.kawa.xml.Notation"), 36);
    decimalType = new XDataType("decimal", (Type)ClassType.make("java.lang.Number"), 4);
    floatType = new XDataType("float", (Type)ClassType.make("java.lang.Float"), 18);
    doubleType = new XDataType("double", (Type)ClassType.make("java.lang.Double"), 19);
    durationType = new XDataType("duration", (Type)ClassType.make("gnu.math.Duration"), 28);
    yearMonthDurationType = new XDataType("yearMonthDuration", (Type)ClassType.make("gnu.math.Duration"), 29);
    dayTimeDurationType = new XDataType("dayTimeDuration", (Type)ClassType.make("gnu.math.Duration"), 30);
    untypedType = new XDataType("untyped", (Type)Type.objectType, 48);
    DOUBLE_ZERO = makeDouble(0.0D);
    DOUBLE_ONE = makeDouble(1.0D);
    FLOAT_ZERO = makeFloat(0.0F);
    FLOAT_ONE = makeFloat(1.0F);
    DECIMAL_ONE = BigDecimal.valueOf(1L);
  }
  
  public XDataType(Object paramObject, Type paramType, int paramInt) {
    super(paramType);
    this.name = paramObject;
    if (paramObject != null)
      setName(paramObject.toString()); 
    this.implementationType = paramType;
    this.typeCode = paramInt;
  }
  
  public static Double makeDouble(double paramDouble) {
    return Double.valueOf(paramDouble);
  }
  
  public static Float makeFloat(float paramFloat) {
    return Float.valueOf(paramFloat);
  }
  
  public Object cast(Object paramObject) {
    paramObject = KNode.atomicValue(paramObject);
    if (paramObject instanceof UntypedAtomic)
      return (this.typeCode != 37) ? valueOf(paramObject.toString()) : paramObject; 
    if (paramObject instanceof String)
      return valueOf(paramObject.toString()); 
    switch (this.typeCode) {
      default:
        return coerceFromObject(paramObject);
      case 38:
        return TextUtils.asString(paramObject);
      case 37:
        return new UntypedAtomic(TextUtils.stringValue(paramObject));
      case 33:
        return URIPath.makeURI(paramObject);
      case 31:
        if (paramObject instanceof Boolean)
          return ((Boolean)paramObject).booleanValue() ? Boolean.TRUE : Boolean.FALSE; 
        if (paramObject instanceof Number) {
          double d = ((Number)paramObject).doubleValue();
          return (d == 0.0D || Double.isNaN(d)) ? Boolean.FALSE : Boolean.TRUE;
        } 
      case 4:
        if (!(paramObject instanceof BigDecimal)) {
          if (paramObject instanceof RealNum)
            return ((RealNum)paramObject).asBigDecimal(); 
          if (paramObject instanceof Float || paramObject instanceof Double)
            return BigDecimal.valueOf(((Number)paramObject).doubleValue()); 
          if (paramObject instanceof Boolean) {
            if (((Boolean)paramObject).booleanValue()) {
              paramObject = IntNum.one();
              return cast(paramObject);
            } 
            paramObject = IntNum.zero();
            return cast(paramObject);
          } 
        } 
        return paramObject;
      case 18:
        if (!(paramObject instanceof Float)) {
          if (paramObject instanceof Number)
            return makeFloat(((Number)paramObject).floatValue()); 
          if (paramObject instanceof Boolean)
            return ((Boolean)paramObject).booleanValue() ? FLOAT_ONE : FLOAT_ZERO; 
        } 
        return paramObject;
      case 19:
        if (!(paramObject instanceof Double)) {
          if (paramObject instanceof Number)
            return makeDouble(((Number)paramObject).doubleValue()); 
          if (paramObject instanceof Boolean)
            return ((Boolean)paramObject).booleanValue() ? DOUBLE_ONE : DOUBLE_ZERO; 
        } 
        return paramObject;
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
        if (paramObject instanceof DateTime) {
          int i = XTimeType.components(((XTimeType)this).typeCode);
          paramObject = paramObject;
          int j = paramObject.components();
          if (i == j || (j & 0xE) == 14)
            return paramObject.cast(i); 
          throw new ClassCastException();
        } 
      case 20:
      case 21:
      case 22:
        if (paramObject instanceof DateTime) {
          int i = XTimeType.components(((XTimeType)this).typeCode);
          return ((DateTime)paramObject).cast(i);
        } 
      case 28:
        return castToDuration(paramObject, (Unit)Unit.duration);
      case 29:
        return castToDuration(paramObject, (Unit)Unit.month);
      case 30:
        return castToDuration(paramObject, (Unit)Unit.second);
      case 34:
        if (paramObject instanceof BinaryObject)
          return new Base64Binary(((BinaryObject)paramObject).getBytes()); 
        break;
      case 35:
        break;
    } 
    if (paramObject instanceof BinaryObject)
      return new HexBinary(((BinaryObject)paramObject).getBytes()); 
  }
  
  Duration castToDuration(Object paramObject, Unit paramUnit) {
    if (paramObject instanceof Duration) {
      paramObject = paramObject;
      if (paramObject.unit() == paramUnit)
        return (Duration)paramObject; 
      int i = paramObject.getTotalMonths();
      long l = paramObject.getTotalSeconds();
      int j = paramObject.getNanoSecondsOnly();
      if (paramUnit == Unit.second)
        i = 0; 
      if (paramUnit == Unit.month) {
        l = 0L;
        j = 0;
      } 
      return Duration.make(i, l, j, paramUnit);
    } 
    return (Duration)coerceFromObject(paramObject);
  }
  
  public boolean castable(Object paramObject) {
    try {
      cast(paramObject);
      return true;
    } catch (Throwable throwable) {
      return false;
    } 
  }
  
  public Object coerceFromObject(Object paramObject) {
    if (!isInstance(paramObject))
      throw new ClassCastException("cannot cast " + paramObject + " to " + this.name); 
    return paramObject;
  }
  
  public int compare(Type paramType) {
    return (this == paramType || (this == stringStringType && paramType == stringType) || (this == stringType && paramType == stringStringType)) ? 0 : this.implementationType.compare(paramType);
  }
  
  public Expression convertValue(Expression paramExpression) {
    return null;
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr) {
    Compilation.getCurrent().compileConstant(this, Target.pushObject);
    Method method = ClassType.make("gnu.kawa.xml.XDataType").getDeclaredMethod("coerceFromObject", 1);
    paramCodeAttr.emitSwap();
    paramCodeAttr.emitInvokeVirtual(method);
    this.implementationType.emitCoerceFromObject(paramCodeAttr);
  }
  
  public void emitCoerceToObject(CodeAttr paramCodeAttr) {
    if (this.typeCode == 31) {
      this.implementationType.emitCoerceToObject(paramCodeAttr);
      return;
    } 
    super.emitCoerceToObject(paramCodeAttr);
  }
  
  public void emitIsInstance(Variable paramVariable, Compilation paramCompilation, Target paramTarget) {
    InstanceOf.emitIsInstance(this, paramVariable, paramCompilation, paramTarget);
  }
  
  public void emitTestIf(Variable paramVariable, Declaration paramDeclaration, Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    if (this.typeCode == 31) {
      if (paramVariable != null)
        codeAttr.emitLoad(paramVariable); 
      Type.javalangBooleanType.emitIsInstance(codeAttr);
      codeAttr.emitIfIntNotZero();
      if (paramDeclaration != null) {
        codeAttr.emitLoad(paramVariable);
        Type.booleanType.emitCoerceFromObject(codeAttr);
        paramDeclaration.compileStore(paramCompilation);
      } 
      return;
    } 
    paramCompilation.compileConstant(this, Target.pushObject);
    if (paramVariable == null) {
      codeAttr.emitSwap();
    } else {
      codeAttr.emitLoad(paramVariable);
    } 
    codeAttr.emitInvokeVirtual(Compilation.typeType.getDeclaredMethod("isInstance", 1));
    codeAttr.emitIfIntNotZero();
    if (paramDeclaration != null) {
      codeAttr.emitLoad(paramVariable);
      emitCoerceFromObject(codeAttr);
      paramDeclaration.compileStore(paramCompilation);
      return;
    } 
  }
  
  public Procedure getConstructor() {
    return null;
  }
  
  public Type getImplementationType() {
    return this.implementationType;
  }
  
  public Class getReflectClass() {
    return this.implementationType.getReflectClass();
  }
  
  public boolean isInstance(Object paramObject) {
    boolean bool = false;
    boolean bool1 = true;
    null = bool1;
    switch (this.typeCode) {
      default:
        null = super.isInstance(paramObject);
      case 48:
        return null;
      case 2:
        if (!(paramObject instanceof gnu.lists.SeqPosition)) {
          null = bool1;
          if (paramObject instanceof Nodes)
            return false; 
        } 
        return false;
      case 3:
        if (!(paramObject instanceof gnu.mapping.Values)) {
          null = bool1;
          if (paramObject instanceof gnu.lists.SeqPosition)
            return false; 
        } 
        return false;
      case 38:
        return paramObject instanceof CharSequence;
      case 37:
        return paramObject instanceof UntypedAtomic;
      case 33:
        return paramObject instanceof gnu.text.Path;
      case 31:
        return paramObject instanceof Boolean;
      case 18:
        return paramObject instanceof Float;
      case 19:
        return paramObject instanceof Double;
      case 4:
        if (!(paramObject instanceof BigDecimal)) {
          null = bool;
          return (paramObject instanceof IntNum) ? true : null;
        } 
        return true;
      case 28:
        return paramObject instanceof Duration;
      case 29:
        if (paramObject instanceof Duration) {
          boolean bool2 = bool1;
          if (((Duration)paramObject).unit() != Unit.month)
            return false; 
        } 
        return false;
      case 30:
        break;
    } 
    if (paramObject instanceof Duration) {
      boolean bool2 = bool1;
      if (((Duration)paramObject).unit() != Unit.second)
        return false; 
    } 
    return false;
  }
  
  public void print(Object paramObject, Consumer paramConsumer) {
    if (paramObject instanceof Printable) {
      ((Printable)paramObject).print(paramConsumer);
      return;
    } 
    paramConsumer.write(toString(paramObject));
  }
  
  public String toString(Object paramObject) {
    return paramObject.toString();
  }
  
  public Object valueOf(String paramString) {
    int i;
    String str;
    switch (this.typeCode) {
      default:
        throw new RuntimeException("valueOf not implemented for " + this.name);
      case 38:
        return paramString;
      case 37:
        return new UntypedAtomic(paramString);
      case 33:
        return URIPath.makeURI(TextUtils.replaceWhitespace(paramString, true));
      case 31:
        paramString = paramString.trim();
        if (paramString.equals("true") || paramString.equals("1"))
          return Boolean.TRUE; 
        if (paramString.equals("false") || paramString.equals("0"))
          return Boolean.FALSE; 
        throw new IllegalArgumentException("not a valid boolean: '" + paramString + "'");
      case 18:
      case 19:
        str = paramString.trim();
        if ("INF".equals(str)) {
          paramString = "Infinity";
        } else {
          paramString = str;
          if ("-INF".equals(str))
            paramString = "-Infinity"; 
        } 
        return (this.typeCode == 18) ? Float.valueOf(paramString) : Double.valueOf(paramString);
      case 4:
        paramString = paramString.trim();
        i = paramString.length();
        while (true) {
          if (--i >= 0) {
            char c = paramString.charAt(i);
            if (c != 'e') {
              if (c == 'E')
                continue; 
              continue;
            } 
            throw new IllegalArgumentException("not a valid decimal: '" + paramString + "'");
          } 
          return new BigDecimal(paramString);
        } 
      case 28:
        return Duration.parseDuration(paramString);
      case 29:
        return Duration.parseYearMonthDuration(paramString);
      case 30:
        return Duration.parseDayTimeDuration(paramString);
      case 34:
        return Base64Binary.valueOf(paramString);
      case 35:
        break;
    } 
    return HexBinary.valueOf(paramString);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/XDataType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
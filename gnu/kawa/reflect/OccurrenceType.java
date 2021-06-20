package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import gnu.lists.ItemPredicate;
import gnu.mapping.Procedure;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class OccurrenceType extends ObjectType implements Externalizable, TypeValue {
  public static final Type emptySequenceType = getInstance((Type)SingletonType.instance, 0, 0);
  
  static final Method isInstanceMethod;
  
  public static final ClassType typeOccurrenceType = ClassType.make("gnu.kawa.reflect.OccurrenceType");
  
  Type base;
  
  int maxOccurs;
  
  int minOccurs;
  
  static {
    isInstanceMethod = typeOccurrenceType.getDeclaredMethod("isInstance", 1);
  }
  
  public OccurrenceType(Type paramType, int paramInt1, int paramInt2) {
    this.base = paramType;
    this.minOccurs = paramInt1;
    this.maxOccurs = paramInt2;
  }
  
  public static Type getInstance(Type paramType, int paramInt1, int paramInt2) {
    return (Type)((paramInt1 == 1 && paramInt2 == 1) ? paramType : ((paramInt1 == 0 && paramInt2 < 0 && (paramType == SingletonType.instance || paramType == Type.pointer_type)) ? Type.pointer_type : new OccurrenceType(paramType, paramInt1, paramInt2)));
  }
  
  public static char itemCountCode(Type paramType) {
    int j = itemCountRange(paramType);
    int i = j & 0xFFF;
    j >>= 12;
    return (j == 0) ? '0' : ((i == 0) ? ((j == 1) ? '?' : '*') : ((i == 1 && j == 1) ? '1' : '+'));
  }
  
  public static boolean itemCountIsOne(Type paramType) {
    return (itemCountRange(paramType) == 4097);
  }
  
  public static boolean itemCountIsZeroOrOne(Type paramType) {
    return (itemCountRange(paramType) >> 13 == 0);
  }
  
  public static int itemCountRange(Type paramType) {
    int i = 0;
    if (!(paramType instanceof SingletonType)) {
      OccurrenceType occurrenceType;
      if (paramType instanceof OccurrenceType) {
        occurrenceType = (OccurrenceType)paramType;
        int j = occurrenceType.minOccurs();
        int k = occurrenceType.maxOccurs();
        int i1 = itemCountRange(occurrenceType.getBase());
        if ((j == 1 && k == 1) || i1 == 0)
          return i1; 
        i = k;
        if (k > 1048575)
          i = -1; 
        if (i == 0)
          return 0; 
        int n = i1 >> 12;
        k = i;
        int m = j;
        if (i1 != 4097) {
          k = j;
          if (j > 4095)
            k = 4095; 
          k *= i1 & 0xFFF;
          j = k;
          if (k > 4095)
            j = 4095; 
          if (i < 0 || n < 0) {
            i = -1;
          } else {
            i *= n;
          } 
          k = i;
          m = j;
          if (i > 1048575) {
            k = -1;
            m = j;
          } 
        } 
        return k << 12 | m;
      } 
      if (occurrenceType instanceof gnu.bytecode.PrimType) {
        if (!occurrenceType.isVoid())
          i = 4097; 
        return i;
      } 
      if (!(occurrenceType instanceof gnu.bytecode.ArrayType) && (!(occurrenceType instanceof ObjectType) || occurrenceType.compare((Type)Compilation.typeValues) != -3))
        return -4096; 
    } 
    return 4097;
  }
  
  public static Type itemPrimeType(Type paramType) {
    while (paramType instanceof OccurrenceType)
      paramType = ((OccurrenceType)paramType).getBase(); 
    return (Type)(itemCountIsOne(paramType) ? paramType : SingletonType.instance);
  }
  
  public Object coerceFromObject(Object paramObject) {
    if (!(paramObject instanceof gnu.mapping.Values) && this.minOccurs <= 1 && this.maxOccurs != 0)
      return this.base.coerceFromObject(paramObject); 
    Object object = paramObject;
    if (!isInstance(paramObject))
      throw new ClassCastException(); 
    return object;
  }
  
  public int compare(Type paramType) {
    if (paramType instanceof OccurrenceType) {
      OccurrenceType occurrenceType = (OccurrenceType)paramType;
      if (this.minOccurs == occurrenceType.minOccurs && this.maxOccurs == occurrenceType.maxOccurs)
        return this.base.compare(occurrenceType.getBase()); 
    } 
    return -2;
  }
  
  public Expression convertValue(Expression paramExpression) {
    return null;
  }
  
  public void emitIsInstance(Variable paramVariable, Compilation paramCompilation, Target paramTarget) {
    InstanceOf.emitIsInstance(this, paramVariable, paramCompilation, paramTarget);
  }
  
  public void emitTestIf(Variable paramVariable, Declaration paramDeclaration, Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    if (paramVariable != null)
      codeAttr.emitLoad(paramVariable); 
    if (paramDeclaration != null) {
      codeAttr.emitDup();
      paramDeclaration.compileStore(paramCompilation);
    } 
    paramCompilation.compileConstant(this);
    codeAttr.emitSwap();
    codeAttr.emitInvokeVirtual(isInstanceMethod);
    codeAttr.emitIfIntNotZero();
  }
  
  public Type getBase() {
    return this.base;
  }
  
  public Procedure getConstructor() {
    return null;
  }
  
  public Type getImplementationType() {
    return (Type)Type.pointer_type;
  }
  
  public boolean isInstance(Object paramObject) {
    boolean bool = true;
    boolean bool2 = true;
    boolean bool3 = false;
    if (paramObject instanceof gnu.mapping.Values) {
      paramObject = paramObject;
      int i = paramObject.startPos();
      int j = 0;
      boolean bool4 = false;
      int k = i;
      if (this.base instanceof ItemPredicate) {
        ItemPredicate itemPredicate = (ItemPredicate)this.base;
        j = i;
        i = bool4;
        while (true) {
          bool = itemPredicate.isInstancePos((AbstractSequence)paramObject, j);
          j = paramObject.nextPos(j);
          if (j == 0) {
            if (i >= this.minOccurs) {
              boolean bool6 = bool2;
              if (this.maxOccurs >= 0) {
                if (i <= this.maxOccurs)
                  return bool2; 
              } else {
                return bool6;
              } 
            } 
            return false;
          } 
          boolean bool5 = bool3;
          if (bool) {
            i++;
            continue;
          } 
          return bool5;
        } 
      } 
      while (true) {
        k = paramObject.nextPos(k);
        if (k == 0) {
          if (j >= this.minOccurs) {
            boolean bool5 = bool;
            if (this.maxOccurs >= 0) {
              if (j <= this.maxOccurs)
                return bool; 
            } else {
              return bool5;
            } 
          } 
          return false;
        } 
        Object object = paramObject.getPosPrevious(k);
        if (!this.base.isInstance(object))
          return false; 
        j++;
      } 
    } 
    boolean bool1 = bool3;
    if (this.minOccurs <= 1) {
      bool1 = bool3;
      if (this.maxOccurs != 0)
        return this.base.isInstance(paramObject); 
    } 
    return bool1;
  }
  
  public int maxOccurs() {
    return this.maxOccurs;
  }
  
  public int minOccurs() {
    return this.minOccurs;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.base = (Type)paramObjectInput.readObject();
    this.minOccurs = paramObjectInput.readInt();
    this.maxOccurs = paramObjectInput.readInt();
  }
  
  public String toString() {
    boolean bool;
    String str = this.base.toString();
    if (str == null || str.indexOf(' ') >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    StringBuffer stringBuffer = new StringBuffer();
    if (bool)
      stringBuffer.append('('); 
    stringBuffer.append(str);
    if (bool)
      stringBuffer.append(')'); 
    if (this.minOccurs != 1 || this.maxOccurs != 1) {
      if (this.minOccurs == 0 && this.maxOccurs == 1) {
        stringBuffer.append('?');
        return stringBuffer.toString();
      } 
      if (this.minOccurs == 1 && this.maxOccurs == -1) {
        stringBuffer.append('+');
        return stringBuffer.toString();
      } 
      if (this.minOccurs == 0 && this.maxOccurs == -1) {
        stringBuffer.append('*');
        return stringBuffer.toString();
      } 
      stringBuffer.append('{');
      stringBuffer.append(this.minOccurs);
      stringBuffer.append(',');
      if (this.maxOccurs >= 0) {
        stringBuffer.append(this.maxOccurs);
      } else {
        stringBuffer.append('*');
      } 
      stringBuffer.append('}');
    } 
    return stringBuffer.toString();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.base);
    paramObjectOutput.writeInt(this.minOccurs);
    paramObjectOutput.writeInt(this.maxOccurs);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/OccurrenceType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
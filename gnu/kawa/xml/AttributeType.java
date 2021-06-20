package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import gnu.lists.AttributePredicate;
import gnu.lists.SeqPosition;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.xml.namespace.QName;

public class AttributeType extends NodeType implements TypeValue, Externalizable, AttributePredicate {
  static final Method coerceMethod;
  
  static final Method coerceOrNullMethod;
  
  public static final ClassType typeAttributeType = ClassType.make("gnu.kawa.xml.AttributeType");
  
  Symbol qname;
  
  static {
    coerceMethod = typeAttributeType.getDeclaredMethod("coerce", 3);
    coerceOrNullMethod = typeAttributeType.getDeclaredMethod("coerceOrNull", 3);
  }
  
  public AttributeType(Symbol paramSymbol) {
    this((String)null, paramSymbol);
  }
  
  public AttributeType(String paramString, Symbol paramSymbol) {
    super(paramString);
    this.qname = paramSymbol;
  }
  
  public static SeqPosition coerce(Object paramObject, String paramString1, String paramString2) {
    paramObject = coerceOrNull(paramObject, paramString1, paramString2);
    if (paramObject == null)
      throw new ClassCastException(); 
    return (SeqPosition)paramObject;
  }
  
  public static KAttr coerceOrNull(Object paramObject, String paramString1, String paramString2) {
    KNode kNode = NodeType.coerceOrNull(paramObject, 4);
    if (kNode == null)
      return null; 
    String str = paramString2;
    if (paramString2 != null) {
      str = paramString2;
      if (paramString2.length() == 0)
        str = null; 
    } 
    paramObject = kNode.getNextTypeObject();
    if (paramObject instanceof Symbol) {
      paramObject = paramObject;
      paramString2 = paramObject.getNamespaceURI();
      paramObject = paramObject.getLocalName();
    } else if (paramObject instanceof QName) {
      paramObject = paramObject;
      paramString2 = paramObject.getNamespaceURI();
      paramObject = paramObject.getLocalPart();
    } else {
      paramString2 = "";
      paramObject = paramObject.toString().intern();
    } 
    return ((str == paramObject || str == null) && (paramString1 == paramString2 || paramString1 == null)) ? (KAttr)kNode : null;
  }
  
  public static AttributeType make(Symbol paramSymbol) {
    return new AttributeType(paramSymbol);
  }
  
  public static AttributeType make(String paramString1, String paramString2) {
    if (paramString1 != null) {
      Symbol symbol1 = Symbol.make(paramString1, paramString2);
      return new AttributeType(symbol1);
    } 
    if (paramString2 == "") {
      Symbol symbol1 = ElementType.MATCH_ANY_QNAME;
      return new AttributeType(symbol1);
    } 
    Symbol symbol = new Symbol(null, paramString2);
    return new AttributeType(symbol);
  }
  
  public Object coerceFromObject(Object paramObject) {
    return coerce(paramObject, this.qname.getNamespaceURI(), this.qname.getLocalName());
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr) {
    paramCodeAttr.emitPushString(this.qname.getNamespaceURI());
    paramCodeAttr.emitPushString(this.qname.getLocalName());
    paramCodeAttr.emitInvokeStatic(coerceMethod);
  }
  
  protected void emitCoerceOrNullMethod(Variable paramVariable, Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    if (paramVariable != null)
      codeAttr.emitLoad(paramVariable); 
    codeAttr.emitPushString(this.qname.getNamespaceURI());
    codeAttr.emitPushString(this.qname.getLocalName());
    codeAttr.emitInvokeStatic(coerceOrNullMethod);
  }
  
  public Type getImplementationType() {
    return (Type)ClassType.make("gnu.kawa.xml.KAttr");
  }
  
  public final String getLocalName() {
    return this.qname.getLocalName();
  }
  
  public final String getNamespaceURI() {
    return this.qname.getNamespaceURI();
  }
  
  public boolean isInstance(AbstractSequence paramAbstractSequence, int paramInt, Object paramObject) {
    String str1;
    String str4 = this.qname.getNamespaceURI();
    String str3 = this.qname.getLocalName();
    if (paramObject instanceof Symbol) {
      Symbol symbol = (Symbol)paramObject;
      paramObject = symbol.getNamespaceURI();
      str1 = symbol.getLocalName();
    } else if (paramObject instanceof QName) {
      QName qName = (QName)paramObject;
      paramObject = qName.getNamespaceURI();
      str1 = qName.getLocalPart();
    } else {
      String str = "";
      str1 = paramObject.toString().intern();
      paramObject = str;
    } 
    String str2 = str3;
    if (str3 != null) {
      str2 = str3;
      if (str3.length() == 0)
        str2 = null; 
    } 
    return ((str2 == str1 || str2 == null) && (str4 == paramObject || str4 == null));
  }
  
  public boolean isInstance(Object paramObject) {
    return (coerceOrNull(paramObject, this.qname.getNamespaceURI(), this.qname.getLocalName()) != null);
  }
  
  public boolean isInstancePos(AbstractSequence paramAbstractSequence, int paramInt) {
    int i = paramAbstractSequence.getNextKind(paramInt);
    return (i == 35) ? isInstance(paramAbstractSequence, paramInt, paramAbstractSequence.getNextTypeObject(paramInt)) : ((i == 32) ? isInstance(paramAbstractSequence.getPosNext(paramInt)) : false);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    String str = paramObjectInput.readUTF();
    if (str.length() > 0)
      setName(str); 
    this.qname = (Symbol)paramObjectInput.readObject();
  }
  
  public String toString() {
    return "AttributeType " + this.qname;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    String str2 = getName();
    String str1 = str2;
    if (str2 == null)
      str1 = ""; 
    paramObjectOutput.writeUTF(str1);
    paramObjectOutput.writeObject(this.qname);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/AttributeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
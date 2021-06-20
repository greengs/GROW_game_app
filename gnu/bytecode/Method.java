package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Stack;

public class Method implements AttrContainer, Member {
  int access_flags;
  
  Type[] arg_types;
  
  Attribute attributes;
  
  ClassType classfile;
  
  CodeAttr code;
  
  ExceptionsAttr exceptions;
  
  private String name;
  
  int name_index;
  
  Method next;
  
  Type return_type;
  
  String signature;
  
  int signature_index;
  
  private Method() {}
  
  Method(ClassType paramClassType, int paramInt) {
    if (paramClassType.last_method == null) {
      paramClassType.methods = this;
    } else {
      paramClassType.last_method.next = this;
    } 
    paramClassType.last_method = this;
    paramClassType.methods_count++;
    this.access_flags = paramInt;
    this.classfile = paramClassType;
  }
  
  public Method(Method paramMethod, ClassType paramClassType) {
    this.arg_types = paramMethod.arg_types;
    this.return_type = paramMethod.return_type;
    this.name = paramMethod.name;
    this.access_flags = paramMethod.access_flags;
    this.classfile = paramClassType;
  }
  
  public static Method makeCloneMethod(Type paramType) {
    Method method = new Method();
    method.name = "clone";
    method.access_flags = 1;
    method.arg_types = Type.typeArray0;
    method.return_type = paramType;
    method.classfile = Type.pointer_type;
    return method;
  }
  
  public static String makeSignature(Type[] paramArrayOfType, Type paramType) {
    StringBuilder stringBuilder = new StringBuilder(100);
    int j = paramArrayOfType.length;
    stringBuilder.append('(');
    for (int i = 0; i < j; i++)
      stringBuilder.append(paramArrayOfType[i].getSignature()); 
    stringBuilder.append(')');
    stringBuilder.append(paramType.getSignature());
    return stringBuilder.toString();
  }
  
  public void allocate_local(Variable paramVariable) {
    paramVariable.allocateLocal(this.code);
  }
  
  void assignConstants() {
    ConstantPool constantPool = getConstants();
    if (this.name_index == 0 && this.name != null)
      this.name_index = (constantPool.addUtf8(this.name)).index; 
    if (this.signature_index == 0)
      this.signature_index = (constantPool.addUtf8(getSignature())).index; 
    Attribute.assignConstants(this, this.classfile);
  }
  
  public void cleanupAfterCompilation() {
    this.attributes = null;
    this.exceptions = null;
    this.code = null;
  }
  
  public void compile_checkcast(Type paramType) {
    this.code.emitCheckcast(paramType);
  }
  
  public void compile_push_this() {
    this.code.emitPushThis();
  }
  
  public void compile_push_value(Variable paramVariable) {
    this.code.emitLoad(paramVariable);
  }
  
  public void compile_store_value(Variable paramVariable) {
    this.code.emitStore(paramVariable);
  }
  
  public final Attribute getAttributes() {
    return this.attributes;
  }
  
  public final CodeAttr getCode() {
    return this.code;
  }
  
  public final ConstantPool getConstants() {
    return this.classfile.constants;
  }
  
  public ClassType getDeclaringClass() {
    return this.classfile;
  }
  
  public final ExceptionsAttr getExceptionAttr() {
    return this.exceptions;
  }
  
  public final ClassType[] getExceptions() {
    return (this.exceptions == null) ? null : this.exceptions.getExceptions();
  }
  
  public int getModifiers() {
    return this.access_flags;
  }
  
  public final String getName() {
    return this.name;
  }
  
  public final Method getNext() {
    return this.next;
  }
  
  public final Type[] getParameterTypes() {
    return this.arg_types;
  }
  
  public final Type getReturnType() {
    return this.return_type;
  }
  
  public String getSignature() {
    if (this.signature == null)
      this.signature = makeSignature(this.arg_types, this.return_type); 
    return this.signature;
  }
  
  public final boolean getStaticFlag() {
    return ((this.access_flags & 0x8) != 0);
  }
  
  public void initCode() {
    if (this.classfile.constants == null)
      this.classfile.constants = new ConstantPool(); 
    prepareCode(0);
    this.code.sourceDbgExt = this.classfile.sourceDbgExt;
    this.code.noteParamTypes();
    this.code.pushScope();
  }
  
  public void init_param_slots() {
    startCode();
  }
  
  void instruction_start_hook(int paramInt) {
    prepareCode(paramInt);
  }
  
  public final boolean isAbstract() {
    return ((this.access_flags & 0x400) != 0);
  }
  
  void kill_local(Variable paramVariable) {
    paramVariable.freeLocal(this.code);
  }
  
  public void listParameters(StringBuffer paramStringBuffer) {
    int j = this.arg_types.length;
    paramStringBuffer.append('(');
    for (int i = 0; i < j; i++) {
      if (i > 0)
        paramStringBuffer.append(','); 
      paramStringBuffer.append(this.arg_types[i].getName());
    } 
    paramStringBuffer.append(')');
  }
  
  public void maybe_compile_checkcast(Type paramType) {
    if (paramType != this.code.topType())
      this.code.emitCheckcast(paramType); 
  }
  
  public Scope popScope() {
    return this.code.popScope();
  }
  
  final Type pop_stack_type() {
    return this.code.popType();
  }
  
  void prepareCode(int paramInt) {
    if (this.code == null)
      this.code = new CodeAttr(this); 
    this.code.reserve(paramInt);
  }
  
  public Scope pushScope() {
    prepareCode(0);
    return this.code.pushScope();
  }
  
  final void push_stack_type(Type paramType) {
    this.code.pushType(paramType);
  }
  
  public void push_var(Variable paramVariable) {
    this.code.emitLoad(paramVariable);
  }
  
  public final boolean reachableHere() {
    return this.code.reachableHere();
  }
  
  public final void setAttributes(Attribute paramAttribute) {
    this.attributes = paramAttribute;
  }
  
  public void setExceptions(ClassType[] paramArrayOfClassType) {
    if (this.exceptions == null)
      this.exceptions = new ExceptionsAttr(this); 
    this.exceptions.setExceptions(paramArrayOfClassType);
  }
  
  public void setExceptions(short[] paramArrayOfshort) {
    if (this.exceptions == null)
      this.exceptions = new ExceptionsAttr(this); 
    this.exceptions.setExceptions(paramArrayOfshort, this.classfile);
  }
  
  public void setModifiers(int paramInt) {
    this.access_flags = paramInt;
  }
  
  public final void setName(int paramInt) {
    if (paramInt <= 0) {
      this.name = null;
    } else {
      this.name = ((CpoolUtf8)getConstants().getForced(paramInt, 1)).string;
    } 
    this.name_index = paramInt;
  }
  
  public final void setName(String paramString) {
    this.name = paramString;
  }
  
  public void setSignature(int paramInt) {
    CpoolUtf8 cpoolUtf8 = (CpoolUtf8)getConstants().getForced(paramInt, 1);
    this.signature_index = paramInt;
    setSignature(cpoolUtf8.string);
  }
  
  public void setSignature(String paramString) {
    int j = paramString.length();
    if (j < 3 || paramString.charAt(0) != '(')
      throw new ClassFormatError("bad method signature"); 
    int i = 1;
    Stack<Type> stack = new Stack();
    while (true) {
      int k = Type.signatureLength(paramString, i);
      if (k < 0) {
        if (i < j && paramString.charAt(i) == ')') {
          this.arg_types = new Type[stack.size()];
          k = stack.size();
          while (true) {
            if (--k >= 0) {
              this.arg_types[k] = stack.pop();
              continue;
            } 
            this.return_type = Type.signatureToType(paramString, i + 1, j - i - 1);
            return;
          } 
          break;
        } 
        throw new ClassFormatError("bad method signature");
      } 
      stack.push(Type.signatureToType(paramString, i, k));
      i += k;
    } 
  }
  
  public final void setStaticFlag(boolean paramBoolean) {
    if (paramBoolean) {
      this.access_flags |= 0x8;
      return;
    } 
    this.access_flags ^= 0xFFFFFFF7;
  }
  
  public CodeAttr startCode() {
    initCode();
    this.code.addParamLocals();
    return this.code;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(100);
    stringBuffer.append(getDeclaringClass().getName());
    stringBuffer.append('.');
    stringBuffer.append(this.name);
    if (this.arg_types != null) {
      listParameters(stringBuffer);
      stringBuffer.append(this.return_type.getName());
    } 
    return stringBuffer.toString();
  }
  
  void write(DataOutputStream paramDataOutputStream, ClassType paramClassType) throws IOException {
    paramDataOutputStream.writeShort(this.access_flags);
    paramDataOutputStream.writeShort(this.name_index);
    paramDataOutputStream.writeShort(this.signature_index);
    Attribute.writeAll(this, paramDataOutputStream);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/Method.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
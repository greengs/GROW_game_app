package gnu.expr;

import gnu.bytecode.Field;
import gnu.bytecode.Type;

public class Literal {
  static final int CYCLIC = 4;
  
  static final int EMITTED = 8;
  
  static final int WRITING = 1;
  
  static final int WRITTEN = 2;
  
  public static final Literal nullLiteral = new Literal(null, (Type)Type.nullType);
  
  Type[] argTypes;
  
  Object[] argValues;
  
  public Field field;
  
  public int flags;
  
  int index;
  
  Literal next;
  
  public Type type;
  
  Object value;
  
  public Literal(Object paramObject, Field paramField, LitTable paramLitTable) {
    this.value = paramObject;
    paramLitTable.literalTable.put(paramObject, this);
    this.field = paramField;
    this.type = paramField.getType();
    this.flags = 10;
  }
  
  Literal(Object paramObject, Type paramType) {
    this.value = paramObject;
    this.type = paramType;
  }
  
  public Literal(Object paramObject, Type paramType, LitTable paramLitTable) {
    this.value = paramObject;
    paramLitTable.literalTable.put(paramObject, this);
    this.type = paramType;
  }
  
  public Literal(Object paramObject, LitTable paramLitTable) {
    this(paramObject, (String)null, paramLitTable);
  }
  
  public Literal(Object paramObject, String paramString, LitTable paramLitTable) {
    this.value = paramObject;
    paramLitTable.literalTable.put(paramObject, this);
    this.type = Type.make(paramObject.getClass());
    assign(paramString, paramLitTable);
  }
  
  void assign(Field paramField, LitTable paramLitTable) {
    this.next = paramLitTable.literalsChain;
    paramLitTable.literalsChain = this;
    this.field = paramField;
  }
  
  void assign(LitTable paramLitTable) {
    assign((String)null, paramLitTable);
  }
  
  void assign(String paramString, LitTable paramLitTable) {
    int i;
    if (paramLitTable.comp.immediate) {
      i = 9;
    } else {
      i = 24;
    } 
    if (paramString == null) {
      int j = paramLitTable.literalsCount;
      paramLitTable.literalsCount = j + 1;
      this.index = j;
      paramString = "Lit" + this.index;
    } else {
      i |= 0x1;
    } 
    assign(paramLitTable.mainClass.addField(paramString, this.type, i), paramLitTable);
  }
  
  public final Object getValue() {
    return this.value;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/Literal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
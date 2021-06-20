package gnu.mapping;

import gnu.bytecode.Type;

public class WrongType extends WrappedException {
  public static final int ARG_CAST = -4;
  
  public static final int ARG_DESCRIPTION = -3;
  
  public static final int ARG_UNKNOWN = -1;
  
  public static final int ARG_VARNAME = -2;
  
  public Object argValue;
  
  public Object expectedType;
  
  public int number;
  
  public Procedure proc;
  
  public String procname;
  
  public WrongType(int paramInt, Object paramObject, Type paramType) {
    this.number = paramInt;
    this.argValue = paramObject;
    this.expectedType = paramType;
  }
  
  public WrongType(Procedure paramProcedure, int paramInt, ClassCastException paramClassCastException) {
    super(paramClassCastException);
    this.proc = paramProcedure;
    this.procname = paramProcedure.getName();
    this.number = paramInt;
  }
  
  public WrongType(Procedure paramProcedure, int paramInt, Object paramObject) {
    this.proc = paramProcedure;
    this.procname = paramProcedure.getName();
    this.number = paramInt;
    this.argValue = paramObject;
  }
  
  public WrongType(Procedure paramProcedure, int paramInt, Object paramObject, Type paramType) {
    this.proc = paramProcedure;
    this.procname = paramProcedure.getName();
    this.number = paramInt;
    this.argValue = paramObject;
    this.expectedType = paramType;
  }
  
  public WrongType(Procedure paramProcedure, int paramInt, Object paramObject, String paramString) {
    this(paramProcedure.getName(), paramInt, paramObject, paramString);
    this.proc = paramProcedure;
  }
  
  public WrongType(ClassCastException paramClassCastException, Procedure paramProcedure, int paramInt, Object paramObject) {
    this(paramProcedure, paramInt, paramClassCastException);
    this.argValue = paramObject;
  }
  
  public WrongType(ClassCastException paramClassCastException, String paramString, int paramInt, Object paramObject) {
    this(paramString, paramInt, paramClassCastException);
    this.argValue = paramObject;
  }
  
  public WrongType(String paramString, int paramInt, ClassCastException paramClassCastException) {
    super(paramClassCastException);
    this.procname = paramString;
    this.number = paramInt;
  }
  
  public WrongType(String paramString1, int paramInt, Object paramObject, String paramString2) {
    this.procname = paramString1;
    this.number = paramInt;
    this.argValue = paramObject;
    this.expectedType = paramString2;
  }
  
  public WrongType(String paramString1, int paramInt, String paramString2) {
    super((String)null, (Throwable)null);
    this.procname = paramString1;
    this.number = paramInt;
    this.expectedType = paramString2;
  }
  
  public static WrongType make(ClassCastException paramClassCastException, Procedure paramProcedure, int paramInt) {
    return new WrongType(paramProcedure, paramInt, paramClassCastException);
  }
  
  public static WrongType make(ClassCastException paramClassCastException, Procedure paramProcedure, int paramInt, Object paramObject) {
    WrongType wrongType = new WrongType(paramProcedure, paramInt, paramClassCastException);
    wrongType.argValue = paramObject;
    return wrongType;
  }
  
  public static WrongType make(ClassCastException paramClassCastException, String paramString, int paramInt) {
    return new WrongType(paramString, paramInt, paramClassCastException);
  }
  
  public static WrongType make(ClassCastException paramClassCastException, String paramString, int paramInt, Object paramObject) {
    WrongType wrongType = new WrongType(paramString, paramInt, paramClassCastException);
    wrongType.argValue = paramObject;
    return wrongType;
  }
  
  public String getMessage() {
    StringBuffer stringBuffer = new StringBuffer(100);
    if (this.number == -3) {
      stringBuffer.append(this.procname);
    } else if (this.number == -4 || this.number == -2) {
      stringBuffer.append("Value");
    } else {
      stringBuffer.append("Argument ");
      if (this.number > 0) {
        stringBuffer.append('#');
        stringBuffer.append(this.number);
      } 
    } 
    if (this.argValue != null) {
      stringBuffer.append(" (");
      String str = this.argValue.toString();
      if (str.length() > 50) {
        stringBuffer.append(str.substring(0, 47));
        stringBuffer.append("...");
      } else {
        stringBuffer.append(str);
      } 
      stringBuffer.append(")");
    } 
    if (this.procname != null && this.number != -3) {
      String str;
      if (this.number == -2) {
        str = " for variable '";
      } else {
        str = " to '";
      } 
      stringBuffer.append(str);
      stringBuffer.append(this.procname);
      stringBuffer.append("'");
    } 
    stringBuffer.append(" has wrong type");
    if (this.argValue != null) {
      stringBuffer.append(" (");
      stringBuffer.append(this.argValue.getClass().getName());
      stringBuffer.append(")");
    } 
    Object object2 = this.expectedType;
    Object object1 = object2;
    if (object2 == null) {
      object1 = object2;
      if (this.number > 0) {
        object1 = object2;
        if (this.proc instanceof MethodProc)
          object1 = ((MethodProc)this.proc).getParameterType(this.number - 1); 
      } 
    } 
    if (object1 != null && object1 != Type.pointer_type) {
      stringBuffer.append(" (expected: ");
      if (object1 instanceof Type) {
        object2 = ((Type)object1).getName();
      } else {
        object2 = object1;
        if (object1 instanceof Class)
          object2 = ((Class)object1).getName(); 
      } 
      stringBuffer.append(object2);
      stringBuffer.append(")");
    } 
    object1 = getCause();
    if (object1 != null) {
      object1 = object1.getMessage();
      if (object1 != null) {
        stringBuffer.append(" (");
        stringBuffer.append((String)object1);
        stringBuffer.append(')');
      } 
    } 
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/WrongType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.mapping;

public class WrongArguments extends IllegalArgumentException {
  public int number;
  
  Procedure proc;
  
  public String procname;
  
  public String usage;
  
  public WrongArguments(Procedure paramProcedure, int paramInt) {
    this.proc = paramProcedure;
    this.number = paramInt;
  }
  
  public WrongArguments(String paramString1, int paramInt, String paramString2) {
    this.procname = paramString1;
    this.number = paramInt;
    this.usage = paramString2;
  }
  
  public static String checkArgCount(Procedure paramProcedure, int paramInt) {
    int i = paramProcedure.numArgs();
    String str2 = paramProcedure.getName();
    String str1 = str2;
    if (str2 == null)
      str1 = paramProcedure.getClass().getName(); 
    return checkArgCount(str1, i & 0xFFF, i >> 12, paramInt);
  }
  
  public static String checkArgCount(String paramString, int paramInt1, int paramInt2, int paramInt3) {
    boolean bool;
    if (paramInt3 < paramInt1) {
      bool = false;
    } else if (paramInt2 >= 0 && paramInt3 > paramInt2) {
      bool = true;
    } else {
      return null;
    } 
    StringBuffer stringBuffer = new StringBuffer(100);
    stringBuffer.append("call to ");
    if (paramString == null) {
      stringBuffer.append("unnamed procedure");
    } else {
      stringBuffer.append('\'');
      stringBuffer.append(paramString);
      stringBuffer.append('\'');
    } 
    if (bool) {
      paramString = " has too many";
    } else {
      paramString = " has too few";
    } 
    stringBuffer.append(paramString);
    stringBuffer.append(" arguments (");
    stringBuffer.append(paramInt3);
    if (paramInt1 == paramInt2) {
      stringBuffer.append("; must be ");
      stringBuffer.append(paramInt1);
      stringBuffer.append(')');
      return stringBuffer.toString();
    } 
    stringBuffer.append("; min=");
    stringBuffer.append(paramInt1);
    if (paramInt2 >= 0) {
      stringBuffer.append(", max=");
      stringBuffer.append(paramInt2);
    } 
    stringBuffer.append(')');
    return stringBuffer.toString();
  }
  
  public String getMessage() {
    if (this.proc != null) {
      String str = checkArgCount(this.proc, this.number);
      if (str != null)
        return str; 
    } 
    return super.getMessage();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/WrongArguments.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
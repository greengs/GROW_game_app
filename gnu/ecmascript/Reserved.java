package gnu.ecmascript;

import gnu.mapping.Procedure;

public class Reserved {
  public static final int BREAK_TOKEN = 35;
  
  public static final int CONTINUE_TOKEN = 34;
  
  public static final int ELSE_TOKEN = 38;
  
  public static final int FOR_TOKEN = 33;
  
  public static final int FUNCTION_TOKEN = 41;
  
  public static final int IF_TOKEN = 31;
  
  public static final int LESS_OP = 5;
  
  public static final int LSHIFT_OP = 4;
  
  public static final int MINUS_OP = 2;
  
  public static final int NEW_TOKEN = 39;
  
  public static final int PLUS_OP = 1;
  
  public static final int RETURN_TOKEN = 36;
  
  public static final int THIS_TOKEN = 40;
  
  public static final int TIMES_OP = 3;
  
  public static final int VAR_TOKEN = 30;
  
  public static final int WHILE_TOKEN = 32;
  
  public static final int WITH_TOKEN = 37;
  
  static final Reserved opBitAnd;
  
  static final Reserved opBitOr;
  
  static final Reserved opBitXor;
  
  static final Reserved opBoolAnd;
  
  static final Reserved opBoolOr = new Reserved("||", 1, 0);
  
  static final Reserved opDivide;
  
  static final Reserved opEqual;
  
  static final Reserved opGreater;
  
  static final Reserved opGreaterEqual;
  
  static final Reserved opLess;
  
  static final Reserved opLessEqual;
  
  static final Reserved opLshift;
  
  static final Reserved opMinus;
  
  static Reserved opMinusMinus;
  
  static final Reserved opNotEqual;
  
  static final Reserved opPlus;
  
  static Reserved opPlusPlus;
  
  static final Reserved opRemainder;
  
  static final Reserved opRshiftSigned;
  
  static final Reserved opRshiftUnsigned;
  
  static final Reserved opTimes;
  
  String name;
  
  int prio;
  
  Procedure proc;
  
  static {
    opBoolAnd = new Reserved("&&", 2, 0);
    opBitOr = new Reserved("|", 3, 0);
    opBitXor = new Reserved("^", 4, 0);
    opBitAnd = new Reserved("&", 5, 0);
    opEqual = new Reserved("=", 6, 0);
    opNotEqual = new Reserved("!=", 6, 0);
    opLess = new Reserved("<", 7, 5);
    opGreater = new Reserved(">", 7, 0);
    opLessEqual = new Reserved("<=", 7, 0);
    opGreaterEqual = new Reserved(">=", 7, 0);
    opLshift = new Reserved("<<", 8, 4);
    opRshiftSigned = new Reserved(">>", 8, 0);
    opRshiftUnsigned = new Reserved(">>>", 8, 0);
    opPlus = new Reserved("+", 9, 1);
    opMinus = new Reserved("-", 9, 2);
    opTimes = new Reserved("*", 10, 3);
    opDivide = new Reserved("/", 10, 0);
    opRemainder = new Reserved("%", 10, 0);
  }
  
  public Reserved(String paramString, int paramInt) {
    this.name = paramString;
    this.prio = paramInt;
  }
  
  public Reserved(String paramString, int paramInt1, int paramInt2) {
    this.name = paramString;
    this.prio = paramInt1;
    this.proc = (Procedure)new BinaryOp(paramString, paramInt2);
  }
  
  public Reserved(String paramString, int paramInt, Procedure paramProcedure) {
    this.name = paramString;
    this.prio = paramInt;
    this.proc = paramProcedure;
  }
  
  public boolean isAssignmentOp() {
    return false;
  }
  
  public String toString() {
    return "[Reserved \"" + this.name + "\" prio:" + this.prio + "]";
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/ecmascript/Reserved.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
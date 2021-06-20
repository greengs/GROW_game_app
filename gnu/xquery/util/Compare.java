package gnu.xquery.util;

import gnu.kawa.functions.NumberCompare;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.XDataType;
import gnu.kawa.xml.XTimeType;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import gnu.math.DFloNum;
import gnu.math.DateTime;
import gnu.math.Duration;
import gnu.math.Unit;

public class Compare extends Procedure2 {
  public static final Compare $Eq = make("=", 8);
  
  public static final Compare $Ex$Eq = make("!=", 23);
  
  public static final Compare $Gr = make(">", 16);
  
  public static final Compare $Gr$Eq = make(">=", 24);
  
  public static final Compare $Ls = make("<", 4);
  
  public static final Compare $Ls$Eq = make("<=", 12);
  
  static final int LENIENT_COMPARISON = 64;
  
  static final int LENIENT_EQ = 72;
  
  static final int RESULT_EQU = 0;
  
  static final int RESULT_GRT = 1;
  
  static final int RESULT_LSS = -1;
  
  static final int RESULT_NAN = -2;
  
  static final int RESULT_NEQ = -3;
  
  static final int TRUE_IF_EQU = 8;
  
  static final int TRUE_IF_GRT = 16;
  
  static final int TRUE_IF_LSS = 4;
  
  static final int TRUE_IF_NAN = 2;
  
  static final int TRUE_IF_NEQ = 1;
  
  static final int VALUE_COMPARISON = 32;
  
  public static final Compare valEq = make("eq", 40);
  
  public static final Compare valGe;
  
  public static final Compare valGt;
  
  public static final Compare valLe;
  
  public static final Compare valLt;
  
  public static final Compare valNe = make("ne", 55);
  
  int flags;
  
  static {
    valGt = make("gt", 48);
    valGe = make("ge", 56);
    valLt = make("lt", 36);
    valLe = make("le", 44);
  }
  
  public static boolean apply(int paramInt, Object paramObject1, Object paramObject2, NamedCollator paramNamedCollator) {
    if (paramObject1 instanceof Values) {
      paramObject1 = paramObject1;
      int i = 0;
      while (true) {
        int j = paramObject1.nextDataIndex(i);
        if (j >= 0) {
          if (apply(paramInt, paramObject1.getPosNext(i << 1), paramObject2, paramNamedCollator))
            return true; 
          i = j;
          continue;
        } 
        return false;
      } 
    } 
    if (paramObject2 instanceof Values) {
      paramObject2 = paramObject2;
      int i = 0;
      while (true) {
        int j = paramObject2.nextDataIndex(i);
        if (j >= 0) {
          if (apply(paramInt, paramObject1, paramObject2.getPosNext(i << 1), paramNamedCollator))
            return true; 
          i = j;
          continue;
        } 
        return false;
      } 
    } 
    return atomicCompare(paramInt, KNode.atomicValue(paramObject1), KNode.atomicValue(paramObject2), paramNamedCollator);
  }
  
  public static boolean atomicCompare(int paramInt, Object paramObject1, Object paramObject2, NamedCollator paramNamedCollator) {
    int i;
    Object object = paramObject1;
    if (paramObject1 instanceof gnu.kawa.xml.UntypedAtomic) {
      paramObject1 = paramObject1.toString();
      if ((paramInt & 0x20) != 0) {
        object = paramObject1;
      } else if (paramObject2 instanceof DateTime) {
        object = XTimeType.parseDateTime((String)paramObject1, ((DateTime)paramObject2).components());
      } else if (paramObject2 instanceof Duration) {
        object = Duration.parse((String)paramObject1, ((Duration)paramObject2).unit());
      } else if (paramObject2 instanceof Number) {
        object = new DFloNum((String)paramObject1);
      } else if (paramObject2 instanceof Boolean) {
        object = XDataType.booleanType.valueOf((String)paramObject1);
      } else {
        object = paramObject1;
      } 
    } 
    paramObject1 = paramObject2;
    if (paramObject2 instanceof gnu.kawa.xml.UntypedAtomic) {
      paramObject1 = paramObject2.toString();
      if ((paramInt & 0x20) == 0)
        if (object instanceof DateTime) {
          paramObject1 = XTimeType.parseDateTime((String)paramObject1, ((DateTime)object).components());
        } else if (object instanceof Duration) {
          paramObject1 = Duration.parse((String)paramObject1, ((Duration)object).unit());
        } else if (object instanceof Number) {
          paramObject1 = new DFloNum((String)paramObject1);
        } else if (object instanceof Boolean) {
          paramObject1 = XDataType.booleanType.valueOf((String)paramObject1);
        }  
    } 
    if (object instanceof Number || paramObject1 instanceof Number) {
      if (object instanceof Duration) {
        if (!(paramObject1 instanceof Duration)) {
          i = -3;
        } else {
          paramObject2 = object;
          paramObject1 = paramObject1;
          if ((((Duration)paramObject2).unit != ((Duration)paramObject1).unit || ((Duration)paramObject2).unit == Unit.duration) && !equalityComparison(paramInt)) {
            i = -3;
          } else {
            i = Duration.compare((Duration)paramObject2, (Duration)paramObject1);
          } 
        } 
      } else if (object instanceof DateTime) {
        if (!(paramObject1 instanceof DateTime)) {
          i = -3;
        } else {
          paramObject2 = object;
          paramObject1 = paramObject1;
          i = paramObject2.components();
          if (i != paramObject1.components()) {
            i = -3;
          } else if (!equalityComparison(paramInt) && i != 112 && i != 14 && i != 126) {
            i = -3;
          } else {
            i = DateTime.compare((DateTime)paramObject2, (DateTime)paramObject1);
          } 
        } 
      } else if (paramObject1 instanceof Duration || paramObject1 instanceof DateTime) {
        i = -3;
      } else {
        i = NumberCompare.compare(object, paramObject1, false);
      } 
      if (i == -3 && (paramInt & 0x40) == 0)
        throw new IllegalArgumentException("values cannot be compared"); 
      return NumberCompare.checkCompareCode(i, paramInt);
    } 
    if (object instanceof gnu.mapping.Symbol) {
      if (paramObject1 instanceof gnu.mapping.Symbol && equalityComparison(paramInt)) {
        if (object.equals(paramObject1)) {
          i = 0;
        } else {
          i = -2;
        } 
      } else {
        i = -3;
      } 
    } else if (object instanceof Boolean) {
      if (paramObject1 instanceof Boolean) {
        boolean bool1 = ((Boolean)object).booleanValue();
        boolean bool2 = ((Boolean)paramObject1).booleanValue();
        if (bool1 == bool2) {
          i = 0;
        } else if (bool2) {
          i = -1;
        } else {
          i = 1;
        } 
      } else {
        i = -3;
      } 
    } else if (paramObject1 instanceof Boolean || paramObject1 instanceof gnu.mapping.Symbol) {
      i = -3;
    } else {
      paramObject2 = object.toString();
      paramObject1 = paramObject1.toString();
      if (paramNamedCollator != null) {
        i = paramNamedCollator.compare((String)paramObject2, (String)paramObject1);
      } else {
        i = NamedCollator.codepointCompare((String)paramObject2, (String)paramObject1);
      } 
      if (i < 0) {
        i = -1;
      } else if (i > 0) {
        i = 1;
      } else {
        i = 0;
      } 
    } 
    if (i == -3 && (paramInt & 0x40) == 0)
      throw new IllegalArgumentException("values cannot be compared"); 
    return NumberCompare.checkCompareCode(i, paramInt);
  }
  
  public static boolean equalityComparison(int paramInt) {
    int i;
    if ((paramInt & 0x10) != 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if ((paramInt & 0x4) != 0) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    return (i == paramInt);
  }
  
  public static Compare make(String paramString, int paramInt) {
    Compare compare = new Compare();
    compare.setName(paramString);
    compare.setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateCompare");
    compare.flags = paramInt;
    return compare;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    return ((this.flags & 0x20) != 0) ? ((paramObject1 == null || paramObject1 == Values.empty) ? paramObject1 : ((paramObject2 == null || paramObject2 == Values.empty) ? paramObject2 : (atomicCompare(this.flags, KNode.atomicValue(paramObject1), KNode.atomicValue(paramObject2), (NamedCollator)null) ? Boolean.TRUE : Boolean.FALSE))) : (apply(this.flags, paramObject1, paramObject2, (NamedCollator)null) ? Boolean.TRUE : Boolean.FALSE);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/Compare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
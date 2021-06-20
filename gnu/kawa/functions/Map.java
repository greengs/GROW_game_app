package gnu.kawa.functions;

import gnu.expr.Declaration;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;

public class Map extends ProcedureN {
  final Declaration applyFieldDecl;
  
  final ApplyToArgs applyToArgs;
  
  boolean collect;
  
  final IsEq isEq;
  
  public Map(boolean paramBoolean, ApplyToArgs paramApplyToArgs, Declaration paramDeclaration, IsEq paramIsEq) {
    super(str);
    String str;
    this.collect = paramBoolean;
    this.applyToArgs = paramApplyToArgs;
    this.applyFieldDecl = paramDeclaration;
    this.isEq = paramIsEq;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyMap");
  }
  
  public static void forEach1(Procedure paramProcedure, Object paramObject) throws Throwable {
    while (paramObject != LList.Empty) {
      paramObject = paramObject;
      paramProcedure.apply1(paramObject.getCar());
      paramObject = paramObject.getCdr();
    } 
  }
  
  public static Object map1(Procedure paramProcedure, Object paramObject) throws Throwable {
    Object object1 = LList.Empty;
    Pair pair = null;
    Object object2 = paramObject;
    paramObject = pair;
    while (object2 != LList.Empty) {
      pair = (Pair)object2;
      object2 = new Pair(paramProcedure.apply1(pair.getCar()), LList.Empty);
      if (paramObject == null) {
        object1 = object2;
      } else {
        paramObject.setCdr(object2);
      } 
      paramObject = object2;
      object2 = pair.getCdr();
    } 
    return object1;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    if (paramObject1 instanceof Procedure) {
      paramObject1 = paramObject1;
      if (this.collect)
        return map1((Procedure)paramObject1, paramObject2); 
      forEach1((Procedure)paramObject1, paramObject2);
      return Values.empty;
    } 
    return applyN(new Object[] { paramObject1, paramObject2 });
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    Values values1;
    byte b;
    Values values2;
    Object[] arrayOfObject1;
    ApplyToArgs applyToArgs;
    int i = paramArrayOfObject.length - 1;
    if (i == 1 && paramArrayOfObject[0] instanceof Procedure) {
      Procedure procedure = (Procedure)paramArrayOfObject[0];
      if (this.collect)
        return map1(procedure, paramArrayOfObject[1]); 
      forEach1(procedure, paramArrayOfObject[1]);
      return Values.empty;
    } 
    Pair pair = null;
    if (this.collect) {
      LList lList = LList.Empty;
    } else {
      values2 = Values.empty;
    } 
    Object[] arrayOfObject2 = new Object[i];
    System.arraycopy(paramArrayOfObject, 1, arrayOfObject2, 0, i);
    if (paramArrayOfObject[0] instanceof Procedure) {
      b = 0;
      arrayOfObject1 = new Object[i];
      Procedure procedure = (Procedure)paramArrayOfObject[0];
      values1 = values2;
    } else {
      b = 1;
      arrayOfObject1 = new Object[i + 1];
      arrayOfObject1[0] = values1[0];
      applyToArgs = this.applyToArgs;
      values1 = values2;
    } 
    while (true) {
      int j = 0;
      while (j < i) {
        Pair pair1;
        Object object1 = arrayOfObject2[j];
        values2 = values1;
        if (object1 != LList.Empty) {
          pair1 = (Pair)object1;
          arrayOfObject1[b + j] = pair1.getCar();
          arrayOfObject2[j] = pair1.getCdr();
          j++;
          continue;
        } 
        return pair1;
      } 
      Object object = applyToArgs.applyN(arrayOfObject1);
      if (this.collect) {
        object = new Pair(object, LList.Empty);
        if (pair == null) {
          Object object2 = object;
        } else {
          pair.setCdr(object);
        } 
        Object object1 = object;
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/Map.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
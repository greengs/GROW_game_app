package gnu.kawa.functions;

import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;

public class Apply extends ProcedureN {
  ApplyToArgs applyToArgs;
  
  public Apply(String paramString, ApplyToArgs paramApplyToArgs) {
    super(paramString);
    this.applyToArgs = paramApplyToArgs;
  }
  
  public static Object[] getArguments(Object[] paramArrayOfObject, int paramInt, Procedure paramProcedure) {
    int i;
    int k = paramArrayOfObject.length;
    if (k < paramInt + 1)
      throw new WrongArguments("apply", 2, "(apply proc [args] args) [count:" + k + " skip:" + paramInt + "]"); 
    Object object2 = paramArrayOfObject[k - 1];
    if (object2 instanceof Object[]) {
      Object[] arrayOfObject1 = (Object[])object2;
      if (k == 2)
        return arrayOfObject1; 
      i = arrayOfObject1.length;
    } else if (object2 instanceof Sequence) {
      i = ((Sequence)object2).size();
    } else {
      i = -1;
    } 
    if (i < 0)
      throw new WrongType(paramProcedure, k, object2, "sequence or array"); 
    Object[] arrayOfObject = new Object[i + k - paramInt - 1];
    int j;
    for (j = 0; j < k - paramInt - 1; j++)
      arrayOfObject[j] = paramArrayOfObject[j + paramInt]; 
    paramInt = j;
    Object object1 = object2;
    k = i;
    if (object2 instanceof Object[]) {
      System.arraycopy(object2, 0, arrayOfObject, j, i);
      return arrayOfObject;
    } 
    while (object1 instanceof gnu.lists.Pair) {
      object1 = object1;
      arrayOfObject[paramInt] = object1.getCar();
      object1 = object1.getCdr();
      k--;
      paramInt++;
    } 
    if (k > 0) {
      object1 = object1;
      i = 0;
      while (true) {
        if (i < k) {
          arrayOfObject[paramInt] = object1.get(i);
          i++;
          paramInt++;
          continue;
        } 
        return arrayOfObject;
      } 
    } 
    return arrayOfObject;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    Object[] arrayOfObject = paramCallContext.getArgs();
    this.applyToArgs.checkN(getArguments(arrayOfObject, 0, (Procedure)this), paramCallContext);
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    return this.applyToArgs.applyN(getArguments(paramArrayOfObject, 0, (Procedure)this));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/Apply.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
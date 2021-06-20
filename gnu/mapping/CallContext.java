package gnu.mapping;

import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.math.IntNum;

public class CallContext {
  public static final int ARG_IN_IVALUE1 = 5;
  
  public static final int ARG_IN_IVALUE2 = 6;
  
  public static final int ARG_IN_VALUE1 = 1;
  
  public static final int ARG_IN_VALUE2 = 2;
  
  public static final int ARG_IN_VALUE3 = 3;
  
  public static final int ARG_IN_VALUE4 = 4;
  
  public static final int ARG_IN_VALUES_ARRAY = 0;
  
  static ThreadLocal currentContext = new ThreadLocal();
  
  public Consumer consumer = (Consumer)this.vstack;
  
  public int count;
  
  public Object[][] evalFrames;
  
  public int ivalue1;
  
  public int ivalue2;
  
  public int next;
  
  public int pc;
  
  public Procedure proc;
  
  public Object value1;
  
  public Object value2;
  
  public Object value3;
  
  public Object value4;
  
  public Object[] values;
  
  public ValueStack vstack = new ValueStack();
  
  public int where;
  
  public static CallContext getInstance() {
    CallContext callContext2 = getOnlyInstance();
    CallContext callContext1 = callContext2;
    if (callContext2 == null) {
      callContext1 = new CallContext();
      setInstance(callContext1);
    } 
    return callContext1;
  }
  
  public static CallContext getOnlyInstance() {
    return currentContext.get();
  }
  
  public static void setInstance(CallContext paramCallContext) {
    Thread.currentThread();
    currentContext.set(paramCallContext);
  }
  
  public final void cleanupFromContext(int paramInt) {
    ValueStack valueStack = this.vstack;
    char[] arrayOfChar = valueStack.data;
    int i = arrayOfChar[paramInt - 2] << 16 | arrayOfChar[paramInt - 1] & Character.MAX_VALUE;
    this.consumer = (Consumer)valueStack.objects[i];
    valueStack.objects[i] = null;
    valueStack.oindex = i;
    valueStack.gapStart = paramInt - 3;
  }
  
  Object getArgAsObject(int paramInt) {
    if (paramInt < 8) {
      switch (this.where >> paramInt * 4 & 0xF) {
        default:
          return this.values[paramInt];
        case 1:
          return this.value1;
        case 2:
          return this.value2;
        case 3:
          return this.value3;
        case 4:
          return this.value4;
        case 5:
          return IntNum.make(this.ivalue1);
        case 6:
          break;
      } 
      return IntNum.make(this.ivalue2);
    } 
  }
  
  public int getArgCount() {
    return this.count;
  }
  
  public Object[] getArgs() {
    if (this.where == 0)
      return this.values; 
    int j = this.count;
    this.next = 0;
    Object[] arrayOfObject = new Object[j];
    int i = 0;
    while (true) {
      Object[] arrayOfObject1 = arrayOfObject;
      if (i < j) {
        arrayOfObject[i] = getNextArg();
        i++;
        continue;
      } 
      return arrayOfObject1;
    } 
  }
  
  public final Object getFromContext(int paramInt) throws Throwable {
    runUntilDone();
    ValueStack valueStack = this.vstack;
    Object object = Values.make(valueStack, paramInt, valueStack.gapStart);
    cleanupFromContext(paramInt);
    return object;
  }
  
  public Object getNextArg() {
    if (this.next >= this.count)
      throw new WrongArguments(null, this.count); 
    int i = this.next;
    this.next = i + 1;
    return getArgAsObject(i);
  }
  
  public Object getNextArg(Object paramObject) {
    if (this.next >= this.count)
      return paramObject; 
    int i = this.next;
    this.next = i + 1;
    return getArgAsObject(i);
  }
  
  public int getNextIntArg() {
    if (this.next >= this.count)
      throw new WrongArguments(null, this.count); 
    int i = this.next;
    this.next = i + 1;
    return ((Number)getArgAsObject(i)).intValue();
  }
  
  public int getNextIntArg(int paramInt) {
    if (this.next >= this.count)
      return paramInt; 
    paramInt = this.next;
    this.next = paramInt + 1;
    return ((Number)getArgAsObject(paramInt)).intValue();
  }
  
  public final Object[] getRestArgsArray(int paramInt) {
    Object[] arrayOfObject = new Object[this.count - paramInt];
    int i = 0;
    while (paramInt < this.count) {
      arrayOfObject[i] = getArgAsObject(paramInt);
      i++;
      paramInt++;
    } 
    return arrayOfObject;
  }
  
  public final LList getRestArgsList(int paramInt) {
    Pair pair2;
    LList lList2 = LList.Empty;
    LList lList1 = lList2;
    for (Pair pair1 = null; paramInt < this.count; pair1 = pair) {
      Pair pair = new Pair(getArgAsObject(paramInt), lList2);
      if (pair1 == null) {
        pair2 = pair;
      } else {
        pair1.setCdr(pair);
      } 
      paramInt++;
    } 
    return (LList)pair2;
  }
  
  public void lastArg() {
    if (this.next < this.count)
      throw new WrongArguments(null, this.count); 
    this.values = null;
  }
  
  public void runUntilDone() throws Throwable {
    while (true) {
      Procedure procedure = this.proc;
      if (procedure == null)
        return; 
      this.proc = null;
      procedure.apply(this);
    } 
  }
  
  public final Object runUntilValue() throws Throwable {
    Consumer consumer = this.consumer;
    ValueStack valueStack = this.vstack;
    this.consumer = (Consumer)valueStack;
    int i = valueStack.gapStart;
    int j = valueStack.oindex;
    try {
      runUntilDone();
      return Values.make(valueStack, i, valueStack.gapStart);
    } finally {
      this.consumer = consumer;
      valueStack.gapStart = i;
      valueStack.oindex = j;
    } 
  }
  
  public final void runUntilValue(Consumer paramConsumer) throws Throwable {
    Consumer consumer = this.consumer;
    this.consumer = paramConsumer;
    try {
      runUntilDone();
      return;
    } finally {
      this.consumer = consumer;
    } 
  }
  
  public final int startFromContext() {
    ValueStack valueStack = this.vstack;
    int i = valueStack.find(this.consumer);
    valueStack.ensureSpace(3);
    int j = valueStack.gapStart;
    char[] arrayOfChar = valueStack.data;
    int k = j + 1;
    arrayOfChar[j] = '';
    valueStack.setIntN(k, i);
    i = k + 2;
    this.consumer = (Consumer)valueStack;
    valueStack.gapStart = i;
    return i;
  }
  
  public void writeValue(Object paramObject) {
    Values.writeValues(paramObject, this.consumer);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/CallContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
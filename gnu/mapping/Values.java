package gnu.mapping;

import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Iterator;
import java.util.List;

public class Values extends TreeList implements Printable, Externalizable {
  public static final Values empty;
  
  public static final Object[] noArgs = new Object[0];
  
  static {
    empty = new Values(noArgs);
  }
  
  public Values() {}
  
  public Values(Object[] paramArrayOfObject) {
    for (int i = 0; i < paramArrayOfObject.length; i++)
      writeObject(paramArrayOfObject[i]); 
  }
  
  public static int countValues(Object paramObject) {
    return (paramObject instanceof Values) ? ((Values)paramObject).size() : 1;
  }
  
  public static Values make() {
    return new Values();
  }
  
  public static Object make(TreeList paramTreeList) {
    return make(paramTreeList, 0, paramTreeList.data.length);
  }
  
  public static Object make(TreeList paramTreeList, int paramInt1, int paramInt2) {
    if (paramInt1 != paramInt2) {
      int i = paramTreeList.nextDataIndex(paramInt1);
      if (i > 0) {
        if (i == paramInt2 || paramTreeList.nextDataIndex(i) < 0)
          return paramTreeList.getPosNext(paramInt1 << 1); 
        Values values = new Values();
        paramTreeList.consumeIRange(paramInt1, paramInt2, (Consumer)values);
        return values;
      } 
    } 
    return empty;
  }
  
  public static Object make(List paramList) {
    int i;
    if (paramList == null) {
      i = 0;
    } else {
      i = paramList.size();
    } 
    if (i == 0)
      return empty; 
    if (i == 1)
      return paramList.get(0); 
    Values values = new Values();
    Iterator iterator = paramList.iterator();
    while (true) {
      Values values1 = values;
      if (iterator.hasNext()) {
        values.writeObject(iterator.next());
        continue;
      } 
      return values1;
    } 
  }
  
  public static Object make(Object[] paramArrayOfObject) {
    return (paramArrayOfObject.length == 1) ? paramArrayOfObject[0] : ((paramArrayOfObject.length == 0) ? empty : new Values(paramArrayOfObject));
  }
  
  public static int nextIndex(Object paramObject, int paramInt) {
    return (paramObject instanceof Values) ? ((Values)paramObject).nextDataIndex(paramInt) : ((paramInt == 0) ? 1 : -1);
  }
  
  public static Object nextValue(Object paramObject, int paramInt) {
    Object object = paramObject;
    if (paramObject instanceof Values) {
      object = paramObject;
      int i = paramInt;
      if (paramInt >= ((Values)object).gapEnd)
        i = paramInt - ((Values)object).gapEnd - ((Values)object).gapStart; 
      object = ((Values)paramObject).getPosNext(i << 1);
    } 
    return object;
  }
  
  public static Object values(Object... paramVarArgs) {
    return make(paramVarArgs);
  }
  
  public static void writeValues(Object paramObject, Consumer paramConsumer) {
    if (paramObject instanceof Values) {
      ((Values)paramObject).consume(paramConsumer);
      return;
    } 
    paramConsumer.writeObject(paramObject);
  }
  
  public Object call_with(Procedure paramProcedure) throws Throwable {
    return paramProcedure.applyN(toArray());
  }
  
  public final Object canonicalize() {
    Values values = this;
    if (this.gapEnd == this.data.length) {
      if (this.gapStart == 0)
        return empty; 
    } else {
      return values;
    } 
    values = this;
    return (nextDataIndex(0) == this.gapStart) ? getPosNext(0) : values;
  }
  
  public Object[] getValues() {
    return isEmpty() ? noArgs : toArray();
  }
  
  public void print(Consumer paramConsumer) {
    if (this == empty) {
      paramConsumer.write("#!void");
      return;
    } 
    int i = (toArray()).length;
    if (true)
      paramConsumer.write("#<values"); 
    for (i = 0;; i = k) {
      int k = nextDataIndex(i);
      if (k < 0) {
        if (true) {
          paramConsumer.write(62);
          return;
        } 
        return;
      } 
      paramConsumer.write(32);
      int j = i;
      if (i >= this.gapEnd)
        j = i - this.gapEnd - this.gapStart; 
      Object object = getPosNext(j << 1);
      if (object instanceof Printable) {
        ((Printable)object).print(paramConsumer);
      } else {
        paramConsumer.writeObject(object);
      } 
    } 
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    int j = paramObjectInput.readInt();
    for (int i = 0; i < j; i++)
      writeObject(paramObjectInput.readObject()); 
  }
  
  public Object readResolve() throws ObjectStreamException {
    Values values = this;
    if (isEmpty())
      values = empty; 
    return values;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    Object[] arrayOfObject = toArray();
    int j = arrayOfObject.length;
    paramObjectOutput.writeInt(j);
    for (int i = 0; i < j; i++)
      paramObjectOutput.writeObject(arrayOfObject[i]); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Values.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
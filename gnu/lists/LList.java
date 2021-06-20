package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Iterator;
import java.util.List;

public class LList extends ExtSequence implements Sequence, Externalizable, Comparable {
  public static final LList Empty = new LList();
  
  public static Pair chain1(Pair paramPair, Object paramObject) {
    paramObject = new Pair(paramObject, Empty);
    paramPair.cdr = paramObject;
    return (Pair)paramObject;
  }
  
  public static Pair chain4(Pair paramPair, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    paramObject4 = new Pair(paramObject4, Empty);
    paramPair.cdr = new Pair(paramObject1, new Pair(paramObject2, new Pair(paramObject3, paramObject4)));
    return (Pair)paramObject4;
  }
  
  public static Object checkNonList(Object paramObject) {
    Object object = paramObject;
    if (paramObject instanceof LList)
      object = "#<not a pair>"; 
    return object;
  }
  
  public static Object consX(Object[] paramArrayOfObject) {
    Object object = paramArrayOfObject[0];
    int j = paramArrayOfObject.length - 1;
    if (j <= 0)
      return object; 
    Pair pair = new Pair(object, null);
    object = pair;
    for (int i = 1; i < j; i++) {
      Pair pair1 = new Pair(paramArrayOfObject[i], null);
      ((Pair)object).cdr = pair1;
      object = pair1;
    } 
    ((Pair)object).cdr = paramArrayOfObject[j];
    return pair;
  }
  
  public static final int length(Object paramObject) {
    int i = 0;
    while (paramObject instanceof Pair) {
      i++;
      paramObject = ((Pair)paramObject).cdr;
    } 
    return i;
  }
  
  public static Pair list1(Object paramObject) {
    return new Pair(paramObject, Empty);
  }
  
  public static Pair list2(Object paramObject1, Object paramObject2) {
    return new Pair(paramObject1, new Pair(paramObject2, Empty));
  }
  
  public static Pair list3(Object paramObject1, Object paramObject2, Object paramObject3) {
    return new Pair(paramObject1, new Pair(paramObject2, new Pair(paramObject3, Empty)));
  }
  
  public static Pair list4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    return new Pair(paramObject1, new Pair(paramObject2, new Pair(paramObject3, new Pair(paramObject4, Empty))));
  }
  
  public static int listLength(Object paramObject, boolean paramBoolean) {
    int i = 0;
    Object object = paramObject;
    while (true) {
      if (paramObject == Empty)
        return i; 
      if (!(paramObject instanceof Pair)) {
        if (paramObject instanceof Sequence && paramBoolean) {
          int k = ((Sequence)paramObject).size();
          int j = k;
          if (k >= 0)
            j = k + i; 
          return j;
        } 
        return -2;
      } 
      Pair pair = (Pair)paramObject;
      if (pair.cdr == Empty)
        return i + 1; 
      if (paramObject == object && i > 0)
        return -1; 
      if (!(pair.cdr instanceof Pair)) {
        i++;
        paramObject = pair.cdr;
        continue;
      } 
      if (!(object instanceof Pair))
        return -2; 
      object = ((Pair)object).cdr;
      paramObject = ((Pair)pair.cdr).cdr;
      i += 2;
    } 
  }
  
  public static Object listTail(Object paramObject, int paramInt) {
    while (true) {
      if (--paramInt >= 0) {
        if (!(paramObject instanceof Pair))
          throw new IndexOutOfBoundsException("List is too short."); 
        paramObject = ((Pair)paramObject).cdr;
        continue;
      } 
      return paramObject;
    } 
  }
  
  public static LList makeList(List paramList) {
    Iterator iterator = paramList.iterator();
    List list2 = Empty;
    for (List list1 = null; iterator.hasNext(); list1 = paramList) {
      paramList = new Pair(iterator.next(), Empty);
      if (list1 == null) {
        list2 = paramList;
      } else {
        ((Pair)list1).cdr = paramList;
      } 
    } 
    return (LList)list2;
  }
  
  public static LList makeList(Object[] paramArrayOfObject, int paramInt) {
    LList lList = Empty;
    int i = paramArrayOfObject.length - paramInt;
    while (true) {
      if (--i >= 0) {
        lList = new Pair(paramArrayOfObject[paramInt + i], lList);
        continue;
      } 
      return lList;
    } 
  }
  
  public static LList makeList(Object[] paramArrayOfObject, int paramInt1, int paramInt2) {
    LList lList = Empty;
    while (true) {
      if (--paramInt2 >= 0) {
        lList = new Pair(paramArrayOfObject[paramInt1 + paramInt2], lList);
        continue;
      } 
      return lList;
    } 
  }
  
  public static LList reverseInPlace(Object paramObject) {
    LList lList = Empty;
    Object object = paramObject;
    for (paramObject = lList; object != Empty; paramObject = lList) {
      lList = (Pair)object;
      object = ((Pair)lList).cdr;
      ((Pair)lList).cdr = paramObject;
    } 
    return (LList)paramObject;
  }
  
  public int compareTo(Object paramObject) {
    return (paramObject == Empty) ? 0 : -1;
  }
  
  public void consume(Consumer paramConsumer) {
    Object object = this;
    paramConsumer.startElement("list");
    while (object instanceof Pair) {
      if (object != this)
        paramConsumer.write(32); 
      object = object;
      paramConsumer.writeObject(((Pair)object).car);
      object = ((Pair)object).cdr;
    } 
    if (object != Empty) {
      paramConsumer.write(32);
      paramConsumer.write(". ");
      paramConsumer.writeObject(checkNonList(object));
    } 
    paramConsumer.endElement();
  }
  
  public int createPos(int paramInt, boolean paramBoolean) {
    LListPosition lListPosition = new LListPosition(this, paramInt, paramBoolean);
    return PositionManager.manager.register(lListPosition);
  }
  
  public int createRelativePos(int paramInt1, int paramInt2, boolean paramBoolean) {
    boolean bool = isAfterPos(paramInt1);
    if (paramInt2 < 0 || paramInt1 == 0)
      return super.createRelativePos(paramInt1, paramInt2, paramBoolean); 
    if (paramInt2 == 0) {
      if (paramBoolean == bool)
        return copyPos(paramInt1); 
      if (paramBoolean && !bool)
        return super.createRelativePos(paramInt1, paramInt2, paramBoolean); 
    } 
    if (paramInt1 < 0)
      throw new IndexOutOfBoundsException(); 
    LListPosition lListPosition1 = (LListPosition)PositionManager.getPositionObject(paramInt1);
    if (lListPosition1.xpos == null)
      return super.createRelativePos(paramInt1, paramInt2, paramBoolean); 
    LListPosition lListPosition2 = new LListPosition(lListPosition1);
    Object object2 = lListPosition2.xpos;
    int j = lListPosition2.ipos;
    paramInt1 = j;
    int i = paramInt2;
    if (paramBoolean) {
      paramInt1 = j;
      i = paramInt2;
      if (!bool) {
        i = paramInt2 - 1;
        paramInt1 = j + 3;
      } 
    } 
    paramInt2 = paramInt1;
    Object object1 = object2;
    j = i;
    if (!paramBoolean) {
      paramInt2 = paramInt1;
      object1 = object2;
      j = i;
      if (bool) {
        j = i + 1;
        paramInt2 = paramInt1 - 3;
        object1 = object2;
      } 
    } 
    while (true) {
      if (!(object1 instanceof Pair))
        throw new IndexOutOfBoundsException(); 
      if (--j < 0) {
        lListPosition2.ipos = paramInt2;
        lListPosition2.xpos = object1;
        return PositionManager.manager.register(lListPosition2);
      } 
      object1 = object1;
      paramInt2 += 2;
      object1 = ((Pair)object1).cdr;
    } 
  }
  
  public boolean equals(Object paramObject) {
    return (this == paramObject);
  }
  
  public Object get(int paramInt) {
    throw new IndexOutOfBoundsException();
  }
  
  public SeqPosition getIterator(int paramInt) {
    return new LListPosition(this, paramInt, false);
  }
  
  public Object getPosNext(int paramInt) {
    return eofValue;
  }
  
  public Object getPosPrevious(int paramInt) {
    return eofValue;
  }
  
  public boolean hasNext(int paramInt) {
    return false;
  }
  
  public boolean isEmpty() {
    return true;
  }
  
  public int nextPos(int paramInt) {
    return 0;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {}
  
  public Object readResolve() throws ObjectStreamException {
    return Empty;
  }
  
  protected void setPosNext(int paramInt, Object paramObject) {
    if (paramInt <= 0) {
      if (paramInt == -1 || !(this instanceof Pair))
        throw new IndexOutOfBoundsException(); 
      ((Pair)this).car = paramObject;
      return;
    } 
    PositionManager.getPositionObject(paramInt).setNext(paramObject);
  }
  
  protected void setPosPrevious(int paramInt, Object paramObject) {
    if (paramInt <= 0) {
      if (paramInt == 0 || !(this instanceof Pair))
        throw new IndexOutOfBoundsException(); 
      (((Pair)this).lastPair()).car = paramObject;
      return;
    } 
    PositionManager.getPositionObject(paramInt).setPrevious(paramObject);
  }
  
  public int size() {
    return 0;
  }
  
  public String toString() {
    LList lList = this;
    int i = 0;
    StringBuffer stringBuffer = new StringBuffer(100);
    stringBuffer.append('(');
    while (true) {
      if (lList != Empty) {
        Object object;
        if (i)
          stringBuffer.append(' '); 
        if (i >= 10) {
          stringBuffer.append("...");
          stringBuffer.append(')');
          return stringBuffer.toString();
        } 
        if (lList instanceof Pair) {
          lList = lList;
          stringBuffer.append(((Pair)lList).car);
          object = ((Pair)lList).cdr;
          i++;
          continue;
        } 
        stringBuffer.append(". ");
        stringBuffer.append(checkNonList(object));
      } 
      stringBuffer.append(')');
      return stringBuffer.toString();
    } 
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/LList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
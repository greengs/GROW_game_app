package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Pair extends LList implements Externalizable {
  protected Object car;
  
  protected Object cdr;
  
  public Pair() {}
  
  public Pair(Object paramObject1, Object paramObject2) {
    this.car = paramObject1;
    this.cdr = paramObject2;
  }
  
  public static int compareTo(Pair paramPair1, Pair paramPair2) {
    Object object1;
    Object object2;
    if (paramPair1 == paramPair2)
      return 0; 
    if (paramPair1 == null)
      return -1; 
    Pair pair = paramPair1;
    paramPair1 = paramPair2;
    if (paramPair2 == null)
      return 1; 
    while (true) {
      object2 = pair.car;
      Object object = paramPair1.car;
      int j = ((Comparable<Comparable>)object2).compareTo((Comparable)object);
      int i = j;
      if (j == 0) {
        object2 = pair.cdr;
        object1 = paramPair1.cdr;
        if (object2 == object1)
          return 0; 
        if (object2 == null)
          return -1; 
        if (object1 == null)
          return 1; 
        if (!(object2 instanceof Pair) || !(object1 instanceof Pair))
          break; 
        pair = (Pair)object2;
        object1 = object1;
        continue;
      } 
      return i;
    } 
    return ((Comparable<Comparable>)object2).compareTo((Comparable)object1);
  }
  
  public static boolean equals(Pair paramPair1, Pair paramPair2) {
    if (paramPair1 != paramPair2) {
      if (paramPair1 != null) {
        Pair pair = paramPair1;
        paramPair1 = paramPair2;
        if (paramPair2 != null) {
          Object object1;
          Object object2;
          while (true) {
            object2 = pair.car;
            Object object = paramPair1.car;
            if (object2 != object && (object2 == null || !object2.equals(object)))
              return false; 
            object2 = pair.cdr;
            object1 = paramPair1.cdr;
            if (object2 != object1) {
              if (object2 == null || object1 == null)
                return false; 
              if (!(object2 instanceof Pair) || !(object1 instanceof Pair))
                break; 
              pair = (Pair)object2;
              object1 = object1;
              continue;
            } 
            return true;
          } 
          return object2.equals(object1);
        } 
      } 
      return false;
    } 
    return true;
  }
  
  public static Pair make(Object paramObject1, Object paramObject2) {
    return new Pair(paramObject1, paramObject2);
  }
  
  public int compareTo(Object paramObject) {
    return (paramObject == Empty) ? 1 : compareTo(this, (Pair)paramObject);
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject != null && paramObject instanceof Pair) ? equals(this, (Pair)paramObject) : false;
  }
  
  public Object get(int paramInt) {
    int i;
    Pair pair = this;
    while (true) {
      i = paramInt;
      if (paramInt > 0) {
        paramInt--;
        if (pair.cdr instanceof Pair) {
          pair = (Pair)pair.cdr;
          continue;
        } 
        i = paramInt;
        if (pair.cdr instanceof Sequence)
          return ((Sequence)pair.cdr).get(paramInt); 
      } 
      break;
    } 
    if (i == 0)
      return pair.car; 
    throw new IndexOutOfBoundsException();
  }
  
  public Object getCar() {
    return this.car;
  }
  
  public Object getCdr() {
    return this.cdr;
  }
  
  public Object getPosNext(int paramInt) {
    return (paramInt <= 0) ? ((paramInt == 0) ? this.car : eofValue) : PositionManager.getPositionObject(paramInt).getNext();
  }
  
  public Object getPosPrevious(int paramInt) {
    return (paramInt <= 0) ? ((paramInt == 0) ? eofValue : (lastPair()).car) : PositionManager.getPositionObject(paramInt).getPrevious();
  }
  
  public boolean hasNext(int paramInt) {
    return (paramInt <= 0) ? ((paramInt == 0)) : PositionManager.getPositionObject(paramInt).hasNext();
  }
  
  public int hashCode() {
    int i = 1;
    Object object = this;
    while (object instanceof Pair) {
      int k;
      object = object;
      Object object1 = ((Pair)object).car;
      if (object1 == null) {
        k = 0;
      } else {
        k = object1.hashCode();
      } 
      i = i * 31 + k;
      object = ((Pair)object).cdr;
    } 
    int j = i;
    if (object != LList.Empty) {
      j = i;
      if (object != null)
        j = i ^ object.hashCode(); 
    } 
    return j;
  }
  
  public boolean isEmpty() {
    return false;
  }
  
  public final Pair lastPair() {
    Pair pair = this;
    while (true) {
      Object object = pair.cdr;
      if (object instanceof Pair) {
        pair = (Pair)object;
        continue;
      } 
      return pair;
    } 
  }
  
  public int length() {
    int i = 0;
    Pair pair1 = this;
    Pair pair2 = this;
    while (true) {
      if (pair1 == Empty)
        return i; 
      if (!(pair1 instanceof Pair)) {
        if (pair1 instanceof Sequence) {
          int k = pair1.size();
          int j = k;
          if (k >= 0)
            j = k + i; 
          return j;
        } 
        return -2;
      } 
      Pair pair = pair1;
      if (pair.cdr == Empty)
        return i + 1; 
      if (pair1 == pair2 && i > 0)
        return -1; 
      if (!(pair.cdr instanceof Pair)) {
        i++;
        Object object = pair.cdr;
        continue;
      } 
      if (!(pair2 instanceof Pair))
        return -2; 
      Object object2 = pair2.cdr;
      Object object1 = ((Pair)pair.cdr).cdr;
      i += 2;
    } 
  }
  
  public int nextPos(int paramInt) {
    if (paramInt <= 0)
      return (paramInt < 0) ? 0 : PositionManager.manager.register(new LListPosition(this, 1, true)); 
    if (!((LListPosition)PositionManager.getPositionObject(paramInt)).gotoNext())
      paramInt = 0; 
    return paramInt;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.car = paramObjectInput.readObject();
    this.cdr = paramObjectInput.readObject();
  }
  
  public Object readResolve() throws ObjectStreamException {
    return this;
  }
  
  public void setCar(Object paramObject) {
    this.car = paramObject;
  }
  
  public void setCdr(Object paramObject) {
    this.cdr = paramObject;
  }
  
  public void setCdrBackdoor(Object paramObject) {
    this.cdr = paramObject;
  }
  
  public int size() {
    int i = listLength(this, true);
    if (i >= 0)
      return i; 
    if (i == -1)
      return Integer.MAX_VALUE; 
    throw new RuntimeException("not a true list");
  }
  
  public Object[] toArray() {
    int k = size();
    Object[] arrayOfObject = new Object[k];
    int i = 0;
    Sequence sequence = this;
    while (i < k && sequence instanceof Pair) {
      sequence = sequence;
      arrayOfObject[i] = ((Pair)sequence).car;
      sequence = (Sequence)((Pair)sequence).cdr;
      i++;
    } 
    for (int j = i; j < k; j++)
      arrayOfObject[j] = sequence.get(j - i); 
    return arrayOfObject;
  }
  
  public Object[] toArray(Object[] paramArrayOfObject) {
    int i = paramArrayOfObject.length;
    int m = length();
    int k = i;
    if (m > i) {
      paramArrayOfObject = new Object[m];
      k = m;
    } 
    i = 0;
    Sequence sequence = this;
    while (i < m && sequence instanceof Pair) {
      sequence = sequence;
      paramArrayOfObject[i] = ((Pair)sequence).car;
      sequence = (Sequence)((Pair)sequence).cdr;
      i++;
    } 
    for (int j = i; j < m; j++)
      paramArrayOfObject[j] = sequence.get(j - i); 
    if (m < k)
      paramArrayOfObject[m] = null; 
    return paramArrayOfObject;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.car);
    paramObjectOutput.writeObject(this.cdr);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/Pair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
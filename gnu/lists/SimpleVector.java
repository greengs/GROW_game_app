package gnu.lists;

import java.util.Collection;
import java.util.Iterator;

public abstract class SimpleVector extends AbstractSequence implements Sequence, Array {
  public int size;
  
  protected static int compareToInt(SimpleVector paramSimpleVector1, SimpleVector paramSimpleVector2) {
    int i;
    int k = paramSimpleVector1.size;
    int m = paramSimpleVector2.size;
    if (k > m) {
      i = m;
    } else {
      i = k;
    } 
    for (int j = 0; j < i; j++) {
      int n = paramSimpleVector1.intAtBuffer(j);
      int i1 = paramSimpleVector2.intAtBuffer(j);
      if (11 != i1)
        return (n > i1) ? 1 : -1; 
    } 
    return k - m;
  }
  
  protected static int compareToLong(SimpleVector paramSimpleVector1, SimpleVector paramSimpleVector2) {
    int i;
    int k = paramSimpleVector1.size;
    int m = paramSimpleVector2.size;
    if (k > m) {
      i = m;
    } else {
      i = k;
    } 
    for (int j = 0; j < i; j++) {
      long l1 = paramSimpleVector1.longAtBuffer(j);
      long l2 = paramSimpleVector2.longAtBuffer(j);
      if (l1 != l2)
        return (l1 > l2) ? 1 : -1; 
    } 
    return k - m;
  }
  
  public void add(int paramInt, Object paramObject) {
    int i = 16;
    int j = this.size + 1;
    this.size = j;
    int k = getBufferLength();
    if (j > k) {
      if (k >= 16)
        i = k * 2; 
      setBufferLength(i);
    } 
    this.size = j;
    if (this.size != paramInt)
      shift(paramInt, paramInt + 1, this.size - paramInt); 
    set(paramInt, paramObject);
  }
  
  public boolean add(Object paramObject) {
    add(this.size, paramObject);
    return true;
  }
  
  public boolean addAll(int paramInt, Collection paramCollection) {
    boolean bool = false;
    int i = paramCollection.size();
    setSize(this.size + i);
    shift(paramInt, paramInt + i, this.size - i - paramInt);
    Iterator iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      set(paramInt, iterator.next());
      bool = true;
      paramInt++;
    } 
    return bool;
  }
  
  protected int addPos(int paramInt, Object paramObject) {
    paramInt >>>= 1;
    add(paramInt, paramObject);
    return (paramInt << 1) + 3;
  }
  
  public void clear() {
    setSize(0);
  }
  
  protected abstract void clearBuffer(int paramInt1, int paramInt2);
  
  public void consume(int paramInt1, int paramInt2, Consumer paramConsumer) {
    consumePosRange(paramInt1 << 1, paramInt1 + paramInt2 << 1, paramConsumer);
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer) {
    paramInt >>>= 1;
    if (paramInt >= this.size)
      return false; 
    paramConsumer.writeObject(getBuffer(paramInt));
    return true;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer) {
    if (!paramConsumer.ignoring()) {
      int i = paramInt1 >>> 1;
      int j = paramInt2 >>> 1;
      paramInt1 = j;
      paramInt2 = i;
      if (j > this.size) {
        paramInt1 = this.size;
        paramInt2 = i;
      } 
      while (true) {
        if (paramInt2 < paramInt1) {
          paramConsumer.writeObject(getBuffer(paramInt2));
          paramInt2++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public int createPos(int paramInt, boolean paramBoolean) {
    if (paramBoolean) {
      boolean bool1 = true;
      return bool1 | paramInt << 1;
    } 
    boolean bool = false;
    return bool | paramInt << 1;
  }
  
  public void fill(int paramInt1, int paramInt2, Object paramObject) {
    if (paramInt1 < 0 || paramInt2 > this.size)
      throw new IndexOutOfBoundsException(); 
    while (paramInt1 < paramInt2) {
      setBuffer(paramInt1, paramObject);
      paramInt1++;
    } 
  }
  
  public void fill(Object paramObject) {
    int i = this.size;
    while (true) {
      if (--i >= 0) {
        setBuffer(i, paramObject);
        continue;
      } 
      break;
    } 
  }
  
  public void fillPosRange(int paramInt1, int paramInt2, Object paramObject) {
    if (paramInt1 == -1) {
      paramInt1 = this.size;
    } else {
      paramInt1 >>>= 1;
    } 
    if (paramInt2 == -1) {
      paramInt2 = this.size;
    } else {
      paramInt2 >>>= 1;
    } 
    while (paramInt1 < paramInt2) {
      setBuffer(paramInt1, paramObject);
      paramInt1++;
    } 
  }
  
  public Object get(int paramInt) {
    if (paramInt >= this.size)
      throw new IndexOutOfBoundsException(); 
    return getBuffer(paramInt);
  }
  
  protected abstract Object getBuffer();
  
  protected abstract Object getBuffer(int paramInt);
  
  public abstract int getBufferLength();
  
  public int getElementKind() {
    return 32;
  }
  
  public int getNextKind(int paramInt) {
    return hasNext(paramInt) ? getElementKind() : 0;
  }
  
  public Object getPosNext(int paramInt) {
    paramInt >>>= 1;
    return (paramInt >= this.size) ? eofValue : getBuffer(paramInt);
  }
  
  public Object getRowMajor(int paramInt) {
    return get(paramInt);
  }
  
  public String getTag() {
    return null;
  }
  
  public int intAt(int paramInt) {
    if (paramInt >= this.size)
      throw new IndexOutOfBoundsException(); 
    return intAtBuffer(paramInt);
  }
  
  public int intAtBuffer(int paramInt) {
    return Convert.toInt(getBuffer(paramInt));
  }
  
  protected boolean isAfterPos(int paramInt) {
    return ((paramInt & 0x1) != 0);
  }
  
  public long longAt(int paramInt) {
    if (paramInt >= this.size)
      throw new IndexOutOfBoundsException(); 
    return longAtBuffer(paramInt);
  }
  
  public long longAtBuffer(int paramInt) {
    return Convert.toLong(getBuffer(paramInt));
  }
  
  protected int nextIndex(int paramInt) {
    return (paramInt == -1) ? this.size : (paramInt >>> 1);
  }
  
  public int nextPos(int paramInt) {
    if (paramInt != -1) {
      paramInt >>>= 1;
      if (paramInt != this.size)
        return (paramInt << 1) + 3; 
    } 
    return 0;
  }
  
  public Object remove(int paramInt) {
    if (paramInt < 0 || paramInt >= this.size)
      throw new IndexOutOfBoundsException(); 
    Object object = get(paramInt);
    shift(paramInt + 1, paramInt, 1);
    this.size--;
    clearBuffer(this.size, 1);
    return object;
  }
  
  public boolean remove(Object paramObject) {
    int i = indexOf(paramObject);
    if (i < 0)
      return false; 
    shift(i + 1, i, 1);
    this.size--;
    clearBuffer(this.size, 1);
    return true;
  }
  
  public boolean removeAll(Collection paramCollection) {
    boolean bool = false;
    int j = 0;
    for (int i = 0; i < this.size; i++) {
      Object object = get(i);
      if (paramCollection.contains(object)) {
        bool = true;
      } else {
        if (bool)
          set(j, object); 
        j++;
      } 
    } 
    setSize(j);
    return bool;
  }
  
  public void removePos(int paramInt1, int paramInt2) {
    int i = paramInt1 >>> 1;
    paramInt1 = i;
    if (i > this.size)
      paramInt1 = this.size; 
    if (paramInt2 >= 0) {
      i = paramInt1;
      paramInt1 += paramInt2;
    } else {
      i = paramInt1 + paramInt2;
      paramInt2 = -paramInt2;
    } 
    if (i < 0 || paramInt1 >= this.size)
      throw new IndexOutOfBoundsException(); 
    shift(paramInt1, i, this.size - paramInt1);
    this.size -= paramInt2;
    clearBuffer(this.size, paramInt2);
  }
  
  protected void removePosRange(int paramInt1, int paramInt2) {
    int i = paramInt1 >>> 1;
    paramInt2 >>>= 1;
    if (i >= paramInt2)
      return; 
    paramInt1 = paramInt2;
    if (paramInt2 > this.size)
      paramInt1 = this.size; 
    shift(paramInt1, i, this.size - paramInt1);
    paramInt1 -= i;
    this.size -= paramInt1;
    clearBuffer(this.size, paramInt1);
  }
  
  protected void resizeShift(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = paramInt4 - paramInt3;
    int j = getBufferLength();
    int k = j - paramInt2 - paramInt1 + i;
    if (k > j) {
      setBufferLength(k);
      this.size = k;
    } 
    int m = paramInt1 - paramInt3;
    if (m >= 0) {
      paramInt1 = j - paramInt2;
      shift(paramInt2, k - paramInt1, paramInt1);
      if (m > 0)
        shift(paramInt3, paramInt4, m); 
    } else {
      k -= paramInt4;
      shift(j - k, paramInt4, k);
      shift(paramInt2, paramInt1, paramInt3 - paramInt1);
    } 
    clearBuffer(paramInt3, i);
  }
  
  public boolean retainAll(Collection paramCollection) {
    boolean bool = false;
    int j = 0;
    for (int i = 0; i < this.size; i++) {
      Object object = get(i);
      if (!paramCollection.contains(object)) {
        bool = true;
      } else {
        if (bool)
          set(j, object); 
        j++;
      } 
    } 
    setSize(j);
    return bool;
  }
  
  public Object set(int paramInt, Object paramObject) {
    if (paramInt >= this.size)
      throw new IndexOutOfBoundsException(); 
    Object object = getBuffer(paramInt);
    setBuffer(paramInt, paramObject);
    return object;
  }
  
  protected abstract Object setBuffer(int paramInt, Object paramObject);
  
  public abstract void setBufferLength(int paramInt);
  
  public void setSize(int paramInt) {
    int i = 16;
    int j = this.size;
    this.size = paramInt;
    if (paramInt < j) {
      clearBuffer(paramInt, j - paramInt);
      return;
    } 
    j = getBufferLength();
    if (paramInt > j) {
      if (j >= 16)
        i = j * 2; 
      if (paramInt <= i)
        paramInt = i; 
      setBufferLength(paramInt);
      return;
    } 
  }
  
  public void shift(int paramInt1, int paramInt2, int paramInt3) {
    Object object = getBuffer();
    System.arraycopy(object, paramInt1, object, paramInt2, paramInt3);
  }
  
  public final int size() {
    return this.size;
  }
  
  public Array transpose(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt, int[] paramArrayOfint3) {
    GeneralArray generalArray = new GeneralArray();
    generalArray.strides = paramArrayOfint3;
    generalArray.dimensions = paramArrayOfint2;
    generalArray.lowBounds = paramArrayOfint1;
    generalArray.offset = paramInt;
    generalArray.base = this;
    generalArray.simple = false;
    return generalArray;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/SimpleVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
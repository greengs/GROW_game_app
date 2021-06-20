package gnu.lists;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class AbstractSequence {
  public static int compare(AbstractSequence paramAbstractSequence1, int paramInt1, AbstractSequence paramAbstractSequence2, int paramInt2) {
    return (paramAbstractSequence1 == paramAbstractSequence2) ? paramAbstractSequence1.compare(paramInt1, paramInt2) : paramAbstractSequence1.stableCompare(paramAbstractSequence2);
  }
  
  public static RuntimeException unsupportedException(String paramString) {
    return new UnsupportedOperationException(paramString);
  }
  
  public void add(int paramInt, Object paramObject) {
    paramInt = createPos(paramInt, false);
    addPos(paramInt, paramObject);
    releasePos(paramInt);
  }
  
  public boolean add(Object paramObject) {
    addPos(endPos(), paramObject);
    return true;
  }
  
  public boolean addAll(int paramInt, Collection paramCollection) {
    boolean bool = false;
    paramInt = createPos(paramInt, false);
    Iterator iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      paramInt = addPos(paramInt, iterator.next());
      bool = true;
    } 
    releasePos(paramInt);
    return bool;
  }
  
  public boolean addAll(Collection paramCollection) {
    return addAll(size(), paramCollection);
  }
  
  protected int addPos(int paramInt, Object paramObject) {
    throw unsupported("addPos");
  }
  
  public void clear() {
    removePos(startPos(), endPos());
  }
  
  public int compare(int paramInt1, int paramInt2) {
    paramInt1 = nextIndex(paramInt1);
    paramInt2 = nextIndex(paramInt2);
    return (paramInt1 < paramInt2) ? -1 : ((paramInt1 > paramInt2) ? 1 : 0);
  }
  
  public final int compare(SeqPosition paramSeqPosition1, SeqPosition paramSeqPosition2) {
    return compare(paramSeqPosition1.ipos, paramSeqPosition2.ipos);
  }
  
  public void consume(Consumer paramConsumer) {
    boolean bool = this instanceof Sequence;
    if (bool)
      paramConsumer.startElement("#sequence"); 
    consumePosRange(startPos(), endPos(), paramConsumer);
    if (bool)
      paramConsumer.endElement(); 
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer) {
    int i = nextPos(paramInt);
    if (i == 0)
      return false; 
    consumePosRange(paramInt, i, paramConsumer);
    return true;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer) {
    if (paramConsumer.ignoring())
      return; 
    for (paramInt1 = copyPos(paramInt1); !equals(paramInt1, paramInt2); paramInt1 = nextPos(paramInt1)) {
      if (!hasNext(paramInt1))
        throw new RuntimeException(); 
      paramConsumer.writeObject(getPosNext(paramInt1));
    } 
    releasePos(paramInt1);
  }
  
  public boolean contains(Object paramObject) {
    return (indexOf(paramObject) >= 0);
  }
  
  public boolean containsAll(Collection paramCollection) {
    Iterator iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      if (!contains(iterator.next()))
        return false; 
    } 
    return true;
  }
  
  public int copyPos(int paramInt) {
    return paramInt;
  }
  
  public abstract int createPos(int paramInt, boolean paramBoolean);
  
  public int createRelativePos(int paramInt1, int paramInt2, boolean paramBoolean) {
    return createPos(nextIndex(paramInt1) + paramInt2, paramBoolean);
  }
  
  public final Enumeration elements() {
    return getIterator();
  }
  
  public int endPos() {
    return -1;
  }
  
  public boolean equals(int paramInt1, int paramInt2) {
    return (compare(paramInt1, paramInt2) == 0);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool1 = true;
    boolean bool2 = false;
    if (!(this instanceof List) || !(paramObject instanceof List)) {
      if (this != paramObject)
        bool1 = false; 
      return bool1;
    } 
    Iterator<Object> iterator = iterator();
    paramObject = ((List)paramObject).iterator();
    while (true) {
      boolean bool = iterator.hasNext();
      bool1 = bool2;
      if (bool == paramObject.hasNext()) {
        if (!bool)
          return true; 
        Object object1 = iterator.next();
        Object object2 = paramObject.next();
        if (object1 == null) {
          if (object2 != null)
            return false; 
          continue;
        } 
        if (!object1.equals(object2))
          return false; 
        continue;
      } 
      return bool1;
    } 
  }
  
  public void fill(int paramInt1, int paramInt2, Object paramObject) {
    paramInt1 = createPos(paramInt1, false);
    paramInt2 = createPos(paramInt2, true);
    while (compare(paramInt1, paramInt2) < 0) {
      setPosNext(paramInt1, paramObject);
      paramInt1 = nextPos(paramInt1);
    } 
    releasePos(paramInt1);
    releasePos(paramInt2);
  }
  
  public void fill(Object paramObject) {
    int i = startPos();
    while (true) {
      i = nextPos(i);
      if (i != 0) {
        setPosPrevious(i, paramObject);
        continue;
      } 
      break;
    } 
  }
  
  public void fillPosRange(int paramInt1, int paramInt2, Object paramObject) {
    for (paramInt1 = copyPos(paramInt1); compare(paramInt1, paramInt2) < 0; paramInt1 = nextPos(paramInt1))
      setPosNext(paramInt1, paramObject); 
    releasePos(paramInt1);
  }
  
  public int firstAttributePos(int paramInt) {
    return 0;
  }
  
  public int firstChildPos(int paramInt) {
    return 0;
  }
  
  public int firstChildPos(int paramInt, ItemPredicate paramItemPredicate) {
    int i = firstChildPos(paramInt);
    if (i == 0)
      return 0; 
    paramInt = i;
    return !paramItemPredicate.isInstancePos(this, i) ? nextMatching(i, paramItemPredicate, endPos(), false) : paramInt;
  }
  
  protected int fromEndIndex(int paramInt) {
    return size() - nextIndex(paramInt);
  }
  
  public abstract Object get(int paramInt);
  
  public Object get(int[] paramArrayOfint) {
    return get(paramArrayOfint[0]);
  }
  
  public Object getAttribute(int paramInt) {
    return null;
  }
  
  public int getAttributeLength() {
    return 0;
  }
  
  protected int getContainingSequenceSize(int paramInt) {
    return size();
  }
  
  public int getEffectiveIndex(int[] paramArrayOfint) {
    return paramArrayOfint[0];
  }
  
  protected int getIndexDifference(int paramInt1, int paramInt2) {
    return nextIndex(paramInt1) - nextIndex(paramInt2);
  }
  
  public final SeqPosition getIterator() {
    return getIterator(0);
  }
  
  public SeqPosition getIterator(int paramInt) {
    return new SeqPosition(this, paramInt, false);
  }
  
  public SeqPosition getIteratorAtPos(int paramInt) {
    return new SeqPosition(this, copyPos(paramInt));
  }
  
  public int getLowBound(int paramInt) {
    return 0;
  }
  
  public int getNextKind(int paramInt) {
    return hasNext(paramInt) ? 32 : 0;
  }
  
  public String getNextTypeName(int paramInt) {
    return null;
  }
  
  public Object getNextTypeObject(int paramInt) {
    return null;
  }
  
  public Object getPosNext(int paramInt) {
    return !hasNext(paramInt) ? Sequence.eofValue : get(nextIndex(paramInt));
  }
  
  public Object getPosPrevious(int paramInt) {
    paramInt = nextIndex(paramInt);
    return (paramInt <= 0) ? Sequence.eofValue : get(paramInt - 1);
  }
  
  public int getSize(int paramInt) {
    return (paramInt == 0) ? size() : 1;
  }
  
  protected boolean gotoAttributesStart(TreePosition paramTreePosition) {
    return false;
  }
  
  public final boolean gotoChildrenStart(TreePosition paramTreePosition) {
    int i = firstChildPos(paramTreePosition.getPos());
    if (i == 0)
      return false; 
    paramTreePosition.push(this, i);
    return true;
  }
  
  protected boolean gotoParent(TreePosition paramTreePosition) {
    if (paramTreePosition.depth < 0)
      return false; 
    paramTreePosition.pop();
    return true;
  }
  
  public boolean hasNext(int paramInt) {
    return (nextIndex(paramInt) != size());
  }
  
  protected boolean hasPrevious(int paramInt) {
    return (nextIndex(paramInt) != 0);
  }
  
  public int hashCode() {
    int i = 1;
    int j = startPos();
    while (true) {
      int k = nextPos(j);
      if (k != 0) {
        Object object = getPosPrevious(k);
        if (object == null) {
          j = 0;
        } else {
          j = object.hashCode();
        } 
        i = i * 31 + j;
        j = k;
        continue;
      } 
      return i;
    } 
  }
  
  public int indexOf(Object paramObject) {
    int i = 0;
    int j = startPos();
    while (true) {
      j = nextPos(j);
      if (j != 0) {
        Object object = getPosPrevious(j);
        if ((paramObject == null) ? (object == null) : paramObject.equals(object)) {
          releasePos(j);
          return i;
        } 
        i++;
        continue;
      } 
      return -1;
    } 
  }
  
  protected boolean isAfterPos(int paramInt) {
    return ((paramInt & 0x1) != 0);
  }
  
  public boolean isEmpty() {
    return (size() == 0);
  }
  
  public final Iterator iterator() {
    return getIterator();
  }
  
  public int lastIndexOf(Object paramObject) {
    int i = size();
    while (true) {
      int j = i - 1;
      if (j >= 0) {
        Object object = get(j);
        if (paramObject == null) {
          i = j;
          if (object == null)
            return j; 
          continue;
        } 
        i = j;
        if (paramObject.equals(object))
          return j; 
        continue;
      } 
      return -1;
    } 
  }
  
  public final ListIterator listIterator() {
    return getIterator(0);
  }
  
  public final ListIterator listIterator(int paramInt) {
    return getIterator(paramInt);
  }
  
  protected int nextIndex(int paramInt) {
    return getIndexDifference(paramInt, startPos());
  }
  
  public final int nextIndex(SeqPosition paramSeqPosition) {
    return nextIndex(paramSeqPosition.ipos);
  }
  
  public int nextMatching(int paramInt1, ItemPredicate paramItemPredicate, int paramInt2, boolean paramBoolean) {
    if (paramBoolean)
      throw unsupported("nextMatching with descend"); 
    while (true) {
      if (compare(paramInt1, paramInt2) >= 0)
        return 0; 
      int i = nextPos(paramInt1);
      paramInt1 = i;
      if (paramItemPredicate.isInstancePos(this, i))
        return i; 
    } 
  }
  
  public int nextPos(int paramInt) {
    if (!hasNext(paramInt))
      return 0; 
    int i = createRelativePos(paramInt, 1, true);
    releasePos(paramInt);
    return i;
  }
  
  public int parentPos(int paramInt) {
    return endPos();
  }
  
  public int previousPos(int paramInt) {
    if (!hasPrevious(paramInt))
      return 0; 
    int i = createRelativePos(paramInt, -1, false);
    releasePos(paramInt);
    return i;
  }
  
  public int rank() {
    return 1;
  }
  
  protected void releasePos(int paramInt) {}
  
  public Object remove(int paramInt) {
    if (paramInt < 0 || paramInt >= size())
      throw new IndexOutOfBoundsException(); 
    paramInt = createPos(paramInt, false);
    Object object = getPosNext(paramInt);
    removePos(paramInt, 1);
    releasePos(paramInt);
    return object;
  }
  
  public boolean remove(Object paramObject) {
    int i = indexOf(paramObject);
    if (i < 0)
      return false; 
    i = createPos(i, false);
    removePos(i, 1);
    releasePos(i);
    return true;
  }
  
  public boolean removeAll(Collection paramCollection) {
    boolean bool = false;
    int i = startPos();
    while (true) {
      int j = nextPos(i);
      if (j != 0) {
        i = j;
        if (paramCollection.contains(getPosPrevious(j))) {
          removePos(j, -1);
          bool = true;
          i = j;
        } 
        continue;
      } 
      return bool;
    } 
  }
  
  public void removePos(int paramInt1, int paramInt2) {
    int i = createRelativePos(paramInt1, paramInt2, false);
    if (paramInt2 >= 0) {
      removePosRange(paramInt1, i);
    } else {
      removePosRange(i, paramInt1);
    } 
    releasePos(i);
  }
  
  protected void removePosRange(int paramInt1, int paramInt2) {
    throw unsupported("removePosRange");
  }
  
  public boolean retainAll(Collection paramCollection) {
    boolean bool = false;
    int i = startPos();
    while (true) {
      int j = nextPos(i);
      if (j != 0) {
        i = j;
        if (!paramCollection.contains(getPosPrevious(j))) {
          removePos(j, -1);
          bool = true;
          i = j;
        } 
        continue;
      } 
      return bool;
    } 
  }
  
  public Object set(int paramInt, Object paramObject) {
    throw unsupported("set");
  }
  
  public Object set(int[] paramArrayOfint, Object paramObject) {
    return set(paramArrayOfint[0], paramObject);
  }
  
  protected void setPosNext(int paramInt, Object paramObject) {
    paramInt = nextIndex(paramInt);
    if (paramInt >= size())
      throw new IndexOutOfBoundsException(); 
    set(paramInt, paramObject);
  }
  
  protected void setPosPrevious(int paramInt, Object paramObject) {
    paramInt = nextIndex(paramInt);
    if (paramInt == 0)
      throw new IndexOutOfBoundsException(); 
    set(paramInt - 1, paramObject);
  }
  
  public abstract int size();
  
  public int stableCompare(AbstractSequence paramAbstractSequence) {
    int i = System.identityHashCode(this);
    int j = System.identityHashCode(paramAbstractSequence);
    return (i < j) ? -1 : ((i > j) ? 1 : 0);
  }
  
  public int startPos() {
    return 0;
  }
  
  public List subList(int paramInt1, int paramInt2) {
    return subSequencePos(createPos(paramInt1, false), createPos(paramInt2, true));
  }
  
  public Sequence subSequence(SeqPosition paramSeqPosition1, SeqPosition paramSeqPosition2) {
    return subSequencePos(paramSeqPosition1.ipos, paramSeqPosition2.ipos);
  }
  
  protected Sequence subSequencePos(int paramInt1, int paramInt2) {
    return new SubSequence(this, paramInt1, paramInt2);
  }
  
  public Object[] toArray() {
    Object[] arrayOfObject = new Object[size()];
    int j = startPos();
    int i = 0;
    while (true) {
      j = nextPos(j);
      if (j != 0) {
        arrayOfObject[i] = getPosPrevious(j);
        i++;
        continue;
      } 
      return arrayOfObject;
    } 
  }
  
  public Object[] toArray(Object[] paramArrayOfObject) {
    int j = paramArrayOfObject.length;
    int m = size();
    int i = j;
    Object[] arrayOfObject = paramArrayOfObject;
    if (m > j) {
      arrayOfObject = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), m);
      i = m;
    } 
    int k = startPos();
    j = 0;
    while (true) {
      k = nextPos(k);
      if (k != 0) {
        arrayOfObject[j] = getPosPrevious(k);
        j++;
        continue;
      } 
      if (m < i)
        arrayOfObject[m] = null; 
      return arrayOfObject;
    } 
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(100);
    if (this instanceof Sequence)
      stringBuffer.append('['); 
    toString(", ", stringBuffer);
    if (this instanceof Sequence)
      stringBuffer.append(']'); 
    return stringBuffer.toString();
  }
  
  public void toString(String paramString, StringBuffer paramStringBuffer) {
    boolean bool = false;
    int i = startPos();
    while (true) {
      i = nextPos(i);
      if (i != 0) {
        if (bool) {
          paramStringBuffer.append(paramString);
        } else {
          bool = true;
        } 
        paramStringBuffer.append(getPosPrevious(i));
        continue;
      } 
      break;
    } 
  }
  
  protected RuntimeException unsupported(String paramString) {
    return unsupportedException(getClass().getName() + " does not implement " + paramString);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/AbstractSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
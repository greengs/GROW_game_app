package gnu.xquery.util;

import gnu.kawa.functions.NumberCompare;
import gnu.kawa.xml.KNode;
import gnu.lists.FilterConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;

public class OrderedTuples extends FilterConsumer {
  Procedure body;
  
  Object[] comps;
  
  int first;
  
  int n;
  
  int[] next;
  
  Object[] tuples = new Object[10];
  
  OrderedTuples() {
    super(null);
  }
  
  public static OrderedTuples make$V(Procedure paramProcedure, Object[] paramArrayOfObject) {
    OrderedTuples orderedTuples = new OrderedTuples();
    orderedTuples.comps = paramArrayOfObject;
    orderedTuples.body = paramProcedure;
    return orderedTuples;
  }
  
  int cmp(int paramInt1, int paramInt2) throws Throwable {
    for (int i = 0; i < this.comps.length; i += 3) {
      int j;
      Procedure procedure = (Procedure)this.comps[i];
      String str = (String)this.comps[i + 1];
      NamedCollator namedCollator2 = (NamedCollator)this.comps[i + 2];
      NamedCollator namedCollator1 = namedCollator2;
      if (namedCollator2 == null)
        namedCollator1 = NamedCollator.codepointCollation; 
      Object object1 = procedure.applyN((Object[])this.tuples[paramInt1]);
      Object object3 = procedure.applyN((Object[])this.tuples[paramInt2]);
      Object object2 = KNode.atomicValue(object1);
      object3 = KNode.atomicValue(object3);
      object1 = object2;
      if (object2 instanceof gnu.kawa.xml.UntypedAtomic)
        object1 = object2.toString(); 
      object2 = object3;
      if (object3 instanceof gnu.kawa.xml.UntypedAtomic)
        object2 = object3.toString(); 
      boolean bool2 = SequenceUtils.isEmptySequence(object1);
      boolean bool1 = SequenceUtils.isEmptySequence(object2);
      if (bool2 && bool1)
        continue; 
      if (bool2 || bool1) {
        if (str.charAt(1) == 'L') {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        if (bool2 == bool1) {
          j = -1;
        } else {
          j = 1;
        } 
      } else {
        byte b;
        if (object1 instanceof Number && Double.isNaN(((Number)object1).doubleValue())) {
          j = 1;
        } else {
          j = 0;
        } 
        if (object2 instanceof Number && Double.isNaN(((Number)object2).doubleValue())) {
          b = 1;
        } else {
          b = 0;
        } 
        if (!j || !b) {
          if (j || b) {
            if (str.charAt(1) == 'L') {
              b = 1;
            } else {
              b = 0;
            } 
            if (j == b) {
              j = -1;
            } else {
              j = 1;
            } 
          } else if (object1 instanceof Number && object2 instanceof Number) {
            j = NumberCompare.compare(object1, object2, false);
          } else {
            j = namedCollator1.compare(object1.toString(), object2.toString());
          } 
        } else {
          continue;
        } 
      } 
      if (j != 0)
        return (str.charAt(0) == 'A') ? j : -j; 
      continue;
    } 
    return 0;
  }
  
  void emit(int paramInt, CallContext paramCallContext) throws Throwable {
    Object[] arrayOfObject = (Object[])this.tuples[paramInt];
    this.body.checkN(arrayOfObject, paramCallContext);
    paramCallContext.runUntilDone();
  }
  
  void emit(CallContext paramCallContext) throws Throwable {
    for (int i = this.first; i >= 0; i = this.next[i])
      emit(i, paramCallContext); 
  }
  
  int listsort(int paramInt) throws Throwable {
    if (this.n == 0)
      return -1; 
    this.next = new int[this.n];
    for (int i = 1;; i++) {
      if (i == this.n) {
        this.next[i - 1] = -1;
        int j = 1;
        while (true) {
          i = -1;
          int m = -1;
          int k = 0;
          while (paramInt >= 0) {
            int i3 = k + 1;
            int n = paramInt;
            int i1 = 0;
            int i2 = 0;
            while (true) {
              k = i1;
              int i4 = n;
              if (i2 < j) {
                k = i1 + 1;
                i4 = this.next[n];
                if (i4 >= 0) {
                  i2++;
                  i1 = k;
                  n = i4;
                  continue;
                } 
              } 
              n = j;
              i2 = i;
              i1 = m;
              i = i4;
              i4 = k;
              k = paramInt;
              while (i4 > 0 || (n > 0 && i >= 0)) {
                if (i4 == 0) {
                  paramInt = i;
                  i = this.next[i];
                  n--;
                } else if (n == 0 || i < 0) {
                  paramInt = k;
                  k = this.next[k];
                  i4--;
                } else if (cmp(k, i) <= 0) {
                  paramInt = k;
                  k = this.next[k];
                  i4--;
                } else {
                  paramInt = i;
                  i = this.next[i];
                  n--;
                } 
                if (i1 >= 0) {
                  this.next[i1] = paramInt;
                } else {
                  i2 = paramInt;
                } 
                i1 = paramInt;
              } 
              break;
            } 
            paramInt = i;
            k = i3;
            m = i1;
            i = i2;
          } 
          this.next[m] = -1;
          if (k <= 1)
            return i; 
          j *= 2;
          paramInt = i;
        } 
      } 
      this.next[i - 1] = i;
    } 
  }
  
  public void run$X(CallContext paramCallContext) throws Throwable {
    this.first = listsort(0);
    emit(paramCallContext);
  }
  
  public void writeObject(Object paramObject) {
    if (this.n >= this.tuples.length) {
      Object[] arrayOfObject1 = new Object[this.n * 2];
      System.arraycopy(this.tuples, 0, arrayOfObject1, 0, this.n);
      this.tuples = arrayOfObject1;
    } 
    Object[] arrayOfObject = this.tuples;
    int i = this.n;
    this.n = i + 1;
    arrayOfObject[i] = paramObject;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/OrderedTuples.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package com.google.appinventor.components.runtime.collect;

import java.util.ArrayList;
import java.util.Collections;

public class Lists {
  public static <E> ArrayList<E> newArrayList() {
    return new ArrayList<E>();
  }
  
  public static <E> ArrayList<E> newArrayList(E... paramVarArgs) {
    ArrayList<? super E> arrayList = new ArrayList(paramVarArgs.length * 110 / 100 + 5);
    Collections.addAll(arrayList, paramVarArgs);
    return (ArrayList)arrayList;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/collect/Lists.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
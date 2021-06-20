package com.google.appinventor.components.runtime.collect;

import java.util.Collections;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Sets {
  public static <K> HashSet<K> newHashSet() {
    return new HashSet<K>();
  }
  
  public static <E> HashSet<E> newHashSet(E... paramVarArgs) {
    HashSet<? super E> hashSet = new HashSet(paramVarArgs.length * 4 / 3 + 1);
    Collections.addAll(hashSet, paramVarArgs);
    return (HashSet)hashSet;
  }
  
  public static <E> SortedSet<E> newSortedSet(E... paramVarArgs) {
    TreeSet<? super E> treeSet = new TreeSet();
    Collections.addAll(treeSet, paramVarArgs);
    return (SortedSet)treeSet;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/collect/Sets.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
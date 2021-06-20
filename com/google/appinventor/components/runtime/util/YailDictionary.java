package com.google.appinventor.components.runtime.util;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import gnu.lists.LList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

public class YailDictionary extends LinkedHashMap<Object, Object> implements YailObject<YailList> {
  public static final Object ALL = new Object() {
      public String toString() {
        return "ALL_ITEMS";
      }
    };
  
  private static final String LOG_TAG = "YailDictionary";
  
  public YailDictionary() {}
  
  public YailDictionary(Map<Object, Object> paramMap) {
    super(paramMap);
  }
  
  private static Object alistLookup(YailList paramYailList, Object paramObject) {
    for (YailList yailList : paramYailList.getCdr()) {
      if (yailList instanceof YailList) {
        if (((YailList)yailList).getObject(0).equals(paramObject))
          return yailList.getObject(1); 
        continue;
      } 
      return null;
    } 
    return null;
  }
  
  public static YailDictionary alistToDict(YailList paramYailList) {
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    for (YailList yailList : paramYailList.getCdr()) {
      Object object1 = yailList.getObject(0);
      Object object2 = yailList.getObject(1);
      if (object2 instanceof YailList && isAlist((YailList)object2).booleanValue()) {
        linkedHashMap.put(object1, alistToDict((YailList)object2));
        continue;
      } 
      if (object2 instanceof YailList) {
        linkedHashMap.put(object1, checkList((YailList)object2));
        continue;
      } 
      linkedHashMap.put(object1, object2);
    } 
    return new YailDictionary(linkedHashMap);
  }
  
  private static Collection<Object> allOf(Object paramObject) {
    return (paramObject instanceof Map) ? allOf((Map<Object, Object>)paramObject) : ((paramObject instanceof List) ? allOf((List<Object>)paramObject) : Collections.emptyList());
  }
  
  private static Collection<Object> allOf(List<Object> paramList) {
    Collection<Object> collection;
    if (paramList instanceof YailList) {
      if (isAlist((YailList)paramList).booleanValue()) {
        ArrayList<Object> arrayList = new ArrayList();
        Iterator<YailList> iterator = ((LList)((YailList)paramList).getCdr()).iterator();
        while (true) {
          paramList = arrayList;
          if (iterator.hasNext()) {
            arrayList.add(((YailList)iterator.next()).getObject(1));
            continue;
          } 
          break;
        } 
      } else {
        collection = (Collection)((YailList)paramList).getCdr();
      } 
      return collection;
    } 
    return collection;
  }
  
  private static Collection<Object> allOf(Map<Object, Object> paramMap) {
    return paramMap.values();
  }
  
  private static YailList checkList(YailList paramYailList) {
    Object[] arrayOfObject = new Object[paramYailList.size()];
    int i = 0;
    Iterator<T> iterator = paramYailList.iterator();
    iterator.next();
    boolean bool = false;
    while (iterator.hasNext()) {
      T t = iterator.next();
      if (t instanceof YailList) {
        if (isAlist((YailList)t).booleanValue()) {
          arrayOfObject[i] = alistToDict((YailList)t);
          bool = true;
        } else {
          arrayOfObject[i] = checkList((YailList)t);
          if (arrayOfObject[i] != t)
            bool = true; 
        } 
      } else {
        arrayOfObject[i] = t;
      } 
      i++;
    } 
    if (bool)
      paramYailList = YailList.makeList(arrayOfObject); 
    return paramYailList;
  }
  
  private static YailList checkListForDicts(YailList paramYailList) {
    ArrayList<YailList> arrayList = new ArrayList();
    for (YailDictionary yailDictionary : paramYailList.getCdr()) {
      if (yailDictionary instanceof YailDictionary) {
        arrayList.add(dictToAlist(yailDictionary));
        continue;
      } 
      if (yailDictionary instanceof YailList) {
        arrayList.add(checkListForDicts((YailList)yailDictionary));
        continue;
      } 
      arrayList.add(yailDictionary);
    } 
    return YailList.makeList(arrayList);
  }
  
  public static YailList dictToAlist(YailDictionary paramYailDictionary) {
    ArrayList<YailList> arrayList = new ArrayList();
    for (Map.Entry<Object, Object> entry : paramYailDictionary.entrySet()) {
      arrayList.add(YailList.makeList(new Object[] { entry.getKey(), entry.getValue() }));
    } 
    return YailList.makeList(arrayList);
  }
  
  private Object getFromList(List<?> paramList, Object paramObject) {
    byte b;
    if (paramList instanceof YailList) {
      b = 0;
    } else {
      b = 1;
    } 
    try {
      if (paramObject instanceof gnu.lists.FString)
        return paramList.get(Integer.parseInt(paramObject.toString()) - b); 
      if (paramObject instanceof String)
        return paramList.get(Integer.parseInt((String)paramObject) - b); 
      if (paramObject instanceof Number)
        return paramList.get(((Number)paramObject).intValue() - b); 
    } catch (NumberFormatException numberFormatException) {
      Log.w("YailDictionary", "Unable to parse key as integer: " + paramObject, numberFormatException);
      throw new YailRuntimeError("Unable to parse key as integer: " + paramObject, "NumberParseException");
    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
      Log.w("YailDictionary", "Requested too large of an index: " + paramObject, indexOutOfBoundsException);
      throw new YailRuntimeError("Requested too large of an index: " + paramObject, "IndexOutOfBoundsException");
    } 
    return null;
  }
  
  private static Boolean isAlist(YailList paramYailList) {
    boolean bool = false;
    for (YailList yailList : paramYailList.getCdr()) {
      if (!(yailList instanceof YailList))
        return Boolean.valueOf(false); 
      if (((YailList)yailList).size() != 2)
        return Boolean.valueOf(false); 
      bool = true;
    } 
    return Boolean.valueOf(bool);
  }
  
  private static int keyToIndex(List<?> paramList, Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: instanceof com/google/appinventor/components/runtime/util/YailList
    //   4: ifeq -> 101
    //   7: iconst_0
    //   8: istore_2
    //   9: aload_1
    //   10: instanceof java/lang/Number
    //   13: ifeq -> 106
    //   16: aload_1
    //   17: checkcast java/lang/Number
    //   20: invokevirtual intValue : ()I
    //   23: istore_3
    //   24: iload_3
    //   25: iload_2
    //   26: isub
    //   27: istore_3
    //   28: iload_3
    //   29: iflt -> 46
    //   32: iload_3
    //   33: aload_0
    //   34: invokeinterface size : ()I
    //   39: iconst_1
    //   40: iadd
    //   41: iload_2
    //   42: isub
    //   43: if_icmplt -> 140
    //   46: new com/google/appinventor/components/runtime/errors/DispatchableError
    //   49: dup
    //   50: sipush #3201
    //   53: iconst_2
    //   54: anewarray java/lang/Object
    //   57: dup
    //   58: iconst_0
    //   59: iload_3
    //   60: iload_2
    //   61: iadd
    //   62: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   65: aastore
    //   66: dup
    //   67: iconst_1
    //   68: aload_0
    //   69: invokestatic getJsonRepresentation : (Ljava/lang/Object;)Ljava/lang/String;
    //   72: aastore
    //   73: invokespecial <init> : (I[Ljava/lang/Object;)V
    //   76: athrow
    //   77: astore_0
    //   78: ldc 'YailDictionary'
    //   80: ldc 'Unable to serialize object as JSON'
    //   82: aload_0
    //   83: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   86: pop
    //   87: new com/google/appinventor/components/runtime/errors/YailRuntimeError
    //   90: dup
    //   91: aload_0
    //   92: invokevirtual getMessage : ()Ljava/lang/String;
    //   95: ldc 'JSON Error'
    //   97: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   100: athrow
    //   101: iconst_1
    //   102: istore_2
    //   103: goto -> 9
    //   106: aload_1
    //   107: invokevirtual toString : ()Ljava/lang/String;
    //   110: invokestatic parseInt : (Ljava/lang/String;)I
    //   113: istore_3
    //   114: goto -> 24
    //   117: astore_0
    //   118: new com/google/appinventor/components/runtime/errors/DispatchableError
    //   121: dup
    //   122: sipush #3202
    //   125: iconst_1
    //   126: anewarray java/lang/Object
    //   129: dup
    //   130: iconst_0
    //   131: aload_1
    //   132: invokevirtual toString : ()Ljava/lang/String;
    //   135: aastore
    //   136: invokespecial <init> : (I[Ljava/lang/Object;)V
    //   139: athrow
    //   140: iload_3
    //   141: ireturn
    // Exception table:
    //   from	to	target	type
    //   46	77	77	org/json/JSONException
    //   106	114	117	java/lang/NumberFormatException
  }
  
  private Object lookupTargetForKey(Object paramObject1, Object paramObject2) {
    if (paramObject1 instanceof YailDictionary)
      return ((YailDictionary)paramObject1).get(paramObject2); 
    if (paramObject1 instanceof List)
      return ((List)paramObject1).get(keyToIndex((List)paramObject1, paramObject2)); 
    if (paramObject1 == null) {
      paramObject1 = "null";
      throw new DispatchableError(3203, new Object[] { paramObject1 });
    } 
    paramObject1 = paramObject1.getClass().getSimpleName();
    throw new DispatchableError(3203, new Object[] { paramObject1 });
  }
  
  public static YailDictionary makeDictionary() {
    return new YailDictionary();
  }
  
  public static YailDictionary makeDictionary(List<YailList> paramList) {
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    for (YailList yailList : paramList) {
      Object object1 = yailList.getObject(0);
      Object object2 = yailList.getObject(1);
      if (object2 instanceof YailList) {
        if (isAlist((YailList)object2).booleanValue()) {
          linkedHashMap.put(object1, alistToDict((YailList)object2));
          continue;
        } 
        linkedHashMap.put(object1, checkList((YailList)object2));
        continue;
      } 
      linkedHashMap.put(object1, object2);
    } 
    return new YailDictionary(linkedHashMap);
  }
  
  public static YailDictionary makeDictionary(Map<Object, Object> paramMap) {
    return new YailDictionary(paramMap);
  }
  
  public static YailDictionary makeDictionary(Object... paramVarArgs) {
    if (paramVarArgs.length % 2 == 1)
      throw new IllegalArgumentException("Expected an even number of key-value entries."); 
    YailDictionary yailDictionary = new YailDictionary();
    for (int i = 0; i < paramVarArgs.length; i += 2)
      yailDictionary.put(paramVarArgs[i], paramVarArgs[i + 1]); 
    return yailDictionary;
  }
  
  public static <T> List<Object> walkKeyPath(YailObject<?> paramYailObject, List<T> paramList) {
    return walkKeyPath(paramYailObject, paramList, new ArrayList());
  }
  
  private static <T> List<Object> walkKeyPath(Object paramObject, List<T> paramList, List<Object> paramList1) {
    if (paramList.isEmpty()) {
      if (paramObject != null)
        paramList1.add(paramObject); 
      return paramList1;
    } 
    if (paramObject != null) {
      T t = paramList.get(0);
      paramList = paramList.subList(1, paramList.size());
      if (t == ALL) {
        paramObject = allOf(paramObject).iterator();
        while (true) {
          if (paramObject.hasNext()) {
            walkKeyPath(paramObject.next(), paramList, paramList1);
            continue;
          } 
          return paramList1;
        } 
      } 
      if (paramObject instanceof Map) {
        walkKeyPath(((Map)paramObject).get(t), paramList, paramList1);
        return paramList1;
      } 
      if (paramObject instanceof YailList && isAlist((YailList)paramObject).booleanValue()) {
        paramObject = alistLookup((YailList)paramObject, t);
        if (paramObject != null) {
          walkKeyPath(paramObject, paramList, paramList1);
          return paramList1;
        } 
        return paramList1;
      } 
      if (paramObject instanceof List) {
        int i = keyToIndex((List)paramObject, t);
        try {
          walkKeyPath(((List)paramObject).get(i), paramList, paramList1);
          return paramList1;
        } catch (Exception exception) {
          return paramList1;
        } 
      } 
    } 
    return paramList1;
  }
  
  public boolean containsKey(Object paramObject) {
    return (paramObject instanceof gnu.lists.FString) ? super.containsKey(paramObject.toString()) : super.containsKey(paramObject);
  }
  
  public boolean containsValue(Object paramObject) {
    return (paramObject instanceof gnu.lists.FString) ? super.containsValue(paramObject.toString()) : super.containsValue(paramObject);
  }
  
  public Object get(Object paramObject) {
    return (paramObject instanceof gnu.lists.FString) ? super.get(paramObject.toString()) : super.get(paramObject);
  }
  
  public Object getObject(int paramInt) {
    if (paramInt < 0 || paramInt >= size())
      throw new IndexOutOfBoundsException(); 
    for (Map.Entry<Object, Object> entry : entrySet()) {
      if (paramInt == 0)
        return Lists.newArrayList(new Object[] { entry.getKey(), entry.getValue() }); 
      paramInt--;
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public Object getObjectAtKeyPath(List<?> paramList) {
    YailDictionary yailDictionary2 = this;
    Iterator<?> iterator = paramList.iterator();
    YailDictionary yailDictionary1 = yailDictionary2;
    while (true) {
      yailDictionary2 = yailDictionary1;
      if (iterator.hasNext()) {
        Object object;
        yailDictionary2 = (YailDictionary)iterator.next();
        if (yailDictionary1 instanceof Map) {
          yailDictionary1 = (YailDictionary)yailDictionary1.get(yailDictionary2);
          continue;
        } 
        if (yailDictionary1 instanceof YailList && isAlist((YailList)yailDictionary1).booleanValue()) {
          object = alistToDict((YailList)yailDictionary1).get(yailDictionary2);
          continue;
        } 
        if (object instanceof List) {
          object = getFromList((List)object, yailDictionary2);
          continue;
        } 
        yailDictionary2 = null;
      } 
      break;
    } 
    return yailDictionary2;
  }
  
  @NonNull
  public Iterator<YailList> iterator() {
    return new DictIterator(entrySet().iterator());
  }
  
  public Object put(Object paramObject1, Object paramObject2) {
    Object object = paramObject1;
    if (paramObject1 instanceof gnu.lists.FString)
      object = paramObject1.toString(); 
    paramObject1 = paramObject2;
    if (paramObject2 instanceof gnu.lists.FString)
      paramObject1 = paramObject2.toString(); 
    return super.put(object, paramObject1);
  }
  
  public Object remove(Object paramObject) {
    return (paramObject instanceof gnu.lists.FString) ? super.remove(paramObject.toString()) : super.remove(paramObject);
  }
  
  public void setPair(YailList paramYailList) {
    put(paramYailList.getObject(0), paramYailList.getObject(1));
  }
  
  public void setValueForKeyPath(List<?> paramList, Object paramObject) {
    Iterator<?> iterator = paramList.iterator();
    if (!paramList.isEmpty()) {
      YailDictionary yailDictionary = this;
      while (true) {
        if (iterator.hasNext()) {
          Object object1;
          Object object2 = iterator.next();
          if (iterator.hasNext()) {
            object1 = lookupTargetForKey(yailDictionary, object2);
            continue;
          } 
          if (object1 instanceof YailDictionary) {
            ((YailDictionary)object1).put(object2, paramObject);
            continue;
          } 
          if (object1 instanceof YailList) {
            ((LList)object1).getIterator(keyToIndex((List)object1, object2)).set(paramObject);
            continue;
          } 
          if (object1 instanceof List) {
            ((List<Object>)object1).set(keyToIndex((List)object1, object2), paramObject);
            continue;
          } 
          throw new DispatchableError(3203);
        } 
        return;
      } 
    } 
  }
  
  public String toString() {
    try {
      return JsonUtil.getJsonRepresentation(this);
    } catch (JSONException jSONException) {
      throw new YailRuntimeError(jSONException.getMessage(), "JSON Error");
    } 
  }
  
  private static class DictIterator implements Iterator<YailList> {
    final Iterator<Map.Entry<Object, Object>> it;
    
    DictIterator(Iterator<Map.Entry<Object, Object>> param1Iterator) {
      this.it = param1Iterator;
    }
    
    public boolean hasNext() {
      return this.it.hasNext();
    }
    
    public YailList next() {
      Map.Entry entry = this.it.next();
      return YailList.makeList(new Object[] { entry.getKey(), entry.getValue() });
    }
    
    public void remove() {
      this.it.remove();
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/YailDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
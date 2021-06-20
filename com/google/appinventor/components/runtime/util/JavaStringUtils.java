package com.google.appinventor.components.runtime.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaStringUtils {
  private static final boolean DEBUG = false;
  
  public static final String LOG_TAG_JOIN_STRINGS = "JavaJoinListOfStrings";
  
  private static final MappingOrder mappingOrderDictionary = new MappingOrder();
  
  private static final MappingOrder mappingOrderEarliestOccurrence;
  
  private static final MappingOrder mappingOrderLongestStringFirst = new MappingLongestStringFirstOrder();
  
  private static final Comparator<Range> rangeComparator;
  
  static {
    mappingOrderEarliestOccurrence = new MappingEarliestOccurrenceFirstOrder();
    rangeComparator = new RangeComparator();
  }
  
  private static String applyMappings(String paramString, Map<String, String> paramMap, List<String> paramList) {
    TreeSet<Range> treeSet = new TreeSet<Range>(rangeComparator);
    for (String str : paramList) {
      Matcher matcher = Pattern.compile(Pattern.quote(str)).matcher(paramString);
      str = paramMap.get(str);
      while (matcher.find())
        treeSet.add(new Range(matcher.start(), matcher.end(), str)); 
    } 
    for (Range range : treeSet) {
      String str1 = paramString.substring(0, range.start);
      String str2 = range.text;
      paramString = paramString.substring(range.end);
      paramString = str1 + str2 + paramString;
    } 
    return paramString;
  }
  
  private static String join(List<Object> paramList, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    boolean bool = true;
    for (Object object : paramList) {
      if (bool) {
        bool = false;
      } else {
        stringBuilder.append(paramString);
      } 
      stringBuilder.append(object.toString());
    } 
    return stringBuilder.toString();
  }
  
  public static String joinStrings(List<Object> paramList, String paramString) {
    return join(paramList, paramString);
  }
  
  public static String replaceAllMappings(String paramString, Map<Object, Object> paramMap, MappingOrder paramMappingOrder) {
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    ArrayList<String> arrayList = new ArrayList();
    while (iterator.hasNext()) {
      Map.Entry entry = iterator.next();
      String str1 = entry.getKey().toString();
      String str2 = entry.getValue().toString();
      if (!hashMap.containsKey(str1))
        arrayList.add(str1); 
      hashMap.put(str1, str2);
    } 
    paramMappingOrder.changeOrder(arrayList, paramString);
    return applyMappings(paramString, (Map)hashMap, arrayList);
  }
  
  public static String replaceAllMappingsDictionaryOrder(String paramString, Map<Object, Object> paramMap) {
    return replaceAllMappings(paramString, paramMap, mappingOrderDictionary);
  }
  
  public static String replaceAllMappingsEarliestOccurrenceOrder(String paramString, Map<Object, Object> paramMap) {
    return replaceAllMappings(paramString, paramMap, mappingOrderEarliestOccurrence);
  }
  
  public static String replaceAllMappingsLongestStringOrder(String paramString, Map<Object, Object> paramMap) {
    return replaceAllMappings(paramString, paramMap, mappingOrderLongestStringFirst);
  }
  
  private static class MappingEarliestOccurrenceFirstOrder extends MappingOrder {
    private MappingEarliestOccurrenceFirstOrder() {}
    
    public void changeOrder(List<String> param1List, String param1String) {
      final HashMap<Object, Object> occurrenceIndices = new HashMap<Object, Object>();
      for (String str : param1List) {
        int j = param1String.indexOf(str);
        int i = j;
        if (j == -1)
          i = param1String.length() + hashMap.size(); 
        hashMap.put(str, Integer.valueOf(i));
      } 
      Collections.sort(param1List, new Comparator<String>() {
            public int compare(String param2String1, String param2String2) {
              int i = ((Integer)occurrenceIndices.get(param2String1)).intValue();
              int j = ((Integer)occurrenceIndices.get(param2String2)).intValue();
              return (i == j) ? Integer.compare(param2String2.length(), param2String1.length()) : Integer.compare(i, j);
            }
          });
    }
  }
  
  class null implements Comparator<String> {
    public int compare(String param1String1, String param1String2) {
      int i = ((Integer)occurrenceIndices.get(param1String1)).intValue();
      int j = ((Integer)occurrenceIndices.get(param1String2)).intValue();
      return (i == j) ? Integer.compare(param1String2.length(), param1String1.length()) : Integer.compare(i, j);
    }
  }
  
  private static class MappingLongestStringFirstOrder extends MappingOrder {
    private MappingLongestStringFirstOrder() {}
    
    public void changeOrder(List<String> param1List, String param1String) {
      Collections.sort(param1List, new Comparator<String>() {
            public int compare(String param2String1, String param2String2) {
              return Integer.compare(param2String2.length(), param2String1.length());
            }
          });
    }
  }
  
  class null implements Comparator<String> {
    public int compare(String param1String1, String param1String2) {
      return Integer.compare(param1String2.length(), param1String1.length());
    }
  }
  
  private static class MappingOrder {
    private MappingOrder() {}
    
    public void changeOrder(List<String> param1List, String param1String) {}
  }
  
  private static class Range {
    int end;
    
    int start;
    
    String text;
    
    public Range(int param1Int1, int param1Int2, String param1String) {
      this.start = param1Int1;
      this.end = param1Int2;
      this.text = param1String;
    }
  }
  
  private static class RangeComparator implements Comparator<Range> {
    private RangeComparator() {}
    
    public int compare(JavaStringUtils.Range param1Range1, JavaStringUtils.Range param1Range2) {
      return (Math.max(param1Range1.start, param1Range2.start) < Math.min(param1Range1.end, param1Range2.end)) ? 0 : Integer.compare(param1Range2.end, param1Range1.end);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/JavaStringUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
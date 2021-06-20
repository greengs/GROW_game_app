package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import gnu.math.IntFraction;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonUtil {
  private static final String BINFILE_DIR = "/AppInventorBinaries";
  
  private static final String LOG_TAG = "JsonUtil";
  
  @Deprecated
  public static Object convertJsonItem(Object paramObject) throws JSONException {
    return convertJsonItem(paramObject, false);
  }
  
  public static Object convertJsonItem(Object<Object> paramObject, boolean paramBoolean) throws JSONException {
    if (paramObject == null)
      return "null"; 
    if (paramObject instanceof JSONObject)
      return paramBoolean ? getDictionaryFromJsonObject((JSONObject)paramObject) : getListFromJsonObject((JSONObject)paramObject); 
    if (paramObject instanceof JSONArray) {
      List<Object> list = getListFromJsonArray((JSONArray)paramObject, paramBoolean);
      paramObject = (Object<Object>)list;
      return paramBoolean ? YailList.makeList(list) : paramObject;
    } 
    return (paramObject.equals(Boolean.FALSE) || (paramObject instanceof String && ((String)paramObject).equalsIgnoreCase("false"))) ? Boolean.valueOf(false) : ((paramObject.equals(Boolean.TRUE) || (paramObject instanceof String && ((String)paramObject).equalsIgnoreCase("true"))) ? Boolean.valueOf(true) : ((paramObject instanceof Number) ? paramObject : paramObject.toString()));
  }
  
  public static String encodeJsonObject(Object paramObject) throws IllegalArgumentException {
    try {
      return getJsonRepresentation(paramObject);
    } catch (JSONException jSONException) {
      throw new IllegalArgumentException("jsonObject is not a legal JSON object");
    } 
  }
  
  public static YailDictionary getDictionaryFromJsonObject(JSONObject paramJSONObject) throws JSONException {
    YailDictionary yailDictionary = new YailDictionary();
    TreeSet treeSet = new TreeSet();
    Iterator iterator = paramJSONObject.keys();
    while (iterator.hasNext())
      treeSet.add(iterator.next()); 
    for (String str : treeSet)
      yailDictionary.put(str, convertJsonItem(paramJSONObject.get(str), true)); 
    return yailDictionary;
  }
  
  public static String getJsonRepresentation(Object<Map.Entry<Object, Object>> paramObject) throws JSONException {
    if (paramObject == null || paramObject.equals(null))
      return "null"; 
    if (paramObject instanceof gnu.lists.FString)
      return JSONObject.quote(paramObject.toString()); 
    if (paramObject instanceof YailList)
      return ((YailList)paramObject).toJSONString(); 
    if (paramObject instanceof IntFraction)
      return JSONObject.numberToString(Double.valueOf(((IntFraction)paramObject).doubleValue())); 
    if (paramObject instanceof Number)
      return JSONObject.numberToString((Number)paramObject); 
    if (paramObject instanceof Boolean)
      return paramObject.toString(); 
    Object<Map.Entry<Object, Object>> object = paramObject;
    if (paramObject instanceof List)
      object = (Object<Map.Entry<Object, Object>>)((List)paramObject).toArray(); 
    if (object instanceof YailDictionary) {
      StringBuilder stringBuilder = new StringBuilder();
      object = object;
      paramObject = (Object<Map.Entry<Object, Object>>)"";
      stringBuilder.append('{');
      for (Map.Entry<Object, Object> entry : object.entrySet()) {
        stringBuilder.append((String)paramObject);
        stringBuilder.append(JSONObject.quote(entry.getKey().toString()));
        stringBuilder.append(':');
        stringBuilder.append(getJsonRepresentation(entry.getValue()));
        paramObject = (Object<Map.Entry<Object, Object>>)",";
      } 
      stringBuilder.append('}');
      return stringBuilder.toString();
    } 
    if (object.getClass().isArray()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("[");
      paramObject = (Object<Map.Entry<Object, Object>>)"";
      for (Object object1 : (Object[])object) {
        stringBuilder.append((String)paramObject).append(getJsonRepresentation(object1));
        paramObject = (Object<Map.Entry<Object, Object>>)",";
      } 
      stringBuilder.append("]");
      return stringBuilder.toString();
    } 
    return JSONObject.quote(object.toString());
  }
  
  public static String getJsonRepresentationIfValueFileName(Context paramContext, Object<String> paramObject) {
    try {
      if (paramObject instanceof String) {
        paramObject = (Object<String>)getStringListFromJsonArray(new JSONArray((String)paramObject));
      } else if (paramObject instanceof List) {
        paramObject = paramObject;
      } else {
        throw new YailRuntimeError("getJsonRepresentationIfValueFileName called on unknown type", paramObject.getClass().getName());
      } 
      if (paramObject.size() == 2) {
        if (((String)paramObject.get(0)).startsWith(".")) {
          String str = writeFile(paramContext, paramObject.get(1), ((String)paramObject.get(0)).substring(1));
          System.out.println("Filename Written: " + str);
          return getJsonRepresentation(str.replace("file:/", "file:///"));
        } 
        return null;
      } 
    } catch (JSONException jSONException) {
      Log.e("JsonUtil", "JSONException", (Throwable)jSONException);
      return null;
    } 
    return null;
  }
  
  @Deprecated
  public static String getJsonRepresentationIfValueFileName(Object paramObject) {
    Log.w("JsonUtil", "Calling deprecated function getJsonRepresentationIfValueFileName", new IllegalAccessException());
    return getJsonRepresentationIfValueFileName((Context)Form.getActiveForm(), paramObject);
  }
  
  @Deprecated
  public static List<Object> getListFromJsonArray(JSONArray paramJSONArray) throws JSONException {
    return getListFromJsonArray(paramJSONArray, false);
  }
  
  public static List<Object> getListFromJsonArray(JSONArray paramJSONArray, boolean paramBoolean) throws JSONException {
    ArrayList<Object> arrayList = new ArrayList();
    for (int i = 0; i < paramJSONArray.length(); i++)
      arrayList.add(convertJsonItem(paramJSONArray.get(i), paramBoolean)); 
    return arrayList;
  }
  
  public static List<Object> getListFromJsonObject(JSONObject paramJSONObject) throws JSONException {
    ArrayList<ArrayList<String>> arrayList = new ArrayList();
    null = paramJSONObject.keys();
    ArrayList<Comparable> arrayList1 = new ArrayList();
    while (null.hasNext())
      arrayList1.add(null.next()); 
    Collections.sort(arrayList1);
    for (String str : arrayList1) {
      ArrayList<String> arrayList2 = new ArrayList();
      arrayList2.add(str);
      arrayList2.add(convertJsonItem(paramJSONObject.get(str), false));
      arrayList.add(arrayList2);
    } 
    return (List)arrayList;
  }
  
  @Deprecated
  public static Object getObjectFromJson(String paramString) throws JSONException {
    return getObjectFromJson(paramString, false);
  }
  
  public static Object getObjectFromJson(String paramString, boolean paramBoolean) throws JSONException {
    if (paramString == null || paramString.equals(""))
      return ""; 
    Object object2 = (new JSONTokener(paramString)).nextValue();
    if (object2 == null || object2.equals(JSONObject.NULL))
      return null; 
    Object object1 = object2;
    if (!(object2 instanceof String)) {
      object1 = object2;
      if (!(object2 instanceof Number)) {
        object1 = object2;
        if (!(object2 instanceof Boolean)) {
          if (object2 instanceof JSONArray)
            return getListFromJsonArray((JSONArray)object2, paramBoolean); 
          if (object2 instanceof JSONObject)
            return paramBoolean ? getDictionaryFromJsonObject((JSONObject)object2) : getListFromJsonObject((JSONObject)object2); 
          throw new JSONException("Invalid JSON string.");
        } 
      } 
    } 
    return object1;
  }
  
  public static List<String> getStringListFromJsonArray(JSONArray paramJSONArray) throws JSONException {
    ArrayList<String> arrayList = new ArrayList();
    for (int i = 0; i < paramJSONArray.length(); i++)
      arrayList.add(paramJSONArray.getString(i)); 
    return arrayList;
  }
  
  private static void trimDirectory(int paramInt, File paramFile) {
    File[] arrayOfFile = paramFile.listFiles();
    Arrays.sort(arrayOfFile, new Comparator<File>() {
          public int compare(File param1File1, File param1File2) {
            return Long.valueOf(param1File1.lastModified()).compareTo(Long.valueOf(param1File2.lastModified()));
          }
        });
    int j = arrayOfFile.length;
    for (int i = 0; i < j - paramInt; i++)
      arrayOfFile[i].delete(); 
  }
  
  private static String writeFile(Context paramContext, String paramString1, String paramString2) {
    Exception exception;
    byte[] arrayOfByte1;
    Closeable closeable3 = null;
    Closeable closeable2 = null;
    Closeable closeable1 = closeable3;
    try {
      if (paramString2.length() != 3) {
        closeable1 = closeable3;
        if (paramString2.length() != 4) {
          closeable1 = closeable3;
          throw new YailRuntimeError("File Extension must be three or four characters", "Write Error");
        } 
      } 
    } catch (Exception null) {
      closeable1 = closeable2;
      throw new YailRuntimeError(exception.getMessage(), "Write");
    } finally {
      IOUtils.closeQuietly("JsonUtil", closeable1);
    } 
    byte[] arrayOfByte2 = Base64.decode(paramString1, 0);
    closeable1 = closeable3;
    File file2 = new File(QUtil.getExternalStoragePath(paramContext) + "/AppInventorBinaries");
    closeable1 = closeable3;
    file2.mkdirs();
    closeable1 = closeable3;
    File file1 = File.createTempFile("BinFile", "." + paramString2, file2);
    closeable1 = closeable3;
    FileOutputStream fileOutputStream = new FileOutputStream(file1);
    try {
      return str;
    } catch (Exception exception1) {
    
    } finally {
      arrayOfByte2 = null;
      Exception exception1 = exception;
      arrayOfByte1 = arrayOfByte2;
      IOUtils.closeQuietly("JsonUtil", (Closeable)exception1);
    } 
    throw new YailRuntimeError(arrayOfByte1.getMessage(), "Write");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/JsonUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
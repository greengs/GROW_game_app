package gnu.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Options {
  public static final int BOOLEAN_OPTION = 1;
  
  public static final int STRING_OPTION = 2;
  
  public static final String UNKNOWN = "unknown option name";
  
  OptionInfo first;
  
  HashMap<String, OptionInfo> infoTable;
  
  OptionInfo last;
  
  Options previous;
  
  HashMap<String, Object> valueTable;
  
  public Options() {}
  
  public Options(Options paramOptions) {
    this.previous = paramOptions;
  }
  
  private void error(String paramString, SourceMessages paramSourceMessages) {
    if (paramSourceMessages == null)
      throw new RuntimeException(paramString); 
    paramSourceMessages.error('e', paramString);
  }
  
  static Object valueOf(OptionInfo paramOptionInfo, String paramString) {
    String str = paramString;
    if ((paramOptionInfo.kind & 0x1) != 0) {
      if (paramString == null || paramString.equals("1") || paramString.equals("on") || paramString.equals("yes") || paramString.equals("true"))
        return Boolean.TRUE; 
    } else {
      return str;
    } 
    return (paramString.equals("0") || paramString.equals("off") || paramString.equals("no") || paramString.equals("false")) ? Boolean.FALSE : null;
  }
  
  public OptionInfo add(String paramString1, int paramInt, Object paramObject, String paramString2) {
    if (this.infoTable == null) {
      this.infoTable = new HashMap<String, OptionInfo>();
    } else if (this.infoTable.get(paramString1) != null) {
      throw new RuntimeException("duplicate option key: " + paramString1);
    } 
    OptionInfo optionInfo = new OptionInfo();
    optionInfo.key = paramString1;
    optionInfo.kind = paramInt;
    optionInfo.defaultValue = paramObject;
    optionInfo.documentation = paramString2;
    if (this.first == null) {
      this.first = optionInfo;
      this.last = optionInfo;
      this.infoTable.put(paramString1, optionInfo);
      return optionInfo;
    } 
    this.last.next = optionInfo;
    this.last = optionInfo;
    this.infoTable.put(paramString1, optionInfo);
    return optionInfo;
  }
  
  public OptionInfo add(String paramString1, int paramInt, String paramString2) {
    return add(paramString1, paramInt, null, paramString2);
  }
  
  public Object get(OptionInfo paramOptionInfo) {
    return get(paramOptionInfo, (Object)null);
  }
  
  public Object get(OptionInfo paramOptionInfo, Object paramObject) {
    Options options = this;
    Object object = paramObject;
    for (paramObject = options; paramObject != null; paramObject = ((Options)paramObject).previous) {
      OptionInfo optionInfo = paramOptionInfo;
      while (true) {
        Object object1;
        if (((Options)paramObject).valueTable == null) {
          object1 = null;
        } else {
          object1 = ((Options)paramObject).valueTable.get(optionInfo.key);
        } 
        if (object1 != null)
          return object1; 
        if (optionInfo.defaultValue instanceof OptionInfo) {
          optionInfo = (OptionInfo)optionInfo.defaultValue;
          continue;
        } 
        if (optionInfo.defaultValue != null)
          object = optionInfo.defaultValue; 
        break;
      } 
    } 
    return object;
  }
  
  public Object get(String paramString, Object paramObject) {
    OptionInfo optionInfo = getInfo(paramString);
    if (optionInfo == null)
      throw new RuntimeException("invalid option key: " + paramString); 
    return get(optionInfo, paramObject);
  }
  
  public boolean getBoolean(OptionInfo paramOptionInfo) {
    Object object = get(paramOptionInfo, (Object)null);
    return (object == null) ? false : ((Boolean)object).booleanValue();
  }
  
  public boolean getBoolean(OptionInfo paramOptionInfo, boolean paramBoolean) {
    if (paramBoolean) {
      Boolean bool1 = Boolean.TRUE;
      return ((Boolean)get(paramOptionInfo, bool1)).booleanValue();
    } 
    Boolean bool = Boolean.FALSE;
    return ((Boolean)get(paramOptionInfo, bool)).booleanValue();
  }
  
  public boolean getBoolean(String paramString) {
    return ((Boolean)get(paramString, Boolean.FALSE)).booleanValue();
  }
  
  public boolean getBoolean(String paramString, boolean paramBoolean) {
    if (paramBoolean) {
      Boolean bool1 = Boolean.TRUE;
      return ((Boolean)get(paramString, bool1)).booleanValue();
    } 
    Boolean bool = Boolean.FALSE;
    return ((Boolean)get(paramString, bool)).booleanValue();
  }
  
  public String getDoc(String paramString) {
    OptionInfo optionInfo = getInfo(paramString);
    return (paramString == null) ? null : optionInfo.documentation;
  }
  
  public OptionInfo getInfo(String paramString) {
    OptionInfo optionInfo1;
    if (this.infoTable == null) {
      optionInfo1 = null;
    } else {
      optionInfo1 = this.infoTable.get(paramString);
    } 
    OptionInfo optionInfo2 = optionInfo1;
    if (optionInfo1 == null) {
      optionInfo2 = optionInfo1;
      if (this.previous != null)
        optionInfo2 = this.previous.getInfo(paramString); 
    } 
    return optionInfo2;
  }
  
  public Object getLocal(String paramString) {
    return (this.valueTable == null) ? null : this.valueTable.get(paramString);
  }
  
  public ArrayList<String> keys() {
    ArrayList<String> arrayList = new ArrayList();
    for (Options options = this; options != null; options = options.previous) {
      if (options.infoTable != null)
        for (String str : options.infoTable.keySet()) {
          if (!arrayList.contains(str))
            arrayList.add(str); 
        }  
    } 
    return arrayList;
  }
  
  public void popOptionValues(Vector<String> paramVector) {
    int i = paramVector.size();
    while (true) {
      i -= 3;
      if (i >= 0) {
        String str = paramVector.elementAt(i);
        Object object = paramVector.elementAt(i + 1);
        paramVector.setElementAt(null, i + 1);
        reset(str, object);
        continue;
      } 
      break;
    } 
  }
  
  public void pushOptionValues(Vector<String> paramVector) {
    int j = paramVector.size();
    for (int i = 0; i < j; i++) {
      int k = i + 1;
      String str1 = paramVector.elementAt(i);
      String str2 = (String)paramVector.elementAt(k);
      i = k + 1;
      paramVector.setElementAt(str2, k);
      set(str1, paramVector.elementAt(i));
    } 
  }
  
  public void reset(String paramString, Object paramObject) {
    if (this.valueTable == null)
      this.valueTable = new HashMap<String, Object>(); 
    if (paramObject == null) {
      this.valueTable.remove(paramString);
      return;
    } 
    this.valueTable.put(paramString, paramObject);
  }
  
  public String set(String paramString1, String paramString2) {
    OptionInfo optionInfo = getInfo(paramString1);
    if (optionInfo == null)
      return "unknown option name"; 
    Object object = valueOf(optionInfo, paramString2);
    if (object == null && (optionInfo.kind & 0x1) != 0)
      return "value of option " + paramString1 + " must be yes/no/true/false/on/off/1/0"; 
    if (this.valueTable == null)
      this.valueTable = new HashMap<String, Object>(); 
    this.valueTable.put(paramString1, object);
    return null;
  }
  
  public void set(String paramString, Object paramObject) {
    set(paramString, paramObject, null);
  }
  
  public void set(String paramString, Object paramObject, SourceMessages paramSourceMessages) {
    Object object = getInfo(paramString);
    if (object == null) {
      error("invalid option key: " + paramString, paramSourceMessages);
      return;
    } 
    if ((((OptionInfo)object).kind & 0x1) != 0) {
      Object object1 = paramObject;
      if (paramObject instanceof String)
        object1 = valueOf((OptionInfo)object, (String)paramObject); 
      Object object2 = object1;
      if (!(object1 instanceof Boolean)) {
        error("value for option " + paramString + " must be boolean or yes/no/true/false/on/off/1/0", paramSourceMessages);
        return;
      } 
    } else {
      object = paramObject;
      if (paramObject == null)
        object = ""; 
    } 
    if (this.valueTable == null)
      this.valueTable = new HashMap<String, Object>(); 
    this.valueTable.put(paramString, object);
  }
  
  public static final class OptionInfo {
    Object defaultValue;
    
    String documentation;
    
    String key;
    
    int kind;
    
    OptionInfo next;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/Options.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
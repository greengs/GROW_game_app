package gnu.bytecode;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnnotationEntry implements Annotation {
  ClassType annotationType;
  
  LinkedHashMap<String, Object> elementsValue = new LinkedHashMap<String, Object>(10);
  
  public void addMember(String paramString, Object paramObject) {
    this.elementsValue.put(paramString, paramObject);
  }
  
  public Class<? extends Annotation> annotationType() {
    return this.annotationType.getReflectClass();
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof AnnotationEntry) {
      paramObject = paramObject;
      if (getAnnotationType().getName().equals(paramObject.getAnnotationType().getName())) {
        for (Map.Entry<String, Object> entry : this.elementsValue.entrySet()) {
          String str = (String)entry.getKey();
          entry = (Map.Entry<String, Object>)entry.getValue();
          str = (String)((AnnotationEntry)paramObject).elementsValue.get(str);
          if (entry != str) {
            if (entry != null && str != null) {
              if (!entry.equals(str))
                return false; 
              continue;
            } 
            return false;
          } 
        } 
        paramObject = ((AnnotationEntry)paramObject).elementsValue.entrySet().iterator();
        while (paramObject.hasNext()) {
          Map.Entry entry = paramObject.next();
          String str = (String)entry.getKey();
          entry = (Map.Entry)entry.getValue();
          str = (String)this.elementsValue.get(str);
          if (str != entry) {
            if (str != null && entry != null) {
              if (!str.equals(entry))
                return false; 
              continue;
            } 
            return false;
          } 
        } 
        return true;
      } 
    } 
    return false;
  }
  
  public ClassType getAnnotationType() {
    return this.annotationType;
  }
  
  public int hashCode() {
    int i = 0;
    for (Map.Entry<String, Object> entry : this.elementsValue.entrySet())
      i += ((String)entry.getKey()).hashCode() * 127 ^ entry.getValue().hashCode(); 
    return i;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append('@');
    stringBuilder.append(getAnnotationType().getName());
    stringBuilder.append('(');
    int i = 0;
    for (Map.Entry<String, Object> entry : this.elementsValue.entrySet()) {
      if (i)
        stringBuilder.append(", "); 
      stringBuilder.append((String)entry.getKey());
      stringBuilder.append('=');
      stringBuilder.append(entry.getValue());
      i++;
    } 
    stringBuilder.append(')');
    return stringBuilder.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/AnnotationEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
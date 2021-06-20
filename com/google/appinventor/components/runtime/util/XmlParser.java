package com.google.appinventor.components.runtime.util;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParser extends DefaultHandler {
  private static final String CONTENT_TAG = "$content";
  
  private YailDictionary currentElement = null;
  
  private YailDictionary root = null;
  
  private Deque<YailDictionary> stack = new LinkedList<YailDictionary>();
  
  public void characters(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    List<String> list = (List)this.currentElement.get("$content");
    if (list instanceof ArrayList) {
      String str = (new String(paramArrayOfchar, paramInt1, paramInt2)).trim();
      if (!str.isEmpty())
        list.add(str); 
    } 
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3) {
    for (Map.Entry<Object, Object> entry : this.currentElement.entrySet()) {
      if (entry.getValue() instanceof ArrayList)
        entry.setValue(YailList.makeList((List)entry.getValue())); 
    } 
    if (!this.stack.isEmpty())
      this.currentElement = this.stack.pop(); 
  }
  
  public YailDictionary getRoot() {
    return this.root;
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes) {
    YailDictionary yailDictionary2 = new YailDictionary();
    yailDictionary2.put("$tag", paramString3);
    yailDictionary2.put("$namespaceUri", paramString1);
    paramString1 = paramString2;
    if (paramString2.isEmpty())
      paramString1 = paramString3; 
    yailDictionary2.put("$localName", paramString1);
    if (paramString3.contains(":")) {
      yailDictionary2.put("$namespace", paramString3.split(":")[0]);
    } else {
      yailDictionary2.put("$namespace", "");
    } 
    YailDictionary yailDictionary1 = new YailDictionary();
    int i;
    for (i = 0; i < paramAttributes.getLength(); i++)
      yailDictionary1.put(paramAttributes.getQName(i), paramAttributes.getValue(i)); 
    yailDictionary2.put("$attributes", yailDictionary1);
    yailDictionary2.put("$content", new ArrayList());
    if (this.currentElement != null) {
      ((List<YailDictionary>)this.currentElement.get("$content")).add(yailDictionary2);
      if (!this.currentElement.containsKey(paramString3))
        this.currentElement.put(paramString3, new ArrayList()); 
      ((List<YailDictionary>)this.currentElement.get(paramString3)).add(yailDictionary2);
      this.stack.push(this.currentElement);
    } else {
      this.root = yailDictionary2;
    } 
    this.currentElement = yailDictionary2;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/XmlParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
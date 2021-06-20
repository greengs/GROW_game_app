package gnu.xquery.util;

import gnu.mapping.Symbol;
import gnu.mapping.Values;

public class XQException extends RuntimeException {
  public static Symbol FOER0000_QNAME = Symbol.make("http://www.w3.org/2005/xqt-errors", "FOER0000", "err");
  
  public Symbol code;
  
  public String description;
  
  public Object errorValue;
  
  public XQException(Symbol paramSymbol, String paramString, Object paramObject) {
    super(paramString);
    this.code = paramSymbol;
    this.description = paramString;
    this.errorValue = paramObject;
  }
  
  public static void error() {
    throw new XQException(FOER0000_QNAME, null, null);
  }
  
  public static void error(Symbol paramSymbol) {
    throw new XQException(paramSymbol, null, null);
  }
  
  public static void error(Object paramObject, String paramString) {
    if (paramObject != null) {
      Object object = paramObject;
      if (paramObject == Values.empty) {
        object = FOER0000_QNAME;
        throw new XQException((Symbol)object, paramString, null);
      } 
      throw new XQException((Symbol)object, paramString, null);
    } 
    Symbol symbol = FOER0000_QNAME;
    throw new XQException(symbol, paramString, null);
  }
  
  public static void error(Object paramObject1, String paramString, Object paramObject2) {
    if (paramObject1 != null) {
      Object object = paramObject1;
      if (paramObject1 == Values.empty) {
        object = FOER0000_QNAME;
        throw new XQException((Symbol)object, paramString, paramObject2);
      } 
      throw new XQException((Symbol)object, paramString, paramObject2);
    } 
    Symbol symbol = FOER0000_QNAME;
    throw new XQException(symbol, paramString, paramObject2);
  }
  
  public String getMessage() {
    StringBuffer stringBuffer = new StringBuffer(100);
    if (this.description == null) {
      stringBuffer.append("XQuery-error");
    } else {
      stringBuffer.append(this.description);
    } 
    if (this.code != null) {
      stringBuffer.append(" [");
      String str = this.code.getPrefix();
      if (str != null && str.length() > 0) {
        stringBuffer.append(str);
        stringBuffer.append(':');
      } 
      stringBuffer.append(this.code.getLocalName());
      stringBuffer.append(']');
    } 
    if (this.errorValue != null && this.errorValue != Values.empty) {
      stringBuffer.append(" value: ");
      stringBuffer.append(this.errorValue);
    } 
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/XQException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
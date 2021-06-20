package gnu.mapping;

import gnu.text.SourceLocator;

public class UnboundLocationException extends RuntimeException {
  int column;
  
  String filename;
  
  int line;
  
  Location location;
  
  public Object symbol;
  
  public UnboundLocationException() {}
  
  public UnboundLocationException(Location paramLocation) {
    this.location = paramLocation;
  }
  
  public UnboundLocationException(Object paramObject) {
    this.symbol = paramObject;
  }
  
  public UnboundLocationException(Object paramObject, SourceLocator paramSourceLocator) {
    this.symbol = paramObject;
    if (paramSourceLocator != null) {
      this.filename = paramSourceLocator.getFileName();
      this.line = paramSourceLocator.getLineNumber();
      this.column = paramSourceLocator.getColumnNumber();
    } 
  }
  
  public UnboundLocationException(Object paramObject, String paramString) {
    super(paramString);
    this.symbol = paramObject;
  }
  
  public UnboundLocationException(Object paramObject, String paramString, int paramInt1, int paramInt2) {
    this.symbol = paramObject;
    this.filename = paramString;
    this.line = paramInt1;
    this.column = paramInt2;
  }
  
  public String getMessage() {
    Symbol symbol;
    String str = super.getMessage();
    if (str != null)
      return str; 
    StringBuffer stringBuffer = new StringBuffer();
    if (this.filename != null || this.line > 0) {
      if (this.filename != null)
        stringBuffer.append(this.filename); 
      if (this.line >= 0) {
        stringBuffer.append(':');
        stringBuffer.append(this.line);
        if (this.column > 0) {
          stringBuffer.append(':');
          stringBuffer.append(this.column);
        } 
      } 
      stringBuffer.append(": ");
    } 
    if (this.location == null) {
      str = null;
    } else {
      symbol = this.location.getKeySymbol();
    } 
    if (symbol != null) {
      stringBuffer.append("unbound location ");
      stringBuffer.append(symbol);
      Object object = this.location.getKeyProperty();
      if (object != null) {
        stringBuffer.append(" (property ");
        stringBuffer.append(object);
        stringBuffer.append(')');
      } 
      return stringBuffer.toString();
    } 
    if (this.symbol != null) {
      stringBuffer.append("unbound location ");
      stringBuffer.append(this.symbol);
      return stringBuffer.toString();
    } 
    stringBuffer.append("unbound location");
    return stringBuffer.toString();
  }
  
  public void setLine(String paramString, int paramInt1, int paramInt2) {
    this.filename = paramString;
    this.line = paramInt1;
    this.column = paramInt2;
  }
  
  public String toString() {
    return getMessage();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/UnboundLocationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.kawa.xml;

public class XString implements CharSequence {
  public String text;
  
  private XStringType type;
  
  XString(String paramString, XStringType paramXStringType) {
    this.text = paramString;
    this.type = paramXStringType;
  }
  
  public char charAt(int paramInt) {
    return this.text.charAt(paramInt);
  }
  
  public XStringType getStringType() {
    return this.type;
  }
  
  public int length() {
    return this.text.length();
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2) {
    return this.text.substring(paramInt1, paramInt2);
  }
  
  public String toString() {
    return this.text;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/XString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
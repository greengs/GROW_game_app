package gnu.kawa.xml;

public class UntypedAtomic {
  String text;
  
  public UntypedAtomic(String paramString) {
    this.text = paramString;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof UntypedAtomic && this.text.equals(((UntypedAtomic)paramObject).text));
  }
  
  public int hashCode() {
    return this.text.hashCode();
  }
  
  public String toString() {
    return this.text;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/UntypedAtomic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
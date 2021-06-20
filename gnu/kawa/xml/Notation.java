package gnu.kawa.xml;

import gnu.mapping.Symbol;

public class Notation {
  Symbol qname;
  
  public boolean equals(Notation paramNotation1, Notation paramNotation2) {
    return paramNotation1.qname.equals(paramNotation2.qname);
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof Notation && equals(this, (Notation)paramObject));
  }
  
  public int hashCode() {
    return this.qname.hashCode();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/Notation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
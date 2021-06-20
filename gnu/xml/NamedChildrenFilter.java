package gnu.xml;

import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.mapping.Symbol;

public class NamedChildrenFilter extends FilterConsumer {
  int level;
  
  String localName;
  
  int matchLevel;
  
  String namespaceURI;
  
  public NamedChildrenFilter(String paramString1, String paramString2, Consumer paramConsumer) {
    super(paramConsumer);
    this.namespaceURI = paramString1;
    this.localName = paramString2;
    this.skipping = true;
  }
  
  public static NamedChildrenFilter make(String paramString1, String paramString2, Consumer paramConsumer) {
    return new NamedChildrenFilter(paramString1, paramString2, paramConsumer);
  }
  
  public void endDocument() {
    this.level--;
    super.endDocument();
  }
  
  public void endElement() {
    this.level--;
    super.endElement();
    if (!this.skipping && this.matchLevel == this.level)
      this.skipping = true; 
  }
  
  public void startDocument() {
    this.level++;
    super.startDocument();
  }
  
  public void startElement(Object paramObject) {
    if (this.skipping && this.level == 1) {
      String str1;
      String str2;
      if (paramObject instanceof Symbol) {
        Symbol symbol = (Symbol)paramObject;
        str2 = symbol.getNamespaceURI();
        str1 = symbol.getLocalName();
      } else {
        str2 = "";
        str1 = paramObject.toString().intern();
      } 
      if ((this.localName == str1 || this.localName == null) && (this.namespaceURI == str2 || this.namespaceURI == null)) {
        this.skipping = false;
        this.matchLevel = this.level;
      } 
    } 
    super.startElement(paramObject);
    this.level++;
  }
  
  public void writeObject(Object paramObject) {
    if (paramObject instanceof SeqPosition) {
      SeqPosition seqPosition = (SeqPosition)paramObject;
      if (seqPosition.sequence instanceof TreeList) {
        ((TreeList)seqPosition.sequence).consumeNext(seqPosition.ipos, (Consumer)this);
        return;
      } 
    } 
    if (paramObject instanceof Consumable) {
      ((Consumable)paramObject).consume((Consumer)this);
      return;
    } 
    super.writeObject(paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xml/NamedChildrenFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
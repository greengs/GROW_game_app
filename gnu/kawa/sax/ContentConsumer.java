package gnu.kawa.sax;

import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.mapping.Symbol;
import gnu.text.Char;
import gnu.xml.XName;
import java.io.IOException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class ContentConsumer implements Consumer {
  String attrLocalName;
  
  String attrQName;
  
  String attrURI;
  
  AttributesImpl attributes = new AttributesImpl();
  
  char[] chBuffer;
  
  int inStartTag;
  
  String[] names = new String[15];
  
  int nesting = 0;
  
  ContentHandler out;
  
  StringBuilder strBuffer = new StringBuilder(200);
  
  public ContentConsumer() {}
  
  public ContentConsumer(ContentHandler paramContentHandler) {
    this.out = paramContentHandler;
  }
  
  public ContentConsumer append(char paramChar) {
    write(paramChar);
    return this;
  }
  
  public ContentConsumer append(CharSequence paramCharSequence) {
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence == null)
      charSequence = "null"; 
    write(charSequence, 0, charSequence.length());
    return this;
  }
  
  public ContentConsumer append(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence == null)
      charSequence = "null"; 
    write(charSequence, paramInt1, paramInt2);
    return this;
  }
  
  public void endAttribute() {
    this.attributes.addAttribute(this.attrURI, this.attrLocalName, this.attrQName, "CDATA", this.strBuffer.toString());
    this.strBuffer.setLength(0);
    this.inStartTag = 1;
  }
  
  public void endDocument() {
    try {
      this.out.endDocument();
      return;
    } catch (SAXException sAXException) {
      error("endDocument", sAXException);
      return;
    } 
  }
  
  public void endElement() {
    endStartTag();
    flushStrBuffer();
    this.nesting--;
    int i = this.nesting * 3;
    try {
      this.out.endElement(this.names[i], this.names[i + 1], this.names[i + 2]);
    } catch (SAXException sAXException) {
      error("endElement", sAXException);
    } 
    this.names[i] = null;
    this.names[i + 1] = null;
    this.names[i + 2] = null;
  }
  
  public void endStartTag() {
    if (this.inStartTag != 1)
      return; 
    int i = (this.nesting - 1) * 3;
    try {
      this.out.startElement(this.names[i], this.names[i + 1], this.names[i + 2], this.attributes);
    } catch (SAXException sAXException) {
      error("startElement", sAXException);
    } 
    this.attributes.clear();
    this.inStartTag = 0;
  }
  
  public void error(String paramString, SAXException paramSAXException) {
    throw new RuntimeException("caught " + paramSAXException + " in " + paramString);
  }
  
  public void finalize() {
    flushStrBuffer();
  }
  
  void flushStrBuffer() {
    if (this.strBuffer.length() > 0) {
      if (this.chBuffer == null)
        this.chBuffer = new char[200]; 
      try {
        int j = this.strBuffer.length();
        for (int i = 0;; i += k) {
          int m = j - i;
          if (m <= 0) {
            this.strBuffer.setLength(0);
            return;
          } 
          int k = m;
          if (m > this.chBuffer.length)
            k = this.chBuffer.length; 
          this.strBuffer.getChars(i, i + k, this.chBuffer, i);
          this.out.characters(this.chBuffer, 0, k);
        } 
      } catch (SAXException sAXException) {
        error("characters", sAXException);
      } 
    } 
  }
  
  public ContentHandler getContentHandler() {
    return this.out;
  }
  
  public boolean ignoring() {
    return false;
  }
  
  public void setContentHandler(ContentHandler paramContentHandler) {
    this.out = paramContentHandler;
  }
  
  public void startAttribute(Object paramObject) {
    this.attrURI = ((Symbol)paramObject).getNamespaceURI();
    this.attrLocalName = ((Symbol)paramObject).getLocalName();
    this.attrQName = paramObject.toString();
    this.inStartTag = 2;
  }
  
  public void startDocument() {
    try {
      this.out.startDocument();
      return;
    } catch (SAXException sAXException) {
      error("startDocument", sAXException);
      return;
    } 
  }
  
  public void startElement(Object paramObject) {
    String str1;
    String str2;
    if (this.inStartTag == 1)
      endStartTag(); 
    flushStrBuffer();
    int i = this.nesting * 3;
    if (i >= this.names.length) {
      String[] arrayOfString = new String[i * 2];
      System.arraycopy(this.names, 0, arrayOfString, 0, i);
      this.names = arrayOfString;
    } 
    if (paramObject instanceof Symbol) {
      Symbol symbol = (Symbol)paramObject;
      str2 = symbol.getNamespaceURI();
      str1 = symbol.getLocalName();
    } else if (paramObject instanceof XName) {
      XName xName = (XName)paramObject;
      str2 = xName.getNamespaceURI();
      str1 = xName.getLocalName();
    } else {
      str2 = "";
      str1 = paramObject.toString();
    } 
    this.names[i] = str2;
    this.names[i + 1] = str1;
    this.names[i + 2] = paramObject.toString();
    this.inStartTag = 1;
    this.nesting++;
  }
  
  public void write(int paramInt) {
    if (this.inStartTag == 1)
      endStartTag(); 
    int i = paramInt;
    if (paramInt >= 65536) {
      this.strBuffer.append((char)((paramInt - 65536 >> 10) + 55296));
      i = (paramInt & 0x3FF) + 56320;
    } 
    this.strBuffer.append((char)i);
  }
  
  public void write(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    if (this.inStartTag == 1)
      endStartTag(); 
    this.strBuffer.append(paramCharSequence, paramInt1, paramInt2);
  }
  
  public void write(String paramString) {
    if (this.inStartTag == 1)
      endStartTag(); 
    this.strBuffer.append(paramString);
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this.inStartTag == 1)
      endStartTag(); 
    if (this.inStartTag == 2) {
      this.strBuffer.append(paramArrayOfchar, paramInt1, paramInt2);
      return;
    } 
    flushStrBuffer();
    try {
      this.out.characters(paramArrayOfchar, paramInt1, paramInt2);
      return;
    } catch (SAXException sAXException) {
      error("characters", sAXException);
      return;
    } 
  }
  
  public void writeBoolean(boolean paramBoolean) {
    if (this.inStartTag == 1)
      endStartTag(); 
    this.strBuffer.append(paramBoolean);
  }
  
  public void writeDouble(double paramDouble) {
    if (this.inStartTag == 1)
      endStartTag(); 
    this.strBuffer.append(paramDouble);
  }
  
  public void writeFloat(float paramFloat) {
    if (this.inStartTag == 1)
      endStartTag(); 
    this.strBuffer.append(paramFloat);
  }
  
  public void writeInt(int paramInt) {
    if (this.inStartTag == 1)
      endStartTag(); 
    this.strBuffer.append(paramInt);
  }
  
  public void writeLong(long paramLong) {
    if (this.inStartTag == 1)
      endStartTag(); 
    this.strBuffer.append(paramLong);
  }
  
  public void writeObject(Object paramObject) {
    if (paramObject instanceof Consumable) {
      ((Consumable)paramObject).consume(this);
      return;
    } 
    if (paramObject instanceof SeqPosition) {
      paramObject = paramObject;
      ((SeqPosition)paramObject).sequence.consumeNext(((SeqPosition)paramObject).ipos, this);
      return;
    } 
    if (paramObject instanceof Char) {
      ((Char)paramObject).print(this);
      return;
    } 
    if (paramObject == null) {
      paramObject = "(null)";
    } else {
      paramObject = paramObject.toString();
    } 
    write((String)paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/sax/ContentConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
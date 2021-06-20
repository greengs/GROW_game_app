package gnu.lists;

import java.io.IOException;

public class FilterConsumer implements XConsumer {
  protected Object attributeType;
  
  protected Consumer base;
  
  protected boolean inAttribute;
  
  protected boolean skipping;
  
  public FilterConsumer(Consumer paramConsumer) {
    this.base = paramConsumer;
  }
  
  public Consumer append(char paramChar) {
    write(paramChar);
    return this;
  }
  
  public Consumer append(CharSequence paramCharSequence) {
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence == null)
      charSequence = "null"; 
    write(charSequence, 0, charSequence.length());
    return this;
  }
  
  public Consumer append(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence == null)
      charSequence = "null"; 
    write(charSequence, paramInt1, paramInt2 - paramInt1);
    return this;
  }
  
  protected void beforeContent() {}
  
  protected void beforeNode() {}
  
  public void beginEntity(Object paramObject) {
    if (!this.skipping) {
      beforeNode();
      if (this.base instanceof XConsumer)
        ((XConsumer)this.base).beginEntity(paramObject); 
    } 
  }
  
  public void endAttribute() {
    if (!this.skipping)
      this.base.endAttribute(); 
    this.inAttribute = false;
  }
  
  public void endDocument() {
    if (!this.skipping)
      this.base.endDocument(); 
  }
  
  public void endElement() {
    if (!this.skipping)
      this.base.endElement(); 
  }
  
  public void endEntity() {
    if (!this.skipping && this.base instanceof XConsumer)
      ((XConsumer)this.base).endEntity(); 
  }
  
  public boolean ignoring() {
    return this.base.ignoring();
  }
  
  public void startAttribute(Object paramObject) {
    this.attributeType = paramObject;
    this.inAttribute = true;
    if (!this.skipping) {
      beforeNode();
      this.base.startAttribute(paramObject);
    } 
  }
  
  public void startDocument() {
    if (!this.skipping)
      this.base.startDocument(); 
  }
  
  public void startElement(Object paramObject) {
    if (!this.skipping) {
      beforeNode();
      this.base.startElement(paramObject);
    } 
  }
  
  public void write(int paramInt) {
    beforeContent();
    if (!this.skipping)
      this.base.write(paramInt); 
  }
  
  public void write(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    beforeContent();
    if (!this.skipping)
      this.base.write(paramCharSequence, paramInt1, paramInt2); 
  }
  
  public void write(String paramString) {
    write(paramString, 0, paramString.length());
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    beforeContent();
    if (!this.skipping)
      this.base.write(paramArrayOfchar, paramInt1, paramInt2); 
  }
  
  public void writeBoolean(boolean paramBoolean) {
    beforeContent();
    if (!this.skipping)
      this.base.writeBoolean(paramBoolean); 
  }
  
  public void writeCDATA(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    beforeContent();
    if (!this.skipping) {
      if (this.base instanceof XConsumer) {
        ((XConsumer)this.base).writeCDATA(paramArrayOfchar, paramInt1, paramInt2);
        return;
      } 
    } else {
      return;
    } 
    this.base.write(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public void writeComment(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (!this.skipping) {
      beforeNode();
      if (this.base instanceof XConsumer)
        ((XConsumer)this.base).writeComment(paramArrayOfchar, paramInt1, paramInt2); 
    } 
  }
  
  public void writeDouble(double paramDouble) {
    beforeContent();
    if (!this.skipping)
      this.base.writeDouble(paramDouble); 
  }
  
  public void writeFloat(float paramFloat) {
    beforeContent();
    if (!this.skipping)
      this.base.writeFloat(paramFloat); 
  }
  
  public void writeInt(int paramInt) {
    beforeContent();
    if (!this.skipping)
      this.base.writeInt(paramInt); 
  }
  
  public void writeLong(long paramLong) {
    beforeContent();
    if (!this.skipping)
      this.base.writeLong(paramLong); 
  }
  
  public void writeObject(Object paramObject) {
    beforeContent();
    if (!this.skipping)
      this.base.writeObject(paramObject); 
  }
  
  public void writeProcessingInstruction(String paramString, char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (!this.skipping) {
      beforeNode();
      if (this.base instanceof XConsumer)
        ((XConsumer)this.base).writeProcessingInstruction(paramString, paramArrayOfchar, paramInt1, paramInt2); 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/FilterConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
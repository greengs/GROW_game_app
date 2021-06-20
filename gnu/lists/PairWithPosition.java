package gnu.lists;

import gnu.text.SourceLocator;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class PairWithPosition extends ImmutablePair implements SourceLocator {
  String filename;
  
  int position;
  
  public PairWithPosition() {}
  
  public PairWithPosition(SourceLocator paramSourceLocator, Object paramObject1, Object paramObject2) {
    super(paramObject1, paramObject2);
    this.filename = paramSourceLocator.getFileName();
    setLine(paramSourceLocator.getLineNumber(), paramSourceLocator.getColumnNumber());
  }
  
  public PairWithPosition(Object paramObject1, Object paramObject2) {
    super(paramObject1, paramObject2);
  }
  
  public static PairWithPosition make(Object paramObject1, Object paramObject2, String paramString, int paramInt) {
    paramObject1 = new PairWithPosition(paramObject1, paramObject2);
    ((PairWithPosition)paramObject1).filename = paramString;
    ((PairWithPosition)paramObject1).position = paramInt;
    return (PairWithPosition)paramObject1;
  }
  
  public static PairWithPosition make(Object paramObject1, Object paramObject2, String paramString, int paramInt1, int paramInt2) {
    paramObject1 = new PairWithPosition(paramObject1, paramObject2);
    ((PairWithPosition)paramObject1).filename = paramString;
    paramObject1.setLine(paramInt1, paramInt2);
    return (PairWithPosition)paramObject1;
  }
  
  public final int getColumnNumber() {
    int j = this.position & 0xFFF;
    int i = j;
    if (j == 0)
      i = -1; 
    return i;
  }
  
  public final String getFileName() {
    return this.filename;
  }
  
  public final int getLineNumber() {
    int j = this.position >> 12;
    int i = j;
    if (j == 0)
      i = -1; 
    return i;
  }
  
  public String getPublicId() {
    return null;
  }
  
  public String getSystemId() {
    return this.filename;
  }
  
  public boolean isStableSourceLocation() {
    return true;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.car = paramObjectInput.readObject();
    this.cdr = paramObjectInput.readObject();
    this.filename = (String)paramObjectInput.readObject();
    this.position = paramObjectInput.readInt();
  }
  
  public final void setFile(String paramString) {
    this.filename = paramString;
  }
  
  public final void setLine(int paramInt) {
    setLine(paramInt, 0);
  }
  
  public final void setLine(int paramInt1, int paramInt2) {
    int i = paramInt1;
    if (paramInt1 < 0)
      i = 0; 
    paramInt1 = paramInt2;
    if (paramInt2 < 0)
      paramInt1 = 0; 
    this.position = (i << 12) + paramInt1;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.car);
    paramObjectOutput.writeObject(this.cdr);
    paramObjectOutput.writeObject(this.filename);
    paramObjectOutput.writeInt(this.position);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/PairWithPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
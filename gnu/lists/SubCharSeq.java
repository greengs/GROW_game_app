package gnu.lists;

import java.io.IOException;
import java.util.List;

public class SubCharSeq extends SubSequence implements CharSeq {
  public SubCharSeq(AbstractSequence paramAbstractSequence, int paramInt1, int paramInt2) {
    super(paramAbstractSequence, paramInt1, paramInt2);
  }
  
  private SubCharSeq subCharSeq(int paramInt1, int paramInt2) {
    int i = size();
    if (paramInt1 < 0 || paramInt2 < paramInt1 || paramInt2 > i)
      throw new IndexOutOfBoundsException(); 
    return new SubCharSeq(this.base, this.base.createRelativePos(this.ipos0, paramInt1, false), this.base.createRelativePos(this.ipos0, paramInt2, true));
  }
  
  public char charAt(int paramInt) {
    if (paramInt < 0 || paramInt >= size())
      throw new IndexOutOfBoundsException(); 
    int i = this.base.nextIndex(this.ipos0);
    return ((CharSeq)this.base).charAt(i + paramInt);
  }
  
  public void consume(int paramInt1, int paramInt2, Consumer paramConsumer) {
    int i = this.base.nextIndex(this.ipos0);
    int j = this.base.nextIndex(this.ipos0);
    if (paramInt1 < 0 || paramInt2 < 0 || i + paramInt1 + paramInt2 > j)
      throw new IndexOutOfBoundsException(); 
    ((CharSeq)this.base).consume(i + paramInt1, paramInt2, paramConsumer);
  }
  
  public void fill(char paramChar) {
    int i = this.base.nextIndex(this.ipos0);
    int j = this.base.nextIndex(this.ipos0);
    ((CharSeq)this.base).fill(i, j, paramChar);
  }
  
  public void fill(int paramInt1, int paramInt2, char paramChar) {
    int i = this.base.nextIndex(this.ipos0);
    int j = this.base.nextIndex(this.ipos0);
    if (paramInt1 < 0 || paramInt2 < paramInt1 || i + paramInt2 > j)
      throw new IndexOutOfBoundsException(); 
    ((CharSeq)this.base).fill(i + paramInt1, i + paramInt2, paramChar);
  }
  
  public void getChars(int paramInt1, int paramInt2, char[] paramArrayOfchar, int paramInt3) {
    while (paramInt1 < paramInt2) {
      paramArrayOfchar[paramInt3] = charAt(paramInt1);
      paramInt1++;
      paramInt3++;
    } 
  }
  
  public int length() {
    return size();
  }
  
  public void setCharAt(int paramInt, char paramChar) {
    if (paramInt < 0 || paramInt >= size())
      throw new IndexOutOfBoundsException(); 
    int i = this.base.nextIndex(this.ipos0);
    ((CharSeq)this.base).setCharAt(i + paramInt, paramChar);
  }
  
  public List subList(int paramInt1, int paramInt2) {
    return subCharSeq(paramInt1, paramInt2);
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2) {
    return subCharSeq(paramInt1, paramInt2);
  }
  
  public String toString() {
    int j = size();
    StringBuffer stringBuffer = new StringBuffer(j);
    for (int i = 0; i < j; i++)
      stringBuffer.append(charAt(i)); 
    return stringBuffer.toString();
  }
  
  public void writeTo(int paramInt1, int paramInt2, Appendable paramAppendable) throws IOException {
    int i = this.base.nextIndex(this.ipos0);
    int j = this.base.nextIndex(this.ipos0);
    if (paramInt1 < 0 || paramInt2 < 0 || i + paramInt1 + paramInt2 > j)
      throw new IndexOutOfBoundsException(); 
    ((CharSeq)this.base).writeTo(i + paramInt1, paramInt2, paramAppendable);
  }
  
  public void writeTo(Appendable paramAppendable) throws IOException {
    int i = this.base.nextIndex(this.ipos0);
    ((CharSeq)this.base).writeTo(i, size(), paramAppendable);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/SubCharSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
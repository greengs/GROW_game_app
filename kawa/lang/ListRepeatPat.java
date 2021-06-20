package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ListRepeatPat extends Pattern implements Printable, Externalizable {
  Pattern element_pattern;
  
  public ListRepeatPat() {}
  
  public ListRepeatPat(Pattern paramPattern) {
    this.element_pattern = paramPattern;
  }
  
  public static ListRepeatPat make(Pattern paramPattern) {
    return new ListRepeatPat(paramPattern);
  }
  
  public boolean match(Object paramObject, Object[] paramArrayOfObject, int paramInt) {
    int k = LList.listLength(paramObject, false);
    if (k < 0)
      return false; 
    int j = this.element_pattern.varCount();
    int i = j;
    while (true) {
      if (--i >= 0) {
        paramArrayOfObject[paramInt + i] = new Object[k];
        continue;
      } 
      Object[] arrayOfObject = new Object[j];
      for (i = 0; i < k; i++) {
        paramObject = paramObject;
        if (!this.element_pattern.match(paramObject.getCar(), arrayOfObject, 0))
          return false; 
        int m;
        for (m = 0; m < j; m++)
          ((Object[])paramArrayOfObject[paramInt + m])[i] = arrayOfObject[m]; 
        paramObject = paramObject.getCdr();
      } 
      return true;
    } 
  }
  
  public void print(Consumer paramConsumer) {
    paramConsumer.write("#<list-repeat-pattern ");
    this.element_pattern.print(paramConsumer);
    paramConsumer.write(62);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.element_pattern = (Pattern)paramObjectInput.readObject();
  }
  
  public int varCount() {
    return this.element_pattern.varCount();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.element_pattern);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/ListRepeatPat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
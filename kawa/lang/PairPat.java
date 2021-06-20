package kawa.lang;

import gnu.lists.Consumer;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class PairPat extends Pattern implements Printable, Externalizable {
  Pattern car;
  
  private int car_count;
  
  Pattern cdr;
  
  private int cdr_count;
  
  public PairPat() {}
  
  public PairPat(Pattern paramPattern1, Pattern paramPattern2) {
    this.car = paramPattern1;
    this.cdr = paramPattern2;
    this.car_count = paramPattern1.varCount();
    this.cdr_count = paramPattern2.varCount();
  }
  
  public static PairPat make(Pattern paramPattern1, Pattern paramPattern2) {
    return new PairPat(paramPattern1, paramPattern2);
  }
  
  public boolean match(Object paramObject, Object[] paramArrayOfObject, int paramInt) {
    if (paramObject instanceof gnu.lists.Pair) {
      paramObject = paramObject;
      if (this.car.match(paramObject.getCar(), paramArrayOfObject, paramInt) && this.cdr.match(paramObject.getCdr(), paramArrayOfObject, this.car_count + paramInt))
        return true; 
    } 
    return false;
  }
  
  public void print(Consumer paramConsumer) {
    paramConsumer.write("#<pair-pattern car: ");
    this.car.print(paramConsumer);
    paramConsumer.write(" cdr: ");
    this.cdr.print(paramConsumer);
    paramConsumer.write(62);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.car = (Pattern)paramObjectInput.readObject();
    this.cdr = (Pattern)paramObjectInput.readObject();
  }
  
  public int varCount() {
    return this.car_count + this.cdr_count;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.car);
    paramObjectOutput.writeObject(this.cdr);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/PairPat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
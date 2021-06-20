package kawa.standard;

import gnu.math.Quantity;
import gnu.math.Unit;
import kawa.lang.GenericError;

public class sleep {
  public static void sleep(Quantity paramQuantity) {
    Unit unit = paramQuantity.unit();
    if (unit == Unit.Empty || unit.dimensions() == Unit.second.dimensions()) {
      double d = paramQuantity.doubleValue();
      long l = (long)(1000.0D * d);
      int i = (int)(1.0E9D * d - l * 1000000.0D);
      try {
        Thread.sleep(l, i);
        return;
      } catch (InterruptedException interruptedException) {
        throw new GenericError("sleep was interrupted");
      } 
    } 
    throw new GenericError("bad unit for sleep");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/sleep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.math;

public class Dimensions {
  public static Dimensions Empty;
  
  private static Dimensions[] hashTable = new Dimensions[100];
  
  BaseUnit[] bases;
  
  private Dimensions chain;
  
  int hash_code;
  
  short[] powers;
  
  static {
    Empty = new Dimensions();
  }
  
  private Dimensions() {
    this.bases = new BaseUnit[1];
    this.bases[0] = Unit.Empty;
    enterHash(0);
  }
  
  Dimensions(BaseUnit paramBaseUnit) {
    this.bases = new BaseUnit[2];
    this.powers = new short[1];
    this.bases[0] = paramBaseUnit;
    this.bases[1] = Unit.Empty;
    this.powers[0] = 1;
    enterHash(paramBaseUnit.index);
  }
  
  private Dimensions(Dimensions paramDimensions1, int paramInt1, Dimensions paramDimensions2, int paramInt2, int paramInt3) {
    this.hash_code = paramInt3;
    int i;
    for (i = 0; paramDimensions1.bases[i] != Unit.Empty; i++);
    int j;
    for (j = 0; paramDimensions2.bases[j] != Unit.Empty; j++);
    i = i + j + 1;
    this.bases = new BaseUnit[i];
    this.powers = new short[i];
    int k = 0;
    i = 0;
    j = 0;
    while (true) {
      int m;
      BaseUnit baseUnit1 = paramDimensions1.bases[j];
      BaseUnit baseUnit2 = paramDimensions2.bases[i];
      if (baseUnit1.index < baseUnit2.index) {
        m = paramDimensions1.powers[j] * paramInt1;
        j++;
      } else if (baseUnit2.index < baseUnit1.index) {
        baseUnit1 = baseUnit2;
        m = paramDimensions2.powers[i] * paramInt2;
        i++;
      } else {
        if (baseUnit2 == Unit.Empty) {
          this.bases[k] = Unit.Empty;
          enterHash(paramInt3);
          return;
        } 
        int i2 = paramDimensions1.powers[j] * paramInt1 + paramDimensions2.powers[i] * paramInt2;
        int n = j + 1;
        int i1 = i + 1;
        j = n;
        i = i1;
        m = i2;
        if (i2 == 0) {
          j = n;
          i = i1;
          continue;
        } 
      } 
      if ((short)m != m)
        throw new ArithmeticException("overflow in dimensions"); 
      this.bases[k] = baseUnit1;
      this.powers[k] = (short)m;
      k++;
    } 
  }
  
  private void enterHash(int paramInt) {
    this.hash_code = paramInt;
    paramInt = (Integer.MAX_VALUE & paramInt) % hashTable.length;
    this.chain = hashTable[paramInt];
    hashTable[paramInt] = this;
  }
  
  private boolean matchesProduct(Dimensions paramDimensions1, int paramInt1, Dimensions paramDimensions2, int paramInt2) {
    int j = 0;
    int i = 0;
    int k = 0;
    while (true) {
      int m;
      BaseUnit baseUnit1 = paramDimensions1.bases[j];
      BaseUnit baseUnit2 = paramDimensions2.bases[i];
      if (baseUnit1.index < baseUnit2.index) {
        m = paramDimensions1.powers[j] * paramInt1;
        j++;
      } else if (baseUnit2.index < baseUnit1.index) {
        baseUnit1 = baseUnit2;
        m = paramDimensions2.powers[i] * paramInt2;
        i++;
      } else {
        if (baseUnit2 == Unit.Empty)
          return (this.bases[k] == baseUnit2); 
        int i2 = paramDimensions1.powers[j] * paramInt1 + paramDimensions2.powers[i] * paramInt2;
        int n = j + 1;
        int i1 = i + 1;
        j = n;
        i = i1;
        m = i2;
        if (i2 == 0) {
          j = n;
          i = i1;
          continue;
        } 
      } 
      if (this.bases[k] == baseUnit1 && this.powers[k] == m) {
        k++;
        continue;
      } 
      return false;
    } 
  }
  
  public static Dimensions product(Dimensions paramDimensions1, int paramInt1, Dimensions paramDimensions2, int paramInt2) {
    int i = paramDimensions1.hashCode() * paramInt1 + paramDimensions2.hashCode() * paramInt2;
    int j = hashTable.length;
    for (Dimensions dimensions = hashTable[(Integer.MAX_VALUE & i) % j]; dimensions != null; dimensions = dimensions.chain) {
      if (dimensions.hash_code == i && dimensions.matchesProduct(paramDimensions1, paramInt1, paramDimensions2, paramInt2))
        return dimensions; 
    } 
    return new Dimensions(paramDimensions1, paramInt1, paramDimensions2, paramInt2, i);
  }
  
  public int getPower(BaseUnit paramBaseUnit) {
    for (int i = 0; (this.bases[i]).index <= paramBaseUnit.index; i++) {
      if (this.bases[i] == paramBaseUnit)
        return this.powers[i]; 
    } 
    return 0;
  }
  
  public final int hashCode() {
    return this.hash_code;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = 0; this.bases[i] != Unit.Empty; i++) {
      if (i)
        stringBuffer.append('*'); 
      stringBuffer.append(this.bases[i]);
      short s = this.powers[i];
      if (s != 1) {
        stringBuffer.append('^');
        stringBuffer.append(s);
      } 
    } 
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/Dimensions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
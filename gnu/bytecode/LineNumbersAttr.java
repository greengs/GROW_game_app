package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class LineNumbersAttr extends Attribute {
  int linenumber_count;
  
  short[] linenumber_table;
  
  public LineNumbersAttr(CodeAttr paramCodeAttr) {
    super("LineNumberTable");
    addToFrontOf(paramCodeAttr);
    paramCodeAttr.lines = this;
  }
  
  public LineNumbersAttr(short[] paramArrayOfshort, CodeAttr paramCodeAttr) {
    this(paramCodeAttr);
    this.linenumber_table = paramArrayOfshort;
    this.linenumber_count = paramArrayOfshort.length >> 1;
  }
  
  public final int getLength() {
    return this.linenumber_count * 4 + 2;
  }
  
  public int getLineCount() {
    return this.linenumber_count;
  }
  
  public short[] getLineNumberTable() {
    return this.linenumber_table;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter) {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", count: ");
    paramClassTypeWriter.println(this.linenumber_count);
    for (int i = 0; i < this.linenumber_count; i++) {
      paramClassTypeWriter.print("  line: ");
      paramClassTypeWriter.print(this.linenumber_table[i * 2 + 1] & 0xFFFF);
      paramClassTypeWriter.print(" at pc: ");
      paramClassTypeWriter.println(this.linenumber_table[i * 2] & 0xFFFF);
    } 
  }
  
  public void put(int paramInt1, int paramInt2) {
    if (this.linenumber_table == null) {
      this.linenumber_table = new short[32];
    } else if (this.linenumber_count * 2 >= this.linenumber_table.length) {
      short[] arrayOfShort = new short[this.linenumber_table.length * 2];
      System.arraycopy(this.linenumber_table, 0, arrayOfShort, 0, this.linenumber_count * 2);
      this.linenumber_table = arrayOfShort;
    } 
    this.linenumber_table[this.linenumber_count * 2] = (short)paramInt2;
    this.linenumber_table[this.linenumber_count * 2 + 1] = (short)paramInt1;
    this.linenumber_count++;
  }
  
  public void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeShort(this.linenumber_count);
    int j = this.linenumber_count;
    for (int i = 0; i < j * 2; i++)
      paramDataOutputStream.writeShort(this.linenumber_table[i]); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/LineNumbersAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.kawa.lispexpr;

public class ReaderMisc extends ReadTableEntry {
  int kind;
  
  public ReaderMisc(int paramInt) {
    this.kind = paramInt;
  }
  
  public int getKind() {
    return this.kind;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReaderMisc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
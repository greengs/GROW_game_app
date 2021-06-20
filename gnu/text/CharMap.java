package gnu.text;

import gnu.kawa.util.AbstractWeakHashTable;

class CharMap extends AbstractWeakHashTable<Char, Char> {
  public Char get(int paramInt) {
    cleanup();
    int i = hashToIndex(paramInt);
    for (AbstractWeakHashTable.WEntry wEntry = ((AbstractWeakHashTable.WEntry[])this.table)[i]; wEntry != null; wEntry = wEntry.next) {
      Char char_1 = (Char)wEntry.getValue();
      if (char_1 != null && char_1.intValue() == paramInt)
        return char_1; 
    } 
    Char char_ = new Char(paramInt);
    put(char_, char_);
    return char_;
  }
  
  protected Char getKeyFromValue(Char paramChar) {
    return paramChar;
  }
  
  protected boolean matches(Char paramChar1, Char paramChar2) {
    return (paramChar1.intValue() == paramChar2.intValue());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/CharMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
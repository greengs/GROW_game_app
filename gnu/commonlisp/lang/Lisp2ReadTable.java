package gnu.commonlisp.lang;

import gnu.kawa.lispexpr.ReadTable;

class Lisp2ReadTable extends ReadTable {
  protected Object makeSymbol(String paramString) {
    return Lisp2.asSymbol(paramString.intern());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/commonlisp/lang/Lisp2ReadTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.kawa.xslt;

import gnu.mapping.Procedure;
import gnu.mapping.Symbol;

public class TemplateTable {
  static final TemplateTable nullModeTable = new TemplateTable(XSLT.nullMode);
  
  TemplateEntry entries;
  
  Symbol name;
  
  public TemplateTable(Symbol paramSymbol) {
    this.name = paramSymbol;
  }
  
  static TemplateTable getTemplateTable(Symbol paramSymbol) {
    return (paramSymbol == XSLT.nullMode) ? nullModeTable : null;
  }
  
  public void enter(String paramString, double paramDouble, Procedure paramProcedure) {
    TemplateEntry templateEntry = new TemplateEntry();
    templateEntry.procedure = paramProcedure;
    templateEntry.priority = paramDouble;
    templateEntry.pattern = paramString;
    templateEntry.next = this.entries;
    this.entries = templateEntry;
  }
  
  public Procedure find(String paramString) {
    for (TemplateEntry templateEntry = this.entries; templateEntry != null; templateEntry = templateEntry.next) {
      if (templateEntry.pattern.equals(paramString))
        return templateEntry.procedure; 
    } 
    return null;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xslt/TemplateTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
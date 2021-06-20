package gnu.kawa.lispexpr;

import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;

public class LispPackage extends Namespace {
  Namespace exported;
  
  NamespaceUse imported;
  
  NamespaceUse importing;
  
  LList shadowingSymbols = LList.Empty;
  
  private void addToShadowingSymbols(Symbol paramSymbol) {
    LList lList = this.shadowingSymbols;
    while (lList != LList.Empty) {
      Pair pair = (Pair)lList;
      if (pair.getCar() == paramSymbol)
        return; 
      Object object = pair.getCdr();
    } 
    this.shadowingSymbols = (LList)new Pair(paramSymbol, this.shadowingSymbols);
  }
  
  private boolean removeFromShadowingSymbols(Symbol paramSymbol) {
    Pair pair = null;
    LList lList = this.shadowingSymbols;
    while (lList != LList.Empty) {
      Pair pair1 = (Pair)lList;
      Object object = pair1.getCdr();
      if (pair1.getCar() == paramSymbol) {
        if (pair == null) {
          this.shadowingSymbols = (LList)object;
          return true;
        } 
        pair.setCdr(object);
        return true;
      } 
      pair = pair1;
    } 
    return false;
  }
  
  public boolean isPresent(String paramString) {
    boolean bool = false;
    if (lookupPresent(paramString, paramString.hashCode(), false) != null)
      bool = true; 
    return bool;
  }
  
  public Symbol lookup(String paramString, int paramInt, boolean paramBoolean) {
    Symbol symbol = this.exported.lookup(paramString, paramInt, false);
    if (symbol != null)
      return symbol; 
    symbol = lookupInternal(paramString, paramInt);
    if (symbol != null)
      return symbol; 
    for (NamespaceUse namespaceUse = this.imported; namespaceUse != null; namespaceUse = namespaceUse.nextImported) {
      Symbol symbol1 = lookup(paramString, paramInt, false);
      if (symbol1 != null)
        return symbol1; 
    } 
    return paramBoolean ? add(new Symbol(this, paramString), paramInt) : null;
  }
  
  public Symbol lookupPresent(String paramString, int paramInt, boolean paramBoolean) {
    Symbol symbol2 = this.exported.lookup(paramString, paramInt, false);
    Symbol symbol1 = symbol2;
    if (symbol2 == null)
      symbol1 = super.lookup(paramString, paramInt, paramBoolean); 
    return symbol1;
  }
  
  public void shadow(String paramString) {
    addToShadowingSymbols(lookupPresent(paramString, paramString.hashCode(), true));
  }
  
  public void shadowingImport(Symbol paramSymbol) {
    String str = paramSymbol.getName();
    str.hashCode();
    Symbol symbol = lookupPresent(str, str.hashCode(), false);
    if (symbol != null && symbol != paramSymbol)
      unintern(symbol); 
    addToShadowingSymbols(paramSymbol);
  }
  
  public boolean unintern(Symbol paramSymbol) {
    null = false;
    String str = paramSymbol.getName();
    int i = str.hashCode();
    if (this.exported.lookup(str, i, false) == paramSymbol) {
      this.exported.remove(paramSymbol);
    } else if (super.lookup(str, i, false) == paramSymbol) {
      remove(paramSymbol);
    } else {
      return null;
    } 
    paramSymbol.setNamespace(null);
    if (removeFromShadowingSymbols(paramSymbol));
    return true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/LispPackage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
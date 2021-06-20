package gnu.expr;

import gnu.kawa.util.GeneralHashTable;
import gnu.kawa.util.HashNode;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Symbol;

public class NameLookup extends GeneralHashTable<Object, Declaration> {
  static final Symbol KEY = Symbol.makeUninterned("<current-NameLookup>");
  
  Language language;
  
  public NameLookup(Language paramLanguage) {
    this.language = paramLanguage;
  }
  
  public static NameLookup getInstance(Environment paramEnvironment, Language paramLanguage) {
    NameLookup nameLookup1;
    Location location = paramEnvironment.getLocation(KEY);
    NameLookup nameLookup2 = (NameLookup)location.get(null);
    if (nameLookup2 == null) {
      nameLookup1 = new NameLookup(paramLanguage);
      location.set(nameLookup1);
      return nameLookup1;
    } 
    nameLookup2.setLanguage((Language)nameLookup1);
    return nameLookup2;
  }
  
  public static void setInstance(Environment paramEnvironment, NameLookup paramNameLookup) {
    if (paramNameLookup == null) {
      paramEnvironment.remove(KEY);
      return;
    } 
    paramEnvironment.put(KEY, null, paramNameLookup);
  }
  
  public Language getLanguage() {
    return this.language;
  }
  
  public Declaration lookup(Object paramObject, int paramInt) {
    int i = hashToIndex(hash(paramObject));
    for (HashNode hashNode = ((HashNode[])this.table)[i]; hashNode != null; hashNode = hashNode.next) {
      Declaration declaration = (Declaration)hashNode.getValue();
      if (paramObject.equals(declaration.getSymbol()) && this.language.hasNamespace(declaration, paramInt))
        return declaration; 
    } 
    return null;
  }
  
  public Declaration lookup(Object paramObject, boolean paramBoolean) {
    if (paramBoolean) {
      byte b = 2;
      return lookup(paramObject, b);
    } 
    boolean bool = true;
    return lookup(paramObject, bool);
  }
  
  public void pop(ScopeExp paramScopeExp) {
    for (Declaration declaration = paramScopeExp.firstDecl(); declaration != null; declaration = declaration.nextDecl())
      pop(declaration); 
  }
  
  public boolean pop(Declaration paramDeclaration) {
    Object object1 = paramDeclaration.getSymbol();
    if (object1 == null)
      return false; 
    int i = hash(object1);
    Object object2 = null;
    i = hashToIndex(i);
    for (object1 = ((HashNode[])this.table)[i]; object1 != null; object1 = hashNode) {
      HashNode hashNode = ((HashNode)object1).next;
      if (object1.getValue() == paramDeclaration) {
        if (object2 == null) {
          ((HashNode[])this.table)[i] = hashNode;
          this.num_bindings--;
          return true;
        } 
        ((HashNode)object2).next = hashNode;
        this.num_bindings--;
        return true;
      } 
      object2 = object1;
    } 
    return false;
  }
  
  public void push(Declaration paramDeclaration) {
    Object object = paramDeclaration.getSymbol();
    if (object == null)
      return; 
    int i = this.num_bindings + 1;
    this.num_bindings = i;
    if (i >= ((HashNode[])this.table).length)
      rehash(); 
    i = hash(object);
    HashNode hashNode = makeEntry(object, i, paramDeclaration);
    i = hashToIndex(i);
    hashNode.next = ((HashNode[])this.table)[i];
    ((HashNode[])this.table)[i] = hashNode;
  }
  
  public void push(ScopeExp paramScopeExp) {
    for (Declaration declaration = paramScopeExp.firstDecl(); declaration != null; declaration = declaration.nextDecl())
      push(declaration); 
  }
  
  public void removeSubsumed(Declaration paramDeclaration) {
    int i = hashToIndex(hash(paramDeclaration.getSymbol()));
    HashNode hashNode2 = null;
    for (HashNode hashNode1 = ((HashNode[])this.table)[i]; hashNode1 != null; hashNode1 = hashNode) {
      HashNode hashNode = hashNode1.next;
      Declaration declaration = (Declaration)hashNode1.getValue();
      if (declaration != paramDeclaration && subsumedBy(paramDeclaration, declaration)) {
        if (hashNode2 == null) {
          ((HashNode[])this.table)[i] = hashNode;
        } else {
          hashNode2.next = hashNode;
        } 
      } else {
        hashNode2 = hashNode1;
      } 
    } 
  }
  
  public void setLanguage(Language paramLanguage) {
    this.language = paramLanguage;
  }
  
  protected boolean subsumedBy(Declaration paramDeclaration1, Declaration paramDeclaration2) {
    return (paramDeclaration1.getSymbol() == paramDeclaration2.getSymbol() && (this.language.getNamespaceOf(paramDeclaration1) & this.language.getNamespaceOf(paramDeclaration2)) != 0);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/NameLookup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
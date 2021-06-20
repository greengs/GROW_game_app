package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;

public abstract class ScopeExp extends Expression {
  static int counter;
  
  Declaration decls;
  
  protected int frameSize;
  
  public int id;
  
  Declaration last;
  
  public ScopeExp outer;
  
  private Scope scope;
  
  public ScopeExp() {
    int i = counter + 1;
    counter = i;
    this.id = i;
  }
  
  public static void duplicateDeclarationError(Declaration paramDeclaration1, Declaration paramDeclaration2, Compilation paramCompilation) {
    paramCompilation.error('e', paramDeclaration2, "duplicate declaration of '", "'");
    paramCompilation.error('e', paramDeclaration1, "(this is the previous declaration of '", "')");
  }
  
  public static int nesting(ScopeExp paramScopeExp) {
    int i;
    for (i = 0; paramScopeExp != null; i++)
      paramScopeExp = paramScopeExp.outer; 
    return i;
  }
  
  public void add(Declaration paramDeclaration) {
    if (this.last == null) {
      this.decls = paramDeclaration;
    } else {
      this.last.next = paramDeclaration;
    } 
    this.last = paramDeclaration;
    paramDeclaration.context = this;
  }
  
  public void add(Declaration paramDeclaration1, Declaration paramDeclaration2) {
    if (paramDeclaration1 == null) {
      paramDeclaration2.next = this.decls;
      this.decls = paramDeclaration2;
    } else {
      paramDeclaration2.next = paramDeclaration1.next;
      paramDeclaration1.next = paramDeclaration2;
    } 
    if (this.last == paramDeclaration1)
      this.last = paramDeclaration2; 
    paramDeclaration2.context = this;
  }
  
  public final Declaration addDeclaration(Object paramObject) {
    paramObject = new Declaration(paramObject);
    add((Declaration)paramObject);
    return (Declaration)paramObject;
  }
  
  public final Declaration addDeclaration(Object paramObject, Type paramType) {
    paramObject = new Declaration(paramObject, paramType);
    add((Declaration)paramObject);
    return (Declaration)paramObject;
  }
  
  public final void addDeclaration(Declaration paramDeclaration) {
    add(paramDeclaration);
  }
  
  public int countDecls() {
    int i = 0;
    for (Declaration declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl())
      i++; 
    return i;
  }
  
  public int countNonDynamicDecls() {
    int i = 0;
    Declaration declaration = firstDecl();
    while (declaration != null) {
      int j = i;
      if (!declaration.getFlag(268435456L))
        j = i + 1; 
      declaration = declaration.nextDecl();
      i = j;
    } 
    return i;
  }
  
  public LambdaExp currentLambda() {
    for (ScopeExp scopeExp = this;; scopeExp = scopeExp.outer) {
      if (scopeExp == null)
        return null; 
      if (scopeExp instanceof LambdaExp)
        return (LambdaExp)scopeExp; 
    } 
  }
  
  public ModuleExp currentModule() {
    for (ScopeExp scopeExp = this;; scopeExp = scopeExp.outer) {
      if (scopeExp == null)
        return null; 
      if (scopeExp instanceof ModuleExp)
        return (ModuleExp)scopeExp; 
    } 
  }
  
  public Declaration firstDecl() {
    return this.decls;
  }
  
  public Declaration getDefine(Object paramObject, char paramChar, Compilation paramCompilation) {
    Declaration declaration = lookup(paramObject);
    if (declaration == null)
      return addDeclaration(paramObject); 
    if ((declaration.flags & 0x10200L) != 0L) {
      declaration.flags &= 0xFFFFFFFFFFFEFDFFL;
      return declaration;
    } 
    paramObject = addDeclaration(paramObject);
    duplicateDeclarationError(declaration, (Declaration)paramObject, paramCompilation);
    return (Declaration)paramObject;
  }
  
  public Declaration getNoDefine(Object paramObject) {
    Declaration declaration2 = lookup(paramObject);
    Declaration declaration1 = declaration2;
    if (declaration2 == null) {
      declaration1 = addDeclaration(paramObject);
      declaration1.flags |= 0x10200L;
    } 
    return declaration1;
  }
  
  public Scope getVarScope() {
    Scope scope2 = this.scope;
    Scope scope1 = scope2;
    if (scope2 == null) {
      scope1 = new Scope();
      this.scope = scope1;
    } 
    return scope1;
  }
  
  public Declaration lookup(Object paramObject) {
    if (paramObject != null)
      for (Declaration declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl()) {
        if (paramObject.equals(declaration.symbol))
          return declaration; 
      }  
    return null;
  }
  
  public Declaration lookup(Object paramObject, Language paramLanguage, int paramInt) {
    for (Declaration declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl()) {
      if (paramObject.equals(declaration.symbol) && paramLanguage.hasNamespace(declaration, paramInt))
        return declaration; 
    } 
    return null;
  }
  
  public boolean nestedIn(ScopeExp paramScopeExp) {
    for (ScopeExp scopeExp = this; scopeExp != null; scopeExp = scopeExp.outer) {
      if (scopeExp == paramScopeExp)
        return true; 
    } 
    return false;
  }
  
  public void popScope(CodeAttr paramCodeAttr) {
    for (Declaration declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl())
      declaration.var = null; 
    paramCodeAttr.popScope();
    this.scope = null;
  }
  
  public void remove(Declaration paramDeclaration) {
    Declaration declaration2 = null;
    for (Declaration declaration1 = firstDecl();; declaration1 = declaration1.nextDecl()) {
      if (declaration1 != null) {
        if (declaration1 == paramDeclaration) {
          remove(declaration2, paramDeclaration);
          return;
        } 
      } else {
        return;
      } 
      declaration2 = declaration1;
    } 
  }
  
  public void remove(Declaration paramDeclaration1, Declaration paramDeclaration2) {
    if (paramDeclaration1 == null) {
      this.decls = paramDeclaration2.next;
    } else {
      paramDeclaration1.next = paramDeclaration2.next;
    } 
    if (this.last == paramDeclaration2)
      this.last = paramDeclaration1; 
  }
  
  public void replaceFollowing(Declaration paramDeclaration1, Declaration paramDeclaration2) {
    if (paramDeclaration1 == null) {
      paramDeclaration1 = this.decls;
      this.decls = paramDeclaration2;
    } else {
      Declaration declaration = paramDeclaration1.next;
      paramDeclaration1.next = paramDeclaration2;
      paramDeclaration1 = declaration;
    } 
    paramDeclaration2.next = paramDeclaration1.next;
    if (this.last == paramDeclaration1)
      this.last = paramDeclaration2; 
    paramDeclaration1.next = null;
    paramDeclaration2.context = this;
  }
  
  protected void setIndexes() {
    Declaration declaration = firstDecl();
    int i;
    for (i = 0; declaration != null; i++) {
      declaration.evalIndex = i;
      declaration = declaration.nextDecl();
    } 
    this.frameSize = i;
  }
  
  public String toString() {
    return getClass().getName() + "#" + this.id;
  }
  
  public ScopeExp topLevel() {
    for (ScopeExp scopeExp = this;; scopeExp = scopeExp1) {
      ScopeExp scopeExp1 = scopeExp.outer;
      if (scopeExp1 == null || scopeExp1 instanceof ModuleExp)
        return scopeExp; 
    } 
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitScopeExp(this, paramD);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ScopeExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
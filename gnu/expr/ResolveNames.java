package gnu.expr;

public class ResolveNames extends ExpExpVisitor<Void> {
  protected NameLookup lookup;
  
  public ResolveNames() {}
  
  public ResolveNames(Compilation paramCompilation) {
    setContext(paramCompilation);
    this.lookup = paramCompilation.lexical;
  }
  
  public Declaration lookup(Expression paramExpression, Object paramObject, boolean paramBoolean) {
    return this.lookup.lookup(paramObject, paramBoolean);
  }
  
  protected void push(ScopeExp paramScopeExp) {
    this.lookup.push(paramScopeExp);
  }
  
  public void resolveModule(ModuleExp paramModuleExp) {
    Compilation compilation = Compilation.setSaveCurrent(this.comp);
    try {
      push(paramModuleExp);
      paramModuleExp.visitChildren(this, null);
      return;
    } finally {
      Compilation.restoreCurrent(compilation);
    } 
  }
  
  protected Expression visitLetExp(LetExp paramLetExp, Void paramVoid) {
    visitDeclarationTypes(paramLetExp);
    paramLetExp.visitInitializers(this, paramVoid);
    push(paramLetExp);
    paramLetExp.body = visit(paramLetExp.body, paramVoid);
    this.lookup.pop(paramLetExp);
    return paramLetExp;
  }
  
  protected Expression visitReferenceExp(ReferenceExp paramReferenceExp, Void paramVoid) {
    if (paramReferenceExp.getBinding() == null) {
      Declaration declaration = lookup(paramReferenceExp, paramReferenceExp.getSymbol(), paramReferenceExp.isProcedureName());
      if (declaration != null)
        paramReferenceExp.setBinding(declaration); 
    } 
    return paramReferenceExp;
  }
  
  protected Expression visitScopeExp(ScopeExp paramScopeExp, Void paramVoid) {
    visitDeclarationTypes(paramScopeExp);
    push(paramScopeExp);
    paramScopeExp.visitChildren(this, paramVoid);
    this.lookup.pop(paramScopeExp);
    return paramScopeExp;
  }
  
  protected Expression visitSetExp(SetExp paramSetExp, Void paramVoid) {
    if (paramSetExp.binding == null) {
      Declaration declaration = lookup(paramSetExp, paramSetExp.getSymbol(), paramSetExp.isFuncDef());
      if (declaration != null)
        declaration.setCanWrite(true); 
      paramSetExp.binding = declaration;
    } 
    return super.visitSetExp(paramSetExp, paramVoid);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ResolveNames.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
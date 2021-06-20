package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.expr.ThisExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import kawa.lang.Macro;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_syntax extends Syntax {
  public static final define_syntax define_macro = new define_syntax("%define-macro", false);
  
  public static final define_syntax define_syntax = new define_syntax("%define-syntax", true);
  
  static PrimProcedure makeHygienic;
  
  static PrimProcedure makeNonHygienic;
  
  static PrimProcedure setCapturedScope;
  
  static ClassType typeMacro = ClassType.make("kawa.lang.Macro");
  
  boolean hygienic = true;
  
  static {
    makeHygienic = new PrimProcedure(typeMacro.getDeclaredMethod("make", 3));
    makeNonHygienic = new PrimProcedure(typeMacro.getDeclaredMethod("makeNonHygienic", 3));
    setCapturedScope = new PrimProcedure(typeMacro.getDeclaredMethod("setCapturedScope", 1));
    makeHygienic.setSideEffectFree();
    makeNonHygienic.setSideEffectFree();
  }
  
  public define_syntax() {}
  
  public define_syntax(Object paramObject, boolean paramBoolean) {
    super(paramObject);
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    return paramTranslator.syntaxError("define-syntax not in a body");
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator) {
    PrimProcedure primProcedure;
    SyntaxForm syntaxForm1 = null;
    Object object1;
    for (object1 = paramPair.getCdr(); object1 instanceof SyntaxForm; object1 = syntaxForm1.getDatum())
      syntaxForm1 = (SyntaxForm)object1; 
    Object object2 = object1;
    if (object2 instanceof Pair) {
      object2 = object2;
      object1 = object2.getCar();
      object2 = object2.getCdr();
    } else {
      object1 = null;
    } 
    SyntaxForm syntaxForm2 = syntaxForm1;
    while (object1 instanceof SyntaxForm) {
      syntaxForm2 = (SyntaxForm)object1;
      object1 = syntaxForm2.getDatum();
    } 
    Object object3 = paramTranslator.namespaceResolve(object1);
    if (!(object3 instanceof gnu.mapping.Symbol)) {
      paramTranslator.formStack.addElement(paramTranslator.syntaxError("missing macro name for " + Translator.safeCar(paramPair)));
      return;
    } 
    if (object2 == null || Translator.safeCdr(object2) != LList.Empty) {
      paramTranslator.formStack.addElement(paramTranslator.syntaxError("invalid syntax for " + getName()));
      return;
    } 
    object1 = paramTranslator.define(object3, syntaxForm2, paramScopeExp);
    object1.setType((Type)typeMacro);
    paramTranslator.push((Declaration)object1);
    Macro macro1 = paramTranslator.currentMacroDefinition;
    Macro macro2 = Macro.make((Declaration)object1);
    macro2.setHygienic(this.hygienic);
    paramTranslator.currentMacroDefinition = macro2;
    Expression expression = paramTranslator.rewrite_car((Pair)object2, syntaxForm1);
    paramTranslator.currentMacroDefinition = macro1;
    macro2.expander = expression;
    if (expression instanceof LambdaExp)
      ((LambdaExp)expression).setFlag(256); 
    object2 = new QuoteExp(object3);
    ThisExp thisExp = ThisExp.makeGivingContext(paramScopeExp);
    if (this.hygienic) {
      primProcedure = makeHygienic;
    } else {
      primProcedure = makeNonHygienic;
    } 
    ApplyExp applyExp = new ApplyExp((Procedure)primProcedure, new Expression[] { (Expression)object2, expression, (Expression)thisExp });
    object1.noteValue((Expression)applyExp);
    object1.setProcedureDecl(true);
    if (((Declaration)object1).context instanceof gnu.expr.ModuleExp) {
      SetExp setExp = new SetExp((Declaration)object1, (Expression)applyExp);
      setExp.setDefining(true);
      if (paramTranslator.getLanguage().hasSeparateFunctionNamespace())
        setExp.setFuncDef(true); 
      paramTranslator.formStack.addElement(setExp);
      if (paramTranslator.immediate) {
        ReferenceExp referenceExp = new ReferenceExp((Declaration)object1);
        QuoteExp quoteExp = new QuoteExp(paramScopeExp);
        paramTranslator.formStack.addElement(new ApplyExp((Procedure)setCapturedScope, new Expression[] { (Expression)referenceExp, (Expression)quoteExp }));
        return;
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/define_syntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
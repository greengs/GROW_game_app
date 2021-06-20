package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.PatternScope;
import kawa.lang.Quote;
import kawa.lang.SyntaxTemplate;
import kawa.lang.Translator;

public class syntax extends Quote {
  static final Method makeTemplateScopeMethod;
  
  public static final syntax quasiSyntax;
  
  public static final syntax syntax = new syntax("syntax", false);
  
  static final ClassType typeTemplateScope;
  
  static {
    quasiSyntax = new syntax("quasisyntax", true);
    typeTemplateScope = ClassType.make("kawa.lang.TemplateScope");
    makeTemplateScopeMethod = typeTemplateScope.getDeclaredMethod("make", 0);
  }
  
  public syntax(String paramString, boolean paramBoolean) {
    super(paramString, paramBoolean);
  }
  
  static Expression makeSyntax(Object paramObject, Translator paramTranslator) {
    SyntaxTemplate syntaxTemplate = new SyntaxTemplate(paramObject, null, paramTranslator);
    QuoteExp quoteExp = QuoteExp.nullExp;
    PatternScope patternScope = paramTranslator.patternScope;
    paramObject = quoteExp;
    if (patternScope != null) {
      paramObject = quoteExp;
      if (patternScope.matchArray != null)
        paramObject = new ReferenceExp(patternScope.matchArray); 
    } 
    quoteExp = new QuoteExp(syntaxTemplate);
    ReferenceExp referenceExp = new ReferenceExp(paramTranslator.templateScopeDecl);
    return (Expression)new ApplyExp(ClassType.make("kawa.lang.SyntaxTemplate").getDeclaredMethod("execute", 2), new Expression[] { (Expression)quoteExp, (Expression)paramObject, (Expression)referenceExp });
  }
  
  protected boolean expandColonForms() {
    return false;
  }
  
  protected Expression leaf(Object paramObject, Translator paramTranslator) {
    return makeSyntax(paramObject, paramTranslator);
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    if (paramPair.getCdr() instanceof Pair) {
      paramPair = (Pair)paramPair.getCdr();
      if (paramPair.getCdr() == LList.Empty) {
        Declaration declaration = paramTranslator.templateScopeDecl;
        if (declaration == null) {
          paramTranslator.letStart();
          ApplyExp applyExp = new ApplyExp(makeTemplateScopeMethod, Expression.noExpressions);
          Declaration declaration1 = paramTranslator.letVariable(null, (Type)typeTemplateScope, (Expression)applyExp);
          declaration1.setCanRead();
          paramTranslator.templateScopeDecl = declaration1;
          paramTranslator.letEnter();
        } 
        try {
          byte b;
          Object object = paramPair.getCar();
          if (this.isQuasi) {
            b = 1;
          } else {
            b = -1;
          } 
          Expression expression = coerceExpression(expand(object, b, paramTranslator), paramTranslator);
          object = expression;
          if (declaration == null)
            object = paramTranslator.letDone(expression); 
          return (Expression)object;
        } finally {
          paramTranslator.templateScopeDecl = declaration;
        } 
      } 
    } 
    return paramTranslator.syntaxError("syntax forms requires a single form");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/syntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
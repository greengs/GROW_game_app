package kawa.standard;

import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.LangExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define extends Syntax {
  public static final define defineRaw = new define(SchemeCompilation.lambda);
  
  Lambda lambda;
  
  public define(Lambda paramLambda) {
    this.lambda = paramLambda;
  }
  
  String getName(int paramInt) {
    return ((paramInt & 0x4) != 0) ? "define-private" : (((paramInt & 0x8) != 0) ? "define-constant" : "define");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    boolean bool;
    Pair pair = (Pair)paramPair.getCdr();
    paramPair = (Pair)pair.getCdr();
    Object object2 = ((Pair)paramPair.getCdr()).getCdr();
    Object object3 = Translator.stripSyntax(pair.getCar());
    int i = ((Number)Translator.stripSyntax(paramPair.getCar())).intValue();
    if ((i & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    if (!(object3 instanceof Declaration))
      return paramTranslator.syntaxError(getName(i) + " is only allowed in a <body>"); 
    object3 = object3;
    if (object3.getFlag(8192L)) {
      Expression expression = object3.getTypeExp();
      if (expression instanceof LangExp)
        object3.setType(paramTranslator.exp2Type((Pair)((LangExp)expression).getLangValue())); 
    } 
    if ((i & 0x2) != 0) {
      LambdaExp lambdaExp = (LambdaExp)object3.getValue();
      object2 = object2.getCdr();
      this.lambda.rewriteBody(lambdaExp, object2, paramTranslator);
      object2 = lambdaExp;
      if (!Compilation.inlineOk) {
        object3.noteValue(null);
        object2 = lambdaExp;
      } 
    } else {
      Expression expression = paramTranslator.rewrite(object2.getCar());
      if (((Declaration)object3).context instanceof gnu.expr.ModuleExp && !bool && object3.getCanWrite()) {
        object2 = null;
      } else {
        object2 = expression;
      } 
      object3.noteValue((Expression)object2);
      object2 = expression;
    } 
    object2 = new SetExp((Declaration)object3, (Expression)object2);
    object2.setDefining(true);
    Object object1 = object2;
    if (bool) {
      object1 = object2;
      if (!(paramTranslator.currentScope() instanceof gnu.expr.ModuleExp)) {
        paramTranslator.error('w', "define-private not at top level " + paramTranslator.currentScope());
        return (Expression)object2;
      } 
    } 
    return (Expression)object1;
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator) {
    boolean bool1;
    boolean bool2;
    Pair pair3 = (Pair)paramPair.getCdr();
    Pair pair2 = (Pair)pair3.getCdr();
    Pair pair4 = (Pair)pair2.getCdr();
    Pair pair5 = (Pair)pair4.getCdr();
    SyntaxForm syntaxForm = null;
    Object object1;
    for (object1 = pair3.getCar(); object1 instanceof SyntaxForm; object1 = syntaxForm.getDatum())
      syntaxForm = (SyntaxForm)object1; 
    int i = ((Number)Translator.stripSyntax(pair2.getCar())).intValue();
    if ((i & 0x4) != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if ((i & 0x8) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    paramTranslator.currentScope();
    Object object2 = paramTranslator.namespaceResolve(object1);
    object1 = object2;
    if (!(object2 instanceof gnu.mapping.Symbol)) {
      paramTranslator.error('e', "'" + object2 + "' is not a valid identifier");
      object1 = null;
    } 
    object2 = paramTranslator.pushPositionOf(pair3);
    object1 = paramTranslator.define(object1, syntaxForm, paramScopeExp);
    paramTranslator.popPositionOf(object2);
    Object object3 = object1.getSymbol();
    if (bool1) {
      object1.setFlag(16777216L);
      object1.setPrivate(true);
    } 
    if (bool2)
      object1.setFlag(16384L); 
    object1.setFlag(262144L);
    Pair pair1 = pair2;
    if ((i & 0x2) != 0) {
      object2 = new LambdaExp();
      object2.setSymbol(object3);
      if (Compilation.inlineOk) {
        object1.setProcedureDecl(true);
        object1.setType((Type)Compilation.typeProcedure);
        ((LambdaExp)object2).nameDecl = (Declaration)object1;
      } 
      object3 = pair5.getCar();
      Object object4 = pair5.getCdr();
      Translator.setLine((Expression)object2, pair3);
      this.lambda.rewriteFormals((LambdaExp)object2, object3, paramTranslator, null);
      Object object5 = this.lambda.rewriteAttrs((LambdaExp)object2, object4, paramTranslator);
      pair1 = pair2;
      if (object5 != object4)
        pair1 = new Pair(pair2.getCar(), new Pair(pair4.getCar(), new Pair(object3, object5))); 
      object1.noteValue((Expression)object2);
    } 
    if (paramScopeExp instanceof gnu.expr.ModuleExp && !bool1 && (!Compilation.inlineOk || paramTranslator.sharedModuleDefs()))
      object1.setCanWrite(true); 
    if ((i & 0x1) != 0) {
      object1.setTypeExp((Expression)new LangExp(pair4));
      object1.setFlag(8192L);
    } 
    paramPair = Translator.makePair(paramPair, this, Translator.makePair(pair3, object1, pair1));
    Translator.setLine((Declaration)object1, pair3);
    paramTranslator.formStack.addElement(paramPair);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/define.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
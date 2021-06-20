package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BlockExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ExitExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.math.IntNum;
import kawa.lang.Pattern;
import kawa.lang.PatternScope;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxPattern;
import kawa.lang.Translator;

public class syntax_case extends Syntax {
  public static final syntax_case syntax_case = new syntax_case();
  
  PrimProcedure call_error;
  
  static {
    syntax_case.setName("syntax-case");
  }
  
  public static Object error(String paramString, Object paramObject) {
    paramObject = Compilation.getCurrent();
    if (paramObject == null)
      throw new RuntimeException("no match in syntax-case"); 
    Syntax syntax = paramObject.getCurrentSyntax();
    if (syntax == null) {
      str = "some syntax";
      return paramObject.syntaxError("no matching case while expanding " + str);
    } 
    String str = str.getName();
    return paramObject.syntaxError("no matching case while expanding " + str);
  }
  
  Expression rewriteClauses(Object paramObject, syntax_case_work paramsyntax_case_work, Translator paramTranslator) {
    ReferenceExp referenceExp;
    ClassType classType;
    Language language = paramTranslator.getLanguage();
    if (paramObject == LList.Empty) {
      paramObject = new QuoteExp("syntax-case");
      referenceExp = new ReferenceExp(paramsyntax_case_work.inputExpression);
      if (this.call_error == null) {
        classType = ClassType.make("kawa.standard.syntax_case");
        ClassType classType1 = Compilation.javaStringType;
        ClassType classType2 = Type.objectType;
        ClassType classType3 = Type.objectType;
        this.call_error = new PrimProcedure(classType.addMethod("error", new Type[] { (Type)classType1, (Type)classType2 }, (Type)classType3, 9), language);
      } 
      return (Expression)new ApplyExp((Procedure)this.call_error, new Expression[] { (Expression)paramObject, (Expression)referenceExp });
    } 
    Object object = classType.pushPositionOf(paramObject);
    try {
      if (paramObject instanceof Pair) {
        Object object1 = ((Pair)paramObject).getCar();
        if (object1 instanceof Pair) {
          Pair pair = (Pair)object1;
          PatternScope patternScope = PatternScope.push((Translator)classType);
          patternScope.matchArray = ((Translator)classType).matchArray;
          classType.push((ScopeExp)patternScope);
          object1 = null;
          Object object2;
          for (object2 = pair.getCdr(); object2 instanceof SyntaxForm; object2 = object1.getDatum())
            object1 = object2; 
          if (!(object2 instanceof Pair)) {
            paramObject = classType.syntaxError("missing syntax-case output expression");
            return (Expression)paramObject;
          } 
          int i = patternScope.pattern_names.size();
          SyntaxPattern syntaxPattern = new SyntaxPattern(pair.getCar(), ((syntax_case_work)referenceExp).literal_identifiers, (Translator)classType);
          int j = syntaxPattern.varCount();
          if (j > ((syntax_case_work)referenceExp).maxVars)
            ((syntax_case_work)referenceExp).maxVars = j; 
          BlockExp blockExp = new BlockExp();
          QuoteExp quoteExp1 = new QuoteExp(syntaxPattern);
          ReferenceExp referenceExp1 = new ReferenceExp(((syntax_case_work)referenceExp).inputExpression);
          ReferenceExp referenceExp2 = new ReferenceExp(((Translator)classType).matchArray);
          QuoteExp quoteExp2 = new QuoteExp(IntNum.zero());
          ApplyExp applyExp = new ApplyExp((Procedure)new PrimProcedure(Pattern.matchPatternMethod, language), new Expression[] { (Expression)quoteExp1, (Expression)referenceExp1, (Expression)referenceExp2, (Expression)quoteExp2 });
          i = j - i;
          Expression[] arrayOfExpression = new Expression[i];
          while (true) {
            if (--i >= 0) {
              arrayOfExpression[i] = (Expression)QuoteExp.undefined_exp;
              continue;
            } 
            patternScope.inits = arrayOfExpression;
            Pair pair1 = (Pair)object2;
            if (pair1.getCdr() == LList.Empty) {
              object1 = classType.rewrite_car(pair1, (SyntaxForm)object1);
              patternScope.setBody((Expression)object1);
              classType.pop((ScopeExp)patternScope);
              PatternScope.pop((Translator)classType);
              blockExp.setBody((Expression)new IfExp((Expression)applyExp, (Expression)patternScope, (Expression)new ExitExp(blockExp)), rewriteClauses(((Pair)paramObject).getCdr(), (syntax_case_work)referenceExp, (Translator)classType));
              return (Expression)blockExp;
            } 
            object2 = classType.rewrite_car(pair1, (SyntaxForm)object1);
            if (pair1.getCdr() instanceof Pair) {
              pair1 = (Pair)pair1.getCdr();
              if (pair1.getCdr() != LList.Empty) {
                paramObject = classType.syntaxError("syntax-case:  bad clause");
                return (Expression)paramObject;
              } 
            } else {
              paramObject = classType.syntaxError("syntax-case:  bad clause");
              return (Expression)paramObject;
            } 
            object1 = new IfExp((Expression)object2, classType.rewrite_car(pair1, (SyntaxForm)object1), (Expression)new ExitExp(blockExp));
            patternScope.setBody((Expression)object1);
            classType.pop((ScopeExp)patternScope);
            PatternScope.pop((Translator)classType);
            blockExp.setBody((Expression)new IfExp((Expression)applyExp, (Expression)patternScope, (Expression)new ExitExp(blockExp)), rewriteClauses(((Pair)paramObject).getCdr(), (syntax_case_work)referenceExp, (Translator)classType));
            return (Expression)blockExp;
          } 
        } 
      } 
      paramObject = classType.syntaxError("syntax-case:  bad clause list");
      return (Expression)paramObject;
    } finally {
      classType.popPositionOf(object);
    } 
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    syntax_case_work syntax_case_work = new syntax_case_work();
    Object object = paramPair.getCdr();
    if (object instanceof Pair && ((Pair)object).getCdr() instanceof Pair) {
      Expression[] arrayOfExpression1 = new Expression[2];
      LetExp letExp = new LetExp(arrayOfExpression1);
      syntax_case_work.inputExpression = letExp.addDeclaration(null);
      Declaration declaration1 = paramTranslator.matchArray;
      Declaration declaration2 = letExp.addDeclaration(null);
      declaration2.setType((Type)Compilation.objArrayType);
      declaration2.setCanRead(true);
      paramTranslator.matchArray = declaration2;
      syntax_case_work.inputExpression.setCanRead(true);
      paramTranslator.push((ScopeExp)letExp);
      object = object;
      arrayOfExpression1[0] = paramTranslator.rewrite(object.getCar());
      syntax_case_work.inputExpression.noteValue(arrayOfExpression1[0]);
      object = object.getCdr();
      syntax_case_work.literal_identifiers = SyntaxPattern.getLiteralsList(object.getCar(), null, paramTranslator);
      letExp.body = rewriteClauses(object.getCdr(), syntax_case_work, paramTranslator);
      paramTranslator.pop((ScopeExp)letExp);
      object = ClassType.make("kawa.lang.SyntaxPattern").getDeclaredMethod("allocVars", 2);
      Expression[] arrayOfExpression2 = new Expression[2];
      arrayOfExpression2[0] = (Expression)new QuoteExp(IntNum.make(syntax_case_work.maxVars));
      if (declaration1 == null) {
        arrayOfExpression2[1] = (Expression)QuoteExp.nullExp;
        arrayOfExpression1[1] = (Expression)new ApplyExp((Method)object, arrayOfExpression2);
        declaration2.noteValue(arrayOfExpression1[1]);
        paramTranslator.matchArray = declaration1;
        return (Expression)letExp;
      } 
      arrayOfExpression2[1] = (Expression)new ReferenceExp(declaration1);
      arrayOfExpression1[1] = (Expression)new ApplyExp((Method)object, arrayOfExpression2);
      declaration2.noteValue(arrayOfExpression1[1]);
      paramTranslator.matchArray = declaration1;
      return (Expression)letExp;
    } 
    return paramTranslator.syntaxError("insufficiant arguments to syntax-case");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/syntax_case.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.kawa.lispexpr;

import gnu.bytecode.Field;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.expr.ScopeExp;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Sequence;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import kawa.lang.Translator;

public abstract class LispLanguage extends Language {
  public static final Symbol bracket_apply_sym;
  
  public static final Symbol bracket_list_sym;
  
  public static StaticFieldLocation getNamedPartLocation;
  
  public static final Symbol lookup_sym = Namespace.EmptyNamespace.getSymbol("$lookup$");
  
  public static final String quasiquote_sym = "quasiquote";
  
  public static final String quote_sym = "quote";
  
  public static final String unquote_sym = "unquote";
  
  public static final String unquotesplicing_sym = "unquote-splicing";
  
  public ReadTable defaultReadTable = createReadTable();
  
  static {
    bracket_list_sym = Namespace.EmptyNamespace.getSymbol("$bracket-list$");
    bracket_apply_sym = Namespace.EmptyNamespace.getSymbol("$bracket-apply$");
    getNamedPartLocation = new StaticFieldLocation("gnu.kawa.functions.GetNamedPart", "getNamedPart");
    getNamedPartLocation.setProcedure();
  }
  
  public static Symbol langSymbolToSymbol(Object paramObject) {
    return ((LispLanguage)Language.getDefaultLanguage()).fromLangSymbol(paramObject);
  }
  
  public Expression checkDefaultBinding(Symbol paramSymbol, Translator paramTranslator) {
    return null;
  }
  
  public abstract ReadTable createReadTable();
  
  public Declaration declFromField(ModuleExp paramModuleExp, Object paramObject, Field paramField) {
    boolean bool;
    Declaration declaration = super.declFromField(paramModuleExp, paramObject, paramField);
    if ((paramField.getModifiers() & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool && paramObject instanceof kawa.lang.Syntax)
      declaration.setSyntax(); 
    return declaration;
  }
  
  protected void defSntxStFld(String paramString1, String paramString2) {
    defSntxStFld(paramString1, paramString2, Compilation.mangleNameIfNeeded(paramString1));
  }
  
  protected void defSntxStFld(String paramString1, String paramString2, String paramString3) {
    Object object;
    if (hasSeparateFunctionNamespace()) {
      object = EnvironmentKey.FUNCTION;
    } else {
      object = null;
    } 
    StaticFieldLocation.define(this.environ, this.environ.getSymbol(paramString1), object, paramString2, paramString3).setSyntax();
  }
  
  protected Symbol fromLangSymbol(Object paramObject) {
    return (paramObject instanceof String) ? getSymbol((String)paramObject) : (Symbol)paramObject;
  }
  
  public Compilation getCompilation(Lexer paramLexer, SourceMessages paramSourceMessages, NameLookup paramNameLookup) {
    return (Compilation)new Translator(this, paramSourceMessages, paramNameLookup);
  }
  
  public Lexer getLexer(InPort paramInPort, SourceMessages paramSourceMessages) {
    return new LispReader((LineBufferedReader)paramInPort, paramSourceMessages);
  }
  
  public Expression makeApply(Expression paramExpression, Expression[] paramArrayOfExpression) {
    return (Expression)new ApplyExp(paramExpression, paramArrayOfExpression);
  }
  
  public Expression makeBody(Expression[] paramArrayOfExpression) {
    return (Expression)new BeginExp(paramArrayOfExpression);
  }
  
  public boolean parse(Compilation paramCompilation, int paramInt) throws IOException, SyntaxException {
    null = (Translator)paramCompilation;
    Lexer lexer = null.lexer;
    ModuleExp moduleExp = null.mainLambda;
    new Values();
    LispReader lispReader = (LispReader)lexer;
    paramCompilation = Compilation.setSaveCurrent((Compilation)null);
    try {
      if (null.pendingForm != null) {
        null.scanForm(null.pendingForm, (ScopeExp)moduleExp);
        null.pendingForm = null;
      } 
      while (true) {
        Object object1 = lispReader.readCommand();
        Object object2 = Sequence.eofValue;
        if (object1 == object2) {
          if ((paramInt & 0x4) != 0)
            return false; 
          continue;
        } 
        null.scanForm(object1, (ScopeExp)moduleExp);
        if ((paramInt & 0x4) != 0) {
          if (null.getMessages().seenErrors()) {
            while (true) {
              int i = lispReader.peek();
              if (i < 0 || i == 13 || i == 10) {
                if (lexer.peek() == 41)
                  lexer.fatal("An unexpected close paren was read."); 
                null.finishModule(moduleExp);
                if ((paramInt & 0x8) == 0)
                  null.firstForm = 0; 
                null.setState(4);
                return true;
              } 
              lispReader.skip();
            } 
            break;
          } 
          continue;
        } 
        if ((paramInt & 0x8) != 0) {
          int i = null.getState();
          if (i >= 2)
            return true; 
        } 
      } 
    } finally {
      Compilation.restoreCurrent(paramCompilation);
    } 
  }
  
  public void resolve(Compilation paramCompilation) {
    Translator translator = (Translator)paramCompilation;
    translator.resolveModule(translator.getModule());
  }
  
  public boolean selfEvaluatingSymbol(Object paramObject) {
    return paramObject instanceof gnu.expr.Keyword;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/LispLanguage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
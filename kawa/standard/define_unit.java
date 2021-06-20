package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import gnu.math.BaseUnit;
import gnu.math.NamedUnit;
import gnu.math.Quantity;
import gnu.math.Unit;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_unit extends Syntax {
  public static final define_unit define_base_unit;
  
  public static final define_unit define_unit = new define_unit(false);
  
  boolean base;
  
  static {
    define_unit.setName("define-unit");
    define_base_unit = new define_unit(true);
    define_base_unit.setName("define-base-unit");
  }
  
  public define_unit(boolean paramBoolean) {
    this.base = paramBoolean;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    Pair pair;
    Object object = paramPair.getCdr();
    if (object instanceof Pair) {
      pair = (Pair)object;
      if (!(pair.getCar() instanceof Declaration))
        return paramTranslator.syntaxError("invalid syntax for " + getName()); 
    } else {
      return paramTranslator.syntaxError("invalid syntax for " + getName());
    } 
    Declaration declaration = (Declaration)pair.getCar();
    String str = ((Symbol)declaration.getSymbol()).getLocalPart();
    ClassType classType = ClassType.make("gnu.math.Unit");
    declaration.setType((Type)classType);
    object = declaration.getValue();
    if (!(object instanceof QuoteExp) || !(((QuoteExp)object).getValue() instanceof Unit)) {
      SetExp setExp1;
      if (this.base) {
        object = null;
        if (pair.getCdr() != LList.Empty)
          object = ((Pair)pair.getCdr()).getCar().toString(); 
        object = new QuoteExp(BaseUnit.make(str, (String)object));
        setExp1 = new SetExp(declaration, (Expression)object);
        setExp1.setDefining(true);
        declaration.noteValue((Expression)object);
        return (Expression)setExp1;
      } 
      if (!(pair.getCdr() instanceof Pair))
        return setExp1.syntaxError("missing value for define-unit"); 
      object = setExp1.rewrite(((Pair)pair.getCdr()).getCar());
      if (object instanceof QuoteExp) {
        Object object1 = ((QuoteExp)object).getValue();
        if (object1 instanceof Quantity) {
          object = new QuoteExp(Unit.make(str, (Quantity)object1));
          object1 = new SetExp(declaration, (Expression)object);
          object1.setDefining(true);
          declaration.noteValue((Expression)object);
          return (Expression)object1;
        } 
      } 
      object = Invoke.makeInvokeStatic(classType, "make", new Expression[] { (Expression)new QuoteExp(str), (Expression)object });
    } 
    SetExp setExp = new SetExp(declaration, (Expression)object);
    setExp.setDefining(true);
    declaration.noteValue((Expression)object);
    return (Expression)setExp;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector<Pair> paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    if (paramPair.getCdr() instanceof Pair) {
      Pair pair = (Pair)paramPair.getCdr();
      Object object = pair.getCar();
      if (object instanceof gnu.mapping.SimpleSymbol) {
        NamedUnit namedUnit;
        String str = object.toString();
        object = paramScopeExp.getDefine(Scheme.unitNamespace.getSymbol(str), 'w', (Compilation)paramTranslator);
        paramTranslator.push((Declaration)object);
        Translator.setLine((Declaration)object, pair);
        object.setFlag(16384L);
        if (paramScopeExp instanceof gnu.expr.ModuleExp)
          object.setCanRead(true); 
        paramTranslator = null;
        if (this.base && pair.getCdr() == LList.Empty) {
          BaseUnit baseUnit = BaseUnit.make(str, (String)null);
        } else {
          Translator translator = paramTranslator;
          if (pair.getCdr() instanceof Pair) {
            Object object1 = ((Pair)pair.getCdr()).getCar();
            if (this.base && object1 instanceof CharSequence) {
              BaseUnit baseUnit = BaseUnit.make(str, object1.toString());
            } else {
              translator = paramTranslator;
              if (!this.base) {
                translator = paramTranslator;
                if (object1 instanceof Quantity)
                  namedUnit = Unit.make(str, (Quantity)object1); 
              } 
            } 
          } 
        } 
        if (namedUnit != null)
          object.noteValue((Expression)new QuoteExp(namedUnit)); 
        paramVector.addElement(Translator.makePair(paramPair, this, Translator.makePair(pair, object, pair.getCdr())));
        return true;
      } 
    } 
    paramTranslator.error('e', "missing name in define-unit");
    return false;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/define_unit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
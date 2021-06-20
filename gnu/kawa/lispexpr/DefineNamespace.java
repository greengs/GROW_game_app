package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class DefineNamespace extends Syntax {
  public static final String XML_NAMESPACE_MAGIC = "&xml&";
  
  public static final DefineNamespace define_namespace = new DefineNamespace();
  
  public static final DefineNamespace define_private_namespace = new DefineNamespace();
  
  public static final DefineNamespace define_xml_namespace = new DefineNamespace();
  
  private boolean makePrivate;
  
  private boolean makeXML;
  
  static {
    define_namespace.setName("define-namespace");
    define_private_namespace.setName("define-private-namespace");
    define_private_namespace.makePrivate = true;
    define_xml_namespace.setName("define-xml-namespace");
    define_xml_namespace.makeXML = true;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    return paramTranslator.syntaxError("define-namespace is only allowed in a <body>");
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector<SetExp> paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    Pair pair1;
    Pair pair2;
    if (paramPair.getCdr() instanceof Pair) {
      pair1 = (Pair)paramPair.getCdr();
      if (pair1.getCar() instanceof Symbol && pair1.getCdr() instanceof Pair) {
        pair2 = (Pair)pair1.getCdr();
        if (pair2.getCdr() != LList.Empty) {
          paramTranslator.error('e', "invalid syntax for define-namespace");
          return false;
        } 
      } else {
        paramTranslator.error('e', "invalid syntax for define-namespace");
        return false;
      } 
    } else {
      paramTranslator.error('e', "invalid syntax for define-namespace");
      return false;
    } 
    Symbol symbol = (Symbol)pair1.getCar();
    Declaration declaration = paramScopeExp.getDefine(symbol, 'w', (Compilation)paramTranslator);
    paramTranslator.push(declaration);
    declaration.setFlag(2375680L);
    if (this.makePrivate) {
      declaration.setFlag(16777216L);
      declaration.setPrivate(true);
    } else if (paramScopeExp instanceof gnu.expr.ModuleExp) {
      declaration.setCanRead(true);
    } 
    Translator.setLine(declaration, pair1);
    if (pair2.getCar() instanceof CharSequence) {
      Namespace namespace;
      String str = pair2.getCar().toString();
      if (str.startsWith("class:")) {
        namespace = ClassNamespace.getInstance(str, ClassType.make(str.substring(6)));
        declaration.setType((Type)ClassType.make("gnu.kawa.lispexpr.ClassNamespace"));
      } else if (this.makeXML) {
        XmlNamespace xmlNamespace = XmlNamespace.getInstance(namespace.getName(), str);
        declaration.setType((Type)ClassType.make("gnu.kawa.xml.XmlNamespace"));
      } else {
        namespace = Namespace.valueOf(str);
        declaration.setType((Type)ClassType.make("gnu.mapping.Namespace"));
      } 
      QuoteExp quoteExp = new QuoteExp(namespace);
      declaration.setFlag(8192L);
      declaration.noteValue((Expression)quoteExp);
      paramVector.addElement(SetExp.makeDefinition(declaration, (Expression)quoteExp));
      return true;
    } 
    Expression expression = paramTranslator.rewrite_car(pair2, false);
    declaration.noteValue(expression);
    paramVector.addElement(SetExp.makeDefinition(declaration, expression));
    return true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/DefineNamespace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
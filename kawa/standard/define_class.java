package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import gnu.text.SourceLocator;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_class extends Syntax {
  public static final define_class define_class = new define_class("define-class", false);
  
  public static final define_class define_simple_class = new define_class("define-simple-class", true);
  
  boolean isSimple;
  
  object objectSyntax = object.objectSyntax;
  
  define_class(String paramString, boolean paramBoolean) {
    super(paramString);
    this.isSimple = paramBoolean;
  }
  
  define_class(object paramobject, boolean paramBoolean) {
    this.isSimple = paramBoolean;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    Object object1 = null;
    Object object2 = paramPair.getCdr();
    if (object2 instanceof Pair) {
      paramPair = (Pair)object2;
      object1 = paramPair.getCar();
      if (!(object1 instanceof Declaration))
        return paramTranslator.syntaxError(getName() + " can only be used in <body>"); 
      object1 = object1;
    } 
    object2 = object1.getValue();
    this.objectSyntax.rewriteClassDef((Object[])paramPair.getCdr(), paramTranslator);
    SetExp setExp = new SetExp((Declaration)object1, (Expression)object2);
    setExp.setDefining(true);
    return (Expression)setExp;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector<Pair> paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    ClassType classType;
    String str;
    Object object2 = paramPair.getCdr();
    SyntaxForm syntaxForm = null;
    while (object2 instanceof SyntaxForm) {
      syntaxForm = (SyntaxForm)object2;
      object2 = syntaxForm.getDatum();
    } 
    if (!(object2 instanceof Pair))
      return super.scanForDefinitions(paramPair, paramVector, paramScopeExp, paramTranslator); 
    Pair pair = (Pair)object2;
    for (object2 = pair.getCar(); object2 instanceof SyntaxForm; object2 = syntaxForm.getDatum())
      syntaxForm = (SyntaxForm)object2; 
    object2 = paramTranslator.namespaceResolve(object2);
    if (!(object2 instanceof String) && !(object2 instanceof Symbol)) {
      paramTranslator.error('e', "missing class name");
      return false;
    } 
    Declaration declaration = paramTranslator.define(object2, syntaxForm, paramScopeExp);
    if (pair instanceof gnu.lists.PairWithPosition)
      declaration.setLocation((SourceLocator)pair); 
    ClassExp classExp = new ClassExp(this.isSimple);
    declaration.noteValue((Expression)classExp);
    declaration.setFlag(536887296L);
    if (this.isSimple) {
      classType = Compilation.typeClass;
    } else {
      classType = Compilation.typeClassType;
    } 
    declaration.setType((Type)classType);
    paramTranslator.mustCompileHere();
    if (object2 instanceof Symbol) {
      str = ((Symbol)object2).getName();
    } else {
      str = object2.toString();
    } 
    int i = str.length();
    object2 = str;
    if (i > 2) {
      object2 = str;
      if (str.charAt(0) == '<') {
        object2 = str;
        if (str.charAt(i - 1) == '>')
          object2 = str.substring(1, i - 1); 
      } 
    } 
    classExp.setName((String)object2);
    Object object1;
    for (object1 = pair.getCdr(); object1 instanceof SyntaxForm; object1 = syntaxForm.getDatum())
      syntaxForm = (SyntaxForm)object1; 
    if (!(object1 instanceof Pair)) {
      paramTranslator.error('e', "missing class members");
      return false;
    } 
    object1 = object1;
    object2 = paramTranslator.currentScope();
    if (syntaxForm != null)
      paramTranslator.setCurrentScope((ScopeExp)syntaxForm.getScope()); 
    Object[] arrayOfObject = this.objectSyntax.scanClassDef((Pair)object1, classExp, paramTranslator);
    if (syntaxForm != null)
      paramTranslator.setCurrentScope((ScopeExp)object2); 
    if (arrayOfObject == null)
      return false; 
    paramVector.addElement(Translator.makePair(paramPair, this, Translator.makePair((Pair)object1, declaration, arrayOfObject)));
    return true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/define_class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
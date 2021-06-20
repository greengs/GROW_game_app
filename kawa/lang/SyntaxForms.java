package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.lists.ImmutablePair;
import gnu.lists.LList;
import gnu.lists.Pair;

public class SyntaxForms {
  public static final boolean DEBUGGING = true;
  
  public static boolean freeIdentifierEquals(SyntaxForm paramSyntaxForm1, SyntaxForm paramSyntaxForm2) {
    Translator translator = (Translator)Compilation.getCurrent();
    return (translator.lexical.lookup(paramSyntaxForm1.getDatum(), -1) == translator.lexical.lookup(paramSyntaxForm2.getDatum(), -1));
  }
  
  public static Object fromDatum(Object paramObject, SyntaxForm paramSyntaxForm) {
    return makeForm(paramObject, paramSyntaxForm.getScope());
  }
  
  public static Object fromDatumIfNeeded(Object paramObject, SyntaxForm paramSyntaxForm) {
    return (paramObject == paramSyntaxForm.getDatum()) ? paramSyntaxForm : ((paramObject instanceof SyntaxForm) ? paramObject : fromDatum(paramObject, paramSyntaxForm));
  }
  
  public static boolean isIdentifier(SyntaxForm paramSyntaxForm) {
    return paramSyntaxForm.getDatum() instanceof gnu.mapping.Symbol;
  }
  
  public static Object makeForm(Object paramObject, TemplateScope paramTemplateScope) {
    if (paramObject instanceof Pair)
      return new PairSyntaxForm((Pair)paramObject, paramTemplateScope); 
    Object object = paramObject;
    return (paramObject != LList.Empty) ? new SimpleSyntaxForm(paramObject, paramTemplateScope) : object;
  }
  
  public static Object makeWithTemplate(Object paramObject1, Object paramObject2) {
    if (paramObject2 instanceof SyntaxForm)
      return paramObject2; 
    Object object = paramObject2;
    if (paramObject1 instanceof SyntaxForm) {
      paramObject1 = paramObject1;
      return (paramObject2 == paramObject1.getDatum()) ? paramObject1 : fromDatum(paramObject2, (SyntaxForm)paramObject1);
    } 
    return object;
  }
  
  public static Expression rewrite(Object paramObject) {
    return ((Translator)Compilation.getCurrent()).rewrite(paramObject);
  }
  
  public static Expression rewriteBody(Object paramObject) {
    return ((Translator)Compilation.getCurrent()).rewrite_body(paramObject);
  }
  
  public static String toString(SyntaxForm paramSyntaxForm, String paramString) {
    StringBuilder stringBuilder = new StringBuilder("#<syntax");
    if (paramString != null) {
      stringBuilder.append('#');
      stringBuilder.append(paramString);
    } 
    stringBuilder.append(' ');
    stringBuilder.append(paramSyntaxForm.getDatum());
    TemplateScope templateScope = paramSyntaxForm.getScope();
    if (templateScope == null) {
      stringBuilder.append(" in null");
      stringBuilder.append(">");
      return stringBuilder.toString();
    } 
    stringBuilder.append(" in #");
    stringBuilder.append(templateScope.id);
    stringBuilder.append(">");
    return stringBuilder.toString();
  }
  
  static class PairSyntaxForm extends ImmutablePair implements SyntaxForm {
    private Pair datum;
    
    private TemplateScope scope;
    
    public PairSyntaxForm(Pair param1Pair, TemplateScope param1TemplateScope) {
      this.datum = param1Pair;
      this.scope = param1TemplateScope;
    }
    
    public Object getCar() {
      if (this.car == null)
        this.car = SyntaxForms.makeForm(this.datum.getCar(), this.scope); 
      return this.car;
    }
    
    public Object getCdr() {
      if (this.cdr == null)
        this.cdr = SyntaxForms.makeForm(this.datum.getCdr(), this.scope); 
      return this.cdr;
    }
    
    public Object getDatum() {
      return this.datum;
    }
    
    public TemplateScope getScope() {
      return this.scope;
    }
    
    public String toString() {
      return SyntaxForms.toString(this, null);
    }
  }
  
  static class SimpleSyntaxForm implements SyntaxForm {
    static int counter;
    
    private Object datum;
    
    int id;
    
    private TemplateScope scope;
    
    SimpleSyntaxForm(Object param1Object, TemplateScope param1TemplateScope) {
      int i = counter + 1;
      counter = i;
      this.id = i;
      this.datum = param1Object;
      this.scope = param1TemplateScope;
    }
    
    public Object getDatum() {
      return this.datum;
    }
    
    public TemplateScope getScope() {
      return this.scope;
    }
    
    public String toString() {
      return SyntaxForms.toString(this, Integer.toString(this.id));
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/SyntaxForms.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
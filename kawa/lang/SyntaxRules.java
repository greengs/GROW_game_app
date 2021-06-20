package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure1;
import gnu.text.Printable;
import gnu.text.ReportFormat;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class SyntaxRules extends Procedure1 implements Printable, Externalizable {
  Object[] literal_identifiers;
  
  int maxVars = 0;
  
  SyntaxRule[] rules;
  
  public SyntaxRules() {}
  
  public SyntaxRules(Object[] paramArrayOfObject, Object paramObject, Translator paramTranslator) {
    this.literal_identifiers = paramArrayOfObject;
    int j = Translator.listLength(paramObject);
    int i = j;
    if (j < 0) {
      i = 0;
      paramTranslator.syntaxError("missing or invalid syntax-rules");
    } 
    this.rules = new SyntaxRule[i];
    SyntaxForm syntaxForm = null;
    j = 0;
    while (j < i) {
      while (paramObject instanceof SyntaxForm) {
        syntaxForm = (SyntaxForm)paramObject;
        paramObject = syntaxForm.getDatum();
      } 
      Pair pair = (Pair)paramObject;
      paramObject = syntaxForm;
      Object object2;
      for (object2 = pair.getCar(); object2 instanceof SyntaxForm; object2 = paramObject.getDatum())
        paramObject = object2; 
      if (!(object2 instanceof Pair)) {
        paramTranslator.syntaxError("missing pattern in " + j + "'th syntax rule");
        return;
      } 
      Object object1 = paramObject;
      object2 = object2;
      Object object3 = object2.getCar();
      String str = paramTranslator.getFileName();
      int k = paramTranslator.getLineNumber();
      int m = paramTranslator.getColumnNumber();
      try {
        paramTranslator.setLine(object2);
        for (object2 = object2.getCdr(); object2 instanceof SyntaxForm; object2 = paramObject.getDatum())
          paramObject = object2; 
        if (!(object2 instanceof Pair)) {
          paramTranslator.syntaxError("missing template in " + j + "'th syntax rule");
          return;
        } 
        object2 = object2;
        if (object2.getCdr() != LList.Empty) {
          paramTranslator.syntaxError("junk after " + j + "'th syntax rule");
          return;
        } 
        Object object = object2.getCar();
        paramTranslator.push((ScopeExp)PatternScope.push(paramTranslator));
        for (object2 = object3; object2 instanceof SyntaxForm; object2 = object1.getDatum())
          object1 = object2; 
        object3 = new StringBuffer();
        if (object2 instanceof Pair) {
          paramArrayOfObject[0] = ((Pair)object2).getCar();
          object2 = object2;
          object3.append('\f');
          object3.append('\030');
          object1 = new SyntaxPattern((StringBuffer)object3, object2.getCdr(), (SyntaxForm)object1, paramArrayOfObject, paramTranslator);
          this.rules[j] = new SyntaxRule((SyntaxPattern)object1, object, (SyntaxForm)paramObject, paramTranslator);
          PatternScope.pop(paramTranslator);
          paramTranslator.pop();
          paramTranslator.setLine(str, k, m);
          j++;
          paramObject = pair.getCdr();
          continue;
        } 
        paramTranslator.syntaxError("pattern does not start with name");
        return;
      } finally {
        paramTranslator.setLine(str, k, m);
      } 
    } 
    i = this.rules.length;
    while (true) {
      j = i - 1;
      if (j >= 0) {
        int k = (this.rules[j]).patternNesting.length();
        i = j;
        if (k > this.maxVars) {
          this.maxVars = k;
          i = j;
        } 
        continue;
      } 
      return;
    } 
  }
  
  public SyntaxRules(Object[] paramArrayOfObject, SyntaxRule[] paramArrayOfSyntaxRule, int paramInt) {
    this.literal_identifiers = paramArrayOfObject;
    this.rules = paramArrayOfSyntaxRule;
    this.maxVars = paramInt;
  }
  
  public Object apply1(Object paramObject) {
    if (paramObject instanceof SyntaxForm) {
      null = (SyntaxForm)paramObject;
      paramObject = Compilation.getCurrent();
      ScopeExp scopeExp = paramObject.currentScope();
      paramObject.setCurrentScope((ScopeExp)null.getScope());
      try {
        return expand(null, (Translator)paramObject);
      } finally {
        paramObject.setCurrentScope(scopeExp);
      } 
    } 
    return expand(paramObject, (Translator)Compilation.getCurrent());
  }
  
  public Object expand(Object paramObject, Translator paramTranslator) {
    Object[] arrayOfObject = new Object[this.maxVars];
    Macro macro = (Macro)paramTranslator.getCurrentSyntax();
    for (int i = 0; i < this.rules.length; i++) {
      SyntaxRule syntaxRule = this.rules[i];
      if (syntaxRule == null)
        return new ErrorExp("error defining " + macro); 
      if (syntaxRule.pattern.match(paramObject, arrayOfObject, 0))
        return syntaxRule.execute(arrayOfObject, paramTranslator, TemplateScope.make(paramTranslator)); 
    } 
    return paramTranslator.syntaxError("no matching syntax-rule for " + this.literal_identifiers[0]);
  }
  
  public void print(Consumer paramConsumer) {
    paramConsumer.write("#<macro ");
    ReportFormat.print(this.literal_identifiers[0], paramConsumer);
    paramConsumer.write(62);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.literal_identifiers = (Object[])paramObjectInput.readObject();
    this.rules = (SyntaxRule[])paramObjectInput.readObject();
    this.maxVars = paramObjectInput.readInt();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.literal_identifiers);
    paramObjectOutput.writeObject(this.rules);
    paramObjectOutput.writeInt(this.maxVars);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/SyntaxRules.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
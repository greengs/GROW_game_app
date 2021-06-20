package kawa.lang;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class SyntaxRule extends SyntaxTemplate implements Externalizable {
  SyntaxPattern pattern;
  
  public SyntaxRule() {}
  
  public SyntaxRule(SyntaxPattern paramSyntaxPattern, Object paramObject, SyntaxForm paramSyntaxForm, Translator paramTranslator) {
    super(paramObject, paramSyntaxForm, paramTranslator);
    this.pattern = paramSyntaxPattern;
  }
  
  public SyntaxRule(SyntaxPattern paramSyntaxPattern, String paramString1, String paramString2, Object[] paramArrayOfObject, int paramInt) {
    super(paramString1, paramString2, paramArrayOfObject, paramInt);
    this.pattern = paramSyntaxPattern;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.pattern = (SyntaxPattern)paramObjectInput.readObject();
    super.readExternal(paramObjectInput);
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.pattern);
    super.writeExternal(paramObjectOutput);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/SyntaxRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
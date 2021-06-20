package kawa.lang;

import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import gnu.mapping.Environment;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongArguments;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;

public class AutoloadSyntax extends Syntax implements Externalizable {
  String className;
  
  Environment env;
  
  Syntax loaded;
  
  public AutoloadSyntax() {}
  
  public AutoloadSyntax(String paramString1, String paramString2) {
    super(paramString1);
    this.className = paramString2;
  }
  
  public AutoloadSyntax(String paramString1, String paramString2, Environment paramEnvironment) {
    super(paramString1);
    this.className = paramString2;
    this.env = paramEnvironment;
  }
  
  private void throw_error(String paramString) {
    StringBuilder stringBuilder = (new StringBuilder()).append(paramString).append(this.className).append(" while autoloading ");
    if (getName() == null) {
      paramString = "";
      throw new GenericError(stringBuilder.append(paramString).toString());
    } 
    paramString = getName().toString();
    throw new GenericError(stringBuilder.append(paramString).toString());
  }
  
  void load() {
    String str = getName();
    try {
      Object object = Class.forName(this.className).newInstance();
      if (object instanceof Syntax) {
        this.loaded = (Syntax)object;
        if (str != null && this.loaded.getName() == null) {
          this.loaded.setName(str);
          return;
        } 
      } else {
        throw_error("failed to autoload valid syntax object ");
        return;
      } 
    } catch (ClassNotFoundException classNotFoundException) {
      throw_error("failed to find class ");
      return;
    } catch (InstantiationException instantiationException) {
      throw_error("failed to instantiate class ");
      return;
    } catch (IllegalAccessException illegalAccessException) {
      throw_error("illegal access in class ");
      return;
    } catch (UnboundLocationException unboundLocationException) {
      throw_error("missing symbol '" + unboundLocationException.getMessage() + "' ");
      return;
    } catch (WrongArguments wrongArguments) {
      throw_error("type error");
    } 
  }
  
  public void print(PrintWriter paramPrintWriter) {
    paramPrintWriter.print(toString());
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    setName((String)paramObjectInput.readObject());
    this.className = (String)paramObjectInput.readObject();
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    // Byte code:
    //   0: aload_0
    //   1: getfield loaded : Lkawa/lang/Syntax;
    //   4: ifnonnull -> 11
    //   7: aload_0
    //   8: invokevirtual load : ()V
    //   11: aload_2
    //   12: getfield currentSyntax : Lkawa/lang/Syntax;
    //   15: astore_3
    //   16: aload_2
    //   17: aload_0
    //   18: getfield loaded : Lkawa/lang/Syntax;
    //   21: putfield currentSyntax : Lkawa/lang/Syntax;
    //   24: aload_0
    //   25: getfield loaded : Lkawa/lang/Syntax;
    //   28: aload_1
    //   29: aload_2
    //   30: invokevirtual rewriteForm : (Lgnu/lists/Pair;Lkawa/lang/Translator;)Lgnu/expr/Expression;
    //   33: astore_1
    //   34: aload_2
    //   35: aload_3
    //   36: putfield currentSyntax : Lkawa/lang/Syntax;
    //   39: aload_1
    //   40: areturn
    //   41: astore_1
    //   42: aload_2
    //   43: aload_1
    //   44: invokevirtual getMessage : ()Ljava/lang/String;
    //   47: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   50: areturn
    //   51: astore_1
    //   52: aload_2
    //   53: aload_1
    //   54: invokevirtual getMessage : ()Ljava/lang/String;
    //   57: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   60: areturn
    //   61: astore_1
    //   62: aload_2
    //   63: aload_3
    //   64: putfield currentSyntax : Lkawa/lang/Syntax;
    //   67: aload_1
    //   68: athrow
    // Exception table:
    //   from	to	target	type
    //   7	11	41	kawa/lang/GenericError
    //   7	11	51	gnu/mapping/WrongType
    //   24	34	61	finally
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator) {
    if (this.loaded == null)
      try {
        load();
        this.loaded.scanForm(paramPair, paramScopeExp, paramTranslator);
        return;
      } catch (RuntimeException runtimeException) {
        paramTranslator.syntaxError(runtimeException.getMessage());
        return;
      }  
    this.loaded.scanForm((Pair)runtimeException, paramScopeExp, paramTranslator);
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(100);
    stringBuffer.append("#<syntax ");
    if (getName() != null) {
      stringBuffer.append(getName());
      stringBuffer.append(' ');
    } 
    if (this.loaded != null) {
      stringBuffer.append("autoloaded>");
      return stringBuffer.toString();
    } 
    stringBuffer.append("autoload ");
    stringBuffer.append(this.className);
    stringBuffer.append(">");
    return stringBuffer.toString();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(getName());
    paramObjectOutput.writeObject(this.className);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/AutoloadSyntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.expr;

import gnu.lists.Consumer;
import gnu.lists.Sequence;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Special implements Printable, Externalizable {
  public static final Special abstractSpecial;
  
  public static final Special dfault;
  
  public static final Object eof;
  
  public static final Special key;
  
  public static final Special optional;
  
  public static final Special rest;
  
  public static final Special undefined = new Special("undefined");
  
  private String name;
  
  static {
    optional = new Special("optional");
    rest = new Special("rest");
    key = new Special("key");
    dfault = new Special("default");
    abstractSpecial = new Special("abstract");
    eof = Sequence.eofValue;
  }
  
  public Special() {}
  
  private Special(String paramString) {
    this.name = new String(paramString);
  }
  
  public static Special make(String paramString) {
    return (paramString == "optional") ? optional : ((paramString == "rest") ? rest : ((paramString == "key") ? key : ((paramString == "default") ? dfault : new Special(paramString))));
  }
  
  public int hashCode() {
    return this.name.hashCode();
  }
  
  public void print(Consumer paramConsumer) {
    paramConsumer.write("#!");
    paramConsumer.write(this.name);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.name = paramObjectInput.readUTF();
  }
  
  public Object readResolve() throws ObjectStreamException {
    return make(this.name);
  }
  
  public final String toString() {
    return "#!" + this.name;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeUTF(this.name);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/Special.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
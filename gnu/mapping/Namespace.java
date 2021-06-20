package gnu.mapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Hashtable;

public class Namespace implements Externalizable, HasNamedParts {
  public static final Namespace EmptyNamespace;
  
  protected static final Hashtable nsTable = new Hashtable<Object, Object>(50);
  
  int log2Size = 4;
  
  private int mask;
  
  String name;
  
  int num_bindings;
  
  protected String prefix = "";
  
  protected SymbolRef[] table;
  
  static {
    EmptyNamespace = valueOf("");
  }
  
  protected Namespace() {
    this(64);
  }
  
  protected Namespace(int paramInt) {
    while (paramInt > 1 << this.log2Size)
      this.log2Size++; 
    paramInt = 1 << this.log2Size;
    this.table = new SymbolRef[paramInt];
    this.mask = paramInt - 1;
  }
  
  public static Namespace create() {
    return new Namespace(64);
  }
  
  public static Namespace create(int paramInt) {
    return new Namespace(paramInt);
  }
  
  public static Namespace getDefault() {
    return EmptyNamespace;
  }
  
  public static Symbol getDefaultSymbol(String paramString) {
    return EmptyNamespace.getSymbol(paramString);
  }
  
  public static Namespace makeUnknownNamespace(String paramString) {
    if (paramString == null || paramString == "") {
      String str1 = "";
      return valueOf(str1, paramString);
    } 
    String str = "http://kawa.gnu.org/unknown-namespace/" + paramString;
    return valueOf(str, paramString);
  }
  
  public static Namespace valueOf() {
    return EmptyNamespace;
  }
  
  public static Namespace valueOf(String paramString) {
    null = paramString;
    if (paramString == null)
      null = ""; 
    synchronized (nsTable) {
      Namespace namespace = (Namespace)nsTable.get(null);
      if (namespace != null)
        return namespace; 
      namespace = new Namespace();
      namespace.setName(null.intern());
      nsTable.put(null, namespace);
      return namespace;
    } 
  }
  
  public static Namespace valueOf(String paramString, SimpleSymbol paramSimpleSymbol) {
    if (paramSimpleSymbol == null) {
      paramSimpleSymbol = null;
      return valueOf(paramString, (String)paramSimpleSymbol);
    } 
    String str = paramSimpleSymbol.getName();
    return valueOf(paramString, str);
  }
  
  public static Namespace valueOf(String paramString1, String paramString2) {
    if (paramString2 == null || paramString2.length() == 0)
      return valueOf(paramString1); 
    String str = paramString2 + " -> " + paramString1;
    synchronized (nsTable) {
      Object object = nsTable.get(str);
      if (object instanceof Namespace)
        return (Namespace)object; 
    } 
    Namespace namespace = new Namespace();
    namespace.setName(paramString1.intern());
    namespace.prefix = paramString2.intern();
    nsTable.put(str, namespace);
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_2} */
    return namespace;
  }
  
  public Symbol add(Symbol paramSymbol, int paramInt) {
    paramInt &= this.mask;
    SymbolRef symbolRef = new SymbolRef(paramSymbol, this);
    paramSymbol.namespace = this;
    symbolRef.next = this.table[paramInt];
    this.table[paramInt] = symbolRef;
    this.num_bindings++;
    if (this.num_bindings >= this.table.length)
      rehash(); 
    return paramSymbol;
  }
  
  public Object get(String paramString) {
    return Environment.getCurrent().get(getSymbol(paramString));
  }
  
  public final String getName() {
    return this.name;
  }
  
  public final String getPrefix() {
    return this.prefix;
  }
  
  public Symbol getSymbol(String paramString) {
    return lookup(paramString, paramString.hashCode(), true);
  }
  
  public boolean isConstant(String paramString) {
    return false;
  }
  
  public Symbol lookup(String paramString) {
    return lookup(paramString, paramString.hashCode(), false);
  }
  
  public Symbol lookup(String paramString, int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: iload_2
    //   5: invokevirtual lookupInternal : (Ljava/lang/String;I)Lgnu/mapping/Symbol;
    //   8: astore #4
    //   10: aload #4
    //   12: ifnull -> 20
    //   15: aload_0
    //   16: monitorexit
    //   17: aload #4
    //   19: areturn
    //   20: iload_3
    //   21: ifeq -> 69
    //   24: aload_0
    //   25: getstatic gnu/mapping/Namespace.EmptyNamespace : Lgnu/mapping/Namespace;
    //   28: if_acmpne -> 56
    //   31: new gnu/mapping/SimpleSymbol
    //   34: dup
    //   35: aload_1
    //   36: invokespecial <init> : (Ljava/lang/String;)V
    //   39: astore_1
    //   40: aload_0
    //   41: aload_1
    //   42: iload_2
    //   43: invokevirtual add : (Lgnu/mapping/Symbol;I)Lgnu/mapping/Symbol;
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: areturn
    //   51: astore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_1
    //   55: athrow
    //   56: new gnu/mapping/Symbol
    //   59: dup
    //   60: aload_0
    //   61: aload_1
    //   62: invokespecial <init> : (Lgnu/mapping/Namespace;Ljava/lang/String;)V
    //   65: astore_1
    //   66: goto -> 40
    //   69: aload_0
    //   70: monitorexit
    //   71: aconst_null
    //   72: areturn
    // Exception table:
    //   from	to	target	type
    //   2	10	51	finally
    //   15	17	51	finally
    //   24	40	51	finally
    //   40	49	51	finally
    //   52	54	51	finally
    //   56	66	51	finally
    //   69	71	51	finally
  }
  
  protected final Symbol lookupInternal(String paramString, int paramInt) {
    paramInt &= this.mask;
    SymbolRef symbolRef2 = null;
    for (SymbolRef symbolRef1 = this.table[paramInt]; symbolRef1 != null; symbolRef1 = symbolRef) {
      SymbolRef symbolRef = symbolRef1.next;
      Symbol symbol = symbolRef1.getSymbol();
      if (symbol == null) {
        if (symbolRef2 == null) {
          this.table[paramInt] = symbolRef;
        } else {
          symbolRef2.next = symbolRef;
        } 
        this.num_bindings--;
      } else {
        if (symbol.getLocalPart().equals(paramString))
          return symbol; 
        symbolRef2 = symbolRef1;
      } 
    } 
    return null;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.name = ((String)paramObjectInput.readObject()).intern();
    this.prefix = (String)paramObjectInput.readObject();
  }
  
  public Object readResolve() throws ObjectStreamException {
    String str = getName();
    if (str != null) {
      if (this.prefix != null && this.prefix.length() != 0)
        str = this.prefix + " -> " + str; 
      Namespace namespace = (Namespace)nsTable.get(str);
      if (namespace != null)
        return namespace; 
      nsTable.put(str, this);
    } 
    return this;
  }
  
  protected void rehash() {
    int j = this.table.length;
    int i = j * 2;
    int m = i - 1;
    int k = 0;
    SymbolRef[] arrayOfSymbolRef1 = this.table;
    SymbolRef[] arrayOfSymbolRef2 = new SymbolRef[i];
    label16: while (true) {
      int n = j - 1;
      if (n >= 0) {
        SymbolRef symbolRef = arrayOfSymbolRef1[n];
        i = k;
        while (true) {
          k = i;
          j = n;
          if (symbolRef != null) {
            SymbolRef symbolRef1 = symbolRef.next;
            Symbol symbol = symbolRef.getSymbol();
            j = i;
            if (symbol != null) {
              k = symbol.getName().hashCode() & m;
              j = i + 1;
              symbolRef.next = arrayOfSymbolRef2[k];
              arrayOfSymbolRef2[k] = symbolRef;
            } 
            symbolRef = symbolRef1;
            i = j;
            continue;
          } 
          continue label16;
        } 
        break;
      } 
      this.table = arrayOfSymbolRef2;
      this.log2Size++;
      this.mask = m;
      this.num_bindings = k;
      return;
    } 
  }
  
  public boolean remove(Symbol paramSymbol) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual getLocalPart : ()Ljava/lang/String;
    //   6: invokevirtual hashCode : ()I
    //   9: aload_0
    //   10: getfield mask : I
    //   13: iand
    //   14: istore_2
    //   15: aconst_null
    //   16: astore #4
    //   18: aload_0
    //   19: getfield table : [Lgnu/mapping/SymbolRef;
    //   22: iload_2
    //   23: aaload
    //   24: astore_3
    //   25: aload_3
    //   26: ifnull -> 108
    //   29: aload_3
    //   30: getfield next : Lgnu/mapping/SymbolRef;
    //   33: astore #5
    //   35: aload_3
    //   36: invokevirtual getSymbol : ()Lgnu/mapping/Symbol;
    //   39: astore #6
    //   41: aload #6
    //   43: ifnull -> 52
    //   46: aload #6
    //   48: aload_1
    //   49: if_acmpne -> 99
    //   52: aload #4
    //   54: ifnonnull -> 84
    //   57: aload_0
    //   58: getfield table : [Lgnu/mapping/SymbolRef;
    //   61: iload_2
    //   62: aload #5
    //   64: aastore
    //   65: aload_0
    //   66: aload_0
    //   67: getfield num_bindings : I
    //   70: iconst_1
    //   71: isub
    //   72: putfield num_bindings : I
    //   75: aload #6
    //   77: ifnull -> 102
    //   80: aload_0
    //   81: monitorexit
    //   82: iconst_1
    //   83: ireturn
    //   84: aload #4
    //   86: aload #5
    //   88: putfield next : Lgnu/mapping/SymbolRef;
    //   91: goto -> 65
    //   94: astore_1
    //   95: aload_0
    //   96: monitorexit
    //   97: aload_1
    //   98: athrow
    //   99: aload_3
    //   100: astore #4
    //   102: aload #5
    //   104: astore_3
    //   105: goto -> 25
    //   108: aload_0
    //   109: monitorexit
    //   110: iconst_0
    //   111: ireturn
    // Exception table:
    //   from	to	target	type
    //   2	15	94	finally
    //   18	25	94	finally
    //   29	41	94	finally
    //   57	65	94	finally
    //   65	75	94	finally
    //   80	82	94	finally
    //   84	91	94	finally
    //   95	97	94	finally
    //   108	110	94	finally
  }
  
  public final void setName(String paramString) {
    this.name = paramString;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("#,(namespace \"");
    stringBuilder.append(this.name);
    stringBuilder.append('"');
    if (this.prefix != null && this.prefix != "") {
      stringBuilder.append(' ');
      stringBuilder.append(this.prefix);
    } 
    stringBuilder.append(')');
    return stringBuilder.toString();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(getName());
    paramObjectOutput.writeObject(this.prefix);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Namespace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
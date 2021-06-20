package kawa.lang;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.mapping.Procedure;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;

public class AutoloadProcedure extends Procedure implements Externalizable {
  static final Class classModuleBody = ModuleBody.class;
  
  String className;
  
  Language language;
  
  Procedure loaded;
  
  public AutoloadProcedure() {}
  
  public AutoloadProcedure(String paramString1, String paramString2) {
    super(paramString1);
    this.className = paramString2;
  }
  
  public AutoloadProcedure(String paramString1, String paramString2, Language paramLanguage) {
    super(paramString1);
    this.className = paramString2;
    this.language = paramLanguage;
  }
  
  private void throw_error(String paramString) {
    this.loaded = null;
    String str = getName();
    StringBuilder stringBuilder = (new StringBuilder()).append(paramString).append(this.className).append(" while autoloading ");
    if (str == null) {
      paramString = "";
      throw new RuntimeException(stringBuilder.append(paramString).toString());
    } 
    paramString = str.toString();
    throw new RuntimeException(stringBuilder.append(paramString).toString());
  }
  
  public Object apply0() throws Throwable {
    return getLoaded().apply0();
  }
  
  public Object apply1(Object paramObject) throws Throwable {
    return getLoaded().apply1(paramObject);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    return getLoaded().apply2(paramObject1, paramObject2);
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3) throws Throwable {
    return getLoaded().apply3(paramObject1, paramObject2, paramObject3);
  }
  
  public Object apply4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) throws Throwable {
    return getLoaded().apply4(paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    if (this.loaded == null)
      load(); 
    if (this.loaded instanceof AutoloadProcedure)
      throw new InternalError("circularity in autoload of " + getName()); 
    return this.loaded.applyN(paramArrayOfObject);
  }
  
  public Procedure getLoaded() {
    if (this.loaded == null)
      load(); 
    return this.loaded;
  }
  
  public Object getProperty(Object paramObject1, Object paramObject2) {
    Object object = super.getProperty(paramObject1, null);
    return (object != null) ? object : getLoaded().getProperty(paramObject1, paramObject2);
  }
  
  public Procedure getSetter() {
    if (this.loaded == null)
      load(); 
    return (this.loaded instanceof gnu.mapping.HasSetter) ? this.loaded.getSetter() : super.getSetter();
  }
  
  void load() {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   6: astore #4
    //   8: aload_0
    //   9: getfield language : Lgnu/expr/Language;
    //   12: astore_2
    //   13: aload_2
    //   14: astore_1
    //   15: aload_2
    //   16: ifnonnull -> 23
    //   19: invokestatic getDefaultLanguage : ()Lgnu/expr/Language;
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual getLangEnvironment : ()Lgnu/mapping/Environment;
    //   27: astore #5
    //   29: aload #4
    //   31: instanceof gnu/mapping/Symbol
    //   34: ifeq -> 185
    //   37: aload #4
    //   39: checkcast gnu/mapping/Symbol
    //   42: astore_2
    //   43: aload_0
    //   44: getfield className : Ljava/lang/String;
    //   47: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   50: astore #6
    //   52: getstatic kawa/lang/AutoloadProcedure.classModuleBody : Ljava/lang/Class;
    //   55: aload #6
    //   57: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   60: ifeq -> 209
    //   63: invokestatic getContext : ()Lgnu/expr/ModuleContext;
    //   66: aload #6
    //   68: invokevirtual searchInstance : (Ljava/lang/Class;)Ljava/lang/Object;
    //   71: astore_3
    //   72: aload_3
    //   73: ifnonnull -> 109
    //   76: aload #6
    //   78: ldc '$instance'
    //   80: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   83: aconst_null
    //   84: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   87: astore_3
    //   88: aload_3
    //   89: aload_1
    //   90: aload #5
    //   92: invokestatic defineAll : (Ljava/lang/Object;Lgnu/expr/Language;Lgnu/mapping/Environment;)V
    //   95: aload_3
    //   96: instanceof gnu/expr/ModuleBody
    //   99: ifeq -> 109
    //   102: aload_3
    //   103: checkcast gnu/expr/ModuleBody
    //   106: invokevirtual run : ()V
    //   109: aload #5
    //   111: aload_2
    //   112: aconst_null
    //   113: invokevirtual getFunction : (Lgnu/mapping/Symbol;Ljava/lang/Object;)Ljava/lang/Object;
    //   116: astore_1
    //   117: aload_1
    //   118: ifnull -> 128
    //   121: aload_1
    //   122: instanceof gnu/mapping/Procedure
    //   125: ifne -> 152
    //   128: aload_0
    //   129: new java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial <init> : ()V
    //   136: ldc 'invalid ModuleBody class - does not define '
    //   138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: aload #4
    //   143: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   146: invokevirtual toString : ()Ljava/lang/String;
    //   149: invokespecial throw_error : (Ljava/lang/String;)V
    //   152: aload_0
    //   153: aload_1
    //   154: checkcast gnu/mapping/Procedure
    //   157: putfield loaded : Lgnu/mapping/Procedure;
    //   160: aload #4
    //   162: ifnull -> 184
    //   165: aload_0
    //   166: getfield loaded : Lgnu/mapping/Procedure;
    //   169: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   172: ifnonnull -> 184
    //   175: aload_0
    //   176: getfield loaded : Lgnu/mapping/Procedure;
    //   179: aload #4
    //   181: invokevirtual setSymbol : (Ljava/lang/Object;)V
    //   184: return
    //   185: aload #5
    //   187: aload #4
    //   189: invokevirtual toString : ()Ljava/lang/String;
    //   192: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   195: astore_2
    //   196: goto -> 43
    //   199: astore_3
    //   200: aload #6
    //   202: invokevirtual newInstance : ()Ljava/lang/Object;
    //   205: astore_3
    //   206: goto -> 88
    //   209: aload_0
    //   210: aload #6
    //   212: invokevirtual newInstance : ()Ljava/lang/Object;
    //   215: checkcast gnu/mapping/Procedure
    //   218: putfield loaded : Lgnu/mapping/Procedure;
    //   221: aload_0
    //   222: getfield loaded : Lgnu/mapping/Procedure;
    //   225: aload_0
    //   226: if_acmpne -> 235
    //   229: aload_0
    //   230: ldc 'circularity detected'
    //   232: invokespecial throw_error : (Ljava/lang/String;)V
    //   235: aload #4
    //   237: ifnull -> 160
    //   240: aload_1
    //   241: invokevirtual hasSeparateFunctionNamespace : ()Z
    //   244: ifeq -> 251
    //   247: getstatic gnu/mapping/EnvironmentKey.FUNCTION : Ljava/lang/Object;
    //   250: astore_3
    //   251: aload #5
    //   253: aload_2
    //   254: aload_3
    //   255: aload_0
    //   256: getfield loaded : Lgnu/mapping/Procedure;
    //   259: invokevirtual put : (Lgnu/mapping/Symbol;Ljava/lang/Object;Ljava/lang/Object;)V
    //   262: goto -> 160
    //   265: astore_1
    //   266: goto -> 160
    //   269: astore_1
    //   270: aload_0
    //   271: ldc 'failed to find class '
    //   273: invokespecial throw_error : (Ljava/lang/String;)V
    //   276: return
    //   277: astore_1
    //   278: aload_0
    //   279: ldc 'failed to instantiate class '
    //   281: invokespecial throw_error : (Ljava/lang/String;)V
    //   284: return
    //   285: astore_1
    //   286: aload_0
    //   287: ldc 'illegal access in class '
    //   289: invokespecial throw_error : (Ljava/lang/String;)V
    //   292: return
    // Exception table:
    //   from	to	target	type
    //   43	72	269	java/lang/ClassNotFoundException
    //   43	72	277	java/lang/InstantiationException
    //   43	72	285	java/lang/IllegalAccessException
    //   76	88	199	java/lang/NoSuchFieldException
    //   76	88	269	java/lang/ClassNotFoundException
    //   76	88	277	java/lang/InstantiationException
    //   76	88	285	java/lang/IllegalAccessException
    //   88	109	269	java/lang/ClassNotFoundException
    //   88	109	277	java/lang/InstantiationException
    //   88	109	285	java/lang/IllegalAccessException
    //   109	117	269	java/lang/ClassNotFoundException
    //   109	117	277	java/lang/InstantiationException
    //   109	117	285	java/lang/IllegalAccessException
    //   121	128	269	java/lang/ClassNotFoundException
    //   121	128	277	java/lang/InstantiationException
    //   121	128	285	java/lang/IllegalAccessException
    //   128	152	269	java/lang/ClassNotFoundException
    //   128	152	277	java/lang/InstantiationException
    //   128	152	285	java/lang/IllegalAccessException
    //   152	160	269	java/lang/ClassNotFoundException
    //   152	160	277	java/lang/InstantiationException
    //   152	160	285	java/lang/IllegalAccessException
    //   165	184	269	java/lang/ClassNotFoundException
    //   165	184	277	java/lang/InstantiationException
    //   165	184	285	java/lang/IllegalAccessException
    //   200	206	269	java/lang/ClassNotFoundException
    //   200	206	277	java/lang/InstantiationException
    //   200	206	285	java/lang/IllegalAccessException
    //   209	235	269	java/lang/ClassNotFoundException
    //   209	235	277	java/lang/InstantiationException
    //   209	235	285	java/lang/IllegalAccessException
    //   240	251	265	gnu/mapping/UnboundLocationException
    //   240	251	269	java/lang/ClassNotFoundException
    //   240	251	277	java/lang/InstantiationException
    //   240	251	285	java/lang/IllegalAccessException
    //   251	262	265	gnu/mapping/UnboundLocationException
    //   251	262	269	java/lang/ClassNotFoundException
    //   251	262	277	java/lang/InstantiationException
    //   251	262	285	java/lang/IllegalAccessException
  }
  
  public int numArgs() {
    return getLoaded().numArgs();
  }
  
  public void print(PrintWriter paramPrintWriter) {
    paramPrintWriter.print("#<procedure ");
    String str = getName();
    if (str != null)
      paramPrintWriter.print(str); 
    paramPrintWriter.print('>');
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    setName((String)paramObjectInput.readObject());
    this.className = (String)paramObjectInput.readObject();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(getName());
    paramObjectOutput.writeObject(this.className);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/AutoloadProcedure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
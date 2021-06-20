package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;

public class BindingInitializer extends Initializer {
  static final ClassType typeThreadLocation = ClassType.make("gnu.mapping.ThreadLocation");
  
  Declaration decl;
  
  Expression value;
  
  public BindingInitializer(Declaration paramDeclaration, Expression paramExpression) {
    this.decl = paramDeclaration;
    this.value = paramExpression;
    this.field = paramDeclaration.field;
  }
  
  public static void create(Declaration paramDeclaration, Expression paramExpression, Compilation paramCompilation) {
    BindingInitializer bindingInitializer = new BindingInitializer(paramDeclaration, paramExpression);
    if ((paramDeclaration.field != null) ? paramDeclaration.field.getStaticFlag() : paramDeclaration.isStatic()) {
      bindingInitializer.next = paramCompilation.clinitChain;
      paramCompilation.clinitChain = bindingInitializer;
      return;
    } 
    bindingInitializer.next = paramCompilation.mainLambda.initChain;
    paramCompilation.mainLambda.initChain = bindingInitializer;
  }
  
  public static Method makeLocationMethod(Object paramObject) {
    Type[] arrayOfType = new Type[1];
    if (paramObject instanceof gnu.mapping.Symbol) {
      arrayOfType[0] = (Type)Compilation.typeSymbol;
      return Compilation.typeLocation.getDeclaredMethod("make", arrayOfType);
    } 
    arrayOfType[0] = (Type)Type.javalangStringType;
    return Compilation.typeLocation.getDeclaredMethod("make", arrayOfType);
  }
  
  public void emit(Compilation paramCompilation) {
    // Byte code:
    //   0: aload_0
    //   1: getfield decl : Lgnu/expr/Declaration;
    //   4: invokevirtual ignorable : ()Z
    //   7: ifeq -> 29
    //   10: aload_0
    //   11: getfield value : Lgnu/expr/Expression;
    //   14: ifnull -> 28
    //   17: aload_0
    //   18: getfield value : Lgnu/expr/Expression;
    //   21: aload_1
    //   22: getstatic gnu/expr/Target.Ignore : Lgnu/expr/Target;
    //   25: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   28: return
    //   29: aload_1
    //   30: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   33: astore #6
    //   35: aload_0
    //   36: getfield value : Lgnu/expr/Expression;
    //   39: instanceof gnu/expr/QuoteExp
    //   42: ifeq -> 85
    //   45: aload_0
    //   46: getfield value : Lgnu/expr/Expression;
    //   49: checkcast gnu/expr/QuoteExp
    //   52: invokevirtual getValue : ()Ljava/lang/Object;
    //   55: astore_3
    //   56: aload_3
    //   57: ifnull -> 85
    //   60: aload_3
    //   61: instanceof java/lang/String
    //   64: ifne -> 85
    //   67: aload_1
    //   68: getfield litTable : Lgnu/expr/LitTable;
    //   71: aload_3
    //   72: invokevirtual findLiteral : (Ljava/lang/Object;)Lgnu/expr/Literal;
    //   75: getfield field : Lgnu/bytecode/Field;
    //   78: aload_0
    //   79: getfield field : Lgnu/bytecode/Field;
    //   82: if_acmpeq -> 28
    //   85: aload_0
    //   86: getfield decl : Lgnu/expr/Declaration;
    //   89: invokevirtual getLineNumber : ()I
    //   92: istore_2
    //   93: aload_1
    //   94: invokevirtual getMessages : ()Lgnu/text/SourceMessages;
    //   97: astore #7
    //   99: aload #7
    //   101: aload_0
    //   102: getfield decl : Lgnu/expr/Declaration;
    //   105: invokevirtual swapSourceLocator : (Lgnu/text/SourceLocator;)Lgnu/text/SourceLocator;
    //   108: astore #8
    //   110: iload_2
    //   111: ifle -> 127
    //   114: aload #6
    //   116: aload_0
    //   117: getfield decl : Lgnu/expr/Declaration;
    //   120: invokevirtual getFileName : ()Ljava/lang/String;
    //   123: iload_2
    //   124: invokevirtual putLineNumber : (Ljava/lang/String;I)V
    //   127: aload_0
    //   128: getfield field : Lgnu/bytecode/Field;
    //   131: ifnull -> 149
    //   134: aload_0
    //   135: getfield field : Lgnu/bytecode/Field;
    //   138: invokevirtual getStaticFlag : ()Z
    //   141: ifne -> 149
    //   144: aload #6
    //   146: invokevirtual emitPushThis : ()V
    //   149: aload_0
    //   150: getfield value : Lgnu/expr/Expression;
    //   153: ifnonnull -> 405
    //   156: aload_1
    //   157: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   160: invokevirtual hasSeparateFunctionNamespace : ()Z
    //   163: ifeq -> 305
    //   166: aload_0
    //   167: getfield decl : Lgnu/expr/Declaration;
    //   170: invokevirtual isProcedureDecl : ()Z
    //   173: ifeq -> 305
    //   176: getstatic gnu/mapping/EnvironmentKey.FUNCTION : Ljava/lang/Object;
    //   179: astore_3
    //   180: aload_0
    //   181: getfield decl : Lgnu/expr/Declaration;
    //   184: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   187: astore #5
    //   189: aload_0
    //   190: getfield decl : Lgnu/expr/Declaration;
    //   193: ldc2_w 268500992
    //   196: invokevirtual getFlag : (J)Z
    //   199: ifeq -> 321
    //   202: aload #5
    //   204: astore #4
    //   206: aload #5
    //   208: instanceof java/lang/String
    //   211: ifeq -> 227
    //   214: getstatic gnu/mapping/Namespace.EmptyNamespace : Lgnu/mapping/Namespace;
    //   217: aload #5
    //   219: checkcast java/lang/String
    //   222: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   225: astore #4
    //   227: aload_1
    //   228: aload #4
    //   230: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   233: invokevirtual compileConstant : (Ljava/lang/Object;Lgnu/expr/Target;)V
    //   236: aload_3
    //   237: ifnonnull -> 310
    //   240: aload #6
    //   242: invokevirtual emitPushNull : ()V
    //   245: aload #6
    //   247: getstatic gnu/expr/BindingInitializer.typeThreadLocation : Lgnu/bytecode/ClassType;
    //   250: ldc 'getInstance'
    //   252: iconst_2
    //   253: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   256: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   259: aload_0
    //   260: getfield field : Lgnu/bytecode/Field;
    //   263: ifnonnull -> 446
    //   266: aload_0
    //   267: getfield decl : Lgnu/expr/Declaration;
    //   270: invokevirtual getVariable : ()Lgnu/bytecode/Variable;
    //   273: astore_3
    //   274: aload_3
    //   275: astore_1
    //   276: aload_3
    //   277: ifnonnull -> 290
    //   280: aload_0
    //   281: getfield decl : Lgnu/expr/Declaration;
    //   284: aload #6
    //   286: invokevirtual allocateVariable : (Lgnu/bytecode/CodeAttr;)Lgnu/bytecode/Variable;
    //   289: astore_1
    //   290: aload #6
    //   292: aload_1
    //   293: invokevirtual emitStore : (Lgnu/bytecode/Variable;)V
    //   296: aload #7
    //   298: aload #8
    //   300: invokevirtual swapSourceLocator : (Lgnu/text/SourceLocator;)Lgnu/text/SourceLocator;
    //   303: pop
    //   304: return
    //   305: aconst_null
    //   306: astore_3
    //   307: goto -> 180
    //   310: aload_1
    //   311: aload_3
    //   312: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   315: invokevirtual compileConstant : (Ljava/lang/Object;Lgnu/expr/Target;)V
    //   318: goto -> 245
    //   321: aload_0
    //   322: getfield decl : Lgnu/expr/Declaration;
    //   325: invokevirtual isFluid : ()Z
    //   328: ifeq -> 383
    //   331: aload #5
    //   333: instanceof gnu/mapping/Symbol
    //   336: ifeq -> 376
    //   339: getstatic gnu/expr/Compilation.typeSymbol : Lgnu/bytecode/ClassType;
    //   342: astore_3
    //   343: aload_1
    //   344: aload #5
    //   346: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   349: invokevirtual compileConstant : (Ljava/lang/Object;Lgnu/expr/Target;)V
    //   352: aload #6
    //   354: getstatic gnu/expr/BindingInitializer.typeThreadLocation : Lgnu/bytecode/ClassType;
    //   357: ldc 'makeAnonymous'
    //   359: iconst_1
    //   360: anewarray gnu/bytecode/Type
    //   363: dup
    //   364: iconst_0
    //   365: aload_3
    //   366: aastore
    //   367: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   370: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   373: goto -> 259
    //   376: getstatic gnu/bytecode/Type.toStringType : Lgnu/bytecode/ClassType;
    //   379: astore_3
    //   380: goto -> 343
    //   383: aload_1
    //   384: aload #5
    //   386: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   389: invokevirtual compileConstant : (Ljava/lang/Object;Lgnu/expr/Target;)V
    //   392: aload #6
    //   394: aload #5
    //   396: invokestatic makeLocationMethod : (Ljava/lang/Object;)Lgnu/bytecode/Method;
    //   399: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   402: goto -> 259
    //   405: aload_0
    //   406: getfield field : Lgnu/bytecode/Field;
    //   409: ifnonnull -> 435
    //   412: aload_0
    //   413: getfield decl : Lgnu/expr/Declaration;
    //   416: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   419: astore_3
    //   420: aload_0
    //   421: getfield value : Lgnu/expr/Expression;
    //   424: aload_1
    //   425: aload_3
    //   426: invokestatic getInstance : (Lgnu/bytecode/Type;)Lgnu/expr/Target;
    //   429: invokevirtual compileWithPosition : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   432: goto -> 259
    //   435: aload_0
    //   436: getfield field : Lgnu/bytecode/Field;
    //   439: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   442: astore_3
    //   443: goto -> 420
    //   446: aload_0
    //   447: getfield field : Lgnu/bytecode/Field;
    //   450: invokevirtual getStaticFlag : ()Z
    //   453: ifeq -> 468
    //   456: aload #6
    //   458: aload_0
    //   459: getfield field : Lgnu/bytecode/Field;
    //   462: invokevirtual emitPutStatic : (Lgnu/bytecode/Field;)V
    //   465: goto -> 296
    //   468: aload #6
    //   470: aload_0
    //   471: getfield field : Lgnu/bytecode/Field;
    //   474: invokevirtual emitPutField : (Lgnu/bytecode/Field;)V
    //   477: goto -> 296
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/BindingInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.expr.Expression;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class MakeXmlElement extends Syntax {
  public static final MakeXmlElement makeXml = new MakeXmlElement();
  
  static final ClassType typeNamespace = ClassType.make("gnu.mapping.Namespace");
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getCdr : ()Ljava/lang/Object;
    //   4: checkcast gnu/lists/Pair
    //   7: astore #6
    //   9: aload #6
    //   11: invokevirtual getCar : ()Ljava/lang/Object;
    //   14: astore #5
    //   16: aload #6
    //   18: invokevirtual getCdr : ()Ljava/lang/Object;
    //   21: astore #10
    //   23: iconst_0
    //   24: istore_3
    //   25: aload_2
    //   26: getfield xmlElementNamespaces : Lgnu/xml/NamespaceBinding;
    //   29: astore #8
    //   31: aload #8
    //   33: astore #6
    //   35: aload #5
    //   37: instanceof gnu/lists/Pair
    //   40: ifeq -> 371
    //   43: iload_3
    //   44: istore #4
    //   46: iload_3
    //   47: ifne -> 57
    //   50: aload_2
    //   51: invokevirtual letStart : ()V
    //   54: iconst_1
    //   55: istore #4
    //   57: aload #5
    //   59: checkcast gnu/lists/Pair
    //   62: astore #11
    //   64: aload #11
    //   66: invokevirtual getCar : ()Ljava/lang/Object;
    //   69: checkcast gnu/lists/Pair
    //   72: astore #7
    //   74: aload #7
    //   76: invokevirtual getCar : ()Ljava/lang/Object;
    //   79: checkcast java/lang/String
    //   82: astore #5
    //   84: aload #5
    //   86: invokevirtual length : ()I
    //   89: ifne -> 218
    //   92: aconst_null
    //   93: astore #5
    //   95: aload #7
    //   97: invokevirtual getCdr : ()Ljava/lang/Object;
    //   100: astore #7
    //   102: new java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial <init> : ()V
    //   109: astore #9
    //   111: aload #7
    //   113: instanceof gnu/lists/Pair
    //   116: ifeq -> 254
    //   119: aload #7
    //   121: checkcast gnu/lists/Pair
    //   124: astore #12
    //   126: aload #12
    //   128: invokevirtual getCar : ()Ljava/lang/Object;
    //   131: astore #7
    //   133: aload #7
    //   135: iconst_0
    //   136: invokestatic listLength : (Ljava/lang/Object;Z)I
    //   139: iconst_2
    //   140: if_icmpne -> 228
    //   143: aload #7
    //   145: instanceof gnu/lists/Pair
    //   148: ifeq -> 228
    //   151: aload #7
    //   153: checkcast gnu/lists/Pair
    //   156: invokevirtual getCar : ()Ljava/lang/Object;
    //   159: getstatic gnu/kawa/xml/MakeText.makeText : Lgnu/kawa/xml/MakeText;
    //   162: if_acmpne -> 228
    //   165: aload #7
    //   167: checkcast gnu/lists/Pair
    //   170: invokevirtual getCdr : ()Ljava/lang/Object;
    //   173: checkcast gnu/lists/Pair
    //   176: invokevirtual getCar : ()Ljava/lang/Object;
    //   179: astore #7
    //   181: aload #7
    //   183: ifnonnull -> 243
    //   186: aload_2
    //   187: aload #12
    //   189: invokevirtual pushPositionOf : (Ljava/lang/Object;)Ljava/lang/Object;
    //   192: astore #7
    //   194: aload_2
    //   195: bipush #101
    //   197: ldc 'namespace URI must be literal'
    //   199: invokevirtual error : (CLjava/lang/String;)V
    //   202: aload_2
    //   203: aload #7
    //   205: invokevirtual popPositionOf : (Ljava/lang/Object;)V
    //   208: aload #12
    //   210: invokevirtual getCdr : ()Ljava/lang/Object;
    //   213: astore #7
    //   215: goto -> 111
    //   218: aload #5
    //   220: invokevirtual intern : ()Ljava/lang/String;
    //   223: astore #5
    //   225: goto -> 95
    //   228: aload_2
    //   229: aload #12
    //   231: iconst_0
    //   232: invokevirtual rewrite_car : (Lgnu/lists/Pair;Z)Lgnu/expr/Expression;
    //   235: invokevirtual valueIfConstant : ()Ljava/lang/Object;
    //   238: astore #7
    //   240: goto -> 181
    //   243: aload #9
    //   245: aload #7
    //   247: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   250: pop
    //   251: goto -> 208
    //   254: aload #9
    //   256: invokevirtual toString : ()Ljava/lang/String;
    //   259: invokevirtual intern : ()Ljava/lang/String;
    //   262: astore #9
    //   264: aload #9
    //   266: ldc ''
    //   268: if_acmpne -> 352
    //   271: aconst_null
    //   272: astore #7
    //   274: new gnu/xml/NamespaceBinding
    //   277: dup
    //   278: aload #5
    //   280: aload #7
    //   282: aload #6
    //   284: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Lgnu/xml/NamespaceBinding;)V
    //   287: astore #7
    //   289: aload #5
    //   291: ifnonnull -> 359
    //   294: aload #9
    //   296: invokestatic valueOf : (Ljava/lang/String;)Lgnu/mapping/Namespace;
    //   299: astore #6
    //   301: ldc '[default-element-namespace]'
    //   303: astore #5
    //   305: aload_2
    //   306: getstatic gnu/mapping/Namespace.EmptyNamespace : Lgnu/mapping/Namespace;
    //   309: aload #5
    //   311: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   314: getstatic gnu/kawa/lispexpr/MakeXmlElement.typeNamespace : Lgnu/bytecode/ClassType;
    //   317: new gnu/expr/QuoteExp
    //   320: dup
    //   321: aload #6
    //   323: invokespecial <init> : (Ljava/lang/Object;)V
    //   326: invokevirtual letVariable : (Ljava/lang/Object;Lgnu/bytecode/Type;Lgnu/expr/Expression;)Lgnu/expr/Declaration;
    //   329: ldc2_w 2121728
    //   332: invokevirtual setFlag : (J)V
    //   335: aload #11
    //   337: invokevirtual getCdr : ()Ljava/lang/Object;
    //   340: astore #5
    //   342: aload #7
    //   344: astore #6
    //   346: iload #4
    //   348: istore_3
    //   349: goto -> 35
    //   352: aload #9
    //   354: astore #7
    //   356: goto -> 274
    //   359: aload #5
    //   361: aload #9
    //   363: invokestatic getInstance : (Ljava/lang/String;Ljava/lang/String;)Lgnu/kawa/xml/XmlNamespace;
    //   366: astore #6
    //   368: goto -> 305
    //   371: new gnu/kawa/xml/MakeElement
    //   374: dup
    //   375: invokespecial <init> : ()V
    //   378: astore #5
    //   380: aload #5
    //   382: aload #6
    //   384: invokevirtual setNamespaceNodes : (Lgnu/xml/NamespaceBinding;)V
    //   387: aload_2
    //   388: aload #6
    //   390: putfield xmlElementNamespaces : Lgnu/xml/NamespaceBinding;
    //   393: iload_3
    //   394: ifeq -> 401
    //   397: aload_2
    //   398: invokevirtual letEnter : ()V
    //   401: aload_2
    //   402: aload_1
    //   403: aload #5
    //   405: aload #10
    //   407: invokestatic makePair : (Lgnu/lists/Pair;Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   410: invokevirtual rewrite : (Ljava/lang/Object;)Lgnu/expr/Expression;
    //   413: astore #5
    //   415: aload #5
    //   417: astore_1
    //   418: iload_3
    //   419: ifeq -> 429
    //   422: aload_2
    //   423: aload #5
    //   425: invokevirtual letDone : (Lgnu/expr/Expression;)Lgnu/expr/LetExp;
    //   428: astore_1
    //   429: aload_2
    //   430: aload #8
    //   432: putfield xmlElementNamespaces : Lgnu/xml/NamespaceBinding;
    //   435: aload_1
    //   436: areturn
    //   437: astore_1
    //   438: aload_2
    //   439: aload #8
    //   441: putfield xmlElementNamespaces : Lgnu/xml/NamespaceBinding;
    //   444: aload_1
    //   445: athrow
    // Exception table:
    //   from	to	target	type
    //   397	401	437	finally
    //   401	415	437	finally
    //   422	429	437	finally
  }
  
  static {
    makeXml.setName("$make-xml$");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/MakeXmlElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
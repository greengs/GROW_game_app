package gnu.expr;

import gnu.mapping.EnvironmentKey;
import gnu.mapping.KeyPair;
import gnu.mapping.Symbol;
import gnu.text.SourceLocator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class FindCapturedVars extends ExpExpVisitor<Void> {
  int backJumpPossible = 0;
  
  ModuleExp currentModule = null;
  
  Hashtable unknownDecls = null;
  
  static Expression checkInlineable(LambdaExp paramLambdaExp, Set<LambdaExp> paramSet) {
    if (paramLambdaExp.returnContinuation == LambdaExp.unknownContinuation)
      return paramLambdaExp.returnContinuation; 
    if (paramSet.contains(paramLambdaExp))
      return paramLambdaExp.returnContinuation; 
    if (paramLambdaExp.getCanRead() || paramLambdaExp.isClassMethod() || paramLambdaExp.min_args != paramLambdaExp.max_args) {
      paramLambdaExp.returnContinuation = LambdaExp.unknownContinuation;
      return LambdaExp.unknownContinuation;
    } 
    paramSet.add(paramLambdaExp);
    Expression expression1 = paramLambdaExp.returnContinuation;
    Expression expression2 = expression1;
    if (paramLambdaExp.tailCallers != null) {
      Iterator<LambdaExp> iterator = paramLambdaExp.tailCallers.iterator();
      while (true) {
        expression2 = expression1;
        if (iterator.hasNext()) {
          LambdaExp lambdaExp = iterator.next();
          expression2 = checkInlineable(lambdaExp, paramSet);
          if (expression2 == LambdaExp.unknownContinuation) {
            if (expression1 == null || expression1 == lambdaExp.body) {
              expression1 = lambdaExp.body;
              paramLambdaExp.inlineHome = lambdaExp;
              continue;
            } 
            paramLambdaExp.returnContinuation = LambdaExp.unknownContinuation;
            return expression2;
          } 
          if (expression1 == null) {
            expression1 = expression2;
            if (paramLambdaExp.inlineHome == null) {
              if (paramLambdaExp.nestedIn(lambdaExp)) {
                expression1 = lambdaExp;
              } else {
                expression1 = lambdaExp.inlineHome;
              } 
              paramLambdaExp.inlineHome = (LambdaExp)expression1;
              expression1 = expression2;
            } 
            continue;
          } 
          if ((expression2 != null && expression1 != expression2) || paramLambdaExp.getFlag(32)) {
            paramLambdaExp.returnContinuation = LambdaExp.unknownContinuation;
            return LambdaExp.unknownContinuation;
          } 
          continue;
        } 
        break;
      } 
    } 
    return expression2;
  }
  
  public static void findCapturedVars(Expression paramExpression, Compilation paramCompilation) {
    FindCapturedVars findCapturedVars = new FindCapturedVars();
    findCapturedVars.setContext(paramCompilation);
    paramExpression.visit(findCapturedVars, (Object)null);
  }
  
  Declaration allocUnboundDecl(Object paramObject, boolean paramBoolean) {
    Object object1 = paramObject;
    Object object2 = object1;
    boolean bool = paramBoolean;
    if (paramBoolean) {
      object2 = object1;
      bool = paramBoolean;
      if (paramObject instanceof Symbol)
        if (!getCompilation().getLanguage().hasSeparateFunctionNamespace()) {
          bool = false;
          object2 = object1;
        } else {
          object2 = new KeyPair((Symbol)paramObject, EnvironmentKey.FUNCTION);
          bool = paramBoolean;
        }  
    } 
    if (this.unknownDecls == null) {
      this.unknownDecls = new Hashtable<Object, Object>(100);
      object1 = null;
    } else {
      object1 = this.unknownDecls.get(object2);
    } 
    Object object3 = object1;
    if (object1 == null) {
      object3 = this.currentModule.addDeclaration(paramObject);
      object3.setSimple(false);
      object3.setPrivate(true);
      if (bool)
        object3.setProcedureDecl(true); 
      if (this.currentModule.isStatic())
        object3.setFlag(2048L); 
      object3.setCanRead(true);
      object3.setCanWrite(true);
      object3.setFlag(327680L);
      object3.setIndirectBinding(true);
      this.unknownDecls.put(object2, object3);
    } 
    return (Declaration)object3;
  }
  
  public void capture(Declaration paramDeclaration) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getCanRead : ()Z
    //   4: ifne -> 15
    //   7: aload_1
    //   8: invokevirtual getCanCall : ()Z
    //   11: ifne -> 15
    //   14: return
    //   15: aload_1
    //   16: getfield field : Lgnu/bytecode/Field;
    //   19: ifnull -> 32
    //   22: aload_1
    //   23: getfield field : Lgnu/bytecode/Field;
    //   26: invokevirtual getStaticFlag : ()Z
    //   29: ifne -> 14
    //   32: aload_0
    //   33: getfield comp : Lgnu/expr/Compilation;
    //   36: getfield immediate : Z
    //   39: ifeq -> 49
    //   42: aload_1
    //   43: invokevirtual hasConstantValue : ()Z
    //   46: ifne -> 14
    //   49: aload_0
    //   50: invokevirtual getCurrentLambda : ()Lgnu/expr/LambdaExp;
    //   53: astore_2
    //   54: aload_1
    //   55: invokevirtual getContext : ()Lgnu/expr/ScopeExp;
    //   58: astore_3
    //   59: aload_3
    //   60: ifnonnull -> 99
    //   63: new java/lang/Error
    //   66: dup
    //   67: new java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial <init> : ()V
    //   74: ldc 'null context for '
    //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: aload_1
    //   80: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   83: ldc ' curL:'
    //   85: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload_2
    //   89: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   92: invokevirtual toString : ()Ljava/lang/String;
    //   95: invokespecial <init> : (Ljava/lang/String;)V
    //   98: athrow
    //   99: aload_3
    //   100: invokevirtual currentLambda : ()Lgnu/expr/LambdaExp;
    //   103: astore #7
    //   105: aconst_null
    //   106: astore #5
    //   108: aconst_null
    //   109: astore_3
    //   110: aload_2
    //   111: aload #7
    //   113: if_acmpeq -> 184
    //   116: aload_2
    //   117: invokevirtual getInlineOnly : ()Z
    //   120: ifeq -> 184
    //   123: aload_2
    //   124: invokevirtual outerLambda : ()Lgnu/expr/LambdaExp;
    //   127: astore #6
    //   129: aload #5
    //   131: astore #4
    //   133: aload #6
    //   135: aload #5
    //   137: if_acmpeq -> 150
    //   140: aload #6
    //   142: getfield firstChild : Lgnu/expr/LambdaExp;
    //   145: astore_3
    //   146: aload #6
    //   148: astore #4
    //   150: aload_3
    //   151: ifnull -> 161
    //   154: aload_2
    //   155: getfield inlineHome : Lgnu/expr/LambdaExp;
    //   158: ifnonnull -> 167
    //   161: aload_2
    //   162: iconst_0
    //   163: invokevirtual setCanCall : (Z)V
    //   166: return
    //   167: aload_2
    //   168: invokevirtual getCaller : ()Lgnu/expr/LambdaExp;
    //   171: astore_2
    //   172: aload_3
    //   173: getfield nextSibling : Lgnu/expr/LambdaExp;
    //   176: astore_3
    //   177: aload #4
    //   179: astore #5
    //   181: goto -> 110
    //   184: aload_0
    //   185: getfield comp : Lgnu/expr/Compilation;
    //   188: invokevirtual usingCPStyle : ()Z
    //   191: ifeq -> 263
    //   194: aload_2
    //   195: instanceof gnu/expr/ModuleExp
    //   198: ifne -> 14
    //   201: aload_1
    //   202: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   205: astore_3
    //   206: aload_3
    //   207: ifnull -> 217
    //   210: aload_3
    //   211: instanceof gnu/expr/LambdaExp
    //   214: ifne -> 270
    //   217: aconst_null
    //   218: astore_3
    //   219: aload_1
    //   220: ldc2_w 65536
    //   223: invokevirtual getFlag : (J)Z
    //   226: ifeq -> 239
    //   229: aload_2
    //   230: astore #4
    //   232: aload #4
    //   234: aload #7
    //   236: if_acmpne -> 317
    //   239: aload_1
    //   240: getfield base : Lgnu/expr/Declaration;
    //   243: ifnull -> 359
    //   246: aload_1
    //   247: getfield base : Lgnu/expr/Declaration;
    //   250: iconst_1
    //   251: invokevirtual setCanRead : (Z)V
    //   254: aload_0
    //   255: aload_1
    //   256: getfield base : Lgnu/expr/Declaration;
    //   259: invokevirtual capture : (Lgnu/expr/Declaration;)V
    //   262: return
    //   263: aload_2
    //   264: aload #7
    //   266: if_acmpne -> 201
    //   269: return
    //   270: aload_3
    //   271: checkcast gnu/expr/LambdaExp
    //   274: astore #4
    //   276: aload #4
    //   278: invokevirtual getInlineOnly : ()Z
    //   281: ifne -> 14
    //   284: aload #4
    //   286: invokevirtual isHandlingTailCalls : ()Z
    //   289: ifeq -> 297
    //   292: aconst_null
    //   293: astore_3
    //   294: goto -> 219
    //   297: aload #4
    //   299: astore_3
    //   300: aload #4
    //   302: aload_2
    //   303: if_acmpne -> 219
    //   306: aload #4
    //   308: astore_3
    //   309: aload_1
    //   310: invokevirtual getCanRead : ()Z
    //   313: ifne -> 219
    //   316: return
    //   317: aload #4
    //   319: getfield nameDecl : Lgnu/expr/Declaration;
    //   322: ifnull -> 349
    //   325: aload #4
    //   327: getfield nameDecl : Lgnu/expr/Declaration;
    //   330: ldc2_w 2048
    //   333: invokevirtual getFlag : (J)Z
    //   336: ifeq -> 349
    //   339: aload_1
    //   340: ldc2_w 2048
    //   343: invokevirtual setFlag : (J)V
    //   346: goto -> 239
    //   349: aload #4
    //   351: invokevirtual outerLambda : ()Lgnu/expr/LambdaExp;
    //   354: astore #4
    //   356: goto -> 232
    //   359: aload_1
    //   360: invokevirtual getCanRead : ()Z
    //   363: ifne -> 377
    //   366: aload_1
    //   367: invokevirtual getCanCall : ()Z
    //   370: ifne -> 377
    //   373: aload_3
    //   374: ifnonnull -> 14
    //   377: aload_1
    //   378: invokevirtual isStatic : ()Z
    //   381: ifne -> 426
    //   384: aload_1
    //   385: invokevirtual isFluid : ()Z
    //   388: ifne -> 395
    //   391: aload_2
    //   392: invokevirtual setImportsLexVars : ()V
    //   395: aload_2
    //   396: invokevirtual outerLambda : ()Lgnu/expr/LambdaExp;
    //   399: astore #4
    //   401: aload #4
    //   403: aload #7
    //   405: if_acmpeq -> 426
    //   408: aload #4
    //   410: ifnull -> 426
    //   413: aload_1
    //   414: invokevirtual getCanRead : ()Z
    //   417: ifne -> 509
    //   420: aload_3
    //   421: aload #4
    //   423: if_acmpne -> 509
    //   426: aload #7
    //   428: ifnonnull -> 666
    //   431: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   434: new java/lang/StringBuilder
    //   437: dup
    //   438: invokespecial <init> : ()V
    //   441: ldc_w 'null declLambda for '
    //   444: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: aload_1
    //   448: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   451: ldc ' curL:'
    //   453: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   456: aload_2
    //   457: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   460: invokevirtual toString : ()Ljava/lang/String;
    //   463: invokevirtual println : (Ljava/lang/String;)V
    //   466: aload_1
    //   467: getfield context : Lgnu/expr/ScopeExp;
    //   470: astore_2
    //   471: aload_2
    //   472: ifnull -> 666
    //   475: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   478: new java/lang/StringBuilder
    //   481: dup
    //   482: invokespecial <init> : ()V
    //   485: ldc_w '- context:'
    //   488: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: aload_2
    //   492: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   495: invokevirtual toString : ()Ljava/lang/String;
    //   498: invokevirtual println : (Ljava/lang/String;)V
    //   501: aload_2
    //   502: getfield outer : Lgnu/expr/ScopeExp;
    //   505: astore_2
    //   506: goto -> 471
    //   509: aload #4
    //   511: getfield nameDecl : Lgnu/expr/Declaration;
    //   514: astore #5
    //   516: aload #5
    //   518: ifnull -> 578
    //   521: aload #5
    //   523: ldc2_w 2048
    //   526: invokevirtual getFlag : (J)Z
    //   529: ifeq -> 578
    //   532: aload_0
    //   533: getfield comp : Lgnu/expr/Compilation;
    //   536: bipush #101
    //   538: new java/lang/StringBuilder
    //   541: dup
    //   542: invokespecial <init> : ()V
    //   545: ldc_w 'static '
    //   548: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   551: aload #4
    //   553: invokevirtual getName : ()Ljava/lang/String;
    //   556: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   559: ldc_w ' references non-static '
    //   562: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   565: aload_1
    //   566: invokevirtual getName : ()Ljava/lang/String;
    //   569: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   572: invokevirtual toString : ()Ljava/lang/String;
    //   575: invokevirtual error : (CLjava/lang/String;)V
    //   578: aload #4
    //   580: instanceof gnu/expr/ClassExp
    //   583: ifeq -> 651
    //   586: aload #4
    //   588: invokevirtual getName : ()Ljava/lang/String;
    //   591: ifnull -> 651
    //   594: aload #4
    //   596: checkcast gnu/expr/ClassExp
    //   599: invokevirtual isSimple : ()Z
    //   602: ifeq -> 651
    //   605: aload_0
    //   606: getfield comp : Lgnu/expr/Compilation;
    //   609: bipush #119
    //   611: aload #4
    //   613: getfield nameDecl : Lgnu/expr/Declaration;
    //   616: ldc_w 'simple class '
    //   619: new java/lang/StringBuilder
    //   622: dup
    //   623: invokespecial <init> : ()V
    //   626: ldc_w ' requiring lexical link (because of reference to '
    //   629: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   632: aload_1
    //   633: invokevirtual getName : ()Ljava/lang/String;
    //   636: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   639: ldc_w ') - use define-class instead'
    //   642: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   645: invokevirtual toString : ()Ljava/lang/String;
    //   648: invokevirtual error : (CLgnu/expr/Declaration;Ljava/lang/String;Ljava/lang/String;)V
    //   651: aload #4
    //   653: invokevirtual setNeedsStaticLink : ()V
    //   656: aload #4
    //   658: invokevirtual outerLambda : ()Lgnu/expr/LambdaExp;
    //   661: astore #4
    //   663: goto -> 401
    //   666: aload #7
    //   668: aload_1
    //   669: invokevirtual capture : (Lgnu/expr/Declaration;)V
    //   672: return
  }
  
  void capture(Declaration paramDeclaration1, Declaration paramDeclaration2) {
    // Byte code:
    //   0: aload_2
    //   1: astore_3
    //   2: aload_2
    //   3: invokevirtual isAlias : ()Z
    //   6: ifeq -> 70
    //   9: aload_2
    //   10: astore_3
    //   11: aload_2
    //   12: getfield value : Lgnu/expr/Expression;
    //   15: instanceof gnu/expr/ReferenceExp
    //   18: ifeq -> 70
    //   21: aload_2
    //   22: getfield value : Lgnu/expr/Expression;
    //   25: checkcast gnu/expr/ReferenceExp
    //   28: astore #4
    //   30: aload #4
    //   32: getfield binding : Lgnu/expr/Declaration;
    //   35: astore #5
    //   37: aload_2
    //   38: astore_3
    //   39: aload #5
    //   41: ifnull -> 70
    //   44: aload_1
    //   45: ifnull -> 58
    //   48: aload_2
    //   49: astore_3
    //   50: aload #5
    //   52: invokevirtual needsContext : ()Z
    //   55: ifne -> 70
    //   58: aload_0
    //   59: aload #4
    //   61: invokevirtual contextDecl : ()Lgnu/expr/Declaration;
    //   64: aload #5
    //   66: invokevirtual capture : (Lgnu/expr/Declaration;Lgnu/expr/Declaration;)V
    //   69: return
    //   70: aload_3
    //   71: invokevirtual isFluid : ()Z
    //   74: ifeq -> 95
    //   77: aload_3
    //   78: getfield context : Lgnu/expr/ScopeExp;
    //   81: instanceof gnu/expr/FluidLetExp
    //   84: ifeq -> 95
    //   87: aload_3
    //   88: getfield base : Lgnu/expr/Declaration;
    //   91: astore_3
    //   92: goto -> 70
    //   95: aload_1
    //   96: ifnull -> 112
    //   99: aload_3
    //   100: invokevirtual needsContext : ()Z
    //   103: ifeq -> 112
    //   106: aload_0
    //   107: aload_1
    //   108: invokevirtual capture : (Lgnu/expr/Declaration;)V
    //   111: return
    //   112: aload_0
    //   113: aload_3
    //   114: invokevirtual capture : (Lgnu/expr/Declaration;)V
    //   117: return
  }
  
  void maybeWarnNoDeclarationSeen(Object paramObject, Compilation paramCompilation, SourceLocator paramSourceLocator) {
    if (paramCompilation.warnUndefinedVariable())
      paramCompilation.error('w', "no declaration seen for " + paramObject, paramSourceLocator); 
  }
  
  protected Expression visitApplyExp(ApplyExp paramApplyExp, Void paramVoid) {
    int i;
    boolean bool1;
    int j = this.backJumpPossible;
    byte b = 0;
    boolean bool2 = false;
    if (paramApplyExp.func instanceof ReferenceExp && Compilation.defaultCallConvention <= 1) {
      Declaration declaration = Declaration.followAliases(((ReferenceExp)paramApplyExp.func).binding);
      bool1 = bool2;
      i = b;
      if (declaration != null) {
        bool1 = bool2;
        i = b;
        if (declaration.context instanceof ModuleExp) {
          bool1 = bool2;
          i = b;
          if (!declaration.isPublic()) {
            bool1 = bool2;
            i = b;
            if (!declaration.getFlag(4096L)) {
              Expression expression = declaration.getValue();
              bool1 = bool2;
              i = b;
              if (expression instanceof LambdaExp) {
                bool1 = bool2;
                i = b;
                if (!((LambdaExp)expression).getNeedsClosureEnv()) {
                  i = 1;
                  bool1 = bool2;
                } 
              } 
            } 
          } 
        } 
      } 
    } else {
      bool1 = bool2;
      i = b;
      if (paramApplyExp.func instanceof QuoteExp) {
        bool1 = bool2;
        i = b;
        if (paramApplyExp.getArgCount() > 0) {
          Object object = ((QuoteExp)paramApplyExp.func).getValue();
          Expression expression = paramApplyExp.getArg(0);
          bool1 = bool2;
          i = b;
          if (object instanceof PrimProcedure) {
            bool1 = bool2;
            i = b;
            if (expression instanceof ReferenceExp) {
              object = object;
              Declaration declaration = Declaration.followAliases(((ReferenceExp)expression).binding);
              bool1 = bool2;
              i = b;
              if (declaration != null) {
                bool1 = bool2;
                i = b;
                if (declaration.context instanceof ModuleExp) {
                  bool1 = bool2;
                  i = b;
                  if (!declaration.getFlag(4096L)) {
                    object = declaration.getValue();
                    bool1 = bool2;
                    i = b;
                    if (object instanceof ClassExp) {
                      Expression[] arrayOfExpression = paramApplyExp.getArgs();
                      bool1 = bool2;
                      i = b;
                      if (!((LambdaExp)object).getNeedsClosureEnv()) {
                        paramApplyExp.nextCall = declaration.firstCall;
                        declaration.firstCall = paramApplyExp;
                        for (i = 1; i < arrayOfExpression.length; i++)
                          arrayOfExpression[i].visit(this, paramVoid); 
                        bool1 = true;
                        i = 1;
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } 
        } 
      } 
    } 
    if (i == 0)
      paramApplyExp.func = paramApplyExp.func.<Expression, Void>visit(this, paramVoid); 
    if (this.exitValue == null && !bool1)
      paramApplyExp.args = visitExps(paramApplyExp.args, paramVoid); 
    if (this.backJumpPossible > j)
      paramApplyExp.setFlag(8); 
    return paramApplyExp;
  }
  
  protected Expression visitClassExp(ClassExp paramClassExp, Void paramVoid) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokespecial visitClassExp : (Lgnu/expr/ClassExp;Ljava/lang/Object;)Ljava/lang/Object;
    //   6: checkcast gnu/expr/Expression
    //   9: astore_3
    //   10: aload_1
    //   11: getfield explicitInit : Z
    //   14: ifne -> 82
    //   17: aload_1
    //   18: getfield instanceType : Lgnu/bytecode/ClassType;
    //   21: invokevirtual isInterface : ()Z
    //   24: ifne -> 82
    //   27: aload_1
    //   28: getfield instanceType : Lgnu/bytecode/ClassType;
    //   31: aload_1
    //   32: invokestatic getConstructor : (Lgnu/bytecode/ClassType;Lgnu/expr/LambdaExp;)Lgnu/bytecode/Method;
    //   35: pop
    //   36: aload_1
    //   37: invokevirtual isSimple : ()Z
    //   40: ifeq -> 80
    //   43: aload_1
    //   44: invokevirtual getNeedsClosureEnv : ()Z
    //   47: ifeq -> 80
    //   50: aload_1
    //   51: getfield nameDecl : Lgnu/expr/Declaration;
    //   54: ifnull -> 80
    //   57: aload_1
    //   58: getfield nameDecl : Lgnu/expr/Declaration;
    //   61: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   64: getstatic gnu/expr/Compilation.typeClass : Lgnu/bytecode/ClassType;
    //   67: if_acmpne -> 80
    //   70: aload_1
    //   71: getfield nameDecl : Lgnu/expr/Declaration;
    //   74: getstatic gnu/expr/Compilation.typeClassType : Lgnu/bytecode/ClassType;
    //   77: invokevirtual setType : (Lgnu/bytecode/Type;)V
    //   80: aload_3
    //   81: areturn
    //   82: aload_1
    //   83: invokevirtual getNeedsClosureEnv : ()Z
    //   86: ifeq -> 36
    //   89: aload_1
    //   90: getfield firstChild : Lgnu/expr/LambdaExp;
    //   93: astore_2
    //   94: aload_2
    //   95: ifnull -> 36
    //   98: ldc_w '*init*'
    //   101: aload_2
    //   102: invokevirtual getName : ()Ljava/lang/String;
    //   105: invokevirtual equals : (Ljava/lang/Object;)Z
    //   108: ifeq -> 116
    //   111: aload_2
    //   112: iconst_1
    //   113: invokevirtual setNeedsStaticLink : (Z)V
    //   116: aload_2
    //   117: getfield nextSibling : Lgnu/expr/LambdaExp;
    //   120: astore_2
    //   121: goto -> 94
  }
  
  public void visitDefaultArgs(LambdaExp paramLambdaExp, Void paramVoid) {
    if (paramLambdaExp.defaultArgs != null) {
      super.visitDefaultArgs(paramLambdaExp, paramVoid);
      Declaration declaration = paramLambdaExp.firstDecl();
      while (true) {
        if (declaration != null) {
          if (!declaration.isSimple()) {
            paramLambdaExp.setFlag(true, 512);
            return;
          } 
          declaration = declaration.nextDecl();
          continue;
        } 
        return;
      } 
    } 
  }
  
  protected Expression visitFluidLetExp(FluidLetExp paramFluidLetExp, Void paramVoid) {
    for (Declaration declaration = paramFluidLetExp.firstDecl(); declaration != null; declaration = declaration.nextDecl()) {
      if (declaration.base == null) {
        Object object = declaration.getSymbol();
        Declaration declaration1 = allocUnboundDecl(object, false);
        maybeWarnNoDeclarationSeen(object, this.comp, paramFluidLetExp);
        capture(declaration1);
        declaration.base = declaration1;
      } 
    } 
    return super.visitLetExp(paramFluidLetExp, paramVoid);
  }
  
  protected Expression visitLambdaExp(LambdaExp paramLambdaExp, Void paramVoid) {
    if (checkInlineable(paramLambdaExp, new LinkedHashSet<LambdaExp>()) != LambdaExp.unknownContinuation && (!(paramLambdaExp.outer instanceof ModuleExp) || paramLambdaExp.nameDecl == null)) {
      paramLambdaExp.setInlineOnly(true);
      this.backJumpPossible++;
    } 
    return super.visitLambdaExp(paramLambdaExp, paramVoid);
  }
  
  protected Expression visitLetExp(LetExp paramLetExp, Void paramVoid) {
    if (paramLetExp.body instanceof BeginExp) {
      Expression[] arrayOfExpression1 = paramLetExp.inits;
      int k = arrayOfExpression1.length;
      Expression[] arrayOfExpression2 = ((BeginExp)paramLetExp.body).exps;
      int j = 0;
      Declaration declaration = paramLetExp.firstDecl();
      int i = 0;
      while (i < arrayOfExpression2.length && j < k) {
        Expression expression = arrayOfExpression2[i];
        Declaration declaration1 = declaration;
        int m = j;
        if (expression instanceof SetExp) {
          expression = expression;
          declaration1 = declaration;
          m = j;
          if (((SetExp)expression).binding == declaration) {
            declaration1 = declaration;
            m = j;
            if (arrayOfExpression1[j] == QuoteExp.nullExp) {
              declaration1 = declaration;
              m = j;
              if (expression.isDefining()) {
                Expression expression1 = ((SetExp)expression).new_value;
                if ((expression1 instanceof QuoteExp || expression1 instanceof LambdaExp) && declaration.getValue() == expression1) {
                  arrayOfExpression1[j] = expression1;
                  arrayOfExpression2[i] = QuoteExp.voidExp;
                } 
                m = j + 1;
                declaration1 = declaration.nextDecl();
              } 
            } 
          } 
        } 
        i++;
        declaration = declaration1;
        j = m;
      } 
    } 
    return super.visitLetExp(paramLetExp, paramVoid);
  }
  
  protected Expression visitModuleExp(ModuleExp paramModuleExp, Void paramVoid) {
    ModuleExp moduleExp = this.currentModule;
    Hashtable hashtable = this.unknownDecls;
    this.currentModule = paramModuleExp;
    this.unknownDecls = null;
    try {
      return visitLambdaExp(paramModuleExp, paramVoid);
    } finally {
      this.currentModule = moduleExp;
      this.unknownDecls = hashtable;
    } 
  }
  
  protected Expression visitReferenceExp(ReferenceExp paramReferenceExp, Void paramVoid) {
    Declaration declaration2 = paramReferenceExp.getBinding();
    Declaration declaration1 = declaration2;
    if (declaration2 == null) {
      declaration1 = allocUnboundDecl(paramReferenceExp.getSymbol(), paramReferenceExp.isProcedureName());
      paramReferenceExp.setBinding(declaration1);
    } 
    if (declaration1.getFlag(65536L) && this.comp.resolve(paramReferenceExp.getSymbol(), paramReferenceExp.isProcedureName()) == null)
      maybeWarnNoDeclarationSeen(paramReferenceExp.getSymbol(), this.comp, paramReferenceExp); 
    capture(paramReferenceExp.contextDecl(), declaration1);
    return paramReferenceExp;
  }
  
  protected Expression visitSetExp(SetExp paramSetExp, Void paramVoid) {
    Declaration declaration2 = paramSetExp.binding;
    Declaration declaration1 = declaration2;
    if (declaration2 == null) {
      declaration1 = allocUnboundDecl(paramSetExp.getSymbol(), paramSetExp.isFuncDef());
      paramSetExp.binding = declaration1;
    } 
    if (!declaration1.ignorable()) {
      declaration2 = declaration1;
      if (!paramSetExp.isDefining())
        declaration2 = Declaration.followAliases(declaration1); 
      capture(paramSetExp.contextDecl(), declaration2);
    } 
    return super.visitSetExp(paramSetExp, paramVoid);
  }
  
  protected Expression visitThisExp(ThisExp paramThisExp, Void paramVoid) {
    if (paramThisExp.isForContext()) {
      getCurrentLambda().setImportsLexVars();
      return paramThisExp;
    } 
    return visitReferenceExp(paramThisExp, paramVoid);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/FindCapturedVars.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
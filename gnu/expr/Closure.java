package gnu.expr;

import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;

class Closure extends MethodProc {
  Object[][] evalFrames;
  
  LambdaExp lambda;
  
  public Closure(LambdaExp paramLambdaExp, CallContext paramCallContext) {
    this.lambda = paramLambdaExp;
    Object[][] arrayOfObject = paramCallContext.evalFrames;
    if (arrayOfObject != null) {
      int i;
      for (i = arrayOfObject.length; i > 0 && arrayOfObject[i - 1] == null; i--);
      this.evalFrames = new Object[i][];
      System.arraycopy(arrayOfObject, 0, this.evalFrames, 0, i);
    } 
    setSymbol(this.lambda.getSymbol());
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    int i;
    int k = ScopeExp.nesting(this.lambda);
    null = paramCallContext.values;
    Object[][] arrayOfObject2 = paramCallContext.evalFrames;
    if (this.evalFrames == null) {
      i = 0;
    } else {
      i = this.evalFrames.length;
    } 
    int j = i;
    if (k >= i)
      j = k; 
    Object[][] arrayOfObject1 = new Object[j + 10][];
    if (this.evalFrames != null)
      System.arraycopy(this.evalFrames, 0, arrayOfObject1, 0, this.evalFrames.length); 
    arrayOfObject1[k] = null;
    paramCallContext.evalFrames = arrayOfObject1;
    try {
      if (this.lambda.body == null) {
        StringBuffer stringBuffer = new StringBuffer("procedure ");
        String str2 = this.lambda.getName();
        String str1 = str2;
        if (str2 == null)
          str1 = "<anonymous>"; 
        stringBuffer.append(str1);
        i = this.lambda.getLineNumber();
        if (i > 0) {
          stringBuffer.append(" at line ");
          stringBuffer.append(i);
        } 
        stringBuffer.append(" was called before it was expanded");
        throw new RuntimeException(stringBuffer.toString());
      } 
    } finally {
      paramCallContext.evalFrames = arrayOfObject2;
    } 
    paramCallContext.evalFrames = arrayOfObject2;
  }
  
  public Object getProperty(Object paramObject1, Object paramObject2) {
    Object object2 = super.getProperty(paramObject1, paramObject2);
    Object object1 = object2;
    if (object2 == null)
      object1 = this.lambda.getProperty(paramObject1, paramObject2); 
    return object1;
  }
  
  public int match0(CallContext paramCallContext) {
    return matchN(new Object[0], paramCallContext);
  }
  
  public int match1(Object paramObject, CallContext paramCallContext) {
    return matchN(new Object[] { paramObject }, paramCallContext);
  }
  
  public int match2(Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    return matchN(new Object[] { paramObject1, paramObject2 }, paramCallContext);
  }
  
  public int match3(Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    return matchN(new Object[] { paramObject1, paramObject2, paramObject3 }, paramCallContext);
  }
  
  public int match4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    return matchN(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 }, paramCallContext);
  }
  
  public int matchN(Object[] paramArrayOfObject, CallContext paramCallContext) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual numArgs : ()I
    //   4: istore_3
    //   5: aload_1
    //   6: arraylength
    //   7: istore #8
    //   9: iload_3
    //   10: sipush #4095
    //   13: iand
    //   14: istore #4
    //   16: iload #8
    //   18: iload #4
    //   20: if_icmpge -> 29
    //   23: ldc -983040
    //   25: iload #4
    //   27: ior
    //   28: ireturn
    //   29: iload_3
    //   30: bipush #12
    //   32: ishr
    //   33: istore_3
    //   34: iload #8
    //   36: iload_3
    //   37: if_icmple -> 49
    //   40: iload_3
    //   41: iflt -> 49
    //   44: ldc -917504
    //   46: iload_3
    //   47: ior
    //   48: ireturn
    //   49: aload_0
    //   50: getfield lambda : Lgnu/expr/LambdaExp;
    //   53: getfield frameSize : I
    //   56: anewarray java/lang/Object
    //   59: astore #14
    //   61: aload_0
    //   62: getfield lambda : Lgnu/expr/LambdaExp;
    //   65: getfield keywords : [Lgnu/expr/Keyword;
    //   68: ifnonnull -> 206
    //   71: iconst_0
    //   72: istore_3
    //   73: aload_0
    //   74: getfield lambda : Lgnu/expr/LambdaExp;
    //   77: getfield defaultArgs : [Lgnu/expr/Expression;
    //   80: ifnonnull -> 218
    //   83: iconst_0
    //   84: istore #6
    //   86: iconst_0
    //   87: istore #5
    //   89: aload_0
    //   90: getfield lambda : Lgnu/expr/LambdaExp;
    //   93: getfield min_args : I
    //   96: istore #9
    //   98: aload_0
    //   99: getfield lambda : Lgnu/expr/LambdaExp;
    //   102: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   105: astore #13
    //   107: iconst_0
    //   108: istore #4
    //   110: iconst_0
    //   111: istore_3
    //   112: aload #13
    //   114: ifnull -> 512
    //   117: iload_3
    //   118: iload #9
    //   120: if_icmpge -> 233
    //   123: iload_3
    //   124: iconst_1
    //   125: iadd
    //   126: istore #7
    //   128: aload_1
    //   129: iload_3
    //   130: aaload
    //   131: astore #11
    //   133: iload #7
    //   135: istore_3
    //   136: aload #11
    //   138: astore #12
    //   140: aload #13
    //   142: getfield type : Lgnu/bytecode/Type;
    //   145: ifnull -> 160
    //   148: aload #13
    //   150: getfield type : Lgnu/bytecode/Type;
    //   153: aload #11
    //   155: invokevirtual coerceFromObject : (Ljava/lang/Object;)Ljava/lang/Object;
    //   158: astore #12
    //   160: aload #12
    //   162: astore #11
    //   164: aload #13
    //   166: invokevirtual isIndirectBinding : ()Z
    //   169: ifeq -> 186
    //   172: aload #13
    //   174: invokevirtual makeIndirectLocationFor : ()Lgnu/mapping/Location;
    //   177: astore #11
    //   179: aload #11
    //   181: aload #12
    //   183: invokevirtual set : (Ljava/lang/Object;)V
    //   186: aload #14
    //   188: aload #13
    //   190: getfield evalIndex : I
    //   193: aload #11
    //   195: aastore
    //   196: aload #13
    //   198: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   201: astore #13
    //   203: goto -> 112
    //   206: aload_0
    //   207: getfield lambda : Lgnu/expr/LambdaExp;
    //   210: getfield keywords : [Lgnu/expr/Keyword;
    //   213: arraylength
    //   214: istore_3
    //   215: goto -> 73
    //   218: aload_0
    //   219: getfield lambda : Lgnu/expr/LambdaExp;
    //   222: getfield defaultArgs : [Lgnu/expr/Expression;
    //   225: arraylength
    //   226: iload_3
    //   227: isub
    //   228: istore #6
    //   230: goto -> 86
    //   233: iload_3
    //   234: iload #9
    //   236: iload #6
    //   238: iadd
    //   239: if_icmpge -> 285
    //   242: iload_3
    //   243: iload #8
    //   245: if_icmpge -> 270
    //   248: iload_3
    //   249: iconst_1
    //   250: iadd
    //   251: istore #7
    //   253: aload_1
    //   254: iload_3
    //   255: aaload
    //   256: astore #11
    //   258: iload #7
    //   260: istore_3
    //   261: iload #5
    //   263: iconst_1
    //   264: iadd
    //   265: istore #5
    //   267: goto -> 136
    //   270: aload_0
    //   271: getfield lambda : Lgnu/expr/LambdaExp;
    //   274: iload #5
    //   276: aload_2
    //   277: invokevirtual evalDefaultArg : (ILgnu/mapping/CallContext;)Ljava/lang/Object;
    //   280: astore #11
    //   282: goto -> 261
    //   285: aload_0
    //   286: getfield lambda : Lgnu/expr/LambdaExp;
    //   289: getfield max_args : I
    //   292: ifge -> 438
    //   295: iload_3
    //   296: iload #9
    //   298: iload #6
    //   300: iadd
    //   301: if_icmpne -> 438
    //   304: aload #13
    //   306: getfield type : Lgnu/bytecode/Type;
    //   309: instanceof gnu/bytecode/ArrayType
    //   312: ifeq -> 428
    //   315: iload #8
    //   317: iload_3
    //   318: isub
    //   319: istore #10
    //   321: aload #13
    //   323: getfield type : Lgnu/bytecode/Type;
    //   326: checkcast gnu/bytecode/ArrayType
    //   329: invokevirtual getComponentType : ()Lgnu/bytecode/Type;
    //   332: astore #15
    //   334: aload #15
    //   336: getstatic gnu/bytecode/Type.objectType : Lgnu/bytecode/ClassType;
    //   339: if_acmpne -> 362
    //   342: iload #10
    //   344: anewarray java/lang/Object
    //   347: astore #11
    //   349: aload_1
    //   350: iload_3
    //   351: aload #11
    //   353: iconst_0
    //   354: iload #10
    //   356: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   359: goto -> 136
    //   362: aload #15
    //   364: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   367: iload #10
    //   369: invokestatic newInstance : (Ljava/lang/Class;I)Ljava/lang/Object;
    //   372: astore #12
    //   374: iconst_0
    //   375: istore #7
    //   377: aload #12
    //   379: astore #11
    //   381: iload #7
    //   383: iload #10
    //   385: if_icmpge -> 359
    //   388: aload #15
    //   390: aload_1
    //   391: iload_3
    //   392: iload #7
    //   394: iadd
    //   395: aaload
    //   396: invokevirtual coerceFromObject : (Ljava/lang/Object;)Ljava/lang/Object;
    //   399: astore #11
    //   401: aload #12
    //   403: iload #7
    //   405: aload #11
    //   407: invokestatic set : (Ljava/lang/Object;ILjava/lang/Object;)V
    //   410: iload #7
    //   412: iconst_1
    //   413: iadd
    //   414: istore #7
    //   416: goto -> 377
    //   419: astore_1
    //   420: ldc -786432
    //   422: iload_3
    //   423: iload #7
    //   425: iadd
    //   426: ior
    //   427: ireturn
    //   428: aload_1
    //   429: iload_3
    //   430: invokestatic makeList : ([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   433: astore #11
    //   435: goto -> 136
    //   438: aload_0
    //   439: getfield lambda : Lgnu/expr/LambdaExp;
    //   442: getfield keywords : [Lgnu/expr/Keyword;
    //   445: astore #11
    //   447: iload #4
    //   449: iconst_1
    //   450: iadd
    //   451: istore #7
    //   453: aload_1
    //   454: iload #9
    //   456: iload #6
    //   458: iadd
    //   459: aload #11
    //   461: iload #4
    //   463: aaload
    //   464: invokestatic searchForKeyword : ([Ljava/lang/Object;ILjava/lang/Object;)Ljava/lang/Object;
    //   467: astore #12
    //   469: aload #12
    //   471: astore #11
    //   473: aload #12
    //   475: getstatic gnu/expr/Special.dfault : Lgnu/expr/Special;
    //   478: if_acmpne -> 493
    //   481: aload_0
    //   482: getfield lambda : Lgnu/expr/LambdaExp;
    //   485: iload #5
    //   487: aload_2
    //   488: invokevirtual evalDefaultArg : (ILgnu/mapping/CallContext;)Ljava/lang/Object;
    //   491: astore #11
    //   493: iload #5
    //   495: iconst_1
    //   496: iadd
    //   497: istore #5
    //   499: iload #7
    //   501: istore #4
    //   503: goto -> 136
    //   506: astore_1
    //   507: ldc -786432
    //   509: iload_3
    //   510: ior
    //   511: ireturn
    //   512: aload_2
    //   513: aload #14
    //   515: putfield values : [Ljava/lang/Object;
    //   518: aload_2
    //   519: iconst_0
    //   520: putfield where : I
    //   523: aload_2
    //   524: iconst_0
    //   525: putfield next : I
    //   528: aload_2
    //   529: aload_0
    //   530: putfield proc : Lgnu/mapping/Procedure;
    //   533: iconst_0
    //   534: ireturn
    // Exception table:
    //   from	to	target	type
    //   148	160	506	java/lang/ClassCastException
    //   388	401	419	java/lang/ClassCastException
  }
  
  public int numArgs() {
    return this.lambda.min_args | this.lambda.max_args << 12;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/Closure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
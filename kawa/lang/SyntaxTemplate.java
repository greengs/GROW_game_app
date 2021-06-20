package kawa.lang;

import gnu.expr.Compilation;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Vector;

public class SyntaxTemplate implements Externalizable {
  static final int BUILD_CONS = 1;
  
  static final int BUILD_DOTS = 5;
  
  static final int BUILD_LIST1 = 8;
  
  static final int BUILD_LITERAL = 4;
  
  static final int BUILD_MISC = 0;
  
  static final int BUILD_NIL = 16;
  
  static final int BUILD_SYNTAX = 24;
  
  static final int BUILD_VAR = 2;
  
  static final int BUILD_VAR_CAR = 3;
  
  static final int BUILD_VECTOR = 40;
  
  static final int BUILD_WIDE = 7;
  
  static final String dots3 = "...";
  
  Object[] literal_values;
  
  int max_nesting;
  
  String patternNesting;
  
  String template_program;
  
  protected SyntaxTemplate() {}
  
  public SyntaxTemplate(Object paramObject, SyntaxForm paramSyntaxForm, Translator paramTranslator) {
    String str;
    if (paramTranslator == null || paramTranslator.patternScope == null) {
      str = "";
    } else {
      str = paramTranslator.patternScope.patternNesting.toString();
    } 
    this.patternNesting = str;
    StringBuffer stringBuffer = new StringBuffer();
    Vector vector = new Vector();
    convert_template(paramObject, paramSyntaxForm, stringBuffer, 0, vector, new IdentityHashMap<Object, Object>(), false, paramTranslator);
    this.template_program = stringBuffer.toString();
    this.literal_values = new Object[vector.size()];
    vector.copyInto(this.literal_values);
  }
  
  public SyntaxTemplate(String paramString1, String paramString2, Object[] paramArrayOfObject, int paramInt) {
    this.patternNesting = paramString1;
    this.template_program = paramString2;
    this.literal_values = paramArrayOfObject;
    this.max_nesting = paramInt;
  }
  
  private int get_count(Object paramObject, int paramInt, int[] paramArrayOfint) {
    int i;
    for (i = 0; i < paramInt; i++)
      paramObject = ((Object[])paramObject)[paramArrayOfint[i]]; 
    return ((Object[])paramObject).length;
  }
  
  static int indexOf(Vector paramVector, Object paramObject) {
    int j = paramVector.size();
    for (int i = 0; i < j; i++) {
      if (paramVector.elementAt(i) == paramObject)
        return i; 
    } 
    return -1;
  }
  
  public int convert_template(Object paramObject1, SyntaxForm paramSyntaxForm, StringBuffer paramStringBuffer, int paramInt, Vector paramVector, Object paramObject2, boolean paramBoolean, Translator paramTranslator) {
    // Byte code:
    //   0: aload_1
    //   1: instanceof kawa/lang/SyntaxForm
    //   4: ifeq -> 22
    //   7: aload_1
    //   8: checkcast kawa/lang/SyntaxForm
    //   11: astore_2
    //   12: aload_2
    //   13: invokeinterface getDatum : ()Ljava/lang/Object;
    //   18: astore_1
    //   19: goto -> 0
    //   22: aload_1
    //   23: instanceof gnu/lists/Pair
    //   26: ifne -> 36
    //   29: aload_1
    //   30: instanceof gnu/lists/FVector
    //   33: ifeq -> 75
    //   36: aload #6
    //   38: checkcast java/util/IdentityHashMap
    //   41: astore #17
    //   43: aload #17
    //   45: aload_1
    //   46: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   49: ifeq -> 67
    //   52: aload #8
    //   54: ldc 'self-referential (cyclic) syntax template'
    //   56: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   59: pop
    //   60: bipush #-2
    //   62: istore #4
    //   64: iload #4
    //   66: ireturn
    //   67: aload #17
    //   69: aload_1
    //   70: aload_1
    //   71: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   74: pop
    //   75: aload_1
    //   76: instanceof gnu/lists/Pair
    //   79: ifeq -> 546
    //   82: aload_1
    //   83: checkcast gnu/lists/Pair
    //   86: astore #17
    //   88: bipush #-2
    //   90: istore #10
    //   92: aload_3
    //   93: invokevirtual length : ()I
    //   96: istore #14
    //   98: aload #17
    //   100: invokevirtual getCar : ()Ljava/lang/Object;
    //   103: astore #18
    //   105: aload #8
    //   107: aload #18
    //   109: ldc '...'
    //   111: invokevirtual matches : (Ljava/lang/Object;Ljava/lang/String;)Z
    //   114: ifeq -> 259
    //   117: aload #17
    //   119: invokevirtual getCdr : ()Ljava/lang/Object;
    //   122: invokestatic stripSyntax : (Ljava/lang/Object;)Ljava/lang/Object;
    //   125: astore #19
    //   127: aload #19
    //   129: instanceof gnu/lists/Pair
    //   132: ifeq -> 259
    //   135: aload #19
    //   137: checkcast gnu/lists/Pair
    //   140: astore #19
    //   142: aload #19
    //   144: invokevirtual getCar : ()Ljava/lang/Object;
    //   147: ldc '...'
    //   149: if_acmpne -> 259
    //   152: aload #19
    //   154: invokevirtual getCdr : ()Ljava/lang/Object;
    //   157: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   160: if_acmpne -> 259
    //   163: ldc '...'
    //   165: astore_2
    //   166: aload #5
    //   168: aload_2
    //   169: invokestatic indexOf : (Ljava/util/Vector;Ljava/lang/Object;)I
    //   172: istore #9
    //   174: iload #9
    //   176: istore #4
    //   178: iload #9
    //   180: ifge -> 196
    //   183: aload #5
    //   185: invokevirtual size : ()I
    //   188: istore #4
    //   190: aload #5
    //   192: aload_2
    //   193: invokevirtual addElement : (Ljava/lang/Object;)V
    //   196: aload_2
    //   197: instanceof gnu/mapping/Symbol
    //   200: ifeq -> 214
    //   203: aload #8
    //   205: aload_2
    //   206: aload #8
    //   208: invokevirtual currentScope : ()Lgnu/expr/ScopeExp;
    //   211: invokevirtual noteAccess : (Ljava/lang/Object;Lgnu/expr/ScopeExp;)V
    //   214: aload_2
    //   215: instanceof kawa/lang/SyntaxForm
    //   218: ifne -> 234
    //   221: aload_2
    //   222: ldc '...'
    //   224: if_acmpeq -> 234
    //   227: aload_3
    //   228: bipush #24
    //   230: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   233: pop
    //   234: aload_3
    //   235: iload #4
    //   237: bipush #8
    //   239: imul
    //   240: iconst_4
    //   241: iadd
    //   242: i2c
    //   243: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   246: pop
    //   247: aload_2
    //   248: ldc '...'
    //   250: if_acmpne -> 746
    //   253: iconst_m1
    //   254: istore #4
    //   256: iload #4
    //   258: ireturn
    //   259: aload #5
    //   261: invokevirtual size : ()I
    //   264: istore #15
    //   266: aload_3
    //   267: bipush #8
    //   269: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   272: pop
    //   273: iconst_0
    //   274: istore #9
    //   276: aload #17
    //   278: invokevirtual getCdr : ()Ljava/lang/Object;
    //   281: astore #17
    //   283: aload #17
    //   285: instanceof gnu/lists/Pair
    //   288: ifeq -> 313
    //   291: aload #17
    //   293: checkcast gnu/lists/Pair
    //   296: astore #19
    //   298: aload #8
    //   300: aload #19
    //   302: invokevirtual getCar : ()Ljava/lang/Object;
    //   305: ldc '...'
    //   307: invokevirtual matches : (Ljava/lang/Object;Ljava/lang/String;)Z
    //   310: ifne -> 467
    //   313: aload_0
    //   314: aload #18
    //   316: aload_2
    //   317: aload_3
    //   318: iload #4
    //   320: iload #9
    //   322: iadd
    //   323: aload #5
    //   325: aload #6
    //   327: iconst_0
    //   328: aload #8
    //   330: invokevirtual convert_template : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;Ljava/lang/StringBuffer;ILjava/util/Vector;Ljava/lang/Object;ZLkawa/lang/Translator;)I
    //   333: istore #12
    //   335: aload #17
    //   337: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   340: if_acmpeq -> 383
    //   343: aload_3
    //   344: iload #14
    //   346: aload_3
    //   347: invokevirtual length : ()I
    //   350: iload #14
    //   352: isub
    //   353: iconst_1
    //   354: isub
    //   355: iconst_3
    //   356: ishl
    //   357: iconst_1
    //   358: iadd
    //   359: i2c
    //   360: invokevirtual setCharAt : (IC)V
    //   363: aload_0
    //   364: aload #17
    //   366: aload_2
    //   367: aload_3
    //   368: iload #4
    //   370: aload #5
    //   372: aload #6
    //   374: iload #7
    //   376: aload #8
    //   378: invokevirtual convert_template : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;Ljava/lang/StringBuffer;ILjava/util/Vector;Ljava/lang/Object;ZLkawa/lang/Translator;)I
    //   381: istore #10
    //   383: iload #9
    //   385: ifle -> 489
    //   388: iload #12
    //   390: ifge -> 401
    //   393: aload #8
    //   395: ldc '... follows template with no suitably-nested pattern variable'
    //   397: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   400: pop
    //   401: iload #9
    //   403: istore #11
    //   405: iload #11
    //   407: iconst_1
    //   408: isub
    //   409: istore #13
    //   411: iload #13
    //   413: iflt -> 489
    //   416: aload_3
    //   417: iload #14
    //   419: iload #13
    //   421: iadd
    //   422: iconst_1
    //   423: iadd
    //   424: iload #12
    //   426: iconst_3
    //   427: ishl
    //   428: iconst_5
    //   429: iadd
    //   430: i2c
    //   431: invokevirtual setCharAt : (IC)V
    //   434: iload #4
    //   436: iload #9
    //   438: iadd
    //   439: istore #16
    //   441: iload #13
    //   443: istore #11
    //   445: iload #16
    //   447: aload_0
    //   448: getfield max_nesting : I
    //   451: if_icmplt -> 405
    //   454: aload_0
    //   455: iload #16
    //   457: putfield max_nesting : I
    //   460: iload #13
    //   462: istore #11
    //   464: goto -> 405
    //   467: iload #9
    //   469: iconst_1
    //   470: iadd
    //   471: istore #9
    //   473: aload #19
    //   475: invokevirtual getCdr : ()Ljava/lang/Object;
    //   478: astore #17
    //   480: aload_3
    //   481: iconst_5
    //   482: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   485: pop
    //   486: goto -> 283
    //   489: iload #12
    //   491: istore #4
    //   493: iload #12
    //   495: ifge -> 64
    //   498: iload #10
    //   500: iflt -> 506
    //   503: iload #10
    //   505: ireturn
    //   506: iload #12
    //   508: iconst_m1
    //   509: if_icmpeq -> 518
    //   512: iload #10
    //   514: iconst_m1
    //   515: if_icmpne -> 520
    //   518: iconst_m1
    //   519: ireturn
    //   520: iload #7
    //   522: ifeq -> 528
    //   525: bipush #-2
    //   527: ireturn
    //   528: aload #5
    //   530: iload #15
    //   532: invokevirtual setSize : (I)V
    //   535: aload_3
    //   536: iload #14
    //   538: invokevirtual setLength : (I)V
    //   541: aload_1
    //   542: astore_2
    //   543: goto -> 166
    //   546: aload_1
    //   547: instanceof gnu/lists/FVector
    //   550: ifeq -> 583
    //   553: aload_3
    //   554: bipush #40
    //   556: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   559: pop
    //   560: aload_0
    //   561: aload_1
    //   562: checkcast gnu/lists/FVector
    //   565: invokestatic makeList : (Ljava/util/List;)Lgnu/lists/LList;
    //   568: aload_2
    //   569: aload_3
    //   570: iload #4
    //   572: aload #5
    //   574: aload #6
    //   576: iconst_1
    //   577: aload #8
    //   579: invokevirtual convert_template : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;Ljava/lang/StringBuffer;ILjava/util/Vector;Ljava/lang/Object;ZLkawa/lang/Translator;)I
    //   582: ireturn
    //   583: aload_1
    //   584: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   587: if_acmpne -> 600
    //   590: aload_3
    //   591: bipush #16
    //   593: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   596: pop
    //   597: bipush #-2
    //   599: ireturn
    //   600: aload_1
    //   601: astore_2
    //   602: aload_1
    //   603: instanceof gnu/mapping/Symbol
    //   606: ifeq -> 166
    //   609: aload_1
    //   610: astore_2
    //   611: aload #8
    //   613: ifnull -> 166
    //   616: aload_1
    //   617: astore_2
    //   618: aload #8
    //   620: getfield patternScope : Lkawa/lang/PatternScope;
    //   623: ifnull -> 166
    //   626: aload #8
    //   628: getfield patternScope : Lkawa/lang/PatternScope;
    //   631: getfield pattern_names : Ljava/util/Vector;
    //   634: aload_1
    //   635: invokestatic indexOf : (Ljava/util/Vector;Ljava/lang/Object;)I
    //   638: istore #10
    //   640: aload_1
    //   641: astore_2
    //   642: iload #10
    //   644: iflt -> 166
    //   647: aload_0
    //   648: getfield patternNesting : Ljava/lang/String;
    //   651: iload #10
    //   653: invokevirtual charAt : (I)C
    //   656: istore #11
    //   658: iload #11
    //   660: iconst_1
    //   661: iand
    //   662: ifeq -> 734
    //   665: iconst_3
    //   666: istore #9
    //   668: iload #11
    //   670: iconst_1
    //   671: ishr
    //   672: istore #11
    //   674: iload #11
    //   676: iload #4
    //   678: if_icmple -> 706
    //   681: aload #8
    //   683: new java/lang/StringBuilder
    //   686: dup
    //   687: invokespecial <init> : ()V
    //   690: ldc 'inconsistent ... nesting of '
    //   692: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   695: aload_1
    //   696: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   699: invokevirtual toString : ()Ljava/lang/String;
    //   702: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   705: pop
    //   706: aload_3
    //   707: iload #10
    //   709: bipush #8
    //   711: imul
    //   712: iload #9
    //   714: iadd
    //   715: i2c
    //   716: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   719: pop
    //   720: iload #11
    //   722: iload #4
    //   724: if_icmpne -> 740
    //   727: iload #10
    //   729: istore #4
    //   731: iload #4
    //   733: ireturn
    //   734: iconst_2
    //   735: istore #9
    //   737: goto -> 668
    //   740: iconst_m1
    //   741: istore #4
    //   743: goto -> 731
    //   746: bipush #-2
    //   748: istore #4
    //   750: goto -> 256
  }
  
  Object execute(int paramInt1, Object[] paramArrayOfObject, int paramInt2, int[] paramArrayOfint, Translator paramTranslator, TemplateScope paramTemplateScope) {
    Object object1;
    Object object2;
    char c = this.template_program.charAt(paramInt1);
    int i = paramInt1;
    for (paramInt1 = c; (paramInt1 & 0x7) == 7; paramInt1 = paramInt1 - 7 << 13 | str.charAt(++i))
      String str = this.template_program; 
    if (paramInt1 == 8)
      return executeToList(i + 1, paramArrayOfObject, paramInt2, paramArrayOfint, paramTranslator, paramTemplateScope); 
    if (paramInt1 == 16)
      return LList.Empty; 
    if (paramInt1 == 24) {
      object2 = execute(i + 1, paramArrayOfObject, paramInt2, paramArrayOfint, paramTranslator, paramTemplateScope);
      object1 = object2;
      return (object2 != LList.Empty) ? SyntaxForms.makeForm(object2, paramTemplateScope) : object1;
    } 
    if ((paramInt1 & 0x7) == 1) {
      Pair pair = null;
      Object object = null;
      while (true) {
        Object object3;
        Pair pair1;
        LList lList = executeToList(++i, (Object[])object1, paramInt2, (int[])object2, paramTranslator, paramTemplateScope);
        if (pair == null) {
          object3 = lList;
          pair1 = pair;
        } else {
          pair.setCdrBackdoor(lList);
          pair1 = pair;
          object3 = object;
        } 
        while (lList instanceof Pair) {
          pair1 = (Pair)lList;
          Object object4 = pair1.getCdr();
        } 
        int j = i + (paramInt1 >> 3);
        char c1 = this.template_program.charAt(j);
        paramInt1 = c1;
        pair = pair1;
        object = object3;
        i = j;
        if ((c1 & 0x7) != 1) {
          object1 = execute(j, (Object[])object1, paramInt2, (int[])object2, paramTranslator, paramTemplateScope);
          if (pair1 != null) {
            pair1.setCdrBackdoor(object1);
            object1 = object3;
          } 
          return object1;
        } 
      } 
    } 
    if (paramInt1 == 40)
      return new FVector((List)execute(i + 1, (Object[])object1, paramInt2, (int[])object2, paramTranslator, paramTemplateScope)); 
    if ((paramInt1 & 0x7) == 4)
      return this.literal_values[paramInt1 >> 3]; 
    if ((paramInt1 & 0x6) == 2) {
      object2 = get_var(paramInt1 >> 3, (Object[])object1, (int[])object2);
      object1 = object2;
      if ((paramInt1 & 0x7) == 3)
        object1 = ((Pair)object2).getCar(); 
      return object1;
    } 
    throw new Error("unknown template code: " + paramInt1 + " at " + i);
  }
  
  public Object execute(Object[] paramArrayOfObject, TemplateScope paramTemplateScope) {
    return execute(0, paramArrayOfObject, 0, new int[this.max_nesting], (Translator)Compilation.getCurrent(), paramTemplateScope);
  }
  
  public Object execute(Object[] paramArrayOfObject, Translator paramTranslator, TemplateScope paramTemplateScope) {
    return execute(0, paramArrayOfObject, 0, new int[this.max_nesting], paramTranslator, paramTemplateScope);
  }
  
  LList executeToList(int paramInt1, Object[] paramArrayOfObject, int paramInt2, int[] paramArrayOfint, Translator paramTranslator, TemplateScope paramTemplateScope) {
    Pair pair;
    int j = this.template_program.charAt(paramInt1);
    int i = paramInt1;
    while ((j & 0x7) == 7) {
      String str = this.template_program;
      j = j - 7 << 13 | str.charAt(++i);
    } 
    if ((j & 0x7) == 3) {
      pair = (Pair)get_var(j >> 3, paramArrayOfObject, paramArrayOfint);
      return (LList)Translator.makePair(pair, pair.getCar(), LList.Empty);
    } 
    if ((j & 0x7) == 5) {
      j = get_count(pair[j >> 3], paramInt2, paramArrayOfint);
      LList lList = LList.Empty;
      Pair pair1 = null;
      paramInt1 = 0;
      while (true) {
        LList lList1 = lList;
        if (paramInt1 < j) {
          paramArrayOfint[paramInt2] = paramInt1;
          lList1 = executeToList(i + 1, (Object[])pair, paramInt2 + 1, paramArrayOfint, paramTranslator, paramTemplateScope);
          if (pair1 == null) {
            lList = lList1;
          } else {
            pair1.setCdrBackdoor(lList1);
          } 
          while (lList1 instanceof Pair) {
            pair1 = (Pair)lList1;
            lList1 = (LList)pair1.getCdr();
          } 
          paramInt1++;
          continue;
        } 
        return lList1;
      } 
    } 
    return (LList)new Pair(execute(paramInt1, (Object[])pair, paramInt2, paramArrayOfint, paramTranslator, paramTemplateScope), LList.Empty);
  }
  
  Object get_var(int paramInt, Object[] paramArrayOfObject, int[] paramArrayOfint) {
    Object object1 = paramArrayOfObject[paramInt];
    Object object2 = object1;
    if (paramInt < this.patternNesting.length()) {
      char c = this.patternNesting.charAt(paramInt);
      paramInt = 0;
      while (true) {
        object2 = object1;
        if (paramInt < c >> 1) {
          object1 = ((Object[])object1)[paramArrayOfint[paramInt]];
          paramInt++;
          continue;
        } 
        break;
      } 
    } 
    return object2;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.patternNesting = (String)paramObjectInput.readObject();
    this.template_program = (String)paramObjectInput.readObject();
    this.literal_values = (Object[])paramObjectInput.readObject();
    this.max_nesting = paramObjectInput.readInt();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.patternNesting);
    paramObjectOutput.writeObject(this.template_program);
    paramObjectOutput.writeObject(this.literal_values);
    paramObjectOutput.writeInt(this.max_nesting);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/SyntaxTemplate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
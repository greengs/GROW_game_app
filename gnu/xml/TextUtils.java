package gnu.xml;

import gnu.kawa.xml.KNode;
import gnu.lists.Consumer;
import gnu.mapping.Values;
import java.math.BigDecimal;

public class TextUtils {
  public static String asString(Object paramObject) {
    if (paramObject == Values.empty || paramObject == null)
      return ""; 
    if (paramObject instanceof Values)
      throw new ClassCastException(); 
    StringBuffer stringBuffer = new StringBuffer(100);
    stringValue(paramObject, stringBuffer);
    return stringBuffer.toString();
  }
  
  public static String replaceWhitespace(String paramString, boolean paramBoolean) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #10
    //   3: aload_0
    //   4: invokevirtual length : ()I
    //   7: istore #9
    //   9: iload_1
    //   10: ifeq -> 166
    //   13: iconst_1
    //   14: istore #4
    //   16: iconst_0
    //   17: istore #5
    //   19: iload #5
    //   21: iload #9
    //   23: if_icmpge -> 343
    //   26: iload #5
    //   28: iconst_1
    //   29: iadd
    //   30: istore #6
    //   32: aload_0
    //   33: iload #5
    //   35: invokevirtual charAt : (I)C
    //   38: istore_3
    //   39: iload_3
    //   40: bipush #32
    //   42: if_icmpne -> 172
    //   45: iconst_1
    //   46: istore #5
    //   48: iload_3
    //   49: istore_2
    //   50: aload #10
    //   52: astore #11
    //   54: aload #10
    //   56: ifnonnull -> 214
    //   59: iload #5
    //   61: iconst_2
    //   62: if_icmpeq -> 115
    //   65: iload #5
    //   67: iconst_1
    //   68: if_icmpne -> 80
    //   71: iload #4
    //   73: ifle -> 80
    //   76: iload_1
    //   77: ifne -> 115
    //   80: iload_3
    //   81: istore_2
    //   82: aload #10
    //   84: astore #11
    //   86: iload #5
    //   88: iconst_1
    //   89: if_icmpne -> 214
    //   92: iload_3
    //   93: istore_2
    //   94: aload #10
    //   96: astore #11
    //   98: iload #6
    //   100: iload #9
    //   102: if_icmpne -> 214
    //   105: iload_3
    //   106: istore_2
    //   107: aload #10
    //   109: astore #11
    //   111: iload_1
    //   112: ifeq -> 214
    //   115: new java/lang/StringBuilder
    //   118: dup
    //   119: invokespecial <init> : ()V
    //   122: astore #11
    //   124: iload #4
    //   126: ifle -> 202
    //   129: iload #6
    //   131: iconst_2
    //   132: isub
    //   133: istore #7
    //   135: iconst_0
    //   136: istore #8
    //   138: iload #8
    //   140: iload #7
    //   142: if_icmpge -> 211
    //   145: aload #11
    //   147: aload_0
    //   148: iload #8
    //   150: invokevirtual charAt : (I)C
    //   153: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   156: pop
    //   157: iload #8
    //   159: iconst_1
    //   160: iadd
    //   161: istore #8
    //   163: goto -> 138
    //   166: iconst_0
    //   167: istore #4
    //   169: goto -> 16
    //   172: iload_3
    //   173: bipush #9
    //   175: if_icmpeq -> 190
    //   178: iload_3
    //   179: bipush #13
    //   181: if_icmpeq -> 190
    //   184: iload_3
    //   185: bipush #10
    //   187: if_icmpne -> 196
    //   190: iconst_2
    //   191: istore #5
    //   193: goto -> 48
    //   196: iconst_0
    //   197: istore #5
    //   199: goto -> 48
    //   202: iload #6
    //   204: iconst_1
    //   205: isub
    //   206: istore #7
    //   208: goto -> 135
    //   211: bipush #32
    //   213: istore_2
    //   214: iload #4
    //   216: istore #7
    //   218: iload_1
    //   219: ifeq -> 316
    //   222: iload #4
    //   224: ifle -> 276
    //   227: iload #5
    //   229: ifne -> 276
    //   232: aload #11
    //   234: ifnull -> 253
    //   237: aload #11
    //   239: invokevirtual length : ()I
    //   242: ifle -> 253
    //   245: aload #11
    //   247: bipush #32
    //   249: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   252: pop
    //   253: iconst_0
    //   254: istore #4
    //   256: iload #4
    //   258: istore #7
    //   260: iload #4
    //   262: ifle -> 316
    //   265: iload #6
    //   267: istore #5
    //   269: aload #11
    //   271: astore #10
    //   273: goto -> 19
    //   276: iload #5
    //   278: iconst_2
    //   279: if_icmpeq -> 293
    //   282: iload #5
    //   284: iconst_1
    //   285: if_icmpne -> 299
    //   288: iload #4
    //   290: ifle -> 299
    //   293: iconst_2
    //   294: istore #4
    //   296: goto -> 256
    //   299: iload #5
    //   301: ifle -> 310
    //   304: iconst_1
    //   305: istore #4
    //   307: goto -> 256
    //   310: iconst_0
    //   311: istore #4
    //   313: goto -> 256
    //   316: aload #11
    //   318: ifnull -> 328
    //   321: aload #11
    //   323: iload_2
    //   324: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   327: pop
    //   328: iload #6
    //   330: istore #5
    //   332: iload #7
    //   334: istore #4
    //   336: aload #11
    //   338: astore #10
    //   340: goto -> 19
    //   343: aload #10
    //   345: ifnull -> 354
    //   348: aload #10
    //   350: invokevirtual toString : ()Ljava/lang/String;
    //   353: astore_0
    //   354: aload_0
    //   355: areturn
  }
  
  public static String stringValue(Object paramObject) {
    StringBuffer stringBuffer = new StringBuffer(100);
    if (paramObject instanceof Values) {
      paramObject = paramObject;
      int i = 0;
      while (true) {
        int j = paramObject.getNextKind(i);
        if (j != 0) {
          if (j == 32) {
            stringValue(paramObject.getPosNext(i), stringBuffer);
          } else {
            paramObject.stringValue(paramObject.posToDataIndex(i), stringBuffer);
          } 
          i = paramObject.nextPos(i);
          continue;
        } 
        return stringBuffer.toString();
      } 
    } 
    stringValue(paramObject, stringBuffer);
    return stringBuffer.toString();
  }
  
  public static void stringValue(Object paramObject, StringBuffer paramStringBuffer) {
    Object object;
    if (paramObject instanceof KNode) {
      paramObject = paramObject;
      object = ((KNode)paramObject).sequence;
      object.stringValue(object.posToDataIndex(((KNode)paramObject).ipos), paramStringBuffer);
      return;
    } 
    if (paramObject instanceof BigDecimal) {
      object = XMLPrinter.formatDecimal((BigDecimal)paramObject);
    } else if (paramObject instanceof Double || paramObject instanceof gnu.math.DFloNum) {
      object = XMLPrinter.formatDouble(((Number)paramObject).doubleValue());
    } else {
      object = paramObject;
      if (paramObject instanceof Float)
        object = XMLPrinter.formatFloat(((Number)paramObject).floatValue()); 
    } 
    if (object != null && object != Values.empty) {
      paramStringBuffer.append(object);
      return;
    } 
  }
  
  public static void textValue(Object paramObject, Consumer paramConsumer) {
    if (paramObject == null || (paramObject instanceof Values && ((Values)paramObject).isEmpty()))
      return; 
    if (paramObject instanceof String) {
      paramObject = paramObject;
    } else {
      StringBuffer stringBuffer = new StringBuffer();
      if (paramObject instanceof Values) {
        paramObject = ((Values)paramObject).getValues();
        for (int i = 0; i < paramObject.length; i++) {
          if (i > 0)
            stringBuffer.append(' '); 
          stringValue(paramObject[i], stringBuffer);
        } 
      } else {
        stringValue(paramObject, stringBuffer);
      } 
      paramObject = stringBuffer.toString();
    } 
    paramConsumer.write((String)paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xml/TextUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package androidx.vectordrawable.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.annotation.AnimatorRes;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AnimatorInflaterCompat {
  private static final boolean DBG_ANIMATOR_INFLATER = false;
  
  private static final int MAX_NUM_POINTS = 100;
  
  private static final String TAG = "AnimatorInflater";
  
  private static final int TOGETHER = 0;
  
  private static final int VALUE_TYPE_COLOR = 3;
  
  private static final int VALUE_TYPE_FLOAT = 0;
  
  private static final int VALUE_TYPE_INT = 1;
  
  private static final int VALUE_TYPE_PATH = 2;
  
  private static final int VALUE_TYPE_UNDEFINED = 4;
  
  private static Animator createAnimatorFromXml(Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, float paramFloat) throws XmlPullParserException, IOException {
    return createAnimatorFromXml(paramContext, paramResources, paramTheme, paramXmlPullParser, Xml.asAttributeSet(paramXmlPullParser), null, 0, paramFloat);
  }
  
  private static Animator createAnimatorFromXml(Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, AnimatorSet paramAnimatorSet, int paramInt, float paramFloat) throws XmlPullParserException, IOException {
    Animator[] arrayOfAnimator;
    TypedArray typedArray = null;
    ArrayList<TypedArray> arrayList = null;
    int i = paramXmlPullParser.getDepth();
    while (true) {
      int j = paramXmlPullParser.next();
      if ((j != 3 || paramXmlPullParser.getDepth() > i) && j != 1) {
        if (j == 2) {
          ObjectAnimator objectAnimator;
          TypedArray typedArray1;
          String str = paramXmlPullParser.getName();
          j = 0;
          if (str.equals("objectAnimator")) {
            objectAnimator = loadObjectAnimator(paramContext, paramResources, paramTheme, paramAttributeSet, paramFloat, paramXmlPullParser);
          } else {
            ValueAnimator valueAnimator;
            if (objectAnimator.equals("animator")) {
              valueAnimator = loadAnimator(paramContext, paramResources, paramTheme, paramAttributeSet, null, paramFloat, paramXmlPullParser);
            } else {
              AnimatorSet animatorSet;
              if (valueAnimator.equals("set")) {
                animatorSet = new AnimatorSet();
                typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_ANIMATOR_SET);
                int k = TypedArrayUtils.getNamedInt(typedArray, paramXmlPullParser, "ordering", 0, 0);
                createAnimatorFromXml(paramContext, paramResources, paramTheme, paramXmlPullParser, paramAttributeSet, animatorSet, k, paramFloat);
                typedArray.recycle();
              } else if (animatorSet.equals("propertyValuesHolder")) {
                PropertyValuesHolder[] arrayOfPropertyValuesHolder = loadValues(paramContext, paramResources, paramTheme, paramXmlPullParser, Xml.asAttributeSet(paramXmlPullParser));
                if (arrayOfPropertyValuesHolder != null && typedArray != null && typedArray instanceof ValueAnimator)
                  ((ValueAnimator)typedArray).setValues(arrayOfPropertyValuesHolder); 
                j = 1;
                typedArray1 = typedArray;
              } else {
                throw new RuntimeException("Unknown animator name: " + paramXmlPullParser.getName());
              } 
            } 
          } 
          typedArray = typedArray1;
          if (paramAnimatorSet != null) {
            typedArray = typedArray1;
            if (j == 0) {
              ArrayList<TypedArray> arrayList1 = arrayList;
              if (arrayList == null)
                arrayList1 = new ArrayList(); 
              arrayList1.add(typedArray1);
              typedArray = typedArray1;
              arrayList = arrayList1;
            } 
          } 
        } 
        continue;
      } 
      break;
    } 
    if (paramAnimatorSet != null && arrayList != null) {
      arrayOfAnimator = new Animator[arrayList.size()];
      int j = 0;
      Iterator<TypedArray> iterator = arrayList.iterator();
      while (iterator.hasNext()) {
        arrayOfAnimator[j] = (Animator)iterator.next();
        j++;
      } 
      if (paramInt == 0) {
        paramAnimatorSet.playTogether(arrayOfAnimator);
        return (Animator)typedArray;
      } 
    } else {
      return (Animator)typedArray;
    } 
    paramAnimatorSet.playSequentially(arrayOfAnimator);
    return (Animator)typedArray;
  }
  
  private static Keyframe createNewKeyframe(Keyframe paramKeyframe, float paramFloat) {
    return (paramKeyframe.getType() == float.class) ? Keyframe.ofFloat(paramFloat) : ((paramKeyframe.getType() == int.class) ? Keyframe.ofInt(paramFloat) : Keyframe.ofObject(paramFloat));
  }
  
  private static void distributeKeyframes(Keyframe[] paramArrayOfKeyframe, float paramFloat, int paramInt1, int paramInt2) {
    paramFloat /= (paramInt2 - paramInt1 + 2);
    while (paramInt1 <= paramInt2) {
      paramArrayOfKeyframe[paramInt1].setFraction(paramArrayOfKeyframe[paramInt1 - 1].getFraction() + paramFloat);
      paramInt1++;
    } 
  }
  
  private static void dumpKeyframes(Object[] paramArrayOfObject, String paramString) {
    if (paramArrayOfObject != null && paramArrayOfObject.length != 0) {
      Log.d("AnimatorInflater", paramString);
      int j = paramArrayOfObject.length;
      int i = 0;
      while (true) {
        if (i < j) {
          Float float_;
          Object object;
          Keyframe keyframe = (Keyframe)paramArrayOfObject[i];
          StringBuilder stringBuilder = (new StringBuilder()).append("Keyframe ").append(i).append(": fraction ");
          if (keyframe.getFraction() < 0.0F) {
            paramString = "null";
          } else {
            float_ = Float.valueOf(keyframe.getFraction());
          } 
          stringBuilder = stringBuilder.append(float_).append(", ").append(", value : ");
          if (keyframe.hasValue()) {
            object = keyframe.getValue();
          } else {
            object = "null";
          } 
          Log.d("AnimatorInflater", stringBuilder.append(object).toString());
          i++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  private static PropertyValuesHolder getPVH(TypedArray paramTypedArray, int paramInt1, int paramInt2, int paramInt3, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: iload_2
    //   2: invokevirtual peekValue : (I)Landroid/util/TypedValue;
    //   5: astore #12
    //   7: aload #12
    //   9: ifnull -> 219
    //   12: iconst_1
    //   13: istore #8
    //   15: iload #8
    //   17: ifeq -> 225
    //   20: aload #12
    //   22: getfield type : I
    //   25: istore #10
    //   27: aload_0
    //   28: iload_3
    //   29: invokevirtual peekValue : (I)Landroid/util/TypedValue;
    //   32: astore #12
    //   34: aload #12
    //   36: ifnull -> 231
    //   39: iconst_1
    //   40: istore #9
    //   42: iload #9
    //   44: ifeq -> 237
    //   47: aload #12
    //   49: getfield type : I
    //   52: istore #11
    //   54: iload_1
    //   55: istore #7
    //   57: iload_1
    //   58: iconst_4
    //   59: if_icmpne -> 91
    //   62: iload #8
    //   64: ifeq -> 75
    //   67: iload #10
    //   69: invokestatic isColorType : (I)Z
    //   72: ifne -> 88
    //   75: iload #9
    //   77: ifeq -> 243
    //   80: iload #11
    //   82: invokestatic isColorType : (I)Z
    //   85: ifeq -> 243
    //   88: iconst_3
    //   89: istore #7
    //   91: iload #7
    //   93: ifne -> 249
    //   96: iconst_1
    //   97: istore_1
    //   98: aconst_null
    //   99: astore #14
    //   101: aconst_null
    //   102: astore #12
    //   104: iload #7
    //   106: iconst_2
    //   107: if_icmpne -> 323
    //   110: aload_0
    //   111: iload_2
    //   112: invokevirtual getString : (I)Ljava/lang/String;
    //   115: astore #13
    //   117: aload_0
    //   118: iload_3
    //   119: invokevirtual getString : (I)Ljava/lang/String;
    //   122: astore #14
    //   124: aload #13
    //   126: invokestatic createNodesFromPathData : (Ljava/lang/String;)[Landroidx/core/graphics/PathParser$PathDataNode;
    //   129: astore #15
    //   131: aload #14
    //   133: invokestatic createNodesFromPathData : (Ljava/lang/String;)[Landroidx/core/graphics/PathParser$PathDataNode;
    //   136: astore #16
    //   138: aload #15
    //   140: ifnonnull -> 151
    //   143: aload #12
    //   145: astore_0
    //   146: aload #16
    //   148: ifnull -> 275
    //   151: aload #15
    //   153: ifnull -> 293
    //   156: new androidx/vectordrawable/graphics/drawable/AnimatorInflaterCompat$PathDataEvaluator
    //   159: dup
    //   160: invokespecial <init> : ()V
    //   163: astore_0
    //   164: aload #16
    //   166: ifnull -> 277
    //   169: aload #15
    //   171: aload #16
    //   173: invokestatic canMorph : ([Landroidx/core/graphics/PathParser$PathDataNode;[Landroidx/core/graphics/PathParser$PathDataNode;)Z
    //   176: ifne -> 254
    //   179: new android/view/InflateException
    //   182: dup
    //   183: new java/lang/StringBuilder
    //   186: dup
    //   187: invokespecial <init> : ()V
    //   190: ldc_w ' Can't morph from '
    //   193: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: aload #13
    //   198: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: ldc_w ' to '
    //   204: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: aload #14
    //   209: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: invokevirtual toString : ()Ljava/lang/String;
    //   215: invokespecial <init> : (Ljava/lang/String;)V
    //   218: athrow
    //   219: iconst_0
    //   220: istore #8
    //   222: goto -> 15
    //   225: iconst_0
    //   226: istore #10
    //   228: goto -> 27
    //   231: iconst_0
    //   232: istore #9
    //   234: goto -> 42
    //   237: iconst_0
    //   238: istore #11
    //   240: goto -> 54
    //   243: iconst_0
    //   244: istore #7
    //   246: goto -> 91
    //   249: iconst_0
    //   250: istore_1
    //   251: goto -> 98
    //   254: aload #4
    //   256: aload_0
    //   257: iconst_2
    //   258: anewarray java/lang/Object
    //   261: dup
    //   262: iconst_0
    //   263: aload #15
    //   265: aastore
    //   266: dup
    //   267: iconst_1
    //   268: aload #16
    //   270: aastore
    //   271: invokestatic ofObject : (Ljava/lang/String;Landroid/animation/TypeEvaluator;[Ljava/lang/Object;)Landroid/animation/PropertyValuesHolder;
    //   274: astore_0
    //   275: aload_0
    //   276: areturn
    //   277: aload #4
    //   279: aload_0
    //   280: iconst_1
    //   281: anewarray java/lang/Object
    //   284: dup
    //   285: iconst_0
    //   286: aload #15
    //   288: aastore
    //   289: invokestatic ofObject : (Ljava/lang/String;Landroid/animation/TypeEvaluator;[Ljava/lang/Object;)Landroid/animation/PropertyValuesHolder;
    //   292: areturn
    //   293: aload #12
    //   295: astore_0
    //   296: aload #16
    //   298: ifnull -> 275
    //   301: aload #4
    //   303: new androidx/vectordrawable/graphics/drawable/AnimatorInflaterCompat$PathDataEvaluator
    //   306: dup
    //   307: invokespecial <init> : ()V
    //   310: iconst_1
    //   311: anewarray java/lang/Object
    //   314: dup
    //   315: iconst_0
    //   316: aload #16
    //   318: aastore
    //   319: invokestatic ofObject : (Ljava/lang/String;Landroid/animation/TypeEvaluator;[Ljava/lang/Object;)Landroid/animation/PropertyValuesHolder;
    //   322: areturn
    //   323: aconst_null
    //   324: astore #13
    //   326: iload #7
    //   328: iconst_3
    //   329: if_icmpne -> 337
    //   332: invokestatic getInstance : ()Landroidx/vectordrawable/graphics/drawable/ArgbEvaluator;
    //   335: astore #13
    //   337: iload_1
    //   338: ifeq -> 508
    //   341: iload #8
    //   343: ifeq -> 465
    //   346: iload #10
    //   348: iconst_5
    //   349: if_icmpne -> 425
    //   352: aload_0
    //   353: iload_2
    //   354: fconst_0
    //   355: invokevirtual getDimension : (IF)F
    //   358: fstore #5
    //   360: iload #9
    //   362: ifeq -> 447
    //   365: iload #11
    //   367: iconst_5
    //   368: if_icmpne -> 436
    //   371: aload_0
    //   372: iload_3
    //   373: fconst_0
    //   374: invokevirtual getDimension : (IF)F
    //   377: fstore #6
    //   379: aload #4
    //   381: iconst_2
    //   382: newarray float
    //   384: dup
    //   385: iconst_0
    //   386: fload #5
    //   388: fastore
    //   389: dup
    //   390: iconst_1
    //   391: fload #6
    //   393: fastore
    //   394: invokestatic ofFloat : (Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;
    //   397: astore #12
    //   399: aload #12
    //   401: astore_0
    //   402: aload #12
    //   404: ifnull -> 275
    //   407: aload #12
    //   409: astore_0
    //   410: aload #13
    //   412: ifnull -> 275
    //   415: aload #12
    //   417: aload #13
    //   419: invokevirtual setEvaluator : (Landroid/animation/TypeEvaluator;)V
    //   422: aload #12
    //   424: areturn
    //   425: aload_0
    //   426: iload_2
    //   427: fconst_0
    //   428: invokevirtual getFloat : (IF)F
    //   431: fstore #5
    //   433: goto -> 360
    //   436: aload_0
    //   437: iload_3
    //   438: fconst_0
    //   439: invokevirtual getFloat : (IF)F
    //   442: fstore #6
    //   444: goto -> 379
    //   447: aload #4
    //   449: iconst_1
    //   450: newarray float
    //   452: dup
    //   453: iconst_0
    //   454: fload #5
    //   456: fastore
    //   457: invokestatic ofFloat : (Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;
    //   460: astore #12
    //   462: goto -> 399
    //   465: iload #11
    //   467: iconst_5
    //   468: if_icmpne -> 497
    //   471: aload_0
    //   472: iload_3
    //   473: fconst_0
    //   474: invokevirtual getDimension : (IF)F
    //   477: fstore #5
    //   479: aload #4
    //   481: iconst_1
    //   482: newarray float
    //   484: dup
    //   485: iconst_0
    //   486: fload #5
    //   488: fastore
    //   489: invokestatic ofFloat : (Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;
    //   492: astore #12
    //   494: goto -> 399
    //   497: aload_0
    //   498: iload_3
    //   499: fconst_0
    //   500: invokevirtual getFloat : (IF)F
    //   503: fstore #5
    //   505: goto -> 479
    //   508: iload #8
    //   510: ifeq -> 640
    //   513: iload #10
    //   515: iconst_5
    //   516: if_icmpne -> 567
    //   519: aload_0
    //   520: iload_2
    //   521: fconst_0
    //   522: invokevirtual getDimension : (IF)F
    //   525: f2i
    //   526: istore_1
    //   527: iload #9
    //   529: ifeq -> 623
    //   532: iload #11
    //   534: iconst_5
    //   535: if_icmpne -> 595
    //   538: aload_0
    //   539: iload_3
    //   540: fconst_0
    //   541: invokevirtual getDimension : (IF)F
    //   544: f2i
    //   545: istore_2
    //   546: aload #4
    //   548: iconst_2
    //   549: newarray int
    //   551: dup
    //   552: iconst_0
    //   553: iload_1
    //   554: iastore
    //   555: dup
    //   556: iconst_1
    //   557: iload_2
    //   558: iastore
    //   559: invokestatic ofInt : (Ljava/lang/String;[I)Landroid/animation/PropertyValuesHolder;
    //   562: astore #12
    //   564: goto -> 399
    //   567: iload #10
    //   569: invokestatic isColorType : (I)Z
    //   572: ifeq -> 585
    //   575: aload_0
    //   576: iload_2
    //   577: iconst_0
    //   578: invokevirtual getColor : (II)I
    //   581: istore_1
    //   582: goto -> 527
    //   585: aload_0
    //   586: iload_2
    //   587: iconst_0
    //   588: invokevirtual getInt : (II)I
    //   591: istore_1
    //   592: goto -> 527
    //   595: iload #11
    //   597: invokestatic isColorType : (I)Z
    //   600: ifeq -> 613
    //   603: aload_0
    //   604: iload_3
    //   605: iconst_0
    //   606: invokevirtual getColor : (II)I
    //   609: istore_2
    //   610: goto -> 546
    //   613: aload_0
    //   614: iload_3
    //   615: iconst_0
    //   616: invokevirtual getInt : (II)I
    //   619: istore_2
    //   620: goto -> 546
    //   623: aload #4
    //   625: iconst_1
    //   626: newarray int
    //   628: dup
    //   629: iconst_0
    //   630: iload_1
    //   631: iastore
    //   632: invokestatic ofInt : (Ljava/lang/String;[I)Landroid/animation/PropertyValuesHolder;
    //   635: astore #12
    //   637: goto -> 399
    //   640: aload #14
    //   642: astore #12
    //   644: iload #9
    //   646: ifeq -> 399
    //   649: iload #11
    //   651: iconst_5
    //   652: if_icmpne -> 680
    //   655: aload_0
    //   656: iload_3
    //   657: fconst_0
    //   658: invokevirtual getDimension : (IF)F
    //   661: f2i
    //   662: istore_1
    //   663: aload #4
    //   665: iconst_1
    //   666: newarray int
    //   668: dup
    //   669: iconst_0
    //   670: iload_1
    //   671: iastore
    //   672: invokestatic ofInt : (Ljava/lang/String;[I)Landroid/animation/PropertyValuesHolder;
    //   675: astore #12
    //   677: goto -> 399
    //   680: iload #11
    //   682: invokestatic isColorType : (I)Z
    //   685: ifeq -> 698
    //   688: aload_0
    //   689: iload_3
    //   690: iconst_0
    //   691: invokevirtual getColor : (II)I
    //   694: istore_1
    //   695: goto -> 663
    //   698: aload_0
    //   699: iload_3
    //   700: iconst_0
    //   701: invokevirtual getInt : (II)I
    //   704: istore_1
    //   705: goto -> 663
  }
  
  private static int inferValueTypeFromValues(TypedArray paramTypedArray, int paramInt1, int paramInt2) {
    boolean bool;
    int i = 1;
    TypedValue typedValue2 = paramTypedArray.peekValue(paramInt1);
    if (typedValue2 != null) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    } 
    if (paramInt1 != 0) {
      bool = typedValue2.type;
    } else {
      bool = false;
    } 
    TypedValue typedValue1 = paramTypedArray.peekValue(paramInt2);
    if (typedValue1 != null) {
      paramInt2 = i;
    } else {
      paramInt2 = 0;
    } 
    if (paramInt2 != 0) {
      i = typedValue1.type;
    } else {
      i = 0;
    } 
    return ((paramInt1 != 0 && isColorType(bool)) || (paramInt2 != 0 && isColorType(i))) ? 3 : 0;
  }
  
  private static int inferValueTypeOfKeyframe(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, XmlPullParser paramXmlPullParser) {
    byte b = 0;
    TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_KEYFRAME);
    TypedValue typedValue = TypedArrayUtils.peekNamedValue(typedArray, paramXmlPullParser, "value", 0);
    if (typedValue != null)
      b = 1; 
    if (b && isColorType(typedValue.type)) {
      b = 3;
      typedArray.recycle();
      return b;
    } 
    b = 0;
    typedArray.recycle();
    return b;
  }
  
  private static boolean isColorType(int paramInt) {
    return (paramInt >= 28 && paramInt <= 31);
  }
  
  public static Animator loadAnimator(Context paramContext, @AnimatorRes int paramInt) throws Resources.NotFoundException {
    return (Build.VERSION.SDK_INT >= 24) ? AnimatorInflater.loadAnimator(paramContext, paramInt) : loadAnimator(paramContext, paramContext.getResources(), paramContext.getTheme(), paramInt);
  }
  
  public static Animator loadAnimator(Context paramContext, Resources paramResources, Resources.Theme paramTheme, @AnimatorRes int paramInt) throws Resources.NotFoundException {
    return loadAnimator(paramContext, paramResources, paramTheme, paramInt, 1.0F);
  }
  
  public static Animator loadAnimator(Context paramContext, Resources paramResources, Resources.Theme paramTheme, @AnimatorRes int paramInt, float paramFloat) throws Resources.NotFoundException {
    XmlResourceParser xmlResourceParser1 = null;
    XmlResourceParser xmlResourceParser3 = null;
    XmlResourceParser xmlResourceParser2 = null;
    try {
      XmlResourceParser xmlResourceParser = paramResources.getAnimation(paramInt);
      xmlResourceParser2 = xmlResourceParser;
      xmlResourceParser1 = xmlResourceParser;
      xmlResourceParser3 = xmlResourceParser;
      return createAnimatorFromXml(paramContext, paramResources, paramTheme, (XmlPullParser)xmlResourceParser, paramFloat);
    } catch (XmlPullParserException xmlPullParserException) {
      xmlResourceParser1 = xmlResourceParser2;
      Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(paramInt));
      xmlResourceParser1 = xmlResourceParser2;
      notFoundException.initCause((Throwable)xmlPullParserException);
      xmlResourceParser1 = xmlResourceParser2;
      throw notFoundException;
    } catch (IOException iOException) {
      xmlResourceParser1 = xmlResourceParser3;
      Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(paramInt));
      xmlResourceParser1 = xmlResourceParser3;
      notFoundException.initCause(iOException);
      xmlResourceParser1 = xmlResourceParser3;
      throw notFoundException;
    } finally {
      if (xmlResourceParser1 != null)
        xmlResourceParser1.close(); 
    } 
  }
  
  private static ValueAnimator loadAnimator(Context paramContext, Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, ValueAnimator paramValueAnimator, float paramFloat, XmlPullParser paramXmlPullParser) throws Resources.NotFoundException {
    TypedArray typedArray2 = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_ANIMATOR);
    TypedArray typedArray1 = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
    ValueAnimator valueAnimator = paramValueAnimator;
    if (paramValueAnimator == null)
      valueAnimator = new ValueAnimator(); 
    parseAnimatorFromTypeArray(valueAnimator, typedArray2, typedArray1, paramFloat, paramXmlPullParser);
    int i = TypedArrayUtils.getNamedResourceId(typedArray2, paramXmlPullParser, "interpolator", 0, 0);
    if (i > 0)
      valueAnimator.setInterpolator((TimeInterpolator)AnimationUtilsCompat.loadInterpolator(paramContext, i)); 
    typedArray2.recycle();
    if (typedArray1 != null)
      typedArray1.recycle(); 
    return valueAnimator;
  }
  
  private static Keyframe loadKeyframe(Context paramContext, Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, int paramInt, XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    boolean bool;
    TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_KEYFRAME);
    paramTheme = null;
    float f = TypedArrayUtils.getNamedFloat(typedArray, paramXmlPullParser, "fraction", 3, -1.0F);
    TypedValue typedValue = TypedArrayUtils.peekNamedValue(typedArray, paramXmlPullParser, "value", 0);
    if (typedValue != null) {
      bool = true;
    } else {
      bool = false;
    } 
    int i = paramInt;
    if (paramInt == 4)
      if (bool && isColorType(typedValue.type)) {
        i = 3;
      } else {
        i = 0;
      }  
    if (bool) {
      Resources.Theme theme = paramTheme;
      switch (i) {
        default:
          theme = paramTheme;
        case 2:
          paramInt = TypedArrayUtils.getNamedResourceId(typedArray, paramXmlPullParser, "interpolator", 1, 0);
          if (paramInt > 0)
            theme.setInterpolator((TimeInterpolator)AnimationUtilsCompat.loadInterpolator(paramContext, paramInt)); 
          typedArray.recycle();
          return (Keyframe)theme;
        case 0:
          keyframe1 = Keyframe.ofFloat(f, TypedArrayUtils.getNamedFloat(typedArray, paramXmlPullParser, "value", 0, 0.0F));
        case 1:
        case 3:
          break;
      } 
      Keyframe keyframe1 = Keyframe.ofInt(f, TypedArrayUtils.getNamedInt(typedArray, paramXmlPullParser, "value", 0, 0));
    } 
    if (i == 0)
      Keyframe keyframe1 = Keyframe.ofFloat(f); 
    Keyframe keyframe = Keyframe.ofInt(f);
  }
  
  private static ObjectAnimator loadObjectAnimator(Context paramContext, Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, float paramFloat, XmlPullParser paramXmlPullParser) throws Resources.NotFoundException {
    ObjectAnimator objectAnimator = new ObjectAnimator();
    loadAnimator(paramContext, paramResources, paramTheme, paramAttributeSet, (ValueAnimator)objectAnimator, paramFloat, paramXmlPullParser);
    return objectAnimator;
  }
  
  private static PropertyValuesHolder loadPvh(Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, String paramString, int paramInt) throws XmlPullParserException, IOException {
    PropertyValuesHolder propertyValuesHolder;
    Context context = null;
    ArrayList<Keyframe> arrayList = null;
    int i = paramInt;
    while (true) {
      paramInt = paramXmlPullParser.next();
      if (paramInt != 3 && paramInt != 1) {
        if (paramXmlPullParser.getName().equals("keyframe")) {
          paramInt = i;
          if (i == 4)
            paramInt = inferValueTypeOfKeyframe(paramResources, paramTheme, Xml.asAttributeSet(paramXmlPullParser), paramXmlPullParser); 
          Keyframe keyframe = loadKeyframe(paramContext, paramResources, paramTheme, Xml.asAttributeSet(paramXmlPullParser), paramInt, paramXmlPullParser);
          ArrayList<Keyframe> arrayList1 = arrayList;
          if (keyframe != null) {
            arrayList1 = arrayList;
            if (arrayList == null)
              arrayList1 = new ArrayList(); 
            arrayList1.add(keyframe);
          } 
          paramXmlPullParser.next();
          arrayList = arrayList1;
          i = paramInt;
        } 
        continue;
      } 
      break;
    } 
    paramContext = context;
    if (arrayList != null) {
      int j = arrayList.size();
      paramContext = context;
      if (j > 0) {
        Keyframe keyframe1 = arrayList.get(0);
        Keyframe keyframe2 = arrayList.get(j - 1);
        float f = keyframe2.getFraction();
        paramInt = j;
        if (f < 1.0F)
          if (f < 0.0F) {
            keyframe2.setFraction(1.0F);
            paramInt = j;
          } else {
            arrayList.add(arrayList.size(), createNewKeyframe(keyframe2, 1.0F));
            paramInt = j + 1;
          }  
        f = keyframe1.getFraction();
        int k = paramInt;
        if (f != 0.0F)
          if (f < 0.0F) {
            keyframe1.setFraction(0.0F);
            k = paramInt;
          } else {
            arrayList.add(0, createNewKeyframe(keyframe1, 0.0F));
            k = paramInt + 1;
          }  
        Keyframe[] arrayOfKeyframe = new Keyframe[k];
        arrayList.toArray(arrayOfKeyframe);
        paramInt = 0;
        label57: while (paramInt < k) {
          keyframe2 = arrayOfKeyframe[paramInt];
          if (keyframe2.getFraction() < 0.0F) {
            if (paramInt == 0) {
              keyframe2.setFraction(0.0F);
              continue;
            } 
            if (paramInt == k - 1) {
              keyframe2.setFraction(1.0F);
              continue;
            } 
            int m = paramInt;
            for (j = paramInt + 1;; j++) {
              if (j >= k - 1 || arrayOfKeyframe[j].getFraction() >= 0.0F) {
                distributeKeyframes(arrayOfKeyframe, arrayOfKeyframe[m + 1].getFraction() - arrayOfKeyframe[paramInt - 1].getFraction(), paramInt, m);
                paramInt++;
                continue label57;
              } 
              m = j;
            } 
          } 
          continue;
        } 
        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofKeyframe(paramString, arrayOfKeyframe);
        propertyValuesHolder = propertyValuesHolder1;
        if (i == 3) {
          propertyValuesHolder1.setEvaluator(ArgbEvaluator.getInstance());
          propertyValuesHolder = propertyValuesHolder1;
        } 
      } 
    } 
    return propertyValuesHolder;
  }
  
  private static PropertyValuesHolder[] loadValues(Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) throws XmlPullParserException, IOException {
    PropertyValuesHolder[] arrayOfPropertyValuesHolder;
    ArrayList<PropertyValuesHolder> arrayList;
    PropertyValuesHolder propertyValuesHolder = null;
    while (true) {
      int i = paramXmlPullParser.getEventType();
      if (i != 3 && i != 1) {
        ArrayList<PropertyValuesHolder> arrayList1;
        if (i != 2) {
          paramXmlPullParser.next();
          continue;
        } 
        PropertyValuesHolder propertyValuesHolder1 = propertyValuesHolder;
        if (paramXmlPullParser.getName().equals("propertyValuesHolder")) {
          TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
          String str = TypedArrayUtils.getNamedString(typedArray, paramXmlPullParser, "propertyName", 3);
          i = TypedArrayUtils.getNamedInt(typedArray, paramXmlPullParser, "valueType", 2, 4);
          propertyValuesHolder1 = loadPvh(paramContext, paramResources, paramTheme, paramXmlPullParser, str, i);
          PropertyValuesHolder propertyValuesHolder2 = propertyValuesHolder1;
          if (propertyValuesHolder1 == null)
            propertyValuesHolder2 = getPVH(typedArray, i, 0, 1, str); 
          propertyValuesHolder1 = propertyValuesHolder;
          if (propertyValuesHolder2 != null) {
            propertyValuesHolder1 = propertyValuesHolder;
            if (propertyValuesHolder == null)
              arrayList1 = new ArrayList(); 
            arrayList1.add(propertyValuesHolder2);
          } 
          typedArray.recycle();
        } 
        paramXmlPullParser.next();
        arrayList = arrayList1;
        continue;
      } 
      break;
    } 
    paramContext = null;
    if (arrayList != null) {
      int j = arrayList.size();
      PropertyValuesHolder[] arrayOfPropertyValuesHolder1 = new PropertyValuesHolder[j];
      int i = 0;
      while (true) {
        arrayOfPropertyValuesHolder = arrayOfPropertyValuesHolder1;
        if (i < j) {
          arrayOfPropertyValuesHolder1[i] = arrayList.get(i);
          i++;
          continue;
        } 
        break;
      } 
    } 
    return arrayOfPropertyValuesHolder;
  }
  
  private static void parseAnimatorFromTypeArray(ValueAnimator paramValueAnimator, TypedArray paramTypedArray1, TypedArray paramTypedArray2, float paramFloat, XmlPullParser paramXmlPullParser) {
    long l1 = TypedArrayUtils.getNamedInt(paramTypedArray1, paramXmlPullParser, "duration", 1, 300);
    long l2 = TypedArrayUtils.getNamedInt(paramTypedArray1, paramXmlPullParser, "startOffset", 2, 0);
    int i = TypedArrayUtils.getNamedInt(paramTypedArray1, paramXmlPullParser, "valueType", 7, 4);
    int j = i;
    if (TypedArrayUtils.hasAttribute(paramXmlPullParser, "valueFrom")) {
      j = i;
      if (TypedArrayUtils.hasAttribute(paramXmlPullParser, "valueTo")) {
        int k = i;
        if (i == 4)
          k = inferValueTypeFromValues(paramTypedArray1, 5, 6); 
        PropertyValuesHolder propertyValuesHolder = getPVH(paramTypedArray1, k, 5, 6, "");
        j = k;
        if (propertyValuesHolder != null) {
          paramValueAnimator.setValues(new PropertyValuesHolder[] { propertyValuesHolder });
          j = k;
        } 
      } 
    } 
    paramValueAnimator.setDuration(l1);
    paramValueAnimator.setStartDelay(l2);
    paramValueAnimator.setRepeatCount(TypedArrayUtils.getNamedInt(paramTypedArray1, paramXmlPullParser, "repeatCount", 3, 0));
    paramValueAnimator.setRepeatMode(TypedArrayUtils.getNamedInt(paramTypedArray1, paramXmlPullParser, "repeatMode", 4, 1));
    if (paramTypedArray2 != null)
      setupObjectAnimator(paramValueAnimator, paramTypedArray2, j, paramFloat, paramXmlPullParser); 
  }
  
  private static void setupObjectAnimator(ValueAnimator paramValueAnimator, TypedArray paramTypedArray, int paramInt, float paramFloat, XmlPullParser paramXmlPullParser) {
    // Byte code:
    //   0: aload_0
    //   1: checkcast android/animation/ObjectAnimator
    //   4: astore_0
    //   5: aload_1
    //   6: aload #4
    //   8: ldc_w 'pathData'
    //   11: iconst_1
    //   12: invokestatic getNamedString : (Landroid/content/res/TypedArray;Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)Ljava/lang/String;
    //   15: astore #5
    //   17: aload #5
    //   19: ifnull -> 116
    //   22: aload_1
    //   23: aload #4
    //   25: ldc_w 'propertyXName'
    //   28: iconst_2
    //   29: invokestatic getNamedString : (Landroid/content/res/TypedArray;Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)Ljava/lang/String;
    //   32: astore #6
    //   34: aload_1
    //   35: aload #4
    //   37: ldc_w 'propertyYName'
    //   40: iconst_3
    //   41: invokestatic getNamedString : (Landroid/content/res/TypedArray;Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)Ljava/lang/String;
    //   44: astore #4
    //   46: iload_2
    //   47: iconst_2
    //   48: if_icmpeq -> 56
    //   51: iload_2
    //   52: iconst_4
    //   53: if_icmpne -> 56
    //   56: aload #6
    //   58: ifnonnull -> 97
    //   61: aload #4
    //   63: ifnonnull -> 97
    //   66: new android/view/InflateException
    //   69: dup
    //   70: new java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial <init> : ()V
    //   77: aload_1
    //   78: invokevirtual getPositionDescription : ()Ljava/lang/String;
    //   81: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: ldc_w ' propertyXName or propertyYName is needed for PathData'
    //   87: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: invokevirtual toString : ()Ljava/lang/String;
    //   93: invokespecial <init> : (Ljava/lang/String;)V
    //   96: athrow
    //   97: aload #5
    //   99: invokestatic createPathFromPathData : (Ljava/lang/String;)Landroid/graphics/Path;
    //   102: aload_0
    //   103: ldc_w 0.5
    //   106: fload_3
    //   107: fmul
    //   108: aload #6
    //   110: aload #4
    //   112: invokestatic setupPathMotion : (Landroid/graphics/Path;Landroid/animation/ObjectAnimator;FLjava/lang/String;Ljava/lang/String;)V
    //   115: return
    //   116: aload_0
    //   117: aload_1
    //   118: aload #4
    //   120: ldc_w 'propertyName'
    //   123: iconst_0
    //   124: invokestatic getNamedString : (Landroid/content/res/TypedArray;Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)Ljava/lang/String;
    //   127: invokevirtual setPropertyName : (Ljava/lang/String;)V
    //   130: return
  }
  
  private static void setupPathMotion(Path paramPath, ObjectAnimator paramObjectAnimator, float paramFloat, String paramString1, String paramString2) {
    PathMeasure pathMeasure = new PathMeasure(paramPath, false);
    float f = 0.0F;
    ArrayList<Float> arrayList = new ArrayList();
    arrayList.add(Float.valueOf(0.0F));
    while (true) {
      float f1 = f + pathMeasure.getLength();
      arrayList.add(Float.valueOf(f1));
      f = f1;
      if (!pathMeasure.nextContour()) {
        PropertyValuesHolder propertyValuesHolder1;
        PropertyValuesHolder propertyValuesHolder2;
        PathMeasure pathMeasure1 = new PathMeasure(paramPath, false);
        int k = Math.min(100, (int)(f1 / paramFloat) + 1);
        float[] arrayOfFloat2 = new float[k];
        float[] arrayOfFloat1 = new float[k];
        float[] arrayOfFloat3 = new float[2];
        int j = 0;
        f = f1 / (k - 1);
        paramFloat = 0.0F;
        int i = 0;
        while (i < k) {
          pathMeasure1.getPosTan(paramFloat - ((Float)arrayList.get(j)).floatValue(), arrayOfFloat3, null);
          arrayOfFloat2[i] = arrayOfFloat3[0];
          arrayOfFloat1[i] = arrayOfFloat3[1];
          paramFloat += f;
          int m = j;
          if (j + 1 < arrayList.size()) {
            m = j;
            if (paramFloat > ((Float)arrayList.get(j + 1)).floatValue()) {
              m = j + 1;
              pathMeasure1.nextContour();
            } 
          } 
          i++;
          j = m;
        } 
        pathMeasure1 = null;
        arrayList = null;
        if (paramString1 != null)
          propertyValuesHolder1 = PropertyValuesHolder.ofFloat(paramString1, arrayOfFloat2); 
        ArrayList<Float> arrayList1 = arrayList;
        if (paramString2 != null)
          propertyValuesHolder2 = PropertyValuesHolder.ofFloat(paramString2, arrayOfFloat1); 
        if (propertyValuesHolder1 == null) {
          paramObjectAnimator.setValues(new PropertyValuesHolder[] { propertyValuesHolder2 });
          return;
        } 
        if (propertyValuesHolder2 == null) {
          paramObjectAnimator.setValues(new PropertyValuesHolder[] { propertyValuesHolder1 });
          return;
        } 
        paramObjectAnimator.setValues(new PropertyValuesHolder[] { propertyValuesHolder1, propertyValuesHolder2 });
        return;
      } 
    } 
  }
  
  private static class PathDataEvaluator implements TypeEvaluator<PathParser.PathDataNode[]> {
    private PathParser.PathDataNode[] mNodeArray;
    
    PathDataEvaluator() {}
    
    PathDataEvaluator(PathParser.PathDataNode[] param1ArrayOfPathDataNode) {
      this.mNodeArray = param1ArrayOfPathDataNode;
    }
    
    public PathParser.PathDataNode[] evaluate(float param1Float, PathParser.PathDataNode[] param1ArrayOfPathDataNode1, PathParser.PathDataNode[] param1ArrayOfPathDataNode2) {
      if (!PathParser.canMorph(param1ArrayOfPathDataNode1, param1ArrayOfPathDataNode2))
        throw new IllegalArgumentException("Can't interpolate between two incompatible pathData"); 
      if (this.mNodeArray == null || !PathParser.canMorph(this.mNodeArray, param1ArrayOfPathDataNode1))
        this.mNodeArray = PathParser.deepCopyNodes(param1ArrayOfPathDataNode1); 
      int i;
      for (i = 0; i < param1ArrayOfPathDataNode1.length; i++)
        this.mNodeArray[i].interpolatePathDataNode(param1ArrayOfPathDataNode1[i], param1ArrayOfPathDataNode2[i], param1Float); 
      return this.mNodeArray;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/vectordrawable/graphics/drawable/AnimatorInflaterCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
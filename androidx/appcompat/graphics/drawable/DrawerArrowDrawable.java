package androidx.appcompat.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DrawerArrowDrawable extends Drawable {
  public static final int ARROW_DIRECTION_END = 3;
  
  public static final int ARROW_DIRECTION_LEFT = 0;
  
  public static final int ARROW_DIRECTION_RIGHT = 1;
  
  public static final int ARROW_DIRECTION_START = 2;
  
  private static final float ARROW_HEAD_ANGLE = (float)Math.toRadians(45.0D);
  
  private float mArrowHeadLength;
  
  private float mArrowShaftLength;
  
  private float mBarGap;
  
  private float mBarLength;
  
  private int mDirection = 2;
  
  private float mMaxCutForBarSize;
  
  private final Paint mPaint = new Paint();
  
  private final Path mPath = new Path();
  
  private float mProgress;
  
  private final int mSize;
  
  private boolean mSpin;
  
  private boolean mVerticalMirror = false;
  
  public DrawerArrowDrawable(Context paramContext) {
    this.mPaint.setStyle(Paint.Style.STROKE);
    this.mPaint.setStrokeJoin(Paint.Join.MITER);
    this.mPaint.setStrokeCap(Paint.Cap.BUTT);
    this.mPaint.setAntiAlias(true);
    TypedArray typedArray = paramContext.getTheme().obtainStyledAttributes(null, R.styleable.DrawerArrowToggle, R.attr.drawerArrowStyle, R.style.Base_Widget_AppCompat_DrawerArrowToggle);
    setColor(typedArray.getColor(R.styleable.DrawerArrowToggle_color, 0));
    setBarThickness(typedArray.getDimension(R.styleable.DrawerArrowToggle_thickness, 0.0F));
    setSpinEnabled(typedArray.getBoolean(R.styleable.DrawerArrowToggle_spinBars, true));
    setGapSize(Math.round(typedArray.getDimension(R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0F)));
    this.mSize = typedArray.getDimensionPixelSize(R.styleable.DrawerArrowToggle_drawableSize, 0);
    this.mBarLength = Math.round(typedArray.getDimension(R.styleable.DrawerArrowToggle_barLength, 0.0F));
    this.mArrowHeadLength = Math.round(typedArray.getDimension(R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0F));
    this.mArrowShaftLength = typedArray.getDimension(R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0F);
    typedArray.recycle();
  }
  
  private static float lerp(float paramFloat1, float paramFloat2, float paramFloat3) {
    return (paramFloat2 - paramFloat1) * paramFloat3 + paramFloat1;
  }
  
  public void draw(Canvas paramCanvas) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getBounds : ()Landroid/graphics/Rect;
    //   4: astore #10
    //   6: aload_0
    //   7: getfield mDirection : I
    //   10: tableswitch default -> 40, 0 -> 418, 1 -> 424, 2 -> 40, 3 -> 430
    //   40: aload_0
    //   41: invokestatic getLayoutDirection : (Landroid/graphics/drawable/Drawable;)I
    //   44: iconst_1
    //   45: if_icmpne -> 449
    //   48: iconst_1
    //   49: istore #9
    //   51: aload_0
    //   52: getfield mArrowHeadLength : F
    //   55: aload_0
    //   56: getfield mArrowHeadLength : F
    //   59: fmul
    //   60: fconst_2
    //   61: fmul
    //   62: f2d
    //   63: invokestatic sqrt : (D)D
    //   66: d2f
    //   67: fstore_2
    //   68: aload_0
    //   69: getfield mBarLength : F
    //   72: fload_2
    //   73: aload_0
    //   74: getfield mProgress : F
    //   77: invokestatic lerp : (FFF)F
    //   80: fstore #6
    //   82: aload_0
    //   83: getfield mBarLength : F
    //   86: aload_0
    //   87: getfield mArrowShaftLength : F
    //   90: aload_0
    //   91: getfield mProgress : F
    //   94: invokestatic lerp : (FFF)F
    //   97: fstore #4
    //   99: fconst_0
    //   100: aload_0
    //   101: getfield mMaxCutForBarSize : F
    //   104: aload_0
    //   105: getfield mProgress : F
    //   108: invokestatic lerp : (FFF)F
    //   111: invokestatic round : (F)I
    //   114: i2f
    //   115: fstore #5
    //   117: fconst_0
    //   118: getstatic androidx/appcompat/graphics/drawable/DrawerArrowDrawable.ARROW_HEAD_ANGLE : F
    //   121: aload_0
    //   122: getfield mProgress : F
    //   125: invokestatic lerp : (FFF)F
    //   128: fstore #7
    //   130: iload #9
    //   132: ifeq -> 455
    //   135: fconst_0
    //   136: fstore_2
    //   137: iload #9
    //   139: ifeq -> 462
    //   142: ldc 180.0
    //   144: fstore_3
    //   145: fload_2
    //   146: fload_3
    //   147: aload_0
    //   148: getfield mProgress : F
    //   151: invokestatic lerp : (FFF)F
    //   154: fstore_2
    //   155: fload #6
    //   157: f2d
    //   158: fload #7
    //   160: f2d
    //   161: invokestatic cos : (D)D
    //   164: dmul
    //   165: invokestatic round : (D)J
    //   168: l2f
    //   169: fstore_3
    //   170: fload #6
    //   172: f2d
    //   173: fload #7
    //   175: f2d
    //   176: invokestatic sin : (D)D
    //   179: dmul
    //   180: invokestatic round : (D)J
    //   183: l2f
    //   184: fstore #6
    //   186: aload_0
    //   187: getfield mPath : Landroid/graphics/Path;
    //   190: invokevirtual rewind : ()V
    //   193: aload_0
    //   194: getfield mBarGap : F
    //   197: aload_0
    //   198: getfield mPaint : Landroid/graphics/Paint;
    //   201: invokevirtual getStrokeWidth : ()F
    //   204: fadd
    //   205: aload_0
    //   206: getfield mMaxCutForBarSize : F
    //   209: fneg
    //   210: aload_0
    //   211: getfield mProgress : F
    //   214: invokestatic lerp : (FFF)F
    //   217: fstore #7
    //   219: fload #4
    //   221: fneg
    //   222: fconst_2
    //   223: fdiv
    //   224: fstore #8
    //   226: aload_0
    //   227: getfield mPath : Landroid/graphics/Path;
    //   230: fload #8
    //   232: fload #5
    //   234: fadd
    //   235: fconst_0
    //   236: invokevirtual moveTo : (FF)V
    //   239: aload_0
    //   240: getfield mPath : Landroid/graphics/Path;
    //   243: fload #4
    //   245: fconst_2
    //   246: fload #5
    //   248: fmul
    //   249: fsub
    //   250: fconst_0
    //   251: invokevirtual rLineTo : (FF)V
    //   254: aload_0
    //   255: getfield mPath : Landroid/graphics/Path;
    //   258: fload #8
    //   260: fload #7
    //   262: invokevirtual moveTo : (FF)V
    //   265: aload_0
    //   266: getfield mPath : Landroid/graphics/Path;
    //   269: fload_3
    //   270: fload #6
    //   272: invokevirtual rLineTo : (FF)V
    //   275: aload_0
    //   276: getfield mPath : Landroid/graphics/Path;
    //   279: fload #8
    //   281: fload #7
    //   283: fneg
    //   284: invokevirtual moveTo : (FF)V
    //   287: aload_0
    //   288: getfield mPath : Landroid/graphics/Path;
    //   291: fload_3
    //   292: fload #6
    //   294: fneg
    //   295: invokevirtual rLineTo : (FF)V
    //   298: aload_0
    //   299: getfield mPath : Landroid/graphics/Path;
    //   302: invokevirtual close : ()V
    //   305: aload_1
    //   306: invokevirtual save : ()I
    //   309: pop
    //   310: aload_0
    //   311: getfield mPaint : Landroid/graphics/Paint;
    //   314: invokevirtual getStrokeWidth : ()F
    //   317: fstore_3
    //   318: aload #10
    //   320: invokevirtual height : ()I
    //   323: i2f
    //   324: ldc_w 3.0
    //   327: fload_3
    //   328: fmul
    //   329: fsub
    //   330: aload_0
    //   331: getfield mBarGap : F
    //   334: fconst_2
    //   335: fmul
    //   336: fsub
    //   337: f2i
    //   338: iconst_4
    //   339: idiv
    //   340: iconst_2
    //   341: imul
    //   342: i2f
    //   343: fstore #4
    //   345: aload_0
    //   346: getfield mBarGap : F
    //   349: fstore #5
    //   351: aload_1
    //   352: aload #10
    //   354: invokevirtual centerX : ()I
    //   357: i2f
    //   358: fload #4
    //   360: ldc_w 1.5
    //   363: fload_3
    //   364: fmul
    //   365: fload #5
    //   367: fadd
    //   368: fadd
    //   369: invokevirtual translate : (FF)V
    //   372: aload_0
    //   373: getfield mSpin : Z
    //   376: ifeq -> 473
    //   379: aload_0
    //   380: getfield mVerticalMirror : Z
    //   383: iload #9
    //   385: ixor
    //   386: ifeq -> 467
    //   389: iconst_m1
    //   390: istore #9
    //   392: aload_1
    //   393: iload #9
    //   395: i2f
    //   396: fload_2
    //   397: fmul
    //   398: invokevirtual rotate : (F)V
    //   401: aload_1
    //   402: aload_0
    //   403: getfield mPath : Landroid/graphics/Path;
    //   406: aload_0
    //   407: getfield mPaint : Landroid/graphics/Paint;
    //   410: invokevirtual drawPath : (Landroid/graphics/Path;Landroid/graphics/Paint;)V
    //   413: aload_1
    //   414: invokevirtual restore : ()V
    //   417: return
    //   418: iconst_0
    //   419: istore #9
    //   421: goto -> 51
    //   424: iconst_1
    //   425: istore #9
    //   427: goto -> 51
    //   430: aload_0
    //   431: invokestatic getLayoutDirection : (Landroid/graphics/drawable/Drawable;)I
    //   434: ifne -> 443
    //   437: iconst_1
    //   438: istore #9
    //   440: goto -> 51
    //   443: iconst_0
    //   444: istore #9
    //   446: goto -> 440
    //   449: iconst_0
    //   450: istore #9
    //   452: goto -> 51
    //   455: ldc_w -180.0
    //   458: fstore_2
    //   459: goto -> 137
    //   462: fconst_0
    //   463: fstore_3
    //   464: goto -> 145
    //   467: iconst_1
    //   468: istore #9
    //   470: goto -> 392
    //   473: iload #9
    //   475: ifeq -> 401
    //   478: aload_1
    //   479: ldc 180.0
    //   481: invokevirtual rotate : (F)V
    //   484: goto -> 401
  }
  
  public float getArrowHeadLength() {
    return this.mArrowHeadLength;
  }
  
  public float getArrowShaftLength() {
    return this.mArrowShaftLength;
  }
  
  public float getBarLength() {
    return this.mBarLength;
  }
  
  public float getBarThickness() {
    return this.mPaint.getStrokeWidth();
  }
  
  @ColorInt
  public int getColor() {
    return this.mPaint.getColor();
  }
  
  public int getDirection() {
    return this.mDirection;
  }
  
  public float getGapSize() {
    return this.mBarGap;
  }
  
  public int getIntrinsicHeight() {
    return this.mSize;
  }
  
  public int getIntrinsicWidth() {
    return this.mSize;
  }
  
  public int getOpacity() {
    return -3;
  }
  
  public final Paint getPaint() {
    return this.mPaint;
  }
  
  @FloatRange(from = 0.0D, to = 1.0D)
  public float getProgress() {
    return this.mProgress;
  }
  
  public boolean isSpinEnabled() {
    return this.mSpin;
  }
  
  public void setAlpha(int paramInt) {
    if (paramInt != this.mPaint.getAlpha()) {
      this.mPaint.setAlpha(paramInt);
      invalidateSelf();
    } 
  }
  
  public void setArrowHeadLength(float paramFloat) {
    if (this.mArrowHeadLength != paramFloat) {
      this.mArrowHeadLength = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setArrowShaftLength(float paramFloat) {
    if (this.mArrowShaftLength != paramFloat) {
      this.mArrowShaftLength = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setBarLength(float paramFloat) {
    if (this.mBarLength != paramFloat) {
      this.mBarLength = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setBarThickness(float paramFloat) {
    if (this.mPaint.getStrokeWidth() != paramFloat) {
      this.mPaint.setStrokeWidth(paramFloat);
      this.mMaxCutForBarSize = (float)((paramFloat / 2.0F) * Math.cos(ARROW_HEAD_ANGLE));
      invalidateSelf();
    } 
  }
  
  public void setColor(@ColorInt int paramInt) {
    if (paramInt != this.mPaint.getColor()) {
      this.mPaint.setColor(paramInt);
      invalidateSelf();
    } 
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    this.mPaint.setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setDirection(int paramInt) {
    if (paramInt != this.mDirection) {
      this.mDirection = paramInt;
      invalidateSelf();
    } 
  }
  
  public void setGapSize(float paramFloat) {
    if (paramFloat != this.mBarGap) {
      this.mBarGap = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setProgress(@FloatRange(from = 0.0D, to = 1.0D) float paramFloat) {
    if (this.mProgress != paramFloat) {
      this.mProgress = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setSpinEnabled(boolean paramBoolean) {
    if (this.mSpin != paramBoolean) {
      this.mSpin = paramBoolean;
      invalidateSelf();
    } 
  }
  
  public void setVerticalMirror(boolean paramBoolean) {
    if (this.mVerticalMirror != paramBoolean) {
      this.mVerticalMirror = paramBoolean;
      invalidateSelf();
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface ArrowDirection {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/graphics/drawable/DrawerArrowDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
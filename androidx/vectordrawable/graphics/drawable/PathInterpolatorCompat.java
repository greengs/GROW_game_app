package androidx.vectordrawable.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class PathInterpolatorCompat implements Interpolator {
  public static final double EPSILON = 1.0E-5D;
  
  public static final int MAX_NUM_POINTS = 3000;
  
  private static final float PRECISION = 0.002F;
  
  private float[] mX;
  
  private float[] mY;
  
  public PathInterpolatorCompat(Context paramContext, AttributeSet paramAttributeSet, XmlPullParser paramXmlPullParser) {
    this(paramContext.getResources(), paramContext.getTheme(), paramAttributeSet, paramXmlPullParser);
  }
  
  public PathInterpolatorCompat(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, XmlPullParser paramXmlPullParser) {
    TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_PATH_INTERPOLATOR);
    parseInterpolatorFromTypeArray(typedArray, paramXmlPullParser);
    typedArray.recycle();
  }
  
  private void initCubic(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    Path path = new Path();
    path.moveTo(0.0F, 0.0F);
    path.cubicTo(paramFloat1, paramFloat2, paramFloat3, paramFloat4, 1.0F, 1.0F);
    initPath(path);
  }
  
  private void initPath(Path paramPath) {
    PathMeasure pathMeasure = new PathMeasure(paramPath, false);
    float f = pathMeasure.getLength();
    int k = Math.min(3000, (int)(f / 0.002F) + 1);
    if (k <= 0)
      throw new IllegalArgumentException("The Path has a invalid length " + f); 
    this.mX = new float[k];
    this.mY = new float[k];
    float[] arrayOfFloat = new float[2];
    int i;
    for (i = 0; i < k; i++) {
      pathMeasure.getPosTan(i * f / (k - 1), arrayOfFloat, null);
      this.mX[i] = arrayOfFloat[0];
      this.mY[i] = arrayOfFloat[1];
    } 
    if (Math.abs(this.mX[0]) > 1.0E-5D || Math.abs(this.mY[0]) > 1.0E-5D || Math.abs(this.mX[k - 1] - 1.0F) > 1.0E-5D || Math.abs(this.mY[k - 1] - 1.0F) > 1.0E-5D)
      throw new IllegalArgumentException("The Path must start at (0,0) and end at (1,1) start: " + this.mX[0] + "," + this.mY[0] + " end:" + this.mX[k - 1] + "," + this.mY[k - 1]); 
    f = 0.0F;
    int j = 0;
    for (i = 0; j < k; i++) {
      float f1 = this.mX[i];
      if (f1 < f)
        throw new IllegalArgumentException("The Path cannot loop back on itself, x :" + f1); 
      this.mX[j] = f1;
      f = f1;
      j++;
    } 
    if (pathMeasure.nextContour())
      throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours"); 
  }
  
  private void initQuad(float paramFloat1, float paramFloat2) {
    Path path = new Path();
    path.moveTo(0.0F, 0.0F);
    path.quadTo(paramFloat1, paramFloat2, 1.0F, 1.0F);
    initPath(path);
  }
  
  private void parseInterpolatorFromTypeArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser) {
    String str;
    Path path;
    if (TypedArrayUtils.hasAttribute(paramXmlPullParser, "pathData")) {
      str = TypedArrayUtils.getNamedString(paramTypedArray, paramXmlPullParser, "pathData", 4);
      path = PathParser.createPathFromPathData(str);
      if (path == null)
        throw new InflateException("The path is null, which is created from " + str); 
      initPath(path);
      return;
    } 
    if (!TypedArrayUtils.hasAttribute((XmlPullParser)path, "controlX1"))
      throw new InflateException("pathInterpolator requires the controlX1 attribute"); 
    if (!TypedArrayUtils.hasAttribute((XmlPullParser)path, "controlY1"))
      throw new InflateException("pathInterpolator requires the controlY1 attribute"); 
    float f1 = TypedArrayUtils.getNamedFloat((TypedArray)str, (XmlPullParser)path, "controlX1", 0, 0.0F);
    float f2 = TypedArrayUtils.getNamedFloat((TypedArray)str, (XmlPullParser)path, "controlY1", 1, 0.0F);
    boolean bool = TypedArrayUtils.hasAttribute((XmlPullParser)path, "controlX2");
    if (bool != TypedArrayUtils.hasAttribute((XmlPullParser)path, "controlY2"))
      throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers."); 
    if (!bool) {
      initQuad(f1, f2);
      return;
    } 
    initCubic(f1, f2, TypedArrayUtils.getNamedFloat((TypedArray)str, (XmlPullParser)path, "controlX2", 2, 0.0F), TypedArrayUtils.getNamedFloat((TypedArray)str, (XmlPullParser)path, "controlY2", 3, 0.0F));
  }
  
  public float getInterpolation(float paramFloat) {
    if (paramFloat <= 0.0F)
      return 0.0F; 
    if (paramFloat >= 1.0F)
      return 1.0F; 
    int i = 0;
    int j = this.mX.length - 1;
    while (j - i > 1) {
      int k = (i + j) / 2;
      if (paramFloat < this.mX[k]) {
        j = k;
        continue;
      } 
      i = k;
    } 
    float f = this.mX[j] - this.mX[i];
    if (f == 0.0F)
      return this.mY[i]; 
    paramFloat = (paramFloat - this.mX[i]) / f;
    f = this.mY[i];
    return (this.mY[j] - f) * paramFloat + f;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/vectordrawable/graphics/drawable/PathInterpolatorCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
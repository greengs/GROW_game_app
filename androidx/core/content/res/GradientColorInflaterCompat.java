package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
final class GradientColorInflaterCompat {
  private static final int TILE_MODE_CLAMP = 0;
  
  private static final int TILE_MODE_MIRROR = 2;
  
  private static final int TILE_MODE_REPEAT = 1;
  
  private static ColorStops checkColors(@Nullable ColorStops paramColorStops, @ColorInt int paramInt1, @ColorInt int paramInt2, boolean paramBoolean, @ColorInt int paramInt3) {
    return (paramColorStops != null) ? paramColorStops : (paramBoolean ? new ColorStops(paramInt1, paramInt3, paramInt2) : new ColorStops(paramInt1, paramInt2));
  }
  
  static Shader createFromXml(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i;
    AttributeSet attributeSet = Xml.asAttributeSet(paramXmlPullParser);
    do {
      i = paramXmlPullParser.next();
    } while (i != 2 && i != 1);
    if (i != 2)
      throw new XmlPullParserException("No start tag found"); 
    return createFromXmlInner(paramResources, paramXmlPullParser, attributeSet, paramTheme);
  }
  
  static Shader createFromXmlInner(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws IOException, XmlPullParserException {
    String str = paramXmlPullParser.getName();
    if (!str.equals("gradient"))
      throw new XmlPullParserException(paramXmlPullParser.getPositionDescription() + ": invalid gradient color tag " + str); 
    TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.GradientColor);
    float f1 = TypedArrayUtils.getNamedFloat(typedArray, paramXmlPullParser, "startX", R.styleable.GradientColor_android_startX, 0.0F);
    float f2 = TypedArrayUtils.getNamedFloat(typedArray, paramXmlPullParser, "startY", R.styleable.GradientColor_android_startY, 0.0F);
    float f3 = TypedArrayUtils.getNamedFloat(typedArray, paramXmlPullParser, "endX", R.styleable.GradientColor_android_endX, 0.0F);
    float f4 = TypedArrayUtils.getNamedFloat(typedArray, paramXmlPullParser, "endY", R.styleable.GradientColor_android_endY, 0.0F);
    float f5 = TypedArrayUtils.getNamedFloat(typedArray, paramXmlPullParser, "centerX", R.styleable.GradientColor_android_centerX, 0.0F);
    float f6 = TypedArrayUtils.getNamedFloat(typedArray, paramXmlPullParser, "centerY", R.styleable.GradientColor_android_centerY, 0.0F);
    int i = TypedArrayUtils.getNamedInt(typedArray, paramXmlPullParser, "type", R.styleable.GradientColor_android_type, 0);
    int j = TypedArrayUtils.getNamedColor(typedArray, paramXmlPullParser, "startColor", R.styleable.GradientColor_android_startColor, 0);
    boolean bool = TypedArrayUtils.hasAttribute(paramXmlPullParser, "centerColor");
    int k = TypedArrayUtils.getNamedColor(typedArray, paramXmlPullParser, "centerColor", R.styleable.GradientColor_android_centerColor, 0);
    int m = TypedArrayUtils.getNamedColor(typedArray, paramXmlPullParser, "endColor", R.styleable.GradientColor_android_endColor, 0);
    int n = TypedArrayUtils.getNamedInt(typedArray, paramXmlPullParser, "tileMode", R.styleable.GradientColor_android_tileMode, 0);
    float f7 = TypedArrayUtils.getNamedFloat(typedArray, paramXmlPullParser, "gradientRadius", R.styleable.GradientColor_android_gradientRadius, 0.0F);
    typedArray.recycle();
    ColorStops colorStops = checkColors(inflateChildElements(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme), j, m, bool, k);
    switch (i) {
      default:
        return (Shader)new LinearGradient(f1, f2, f3, f4, colorStops.mColors, colorStops.mOffsets, parseTileMode(n));
      case 1:
        if (f7 <= 0.0F)
          throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type"); 
        return (Shader)new RadialGradient(f5, f6, f7, colorStops.mColors, colorStops.mOffsets, parseTileMode(n));
      case 2:
        break;
    } 
    return (Shader)new SweepGradient(f5, f6, colorStops.mColors, colorStops.mOffsets);
  }
  
  private static ColorStops inflateChildElements(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i = paramXmlPullParser.getDepth() + 1;
    ArrayList<Float> arrayList = new ArrayList(20);
    ArrayList<Integer> arrayList1 = new ArrayList(20);
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          if (j == 2 && k <= i && paramXmlPullParser.getName().equals("item")) {
            TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.GradientColorItem);
            boolean bool1 = typedArray.hasValue(R.styleable.GradientColorItem_android_color);
            boolean bool2 = typedArray.hasValue(R.styleable.GradientColorItem_android_offset);
            if (!bool1 || !bool2)
              throw new XmlPullParserException(paramXmlPullParser.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' " + "attribute!"); 
            j = typedArray.getColor(R.styleable.GradientColorItem_android_color, 0);
            float f = typedArray.getFloat(R.styleable.GradientColorItem_android_offset, 0.0F);
            typedArray.recycle();
            arrayList1.add(Integer.valueOf(j));
            arrayList.add(Float.valueOf(f));
          } 
          continue;
        } 
      } 
      break;
    } 
    return (arrayList1.size() > 0) ? new ColorStops(arrayList1, arrayList) : null;
  }
  
  private static Shader.TileMode parseTileMode(int paramInt) {
    switch (paramInt) {
      default:
        return Shader.TileMode.CLAMP;
      case 1:
        return Shader.TileMode.REPEAT;
      case 2:
        break;
    } 
    return Shader.TileMode.MIRROR;
  }
  
  static final class ColorStops {
    final int[] mColors;
    
    final float[] mOffsets;
    
    ColorStops(@ColorInt int param1Int1, @ColorInt int param1Int2) {
      this.mColors = new int[] { param1Int1, param1Int2 };
      this.mOffsets = new float[] { 0.0F, 1.0F };
    }
    
    ColorStops(@ColorInt int param1Int1, @ColorInt int param1Int2, @ColorInt int param1Int3) {
      this.mColors = new int[] { param1Int1, param1Int2, param1Int3 };
      this.mOffsets = new float[] { 0.0F, 0.5F, 1.0F };
    }
    
    ColorStops(@NonNull List<Integer> param1List, @NonNull List<Float> param1List1) {
      int j = param1List.size();
      this.mColors = new int[j];
      this.mOffsets = new float[j];
      for (int i = 0; i < j; i++) {
        this.mColors[i] = ((Integer)param1List.get(i)).intValue();
        this.mOffsets[i] = ((Float)param1List1.get(i)).floatValue();
      } 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/content/res/GradientColorInflaterCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
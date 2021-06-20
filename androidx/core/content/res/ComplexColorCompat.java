package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ComplexColorCompat {
  private static final String LOG_TAG = "ComplexColorCompat";
  
  private int mColor;
  
  private final ColorStateList mColorStateList;
  
  private final Shader mShader;
  
  private ComplexColorCompat(Shader paramShader, ColorStateList paramColorStateList, @ColorInt int paramInt) {
    this.mShader = paramShader;
    this.mColorStateList = paramColorStateList;
    this.mColor = paramInt;
  }
  
  @NonNull
  private static ComplexColorCompat createFromXml(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme) throws IOException, XmlPullParserException {
    XmlResourceParser xmlResourceParser = paramResources.getXml(paramInt);
    AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)xmlResourceParser);
    do {
      paramInt = xmlResourceParser.next();
    } while (paramInt != 2 && paramInt != 1);
    if (paramInt != 2)
      throw new XmlPullParserException("No start tag found"); 
    String str = xmlResourceParser.getName();
    paramInt = -1;
    switch (str.hashCode()) {
      default:
        switch (paramInt) {
          default:
            throw new XmlPullParserException(xmlResourceParser.getPositionDescription() + ": unsupported complex color tag " + str);
          case 0:
            return from(ColorStateListInflaterCompat.createFromXmlInner(paramResources, (XmlPullParser)xmlResourceParser, attributeSet, paramTheme));
          case 1:
            break;
        } 
        return from(GradientColorInflaterCompat.createFromXmlInner(paramResources, (XmlPullParser)xmlResourceParser, attributeSet, paramTheme));
      case 1191572447:
        if (str.equals("selector"))
          paramInt = 0; 
      case 89650992:
        break;
    } 
    if (str.equals("gradient"))
      paramInt = 1; 
  }
  
  static ComplexColorCompat from(@ColorInt int paramInt) {
    return new ComplexColorCompat(null, null, paramInt);
  }
  
  static ComplexColorCompat from(@NonNull ColorStateList paramColorStateList) {
    return new ComplexColorCompat(null, paramColorStateList, paramColorStateList.getDefaultColor());
  }
  
  static ComplexColorCompat from(@NonNull Shader paramShader) {
    return new ComplexColorCompat(paramShader, null, 0);
  }
  
  @Nullable
  public static ComplexColorCompat inflate(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme) {
    try {
      return createFromXml(paramResources, paramInt, paramTheme);
    } catch (Exception exception) {
      Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", exception);
      return null;
    } 
  }
  
  @ColorInt
  public int getColor() {
    return this.mColor;
  }
  
  @Nullable
  public Shader getShader() {
    return this.mShader;
  }
  
  public boolean isGradient() {
    return (this.mShader != null);
  }
  
  public boolean isStateful() {
    return (this.mShader == null && this.mColorStateList != null && this.mColorStateList.isStateful());
  }
  
  public boolean onStateChanged(int[] paramArrayOfint) {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (isStateful()) {
      int i = this.mColorStateList.getColorForState(paramArrayOfint, this.mColorStateList.getDefaultColor());
      bool1 = bool2;
      if (i != this.mColor) {
        bool1 = true;
        this.mColor = i;
      } 
    } 
    return bool1;
  }
  
  public void setColor(@ColorInt int paramInt) {
    this.mColor = paramInt;
  }
  
  public boolean willDraw() {
    return (isGradient() || this.mColor != 0);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/content/res/ComplexColorCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
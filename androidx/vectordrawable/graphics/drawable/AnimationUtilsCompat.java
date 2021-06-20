package androidx.vectordrawable.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import androidx.annotation.RestrictTo;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AnimationUtilsCompat {
  private static Interpolator createInterpolatorFromXml(Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    PathInterpolatorCompat pathInterpolatorCompat;
    paramResources = null;
    int i = paramXmlPullParser.getDepth();
    while (true) {
      int j = paramXmlPullParser.next();
      if ((j != 3 || paramXmlPullParser.getDepth() > i) && j != 1) {
        if (j == 2) {
          LinearInterpolator linearInterpolator;
          AccelerateInterpolator accelerateInterpolator;
          AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;
          CycleInterpolator cycleInterpolator;
          AnticipateInterpolator anticipateInterpolator;
          OvershootInterpolator overshootInterpolator;
          BounceInterpolator bounceInterpolator;
          AttributeSet attributeSet = Xml.asAttributeSet(paramXmlPullParser);
          String str = paramXmlPullParser.getName();
          if (str.equals("linearInterpolator")) {
            linearInterpolator = new LinearInterpolator();
            continue;
          } 
          if (str.equals("accelerateInterpolator")) {
            accelerateInterpolator = new AccelerateInterpolator(paramContext, (AttributeSet)linearInterpolator);
            continue;
          } 
          if (str.equals("decelerateInterpolator")) {
            DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(paramContext, (AttributeSet)accelerateInterpolator);
            continue;
          } 
          if (str.equals("accelerateDecelerateInterpolator")) {
            accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
            continue;
          } 
          if (str.equals("cycleInterpolator")) {
            cycleInterpolator = new CycleInterpolator(paramContext, (AttributeSet)accelerateDecelerateInterpolator);
            continue;
          } 
          if (str.equals("anticipateInterpolator")) {
            anticipateInterpolator = new AnticipateInterpolator(paramContext, (AttributeSet)cycleInterpolator);
            continue;
          } 
          if (str.equals("overshootInterpolator")) {
            overshootInterpolator = new OvershootInterpolator(paramContext, (AttributeSet)anticipateInterpolator);
            continue;
          } 
          if (str.equals("anticipateOvershootInterpolator")) {
            AnticipateOvershootInterpolator anticipateOvershootInterpolator = new AnticipateOvershootInterpolator(paramContext, (AttributeSet)overshootInterpolator);
            continue;
          } 
          if (str.equals("bounceInterpolator")) {
            bounceInterpolator = new BounceInterpolator();
            continue;
          } 
          if (str.equals("pathInterpolator")) {
            pathInterpolatorCompat = new PathInterpolatorCompat(paramContext, (AttributeSet)bounceInterpolator, paramXmlPullParser);
            continue;
          } 
          throw new RuntimeException("Unknown interpolator name: " + paramXmlPullParser.getName());
        } 
        continue;
      } 
      break;
    } 
    return pathInterpolatorCompat;
  }
  
  public static Interpolator loadInterpolator(Context paramContext, int paramInt) throws Resources.NotFoundException {
    LinearOutSlowInInterpolator linearOutSlowInInterpolator;
    if (Build.VERSION.SDK_INT >= 21)
      return AnimationUtils.loadInterpolator(paramContext, paramInt); 
    Resources.NotFoundException notFoundException1 = null;
    Resources.NotFoundException notFoundException3 = null;
    Resources.NotFoundException notFoundException2 = null;
    if (paramInt == 17563663) {
      try {
        FastOutLinearInInterpolator fastOutLinearInInterpolator = new FastOutLinearInInterpolator();
      } catch (XmlPullParserException xmlPullParserException) {
        notFoundException1 = notFoundException2;
        notFoundException3 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(paramInt));
        notFoundException1 = notFoundException2;
        notFoundException3.initCause((Throwable)xmlPullParserException);
        notFoundException1 = notFoundException2;
        throw notFoundException3;
      } catch (IOException iOException) {
      
      } finally {
        if (notFoundException1 != null)
          notFoundException1.close(); 
      } 
      return (Interpolator)paramContext;
    } 
    if (paramInt == 17563661) {
      FastOutSlowInInterpolator fastOutSlowInInterpolator = new FastOutSlowInInterpolator();
      if (false)
        throw new NullPointerException(); 
      return (Interpolator)fastOutSlowInInterpolator;
    } 
    if (paramInt == 17563662) {
      linearOutSlowInInterpolator = new LinearOutSlowInInterpolator();
      if (false)
        throw new NullPointerException(); 
      return (Interpolator)linearOutSlowInInterpolator;
    } 
    XmlResourceParser xmlResourceParser4 = linearOutSlowInInterpolator.getResources().getAnimation(paramInt);
    XmlResourceParser xmlResourceParser2 = xmlResourceParser4;
    XmlResourceParser xmlResourceParser1 = xmlResourceParser4;
    XmlResourceParser xmlResourceParser3 = xmlResourceParser4;
    Interpolator interpolator1 = createInterpolatorFromXml((Context)linearOutSlowInInterpolator, linearOutSlowInInterpolator.getResources(), linearOutSlowInInterpolator.getTheme(), (XmlPullParser)xmlResourceParser4);
    Interpolator interpolator2 = interpolator1;
    interpolator1 = interpolator2;
    if (xmlResourceParser4 != null) {
      xmlResourceParser4.close();
      return interpolator2;
    } 
    return interpolator1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/vectordrawable/graphics/drawable/AnimationUtilsCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package androidx.appcompat.text;

import android.content.Context;
import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import androidx.annotation.RestrictTo;
import java.util.Locale;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AllCapsTransformationMethod implements TransformationMethod {
  private Locale mLocale;
  
  public AllCapsTransformationMethod(Context paramContext) {
    this.mLocale = (paramContext.getResources().getConfiguration()).locale;
  }
  
  public CharSequence getTransformation(CharSequence paramCharSequence, View paramView) {
    return (paramCharSequence != null) ? paramCharSequence.toString().toUpperCase(this.mLocale) : null;
  }
  
  public void onFocusChanged(View paramView, CharSequence paramCharSequence, boolean paramBoolean, int paramInt, Rect paramRect) {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/text/AllCapsTransformationMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
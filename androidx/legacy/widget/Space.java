package androidx.legacy.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@Deprecated
public class Space extends View {
  @Deprecated
  public Space(@NonNull Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  @Deprecated
  public Space(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  @Deprecated
  public Space(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    if (getVisibility() == 0)
      setVisibility(4); 
  }
  
  private static int getDefaultSize2(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.getMode(paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    switch (i) {
      default:
        return paramInt1;
      case 0:
        return paramInt1;
      case -2147483648:
        return Math.min(paramInt1, paramInt2);
      case 1073741824:
        break;
    } 
    return paramInt2;
  }
  
  @Deprecated
  @SuppressLint({"MissingSuperCall"})
  public void draw(Canvas paramCanvas) {}
  
  @Deprecated
  protected void onMeasure(int paramInt1, int paramInt2) {
    setMeasuredDimension(getDefaultSize2(getSuggestedMinimumWidth(), paramInt1), getDefaultSize2(getSuggestedMinimumHeight(), paramInt2));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/legacy/widget/Space.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
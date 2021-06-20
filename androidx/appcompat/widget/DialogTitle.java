package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.widget.TextView;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.widget.TextViewCompat;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class DialogTitle extends TextView {
  public DialogTitle(Context paramContext) {
    super(paramContext);
  }
  
  public DialogTitle(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public DialogTitle(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    super.onMeasure(paramInt1, paramInt2);
    Layout layout = getLayout();
    if (layout != null) {
      int i = layout.getLineCount();
      if (i > 0 && layout.getEllipsisCount(i - 1) > 0) {
        setSingleLine(false);
        setMaxLines(2);
        TypedArray typedArray = getContext().obtainStyledAttributes(null, R.styleable.TextAppearance, 16842817, 16973892);
        i = typedArray.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, 0);
        if (i != 0)
          setTextSize(0, i); 
        typedArray.recycle();
        super.onMeasure(paramInt1, paramInt2);
      } 
    } 
  }
  
  public void setCustomSelectionActionModeCallback(ActionMode.Callback paramCallback) {
    super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, paramCallback));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/DialogTitle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
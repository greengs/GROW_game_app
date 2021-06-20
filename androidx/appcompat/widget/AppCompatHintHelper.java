package androidx.appcompat.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

class AppCompatHintHelper {
  static InputConnection onCreateInputConnection(InputConnection paramInputConnection, EditorInfo paramEditorInfo, View paramView) {
    if (paramInputConnection != null && paramEditorInfo.hintText == null)
      for (ViewParent viewParent = paramView.getParent();; viewParent = viewParent.getParent()) {
        if (viewParent instanceof View) {
          if (viewParent instanceof WithHint) {
            paramEditorInfo.hintText = ((WithHint)viewParent).getHint();
            return paramInputConnection;
          } 
        } else {
          return paramInputConnection;
        } 
      }  
    return paramInputConnection;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/AppCompatHintHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package androidx.appcompat.app;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.RestrictTo;
import androidx.fragment.app.DialogFragment;

public class AppCompatDialogFragment extends DialogFragment {
  public Dialog onCreateDialog(Bundle paramBundle) {
    return new AppCompatDialog(getContext(), getTheme());
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setupDialog(Dialog paramDialog, int paramInt) {
    if (paramDialog instanceof AppCompatDialog) {
      AppCompatDialog appCompatDialog = (AppCompatDialog)paramDialog;
      switch (paramInt) {
        default:
          return;
        case 3:
          paramDialog.getWindow().addFlags(24);
          break;
        case 1:
        case 2:
          break;
      } 
      appCompatDialog.supportRequestWindowFeature(1);
      return;
    } 
    super.setupDialog(paramDialog, paramInt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/app/AppCompatDialogFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
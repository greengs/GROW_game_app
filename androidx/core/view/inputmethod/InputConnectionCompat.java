package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class InputConnectionCompat {
  private static final String COMMIT_CONTENT_ACTION = "androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
  
  private static final String COMMIT_CONTENT_CONTENT_URI_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI";
  
  private static final String COMMIT_CONTENT_DESCRIPTION_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
  
  private static final String COMMIT_CONTENT_FLAGS_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
  
  private static final String COMMIT_CONTENT_LINK_URI_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
  
  private static final String COMMIT_CONTENT_OPTS_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
  
  private static final String COMMIT_CONTENT_RESULT_RECEIVER = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
  
  public static final int INPUT_CONTENT_GRANT_READ_URI_PERMISSION = 1;
  
  public static boolean commitContent(@NonNull InputConnection paramInputConnection, @NonNull EditorInfo paramEditorInfo, @NonNull InputContentInfoCompat paramInputContentInfoCompat, int paramInt, @Nullable Bundle paramBundle) {
    ClipDescription clipDescription = paramInputContentInfoCompat.getDescription();
    boolean bool = false;
    String[] arrayOfString = EditorInfoCompat.getContentMimeTypes(paramEditorInfo);
    int j = arrayOfString.length;
    int i = 0;
    while (true) {
      boolean bool1 = bool;
      if (i < j)
        if (clipDescription.hasMimeType(arrayOfString[i])) {
          bool1 = true;
        } else {
          i++;
          continue;
        }  
      if (!bool1)
        return false; 
      if (Build.VERSION.SDK_INT >= 25)
        return paramInputConnection.commitContent((InputContentInfo)paramInputContentInfoCompat.unwrap(), paramInt, paramBundle); 
      Bundle bundle = new Bundle();
      bundle.putParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI", (Parcelable)paramInputContentInfoCompat.getContentUri());
      bundle.putParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION", (Parcelable)paramInputContentInfoCompat.getDescription());
      bundle.putParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI", (Parcelable)paramInputContentInfoCompat.getLinkUri());
      bundle.putInt("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS", paramInt);
      bundle.putParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS", (Parcelable)paramBundle);
      return paramInputConnection.performPrivateCommand("androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", bundle);
    } 
  }
  
  @NonNull
  public static InputConnection createWrapper(@NonNull InputConnection paramInputConnection, @NonNull EditorInfo paramEditorInfo, @NonNull final OnCommitContentListener listener) {
    if (paramInputConnection == null)
      throw new IllegalArgumentException("inputConnection must be non-null"); 
    if (paramEditorInfo == null)
      throw new IllegalArgumentException("editorInfo must be non-null"); 
    if (listener == null)
      throw new IllegalArgumentException("onCommitContentListener must be non-null"); 
    if (Build.VERSION.SDK_INT >= 25)
      return (InputConnection)new InputConnectionWrapper(paramInputConnection, false) {
          public boolean commitContent(InputContentInfo param1InputContentInfo, int param1Int, Bundle param1Bundle) {
            return listener.onCommitContent(InputContentInfoCompat.wrap(param1InputContentInfo), param1Int, param1Bundle) ? true : super.commitContent(param1InputContentInfo, param1Int, param1Bundle);
          }
        }; 
    InputConnection inputConnection = paramInputConnection;
    return (InputConnection)(((EditorInfoCompat.getContentMimeTypes(paramEditorInfo)).length != 0) ? new InputConnectionWrapper(paramInputConnection, false) {
        public boolean performPrivateCommand(String param1String, Bundle param1Bundle) {
          return InputConnectionCompat.handlePerformPrivateCommand(param1String, param1Bundle, listener) ? true : super.performPrivateCommand(param1String, param1Bundle);
        }
      } : inputConnection);
  }
  
  static boolean handlePerformPrivateCommand(@Nullable String paramString, @NonNull Bundle paramBundle, @NonNull OnCommitContentListener paramOnCommitContentListener) {
    boolean bool = true;
    if (TextUtils.equals("androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", paramString) && paramBundle != null) {
      ResultReceiver resultReceiver;
      paramString = null;
      try {
        ResultReceiver resultReceiver1 = (ResultReceiver)paramBundle.getParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER");
        resultReceiver = resultReceiver1;
        Uri uri1 = (Uri)paramBundle.getParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI");
        resultReceiver = resultReceiver1;
        ClipDescription clipDescription = (ClipDescription)paramBundle.getParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION");
        resultReceiver = resultReceiver1;
        Uri uri2 = (Uri)paramBundle.getParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI");
        resultReceiver = resultReceiver1;
        int i = paramBundle.getInt("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS");
        resultReceiver = resultReceiver1;
        paramBundle = (Bundle)paramBundle.getParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS");
        resultReceiver = resultReceiver1;
        boolean bool1 = paramOnCommitContentListener.onCommitContent(new InputContentInfoCompat(uri1, clipDescription, uri2), i, paramBundle);
        return bool1;
      } finally {
        if (resultReceiver != null) {
          if (!false)
            bool = false; 
          resultReceiver.send(bool, null);
        } 
      } 
    } 
    return false;
  }
  
  public static interface OnCommitContentListener {
    boolean onCommitContent(InputContentInfoCompat param1InputContentInfoCompat, int param1Int, Bundle param1Bundle);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/inputmethod/InputConnectionCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
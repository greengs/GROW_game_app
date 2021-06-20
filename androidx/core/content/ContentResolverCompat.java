package androidx.core.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.core.os.CancellationSignal;
import androidx.core.os.OperationCanceledException;

public final class ContentResolverCompat {
  public static Cursor query(ContentResolver paramContentResolver, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal) {
    if (Build.VERSION.SDK_INT >= 16) {
      if (paramCancellationSignal != null)
        try {
          Object object = paramCancellationSignal.getCancellationSignalObject();
          return paramContentResolver.query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, (CancellationSignal)object);
        } catch (Exception exception) {
          if (exception instanceof android.os.OperationCanceledException)
            throw new OperationCanceledException(); 
          throw exception;
        }  
      paramCancellationSignal = null;
      return exception.query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, (CancellationSignal)paramCancellationSignal);
    } 
    if (paramCancellationSignal != null)
      paramCancellationSignal.throwIfCanceled(); 
    return paramContentResolver.query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/content/ContentResolverCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
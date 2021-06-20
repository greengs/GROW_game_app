package com.google.appinventor.components.runtime;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.provider.Contacts;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import com.google.appinventor.components.runtime.util.HoneycombMR1Util;
import com.google.appinventor.components.runtime.util.SdkLevel;

public class EmailAddressAdapter extends ResourceCursorAdapter {
  private static final boolean DEBUG = false;
  
  private static final String[] POST_HONEYCOMB_PROJECTION;
  
  public static final int PRE_HONEYCOMB_DATA_INDEX = 2;
  
  public static final int PRE_HONEYCOMB_NAME_INDEX = 1;
  
  private static final String[] PRE_HONEYCOMB_PROJECTION = new String[] { "_id", "name", "data" };
  
  private static String SORT_ORDER;
  
  private static final String TAG = "EmailAddressAdapter";
  
  private ContentResolver contentResolver;
  
  private Context context;
  
  static {
    POST_HONEYCOMB_PROJECTION = HoneycombMR1Util.getEmailAdapterProjection();
  }
  
  public EmailAddressAdapter(Context paramContext) {
    super(paramContext, 17367050, null);
    this.contentResolver = paramContext.getContentResolver();
    this.context = paramContext;
    if (SdkLevel.getLevel() >= 12) {
      SORT_ORDER = HoneycombMR1Util.getTimesContacted() + " DESC, " + HoneycombMR1Util.getDisplayName();
      return;
    } 
    SORT_ORDER = "times_contacted DESC, name";
  }
  
  private final String makeDisplayString(Cursor paramCursor) {
    String str1;
    String str2;
    int i = paramCursor.getColumnIndex(HoneycombMR1Util.getDisplayName());
    int j = paramCursor.getColumnIndex(HoneycombMR1Util.getEmailAddress());
    StringBuilder stringBuilder = new StringBuilder();
    boolean bool = false;
    if (SdkLevel.getLevel() >= 12) {
      str2 = paramCursor.getString(i);
      str1 = paramCursor.getString(j);
    } else {
      str2 = str1.getString(1);
      str1 = str1.getString(2);
    } 
    if (!TextUtils.isEmpty(str2)) {
      stringBuilder.append(str2);
      bool = true;
    } 
    if (bool)
      stringBuilder.append(" <"); 
    stringBuilder.append(str1);
    if (bool)
      stringBuilder.append(">"); 
    return stringBuilder.toString();
  }
  
  public final void bindView(View paramView, Context paramContext, Cursor paramCursor) {
    ((TextView)paramView).setText(makeDisplayString(paramCursor));
  }
  
  public final String convertToString(Cursor paramCursor) {
    int i = paramCursor.getColumnIndex(HoneycombMR1Util.getDisplayName());
    int j = paramCursor.getColumnIndex(HoneycombMR1Util.getEmailAddress());
    if (SdkLevel.getLevel() >= 12) {
      String str = paramCursor.getString(i);
      str1 = paramCursor.getString(j);
      return (new Rfc822Token(str, str1, null)).toString();
    } 
    String str2 = str1.getString(1);
    String str1 = str1.getString(2);
    return (new Rfc822Token(str2, str1, null)).toString();
  }
  
  public Cursor runQueryOnBackgroundThread(CharSequence paramCharSequence) {
    Uri uri = null;
    StringBuilder stringBuilder = new StringBuilder();
    if (paramCharSequence != null) {
      paramCharSequence = DatabaseUtils.sqlEscapeString(paramCharSequence.toString() + '%');
      if (SdkLevel.getLevel() >= 12) {
        uri = HoneycombMR1Util.getDataContentUri();
        stringBuilder.append("(" + HoneycombMR1Util.getDataMimeType() + "='" + HoneycombMR1Util.getEmailType() + "')");
        stringBuilder.append(" AND ");
        stringBuilder.append("(display_name LIKE ");
        stringBuilder.append((String)paramCharSequence);
        stringBuilder.append(")");
      } else {
        uri = Contacts.ContactMethods.CONTENT_EMAIL_URI;
        stringBuilder.append("(name LIKE ");
        stringBuilder.append((String)paramCharSequence);
        stringBuilder.append(") OR (display_name LIKE ");
        stringBuilder.append((String)paramCharSequence);
        stringBuilder.append(")");
      } 
    } 
    paramCharSequence = stringBuilder.toString();
    return (SdkLevel.getLevel() >= 12) ? this.contentResolver.query(uri, POST_HONEYCOMB_PROJECTION, (String)paramCharSequence, null, SORT_ORDER) : this.contentResolver.query(uri, PRE_HONEYCOMB_PROJECTION, (String)paramCharSequence, null, SORT_ORDER);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/EmailAddressAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
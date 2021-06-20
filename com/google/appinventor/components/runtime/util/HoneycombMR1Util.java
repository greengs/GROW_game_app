package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import java.io.InputStream;

public class HoneycombMR1Util {
  public static int getContactIdIndex(Cursor paramCursor) {
    return paramCursor.getColumnIndex("contact_id");
  }
  
  public static String[] getContactProjection() {
    return new String[] { "_id", "display_name", "photo_thumb_uri", "photo_uri" };
  }
  
  public static Uri getContentUri() {
    return ContactsContract.Contacts.CONTENT_URI;
  }
  
  public static Uri getDataContentUri() {
    return ContactsContract.Data.CONTENT_URI;
  }
  
  public static Cursor getDataCursor(String paramString, Activity paramActivity, String[] paramArrayOfString) {
    return paramActivity.getContentResolver().query(ContactsContract.Data.CONTENT_URI, paramArrayOfString, "contact_id=? AND (mimetype=? OR mimetype=?)", new String[] { paramString, "vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/email_v2" }, null);
  }
  
  public static String getDataMimeType() {
    return "mimetype";
  }
  
  public static String[] getDataProjection() {
    return new String[] { "mimetype", "data1", "data2", "data1", "data2" };
  }
  
  public static String getDisplayName() {
    return "display_name";
  }
  
  public static String[] getEmailAdapterProjection() {
    return new String[] { "_id", "display_name", "data1", "mimetype" };
  }
  
  public static String getEmailAddress() {
    return "data1";
  }
  
  public static int getEmailIndex(Cursor paramCursor) {
    return paramCursor.getColumnIndex("data1");
  }
  
  public static String getEmailType() {
    return "vnd.android.cursor.item/email_v2";
  }
  
  public static int getIdIndex(Cursor paramCursor) {
    return paramCursor.getColumnIndex("_id");
  }
  
  public static int getMimeIndex(Cursor paramCursor) {
    return paramCursor.getColumnIndex("mimetype");
  }
  
  public static int getNameIndex(Cursor paramCursor) {
    return paramCursor.getColumnIndex("display_name");
  }
  
  public static String[] getNameProjection() {
    return new String[] { "contact_id", "display_name", "photo_thumb_uri", "data1" };
  }
  
  public static Uri getPhoneContentUri() {
    return ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
  }
  
  public static int getPhoneIndex(Cursor paramCursor) {
    return paramCursor.getColumnIndex("data1");
  }
  
  public static String getPhoneType() {
    return "vnd.android.cursor.item/phone_v2";
  }
  
  public static int getPhotoIndex(Cursor paramCursor) {
    return paramCursor.getColumnIndex("photo_uri");
  }
  
  public static int getThumbnailIndex(Cursor paramCursor) {
    return paramCursor.getColumnIndex("photo_thumb_uri");
  }
  
  public static String getTimesContacted() {
    return "times_contacted";
  }
  
  public static InputStream openContactPhotoInputStreamHelper(ContentResolver paramContentResolver, Uri paramUri) {
    return ContactsContract.Contacts.openContactPhotoInputStream(paramContentResolver, paramUri);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/HoneycombMR1Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
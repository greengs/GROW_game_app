package com.google.appinventor.components.runtime;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.HoneycombMR1Util;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.util.ArrayList;

@DesignerComponent(category = ComponentCategory.SOCIAL, description = "A button that, when clicked on, displays a list of the contacts' phone numbers to choose among. After the user has made a selection, the following properties will be set to information about the chosen contact: <ul>\n<li> <code>ContactName</code>: the contact's name </li>\n <li> <code>PhoneNumber</code>: the contact's phone number </li>\n <li> <code>EmailAddress</code>: the contact's email address </li> <li> <code>Picture</code>: the name of the file containing the contact's image, which can be used as a <code>Picture</code> property value for the <code>Image</code> or <code>ImageSprite</code> component.</li></ul>\n</p><p>Other properties affect the appearance of the button (<code>TextAlignment</code>, <code>BackgroundColor</code>, etc.) and whether it can be clicked on (<code>Enabled</code>).</p>\n<p>The PhoneNumberPicker component may not work on all Android devices. For example, on Android systems before system 3.0, the returned lists of phone numbers and email addresses will be empty.\n", version = 4)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.READ_CONTACTS")
public class PhoneNumberPicker extends ContactPicker {
  private static String[] DATA_PROJECTION;
  
  private static final int EMAIL_INDEX = 3;
  
  private static final String LOG_TAG = "PhoneNumberPicker";
  
  private static final int NAME_INDEX = 0;
  
  private static String[] NAME_PROJECTION;
  
  private static final int NUMBER_INDEX = 1;
  
  private static final int PERSON_INDEX = 2;
  
  private static final String[] PROJECTION = new String[] { "name", "number", "person", "primary_email" };
  
  public PhoneNumberPicker(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, Contacts.Phones.CONTENT_URI);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String PhoneNumber() {
    return ensureNotNull(this.phoneNumber);
  }
  
  public void postHoneycombGetContactEmailsAndPhones(Cursor paramCursor) {
    ArrayList<String> arrayList1 = new ArrayList();
    ArrayList<String> arrayList2 = new ArrayList();
    if (paramCursor.moveToFirst()) {
      int i = HoneycombMR1Util.getPhoneIndex(paramCursor);
      int j = HoneycombMR1Util.getEmailIndex(paramCursor);
      int k = HoneycombMR1Util.getMimeIndex(paramCursor);
      String str1 = HoneycombMR1Util.getPhoneType();
      String str2 = HoneycombMR1Util.getEmailType();
      while (!paramCursor.isAfterLast()) {
        String str = guardCursorGetString(paramCursor, k);
        if (str.contains(str1)) {
          arrayList1.add(guardCursorGetString(paramCursor, i));
        } else if (str.contains(str2)) {
          arrayList2.add(guardCursorGetString(paramCursor, j));
        } else {
          Log.i("ContactPicker", "Type mismatch: " + str + " not " + str1 + " or " + str2);
        } 
        paramCursor.moveToNext();
      } 
      this.phoneNumberList = arrayList1;
      this.emailAddressList = arrayList2;
      if (!this.emailAddressList.isEmpty()) {
        this.emailAddress = this.emailAddressList.get(0);
        return;
      } 
    } else {
      return;
    } 
    this.emailAddress = "";
  }
  
  public String postHoneycombGetContactNameAndPicture(Cursor paramCursor) {
    String str = "";
    if (paramCursor.moveToFirst()) {
      int i = HoneycombMR1Util.getContactIdIndex(paramCursor);
      int j = HoneycombMR1Util.getNameIndex(paramCursor);
      int k = HoneycombMR1Util.getThumbnailIndex(paramCursor);
      this.phoneNumber = guardCursorGetString(paramCursor, HoneycombMR1Util.getPhoneIndex(paramCursor));
      str = guardCursorGetString(paramCursor, i);
      this.contactName = guardCursorGetString(paramCursor, j);
      this.contactPictureUri = guardCursorGetString(paramCursor, k);
    } 
    return str;
  }
  
  public void preHoneycombGetContactInfo(Cursor paramCursor) {
    if (paramCursor.moveToFirst()) {
      this.contactName = guardCursorGetString(paramCursor, 0);
      this.phoneNumber = guardCursorGetString(paramCursor, 1);
      int i = paramCursor.getInt(2);
      this.contactPictureUri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, i).toString();
      this.emailAddress = getEmailAddress(guardCursorGetString(paramCursor, 3));
    } 
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent) {
    if (paramInt1 == this.requestCode && paramInt2 == -1) {
      String str;
      Log.i("PhoneNumberPicker", "received intent is " + paramIntent);
      Uri uri = paramIntent.getData();
      if (SdkLevel.getLevel() >= 12) {
        str = "//com.android.contacts/data";
      } else {
        str = "//contacts/phones";
      } 
      if (checkContactUri(uri, str)) {
        Cursor cursor1;
        String str1;
        String str2;
        String str4 = null;
        Cursor cursor4 = null;
        Cursor cursor5 = null;
        Cursor cursor6 = null;
        String str3 = null;
        null = cursor4;
        Cursor cursor3 = cursor5;
        str = str4;
        Cursor cursor2 = cursor6;
        try {
          String str5;
          if (SdkLevel.getLevel() >= 12) {
            null = cursor4;
            cursor3 = cursor5;
            str = str4;
            cursor2 = cursor6;
            NAME_PROJECTION = HoneycombMR1Util.getNameProjection();
            null = cursor4;
            cursor3 = cursor5;
            str = str4;
            cursor2 = cursor6;
            cursor4 = this.activityContext.getContentResolver().query(uri, NAME_PROJECTION, null, null, null);
            null = cursor4;
            cursor3 = cursor5;
            Cursor cursor = cursor4;
            cursor2 = cursor6;
            str3 = postHoneycombGetContactNameAndPicture(cursor4);
            null = cursor4;
            cursor3 = cursor5;
            cursor = cursor4;
            cursor2 = cursor6;
            DATA_PROJECTION = HoneycombMR1Util.getDataProjection();
            null = cursor4;
            cursor3 = cursor5;
            cursor = cursor4;
            cursor2 = cursor6;
            cursor5 = HoneycombMR1Util.getDataCursor(str3, this.activityContext, DATA_PROJECTION);
            null = cursor4;
            cursor3 = cursor5;
            cursor = cursor4;
            cursor2 = cursor5;
            postHoneycombGetContactEmailsAndPhones(cursor5);
          } else {
            null = cursor4;
            cursor3 = cursor5;
            str = str4;
            cursor2 = cursor6;
            cursor4 = this.activityContext.getContentResolver().query(uri, PROJECTION, null, null, null);
            null = cursor4;
            cursor3 = cursor5;
            Cursor cursor = cursor4;
            cursor2 = cursor6;
            preHoneycombGetContactInfo(cursor4);
            str5 = str3;
          } 
          null = cursor4;
          str2 = str5;
          cursor1 = cursor4;
          str1 = str5;
          Log.i("PhoneNumberPicker", "Contact name = " + this.contactName + ", phone number = " + this.phoneNumber + ", emailAddress = " + this.emailAddress + ", contactPhotoUri = " + this.contactPictureUri);
          if (cursor4 != null)
            cursor4.close(); 
          return;
        } catch (Exception exception) {
          cursor1 = null;
          str1 = str2;
          Log.e("PhoneNumberPicker", "Exception in resultReturned", exception);
          cursor1 = null;
          str1 = str2;
          puntContactSelection(1107);
          if (null != null)
            null.close(); 
          return;
        } finally {
          if (cursor1 != null)
            cursor1.close(); 
          if (str1 != null)
            str1.close(); 
        } 
      } 
    } else {
      return;
    } 
    AfterPicking();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/PhoneNumberPicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
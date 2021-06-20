package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.HoneycombMR1Util;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DesignerComponent(category = ComponentCategory.SOCIAL, description = "A button that, when clicked on, displays a list of the contacts to choose among. After the user has made a selection, the following properties will be set to information about the chosen contact: <ul>\n<li> <code>ContactName</code>: the contact's name </li>\n <li> <code>EmailAddress</code>: the contact's primary email address </li>\n <li> <code>ContactUri</code>: the contact's URI on the device </li>\n<li> <code>EmailAddressList</code>: a list of the contact's email addresses </li>\n <li> <code>PhoneNumber</code>: the contact's primary phone number (on Later Android Verisons)</li>\n <li> <code>PhoneNumberList</code>: a list of the contact's phone numbers (on Later Android Versions)</li>\n <li> <code>Picture</code>: the name of the file containing the contact's image, which can be used as a <code>Picture</code> property value for the <code>Image</code> or <code>ImageSprite</code> component.</li></ul>\n</p><p>Other properties affect the appearance of the button (<code>TextAlignment</code>, <code>BackgroundColor</code>, etc.) and whether it can be clicked on (<code>Enabled</code>).\n</p><p>The ContactPicker component might not work on all phones. For example, on Android systems before system 3.0, it cannot pick phone numbers, and the list of email addresses will contain only one email.", version = 6)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.READ_CONTACTS")
public class ContactPicker extends Picker implements ActivityResultListener {
  private static String[] CONTACT_PROJECTION;
  
  private static String[] DATA_PROJECTION;
  
  private static final int EMAIL_INDEX = 1;
  
  private static final int NAME_INDEX = 0;
  
  private static final int PHONE_INDEX = 2;
  
  private static final String[] PROJECTION = new String[] { "name", "primary_email" };
  
  protected final Activity activityContext;
  
  protected String contactName;
  
  protected String contactPictureUri;
  
  protected String contactUri;
  
  protected String emailAddress;
  
  protected List emailAddressList;
  
  private boolean havePermission = false;
  
  private final Uri intentUri;
  
  protected String phoneNumber;
  
  protected List phoneNumberList;
  
  public ContactPicker(ComponentContainer paramComponentContainer) {
    this(paramComponentContainer, Contacts.People.CONTENT_URI);
  }
  
  protected ContactPicker(ComponentContainer paramComponentContainer, Uri paramUri) {
    super(paramComponentContainer);
    this.activityContext = paramComponentContainer.$context();
    if (SdkLevel.getLevel() >= 12 && paramUri.equals(Contacts.People.CONTENT_URI)) {
      this.intentUri = HoneycombMR1Util.getContentUri();
      return;
    } 
    if (SdkLevel.getLevel() >= 12 && paramUri.equals(Contacts.Phones.CONTENT_URI)) {
      this.intentUri = HoneycombMR1Util.getPhoneContentUri();
      return;
    } 
    this.intentUri = paramUri;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String ContactName() {
    return ensureNotNull(this.contactName);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "URI that specifies the location of the contact on the device.")
  public String ContactUri() {
    return ensureNotNull(this.contactUri);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String EmailAddress() {
    return ensureNotNull(this.emailAddress);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public List EmailAddressList() {
    return ensureNotNull(this.emailAddressList);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String PhoneNumber() {
    return ensureNotNull(this.phoneNumber);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public List PhoneNumberList() {
    return ensureNotNull(this.phoneNumberList);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String Picture() {
    return ensureNotNull(this.contactPictureUri);
  }
  
  @SimpleFunction(description = "view a contact via its URI")
  public void ViewContact(String paramString) {
    if (this.contactUri != null) {
      Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      if (intent.resolveActivity(this.activityContext.getPackageManager()) != null)
        this.activityContext.startActivity(intent); 
    } 
  }
  
  protected boolean checkContactUri(Uri paramUri, String paramString) {
    Log.i("ContactPicker", "contactUri is " + paramUri);
    if (paramUri == null || !"content".equals(paramUri.getScheme())) {
      Log.i("ContactPicker", "checkContactUri failed: A");
      puntContactSelection(1107);
      return false;
    } 
    if (!paramUri.getSchemeSpecificPart().startsWith(paramString)) {
      Log.i("ContactPicker", "checkContactUri failed: C");
      Log.i("ContactPicker", paramUri.getPath());
      puntContactSelection(1107);
      return false;
    } 
    return true;
  }
  
  public void click() {
    if (!this.havePermission) {
      this.container.$form().askPermission("android.permission.READ_CONTACTS", new PermissionResultHandler() {
            public void HandlePermissionResponse(String param1String, boolean param1Boolean) {
              if (param1Boolean) {
                ContactPicker.access$002(ContactPicker.this, true);
                ContactPicker.this.click();
                return;
              } 
              ContactPicker.this.container.$form().dispatchPermissionDeniedEvent(ContactPicker.this, "Click", "android.permission.READ_CONTACTS");
            }
          });
      return;
    } 
    super.click();
  }
  
  protected String ensureNotNull(String paramString) {
    String str = paramString;
    if (paramString == null)
      str = ""; 
    return str;
  }
  
  protected List ensureNotNull(List paramList) {
    List list = paramList;
    if (paramList == null)
      list = new ArrayList(); 
    return list;
  }
  
  protected String getEmailAddress(String paramString) {
    try {
      int i = Integer.parseInt(paramString);
      paramString = "";
      String str = "contact_methods._id = " + i;
      Cursor cursor = this.activityContext.getContentResolver().query(Contacts.ContactMethods.CONTENT_EMAIL_URI, new String[] { "data" }, str, null, null);
      try {
        if (cursor.moveToFirst())
          paramString = guardCursorGetString(cursor, 0); 
        return ensureNotNull(paramString);
      } finally {
        cursor.close();
      } 
    } catch (NumberFormatException numberFormatException) {
      return "";
    } 
  }
  
  protected Intent getIntent() {
    return new Intent("android.intent.action.PICK", this.intentUri);
  }
  
  protected String guardCursorGetString(Cursor paramCursor, int paramInt) {
    String str;
    try {
      str = paramCursor.getString(paramInt);
    } catch (Exception exception) {
      str = "";
    } 
    return ensureNotNull(str);
  }
  
  public void postHoneycombGetContactEmailAndPhone(Cursor paramCursor) {
    this.phoneNumber = "";
    this.emailAddress = "";
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
    } 
    if (!arrayList1.isEmpty())
      this.phoneNumber = arrayList1.get(0); 
    if (!arrayList2.isEmpty())
      this.emailAddress = arrayList2.get(0); 
    this.phoneNumberList = arrayList1;
    this.emailAddressList = arrayList2;
  }
  
  public String postHoneycombGetContactNameAndPicture(Cursor paramCursor) {
    String str = "";
    if (paramCursor.moveToFirst()) {
      int i = HoneycombMR1Util.getIdIndex(paramCursor);
      int j = HoneycombMR1Util.getNameIndex(paramCursor);
      int k = HoneycombMR1Util.getThumbnailIndex(paramCursor);
      int m = HoneycombMR1Util.getPhotoIndex(paramCursor);
      str = guardCursorGetString(paramCursor, i);
      this.contactName = guardCursorGetString(paramCursor, j);
      this.contactPictureUri = guardCursorGetString(paramCursor, k);
      Log.i("ContactPicker", "photo_uri=" + guardCursorGetString(paramCursor, m));
    } 
    return str;
  }
  
  public void preHoneycombGetContactInfo(Cursor paramCursor, Uri paramUri) {
    if (paramCursor.moveToFirst()) {
      List<String> list;
      this.contactName = guardCursorGetString(paramCursor, 0);
      this.emailAddress = getEmailAddress(guardCursorGetString(paramCursor, 1));
      this.contactUri = paramUri.toString();
      this.contactPictureUri = paramUri.toString();
      if (this.emailAddress.equals("")) {
        list = new ArrayList();
      } else {
        list = Arrays.asList(new String[] { this.emailAddress });
      } 
      this.emailAddressList = list;
    } 
  }
  
  protected void puntContactSelection(int paramInt) {
    this.contactName = "";
    this.emailAddress = "";
    this.contactPictureUri = "";
    this.container.$form().dispatchErrorOccurredEvent(this, "", paramInt, new Object[0]);
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent) {
    if (paramInt1 == this.requestCode && paramInt2 == -1) {
      String str;
      Log.i("ContactPicker", "received intent is " + paramIntent);
      Uri uri = paramIntent.getData();
      if (SdkLevel.getLevel() >= 12) {
        str = "//com.android.contacts/contact";
      } else {
        str = "//contacts/people";
      } 
      if (checkContactUri(uri, str)) {
        String str1;
        String str2;
        Cursor cursor6 = null;
        String str3 = null;
        Cursor cursor4 = null;
        Cursor cursor5 = null;
        String str4 = null;
        str = str3;
        Cursor cursor2 = cursor4;
        Cursor cursor1 = cursor6;
        Cursor cursor3 = cursor5;
        try {
          Cursor cursor;
          String str5;
          if (SdkLevel.getLevel() >= 12) {
            str = str3;
            cursor2 = cursor4;
            cursor1 = cursor6;
            cursor3 = cursor5;
            CONTACT_PROJECTION = HoneycombMR1Util.getContactProjection();
            str = str3;
            cursor2 = cursor4;
            cursor1 = cursor6;
            cursor3 = cursor5;
            cursor = this.activityContext.getContentResolver().query(uri, CONTACT_PROJECTION, null, null, null);
            Cursor cursor7 = cursor;
            cursor2 = cursor4;
            cursor1 = cursor;
            cursor3 = cursor5;
            str4 = postHoneycombGetContactNameAndPicture(cursor);
            cursor7 = cursor;
            cursor2 = cursor4;
            cursor1 = cursor;
            cursor3 = cursor5;
            DATA_PROJECTION = HoneycombMR1Util.getDataProjection();
            cursor7 = cursor;
            cursor2 = cursor4;
            cursor1 = cursor;
            cursor3 = cursor5;
            cursor4 = HoneycombMR1Util.getDataCursor(str4, this.activityContext, DATA_PROJECTION);
            cursor7 = cursor;
            cursor2 = cursor4;
            cursor1 = cursor;
            cursor3 = cursor4;
            postHoneycombGetContactEmailAndPhone(cursor4);
            cursor7 = cursor;
            cursor2 = cursor4;
            cursor1 = cursor;
            cursor3 = cursor4;
            this.contactUri = uri.toString();
          } else {
            Cursor cursor7 = cursor;
            cursor2 = cursor4;
            cursor1 = cursor6;
            cursor3 = cursor5;
            cursor = this.activityContext.getContentResolver().query(uri, PROJECTION, null, null, null);
            cursor7 = cursor;
            cursor2 = cursor4;
            cursor1 = cursor;
            cursor3 = cursor5;
            preHoneycombGetContactInfo(cursor, uri);
            str5 = str4;
          } 
          null = cursor;
          str1 = str5;
          cursor1 = cursor;
          str2 = str5;
          Log.i("ContactPicker", "Contact name = " + this.contactName + ", email address = " + this.emailAddress + ",contact Uri = " + this.contactUri + ", phone number = " + this.phoneNumber + ", contactPhotoUri = " + this.contactPictureUri);
          if (cursor != null)
            cursor.close(); 
          return;
        } catch (Exception exception) {
          cursor1 = null;
          str2 = str1;
          Log.i("ContactPicker", "checkContactUri failed: D");
          cursor1 = null;
          str2 = str1;
          puntContactSelection(1107);
          if (null != null)
            null.close(); 
          return;
        } finally {
          if (cursor1 != null)
            cursor1.close(); 
          if (str2 != null)
            str2.close(); 
        } 
      } 
    } else {
      return;
    } 
    AfterPicking();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/ContactPicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
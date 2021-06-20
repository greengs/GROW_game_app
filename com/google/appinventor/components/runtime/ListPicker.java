package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.Intent;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesActivities;
import com.google.appinventor.components.annotations.androidmanifest.ActivityElement;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ElementsUtil;
import com.google.appinventor.components.runtime.util.YailList;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "<p>A button that, when clicked on, displays a list of texts for the user to choose among. The texts can be specified through the Designer or Blocks Editor by setting the <code>ElementsFromString</code> property to their string-separated concatenation (for example, <em>choice 1, choice 2, choice 3</em>) or by setting the <code>Elements</code> property to a List in the Blocks editor.</p><p>Setting property ShowFilterBar to true, will make the list searchable.  Other properties affect the appearance of the button (<code>TextAlignment</code>, <code>BackgroundColor</code>, etc.) and whether it can be clicked on (<code>Enabled</code>).</p>", version = 9)
@SimpleObject
@UsesActivities(activities = {@ActivityElement(configChanges = "orientation|keyboardHidden", name = "com.google.appinventor.components.runtime.ListPickerActivity", screenOrientation = "behind")})
public class ListPicker extends Picker implements ActivityResultListener, Deleteable, OnResumeListener {
  private static final boolean DEFAULT_ENABLED = false;
  
  public static final int DEFAULT_ITEM_BACKGROUND_COLOR = -16777216;
  
  public static final int DEFAULT_ITEM_TEXT_COLOR = -1;
  
  static final String LIST_ACTIVITY_ANIM_TYPE;
  
  static final String LIST_ACTIVITY_ARG_NAME;
  
  static final String LIST_ACTIVITY_BACKGROUND_COLOR;
  
  private static final String LIST_ACTIVITY_CLASS = ListPickerActivity.class.getName();
  
  static final String LIST_ACTIVITY_ITEM_TEXT_COLOR;
  
  static final String LIST_ACTIVITY_ORIENTATION_TYPE;
  
  static final String LIST_ACTIVITY_RESULT_INDEX;
  
  static final String LIST_ACTIVITY_RESULT_NAME;
  
  static final String LIST_ACTIVITY_SHOW_SEARCH_BAR;
  
  static final String LIST_ACTIVITY_TITLE;
  
  private int itemBackgroundColor;
  
  private int itemTextColor;
  
  private YailList items = new YailList();
  
  private boolean resumedFromListFlag = false;
  
  private String selection;
  
  private int selectionIndex;
  
  private boolean showFilter = false;
  
  private String title = "";
  
  static {
    LIST_ACTIVITY_ARG_NAME = LIST_ACTIVITY_CLASS + ".list";
    LIST_ACTIVITY_RESULT_NAME = LIST_ACTIVITY_CLASS + ".selection";
    LIST_ACTIVITY_RESULT_INDEX = LIST_ACTIVITY_CLASS + ".index";
    LIST_ACTIVITY_ANIM_TYPE = LIST_ACTIVITY_CLASS + ".anim";
    LIST_ACTIVITY_SHOW_SEARCH_BAR = LIST_ACTIVITY_CLASS + ".search";
    LIST_ACTIVITY_TITLE = LIST_ACTIVITY_CLASS + ".title";
    LIST_ACTIVITY_ORIENTATION_TYPE = LIST_ACTIVITY_CLASS + ".orientation";
    LIST_ACTIVITY_ITEM_TEXT_COLOR = LIST_ACTIVITY_CLASS + ".itemtextcolor";
    LIST_ACTIVITY_BACKGROUND_COLOR = LIST_ACTIVITY_CLASS + ".backgroundcolor";
  }
  
  public ListPicker(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    SelectionIndex(0);
    this.itemTextColor = -1;
    this.itemBackgroundColor = -16777216;
    paramComponentContainer.$form().registerForOnResume(this);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public YailList Elements() {
    return this.items;
  }
  
  @SimpleProperty
  public void Elements(YailList paramYailList) {
    this.items = ElementsUtil.elements(paramYailList, "ListPicker");
  }
  
  @DesignerProperty(defaultValue = "", editorType = "textArea")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public void ElementsFromString(String paramString) {
    this.items = ElementsUtil.elementsFromString(paramString);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The background color of the ListPicker items.")
  public int ItemBackgroundColor() {
    return this.itemBackgroundColor;
  }
  
  @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
  @SimpleProperty
  public void ItemBackgroundColor(int paramInt) {
    this.itemBackgroundColor = paramInt;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The text color of the ListPicker items.")
  public int ItemTextColor() {
    return this.itemTextColor;
  }
  
  @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
  @SimpleProperty
  public void ItemTextColor(int paramInt) {
    this.itemTextColor = paramInt;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The selected item.  When directly changed by the programmer, the SelectionIndex property is also changed to the first item in the ListPicker with the given value.  If the value does not appear, SelectionIndex will be set to 0.")
  public String Selection() {
    return this.selection;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void Selection(String paramString) {
    this.selection = paramString;
    this.selectionIndex = ElementsUtil.setSelectedIndexFromValue(paramString, this.items);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The index of the currently selected item, starting at 1.  If no item is selected, the value will be 0.  If an attempt is made to set this to a number less than 1 or greater than the number of items in the ListPicker, SelectionIndex will be set to 0, and Selection will be set to the empty text.")
  public int SelectionIndex() {
    return this.selectionIndex;
  }
  
  @SimpleProperty
  public void SelectionIndex(int paramInt) {
    this.selectionIndex = ElementsUtil.selectionIndex(paramInt, this.items);
    this.selection = ElementsUtil.setSelectionFromIndex(paramInt, this.items);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void ShowFilterBar(boolean paramBoolean) {
    this.showFilter = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns current state of ShowFilterBar indicating if Search Filter Bar will be displayed on ListPicker or not")
  public boolean ShowFilterBar() {
    return this.showFilter;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Optional title displayed at the top of the list of choices.")
  public String Title() {
    return this.title;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void Title(String paramString) {
    this.title = paramString;
  }
  
  public Intent getIntent() {
    Intent intent = new Intent();
    intent.setClassName((Context)this.container.$context(), LIST_ACTIVITY_CLASS);
    intent.putExtra(LIST_ACTIVITY_ARG_NAME, this.items.toStringArray());
    intent.putExtra(LIST_ACTIVITY_SHOW_SEARCH_BAR, String.valueOf(this.showFilter));
    if (!this.title.equals(""))
      intent.putExtra(LIST_ACTIVITY_TITLE, this.title); 
    String str = this.container.$form().getOpenAnimType();
    intent.putExtra(LIST_ACTIVITY_ANIM_TYPE, str);
    intent.putExtra(LIST_ACTIVITY_ORIENTATION_TYPE, this.container.$form().ScreenOrientation());
    intent.putExtra(LIST_ACTIVITY_ITEM_TEXT_COLOR, this.itemTextColor);
    intent.putExtra(LIST_ACTIVITY_BACKGROUND_COLOR, this.itemBackgroundColor);
    return intent;
  }
  
  public void onDelete() {
    this.container.$form().unregisterForActivityResult(this);
  }
  
  public void onResume() {
    if (this.resumedFromListFlag) {
      this.container.$form().getWindow().setSoftInputMode(3);
      this.resumedFromListFlag = false;
    } 
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent) {
    if (paramInt1 == this.requestCode && paramInt2 == -1) {
      if (paramIntent.hasExtra(LIST_ACTIVITY_RESULT_NAME)) {
        this.selection = paramIntent.getStringExtra(LIST_ACTIVITY_RESULT_NAME);
      } else {
        this.selection = "";
      } 
      this.selectionIndex = paramIntent.getIntExtra(LIST_ACTIVITY_RESULT_INDEX, 0);
      AfterPicking();
      this.resumedFromListFlag = true;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/ListPicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
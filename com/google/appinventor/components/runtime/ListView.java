package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ElementsUtil;
import com.google.appinventor.components.runtime.util.YailList;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "<p>This is a visible component that displays a list of text elements. <br> The list can be set using the ElementsFromString property or using the Elements block in the blocks editor. </p>", iconName = "images/listView.png", nonVisible = false, version = 5)
@SimpleObject
public final class ListView extends AndroidViewComponent implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
  private static final int DEFAULT_BACKGROUND_COLOR = -16777216;
  
  private static final boolean DEFAULT_ENABLED = false;
  
  private static final int DEFAULT_SELECTION_COLOR = -3355444;
  
  private static final int DEFAULT_TEXT_COLOR = -1;
  
  private static final int DEFAULT_TEXT_SIZE = 22;
  
  private static final String LOG_TAG = "ListView";
  
  private static final Drawable UNSELECTED_DRAWABLE = (Drawable)new ColorDrawable(0);
  
  private ArrayAdapter<Spannable> adapter;
  
  private ArrayAdapter<Spannable> adapterCopy;
  
  private int backgroundColor;
  
  protected final ComponentContainer container;
  
  private YailList items;
  
  private View lastSelected;
  
  private final LinearLayout listViewLayout;
  
  private String selection;
  
  private int selectionColor;
  
  private Drawable selectionDrawable;
  
  private int selectionIndex;
  
  private boolean showFilter = false;
  
  private int textColor;
  
  private int textSize;
  
  private EditText txtSearchBox;
  
  private final android.widget.ListView view;
  
  public ListView(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    this.container = paramComponentContainer;
    this.items = YailList.makeEmptyList();
    SelectionIndex(0);
    this.view = new android.widget.ListView((Context)paramComponentContainer.$context());
    this.view.setOnItemClickListener(this);
    this.view.setOnItemSelectedListener(this);
    this.view.setChoiceMode(1);
    this.view.setScrollingCacheEnabled(false);
    this.view.setSelector((Drawable)new StateListDrawable());
    this.listViewLayout = new LinearLayout((Context)paramComponentContainer.$context());
    this.listViewLayout.setOrientation(1);
    this.txtSearchBox = new EditText((Context)paramComponentContainer.$context());
    this.txtSearchBox.setSingleLine(true);
    this.txtSearchBox.setWidth(-2);
    this.txtSearchBox.setPadding(10, 10, 10, 10);
    this.txtSearchBox.setHint("Search list...");
    if (!AppInventorCompatActivity.isClassicMode())
      this.txtSearchBox.setBackgroundColor(-1); 
    this.txtSearchBox.addTextChangedListener(new TextWatcher() {
          public void afterTextChanged(Editable param1Editable) {}
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            ListView.this.adapter.getFilter().filter(param1CharSequence);
          }
        });
    if (this.showFilter) {
      this.txtSearchBox.setVisibility(0);
    } else {
      this.txtSearchBox.setVisibility(8);
    } 
    Width(-2);
    BackgroundColor(-16777216);
    SelectionColor(-3355444);
    this.textColor = -1;
    TextColor(this.textColor);
    this.textSize = 22;
    TextSize(this.textSize);
    ElementsFromString("");
    this.listViewLayout.addView((View)this.txtSearchBox);
    this.listViewLayout.addView((View)this.view);
    this.listViewLayout.requestLayout();
    paramComponentContainer.$add(this);
  }
  
  private void updateSelectionIndex() {
    if (this.selectionIndex > 0) {
      View view = this.container.$form().getCurrentFocus();
      this.view.requestFocusFromTouch();
      this.view.setSelection(this.selectionIndex - 1);
      if (view != null)
        view.requestFocus(); 
      return;
    } 
    if (this.lastSelected != null) {
      this.lastSelected.setBackgroundDrawable(UNSELECTED_DRAWABLE);
      this.lastSelected = null;
      return;
    } 
  }
  
  @SimpleEvent(description = "Simple event to be raised after the an element has been chosen in the list. The selected element is available in the Selection property.")
  public void AfterPicking() {
    EventDispatcher.dispatchEvent(this, "AfterPicking", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of the listview background.")
  public int BackgroundColor() {
    return this.backgroundColor;
  }
  
  @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
  @SimpleProperty
  public void BackgroundColor(int paramInt) {
    this.backgroundColor = paramInt;
    setBackgroundColor(this.backgroundColor);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public YailList Elements() {
    return this.items;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "List of text elements to show in the ListView.  This will signal an error if the elements are not text strings.")
  public void Elements(YailList paramYailList) {
    this.items = ElementsUtil.elements(paramYailList, "Listview");
    setAdapterData();
  }
  
  @DesignerProperty(defaultValue = "", editorType = "textArea")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The TextView elements specified as a string with the items separated by commas such as: Cheese,Fruit,Bacon,Radish. Each word before the comma will be an element in the list.")
  public void ElementsFromString(String paramString) {
    this.items = ElementsUtil.elementsFromString(paramString);
    setAdapterData();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Determines the height of the list on the view.")
  public void Height(int paramInt) {
    int i = paramInt;
    if (paramInt == -1)
      i = -2; 
    super.Height(i);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the text last selected in the ListView.")
  public String Selection() {
    return this.selection;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void Selection(String paramString) {
    this.selection = paramString;
    this.selectionIndex = ElementsUtil.setSelectedIndexFromValue(paramString, this.items);
    updateSelectionIndex();
  }
  
  @SimpleProperty(description = "The color of the item when it is selected.")
  public int SelectionColor() {
    return this.selectionColor;
  }
  
  @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color")
  @SimpleProperty
  public void SelectionColor(int paramInt) {
    this.selectionColor = paramInt;
    this.selectionDrawable = (Drawable)new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] { paramInt, paramInt });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The index of the currently selected item, starting at 1.  If no item is selected, the value will be 0.  If an attempt is made to set this to a number less than 1 or greater than the number of items in the ListView, SelectionIndex will be set to 0, and Selection will be set to the empty text.")
  public int SelectionIndex() {
    return this.selectionIndex;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Specifies the position of the selected item in the ListView. This could be used to retrievethe text at the chosen position. If an attempt is made to set this to a number less than 1 or greater than the number of items in the ListView, SelectionIndex will be set to 0, and Selection will be set to the empty text.")
  public void SelectionIndex(int paramInt) {
    this.selectionIndex = ElementsUtil.selectionIndex(paramInt, this.items);
    this.selection = ElementsUtil.setSelectionFromIndex(paramInt, this.items);
    updateSelectionIndex();
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(description = "Sets visibility of ShowFilterBar. True will show the bar, False will hide it.")
  public void ShowFilterBar(boolean paramBoolean) {
    this.showFilter = paramBoolean;
    if (paramBoolean) {
      this.txtSearchBox.setVisibility(0);
      return;
    } 
    this.txtSearchBox.setVisibility(8);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns current state of ShowFilterBar for visibility.")
  public boolean ShowFilterBar() {
    return this.showFilter;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The text color of the listview items.")
  public int TextColor() {
    return this.textColor;
  }
  
  @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
  @SimpleProperty
  public void TextColor(int paramInt) {
    this.textColor = paramInt;
    setAdapterData();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The text size of the listview items.")
  public int TextSize() {
    return this.textSize;
  }
  
  @DesignerProperty(defaultValue = "22", editorType = "non_negative_integer")
  @SimpleProperty
  public void TextSize(int paramInt) {
    if (paramInt > 1000) {
      this.textSize = 999;
    } else {
      this.textSize = paramInt;
    } 
    setAdapterData();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Determines the width of the list on the view.")
  public void Width(int paramInt) {
    int i = paramInt;
    if (paramInt == -1)
      i = -2; 
    super.Width(i);
  }
  
  public View getView() {
    return (View)this.listViewLayout;
  }
  
  public Spannable[] itemsToColoredText() {
    int k = this.items.size();
    int j = this.textSize;
    Spannable[] arrayOfSpannable = new Spannable[k];
    for (int i = 1; i <= k; i++) {
      SpannableString spannableString = new SpannableString(YailList.YailListElementToString(this.items.get(i)));
      spannableString.setSpan(new ForegroundColorSpan(this.textColor), 0, spannableString.length(), 0);
      this.container.$form();
      if (!Form.getCompatibilityMode())
        j = (int)(this.textSize * this.container.$form().deviceDensity()); 
      spannableString.setSpan(new AbsoluteSizeSpan(j), 0, spannableString.length(), 0);
      arrayOfSpannable[i - 1] = (Spannable)spannableString;
    } 
    return arrayOfSpannable;
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    Spannable spannable = (Spannable)paramAdapterView.getAdapter().getItem(paramInt);
    this.selection = spannable.toString();
    this.selectionIndex = this.adapterCopy.getPosition(spannable) + 1;
    if (this.lastSelected != null)
      this.lastSelected.setBackgroundDrawable(UNSELECTED_DRAWABLE); 
    paramView.setBackgroundDrawable(this.selectionDrawable);
    this.lastSelected = paramView;
    AfterPicking();
  }
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    onItemClick(paramAdapterView, paramView, paramInt, paramLong);
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
  
  public void setAdapterData() {
    this.adapter = new ArrayAdapter((Context)this.container.$context(), 17367043, (Object[])itemsToColoredText());
    this.view.setAdapter((ListAdapter)this.adapter);
    this.adapterCopy = new ArrayAdapter((Context)this.container.$context(), 17367043);
    for (int i = 0; i < this.adapter.getCount(); i++)
      this.adapterCopy.insert(this.adapter.getItem(i), i); 
  }
  
  public void setBackgroundColor(int paramInt) {
    this.backgroundColor = paramInt;
    this.view.setBackgroundColor(this.backgroundColor);
    this.listViewLayout.setBackgroundColor(this.backgroundColor);
    this.view.setCacheColorHint(this.backgroundColor);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/ListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.appinventor.components.runtime.util.AnimationUtil;

public class ListPickerActivity extends AppInventorCompatActivity implements AdapterView.OnItemClickListener {
  static int backgroundColor;
  
  static int itemColor;
  
  MyAdapter adapter;
  
  private String closeAnim = "";
  
  private ListView listView;
  
  EditText txtSearchBox;
  
  public void onBackPressed() {
    AnimationUtil.ApplyCloseScreenAnimation(this, this.closeAnim);
    super.onBackPressed();
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    styleTitleBar();
    LinearLayout linearLayout = new LinearLayout((Context)this);
    linearLayout.setOrientation(1);
    Intent intent = getIntent();
    if (intent.hasExtra(ListPicker.LIST_ACTIVITY_ANIM_TYPE))
      this.closeAnim = intent.getStringExtra(ListPicker.LIST_ACTIVITY_ANIM_TYPE); 
    if (intent.hasExtra(ListPicker.LIST_ACTIVITY_ORIENTATION_TYPE)) {
      String str = intent.getStringExtra(ListPicker.LIST_ACTIVITY_ORIENTATION_TYPE).toLowerCase();
      if (str.equals("portrait")) {
        setRequestedOrientation(1);
      } else if (str.equals("landscape")) {
        setRequestedOrientation(0);
      } 
    } 
    if (intent.hasExtra(ListPicker.LIST_ACTIVITY_TITLE))
      setTitle(intent.getStringExtra(ListPicker.LIST_ACTIVITY_TITLE)); 
    if (intent.hasExtra(ListPicker.LIST_ACTIVITY_ARG_NAME)) {
      String[] arrayOfString = getIntent().getStringArrayExtra(ListPicker.LIST_ACTIVITY_ARG_NAME);
      this.listView = new ListView((Context)this);
      this.listView.setOnItemClickListener(this);
      this.listView.setScrollingCacheEnabled(false);
      itemColor = intent.getIntExtra(ListPicker.LIST_ACTIVITY_ITEM_TEXT_COLOR, -1);
      backgroundColor = intent.getIntExtra(ListPicker.LIST_ACTIVITY_BACKGROUND_COLOR, -16777216);
      linearLayout.setBackgroundColor(backgroundColor);
      this.adapter = new MyAdapter((Context)this, arrayOfString);
      this.listView.setAdapter((ListAdapter)this.adapter);
      String str = intent.getStringExtra(ListPicker.LIST_ACTIVITY_SHOW_SEARCH_BAR);
      this.txtSearchBox = new EditText((Context)this);
      this.txtSearchBox.setSingleLine(true);
      this.txtSearchBox.setWidth(-2);
      this.txtSearchBox.setPadding(10, 10, 10, 10);
      this.txtSearchBox.setHint("Search list...");
      if (!isClassicMode())
        this.txtSearchBox.setBackgroundColor(-1); 
      if (str == null || !str.equalsIgnoreCase("true"))
        this.txtSearchBox.setVisibility(8); 
      this.txtSearchBox.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {}
            
            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
            
            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
              ListPickerActivity.this.adapter.getFilter().filter(param1CharSequence);
            }
          });
    } else {
      setResult(0);
      finish();
      AnimationUtil.ApplyCloseScreenAnimation(this, this.closeAnim);
    } 
    linearLayout.addView((View)this.txtSearchBox);
    linearLayout.addView((View)this.listView);
    setContentView((View)linearLayout);
    linearLayout.requestLayout();
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    getWindow().setSoftInputMode(3);
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    String str = (String)paramAdapterView.getAdapter().getItem(paramInt);
    Intent intent = new Intent();
    intent.putExtra(ListPicker.LIST_ACTIVITY_RESULT_NAME, str);
    intent.putExtra(ListPicker.LIST_ACTIVITY_RESULT_INDEX, paramInt + 1);
    this.closeAnim = str;
    setResult(-1, intent);
    finish();
    AnimationUtil.ApplyCloseScreenAnimation(this, this.closeAnim);
  }
  
  private static class MyAdapter extends ArrayAdapter<String> {
    private final Context mContext;
    
    public MyAdapter(Context param1Context, String[] param1ArrayOfString) {
      super(param1Context, 17367040, (Object[])param1ArrayOfString);
      this.mContext = param1Context;
    }
    
    public long getItemId(int param1Int) {
      return ((String)getItem(param1Int)).hashCode();
    }
    
    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      TextView textView2 = (TextView)param1View;
      TextView textView1 = textView2;
      if (textView2 == null)
        textView1 = (TextView)LayoutInflater.from(this.mContext).inflate(17367043, param1ViewGroup, false); 
      textView1.setText((CharSequence)getItem(param1Int));
      textView1.setTextColor(ListPickerActivity.itemColor);
      return (View)textView1;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/ListPickerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
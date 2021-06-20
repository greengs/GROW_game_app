package com.google.appinventor.components.runtime;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public class TableLayout implements Layout {
  private final Handler handler;
  
  private final android.widget.TableLayout layoutManager;
  
  private int numColumns;
  
  private int numRows;
  
  TableLayout(Context paramContext, int paramInt1, int paramInt2) {
    this.layoutManager = new android.widget.TableLayout(paramContext);
    this.numColumns = paramInt1;
    this.numRows = paramInt2;
    this.handler = new Handler();
    int i;
    for (i = 0; i < paramInt2; i++) {
      TableRow tableRow = new TableRow(paramContext);
      int j;
      for (j = 0; j < paramInt1; j++)
        tableRow.addView(newEmptyCellView(), j, (ViewGroup.LayoutParams)newEmptyCellLayoutParams()); 
      this.layoutManager.addView((View)tableRow, i, (ViewGroup.LayoutParams)new android.widget.TableLayout.LayoutParams());
    } 
  }
  
  private void addChild(AndroidViewComponent paramAndroidViewComponent) {
    View view;
    int i = paramAndroidViewComponent.Row();
    int j = paramAndroidViewComponent.Column();
    if (i == -1 || j == -1) {
      addChildLater(paramAndroidViewComponent);
      return;
    } 
    if (i >= 0 && i < this.numRows) {
      if (j >= 0 && j < this.numColumns) {
        TableRow tableRow = (TableRow)this.layoutManager.getChildAt(i);
        tableRow.removeViewAt(j);
        view = paramAndroidViewComponent.getView();
        tableRow.addView(view, j, view.getLayoutParams());
        return;
      } 
      Log.e("TableLayout", "Child has illegal Column property: " + view);
      return;
    } 
    Log.e("TableLayout", "Child has illegal Row property: " + view);
  }
  
  private void addChildLater(final AndroidViewComponent child) {
    this.handler.post(new Runnable() {
          public void run() {
            TableLayout.this.addChild(child);
          }
        });
  }
  
  private static TableRow.LayoutParams newCellLayoutParams() {
    return new TableRow.LayoutParams();
  }
  
  private static TableRow.LayoutParams newEmptyCellLayoutParams() {
    return new TableRow.LayoutParams(0, 0);
  }
  
  private View newEmptyCellView() {
    return (View)new TextView(this.layoutManager.getContext());
  }
  
  public void add(AndroidViewComponent paramAndroidViewComponent) {
    paramAndroidViewComponent.getView().setLayoutParams((ViewGroup.LayoutParams)newCellLayoutParams());
    addChildLater(paramAndroidViewComponent);
  }
  
  public ViewGroup getLayoutManager() {
    return (ViewGroup)this.layoutManager;
  }
  
  int getNumColumns() {
    return this.numColumns;
  }
  
  int getNumRows() {
    return this.numRows;
  }
  
  void setNumColumns(int paramInt) {
    if (paramInt > this.numColumns) {
      this.layoutManager.getContext();
      for (int i = 0; i < this.numRows; i++) {
        TableRow tableRow = (TableRow)this.layoutManager.getChildAt(i);
        for (int j = this.numColumns; j < paramInt; j++)
          tableRow.addView(newEmptyCellView(), j, (ViewGroup.LayoutParams)newEmptyCellLayoutParams()); 
      } 
      this.numColumns = paramInt;
      return;
    } 
    if (paramInt < this.numColumns) {
      for (int i = 0; i < this.numRows; i++)
        ((TableRow)this.layoutManager.getChildAt(i)).removeViews(paramInt, this.numColumns - paramInt); 
      this.numColumns = paramInt;
      return;
    } 
  }
  
  void setNumRows(int paramInt) {
    if (paramInt > this.numRows) {
      Context context = this.layoutManager.getContext();
      for (int i = this.numRows; i < paramInt; i++) {
        TableRow tableRow = new TableRow(context);
        for (int j = 0; j < this.numColumns; j++)
          tableRow.addView(newEmptyCellView(), j, (ViewGroup.LayoutParams)newEmptyCellLayoutParams()); 
        this.layoutManager.addView((View)tableRow, i, (ViewGroup.LayoutParams)new android.widget.TableLayout.LayoutParams());
      } 
      this.numRows = paramInt;
      return;
    } 
    if (paramInt < this.numRows) {
      this.layoutManager.removeViews(paramInt, this.numRows - paramInt);
      this.numRows = paramInt;
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/TableLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
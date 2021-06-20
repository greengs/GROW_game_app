package com.google.appinventor.components.runtime.view;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.appinventor.components.runtime.util.ViewUtil;
import org.osmdroid.views.MapView;

public class ZoomControlView extends LinearLayout {
  private float density;
  
  private final MapView parent;
  
  private final Button zoomIn;
  
  private final Button zoomOut;
  
  public ZoomControlView(MapView paramMapView) {
    super(paramMapView.getContext());
    this.density = (paramMapView.getContext().getResources().getDisplayMetrics()).density;
    this.parent = paramMapView;
    setOrientation(1);
    this.zoomIn = new Button(paramMapView.getContext());
    this.zoomOut = new Button(paramMapView.getContext());
    initButton(this.zoomIn, "+");
    initButton(this.zoomOut, "−");
    this.zoomIn.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            ZoomControlView.this.parent.getController().zoomIn();
          }
        });
    this.zoomOut.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            ZoomControlView.this.parent.getController().zoomOut();
          }
        });
    ViewUtil.setBackgroundDrawable((View)this.zoomIn, getZoomInDrawable(this.density));
    ViewUtil.setBackgroundDrawable((View)this.zoomOut, getZoomOutDrawable(this.density));
    int[][] arrayOfInt = new int[2][];
    (new int[1])[0] = -16842910;
    arrayOfInt[0] = new int[1];
    (new int[1])[0] = 16842910;
    arrayOfInt[1] = new int[1];
    int[] arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -3355444;
    arrayOfInt1[1] = -16777216;
    this.zoomIn.setTextColor(new ColorStateList(arrayOfInt, arrayOfInt1));
    this.zoomOut.setTextColor(new ColorStateList(arrayOfInt, arrayOfInt1));
    addView((View)this.zoomIn);
    addView((View)this.zoomOut);
    setPadding((int)(this.density * 12.0F), (int)(this.density * 12.0F), 0, 0);
    updateButtons();
  }
  
  private static Drawable getZoomInDrawable(float paramFloat) {
    int i = (int)(4.0F * paramFloat);
    ShapeDrawable shapeDrawable = new ShapeDrawable((Shape)new RoundRectShape(new float[] { i, i, i, i, 0.0F, 0.0F, 0.0F, 0.0F }, null, null));
    shapeDrawable.getPaint().setColor(-1);
    return (Drawable)shapeDrawable;
  }
  
  private static Drawable getZoomOutDrawable(float paramFloat) {
    int i = (int)(4.0F * paramFloat);
    ShapeDrawable shapeDrawable = new ShapeDrawable((Shape)new RoundRectShape(new float[] { 0.0F, 0.0F, 0.0F, 0.0F, i, i, i, i }, null, null));
    shapeDrawable.getPaint().setColor(-1);
    return (Drawable)shapeDrawable;
  }
  
  private void initButton(Button paramButton, String paramString) {
    paramButton.setText(paramString);
    paramButton.setTextSize(22.0F);
    paramButton.setPadding(0, 0, 0, 0);
    paramButton.setWidth((int)(this.density * 30.0F));
    paramButton.setHeight((int)(this.density * 30.0F));
    paramButton.setSingleLine();
    paramButton.setGravity(17);
  }
  
  public final void updateButtons() {
    this.zoomIn.setEnabled(this.parent.canZoomIn());
    this.zoomOut.setEnabled(this.parent.canZoomOut());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/view/ZoomControlView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
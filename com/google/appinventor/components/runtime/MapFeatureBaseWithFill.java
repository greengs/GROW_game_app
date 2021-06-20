package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.MapFactory;

@SimpleObject
public abstract class MapFeatureBaseWithFill extends MapFeatureBase implements MapFactory.HasFill {
  private int fillColor = -65536;
  
  private float fillOpacity = 1.0F;
  
  public MapFeatureBaseWithFill(MapFactory.MapFeatureContainer paramMapFeatureContainer, MapFactory.MapFeatureVisitor<Double> paramMapFeatureVisitor) {
    super(paramMapFeatureContainer, paramMapFeatureVisitor);
    FillColor(-65536);
    FillOpacity(1.0F);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The paint color used to fill in the %type%.")
  public int FillColor() {
    return this.fillColor;
  }
  
  @DesignerProperty(defaultValue = "&HFFFF0000", editorType = "color")
  @SimpleProperty
  public void FillColor(int paramInt) {
    this.fillColor = paramInt;
    this.map.getController().updateFeatureFill(this);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The opacity of the interior of the map feature.")
  public float FillOpacity() {
    return this.fillOpacity;
  }
  
  @DesignerProperty(defaultValue = "1.0", editorType = "float")
  @SimpleProperty
  public void FillOpacity(float paramFloat) {
    this.fillOpacity = paramFloat;
    this.fillColor = this.fillColor & 0xFFFFFF | Math.round(255.0F * paramFloat) << 24;
    this.map.getController().updateFeatureFill(this);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/MapFeatureBaseWithFill.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
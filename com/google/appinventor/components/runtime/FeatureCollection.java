package com.google.appinventor.components.runtime;

import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import org.json.JSONException;

@DesignerComponent(category = ComponentCategory.MAPS, description = "A FeatureCollection contains one or more map features as a group. Any events fired on a feature in the collection will also trigger the corresponding event on the collection object. FeatureCollections can be loaded from external resources as a means of populating a Map with content.", version = 2)
@SimpleObject
public class FeatureCollection extends MapFeatureContainerBase implements MapFactory.MapFeatureCollection {
  private Map map;
  
  private String source = "";
  
  public FeatureCollection(MapFactory.MapFeatureContainer paramMapFeatureContainer) {
    super((ComponentContainer)paramMapFeatureContainer);
    this.map = paramMapFeatureContainer.getMap();
  }
  
  @DesignerProperty(editorType = "textArea")
  @SimpleProperty(description = "Loads a collection of features from the given string. If the string is not valid GeoJSON, the ErrorLoadingFeatureCollection error will be run with url = <string>.")
  public void FeaturesFromGeoJSON(String paramString) {
    try {
      processGeoJSON("<string>", paramString);
      return;
    } catch (JSONException jSONException) {
      $form().dispatchErrorOccurredEvent(this, "FeaturesFromGeoJSON", 3411, new Object[] { jSONException.getMessage() });
      return;
    } 
  }
  
  @SimpleEvent(description = "A GeoJSON document was successfully read from url. The features specified in the document are provided as a list in features.")
  public void GotFeatures(String paramString, YailList paramYailList) {
    this.source = paramString;
    super.GotFeatures(paramString, paramYailList);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Specifies the source URL used to populate the feature collection. If the feature collection was not loaded from a URL, this will be the empty string.")
  public String Source() {
    return this.source;
  }
  
  @DesignerProperty(editorType = "geojson_type")
  public void Source(String paramString) {
    this.source = paramString;
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "visibility")
  @SimpleProperty(description = "Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.")
  public void Visible(boolean paramBoolean) {
    getMap().getController().setFeatureCollectionVisible(this, paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public boolean Visible() {
    return getMap().getController().isFeatureCollectionVisible(this);
  }
  
  public Map getMap() {
    return this.map;
  }
  
  public View getView() {
    return null;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/FeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
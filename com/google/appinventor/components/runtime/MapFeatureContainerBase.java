package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.util.Log;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;

@SimpleObject
public abstract class MapFeatureContainerBase extends AndroidViewComponent implements MapFactory.MapFeatureContainer {
  private static final int ERROR_CODE_IO_EXCEPTION = -2;
  
  private static final int ERROR_CODE_MALFORMED_GEOJSON = -3;
  
  private static final int ERROR_CODE_MALFORMED_URL = -1;
  
  private static final int ERROR_CODE_UNKNOWN_TYPE = -4;
  
  private static final String ERROR_IO_EXCEPTION = "Unable to download content from URL";
  
  private static final String ERROR_MALFORMED_GEOJSON = "Malformed GeoJSON response. Expected FeatureCollection as root element.";
  
  private static final String ERROR_MALFORMED_URL = "The URL is malformed";
  
  private static final String ERROR_UNKNOWN_TYPE = "Unrecognized/invalid type in JSON object";
  
  private static final String GEOJSON_FEATURECOLLECTION = "FeatureCollection";
  
  private static final String GEOJSON_FEATURES = "features";
  
  private static final String GEOJSON_GEOMETRYCOLLECTION = "GeometryCollection";
  
  private static final String GEOJSON_TYPE = "type";
  
  private static final String TAG = MapFeatureContainerBase.class.getSimpleName();
  
  private final MapFactory.MapFeatureVisitor<Void> featureAdder = new MapFactory.MapFeatureVisitor<Void>() {
      public Void visit(MapFactory.MapCircle param1MapCircle, Object... param1VarArgs) {
        MapFeatureContainerBase.this.addFeature(param1MapCircle);
        return null;
      }
      
      public Void visit(MapFactory.MapLineString param1MapLineString, Object... param1VarArgs) {
        MapFeatureContainerBase.this.addFeature(param1MapLineString);
        return null;
      }
      
      public Void visit(MapFactory.MapMarker param1MapMarker, Object... param1VarArgs) {
        MapFeatureContainerBase.this.addFeature(param1MapMarker);
        return null;
      }
      
      public Void visit(MapFactory.MapPolygon param1MapPolygon, Object... param1VarArgs) {
        MapFeatureContainerBase.this.addFeature(param1MapPolygon);
        return null;
      }
      
      public Void visit(MapFactory.MapRectangle param1MapRectangle, Object... param1VarArgs) {
        MapFeatureContainerBase.this.addFeature(param1MapRectangle);
        return null;
      }
    };
  
  protected List<MapFactory.MapFeature> features = new CopyOnWriteArrayList<MapFactory.MapFeature>();
  
  protected MapFeatureContainerBase(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
  }
  
  private String loadUrl(final String url) {
    try {
      URLConnection uRLConnection = (new URL(url)).openConnection();
      uRLConnection.connect();
      if (uRLConnection instanceof HttpURLConnection) {
        HttpURLConnection httpURLConnection = (HttpURLConnection)uRLConnection;
        final int responseCode = httpURLConnection.getResponseCode();
        final String responseMessage = httpURLConnection.getResponseMessage();
        if (i != 200) {
          $form().runOnUiThread(new Runnable() {
                public void run() {
                  MapFeatureContainerBase.this.LoadError(url, responseCode, responseMessage);
                }
              });
          httpURLConnection.disconnect();
          return null;
        } 
      } 
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRLConnection.getInputStream(), "UTF-8"));
      StringBuilder stringBuilder = new StringBuilder();
      while (true) {
        final String responseMessage = bufferedReader.readLine();
        if (str != null) {
          stringBuilder.append(str);
          stringBuilder.append("\n");
          continue;
        } 
        bufferedReader.close();
        return stringBuilder.toString();
      } 
    } catch (MalformedURLException malformedURLException) {
      $form().runOnUiThread(new Runnable() {
            public void run() {
              MapFeatureContainerBase.this.LoadError(url, -1, "The URL is malformed");
            }
          });
      return null;
    } catch (IOException iOException) {
      $form().runOnUiThread(new Runnable() {
            public void run() {
              MapFeatureContainerBase.this.LoadError(url, -2, "Unable to download content from URL");
            }
          });
      return null;
    } 
  }
  
  private void performGet(String paramString) {
    try {
      String str = loadUrl(paramString);
      if (str == null)
        return; 
      processGeoJSON(paramString, str);
      return;
    } catch (Exception exception) {
      Log.e(TAG, "Exception retreiving GeoJSON", exception);
      $form().dispatchErrorOccurredEvent(this, "LoadFromURL", -4, new Object[] { exception.toString() });
      return;
    } 
  }
  
  public void $add(AndroidViewComponent paramAndroidViewComponent) {
    throw new UnsupportedOperationException("Map.$add() called");
  }
  
  public Activity $context() {
    return this.container.$context();
  }
  
  public Form $form() {
    return this.container.$form();
  }
  
  @SimpleEvent(description = "The user clicked on a map feature.")
  public void FeatureClick(MapFactory.MapFeature paramMapFeature) {
    EventDispatcher.dispatchEvent(this, "FeatureClick", new Object[] { paramMapFeature });
    if (getMap() != this)
      getMap().FeatureClick(paramMapFeature); 
  }
  
  @SimpleEvent(description = "The user dragged a map feature.")
  public void FeatureDrag(MapFactory.MapFeature paramMapFeature) {
    EventDispatcher.dispatchEvent(this, "FeatureDrag", new Object[] { paramMapFeature });
    if (getMap() != this)
      getMap().FeatureDrag(paramMapFeature); 
  }
  
  @SimpleFunction
  public Object FeatureFromDescription(YailList paramYailList) {
    try {
      return GeoJSONUtil.processGeoJSONFeature(TAG, this, paramYailList);
    } catch (IllegalArgumentException illegalArgumentException) {
      $form().dispatchErrorOccurredEvent(this, "FeatureFromDescription", -3, new Object[] { illegalArgumentException.getMessage() });
      return illegalArgumentException.getMessage();
    } 
  }
  
  @SimpleEvent(description = "The user long-pressed on a map feature.")
  public void FeatureLongClick(MapFactory.MapFeature paramMapFeature) {
    EventDispatcher.dispatchEvent(this, "FeatureLongClick", new Object[] { paramMapFeature });
    if (getMap() != this)
      getMap().FeatureLongClick(paramMapFeature); 
  }
  
  @SimpleEvent(description = "The user started dragging a map feature.")
  public void FeatureStartDrag(MapFactory.MapFeature paramMapFeature) {
    EventDispatcher.dispatchEvent(this, "FeatureStartDrag", new Object[] { paramMapFeature });
    if (getMap() != this)
      getMap().FeatureStartDrag(paramMapFeature); 
  }
  
  @SimpleEvent(description = "The user stopped dragging a map feature.")
  public void FeatureStopDrag(MapFactory.MapFeature paramMapFeature) {
    EventDispatcher.dispatchEvent(this, "FeatureStopDrag", new Object[] { paramMapFeature });
    if (getMap() != this)
      getMap().FeatureStopDrag(paramMapFeature); 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The list of features placed on this %type%. This list also includes any features created by calls to FeatureFromDescription")
  public YailList Features() {
    return YailList.makeList(this.features);
  }
  
  @SimpleProperty
  public void Features(YailList paramYailList) {
    Iterator<MapFactory.MapFeature> iterator = this.features.iterator();
    while (iterator.hasNext())
      ((MapFactory.MapFeature)iterator.next()).removeFromMap(); 
    this.features.clear();
    ListIterator<Iterator<MapFactory.MapFeature>> listIterator = paramYailList.listIterator(1);
    while (listIterator.hasNext()) {
      iterator = listIterator.next();
      if (iterator instanceof MapFactory.MapFeature)
        addFeature((MapFactory.MapFeature)iterator); 
    } 
    getMap().getView().invalidate();
  }
  
  @SimpleEvent(description = "A GeoJSON document was successfully read from url. The features specified in the document are provided as a list in features.")
  public void GotFeatures(String paramString, YailList paramYailList) {
    if (!EventDispatcher.dispatchEvent(this, "GotFeatures", new Object[] { paramString, paramYailList })) {
      Iterator<YailList> iterator = paramYailList.iterator();
      iterator.next();
      while (iterator.hasNext())
        FeatureFromDescription(iterator.next()); 
    } 
  }
  
  @SimpleEvent(description = "An error was encountered while processing a GeoJSON document at the given url. The responseCode parameter will contain an HTTP status code and the errorMessage parameter will contain a detailed error message.")
  public void LoadError(String paramString1, int paramInt, String paramString2) {
    if (!EventDispatcher.dispatchEvent(this, "LoadError", new Object[] { paramString1, Integer.valueOf(paramInt), paramString2 })) {
      if (paramString1.startsWith("file:")) {
        $form().dispatchErrorOccurredEvent(this, "LoadFromURL", 2102, new Object[] { paramString1 });
        return;
      } 
    } else {
      return;
    } 
    $form().dispatchErrorOccurredEvent(this, "LoadFromURL", 1101, new Object[] { paramString1 });
  }
  
  @SimpleFunction(description = "<p>Load a feature collection in <a href=\"https://en.wikipedia.org/wiki/GeoJSON\">GeoJSON</a> format from the given url. On success, the event GotFeatures will be raised with the given url and a list of the features parsed from the GeoJSON as a list of (key, value) pairs. On failure, the LoadError event will be raised with any applicable HTTP response code and error message.</p>")
  public void LoadFromURL(final String url) {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            MapFeatureContainerBase.this.performGet(url);
          }
        });
  }
  
  void addFeature(MapFactory.MapCircle paramMapCircle) {
    this.features.add(paramMapCircle);
    getMap().addFeature(paramMapCircle);
  }
  
  public void addFeature(MapFactory.MapFeature paramMapFeature) {
    paramMapFeature.accept(this.featureAdder, new Object[0]);
  }
  
  void addFeature(MapFactory.MapLineString paramMapLineString) {
    this.features.add(paramMapLineString);
    getMap().addFeature(paramMapLineString);
  }
  
  void addFeature(MapFactory.MapMarker paramMapMarker) {
    this.features.add(paramMapMarker);
    getMap().addFeature(paramMapMarker);
  }
  
  void addFeature(MapFactory.MapPolygon paramMapPolygon) {
    this.features.add(paramMapPolygon);
    getMap().addFeature(paramMapPolygon);
  }
  
  void addFeature(MapFactory.MapRectangle paramMapRectangle) {
    this.features.add(paramMapRectangle);
    getMap().addFeature(paramMapRectangle);
  }
  
  public Iterator<MapFactory.MapFeature> iterator() {
    return this.features.iterator();
  }
  
  protected void processGeoJSON(final String url, String paramString2) throws JSONException {
    String str = GeoJSONUtil.getGeoJSONType(paramString2, "type");
    if (!"FeatureCollection".equals(str) && !"GeometryCollection".equals(str)) {
      $form().runOnUiThread(new Runnable() {
            public void run() {
              MapFeatureContainerBase.this.LoadError(url, -3, "Malformed GeoJSON response. Expected FeatureCollection as root element.");
            }
          });
      return;
    } 
    final List yailFeatures = GeoJSONUtil.getGeoJSONFeatures(TAG, paramString2);
    $form().runOnUiThread(new Runnable() {
          public void run() {
            MapFeatureContainerBase.this.GotFeatures(url, YailList.makeList(yailFeatures));
          }
        });
  }
  
  public void removeFeature(MapFactory.MapFeature paramMapFeature) {
    this.features.remove(paramMapFeature);
    getMap().removeFeature(paramMapFeature);
  }
  
  public void setChildHeight(AndroidViewComponent paramAndroidViewComponent, int paramInt) {
    throw new UnsupportedOperationException("Map.setChildHeight called");
  }
  
  public void setChildWidth(AndroidViewComponent paramAndroidViewComponent, int paramInt) {
    throw new UnsupportedOperationException("Map.setChildWidth called");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/MapFeatureContainerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
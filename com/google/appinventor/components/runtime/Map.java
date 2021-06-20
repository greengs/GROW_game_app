package com.google.appinventor.components.runtime;

import android.util.Log;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesAssets;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.IOException;
import java.util.List;
import org.osmdroid.util.BoundingBox;

@DesignerComponent(androidMinSdk = 8, category = ComponentCategory.MAPS, description = "<p>A two-dimensional container that renders map tiles in the background and allows for multiple Marker elements to identify points on the map. Map tiles are supplied by OpenStreetMap contributors and the United States Geological Survey.</p><p>The Map component provides three utilities for manipulating its boundaries within App Inventor. First, a locking mechanism is provided to allow the map to be moved relative to other components on the Screen. Second, when unlocked, the user can pan the Map to any location. At this new location, the &quot;Set Initial Boundary&quot; button can be pressed to save the current Map coordinates to its properties. Lastly, if the Map is moved to a different location, for example to add Markers off-screen, then the &quot;Reset Map to Initial Bounds&quot; button can be used to re-center the Map at the starting location.</p>", version = 5)
@SimpleObject
@UsesAssets(fileNames = "location.png, marker.svg")
@UsesLibraries(libraries = "osmdroid.aar, osmdroid.jar, androidsvg.jar, jts.jar")
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_COARSE_LOCATION, android.permission.ACCESS_WIFI_STATE, android.permission.ACCESS_NETWORK_STATE, android.permission.WRITE_EXTERNAL_STORAGE, android.permission.READ_EXTERNAL_STORAGE")
public class Map extends MapFeatureContainerBase implements MapFactory.MapEventListener {
  private static final String ERROR_INVALID_NUMBER = "%s is not a valid number.";
  
  private static final String ERROR_LATITUDE_OUT_OF_BOUNDS = "Latitude %f is out of bounds.";
  
  private static final String ERROR_LONGITUDE_OUT_OF_BOUNDS = "Longitude %f is out of bounds.";
  
  private static final String TAG = Map.class.getSimpleName();
  
  private MapFactory.MapController mapController = null;
  
  private LocationSensor sensor = null;
  
  public Map(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    Log.d(TAG, "Map.<init>");
    paramComponentContainer.$add(this);
    Width(176);
    Height(144);
    CenterFromString("42.359144, -71.093612");
    ZoomLevel(13);
    EnableZoom(true);
    EnablePan(true);
    MapType(1);
    ShowCompass(false);
    LocationSensor(new LocationSensor(paramComponentContainer.$form(), false));
    ShowUser(false);
    ShowZoom(false);
    EnableRotation(false);
    ShowScale(false);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Bounding box for the map stored as [[North, West], [South, East]].")
  public YailList BoundingBox() {
    BoundingBox boundingBox = this.mapController.getBoundingBox();
    return YailList.makeList((Object[])new YailList[] { YailList.makeList((Object[])new Double[] { Double.valueOf(boundingBox.getLatNorth()), Double.valueOf(boundingBox.getLonWest()) }), YailList.makeList((Object[])new Double[] { Double.valueOf(boundingBox.getLatSouth()), Double.valueOf(boundingBox.getLonEast()) }) });
  }
  
  @SimpleProperty
  public void BoundingBox(YailList paramYailList) {
    double d1 = GeometryUtil.coerceToDouble(((YailList)paramYailList.get(1)).get(1));
    double d2 = GeometryUtil.coerceToDouble(((YailList)paramYailList.get(1)).get(2));
    double d3 = GeometryUtil.coerceToDouble(((YailList)paramYailList.get(2)).get(1));
    double d4 = GeometryUtil.coerceToDouble(((YailList)paramYailList.get(2)).get(2));
    this.mapController.setBoundingBox(new BoundingBox(d1, d4, d3, d2));
  }
  
  @SimpleEvent(description = "User has changed the map bounds by panning or zooming the map.")
  public void BoundsChange() {
    EventDispatcher.dispatchEvent(this, "BoundsChange", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "42.359144, -71.093612", editorType = "geographic_point")
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "<p>Set the initial center coordinate of the map. The value is specified as a comma-separated pair of decimal latitude and longitude coordinates, for example, <code>42.359144, -71.093612</code>.</p><p>In blocks code, it is recommended for performance reasons to use SetCenter with numerical latitude and longitude rather than convert to the string representation for use with this property.</p>")
  public void CenterFromString(String paramString) {
    String[] arrayOfString = paramString.split(",");
    if (arrayOfString.length != 2) {
      Log.e(TAG, paramString + " is not a valid point.");
      InvalidPoint(paramString + " is not a valid point.");
      return;
    } 
    try {
      double d = Double.parseDouble(arrayOfString[0].trim());
      try {
        double d1 = Double.parseDouble(arrayOfString[1].trim());
        if (d > 90.0D || d < -90.0D) {
          InvalidPoint(String.format("Latitude %f is out of bounds.", new Object[] { Double.valueOf(d) }));
          return;
        } 
      } catch (NumberFormatException numberFormatException) {
        Log.e(TAG, String.format("%s is not a valid number.", new Object[] { arrayOfString[1] }));
        InvalidPoint(String.format("%s is not a valid number.", new Object[] { arrayOfString[1] }));
      } 
    } catch (NumberFormatException numberFormatException) {
      Log.e(TAG, String.format("%s is not a valid number.", new Object[] { arrayOfString[0] }));
      InvalidPoint(String.format("%s is not a valid number.", new Object[] { arrayOfString[0] }));
      return;
    } 
  }
  
  @SimpleFunction(description = "Create a new marker with default properties at the specified latitude and longitude.")
  public Marker CreateMarker(double paramDouble1, double paramDouble2) {
    Marker marker = new Marker(this);
    marker.SetLocation(paramDouble1, paramDouble2);
    return marker;
  }
  
  @SimpleEvent(description = "The user double-tapped at a point on the map. This event will be followed by a ZoomChanged event if zooming gestures are enabled and the map is not at the highest possible zoom level.")
  public void DoubleTapAtPoint(double paramDouble1, double paramDouble2) {
    EventDispatcher.dispatchEvent(this, "DoubleTapAtPoint", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void EnablePan(boolean paramBoolean) {
    this.mapController.setPanEnabled(paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Enable two-finger panning of the Map")
  public boolean EnablePan() {
    return this.mapController.isPanEnabled();
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void EnableRotation(boolean paramBoolean) {
    this.mapController.setRotationEnabled(paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If set to true, the user can use multitouch gestures to rotate the map around its current center.")
  public boolean EnableRotation() {
    return this.mapController.isRotationEnabled();
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void EnableZoom(boolean paramBoolean) {
    this.mapController.setZoomEnabled(paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If this property is set to true, multitouch zoom gestures are allowed on the map. Otherwise, the map zoom cannot be changed by the user except via the zoom control buttons.")
  public boolean EnableZoom() {
    return this.mapController.isZoomEnabled();
  }
  
  @SimpleProperty
  public YailList Features() {
    return super.Features();
  }
  
  @SimpleEvent(description = "An invalid coordinate was supplied during a maps operation. The message parameter will have more details about the issue.")
  public void InvalidPoint(String paramString) {
    EventDispatcher.dispatchEvent(this, "InvalidPoint", new Object[] { paramString });
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The latitude of the center of the map.")
  public double Latitude() {
    return this.mapController.getLatitude();
  }
  
  public LocationSensor LocationSensor() {
    return this.sensor;
  }
  
  @DesignerProperty(editorType = "component:com.google.appinventor.components.runtime.LocationSensor")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Uses the provided LocationSensor for user location data rather than the built-in location provider.")
  public void LocationSensor(LocationSensor paramLocationSensor) {
    LocationSensor.LocationSensorListener locationSensorListener = this.mapController.getLocationListener();
    if (this.sensor != null)
      this.sensor.removeListener(locationSensorListener); 
    this.sensor = paramLocationSensor;
    if (this.sensor != null)
      this.sensor.addListener(locationSensorListener); 
  }
  
  @SimpleEvent(description = "The user long-pressed at a point on the map.")
  public void LongPressAtPoint(double paramDouble1, double paramDouble2) {
    EventDispatcher.dispatchEvent(this, "LongPressAtPoint", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The longitude of the center of the map.")
  public double Longitude() {
    return this.mapController.getLongitude();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The type of tile layer to use as the base of the map. Valid values are: 1 (Roads), 2 (Aerial), 3 (Terrain)")
  public int MapType() {
    return this.mapController.getMapType().ordinal();
  }
  
  @DesignerProperty(defaultValue = "1", editorType = "map_type")
  @SimpleProperty
  public void MapType(int paramInt) {
    MapFactory.MapType mapType = MapFactory.MapType.values()[paramInt];
    this.mapController.setMapType(mapType);
  }
  
  @SimpleFunction(description = "Pans the map center to the given latitude and longitude and adjust the zoom level to the specified zoom.")
  public void PanTo(double paramDouble1, double paramDouble2, int paramInt) {
    this.mapController.panTo(paramDouble1, paramDouble2, paramInt, 1.0D);
  }
  
  @SimpleEvent(description = "Map has been initialized and is ready for user interaction.")
  public void Ready() {
    EventDispatcher.dispatchEvent(this, "Ready", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Sets or gets the rotation of the map in decimal degrees if any")
  public float Rotation() {
    return this.mapController.getRotation();
  }
  
  @DesignerProperty(defaultValue = "0.0", editorType = "float")
  @SimpleProperty
  public void Rotation(float paramFloat) {
    this.mapController.setRotation(paramFloat);
  }
  
  @SimpleFunction(description = "Save the contents of the Map to the specified path.")
  public void Save(final String path) {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            try {
              GeoJSONUtil.writeFeaturesAsGeoJSON(featuresToSave, path);
              return;
            } catch (IOException iOException) {
              final Form form = Map.this.$form();
              form.runOnUiThread(new Runnable() {
                    public void run() {
                      form.dispatchErrorOccurredEvent(Map.this, "Save", 3412, new Object[] { this.val$e.getMessage() });
                    }
                  });
              return;
            } 
          }
        });
  }
  
  @SimpleProperty
  public int ScaleUnits() {
    switch (this.mapController.getScaleUnits()) {
      default:
        return 0;
      case METRIC:
        return 1;
      case IMPERIAL:
        break;
    } 
    return 2;
  }
  
  @DesignerProperty(defaultValue = "1", editorType = "map_unit_system")
  @SimpleProperty
  public void ScaleUnits(int paramInt) {
    if (1 <= paramInt && paramInt < (MapFactory.MapScaleUnits.values()).length) {
      this.mapController.setScaleUnits(MapFactory.MapScaleUnits.values()[paramInt]);
      return;
    } 
    $form().dispatchErrorOccurredEvent(this, "ScaleUnits", 3421, new Object[] { Integer.valueOf(paramInt) });
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void ShowCompass(boolean paramBoolean) {
    this.mapController.setCompassEnabled(paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Show a compass icon rotated based on user orientation.")
  public boolean ShowCompass() {
    return this.mapController.isCompassEnabled();
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void ShowScale(boolean paramBoolean) {
    this.mapController.setScaleVisible(paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Shows a scale reference on the map.")
  public boolean ShowScale() {
    return this.mapController.isScaleVisible();
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void ShowUser(boolean paramBoolean) {
    this.mapController.setShowUserEnabled(paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Show the user's location on the map.")
  public boolean ShowUser() {
    return this.mapController.isShowUserEnabled();
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void ShowZoom(boolean paramBoolean) {
    this.mapController.setZoomControlEnabled(paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Show zoom buttons on the map.")
  public boolean ShowZoom() {
    return this.mapController.isZoomControlEnabled();
  }
  
  @SimpleEvent(description = "The user tapped at a point on the map.")
  public void TapAtPoint(double paramDouble1, double paramDouble2) {
    EventDispatcher.dispatchEvent(this, "TapAtPoint", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the user's latitude if ShowUser is enabled.")
  public double UserLatitude() {
    return (this.sensor == null) ? -999.0D : this.sensor.Latitude();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the user's longitude if ShowUser is enabled.")
  public double UserLongitude() {
    return (this.sensor == null) ? -999.0D : this.sensor.Longitude();
  }
  
  @SimpleEvent(description = "User has changed the zoom level of the map.")
  public void ZoomChange() {
    EventDispatcher.dispatchEvent(this, "ZoomChange", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The zoom level of the map. Valid values of ZoomLevel are dependent on the tile provider and the latitude and longitude of the map. For example, zoom levels are more constrained over oceans than dense city centers to conserve space for storing tiles, so valid values may be 1-7 over ocean and 1-18 over cities. Tile providers may send warning or error tiles if the zoom level is too great for the server to support.")
  public int ZoomLevel() {
    return this.mapController.getZoom();
  }
  
  @DesignerProperty(defaultValue = "13", editorType = "map_zoom")
  @SimpleProperty
  public void ZoomLevel(int paramInt) {
    this.mapController.setZoom(paramInt);
  }
  
  void addFeature(MapFactory.MapCircle paramMapCircle) {
    this.features.add(paramMapCircle);
    paramMapCircle.setMap(this);
    this.mapController.addFeature(paramMapCircle);
  }
  
  void addFeature(MapFactory.MapLineString paramMapLineString) {
    this.features.add(paramMapLineString);
    paramMapLineString.setMap(this);
    this.mapController.addFeature(paramMapLineString);
  }
  
  void addFeature(MapFactory.MapMarker paramMapMarker) {
    this.features.add(paramMapMarker);
    paramMapMarker.setMap(this);
    this.mapController.addFeature(paramMapMarker);
  }
  
  void addFeature(MapFactory.MapPolygon paramMapPolygon) {
    this.features.add(paramMapPolygon);
    paramMapPolygon.setMap(this);
    this.mapController.addFeature(paramMapPolygon);
  }
  
  void addFeature(MapFactory.MapRectangle paramMapRectangle) {
    this.features.add(paramMapRectangle);
    paramMapRectangle.setMap(this);
    this.mapController.addFeature(paramMapRectangle);
  }
  
  public MapFactory.MapController getController() {
    return this.mapController;
  }
  
  public Map getMap() {
    return this;
  }
  
  public View getView() {
    if (this.mapController == null) {
      this.mapController = MapFactory.newMap(this.container.$form());
      this.mapController.addEventListener(this);
    } 
    return this.mapController.getView();
  }
  
  public void onBoundsChanged() {
    this.container.$form().runOnUiThread(new Runnable() {
          public void run() {
            Map.this.BoundsChange();
          }
        });
  }
  
  public void onDoubleTap(final double latitude, final double longitude) {
    this.container.$form().runOnUiThread(new Runnable() {
          public void run() {
            Map.this.DoubleTapAtPoint(latitude, longitude);
          }
        });
  }
  
  public void onFeatureClick(final MapFactory.MapFeature feature) {
    this.container.$form().runOnUiThread(new Runnable() {
          public void run() {
            feature.Click();
          }
        });
  }
  
  public void onFeatureDrag(final MapFactory.MapFeature feature) {
    this.container.$form().runOnUiThread(new Runnable() {
          public void run() {
            feature.Drag();
          }
        });
  }
  
  public void onFeatureLongPress(final MapFactory.MapFeature feature) {
    this.container.$form().runOnUiThread(new Runnable() {
          public void run() {
            feature.LongClick();
          }
        });
  }
  
  public void onFeatureStartDrag(final MapFactory.MapFeature feature) {
    this.container.$form().runOnUiThread(new Runnable() {
          public void run() {
            feature.StartDrag();
          }
        });
  }
  
  public void onFeatureStopDrag(final MapFactory.MapFeature feature) {
    this.container.$form().runOnUiThread(new Runnable() {
          public void run() {
            feature.StopDrag();
          }
        });
  }
  
  public void onLongPress(final double latitude, final double longitude) {
    this.container.$form().runOnUiThread(new Runnable() {
          public void run() {
            Map.this.LongPressAtPoint(latitude, longitude);
          }
        });
  }
  
  public void onReady(MapFactory.MapController paramMapController) {
    this.container.$form().runOnUiThread(new Runnable() {
          public void run() {
            Map.this.Ready();
          }
        });
  }
  
  public void onSingleTap(final double latitude, final double longitude) {
    this.container.$form().runOnUiThread(new Runnable() {
          public void run() {
            Map.this.TapAtPoint(latitude, longitude);
          }
        });
  }
  
  public void onZoom() {
    this.container.$form().runOnUiThread(new Runnable() {
          public void run() {
            Map.this.ZoomChange();
          }
        });
  }
  
  public void removeFeature(MapFactory.MapFeature paramMapFeature) {
    this.features.remove(paramMapFeature);
    this.mapController.removeFeature(paramMapFeature);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Map.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
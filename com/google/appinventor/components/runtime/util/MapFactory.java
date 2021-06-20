package com.google.appinventor.components.runtime.util;

import android.os.Build;
import android.view.View;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.LocationSensor;
import com.google.appinventor.components.runtime.Map;
import java.util.Iterator;
import java.util.List;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;

public final class MapFactory {
  public static MapController newMap(Form paramForm) {
    return (MapController)((Build.VERSION.SDK_INT < 8) ? new DummyMapController() : new NativeOpenStreetMapController(paramForm));
  }
  
  public static interface HasFill {
    int FillColor();
    
    void FillColor(int param1Int);
    
    float FillOpacity();
    
    void FillOpacity(float param1Float);
  }
  
  public static interface HasStroke {
    int StrokeColor();
    
    void StrokeColor(int param1Int);
    
    float StrokeOpacity();
    
    void StrokeOpacity(float param1Float);
    
    int StrokeWidth();
    
    void StrokeWidth(int param1Int);
  }
  
  public static interface MapCircle extends MapFeature, HasFill, HasStroke {
    double Latitude();
    
    void Latitude(double param1Double);
    
    double Longitude();
    
    void Longitude(double param1Double);
    
    double Radius();
    
    void Radius(double param1Double);
    
    void SetLocation(double param1Double1, double param1Double2);
    
    void updateCenter(double param1Double1, double param1Double2);
  }
  
  public static interface MapController {
    void addEventListener(MapFactory.MapEventListener param1MapEventListener);
    
    void addFeature(MapFactory.MapCircle param1MapCircle);
    
    void addFeature(MapFactory.MapLineString param1MapLineString);
    
    void addFeature(MapFactory.MapMarker param1MapMarker);
    
    void addFeature(MapFactory.MapPolygon param1MapPolygon);
    
    void addFeature(MapFactory.MapRectangle param1MapRectangle);
    
    BoundingBox getBoundingBox();
    
    double getLatitude();
    
    LocationSensor.LocationSensorListener getLocationListener();
    
    double getLongitude();
    
    MapFactory.MapType getMapType();
    
    int getOverlayCount();
    
    float getRotation();
    
    MapFactory.MapScaleUnits getScaleUnits();
    
    View getView();
    
    int getZoom();
    
    void hideFeature(MapFactory.MapFeature param1MapFeature);
    
    void hideInfobox(MapFactory.MapFeature param1MapFeature);
    
    boolean isCompassEnabled();
    
    boolean isFeatureCollectionVisible(MapFactory.MapFeatureCollection param1MapFeatureCollection);
    
    boolean isFeatureVisible(MapFactory.MapFeature param1MapFeature);
    
    boolean isInfoboxVisible(MapFactory.MapFeature param1MapFeature);
    
    boolean isPanEnabled();
    
    boolean isRotationEnabled();
    
    boolean isScaleVisible();
    
    boolean isShowUserEnabled();
    
    boolean isZoomControlEnabled();
    
    boolean isZoomEnabled();
    
    void panTo(double param1Double1, double param1Double2, int param1Int, double param1Double3);
    
    void removeFeature(MapFactory.MapFeature param1MapFeature);
    
    void setBoundingBox(BoundingBox param1BoundingBox);
    
    void setCenter(double param1Double1, double param1Double2);
    
    void setCompassEnabled(boolean param1Boolean);
    
    void setFeatureCollectionVisible(MapFactory.MapFeatureCollection param1MapFeatureCollection, boolean param1Boolean);
    
    void setMapType(MapFactory.MapType param1MapType);
    
    void setPanEnabled(boolean param1Boolean);
    
    void setRotation(float param1Float);
    
    void setRotationEnabled(boolean param1Boolean);
    
    void setScaleUnits(MapFactory.MapScaleUnits param1MapScaleUnits);
    
    void setScaleVisible(boolean param1Boolean);
    
    void setShowUserEnabled(boolean param1Boolean);
    
    void setZoom(int param1Int);
    
    void setZoomControlEnabled(boolean param1Boolean);
    
    void setZoomEnabled(boolean param1Boolean);
    
    void showFeature(MapFactory.MapFeature param1MapFeature);
    
    void showInfobox(MapFactory.MapFeature param1MapFeature);
    
    void updateFeatureDraggable(MapFactory.MapFeature param1MapFeature);
    
    void updateFeatureFill(MapFactory.HasFill param1HasFill);
    
    void updateFeatureHoles(MapFactory.MapPolygon param1MapPolygon);
    
    void updateFeatureImage(MapFactory.MapMarker param1MapMarker);
    
    void updateFeaturePosition(MapFactory.MapCircle param1MapCircle);
    
    void updateFeaturePosition(MapFactory.MapLineString param1MapLineString);
    
    void updateFeaturePosition(MapFactory.MapMarker param1MapMarker);
    
    void updateFeaturePosition(MapFactory.MapPolygon param1MapPolygon);
    
    void updateFeaturePosition(MapFactory.MapRectangle param1MapRectangle);
    
    void updateFeatureSize(MapFactory.MapMarker param1MapMarker);
    
    void updateFeatureStroke(MapFactory.HasStroke param1HasStroke);
    
    void updateFeatureText(MapFactory.MapFeature param1MapFeature);
  }
  
  public static interface MapEventListener {
    void onBoundsChanged();
    
    void onDoubleTap(double param1Double1, double param1Double2);
    
    void onFeatureClick(MapFactory.MapFeature param1MapFeature);
    
    void onFeatureDrag(MapFactory.MapFeature param1MapFeature);
    
    void onFeatureLongPress(MapFactory.MapFeature param1MapFeature);
    
    void onFeatureStartDrag(MapFactory.MapFeature param1MapFeature);
    
    void onFeatureStopDrag(MapFactory.MapFeature param1MapFeature);
    
    void onLongPress(double param1Double1, double param1Double2);
    
    void onReady(MapFactory.MapController param1MapController);
    
    void onSingleTap(double param1Double1, double param1Double2);
    
    void onZoom();
  }
  
  public static interface MapFeature extends Component {
    void Click();
    
    String Description();
    
    void Description(String param1String);
    
    void Drag();
    
    void Draggable(boolean param1Boolean);
    
    boolean Draggable();
    
    void EnableInfobox(boolean param1Boolean);
    
    boolean EnableInfobox();
    
    void HideInfobox();
    
    void LongClick();
    
    void ShowInfobox();
    
    void StartDrag();
    
    void StopDrag();
    
    String Title();
    
    void Title(String param1String);
    
    String Type();
    
    void Visible(boolean param1Boolean);
    
    boolean Visible();
    
    <T> T accept(MapFactory.MapFeatureVisitor<T> param1MapFeatureVisitor, Object... param1VarArgs);
    
    GeoPoint getCentroid();
    
    Geometry getGeometry();
    
    void removeFromMap();
    
    void setMap(MapFactory.MapFeatureContainer param1MapFeatureContainer);
  }
  
  public static interface MapFeatureCollection extends MapFeatureContainer {
    YailList Features();
    
    void GotFeatures(String param1String, YailList param1YailList);
    
    void LoadError(String param1String1, int param1Int, String param1String2);
    
    void LoadFromURL(String param1String);
    
    String Source();
    
    void Source(String param1String);
    
    boolean Visible();
  }
  
  public static interface MapFeatureContainer extends ComponentContainer, Iterable<MapFeature> {
    void FeatureClick(MapFactory.MapFeature param1MapFeature);
    
    void FeatureDrag(MapFactory.MapFeature param1MapFeature);
    
    void FeatureLongClick(MapFactory.MapFeature param1MapFeature);
    
    void FeatureStartDrag(MapFactory.MapFeature param1MapFeature);
    
    void FeatureStopDrag(MapFactory.MapFeature param1MapFeature);
    
    YailList Features();
    
    void Features(YailList param1YailList);
    
    void addFeature(MapFactory.MapFeature param1MapFeature);
    
    Map getMap();
    
    Iterator<MapFactory.MapFeature> iterator();
    
    void removeFeature(MapFactory.MapFeature param1MapFeature);
  }
  
  public static final class MapFeatureType {
    public static final String TYPE_CIRCLE = "Circle";
    
    public static final String TYPE_LINESTRING = "LineString";
    
    public static final String TYPE_MARKER = "Marker";
    
    public static final String TYPE_MULTILINESTRING = "MultiLineString";
    
    public static final String TYPE_MULTIPOINT = "MultiPoint";
    
    public static final String TYPE_MULTIPOLYGON = "MultiPolygon";
    
    public static final String TYPE_POINT = "Point";
    
    public static final String TYPE_POLYGON = "Polygon";
    
    public static final String TYPE_RECTANGLE = "Rectangle";
  }
  
  public static interface MapFeatureVisitor<T> {
    T visit(MapFactory.MapCircle param1MapCircle, Object... param1VarArgs);
    
    T visit(MapFactory.MapLineString param1MapLineString, Object... param1VarArgs);
    
    T visit(MapFactory.MapMarker param1MapMarker, Object... param1VarArgs);
    
    T visit(MapFactory.MapPolygon param1MapPolygon, Object... param1VarArgs);
    
    T visit(MapFactory.MapRectangle param1MapRectangle, Object... param1VarArgs);
  }
  
  public static interface MapLineString extends MapFeature, HasStroke {
    YailList Points();
    
    void Points(YailList param1YailList);
    
    List<GeoPoint> getPoints();
    
    void updatePoints(List<GeoPoint> param1List);
  }
  
  public static interface MapMarker extends MapFeature, HasFill, HasStroke {
    int AnchorHorizontal();
    
    void AnchorHorizontal(int param1Int);
    
    int AnchorVertical();
    
    void AnchorVertical(int param1Int);
    
    int Height();
    
    void Height(int param1Int);
    
    String ImageAsset();
    
    void ImageAsset(String param1String);
    
    double Latitude();
    
    void Latitude(double param1Double);
    
    double Longitude();
    
    void Longitude(double param1Double);
    
    void SetLocation(double param1Double1, double param1Double2);
    
    void ShowShadow(boolean param1Boolean);
    
    boolean ShowShadow();
    
    int Width();
    
    void Width(int param1Int);
    
    IGeoPoint getLocation();
    
    void updateLocation(double param1Double1, double param1Double2);
  }
  
  public static interface MapPolygon extends MapFeature, HasFill, HasStroke {
    YailList HolePoints();
    
    void HolePoints(YailList param1YailList);
    
    YailList Points();
    
    void Points(YailList param1YailList);
    
    List<List<List<GeoPoint>>> getHolePoints();
    
    List<List<GeoPoint>> getPoints();
    
    void updateHolePoints(List<List<List<GeoPoint>>> param1List);
    
    void updatePoints(List<List<GeoPoint>> param1List);
  }
  
  public static interface MapRectangle extends MapFeature, HasFill, HasStroke {
    YailList Bounds();
    
    YailList Center();
    
    double EastLongitude();
    
    void EastLongitude(double param1Double);
    
    double NorthLatitude();
    
    void NorthLatitude(double param1Double);
    
    void SetCenter(double param1Double1, double param1Double2);
    
    double SouthLatitude();
    
    void SouthLatitude(double param1Double);
    
    double WestLongitude();
    
    void WestLongitude(double param1Double);
    
    void updateBounds(double param1Double1, double param1Double2, double param1Double3, double param1Double4);
  }
  
  public enum MapScaleUnits {
    IMPERIAL, METRIC, UNKNOWN;
    
    static {
      IMPERIAL = new MapScaleUnits("IMPERIAL", 2);
      $VALUES = new MapScaleUnits[] { UNKNOWN, METRIC, IMPERIAL };
    }
  }
  
  public enum MapType {
    AERIAL, ROADS, TERRAIN, UNKNOWN;
    
    static {
      AERIAL = new MapType("AERIAL", 2);
      TERRAIN = new MapType("TERRAIN", 3);
      $VALUES = new MapType[] { UNKNOWN, ROADS, AERIAL, TERRAIN };
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/MapFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
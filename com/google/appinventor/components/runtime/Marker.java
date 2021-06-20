package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;

@DesignerComponent(category = ComponentCategory.MAPS, description = "<p>An icon positioned at a point to indicate information on a map. Markers can be used to provide an info window, custom fill and stroke colors, and custom images to convey information to the user.</p>", version = 3)
@SimpleObject
@UsesLibraries(libraries = "osmdroid.aar, androidsvg.jar")
public class Marker extends MapFeatureBaseWithFill implements MapFactory.MapMarker {
  private static final String TAG = Marker.class.getSimpleName();
  
  private static final MapFactory.MapFeatureVisitor<Double> bearingComputation;
  
  private static final MapFactory.MapFeatureVisitor<Double> distanceComputation = new MapFactory.MapFeatureVisitor<Double>() {
      public Double visit(MapFactory.MapCircle param1MapCircle, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids((Marker)param1VarArgs[0], param1MapCircle)) : Double.valueOf(GeometryUtil.distanceBetweenEdges((Marker)param1VarArgs[0], param1MapCircle));
      }
      
      public Double visit(MapFactory.MapLineString param1MapLineString, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids((Marker)param1VarArgs[0], param1MapLineString)) : Double.valueOf(GeometryUtil.distanceBetweenEdges((Marker)param1VarArgs[0], param1MapLineString));
      }
      
      public Double visit(MapFactory.MapMarker param1MapMarker, Object... param1VarArgs) {
        return Double.valueOf(GeometryUtil.distanceBetween((Marker)param1VarArgs[0], param1MapMarker));
      }
      
      public Double visit(MapFactory.MapPolygon param1MapPolygon, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids((Marker)param1VarArgs[0], param1MapPolygon)) : Double.valueOf(GeometryUtil.distanceBetweenEdges((Marker)param1VarArgs[0], param1MapPolygon));
      }
      
      public Double visit(MapFactory.MapRectangle param1MapRectangle, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids((Marker)param1VarArgs[0], param1MapRectangle)) : Double.valueOf(GeometryUtil.distanceBetweenEdges((Marker)param1VarArgs[0], param1MapRectangle));
      }
    };
  
  private int anchorHAlign = 3;
  
  private int anchorVAlign = 3;
  
  private int height = -1;
  
  private String imagePath = "";
  
  private GeoPoint location = new GeoPoint(0.0D, 0.0D);
  
  private int width = -1;
  
  static {
    bearingComputation = new MapFactory.MapFeatureVisitor<Double>() {
        public Double visit(MapFactory.MapCircle param1MapCircle, Object... param1VarArgs) {
          return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.bearingToCentroid((MapFactory.MapMarker)param1VarArgs[0], param1MapCircle)) : Double.valueOf(GeometryUtil.bearingToEdge((MapFactory.MapMarker)param1VarArgs[0], param1MapCircle));
        }
        
        public Double visit(MapFactory.MapLineString param1MapLineString, Object... param1VarArgs) {
          return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.bearingToCentroid((MapFactory.MapMarker)param1VarArgs[0], param1MapLineString)) : Double.valueOf(GeometryUtil.bearingToEdge((MapFactory.MapMarker)param1VarArgs[0], param1MapLineString));
        }
        
        public Double visit(MapFactory.MapMarker param1MapMarker, Object... param1VarArgs) {
          return Double.valueOf(GeometryUtil.bearingTo((Marker)param1VarArgs[0], param1MapMarker));
        }
        
        public Double visit(MapFactory.MapPolygon param1MapPolygon, Object... param1VarArgs) {
          return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.bearingToCentroid((MapFactory.MapMarker)param1VarArgs[0], param1MapPolygon)) : Double.valueOf(GeometryUtil.bearingToEdge((MapFactory.MapMarker)param1VarArgs[0], param1MapPolygon));
        }
        
        public Double visit(MapFactory.MapRectangle param1MapRectangle, Object... param1VarArgs) {
          return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.bearingToCentroid((MapFactory.MapMarker)param1VarArgs[0], param1MapRectangle)) : Double.valueOf(GeometryUtil.bearingToEdge((MapFactory.MapMarker)param1VarArgs[0], param1MapRectangle));
        }
      };
  }
  
  public Marker(MapFactory.MapFeatureContainer paramMapFeatureContainer) {
    super(paramMapFeatureContainer, distanceComputation);
    paramMapFeatureContainer.addFeature(this);
    ShowShadow(false);
    AnchorHorizontal(3);
    AnchorVertical(3);
    ImageAsset("");
    Width(-1);
    Height(-1);
    Latitude(0.0D);
    Longitude(0.0D);
  }
  
  @SimpleProperty(description = "The horizontal alignment property controls where the Marker's anchor is located relative to its width.")
  public int AnchorHorizontal() {
    return this.anchorHAlign;
  }
  
  @DesignerProperty(defaultValue = "3", editorType = "horizontal_alignment")
  @SimpleProperty
  public void AnchorHorizontal(int paramInt) {
    if (paramInt == this.anchorHAlign)
      return; 
    if (paramInt > 3 || paramInt < 1) {
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "AnchorHorizontal", 3417, new Object[] { Integer.valueOf(paramInt) });
      return;
    } 
    this.anchorHAlign = paramInt;
    this.map.getController().updateFeaturePosition(this);
  }
  
  @SimpleProperty(description = "The vertical alignment property controls where the Marker's anchor is located relative to its height.")
  public int AnchorVertical() {
    return this.anchorVAlign;
  }
  
  @DesignerProperty(defaultValue = "3", editorType = "vertical_alignment")
  @SimpleProperty
  public void AnchorVertical(int paramInt) {
    if (paramInt == this.anchorVAlign)
      return; 
    if (paramInt > 3 || paramInt < 1) {
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "AnchorVertical", 3416, new Object[] { Integer.valueOf(paramInt) });
      return;
    } 
    this.anchorVAlign = paramInt;
    this.map.getController().updateFeaturePosition(this);
  }
  
  @SimpleFunction(description = "Returns the bearing from the Marker to the given map feature, in degrees from due north. If the centroids parameter is true, the bearing will be to the center of the map feature. Otherwise, the bearing will be computed to the point in the feature nearest the Marker.")
  public double BearingToFeature(MapFactory.MapFeature paramMapFeature, boolean paramBoolean) {
    return (paramMapFeature == null) ? -1.0D : ((Double)paramMapFeature.accept(bearingComputation, new Object[] { this, Boolean.valueOf(paramBoolean) })).doubleValue();
  }
  
  @SimpleFunction(description = "Returns the bearing from the Marker to the given latitude and longitude, in degrees from due north.")
  public double BearingToPoint(double paramDouble1, double paramDouble2) {
    return this.location.bearingTo((IGeoPoint)new GeoPoint(paramDouble1, paramDouble2));
  }
  
  @SimpleFunction(description = "Compute the distance, in meters, between a Marker and a latitude, longitude point.")
  public double DistanceToPoint(double paramDouble1, double paramDouble2) {
    return GeometryUtil.distanceBetween(this, new GeoPoint(paramDouble1, paramDouble2));
  }
  
  public double DistanceToPoint(double paramDouble1, double paramDouble2, boolean paramBoolean) {
    return DistanceToPoint(paramDouble1, paramDouble2);
  }
  
  @SimpleProperty
  public int Height() {
    return (this.height == -2) ? this.map.getView().getHeight() : ((this.height < -1000) ? (int)((-this.height - 1000.0D) / 100.0D * this.map.getView().getHeight()) : this.height);
  }
  
  @SimpleProperty
  public void Height(int paramInt) {
    this.height = paramInt;
    this.map.getController().updateFeatureSize(this);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void HeightPercent(int paramInt) {
    this.height = -1000 - paramInt;
    this.map.getController().updateFeatureSize(this);
  }
  
  @SimpleProperty(description = "The ImageAsset property is used to provide an alternative image for the Marker.")
  public String ImageAsset() {
    return this.imagePath;
  }
  
  @DesignerProperty(editorType = "asset")
  @SimpleProperty
  public void ImageAsset(String paramString) {
    Log.d(TAG, "ImageAsset");
    this.imagePath = paramString;
    this.map.getController().updateFeatureImage(this);
  }
  
  @SimpleProperty
  public double Latitude() {
    return this.location.getLatitude();
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "latitude")
  @SimpleProperty
  public void Latitude(double paramDouble) {
    Log.d(TAG, "Latitude");
    if (paramDouble < -90.0D || paramDouble > 90.0D) {
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "Latitude", 3413, new Object[] { Double.valueOf(paramDouble) });
      return;
    } 
    this.location.setLatitude(paramDouble);
    clearGeometry();
    this.map.getController().updateFeaturePosition(this);
  }
  
  @SimpleProperty
  public double Longitude() {
    return this.location.getLongitude();
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "longitude")
  @SimpleProperty
  public void Longitude(double paramDouble) {
    Log.d(TAG, "Longitude");
    if (paramDouble < -180.0D || paramDouble > 180.0D) {
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "Longitude", 3414, new Object[] { Double.valueOf(paramDouble) });
      return;
    } 
    this.location.setLongitude(paramDouble);
    clearGeometry();
    this.map.getController().updateFeaturePosition(this);
  }
  
  @SimpleFunction(description = "Set the location of the marker.")
  public void SetLocation(double paramDouble1, double paramDouble2) {
    Log.d(TAG, "SetLocation");
    this.location.setCoords(paramDouble1, paramDouble2);
    clearGeometry();
    this.map.getController().updateFeaturePosition(this);
  }
  
  @SimpleProperty(userVisible = false)
  public void ShowShadow(boolean paramBoolean) {}
  
  @SimpleProperty(description = "Gets whether or not the shadow of the Marker is shown.")
  public boolean ShowShadow() {
    return false;
  }
  
  @SimpleProperty
  public void StrokeColor(int paramInt) {
    super.StrokeColor(paramInt);
    this.map.getController().updateFeatureStroke(this);
  }
  
  @SimpleProperty
  public String Type() {
    return "Marker";
  }
  
  @SimpleProperty
  public int Width() {
    return (this.width == -2) ? this.map.getView().getWidth() : ((this.width < -1000) ? (int)((-this.width - 1000.0D) / 100.0D * this.map.getView().getWidth()) : this.width);
  }
  
  @SimpleProperty
  public void Width(int paramInt) {
    this.width = paramInt;
    this.map.getController().updateFeatureSize(this);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void WidthPercent(int paramInt) {
    this.width = -1000 - paramInt;
    this.map.getController().updateFeatureSize(this);
  }
  
  public <T> T accept(MapFactory.MapFeatureVisitor<T> paramMapFeatureVisitor, Object... paramVarArgs) {
    return (T)paramMapFeatureVisitor.visit(this, paramVarArgs);
  }
  
  protected Geometry computeGeometry() {
    return GeometryUtil.createGeometry(this.location);
  }
  
  public IGeoPoint getLocation() {
    return (IGeoPoint)this.location;
  }
  
  public void updateLocation(double paramDouble1, double paramDouble2) {
    this.location = new GeoPoint(paramDouble1, paramDouble2);
    clearGeometry();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Marker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
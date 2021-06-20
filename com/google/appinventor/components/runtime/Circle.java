package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.util.GeoPoint;

@DesignerComponent(category = ComponentCategory.MAPS, description = "Circle", version = 2)
@SimpleObject
public class Circle extends PolygonBase implements MapFactory.MapCircle {
  private static final MapFactory.MapFeatureVisitor<Double> distanceComputation = new MapFactory.MapFeatureVisitor<Double>() {
      public Double visit(MapFactory.MapCircle param1MapCircle, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapCircle, (Circle)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapCircle, (Circle)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapLineString param1MapLineString, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapLineString, (Circle)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapLineString, (Circle)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapMarker param1MapMarker, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapMarker, (Circle)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapMarker, (Circle)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapPolygon param1MapPolygon, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapPolygon, (Circle)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapPolygon, (Circle)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapRectangle param1MapRectangle, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids((Circle)param1VarArgs[0], param1MapRectangle)) : Double.valueOf(GeometryUtil.distanceBetweenEdges((Circle)param1VarArgs[0], param1MapRectangle));
      }
    };
  
  private GeoPoint center = new GeoPoint(0.0D, 0.0D);
  
  private double latitude;
  
  private double longitude;
  
  private double radius;
  
  public Circle(MapFactory.MapFeatureContainer paramMapFeatureContainer) {
    super(paramMapFeatureContainer, distanceComputation);
    paramMapFeatureContainer.addFeature(this);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The latitude of the center of the circle.")
  public double Latitude() {
    return this.latitude;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "latitude")
  @SimpleProperty
  public void Latitude(double paramDouble) {
    if (GeometryUtil.isValidLatitude(paramDouble)) {
      this.latitude = paramDouble;
      this.center.setLatitude(paramDouble);
      clearGeometry();
      this.map.getController().updateFeaturePosition(this);
      return;
    } 
    getDispatchDelegate().dispatchErrorOccurredEvent((Component)this, "Latitude", 3413, new Object[] { Double.valueOf(paramDouble) });
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The longitude of the center of the circle.")
  public double Longitude() {
    return this.longitude;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "longitude")
  @SimpleProperty
  public void Longitude(double paramDouble) {
    if (GeometryUtil.isValidLongitude(paramDouble)) {
      this.longitude = paramDouble;
      this.center.setLongitude(paramDouble);
      clearGeometry();
      this.map.getController().updateFeaturePosition(this);
      return;
    } 
    getDispatchDelegate().dispatchErrorOccurredEvent((Component)this, "Longitude", 3414, new Object[] { Double.valueOf(paramDouble) });
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The radius of the circle in meters.")
  public double Radius() {
    return this.radius;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "non_negative_float")
  @SimpleProperty
  public void Radius(double paramDouble) {
    this.radius = paramDouble;
    clearGeometry();
    this.map.getController().updateFeaturePosition(this);
  }
  
  @SimpleFunction(description = "Set the center of the Circle.")
  public void SetLocation(double paramDouble1, double paramDouble2) {
    if (!GeometryUtil.isValidLatitude(paramDouble1)) {
      getDispatchDelegate().dispatchErrorOccurredEvent((Component)this, "SetLocation", 3413, new Object[] { Double.valueOf(paramDouble1) });
      return;
    } 
    if (!GeometryUtil.isValidLongitude(paramDouble2)) {
      getDispatchDelegate().dispatchErrorOccurredEvent((Component)this, "SetLocation", 3414, new Object[] { Double.valueOf(paramDouble2) });
      return;
    } 
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
    this.center.setLatitude(paramDouble1);
    this.center.setLongitude(paramDouble2);
    clearGeometry();
    this.map.getController().updateFeaturePosition(this);
  }
  
  @SimpleProperty(description = "Returns the type of the feature. For Circles, this returns the text \"Circle\".")
  public String Type() {
    return "Circle";
  }
  
  public <T> T accept(MapFactory.MapFeatureVisitor<T> paramMapFeatureVisitor, Object... paramVarArgs) {
    return (T)paramMapFeatureVisitor.visit(this, paramVarArgs);
  }
  
  protected Geometry computeGeometry() {
    return GeometryUtil.createGeometry(this.center);
  }
  
  public void updateCenter(double paramDouble1, double paramDouble2) {
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
    clearGeometry();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Circle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
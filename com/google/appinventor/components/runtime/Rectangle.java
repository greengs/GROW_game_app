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
import com.google.appinventor.components.runtime.util.YailList;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;

@DesignerComponent(category = ComponentCategory.MAPS, description = "Rectangle", version = 2)
@SimpleObject
public class Rectangle extends PolygonBase implements MapFactory.MapRectangle {
  private static final MapFactory.MapFeatureVisitor<Double> distanceComputation = new MapFactory.MapFeatureVisitor<Double>() {
      public Double visit(MapFactory.MapCircle param1MapCircle, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapCircle, (Rectangle)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapCircle, (Rectangle)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapLineString param1MapLineString, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapLineString, (Rectangle)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapLineString, (Rectangle)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapMarker param1MapMarker, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapMarker, (Rectangle)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapMarker, (Rectangle)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapPolygon param1MapPolygon, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapPolygon, (Rectangle)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapPolygon, (Rectangle)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapRectangle param1MapRectangle, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapRectangle, (Rectangle)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapRectangle, (Rectangle)param1VarArgs[0]));
      }
    };
  
  private double east = 0.0D;
  
  private double north = 0.0D;
  
  private double south = 0.0D;
  
  private double west = 0.0D;
  
  public Rectangle(MapFactory.MapFeatureContainer paramMapFeatureContainer) {
    super(paramMapFeatureContainer, distanceComputation);
    paramMapFeatureContainer.addFeature(this);
  }
  
  @SimpleFunction(description = "Returns the bounding box of the Rectangle in the format ((North West) (South East)).")
  public YailList Bounds() {
    return YailList.makeList((Object[])new YailList[] { YailList.makeList((Object[])new Double[] { Double.valueOf(this.north), Double.valueOf(this.west) }), YailList.makeList((Object[])new Double[] { Double.valueOf(this.south), Double.valueOf(this.east) }) });
  }
  
  @SimpleFunction(description = "Returns the center of the Rectangle as a list of the form (Latitude Longitude).")
  public YailList Center() {
    return GeometryUtil.asYailList((IGeoPoint)getCentroid());
  }
  
  @SimpleProperty
  public double EastLongitude() {
    return this.east;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "float")
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The east edge of the rectangle, in decimal degrees east of the prime meridian.")
  public void EastLongitude(double paramDouble) {
    this.east = paramDouble;
    clearGeometry();
    this.map.getController().updateFeaturePosition(this);
  }
  
  @SimpleProperty
  public double NorthLatitude() {
    return this.north;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "float")
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The north edge of the rectangle, in decimal degrees north of the equator.")
  public void NorthLatitude(double paramDouble) {
    this.north = paramDouble;
    clearGeometry();
    this.map.getController().updateFeaturePosition(this);
  }
  
  @SimpleFunction(description = "Moves the Rectangle so that it is centered on the given latitude and longitude while attempting to maintain the width and height of the Rectangle as measured from the center to the edges.")
  public void SetCenter(double paramDouble1, double paramDouble2) {
    if (paramDouble1 < -90.0D || paramDouble1 > 90.0D) {
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "SetCenter", 3405, new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
      return;
    } 
    if (paramDouble2 < -180.0D || paramDouble2 > 180.0D) {
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "SetCenter", 3405, new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
      return;
    } 
    GeoPoint geoPoint1 = getCentroid();
    GeoPoint geoPoint2 = new GeoPoint(this.north, geoPoint1.getLongitude());
    GeoPoint geoPoint3 = new GeoPoint(this.south, geoPoint1.getLongitude());
    GeoPoint geoPoint4 = new GeoPoint(geoPoint1.getLatitude(), this.east);
    GeoPoint geoPoint5 = new GeoPoint(geoPoint1.getLatitude(), this.west);
    double d1 = GeometryUtil.distanceBetween((IGeoPoint)geoPoint2, (IGeoPoint)geoPoint3) / 2.0D;
    double d2 = GeometryUtil.distanceBetween((IGeoPoint)geoPoint4, (IGeoPoint)geoPoint5) / 2.0D;
    geoPoint1.setCoords(paramDouble1, paramDouble2);
    this.north = geoPoint1.destinationPoint(d1, 0.0F).getLatitude();
    this.south = geoPoint1.destinationPoint(d1, 180.0F).getLatitude();
    this.east = geoPoint1.destinationPoint(d2, 90.0F).getLongitude();
    this.west = geoPoint1.destinationPoint(d2, 270.0F).getLongitude();
    clearGeometry();
    this.map.getController().updateFeaturePosition(this);
  }
  
  @SimpleProperty
  public double SouthLatitude() {
    return this.south;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "float")
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The south edge of the rectangle, in decimal degrees north of the equator.")
  public void SouthLatitude(double paramDouble) {
    this.south = paramDouble;
    clearGeometry();
    this.map.getController().updateFeaturePosition(this);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the type of the feature. For rectangles, this returns the text \"Rectangle\".")
  public String Type() {
    return "Rectangle";
  }
  
  @SimpleProperty
  public double WestLongitude() {
    return this.west;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "float")
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The west edge of the rectangle, in decimal degrees east of the equator.")
  public void WestLongitude(double paramDouble) {
    this.west = paramDouble;
    clearGeometry();
    this.map.getController().updateFeaturePosition(this);
  }
  
  public <T> T accept(MapFactory.MapFeatureVisitor<T> paramMapFeatureVisitor, Object... paramVarArgs) {
    return (T)paramMapFeatureVisitor.visit(this, paramVarArgs);
  }
  
  protected Geometry computeGeometry() {
    return GeometryUtil.createGeometry(this.north, this.east, this.south, this.west);
  }
  
  public void updateBounds(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
    this.north = paramDouble1;
    this.west = paramDouble2;
    this.south = paramDouble3;
    this.east = paramDouble4;
    clearGeometry();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Rectangle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
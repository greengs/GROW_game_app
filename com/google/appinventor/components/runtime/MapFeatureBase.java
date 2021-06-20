package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;

@SimpleObject
public abstract class MapFeatureBase implements MapFactory.MapFeature, MapFactory.HasStroke {
  private GeoPoint centroid = null;
  
  protected MapFactory.MapFeatureContainer container = null;
  
  private String description = "";
  
  private final MapFactory.MapFeatureVisitor<Double> distanceComputation;
  
  private MapFactory.MapFeatureVisitor<Double> distanceToPoint = new MapFactory.MapFeatureVisitor<Double>() {
      public Double visit(MapFactory.MapCircle param1MapCircle, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapCircle, (GeoPoint)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapCircle, (GeoPoint)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapLineString param1MapLineString, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapLineString, (GeoPoint)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapLineString, (GeoPoint)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapMarker param1MapMarker, Object... param1VarArgs) {
        return Double.valueOf(GeometryUtil.distanceBetween(param1MapMarker, (GeoPoint)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapPolygon param1MapPolygon, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapPolygon, (GeoPoint)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapPolygon, (GeoPoint)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapRectangle param1MapRectangle, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapRectangle, (GeoPoint)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapRectangle, (GeoPoint)param1VarArgs[0]));
      }
    };
  
  private boolean draggable = false;
  
  private Geometry geometry = null;
  
  private boolean infobox = false;
  
  protected Map map = null;
  
  private int strokeColor = -16777216;
  
  private float strokeOpacity = 1.0F;
  
  private int strokeWidth = 1;
  
  private String title = "";
  
  private boolean visible = true;
  
  protected MapFeatureBase(MapFactory.MapFeatureContainer paramMapFeatureContainer, MapFactory.MapFeatureVisitor<Double> paramMapFeatureVisitor) {
    this.container = paramMapFeatureContainer;
    this.map = paramMapFeatureContainer.getMap();
    this.distanceComputation = paramMapFeatureVisitor;
    Description("");
    Draggable(false);
    EnableInfobox(false);
    StrokeColor(-16777216);
    StrokeOpacity(1.0F);
    StrokeWidth(1);
    Title("");
    Visible(true);
  }
  
  public YailList Centroid() {
    return GeometryUtil.asYailList((IGeoPoint)getCentroid());
  }
  
  @SimpleEvent(description = "The user clicked on the %type%.")
  public void Click() {
    EventDispatcher.dispatchEvent((Component)this, "Click", new Object[0]);
    this.container.FeatureClick(this);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The description displayed in the info window that appears when the user clicks on the %type%.")
  public String Description() {
    return this.description;
  }
  
  @DesignerProperty
  @SimpleProperty
  public void Description(String paramString) {
    this.description = paramString;
    this.map.getController().updateFeatureText(this);
  }
  
  @SimpleFunction(description = "Compute the distance, in meters, between two map features.")
  public double DistanceToFeature(MapFactory.MapFeature paramMapFeature, boolean paramBoolean) {
    return (paramMapFeature == null) ? -1.0D : ((Double)paramMapFeature.accept(this.distanceComputation, new Object[] { this, Boolean.valueOf(paramBoolean) })).doubleValue();
  }
  
  @SimpleFunction(description = "Compute the distance, in meters, between a %type% and a latitude, longitude point.")
  public double DistanceToPoint(double paramDouble1, double paramDouble2, boolean paramBoolean) {
    return ((Double)accept(this.distanceToPoint, new Object[] { new GeoPoint(paramDouble1, paramDouble2), Boolean.valueOf(paramBoolean) })).doubleValue();
  }
  
  @SimpleEvent(description = "The user dragged the %type%.")
  public void Drag() {
    EventDispatcher.dispatchEvent((Component)this, "Drag", new Object[0]);
    this.container.FeatureDrag(this);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void Draggable(boolean paramBoolean) {
    this.draggable = paramBoolean;
    this.map.getController().updateFeatureDraggable(this);
  }
  
  @SimpleProperty(description = "The Draggable property is used to set whether or not the user can drag the %type% by long-pressing and then dragging the %type% to a new location.")
  public boolean Draggable() {
    return this.draggable;
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void EnableInfobox(boolean paramBoolean) {
    this.infobox = paramBoolean;
    this.map.getController().updateFeatureText(this);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Enable or disable the infobox window display when the user taps the %type%.")
  public boolean EnableInfobox() {
    return this.infobox;
  }
  
  @SimpleFunction(description = "Hide the infobox if it is shown. If the infobox is not visible this function has no effect.")
  public void HideInfobox() {
    this.map.getController().hideInfobox(this);
  }
  
  @SimpleEvent(description = "The user long-pressed on the %type%. This event will only trigger if Draggable is false.")
  public void LongClick() {
    EventDispatcher.dispatchEvent((Component)this, "LongClick", new Object[0]);
    this.container.FeatureLongClick(this);
  }
  
  @SimpleFunction(description = "Show the infobox for the %type%. This will show the infobox even if EnableInfobox is set to false.")
  public void ShowInfobox() {
    this.map.getController().showInfobox(this);
  }
  
  @SimpleEvent(description = "The user started a drag operation.")
  public void StartDrag() {
    EventDispatcher.dispatchEvent((Component)this, "StartDrag", new Object[0]);
    this.container.FeatureStartDrag(this);
  }
  
  @SimpleEvent(description = "The user stopped a drag operation.")
  public void StopDrag() {
    EventDispatcher.dispatchEvent((Component)this, "StopDrag", new Object[0]);
    this.container.FeatureStopDrag(this);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The paint color used to outline the %type%.")
  public int StrokeColor() {
    return this.strokeColor;
  }
  
  @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
  @SimpleProperty
  public void StrokeColor(int paramInt) {
    this.strokeColor = paramInt;
    this.map.getController().updateFeatureStroke(this);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The opacity of the stroke used to outline the map feature.")
  public float StrokeOpacity() {
    return this.strokeOpacity;
  }
  
  @DesignerProperty(defaultValue = "1.0", editorType = "float")
  @SimpleProperty
  public void StrokeOpacity(float paramFloat) {
    this.strokeOpacity = paramFloat;
    this.strokeColor = this.strokeColor & 0xFFFFFF | Math.round(255.0F * paramFloat) << 24;
    this.map.getController().updateFeatureStroke(this);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The width of the stroke used to outline the %type%.")
  public int StrokeWidth() {
    return this.strokeWidth;
  }
  
  @DesignerProperty(defaultValue = "1", editorType = "integer")
  @SimpleProperty
  public void StrokeWidth(int paramInt) {
    this.strokeWidth = paramInt;
    this.map.getController().updateFeatureStroke(this);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The title displayed in the info window that appears when the user clicks on the %type%.")
  public String Title() {
    return this.title;
  }
  
  @DesignerProperty
  @SimpleProperty
  public void Title(String paramString) {
    this.title = paramString;
    this.map.getController().updateFeatureText(this);
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "visibility")
  @SimpleProperty
  public void Visible(boolean paramBoolean) {
    if (this.visible == paramBoolean)
      return; 
    this.visible = paramBoolean;
    if (this.visible) {
      this.map.getController().showFeature(this);
    } else {
      this.map.getController().hideFeature(this);
    } 
    this.map.getView().invalidate();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies whether the %type% should be visible on the screen. Value is true if the component is showing and false if hidden.")
  public boolean Visible() {
    return this.visible;
  }
  
  protected final void clearGeometry() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aconst_null
    //   4: putfield centroid : Lorg/osmdroid/util/GeoPoint;
    //   7: aload_0
    //   8: aconst_null
    //   9: putfield geometry : Lorg/locationtech/jts/geom/Geometry;
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	15	finally
  }
  
  protected abstract Geometry computeGeometry();
  
  public final GeoPoint getCentroid() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield centroid : Lorg/osmdroid/util/GeoPoint;
    //   6: ifnonnull -> 23
    //   9: aload_0
    //   10: aload_0
    //   11: invokevirtual getGeometry : ()Lorg/locationtech/jts/geom/Geometry;
    //   14: invokevirtual getCentroid : ()Lorg/locationtech/jts/geom/Point;
    //   17: invokestatic jtsPointToGeoPoint : (Lorg/locationtech/jts/geom/Point;)Lorg/osmdroid/util/GeoPoint;
    //   20: putfield centroid : Lorg/osmdroid/util/GeoPoint;
    //   23: aload_0
    //   24: getfield centroid : Lorg/osmdroid/util/GeoPoint;
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: areturn
    //   32: astore_1
    //   33: aload_0
    //   34: monitorexit
    //   35: aload_1
    //   36: athrow
    // Exception table:
    //   from	to	target	type
    //   2	23	32	finally
    //   23	28	32	finally
  }
  
  public HandlesEventDispatching getDispatchDelegate() {
    return this.map.getDispatchDelegate();
  }
  
  public final Geometry getGeometry() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield geometry : Lorg/locationtech/jts/geom/Geometry;
    //   6: ifnonnull -> 17
    //   9: aload_0
    //   10: aload_0
    //   11: invokevirtual computeGeometry : ()Lorg/locationtech/jts/geom/Geometry;
    //   14: putfield geometry : Lorg/locationtech/jts/geom/Geometry;
    //   17: aload_0
    //   18: getfield geometry : Lorg/locationtech/jts/geom/Geometry;
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: areturn
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	26	finally
    //   17	22	26	finally
  }
  
  public void removeFromMap() {
    this.map.getController().removeFeature(this);
  }
  
  public void setMap(MapFactory.MapFeatureContainer paramMapFeatureContainer) {
    this.map = paramMapFeatureContainer.getMap();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/MapFeatureBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
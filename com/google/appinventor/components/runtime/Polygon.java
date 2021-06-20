package com.google.appinventor.components.runtime;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.util.GeoPoint;

@DesignerComponent(category = ComponentCategory.MAPS, description = "Polygon", version = 2)
@SimpleObject
public class Polygon extends PolygonBase implements MapFactory.MapPolygon {
  private static final String TAG = Polygon.class.getSimpleName();
  
  private static final MapFactory.MapFeatureVisitor<Double> distanceComputation = new MapFactory.MapFeatureVisitor<Double>() {
      public Double visit(MapFactory.MapCircle param1MapCircle, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids((Polygon)param1VarArgs[0], param1MapCircle)) : Double.valueOf(GeometryUtil.distanceBetweenEdges((Polygon)param1VarArgs[0], param1MapCircle));
      }
      
      public Double visit(MapFactory.MapLineString param1MapLineString, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapLineString, (Polygon)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapLineString, (Polygon)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapMarker param1MapMarker, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapMarker, (Polygon)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapMarker, (Polygon)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapPolygon param1MapPolygon, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapPolygon, (Polygon)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapPolygon, (Polygon)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapRectangle param1MapRectangle, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids((Polygon)param1VarArgs[0], param1MapRectangle)) : Double.valueOf(GeometryUtil.distanceBetweenEdges((Polygon)param1VarArgs[0], param1MapRectangle));
      }
    };
  
  private List<List<List<GeoPoint>>> holePoints = new ArrayList<List<List<GeoPoint>>>();
  
  private boolean initialized = false;
  
  private boolean multipolygon = false;
  
  private List<List<GeoPoint>> points = new ArrayList<List<GeoPoint>>();
  
  public Polygon(MapFactory.MapFeatureContainer paramMapFeatureContainer) {
    super(paramMapFeatureContainer, distanceComputation);
    paramMapFeatureContainer.addFeature(this);
  }
  
  @SimpleFunction(description = "Returns the centroid of the Polygon as a (latitude, longitude) pair.")
  public YailList Centroid() {
    return super.Centroid();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets or sets the sequence of points used to draw holes in the polygon.")
  public YailList HolePoints() {
    if (this.holePoints.isEmpty())
      return YailList.makeEmptyList(); 
    if (this.multipolygon) {
      LinkedList<YailList> linkedList = new LinkedList();
      Iterator<List<List<GeoPoint>>> iterator = this.holePoints.iterator();
      while (iterator.hasNext())
        linkedList.add(GeometryUtil.multiPolygonToYailList(iterator.next())); 
      return YailList.makeList(linkedList);
    } 
    return GeometryUtil.multiPolygonToYailList(this.holePoints.get(0));
  }
  
  @SimpleProperty
  public void HolePoints(YailList paramYailList) {
    try {
      if (paramYailList.size() == 0) {
        this.holePoints = new ArrayList<List<List<GeoPoint>>>();
      } else if (this.multipolygon) {
        this.holePoints = GeometryUtil.multiPolygonHolesFromYailList(paramYailList);
      } else if (GeometryUtil.isMultiPolygon(paramYailList)) {
        ArrayList<List> arrayList = new ArrayList();
        arrayList.add(GeometryUtil.multiPolygonFromYailList(paramYailList));
        this.holePoints = (List)arrayList;
      } else {
        throw new DispatchableError(3404, new Object[] { "Unable to determine the structure of the points argument." });
      } 
      if (this.initialized) {
        clearGeometry();
        this.map.getController().updateFeatureHoles(this);
        return;
      } 
    } catch (DispatchableError dispatchableError) {
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "HolePoints", dispatchableError.getErrorCode(), dispatchableError.getArguments());
      return;
    } 
  }
  
  @DesignerProperty(editorType = "textArea")
  @SimpleProperty(description = "Constructs holes in a polygon from a given list of coordinates per hole.")
  public void HolePointsFromString(String paramString) {
    if (TextUtils.isEmpty(paramString)) {
      this.holePoints = new ArrayList<List<List<GeoPoint>>>();
      this.map.getController().updateFeatureHoles(this);
      return;
    } 
    try {
      JSONArray jSONArray = new JSONArray(paramString);
      if (jSONArray.length() == 0) {
        this.holePoints = new ArrayList<List<List<GeoPoint>>>();
        this.map.getController().updateFeatureHoles(this);
        return;
      } 
    } catch (JSONException jSONException) {
      Log.e(TAG, "Unable to parse point string", (Throwable)jSONException);
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "HolePointsFromString", 3404, new Object[] { jSONException.getMessage() });
      return;
    } 
    this.holePoints = GeometryUtil.multiPolygonHolesToList((JSONArray)jSONException);
    if (this.initialized) {
      clearGeometry();
      this.map.getController().updateFeatureHoles(this);
    } 
    Log.d(TAG, "Points: " + this.points);
  }
  
  public void Initialize() {
    this.initialized = true;
    clearGeometry();
    this.map.getController().updateFeaturePosition(this);
    this.map.getController().updateFeatureHoles(this);
    this.map.getController().updateFeatureText(this);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets or sets the sequence of points used to draw the polygon.")
  public YailList Points() {
    if (this.points.isEmpty())
      return YailList.makeEmptyList(); 
    if (this.multipolygon) {
      LinkedList<YailList> linkedList = new LinkedList();
      Iterator<List<GeoPoint>> iterator = this.points.iterator();
      while (iterator.hasNext())
        linkedList.add(GeometryUtil.pointsListToYailList(iterator.next())); 
      return YailList.makeList(linkedList);
    } 
    return GeometryUtil.pointsListToYailList(this.points.get(0));
  }
  
  @SimpleProperty
  public void Points(YailList paramYailList) {
    try {
      if (GeometryUtil.isPolygon(paramYailList)) {
        this.multipolygon = false;
        this.points.clear();
        this.points.add(GeometryUtil.pointsFromYailList(paramYailList));
      } else if (GeometryUtil.isMultiPolygon(paramYailList)) {
        this.multipolygon = true;
        this.points = GeometryUtil.multiPolygonFromYailList(paramYailList);
      } else {
        throw new DispatchableError(3404, new Object[] { "Unable to determine the structure of the points argument." });
      } 
      if (this.initialized) {
        clearGeometry();
        this.map.getController().updateFeaturePosition(this);
        return;
      } 
    } catch (DispatchableError dispatchableError) {
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "Points", dispatchableError.getErrorCode(), dispatchableError.getArguments());
      return;
    } 
  }
  
  @DesignerProperty(editorType = "textArea")
  @SimpleProperty(description = "Constructs a polygon from the given list of coordinates.")
  public void PointsFromString(String paramString) {
    if (TextUtils.isEmpty(paramString)) {
      this.points = new ArrayList<List<GeoPoint>>();
      this.map.getController().updateFeaturePosition(this);
      return;
    } 
    try {
      boolean bool;
      JSONArray jSONArray = new JSONArray(paramString);
      if (jSONArray.length() == 0) {
        this.points = new ArrayList<List<GeoPoint>>();
        this.multipolygon = false;
        this.map.getController().updateFeaturePosition(this);
        return;
      } 
      this.points = GeometryUtil.multiPolygonToList(jSONArray);
      if (this.points.size() > 1) {
        bool = true;
      } else {
        bool = false;
      } 
      this.multipolygon = bool;
      if (this.initialized) {
        clearGeometry();
        this.map.getController().updateFeaturePosition(this);
        return;
      } 
      return;
    } catch (JSONException jSONException) {
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "PointsFromString", 3404, new Object[] { jSONException.getMessage() });
      return;
    } catch (DispatchableError dispatchableError) {
      getDispatchDelegate().dispatchErrorOccurredEvent((Component)this, "PointsFromString", dispatchableError.getErrorCode(), dispatchableError.getArguments());
      return;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the type of the feature. For polygons, this returns the text \"Polygon\".")
  public String Type() {
    return "Polygon";
  }
  
  public <T> T accept(MapFactory.MapFeatureVisitor<T> paramMapFeatureVisitor, Object... paramVarArgs) {
    return (T)paramMapFeatureVisitor.visit(this, paramVarArgs);
  }
  
  protected Geometry computeGeometry() {
    return GeometryUtil.createGeometry(this.points, this.holePoints);
  }
  
  public List<List<List<GeoPoint>>> getHolePoints() {
    return this.holePoints;
  }
  
  public List<List<GeoPoint>> getPoints() {
    return this.points;
  }
  
  @VisibleForTesting
  boolean isInitialized() {
    return this.initialized;
  }
  
  public void updateHolePoints(List<List<List<GeoPoint>>> paramList) {
    this.holePoints.clear();
    this.holePoints.addAll(paramList);
    clearGeometry();
  }
  
  public void updatePoints(List<List<GeoPoint>> paramList) {
    this.points.clear();
    this.points.addAll(paramList);
    clearGeometry();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Polygon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
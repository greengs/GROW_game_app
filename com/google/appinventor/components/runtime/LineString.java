package com.google.appinventor.components.runtime;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.util.GeoPoint;

@DesignerComponent(category = ComponentCategory.MAPS, description = "LineString", version = 2)
@SimpleObject
public class LineString extends MapFeatureBase implements MapFactory.MapLineString {
  private static final String TAG = LineString.class.getSimpleName();
  
  private static final MapFactory.MapFeatureVisitor<Double> distanceComputation = new MapFactory.MapFeatureVisitor<Double>() {
      public Double visit(MapFactory.MapCircle param1MapCircle, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids((LineString)param1VarArgs[0], param1MapCircle)) : Double.valueOf(GeometryUtil.distanceBetweenEdges((LineString)param1VarArgs[0], param1MapCircle));
      }
      
      public Double visit(MapFactory.MapLineString param1MapLineString, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapLineString, (LineString)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapLineString, (LineString)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapMarker param1MapMarker, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids(param1MapMarker, (LineString)param1VarArgs[0])) : Double.valueOf(GeometryUtil.distanceBetweenEdges(param1MapMarker, (LineString)param1VarArgs[0]));
      }
      
      public Double visit(MapFactory.MapPolygon param1MapPolygon, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids((LineString)param1VarArgs[0], param1MapPolygon)) : Double.valueOf(GeometryUtil.distanceBetweenEdges((LineString)param1VarArgs[0], param1MapPolygon));
      }
      
      public Double visit(MapFactory.MapRectangle param1MapRectangle, Object... param1VarArgs) {
        return ((Boolean)param1VarArgs[1]).booleanValue() ? Double.valueOf(GeometryUtil.distanceBetweenCentroids((LineString)param1VarArgs[0], param1MapRectangle)) : Double.valueOf(GeometryUtil.distanceBetweenEdges((LineString)param1VarArgs[0], param1MapRectangle));
      }
    };
  
  private List<GeoPoint> points = new ArrayList<GeoPoint>();
  
  public LineString(MapFactory.MapFeatureContainer paramMapFeatureContainer) {
    super(paramMapFeatureContainer, distanceComputation);
    StrokeWidth(3);
    paramMapFeatureContainer.addFeature(this);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A list of latitude and longitude pairs that represent the line segments of the polyline.")
  public YailList Points() {
    return GeometryUtil.pointsListToYailList(this.points);
  }
  
  @SimpleProperty
  public void Points(@NonNull YailList paramYailList) {
    if (paramYailList.size() < 2) {
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "Points", 3401, new Object[] { Integer.valueOf(paramYailList.length() - 1) });
      return;
    } 
    try {
      this.points = GeometryUtil.pointsFromYailList(paramYailList);
      clearGeometry();
      this.map.getController().updateFeaturePosition(this);
      return;
    } catch (DispatchableError dispatchableError) {
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "Points", dispatchableError.getErrorCode(), dispatchableError.getArguments());
      return;
    } 
  }
  
  @DesignerProperty(editorType = "textArea")
  @SimpleProperty
  public void PointsFromString(String paramString) {
    ArrayList<GeoPoint> arrayList;
    try {
      arrayList = new ArrayList();
      JSONArray jSONArray = new JSONArray(paramString);
      if (jSONArray.length() < 2)
        throw new DispatchableError(3401, new Object[] { Integer.valueOf(jSONArray.length()) }); 
      int j = jSONArray.length();
      int i;
      for (i = 0; i < j; i++) {
        JSONArray jSONArray1 = jSONArray.optJSONArray(i);
        if (jSONArray1 == null)
          throw new DispatchableError(3420, new Object[] { Integer.valueOf(i), jSONArray.get(i).toString() }); 
        if (jSONArray1.length() < 2)
          throw new DispatchableError(3403, new Object[] { Integer.valueOf(i), Integer.valueOf(paramString.length()) }); 
        double d1 = jSONArray1.optDouble(0, Double.NaN);
        double d2 = jSONArray1.optDouble(1, Double.NaN);
        if (!GeometryUtil.isValidLatitude(d1))
          throw new DispatchableError(3418, new Object[] { Integer.valueOf(i), jSONArray.get(0).toString() }); 
        if (!GeometryUtil.isValidLongitude(d2))
          throw new DispatchableError(3419, new Object[] { Integer.valueOf(i), jSONArray.get(1).toString() }); 
        arrayList.add(new GeoPoint(d1, d2));
      } 
    } catch (JSONException jSONException) {
      Log.e(TAG, "Malformed string to LineString.PointsFromString", (Throwable)jSONException);
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "PointsFromString", 3402, new Object[] { jSONException.getMessage() });
      return;
    } catch (DispatchableError dispatchableError) {
      this.container.$form().dispatchErrorOccurredEvent((Component)this, "PointsFromString", dispatchableError.getErrorCode(), dispatchableError.getArguments());
      return;
    } 
    this.points = arrayList;
    clearGeometry();
    this.map.getController().updateFeaturePosition(this);
  }
  
  @DesignerProperty(defaultValue = "3")
  @SimpleProperty
  public void StrokeWidth(int paramInt) {
    super.StrokeWidth(paramInt);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the type of the map feature. For LineString, this returns the text \"LineString\".")
  public String Type() {
    return "LineString";
  }
  
  public <T> T accept(MapFactory.MapFeatureVisitor<T> paramMapFeatureVisitor, Object... paramVarArgs) {
    return (T)paramMapFeatureVisitor.visit(this, paramVarArgs);
  }
  
  protected Geometry computeGeometry() {
    return GeometryUtil.createGeometry(this.points);
  }
  
  public List<GeoPoint> getPoints() {
    return this.points;
  }
  
  public void updatePoints(List<GeoPoint> paramList) {
    this.points = paramList;
    clearGeometry();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/LineString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
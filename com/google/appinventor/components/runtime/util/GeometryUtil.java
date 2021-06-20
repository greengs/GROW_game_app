package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.errors.IterationError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;

public final class GeometryUtil {
  public static final double EARTH_RADIUS = 6378137.0D;
  
  private static final GeometryFactory FACTORY = new GeometryFactory(new PrecisionModel(), 4326);
  
  public static final double ONE_DEG_IN_METERS = 111319.49079327358D;
  
  public static final int WEB_MERCATOR_SRID = 4326;
  
  public static YailList asYailList(IGeoPoint paramIGeoPoint) {
    return YailList.makeList(new Object[] { Double.valueOf(paramIGeoPoint.getLatitude()), Double.valueOf(paramIGeoPoint.getLongitude()) });
  }
  
  public static double bearingTo(MapFactory.MapMarker paramMapMarker1, MapFactory.MapMarker paramMapMarker2) {
    return paramMapMarker1.getCentroid().bearingTo((IGeoPoint)paramMapMarker2.getCentroid());
  }
  
  public static double bearingToCentroid(MapFactory.MapMarker paramMapMarker, MapFactory.MapCircle paramMapCircle) {
    return paramMapMarker.getCentroid().bearingTo((IGeoPoint)paramMapCircle.getCentroid());
  }
  
  public static double bearingToCentroid(MapFactory.MapMarker paramMapMarker, MapFactory.MapLineString paramMapLineString) {
    return paramMapMarker.getCentroid().bearingTo((IGeoPoint)paramMapLineString.getCentroid());
  }
  
  public static double bearingToCentroid(MapFactory.MapMarker paramMapMarker, MapFactory.MapPolygon paramMapPolygon) {
    return paramMapMarker.getCentroid().bearingTo((IGeoPoint)paramMapPolygon.getCentroid());
  }
  
  public static double bearingToCentroid(MapFactory.MapMarker paramMapMarker, MapFactory.MapRectangle paramMapRectangle) {
    return paramMapMarker.getCentroid().bearingTo((IGeoPoint)paramMapRectangle.getCentroid());
  }
  
  public static double bearingToEdge(MapFactory.MapMarker paramMapMarker, MapFactory.MapCircle paramMapCircle) {
    return paramMapMarker.getCentroid().bearingTo((IGeoPoint)paramMapCircle.getCentroid());
  }
  
  public static double bearingToEdge(MapFactory.MapMarker paramMapMarker, MapFactory.MapLineString paramMapLineString) {
    return paramMapMarker.getCentroid().bearingTo((IGeoPoint)paramMapLineString.getCentroid());
  }
  
  public static double bearingToEdge(MapFactory.MapMarker paramMapMarker, MapFactory.MapPolygon paramMapPolygon) {
    return paramMapMarker.getCentroid().bearingTo((IGeoPoint)paramMapPolygon.getCentroid());
  }
  
  public static double bearingToEdge(MapFactory.MapMarker paramMapMarker, MapFactory.MapRectangle paramMapRectangle) {
    return paramMapMarker.getCentroid().bearingTo((IGeoPoint)paramMapRectangle.getCentroid());
  }
  
  public static double coerceToDouble(Object paramObject) {
    if (paramObject instanceof Number)
      return ((Number)paramObject).doubleValue(); 
    try {
      return Double.parseDouble(paramObject.toString());
    } catch (NumberFormatException numberFormatException) {
      return Double.NaN;
    } 
  }
  
  public static GeoPoint coerceToPoint(Object paramObject1, Object paramObject2) {
    double d1 = coerceToDouble(paramObject1);
    double d2 = coerceToDouble(paramObject2);
    if (Double.isNaN(d1))
      throw new IllegalArgumentException("Latitude must be a numeric."); 
    if (Double.isNaN(d2))
      throw new IllegalArgumentException("Longitude must be a numeric."); 
    if (d1 < -90.0D || d1 > 90.0D)
      throw new IllegalArgumentException("Latitude must be between -90 and 90."); 
    if (d2 < -180.0D || d2 > 180.0D)
      throw new IllegalArgumentException("Longitude must be between -180 and 180."); 
    return new GeoPoint(d1, d2);
  }
  
  public static Geometry createGeometry(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
    return (Geometry)FACTORY.createPolygon(new Coordinate[] { new Coordinate(paramDouble2, paramDouble1), new Coordinate(paramDouble2, paramDouble3), new Coordinate(paramDouble4, paramDouble3), new Coordinate(paramDouble4, paramDouble1), new Coordinate(paramDouble2, paramDouble1) });
  }
  
  public static Geometry createGeometry(List<GeoPoint> paramList) {
    return (Geometry)FACTORY.createLineString(pointsToCoordinates(paramList));
  }
  
  public static Geometry createGeometry(List<List<GeoPoint>> paramList, List<List<List<GeoPoint>>> paramList1) {
    Iterator<List<GeoPoint>> iterator;
    if (paramList == null)
      throw new IllegalArgumentException("points must not be null."); 
    if (paramList1 != null && !paramList1.isEmpty() && paramList1.size() != paramList.size())
      throw new IllegalArgumentException("holes must either be null or the same length as points."); 
    Polygon[] arrayOfPolygon = new Polygon[paramList.size()];
    boolean bool = false;
    int i = 0;
    if (paramList1 == null || paramList1.isEmpty()) {
      iterator = paramList.iterator();
      while (iterator.hasNext()) {
        arrayOfPolygon[i] = ringToPolygon(iterator.next());
        i++;
      } 
    } else {
      iterator = iterator.iterator();
      Iterator<List<List<GeoPoint>>> iterator1 = paramList1.iterator();
      for (i = bool; iterator.hasNext(); i++)
        arrayOfPolygon[i] = ringToPolygon(iterator.next(), iterator1.next()); 
    } 
    return (Geometry)((arrayOfPolygon.length == 1) ? arrayOfPolygon[0] : FACTORY.createMultiPolygon(arrayOfPolygon));
  }
  
  public static Geometry createGeometry(GeoPoint paramGeoPoint) {
    return (Geometry)FACTORY.createPoint(geoPointToCoordinate(paramGeoPoint));
  }
  
  public static double distanceBetween(MapFactory.MapMarker paramMapMarker1, MapFactory.MapMarker paramMapMarker2) {
    return distanceBetween(paramMapMarker1.getLocation(), paramMapMarker2.getLocation());
  }
  
  public static double distanceBetween(MapFactory.MapMarker paramMapMarker, GeoPoint paramGeoPoint) {
    return distanceBetween(paramMapMarker.getLocation(), (IGeoPoint)paramGeoPoint);
  }
  
  public static double distanceBetween(IGeoPoint paramIGeoPoint1, IGeoPoint paramIGeoPoint2) {
    double d1 = Math.toRadians(paramIGeoPoint1.getLatitude());
    double d2 = Math.toRadians(paramIGeoPoint1.getLongitude());
    double d3 = Math.toRadians(paramIGeoPoint2.getLatitude());
    double d4 = Math.toRadians(paramIGeoPoint2.getLongitude());
    d1 = Math.pow(Math.sin((d3 - d1) / 2.0D), 2.0D) + Math.cos(d1) * Math.cos(d3) * Math.pow(Math.sin((d4 - d2) / 2.0D), 2.0D);
    return 6378137.0D * 2.0D * Math.atan2(Math.sqrt(d1), Math.sqrt(1.0D - d1));
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapCircle paramMapCircle1, MapFactory.MapCircle paramMapCircle2) {
    return distanceBetween((IGeoPoint)paramMapCircle1.getCentroid(), (IGeoPoint)paramMapCircle2.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapCircle paramMapCircle, MapFactory.MapRectangle paramMapRectangle) {
    return distanceBetween((IGeoPoint)paramMapCircle.getCentroid(), (IGeoPoint)paramMapRectangle.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapCircle paramMapCircle, GeoPoint paramGeoPoint) {
    return distanceBetween((IGeoPoint)paramMapCircle.getCentroid(), (IGeoPoint)paramGeoPoint);
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapLineString paramMapLineString, MapFactory.MapCircle paramMapCircle) {
    return distanceBetween((IGeoPoint)paramMapLineString.getCentroid(), (IGeoPoint)paramMapCircle.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapLineString paramMapLineString1, MapFactory.MapLineString paramMapLineString2) {
    return distanceBetween((IGeoPoint)paramMapLineString1.getCentroid(), (IGeoPoint)paramMapLineString2.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapLineString paramMapLineString, MapFactory.MapPolygon paramMapPolygon) {
    return distanceBetween((IGeoPoint)paramMapLineString.getCentroid(), (IGeoPoint)paramMapPolygon.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapLineString paramMapLineString, MapFactory.MapRectangle paramMapRectangle) {
    return distanceBetween((IGeoPoint)paramMapLineString.getCentroid(), (IGeoPoint)paramMapRectangle.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapLineString paramMapLineString, GeoPoint paramGeoPoint) {
    return distanceBetween((IGeoPoint)paramMapLineString.getCentroid(), (IGeoPoint)paramGeoPoint);
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapMarker paramMapMarker, MapFactory.MapCircle paramMapCircle) {
    return distanceBetween((IGeoPoint)paramMapMarker.getCentroid(), (IGeoPoint)paramMapCircle.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapMarker paramMapMarker, MapFactory.MapLineString paramMapLineString) {
    return distanceBetween((IGeoPoint)paramMapMarker.getCentroid(), (IGeoPoint)paramMapLineString.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapMarker paramMapMarker, MapFactory.MapPolygon paramMapPolygon) {
    return distanceBetween((IGeoPoint)paramMapMarker.getCentroid(), (IGeoPoint)paramMapPolygon.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapMarker paramMapMarker, MapFactory.MapRectangle paramMapRectangle) {
    return distanceBetween((IGeoPoint)paramMapMarker.getCentroid(), (IGeoPoint)paramMapRectangle.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapPolygon paramMapPolygon, MapFactory.MapCircle paramMapCircle) {
    return distanceBetween((IGeoPoint)paramMapPolygon.getCentroid(), (IGeoPoint)paramMapCircle.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapPolygon paramMapPolygon1, MapFactory.MapPolygon paramMapPolygon2) {
    return distanceBetween((IGeoPoint)paramMapPolygon1.getCentroid(), (IGeoPoint)paramMapPolygon2.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapPolygon paramMapPolygon, MapFactory.MapRectangle paramMapRectangle) {
    return distanceBetween((IGeoPoint)paramMapPolygon.getCentroid(), (IGeoPoint)paramMapRectangle.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapPolygon paramMapPolygon, GeoPoint paramGeoPoint) {
    return distanceBetween((IGeoPoint)paramMapPolygon.getCentroid(), (IGeoPoint)paramGeoPoint);
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapRectangle paramMapRectangle1, MapFactory.MapRectangle paramMapRectangle2) {
    return distanceBetween((IGeoPoint)paramMapRectangle1.getCentroid(), (IGeoPoint)paramMapRectangle2.getCentroid());
  }
  
  public static double distanceBetweenCentroids(MapFactory.MapRectangle paramMapRectangle, GeoPoint paramGeoPoint) {
    return distanceBetween((IGeoPoint)paramMapRectangle.getCentroid(), (IGeoPoint)paramGeoPoint);
  }
  
  public static double distanceBetweenEdges(MapFactory.MapCircle paramMapCircle1, MapFactory.MapCircle paramMapCircle2) {
    double d2 = distanceBetween((IGeoPoint)paramMapCircle1.getCentroid(), (IGeoPoint)paramMapCircle2.getCentroid()) - paramMapCircle1.Radius() - paramMapCircle2.Radius();
    double d1 = d2;
    if (d2 < 0.0D)
      d1 = 0.0D; 
    return d1;
  }
  
  public static double distanceBetweenEdges(MapFactory.MapCircle paramMapCircle, MapFactory.MapRectangle paramMapRectangle) {
    double d2 = 111319.49079327358D * paramMapRectangle.getGeometry().distance(createGeometry(paramMapCircle.getCentroid())) - paramMapCircle.Radius();
    double d1 = d2;
    if (d2 < 0.0D)
      d1 = 0.0D; 
    return d1;
  }
  
  public static double distanceBetweenEdges(MapFactory.MapCircle paramMapCircle, GeoPoint paramGeoPoint) {
    double d2 = distanceBetween((IGeoPoint)paramMapCircle.getCentroid(), (IGeoPoint)paramGeoPoint) - paramMapCircle.Radius();
    double d1 = d2;
    if (d2 < 0.0D)
      d1 = 0.0D; 
    return d1;
  }
  
  public static double distanceBetweenEdges(MapFactory.MapLineString paramMapLineString, MapFactory.MapCircle paramMapCircle) {
    double d2 = 111319.49079327358D * paramMapLineString.getGeometry().distance(createGeometry(paramMapCircle.getCentroid())) - paramMapCircle.Radius();
    double d1 = d2;
    if (d2 < 0.0D)
      d1 = 0.0D; 
    return d1;
  }
  
  public static double distanceBetweenEdges(MapFactory.MapLineString paramMapLineString1, MapFactory.MapLineString paramMapLineString2) {
    return 111319.49079327358D * paramMapLineString1.getGeometry().distance(paramMapLineString2.getGeometry());
  }
  
  public static double distanceBetweenEdges(MapFactory.MapLineString paramMapLineString, MapFactory.MapPolygon paramMapPolygon) {
    return 111319.49079327358D * paramMapLineString.getGeometry().distance(paramMapPolygon.getGeometry());
  }
  
  public static double distanceBetweenEdges(MapFactory.MapLineString paramMapLineString, MapFactory.MapRectangle paramMapRectangle) {
    return 111319.49079327358D * paramMapLineString.getGeometry().distance(paramMapRectangle.getGeometry());
  }
  
  public static double distanceBetweenEdges(MapFactory.MapLineString paramMapLineString, GeoPoint paramGeoPoint) {
    return 111319.49079327358D * paramMapLineString.getGeometry().distance(createGeometry(paramGeoPoint));
  }
  
  public static double distanceBetweenEdges(MapFactory.MapMarker paramMapMarker, MapFactory.MapCircle paramMapCircle) {
    double d2 = paramMapMarker.getCentroid().distanceTo((IGeoPoint)paramMapCircle.getCentroid()) - paramMapCircle.Radius();
    double d1 = d2;
    if (d2 < 0.0D)
      d1 = 0.0D; 
    return d1;
  }
  
  public static double distanceBetweenEdges(MapFactory.MapMarker paramMapMarker, MapFactory.MapLineString paramMapLineString) {
    return 111319.49079327358D * paramMapMarker.getGeometry().distance(paramMapLineString.getGeometry());
  }
  
  public static double distanceBetweenEdges(MapFactory.MapMarker paramMapMarker, MapFactory.MapPolygon paramMapPolygon) {
    return 111319.49079327358D * paramMapMarker.getGeometry().distance(paramMapPolygon.getGeometry());
  }
  
  public static double distanceBetweenEdges(MapFactory.MapMarker paramMapMarker, MapFactory.MapRectangle paramMapRectangle) {
    return 111319.49079327358D * paramMapMarker.getGeometry().distance(paramMapRectangle.getGeometry());
  }
  
  public static double distanceBetweenEdges(MapFactory.MapPolygon paramMapPolygon, MapFactory.MapCircle paramMapCircle) {
    double d2 = 111319.49079327358D * paramMapPolygon.getGeometry().distance(createGeometry(paramMapCircle.getCentroid())) - paramMapCircle.Radius();
    double d1 = d2;
    if (d2 < 0.0D)
      d1 = 0.0D; 
    return d1;
  }
  
  public static double distanceBetweenEdges(MapFactory.MapPolygon paramMapPolygon1, MapFactory.MapPolygon paramMapPolygon2) {
    return 111319.49079327358D * paramMapPolygon1.getGeometry().distance(paramMapPolygon2.getGeometry());
  }
  
  public static double distanceBetweenEdges(MapFactory.MapPolygon paramMapPolygon, MapFactory.MapRectangle paramMapRectangle) {
    return 111319.49079327358D * paramMapPolygon.getGeometry().distance(paramMapRectangle.getGeometry());
  }
  
  public static double distanceBetweenEdges(MapFactory.MapPolygon paramMapPolygon, GeoPoint paramGeoPoint) {
    return 111319.49079327358D * paramMapPolygon.getGeometry().distance(createGeometry(paramGeoPoint));
  }
  
  public static double distanceBetweenEdges(MapFactory.MapRectangle paramMapRectangle1, MapFactory.MapRectangle paramMapRectangle2) {
    return 111319.49079327358D * paramMapRectangle1.getGeometry().distance(paramMapRectangle2.getGeometry());
  }
  
  public static double distanceBetweenEdges(MapFactory.MapRectangle paramMapRectangle, GeoPoint paramGeoPoint) {
    return 111319.49079327358D * paramMapRectangle.getGeometry().distance(createGeometry(paramGeoPoint));
  }
  
  public static Coordinate geoPointToCoordinate(GeoPoint paramGeoPoint) {
    return new Coordinate(paramGeoPoint.getLongitude(), paramGeoPoint.getLatitude());
  }
  
  public static LinearRing geoPointsToLinearRing(List<GeoPoint> paramList) {
    return FACTORY.createLinearRing(pointsToCoordinates(paramList));
  }
  
  public static GeoPoint getCentroid(List<List<GeoPoint>> paramList, List<List<List<GeoPoint>>> paramList1) {
    return jtsPointToGeoPoint(createGeometry(paramList, paramList1).getCentroid());
  }
  
  public static GeoPoint getMidpoint(List<GeoPoint> paramList) {
    return paramList.isEmpty() ? new GeoPoint(0.0D, 0.0D) : ((paramList.size() == 1) ? new GeoPoint(paramList.get(0)) : jtsPointToGeoPoint(FACTORY.createLineString(pointsToCoordinates(paramList)).getCentroid()));
  }
  
  public static boolean isMultiPolygon(YailList paramYailList) {
    return (paramYailList.size() > 0 && isPolygon(TypeUtil.<YailList>castNotNull(paramYailList.get(1), YailList.class, "list")));
  }
  
  public static boolean isPolygon(YailList paramYailList) {
    if (paramYailList.size() < 3)
      return false; 
    try {
      pointFromYailList(TypeUtil.<YailList>castNotNull(paramYailList.get(1), YailList.class, "list"));
      return true;
    } catch (DispatchableError dispatchableError) {
      return false;
    } 
  }
  
  public static boolean isValidLatitude(double paramDouble) {
    return (-90.0D <= paramDouble && paramDouble <= 90.0D);
  }
  
  public static boolean isValidLongitude(double paramDouble) {
    return (-180.0D <= paramDouble && paramDouble <= 180.0D);
  }
  
  public static GeoPoint jtsPointToGeoPoint(Point paramPoint) {
    return new GeoPoint(paramPoint.getY(), paramPoint.getX());
  }
  
  public static List<List<GeoPoint>> multiPolygonFromYailList(YailList paramYailList) {
    ArrayList<List<GeoPoint>> arrayList = new ArrayList();
    ListIterator listIterator = paramYailList.listIterator(1);
    while (listIterator.hasNext())
      arrayList.add(pointsFromYailList(TypeUtil.<YailList>castNotNull(listIterator.next(), YailList.class, "list"))); 
    return arrayList;
  }
  
  public static List<List<List<GeoPoint>>> multiPolygonHolesFromYailList(YailList paramYailList) {
    ArrayList<List<List<GeoPoint>>> arrayList = new ArrayList();
    ListIterator listIterator = paramYailList.listIterator(1);
    int i = 1;
    try {
      while (listIterator.hasNext()) {
        arrayList.add(multiPolygonFromYailList(TypeUtil.<YailList>castNotNull(listIterator.next(), YailList.class, "list")));
        i++;
      } 
    } catch (DispatchableError dispatchableError) {
      throw IterationError.fromError(i, dispatchableError);
    } 
    return arrayList;
  }
  
  public static List<List<List<GeoPoint>>> multiPolygonHolesToList(JSONArray paramJSONArray) throws JSONException {
    ArrayList<List<List<GeoPoint>>> arrayList = new ArrayList();
    if (paramJSONArray.getJSONArray(0).getJSONArray(0).optJSONArray(0) == null) {
      arrayList.add(multiPolygonToList(paramJSONArray));
      return arrayList;
    } 
    int i = 0;
    while (true) {
      if (i < paramJSONArray.length()) {
        arrayList.add(multiPolygonToList(paramJSONArray.getJSONArray(i)));
        i++;
        continue;
      } 
      return arrayList;
    } 
  }
  
  public static List<List<GeoPoint>> multiPolygonToList(JSONArray paramJSONArray) throws JSONException {
    ArrayList<List<GeoPoint>> arrayList = new ArrayList();
    if (paramJSONArray.length() != 0) {
      if (paramJSONArray.getJSONArray(0).optJSONArray(0) == null) {
        arrayList.add(polygonToList(paramJSONArray));
        return arrayList;
      } 
      int i = 0;
      while (true) {
        if (i < paramJSONArray.length()) {
          arrayList.add(polygonToList(paramJSONArray.getJSONArray(i)));
          i++;
          continue;
        } 
        return arrayList;
      } 
    } 
    return arrayList;
  }
  
  public static YailList multiPolygonToYailList(List<List<GeoPoint>> paramList) {
    LinkedList<YailList> linkedList = new LinkedList();
    Iterator<List<GeoPoint>> iterator = paramList.iterator();
    while (iterator.hasNext())
      linkedList.add(pointsListToYailList((List<? extends IGeoPoint>)iterator.next())); 
    return YailList.makeList(linkedList);
  }
  
  public static GeoPoint pointFromYailList(YailList paramYailList) {
    if (paramYailList.length() < 3)
      throw new DispatchableError(3409, new Object[] { Integer.valueOf(2), Integer.valueOf(paramYailList.length() - 1) }); 
    try {
      return coerceToPoint(paramYailList.get(1), paramYailList.get(2));
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new DispatchableError(3405, new Object[] { paramYailList.get(1), paramYailList.get(2) });
    } 
  }
  
  public static List<GeoPoint> pointsFromYailList(YailList paramYailList) {
    ArrayList<GeoPoint> arrayList = new ArrayList();
    Iterator<T> iterator = paramYailList.iterator();
    int i = 1;
    iterator.next();
    while (iterator.hasNext()) {
      try {
        arrayList.add(pointFromYailList(TypeUtil.<YailList>castNotNull(iterator.next(), YailList.class, "list")));
        i++;
      } catch (DispatchableError dispatchableError) {
        throw IterationError.fromError(i, dispatchableError);
      } 
    } 
    return arrayList;
  }
  
  public static YailList pointsListToYailList(List<? extends IGeoPoint> paramList) {
    ArrayList<YailList> arrayList = new ArrayList();
    Iterator<? extends IGeoPoint> iterator = paramList.iterator();
    while (iterator.hasNext())
      arrayList.add(asYailList(iterator.next())); 
    return YailList.makeList(arrayList);
  }
  
  public static Coordinate[] pointsToCoordinates(List<GeoPoint> paramList) {
    boolean bool = ((GeoPoint)paramList.get(0)).equals(paramList.get(paramList.size() - 1));
    int j = paramList.size();
    if (bool) {
      i = 0;
    } else {
      i = 1;
    } 
    Coordinate[] arrayOfCoordinate = new Coordinate[i + j];
    int i = 0;
    Iterator<GeoPoint> iterator = paramList.iterator();
    while (iterator.hasNext()) {
      arrayOfCoordinate[i] = geoPointToCoordinate(iterator.next());
      i++;
    } 
    if (!bool)
      arrayOfCoordinate[i] = arrayOfCoordinate[0]; 
    return arrayOfCoordinate;
  }
  
  public static List<GeoPoint> polygonToList(JSONArray paramJSONArray) throws JSONException {
    ArrayList<GeoPoint> arrayList = new ArrayList(paramJSONArray.length());
    if (paramJSONArray.length() < 3)
      throw new DispatchableError(3404, new Object[] { "Too few points in Polygon, expected 3." }); 
    for (int i = 0; i < paramJSONArray.length(); i++) {
      JSONArray jSONArray = paramJSONArray.getJSONArray(i);
      if (jSONArray.length() < 2)
        throw new JSONException("Invalid number of dimensions in polygon, expected 2."); 
      if (jSONArray.length() == 2) {
        arrayList.add(new GeoPoint(jSONArray.getDouble(0), jSONArray.getDouble(1)));
      } else {
        arrayList.add(new GeoPoint(jSONArray.getDouble(0), jSONArray.getDouble(1), jSONArray.getDouble(2)));
      } 
    } 
    return arrayList;
  }
  
  public static Polygon ringToPolygon(List<GeoPoint> paramList) {
    return FACTORY.createPolygon(geoPointsToLinearRing(paramList));
  }
  
  public static Polygon ringToPolygon(List<GeoPoint> paramList, List<List<GeoPoint>> paramList1) {
    LinearRing linearRing = geoPointsToLinearRing(paramList);
    LinearRing[] arrayOfLinearRing = new LinearRing[paramList1.size()];
    int i = 0;
    Iterator<List<GeoPoint>> iterator = paramList1.iterator();
    while (iterator.hasNext()) {
      arrayOfLinearRing[i] = geoPointsToLinearRing(iterator.next());
      i++;
    } 
    return FACTORY.createPolygon(linearRing, arrayOfLinearRing);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/GeometryUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
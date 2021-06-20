package com.google.appinventor.components.runtime.util;

import android.text.TextUtils;
import android.util.Log;
import com.google.appinventor.components.runtime.LineString;
import com.google.appinventor.components.runtime.Marker;
import com.google.appinventor.components.runtime.Polygon;
import com.google.common.annotations.VisibleForTesting;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;

public final class GeoJSONUtil {
  private static final int ERROR_CODE_MALFORMED_GEOJSON = -3;
  
  private static final String ERROR_MALFORMED_GEOJSON = "Malformed GeoJSON response. Expected FeatureCollection as root element.";
  
  private static final String ERROR_UNKNOWN_TYPE = "Unrecognized/invalid type in JSON object";
  
  private static final String GEOJSON_COORDINATES = "coordinates";
  
  private static final String GEOJSON_FEATURE = "Feature";
  
  private static final String GEOJSON_FEATURECOLLECTION = "FeatureCollection";
  
  private static final String GEOJSON_FEATURES = "features";
  
  private static final String GEOJSON_GEOMETRY = "geometry";
  
  private static final String GEOJSON_GEOMETRYCOLLECTION = "GeometryCollection";
  
  private static final String GEOJSON_PROPERTIES = "properties";
  
  private static final String GEOJSON_TYPE = "type";
  
  private static final int KEY = 1;
  
  private static final int LATITUDE = 2;
  
  private static final int LONGITUDE = 1;
  
  private static final String PROPERTY_ANCHOR_HORIZONTAL = "anchorHorizontal";
  
  private static final String PROPERTY_ANCHOR_VERTICAL = "anchorVertical";
  
  private static final String PROPERTY_DESCRIPTION = "description";
  
  private static final String PROPERTY_DRAGGABLE = "draggable";
  
  private static final String PROPERTY_FILL = "fill";
  
  private static final String PROPERTY_FILL_OPACITY = "fill-opacity";
  
  private static final String PROPERTY_HEIGHT = "height";
  
  private static final String PROPERTY_IMAGE = "image";
  
  private static final String PROPERTY_INFOBOX = "infobox";
  
  private static final String PROPERTY_STROKE = "stroke";
  
  private static final String PROPERTY_STROKE_OPACITY = "stroke-opacity";
  
  private static final String PROPERTY_STROKE_WIDTH = "stroke-width";
  
  private static final String PROPERTY_TITLE = "title";
  
  private static final String PROPERTY_VISIBLE = "visible";
  
  private static final String PROPERTY_WIDTH = "width";
  
  private static final Map<String, PropertyApplication> SUPPORTED_PROPERTIES;
  
  private static final int VALUE = 2;
  
  private static final Map<String, Integer> colors = new HashMap<String, Integer>();
  
  static {
    colors.put("black", Integer.valueOf(-16777216));
    colors.put("blue", Integer.valueOf(-16776961));
    colors.put("cyan", Integer.valueOf(-16711681));
    colors.put("darkgray", Integer.valueOf(-12303292));
    colors.put("gray", Integer.valueOf(-7829368));
    colors.put("green", Integer.valueOf(-16711936));
    colors.put("lightgray", Integer.valueOf(-3355444));
    colors.put("magenta", Integer.valueOf(-65281));
    colors.put("orange", Integer.valueOf(-14336));
    colors.put("pink", Integer.valueOf(-20561));
    colors.put("red", Integer.valueOf(-65536));
    colors.put("white", Integer.valueOf(-1));
    colors.put("yellow", Integer.valueOf(-256));
    SUPPORTED_PROPERTIES = new HashMap<String, PropertyApplication>();
    SUPPORTED_PROPERTIES.put("anchorHorizontal".toLowerCase(), new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            if (param1MapFeature instanceof MapFactory.MapMarker)
              ((MapFactory.MapMarker)param1MapFeature).AnchorHorizontal(GeoJSONUtil.parseIntegerOrString(param1Object)); 
          }
        });
    SUPPORTED_PROPERTIES.put("anchorVertical".toLowerCase(), new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            if (param1MapFeature instanceof MapFactory.MapMarker)
              ((MapFactory.MapMarker)param1MapFeature).AnchorHorizontal(); 
          }
        });
    SUPPORTED_PROPERTIES.put("description", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            param1MapFeature.Description(param1Object.toString());
          }
        });
    SUPPORTED_PROPERTIES.put("draggable", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            param1MapFeature.Draggable(GeoJSONUtil.parseBooleanOrString(param1Object));
          }
        });
    SUPPORTED_PROPERTIES.put("fill", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            if (param1MapFeature instanceof MapFactory.HasFill) {
              int i;
              MapFactory.HasFill hasFill = (MapFactory.HasFill)param1MapFeature;
              if (param1Object instanceof Number) {
                i = ((Number)param1Object).intValue();
              } else {
                i = GeoJSONUtil.parseColor(param1Object.toString());
              } 
              hasFill.FillColor(i);
            } 
          }
        });
    SUPPORTED_PROPERTIES.put("fill-opacity", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            if (param1MapFeature instanceof MapFactory.HasFill)
              ((MapFactory.HasFill)param1MapFeature).FillOpacity(GeoJSONUtil.parseFloatOrString(param1Object)); 
          }
        });
    SUPPORTED_PROPERTIES.put("height", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            if (param1MapFeature instanceof MapFactory.MapMarker)
              ((MapFactory.MapMarker)param1MapFeature).Height(GeoJSONUtil.parseIntegerOrString(param1Object)); 
          }
        });
    SUPPORTED_PROPERTIES.put("image", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            if (param1MapFeature instanceof MapFactory.MapMarker)
              ((MapFactory.MapMarker)param1MapFeature).ImageAsset(param1Object.toString()); 
          }
        });
    SUPPORTED_PROPERTIES.put("infobox", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            param1MapFeature.EnableInfobox(GeoJSONUtil.parseBooleanOrString(param1Object));
          }
        });
    SUPPORTED_PROPERTIES.put("stroke", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            if (param1MapFeature instanceof MapFactory.HasStroke) {
              int i;
              MapFactory.HasStroke hasStroke = (MapFactory.HasStroke)param1MapFeature;
              if (param1Object instanceof Number) {
                i = ((Number)param1Object).intValue();
              } else {
                i = GeoJSONUtil.parseColor(param1Object.toString());
              } 
              hasStroke.StrokeColor(i);
            } 
          }
        });
    SUPPORTED_PROPERTIES.put("stroke-opacity", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            if (param1MapFeature instanceof MapFactory.HasStroke)
              ((MapFactory.HasStroke)param1MapFeature).StrokeOpacity(GeoJSONUtil.parseFloatOrString(param1Object)); 
          }
        });
    SUPPORTED_PROPERTIES.put("stroke-width", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            if (param1MapFeature instanceof MapFactory.HasStroke)
              ((MapFactory.HasStroke)param1MapFeature).StrokeWidth(GeoJSONUtil.parseIntegerOrString(param1Object)); 
          }
        });
    SUPPORTED_PROPERTIES.put("title", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            param1MapFeature.Title(param1Object.toString());
          }
        });
    SUPPORTED_PROPERTIES.put("width", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            if (param1MapFeature instanceof MapFactory.MapMarker)
              ((MapFactory.MapMarker)param1MapFeature).Width(GeoJSONUtil.parseIntegerOrString(param1Object)); 
          }
        });
    SUPPORTED_PROPERTIES.put("visible", new PropertyApplication() {
          public void apply(MapFactory.MapFeature param1MapFeature, Object param1Object) {
            param1MapFeature.Visible(GeoJSONUtil.parseBooleanOrString(param1Object));
          }
        });
  }
  
  @VisibleForTesting
  static int charToHex(char paramChar) {
    if ('0' <= paramChar && paramChar <= '9')
      return paramChar - 48; 
    if ('a' <= paramChar && paramChar <= 'f')
      return paramChar - 97 + 10; 
    if ('A' <= paramChar && paramChar <= 'F')
      return paramChar - 65 + 10; 
    throw new IllegalArgumentException("Invalid hex character. Expected [0-9A-Fa-f].");
  }
  
  public static List<YailList> getGeoJSONFeatures(String paramString1, String paramString2) throws JSONException {
    JSONArray jSONArray = (new JSONObject(stripBOM(paramString2))).getJSONArray("features");
    ArrayList<YailList> arrayList = new ArrayList();
    for (int i = 0; i < jSONArray.length(); i++)
      arrayList.add(jsonObjectToYail(paramString1, jSONArray.getJSONObject(i))); 
    return arrayList;
  }
  
  public static String getGeoJSONType(String paramString1, String paramString2) throws JSONException {
    return (new JSONObject(stripBOM(paramString1))).optString(paramString2);
  }
  
  private static YailList jsonArrayToYail(String paramString, JSONArray paramJSONArray) throws JSONException {
    ArrayList<Object> arrayList = new ArrayList();
    for (int i = 0; i < paramJSONArray.length(); i++) {
      Object object = paramJSONArray.get(i);
      if (object instanceof Boolean || object instanceof Integer || object instanceof Long || object instanceof Double || object instanceof String) {
        arrayList.add(object);
      } else if (object instanceof JSONArray) {
        arrayList.add(jsonArrayToYail(paramString, (JSONArray)object));
      } else if (object instanceof JSONObject) {
        arrayList.add(jsonObjectToYail(paramString, (JSONObject)object));
      } else if (!JSONObject.NULL.equals(object)) {
        Log.wtf(paramString, "Unrecognized/invalid type in JSON object: " + object.getClass());
        throw new IllegalArgumentException("Unrecognized/invalid type in JSON object");
      } 
    } 
    return YailList.makeList(arrayList);
  }
  
  private static YailList jsonObjectToYail(String paramString, JSONObject paramJSONObject) throws JSONException {
    ArrayList<YailList> arrayList = new ArrayList();
    Iterator<String> iterator = paramJSONObject.keys();
    while (iterator.hasNext()) {
      String str = iterator.next();
      Object object = paramJSONObject.get(str);
      if (object instanceof Boolean || object instanceof Integer || object instanceof Long || object instanceof Double || object instanceof String) {
        arrayList.add(YailList.makeList(new Object[] { str, object }));
        continue;
      } 
      if (object instanceof JSONArray) {
        arrayList.add(YailList.makeList(new Object[] { str, jsonArrayToYail(paramString, (JSONArray)object) }));
        continue;
      } 
      if (object instanceof JSONObject) {
        arrayList.add(YailList.makeList(new Object[] { str, jsonObjectToYail(paramString, (JSONObject)object) }));
        continue;
      } 
      if (!JSONObject.NULL.equals(object)) {
        Log.wtf(paramString, "Unrecognized/invalid type in JSON object: " + object.getClass());
        throw new IllegalArgumentException("Unrecognized/invalid type in JSON object");
      } 
    } 
    return YailList.makeList(arrayList);
  }
  
  private static MapFactory.MapLineString lineStringFromGeoJSON(MapFactory.MapFeatureContainer paramMapFeatureContainer, YailList paramYailList) {
    if (paramYailList.size() < 2)
      throw new IllegalArgumentException("Too few coordinates supplied in GeoJSON"); 
    LineString lineString = new LineString(paramMapFeatureContainer);
    lineString.Points(swapCoordinates(paramYailList));
    return (MapFactory.MapLineString)lineString;
  }
  
  private static MapFactory.MapMarker markerFromGeoJSON(MapFactory.MapFeatureContainer paramMapFeatureContainer, YailList paramYailList) {
    if (paramYailList.length() != 3)
      throw new IllegalArgumentException("Invalid coordinate supplied in GeoJSON"); 
    Marker marker = new Marker(paramMapFeatureContainer);
    marker.Latitude(((Number)paramYailList.get(2)).doubleValue());
    marker.Longitude(((Number)paramYailList.get(1)).doubleValue());
    return (MapFactory.MapMarker)marker;
  }
  
  private static MapFactory.MapPolygon multipolygonFromGeoJSON(MapFactory.MapFeatureContainer paramMapFeatureContainer, YailList paramYailList) {
    Polygon polygon = new Polygon(paramMapFeatureContainer);
    ArrayList<YailList> arrayList1 = new ArrayList();
    ArrayList<YailList> arrayList2 = new ArrayList();
    Iterator<T> iterator = paramYailList.iterator();
    iterator.next();
    while (iterator.hasNext()) {
      YailList yailList = (YailList)iterator.next();
      arrayList1.add(swapCoordinates((YailList)yailList.get(1)));
      arrayList2.add(YailList.makeList((List)swapNestedCoordinates((LList)((Pair)yailList.getCdr()).getCdr())));
    } 
    polygon.Points(YailList.makeList(arrayList1));
    polygon.HolePoints(YailList.makeList(arrayList2));
    polygon.Initialize();
    return (MapFactory.MapPolygon)polygon;
  }
  
  @VisibleForTesting
  static boolean parseBooleanOrString(Object paramObject) {
    if (paramObject instanceof Boolean)
      return ((Boolean)paramObject).booleanValue(); 
    if (paramObject instanceof String)
      return (!"false".equalsIgnoreCase((String)paramObject) && ((String)paramObject).length() != 0); 
    if (paramObject instanceof gnu.lists.FString)
      return parseBooleanOrString(paramObject.toString()); 
    throw new IllegalArgumentException();
  }
  
  @VisibleForTesting
  static int parseColor(String paramString) {
    paramString = paramString.toLowerCase();
    Integer integer = colors.get(paramString);
    return (integer != null) ? integer.intValue() : (paramString.startsWith("#") ? parseColorHex(paramString.substring(1)) : (paramString.startsWith("&h") ? parseColorHex(paramString.substring(2)) : -65536));
  }
  
  @VisibleForTesting
  static int parseColorHex(String paramString) {
    int i;
    int j = 0;
    if (paramString.length() == 3) {
      j = -16777216;
      int k = 0;
      while (true) {
        i = j;
        if (k < paramString.length()) {
          i = charToHex(paramString.charAt(k));
          j |= (i << 4 | i) << (2 - k) * 8;
          k++;
          continue;
        } 
        break;
      } 
    } else if (paramString.length() == 6) {
      j = -16777216;
      int k = 0;
      while (true) {
        i = j;
        if (k < 3) {
          j |= (charToHex(paramString.charAt(k * 2)) << 4 | charToHex(paramString.charAt(k * 2 + 1))) << (2 - k) * 8;
          k++;
          continue;
        } 
        break;
      } 
    } else if (paramString.length() == 8) {
      int k = 0;
      while (true) {
        i = j;
        if (k < 4) {
          j |= (charToHex(paramString.charAt(k * 2)) << 4 | charToHex(paramString.charAt(k * 2 + 1))) << (3 - k) * 8;
          k++;
          continue;
        } 
        break;
      } 
    } else {
      throw new IllegalArgumentException();
    } 
    return i;
  }
  
  @VisibleForTesting
  static float parseFloatOrString(Object paramObject) {
    if (paramObject instanceof Number)
      return ((Number)paramObject).floatValue(); 
    if (paramObject instanceof String)
      return Float.parseFloat((String)paramObject); 
    if (paramObject instanceof gnu.lists.FString)
      return Float.parseFloat(paramObject.toString()); 
    throw new IllegalArgumentException();
  }
  
  @VisibleForTesting
  static int parseIntegerOrString(Object paramObject) {
    if (paramObject instanceof Number)
      return ((Number)paramObject).intValue(); 
    if (paramObject instanceof String)
      return Integer.parseInt((String)paramObject); 
    if (paramObject instanceof gnu.lists.FString)
      return Integer.parseInt(paramObject.toString()); 
    throw new IllegalArgumentException();
  }
  
  private static MapFactory.MapPolygon polygonFromGeoJSON(MapFactory.MapFeatureContainer paramMapFeatureContainer, YailList paramYailList) {
    Polygon polygon = new Polygon(paramMapFeatureContainer);
    Iterator<T> iterator = paramYailList.iterator();
    iterator.next();
    polygon.Points(swapCoordinates((YailList)iterator.next()));
    if (iterator.hasNext())
      polygon.HolePoints(YailList.makeList((List)swapNestedCoordinates((LList)((Pair)paramYailList.getCdr()).getCdr()))); 
    polygon.Initialize();
    return (MapFactory.MapPolygon)polygon;
  }
  
  private static MapFactory.MapFeature processCoordinates(MapFactory.MapFeatureContainer paramMapFeatureContainer, String paramString, YailList paramYailList) {
    if ("Point".equals(paramString))
      return markerFromGeoJSON(paramMapFeatureContainer, paramYailList); 
    if ("LineString".equals(paramString))
      return lineStringFromGeoJSON(paramMapFeatureContainer, paramYailList); 
    if ("Polygon".equals(paramString))
      return polygonFromGeoJSON(paramMapFeatureContainer, paramYailList); 
    if ("MultiPolygon".equals(paramString))
      return multipolygonFromGeoJSON(paramMapFeatureContainer, paramYailList); 
    throw new IllegalArgumentException();
  }
  
  public static MapFactory.MapFeature processGeoJSONFeature(String paramString, MapFactory.MapFeatureContainer paramMapFeatureContainer, YailList paramYailList) {
    String str = null;
    YailList yailList1 = null;
    YailList yailList2 = null;
    Iterator<YailList> iterator = ((LList)paramYailList.getCdr()).iterator();
    paramYailList = yailList2;
    while (iterator.hasNext()) {
      YailList yailList = iterator.next();
      String str1 = yailList.getString(0);
      Object object = yailList.getObject(1);
      if ("type".equals(str1)) {
        str = (String)object;
        continue;
      } 
      if ("geometry".equals(str1)) {
        yailList1 = (YailList)object;
        continue;
      } 
      if ("properties".equals(str1)) {
        paramYailList = (YailList)object;
        continue;
      } 
      Log.w(paramString, String.format("Unsupported field \"%s\" in JSON format", new Object[] { str1 }));
    } 
    if (!"Feature".equals(str))
      throw new IllegalArgumentException(String.format("Unknown type \"%s\"", new Object[] { str })); 
    if (yailList1 == null)
      throw new IllegalArgumentException("No geometry defined for feature."); 
    MapFactory.MapFeature mapFeature = processGeometry(paramString, paramMapFeatureContainer, yailList1);
    if (paramYailList != null)
      processProperties(paramString, mapFeature, paramYailList); 
    return mapFeature;
  }
  
  private static MapFactory.MapFeature processGeometry(String paramString, MapFactory.MapFeatureContainer paramMapFeatureContainer, YailList paramYailList) {
    String str;
    YailList yailList2 = null;
    YailList yailList1 = null;
    Iterator<YailList> iterator = ((LList)paramYailList.getCdr()).iterator();
    paramYailList = yailList2;
    while (iterator.hasNext()) {
      YailList yailList = iterator.next();
      String str1 = yailList.getString(0);
      Object object = yailList.getObject(1);
      if ("type".equals(str1)) {
        str = (String)object;
        continue;
      } 
      if ("coordinates".equals(str1)) {
        yailList1 = (YailList)object;
        continue;
      } 
      Log.w(paramString, String.format("Unsupported field \"%s\" in JSON format", new Object[] { str1 }));
    } 
    if (yailList1 == null)
      throw new IllegalArgumentException("No coordinates found in GeoJSON Feature"); 
    return processCoordinates(paramMapFeatureContainer, str, yailList1);
  }
  
  private static void processProperties(String paramString, MapFactory.MapFeature paramMapFeature, YailList paramYailList) {
    for (T t : paramYailList) {
      if (t instanceof YailList) {
        YailList yailList = (YailList)t;
        String str = yailList.get(1).toString();
        PropertyApplication propertyApplication = SUPPORTED_PROPERTIES.get(str.toLowerCase());
        if (propertyApplication != null) {
          propertyApplication.apply(paramMapFeature, yailList.get(2));
          continue;
        } 
        Log.i(paramString, String.format("Ignoring GeoJSON property \"%s\"", new Object[] { str }));
      } 
    } 
  }
  
  private static String stripBOM(String paramString) {
    String str = paramString;
    if (paramString.charAt(0) == '﻿')
      str = paramString.substring(1); 
    return str;
  }
  
  public static YailList swapCoordinates(YailList paramYailList) {
    Iterator<T> iterator = paramYailList.iterator();
    iterator.next();
    while (iterator.hasNext()) {
      YailList yailList = (YailList)iterator.next();
      Object object = yailList.get(1);
      Pair pair = (Pair)yailList.getCdr();
      pair.setCar(yailList.get(2));
      ((Pair)pair.getCdr()).setCar(object);
    } 
    return paramYailList;
  }
  
  public static <E> List<List<E>> swapCoordinates2(List<List<E>> paramList) {
    for (List<Object> list : paramList) {
      Object object = list.get(0);
      list.set(0, list.get(1));
      list.set(1, object);
    } 
    return paramList;
  }
  
  public static LList swapNestedCoordinates(LList paramLList) {
    for (LList lList = paramLList; !lList.isEmpty(); lList = (LList)((Pair)lList).getCdr())
      swapCoordinates((YailList)lList.get(0)); 
    return paramLList;
  }
  
  public static void writeFeaturesAsGeoJSON(List<MapFactory.MapFeature> paramList, String paramString) throws IOException {
    FeatureWriter featureWriter1;
    FeatureWriter featureWriter2 = null;
    try {
      FeatureWriter featureWriter;
      PrintStream printStream = new PrintStream(new FileOutputStream(paramString));
      try {
        featureWriter2 = new FeatureWriter(printStream);
        printStream.print("{\"type\": \"FeatureCollection\", \"features\":[");
        Iterator<MapFactory.MapFeature> iterator = paramList.iterator();
        if (iterator.hasNext()) {
          ((MapFactory.MapFeature)iterator.next()).accept(featureWriter2, new Object[0]);
          while (iterator.hasNext()) {
            MapFactory.MapFeature mapFeature = iterator.next();
            printStream.print(',');
            mapFeature.accept(featureWriter2, new Object[0]);
          } 
        } 
      } finally {
        featureWriter2 = null;
        PrintStream printStream1 = printStream;
        featureWriter = featureWriter2;
        IOUtils.closeQuietly("GeoJSONUtil", printStream1);
      } 
      featureWriter.print("]}");
      return;
    } finally {
      paramString = null;
    } 
    IOUtils.closeQuietly("GeoJSONUtil", (Closeable)featureWriter1);
    throw paramString;
  }
  
  private static final class FeatureWriter implements MapFactory.MapFeatureVisitor<Void> {
    private final PrintStream out;
    
    private FeatureWriter(PrintStream param1PrintStream) {
      this.out = param1PrintStream;
    }
    
    private static boolean hasAltitude(GeoPoint param1GeoPoint) {
      return (Double.compare(0.0D, param1GeoPoint.getAltitude()) != 0);
    }
    
    private void writeColorProperty(String param1String, int param1Int) {
      this.out.print(",\"");
      this.out.print(param1String);
      this.out.print("\":\"&H");
      param1String = Integer.toHexString(param1Int);
      for (param1Int = 8; param1Int > param1String.length(); param1Int--)
        this.out.print("0"); 
      this.out.print(param1String);
      this.out.print("\"");
    }
    
    private void writeLineGeometry(MapFactory.MapLineString param1MapLineString) {
      this.out.print("\"geometry\":{\"type\":\"LineString\",\"coordinates\":[");
      writePoints(param1MapLineString.getPoints());
      this.out.print("]}");
    }
    
    private void writeMultipolygonGeometryNoHoles(MapFactory.MapPolygon param1MapPolygon) {
      this.out.print("\"geometry\":{\"type\":\"MultiPolygon\",\"coordinates\":[");
      Iterator<List<GeoPoint>> iterator1 = param1MapPolygon.getPoints().iterator();
      Iterator<List<List<GeoPoint>>> iterator = param1MapPolygon.getHolePoints().iterator();
      for (boolean bool = true; iterator1.hasNext(); bool = false) {
        if (!bool)
          this.out.print(","); 
        this.out.print("[");
        writePoints(iterator1.next());
        if (iterator.hasNext())
          for (List<GeoPoint> list : iterator.next()) {
            this.out.print(",");
            writePoints(list);
          }  
        this.out.print("]");
      } 
      this.out.print("]}");
    }
    
    private void writePointGeometry(GeoPoint param1GeoPoint) {
      this.out.print("\"geometry\":{\"type\":\"Point\",\"coordinates\":[");
      this.out.print(param1GeoPoint.getLongitude());
      this.out.print(",");
      this.out.print(param1GeoPoint.getLatitude());
      if (hasAltitude(param1GeoPoint)) {
        this.out.print(",");
        this.out.print(param1GeoPoint.getAltitude());
      } 
      this.out.print("]}");
    }
    
    private void writePoints(List<GeoPoint> param1List) {
      boolean bool = true;
      for (GeoPoint geoPoint : param1List) {
        if (!bool)
          this.out.print(','); 
        this.out.print("[");
        this.out.print(geoPoint.getLongitude());
        this.out.print(",");
        this.out.print(geoPoint.getLatitude());
        if (hasAltitude(geoPoint)) {
          this.out.print(",");
          this.out.print(geoPoint.getAltitude());
        } 
        this.out.print("]");
        bool = false;
      } 
    }
    
    private void writePolygonGeometry(MapFactory.MapPolygon param1MapPolygon) {
      if (param1MapPolygon.getPoints().size() > 1) {
        writeMultipolygonGeometryNoHoles(param1MapPolygon);
        return;
      } 
      writePolygonGeometryNoHoles(param1MapPolygon);
    }
    
    private void writePolygonGeometryNoHoles(MapFactory.MapPolygon param1MapPolygon) {
      this.out.print("\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[");
      writePoints(param1MapPolygon.getPoints().get(0));
      if (!param1MapPolygon.getHolePoints().isEmpty())
        for (List<GeoPoint> list : param1MapPolygon.getHolePoints().get(0)) {
          this.out.print(",");
          writePoints(list);
        }  
      this.out.print("]}");
    }
    
    private void writeProperties(MapFactory.HasFill param1HasFill) {
      writeColorProperty("fill", param1HasFill.FillColor());
      writeProperty("fill-opacity", Float.valueOf(param1HasFill.FillOpacity()));
    }
    
    private void writeProperties(MapFactory.HasStroke param1HasStroke) {
      writeColorProperty("stroke", param1HasStroke.StrokeColor());
      writeProperty("stroke-opacity", Float.valueOf(param1HasStroke.StrokeOpacity()));
      writeProperty("stroke-width", Integer.valueOf(param1HasStroke.StrokeWidth()));
    }
    
    private void writeProperties(MapFactory.MapFeature param1MapFeature) {
      writeProperty("description", param1MapFeature.Description());
      writeProperty("draggable", Boolean.valueOf(param1MapFeature.Draggable()));
      writeProperty("infobox", Boolean.valueOf(param1MapFeature.EnableInfobox()));
      writeProperty("title", param1MapFeature.Title());
      writeProperty("visible", Boolean.valueOf(param1MapFeature.Visible()));
    }
    
    private void writePropertiesHeader(String param1String) {
      this.out.print(",\"properties\":{\"$Type\":\"" + param1String + "\"");
    }
    
    private void writeProperty(String param1String, Object param1Object) {
      try {
        param1Object = JsonUtil.getJsonRepresentation(param1Object);
        this.out.print(",\"");
        this.out.print(param1String);
        this.out.print("\":");
        this.out.print((String)param1Object);
        return;
      } catch (JSONException jSONException) {
        Log.w("GeoJSONUtil", "Unable to serialize the value of \"" + param1String + "\" as JSON", (Throwable)jSONException);
        return;
      } 
    }
    
    private void writeProperty(String param1String1, String param1String2) {
      if (param1String2 == null || TextUtils.isEmpty(param1String2))
        return; 
      writeProperty(param1String1, param1String2);
    }
    
    private void writeType(String param1String) {
      this.out.print("\"type\":\"");
      this.out.print(param1String);
      this.out.print("\"");
    }
    
    public Void visit(MapFactory.MapCircle param1MapCircle, Object... param1VarArgs) {
      this.out.print("{");
      writeType("Feature");
      this.out.print(',');
      writePointGeometry(param1MapCircle.getCentroid());
      writePropertiesHeader(param1MapCircle.getClass().getName());
      writeProperties(param1MapCircle);
      writeProperties(param1MapCircle);
      writeProperties(param1MapCircle);
      this.out.print("}}");
      return null;
    }
    
    public Void visit(MapFactory.MapLineString param1MapLineString, Object... param1VarArgs) {
      this.out.print("{");
      writeType("Feature");
      this.out.print(',');
      writeLineGeometry(param1MapLineString);
      writePropertiesHeader(param1MapLineString.getClass().getName());
      writeProperties(param1MapLineString);
      writeProperties(param1MapLineString);
      this.out.print("}}");
      return null;
    }
    
    public Void visit(MapFactory.MapMarker param1MapMarker, Object... param1VarArgs) {
      this.out.print("{");
      writeType("Feature");
      this.out.print(',');
      writePointGeometry(param1MapMarker.getCentroid());
      writePropertiesHeader(param1MapMarker.getClass().getName());
      writeProperties(param1MapMarker);
      writeProperties(param1MapMarker);
      writeProperties(param1MapMarker);
      writeProperty("anchorHorizontal", Integer.valueOf(param1MapMarker.AnchorHorizontal()));
      writeProperty("anchorVertical", Integer.valueOf(param1MapMarker.AnchorVertical()));
      writeProperty("height", Integer.valueOf(param1MapMarker.Height()));
      writeProperty("image", param1MapMarker.ImageAsset());
      writeProperty("width", Integer.valueOf(param1MapMarker.Width()));
      this.out.print("}}");
      return null;
    }
    
    public Void visit(MapFactory.MapPolygon param1MapPolygon, Object... param1VarArgs) {
      this.out.print("{");
      writeType("Feature");
      this.out.print(',');
      writePolygonGeometry(param1MapPolygon);
      writePropertiesHeader(param1MapPolygon.getClass().getName());
      writeProperties(param1MapPolygon);
      writeProperties(param1MapPolygon);
      writeProperties(param1MapPolygon);
      this.out.print("}}");
      return null;
    }
    
    public Void visit(MapFactory.MapRectangle param1MapRectangle, Object... param1VarArgs) {
      this.out.print("{");
      writeType("Feature");
      this.out.print(",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[");
      this.out.print("[" + param1MapRectangle.WestLongitude() + "," + param1MapRectangle.NorthLatitude() + "],");
      this.out.print("[" + param1MapRectangle.WestLongitude() + "," + param1MapRectangle.SouthLatitude() + "],");
      this.out.print("[" + param1MapRectangle.EastLongitude() + "," + param1MapRectangle.SouthLatitude() + "],");
      this.out.print("[" + param1MapRectangle.EastLongitude() + "," + param1MapRectangle.NorthLatitude() + "],");
      this.out.print("[" + param1MapRectangle.WestLongitude() + "," + param1MapRectangle.NorthLatitude() + "]]}");
      writePropertiesHeader(param1MapRectangle.getClass().getName());
      writeProperties(param1MapRectangle);
      writeProperties(param1MapRectangle);
      writeProperties(param1MapRectangle);
      writeProperty("NorthLatitude", Double.valueOf(param1MapRectangle.NorthLatitude()));
      writeProperty("WestLongitude", Double.valueOf(param1MapRectangle.WestLongitude()));
      writeProperty("SouthLatitude", Double.valueOf(param1MapRectangle.SouthLatitude()));
      writeProperty("EastLongitude", Double.valueOf(param1MapRectangle.EastLongitude()));
      this.out.print("}}");
      return null;
    }
  }
  
  private static interface PropertyApplication {
    void apply(MapFactory.MapFeature param1MapFeature, Object param1Object);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/GeoJSONUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
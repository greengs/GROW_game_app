package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailDictionary;
import com.google.appinventor.components.runtime.util.YailList;
import com.google.appinventor.components.runtime.util.YailObject;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.osmdroid.util.GeoPoint;

@DesignerComponent(category = ComponentCategory.MAPS, description = "Navigation", iconName = "images/navigation.png", nonVisible = true, version = 1)
@SimpleObject
@UsesLibraries({"osmdroid.jar"})
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class Navigation extends AndroidNonvisibleComponent implements Component {
  public static final String OPEN_ROUTE_SERVICE_URL = "https://api.openrouteservice.org/v2/directions/";
  
  private static final String TAG = "Navigation";
  
  private String apiKey = "";
  
  private GeoPoint endLocation = new GeoPoint(0.0D, 0.0D);
  
  private String language = "en";
  
  private YailDictionary lastResponse = YailDictionary.makeDictionary();
  
  private TransportMethod method = TransportMethod.DEFAULT;
  
  private String serviceUrl = "https://api.openrouteservice.org/v2/directions/";
  
  private GeoPoint startLocation = new GeoPoint(0.0D, 0.0D);
  
  public Navigation(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
  }
  
  private Double[][] getCoordinates(GeoPoint paramGeoPoint1, GeoPoint paramGeoPoint2) {
    Double[][] arrayOfDouble = (Double[][])Array.newInstance(Double.class, new int[] { 2, 2 });
    arrayOfDouble[0][0] = Double.valueOf(paramGeoPoint1.getLongitude());
    arrayOfDouble[0][1] = Double.valueOf(paramGeoPoint1.getLatitude());
    arrayOfDouble[1][0] = Double.valueOf(paramGeoPoint2.getLongitude());
    arrayOfDouble[1][1] = Double.valueOf(paramGeoPoint2.getLatitude());
    return arrayOfDouble;
  }
  
  private List<?> getDirections(YailDictionary paramYailDictionary) {
    return YailDictionary.walkKeyPath((YailObject)paramYailDictionary, Arrays.asList(new Object[] { "properties", "segments", YailDictionary.ALL, "steps", YailDictionary.ALL, "instruction" }));
  }
  
  private YailList getLineStringCoords(YailDictionary paramYailDictionary) {
    return GeoJSONUtil.swapCoordinates((YailList)paramYailDictionary.getObjectAtKeyPath(Arrays.asList(new String[] { "geometry", "coordinates" })));
  }
  
  private static String getResponseContent(HttpURLConnection paramHttpURLConnection) throws IOException {
    String str2 = paramHttpURLConnection.getContentEncoding();
    String str1 = str2;
    if (str2 == null)
      str1 = "UTF-8"; 
    Log.d("Navigation", Integer.toString(paramHttpURLConnection.getResponseCode()));
    InputStreamReader inputStreamReader = new InputStreamReader(paramHttpURLConnection.getInputStream(), str1);
    try {
      StringBuilder stringBuilder;
      int i = paramHttpURLConnection.getContentLength();
      if (i != -1) {
        stringBuilder = new StringBuilder(i);
      } else {
        stringBuilder = new StringBuilder();
      } 
      char[] arrayOfChar = new char[1024];
      while (true) {
        i = inputStreamReader.read(arrayOfChar);
        if (i != -1) {
          stringBuilder.append(arrayOfChar, 0, i);
          continue;
        } 
        return stringBuilder.toString();
      } 
    } finally {
      inputStreamReader.close();
    } 
  }
  
  private void performRequest(GeoPoint paramGeoPoint1, GeoPoint paramGeoPoint2, TransportMethod paramTransportMethod) throws IOException, JSONException {
    HttpURLConnection httpURLConnection = (HttpURLConnection)(new URL(this.serviceUrl + paramTransportMethod.method() + "/geojson/")).openConnection();
    httpURLConnection.setDoInput(true);
    httpURLConnection.setDoOutput(true);
    httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    httpURLConnection.setRequestMethod("POST");
    httpURLConnection.setRequestProperty("Authorization", this.apiKey);
    try {
      null = ("{\"coordinates\": " + JsonUtil.getJsonRepresentation(getCoordinates(paramGeoPoint1, paramGeoPoint2)) + ", \"language\": \"" + this.language + "\"}").getBytes("UTF-8");
      httpURLConnection.setFixedLengthStreamingMode(null.length);
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
      try {
        bufferedOutputStream.write(null, 0, null.length);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
      } finally {
        bufferedOutputStream.close();
      } 
      String str = getResponseContent(httpURLConnection);
      Log.d("Navigation", str);
      final YailDictionary response = (YailDictionary)JsonUtil.getObjectFromJson(str, true);
      YailList yailList = (YailList)yailDictionary.get("features");
      if (yailList.size() > 0) {
        YailDictionary yailDictionary1 = (YailDictionary)yailList.getObject(0);
        YailDictionary yailDictionary2 = (YailDictionary)yailDictionary1.getObjectAtKeyPath(Arrays.asList(new String[] { "properties", "summary" }));
        final double distance = ((Double)yailDictionary2.get("distance")).doubleValue();
        final double duration = ((Double)yailDictionary2.get("duration")).doubleValue();
        final YailList directions = YailList.makeList(getDirections(yailDictionary1));
        final YailList coordinates = getLineStringCoords(yailDictionary1);
        this.form.runOnUiThread(new Runnable() {
              public void run() {
                Navigation.access$202(Navigation.this, response);
                Navigation.this.GotDirections(directions, coordinates, distance, duration);
              }
            });
      } else {
        this.form.dispatchErrorOccurredEvent(this, "RequestDirections", 4004, new Object[0]);
      } 
      return;
    } catch (Exception exception) {
      this.form.dispatchErrorOccurredEvent(this, "RequestDirections", 4002, new Object[] { exception.getMessage() });
      exception.printStackTrace();
      return;
    } finally {
      httpURLConnection.disconnect();
    } 
  }
  
  @DesignerProperty(editorType = "string")
  @SimpleProperty(description = "API Key for Open Route Service.")
  public void ApiKey(String paramString) {
    this.apiKey = paramString;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The latitude of the end location.")
  public double EndLatitude() {
    return this.endLocation.getLatitude();
  }
  
  @DesignerProperty(defaultValue = "0.0", editorType = "latitude")
  @SimpleProperty
  public void EndLatitude(double paramDouble) {
    if (GeometryUtil.isValidLatitude(paramDouble)) {
      this.endLocation.setLatitude(paramDouble);
      return;
    } 
    getDispatchDelegate().dispatchErrorOccurredEvent(this, "EndLatitude", 3413, new Object[] { Double.valueOf(paramDouble) });
  }
  
  @SimpleProperty(description = "Set the end location.")
  public void EndLocation(MapFactory.MapFeature paramMapFeature) {
    GeoPoint geoPoint = paramMapFeature.getCentroid();
    double d1 = geoPoint.getLatitude();
    double d2 = geoPoint.getLongitude();
    if (!GeometryUtil.isValidLatitude(d1)) {
      getDispatchDelegate().dispatchErrorOccurredEvent(this, "SetEndLocation", 3413, new Object[] { Double.valueOf(d1) });
      return;
    } 
    if (!GeometryUtil.isValidLongitude(d2)) {
      getDispatchDelegate().dispatchErrorOccurredEvent(this, "SetEndLocation", 3414, new Object[] { Double.valueOf(d2) });
      return;
    } 
    this.endLocation.setCoords(d1, d2);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The longitude of the end location.")
  public double EndLongitude() {
    return this.endLocation.getLongitude();
  }
  
  @DesignerProperty(defaultValue = "0.0", editorType = "longitude")
  @SimpleProperty
  public void EndLongitude(double paramDouble) {
    if (GeometryUtil.isValidLongitude(paramDouble)) {
      this.endLocation.setLongitude(paramDouble);
      return;
    } 
    getDispatchDelegate().dispatchErrorOccurredEvent(this, "EndLongitude", 3414, new Object[] { Double.valueOf(paramDouble) });
  }
  
  @SimpleEvent(description = "Event triggered when the Openrouteservice returns the directions.")
  public void GotDirections(YailList paramYailList1, YailList paramYailList2, double paramDouble1, double paramDouble2) {
    Log.d("Navigation", "GotDirections");
    EventDispatcher.dispatchEvent(this, "GotDirections", new Object[] { paramYailList1, paramYailList2, Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
  }
  
  @SimpleProperty
  public String Language() {
    return this.language;
  }
  
  @DesignerProperty(defaultValue = "en")
  @SimpleProperty(description = "The language to use for textual directions.")
  public void Language(String paramString) {
    this.language = paramString;
  }
  
  @SimpleFunction(description = "Request directions from the routing service.")
  public void RequestDirections() {
    if (this.apiKey.equals("")) {
      this.form.dispatchErrorOccurredEvent(this, "Authorization", 4001, new Object[0]);
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            try {
              Navigation.this.performRequest(startLocation, endLocation, method);
              return;
            } catch (IOException iOException) {
              Navigation.this.form.dispatchErrorOccurredEvent(Navigation.this, "RequestDirections", 0, new Object[0]);
              return;
            } catch (JSONException jSONException) {
              Navigation.this.form.dispatchErrorOccurredEvent(Navigation.this, "RequestDirections", 0, new Object[0]);
              return;
            } 
          }
        });
  }
  
  @SimpleProperty(description = "Content of the last response as a dictionary.")
  public YailDictionary ResponseContent() {
    return this.lastResponse;
  }
  
  @SimpleProperty(userVisible = false)
  public void ServiceURL(String paramString) {
    this.serviceUrl = paramString;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The latitude of the start location.")
  public double StartLatitude() {
    return this.startLocation.getLatitude();
  }
  
  @DesignerProperty(defaultValue = "0.0", editorType = "latitude")
  @SimpleProperty
  public void StartLatitude(double paramDouble) {
    if (GeometryUtil.isValidLatitude(paramDouble)) {
      this.startLocation.setLatitude(paramDouble);
      return;
    } 
    getDispatchDelegate().dispatchErrorOccurredEvent(this, "StartLatitude", 3413, new Object[] { Double.valueOf(paramDouble) });
  }
  
  @SimpleProperty(description = "Set the start location.")
  public void StartLocation(MapFactory.MapFeature paramMapFeature) {
    GeoPoint geoPoint = paramMapFeature.getCentroid();
    double d1 = geoPoint.getLatitude();
    double d2 = geoPoint.getLongitude();
    if (!GeometryUtil.isValidLatitude(d1)) {
      getDispatchDelegate().dispatchErrorOccurredEvent(this, "SetStartLocation", 3413, new Object[] { Double.valueOf(d1) });
      return;
    } 
    if (!GeometryUtil.isValidLongitude(d2)) {
      getDispatchDelegate().dispatchErrorOccurredEvent(this, "SetStartLocation", 3414, new Object[] { Double.valueOf(d2) });
      return;
    } 
    this.startLocation.setCoords(d1, d2);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The longitude of the start location.")
  public double StartLongitude() {
    return this.startLocation.getLongitude();
  }
  
  @DesignerProperty(defaultValue = "0.0", editorType = "longitude")
  @SimpleProperty
  public void StartLongitude(double paramDouble) {
    if (GeometryUtil.isValidLongitude(paramDouble)) {
      this.startLocation.setLongitude(paramDouble);
      return;
    } 
    getDispatchDelegate().dispatchErrorOccurredEvent(this, "StartLongitude", 3414, new Object[] { Double.valueOf(paramDouble) });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String TransportationMethod() {
    return this.method.method();
  }
  
  @DesignerProperty(defaultValue = "foot-walking", editorType = "navigation_method")
  @SimpleProperty(description = "The transportation method used for determining the route.")
  public void TransportationMethod(String paramString) {
    for (TransportMethod transportMethod : TransportMethod.values()) {
      if (paramString.equals(transportMethod.method()))
        this.method = transportMethod; 
    } 
  }
  
  enum TransportMethod {
    CYCLING,
    DEFAULT("foot-walking"),
    DRIVING("driving-car"),
    WALKING("driving-car"),
    WHEELCHAIR("driving-car");
    
    private final String method;
    
    static {
      $VALUES = new TransportMethod[] { DEFAULT, DRIVING, CYCLING, WALKING, WHEELCHAIR };
    }
    
    TransportMethod(String param1String1) {
      this.method = param1String1;
    }
    
    private String method() {
      return this.method;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Navigation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
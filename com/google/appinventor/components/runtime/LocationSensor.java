package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "Non-visible component providing location information, including longitude, latitude, altitude (if supported by the device), speed (if supported by the device), and address.  This can also perform \"geocoding\", converting a given address (not necessarily the current one) to a latitude (with the <code>LatitudeFromAddress</code> method) and a longitude (with the <code>LongitudeFromAddress</code> method).</p>\n<p>In order to function, the component must have its <code>Enabled</code> property set to True, and the device must have location sensing enabled through wireless networks or GPS satellites (if outdoors).</p>\nLocation information might not be immediately available when an app starts.  You'll have to wait a short time for a location provider to be found and used, or wait for the LocationChanged event", iconName = "images/locationSensor.png", nonVisible = true, version = 3)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.ACCESS_FINE_LOCATION,android.permission.ACCESS_COARSE_LOCATION,android.permission.ACCESS_MOCK_LOCATION,android.permission.ACCESS_LOCATION_EXTRA_COMMANDS")
public class LocationSensor extends AndroidNonvisibleComponent implements Component, OnStopListener, OnResumeListener, Deleteable {
  private static final String LOG_TAG = LocationSensor.class.getSimpleName();
  
  public static final int UNKNOWN_VALUE = 0;
  
  private List<String> allProviders;
  
  private double altitude = 0.0D;
  
  private final Handler androidUIHandler = new Handler();
  
  private int distanceInterval;
  
  private boolean enabled = true;
  
  private Geocoder geocoder;
  
  private final Handler handler;
  
  private boolean hasAltitude = false;
  
  private boolean hasLocationData = false;
  
  private boolean havePermission = false;
  
  private boolean initialized = false;
  
  private Location lastLocation;
  
  private double latitude = 0.0D;
  
  private final Set<LocationSensorListener> listeners = new HashSet<LocationSensorListener>();
  
  private boolean listening = false;
  
  private final Criteria locationCriteria;
  
  private final LocationManager locationManager;
  
  private LocationProvider locationProvider;
  
  private double longitude = 0.0D;
  
  private MyLocationListener myLocationListener;
  
  private boolean providerLocked = false;
  
  private String providerName;
  
  private float speed = 0.0F;
  
  private int timeInterval;
  
  public LocationSensor(ComponentContainer paramComponentContainer) {
    this(paramComponentContainer, true);
  }
  
  public LocationSensor(ComponentContainer paramComponentContainer, boolean paramBoolean) {
    super(paramComponentContainer.$form());
    this.enabled = paramBoolean;
    this.handler = new Handler();
    this.form.registerForOnResume(this);
    this.form.registerForOnStop(this);
    this.timeInterval = 60000;
    this.distanceInterval = 5;
    Activity activity = paramComponentContainer.$context();
    this.geocoder = new Geocoder((Context)activity);
    this.locationManager = (LocationManager)activity.getSystemService("location");
    this.locationCriteria = new Criteria();
    this.myLocationListener = new MyLocationListener();
    this.allProviders = new ArrayList<String>();
    Enabled(paramBoolean);
  }
  
  private boolean empty(String paramString) {
    return (paramString == null || paramString.length() == 0);
  }
  
  private boolean startProvider(String paramString) {
    this.providerName = paramString;
    LocationProvider locationProvider = this.locationManager.getProvider(paramString);
    if (locationProvider == null) {
      Log.d(LOG_TAG, "getProvider(" + paramString + ") returned null");
      return false;
    } 
    stopListening();
    this.locationProvider = locationProvider;
    this.locationManager.requestLocationUpdates(paramString, this.timeInterval, this.distanceInterval, this.myLocationListener);
    this.listening = true;
    return true;
  }
  
  private void stopListening() {
    if (this.listening) {
      this.locationManager.removeUpdates(this.myLocationListener);
      this.locationProvider = null;
      this.listening = false;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The most recent measure of accuracy, in meters.  If no value is available, 0 will be returned.")
  public double Accuracy() {
    return (this.lastLocation != null && this.lastLocation.hasAccuracy()) ? this.lastLocation.getAccuracy() : ((this.locationProvider != null) ? this.locationProvider.getAccuracy() : 0.0D);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The most recently available altitude value, in meters.  If no value is available, 0 will be returned.")
  public double Altitude() {
    return this.altitude;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public List<String> AvailableProviders() {
    return this.allProviders;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Provides a textual representation of the current address or \"No address available\".")
  public String CurrentAddress() {
    if ((this.hasLocationData && this.latitude <= 90.0D && this.latitude >= -90.0D && this.longitude <= 180.0D) || this.longitude >= -180.0D)
      try {
        List<Address> list = this.geocoder.getFromLocation(this.latitude, this.longitude, 1);
        if (list != null && list.size() == 1) {
          Address address = list.get(0);
          if (address != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
              stringBuilder.append(address.getAddressLine(i));
              stringBuilder.append("\n");
            } 
            return stringBuilder.toString();
          } 
        } 
      } catch (Exception exception) {
        if (exception instanceof IllegalArgumentException || exception instanceof IOException || exception instanceof IndexOutOfBoundsException) {
          Log.e(LOG_TAG, "Exception thrown by getting current address " + exception.getMessage());
          return "No address available";
        } 
      }  
    return "No address available";
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines the minimum distance interval, in meters, that the sensor will try to use for sending out location updates. For example, if this is set to 5, then the sensor will fire a LocationChanged event only after 5 meters have been traversed. However, the sensor does not guarantee that an update will be received at exactly the distance interval. It may take more than 5 meters to fire an event, for instance.")
  public int DistanceInterval() {
    return this.distanceInterval;
  }
  
  @DesignerProperty(defaultValue = "5", editorType = "sensor_dist_interval")
  @SimpleProperty
  public void DistanceInterval(int paramInt) {
    if (paramInt >= 0 && paramInt <= 1000) {
      this.distanceInterval = paramInt;
      if (this.enabled)
        RefreshProvider("DistanceInterval"); 
      Iterator<LocationSensorListener> iterator = this.listeners.iterator();
      while (true) {
        if (iterator.hasNext()) {
          ((LocationSensorListener)iterator.next()).onDistanceIntervalChanged(this.distanceInterval);
          continue;
        } 
        return;
      } 
    } 
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean) {
    this.enabled = paramBoolean;
    if (!this.initialized)
      return; 
    if (!paramBoolean) {
      stopListening();
      return;
    } 
    RefreshProvider("Enabled");
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public boolean Enabled() {
    return this.enabled;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public boolean HasAccuracy() {
    return (Accuracy() != 0.0D && this.enabled);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public boolean HasAltitude() {
    return (this.hasAltitude && this.enabled);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public boolean HasLongitudeLatitude() {
    return (this.hasLocationData && this.enabled);
  }
  
  public void Initialize() {
    this.initialized = true;
    Enabled(this.enabled);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public double Latitude() {
    return this.latitude;
  }
  
  @SimpleFunction(description = "Derives latitude of given address")
  public double LatitudeFromAddress(String paramString) {
    try {
      List list = this.geocoder.getFromLocationName(paramString, 1);
      Log.i(LOG_TAG, "latitude addressObjs size is " + list.size() + " for " + paramString);
      if (list == null || list.size() == 0)
        throw new IOException(""); 
    } catch (IOException iOException) {
      this.form.dispatchErrorOccurredEvent(this, "LatitudeFromAddress", 101, new Object[] { paramString });
      return 0.0D;
    } 
    return ((Address)iOException.get(0)).getLatitude();
  }
  
  @SimpleEvent(description = "Indicates that a new location has been detected.")
  public void LocationChanged(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat) {
    EventDispatcher.dispatchEvent(this, "LocationChanged", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2), Double.valueOf(paramDouble3), Float.valueOf(paramFloat) });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public double Longitude() {
    return this.longitude;
  }
  
  @SimpleFunction(description = "Derives longitude of given address")
  public double LongitudeFromAddress(String paramString) {
    try {
      List list = this.geocoder.getFromLocationName(paramString, 1);
      Log.i(LOG_TAG, "longitude addressObjs size is " + list.size() + " for " + paramString);
      if (list == null || list.size() == 0)
        throw new IOException(""); 
    } catch (IOException iOException) {
      this.form.dispatchErrorOccurredEvent(this, "LongitudeFromAddress", 102, new Object[] { paramString });
      return 0.0D;
    } 
    return ((Address)iOException.get(0)).getLongitude();
  }
  
  @SimpleProperty
  public void ProviderLocked(boolean paramBoolean) {
    this.providerLocked = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public boolean ProviderLocked() {
    return this.providerLocked;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String ProviderName() {
    return (this.providerName == null) ? "NO PROVIDER" : this.providerName;
  }
  
  @SimpleProperty
  public void ProviderName(String paramString) {
    this.providerName = paramString;
    if (!empty(paramString) && startProvider(paramString))
      return; 
    RefreshProvider("ProviderName");
  }
  
  public void RefreshProvider(final String caller) {
    if (this.initialized) {
      stopListening();
      if (!this.havePermission)
        this.androidUIHandler.post(new Runnable() {
              public void run() {
                me.form.askPermission("android.permission.ACCESS_FINE_LOCATION", new PermissionResultHandler() {
                      public void HandlePermissionResponse(String param2String, boolean param2Boolean) {
                        if (param2Boolean) {
                          LocationSensor.access$1402(me, true);
                          me.RefreshProvider(caller);
                          Log.d(LocationSensor.LOG_TAG, "Permission Granted");
                          return;
                        } 
                        LocationSensor.access$1402(me, false);
                        LocationSensor.access$1002(me, false);
                        me.form.dispatchPermissionDeniedEvent(me, caller, "android.permission.ACCESS_FINE_LOCATION");
                      }
                    });
              }
            }); 
      if (this.providerLocked && !empty(this.providerName)) {
        this.listening = startProvider(this.providerName);
        return;
      } 
      this.allProviders = this.locationManager.getProviders(true);
      caller = this.locationManager.getBestProvider(this.locationCriteria, true);
      if (caller != null && !caller.equals(this.allProviders.get(0)))
        this.allProviders.add(0, caller); 
      Iterator<String> iterator = this.allProviders.iterator();
      while (true) {
        if (iterator.hasNext()) {
          String str = iterator.next();
          this.listening = startProvider(str);
          if (this.listening) {
            if (!this.providerLocked) {
              this.providerName = str;
              return;
            } 
            return;
          } 
          continue;
        } 
        return;
      } 
    } 
  }
  
  @SimpleEvent
  public void StatusChanged(String paramString1, String paramString2) {
    if (this.enabled)
      EventDispatcher.dispatchEvent(this, "StatusChanged", new Object[] { paramString1, paramString2 }); 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines the minimum time interval, in milliseconds, that the sensor will try to use for sending out location updates. However, location updates will only be received when the location of the phone actually changes, and use of the specified time interval is not guaranteed. For example, if 1000 is used as the time interval, location updates will never be fired sooner than 1000ms, but they may be fired anytime after.")
  public int TimeInterval() {
    return this.timeInterval;
  }
  
  @DesignerProperty(defaultValue = "60000", editorType = "sensor_time_interval")
  @SimpleProperty
  public void TimeInterval(int paramInt) {
    if (paramInt >= 0 && paramInt <= 1000000) {
      this.timeInterval = paramInt;
      if (this.enabled)
        RefreshProvider("TimeInterval"); 
      Iterator<LocationSensorListener> iterator = this.listeners.iterator();
      while (true) {
        if (iterator.hasNext()) {
          ((LocationSensorListener)iterator.next()).onTimeIntervalChanged(this.timeInterval);
          continue;
        } 
        return;
      } 
    } 
  }
  
  public void addListener(LocationSensorListener paramLocationSensorListener) {
    paramLocationSensorListener.setSource(this);
    this.listeners.add(paramLocationSensorListener);
  }
  
  public void onDelete() {
    stopListening();
  }
  
  public void onResume() {
    if (this.enabled)
      RefreshProvider("onResume"); 
  }
  
  public void onStop() {
    stopListening();
  }
  
  public void removeListener(LocationSensorListener paramLocationSensorListener) {
    this.listeners.remove(paramLocationSensorListener);
    paramLocationSensorListener.setSource(null);
  }
  
  public static interface LocationSensorListener extends LocationListener {
    void onDistanceIntervalChanged(int param1Int);
    
    void onTimeIntervalChanged(int param1Int);
    
    void setSource(LocationSensor param1LocationSensor);
  }
  
  private class MyLocationListener implements LocationListener {
    private MyLocationListener() {}
    
    public void onLocationChanged(final Location location) {
      LocationSensor.access$002(LocationSensor.this, location);
      LocationSensor.access$102(LocationSensor.this, location.getLongitude());
      LocationSensor.access$202(LocationSensor.this, location.getLatitude());
      LocationSensor.access$302(LocationSensor.this, location.getSpeed());
      if (location.hasAltitude()) {
        LocationSensor.access$402(LocationSensor.this, true);
        LocationSensor.access$502(LocationSensor.this, location.getAltitude());
      } 
      if (LocationSensor.this.longitude != 0.0D || LocationSensor.this.latitude != 0.0D) {
        LocationSensor.access$602(LocationSensor.this, true);
        final double argLatitude = LocationSensor.this.latitude;
        final double argLongitude = LocationSensor.this.longitude;
        final double argAltitude = LocationSensor.this.altitude;
        final float argSpeed = LocationSensor.this.speed;
        LocationSensor.this.androidUIHandler.post(new Runnable() {
              public void run() {
                LocationSensor.this.LocationChanged(argLatitude, argLongitude, argAltitude, argSpeed);
                Iterator<LocationSensor.LocationSensorListener> iterator = LocationSensor.this.listeners.iterator();
                while (iterator.hasNext())
                  ((LocationSensor.LocationSensorListener)iterator.next()).onLocationChanged(location); 
              }
            });
      } 
    }
    
    public void onProviderDisabled(String param1String) {
      LocationSensor.this.StatusChanged(param1String, "Disabled");
      LocationSensor.this.stopListening();
      if (LocationSensor.this.enabled)
        LocationSensor.this.RefreshProvider("onProviderDisabled"); 
    }
    
    public void onProviderEnabled(String param1String) {
      LocationSensor.this.StatusChanged(param1String, "Enabled");
      LocationSensor.this.RefreshProvider("onProviderEnabled");
    }
    
    public void onStatusChanged(String param1String, int param1Int, Bundle param1Bundle) {
      switch (param1Int) {
        default:
          return;
        case 1:
          LocationSensor.this.StatusChanged(param1String, "TEMPORARILY_UNAVAILABLE");
          return;
        case 0:
          LocationSensor.this.StatusChanged(param1String, "OUT_OF_SERVICE");
          if (param1String.equals(LocationSensor.this.providerName)) {
            LocationSensor.this.stopListening();
            LocationSensor.this.RefreshProvider("onStatusChanged");
            return;
          } 
        case 2:
          break;
      } 
      LocationSensor.this.StatusChanged(param1String, "AVAILABLE");
      if (!param1String.equals(LocationSensor.this.providerName) && !LocationSensor.this.allProviders.contains(param1String)) {
        LocationSensor.this.RefreshProvider("onStatusChanged");
        return;
      } 
    }
  }
  
  class null implements Runnable {
    public void run() {
      LocationSensor.this.LocationChanged(argLatitude, argLongitude, argAltitude, argSpeed);
      Iterator<LocationSensor.LocationSensorListener> iterator = LocationSensor.this.listeners.iterator();
      while (iterator.hasNext())
        ((LocationSensor.LocationSensorListener)iterator.next()).onLocationChanged(location); 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/LocationSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
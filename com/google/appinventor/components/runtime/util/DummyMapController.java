package com.google.appinventor.components.runtime.util;

import android.view.View;
import com.google.appinventor.components.runtime.LocationSensor;
import org.osmdroid.util.BoundingBox;

class DummyMapController implements MapFactory.MapController {
  public void addEventListener(MapFactory.MapEventListener paramMapEventListener) {
    throw new UnsupportedOperationException();
  }
  
  public void addFeature(MapFactory.MapCircle paramMapCircle) {
    throw new UnsupportedOperationException();
  }
  
  public void addFeature(MapFactory.MapLineString paramMapLineString) {
    throw new UnsupportedOperationException();
  }
  
  public void addFeature(MapFactory.MapMarker paramMapMarker) {
    throw new UnsupportedOperationException();
  }
  
  public void addFeature(MapFactory.MapPolygon paramMapPolygon) {
    throw new UnsupportedOperationException();
  }
  
  public void addFeature(MapFactory.MapRectangle paramMapRectangle) {
    throw new UnsupportedOperationException();
  }
  
  public BoundingBox getBoundingBox() {
    throw new UnsupportedOperationException();
  }
  
  public double getLatitude() {
    throw new UnsupportedOperationException();
  }
  
  public LocationSensor.LocationSensorListener getLocationListener() {
    throw new UnsupportedOperationException();
  }
  
  public double getLongitude() {
    throw new UnsupportedOperationException();
  }
  
  public MapFactory.MapType getMapType() {
    throw new UnsupportedOperationException();
  }
  
  public int getOverlayCount() {
    throw new UnsupportedOperationException();
  }
  
  public float getRotation() {
    throw new UnsupportedOperationException();
  }
  
  public MapFactory.MapScaleUnits getScaleUnits() {
    throw new UnsupportedOperationException();
  }
  
  public View getView() {
    throw new UnsupportedOperationException();
  }
  
  public int getZoom() {
    throw new UnsupportedOperationException();
  }
  
  public void hideFeature(MapFactory.MapFeature paramMapFeature) {
    throw new UnsupportedOperationException();
  }
  
  public void hideInfobox(MapFactory.MapFeature paramMapFeature) {
    throw new UnsupportedOperationException();
  }
  
  public boolean isCompassEnabled() {
    throw new UnsupportedOperationException();
  }
  
  public boolean isFeatureCollectionVisible(MapFactory.MapFeatureCollection paramMapFeatureCollection) {
    throw new UnsupportedOperationException();
  }
  
  public boolean isFeatureVisible(MapFactory.MapFeature paramMapFeature) {
    throw new UnsupportedOperationException();
  }
  
  public boolean isInfoboxVisible(MapFactory.MapFeature paramMapFeature) {
    throw new UnsupportedOperationException();
  }
  
  public boolean isPanEnabled() {
    throw new UnsupportedOperationException();
  }
  
  public boolean isRotationEnabled() {
    throw new UnsupportedOperationException();
  }
  
  public boolean isScaleVisible() {
    throw new UnsupportedOperationException();
  }
  
  public boolean isShowUserEnabled() {
    throw new UnsupportedOperationException();
  }
  
  public boolean isZoomControlEnabled() {
    throw new UnsupportedOperationException();
  }
  
  public boolean isZoomEnabled() {
    throw new UnsupportedOperationException();
  }
  
  public void panTo(double paramDouble1, double paramDouble2, int paramInt, double paramDouble3) {
    throw new UnsupportedOperationException();
  }
  
  public void removeFeature(MapFactory.MapFeature paramMapFeature) {
    throw new UnsupportedOperationException();
  }
  
  public void setBoundingBox(BoundingBox paramBoundingBox) {
    throw new UnsupportedOperationException();
  }
  
  public void setCenter(double paramDouble1, double paramDouble2) {
    throw new UnsupportedOperationException();
  }
  
  public void setCompassEnabled(boolean paramBoolean) {
    throw new UnsupportedOperationException();
  }
  
  public void setFeatureCollectionVisible(MapFactory.MapFeatureCollection paramMapFeatureCollection, boolean paramBoolean) {
    throw new UnsupportedOperationException();
  }
  
  public void setMapType(MapFactory.MapType paramMapType) {
    throw new UnsupportedOperationException();
  }
  
  public void setPanEnabled(boolean paramBoolean) {
    throw new UnsupportedOperationException();
  }
  
  public void setRotation(float paramFloat) {
    throw new UnsupportedOperationException();
  }
  
  public void setRotationEnabled(boolean paramBoolean) {
    throw new UnsupportedOperationException();
  }
  
  public void setScaleUnits(MapFactory.MapScaleUnits paramMapScaleUnits) {
    throw new UnsupportedOperationException();
  }
  
  public void setScaleVisible(boolean paramBoolean) {
    throw new UnsupportedOperationException();
  }
  
  public void setShowUserEnabled(boolean paramBoolean) {
    throw new UnsupportedOperationException();
  }
  
  public void setZoom(int paramInt) {
    throw new UnsupportedOperationException();
  }
  
  public void setZoomControlEnabled(boolean paramBoolean) {
    throw new UnsupportedOperationException();
  }
  
  public void setZoomEnabled(boolean paramBoolean) {
    throw new UnsupportedOperationException();
  }
  
  public void showFeature(MapFactory.MapFeature paramMapFeature) {
    throw new UnsupportedOperationException();
  }
  
  public void showInfobox(MapFactory.MapFeature paramMapFeature) {
    throw new UnsupportedOperationException();
  }
  
  public void updateFeatureDraggable(MapFactory.MapFeature paramMapFeature) {
    throw new UnsupportedOperationException();
  }
  
  public void updateFeatureFill(MapFactory.HasFill paramHasFill) {
    throw new UnsupportedOperationException();
  }
  
  public void updateFeatureHoles(MapFactory.MapPolygon paramMapPolygon) {
    throw new UnsupportedOperationException();
  }
  
  public void updateFeatureImage(MapFactory.MapMarker paramMapMarker) {
    throw new UnsupportedOperationException();
  }
  
  public void updateFeaturePosition(MapFactory.MapCircle paramMapCircle) {
    throw new UnsupportedOperationException();
  }
  
  public void updateFeaturePosition(MapFactory.MapLineString paramMapLineString) {
    throw new UnsupportedOperationException();
  }
  
  public void updateFeaturePosition(MapFactory.MapMarker paramMapMarker) {
    throw new UnsupportedOperationException();
  }
  
  public void updateFeaturePosition(MapFactory.MapPolygon paramMapPolygon) {
    throw new UnsupportedOperationException();
  }
  
  public void updateFeaturePosition(MapFactory.MapRectangle paramMapRectangle) {
    throw new UnsupportedOperationException();
  }
  
  public void updateFeatureSize(MapFactory.MapMarker paramMapMarker) {
    throw new UnsupportedOperationException();
  }
  
  public void updateFeatureStroke(MapFactory.HasStroke paramHasStroke) {
    throw new UnsupportedOperationException();
  }
  
  public void updateFeatureText(MapFactory.MapFeature paramMapFeature) {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/DummyMapController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
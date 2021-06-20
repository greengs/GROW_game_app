package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.runtime.util.MapFactory;

@SimpleObject
public abstract class PolygonBase extends MapFeatureBaseWithFill {
  public PolygonBase(MapFactory.MapFeatureContainer paramMapFeatureContainer, MapFactory.MapFeatureVisitor<Double> paramMapFeatureVisitor) {
    super(paramMapFeatureContainer, paramMapFeatureVisitor);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/PolygonBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
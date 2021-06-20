package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.caverock.androidsvg.SVG;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.LocationSensor;
import com.google.appinventor.components.runtime.view.ZoomControlView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayWithIW;
import org.osmdroid.views.overlay.OverlayWithIWVisitor;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.IOrientationProvider;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.infowindow.OverlayInfoWindow;
import org.osmdroid.views.overlay.mylocation.IMyLocationConsumer;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

class NativeOpenStreetMapController implements MapFactory.MapController, MapListener {
  private static final float[] ANCHOR_HORIZONTAL;
  
  private static final float[] ANCHOR_VERTICAL;
  
  private static final long SPECIFIED_FILL = 1L;
  
  private static final long SPECIFIED_FILL_OPACITY = 4L;
  
  private static final long SPECIFIED_STROKE = 8L;
  
  private static final long SPECIFIED_STROKE_OPACITY = 16L;
  
  private static final long SPECIFIED_STROKE_WIDTH = 32L;
  
  private static final String TAG = NativeOpenStreetMapController.class.getSimpleName();
  
  private boolean caches;
  
  private CompassOverlay compass = null;
  
  private RelativeLayout containerView;
  
  private OverlayInfoWindow defaultInfoWindow = null;
  
  private SVG defaultMarkerSVG = null;
  
  private Set<MapFactory.MapEventListener> eventListeners = new HashSet<MapFactory.MapEventListener>();
  
  private Map<MapFactory.MapFeature, OverlayWithIW> featureOverlays = new HashMap<MapFactory.MapFeature, OverlayWithIW>();
  
  private final Form form;
  
  private Set<MapFactory.MapFeatureCollection> hiddenFeatureCollections = new HashSet<MapFactory.MapFeatureCollection>();
  
  private Set<MapFactory.MapFeature> hiddenFeatures = new HashSet<MapFactory.MapFeature>();
  
  private float lastAzimuth = Float.NaN;
  
  private final AppInventorLocationSensorAdapter locationProvider;
  
  private boolean ready = false;
  
  private RotationGestureOverlay rotation = null;
  
  private ScaleBarOverlay scaleBar;
  
  private MapFactory.MapType tileType;
  
  private TouchOverlay touch = null;
  
  private final MyLocationNewOverlay userLocation;
  
  private MapView view;
  
  private boolean zoomControlEnabled;
  
  private ZoomControlView zoomControls = null;
  
  private boolean zoomEnabled;
  
  static {
    ANCHOR_HORIZONTAL = new float[] { Float.NaN, 0.0F, 1.0F, 0.5F };
    ANCHOR_VERTICAL = new float[] { Float.NaN, 0.0F, 0.5F, 1.0F };
  }
  
  NativeOpenStreetMapController(Form paramForm) {
    OpenStreetMapTileProviderConstants.setUserAgentValue(paramForm.getApplication().getPackageName());
    File file = new File(paramForm.getCacheDir(), "osmdroid");
    if (file.exists() || file.mkdirs()) {
      Configuration.getInstance().setOsmdroidBasePath(file);
      file = new File(file, "tiles");
      if (file.exists() || file.mkdirs()) {
        Configuration.getInstance().setOsmdroidTileCache(file);
        this.caches = true;
      } 
    } 
    this.form = paramForm;
    this.touch = new TouchOverlay();
    this.view = new CustomMapView(paramForm.getApplicationContext());
    this.locationProvider = new AppInventorLocationSensorAdapter();
    this.defaultInfoWindow = new OverlayInfoWindow(this.view);
    this.view.setTilesScaledToDpi(true);
    this.view.setMapListener(this);
    this.view.getOverlayManager().add(this.touch);
    this.view.addOnTapListener(new MapView.OnTapListener() {
          public void onDoubleTap(MapView param1MapView, double param1Double1, double param1Double2) {
            Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
            while (iterator.hasNext())
              ((MapFactory.MapEventListener)iterator.next()).onDoubleTap(param1Double1, param1Double2); 
          }
          
          public void onSingleTap(MapView param1MapView, double param1Double1, double param1Double2) {
            Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
            while (iterator.hasNext())
              ((MapFactory.MapEventListener)iterator.next()).onSingleTap(param1Double1, param1Double2); 
          }
        });
    this.zoomControls = new ZoomControlView(this.view);
    this.userLocation = new MyLocationNewOverlay(this.locationProvider, this.view);
    this.scaleBar = new ScaleBarOverlay(this.view);
    this.scaleBar.setAlignBottom(true);
    this.scaleBar.setAlignRight(true);
    this.scaleBar.disableScaleBar();
    this.view.getOverlayManager().add(this.scaleBar);
    this.containerView = new RelativeLayout((Context)paramForm);
    this.containerView.setClipChildren(true);
    this.containerView.addView((View)this.view, (ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
    this.containerView.addView((View)this.zoomControls);
    this.zoomControls.setVisibility(8);
  }
  
  private void configurePolygon(final MapFactory.MapFeature component, Polygon paramPolygon) {
    this.featureOverlays.put(component, paramPolygon);
    paramPolygon.setOnClickListener(new Polygon.OnClickListener() {
          public boolean onClick(Polygon param1Polygon, MapView param1MapView, GeoPoint param1GeoPoint) {
            Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
            while (iterator.hasNext())
              ((MapFactory.MapEventListener)iterator.next()).onFeatureClick(component); 
            if (component.EnableInfobox())
              param1Polygon.showInfoWindow(param1GeoPoint); 
            return true;
          }
          
          public boolean onLongClick(Polygon param1Polygon, MapView param1MapView, GeoPoint param1GeoPoint) {
            Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
            while (iterator.hasNext())
              ((MapFactory.MapEventListener)iterator.next()).onFeatureLongPress(component); 
            return true;
          }
        });
    paramPolygon.setOnDragListener(new Polygon.OnDragListener() {
          public void onDrag(Polygon param1Polygon) {
            Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
            while (iterator.hasNext())
              ((MapFactory.MapEventListener)iterator.next()).onFeatureDrag(component); 
          }
          
          public void onDragEnd(Polygon param1Polygon) {
            if (component instanceof MapFactory.MapCircle) {
              double d2 = 0.0D;
              double d1 = 0.0D;
              int i = param1Polygon.getPoints().size();
              for (GeoPoint geoPoint : param1Polygon.getPoints()) {
                d2 += geoPoint.getLatitude();
                d1 += geoPoint.getLongitude();
              } 
              if (i > 0) {
                ((MapFactory.MapCircle)component).updateCenter(d2 / i, d1 / i);
              } else {
                ((MapFactory.MapCircle)component).updateCenter(0.0D, 0.0D);
              } 
            } else if (component instanceof MapFactory.MapRectangle) {
              double d4 = -90.0D;
              double d1 = -180.0D;
              double d2 = 180.0D;
              double d3 = 90.0D;
              for (GeoPoint geoPoint : param1Polygon.getPoints()) {
                double d6 = geoPoint.getLatitude();
                double d5 = geoPoint.getLongitude();
                d4 = Math.max(d4, d6);
                d3 = Math.min(d3, d6);
                d1 = Math.max(d1, d5);
                d2 = Math.min(d2, d5);
              } 
              ((MapFactory.MapRectangle)component).updateBounds(d4, d2, d3, d1);
            } else {
              ((MapFactory.MapPolygon)component).updatePoints(((NativeOpenStreetMapController.MultiPolygon)param1Polygon).getMultiPoints());
              ((MapFactory.MapPolygon)component).updateHolePoints(((NativeOpenStreetMapController.MultiPolygon)param1Polygon).getMultiHoles());
            } 
            Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
            while (iterator.hasNext())
              ((MapFactory.MapEventListener)iterator.next()).onFeatureStopDrag(component); 
          }
          
          public void onDragStart(Polygon param1Polygon) {
            Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
            while (iterator.hasNext())
              ((MapFactory.MapEventListener)iterator.next()).onFeatureStartDrag(component); 
          }
        });
    if (component.Visible()) {
      showOverlay((OverlayWithIW)paramPolygon);
      return;
    } 
    hideOverlay((OverlayWithIW)paramPolygon);
  }
  
  private Polygon createNativeCircle(MapFactory.MapCircle paramMapCircle) {
    Polygon polygon = new Polygon();
    createPolygon(polygon, paramMapCircle);
    polygon.setPoints(Polygon.pointsAsCircle(new GeoPoint(paramMapCircle.Latitude(), paramMapCircle.Longitude()), paramMapCircle.Radius()));
    return polygon;
  }
  
  private void createNativeMarker(MapFactory.MapMarker paramMapMarker, AsyncCallbackPair<Marker> paramAsyncCallbackPair) {
    final Marker osmMarker = new Marker(this.view);
    this.featureOverlays.put(paramMapMarker, marker);
    marker.setDraggable(paramMapMarker.Draggable());
    marker.setTitle(paramMapMarker.Title());
    marker.setSnippet(paramMapMarker.Description());
    marker.setPosition(new GeoPoint(paramMapMarker.Latitude(), paramMapMarker.Longitude()));
    marker.setAnchor(0.5F, 1.0F);
    getMarkerDrawable(paramMapMarker, new AsyncCallbackFacade<Drawable, Marker>(paramAsyncCallbackPair) {
          public void onFailure(String param1String) {
            this.callback.onFailure(param1String);
          }
          
          public void onSuccess(Drawable param1Drawable) {
            osmMarker.setIcon(param1Drawable);
            this.callback.onSuccess(osmMarker);
          }
        });
  }
  
  private MultiPolygon createNativePolygon(MapFactory.MapPolygon paramMapPolygon) {
    MultiPolygon multiPolygon = new MultiPolygon();
    createPolygon(multiPolygon, paramMapPolygon);
    multiPolygon.setMultiPoints(paramMapPolygon.getPoints());
    multiPolygon.setMultiHoles(paramMapPolygon.getHolePoints());
    return multiPolygon;
  }
  
  private Polyline createNativePolyline(MapFactory.MapLineString paramMapLineString) {
    Polyline polyline = new Polyline();
    polyline.setDraggable(paramMapLineString.Draggable());
    polyline.setTitle(paramMapLineString.Title());
    polyline.setSnippet(paramMapLineString.Description());
    polyline.setPoints(paramMapLineString.getPoints());
    polyline.setColor(paramMapLineString.StrokeColor());
    polyline.setWidth(paramMapLineString.StrokeWidth());
    polyline.setInfoWindow((InfoWindow)this.defaultInfoWindow);
    return polyline;
  }
  
  private Polygon createNativeRectangle(MapFactory.MapRectangle paramMapRectangle) {
    BoundingBox boundingBox = new BoundingBox(paramMapRectangle.NorthLatitude(), paramMapRectangle.EastLongitude(), paramMapRectangle.SouthLatitude(), paramMapRectangle.WestLongitude());
    Polygon polygon = new Polygon();
    createPolygon(polygon, paramMapRectangle);
    polygon.setPoints(new ArrayList(Polygon.pointsAsRect(boundingBox)));
    return polygon;
  }
  
  private void createPolygon(Polygon paramPolygon, MapFactory.MapFeature paramMapFeature) {
    paramPolygon.setDraggable(paramMapFeature.Draggable());
    paramPolygon.setTitle(paramMapFeature.Title());
    paramPolygon.setSnippet(paramMapFeature.Description());
    paramPolygon.setStrokeColor(((MapFactory.HasStroke)paramMapFeature).StrokeColor());
    paramPolygon.setStrokeWidth(((MapFactory.HasStroke)paramMapFeature).StrokeWidth());
    paramPolygon.setFillColor(((MapFactory.HasFill)paramMapFeature).FillColor());
    paramPolygon.setInfoWindow((InfoWindow)this.defaultInfoWindow);
  }
  
  private static float getBestGuessHeight(SVG.Svg paramSvg) {
    return (paramSvg.height != null) ? paramSvg.height.floatValue() : ((paramSvg.viewBox != null) ? paramSvg.viewBox.height : 50.0F);
  }
  
  private static float getBestGuessWidth(SVG.Svg paramSvg) {
    return (paramSvg.width != null) ? paramSvg.width.floatValue() : ((paramSvg.viewBox != null) ? paramSvg.viewBox.width : 30.0F);
  }
  
  private Drawable getDefaultMarkerDrawable(MapFactory.MapMarker paramMapMarker) {
    return rasterizeSVG(paramMapMarker, this.defaultMarkerSVG);
  }
  
  private void getMarkerDrawable(MapFactory.MapMarker paramMapMarker, AsyncCallbackPair<Drawable> paramAsyncCallbackPair) {
    String str = paramMapMarker.ImageAsset();
    if (str == null || str.length() == 0 || str.endsWith(".svg")) {
      getMarkerDrawableVector(paramMapMarker, paramAsyncCallbackPair);
      return;
    } 
    getMarkerDrawableRaster(paramMapMarker, paramAsyncCallbackPair);
  }
  
  private void getMarkerDrawableRaster(final MapFactory.MapMarker aiMarker, final AsyncCallbackPair<Drawable> callback) {
    MediaUtil.getBitmapDrawableAsync(this.form, aiMarker.ImageAsset(), new AsyncCallbackPair<BitmapDrawable>() {
          public void onFailure(String param1String) {
            callback.onSuccess(NativeOpenStreetMapController.this.getDefaultMarkerDrawable(aiMarker));
          }
          
          public void onSuccess(BitmapDrawable param1BitmapDrawable) {
            param1BitmapDrawable.setAlpha(Math.round(aiMarker.FillOpacity() * 255.0F));
            callback.onSuccess(param1BitmapDrawable);
          }
        });
  }
  
  private void getMarkerDrawableVector(MapFactory.MapMarker paramMapMarker, AsyncCallbackPair<Drawable> paramAsyncCallbackPair) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #5
    //   3: aconst_null
    //   4: astore #4
    //   6: aload_0
    //   7: getfield defaultMarkerSVG : Lcom/caverock/androidsvg/SVG;
    //   10: ifnonnull -> 91
    //   13: aload_0
    //   14: aload_0
    //   15: getfield view : Lorg/osmdroid/views/MapView;
    //   18: invokevirtual getContext : ()Landroid/content/Context;
    //   21: invokevirtual getAssets : ()Landroid/content/res/AssetManager;
    //   24: ldc_w 'marker.svg'
    //   27: invokestatic getFromAsset : (Landroid/content/res/AssetManager;Ljava/lang/String;)Lcom/caverock/androidsvg/SVG;
    //   30: putfield defaultMarkerSVG : Lcom/caverock/androidsvg/SVG;
    //   33: aload_0
    //   34: getfield defaultMarkerSVG : Lcom/caverock/androidsvg/SVG;
    //   37: ifnull -> 50
    //   40: aload_0
    //   41: getfield defaultMarkerSVG : Lcom/caverock/androidsvg/SVG;
    //   44: invokevirtual getRootElement : ()Lcom/caverock/androidsvg/SVG$Svg;
    //   47: ifnonnull -> 91
    //   50: new java/lang/IllegalStateException
    //   53: dup
    //   54: ldc_w 'Unable to load SVG from assets'
    //   57: invokespecial <init> : (Ljava/lang/String;)V
    //   60: athrow
    //   61: astore_3
    //   62: getstatic com/google/appinventor/components/runtime/util/NativeOpenStreetMapController.TAG : Ljava/lang/String;
    //   65: ldc_w 'Invalid SVG in Marker asset'
    //   68: aload_3
    //   69: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   72: pop
    //   73: goto -> 33
    //   76: astore_3
    //   77: getstatic com/google/appinventor/components/runtime/util/NativeOpenStreetMapController.TAG : Ljava/lang/String;
    //   80: ldc_w 'Unable to read Marker asset'
    //   83: aload_3
    //   84: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   87: pop
    //   88: goto -> 33
    //   91: aload_1
    //   92: invokeinterface ImageAsset : ()Ljava/lang/String;
    //   97: astore #7
    //   99: aload #5
    //   101: astore_3
    //   102: aload #7
    //   104: ifnull -> 193
    //   107: aload #5
    //   109: astore_3
    //   110: aload #7
    //   112: invokevirtual length : ()I
    //   115: ifeq -> 193
    //   118: aload_0
    //   119: getfield view : Lorg/osmdroid/views/MapView;
    //   122: invokevirtual getContext : ()Landroid/content/Context;
    //   125: invokevirtual getAssets : ()Landroid/content/res/AssetManager;
    //   128: aload #7
    //   130: invokestatic getFromAsset : (Landroid/content/res/AssetManager;Ljava/lang/String;)Lcom/caverock/androidsvg/SVG;
    //   133: astore_3
    //   134: aload_3
    //   135: astore #4
    //   137: aload #4
    //   139: astore_3
    //   140: aload #4
    //   142: ifnonnull -> 193
    //   145: aconst_null
    //   146: astore #6
    //   148: aconst_null
    //   149: astore_3
    //   150: aconst_null
    //   151: astore #5
    //   153: aload_0
    //   154: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   157: aload #7
    //   159: invokestatic openMedia : (Lcom/google/appinventor/components/runtime/Form;Ljava/lang/String;)Ljava/io/InputStream;
    //   162: astore #7
    //   164: aload #7
    //   166: astore #5
    //   168: aload #7
    //   170: astore #6
    //   172: aload #7
    //   174: astore_3
    //   175: aload #7
    //   177: invokestatic getFromInputStream : (Ljava/io/InputStream;)Lcom/caverock/androidsvg/SVG;
    //   180: astore #8
    //   182: aload #8
    //   184: astore_3
    //   185: getstatic com/google/appinventor/components/runtime/util/NativeOpenStreetMapController.TAG : Ljava/lang/String;
    //   188: aload #7
    //   190: invokestatic closeQuietly : (Ljava/lang/String;Ljava/io/Closeable;)V
    //   193: aload_3
    //   194: astore #4
    //   196: aload_3
    //   197: ifnonnull -> 206
    //   200: aload_0
    //   201: getfield defaultMarkerSVG : Lcom/caverock/androidsvg/SVG;
    //   204: astore #4
    //   206: aload_2
    //   207: aload_0
    //   208: aload_1
    //   209: aload #4
    //   211: invokespecial rasterizeSVG : (Lcom/google/appinventor/components/runtime/util/MapFactory$MapMarker;Lcom/caverock/androidsvg/SVG;)Landroid/graphics/drawable/Drawable;
    //   214: invokeinterface onSuccess : (Ljava/lang/Object;)V
    //   219: return
    //   220: astore_3
    //   221: getstatic com/google/appinventor/components/runtime/util/NativeOpenStreetMapController.TAG : Ljava/lang/String;
    //   224: ldc_w 'Invalid SVG in Marker asset'
    //   227: aload_3
    //   228: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   231: pop
    //   232: goto -> 137
    //   235: astore_3
    //   236: getstatic com/google/appinventor/components/runtime/util/NativeOpenStreetMapController.TAG : Ljava/lang/String;
    //   239: ldc_w 'Unable to read Marker asset'
    //   242: aload_3
    //   243: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   246: pop
    //   247: goto -> 137
    //   250: astore #6
    //   252: aload #5
    //   254: astore_3
    //   255: getstatic com/google/appinventor/components/runtime/util/NativeOpenStreetMapController.TAG : Ljava/lang/String;
    //   258: ldc_w 'Invalid SVG in Marker asset'
    //   261: aload #6
    //   263: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   266: pop
    //   267: getstatic com/google/appinventor/components/runtime/util/NativeOpenStreetMapController.TAG : Ljava/lang/String;
    //   270: aload #5
    //   272: invokestatic closeQuietly : (Ljava/lang/String;Ljava/io/Closeable;)V
    //   275: aload #4
    //   277: astore_3
    //   278: goto -> 193
    //   281: astore #5
    //   283: aload #6
    //   285: astore_3
    //   286: getstatic com/google/appinventor/components/runtime/util/NativeOpenStreetMapController.TAG : Ljava/lang/String;
    //   289: ldc_w 'Unable to read Marker asset'
    //   292: aload #5
    //   294: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   297: pop
    //   298: getstatic com/google/appinventor/components/runtime/util/NativeOpenStreetMapController.TAG : Ljava/lang/String;
    //   301: aload #6
    //   303: invokestatic closeQuietly : (Ljava/lang/String;Ljava/io/Closeable;)V
    //   306: aload #4
    //   308: astore_3
    //   309: goto -> 193
    //   312: astore_1
    //   313: getstatic com/google/appinventor/components/runtime/util/NativeOpenStreetMapController.TAG : Ljava/lang/String;
    //   316: aload_3
    //   317: invokestatic closeQuietly : (Ljava/lang/String;Ljava/io/Closeable;)V
    //   320: aload_1
    //   321: athrow
    //   322: astore_1
    //   323: aload_2
    //   324: aload_1
    //   325: invokevirtual getMessage : ()Ljava/lang/String;
    //   328: invokeinterface onFailure : (Ljava/lang/String;)V
    //   333: return
    // Exception table:
    //   from	to	target	type
    //   13	33	61	com/caverock/androidsvg/SVGParseException
    //   13	33	76	java/io/IOException
    //   118	134	220	com/caverock/androidsvg/SVGParseException
    //   118	134	235	java/io/IOException
    //   153	164	250	com/caverock/androidsvg/SVGParseException
    //   153	164	281	java/io/IOException
    //   153	164	312	finally
    //   175	182	250	com/caverock/androidsvg/SVGParseException
    //   175	182	281	java/io/IOException
    //   175	182	312	finally
    //   206	219	322	java/lang/Exception
    //   255	267	312	finally
    //   286	298	312	finally
  }
  
  private Drawable rasterizeSVG(MapFactory.MapMarker paramMapMarker, SVG paramSVG) {
    float f1;
    float f2;
    SVG.Svg svg = paramSVG.getRootElement();
    float f3 = (this.view.getContext().getResources().getDisplayMetrics()).density;
    if (paramMapMarker.Height() <= 0) {
      f1 = getBestGuessHeight(svg);
    } else {
      f1 = paramMapMarker.Height();
    } 
    if (paramMapMarker.Width() <= 0) {
      f2 = getBestGuessWidth(svg);
    } else {
      f2 = paramMapMarker.Width();
    } 
    float f4 = f1 / getBestGuessHeight(svg);
    float f5 = f2 / getBestGuessWidth(svg);
    float f6 = (float)Math.sqrt((f4 * f4 + f5 * f5));
    Paint paint1 = new Paint();
    Paint paint2 = new Paint();
    PaintUtil.changePaint(paint1, paramMapMarker.FillColor());
    PaintUtil.changePaint(paint2, paramMapMarker.StrokeColor());
    SVG.Length length = new SVG.Length(paramMapMarker.StrokeWidth() / f6);
    for (SVG.SvgObject svgObject : svg.getChildren()) {
      if (svgObject instanceof SVG.SvgConditionalElement) {
        SVG.SvgConditionalElement svgConditionalElement = (SVG.SvgConditionalElement)svgObject;
        svgConditionalElement.baseStyle.fill = (SVG.SvgPaint)new SVG.Colour(paint1.getColor());
        svgConditionalElement.baseStyle.fillOpacity = Float.valueOf(paint1.getAlpha() / 255.0F);
        svgConditionalElement.baseStyle.stroke = (SVG.SvgPaint)new SVG.Colour(paint2.getColor());
        svgConditionalElement.baseStyle.strokeOpacity = Float.valueOf(paint2.getAlpha() / 255.0F);
        svgConditionalElement.baseStyle.strokeWidth = length;
        svgConditionalElement.baseStyle.specifiedFlags = 61L;
        if (svgConditionalElement.style != null) {
          if ((svgConditionalElement.style.specifiedFlags & 0x1L) == 0L) {
            svgConditionalElement.style.fill = (SVG.SvgPaint)new SVG.Colour(paint1.getColor());
            SVG.Style style = svgConditionalElement.style;
            style.specifiedFlags |= 0x1L;
          } 
          if ((svgConditionalElement.style.specifiedFlags & 0x4L) == 0L) {
            svgConditionalElement.style.fillOpacity = Float.valueOf(paint1.getAlpha() / 255.0F);
            SVG.Style style = svgConditionalElement.style;
            style.specifiedFlags |= 0x4L;
          } 
          if ((svgConditionalElement.style.specifiedFlags & 0x8L) == 0L) {
            svgConditionalElement.style.stroke = (SVG.SvgPaint)new SVG.Colour(paint2.getColor());
            SVG.Style style = svgConditionalElement.style;
            style.specifiedFlags |= 0x8L;
          } 
          if ((svgConditionalElement.style.specifiedFlags & 0x10L) == 0L) {
            svgConditionalElement.style.strokeOpacity = Float.valueOf(paint2.getAlpha() / 255.0F);
            SVG.Style style = svgConditionalElement.style;
            style.specifiedFlags |= 0x10L;
          } 
          if ((svgConditionalElement.style.specifiedFlags & 0x20L) == 0L) {
            svgConditionalElement.style.strokeWidth = length;
            SVG.Style style = svgConditionalElement.style;
            style.specifiedFlags |= 0x20L;
          } 
        } 
      } 
    } 
    Picture picture1 = paramSVG.renderToPicture();
    Picture picture2 = new Picture();
    Canvas canvas = picture2.beginRecording((int)((2.0F * paramMapMarker.StrokeWidth() + f2) * f3), (int)((2.0F * paramMapMarker.StrokeWidth() + f1) * f3));
    canvas.scale(f3 * f5, f3 * f4);
    canvas.translate(length.floatValue(), length.floatValue());
    picture1.draw(canvas);
    picture2.endRecording();
    return (Drawable)new PictureDrawable(picture2);
  }
  
  public void addEventListener(MapFactory.MapEventListener paramMapEventListener) {
    this.eventListeners.add(paramMapEventListener);
    if ((this.ready || ViewCompat.isAttachedToWindow((View)this.view)) && this.form.canDispatchEvent(null, "MapReady")) {
      this.ready = true;
      paramMapEventListener.onReady(this);
    } 
  }
  
  public void addFeature(MapFactory.MapCircle paramMapCircle) {
    configurePolygon(paramMapCircle, createNativeCircle(paramMapCircle));
  }
  
  public void addFeature(final MapFactory.MapLineString aiPolyline) {
    Polyline polyline = createNativePolyline(aiPolyline);
    this.featureOverlays.put(aiPolyline, polyline);
    polyline.setOnClickListener(new Polyline.OnClickListener() {
          public boolean onClick(Polyline param1Polyline, MapView param1MapView, GeoPoint param1GeoPoint) {
            Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
            while (iterator.hasNext())
              ((MapFactory.MapEventListener)iterator.next()).onFeatureClick(aiPolyline); 
            if (aiPolyline.EnableInfobox())
              param1Polyline.showInfoWindow(param1GeoPoint); 
            return true;
          }
          
          public boolean onLongClick(Polyline param1Polyline, MapView param1MapView, GeoPoint param1GeoPoint) {
            Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
            while (iterator.hasNext())
              ((MapFactory.MapEventListener)iterator.next()).onFeatureLongPress(aiPolyline); 
            return true;
          }
        });
    polyline.setOnDragListener(new Polyline.OnDragListener() {
          public void onDrag(Polyline param1Polyline) {
            Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
            while (iterator.hasNext())
              ((MapFactory.MapEventListener)iterator.next()).onFeatureDrag(aiPolyline); 
          }
          
          public void onDragEnd(Polyline param1Polyline) {
            aiPolyline.updatePoints(param1Polyline.getPoints());
            Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
            while (iterator.hasNext())
              ((MapFactory.MapEventListener)iterator.next()).onFeatureStopDrag(aiPolyline); 
          }
          
          public void onDragStart(Polyline param1Polyline) {
            Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
            while (iterator.hasNext())
              ((MapFactory.MapEventListener)iterator.next()).onFeatureStartDrag(aiPolyline); 
          }
        });
    if (aiPolyline.Visible()) {
      showOverlay((OverlayWithIW)polyline);
      return;
    } 
    hideOverlay((OverlayWithIW)polyline);
  }
  
  public void addFeature(final MapFactory.MapMarker aiMarker) {
    createNativeMarker(aiMarker, new AsyncCallbackPair<Marker>() {
          public void onFailure(String param1String) {
            Log.e(NativeOpenStreetMapController.TAG, "Unable to create marker: " + param1String);
          }
          
          public void onSuccess(Marker param1Marker) {
            param1Marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                  public boolean onMarkerClick(Marker param2Marker, MapView param2MapView) {
                    Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
                    while (iterator.hasNext())
                      ((MapFactory.MapEventListener)iterator.next()).onFeatureClick(aiMarker); 
                    if (aiMarker.EnableInfobox())
                      param2Marker.showInfoWindow(); 
                    return false;
                  }
                  
                  public boolean onMarkerLongPress(Marker param2Marker, MapView param2MapView) {
                    Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
                    while (iterator.hasNext())
                      ((MapFactory.MapEventListener)iterator.next()).onFeatureLongPress(aiMarker); 
                    return false;
                  }
                });
            param1Marker.setOnMarkerDragListener(new Marker.OnMarkerDragListener() {
                  public void onMarkerDrag(Marker param2Marker) {
                    Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
                    while (iterator.hasNext())
                      ((MapFactory.MapEventListener)iterator.next()).onFeatureDrag(aiMarker); 
                  }
                  
                  public void onMarkerDragEnd(Marker param2Marker) {
                    GeoPoint geoPoint = param2Marker.getPosition();
                    aiMarker.updateLocation(geoPoint.getLatitude(), geoPoint.getLongitude());
                    Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
                    while (iterator.hasNext())
                      ((MapFactory.MapEventListener)iterator.next()).onFeatureStopDrag(aiMarker); 
                  }
                  
                  public void onMarkerDragStart(Marker param2Marker) {
                    Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
                    while (iterator.hasNext())
                      ((MapFactory.MapEventListener)iterator.next()).onFeatureStartDrag(aiMarker); 
                  }
                });
            if (aiMarker.Visible()) {
              NativeOpenStreetMapController.this.showOverlay((OverlayWithIW)param1Marker);
              return;
            } 
            NativeOpenStreetMapController.this.hideOverlay((OverlayWithIW)param1Marker);
          }
        });
  }
  
  public void addFeature(MapFactory.MapPolygon paramMapPolygon) {
    configurePolygon(paramMapPolygon, createNativePolygon(paramMapPolygon));
  }
  
  public void addFeature(MapFactory.MapRectangle paramMapRectangle) {
    configurePolygon(paramMapRectangle, createNativeRectangle(paramMapRectangle));
  }
  
  public BoundingBox getBoundingBox() {
    return this.view.getBoundingBox();
  }
  
  public double getLatitude() {
    return this.view.getMapCenter().getLatitude();
  }
  
  public LocationSensor.LocationSensorListener getLocationListener() {
    return this.locationProvider;
  }
  
  public double getLongitude() {
    return this.view.getMapCenter().getLongitude();
  }
  
  public MapFactory.MapType getMapType() {
    return this.tileType;
  }
  
  public int getOverlayCount() {
    System.err.println(this.view.getOverlays());
    return this.view.getOverlays().size();
  }
  
  public float getRotation() {
    return this.view.getMapOrientation();
  }
  
  public MapFactory.MapScaleUnits getScaleUnits() {
    switch (this.scaleBar.getUnitsOfMeasure()) {
      default:
        throw new IllegalStateException("Somehow we have an unallowed unit system");
      case imperial:
        return MapFactory.MapScaleUnits.IMPERIAL;
      case metric:
        break;
    } 
    return MapFactory.MapScaleUnits.METRIC;
  }
  
  public View getView() {
    return (View)this.containerView;
  }
  
  public int getZoom() {
    return (int)this.view.getZoomLevel(true);
  }
  
  public void hideFeature(MapFactory.MapFeature paramMapFeature) {
    hideOverlay(this.featureOverlays.get(paramMapFeature));
  }
  
  public void hideInfobox(MapFactory.MapFeature paramMapFeature) {
    ((OverlayWithIW)this.featureOverlays.get(paramMapFeature)).closeInfoWindow();
  }
  
  protected void hideOverlay(OverlayWithIW paramOverlayWithIW) {
    this.view.getOverlayManager().remove(paramOverlayWithIW);
    this.view.invalidate();
  }
  
  public boolean isCompassEnabled() {
    return (this.compass != null && this.compass.isCompassEnabled());
  }
  
  public boolean isFeatureCollectionVisible(MapFactory.MapFeatureCollection paramMapFeatureCollection) {
    return !this.hiddenFeatureCollections.contains(paramMapFeatureCollection);
  }
  
  public boolean isFeatureVisible(MapFactory.MapFeature paramMapFeature) {
    OverlayWithIW overlayWithIW = this.featureOverlays.get(paramMapFeature);
    return (overlayWithIW != null && this.view.getOverlayManager().contains(overlayWithIW));
  }
  
  public boolean isInfoboxVisible(MapFactory.MapFeature paramMapFeature) {
    OverlayWithIW overlayWithIW = this.featureOverlays.get(paramMapFeature);
    return (overlayWithIW != null && overlayWithIW.isInfoWindowOpen());
  }
  
  public boolean isPanEnabled() {
    return this.touch.scrollEnabled;
  }
  
  public boolean isRotationEnabled() {
    return (this.rotation != null && this.rotation.isEnabled());
  }
  
  public boolean isScaleVisible() {
    return this.scaleBar.isEnabled();
  }
  
  public boolean isShowUserEnabled() {
    return (this.userLocation != null && this.userLocation.isEnabled());
  }
  
  public boolean isZoomControlEnabled() {
    return this.zoomControlEnabled;
  }
  
  public boolean isZoomEnabled() {
    return this.zoomEnabled;
  }
  
  public boolean onScroll(ScrollEvent paramScrollEvent) {
    Iterator<MapFactory.MapEventListener> iterator = this.eventListeners.iterator();
    while (iterator.hasNext())
      ((MapFactory.MapEventListener)iterator.next()).onBoundsChanged(); 
    return true;
  }
  
  public boolean onZoom(ZoomEvent paramZoomEvent) {
    this.zoomControls.updateButtons();
    Iterator<MapFactory.MapEventListener> iterator = this.eventListeners.iterator();
    while (iterator.hasNext())
      ((MapFactory.MapEventListener)iterator.next()).onZoom(); 
    return true;
  }
  
  public void panTo(double paramDouble1, double paramDouble2, int paramInt, double paramDouble3) {
    this.view.getController().animateTo((IGeoPoint)new GeoPoint(paramDouble1, paramDouble2));
    if (this.view.getController().zoomTo(paramInt)) {
      Animation animation = this.view.getAnimation();
      if (animation != null)
        animation.setDuration((long)(1000.0D * paramDouble3)); 
    } 
  }
  
  public void removeFeature(MapFactory.MapFeature paramMapFeature) {
    this.view.getOverlayManager().remove(this.featureOverlays.get(paramMapFeature));
    this.featureOverlays.remove(paramMapFeature);
  }
  
  public void setBoundingBox(BoundingBox paramBoundingBox) {
    this.view.getController().setCenter((IGeoPoint)paramBoundingBox.getCenter());
    this.view.getController().zoomToSpan(paramBoundingBox.getLatitudeSpan(), paramBoundingBox.getLongitudeSpan());
  }
  
  public void setCenter(double paramDouble1, double paramDouble2) {
    this.view.getController().setCenter((IGeoPoint)new GeoPoint(paramDouble1, paramDouble2));
  }
  
  public void setCompassEnabled(boolean paramBoolean) {
    if (paramBoolean && this.compass == null) {
      this.compass = new CompassOverlay(this.view.getContext(), this.view);
      this.view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
              float f = (NativeOpenStreetMapController.this.view.getContext().getResources().getDisplayMetrics()).density;
              NativeOpenStreetMapController.this.compass.setCompassCenter(NativeOpenStreetMapController.this.view.getMeasuredWidth() / f - 35.0F, 35.0F);
              return true;
            }
          });
      this.view.getOverlayManager().add(this.compass);
    } 
    if (this.compass != null) {
      if (paramBoolean) {
        if (this.compass.getOrientationProvider() != null) {
          this.compass.enableCompass();
        } else {
          this.compass.enableCompass((IOrientationProvider)new InternalCompassOrientationProvider(this.view.getContext()));
        } 
        this.compass.onOrientationChanged(this.lastAzimuth, null);
        return;
      } 
    } else {
      return;
    } 
    this.lastAzimuth = this.compass.getOrientation();
    this.compass.disableCompass();
  }
  
  public void setFeatureCollectionVisible(MapFactory.MapFeatureCollection paramMapFeatureCollection, boolean paramBoolean) {
    if ((paramBoolean || !this.hiddenFeatureCollections.contains(paramMapFeatureCollection)) && (!paramBoolean || this.hiddenFeatureCollections.contains(paramMapFeatureCollection))) {
      if (paramBoolean) {
        this.hiddenFeatureCollections.remove(paramMapFeatureCollection);
        iterator = paramMapFeatureCollection.iterator();
        while (true) {
          if (iterator.hasNext()) {
            MapFactory.MapFeature mapFeature = iterator.next();
            this.hiddenFeatures.remove(mapFeature);
            if (mapFeature.Visible())
              showFeature(mapFeature); 
            continue;
          } 
          return;
        } 
      } 
      this.hiddenFeatureCollections.add(iterator);
      Iterator<MapFactory.MapFeature> iterator = iterator.iterator();
      while (true) {
        if (iterator.hasNext()) {
          MapFactory.MapFeature mapFeature = iterator.next();
          this.hiddenFeatures.add(mapFeature);
          hideFeature(mapFeature);
          continue;
        } 
        return;
      } 
    } 
  }
  
  public void setMapType(MapFactory.MapType paramMapType) {
    switch (paramMapType) {
      default:
        return;
      case imperial:
        this.tileType = paramMapType;
        this.view.setTileSource((ITileSource)TileSourceFactory.MAPNIK);
        return;
      case metric:
        this.tileType = paramMapType;
        this.view.setTileSource((ITileSource)TileSourceFactory.USGS_SAT);
        return;
      case null:
        break;
    } 
    this.tileType = paramMapType;
    this.view.setTileSource((ITileSource)TileSourceFactory.USGS_TOPO);
  }
  
  public void setPanEnabled(boolean paramBoolean) {
    TouchOverlay.access$802(this.touch, paramBoolean);
  }
  
  public void setRotation(float paramFloat) {
    this.view.setMapOrientation(paramFloat);
  }
  
  public void setRotationEnabled(boolean paramBoolean) {
    if (paramBoolean && this.rotation == null)
      this.rotation = new RotationGestureOverlay(this.view); 
    if (this.rotation != null) {
      this.rotation.setEnabled(paramBoolean);
      if (paramBoolean) {
        this.view.getOverlayManager().add(this.rotation);
        return;
      } 
    } else {
      return;
    } 
    this.view.getOverlayManager().remove(this.rotation);
  }
  
  public void setScaleUnits(MapFactory.MapScaleUnits paramMapScaleUnits) {
    switch (paramMapScaleUnits) {
      default:
        throw new IllegalArgumentException("Unallowable unit system: " + paramMapScaleUnits);
      case imperial:
        this.scaleBar.setUnitsOfMeasure(ScaleBarOverlay.UnitsOfMeasure.metric);
        this.view.invalidate();
        return;
      case metric:
        break;
    } 
    this.scaleBar.setUnitsOfMeasure(ScaleBarOverlay.UnitsOfMeasure.imperial);
    this.view.invalidate();
  }
  
  public void setScaleVisible(boolean paramBoolean) {
    this.scaleBar.setEnabled(paramBoolean);
    this.view.invalidate();
  }
  
  public void setShowUserEnabled(boolean paramBoolean) {
    this.userLocation.setEnabled(paramBoolean);
    if (paramBoolean) {
      this.userLocation.enableMyLocation();
      this.view.getOverlayManager().add(this.userLocation);
      return;
    } 
    this.userLocation.disableMyLocation();
    this.view.getOverlayManager().remove(this.userLocation);
  }
  
  public void setZoom(int paramInt) {
    this.view.getController().setZoom(paramInt);
    this.zoomControls.updateButtons();
  }
  
  public void setZoomControlEnabled(boolean paramBoolean) {
    if (this.zoomControlEnabled != paramBoolean) {
      byte b;
      ZoomControlView zoomControlView = this.zoomControls;
      if (paramBoolean) {
        b = 0;
      } else {
        b = 8;
      } 
      zoomControlView.setVisibility(b);
      this.zoomControlEnabled = paramBoolean;
      this.containerView.invalidate();
    } 
  }
  
  public void setZoomEnabled(boolean paramBoolean) {
    this.zoomEnabled = paramBoolean;
    this.view.setMultiTouchControls(paramBoolean);
  }
  
  public void showFeature(MapFactory.MapFeature paramMapFeature) {
    if (!this.hiddenFeatures.contains(paramMapFeature))
      showOverlay(this.featureOverlays.get(paramMapFeature)); 
  }
  
  public void showInfobox(MapFactory.MapFeature paramMapFeature) {
    ((OverlayWithIW)this.featureOverlays.get(paramMapFeature)).showInfoWindow();
  }
  
  protected void showOverlay(OverlayWithIW paramOverlayWithIW) {
    this.view.getOverlayManager().add(paramOverlayWithIW);
    this.view.invalidate();
  }
  
  public void updateFeatureDraggable(MapFactory.MapFeature paramMapFeature) {
    OverlayWithIW overlayWithIW = this.featureOverlays.get(paramMapFeature);
    if (overlayWithIW != null)
      overlayWithIW.setDraggable(paramMapFeature.Draggable()); 
  }
  
  public void updateFeatureFill(final MapFactory.HasFill aiFeature) {
    OverlayWithIW overlayWithIW = this.featureOverlays.get(aiFeature);
    if (overlayWithIW == null)
      return; 
    overlayWithIW.accept(new OverlayWithIWVisitor() {
          public void visit(final Marker marker) {
            NativeOpenStreetMapController.this.getMarkerDrawable((MapFactory.MapMarker)aiFeature, new AsyncCallbackPair<Drawable>() {
                  public void onFailure(String param2String) {
                    Log.e(NativeOpenStreetMapController.TAG, "Unable to update fill color for marker: " + param2String);
                  }
                  
                  public void onSuccess(Drawable param2Drawable) {
                    marker.setIcon(param2Drawable);
                    NativeOpenStreetMapController.this.view.invalidate();
                  }
                });
          }
          
          public void visit(Polygon param1Polygon) {
            param1Polygon.setFillColor(aiFeature.FillColor());
            NativeOpenStreetMapController.this.view.invalidate();
          }
          
          public void visit(Polyline param1Polyline) {}
        });
  }
  
  public void updateFeatureHoles(MapFactory.MapPolygon paramMapPolygon) {
    MultiPolygon multiPolygon = (MultiPolygon)this.featureOverlays.get(paramMapPolygon);
    if (multiPolygon != null) {
      multiPolygon.setMultiHoles(paramMapPolygon.getHolePoints());
      this.view.invalidate();
    } 
  }
  
  public void updateFeatureImage(MapFactory.MapMarker paramMapMarker) {
    final Marker marker = (Marker)this.featureOverlays.get(paramMapMarker);
    if (marker == null)
      return; 
    getMarkerDrawable(paramMapMarker, new AsyncCallbackPair<Drawable>() {
          public void onFailure(String param1String) {
            Log.e(NativeOpenStreetMapController.TAG, "Unable to update feature image: " + param1String);
          }
          
          public void onSuccess(Drawable param1Drawable) {
            marker.setIcon(param1Drawable);
            NativeOpenStreetMapController.this.view.invalidate();
          }
        });
  }
  
  public void updateFeaturePosition(MapFactory.MapCircle paramMapCircle) {
    GeoPoint geoPoint = new GeoPoint(paramMapCircle.Latitude(), paramMapCircle.Longitude());
    Polygon polygon = (Polygon)this.featureOverlays.get(paramMapCircle);
    if (polygon != null) {
      polygon.setPoints(Polygon.pointsAsCircle(geoPoint, paramMapCircle.Radius()));
      this.view.invalidate();
    } 
  }
  
  public void updateFeaturePosition(MapFactory.MapLineString paramMapLineString) {
    Polyline polyline = (Polyline)this.featureOverlays.get(paramMapLineString);
    if (polyline != null) {
      polyline.setPoints(paramMapLineString.getPoints());
      this.view.invalidate();
    } 
  }
  
  public void updateFeaturePosition(MapFactory.MapMarker paramMapMarker) {
    Marker marker = (Marker)this.featureOverlays.get(paramMapMarker);
    if (marker != null) {
      marker.setAnchor(ANCHOR_HORIZONTAL[paramMapMarker.AnchorHorizontal()], ANCHOR_VERTICAL[paramMapMarker.AnchorVertical()]);
      marker.setPosition(new GeoPoint(paramMapMarker.Latitude(), paramMapMarker.Longitude()));
      this.view.invalidate();
    } 
  }
  
  public void updateFeaturePosition(MapFactory.MapPolygon paramMapPolygon) {
    MultiPolygon multiPolygon = (MultiPolygon)this.featureOverlays.get(paramMapPolygon);
    if (multiPolygon != null) {
      multiPolygon.setMultiPoints(paramMapPolygon.getPoints());
      this.view.invalidate();
    } 
  }
  
  public void updateFeaturePosition(MapFactory.MapRectangle paramMapRectangle) {
    Polygon polygon = (Polygon)this.featureOverlays.get(paramMapRectangle);
    if (polygon != null) {
      polygon.setPoints(Polygon.pointsAsRect(new BoundingBox(paramMapRectangle.NorthLatitude(), paramMapRectangle.EastLongitude(), paramMapRectangle.SouthLatitude(), paramMapRectangle.WestLongitude())));
      this.view.invalidate();
    } 
  }
  
  public void updateFeatureSize(MapFactory.MapMarker paramMapMarker) {
    final Marker marker = (Marker)this.featureOverlays.get(paramMapMarker);
    if (marker == null)
      return; 
    getMarkerDrawable(paramMapMarker, new AsyncCallbackPair<Drawable>() {
          public void onFailure(String param1String) {
            Log.wtf(NativeOpenStreetMapController.TAG, "Cannot find default marker");
          }
          
          public void onSuccess(Drawable param1Drawable) {
            marker.setIcon(param1Drawable);
            NativeOpenStreetMapController.this.view.invalidate();
          }
        });
  }
  
  public void updateFeatureStroke(final MapFactory.HasStroke aiFeature) {
    OverlayWithIW overlayWithIW = this.featureOverlays.get(aiFeature);
    if (overlayWithIW == null)
      return; 
    overlayWithIW.accept(new OverlayWithIWVisitor() {
          public void visit(final Marker marker) {
            NativeOpenStreetMapController.this.getMarkerDrawable((MapFactory.MapMarker)aiFeature, new AsyncCallbackPair<Drawable>() {
                  public void onFailure(String param2String) {
                    Log.e(NativeOpenStreetMapController.TAG, "Unable to update stroke color for marker: " + param2String);
                  }
                  
                  public void onSuccess(Drawable param2Drawable) {
                    marker.setIcon(param2Drawable);
                    NativeOpenStreetMapController.this.view.invalidate();
                  }
                });
          }
          
          public void visit(Polygon param1Polygon) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            NativeOpenStreetMapController.this.form.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            param1Polygon.setStrokeColor(aiFeature.StrokeColor());
            param1Polygon.setStrokeWidth(aiFeature.StrokeWidth() * displayMetrics.density);
            NativeOpenStreetMapController.this.view.invalidate();
          }
          
          public void visit(Polyline param1Polyline) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            NativeOpenStreetMapController.this.form.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            param1Polyline.setColor(aiFeature.StrokeColor());
            param1Polyline.setWidth(aiFeature.StrokeWidth() * displayMetrics.density);
            NativeOpenStreetMapController.this.view.invalidate();
          }
        });
  }
  
  public void updateFeatureText(MapFactory.MapFeature paramMapFeature) {
    OverlayWithIW overlayWithIW = this.featureOverlays.get(paramMapFeature);
    if (overlayWithIW != null) {
      overlayWithIW.setTitle(paramMapFeature.Title());
      overlayWithIW.setSnippet(paramMapFeature.Description());
    } 
  }
  
  private static class AppInventorLocationSensorAdapter implements IMyLocationProvider, LocationSensor.LocationSensorListener {
    private IMyLocationConsumer consumer;
    
    private boolean enabled = false;
    
    private Location lastLocation;
    
    private LocationSensor source;
    
    private AppInventorLocationSensorAdapter() {}
    
    public void destroy() {
      this.consumer = null;
    }
    
    public Location getLastKnownLocation() {
      return this.lastLocation;
    }
    
    public void onDistanceIntervalChanged(int param1Int) {}
    
    public void onLocationChanged(Location param1Location) {
      this.lastLocation = param1Location;
      if (this.consumer != null)
        this.consumer.onLocationChanged(param1Location, this); 
    }
    
    public void onProviderDisabled(String param1String) {}
    
    public void onProviderEnabled(String param1String) {}
    
    public void onStatusChanged(String param1String, int param1Int, Bundle param1Bundle) {}
    
    public void onTimeIntervalChanged(int param1Int) {}
    
    public void setSource(LocationSensor param1LocationSensor) {
      if (this.source != param1LocationSensor) {
        if (this.source != null)
          this.source.Enabled(false); 
        this.source = param1LocationSensor;
        if (this.source != null) {
          this.source.Enabled(this.enabled);
          return;
        } 
      } 
    }
    
    public boolean startLocationProvider(IMyLocationConsumer param1IMyLocationConsumer) {
      this.consumer = param1IMyLocationConsumer;
      if (this.source != null) {
        this.source.Enabled(true);
        this.enabled = true;
      } 
      return this.enabled;
    }
    
    public void stopLocationProvider() {
      if (this.source != null)
        this.source.Enabled(false); 
      this.enabled = false;
    }
  }
  
  private class CustomMapView extends MapView {
    public CustomMapView(Context param1Context) {
      super(param1Context, null, new NativeOpenStreetMapController.MapReadyHandler(NativeOpenStreetMapController.this, null));
    }
    
    public void onDetach() {}
    
    protected void onSizeChanged(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      scrollTo(getScrollX() + (param1Int3 - param1Int1) / 2, getScrollY() + (param1Int4 - param1Int2) / 2);
      super.onSizeChanged(param1Int1, param1Int2, param1Int3, param1Int4);
    }
  }
  
  private class MapReadyHandler extends Handler {
    private MapReadyHandler() {}
    
    public void handleMessage(Message param1Message) {
      switch (param1Message.what) {
        default:
          return;
        case 0:
          break;
      } 
      if (!NativeOpenStreetMapController.this.ready && NativeOpenStreetMapController.this.form.canDispatchEvent(null, "MapReady")) {
        NativeOpenStreetMapController.access$102(NativeOpenStreetMapController.this, true);
        NativeOpenStreetMapController.this.form.runOnUiThread(new Runnable() {
              public void run() {
                Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
                while (iterator.hasNext())
                  ((MapFactory.MapEventListener)iterator.next()).onReady(NativeOpenStreetMapController.this); 
              }
            });
      } 
      NativeOpenStreetMapController.this.view.invalidate();
    }
  }
  
  class null implements Runnable {
    public void run() {
      Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
      while (iterator.hasNext())
        ((MapFactory.MapEventListener)iterator.next()).onReady(NativeOpenStreetMapController.this); 
    }
  }
  
  static class MultiPolygon extends Polygon {
    private List<Polygon> children = new ArrayList<Polygon>();
    
    private Polygon.OnClickListener clickListener;
    
    private Polygon.OnDragListener dragListener;
    
    private boolean draggable;
    
    public boolean contains(MotionEvent param1MotionEvent) {
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext()) {
        if (((Polygon)iterator.next()).contains(param1MotionEvent))
          return true; 
      } 
      return false;
    }
    
    public void draw(Canvas param1Canvas, MapView param1MapView, boolean param1Boolean) {
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        ((Polygon)iterator.next()).draw(param1Canvas, param1MapView, param1Boolean); 
    }
    
    public void finishMove(MotionEvent param1MotionEvent1, MotionEvent param1MotionEvent2, MapView param1MapView) {
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        ((Polygon)iterator.next()).finishMove(param1MotionEvent1, param1MotionEvent2, param1MapView); 
    }
    
    public List<List<List<GeoPoint>>> getMultiHoles() {
      ArrayList<List> arrayList = new ArrayList();
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        arrayList.add(((Polygon)iterator.next()).getHoles()); 
      return (List)arrayList;
    }
    
    public List<List<GeoPoint>> getMultiPoints() {
      ArrayList<List> arrayList = new ArrayList();
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        arrayList.add(((Polygon)iterator.next()).getPoints()); 
      return (List)arrayList;
    }
    
    public void moveToEventPosition(MotionEvent param1MotionEvent1, MotionEvent param1MotionEvent2, MapView param1MapView) {
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        ((Polygon)iterator.next()).moveToEventPosition(param1MotionEvent1, param1MotionEvent2, param1MapView); 
    }
    
    public boolean onLongPress(MotionEvent param1MotionEvent, MapView param1MapView) {
      boolean bool = contains(param1MotionEvent);
      if (bool) {
        if (this.mDraggable) {
          this.mIsDragged = true;
          closeInfoWindow();
          this.mDragStartPoint = param1MotionEvent;
          if (this.mOnDragListener != null)
            this.mOnDragListener.onDragStart(this); 
          moveToEventPosition(param1MotionEvent, this.mDragStartPoint, param1MapView);
          return bool;
        } 
      } else {
        return bool;
      } 
      if (this.mOnClickListener != null) {
        this.mOnClickListener.onLongClick(this, param1MapView, (GeoPoint)param1MapView.getProjection().fromPixels((int)param1MotionEvent.getX(), (int)param1MotionEvent.getY()));
        return bool;
      } 
      return bool;
    }
    
    public boolean onSingleTapConfirmed(MotionEvent param1MotionEvent, MapView param1MapView) {
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext()) {
        if (((Polygon)iterator.next()).onSingleTapConfirmed(param1MotionEvent, param1MapView))
          return true; 
      } 
      return false;
    }
    
    public boolean onTouchEvent(MotionEvent param1MotionEvent, MapView param1MapView) {
      if (this.mDraggable && this.mIsDragged) {
        if (param1MotionEvent.getAction() == 1) {
          this.mIsDragged = false;
          finishMove(this.mDragStartPoint, param1MotionEvent, param1MapView);
          if (this.mOnDragListener != null)
            this.mOnDragListener.onDragEnd(this); 
          return true;
        } 
        if (param1MotionEvent.getAction() == 2) {
          moveToEventPosition(param1MotionEvent, this.mDragStartPoint, param1MapView);
          if (this.mOnDragListener != null) {
            this.mOnDragListener.onDrag(this);
            return true;
          } 
          return true;
        } 
      } 
      return false;
    }
    
    public void setDraggable(boolean param1Boolean) {
      super.setDraggable(param1Boolean);
      this.draggable = param1Boolean;
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        ((Polygon)iterator.next()).setDraggable(param1Boolean); 
    }
    
    public void setFillColor(int param1Int) {
      super.setFillColor(param1Int);
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        ((Polygon)iterator.next()).setFillColor(param1Int); 
    }
    
    public void setMultiHoles(List<List<List<GeoPoint>>> param1List) {
      Iterator<Polygon> iterator;
      if (param1List == null || param1List.isEmpty()) {
        iterator = this.children.iterator();
        while (iterator.hasNext())
          ((Polygon)iterator.next()).setHoles(Collections.emptyList()); 
      } else {
        if (iterator.size() != this.children.size())
          throw new IllegalArgumentException("Holes and points are not of the same arity."); 
        Iterator<Polygon> iterator1 = this.children.iterator();
        iterator = iterator.iterator();
        while (iterator1.hasNext() && iterator.hasNext())
          ((Polygon)iterator1.next()).setHoles((List)iterator.next()); 
      } 
    }
    
    public void setMultiPoints(List<List<GeoPoint>> param1List) {
      Iterator<Polygon> iterator1 = this.children.iterator();
      Iterator<List<GeoPoint>> iterator = param1List.iterator();
      while (iterator1.hasNext() && iterator.hasNext())
        ((Polygon)iterator1.next()).setPoints(iterator.next()); 
      while (iterator1.hasNext()) {
        iterator1.next();
        iterator1.remove();
      } 
      while (iterator.hasNext()) {
        Polygon polygon = new Polygon();
        polygon.setPoints(iterator.next());
        polygon.setStrokeColor(getStrokeColor());
        polygon.setFillColor(getFillColor());
        polygon.setStrokeWidth(getStrokeWidth());
        polygon.setInfoWindow(getInfoWindow());
        polygon.setDraggable(this.draggable);
        polygon.setOnClickListener(this.clickListener);
        polygon.setOnDragListener(this.dragListener);
        this.children.add(polygon);
      } 
    }
    
    public void setOnClickListener(Polygon.OnClickListener param1OnClickListener) {
      super.setOnClickListener(param1OnClickListener);
      this.clickListener = param1OnClickListener;
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        ((Polygon)iterator.next()).setOnClickListener(param1OnClickListener); 
    }
    
    public void setOnDragListener(Polygon.OnDragListener param1OnDragListener) {
      super.setOnDragListener(param1OnDragListener);
      this.dragListener = param1OnDragListener;
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        ((Polygon)iterator.next()).setOnDragListener(param1OnDragListener); 
    }
    
    public void setSnippet(String param1String) {
      super.setSnippet(param1String);
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        ((Polygon)iterator.next()).setSnippet(param1String); 
    }
    
    public void setStrokeColor(int param1Int) {
      super.setStrokeColor(param1Int);
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        ((Polygon)iterator.next()).setStrokeColor(param1Int); 
    }
    
    public void setStrokeWidth(float param1Float) {
      super.setStrokeWidth(param1Float);
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        ((Polygon)iterator.next()).setStrokeWidth(param1Float); 
    }
    
    public void setTitle(String param1String) {
      super.setTitle(param1String);
      Iterator<Polygon> iterator = this.children.iterator();
      while (iterator.hasNext())
        ((Polygon)iterator.next()).setTitle(param1String); 
    }
    
    public void showInfoWindow() {
      if (this.children.size() > 0)
        ((Polygon)this.children.get(0)).showInfoWindow(); 
    }
  }
  
  private class TouchOverlay extends Overlay {
    private boolean scrollEnabled = true;
    
    private TouchOverlay() {}
    
    public void draw(Canvas param1Canvas, MapView param1MapView, boolean param1Boolean) {}
    
    public boolean onFling(MotionEvent param1MotionEvent1, MotionEvent param1MotionEvent2, float param1Float1, float param1Float2, MapView param1MapView) {
      return !this.scrollEnabled;
    }
    
    public boolean onLongPress(MotionEvent param1MotionEvent, MapView param1MapView) {
      IGeoPoint iGeoPoint = param1MapView.getProjection().fromPixels((int)param1MotionEvent.getX(), (int)param1MotionEvent.getY());
      double d1 = iGeoPoint.getLatitude();
      double d2 = iGeoPoint.getLongitude();
      Iterator<MapFactory.MapEventListener> iterator = NativeOpenStreetMapController.this.eventListeners.iterator();
      while (iterator.hasNext())
        ((MapFactory.MapEventListener)iterator.next()).onLongPress(d1, d2); 
      return false;
    }
    
    public boolean onScroll(MotionEvent param1MotionEvent1, MotionEvent param1MotionEvent2, float param1Float1, float param1Float2, MapView param1MapView) {
      return !this.scrollEnabled;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/NativeOpenStreetMapController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
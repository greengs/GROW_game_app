package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.RequiresApi;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.collect.Sets;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.BoundingBox;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.PaintUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@DesignerComponent(category = ComponentCategory.ANIMATION, description = "<p>A two-dimensional touch-sensitive rectangular panel on which drawing can be done and sprites can be moved.</p> <p>The <code>BackgroundColor</code>, <code>PaintColor</code>, <code>BackgroundImage</code>, <code>Width</code>, and <code>Height</code> of the Canvas can be set in either the Designer or in the Blocks Editor.  The <code>Width</code> and <code>Height</code> are measured in pixels and must be positive.</p><p>Any location on the Canvas can be specified as a pair of (X, Y) values, where <ul> <li>X is the number of pixels away from the left edge of the Canvas</li><li>Y is the number of pixels away from the top edge of the Canvas</li></ul>.</p> <p>There are events to tell when and where a Canvas has been touched or a <code>Sprite</code> (<code>ImageSprite</code> or <code>Ball</code>) has been dragged.  There are also methods for drawing points, lines, and circles.</p>", version = 13)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public final class Canvas extends AndroidViewComponent implements ComponentContainer {
  private static final int DEFAULT_BACKGROUND_COLOR = -1;
  
  private static final float DEFAULT_LINE_WIDTH = 2.0F;
  
  private static final int DEFAULT_PAINT_COLOR = -16777216;
  
  private static final int DEFAULT_TEXTALIGNMENT = 1;
  
  private static final int FLING_INTERVAL = 1000;
  
  private static final String LOG_TAG = "Canvas";
  
  private static final int MIN_WIDTH_HEIGHT = 1;
  
  private int backgroundColor;
  
  private String backgroundImagePath = "";
  
  private final Activity context;
  
  private boolean drawn;
  
  private boolean extendMovesOutsideCanvas = false;
  
  private final Set<ExtensionGestureDetector> extensionGestureDetectors = Sets.newHashSet();
  
  private Form form = $form();
  
  private boolean havePermission = false;
  
  private final GestureDetector mGestureDetector;
  
  private final MotionEventParser motionEventParser;
  
  private final Paint paint;
  
  private int paintColor;
  
  private final List<Sprite> sprites;
  
  private int textAlignment;
  
  private final CanvasView view;
  
  public Canvas(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    this.context = paramComponentContainer.$context();
    this.view = new CanvasView((Context)this.context);
    paramComponentContainer.$add(this);
    this.paint = new Paint();
    this.paint.setFlags(1);
    this.paint.setStrokeWidth(2.0F);
    PaintColor(-16777216);
    BackgroundColor(-1);
    TextAlignment(1);
    FontSize(14.0F);
    this.sprites = new LinkedList<Sprite>();
    this.motionEventParser = new MotionEventParser();
    this.mGestureDetector = new GestureDetector((Context)this.context, (GestureDetector.OnGestureListener)new FlingGestureListener());
  }
  
  private void changePaint(Paint paramPaint, int paramInt) {
    if (paramInt == 0) {
      PaintUtil.changePaint(paramPaint, -16777216);
      return;
    } 
    if (paramInt == 16777215) {
      PaintUtil.changePaintTransparent(paramPaint);
      return;
    } 
    PaintUtil.changePaint(paramPaint, paramInt);
  }
  
  private Path parsePath(float[][] paramArrayOffloat) throws IllegalArgumentException {
    if (paramArrayOffloat == null || paramArrayOffloat.length == 0)
      throw new IllegalArgumentException(); 
    float f = $form().deviceDensity();
    Path path = new Path();
    path.moveTo(paramArrayOffloat[0][0] * f, paramArrayOffloat[0][1] * f);
    for (int i = 1; i < paramArrayOffloat.length; i++)
      path.lineTo(paramArrayOffloat[i][0] * f, paramArrayOffloat[i][1] * f); 
    return path;
  }
  
  private float[][] parsePointList(YailList paramYailList) throws IllegalArgumentException {
    int i = 0;
    if (paramYailList == null || paramYailList.size() == 0)
      throw new IllegalArgumentException(); 
    int j = paramYailList.size();
    float[][] arrayOfFloat = (float[][])Array.newInstance(float.class, new int[] { j, 2 });
    j = 0;
    Object[] arrayOfObject = paramYailList.toArray();
    int k = arrayOfObject.length;
    while (i < k) {
      Object object = arrayOfObject[i];
      if (object instanceof YailList) {
        object = object;
        if (object.size() == 2) {
          try {
            arrayOfFloat[j][0] = Float.parseFloat(object.getString(0));
            arrayOfFloat[j][1] = Float.parseFloat(object.getString(1));
            j++;
            i++;
          } catch (NullPointerException nullPointerException) {
            throw new IllegalArgumentException(nullPointerException.fillInStackTrace());
          } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(numberFormatException.fillInStackTrace());
          } 
          continue;
        } 
        throw new IllegalArgumentException("length of item YailList(" + j + ") is not 2");
      } 
      throw new IllegalArgumentException("item(" + j + ") in YailList is not a YailList");
    } 
    return arrayOfFloat;
  }
  
  private String saveFile(File paramFile, Bitmap.CompressFormat paramCompressFormat, String paramString) {
    try {
      Bitmap bitmap;
      FileOutputStream fileOutputStream = new FileOutputStream(paramFile);
      if (this.view.completeCache == null) {
        bitmap = this.view.buildCache();
      } else {
        bitmap = this.view.completeCache;
      } 
      try {
        boolean bool = bitmap.compress(paramCompressFormat, 100, fileOutputStream);
        fileOutputStream.close();
      } finally {
        fileOutputStream.close();
      } 
      this.container.$form().dispatchErrorOccurredEvent(this, paramString, 1001, new Object[0]);
    } catch (FileNotFoundException fileNotFoundException) {
      this.container.$form().dispatchErrorOccurredEvent(this, paramString, 707, new Object[] { paramFile.getAbsolutePath() });
    } catch (IOException iOException) {
      this.container.$form().dispatchErrorOccurredEvent(this, paramString, 708, new Object[] { iOException.getMessage() });
    } 
    return "";
  }
  
  public void $add(AndroidViewComponent paramAndroidViewComponent) {
    throw new UnsupportedOperationException("Canvas.$add() called");
  }
  
  public Activity $context() {
    return this.context;
  }
  
  public Form $form() {
    return this.container.$form();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of the canvas background.")
  public int BackgroundColor() {
    return this.backgroundColor;
  }
  
  @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
  @SimpleProperty
  public void BackgroundColor(int paramInt) {
    this.view.setBackgroundColor(paramInt);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The name of a file containing the background image for the canvas")
  public String BackgroundImage() {
    return this.backgroundImagePath;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "asset")
  @SimpleProperty
  public void BackgroundImage(String paramString) {
    this.view.setBackgroundImage(paramString);
  }
  
  @SimpleProperty(description = "Set the background image in Base64 format. This requires API level >= 8. For devices with API level less than 8, setting this will end up with an empty background.")
  @RequiresApi(api = 8)
  public void BackgroundImageinBase64(String paramString) {
    if (SdkLevel.getLevel() >= 8) {
      this.view.setBackgroundImageBase64(paramString);
      return;
    } 
    this.view.setBackgroundImageBase64("");
  }
  
  @SimpleFunction(description = "Clears anything drawn on this Canvas but not any background color or image.")
  public void Clear() {
    this.view.clearDrawingLayer();
  }
  
  @SimpleEvent
  public void Dragged(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, boolean paramBoolean) {
    EventDispatcher.dispatchEvent(this, "Dragged", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3), Float.valueOf(paramFloat4), Float.valueOf(paramFloat5), Float.valueOf(paramFloat6), Boolean.valueOf(paramBoolean) });
  }
  
  @SimpleFunction(description = "Draw an arc on Canvas, by drawing an arc from a specified oval (specified by left, top, right & bottom). Start angle is 0 when heading to the right, and increase when rotate clockwise. When useCenter is true, a sector will be drawed instead of an arc. When fill is true, a filled arc (or sector) will be drawed instead of just an outline.")
  public void DrawArc(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2) {
    Paint.Style style;
    float f = $form().deviceDensity();
    Paint paint = new Paint(this.paint);
    if (paramBoolean2) {
      style = Paint.Style.FILL;
    } else {
      style = Paint.Style.STROKE;
    } 
    paint.setStyle(style);
    this.view.canvas.drawArc(new RectF(paramInt1 * f, paramInt2 * f, paramInt3 * f, paramInt4 * f), paramFloat1, paramFloat2, paramBoolean1, paint);
    this.view.invalidate();
  }
  
  @SimpleFunction
  public void DrawCircle(int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean) {
    Paint.Style style;
    float f1 = paramInt1;
    float f2 = $form().deviceDensity();
    float f3 = paramInt2;
    float f4 = $form().deviceDensity();
    float f5 = $form().deviceDensity();
    Paint paint = new Paint(this.paint);
    if (paramBoolean) {
      style = Paint.Style.FILL;
    } else {
      style = Paint.Style.STROKE;
    } 
    paint.setStyle(style);
    this.view.canvas.drawCircle(f1 * f2, f3 * f4, paramFloat * f5, paint);
    this.view.invalidate();
  }
  
  @SimpleFunction
  public void DrawLine(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    float f1 = paramInt1;
    float f2 = $form().deviceDensity();
    float f3 = paramInt2;
    float f4 = $form().deviceDensity();
    float f5 = paramInt3;
    float f6 = $form().deviceDensity();
    float f7 = paramInt4;
    float f8 = $form().deviceDensity();
    this.view.canvas.drawLine(f1 * f2, f3 * f4, f5 * f6, f7 * f8, this.paint);
    this.view.invalidate();
  }
  
  @SimpleFunction
  public void DrawPoint(int paramInt1, int paramInt2) {
    float f1 = paramInt1;
    float f2 = $form().deviceDensity();
    float f3 = paramInt2;
    float f4 = $form().deviceDensity();
    this.view.canvas.drawPoint(f1 * f2, f3 * f4, this.paint);
    this.view.invalidate();
  }
  
  @SimpleFunction(description = "Draws a shape on the canvas. pointList should be a list contains sub-lists with two number which represents a coordinate. The first point and last point does not need to be the same. e.g. ((x1 y1) (x2 y2) (x3 y3)) When fill is true, the shape will be filled.")
  public void DrawShape(YailList paramYailList, boolean paramBoolean) {
    try {
      Paint.Style style;
      Path path = parsePath(parsePointList(paramYailList));
      path.close();
      Paint paint = new Paint(this.paint);
      if (paramBoolean) {
        style = Paint.Style.FILL;
      } else {
        style = Paint.Style.STROKE;
      } 
      paint.setStyle(style);
      this.view.canvas.drawPath(path, paint);
      this.view.invalidate();
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      $form().dispatchErrorOccurredEvent(this, "DrawShape", 1004, new Object[0]);
      return;
    } 
  }
  
  @SimpleFunction(description = "Draws the specified text relative to the specified coordinates using the values of the FontSize and TextAlignment properties.")
  public void DrawText(String paramString, int paramInt1, int paramInt2) {
    float f1 = $form().deviceDensity();
    float f2 = paramInt1;
    float f3 = paramInt2;
    this.view.canvas.drawText(paramString, f2 * f1, f3 * f1, this.paint);
    this.view.invalidate();
  }
  
  @SimpleFunction(description = "Draws the specified text starting at the specified coordinates at the specified angle using the values of the FontSize and TextAlignment properties.")
  public void DrawTextAtAngle(String paramString, int paramInt1, int paramInt2, float paramFloat) {
    paramInt1 = (int)(paramInt1 * $form().deviceDensity());
    paramInt2 = (int)(paramInt2 * $form().deviceDensity());
    this.view.drawTextAtAngle(paramString, paramInt1, paramInt2, paramFloat);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(userVisible = true)
  public void ExtendMovesOutsideCanvas(boolean paramBoolean) {
    this.extendMovesOutsideCanvas = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines whether moves can extend beyond the canvas borders.   Default is false. This should normally be false, and the property is provided for backwards compatibility.", userVisible = true)
  public boolean ExtendMovesOutsideCanvas() {
    return this.extendMovesOutsideCanvas;
  }
  
  @SimpleEvent
  public void Flung(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, boolean paramBoolean) {
    EventDispatcher.dispatchEvent(this, "Flung", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3), Float.valueOf(paramFloat4), Float.valueOf(paramFloat5), Float.valueOf(paramFloat6), Boolean.valueOf(paramBoolean) });
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The font size of text drawn on the canvas.")
  public float FontSize() {
    float f = $form().deviceDensity();
    return this.paint.getTextSize() / f;
  }
  
  @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float")
  @SimpleProperty
  public void FontSize(float paramFloat) {
    float f = $form().deviceDensity();
    this.paint.setTextSize(paramFloat * f);
  }
  
  @SimpleFunction(description = "Gets the color of the specified point. This includes the background and any drawn points, lines, or circles but not sprites.")
  public int GetBackgroundPixelColor(int paramInt1, int paramInt2) {
    paramInt1 = (int)(paramInt1 * $form().deviceDensity());
    paramInt2 = (int)(paramInt2 * $form().deviceDensity());
    return this.view.getBackgroundPixelColor(paramInt1, paramInt2);
  }
  
  @SimpleFunction(description = "Gets the color of the specified point.")
  public int GetPixelColor(int paramInt1, int paramInt2) {
    paramInt1 = (int)(paramInt1 * $form().deviceDensity());
    paramInt2 = (int)(paramInt2 * $form().deviceDensity());
    return this.view.getPixelColor(paramInt1, paramInt2);
  }
  
  @SimpleProperty
  public void Height(int paramInt) {
    if (paramInt > 0 || paramInt == -2 || paramInt == -1 || paramInt <= -1000) {
      super.Height(paramInt);
      return;
    } 
    this.container.$form().dispatchErrorOccurredEvent(this, "Height", 1003, new Object[0]);
  }
  
  public void Initialize() {
    if (!this.havePermission && this.form.doesAppDeclarePermission("android.permission.WRITE_EXTERNAL_STORAGE"))
      this.form.askPermission(new BulkPermissionRequest(this, "Canvas", new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }) {
            public void onGranted() {
              Canvas.access$902(me, true);
            }
          }); 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The width of lines drawn on the canvas.")
  public float LineWidth() {
    return this.paint.getStrokeWidth() / $form().deviceDensity();
  }
  
  @DesignerProperty(defaultValue = "2.0", editorType = "non_negative_float")
  @SimpleProperty
  public void LineWidth(float paramFloat) {
    this.paint.setStrokeWidth($form().deviceDensity() * paramFloat);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color in which lines are drawn")
  public int PaintColor() {
    return this.paintColor;
  }
  
  @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
  @SimpleProperty
  public void PaintColor(int paramInt) {
    this.paintColor = paramInt;
    changePaint(this.paint, paramInt);
  }
  
  @SimpleFunction(description = "Saves a picture of this Canvas to the device's external storage. If an error occurs, the Screen's ErrorOccurred event will be called.")
  @UsesPermissions({"android.permission.WRITE_EXTERNAL_STORAGE"})
  public String Save() {
    try {
      return saveFile(FileUtil.getPictureFile($form(), "png"), Bitmap.CompressFormat.PNG, "Save");
    } catch (PermissionException permissionException) {
      this.container.$form().dispatchPermissionDeniedEvent(this, "Save", permissionException);
    } catch (IOException iOException) {
      this.container.$form().dispatchErrorOccurredEvent(this, "Save", 708, new Object[] { iOException.getMessage() });
    } catch (com.google.appinventor.components.runtime.util.FileUtil.FileException fileException) {
      this.container.$form().dispatchErrorOccurredEvent(this, "Save", fileException.getErrorMessageNumber(), new Object[0]);
    } 
    return "";
  }
  
  @SimpleFunction(description = "Saves a picture of this Canvas to the device's external storage in the file named fileName. fileName must end with one of .jpg, .jpeg, or .png, which determines the file type.")
  @UsesPermissions({"android.permission.WRITE_EXTERNAL_STORAGE"})
  public String SaveAs(String paramString) {
    Bitmap.CompressFormat compressFormat;
    if (paramString.endsWith(".jpg") || paramString.endsWith(".jpeg")) {
      compressFormat = Bitmap.CompressFormat.JPEG;
    } else if (paramString.endsWith(".png")) {
      compressFormat = Bitmap.CompressFormat.PNG;
    } else if (!paramString.contains(".")) {
      paramString = paramString + ".png";
      compressFormat = Bitmap.CompressFormat.PNG;
    } else {
      this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", 706, new Object[0]);
      return "";
    } 
    try {
      return saveFile(FileUtil.getExternalFile($form(), paramString), compressFormat, "SaveAs");
    } catch (PermissionException permissionException) {
      this.container.$form().dispatchPermissionDeniedEvent(this, "SaveAs", permissionException);
    } catch (IOException iOException) {
      this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", 708, new Object[] { iOException.getMessage() });
    } catch (com.google.appinventor.components.runtime.util.FileUtil.FileException fileException) {
      this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", fileException.getErrorMessageNumber(), new Object[0]);
    } 
    return "";
  }
  
  @SimpleFunction(description = "Sets the color of the specified point. This differs from DrawPoint by having an argument for color.")
  public void SetBackgroundPixelColor(int paramInt1, int paramInt2, int paramInt3) {
    Paint paint = new Paint();
    PaintUtil.changePaint(paint, paramInt3);
    paramInt1 = (int)(paramInt1 * $form().deviceDensity());
    paramInt2 = (int)(paramInt2 * $form().deviceDensity());
    this.view.canvas.drawPoint(paramInt1, paramInt2, paint);
    this.view.invalidate();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Determines the alignment of the text drawn by DrawText() or DrawAngle() with respect to the point specified by that command: point at the left of the text, point at the center of the text, or point at the right of the text.", userVisible = true)
  public int TextAlignment() {
    return this.textAlignment;
  }
  
  @DesignerProperty(defaultValue = "1", editorType = "textalignment")
  @SimpleProperty(userVisible = true)
  public void TextAlignment(int paramInt) {
    this.textAlignment = paramInt;
    switch (paramInt) {
      default:
        return;
      case 0:
        this.paint.setTextAlign(Paint.Align.LEFT);
        return;
      case 1:
        this.paint.setTextAlign(Paint.Align.CENTER);
        return;
      case 2:
        break;
    } 
    this.paint.setTextAlign(Paint.Align.RIGHT);
  }
  
  @SimpleEvent
  public void TouchDown(float paramFloat1, float paramFloat2) {
    EventDispatcher.dispatchEvent(this, "TouchDown", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) });
  }
  
  @SimpleEvent
  public void TouchUp(float paramFloat1, float paramFloat2) {
    EventDispatcher.dispatchEvent(this, "TouchUp", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) });
  }
  
  @SimpleEvent
  public void Touched(float paramFloat1, float paramFloat2, boolean paramBoolean) {
    EventDispatcher.dispatchEvent(this, "Touched", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Boolean.valueOf(paramBoolean) });
  }
  
  @SimpleProperty
  public void Width(int paramInt) {
    if (paramInt > 0 || paramInt == -2 || paramInt == -1 || paramInt <= -1000) {
      super.Width(paramInt);
      return;
    } 
    this.container.$form().dispatchErrorOccurredEvent(this, "Width", 1002, new Object[0]);
  }
  
  void addSprite(Sprite paramSprite) {
    for (int i = 0; i < this.sprites.size(); i++) {
      if (((Sprite)this.sprites.get(i)).Z() > paramSprite.Z()) {
        this.sprites.add(i, paramSprite);
        return;
      } 
    } 
    this.sprites.add(paramSprite);
  }
  
  void changeSpriteLayer(Sprite paramSprite) {
    removeSprite(paramSprite);
    addSprite(paramSprite);
    this.view.invalidate();
  }
  
  protected void findSpriteCollisions(Sprite paramSprite) {
    for (Sprite sprite : this.sprites) {
      if (sprite != paramSprite) {
        if (paramSprite.CollidingWith(sprite)) {
          if (!paramSprite.Visible() || !paramSprite.Enabled() || !sprite.Visible() || !sprite.Enabled() || !Sprite.colliding(sprite, paramSprite)) {
            paramSprite.NoLongerCollidingWith(sprite);
            sprite.NoLongerCollidingWith(paramSprite);
          } 
          continue;
        } 
        if (paramSprite.Visible() && paramSprite.Enabled() && sprite.Visible() && sprite.Enabled() && Sprite.colliding(sprite, paramSprite)) {
          paramSprite.CollidedWith(sprite);
          sprite.CollidedWith(paramSprite);
        } 
      } 
    } 
  }
  
  public Activity getContext() {
    return this.context;
  }
  
  public View getView() {
    return this.view;
  }
  
  public boolean ready() {
    return this.drawn;
  }
  
  void registerChange(Sprite paramSprite) {
    this.view.invalidate();
    findSpriteCollisions(paramSprite);
  }
  
  public void registerCustomGestureDetector(ExtensionGestureDetector paramExtensionGestureDetector) {
    this.extensionGestureDetectors.add(paramExtensionGestureDetector);
  }
  
  public void removeCustomGestureDetector(Object paramObject) {
    this.extensionGestureDetectors.remove(paramObject);
  }
  
  void removeSprite(Sprite paramSprite) {
    this.sprites.remove(paramSprite);
  }
  
  public void setChildHeight(AndroidViewComponent paramAndroidViewComponent, int paramInt) {
    throw new UnsupportedOperationException("Canvas.setChildHeight() called");
  }
  
  public void setChildWidth(AndroidViewComponent paramAndroidViewComponent, int paramInt) {
    throw new UnsupportedOperationException("Canvas.setChildWidth() called");
  }
  
  private final class CanvasView extends View {
    private BitmapDrawable backgroundDrawable;
    
    private Bitmap bitmap = Bitmap.createBitmap(32, 48, Bitmap.Config.ARGB_8888);
    
    private android.graphics.Canvas canvas = new android.graphics.Canvas(this.bitmap);
    
    private Bitmap completeCache;
    
    private Bitmap scaledBackgroundBitmap;
    
    public CanvasView(Context param1Context) {
      super(param1Context);
    }
    
    private Bitmap buildCache() {
      setDrawingCacheEnabled(true);
      destroyDrawingCache();
      Bitmap bitmap2 = getDrawingCache();
      Bitmap bitmap1 = bitmap2;
      if (bitmap2 == null) {
        int i = getWidth();
        int j = getHeight();
        bitmap1 = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
        android.graphics.Canvas canvas = new android.graphics.Canvas(bitmap1);
        layout(0, 0, i, j);
        draw(canvas);
      } 
      return bitmap1;
    }
    
    private void clearDrawingLayer() {
      this.canvas.drawColor(0, PorterDuff.Mode.CLEAR);
      invalidate();
    }
    
    private void drawTextAtAngle(String param1String, int param1Int1, int param1Int2, float param1Float) {
      this.canvas.save();
      this.canvas.rotate(-param1Float, param1Int1, param1Int2);
      this.canvas.drawText(param1String, param1Int1, param1Int2, Canvas.this.paint);
      this.canvas.restore();
      invalidate();
    }
    
    private int getBackgroundPixelColor(int param1Int1, int param1Int2) {
      if (param1Int1 < 0 || param1Int1 >= this.bitmap.getWidth() || param1Int2 < 0 || param1Int2 >= this.bitmap.getHeight())
        return 16777215; 
      try {
        int j = this.bitmap.getPixel(param1Int1, param1Int2);
        int i = j;
        if (j == 0) {
          if (this.backgroundDrawable != null) {
            if (this.scaledBackgroundBitmap == null)
              this.scaledBackgroundBitmap = Bitmap.createScaledBitmap(this.backgroundDrawable.getBitmap(), this.bitmap.getWidth(), this.bitmap.getHeight(), false); 
            return this.scaledBackgroundBitmap.getPixel(param1Int1, param1Int2);
          } 
          return (Color.alpha(Canvas.this.backgroundColor) != 0) ? Canvas.this.backgroundColor : 16777215;
        } 
        return i;
      } catch (IllegalArgumentException illegalArgumentException) {
        Log.e("Canvas", String.format("Returning COLOR_NONE (exception) from getBackgroundPixelColor.", new Object[0]));
        return 16777215;
      } 
    }
    
    private int getPixelColor(int param1Int1, int param1Int2) {
      if (param1Int1 < 0 || param1Int1 >= this.bitmap.getWidth() || param1Int2 < 0 || param1Int2 >= this.bitmap.getHeight())
        return 16777215; 
      if (this.completeCache == null) {
        boolean bool1;
        boolean bool2 = false;
        Iterator<Sprite> iterator = Canvas.this.sprites.iterator();
        while (true) {
          bool1 = bool2;
          if (iterator.hasNext()) {
            if (((Sprite)iterator.next()).Visible()) {
              bool1 = true;
              break;
            } 
            continue;
          } 
          break;
        } 
        if (!bool1)
          return getBackgroundPixelColor(param1Int1, param1Int2); 
        this.completeCache = buildCache();
      } 
      try {
        return this.completeCache.getPixel(param1Int1, param1Int2);
      } catch (IllegalArgumentException illegalArgumentException) {
        Log.e("Canvas", String.format("Returning COLOR_NONE (exception) from getPixelColor.", new Object[0]));
        return 16777215;
      } 
    }
    
    private int getSize(int param1Int1, int param1Int2) {
      int j = View.MeasureSpec.getMode(param1Int1);
      int i = View.MeasureSpec.getSize(param1Int1);
      if (j == 1073741824)
        return i; 
      param1Int1 = param1Int2;
      return (j == Integer.MIN_VALUE) ? Math.min(param1Int2, i) : param1Int1;
    }
    
    private void setBackground() {
      ColorDrawable colorDrawable;
      int i = -1;
      BitmapDrawable bitmapDrawable = this.backgroundDrawable;
      if (Canvas.this.backgroundImagePath != "" && this.backgroundDrawable != null) {
        Drawable drawable = this.backgroundDrawable.getConstantState().newDrawable();
        if (Canvas.this.backgroundColor != 0)
          i = Canvas.this.backgroundColor; 
        drawable.setColorFilter(i, PorterDuff.Mode.DST_OVER);
      } else {
        if (Canvas.this.backgroundColor != 0)
          i = Canvas.this.backgroundColor; 
        colorDrawable = new ColorDrawable(i);
      } 
      setBackgroundDrawable((Drawable)colorDrawable);
    }
    
    public void onDraw(android.graphics.Canvas param1Canvas) {
      this.completeCache = null;
      super.onDraw(param1Canvas);
      param1Canvas.drawBitmap(this.bitmap, 0.0F, 0.0F, null);
      Iterator<Sprite> iterator = Canvas.this.sprites.iterator();
      while (iterator.hasNext())
        ((Sprite)iterator.next()).onDraw(param1Canvas); 
      Canvas.access$202(Canvas.this, true);
    }
    
    protected void onMeasure(int param1Int1, int param1Int2) {
      byte b1;
      byte b2;
      if (this.backgroundDrawable != null) {
        Bitmap bitmap = this.backgroundDrawable.getBitmap();
        b2 = bitmap.getWidth();
        b1 = bitmap.getHeight();
      } else {
        b2 = 32;
        b1 = 48;
      } 
      setMeasuredDimension(getSize(param1Int1, b2), getSize(param1Int2, b1));
    }
    
    protected void onSizeChanged(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      param1Int3 = this.bitmap.getWidth();
      param1Int4 = this.bitmap.getHeight();
      if (param1Int1 != param1Int3 || param1Int2 != param1Int4) {
        Bitmap bitmap = this.bitmap;
        try {
          Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, param1Int1, param1Int2, false);
          if (bitmap1.isMutable()) {
            this.bitmap = bitmap1;
            this.canvas = new android.graphics.Canvas(this.bitmap);
          } else {
            this.bitmap = Bitmap.createBitmap(param1Int1, param1Int2, Bitmap.Config.ARGB_8888);
            this.canvas = new android.graphics.Canvas(this.bitmap);
            Rect rect = new Rect(0, 0, param1Int3, param1Int4);
            RectF rectF = new RectF(0.0F, 0.0F, param1Int1, param1Int2);
            this.canvas.drawBitmap(bitmap, rect, rectF, null);
          } 
        } catch (IllegalArgumentException illegalArgumentException) {
          Log.e("Canvas", "Bad values to createScaledBimap w = " + param1Int1 + ", h = " + param1Int2);
        } 
        this.scaledBackgroundBitmap = null;
      } 
    }
    
    public boolean onTouchEvent(MotionEvent param1MotionEvent) {
      Canvas.this.container.$form().dontGrabTouchEventsForComponent();
      Canvas.this.motionEventParser.parse(param1MotionEvent);
      Canvas.this.mGestureDetector.onTouchEvent(param1MotionEvent);
      Iterator<Canvas.ExtensionGestureDetector> iterator = Canvas.this.extensionGestureDetectors.iterator();
      while (iterator.hasNext())
        ((Canvas.ExtensionGestureDetector)iterator.next()).onTouchEvent(param1MotionEvent); 
      return true;
    }
    
    public void setBackgroundColor(int param1Int) {
      Canvas.access$702(Canvas.this, param1Int);
      setBackground();
      clearDrawingLayer();
    }
    
    void setBackgroundImage(String param1String) {
      Canvas canvas = Canvas.this;
      String str = param1String;
      if (param1String == null)
        str = ""; 
      Canvas.access$602(canvas, str);
      this.backgroundDrawable = null;
      this.scaledBackgroundBitmap = null;
      if (!TextUtils.isEmpty(Canvas.this.backgroundImagePath))
        try {
          this.backgroundDrawable = MediaUtil.getBitmapDrawable(Canvas.this.container.$form(), Canvas.this.backgroundImagePath);
        } catch (IOException iOException) {
          Log.e("Canvas", "Unable to load " + Canvas.this.backgroundImagePath);
        }  
      setBackground();
      clearDrawingLayer();
    }
    
    @RequiresApi(api = 8)
    void setBackgroundImageBase64(String param1String) {
      Canvas canvas = Canvas.this;
      String str = param1String;
      if (param1String == null)
        str = ""; 
      Canvas.access$602(canvas, str);
      this.backgroundDrawable = null;
      this.scaledBackgroundBitmap = null;
      if (!TextUtils.isEmpty(Canvas.this.backgroundImagePath)) {
        byte[] arrayOfByte = Base64.decode(Canvas.this.backgroundImagePath, 0);
        this.backgroundDrawable = new BitmapDrawable(BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length));
      } 
      setBackground();
      clearDrawingLayer();
    }
  }
  
  public static interface ExtensionGestureDetector {
    boolean onTouchEvent(MotionEvent param1MotionEvent);
  }
  
  class FlingGestureListener extends GestureDetector.SimpleOnGestureListener {
    public boolean onFling(MotionEvent param1MotionEvent1, MotionEvent param1MotionEvent2, float param1Float1, float param1Float2) {
      float f1 = Math.max(0, (int)(param1MotionEvent1.getX() / Canvas.this.$form().deviceDensity()));
      float f2 = Math.max(0, (int)(param1MotionEvent1.getY() / Canvas.this.$form().deviceDensity()));
      param1Float1 /= 1000.0F;
      param1Float2 /= 1000.0F;
      float f3 = (float)Math.sqrt((param1Float1 * param1Float1 + param1Float2 * param1Float2));
      float f4 = (float)-Math.toDegrees(Math.atan2(param1Float2, param1Float1));
      int i = Canvas.this.Width();
      int j = Canvas.this.Height();
      BoundingBox boundingBox = new BoundingBox(Math.max(0, (int)f1 - 12), Math.max(0, (int)f2 - 12), Math.min(i - 1, (int)f1 + 12), Math.min(j - 1, (int)f2 + 12));
      boolean bool = false;
      for (Sprite sprite : Canvas.this.sprites) {
        if (sprite.Enabled() && sprite.Visible() && sprite.intersectsWith(boundingBox)) {
          sprite.Flung(f1, f2, f3, f4, param1Float1, param1Float2);
          bool = true;
        } 
      } 
      Canvas.this.Flung(f1, f2, f3, f4, param1Float1, param1Float2, bool);
      return true;
    }
  }
  
  class MotionEventParser {
    public static final int FINGER_HEIGHT = 24;
    
    public static final int FINGER_WIDTH = 24;
    
    private static final int HALF_FINGER_HEIGHT = 12;
    
    private static final int HALF_FINGER_WIDTH = 12;
    
    public static final int TAP_THRESHOLD = 15;
    
    private static final int UNSET = -1;
    
    private boolean drag = false;
    
    private final List<Sprite> draggedSprites = new ArrayList<Sprite>();
    
    private boolean isDrag = false;
    
    private float lastX = -1.0F;
    
    private float lastY = -1.0F;
    
    private float startX = -1.0F;
    
    private float startY = -1.0F;
    
    void parse(MotionEvent param1MotionEvent) {
      int i = Canvas.this.Width();
      int j = Canvas.this.Height();
      float f1 = Math.max(0.0F, (int)param1MotionEvent.getX() / Canvas.this.$form().deviceDensity());
      float f2 = Math.max(0.0F, (int)param1MotionEvent.getY() / Canvas.this.$form().deviceDensity());
      BoundingBox boundingBox = new BoundingBox(Math.max(0, (int)f1 - 12), Math.max(0, (int)f2 - 12), Math.min(i - 1, (int)f1 + 12), Math.min(j - 1, (int)f2 + 12));
      switch (param1MotionEvent.getAction()) {
        default:
          return;
        case 0:
          this.draggedSprites.clear();
          this.startX = f1;
          this.startY = f2;
          this.lastX = f1;
          this.lastY = f2;
          this.drag = false;
          this.isDrag = false;
          for (Sprite sprite : Canvas.this.sprites) {
            if (sprite.Enabled() && sprite.Visible() && sprite.intersectsWith(boundingBox)) {
              this.draggedSprites.add(sprite);
              sprite.TouchDown(this.startX, this.startY);
            } 
          } 
          Canvas.this.TouchDown(this.startX, this.startY);
          return;
        case 2:
          if (this.startX == -1.0F || this.startY == -1.0F || this.lastX == -1.0F || this.lastY == -1.0F)
            Log.w("Canvas", "In Canvas.MotionEventParser.parse(), an ACTION_MOVE was passed without a preceding ACTION_DOWN: " + param1MotionEvent); 
          if (this.isDrag || Math.abs(f1 - this.startX) >= 15.0F || Math.abs(f2 - this.startY) >= 15.0F) {
            this.isDrag = true;
            this.drag = true;
            if ((f1 > 0.0F && f1 <= i && f2 > 0.0F && f2 <= j) || Canvas.this.extendMovesOutsideCanvas) {
              for (Sprite sprite : Canvas.this.sprites) {
                if (!this.draggedSprites.contains(sprite) && sprite.Enabled() && sprite.Visible() && sprite.intersectsWith(boundingBox))
                  this.draggedSprites.add(sprite); 
              } 
              boolean bool = false;
              for (Sprite sprite : this.draggedSprites) {
                if (sprite.Enabled() && sprite.Visible()) {
                  sprite.Dragged(this.startX, this.startY, this.lastX, this.lastY, f1, f2);
                  bool = true;
                } 
              } 
              Canvas.this.Dragged(this.startX, this.startY, this.lastX, this.lastY, f1, f2, bool);
              this.lastX = f1;
              this.lastY = f2;
              return;
            } 
          } 
        case 1:
          break;
      } 
      if (!this.drag) {
        boolean bool = false;
        for (Sprite sprite : this.draggedSprites) {
          if (sprite.Enabled() && sprite.Visible()) {
            sprite.Touched(f1, f2);
            sprite.TouchUp(f1, f2);
            bool = true;
          } 
        } 
        Canvas.this.Touched(f1, f2, bool);
      } else {
        Iterator<Sprite> iterator = this.draggedSprites.iterator();
        while (true) {
          if (iterator.hasNext()) {
            Sprite sprite = iterator.next();
            if (sprite.Enabled() && sprite.Visible()) {
              sprite.Touched(f1, f2);
              sprite.TouchUp(f1, f2);
            } 
            continue;
          } 
          Canvas.this.TouchUp(f1, f2);
          this.drag = false;
          this.startX = -1.0F;
          this.startY = -1.0F;
          this.lastX = -1.0F;
          this.lastY = -1.0F;
          return;
        } 
      } 
      Canvas.this.TouchUp(f1, f2);
      this.drag = false;
      this.startX = -1.0F;
      this.startY = -1.0F;
      this.lastX = -1.0F;
      this.lastY = -1.0F;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Canvas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
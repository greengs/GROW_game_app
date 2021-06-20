package com.google.appinventor.components.runtime;

import android.graphics.Canvas;
import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.util.BoundingBox;
import com.google.appinventor.components.runtime.util.TimerInternal;
import java.util.HashSet;
import java.util.Set;

@SimpleObject
public abstract class Sprite extends VisibleComponent implements AlarmHandler, OnDestroyListener, Deleteable {
  private static final boolean DEFAULT_ENABLED = true;
  
  private static final int DEFAULT_HEADING = 0;
  
  private static final int DEFAULT_INTERVAL = 100;
  
  protected static final boolean DEFAULT_ORIGIN_AT_CENTER = false;
  
  private static final float DEFAULT_SPEED = 0.0F;
  
  private static final boolean DEFAULT_VISIBLE = true;
  
  private static final double DEFAULT_Z = 1.0D;
  
  private static final String LOG_TAG = "Sprite";
  
  private final Handler androidUIHandler;
  
  protected final Canvas canvas;
  
  protected Form form;
  
  protected double heading;
  
  protected double headingCos;
  
  protected double headingRadians;
  
  protected double headingSin;
  
  protected boolean initialized = false;
  
  protected int interval;
  
  protected boolean originAtCenter;
  
  private final Set<Sprite> registeredCollisions;
  
  protected float speed;
  
  private final TimerInternal timerInternal;
  
  protected double userHeading;
  
  protected boolean visible = true;
  
  protected double xCenter;
  
  protected double xLeft;
  
  protected double yCenter;
  
  protected double yTop;
  
  protected double zLayer;
  
  protected Sprite(ComponentContainer paramComponentContainer) {
    this(paramComponentContainer, new Handler());
  }
  
  protected Sprite(ComponentContainer paramComponentContainer, Handler paramHandler) {
    this.androidUIHandler = paramHandler;
    if (!(paramComponentContainer instanceof Canvas))
      throw new IllegalArgumentError("Sprite constructor called with container " + paramComponentContainer); 
    this.canvas = (Canvas)paramComponentContainer;
    this.canvas.addSprite(this);
    this.registeredCollisions = new HashSet<Sprite>();
    this.timerInternal = new TimerInternal(this, true, 100, paramHandler);
    this.form = paramComponentContainer.$form();
    OriginAtCenter(false);
    Heading(0.0D);
    Enabled(true);
    Interval(100);
    Speed(0.0F);
    Visible(true);
    Z(1.0D);
    paramComponentContainer.$form().registerForOnDestroy(this);
  }
  
  public static boolean colliding(Sprite paramSprite1, Sprite paramSprite2) {
    BoundingBox boundingBox = paramSprite1.getBoundingBox(1);
    if (boundingBox.intersectDestructively(paramSprite2.getBoundingBox(1))) {
      double d = boundingBox.getLeft();
      while (true) {
        if (d <= boundingBox.getRight()) {
          double d1;
          for (d1 = boundingBox.getTop(); d1 <= boundingBox.getBottom(); d1++) {
            if (paramSprite1.containsPoint(d, d1) && paramSprite2.containsPoint(d, d1))
              return true; 
          } 
          d++;
          continue;
        } 
        return false;
      } 
    } 
    return false;
  }
  
  private final boolean overEastEdge(int paramInt) {
    return (this.xLeft + Width() > paramInt);
  }
  
  private final boolean overNorthEdge() {
    return (this.yTop < 0.0D);
  }
  
  private final boolean overSouthEdge(int paramInt) {
    return (this.yTop + Height() > paramInt);
  }
  
  private final boolean overWestEdge() {
    return (this.xLeft < 0.0D);
  }
  
  private void updateX(double paramDouble) {
    if (this.originAtCenter) {
      this.xCenter = paramDouble;
      this.xLeft = xCenterToLeft(paramDouble);
      return;
    } 
    this.xLeft = paramDouble;
    this.xCenter = xLeftToCenter(paramDouble);
  }
  
  private void updateY(double paramDouble) {
    if (this.originAtCenter) {
      this.yCenter = paramDouble;
      this.yTop = yCenterToTop(paramDouble);
      return;
    } 
    this.yTop = paramDouble;
    this.yCenter = yTopToCenter(paramDouble);
  }
  
  private double xCenterToLeft(double paramDouble) {
    return paramDouble - (Width() / 2);
  }
  
  private double xLeftToCenter(double paramDouble) {
    return (Width() / 2) + paramDouble;
  }
  
  private double yCenterToTop(double paramDouble) {
    return paramDouble - (Width() / 2);
  }
  
  private double yTopToCenter(double paramDouble) {
    return (Width() / 2) + paramDouble;
  }
  
  @SimpleFunction(description = "Makes the %type% bounce, as if off a wall. For normal bouncing, the edge argument should be the one returned by EdgeReached.")
  public void Bounce(int paramInt) {
    MoveIntoBounds();
    double d2 = this.userHeading % 360.0D;
    double d1 = d2;
    if (d2 < 0.0D)
      d1 = d2 + 360.0D; 
    if ((paramInt == 3 && (d1 < 90.0D || d1 > 270.0D)) || (paramInt == -3 && d1 > 90.0D && d1 < 270.0D)) {
      Heading(180.0D - d1);
      return;
    } 
    if ((paramInt == 1 && d1 > 0.0D && d1 < 180.0D) || (paramInt == -1 && d1 > 180.0D)) {
      Heading(360.0D - d1);
      return;
    } 
    if ((paramInt == 2 && d1 > 0.0D && d1 < 90.0D) || (paramInt == -4 && d1 > 90.0D && d1 < 180.0D) || (paramInt == -2 && d1 > 180.0D && d1 < 270.0D) || (paramInt == 4 && d1 > 270.0D)) {
      Heading(180.0D + d1);
      return;
    } 
  }
  
  @SimpleEvent
  public void CollidedWith(Sprite paramSprite) {
    if (!this.registeredCollisions.contains(paramSprite)) {
      this.registeredCollisions.add(paramSprite);
      postEvent(this, "CollidedWith", new Object[] { paramSprite });
    } 
  }
  
  @SimpleFunction(description = "Indicates whether a collision has been registered between this %type% and the passed sprite (Ball or ImageSprite).")
  public boolean CollidingWith(Sprite paramSprite) {
    return this.registeredCollisions.contains(paramSprite);
  }
  
  @SimpleEvent(description = "Event handler called when a %type% is dragged. On all calls, the starting coordinates are where the screen was first touched, and the \"current\" coordinates describe the endpoint of the current line segment. On the first call within a given drag, the \"previous\" coordinates are the same as the starting coordinates; subsequently, they are the \"current\" coordinates from the prior call. Note that the %type% won't actually move anywhere in response to the Dragged event unless MoveTo is explicitly called. For smooth movement, each of its coordinates should be set to the sum of its initial value and the difference between its current and previous values.")
  public void Dragged(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    postEvent(this, "Dragged", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3), Float.valueOf(paramFloat4), Float.valueOf(paramFloat5), Float.valueOf(paramFloat6) });
  }
  
  @SimpleEvent(description = "Event handler called when the %type% reaches an edge of the screen. If Bounce is then called with that edge, the %type% will appear to bounce off of the edge it reached. Edge here is represented as an integer that indicates one of eight directions north (1), northeast (2), east (3), southeast (4), south (-1), southwest (-2), west (-3), and northwest (-4).")
  public void EdgeReached(int paramInt) {
    if (paramInt == 0 || paramInt < -4 || paramInt > 4)
      return; 
    postEvent(this, "EdgeReached", new Object[] { Integer.valueOf(paramInt) });
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean) {
    this.timerInternal.Enabled(paramBoolean);
  }
  
  @SimpleProperty(description = "Controls whether the %type% moves and can be interacted with through collisions, dragging, touching, and flinging.")
  public boolean Enabled() {
    return this.timerInternal.Enabled();
  }
  
  @SimpleEvent(description = "Event handler called when a fling gesture (quick swipe) is made on an enabled %type%. This provides the x and y coordinates of the start of the fling (relative to the upper left of the canvas), the speed (pixels per millisecond), the heading (0-360 degrees), and the x and y velocity components of the fling's vector.")
  public void Flung(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    postEvent(this, "Flung", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3), Float.valueOf(paramFloat4), Float.valueOf(paramFloat5), Float.valueOf(paramFloat6) });
  }
  
  @SimpleProperty(description = "Returns the %type%'s heading in degrees above the positive x-axis.  Zero degrees is toward the right of the screen; 90 degrees is toward the top of the screen.")
  public double Heading() {
    return this.userHeading;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "float")
  @SimpleProperty
  public void Heading(double paramDouble) {
    this.userHeading = paramDouble;
    this.heading = -paramDouble;
    this.headingRadians = Math.toRadians(this.heading);
    this.headingCos = Math.cos(this.headingRadians);
    this.headingSin = Math.sin(this.headingRadians);
    registerChange();
  }
  
  public void Initialize() {
    this.initialized = true;
    this.canvas.registerChange(this);
  }
  
  @SimpleProperty(description = "The interval in milliseconds at which the %type%'s position is updated.  For example, if the interval is 50 and the speed is 10, then every 50 milliseconds the sprite will move 10 pixels in the heading direction.")
  public int Interval() {
    return this.timerInternal.Interval();
  }
  
  @DesignerProperty(defaultValue = "100", editorType = "non_negative_integer")
  @SimpleProperty
  public void Interval(int paramInt) {
    this.timerInternal.Interval(paramInt);
  }
  
  @SimpleFunction(description = "Moves the %type% back in bounds if part of it extends out of bounds, having no effect otherwise. If the %type% is too wide to fit on the canvas, this aligns the left side of the %type% with the left side of the canvas. If the %type% is too tall to fit on the canvas, this aligns the top side of the %type% with the top side of the canvas.")
  public void MoveIntoBounds() {
    moveIntoBounds(this.canvas.Width(), this.canvas.Height());
  }
  
  public void MoveTo(double paramDouble1, double paramDouble2) {
    updateX(paramDouble1);
    updateY(paramDouble2);
    registerChange();
  }
  
  @SimpleEvent(description = "Event handler called when a pair of sprites (Balls and ImageSprites) are no longer colliding.")
  public void NoLongerCollidingWith(Sprite paramSprite) {
    this.registeredCollisions.remove(paramSprite);
    postEvent(this, "NoLongerCollidingWith", new Object[] { paramSprite });
  }
  
  protected void OriginAtCenter(boolean paramBoolean) {
    this.originAtCenter = paramBoolean;
  }
  
  @SimpleFunction(description = "Sets the heading of the %type% toward the point with the coordinates (x, y).")
  public void PointInDirection(double paramDouble1, double paramDouble2) {
    Heading(-Math.toDegrees(Math.atan2(paramDouble2 - this.yCenter, paramDouble1 - this.xCenter)));
  }
  
  @SimpleFunction(description = "Turns the %type% to point towards a designated target sprite (Ball or ImageSprite). The new heading will be parallel to the line joining the centerpoints of the two sprites.")
  public void PointTowards(Sprite paramSprite) {
    Heading(-Math.toDegrees(Math.atan2(paramSprite.yCenter - this.yCenter, paramSprite.xCenter - this.xCenter)));
  }
  
  @SimpleProperty(description = "The speed at which the %type% moves. The %type% moves this many pixels every interval if enabled.")
  public float Speed() {
    return this.speed;
  }
  
  @DesignerProperty(defaultValue = "0.0", editorType = "float")
  @SimpleProperty(description = "The number of pixels that the %type% should move every interval, if enabled.")
  public void Speed(float paramFloat) {
    this.speed = paramFloat;
  }
  
  @SimpleEvent(description = "Event handler called when the user begins touching an enabled %type% (placing their finger on a %type% and leaving it there). This provides the x and y coordinates of the touch, relative to the upper left of the canvas.")
  public void TouchDown(float paramFloat1, float paramFloat2) {
    postEvent(this, "TouchDown", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) });
  }
  
  @SimpleEvent(description = "Event handler called when the user stops touching an enabled %type% (lifting their finger after a TouchDown event). This provides the x and y coordinates of the touch, relative to the upper left of the canvas.")
  public void TouchUp(float paramFloat1, float paramFloat2) {
    postEvent(this, "TouchUp", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) });
  }
  
  @SimpleEvent(description = "Event handler called when the user touches an enabled %type% and then immediately lifts their finger. The provided x and y coordinates are relative to the upper left of the canvas.")
  public void Touched(float paramFloat1, float paramFloat2) {
    postEvent(this, "Touched", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) });
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void Visible(boolean paramBoolean) {
    this.visible = paramBoolean;
    registerChange();
  }
  
  @SimpleProperty(description = "Whether the %type% is visible.")
  public boolean Visible() {
    return this.visible;
  }
  
  public double X() {
    return this.originAtCenter ? this.xCenter : this.xLeft;
  }
  
  @DesignerProperty(defaultValue = "0.0", editorType = "float")
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void X(double paramDouble) {
    updateX(paramDouble);
    registerChange();
  }
  
  public double Y() {
    return this.originAtCenter ? this.yCenter : this.yTop;
  }
  
  @DesignerProperty(defaultValue = "0.0", editorType = "float")
  @SimpleProperty
  public void Y(double paramDouble) {
    updateY(paramDouble);
    registerChange();
  }
  
  @SimpleProperty(description = "How the %type% should be layered relative to other Balls and ImageSprites, with higher-numbered layers in front of lower-numbered layers.")
  public double Z() {
    return this.zLayer;
  }
  
  @DesignerProperty(defaultValue = "1.0", editorType = "float")
  @SimpleProperty
  public void Z(double paramDouble) {
    this.zLayer = paramDouble;
    this.canvas.changeSpriteLayer(this);
  }
  
  public void alarm() {
    if (this.initialized && this.speed != 0.0F) {
      updateCoordinates();
      registerChange();
    } 
  }
  
  public boolean containsPoint(double paramDouble1, double paramDouble2) {
    return (paramDouble1 >= this.xLeft && paramDouble1 < this.xLeft + Width() && paramDouble2 >= this.yTop && paramDouble2 < this.yTop + Height());
  }
  
  public BoundingBox getBoundingBox(int paramInt) {
    return new BoundingBox(this.xLeft - paramInt, this.yTop - paramInt, this.xLeft + Width() - 1.0D + paramInt, this.yTop + Height() - 1.0D + paramInt);
  }
  
  public HandlesEventDispatching getDispatchDelegate() {
    return this.canvas.$form();
  }
  
  protected int hitEdge() {
    return !this.canvas.ready() ? 0 : hitEdge(this.canvas.Width(), this.canvas.Height());
  }
  
  protected int hitEdge(int paramInt1, int paramInt2) {
    boolean bool1 = overWestEdge();
    boolean bool2 = overNorthEdge();
    boolean bool3 = overEastEdge(paramInt1);
    boolean bool4 = overSouthEdge(paramInt2);
    if (bool2 || bool4 || bool3 || bool1) {
      MoveIntoBounds();
      if (bool1)
        return bool2 ? -4 : (bool4 ? -2 : -3); 
      if (bool3)
        return bool2 ? 2 : (bool4 ? 4 : 3); 
      if (bool2)
        return 1; 
      if (bool4)
        return -1; 
    } 
    return 0;
  }
  
  public boolean intersectsWith(BoundingBox paramBoundingBox) {
    BoundingBox boundingBox = getBoundingBox(0);
    if (boundingBox.intersectDestructively(paramBoundingBox)) {
      double d = boundingBox.getLeft();
      while (true) {
        if (d < boundingBox.getRight()) {
          double d1;
          for (d1 = boundingBox.getTop(); d1 < boundingBox.getBottom(); d1++) {
            if (containsPoint(d, d1))
              return true; 
          } 
          d++;
          continue;
        } 
        return false;
      } 
    } 
    return false;
  }
  
  protected final void moveIntoBounds(int paramInt1, int paramInt2) {
    boolean bool = false;
    if (Width() > paramInt1) {
      if (this.xLeft != 0.0D) {
        this.xLeft = 0.0D;
        this.xCenter = xLeftToCenter(this.xLeft);
        bool = true;
      } 
    } else if (overWestEdge()) {
      this.xLeft = 0.0D;
      this.xCenter = xLeftToCenter(this.xLeft);
      bool = true;
    } else if (overEastEdge(paramInt1)) {
      this.xLeft = (paramInt1 - Width());
      this.xCenter = xLeftToCenter(this.xLeft);
      bool = true;
    } 
    if (Height() > paramInt2) {
      if (this.yTop != 0.0D) {
        this.yTop = 0.0D;
        this.yCenter = yTopToCenter(this.yTop);
        bool = true;
      } 
    } else if (overNorthEdge()) {
      this.yTop = 0.0D;
      this.yCenter = yTopToCenter(this.yTop);
      bool = true;
    } else if (overSouthEdge(paramInt2)) {
      this.yTop = (paramInt2 - Height());
      this.yCenter = yTopToCenter(this.yTop);
      bool = true;
    } 
    if (bool)
      registerChange(); 
  }
  
  public void onDelete() {
    this.timerInternal.Enabled(false);
    this.canvas.removeSprite(this);
  }
  
  public void onDestroy() {
    this.timerInternal.Enabled(false);
  }
  
  protected abstract void onDraw(Canvas paramCanvas);
  
  protected void postEvent(final Sprite sprite, final String eventName, Object... args) {
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            EventDispatcher.dispatchEvent(sprite, eventName, args);
          }
        });
  }
  
  protected void registerChange() {
    if (!this.initialized) {
      this.canvas.getView().invalidate();
      return;
    } 
    int i = hitEdge();
    if (i != 0)
      EdgeReached(i); 
    this.canvas.registerChange(this);
  }
  
  protected void updateCoordinates() {
    this.xLeft += this.speed * this.headingCos;
    this.xCenter = xLeftToCenter(this.xLeft);
    this.yTop += this.speed * this.headingSin;
    this.yCenter = yTopToCenter(this.yTop);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Sprite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
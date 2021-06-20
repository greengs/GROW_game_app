package gnu.kawa.slib;

import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.models.Paintable;
import gnu.kawa.models.WithTransform;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.swingviews.SwingDisplay;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongType;
import gnu.math.Complex;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import kawa.lib.numbers;
import kawa.standard.Scheme;

public class swing extends ModuleBody {
  public static final swing $instance;
  
  public static final Location Button;
  
  public static final Location Column;
  
  public static final Location Image;
  
  public static final Location Label;
  
  static final SimpleSymbol Lit0;
  
  static final Keyword Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit13;
  
  static final SimpleSymbol Lit14;
  
  static final SimpleSymbol Lit15;
  
  static final SimpleSymbol Lit16;
  
  static final SimpleSymbol Lit17;
  
  static final SimpleSymbol Lit18;
  
  static final SimpleSymbol Lit19;
  
  static final Keyword Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SimpleSymbol Lit22;
  
  static final SimpleSymbol Lit23;
  
  static final SimpleSymbol Lit24;
  
  static final SimpleSymbol Lit25;
  
  static final SimpleSymbol Lit26 = (SimpleSymbol)(new SimpleSymbol("scroll")).readResolve();
  
  static final Keyword Lit3;
  
  static final Keyword Lit4;
  
  static final Keyword Lit5;
  
  static final Keyword Lit6;
  
  static final Keyword Lit7;
  
  static final Keyword Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final Location Row;
  
  public static final Location Text;
  
  public static final Location Window;
  
  public static final Location button;
  
  public static final Color color$Mnred;
  
  public static final ModuleMethod composite$Mnsrc;
  
  public static final ModuleMethod composite$Mnsrc$Mnover;
  
  public static final ModuleMethod draw;
  
  public static final ModuleMethod fill;
  
  public static final Location image$Mnheight;
  
  public static final Location image$Mnread;
  
  public static final Location image$Mnwidth;
  
  static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtDrawShape$Gr;
  
  static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtFillShape$Gr;
  
  static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtWithPaint$Gr;
  
  static final Location loc$gnu$Dtkawa$Dtmodels$DtWithComposite;
  
  public static final ModuleMethod make$Mnaction$Mnlistener;
  
  public static final ModuleMethod menu;
  
  public static final ModuleMethod menubar;
  
  public static final ModuleMethod menuitem;
  
  public static final ModuleMethod polygon;
  
  public static final ModuleMethod rotation;
  
  public static final Location run$Mnapplication;
  
  public static final ModuleMethod scroll;
  
  public static final Location set$Mncontent;
  
  public static final ModuleMethod with$Mncomposite;
  
  public static final ModuleMethod with$Mnpaint;
  
  public static final ModuleMethod with$Mntransform;
  
  static {
    Lit25 = (SimpleSymbol)(new SimpleSymbol("polygon")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("menuitem")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("menu")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("menubar")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("with-transform")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("rotation")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("composite-src")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("composite-src-over")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("with-composite")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("with-paint")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("draw")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("fill")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("make-action-listener")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("gnu.kawa.models.WithComposite")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.models.WithPaint>")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.models.DrawShape>")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.models.FillShape>")).readResolve();
    Lit8 = Keyword.make("h");
    Lit7 = Keyword.make("w");
    Lit6 = Keyword.make("accesskey");
    Lit5 = Keyword.make("disabled");
    Lit4 = Keyword.make("oncommand");
    Lit3 = Keyword.make("default");
    Lit2 = Keyword.make("image");
    Lit1 = Keyword.make("label");
    Lit0 = (SimpleSymbol)(new SimpleSymbol("make")).readResolve();
    $instance = new swing();
    loc$$Lsgnu$Dtkawa$Dtmodels$DtFillShape$Gr = (Location)ThreadLocation.getInstance((Symbol)Lit9, null);
    loc$$Lsgnu$Dtkawa$Dtmodels$DtDrawShape$Gr = (Location)ThreadLocation.getInstance((Symbol)Lit10, null);
    loc$$Lsgnu$Dtkawa$Dtmodels$DtWithPaint$Gr = (Location)ThreadLocation.getInstance((Symbol)Lit11, null);
    loc$gnu$Dtkawa$Dtmodels$DtWithComposite = (Location)ThreadLocation.getInstance((Symbol)Lit12, null);
    button = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "button");
    Button = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "Button");
    Image = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "Image");
    image$Mnread = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "image$Mnread");
    image$Mnwidth = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "image$Mnwidth");
    image$Mnheight = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "image$Mnheight");
    Label = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "Label");
    Text = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "Text");
    Row = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "Row");
    Column = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "Column");
    set$Mncontent = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "set$Mncontent");
    Window = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "Window");
    run$Mnapplication = (Location)StaticFieldLocation.make("gnu.kawa.slib.gui", "run$Mnapplication");
    swing swing1 = $instance;
    make$Mnaction$Mnlistener = new ModuleMethod(swing1, 1, Lit13, 4097);
    fill = new ModuleMethod(swing1, 2, Lit14, 4097);
    draw = new ModuleMethod(swing1, 3, Lit15, 4097);
    with$Mnpaint = new ModuleMethod(swing1, 4, Lit16, 8194);
    with$Mncomposite = new ModuleMethod(swing1, 5, Lit17, -4096);
    composite$Mnsrc$Mnover = new ModuleMethod(swing1, 6, Lit18, 4096);
    composite$Mnsrc = new ModuleMethod(swing1, 8, Lit19, 4096);
    rotation = new ModuleMethod(swing1, 10, Lit20, 4097);
    with$Mntransform = new ModuleMethod(swing1, 11, Lit21, 8194);
    menubar = new ModuleMethod(swing1, 12, Lit22, -4096);
    menu = new ModuleMethod(swing1, 13, Lit23, -4096);
    menuitem = new ModuleMethod(swing1, 14, Lit24, -4096);
    polygon = new ModuleMethod(swing1, 15, Lit25, -4095);
    scroll = new ModuleMethod(swing1, 16, Lit26, -4095);
    $instance.run();
  }
  
  public swing() {
    ModuleInfo.register(this);
  }
  
  public static Composite compositeSrc() {
    return compositeSrc(1.0F);
  }
  
  public static Composite compositeSrc(float paramFloat) {
    return AlphaComposite.getInstance(AlphaComposite.SRC, paramFloat);
  }
  
  public static Composite compositeSrcOver() {
    return compositeSrcOver(1.0F);
  }
  
  public static Composite compositeSrcOver(float paramFloat) {
    return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, paramFloat);
  }
  
  public static Paintable draw(Shape paramShape) {
    Invoke invoke = Invoke.make;
    Location location = loc$$Lsgnu$Dtkawa$Dtmodels$DtDrawShape$Gr;
    try {
      Object object = location.get();
      return (Paintable)invoke.apply2(object, paramShape);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/swing.scm", 22, 9);
      throw unboundLocationException;
    } 
  }
  
  public static Paintable fill(Shape paramShape) {
    Invoke invoke = Invoke.make;
    Location location = loc$$Lsgnu$Dtkawa$Dtmodels$DtFillShape$Gr;
    try {
      Object object = location.get();
      return (Paintable)invoke.apply2(object, paramShape);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/swing.scm", 19, 9);
      throw unboundLocationException;
    } 
  }
  
  public static ActionListener makeActionListener(Object paramObject) {
    return SwingDisplay.makeActionListener(paramObject);
  }
  
  public static JMenu menu(Object... paramVarArgs) {
    JMenu jMenu = new JMenu();
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++) {
      boolean bool;
      Object object = paramVarArgs[i];
      if (object == Lit1) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool ? (i + 1 < j) : bool) {
        object = paramVarArgs[i + 1];
        if (object == null) {
          object = null;
        } else {
          object = object.toString();
        } 
        jMenu.setText((String)object);
        i += 2;
        continue;
      } 
      jMenu.add((JMenuItem)object);
    } 
    return jMenu;
  }
  
  public static JMenuBar menubar(Object... paramVarArgs) {
    JMenuBar jMenuBar = new JMenuBar();
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++)
      jMenuBar.add((JMenu)paramVarArgs[i]); 
    return jMenuBar;
  }
  
  public static JMenuItem menuitem$V(Object[] paramArrayOfObject) {
    Object object1 = Keyword.searchForKeyword(paramArrayOfObject, 0, Lit1, null);
    if (object1 == null) {
      object1 = null;
    } else {
      object1 = object1.toString();
    } 
    Keyword.searchForKeyword(paramArrayOfObject, 0, Lit2, null);
    Keyword.searchForKeyword(paramArrayOfObject, 0, Lit3, null);
    Object object2 = Keyword.searchForKeyword(paramArrayOfObject, 0, Lit4, null);
    Object object3 = Keyword.searchForKeyword(paramArrayOfObject, 0, Lit5, Boolean.FALSE);
    Keyword.searchForKeyword(paramArrayOfObject, 0, Lit6, null);
    JMenuItem jMenuItem = new JMenuItem();
    if (object3 != Boolean.FALSE)
      jMenuItem.setEnabled(false); 
    if (object1 != null)
      jMenuItem.setText((String)object1); 
    if (object2 != null)
      jMenuItem.addActionListener(makeActionListener(object2)); 
    return jMenuItem;
  }
  
  public static Object polygon(Complex paramComplex, Object... paramVarArgs) {
    GeneralPath generalPath = new GeneralPath();
    int j = paramVarArgs.length;
    generalPath.moveTo(numbers.realPart(paramComplex).doubleValue(), numbers.imagPart(paramComplex).doubleValue());
    int i = 0;
    while (true) {
      if (i < j) {
        Object object = paramVarArgs[i];
        try {
          Complex complex = (Complex)object;
          generalPath.lineTo(numbers.realPart(complex).doubleValue(), numbers.imagPart(complex).doubleValue());
          i++;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "pt", -2, object);
        } 
        continue;
      } 
      generalPath.closePath();
      return generalPath;
    } 
  }
  
  public static AffineTransform rotation(double paramDouble) {
    return AffineTransform.getRotateInstance(paramDouble);
  }
  
  public static JScrollPane scroll$V(Object paramObject, Object[] paramArrayOfObject) {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: getstatic gnu/kawa/slib/swing.Lit7 : Lgnu/expr/Keyword;
    //   5: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   8: invokestatic searchForKeyword : ([Ljava/lang/Object;ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   11: astore #4
    //   13: aload_1
    //   14: iconst_0
    //   15: getstatic gnu/kawa/slib/swing.Lit8 : Lgnu/expr/Keyword;
    //   18: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   21: invokestatic searchForKeyword : ([Ljava/lang/Object;ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   24: astore #5
    //   26: aload_0
    //   27: astore_1
    //   28: aload_0
    //   29: instanceof gnu/kawa/models/Paintable
    //   32: ifeq -> 49
    //   35: aload_0
    //   36: checkcast gnu/kawa/models/Paintable
    //   39: astore_1
    //   40: new gnu/kawa/swingviews/SwingPaintable
    //   43: dup
    //   44: aload_1
    //   45: invokespecial <init> : (Lgnu/kawa/models/Paintable;)V
    //   48: astore_1
    //   49: aload_1
    //   50: checkcast java/awt/Component
    //   53: astore_0
    //   54: new javax/swing/JScrollPane
    //   57: dup
    //   58: aload_0
    //   59: invokespecial <init> : (Ljava/awt/Component;)V
    //   62: astore_0
    //   63: aload #4
    //   65: checkcast java/lang/Number
    //   68: invokevirtual intValue : ()I
    //   71: istore_2
    //   72: aload #5
    //   74: checkcast java/lang/Number
    //   77: invokevirtual intValue : ()I
    //   80: istore_3
    //   81: aload_0
    //   82: new java/awt/Dimension
    //   85: dup
    //   86: iload_2
    //   87: iload_3
    //   88: invokespecial <init> : (II)V
    //   91: invokevirtual setPreferredSize : (Ljava/awt/Dimension;)V
    //   94: aload_0
    //   95: areturn
    //   96: astore_1
    //   97: new gnu/mapping/WrongType
    //   100: dup
    //   101: aload_1
    //   102: ldc_w 'gnu.kawa.swingviews.SwingPaintable.<init>(gnu.kawa.models.Paintable)'
    //   105: iconst_1
    //   106: aload_0
    //   107: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   110: athrow
    //   111: astore_0
    //   112: new gnu/mapping/WrongType
    //   115: dup
    //   116: aload_0
    //   117: ldc_w 'javax.swing.JScrollPane.<init>(java.awt.Component)'
    //   120: iconst_1
    //   121: aload_1
    //   122: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   125: athrow
    //   126: astore_0
    //   127: new gnu/mapping/WrongType
    //   130: dup
    //   131: aload_0
    //   132: ldc_w 'java.awt.Dimension.<init>(int,int)'
    //   135: iconst_1
    //   136: aload #4
    //   138: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   141: athrow
    //   142: astore_0
    //   143: new gnu/mapping/WrongType
    //   146: dup
    //   147: aload_0
    //   148: ldc_w 'java.awt.Dimension.<init>(int,int)'
    //   151: iconst_2
    //   152: aload #5
    //   154: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   157: athrow
    // Exception table:
    //   from	to	target	type
    //   35	40	96	java/lang/ClassCastException
    //   49	54	111	java/lang/ClassCastException
    //   63	72	126	java/lang/ClassCastException
    //   72	81	142	java/lang/ClassCastException
  }
  
  public static Object withComposite(Object... paramVarArgs) {
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    GetNamedPart getNamedPart = GetNamedPart.getNamedPart;
    Location location = loc$gnu$Dtkawa$Dtmodels$DtWithComposite;
    try {
      Object object = location.get();
      return applyToArgs.apply2(getNamedPart.apply2(object, Lit0), paramVarArgs);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/swing.scm", 29, 4);
      throw unboundLocationException;
    } 
  }
  
  public static Object withPaint(Color paramColor, Paintable paramPaintable) {
    Invoke invoke = Invoke.make;
    Location location = loc$$Lsgnu$Dtkawa$Dtmodels$DtWithPaint$Gr;
    try {
      Object object = location.get();
      return invoke.apply3(object, paramPaintable, paramColor);
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/swing.scm", 26, 10);
      throw unboundLocationException;
    } 
  }
  
  public static WithTransform withTransform(AffineTransform paramAffineTransform, Paintable paramPaintable) {
    return new WithTransform(paramPaintable, paramAffineTransform);
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply0(paramModuleMethod);
      case 6:
        return compositeSrcOver();
      case 8:
        break;
    } 
    return compositeSrc();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return makeActionListener(paramObject);
      case 2:
        try {
          Shape shape = (Shape)paramObject;
          return fill(shape);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "fill", 1, paramObject);
        } 
      case 3:
        try {
          Shape shape = (Shape)paramObject;
          return draw(shape);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "draw", 1, paramObject);
        } 
      case 6:
        try {
          float f = ((Number)paramObject).floatValue();
          return compositeSrcOver(f);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "composite-src-over", 1, paramObject);
        } 
      case 8:
        try {
          float f = ((Number)paramObject).floatValue();
          return compositeSrc(f);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "composite-src", 1, paramObject);
        } 
      case 10:
        break;
    } 
    try {
      double d = ((Number)paramObject).doubleValue();
      return rotation(d);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "rotation", 1, paramObject);
    } 
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 4:
        try {
          Color color = (Color)paramObject1;
          try {
            paramObject1 = paramObject2;
            return withPaint(color, (Paintable)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "with-paint", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "with-paint", 1, paramObject1);
        } 
      case 11:
        break;
    } 
    try {
      AffineTransform affineTransform = (AffineTransform)paramObject1;
      try {
        paramObject1 = paramObject2;
        return withTransform(affineTransform, (Paintable)paramObject1);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "with-transform", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "with-transform", 1, paramObject1);
    } 
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 5:
        return withComposite(paramArrayOfObject);
      case 12:
        return menubar(paramArrayOfObject);
      case 13:
        return menu(paramArrayOfObject);
      case 14:
        return menuitem$V(paramArrayOfObject);
      case 15:
        object = paramArrayOfObject[0];
        try {
          Complex complex = (Complex)object;
          int j = paramArrayOfObject.length - 1;
          object = new Object[j];
          while (true) {
            if (--j < 0)
              return polygon(complex, (Object[])object); 
            object[j] = paramArrayOfObject[j + 1];
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "polygon", 1, object);
        } 
      case 16:
        break;
    } 
    Object object = classCastException[0];
    int i = classCastException.length - 1;
    Object[] arrayOfObject = new Object[i];
    while (true) {
      if (--i < 0)
        return scroll$V(object, arrayOfObject); 
      arrayOfObject[i] = classCastException[i + 1];
    } 
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match0(paramModuleMethod, paramCallContext);
      case 8:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 6:
        break;
    } 
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 0;
    return 0;
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 10:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 8:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 6:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 3:
        if (!(paramObject instanceof Shape))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 2:
        if (!(paramObject instanceof Shape))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 1:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 11:
        if (paramObject1 instanceof AffineTransform) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Paintable))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 4:
        break;
    } 
    if (paramObject1 instanceof Color) {
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Paintable))
        return -786430; 
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return SYNTHETIC_LOCAL_VARIABLE_5;
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 16:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 15:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 14:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 13:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 12:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 5:
        break;
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 5;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    color$Mnred = Color.red;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/swing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
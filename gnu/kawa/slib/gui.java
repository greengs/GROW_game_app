package gnu.kawa.slib;

import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.Format;
import gnu.kawa.models.Box;
import gnu.kawa.models.Button;
import gnu.kawa.models.Display;
import gnu.kawa.models.Label;
import gnu.kawa.models.Model;
import gnu.kawa.models.Text;
import gnu.kawa.models.Viewable;
import gnu.kawa.models.Window;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.xml.KAttr;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.misc;
import kawa.standard.Scheme;

public class gui extends ModuleBody {
  public static final gui $instance;
  
  public static final ModuleMethod Button;
  
  public static final ModuleMethod Column;
  
  public static final Macro Image;
  
  public static final ModuleMethod Label;
  
  static final Class Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12;
  
  static final SyntaxRules Lit13;
  
  static final SimpleSymbol Lit14;
  
  static final SimpleSymbol Lit15;
  
  static final SimpleSymbol Lit16;
  
  static final SimpleSymbol Lit17;
  
  static final SimpleSymbol Lit18;
  
  static final SimpleSymbol Lit19;
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SimpleSymbol Lit22;
  
  static final SimpleSymbol Lit23;
  
  static final SyntaxRules Lit24;
  
  static final SimpleSymbol Lit25;
  
  static final SimpleSymbol Lit26;
  
  static final SimpleSymbol Lit27;
  
  static final SimpleSymbol Lit28;
  
  static final SimpleSymbol Lit29;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit30;
  
  static final SimpleSymbol Lit31;
  
  static final SimpleSymbol Lit32;
  
  static final SimpleSymbol Lit33;
  
  static final SimpleSymbol Lit34;
  
  static final SimpleSymbol Lit35;
  
  static final SimpleSymbol Lit36;
  
  static final SimpleSymbol Lit37;
  
  static final SimpleSymbol Lit38;
  
  static final SimpleSymbol Lit39;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit40;
  
  static final SimpleSymbol Lit41;
  
  static final SimpleSymbol Lit42;
  
  static final SimpleSymbol Lit43;
  
  static final IntNum Lit44 = IntNum.make(1);
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SyntaxRules Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod Row;
  
  public static final ModuleMethod Text;
  
  public static final ModuleMethod Window;
  
  public static final ModuleMethod as$Mncolor;
  
  public static final ModuleMethod button;
  
  public static final ModuleMethod image$Mnheight;
  
  public static final ModuleMethod image$Mnread;
  
  public static final ModuleMethod image$Mnwidth;
  
  static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtColumn$Gr;
  
  static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtRow$Gr;
  
  static final Location loc$$St$DtgetHeight;
  
  static final Location loc$$St$DtgetWidth;
  
  public static final Macro process$Mnkeywords;
  
  public static final Macro run$Mnapplication;
  
  public static final ModuleMethod set$Mncontent;
  
  static {
    Lit43 = (SimpleSymbol)(new SimpleSymbol("value")).readResolve();
    Lit42 = (SimpleSymbol)(new SimpleSymbol("name")).readResolve();
    Lit41 = (SimpleSymbol)(new SimpleSymbol("invoke")).readResolve();
    Lit40 = (SimpleSymbol)(new SimpleSymbol("getName")).readResolve();
    Lit39 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
    Lit38 = (SimpleSymbol)(new SimpleSymbol("attr")).readResolve();
    Lit37 = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.xml.KAttr>")).readResolve();
    Lit36 = (SimpleSymbol)(new SimpleSymbol("instance?")).readResolve();
    Lit35 = (SimpleSymbol)(new SimpleSymbol("+")).readResolve();
    Lit34 = (SimpleSymbol)(new SimpleSymbol("loop")).readResolve();
    Lit33 = (SimpleSymbol)(new SimpleSymbol("<object>")).readResolve();
    Lit32 = (SimpleSymbol)(new SimpleSymbol("primitive-array-get")).readResolve();
    Lit31 = (SimpleSymbol)(new SimpleSymbol("quasiquote")).readResolve();
    Lit30 = (SimpleSymbol)(new SimpleSymbol("$lookup$")).readResolve();
    Lit29 = (SimpleSymbol)(new SimpleSymbol("arg")).readResolve();
    Lit28 = (SimpleSymbol)(new SimpleSymbol("num-args")).readResolve();
    Lit27 = (SimpleSymbol)(new SimpleSymbol("i")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("<int>")).readResolve();
    Lit25 = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
    SimpleSymbol simpleSymbol = (SimpleSymbol)(new SimpleSymbol("run-application")).readResolve();
    Lit23 = simpleSymbol;
    SyntaxRule syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\b\003", new Object[] { PairWithPosition.make(Lit30, Pair.make((new SimpleSymbol("gnu.kawa.models.Window")).readResolve(), Pair.make(Pair.make(Lit31, Pair.make((new SimpleSymbol("open")).readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 749575) }0);
    Lit24 = new SyntaxRules(new Object[] { simpleSymbol }, new SyntaxRule[] { syntaxRule }, 1);
    Lit22 = (SimpleSymbol)(new SimpleSymbol("Window")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("set-content")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("Column")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("Row")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("Text")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("Label")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("image-height")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("image-width")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("image-read")).readResolve();
    simpleSymbol = (SimpleSymbol)(new SimpleSymbol("text-field")).readResolve();
    syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\003", new Object[0], 1), "\000", "\021\030\004\021\030\f\002", new Object[] { (new SimpleSymbol("make")).readResolve(), (new SimpleSymbol("<gnu.kawa.models.DrawImage>")).readResolve() }, 0);
    Lit13 = new SyntaxRules(new Object[] { simpleSymbol }, new SyntaxRule[] { syntaxRule }, 1);
    Lit12 = (SimpleSymbol)(new SimpleSymbol("Image")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("Button")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("button")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("as-color")).readResolve();
    simpleSymbol = (SimpleSymbol)(new SimpleSymbol("process-keywords")).readResolve();
    Lit7 = simpleSymbol;
    syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\f\037\b", new Object[0], 4), "\001\001\001\001", "\021\030\004\b\021\030\f\021\030\024\021\030\034\b\021\030$\t\013\030,\b\021\030\004\021\0304\021\030<\b\021\030D\021\030L\b\021\030\004a\b\021\030T\b\021\030\\\t\013\030d\b\021\030l©\021\030ty\t\023\t\003\021\030|\b\021\030\t\013\030\030\021\030i\021\030¤\021\030¬\b\t\023\t\003\030´\030¼\b\021\030Ä1\t\033\t\003\030Ì\030Ô", new Object[] { 
          (new SimpleSymbol("let")).readResolve(), Lit28, Lit25, Lit26, (new SimpleSymbol("field")).readResolve(), PairWithPosition.make(PairWithPosition.make(Lit39, PairWithPosition.make((new SimpleSymbol("length")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 16426), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 16426), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 16425), Lit34, PairWithPosition.make(PairWithPosition.make(Lit27, PairWithPosition.make(Lit25, PairWithPosition.make(Lit26, PairWithPosition.make(IntNum.make(0), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20509), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20503), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20500), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20497), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20496), (new SimpleSymbol("if")).readResolve(), PairWithPosition.make((new SimpleSymbol("<")).readResolve(), PairWithPosition.make(Lit27, PairWithPosition.make(Lit28, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 24593), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 24591), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 24588), 
          Lit29, PairWithPosition.make(Lit32, PairWithPosition.make(Lit33, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 28710), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 28689), PairWithPosition.make(Lit27, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 28725), (new SimpleSymbol("cond")).readResolve(), PairWithPosition.make(Lit36, PairWithPosition.make(Lit29, PairWithPosition.make((new SimpleSymbol("<gnu.expr.Keyword>")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 32797), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 32793), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 32782), PairWithPosition.make(PairWithPosition.make(Lit30, Pair.make((new SimpleSymbol("gnu.expr.Keyword")).readResolve(), Pair.make(Pair.make(Lit31, Pair.make(Lit40, LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 40970), PairWithPosition.make(Lit29, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 40995), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 40969), PairWithPosition.make(Lit32, PairWithPosition.make(Lit33, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45087), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45066), PairWithPosition.make(PairWithPosition.make(Lit35, PairWithPosition.make(Lit27, PairWithPosition.make(Lit44, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45107), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45105), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45102), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45102), PairWithPosition.make(PairWithPosition.make(Lit34, PairWithPosition.make(PairWithPosition.make(Lit35, PairWithPosition.make(Lit27, PairWithPosition.make(IntNum.make(2), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49170), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49168), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49165), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49165), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49159), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49159), PairWithPosition.make(Lit36, PairWithPosition.make(Lit29, PairWithPosition.make(Lit37, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 53270), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 53266), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 53255), 
          (new SimpleSymbol("let*")).readResolve(), PairWithPosition.make(PairWithPosition.make(Lit38, PairWithPosition.make(Lit25, PairWithPosition.make(Lit37, PairWithPosition.make(Lit29, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57388), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57367), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57364), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57358), PairWithPosition.make(PairWithPosition.make(Lit42, PairWithPosition.make(Lit25, PairWithPosition.make((new SimpleSymbol("<java.lang.String>")).readResolve(), PairWithPosition.make(PairWithPosition.make(Lit41, PairWithPosition.make(Lit38, PairWithPosition.make(PairWithPosition.make(Lit39, PairWithPosition.make(Lit40, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61489), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61489), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61488), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61483), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61475), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61475), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61456), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61453), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61447), PairWithPosition.make(PairWithPosition.make(Lit43, PairWithPosition.make(PairWithPosition.make(Lit41, PairWithPosition.make(Lit38, PairWithPosition.make(PairWithPosition.make(Lit39, PairWithPosition.make((new SimpleSymbol("getObjectValue")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65564), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65564), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65563), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65558), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65550), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65550), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65543), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65543), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61447), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57357), PairWithPosition.make(Lit42, PairWithPosition.make(Lit43, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 69666), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 69661), PairWithPosition.make(PairWithPosition.make(Lit34, PairWithPosition.make(PairWithPosition.make(Lit35, PairWithPosition.make(Lit27, PairWithPosition.make(Lit44, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73746), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73744), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73741), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73741), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73735), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73735), (new SimpleSymbol("else")).readResolve(), PairWithPosition.make(Lit29, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 81951), PairWithPosition.make(PairWithPosition.make(Lit34, PairWithPosition.make(PairWithPosition.make(Lit35, PairWithPosition.make(Lit27, PairWithPosition.make(Lit44, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86034), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86032), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86029), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86029), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86023), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86023) }0);
    Lit8 = new SyntaxRules(new Object[] { simpleSymbol }, new SyntaxRule[] { syntaxRule }, 4);
    Lit6 = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.models.Column>")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.models.Row>")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("*.getHeight")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("*.getWidth")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("cell-spacing")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("text")).readResolve();
    Lit0 = Color.class;
    $instance = new gui();
    loc$$St$DtgetWidth = (Location)ThreadLocation.getInstance((Symbol)Lit3, null);
    loc$$St$DtgetHeight = (Location)ThreadLocation.getInstance((Symbol)Lit4, null);
    loc$$Lsgnu$Dtkawa$Dtmodels$DtRow$Gr = (Location)ThreadLocation.getInstance((Symbol)Lit5, null);
    loc$$Lsgnu$Dtkawa$Dtmodels$DtColumn$Gr = (Location)ThreadLocation.getInstance((Symbol)Lit6, null);
    process$Mnkeywords = Macro.make(Lit7, (Procedure)Lit8, $instance);
    gui gui1 = $instance;
    as$Mncolor = new ModuleMethod(gui1, 1, Lit9, 4097);
    button = new ModuleMethod(gui1, 2, Lit10, -4096);
    Button = new ModuleMethod(gui1, 3, Lit11, -4096);
    Image = Macro.make(Lit12, (Procedure)Lit13, $instance);
    image$Mnread = new ModuleMethod(gui1, 4, Lit14, 4097);
    image$Mnwidth = new ModuleMethod(gui1, 5, Lit15, 4097);
    image$Mnheight = new ModuleMethod(gui1, 6, Lit16, 4097);
    Label = new ModuleMethod(gui1, 7, Lit17, -4096);
    Text = new ModuleMethod(gui1, 8, Lit18, -4096);
    Row = new ModuleMethod(gui1, 9, Lit19, -4096);
    Column = new ModuleMethod(gui1, 10, Lit20, -4096);
    set$Mncontent = new ModuleMethod(gui1, 11, Lit21, 8194);
    Window = new ModuleMethod(gui1, 12, Lit22, -4096);
    run$Mnapplication = Macro.make(Lit23, (Procedure)Lit24, $instance);
    $instance.run();
  }
  
  public gui() {
    ModuleInfo.register(this);
  }
  
  public static Button Button(Object... paramVarArgs) {
    Button button = new Button();
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++) {
      Object object = paramVarArgs[i];
      if (object instanceof Keyword) {
        try {
          Keyword keyword = (Keyword)object;
          buttonKeyword(button, keyword.getName(), paramVarArgs[i + 1]);
          i += 2;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, object);
        } 
        continue;
      } 
      if (object instanceof KAttr) {
        try {
          KAttr kAttr = (KAttr)object;
          buttonKeyword(button, kAttr.getName(), kAttr.getObjectValue());
          i++;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "attr", -2, object);
        } 
        continue;
      } 
      buttonNonKeyword(button, object);
    } 
    return button;
  }
  
  public static Object Column(Object... paramVarArgs) {
    Object object = Invoke.make;
    Location location = loc$$Lsgnu$Dtkawa$Dtmodels$DtColumn$Gr;
    try {
      Object object1 = location.get();
      object = object.apply1(object1);
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j) {
        object1 = paramVarArgs[i];
        if (object1 instanceof Keyword)
          try {
            Box box = (Box)object;
            try {
              Keyword keyword = (Keyword)object1;
              boxKeyword(box, keyword.getName(), paramVarArgs[i + 1]);
              i += 2;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, object1);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "box-keyword", 0, object);
          }  
        if (object1 instanceof KAttr)
          try {
            KAttr kAttr = (KAttr)object1;
            object1 = kAttr.getName();
            Object object2 = kAttr.getObjectValue();
            try {
              Box box = (Box)object;
              boxKeyword(box, (String)object1, object2);
              i++;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "box-keyword", 0, object);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "attr", -2, object1);
          }  
        try {
          Box box = (Box)object;
          boxNonKeyword(box, object1);
          i++;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "box-non-keyword", 0, object);
        } 
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 151, 25);
      throw unboundLocationException;
    } 
    return object;
  }
  
  public static Label Label(Object... paramVarArgs) {
    Label label = new Label();
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++) {
      Object object = paramVarArgs[i];
      if (object instanceof Keyword) {
        try {
          Keyword keyword = (Keyword)object;
          labelKeyword(label, keyword.getName(), paramVarArgs[i + 1]);
          i += 2;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, object);
        } 
        continue;
      } 
      if (object instanceof KAttr) {
        try {
          KAttr kAttr = (KAttr)object;
          labelKeyword(label, kAttr.getName(), kAttr.getObjectValue());
          i++;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "attr", -2, object);
        } 
        continue;
      } 
      labelNonKeyword(label, object);
    } 
    return label;
  }
  
  public static Object Row(Object... paramVarArgs) {
    Object object = Invoke.make;
    Location location = loc$$Lsgnu$Dtkawa$Dtmodels$DtRow$Gr;
    try {
      Object object1 = location.get();
      object = object.apply1(object1);
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j) {
        object1 = paramVarArgs[i];
        if (object1 instanceof Keyword)
          try {
            Box box = (Box)object;
            try {
              Keyword keyword = (Keyword)object1;
              boxKeyword(box, keyword.getName(), paramVarArgs[i + 1]);
              i += 2;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, object1);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "box-keyword", 0, object);
          }  
        if (object1 instanceof KAttr)
          try {
            KAttr kAttr = (KAttr)object1;
            object1 = kAttr.getName();
            Object object2 = kAttr.getObjectValue();
            try {
              Box box = (Box)object;
              boxKeyword(box, (String)object1, object2);
              i++;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "box-keyword", 0, object);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "attr", -2, object1);
          }  
        try {
          Box box = (Box)object;
          boxNonKeyword(box, object1);
          i++;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "box-non-keyword", 0, object);
        } 
      } 
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 146, 25);
      throw unboundLocationException;
    } 
    return object;
  }
  
  public static Text Text(Object... paramVarArgs) {
    Text text = new Text();
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++) {
      Object object = paramVarArgs[i];
      if (object instanceof Keyword) {
        try {
          Keyword keyword = (Keyword)object;
          textKeyword(text, keyword.getName(), paramVarArgs[i + 1]);
          i += 2;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, object);
        } 
        continue;
      } 
      if (object instanceof KAttr) {
        try {
          KAttr kAttr = (KAttr)object;
          textKeyword(text, kAttr.getName(), kAttr.getObjectValue());
          i++;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "attr", -2, object);
        } 
        continue;
      } 
      textNonKeyword(text, object);
    } 
    return text;
  }
  
  public static Window Window(Object... paramVarArgs) {
    Window window = Display.getInstance().makeWindow();
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++) {
      Object object = paramVarArgs[i];
      if (object instanceof Keyword) {
        try {
          Keyword keyword = (Keyword)object;
          windowKeyword(window, keyword.getName(), paramVarArgs[i + 1]);
          i += 2;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, object);
        } 
        continue;
      } 
      if (object instanceof KAttr) {
        try {
          KAttr kAttr = (KAttr)object;
          windowKeyword(window, kAttr.getName(), kAttr.getObjectValue());
          i++;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "attr", -2, object);
        } 
        continue;
      } 
      windowNonKeyword(window, object);
    } 
    return window;
  }
  
  public static Color asColor(Object paramObject) {
    if (paramObject instanceof Color)
      return (Color)paramObject; 
    if (paramObject instanceof Integer)
      try {
        Integer integer = (Integer)paramObject;
        return new Color(integer.intValue());
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "java.lang.Integer.intValue()", 1, paramObject);
      }  
    return (paramObject instanceof IntNum) ? new Color(IntNum.intValue(paramObject)) : (Color)SlotGet.staticField.apply2(Lit0, paramObject.toString());
  }
  
  static Model asModel(Object paramObject) {
    return Display.getInstance().coerceToModel(paramObject);
  }
  
  static Object boxKeyword(Box paramBox, String paramString, Object paramObject) {
    if (paramString == Lit2) {
      paramBox.setCellSpacing(paramObject);
      return Values.empty;
    } 
    return misc.error$V(Format.formatToString(0, new Object[] { "unknown box attribute ~s", paramString }), new Object[0]);
  }
  
  static void boxNonKeyword(Box paramBox, Object paramObject) {
    paramBox.add((Viewable)asModel(paramObject));
  }
  
  public static Button button(Object... paramVarArgs) {
    Button button = new Button();
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++) {
      Object object = paramVarArgs[i];
      if (object instanceof Keyword) {
        try {
          Keyword keyword = (Keyword)object;
          buttonKeyword(button, keyword.getName(), paramVarArgs[i + 1]);
          i += 2;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, object);
        } 
        continue;
      } 
      if (object instanceof KAttr) {
        try {
          KAttr kAttr = (KAttr)object;
          buttonKeyword(button, kAttr.getName(), kAttr.getObjectValue());
          i++;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "attr", -2, object);
        } 
        continue;
      } 
      buttonNonKeyword(button, object);
    } 
    return button;
  }
  
  static Object buttonKeyword(Button paramButton, String paramString, Object paramObject) {
    Boolean bool;
    boolean bool1 = true;
    if (paramString == "foreground") {
      paramButton.setForeground(asColor(paramObject));
      return Values.empty;
    } 
    if (paramString == "background") {
      paramButton.setBackground(asColor(paramObject));
      return Values.empty;
    } 
    if (paramString == "action") {
      paramButton.setAction(paramObject);
      return Values.empty;
    } 
    if (paramString == "text") {
      if (paramObject == null) {
        paramString = null;
        paramButton.setText(paramString);
        return Values.empty;
      } 
      paramString = paramObject.toString();
      paramButton.setText(paramString);
      return Values.empty;
    } 
    if (paramString == "disabled")
      try {
        bool = Boolean.FALSE;
        if (paramObject == bool)
          bool1 = false; 
        paramButton.setDisabled(bool1);
        return Values.empty;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "gnu.kawa.models.Button.setDisabled(boolean)", 2, paramObject);
      }  
    return misc.error$V(Format.formatToString(0, new Object[] { "unknown button attribute ~s", bool }), new Object[0]);
  }
  
  static Boolean buttonNonKeyword(Button paramButton, Object paramObject) {
    return Boolean.TRUE;
  }
  
  public static int imageHeight(BufferedImage paramBufferedImage) {
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    Location location = loc$$St$DtgetHeight;
    try {
      Object object = location.get();
      return ((Number)applyToArgs.apply2(object, paramBufferedImage)).intValue();
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 77, 3);
      throw unboundLocationException;
    } 
  }
  
  public static BufferedImage imageRead(Path paramPath) {
    return ImageIO.read(paramPath.openInputStream());
  }
  
  public static int imageWidth(BufferedImage paramBufferedImage) {
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    Location location = loc$$St$DtgetWidth;
    try {
      Object object = location.get();
      return ((Number)applyToArgs.apply2(object, paramBufferedImage)).intValue();
    } catch (UnboundLocationException unboundLocationException) {
      unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 74, 3);
      throw unboundLocationException;
    } 
  }
  
  static Object labelKeyword(Label paramLabel, String paramString, Object paramObject) {
    if (paramString == Lit1) {
      if (paramObject == null) {
        paramString = null;
        paramLabel.setText(paramString);
        return Values.empty;
      } 
      paramString = paramObject.toString();
      paramLabel.setText(paramString);
      return Values.empty;
    } 
    return misc.error$V(Format.formatToString(0, new Object[] { "unknown label attribute ~s", paramString }), new Object[0]);
  }
  
  static void labelNonKeyword(Label paramLabel, Object paramObject) {
    if (paramObject == null) {
      paramObject = null;
    } else {
      paramObject = paramObject.toString();
    } 
    paramLabel.setText((String)paramObject);
  }
  
  public static void setContent(Window paramWindow, Object paramObject) {
    paramWindow.setContent(paramObject);
  }
  
  static Object textKeyword(Text paramText, String paramString, Object paramObject) {
    if (paramString == Lit1) {
      if (paramObject == null) {
        paramString = null;
        paramText.setText(paramString);
        return Values.empty;
      } 
      paramString = paramObject.toString();
      paramText.setText(paramString);
      return Values.empty;
    } 
    return misc.error$V(Format.formatToString(0, new Object[] { "unknown text attribute ~s", paramString }), new Object[0]);
  }
  
  static void textNonKeyword(Text paramText, Object paramObject) {
    if (paramObject == null) {
      paramObject = null;
    } else {
      paramObject = paramObject.toString();
    } 
    paramText.setText((String)paramObject);
  }
  
  static Object windowKeyword(Window paramWindow, String paramString, Object paramObject) {
    if (paramString == "title") {
      if (paramObject == null) {
        paramString = null;
        paramWindow.setTitle(paramString);
        return Values.empty;
      } 
      paramString = paramObject.toString();
      paramWindow.setTitle(paramString);
      return Values.empty;
    } 
    if (paramString == "content") {
      paramWindow.setContent(paramObject);
      return Values.empty;
    } 
    if (paramString == "menubar") {
      paramWindow.setMenuBar(paramObject);
      return Values.empty;
    } 
    return misc.error$V(Format.formatToString(0, new Object[] { "unknown window attribute ~s", paramString }), new Object[0]);
  }
  
  static void windowNonKeyword(Window paramWindow, Object paramObject) {
    paramWindow.setContent(paramObject);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return asColor(paramObject);
      case 4:
        try {
          Path path = Path.valueOf(paramObject);
          return imageRead(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "image-read", 1, paramObject);
        } 
      case 5:
        try {
          BufferedImage bufferedImage = (BufferedImage)paramObject;
          return Integer.valueOf(imageWidth(bufferedImage));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "image-width", 1, paramObject);
        } 
      case 6:
        break;
    } 
    try {
      BufferedImage bufferedImage = (BufferedImage)paramObject;
      return Integer.valueOf(imageHeight(bufferedImage));
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "image-height", 1, paramObject);
    } 
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    if (paramModuleMethod.selector == 11)
      try {
        Window window = (Window)paramObject1;
        setContent(window, paramObject2);
        return Values.empty;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "set-content", 1, paramObject1);
      }  
    return super.apply2((ModuleMethod)classCastException, paramObject1, paramObject2);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 2:
        return button(paramArrayOfObject);
      case 3:
        return Button(paramArrayOfObject);
      case 7:
        return Label(paramArrayOfObject);
      case 8:
        return Text(paramArrayOfObject);
      case 9:
        return Row(paramArrayOfObject);
      case 10:
        return Column(paramArrayOfObject);
      case 12:
        break;
    } 
    return Window(paramArrayOfObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 6:
        if (paramObject instanceof BufferedImage) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 5:
        if (paramObject instanceof BufferedImage) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 4:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 1:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 11) {
      if (!(paramObject1 instanceof Window))
        return -786431; 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 12:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 10:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 9:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 8:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 7:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 3:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 2:
        break;
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 5;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/gui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
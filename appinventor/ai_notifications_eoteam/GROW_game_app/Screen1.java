package appinventor.ai_notifications_eoteam.GROW_game_app;

import android.os.Bundle;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Image;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.RetValManager;
import com.google.appinventor.components.runtime.util.RuntimeErrorAlert;
import com.google.youngandroid.runtime;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Promise;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;

public class Screen1 extends Form implements Runnable {
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final PairWithPosition Lit100;
  
  static final SimpleSymbol Lit101;
  
  static final FString Lit102;
  
  static final SimpleSymbol Lit103;
  
  static final IntNum Lit104;
  
  static final FString Lit105;
  
  static final SimpleSymbol Lit106;
  
  static final SimpleSymbol Lit107;
  
  static final SimpleSymbol Lit108;
  
  static final SimpleSymbol Lit109;
  
  static final IntNum Lit11;
  
  static final SimpleSymbol Lit110;
  
  static final SimpleSymbol Lit111;
  
  static final SimpleSymbol Lit112;
  
  static final SimpleSymbol Lit113;
  
  static final SimpleSymbol Lit114;
  
  static final SimpleSymbol Lit115;
  
  static final SimpleSymbol Lit116;
  
  static final SimpleSymbol Lit117;
  
  static final SimpleSymbol Lit118;
  
  static final SimpleSymbol Lit119 = (SimpleSymbol)(new SimpleSymbol("lookup-handler")).readResolve();
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit13;
  
  static final SimpleSymbol Lit14;
  
  static final SimpleSymbol Lit15;
  
  static final SimpleSymbol Lit16;
  
  static final SimpleSymbol Lit17;
  
  static final FString Lit18;
  
  static final SimpleSymbol Lit19;
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final IntNum Lit21;
  
  static final FString Lit22;
  
  static final FString Lit23;
  
  static final SimpleSymbol Lit24;
  
  static final IntNum Lit25;
  
  static final SimpleSymbol Lit26;
  
  static final SimpleSymbol Lit27;
  
  static final SimpleSymbol Lit28;
  
  static final IntNum Lit29;
  
  static final SimpleSymbol Lit3;
  
  static final FString Lit30;
  
  static final FString Lit31;
  
  static final SimpleSymbol Lit32;
  
  static final IntNum Lit33;
  
  static final FString Lit34;
  
  static final FString Lit35;
  
  static final SimpleSymbol Lit36;
  
  static final SimpleSymbol Lit37;
  
  static final IntNum Lit38;
  
  static final SimpleSymbol Lit39;
  
  static final IntNum Lit4;
  
  static final SimpleSymbol Lit40;
  
  static final IntNum Lit41;
  
  static final FString Lit42;
  
  static final FString Lit43;
  
  static final SimpleSymbol Lit44;
  
  static final IntNum Lit45;
  
  static final FString Lit46;
  
  static final FString Lit47;
  
  static final SimpleSymbol Lit48;
  
  static final IntNum Lit49;
  
  static final SimpleSymbol Lit5;
  
  static final IntNum Lit50;
  
  static final FString Lit51;
  
  static final FString Lit52;
  
  static final SimpleSymbol Lit53;
  
  static final SimpleSymbol Lit54;
  
  static final IntNum Lit55;
  
  static final IntNum Lit56;
  
  static final FString Lit57;
  
  static final FString Lit58;
  
  static final SimpleSymbol Lit59;
  
  static final SimpleSymbol Lit6;
  
  static final IntNum Lit60;
  
  static final FString Lit61;
  
  static final FString Lit62;
  
  static final SimpleSymbol Lit63;
  
  static final IntNum Lit64;
  
  static final FString Lit65;
  
  static final FString Lit66;
  
  static final SimpleSymbol Lit67;
  
  static final IntNum Lit68;
  
  static final FString Lit69;
  
  static final IntNum Lit7;
  
  static final FString Lit70;
  
  static final SimpleSymbol Lit71;
  
  static final IntNum Lit72;
  
  static final FString Lit73;
  
  static final FString Lit74;
  
  static final SimpleSymbol Lit75;
  
  static final IntNum Lit76;
  
  static final FString Lit77;
  
  static final FString Lit78;
  
  static final SimpleSymbol Lit79;
  
  static final SimpleSymbol Lit8;
  
  static final IntNum Lit80;
  
  static final FString Lit81;
  
  static final FString Lit82;
  
  static final SimpleSymbol Lit83;
  
  static final IntNum Lit84;
  
  static final SimpleSymbol Lit85;
  
  static final IntNum Lit86;
  
  static final FString Lit87;
  
  static final PairWithPosition Lit88;
  
  static final SimpleSymbol Lit89;
  
  static final SimpleSymbol Lit9;
  
  static final SimpleSymbol Lit90;
  
  static final FString Lit91;
  
  static final SimpleSymbol Lit92;
  
  static final IntNum Lit93;
  
  static final IntNum Lit94;
  
  static final FString Lit95;
  
  static final FString Lit96;
  
  static final SimpleSymbol Lit97;
  
  static final IntNum Lit98;
  
  static final FString Lit99;
  
  public static Screen1 Screen1;
  
  static final ModuleMethod lambda$Fn1;
  
  static final ModuleMethod lambda$Fn10;
  
  static final ModuleMethod lambda$Fn11;
  
  static final ModuleMethod lambda$Fn12;
  
  static final ModuleMethod lambda$Fn13;
  
  static final ModuleMethod lambda$Fn14;
  
  static final ModuleMethod lambda$Fn15;
  
  static final ModuleMethod lambda$Fn16;
  
  static final ModuleMethod lambda$Fn17;
  
  static final ModuleMethod lambda$Fn18;
  
  static final ModuleMethod lambda$Fn19;
  
  static final ModuleMethod lambda$Fn2;
  
  static final ModuleMethod lambda$Fn20;
  
  static final ModuleMethod lambda$Fn21;
  
  static final ModuleMethod lambda$Fn22;
  
  static final ModuleMethod lambda$Fn23;
  
  static final ModuleMethod lambda$Fn24;
  
  static final ModuleMethod lambda$Fn25;
  
  static final ModuleMethod lambda$Fn26;
  
  static final ModuleMethod lambda$Fn27;
  
  static final ModuleMethod lambda$Fn28;
  
  static final ModuleMethod lambda$Fn29;
  
  static final ModuleMethod lambda$Fn3;
  
  static final ModuleMethod lambda$Fn30;
  
  static final ModuleMethod lambda$Fn31;
  
  static final ModuleMethod lambda$Fn32;
  
  static final ModuleMethod lambda$Fn33;
  
  static final ModuleMethod lambda$Fn34;
  
  static final ModuleMethod lambda$Fn35;
  
  static final ModuleMethod lambda$Fn36;
  
  static final ModuleMethod lambda$Fn4;
  
  static final ModuleMethod lambda$Fn5;
  
  static final ModuleMethod lambda$Fn6;
  
  static final ModuleMethod lambda$Fn7;
  
  static final ModuleMethod lambda$Fn8;
  
  static final ModuleMethod lambda$Fn9;
  
  public Boolean $Stdebug$Mnform$St;
  
  public final ModuleMethod $define;
  
  public Button Button1;
  
  public final ModuleMethod Button1$Click;
  
  public Button Button2;
  
  public final ModuleMethod Button2$Click;
  
  public HorizontalArrangement HorizontalArrangement1;
  
  public Image Image1;
  
  public Label Label1;
  
  public Label Label2;
  
  public Label Label3;
  
  public Label Label4;
  
  public Label Label5;
  
  public Label Label6;
  
  public VerticalArrangement VerticalArrangement1;
  
  public VerticalArrangement VerticalArrangement2;
  
  public VerticalArrangement VerticalArrangement3;
  
  public VerticalArrangement VerticalArrangement4;
  
  public VerticalArrangement VerticalArrangement5;
  
  public VerticalArrangement VerticalArrangement6;
  
  public VerticalArrangement VerticalArrangement7;
  
  public final ModuleMethod add$Mnto$Mncomponents;
  
  public final ModuleMethod add$Mnto$Mnevents;
  
  public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
  
  public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
  
  public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
  
  public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
  
  public final ModuleMethod android$Mnlog$Mnform;
  
  public LList components$Mnto$Mncreate;
  
  public final ModuleMethod dispatchEvent;
  
  public final ModuleMethod dispatchGenericEvent;
  
  public LList events$Mnto$Mnregister;
  
  public LList form$Mndo$Mnafter$Mncreation;
  
  public Environment form$Mnenvironment;
  
  public Symbol form$Mnname$Mnsymbol;
  
  public final ModuleMethod get$Mnsimple$Mnname;
  
  public Environment global$Mnvar$Mnenvironment;
  
  public LList global$Mnvars$Mnto$Mncreate;
  
  public final ModuleMethod is$Mnbound$Mnin$Mnform$Mnenvironment;
  
  public final ModuleMethod lookup$Mnhandler;
  
  public final ModuleMethod lookup$Mnin$Mnform$Mnenvironment;
  
  public final ModuleMethod onCreate;
  
  public final ModuleMethod process$Mnexception;
  
  public final ModuleMethod send$Mnerror;
  
  static {
    Lit118 = (SimpleSymbol)(new SimpleSymbol("dispatchGenericEvent")).readResolve();
    Lit117 = (SimpleSymbol)(new SimpleSymbol("dispatchEvent")).readResolve();
    Lit116 = (SimpleSymbol)(new SimpleSymbol("send-error")).readResolve();
    Lit115 = (SimpleSymbol)(new SimpleSymbol("add-to-form-do-after-creation")).readResolve();
    Lit114 = (SimpleSymbol)(new SimpleSymbol("add-to-global-vars")).readResolve();
    Lit113 = (SimpleSymbol)(new SimpleSymbol("add-to-components")).readResolve();
    Lit112 = (SimpleSymbol)(new SimpleSymbol("add-to-events")).readResolve();
    Lit111 = (SimpleSymbol)(new SimpleSymbol("add-to-global-var-environment")).readResolve();
    Lit110 = (SimpleSymbol)(new SimpleSymbol("is-bound-in-form-environment")).readResolve();
    Lit109 = (SimpleSymbol)(new SimpleSymbol("lookup-in-form-environment")).readResolve();
    Lit108 = (SimpleSymbol)(new SimpleSymbol("add-to-form-environment")).readResolve();
    Lit107 = (SimpleSymbol)(new SimpleSymbol("android-log-form")).readResolve();
    Lit106 = (SimpleSymbol)(new SimpleSymbol("get-simple-name")).readResolve();
    Lit105 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit104 = IntNum.make(-1005);
    Lit103 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement6")).readResolve();
    Lit102 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit101 = (SimpleSymbol)(new SimpleSymbol("Button2$Click")).readResolve();
    SimpleSymbol simpleSymbol = (SimpleSymbol)(new SimpleSymbol("text")).readResolve();
    Lit9 = simpleSymbol;
    Lit100 = PairWithPosition.make(simpleSymbol, LList.Empty, "/tmp/1624147919646_0.8026631161501641-0/youngandroidproject/../src/appinventor/ai_notifications_eoteam/GROW_game_app/Screen1.yail", 794701);
    Lit99 = new FString("com.google.appinventor.components.runtime.Button");
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = -7352248;
    Lit98 = IntNum.make(arrayOfInt);
    Lit97 = (SimpleSymbol)(new SimpleSymbol("Button2")).readResolve();
    Lit96 = new FString("com.google.appinventor.components.runtime.Button");
    Lit95 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit94 = IntNum.make(-1005);
    Lit93 = IntNum.make(-1005);
    Lit92 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement5")).readResolve();
    Lit91 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit90 = (SimpleSymbol)(new SimpleSymbol("Click")).readResolve();
    Lit89 = (SimpleSymbol)(new SimpleSymbol("Button1$Click")).readResolve();
    Lit88 = PairWithPosition.make(Lit9, LList.Empty, "/tmp/1624147919646_0.8026631161501641-0/youngandroidproject/../src/appinventor/ai_notifications_eoteam/GROW_game_app/Screen1.yail", 696398);
    Lit87 = new FString("com.google.appinventor.components.runtime.Button");
    Lit86 = IntNum.make(1);
    Lit85 = (SimpleSymbol)(new SimpleSymbol("Shape")).readResolve();
    arrayOfInt = new int[2];
    arrayOfInt[0] = -7352248;
    Lit84 = IntNum.make(arrayOfInt);
    Lit83 = (SimpleSymbol)(new SimpleSymbol("Button1")).readResolve();
    Lit82 = new FString("com.google.appinventor.components.runtime.Button");
    Lit81 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -15227642;
    Lit80 = IntNum.make(arrayOfInt);
    Lit79 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement1")).readResolve();
    Lit78 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit77 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit76 = IntNum.make(-1008);
    Lit75 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement3")).readResolve();
    Lit74 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit73 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit72 = IntNum.make(arrayOfInt);
    Lit71 = (SimpleSymbol)(new SimpleSymbol("Label6")).readResolve();
    Lit70 = new FString("com.google.appinventor.components.runtime.Label");
    Lit69 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit68 = IntNum.make(arrayOfInt);
    Lit67 = (SimpleSymbol)(new SimpleSymbol("Label5")).readResolve();
    Lit66 = new FString("com.google.appinventor.components.runtime.Label");
    Lit65 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit64 = IntNum.make(arrayOfInt);
    Lit63 = (SimpleSymbol)(new SimpleSymbol("Label4")).readResolve();
    Lit62 = new FString("com.google.appinventor.components.runtime.Label");
    Lit61 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit60 = IntNum.make(arrayOfInt);
    Lit59 = (SimpleSymbol)(new SimpleSymbol("Label3")).readResolve();
    Lit58 = new FString("com.google.appinventor.components.runtime.Label");
    Lit57 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit56 = IntNum.make(arrayOfInt);
    Lit55 = IntNum.make(18);
    Lit54 = (SimpleSymbol)(new SimpleSymbol("FontBold")).readResolve();
    Lit53 = (SimpleSymbol)(new SimpleSymbol("Label2")).readResolve();
    Lit52 = new FString("com.google.appinventor.components.runtime.Label");
    Lit51 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit50 = IntNum.make(-1070);
    arrayOfInt = new int[2];
    arrayOfInt[0] = -7352248;
    Lit49 = IntNum.make(arrayOfInt);
    Lit48 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement1")).readResolve();
    Lit47 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit46 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit45 = IntNum.make(-1005);
    Lit44 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement7")).readResolve();
    Lit43 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit42 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit41 = IntNum.make(arrayOfInt);
    Lit40 = (SimpleSymbol)(new SimpleSymbol("TextColor")).readResolve();
    Lit39 = (SimpleSymbol)(new SimpleSymbol("Text")).readResolve();
    Lit38 = IntNum.make(28);
    Lit37 = (SimpleSymbol)(new SimpleSymbol("FontSize")).readResolve();
    Lit36 = (SimpleSymbol)(new SimpleSymbol("Label1")).readResolve();
    Lit35 = new FString("com.google.appinventor.components.runtime.Label");
    Lit34 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit33 = IntNum.make(-1005);
    Lit32 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement2")).readResolve();
    Lit31 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit30 = new FString("com.google.appinventor.components.runtime.Image");
    Lit29 = IntNum.make(-1085);
    Lit28 = (SimpleSymbol)(new SimpleSymbol("Width")).readResolve();
    Lit27 = (SimpleSymbol)(new SimpleSymbol("ScalePictureToFit")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("Picture")).readResolve();
    Lit25 = IntNum.make(-1030);
    Lit24 = (SimpleSymbol)(new SimpleSymbol("Image1")).readResolve();
    Lit23 = new FString("com.google.appinventor.components.runtime.Image");
    Lit22 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit21 = IntNum.make(-1005);
    Lit20 = (SimpleSymbol)(new SimpleSymbol("Height")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement4")).readResolve();
    Lit18 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit17 = (SimpleSymbol)(new SimpleSymbol("TitleVisible")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("Title")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("Sizing")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("boolean")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("ShowListsAsJson")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("Icon")).readResolve();
    arrayOfInt = new int[2];
    arrayOfInt[0] = -15227642;
    Lit11 = IntNum.make(arrayOfInt);
    Lit10 = (SimpleSymbol)(new SimpleSymbol("BackgroundColor")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("AppName")).readResolve();
    Lit7 = IntNum.make(2);
    Lit6 = (SimpleSymbol)(new SimpleSymbol("AlignVertical")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("number")).readResolve();
    Lit4 = IntNum.make(3);
    Lit3 = (SimpleSymbol)(new SimpleSymbol("AlignHorizontal")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("*the-null-value*")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("getMessage")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("Screen1")).readResolve();
  }
  
  public Screen1() {
    ModuleInfo.register(this);
    Screen1$frame screen1$frame = new Screen1$frame();
    screen1$frame.$main = this;
    this.get$Mnsimple$Mnname = new ModuleMethod(screen1$frame, 1, Lit106, 4097);
    this.onCreate = new ModuleMethod(screen1$frame, 2, "onCreate", 4097);
    this.android$Mnlog$Mnform = new ModuleMethod(screen1$frame, 3, Lit107, 4097);
    this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(screen1$frame, 4, Lit108, 8194);
    this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(screen1$frame, 5, Lit109, 8193);
    this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(screen1$frame, 7, Lit110, 4097);
    this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(screen1$frame, 8, Lit111, 8194);
    this.add$Mnto$Mnevents = new ModuleMethod(screen1$frame, 9, Lit112, 8194);
    this.add$Mnto$Mncomponents = new ModuleMethod(screen1$frame, 10, Lit113, 16388);
    this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(screen1$frame, 11, Lit114, 8194);
    this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(screen1$frame, 12, Lit115, 4097);
    this.send$Mnerror = new ModuleMethod(screen1$frame, 13, Lit116, 4097);
    this.process$Mnexception = new ModuleMethod(screen1$frame, 14, "process-exception", 4097);
    this.dispatchEvent = new ModuleMethod(screen1$frame, 15, Lit117, 16388);
    this.dispatchGenericEvent = new ModuleMethod(screen1$frame, 16, Lit118, 16388);
    this.lookup$Mnhandler = new ModuleMethod(screen1$frame, 17, Lit119, 8194);
    ModuleMethod moduleMethod = new ModuleMethod(screen1$frame, 18, null, 0);
    moduleMethod.setProperty("source-location", "/tmp/runtime6993135000179593734.scm:622");
    lambda$Fn1 = moduleMethod;
    this.$define = new ModuleMethod(screen1$frame, 19, "$define", 0);
    lambda$Fn2 = new ModuleMethod(screen1$frame, 20, null, 0);
    lambda$Fn3 = new ModuleMethod(screen1$frame, 21, null, 0);
    lambda$Fn4 = new ModuleMethod(screen1$frame, 22, null, 0);
    lambda$Fn5 = new ModuleMethod(screen1$frame, 23, null, 0);
    lambda$Fn6 = new ModuleMethod(screen1$frame, 24, null, 0);
    lambda$Fn7 = new ModuleMethod(screen1$frame, 25, null, 0);
    lambda$Fn8 = new ModuleMethod(screen1$frame, 26, null, 0);
    lambda$Fn9 = new ModuleMethod(screen1$frame, 27, null, 0);
    lambda$Fn10 = new ModuleMethod(screen1$frame, 28, null, 0);
    lambda$Fn11 = new ModuleMethod(screen1$frame, 29, null, 0);
    lambda$Fn12 = new ModuleMethod(screen1$frame, 30, null, 0);
    lambda$Fn13 = new ModuleMethod(screen1$frame, 31, null, 0);
    lambda$Fn14 = new ModuleMethod(screen1$frame, 32, null, 0);
    lambda$Fn15 = new ModuleMethod(screen1$frame, 33, null, 0);
    lambda$Fn16 = new ModuleMethod(screen1$frame, 34, null, 0);
    lambda$Fn17 = new ModuleMethod(screen1$frame, 35, null, 0);
    lambda$Fn18 = new ModuleMethod(screen1$frame, 36, null, 0);
    lambda$Fn19 = new ModuleMethod(screen1$frame, 37, null, 0);
    lambda$Fn20 = new ModuleMethod(screen1$frame, 38, null, 0);
    lambda$Fn21 = new ModuleMethod(screen1$frame, 39, null, 0);
    lambda$Fn22 = new ModuleMethod(screen1$frame, 40, null, 0);
    lambda$Fn23 = new ModuleMethod(screen1$frame, 41, null, 0);
    lambda$Fn24 = new ModuleMethod(screen1$frame, 42, null, 0);
    lambda$Fn25 = new ModuleMethod(screen1$frame, 43, null, 0);
    lambda$Fn26 = new ModuleMethod(screen1$frame, 44, null, 0);
    lambda$Fn27 = new ModuleMethod(screen1$frame, 45, null, 0);
    lambda$Fn28 = new ModuleMethod(screen1$frame, 46, null, 0);
    lambda$Fn29 = new ModuleMethod(screen1$frame, 47, null, 0);
    lambda$Fn30 = new ModuleMethod(screen1$frame, 48, null, 0);
    this.Button1$Click = new ModuleMethod(screen1$frame, 49, Lit89, 0);
    lambda$Fn31 = new ModuleMethod(screen1$frame, 50, null, 0);
    lambda$Fn32 = new ModuleMethod(screen1$frame, 51, null, 0);
    lambda$Fn33 = new ModuleMethod(screen1$frame, 52, null, 0);
    lambda$Fn34 = new ModuleMethod(screen1$frame, 53, null, 0);
    this.Button2$Click = new ModuleMethod(screen1$frame, 54, Lit101, 0);
    lambda$Fn35 = new ModuleMethod(screen1$frame, 55, null, 0);
    lambda$Fn36 = new ModuleMethod(screen1$frame, 56, null, 0);
  }
  
  static Object lambda10() {
    runtime.setAndCoerceProperty$Ex(Lit36, Lit37, Lit38, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit36, Lit39, "GROW", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit36, Lit40, Lit41, Lit5);
  }
  
  static Object lambda11() {
    runtime.setAndCoerceProperty$Ex(Lit36, Lit37, Lit38, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit36, Lit39, "GROW", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit36, Lit40, Lit41, Lit5);
  }
  
  static Object lambda12() {
    return runtime.setAndCoerceProperty$Ex(Lit44, Lit20, Lit45, Lit5);
  }
  
  static Object lambda13() {
    return runtime.setAndCoerceProperty$Ex(Lit44, Lit20, Lit45, Lit5);
  }
  
  static Object lambda14() {
    runtime.setAndCoerceProperty$Ex(Lit48, Lit3, Lit4, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit48, Lit6, Lit7, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit48, Lit10, Lit49, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit48, Lit28, Lit50, Lit5);
  }
  
  static Object lambda15() {
    runtime.setAndCoerceProperty$Ex(Lit48, Lit3, Lit4, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit48, Lit6, Lit7, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit48, Lit10, Lit49, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit48, Lit28, Lit50, Lit5);
  }
  
  static Object lambda16() {
    runtime.setAndCoerceProperty$Ex(Lit53, Lit54, Boolean.TRUE, Lit14);
    runtime.setAndCoerceProperty$Ex(Lit53, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit53, Lit39, "Green Rescue for", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit53, Lit40, Lit56, Lit5);
  }
  
  static Object lambda17() {
    runtime.setAndCoerceProperty$Ex(Lit53, Lit54, Boolean.TRUE, Lit14);
    runtime.setAndCoerceProperty$Ex(Lit53, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit53, Lit39, "Green Rescue for", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit53, Lit40, Lit56, Lit5);
  }
  
  static Object lambda18() {
    runtime.setAndCoerceProperty$Ex(Lit59, Lit54, Boolean.TRUE, Lit14);
    runtime.setAndCoerceProperty$Ex(Lit59, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit59, Lit39, "urban", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit59, Lit40, Lit60, Lit5);
  }
  
  static Object lambda19() {
    runtime.setAndCoerceProperty$Ex(Lit59, Lit54, Boolean.TRUE, Lit14);
    runtime.setAndCoerceProperty$Ex(Lit59, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit59, Lit39, "urban", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit59, Lit40, Lit60, Lit5);
  }
  
  public static SimpleSymbol lambda1symbolAppend$V(Object[] paramArrayOfObject) {
    LList lList2 = LList.makeList(paramArrayOfObject, 0);
    Apply apply = Scheme.apply;
    ModuleMethod moduleMethod = strings.string$Mnappend;
    LList lList1 = LList.Empty;
    while (true) {
      Object object1;
      Object object2;
      if (lList2 == LList.Empty) {
        object1 = apply.apply2(moduleMethod, LList.reverseInPlace(lList1));
        try {
          CharSequence charSequence = (CharSequence)object1;
          return misc.string$To$Symbol(charSequence);
        } catch (ClassCastException null) {
          throw new WrongType(object2, "string->symbol", 1, object1);
        } 
      } 
      try {
        Pair pair = (Pair)object2;
        object2 = pair.getCdr();
        Object object = pair.getCar();
        try {
          Symbol symbol = (Symbol)object;
          object1 = Pair.make(misc.symbol$To$String(symbol), object1);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "symbol->string", 1, object);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, object2);
      } 
    } 
  }
  
  static Object lambda2() {
    return null;
  }
  
  static Object lambda20() {
    runtime.setAndCoerceProperty$Ex(Lit63, Lit54, Boolean.TRUE, Lit14);
    runtime.setAndCoerceProperty$Ex(Lit63, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit63, Lit39, "envirOnmental", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit63, Lit40, Lit64, Lit5);
  }
  
  static Object lambda21() {
    runtime.setAndCoerceProperty$Ex(Lit63, Lit54, Boolean.TRUE, Lit14);
    runtime.setAndCoerceProperty$Ex(Lit63, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit63, Lit39, "envirOnmental", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit63, Lit40, Lit64, Lit5);
  }
  
  static Object lambda22() {
    runtime.setAndCoerceProperty$Ex(Lit67, Lit54, Boolean.TRUE, Lit14);
    runtime.setAndCoerceProperty$Ex(Lit67, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit67, Lit39, "Wealth", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit67, Lit40, Lit68, Lit5);
  }
  
  static Object lambda23() {
    runtime.setAndCoerceProperty$Ex(Lit67, Lit54, Boolean.TRUE, Lit14);
    runtime.setAndCoerceProperty$Ex(Lit67, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit67, Lit39, "Wealth", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit67, Lit40, Lit68, Lit5);
  }
  
  static Object lambda24() {
    runtime.setAndCoerceProperty$Ex(Lit71, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit71, Lit39, "\"Change the world together\"", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit71, Lit40, Lit72, Lit5);
  }
  
  static Object lambda25() {
    runtime.setAndCoerceProperty$Ex(Lit71, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit71, Lit39, "\"Change the world together\"", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit71, Lit40, Lit72, Lit5);
  }
  
  static Object lambda26() {
    return runtime.setAndCoerceProperty$Ex(Lit75, Lit20, Lit76, Lit5);
  }
  
  static Object lambda27() {
    return runtime.setAndCoerceProperty$Ex(Lit75, Lit20, Lit76, Lit5);
  }
  
  static Object lambda28() {
    return runtime.setAndCoerceProperty$Ex(Lit79, Lit10, Lit80, Lit5);
  }
  
  static Object lambda29() {
    return runtime.setAndCoerceProperty$Ex(Lit79, Lit10, Lit80, Lit5);
  }
  
  static Object lambda3() {
    runtime.setAndCoerceProperty$Ex(Lit0, Lit3, Lit4, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit6, Lit7, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit8, "GROW_game_app", Lit9);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit10, Lit11, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit12, "start.jpg", Lit9);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit13, Boolean.TRUE, Lit14);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit15, "Responsive", Lit9);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit16, "Screen1", Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit0, Lit17, Boolean.FALSE, Lit14);
  }
  
  static Object lambda30() {
    runtime.setAndCoerceProperty$Ex(Lit83, Lit10, Lit84, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit83, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit83, Lit85, Lit86, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit83, Lit39, "Sign up", Lit9);
  }
  
  static Object lambda31() {
    runtime.setAndCoerceProperty$Ex(Lit83, Lit10, Lit84, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit83, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit83, Lit85, Lit86, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit83, Lit39, "Sign up", Lit9);
  }
  
  static Object lambda32() {
    runtime.setAndCoerceProperty$Ex(Lit92, Lit20, Lit93, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit92, Lit28, Lit94, Lit5);
  }
  
  static Object lambda33() {
    runtime.setAndCoerceProperty$Ex(Lit92, Lit20, Lit93, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit92, Lit28, Lit94, Lit5);
  }
  
  static Object lambda34() {
    runtime.setAndCoerceProperty$Ex(Lit97, Lit10, Lit98, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit97, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit97, Lit85, Lit86, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit97, Lit39, "Log in", Lit9);
  }
  
  static Object lambda35() {
    runtime.setAndCoerceProperty$Ex(Lit97, Lit10, Lit98, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit97, Lit37, Lit55, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit97, Lit85, Lit86, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit97, Lit39, "Log in", Lit9);
  }
  
  static Object lambda36() {
    return runtime.setAndCoerceProperty$Ex(Lit103, Lit20, Lit104, Lit5);
  }
  
  static Object lambda37() {
    return runtime.setAndCoerceProperty$Ex(Lit103, Lit20, Lit104, Lit5);
  }
  
  static Object lambda4() {
    return runtime.setAndCoerceProperty$Ex(Lit19, Lit20, Lit21, Lit5);
  }
  
  static Object lambda5() {
    return runtime.setAndCoerceProperty$Ex(Lit19, Lit20, Lit21, Lit5);
  }
  
  static Object lambda6() {
    runtime.setAndCoerceProperty$Ex(Lit24, Lit20, Lit25, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit24, Lit26, "start.jpg", Lit9);
    runtime.setAndCoerceProperty$Ex(Lit24, Lit27, Boolean.TRUE, Lit14);
    return runtime.setAndCoerceProperty$Ex(Lit24, Lit28, Lit29, Lit5);
  }
  
  static Object lambda7() {
    runtime.setAndCoerceProperty$Ex(Lit24, Lit20, Lit25, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit24, Lit26, "start.jpg", Lit9);
    runtime.setAndCoerceProperty$Ex(Lit24, Lit27, Boolean.TRUE, Lit14);
    return runtime.setAndCoerceProperty$Ex(Lit24, Lit28, Lit29, Lit5);
  }
  
  static Object lambda8() {
    return runtime.setAndCoerceProperty$Ex(Lit32, Lit20, Lit33, Lit5);
  }
  
  static Object lambda9() {
    return runtime.setAndCoerceProperty$Ex(Lit32, Lit20, Lit33, Lit5);
  }
  
  public void $define() {
    Language.setDefaults((Language)Scheme.getInstance());
    try {
      run();
    } catch (Exception exception) {
      androidLogForm(exception.getMessage());
      processException(exception);
    } 
    Screen1 = this;
    addToFormEnvironment((Symbol)Lit0, this);
    LList lList = this.events$Mnto$Mnregister;
    while (true) {
      if (lList == LList.Empty)
        try {
          lList = lists.reverse(this.components$Mnto$Mncreate);
          addToGlobalVars(Lit2, lambda$Fn1);
          LList lList1 = lists.reverse(this.form$Mndo$Mnafter$Mncreation);
          while (true) {
            Object object1;
            if (lList1 == LList.Empty) {
              lList1 = lList;
              while (true) {
                Object object2;
                if (lList1 == LList.Empty) {
                  lList1 = lists.reverse(this.global$Mnvars$Mnto$Mncreate);
                  while (true) {
                    if (lList1 == LList.Empty) {
                      lList1 = lList;
                      while (true) {
                        if (lList1 == LList.Empty)
                          while (true) {
                            lList1 = LList.Empty;
                            if (lList == lList1)
                              return; 
                            try {
                              Pair pair = (Pair)lList;
                              object = pair.getCar();
                              Object object3 = lists.caddr.apply1(object);
                              lists.cadddr.apply1(object);
                              callInitialize(SlotGet.field.apply2(this, object3));
                              object = pair.getCdr();
                            } catch (ClassCastException null) {
                              throw new WrongType(object1, "arg0", -2, object);
                            } 
                          }  
                        try {
                          Pair pair = (Pair)object1;
                          object1 = pair.getCar();
                          lists.caddr.apply1(object1);
                          object1 = lists.cadddr.apply1(object1);
                          if (object1 != Boolean.FALSE)
                            Scheme.applyToArgs.apply1(object1); 
                          object1 = pair.getCdr();
                        } catch (ClassCastException null) {
                          throw new WrongType(object, "arg0", -2, object1);
                        } 
                      } 
                      break;
                    } 
                    try {
                      Pair pair = (Pair)object1;
                      Object object3 = pair.getCar();
                      object1 = lists.car.apply1(object3);
                      object3 = lists.cadr.apply1(object3);
                      try {
                        object2 = object1;
                        addToGlobalVarEnvironment((Symbol)object2, Scheme.applyToArgs.apply1(object3));
                        object1 = pair.getCdr();
                      } catch (ClassCastException null) {}
                    } catch (ClassCastException null) {
                      throw new WrongType(object, "arg0", -2, object1);
                    } 
                    throw new WrongType(object, "add-to-global-var-environment", 0, object1);
                  } 
                  break;
                } 
                try {
                  Pair pair = (Pair)object1;
                  object2 = pair.getCar();
                  object1 = lists.caddr.apply1(object2);
                  lists.cadddr.apply1(object2);
                  Object object3 = lists.cadr.apply1(object2);
                  object2 = lists.car.apply1(object2);
                  try {
                    Symbol symbol = (Symbol)object2;
                    object2 = lookupInFormEnvironment(symbol);
                    object3 = Invoke.make.apply2(object3, object2);
                    SlotSet.set$Mnfield$Ex.apply3(this, object1, object3);
                    try {
                      object2 = object1;
                      addToFormEnvironment((Symbol)object2, object3);
                      object1 = pair.getCdr();
                    } catch (ClassCastException null) {}
                  } catch (ClassCastException null) {}
                } catch (ClassCastException null) {
                  throw new WrongType(object, "arg0", -2, object1);
                } 
                throw new WrongType(object, "lookup-in-form-environment", 0, object2);
              } 
              break;
            } 
            try {
              Pair pair = (Pair)object1;
              misc.force(pair.getCar());
              object1 = pair.getCdr();
            } catch (ClassCastException null) {
              throw new WrongType(object, "arg0", -2, object1);
            } 
          } 
          continue;
        } catch (YailRuntimeError object) {
          processException(object);
          return;
        }  
      try {
        Pair pair = (Pair)object;
        Object object1 = pair.getCar();
        object = lists.car.apply1(object1);
        if (object == null) {
          object = null;
        } else {
          object = object.toString();
        } 
        object1 = lists.cdr.apply1(object1);
        if (object1 == null) {
          object1 = null;
        } else {
          object1 = object1.toString();
        } 
        EventDispatcher.registerEventForDelegation((HandlesEventDispatching)this, (String)object, (String)object1);
        object = pair.getCdr();
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, object);
      } 
    } 
  }
  
  public Object Button1$Click() {
    runtime.setThisForm();
    return runtime.callYailPrimitive(runtime.open$Mnanother$Mnscreen, LList.list1("Sign_up"), Lit88, "open another screen");
  }
  
  public Object Button2$Click() {
    runtime.setThisForm();
    return runtime.callYailPrimitive(runtime.open$Mnanother$Mnscreen, LList.list1("Log_in"), Lit100, "open another screen");
  }
  
  public void addToComponents(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    this.components$Mnto$Mncreate = (LList)lists.cons(LList.list4(paramObject1, paramObject2, paramObject3, paramObject4), this.components$Mnto$Mncreate);
  }
  
  public void addToEvents(Object paramObject1, Object paramObject2) {
    this.events$Mnto$Mnregister = (LList)lists.cons(lists.cons(paramObject1, paramObject2), this.events$Mnto$Mnregister);
  }
  
  public void addToFormDoAfterCreation(Object paramObject) {
    this.form$Mndo$Mnafter$Mncreation = (LList)lists.cons(paramObject, this.form$Mndo$Mnafter$Mncreation);
  }
  
  public void addToFormEnvironment(Symbol paramSymbol, Object paramObject) {
    androidLogForm(Format.formatToString(0, new Object[] { "Adding ~A to env ~A with value ~A", paramSymbol, this.form$Mnenvironment, paramObject }));
    this.form$Mnenvironment.put(paramSymbol, paramObject);
  }
  
  public void addToGlobalVarEnvironment(Symbol paramSymbol, Object paramObject) {
    androidLogForm(Format.formatToString(0, new Object[] { "Adding ~A to env ~A with value ~A", paramSymbol, this.global$Mnvar$Mnenvironment, paramObject }));
    this.global$Mnvar$Mnenvironment.put(paramSymbol, paramObject);
  }
  
  public void addToGlobalVars(Object paramObject1, Object paramObject2) {
    this.global$Mnvars$Mnto$Mncreate = (LList)lists.cons(LList.list2(paramObject1, paramObject2), this.global$Mnvars$Mnto$Mncreate);
  }
  
  public void androidLogForm(Object paramObject) {}
  
  public boolean dispatchEvent(Component paramComponent, String paramString1, String paramString2, Object[] paramArrayOfObject) {
    SimpleSymbol simpleSymbol = misc.string$To$Symbol(paramString1);
    if (isBoundInFormEnvironment((Symbol)simpleSymbol)) {
      if (lookupInFormEnvironment((Symbol)simpleSymbol) == paramComponent) {
        object = lookupHandler(paramString1, paramString2);
        try {
          Scheme.apply.apply2(object, LList.makeList(paramArrayOfObject, 0));
          return true;
        } catch (PermissionException object) {
          boolean bool;
          object.printStackTrace();
          if (this == paramComponent) {
            bool = true;
          } else {
            bool = false;
          } 
          if (bool ? IsEqual.apply(paramString2, "PermissionNeeded") : bool) {
            processException(object);
            return false;
          } 
          PermissionDenied(paramComponent, paramString2, object.getPermissionNeeded());
          return false;
        } catch (Throwable throwable) {
          androidLogForm(throwable.getMessage());
          throwable.printStackTrace();
          processException(throwable);
          return false;
        } 
      } 
      return false;
    } 
    EventDispatcher.unregisterEventForDelegation((HandlesEventDispatching)this, (String)object, paramString2);
    return false;
  }
  
  public void dispatchGenericEvent(Component paramComponent, String paramString, boolean paramBoolean, Object[] paramArrayOfObject) {
    boolean bool = true;
    Object object = lookupInFormEnvironment((Symbol)misc.string$To$Symbol((CharSequence)strings.stringAppend(new Object[] { "any$", getSimpleName(paramComponent), "$", paramString })));
    if (object != Boolean.FALSE)
      try {
        Boolean bool1;
        Apply apply = Scheme.apply;
        if (paramBoolean) {
          bool1 = Boolean.TRUE;
        } else {
          bool1 = Boolean.FALSE;
        } 
        apply.apply2(object, lists.cons(paramComponent, lists.cons(bool1, LList.makeList(paramArrayOfObject, 0))));
        return;
      } catch (PermissionException permissionException) {
        permissionException.printStackTrace();
        if (this != paramComponent)
          bool = false; 
        if (bool ? IsEqual.apply(paramString, "PermissionNeeded") : bool) {
          processException(permissionException);
          return;
        } 
        PermissionDenied(paramComponent, paramString, permissionException.getPermissionNeeded());
        return;
      } catch (Throwable throwable) {
        androidLogForm(throwable.getMessage());
        throwable.printStackTrace();
        processException(throwable);
      }  
  }
  
  public String getSimpleName(Object paramObject) {
    return paramObject.getClass().getSimpleName();
  }
  
  public boolean isBoundInFormEnvironment(Symbol paramSymbol) {
    return this.form$Mnenvironment.isBound(paramSymbol);
  }
  
  public Object lookupHandler(Object paramObject1, Object paramObject2) {
    Object object = null;
    if (paramObject1 == null) {
      paramObject1 = null;
    } else {
      paramObject1 = paramObject1.toString();
    } 
    if (paramObject2 == null) {
      paramObject2 = object;
      return lookupInFormEnvironment((Symbol)misc.string$To$Symbol(EventDispatcher.makeFullEventName((String)paramObject1, (String)paramObject2)));
    } 
    paramObject2 = paramObject2.toString();
    return lookupInFormEnvironment((Symbol)misc.string$To$Symbol(EventDispatcher.makeFullEventName((String)paramObject1, (String)paramObject2)));
  }
  
  public Object lookupInFormEnvironment(Symbol paramSymbol) {
    return lookupInFormEnvironment(paramSymbol, Boolean.FALSE);
  }
  
  public Object lookupInFormEnvironment(Symbol paramSymbol, Object paramObject) {
    if (this.form$Mnenvironment == null) {
      i = 1;
    } else {
      i = 0;
    } 
    int i = i + 1 & 0x1;
    if ((i != 0) ? this.form$Mnenvironment.isBound(paramSymbol) : (i != 0))
      paramObject = this.form$Mnenvironment.get(paramSymbol); 
    return paramObject;
  }
  
  public void onCreate(Bundle paramBundle) {
    AppInventorCompatActivity.setClassicModeFromYail(true);
    super.onCreate(paramBundle);
  }
  
  public void processException(Object paramObject) {
    Object object = Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(paramObject, Lit1));
    if (object == null) {
      object = null;
    } else {
      object = object.toString();
    } 
    if (paramObject instanceof YailRuntimeError) {
      paramObject = ((YailRuntimeError)paramObject).getErrorType();
    } else {
      paramObject = "Runtime Error";
    } 
    RuntimeErrorAlert.alert(this, (String)object, (String)paramObject, "End Application");
  }
  
  public void run() {
    CallContext callContext = CallContext.getInstance();
    Consumer consumer = callContext.consumer;
    callContext.consumer = (Consumer)VoidConsumer.instance;
    try {
      run(callContext);
      throwable = null;
    } catch (Throwable throwable) {}
    ModuleBody.runCleanup(callContext, throwable, consumer);
  }
  
  public final void run(CallContext paramCallContext) {
    String str;
    Consumer consumer = paramCallContext.consumer;
    runtime.$instance.run();
    this.$Stdebug$Mnform$St = Boolean.FALSE;
    this.form$Mnenvironment = (Environment)Environment.make(misc.symbol$To$String((Symbol)Lit0));
    FString fString = strings.stringAppend(new Object[] { misc.symbol$To$String((Symbol)Lit0), "-global-vars" });
    if (fString == null) {
      fString = null;
    } else {
      str = fString.toString();
    } 
    this.global$Mnvar$Mnenvironment = (Environment)Environment.make(str);
    Screen1 = null;
    this.form$Mnname$Mnsymbol = (Symbol)Lit0;
    this.events$Mnto$Mnregister = LList.Empty;
    this.components$Mnto$Mncreate = LList.Empty;
    this.global$Mnvars$Mnto$Mncreate = LList.Empty;
    this.form$Mndo$Mnafter$Mncreation = LList.Empty;
    runtime.$instance.run();
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.setAndCoerceProperty$Ex(Lit0, Lit3, Lit4, Lit5);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit6, Lit7, Lit5);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit8, "GROW_game_app", Lit9);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit10, Lit11, Lit5);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit12, "start.jpg", Lit9);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit13, Boolean.TRUE, Lit14);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit15, "Responsive", Lit9);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit16, "Screen1", Lit9);
      Values.writeValues(runtime.setAndCoerceProperty$Ex(Lit0, Lit17, Boolean.FALSE, Lit14), consumer);
    } else {
      addToFormDoAfterCreation(new Promise((Procedure)lambda$Fn2));
    } 
    this.VerticalArrangement4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit18, Lit19, lambda$Fn3), consumer);
    } else {
      addToComponents(Lit0, Lit22, Lit19, lambda$Fn4);
    } 
    this.Image1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit23, Lit24, lambda$Fn5), consumer);
    } else {
      addToComponents(Lit0, Lit30, Lit24, lambda$Fn6);
    } 
    this.VerticalArrangement2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit31, Lit32, lambda$Fn7), consumer);
    } else {
      addToComponents(Lit0, Lit34, Lit32, lambda$Fn8);
    } 
    this.Label1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit35, Lit36, lambda$Fn9), consumer);
    } else {
      addToComponents(Lit0, Lit42, Lit36, lambda$Fn10);
    } 
    this.VerticalArrangement7 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit43, Lit44, lambda$Fn11), consumer);
    } else {
      addToComponents(Lit0, Lit46, Lit44, lambda$Fn12);
    } 
    this.VerticalArrangement1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit47, Lit48, lambda$Fn13), consumer);
    } else {
      addToComponents(Lit0, Lit51, Lit48, lambda$Fn14);
    } 
    this.Label2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit48, Lit52, Lit53, lambda$Fn15), consumer);
    } else {
      addToComponents(Lit48, Lit57, Lit53, lambda$Fn16);
    } 
    this.Label3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit48, Lit58, Lit59, lambda$Fn17), consumer);
    } else {
      addToComponents(Lit48, Lit61, Lit59, lambda$Fn18);
    } 
    this.Label4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit48, Lit62, Lit63, lambda$Fn19), consumer);
    } else {
      addToComponents(Lit48, Lit65, Lit63, lambda$Fn20);
    } 
    this.Label5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit48, Lit66, Lit67, lambda$Fn21), consumer);
    } else {
      addToComponents(Lit48, Lit69, Lit67, lambda$Fn22);
    } 
    this.Label6 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit48, Lit70, Lit71, lambda$Fn23), consumer);
    } else {
      addToComponents(Lit48, Lit73, Lit71, lambda$Fn24);
    } 
    this.VerticalArrangement3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit74, Lit75, lambda$Fn25), consumer);
    } else {
      addToComponents(Lit0, Lit77, Lit75, lambda$Fn26);
    } 
    this.HorizontalArrangement1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit78, Lit79, lambda$Fn27), consumer);
    } else {
      addToComponents(Lit0, Lit81, Lit79, lambda$Fn28);
    } 
    this.Button1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit79, Lit82, Lit83, lambda$Fn29), consumer);
    } else {
      addToComponents(Lit79, Lit87, Lit83, lambda$Fn30);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.addToCurrentFormEnvironment((Symbol)Lit89, this.Button1$Click);
    } else {
      addToFormEnvironment((Symbol)Lit89, this.Button1$Click);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      EventDispatcher.registerEventForDelegation((HandlesEventDispatching)runtime.$Stthis$Mnform$St, "Button1", "Click");
    } else {
      addToEvents(Lit83, Lit90);
    } 
    this.VerticalArrangement5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit79, Lit91, Lit92, lambda$Fn31), consumer);
    } else {
      addToComponents(Lit79, Lit95, Lit92, lambda$Fn32);
    } 
    this.Button2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit79, Lit96, Lit97, lambda$Fn33), consumer);
    } else {
      addToComponents(Lit79, Lit99, Lit97, lambda$Fn34);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.addToCurrentFormEnvironment((Symbol)Lit101, this.Button2$Click);
    } else {
      addToFormEnvironment((Symbol)Lit101, this.Button2$Click);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      EventDispatcher.registerEventForDelegation((HandlesEventDispatching)runtime.$Stthis$Mnform$St, "Button2", "Click");
    } else {
      addToEvents(Lit97, Lit90);
    } 
    this.VerticalArrangement6 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit102, Lit103, lambda$Fn35), consumer);
    } else {
      addToComponents(Lit0, Lit105, Lit103, lambda$Fn36);
    } 
    runtime.initRuntime();
  }
  
  public void sendError(Object paramObject) {
    if (paramObject == null) {
      paramObject = null;
    } else {
      paramObject = paramObject.toString();
    } 
    RetValManager.sendError((String)paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/appinventor/ai_notifications_eoteam/GROW_game_app/Screen1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
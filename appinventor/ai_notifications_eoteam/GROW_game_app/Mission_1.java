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
import com.google.appinventor.components.runtime.Map;
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

public class Mission_1 extends Form implements Runnable {
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final FString Lit100;
  
  static final FString Lit101;
  
  static final IntNum Lit102;
  
  static final IntNum Lit103;
  
  static final FString Lit104;
  
  static final FString Lit105;
  
  static final SimpleSymbol Lit106;
  
  static final IntNum Lit107;
  
  static final SimpleSymbol Lit108;
  
  static final SimpleSymbol Lit109;
  
  static final SimpleSymbol Lit11;
  
  static final IntNum Lit110;
  
  static final FString Lit111;
  
  static final FString Lit112;
  
  static final IntNum Lit113;
  
  static final IntNum Lit114;
  
  static final FString Lit115;
  
  static final FString Lit116;
  
  static final SimpleSymbol Lit117;
  
  static final FString Lit118;
  
  static final FString Lit119;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit120;
  
  static final IntNum Lit121;
  
  static final FString Lit122;
  
  static final FString Lit123;
  
  static final SimpleSymbol Lit124;
  
  static final SimpleSymbol Lit125;
  
  static final IntNum Lit126;
  
  static final FString Lit127;
  
  static final FString Lit128;
  
  static final SimpleSymbol Lit129;
  
  static final FString Lit13;
  
  static final IntNum Lit130;
  
  static final FString Lit131;
  
  static final FString Lit132;
  
  static final SimpleSymbol Lit133;
  
  static final IntNum Lit134;
  
  static final FString Lit135;
  
  static final FString Lit136;
  
  static final SimpleSymbol Lit137;
  
  static final IntNum Lit138;
  
  static final FString Lit139;
  
  static final SimpleSymbol Lit14;
  
  static final FString Lit140;
  
  static final SimpleSymbol Lit141;
  
  static final IntNum Lit142;
  
  static final FString Lit143;
  
  static final FString Lit144;
  
  static final SimpleSymbol Lit145;
  
  static final IntNum Lit146;
  
  static final IntNum Lit147;
  
  static final FString Lit148;
  
  static final FString Lit149;
  
  static final SimpleSymbol Lit15;
  
  static final SimpleSymbol Lit150;
  
  static final IntNum Lit151;
  
  static final FString Lit152;
  
  static final FString Lit153;
  
  static final SimpleSymbol Lit154;
  
  static final IntNum Lit155;
  
  static final FString Lit156;
  
  static final SimpleSymbol Lit157;
  
  static final SimpleSymbol Lit158;
  
  static final SimpleSymbol Lit159;
  
  static final IntNum Lit16;
  
  static final SimpleSymbol Lit160;
  
  static final SimpleSymbol Lit161;
  
  static final SimpleSymbol Lit162;
  
  static final SimpleSymbol Lit163;
  
  static final SimpleSymbol Lit164;
  
  static final SimpleSymbol Lit165;
  
  static final SimpleSymbol Lit166;
  
  static final SimpleSymbol Lit167;
  
  static final SimpleSymbol Lit168;
  
  static final SimpleSymbol Lit169;
  
  static final FString Lit17;
  
  static final SimpleSymbol Lit170 = (SimpleSymbol)(new SimpleSymbol("lookup-handler")).readResolve();
  
  static final FString Lit18;
  
  static final SimpleSymbol Lit19;
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final IntNum Lit21;
  
  static final SimpleSymbol Lit22;
  
  static final IntNum Lit23;
  
  static final IntNum Lit24;
  
  static final SimpleSymbol Lit25;
  
  static final IntNum Lit26;
  
  static final FString Lit27;
  
  static final FString Lit28;
  
  static final SimpleSymbol Lit29;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit30;
  
  static final SimpleSymbol Lit31;
  
  static final IntNum Lit32;
  
  static final SimpleSymbol Lit33;
  
  static final SimpleSymbol Lit34;
  
  static final IntNum Lit35;
  
  static final FString Lit36;
  
  static final FString Lit37;
  
  static final SimpleSymbol Lit38;
  
  static final IntNum Lit39;
  
  static final SimpleSymbol Lit4;
  
  static final FString Lit40;
  
  static final FString Lit41;
  
  static final SimpleSymbol Lit42;
  
  static final IntNum Lit43;
  
  static final FString Lit44;
  
  static final FString Lit45;
  
  static final SimpleSymbol Lit46;
  
  static final IntNum Lit47;
  
  static final SimpleSymbol Lit48;
  
  static final IntNum Lit49;
  
  static final SimpleSymbol Lit5;
  
  static final IntNum Lit50;
  
  static final FString Lit51;
  
  static final SimpleSymbol Lit52;
  
  static final SimpleSymbol Lit53;
  
  static final SimpleSymbol Lit54;
  
  static final SimpleSymbol Lit55;
  
  static final SimpleSymbol Lit56;
  
  static final SimpleSymbol Lit57;
  
  static final SimpleSymbol Lit58;
  
  static final FString Lit59;
  
  static final IntNum Lit6;
  
  static final SimpleSymbol Lit60;
  
  static final IntNum Lit61;
  
  static final IntNum Lit62;
  
  static final FString Lit63;
  
  static final FString Lit64;
  
  static final SimpleSymbol Lit65;
  
  static final IntNum Lit66;
  
  static final IntNum Lit67;
  
  static final FString Lit68;
  
  static final SimpleSymbol Lit69;
  
  static final SimpleSymbol Lit7;
  
  static final FString Lit70;
  
  static final SimpleSymbol Lit71;
  
  static final IntNum Lit72;
  
  static final IntNum Lit73;
  
  static final FString Lit74;
  
  static final FString Lit75;
  
  static final SimpleSymbol Lit76;
  
  static final IntNum Lit77;
  
  static final IntNum Lit78;
  
  static final FString Lit79;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit80;
  
  static final FString Lit81;
  
  static final SimpleSymbol Lit82;
  
  static final IntNum Lit83;
  
  static final IntNum Lit84;
  
  static final FString Lit85;
  
  static final FString Lit86;
  
  static final SimpleSymbol Lit87;
  
  static final IntNum Lit88;
  
  static final IntNum Lit89;
  
  static final SimpleSymbol Lit9;
  
  static final FString Lit90;
  
  static final SimpleSymbol Lit91;
  
  static final FString Lit92;
  
  static final SimpleSymbol Lit93;
  
  static final IntNum Lit94;
  
  static final FString Lit95;
  
  static final FString Lit96;
  
  static final IntNum Lit97;
  
  static final SimpleSymbol Lit98;
  
  static final IntNum Lit99;
  
  public static Mission_1 Mission_1;
  
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
  
  static final ModuleMethod lambda$Fn37;
  
  static final ModuleMethod lambda$Fn38;
  
  static final ModuleMethod lambda$Fn39;
  
  static final ModuleMethod lambda$Fn4;
  
  static final ModuleMethod lambda$Fn40;
  
  static final ModuleMethod lambda$Fn41;
  
  static final ModuleMethod lambda$Fn42;
  
  static final ModuleMethod lambda$Fn43;
  
  static final ModuleMethod lambda$Fn44;
  
  static final ModuleMethod lambda$Fn45;
  
  static final ModuleMethod lambda$Fn46;
  
  static final ModuleMethod lambda$Fn47;
  
  static final ModuleMethod lambda$Fn48;
  
  static final ModuleMethod lambda$Fn49;
  
  static final ModuleMethod lambda$Fn5;
  
  static final ModuleMethod lambda$Fn50;
  
  static final ModuleMethod lambda$Fn51;
  
  static final ModuleMethod lambda$Fn52;
  
  static final ModuleMethod lambda$Fn53;
  
  static final ModuleMethod lambda$Fn54;
  
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
  
  public Button Button3;
  
  public final ModuleMethod Button3$Click;
  
  public Button Button4;
  
  public final ModuleMethod Button4$Click;
  
  public HorizontalArrangement HorizontalArrangement1;
  
  public HorizontalArrangement HorizontalArrangement2;
  
  public HorizontalArrangement HorizontalArrangement3;
  
  public Image Image1;
  
  public Image Image2;
  
  public Image Image3;
  
  public Label Label1;
  
  public Label Label2;
  
  public Label Label3;
  
  public Label Label4;
  
  public Label Label5;
  
  public Label Label6;
  
  public Label Label7;
  
  public Map Map1;
  
  public VerticalArrangement VerticalArrangement1;
  
  public VerticalArrangement VerticalArrangement2;
  
  public VerticalArrangement VerticalArrangement3;
  
  public VerticalArrangement VerticalArrangement4;
  
  public VerticalArrangement VerticalArrangement5;
  
  public VerticalArrangement VerticalArrangement6;
  
  public VerticalArrangement VerticalArrangement7;
  
  public VerticalArrangement VerticalArrangement8;
  
  public VerticalArrangement VerticalArrangement9;
  
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
    Lit169 = (SimpleSymbol)(new SimpleSymbol("dispatchGenericEvent")).readResolve();
    Lit168 = (SimpleSymbol)(new SimpleSymbol("dispatchEvent")).readResolve();
    Lit167 = (SimpleSymbol)(new SimpleSymbol("send-error")).readResolve();
    Lit166 = (SimpleSymbol)(new SimpleSymbol("add-to-form-do-after-creation")).readResolve();
    Lit165 = (SimpleSymbol)(new SimpleSymbol("add-to-global-vars")).readResolve();
    Lit164 = (SimpleSymbol)(new SimpleSymbol("add-to-components")).readResolve();
    Lit163 = (SimpleSymbol)(new SimpleSymbol("add-to-events")).readResolve();
    Lit162 = (SimpleSymbol)(new SimpleSymbol("add-to-global-var-environment")).readResolve();
    Lit161 = (SimpleSymbol)(new SimpleSymbol("is-bound-in-form-environment")).readResolve();
    Lit160 = (SimpleSymbol)(new SimpleSymbol("lookup-in-form-environment")).readResolve();
    Lit159 = (SimpleSymbol)(new SimpleSymbol("add-to-form-environment")).readResolve();
    Lit158 = (SimpleSymbol)(new SimpleSymbol("android-log-form")).readResolve();
    Lit157 = (SimpleSymbol)(new SimpleSymbol("get-simple-name")).readResolve();
    Lit156 = new FString("com.google.appinventor.components.runtime.Label");
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit155 = IntNum.make(arrayOfInt);
    Lit154 = (SimpleSymbol)(new SimpleSymbol("Label7")).readResolve();
    Lit153 = new FString("com.google.appinventor.components.runtime.Label");
    Lit152 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit151 = IntNum.make(arrayOfInt);
    Lit150 = (SimpleSymbol)(new SimpleSymbol("Label5")).readResolve();
    Lit149 = new FString("com.google.appinventor.components.runtime.Label");
    Lit148 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit147 = IntNum.make(-1001);
    Lit146 = IntNum.make(-1001);
    Lit145 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement8")).readResolve();
    Lit144 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit143 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit142 = IntNum.make(arrayOfInt);
    Lit141 = (SimpleSymbol)(new SimpleSymbol("Label4")).readResolve();
    Lit140 = new FString("com.google.appinventor.components.runtime.Label");
    Lit139 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit138 = IntNum.make(-1001);
    Lit137 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement7")).readResolve();
    Lit136 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit135 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit134 = IntNum.make(arrayOfInt);
    Lit133 = (SimpleSymbol)(new SimpleSymbol("Label3")).readResolve();
    Lit132 = new FString("com.google.appinventor.components.runtime.Label");
    Lit131 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit130 = IntNum.make(-1001);
    Lit129 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement6")).readResolve();
    Lit128 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit127 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit126 = IntNum.make(arrayOfInt);
    Lit125 = (SimpleSymbol)(new SimpleSymbol("TextAlignment")).readResolve();
    Lit124 = (SimpleSymbol)(new SimpleSymbol("Label6")).readResolve();
    Lit123 = new FString("com.google.appinventor.components.runtime.Label");
    Lit122 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit121 = IntNum.make(-1100);
    Lit120 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement4")).readResolve();
    Lit119 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit118 = new FString("com.google.appinventor.components.runtime.Label");
    Lit117 = (SimpleSymbol)(new SimpleSymbol("Label2")).readResolve();
    Lit116 = new FString("com.google.appinventor.components.runtime.Label");
    Lit115 = new FString("com.google.appinventor.components.runtime.Image");
    Lit114 = IntNum.make(-1100);
    Lit113 = IntNum.make(-1040);
    Lit112 = new FString("com.google.appinventor.components.runtime.Image");
    Lit111 = new FString("com.google.appinventor.components.runtime.Map");
    Lit110 = IntNum.make(-1080);
    Lit109 = (SimpleSymbol)(new SimpleSymbol("ShowScale")).readResolve();
    Lit108 = (SimpleSymbol)(new SimpleSymbol("ShowCompass")).readResolve();
    Lit107 = IntNum.make(-1040);
    Lit106 = (SimpleSymbol)(new SimpleSymbol("CenterFromString")).readResolve();
    Lit105 = new FString("com.google.appinventor.components.runtime.Map");
    Lit104 = new FString("com.google.appinventor.components.runtime.Image");
    Lit103 = IntNum.make(-1100);
    Lit102 = IntNum.make(-1040);
    Lit101 = new FString("com.google.appinventor.components.runtime.Image");
    Lit100 = new FString("com.google.appinventor.components.runtime.Image");
    Lit99 = IntNum.make(-1100);
    Lit98 = (SimpleSymbol)(new SimpleSymbol("Picture")).readResolve();
    Lit97 = IntNum.make(-1040);
    Lit96 = new FString("com.google.appinventor.components.runtime.Image");
    Lit95 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit94 = IntNum.make(-1100);
    Lit93 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement5")).readResolve();
    Lit92 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit91 = (SimpleSymbol)(new SimpleSymbol("Button3$Click")).readResolve();
    Lit90 = new FString("com.google.appinventor.components.runtime.Button");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit89 = IntNum.make(arrayOfInt);
    arrayOfInt = new int[2];
    arrayOfInt[0] = -7352248;
    Lit88 = IntNum.make(arrayOfInt);
    Lit87 = (SimpleSymbol)(new SimpleSymbol("Button3")).readResolve();
    Lit86 = new FString("com.google.appinventor.components.runtime.Button");
    Lit85 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit84 = IntNum.make(-1003);
    Lit83 = IntNum.make(-1005);
    Lit82 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement3")).readResolve();
    Lit81 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit80 = (SimpleSymbol)(new SimpleSymbol("Button2$Click")).readResolve();
    Lit79 = new FString("com.google.appinventor.components.runtime.Button");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit78 = IntNum.make(arrayOfInt);
    arrayOfInt = new int[2];
    arrayOfInt[0] = -7352248;
    Lit77 = IntNum.make(arrayOfInt);
    Lit76 = (SimpleSymbol)(new SimpleSymbol("Button2")).readResolve();
    Lit75 = new FString("com.google.appinventor.components.runtime.Button");
    Lit74 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit73 = IntNum.make(-1003);
    Lit72 = IntNum.make(-1005);
    Lit71 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement2")).readResolve();
    Lit70 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit69 = (SimpleSymbol)(new SimpleSymbol("Button1$Click")).readResolve();
    Lit68 = new FString("com.google.appinventor.components.runtime.Button");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit67 = IntNum.make(arrayOfInt);
    arrayOfInt = new int[2];
    arrayOfInt[0] = -7352248;
    Lit66 = IntNum.make(arrayOfInt);
    Lit65 = (SimpleSymbol)(new SimpleSymbol("Button1")).readResolve();
    Lit64 = new FString("com.google.appinventor.components.runtime.Button");
    Lit63 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit62 = IntNum.make(-1003);
    Lit61 = IntNum.make(-1005);
    Lit60 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement9")).readResolve();
    Lit59 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit58 = (SimpleSymbol)(new SimpleSymbol("Click")).readResolve();
    Lit57 = (SimpleSymbol)(new SimpleSymbol("Button4$Click")).readResolve();
    Lit56 = (SimpleSymbol)(new SimpleSymbol("Image1")).readResolve();
    Lit55 = (SimpleSymbol)(new SimpleSymbol("Image3")).readResolve();
    Lit54 = (SimpleSymbol)(new SimpleSymbol("Image2")).readResolve();
    Lit53 = (SimpleSymbol)(new SimpleSymbol("Visible")).readResolve();
    Lit52 = (SimpleSymbol)(new SimpleSymbol("Map1")).readResolve();
    Lit51 = new FString("com.google.appinventor.components.runtime.Button");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit50 = IntNum.make(arrayOfInt);
    Lit49 = IntNum.make(1);
    Lit48 = (SimpleSymbol)(new SimpleSymbol("Shape")).readResolve();
    arrayOfInt = new int[2];
    arrayOfInt[0] = -7352248;
    Lit47 = IntNum.make(arrayOfInt);
    Lit46 = (SimpleSymbol)(new SimpleSymbol("Button4")).readResolve();
    Lit45 = new FString("com.google.appinventor.components.runtime.Button");
    Lit44 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit43 = IntNum.make(-1100);
    Lit42 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement1")).readResolve();
    Lit41 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit40 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit39 = IntNum.make(-1005);
    Lit38 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement3")).readResolve();
    Lit37 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit36 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit35 = IntNum.make(arrayOfInt);
    Lit34 = (SimpleSymbol)(new SimpleSymbol("TextColor")).readResolve();
    Lit33 = (SimpleSymbol)(new SimpleSymbol("Text")).readResolve();
    Lit32 = IntNum.make(18);
    Lit31 = (SimpleSymbol)(new SimpleSymbol("FontSize")).readResolve();
    Lit30 = (SimpleSymbol)(new SimpleSymbol("FontBold")).readResolve();
    Lit29 = (SimpleSymbol)(new SimpleSymbol("Label1")).readResolve();
    Lit28 = new FString("com.google.appinventor.components.runtime.Label");
    Lit27 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit26 = IntNum.make(-1100);
    Lit25 = (SimpleSymbol)(new SimpleSymbol("Width")).readResolve();
    Lit24 = IntNum.make(-1005);
    Lit23 = IntNum.make(2);
    Lit22 = (SimpleSymbol)(new SimpleSymbol("AlignVertical")).readResolve();
    Lit21 = IntNum.make(3);
    Lit20 = (SimpleSymbol)(new SimpleSymbol("AlignHorizontal")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement2")).readResolve();
    Lit18 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit17 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit16 = IntNum.make(-1005);
    Lit15 = (SimpleSymbol)(new SimpleSymbol("Height")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement1")).readResolve();
    Lit13 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit12 = (SimpleSymbol)(new SimpleSymbol("TitleVisible")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("Title")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("Sizing")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("boolean")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("ShowListsAsJson")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("number")).readResolve();
    arrayOfInt = new int[2];
    arrayOfInt[0] = -15227642;
    Lit6 = IntNum.make(arrayOfInt);
    Lit5 = (SimpleSymbol)(new SimpleSymbol("BackgroundColor")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("text")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("AppName")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("*the-null-value*")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("getMessage")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("Mission_1")).readResolve();
  }
  
  public Mission_1() {
    ModuleInfo.register(this);
    Mission_1$frame mission_1$frame = new Mission_1$frame();
    mission_1$frame.$main = this;
    this.get$Mnsimple$Mnname = new ModuleMethod(mission_1$frame, 1, Lit157, 4097);
    this.onCreate = new ModuleMethod(mission_1$frame, 2, "onCreate", 4097);
    this.android$Mnlog$Mnform = new ModuleMethod(mission_1$frame, 3, Lit158, 4097);
    this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(mission_1$frame, 4, Lit159, 8194);
    this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(mission_1$frame, 5, Lit160, 8193);
    this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(mission_1$frame, 7, Lit161, 4097);
    this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(mission_1$frame, 8, Lit162, 8194);
    this.add$Mnto$Mnevents = new ModuleMethod(mission_1$frame, 9, Lit163, 8194);
    this.add$Mnto$Mncomponents = new ModuleMethod(mission_1$frame, 10, Lit164, 16388);
    this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(mission_1$frame, 11, Lit165, 8194);
    this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(mission_1$frame, 12, Lit166, 4097);
    this.send$Mnerror = new ModuleMethod(mission_1$frame, 13, Lit167, 4097);
    this.process$Mnexception = new ModuleMethod(mission_1$frame, 14, "process-exception", 4097);
    this.dispatchEvent = new ModuleMethod(mission_1$frame, 15, Lit168, 16388);
    this.dispatchGenericEvent = new ModuleMethod(mission_1$frame, 16, Lit169, 16388);
    this.lookup$Mnhandler = new ModuleMethod(mission_1$frame, 17, Lit170, 8194);
    ModuleMethod moduleMethod = new ModuleMethod(mission_1$frame, 18, null, 0);
    moduleMethod.setProperty("source-location", "/tmp/runtime6993135000179593734.scm:622");
    lambda$Fn1 = moduleMethod;
    this.$define = new ModuleMethod(mission_1$frame, 19, "$define", 0);
    lambda$Fn2 = new ModuleMethod(mission_1$frame, 20, null, 0);
    lambda$Fn3 = new ModuleMethod(mission_1$frame, 21, null, 0);
    lambda$Fn4 = new ModuleMethod(mission_1$frame, 22, null, 0);
    lambda$Fn5 = new ModuleMethod(mission_1$frame, 23, null, 0);
    lambda$Fn6 = new ModuleMethod(mission_1$frame, 24, null, 0);
    lambda$Fn7 = new ModuleMethod(mission_1$frame, 25, null, 0);
    lambda$Fn8 = new ModuleMethod(mission_1$frame, 26, null, 0);
    lambda$Fn9 = new ModuleMethod(mission_1$frame, 27, null, 0);
    lambda$Fn10 = new ModuleMethod(mission_1$frame, 28, null, 0);
    lambda$Fn11 = new ModuleMethod(mission_1$frame, 29, null, 0);
    lambda$Fn12 = new ModuleMethod(mission_1$frame, 30, null, 0);
    lambda$Fn13 = new ModuleMethod(mission_1$frame, 31, null, 0);
    lambda$Fn14 = new ModuleMethod(mission_1$frame, 32, null, 0);
    this.Button4$Click = new ModuleMethod(mission_1$frame, 33, Lit57, 0);
    lambda$Fn15 = new ModuleMethod(mission_1$frame, 34, null, 0);
    lambda$Fn16 = new ModuleMethod(mission_1$frame, 35, null, 0);
    lambda$Fn17 = new ModuleMethod(mission_1$frame, 36, null, 0);
    lambda$Fn18 = new ModuleMethod(mission_1$frame, 37, null, 0);
    this.Button1$Click = new ModuleMethod(mission_1$frame, 38, Lit69, 0);
    lambda$Fn19 = new ModuleMethod(mission_1$frame, 39, null, 0);
    lambda$Fn20 = new ModuleMethod(mission_1$frame, 40, null, 0);
    lambda$Fn21 = new ModuleMethod(mission_1$frame, 41, null, 0);
    lambda$Fn22 = new ModuleMethod(mission_1$frame, 42, null, 0);
    this.Button2$Click = new ModuleMethod(mission_1$frame, 43, Lit80, 0);
    lambda$Fn23 = new ModuleMethod(mission_1$frame, 44, null, 0);
    lambda$Fn24 = new ModuleMethod(mission_1$frame, 45, null, 0);
    lambda$Fn25 = new ModuleMethod(mission_1$frame, 46, null, 0);
    lambda$Fn26 = new ModuleMethod(mission_1$frame, 47, null, 0);
    this.Button3$Click = new ModuleMethod(mission_1$frame, 48, Lit91, 0);
    lambda$Fn27 = new ModuleMethod(mission_1$frame, 49, null, 0);
    lambda$Fn28 = new ModuleMethod(mission_1$frame, 50, null, 0);
    lambda$Fn29 = new ModuleMethod(mission_1$frame, 51, null, 0);
    lambda$Fn30 = new ModuleMethod(mission_1$frame, 52, null, 0);
    lambda$Fn31 = new ModuleMethod(mission_1$frame, 53, null, 0);
    lambda$Fn32 = new ModuleMethod(mission_1$frame, 54, null, 0);
    lambda$Fn33 = new ModuleMethod(mission_1$frame, 55, null, 0);
    lambda$Fn34 = new ModuleMethod(mission_1$frame, 56, null, 0);
    lambda$Fn35 = new ModuleMethod(mission_1$frame, 57, null, 0);
    lambda$Fn36 = new ModuleMethod(mission_1$frame, 58, null, 0);
    lambda$Fn37 = new ModuleMethod(mission_1$frame, 59, null, 0);
    lambda$Fn38 = new ModuleMethod(mission_1$frame, 60, null, 0);
    lambda$Fn39 = new ModuleMethod(mission_1$frame, 61, null, 0);
    lambda$Fn40 = new ModuleMethod(mission_1$frame, 62, null, 0);
    lambda$Fn41 = new ModuleMethod(mission_1$frame, 63, null, 0);
    lambda$Fn42 = new ModuleMethod(mission_1$frame, 64, null, 0);
    lambda$Fn43 = new ModuleMethod(mission_1$frame, 65, null, 0);
    lambda$Fn44 = new ModuleMethod(mission_1$frame, 66, null, 0);
    lambda$Fn45 = new ModuleMethod(mission_1$frame, 67, null, 0);
    lambda$Fn46 = new ModuleMethod(mission_1$frame, 68, null, 0);
    lambda$Fn47 = new ModuleMethod(mission_1$frame, 69, null, 0);
    lambda$Fn48 = new ModuleMethod(mission_1$frame, 70, null, 0);
    lambda$Fn49 = new ModuleMethod(mission_1$frame, 71, null, 0);
    lambda$Fn50 = new ModuleMethod(mission_1$frame, 72, null, 0);
    lambda$Fn51 = new ModuleMethod(mission_1$frame, 73, null, 0);
    lambda$Fn52 = new ModuleMethod(mission_1$frame, 74, null, 0);
    lambda$Fn53 = new ModuleMethod(mission_1$frame, 75, null, 0);
    lambda$Fn54 = new ModuleMethod(mission_1$frame, 76, null, 0);
  }
  
  static Object lambda10() {
    return runtime.setAndCoerceProperty$Ex(Lit38, Lit15, Lit39, Lit7);
  }
  
  static Object lambda11() {
    return runtime.setAndCoerceProperty$Ex(Lit38, Lit15, Lit39, Lit7);
  }
  
  static Object lambda12() {
    runtime.setAndCoerceProperty$Ex(Lit42, Lit20, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit42, Lit22, Lit23, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit42, Lit25, Lit43, Lit7);
  }
  
  static Object lambda13() {
    runtime.setAndCoerceProperty$Ex(Lit42, Lit20, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit42, Lit22, Lit23, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit42, Lit25, Lit43, Lit7);
  }
  
  static Object lambda14() {
    runtime.setAndCoerceProperty$Ex(Lit46, Lit5, Lit47, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit46, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit46, Lit48, Lit49, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit46, Lit33, "Map", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit46, Lit34, Lit50, Lit7);
  }
  
  static Object lambda15() {
    runtime.setAndCoerceProperty$Ex(Lit46, Lit5, Lit47, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit46, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit46, Lit48, Lit49, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit46, Lit33, "Map", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit46, Lit34, Lit50, Lit7);
  }
  
  static Object lambda16() {
    runtime.setAndCoerceProperty$Ex(Lit60, Lit15, Lit61, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit60, Lit25, Lit62, Lit7);
  }
  
  static Object lambda17() {
    runtime.setAndCoerceProperty$Ex(Lit60, Lit15, Lit61, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit60, Lit25, Lit62, Lit7);
  }
  
  static Object lambda18() {
    runtime.setAndCoerceProperty$Ex(Lit65, Lit5, Lit66, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit65, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit65, Lit48, Lit49, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit65, Lit33, "Surface Temperature", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit65, Lit34, Lit67, Lit7);
  }
  
  static Object lambda19() {
    runtime.setAndCoerceProperty$Ex(Lit65, Lit5, Lit66, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit65, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit65, Lit48, Lit49, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit65, Lit33, "Surface Temperature", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit65, Lit34, Lit67, Lit7);
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
    runtime.setAndCoerceProperty$Ex(Lit71, Lit15, Lit72, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit71, Lit25, Lit73, Lit7);
  }
  
  static Object lambda21() {
    runtime.setAndCoerceProperty$Ex(Lit71, Lit15, Lit72, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit71, Lit25, Lit73, Lit7);
  }
  
  static Object lambda22() {
    runtime.setAndCoerceProperty$Ex(Lit76, Lit5, Lit77, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit76, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit76, Lit48, Lit49, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit76, Lit33, "NDVI", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit76, Lit34, Lit78, Lit7);
  }
  
  static Object lambda23() {
    runtime.setAndCoerceProperty$Ex(Lit76, Lit5, Lit77, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit76, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit76, Lit48, Lit49, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit76, Lit33, "NDVI", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit76, Lit34, Lit78, Lit7);
  }
  
  static Object lambda24() {
    runtime.setAndCoerceProperty$Ex(Lit82, Lit15, Lit83, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit82, Lit25, Lit84, Lit7);
  }
  
  static Object lambda25() {
    runtime.setAndCoerceProperty$Ex(Lit82, Lit15, Lit83, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit82, Lit25, Lit84, Lit7);
  }
  
  static Object lambda26() {
    runtime.setAndCoerceProperty$Ex(Lit87, Lit5, Lit88, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit87, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit87, Lit48, Lit49, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit87, Lit33, "Rank", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit87, Lit34, Lit89, Lit7);
  }
  
  static Object lambda27() {
    runtime.setAndCoerceProperty$Ex(Lit87, Lit5, Lit88, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit87, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit87, Lit48, Lit49, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit87, Lit33, "Rank", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit87, Lit34, Lit89, Lit7);
  }
  
  static Object lambda28() {
    runtime.setAndCoerceProperty$Ex(Lit93, Lit20, Lit21, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit93, Lit25, Lit94, Lit7);
  }
  
  static Object lambda29() {
    runtime.setAndCoerceProperty$Ex(Lit93, Lit20, Lit21, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit93, Lit25, Lit94, Lit7);
  }
  
  static Object lambda3() {
    runtime.setAndCoerceProperty$Ex(Lit0, Lit3, "GROW_game_app", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit5, Lit6, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit8, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "Responsive", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit11, "Mission_1", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit0, Lit12, Boolean.FALSE, Lit9);
  }
  
  static Object lambda30() {
    runtime.setAndCoerceProperty$Ex(Lit55, Lit15, Lit97, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit55, Lit98, "rank.jpg", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit55, Lit53, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit55, Lit25, Lit99, Lit7);
  }
  
  static Object lambda31() {
    runtime.setAndCoerceProperty$Ex(Lit55, Lit15, Lit97, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit55, Lit98, "rank.jpg", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit55, Lit53, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit55, Lit25, Lit99, Lit7);
  }
  
  static Object lambda32() {
    runtime.setAndCoerceProperty$Ex(Lit54, Lit15, Lit102, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit54, Lit98, "zoom_ndvi.jpg", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit54, Lit53, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit54, Lit25, Lit103, Lit7);
  }
  
  static Object lambda33() {
    runtime.setAndCoerceProperty$Ex(Lit54, Lit15, Lit102, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit54, Lit98, "zoom_ndvi.jpg", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit54, Lit53, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit54, Lit25, Lit103, Lit7);
  }
  
  static Object lambda34() {
    runtime.setAndCoerceProperty$Ex(Lit52, Lit106, "38.015843000448356, 23.695793151855472", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit52, Lit15, Lit107, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit52, Lit108, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit52, Lit109, Boolean.TRUE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit52, Lit25, Lit110, Lit7);
  }
  
  static Object lambda35() {
    runtime.setAndCoerceProperty$Ex(Lit52, Lit106, "38.015843000448356, 23.695793151855472", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit52, Lit15, Lit107, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit52, Lit108, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit52, Lit109, Boolean.TRUE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit52, Lit25, Lit110, Lit7);
  }
  
  static Object lambda36() {
    runtime.setAndCoerceProperty$Ex(Lit56, Lit15, Lit113, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit98, "zoom_cel.jpg", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit53, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit56, Lit25, Lit114, Lit7);
  }
  
  static Object lambda37() {
    runtime.setAndCoerceProperty$Ex(Lit56, Lit15, Lit113, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit98, "zoom_cel.jpg", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit53, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit56, Lit25, Lit114, Lit7);
  }
  
  static Object lambda38() {
    runtime.setAndCoerceProperty$Ex(Lit120, Lit20, Lit21, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit120, Lit25, Lit121, Lit7);
  }
  
  static Object lambda39() {
    runtime.setAndCoerceProperty$Ex(Lit120, Lit20, Lit21, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit120, Lit25, Lit121, Lit7);
  }
  
  static Object lambda4() {
    return runtime.setAndCoerceProperty$Ex(Lit14, Lit15, Lit16, Lit7);
  }
  
  static Object lambda40() {
    runtime.setAndCoerceProperty$Ex(Lit124, Lit30, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit124, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit124, Lit33, "Statistic Data", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit124, Lit125, Lit49, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit124, Lit34, Lit126, Lit7);
  }
  
  static Object lambda41() {
    runtime.setAndCoerceProperty$Ex(Lit124, Lit30, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit124, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit124, Lit33, "Statistic Data", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit124, Lit125, Lit49, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit124, Lit34, Lit126, Lit7);
  }
  
  static Object lambda42() {
    return runtime.setAndCoerceProperty$Ex(Lit129, Lit15, Lit130, Lit7);
  }
  
  static Object lambda43() {
    return runtime.setAndCoerceProperty$Ex(Lit129, Lit15, Lit130, Lit7);
  }
  
  static Object lambda44() {
    runtime.setAndCoerceProperty$Ex(Lit133, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit133, Lit33, "Healthy Vegetation Urban Cover : 3,22% (Low)", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit133, Lit34, Lit134, Lit7);
  }
  
  static Object lambda45() {
    runtime.setAndCoerceProperty$Ex(Lit133, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit133, Lit33, "Healthy Vegetation Urban Cover : 3,22% (Low)", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit133, Lit34, Lit134, Lit7);
  }
  
  static Object lambda46() {
    return runtime.setAndCoerceProperty$Ex(Lit137, Lit15, Lit138, Lit7);
  }
  
  static Object lambda47() {
    return runtime.setAndCoerceProperty$Ex(Lit137, Lit15, Lit138, Lit7);
  }
  
  static Object lambda48() {
    runtime.setAndCoerceProperty$Ex(Lit141, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit141, Lit33, "Mean temperature : 25,5 C", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit141, Lit34, Lit142, Lit7);
  }
  
  static Object lambda49() {
    runtime.setAndCoerceProperty$Ex(Lit141, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit141, Lit33, "Mean temperature : 25,5 C", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit141, Lit34, Lit142, Lit7);
  }
  
  static Object lambda5() {
    return runtime.setAndCoerceProperty$Ex(Lit14, Lit15, Lit16, Lit7);
  }
  
  static Object lambda50() {
    runtime.setAndCoerceProperty$Ex(Lit145, Lit15, Lit146, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit145, Lit25, Lit147, Lit7);
  }
  
  static Object lambda51() {
    runtime.setAndCoerceProperty$Ex(Lit145, Lit15, Lit146, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit145, Lit25, Lit147, Lit7);
  }
  
  static Object lambda52() {
    runtime.setAndCoerceProperty$Ex(Lit150, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit150, Lit33, "Mean Tropospheric NO2 : 8 * 10^15 molecules/cm^2 (High)", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit150, Lit34, Lit151, Lit7);
  }
  
  static Object lambda53() {
    runtime.setAndCoerceProperty$Ex(Lit150, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit150, Lit33, "Mean Tropospheric NO2 : 8 * 10^15 molecules/cm^2 (High)", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit150, Lit34, Lit151, Lit7);
  }
  
  static Object lambda54() {
    runtime.setAndCoerceProperty$Ex(Lit154, Lit30, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit154, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit154, Lit33, "Peristeri Rank: 3 (Low)", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit154, Lit34, Lit155, Lit7);
  }
  
  static Object lambda55() {
    runtime.setAndCoerceProperty$Ex(Lit154, Lit30, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit154, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit154, Lit33, "Peristeri Rank: 3 (Low)", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit154, Lit34, Lit155, Lit7);
  }
  
  static Object lambda6() {
    runtime.setAndCoerceProperty$Ex(Lit19, Lit20, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit19, Lit22, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit19, Lit15, Lit24, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit19, Lit25, Lit26, Lit7);
  }
  
  static Object lambda7() {
    runtime.setAndCoerceProperty$Ex(Lit19, Lit20, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit19, Lit22, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit19, Lit15, Lit24, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit19, Lit25, Lit26, Lit7);
  }
  
  static Object lambda8() {
    runtime.setAndCoerceProperty$Ex(Lit29, Lit30, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit29, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit29, Lit33, "Mission 1", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit29, Lit34, Lit35, Lit7);
  }
  
  static Object lambda9() {
    runtime.setAndCoerceProperty$Ex(Lit29, Lit30, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit29, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit29, Lit33, "Mission 1", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit29, Lit34, Lit35, Lit7);
  }
  
  public void $define() {
    Language.setDefaults((Language)Scheme.getInstance());
    try {
      run();
    } catch (Exception exception) {
      androidLogForm(exception.getMessage());
      processException(exception);
    } 
    Mission_1 = this;
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
    runtime.setAndCoerceProperty$Ex(Lit52, Lit53, Boolean.FALSE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit54, Lit53, Boolean.FALSE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit55, Lit53, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit56, Lit53, Boolean.TRUE, Lit9);
  }
  
  public Object Button2$Click() {
    runtime.setThisForm();
    runtime.setAndCoerceProperty$Ex(Lit52, Lit53, Boolean.FALSE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit53, Boolean.FALSE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit55, Lit53, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit54, Lit53, Boolean.TRUE, Lit9);
  }
  
  public Object Button3$Click() {
    runtime.setThisForm();
    runtime.setAndCoerceProperty$Ex(Lit52, Lit53, Boolean.FALSE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit53, Boolean.FALSE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit54, Lit53, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit55, Lit53, Boolean.TRUE, Lit9);
  }
  
  public Object Button4$Click() {
    runtime.setThisForm();
    runtime.setAndCoerceProperty$Ex(Lit52, Lit53, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit54, Lit53, Boolean.FALSE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit55, Lit53, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit56, Lit53, Boolean.FALSE, Lit9);
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
    Mission_1 = null;
    this.form$Mnname$Mnsymbol = (Symbol)Lit0;
    this.events$Mnto$Mnregister = LList.Empty;
    this.components$Mnto$Mncreate = LList.Empty;
    this.global$Mnvars$Mnto$Mncreate = LList.Empty;
    this.form$Mndo$Mnafter$Mncreation = LList.Empty;
    runtime.$instance.run();
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.setAndCoerceProperty$Ex(Lit0, Lit3, "GROW_game_app", Lit4);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit5, Lit6, Lit7);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit8, Boolean.TRUE, Lit9);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "Responsive", Lit4);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit11, "Mission_1", Lit4);
      Values.writeValues(runtime.setAndCoerceProperty$Ex(Lit0, Lit12, Boolean.FALSE, Lit9), consumer);
    } else {
      addToFormDoAfterCreation(new Promise((Procedure)lambda$Fn2));
    } 
    this.VerticalArrangement1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit13, Lit14, lambda$Fn3), consumer);
    } else {
      addToComponents(Lit0, Lit17, Lit14, lambda$Fn4);
    } 
    this.VerticalArrangement2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit18, Lit19, lambda$Fn5), consumer);
    } else {
      addToComponents(Lit0, Lit27, Lit19, lambda$Fn6);
    } 
    this.Label1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit19, Lit28, Lit29, lambda$Fn7), consumer);
    } else {
      addToComponents(Lit19, Lit36, Lit29, lambda$Fn8);
    } 
    this.VerticalArrangement3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit37, Lit38, lambda$Fn9), consumer);
    } else {
      addToComponents(Lit0, Lit40, Lit38, lambda$Fn10);
    } 
    this.HorizontalArrangement1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit41, Lit42, lambda$Fn11), consumer);
    } else {
      addToComponents(Lit0, Lit44, Lit42, lambda$Fn12);
    } 
    this.Button4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit42, Lit45, Lit46, lambda$Fn13), consumer);
    } else {
      addToComponents(Lit42, Lit51, Lit46, lambda$Fn14);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.addToCurrentFormEnvironment((Symbol)Lit57, this.Button4$Click);
    } else {
      addToFormEnvironment((Symbol)Lit57, this.Button4$Click);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      EventDispatcher.registerEventForDelegation((HandlesEventDispatching)runtime.$Stthis$Mnform$St, "Button4", "Click");
    } else {
      addToEvents(Lit46, Lit58);
    } 
    this.VerticalArrangement9 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit42, Lit59, Lit60, lambda$Fn15), consumer);
    } else {
      addToComponents(Lit42, Lit63, Lit60, lambda$Fn16);
    } 
    this.Button1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit42, Lit64, Lit65, lambda$Fn17), consumer);
    } else {
      addToComponents(Lit42, Lit68, Lit65, lambda$Fn18);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.addToCurrentFormEnvironment((Symbol)Lit69, this.Button1$Click);
    } else {
      addToFormEnvironment((Symbol)Lit69, this.Button1$Click);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      EventDispatcher.registerEventForDelegation((HandlesEventDispatching)runtime.$Stthis$Mnform$St, "Button1", "Click");
    } else {
      addToEvents(Lit65, Lit58);
    } 
    this.HorizontalArrangement2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit42, Lit70, Lit71, lambda$Fn19), consumer);
    } else {
      addToComponents(Lit42, Lit74, Lit71, lambda$Fn20);
    } 
    this.Button2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit42, Lit75, Lit76, lambda$Fn21), consumer);
    } else {
      addToComponents(Lit42, Lit79, Lit76, lambda$Fn22);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.addToCurrentFormEnvironment((Symbol)Lit80, this.Button2$Click);
    } else {
      addToFormEnvironment((Symbol)Lit80, this.Button2$Click);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      EventDispatcher.registerEventForDelegation((HandlesEventDispatching)runtime.$Stthis$Mnform$St, "Button2", "Click");
    } else {
      addToEvents(Lit76, Lit58);
    } 
    this.HorizontalArrangement3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit42, Lit81, Lit82, lambda$Fn23), consumer);
    } else {
      addToComponents(Lit42, Lit85, Lit82, lambda$Fn24);
    } 
    this.Button3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit42, Lit86, Lit87, lambda$Fn25), consumer);
    } else {
      addToComponents(Lit42, Lit90, Lit87, lambda$Fn26);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.addToCurrentFormEnvironment((Symbol)Lit91, this.Button3$Click);
    } else {
      addToFormEnvironment((Symbol)Lit91, this.Button3$Click);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      EventDispatcher.registerEventForDelegation((HandlesEventDispatching)runtime.$Stthis$Mnform$St, "Button3", "Click");
    } else {
      addToEvents(Lit87, Lit58);
    } 
    this.VerticalArrangement5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit92, Lit93, lambda$Fn27), consumer);
    } else {
      addToComponents(Lit0, Lit95, Lit93, lambda$Fn28);
    } 
    this.Image3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit93, Lit96, Lit55, lambda$Fn29), consumer);
    } else {
      addToComponents(Lit93, Lit100, Lit55, lambda$Fn30);
    } 
    this.Image2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit93, Lit101, Lit54, lambda$Fn31), consumer);
    } else {
      addToComponents(Lit93, Lit104, Lit54, lambda$Fn32);
    } 
    this.Map1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit93, Lit105, Lit52, lambda$Fn33), consumer);
    } else {
      addToComponents(Lit93, Lit111, Lit52, lambda$Fn34);
    } 
    this.Image1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit93, Lit112, Lit56, lambda$Fn35), consumer);
    } else {
      addToComponents(Lit93, Lit115, Lit56, lambda$Fn36);
    } 
    this.Label2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit116, Lit117, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit118, Lit117, Boolean.FALSE);
    } 
    this.VerticalArrangement4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit119, Lit120, lambda$Fn37), consumer);
    } else {
      addToComponents(Lit0, Lit122, Lit120, lambda$Fn38);
    } 
    this.Label6 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit120, Lit123, Lit124, lambda$Fn39), consumer);
    } else {
      addToComponents(Lit120, Lit127, Lit124, lambda$Fn40);
    } 
    this.VerticalArrangement6 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit120, Lit128, Lit129, lambda$Fn41), consumer);
    } else {
      addToComponents(Lit120, Lit131, Lit129, lambda$Fn42);
    } 
    this.Label3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit120, Lit132, Lit133, lambda$Fn43), consumer);
    } else {
      addToComponents(Lit120, Lit135, Lit133, lambda$Fn44);
    } 
    this.VerticalArrangement7 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit120, Lit136, Lit137, lambda$Fn45), consumer);
    } else {
      addToComponents(Lit120, Lit139, Lit137, lambda$Fn46);
    } 
    this.Label4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit120, Lit140, Lit141, lambda$Fn47), consumer);
    } else {
      addToComponents(Lit120, Lit143, Lit141, lambda$Fn48);
    } 
    this.VerticalArrangement8 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit120, Lit144, Lit145, lambda$Fn49), consumer);
    } else {
      addToComponents(Lit120, Lit148, Lit145, lambda$Fn50);
    } 
    this.Label5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit120, Lit149, Lit150, lambda$Fn51), consumer);
    } else {
      addToComponents(Lit120, Lit152, Lit150, lambda$Fn52);
    } 
    this.Label7 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit120, Lit153, Lit154, lambda$Fn53), consumer);
    } else {
      addToComponents(Lit120, Lit156, Lit154, lambda$Fn54);
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/appinventor/ai_notifications_eoteam/GROW_game_app/Mission_1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
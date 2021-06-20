package appinventor.ai_notifications_eoteam.GROW_game_app;

import android.os.Bundle;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.CheckBox;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.ListPicker;
import com.google.appinventor.components.runtime.TextBox;
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

public class Sign_up extends Form implements Runnable {
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final FString Lit100;
  
  static final SimpleSymbol Lit101;
  
  static final IntNum Lit102;
  
  static final FString Lit103;
  
  static final FString Lit104;
  
  static final SimpleSymbol Lit105;
  
  static final IntNum Lit106;
  
  static final SimpleSymbol Lit107;
  
  static final SimpleSymbol Lit108;
  
  static final IntNum Lit109;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit110;
  
  static final IntNum Lit111;
  
  static final FString Lit112;
  
  static final FString Lit113;
  
  static final SimpleSymbol Lit114;
  
  static final IntNum Lit115;
  
  static final FString Lit116;
  
  static final FString Lit117;
  
  static final SimpleSymbol Lit118;
  
  static final IntNum Lit119;
  
  static final SimpleSymbol Lit12;
  
  static final FString Lit120;
  
  static final FString Lit121;
  
  static final SimpleSymbol Lit122;
  
  static final IntNum Lit123;
  
  static final FString Lit124;
  
  static final FString Lit125;
  
  static final SimpleSymbol Lit126;
  
  static final IntNum Lit127;
  
  static final FString Lit128;
  
  static final FString Lit129;
  
  static final SimpleSymbol Lit13;
  
  static final SimpleSymbol Lit130;
  
  static final IntNum Lit131;
  
  static final FString Lit132;
  
  static final FString Lit133;
  
  static final SimpleSymbol Lit134;
  
  static final FString Lit135;
  
  static final FString Lit136;
  
  static final SimpleSymbol Lit137;
  
  static final IntNum Lit138;
  
  static final FString Lit139;
  
  static final SimpleSymbol Lit14;
  
  static final FString Lit140;
  
  static final SimpleSymbol Lit141;
  
  static final IntNum Lit142;
  
  static final IntNum Lit143;
  
  static final FString Lit144;
  
  static final FString Lit145;
  
  static final SimpleSymbol Lit146;
  
  static final FString Lit147;
  
  static final FString Lit148;
  
  static final SimpleSymbol Lit149;
  
  static final FString Lit15;
  
  static final SimpleSymbol Lit150;
  
  static final IntNum Lit151;
  
  static final IntNum Lit152;
  
  static final FString Lit153;
  
  static final FString Lit154;
  
  static final SimpleSymbol Lit155;
  
  static final IntNum Lit156;
  
  static final FString Lit157;
  
  static final FString Lit158;
  
  static final SimpleSymbol Lit159;
  
  static final SimpleSymbol Lit16;
  
  static final IntNum Lit160;
  
  static final IntNum Lit161;
  
  static final IntNum Lit162;
  
  static final FString Lit163;
  
  static final PairWithPosition Lit164;
  
  static final SimpleSymbol Lit165;
  
  static final SimpleSymbol Lit166;
  
  static final SimpleSymbol Lit167;
  
  static final SimpleSymbol Lit168;
  
  static final SimpleSymbol Lit169;
  
  static final SimpleSymbol Lit17;
  
  static final SimpleSymbol Lit170;
  
  static final SimpleSymbol Lit171;
  
  static final SimpleSymbol Lit172;
  
  static final SimpleSymbol Lit173;
  
  static final SimpleSymbol Lit174;
  
  static final SimpleSymbol Lit175;
  
  static final SimpleSymbol Lit176;
  
  static final SimpleSymbol Lit177;
  
  static final SimpleSymbol Lit178;
  
  static final SimpleSymbol Lit179;
  
  static final IntNum Lit18;
  
  static final SimpleSymbol Lit180 = (SimpleSymbol)(new SimpleSymbol("lookup-handler")).readResolve();
  
  static final FString Lit19;
  
  static final SimpleSymbol Lit2;
  
  static final FString Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SimpleSymbol Lit22;
  
  static final SimpleSymbol Lit23;
  
  static final SimpleSymbol Lit24;
  
  static final IntNum Lit25;
  
  static final SimpleSymbol Lit26;
  
  static final SimpleSymbol Lit27;
  
  static final IntNum Lit28;
  
  static final SimpleSymbol Lit29;
  
  static final SimpleSymbol Lit3;
  
  static final IntNum Lit30;
  
  static final SimpleSymbol Lit31;
  
  static final IntNum Lit32;
  
  static final FString Lit33;
  
  static final FString Lit34;
  
  static final SimpleSymbol Lit35;
  
  static final IntNum Lit36;
  
  static final FString Lit37;
  
  static final FString Lit38;
  
  static final SimpleSymbol Lit39;
  
  static final SimpleSymbol Lit4;
  
  static final FString Lit40;
  
  static final FString Lit41;
  
  static final SimpleSymbol Lit42;
  
  static final IntNum Lit43;
  
  static final FString Lit44;
  
  static final FString Lit45;
  
  static final SimpleSymbol Lit46;
  
  static final IntNum Lit47;
  
  static final IntNum Lit48;
  
  static final FString Lit49;
  
  static final SimpleSymbol Lit5;
  
  static final FString Lit50;
  
  static final SimpleSymbol Lit51;
  
  static final FString Lit52;
  
  static final FString Lit53;
  
  static final SimpleSymbol Lit54;
  
  static final IntNum Lit55;
  
  static final FString Lit56;
  
  static final FString Lit57;
  
  static final SimpleSymbol Lit58;
  
  static final FString Lit59;
  
  static final IntNum Lit6;
  
  static final FString Lit60;
  
  static final SimpleSymbol Lit61;
  
  static final IntNum Lit62;
  
  static final FString Lit63;
  
  static final FString Lit64;
  
  static final SimpleSymbol Lit65;
  
  static final IntNum Lit66;
  
  static final IntNum Lit67;
  
  static final FString Lit68;
  
  static final FString Lit69;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit70;
  
  static final FString Lit71;
  
  static final FString Lit72;
  
  static final SimpleSymbol Lit73;
  
  static final IntNum Lit74;
  
  static final FString Lit75;
  
  static final FString Lit76;
  
  static final SimpleSymbol Lit77;
  
  static final FString Lit78;
  
  static final FString Lit79;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit80;
  
  static final IntNum Lit81;
  
  static final FString Lit82;
  
  static final FString Lit83;
  
  static final SimpleSymbol Lit84;
  
  static final FString Lit85;
  
  static final FString Lit86;
  
  static final SimpleSymbol Lit87;
  
  static final IntNum Lit88;
  
  static final FString Lit89;
  
  static final SimpleSymbol Lit9;
  
  static final FString Lit90;
  
  static final SimpleSymbol Lit91;
  
  static final FString Lit92;
  
  static final FString Lit93;
  
  static final SimpleSymbol Lit94;
  
  static final IntNum Lit95;
  
  static final FString Lit96;
  
  static final FString Lit97;
  
  static final SimpleSymbol Lit98;
  
  static final FString Lit99;
  
  public static Sign_up Sign_up;
  
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
  
  static final ModuleMethod lambda$Fn6;
  
  static final ModuleMethod lambda$Fn7;
  
  static final ModuleMethod lambda$Fn8;
  
  static final ModuleMethod lambda$Fn9;
  
  public Boolean $Stdebug$Mnform$St;
  
  public final ModuleMethod $define;
  
  public Button Button1;
  
  public final ModuleMethod Button1$Click;
  
  public CheckBox CheckBox1;
  
  public CheckBox CheckBox2;
  
  public CheckBox CheckBox3;
  
  public CheckBox CheckBox4;
  
  public HorizontalArrangement HorizontalArrangement1;
  
  public HorizontalArrangement HorizontalArrangement2;
  
  public HorizontalArrangement HorizontalArrangement3;
  
  public HorizontalArrangement HorizontalArrangement4;
  
  public HorizontalArrangement HorizontalArrangement5;
  
  public HorizontalArrangement HorizontalArrangement6;
  
  public HorizontalArrangement HorizontalArrangement7;
  
  public HorizontalArrangement HorizontalArrangement8;
  
  public Label Label1;
  
  public Label Label2;
  
  public Label Label3;
  
  public Label Label4;
  
  public Label Label5;
  
  public Label Label6;
  
  public ListPicker ListPicker1;
  
  public final ModuleMethod Sign_up$Initialize;
  
  public TextBox TextBox1;
  
  public TextBox TextBox2;
  
  public TextBox TextBox3;
  
  public TextBox TextBox4;
  
  public TextBox TextBox5;
  
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
    Lit179 = (SimpleSymbol)(new SimpleSymbol("dispatchGenericEvent")).readResolve();
    Lit178 = (SimpleSymbol)(new SimpleSymbol("dispatchEvent")).readResolve();
    Lit177 = (SimpleSymbol)(new SimpleSymbol("send-error")).readResolve();
    Lit176 = (SimpleSymbol)(new SimpleSymbol("add-to-form-do-after-creation")).readResolve();
    Lit175 = (SimpleSymbol)(new SimpleSymbol("add-to-global-vars")).readResolve();
    Lit174 = (SimpleSymbol)(new SimpleSymbol("add-to-components")).readResolve();
    Lit173 = (SimpleSymbol)(new SimpleSymbol("add-to-events")).readResolve();
    Lit172 = (SimpleSymbol)(new SimpleSymbol("add-to-global-var-environment")).readResolve();
    Lit171 = (SimpleSymbol)(new SimpleSymbol("is-bound-in-form-environment")).readResolve();
    Lit170 = (SimpleSymbol)(new SimpleSymbol("lookup-in-form-environment")).readResolve();
    Lit169 = (SimpleSymbol)(new SimpleSymbol("add-to-form-environment")).readResolve();
    Lit168 = (SimpleSymbol)(new SimpleSymbol("android-log-form")).readResolve();
    Lit167 = (SimpleSymbol)(new SimpleSymbol("get-simple-name")).readResolve();
    Lit166 = (SimpleSymbol)(new SimpleSymbol("Click")).readResolve();
    Lit165 = (SimpleSymbol)(new SimpleSymbol("Button1$Click")).readResolve();
    SimpleSymbol simpleSymbol = (SimpleSymbol)(new SimpleSymbol("text")).readResolve();
    Lit4 = simpleSymbol;
    Lit164 = PairWithPosition.make(simpleSymbol, LList.Empty, "/tmp/1624147919646_0.8026631161501641-0/youngandroidproject/../src/appinventor/ai_notifications_eoteam/GROW_game_app/Sign_up.yail", 1290318);
    Lit163 = new FString("com.google.appinventor.components.runtime.Button");
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit162 = IntNum.make(arrayOfInt);
    Lit161 = IntNum.make(20);
    arrayOfInt = new int[2];
    arrayOfInt[0] = -7352248;
    Lit160 = IntNum.make(arrayOfInt);
    Lit159 = (SimpleSymbol)(new SimpleSymbol("Button1")).readResolve();
    Lit158 = new FString("com.google.appinventor.components.runtime.Button");
    Lit157 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit156 = IntNum.make(-1005);
    Lit155 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement4")).readResolve();
    Lit154 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit153 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit152 = IntNum.make(-1100);
    Lit151 = IntNum.make(3);
    Lit150 = (SimpleSymbol)(new SimpleSymbol("AlignHorizontal")).readResolve();
    Lit149 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement3")).readResolve();
    Lit148 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit147 = new FString("com.google.appinventor.components.runtime.TextBox");
    Lit146 = (SimpleSymbol)(new SimpleSymbol("TextBox5")).readResolve();
    Lit145 = new FString("com.google.appinventor.components.runtime.TextBox");
    Lit144 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit143 = IntNum.make(-1002);
    Lit142 = IntNum.make(-1005);
    Lit141 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement6")).readResolve();
    Lit140 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit139 = new FString("com.google.appinventor.components.runtime.CheckBox");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit138 = IntNum.make(arrayOfInt);
    Lit137 = (SimpleSymbol)(new SimpleSymbol("CheckBox4")).readResolve();
    Lit136 = new FString("com.google.appinventor.components.runtime.CheckBox");
    Lit135 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit134 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement5")).readResolve();
    Lit133 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit132 = new FString("com.google.appinventor.components.runtime.CheckBox");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit131 = IntNum.make(arrayOfInt);
    Lit130 = (SimpleSymbol)(new SimpleSymbol("CheckBox3")).readResolve();
    Lit129 = new FString("com.google.appinventor.components.runtime.CheckBox");
    Lit128 = new FString("com.google.appinventor.components.runtime.CheckBox");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit127 = IntNum.make(arrayOfInt);
    Lit126 = (SimpleSymbol)(new SimpleSymbol("CheckBox2")).readResolve();
    Lit125 = new FString("com.google.appinventor.components.runtime.CheckBox");
    Lit124 = new FString("com.google.appinventor.components.runtime.CheckBox");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit123 = IntNum.make(arrayOfInt);
    Lit122 = (SimpleSymbol)(new SimpleSymbol("CheckBox1")).readResolve();
    Lit121 = new FString("com.google.appinventor.components.runtime.CheckBox");
    Lit120 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit119 = IntNum.make(arrayOfInt);
    Lit118 = (SimpleSymbol)(new SimpleSymbol("Label6")).readResolve();
    Lit117 = new FString("com.google.appinventor.components.runtime.Label");
    Lit116 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit115 = IntNum.make(-1002);
    Lit114 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement9")).readResolve();
    Lit113 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit112 = new FString("com.google.appinventor.components.runtime.ListPicker");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit111 = IntNum.make(arrayOfInt);
    Lit110 = (SimpleSymbol)(new SimpleSymbol("Shape")).readResolve();
    arrayOfInt = new int[2];
    arrayOfInt[0] = -7352248;
    Lit109 = IntNum.make(arrayOfInt);
    Lit108 = (SimpleSymbol)(new SimpleSymbol("ItemBackgroundColor")).readResolve();
    Lit107 = (SimpleSymbol)(new SimpleSymbol("ElementsFromString")).readResolve();
    arrayOfInt = new int[2];
    arrayOfInt[0] = -7352248;
    Lit106 = IntNum.make(arrayOfInt);
    Lit105 = (SimpleSymbol)(new SimpleSymbol("ListPicker1")).readResolve();
    Lit104 = new FString("com.google.appinventor.components.runtime.ListPicker");
    Lit103 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit102 = IntNum.make(-1002);
    Lit101 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement8")).readResolve();
    Lit100 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit99 = new FString("com.google.appinventor.components.runtime.TextBox");
    Lit98 = (SimpleSymbol)(new SimpleSymbol("TextBox4")).readResolve();
    Lit97 = new FString("com.google.appinventor.components.runtime.TextBox");
    Lit96 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit95 = IntNum.make(arrayOfInt);
    Lit94 = (SimpleSymbol)(new SimpleSymbol("Label5")).readResolve();
    Lit93 = new FString("com.google.appinventor.components.runtime.Label");
    Lit92 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit91 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement4")).readResolve();
    Lit90 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit89 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit88 = IntNum.make(-1002);
    Lit87 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement7")).readResolve();
    Lit86 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit85 = new FString("com.google.appinventor.components.runtime.TextBox");
    Lit84 = (SimpleSymbol)(new SimpleSymbol("TextBox3")).readResolve();
    Lit83 = new FString("com.google.appinventor.components.runtime.TextBox");
    Lit82 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit81 = IntNum.make(arrayOfInt);
    Lit80 = (SimpleSymbol)(new SimpleSymbol("Label4")).readResolve();
    Lit79 = new FString("com.google.appinventor.components.runtime.Label");
    Lit78 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit77 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement3")).readResolve();
    Lit76 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit75 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit74 = IntNum.make(-1002);
    Lit73 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement6")).readResolve();
    Lit72 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit71 = new FString("com.google.appinventor.components.runtime.TextBox");
    Lit70 = (SimpleSymbol)(new SimpleSymbol("TextBox2")).readResolve();
    Lit69 = new FString("com.google.appinventor.components.runtime.TextBox");
    Lit68 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit67 = IntNum.make(-1005);
    Lit66 = IntNum.make(-1005);
    Lit65 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement8")).readResolve();
    Lit64 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit63 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit62 = IntNum.make(arrayOfInt);
    Lit61 = (SimpleSymbol)(new SimpleSymbol("Label3")).readResolve();
    Lit60 = new FString("com.google.appinventor.components.runtime.Label");
    Lit59 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit58 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement2")).readResolve();
    Lit57 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit56 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit55 = IntNum.make(-1002);
    Lit54 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement5")).readResolve();
    Lit53 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit52 = new FString("com.google.appinventor.components.runtime.TextBox");
    Lit51 = (SimpleSymbol)(new SimpleSymbol("TextBox1")).readResolve();
    Lit50 = new FString("com.google.appinventor.components.runtime.TextBox");
    Lit49 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit48 = IntNum.make(-1004);
    Lit47 = IntNum.make(-1005);
    Lit46 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement7")).readResolve();
    Lit45 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit44 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit43 = IntNum.make(arrayOfInt);
    Lit42 = (SimpleSymbol)(new SimpleSymbol("Label2")).readResolve();
    Lit41 = new FString("com.google.appinventor.components.runtime.Label");
    Lit40 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit39 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement1")).readResolve();
    Lit38 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit37 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit36 = IntNum.make(-1005);
    Lit35 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement2")).readResolve();
    Lit34 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit33 = new FString("com.google.appinventor.components.runtime.Label");
    Lit32 = IntNum.make(-1100);
    Lit31 = (SimpleSymbol)(new SimpleSymbol("Width")).readResolve();
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit30 = IntNum.make(arrayOfInt);
    Lit29 = (SimpleSymbol)(new SimpleSymbol("TextColor")).readResolve();
    Lit28 = IntNum.make(1);
    Lit27 = (SimpleSymbol)(new SimpleSymbol("TextAlignment")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("Text")).readResolve();
    Lit25 = IntNum.make(18);
    Lit24 = (SimpleSymbol)(new SimpleSymbol("FontSize")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("FontItalic")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("FontBold")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("Label1")).readResolve();
    Lit20 = new FString("com.google.appinventor.components.runtime.Label");
    Lit19 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit18 = IntNum.make(-1005);
    Lit17 = (SimpleSymbol)(new SimpleSymbol("Height")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement1")).readResolve();
    Lit15 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit14 = (SimpleSymbol)(new SimpleSymbol("Initialize")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("Sign_up$Initialize")).readResolve();
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
    Lit3 = (SimpleSymbol)(new SimpleSymbol("AppName")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("*the-null-value*")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("getMessage")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("Sign_up")).readResolve();
  }
  
  public Sign_up() {
    ModuleInfo.register(this);
    Sign_up$frame sign_up$frame = new Sign_up$frame();
    sign_up$frame.$main = this;
    this.get$Mnsimple$Mnname = new ModuleMethod(sign_up$frame, 1, Lit167, 4097);
    this.onCreate = new ModuleMethod(sign_up$frame, 2, "onCreate", 4097);
    this.android$Mnlog$Mnform = new ModuleMethod(sign_up$frame, 3, Lit168, 4097);
    this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(sign_up$frame, 4, Lit169, 8194);
    this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(sign_up$frame, 5, Lit170, 8193);
    this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(sign_up$frame, 7, Lit171, 4097);
    this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(sign_up$frame, 8, Lit172, 8194);
    this.add$Mnto$Mnevents = new ModuleMethod(sign_up$frame, 9, Lit173, 8194);
    this.add$Mnto$Mncomponents = new ModuleMethod(sign_up$frame, 10, Lit174, 16388);
    this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(sign_up$frame, 11, Lit175, 8194);
    this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(sign_up$frame, 12, Lit176, 4097);
    this.send$Mnerror = new ModuleMethod(sign_up$frame, 13, Lit177, 4097);
    this.process$Mnexception = new ModuleMethod(sign_up$frame, 14, "process-exception", 4097);
    this.dispatchEvent = new ModuleMethod(sign_up$frame, 15, Lit178, 16388);
    this.dispatchGenericEvent = new ModuleMethod(sign_up$frame, 16, Lit179, 16388);
    this.lookup$Mnhandler = new ModuleMethod(sign_up$frame, 17, Lit180, 8194);
    ModuleMethod moduleMethod = new ModuleMethod(sign_up$frame, 18, null, 0);
    moduleMethod.setProperty("source-location", "/tmp/runtime6993135000179593734.scm:622");
    lambda$Fn1 = moduleMethod;
    this.$define = new ModuleMethod(sign_up$frame, 19, "$define", 0);
    lambda$Fn2 = new ModuleMethod(sign_up$frame, 20, null, 0);
    this.Sign_up$Initialize = new ModuleMethod(sign_up$frame, 21, Lit13, 0);
    lambda$Fn3 = new ModuleMethod(sign_up$frame, 22, null, 0);
    lambda$Fn4 = new ModuleMethod(sign_up$frame, 23, null, 0);
    lambda$Fn5 = new ModuleMethod(sign_up$frame, 24, null, 0);
    lambda$Fn6 = new ModuleMethod(sign_up$frame, 25, null, 0);
    lambda$Fn7 = new ModuleMethod(sign_up$frame, 26, null, 0);
    lambda$Fn8 = new ModuleMethod(sign_up$frame, 27, null, 0);
    lambda$Fn9 = new ModuleMethod(sign_up$frame, 28, null, 0);
    lambda$Fn10 = new ModuleMethod(sign_up$frame, 29, null, 0);
    lambda$Fn11 = new ModuleMethod(sign_up$frame, 30, null, 0);
    lambda$Fn12 = new ModuleMethod(sign_up$frame, 31, null, 0);
    lambda$Fn13 = new ModuleMethod(sign_up$frame, 32, null, 0);
    lambda$Fn14 = new ModuleMethod(sign_up$frame, 33, null, 0);
    lambda$Fn15 = new ModuleMethod(sign_up$frame, 34, null, 0);
    lambda$Fn16 = new ModuleMethod(sign_up$frame, 35, null, 0);
    lambda$Fn17 = new ModuleMethod(sign_up$frame, 36, null, 0);
    lambda$Fn18 = new ModuleMethod(sign_up$frame, 37, null, 0);
    lambda$Fn19 = new ModuleMethod(sign_up$frame, 38, null, 0);
    lambda$Fn20 = new ModuleMethod(sign_up$frame, 39, null, 0);
    lambda$Fn21 = new ModuleMethod(sign_up$frame, 40, null, 0);
    lambda$Fn22 = new ModuleMethod(sign_up$frame, 41, null, 0);
    lambda$Fn23 = new ModuleMethod(sign_up$frame, 42, null, 0);
    lambda$Fn24 = new ModuleMethod(sign_up$frame, 43, null, 0);
    lambda$Fn25 = new ModuleMethod(sign_up$frame, 44, null, 0);
    lambda$Fn26 = new ModuleMethod(sign_up$frame, 45, null, 0);
    lambda$Fn27 = new ModuleMethod(sign_up$frame, 46, null, 0);
    lambda$Fn28 = new ModuleMethod(sign_up$frame, 47, null, 0);
    lambda$Fn29 = new ModuleMethod(sign_up$frame, 48, null, 0);
    lambda$Fn30 = new ModuleMethod(sign_up$frame, 49, null, 0);
    lambda$Fn31 = new ModuleMethod(sign_up$frame, 50, null, 0);
    lambda$Fn32 = new ModuleMethod(sign_up$frame, 51, null, 0);
    lambda$Fn33 = new ModuleMethod(sign_up$frame, 52, null, 0);
    lambda$Fn34 = new ModuleMethod(sign_up$frame, 53, null, 0);
    lambda$Fn35 = new ModuleMethod(sign_up$frame, 54, null, 0);
    lambda$Fn36 = new ModuleMethod(sign_up$frame, 55, null, 0);
    lambda$Fn37 = new ModuleMethod(sign_up$frame, 56, null, 0);
    lambda$Fn38 = new ModuleMethod(sign_up$frame, 57, null, 0);
    lambda$Fn39 = new ModuleMethod(sign_up$frame, 58, null, 0);
    lambda$Fn40 = new ModuleMethod(sign_up$frame, 59, null, 0);
    lambda$Fn41 = new ModuleMethod(sign_up$frame, 60, null, 0);
    lambda$Fn42 = new ModuleMethod(sign_up$frame, 61, null, 0);
    lambda$Fn43 = new ModuleMethod(sign_up$frame, 62, null, 0);
    lambda$Fn44 = new ModuleMethod(sign_up$frame, 63, null, 0);
    lambda$Fn45 = new ModuleMethod(sign_up$frame, 64, null, 0);
    lambda$Fn46 = new ModuleMethod(sign_up$frame, 65, null, 0);
    lambda$Fn47 = new ModuleMethod(sign_up$frame, 66, null, 0);
    lambda$Fn48 = new ModuleMethod(sign_up$frame, 67, null, 0);
    lambda$Fn49 = new ModuleMethod(sign_up$frame, 68, null, 0);
    lambda$Fn50 = new ModuleMethod(sign_up$frame, 69, null, 0);
    this.Button1$Click = new ModuleMethod(sign_up$frame, 70, Lit165, 0);
  }
  
  static Object lambda10() {
    runtime.setAndCoerceProperty$Ex(Lit42, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit42, Lit26, "Name:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit42, Lit29, Lit43, Lit7);
  }
  
  static Object lambda11() {
    runtime.setAndCoerceProperty$Ex(Lit42, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit42, Lit26, "Name:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit42, Lit29, Lit43, Lit7);
  }
  
  static Object lambda12() {
    runtime.setAndCoerceProperty$Ex(Lit46, Lit17, Lit47, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit46, Lit31, Lit48, Lit7);
  }
  
  static Object lambda13() {
    runtime.setAndCoerceProperty$Ex(Lit46, Lit17, Lit47, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit46, Lit31, Lit48, Lit7);
  }
  
  static Object lambda14() {
    return runtime.setAndCoerceProperty$Ex(Lit54, Lit17, Lit55, Lit7);
  }
  
  static Object lambda15() {
    return runtime.setAndCoerceProperty$Ex(Lit54, Lit17, Lit55, Lit7);
  }
  
  static Object lambda16() {
    runtime.setAndCoerceProperty$Ex(Lit61, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit61, Lit26, "Email:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit61, Lit29, Lit62, Lit7);
  }
  
  static Object lambda17() {
    runtime.setAndCoerceProperty$Ex(Lit61, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit61, Lit26, "Email:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit61, Lit29, Lit62, Lit7);
  }
  
  static Object lambda18() {
    runtime.setAndCoerceProperty$Ex(Lit65, Lit17, Lit66, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit65, Lit31, Lit67, Lit7);
  }
  
  static Object lambda19() {
    runtime.setAndCoerceProperty$Ex(Lit65, Lit17, Lit66, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit65, Lit31, Lit67, Lit7);
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
    return runtime.setAndCoerceProperty$Ex(Lit73, Lit17, Lit74, Lit7);
  }
  
  static Object lambda21() {
    return runtime.setAndCoerceProperty$Ex(Lit73, Lit17, Lit74, Lit7);
  }
  
  static Object lambda22() {
    runtime.setAndCoerceProperty$Ex(Lit80, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit80, Lit26, "Username:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit80, Lit29, Lit81, Lit7);
  }
  
  static Object lambda23() {
    runtime.setAndCoerceProperty$Ex(Lit80, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit80, Lit26, "Username:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit80, Lit29, Lit81, Lit7);
  }
  
  static Object lambda24() {
    return runtime.setAndCoerceProperty$Ex(Lit87, Lit17, Lit88, Lit7);
  }
  
  static Object lambda25() {
    return runtime.setAndCoerceProperty$Ex(Lit87, Lit17, Lit88, Lit7);
  }
  
  static Object lambda26() {
    runtime.setAndCoerceProperty$Ex(Lit94, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit94, Lit26, "Password:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit94, Lit29, Lit95, Lit7);
  }
  
  static Object lambda27() {
    runtime.setAndCoerceProperty$Ex(Lit94, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit94, Lit26, "Password:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit94, Lit29, Lit95, Lit7);
  }
  
  static Object lambda28() {
    return runtime.setAndCoerceProperty$Ex(Lit101, Lit17, Lit102, Lit7);
  }
  
  static Object lambda29() {
    return runtime.setAndCoerceProperty$Ex(Lit101, Lit17, Lit102, Lit7);
  }
  
  static Object lambda3() {
    runtime.setAndCoerceProperty$Ex(Lit0, Lit3, "GROW_game_app", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit5, Lit6, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit8, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "Responsive", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit11, "Sign_up", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit0, Lit12, Boolean.FALSE, Lit9);
  }
  
  static Object lambda30() {
    runtime.setAndCoerceProperty$Ex(Lit105, Lit5, Lit106, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit105, Lit107, "User,Stakeholder", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit105, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit105, Lit108, Lit109, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit105, Lit110, Lit28, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit105, Lit26, "User / Stakeholder", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit105, Lit29, Lit111, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit105, Lit11, "Choose from list", Lit4);
  }
  
  static Object lambda31() {
    runtime.setAndCoerceProperty$Ex(Lit105, Lit5, Lit106, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit105, Lit107, "User,Stakeholder", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit105, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit105, Lit108, Lit109, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit105, Lit110, Lit28, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit105, Lit26, "User / Stakeholder", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit105, Lit29, Lit111, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit105, Lit11, "Choose from list", Lit4);
  }
  
  static Object lambda32() {
    return runtime.setAndCoerceProperty$Ex(Lit114, Lit17, Lit115, Lit7);
  }
  
  static Object lambda33() {
    return runtime.setAndCoerceProperty$Ex(Lit114, Lit17, Lit115, Lit7);
  }
  
  static Object lambda34() {
    runtime.setAndCoerceProperty$Ex(Lit118, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit118, Lit26, "Extra Skills:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit118, Lit29, Lit119, Lit7);
  }
  
  static Object lambda35() {
    runtime.setAndCoerceProperty$Ex(Lit118, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit118, Lit26, "Extra Skills:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit118, Lit29, Lit119, Lit7);
  }
  
  static Object lambda36() {
    runtime.setAndCoerceProperty$Ex(Lit122, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit122, Lit26, "Agriculturist", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit122, Lit29, Lit123, Lit7);
  }
  
  static Object lambda37() {
    runtime.setAndCoerceProperty$Ex(Lit122, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit122, Lit26, "Agriculturist", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit122, Lit29, Lit123, Lit7);
  }
  
  static Object lambda38() {
    runtime.setAndCoerceProperty$Ex(Lit126, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit126, Lit26, "Landscape architect", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit126, Lit29, Lit127, Lit7);
  }
  
  static Object lambda39() {
    runtime.setAndCoerceProperty$Ex(Lit126, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit126, Lit26, "Landscape architect", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit126, Lit29, Lit127, Lit7);
  }
  
  static Object lambda4() {
    return runtime.setAndCoerceProperty$Ex(Lit16, Lit17, Lit18, Lit7);
  }
  
  static Object lambda40() {
    runtime.setAndCoerceProperty$Ex(Lit130, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit130, Lit26, "Environmentalist", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit130, Lit29, Lit131, Lit7);
  }
  
  static Object lambda41() {
    runtime.setAndCoerceProperty$Ex(Lit130, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit130, Lit26, "Environmentalist", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit130, Lit29, Lit131, Lit7);
  }
  
  static Object lambda42() {
    runtime.setAndCoerceProperty$Ex(Lit137, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit137, Lit26, "Other:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit137, Lit29, Lit138, Lit7);
  }
  
  static Object lambda43() {
    runtime.setAndCoerceProperty$Ex(Lit137, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit137, Lit26, "Other:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit137, Lit29, Lit138, Lit7);
  }
  
  static Object lambda44() {
    runtime.setAndCoerceProperty$Ex(Lit141, Lit17, Lit142, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit141, Lit31, Lit143, Lit7);
  }
  
  static Object lambda45() {
    runtime.setAndCoerceProperty$Ex(Lit141, Lit17, Lit142, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit141, Lit31, Lit143, Lit7);
  }
  
  static Object lambda46() {
    runtime.setAndCoerceProperty$Ex(Lit149, Lit150, Lit151, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit149, Lit31, Lit152, Lit7);
  }
  
  static Object lambda47() {
    runtime.setAndCoerceProperty$Ex(Lit149, Lit150, Lit151, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit149, Lit31, Lit152, Lit7);
  }
  
  static Object lambda48() {
    return runtime.setAndCoerceProperty$Ex(Lit155, Lit17, Lit156, Lit7);
  }
  
  static Object lambda49() {
    return runtime.setAndCoerceProperty$Ex(Lit155, Lit17, Lit156, Lit7);
  }
  
  static Object lambda5() {
    return runtime.setAndCoerceProperty$Ex(Lit16, Lit17, Lit18, Lit7);
  }
  
  static Object lambda50() {
    runtime.setAndCoerceProperty$Ex(Lit159, Lit5, Lit160, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit159, Lit24, Lit161, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit159, Lit26, "Sign up", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit159, Lit29, Lit162, Lit7);
  }
  
  static Object lambda51() {
    runtime.setAndCoerceProperty$Ex(Lit159, Lit5, Lit160, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit159, Lit24, Lit161, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit159, Lit26, "Sign up", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit159, Lit29, Lit162, Lit7);
  }
  
  static Object lambda6() {
    runtime.setAndCoerceProperty$Ex(Lit21, Lit22, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit21, Lit23, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit21, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit21, Lit26, "Create your account", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit21, Lit27, Lit28, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit21, Lit29, Lit30, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit21, Lit31, Lit32, Lit7);
  }
  
  static Object lambda7() {
    runtime.setAndCoerceProperty$Ex(Lit21, Lit22, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit21, Lit23, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit21, Lit24, Lit25, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit21, Lit26, "Create your account", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit21, Lit27, Lit28, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit21, Lit29, Lit30, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit21, Lit31, Lit32, Lit7);
  }
  
  static Object lambda8() {
    return runtime.setAndCoerceProperty$Ex(Lit35, Lit17, Lit36, Lit7);
  }
  
  static Object lambda9() {
    return runtime.setAndCoerceProperty$Ex(Lit35, Lit17, Lit36, Lit7);
  }
  
  public void $define() {
    Language.setDefaults((Language)Scheme.getInstance());
    try {
      run();
    } catch (Exception exception) {
      androidLogForm(exception.getMessage());
      processException(exception);
    } 
    Sign_up = this;
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
    return runtime.callYailPrimitive(runtime.open$Mnanother$Mnscreen, LList.list1("Profile"), Lit164, "open another screen");
  }
  
  public Object Sign_up$Initialize() {
    runtime.setThisForm();
    return runtime.lookupGlobalVarInCurrentFormEnvironment((Symbol)Lit2, runtime.$Stthe$Mnnull$Mnvalue$St);
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
    Sign_up = null;
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
      runtime.setAndCoerceProperty$Ex(Lit0, Lit11, "Sign_up", Lit4);
      Values.writeValues(runtime.setAndCoerceProperty$Ex(Lit0, Lit12, Boolean.FALSE, Lit9), consumer);
    } else {
      addToFormDoAfterCreation(new Promise((Procedure)lambda$Fn2));
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.addToCurrentFormEnvironment((Symbol)Lit13, this.Sign_up$Initialize);
    } else {
      addToFormEnvironment((Symbol)Lit13, this.Sign_up$Initialize);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      EventDispatcher.registerEventForDelegation((HandlesEventDispatching)runtime.$Stthis$Mnform$St, "Sign_up", "Initialize");
    } else {
      addToEvents(Lit0, Lit14);
    } 
    this.VerticalArrangement1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit15, Lit16, lambda$Fn3), consumer);
    } else {
      addToComponents(Lit0, Lit19, Lit16, lambda$Fn4);
    } 
    this.Label1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit20, Lit21, lambda$Fn5), consumer);
    } else {
      addToComponents(Lit0, Lit33, Lit21, lambda$Fn6);
    } 
    this.VerticalArrangement2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit34, Lit35, lambda$Fn7), consumer);
    } else {
      addToComponents(Lit0, Lit37, Lit35, lambda$Fn8);
    } 
    this.HorizontalArrangement1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit38, Lit39, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit40, Lit39, Boolean.FALSE);
    } 
    this.Label2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit39, Lit41, Lit42, lambda$Fn9), consumer);
    } else {
      addToComponents(Lit39, Lit44, Lit42, lambda$Fn10);
    } 
    this.HorizontalArrangement7 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit39, Lit45, Lit46, lambda$Fn11), consumer);
    } else {
      addToComponents(Lit39, Lit49, Lit46, lambda$Fn12);
    } 
    this.TextBox1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit39, Lit50, Lit51, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit39, Lit52, Lit51, Boolean.FALSE);
    } 
    this.VerticalArrangement5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit53, Lit54, lambda$Fn13), consumer);
    } else {
      addToComponents(Lit0, Lit56, Lit54, lambda$Fn14);
    } 
    this.HorizontalArrangement2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit57, Lit58, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit59, Lit58, Boolean.FALSE);
    } 
    this.Label3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit58, Lit60, Lit61, lambda$Fn15), consumer);
    } else {
      addToComponents(Lit58, Lit63, Lit61, lambda$Fn16);
    } 
    this.HorizontalArrangement8 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit58, Lit64, Lit65, lambda$Fn17), consumer);
    } else {
      addToComponents(Lit58, Lit68, Lit65, lambda$Fn18);
    } 
    this.TextBox2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit58, Lit69, Lit70, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit58, Lit71, Lit70, Boolean.FALSE);
    } 
    this.VerticalArrangement6 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit72, Lit73, lambda$Fn19), consumer);
    } else {
      addToComponents(Lit0, Lit75, Lit73, lambda$Fn20);
    } 
    this.HorizontalArrangement3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit76, Lit77, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit78, Lit77, Boolean.FALSE);
    } 
    this.Label4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit77, Lit79, Lit80, lambda$Fn21), consumer);
    } else {
      addToComponents(Lit77, Lit82, Lit80, lambda$Fn22);
    } 
    this.TextBox3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit77, Lit83, Lit84, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit77, Lit85, Lit84, Boolean.FALSE);
    } 
    this.VerticalArrangement7 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit86, Lit87, lambda$Fn23), consumer);
    } else {
      addToComponents(Lit0, Lit89, Lit87, lambda$Fn24);
    } 
    this.HorizontalArrangement4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit90, Lit91, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit92, Lit91, Boolean.FALSE);
    } 
    this.Label5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit91, Lit93, Lit94, lambda$Fn25), consumer);
    } else {
      addToComponents(Lit91, Lit96, Lit94, lambda$Fn26);
    } 
    this.TextBox4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit91, Lit97, Lit98, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit91, Lit99, Lit98, Boolean.FALSE);
    } 
    this.VerticalArrangement8 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit100, Lit101, lambda$Fn27), consumer);
    } else {
      addToComponents(Lit0, Lit103, Lit101, lambda$Fn28);
    } 
    this.ListPicker1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit104, Lit105, lambda$Fn29), consumer);
    } else {
      addToComponents(Lit0, Lit112, Lit105, lambda$Fn30);
    } 
    this.VerticalArrangement9 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit113, Lit114, lambda$Fn31), consumer);
    } else {
      addToComponents(Lit0, Lit116, Lit114, lambda$Fn32);
    } 
    this.Label6 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit117, Lit118, lambda$Fn33), consumer);
    } else {
      addToComponents(Lit0, Lit120, Lit118, lambda$Fn34);
    } 
    this.CheckBox1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit121, Lit122, lambda$Fn35), consumer);
    } else {
      addToComponents(Lit0, Lit124, Lit122, lambda$Fn36);
    } 
    this.CheckBox2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit125, Lit126, lambda$Fn37), consumer);
    } else {
      addToComponents(Lit0, Lit128, Lit126, lambda$Fn38);
    } 
    this.CheckBox3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit129, Lit130, lambda$Fn39), consumer);
    } else {
      addToComponents(Lit0, Lit132, Lit130, lambda$Fn40);
    } 
    this.HorizontalArrangement5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit133, Lit134, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit135, Lit134, Boolean.FALSE);
    } 
    this.CheckBox4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit134, Lit136, Lit137, lambda$Fn41), consumer);
    } else {
      addToComponents(Lit134, Lit139, Lit137, lambda$Fn42);
    } 
    this.HorizontalArrangement6 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit134, Lit140, Lit141, lambda$Fn43), consumer);
    } else {
      addToComponents(Lit134, Lit144, Lit141, lambda$Fn44);
    } 
    this.TextBox5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit134, Lit145, Lit146, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit134, Lit147, Lit146, Boolean.FALSE);
    } 
    this.VerticalArrangement3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit148, Lit149, lambda$Fn45), consumer);
    } else {
      addToComponents(Lit0, Lit153, Lit149, lambda$Fn46);
    } 
    this.VerticalArrangement4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit149, Lit154, Lit155, lambda$Fn47), consumer);
    } else {
      addToComponents(Lit149, Lit157, Lit155, lambda$Fn48);
    } 
    this.Button1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit149, Lit158, Lit159, lambda$Fn49), consumer);
    } else {
      addToComponents(Lit149, Lit163, Lit159, lambda$Fn50);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.addToCurrentFormEnvironment((Symbol)Lit165, this.Button1$Click);
    } else {
      addToFormEnvironment((Symbol)Lit165, this.Button1$Click);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      EventDispatcher.registerEventForDelegation((HandlesEventDispatching)runtime.$Stthis$Mnform$St, "Button1", "Click");
    } else {
      addToEvents(Lit159, Lit166);
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/appinventor/ai_notifications_eoteam/GROW_game_app/Sign_up.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
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
import com.google.appinventor.components.runtime.Image;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Twitter;
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

public class Profile extends Form implements Runnable {
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final IntNum Lit100;
  
  static final FString Lit101;
  
  static final FString Lit102;
  
  static final SimpleSymbol Lit103;
  
  static final FString Lit104;
  
  static final FString Lit105;
  
  static final SimpleSymbol Lit106;
  
  static final IntNum Lit107;
  
  static final FString Lit108;
  
  static final FString Lit109;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit110;
  
  static final IntNum Lit111;
  
  static final FString Lit112;
  
  static final FString Lit113;
  
  static final SimpleSymbol Lit114;
  
  static final FString Lit115;
  
  static final FString Lit116;
  
  static final SimpleSymbol Lit117;
  
  static final IntNum Lit118;
  
  static final FString Lit119;
  
  static final SimpleSymbol Lit12;
  
  static final FString Lit120;
  
  static final SimpleSymbol Lit121;
  
  static final IntNum Lit122;
  
  static final FString Lit123;
  
  static final FString Lit124;
  
  static final SimpleSymbol Lit125;
  
  static final IntNum Lit126;
  
  static final FString Lit127;
  
  static final FString Lit128;
  
  static final SimpleSymbol Lit129;
  
  static final FString Lit13;
  
  static final SimpleSymbol Lit130;
  
  static final IntNum Lit131;
  
  static final IntNum Lit132;
  
  static final FString Lit133;
  
  static final PairWithPosition Lit134;
  
  static final SimpleSymbol Lit135;
  
  static final FString Lit136;
  
  static final SimpleSymbol Lit137;
  
  static final FString Lit138;
  
  static final FString Lit139;
  
  static final SimpleSymbol Lit14;
  
  static final SimpleSymbol Lit140;
  
  static final SimpleSymbol Lit141;
  
  static final FString Lit142;
  
  static final FString Lit143;
  
  static final SimpleSymbol Lit144;
  
  static final IntNum Lit145;
  
  static final FString Lit146;
  
  static final PairWithPosition Lit147;
  
  static final SimpleSymbol Lit148;
  
  static final FString Lit149;
  
  static final SimpleSymbol Lit15;
  
  static final SimpleSymbol Lit150;
  
  static final FString Lit151;
  
  static final FString Lit152;
  
  static final SimpleSymbol Lit153;
  
  static final FString Lit154;
  
  static final FString Lit155;
  
  static final SimpleSymbol Lit156;
  
  static final IntNum Lit157;
  
  static final FString Lit158;
  
  static final FString Lit159;
  
  static final IntNum Lit16;
  
  static final SimpleSymbol Lit160;
  
  static final FString Lit161;
  
  static final FString Lit162;
  
  static final SimpleSymbol Lit163;
  
  static final FString Lit164;
  
  static final FString Lit165;
  
  static final SimpleSymbol Lit166;
  
  static final IntNum Lit167;
  
  static final FString Lit168;
  
  static final FString Lit169;
  
  static final FString Lit17;
  
  static final SimpleSymbol Lit170;
  
  static final IntNum Lit171;
  
  static final FString Lit172;
  
  static final FString Lit173;
  
  static final SimpleSymbol Lit174;
  
  static final IntNum Lit175;
  
  static final IntNum Lit176;
  
  static final FString Lit177;
  
  static final FString Lit178;
  
  static final SimpleSymbol Lit179;
  
  static final FString Lit18;
  
  static final FString Lit180;
  
  static final FString Lit181;
  
  static final SimpleSymbol Lit182;
  
  static final FString Lit183;
  
  static final SimpleSymbol Lit184;
  
  static final SimpleSymbol Lit185;
  
  static final SimpleSymbol Lit186;
  
  static final SimpleSymbol Lit187;
  
  static final SimpleSymbol Lit188;
  
  static final SimpleSymbol Lit189;
  
  static final SimpleSymbol Lit19;
  
  static final SimpleSymbol Lit190;
  
  static final SimpleSymbol Lit191;
  
  static final SimpleSymbol Lit192;
  
  static final SimpleSymbol Lit193;
  
  static final SimpleSymbol Lit194;
  
  static final SimpleSymbol Lit195;
  
  static final SimpleSymbol Lit196;
  
  static final SimpleSymbol Lit197 = (SimpleSymbol)(new SimpleSymbol("lookup-handler")).readResolve();
  
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
  
  static final IntNum Lit30;
  
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
  
  static final SimpleSymbol Lit44;
  
  static final IntNum Lit45;
  
  static final FString Lit46;
  
  static final PairWithPosition Lit47;
  
  static final SimpleSymbol Lit48;
  
  static final SimpleSymbol Lit49;
  
  static final SimpleSymbol Lit5;
  
  static final FString Lit50;
  
  static final SimpleSymbol Lit51;
  
  static final IntNum Lit52;
  
  static final IntNum Lit53;
  
  static final FString Lit54;
  
  static final FString Lit55;
  
  static final SimpleSymbol Lit56;
  
  static final IntNum Lit57;
  
  static final FString Lit58;
  
  static final FString Lit59;
  
  static final IntNum Lit6;
  
  static final SimpleSymbol Lit60;
  
  static final IntNum Lit61;
  
  static final SimpleSymbol Lit62;
  
  static final SimpleSymbol Lit63;
  
  static final FString Lit64;
  
  static final FString Lit65;
  
  static final SimpleSymbol Lit66;
  
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
  
  static final IntNum Lit78;
  
  static final FString Lit79;
  
  static final SimpleSymbol Lit8;
  
  static final FString Lit80;
  
  static final SimpleSymbol Lit81;
  
  static final FString Lit82;
  
  static final FString Lit83;
  
  static final SimpleSymbol Lit84;
  
  static final IntNum Lit85;
  
  static final FString Lit86;
  
  static final FString Lit87;
  
  static final SimpleSymbol Lit88;
  
  static final IntNum Lit89;
  
  static final SimpleSymbol Lit9;
  
  static final FString Lit90;
  
  static final FString Lit91;
  
  static final SimpleSymbol Lit92;
  
  static final FString Lit93;
  
  static final FString Lit94;
  
  static final SimpleSymbol Lit95;
  
  static final IntNum Lit96;
  
  static final FString Lit97;
  
  static final FString Lit98;
  
  static final SimpleSymbol Lit99;
  
  public static Profile Profile;
  
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
  
  static final ModuleMethod lambda$Fn55;
  
  static final ModuleMethod lambda$Fn56;
  
  static final ModuleMethod lambda$Fn57;
  
  static final ModuleMethod lambda$Fn58;
  
  static final ModuleMethod lambda$Fn59;
  
  static final ModuleMethod lambda$Fn6;
  
  static final ModuleMethod lambda$Fn60;
  
  static final ModuleMethod lambda$Fn61;
  
  static final ModuleMethod lambda$Fn62;
  
  static final ModuleMethod lambda$Fn63;
  
  static final ModuleMethod lambda$Fn64;
  
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
  
  public Button Button4;
  
  public Button Button5;
  
  public final ModuleMethod Button5$Click;
  
  public CheckBox CheckBox1;
  
  public CheckBox CheckBox2;
  
  public CheckBox CheckBox3;
  
  public HorizontalArrangement HorizontalArrangement1;
  
  public HorizontalArrangement HorizontalArrangement2;
  
  public HorizontalArrangement HorizontalArrangement3;
  
  public HorizontalArrangement HorizontalArrangement4;
  
  public HorizontalArrangement HorizontalArrangement5;
  
  public HorizontalArrangement HorizontalArrangement6;
  
  public HorizontalArrangement HorizontalArrangement7;
  
  public HorizontalArrangement HorizontalArrangement8;
  
  public HorizontalArrangement HorizontalArrangement9;
  
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
  
  public Label Label8;
  
  public Label Label9;
  
  public Twitter Twitter1;
  
  public Twitter Twitter2;
  
  public VerticalArrangement VerticalArrangement1;
  
  public VerticalArrangement VerticalArrangement2;
  
  public VerticalArrangement VerticalArrangement3;
  
  public VerticalArrangement VerticalArrangement4;
  
  public VerticalArrangement VerticalArrangement5;
  
  public VerticalArrangement VerticalArrangement6;
  
  public VerticalArrangement VerticalArrangement7;
  
  public VerticalArrangement VerticalArrangement8;
  
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
    Lit196 = (SimpleSymbol)(new SimpleSymbol("dispatchGenericEvent")).readResolve();
    Lit195 = (SimpleSymbol)(new SimpleSymbol("dispatchEvent")).readResolve();
    Lit194 = (SimpleSymbol)(new SimpleSymbol("send-error")).readResolve();
    Lit193 = (SimpleSymbol)(new SimpleSymbol("add-to-form-do-after-creation")).readResolve();
    Lit192 = (SimpleSymbol)(new SimpleSymbol("add-to-global-vars")).readResolve();
    Lit191 = (SimpleSymbol)(new SimpleSymbol("add-to-components")).readResolve();
    Lit190 = (SimpleSymbol)(new SimpleSymbol("add-to-events")).readResolve();
    Lit189 = (SimpleSymbol)(new SimpleSymbol("add-to-global-var-environment")).readResolve();
    Lit188 = (SimpleSymbol)(new SimpleSymbol("is-bound-in-form-environment")).readResolve();
    Lit187 = (SimpleSymbol)(new SimpleSymbol("lookup-in-form-environment")).readResolve();
    Lit186 = (SimpleSymbol)(new SimpleSymbol("add-to-form-environment")).readResolve();
    Lit185 = (SimpleSymbol)(new SimpleSymbol("android-log-form")).readResolve();
    Lit184 = (SimpleSymbol)(new SimpleSymbol("get-simple-name")).readResolve();
    Lit183 = new FString("com.google.appinventor.components.runtime.Twitter");
    Lit182 = (SimpleSymbol)(new SimpleSymbol("Twitter2")).readResolve();
    Lit181 = new FString("com.google.appinventor.components.runtime.Twitter");
    Lit180 = new FString("com.google.appinventor.components.runtime.Twitter");
    Lit179 = (SimpleSymbol)(new SimpleSymbol("Twitter1")).readResolve();
    Lit178 = new FString("com.google.appinventor.components.runtime.Twitter");
    Lit177 = new FString("com.google.appinventor.components.runtime.Image");
    Lit176 = IntNum.make(-1100);
    Lit175 = IntNum.make(-1010);
    Lit174 = (SimpleSymbol)(new SimpleSymbol("Image3")).readResolve();
    Lit173 = new FString("com.google.appinventor.components.runtime.Image");
    Lit172 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit171 = IntNum.make(-1005);
    Lit170 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement7")).readResolve();
    Lit169 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit168 = new FString("com.google.appinventor.components.runtime.Button");
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = -1;
    Lit167 = IntNum.make(arrayOfInt2);
    Lit166 = (SimpleSymbol)(new SimpleSymbol("Button4")).readResolve();
    Lit165 = new FString("com.google.appinventor.components.runtime.Button");
    Lit164 = new FString("com.google.appinventor.components.runtime.CheckBox");
    Lit163 = (SimpleSymbol)(new SimpleSymbol("CheckBox3")).readResolve();
    Lit162 = new FString("com.google.appinventor.components.runtime.CheckBox");
    Lit161 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit160 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement8")).readResolve();
    Lit159 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit158 = new FString("com.google.appinventor.components.runtime.Button");
    arrayOfInt2 = new int[2];
    arrayOfInt2[0] = -1;
    Lit157 = IntNum.make(arrayOfInt2);
    Lit156 = (SimpleSymbol)(new SimpleSymbol("Button3")).readResolve();
    Lit155 = new FString("com.google.appinventor.components.runtime.Button");
    Lit154 = new FString("com.google.appinventor.components.runtime.CheckBox");
    Lit153 = (SimpleSymbol)(new SimpleSymbol("CheckBox2")).readResolve();
    Lit152 = new FString("com.google.appinventor.components.runtime.CheckBox");
    Lit151 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit150 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement7")).readResolve();
    Lit149 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit148 = (SimpleSymbol)(new SimpleSymbol("Button2$Click")).readResolve();
    SimpleSymbol simpleSymbol = (SimpleSymbol)(new SimpleSymbol("text")).readResolve();
    Lit4 = simpleSymbol;
    Lit147 = PairWithPosition.make(simpleSymbol, LList.Empty, "/tmp/1624147919646_0.8026631161501641-0/youngandroidproject/../src/appinventor/ai_notifications_eoteam/GROW_game_app/Profile.yail", 1204304);
    Lit146 = new FString("com.google.appinventor.components.runtime.Button");
    int[] arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -1;
    Lit145 = IntNum.make(arrayOfInt1);
    Lit144 = (SimpleSymbol)(new SimpleSymbol("Button2")).readResolve();
    Lit143 = new FString("com.google.appinventor.components.runtime.Button");
    Lit142 = new FString("com.google.appinventor.components.runtime.CheckBox");
    Lit141 = (SimpleSymbol)(new SimpleSymbol("Enabled")).readResolve();
    Lit140 = (SimpleSymbol)(new SimpleSymbol("CheckBox1")).readResolve();
    Lit139 = new FString("com.google.appinventor.components.runtime.CheckBox");
    Lit138 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit137 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement6")).readResolve();
    Lit136 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit135 = (SimpleSymbol)(new SimpleSymbol("Button5$Click")).readResolve();
    Lit134 = PairWithPosition.make(Lit4, LList.Empty, "/tmp/1624147919646_0.8026631161501641-0/youngandroidproject/../src/appinventor/ai_notifications_eoteam/GROW_game_app/Profile.yail", 1077327);
    Lit133 = new FString("com.google.appinventor.components.runtime.Button");
    arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -1;
    Lit132 = IntNum.make(arrayOfInt1);
    Lit131 = IntNum.make(1);
    Lit130 = (SimpleSymbol)(new SimpleSymbol("Shape")).readResolve();
    Lit129 = (SimpleSymbol)(new SimpleSymbol("Button5")).readResolve();
    Lit128 = new FString("com.google.appinventor.components.runtime.Button");
    Lit127 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit126 = IntNum.make(-1005);
    Lit125 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement4")).readResolve();
    Lit124 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit123 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -1;
    Lit122 = IntNum.make(arrayOfInt1);
    Lit121 = (SimpleSymbol)(new SimpleSymbol("Label9")).readResolve();
    Lit120 = new FString("com.google.appinventor.components.runtime.Label");
    Lit119 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -1;
    Lit118 = IntNum.make(arrayOfInt1);
    Lit117 = (SimpleSymbol)(new SimpleSymbol("Label8")).readResolve();
    Lit116 = new FString("com.google.appinventor.components.runtime.Label");
    Lit115 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit114 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement5")).readResolve();
    Lit113 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit112 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -1;
    Lit111 = IntNum.make(arrayOfInt1);
    Lit110 = (SimpleSymbol)(new SimpleSymbol("Label7")).readResolve();
    Lit109 = new FString("com.google.appinventor.components.runtime.Label");
    Lit108 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -1;
    Lit107 = IntNum.make(arrayOfInt1);
    Lit106 = (SimpleSymbol)(new SimpleSymbol("Label6")).readResolve();
    Lit105 = new FString("com.google.appinventor.components.runtime.Label");
    Lit104 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit103 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement4")).readResolve();
    Lit102 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit101 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -16776961;
    Lit100 = IntNum.make(arrayOfInt1);
    Lit99 = (SimpleSymbol)(new SimpleSymbol("Label5")).readResolve();
    Lit98 = new FString("com.google.appinventor.components.runtime.Label");
    Lit97 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -1;
    Lit96 = IntNum.make(arrayOfInt1);
    Lit95 = (SimpleSymbol)(new SimpleSymbol("Label4")).readResolve();
    Lit94 = new FString("com.google.appinventor.components.runtime.Label");
    Lit93 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit92 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement3")).readResolve();
    Lit91 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit90 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -1;
    Lit89 = IntNum.make(arrayOfInt1);
    Lit88 = (SimpleSymbol)(new SimpleSymbol("Label2")).readResolve();
    Lit87 = new FString("com.google.appinventor.components.runtime.Label");
    Lit86 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -1;
    Lit85 = IntNum.make(arrayOfInt1);
    Lit84 = (SimpleSymbol)(new SimpleSymbol("Label3")).readResolve();
    Lit83 = new FString("com.google.appinventor.components.runtime.Label");
    Lit82 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit81 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement2")).readResolve();
    Lit80 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit79 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit78 = IntNum.make(-1005);
    Lit77 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement8")).readResolve();
    Lit76 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit75 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit74 = IntNum.make(-1025);
    Lit73 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement6")).readResolve();
    Lit72 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit71 = new FString("com.google.appinventor.components.runtime.Image");
    Lit70 = (SimpleSymbol)(new SimpleSymbol("Image2")).readResolve();
    Lit69 = new FString("com.google.appinventor.components.runtime.Image");
    Lit68 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit67 = IntNum.make(-1010);
    Lit66 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement3")).readResolve();
    Lit65 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit64 = new FString("com.google.appinventor.components.runtime.Image");
    Lit63 = (SimpleSymbol)(new SimpleSymbol("ScalePictureToFit")).readResolve();
    Lit62 = (SimpleSymbol)(new SimpleSymbol("Picture")).readResolve();
    Lit61 = IntNum.make(80);
    Lit60 = (SimpleSymbol)(new SimpleSymbol("Image1")).readResolve();
    Lit59 = new FString("com.google.appinventor.components.runtime.Image");
    Lit58 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit57 = IntNum.make(-1025);
    Lit56 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement5")).readResolve();
    Lit55 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit54 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit53 = IntNum.make(-1100);
    Lit52 = IntNum.make(-1015);
    Lit51 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement9")).readResolve();
    Lit50 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit49 = (SimpleSymbol)(new SimpleSymbol("Click")).readResolve();
    Lit48 = (SimpleSymbol)(new SimpleSymbol("Button1$Click")).readResolve();
    Lit47 = PairWithPosition.make(Lit4, LList.Empty, "/tmp/1624147919646_0.8026631161501641-0/youngandroidproject/../src/appinventor/ai_notifications_eoteam/GROW_game_app/Profile.yail", 286800);
    Lit46 = new FString("com.google.appinventor.components.runtime.Button");
    Lit45 = IntNum.make(-1010);
    Lit44 = (SimpleSymbol)(new SimpleSymbol("Image")).readResolve();
    Lit43 = IntNum.make(-1005);
    Lit42 = (SimpleSymbol)(new SimpleSymbol("Button1")).readResolve();
    Lit41 = new FString("com.google.appinventor.components.runtime.Button");
    Lit40 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit39 = IntNum.make(-1005);
    Lit38 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement2")).readResolve();
    Lit37 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit36 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -1;
    Lit35 = IntNum.make(arrayOfInt1);
    Lit34 = (SimpleSymbol)(new SimpleSymbol("TextColor")).readResolve();
    Lit33 = (SimpleSymbol)(new SimpleSymbol("Text")).readResolve();
    Lit32 = IntNum.make(18);
    Lit31 = (SimpleSymbol)(new SimpleSymbol("FontSize")).readResolve();
    arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -7352248;
    Lit30 = IntNum.make(arrayOfInt1);
    Lit29 = (SimpleSymbol)(new SimpleSymbol("Label1")).readResolve();
    Lit28 = new FString("com.google.appinventor.components.runtime.Label");
    Lit27 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit26 = IntNum.make(-1100);
    Lit25 = (SimpleSymbol)(new SimpleSymbol("Width")).readResolve();
    Lit24 = IntNum.make(-1010);
    Lit23 = IntNum.make(2);
    Lit22 = (SimpleSymbol)(new SimpleSymbol("AlignVertical")).readResolve();
    Lit21 = IntNum.make(3);
    Lit20 = (SimpleSymbol)(new SimpleSymbol("AlignHorizontal")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement1")).readResolve();
    Lit18 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
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
    arrayOfInt1 = new int[2];
    arrayOfInt1[0] = -15227642;
    Lit6 = IntNum.make(arrayOfInt1);
    Lit5 = (SimpleSymbol)(new SimpleSymbol("BackgroundColor")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("AppName")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("*the-null-value*")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("getMessage")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("Profile")).readResolve();
  }
  
  public Profile() {
    ModuleInfo.register(this);
    Profile$frame profile$frame = new Profile$frame();
    profile$frame.$main = this;
    this.get$Mnsimple$Mnname = new ModuleMethod(profile$frame, 1, Lit184, 4097);
    this.onCreate = new ModuleMethod(profile$frame, 2, "onCreate", 4097);
    this.android$Mnlog$Mnform = new ModuleMethod(profile$frame, 3, Lit185, 4097);
    this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(profile$frame, 4, Lit186, 8194);
    this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(profile$frame, 5, Lit187, 8193);
    this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(profile$frame, 7, Lit188, 4097);
    this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(profile$frame, 8, Lit189, 8194);
    this.add$Mnto$Mnevents = new ModuleMethod(profile$frame, 9, Lit190, 8194);
    this.add$Mnto$Mncomponents = new ModuleMethod(profile$frame, 10, Lit191, 16388);
    this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(profile$frame, 11, Lit192, 8194);
    this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(profile$frame, 12, Lit193, 4097);
    this.send$Mnerror = new ModuleMethod(profile$frame, 13, Lit194, 4097);
    this.process$Mnexception = new ModuleMethod(profile$frame, 14, "process-exception", 4097);
    this.dispatchEvent = new ModuleMethod(profile$frame, 15, Lit195, 16388);
    this.dispatchGenericEvent = new ModuleMethod(profile$frame, 16, Lit196, 16388);
    this.lookup$Mnhandler = new ModuleMethod(profile$frame, 17, Lit197, 8194);
    ModuleMethod moduleMethod = new ModuleMethod(profile$frame, 18, null, 0);
    moduleMethod.setProperty("source-location", "/tmp/runtime6993135000179593734.scm:622");
    lambda$Fn1 = moduleMethod;
    this.$define = new ModuleMethod(profile$frame, 19, "$define", 0);
    lambda$Fn2 = new ModuleMethod(profile$frame, 20, null, 0);
    lambda$Fn3 = new ModuleMethod(profile$frame, 21, null, 0);
    lambda$Fn4 = new ModuleMethod(profile$frame, 22, null, 0);
    lambda$Fn5 = new ModuleMethod(profile$frame, 23, null, 0);
    lambda$Fn6 = new ModuleMethod(profile$frame, 24, null, 0);
    lambda$Fn7 = new ModuleMethod(profile$frame, 25, null, 0);
    lambda$Fn8 = new ModuleMethod(profile$frame, 26, null, 0);
    lambda$Fn9 = new ModuleMethod(profile$frame, 27, null, 0);
    lambda$Fn10 = new ModuleMethod(profile$frame, 28, null, 0);
    lambda$Fn11 = new ModuleMethod(profile$frame, 29, null, 0);
    lambda$Fn12 = new ModuleMethod(profile$frame, 30, null, 0);
    this.Button1$Click = new ModuleMethod(profile$frame, 31, Lit48, 0);
    lambda$Fn13 = new ModuleMethod(profile$frame, 32, null, 0);
    lambda$Fn14 = new ModuleMethod(profile$frame, 33, null, 0);
    lambda$Fn15 = new ModuleMethod(profile$frame, 34, null, 0);
    lambda$Fn16 = new ModuleMethod(profile$frame, 35, null, 0);
    lambda$Fn17 = new ModuleMethod(profile$frame, 36, null, 0);
    lambda$Fn18 = new ModuleMethod(profile$frame, 37, null, 0);
    lambda$Fn19 = new ModuleMethod(profile$frame, 38, null, 0);
    lambda$Fn20 = new ModuleMethod(profile$frame, 39, null, 0);
    lambda$Fn21 = new ModuleMethod(profile$frame, 40, null, 0);
    lambda$Fn22 = new ModuleMethod(profile$frame, 41, null, 0);
    lambda$Fn23 = new ModuleMethod(profile$frame, 42, null, 0);
    lambda$Fn24 = new ModuleMethod(profile$frame, 43, null, 0);
    lambda$Fn25 = new ModuleMethod(profile$frame, 44, null, 0);
    lambda$Fn26 = new ModuleMethod(profile$frame, 45, null, 0);
    lambda$Fn27 = new ModuleMethod(profile$frame, 46, null, 0);
    lambda$Fn28 = new ModuleMethod(profile$frame, 47, null, 0);
    lambda$Fn29 = new ModuleMethod(profile$frame, 48, null, 0);
    lambda$Fn30 = new ModuleMethod(profile$frame, 49, null, 0);
    lambda$Fn31 = new ModuleMethod(profile$frame, 50, null, 0);
    lambda$Fn32 = new ModuleMethod(profile$frame, 51, null, 0);
    lambda$Fn33 = new ModuleMethod(profile$frame, 52, null, 0);
    lambda$Fn34 = new ModuleMethod(profile$frame, 53, null, 0);
    lambda$Fn35 = new ModuleMethod(profile$frame, 54, null, 0);
    lambda$Fn36 = new ModuleMethod(profile$frame, 55, null, 0);
    lambda$Fn37 = new ModuleMethod(profile$frame, 56, null, 0);
    lambda$Fn38 = new ModuleMethod(profile$frame, 57, null, 0);
    lambda$Fn39 = new ModuleMethod(profile$frame, 58, null, 0);
    lambda$Fn40 = new ModuleMethod(profile$frame, 59, null, 0);
    lambda$Fn41 = new ModuleMethod(profile$frame, 60, null, 0);
    lambda$Fn42 = new ModuleMethod(profile$frame, 61, null, 0);
    lambda$Fn43 = new ModuleMethod(profile$frame, 62, null, 0);
    lambda$Fn44 = new ModuleMethod(profile$frame, 63, null, 0);
    lambda$Fn45 = new ModuleMethod(profile$frame, 64, null, 0);
    lambda$Fn46 = new ModuleMethod(profile$frame, 65, null, 0);
    this.Button5$Click = new ModuleMethod(profile$frame, 66, Lit135, 0);
    lambda$Fn47 = new ModuleMethod(profile$frame, 67, null, 0);
    lambda$Fn48 = new ModuleMethod(profile$frame, 68, null, 0);
    lambda$Fn49 = new ModuleMethod(profile$frame, 69, null, 0);
    lambda$Fn50 = new ModuleMethod(profile$frame, 70, null, 0);
    lambda$Fn51 = new ModuleMethod(profile$frame, 71, null, 0);
    lambda$Fn52 = new ModuleMethod(profile$frame, 72, null, 0);
    this.Button2$Click = new ModuleMethod(profile$frame, 73, Lit148, 0);
    lambda$Fn53 = new ModuleMethod(profile$frame, 74, null, 0);
    lambda$Fn54 = new ModuleMethod(profile$frame, 75, null, 0);
    lambda$Fn55 = new ModuleMethod(profile$frame, 76, null, 0);
    lambda$Fn56 = new ModuleMethod(profile$frame, 77, null, 0);
    lambda$Fn57 = new ModuleMethod(profile$frame, 78, null, 0);
    lambda$Fn58 = new ModuleMethod(profile$frame, 79, null, 0);
    lambda$Fn59 = new ModuleMethod(profile$frame, 80, null, 0);
    lambda$Fn60 = new ModuleMethod(profile$frame, 81, null, 0);
    lambda$Fn61 = new ModuleMethod(profile$frame, 82, null, 0);
    lambda$Fn62 = new ModuleMethod(profile$frame, 83, null, 0);
    lambda$Fn63 = new ModuleMethod(profile$frame, 84, null, 0);
    lambda$Fn64 = new ModuleMethod(profile$frame, 85, null, 0);
  }
  
  static Object lambda10() {
    return runtime.setAndCoerceProperty$Ex(Lit38, Lit25, Lit39, Lit7);
  }
  
  static Object lambda11() {
    return runtime.setAndCoerceProperty$Ex(Lit38, Lit25, Lit39, Lit7);
  }
  
  static Object lambda12() {
    runtime.setAndCoerceProperty$Ex(Lit42, Lit15, Lit43, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit42, Lit44, "trophie.png", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit42, Lit25, Lit45, Lit7);
  }
  
  static Object lambda13() {
    runtime.setAndCoerceProperty$Ex(Lit42, Lit15, Lit43, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit42, Lit44, "trophie.png", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit42, Lit25, Lit45, Lit7);
  }
  
  static Object lambda14() {
    runtime.setAndCoerceProperty$Ex(Lit51, Lit15, Lit52, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit51, Lit25, Lit53, Lit7);
  }
  
  static Object lambda15() {
    runtime.setAndCoerceProperty$Ex(Lit51, Lit15, Lit52, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit51, Lit25, Lit53, Lit7);
  }
  
  static Object lambda16() {
    return runtime.setAndCoerceProperty$Ex(Lit56, Lit25, Lit57, Lit7);
  }
  
  static Object lambda17() {
    return runtime.setAndCoerceProperty$Ex(Lit56, Lit25, Lit57, Lit7);
  }
  
  static Object lambda18() {
    runtime.setAndCoerceProperty$Ex(Lit60, Lit15, Lit61, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit60, Lit62, "kon.png", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit60, Lit63, Boolean.TRUE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit60, Lit25, Lit61, Lit7);
  }
  
  static Object lambda19() {
    runtime.setAndCoerceProperty$Ex(Lit60, Lit15, Lit61, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit60, Lit62, "kon.png", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit60, Lit63, Boolean.TRUE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit60, Lit25, Lit61, Lit7);
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
    return runtime.setAndCoerceProperty$Ex(Lit66, Lit15, Lit67, Lit7);
  }
  
  static Object lambda21() {
    return runtime.setAndCoerceProperty$Ex(Lit66, Lit15, Lit67, Lit7);
  }
  
  static Object lambda22() {
    runtime.setAndCoerceProperty$Ex(Lit70, Lit15, Lit61, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit70, Lit62, "konav.png", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit70, Lit63, Boolean.TRUE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit70, Lit25, Lit61, Lit7);
  }
  
  static Object lambda23() {
    runtime.setAndCoerceProperty$Ex(Lit70, Lit15, Lit61, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit70, Lit62, "konav.png", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit70, Lit63, Boolean.TRUE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit70, Lit25, Lit61, Lit7);
  }
  
  static Object lambda24() {
    return runtime.setAndCoerceProperty$Ex(Lit73, Lit25, Lit74, Lit7);
  }
  
  static Object lambda25() {
    return runtime.setAndCoerceProperty$Ex(Lit73, Lit25, Lit74, Lit7);
  }
  
  static Object lambda26() {
    return runtime.setAndCoerceProperty$Ex(Lit77, Lit15, Lit78, Lit7);
  }
  
  static Object lambda27() {
    return runtime.setAndCoerceProperty$Ex(Lit77, Lit15, Lit78, Lit7);
  }
  
  static Object lambda28() {
    runtime.setAndCoerceProperty$Ex(Lit84, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit84, Lit33, "Name:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit84, Lit34, Lit85, Lit7);
  }
  
  static Object lambda29() {
    runtime.setAndCoerceProperty$Ex(Lit84, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit84, Lit33, "Name:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit84, Lit34, Lit85, Lit7);
  }
  
  static Object lambda3() {
    runtime.setAndCoerceProperty$Ex(Lit0, Lit3, "GROW_game_app", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit5, Lit6, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit8, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "Responsive", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit11, "Profile", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit0, Lit12, Boolean.FALSE, Lit9);
  }
  
  static Object lambda30() {
    runtime.setAndCoerceProperty$Ex(Lit88, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit88, Lit33, "Konstantina Bantouvaki", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit88, Lit34, Lit89, Lit7);
  }
  
  static Object lambda31() {
    runtime.setAndCoerceProperty$Ex(Lit88, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit88, Lit33, "Konstantina Bantouvaki", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit88, Lit34, Lit89, Lit7);
  }
  
  static Object lambda32() {
    runtime.setAndCoerceProperty$Ex(Lit95, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit95, Lit33, "Email:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit95, Lit34, Lit96, Lit7);
  }
  
  static Object lambda33() {
    runtime.setAndCoerceProperty$Ex(Lit95, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit95, Lit33, "Email:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit95, Lit34, Lit96, Lit7);
  }
  
  static Object lambda34() {
    runtime.setAndCoerceProperty$Ex(Lit99, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit99, Lit33, "kb@gmail.com", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit99, Lit34, Lit100, Lit7);
  }
  
  static Object lambda35() {
    runtime.setAndCoerceProperty$Ex(Lit99, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit99, Lit33, "kb@gmail.com", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit99, Lit34, Lit100, Lit7);
  }
  
  static Object lambda36() {
    runtime.setAndCoerceProperty$Ex(Lit106, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit106, Lit33, "Location:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit106, Lit34, Lit107, Lit7);
  }
  
  static Object lambda37() {
    runtime.setAndCoerceProperty$Ex(Lit106, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit106, Lit33, "Location:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit106, Lit34, Lit107, Lit7);
  }
  
  static Object lambda38() {
    runtime.setAndCoerceProperty$Ex(Lit110, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit110, Lit33, "Athens", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit110, Lit34, Lit111, Lit7);
  }
  
  static Object lambda39() {
    runtime.setAndCoerceProperty$Ex(Lit110, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit110, Lit33, "Athens", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit110, Lit34, Lit111, Lit7);
  }
  
  static Object lambda4() {
    return runtime.setAndCoerceProperty$Ex(Lit14, Lit15, Lit16, Lit7);
  }
  
  static Object lambda40() {
    runtime.setAndCoerceProperty$Ex(Lit117, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit117, Lit33, "Skills:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit117, Lit34, Lit118, Lit7);
  }
  
  static Object lambda41() {
    runtime.setAndCoerceProperty$Ex(Lit117, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit117, Lit33, "Skills:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit117, Lit34, Lit118, Lit7);
  }
  
  static Object lambda42() {
    runtime.setAndCoerceProperty$Ex(Lit121, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit121, Lit33, "Geographer", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit121, Lit34, Lit122, Lit7);
  }
  
  static Object lambda43() {
    runtime.setAndCoerceProperty$Ex(Lit121, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit121, Lit33, "Geographer", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit121, Lit34, Lit122, Lit7);
  }
  
  static Object lambda44() {
    return runtime.setAndCoerceProperty$Ex(Lit125, Lit15, Lit126, Lit7);
  }
  
  static Object lambda45() {
    return runtime.setAndCoerceProperty$Ex(Lit125, Lit15, Lit126, Lit7);
  }
  
  static Object lambda46() {
    runtime.setAndCoerceProperty$Ex(Lit129, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit129, Lit130, Lit131, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit129, Lit33, "Missions:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit129, Lit34, Lit132, Lit7);
  }
  
  static Object lambda47() {
    runtime.setAndCoerceProperty$Ex(Lit129, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit129, Lit130, Lit131, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit129, Lit33, "Missions:", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit129, Lit34, Lit132, Lit7);
  }
  
  static Object lambda48() {
    return runtime.setAndCoerceProperty$Ex(Lit137, Lit22, Lit23, Lit7);
  }
  
  static Object lambda49() {
    return runtime.setAndCoerceProperty$Ex(Lit137, Lit22, Lit23, Lit7);
  }
  
  static Object lambda5() {
    return runtime.setAndCoerceProperty$Ex(Lit14, Lit15, Lit16, Lit7);
  }
  
  static Object lambda50() {
    runtime.setAndCoerceProperty$Ex(Lit140, Lit141, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit140, Lit31, Lit32, Lit7);
  }
  
  static Object lambda51() {
    runtime.setAndCoerceProperty$Ex(Lit140, Lit141, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit140, Lit31, Lit32, Lit7);
  }
  
  static Object lambda52() {
    runtime.setAndCoerceProperty$Ex(Lit144, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit144, Lit130, Lit131, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit144, Lit33, "Mission 1", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit144, Lit34, Lit145, Lit7);
  }
  
  static Object lambda53() {
    runtime.setAndCoerceProperty$Ex(Lit144, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit144, Lit130, Lit131, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit144, Lit33, "Mission 1", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit144, Lit34, Lit145, Lit7);
  }
  
  static Object lambda54() {
    runtime.setAndCoerceProperty$Ex(Lit153, Lit141, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit153, Lit31, Lit32, Lit7);
  }
  
  static Object lambda55() {
    runtime.setAndCoerceProperty$Ex(Lit153, Lit141, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit153, Lit31, Lit32, Lit7);
  }
  
  static Object lambda56() {
    runtime.setAndCoerceProperty$Ex(Lit156, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit156, Lit130, Lit131, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit156, Lit33, "Mission 2", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit156, Lit34, Lit157, Lit7);
  }
  
  static Object lambda57() {
    runtime.setAndCoerceProperty$Ex(Lit156, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit156, Lit130, Lit131, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit156, Lit33, "Mission 2", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit156, Lit34, Lit157, Lit7);
  }
  
  static Object lambda58() {
    runtime.setAndCoerceProperty$Ex(Lit163, Lit141, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit163, Lit31, Lit32, Lit7);
  }
  
  static Object lambda59() {
    runtime.setAndCoerceProperty$Ex(Lit163, Lit141, Boolean.FALSE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit163, Lit31, Lit32, Lit7);
  }
  
  static Object lambda6() {
    runtime.setAndCoerceProperty$Ex(Lit19, Lit20, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit19, Lit22, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit19, Lit15, Lit24, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit19, Lit25, Lit26, Lit7);
  }
  
  static Object lambda60() {
    runtime.setAndCoerceProperty$Ex(Lit166, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit166, Lit130, Lit131, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit166, Lit33, "Mission 3", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit166, Lit34, Lit167, Lit7);
  }
  
  static Object lambda61() {
    runtime.setAndCoerceProperty$Ex(Lit166, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit166, Lit130, Lit131, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit166, Lit33, "Mission 3", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit166, Lit34, Lit167, Lit7);
  }
  
  static Object lambda62() {
    return runtime.setAndCoerceProperty$Ex(Lit170, Lit15, Lit171, Lit7);
  }
  
  static Object lambda63() {
    return runtime.setAndCoerceProperty$Ex(Lit170, Lit15, Lit171, Lit7);
  }
  
  static Object lambda64() {
    runtime.setAndCoerceProperty$Ex(Lit174, Lit15, Lit175, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit174, Lit62, "social.png", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit174, Lit63, Boolean.TRUE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit174, Lit25, Lit176, Lit7);
  }
  
  static Object lambda65() {
    runtime.setAndCoerceProperty$Ex(Lit174, Lit15, Lit175, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit174, Lit62, "social.png", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit174, Lit63, Boolean.TRUE, Lit9);
    return runtime.setAndCoerceProperty$Ex(Lit174, Lit25, Lit176, Lit7);
  }
  
  static Object lambda7() {
    runtime.setAndCoerceProperty$Ex(Lit19, Lit20, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit19, Lit22, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit19, Lit15, Lit24, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit19, Lit25, Lit26, Lit7);
  }
  
  static Object lambda8() {
    runtime.setAndCoerceProperty$Ex(Lit29, Lit5, Lit30, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit29, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit29, Lit33, "Your Profile", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit29, Lit34, Lit35, Lit7);
  }
  
  static Object lambda9() {
    runtime.setAndCoerceProperty$Ex(Lit29, Lit5, Lit30, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit29, Lit31, Lit32, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit29, Lit33, "Your Profile", Lit4);
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
    Profile = this;
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
    return runtime.callYailPrimitive(runtime.open$Mnanother$Mnscreen, LList.list1("Standings"), Lit47, "open another screen");
  }
  
  public Object Button2$Click() {
    runtime.setThisForm();
    return runtime.callYailPrimitive(runtime.open$Mnanother$Mnscreen, LList.list1("Mission_1"), Lit147, "open another screen");
  }
  
  public Object Button5$Click() {
    runtime.setThisForm();
    return runtime.callYailPrimitive(runtime.open$Mnanother$Mnscreen, LList.list1("Missions"), Lit134, "open another screen");
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
    Profile = null;
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
      runtime.setAndCoerceProperty$Ex(Lit0, Lit11, "Profile", Lit4);
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
    this.HorizontalArrangement1 = null;
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
    this.VerticalArrangement2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit19, Lit37, Lit38, lambda$Fn9), consumer);
    } else {
      addToComponents(Lit19, Lit40, Lit38, lambda$Fn10);
    } 
    this.Button1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit19, Lit41, Lit42, lambda$Fn11), consumer);
    } else {
      addToComponents(Lit19, Lit46, Lit42, lambda$Fn12);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.addToCurrentFormEnvironment((Symbol)Lit48, this.Button1$Click);
    } else {
      addToFormEnvironment((Symbol)Lit48, this.Button1$Click);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      EventDispatcher.registerEventForDelegation((HandlesEventDispatching)runtime.$Stthis$Mnform$St, "Button1", "Click");
    } else {
      addToEvents(Lit42, Lit49);
    } 
    this.HorizontalArrangement9 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit50, Lit51, lambda$Fn13), consumer);
    } else {
      addToComponents(Lit0, Lit54, Lit51, lambda$Fn14);
    } 
    this.VerticalArrangement5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit51, Lit55, Lit56, lambda$Fn15), consumer);
    } else {
      addToComponents(Lit51, Lit58, Lit56, lambda$Fn16);
    } 
    this.Image1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit51, Lit59, Lit60, lambda$Fn17), consumer);
    } else {
      addToComponents(Lit51, Lit64, Lit60, lambda$Fn18);
    } 
    this.VerticalArrangement3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit51, Lit65, Lit66, lambda$Fn19), consumer);
    } else {
      addToComponents(Lit51, Lit68, Lit66, lambda$Fn20);
    } 
    this.Image2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit51, Lit69, Lit70, lambda$Fn21), consumer);
    } else {
      addToComponents(Lit51, Lit71, Lit70, lambda$Fn22);
    } 
    this.VerticalArrangement6 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit51, Lit72, Lit73, lambda$Fn23), consumer);
    } else {
      addToComponents(Lit51, Lit75, Lit73, lambda$Fn24);
    } 
    this.VerticalArrangement8 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit76, Lit77, lambda$Fn25), consumer);
    } else {
      addToComponents(Lit0, Lit79, Lit77, lambda$Fn26);
    } 
    this.HorizontalArrangement2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit80, Lit81, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit82, Lit81, Boolean.FALSE);
    } 
    this.Label3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit81, Lit83, Lit84, lambda$Fn27), consumer);
    } else {
      addToComponents(Lit81, Lit86, Lit84, lambda$Fn28);
    } 
    this.Label2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit81, Lit87, Lit88, lambda$Fn29), consumer);
    } else {
      addToComponents(Lit81, Lit90, Lit88, lambda$Fn30);
    } 
    this.HorizontalArrangement3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit91, Lit92, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit93, Lit92, Boolean.FALSE);
    } 
    this.Label4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit92, Lit94, Lit95, lambda$Fn31), consumer);
    } else {
      addToComponents(Lit92, Lit97, Lit95, lambda$Fn32);
    } 
    this.Label5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit92, Lit98, Lit99, lambda$Fn33), consumer);
    } else {
      addToComponents(Lit92, Lit101, Lit99, lambda$Fn34);
    } 
    this.HorizontalArrangement4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit102, Lit103, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit104, Lit103, Boolean.FALSE);
    } 
    this.Label6 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit103, Lit105, Lit106, lambda$Fn35), consumer);
    } else {
      addToComponents(Lit103, Lit108, Lit106, lambda$Fn36);
    } 
    this.Label7 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit103, Lit109, Lit110, lambda$Fn37), consumer);
    } else {
      addToComponents(Lit103, Lit112, Lit110, lambda$Fn38);
    } 
    this.HorizontalArrangement5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit113, Lit114, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit115, Lit114, Boolean.FALSE);
    } 
    this.Label8 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit114, Lit116, Lit117, lambda$Fn39), consumer);
    } else {
      addToComponents(Lit114, Lit119, Lit117, lambda$Fn40);
    } 
    this.Label9 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit114, Lit120, Lit121, lambda$Fn41), consumer);
    } else {
      addToComponents(Lit114, Lit123, Lit121, lambda$Fn42);
    } 
    this.VerticalArrangement4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit124, Lit125, lambda$Fn43), consumer);
    } else {
      addToComponents(Lit0, Lit127, Lit125, lambda$Fn44);
    } 
    this.Button5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit128, Lit129, lambda$Fn45), consumer);
    } else {
      addToComponents(Lit0, Lit133, Lit129, lambda$Fn46);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.addToCurrentFormEnvironment((Symbol)Lit135, this.Button5$Click);
    } else {
      addToFormEnvironment((Symbol)Lit135, this.Button5$Click);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      EventDispatcher.registerEventForDelegation((HandlesEventDispatching)runtime.$Stthis$Mnform$St, "Button5", "Click");
    } else {
      addToEvents(Lit129, Lit49);
    } 
    this.HorizontalArrangement6 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit136, Lit137, lambda$Fn47), consumer);
    } else {
      addToComponents(Lit0, Lit138, Lit137, lambda$Fn48);
    } 
    this.CheckBox1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit137, Lit139, Lit140, lambda$Fn49), consumer);
    } else {
      addToComponents(Lit137, Lit142, Lit140, lambda$Fn50);
    } 
    this.Button2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit137, Lit143, Lit144, lambda$Fn51), consumer);
    } else {
      addToComponents(Lit137, Lit146, Lit144, lambda$Fn52);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.addToCurrentFormEnvironment((Symbol)Lit148, this.Button2$Click);
    } else {
      addToFormEnvironment((Symbol)Lit148, this.Button2$Click);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      EventDispatcher.registerEventForDelegation((HandlesEventDispatching)runtime.$Stthis$Mnform$St, "Button2", "Click");
    } else {
      addToEvents(Lit144, Lit49);
    } 
    this.HorizontalArrangement7 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit149, Lit150, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit151, Lit150, Boolean.FALSE);
    } 
    this.CheckBox2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit150, Lit152, Lit153, lambda$Fn53), consumer);
    } else {
      addToComponents(Lit150, Lit154, Lit153, lambda$Fn54);
    } 
    this.Button3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit150, Lit155, Lit156, lambda$Fn55), consumer);
    } else {
      addToComponents(Lit150, Lit158, Lit156, lambda$Fn56);
    } 
    this.HorizontalArrangement8 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit159, Lit160, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit161, Lit160, Boolean.FALSE);
    } 
    this.CheckBox3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit160, Lit162, Lit163, lambda$Fn57), consumer);
    } else {
      addToComponents(Lit160, Lit164, Lit163, lambda$Fn58);
    } 
    this.Button4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit160, Lit165, Lit166, lambda$Fn59), consumer);
    } else {
      addToComponents(Lit160, Lit168, Lit166, lambda$Fn60);
    } 
    this.VerticalArrangement7 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit169, Lit170, lambda$Fn61), consumer);
    } else {
      addToComponents(Lit0, Lit172, Lit170, lambda$Fn62);
    } 
    this.Image3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit173, Lit174, lambda$Fn63), consumer);
    } else {
      addToComponents(Lit0, Lit177, Lit174, lambda$Fn64);
    } 
    this.Twitter1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit178, Lit179, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit180, Lit179, Boolean.FALSE);
    } 
    this.Twitter2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit181, Lit182, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit0, Lit183, Lit182, Boolean.FALSE);
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/appinventor/ai_notifications_eoteam/GROW_game_app/Profile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
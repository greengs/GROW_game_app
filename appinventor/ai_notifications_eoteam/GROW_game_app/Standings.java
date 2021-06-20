package appinventor.ai_notifications_eoteam.GROW_game_app;

import android.os.Bundle;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TableArrangement;
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
import gnu.math.DFloNum;
import gnu.math.IntNum;
import kawa.lang.Promise;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.require;

public class Standings extends Form implements Runnable {
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final FString Lit100;
  
  static final FString Lit101;
  
  static final SimpleSymbol Lit102;
  
  static final DFloNum Lit103;
  
  static final IntNum Lit104;
  
  static final FString Lit105;
  
  static final FString Lit106;
  
  static final SimpleSymbol Lit107;
  
  static final IntNum Lit108;
  
  static final FString Lit109;
  
  static final SimpleSymbol Lit11;
  
  static final FString Lit110;
  
  static final SimpleSymbol Lit111;
  
  static final IntNum Lit112;
  
  static final FString Lit113;
  
  static final FString Lit114;
  
  static final SimpleSymbol Lit115;
  
  static final IntNum Lit116;
  
  static final FString Lit117;
  
  static final FString Lit118;
  
  static final SimpleSymbol Lit119;
  
  static final SimpleSymbol Lit12;
  
  static final IntNum Lit120;
  
  static final FString Lit121;
  
  static final FString Lit122;
  
  static final SimpleSymbol Lit123;
  
  static final IntNum Lit124;
  
  static final FString Lit125;
  
  static final FString Lit126;
  
  static final SimpleSymbol Lit127;
  
  static final IntNum Lit128;
  
  static final FString Lit129;
  
  static final FString Lit13;
  
  static final FString Lit130;
  
  static final SimpleSymbol Lit131;
  
  static final IntNum Lit132;
  
  static final FString Lit133;
  
  static final FString Lit134;
  
  static final SimpleSymbol Lit135;
  
  static final IntNum Lit136;
  
  static final IntNum Lit137;
  
  static final FString Lit138;
  
  static final FString Lit139;
  
  static final SimpleSymbol Lit14;
  
  static final SimpleSymbol Lit140;
  
  static final IntNum Lit141;
  
  static final FString Lit142;
  
  static final FString Lit143;
  
  static final SimpleSymbol Lit144;
  
  static final IntNum Lit145;
  
  static final FString Lit146;
  
  static final FString Lit147;
  
  static final SimpleSymbol Lit148;
  
  static final IntNum Lit149;
  
  static final SimpleSymbol Lit15;
  
  static final FString Lit150;
  
  static final FString Lit151;
  
  static final SimpleSymbol Lit152;
  
  static final IntNum Lit153;
  
  static final FString Lit154;
  
  static final FString Lit155;
  
  static final SimpleSymbol Lit156;
  
  static final IntNum Lit157;
  
  static final FString Lit158;
  
  static final FString Lit159;
  
  static final IntNum Lit16;
  
  static final SimpleSymbol Lit160;
  
  static final IntNum Lit161;
  
  static final FString Lit162;
  
  static final SimpleSymbol Lit163;
  
  static final SimpleSymbol Lit164;
  
  static final SimpleSymbol Lit165;
  
  static final SimpleSymbol Lit166;
  
  static final SimpleSymbol Lit167;
  
  static final SimpleSymbol Lit168;
  
  static final SimpleSymbol Lit169;
  
  static final FString Lit17;
  
  static final SimpleSymbol Lit170;
  
  static final SimpleSymbol Lit171;
  
  static final SimpleSymbol Lit172;
  
  static final SimpleSymbol Lit173;
  
  static final SimpleSymbol Lit174;
  
  static final SimpleSymbol Lit175;
  
  static final SimpleSymbol Lit176 = (SimpleSymbol)(new SimpleSymbol("lookup-handler")).readResolve();
  
  static final FString Lit18;
  
  static final SimpleSymbol Lit19;
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final IntNum Lit21;
  
  static final SimpleSymbol Lit22;
  
  static final IntNum Lit23;
  
  static final SimpleSymbol Lit24;
  
  static final IntNum Lit25;
  
  static final FString Lit26;
  
  static final FString Lit27;
  
  static final SimpleSymbol Lit28;
  
  static final SimpleSymbol Lit29;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit30;
  
  static final IntNum Lit31;
  
  static final SimpleSymbol Lit32;
  
  static final SimpleSymbol Lit33;
  
  static final IntNum Lit34;
  
  static final FString Lit35;
  
  static final FString Lit36;
  
  static final SimpleSymbol Lit37;
  
  static final IntNum Lit38;
  
  static final FString Lit39;
  
  static final SimpleSymbol Lit4;
  
  static final FString Lit40;
  
  static final SimpleSymbol Lit41;
  
  static final SimpleSymbol Lit42;
  
  static final IntNum Lit43;
  
  static final SimpleSymbol Lit44;
  
  static final IntNum Lit45;
  
  static final IntNum Lit46;
  
  static final FString Lit47;
  
  static final FString Lit48;
  
  static final SimpleSymbol Lit49;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit50;
  
  static final IntNum Lit51;
  
  static final IntNum Lit52;
  
  static final SimpleSymbol Lit53;
  
  static final FString Lit54;
  
  static final FString Lit55;
  
  static final SimpleSymbol Lit56;
  
  static final SimpleSymbol Lit57;
  
  static final IntNum Lit58;
  
  static final IntNum Lit59;
  
  static final IntNum Lit6;
  
  static final FString Lit60;
  
  static final FString Lit61;
  
  static final SimpleSymbol Lit62;
  
  static final IntNum Lit63;
  
  static final FString Lit64;
  
  static final FString Lit65;
  
  static final SimpleSymbol Lit66;
  
  static final IntNum Lit67;
  
  static final FString Lit68;
  
  static final FString Lit69;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit70;
  
  static final IntNum Lit71;
  
  static final FString Lit72;
  
  static final FString Lit73;
  
  static final SimpleSymbol Lit74;
  
  static final IntNum Lit75;
  
  static final FString Lit76;
  
  static final FString Lit77;
  
  static final SimpleSymbol Lit78;
  
  static final IntNum Lit79;
  
  static final SimpleSymbol Lit8;
  
  static final FString Lit80;
  
  static final FString Lit81;
  
  static final SimpleSymbol Lit82;
  
  static final IntNum Lit83;
  
  static final FString Lit84;
  
  static final FString Lit85;
  
  static final SimpleSymbol Lit86;
  
  static final IntNum Lit87;
  
  static final FString Lit88;
  
  static final FString Lit89;
  
  static final SimpleSymbol Lit9;
  
  static final SimpleSymbol Lit90;
  
  static final IntNum Lit91;
  
  static final FString Lit92;
  
  static final FString Lit93;
  
  static final SimpleSymbol Lit94;
  
  static final IntNum Lit95;
  
  static final FString Lit96;
  
  static final FString Lit97;
  
  static final SimpleSymbol Lit98;
  
  static final IntNum Lit99;
  
  public static Standings Standings;
  
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
  
  static final ModuleMethod lambda$Fn65;
  
  static final ModuleMethod lambda$Fn66;
  
  static final ModuleMethod lambda$Fn7;
  
  static final ModuleMethod lambda$Fn8;
  
  static final ModuleMethod lambda$Fn9;
  
  public Boolean $Stdebug$Mnform$St;
  
  public final ModuleMethod $define;
  
  public HorizontalArrangement HorizontalArrangement1;
  
  public Label Label10;
  
  public Label Label11;
  
  public Label Label12;
  
  public Label Label13;
  
  public Label Label14;
  
  public Label Label15;
  
  public Label Label16;
  
  public Label Label17;
  
  public Label Label18;
  
  public Label Label19;
  
  public Label Label2;
  
  public Label Label3;
  
  public Label Label4;
  
  public Label Label5;
  
  public Label Label6;
  
  public Label Label7;
  
  public Label Label8;
  
  public Label Label9;
  
  public TableArrangement TableArrangement1;
  
  public VerticalArrangement VerticalArrangement1;
  
  public VerticalArrangement VerticalArrangement10;
  
  public VerticalArrangement VerticalArrangement11;
  
  public VerticalArrangement VerticalArrangement12;
  
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
    Lit175 = (SimpleSymbol)(new SimpleSymbol("dispatchGenericEvent")).readResolve();
    Lit174 = (SimpleSymbol)(new SimpleSymbol("dispatchEvent")).readResolve();
    Lit173 = (SimpleSymbol)(new SimpleSymbol("send-error")).readResolve();
    Lit172 = (SimpleSymbol)(new SimpleSymbol("add-to-form-do-after-creation")).readResolve();
    Lit171 = (SimpleSymbol)(new SimpleSymbol("add-to-global-vars")).readResolve();
    Lit170 = (SimpleSymbol)(new SimpleSymbol("add-to-components")).readResolve();
    Lit169 = (SimpleSymbol)(new SimpleSymbol("add-to-events")).readResolve();
    Lit168 = (SimpleSymbol)(new SimpleSymbol("add-to-global-var-environment")).readResolve();
    Lit167 = (SimpleSymbol)(new SimpleSymbol("is-bound-in-form-environment")).readResolve();
    Lit166 = (SimpleSymbol)(new SimpleSymbol("lookup-in-form-environment")).readResolve();
    Lit165 = (SimpleSymbol)(new SimpleSymbol("add-to-form-environment")).readResolve();
    Lit164 = (SimpleSymbol)(new SimpleSymbol("android-log-form")).readResolve();
    Lit163 = (SimpleSymbol)(new SimpleSymbol("get-simple-name")).readResolve();
    Lit162 = new FString("com.google.appinventor.components.runtime.Label");
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit161 = IntNum.make(arrayOfInt);
    Lit160 = (SimpleSymbol)(new SimpleSymbol("Label19")).readResolve();
    Lit159 = new FString("com.google.appinventor.components.runtime.Label");
    Lit158 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit157 = IntNum.make(arrayOfInt);
    Lit156 = (SimpleSymbol)(new SimpleSymbol("Label18")).readResolve();
    Lit155 = new FString("com.google.appinventor.components.runtime.Label");
    Lit154 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit153 = IntNum.make(arrayOfInt);
    Lit152 = (SimpleSymbol)(new SimpleSymbol("Label17")).readResolve();
    Lit151 = new FString("com.google.appinventor.components.runtime.Label");
    Lit150 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit149 = IntNum.make(arrayOfInt);
    Lit148 = (SimpleSymbol)(new SimpleSymbol("Label16")).readResolve();
    Lit147 = new FString("com.google.appinventor.components.runtime.Label");
    Lit146 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit145 = IntNum.make(arrayOfInt);
    Lit144 = (SimpleSymbol)(new SimpleSymbol("Label15")).readResolve();
    Lit143 = new FString("com.google.appinventor.components.runtime.Label");
    Lit142 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit141 = IntNum.make(arrayOfInt);
    Lit140 = (SimpleSymbol)(new SimpleSymbol("Label14")).readResolve();
    Lit139 = new FString("com.google.appinventor.components.runtime.Label");
    Lit138 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit137 = IntNum.make(arrayOfInt);
    Lit136 = IntNum.make(0);
    Lit135 = (SimpleSymbol)(new SimpleSymbol("Label13")).readResolve();
    Lit134 = new FString("com.google.appinventor.components.runtime.Label");
    Lit133 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit132 = IntNum.make(arrayOfInt);
    Lit131 = (SimpleSymbol)(new SimpleSymbol("Label2")).readResolve();
    Lit130 = new FString("com.google.appinventor.components.runtime.Label");
    Lit129 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit128 = IntNum.make(-1002);
    Lit127 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement3")).readResolve();
    Lit126 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit125 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit124 = IntNum.make(arrayOfInt);
    Lit123 = (SimpleSymbol)(new SimpleSymbol("Label3")).readResolve();
    Lit122 = new FString("com.google.appinventor.components.runtime.Label");
    Lit121 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit120 = IntNum.make(-1002);
    Lit119 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement4")).readResolve();
    Lit118 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit117 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit116 = IntNum.make(arrayOfInt);
    Lit115 = (SimpleSymbol)(new SimpleSymbol("Label5")).readResolve();
    Lit114 = new FString("com.google.appinventor.components.runtime.Label");
    Lit113 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit112 = IntNum.make(-1002);
    Lit111 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement2")).readResolve();
    Lit110 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit109 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit108 = IntNum.make(arrayOfInt);
    Lit107 = (SimpleSymbol)(new SimpleSymbol("Label4")).readResolve();
    Lit106 = new FString("com.google.appinventor.components.runtime.Label");
    Lit105 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit104 = IntNum.make(arrayOfInt);
    Lit103 = DFloNum.make(18);
    Lit102 = (SimpleSymbol)(new SimpleSymbol("Label6")).readResolve();
    Lit101 = new FString("com.google.appinventor.components.runtime.Label");
    Lit100 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit99 = IntNum.make(arrayOfInt);
    Lit98 = (SimpleSymbol)(new SimpleSymbol("Label8")).readResolve();
    Lit97 = new FString("com.google.appinventor.components.runtime.Label");
    Lit96 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit95 = IntNum.make(arrayOfInt);
    Lit94 = (SimpleSymbol)(new SimpleSymbol("Label10")).readResolve();
    Lit93 = new FString("com.google.appinventor.components.runtime.Label");
    Lit92 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit91 = IntNum.make(-1006);
    Lit90 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement9")).readResolve();
    Lit89 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit88 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit87 = IntNum.make(-1005);
    Lit86 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement6")).readResolve();
    Lit85 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit84 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit83 = IntNum.make(-1005);
    Lit82 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement7")).readResolve();
    Lit81 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit80 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit79 = IntNum.make(-1005);
    Lit78 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement8")).readResolve();
    Lit77 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit76 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit75 = IntNum.make(arrayOfInt);
    Lit74 = (SimpleSymbol)(new SimpleSymbol("Label7")).readResolve();
    Lit73 = new FString("com.google.appinventor.components.runtime.Label");
    Lit72 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit71 = IntNum.make(-1002);
    Lit70 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement1")).readResolve();
    Lit69 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit68 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit67 = IntNum.make(arrayOfInt);
    Lit66 = (SimpleSymbol)(new SimpleSymbol("Label9")).readResolve();
    Lit65 = new FString("com.google.appinventor.components.runtime.Label");
    Lit64 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit63 = IntNum.make(-1002);
    Lit62 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement5")).readResolve();
    Lit61 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit60 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit59 = IntNum.make(arrayOfInt);
    Lit58 = IntNum.make(1);
    Lit57 = (SimpleSymbol)(new SimpleSymbol("TextAlignment")).readResolve();
    Lit56 = (SimpleSymbol)(new SimpleSymbol("Label11")).readResolve();
    Lit55 = new FString("com.google.appinventor.components.runtime.Label");
    Lit54 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit53 = (SimpleSymbol)(new SimpleSymbol("Row")).readResolve();
    Lit52 = IntNum.make(-1005);
    Lit51 = IntNum.make(4);
    Lit50 = (SimpleSymbol)(new SimpleSymbol("Column")).readResolve();
    Lit49 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement10")).readResolve();
    Lit48 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit47 = new FString("com.google.appinventor.components.runtime.TableArrangement");
    Lit46 = IntNum.make(-1100);
    Lit45 = IntNum.make(6);
    Lit44 = (SimpleSymbol)(new SimpleSymbol("Rows")).readResolve();
    Lit43 = IntNum.make(5);
    Lit42 = (SimpleSymbol)(new SimpleSymbol("Columns")).readResolve();
    Lit41 = (SimpleSymbol)(new SimpleSymbol("TableArrangement1")).readResolve();
    Lit40 = new FString("com.google.appinventor.components.runtime.TableArrangement");
    Lit39 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit38 = IntNum.make(-1005);
    Lit37 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement12")).readResolve();
    Lit36 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit35 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit34 = IntNum.make(arrayOfInt);
    Lit33 = (SimpleSymbol)(new SimpleSymbol("TextColor")).readResolve();
    Lit32 = (SimpleSymbol)(new SimpleSymbol("Text")).readResolve();
    Lit31 = IntNum.make(18);
    Lit30 = (SimpleSymbol)(new SimpleSymbol("FontSize")).readResolve();
    Lit29 = (SimpleSymbol)(new SimpleSymbol("FontBold")).readResolve();
    Lit28 = (SimpleSymbol)(new SimpleSymbol("Label12")).readResolve();
    Lit27 = new FString("com.google.appinventor.components.runtime.Label");
    Lit26 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit25 = IntNum.make(-1100);
    Lit24 = (SimpleSymbol)(new SimpleSymbol("Width")).readResolve();
    Lit23 = IntNum.make(2);
    Lit22 = (SimpleSymbol)(new SimpleSymbol("AlignVertical")).readResolve();
    Lit21 = IntNum.make(3);
    Lit20 = (SimpleSymbol)(new SimpleSymbol("AlignHorizontal")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement1")).readResolve();
    Lit18 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit17 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit16 = IntNum.make(-1005);
    Lit15 = (SimpleSymbol)(new SimpleSymbol("Height")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement11")).readResolve();
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
    Lit0 = (SimpleSymbol)(new SimpleSymbol("Standings")).readResolve();
  }
  
  public Standings() {
    ModuleInfo.register(this);
    Standings$frame standings$frame = new Standings$frame();
    standings$frame.$main = this;
    this.get$Mnsimple$Mnname = new ModuleMethod(standings$frame, 1, Lit163, 4097);
    this.onCreate = new ModuleMethod(standings$frame, 2, "onCreate", 4097);
    this.android$Mnlog$Mnform = new ModuleMethod(standings$frame, 3, Lit164, 4097);
    this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(standings$frame, 4, Lit165, 8194);
    this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(standings$frame, 5, Lit166, 8193);
    this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(standings$frame, 7, Lit167, 4097);
    this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(standings$frame, 8, Lit168, 8194);
    this.add$Mnto$Mnevents = new ModuleMethod(standings$frame, 9, Lit169, 8194);
    this.add$Mnto$Mncomponents = new ModuleMethod(standings$frame, 10, Lit170, 16388);
    this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(standings$frame, 11, Lit171, 8194);
    this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(standings$frame, 12, Lit172, 4097);
    this.send$Mnerror = new ModuleMethod(standings$frame, 13, Lit173, 4097);
    this.process$Mnexception = new ModuleMethod(standings$frame, 14, "process-exception", 4097);
    this.dispatchEvent = new ModuleMethod(standings$frame, 15, Lit174, 16388);
    this.dispatchGenericEvent = new ModuleMethod(standings$frame, 16, Lit175, 16388);
    this.lookup$Mnhandler = new ModuleMethod(standings$frame, 17, Lit176, 8194);
    ModuleMethod moduleMethod = new ModuleMethod(standings$frame, 18, null, 0);
    moduleMethod.setProperty("source-location", "/tmp/runtime6993135000179593734.scm:622");
    lambda$Fn1 = moduleMethod;
    this.$define = new ModuleMethod(standings$frame, 19, "$define", 0);
    lambda$Fn2 = new ModuleMethod(standings$frame, 20, null, 0);
    lambda$Fn3 = new ModuleMethod(standings$frame, 21, null, 0);
    lambda$Fn4 = new ModuleMethod(standings$frame, 22, null, 0);
    lambda$Fn5 = new ModuleMethod(standings$frame, 23, null, 0);
    lambda$Fn6 = new ModuleMethod(standings$frame, 24, null, 0);
    lambda$Fn7 = new ModuleMethod(standings$frame, 25, null, 0);
    lambda$Fn8 = new ModuleMethod(standings$frame, 26, null, 0);
    lambda$Fn9 = new ModuleMethod(standings$frame, 27, null, 0);
    lambda$Fn10 = new ModuleMethod(standings$frame, 28, null, 0);
    lambda$Fn11 = new ModuleMethod(standings$frame, 29, null, 0);
    lambda$Fn12 = new ModuleMethod(standings$frame, 30, null, 0);
    lambda$Fn13 = new ModuleMethod(standings$frame, 31, null, 0);
    lambda$Fn14 = new ModuleMethod(standings$frame, 32, null, 0);
    lambda$Fn15 = new ModuleMethod(standings$frame, 33, null, 0);
    lambda$Fn16 = new ModuleMethod(standings$frame, 34, null, 0);
    lambda$Fn17 = new ModuleMethod(standings$frame, 35, null, 0);
    lambda$Fn18 = new ModuleMethod(standings$frame, 36, null, 0);
    lambda$Fn19 = new ModuleMethod(standings$frame, 37, null, 0);
    lambda$Fn20 = new ModuleMethod(standings$frame, 38, null, 0);
    lambda$Fn21 = new ModuleMethod(standings$frame, 39, null, 0);
    lambda$Fn22 = new ModuleMethod(standings$frame, 40, null, 0);
    lambda$Fn23 = new ModuleMethod(standings$frame, 41, null, 0);
    lambda$Fn24 = new ModuleMethod(standings$frame, 42, null, 0);
    lambda$Fn25 = new ModuleMethod(standings$frame, 43, null, 0);
    lambda$Fn26 = new ModuleMethod(standings$frame, 44, null, 0);
    lambda$Fn27 = new ModuleMethod(standings$frame, 45, null, 0);
    lambda$Fn28 = new ModuleMethod(standings$frame, 46, null, 0);
    lambda$Fn29 = new ModuleMethod(standings$frame, 47, null, 0);
    lambda$Fn30 = new ModuleMethod(standings$frame, 48, null, 0);
    lambda$Fn31 = new ModuleMethod(standings$frame, 49, null, 0);
    lambda$Fn32 = new ModuleMethod(standings$frame, 50, null, 0);
    lambda$Fn33 = new ModuleMethod(standings$frame, 51, null, 0);
    lambda$Fn34 = new ModuleMethod(standings$frame, 52, null, 0);
    lambda$Fn35 = new ModuleMethod(standings$frame, 53, null, 0);
    lambda$Fn36 = new ModuleMethod(standings$frame, 54, null, 0);
    lambda$Fn37 = new ModuleMethod(standings$frame, 55, null, 0);
    lambda$Fn38 = new ModuleMethod(standings$frame, 56, null, 0);
    lambda$Fn39 = new ModuleMethod(standings$frame, 57, null, 0);
    lambda$Fn40 = new ModuleMethod(standings$frame, 58, null, 0);
    lambda$Fn41 = new ModuleMethod(standings$frame, 59, null, 0);
    lambda$Fn42 = new ModuleMethod(standings$frame, 60, null, 0);
    lambda$Fn43 = new ModuleMethod(standings$frame, 61, null, 0);
    lambda$Fn44 = new ModuleMethod(standings$frame, 62, null, 0);
    lambda$Fn45 = new ModuleMethod(standings$frame, 63, null, 0);
    lambda$Fn46 = new ModuleMethod(standings$frame, 64, null, 0);
    lambda$Fn47 = new ModuleMethod(standings$frame, 65, null, 0);
    lambda$Fn48 = new ModuleMethod(standings$frame, 66, null, 0);
    lambda$Fn49 = new ModuleMethod(standings$frame, 67, null, 0);
    lambda$Fn50 = new ModuleMethod(standings$frame, 68, null, 0);
    lambda$Fn51 = new ModuleMethod(standings$frame, 69, null, 0);
    lambda$Fn52 = new ModuleMethod(standings$frame, 70, null, 0);
    lambda$Fn53 = new ModuleMethod(standings$frame, 71, null, 0);
    lambda$Fn54 = new ModuleMethod(standings$frame, 72, null, 0);
    lambda$Fn55 = new ModuleMethod(standings$frame, 73, null, 0);
    lambda$Fn56 = new ModuleMethod(standings$frame, 74, null, 0);
    lambda$Fn57 = new ModuleMethod(standings$frame, 75, null, 0);
    lambda$Fn58 = new ModuleMethod(standings$frame, 76, null, 0);
    lambda$Fn59 = new ModuleMethod(standings$frame, 77, null, 0);
    lambda$Fn60 = new ModuleMethod(standings$frame, 78, null, 0);
    lambda$Fn61 = new ModuleMethod(standings$frame, 79, null, 0);
    lambda$Fn62 = new ModuleMethod(standings$frame, 80, null, 0);
    lambda$Fn63 = new ModuleMethod(standings$frame, 81, null, 0);
    lambda$Fn64 = new ModuleMethod(standings$frame, 82, null, 0);
    lambda$Fn65 = new ModuleMethod(standings$frame, 83, null, 0);
    lambda$Fn66 = new ModuleMethod(standings$frame, 84, null, 0);
  }
  
  static Object lambda10() {
    return runtime.setAndCoerceProperty$Ex(Lit37, Lit15, Lit38, Lit7);
  }
  
  static Object lambda11() {
    return runtime.setAndCoerceProperty$Ex(Lit37, Lit15, Lit38, Lit7);
  }
  
  static Object lambda12() {
    runtime.setAndCoerceProperty$Ex(Lit41, Lit42, Lit43, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit41, Lit44, Lit45, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit41, Lit24, Lit46, Lit7);
  }
  
  static Object lambda13() {
    runtime.setAndCoerceProperty$Ex(Lit41, Lit42, Lit43, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit41, Lit44, Lit45, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit41, Lit24, Lit46, Lit7);
  }
  
  static Object lambda14() {
    runtime.setAndCoerceProperty$Ex(Lit49, Lit50, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit49, Lit15, Lit52, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit49, Lit53, Lit43, Lit7);
  }
  
  static Object lambda15() {
    runtime.setAndCoerceProperty$Ex(Lit49, Lit50, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit49, Lit15, Lit52, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit49, Lit53, Lit43, Lit7);
  }
  
  static Object lambda16() {
    runtime.setAndCoerceProperty$Ex(Lit56, Lit50, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit53, Lit43, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit32, "1", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit56, Lit33, Lit59, Lit7);
  }
  
  static Object lambda17() {
    runtime.setAndCoerceProperty$Ex(Lit56, Lit50, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit53, Lit43, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit32, "1", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit56, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit56, Lit33, Lit59, Lit7);
  }
  
  static Object lambda18() {
    runtime.setAndCoerceProperty$Ex(Lit62, Lit50, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit62, Lit15, Lit63, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit62, Lit53, Lit43, Lit7);
  }
  
  static Object lambda19() {
    runtime.setAndCoerceProperty$Ex(Lit62, Lit50, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit62, Lit15, Lit63, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit62, Lit53, Lit43, Lit7);
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
    runtime.setAndCoerceProperty$Ex(Lit66, Lit50, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit66, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit66, Lit53, Lit43, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit66, Lit32, "pangeo", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit66, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit66, Lit33, Lit67, Lit7);
  }
  
  static Object lambda21() {
    runtime.setAndCoerceProperty$Ex(Lit66, Lit50, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit66, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit66, Lit53, Lit43, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit66, Lit32, "pangeo", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit66, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit66, Lit33, Lit67, Lit7);
  }
  
  static Object lambda22() {
    runtime.setAndCoerceProperty$Ex(Lit70, Lit50, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit70, Lit15, Lit71, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit70, Lit53, Lit51, Lit7);
  }
  
  static Object lambda23() {
    runtime.setAndCoerceProperty$Ex(Lit70, Lit50, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit70, Lit15, Lit71, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit70, Lit53, Lit51, Lit7);
  }
  
  static Object lambda24() {
    runtime.setAndCoerceProperty$Ex(Lit74, Lit50, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit74, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit74, Lit53, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit74, Lit32, "theogat", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit74, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit74, Lit33, Lit75, Lit7);
  }
  
  static Object lambda25() {
    runtime.setAndCoerceProperty$Ex(Lit74, Lit50, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit74, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit74, Lit53, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit74, Lit32, "theogat", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit74, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit74, Lit33, Lit75, Lit7);
  }
  
  static Object lambda26() {
    runtime.setAndCoerceProperty$Ex(Lit78, Lit50, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit78, Lit15, Lit79, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit78, Lit53, Lit51, Lit7);
  }
  
  static Object lambda27() {
    runtime.setAndCoerceProperty$Ex(Lit78, Lit50, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit78, Lit15, Lit79, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit78, Lit53, Lit51, Lit7);
  }
  
  static Object lambda28() {
    runtime.setAndCoerceProperty$Ex(Lit82, Lit50, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit82, Lit15, Lit83, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit82, Lit53, Lit21, Lit7);
  }
  
  static Object lambda29() {
    runtime.setAndCoerceProperty$Ex(Lit82, Lit50, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit82, Lit15, Lit83, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit82, Lit53, Lit21, Lit7);
  }
  
  static Object lambda3() {
    runtime.setAndCoerceProperty$Ex(Lit0, Lit3, "GROW_game_app", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit5, Lit6, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit8, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "Responsive", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit11, "Standings", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit0, Lit12, Boolean.FALSE, Lit9);
  }
  
  static Object lambda30() {
    runtime.setAndCoerceProperty$Ex(Lit86, Lit50, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit86, Lit15, Lit87, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit86, Lit53, Lit23, Lit7);
  }
  
  static Object lambda31() {
    runtime.setAndCoerceProperty$Ex(Lit86, Lit50, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit86, Lit15, Lit87, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit86, Lit53, Lit23, Lit7);
  }
  
  static Object lambda32() {
    runtime.setAndCoerceProperty$Ex(Lit90, Lit50, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit90, Lit15, Lit91, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit90, Lit53, Lit58, Lit7);
  }
  
  static Object lambda33() {
    runtime.setAndCoerceProperty$Ex(Lit90, Lit50, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit90, Lit15, Lit91, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit90, Lit53, Lit58, Lit7);
  }
  
  static Object lambda34() {
    runtime.setAndCoerceProperty$Ex(Lit94, Lit50, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit94, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit94, Lit53, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit94, Lit32, "2", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit94, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit94, Lit33, Lit95, Lit7);
  }
  
  static Object lambda35() {
    runtime.setAndCoerceProperty$Ex(Lit94, Lit50, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit94, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit94, Lit53, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit94, Lit32, "2", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit94, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit94, Lit33, Lit95, Lit7);
  }
  
  static Object lambda36() {
    runtime.setAndCoerceProperty$Ex(Lit98, Lit50, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit98, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit98, Lit53, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit98, Lit32, "2", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit98, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit98, Lit33, Lit99, Lit7);
  }
  
  static Object lambda37() {
    runtime.setAndCoerceProperty$Ex(Lit98, Lit50, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit98, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit98, Lit53, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit98, Lit32, "2", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit98, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit98, Lit33, Lit99, Lit7);
  }
  
  static Object lambda38() {
    runtime.setAndCoerceProperty$Ex(Lit102, Lit50, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit102, Lit30, Lit103, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit102, Lit53, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit102, Lit32, "3", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit102, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit102, Lit33, Lit104, Lit7);
  }
  
  static Object lambda39() {
    runtime.setAndCoerceProperty$Ex(Lit102, Lit50, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit102, Lit30, Lit103, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit102, Lit53, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit102, Lit32, "3", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit102, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit102, Lit33, Lit104, Lit7);
  }
  
  static Object lambda4() {
    return runtime.setAndCoerceProperty$Ex(Lit14, Lit15, Lit16, Lit7);
  }
  
  static Object lambda40() {
    runtime.setAndCoerceProperty$Ex(Lit107, Lit50, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit107, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit107, Lit53, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit107, Lit32, "4", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit107, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit107, Lit33, Lit108, Lit7);
  }
  
  static Object lambda41() {
    runtime.setAndCoerceProperty$Ex(Lit107, Lit50, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit107, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit107, Lit53, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit107, Lit32, "4", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit107, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit107, Lit33, Lit108, Lit7);
  }
  
  static Object lambda42() {
    runtime.setAndCoerceProperty$Ex(Lit111, Lit50, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit111, Lit15, Lit112, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit111, Lit53, Lit21, Lit7);
  }
  
  static Object lambda43() {
    runtime.setAndCoerceProperty$Ex(Lit111, Lit50, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit111, Lit15, Lit112, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit111, Lit53, Lit21, Lit7);
  }
  
  static Object lambda44() {
    runtime.setAndCoerceProperty$Ex(Lit115, Lit50, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit115, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit115, Lit53, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit115, Lit32, "tobo", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit115, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit115, Lit33, Lit116, Lit7);
  }
  
  static Object lambda45() {
    runtime.setAndCoerceProperty$Ex(Lit115, Lit50, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit115, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit115, Lit53, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit115, Lit32, "tobo", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit115, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit115, Lit33, Lit116, Lit7);
  }
  
  static Object lambda46() {
    runtime.setAndCoerceProperty$Ex(Lit119, Lit50, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit119, Lit15, Lit120, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit119, Lit53, Lit23, Lit7);
  }
  
  static Object lambda47() {
    runtime.setAndCoerceProperty$Ex(Lit119, Lit50, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit119, Lit15, Lit120, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit119, Lit53, Lit23, Lit7);
  }
  
  static Object lambda48() {
    runtime.setAndCoerceProperty$Ex(Lit123, Lit50, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit123, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit123, Lit53, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit123, Lit32, "andy", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit123, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit123, Lit33, Lit124, Lit7);
  }
  
  static Object lambda49() {
    runtime.setAndCoerceProperty$Ex(Lit123, Lit50, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit123, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit123, Lit53, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit123, Lit32, "andy", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit123, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit123, Lit33, Lit124, Lit7);
  }
  
  static Object lambda5() {
    return runtime.setAndCoerceProperty$Ex(Lit14, Lit15, Lit16, Lit7);
  }
  
  static Object lambda50() {
    runtime.setAndCoerceProperty$Ex(Lit127, Lit50, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit127, Lit15, Lit128, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit127, Lit53, Lit58, Lit7);
  }
  
  static Object lambda51() {
    runtime.setAndCoerceProperty$Ex(Lit127, Lit50, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit127, Lit15, Lit128, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit127, Lit53, Lit58, Lit7);
  }
  
  static Object lambda52() {
    runtime.setAndCoerceProperty$Ex(Lit131, Lit50, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit131, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit131, Lit53, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit131, Lit32, "kb", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit131, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit131, Lit33, Lit132, Lit7);
  }
  
  static Object lambda53() {
    runtime.setAndCoerceProperty$Ex(Lit131, Lit50, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit131, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit131, Lit53, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit131, Lit32, "kb", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit131, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit131, Lit33, Lit132, Lit7);
  }
  
  static Object lambda54() {
    runtime.setAndCoerceProperty$Ex(Lit135, Lit50, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit135, Lit29, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit135, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit135, Lit53, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit135, Lit32, "Username", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit135, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit135, Lit33, Lit137, Lit7);
  }
  
  static Object lambda55() {
    runtime.setAndCoerceProperty$Ex(Lit135, Lit50, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit135, Lit29, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit135, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit135, Lit53, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit135, Lit32, "Username", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit135, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit135, Lit33, Lit137, Lit7);
  }
  
  static Object lambda56() {
    runtime.setAndCoerceProperty$Ex(Lit140, Lit50, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit140, Lit29, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit140, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit140, Lit53, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit140, Lit32, "Missions", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit140, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit140, Lit33, Lit141, Lit7);
  }
  
  static Object lambda57() {
    runtime.setAndCoerceProperty$Ex(Lit140, Lit50, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit140, Lit29, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit140, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit140, Lit53, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit140, Lit32, "Missions", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit140, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit140, Lit33, Lit141, Lit7);
  }
  
  static Object lambda58() {
    runtime.setAndCoerceProperty$Ex(Lit144, Lit50, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit144, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit144, Lit53, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit144, Lit32, "1.", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit144, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit144, Lit33, Lit145, Lit7);
  }
  
  static Object lambda59() {
    runtime.setAndCoerceProperty$Ex(Lit144, Lit50, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit144, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit144, Lit53, Lit58, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit144, Lit32, "1.", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit144, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit144, Lit33, Lit145, Lit7);
  }
  
  static Object lambda6() {
    runtime.setAndCoerceProperty$Ex(Lit19, Lit20, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit19, Lit22, Lit23, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit19, Lit24, Lit25, Lit7);
  }
  
  static Object lambda60() {
    runtime.setAndCoerceProperty$Ex(Lit148, Lit50, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit148, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit148, Lit53, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit148, Lit32, "2.", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit148, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit148, Lit33, Lit149, Lit7);
  }
  
  static Object lambda61() {
    runtime.setAndCoerceProperty$Ex(Lit148, Lit50, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit148, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit148, Lit53, Lit23, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit148, Lit32, "2.", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit148, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit148, Lit33, Lit149, Lit7);
  }
  
  static Object lambda62() {
    runtime.setAndCoerceProperty$Ex(Lit152, Lit50, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit152, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit152, Lit53, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit152, Lit32, "3.", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit152, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit152, Lit33, Lit153, Lit7);
  }
  
  static Object lambda63() {
    runtime.setAndCoerceProperty$Ex(Lit152, Lit50, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit152, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit152, Lit53, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit152, Lit32, "3.", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit152, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit152, Lit33, Lit153, Lit7);
  }
  
  static Object lambda64() {
    runtime.setAndCoerceProperty$Ex(Lit156, Lit50, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit156, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit156, Lit53, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit156, Lit32, "4.", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit156, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit156, Lit33, Lit157, Lit7);
  }
  
  static Object lambda65() {
    runtime.setAndCoerceProperty$Ex(Lit156, Lit50, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit156, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit156, Lit53, Lit51, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit156, Lit32, "4.", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit156, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit156, Lit33, Lit157, Lit7);
  }
  
  static Object lambda66() {
    runtime.setAndCoerceProperty$Ex(Lit160, Lit50, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit160, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit160, Lit53, Lit43, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit160, Lit32, "5.", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit160, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit160, Lit33, Lit161, Lit7);
  }
  
  static Object lambda67() {
    runtime.setAndCoerceProperty$Ex(Lit160, Lit50, Lit136, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit160, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit160, Lit53, Lit43, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit160, Lit32, "5.", Lit4);
    runtime.setAndCoerceProperty$Ex(Lit160, Lit57, Lit58, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit160, Lit33, Lit161, Lit7);
  }
  
  static Object lambda7() {
    runtime.setAndCoerceProperty$Ex(Lit19, Lit20, Lit21, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit19, Lit22, Lit23, Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit19, Lit24, Lit25, Lit7);
  }
  
  static Object lambda8() {
    runtime.setAndCoerceProperty$Ex(Lit28, Lit29, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit28, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit28, Lit32, "Tournament", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit28, Lit33, Lit34, Lit7);
  }
  
  static Object lambda9() {
    runtime.setAndCoerceProperty$Ex(Lit28, Lit29, Boolean.TRUE, Lit9);
    runtime.setAndCoerceProperty$Ex(Lit28, Lit30, Lit31, Lit7);
    runtime.setAndCoerceProperty$Ex(Lit28, Lit32, "Tournament", Lit4);
    return runtime.setAndCoerceProperty$Ex(Lit28, Lit33, Lit34, Lit7);
  }
  
  public void $define() {
    Language.setDefaults((Language)Scheme.getInstance());
    try {
      run();
    } catch (Exception exception) {
      androidLogForm(exception.getMessage());
      processException(exception);
    } 
    Standings = this;
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
    Consumer consumer = paramCallContext.consumer;
    Object object = require.find("com.google.youngandroid.runtime");
    try {
      Runnable runnable = (Runnable)object;
      runnable.run();
      this.$Stdebug$Mnform$St = Boolean.FALSE;
      this.form$Mnenvironment = (Environment)Environment.make(misc.symbol$To$String((Symbol)Lit0));
      object = strings.stringAppend(new Object[] { misc.symbol$To$String((Symbol)Lit0), "-global-vars" });
      if (object == null) {
        object = null;
      } else {
        object = object.toString();
      } 
      this.global$Mnvar$Mnenvironment = (Environment)Environment.make((String)object);
      Standings = null;
      this.form$Mnname$Mnsymbol = (Symbol)Lit0;
      this.events$Mnto$Mnregister = LList.Empty;
      this.components$Mnto$Mncreate = LList.Empty;
      this.global$Mnvars$Mnto$Mncreate = LList.Empty;
      this.form$Mndo$Mnafter$Mncreation = LList.Empty;
      object = require.find("com.google.youngandroid.runtime");
      try {
        runnable = (Runnable)object;
        runnable.run();
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          runtime.setAndCoerceProperty$Ex(Lit0, Lit3, "GROW_game_app", Lit4);
          runtime.setAndCoerceProperty$Ex(Lit0, Lit5, Lit6, Lit7);
          runtime.setAndCoerceProperty$Ex(Lit0, Lit8, Boolean.TRUE, Lit9);
          runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "Responsive", Lit4);
          runtime.setAndCoerceProperty$Ex(Lit0, Lit11, "Standings", Lit4);
          Values.writeValues(runtime.setAndCoerceProperty$Ex(Lit0, Lit12, Boolean.FALSE, Lit9), consumer);
        } else {
          addToFormDoAfterCreation(new Promise((Procedure)lambda$Fn2));
        } 
        this.VerticalArrangement11 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit13, Lit14, lambda$Fn3), consumer);
        } else {
          addToComponents(Lit0, Lit17, Lit14, lambda$Fn4);
        } 
        this.HorizontalArrangement1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit18, Lit19, lambda$Fn5), consumer);
        } else {
          addToComponents(Lit0, Lit26, Lit19, lambda$Fn6);
        } 
        this.Label12 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit19, Lit27, Lit28, lambda$Fn7), consumer);
        } else {
          addToComponents(Lit19, Lit35, Lit28, lambda$Fn8);
        } 
        this.VerticalArrangement12 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit36, Lit37, lambda$Fn9), consumer);
        } else {
          addToComponents(Lit0, Lit39, Lit37, lambda$Fn10);
        } 
        this.TableArrangement1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit40, Lit41, lambda$Fn11), consumer);
        } else {
          addToComponents(Lit0, Lit47, Lit41, lambda$Fn12);
        } 
        this.VerticalArrangement10 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit48, Lit49, lambda$Fn13), consumer);
        } else {
          addToComponents(Lit41, Lit54, Lit49, lambda$Fn14);
        } 
        this.Label11 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit55, Lit56, lambda$Fn15), consumer);
        } else {
          addToComponents(Lit41, Lit60, Lit56, lambda$Fn16);
        } 
        this.VerticalArrangement5 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit61, Lit62, lambda$Fn17), consumer);
        } else {
          addToComponents(Lit41, Lit64, Lit62, lambda$Fn18);
        } 
        this.Label9 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit65, Lit66, lambda$Fn19), consumer);
        } else {
          addToComponents(Lit41, Lit68, Lit66, lambda$Fn20);
        } 
        this.VerticalArrangement1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit69, Lit70, lambda$Fn21), consumer);
        } else {
          addToComponents(Lit41, Lit72, Lit70, lambda$Fn22);
        } 
        this.Label7 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit73, Lit74, lambda$Fn23), consumer);
        } else {
          addToComponents(Lit41, Lit76, Lit74, lambda$Fn24);
        } 
        this.VerticalArrangement8 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit77, Lit78, lambda$Fn25), consumer);
        } else {
          addToComponents(Lit41, Lit80, Lit78, lambda$Fn26);
        } 
        this.VerticalArrangement7 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit81, Lit82, lambda$Fn27), consumer);
        } else {
          addToComponents(Lit41, Lit84, Lit82, lambda$Fn28);
        } 
        this.VerticalArrangement6 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit85, Lit86, lambda$Fn29), consumer);
        } else {
          addToComponents(Lit41, Lit88, Lit86, lambda$Fn30);
        } 
        this.VerticalArrangement9 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit89, Lit90, lambda$Fn31), consumer);
        } else {
          addToComponents(Lit41, Lit92, Lit90, lambda$Fn32);
        } 
        this.Label10 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit93, Lit94, lambda$Fn33), consumer);
        } else {
          addToComponents(Lit41, Lit96, Lit94, lambda$Fn34);
        } 
        this.Label8 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit97, Lit98, lambda$Fn35), consumer);
        } else {
          addToComponents(Lit41, Lit100, Lit98, lambda$Fn36);
        } 
        this.Label6 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit101, Lit102, lambda$Fn37), consumer);
        } else {
          addToComponents(Lit41, Lit105, Lit102, lambda$Fn38);
        } 
        this.Label4 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit106, Lit107, lambda$Fn39), consumer);
        } else {
          addToComponents(Lit41, Lit109, Lit107, lambda$Fn40);
        } 
        this.VerticalArrangement2 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit110, Lit111, lambda$Fn41), consumer);
        } else {
          addToComponents(Lit41, Lit113, Lit111, lambda$Fn42);
        } 
        this.Label5 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit114, Lit115, lambda$Fn43), consumer);
        } else {
          addToComponents(Lit41, Lit117, Lit115, lambda$Fn44);
        } 
        this.VerticalArrangement4 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit118, Lit119, lambda$Fn45), consumer);
        } else {
          addToComponents(Lit41, Lit121, Lit119, lambda$Fn46);
        } 
        this.Label3 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit122, Lit123, lambda$Fn47), consumer);
        } else {
          addToComponents(Lit41, Lit125, Lit123, lambda$Fn48);
        } 
        this.VerticalArrangement3 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit126, Lit127, lambda$Fn49), consumer);
        } else {
          addToComponents(Lit41, Lit129, Lit127, lambda$Fn50);
        } 
        this.Label2 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit130, Lit131, lambda$Fn51), consumer);
        } else {
          addToComponents(Lit41, Lit133, Lit131, lambda$Fn52);
        } 
        this.Label13 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit134, Lit135, lambda$Fn53), consumer);
        } else {
          addToComponents(Lit41, Lit138, Lit135, lambda$Fn54);
        } 
        this.Label14 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit139, Lit140, lambda$Fn55), consumer);
        } else {
          addToComponents(Lit41, Lit142, Lit140, lambda$Fn56);
        } 
        this.Label15 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit143, Lit144, lambda$Fn57), consumer);
        } else {
          addToComponents(Lit41, Lit146, Lit144, lambda$Fn58);
        } 
        this.Label16 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit147, Lit148, lambda$Fn59), consumer);
        } else {
          addToComponents(Lit41, Lit150, Lit148, lambda$Fn60);
        } 
        this.Label17 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit151, Lit152, lambda$Fn61), consumer);
        } else {
          addToComponents(Lit41, Lit154, Lit152, lambda$Fn62);
        } 
        this.Label18 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit155, Lit156, lambda$Fn63), consumer);
        } else {
          addToComponents(Lit41, Lit158, Lit156, lambda$Fn64);
        } 
        this.Label19 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
          Values.writeValues(runtime.addComponentWithinRepl(Lit41, Lit159, Lit160, lambda$Fn65), consumer);
        } else {
          addToComponents(Lit41, Lit162, Lit160, lambda$Fn66);
        } 
        runtime.initRuntime();
        return;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "java.lang.Runnable.run()", 1, object);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "java.lang.Runnable.run()", 1, object);
    } 
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/appinventor/ai_notifications_eoteam/GROW_game_app/Standings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
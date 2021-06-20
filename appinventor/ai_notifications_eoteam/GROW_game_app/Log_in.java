package appinventor.ai_notifications_eoteam.GROW_game_app;

import android.os.Bundle;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.PasswordTextBox;
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

public class Log_in extends Form implements Runnable {
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit13;
  
  static final SimpleSymbol Lit14;
  
  static final FString Lit15;
  
  static final SimpleSymbol Lit16;
  
  static final SimpleSymbol Lit17;
  
  static final IntNum Lit18;
  
  static final FString Lit19;
  
  static final SimpleSymbol Lit2;
  
  static final FString Lit20;
  
  static final SimpleSymbol Lit21;
  
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
  
  static final SimpleSymbol Lit31;
  
  static final IntNum Lit32;
  
  static final SimpleSymbol Lit33;
  
  static final SimpleSymbol Lit34;
  
  static final IntNum Lit35;
  
  static final FString Lit36;
  
  static final FString Lit37;
  
  static final SimpleSymbol Lit38;
  
  static final IntNum Lit39;
  
  static final IntNum Lit4;
  
  static final FString Lit40;
  
  static final FString Lit41;
  
  static final SimpleSymbol Lit42;
  
  static final FString Lit43;
  
  static final FString Lit44;
  
  static final SimpleSymbol Lit45;
  
  static final IntNum Lit46;
  
  static final FString Lit47;
  
  static final FString Lit48;
  
  static final SimpleSymbol Lit49;
  
  static final SimpleSymbol Lit5;
  
  static final FString Lit50;
  
  static final FString Lit51;
  
  static final SimpleSymbol Lit52;
  
  static final IntNum Lit53;
  
  static final FString Lit54;
  
  static final FString Lit55;
  
  static final SimpleSymbol Lit56;
  
  static final FString Lit57;
  
  static final FString Lit58;
  
  static final SimpleSymbol Lit59;
  
  static final SimpleSymbol Lit6;
  
  static final IntNum Lit60;
  
  static final FString Lit61;
  
  static final FString Lit62;
  
  static final SimpleSymbol Lit63;
  
  static final IntNum Lit64;
  
  static final IntNum Lit65;
  
  static final FString Lit66;
  
  static final FString Lit67;
  
  static final SimpleSymbol Lit68;
  
  static final FString Lit69;
  
  static final SimpleSymbol Lit7;
  
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
  
  static final IntNum Lit81;
  
  static final FString Lit82;
  
  static final PairWithPosition Lit83;
  
  static final SimpleSymbol Lit84;
  
  static final SimpleSymbol Lit85;
  
  static final SimpleSymbol Lit86;
  
  static final SimpleSymbol Lit87;
  
  static final SimpleSymbol Lit88;
  
  static final SimpleSymbol Lit89;
  
  static final IntNum Lit9;
  
  static final SimpleSymbol Lit90;
  
  static final SimpleSymbol Lit91;
  
  static final SimpleSymbol Lit92;
  
  static final SimpleSymbol Lit93;
  
  static final SimpleSymbol Lit94;
  
  static final SimpleSymbol Lit95;
  
  static final SimpleSymbol Lit96;
  
  static final SimpleSymbol Lit97;
  
  static final SimpleSymbol Lit98;
  
  static final SimpleSymbol Lit99 = (SimpleSymbol)(new SimpleSymbol("lookup-handler")).readResolve();
  
  public static Log_in Log_in;
  
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
  
  static final ModuleMethod lambda$Fn3;
  
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
  
  public HorizontalArrangement HorizontalArrangement1;
  
  public HorizontalArrangement HorizontalArrangement2;
  
  public HorizontalArrangement HorizontalArrangement3;
  
  public HorizontalArrangement HorizontalArrangement4;
  
  public HorizontalArrangement HorizontalArrangement5;
  
  public Label Label1;
  
  public Label Label2;
  
  public Label Label3;
  
  public PasswordTextBox PasswordTextBox1;
  
  public TextBox TextBox1;
  
  public VerticalArrangement VerticalArrangement1;
  
  public VerticalArrangement VerticalArrangement2;
  
  public VerticalArrangement VerticalArrangement3;
  
  public VerticalArrangement VerticalArrangement4;
  
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
    Lit98 = (SimpleSymbol)(new SimpleSymbol("dispatchGenericEvent")).readResolve();
    Lit97 = (SimpleSymbol)(new SimpleSymbol("dispatchEvent")).readResolve();
    Lit96 = (SimpleSymbol)(new SimpleSymbol("send-error")).readResolve();
    Lit95 = (SimpleSymbol)(new SimpleSymbol("add-to-form-do-after-creation")).readResolve();
    Lit94 = (SimpleSymbol)(new SimpleSymbol("add-to-global-vars")).readResolve();
    Lit93 = (SimpleSymbol)(new SimpleSymbol("add-to-components")).readResolve();
    Lit92 = (SimpleSymbol)(new SimpleSymbol("add-to-events")).readResolve();
    Lit91 = (SimpleSymbol)(new SimpleSymbol("add-to-global-var-environment")).readResolve();
    Lit90 = (SimpleSymbol)(new SimpleSymbol("is-bound-in-form-environment")).readResolve();
    Lit89 = (SimpleSymbol)(new SimpleSymbol("lookup-in-form-environment")).readResolve();
    Lit88 = (SimpleSymbol)(new SimpleSymbol("add-to-form-environment")).readResolve();
    Lit87 = (SimpleSymbol)(new SimpleSymbol("android-log-form")).readResolve();
    Lit86 = (SimpleSymbol)(new SimpleSymbol("get-simple-name")).readResolve();
    Lit85 = (SimpleSymbol)(new SimpleSymbol("Click")).readResolve();
    Lit84 = (SimpleSymbol)(new SimpleSymbol("Button1$Click")).readResolve();
    SimpleSymbol simpleSymbol = (SimpleSymbol)(new SimpleSymbol("text")).readResolve();
    Lit7 = simpleSymbol;
    Lit83 = PairWithPosition.make(simpleSymbol, LList.Empty, "/tmp/1624147919646_0.8026631161501641-0/youngandroidproject/../src/appinventor/ai_notifications_eoteam/GROW_game_app/Log_in.yail", 610382);
    Lit82 = new FString("com.google.appinventor.components.runtime.Button");
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit81 = IntNum.make(arrayOfInt);
    arrayOfInt = new int[2];
    arrayOfInt[0] = -7352248;
    Lit80 = IntNum.make(arrayOfInt);
    Lit79 = (SimpleSymbol)(new SimpleSymbol("Button1")).readResolve();
    Lit78 = new FString("com.google.appinventor.components.runtime.Button");
    Lit77 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit76 = IntNum.make(-1100);
    Lit75 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement4")).readResolve();
    Lit74 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit73 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit72 = IntNum.make(-1005);
    Lit71 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement3")).readResolve();
    Lit70 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit69 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    Lit68 = (SimpleSymbol)(new SimpleSymbol("PasswordTextBox1")).readResolve();
    Lit67 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    Lit66 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit65 = IntNum.make(-1008);
    Lit64 = IntNum.make(-1002);
    Lit63 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement5")).readResolve();
    Lit62 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit61 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit60 = IntNum.make(arrayOfInt);
    Lit59 = (SimpleSymbol)(new SimpleSymbol("Label3")).readResolve();
    Lit58 = new FString("com.google.appinventor.components.runtime.Label");
    Lit57 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit56 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement3")).readResolve();
    Lit55 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit54 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit53 = IntNum.make(-1002);
    Lit52 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement4")).readResolve();
    Lit51 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit50 = new FString("com.google.appinventor.components.runtime.TextBox");
    Lit49 = (SimpleSymbol)(new SimpleSymbol("TextBox1")).readResolve();
    Lit48 = new FString("com.google.appinventor.components.runtime.TextBox");
    Lit47 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit46 = IntNum.make(arrayOfInt);
    Lit45 = (SimpleSymbol)(new SimpleSymbol("Label2")).readResolve();
    Lit44 = new FString("com.google.appinventor.components.runtime.Label");
    Lit43 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit42 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement2")).readResolve();
    Lit41 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit40 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit39 = IntNum.make(-1005);
    Lit38 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement2")).readResolve();
    Lit37 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit36 = new FString("com.google.appinventor.components.runtime.Label");
    arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit35 = IntNum.make(arrayOfInt);
    Lit34 = (SimpleSymbol)(new SimpleSymbol("TextColor")).readResolve();
    Lit33 = (SimpleSymbol)(new SimpleSymbol("Text")).readResolve();
    Lit32 = IntNum.make(18);
    Lit31 = (SimpleSymbol)(new SimpleSymbol("FontSize")).readResolve();
    Lit30 = (SimpleSymbol)(new SimpleSymbol("FontItalic")).readResolve();
    Lit29 = (SimpleSymbol)(new SimpleSymbol("FontBold")).readResolve();
    Lit28 = (SimpleSymbol)(new SimpleSymbol("Label1")).readResolve();
    Lit27 = new FString("com.google.appinventor.components.runtime.Label");
    Lit26 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit25 = IntNum.make(-1100);
    Lit24 = (SimpleSymbol)(new SimpleSymbol("Width")).readResolve();
    Lit23 = IntNum.make(3);
    Lit22 = (SimpleSymbol)(new SimpleSymbol("AlignHorizontal")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement1")).readResolve();
    Lit20 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit19 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit18 = IntNum.make(-1005);
    Lit17 = (SimpleSymbol)(new SimpleSymbol("Height")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("VerticalArrangement1")).readResolve();
    Lit15 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    Lit14 = (SimpleSymbol)(new SimpleSymbol("TitleVisible")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("Title")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("Sizing")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("boolean")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("ShowListsAsJson")).readResolve();
    arrayOfInt = new int[2];
    arrayOfInt[0] = -15227642;
    Lit9 = IntNum.make(arrayOfInt);
    Lit8 = (SimpleSymbol)(new SimpleSymbol("BackgroundColor")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("AppName")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("number")).readResolve();
    Lit4 = IntNum.make(2);
    Lit3 = (SimpleSymbol)(new SimpleSymbol("AlignVertical")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("*the-null-value*")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("getMessage")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("Log_in")).readResolve();
  }
  
  public Log_in() {
    ModuleInfo.register(this);
    Log_in$frame log_in$frame = new Log_in$frame();
    log_in$frame.$main = this;
    this.get$Mnsimple$Mnname = new ModuleMethod(log_in$frame, 1, Lit86, 4097);
    this.onCreate = new ModuleMethod(log_in$frame, 2, "onCreate", 4097);
    this.android$Mnlog$Mnform = new ModuleMethod(log_in$frame, 3, Lit87, 4097);
    this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(log_in$frame, 4, Lit88, 8194);
    this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(log_in$frame, 5, Lit89, 8193);
    this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(log_in$frame, 7, Lit90, 4097);
    this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(log_in$frame, 8, Lit91, 8194);
    this.add$Mnto$Mnevents = new ModuleMethod(log_in$frame, 9, Lit92, 8194);
    this.add$Mnto$Mncomponents = new ModuleMethod(log_in$frame, 10, Lit93, 16388);
    this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(log_in$frame, 11, Lit94, 8194);
    this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(log_in$frame, 12, Lit95, 4097);
    this.send$Mnerror = new ModuleMethod(log_in$frame, 13, Lit96, 4097);
    this.process$Mnexception = new ModuleMethod(log_in$frame, 14, "process-exception", 4097);
    this.dispatchEvent = new ModuleMethod(log_in$frame, 15, Lit97, 16388);
    this.dispatchGenericEvent = new ModuleMethod(log_in$frame, 16, Lit98, 16388);
    this.lookup$Mnhandler = new ModuleMethod(log_in$frame, 17, Lit99, 8194);
    ModuleMethod moduleMethod = new ModuleMethod(log_in$frame, 18, null, 0);
    moduleMethod.setProperty("source-location", "/tmp/runtime6993135000179593734.scm:622");
    lambda$Fn1 = moduleMethod;
    this.$define = new ModuleMethod(log_in$frame, 19, "$define", 0);
    lambda$Fn2 = new ModuleMethod(log_in$frame, 20, null, 0);
    lambda$Fn3 = new ModuleMethod(log_in$frame, 21, null, 0);
    lambda$Fn4 = new ModuleMethod(log_in$frame, 22, null, 0);
    lambda$Fn5 = new ModuleMethod(log_in$frame, 23, null, 0);
    lambda$Fn6 = new ModuleMethod(log_in$frame, 24, null, 0);
    lambda$Fn7 = new ModuleMethod(log_in$frame, 25, null, 0);
    lambda$Fn8 = new ModuleMethod(log_in$frame, 26, null, 0);
    lambda$Fn9 = new ModuleMethod(log_in$frame, 27, null, 0);
    lambda$Fn10 = new ModuleMethod(log_in$frame, 28, null, 0);
    lambda$Fn11 = new ModuleMethod(log_in$frame, 29, null, 0);
    lambda$Fn12 = new ModuleMethod(log_in$frame, 30, null, 0);
    lambda$Fn13 = new ModuleMethod(log_in$frame, 31, null, 0);
    lambda$Fn14 = new ModuleMethod(log_in$frame, 32, null, 0);
    lambda$Fn15 = new ModuleMethod(log_in$frame, 33, null, 0);
    lambda$Fn16 = new ModuleMethod(log_in$frame, 34, null, 0);
    lambda$Fn17 = new ModuleMethod(log_in$frame, 35, null, 0);
    lambda$Fn18 = new ModuleMethod(log_in$frame, 36, null, 0);
    lambda$Fn19 = new ModuleMethod(log_in$frame, 37, null, 0);
    lambda$Fn20 = new ModuleMethod(log_in$frame, 38, null, 0);
    lambda$Fn21 = new ModuleMethod(log_in$frame, 39, null, 0);
    lambda$Fn22 = new ModuleMethod(log_in$frame, 40, null, 0);
    lambda$Fn23 = new ModuleMethod(log_in$frame, 41, null, 0);
    lambda$Fn24 = new ModuleMethod(log_in$frame, 42, null, 0);
    lambda$Fn25 = new ModuleMethod(log_in$frame, 43, null, 0);
    lambda$Fn26 = new ModuleMethod(log_in$frame, 44, null, 0);
    lambda$Fn27 = new ModuleMethod(log_in$frame, 45, null, 0);
    lambda$Fn28 = new ModuleMethod(log_in$frame, 46, null, 0);
    this.Button1$Click = new ModuleMethod(log_in$frame, 47, Lit84, 0);
  }
  
  static Object lambda10() {
    return runtime.setAndCoerceProperty$Ex(Lit38, Lit17, Lit39, Lit5);
  }
  
  static Object lambda11() {
    return runtime.setAndCoerceProperty$Ex(Lit38, Lit17, Lit39, Lit5);
  }
  
  static Object lambda12() {
    return runtime.setAndCoerceProperty$Ex(Lit42, Lit3, Lit4, Lit5);
  }
  
  static Object lambda13() {
    return runtime.setAndCoerceProperty$Ex(Lit42, Lit3, Lit4, Lit5);
  }
  
  static Object lambda14() {
    runtime.setAndCoerceProperty$Ex(Lit45, Lit31, Lit32, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit45, Lit33, "Username/Email:", Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit45, Lit34, Lit46, Lit5);
  }
  
  static Object lambda15() {
    runtime.setAndCoerceProperty$Ex(Lit45, Lit31, Lit32, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit45, Lit33, "Username/Email:", Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit45, Lit34, Lit46, Lit5);
  }
  
  static Object lambda16() {
    return runtime.setAndCoerceProperty$Ex(Lit52, Lit17, Lit53, Lit5);
  }
  
  static Object lambda17() {
    return runtime.setAndCoerceProperty$Ex(Lit52, Lit17, Lit53, Lit5);
  }
  
  static Object lambda18() {
    return runtime.setAndCoerceProperty$Ex(Lit56, Lit3, Lit4, Lit5);
  }
  
  static Object lambda19() {
    return runtime.setAndCoerceProperty$Ex(Lit56, Lit3, Lit4, Lit5);
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
    runtime.setAndCoerceProperty$Ex(Lit59, Lit31, Lit32, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit59, Lit33, "Password:     ", Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit59, Lit34, Lit60, Lit5);
  }
  
  static Object lambda21() {
    runtime.setAndCoerceProperty$Ex(Lit59, Lit31, Lit32, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit59, Lit33, "Password:     ", Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit59, Lit34, Lit60, Lit5);
  }
  
  static Object lambda22() {
    runtime.setAndCoerceProperty$Ex(Lit63, Lit17, Lit64, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit63, Lit24, Lit65, Lit5);
  }
  
  static Object lambda23() {
    runtime.setAndCoerceProperty$Ex(Lit63, Lit17, Lit64, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit63, Lit24, Lit65, Lit5);
  }
  
  static Object lambda24() {
    return runtime.setAndCoerceProperty$Ex(Lit71, Lit17, Lit72, Lit5);
  }
  
  static Object lambda25() {
    return runtime.setAndCoerceProperty$Ex(Lit71, Lit17, Lit72, Lit5);
  }
  
  static Object lambda26() {
    runtime.setAndCoerceProperty$Ex(Lit75, Lit22, Lit23, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit75, Lit24, Lit76, Lit5);
  }
  
  static Object lambda27() {
    runtime.setAndCoerceProperty$Ex(Lit75, Lit22, Lit23, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit75, Lit24, Lit76, Lit5);
  }
  
  static Object lambda28() {
    runtime.setAndCoerceProperty$Ex(Lit79, Lit8, Lit80, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit79, Lit31, Lit32, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit79, Lit33, "Log in", Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit79, Lit34, Lit81, Lit5);
  }
  
  static Object lambda29() {
    runtime.setAndCoerceProperty$Ex(Lit79, Lit8, Lit80, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit79, Lit31, Lit32, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit79, Lit33, "Log in", Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit79, Lit34, Lit81, Lit5);
  }
  
  static Object lambda3() {
    runtime.setAndCoerceProperty$Ex(Lit0, Lit3, Lit4, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit6, "GROW_game_app", Lit7);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit8, Lit9, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit10, Boolean.TRUE, Lit11);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit12, "Responsive", Lit7);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit13, "Log_in", Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit0, Lit14, Boolean.FALSE, Lit11);
  }
  
  static Object lambda4() {
    return runtime.setAndCoerceProperty$Ex(Lit16, Lit17, Lit18, Lit5);
  }
  
  static Object lambda5() {
    return runtime.setAndCoerceProperty$Ex(Lit16, Lit17, Lit18, Lit5);
  }
  
  static Object lambda6() {
    runtime.setAndCoerceProperty$Ex(Lit21, Lit22, Lit23, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit21, Lit24, Lit25, Lit5);
  }
  
  static Object lambda7() {
    runtime.setAndCoerceProperty$Ex(Lit21, Lit22, Lit23, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit21, Lit24, Lit25, Lit5);
  }
  
  static Object lambda8() {
    runtime.setAndCoerceProperty$Ex(Lit28, Lit29, Boolean.TRUE, Lit11);
    runtime.setAndCoerceProperty$Ex(Lit28, Lit30, Boolean.TRUE, Lit11);
    runtime.setAndCoerceProperty$Ex(Lit28, Lit31, Lit32, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit28, Lit33, "Log in to your account", Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit28, Lit34, Lit35, Lit5);
  }
  
  static Object lambda9() {
    runtime.setAndCoerceProperty$Ex(Lit28, Lit29, Boolean.TRUE, Lit11);
    runtime.setAndCoerceProperty$Ex(Lit28, Lit30, Boolean.TRUE, Lit11);
    runtime.setAndCoerceProperty$Ex(Lit28, Lit31, Lit32, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit28, Lit33, "Log in to your account", Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit28, Lit34, Lit35, Lit5);
  }
  
  public void $define() {
    Language.setDefaults((Language)Scheme.getInstance());
    try {
      run();
    } catch (Exception exception) {
      androidLogForm(exception.getMessage());
      processException(exception);
    } 
    Log_in = this;
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
    return runtime.callYailPrimitive(runtime.open$Mnanother$Mnscreen, LList.list1("Profile"), Lit83, "open another screen");
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
    Log_in = null;
    this.form$Mnname$Mnsymbol = (Symbol)Lit0;
    this.events$Mnto$Mnregister = LList.Empty;
    this.components$Mnto$Mncreate = LList.Empty;
    this.global$Mnvars$Mnto$Mncreate = LList.Empty;
    this.form$Mndo$Mnafter$Mncreation = LList.Empty;
    runtime.$instance.run();
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.setAndCoerceProperty$Ex(Lit0, Lit3, Lit4, Lit5);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit6, "GROW_game_app", Lit7);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit8, Lit9, Lit5);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit10, Boolean.TRUE, Lit11);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit12, "Responsive", Lit7);
      runtime.setAndCoerceProperty$Ex(Lit0, Lit13, "Log_in", Lit7);
      Values.writeValues(runtime.setAndCoerceProperty$Ex(Lit0, Lit14, Boolean.FALSE, Lit11), consumer);
    } else {
      addToFormDoAfterCreation(new Promise((Procedure)lambda$Fn2));
    } 
    this.VerticalArrangement1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit15, Lit16, lambda$Fn3), consumer);
    } else {
      addToComponents(Lit0, Lit19, Lit16, lambda$Fn4);
    } 
    this.HorizontalArrangement1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit20, Lit21, lambda$Fn5), consumer);
    } else {
      addToComponents(Lit0, Lit26, Lit21, lambda$Fn6);
    } 
    this.Label1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit21, Lit27, Lit28, lambda$Fn7), consumer);
    } else {
      addToComponents(Lit21, Lit36, Lit28, lambda$Fn8);
    } 
    this.VerticalArrangement2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit37, Lit38, lambda$Fn9), consumer);
    } else {
      addToComponents(Lit0, Lit40, Lit38, lambda$Fn10);
    } 
    this.HorizontalArrangement2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit41, Lit42, lambda$Fn11), consumer);
    } else {
      addToComponents(Lit0, Lit43, Lit42, lambda$Fn12);
    } 
    this.Label2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit42, Lit44, Lit45, lambda$Fn13), consumer);
    } else {
      addToComponents(Lit42, Lit47, Lit45, lambda$Fn14);
    } 
    this.TextBox1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit42, Lit48, Lit49, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit42, Lit50, Lit49, Boolean.FALSE);
    } 
    this.VerticalArrangement4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit51, Lit52, lambda$Fn15), consumer);
    } else {
      addToComponents(Lit0, Lit54, Lit52, lambda$Fn16);
    } 
    this.HorizontalArrangement3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit55, Lit56, lambda$Fn17), consumer);
    } else {
      addToComponents(Lit0, Lit57, Lit56, lambda$Fn18);
    } 
    this.Label3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit56, Lit58, Lit59, lambda$Fn19), consumer);
    } else {
      addToComponents(Lit56, Lit61, Lit59, lambda$Fn20);
    } 
    this.HorizontalArrangement5 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit56, Lit62, Lit63, lambda$Fn21), consumer);
    } else {
      addToComponents(Lit56, Lit66, Lit63, lambda$Fn22);
    } 
    this.PasswordTextBox1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit56, Lit67, Lit68, Boolean.FALSE), consumer);
    } else {
      addToComponents(Lit56, Lit69, Lit68, Boolean.FALSE);
    } 
    this.VerticalArrangement3 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit70, Lit71, lambda$Fn23), consumer);
    } else {
      addToComponents(Lit0, Lit73, Lit71, lambda$Fn24);
    } 
    this.HorizontalArrangement4 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit74, Lit75, lambda$Fn25), consumer);
    } else {
      addToComponents(Lit0, Lit77, Lit75, lambda$Fn26);
    } 
    this.Button1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit75, Lit78, Lit79, lambda$Fn27), consumer);
    } else {
      addToComponents(Lit75, Lit82, Lit79, lambda$Fn28);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      runtime.addToCurrentFormEnvironment((Symbol)Lit84, this.Button1$Click);
    } else {
      addToFormEnvironment((Symbol)Lit84, this.Button1$Click);
    } 
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      EventDispatcher.registerEventForDelegation((HandlesEventDispatching)runtime.$Stthis$Mnform$St, "Button1", "Click");
    } else {
      addToEvents(Lit79, Lit85);
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/appinventor/ai_notifications_eoteam/GROW_game_app/Log_in.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
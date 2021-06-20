package appinventor.ai_notifications_eoteam.GROW_game_app;

import android.os.Bundle;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Image;
import com.google.appinventor.components.runtime.Label;
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

public class Missions extends Form implements Runnable {
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
  
  static final SimpleSymbol Lit19;
  
  static final SimpleSymbol Lit2;
  
  static final IntNum Lit20;
  
  static final FString Lit21;
  
  static final FString Lit22;
  
  static final SimpleSymbol Lit23;
  
  static final SimpleSymbol Lit24;
  
  static final SimpleSymbol Lit25;
  
  static final IntNum Lit26;
  
  static final SimpleSymbol Lit27;
  
  static final SimpleSymbol Lit28;
  
  static final IntNum Lit29;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit30;
  
  static final IntNum Lit31;
  
  static final FString Lit32;
  
  static final FString Lit33;
  
  static final SimpleSymbol Lit34;
  
  static final IntNum Lit35;
  
  static final FString Lit36;
  
  static final FString Lit37;
  
  static final SimpleSymbol Lit38;
  
  static final IntNum Lit39;
  
  static final IntNum Lit4;
  
  static final SimpleSymbol Lit40;
  
  static final IntNum Lit41;
  
  static final FString Lit42;
  
  static final SimpleSymbol Lit43;
  
  static final SimpleSymbol Lit44;
  
  static final SimpleSymbol Lit45;
  
  static final SimpleSymbol Lit46;
  
  static final SimpleSymbol Lit47;
  
  static final SimpleSymbol Lit48;
  
  static final SimpleSymbol Lit49;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit50;
  
  static final SimpleSymbol Lit51;
  
  static final SimpleSymbol Lit52;
  
  static final SimpleSymbol Lit53;
  
  static final SimpleSymbol Lit54;
  
  static final SimpleSymbol Lit55;
  
  static final SimpleSymbol Lit56 = (SimpleSymbol)(new SimpleSymbol("lookup-handler")).readResolve();
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final IntNum Lit9;
  
  public static Missions Missions;
  
  static final ModuleMethod lambda$Fn1;
  
  static final ModuleMethod lambda$Fn10;
  
  static final ModuleMethod lambda$Fn2;
  
  static final ModuleMethod lambda$Fn3;
  
  static final ModuleMethod lambda$Fn4;
  
  static final ModuleMethod lambda$Fn5;
  
  static final ModuleMethod lambda$Fn6;
  
  static final ModuleMethod lambda$Fn7;
  
  static final ModuleMethod lambda$Fn8;
  
  static final ModuleMethod lambda$Fn9;
  
  public Boolean $Stdebug$Mnform$St;
  
  public final ModuleMethod $define;
  
  public HorizontalArrangement HorizontalArrangement1;
  
  public HorizontalArrangement HorizontalArrangement2;
  
  public Image Image1;
  
  public Label Label1;
  
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
    Lit55 = (SimpleSymbol)(new SimpleSymbol("dispatchGenericEvent")).readResolve();
    Lit54 = (SimpleSymbol)(new SimpleSymbol("dispatchEvent")).readResolve();
    Lit53 = (SimpleSymbol)(new SimpleSymbol("send-error")).readResolve();
    Lit52 = (SimpleSymbol)(new SimpleSymbol("add-to-form-do-after-creation")).readResolve();
    Lit51 = (SimpleSymbol)(new SimpleSymbol("add-to-global-vars")).readResolve();
    Lit50 = (SimpleSymbol)(new SimpleSymbol("add-to-components")).readResolve();
    Lit49 = (SimpleSymbol)(new SimpleSymbol("add-to-events")).readResolve();
    Lit48 = (SimpleSymbol)(new SimpleSymbol("add-to-global-var-environment")).readResolve();
    Lit47 = (SimpleSymbol)(new SimpleSymbol("is-bound-in-form-environment")).readResolve();
    Lit46 = (SimpleSymbol)(new SimpleSymbol("lookup-in-form-environment")).readResolve();
    Lit45 = (SimpleSymbol)(new SimpleSymbol("add-to-form-environment")).readResolve();
    Lit44 = (SimpleSymbol)(new SimpleSymbol("android-log-form")).readResolve();
    Lit43 = (SimpleSymbol)(new SimpleSymbol("get-simple-name")).readResolve();
    Lit42 = new FString("com.google.appinventor.components.runtime.Image");
    Lit41 = IntNum.make(-1080);
    Lit40 = (SimpleSymbol)(new SimpleSymbol("Picture")).readResolve();
    Lit39 = IntNum.make(-1050);
    Lit38 = (SimpleSymbol)(new SimpleSymbol("Image1")).readResolve();
    Lit37 = new FString("com.google.appinventor.components.runtime.Image");
    Lit36 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit35 = IntNum.make(-1005);
    Lit34 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement2")).readResolve();
    Lit33 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit32 = new FString("com.google.appinventor.components.runtime.Label");
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = -1;
    Lit31 = IntNum.make(arrayOfInt);
    Lit30 = (SimpleSymbol)(new SimpleSymbol("TextColor")).readResolve();
    Lit29 = IntNum.make(1);
    Lit28 = (SimpleSymbol)(new SimpleSymbol("TextAlignment")).readResolve();
    Lit27 = (SimpleSymbol)(new SimpleSymbol("Text")).readResolve();
    Lit26 = IntNum.make(18);
    Lit25 = (SimpleSymbol)(new SimpleSymbol("FontSize")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("FontBold")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("Label1")).readResolve();
    Lit22 = new FString("com.google.appinventor.components.runtime.Label");
    Lit21 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit20 = IntNum.make(-1005);
    Lit19 = (SimpleSymbol)(new SimpleSymbol("Width")).readResolve();
    Lit18 = IntNum.make(-1005);
    Lit17 = (SimpleSymbol)(new SimpleSymbol("Height")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("HorizontalArrangement1")).readResolve();
    Lit15 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    Lit14 = (SimpleSymbol)(new SimpleSymbol("TitleVisible")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("Title")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("Sizing")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("boolean")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("ShowListsAsJson")).readResolve();
    arrayOfInt = new int[2];
    arrayOfInt[0] = -15227642;
    Lit9 = IntNum.make(arrayOfInt);
    Lit8 = (SimpleSymbol)(new SimpleSymbol("BackgroundColor")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("text")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("AppName")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("number")).readResolve();
    Lit4 = IntNum.make(3);
    Lit3 = (SimpleSymbol)(new SimpleSymbol("AlignHorizontal")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("*the-null-value*")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("getMessage")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("Missions")).readResolve();
  }
  
  public Missions() {
    ModuleInfo.register(this);
    Missions$frame missions$frame = new Missions$frame();
    missions$frame.$main = this;
    this.get$Mnsimple$Mnname = new ModuleMethod(missions$frame, 1, Lit43, 4097);
    this.onCreate = new ModuleMethod(missions$frame, 2, "onCreate", 4097);
    this.android$Mnlog$Mnform = new ModuleMethod(missions$frame, 3, Lit44, 4097);
    this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(missions$frame, 4, Lit45, 8194);
    this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(missions$frame, 5, Lit46, 8193);
    this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(missions$frame, 7, Lit47, 4097);
    this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(missions$frame, 8, Lit48, 8194);
    this.add$Mnto$Mnevents = new ModuleMethod(missions$frame, 9, Lit49, 8194);
    this.add$Mnto$Mncomponents = new ModuleMethod(missions$frame, 10, Lit50, 16388);
    this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(missions$frame, 11, Lit51, 8194);
    this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(missions$frame, 12, Lit52, 4097);
    this.send$Mnerror = new ModuleMethod(missions$frame, 13, Lit53, 4097);
    this.process$Mnexception = new ModuleMethod(missions$frame, 14, "process-exception", 4097);
    this.dispatchEvent = new ModuleMethod(missions$frame, 15, Lit54, 16388);
    this.dispatchGenericEvent = new ModuleMethod(missions$frame, 16, Lit55, 16388);
    this.lookup$Mnhandler = new ModuleMethod(missions$frame, 17, Lit56, 8194);
    ModuleMethod moduleMethod = new ModuleMethod(missions$frame, 18, null, 0);
    moduleMethod.setProperty("source-location", "/tmp/runtime6993135000179593734.scm:622");
    lambda$Fn1 = moduleMethod;
    this.$define = new ModuleMethod(missions$frame, 19, "$define", 0);
    lambda$Fn2 = new ModuleMethod(missions$frame, 20, null, 0);
    lambda$Fn3 = new ModuleMethod(missions$frame, 21, null, 0);
    lambda$Fn4 = new ModuleMethod(missions$frame, 22, null, 0);
    lambda$Fn5 = new ModuleMethod(missions$frame, 23, null, 0);
    lambda$Fn6 = new ModuleMethod(missions$frame, 24, null, 0);
    lambda$Fn7 = new ModuleMethod(missions$frame, 25, null, 0);
    lambda$Fn8 = new ModuleMethod(missions$frame, 26, null, 0);
    lambda$Fn9 = new ModuleMethod(missions$frame, 27, null, 0);
    lambda$Fn10 = new ModuleMethod(missions$frame, 28, null, 0);
  }
  
  static Object lambda10() {
    runtime.setAndCoerceProperty$Ex(Lit38, Lit17, Lit39, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit38, Lit40, "Attiki.jpg", Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit38, Lit19, Lit41, Lit5);
  }
  
  static Object lambda11() {
    runtime.setAndCoerceProperty$Ex(Lit38, Lit17, Lit39, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit38, Lit40, "Attiki.jpg", Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit38, Lit19, Lit41, Lit5);
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
  
  static Object lambda3() {
    runtime.setAndCoerceProperty$Ex(Lit0, Lit3, Lit4, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit6, "GROW_game_app", Lit7);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit8, Lit9, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit10, Boolean.TRUE, Lit11);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit12, "Responsive", Lit7);
    runtime.setAndCoerceProperty$Ex(Lit0, Lit13, "Missions", Lit7);
    return runtime.setAndCoerceProperty$Ex(Lit0, Lit14, Boolean.FALSE, Lit11);
  }
  
  static Object lambda4() {
    runtime.setAndCoerceProperty$Ex(Lit16, Lit17, Lit18, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit16, Lit19, Lit20, Lit5);
  }
  
  static Object lambda5() {
    runtime.setAndCoerceProperty$Ex(Lit16, Lit17, Lit18, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit16, Lit19, Lit20, Lit5);
  }
  
  static Object lambda6() {
    runtime.setAndCoerceProperty$Ex(Lit23, Lit24, Boolean.TRUE, Lit11);
    runtime.setAndCoerceProperty$Ex(Lit23, Lit25, Lit26, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit23, Lit27, "Missions", Lit7);
    runtime.setAndCoerceProperty$Ex(Lit23, Lit28, Lit29, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit23, Lit30, Lit31, Lit5);
  }
  
  static Object lambda7() {
    runtime.setAndCoerceProperty$Ex(Lit23, Lit24, Boolean.TRUE, Lit11);
    runtime.setAndCoerceProperty$Ex(Lit23, Lit25, Lit26, Lit5);
    runtime.setAndCoerceProperty$Ex(Lit23, Lit27, "Missions", Lit7);
    runtime.setAndCoerceProperty$Ex(Lit23, Lit28, Lit29, Lit5);
    return runtime.setAndCoerceProperty$Ex(Lit23, Lit30, Lit31, Lit5);
  }
  
  static Object lambda8() {
    return runtime.setAndCoerceProperty$Ex(Lit34, Lit17, Lit35, Lit5);
  }
  
  static Object lambda9() {
    return runtime.setAndCoerceProperty$Ex(Lit34, Lit17, Lit35, Lit5);
  }
  
  public void $define() {
    Language.setDefaults((Language)Scheme.getInstance());
    try {
      run();
    } catch (Exception exception) {
      androidLogForm(exception.getMessage());
      processException(exception);
    } 
    Missions = this;
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
    Missions = null;
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
      runtime.setAndCoerceProperty$Ex(Lit0, Lit13, "Missions", Lit7);
      Values.writeValues(runtime.setAndCoerceProperty$Ex(Lit0, Lit14, Boolean.FALSE, Lit11), consumer);
    } else {
      addToFormDoAfterCreation(new Promise((Procedure)lambda$Fn2));
    } 
    this.HorizontalArrangement1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit15, Lit16, lambda$Fn3), consumer);
    } else {
      addToComponents(Lit0, Lit21, Lit16, lambda$Fn4);
    } 
    this.Label1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit22, Lit23, lambda$Fn5), consumer);
    } else {
      addToComponents(Lit0, Lit32, Lit23, lambda$Fn6);
    } 
    this.HorizontalArrangement2 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit33, Lit34, lambda$Fn7), consumer);
    } else {
      addToComponents(Lit0, Lit36, Lit34, lambda$Fn8);
    } 
    this.Image1 = null;
    if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
      Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit37, Lit38, lambda$Fn9), consumer);
    } else {
      addToComponents(Lit0, Lit42, Lit38, lambda$Fn10);
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/appinventor/ai_notifications_eoteam/GROW_game_app/Missions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
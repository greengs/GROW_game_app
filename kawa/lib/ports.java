package kawa.lib;

import gnu.bytecode.ClassType;
import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.EofClass;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.InPort;
import gnu.mapping.Location;
import gnu.mapping.LocationProc;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.LineBufferedReader;
import gnu.text.Path;
import gnu.text.SyntaxException;
import java.io.Writer;
import kawa.standard.Scheme;
import kawa.standard.char_ready_p;
import kawa.standard.read_line;

public class ports extends ModuleBody {
  public static final ports $instance;
  
  static final SimpleSymbol Lit0;
  
  static final ClassType Lit1;
  
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
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SimpleSymbol Lit22;
  
  static final SimpleSymbol Lit23;
  
  static final SimpleSymbol Lit24;
  
  static final SimpleSymbol Lit25;
  
  static final SimpleSymbol Lit26;
  
  static final SimpleSymbol Lit27;
  
  static final SimpleSymbol Lit28;
  
  static final SimpleSymbol Lit29;
  
  static final ClassType Lit3;
  
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
  
  static final SimpleSymbol Lit44;
  
  static final SimpleSymbol Lit45 = (SimpleSymbol)(new SimpleSymbol("transcript-off")).readResolve();
  
  static final Keyword Lit5;
  
  static final IntNum Lit6;
  
  static final Char Lit7;
  
  static final Char Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod call$Mnwith$Mninput$Mnfile;
  
  public static final ModuleMethod call$Mnwith$Mninput$Mnstring;
  
  public static final ModuleMethod call$Mnwith$Mnoutput$Mnfile;
  
  public static final ModuleMethod call$Mnwith$Mnoutput$Mnstring;
  
  public static final ModuleMethod char$Mnready$Qu;
  
  public static final ModuleMethod close$Mninput$Mnport;
  
  public static final ModuleMethod close$Mnoutput$Mnport;
  
  public static final LocationProc current$Mnerror$Mnport;
  
  public static final LocationProc current$Mninput$Mnport;
  
  public static final LocationProc current$Mnoutput$Mnport;
  
  public static final ModuleMethod default$Mnprompter;
  
  public static final ModuleMethod display;
  
  public static final ModuleMethod eof$Mnobject$Qu;
  
  public static final ModuleMethod force$Mnoutput;
  
  public static final ModuleMethod get$Mnoutput$Mnstring;
  
  public static final ModuleMethod input$Mnport$Mncolumn$Mnnumber;
  
  public static final GenericProc input$Mnport$Mnline$Mnnumber;
  
  static final ModuleMethod input$Mnport$Mnline$Mnnumber$Fn5;
  
  public static final GenericProc input$Mnport$Mnprompter;
  
  static final ModuleMethod input$Mnport$Mnprompter$Fn6;
  
  public static final ModuleMethod input$Mnport$Mnread$Mnstate;
  
  public static final ModuleMethod input$Mnport$Qu;
  
  static final ModuleMethod lambda$Fn1;
  
  static final ModuleMethod lambda$Fn2;
  
  static final ModuleMethod lambda$Fn3;
  
  public static final ModuleMethod newline;
  
  public static final ModuleMethod open$Mninput$Mnfile;
  
  public static final ModuleMethod open$Mninput$Mnstring;
  
  public static final ModuleMethod open$Mnoutput$Mnfile;
  
  public static final ModuleMethod open$Mnoutput$Mnstring;
  
  public static final ModuleMethod output$Mnport$Qu;
  
  public static final ModuleMethod port$Mncolumn;
  
  public static final GenericProc port$Mnline;
  
  static final ModuleMethod port$Mnline$Fn4;
  
  public static final ModuleMethod read;
  
  public static final ModuleMethod read$Mnline;
  
  public static final ModuleMethod set$Mninput$Mnport$Mnline$Mnnumber$Ex;
  
  public static final ModuleMethod set$Mninput$Mnport$Mnprompter$Ex;
  
  public static final ModuleMethod set$Mnport$Mnline$Ex;
  
  public static final ModuleMethod transcript$Mnoff;
  
  public static final ModuleMethod transcript$Mnon;
  
  public static final ModuleMethod with$Mninput$Mnfrom$Mnfile;
  
  public static final ModuleMethod with$Mnoutput$Mnto$Mnfile;
  
  public static final ModuleMethod write;
  
  public static final ModuleMethod write$Mnchar;
  
  static {
    Lit44 = (SimpleSymbol)(new SimpleSymbol("transcript-on")).readResolve();
    Lit43 = (SimpleSymbol)(new SimpleSymbol("read-line")).readResolve();
    Lit42 = (SimpleSymbol)(new SimpleSymbol("read")).readResolve();
    Lit41 = (SimpleSymbol)(new SimpleSymbol("close-output-port")).readResolve();
    Lit40 = (SimpleSymbol)(new SimpleSymbol("close-input-port")).readResolve();
    Lit39 = (SimpleSymbol)(new SimpleSymbol("input-port-prompter")).readResolve();
    Lit38 = (SimpleSymbol)(new SimpleSymbol("set-input-port-prompter!")).readResolve();
    Lit37 = (SimpleSymbol)(new SimpleSymbol("default-prompter")).readResolve();
    Lit36 = (SimpleSymbol)(new SimpleSymbol("input-port-column-number")).readResolve();
    Lit35 = (SimpleSymbol)(new SimpleSymbol("port-column")).readResolve();
    Lit34 = (SimpleSymbol)(new SimpleSymbol("input-port-line-number")).readResolve();
    Lit33 = (SimpleSymbol)(new SimpleSymbol("set-input-port-line-number!")).readResolve();
    Lit32 = (SimpleSymbol)(new SimpleSymbol("port-line")).readResolve();
    Lit31 = (SimpleSymbol)(new SimpleSymbol("set-port-line!")).readResolve();
    Lit30 = (SimpleSymbol)(new SimpleSymbol("input-port-read-state")).readResolve();
    Lit29 = (SimpleSymbol)(new SimpleSymbol("display")).readResolve();
    Lit28 = (SimpleSymbol)(new SimpleSymbol("write")).readResolve();
    Lit27 = (SimpleSymbol)(new SimpleSymbol("char-ready?")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("eof-object?")).readResolve();
    Lit25 = (SimpleSymbol)(new SimpleSymbol("newline")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("force-output")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("call-with-output-string")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("call-with-input-string")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("get-output-string")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("open-output-string")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("open-input-string")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("write-char")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("output-port?")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("input-port?")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("with-output-to-file")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("with-input-from-file")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("call-with-output-file")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("call-with-input-file")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("open-output-file")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("open-input-file")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("trim")).readResolve();
    Lit8 = Char.make(32);
    Lit7 = Char.make(10);
    Lit6 = IntNum.make(1);
    Lit5 = Keyword.make("setter");
    Lit4 = (SimpleSymbol)(new SimpleSymbol("current-error-port")).readResolve();
    Lit3 = ClassType.make("gnu.mapping.OutPort");
    Lit2 = (SimpleSymbol)(new SimpleSymbol("current-output-port")).readResolve();
    Lit1 = ClassType.make("gnu.mapping.InPort");
    Lit0 = (SimpleSymbol)(new SimpleSymbol("current-input-port")).readResolve();
    $instance = new ports();
    ports ports1 = $instance;
    open$Mninput$Mnfile = new ModuleMethod(ports1, 1, Lit10, 4097);
    open$Mnoutput$Mnfile = new ModuleMethod(ports1, 2, Lit11, 4097);
    call$Mnwith$Mninput$Mnfile = new ModuleMethod(ports1, 3, Lit12, 8194);
    call$Mnwith$Mnoutput$Mnfile = new ModuleMethod(ports1, 4, Lit13, 8194);
    with$Mninput$Mnfrom$Mnfile = new ModuleMethod(ports1, 5, Lit14, 8194);
    with$Mnoutput$Mnto$Mnfile = new ModuleMethod(ports1, 6, Lit15, 8194);
    input$Mnport$Qu = new ModuleMethod(ports1, 7, Lit16, 4097);
    output$Mnport$Qu = new ModuleMethod(ports1, 8, Lit17, 4097);
    lambda$Fn1 = new ModuleMethod(ports1, 9, null, 4097);
    lambda$Fn2 = new ModuleMethod(ports1, 10, null, 4097);
    lambda$Fn3 = new ModuleMethod(ports1, 11, null, 4097);
    write$Mnchar = new ModuleMethod(ports1, 12, Lit18, 8193);
    open$Mninput$Mnstring = new ModuleMethod(ports1, 14, Lit19, 4097);
    open$Mnoutput$Mnstring = new ModuleMethod(ports1, 15, Lit20, 0);
    get$Mnoutput$Mnstring = new ModuleMethod(ports1, 16, Lit21, 4097);
    call$Mnwith$Mninput$Mnstring = new ModuleMethod(ports1, 17, Lit22, 8194);
    call$Mnwith$Mnoutput$Mnstring = new ModuleMethod(ports1, 18, Lit23, 4097);
    force$Mnoutput = new ModuleMethod(ports1, 19, Lit24, 4096);
    newline = new ModuleMethod(ports1, 21, Lit25, 4096);
    eof$Mnobject$Qu = new ModuleMethod(ports1, 23, Lit26, 4097);
    char$Mnready$Qu = new ModuleMethod(ports1, 24, Lit27, 4096);
    write = new ModuleMethod(ports1, 26, Lit28, 8193);
    display = new ModuleMethod(ports1, 28, Lit29, 8193);
    input$Mnport$Mnread$Mnstate = new ModuleMethod(ports1, 30, Lit30, 4097);
    set$Mnport$Mnline$Ex = new ModuleMethod(ports1, 31, Lit31, 8194);
    port$Mnline$Fn4 = new ModuleMethod(ports1, 32, Lit32, 4097);
    set$Mninput$Mnport$Mnline$Mnnumber$Ex = new ModuleMethod(ports1, 33, Lit33, 8194);
    input$Mnport$Mnline$Mnnumber$Fn5 = new ModuleMethod(ports1, 34, Lit34, 4097);
    port$Mncolumn = new ModuleMethod(ports1, 35, Lit35, 4097);
    input$Mnport$Mncolumn$Mnnumber = new ModuleMethod(ports1, 36, Lit36, 4097);
    default$Mnprompter = new ModuleMethod(ports1, 37, Lit37, 4097);
    set$Mninput$Mnport$Mnprompter$Ex = new ModuleMethod(ports1, 38, Lit38, 8194);
    input$Mnport$Mnprompter$Fn6 = new ModuleMethod(ports1, 39, Lit39, 4097);
    close$Mninput$Mnport = new ModuleMethod(ports1, 40, Lit40, 4097);
    close$Mnoutput$Mnport = new ModuleMethod(ports1, 41, Lit41, 4097);
    read = new ModuleMethod(ports1, 42, Lit42, 4096);
    read$Mnline = new ModuleMethod(ports1, 44, Lit43, 8192);
    transcript$Mnon = new ModuleMethod(ports1, 47, Lit44, 4097);
    transcript$Mnoff = new ModuleMethod(ports1, 48, Lit45, 0);
    $instance.run();
  }
  
  public ports() {
    ModuleInfo.register(this);
  }
  
  public static Object callWithInputFile(Path paramPath, Procedure paramProcedure) {
    InPort inPort = openInputFile(paramPath);
    try {
      return paramProcedure.apply1(inPort);
    } finally {
      closeInputPort(inPort);
    } 
  }
  
  public static Object callWithInputString(CharSequence paramCharSequence, Procedure paramProcedure) {
    CharArrayInPort charArrayInPort2;
    if (paramCharSequence == null) {
      paramCharSequence = null;
      charArrayInPort2 = new CharArrayInPort((String)paramCharSequence);
      object = paramProcedure.apply1(charArrayInPort2);
      closeInputPort((InPort)charArrayInPort2);
      return object;
    } 
    String str = charArrayInPort2.toString();
    CharArrayInPort charArrayInPort1 = new CharArrayInPort(str);
    Object object = object.apply1(charArrayInPort1);
    closeInputPort((InPort)charArrayInPort1);
    return object;
  }
  
  public static Object callWithOutputFile(Path paramPath, Procedure paramProcedure) {
    OutPort outPort = openOutputFile(paramPath);
    try {
      return paramProcedure.apply1(outPort);
    } finally {
      closeOutputPort(outPort);
    } 
  }
  
  public static Object callWithOutputString(Procedure paramProcedure) {
    CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
    paramProcedure.apply1(charArrayOutPort);
    char[] arrayOfChar = charArrayOutPort.toCharArray();
    charArrayOutPort.close();
    return new FString(arrayOfChar);
  }
  
  public static Object closeInputPort(InPort paramInPort) {
    paramInPort.close();
    return Values.empty;
  }
  
  public static Object closeOutputPort(OutPort paramOutPort) {
    paramOutPort.close();
    return Values.empty;
  }
  
  public static Object defaultPrompter(Object paramObject) {
    FString fString;
    char c = inputPortReadState(paramObject);
    if (characters.isChar$Eq(Char.make(c), Lit7))
      return ""; 
    if (characters.isChar$Eq(Char.make(c), Lit8)) {
      String str = "#|kawa:";
    } else {
      fString = strings.stringAppend(new Object[] { "#|", strings.makeString(1, Char.make(c)), "---:" });
    } 
    paramObject = input$Mnport$Mnline$Mnnumber.apply1(paramObject);
    try {
      Number number = (Number)paramObject;
      return strings.stringAppend(new Object[] { fString, numbers.number$To$String(number), "|# " });
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "number->string", 0, paramObject);
    } 
  }
  
  public static void display(Object paramObject) {
    display(paramObject, current$Mnoutput$Mnport.apply0());
  }
  
  public static void display(Object paramObject1, Object paramObject2) {
    AbstractFormat abstractFormat = Scheme.displayFormat;
    try {
      Consumer consumer = (Consumer)paramObject2;
      abstractFormat.format(paramObject1, consumer);
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "gnu.lists.AbstractFormat.format(java.lang.Object,gnu.lists.Consumer)", 3, paramObject2);
    } 
  }
  
  public static void forceOutput() {
    forceOutput(current$Mnoutput$Mnport.apply0());
  }
  
  public static void forceOutput(Object paramObject) {
    try {
      Writer writer = (Writer)paramObject;
      writer.flush();
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "java.io.Writer.flush()", 1, paramObject);
    } 
  }
  
  public static FString getOutputString(CharArrayOutPort paramCharArrayOutPort) {
    return new FString(paramCharArrayOutPort.toCharArray());
  }
  
  public static int inputPortColumnNumber(Object paramObject) {
    return portColumn(paramObject) + 1;
  }
  
  public static Object inputPortLineNumber(LineBufferedReader paramLineBufferedReader) {
    return AddOp.$Pl.apply2(Lit6, port$Mnline.apply1(paramLineBufferedReader));
  }
  
  public static Procedure inputPortPrompter(TtyInPort paramTtyInPort) {
    return paramTtyInPort.getPrompter();
  }
  
  public static char inputPortReadState(Object paramObject) {
    try {
      InPort inPort = (InPort)paramObject;
      return inPort.getReadState();
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "gnu.mapping.InPort.getReadState()", 1, paramObject);
    } 
  }
  
  public static boolean isCharReady() {
    return isCharReady(current$Mninput$Mnport.apply0());
  }
  
  public static boolean isCharReady(Object paramObject) {
    return char_ready_p.ready(paramObject);
  }
  
  public static boolean isEofObject(Object paramObject) {
    return (paramObject == EofClass.eofValue);
  }
  
  public static boolean isInputPort(Object paramObject) {
    return paramObject instanceof InPort;
  }
  
  public static boolean isOutputPort(Object paramObject) {
    return paramObject instanceof OutPort;
  }
  
  static Object lambda1(Object paramObject) {
    try {
      return paramObject;
    } catch (ClassCastException classCastException) {
      paramObject = WrongType.make(classCastException, (Procedure)current$Mninput$Mnport, 1, paramObject);
      ((WrongType)paramObject).expectedType = Lit1;
      throw (Throwable)paramObject;
    } 
  }
  
  static Object lambda2(Object paramObject) {
    try {
      return paramObject;
    } catch (ClassCastException classCastException) {
      paramObject = WrongType.make(classCastException, (Procedure)current$Mnoutput$Mnport, 1, paramObject);
      ((WrongType)paramObject).expectedType = Lit3;
      throw (Throwable)paramObject;
    } 
  }
  
  static Object lambda3(Object paramObject) {
    try {
      return paramObject;
    } catch (ClassCastException classCastException) {
      paramObject = WrongType.make(classCastException, (Procedure)current$Mnerror$Mnport, 1, paramObject);
      ((WrongType)paramObject).expectedType = Lit3;
      throw (Throwable)paramObject;
    } 
  }
  
  public static void newline() {
    newline(current$Mnoutput$Mnport.apply0());
  }
  
  public static void newline(Object paramObject) {
    try {
      OutPort outPort = (OutPort)paramObject;
      outPort.println();
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "gnu.mapping.OutPort.println()", 1, paramObject);
    } 
  }
  
  public static InPort openInputFile(Path paramPath) {
    return InPort.openFile(paramPath);
  }
  
  public static InPort openInputString(CharSequence paramCharSequence) {
    if (paramCharSequence == null) {
      paramCharSequence = null;
      return (InPort)new CharArrayInPort((String)paramCharSequence);
    } 
    paramCharSequence = paramCharSequence.toString();
    return (InPort)new CharArrayInPort((String)paramCharSequence);
  }
  
  public static OutPort openOutputFile(Path paramPath) {
    return OutPort.openFile(paramPath);
  }
  
  public static CharArrayOutPort openOutputString() {
    return new CharArrayOutPort();
  }
  
  public static int portColumn(Object paramObject) {
    try {
      LineBufferedReader lineBufferedReader = (LineBufferedReader)paramObject;
      return lineBufferedReader.getColumnNumber();
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "gnu.text.LineBufferedReader.getColumnNumber()", 1, paramObject);
    } 
  }
  
  public static int portLine(LineBufferedReader paramLineBufferedReader) {
    return paramLineBufferedReader.getLineNumber();
  }
  
  public static Object read() {
    return read((InPort)current$Mninput$Mnport.apply0());
  }
  
  public static Object read(InPort paramInPort) {
    Object object;
    LispReader lispReader = new LispReader((LineBufferedReader)paramInPort);
    try {
      object = lispReader.readObject();
      if (lispReader.seenErrors())
        throw (Throwable)new SyntaxException(lispReader.getMessages()); 
    } catch (SyntaxException syntaxException) {
      syntaxException.setHeader("syntax error in read:");
      throw (Throwable)syntaxException;
    } 
    return object;
  }
  
  public static Object readLine() {
    return readLine((LineBufferedReader)current$Mninput$Mnport.apply0(), (Symbol)Lit9);
  }
  
  public static Object readLine(LineBufferedReader paramLineBufferedReader) {
    return readLine(paramLineBufferedReader, (Symbol)Lit9);
  }
  
  public static Object readLine(LineBufferedReader paramLineBufferedReader, Symbol paramSymbol) {
    if (paramSymbol == null) {
      paramSymbol = null;
      return read_line.apply(paramLineBufferedReader, (String)paramSymbol);
    } 
    String str = paramSymbol.toString();
    return read_line.apply(paramLineBufferedReader, str);
  }
  
  public static void setInputPortLineNumber$Ex(Object paramObject1, Object paramObject2) {
    setPortLine$Ex(paramObject1, AddOp.$Mn.apply2(paramObject2, Lit6));
  }
  
  public static void setInputPortPrompter$Ex(TtyInPort paramTtyInPort, Procedure paramProcedure) {
    paramTtyInPort.setPrompter(paramProcedure);
  }
  
  public static void setPortLine$Ex(Object paramObject1, Object paramObject2) {
    try {
      LineBufferedReader lineBufferedReader = (LineBufferedReader)paramObject1;
      try {
        int i = ((Number)paramObject2).intValue();
        lineBufferedReader.setLineNumber(i);
        return;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "gnu.text.LineBufferedReader.setLineNumber(int)", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "gnu.text.LineBufferedReader.setLineNumber(int)", 1, classCastException);
    } 
  }
  
  public static void transcriptOff() {
    OutPort.closeLogFile();
  }
  
  public static void transcriptOn(Object paramObject) {
    OutPort.setLogFile(paramObject.toString());
  }
  
  public static Object withInputFromFile(Path paramPath, Procedure paramProcedure) {
    InPort inPort1 = InPort.openFile(paramPath);
    InPort inPort2 = InPort.inDefault();
    try {
      InPort.setInDefault(inPort1);
      return paramProcedure.apply0();
    } finally {
      InPort.setInDefault(inPort2);
      inPort1.close();
    } 
  }
  
  public static Object withOutputToFile(Path paramPath, Procedure paramProcedure) {
    OutPort outPort1 = OutPort.openFile(paramPath);
    OutPort outPort2 = OutPort.outDefault();
    try {
      OutPort.setOutDefault(outPort1);
      return paramProcedure.apply0();
    } finally {
      OutPort.setOutDefault(outPort2);
      outPort1.close();
    } 
  }
  
  public static void write(Object paramObject) {
    write(paramObject, current$Mnoutput$Mnport.apply0());
  }
  
  public static void write(Object paramObject1, Object paramObject2) {
    AbstractFormat abstractFormat = Scheme.writeFormat;
    try {
      Consumer consumer = (Consumer)paramObject2;
      abstractFormat.format(paramObject1, consumer);
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "gnu.lists.AbstractFormat.format(java.lang.Object,gnu.lists.Consumer)", 3, paramObject2);
    } 
  }
  
  public static void writeChar(Object paramObject) {
    writeChar(paramObject, OutPort.outDefault());
  }
  
  public static void writeChar(Object paramObject, OutPort paramOutPort) {
    try {
      Char char_ = (Char)paramObject;
      Char.print(characters.char$To$Integer(char_), (Consumer)paramOutPort);
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "char->integer", 1, paramObject);
    } 
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply0(paramModuleMethod);
      case 15:
        return openOutputString();
      case 19:
        forceOutput();
        return Values.empty;
      case 21:
        newline();
        return Values.empty;
      case 24:
        return isCharReady() ? Boolean.TRUE : Boolean.FALSE;
      case 42:
        return read();
      case 44:
        return readLine();
      case 48:
        break;
    } 
    transcriptOff();
    return Values.empty;
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        try {
          Path path = Path.valueOf(paramObject);
          return openInputFile(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "open-input-file", 1, paramObject);
        } 
      case 2:
        try {
          Path path = Path.valueOf(paramObject);
          return openOutputFile(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "open-output-file", 1, paramObject);
        } 
      case 7:
        return isInputPort(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 8:
        return isOutputPort(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 9:
        return lambda1(paramObject);
      case 10:
        return lambda2(paramObject);
      case 11:
        return lambda3(paramObject);
      case 12:
        writeChar(paramObject);
        return Values.empty;
      case 14:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return openInputString(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "open-input-string", 1, paramObject);
        } 
      case 16:
        try {
          CharArrayOutPort charArrayOutPort = (CharArrayOutPort)paramObject;
          return getOutputString(charArrayOutPort);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "get-output-string", 1, paramObject);
        } 
      case 18:
        try {
          Procedure procedure = (Procedure)paramObject;
          return callWithOutputString(procedure);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "call-with-output-string", 1, paramObject);
        } 
      case 19:
        forceOutput(paramObject);
        return Values.empty;
      case 21:
        newline(paramObject);
        return Values.empty;
      case 23:
        return isEofObject(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 24:
        return isCharReady(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 26:
        write(paramObject);
        return Values.empty;
      case 28:
        display(paramObject);
        return Values.empty;
      case 30:
        return Char.make(inputPortReadState(paramObject));
      case 32:
        try {
          LineBufferedReader lineBufferedReader = (LineBufferedReader)paramObject;
          return Integer.valueOf(portLine(lineBufferedReader));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "port-line", 1, paramObject);
        } 
      case 34:
        try {
          LineBufferedReader lineBufferedReader = (LineBufferedReader)paramObject;
          return inputPortLineNumber(lineBufferedReader);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "input-port-line-number", 1, paramObject);
        } 
      case 35:
        return Integer.valueOf(portColumn(paramObject));
      case 36:
        return Integer.valueOf(inputPortColumnNumber(paramObject));
      case 37:
        return defaultPrompter(paramObject);
      case 39:
        try {
          TtyInPort ttyInPort = (TtyInPort)paramObject;
          return inputPortPrompter(ttyInPort);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "input-port-prompter", 1, paramObject);
        } 
      case 40:
        try {
          InPort inPort = (InPort)paramObject;
          return closeInputPort(inPort);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "close-input-port", 1, paramObject);
        } 
      case 41:
        try {
          OutPort outPort = (OutPort)paramObject;
          return closeOutputPort(outPort);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "close-output-port", 1, paramObject);
        } 
      case 42:
        try {
          InPort inPort = (InPort)paramObject;
          return read(inPort);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "read", 1, paramObject);
        } 
      case 44:
        try {
          LineBufferedReader lineBufferedReader = (LineBufferedReader)paramObject;
          return readLine(lineBufferedReader);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "read-line", 1, paramObject);
        } 
      case 47:
        break;
    } 
    transcriptOn(paramObject);
    return Values.empty;
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 3:
        try {
          Path path = Path.valueOf(paramObject1);
          try {
            paramObject1 = paramObject2;
            return callWithInputFile(path, (Procedure)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "call-with-input-file", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "call-with-input-file", 1, paramObject1);
        } 
      case 4:
        try {
          Path path = Path.valueOf(paramObject1);
          try {
            paramObject1 = paramObject2;
            return callWithOutputFile(path, (Procedure)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "call-with-output-file", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "call-with-output-file", 1, paramObject1);
        } 
      case 5:
        try {
          Path path = Path.valueOf(paramObject1);
          try {
            paramObject1 = paramObject2;
            return withInputFromFile(path, (Procedure)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "with-input-from-file", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "with-input-from-file", 1, paramObject1);
        } 
      case 6:
        try {
          Path path = Path.valueOf(paramObject1);
          try {
            paramObject1 = paramObject2;
            return withOutputToFile(path, (Procedure)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "with-output-to-file", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "with-output-to-file", 1, paramObject1);
        } 
      case 12:
        try {
          OutPort outPort = (OutPort)paramObject2;
          writeChar(paramObject1, outPort);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "write-char", 2, paramObject2);
        } 
      case 17:
        try {
          CharSequence charSequence = (CharSequence)paramObject1;
          try {
            paramObject1 = paramObject2;
            return callWithInputString(charSequence, (Procedure)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "call-with-input-string", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "call-with-input-string", 1, paramObject1);
        } 
      case 26:
        write(paramObject1, paramObject2);
        return Values.empty;
      case 28:
        display(paramObject1, paramObject2);
        return Values.empty;
      case 31:
        setPortLine$Ex(paramObject1, paramObject2);
        return Values.empty;
      case 33:
        setInputPortLineNumber$Ex(paramObject1, paramObject2);
        return Values.empty;
      case 38:
        try {
          TtyInPort ttyInPort = (TtyInPort)paramObject1;
          try {
            paramObject1 = paramObject2;
            setInputPortPrompter$Ex(ttyInPort, (Procedure)paramObject1);
            return Values.empty;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "set-input-port-prompter!", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-input-port-prompter!", 1, paramObject1);
        } 
      case 44:
        break;
    } 
    try {
      LineBufferedReader lineBufferedReader = (LineBufferedReader)paramObject1;
      try {
        paramObject1 = paramObject2;
        return readLine(lineBufferedReader, (Symbol)paramObject1);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "read-line", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "read-line", 1, paramObject1);
    } 
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match0(paramModuleMethod, paramCallContext);
      case 48:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 44:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 42:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 24:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 21:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 19:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 15:
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
      case 47:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 44:
        if (!(paramObject instanceof LineBufferedReader))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 42:
        if (!(paramObject instanceof InPort))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 41:
        if (!(paramObject instanceof OutPort))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 40:
        if (!(paramObject instanceof InPort))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 39:
        if (!(paramObject instanceof TtyInPort))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 37:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 36:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 35:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 34:
        if (!(paramObject instanceof LineBufferedReader))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 32:
        if (!(paramObject instanceof LineBufferedReader))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 30:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 28:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 26:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 24:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 23:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 21:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 19:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 18:
        if (!(paramObject instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 16:
        if (!(paramObject instanceof CharArrayOutPort))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 14:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 12:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 11:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 10:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 9:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 8:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 7:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 2:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 1:
        break;
    } 
    if (Path.coerceToPathOrNull(paramObject) != null) {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    } 
    return -786431;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 44:
        if (paramObject1 instanceof LineBufferedReader) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Symbol))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 38:
        if (paramObject1 instanceof TtyInPort) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Procedure))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 33:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 31:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 28:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 26:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 17:
        if (paramObject1 instanceof CharSequence) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Procedure))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 12:
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof OutPort))
          return -786430; 
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 6:
        if (Path.coerceToPathOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Procedure))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 5:
        if (Path.coerceToPathOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Procedure))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 4:
        if (Path.coerceToPathOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Procedure))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 3:
        break;
    } 
    if (Path.coerceToPathOrNull(paramObject1) != null) {
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure))
        return -786430; 
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return SYNTHETIC_LOCAL_VARIABLE_5;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    current$Mninput$Mnport = LocationProc.makeNamed((Symbol)Lit0, (Location)InPort.inLocation);
    current$Mninput$Mnport.pushConverter((Procedure)lambda$Fn1);
    current$Mnoutput$Mnport = LocationProc.makeNamed((Symbol)Lit2, (Location)OutPort.outLocation);
    current$Mnoutput$Mnport.pushConverter((Procedure)lambda$Fn2);
    current$Mnerror$Mnport = LocationProc.makeNamed((Symbol)Lit4, (Location)OutPort.errLocation);
    current$Mnerror$Mnport.pushConverter((Procedure)lambda$Fn3);
    port$Mnline = new GenericProc("port-line");
    GenericProc genericProc = port$Mnline;
    Keyword keyword = Lit5;
    ModuleMethod moduleMethod1 = set$Mnport$Mnline$Ex;
    ModuleMethod moduleMethod2 = port$Mnline$Fn4;
    genericProc.setProperties(new Object[] { keyword, moduleMethod1, port$Mnline$Fn4 });
    input$Mnport$Mnline$Mnnumber = new GenericProc("input-port-line-number");
    genericProc = input$Mnport$Mnline$Mnnumber;
    keyword = Lit5;
    moduleMethod1 = set$Mninput$Mnport$Mnline$Mnnumber$Ex;
    moduleMethod2 = input$Mnport$Mnline$Mnnumber$Fn5;
    genericProc.setProperties(new Object[] { keyword, moduleMethod1, input$Mnport$Mnline$Mnnumber$Fn5 });
    input$Mnport$Mnprompter = new GenericProc("input-port-prompter");
    genericProc = input$Mnport$Mnprompter;
    keyword = Lit5;
    moduleMethod1 = set$Mninput$Mnport$Mnprompter$Ex;
    moduleMethod2 = input$Mnport$Mnprompter$Fn6;
    genericProc.setProperties(new Object[] { keyword, moduleMethod1, input$Mnport$Mnprompter$Fn6 });
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/ports.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
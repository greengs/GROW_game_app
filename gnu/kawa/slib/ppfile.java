package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.text.Char;
import gnu.text.Path;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.standard.Scheme;
import kawa.standard.readchar;

public class ppfile extends ModuleBody {
  public static final ppfile $instance;
  
  static final Char Lit0;
  
  static final Char Lit1;
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit3 = (SimpleSymbol)(new SimpleSymbol("pprint-file")).readResolve();
  
  static final ModuleMethod lambda$Fn3;
  
  public static final ModuleMethod pprint$Mnfile;
  
  public static final ModuleMethod pprint$Mnfilter$Mnfile;
  
  static {
    Lit2 = (SimpleSymbol)(new SimpleSymbol("pprint-filter-file")).readResolve();
    Lit1 = Char.make(10);
    Lit0 = Char.make(59);
    $instance = new ppfile();
    ppfile ppfile1 = $instance;
    pprint$Mnfilter$Mnfile = new ModuleMethod(ppfile1, 3, Lit2, -4094);
    ModuleMethod moduleMethod = new ModuleMethod(ppfile1, 4, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/ppfile.scm:70");
    lambda$Fn3 = moduleMethod;
    pprint$Mnfile = new ModuleMethod(ppfile1, 5, Lit3, 8193);
    $instance.run();
  }
  
  public ppfile() {
    ModuleInfo.register(this);
  }
  
  static Object lambda3(Object paramObject) {
    return paramObject;
  }
  
  public static Object pprintFile(Object paramObject) {
    return pprintFile(paramObject, ports.current$Mnoutput$Mnport.apply0());
  }
  
  public static Object pprintFile(Object paramObject1, Object paramObject2) {
    return pprintFilterFile$V(paramObject1, lambda$Fn3, new Object[] { paramObject2 });
  }
  
  public static Object pprintFilterFile$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    frame frame = new frame();
    frame.filter = paramObject2;
    frame.optarg = LList.makeList(paramArrayOfObject, 0);
    paramObject2 = frame.lambda$Fn1;
    if (ports.isInputPort(paramObject1))
      return frame.lambda1(paramObject1); 
    try {
      Path path = Path.valueOf(paramObject1);
      return ports.callWithInputFile(path, (Procedure)paramObject2);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "call-with-input-file", 1, paramObject1);
    } 
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 4:
        return lambda3(paramObject);
      case 5:
        break;
    } 
    return pprintFile(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    return (paramModuleMethod.selector == 5) ? pprintFile(paramObject1, paramObject2) : super.apply2(paramModuleMethod, paramObject1, paramObject2);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    Object object;
    if (paramModuleMethod.selector == 3) {
      object = paramArrayOfObject[0];
      Object object1 = paramArrayOfObject[1];
      int i = paramArrayOfObject.length - 2;
      Object[] arrayOfObject = new Object[i];
      while (true) {
        if (--i < 0)
          return pprintFilterFile$V(object, object1, arrayOfObject); 
        arrayOfObject[i] = paramArrayOfObject[i + 2];
      } 
    } 
    return super.applyN((ModuleMethod)object, paramArrayOfObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 5:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 4:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 5) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 3) {
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    } 
    return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
  
  public class frame extends ModuleBody {
    Object filter;
    
    final ModuleMethod lambda$Fn1;
    
    LList optarg;
    
    public frame() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/ppfile.scm:27");
      this.lambda$Fn1 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 2) ? lambda1(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda1(Object param1Object) {
      frame0 frame0 = new frame0();
      frame0.staticLink = this;
      frame0.port = param1Object;
      ModuleMethod moduleMethod = frame0.lambda$Fn2;
      if (lists.isNull(this.optarg)) {
        param1Object = ports.current$Mnoutput$Mnport.apply0();
      } else {
        param1Object = lists.car.apply1(this.optarg);
      } 
      if (ports.isOutputPort(param1Object))
        return frame0.lambda2(param1Object); 
      try {
        Path path = Path.valueOf(param1Object);
        return ports.callWithOutputFile(path, (Procedure)moduleMethod);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "call-with-output-file", 1, param1Object);
      } 
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 2) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public class frame0 extends ModuleBody {
      final ModuleMethod lambda$Fn2;
      
      Object port;
      
      ppfile.frame staticLink;
      
      public frame0() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/ppfile.scm:34");
        this.lambda$Fn2 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 1) ? lambda2(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      Object lambda2(Object param2Object) {
        Object object = readchar.peekChar.apply1(this.port);
        label46: while (true) {
          boolean bool = ports.isEofObject(object);
          if (bool)
            return bool ? Boolean.TRUE : Boolean.FALSE; 
          try {
            Char char_ = (Char)object;
            if (unicode.isCharWhitespace(char_)) {
              ports.display(readchar.readChar.apply1(this.port), param2Object);
              object = readchar.peekChar.apply1(this.port);
              continue;
            } 
            char_ = ppfile.Lit0;
            try {
              Char char_1 = (Char)object;
              if (characters.isChar$Eq(char_, char_1)) {
                while (true) {
                  bool = ports.isEofObject(object);
                  if (bool)
                    return bool ? Boolean.TRUE : Boolean.FALSE; 
                  char_ = ppfile.Lit1;
                  try {
                    char_1 = (Char)object;
                    if (characters.isChar$Eq(char_, char_1)) {
                      ports.display(readchar.readChar.apply1(this.port), param2Object);
                      object = readchar.peekChar.apply1(this.port);
                      continue label46;
                    } 
                    ports.display(readchar.readChar.apply1(this.port), param2Object);
                    object = readchar.peekChar.apply1(this.port);
                  } catch (ClassCastException classCastException) {
                    throw new WrongType(classCastException, "char=?", 2, object);
                  } 
                } 
                break;
              } 
              object = this.port;
              try {
                InPort inPort = (InPort)object;
                object = ports.read(inPort);
                bool = ports.isEofObject(object);
                if (bool)
                  return bool ? Boolean.TRUE : Boolean.FALSE; 
                pp.prettyPrint(Scheme.applyToArgs.apply2(this.staticLink.filter, object), classCastException);
                Object object1 = readchar.peekChar.apply1(this.port);
                object = object1;
                if (Scheme.isEqv.apply2(ppfile.Lit1, object1) != Boolean.FALSE) {
                  readchar.readChar.apply1(this.port);
                  object = readchar.peekChar.apply1(this.port);
                } 
              } catch (ClassCastException classCastException1) {
                throw new WrongType(classCastException1, "read", 1, object);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "char=?", 2, object);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char-whitespace?", 1, object);
          } 
        } 
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 1) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
    }
  }
  
  public class frame0 extends ModuleBody {
    final ModuleMethod lambda$Fn2;
    
    Object port;
    
    ppfile.frame staticLink;
    
    public frame0() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/ppfile.scm:34");
      this.lambda$Fn2 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 1) ? lambda2(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda2(Object param1Object) {
      Object object = readchar.peekChar.apply1(this.port);
      label46: while (true) {
        boolean bool = ports.isEofObject(object);
        if (bool)
          return bool ? Boolean.TRUE : Boolean.FALSE; 
        try {
          Char char_ = (Char)object;
          if (unicode.isCharWhitespace(char_)) {
            ports.display(readchar.readChar.apply1(this.port), param1Object);
            object = readchar.peekChar.apply1(this.port);
            continue;
          } 
          char_ = ppfile.Lit0;
          try {
            Char char_1 = (Char)object;
            if (characters.isChar$Eq(char_, char_1)) {
              while (true) {
                bool = ports.isEofObject(object);
                if (bool)
                  return bool ? Boolean.TRUE : Boolean.FALSE; 
                char_ = ppfile.Lit1;
                try {
                  char_1 = (Char)object;
                  if (characters.isChar$Eq(char_, char_1)) {
                    ports.display(readchar.readChar.apply1(this.port), param1Object);
                    object = readchar.peekChar.apply1(this.port);
                    continue label46;
                  } 
                  ports.display(readchar.readChar.apply1(this.port), param1Object);
                  object = readchar.peekChar.apply1(this.port);
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "char=?", 2, object);
                } 
              } 
              break;
            } 
            object = this.port;
            try {
              InPort inPort = (InPort)object;
              object = ports.read(inPort);
              bool = ports.isEofObject(object);
              if (bool)
                return bool ? Boolean.TRUE : Boolean.FALSE; 
              pp.prettyPrint(Scheme.applyToArgs.apply2(this.staticLink.filter, object), classCastException);
              Object object1 = readchar.peekChar.apply1(this.port);
              object = object1;
              if (Scheme.isEqv.apply2(ppfile.Lit1, object1) != Boolean.FALSE) {
                readchar.readChar.apply1(this.port);
                object = readchar.peekChar.apply1(this.port);
              } 
            } catch (ClassCastException classCastException1) {
              throw new WrongType(classCastException1, "read", 1, object);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "char=?", 2, object);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "char-whitespace?", 1, object);
        } 
      } 
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 1) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/ppfile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
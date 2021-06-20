package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.FileUtils;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.IsEqual;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.FilePath;
import gnu.text.Path;
import java.io.File;
import java.io.IOException;
import kawa.standard.readchar;

public class files extends ModuleBody {
  public static final ModuleMethod $Mn$Grpathname;
  
  public static final ModuleMethod $Pcfile$Mnseparator;
  
  public static final files $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
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
  
  static final SimpleSymbol Lit29 = (SimpleSymbol)(new SimpleSymbol("make-temporary-file")).readResolve();
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod URI$Qu;
  
  public static final ModuleMethod absolute$Mnpath$Qu;
  
  public static final ModuleMethod copy$Mnfile;
  
  public static final ModuleMethod create$Mndirectory;
  
  public static final ModuleMethod delete$Mnfile;
  
  public static final ModuleMethod directory$Mnfiles;
  
  public static final ModuleMethod file$Mndirectory$Qu;
  
  public static final ModuleMethod file$Mnexists$Qu;
  
  public static final ModuleMethod file$Mnreadable$Qu;
  
  public static final ModuleMethod file$Mnwritable$Qu;
  
  public static final ModuleMethod filepath$Qu;
  
  public static final ModuleMethod make$Mntemporary$Mnfile;
  
  public static final ModuleMethod path$Mnauthority;
  
  public static final ModuleMethod path$Mndirectory;
  
  public static final ModuleMethod path$Mnextension;
  
  public static final ModuleMethod path$Mnfile;
  
  public static final ModuleMethod path$Mnfragment;
  
  public static final ModuleMethod path$Mnhost;
  
  public static final ModuleMethod path$Mnlast;
  
  public static final ModuleMethod path$Mnparent;
  
  public static final ModuleMethod path$Mnport;
  
  public static final ModuleMethod path$Mnquery;
  
  public static final ModuleMethod path$Mnscheme;
  
  public static final ModuleMethod path$Mnuser$Mninfo;
  
  public static final ModuleMethod path$Qu;
  
  public static final ModuleMethod rename$Mnfile;
  
  public static final ModuleMethod resolve$Mnuri;
  
  public static final ModuleMethod system$Mntmpdir;
  
  public static String $PcFileSeparator() {
    return System.getProperty("file.separator");
  }
  
  public static Path $To$Pathname(Object paramObject) {
    return Path.valueOf(paramObject);
  }
  
  static {
    Lit28 = (SimpleSymbol)(new SimpleSymbol("resolve-uri")).readResolve();
    Lit27 = (SimpleSymbol)(new SimpleSymbol("system-tmpdir")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("%file-separator")).readResolve();
    Lit25 = (SimpleSymbol)(new SimpleSymbol("->pathname")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("directory-files")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("create-directory")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("copy-file")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("rename-file")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("delete-file")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("file-writable?")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("file-readable?")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("file-directory?")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("file-exists?")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("path-fragment")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("path-query")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("path-port")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("path-extension")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("path-last")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("path-parent")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("path-directory")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("path-file")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("path-host")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("path-user-info")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("path-authority")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("path-scheme")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("absolute-path?")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("URI?")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("filepath?")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("path?")).readResolve();
    $instance = new files();
    files files1 = $instance;
    path$Qu = new ModuleMethod(files1, 1, Lit0, 4097);
    filepath$Qu = new ModuleMethod(files1, 2, Lit1, 4097);
    URI$Qu = new ModuleMethod(files1, 3, Lit2, 4097);
    absolute$Mnpath$Qu = new ModuleMethod(files1, 4, Lit3, 4097);
    path$Mnscheme = new ModuleMethod(files1, 5, Lit4, 4097);
    path$Mnauthority = new ModuleMethod(files1, 6, Lit5, 4097);
    path$Mnuser$Mninfo = new ModuleMethod(files1, 7, Lit6, 4097);
    path$Mnhost = new ModuleMethod(files1, 8, Lit7, 4097);
    path$Mnfile = new ModuleMethod(files1, 9, Lit8, 4097);
    path$Mndirectory = new ModuleMethod(files1, 10, Lit9, 4097);
    path$Mnparent = new ModuleMethod(files1, 11, Lit10, 4097);
    path$Mnlast = new ModuleMethod(files1, 12, Lit11, 4097);
    path$Mnextension = new ModuleMethod(files1, 13, Lit12, 4097);
    path$Mnport = new ModuleMethod(files1, 14, Lit13, 4097);
    path$Mnquery = new ModuleMethod(files1, 15, Lit14, 4097);
    path$Mnfragment = new ModuleMethod(files1, 16, Lit15, 4097);
    file$Mnexists$Qu = new ModuleMethod(files1, 17, Lit16, 4097);
    file$Mndirectory$Qu = new ModuleMethod(files1, 18, Lit17, 4097);
    file$Mnreadable$Qu = new ModuleMethod(files1, 19, Lit18, 4097);
    file$Mnwritable$Qu = new ModuleMethod(files1, 20, Lit19, 4097);
    delete$Mnfile = new ModuleMethod(files1, 21, Lit20, 4097);
    rename$Mnfile = new ModuleMethod(files1, 22, Lit21, 8194);
    copy$Mnfile = new ModuleMethod(files1, 23, Lit22, 8194);
    create$Mndirectory = new ModuleMethod(files1, 24, Lit23, 4097);
    directory$Mnfiles = new ModuleMethod(files1, 25, Lit24, 4097);
    $Mn$Grpathname = new ModuleMethod(files1, 26, Lit25, 4097);
    $Pcfile$Mnseparator = new ModuleMethod(files1, 27, Lit26, 0);
    system$Mntmpdir = new ModuleMethod(files1, 28, Lit27, 0);
    resolve$Mnuri = new ModuleMethod(files1, 29, Lit28, 8194);
    make$Mntemporary$Mnfile = new ModuleMethod(files1, 30, Lit29, 4096);
    $instance.run();
  }
  
  public files() {
    ModuleInfo.register(this);
  }
  
  public static boolean URI$Qu(Object paramObject) {
    return paramObject instanceof gnu.text.URIPath;
  }
  
  public static void copyFile(Path paramPath1, Path paramPath2) {
    InPort inPort = ports.openInputFile(paramPath1);
    OutPort outPort = ports.openOutputFile(paramPath2);
    for (Object object = readchar.readChar.apply1(inPort); !ports.isEofObject(object); object = readchar.readChar.apply1(inPort))
      ports.writeChar(object, outPort); 
    ports.closeOutputPort(outPort);
    ports.closeInputPort(inPort);
  }
  
  public static boolean createDirectory(FilePath paramFilePath) {
    return paramFilePath.toFile().mkdir();
  }
  
  public static void deleteFile(FilePath paramFilePath) {
    if (!paramFilePath.delete())
      throw (Throwable)new IOException(Format.formatToString(0, new Object[] { "cannot delete ~a", paramFilePath }).toString()); 
  }
  
  public static Object directoryFiles(FilePath paramFilePath) {
    String str;
    File file = paramFilePath.toFile();
    if (file == null) {
      file = null;
    } else {
      str = file.toString();
    } 
    String[] arrayOfString = (new File(str)).list();
    return (arrayOfString == null) ? Boolean.FALSE : LList.makeList((Object[])arrayOfString, 0);
  }
  
  public static boolean isAbsolutePath(Path paramPath) {
    return paramPath.isAbsolute();
  }
  
  public static boolean isFileDirectory(Path paramPath) {
    return paramPath.isDirectory();
  }
  
  public static boolean isFileExists(Path paramPath) {
    return paramPath.exists();
  }
  
  public static boolean isFileReadable(FilePath paramFilePath) {
    return paramFilePath.toFile().canRead();
  }
  
  public static boolean isFileWritable(FilePath paramFilePath) {
    return paramFilePath.toFile().canWrite();
  }
  
  public static boolean isFilepath(Object paramObject) {
    return paramObject instanceof FilePath;
  }
  
  public static boolean isPath(Object paramObject) {
    return paramObject instanceof Path;
  }
  
  public static FilePath makeTemporaryFile() {
    return makeTemporaryFile("kawa~d.tmp");
  }
  
  public static FilePath makeTemporaryFile(CharSequence paramCharSequence) {
    return FilePath.makeFilePath(FileUtils.createTempFile(paramCharSequence.toString()));
  }
  
  public static Object pathAuthority(Path paramPath) {
    Boolean bool;
    String str2 = paramPath.getAuthority();
    String str1 = str2;
    if (str2 == null)
      bool = Boolean.FALSE; 
    return bool;
  }
  
  public static Object pathDirectory(Path paramPath) {
    paramPath = paramPath.getDirectory();
    return (paramPath == null) ? Boolean.FALSE : paramPath.toString();
  }
  
  public static Object pathExtension(Path paramPath) {
    Boolean bool;
    String str2 = paramPath.getExtension();
    String str1 = str2;
    if (str2 == null)
      bool = Boolean.FALSE; 
    return bool;
  }
  
  public static Object pathFile(Path paramPath) {
    Boolean bool;
    String str2 = paramPath.getPath();
    String str1 = str2;
    if (str2 == null)
      bool = Boolean.FALSE; 
    return bool;
  }
  
  public static Object pathFragment(Path paramPath) {
    Boolean bool;
    String str2 = paramPath.getFragment();
    String str1 = str2;
    if (str2 == null)
      bool = Boolean.FALSE; 
    return bool;
  }
  
  public static String pathHost(Path paramPath) {
    return paramPath.getHost();
  }
  
  public static Object pathLast(Path paramPath) {
    Boolean bool;
    String str2 = paramPath.getLast();
    String str1 = str2;
    if (str2 == null)
      bool = Boolean.FALSE; 
    return bool;
  }
  
  public static Object pathParent(Path paramPath) {
    paramPath = paramPath.getParent();
    return (paramPath == null) ? Boolean.FALSE : paramPath.toString();
  }
  
  public static int pathPort(Path paramPath) {
    return paramPath.getPort();
  }
  
  public static Object pathQuery(Path paramPath) {
    Boolean bool;
    String str2 = paramPath.getQuery();
    String str1 = str2;
    if (str2 == null)
      bool = Boolean.FALSE; 
    return bool;
  }
  
  public static Object pathScheme(Path paramPath) {
    Boolean bool;
    String str2 = paramPath.getScheme();
    String str1 = str2;
    if (str2 == null)
      bool = Boolean.FALSE; 
    return bool;
  }
  
  public static Object pathUserInfo(Path paramPath) {
    Boolean bool;
    String str2 = paramPath.getUserInfo();
    String str1 = str2;
    if (str2 == null)
      bool = Boolean.FALSE; 
    return bool;
  }
  
  public static boolean renameFile(FilePath paramFilePath1, FilePath paramFilePath2) {
    return paramFilePath1.toFile().renameTo(paramFilePath2.toFile());
  }
  
  public static Path resolveUri(Path paramPath1, Path paramPath2) {
    return paramPath2.resolve(paramPath1);
  }
  
  public static String systemTmpdir() {
    String str = System.getProperty("java.io.tmpdir");
    return (str != null) ? str : (IsEqual.apply($PcFileSeparator(), "\\") ? "C:\\temp" : "/tmp");
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply0(paramModuleMethod);
      case 27:
        return $PcFileSeparator();
      case 28:
        return systemTmpdir();
      case 30:
        break;
    } 
    return makeTemporaryFile();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return isPath(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 2:
        return isFilepath(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 3:
        return URI$Qu(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 4:
        try {
          Path path = Path.valueOf(paramObject);
          return isAbsolutePath(path) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "absolute-path?", 1, paramObject);
        } 
      case 5:
        try {
          Path path = Path.valueOf(paramObject);
          return pathScheme(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "path-scheme", 1, paramObject);
        } 
      case 6:
        try {
          Path path = Path.valueOf(paramObject);
          return pathAuthority(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "path-authority", 1, paramObject);
        } 
      case 7:
        try {
          Path path = Path.valueOf(paramObject);
          return pathUserInfo(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "path-user-info", 1, paramObject);
        } 
      case 8:
        try {
          Path path = Path.valueOf(paramObject);
          return pathHost(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "path-host", 1, paramObject);
        } 
      case 9:
        try {
          Path path = Path.valueOf(paramObject);
          return pathFile(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "path-file", 1, paramObject);
        } 
      case 10:
        try {
          Path path = Path.valueOf(paramObject);
          return pathDirectory(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "path-directory", 1, paramObject);
        } 
      case 11:
        try {
          Path path = Path.valueOf(paramObject);
          return pathParent(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "path-parent", 1, paramObject);
        } 
      case 12:
        try {
          Path path = Path.valueOf(paramObject);
          return pathLast(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "path-last", 1, paramObject);
        } 
      case 13:
        try {
          Path path = Path.valueOf(paramObject);
          return pathExtension(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "path-extension", 1, paramObject);
        } 
      case 14:
        try {
          Path path = Path.valueOf(paramObject);
          return Integer.valueOf(pathPort(path));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "path-port", 1, paramObject);
        } 
      case 15:
        try {
          Path path = Path.valueOf(paramObject);
          return pathQuery(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "path-query", 1, paramObject);
        } 
      case 16:
        try {
          Path path = Path.valueOf(paramObject);
          return pathFragment(path);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "path-fragment", 1, paramObject);
        } 
      case 17:
        try {
          Path path = Path.valueOf(paramObject);
          return isFileExists(path) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "file-exists?", 1, paramObject);
        } 
      case 18:
        try {
          Path path = Path.valueOf(paramObject);
          return isFileDirectory(path) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "file-directory?", 1, paramObject);
        } 
      case 19:
        try {
          FilePath filePath = FilePath.makeFilePath(paramObject);
          return isFileReadable(filePath) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "file-readable?", 1, paramObject);
        } 
      case 20:
        try {
          FilePath filePath = FilePath.makeFilePath(paramObject);
          return isFileWritable(filePath) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "file-writable?", 1, paramObject);
        } 
      case 21:
        try {
          FilePath filePath = FilePath.makeFilePath(paramObject);
          deleteFile(filePath);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "delete-file", 1, paramObject);
        } 
      case 24:
        try {
          FilePath filePath = FilePath.makeFilePath(paramObject);
          return createDirectory(filePath) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "create-directory", 1, paramObject);
        } 
      case 25:
        try {
          FilePath filePath = FilePath.makeFilePath(paramObject);
          return directoryFiles(filePath);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "directory-files", 1, paramObject);
        } 
      case 26:
        return $To$Pathname(paramObject);
      case 30:
        break;
    } 
    try {
      CharSequence charSequence = (CharSequence)paramObject;
      return makeTemporaryFile(charSequence);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "make-temporary-file", 1, paramObject);
    } 
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 22:
        try {
          FilePath filePath = FilePath.makeFilePath(paramObject1);
          try {
            paramObject1 = FilePath.makeFilePath(paramObject2);
            return renameFile(filePath, (FilePath)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "rename-file", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "rename-file", 1, paramObject1);
        } 
      case 23:
        try {
          Path path = Path.valueOf(paramObject1);
          try {
            paramObject1 = Path.valueOf(paramObject2);
            copyFile(path, (Path)paramObject1);
            return Values.empty;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "copy-file", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "copy-file", 1, paramObject1);
        } 
      case 29:
        break;
    } 
    try {
      Path path = Path.valueOf(paramObject1);
      try {
        paramObject1 = Path.valueOf(paramObject2);
        return resolveUri(path, (Path)paramObject1);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "resolve-uri", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "resolve-uri", 1, paramObject1);
    } 
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match0(paramModuleMethod, paramCallContext);
      case 30:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 28:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 27:
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
      case 30:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 26:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 25:
        if (FilePath.coerceToFilePathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 24:
        if (FilePath.coerceToFilePathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 21:
        if (FilePath.coerceToFilePathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 20:
        if (FilePath.coerceToFilePathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 19:
        if (FilePath.coerceToFilePathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 18:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 17:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 16:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 15:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 14:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 13:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 12:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 11:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 10:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 9:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 8:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 7:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 6:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 5:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 4:
        if (Path.coerceToPathOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 3:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 2:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 1:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 29:
        if (Path.coerceToPathOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          if (Path.coerceToPathOrNull(paramObject2) != null) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 23:
        if (Path.coerceToPathOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          if (Path.coerceToPathOrNull(paramObject2) != null) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 22:
        break;
    } 
    if (FilePath.coerceToFilePathOrNull(paramObject1) != null) {
      paramCallContext.value1 = paramObject1;
      if (FilePath.coerceToFilePathOrNull(paramObject2) != null) {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      } 
    } else {
      return -786431;
    } 
    return -786430;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/files.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package kawa.standard;

import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.Symbol;
import gnu.text.SyntaxException;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import kawa.lang.AutoloadProcedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_autoload extends Syntax {
  public static final define_autoload define_autoload = new define_autoload("define-autoload", false);
  
  public static final define_autoload define_autoloads_from_file = new define_autoload("define-autoloads-from-file", true);
  
  boolean fromFile;
  
  public define_autoload(String paramString, boolean paramBoolean) {
    super(paramString);
    this.fromFile = paramBoolean;
  }
  
  public static void findAutoloadComments(LispReader paramLispReader, String paramString, ScopeExp paramScopeExp, Translator paramTranslator) throws IOException, SyntaxException {
    int i = 1;
    int j = ";;;###autoload".length();
    label61: while (true) {
      int k = paramLispReader.peek();
      if (k < 0)
        continue; 
      if (k == 10 || k == 13) {
        paramLispReader.read();
        i = 1;
        continue;
      } 
      int m = k;
      if (i) {
        m = k;
        if (k == 59) {
          int n = 0;
          i = k;
          while (true) {
            if (n == j) {
              m = i;
              if (n > 0) {
                Object object = paramLispReader.readObject();
                if (object instanceof Pair) {
                  Pair pair = (Pair)object;
                  Object object1 = null;
                  String str = null;
                  object = pair.getCar();
                  if (object instanceof String) {
                    object = object.toString();
                  } else if (object instanceof Symbol) {
                    object = ((Symbol)object).getName();
                  } else {
                    object = null;
                  } 
                  if (object == "defun") {
                    str = ((Pair)pair.getCdr()).getCar().toString();
                    object = new AutoloadProcedure(str, paramString, paramTranslator.getLanguage());
                  } else {
                    paramTranslator.error('w', "unsupported ;;;###autoload followed by: " + pair.getCar());
                    object = object1;
                  } 
                  if (object != null) {
                    Declaration declaration = paramScopeExp.getDefine(str, 'w', (Compilation)paramTranslator);
                    object = new QuoteExp(object);
                    declaration.setFlag(16384L);
                    declaration.noteValue((Expression)object);
                    declaration.setProcedureDecl(true);
                    declaration.setType((Type)Compilation.typeProcedure);
                  } 
                } 
                i = 0;
                continue label61;
              } 
              break;
            } 
            k = paramLispReader.read();
            if (k >= 0) {
              if (k == 10 || k == 13) {
                i = 1;
                continue label61;
              } 
              i = k;
              if (n >= 0) {
                if (k == ";;;###autoload".charAt(n)) {
                  n++;
                  i = k;
                  continue;
                } 
                n = -1;
                i = k;
              } 
              continue;
            } 
            return;
          } 
        } 
      } 
      boolean bool = false;
      paramLispReader.skip();
      if (m == 35 && paramLispReader.peek() == 124) {
        paramLispReader.skip();
        paramLispReader.readNestedComment('#', '|');
        i = bool;
        continue;
      } 
      i = bool;
      if (!Character.isWhitespace((char)m)) {
        i = bool;
        if (paramLispReader.readObject(m) == Sequence.eofValue)
          break; 
      } 
    } 
  }
  
  public static boolean process(Object paramObject1, Object paramObject2, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    if (paramObject1 instanceof Pair) {
      paramObject1 = paramObject1;
      return !(!process(paramObject1.getCar(), paramObject2, paramVector, paramScopeExp, paramTranslator) || !process(paramObject1.getCdr(), paramObject2, paramVector, paramScopeExp, paramTranslator));
    } 
    if (paramObject1 != LList.Empty) {
      if (paramObject1 instanceof String || paramObject1 instanceof Symbol) {
        String str = paramObject1.toString();
        Declaration declaration = paramScopeExp.getDefine(str, 'w', (Compilation)paramTranslator);
        paramObject1 = paramObject2;
        if (paramObject2 instanceof String) {
          String str1 = (String)paramObject2;
          int i = str1.length();
          paramObject1 = paramObject2;
          if (i > 2) {
            paramObject1 = paramObject2;
            if (str1.charAt(0) == '<') {
              paramObject1 = paramObject2;
              if (str1.charAt(i - 1) == '>')
                paramObject1 = str1.substring(1, i - 1); 
            } 
          } 
        } 
        paramObject1 = new QuoteExp(new AutoloadProcedure(str, paramObject1.toString(), paramTranslator.getLanguage()));
        declaration.setFlag(16384L);
        declaration.noteValue((Expression)paramObject1);
        return true;
      } 
      return false;
    } 
    return true;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    return null;
  }
  
  public boolean scanFile(String paramString, ScopeExp paramScopeExp, Translator paramTranslator) {
    if (paramString.endsWith(".el"));
    File file2 = new File(paramString);
    File file1 = file2;
    if (!file2.isAbsolute())
      file1 = new File((new File(paramTranslator.getFileName())).getParent(), paramString); 
    String str = file1.getPath();
    int i = str.lastIndexOf('.');
    if (i >= 0) {
      String str1 = str.substring(i);
      Language language = Language.getInstance(str1);
      if (language == null) {
        paramTranslator.syntaxError("unknown extension for " + str);
        return true;
      } 
      String str2 = paramTranslator.classPrefix;
      i = str1.length();
      for (str1 = paramString.substring(0, paramString.length() - i); str1.startsWith("../"); str1 = str1.substring(3)) {
        i = str2.lastIndexOf('.', str2.length() - 2);
        if (i < 0) {
          paramTranslator.syntaxError("cannot use relative filename \"" + paramString + "\" with simple prefix \"" + str2 + "\"");
          return false;
        } 
        str2 = str2.substring(0, i + 1);
      } 
      paramString = (str2 + str1).replace('/', '.');
      try {
        findAutoloadComments((LispReader)language.getLexer(InPort.openFile(str), paramTranslator.getMessages()), paramString, paramScopeExp, paramTranslator);
        return true;
      } catch (Exception exception) {
        paramTranslator.syntaxError("error reading " + str + ": " + exception);
        return true;
      } 
    } 
    return true;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    Object object1;
    boolean bool = false;
    if (!(paramPair.getCdr() instanceof Pair))
      return super.scanForDefinitions(paramPair, paramVector, paramScopeExp, paramTranslator); 
    paramPair = (Pair)paramPair.getCdr();
    if (this.fromFile)
      while (true) {
        if (!(paramPair.getCar() instanceof CharSequence)) {
          paramTranslator.syntaxError("invalid syntax for define-autoloads-from-file");
          return false;
        } 
        boolean bool1 = bool;
        if (scanFile(paramPair.getCar().toString(), paramScopeExp, paramTranslator)) {
          object1 = paramPair.getCdr();
          if (object1 == LList.Empty)
            return true; 
          if (object1 instanceof Pair) {
            paramPair = (Pair)paramPair.getCdr();
            continue;
          } 
          paramTranslator.syntaxError("invalid syntax for define-autoloads-from-file");
          return false;
        } 
        return bool1;
      }  
    Object object2 = paramPair.getCar();
    if (paramPair.getCdr() instanceof Pair)
      return process(object2, ((Pair)paramPair.getCdr()).getCar(), (Vector)object1, paramScopeExp, paramTranslator); 
    paramTranslator.syntaxError("invalid syntax for define-autoload");
    return false;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/define_autoload.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
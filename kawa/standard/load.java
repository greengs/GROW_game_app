package kawa.standard;

import gnu.mapping.Environment;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.text.SyntaxException;
import java.io.FileNotFoundException;
import kawa.Shell;

public class load extends Procedure1 {
  public static final load load = new load("load", false);
  
  public static final load loadRelative = new load("load-relative", true);
  
  boolean relative;
  
  public load(String paramString, boolean paramBoolean) {
    super(paramString);
    this.relative = paramBoolean;
  }
  
  public final Object apply1(Object paramObject) throws Throwable {
    return apply2(paramObject, Environment.getCurrent());
  }
  
  public final Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    try {
      Environment environment = (Environment)paramObject2;
      Path path = Path.valueOf(paramObject1);
      paramObject2 = path;
      if (this.relative) {
        Path path1 = Shell.currentLoadPath.get();
        paramObject2 = path;
        if (path1 != null)
          paramObject2 = path1.resolve(path); 
      } 
      Shell.runFile(paramObject2.openInputStream(), (Path)paramObject2, environment, true, 0);
      return Values.empty;
    } catch (FileNotFoundException fileNotFoundException) {
      throw new RuntimeException("cannot load " + fileNotFoundException.getMessage());
    } catch (SyntaxException syntaxException) {
      throw new RuntimeException("load: errors while compiling '" + fileNotFoundException + "':\n" + syntaxException.getMessages().toString(20));
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/load.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
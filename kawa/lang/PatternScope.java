package kawa.lang;

import gnu.expr.Declaration;
import gnu.expr.LetExp;
import java.util.Vector;

public class PatternScope extends LetExp {
  public Declaration matchArray;
  
  public StringBuffer patternNesting;
  
  public Vector pattern_names;
  
  PatternScope previousSyntax;
  
  public PatternScope() {
    super(null);
  }
  
  public static void pop(Translator paramTranslator) {
    paramTranslator.patternScope = paramTranslator.patternScope.previousSyntax;
  }
  
  public static PatternScope push(Translator paramTranslator) {
    PatternScope patternScope1 = new PatternScope();
    PatternScope patternScope2 = paramTranslator.patternScope;
    patternScope1.previousSyntax = patternScope2;
    paramTranslator.patternScope = patternScope1;
    if (patternScope2 == null) {
      patternScope1.pattern_names = new Vector();
      patternScope1.patternNesting = new StringBuffer();
      patternScope1.outer = paramTranslator.currentScope();
      return patternScope1;
    } 
    patternScope1.pattern_names = (Vector)patternScope2.pattern_names.clone();
    patternScope1.patternNesting = new StringBuffer(patternScope2.patternNesting.toString());
    patternScope1.outer = paramTranslator.currentScope();
    return patternScope1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/PatternScope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
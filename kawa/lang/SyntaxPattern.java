package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.OutPort;
import gnu.text.SourceLocator;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

public class SyntaxPattern extends Pattern implements Externalizable {
  static final int MATCH_ANY = 3;
  
  static final int MATCH_ANY_CAR = 7;
  
  static final int MATCH_EQUALS = 2;
  
  static final int MATCH_IGNORE = 24;
  
  static final int MATCH_LENGTH = 6;
  
  static final int MATCH_LREPEAT = 5;
  
  static final int MATCH_MISC = 0;
  
  static final int MATCH_NIL = 8;
  
  static final int MATCH_PAIR = 4;
  
  static final int MATCH_VECTOR = 16;
  
  static final int MATCH_WIDE = 1;
  
  Object[] literals;
  
  String program;
  
  int varCount;
  
  public SyntaxPattern(Object paramObject, Object[] paramArrayOfObject, Translator paramTranslator) {
    this(new StringBuffer(), paramObject, null, paramArrayOfObject, paramTranslator);
  }
  
  public SyntaxPattern(String paramString, Object[] paramArrayOfObject, int paramInt) {
    this.program = paramString;
    this.literals = paramArrayOfObject;
    this.varCount = paramInt;
  }
  
  SyntaxPattern(StringBuffer paramStringBuffer, Object paramObject, SyntaxForm paramSyntaxForm, Object[] paramArrayOfObject, Translator paramTranslator) {
    Vector vector = new Vector();
    translate(paramObject, paramStringBuffer, paramArrayOfObject, 0, vector, null, false, paramTranslator);
    this.program = paramStringBuffer.toString();
    this.literals = new Object[vector.size()];
    vector.copyInto(this.literals);
    this.varCount = paramTranslator.patternScope.pattern_names.size();
  }
  
  private static void addInt(StringBuffer paramStringBuffer, int paramInt) {
    if (paramInt > 65535)
      addInt(paramStringBuffer, (paramInt << 13) + 1); 
    paramStringBuffer.append((char)paramInt);
  }
  
  public static Object[] allocVars(int paramInt, Object[] paramArrayOfObject) {
    Object[] arrayOfObject = new Object[paramInt];
    if (paramArrayOfObject != null)
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramArrayOfObject.length); 
    return arrayOfObject;
  }
  
  public static Object[] getLiteralsList(Object paramObject, SyntaxForm paramSyntaxForm, Translator paramTranslator) {
    Object object = paramTranslator.pushPositionOf(paramObject);
    int j = Translator.listLength(paramObject);
    int i = j;
    if (j < 0) {
      paramTranslator.error('e', "missing or malformed literals list");
      i = 0;
    } 
    Object[] arrayOfObject = new Object[i + 1];
    for (j = 1; j <= i; j++) {
      while (paramObject instanceof SyntaxForm)
        paramObject = ((SyntaxForm)paramObject).getDatum(); 
      Pair pair = (Pair)paramObject;
      paramTranslator.pushPositionOf(pair);
      Object object1 = pair.getCar();
      paramObject = object1;
      if (paramObject instanceof SyntaxForm)
        paramObject = ((SyntaxForm)paramObject).getDatum(); 
      if (!(paramObject instanceof gnu.mapping.Symbol))
        paramTranslator.error('e', "non-symbol '" + paramObject + "' in literals list"); 
      arrayOfObject[j] = object1;
      paramObject = pair.getCdr();
    } 
    paramTranslator.popPositionOf(object);
    return arrayOfObject;
  }
  
  private static int insertInt(int paramInt1, StringBuffer paramStringBuffer, int paramInt2) {
    int i = paramInt1;
    if (paramInt2 > 65535)
      i = paramInt1 + insertInt(paramInt1, paramStringBuffer, (paramInt2 << 13) + 1); 
    paramStringBuffer.insert(i, (char)paramInt2);
    return i + 1;
  }
  
  public static boolean literalIdentifierEq(Object paramObject1, ScopeExp paramScopeExp1, Object paramObject2, ScopeExp paramScopeExp2) {
    boolean bool2 = true;
    if (paramObject1 != paramObject2 && (paramObject1 == null || paramObject2 == null || !paramObject1.equals(paramObject2)))
      return false; 
    boolean bool1 = bool2;
    if (paramScopeExp1 != paramScopeExp2) {
      Declaration declaration = null;
      ScopeExp scopeExp2 = null;
      ScopeExp scopeExp1 = paramScopeExp1;
      while (true) {
        Declaration declaration1 = declaration;
        paramScopeExp1 = scopeExp2;
        ScopeExp scopeExp = paramScopeExp2;
        if (scopeExp1 != null) {
          declaration1 = declaration;
          paramScopeExp1 = scopeExp2;
          scopeExp = paramScopeExp2;
          if (!(scopeExp1 instanceof gnu.expr.ModuleExp)) {
            declaration = scopeExp1.lookup(paramObject1);
            if (declaration != null) {
              scopeExp = paramScopeExp2;
              paramScopeExp1 = scopeExp2;
              declaration1 = declaration;
            } else {
              scopeExp1 = scopeExp1.outer;
              continue;
            } 
          } 
        } 
        while (true) {
          paramObject1 = paramScopeExp1;
          if (scopeExp != null) {
            paramObject1 = paramScopeExp1;
            if (!(scopeExp instanceof gnu.expr.ModuleExp)) {
              Declaration declaration2 = scopeExp.lookup(paramObject2);
              if (declaration2 != null) {
                paramObject1 = declaration2;
              } else {
                scopeExp = scopeExp.outer;
                continue;
              } 
            } 
          } 
          bool1 = bool2;
          return (declaration1 != paramObject1) ? false : bool1;
        } 
        break;
      } 
    } 
    return bool1;
  }
  
  public void disassemble() {
    disassemble((PrintWriter)OutPort.errDefault(), (Translator)Compilation.getCurrent(), 0, this.program.length());
  }
  
  public void disassemble(PrintWriter paramPrintWriter, Translator paramTranslator) {
    disassemble(paramPrintWriter, paramTranslator, 0, this.program.length());
  }
  
  void disassemble(PrintWriter paramPrintWriter, Translator paramTranslator, int paramInt1, int paramInt2) {
    Vector vector2 = null;
    Vector vector1 = vector2;
    if (paramTranslator != null) {
      vector1 = vector2;
      if (paramTranslator.patternScope != null)
        vector1 = paramTranslator.patternScope.pattern_names; 
    } 
    char c = Character.MIN_VALUE;
    int i = paramInt1;
    for (paramInt1 = c;; paramInt1 = 0) {
      if (i < paramInt2) {
        String str2;
        StringBuilder stringBuilder1;
        String str1;
        StringBuilder stringBuilder3;
        String str3;
        StringBuilder stringBuilder2;
        c = this.program.charAt(i);
        paramPrintWriter.print(" " + i + ": " + c);
        i++;
        int j = c & 0x7;
        paramInt1 = paramInt1 << 13 | c >> 3;
        switch (j) {
          default:
            paramPrintWriter.println(" - " + j + '/' + paramInt1);
            paramInt1 = 0;
            continue;
          case 1:
            paramPrintWriter.println(" - WIDE " + paramInt1);
            continue;
          case 2:
            paramPrintWriter.print(" - EQUALS[" + paramInt1 + "]");
            if (this.literals != null && paramInt1 >= 0 && paramInt1 < this.literals.length)
              paramPrintWriter.print(this.literals[paramInt1]); 
            paramPrintWriter.println();
            paramInt1 = 0;
            continue;
          case 3:
          case 7:
            stringBuilder3 = new StringBuilder();
            if (j == 3) {
              str2 = " - ANY[";
            } else {
              str2 = " - ANY_CAR[";
            } 
            paramPrintWriter.print(stringBuilder3.append(str2).append(paramInt1).append("]").toString());
            if (vector1 != null && paramInt1 >= 0 && paramInt1 < vector1.size())
              paramPrintWriter.print(vector1.elementAt(paramInt1)); 
            paramPrintWriter.println();
            paramInt1 = 0;
            continue;
          case 4:
            paramPrintWriter.println(" - PAIR[" + paramInt1 + "]");
            paramInt1 = 0;
            continue;
          case 5:
            paramPrintWriter.println(" - LREPEAT[" + paramInt1 + "]");
            disassemble(paramPrintWriter, paramTranslator, i, i + paramInt1);
            i += paramInt1;
            stringBuilder1 = (new StringBuilder()).append(" ").append(i).append(": - repeat first var:");
            str3 = this.program;
            paramInt1 = i + 1;
            paramPrintWriter.println(stringBuilder1.append(str3.charAt(i) >> 3).toString());
            stringBuilder1 = (new StringBuilder()).append(" ").append(paramInt1).append(": - repeast nested vars:");
            str3 = this.program;
            i = paramInt1 + 1;
            paramPrintWriter.println(stringBuilder1.append(str3.charAt(paramInt1) >> 3).toString());
            paramInt1 = 0;
            continue;
          case 6:
            stringBuilder2 = (new StringBuilder()).append(" - LENGTH ").append(paramInt1 >> 1).append(" pairs. ");
            if ((paramInt1 & 0x1) == 0) {
              str1 = "pure list";
            } else {
              str1 = "impure list";
            } 
            paramPrintWriter.println(stringBuilder2.append(str1).toString());
            paramInt1 = 0;
            continue;
          case 0:
            break;
        } 
        paramPrintWriter.print("[misc ch:" + c + " n:" + '\b' + "]");
        if (c == '\b') {
          paramPrintWriter.println(" - NIL");
        } else if (c == '\020') {
          paramPrintWriter.println(" - VECTOR");
        } else if (c == '\030') {
          paramPrintWriter.println(" - IGNORE");
        } else {
        
        } 
      } else {
        break;
      } 
    } 
  }
  
  public boolean match(Object paramObject, Object[] paramArrayOfObject, int paramInt) {
    return match(paramObject, paramArrayOfObject, paramInt, 0, null);
  }
  
  public boolean match(Object paramObject, Object[] paramArrayOfObject, int paramInt1, int paramInt2, SyntaxForm paramSyntaxForm) {
    int j = 0;
    int i = paramInt2;
    paramInt2 = j;
    label140: while (true) {
      Syntax syntax;
      Object object1;
      int m;
      int n;
      int i1;
      int i2;
      int i3;
      Object object3;
      Object[][] arrayOfObject;
      Translator translator;
      while (paramObject instanceof SyntaxForm) {
        paramSyntaxForm = (SyntaxForm)paramObject;
        paramObject = paramSyntaxForm.getDatum();
      } 
      String str = this.program;
      int k = i + 1;
      i = str.charAt(i);
      j = paramInt2 << 13 | i >> 3;
      switch (i & 0x7) {
        default:
          disassemble();
          throw new Error("unrecognized pattern opcode @pc:" + k);
        case 1:
          i = k;
          paramInt2 = j;
          continue;
        case 0:
          if (i == 8)
            return (paramObject == LList.Empty); 
          if (i == 16)
            return !(paramObject instanceof gnu.lists.FVector) ? false : match(LList.makeList((List)paramObject), paramArrayOfObject, paramInt1, k, paramSyntaxForm); 
          if (i == 24)
            return true; 
          throw new Error("unknwon pattern opcode");
        case 8:
          return (paramObject == LList.Empty);
        case 6:
          object2 = paramObject;
          paramInt2 = 0;
          while (true) {
            while (object2 instanceof SyntaxForm)
              object2 = ((SyntaxForm)object2).getDatum(); 
            if (paramInt2 == j >> 1) {
              if (((j & 0x1) == 0) ? (object2 != LList.Empty) : (object2 instanceof Pair))
                return false; 
              paramInt2 = 0;
              i = k;
              continue label140;
            } 
            if (object2 instanceof Pair) {
              object2 = ((Pair)object2).getCdr();
              paramInt2++;
              continue;
            } 
            return false;
          } 
          break;
        case 4:
          if (!(paramObject instanceof Pair))
            return false; 
          paramObject = paramObject;
          if (!match_car((Pair)paramObject, paramArrayOfObject, paramInt1, k, paramSyntaxForm))
            return false; 
          i = k + j;
          paramInt2 = 0;
          paramObject = paramObject.getCdr();
          continue;
        case 5:
          paramInt2 = k + j;
          object2 = this.program;
          i = paramInt2 + 1;
          paramInt2 = object2.charAt(paramInt2);
          j = paramInt2 >> 3;
          while ((paramInt2 & 0x7) == 1) {
            paramInt2 = this.program.charAt(i);
            j = j << 13 | paramInt2 >> 3;
            i++;
          } 
          i2 = j + paramInt1;
          m = this.program.charAt(i) >> 3;
          j = i + 1;
          i = paramInt2;
          for (paramInt2 = j; (i & 0x7) == 1; paramInt2++) {
            i = this.program.charAt(paramInt2);
            m = m << 13 | i >> 3;
          } 
          object2 = this.program;
          j = paramInt2 + 1;
          i = object2.charAt(paramInt2);
          n = 1;
          paramInt2 = 1;
          if (i == 8) {
            i1 = 0;
            n = paramInt2;
          } else {
            i1 = i >> 3;
            paramInt2 = j;
            j = i1;
            while ((i & 0x7) == 1) {
              i = this.program.charAt(paramInt2);
              j = j << 13 | i >> 3;
              paramInt2++;
            } 
            i = n;
            if ((j & 0x1) != 0)
              i = 0; 
            i1 = j >> 1;
            n = i;
            j = paramInt2;
          } 
          i = Translator.listLength(paramObject);
          if (i >= 0) {
            paramInt2 = 1;
          } else {
            paramInt2 = 0;
            i = -1 - i;
          } 
          if (i < i1 || (n != 0 && paramInt2 == 0))
            return false; 
          i3 = i - i1;
          arrayOfObject = new Object[m][];
          for (paramInt2 = 0; paramInt2 < m; paramInt2++)
            arrayOfObject[paramInt2] = new Object[i3]; 
          paramInt2 = 0;
          object3 = paramSyntaxForm;
          object2 = paramObject;
          while (paramInt2 < i3) {
            paramObject = object3;
            while (object2 instanceof SyntaxForm) {
              paramObject = object2;
              object2 = paramObject.getDatum();
            } 
            Pair pair = (Pair)object2;
            if (!match_car(pair, paramArrayOfObject, paramInt1, k, (SyntaxForm)paramObject))
              return false; 
            object2 = pair.getCdr();
            for (i = 0; i < m; i++)
              arrayOfObject[i][paramInt2] = paramArrayOfObject[i2 + i]; 
            paramInt2++;
            object3 = paramObject;
          } 
          for (paramInt2 = 0; paramInt2 < m; paramInt2++)
            paramArrayOfObject[i2 + paramInt2] = arrayOfObject[paramInt2]; 
          k = 0;
          paramInt2 = k;
          paramObject = object2;
          i = j;
          object1 = object3;
          if (i1 == 0) {
            paramInt2 = k;
            paramObject = object2;
            i = j;
            object1 = object3;
            if (n != 0)
              return true; 
          } 
          continue;
        case 2:
          object2 = this.literals[j];
          translator = (Translator)Compilation.getCurrent();
          if (object2 instanceof SyntaxForm) {
            SyntaxForm syntaxForm = (SyntaxForm)object2;
            object2 = syntaxForm.getDatum();
            TemplateScope templateScope = syntaxForm.getScope();
          } else {
            syntax = translator.getCurrentSyntax();
            if (syntax instanceof Macro) {
              ScopeExp scopeExp = ((Macro)syntax).getCapturedScope();
            } else {
              syntax = null;
            } 
          } 
          if (paramObject instanceof SyntaxForm) {
            paramObject = paramObject;
            object3 = paramObject.getDatum();
            paramObject = paramObject.getScope();
            return literalIdentifierEq(object2, (ScopeExp)syntax, object3, (ScopeExp)paramObject);
          } 
          object3 = paramObject;
          if (object1 == null) {
            paramObject = translator.currentScope();
            return literalIdentifierEq(object2, (ScopeExp)syntax, object3, (ScopeExp)paramObject);
          } 
          paramObject = object1.getScope();
          return literalIdentifierEq(object2, (ScopeExp)syntax, object3, (ScopeExp)paramObject);
        case 3:
          break;
      } 
      Object object2 = paramObject;
      if (object1 != null)
        object2 = SyntaxForms.fromDatum(paramObject, (SyntaxForm)object1); 
      syntax[paramInt1 + j] = (Syntax)object2;
      return true;
    } 
  }
  
  boolean match_car(Pair paramPair, Object[] paramArrayOfObject, int paramInt1, int paramInt2, SyntaxForm paramSyntaxForm) {
    String str = this.program;
    int i = paramInt2 + 1;
    char c = str.charAt(paramInt2);
    int j = c >> 3;
    while ((c & 0x7) == 1) {
      c = this.program.charAt(i);
      j = j << 13 | c >> 3;
      i++;
    } 
    if ((c & 0x7) == 7) {
      Pair pair = paramPair;
      if (paramSyntaxForm != null) {
        pair = paramPair;
        if (!(paramPair.getCar() instanceof SyntaxForm))
          pair = Translator.makePair(paramPair, SyntaxForms.fromDatum(paramPair.getCar(), paramSyntaxForm), paramPair.getCdr()); 
      } 
      paramArrayOfObject[paramInt1 + j] = pair;
      return true;
    } 
    return match(paramPair.getCar(), paramArrayOfObject, paramInt1, paramInt2, paramSyntaxForm);
  }
  
  public void print(Consumer paramConsumer) {
    paramConsumer.write("#<syntax-pattern>");
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.literals = (Object[])paramObjectInput.readObject();
    this.program = (String)paramObjectInput.readObject();
    this.varCount = paramObjectInput.readInt();
  }
  
  void translate(Object paramObject, StringBuffer paramStringBuffer, Object[] paramArrayOfObject, int paramInt, Vector<Object> paramVector, SyntaxForm paramSyntaxForm, char paramChar, Translator paramTranslator) {
    PatternScope patternScope = paramTranslator.patternScope;
    Vector<Object> vector = patternScope.pattern_names;
    while (true) {
      byte b;
      int j;
      int k;
      int m;
      int n;
      Object object1;
      Object object2;
      SyntaxForm syntaxForm = paramSyntaxForm;
      if (paramObject instanceof SyntaxForm) {
        paramSyntaxForm = (SyntaxForm)paramObject;
        paramObject = paramSyntaxForm.getDatum();
        continue;
      } 
      if (paramObject instanceof Pair) {
        object2 = paramTranslator.pushPositionOf(paramObject);
        try {
          m = paramStringBuffer.length();
          paramStringBuffer.append('\004');
          Pair pair = (Pair)paramObject;
          object1 = pair.getCdr();
          SyntaxForm syntaxForm1 = syntaxForm;
          while (object1 instanceof SyntaxForm) {
            syntaxForm1 = (SyntaxForm)object1;
            object1 = syntaxForm1.getDatum();
          } 
          k = 0;
          paramObject = object1;
          j = k;
          paramSyntaxForm = syntaxForm1;
          if (object1 instanceof Pair) {
            paramObject = object1;
            j = k;
            paramSyntaxForm = syntaxForm1;
            if (paramTranslator.matches(((Pair)object1).getCar(), "...")) {
              k = 1;
              object1 = ((Pair)object1).getCdr();
              paramSyntaxForm = syntaxForm1;
              while (true) {
                paramObject = object1;
                j = k;
                if (object1 instanceof SyntaxForm) {
                  paramSyntaxForm = (SyntaxForm)object1;
                  object1 = paramSyntaxForm.getDatum();
                  continue;
                } 
                break;
              } 
            } 
          } 
          n = vector.size();
          k = paramChar;
          if (paramChar == 'P')
            k = 0; 
          object1 = pair.getCar();
        } finally {
          paramTranslator.popPositionOf(object2);
        } 
      } else {
        if (paramObject instanceof gnu.mapping.Symbol) {
          j = paramArrayOfObject.length;
          while (true) {
            int i3;
            if (--j >= 0) {
              ScopeExp scopeExp = paramTranslator.currentScope();
              if (syntaxForm == null) {
                ScopeExp scopeExp1 = scopeExp;
              } else {
                object1 = syntaxForm.getScope();
              } 
              Object object = paramArrayOfObject[j];
              if (object instanceof SyntaxForm) {
                SyntaxForm syntaxForm1 = (SyntaxForm)object;
                object = syntaxForm1.getDatum();
                TemplateScope templateScope = syntaxForm1.getScope();
              } else if (paramTranslator.currentMacroDefinition != null) {
                scopeExp = paramTranslator.currentMacroDefinition.getCapturedScope();
              } 
              if (literalIdentifierEq(paramObject, (ScopeExp)object1, object, scopeExp)) {
                i3 = SyntaxTemplate.indexOf(paramVector, paramObject);
                paramInt = i3;
                if (i3 < 0) {
                  paramInt = paramVector.size();
                  paramVector.addElement(paramObject);
                } 
                addInt(paramStringBuffer, paramInt << 3 | 0x2);
                return;
              } 
              continue;
            } 
            if (vector.contains(paramObject))
              paramTranslator.syntaxError("duplicated pattern variable " + paramObject); 
            k = vector.size();
            vector.addElement(paramObject);
            if (i3 == 80) {
              i3 = 1;
            } else {
              i3 = 0;
            } 
            if (i3 != 0) {
              j = 1;
            } else {
              j = 0;
            } 
            patternScope.patternNesting.append((char)((paramInt << 1) + j));
            paramObject = patternScope.addDeclaration(paramObject);
            paramObject.setLocation((SourceLocator)paramTranslator);
            paramTranslator.push((Declaration)paramObject);
            if (i3 != 0) {
              paramInt = 7;
            } else {
              paramInt = 3;
            } 
            addInt(paramStringBuffer, paramInt | k << 3);
            return;
          } 
          break;
        } 
        if (paramObject == LList.Empty) {
          paramStringBuffer.append('\b');
          return;
        } 
        if (paramObject instanceof gnu.lists.FVector) {
          paramStringBuffer.append('\020');
          paramObject = LList.makeList((List)paramObject);
          paramChar = 'V';
          paramSyntaxForm = syntaxForm;
          continue;
        } 
        i = SyntaxTemplate.indexOf(paramVector, paramObject);
        paramInt = i;
        if (i < 0) {
          paramInt = paramVector.size();
          paramVector.addElement(paramObject);
        } 
        addInt(paramStringBuffer, paramInt << 3 | 0x2);
        return;
      } 
      if (k == 86) {
        b = 0;
      } else {
        b = 80;
      } 
      translate(object1, paramStringBuffer, paramArrayOfObject, i, paramVector, syntaxForm, b, paramTranslator);
      int i1 = vector.size();
      int i2 = paramStringBuffer.length();
      if (j != 0) {
        i = 5;
      } else {
        i = 4;
      } 
      i2 = i2 - m - 1 << 3 | i;
      int i = m;
      if (i2 > 65535)
        i = m + insertInt(m, paramStringBuffer, (i2 >> 13) + 1); 
      paramStringBuffer.setCharAt(i, (char)i2);
      i = Translator.listLength(paramObject);
      if (i == Integer.MIN_VALUE) {
        paramTranslator.syntaxError("cyclic pattern list");
        paramTranslator.popPositionOf(object2);
        return;
      } 
      if (j != 0) {
        addInt(paramStringBuffer, n << 3);
        addInt(paramStringBuffer, i1 - n << 3);
        if (paramObject == LList.Empty) {
          paramStringBuffer.append('\b');
          paramTranslator.popPositionOf(object2);
          return;
        } 
        if (i >= 0) {
          i <<= 1;
        } else {
          i = (-i << 1) - 1;
        } 
        addInt(paramStringBuffer, i << 3 | 0x6);
      } 
      paramTranslator.popPositionOf(object2);
      i = k;
    } 
  }
  
  public int varCount() {
    return this.varCount;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.program);
    paramObjectOutput.writeObject(this.literals);
    paramObjectOutput.writeInt(this.varCount);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/SyntaxPattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
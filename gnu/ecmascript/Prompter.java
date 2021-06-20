package gnu.ecmascript;

import gnu.mapping.InPort;
import gnu.mapping.Procedure1;

class Prompter extends Procedure1 {
  public Object apply1(Object paramObject) {
    return prompt((InPort)paramObject);
  }
  
  String prompt(InPort paramInPort) {
    return "(EcmaScript:" + (paramInPort.getLineNumber() + 1) + ") ";
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/ecmascript/Prompter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
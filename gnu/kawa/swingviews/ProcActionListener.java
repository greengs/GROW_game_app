package gnu.kawa.swingviews;

import gnu.mapping.Procedure;
import gnu.mapping.WrappedException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ProcActionListener implements ActionListener {
  Procedure proc;
  
  public ProcActionListener(Procedure paramProcedure) {
    this.proc = paramProcedure;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    try {
      this.proc.apply1(paramActionEvent);
      return;
    } catch (Throwable throwable) {
      throw new WrappedException(throwable);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/swingviews/ProcActionListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
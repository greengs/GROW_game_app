package gnu.bytecode;

public class IfState {
  boolean doing_else;
  
  Label end_label;
  
  IfState previous;
  
  int stack_growth;
  
  int start_stack_size;
  
  Type[] then_stacked_types;
  
  public IfState(CodeAttr paramCodeAttr) {
    this(paramCodeAttr, new Label(paramCodeAttr));
  }
  
  public IfState(CodeAttr paramCodeAttr, Label paramLabel) {
    this.previous = paramCodeAttr.if_stack;
    paramCodeAttr.if_stack = this;
    this.end_label = paramLabel;
    this.start_stack_size = paramCodeAttr.SP;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/IfState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
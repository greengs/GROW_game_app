package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;

public class Attributes extends MethodProc {
  public static final Attributes attributes = new Attributes();
  
  public static void attributes(TreeList paramTreeList, int paramInt, Consumer paramConsumer) {
    paramInt = paramTreeList.gotoAttributesStart(paramInt);
    System.out.print("Attributes called, at:" + paramInt + " ");
    paramTreeList.dump();
    while (true) {
      if (paramInt >= 0) {
        int i = paramInt << 1;
        if (paramTreeList.getNextKind(i) == 35) {
          int j = paramTreeList.nextDataIndex(paramInt);
          if (paramConsumer instanceof PositionConsumer) {
            ((PositionConsumer)paramConsumer).writePosition((AbstractSequence)paramTreeList, i);
          } else {
            paramTreeList.consumeIRange(paramInt, j, paramConsumer);
          } 
          paramInt = j;
          continue;
        } 
      } 
      return;
    } 
  }
  
  public static void attributes(Object paramObject, Consumer paramConsumer) {
    if (paramObject instanceof TreeList) {
      attributes((TreeList)paramObject, 0, paramConsumer);
      return;
    } 
    if (paramObject instanceof SeqPosition && !(paramObject instanceof gnu.lists.TreePosition)) {
      paramObject = paramObject;
      if (((SeqPosition)paramObject).sequence instanceof TreeList) {
        attributes((TreeList)((SeqPosition)paramObject).sequence, ((SeqPosition)paramObject).ipos >> 1, paramConsumer);
        return;
      } 
    } 
  }
  
  public void apply(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    Object object = paramCallContext.getNextArg();
    paramCallContext.lastArg();
    if (object instanceof gnu.mapping.Values) {
      TreeList treeList = (TreeList)object;
      for (int i = 0;; i = treeList.nextDataIndex(i)) {
        int j = treeList.getNextKind(i << 1);
        if (j == 0)
          return; 
        if (j == 32) {
          attributes(treeList.getPosNext(i << 1), consumer);
        } else {
          attributes(treeList, i, consumer);
        } 
      } 
    } 
    attributes(object, consumer);
  }
  
  public int numArgs() {
    return 4097;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/Attributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
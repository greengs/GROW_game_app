package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;

public class Children extends MethodProc {
  public static final Children children = new Children();
  
  public static void children(TreeList paramTreeList, int paramInt, Consumer paramConsumer) {
    int i = paramTreeList.gotoChildrenStart(paramInt);
    if (i >= 0) {
      int j = paramTreeList.nextDataIndex(paramInt);
      while (true) {
        int k = paramTreeList.nextNodeIndex(i, j);
        paramInt = k;
        if (k == i)
          paramInt = paramTreeList.nextDataIndex(i); 
        if (paramInt >= 0) {
          if (paramConsumer instanceof PositionConsumer) {
            ((PositionConsumer)paramConsumer).writePosition((AbstractSequence)paramTreeList, i << 1);
          } else {
            paramTreeList.consumeIRange(i, paramInt, paramConsumer);
          } 
          i = paramInt;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public static void children(Object paramObject, Consumer paramConsumer) {
    if (paramObject instanceof TreeList) {
      children((TreeList)paramObject, 0, paramConsumer);
      return;
    } 
    if (paramObject instanceof SeqPosition && !(paramObject instanceof gnu.lists.TreePosition)) {
      paramObject = paramObject;
      if (((SeqPosition)paramObject).sequence instanceof TreeList) {
        children((TreeList)((SeqPosition)paramObject).sequence, ((SeqPosition)paramObject).ipos >> 1, paramConsumer);
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
          children(treeList.getPosNext(i << 1), consumer);
        } else {
          children(treeList, i, consumer);
        } 
      } 
    } 
    children(object, consumer);
  }
  
  public int numArgs() {
    return 4097;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/Children.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
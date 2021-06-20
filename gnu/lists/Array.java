package gnu.lists;

public interface Array {
  Object get(int[] paramArrayOfint);
  
  int getEffectiveIndex(int[] paramArrayOfint);
  
  int getLowBound(int paramInt);
  
  Object getRowMajor(int paramInt);
  
  int getSize(int paramInt);
  
  boolean isEmpty();
  
  int rank();
  
  Object set(int[] paramArrayOfint, Object paramObject);
  
  Array transpose(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt, int[] paramArrayOfint3);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/Array.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
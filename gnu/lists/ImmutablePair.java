package gnu.lists;

public class ImmutablePair extends Pair {
  public ImmutablePair() {}
  
  public ImmutablePair(Object paramObject1, Object paramObject2) {
    this.car = paramObject1;
    this.cdr = paramObject2;
  }
  
  public void setCar(Object paramObject) {
    throw new UnsupportedOperationException("cannot modify read-only pair");
  }
  
  public void setCdr(Object paramObject) {
    throw new UnsupportedOperationException("cannot modify read-only pair");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/ImmutablePair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
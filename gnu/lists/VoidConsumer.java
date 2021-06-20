package gnu.lists;

public class VoidConsumer extends FilterConsumer {
  public static VoidConsumer instance = new VoidConsumer();
  
  public VoidConsumer() {
    super(null);
  }
  
  public static VoidConsumer getInstance() {
    return instance;
  }
  
  public boolean ignoring() {
    return true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/VoidConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
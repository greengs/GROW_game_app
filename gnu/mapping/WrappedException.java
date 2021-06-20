package gnu.mapping;

public class WrappedException extends RuntimeException {
  public WrappedException() {}
  
  public WrappedException(String paramString) {
    super(paramString);
  }
  
  public WrappedException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
  
  public WrappedException(Throwable paramThrowable) {
    this(paramThrowable.toString(), paramThrowable);
  }
  
  public static RuntimeException wrapIfNeeded(Throwable paramThrowable) {
    return (paramThrowable instanceof RuntimeException) ? (RuntimeException)paramThrowable : new WrappedException(paramThrowable);
  }
  
  public Throwable getException() {
    return getCause();
  }
  
  public String toString() {
    return getMessage();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/WrappedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.mapping;

public class ProcLocation extends Location {
  Object[] args;
  
  Procedure proc;
  
  public ProcLocation(Procedure paramProcedure, Object[] paramArrayOfObject) {
    this.proc = paramProcedure;
    this.args = paramArrayOfObject;
  }
  
  public Object get(Object paramObject) {
    try {
      return this.proc.applyN(this.args);
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (Error error) {
      throw error;
    } catch (Throwable throwable) {
      throw new WrappedException(throwable);
    } 
  }
  
  public boolean isBound() {
    return true;
  }
  
  public void set(Object paramObject) {
    int i = this.args.length;
    Object[] arrayOfObject = new Object[i + 1];
    arrayOfObject[i] = paramObject;
    System.arraycopy(this.args, 0, arrayOfObject, 0, i);
    try {
      this.proc.setN(arrayOfObject);
      return;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (Error error) {
      throw error;
    } catch (Throwable throwable) {
      throw new WrappedException(throwable);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/ProcLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
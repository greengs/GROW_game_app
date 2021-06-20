package gnu.expr;

class BlockExitException extends RuntimeException {
  ExitExp exit;
  
  Object result;
  
  public BlockExitException(ExitExp paramExitExp, Object paramObject) {
    this.exit = paramExitExp;
    this.result = paramObject;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/BlockExitException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package gnu.bytecode;

public interface Member {
  ClassType getDeclaringClass();
  
  int getModifiers();
  
  String getName();
  
  boolean getStaticFlag();
  
  void setName(String paramString);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/Member.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
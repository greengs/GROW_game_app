package gnu.kawa.models;

public interface Window {
  Display getDisplay();
  
  String getTitle();
  
  void open();
  
  void setContent(Object paramObject);
  
  void setMenuBar(Object paramObject);
  
  void setTitle(String paramString);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/models/Window.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
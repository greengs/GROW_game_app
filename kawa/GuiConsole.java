package kawa;

import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class GuiConsole extends JFrame implements ActionListener, ReplDocument.DocumentCloseListener {
  private static String CLOSE = "Close";
  
  private static String EXIT = "Exit";
  
  private static String NEW = "New";
  
  private static String NEW_SHARED = "New (Shared)";
  
  private static String PURGE_MESSAGE = "Purge Buffer";
  
  static int window_number = 0;
  
  ReplDocument document;
  
  ReplPane pane;
  
  public GuiConsole() {
    this(Language.getDefaultLanguage(), Environment.getCurrent(), false);
  }
  
  public GuiConsole(Language paramLanguage, Environment paramEnvironment, boolean paramBoolean) {
    super("Kawa");
    repl.getLanguage();
    init(new ReplDocument(paramLanguage, paramEnvironment, paramBoolean));
  }
  
  public GuiConsole(ReplDocument paramReplDocument) {
    super("Kawa");
    init(paramReplDocument);
  }
  
  public static void main(String[] paramArrayOfString) {
    repl.noConsole = false;
    int i = repl.processArgs(paramArrayOfString, 0, paramArrayOfString.length);
    repl.getLanguage();
    repl.setArgs(paramArrayOfString, i);
    repl.checkInitFile();
    new GuiConsole();
  }
  
  private void setupMenus() {
    WindowAdapter windowAdapter = new WindowAdapter() {
        public void windowClosing(WindowEvent param1WindowEvent) {
          GuiConsole.this.close();
        }
      };
    MenuBar menuBar = new MenuBar();
    Menu menu2 = new Menu("File");
    Menu menu1 = new Menu("Utilities");
    menuBar.add(menu2);
    menuBar.add(menu1);
    MenuItem menuItem2 = new MenuItem(NEW);
    menuItem2.addActionListener(this);
    menu2.add(menuItem2);
    menuItem2 = new MenuItem(NEW_SHARED);
    menuItem2.addActionListener(this);
    menu2.add(menuItem2);
    menuItem2 = new MenuItem(CLOSE);
    menuItem2.addActionListener(this);
    menu2.add(menuItem2);
    menuItem2 = new MenuItem(EXIT);
    menuItem2.addActionListener(this);
    addWindowListener(windowAdapter);
    menu2.add(menuItem2);
    MenuItem menuItem1 = new MenuItem(PURGE_MESSAGE);
    menuItem1.addActionListener(this);
    menu1.add(menuItem1);
    setMenuBar(menuBar);
  }
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    String str = paramActionEvent.getActionCommand();
    if (str.equals(NEW)) {
      new GuiConsole(this.document.language, Environment.getGlobal(), false);
      return;
    } 
    if (str.equals(NEW_SHARED)) {
      new GuiConsole(this.document.language, this.document.environment, true);
      return;
    } 
    if (str.equals(EXIT)) {
      System.exit(0);
      return;
    } 
    if (str.equals(CLOSE)) {
      close();
      return;
    } 
    if (str.equals(PURGE_MESSAGE)) {
      this.pane.document.deleteOldText();
      return;
    } 
    OutPort.outDefault().println("Unknown menu action: " + str);
  }
  
  void close() {
    this.document.removeDocumentCloseListener(this);
    dispose();
  }
  
  public void closed(ReplDocument paramReplDocument) {
    close();
  }
  
  void init(ReplDocument paramReplDocument) {
    this.document = paramReplDocument;
    this.document.addDocumentCloseListener(this);
    this.pane = new ReplPane(this.document);
    window_number++;
    setLayout(new BorderLayout(0, 0));
    add("Center", new JScrollPane(this.pane));
    setupMenus();
    setLocation(window_number * 100, window_number * 50);
    setSize(700, 500);
    setVisible(true);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/GuiConsole.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
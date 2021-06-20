package kawa;

import gnu.kawa.models.Paintable;
import gnu.kawa.models.Viewable;
import gnu.kawa.swingviews.SwingDisplay;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

class ReplEditorKit extends StyledEditorKit {
  ViewFactory factory;
  
  final ReplPane pane;
  
  ViewFactory styledFactory;
  
  public ReplEditorKit(final ReplPane pane) {
    this.pane = pane;
    this.styledFactory = super.getViewFactory();
    this.factory = new ViewFactory() {
        public View create(Element param1Element) {
          String str = param1Element.getName();
          return (str == "Viewable") ? new ComponentView(param1Element) {
              protected Component createComponent() {
                AttributeSet attributeSet = getElement().getAttributes();
                JPanel jPanel = new JPanel();
                ((Viewable)attributeSet.getAttribute(ReplPane.ViewableAttribute)).makeView(SwingDisplay.getInstance(), jPanel);
                if (jPanel.getComponentCount() == 1) {
                  Component component = jPanel.getComponent(0);
                  jPanel.removeAll();
                  return component;
                } 
                jPanel.setBackground(pane.getBackground());
                return jPanel;
              }
            } : ((str == "Paintable") ? new PaintableView(param1Element, (Paintable)param1Element.getAttributes().getAttribute(ReplPane.PaintableAttribute)) : ReplEditorKit.this.styledFactory.create(param1Element));
        }
      };
  }
  
  public ViewFactory getViewFactory() {
    return this.factory;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/ReplEditorKit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
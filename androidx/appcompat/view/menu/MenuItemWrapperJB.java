package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.view.ActionProvider;

@RequiresApi(16)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class MenuItemWrapperJB extends MenuItemWrapperICS {
  MenuItemWrapperJB(Context paramContext, SupportMenuItem paramSupportMenuItem) {
    super(paramContext, paramSupportMenuItem);
  }
  
  MenuItemWrapperICS.ActionProviderWrapper createActionProviderWrapper(ActionProvider paramActionProvider) {
    return new ActionProviderWrapperJB(this.mContext, paramActionProvider);
  }
  
  class ActionProviderWrapperJB extends MenuItemWrapperICS.ActionProviderWrapper implements ActionProvider.VisibilityListener {
    ActionProvider.VisibilityListener mListener;
    
    public ActionProviderWrapperJB(Context param1Context, ActionProvider param1ActionProvider) {
      super(param1Context, param1ActionProvider);
    }
    
    public boolean isVisible() {
      return this.mInner.isVisible();
    }
    
    public void onActionProviderVisibilityChanged(boolean param1Boolean) {
      if (this.mListener != null)
        this.mListener.onActionProviderVisibilityChanged(param1Boolean); 
    }
    
    public View onCreateActionView(MenuItem param1MenuItem) {
      return this.mInner.onCreateActionView(param1MenuItem);
    }
    
    public boolean overridesItemVisibility() {
      return this.mInner.overridesItemVisibility();
    }
    
    public void refreshVisibility() {
      this.mInner.refreshVisibility();
    }
    
    public void setVisibilityListener(ActionProvider.VisibilityListener param1VisibilityListener) {
      this.mListener = param1VisibilityListener;
      ActionProvider actionProvider = this.mInner;
      if (param1VisibilityListener != null) {
        ActionProviderWrapperJB actionProviderWrapperJB = this;
      } else {
        param1VisibilityListener = null;
      } 
      actionProvider.setVisibilityListener((ActionProvider.VisibilityListener)param1VisibilityListener);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/view/menu/MenuItemWrapperJB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
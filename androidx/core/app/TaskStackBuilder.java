package androidx.core.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Iterator;

public final class TaskStackBuilder implements Iterable<Intent> {
  private static final String TAG = "TaskStackBuilder";
  
  private final ArrayList<Intent> mIntents = new ArrayList<Intent>();
  
  private final Context mSourceContext;
  
  private TaskStackBuilder(Context paramContext) {
    this.mSourceContext = paramContext;
  }
  
  @NonNull
  public static TaskStackBuilder create(@NonNull Context paramContext) {
    return new TaskStackBuilder(paramContext);
  }
  
  @Deprecated
  public static TaskStackBuilder from(Context paramContext) {
    return create(paramContext);
  }
  
  @NonNull
  public TaskStackBuilder addNextIntent(@NonNull Intent paramIntent) {
    this.mIntents.add(paramIntent);
    return this;
  }
  
  @NonNull
  public TaskStackBuilder addNextIntentWithParentStack(@NonNull Intent paramIntent) {
    ComponentName componentName2 = paramIntent.getComponent();
    ComponentName componentName1 = componentName2;
    if (componentName2 == null)
      componentName1 = paramIntent.resolveActivity(this.mSourceContext.getPackageManager()); 
    if (componentName1 != null)
      addParentStack(componentName1); 
    addNextIntent(paramIntent);
    return this;
  }
  
  @NonNull
  public TaskStackBuilder addParentStack(@NonNull Activity paramActivity) {
    Intent intent1 = null;
    if (paramActivity instanceof SupportParentable)
      intent1 = ((SupportParentable)paramActivity).getSupportParentActivityIntent(); 
    Intent intent2 = intent1;
    if (intent1 == null)
      intent2 = NavUtils.getParentActivityIntent(paramActivity); 
    if (intent2 != null) {
      ComponentName componentName2 = intent2.getComponent();
      ComponentName componentName1 = componentName2;
      if (componentName2 == null)
        componentName1 = intent2.resolveActivity(this.mSourceContext.getPackageManager()); 
      addParentStack(componentName1);
      addNextIntent(intent2);
    } 
    return this;
  }
  
  public TaskStackBuilder addParentStack(ComponentName paramComponentName) {
    int i = this.mIntents.size();
    try {
      for (Intent intent = NavUtils.getParentActivityIntent(this.mSourceContext, paramComponentName); intent != null; intent = NavUtils.getParentActivityIntent(this.mSourceContext, intent.getComponent()))
        this.mIntents.add(i, intent); 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
      throw new IllegalArgumentException(nameNotFoundException);
    } 
    return this;
  }
  
  @NonNull
  public TaskStackBuilder addParentStack(@NonNull Class<?> paramClass) {
    return addParentStack(new ComponentName(this.mSourceContext, paramClass));
  }
  
  @Nullable
  public Intent editIntentAt(int paramInt) {
    return this.mIntents.get(paramInt);
  }
  
  @Deprecated
  public Intent getIntent(int paramInt) {
    return editIntentAt(paramInt);
  }
  
  public int getIntentCount() {
    return this.mIntents.size();
  }
  
  @NonNull
  public Intent[] getIntents() {
    Intent[] arrayOfIntent = new Intent[this.mIntents.size()];
    if (arrayOfIntent.length != 0) {
      arrayOfIntent[0] = (new Intent(this.mIntents.get(0))).addFlags(268484608);
      int i = 1;
      while (true) {
        if (i < arrayOfIntent.length) {
          arrayOfIntent[i] = new Intent(this.mIntents.get(i));
          i++;
          continue;
        } 
        return arrayOfIntent;
      } 
    } 
    return arrayOfIntent;
  }
  
  @Nullable
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2) {
    return getPendingIntent(paramInt1, paramInt2, null);
  }
  
  @Nullable
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2, @Nullable Bundle paramBundle) {
    if (this.mIntents.isEmpty())
      throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent"); 
    Intent[] arrayOfIntent = this.mIntents.<Intent>toArray(new Intent[this.mIntents.size()]);
    arrayOfIntent[0] = (new Intent(arrayOfIntent[0])).addFlags(268484608);
    return (Build.VERSION.SDK_INT >= 16) ? PendingIntent.getActivities(this.mSourceContext, paramInt1, arrayOfIntent, paramInt2, paramBundle) : PendingIntent.getActivities(this.mSourceContext, paramInt1, arrayOfIntent, paramInt2);
  }
  
  @Deprecated
  public Iterator<Intent> iterator() {
    return this.mIntents.iterator();
  }
  
  public void startActivities() {
    startActivities(null);
  }
  
  public void startActivities(@Nullable Bundle paramBundle) {
    if (this.mIntents.isEmpty())
      throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities"); 
    Intent[] arrayOfIntent = this.mIntents.<Intent>toArray(new Intent[this.mIntents.size()]);
    arrayOfIntent[0] = (new Intent(arrayOfIntent[0])).addFlags(268484608);
    if (!ContextCompat.startActivities(this.mSourceContext, arrayOfIntent, paramBundle)) {
      Intent intent = new Intent(arrayOfIntent[arrayOfIntent.length - 1]);
      intent.addFlags(268435456);
      this.mSourceContext.startActivity(intent);
    } 
  }
  
  public static interface SupportParentable {
    @Nullable
    Intent getSupportParentActivityIntent();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/app/TaskStackBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
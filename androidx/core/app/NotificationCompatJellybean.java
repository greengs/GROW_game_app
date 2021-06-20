package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiresApi(16)
class NotificationCompatJellybean {
  static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
  
  static final String EXTRA_DATA_ONLY_REMOTE_INPUTS = "android.support.dataRemoteInputs";
  
  private static final String KEY_ACTION_INTENT = "actionIntent";
  
  private static final String KEY_ALLOWED_DATA_TYPES = "allowedDataTypes";
  
  private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
  
  private static final String KEY_CHOICES = "choices";
  
  private static final String KEY_DATA_ONLY_REMOTE_INPUTS = "dataOnlyRemoteInputs";
  
  private static final String KEY_EXTRAS = "extras";
  
  private static final String KEY_ICON = "icon";
  
  private static final String KEY_LABEL = "label";
  
  private static final String KEY_REMOTE_INPUTS = "remoteInputs";
  
  private static final String KEY_RESULT_KEY = "resultKey";
  
  private static final String KEY_SEMANTIC_ACTION = "semanticAction";
  
  private static final String KEY_SHOWS_USER_INTERFACE = "showsUserInterface";
  
  private static final String KEY_TITLE = "title";
  
  public static final String TAG = "NotificationCompat";
  
  private static Class<?> sActionClass;
  
  private static Field sActionIconField;
  
  private static Field sActionIntentField;
  
  private static Field sActionTitleField;
  
  private static boolean sActionsAccessFailed;
  
  private static Field sActionsField;
  
  private static final Object sActionsLock;
  
  private static Field sExtrasField;
  
  private static boolean sExtrasFieldAccessFailed;
  
  private static final Object sExtrasLock = new Object();
  
  static {
    sActionsLock = new Object();
  }
  
  public static SparseArray<Bundle> buildActionExtrasMap(List<Bundle> paramList) {
    SparseArray<Bundle> sparseArray = null;
    int i = 0;
    int j = paramList.size();
    while (i < j) {
      Bundle bundle = paramList.get(i);
      SparseArray<Bundle> sparseArray1 = sparseArray;
      if (bundle != null) {
        sparseArray1 = sparseArray;
        if (sparseArray == null)
          sparseArray1 = new SparseArray(); 
        sparseArray1.put(i, bundle);
      } 
      i++;
      sparseArray = sparseArray1;
    } 
    return sparseArray;
  }
  
  private static boolean ensureActionReflectionReadyLocked() {
    boolean bool = true;
    if (sActionsAccessFailed)
      return false; 
    try {
      if (sActionsField == null) {
        sActionClass = Class.forName("android.app.Notification$Action");
        sActionIconField = sActionClass.getDeclaredField("icon");
        sActionTitleField = sActionClass.getDeclaredField("title");
        sActionIntentField = sActionClass.getDeclaredField("actionIntent");
        sActionsField = Notification.class.getDeclaredField("actions");
        sActionsField.setAccessible(true);
      } 
    } catch (ClassNotFoundException classNotFoundException) {
      Log.e("NotificationCompat", "Unable to access notification actions", classNotFoundException);
      sActionsAccessFailed = true;
    } catch (NoSuchFieldException noSuchFieldException) {
      Log.e("NotificationCompat", "Unable to access notification actions", noSuchFieldException);
      sActionsAccessFailed = true;
    } 
    if (sActionsAccessFailed)
      bool = false; 
    return bool;
  }
  
  private static RemoteInput fromBundle(Bundle paramBundle) {
    ArrayList arrayList = paramBundle.getStringArrayList("allowedDataTypes");
    HashSet<String> hashSet = new HashSet();
    if (arrayList != null) {
      Iterator<String> iterator = arrayList.iterator();
      while (iterator.hasNext())
        hashSet.add(iterator.next()); 
    } 
    return new RemoteInput(paramBundle.getString("resultKey"), paramBundle.getCharSequence("label"), paramBundle.getCharSequenceArray("choices"), paramBundle.getBoolean("allowFreeFormInput"), paramBundle.getBundle("extras"), hashSet);
  }
  
  private static RemoteInput[] fromBundleArray(Bundle[] paramArrayOfBundle) {
    if (paramArrayOfBundle == null)
      return null; 
    RemoteInput[] arrayOfRemoteInput = new RemoteInput[paramArrayOfBundle.length];
    int i = 0;
    while (true) {
      RemoteInput[] arrayOfRemoteInput1 = arrayOfRemoteInput;
      if (i < paramArrayOfBundle.length) {
        arrayOfRemoteInput[i] = fromBundle(paramArrayOfBundle[i]);
        i++;
        continue;
      } 
      return arrayOfRemoteInput1;
    } 
  }
  
  public static NotificationCompat.Action getAction(Notification paramNotification, int paramInt) {
    synchronized (sActionsLock) {
      Object[] arrayOfObject = getActionObjectsLocked(paramNotification);
      if (arrayOfObject != null) {
        Bundle bundle1;
        Object object = arrayOfObject[paramInt];
        arrayOfObject = null;
        Bundle bundle2 = getExtras(paramNotification);
        Object[] arrayOfObject1 = arrayOfObject;
        if (bundle2 != null) {
          SparseArray sparseArray = bundle2.getSparseParcelableArray("android.support.actionExtras");
          arrayOfObject1 = arrayOfObject;
          if (sparseArray != null)
            bundle1 = (Bundle)sparseArray.get(paramInt); 
        } 
        return readAction(sActionIconField.getInt(object), (CharSequence)sActionTitleField.get(object), (PendingIntent)sActionIntentField.get(object), bundle1);
      } 
      return null;
    } 
  }
  
  public static int getActionCount(Notification paramNotification) {
    synchronized (sActionsLock) {
      Object[] arrayOfObject = getActionObjectsLocked(paramNotification);
      if (arrayOfObject != null)
        return arrayOfObject.length; 
    } 
    boolean bool = false;
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_2} */
    return bool;
  }
  
  static NotificationCompat.Action getActionFromBundle(Bundle paramBundle) {
    Bundle bundle = paramBundle.getBundle("extras");
    boolean bool = false;
    if (bundle != null)
      bool = bundle.getBoolean("android.support.allowGeneratedReplies", false); 
    return new NotificationCompat.Action(paramBundle.getInt("icon"), paramBundle.getCharSequence("title"), (PendingIntent)paramBundle.getParcelable("actionIntent"), paramBundle.getBundle("extras"), fromBundleArray(getBundleArrayFromBundle(paramBundle, "remoteInputs")), fromBundleArray(getBundleArrayFromBundle(paramBundle, "dataOnlyRemoteInputs")), bool, paramBundle.getInt("semanticAction"), paramBundle.getBoolean("showsUserInterface"));
  }
  
  private static Object[] getActionObjectsLocked(Notification paramNotification) {
    synchronized (sActionsLock) {
      if (!ensureActionReflectionReadyLocked())
        return null; 
      try {
        return (Object[])sActionsField.get(paramNotification);
      } catch (IllegalAccessException illegalAccessException) {}
    } 
    Log.e("NotificationCompat", "Unable to access notification actions", (Throwable)paramNotification);
    sActionsAccessFailed = true;
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_1} */
    return null;
  }
  
  private static Bundle[] getBundleArrayFromBundle(Bundle paramBundle, String paramString) {
    Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray(paramString);
    if (arrayOfParcelable instanceof Bundle[] || arrayOfParcelable == null)
      return (Bundle[])arrayOfParcelable; 
    Bundle[] arrayOfBundle = Arrays.<Bundle, Parcelable>copyOf(arrayOfParcelable, arrayOfParcelable.length, Bundle[].class);
    paramBundle.putParcelableArray(paramString, (Parcelable[])arrayOfBundle);
    return arrayOfBundle;
  }
  
  static Bundle getBundleForAction(NotificationCompat.Action paramAction) {
    Bundle bundle2 = new Bundle();
    bundle2.putInt("icon", paramAction.getIcon());
    bundle2.putCharSequence("title", paramAction.getTitle());
    bundle2.putParcelable("actionIntent", (Parcelable)paramAction.getActionIntent());
    if (paramAction.getExtras() != null) {
      Bundle bundle = new Bundle(paramAction.getExtras());
      bundle.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
      bundle2.putBundle("extras", bundle);
      bundle2.putParcelableArray("remoteInputs", (Parcelable[])toBundleArray(paramAction.getRemoteInputs()));
      bundle2.putBoolean("showsUserInterface", paramAction.getShowsUserInterface());
      bundle2.putInt("semanticAction", paramAction.getSemanticAction());
      return bundle2;
    } 
    Bundle bundle1 = new Bundle();
    bundle1.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    bundle2.putBundle("extras", bundle1);
    bundle2.putParcelableArray("remoteInputs", (Parcelable[])toBundleArray(paramAction.getRemoteInputs()));
    bundle2.putBoolean("showsUserInterface", paramAction.getShowsUserInterface());
    bundle2.putInt("semanticAction", paramAction.getSemanticAction());
    return bundle2;
  }
  
  public static Bundle getExtras(Notification paramNotification) {
    synchronized (sExtrasLock) {
      if (sExtrasFieldAccessFailed)
        return null; 
      try {
        if (sExtrasField == null) {
          Field field = Notification.class.getDeclaredField("extras");
          if (!Bundle.class.isAssignableFrom(field.getType())) {
            Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
            sExtrasFieldAccessFailed = true;
            return null;
          } 
          field.setAccessible(true);
          sExtrasField = field;
        } 
        Bundle bundle2 = (Bundle)sExtrasField.get(paramNotification);
        Bundle bundle1 = bundle2;
        if (bundle2 == null) {
          bundle1 = new Bundle();
          sExtrasField.set(paramNotification, bundle1);
        } 
        return bundle1;
      } catch (IllegalAccessException illegalAccessException) {
        Log.e("NotificationCompat", "Unable to access notification extras", illegalAccessException);
      } catch (NoSuchFieldException noSuchFieldException) {}
    } 
  }
  
  public static NotificationCompat.Action readAction(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle) {
    RemoteInput[] arrayOfRemoteInput1 = null;
    RemoteInput[] arrayOfRemoteInput2 = null;
    boolean bool = false;
    if (paramBundle != null) {
      arrayOfRemoteInput1 = fromBundleArray(getBundleArrayFromBundle(paramBundle, "android.support.remoteInputs"));
      arrayOfRemoteInput2 = fromBundleArray(getBundleArrayFromBundle(paramBundle, "android.support.dataRemoteInputs"));
      bool = paramBundle.getBoolean("android.support.allowGeneratedReplies");
    } 
    return new NotificationCompat.Action(paramInt, paramCharSequence, paramPendingIntent, paramBundle, arrayOfRemoteInput1, arrayOfRemoteInput2, bool, 0, true);
  }
  
  private static Bundle toBundle(RemoteInput paramRemoteInput) {
    Bundle bundle = new Bundle();
    bundle.putString("resultKey", paramRemoteInput.getResultKey());
    bundle.putCharSequence("label", paramRemoteInput.getLabel());
    bundle.putCharSequenceArray("choices", paramRemoteInput.getChoices());
    bundle.putBoolean("allowFreeFormInput", paramRemoteInput.getAllowFreeFormInput());
    bundle.putBundle("extras", paramRemoteInput.getExtras());
    Set<String> set = paramRemoteInput.getAllowedDataTypes();
    if (set != null && !set.isEmpty()) {
      ArrayList<String> arrayList = new ArrayList(set.size());
      Iterator<String> iterator = set.iterator();
      while (iterator.hasNext())
        arrayList.add(iterator.next()); 
      bundle.putStringArrayList("allowedDataTypes", arrayList);
    } 
    return bundle;
  }
  
  private static Bundle[] toBundleArray(RemoteInput[] paramArrayOfRemoteInput) {
    if (paramArrayOfRemoteInput == null)
      return null; 
    Bundle[] arrayOfBundle = new Bundle[paramArrayOfRemoteInput.length];
    int i = 0;
    while (true) {
      Bundle[] arrayOfBundle1 = arrayOfBundle;
      if (i < paramArrayOfRemoteInput.length) {
        arrayOfBundle[i] = toBundle(paramArrayOfRemoteInput[i]);
        i++;
        continue;
      } 
      return arrayOfBundle1;
    } 
  }
  
  public static Bundle writeActionAndGetExtras(Notification.Builder paramBuilder, NotificationCompat.Action paramAction) {
    paramBuilder.addAction(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
    Bundle bundle = new Bundle(paramAction.getExtras());
    if (paramAction.getRemoteInputs() != null)
      bundle.putParcelableArray("android.support.remoteInputs", (Parcelable[])toBundleArray(paramAction.getRemoteInputs())); 
    if (paramAction.getDataOnlyRemoteInputs() != null)
      bundle.putParcelableArray("android.support.dataRemoteInputs", (Parcelable[])toBundleArray(paramAction.getDataOnlyRemoteInputs())); 
    bundle.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    return bundle;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/app/NotificationCompatJellybean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
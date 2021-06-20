package androidx.core.app;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class RemoteInput {
  private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
  
  public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
  
  public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
  
  private static final String TAG = "RemoteInput";
  
  private final boolean mAllowFreeFormTextInput;
  
  private final Set<String> mAllowedDataTypes;
  
  private final CharSequence[] mChoices;
  
  private final Bundle mExtras;
  
  private final CharSequence mLabel;
  
  private final String mResultKey;
  
  RemoteInput(String paramString, CharSequence paramCharSequence, CharSequence[] paramArrayOfCharSequence, boolean paramBoolean, Bundle paramBundle, Set<String> paramSet) {
    this.mResultKey = paramString;
    this.mLabel = paramCharSequence;
    this.mChoices = paramArrayOfCharSequence;
    this.mAllowFreeFormTextInput = paramBoolean;
    this.mExtras = paramBundle;
    this.mAllowedDataTypes = paramSet;
  }
  
  public static void addDataResultToIntent(RemoteInput paramRemoteInput, Intent paramIntent, Map<String, Uri> paramMap) {
    if (Build.VERSION.SDK_INT >= 26) {
      android.app.RemoteInput.addDataResultToIntent(fromCompat(paramRemoteInput), paramIntent, paramMap);
      return;
    } 
    if (Build.VERSION.SDK_INT >= 16) {
      Intent intent2 = getClipDataIntentFromIntent(paramIntent);
      Intent intent1 = intent2;
      if (intent2 == null)
        intent1 = new Intent(); 
      for (Map.Entry<String, Uri> entry : paramMap.entrySet()) {
        String str = (String)entry.getKey();
        Uri uri = (Uri)entry.getValue();
        if (str != null) {
          Bundle bundle2 = intent1.getBundleExtra(getExtraResultsKeyForData(str));
          Bundle bundle1 = bundle2;
          if (bundle2 == null)
            bundle1 = new Bundle(); 
          bundle1.putString(paramRemoteInput.getResultKey(), uri.toString());
          intent1.putExtra(getExtraResultsKeyForData(str), bundle1);
        } 
      } 
      paramIntent.setClipData(ClipData.newIntent("android.remoteinput.results", intent1));
      return;
    } 
    Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
  }
  
  public static void addResultsToIntent(RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle) {
    int i = 0;
    if (Build.VERSION.SDK_INT >= 26) {
      android.app.RemoteInput.addResultsToIntent(fromCompat(paramArrayOfRemoteInput), paramIntent, paramBundle);
      return;
    } 
    if (Build.VERSION.SDK_INT >= 20) {
      Bundle bundle = getResultsFromIntent(paramIntent);
      if (bundle != null) {
        bundle.putAll(paramBundle);
        paramBundle = bundle;
      } 
      int j = paramArrayOfRemoteInput.length;
      i = 0;
      while (true) {
        if (i < j) {
          RemoteInput remoteInput = paramArrayOfRemoteInput[i];
          Map<String, Uri> map = getDataResultsFromIntent(paramIntent, remoteInput.getResultKey());
          android.app.RemoteInput.addResultsToIntent(fromCompat(new RemoteInput[] { remoteInput }, ), paramIntent, paramBundle);
          if (map != null)
            addDataResultToIntent(remoteInput, paramIntent, map); 
          i++;
          continue;
        } 
        return;
      } 
    } 
    if (Build.VERSION.SDK_INT >= 16) {
      Intent intent2 = getClipDataIntentFromIntent(paramIntent);
      Intent intent1 = intent2;
      if (intent2 == null)
        intent1 = new Intent(); 
      Bundle bundle2 = intent1.getBundleExtra("android.remoteinput.resultsData");
      Bundle bundle1 = bundle2;
      if (bundle2 == null)
        bundle1 = new Bundle(); 
      int j = paramArrayOfRemoteInput.length;
      while (i < j) {
        RemoteInput remoteInput = paramArrayOfRemoteInput[i];
        Object object = paramBundle.get(remoteInput.getResultKey());
        if (object instanceof CharSequence)
          bundle1.putCharSequence(remoteInput.getResultKey(), (CharSequence)object); 
        i++;
      } 
      intent1.putExtra("android.remoteinput.resultsData", bundle1);
      paramIntent.setClipData(ClipData.newIntent("android.remoteinput.results", intent1));
      return;
    } 
    Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
  }
  
  @RequiresApi(20)
  static android.app.RemoteInput fromCompat(RemoteInput paramRemoteInput) {
    return (new android.app.RemoteInput.Builder(paramRemoteInput.getResultKey())).setLabel(paramRemoteInput.getLabel()).setChoices(paramRemoteInput.getChoices()).setAllowFreeFormInput(paramRemoteInput.getAllowFreeFormInput()).addExtras(paramRemoteInput.getExtras()).build();
  }
  
  @RequiresApi(20)
  static android.app.RemoteInput[] fromCompat(RemoteInput[] paramArrayOfRemoteInput) {
    if (paramArrayOfRemoteInput == null)
      return null; 
    android.app.RemoteInput[] arrayOfRemoteInput = new android.app.RemoteInput[paramArrayOfRemoteInput.length];
    int i = 0;
    while (true) {
      android.app.RemoteInput[] arrayOfRemoteInput1 = arrayOfRemoteInput;
      if (i < paramArrayOfRemoteInput.length) {
        arrayOfRemoteInput[i] = fromCompat(paramArrayOfRemoteInput[i]);
        i++;
        continue;
      } 
      return arrayOfRemoteInput1;
    } 
  }
  
  @RequiresApi(16)
  private static Intent getClipDataIntentFromIntent(Intent paramIntent) {
    ClipData clipData = paramIntent.getClipData();
    if (clipData != null) {
      ClipDescription clipDescription = clipData.getDescription();
      if (clipDescription.hasMimeType("text/vnd.android.intent") && clipDescription.getLabel().equals("android.remoteinput.results"))
        return clipData.getItemAt(0).getIntent(); 
    } 
    return null;
  }
  
  public static Map<String, Uri> getDataResultsFromIntent(Intent paramIntent, String paramString) {
    Intent intent = null;
    if (Build.VERSION.SDK_INT >= 26)
      return android.app.RemoteInput.getDataResultsFromIntent(paramIntent, paramString); 
    if (Build.VERSION.SDK_INT >= 16) {
      HashMap<Object, Object> hashMap;
      Intent intent1 = getClipDataIntentFromIntent(paramIntent);
      paramIntent = intent;
      if (intent1 != null) {
        HashMap<Object, Object> hashMap1 = new HashMap<Object, Object>();
        for (String str : intent1.getExtras().keySet()) {
          if (str.startsWith("android.remoteinput.dataTypeResultsData")) {
            String str1 = str.substring("android.remoteinput.dataTypeResultsData".length());
            if (!str1.isEmpty()) {
              str = intent1.getBundleExtra(str).getString(paramString);
              if (str != null && !str.isEmpty())
                hashMap1.put(str1, Uri.parse(str)); 
            } 
          } 
        } 
        hashMap = hashMap1;
        if (hashMap1.isEmpty())
          hashMap = null; 
        return (Map)hashMap;
      } 
      return (Map)hashMap;
    } 
    Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    return null;
  }
  
  private static String getExtraResultsKeyForData(String paramString) {
    return "android.remoteinput.dataTypeResultsData" + paramString;
  }
  
  public static Bundle getResultsFromIntent(Intent paramIntent) {
    Intent intent = null;
    if (Build.VERSION.SDK_INT >= 20)
      return android.app.RemoteInput.getResultsFromIntent(paramIntent); 
    if (Build.VERSION.SDK_INT >= 16) {
      Intent intent1 = getClipDataIntentFromIntent(paramIntent);
      paramIntent = intent;
      return (Bundle)((intent1 != null) ? intent1.getExtras().getParcelable("android.remoteinput.resultsData") : paramIntent);
    } 
    Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    return null;
  }
  
  public boolean getAllowFreeFormInput() {
    return this.mAllowFreeFormTextInput;
  }
  
  public Set<String> getAllowedDataTypes() {
    return this.mAllowedDataTypes;
  }
  
  public CharSequence[] getChoices() {
    return this.mChoices;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public CharSequence getLabel() {
    return this.mLabel;
  }
  
  public String getResultKey() {
    return this.mResultKey;
  }
  
  public boolean isDataOnly() {
    return (!getAllowFreeFormInput() && (getChoices() == null || (getChoices()).length == 0) && getAllowedDataTypes() != null && !getAllowedDataTypes().isEmpty());
  }
  
  public static final class Builder {
    private boolean mAllowFreeFormTextInput = true;
    
    private final Set<String> mAllowedDataTypes = new HashSet<String>();
    
    private CharSequence[] mChoices;
    
    private final Bundle mExtras = new Bundle();
    
    private CharSequence mLabel;
    
    private final String mResultKey;
    
    public Builder(@NonNull String param1String) {
      if (param1String == null)
        throw new IllegalArgumentException("Result key can't be null"); 
      this.mResultKey = param1String;
    }
    
    @NonNull
    public Builder addExtras(@NonNull Bundle param1Bundle) {
      if (param1Bundle != null)
        this.mExtras.putAll(param1Bundle); 
      return this;
    }
    
    @NonNull
    public RemoteInput build() {
      return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mExtras, this.mAllowedDataTypes);
    }
    
    @NonNull
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    @NonNull
    public Builder setAllowDataType(@NonNull String param1String, boolean param1Boolean) {
      if (param1Boolean) {
        this.mAllowedDataTypes.add(param1String);
        return this;
      } 
      this.mAllowedDataTypes.remove(param1String);
      return this;
    }
    
    @NonNull
    public Builder setAllowFreeFormInput(boolean param1Boolean) {
      this.mAllowFreeFormTextInput = param1Boolean;
      return this;
    }
    
    @NonNull
    public Builder setChoices(@Nullable CharSequence[] param1ArrayOfCharSequence) {
      this.mChoices = param1ArrayOfCharSequence;
      return this;
    }
    
    @NonNull
    public Builder setLabel(@Nullable CharSequence param1CharSequence) {
      this.mLabel = param1CharSequence;
      return this;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/app/RemoteInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
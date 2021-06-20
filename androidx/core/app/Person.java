package androidx.core.app;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;

public class Person {
  private static final String ICON_KEY = "icon";
  
  private static final String IS_BOT_KEY = "isBot";
  
  private static final String IS_IMPORTANT_KEY = "isImportant";
  
  private static final String KEY_KEY = "key";
  
  private static final String NAME_KEY = "name";
  
  private static final String URI_KEY = "uri";
  
  @Nullable
  IconCompat mIcon;
  
  boolean mIsBot;
  
  boolean mIsImportant;
  
  @Nullable
  String mKey;
  
  @Nullable
  CharSequence mName;
  
  @Nullable
  String mUri;
  
  Person(Builder paramBuilder) {
    this.mName = paramBuilder.mName;
    this.mIcon = paramBuilder.mIcon;
    this.mUri = paramBuilder.mUri;
    this.mKey = paramBuilder.mKey;
    this.mIsBot = paramBuilder.mIsBot;
    this.mIsImportant = paramBuilder.mIsImportant;
  }
  
  @NonNull
  @RequiresApi(28)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static Person fromAndroidPerson(@NonNull android.app.Person paramPerson) {
    Builder builder = (new Builder()).setName(paramPerson.getName());
    if (paramPerson.getIcon() != null) {
      IconCompat iconCompat1 = IconCompat.createFromIcon(paramPerson.getIcon());
      return builder.setIcon(iconCompat1).setUri(paramPerson.getUri()).setKey(paramPerson.getKey()).setBot(paramPerson.isBot()).setImportant(paramPerson.isImportant()).build();
    } 
    IconCompat iconCompat = null;
    return builder.setIcon(iconCompat).setUri(paramPerson.getUri()).setKey(paramPerson.getKey()).setBot(paramPerson.isBot()).setImportant(paramPerson.isImportant()).build();
  }
  
  @NonNull
  public static Person fromBundle(@NonNull Bundle paramBundle) {
    Bundle bundle = paramBundle.getBundle("icon");
    Builder builder = (new Builder()).setName(paramBundle.getCharSequence("name"));
    if (bundle != null) {
      IconCompat iconCompat = IconCompat.createFromBundle(bundle);
      return builder.setIcon(iconCompat).setUri(paramBundle.getString("uri")).setKey(paramBundle.getString("key")).setBot(paramBundle.getBoolean("isBot")).setImportant(paramBundle.getBoolean("isImportant")).build();
    } 
    bundle = null;
    return builder.setIcon((IconCompat)bundle).setUri(paramBundle.getString("uri")).setKey(paramBundle.getString("key")).setBot(paramBundle.getBoolean("isBot")).setImportant(paramBundle.getBoolean("isImportant")).build();
  }
  
  @Nullable
  public IconCompat getIcon() {
    return this.mIcon;
  }
  
  @Nullable
  public String getKey() {
    return this.mKey;
  }
  
  @Nullable
  public CharSequence getName() {
    return this.mName;
  }
  
  @Nullable
  public String getUri() {
    return this.mUri;
  }
  
  public boolean isBot() {
    return this.mIsBot;
  }
  
  public boolean isImportant() {
    return this.mIsImportant;
  }
  
  @NonNull
  @RequiresApi(28)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public android.app.Person toAndroidPerson() {
    android.app.Person.Builder builder = (new android.app.Person.Builder()).setName(getName());
    if (getIcon() != null) {
      Icon icon1 = getIcon().toIcon();
      return builder.setIcon(icon1).setUri(getUri()).setKey(getKey()).setBot(isBot()).setImportant(isImportant()).build();
    } 
    Icon icon = null;
    return builder.setIcon(icon).setUri(getUri()).setKey(getKey()).setBot(isBot()).setImportant(isImportant()).build();
  }
  
  @NonNull
  public Builder toBuilder() {
    return new Builder(this);
  }
  
  @NonNull
  public Bundle toBundle() {
    Bundle bundle2 = new Bundle();
    bundle2.putCharSequence("name", this.mName);
    if (this.mIcon != null) {
      Bundle bundle = this.mIcon.toBundle();
      bundle2.putBundle("icon", bundle);
      bundle2.putString("uri", this.mUri);
      bundle2.putString("key", this.mKey);
      bundle2.putBoolean("isBot", this.mIsBot);
      bundle2.putBoolean("isImportant", this.mIsImportant);
      return bundle2;
    } 
    Bundle bundle1 = null;
    bundle2.putBundle("icon", bundle1);
    bundle2.putString("uri", this.mUri);
    bundle2.putString("key", this.mKey);
    bundle2.putBoolean("isBot", this.mIsBot);
    bundle2.putBoolean("isImportant", this.mIsImportant);
    return bundle2;
  }
  
  public static class Builder {
    @Nullable
    IconCompat mIcon;
    
    boolean mIsBot;
    
    boolean mIsImportant;
    
    @Nullable
    String mKey;
    
    @Nullable
    CharSequence mName;
    
    @Nullable
    String mUri;
    
    public Builder() {}
    
    Builder(Person param1Person) {
      this.mName = param1Person.mName;
      this.mIcon = param1Person.mIcon;
      this.mUri = param1Person.mUri;
      this.mKey = param1Person.mKey;
      this.mIsBot = param1Person.mIsBot;
      this.mIsImportant = param1Person.mIsImportant;
    }
    
    @NonNull
    public Person build() {
      return new Person(this);
    }
    
    @NonNull
    public Builder setBot(boolean param1Boolean) {
      this.mIsBot = param1Boolean;
      return this;
    }
    
    @NonNull
    public Builder setIcon(@Nullable IconCompat param1IconCompat) {
      this.mIcon = param1IconCompat;
      return this;
    }
    
    @NonNull
    public Builder setImportant(boolean param1Boolean) {
      this.mIsImportant = param1Boolean;
      return this;
    }
    
    @NonNull
    public Builder setKey(@Nullable String param1String) {
      this.mKey = param1String;
      return this;
    }
    
    @NonNull
    public Builder setName(@Nullable CharSequence param1CharSequence) {
      this.mName = param1CharSequence;
      return this;
    }
    
    @NonNull
    public Builder setUri(@Nullable String param1String) {
      this.mUri = param1String;
      return this;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/app/Person.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
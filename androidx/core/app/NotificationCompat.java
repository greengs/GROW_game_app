package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Person;
import android.app.RemoteInput;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.text.BidiFormatter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NotificationCompat {
  public static final int BADGE_ICON_LARGE = 2;
  
  public static final int BADGE_ICON_NONE = 0;
  
  public static final int BADGE_ICON_SMALL = 1;
  
  public static final String CATEGORY_ALARM = "alarm";
  
  public static final String CATEGORY_CALL = "call";
  
  public static final String CATEGORY_EMAIL = "email";
  
  public static final String CATEGORY_ERROR = "err";
  
  public static final String CATEGORY_EVENT = "event";
  
  public static final String CATEGORY_MESSAGE = "msg";
  
  public static final String CATEGORY_PROGRESS = "progress";
  
  public static final String CATEGORY_PROMO = "promo";
  
  public static final String CATEGORY_RECOMMENDATION = "recommendation";
  
  public static final String CATEGORY_REMINDER = "reminder";
  
  public static final String CATEGORY_SERVICE = "service";
  
  public static final String CATEGORY_SOCIAL = "social";
  
  public static final String CATEGORY_STATUS = "status";
  
  public static final String CATEGORY_SYSTEM = "sys";
  
  public static final String CATEGORY_TRANSPORT = "transport";
  
  @ColorInt
  public static final int COLOR_DEFAULT = 0;
  
  public static final int DEFAULT_ALL = -1;
  
  public static final int DEFAULT_LIGHTS = 4;
  
  public static final int DEFAULT_SOUND = 1;
  
  public static final int DEFAULT_VIBRATE = 2;
  
  public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
  
  public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
  
  public static final String EXTRA_BIG_TEXT = "android.bigText";
  
  public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
  
  public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
  
  public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";
  
  public static final String EXTRA_INFO_TEXT = "android.infoText";
  
  public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
  
  public static final String EXTRA_LARGE_ICON = "android.largeIcon";
  
  public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
  
  public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
  
  public static final String EXTRA_MESSAGES = "android.messages";
  
  public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";
  
  public static final String EXTRA_PEOPLE = "android.people";
  
  public static final String EXTRA_PICTURE = "android.picture";
  
  public static final String EXTRA_PROGRESS = "android.progress";
  
  public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
  
  public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
  
  public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
  
  public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
  
  public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
  
  public static final String EXTRA_SHOW_WHEN = "android.showWhen";
  
  public static final String EXTRA_SMALL_ICON = "android.icon";
  
  public static final String EXTRA_SUB_TEXT = "android.subText";
  
  public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
  
  public static final String EXTRA_TEMPLATE = "android.template";
  
  public static final String EXTRA_TEXT = "android.text";
  
  public static final String EXTRA_TEXT_LINES = "android.textLines";
  
  public static final String EXTRA_TITLE = "android.title";
  
  public static final String EXTRA_TITLE_BIG = "android.title.big";
  
  public static final int FLAG_AUTO_CANCEL = 16;
  
  public static final int FLAG_FOREGROUND_SERVICE = 64;
  
  public static final int FLAG_GROUP_SUMMARY = 512;
  
  @Deprecated
  public static final int FLAG_HIGH_PRIORITY = 128;
  
  public static final int FLAG_INSISTENT = 4;
  
  public static final int FLAG_LOCAL_ONLY = 256;
  
  public static final int FLAG_NO_CLEAR = 32;
  
  public static final int FLAG_ONGOING_EVENT = 2;
  
  public static final int FLAG_ONLY_ALERT_ONCE = 8;
  
  public static final int FLAG_SHOW_LIGHTS = 1;
  
  public static final int GROUP_ALERT_ALL = 0;
  
  public static final int GROUP_ALERT_CHILDREN = 2;
  
  public static final int GROUP_ALERT_SUMMARY = 1;
  
  public static final int PRIORITY_DEFAULT = 0;
  
  public static final int PRIORITY_HIGH = 1;
  
  public static final int PRIORITY_LOW = -1;
  
  public static final int PRIORITY_MAX = 2;
  
  public static final int PRIORITY_MIN = -2;
  
  public static final int STREAM_DEFAULT = -1;
  
  public static final int VISIBILITY_PRIVATE = 0;
  
  public static final int VISIBILITY_PUBLIC = 1;
  
  public static final int VISIBILITY_SECRET = -1;
  
  public static Action getAction(Notification paramNotification, int paramInt) {
    Bundle bundle;
    if (Build.VERSION.SDK_INT >= 20)
      return getActionCompatFromAction(paramNotification.actions[paramInt]); 
    if (Build.VERSION.SDK_INT >= 19) {
      Notification.Action action = paramNotification.actions[paramInt];
      Notification notification = null;
      SparseArray sparseArray = paramNotification.extras.getSparseParcelableArray("android.support.actionExtras");
      paramNotification = notification;
      if (sparseArray != null)
        bundle = (Bundle)sparseArray.get(paramInt); 
      return NotificationCompatJellybean.readAction(action.icon, action.title, action.actionIntent, bundle);
    } 
    return (Build.VERSION.SDK_INT >= 16) ? NotificationCompatJellybean.getAction((Notification)bundle, paramInt) : null;
  }
  
  @RequiresApi(20)
  static Action getActionCompatFromAction(Notification.Action paramAction) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getRemoteInputs : ()[Landroid/app/RemoteInput;
    //   4: astore #5
    //   6: aload #5
    //   8: ifnonnull -> 99
    //   11: aconst_null
    //   12: astore #4
    //   14: getstatic android/os/Build$VERSION.SDK_INT : I
    //   17: bipush #24
    //   19: if_icmplt -> 171
    //   22: aload_0
    //   23: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   26: ldc_w 'android.support.allowGeneratedReplies'
    //   29: invokevirtual getBoolean : (Ljava/lang/String;)Z
    //   32: ifne -> 42
    //   35: aload_0
    //   36: invokevirtual getAllowGeneratedReplies : ()Z
    //   39: ifeq -> 166
    //   42: iconst_1
    //   43: istore_2
    //   44: aload_0
    //   45: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   48: ldc_w 'android.support.action.showsUserInterface'
    //   51: iconst_1
    //   52: invokevirtual getBoolean : (Ljava/lang/String;Z)Z
    //   55: istore_3
    //   56: getstatic android/os/Build$VERSION.SDK_INT : I
    //   59: bipush #28
    //   61: if_icmplt -> 185
    //   64: aload_0
    //   65: invokevirtual getSemanticAction : ()I
    //   68: istore_1
    //   69: new androidx/core/app/NotificationCompat$Action
    //   72: dup
    //   73: aload_0
    //   74: getfield icon : I
    //   77: aload_0
    //   78: getfield title : Ljava/lang/CharSequence;
    //   81: aload_0
    //   82: getfield actionIntent : Landroid/app/PendingIntent;
    //   85: aload_0
    //   86: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   89: aload #4
    //   91: aconst_null
    //   92: iload_2
    //   93: iload_1
    //   94: iload_3
    //   95: invokespecial <init> : (ILjava/lang/CharSequence;Landroid/app/PendingIntent;Landroid/os/Bundle;[Landroidx/core/app/RemoteInput;[Landroidx/core/app/RemoteInput;ZIZ)V
    //   98: areturn
    //   99: aload #5
    //   101: arraylength
    //   102: anewarray androidx/core/app/RemoteInput
    //   105: astore #4
    //   107: iconst_0
    //   108: istore_1
    //   109: iload_1
    //   110: aload #5
    //   112: arraylength
    //   113: if_icmpge -> 200
    //   116: aload #5
    //   118: iload_1
    //   119: aaload
    //   120: astore #6
    //   122: aload #4
    //   124: iload_1
    //   125: new androidx/core/app/RemoteInput
    //   128: dup
    //   129: aload #6
    //   131: invokevirtual getResultKey : ()Ljava/lang/String;
    //   134: aload #6
    //   136: invokevirtual getLabel : ()Ljava/lang/CharSequence;
    //   139: aload #6
    //   141: invokevirtual getChoices : ()[Ljava/lang/CharSequence;
    //   144: aload #6
    //   146: invokevirtual getAllowFreeFormInput : ()Z
    //   149: aload #6
    //   151: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   154: aconst_null
    //   155: invokespecial <init> : (Ljava/lang/String;Ljava/lang/CharSequence;[Ljava/lang/CharSequence;ZLandroid/os/Bundle;Ljava/util/Set;)V
    //   158: aastore
    //   159: iload_1
    //   160: iconst_1
    //   161: iadd
    //   162: istore_1
    //   163: goto -> 109
    //   166: iconst_0
    //   167: istore_2
    //   168: goto -> 44
    //   171: aload_0
    //   172: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   175: ldc_w 'android.support.allowGeneratedReplies'
    //   178: invokevirtual getBoolean : (Ljava/lang/String;)Z
    //   181: istore_2
    //   182: goto -> 44
    //   185: aload_0
    //   186: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   189: ldc_w 'android.support.action.semanticAction'
    //   192: iconst_0
    //   193: invokevirtual getInt : (Ljava/lang/String;I)I
    //   196: istore_1
    //   197: goto -> 69
    //   200: goto -> 14
  }
  
  public static int getActionCount(Notification paramNotification) {
    int i = 0;
    if (Build.VERSION.SDK_INT >= 19) {
      if (paramNotification.actions != null)
        i = paramNotification.actions.length; 
      return i;
    } 
    return (Build.VERSION.SDK_INT >= 16) ? NotificationCompatJellybean.getActionCount(paramNotification) : i;
  }
  
  public static int getBadgeIconType(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 26) ? paramNotification.getBadgeIconType() : 0;
  }
  
  public static String getCategory(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 21) ? paramNotification.category : null;
  }
  
  public static String getChannelId(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 26) ? paramNotification.getChannelId() : null;
  }
  
  @RequiresApi(19)
  public static CharSequence getContentTitle(Notification paramNotification) {
    return paramNotification.extras.getCharSequence("android.title");
  }
  
  @Nullable
  public static Bundle getExtras(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 19) ? paramNotification.extras : ((Build.VERSION.SDK_INT >= 16) ? NotificationCompatJellybean.getExtras(paramNotification) : null);
  }
  
  public static String getGroup(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 20) ? paramNotification.getGroup() : ((Build.VERSION.SDK_INT >= 19) ? paramNotification.extras.getString("android.support.groupKey") : ((Build.VERSION.SDK_INT >= 16) ? NotificationCompatJellybean.getExtras(paramNotification).getString("android.support.groupKey") : null));
  }
  
  public static int getGroupAlertBehavior(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 26) ? paramNotification.getGroupAlertBehavior() : 0;
  }
  
  @RequiresApi(21)
  public static List<Action> getInvisibleActions(Notification paramNotification) {
    ArrayList<Action> arrayList = new ArrayList();
    Bundle bundle = paramNotification.extras.getBundle("android.car.EXTENSIONS");
    if (bundle != null) {
      bundle = bundle.getBundle("invisible_actions");
      if (bundle != null) {
        int i = 0;
        while (true) {
          if (i < bundle.size()) {
            arrayList.add(NotificationCompatJellybean.getActionFromBundle(bundle.getBundle(Integer.toString(i))));
            i++;
            continue;
          } 
          return arrayList;
        } 
      } 
    } 
    return arrayList;
  }
  
  public static boolean getLocalOnly(Notification paramNotification) {
    boolean bool = false;
    if (Build.VERSION.SDK_INT >= 20) {
      if ((paramNotification.flags & 0x100) != 0)
        bool = true; 
      return bool;
    } 
    return (Build.VERSION.SDK_INT >= 19) ? paramNotification.extras.getBoolean("android.support.localOnly") : ((Build.VERSION.SDK_INT >= 16) ? NotificationCompatJellybean.getExtras(paramNotification).getBoolean("android.support.localOnly") : bool);
  }
  
  static Notification[] getNotificationArrayFromBundle(Bundle paramBundle, String paramString) {
    Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray(paramString);
    if (arrayOfParcelable instanceof Notification[] || arrayOfParcelable == null)
      return (Notification[])arrayOfParcelable; 
    Notification[] arrayOfNotification = new Notification[arrayOfParcelable.length];
    for (int i = 0; i < arrayOfParcelable.length; i++)
      arrayOfNotification[i] = (Notification)arrayOfParcelable[i]; 
    paramBundle.putParcelableArray(paramString, (Parcelable[])arrayOfNotification);
    return arrayOfNotification;
  }
  
  public static String getShortcutId(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 26) ? paramNotification.getShortcutId() : null;
  }
  
  public static String getSortKey(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 20) ? paramNotification.getSortKey() : ((Build.VERSION.SDK_INT >= 19) ? paramNotification.extras.getString("android.support.sortKey") : ((Build.VERSION.SDK_INT >= 16) ? NotificationCompatJellybean.getExtras(paramNotification).getString("android.support.sortKey") : null));
  }
  
  public static long getTimeoutAfter(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 26) ? paramNotification.getTimeoutAfter() : 0L;
  }
  
  public static boolean isGroupSummary(Notification paramNotification) {
    boolean bool = false;
    if (Build.VERSION.SDK_INT >= 20) {
      if ((paramNotification.flags & 0x200) != 0)
        bool = true; 
      return bool;
    } 
    return (Build.VERSION.SDK_INT >= 19) ? paramNotification.extras.getBoolean("android.support.isGroupSummary") : ((Build.VERSION.SDK_INT >= 16) ? NotificationCompatJellybean.getExtras(paramNotification).getBoolean("android.support.isGroupSummary") : bool);
  }
  
  public static class Action {
    static final String EXTRA_SEMANTIC_ACTION = "android.support.action.semanticAction";
    
    static final String EXTRA_SHOWS_USER_INTERFACE = "android.support.action.showsUserInterface";
    
    public static final int SEMANTIC_ACTION_ARCHIVE = 5;
    
    public static final int SEMANTIC_ACTION_CALL = 10;
    
    public static final int SEMANTIC_ACTION_DELETE = 4;
    
    public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
    
    public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
    
    public static final int SEMANTIC_ACTION_MUTE = 6;
    
    public static final int SEMANTIC_ACTION_NONE = 0;
    
    public static final int SEMANTIC_ACTION_REPLY = 1;
    
    public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
    
    public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
    
    public static final int SEMANTIC_ACTION_UNMUTE = 7;
    
    public PendingIntent actionIntent;
    
    public int icon;
    
    private boolean mAllowGeneratedReplies;
    
    private final RemoteInput[] mDataOnlyRemoteInputs;
    
    final Bundle mExtras;
    
    private final RemoteInput[] mRemoteInputs;
    
    private final int mSemanticAction;
    
    boolean mShowsUserInterface = true;
    
    public CharSequence title;
    
    public Action(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent) {
      this(param1Int, param1CharSequence, param1PendingIntent, new Bundle(), null, null, true, 0, true);
    }
    
    Action(int param1Int1, CharSequence param1CharSequence, PendingIntent param1PendingIntent, Bundle param1Bundle, RemoteInput[] param1ArrayOfRemoteInput1, RemoteInput[] param1ArrayOfRemoteInput2, boolean param1Boolean1, int param1Int2, boolean param1Boolean2) {
      this.icon = param1Int1;
      this.title = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      this.actionIntent = param1PendingIntent;
      if (param1Bundle == null)
        param1Bundle = new Bundle(); 
      this.mExtras = param1Bundle;
      this.mRemoteInputs = param1ArrayOfRemoteInput1;
      this.mDataOnlyRemoteInputs = param1ArrayOfRemoteInput2;
      this.mAllowGeneratedReplies = param1Boolean1;
      this.mSemanticAction = param1Int2;
      this.mShowsUserInterface = param1Boolean2;
    }
    
    public PendingIntent getActionIntent() {
      return this.actionIntent;
    }
    
    public boolean getAllowGeneratedReplies() {
      return this.mAllowGeneratedReplies;
    }
    
    public RemoteInput[] getDataOnlyRemoteInputs() {
      return this.mDataOnlyRemoteInputs;
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public int getIcon() {
      return this.icon;
    }
    
    public RemoteInput[] getRemoteInputs() {
      return this.mRemoteInputs;
    }
    
    public int getSemanticAction() {
      return this.mSemanticAction;
    }
    
    public boolean getShowsUserInterface() {
      return this.mShowsUserInterface;
    }
    
    public CharSequence getTitle() {
      return this.title;
    }
    
    public static final class Builder {
      private boolean mAllowGeneratedReplies;
      
      private final Bundle mExtras;
      
      private final int mIcon;
      
      private final PendingIntent mIntent;
      
      private ArrayList<RemoteInput> mRemoteInputs;
      
      private int mSemanticAction;
      
      private boolean mShowsUserInterface;
      
      private final CharSequence mTitle;
      
      public Builder(int param2Int, CharSequence param2CharSequence, PendingIntent param2PendingIntent) {
        this(param2Int, param2CharSequence, param2PendingIntent, new Bundle(), null, true, 0, true);
      }
      
      private Builder(int param2Int1, CharSequence param2CharSequence, PendingIntent param2PendingIntent, Bundle param2Bundle, RemoteInput[] param2ArrayOfRemoteInput, boolean param2Boolean1, int param2Int2, boolean param2Boolean2) {
        ArrayList<RemoteInput> arrayList;
        this.mAllowGeneratedReplies = true;
        this.mShowsUserInterface = true;
        this.mIcon = param2Int1;
        this.mTitle = NotificationCompat.Builder.limitCharSequenceLength(param2CharSequence);
        this.mIntent = param2PendingIntent;
        this.mExtras = param2Bundle;
        if (param2ArrayOfRemoteInput == null) {
          param2CharSequence = null;
        } else {
          arrayList = new ArrayList(Arrays.asList((Object[])param2ArrayOfRemoteInput));
        } 
        this.mRemoteInputs = arrayList;
        this.mAllowGeneratedReplies = param2Boolean1;
        this.mSemanticAction = param2Int2;
        this.mShowsUserInterface = param2Boolean2;
      }
      
      public Builder(NotificationCompat.Action param2Action) {
        this(param2Action.icon, param2Action.title, param2Action.actionIntent, new Bundle(param2Action.mExtras), param2Action.getRemoteInputs(), param2Action.getAllowGeneratedReplies(), param2Action.getSemanticAction(), param2Action.mShowsUserInterface);
      }
      
      public Builder addExtras(Bundle param2Bundle) {
        if (param2Bundle != null)
          this.mExtras.putAll(param2Bundle); 
        return this;
      }
      
      public Builder addRemoteInput(RemoteInput param2RemoteInput) {
        if (this.mRemoteInputs == null)
          this.mRemoteInputs = new ArrayList<RemoteInput>(); 
        this.mRemoteInputs.add(param2RemoteInput);
        return this;
      }
      
      public NotificationCompat.Action build() {
        RemoteInput[] arrayOfRemoteInput1;
        ArrayList<RemoteInput> arrayList1 = new ArrayList();
        ArrayList<RemoteInput> arrayList2 = new ArrayList();
        if (this.mRemoteInputs != null)
          for (RemoteInput remoteInput : this.mRemoteInputs) {
            if (remoteInput.isDataOnly()) {
              arrayList1.add(remoteInput);
              continue;
            } 
            arrayList2.add(remoteInput);
          }  
        if (arrayList1.isEmpty()) {
          arrayList1 = null;
        } else {
          arrayOfRemoteInput1 = arrayList1.<RemoteInput>toArray(new RemoteInput[arrayList1.size()]);
        } 
        if (arrayList2.isEmpty()) {
          arrayList2 = null;
          return new NotificationCompat.Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, (RemoteInput[])arrayList2, arrayOfRemoteInput1, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface);
        } 
        RemoteInput[] arrayOfRemoteInput2 = arrayList2.<RemoteInput>toArray(new RemoteInput[arrayList2.size()]);
        return new NotificationCompat.Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayOfRemoteInput2, arrayOfRemoteInput1, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface);
      }
      
      public Builder extend(NotificationCompat.Action.Extender param2Extender) {
        param2Extender.extend(this);
        return this;
      }
      
      public Bundle getExtras() {
        return this.mExtras;
      }
      
      public Builder setAllowGeneratedReplies(boolean param2Boolean) {
        this.mAllowGeneratedReplies = param2Boolean;
        return this;
      }
      
      public Builder setSemanticAction(int param2Int) {
        this.mSemanticAction = param2Int;
        return this;
      }
      
      public Builder setShowsUserInterface(boolean param2Boolean) {
        this.mShowsUserInterface = param2Boolean;
        return this;
      }
    }
    
    public static interface Extender {
      NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder param2Builder);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface SemanticAction {}
    
    public static final class WearableExtender implements Extender {
      private static final int DEFAULT_FLAGS = 1;
      
      private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
      
      private static final int FLAG_AVAILABLE_OFFLINE = 1;
      
      private static final int FLAG_HINT_DISPLAY_INLINE = 4;
      
      private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
      
      private static final String KEY_CANCEL_LABEL = "cancelLabel";
      
      private static final String KEY_CONFIRM_LABEL = "confirmLabel";
      
      private static final String KEY_FLAGS = "flags";
      
      private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
      
      private CharSequence mCancelLabel;
      
      private CharSequence mConfirmLabel;
      
      private int mFlags = 1;
      
      private CharSequence mInProgressLabel;
      
      public WearableExtender() {}
      
      public WearableExtender(NotificationCompat.Action param2Action) {
        Bundle bundle = param2Action.getExtras().getBundle("android.wearable.EXTENSIONS");
        if (bundle != null) {
          this.mFlags = bundle.getInt("flags", 1);
          this.mInProgressLabel = bundle.getCharSequence("inProgressLabel");
          this.mConfirmLabel = bundle.getCharSequence("confirmLabel");
          this.mCancelLabel = bundle.getCharSequence("cancelLabel");
        } 
      }
      
      private void setFlag(int param2Int, boolean param2Boolean) {
        if (param2Boolean) {
          this.mFlags |= param2Int;
          return;
        } 
        this.mFlags &= param2Int ^ 0xFFFFFFFF;
      }
      
      public WearableExtender clone() {
        WearableExtender wearableExtender = new WearableExtender();
        wearableExtender.mFlags = this.mFlags;
        wearableExtender.mInProgressLabel = this.mInProgressLabel;
        wearableExtender.mConfirmLabel = this.mConfirmLabel;
        wearableExtender.mCancelLabel = this.mCancelLabel;
        return wearableExtender;
      }
      
      public NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder param2Builder) {
        Bundle bundle = new Bundle();
        if (this.mFlags != 1)
          bundle.putInt("flags", this.mFlags); 
        if (this.mInProgressLabel != null)
          bundle.putCharSequence("inProgressLabel", this.mInProgressLabel); 
        if (this.mConfirmLabel != null)
          bundle.putCharSequence("confirmLabel", this.mConfirmLabel); 
        if (this.mCancelLabel != null)
          bundle.putCharSequence("cancelLabel", this.mCancelLabel); 
        param2Builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
        return param2Builder;
      }
      
      @Deprecated
      public CharSequence getCancelLabel() {
        return this.mCancelLabel;
      }
      
      @Deprecated
      public CharSequence getConfirmLabel() {
        return this.mConfirmLabel;
      }
      
      public boolean getHintDisplayActionInline() {
        return ((this.mFlags & 0x4) != 0);
      }
      
      public boolean getHintLaunchesActivity() {
        return ((this.mFlags & 0x2) != 0);
      }
      
      @Deprecated
      public CharSequence getInProgressLabel() {
        return this.mInProgressLabel;
      }
      
      public boolean isAvailableOffline() {
        return ((this.mFlags & 0x1) != 0);
      }
      
      public WearableExtender setAvailableOffline(boolean param2Boolean) {
        setFlag(1, param2Boolean);
        return this;
      }
      
      @Deprecated
      public WearableExtender setCancelLabel(CharSequence param2CharSequence) {
        this.mCancelLabel = param2CharSequence;
        return this;
      }
      
      @Deprecated
      public WearableExtender setConfirmLabel(CharSequence param2CharSequence) {
        this.mConfirmLabel = param2CharSequence;
        return this;
      }
      
      public WearableExtender setHintDisplayActionInline(boolean param2Boolean) {
        setFlag(4, param2Boolean);
        return this;
      }
      
      public WearableExtender setHintLaunchesActivity(boolean param2Boolean) {
        setFlag(2, param2Boolean);
        return this;
      }
      
      @Deprecated
      public WearableExtender setInProgressLabel(CharSequence param2CharSequence) {
        this.mInProgressLabel = param2CharSequence;
        return this;
      }
    }
  }
  
  public static final class Builder {
    private boolean mAllowGeneratedReplies;
    
    private final Bundle mExtras;
    
    private final int mIcon;
    
    private final PendingIntent mIntent;
    
    private ArrayList<RemoteInput> mRemoteInputs;
    
    private int mSemanticAction;
    
    private boolean mShowsUserInterface;
    
    private final CharSequence mTitle;
    
    public Builder(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent) {
      this(param1Int, param1CharSequence, param1PendingIntent, new Bundle(), null, true, 0, true);
    }
    
    private Builder(int param1Int1, CharSequence param1CharSequence, PendingIntent param1PendingIntent, Bundle param1Bundle, RemoteInput[] param1ArrayOfRemoteInput, boolean param1Boolean1, int param1Int2, boolean param1Boolean2) {
      ArrayList<RemoteInput> arrayList;
      this.mAllowGeneratedReplies = true;
      this.mShowsUserInterface = true;
      this.mIcon = param1Int1;
      this.mTitle = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      this.mIntent = param1PendingIntent;
      this.mExtras = param1Bundle;
      if (param1ArrayOfRemoteInput == null) {
        param1CharSequence = null;
      } else {
        arrayList = new ArrayList(Arrays.asList((Object[])param1ArrayOfRemoteInput));
      } 
      this.mRemoteInputs = arrayList;
      this.mAllowGeneratedReplies = param1Boolean1;
      this.mSemanticAction = param1Int2;
      this.mShowsUserInterface = param1Boolean2;
    }
    
    public Builder(NotificationCompat.Action param1Action) {
      this(param1Action.icon, param1Action.title, param1Action.actionIntent, new Bundle(param1Action.mExtras), param1Action.getRemoteInputs(), param1Action.getAllowGeneratedReplies(), param1Action.getSemanticAction(), param1Action.mShowsUserInterface);
    }
    
    public Builder addExtras(Bundle param1Bundle) {
      if (param1Bundle != null)
        this.mExtras.putAll(param1Bundle); 
      return this;
    }
    
    public Builder addRemoteInput(RemoteInput param1RemoteInput) {
      if (this.mRemoteInputs == null)
        this.mRemoteInputs = new ArrayList<RemoteInput>(); 
      this.mRemoteInputs.add(param1RemoteInput);
      return this;
    }
    
    public NotificationCompat.Action build() {
      RemoteInput[] arrayOfRemoteInput1;
      ArrayList<RemoteInput> arrayList1 = new ArrayList();
      ArrayList<RemoteInput> arrayList2 = new ArrayList();
      if (this.mRemoteInputs != null)
        for (RemoteInput remoteInput : this.mRemoteInputs) {
          if (remoteInput.isDataOnly()) {
            arrayList1.add(remoteInput);
            continue;
          } 
          arrayList2.add(remoteInput);
        }  
      if (arrayList1.isEmpty()) {
        arrayList1 = null;
      } else {
        arrayOfRemoteInput1 = arrayList1.<RemoteInput>toArray(new RemoteInput[arrayList1.size()]);
      } 
      if (arrayList2.isEmpty()) {
        arrayList2 = null;
        return new NotificationCompat.Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, (RemoteInput[])arrayList2, arrayOfRemoteInput1, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface);
      } 
      RemoteInput[] arrayOfRemoteInput2 = arrayList2.<RemoteInput>toArray(new RemoteInput[arrayList2.size()]);
      return new NotificationCompat.Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayOfRemoteInput2, arrayOfRemoteInput1, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface);
    }
    
    public Builder extend(NotificationCompat.Action.Extender param1Extender) {
      param1Extender.extend(this);
      return this;
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public Builder setAllowGeneratedReplies(boolean param1Boolean) {
      this.mAllowGeneratedReplies = param1Boolean;
      return this;
    }
    
    public Builder setSemanticAction(int param1Int) {
      this.mSemanticAction = param1Int;
      return this;
    }
    
    public Builder setShowsUserInterface(boolean param1Boolean) {
      this.mShowsUserInterface = param1Boolean;
      return this;
    }
  }
  
  public static interface Extender {
    NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder param1Builder);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SemanticAction {}
  
  public static final class WearableExtender implements Action.Extender {
    private static final int DEFAULT_FLAGS = 1;
    
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    
    private static final int FLAG_AVAILABLE_OFFLINE = 1;
    
    private static final int FLAG_HINT_DISPLAY_INLINE = 4;
    
    private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
    
    private static final String KEY_CANCEL_LABEL = "cancelLabel";
    
    private static final String KEY_CONFIRM_LABEL = "confirmLabel";
    
    private static final String KEY_FLAGS = "flags";
    
    private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
    
    private CharSequence mCancelLabel;
    
    private CharSequence mConfirmLabel;
    
    private int mFlags = 1;
    
    private CharSequence mInProgressLabel;
    
    public WearableExtender() {}
    
    public WearableExtender(NotificationCompat.Action param1Action) {
      Bundle bundle = param1Action.getExtras().getBundle("android.wearable.EXTENSIONS");
      if (bundle != null) {
        this.mFlags = bundle.getInt("flags", 1);
        this.mInProgressLabel = bundle.getCharSequence("inProgressLabel");
        this.mConfirmLabel = bundle.getCharSequence("confirmLabel");
        this.mCancelLabel = bundle.getCharSequence("cancelLabel");
      } 
    }
    
    private void setFlag(int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        this.mFlags |= param1Int;
        return;
      } 
      this.mFlags &= param1Int ^ 0xFFFFFFFF;
    }
    
    public WearableExtender clone() {
      WearableExtender wearableExtender = new WearableExtender();
      wearableExtender.mFlags = this.mFlags;
      wearableExtender.mInProgressLabel = this.mInProgressLabel;
      wearableExtender.mConfirmLabel = this.mConfirmLabel;
      wearableExtender.mCancelLabel = this.mCancelLabel;
      return wearableExtender;
    }
    
    public NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder param1Builder) {
      Bundle bundle = new Bundle();
      if (this.mFlags != 1)
        bundle.putInt("flags", this.mFlags); 
      if (this.mInProgressLabel != null)
        bundle.putCharSequence("inProgressLabel", this.mInProgressLabel); 
      if (this.mConfirmLabel != null)
        bundle.putCharSequence("confirmLabel", this.mConfirmLabel); 
      if (this.mCancelLabel != null)
        bundle.putCharSequence("cancelLabel", this.mCancelLabel); 
      param1Builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
      return param1Builder;
    }
    
    @Deprecated
    public CharSequence getCancelLabel() {
      return this.mCancelLabel;
    }
    
    @Deprecated
    public CharSequence getConfirmLabel() {
      return this.mConfirmLabel;
    }
    
    public boolean getHintDisplayActionInline() {
      return ((this.mFlags & 0x4) != 0);
    }
    
    public boolean getHintLaunchesActivity() {
      return ((this.mFlags & 0x2) != 0);
    }
    
    @Deprecated
    public CharSequence getInProgressLabel() {
      return this.mInProgressLabel;
    }
    
    public boolean isAvailableOffline() {
      return ((this.mFlags & 0x1) != 0);
    }
    
    public WearableExtender setAvailableOffline(boolean param1Boolean) {
      setFlag(1, param1Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setCancelLabel(CharSequence param1CharSequence) {
      this.mCancelLabel = param1CharSequence;
      return this;
    }
    
    @Deprecated
    public WearableExtender setConfirmLabel(CharSequence param1CharSequence) {
      this.mConfirmLabel = param1CharSequence;
      return this;
    }
    
    public WearableExtender setHintDisplayActionInline(boolean param1Boolean) {
      setFlag(4, param1Boolean);
      return this;
    }
    
    public WearableExtender setHintLaunchesActivity(boolean param1Boolean) {
      setFlag(2, param1Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setInProgressLabel(CharSequence param1CharSequence) {
      this.mInProgressLabel = param1CharSequence;
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface BadgeIconType {}
  
  public static class BigPictureStyle extends Style {
    private Bitmap mBigLargeIcon;
    
    private boolean mBigLargeIconSet;
    
    private Bitmap mPicture;
    
    public BigPictureStyle() {}
    
    public BigPictureStyle(NotificationCompat.Builder param1Builder) {
      setBuilder(param1Builder);
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      if (Build.VERSION.SDK_INT >= 16) {
        Notification.BigPictureStyle bigPictureStyle = (new Notification.BigPictureStyle(param1NotificationBuilderWithBuilderAccessor.getBuilder())).setBigContentTitle(this.mBigContentTitle).bigPicture(this.mPicture);
        if (this.mBigLargeIconSet)
          bigPictureStyle.bigLargeIcon(this.mBigLargeIcon); 
        if (this.mSummaryTextSet)
          bigPictureStyle.setSummaryText(this.mSummaryText); 
      } 
    }
    
    public BigPictureStyle bigLargeIcon(Bitmap param1Bitmap) {
      this.mBigLargeIcon = param1Bitmap;
      this.mBigLargeIconSet = true;
      return this;
    }
    
    public BigPictureStyle bigPicture(Bitmap param1Bitmap) {
      this.mPicture = param1Bitmap;
      return this;
    }
    
    public BigPictureStyle setBigContentTitle(CharSequence param1CharSequence) {
      this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public BigPictureStyle setSummaryText(CharSequence param1CharSequence) {
      this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      this.mSummaryTextSet = true;
      return this;
    }
  }
  
  public static class BigTextStyle extends Style {
    private CharSequence mBigText;
    
    public BigTextStyle() {}
    
    public BigTextStyle(NotificationCompat.Builder param1Builder) {
      setBuilder(param1Builder);
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      if (Build.VERSION.SDK_INT >= 16) {
        Notification.BigTextStyle bigTextStyle = (new Notification.BigTextStyle(param1NotificationBuilderWithBuilderAccessor.getBuilder())).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
        if (this.mSummaryTextSet)
          bigTextStyle.setSummaryText(this.mSummaryText); 
      } 
    }
    
    public BigTextStyle bigText(CharSequence param1CharSequence) {
      this.mBigText = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public BigTextStyle setBigContentTitle(CharSequence param1CharSequence) {
      this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public BigTextStyle setSummaryText(CharSequence param1CharSequence) {
      this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      this.mSummaryTextSet = true;
      return this;
    }
  }
  
  public static class Builder {
    private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ArrayList<NotificationCompat.Action> mActions = new ArrayList<NotificationCompat.Action>();
    
    int mBadgeIcon = 0;
    
    RemoteViews mBigContentView;
    
    String mCategory;
    
    String mChannelId;
    
    int mColor = 0;
    
    boolean mColorized;
    
    boolean mColorizedSet;
    
    CharSequence mContentInfo;
    
    PendingIntent mContentIntent;
    
    CharSequence mContentText;
    
    CharSequence mContentTitle;
    
    RemoteViews mContentView;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Context mContext;
    
    Bundle mExtras;
    
    PendingIntent mFullScreenIntent;
    
    int mGroupAlertBehavior = 0;
    
    String mGroupKey;
    
    boolean mGroupSummary;
    
    RemoteViews mHeadsUpContentView;
    
    ArrayList<NotificationCompat.Action> mInvisibleActions = new ArrayList<NotificationCompat.Action>();
    
    Bitmap mLargeIcon;
    
    boolean mLocalOnly = false;
    
    Notification mNotification = new Notification();
    
    int mNumber;
    
    @Deprecated
    public ArrayList<String> mPeople;
    
    int mPriority;
    
    int mProgress;
    
    boolean mProgressIndeterminate;
    
    int mProgressMax;
    
    Notification mPublicVersion;
    
    CharSequence[] mRemoteInputHistory;
    
    String mShortcutId;
    
    boolean mShowWhen = true;
    
    String mSortKey;
    
    NotificationCompat.Style mStyle;
    
    CharSequence mSubText;
    
    RemoteViews mTickerView;
    
    long mTimeout;
    
    boolean mUseChronometer;
    
    int mVisibility = 0;
    
    @Deprecated
    public Builder(Context param1Context) {
      this(param1Context, null);
    }
    
    public Builder(@NonNull Context param1Context, @NonNull String param1String) {
      this.mContext = param1Context;
      this.mChannelId = param1String;
      this.mNotification.when = System.currentTimeMillis();
      this.mNotification.audioStreamType = -1;
      this.mPriority = 0;
      this.mPeople = new ArrayList<String>();
    }
    
    protected static CharSequence limitCharSequenceLength(CharSequence param1CharSequence) {
      return (param1CharSequence != null && param1CharSequence.length() > 5120) ? param1CharSequence.subSequence(0, 5120) : param1CharSequence;
    }
    
    private Bitmap reduceLargeIconSize(Bitmap param1Bitmap) {
      if (param1Bitmap != null && Build.VERSION.SDK_INT < 27) {
        Resources resources = this.mContext.getResources();
        int i = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_width);
        int j = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_height);
        if (param1Bitmap.getWidth() > i || param1Bitmap.getHeight() > j) {
          double d = Math.min(i / Math.max(1, param1Bitmap.getWidth()), j / Math.max(1, param1Bitmap.getHeight()));
          return Bitmap.createScaledBitmap(param1Bitmap, (int)Math.ceil(param1Bitmap.getWidth() * d), (int)Math.ceil(param1Bitmap.getHeight() * d), true);
        } 
      } 
      return param1Bitmap;
    }
    
    private void setFlag(int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        Notification notification1 = this.mNotification;
        notification1.flags |= param1Int;
        return;
      } 
      Notification notification = this.mNotification;
      notification.flags &= param1Int ^ 0xFFFFFFFF;
    }
    
    public Builder addAction(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent) {
      this.mActions.add(new NotificationCompat.Action(param1Int, param1CharSequence, param1PendingIntent));
      return this;
    }
    
    public Builder addAction(NotificationCompat.Action param1Action) {
      this.mActions.add(param1Action);
      return this;
    }
    
    public Builder addExtras(Bundle param1Bundle) {
      if (param1Bundle != null) {
        if (this.mExtras == null) {
          this.mExtras = new Bundle(param1Bundle);
          return this;
        } 
      } else {
        return this;
      } 
      this.mExtras.putAll(param1Bundle);
      return this;
    }
    
    @RequiresApi(21)
    public Builder addInvisibleAction(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent) {
      return addInvisibleAction(new NotificationCompat.Action(param1Int, param1CharSequence, param1PendingIntent));
    }
    
    @RequiresApi(21)
    public Builder addInvisibleAction(NotificationCompat.Action param1Action) {
      this.mInvisibleActions.add(param1Action);
      return this;
    }
    
    public Builder addPerson(String param1String) {
      this.mPeople.add(param1String);
      return this;
    }
    
    public Notification build() {
      return (new NotificationCompatBuilder(this)).build();
    }
    
    public Builder extend(NotificationCompat.Extender param1Extender) {
      param1Extender.extend(this);
      return this;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews getBigContentView() {
      return this.mBigContentView;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getColor() {
      return this.mColor;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews getContentView() {
      return this.mContentView;
    }
    
    public Bundle getExtras() {
      if (this.mExtras == null)
        this.mExtras = new Bundle(); 
      return this.mExtras;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews getHeadsUpContentView() {
      return this.mHeadsUpContentView;
    }
    
    @Deprecated
    public Notification getNotification() {
      return build();
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getPriority() {
      return this.mPriority;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public long getWhenIfShowing() {
      return this.mShowWhen ? this.mNotification.when : 0L;
    }
    
    public Builder setAutoCancel(boolean param1Boolean) {
      setFlag(16, param1Boolean);
      return this;
    }
    
    public Builder setBadgeIconType(int param1Int) {
      this.mBadgeIcon = param1Int;
      return this;
    }
    
    public Builder setCategory(String param1String) {
      this.mCategory = param1String;
      return this;
    }
    
    public Builder setChannelId(@NonNull String param1String) {
      this.mChannelId = param1String;
      return this;
    }
    
    public Builder setColor(@ColorInt int param1Int) {
      this.mColor = param1Int;
      return this;
    }
    
    public Builder setColorized(boolean param1Boolean) {
      this.mColorized = param1Boolean;
      this.mColorizedSet = true;
      return this;
    }
    
    public Builder setContent(RemoteViews param1RemoteViews) {
      this.mNotification.contentView = param1RemoteViews;
      return this;
    }
    
    public Builder setContentInfo(CharSequence param1CharSequence) {
      this.mContentInfo = limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public Builder setContentIntent(PendingIntent param1PendingIntent) {
      this.mContentIntent = param1PendingIntent;
      return this;
    }
    
    public Builder setContentText(CharSequence param1CharSequence) {
      this.mContentText = limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public Builder setContentTitle(CharSequence param1CharSequence) {
      this.mContentTitle = limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public Builder setCustomBigContentView(RemoteViews param1RemoteViews) {
      this.mBigContentView = param1RemoteViews;
      return this;
    }
    
    public Builder setCustomContentView(RemoteViews param1RemoteViews) {
      this.mContentView = param1RemoteViews;
      return this;
    }
    
    public Builder setCustomHeadsUpContentView(RemoteViews param1RemoteViews) {
      this.mHeadsUpContentView = param1RemoteViews;
      return this;
    }
    
    public Builder setDefaults(int param1Int) {
      this.mNotification.defaults = param1Int;
      if ((param1Int & 0x4) != 0) {
        Notification notification = this.mNotification;
        notification.flags |= 0x1;
      } 
      return this;
    }
    
    public Builder setDeleteIntent(PendingIntent param1PendingIntent) {
      this.mNotification.deleteIntent = param1PendingIntent;
      return this;
    }
    
    public Builder setExtras(Bundle param1Bundle) {
      this.mExtras = param1Bundle;
      return this;
    }
    
    public Builder setFullScreenIntent(PendingIntent param1PendingIntent, boolean param1Boolean) {
      this.mFullScreenIntent = param1PendingIntent;
      setFlag(128, param1Boolean);
      return this;
    }
    
    public Builder setGroup(String param1String) {
      this.mGroupKey = param1String;
      return this;
    }
    
    public Builder setGroupAlertBehavior(int param1Int) {
      this.mGroupAlertBehavior = param1Int;
      return this;
    }
    
    public Builder setGroupSummary(boolean param1Boolean) {
      this.mGroupSummary = param1Boolean;
      return this;
    }
    
    public Builder setLargeIcon(Bitmap param1Bitmap) {
      this.mLargeIcon = reduceLargeIconSize(param1Bitmap);
      return this;
    }
    
    public Builder setLights(@ColorInt int param1Int1, int param1Int2, int param1Int3) {
      boolean bool = true;
      this.mNotification.ledARGB = param1Int1;
      this.mNotification.ledOnMS = param1Int2;
      this.mNotification.ledOffMS = param1Int3;
      if (this.mNotification.ledOnMS != 0 && this.mNotification.ledOffMS != 0) {
        param1Int1 = 1;
      } else {
        param1Int1 = 0;
      } 
      Notification notification = this.mNotification;
      param1Int2 = this.mNotification.flags;
      if (param1Int1 != 0) {
        param1Int1 = bool;
        notification.flags = param1Int1 | param1Int2 & 0xFFFFFFFE;
        return this;
      } 
      param1Int1 = 0;
      notification.flags = param1Int1 | param1Int2 & 0xFFFFFFFE;
      return this;
    }
    
    public Builder setLocalOnly(boolean param1Boolean) {
      this.mLocalOnly = param1Boolean;
      return this;
    }
    
    public Builder setNumber(int param1Int) {
      this.mNumber = param1Int;
      return this;
    }
    
    public Builder setOngoing(boolean param1Boolean) {
      setFlag(2, param1Boolean);
      return this;
    }
    
    public Builder setOnlyAlertOnce(boolean param1Boolean) {
      setFlag(8, param1Boolean);
      return this;
    }
    
    public Builder setPriority(int param1Int) {
      this.mPriority = param1Int;
      return this;
    }
    
    public Builder setProgress(int param1Int1, int param1Int2, boolean param1Boolean) {
      this.mProgressMax = param1Int1;
      this.mProgress = param1Int2;
      this.mProgressIndeterminate = param1Boolean;
      return this;
    }
    
    public Builder setPublicVersion(Notification param1Notification) {
      this.mPublicVersion = param1Notification;
      return this;
    }
    
    public Builder setRemoteInputHistory(CharSequence[] param1ArrayOfCharSequence) {
      this.mRemoteInputHistory = param1ArrayOfCharSequence;
      return this;
    }
    
    public Builder setShortcutId(String param1String) {
      this.mShortcutId = param1String;
      return this;
    }
    
    public Builder setShowWhen(boolean param1Boolean) {
      this.mShowWhen = param1Boolean;
      return this;
    }
    
    public Builder setSmallIcon(int param1Int) {
      this.mNotification.icon = param1Int;
      return this;
    }
    
    public Builder setSmallIcon(int param1Int1, int param1Int2) {
      this.mNotification.icon = param1Int1;
      this.mNotification.iconLevel = param1Int2;
      return this;
    }
    
    public Builder setSortKey(String param1String) {
      this.mSortKey = param1String;
      return this;
    }
    
    public Builder setSound(Uri param1Uri) {
      this.mNotification.sound = param1Uri;
      this.mNotification.audioStreamType = -1;
      if (Build.VERSION.SDK_INT >= 21)
        this.mNotification.audioAttributes = (new AudioAttributes.Builder()).setContentType(4).setUsage(5).build(); 
      return this;
    }
    
    public Builder setSound(Uri param1Uri, int param1Int) {
      this.mNotification.sound = param1Uri;
      this.mNotification.audioStreamType = param1Int;
      if (Build.VERSION.SDK_INT >= 21)
        this.mNotification.audioAttributes = (new AudioAttributes.Builder()).setContentType(4).setLegacyStreamType(param1Int).build(); 
      return this;
    }
    
    public Builder setStyle(NotificationCompat.Style param1Style) {
      if (this.mStyle != param1Style) {
        this.mStyle = param1Style;
        if (this.mStyle != null)
          this.mStyle.setBuilder(this); 
      } 
      return this;
    }
    
    public Builder setSubText(CharSequence param1CharSequence) {
      this.mSubText = limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public Builder setTicker(CharSequence param1CharSequence) {
      this.mNotification.tickerText = limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public Builder setTicker(CharSequence param1CharSequence, RemoteViews param1RemoteViews) {
      this.mNotification.tickerText = limitCharSequenceLength(param1CharSequence);
      this.mTickerView = param1RemoteViews;
      return this;
    }
    
    public Builder setTimeoutAfter(long param1Long) {
      this.mTimeout = param1Long;
      return this;
    }
    
    public Builder setUsesChronometer(boolean param1Boolean) {
      this.mUseChronometer = param1Boolean;
      return this;
    }
    
    public Builder setVibrate(long[] param1ArrayOflong) {
      this.mNotification.vibrate = param1ArrayOflong;
      return this;
    }
    
    public Builder setVisibility(int param1Int) {
      this.mVisibility = param1Int;
      return this;
    }
    
    public Builder setWhen(long param1Long) {
      this.mNotification.when = param1Long;
      return this;
    }
  }
  
  public static final class CarExtender implements Extender {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
    
    private static final String EXTRA_COLOR = "app_color";
    
    private static final String EXTRA_CONVERSATION = "car_conversation";
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
    
    private static final String EXTRA_LARGE_ICON = "large_icon";
    
    private static final String KEY_AUTHOR = "author";
    
    private static final String KEY_MESSAGES = "messages";
    
    private static final String KEY_ON_READ = "on_read";
    
    private static final String KEY_ON_REPLY = "on_reply";
    
    private static final String KEY_PARTICIPANTS = "participants";
    
    private static final String KEY_REMOTE_INPUT = "remote_input";
    
    private static final String KEY_TEXT = "text";
    
    private static final String KEY_TIMESTAMP = "timestamp";
    
    private int mColor = 0;
    
    private Bitmap mLargeIcon;
    
    private UnreadConversation mUnreadConversation;
    
    public CarExtender() {}
    
    public CarExtender(Notification param1Notification) {
      if (Build.VERSION.SDK_INT >= 21) {
        Bundle bundle;
        if (NotificationCompat.getExtras(param1Notification) == null) {
          param1Notification = null;
        } else {
          bundle = NotificationCompat.getExtras(param1Notification).getBundle("android.car.EXTENSIONS");
        } 
        if (bundle != null) {
          this.mLargeIcon = (Bitmap)bundle.getParcelable("large_icon");
          this.mColor = bundle.getInt("app_color", 0);
          this.mUnreadConversation = getUnreadConversationFromBundle(bundle.getBundle("car_conversation"));
          return;
        } 
      } 
    }
    
    @RequiresApi(21)
    private static Bundle getBundleForUnreadConversation(@NonNull UnreadConversation param1UnreadConversation) {
      Bundle bundle = new Bundle();
      String str2 = null;
      String str1 = str2;
      if (param1UnreadConversation.getParticipants() != null) {
        str1 = str2;
        if ((param1UnreadConversation.getParticipants()).length > 1)
          str1 = param1UnreadConversation.getParticipants()[0]; 
      } 
      Parcelable[] arrayOfParcelable = new Parcelable[(param1UnreadConversation.getMessages()).length];
      for (int i = 0; i < arrayOfParcelable.length; i++) {
        Bundle bundle1 = new Bundle();
        bundle1.putString("text", param1UnreadConversation.getMessages()[i]);
        bundle1.putString("author", str1);
        arrayOfParcelable[i] = (Parcelable)bundle1;
      } 
      bundle.putParcelableArray("messages", arrayOfParcelable);
      RemoteInput remoteInput = param1UnreadConversation.getRemoteInput();
      if (remoteInput != null)
        bundle.putParcelable("remote_input", (Parcelable)(new RemoteInput.Builder(remoteInput.getResultKey())).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build()); 
      bundle.putParcelable("on_reply", (Parcelable)param1UnreadConversation.getReplyPendingIntent());
      bundle.putParcelable("on_read", (Parcelable)param1UnreadConversation.getReadPendingIntent());
      bundle.putStringArray("participants", param1UnreadConversation.getParticipants());
      bundle.putLong("timestamp", param1UnreadConversation.getLatestTimestamp());
      return bundle;
    }
    
    @RequiresApi(21)
    private static UnreadConversation getUnreadConversationFromBundle(@Nullable Bundle param1Bundle) {
      // Byte code:
      //   0: aload_0
      //   1: ifnonnull -> 6
      //   4: aconst_null
      //   5: areturn
      //   6: aload_0
      //   7: ldc 'messages'
      //   9: invokevirtual getParcelableArray : (Ljava/lang/String;)[Landroid/os/Parcelable;
      //   12: astore #5
      //   14: aconst_null
      //   15: astore #4
      //   17: aload #5
      //   19: ifnull -> 59
      //   22: aload #5
      //   24: arraylength
      //   25: anewarray java/lang/String
      //   28: astore #4
      //   30: iconst_1
      //   31: istore_3
      //   32: iconst_0
      //   33: istore_2
      //   34: iload_3
      //   35: istore_1
      //   36: iload_2
      //   37: aload #4
      //   39: arraylength
      //   40: if_icmpge -> 55
      //   43: aload #5
      //   45: iload_2
      //   46: aaload
      //   47: instanceof android/os/Bundle
      //   50: ifne -> 114
      //   53: iconst_0
      //   54: istore_1
      //   55: iload_1
      //   56: ifeq -> 149
      //   59: aload_0
      //   60: ldc 'on_read'
      //   62: invokevirtual getParcelable : (Ljava/lang/String;)Landroid/os/Parcelable;
      //   65: checkcast android/app/PendingIntent
      //   68: astore #6
      //   70: aload_0
      //   71: ldc 'on_reply'
      //   73: invokevirtual getParcelable : (Ljava/lang/String;)Landroid/os/Parcelable;
      //   76: checkcast android/app/PendingIntent
      //   79: astore #7
      //   81: aload_0
      //   82: ldc 'remote_input'
      //   84: invokevirtual getParcelable : (Ljava/lang/String;)Landroid/os/Parcelable;
      //   87: checkcast android/app/RemoteInput
      //   90: astore #5
      //   92: aload_0
      //   93: ldc 'participants'
      //   95: invokevirtual getStringArray : (Ljava/lang/String;)[Ljava/lang/String;
      //   98: astore #8
      //   100: aload #8
      //   102: ifnull -> 112
      //   105: aload #8
      //   107: arraylength
      //   108: iconst_1
      //   109: if_icmpeq -> 151
      //   112: aconst_null
      //   113: areturn
      //   114: aload #4
      //   116: iload_2
      //   117: aload #5
      //   119: iload_2
      //   120: aaload
      //   121: checkcast android/os/Bundle
      //   124: ldc 'text'
      //   126: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
      //   129: aastore
      //   130: aload #4
      //   132: iload_2
      //   133: aaload
      //   134: ifnonnull -> 142
      //   137: iconst_0
      //   138: istore_1
      //   139: goto -> 55
      //   142: iload_2
      //   143: iconst_1
      //   144: iadd
      //   145: istore_2
      //   146: goto -> 34
      //   149: aconst_null
      //   150: areturn
      //   151: aload #5
      //   153: ifnull -> 215
      //   156: new androidx/core/app/RemoteInput
      //   159: dup
      //   160: aload #5
      //   162: invokevirtual getResultKey : ()Ljava/lang/String;
      //   165: aload #5
      //   167: invokevirtual getLabel : ()Ljava/lang/CharSequence;
      //   170: aload #5
      //   172: invokevirtual getChoices : ()[Ljava/lang/CharSequence;
      //   175: aload #5
      //   177: invokevirtual getAllowFreeFormInput : ()Z
      //   180: aload #5
      //   182: invokevirtual getExtras : ()Landroid/os/Bundle;
      //   185: aconst_null
      //   186: invokespecial <init> : (Ljava/lang/String;Ljava/lang/CharSequence;[Ljava/lang/CharSequence;ZLandroid/os/Bundle;Ljava/util/Set;)V
      //   189: astore #5
      //   191: new androidx/core/app/NotificationCompat$CarExtender$UnreadConversation
      //   194: dup
      //   195: aload #4
      //   197: aload #5
      //   199: aload #7
      //   201: aload #6
      //   203: aload #8
      //   205: aload_0
      //   206: ldc 'timestamp'
      //   208: invokevirtual getLong : (Ljava/lang/String;)J
      //   211: invokespecial <init> : ([Ljava/lang/String;Landroidx/core/app/RemoteInput;Landroid/app/PendingIntent;Landroid/app/PendingIntent;[Ljava/lang/String;J)V
      //   214: areturn
      //   215: aconst_null
      //   216: astore #5
      //   218: goto -> 191
    }
    
    public NotificationCompat.Builder extend(NotificationCompat.Builder param1Builder) {
      if (Build.VERSION.SDK_INT < 21)
        return param1Builder; 
      Bundle bundle = new Bundle();
      if (this.mLargeIcon != null)
        bundle.putParcelable("large_icon", (Parcelable)this.mLargeIcon); 
      if (this.mColor != 0)
        bundle.putInt("app_color", this.mColor); 
      if (this.mUnreadConversation != null)
        bundle.putBundle("car_conversation", getBundleForUnreadConversation(this.mUnreadConversation)); 
      param1Builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
      return param1Builder;
    }
    
    @ColorInt
    public int getColor() {
      return this.mColor;
    }
    
    public Bitmap getLargeIcon() {
      return this.mLargeIcon;
    }
    
    public UnreadConversation getUnreadConversation() {
      return this.mUnreadConversation;
    }
    
    public CarExtender setColor(@ColorInt int param1Int) {
      this.mColor = param1Int;
      return this;
    }
    
    public CarExtender setLargeIcon(Bitmap param1Bitmap) {
      this.mLargeIcon = param1Bitmap;
      return this;
    }
    
    public CarExtender setUnreadConversation(UnreadConversation param1UnreadConversation) {
      this.mUnreadConversation = param1UnreadConversation;
      return this;
    }
    
    public static class UnreadConversation {
      private final long mLatestTimestamp;
      
      private final String[] mMessages;
      
      private final String[] mParticipants;
      
      private final PendingIntent mReadPendingIntent;
      
      private final RemoteInput mRemoteInput;
      
      private final PendingIntent mReplyPendingIntent;
      
      UnreadConversation(String[] param2ArrayOfString1, RemoteInput param2RemoteInput, PendingIntent param2PendingIntent1, PendingIntent param2PendingIntent2, String[] param2ArrayOfString2, long param2Long) {
        this.mMessages = param2ArrayOfString1;
        this.mRemoteInput = param2RemoteInput;
        this.mReadPendingIntent = param2PendingIntent2;
        this.mReplyPendingIntent = param2PendingIntent1;
        this.mParticipants = param2ArrayOfString2;
        this.mLatestTimestamp = param2Long;
      }
      
      public long getLatestTimestamp() {
        return this.mLatestTimestamp;
      }
      
      public String[] getMessages() {
        return this.mMessages;
      }
      
      public String getParticipant() {
        return (this.mParticipants.length > 0) ? this.mParticipants[0] : null;
      }
      
      public String[] getParticipants() {
        return this.mParticipants;
      }
      
      public PendingIntent getReadPendingIntent() {
        return this.mReadPendingIntent;
      }
      
      public RemoteInput getRemoteInput() {
        return this.mRemoteInput;
      }
      
      public PendingIntent getReplyPendingIntent() {
        return this.mReplyPendingIntent;
      }
      
      public static class Builder {
        private long mLatestTimestamp;
        
        private final List<String> mMessages = new ArrayList<String>();
        
        private final String mParticipant;
        
        private PendingIntent mReadPendingIntent;
        
        private RemoteInput mRemoteInput;
        
        private PendingIntent mReplyPendingIntent;
        
        public Builder(String param3String) {
          this.mParticipant = param3String;
        }
        
        public Builder addMessage(String param3String) {
          this.mMessages.add(param3String);
          return this;
        }
        
        public NotificationCompat.CarExtender.UnreadConversation build() {
          String[] arrayOfString = this.mMessages.<String>toArray(new String[this.mMessages.size()]);
          String str = this.mParticipant;
          RemoteInput remoteInput = this.mRemoteInput;
          PendingIntent pendingIntent1 = this.mReplyPendingIntent;
          PendingIntent pendingIntent2 = this.mReadPendingIntent;
          long l = this.mLatestTimestamp;
          return new NotificationCompat.CarExtender.UnreadConversation(arrayOfString, remoteInput, pendingIntent1, pendingIntent2, new String[] { str }, l);
        }
        
        public Builder setLatestTimestamp(long param3Long) {
          this.mLatestTimestamp = param3Long;
          return this;
        }
        
        public Builder setReadPendingIntent(PendingIntent param3PendingIntent) {
          this.mReadPendingIntent = param3PendingIntent;
          return this;
        }
        
        public Builder setReplyAction(PendingIntent param3PendingIntent, RemoteInput param3RemoteInput) {
          this.mRemoteInput = param3RemoteInput;
          this.mReplyPendingIntent = param3PendingIntent;
          return this;
        }
      }
    }
    
    public static class Builder {
      private long mLatestTimestamp;
      
      private final List<String> mMessages = new ArrayList<String>();
      
      private final String mParticipant;
      
      private PendingIntent mReadPendingIntent;
      
      private RemoteInput mRemoteInput;
      
      private PendingIntent mReplyPendingIntent;
      
      public Builder(String param2String) {
        this.mParticipant = param2String;
      }
      
      public Builder addMessage(String param2String) {
        this.mMessages.add(param2String);
        return this;
      }
      
      public NotificationCompat.CarExtender.UnreadConversation build() {
        String[] arrayOfString = this.mMessages.<String>toArray(new String[this.mMessages.size()]);
        String str = this.mParticipant;
        RemoteInput remoteInput = this.mRemoteInput;
        PendingIntent pendingIntent1 = this.mReplyPendingIntent;
        PendingIntent pendingIntent2 = this.mReadPendingIntent;
        long l = this.mLatestTimestamp;
        return new NotificationCompat.CarExtender.UnreadConversation(arrayOfString, remoteInput, pendingIntent1, pendingIntent2, new String[] { str }, l);
      }
      
      public Builder setLatestTimestamp(long param2Long) {
        this.mLatestTimestamp = param2Long;
        return this;
      }
      
      public Builder setReadPendingIntent(PendingIntent param2PendingIntent) {
        this.mReadPendingIntent = param2PendingIntent;
        return this;
      }
      
      public Builder setReplyAction(PendingIntent param2PendingIntent, RemoteInput param2RemoteInput) {
        this.mRemoteInput = param2RemoteInput;
        this.mReplyPendingIntent = param2PendingIntent;
        return this;
      }
    }
  }
  
  public static class UnreadConversation {
    private final long mLatestTimestamp;
    
    private final String[] mMessages;
    
    private final String[] mParticipants;
    
    private final PendingIntent mReadPendingIntent;
    
    private final RemoteInput mRemoteInput;
    
    private final PendingIntent mReplyPendingIntent;
    
    UnreadConversation(String[] param1ArrayOfString1, RemoteInput param1RemoteInput, PendingIntent param1PendingIntent1, PendingIntent param1PendingIntent2, String[] param1ArrayOfString2, long param1Long) {
      this.mMessages = param1ArrayOfString1;
      this.mRemoteInput = param1RemoteInput;
      this.mReadPendingIntent = param1PendingIntent2;
      this.mReplyPendingIntent = param1PendingIntent1;
      this.mParticipants = param1ArrayOfString2;
      this.mLatestTimestamp = param1Long;
    }
    
    public long getLatestTimestamp() {
      return this.mLatestTimestamp;
    }
    
    public String[] getMessages() {
      return this.mMessages;
    }
    
    public String getParticipant() {
      return (this.mParticipants.length > 0) ? this.mParticipants[0] : null;
    }
    
    public String[] getParticipants() {
      return this.mParticipants;
    }
    
    public PendingIntent getReadPendingIntent() {
      return this.mReadPendingIntent;
    }
    
    public RemoteInput getRemoteInput() {
      return this.mRemoteInput;
    }
    
    public PendingIntent getReplyPendingIntent() {
      return this.mReplyPendingIntent;
    }
    
    public static class Builder {
      private long mLatestTimestamp;
      
      private final List<String> mMessages = new ArrayList<String>();
      
      private final String mParticipant;
      
      private PendingIntent mReadPendingIntent;
      
      private RemoteInput mRemoteInput;
      
      private PendingIntent mReplyPendingIntent;
      
      public Builder(String param3String) {
        this.mParticipant = param3String;
      }
      
      public Builder addMessage(String param3String) {
        this.mMessages.add(param3String);
        return this;
      }
      
      public NotificationCompat.CarExtender.UnreadConversation build() {
        String[] arrayOfString = this.mMessages.<String>toArray(new String[this.mMessages.size()]);
        String str = this.mParticipant;
        RemoteInput remoteInput = this.mRemoteInput;
        PendingIntent pendingIntent1 = this.mReplyPendingIntent;
        PendingIntent pendingIntent2 = this.mReadPendingIntent;
        long l = this.mLatestTimestamp;
        return new NotificationCompat.CarExtender.UnreadConversation(arrayOfString, remoteInput, pendingIntent1, pendingIntent2, new String[] { str }, l);
      }
      
      public Builder setLatestTimestamp(long param3Long) {
        this.mLatestTimestamp = param3Long;
        return this;
      }
      
      public Builder setReadPendingIntent(PendingIntent param3PendingIntent) {
        this.mReadPendingIntent = param3PendingIntent;
        return this;
      }
      
      public Builder setReplyAction(PendingIntent param3PendingIntent, RemoteInput param3RemoteInput) {
        this.mRemoteInput = param3RemoteInput;
        this.mReplyPendingIntent = param3PendingIntent;
        return this;
      }
    }
  }
  
  public static class Builder {
    private long mLatestTimestamp;
    
    private final List<String> mMessages = new ArrayList<String>();
    
    private final String mParticipant;
    
    private PendingIntent mReadPendingIntent;
    
    private RemoteInput mRemoteInput;
    
    private PendingIntent mReplyPendingIntent;
    
    public Builder(String param1String) {
      this.mParticipant = param1String;
    }
    
    public Builder addMessage(String param1String) {
      this.mMessages.add(param1String);
      return this;
    }
    
    public NotificationCompat.CarExtender.UnreadConversation build() {
      String[] arrayOfString = this.mMessages.<String>toArray(new String[this.mMessages.size()]);
      String str = this.mParticipant;
      RemoteInput remoteInput = this.mRemoteInput;
      PendingIntent pendingIntent1 = this.mReplyPendingIntent;
      PendingIntent pendingIntent2 = this.mReadPendingIntent;
      long l = this.mLatestTimestamp;
      return new NotificationCompat.CarExtender.UnreadConversation(arrayOfString, remoteInput, pendingIntent1, pendingIntent2, new String[] { str }, l);
    }
    
    public Builder setLatestTimestamp(long param1Long) {
      this.mLatestTimestamp = param1Long;
      return this;
    }
    
    public Builder setReadPendingIntent(PendingIntent param1PendingIntent) {
      this.mReadPendingIntent = param1PendingIntent;
      return this;
    }
    
    public Builder setReplyAction(PendingIntent param1PendingIntent, RemoteInput param1RemoteInput) {
      this.mRemoteInput = param1RemoteInput;
      this.mReplyPendingIntent = param1PendingIntent;
      return this;
    }
  }
  
  public static class DecoratedCustomViewStyle extends Style {
    private static final int MAX_ACTION_BUTTONS = 3;
    
    private RemoteViews createRemoteViews(RemoteViews param1RemoteViews, boolean param1Boolean) {
      boolean bool = false;
      RemoteViews remoteViews = applyStandardTemplate(true, R.layout.notification_template_custom_big, false);
      remoteViews.removeAllViews(R.id.actions);
      int j = 0;
      int i = j;
      if (param1Boolean) {
        i = j;
        if (this.mBuilder.mActions != null) {
          int k = Math.min(this.mBuilder.mActions.size(), 3);
          i = j;
          if (k > 0) {
            byte b = 1;
            j = 0;
            while (true) {
              i = b;
              if (j < k) {
                RemoteViews remoteViews1 = generateActionButton(this.mBuilder.mActions.get(j));
                remoteViews.addView(R.id.actions, remoteViews1);
                j++;
                continue;
              } 
              break;
            } 
          } 
        } 
      } 
      if (i != 0) {
        i = bool;
        remoteViews.setViewVisibility(R.id.actions, i);
        remoteViews.setViewVisibility(R.id.action_divider, i);
        buildIntoRemoteViews(remoteViews, param1RemoteViews);
        return remoteViews;
      } 
      i = 8;
      remoteViews.setViewVisibility(R.id.actions, i);
      remoteViews.setViewVisibility(R.id.action_divider, i);
      buildIntoRemoteViews(remoteViews, param1RemoteViews);
      return remoteViews;
    }
    
    private RemoteViews generateActionButton(NotificationCompat.Action param1Action) {
      boolean bool;
      int i;
      if (param1Action.actionIntent == null) {
        bool = true;
      } else {
        bool = false;
      } 
      String str = this.mBuilder.mContext.getPackageName();
      if (bool) {
        i = R.layout.notification_action_tombstone;
      } else {
        i = R.layout.notification_action;
      } 
      RemoteViews remoteViews = new RemoteViews(str, i);
      remoteViews.setImageViewBitmap(R.id.action_image, createColoredBitmap(param1Action.getIcon(), this.mBuilder.mContext.getResources().getColor(R.color.notification_action_color_filter)));
      remoteViews.setTextViewText(R.id.action_text, param1Action.title);
      if (!bool)
        remoteViews.setOnClickPendingIntent(R.id.action_container, param1Action.actionIntent); 
      if (Build.VERSION.SDK_INT >= 15)
        remoteViews.setContentDescription(R.id.action_container, param1Action.title); 
      return remoteViews;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      if (Build.VERSION.SDK_INT >= 24)
        param1NotificationBuilderWithBuilderAccessor.getBuilder().setStyle((Notification.Style)new Notification.DecoratedCustomViewStyle()); 
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      if (Build.VERSION.SDK_INT < 24) {
        RemoteViews remoteViews = this.mBuilder.getBigContentView();
        if (remoteViews == null)
          remoteViews = this.mBuilder.getContentView(); 
        if (remoteViews != null)
          return createRemoteViews(remoteViews, true); 
      } 
      return null;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      return (Build.VERSION.SDK_INT < 24 && this.mBuilder.getContentView() != null) ? createRemoteViews(this.mBuilder.getContentView(), false) : null;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      if (Build.VERSION.SDK_INT < 24) {
        RemoteViews remoteViews1;
        RemoteViews remoteViews2 = this.mBuilder.getHeadsUpContentView();
        if (remoteViews2 != null) {
          remoteViews1 = remoteViews2;
        } else {
          remoteViews1 = this.mBuilder.getContentView();
        } 
        if (remoteViews2 != null)
          return createRemoteViews(remoteViews1, true); 
      } 
      return null;
    }
  }
  
  public static interface Extender {
    NotificationCompat.Builder extend(NotificationCompat.Builder param1Builder);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface GroupAlertBehavior {}
  
  public static class InboxStyle extends Style {
    private ArrayList<CharSequence> mTexts = new ArrayList<CharSequence>();
    
    public InboxStyle() {}
    
    public InboxStyle(NotificationCompat.Builder param1Builder) {
      setBuilder(param1Builder);
    }
    
    public InboxStyle addLine(CharSequence param1CharSequence) {
      this.mTexts.add(NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence));
      return this;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      if (Build.VERSION.SDK_INT >= 16) {
        Notification.InboxStyle inboxStyle = (new Notification.InboxStyle(param1NotificationBuilderWithBuilderAccessor.getBuilder())).setBigContentTitle(this.mBigContentTitle);
        if (this.mSummaryTextSet)
          inboxStyle.setSummaryText(this.mSummaryText); 
        Iterator<CharSequence> iterator = this.mTexts.iterator();
        while (iterator.hasNext())
          inboxStyle.addLine(iterator.next()); 
      } 
    }
    
    public InboxStyle setBigContentTitle(CharSequence param1CharSequence) {
      this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public InboxStyle setSummaryText(CharSequence param1CharSequence) {
      this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      this.mSummaryTextSet = true;
      return this;
    }
  }
  
  public static class MessagingStyle extends Style {
    public static final int MAXIMUM_RETAINED_MESSAGES = 25;
    
    @Nullable
    private CharSequence mConversationTitle;
    
    @Nullable
    private Boolean mIsGroupConversation;
    
    private final List<Message> mMessages = new ArrayList<Message>();
    
    private Person mUser;
    
    private MessagingStyle() {}
    
    public MessagingStyle(@NonNull Person param1Person) {
      if (TextUtils.isEmpty(param1Person.getName()))
        throw new IllegalArgumentException("User's name must not be empty."); 
      this.mUser = param1Person;
    }
    
    @Deprecated
    public MessagingStyle(@NonNull CharSequence param1CharSequence) {
      this.mUser = (new Person.Builder()).setName(param1CharSequence).build();
    }
    
    @Nullable
    public static MessagingStyle extractMessagingStyleFromNotification(Notification param1Notification) {
      Bundle bundle = NotificationCompat.getExtras(param1Notification);
      if (bundle != null && !bundle.containsKey("android.selfDisplayName") && !bundle.containsKey("android.messagingStyleUser"))
        return null; 
      try {
        MessagingStyle messagingStyle = new MessagingStyle();
        messagingStyle.restoreFromCompatExtras(bundle);
        return messagingStyle;
      } catch (ClassCastException classCastException) {
        return null;
      } 
    }
    
    @Nullable
    private Message findLatestIncomingMessage() {
      for (int i = this.mMessages.size() - 1; i >= 0; i--) {
        Message message = this.mMessages.get(i);
        if (message.getPerson() != null && !TextUtils.isEmpty(message.getPerson().getName()))
          return message; 
      } 
      return !this.mMessages.isEmpty() ? this.mMessages.get(this.mMessages.size() - 1) : null;
    }
    
    private boolean hasMessagesWithoutSender() {
      for (int i = this.mMessages.size() - 1; i >= 0; i--) {
        Message message = this.mMessages.get(i);
        if (message.getPerson() != null && message.getPerson().getName() == null)
          return true; 
      } 
      return false;
    }
    
    @NonNull
    private TextAppearanceSpan makeFontColorSpan(int param1Int) {
      return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(param1Int), null);
    }
    
    private CharSequence makeMessageLine(Message param1Message) {
      String str;
      byte b;
      boolean bool;
      BidiFormatter bidiFormatter = BidiFormatter.getInstance();
      SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
      if (Build.VERSION.SDK_INT >= 21) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool) {
        b = -16777216;
      } else {
        b = -1;
      } 
      if (param1Message.getPerson() == null) {
        charSequence2 = "";
      } else {
        charSequence2 = param1Message.getPerson().getName();
      } 
      int i = b;
      CharSequence charSequence3 = charSequence2;
      if (TextUtils.isEmpty(charSequence2)) {
        charSequence2 = this.mUser.getName();
        i = b;
        charSequence3 = charSequence2;
        if (bool) {
          i = b;
          charSequence3 = charSequence2;
          if (this.mBuilder.getColor() != 0) {
            i = this.mBuilder.getColor();
            charSequence3 = charSequence2;
          } 
        } 
      } 
      CharSequence charSequence2 = bidiFormatter.unicodeWrap(charSequence3);
      spannableStringBuilder.append(charSequence2);
      spannableStringBuilder.setSpan(makeFontColorSpan(i), spannableStringBuilder.length() - charSequence2.length(), spannableStringBuilder.length(), 33);
      if (param1Message.getText() == null) {
        str = "";
        spannableStringBuilder.append("  ").append(bidiFormatter.unicodeWrap(str));
        return (CharSequence)spannableStringBuilder;
      } 
      CharSequence charSequence1 = str.getText();
      spannableStringBuilder.append("  ").append(bidiFormatter.unicodeWrap(charSequence1));
      return (CharSequence)spannableStringBuilder;
    }
    
    public void addCompatExtras(Bundle param1Bundle) {
      super.addCompatExtras(param1Bundle);
      param1Bundle.putCharSequence("android.selfDisplayName", this.mUser.getName());
      param1Bundle.putBundle("android.messagingStyleUser", this.mUser.toBundle());
      param1Bundle.putCharSequence("android.hiddenConversationTitle", this.mConversationTitle);
      if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue())
        param1Bundle.putCharSequence("android.conversationTitle", this.mConversationTitle); 
      if (!this.mMessages.isEmpty())
        param1Bundle.putParcelableArray("android.messages", (Parcelable[])Message.getBundleArrayForMessages(this.mMessages)); 
      if (this.mIsGroupConversation != null)
        param1Bundle.putBoolean("android.isGroupConversation", this.mIsGroupConversation.booleanValue()); 
    }
    
    public MessagingStyle addMessage(Message param1Message) {
      this.mMessages.add(param1Message);
      if (this.mMessages.size() > 25)
        this.mMessages.remove(0); 
      return this;
    }
    
    public MessagingStyle addMessage(CharSequence param1CharSequence, long param1Long, Person param1Person) {
      addMessage(new Message(param1CharSequence, param1Long, param1Person));
      return this;
    }
    
    @Deprecated
    public MessagingStyle addMessage(CharSequence param1CharSequence1, long param1Long, CharSequence param1CharSequence2) {
      this.mMessages.add(new Message(param1CharSequence1, param1Long, (new Person.Builder()).setName(param1CharSequence2).build()));
      if (this.mMessages.size() > 25)
        this.mMessages.remove(0); 
      return this;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      setGroupConversation(isGroupConversation());
      if (Build.VERSION.SDK_INT >= 24) {
        Notification.MessagingStyle messagingStyle;
        if (Build.VERSION.SDK_INT >= 28) {
          messagingStyle = new Notification.MessagingStyle(this.mUser.toAndroidPerson());
        } else {
          messagingStyle = new Notification.MessagingStyle(this.mUser.getName());
        } 
        if (this.mIsGroupConversation.booleanValue() || Build.VERSION.SDK_INT >= 28)
          messagingStyle.setConversationTitle(this.mConversationTitle); 
        if (Build.VERSION.SDK_INT >= 28)
          messagingStyle.setGroupConversation(this.mIsGroupConversation.booleanValue()); 
        for (Message message2 : this.mMessages) {
          Notification.MessagingStyle.Message message1;
          if (Build.VERSION.SDK_INT >= 28) {
            Person person;
            Person person1 = message2.getPerson();
            CharSequence charSequence = message2.getText();
            long l = message2.getTimestamp();
            if (person1 == null) {
              person1 = null;
            } else {
              person = person1.toAndroidPerson();
            } 
            message1 = new Notification.MessagingStyle.Message(charSequence, l, person);
          } else {
            CharSequence charSequence = null;
            if (message2.getPerson() != null)
              charSequence = message2.getPerson().getName(); 
            message1 = new Notification.MessagingStyle.Message(message2.getText(), message2.getTimestamp(), charSequence);
          } 
          if (message2.getDataMimeType() != null)
            message1.setData(message2.getDataMimeType(), message2.getDataUri()); 
          messagingStyle.addMessage(message1);
        } 
        messagingStyle.setBuilder(param1NotificationBuilderWithBuilderAccessor.getBuilder());
        return;
      } 
      Message message = findLatestIncomingMessage();
      if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
        param1NotificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(this.mConversationTitle);
      } else if (message != null) {
        param1NotificationBuilderWithBuilderAccessor.getBuilder().setContentTitle("");
        if (message.getPerson() != null)
          param1NotificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(message.getPerson().getName()); 
      } 
      if (message != null) {
        CharSequence charSequence;
        Notification.Builder builder = param1NotificationBuilderWithBuilderAccessor.getBuilder();
        if (this.mConversationTitle != null) {
          charSequence = makeMessageLine(message);
        } else {
          charSequence = charSequence.getText();
        } 
        builder.setContentText(charSequence);
      } 
      if (Build.VERSION.SDK_INT >= 16) {
        boolean bool;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (this.mConversationTitle != null || hasMessagesWithoutSender()) {
          bool = true;
        } else {
          bool = false;
        } 
        for (int i = this.mMessages.size() - 1; i >= 0; i--) {
          CharSequence charSequence;
          message = this.mMessages.get(i);
          if (bool) {
            charSequence = makeMessageLine(message);
          } else {
            charSequence = charSequence.getText();
          } 
          if (i != this.mMessages.size() - 1)
            spannableStringBuilder.insert(0, "\n"); 
          spannableStringBuilder.insert(0, charSequence);
        } 
        (new Notification.BigTextStyle(param1NotificationBuilderWithBuilderAccessor.getBuilder())).setBigContentTitle(null).bigText((CharSequence)spannableStringBuilder);
        return;
      } 
    }
    
    @Nullable
    public CharSequence getConversationTitle() {
      return this.mConversationTitle;
    }
    
    public List<Message> getMessages() {
      return this.mMessages;
    }
    
    public Person getUser() {
      return this.mUser;
    }
    
    @Deprecated
    public CharSequence getUserDisplayName() {
      return this.mUser.getName();
    }
    
    public boolean isGroupConversation() {
      boolean bool = false;
      if (this.mBuilder != null && (this.mBuilder.mContext.getApplicationInfo()).targetSdkVersion < 28 && this.mIsGroupConversation == null) {
        if (this.mConversationTitle != null)
          bool = true; 
        return bool;
      } 
      return (this.mIsGroupConversation != null) ? this.mIsGroupConversation.booleanValue() : bool;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected void restoreFromCompatExtras(Bundle param1Bundle) {
      this.mMessages.clear();
      if (param1Bundle.containsKey("android.messagingStyleUser")) {
        this.mUser = Person.fromBundle(param1Bundle.getBundle("android.messagingStyleUser"));
      } else {
        this.mUser = (new Person.Builder()).setName(param1Bundle.getString("android.selfDisplayName")).build();
      } 
      this.mConversationTitle = param1Bundle.getCharSequence("android.conversationTitle");
      if (this.mConversationTitle == null)
        this.mConversationTitle = param1Bundle.getCharSequence("android.hiddenConversationTitle"); 
      Parcelable[] arrayOfParcelable = param1Bundle.getParcelableArray("android.messages");
      if (arrayOfParcelable != null)
        this.mMessages.addAll(Message.getMessagesFromBundleArray(arrayOfParcelable)); 
      if (param1Bundle.containsKey("android.isGroupConversation"))
        this.mIsGroupConversation = Boolean.valueOf(param1Bundle.getBoolean("android.isGroupConversation")); 
    }
    
    public MessagingStyle setConversationTitle(@Nullable CharSequence param1CharSequence) {
      this.mConversationTitle = param1CharSequence;
      return this;
    }
    
    public MessagingStyle setGroupConversation(boolean param1Boolean) {
      this.mIsGroupConversation = Boolean.valueOf(param1Boolean);
      return this;
    }
    
    public static final class Message {
      static final String KEY_DATA_MIME_TYPE = "type";
      
      static final String KEY_DATA_URI = "uri";
      
      static final String KEY_EXTRAS_BUNDLE = "extras";
      
      static final String KEY_NOTIFICATION_PERSON = "sender_person";
      
      static final String KEY_PERSON = "person";
      
      static final String KEY_SENDER = "sender";
      
      static final String KEY_TEXT = "text";
      
      static final String KEY_TIMESTAMP = "time";
      
      @Nullable
      private String mDataMimeType;
      
      @Nullable
      private Uri mDataUri;
      
      private Bundle mExtras = new Bundle();
      
      @Nullable
      private final Person mPerson;
      
      private final CharSequence mText;
      
      private final long mTimestamp;
      
      public Message(CharSequence param2CharSequence, long param2Long, @Nullable Person param2Person) {
        this.mText = param2CharSequence;
        this.mTimestamp = param2Long;
        this.mPerson = param2Person;
      }
      
      @Deprecated
      public Message(CharSequence param2CharSequence1, long param2Long, CharSequence param2CharSequence2) {
        this(param2CharSequence1, param2Long, (new Person.Builder()).setName(param2CharSequence2).build());
      }
      
      @NonNull
      static Bundle[] getBundleArrayForMessages(List<Message> param2List) {
        Bundle[] arrayOfBundle = new Bundle[param2List.size()];
        int j = param2List.size();
        for (int i = 0; i < j; i++)
          arrayOfBundle[i] = ((Message)param2List.get(i)).toBundle(); 
        return arrayOfBundle;
      }
      
      @Nullable
      static Message getMessageFromBundle(Bundle param2Bundle) {
        try {
          if (!param2Bundle.containsKey("text") || !param2Bundle.containsKey("time"))
            return null; 
          Person person = null;
          if (param2Bundle.containsKey("person")) {
            person = Person.fromBundle(param2Bundle.getBundle("person"));
          } else if (param2Bundle.containsKey("sender_person") && Build.VERSION.SDK_INT >= 28) {
            person = Person.fromAndroidPerson((Person)param2Bundle.getParcelable("sender_person"));
          } else if (param2Bundle.containsKey("sender")) {
            person = (new Person.Builder()).setName(param2Bundle.getCharSequence("sender")).build();
          } 
          Message message2 = new Message(param2Bundle.getCharSequence("text"), param2Bundle.getLong("time"), person);
          if (param2Bundle.containsKey("type") && param2Bundle.containsKey("uri"))
            message2.setData(param2Bundle.getString("type"), (Uri)param2Bundle.getParcelable("uri")); 
          Message message1 = message2;
          if (param2Bundle.containsKey("extras")) {
            message2.getExtras().putAll(param2Bundle.getBundle("extras"));
            return message2;
          } 
          return message1;
        } catch (ClassCastException classCastException) {
          return null;
        } 
      }
      
      @NonNull
      static List<Message> getMessagesFromBundleArray(Parcelable[] param2ArrayOfParcelable) {
        ArrayList<Message> arrayList = new ArrayList(param2ArrayOfParcelable.length);
        for (int i = 0; i < param2ArrayOfParcelable.length; i++) {
          if (param2ArrayOfParcelable[i] instanceof Bundle) {
            Message message = getMessageFromBundle((Bundle)param2ArrayOfParcelable[i]);
            if (message != null)
              arrayList.add(message); 
          } 
        } 
        return arrayList;
      }
      
      private Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (this.mText != null)
          bundle.putCharSequence("text", this.mText); 
        bundle.putLong("time", this.mTimestamp);
        if (this.mPerson != null) {
          bundle.putCharSequence("sender", this.mPerson.getName());
          if (Build.VERSION.SDK_INT >= 28) {
            bundle.putParcelable("sender_person", (Parcelable)this.mPerson.toAndroidPerson());
          } else {
            bundle.putBundle("person", this.mPerson.toBundle());
          } 
        } 
        if (this.mDataMimeType != null)
          bundle.putString("type", this.mDataMimeType); 
        if (this.mDataUri != null)
          bundle.putParcelable("uri", (Parcelable)this.mDataUri); 
        if (this.mExtras != null)
          bundle.putBundle("extras", this.mExtras); 
        return bundle;
      }
      
      @Nullable
      public String getDataMimeType() {
        return this.mDataMimeType;
      }
      
      @Nullable
      public Uri getDataUri() {
        return this.mDataUri;
      }
      
      @NonNull
      public Bundle getExtras() {
        return this.mExtras;
      }
      
      @Nullable
      public Person getPerson() {
        return this.mPerson;
      }
      
      @Deprecated
      @Nullable
      public CharSequence getSender() {
        return (this.mPerson == null) ? null : this.mPerson.getName();
      }
      
      @NonNull
      public CharSequence getText() {
        return this.mText;
      }
      
      public long getTimestamp() {
        return this.mTimestamp;
      }
      
      public Message setData(String param2String, Uri param2Uri) {
        this.mDataMimeType = param2String;
        this.mDataUri = param2Uri;
        return this;
      }
    }
  }
  
  public static final class Message {
    static final String KEY_DATA_MIME_TYPE = "type";
    
    static final String KEY_DATA_URI = "uri";
    
    static final String KEY_EXTRAS_BUNDLE = "extras";
    
    static final String KEY_NOTIFICATION_PERSON = "sender_person";
    
    static final String KEY_PERSON = "person";
    
    static final String KEY_SENDER = "sender";
    
    static final String KEY_TEXT = "text";
    
    static final String KEY_TIMESTAMP = "time";
    
    @Nullable
    private String mDataMimeType;
    
    @Nullable
    private Uri mDataUri;
    
    private Bundle mExtras = new Bundle();
    
    @Nullable
    private final Person mPerson;
    
    private final CharSequence mText;
    
    private final long mTimestamp;
    
    public Message(CharSequence param1CharSequence, long param1Long, @Nullable Person param1Person) {
      this.mText = param1CharSequence;
      this.mTimestamp = param1Long;
      this.mPerson = param1Person;
    }
    
    @Deprecated
    public Message(CharSequence param1CharSequence1, long param1Long, CharSequence param1CharSequence2) {
      this(param1CharSequence1, param1Long, (new Person.Builder()).setName(param1CharSequence2).build());
    }
    
    @NonNull
    static Bundle[] getBundleArrayForMessages(List<Message> param1List) {
      Bundle[] arrayOfBundle = new Bundle[param1List.size()];
      int j = param1List.size();
      for (int i = 0; i < j; i++)
        arrayOfBundle[i] = ((Message)param1List.get(i)).toBundle(); 
      return arrayOfBundle;
    }
    
    @Nullable
    static Message getMessageFromBundle(Bundle param1Bundle) {
      try {
        if (!param1Bundle.containsKey("text") || !param1Bundle.containsKey("time"))
          return null; 
        Person person = null;
        if (param1Bundle.containsKey("person")) {
          person = Person.fromBundle(param1Bundle.getBundle("person"));
        } else if (param1Bundle.containsKey("sender_person") && Build.VERSION.SDK_INT >= 28) {
          person = Person.fromAndroidPerson((Person)param1Bundle.getParcelable("sender_person"));
        } else if (param1Bundle.containsKey("sender")) {
          person = (new Person.Builder()).setName(param1Bundle.getCharSequence("sender")).build();
        } 
        Message message2 = new Message(param1Bundle.getCharSequence("text"), param1Bundle.getLong("time"), person);
        if (param1Bundle.containsKey("type") && param1Bundle.containsKey("uri"))
          message2.setData(param1Bundle.getString("type"), (Uri)param1Bundle.getParcelable("uri")); 
        Message message1 = message2;
        if (param1Bundle.containsKey("extras")) {
          message2.getExtras().putAll(param1Bundle.getBundle("extras"));
          return message2;
        } 
        return message1;
      } catch (ClassCastException classCastException) {
        return null;
      } 
    }
    
    @NonNull
    static List<Message> getMessagesFromBundleArray(Parcelable[] param1ArrayOfParcelable) {
      ArrayList<Message> arrayList = new ArrayList(param1ArrayOfParcelable.length);
      for (int i = 0; i < param1ArrayOfParcelable.length; i++) {
        if (param1ArrayOfParcelable[i] instanceof Bundle) {
          Message message = getMessageFromBundle((Bundle)param1ArrayOfParcelable[i]);
          if (message != null)
            arrayList.add(message); 
        } 
      } 
      return arrayList;
    }
    
    private Bundle toBundle() {
      Bundle bundle = new Bundle();
      if (this.mText != null)
        bundle.putCharSequence("text", this.mText); 
      bundle.putLong("time", this.mTimestamp);
      if (this.mPerson != null) {
        bundle.putCharSequence("sender", this.mPerson.getName());
        if (Build.VERSION.SDK_INT >= 28) {
          bundle.putParcelable("sender_person", (Parcelable)this.mPerson.toAndroidPerson());
        } else {
          bundle.putBundle("person", this.mPerson.toBundle());
        } 
      } 
      if (this.mDataMimeType != null)
        bundle.putString("type", this.mDataMimeType); 
      if (this.mDataUri != null)
        bundle.putParcelable("uri", (Parcelable)this.mDataUri); 
      if (this.mExtras != null)
        bundle.putBundle("extras", this.mExtras); 
      return bundle;
    }
    
    @Nullable
    public String getDataMimeType() {
      return this.mDataMimeType;
    }
    
    @Nullable
    public Uri getDataUri() {
      return this.mDataUri;
    }
    
    @NonNull
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    @Nullable
    public Person getPerson() {
      return this.mPerson;
    }
    
    @Deprecated
    @Nullable
    public CharSequence getSender() {
      return (this.mPerson == null) ? null : this.mPerson.getName();
    }
    
    @NonNull
    public CharSequence getText() {
      return this.mText;
    }
    
    public long getTimestamp() {
      return this.mTimestamp;
    }
    
    public Message setData(String param1String, Uri param1Uri) {
      this.mDataMimeType = param1String;
      this.mDataUri = param1Uri;
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface NotificationVisibility {}
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface StreamType {}
  
  public static abstract class Style {
    CharSequence mBigContentTitle;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected NotificationCompat.Builder mBuilder;
    
    CharSequence mSummaryText;
    
    boolean mSummaryTextSet = false;
    
    private int calculateTopPadding() {
      Resources resources = this.mBuilder.mContext.getResources();
      int i = resources.getDimensionPixelSize(R.dimen.notification_top_pad);
      int j = resources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
      float f = (constrain((resources.getConfiguration()).fontScale, 1.0F, 1.3F) - 1.0F) / 0.29999995F;
      return Math.round((1.0F - f) * i + j * f);
    }
    
    private static float constrain(float param1Float1, float param1Float2, float param1Float3) {
      return (param1Float1 < param1Float2) ? param1Float2 : ((param1Float1 > param1Float3) ? param1Float3 : param1Float1);
    }
    
    private Bitmap createColoredBitmap(int param1Int1, int param1Int2, int param1Int3) {
      Drawable drawable = this.mBuilder.mContext.getResources().getDrawable(param1Int1);
      if (param1Int3 == 0) {
        param1Int1 = drawable.getIntrinsicWidth();
      } else {
        param1Int1 = param1Int3;
      } 
      if (param1Int3 == 0)
        param1Int3 = drawable.getIntrinsicHeight(); 
      Bitmap bitmap = Bitmap.createBitmap(param1Int1, param1Int3, Bitmap.Config.ARGB_8888);
      drawable.setBounds(0, 0, param1Int1, param1Int3);
      if (param1Int2 != 0)
        drawable.mutate().setColorFilter((ColorFilter)new PorterDuffColorFilter(param1Int2, PorterDuff.Mode.SRC_IN)); 
      drawable.draw(new Canvas(bitmap));
      return bitmap;
    }
    
    private Bitmap createIconWithBackground(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      int j = R.drawable.notification_icon_background;
      int i = param1Int4;
      if (param1Int4 == 0)
        i = 0; 
      Bitmap bitmap = createColoredBitmap(j, i, param1Int2);
      Canvas canvas = new Canvas(bitmap);
      Drawable drawable = this.mBuilder.mContext.getResources().getDrawable(param1Int1).mutate();
      drawable.setFilterBitmap(true);
      param1Int1 = (param1Int2 - param1Int3) / 2;
      drawable.setBounds(param1Int1, param1Int1, param1Int3 + param1Int1, param1Int3 + param1Int1);
      drawable.setColorFilter((ColorFilter)new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
      drawable.draw(canvas);
      return bitmap;
    }
    
    private void hideNormalContent(RemoteViews param1RemoteViews) {
      param1RemoteViews.setViewVisibility(R.id.title, 8);
      param1RemoteViews.setViewVisibility(R.id.text2, 8);
      param1RemoteViews.setViewVisibility(R.id.text, 8);
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void addCompatExtras(Bundle param1Bundle) {}
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {}
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews applyStandardTemplate(boolean param1Boolean1, int param1Int, boolean param1Boolean2) {
      Resources resources = this.mBuilder.mContext.getResources();
      RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), param1Int);
      boolean bool = false;
      byte b = 0;
      if (this.mBuilder.getPriority() < -1) {
        param1Int = 1;
      } else {
        param1Int = 0;
      } 
      if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21)
        if (param1Int != 0) {
          remoteViews.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg_low);
          remoteViews.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_low_bg);
        } else {
          remoteViews.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg);
          remoteViews.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_bg);
        }  
      if (this.mBuilder.mLargeIcon != null) {
        if (Build.VERSION.SDK_INT >= 16) {
          remoteViews.setViewVisibility(R.id.icon, 0);
          remoteViews.setImageViewBitmap(R.id.icon, this.mBuilder.mLargeIcon);
        } else {
          remoteViews.setViewVisibility(R.id.icon, 8);
        } 
        if (param1Boolean1 && this.mBuilder.mNotification.icon != 0) {
          param1Int = resources.getDimensionPixelSize(R.dimen.notification_right_icon_size);
          int j = resources.getDimensionPixelSize(R.dimen.notification_small_icon_background_padding);
          if (Build.VERSION.SDK_INT >= 21) {
            Bitmap bitmap = createIconWithBackground(this.mBuilder.mNotification.icon, param1Int, param1Int - j * 2, this.mBuilder.getColor());
            remoteViews.setImageViewBitmap(R.id.right_icon, bitmap);
          } else {
            remoteViews.setImageViewBitmap(R.id.right_icon, createColoredBitmap(this.mBuilder.mNotification.icon, -1));
          } 
          remoteViews.setViewVisibility(R.id.right_icon, 0);
        } 
      } else if (param1Boolean1 && this.mBuilder.mNotification.icon != 0) {
        remoteViews.setViewVisibility(R.id.icon, 0);
        if (Build.VERSION.SDK_INT >= 21) {
          param1Int = resources.getDimensionPixelSize(R.dimen.notification_large_icon_width);
          int j = resources.getDimensionPixelSize(R.dimen.notification_big_circle_margin);
          int k = resources.getDimensionPixelSize(R.dimen.notification_small_icon_size_as_large);
          Bitmap bitmap = createIconWithBackground(this.mBuilder.mNotification.icon, param1Int - j, k, this.mBuilder.getColor());
          remoteViews.setImageViewBitmap(R.id.icon, bitmap);
        } else {
          remoteViews.setImageViewBitmap(R.id.icon, createColoredBitmap(this.mBuilder.mNotification.icon, -1));
        } 
      } 
      if (this.mBuilder.mContentTitle != null)
        remoteViews.setTextViewText(R.id.title, this.mBuilder.mContentTitle); 
      if (this.mBuilder.mContentText != null) {
        remoteViews.setTextViewText(R.id.text, this.mBuilder.mContentText);
        bool = true;
      } 
      if (Build.VERSION.SDK_INT < 21 && this.mBuilder.mLargeIcon != null) {
        param1Int = 1;
      } else {
        param1Int = 0;
      } 
      if (this.mBuilder.mContentInfo != null) {
        remoteViews.setTextViewText(R.id.info, this.mBuilder.mContentInfo);
        remoteViews.setViewVisibility(R.id.info, 0);
        bool = true;
        param1Int = 1;
      } else if (this.mBuilder.mNumber > 0) {
        param1Int = resources.getInteger(R.integer.status_bar_notification_info_maxnum);
        if (this.mBuilder.mNumber > param1Int) {
          remoteViews.setTextViewText(R.id.info, resources.getString(R.string.status_bar_notification_info_overflow));
        } else {
          NumberFormat numberFormat = NumberFormat.getIntegerInstance();
          remoteViews.setTextViewText(R.id.info, numberFormat.format(this.mBuilder.mNumber));
        } 
        remoteViews.setViewVisibility(R.id.info, 0);
        bool = true;
        param1Int = 1;
      } else {
        remoteViews.setViewVisibility(R.id.info, 8);
      } 
      int i = b;
      if (this.mBuilder.mSubText != null) {
        i = b;
        if (Build.VERSION.SDK_INT >= 16) {
          remoteViews.setTextViewText(R.id.text, this.mBuilder.mSubText);
          if (this.mBuilder.mContentText != null) {
            remoteViews.setTextViewText(R.id.text2, this.mBuilder.mContentText);
            remoteViews.setViewVisibility(R.id.text2, 0);
            i = 1;
          } else {
            remoteViews.setViewVisibility(R.id.text2, 8);
            i = b;
          } 
        } 
      } 
      if (i && Build.VERSION.SDK_INT >= 16) {
        if (param1Boolean2) {
          float f = resources.getDimensionPixelSize(R.dimen.notification_subtext_size);
          remoteViews.setTextViewTextSize(R.id.text, 0, f);
        } 
        remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
      } 
      if (this.mBuilder.getWhenIfShowing() != 0L) {
        if (this.mBuilder.mUseChronometer && Build.VERSION.SDK_INT >= 16) {
          remoteViews.setViewVisibility(R.id.chronometer, 0);
          remoteViews.setLong(R.id.chronometer, "setBase", this.mBuilder.getWhenIfShowing() + SystemClock.elapsedRealtime() - System.currentTimeMillis());
          remoteViews.setBoolean(R.id.chronometer, "setStarted", true);
        } else {
          remoteViews.setViewVisibility(R.id.time, 0);
          remoteViews.setLong(R.id.time, "setTime", this.mBuilder.getWhenIfShowing());
        } 
        param1Int = 1;
      } 
      i = R.id.right_side;
      if (param1Int != 0) {
        param1Int = 0;
      } else {
        param1Int = 8;
      } 
      remoteViews.setViewVisibility(i, param1Int);
      i = R.id.line3;
      if (bool) {
        param1Int = 0;
        remoteViews.setViewVisibility(i, param1Int);
        return remoteViews;
      } 
      param1Int = 8;
      remoteViews.setViewVisibility(i, param1Int);
      return remoteViews;
    }
    
    public Notification build() {
      Notification notification = null;
      if (this.mBuilder != null)
        notification = this.mBuilder.build(); 
      return notification;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void buildIntoRemoteViews(RemoteViews param1RemoteViews1, RemoteViews param1RemoteViews2) {
      hideNormalContent(param1RemoteViews1);
      param1RemoteViews1.removeAllViews(R.id.notification_main_column);
      param1RemoteViews1.addView(R.id.notification_main_column, param1RemoteViews2.clone());
      param1RemoteViews1.setViewVisibility(R.id.notification_main_column, 0);
      if (Build.VERSION.SDK_INT >= 21)
        param1RemoteViews1.setViewPadding(R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0); 
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Bitmap createColoredBitmap(int param1Int1, int param1Int2) {
      return createColoredBitmap(param1Int1, param1Int2, 0);
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      return null;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      return null;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      return null;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected void restoreFromCompatExtras(Bundle param1Bundle) {}
    
    public void setBuilder(NotificationCompat.Builder param1Builder) {
      if (this.mBuilder != param1Builder) {
        this.mBuilder = param1Builder;
        if (this.mBuilder != null)
          this.mBuilder.setStyle(this); 
      } 
    }
  }
  
  public static final class WearableExtender implements Extender {
    private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
    
    private static final int DEFAULT_FLAGS = 1;
    
    private static final int DEFAULT_GRAVITY = 80;
    
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    
    private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
    
    private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
    
    private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
    
    private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
    
    private static final int FLAG_HINT_HIDE_ICON = 2;
    
    private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
    
    private static final int FLAG_START_SCROLL_BOTTOM = 8;
    
    private static final String KEY_ACTIONS = "actions";
    
    private static final String KEY_BACKGROUND = "background";
    
    private static final String KEY_BRIDGE_TAG = "bridgeTag";
    
    private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
    
    private static final String KEY_CONTENT_ICON = "contentIcon";
    
    private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
    
    private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
    
    private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
    
    private static final String KEY_DISMISSAL_ID = "dismissalId";
    
    private static final String KEY_DISPLAY_INTENT = "displayIntent";
    
    private static final String KEY_FLAGS = "flags";
    
    private static final String KEY_GRAVITY = "gravity";
    
    private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
    
    private static final String KEY_PAGES = "pages";
    
    public static final int SCREEN_TIMEOUT_LONG = -1;
    
    public static final int SCREEN_TIMEOUT_SHORT = 0;
    
    public static final int SIZE_DEFAULT = 0;
    
    public static final int SIZE_FULL_SCREEN = 5;
    
    public static final int SIZE_LARGE = 4;
    
    public static final int SIZE_MEDIUM = 3;
    
    public static final int SIZE_SMALL = 2;
    
    public static final int SIZE_XSMALL = 1;
    
    public static final int UNSET_ACTION_INDEX = -1;
    
    private ArrayList<NotificationCompat.Action> mActions = new ArrayList<NotificationCompat.Action>();
    
    private Bitmap mBackground;
    
    private String mBridgeTag;
    
    private int mContentActionIndex = -1;
    
    private int mContentIcon;
    
    private int mContentIconGravity = 8388613;
    
    private int mCustomContentHeight;
    
    private int mCustomSizePreset = 0;
    
    private String mDismissalId;
    
    private PendingIntent mDisplayIntent;
    
    private int mFlags = 1;
    
    private int mGravity = 80;
    
    private int mHintScreenTimeout;
    
    private ArrayList<Notification> mPages = new ArrayList<Notification>();
    
    public WearableExtender() {}
    
    public WearableExtender(Notification param1Notification) {
      Bundle bundle = NotificationCompat.getExtras(param1Notification);
      if (bundle != null) {
        bundle = bundle.getBundle("android.wearable.EXTENSIONS");
      } else {
        bundle = null;
      } 
      if (bundle != null) {
        ArrayList<Notification.Action> arrayList = bundle.getParcelableArrayList("actions");
        if (Build.VERSION.SDK_INT >= 16 && arrayList != null) {
          NotificationCompat.Action[] arrayOfAction = new NotificationCompat.Action[arrayList.size()];
          for (int i = 0; i < arrayOfAction.length; i++) {
            if (Build.VERSION.SDK_INT >= 20) {
              arrayOfAction[i] = NotificationCompat.getActionCompatFromAction(arrayList.get(i));
            } else if (Build.VERSION.SDK_INT >= 16) {
              arrayOfAction[i] = NotificationCompatJellybean.getActionFromBundle((Bundle)arrayList.get(i));
            } 
          } 
          Collections.addAll(this.mActions, arrayOfAction);
        } 
        this.mFlags = bundle.getInt("flags", 1);
        this.mDisplayIntent = (PendingIntent)bundle.getParcelable("displayIntent");
        Notification[] arrayOfNotification = NotificationCompat.getNotificationArrayFromBundle(bundle, "pages");
        if (arrayOfNotification != null)
          Collections.addAll(this.mPages, arrayOfNotification); 
        this.mBackground = (Bitmap)bundle.getParcelable("background");
        this.mContentIcon = bundle.getInt("contentIcon");
        this.mContentIconGravity = bundle.getInt("contentIconGravity", 8388613);
        this.mContentActionIndex = bundle.getInt("contentActionIndex", -1);
        this.mCustomSizePreset = bundle.getInt("customSizePreset", 0);
        this.mCustomContentHeight = bundle.getInt("customContentHeight");
        this.mGravity = bundle.getInt("gravity", 80);
        this.mHintScreenTimeout = bundle.getInt("hintScreenTimeout");
        this.mDismissalId = bundle.getString("dismissalId");
        this.mBridgeTag = bundle.getString("bridgeTag");
      } 
    }
    
    @RequiresApi(20)
    private static Notification.Action getActionFromActionCompat(NotificationCompat.Action param1Action) {
      Bundle bundle;
      Notification.Action.Builder builder = new Notification.Action.Builder(param1Action.getIcon(), param1Action.getTitle(), param1Action.getActionIntent());
      if (param1Action.getExtras() != null) {
        bundle = new Bundle(param1Action.getExtras());
      } else {
        bundle = new Bundle();
      } 
      bundle.putBoolean("android.support.allowGeneratedReplies", param1Action.getAllowGeneratedReplies());
      if (Build.VERSION.SDK_INT >= 24)
        builder.setAllowGeneratedReplies(param1Action.getAllowGeneratedReplies()); 
      builder.addExtras(bundle);
      RemoteInput[] arrayOfRemoteInput = param1Action.getRemoteInputs();
      if (arrayOfRemoteInput != null) {
        RemoteInput[] arrayOfRemoteInput1 = RemoteInput.fromCompat(arrayOfRemoteInput);
        int j = arrayOfRemoteInput1.length;
        for (int i = 0; i < j; i++)
          builder.addRemoteInput(arrayOfRemoteInput1[i]); 
      } 
      return builder.build();
    }
    
    private void setFlag(int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        this.mFlags |= param1Int;
        return;
      } 
      this.mFlags &= param1Int ^ 0xFFFFFFFF;
    }
    
    public WearableExtender addAction(NotificationCompat.Action param1Action) {
      this.mActions.add(param1Action);
      return this;
    }
    
    public WearableExtender addActions(List<NotificationCompat.Action> param1List) {
      this.mActions.addAll(param1List);
      return this;
    }
    
    public WearableExtender addPage(Notification param1Notification) {
      this.mPages.add(param1Notification);
      return this;
    }
    
    public WearableExtender addPages(List<Notification> param1List) {
      this.mPages.addAll(param1List);
      return this;
    }
    
    public WearableExtender clearActions() {
      this.mActions.clear();
      return this;
    }
    
    public WearableExtender clearPages() {
      this.mPages.clear();
      return this;
    }
    
    public WearableExtender clone() {
      WearableExtender wearableExtender = new WearableExtender();
      wearableExtender.mActions = new ArrayList<NotificationCompat.Action>(this.mActions);
      wearableExtender.mFlags = this.mFlags;
      wearableExtender.mDisplayIntent = this.mDisplayIntent;
      wearableExtender.mPages = new ArrayList<Notification>(this.mPages);
      wearableExtender.mBackground = this.mBackground;
      wearableExtender.mContentIcon = this.mContentIcon;
      wearableExtender.mContentIconGravity = this.mContentIconGravity;
      wearableExtender.mContentActionIndex = this.mContentActionIndex;
      wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
      wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
      wearableExtender.mGravity = this.mGravity;
      wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
      wearableExtender.mDismissalId = this.mDismissalId;
      wearableExtender.mBridgeTag = this.mBridgeTag;
      return wearableExtender;
    }
    
    public NotificationCompat.Builder extend(NotificationCompat.Builder param1Builder) {
      Bundle bundle = new Bundle();
      if (!this.mActions.isEmpty())
        if (Build.VERSION.SDK_INT >= 16) {
          ArrayList<Notification.Action> arrayList = new ArrayList(this.mActions.size());
          for (NotificationCompat.Action action : this.mActions) {
            if (Build.VERSION.SDK_INT >= 20) {
              arrayList.add(getActionFromActionCompat(action));
              continue;
            } 
            if (Build.VERSION.SDK_INT >= 16)
              arrayList.add(NotificationCompatJellybean.getBundleForAction(action)); 
          } 
          bundle.putParcelableArrayList("actions", arrayList);
        } else {
          bundle.putParcelableArrayList("actions", null);
        }  
      if (this.mFlags != 1)
        bundle.putInt("flags", this.mFlags); 
      if (this.mDisplayIntent != null)
        bundle.putParcelable("displayIntent", (Parcelable)this.mDisplayIntent); 
      if (!this.mPages.isEmpty())
        bundle.putParcelableArray("pages", (Parcelable[])this.mPages.toArray((Object[])new Notification[this.mPages.size()])); 
      if (this.mBackground != null)
        bundle.putParcelable("background", (Parcelable)this.mBackground); 
      if (this.mContentIcon != 0)
        bundle.putInt("contentIcon", this.mContentIcon); 
      if (this.mContentIconGravity != 8388613)
        bundle.putInt("contentIconGravity", this.mContentIconGravity); 
      if (this.mContentActionIndex != -1)
        bundle.putInt("contentActionIndex", this.mContentActionIndex); 
      if (this.mCustomSizePreset != 0)
        bundle.putInt("customSizePreset", this.mCustomSizePreset); 
      if (this.mCustomContentHeight != 0)
        bundle.putInt("customContentHeight", this.mCustomContentHeight); 
      if (this.mGravity != 80)
        bundle.putInt("gravity", this.mGravity); 
      if (this.mHintScreenTimeout != 0)
        bundle.putInt("hintScreenTimeout", this.mHintScreenTimeout); 
      if (this.mDismissalId != null)
        bundle.putString("dismissalId", this.mDismissalId); 
      if (this.mBridgeTag != null)
        bundle.putString("bridgeTag", this.mBridgeTag); 
      param1Builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
      return param1Builder;
    }
    
    public List<NotificationCompat.Action> getActions() {
      return this.mActions;
    }
    
    public Bitmap getBackground() {
      return this.mBackground;
    }
    
    public String getBridgeTag() {
      return this.mBridgeTag;
    }
    
    public int getContentAction() {
      return this.mContentActionIndex;
    }
    
    @Deprecated
    public int getContentIcon() {
      return this.mContentIcon;
    }
    
    @Deprecated
    public int getContentIconGravity() {
      return this.mContentIconGravity;
    }
    
    public boolean getContentIntentAvailableOffline() {
      return ((this.mFlags & 0x1) != 0);
    }
    
    @Deprecated
    public int getCustomContentHeight() {
      return this.mCustomContentHeight;
    }
    
    @Deprecated
    public int getCustomSizePreset() {
      return this.mCustomSizePreset;
    }
    
    public String getDismissalId() {
      return this.mDismissalId;
    }
    
    public PendingIntent getDisplayIntent() {
      return this.mDisplayIntent;
    }
    
    @Deprecated
    public int getGravity() {
      return this.mGravity;
    }
    
    public boolean getHintAmbientBigPicture() {
      return ((this.mFlags & 0x20) != 0);
    }
    
    @Deprecated
    public boolean getHintAvoidBackgroundClipping() {
      return ((this.mFlags & 0x10) != 0);
    }
    
    public boolean getHintContentIntentLaunchesActivity() {
      return ((this.mFlags & 0x40) != 0);
    }
    
    @Deprecated
    public boolean getHintHideIcon() {
      return ((this.mFlags & 0x2) != 0);
    }
    
    @Deprecated
    public int getHintScreenTimeout() {
      return this.mHintScreenTimeout;
    }
    
    @Deprecated
    public boolean getHintShowBackgroundOnly() {
      return ((this.mFlags & 0x4) != 0);
    }
    
    public List<Notification> getPages() {
      return this.mPages;
    }
    
    public boolean getStartScrollBottom() {
      return ((this.mFlags & 0x8) != 0);
    }
    
    public WearableExtender setBackground(Bitmap param1Bitmap) {
      this.mBackground = param1Bitmap;
      return this;
    }
    
    public WearableExtender setBridgeTag(String param1String) {
      this.mBridgeTag = param1String;
      return this;
    }
    
    public WearableExtender setContentAction(int param1Int) {
      this.mContentActionIndex = param1Int;
      return this;
    }
    
    @Deprecated
    public WearableExtender setContentIcon(int param1Int) {
      this.mContentIcon = param1Int;
      return this;
    }
    
    @Deprecated
    public WearableExtender setContentIconGravity(int param1Int) {
      this.mContentIconGravity = param1Int;
      return this;
    }
    
    public WearableExtender setContentIntentAvailableOffline(boolean param1Boolean) {
      setFlag(1, param1Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setCustomContentHeight(int param1Int) {
      this.mCustomContentHeight = param1Int;
      return this;
    }
    
    @Deprecated
    public WearableExtender setCustomSizePreset(int param1Int) {
      this.mCustomSizePreset = param1Int;
      return this;
    }
    
    public WearableExtender setDismissalId(String param1String) {
      this.mDismissalId = param1String;
      return this;
    }
    
    public WearableExtender setDisplayIntent(PendingIntent param1PendingIntent) {
      this.mDisplayIntent = param1PendingIntent;
      return this;
    }
    
    @Deprecated
    public WearableExtender setGravity(int param1Int) {
      this.mGravity = param1Int;
      return this;
    }
    
    public WearableExtender setHintAmbientBigPicture(boolean param1Boolean) {
      setFlag(32, param1Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setHintAvoidBackgroundClipping(boolean param1Boolean) {
      setFlag(16, param1Boolean);
      return this;
    }
    
    public WearableExtender setHintContentIntentLaunchesActivity(boolean param1Boolean) {
      setFlag(64, param1Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setHintHideIcon(boolean param1Boolean) {
      setFlag(2, param1Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setHintScreenTimeout(int param1Int) {
      this.mHintScreenTimeout = param1Int;
      return this;
    }
    
    @Deprecated
    public WearableExtender setHintShowBackgroundOnly(boolean param1Boolean) {
      setFlag(4, param1Boolean);
      return this;
    }
    
    public WearableExtender setStartScrollBottom(boolean param1Boolean) {
      setFlag(8, param1Boolean);
      return this;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/app/NotificationCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
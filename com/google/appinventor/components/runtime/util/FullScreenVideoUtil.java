package com.google.appinventor.components.runtime.util;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.VideoView;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.VideoPlayer;
import com.google.appinventor.components.runtime.errors.PermissionException;
import java.io.IOException;

public class FullScreenVideoUtil implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
  public static final String ACTION_DATA = "ActionData";
  
  public static final String ACTION_SUCESS = "ActionSuccess";
  
  public static final int FULLSCREEN_VIDEO_ACTION_DURATION = 196;
  
  public static final int FULLSCREEN_VIDEO_ACTION_FULLSCREEN = 195;
  
  public static final int FULLSCREEN_VIDEO_ACTION_PAUSE = 192;
  
  public static final int FULLSCREEN_VIDEO_ACTION_PLAY = 191;
  
  public static final int FULLSCREEN_VIDEO_ACTION_SEEK = 190;
  
  public static final int FULLSCREEN_VIDEO_ACTION_SOURCE = 194;
  
  public static final int FULLSCREEN_VIDEO_ACTION_STOP = 193;
  
  public static final int FULLSCREEN_VIDEO_DIALOG_FLAG = 189;
  
  public static final String VIDEOPLAYER_FULLSCREEN = "FullScreenKey";
  
  public static final String VIDEOPLAYER_PLAYING = "PlayingKey";
  
  public static final String VIDEOPLAYER_POSITION = "PositionKey";
  
  public static final String VIDEOPLAYER_SOURCE = "SourceKey";
  
  private Form mForm;
  
  private VideoPlayer mFullScreenPlayer = null;
  
  private Bundle mFullScreenVideoBundle;
  
  private CustomMediaController mFullScreenVideoController;
  
  private Dialog mFullScreenVideoDialog;
  
  private FrameLayout mFullScreenVideoHolder;
  
  private VideoView mFullScreenVideoView;
  
  private Handler mHandler;
  
  private FrameLayout.LayoutParams mMediaControllerParams = new FrameLayout.LayoutParams(-1, -2, 80);
  
  public FullScreenVideoUtil(Form paramForm, Handler paramHandler) {
    this.mForm = paramForm;
    this.mHandler = paramHandler;
    this.mFullScreenVideoDialog = new Dialog((Context)this.mForm, 16973831) {
        public void onBackPressed() {
          Bundle bundle = new Bundle();
          bundle.putInt("PositionKey", FullScreenVideoUtil.this.mFullScreenVideoView.getCurrentPosition());
          bundle.putBoolean("PlayingKey", FullScreenVideoUtil.this.mFullScreenVideoView.isPlaying());
          bundle.putString("SourceKey", FullScreenVideoUtil.this.mFullScreenVideoBundle.getString("SourceKey"));
          FullScreenVideoUtil.this.mFullScreenPlayer.fullScreenKilled(bundle);
          super.onBackPressed();
        }
        
        public void onStart() {
          super.onStart();
          FullScreenVideoUtil.this.startDialog();
        }
      };
  }
  
  private Bundle doFullScreenVideoAction(VideoPlayer paramVideoPlayer, Bundle paramBundle) {
    Log.i("Form.doFullScreenVideoAction", "Source:" + paramVideoPlayer + " Data:" + paramBundle);
    Bundle bundle = new Bundle();
    bundle.putBoolean("ActionSuccess", true);
    if (paramBundle.getBoolean("FullScreenKey") == true) {
      this.mFullScreenPlayer = paramVideoPlayer;
      this.mFullScreenVideoBundle = paramBundle;
      if (!this.mFullScreenVideoDialog.isShowing()) {
        this.mForm.showDialog(189);
        return bundle;
      } 
      this.mFullScreenVideoView.pause();
      bundle.putBoolean("ActionSuccess", setSource(this.mFullScreenVideoBundle.getString("SourceKey"), false));
      return bundle;
    } 
    if (showing()) {
      bundle.putBoolean("PlayingKey", this.mFullScreenVideoView.isPlaying());
      bundle.putInt("PositionKey", this.mFullScreenVideoView.getCurrentPosition());
      bundle.putString("SourceKey", this.mFullScreenVideoBundle.getString("SourceKey"));
      this.mFullScreenPlayer = null;
      this.mFullScreenVideoBundle = null;
      this.mForm.dismissDialog(189);
      return bundle;
    } 
    bundle.putBoolean("ActionSuccess", false);
    return bundle;
  }
  
  public Dialog createFullScreenVideoDialog() {
    if (this.mFullScreenVideoBundle == null)
      Log.i("Form.createFullScreenVideoDialog", "mFullScreenVideoBundle is null"); 
    this.mFullScreenVideoView = new VideoView((Context)this.mForm);
    this.mFullScreenVideoHolder = new FrameLayout((Context)this.mForm);
    this.mFullScreenVideoController = new CustomMediaController((Context)this.mForm);
    this.mFullScreenVideoView.setId(this.mFullScreenVideoView.hashCode());
    this.mFullScreenVideoHolder.setId(this.mFullScreenVideoHolder.hashCode());
    this.mFullScreenVideoView.setMediaController(this.mFullScreenVideoController);
    this.mFullScreenVideoView.setOnTouchListener(new View.OnTouchListener() {
          public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
            Log.i("FullScreenVideoUtil..onTouch", "Video Touched!!");
            return false;
          }
        });
    this.mFullScreenVideoController.setAnchorView((View)this.mFullScreenVideoView);
    String str = this.mForm.ScreenOrientation();
    if (str.equals("landscape") || str.equals("sensorLandscape") || str.equals("reverseLandscape")) {
      this.mFullScreenVideoView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -1, 17));
      this.mFullScreenVideoHolder.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
      this.mFullScreenVideoHolder.addView((View)this.mFullScreenVideoView);
      this.mFullScreenVideoController.addTo((ViewGroup)this.mFullScreenVideoHolder, (ViewGroup.LayoutParams)this.mMediaControllerParams);
      this.mFullScreenVideoDialog.setContentView((View)this.mFullScreenVideoHolder);
      return this.mFullScreenVideoDialog;
    } 
    this.mFullScreenVideoView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -2, 17));
    this.mFullScreenVideoHolder.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    this.mFullScreenVideoHolder.addView((View)this.mFullScreenVideoView);
    this.mFullScreenVideoController.addTo((ViewGroup)this.mFullScreenVideoHolder, (ViewGroup.LayoutParams)this.mMediaControllerParams);
    this.mFullScreenVideoDialog.setContentView((View)this.mFullScreenVideoHolder);
    return this.mFullScreenVideoDialog;
  }
  
  public boolean dialogInitialized() {
    return (this.mFullScreenVideoDialog != null);
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer) {
    if (this.mFullScreenPlayer != null)
      this.mFullScreenPlayer.Completed(); 
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer) {
    Log.i("FullScreenVideoUtil..onPrepared", "Seeking to:" + this.mFullScreenVideoBundle.getInt("PositionKey"));
    this.mFullScreenVideoView.seekTo(this.mFullScreenVideoBundle.getInt("PositionKey"));
    if (this.mFullScreenVideoBundle.getBoolean("PlayingKey")) {
      this.mFullScreenVideoView.start();
      return;
    } 
    this.mFullScreenVideoView.start();
    this.mHandler.postDelayed(new Runnable() {
          public void run() {
            FullScreenVideoUtil.this.mFullScreenVideoView.pause();
          }
        },  100L);
  }
  
  public Bundle performAction(int paramInt, VideoPlayer paramVideoPlayer, Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 'Form.fullScreenVideoAction'
    //   5: new java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial <init> : ()V
    //   12: ldc_w 'Actions:'
    //   15: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: iload_1
    //   19: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   22: ldc_w ' Source:'
    //   25: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: aload_2
    //   29: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   32: ldc_w ': Current Source:'
    //   35: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_0
    //   39: getfield mFullScreenPlayer : Lcom/google/appinventor/components/runtime/VideoPlayer;
    //   42: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   45: ldc ' Data:'
    //   47: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: aload_3
    //   51: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   54: invokevirtual toString : ()Ljava/lang/String;
    //   57: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   60: pop
    //   61: new android/os/Bundle
    //   64: dup
    //   65: invokespecial <init> : ()V
    //   68: astore #4
    //   70: aload #4
    //   72: ldc 'ActionSuccess'
    //   74: iconst_1
    //   75: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   78: aload_2
    //   79: aload_0
    //   80: getfield mFullScreenPlayer : Lcom/google/appinventor/components/runtime/VideoPlayer;
    //   83: if_acmpne -> 388
    //   86: iload_1
    //   87: tableswitch default -> 484, 190 -> 229, 191 -> 195, 192 -> 156, 193 -> 270, 194 -> 304, 195 -> 143, 196 -> 347
    //   128: aload #4
    //   130: ldc 'ActionSuccess'
    //   132: iconst_0
    //   133: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   136: aload #4
    //   138: astore_2
    //   139: aload_0
    //   140: monitorexit
    //   141: aload_2
    //   142: areturn
    //   143: aload_0
    //   144: aload_2
    //   145: aload_3
    //   146: checkcast android/os/Bundle
    //   149: invokespecial doFullScreenVideoAction : (Lcom/google/appinventor/components/runtime/VideoPlayer;Landroid/os/Bundle;)Landroid/os/Bundle;
    //   152: astore_2
    //   153: goto -> 139
    //   156: aload_0
    //   157: invokevirtual showing : ()Z
    //   160: ifeq -> 181
    //   163: aload_0
    //   164: getfield mFullScreenVideoView : Landroid/widget/VideoView;
    //   167: invokevirtual pause : ()V
    //   170: aload #4
    //   172: astore_2
    //   173: goto -> 139
    //   176: astore_2
    //   177: aload_0
    //   178: monitorexit
    //   179: aload_2
    //   180: athrow
    //   181: aload #4
    //   183: ldc 'ActionSuccess'
    //   185: iconst_0
    //   186: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   189: aload #4
    //   191: astore_2
    //   192: goto -> 139
    //   195: aload_0
    //   196: invokevirtual showing : ()Z
    //   199: ifeq -> 215
    //   202: aload_0
    //   203: getfield mFullScreenVideoView : Landroid/widget/VideoView;
    //   206: invokevirtual start : ()V
    //   209: aload #4
    //   211: astore_2
    //   212: goto -> 139
    //   215: aload #4
    //   217: ldc 'ActionSuccess'
    //   219: iconst_0
    //   220: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   223: aload #4
    //   225: astore_2
    //   226: goto -> 139
    //   229: aload_0
    //   230: invokevirtual showing : ()Z
    //   233: ifeq -> 256
    //   236: aload_0
    //   237: getfield mFullScreenVideoView : Landroid/widget/VideoView;
    //   240: aload_3
    //   241: checkcast java/lang/Integer
    //   244: invokevirtual intValue : ()I
    //   247: invokevirtual seekTo : (I)V
    //   250: aload #4
    //   252: astore_2
    //   253: goto -> 139
    //   256: aload #4
    //   258: ldc 'ActionSuccess'
    //   260: iconst_0
    //   261: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   264: aload #4
    //   266: astore_2
    //   267: goto -> 139
    //   270: aload_0
    //   271: invokevirtual showing : ()Z
    //   274: ifeq -> 290
    //   277: aload_0
    //   278: getfield mFullScreenVideoView : Landroid/widget/VideoView;
    //   281: invokevirtual stopPlayback : ()V
    //   284: aload #4
    //   286: astore_2
    //   287: goto -> 139
    //   290: aload #4
    //   292: ldc 'ActionSuccess'
    //   294: iconst_0
    //   295: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   298: aload #4
    //   300: astore_2
    //   301: goto -> 139
    //   304: aload_0
    //   305: invokevirtual showing : ()Z
    //   308: ifeq -> 333
    //   311: aload #4
    //   313: ldc 'ActionSuccess'
    //   315: aload_0
    //   316: aload_3
    //   317: checkcast java/lang/String
    //   320: iconst_1
    //   321: invokevirtual setSource : (Ljava/lang/String;Z)Z
    //   324: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   327: aload #4
    //   329: astore_2
    //   330: goto -> 139
    //   333: aload #4
    //   335: ldc 'ActionSuccess'
    //   337: iconst_0
    //   338: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   341: aload #4
    //   343: astore_2
    //   344: goto -> 139
    //   347: aload_0
    //   348: invokevirtual showing : ()Z
    //   351: ifeq -> 374
    //   354: aload #4
    //   356: ldc 'ActionData'
    //   358: aload_0
    //   359: getfield mFullScreenVideoView : Landroid/widget/VideoView;
    //   362: invokevirtual getDuration : ()I
    //   365: invokevirtual putInt : (Ljava/lang/String;I)V
    //   368: aload #4
    //   370: astore_2
    //   371: goto -> 139
    //   374: aload #4
    //   376: ldc 'ActionSuccess'
    //   378: iconst_0
    //   379: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   382: aload #4
    //   384: astore_2
    //   385: goto -> 139
    //   388: iload_1
    //   389: sipush #195
    //   392: if_icmpne -> 128
    //   395: aload_0
    //   396: invokevirtual showing : ()Z
    //   399: ifeq -> 471
    //   402: aload_0
    //   403: getfield mFullScreenPlayer : Lcom/google/appinventor/components/runtime/VideoPlayer;
    //   406: ifnull -> 471
    //   409: new android/os/Bundle
    //   412: dup
    //   413: invokespecial <init> : ()V
    //   416: astore #4
    //   418: aload #4
    //   420: ldc 'PositionKey'
    //   422: aload_0
    //   423: getfield mFullScreenVideoView : Landroid/widget/VideoView;
    //   426: invokevirtual getCurrentPosition : ()I
    //   429: invokevirtual putInt : (Ljava/lang/String;I)V
    //   432: aload #4
    //   434: ldc 'PlayingKey'
    //   436: aload_0
    //   437: getfield mFullScreenVideoView : Landroid/widget/VideoView;
    //   440: invokevirtual isPlaying : ()Z
    //   443: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   446: aload #4
    //   448: ldc 'SourceKey'
    //   450: aload_0
    //   451: getfield mFullScreenVideoBundle : Landroid/os/Bundle;
    //   454: ldc 'SourceKey'
    //   456: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   459: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   462: aload_0
    //   463: getfield mFullScreenPlayer : Lcom/google/appinventor/components/runtime/VideoPlayer;
    //   466: aload #4
    //   468: invokevirtual fullScreenKilled : (Landroid/os/Bundle;)V
    //   471: aload_0
    //   472: aload_2
    //   473: aload_3
    //   474: checkcast android/os/Bundle
    //   477: invokespecial doFullScreenVideoAction : (Lcom/google/appinventor/components/runtime/VideoPlayer;Landroid/os/Bundle;)Landroid/os/Bundle;
    //   480: astore_2
    //   481: goto -> 139
    //   484: goto -> 128
    // Exception table:
    //   from	to	target	type
    //   2	86	176	finally
    //   128	136	176	finally
    //   143	153	176	finally
    //   156	170	176	finally
    //   181	189	176	finally
    //   195	209	176	finally
    //   215	223	176	finally
    //   229	250	176	finally
    //   256	264	176	finally
    //   270	284	176	finally
    //   290	298	176	finally
    //   304	327	176	finally
    //   333	341	176	finally
    //   347	368	176	finally
    //   374	382	176	finally
    //   395	471	176	finally
    //   471	481	176	finally
  }
  
  public void prepareFullScreenVideoDialog(Dialog paramDialog) {
    this.mFullScreenVideoView.setOnPreparedListener(this);
    this.mFullScreenVideoView.setOnCompletionListener(this);
  }
  
  public boolean setSource(String paramString, boolean paramBoolean) {
    if (paramBoolean)
      try {
        this.mFullScreenVideoBundle.putInt("PositionKey", 0);
        MediaUtil.loadVideoView(this.mFullScreenVideoView, this.mForm, paramString);
        this.mFullScreenVideoBundle.putString("SourceKey", paramString);
        return true;
      } catch (PermissionException permissionException) {
        this.mForm.dispatchPermissionDeniedEvent((Component)this.mFullScreenPlayer, "Source", permissionException);
        return false;
      } catch (IOException iOException) {
        this.mForm.dispatchErrorOccurredEvent((Component)this.mFullScreenPlayer, "Source", 701, new Object[] { permissionException });
        return false;
      }  
    MediaUtil.loadVideoView(this.mFullScreenVideoView, this.mForm, (String)permissionException);
    this.mFullScreenVideoBundle.putString("SourceKey", (String)permissionException);
    return true;
  }
  
  public boolean showing() {
    return (dialogInitialized() && this.mFullScreenVideoDialog.isShowing());
  }
  
  public void startDialog() {
    try {
      MediaUtil.loadVideoView(this.mFullScreenVideoView, this.mForm, this.mFullScreenVideoBundle.getString("SourceKey"));
      return;
    } catch (PermissionException permissionException) {
      this.mForm.dispatchPermissionDeniedEvent((Component)this.mFullScreenPlayer, "Source", permissionException);
      return;
    } catch (IOException iOException) {
      this.mForm.dispatchErrorOccurredEvent((Component)this.mFullScreenPlayer, "Source", 701, new Object[] { this.mFullScreenVideoBundle.getString("SourceKey") });
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/FullScreenVideoUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
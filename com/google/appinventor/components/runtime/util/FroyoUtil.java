package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.media.AudioManager;
import android.view.Display;
import android.webkit.WebViewClient;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.Player;

public class FroyoUtil {
  public static void abandonFocus(AudioManager paramAudioManager, Object paramObject) {
    paramAudioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener)paramObject);
  }
  
  public static boolean focusRequestGranted(AudioManager paramAudioManager, Object paramObject) {
    return (paramAudioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener)paramObject, 3, 1) == 1);
  }
  
  public static int getRotation(Display paramDisplay) {
    return paramDisplay.getRotation();
  }
  
  public static WebViewClient getWebViewClient(boolean paramBoolean1, boolean paramBoolean2, Form paramForm, Component paramComponent) {
    return new FroyoWebViewClient<Component>(paramBoolean2, paramBoolean1, paramForm, paramComponent);
  }
  
  public static Object setAudioFocusChangeListener(final Player player) {
    return new AudioManager.OnAudioFocusChangeListener() {
        private boolean playbackFlag = false;
        
        public void onAudioFocusChange(int param1Int) {
          switch (param1Int) {
            default:
              return;
            case -3:
            case -2:
              if (player != null && player.playerState == Player.State.PLAYING) {
                player.pause();
                this.playbackFlag = true;
                return;
              } 
            case -1:
              this.playbackFlag = false;
              player.OtherPlayerStarted();
              return;
            case 1:
              break;
          } 
          if (player != null && this.playbackFlag && player.playerState == Player.State.PAUSED_BY_EVENT) {
            player.Start();
            this.playbackFlag = false;
            return;
          } 
        }
      };
  }
  
  public static AudioManager setAudioManager(Activity paramActivity) {
    return (AudioManager)paramActivity.getSystemService("audio");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/FroyoUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package com.google.appinventor.components.runtime.util;

import android.os.Handler;

public class AsynchUtil {
  public static void runAsynchronously(final Handler androidUIHandler, final Runnable call, final Runnable callback) {
    (new Thread(new Runnable() {
          public void run() {
            call.run();
            if (callback != null)
              androidUIHandler.post(new Runnable() {
                    public void run() {
                      callback.run();
                    }
                  }); 
          }
        })).start();
  }
  
  public static void runAsynchronously(Runnable paramRunnable) {
    (new Thread(paramRunnable)).start();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/AsynchUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
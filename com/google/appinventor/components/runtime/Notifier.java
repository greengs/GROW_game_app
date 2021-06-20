package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.SdkLevel;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "The Notifier component displays alert dialogs, messages, and temporary alerts, and creates Android log entries through the following methods: <ul><li> ShowMessageDialog: displays a message which the user must dismiss by pressing a button.</li><li> ShowChooseDialog: displays a message two buttons to let the user choose one of two responses, for example, yes or no, after which the AfterChoosing event is raised.</li><li> ShowTextDialog: lets the user enter text in response to the message, after which the AfterTextInput event is raised. <li> ShowPasswordDialog: lets the user enter password in response to the message, after which the AfterTextInput event is raised. <li> ShowAlert: displays a temporary  alert that goes away by itself after a short time.</li><li> ShowProgressDialog: displays an alert with a loading spinner that cannot be dismissed by the user. It can only be dismissed by using the DismissProgressDialog block.</li><li> DismissProgressDialog: Dismisses the progress dialog displayed by ShowProgressDialog.</li><li> LogError: logs an error message to the Android log. </li><li> LogInfo: logs an info message to the Android log.</li><li> LogWarning: logs a warning message to the Android log.</li><li>The messages in the dialogs (but not the alert) can be formatted using the following HTML tags:&lt;b&gt;, &lt;big&gt;, &lt;blockquote&gt;, &lt;br&gt;, &lt;cite&gt;, &lt;dfn&gt;, &lt;div&gt;, &lt;em&gt;, &lt;small&gt;, &lt;strong&gt;, &lt;sub&gt;, &lt;sup&gt;, &lt;tt&gt;. &lt;u&gt;</li><li>You can also use the font tag to specify color, for example, &lt;font color=\"blue\"&gt;.  Some of the available color names are aqua, black, blue, fuchsia, green, grey, lime, maroon, navy, olive, purple, red, silver, teal, white, and yellow</li></ul>", iconName = "images/notifier.png", nonVisible = true, version = 6)
@SimpleObject
public final class Notifier extends AndroidNonvisibleComponent implements Component {
  private static final String LOG_TAG = "Notifier";
  
  private final Activity activity;
  
  private int backgroundColor = -12303292;
  
  private final Handler handler;
  
  private int notifierLength = 1;
  
  private ProgressDialog progressDialog;
  
  private int textColor = -1;
  
  public Notifier(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.activity = paramComponentContainer.$context();
    this.handler = new Handler();
    this.progressDialog = null;
  }
  
  public static void oneButtonAlert(Activity paramActivity, String paramString1, String paramString2, String paramString3) {
    oneButtonAlert(paramActivity, paramString1, paramString2, paramString3, null);
  }
  
  public static void oneButtonAlert(Activity paramActivity, String paramString1, String paramString2, String paramString3, final Runnable callBack) {
    Log.i("Notifier", "One button alert " + paramString1);
    AlertDialog alertDialog = (new AlertDialog.Builder((Context)paramActivity)).create();
    alertDialog.setTitle(paramString2);
    alertDialog.setCancelable(false);
    alertDialog.setMessage((CharSequence)stringToHTML(paramString1));
    alertDialog.setButton(-3, paramString3, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            if (callBack != null)
              callBack.run(); 
          }
        });
    alertDialog.show();
  }
  
  private static SpannableString stringToHTML(String paramString) {
    return new SpannableString((CharSequence)Html.fromHtml(paramString));
  }
  
  private void textInputDialog(String paramString1, final String cancelButtonText, boolean paramBoolean1, boolean paramBoolean2) {
    AlertDialog alertDialog = (new AlertDialog.Builder((Context)this.activity)).create();
    alertDialog.setTitle(cancelButtonText);
    alertDialog.setMessage((CharSequence)stringToHTML(paramString1));
    final EditText input = new EditText((Context)this.activity);
    if (paramBoolean2)
      editText.setInputType(129); 
    alertDialog.setView((View)editText);
    alertDialog.setCancelable(false);
    alertDialog.setButton(-1, "OK", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            Notifier.this.HideKeyboard((View)input);
            Notifier.this.AfterTextInput(input.getText().toString());
          }
        });
    if (paramBoolean1) {
      cancelButtonText = this.activity.getString(17039360);
      alertDialog.setButton(-2, cancelButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
              Notifier.this.HideKeyboard((View)input);
              Notifier.this.TextInputCanceled();
              Notifier.this.AfterTextInput(cancelButtonText);
            }
          });
    } 
    alertDialog.show();
  }
  
  private void toastNow(String paramString) {
    byte b;
    if (SdkLevel.getLevel() >= 14) {
      b = 22;
    } else {
      b = 15;
    } 
    Toast toast = Toast.makeText((Context)this.activity, paramString, this.notifierLength);
    toast.setGravity(17, toast.getXOffset() / 2, toast.getYOffset() / 2);
    TextView textView = new TextView((Context)this.activity);
    textView.setBackgroundColor(this.backgroundColor);
    textView.setTextColor(this.textColor);
    textView.setTextSize(b);
    textView.setTypeface(Typeface.create(Typeface.SANS_SERIF, 0));
    textView.setPadding(10, 10, 10, 10);
    textView.setText(paramString + " ");
    toast.setView((View)textView);
    toast.show();
  }
  
  public static void twoButtonDialog(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, final Runnable positiveAction, final Runnable negativeAction, final Runnable cancelAction) {
    Log.i("Notifier", "ShowChooseDialog: " + paramString1);
    AlertDialog alertDialog = (new AlertDialog.Builder((Context)paramActivity)).create();
    alertDialog.setTitle(paramString2);
    alertDialog.setCancelable(false);
    alertDialog.setMessage((CharSequence)stringToHTML(paramString1));
    alertDialog.setButton(-1, paramString3, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            positiveAction.run();
          }
        });
    alertDialog.setButton(-3, paramString4, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            negativeAction.run();
          }
        });
    if (paramBoolean)
      alertDialog.setButton(-2, paramActivity.getString(17039360), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
              cancelAction.run();
            }
          }); 
    alertDialog.show();
  }
  
  @SimpleEvent(description = "Event after the user has made a selection for ShowChooseDialog.")
  public void AfterChoosing(String paramString) {
    EventDispatcher.dispatchEvent(this, "AfterChoosing", new Object[] { paramString });
  }
  
  @SimpleEvent(description = "Event raised after the user has responded to ShowTextDialog.")
  public void AfterTextInput(String paramString) {
    EventDispatcher.dispatchEvent(this, "AfterTextInput", new Object[] { paramString });
  }
  
  @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
  @SimpleProperty(description = "Specifies the background color for alerts (not dialogs).")
  public void BackgroundColor(int paramInt) {
    this.backgroundColor = paramInt;
  }
  
  @SimpleEvent(description = "Event raised when the user canceled ShowChooseDialog.")
  public void ChoosingCanceled() {
    EventDispatcher.dispatchEvent(this, "ChoosingCanceled", new Object[0]);
  }
  
  @SimpleFunction(description = "Dismiss a previously displayed ProgressDialog box")
  public void DismissProgressDialog() {
    if (this.progressDialog != null) {
      this.progressDialog.dismiss();
      this.progressDialog = null;
    } 
  }
  
  public void HideKeyboard(View paramView) {
    if (paramView != null)
      ((InputMethodManager)this.activity.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0); 
  }
  
  @SimpleFunction(description = "Writes an error message to the Android system log. See the Google Android documentation for how to access the log.")
  public void LogError(String paramString) {
    Log.e("Notifier", paramString);
  }
  
  @SimpleFunction(description = "Writes an information message to the Android log.")
  public void LogInfo(String paramString) {
    Log.i("Notifier", paramString);
  }
  
  @SimpleFunction(description = "Writes a warning message to the Android log. See the Google Android documentation for how to access the log.")
  public void LogWarning(String paramString) {
    Log.w("Notifier", paramString);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies the length of time that the alert is shown -- either \"short\" or \"long\".")
  public int NotifierLength() {
    return this.notifierLength;
  }
  
  @DesignerProperty(defaultValue = "1", editorType = "toast_length")
  @SimpleProperty(userVisible = false)
  public void NotifierLength(int paramInt) {
    this.notifierLength = paramInt;
  }
  
  @SimpleFunction
  public void ShowAlert(final String notice) {
    this.handler.post(new Runnable() {
          public void run() {
            Notifier.this.toastNow(notice);
          }
        });
  }
  
  @SimpleFunction(description = "Shows a dialog box with two buttons, from which the user can choose.  If cancelable is true there will be an additional CANCEL button. Pressing a button will raise the AfterChoosing event.  The \"choice\" parameter to AfterChoosing will be the text on the button that was pressed, or \"Cancel\" if the  CANCEL button was pressed.")
  public void ShowChooseDialog(String paramString1, String paramString2, final String button1Text, final String button2Text, boolean paramBoolean) {
    twoButtonDialog(this.activity, paramString1, paramString2, button1Text, button2Text, paramBoolean, new Runnable() {
          public void run() {
            Notifier.this.AfterChoosing(button1Text);
          }
        },  new Runnable() {
          public void run() {
            Notifier.this.AfterChoosing(button2Text);
          }
        },  new Runnable() {
          public void run() {
            Notifier.this.ChoosingCanceled();
            Notifier.this.AfterChoosing(Notifier.this.activity.getString(17039360));
          }
        });
  }
  
  @SimpleFunction
  public void ShowMessageDialog(String paramString1, String paramString2, String paramString3) {
    oneButtonAlert(this.activity, paramString1, paramString2, paramString3);
  }
  
  @SimpleFunction(description = "Shows a dialog box where the user can enter password (input is masked), after which the AfterTextInput event will be raised.  If cancelable is true there will be an additional CANCEL button. Entering password will raise the AfterTextInput event.  The \"response\" parameter to AfterTextInput will be the entered password, or \"Cancel\" if CANCEL button was pressed.")
  public void ShowPasswordDialog(String paramString1, String paramString2, boolean paramBoolean) {
    textInputDialog(paramString1, paramString2, paramBoolean, true);
  }
  
  @SimpleFunction(description = "Shows a dialog box with an optional title and message (use empty strings if they are not wanted). This dialog box contains a spinning artifact to indicate that the program is working. It cannot be canceled by the user but must be dismissed by the App Inventor Program by using the DismissProgressDialog block.")
  public void ShowProgressDialog(String paramString1, String paramString2) {
    progressDialog(paramString1, paramString2);
  }
  
  @SimpleFunction(description = "Shows a dialog box where the user can enter text, after which the AfterTextInput event will be raised.  If cancelable is true there will be an additional CANCEL button. Entering text will raise the AfterTextInput event.  The \"response\" parameter to AfterTextInput will be the text that was entered, or \"Cancel\" if the CANCEL button was pressed.")
  public void ShowTextDialog(String paramString1, String paramString2, boolean paramBoolean) {
    textInputDialog(paramString1, paramString2, paramBoolean, false);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies the text color for alerts (not dialogs).")
  public int TextColor() {
    return this.textColor;
  }
  
  @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
  @SimpleProperty
  public void TextColor(int paramInt) {
    this.textColor = paramInt;
  }
  
  @SimpleEvent(description = "Event raised when the user canceled ShowTextDialog.")
  public void TextInputCanceled() {
    EventDispatcher.dispatchEvent(this, "TextInputCanceled", new Object[0]);
  }
  
  public void progressDialog(String paramString1, String paramString2) {
    if (this.progressDialog != null)
      DismissProgressDialog(); 
    this.progressDialog = ProgressDialog.show((Context)this.activity, paramString2, paramString1);
    this.progressDialog.setCancelable(false);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Notifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
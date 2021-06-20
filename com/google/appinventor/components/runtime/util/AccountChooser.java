package com.google.appinventor.components.runtime.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

public class AccountChooser {
  private static final String ACCOUNT_PREFERENCE = "account";
  
  private static final String ACCOUNT_TYPE = "com.google";
  
  private static final String LOG_TAG = "AccountChooser";
  
  private static final String NO_ACCOUNT = "";
  
  private AccountManager accountManager;
  
  private Activity activity;
  
  private String chooseAccountPrompt;
  
  private String preferencesKey;
  
  private String service;
  
  public AccountChooser(Activity paramActivity, String paramString1, String paramString2, String paramString3) {
    this.activity = paramActivity;
    this.service = paramString1;
    this.chooseAccountPrompt = paramString2;
    this.preferencesKey = paramString3;
    this.accountManager = AccountManager.get((Context)paramActivity);
  }
  
  private Account chooseAccount(String paramString, Account[] paramArrayOfAccount) {
    int j = paramArrayOfAccount.length;
    for (int i = 0; i < j; i++) {
      Account account = paramArrayOfAccount[i];
      if (account.name.equals(paramString)) {
        Log.i("AccountChooser", "chose account: " + paramString);
        return account;
      } 
    } 
    return null;
  }
  
  private String createAccount() {
    Log.i("AccountChooser", "Adding auth token account ...");
    AccountManagerFuture accountManagerFuture = this.accountManager.addAccount("com.google", this.service, null, null, this.activity, null, null);
    try {
      String str = ((Bundle)accountManagerFuture.getResult()).getString("authAccount");
      Log.i("AccountChooser", "created: " + str);
      return str;
    } catch (OperationCanceledException operationCanceledException) {
      operationCanceledException.printStackTrace();
    } catch (AuthenticatorException authenticatorException) {
      authenticatorException.printStackTrace();
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
    return null;
  }
  
  private String getPersistedAccountName() {
    return getPreferences().getString("account", null);
  }
  
  private SharedPreferences getPreferences() {
    return this.activity.getSharedPreferences(this.preferencesKey, 0);
  }
  
  private void persistAccountName(String paramString) {
    Log.i("AccountChooser", "persisting account: " + paramString);
    getPreferences().edit().putString("account", paramString).commit();
  }
  
  private String selectAccount(Account[] paramArrayOfAccount) {
    String str1;
    SynchronousQueue<String> synchronousQueue = new SynchronousQueue();
    (new SelectAccount(paramArrayOfAccount, synchronousQueue)).start();
    Log.i("AccountChooser", "Select: waiting for user...");
    paramArrayOfAccount = null;
    try {
      String str = synchronousQueue.take();
      str1 = str;
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    } 
    Log.i("AccountChooser", "Selected: " + str1);
    String str2 = str1;
    if ("".equals(str1))
      str2 = null; 
    return str2;
  }
  
  public Account findAccount() {
    // Byte code:
    //   0: aload_0
    //   1: getfield accountManager : Landroid/accounts/AccountManager;
    //   4: ldc 'com.google'
    //   6: invokevirtual getAccountsByType : (Ljava/lang/String;)[Landroid/accounts/Account;
    //   9: astore_3
    //   10: aload_3
    //   11: arraylength
    //   12: iconst_1
    //   13: if_icmpne -> 32
    //   16: aload_0
    //   17: aload_3
    //   18: iconst_0
    //   19: aaload
    //   20: getfield name : Ljava/lang/String;
    //   23: invokespecial persistAccountName : (Ljava/lang/String;)V
    //   26: aload_3
    //   27: iconst_0
    //   28: aaload
    //   29: astore_1
    //   30: aload_1
    //   31: areturn
    //   32: aload_3
    //   33: arraylength
    //   34: ifne -> 73
    //   37: aload_0
    //   38: invokespecial createAccount : ()Ljava/lang/String;
    //   41: astore_1
    //   42: aload_1
    //   43: ifnull -> 63
    //   46: aload_0
    //   47: aload_1
    //   48: invokespecial persistAccountName : (Ljava/lang/String;)V
    //   51: aload_0
    //   52: getfield accountManager : Landroid/accounts/AccountManager;
    //   55: ldc 'com.google'
    //   57: invokevirtual getAccountsByType : (Ljava/lang/String;)[Landroid/accounts/Account;
    //   60: iconst_0
    //   61: aaload
    //   62: areturn
    //   63: ldc 'AccountChooser'
    //   65: ldc 'User failed to create a valid account'
    //   67: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   70: pop
    //   71: aconst_null
    //   72: areturn
    //   73: aload_0
    //   74: invokespecial getPersistedAccountName : ()Ljava/lang/String;
    //   77: astore_1
    //   78: aload_1
    //   79: ifnull -> 95
    //   82: aload_0
    //   83: aload_1
    //   84: aload_3
    //   85: invokespecial chooseAccount : (Ljava/lang/String;[Landroid/accounts/Account;)Landroid/accounts/Account;
    //   88: astore_2
    //   89: aload_2
    //   90: astore_1
    //   91: aload_2
    //   92: ifnonnull -> 30
    //   95: aload_0
    //   96: aload_3
    //   97: invokespecial selectAccount : ([Landroid/accounts/Account;)Ljava/lang/String;
    //   100: astore_1
    //   101: aload_1
    //   102: ifnull -> 117
    //   105: aload_0
    //   106: aload_1
    //   107: invokespecial persistAccountName : (Ljava/lang/String;)V
    //   110: aload_0
    //   111: aload_1
    //   112: aload_3
    //   113: invokespecial chooseAccount : (Ljava/lang/String;[Landroid/accounts/Account;)Landroid/accounts/Account;
    //   116: areturn
    //   117: ldc 'AccountChooser'
    //   119: ldc 'User failed to choose an account'
    //   121: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   124: pop
    //   125: aconst_null
    //   126: areturn
  }
  
  public void forgetAccountName() {
    getPreferences().edit().remove("account").commit();
  }
  
  class SelectAccount extends Thread implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener {
    private String[] accountNames;
    
    private SynchronousQueue<String> queue;
    
    SelectAccount(Account[] param1ArrayOfAccount, SynchronousQueue<String> param1SynchronousQueue) {
      this.queue = param1SynchronousQueue;
      this.accountNames = new String[param1ArrayOfAccount.length];
      int i;
      for (i = 0; i < param1ArrayOfAccount.length; i++)
        this.accountNames[i] = (param1ArrayOfAccount[i]).name; 
    }
    
    public void onCancel(DialogInterface param1DialogInterface) {
      Log.i("AccountChooser", "Chose: canceled");
      onClick(param1DialogInterface, -1);
    }
    
    public void onClick(DialogInterface param1DialogInterface, int param1Int) {
      if (param1Int >= 0) {
        try {
          String str = this.accountNames[param1Int];
          Log.i("AccountChooser", "Chose: " + str);
          this.queue.put(str);
        } catch (InterruptedException interruptedException) {}
      } else {
        this.queue.put("");
      } 
      param1DialogInterface.dismiss();
    }
    
    public void run() {
      AccountChooser.this.activity.runOnUiThread(new Runnable() {
            public void run() {
              (new AlertDialog.Builder((Context)AccountChooser.this.activity)).setTitle((CharSequence)Html.fromHtml(AccountChooser.this.chooseAccountPrompt)).setOnCancelListener(AccountChooser.SelectAccount.this).setSingleChoiceItems((CharSequence[])AccountChooser.SelectAccount.this.accountNames, -1, AccountChooser.SelectAccount.this).show();
              Log.i("AccountChooser", "Dialog showing!");
            }
          });
    }
  }
  
  class null implements Runnable {
    public void run() {
      (new AlertDialog.Builder((Context)AccountChooser.this.activity)).setTitle((CharSequence)Html.fromHtml(AccountChooser.this.chooseAccountPrompt)).setOnCancelListener(this.this$1).setSingleChoiceItems((CharSequence[])this.this$1.accountNames, -1, this.this$1).show();
      Log.i("AccountChooser", "Dialog showing!");
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/AccountChooser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.util.LogWriter;
import androidx.core.view.ViewCompat;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction implements FragmentManager.BackStackEntry, FragmentManagerImpl.OpGenerator {
  static final int OP_ADD = 1;
  
  static final int OP_ATTACH = 7;
  
  static final int OP_DETACH = 6;
  
  static final int OP_HIDE = 4;
  
  static final int OP_NULL = 0;
  
  static final int OP_REMOVE = 3;
  
  static final int OP_REPLACE = 2;
  
  static final int OP_SET_PRIMARY_NAV = 8;
  
  static final int OP_SHOW = 5;
  
  static final int OP_UNSET_PRIMARY_NAV = 9;
  
  static final String TAG = "FragmentManager";
  
  boolean mAddToBackStack;
  
  boolean mAllowAddToBackStack = true;
  
  int mBreadCrumbShortTitleRes;
  
  CharSequence mBreadCrumbShortTitleText;
  
  int mBreadCrumbTitleRes;
  
  CharSequence mBreadCrumbTitleText;
  
  ArrayList<Runnable> mCommitRunnables;
  
  boolean mCommitted;
  
  int mEnterAnim;
  
  int mExitAnim;
  
  int mIndex = -1;
  
  final FragmentManagerImpl mManager;
  
  @Nullable
  String mName;
  
  ArrayList<Op> mOps = new ArrayList<Op>();
  
  int mPopEnterAnim;
  
  int mPopExitAnim;
  
  boolean mReorderingAllowed = false;
  
  ArrayList<String> mSharedElementSourceNames;
  
  ArrayList<String> mSharedElementTargetNames;
  
  int mTransition;
  
  int mTransitionStyle;
  
  public BackStackRecord(FragmentManagerImpl paramFragmentManagerImpl) {
    this.mManager = paramFragmentManagerImpl;
  }
  
  private void doAddOp(int paramInt1, Fragment paramFragment, @Nullable String paramString, int paramInt2) {
    Class<?> clazz = paramFragment.getClass();
    int i = clazz.getModifiers();
    if (clazz.isAnonymousClass() || !Modifier.isPublic(i) || (clazz.isMemberClass() && !Modifier.isStatic(i)))
      throw new IllegalStateException("Fragment " + clazz.getCanonicalName() + " must be a public static class to be  properly recreated from" + " instance state."); 
    paramFragment.mFragmentManager = this.mManager;
    if (paramString != null) {
      if (paramFragment.mTag != null && !paramString.equals(paramFragment.mTag))
        throw new IllegalStateException("Can't change tag of fragment " + paramFragment + ": was " + paramFragment.mTag + " now " + paramString); 
      paramFragment.mTag = paramString;
    } 
    if (paramInt1 != 0) {
      if (paramInt1 == -1)
        throw new IllegalArgumentException("Can't add fragment " + paramFragment + " with tag " + paramString + " to container view with no id"); 
      if (paramFragment.mFragmentId != 0 && paramFragment.mFragmentId != paramInt1)
        throw new IllegalStateException("Can't change container ID of fragment " + paramFragment + ": was " + paramFragment.mFragmentId + " now " + paramInt1); 
      paramFragment.mFragmentId = paramInt1;
      paramFragment.mContainerId = paramInt1;
    } 
    addOp(new Op(paramInt2, paramFragment));
  }
  
  private static boolean isFragmentPostponed(Op paramOp) {
    Fragment fragment = paramOp.fragment;
    return (fragment != null && fragment.mAdded && fragment.mView != null && !fragment.mDetached && !fragment.mHidden && fragment.isPostponed());
  }
  
  public FragmentTransaction add(int paramInt, Fragment paramFragment) {
    doAddOp(paramInt, paramFragment, null, 1);
    return this;
  }
  
  public FragmentTransaction add(int paramInt, Fragment paramFragment, @Nullable String paramString) {
    doAddOp(paramInt, paramFragment, paramString, 1);
    return this;
  }
  
  public FragmentTransaction add(Fragment paramFragment, @Nullable String paramString) {
    doAddOp(0, paramFragment, paramString, 1);
    return this;
  }
  
  void addOp(Op paramOp) {
    this.mOps.add(paramOp);
    paramOp.enterAnim = this.mEnterAnim;
    paramOp.exitAnim = this.mExitAnim;
    paramOp.popEnterAnim = this.mPopEnterAnim;
    paramOp.popExitAnim = this.mPopExitAnim;
  }
  
  public FragmentTransaction addSharedElement(View paramView, String paramString) {
    if (FragmentTransition.supportsTransition()) {
      String str = ViewCompat.getTransitionName(paramView);
      if (str == null)
        throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements"); 
      if (this.mSharedElementSourceNames == null) {
        this.mSharedElementSourceNames = new ArrayList<String>();
        this.mSharedElementTargetNames = new ArrayList<String>();
      } else {
        if (this.mSharedElementTargetNames.contains(paramString))
          throw new IllegalArgumentException("A shared element with the target name '" + paramString + "' has already been added to the transaction."); 
        if (this.mSharedElementSourceNames.contains(str))
          throw new IllegalArgumentException("A shared element with the source name '" + str + " has already been added to the transaction."); 
      } 
      this.mSharedElementSourceNames.add(str);
      this.mSharedElementTargetNames.add(paramString);
    } 
    return this;
  }
  
  public FragmentTransaction addToBackStack(@Nullable String paramString) {
    if (!this.mAllowAddToBackStack)
      throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack."); 
    this.mAddToBackStack = true;
    this.mName = paramString;
    return this;
  }
  
  public FragmentTransaction attach(Fragment paramFragment) {
    addOp(new Op(7, paramFragment));
    return this;
  }
  
  void bumpBackStackNesting(int paramInt) {
    if (this.mAddToBackStack) {
      if (FragmentManagerImpl.DEBUG)
        Log.v("FragmentManager", "Bump nesting in " + this + " by " + paramInt); 
      int j = this.mOps.size();
      int i = 0;
      while (true) {
        if (i < j) {
          Op op = this.mOps.get(i);
          if (op.fragment != null) {
            Fragment fragment = op.fragment;
            fragment.mBackStackNesting += paramInt;
            if (FragmentManagerImpl.DEBUG)
              Log.v("FragmentManager", "Bump nesting of " + op.fragment + " to " + op.fragment.mBackStackNesting); 
          } 
          i++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public int commit() {
    return commitInternal(false);
  }
  
  public int commitAllowingStateLoss() {
    return commitInternal(true);
  }
  
  int commitInternal(boolean paramBoolean) {
    if (this.mCommitted)
      throw new IllegalStateException("commit already called"); 
    if (FragmentManagerImpl.DEBUG) {
      Log.v("FragmentManager", "Commit: " + this);
      PrintWriter printWriter = new PrintWriter((Writer)new LogWriter("FragmentManager"));
      dump("  ", null, printWriter, null);
      printWriter.close();
    } 
    this.mCommitted = true;
    if (this.mAddToBackStack) {
      this.mIndex = this.mManager.allocBackStackIndex(this);
      this.mManager.enqueueAction(this, paramBoolean);
      return this.mIndex;
    } 
    this.mIndex = -1;
    this.mManager.enqueueAction(this, paramBoolean);
    return this.mIndex;
  }
  
  public void commitNow() {
    disallowAddToBackStack();
    this.mManager.execSingleAction(this, false);
  }
  
  public void commitNowAllowingStateLoss() {
    disallowAddToBackStack();
    this.mManager.execSingleAction(this, true);
  }
  
  public FragmentTransaction detach(Fragment paramFragment) {
    addOp(new Op(6, paramFragment));
    return this;
  }
  
  public FragmentTransaction disallowAddToBackStack() {
    if (this.mAddToBackStack)
      throw new IllegalStateException("This transaction is already being added to the back stack"); 
    this.mAllowAddToBackStack = false;
    return this;
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    dump(paramString, paramPrintWriter, true);
  }
  
  public void dump(String paramString, PrintWriter paramPrintWriter, boolean paramBoolean) {
    // Byte code:
    //   0: iload_3
    //   1: ifeq -> 316
    //   4: aload_2
    //   5: aload_1
    //   6: invokevirtual print : (Ljava/lang/String;)V
    //   9: aload_2
    //   10: ldc_w 'mName='
    //   13: invokevirtual print : (Ljava/lang/String;)V
    //   16: aload_2
    //   17: aload_0
    //   18: getfield mName : Ljava/lang/String;
    //   21: invokevirtual print : (Ljava/lang/String;)V
    //   24: aload_2
    //   25: ldc_w ' mIndex='
    //   28: invokevirtual print : (Ljava/lang/String;)V
    //   31: aload_2
    //   32: aload_0
    //   33: getfield mIndex : I
    //   36: invokevirtual print : (I)V
    //   39: aload_2
    //   40: ldc_w ' mCommitted='
    //   43: invokevirtual print : (Ljava/lang/String;)V
    //   46: aload_2
    //   47: aload_0
    //   48: getfield mCommitted : Z
    //   51: invokevirtual println : (Z)V
    //   54: aload_0
    //   55: getfield mTransition : I
    //   58: ifeq -> 102
    //   61: aload_2
    //   62: aload_1
    //   63: invokevirtual print : (Ljava/lang/String;)V
    //   66: aload_2
    //   67: ldc_w 'mTransition=#'
    //   70: invokevirtual print : (Ljava/lang/String;)V
    //   73: aload_2
    //   74: aload_0
    //   75: getfield mTransition : I
    //   78: invokestatic toHexString : (I)Ljava/lang/String;
    //   81: invokevirtual print : (Ljava/lang/String;)V
    //   84: aload_2
    //   85: ldc_w ' mTransitionStyle=#'
    //   88: invokevirtual print : (Ljava/lang/String;)V
    //   91: aload_2
    //   92: aload_0
    //   93: getfield mTransitionStyle : I
    //   96: invokestatic toHexString : (I)Ljava/lang/String;
    //   99: invokevirtual println : (Ljava/lang/String;)V
    //   102: aload_0
    //   103: getfield mEnterAnim : I
    //   106: ifne -> 116
    //   109: aload_0
    //   110: getfield mExitAnim : I
    //   113: ifeq -> 157
    //   116: aload_2
    //   117: aload_1
    //   118: invokevirtual print : (Ljava/lang/String;)V
    //   121: aload_2
    //   122: ldc_w 'mEnterAnim=#'
    //   125: invokevirtual print : (Ljava/lang/String;)V
    //   128: aload_2
    //   129: aload_0
    //   130: getfield mEnterAnim : I
    //   133: invokestatic toHexString : (I)Ljava/lang/String;
    //   136: invokevirtual print : (Ljava/lang/String;)V
    //   139: aload_2
    //   140: ldc_w ' mExitAnim=#'
    //   143: invokevirtual print : (Ljava/lang/String;)V
    //   146: aload_2
    //   147: aload_0
    //   148: getfield mExitAnim : I
    //   151: invokestatic toHexString : (I)Ljava/lang/String;
    //   154: invokevirtual println : (Ljava/lang/String;)V
    //   157: aload_0
    //   158: getfield mPopEnterAnim : I
    //   161: ifne -> 171
    //   164: aload_0
    //   165: getfield mPopExitAnim : I
    //   168: ifeq -> 212
    //   171: aload_2
    //   172: aload_1
    //   173: invokevirtual print : (Ljava/lang/String;)V
    //   176: aload_2
    //   177: ldc_w 'mPopEnterAnim=#'
    //   180: invokevirtual print : (Ljava/lang/String;)V
    //   183: aload_2
    //   184: aload_0
    //   185: getfield mPopEnterAnim : I
    //   188: invokestatic toHexString : (I)Ljava/lang/String;
    //   191: invokevirtual print : (Ljava/lang/String;)V
    //   194: aload_2
    //   195: ldc_w ' mPopExitAnim=#'
    //   198: invokevirtual print : (Ljava/lang/String;)V
    //   201: aload_2
    //   202: aload_0
    //   203: getfield mPopExitAnim : I
    //   206: invokestatic toHexString : (I)Ljava/lang/String;
    //   209: invokevirtual println : (Ljava/lang/String;)V
    //   212: aload_0
    //   213: getfield mBreadCrumbTitleRes : I
    //   216: ifne -> 226
    //   219: aload_0
    //   220: getfield mBreadCrumbTitleText : Ljava/lang/CharSequence;
    //   223: ifnull -> 264
    //   226: aload_2
    //   227: aload_1
    //   228: invokevirtual print : (Ljava/lang/String;)V
    //   231: aload_2
    //   232: ldc_w 'mBreadCrumbTitleRes=#'
    //   235: invokevirtual print : (Ljava/lang/String;)V
    //   238: aload_2
    //   239: aload_0
    //   240: getfield mBreadCrumbTitleRes : I
    //   243: invokestatic toHexString : (I)Ljava/lang/String;
    //   246: invokevirtual print : (Ljava/lang/String;)V
    //   249: aload_2
    //   250: ldc_w ' mBreadCrumbTitleText='
    //   253: invokevirtual print : (Ljava/lang/String;)V
    //   256: aload_2
    //   257: aload_0
    //   258: getfield mBreadCrumbTitleText : Ljava/lang/CharSequence;
    //   261: invokevirtual println : (Ljava/lang/Object;)V
    //   264: aload_0
    //   265: getfield mBreadCrumbShortTitleRes : I
    //   268: ifne -> 278
    //   271: aload_0
    //   272: getfield mBreadCrumbShortTitleText : Ljava/lang/CharSequence;
    //   275: ifnull -> 316
    //   278: aload_2
    //   279: aload_1
    //   280: invokevirtual print : (Ljava/lang/String;)V
    //   283: aload_2
    //   284: ldc_w 'mBreadCrumbShortTitleRes=#'
    //   287: invokevirtual print : (Ljava/lang/String;)V
    //   290: aload_2
    //   291: aload_0
    //   292: getfield mBreadCrumbShortTitleRes : I
    //   295: invokestatic toHexString : (I)Ljava/lang/String;
    //   298: invokevirtual print : (Ljava/lang/String;)V
    //   301: aload_2
    //   302: ldc_w ' mBreadCrumbShortTitleText='
    //   305: invokevirtual print : (Ljava/lang/String;)V
    //   308: aload_2
    //   309: aload_0
    //   310: getfield mBreadCrumbShortTitleText : Ljava/lang/CharSequence;
    //   313: invokevirtual println : (Ljava/lang/Object;)V
    //   316: aload_0
    //   317: getfield mOps : Ljava/util/ArrayList;
    //   320: invokevirtual isEmpty : ()Z
    //   323: ifne -> 736
    //   326: aload_2
    //   327: aload_1
    //   328: invokevirtual print : (Ljava/lang/String;)V
    //   331: aload_2
    //   332: ldc_w 'Operations:'
    //   335: invokevirtual println : (Ljava/lang/String;)V
    //   338: new java/lang/StringBuilder
    //   341: dup
    //   342: invokespecial <init> : ()V
    //   345: aload_1
    //   346: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: ldc_w '    '
    //   352: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   355: invokevirtual toString : ()Ljava/lang/String;
    //   358: pop
    //   359: aload_0
    //   360: getfield mOps : Ljava/util/ArrayList;
    //   363: invokevirtual size : ()I
    //   366: istore #5
    //   368: iconst_0
    //   369: istore #4
    //   371: iload #4
    //   373: iload #5
    //   375: if_icmpge -> 736
    //   378: aload_0
    //   379: getfield mOps : Ljava/util/ArrayList;
    //   382: iload #4
    //   384: invokevirtual get : (I)Ljava/lang/Object;
    //   387: checkcast androidx/fragment/app/BackStackRecord$Op
    //   390: astore #7
    //   392: aload #7
    //   394: getfield cmd : I
    //   397: tableswitch default -> 452, 0 -> 656, 1 -> 664, 2 -> 672, 3 -> 680, 4 -> 688, 5 -> 696, 6 -> 704, 7 -> 712, 8 -> 720, 9 -> 728
    //   452: new java/lang/StringBuilder
    //   455: dup
    //   456: invokespecial <init> : ()V
    //   459: ldc_w 'cmd='
    //   462: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   465: aload #7
    //   467: getfield cmd : I
    //   470: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   473: invokevirtual toString : ()Ljava/lang/String;
    //   476: astore #6
    //   478: aload_2
    //   479: aload_1
    //   480: invokevirtual print : (Ljava/lang/String;)V
    //   483: aload_2
    //   484: ldc_w '  Op #'
    //   487: invokevirtual print : (Ljava/lang/String;)V
    //   490: aload_2
    //   491: iload #4
    //   493: invokevirtual print : (I)V
    //   496: aload_2
    //   497: ldc_w ': '
    //   500: invokevirtual print : (Ljava/lang/String;)V
    //   503: aload_2
    //   504: aload #6
    //   506: invokevirtual print : (Ljava/lang/String;)V
    //   509: aload_2
    //   510: ldc_w ' '
    //   513: invokevirtual print : (Ljava/lang/String;)V
    //   516: aload_2
    //   517: aload #7
    //   519: getfield fragment : Landroidx/fragment/app/Fragment;
    //   522: invokevirtual println : (Ljava/lang/Object;)V
    //   525: iload_3
    //   526: ifeq -> 647
    //   529: aload #7
    //   531: getfield enterAnim : I
    //   534: ifne -> 545
    //   537: aload #7
    //   539: getfield exitAnim : I
    //   542: ifeq -> 588
    //   545: aload_2
    //   546: aload_1
    //   547: invokevirtual print : (Ljava/lang/String;)V
    //   550: aload_2
    //   551: ldc_w 'enterAnim=#'
    //   554: invokevirtual print : (Ljava/lang/String;)V
    //   557: aload_2
    //   558: aload #7
    //   560: getfield enterAnim : I
    //   563: invokestatic toHexString : (I)Ljava/lang/String;
    //   566: invokevirtual print : (Ljava/lang/String;)V
    //   569: aload_2
    //   570: ldc_w ' exitAnim=#'
    //   573: invokevirtual print : (Ljava/lang/String;)V
    //   576: aload_2
    //   577: aload #7
    //   579: getfield exitAnim : I
    //   582: invokestatic toHexString : (I)Ljava/lang/String;
    //   585: invokevirtual println : (Ljava/lang/String;)V
    //   588: aload #7
    //   590: getfield popEnterAnim : I
    //   593: ifne -> 604
    //   596: aload #7
    //   598: getfield popExitAnim : I
    //   601: ifeq -> 647
    //   604: aload_2
    //   605: aload_1
    //   606: invokevirtual print : (Ljava/lang/String;)V
    //   609: aload_2
    //   610: ldc_w 'popEnterAnim=#'
    //   613: invokevirtual print : (Ljava/lang/String;)V
    //   616: aload_2
    //   617: aload #7
    //   619: getfield popEnterAnim : I
    //   622: invokestatic toHexString : (I)Ljava/lang/String;
    //   625: invokevirtual print : (Ljava/lang/String;)V
    //   628: aload_2
    //   629: ldc_w ' popExitAnim=#'
    //   632: invokevirtual print : (Ljava/lang/String;)V
    //   635: aload_2
    //   636: aload #7
    //   638: getfield popExitAnim : I
    //   641: invokestatic toHexString : (I)Ljava/lang/String;
    //   644: invokevirtual println : (Ljava/lang/String;)V
    //   647: iload #4
    //   649: iconst_1
    //   650: iadd
    //   651: istore #4
    //   653: goto -> 371
    //   656: ldc_w 'NULL'
    //   659: astore #6
    //   661: goto -> 478
    //   664: ldc_w 'ADD'
    //   667: astore #6
    //   669: goto -> 478
    //   672: ldc_w 'REPLACE'
    //   675: astore #6
    //   677: goto -> 478
    //   680: ldc_w 'REMOVE'
    //   683: astore #6
    //   685: goto -> 478
    //   688: ldc_w 'HIDE'
    //   691: astore #6
    //   693: goto -> 478
    //   696: ldc_w 'SHOW'
    //   699: astore #6
    //   701: goto -> 478
    //   704: ldc_w 'DETACH'
    //   707: astore #6
    //   709: goto -> 478
    //   712: ldc_w 'ATTACH'
    //   715: astore #6
    //   717: goto -> 478
    //   720: ldc_w 'SET_PRIMARY_NAV'
    //   723: astore #6
    //   725: goto -> 478
    //   728: ldc_w 'UNSET_PRIMARY_NAV'
    //   731: astore #6
    //   733: goto -> 478
    //   736: return
  }
  
  void executeOps() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mOps : Ljava/util/ArrayList;
    //   4: invokevirtual size : ()I
    //   7: istore_2
    //   8: iconst_0
    //   9: istore_1
    //   10: iload_1
    //   11: iload_2
    //   12: if_icmpge -> 318
    //   15: aload_0
    //   16: getfield mOps : Ljava/util/ArrayList;
    //   19: iload_1
    //   20: invokevirtual get : (I)Ljava/lang/Object;
    //   23: checkcast androidx/fragment/app/BackStackRecord$Op
    //   26: astore_3
    //   27: aload_3
    //   28: getfield fragment : Landroidx/fragment/app/Fragment;
    //   31: astore #4
    //   33: aload #4
    //   35: ifnull -> 51
    //   38: aload #4
    //   40: aload_0
    //   41: getfield mTransition : I
    //   44: aload_0
    //   45: getfield mTransitionStyle : I
    //   48: invokevirtual setNextTransition : (II)V
    //   51: aload_3
    //   52: getfield cmd : I
    //   55: tableswitch default -> 104, 1 -> 135, 2 -> 104, 3 -> 190, 4 -> 211, 5 -> 232, 6 -> 253, 7 -> 274, 8 -> 295, 9 -> 307
    //   104: new java/lang/IllegalArgumentException
    //   107: dup
    //   108: new java/lang/StringBuilder
    //   111: dup
    //   112: invokespecial <init> : ()V
    //   115: ldc_w 'Unknown cmd: '
    //   118: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: aload_3
    //   122: getfield cmd : I
    //   125: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   128: invokevirtual toString : ()Ljava/lang/String;
    //   131: invokespecial <init> : (Ljava/lang/String;)V
    //   134: athrow
    //   135: aload #4
    //   137: aload_3
    //   138: getfield enterAnim : I
    //   141: invokevirtual setNextAnim : (I)V
    //   144: aload_0
    //   145: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   148: aload #4
    //   150: iconst_0
    //   151: invokevirtual addFragment : (Landroidx/fragment/app/Fragment;Z)V
    //   154: aload_0
    //   155: getfield mReorderingAllowed : Z
    //   158: ifne -> 183
    //   161: aload_3
    //   162: getfield cmd : I
    //   165: iconst_1
    //   166: if_icmpeq -> 183
    //   169: aload #4
    //   171: ifnull -> 183
    //   174: aload_0
    //   175: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   178: aload #4
    //   180: invokevirtual moveFragmentToExpectedState : (Landroidx/fragment/app/Fragment;)V
    //   183: iload_1
    //   184: iconst_1
    //   185: iadd
    //   186: istore_1
    //   187: goto -> 10
    //   190: aload #4
    //   192: aload_3
    //   193: getfield exitAnim : I
    //   196: invokevirtual setNextAnim : (I)V
    //   199: aload_0
    //   200: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   203: aload #4
    //   205: invokevirtual removeFragment : (Landroidx/fragment/app/Fragment;)V
    //   208: goto -> 154
    //   211: aload #4
    //   213: aload_3
    //   214: getfield exitAnim : I
    //   217: invokevirtual setNextAnim : (I)V
    //   220: aload_0
    //   221: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   224: aload #4
    //   226: invokevirtual hideFragment : (Landroidx/fragment/app/Fragment;)V
    //   229: goto -> 154
    //   232: aload #4
    //   234: aload_3
    //   235: getfield enterAnim : I
    //   238: invokevirtual setNextAnim : (I)V
    //   241: aload_0
    //   242: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   245: aload #4
    //   247: invokevirtual showFragment : (Landroidx/fragment/app/Fragment;)V
    //   250: goto -> 154
    //   253: aload #4
    //   255: aload_3
    //   256: getfield exitAnim : I
    //   259: invokevirtual setNextAnim : (I)V
    //   262: aload_0
    //   263: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   266: aload #4
    //   268: invokevirtual detachFragment : (Landroidx/fragment/app/Fragment;)V
    //   271: goto -> 154
    //   274: aload #4
    //   276: aload_3
    //   277: getfield enterAnim : I
    //   280: invokevirtual setNextAnim : (I)V
    //   283: aload_0
    //   284: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   287: aload #4
    //   289: invokevirtual attachFragment : (Landroidx/fragment/app/Fragment;)V
    //   292: goto -> 154
    //   295: aload_0
    //   296: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   299: aload #4
    //   301: invokevirtual setPrimaryNavigationFragment : (Landroidx/fragment/app/Fragment;)V
    //   304: goto -> 154
    //   307: aload_0
    //   308: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   311: aconst_null
    //   312: invokevirtual setPrimaryNavigationFragment : (Landroidx/fragment/app/Fragment;)V
    //   315: goto -> 154
    //   318: aload_0
    //   319: getfield mReorderingAllowed : Z
    //   322: ifne -> 340
    //   325: aload_0
    //   326: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   329: aload_0
    //   330: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   333: getfield mCurState : I
    //   336: iconst_1
    //   337: invokevirtual moveToState : (IZ)V
    //   340: return
  }
  
  void executePopOps(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mOps : Ljava/util/ArrayList;
    //   4: invokevirtual size : ()I
    //   7: iconst_1
    //   8: isub
    //   9: istore_2
    //   10: iload_2
    //   11: iflt -> 322
    //   14: aload_0
    //   15: getfield mOps : Ljava/util/ArrayList;
    //   18: iload_2
    //   19: invokevirtual get : (I)Ljava/lang/Object;
    //   22: checkcast androidx/fragment/app/BackStackRecord$Op
    //   25: astore_3
    //   26: aload_3
    //   27: getfield fragment : Landroidx/fragment/app/Fragment;
    //   30: astore #4
    //   32: aload #4
    //   34: ifnull -> 53
    //   37: aload #4
    //   39: aload_0
    //   40: getfield mTransition : I
    //   43: invokestatic reverseTransit : (I)I
    //   46: aload_0
    //   47: getfield mTransitionStyle : I
    //   50: invokevirtual setNextTransition : (II)V
    //   53: aload_3
    //   54: getfield cmd : I
    //   57: tableswitch default -> 108, 1 -> 139, 2 -> 108, 3 -> 193, 4 -> 215, 5 -> 236, 6 -> 257, 7 -> 278, 8 -> 299, 9 -> 310
    //   108: new java/lang/IllegalArgumentException
    //   111: dup
    //   112: new java/lang/StringBuilder
    //   115: dup
    //   116: invokespecial <init> : ()V
    //   119: ldc_w 'Unknown cmd: '
    //   122: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: aload_3
    //   126: getfield cmd : I
    //   129: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   132: invokevirtual toString : ()Ljava/lang/String;
    //   135: invokespecial <init> : (Ljava/lang/String;)V
    //   138: athrow
    //   139: aload #4
    //   141: aload_3
    //   142: getfield popExitAnim : I
    //   145: invokevirtual setNextAnim : (I)V
    //   148: aload_0
    //   149: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   152: aload #4
    //   154: invokevirtual removeFragment : (Landroidx/fragment/app/Fragment;)V
    //   157: aload_0
    //   158: getfield mReorderingAllowed : Z
    //   161: ifne -> 186
    //   164: aload_3
    //   165: getfield cmd : I
    //   168: iconst_3
    //   169: if_icmpeq -> 186
    //   172: aload #4
    //   174: ifnull -> 186
    //   177: aload_0
    //   178: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   181: aload #4
    //   183: invokevirtual moveFragmentToExpectedState : (Landroidx/fragment/app/Fragment;)V
    //   186: iload_2
    //   187: iconst_1
    //   188: isub
    //   189: istore_2
    //   190: goto -> 10
    //   193: aload #4
    //   195: aload_3
    //   196: getfield popEnterAnim : I
    //   199: invokevirtual setNextAnim : (I)V
    //   202: aload_0
    //   203: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   206: aload #4
    //   208: iconst_0
    //   209: invokevirtual addFragment : (Landroidx/fragment/app/Fragment;Z)V
    //   212: goto -> 157
    //   215: aload #4
    //   217: aload_3
    //   218: getfield popEnterAnim : I
    //   221: invokevirtual setNextAnim : (I)V
    //   224: aload_0
    //   225: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   228: aload #4
    //   230: invokevirtual showFragment : (Landroidx/fragment/app/Fragment;)V
    //   233: goto -> 157
    //   236: aload #4
    //   238: aload_3
    //   239: getfield popExitAnim : I
    //   242: invokevirtual setNextAnim : (I)V
    //   245: aload_0
    //   246: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   249: aload #4
    //   251: invokevirtual hideFragment : (Landroidx/fragment/app/Fragment;)V
    //   254: goto -> 157
    //   257: aload #4
    //   259: aload_3
    //   260: getfield popEnterAnim : I
    //   263: invokevirtual setNextAnim : (I)V
    //   266: aload_0
    //   267: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   270: aload #4
    //   272: invokevirtual attachFragment : (Landroidx/fragment/app/Fragment;)V
    //   275: goto -> 157
    //   278: aload #4
    //   280: aload_3
    //   281: getfield popExitAnim : I
    //   284: invokevirtual setNextAnim : (I)V
    //   287: aload_0
    //   288: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   291: aload #4
    //   293: invokevirtual detachFragment : (Landroidx/fragment/app/Fragment;)V
    //   296: goto -> 157
    //   299: aload_0
    //   300: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   303: aconst_null
    //   304: invokevirtual setPrimaryNavigationFragment : (Landroidx/fragment/app/Fragment;)V
    //   307: goto -> 157
    //   310: aload_0
    //   311: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   314: aload #4
    //   316: invokevirtual setPrimaryNavigationFragment : (Landroidx/fragment/app/Fragment;)V
    //   319: goto -> 157
    //   322: aload_0
    //   323: getfield mReorderingAllowed : Z
    //   326: ifne -> 348
    //   329: iload_1
    //   330: ifeq -> 348
    //   333: aload_0
    //   334: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   337: aload_0
    //   338: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   341: getfield mCurState : I
    //   344: iconst_1
    //   345: invokevirtual moveToState : (IZ)V
    //   348: return
  }
  
  Fragment expandOps(ArrayList<Fragment> paramArrayList, Fragment paramFragment) {
    int i = 0;
    Fragment fragment = paramFragment;
    while (i < this.mOps.size()) {
      int k;
      int m;
      int n;
      Fragment fragment1;
      Op op = this.mOps.get(i);
      int j = i;
      paramFragment = fragment;
      switch (op.cmd) {
        case 4:
        case 5:
          i = j + 1;
          fragment = paramFragment;
          break;
        case 1:
        case 7:
          paramArrayList.add(op.fragment);
          j = i;
          paramFragment = fragment;
        case 3:
        case 6:
          paramArrayList.remove(op.fragment);
          j = i;
          paramFragment = fragment;
          if (op.fragment == fragment) {
            this.mOps.add(i, new Op(9, op.fragment));
            j = i + 1;
            paramFragment = null;
          } 
        case 2:
          fragment1 = op.fragment;
          n = fragment1.mContainerId;
          k = 0;
          m = paramArrayList.size() - 1;
          paramFragment = fragment;
          j = i;
          i = m;
          while (i >= 0) {
            Fragment fragment2 = paramArrayList.get(i);
            m = k;
            int i1 = j;
            fragment = paramFragment;
            if (fragment2.mContainerId == n)
              if (fragment2 == fragment1) {
                m = 1;
                fragment = paramFragment;
                i1 = j;
              } else {
                m = j;
                fragment = paramFragment;
                if (fragment2 == paramFragment) {
                  this.mOps.add(j, new Op(9, fragment2));
                  m = j + 1;
                  fragment = null;
                } 
                Op op1 = new Op(3, fragment2);
                op1.enterAnim = op.enterAnim;
                op1.popEnterAnim = op.popEnterAnim;
                op1.exitAnim = op.exitAnim;
                op1.popExitAnim = op.popExitAnim;
                this.mOps.add(m, op1);
                paramArrayList.remove(fragment2);
                i1 = m + 1;
                m = k;
              }  
            i--;
            k = m;
            j = i1;
            paramFragment = fragment;
          } 
          if (k != 0) {
            this.mOps.remove(j);
            j--;
          } else {
            op.cmd = 1;
            paramArrayList.add(fragment1);
          } 
        case 8:
          this.mOps.add(i, new Op(9, fragment));
          j = i + 1;
          paramFragment = op.fragment;
      } 
    } 
    return fragment;
  }
  
  public boolean generateOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    if (FragmentManagerImpl.DEBUG)
      Log.v("FragmentManager", "Run: " + this); 
    paramArrayList.add(this);
    paramArrayList1.add(Boolean.valueOf(false));
    if (this.mAddToBackStack)
      this.mManager.addBackStackState(this); 
    return true;
  }
  
  @Nullable
  public CharSequence getBreadCrumbShortTitle() {
    return (this.mBreadCrumbShortTitleRes != 0) ? this.mManager.mHost.getContext().getText(this.mBreadCrumbShortTitleRes) : this.mBreadCrumbShortTitleText;
  }
  
  public int getBreadCrumbShortTitleRes() {
    return this.mBreadCrumbShortTitleRes;
  }
  
  @Nullable
  public CharSequence getBreadCrumbTitle() {
    return (this.mBreadCrumbTitleRes != 0) ? this.mManager.mHost.getContext().getText(this.mBreadCrumbTitleRes) : this.mBreadCrumbTitleText;
  }
  
  public int getBreadCrumbTitleRes() {
    return this.mBreadCrumbTitleRes;
  }
  
  public int getId() {
    return this.mIndex;
  }
  
  @Nullable
  public String getName() {
    return this.mName;
  }
  
  public int getTransition() {
    return this.mTransition;
  }
  
  public int getTransitionStyle() {
    return this.mTransitionStyle;
  }
  
  public FragmentTransaction hide(Fragment paramFragment) {
    addOp(new Op(4, paramFragment));
    return this;
  }
  
  boolean interactsWith(int paramInt) {
    boolean bool = false;
    int j = this.mOps.size();
    for (int i = 0;; i++) {
      boolean bool1 = bool;
      if (i < j) {
        int k;
        Op op = this.mOps.get(i);
        if (op.fragment != null) {
          k = op.fragment.mContainerId;
        } else {
          k = 0;
        } 
        if (k && k == paramInt)
          return true; 
      } else {
        return bool1;
      } 
    } 
  }
  
  boolean interactsWith(ArrayList<BackStackRecord> paramArrayList, int paramInt1, int paramInt2) {
    if (paramInt2 == paramInt1)
      return false; 
    int k = this.mOps.size();
    int j = -1;
    int i = 0;
    while (i < k) {
      int m;
      Op op = this.mOps.get(i);
      if (op.fragment != null) {
        m = op.fragment.mContainerId;
      } else {
        m = 0;
      } 
      int n = j;
      if (m) {
        n = j;
        if (m != j) {
          j = m;
          int i1 = paramInt1;
          while (true) {
            n = j;
            if (i1 < paramInt2) {
              BackStackRecord backStackRecord = paramArrayList.get(i1);
              int i2 = backStackRecord.mOps.size();
              for (n = 0; n < i2; n++) {
                int i3;
                Op op1 = backStackRecord.mOps.get(n);
                if (op1.fragment != null) {
                  i3 = op1.fragment.mContainerId;
                } else {
                  i3 = 0;
                } 
                if (i3 == m)
                  return true; 
              } 
              i1++;
              continue;
            } 
            break;
          } 
        } 
      } 
      i++;
      j = n;
    } 
    return false;
  }
  
  public boolean isAddToBackStackAllowed() {
    return this.mAllowAddToBackStack;
  }
  
  public boolean isEmpty() {
    return this.mOps.isEmpty();
  }
  
  boolean isPostponed() {
    for (int i = 0; i < this.mOps.size(); i++) {
      if (isFragmentPostponed(this.mOps.get(i)))
        return true; 
    } 
    return false;
  }
  
  public FragmentTransaction remove(Fragment paramFragment) {
    addOp(new Op(3, paramFragment));
    return this;
  }
  
  public FragmentTransaction replace(int paramInt, Fragment paramFragment) {
    return replace(paramInt, paramFragment, null);
  }
  
  public FragmentTransaction replace(int paramInt, Fragment paramFragment, @Nullable String paramString) {
    if (paramInt == 0)
      throw new IllegalArgumentException("Must use non-zero containerViewId"); 
    doAddOp(paramInt, paramFragment, paramString, 2);
    return this;
  }
  
  public FragmentTransaction runOnCommit(Runnable paramRunnable) {
    if (paramRunnable == null)
      throw new IllegalArgumentException("runnable cannot be null"); 
    disallowAddToBackStack();
    if (this.mCommitRunnables == null)
      this.mCommitRunnables = new ArrayList<Runnable>(); 
    this.mCommitRunnables.add(paramRunnable);
    return this;
  }
  
  public void runOnCommitRunnables() {
    if (this.mCommitRunnables != null) {
      int i = 0;
      int j = this.mCommitRunnables.size();
      while (i < j) {
        ((Runnable)this.mCommitRunnables.get(i)).run();
        i++;
      } 
      this.mCommitRunnables = null;
    } 
  }
  
  public FragmentTransaction setAllowOptimization(boolean paramBoolean) {
    return setReorderingAllowed(paramBoolean);
  }
  
  public FragmentTransaction setBreadCrumbShortTitle(int paramInt) {
    this.mBreadCrumbShortTitleRes = paramInt;
    this.mBreadCrumbShortTitleText = null;
    return this;
  }
  
  public FragmentTransaction setBreadCrumbShortTitle(@Nullable CharSequence paramCharSequence) {
    this.mBreadCrumbShortTitleRes = 0;
    this.mBreadCrumbShortTitleText = paramCharSequence;
    return this;
  }
  
  public FragmentTransaction setBreadCrumbTitle(int paramInt) {
    this.mBreadCrumbTitleRes = paramInt;
    this.mBreadCrumbTitleText = null;
    return this;
  }
  
  public FragmentTransaction setBreadCrumbTitle(@Nullable CharSequence paramCharSequence) {
    this.mBreadCrumbTitleRes = 0;
    this.mBreadCrumbTitleText = paramCharSequence;
    return this;
  }
  
  public FragmentTransaction setCustomAnimations(int paramInt1, int paramInt2) {
    return setCustomAnimations(paramInt1, paramInt2, 0, 0);
  }
  
  public FragmentTransaction setCustomAnimations(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mEnterAnim = paramInt1;
    this.mExitAnim = paramInt2;
    this.mPopEnterAnim = paramInt3;
    this.mPopExitAnim = paramInt4;
    return this;
  }
  
  void setOnStartPostponedListener(Fragment.OnStartEnterTransitionListener paramOnStartEnterTransitionListener) {
    for (int i = 0; i < this.mOps.size(); i++) {
      Op op = this.mOps.get(i);
      if (isFragmentPostponed(op))
        op.fragment.setOnStartEnterTransitionListener(paramOnStartEnterTransitionListener); 
    } 
  }
  
  public FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment paramFragment) {
    addOp(new Op(8, paramFragment));
    return this;
  }
  
  public FragmentTransaction setReorderingAllowed(boolean paramBoolean) {
    this.mReorderingAllowed = paramBoolean;
    return this;
  }
  
  public FragmentTransaction setTransition(int paramInt) {
    this.mTransition = paramInt;
    return this;
  }
  
  public FragmentTransaction setTransitionStyle(int paramInt) {
    this.mTransitionStyle = paramInt;
    return this;
  }
  
  public FragmentTransaction show(Fragment paramFragment) {
    addOp(new Op(5, paramFragment));
    return this;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("BackStackEntry{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    if (this.mIndex >= 0) {
      stringBuilder.append(" #");
      stringBuilder.append(this.mIndex);
    } 
    if (this.mName != null) {
      stringBuilder.append(" ");
      stringBuilder.append(this.mName);
    } 
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  Fragment trackAddedFragmentsInPop(ArrayList<Fragment> paramArrayList, Fragment paramFragment) {
    int i = 0;
    Fragment fragment = paramFragment;
    while (i < this.mOps.size()) {
      Op op = this.mOps.get(i);
      paramFragment = fragment;
      switch (op.cmd) {
        default:
          paramFragment = fragment;
        case 2:
        case 4:
        case 5:
          i++;
          fragment = paramFragment;
          continue;
        case 1:
        case 7:
          paramArrayList.remove(op.fragment);
          paramFragment = fragment;
        case 3:
        case 6:
          paramArrayList.add(op.fragment);
          paramFragment = fragment;
        case 9:
          paramFragment = op.fragment;
        case 8:
          break;
      } 
      paramFragment = null;
    } 
    return fragment;
  }
  
  static final class Op {
    int cmd;
    
    int enterAnim;
    
    int exitAnim;
    
    Fragment fragment;
    
    int popEnterAnim;
    
    int popExitAnim;
    
    Op() {}
    
    Op(int param1Int, Fragment param1Fragment) {
      this.cmd = param1Int;
      this.fragment = param1Fragment;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/fragment/app/BackStackRecord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
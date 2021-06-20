package androidx.fragment.app;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class FragmentTransition {
  private static final int[] INVERSE_OPS = new int[] { 0, 3, 0, 1, 5, 4, 7, 6, 9, 8 };
  
  private static final FragmentTransitionImpl PLATFORM_IMPL;
  
  private static final FragmentTransitionImpl SUPPORT_IMPL = resolveSupportImpl();
  
  private static void addSharedElementsWithMatchingNames(ArrayList<View> paramArrayList, ArrayMap<String, View> paramArrayMap, Collection<String> paramCollection) {
    for (int i = paramArrayMap.size() - 1; i >= 0; i--) {
      View view = (View)paramArrayMap.valueAt(i);
      if (paramCollection.contains(ViewCompat.getTransitionName(view)))
        paramArrayList.add(view); 
    } 
  }
  
  private static void addToFirstInLastOut(BackStackRecord paramBackStackRecord, BackStackRecord.Op paramOp, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_1
    //   1: getfield fragment : Landroidx/fragment/app/Fragment;
    //   4: astore #16
    //   6: aload #16
    //   8: ifnonnull -> 12
    //   11: return
    //   12: aload #16
    //   14: getfield mContainerId : I
    //   17: istore #12
    //   19: iload #12
    //   21: ifeq -> 11
    //   24: iload_3
    //   25: ifeq -> 331
    //   28: getstatic androidx/fragment/app/FragmentTransition.INVERSE_OPS : [I
    //   31: aload_1
    //   32: getfield cmd : I
    //   35: iaload
    //   36: istore #8
    //   38: iconst_0
    //   39: istore #14
    //   41: iconst_0
    //   42: istore #10
    //   44: iconst_0
    //   45: istore #11
    //   47: iconst_0
    //   48: istore #9
    //   50: iload #11
    //   52: istore #5
    //   54: iload #14
    //   56: istore #13
    //   58: iload #9
    //   60: istore #6
    //   62: iload #10
    //   64: istore #7
    //   66: iload #8
    //   68: tableswitch default -> 112, 1 -> 402, 2 -> 128, 3 -> 536, 4 -> 456, 5 -> 340, 6 -> 536, 7 -> 402
    //   112: iload #10
    //   114: istore #7
    //   116: iload #9
    //   118: istore #6
    //   120: iload #14
    //   122: istore #13
    //   124: iload #11
    //   126: istore #5
    //   128: aload_2
    //   129: iload #12
    //   131: invokevirtual get : (I)Ljava/lang/Object;
    //   134: checkcast androidx/fragment/app/FragmentTransition$FragmentContainerTransition
    //   137: astore #15
    //   139: aload #15
    //   141: astore_1
    //   142: iload #13
    //   144: ifeq -> 172
    //   147: aload #15
    //   149: aload_2
    //   150: iload #12
    //   152: invokestatic ensureContainer : (Landroidx/fragment/app/FragmentTransition$FragmentContainerTransition;Landroid/util/SparseArray;I)Landroidx/fragment/app/FragmentTransition$FragmentContainerTransition;
    //   155: astore_1
    //   156: aload_1
    //   157: aload #16
    //   159: putfield lastIn : Landroidx/fragment/app/Fragment;
    //   162: aload_1
    //   163: iload_3
    //   164: putfield lastInIsPop : Z
    //   167: aload_1
    //   168: aload_0
    //   169: putfield lastInTransaction : Landroidx/fragment/app/BackStackRecord;
    //   172: iload #4
    //   174: ifne -> 249
    //   177: iload #6
    //   179: ifeq -> 249
    //   182: aload_1
    //   183: ifnull -> 200
    //   186: aload_1
    //   187: getfield firstOut : Landroidx/fragment/app/Fragment;
    //   190: aload #16
    //   192: if_acmpne -> 200
    //   195: aload_1
    //   196: aconst_null
    //   197: putfield firstOut : Landroidx/fragment/app/Fragment;
    //   200: aload_0
    //   201: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   204: astore #15
    //   206: aload #16
    //   208: getfield mState : I
    //   211: iconst_1
    //   212: if_icmpge -> 249
    //   215: aload #15
    //   217: getfield mCurState : I
    //   220: iconst_1
    //   221: if_icmplt -> 249
    //   224: aload_0
    //   225: getfield mReorderingAllowed : Z
    //   228: ifne -> 249
    //   231: aload #15
    //   233: aload #16
    //   235: invokevirtual makeActive : (Landroidx/fragment/app/Fragment;)V
    //   238: aload #15
    //   240: aload #16
    //   242: iconst_1
    //   243: iconst_0
    //   244: iconst_0
    //   245: iconst_0
    //   246: invokevirtual moveToState : (Landroidx/fragment/app/Fragment;IIIZ)V
    //   249: aload_1
    //   250: astore #15
    //   252: iload #5
    //   254: ifeq -> 299
    //   257: aload_1
    //   258: ifnull -> 271
    //   261: aload_1
    //   262: astore #15
    //   264: aload_1
    //   265: getfield firstOut : Landroidx/fragment/app/Fragment;
    //   268: ifnonnull -> 299
    //   271: aload_1
    //   272: aload_2
    //   273: iload #12
    //   275: invokestatic ensureContainer : (Landroidx/fragment/app/FragmentTransition$FragmentContainerTransition;Landroid/util/SparseArray;I)Landroidx/fragment/app/FragmentTransition$FragmentContainerTransition;
    //   278: astore #15
    //   280: aload #15
    //   282: aload #16
    //   284: putfield firstOut : Landroidx/fragment/app/Fragment;
    //   287: aload #15
    //   289: iload_3
    //   290: putfield firstOutIsPop : Z
    //   293: aload #15
    //   295: aload_0
    //   296: putfield firstOutTransaction : Landroidx/fragment/app/BackStackRecord;
    //   299: iload #4
    //   301: ifne -> 11
    //   304: iload #7
    //   306: ifeq -> 11
    //   309: aload #15
    //   311: ifnull -> 11
    //   314: aload #15
    //   316: getfield lastIn : Landroidx/fragment/app/Fragment;
    //   319: aload #16
    //   321: if_acmpne -> 11
    //   324: aload #15
    //   326: aconst_null
    //   327: putfield lastIn : Landroidx/fragment/app/Fragment;
    //   330: return
    //   331: aload_1
    //   332: getfield cmd : I
    //   335: istore #8
    //   337: goto -> 38
    //   340: iload #4
    //   342: ifeq -> 392
    //   345: aload #16
    //   347: getfield mHiddenChanged : Z
    //   350: ifeq -> 386
    //   353: aload #16
    //   355: getfield mHidden : Z
    //   358: ifne -> 386
    //   361: aload #16
    //   363: getfield mAdded : Z
    //   366: ifeq -> 386
    //   369: iconst_1
    //   370: istore #13
    //   372: iconst_1
    //   373: istore #6
    //   375: iload #11
    //   377: istore #5
    //   379: iload #10
    //   381: istore #7
    //   383: goto -> 128
    //   386: iconst_0
    //   387: istore #13
    //   389: goto -> 372
    //   392: aload #16
    //   394: getfield mHidden : Z
    //   397: istore #13
    //   399: goto -> 372
    //   402: iload #4
    //   404: ifeq -> 428
    //   407: aload #16
    //   409: getfield mIsNewlyAdded : Z
    //   412: istore #13
    //   414: iconst_1
    //   415: istore #6
    //   417: iload #11
    //   419: istore #5
    //   421: iload #10
    //   423: istore #7
    //   425: goto -> 128
    //   428: aload #16
    //   430: getfield mAdded : Z
    //   433: ifne -> 450
    //   436: aload #16
    //   438: getfield mHidden : Z
    //   441: ifne -> 450
    //   444: iconst_1
    //   445: istore #13
    //   447: goto -> 414
    //   450: iconst_0
    //   451: istore #13
    //   453: goto -> 447
    //   456: iload #4
    //   458: ifeq -> 508
    //   461: aload #16
    //   463: getfield mHiddenChanged : Z
    //   466: ifeq -> 502
    //   469: aload #16
    //   471: getfield mAdded : Z
    //   474: ifeq -> 502
    //   477: aload #16
    //   479: getfield mHidden : Z
    //   482: ifeq -> 502
    //   485: iconst_1
    //   486: istore #5
    //   488: iconst_1
    //   489: istore #7
    //   491: iload #14
    //   493: istore #13
    //   495: iload #9
    //   497: istore #6
    //   499: goto -> 128
    //   502: iconst_0
    //   503: istore #5
    //   505: goto -> 488
    //   508: aload #16
    //   510: getfield mAdded : Z
    //   513: ifeq -> 530
    //   516: aload #16
    //   518: getfield mHidden : Z
    //   521: ifne -> 530
    //   524: iconst_1
    //   525: istore #5
    //   527: goto -> 488
    //   530: iconst_0
    //   531: istore #5
    //   533: goto -> 527
    //   536: iload #4
    //   538: ifeq -> 601
    //   541: aload #16
    //   543: getfield mAdded : Z
    //   546: ifne -> 595
    //   549: aload #16
    //   551: getfield mView : Landroid/view/View;
    //   554: ifnull -> 595
    //   557: aload #16
    //   559: getfield mView : Landroid/view/View;
    //   562: invokevirtual getVisibility : ()I
    //   565: ifne -> 595
    //   568: aload #16
    //   570: getfield mPostponedAlpha : F
    //   573: fconst_0
    //   574: fcmpl
    //   575: iflt -> 595
    //   578: iconst_1
    //   579: istore #5
    //   581: iconst_1
    //   582: istore #7
    //   584: iload #14
    //   586: istore #13
    //   588: iload #9
    //   590: istore #6
    //   592: goto -> 128
    //   595: iconst_0
    //   596: istore #5
    //   598: goto -> 581
    //   601: aload #16
    //   603: getfield mAdded : Z
    //   606: ifeq -> 623
    //   609: aload #16
    //   611: getfield mHidden : Z
    //   614: ifne -> 623
    //   617: iconst_1
    //   618: istore #5
    //   620: goto -> 581
    //   623: iconst_0
    //   624: istore #5
    //   626: goto -> 620
  }
  
  public static void calculateFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean) {
    int j = paramBackStackRecord.mOps.size();
    for (int i = 0; i < j; i++)
      addToFirstInLastOut(paramBackStackRecord, paramBackStackRecord.mOps.get(i), paramSparseArray, false, paramBoolean); 
  }
  
  private static ArrayMap<String, String> calculateNameOverrides(int paramInt1, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt2, int paramInt3) {
    ArrayMap<String, String> arrayMap = new ArrayMap();
    label26: while (--paramInt3 >= paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(paramInt3);
      if (!backStackRecord.interactsWith(paramInt1))
        continue; 
      boolean bool = ((Boolean)paramArrayList1.get(paramInt3)).booleanValue();
      if (backStackRecord.mSharedElementSourceNames != null) {
        ArrayList<String> arrayList1;
        ArrayList<String> arrayList2;
        int j = backStackRecord.mSharedElementSourceNames.size();
        if (bool) {
          arrayList2 = backStackRecord.mSharedElementSourceNames;
          arrayList1 = backStackRecord.mSharedElementTargetNames;
        } else {
          arrayList1 = backStackRecord.mSharedElementSourceNames;
          arrayList2 = backStackRecord.mSharedElementTargetNames;
        } 
        int i = 0;
        while (true) {
          if (i < j) {
            String str1 = arrayList1.get(i);
            String str2 = arrayList2.get(i);
            String str3 = (String)arrayMap.remove(str2);
            if (str3 != null) {
              arrayMap.put(str1, str3);
            } else {
              arrayMap.put(str1, str2);
            } 
            i++;
            continue;
          } 
          paramInt3--;
          continue label26;
        } 
      } 
      continue;
    } 
    return arrayMap;
  }
  
  public static void calculatePopFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean) {
    if (paramBackStackRecord.mManager.mContainer.onHasView()) {
      int i = paramBackStackRecord.mOps.size() - 1;
      while (true) {
        if (i >= 0) {
          addToFirstInLastOut(paramBackStackRecord, paramBackStackRecord.mOps.get(i), paramSparseArray, true, paramBoolean);
          i--;
          continue;
        } 
        return;
      } 
    } 
  }
  
  static void callSharedElementStartEnd(Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean1, ArrayMap<String, View> paramArrayMap, boolean paramBoolean2) {
    SharedElementCallback sharedElementCallback;
    ArrayList<Object> arrayList1;
    ArrayList<Object> arrayList2;
    if (paramBoolean1) {
      sharedElementCallback = paramFragment2.getEnterTransitionCallback();
    } else {
      sharedElementCallback = sharedElementCallback.getEnterTransitionCallback();
    } 
    if (sharedElementCallback != null) {
      int i;
      arrayList1 = new ArrayList();
      arrayList2 = new ArrayList();
      if (paramArrayMap == null) {
        i = 0;
      } else {
        i = paramArrayMap.size();
      } 
      int j;
      for (j = 0; j < i; j++) {
        arrayList2.add(paramArrayMap.keyAt(j));
        arrayList1.add(paramArrayMap.valueAt(j));
      } 
      if (paramBoolean2) {
        sharedElementCallback.onSharedElementStart(arrayList2, arrayList1, null);
        return;
      } 
    } else {
      return;
    } 
    sharedElementCallback.onSharedElementEnd(arrayList2, arrayList1, null);
  }
  
  private static boolean canHandleAll(FragmentTransitionImpl paramFragmentTransitionImpl, List<Object> paramList) {
    int i = 0;
    int j = paramList.size();
    while (i < j) {
      if (!paramFragmentTransitionImpl.canHandle(paramList.get(i)))
        return false; 
      i++;
    } 
    return true;
  }
  
  static ArrayMap<String, View> captureInSharedElements(FragmentTransitionImpl paramFragmentTransitionImpl, ArrayMap<String, String> paramArrayMap, Object<String, View> paramObject, FragmentContainerTransition paramFragmentContainerTransition) {
    ArrayList<String> arrayList;
    Fragment fragment = paramFragmentContainerTransition.lastIn;
    View view = fragment.getView();
    if (paramArrayMap.isEmpty() || paramObject == null || view == null) {
      paramArrayMap.clear();
      return null;
    } 
    ArrayMap<String, View> arrayMap = new ArrayMap();
    paramFragmentTransitionImpl.findNamedViews((Map<String, View>)arrayMap, view);
    BackStackRecord backStackRecord = paramFragmentContainerTransition.lastInTransaction;
    if (paramFragmentContainerTransition.lastInIsPop) {
      paramObject = (Object<String, View>)fragment.getExitTransitionCallback();
      arrayList = backStackRecord.mSharedElementSourceNames;
    } else {
      paramObject = (Object<String, View>)fragment.getEnterTransitionCallback();
      arrayList = ((BackStackRecord)arrayList).mSharedElementTargetNames;
    } 
    if (arrayList != null) {
      arrayMap.retainAll(arrayList);
      arrayMap.retainAll(paramArrayMap.values());
    } 
    if (paramObject != null) {
      paramObject.onMapSharedElements(arrayList, (Map)arrayMap);
      int i = arrayList.size() - 1;
      while (true) {
        paramObject = (Object<String, View>)arrayMap;
        if (i >= 0) {
          String str = arrayList.get(i);
          paramObject = (Object<String, View>)arrayMap.get(str);
          if (paramObject == null) {
            paramObject = (Object<String, View>)findKeyForValue(paramArrayMap, str);
            if (paramObject != null)
              paramArrayMap.remove(paramObject); 
          } else if (!str.equals(ViewCompat.getTransitionName((View)paramObject))) {
            str = findKeyForValue(paramArrayMap, str);
            if (str != null)
              paramArrayMap.put(str, ViewCompat.getTransitionName((View)paramObject)); 
          } 
          i--;
          continue;
        } 
        return (ArrayMap<String, View>)paramObject;
      } 
    } 
    retainValues(paramArrayMap, arrayMap);
    return arrayMap;
  }
  
  private static ArrayMap<String, View> captureOutSharedElements(FragmentTransitionImpl paramFragmentTransitionImpl, ArrayMap<String, String> paramArrayMap, Object<String, View> paramObject, FragmentContainerTransition paramFragmentContainerTransition) {
    ArrayList<String> arrayList;
    if (paramArrayMap.isEmpty() || paramObject == null) {
      paramArrayMap.clear();
      return null;
    } 
    paramObject = (Object<String, View>)paramFragmentContainerTransition.firstOut;
    ArrayMap<String, View> arrayMap = new ArrayMap();
    paramFragmentTransitionImpl.findNamedViews((Map<String, View>)arrayMap, paramObject.getView());
    BackStackRecord backStackRecord = paramFragmentContainerTransition.firstOutTransaction;
    if (paramFragmentContainerTransition.firstOutIsPop) {
      paramObject = (Object<String, View>)paramObject.getEnterTransitionCallback();
      arrayList = backStackRecord.mSharedElementTargetNames;
    } else {
      paramObject = (Object<String, View>)paramObject.getExitTransitionCallback();
      arrayList = ((BackStackRecord)arrayList).mSharedElementSourceNames;
    } 
    arrayMap.retainAll(arrayList);
    if (paramObject != null) {
      paramObject.onMapSharedElements(arrayList, (Map)arrayMap);
      int i = arrayList.size() - 1;
      while (true) {
        paramObject = (Object<String, View>)arrayMap;
        if (i >= 0) {
          String str = arrayList.get(i);
          paramObject = (Object<String, View>)arrayMap.get(str);
          if (paramObject == null) {
            paramArrayMap.remove(str);
          } else if (!str.equals(ViewCompat.getTransitionName((View)paramObject))) {
            str = (String)paramArrayMap.remove(str);
            paramArrayMap.put(ViewCompat.getTransitionName((View)paramObject), str);
          } 
          i--;
          continue;
        } 
        return (ArrayMap<String, View>)paramObject;
      } 
    } 
    paramArrayMap.retainAll(arrayMap.keySet());
    return arrayMap;
  }
  
  private static FragmentTransitionImpl chooseImpl(Fragment paramFragment1, Fragment paramFragment2) {
    ArrayList<Object> arrayList = new ArrayList();
    if (paramFragment1 != null) {
      Object object2 = paramFragment1.getExitTransition();
      if (object2 != null)
        arrayList.add(object2); 
      object2 = paramFragment1.getReturnTransition();
      if (object2 != null)
        arrayList.add(object2); 
      Object object1 = paramFragment1.getSharedElementReturnTransition();
      if (object1 != null)
        arrayList.add(object1); 
    } 
    if (paramFragment2 != null) {
      Object object = paramFragment2.getEnterTransition();
      if (object != null)
        arrayList.add(object); 
      object = paramFragment2.getReenterTransition();
      if (object != null)
        arrayList.add(object); 
      object = paramFragment2.getSharedElementEnterTransition();
      if (object != null)
        arrayList.add(object); 
    } 
    if (!arrayList.isEmpty()) {
      if (PLATFORM_IMPL != null && canHandleAll(PLATFORM_IMPL, arrayList))
        return PLATFORM_IMPL; 
      if (SUPPORT_IMPL != null && canHandleAll(SUPPORT_IMPL, arrayList))
        return SUPPORT_IMPL; 
      if (PLATFORM_IMPL != null || SUPPORT_IMPL != null)
        throw new IllegalArgumentException("Invalid Transition types"); 
    } 
    return null;
  }
  
  static ArrayList<View> configureEnteringExitingViews(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject, Fragment paramFragment, ArrayList<View> paramArrayList, View paramView) {
    ArrayList<View> arrayList = null;
    if (paramObject != null) {
      ArrayList<View> arrayList1 = new ArrayList();
      View view = paramFragment.getView();
      if (view != null)
        paramFragmentTransitionImpl.captureTransitioningViews(arrayList1, view); 
      if (paramArrayList != null)
        arrayList1.removeAll(paramArrayList); 
      arrayList = arrayList1;
      if (!arrayList1.isEmpty()) {
        arrayList1.add(paramView);
        paramFragmentTransitionImpl.addTargets(paramObject, arrayList1);
        arrayList = arrayList1;
      } 
    } 
    return arrayList;
  }
  
  private static Object configureSharedElementsOrdered(final FragmentTransitionImpl impl, ViewGroup paramViewGroup, final View nonExistentView, final ArrayMap<String, String> nameOverrides, final FragmentContainerTransition fragments, final ArrayList<View> sharedElementsOut, final ArrayList<View> sharedElementsIn, final Object enterTransition, final Object inEpicenter) {
    final Object finalSharedElementTransition;
    final Fragment inFragment = fragments.lastIn;
    final Fragment outFragment = fragments.firstOut;
    if (fragment1 == null || fragment2 == null)
      return null; 
    final boolean inIsPop = fragments.lastInIsPop;
    if (nameOverrides.isEmpty()) {
      object = null;
    } else {
      object = getSharedElementTransition(impl, fragment1, fragment2, bool);
    } 
    ArrayMap<String, View> arrayMap = captureOutSharedElements(impl, nameOverrides, object, fragments);
    if (nameOverrides.isEmpty()) {
      object = null;
    } else {
      sharedElementsOut.addAll(arrayMap.values());
    } 
    if (enterTransition == null && inEpicenter == null && object == null)
      return null; 
    callSharedElementStartEnd(fragment1, fragment2, bool, arrayMap, true);
    if (object != null) {
      Rect rect = new Rect();
      impl.setSharedElementTargets(object, nonExistentView, sharedElementsOut);
      setOutEpicenter(impl, object, inEpicenter, arrayMap, fragments.firstOutIsPop, fragments.firstOutTransaction);
      inEpicenter = rect;
      if (enterTransition != null) {
        impl.setEpicenter(enterTransition, rect);
        inEpicenter = rect;
      } 
      OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
            public void run() {
              ArrayMap<String, View> arrayMap = FragmentTransition.captureInSharedElements(impl, nameOverrides, finalSharedElementTransition, fragments);
              if (arrayMap != null) {
                sharedElementsIn.addAll(arrayMap.values());
                sharedElementsIn.add(nonExistentView);
              } 
              FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, arrayMap, false);
              if (finalSharedElementTransition != null) {
                impl.swapSharedElementTargets(finalSharedElementTransition, sharedElementsOut, sharedElementsIn);
                View view = FragmentTransition.getInEpicenterView(arrayMap, fragments, enterTransition, inIsPop);
                if (view != null)
                  impl.getBoundsOnScreen(view, inEpicenter); 
              } 
            }
          });
      return object;
    } 
    inEpicenter = null;
    OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
          public void run() {
            ArrayMap<String, View> arrayMap = FragmentTransition.captureInSharedElements(impl, nameOverrides, finalSharedElementTransition, fragments);
            if (arrayMap != null) {
              sharedElementsIn.addAll(arrayMap.values());
              sharedElementsIn.add(nonExistentView);
            } 
            FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, arrayMap, false);
            if (finalSharedElementTransition != null) {
              impl.swapSharedElementTargets(finalSharedElementTransition, sharedElementsOut, sharedElementsIn);
              View view = FragmentTransition.getInEpicenterView(arrayMap, fragments, enterTransition, inIsPop);
              if (view != null)
                impl.getBoundsOnScreen(view, inEpicenter); 
            } 
          }
        });
    return object;
  }
  
  private static Object configureSharedElementsReordered(final FragmentTransitionImpl impl, ViewGroup paramViewGroup, final View epicenterView, final ArrayMap<String, String> epicenter, FragmentContainerTransition paramFragmentContainerTransition, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2, Object paramObject1, Object paramObject2) {
    Object<String, String> object;
    ArrayMap<String, String> arrayMap;
    final Fragment inFragment = paramFragmentContainerTransition.lastIn;
    final Fragment outFragment = paramFragmentContainerTransition.firstOut;
    if (fragment1 != null)
      fragment1.getView().setVisibility(0); 
    if (fragment1 == null || fragment2 == null)
      return null; 
    final boolean inIsPop = paramFragmentContainerTransition.lastInIsPop;
    if (epicenter.isEmpty()) {
      object = null;
    } else {
      object = (Object<String, String>)getSharedElementTransition(impl, fragment1, fragment2, bool);
    } 
    ArrayMap<String, View> arrayMap2 = captureOutSharedElements(impl, epicenter, object, paramFragmentContainerTransition);
    final ArrayMap<String, View> inSharedElements = captureInSharedElements(impl, epicenter, object, paramFragmentContainerTransition);
    if (epicenter.isEmpty()) {
      epicenter = null;
      if (arrayMap2 != null)
        arrayMap2.clear(); 
      object = (Object<String, String>)epicenter;
      if (arrayMap1 != null) {
        arrayMap1.clear();
        arrayMap = epicenter;
      } 
    } else {
      addSharedElementsWithMatchingNames(paramArrayList1, arrayMap2, epicenter.keySet());
      addSharedElementsWithMatchingNames(paramArrayList2, arrayMap1, epicenter.values());
    } 
    if (paramObject1 == null && paramObject2 == null && arrayMap == null)
      return null; 
    callSharedElementStartEnd(fragment1, fragment2, bool, arrayMap2, true);
    if (arrayMap != null) {
      paramArrayList2.add(epicenterView);
      impl.setSharedElementTargets(arrayMap, epicenterView, paramArrayList1);
      setOutEpicenter(impl, arrayMap, paramObject2, arrayMap2, paramFragmentContainerTransition.firstOutIsPop, paramFragmentContainerTransition.firstOutTransaction);
      Rect rect2 = new Rect();
      View view = getInEpicenterView(arrayMap1, paramFragmentContainerTransition, paramObject1, bool);
      epicenterView = view;
      final Rect epicenter = rect2;
      if (view != null) {
        impl.setEpicenter(paramObject1, rect2);
        rect1 = rect2;
        epicenterView = view;
      } 
      OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
            public void run() {
              FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, inSharedElements, false);
              if (epicenterView != null)
                impl.getBoundsOnScreen(epicenterView, epicenter); 
            }
          });
      return arrayMap;
    } 
    epicenter = null;
    epicenterView = null;
    OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
          public void run() {
            FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, inSharedElements, false);
            if (epicenterView != null)
              impl.getBoundsOnScreen(epicenterView, epicenter); 
          }
        });
    return arrayMap;
  }
  
  private static void configureTransitionsOrdered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap) {
    ViewGroup viewGroup = null;
    if (paramFragmentManagerImpl.mContainer.onHasView())
      viewGroup = (ViewGroup)paramFragmentManagerImpl.mContainer.onFindViewById(paramInt); 
    if (viewGroup != null) {
      Fragment fragment1 = paramFragmentContainerTransition.lastIn;
      Fragment fragment2 = paramFragmentContainerTransition.firstOut;
      FragmentTransitionImpl fragmentTransitionImpl = chooseImpl(fragment2, fragment1);
      if (fragmentTransitionImpl != null) {
        boolean bool1 = paramFragmentContainerTransition.lastInIsPop;
        boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
        Object object2 = getEnterTransition(fragmentTransitionImpl, fragment1, bool1);
        Object object1 = getExitTransition(fragmentTransitionImpl, fragment2, bool2);
        ArrayList<View> arrayList2 = new ArrayList();
        ArrayList<View> arrayList1 = new ArrayList();
        Object object3 = configureSharedElementsOrdered(fragmentTransitionImpl, viewGroup, paramView, paramArrayMap, paramFragmentContainerTransition, arrayList2, arrayList1, object2, object1);
        if (object2 != null || object3 != null || object1 != null) {
          ArrayList<View> arrayList = configureEnteringExitingViews(fragmentTransitionImpl, object1, fragment2, arrayList2, paramView);
          if (arrayList == null || arrayList.isEmpty())
            object1 = null; 
          fragmentTransitionImpl.addTarget(object2, paramView);
          Object object = mergeTransitions(fragmentTransitionImpl, object2, object1, object3, fragment1, paramFragmentContainerTransition.lastInIsPop);
          if (object != null) {
            arrayList2 = new ArrayList<View>();
            fragmentTransitionImpl.scheduleRemoveTargets(object, object2, arrayList2, object1, arrayList, object3, arrayList1);
            scheduleTargetChange(fragmentTransitionImpl, viewGroup, fragment1, paramView, arrayList1, object2, arrayList2, object1, arrayList);
            fragmentTransitionImpl.setNameOverridesOrdered((View)viewGroup, arrayList1, (Map<String, String>)paramArrayMap);
            fragmentTransitionImpl.beginDelayedTransition(viewGroup, object);
            fragmentTransitionImpl.scheduleNameReset(viewGroup, arrayList1, (Map<String, String>)paramArrayMap);
            return;
          } 
        } 
      } 
    } 
  }
  
  private static void configureTransitionsReordered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap) {
    ViewGroup viewGroup = null;
    if (paramFragmentManagerImpl.mContainer.onHasView())
      viewGroup = (ViewGroup)paramFragmentManagerImpl.mContainer.onFindViewById(paramInt); 
    if (viewGroup != null) {
      Fragment fragment2 = paramFragmentContainerTransition.lastIn;
      Fragment fragment1 = paramFragmentContainerTransition.firstOut;
      FragmentTransitionImpl fragmentTransitionImpl = chooseImpl(fragment1, fragment2);
      if (fragmentTransitionImpl != null) {
        boolean bool1 = paramFragmentContainerTransition.lastInIsPop;
        boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
        ArrayList<View> arrayList1 = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList();
        Object object2 = getEnterTransition(fragmentTransitionImpl, fragment2, bool1);
        Object object3 = getExitTransition(fragmentTransitionImpl, fragment1, bool2);
        Object object1 = configureSharedElementsReordered(fragmentTransitionImpl, viewGroup, paramView, paramArrayMap, paramFragmentContainerTransition, arrayList2, arrayList1, object2, object3);
        if (object2 != null || object1 != null || object3 != null) {
          ArrayList<View> arrayList4 = configureEnteringExitingViews(fragmentTransitionImpl, object3, fragment1, arrayList2, paramView);
          ArrayList<View> arrayList3 = configureEnteringExitingViews(fragmentTransitionImpl, object2, fragment2, arrayList1, paramView);
          setViewVisibility(arrayList3, 4);
          Object object = mergeTransitions(fragmentTransitionImpl, object2, object3, object1, fragment2, bool1);
          if (object != null) {
            replaceHide(fragmentTransitionImpl, object3, fragment1, arrayList4);
            ArrayList<String> arrayList = fragmentTransitionImpl.prepareSetNameOverridesReordered(arrayList1);
            fragmentTransitionImpl.scheduleRemoveTargets(object, object2, arrayList3, object3, arrayList4, object1, arrayList1);
            fragmentTransitionImpl.beginDelayedTransition(viewGroup, object);
            fragmentTransitionImpl.setNameOverridesReordered((View)viewGroup, arrayList2, arrayList1, arrayList, (Map<String, String>)paramArrayMap);
            setViewVisibility(arrayList3, 0);
            fragmentTransitionImpl.swapSharedElementTargets(object1, arrayList2, arrayList1);
            return;
          } 
        } 
      } 
    } 
  }
  
  private static FragmentContainerTransition ensureContainer(FragmentContainerTransition paramFragmentContainerTransition, SparseArray<FragmentContainerTransition> paramSparseArray, int paramInt) {
    FragmentContainerTransition fragmentContainerTransition = paramFragmentContainerTransition;
    if (paramFragmentContainerTransition == null) {
      fragmentContainerTransition = new FragmentContainerTransition();
      paramSparseArray.put(paramInt, fragmentContainerTransition);
    } 
    return fragmentContainerTransition;
  }
  
  private static String findKeyForValue(ArrayMap<String, String> paramArrayMap, String paramString) {
    int j = paramArrayMap.size();
    for (int i = 0; i < j; i++) {
      if (paramString.equals(paramArrayMap.valueAt(i)))
        return (String)paramArrayMap.keyAt(i); 
    } 
    return null;
  }
  
  private static Object getEnterTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment, boolean paramBoolean) {
    if (paramFragment == null)
      return null; 
    if (paramBoolean) {
      object = paramFragment.getReenterTransition();
      return paramFragmentTransitionImpl.cloneTransition(object);
    } 
    Object object = object.getEnterTransition();
    return paramFragmentTransitionImpl.cloneTransition(object);
  }
  
  private static Object getExitTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment, boolean paramBoolean) {
    if (paramFragment == null)
      return null; 
    if (paramBoolean) {
      object = paramFragment.getReturnTransition();
      return paramFragmentTransitionImpl.cloneTransition(object);
    } 
    Object object = object.getExitTransition();
    return paramFragmentTransitionImpl.cloneTransition(object);
  }
  
  static View getInEpicenterView(ArrayMap<String, View> paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, Object paramObject, boolean paramBoolean) {
    BackStackRecord backStackRecord = paramFragmentContainerTransition.lastInTransaction;
    if (paramObject != null && paramArrayMap != null && backStackRecord.mSharedElementSourceNames != null && !backStackRecord.mSharedElementSourceNames.isEmpty()) {
      if (paramBoolean) {
        str = backStackRecord.mSharedElementSourceNames.get(0);
        return (View)paramArrayMap.get(str);
      } 
      String str = ((BackStackRecord)str).mSharedElementTargetNames.get(0);
      return (View)paramArrayMap.get(str);
    } 
    return null;
  }
  
  private static Object getSharedElementTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean) {
    if (paramFragment1 == null || paramFragment2 == null)
      return null; 
    if (paramBoolean) {
      object = paramFragment2.getSharedElementReturnTransition();
      return paramFragmentTransitionImpl.wrapTransitionInSet(paramFragmentTransitionImpl.cloneTransition(object));
    } 
    Object object = object.getSharedElementEnterTransition();
    return paramFragmentTransitionImpl.wrapTransitionInSet(paramFragmentTransitionImpl.cloneTransition(object));
  }
  
  private static Object mergeTransitions(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject1, Object paramObject2, Object paramObject3, Fragment paramFragment, boolean paramBoolean) {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (paramObject1 != null) {
      bool1 = bool2;
      if (paramObject2 != null) {
        bool1 = bool2;
        if (paramFragment != null)
          if (paramBoolean) {
            bool1 = paramFragment.getAllowReturnTransitionOverlap();
          } else {
            bool1 = paramFragment.getAllowEnterTransitionOverlap();
          }  
      } 
    } 
    return bool1 ? paramFragmentTransitionImpl.mergeTransitionsTogether(paramObject2, paramObject1, paramObject3) : paramFragmentTransitionImpl.mergeTransitionsInSequence(paramObject2, paramObject1, paramObject3);
  }
  
  private static void replaceHide(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject, Fragment paramFragment, final ArrayList<View> exitingViews) {
    if (paramFragment != null && paramObject != null && paramFragment.mAdded && paramFragment.mHidden && paramFragment.mHiddenChanged) {
      paramFragment.setHideReplaced(true);
      paramFragmentTransitionImpl.scheduleHideFragmentView(paramObject, paramFragment.getView(), exitingViews);
      OneShotPreDrawListener.add((View)paramFragment.mContainer, new Runnable() {
            public void run() {
              FragmentTransition.setViewVisibility(exitingViews, 4);
            }
          });
    } 
  }
  
  private static FragmentTransitionImpl resolveSupportImpl() {
    try {
      return Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  private static void retainValues(ArrayMap<String, String> paramArrayMap, ArrayMap<String, View> paramArrayMap1) {
    for (int i = paramArrayMap.size() - 1; i >= 0; i--) {
      if (!paramArrayMap1.containsKey(paramArrayMap.valueAt(i)))
        paramArrayMap.removeAt(i); 
    } 
  }
  
  private static void scheduleTargetChange(final FragmentTransitionImpl impl, ViewGroup paramViewGroup, final Fragment inFragment, final View nonExistentView, final ArrayList<View> sharedElementsIn, final Object enterTransition, final ArrayList<View> enteringViews, final Object exitTransition, final ArrayList<View> exitingViews) {
    OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
          public void run() {
            if (enterTransition != null) {
              impl.removeTarget(enterTransition, nonExistentView);
              ArrayList<View> arrayList = FragmentTransition.configureEnteringExitingViews(impl, enterTransition, inFragment, sharedElementsIn, nonExistentView);
              enteringViews.addAll(arrayList);
            } 
            if (exitingViews != null) {
              if (exitTransition != null) {
                ArrayList<View> arrayList = new ArrayList();
                arrayList.add(nonExistentView);
                impl.replaceTargets(exitTransition, exitingViews, arrayList);
              } 
              exitingViews.clear();
              exitingViews.add(nonExistentView);
            } 
          }
        });
  }
  
  private static void setOutEpicenter(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject1, Object paramObject2, ArrayMap<String, View> paramArrayMap, boolean paramBoolean, BackStackRecord paramBackStackRecord) {
    if (paramBackStackRecord.mSharedElementSourceNames != null && !paramBackStackRecord.mSharedElementSourceNames.isEmpty()) {
      String str;
      if (paramBoolean) {
        str = paramBackStackRecord.mSharedElementTargetNames.get(0);
      } else {
        str = ((BackStackRecord)str).mSharedElementSourceNames.get(0);
      } 
      View view = (View)paramArrayMap.get(str);
      paramFragmentTransitionImpl.setEpicenter(paramObject1, view);
      if (paramObject2 != null)
        paramFragmentTransitionImpl.setEpicenter(paramObject2, view); 
    } 
  }
  
  static void setViewVisibility(ArrayList<View> paramArrayList, int paramInt) {
    if (paramArrayList != null) {
      int i = paramArrayList.size() - 1;
      while (true) {
        if (i >= 0) {
          ((View)paramArrayList.get(i)).setVisibility(paramInt);
          i--;
          continue;
        } 
        return;
      } 
    } 
  }
  
  static void startTransitions(FragmentManagerImpl paramFragmentManagerImpl, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, boolean paramBoolean) {
    if (paramFragmentManagerImpl.mCurState >= 1) {
      SparseArray<FragmentContainerTransition> sparseArray = new SparseArray();
      int i;
      for (i = paramInt1; i < paramInt2; i++) {
        BackStackRecord backStackRecord = paramArrayList.get(i);
        if (((Boolean)paramArrayList1.get(i)).booleanValue()) {
          calculatePopFragments(backStackRecord, sparseArray, paramBoolean);
        } else {
          calculateFragments(backStackRecord, sparseArray, paramBoolean);
        } 
      } 
      if (sparseArray.size() != 0) {
        View view = new View(paramFragmentManagerImpl.mHost.getContext());
        int j = sparseArray.size();
        i = 0;
        while (true) {
          if (i < j) {
            int k = sparseArray.keyAt(i);
            ArrayMap<String, String> arrayMap = calculateNameOverrides(k, paramArrayList, paramArrayList1, paramInt1, paramInt2);
            FragmentContainerTransition fragmentContainerTransition = (FragmentContainerTransition)sparseArray.valueAt(i);
            if (paramBoolean) {
              configureTransitionsReordered(paramFragmentManagerImpl, k, fragmentContainerTransition, view, arrayMap);
            } else {
              configureTransitionsOrdered(paramFragmentManagerImpl, k, fragmentContainerTransition, view, arrayMap);
            } 
            i++;
            continue;
          } 
          return;
        } 
      } 
    } 
  }
  
  static boolean supportsTransition() {
    return (PLATFORM_IMPL != null || SUPPORT_IMPL != null);
  }
  
  static {
    FragmentTransitionImpl fragmentTransitionImpl;
  }
  
  static {
    if (Build.VERSION.SDK_INT >= 21) {
      fragmentTransitionImpl = new FragmentTransitionCompat21();
    } else {
      fragmentTransitionImpl = null;
    } 
    PLATFORM_IMPL = fragmentTransitionImpl;
  }
  
  static class FragmentContainerTransition {
    public Fragment firstOut;
    
    public boolean firstOutIsPop;
    
    public BackStackRecord firstOutTransaction;
    
    public Fragment lastIn;
    
    public boolean lastInIsPop;
    
    public BackStackRecord lastInTransaction;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/fragment/app/FragmentTransition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
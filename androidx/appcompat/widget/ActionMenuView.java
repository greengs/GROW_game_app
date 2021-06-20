package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;

public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
  static final int GENERATED_ITEM_PADDING = 4;
  
  static final int MIN_CELL_SIZE = 56;
  
  private static final String TAG = "ActionMenuView";
  
  private MenuPresenter.Callback mActionMenuPresenterCallback;
  
  private boolean mFormatItems;
  
  private int mFormatItemsWidth;
  
  private int mGeneratedItemPadding;
  
  private MenuBuilder mMenu;
  
  MenuBuilder.Callback mMenuBuilderCallback;
  
  private int mMinCellSize;
  
  OnMenuItemClickListener mOnMenuItemClickListener;
  
  private Context mPopupContext;
  
  private int mPopupTheme;
  
  private ActionMenuPresenter mPresenter;
  
  private boolean mReserveOverflow;
  
  public ActionMenuView(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public ActionMenuView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    setBaselineAligned(false);
    float f = (paramContext.getResources().getDisplayMetrics()).density;
    this.mMinCellSize = (int)(56.0F * f);
    this.mGeneratedItemPadding = (int)(4.0F * f);
    this.mPopupContext = paramContext;
    this.mPopupTheme = 0;
  }
  
  static int measureChildForCells(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   4: checkcast androidx/appcompat/widget/ActionMenuView$LayoutParams
    //   7: astore #9
    //   9: iload_3
    //   10: invokestatic getSize : (I)I
    //   13: iload #4
    //   15: isub
    //   16: iload_3
    //   17: invokestatic getMode : (I)I
    //   20: invokestatic makeMeasureSpec : (II)I
    //   23: istore #6
    //   25: aload_0
    //   26: instanceof androidx/appcompat/view/menu/ActionMenuItemView
    //   29: ifeq -> 176
    //   32: aload_0
    //   33: checkcast androidx/appcompat/view/menu/ActionMenuItemView
    //   36: astore #8
    //   38: aload #8
    //   40: ifnull -> 182
    //   43: aload #8
    //   45: invokevirtual hasText : ()Z
    //   48: ifeq -> 182
    //   51: iconst_1
    //   52: istore #4
    //   54: iconst_0
    //   55: istore #5
    //   57: iload #5
    //   59: istore_3
    //   60: iload_2
    //   61: ifle -> 131
    //   64: iload #4
    //   66: ifeq -> 77
    //   69: iload #5
    //   71: istore_3
    //   72: iload_2
    //   73: iconst_2
    //   74: if_icmplt -> 131
    //   77: aload_0
    //   78: iload_1
    //   79: iload_2
    //   80: imul
    //   81: ldc -2147483648
    //   83: invokestatic makeMeasureSpec : (II)I
    //   86: iload #6
    //   88: invokevirtual measure : (II)V
    //   91: aload_0
    //   92: invokevirtual getMeasuredWidth : ()I
    //   95: istore #5
    //   97: iload #5
    //   99: iload_1
    //   100: idiv
    //   101: istore_3
    //   102: iload_3
    //   103: istore_2
    //   104: iload #5
    //   106: iload_1
    //   107: irem
    //   108: ifeq -> 115
    //   111: iload_3
    //   112: iconst_1
    //   113: iadd
    //   114: istore_2
    //   115: iload_2
    //   116: istore_3
    //   117: iload #4
    //   119: ifeq -> 131
    //   122: iload_2
    //   123: istore_3
    //   124: iload_2
    //   125: iconst_2
    //   126: if_icmpge -> 131
    //   129: iconst_2
    //   130: istore_3
    //   131: aload #9
    //   133: getfield isOverflowButton : Z
    //   136: ifne -> 188
    //   139: iload #4
    //   141: ifeq -> 188
    //   144: iconst_1
    //   145: istore #7
    //   147: aload #9
    //   149: iload #7
    //   151: putfield expandable : Z
    //   154: aload #9
    //   156: iload_3
    //   157: putfield cellsUsed : I
    //   160: aload_0
    //   161: iload_3
    //   162: iload_1
    //   163: imul
    //   164: ldc 1073741824
    //   166: invokestatic makeMeasureSpec : (II)I
    //   169: iload #6
    //   171: invokevirtual measure : (II)V
    //   174: iload_3
    //   175: ireturn
    //   176: aconst_null
    //   177: astore #8
    //   179: goto -> 38
    //   182: iconst_0
    //   183: istore #4
    //   185: goto -> 54
    //   188: iconst_0
    //   189: istore #7
    //   191: goto -> 147
  }
  
  private void onMeasureExactFormat(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iload_2
    //   1: invokestatic getMode : (I)I
    //   4: istore #18
    //   6: iload_1
    //   7: invokestatic getSize : (I)I
    //   10: istore_1
    //   11: iload_2
    //   12: invokestatic getSize : (I)I
    //   15: istore #17
    //   17: aload_0
    //   18: invokevirtual getPaddingLeft : ()I
    //   21: istore #6
    //   23: aload_0
    //   24: invokevirtual getPaddingRight : ()I
    //   27: istore #7
    //   29: aload_0
    //   30: invokevirtual getPaddingTop : ()I
    //   33: aload_0
    //   34: invokevirtual getPaddingBottom : ()I
    //   37: iadd
    //   38: istore #23
    //   40: iload_2
    //   41: iload #23
    //   43: bipush #-2
    //   45: invokestatic getChildMeasureSpec : (III)I
    //   48: istore #19
    //   50: iload_1
    //   51: iload #6
    //   53: iload #7
    //   55: iadd
    //   56: isub
    //   57: istore #20
    //   59: iload #20
    //   61: aload_0
    //   62: getfield mMinCellSize : I
    //   65: idiv
    //   66: istore_1
    //   67: aload_0
    //   68: getfield mMinCellSize : I
    //   71: istore_2
    //   72: iload_1
    //   73: ifne -> 84
    //   76: aload_0
    //   77: iload #20
    //   79: iconst_0
    //   80: invokevirtual setMeasuredDimension : (II)V
    //   83: return
    //   84: aload_0
    //   85: getfield mMinCellSize : I
    //   88: iload #20
    //   90: iload_2
    //   91: irem
    //   92: iload_1
    //   93: idiv
    //   94: iadd
    //   95: istore #21
    //   97: iconst_0
    //   98: istore #6
    //   100: iconst_0
    //   101: istore #9
    //   103: iconst_0
    //   104: istore #8
    //   106: iconst_0
    //   107: istore #10
    //   109: iconst_0
    //   110: istore #7
    //   112: lconst_0
    //   113: lstore #25
    //   115: aload_0
    //   116: invokevirtual getChildCount : ()I
    //   119: istore #22
    //   121: iconst_0
    //   122: istore #11
    //   124: iload #11
    //   126: iload #22
    //   128: if_icmpge -> 435
    //   131: aload_0
    //   132: iload #11
    //   134: invokevirtual getChildAt : (I)Landroid/view/View;
    //   137: astore #32
    //   139: aload #32
    //   141: invokevirtual getVisibility : ()I
    //   144: bipush #8
    //   146: if_icmpne -> 174
    //   149: lload #25
    //   151: lstore #27
    //   153: iload #7
    //   155: istore #12
    //   157: iload #11
    //   159: iconst_1
    //   160: iadd
    //   161: istore #11
    //   163: iload #12
    //   165: istore #7
    //   167: lload #27
    //   169: lstore #25
    //   171: goto -> 124
    //   174: aload #32
    //   176: instanceof androidx/appcompat/view/menu/ActionMenuItemView
    //   179: istore #31
    //   181: iload #10
    //   183: iconst_1
    //   184: iadd
    //   185: istore #13
    //   187: iload #31
    //   189: ifeq -> 207
    //   192: aload #32
    //   194: aload_0
    //   195: getfield mGeneratedItemPadding : I
    //   198: iconst_0
    //   199: aload_0
    //   200: getfield mGeneratedItemPadding : I
    //   203: iconst_0
    //   204: invokevirtual setPadding : (IIII)V
    //   207: aload #32
    //   209: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   212: checkcast androidx/appcompat/widget/ActionMenuView$LayoutParams
    //   215: astore #33
    //   217: aload #33
    //   219: iconst_0
    //   220: putfield expanded : Z
    //   223: aload #33
    //   225: iconst_0
    //   226: putfield extraPixels : I
    //   229: aload #33
    //   231: iconst_0
    //   232: putfield cellsUsed : I
    //   235: aload #33
    //   237: iconst_0
    //   238: putfield expandable : Z
    //   241: aload #33
    //   243: iconst_0
    //   244: putfield leftMargin : I
    //   247: aload #33
    //   249: iconst_0
    //   250: putfield rightMargin : I
    //   253: iload #31
    //   255: ifeq -> 424
    //   258: aload #32
    //   260: checkcast androidx/appcompat/view/menu/ActionMenuItemView
    //   263: invokevirtual hasText : ()Z
    //   266: ifeq -> 424
    //   269: iconst_1
    //   270: istore #31
    //   272: aload #33
    //   274: iload #31
    //   276: putfield preventEdgeOffset : Z
    //   279: aload #33
    //   281: getfield isOverflowButton : Z
    //   284: ifeq -> 430
    //   287: iconst_1
    //   288: istore_2
    //   289: aload #32
    //   291: iload #21
    //   293: iload_2
    //   294: iload #19
    //   296: iload #23
    //   298: invokestatic measureChildForCells : (Landroid/view/View;IIII)I
    //   301: istore #24
    //   303: iload #9
    //   305: iload #24
    //   307: invokestatic max : (II)I
    //   310: istore #14
    //   312: iload #8
    //   314: istore_2
    //   315: aload #33
    //   317: getfield expandable : Z
    //   320: ifeq -> 328
    //   323: iload #8
    //   325: iconst_1
    //   326: iadd
    //   327: istore_2
    //   328: aload #33
    //   330: getfield isOverflowButton : Z
    //   333: ifeq -> 339
    //   336: iconst_1
    //   337: istore #7
    //   339: iload_1
    //   340: iload #24
    //   342: isub
    //   343: istore #15
    //   345: iload #6
    //   347: aload #32
    //   349: invokevirtual getMeasuredHeight : ()I
    //   352: invokestatic max : (II)I
    //   355: istore #16
    //   357: iload #15
    //   359: istore_1
    //   360: iload_2
    //   361: istore #8
    //   363: iload #7
    //   365: istore #12
    //   367: iload #14
    //   369: istore #9
    //   371: iload #16
    //   373: istore #6
    //   375: lload #25
    //   377: lstore #27
    //   379: iload #13
    //   381: istore #10
    //   383: iload #24
    //   385: iconst_1
    //   386: if_icmpne -> 157
    //   389: lload #25
    //   391: iconst_1
    //   392: iload #11
    //   394: ishl
    //   395: i2l
    //   396: lor
    //   397: lstore #27
    //   399: iload #15
    //   401: istore_1
    //   402: iload_2
    //   403: istore #8
    //   405: iload #7
    //   407: istore #12
    //   409: iload #14
    //   411: istore #9
    //   413: iload #16
    //   415: istore #6
    //   417: iload #13
    //   419: istore #10
    //   421: goto -> 157
    //   424: iconst_0
    //   425: istore #31
    //   427: goto -> 272
    //   430: iload_1
    //   431: istore_2
    //   432: goto -> 289
    //   435: iload #7
    //   437: ifeq -> 545
    //   440: iload #10
    //   442: iconst_2
    //   443: if_icmpne -> 545
    //   446: iconst_1
    //   447: istore #11
    //   449: iconst_0
    //   450: istore_2
    //   451: iload_1
    //   452: istore #12
    //   454: lload #25
    //   456: lstore #27
    //   458: iload #8
    //   460: ifle -> 639
    //   463: lload #25
    //   465: lstore #27
    //   467: iload #12
    //   469: ifle -> 639
    //   472: ldc 2147483647
    //   474: istore #13
    //   476: lconst_0
    //   477: lstore #29
    //   479: iconst_0
    //   480: istore #16
    //   482: iconst_0
    //   483: istore #14
    //   485: iload #14
    //   487: iload #22
    //   489: if_icmpge -> 621
    //   492: aload_0
    //   493: iload #14
    //   495: invokevirtual getChildAt : (I)Landroid/view/View;
    //   498: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   501: checkcast androidx/appcompat/widget/ActionMenuView$LayoutParams
    //   504: astore #32
    //   506: aload #32
    //   508: getfield expandable : Z
    //   511: ifne -> 551
    //   514: lload #29
    //   516: lstore #27
    //   518: iload #16
    //   520: istore_1
    //   521: iload #13
    //   523: istore #15
    //   525: iload #14
    //   527: iconst_1
    //   528: iadd
    //   529: istore #14
    //   531: iload #15
    //   533: istore #13
    //   535: iload_1
    //   536: istore #16
    //   538: lload #27
    //   540: lstore #29
    //   542: goto -> 485
    //   545: iconst_0
    //   546: istore #11
    //   548: goto -> 449
    //   551: aload #32
    //   553: getfield cellsUsed : I
    //   556: iload #13
    //   558: if_icmpge -> 579
    //   561: aload #32
    //   563: getfield cellsUsed : I
    //   566: istore #15
    //   568: lconst_1
    //   569: iload #14
    //   571: lshl
    //   572: lstore #27
    //   574: iconst_1
    //   575: istore_1
    //   576: goto -> 525
    //   579: iload #13
    //   581: istore #15
    //   583: iload #16
    //   585: istore_1
    //   586: lload #29
    //   588: lstore #27
    //   590: aload #32
    //   592: getfield cellsUsed : I
    //   595: iload #13
    //   597: if_icmpne -> 525
    //   600: lload #29
    //   602: lconst_1
    //   603: iload #14
    //   605: lshl
    //   606: lor
    //   607: lstore #27
    //   609: iload #16
    //   611: iconst_1
    //   612: iadd
    //   613: istore_1
    //   614: iload #13
    //   616: istore #15
    //   618: goto -> 525
    //   621: lload #25
    //   623: lload #29
    //   625: lor
    //   626: lstore #25
    //   628: iload #16
    //   630: iload #12
    //   632: if_icmple -> 848
    //   635: lload #25
    //   637: lstore #27
    //   639: iload #7
    //   641: ifne -> 1002
    //   644: iload #10
    //   646: iconst_1
    //   647: if_icmpne -> 1002
    //   650: iconst_1
    //   651: istore_1
    //   652: iload_2
    //   653: istore #7
    //   655: iload #12
    //   657: ifle -> 1158
    //   660: iload_2
    //   661: istore #7
    //   663: lload #27
    //   665: lconst_0
    //   666: lcmp
    //   667: ifeq -> 1158
    //   670: iload #12
    //   672: iload #10
    //   674: iconst_1
    //   675: isub
    //   676: if_icmplt -> 692
    //   679: iload_1
    //   680: ifne -> 692
    //   683: iload_2
    //   684: istore #7
    //   686: iload #9
    //   688: iconst_1
    //   689: if_icmple -> 1158
    //   692: lload #27
    //   694: invokestatic bitCount : (J)I
    //   697: i2f
    //   698: fstore #5
    //   700: fload #5
    //   702: fstore #4
    //   704: iload_1
    //   705: ifne -> 793
    //   708: fload #5
    //   710: fstore_3
    //   711: lconst_1
    //   712: lload #27
    //   714: land
    //   715: lconst_0
    //   716: lcmp
    //   717: ifeq -> 746
    //   720: fload #5
    //   722: fstore_3
    //   723: aload_0
    //   724: iconst_0
    //   725: invokevirtual getChildAt : (I)Landroid/view/View;
    //   728: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   731: checkcast androidx/appcompat/widget/ActionMenuView$LayoutParams
    //   734: getfield preventEdgeOffset : Z
    //   737: ifne -> 746
    //   740: fload #5
    //   742: ldc 0.5
    //   744: fsub
    //   745: fstore_3
    //   746: fload_3
    //   747: fstore #4
    //   749: iconst_1
    //   750: iload #22
    //   752: iconst_1
    //   753: isub
    //   754: ishl
    //   755: i2l
    //   756: lload #27
    //   758: land
    //   759: lconst_0
    //   760: lcmp
    //   761: ifeq -> 793
    //   764: fload_3
    //   765: fstore #4
    //   767: aload_0
    //   768: iload #22
    //   770: iconst_1
    //   771: isub
    //   772: invokevirtual getChildAt : (I)Landroid/view/View;
    //   775: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   778: checkcast androidx/appcompat/widget/ActionMenuView$LayoutParams
    //   781: getfield preventEdgeOffset : Z
    //   784: ifne -> 793
    //   787: fload_3
    //   788: ldc 0.5
    //   790: fsub
    //   791: fstore #4
    //   793: fload #4
    //   795: fconst_0
    //   796: fcmpl
    //   797: ifle -> 1007
    //   800: iload #12
    //   802: iload #21
    //   804: imul
    //   805: i2f
    //   806: fload #4
    //   808: fdiv
    //   809: f2i
    //   810: istore #7
    //   812: iconst_0
    //   813: istore #8
    //   815: iload #8
    //   817: iload #22
    //   819: if_icmpge -> 1155
    //   822: iconst_1
    //   823: iload #8
    //   825: ishl
    //   826: i2l
    //   827: lload #27
    //   829: land
    //   830: lconst_0
    //   831: lcmp
    //   832: ifne -> 1013
    //   835: iload_2
    //   836: istore_1
    //   837: iload #8
    //   839: iconst_1
    //   840: iadd
    //   841: istore #8
    //   843: iload_1
    //   844: istore_2
    //   845: goto -> 815
    //   848: iconst_0
    //   849: istore_1
    //   850: iload_1
    //   851: iload #22
    //   853: if_icmpge -> 997
    //   856: aload_0
    //   857: iload_1
    //   858: invokevirtual getChildAt : (I)Landroid/view/View;
    //   861: astore #32
    //   863: aload #32
    //   865: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   868: checkcast androidx/appcompat/widget/ActionMenuView$LayoutParams
    //   871: astore #33
    //   873: iconst_1
    //   874: iload_1
    //   875: ishl
    //   876: i2l
    //   877: lload #29
    //   879: land
    //   880: lconst_0
    //   881: lcmp
    //   882: ifne -> 930
    //   885: iload #12
    //   887: istore_2
    //   888: lload #25
    //   890: lstore #27
    //   892: aload #33
    //   894: getfield cellsUsed : I
    //   897: iload #13
    //   899: iconst_1
    //   900: iadd
    //   901: if_icmpne -> 916
    //   904: lload #25
    //   906: iconst_1
    //   907: iload_1
    //   908: ishl
    //   909: i2l
    //   910: lor
    //   911: lstore #27
    //   913: iload #12
    //   915: istore_2
    //   916: iload_1
    //   917: iconst_1
    //   918: iadd
    //   919: istore_1
    //   920: iload_2
    //   921: istore #12
    //   923: lload #27
    //   925: lstore #25
    //   927: goto -> 850
    //   930: iload #11
    //   932: ifeq -> 967
    //   935: aload #33
    //   937: getfield preventEdgeOffset : Z
    //   940: ifeq -> 967
    //   943: iload #12
    //   945: iconst_1
    //   946: if_icmpne -> 967
    //   949: aload #32
    //   951: aload_0
    //   952: getfield mGeneratedItemPadding : I
    //   955: iload #21
    //   957: iadd
    //   958: iconst_0
    //   959: aload_0
    //   960: getfield mGeneratedItemPadding : I
    //   963: iconst_0
    //   964: invokevirtual setPadding : (IIII)V
    //   967: aload #33
    //   969: aload #33
    //   971: getfield cellsUsed : I
    //   974: iconst_1
    //   975: iadd
    //   976: putfield cellsUsed : I
    //   979: aload #33
    //   981: iconst_1
    //   982: putfield expanded : Z
    //   985: iload #12
    //   987: iconst_1
    //   988: isub
    //   989: istore_2
    //   990: lload #25
    //   992: lstore #27
    //   994: goto -> 916
    //   997: iconst_1
    //   998: istore_2
    //   999: goto -> 454
    //   1002: iconst_0
    //   1003: istore_1
    //   1004: goto -> 652
    //   1007: iconst_0
    //   1008: istore #7
    //   1010: goto -> 812
    //   1013: aload_0
    //   1014: iload #8
    //   1016: invokevirtual getChildAt : (I)Landroid/view/View;
    //   1019: astore #32
    //   1021: aload #32
    //   1023: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1026: checkcast androidx/appcompat/widget/ActionMenuView$LayoutParams
    //   1029: astore #33
    //   1031: aload #32
    //   1033: instanceof androidx/appcompat/view/menu/ActionMenuItemView
    //   1036: ifeq -> 1080
    //   1039: aload #33
    //   1041: iload #7
    //   1043: putfield extraPixels : I
    //   1046: aload #33
    //   1048: iconst_1
    //   1049: putfield expanded : Z
    //   1052: iload #8
    //   1054: ifne -> 1075
    //   1057: aload #33
    //   1059: getfield preventEdgeOffset : Z
    //   1062: ifne -> 1075
    //   1065: aload #33
    //   1067: iload #7
    //   1069: ineg
    //   1070: iconst_2
    //   1071: idiv
    //   1072: putfield leftMargin : I
    //   1075: iconst_1
    //   1076: istore_1
    //   1077: goto -> 837
    //   1080: aload #33
    //   1082: getfield isOverflowButton : Z
    //   1085: ifeq -> 1116
    //   1088: aload #33
    //   1090: iload #7
    //   1092: putfield extraPixels : I
    //   1095: aload #33
    //   1097: iconst_1
    //   1098: putfield expanded : Z
    //   1101: aload #33
    //   1103: iload #7
    //   1105: ineg
    //   1106: iconst_2
    //   1107: idiv
    //   1108: putfield rightMargin : I
    //   1111: iconst_1
    //   1112: istore_1
    //   1113: goto -> 837
    //   1116: iload #8
    //   1118: ifeq -> 1130
    //   1121: aload #33
    //   1123: iload #7
    //   1125: iconst_2
    //   1126: idiv
    //   1127: putfield leftMargin : I
    //   1130: iload_2
    //   1131: istore_1
    //   1132: iload #8
    //   1134: iload #22
    //   1136: iconst_1
    //   1137: isub
    //   1138: if_icmpeq -> 837
    //   1141: aload #33
    //   1143: iload #7
    //   1145: iconst_2
    //   1146: idiv
    //   1147: putfield rightMargin : I
    //   1150: iload_2
    //   1151: istore_1
    //   1152: goto -> 837
    //   1155: iload_2
    //   1156: istore #7
    //   1158: iload #7
    //   1160: ifeq -> 1232
    //   1163: iconst_0
    //   1164: istore_1
    //   1165: iload_1
    //   1166: iload #22
    //   1168: if_icmpge -> 1232
    //   1171: aload_0
    //   1172: iload_1
    //   1173: invokevirtual getChildAt : (I)Landroid/view/View;
    //   1176: astore #32
    //   1178: aload #32
    //   1180: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1183: checkcast androidx/appcompat/widget/ActionMenuView$LayoutParams
    //   1186: astore #33
    //   1188: aload #33
    //   1190: getfield expanded : Z
    //   1193: ifne -> 1203
    //   1196: iload_1
    //   1197: iconst_1
    //   1198: iadd
    //   1199: istore_1
    //   1200: goto -> 1165
    //   1203: aload #32
    //   1205: aload #33
    //   1207: getfield cellsUsed : I
    //   1210: iload #21
    //   1212: imul
    //   1213: aload #33
    //   1215: getfield extraPixels : I
    //   1218: iadd
    //   1219: ldc 1073741824
    //   1221: invokestatic makeMeasureSpec : (II)I
    //   1224: iload #19
    //   1226: invokevirtual measure : (II)V
    //   1229: goto -> 1196
    //   1232: iload #17
    //   1234: istore_1
    //   1235: iload #18
    //   1237: ldc 1073741824
    //   1239: if_icmpeq -> 1245
    //   1242: iload #6
    //   1244: istore_1
    //   1245: aload_0
    //   1246: iload #20
    //   1248: iload_1
    //   1249: invokevirtual setMeasuredDimension : (II)V
    //   1252: return
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (paramLayoutParams != null && paramLayoutParams instanceof LayoutParams);
  }
  
  public void dismissPopupMenus() {
    if (this.mPresenter != null)
      this.mPresenter.dismissPopupMenus(); 
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    return false;
  }
  
  protected LayoutParams generateDefaultLayoutParams() {
    LayoutParams layoutParams = new LayoutParams(-2, -2);
    layoutParams.gravity = 16;
    return layoutParams;
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    if (paramLayoutParams != null) {
      LayoutParams layoutParams;
      if (paramLayoutParams instanceof LayoutParams) {
        layoutParams = new LayoutParams((LayoutParams)paramLayoutParams);
      } else {
        layoutParams = new LayoutParams((ViewGroup.LayoutParams)layoutParams);
      } 
      if (layoutParams.gravity <= 0)
        layoutParams.gravity = 16; 
      return layoutParams;
    } 
    return generateDefaultLayoutParams();
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public LayoutParams generateOverflowButtonLayoutParams() {
    LayoutParams layoutParams = generateDefaultLayoutParams();
    layoutParams.isOverflowButton = true;
    return layoutParams;
  }
  
  public Menu getMenu() {
    if (this.mMenu == null) {
      MenuPresenter.Callback callback;
      Context context = getContext();
      this.mMenu = new MenuBuilder(context);
      this.mMenu.setCallback(new MenuBuilderCallback());
      this.mPresenter = new ActionMenuPresenter(context);
      this.mPresenter.setReserveOverflow(true);
      ActionMenuPresenter actionMenuPresenter = this.mPresenter;
      if (this.mActionMenuPresenterCallback != null) {
        callback = this.mActionMenuPresenterCallback;
      } else {
        callback = new ActionMenuPresenterCallback();
      } 
      actionMenuPresenter.setCallback(callback);
      this.mMenu.addMenuPresenter((MenuPresenter)this.mPresenter, this.mPopupContext);
      this.mPresenter.setMenuView(this);
    } 
    return (Menu)this.mMenu;
  }
  
  @Nullable
  public Drawable getOverflowIcon() {
    getMenu();
    return this.mPresenter.getOverflowIcon();
  }
  
  public int getPopupTheme() {
    return this.mPopupTheme;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int getWindowAnimations() {
    return 0;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  protected boolean hasSupportDividerBeforeChildAt(int paramInt) {
    if (paramInt == 0)
      return false; 
    View view1 = getChildAt(paramInt - 1);
    View view2 = getChildAt(paramInt);
    int j = 0;
    int i = j;
    if (paramInt < getChildCount()) {
      i = j;
      if (view1 instanceof ActionMenuChildView)
        i = false | ((ActionMenuChildView)view1).needsDividerAfter(); 
    } 
    j = i;
    if (paramInt > 0) {
      j = i;
      if (view2 instanceof ActionMenuChildView)
        return i | ((ActionMenuChildView)view2).needsDividerBefore(); 
    } 
    return j;
  }
  
  public boolean hideOverflowMenu() {
    return (this.mPresenter != null && this.mPresenter.hideOverflowMenu());
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void initialize(MenuBuilder paramMenuBuilder) {
    this.mMenu = paramMenuBuilder;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public boolean invokeItem(MenuItemImpl paramMenuItemImpl) {
    return this.mMenu.performItemAction((MenuItem)paramMenuItemImpl, 0);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public boolean isOverflowMenuShowPending() {
    return (this.mPresenter != null && this.mPresenter.isOverflowMenuShowPending());
  }
  
  public boolean isOverflowMenuShowing() {
    return (this.mPresenter != null && this.mPresenter.isOverflowMenuShowing());
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public boolean isOverflowReserved() {
    return this.mReserveOverflow;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    if (this.mPresenter != null) {
      this.mPresenter.updateMenuView(false);
      if (this.mPresenter.isOverflowMenuShowing()) {
        this.mPresenter.hideOverflowMenu();
        this.mPresenter.showOverflowMenu();
      } 
    } 
  }
  
  public void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    dismissPopupMenus();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (!this.mFormatItems) {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    } 
    int n = getChildCount();
    int m = (paramInt4 - paramInt2) / 2;
    int i1 = getDividerWidth();
    int j = 0;
    paramInt4 = 0;
    paramInt2 = paramInt3 - paramInt1 - getPaddingRight() - getPaddingLeft();
    int k = 0;
    paramBoolean = ViewUtils.isLayoutRtl((View)this);
    int i;
    for (i = 0; i < n; i++) {
      View view = getChildAt(i);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.isOverflowButton) {
          int i3;
          int i2 = view.getMeasuredWidth();
          k = i2;
          if (hasSupportDividerBeforeChildAt(i))
            k = i2 + i1; 
          int i4 = view.getMeasuredHeight();
          if (paramBoolean) {
            i2 = getPaddingLeft() + layoutParams.leftMargin;
            i3 = i2 + k;
          } else {
            i3 = getWidth() - getPaddingRight() - layoutParams.rightMargin;
            i2 = i3 - k;
          } 
          int i5 = m - i4 / 2;
          view.layout(i2, i5, i3, i5 + i4);
          paramInt2 -= k;
          k = 1;
        } else {
          int i2 = view.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
          j += i2;
          i2 = paramInt2 - i2;
          paramInt2 = j;
          if (hasSupportDividerBeforeChildAt(i))
            paramInt2 = j + i1; 
          paramInt4++;
          j = paramInt2;
          paramInt2 = i2;
        } 
      } 
    } 
    if (n == 1 && k == 0) {
      View view = getChildAt(0);
      paramInt2 = view.getMeasuredWidth();
      paramInt4 = view.getMeasuredHeight();
      paramInt1 = (paramInt3 - paramInt1) / 2 - paramInt2 / 2;
      paramInt3 = m - paramInt4 / 2;
      view.layout(paramInt1, paramInt3, paramInt1 + paramInt2, paramInt3 + paramInt4);
      return;
    } 
    if (k != 0) {
      paramInt1 = 0;
    } else {
      paramInt1 = 1;
    } 
    paramInt1 = paramInt4 - paramInt1;
    if (paramInt1 > 0) {
      paramInt1 = paramInt2 / paramInt1;
    } else {
      paramInt1 = 0;
    } 
    paramInt4 = Math.max(0, paramInt1);
    if (paramBoolean) {
      paramInt2 = getWidth() - getPaddingRight();
      paramInt1 = 0;
      while (true) {
        if (paramInt1 < n) {
          View view = getChildAt(paramInt1);
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          paramInt3 = paramInt2;
          if (view.getVisibility() != 8)
            if (layoutParams.isOverflowButton) {
              paramInt3 = paramInt2;
            } else {
              paramInt2 -= layoutParams.rightMargin;
              paramInt3 = view.getMeasuredWidth();
              i = view.getMeasuredHeight();
              j = m - i / 2;
              view.layout(paramInt2 - paramInt3, j, paramInt2, j + i);
              paramInt3 = paramInt2 - layoutParams.leftMargin + paramInt3 + paramInt4;
            }  
          paramInt1++;
          paramInt2 = paramInt3;
          continue;
        } 
        return;
      } 
    } 
    paramInt2 = getPaddingLeft();
    paramInt1 = 0;
    while (true) {
      if (paramInt1 < n) {
        View view = getChildAt(paramInt1);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        paramInt3 = paramInt2;
        if (view.getVisibility() != 8)
          if (layoutParams.isOverflowButton) {
            paramInt3 = paramInt2;
          } else {
            paramInt2 += layoutParams.leftMargin;
            paramInt3 = view.getMeasuredWidth();
            i = view.getMeasuredHeight();
            j = m - i / 2;
            view.layout(paramInt2, j, paramInt2 + paramInt3, j + i);
            paramInt3 = paramInt2 + layoutParams.rightMargin + paramInt3 + paramInt4;
          }  
        paramInt1++;
        paramInt2 = paramInt3;
        continue;
      } 
      return;
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    boolean bool;
    boolean bool1 = this.mFormatItems;
    if (View.MeasureSpec.getMode(paramInt1) == 1073741824) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mFormatItems = bool;
    if (bool1 != this.mFormatItems)
      this.mFormatItemsWidth = 0; 
    int i = View.MeasureSpec.getSize(paramInt1);
    if (this.mFormatItems && this.mMenu != null && i != this.mFormatItemsWidth) {
      this.mFormatItemsWidth = i;
      this.mMenu.onItemsChanged(true);
    } 
    int j = getChildCount();
    if (this.mFormatItems && j > 0) {
      onMeasureExactFormat(paramInt1, paramInt2);
      return;
    } 
    for (i = 0; i < j; i++) {
      LayoutParams layoutParams = (LayoutParams)getChildAt(i).getLayoutParams();
      layoutParams.rightMargin = 0;
      layoutParams.leftMargin = 0;
    } 
    super.onMeasure(paramInt1, paramInt2);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public MenuBuilder peekMenu() {
    return this.mMenu;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setExpandedActionViewsExclusive(boolean paramBoolean) {
    this.mPresenter.setExpandedActionViewsExclusive(paramBoolean);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setMenuCallbacks(MenuPresenter.Callback paramCallback, MenuBuilder.Callback paramCallback1) {
    this.mActionMenuPresenterCallback = paramCallback;
    this.mMenuBuilderCallback = paramCallback1;
  }
  
  public void setOnMenuItemClickListener(OnMenuItemClickListener paramOnMenuItemClickListener) {
    this.mOnMenuItemClickListener = paramOnMenuItemClickListener;
  }
  
  public void setOverflowIcon(@Nullable Drawable paramDrawable) {
    getMenu();
    this.mPresenter.setOverflowIcon(paramDrawable);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setOverflowReserved(boolean paramBoolean) {
    this.mReserveOverflow = paramBoolean;
  }
  
  public void setPopupTheme(@StyleRes int paramInt) {
    if (this.mPopupTheme != paramInt) {
      this.mPopupTheme = paramInt;
      if (paramInt == 0) {
        this.mPopupContext = getContext();
        return;
      } 
    } else {
      return;
    } 
    this.mPopupContext = (Context)new ContextThemeWrapper(getContext(), paramInt);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setPresenter(ActionMenuPresenter paramActionMenuPresenter) {
    this.mPresenter = paramActionMenuPresenter;
    this.mPresenter.setMenuView(this);
  }
  
  public boolean showOverflowMenu() {
    return (this.mPresenter != null && this.mPresenter.showOverflowMenu());
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static interface ActionMenuChildView {
    boolean needsDividerAfter();
    
    boolean needsDividerBefore();
  }
  
  private static class ActionMenuPresenterCallback implements MenuPresenter.Callback {
    public void onCloseMenu(MenuBuilder param1MenuBuilder, boolean param1Boolean) {}
    
    public boolean onOpenSubMenu(MenuBuilder param1MenuBuilder) {
      return false;
    }
  }
  
  public static class LayoutParams extends LinearLayoutCompat.LayoutParams {
    @ExportedProperty
    public int cellsUsed;
    
    @ExportedProperty
    public boolean expandable;
    
    boolean expanded;
    
    @ExportedProperty
    public int extraPixels;
    
    @ExportedProperty
    public boolean isOverflowButton;
    
    @ExportedProperty
    public boolean preventEdgeOffset;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
      this.isOverflowButton = false;
    }
    
    LayoutParams(int param1Int1, int param1Int2, boolean param1Boolean) {
      super(param1Int1, param1Int2);
      this.isOverflowButton = param1Boolean;
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super((ViewGroup.LayoutParams)param1LayoutParams);
      this.isOverflowButton = param1LayoutParams.isOverflowButton;
    }
  }
  
  private class MenuBuilderCallback implements MenuBuilder.Callback {
    public boolean onMenuItemSelected(MenuBuilder param1MenuBuilder, MenuItem param1MenuItem) {
      return (ActionMenuView.this.mOnMenuItemClickListener != null && ActionMenuView.this.mOnMenuItemClickListener.onMenuItemClick(param1MenuItem));
    }
    
    public void onMenuModeChange(MenuBuilder param1MenuBuilder) {
      if (ActionMenuView.this.mMenuBuilderCallback != null)
        ActionMenuView.this.mMenuBuilderCallback.onMenuModeChange(param1MenuBuilder); 
    }
  }
  
  public static interface OnMenuItemClickListener {
    boolean onMenuItemClick(MenuItem param1MenuItem);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/ActionMenuView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
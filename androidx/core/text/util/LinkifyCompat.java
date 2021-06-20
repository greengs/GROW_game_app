package androidx.core.text.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.PatternsCompat;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LinkifyCompat {
  private static final Comparator<LinkSpec> COMPARATOR;
  
  private static final String[] EMPTY_STRING = new String[0];
  
  static {
    COMPARATOR = new Comparator<LinkSpec>() {
        public int compare(LinkifyCompat.LinkSpec param1LinkSpec1, LinkifyCompat.LinkSpec param1LinkSpec2) {
          if (param1LinkSpec1.start >= param1LinkSpec2.start) {
            if (param1LinkSpec1.start > param1LinkSpec2.start)
              return 1; 
            if (param1LinkSpec1.end < param1LinkSpec2.end)
              return 1; 
            if (param1LinkSpec1.end <= param1LinkSpec2.end)
              return 0; 
          } 
          return -1;
        }
      };
  }
  
  private static void addLinkMovementMethod(@NonNull TextView paramTextView) {
    MovementMethod movementMethod = paramTextView.getMovementMethod();
    if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && paramTextView.getLinksClickable())
      paramTextView.setMovementMethod(LinkMovementMethod.getInstance()); 
  }
  
  public static void addLinks(@NonNull TextView paramTextView, @NonNull Pattern paramPattern, @Nullable String paramString) {
    if (shouldAddLinksFallbackToFramework()) {
      Linkify.addLinks(paramTextView, paramPattern, paramString);
      return;
    } 
    addLinks(paramTextView, paramPattern, paramString, (String[])null, (Linkify.MatchFilter)null, (Linkify.TransformFilter)null);
  }
  
  public static void addLinks(@NonNull TextView paramTextView, @NonNull Pattern paramPattern, @Nullable String paramString, @Nullable Linkify.MatchFilter paramMatchFilter, @Nullable Linkify.TransformFilter paramTransformFilter) {
    if (shouldAddLinksFallbackToFramework()) {
      Linkify.addLinks(paramTextView, paramPattern, paramString, paramMatchFilter, paramTransformFilter);
      return;
    } 
    addLinks(paramTextView, paramPattern, paramString, (String[])null, paramMatchFilter, paramTransformFilter);
  }
  
  @SuppressLint({"NewApi"})
  public static void addLinks(@NonNull TextView paramTextView, @NonNull Pattern paramPattern, @Nullable String paramString, @Nullable String[] paramArrayOfString, @Nullable Linkify.MatchFilter paramMatchFilter, @Nullable Linkify.TransformFilter paramTransformFilter) {
    if (shouldAddLinksFallbackToFramework()) {
      Linkify.addLinks(paramTextView, paramPattern, paramString, paramArrayOfString, paramMatchFilter, paramTransformFilter);
      return;
    } 
    SpannableString spannableString = SpannableString.valueOf(paramTextView.getText());
    if (addLinks((Spannable)spannableString, paramPattern, paramString, paramArrayOfString, paramMatchFilter, paramTransformFilter)) {
      paramTextView.setText((CharSequence)spannableString);
      addLinkMovementMethod(paramTextView);
      return;
    } 
  }
  
  public static boolean addLinks(@NonNull Spannable paramSpannable, int paramInt) {
    if (shouldAddLinksFallbackToFramework())
      return Linkify.addLinks(paramSpannable, paramInt); 
    if (paramInt == 0)
      return false; 
    URLSpan[] arrayOfURLSpan = (URLSpan[])paramSpannable.getSpans(0, paramSpannable.length(), URLSpan.class);
    for (int i = arrayOfURLSpan.length - 1; i >= 0; i--)
      paramSpannable.removeSpan(arrayOfURLSpan[i]); 
    if ((paramInt & 0x4) != 0)
      Linkify.addLinks(paramSpannable, 4); 
    ArrayList<LinkSpec> arrayList = new ArrayList();
    if ((paramInt & 0x1) != 0) {
      Pattern pattern = PatternsCompat.AUTOLINK_WEB_URL;
      Linkify.MatchFilter matchFilter = Linkify.sUrlMatchFilter;
      gatherLinks(arrayList, paramSpannable, pattern, new String[] { "http://", "https://", "rtsp://" }, matchFilter, null);
    } 
    if ((paramInt & 0x2) != 0)
      gatherLinks(arrayList, paramSpannable, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[] { "mailto:" }, null, null); 
    if ((paramInt & 0x8) != 0)
      gatherMapLinks(arrayList, paramSpannable); 
    pruneOverlaps(arrayList, paramSpannable);
    if (arrayList.size() == 0)
      return false; 
    for (LinkSpec linkSpec : arrayList) {
      if (linkSpec.frameworkAddedSpan == null)
        applyLink(linkSpec.url, linkSpec.start, linkSpec.end, paramSpannable); 
    } 
    return true;
  }
  
  public static boolean addLinks(@NonNull Spannable paramSpannable, @NonNull Pattern paramPattern, @Nullable String paramString) {
    return shouldAddLinksFallbackToFramework() ? Linkify.addLinks(paramSpannable, paramPattern, paramString) : addLinks(paramSpannable, paramPattern, paramString, (String[])null, (Linkify.MatchFilter)null, (Linkify.TransformFilter)null);
  }
  
  public static boolean addLinks(@NonNull Spannable paramSpannable, @NonNull Pattern paramPattern, @Nullable String paramString, @Nullable Linkify.MatchFilter paramMatchFilter, @Nullable Linkify.TransformFilter paramTransformFilter) {
    return shouldAddLinksFallbackToFramework() ? Linkify.addLinks(paramSpannable, paramPattern, paramString, paramMatchFilter, paramTransformFilter) : addLinks(paramSpannable, paramPattern, paramString, (String[])null, paramMatchFilter, paramTransformFilter);
  }
  
  @SuppressLint({"NewApi"})
  public static boolean addLinks(@NonNull Spannable paramSpannable, @NonNull Pattern paramPattern, @Nullable String paramString, @Nullable String[] paramArrayOfString, @Nullable Linkify.MatchFilter paramMatchFilter, @Nullable Linkify.TransformFilter paramTransformFilter) {
    // Byte code:
    //   0: invokestatic shouldAddLinksFallbackToFramework : ()Z
    //   3: ifeq -> 22
    //   6: aload_0
    //   7: aload_1
    //   8: aload_2
    //   9: aload_3
    //   10: aload #4
    //   12: aload #5
    //   14: invokestatic addLinks : (Landroid/text/Spannable;Ljava/util/regex/Pattern;Ljava/lang/String;[Ljava/lang/String;Landroid/text/util/Linkify$MatchFilter;Landroid/text/util/Linkify$TransformFilter;)Z
    //   17: istore #9
    //   19: iload #9
    //   21: ireturn
    //   22: aload_2
    //   23: astore #10
    //   25: aload_2
    //   26: ifnonnull -> 33
    //   29: ldc ''
    //   31: astore #10
    //   33: aload_3
    //   34: ifnull -> 45
    //   37: aload_3
    //   38: astore_2
    //   39: aload_3
    //   40: arraylength
    //   41: iconst_1
    //   42: if_icmpge -> 49
    //   45: getstatic androidx/core/text/util/LinkifyCompat.EMPTY_STRING : [Ljava/lang/String;
    //   48: astore_2
    //   49: aload_2
    //   50: arraylength
    //   51: iconst_1
    //   52: iadd
    //   53: anewarray java/lang/String
    //   56: astore #11
    //   58: aload #11
    //   60: iconst_0
    //   61: aload #10
    //   63: getstatic java/util/Locale.ROOT : Ljava/util/Locale;
    //   66: invokevirtual toLowerCase : (Ljava/util/Locale;)Ljava/lang/String;
    //   69: aastore
    //   70: iconst_0
    //   71: istore #6
    //   73: iload #6
    //   75: aload_2
    //   76: arraylength
    //   77: if_icmpge -> 120
    //   80: aload_2
    //   81: iload #6
    //   83: aaload
    //   84: astore_3
    //   85: aload_3
    //   86: ifnonnull -> 109
    //   89: ldc ''
    //   91: astore_3
    //   92: aload #11
    //   94: iload #6
    //   96: iconst_1
    //   97: iadd
    //   98: aload_3
    //   99: aastore
    //   100: iload #6
    //   102: iconst_1
    //   103: iadd
    //   104: istore #6
    //   106: goto -> 73
    //   109: aload_3
    //   110: getstatic java/util/Locale.ROOT : Ljava/util/Locale;
    //   113: invokevirtual toLowerCase : (Ljava/util/Locale;)Ljava/lang/String;
    //   116: astore_3
    //   117: goto -> 92
    //   120: iconst_0
    //   121: istore #8
    //   123: aload_1
    //   124: aload_0
    //   125: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   128: astore_1
    //   129: iload #8
    //   131: istore #9
    //   133: aload_1
    //   134: invokevirtual find : ()Z
    //   137: ifeq -> 19
    //   140: aload_1
    //   141: invokevirtual start : ()I
    //   144: istore #6
    //   146: aload_1
    //   147: invokevirtual end : ()I
    //   150: istore #7
    //   152: iconst_1
    //   153: istore #9
    //   155: aload #4
    //   157: ifnull -> 174
    //   160: aload #4
    //   162: aload_0
    //   163: iload #6
    //   165: iload #7
    //   167: invokeinterface acceptMatch : (Ljava/lang/CharSequence;II)Z
    //   172: istore #9
    //   174: iload #9
    //   176: ifeq -> 129
    //   179: aload_1
    //   180: iconst_0
    //   181: invokevirtual group : (I)Ljava/lang/String;
    //   184: aload #11
    //   186: aload_1
    //   187: aload #5
    //   189: invokestatic makeUrl : (Ljava/lang/String;[Ljava/lang/String;Ljava/util/regex/Matcher;Landroid/text/util/Linkify$TransformFilter;)Ljava/lang/String;
    //   192: iload #6
    //   194: iload #7
    //   196: aload_0
    //   197: invokestatic applyLink : (Ljava/lang/String;IILandroid/text/Spannable;)V
    //   200: iconst_1
    //   201: istore #8
    //   203: goto -> 129
  }
  
  public static boolean addLinks(@NonNull TextView paramTextView, int paramInt) {
    boolean bool2 = false;
    if (shouldAddLinksFallbackToFramework())
      return Linkify.addLinks(paramTextView, paramInt); 
    boolean bool1 = bool2;
    if (paramInt != 0) {
      CharSequence charSequence = paramTextView.getText();
      if (charSequence instanceof Spannable) {
        bool1 = bool2;
        if (addLinks((Spannable)charSequence, paramInt)) {
          addLinkMovementMethod(paramTextView);
          return true;
        } 
        return bool1;
      } 
      SpannableString spannableString = SpannableString.valueOf(charSequence);
      bool1 = bool2;
      if (addLinks((Spannable)spannableString, paramInt)) {
        addLinkMovementMethod(paramTextView);
        paramTextView.setText((CharSequence)spannableString);
        return true;
      } 
    } 
    return bool1;
  }
  
  private static void applyLink(String paramString, int paramInt1, int paramInt2, Spannable paramSpannable) {
    paramSpannable.setSpan(new URLSpan(paramString), paramInt1, paramInt2, 33);
  }
  
  private static String findAddress(String paramString) {
    return (Build.VERSION.SDK_INT >= 28) ? WebView.findAddress(paramString) : FindAddress.findAddress(paramString);
  }
  
  private static void gatherLinks(ArrayList<LinkSpec> paramArrayList, Spannable paramSpannable, Pattern paramPattern, String[] paramArrayOfString, Linkify.MatchFilter paramMatchFilter, Linkify.TransformFilter paramTransformFilter) {
    Matcher matcher = paramPattern.matcher((CharSequence)paramSpannable);
    while (matcher.find()) {
      int i = matcher.start();
      int j = matcher.end();
      if (paramMatchFilter == null || paramMatchFilter.acceptMatch((CharSequence)paramSpannable, i, j)) {
        LinkSpec linkSpec = new LinkSpec();
        linkSpec.url = makeUrl(matcher.group(0), paramArrayOfString, matcher, paramTransformFilter);
        linkSpec.start = i;
        linkSpec.end = j;
        paramArrayList.add(linkSpec);
      } 
    } 
  }
  
  private static void gatherMapLinks(ArrayList<LinkSpec> paramArrayList, Spannable paramSpannable) {
    String str = paramSpannable.toString();
    int i = 0;
    while (true) {
      try {
        String str1 = findAddress(str);
        if (str1 != null) {
          int j = str.indexOf(str1);
          if (j < 0)
            return; 
          LinkSpec linkSpec = new LinkSpec();
          int k = j + str1.length();
          linkSpec.start = i + j;
          linkSpec.end = i + k;
          str = str.substring(k);
          i += k;
          try {
            str1 = URLEncoder.encode(str1, "UTF-8");
            linkSpec.url = "geo:0,0?q=" + str1;
            paramArrayList.add(linkSpec);
          } catch (UnsupportedEncodingException unsupportedEncodingException) {}
          continue;
        } 
      } catch (UnsupportedOperationException unsupportedOperationException) {
        return;
      } 
      break;
    } 
  }
  
  private static String makeUrl(@NonNull String paramString, @NonNull String[] paramArrayOfString, Matcher paramMatcher, @Nullable Linkify.TransformFilter paramTransformFilter) {
    String str = paramString;
    if (paramTransformFilter != null)
      str = paramTransformFilter.transformUrl(paramMatcher, paramString); 
    boolean bool = false;
    int i = 0;
    while (true) {
      boolean bool1 = bool;
      paramString = str;
      if (i < paramArrayOfString.length)
        if (str.regionMatches(true, 0, paramArrayOfString[i], 0, paramArrayOfString[i].length())) {
          bool = true;
          bool1 = bool;
          paramString = str;
          if (!str.regionMatches(false, 0, paramArrayOfString[i], 0, paramArrayOfString[i].length())) {
            paramString = paramArrayOfString[i] + str.substring(paramArrayOfString[i].length());
            bool1 = bool;
          } 
        } else {
          i++;
          continue;
        }  
      String str1 = paramString;
      if (!bool1) {
        str1 = paramString;
        if (paramArrayOfString.length > 0)
          str1 = paramArrayOfString[0] + paramString; 
      } 
      return str1;
    } 
  }
  
  private static void pruneOverlaps(ArrayList<LinkSpec> paramArrayList, Spannable paramSpannable) {
    URLSpan[] arrayOfURLSpan = (URLSpan[])paramSpannable.getSpans(0, paramSpannable.length(), URLSpan.class);
    int i;
    for (i = 0; i < arrayOfURLSpan.length; i++) {
      LinkSpec linkSpec = new LinkSpec();
      linkSpec.frameworkAddedSpan = arrayOfURLSpan[i];
      linkSpec.start = paramSpannable.getSpanStart(arrayOfURLSpan[i]);
      linkSpec.end = paramSpannable.getSpanEnd(arrayOfURLSpan[i]);
      paramArrayList.add(linkSpec);
    } 
    Collections.sort(paramArrayList, COMPARATOR);
    int k = paramArrayList.size();
    for (int j = 0; j < k - 1; j++) {
      LinkSpec linkSpec1 = paramArrayList.get(j);
      LinkSpec linkSpec2 = paramArrayList.get(j + 1);
      i = -1;
      if (linkSpec1.start <= linkSpec2.start && linkSpec1.end > linkSpec2.start) {
        if (linkSpec2.end <= linkSpec1.end) {
          i = j + 1;
        } else if (linkSpec1.end - linkSpec1.start > linkSpec2.end - linkSpec2.start) {
          i = j + 1;
        } else if (linkSpec1.end - linkSpec1.start < linkSpec2.end - linkSpec2.start) {
          i = j;
        } 
        if (i != -1) {
          URLSpan uRLSpan = ((LinkSpec)paramArrayList.get(i)).frameworkAddedSpan;
          if (uRLSpan != null)
            paramSpannable.removeSpan(uRLSpan); 
          paramArrayList.remove(i);
          k--;
          continue;
        } 
      } 
    } 
  }
  
  private static boolean shouldAddLinksFallbackToFramework() {
    return (Build.VERSION.SDK_INT >= 28);
  }
  
  private static class LinkSpec {
    int end;
    
    URLSpan frameworkAddedSpan;
    
    int start;
    
    String url;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface LinkifyMask {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/text/util/LinkifyCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
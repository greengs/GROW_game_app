package androidx.core.text;

import android.os.Build;
import android.text.Layout;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.core.os.TraceCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class PrecomputedTextCompat implements Spannable {
  private static final char LINE_FEED = '\n';
  
  @GuardedBy("sLock")
  @NonNull
  private static Executor sExecutor;
  
  private static final Object sLock = new Object();
  
  @NonNull
  private final int[] mParagraphEnds;
  
  @NonNull
  private final Params mParams;
  
  @NonNull
  private final Spannable mText;
  
  @Nullable
  private final PrecomputedText mWrapped;
  
  static {
    sExecutor = null;
  }
  
  @RequiresApi(28)
  private PrecomputedTextCompat(@NonNull PrecomputedText paramPrecomputedText, @NonNull Params paramParams) {
    this.mText = (Spannable)paramPrecomputedText;
    this.mParams = paramParams;
    this.mParagraphEnds = null;
    this.mWrapped = paramPrecomputedText;
  }
  
  private PrecomputedTextCompat(@NonNull CharSequence paramCharSequence, @NonNull Params paramParams, @NonNull int[] paramArrayOfint) {
    this.mText = (Spannable)new SpannableString(paramCharSequence);
    this.mParams = paramParams;
    this.mParagraphEnds = paramArrayOfint;
    this.mWrapped = null;
  }
  
  public static PrecomputedTextCompat create(@NonNull CharSequence paramCharSequence, @NonNull Params paramParams) {
    Preconditions.checkNotNull(paramCharSequence);
    Preconditions.checkNotNull(paramParams);
    try {
      PrecomputedTextCompat precomputedTextCompat;
      TraceCompat.beginSection("PrecomputedText");
      if (Build.VERSION.SDK_INT >= 28 && paramParams.mWrapped != null) {
        precomputedTextCompat = new PrecomputedTextCompat(PrecomputedText.create(paramCharSequence, paramParams.mWrapped), paramParams);
        return precomputedTextCompat;
      } 
      ArrayList<Integer> arrayList = new ArrayList();
      int j = precomputedTextCompat.length();
      int i = 0;
      while (true) {
        if (i < j) {
          i = TextUtils.indexOf((CharSequence)precomputedTextCompat, '\n', i, j);
          if (i < 0) {
            i = j;
          } else {
            i++;
          } 
          arrayList.add(Integer.valueOf(i));
          continue;
        } 
        int[] arrayOfInt = new int[arrayList.size()];
        for (i = 0; i < arrayList.size(); i++)
          arrayOfInt[i] = ((Integer)arrayList.get(i)).intValue(); 
        if (Build.VERSION.SDK_INT >= 23) {
          StaticLayout.Builder.obtain((CharSequence)precomputedTextCompat, 0, precomputedTextCompat.length(), paramParams.getTextPaint(), 2147483647).setBreakStrategy(paramParams.getBreakStrategy()).setHyphenationFrequency(paramParams.getHyphenationFrequency()).setTextDirection(paramParams.getTextDirection()).build();
          precomputedTextCompat = new PrecomputedTextCompat((CharSequence)precomputedTextCompat, paramParams, arrayOfInt);
          return precomputedTextCompat;
        } 
        if (Build.VERSION.SDK_INT >= 21)
          new StaticLayout((CharSequence)precomputedTextCompat, paramParams.getTextPaint(), 2147483647, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false); 
        precomputedTextCompat = new PrecomputedTextCompat((CharSequence)precomputedTextCompat, paramParams, arrayOfInt);
        return precomputedTextCompat;
      } 
    } finally {
      TraceCompat.endSection();
    } 
  }
  
  private int findParaIndex(@IntRange(from = 0L) int paramInt) {
    for (int i = 0; i < this.mParagraphEnds.length; i++) {
      if (paramInt < this.mParagraphEnds[i])
        return i; 
    } 
    throw new IndexOutOfBoundsException("pos must be less than " + this.mParagraphEnds[this.mParagraphEnds.length - 1] + ", gave " + paramInt);
  }
  
  @UiThread
  public static Future<PrecomputedTextCompat> getTextFuture(@NonNull CharSequence paramCharSequence, @NonNull Params paramParams, @Nullable Executor paramExecutor) {
    PrecomputedTextFutureTask precomputedTextFutureTask = new PrecomputedTextFutureTask(paramParams, paramCharSequence);
    Executor executor = paramExecutor;
    if (paramExecutor == null)
      synchronized (sLock) {
        if (sExecutor == null)
          sExecutor = Executors.newFixedThreadPool(1); 
        executor = sExecutor;
        executor.execute(precomputedTextFutureTask);
        return precomputedTextFutureTask;
      }  
    executor.execute(precomputedTextFutureTask);
    return precomputedTextFutureTask;
  }
  
  public char charAt(int paramInt) {
    return this.mText.charAt(paramInt);
  }
  
  @IntRange(from = 0L)
  public int getParagraphCount() {
    return (Build.VERSION.SDK_INT >= 28) ? this.mWrapped.getParagraphCount() : this.mParagraphEnds.length;
  }
  
  @IntRange(from = 0L)
  public int getParagraphEnd(@IntRange(from = 0L) int paramInt) {
    Preconditions.checkArgumentInRange(paramInt, 0, getParagraphCount(), "paraIndex");
    return (Build.VERSION.SDK_INT >= 28) ? this.mWrapped.getParagraphEnd(paramInt) : this.mParagraphEnds[paramInt];
  }
  
  @IntRange(from = 0L)
  public int getParagraphStart(@IntRange(from = 0L) int paramInt) {
    int i = 0;
    Preconditions.checkArgumentInRange(paramInt, 0, getParagraphCount(), "paraIndex");
    return (Build.VERSION.SDK_INT >= 28) ? this.mWrapped.getParagraphStart(paramInt) : ((paramInt != 0) ? this.mParagraphEnds[paramInt - 1] : i);
  }
  
  @NonNull
  public Params getParams() {
    return this.mParams;
  }
  
  @Nullable
  @RequiresApi(28)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public PrecomputedText getPrecomputedText() {
    return (this.mText instanceof PrecomputedText) ? (PrecomputedText)this.mText : null;
  }
  
  public int getSpanEnd(Object paramObject) {
    return this.mText.getSpanEnd(paramObject);
  }
  
  public int getSpanFlags(Object paramObject) {
    return this.mText.getSpanFlags(paramObject);
  }
  
  public int getSpanStart(Object paramObject) {
    return this.mText.getSpanStart(paramObject);
  }
  
  public <T> T[] getSpans(int paramInt1, int paramInt2, Class<T> paramClass) {
    return (T[])((Build.VERSION.SDK_INT >= 28) ? this.mWrapped.getSpans(paramInt1, paramInt2, paramClass) : this.mText.getSpans(paramInt1, paramInt2, paramClass));
  }
  
  public int length() {
    return this.mText.length();
  }
  
  public int nextSpanTransition(int paramInt1, int paramInt2, Class paramClass) {
    return this.mText.nextSpanTransition(paramInt1, paramInt2, paramClass);
  }
  
  public void removeSpan(Object paramObject) {
    if (paramObject instanceof android.text.style.MetricAffectingSpan)
      throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText."); 
    if (Build.VERSION.SDK_INT >= 28) {
      this.mWrapped.removeSpan(paramObject);
      return;
    } 
    this.mText.removeSpan(paramObject);
  }
  
  public void setSpan(Object paramObject, int paramInt1, int paramInt2, int paramInt3) {
    if (paramObject instanceof android.text.style.MetricAffectingSpan)
      throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText."); 
    if (Build.VERSION.SDK_INT >= 28) {
      this.mWrapped.setSpan(paramObject, paramInt1, paramInt2, paramInt3);
      return;
    } 
    this.mText.setSpan(paramObject, paramInt1, paramInt2, paramInt3);
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2) {
    return this.mText.subSequence(paramInt1, paramInt2);
  }
  
  public String toString() {
    return this.mText.toString();
  }
  
  public static final class Params {
    private final int mBreakStrategy;
    
    private final int mHyphenationFrequency;
    
    @NonNull
    private final TextPaint mPaint;
    
    @Nullable
    private final TextDirectionHeuristic mTextDir;
    
    final PrecomputedText.Params mWrapped;
    
    @RequiresApi(28)
    public Params(@NonNull PrecomputedText.Params param1Params) {
      this.mPaint = param1Params.getTextPaint();
      this.mTextDir = param1Params.getTextDirection();
      this.mBreakStrategy = param1Params.getBreakStrategy();
      this.mHyphenationFrequency = param1Params.getHyphenationFrequency();
      this.mWrapped = param1Params;
    }
    
    Params(@NonNull TextPaint param1TextPaint, @NonNull TextDirectionHeuristic param1TextDirectionHeuristic, int param1Int1, int param1Int2) {
      if (Build.VERSION.SDK_INT >= 28) {
        this.mWrapped = (new PrecomputedText.Params.Builder(param1TextPaint)).setBreakStrategy(param1Int1).setHyphenationFrequency(param1Int2).setTextDirection(param1TextDirectionHeuristic).build();
      } else {
        this.mWrapped = null;
      } 
      this.mPaint = param1TextPaint;
      this.mTextDir = param1TextDirectionHeuristic;
      this.mBreakStrategy = param1Int1;
      this.mHyphenationFrequency = param1Int2;
    }
    
    public boolean equals(@Nullable Object param1Object) {
      if (param1Object != this) {
        if (param1Object == null || !(param1Object instanceof Params))
          return false; 
        param1Object = param1Object;
        if (this.mWrapped != null)
          return this.mWrapped.equals(((Params)param1Object).mWrapped); 
        if (Build.VERSION.SDK_INT >= 23) {
          if (this.mBreakStrategy != param1Object.getBreakStrategy())
            return false; 
          if (this.mHyphenationFrequency != param1Object.getHyphenationFrequency())
            return false; 
        } 
        if (Build.VERSION.SDK_INT >= 18 && this.mTextDir != param1Object.getTextDirection())
          return false; 
        if (this.mPaint.getTextSize() != param1Object.getTextPaint().getTextSize())
          return false; 
        if (this.mPaint.getTextScaleX() != param1Object.getTextPaint().getTextScaleX())
          return false; 
        if (this.mPaint.getTextSkewX() != param1Object.getTextPaint().getTextSkewX())
          return false; 
        if (Build.VERSION.SDK_INT >= 21) {
          if (this.mPaint.getLetterSpacing() != param1Object.getTextPaint().getLetterSpacing())
            return false; 
          if (!TextUtils.equals(this.mPaint.getFontFeatureSettings(), param1Object.getTextPaint().getFontFeatureSettings()))
            return false; 
        } 
        if (this.mPaint.getFlags() != param1Object.getTextPaint().getFlags())
          return false; 
        if (Build.VERSION.SDK_INT >= 24) {
          if (!this.mPaint.getTextLocales().equals(param1Object.getTextPaint().getTextLocales()))
            return false; 
        } else if (Build.VERSION.SDK_INT >= 17 && !this.mPaint.getTextLocale().equals(param1Object.getTextPaint().getTextLocale())) {
          return false;
        } 
        if (this.mPaint.getTypeface() == null)
          return !(param1Object.getTextPaint().getTypeface() != null); 
        if (!this.mPaint.getTypeface().equals(param1Object.getTextPaint().getTypeface()))
          return false; 
      } 
      return true;
    }
    
    @RequiresApi(23)
    public int getBreakStrategy() {
      return this.mBreakStrategy;
    }
    
    @RequiresApi(23)
    public int getHyphenationFrequency() {
      return this.mHyphenationFrequency;
    }
    
    @Nullable
    @RequiresApi(18)
    public TextDirectionHeuristic getTextDirection() {
      return this.mTextDir;
    }
    
    @NonNull
    public TextPaint getTextPaint() {
      return this.mPaint;
    }
    
    public int hashCode() {
      return (Build.VERSION.SDK_INT >= 24) ? ObjectsCompat.hash(new Object[] { 
            Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocales(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), 
            Integer.valueOf(this.mHyphenationFrequency) }) : ((Build.VERSION.SDK_INT >= 21) ? ObjectsCompat.hash(new Object[] { 
            Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), 
            Integer.valueOf(this.mHyphenationFrequency) }) : ((Build.VERSION.SDK_INT >= 18) ? ObjectsCompat.hash(new Object[] { Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency) }) : ((Build.VERSION.SDK_INT >= 17) ? ObjectsCompat.hash(new Object[] { Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency) }) : ObjectsCompat.hash(new Object[] { Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTypeface(), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency) }))));
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder("{");
      stringBuilder.append("textSize=" + this.mPaint.getTextSize());
      stringBuilder.append(", textScaleX=" + this.mPaint.getTextScaleX());
      stringBuilder.append(", textSkewX=" + this.mPaint.getTextSkewX());
      if (Build.VERSION.SDK_INT >= 21) {
        stringBuilder.append(", letterSpacing=" + this.mPaint.getLetterSpacing());
        stringBuilder.append(", elegantTextHeight=" + this.mPaint.isElegantTextHeight());
      } 
      if (Build.VERSION.SDK_INT >= 24) {
        stringBuilder.append(", textLocale=" + this.mPaint.getTextLocales());
      } else if (Build.VERSION.SDK_INT >= 17) {
        stringBuilder.append(", textLocale=" + this.mPaint.getTextLocale());
      } 
      stringBuilder.append(", typeface=" + this.mPaint.getTypeface());
      if (Build.VERSION.SDK_INT >= 26)
        stringBuilder.append(", variationSettings=" + this.mPaint.getFontVariationSettings()); 
      stringBuilder.append(", textDir=" + this.mTextDir);
      stringBuilder.append(", breakStrategy=" + this.mBreakStrategy);
      stringBuilder.append(", hyphenationFrequency=" + this.mHyphenationFrequency);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public static class Builder {
      private int mBreakStrategy;
      
      private int mHyphenationFrequency;
      
      @NonNull
      private final TextPaint mPaint;
      
      private TextDirectionHeuristic mTextDir;
      
      public Builder(@NonNull TextPaint param2TextPaint) {
        this.mPaint = param2TextPaint;
        if (Build.VERSION.SDK_INT >= 23) {
          this.mBreakStrategy = 1;
          this.mHyphenationFrequency = 1;
        } else {
          this.mHyphenationFrequency = 0;
          this.mBreakStrategy = 0;
        } 
        if (Build.VERSION.SDK_INT >= 18) {
          this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
          return;
        } 
        this.mTextDir = null;
      }
      
      @NonNull
      public PrecomputedTextCompat.Params build() {
        return new PrecomputedTextCompat.Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
      }
      
      @RequiresApi(23)
      public Builder setBreakStrategy(int param2Int) {
        this.mBreakStrategy = param2Int;
        return this;
      }
      
      @RequiresApi(23)
      public Builder setHyphenationFrequency(int param2Int) {
        this.mHyphenationFrequency = param2Int;
        return this;
      }
      
      @RequiresApi(18)
      public Builder setTextDirection(@NonNull TextDirectionHeuristic param2TextDirectionHeuristic) {
        this.mTextDir = param2TextDirectionHeuristic;
        return this;
      }
    }
  }
  
  public static class Builder {
    private int mBreakStrategy;
    
    private int mHyphenationFrequency;
    
    @NonNull
    private final TextPaint mPaint;
    
    private TextDirectionHeuristic mTextDir;
    
    public Builder(@NonNull TextPaint param1TextPaint) {
      this.mPaint = param1TextPaint;
      if (Build.VERSION.SDK_INT >= 23) {
        this.mBreakStrategy = 1;
        this.mHyphenationFrequency = 1;
      } else {
        this.mHyphenationFrequency = 0;
        this.mBreakStrategy = 0;
      } 
      if (Build.VERSION.SDK_INT >= 18) {
        this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        return;
      } 
      this.mTextDir = null;
    }
    
    @NonNull
    public PrecomputedTextCompat.Params build() {
      return new PrecomputedTextCompat.Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
    }
    
    @RequiresApi(23)
    public Builder setBreakStrategy(int param1Int) {
      this.mBreakStrategy = param1Int;
      return this;
    }
    
    @RequiresApi(23)
    public Builder setHyphenationFrequency(int param1Int) {
      this.mHyphenationFrequency = param1Int;
      return this;
    }
    
    @RequiresApi(18)
    public Builder setTextDirection(@NonNull TextDirectionHeuristic param1TextDirectionHeuristic) {
      this.mTextDir = param1TextDirectionHeuristic;
      return this;
    }
  }
  
  private static class PrecomputedTextFutureTask extends FutureTask<PrecomputedTextCompat> {
    PrecomputedTextFutureTask(@NonNull PrecomputedTextCompat.Params param1Params, @NonNull CharSequence param1CharSequence) {
      super(new PrecomputedTextCallback(param1Params, param1CharSequence));
    }
    
    private static class PrecomputedTextCallback implements Callable<PrecomputedTextCompat> {
      private PrecomputedTextCompat.Params mParams;
      
      private CharSequence mText;
      
      PrecomputedTextCallback(@NonNull PrecomputedTextCompat.Params param2Params, @NonNull CharSequence param2CharSequence) {
        this.mParams = param2Params;
        this.mText = param2CharSequence;
      }
      
      public PrecomputedTextCompat call() throws Exception {
        return PrecomputedTextCompat.create(this.mText, this.mParams);
      }
    }
  }
  
  private static class PrecomputedTextCallback implements Callable<PrecomputedTextCompat> {
    private PrecomputedTextCompat.Params mParams;
    
    private CharSequence mText;
    
    PrecomputedTextCallback(@NonNull PrecomputedTextCompat.Params param1Params, @NonNull CharSequence param1CharSequence) {
      this.mParams = param1Params;
      this.mText = param1CharSequence;
    }
    
    public PrecomputedTextCompat call() throws Exception {
      return PrecomputedTextCompat.create(this.mText, this.mParams);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/text/PrecomputedTextCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
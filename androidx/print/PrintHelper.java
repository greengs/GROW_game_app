package androidx.print;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class PrintHelper {
  @SuppressLint({"InlinedApi"})
  public static final int COLOR_MODE_COLOR = 2;
  
  @SuppressLint({"InlinedApi"})
  public static final int COLOR_MODE_MONOCHROME = 1;
  
  static final boolean IS_MIN_MARGINS_HANDLING_CORRECT;
  
  private static final String LOG_TAG = "PrintHelper";
  
  private static final int MAX_PRINT_SIZE = 3500;
  
  public static final int ORIENTATION_LANDSCAPE = 1;
  
  public static final int ORIENTATION_PORTRAIT = 2;
  
  static final boolean PRINT_ACTIVITY_RESPECTS_ORIENTATION;
  
  public static final int SCALE_MODE_FILL = 2;
  
  public static final int SCALE_MODE_FIT = 1;
  
  int mColorMode = 2;
  
  final Context mContext;
  
  BitmapFactory.Options mDecodeOptions = null;
  
  final Object mLock = new Object();
  
  int mOrientation = 1;
  
  int mScaleMode = 2;
  
  static {
    boolean bool1;
    boolean bool2 = true;
    if (Build.VERSION.SDK_INT < 20 || Build.VERSION.SDK_INT > 23) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    PRINT_ACTIVITY_RESPECTS_ORIENTATION = bool1;
    if (Build.VERSION.SDK_INT != 23) {
      bool1 = bool2;
    } else {
      bool1 = false;
    } 
    IS_MIN_MARGINS_HANDLING_CORRECT = bool1;
  }
  
  public PrintHelper(@NonNull Context paramContext) {
    this.mContext = paramContext;
  }
  
  static Bitmap convertBitmapForColorMode(Bitmap paramBitmap, int paramInt) {
    if (paramInt != 1)
      return paramBitmap; 
    Bitmap bitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    Paint paint = new Paint();
    ColorMatrix colorMatrix = new ColorMatrix();
    colorMatrix.setSaturation(0.0F);
    paint.setColorFilter((ColorFilter)new ColorMatrixColorFilter(colorMatrix));
    canvas.drawBitmap(paramBitmap, 0.0F, 0.0F, paint);
    canvas.setBitmap(null);
    return bitmap;
  }
  
  @RequiresApi(19)
  private static PrintAttributes.Builder copyAttributes(PrintAttributes paramPrintAttributes) {
    PrintAttributes.Builder builder = (new PrintAttributes.Builder()).setMediaSize(paramPrintAttributes.getMediaSize()).setResolution(paramPrintAttributes.getResolution()).setMinMargins(paramPrintAttributes.getMinMargins());
    if (paramPrintAttributes.getColorMode() != 0)
      builder.setColorMode(paramPrintAttributes.getColorMode()); 
    if (Build.VERSION.SDK_INT >= 23 && paramPrintAttributes.getDuplexMode() != 0)
      builder.setDuplexMode(paramPrintAttributes.getDuplexMode()); 
    return builder;
  }
  
  static Matrix getMatrix(int paramInt1, int paramInt2, RectF paramRectF, int paramInt3) {
    Matrix matrix = new Matrix();
    float f = paramRectF.width() / paramInt1;
    if (paramInt3 == 2) {
      f = Math.max(f, paramRectF.height() / paramInt2);
      matrix.postScale(f, f);
      matrix.postTranslate((paramRectF.width() - paramInt1 * f) / 2.0F, (paramRectF.height() - paramInt2 * f) / 2.0F);
      return matrix;
    } 
    f = Math.min(f, paramRectF.height() / paramInt2);
    matrix.postScale(f, f);
    matrix.postTranslate((paramRectF.width() - paramInt1 * f) / 2.0F, (paramRectF.height() - paramInt2 * f) / 2.0F);
    return matrix;
  }
  
  static boolean isPortrait(Bitmap paramBitmap) {
    return (paramBitmap.getWidth() <= paramBitmap.getHeight());
  }
  
  private Bitmap loadBitmap(Uri paramUri, BitmapFactory.Options paramOptions) throws FileNotFoundException {
    if (paramUri == null || this.mContext == null)
      throw new IllegalArgumentException("bad argument to loadBitmap"); 
    InputStream inputStream = null;
    try {
      InputStream inputStream1 = this.mContext.getContentResolver().openInputStream(paramUri);
      inputStream = inputStream1;
      Bitmap bitmap = BitmapFactory.decodeStream(inputStream1, null, paramOptions);
      return bitmap;
    } finally {
      if (inputStream != null)
        try {
          inputStream.close();
        } catch (IOException iOException) {
          Log.w("PrintHelper", "close fail ", iOException);
        }  
    } 
  }
  
  public static boolean systemSupportsPrint() {
    return (Build.VERSION.SDK_INT >= 19);
  }
  
  public int getColorMode() {
    return this.mColorMode;
  }
  
  public int getOrientation() {
    return (Build.VERSION.SDK_INT >= 19 && this.mOrientation == 0) ? 1 : this.mOrientation;
  }
  
  public int getScaleMode() {
    return this.mScaleMode;
  }
  
  Bitmap loadConstrainedBitmap(Uri paramUri) throws FileNotFoundException {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 11
    //   4: aload_0
    //   5: getfield mContext : Landroid/content/Context;
    //   8: ifnonnull -> 22
    //   11: new java/lang/IllegalArgumentException
    //   14: dup
    //   15: ldc_w 'bad argument to getScaledBitmap'
    //   18: invokespecial <init> : (Ljava/lang/String;)V
    //   21: athrow
    //   22: new android/graphics/BitmapFactory$Options
    //   25: dup
    //   26: invokespecial <init> : ()V
    //   29: astore #6
    //   31: aload #6
    //   33: iconst_1
    //   34: putfield inJustDecodeBounds : Z
    //   37: aload_0
    //   38: aload_1
    //   39: aload #6
    //   41: invokespecial loadBitmap : (Landroid/net/Uri;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   44: pop
    //   45: aload #6
    //   47: getfield outWidth : I
    //   50: istore #4
    //   52: aload #6
    //   54: getfield outHeight : I
    //   57: istore #5
    //   59: iload #4
    //   61: ifle -> 69
    //   64: iload #5
    //   66: ifgt -> 71
    //   69: aconst_null
    //   70: areturn
    //   71: iload #4
    //   73: iload #5
    //   75: invokestatic max : (II)I
    //   78: istore_3
    //   79: iconst_1
    //   80: istore_2
    //   81: iload_3
    //   82: sipush #3500
    //   85: if_icmple -> 99
    //   88: iload_3
    //   89: iconst_1
    //   90: iushr
    //   91: istore_3
    //   92: iload_2
    //   93: iconst_1
    //   94: ishl
    //   95: istore_2
    //   96: goto -> 81
    //   99: iload_2
    //   100: ifle -> 69
    //   103: iload #4
    //   105: iload #5
    //   107: invokestatic min : (II)I
    //   110: iload_2
    //   111: idiv
    //   112: ifle -> 69
    //   115: aload_0
    //   116: getfield mLock : Ljava/lang/Object;
    //   119: astore #6
    //   121: aload #6
    //   123: monitorenter
    //   124: aload_0
    //   125: new android/graphics/BitmapFactory$Options
    //   128: dup
    //   129: invokespecial <init> : ()V
    //   132: putfield mDecodeOptions : Landroid/graphics/BitmapFactory$Options;
    //   135: aload_0
    //   136: getfield mDecodeOptions : Landroid/graphics/BitmapFactory$Options;
    //   139: iconst_1
    //   140: putfield inMutable : Z
    //   143: aload_0
    //   144: getfield mDecodeOptions : Landroid/graphics/BitmapFactory$Options;
    //   147: iload_2
    //   148: putfield inSampleSize : I
    //   151: aload_0
    //   152: getfield mDecodeOptions : Landroid/graphics/BitmapFactory$Options;
    //   155: astore #7
    //   157: aload #6
    //   159: monitorexit
    //   160: aload_0
    //   161: aload_1
    //   162: aload #7
    //   164: invokespecial loadBitmap : (Landroid/net/Uri;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   167: astore #6
    //   169: aload_0
    //   170: getfield mLock : Ljava/lang/Object;
    //   173: astore_1
    //   174: aload_1
    //   175: monitorenter
    //   176: aload_0
    //   177: aconst_null
    //   178: putfield mDecodeOptions : Landroid/graphics/BitmapFactory$Options;
    //   181: aload_1
    //   182: monitorexit
    //   183: aload #6
    //   185: areturn
    //   186: astore #6
    //   188: aload_1
    //   189: monitorexit
    //   190: aload #6
    //   192: athrow
    //   193: astore_1
    //   194: aload #6
    //   196: monitorexit
    //   197: aload_1
    //   198: athrow
    //   199: astore #6
    //   201: aload_0
    //   202: getfield mLock : Ljava/lang/Object;
    //   205: astore_1
    //   206: aload_1
    //   207: monitorenter
    //   208: aload_0
    //   209: aconst_null
    //   210: putfield mDecodeOptions : Landroid/graphics/BitmapFactory$Options;
    //   213: aload_1
    //   214: monitorexit
    //   215: aload #6
    //   217: athrow
    //   218: astore #6
    //   220: aload_1
    //   221: monitorexit
    //   222: aload #6
    //   224: athrow
    // Exception table:
    //   from	to	target	type
    //   124	160	193	finally
    //   160	169	199	finally
    //   176	183	186	finally
    //   188	190	186	finally
    //   194	197	193	finally
    //   208	215	218	finally
    //   220	222	218	finally
  }
  
  public void printBitmap(@NonNull String paramString, @NonNull Bitmap paramBitmap) {
    printBitmap(paramString, paramBitmap, (OnPrintFinishCallback)null);
  }
  
  public void printBitmap(@NonNull String paramString, @NonNull Bitmap paramBitmap, @Nullable OnPrintFinishCallback paramOnPrintFinishCallback) {
    PrintAttributes.MediaSize mediaSize;
    if (Build.VERSION.SDK_INT < 19 || paramBitmap == null)
      return; 
    PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
    if (isPortrait(paramBitmap)) {
      mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
    } else {
      mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
    } 
    PrintAttributes printAttributes = (new PrintAttributes.Builder()).setMediaSize(mediaSize).setColorMode(this.mColorMode).build();
    printManager.print(paramString, new PrintBitmapAdapter(paramString, this.mScaleMode, paramBitmap, paramOnPrintFinishCallback), printAttributes);
  }
  
  public void printBitmap(@NonNull String paramString, @NonNull Uri paramUri) throws FileNotFoundException {
    printBitmap(paramString, paramUri, (OnPrintFinishCallback)null);
  }
  
  public void printBitmap(@NonNull String paramString, @NonNull Uri paramUri, @Nullable OnPrintFinishCallback paramOnPrintFinishCallback) throws FileNotFoundException {
    if (Build.VERSION.SDK_INT < 19)
      return; 
    PrintUriAdapter printUriAdapter = new PrintUriAdapter(paramString, paramUri, paramOnPrintFinishCallback, this.mScaleMode);
    PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
    PrintAttributes.Builder builder = new PrintAttributes.Builder();
    builder.setColorMode(this.mColorMode);
    if (this.mOrientation == 1 || this.mOrientation == 0) {
      builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
    } else if (this.mOrientation == 2) {
      builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
    } 
    printManager.print(paramString, printUriAdapter, builder.build());
  }
  
  public void setColorMode(int paramInt) {
    this.mColorMode = paramInt;
  }
  
  public void setOrientation(int paramInt) {
    this.mOrientation = paramInt;
  }
  
  public void setScaleMode(int paramInt) {
    this.mScaleMode = paramInt;
  }
  
  @RequiresApi(19)
  void writeBitmap(final PrintAttributes attributes, final int fittingMode, final Bitmap bitmap, final ParcelFileDescriptor fileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
    final PrintAttributes pdfAttributes;
    if (IS_MIN_MARGINS_HANDLING_CORRECT) {
      printAttributes = attributes;
    } else {
      printAttributes = copyAttributes(attributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
    } 
    (new AsyncTask<Void, Void, Throwable>() {
        protected Throwable doInBackground(Void... param1VarArgs) {
          try {
            if (cancellationSignal.isCanceled())
              return null; 
            PrintedPdfDocument printedPdfDocument = new PrintedPdfDocument(PrintHelper.this.mContext, pdfAttributes);
            Bitmap bitmap = PrintHelper.convertBitmapForColorMode(bitmap, pdfAttributes.getColorMode());
            boolean bool = cancellationSignal.isCanceled();
            if (!bool)
              try {
                RectF rectF;
                PdfDocument.Page page = printedPdfDocument.startPage(1);
                if (PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT) {
                  rectF = new RectF(page.getInfo().getContentRect());
                } else {
                  PrintedPdfDocument printedPdfDocument1 = new PrintedPdfDocument(PrintHelper.this.mContext, attributes);
                  PdfDocument.Page page1 = printedPdfDocument1.startPage(1);
                  rectF = new RectF(page1.getInfo().getContentRect());
                  printedPdfDocument1.finishPage(page1);
                  printedPdfDocument1.close();
                } 
                Matrix matrix = PrintHelper.getMatrix(bitmap.getWidth(), bitmap.getHeight(), rectF, fittingMode);
                if (!PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT) {
                  matrix.postTranslate(rectF.left, rectF.top);
                  page.getCanvas().clipRect(rectF);
                } 
                page.getCanvas().drawBitmap(bitmap, matrix, null);
              } finally {
                printedPdfDocument.close();
                ParcelFileDescriptor parcelFileDescriptor = fileDescriptor;
                if (parcelFileDescriptor != null)
                  try {
                    fileDescriptor.close();
                  } catch (IOException iOException) {} 
                if (bitmap != bitmap)
                  bitmap.recycle(); 
              }  
          } catch (Throwable null) {
            return null;
          } 
          return null;
        }
        
        protected void onPostExecute(Throwable param1Throwable) {
          if (cancellationSignal.isCanceled()) {
            writeResultCallback.onWriteCancelled();
            return;
          } 
          if (param1Throwable == null) {
            writeResultCallback.onWriteFinished(new PageRange[] { PageRange.ALL_PAGES });
            return;
          } 
          Log.e("PrintHelper", "Error writing printed content", param1Throwable);
          writeResultCallback.onWriteFailed(null);
        }
      }).execute((Object[])new Void[0]);
  }
  
  public static interface OnPrintFinishCallback {
    void onFinish();
  }
  
  @RequiresApi(19)
  private class PrintBitmapAdapter extends PrintDocumentAdapter {
    private PrintAttributes mAttributes;
    
    private final Bitmap mBitmap;
    
    private final PrintHelper.OnPrintFinishCallback mCallback;
    
    private final int mFittingMode;
    
    private final String mJobName;
    
    PrintBitmapAdapter(String param1String, int param1Int, Bitmap param1Bitmap, PrintHelper.OnPrintFinishCallback param1OnPrintFinishCallback) {
      this.mJobName = param1String;
      this.mFittingMode = param1Int;
      this.mBitmap = param1Bitmap;
      this.mCallback = param1OnPrintFinishCallback;
    }
    
    public void onFinish() {
      if (this.mCallback != null)
        this.mCallback.onFinish(); 
    }
    
    public void onLayout(PrintAttributes param1PrintAttributes1, PrintAttributes param1PrintAttributes2, CancellationSignal param1CancellationSignal, PrintDocumentAdapter.LayoutResultCallback param1LayoutResultCallback, Bundle param1Bundle) {
      boolean bool = true;
      this.mAttributes = param1PrintAttributes2;
      PrintDocumentInfo printDocumentInfo = (new PrintDocumentInfo.Builder(this.mJobName)).setContentType(1).setPageCount(1).build();
      if (param1PrintAttributes2.equals(param1PrintAttributes1))
        bool = false; 
      param1LayoutResultCallback.onLayoutFinished(printDocumentInfo, bool);
    }
    
    public void onWrite(PageRange[] param1ArrayOfPageRange, ParcelFileDescriptor param1ParcelFileDescriptor, CancellationSignal param1CancellationSignal, PrintDocumentAdapter.WriteResultCallback param1WriteResultCallback) {
      PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, param1ParcelFileDescriptor, param1CancellationSignal, param1WriteResultCallback);
    }
  }
  
  @RequiresApi(19)
  private class PrintUriAdapter extends PrintDocumentAdapter {
    PrintAttributes mAttributes;
    
    Bitmap mBitmap;
    
    final PrintHelper.OnPrintFinishCallback mCallback;
    
    final int mFittingMode;
    
    final Uri mImageFile;
    
    final String mJobName;
    
    AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
    
    PrintUriAdapter(String param1String, Uri param1Uri, PrintHelper.OnPrintFinishCallback param1OnPrintFinishCallback, int param1Int) {
      this.mJobName = param1String;
      this.mImageFile = param1Uri;
      this.mCallback = param1OnPrintFinishCallback;
      this.mFittingMode = param1Int;
      this.mBitmap = null;
    }
    
    void cancelLoad() {
      synchronized (PrintHelper.this.mLock) {
        if (PrintHelper.this.mDecodeOptions != null) {
          if (Build.VERSION.SDK_INT < 24)
            PrintHelper.this.mDecodeOptions.requestCancelDecode(); 
          PrintHelper.this.mDecodeOptions = null;
        } 
        return;
      } 
    }
    
    public void onFinish() {
      super.onFinish();
      cancelLoad();
      if (this.mLoadBitmap != null)
        this.mLoadBitmap.cancel(true); 
      if (this.mCallback != null)
        this.mCallback.onFinish(); 
      if (this.mBitmap != null) {
        this.mBitmap.recycle();
        this.mBitmap = null;
      } 
    }
    
    public void onLayout(PrintAttributes param1PrintAttributes1, PrintAttributes param1PrintAttributes2, CancellationSignal param1CancellationSignal, PrintDocumentAdapter.LayoutResultCallback param1LayoutResultCallback, Bundle param1Bundle) {
      // Byte code:
      //   0: iconst_1
      //   1: istore #6
      //   3: aload_0
      //   4: monitorenter
      //   5: aload_0
      //   6: aload_2
      //   7: putfield mAttributes : Landroid/print/PrintAttributes;
      //   10: aload_0
      //   11: monitorexit
      //   12: aload_3
      //   13: invokevirtual isCanceled : ()Z
      //   16: ifeq -> 30
      //   19: aload #4
      //   21: invokevirtual onLayoutCancelled : ()V
      //   24: return
      //   25: astore_1
      //   26: aload_0
      //   27: monitorexit
      //   28: aload_1
      //   29: athrow
      //   30: aload_0
      //   31: getfield mBitmap : Landroid/graphics/Bitmap;
      //   34: ifnull -> 83
      //   37: new android/print/PrintDocumentInfo$Builder
      //   40: dup
      //   41: aload_0
      //   42: getfield mJobName : Ljava/lang/String;
      //   45: invokespecial <init> : (Ljava/lang/String;)V
      //   48: iconst_1
      //   49: invokevirtual setContentType : (I)Landroid/print/PrintDocumentInfo$Builder;
      //   52: iconst_1
      //   53: invokevirtual setPageCount : (I)Landroid/print/PrintDocumentInfo$Builder;
      //   56: invokevirtual build : ()Landroid/print/PrintDocumentInfo;
      //   59: astore_3
      //   60: aload_2
      //   61: aload_1
      //   62: invokevirtual equals : (Ljava/lang/Object;)Z
      //   65: ifne -> 77
      //   68: aload #4
      //   70: aload_3
      //   71: iload #6
      //   73: invokevirtual onLayoutFinished : (Landroid/print/PrintDocumentInfo;Z)V
      //   76: return
      //   77: iconst_0
      //   78: istore #6
      //   80: goto -> 68
      //   83: aload_0
      //   84: new androidx/print/PrintHelper$PrintUriAdapter$1
      //   87: dup
      //   88: aload_0
      //   89: aload_3
      //   90: aload_2
      //   91: aload_1
      //   92: aload #4
      //   94: invokespecial <init> : (Landroidx/print/PrintHelper$PrintUriAdapter;Landroid/os/CancellationSignal;Landroid/print/PrintAttributes;Landroid/print/PrintAttributes;Landroid/print/PrintDocumentAdapter$LayoutResultCallback;)V
      //   97: iconst_0
      //   98: anewarray android/net/Uri
      //   101: invokevirtual execute : ([Ljava/lang/Object;)Landroid/os/AsyncTask;
      //   104: putfield mLoadBitmap : Landroid/os/AsyncTask;
      //   107: return
      // Exception table:
      //   from	to	target	type
      //   5	12	25	finally
      //   26	28	25	finally
    }
    
    public void onWrite(PageRange[] param1ArrayOfPageRange, ParcelFileDescriptor param1ParcelFileDescriptor, CancellationSignal param1CancellationSignal, PrintDocumentAdapter.WriteResultCallback param1WriteResultCallback) {
      PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, param1ParcelFileDescriptor, param1CancellationSignal, param1WriteResultCallback);
    }
  }
  
  class null extends AsyncTask<Uri, Boolean, Bitmap> {
    protected Bitmap doInBackground(Uri... param1VarArgs) {
      try {
        return PrintHelper.this.loadConstrainedBitmap(this.this$1.mImageFile);
      } catch (FileNotFoundException fileNotFoundException) {
        return null;
      } 
    }
    
    protected void onCancelled(Bitmap param1Bitmap) {
      layoutResultCallback.onLayoutCancelled();
      this.this$1.mLoadBitmap = null;
    }
    
    protected void onPostExecute(Bitmap param1Bitmap) {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: invokespecial onPostExecute : (Ljava/lang/Object;)V
      //   5: aload_1
      //   6: astore_3
      //   7: aload_1
      //   8: ifnull -> 101
      //   11: getstatic androidx/print/PrintHelper.PRINT_ACTIVITY_RESPECTS_ORIENTATION : Z
      //   14: ifeq -> 32
      //   17: aload_1
      //   18: astore_3
      //   19: aload_0
      //   20: getfield this$1 : Landroidx/print/PrintHelper$PrintUriAdapter;
      //   23: getfield this$0 : Landroidx/print/PrintHelper;
      //   26: getfield mOrientation : I
      //   29: ifne -> 101
      //   32: aload_0
      //   33: monitorenter
      //   34: aload_0
      //   35: getfield this$1 : Landroidx/print/PrintHelper$PrintUriAdapter;
      //   38: getfield mAttributes : Landroid/print/PrintAttributes;
      //   41: invokevirtual getMediaSize : ()Landroid/print/PrintAttributes$MediaSize;
      //   44: astore #4
      //   46: aload_0
      //   47: monitorexit
      //   48: aload_1
      //   49: astore_3
      //   50: aload #4
      //   52: ifnull -> 101
      //   55: aload_1
      //   56: astore_3
      //   57: aload #4
      //   59: invokevirtual isPortrait : ()Z
      //   62: aload_1
      //   63: invokestatic isPortrait : (Landroid/graphics/Bitmap;)Z
      //   66: if_icmpeq -> 101
      //   69: new android/graphics/Matrix
      //   72: dup
      //   73: invokespecial <init> : ()V
      //   76: astore_3
      //   77: aload_3
      //   78: ldc 90.0
      //   80: invokevirtual postRotate : (F)Z
      //   83: pop
      //   84: aload_1
      //   85: iconst_0
      //   86: iconst_0
      //   87: aload_1
      //   88: invokevirtual getWidth : ()I
      //   91: aload_1
      //   92: invokevirtual getHeight : ()I
      //   95: aload_3
      //   96: iconst_1
      //   97: invokestatic createBitmap : (Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;
      //   100: astore_3
      //   101: aload_0
      //   102: getfield this$1 : Landroidx/print/PrintHelper$PrintUriAdapter;
      //   105: aload_3
      //   106: putfield mBitmap : Landroid/graphics/Bitmap;
      //   109: aload_3
      //   110: ifnull -> 183
      //   113: new android/print/PrintDocumentInfo$Builder
      //   116: dup
      //   117: aload_0
      //   118: getfield this$1 : Landroidx/print/PrintHelper$PrintUriAdapter;
      //   121: getfield mJobName : Ljava/lang/String;
      //   124: invokespecial <init> : (Ljava/lang/String;)V
      //   127: iconst_1
      //   128: invokevirtual setContentType : (I)Landroid/print/PrintDocumentInfo$Builder;
      //   131: iconst_1
      //   132: invokevirtual setPageCount : (I)Landroid/print/PrintDocumentInfo$Builder;
      //   135: invokevirtual build : ()Landroid/print/PrintDocumentInfo;
      //   138: astore_1
      //   139: aload_0
      //   140: getfield val$newPrintAttributes : Landroid/print/PrintAttributes;
      //   143: aload_0
      //   144: getfield val$oldPrintAttributes : Landroid/print/PrintAttributes;
      //   147: invokevirtual equals : (Ljava/lang/Object;)Z
      //   150: ifne -> 178
      //   153: iconst_1
      //   154: istore_2
      //   155: aload_0
      //   156: getfield val$layoutResultCallback : Landroid/print/PrintDocumentAdapter$LayoutResultCallback;
      //   159: aload_1
      //   160: iload_2
      //   161: invokevirtual onLayoutFinished : (Landroid/print/PrintDocumentInfo;Z)V
      //   164: aload_0
      //   165: getfield this$1 : Landroidx/print/PrintHelper$PrintUriAdapter;
      //   168: aconst_null
      //   169: putfield mLoadBitmap : Landroid/os/AsyncTask;
      //   172: return
      //   173: astore_1
      //   174: aload_0
      //   175: monitorexit
      //   176: aload_1
      //   177: athrow
      //   178: iconst_0
      //   179: istore_2
      //   180: goto -> 155
      //   183: aload_0
      //   184: getfield val$layoutResultCallback : Landroid/print/PrintDocumentAdapter$LayoutResultCallback;
      //   187: aconst_null
      //   188: invokevirtual onLayoutFailed : (Ljava/lang/CharSequence;)V
      //   191: goto -> 164
      // Exception table:
      //   from	to	target	type
      //   34	48	173	finally
      //   174	176	173	finally
    }
    
    protected void onPreExecute() {
      cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            public void onCancel() {
              this.this$2.this$1.cancelLoad();
              PrintHelper.PrintUriAdapter.null.this.cancel(false);
            }
          });
    }
  }
  
  class null implements CancellationSignal.OnCancelListener {
    public void onCancel() {
      this.this$2.this$1.cancelLoad();
      this.this$2.cancel(false);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/print/PrintHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
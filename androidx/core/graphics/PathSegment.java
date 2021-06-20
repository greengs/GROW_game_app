package androidx.core.graphics;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

public final class PathSegment {
  private final PointF mEnd;
  
  private final float mEndFraction;
  
  private final PointF mStart;
  
  private final float mStartFraction;
  
  public PathSegment(@NonNull PointF paramPointF1, float paramFloat1, @NonNull PointF paramPointF2, float paramFloat2) {
    this.mStart = (PointF)Preconditions.checkNotNull(paramPointF1, "start == null");
    this.mStartFraction = paramFloat1;
    this.mEnd = (PointF)Preconditions.checkNotNull(paramPointF2, "end == null");
    this.mEndFraction = paramFloat2;
  }
  
  public boolean equals(Object paramObject) {
    if (this != paramObject) {
      if (!(paramObject instanceof PathSegment))
        return false; 
      paramObject = paramObject;
      if (Float.compare(this.mStartFraction, ((PathSegment)paramObject).mStartFraction) != 0 || Float.compare(this.mEndFraction, ((PathSegment)paramObject).mEndFraction) != 0 || !this.mStart.equals(((PathSegment)paramObject).mStart) || !this.mEnd.equals(((PathSegment)paramObject).mEnd))
        return false; 
    } 
    return true;
  }
  
  @NonNull
  public PointF getEnd() {
    return this.mEnd;
  }
  
  public float getEndFraction() {
    return this.mEndFraction;
  }
  
  @NonNull
  public PointF getStart() {
    return this.mStart;
  }
  
  public float getStartFraction() {
    return this.mStartFraction;
  }
  
  public int hashCode() {
    byte b;
    int i = 0;
    int j = this.mStart.hashCode();
    if (this.mStartFraction != 0.0F) {
      b = Float.floatToIntBits(this.mStartFraction);
    } else {
      b = 0;
    } 
    int k = this.mEnd.hashCode();
    if (this.mEndFraction != 0.0F)
      i = Float.floatToIntBits(this.mEndFraction); 
    return ((j * 31 + b) * 31 + k) * 31 + i;
  }
  
  public String toString() {
    return "PathSegment{start=" + this.mStart + ", startFraction=" + this.mStartFraction + ", end=" + this.mEnd + ", endFraction=" + this.mEndFraction + '}';
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/PathSegment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
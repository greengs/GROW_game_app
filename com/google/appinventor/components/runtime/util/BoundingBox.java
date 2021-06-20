package com.google.appinventor.components.runtime.util;

public final class BoundingBox {
  private double bottom;
  
  private double left;
  
  private double right;
  
  private double top;
  
  public BoundingBox(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
    this.left = paramDouble1;
    this.top = paramDouble2;
    this.right = paramDouble3;
    this.bottom = paramDouble4;
  }
  
  public double getBottom() {
    return this.bottom;
  }
  
  public double getLeft() {
    return this.left;
  }
  
  public double getRight() {
    return this.right;
  }
  
  public double getTop() {
    return this.top;
  }
  
  public boolean intersectDestructively(BoundingBox paramBoundingBox) {
    double d1 = Math.max(this.left, paramBoundingBox.left);
    double d2 = Math.min(this.right, paramBoundingBox.right);
    double d3 = Math.max(this.top, paramBoundingBox.top);
    double d4 = Math.min(this.bottom, paramBoundingBox.bottom);
    if (d1 > d2 || d3 > d4)
      return false; 
    this.left = d1;
    this.right = d2;
    this.top = d3;
    this.bottom = d4;
    return true;
  }
  
  public String toString() {
    return "<BoundingBox (left = " + this.left + ", top = " + this.top + ", right = " + this.right + ", bottom = " + this.bottom + ">";
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/BoundingBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package telasi.android.reading.model;

import java.util.Date;

public class Reading {
  private double reading;
  private boolean readingConfirmed;
  private double previousReading;
  private Date previousReadingDate;
  private double previousRealReading;
  private Date previousRealReadingDate;

  public double getReading() {
    return reading;
  }

  public void setReading(double reading) {
    this.reading = reading;
  }

  public boolean isReadingConfirmed() {
    return readingConfirmed;
  }

  public void setReadingConfirmed(boolean readingConfirmed) {
    this.readingConfirmed = readingConfirmed;
  }

  public double getPreviousReading() {
    return previousReading;
  }

  public void setPreviousReading(double previousReading) {
    this.previousReading = previousReading;
  }

  public Date getPreviousReadingDate() {
    return previousReadingDate;
  }

  public void setPreviousReadingDate(Date previousReadingDate) {
    this.previousReadingDate = previousReadingDate;
  }

  public double getPreviousRealReading() {
    return previousRealReading;
  }

  public void setPreviousRealReading(double previousRealReading) {
    this.previousRealReading = previousRealReading;
  }

  public Date getPreviousRealReadingDate() {
    return previousRealReadingDate;
  }

  public void setPreviousRealReadingDate(Date previousRealReadingDate) {
    this.previousRealReadingDate = previousRealReadingDate;
  }

}

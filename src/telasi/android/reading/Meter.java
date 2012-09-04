package telasi.android.reading;

public class Meter {
  private boolean active;
  private String number;
  private String sealNumber;
  private boolean sealActive;
  private int digits;
  private int coeff;

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getSealNumber() {
    return sealNumber;
  }

  public void setSealNumber(String sealNumber) {
    this.sealNumber = sealNumber;
  }

  public boolean isSealActive() {
    return sealActive;
  }

  public void setSealActive(boolean sealActive) {
    this.sealActive = sealActive;
  }

  public int getDigits() {
    return digits;
  }

  public void setDigits(int digits) {
    this.digits = digits;
  }

  public int getCoeff() {
    return coeff;
  }

  public void setCoeff(int coeff) {
    this.coeff = coeff;
  }

}

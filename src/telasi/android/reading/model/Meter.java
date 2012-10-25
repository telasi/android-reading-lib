package telasi.android.reading.model;

public class Meter {
  private boolean active;
  private String number;
  private String sealNumber;
  private boolean sealActive;
  private int digits;
  private int coeff;
  private String type;
  private boolean without;
  private String newNumber;
  private String newSealNumber;
  private Integer newCoeff;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isWithout() {
    return without;
  }

  public void setWithout(boolean without) {
    this.without = without;
  }

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

  public String getNewNumber() {
    return newNumber;
  }

  public void setNewNumber(String newNumber) {
    this.newNumber = newNumber;
  }

  public String getNewSealNumber() {
    return newSealNumber;
  }

  public void setNewSealNumber(String newSealNumber) {
    this.newSealNumber = newSealNumber;
  }

  public Integer getNewCoeff() {
    return newCoeff;
  }

  public void setNewCoeff(Integer newCoeff) {
    this.newCoeff = newCoeff;
  }

}

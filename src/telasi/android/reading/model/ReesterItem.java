package telasi.android.reading.model;

public class ReesterItem {
  private int id;
  private int sequence;
  private Account account;
  private Meter meter;
  private Reading reading;
  private String note;

  public static double calculateCharge(double r1, double r2, double coeff, int digits) {
    double diff = Math.round(r2 * coeff) - Math.round(r1 * coeff);
    if (diff < 0)
      diff += Math.round(Math.pow(10, digits) * coeff);
    return diff;
  }
  
  public boolean isReadingEntered() {
    return this.getReading().getReading() > 0.0099;
  }

  public boolean isAboveMax() {
    return this.isReadingEntered() && this.getCharge() > this.getAccount().getMaxCharge();
  }

  public boolean isBelowMin() {
    return this.isReadingEntered() && this.getCharge() < this.getAccount().getMinCharge();
  }

  public boolean isSuspicious() {
    return !isReadingEntered() || isAboveMax() || isBelowMin();
  }

  public double getCharge() {
    if (isReadingEntered()) {
      double r1 = getReading().getPreviousReading();
      double r2 = getReading().getReading();
      double coeff = getMeter().getCoeff();
      int digits = getMeter().getDigits();
      return ReesterItem.calculateCharge(r1, r2, coeff, digits);
    }
    return 0;
  }

  public Reading getReading() {
    return reading;
  }

  public void setReading(Reading reading) {
    this.reading = reading;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getSequence() {
    return sequence;
  }

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Meter getMeter() {
    return meter;
  }

  public void setMeter(Meter meter) {
    this.meter = meter;
  }

  public String getNote(){
    return note;
  }

  public void setNote(String note){
    this.note=note;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ReesterItem))
      return false;
    ReesterItem item = (ReesterItem) obj;
    return item.id == this.id;
  }

}

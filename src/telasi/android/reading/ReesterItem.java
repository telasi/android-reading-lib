package telasi.android.reading;

public class ReesterItem {
  private int id;
  private int route;
  private int schedule;
  private int sequence;
  private Account account;
  private Meter meter;
  private Reading reading;

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

  public int getRoute() {
    return route;
  }

  public void setRoute(int routeKey) {
    this.route = routeKey;
  }

  public int getSchedule() {
    return schedule;
  }

  public void setSchedule(int scheduleKey) {
    this.schedule = scheduleKey;
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

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ReesterItem))
      return false;
    ReesterItem item = (ReesterItem) obj;
    return item.id == this.id;
  }

}

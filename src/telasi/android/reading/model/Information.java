package telasi.android.reading.model;

public class Information {
  private String message;

  public Information() {
    this(null);
  }

  public Information(String message) {
    setMessage(message);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

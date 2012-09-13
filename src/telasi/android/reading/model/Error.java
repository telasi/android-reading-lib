package telasi.android.reading.model;

public class Error {
  private String message;

  public Error() {
    this(null);
  }

  public Error(String message) {
    setMessage(message);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

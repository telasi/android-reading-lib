package telasi.android.reading;

public class Account {
  public static int STATUS_ACTIVE = 0;
  public static int STATUS_INACTIVE = 1;
  public static int STATUS_CLOSED = 2;

  private int acckey;
  private int custkey;
  private int status;
  private boolean cut;
  private String accountNumber;
  private String accountID;
  private String customerName;
  private double installedCapacity;
  private double minCharge;
  private double maxCharge;

  public double getInstalledCapacity() {
    return installedCapacity;
  }

  public void setInstalledCapacity(double installedCapacity) {
    this.installedCapacity = installedCapacity;
  }

  public double getMinCharge() {
    return minCharge;
  }

  public void setMinCharge(double minCharge) {
    this.minCharge = minCharge;
  }

  public double getMaxCharge() {
    return maxCharge;
  }

  public void setMaxCharge(double maxCharge) {
    this.maxCharge = maxCharge;
  }

  public int getAcckey() {
    return acckey;
  }

  public void setAcckey(int acckey) {
    this.acckey = acckey;
  }

  public int getCustkey() {
    return custkey;
  }

  public void setCustkey(int custkey) {
    this.custkey = custkey;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public boolean isCut() {
    return cut;
  }

  public void setCut(boolean cut) {
    this.cut = cut;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountID() {
    return accountID;
  }

  public void setAccountID(String accountID) {
    this.accountID = accountID;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

}

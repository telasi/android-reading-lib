package telasi.android.reading.model;

public class Address {
  private int streetId;
  private String streetName;
  private String house;
  private String building;
  private String porch;
  private String flate;
  private String fullAddress;

  public int getStreetId() {
    return streetId;
  }

  public void setStreetId(int streeId) {
    this.streetId = streeId;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getHouse() {
    return house;
  }

  public void setHouse(String house) {
    this.house = house;
  }

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public String getPorch() {
    return porch;
  }

  public void setPorch(String porch) {
    this.porch = porch;
  }

  public String getFlate() {
    return flate;
  }

  public void setFlate(String flate) {
    this.flate = flate;
  }

  public String getFullAddress() {
    return fullAddress;
  }

  public void setFullAddress(String fullAddress) {
    this.fullAddress = fullAddress;
  }
}

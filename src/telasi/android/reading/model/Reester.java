package telasi.android.reading.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Reester {
  public static final int STATUS_INITIAL = 0;
  public static final int STATUS_DOWNLOADED = 1;
  public static final int STATUS_SENT = 2;
  public static final int STATUS_SYNCED = 3;

  private int id;
  private Date cycleDate;
  private int inspectorId;
  private String inspectorName;
  private int schedule;
  private int blockId;
  private String blockName;
  private int regionId;
  private String regionName;
  private int downloads;
  private int uploads;
  private int status;
  private int route;
  private String routeName;
  private int count;
  private ReesterItem[] items_ary = {};
  private List<ReesterItem> items = new ArrayList<ReesterItem>();

  public HashMap<Integer, String> getStreets() {
    HashMap<Integer, String> streets = new HashMap<Integer, String>();
    for (ReesterItem item : getItems()) {
      Address adrs = item.getAccount().getAddress();
      if (!streets.containsKey(adrs.getStreetId()))
        streets.put(adrs.getStreetId(), adrs.getStreetName());
    }
    return streets;
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

  public void setRoute(int route) {
    this.route = route;
  }

  public String getRouteName() {
    return routeName;
  }

  public void setRouteName(String routeName) {
    this.routeName = routeName;
  }

  public Date getCycleDate() {
    return cycleDate;
  }

  public void setCycleDate(Date cycleDate) {
    this.cycleDate = cycleDate;
  }

  public int getInspectorId() {
    return inspectorId;
  }

  public void setInspectorId(int inspectorId) {
    this.inspectorId = inspectorId;
  }

  public String getInspectorName() {
    return inspectorName;
  }

  public void setInspectorName(String inspectorName) {
    this.inspectorName = inspectorName;
  }

  public int getDownloads() {
    return downloads;
  }

  public void setDownloads(int downloads) {
    this.downloads = downloads;
  }

  public int getUploads() {
    return uploads;
  }

  public void setUploads(int uploads) {
    this.uploads = uploads;
  }

  public int getStatus() {
    return status;
  }

  public int getSchedule() {
    return schedule;
  }

  public void setSchedule(int schedule) {
    this.schedule = schedule;
  }

  public int getBlockId() {
    return blockId;
  }

  public void setBlockId(int blockId) {
    this.blockId = blockId;
  }

  public int getRegionId() {
    return regionId;
  }

  public void setRegionId(int regionId) {
    this.regionId = regionId;
  }

  public String getBlockName() {
    return blockName;
  }

  public void setBlockName(String blockName) {
    this.blockName = blockName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  public String getRegionName() {
    return regionName;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public ReesterItem[] getItems() {
    return items_ary;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public boolean addItem(ReesterItem item) {
    boolean resp = !items.contains(item) && items.add(item);
    if (resp)
      initAry();
    return resp;
  }

  public boolean removeItem(ReesterItem item) {
    boolean resp = items.remove(item);
    if (resp)
      initAry();
    return resp;
  }

  private void initAry() {
    this.items_ary = this.items.toArray(new ReesterItem[] {});
  }

}

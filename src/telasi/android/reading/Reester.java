package telasi.android.reading;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reester {
	private int id;
	private Date cycleDate;
	private int inspectorId;
	private int downloads;
	private int uploads;
	private int status;
	private List<ReesterItem> items = new ArrayList<ReesterItem>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setStatus(int status) {
		this.status = status;
	}

	public ReesterItem[] getItems() {
		return items.toArray(new ReesterItem[] {});
	}

	public boolean addItem(ReesterItem item) {
		return !items.contains(item) && items.add(item);
	}
	
	public boolean removeItem(ReesterItem item) {
		return items.remove(item);
	}
	
}

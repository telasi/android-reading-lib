package telasi.android.reading;

public class ReesterItem {
	private int id;
	private int routeKey;
	private int scheduleKey;
	private int sequence;
	private Account account;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRouteKey() {
		return routeKey;
	}

	public void setRouteKey(int routeKey) {
		this.routeKey = routeKey;
	}

	public int getScheduleKey() {
		return scheduleKey;
	}

	public void setScheduleKey(int scheduleKey) {
		this.scheduleKey = scheduleKey;
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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ReesterItem)) return false;
		ReesterItem item = (ReesterItem) obj;
		return item.id == this.id;
	}
	
}

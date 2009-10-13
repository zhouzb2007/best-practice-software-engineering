package at.ac.tuwien.ifs.bpse.designpatterns.observer;

import java.util.Observable;

public class Sensor extends Observable {
	
	private int waterLevelUpstream;
	private int waterLevelDownstream;
	private long timestamp;

	
	public Sensor (int waterLevelUpstream, int waterLevelDownstream) {
		this.waterLevelUpstream = waterLevelUpstream;
		this.waterLevelDownstream = waterLevelDownstream;
	}
	
	public int getWaterLevelUpstream() {
		return waterLevelUpstream;
	}
	public void setWaterLevelUpstream(int waterLevelUpstream) {
		this.waterLevelUpstream = waterLevelUpstream;
		timestamp = System.currentTimeMillis();
		setChanged();
		notifyObservers(this);
	}
	public int getWaterLevelDownstream() {
		return waterLevelDownstream;
	}
	public void setWaterLevelDownstream(int waterLevelDownstream) {
		this.waterLevelDownstream = waterLevelDownstream;
		timestamp = System.currentTimeMillis();
		setChanged();
		notifyObservers(this);
	}

	public long getTimestamp() {
		return timestamp;
	}

	
}

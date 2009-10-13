package at.ac.tuwien.ifs.bpse.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class WaterLevelLogger implements Observer {

	private List<Long> timestampLog = new ArrayList<Long>();
	private List<Integer> upstreamLog = new ArrayList<Integer>();
	private List<Integer> downstreamLog = new ArrayList<Integer>();
	
	public void update(Observable o, Object arg) {
		Sensor sensor = (Sensor) o;
		logWaterLevel(sensor.getTimestamp(), sensor.getWaterLevelUpstream(), sensor.getWaterLevelDownstream());
	}
	
	public void logWaterLevel (long timestamp, int upstreamLevel, int downstreamLevel){
		timestampLog.add(timestamp);
		upstreamLog.add(upstreamLevel);
		downstreamLog.add(downstreamLevel);
	}

	public int getSize() {
		return timestampLog.size();
	}
	
	public long getTimestamp (int index) {
		return timestampLog.get(index);
	}
	
	public int getUpstreamLevel (int index) {
		return upstreamLog.get(index);
	}
	
	public int getDownstreamLevel (int index) {
		return downstreamLog.get(index);
	}

}

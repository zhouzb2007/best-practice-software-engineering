package at.ac.tuwien.ifs.bpse.designpatterns.observer;

import java.util.Observable;
import java.util.Observer;

public class CityAlarm implements Observer {

	private int maxWaterLevelUpstream;
	private int maxWaterLevelDownstream;
	private boolean floodAlarmUpstream = false;
	private boolean floodAlarmDownstream = false;
	
	public CityAlarm(int MaxWaterLevelUpstream, int MaxWaterLevelDownstream) {
		this.maxWaterLevelUpstream = MaxWaterLevelUpstream;
		this.maxWaterLevelDownstream = MaxWaterLevelDownstream;
	}
	
	private void checkFloodWarning (int waterLevelUpstream, int waterLevelDownstream) {
		if (waterLevelUpstream > maxWaterLevelUpstream){
			floodAlarmUpstream = true;
		} else {
			floodAlarmUpstream = false;
		}
		if (waterLevelDownstream > maxWaterLevelDownstream) {
			floodAlarmDownstream = true;
		} else {
			floodAlarmDownstream = false;
		}
			
	}
	
	public void update(Observable o, Object arg) {
		Sensor sensor = (Sensor) o;
		checkFloodWarning(sensor.getWaterLevelUpstream(), sensor.getWaterLevelDownstream());
	}

	public boolean isFloodAlarmUpstream() {
		return floodAlarmUpstream;
	}

	public boolean isFloodAlarmDownstream() {
		return floodAlarmDownstream;
	}
	
	

}

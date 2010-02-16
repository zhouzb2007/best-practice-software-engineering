package at.ac.tuwien.ifs.bpse.designpatterns.observer;

import java.util.Observable;
import java.util.Observer;

public class Dam implements Observer {

	private int waterFlow;
	private int waterFlowDelta;
	private int maxLevelUpstream;
	private int maxLevelDownstream;
	
	public Dam(int initialWaterFlow, int waterFlowDelta, int maxLevelUpstream, int maxLevelDownstream) {
		this.waterFlow = initialWaterFlow;
		this.waterFlowDelta = waterFlowDelta;
		this.maxLevelDownstream = maxLevelDownstream;
		this.maxLevelUpstream = maxLevelUpstream;
	}
	
	public int getWaterFlow() {
		return waterFlow;
	}

	public void setWaterFlow(int waterFlow) {
		this.waterFlow = waterFlow;
	}

	
	/**
	 * Calculate now flow rate according to water levels
	 * @param levelUpstream  current waterlevel upstream (before dam)
	 * @param levelDownstream current waterlevel downstream (after dam)
	 * @return flowrate new flowrate
	 * @throws FloodException 
	 */
	public int calculateNewFlowrate (int levelUpstream, int levelDownstream) throws FloodException {
		// In this example only the emergency case is 
		// implemented for demonstration purpose
		if ((levelDownstream < maxLevelDownstream) && (levelUpstream > maxLevelUpstream)) {
				changeWaterflow(waterFlow + waterFlowDelta);
		} else
		if ((levelDownstream > maxLevelDownstream)  && (levelUpstream < maxLevelUpstream)) {
			changeWaterflow(waterFlow - waterFlowDelta);
		} else
		if ((levelDownstream > maxLevelDownstream)  && (levelUpstream > maxLevelUpstream)) {
			// !! panik !!
			// do nothing, just set alarm
			throw new FloodException("Alarm: Water level exceeds maximum up- and downstream");
		}
		return waterFlow;
	}
	
	private void changeWaterflow (int newWaterflow) {
		waterFlow = newWaterflow;
		// now activate watergate controls...
	}

	private void alarm (){
		System.out.println("Flood Alarm");
	}

	public void update(Observable o, Object arg) {
		Sensor sensor = (Sensor) o;
		try {
			calculateNewFlowrate(sensor.getWaterLevelUpstream(), sensor.getWaterLevelDownstream());
		} catch (FloodException e) {
			alarm();
		}
	}
	
	

}

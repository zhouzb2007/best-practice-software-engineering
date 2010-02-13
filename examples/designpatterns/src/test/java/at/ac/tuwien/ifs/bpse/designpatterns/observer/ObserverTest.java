package at.ac.tuwien.ifs.bpse.designpatterns.observer;

import junit.framework.TestCase;

public class ObserverTest extends TestCase {

	private Dam dam;
	private Sensor sensor;
	private CityAlarm cityAlarm;
	private WaterLevelLogger waterLevelLogger;
	private final int INITIALLEVEL = 20;
	private final int INITIALDELTA = 5;
	private final int MAXLEVELUPSTREAM = 40;
	private final int MAXLEVELDOWNSTREAM = 20;
	
	protected void setUp() throws Exception {
		super.setUp();
		sensor = new Sensor(MAXLEVELUPSTREAM-1, MAXLEVELDOWNSTREAM-1);
		dam = new Dam (INITIALLEVEL, INITIALDELTA, MAXLEVELUPSTREAM, MAXLEVELDOWNSTREAM);
		cityAlarm = new CityAlarm(MAXLEVELUPSTREAM, MAXLEVELDOWNSTREAM);
		waterLevelLogger = new WaterLevelLogger();
		sensor.addObserver(dam);
		sensor.addObserver(cityAlarm);
		sensor.addObserver(waterLevelLogger);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dam = null;
	}

	public void testUpHighDownOk() throws FloodException {
		sensor.setWaterLevelUpstream(MAXLEVELUPSTREAM+1);
		assertEquals(INITIALLEVEL+5, dam.getWaterFlow());
	}

	public void testUpOkDownHigh() throws FloodException {
		sensor.setWaterLevelDownstream(MAXLEVELDOWNSTREAM+1);
		assertEquals(INITIALLEVEL-5, dam.getWaterFlow());
	}

	public void testUpOkDownOk() throws FloodException {
		sensor.setWaterLevelUpstream(MAXLEVELUPSTREAM);
		assertEquals(INITIALLEVEL, dam.getWaterFlow());
		sensor.setWaterLevelDownstream(MAXLEVELDOWNSTREAM);
		assertEquals(INITIALLEVEL, dam.getWaterFlow());
	}

	public void testUpHighDownHigh() {
		// when both levels are over limit, an Exception has to be thrown
		// plus the waterflow must not be changed
		sensor.setWaterLevelUpstream(MAXLEVELUPSTREAM+1);
		sensor.setWaterLevelDownstream(MAXLEVELDOWNSTREAM+1);
		assertEquals(INITIALLEVEL+INITIALDELTA, dam.getWaterFlow());
	}

	public void testDownHighUpHigh() {
		// when both levels are over limit, an Exception has to be thrown
		// plus the waterflow must not be changed
		sensor.setWaterLevelDownstream(MAXLEVELDOWNSTREAM+1);
		sensor.setWaterLevelUpstream(MAXLEVELUPSTREAM+1);
		assertEquals(INITIALLEVEL-INITIALDELTA, dam.getWaterFlow());
	}
	
	public void testCityAlarmDownstream() {
		assertFalse(cityAlarm.isFloodAlarmDownstream());
		assertFalse(cityAlarm.isFloodAlarmUpstream());
		sensor.setWaterLevelDownstream(MAXLEVELDOWNSTREAM+1);
		assertTrue(cityAlarm.isFloodAlarmDownstream());
		assertFalse(cityAlarm.isFloodAlarmUpstream());
		sensor.setWaterLevelDownstream(MAXLEVELDOWNSTREAM);
		assertFalse(cityAlarm.isFloodAlarmDownstream());
		assertFalse(cityAlarm.isFloodAlarmUpstream());
	}
	
	public void testCityAlarmUpstream() {
		assertFalse(cityAlarm.isFloodAlarmDownstream());
		assertFalse(cityAlarm.isFloodAlarmUpstream());
		sensor.setWaterLevelUpstream(MAXLEVELUPSTREAM+1);
		assertFalse(cityAlarm.isFloodAlarmDownstream());
		assertTrue(cityAlarm.isFloodAlarmUpstream());
		sensor.setWaterLevelUpstream(MAXLEVELUPSTREAM);
		assertFalse(cityAlarm.isFloodAlarmDownstream());
		assertFalse(cityAlarm.isFloodAlarmUpstream());
	}
	
	public void testLogger() {
		assertEquals(0, waterLevelLogger.getSize());
		sensor.setWaterLevelDownstream(1);
		assertEquals(1, waterLevelLogger.getSize());
		long timestamp = sensor.getTimestamp();
		assertEquals(timestamp, waterLevelLogger.getTimestamp(0));
		assertEquals(1, waterLevelLogger.getDownstreamLevel(0));
		assertEquals(MAXLEVELUPSTREAM-1, waterLevelLogger.getUpstreamLevel(0));
		
		sensor.setWaterLevelUpstream(8);
		assertEquals(2, waterLevelLogger.getSize());
		assertEquals(8, waterLevelLogger.getUpstreamLevel(1));
	}
}

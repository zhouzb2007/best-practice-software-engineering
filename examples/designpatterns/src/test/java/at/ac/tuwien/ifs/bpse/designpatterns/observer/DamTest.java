package at.ac.tuwien.ifs.bpse.designpatterns.observer;

import junit.framework.TestCase;

public class DamTest extends TestCase {

	private Dam dam;
	private final int INITIALLEVEL = 20;
	private final int INITIALDELTA = 5;
	private final int MAXLEVELUPSTREAM = 40;
	private final int MAXLEVELDOWNSTREAM = 20;
	
	protected void setUp() throws Exception {
		super.setUp();
		dam = new Dam (INITIALLEVEL, INITIALDELTA, MAXLEVELUPSTREAM, MAXLEVELDOWNSTREAM);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dam = null;
	}

	public void testUpHighDownOk() throws FloodException {
		int flow = dam.calculateNewFlowrate(MAXLEVELUPSTREAM+1, MAXLEVELDOWNSTREAM-1);
		assertEquals(INITIALLEVEL+5, flow);
	}

	public void testUpOkDownHigh() throws FloodException {
		int flow = dam.calculateNewFlowrate(MAXLEVELUPSTREAM-1, MAXLEVELDOWNSTREAM+1);
		assertEquals(INITIALLEVEL-5, flow);
	}

	public void testUpOkDownOk() throws FloodException {
		int flow = dam.calculateNewFlowrate(MAXLEVELUPSTREAM-1, MAXLEVELDOWNSTREAM-1);
		assertEquals(INITIALLEVEL, flow);
	}

	public void testUpHighDownHigh() {
		// when both levels are over limit, an Exception has to be thrown
		// plus the waterflow must not be changed
		try {
			dam.calculateNewFlowrate(MAXLEVELUPSTREAM+1, MAXLEVELDOWNSTREAM+1);
			assertTrue(false);
		} catch (FloodException e) {
		}
		assertEquals(INITIALLEVEL, dam.getWaterFlow());
	}
}

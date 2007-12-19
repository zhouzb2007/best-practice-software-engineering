package at.ac.tuwien.ifs.bpse.designpatterns;

import at.ac.tuwien.ifs.bpse.designpatterns.delegation.NotificationManagerTest;
import at.ac.tuwien.ifs.bpse.designpatterns.factory.NotificationFactoryTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for at.ac.tuwien.ifs.bpse.designpatterns");
		//$JUnit-BEGIN$
		suite.addTestSuite(NotificationManagerTest.class);
		suite.addTestSuite(NotificationFactoryTest.class);
		//$JUnit-END$
		return suite;
	}

}

package at.ac.tuwien.ifs.bpse.designpatterns;

import at.ac.tuwien.ifs.bpse.designpatterns.delegation.NotificationManagerTest;
import at.ac.tuwien.ifs.bpse.designpatterns.factory.NotificationFactoryTest;
import at.ac.tuwien.ifs.bpse.designpatterns.observer.DamTest;
import at.ac.tuwien.ifs.bpse.designpatterns.observer.ObserverTest;
import at.ac.tuwien.ifs.bpse.designpatterns.singleton.NoficationManagerServiceTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for at.ac.tuwien.ifs.bpse.designpatterns");
		//$JUnit-BEGIN$
		suite.addTestSuite(NotificationManagerTest.class);
		suite.addTestSuite(NotificationFactoryTest.class);
		suite.addTestSuite(NoficationManagerServiceTest.class);
		suite.addTestSuite(DamTest.class);
		suite.addTestSuite(ObserverTest.class);
		//$JUnit-END$
		return suite;
	}

}

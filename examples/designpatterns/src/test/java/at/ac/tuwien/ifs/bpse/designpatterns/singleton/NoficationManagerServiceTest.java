/**
 * 
 */
package at.ac.tuwien.ifs.bpse.designpatterns.singleton;

import at.ac.tuwien.ifs.bpse.designpatterns.beans.Person;
import at.ac.tuwien.ifs.bpse.designpatterns.delegation.NotificationManager;
import junit.framework.TestCase;

public class NoficationManagerServiceTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


	/**
	 * Test if instantiation works
	 *
	 */
	public void testServiceManagerInstance() {
		NoficationManagerService nms = NoficationManagerService.getInstance();
		assertNotNull(nms);
	}

	/**
	 * Test if Object is really a singleton
	 *
	 */
	public void testServiceManagerSingleton() {
		NoficationManagerService nms1 = NoficationManagerService.getInstance();
		NoficationManagerService nms2 = NoficationManagerService.getInstance();
		// has to be the same instance
		assertEquals(nms1, nms2);
		nms1.setServiceManagerID("myService");
		assertEquals(nms1.getServiceManagerID(), nms2.getServiceManagerID());
	}
	
	/**
	 * Test if the getNotificationManager method works properly
	 *
	 */
	public void testNotificationManager() {
		NoficationManagerService nms = NoficationManagerService.getInstance();
		NotificationManager nm = nms.getNotificationManager();
		assertNotNull(nm);
		Person pi = new Person("Alexander", "Schatten", "alexemail", "12345");
		nm.addMember(pi, NotificationManager.NotificationMethod.SMS);
		Person po = nm.getMember(0);
		assertEquals(po.getEmailaddress(), pi.getEmailaddress());
	}
	
	
	/**
	 * As the NotificationManagerService is a Singleton
	 * Due to this implemenation also the getNotificationManager 
	 * method has to provice always the same instance
	 *
	 */
	public void testNotificationManagerSingleton() {
		NoficationManagerService nms = NoficationManagerService.getInstance();
		NotificationManager nm1 = nms.getNotificationManager();
		NotificationManager nm2 = nms.getNotificationManager();
		assertEquals(nm1, nm2);
	}
}

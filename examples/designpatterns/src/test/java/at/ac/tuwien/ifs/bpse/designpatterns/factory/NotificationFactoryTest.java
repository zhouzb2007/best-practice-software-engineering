package at.ac.tuwien.ifs.bpse.designpatterns.factory;

import junit.framework.TestCase;
import at.ac.tuwien.ifs.bpse.designpatterns.adapter.VoiceCallNotificationAdapter;
import at.ac.tuwien.ifs.bpse.designpatterns.beans.Person;
import at.ac.tuwien.ifs.bpse.designpatterns.delegation.Email;
import at.ac.tuwien.ifs.bpse.designpatterns.delegation.INotification;
import at.ac.tuwien.ifs.bpse.designpatterns.immutable.NotificationIDs;
import at.ac.tuwien.ifs.bpse.designpatterns.proxy.RecordNotificationProxy;

public class NotificationFactoryTest extends TestCase {

	private Person person = new Person ("Alexander", "Schatten", "alexemail", "12345");
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public void testCreateEmailNotification () throws Exception{
		INotification notif = NotificationFactory.createNotification("Email");
		assertEquals(Email.class, notif.getClass());
	}
	
	
	public void testCreateSmsNotification () throws Exception{
		INotification notif = NotificationFactory.createNotification("SMS");
		//assertEquals(Sms.class, notif.getClass());
		assertEquals(RecordNotificationProxy.class, notif.getClass());

	}
	
	
	public void testCreateVoiceNotification () throws Exception{
		INotification notif = NotificationFactory.createNotification("Voice");
		assertEquals(VoiceCallNotificationAdapter.class, notif.getClass());
	}

	
	public void testWrongNotificationType () {
		try {
			INotification notif = NotificationFactory.createNotification("not existing");
			assertNull(notif);
			fail("Notification ID is not existing, should fail!");
		} catch (NotificationMethodNotDefinedException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void testGetNotificationIDs () {
		NotificationIDs nids = NotificationFactory.getNotificationIDs();
		assertEquals(3, nids.size());
		assertEquals("Email", nids.getId(0));
		assertEquals("SMS",   nids.getId(1));
		assertEquals("Voice", nids.getId(2));
		try {
			String s = nids.getId(3);
			System.out.println(s);
			fail ("Index out of bounds exception expected!");
		} catch (IndexOutOfBoundsException e) {
			// ok
		}
		try {
			String s = nids.getId(-1);
			System.out.println(s);
			fail ("Index out of bounds exception expected!");
		} catch (IndexOutOfBoundsException e) {
			// ok
		}
	}
	
	/**
	 * Test Email Notification via Factory
	 */
	public void testSendEmail () throws Exception {
		INotification notif = NotificationFactory.createNotification("Email");
		notif.setPerson(person);
		String status = notif.send("Hello Email, factory used");
		assertEquals("Email sent", status);
	}

	/**
	 * Test SMS Notification via Factory
	 * SMS has actually turned record on, so 
	 * SMS is sending via proxy 
	 */
	public void testSendSMS () throws Exception {
		INotification notif = NotificationFactory.createNotification("SMS");
		notif.setPerson(person);
		String status = notif.send("Hello SMS, factory used");
		assertEquals("SMS sent", status);
	}
	
	/**
	 * Voice is connected through an adapter, so we test this too 
	 */
	public void testNotifyVoice () throws Exception {
		INotification notif = NotificationFactory.createNotification("Voice");
		notif.setPerson(person);
		String status = notif.send("Hello Voice, factory used");
		assertEquals("Phonecall done", status);
	}
}

package at.ac.tuwien.ifs.bpse.designpatterns.factory;

import junit.framework.TestCase;
import at.ac.tuwien.ifs.bpse.designpatterns.beans.Person;
import at.ac.tuwien.ifs.bpse.designpatterns.delegation.Email;
import at.ac.tuwien.ifs.bpse.designpatterns.delegation.INotification;
import at.ac.tuwien.ifs.bpse.designpatterns.delegation.Sms;
import at.ac.tuwien.ifs.bpse.designpatterns.factory.NotificationFactory;

public class NotificationFactoryTest extends TestCase {

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
		assertEquals(Sms.class, notif.getClass());
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
	
	public void testSendEmail () throws Exception {
		INotification notif = NotificationFactory.createNotification("Email");
		notif.setPerson(new Person ("Alexander", "Schatten", "alexemail", "12345"));
		String status = notif.send("Hello Email");
		assertEquals("Email sent", status);
	}
}

package at.ac.tuwien.ifs.bpsw.designpatterns;

import java.util.List;

import at.ac.tuwien.ifs.bpsw.designpatterns.beans.Person;
import at.ac.tuwien.ifs.bpsw.designpatterns.delegation.NotificationManager;
import at.ac.tuwien.ifs.bpsw.designpatterns.delegation.NotificationManager.NotificationMethod;
import junit.framework.TestCase;

public class NotificationManagerTest extends TestCase {
	
	
	private NotificationManager notMan = null;

	protected void setUp() throws Exception {
		super.setUp();
		notMan = new NotificationManager();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public void testAddMember () {
		Person addP = new Person("Alexander", "Schatten", "alexemail", "12345");
		notMan.addMember(addP, NotificationMethod.EMAIL);
		Person getP = notMan.getMember(0);
		assertEquals(addP.getEmailaddress(), getP.getEmailaddress());
		assertEquals(addP.getFirstname(), getP.getFirstname());
		assertEquals(addP.getLastname(), getP.getLastname());
		assertEquals(addP.getTelephoneNr(), getP.getTelephoneNr());
		notMan.removeMember(0);
		try {
			getP = notMan.getMember(0);
			fail("Out of BoundsException should be thrown!");
		} catch (IndexOutOfBoundsException e) {
		}
	}
	
	
	public void testNotification() {
		notMan.addMember(new Person("Alexander", "Schatten", "alexemail",   "12345"), NotificationMethod.EMAIL);
		notMan.addMember(new Person("Stefan",    "Biffl",    "stefanemail", "54321"), NotificationMethod.SMS);
		List<String> statusList = notMan.sendNotifications("Test");
		assertEquals(statusList.get(0), "Email sent");
		assertEquals(statusList.get(1), "SMS sent");
	}
}

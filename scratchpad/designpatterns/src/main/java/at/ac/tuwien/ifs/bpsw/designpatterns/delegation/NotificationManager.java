package at.ac.tuwien.ifs.bpsw.designpatterns.delegation;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.ifs.bpsw.designpatterns.beans.Person;

public class NotificationManager {


	public enum NotificationMethod {SMS, EMAIL};
	
	/**
	 * This list contains references to all Person instances that can be notified by some event
	 */
	private List<Person> members = new ArrayList<Person>();
	
	/**
	 * Add new Person to list of notifications
	 * @param person Person Object holding contact information
	 * @param notificationPref preferences of notification;
	 *        currently implemented "sms" for SMS notification and
	 *        "email" for Email notification
	 */
	public void addMember(Person person, NotificationMethod notificationPref) {
		members.add(person);
		INotification notification;
		if (notificationPref == NotificationMethod.SMS) {
			notification = new Sms();
		} else {
			notification = new Email();
		}
		notification.setPerson(person);
		person.setNotificationPreference(notification);
	}
	
	/**
	 * Send Notifications to all persons with their desired notification method
	 * @param message Message to be sent
	 */
	public List<String> sendNotifications(String message) {
		List<String> statusList = new ArrayList<String>();
		for (Person p : members) {
			String status = p.sendMessage(message);
			statusList.add(status);
		}
		return statusList;
	}
	
	/**
	 * @param id Index of Entry in List
	 * @return Person Object
	 */
	public Person getMember (int id) {
		return members.get(id);
	}
	
	/**
	 * Remove one Member
	 * @param id remover member at list position id
	 */
	public void removeMember (int id) {
		members.remove(id);
	}
}

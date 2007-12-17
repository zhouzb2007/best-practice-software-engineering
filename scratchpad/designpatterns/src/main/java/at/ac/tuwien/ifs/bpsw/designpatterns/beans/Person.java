package at.ac.tuwien.ifs.bpsw.designpatterns.beans;

import at.ac.tuwien.ifs.bpsw.designpatterns.delegation.INotification;

public class Person {

	private String firstname;
	private String lastname;
	private String emailaddress;
	private String telephoneNr;
	private INotification notification;
	
	// Constructor
	
	public Person (String firstname, String lastname, String emailaddress, String telephonNr) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailaddress = emailaddress;
		this.telephoneNr = telephonNr;
	}

	public Person (String firstname, String lastname, String emailaddress, String telephonNr, INotification notification) {
		this (firstname, lastname, emailaddress, telephonNr);
		setNotificationPreference(notification);
	}
	
	// Getter and Setter Methods
	
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getTelephoneNr() {
		return telephoneNr;
	}
	public void setTelephoneNr(String telephoneNr) {
		this.telephoneNr = telephoneNr;
	}
	public void setNotificationPreference(INotification notification) {
		this.notification = notification;
	}
	
	
	/**
	 * Notify this person on the preferred notification channel
	 * @param content
	 * @return Status Message from Notification Service
	 */
	public String sendMessage(String content) {
		String status = notification.send(content);
		return status;
	}
}

package at.ac.tuwien.ifs.bpsw.designpatterns.delegation;

import at.ac.tuwien.ifs.bpsw.designpatterns.beans.Person;

public class Sms implements INotification {

	Person person;
	String lastMsg = "";
	
	public String send(String content) {
		lastMsg = content;
		System.out.println("Sending SMS: " + content);
		return "SMS sent";
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return ("SMS Notification, last Msg was: " + lastMsg);
	}

}

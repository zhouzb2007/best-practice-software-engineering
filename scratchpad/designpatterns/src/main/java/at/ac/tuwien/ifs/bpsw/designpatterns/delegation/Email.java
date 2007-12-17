package at.ac.tuwien.ifs.bpsw.designpatterns.delegation;

import at.ac.tuwien.ifs.bpsw.designpatterns.beans.Person;

public class Email implements INotification {

	Person person;
	String lastMsg = "";
	
	public String send(String content) {
		lastMsg = content;
		System.out.println("Sending Email: " + content);
		return "Email sent";
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return ("Email Notification, last Msg was: " + lastMsg);
	}

}

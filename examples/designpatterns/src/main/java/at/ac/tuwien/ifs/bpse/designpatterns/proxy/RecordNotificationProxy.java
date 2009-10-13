package at.ac.tuwien.ifs.bpse.designpatterns.proxy;

import at.ac.tuwien.ifs.bpse.designpatterns.beans.Person;
import at.ac.tuwien.ifs.bpse.designpatterns.delegation.INotification;

public class RecordNotificationProxy implements INotification {

	private INotification notify;
	
	public RecordNotificationProxy (INotification notify) {
		this.notify = notify;
	}
	
	
	public String send(String content) {
		// This println is only for demonstration purpose
		// In a real implementation this should be done
		// e.g. with a proper logging framework
		System.out.println("Proxy Interception: forwarding '" + content + "'");
		return notify.send(content);
	}

	public void setPerson(Person person) {
		notify.setPerson(person);
	}

}

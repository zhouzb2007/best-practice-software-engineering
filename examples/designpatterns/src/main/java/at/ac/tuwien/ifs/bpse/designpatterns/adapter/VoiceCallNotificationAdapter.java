package at.ac.tuwien.ifs.bpse.designpatterns.adapter;

import at.ac.tuwien.ifs.bpse.designpatterns.beans.Person;
import at.ac.tuwien.ifs.bpse.designpatterns.delegation.INotification;

/**
 * This Adapter class implements the INotification interface and
 * connects to the actual class that does the "phonecall".
 * 
 * This class is needed, as the "phonecall" class/system is by some 
 * reason not able to implement this interface, e.g. because it is
 * a system from a proprietary vendor, a legacy system or by some 
 * other reason.
 *
 */
public class VoiceCallNotificationAdapter implements INotification {
	
	private Person receiver = null;

	public String send(String content) {
		VoiceCall vc = new VoiceCall();
		String status = vc.callNr(receiver.getTelephoneNr(), content);
		return status;
	}

	public void setPerson(Person person) {
		receiver = person;
	}

}

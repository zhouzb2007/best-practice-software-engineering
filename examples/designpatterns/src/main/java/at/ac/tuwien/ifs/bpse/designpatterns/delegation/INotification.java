package at.ac.tuwien.ifs.bpse.designpatterns.delegation;

import at.ac.tuwien.ifs.bpse.designpatterns.beans.Person;

public interface INotification {

	public void setPerson (Person person);
	public String send (String content);
	
}

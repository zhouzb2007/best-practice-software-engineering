package at.ac.tuwien.ifs.bpsw.designpatterns.delegation;

import at.ac.tuwien.ifs.bpsw.designpatterns.beans.Person;

public interface INotification {

	public void setPerson (Person person);
	public String send (String content);
	
}

package at.ac.tuwien.ifs.bpse.designpatterns.factory;

public class NotificationMethodNotDefinedException extends Exception {

	private static final long serialVersionUID = -2567118931275910282L;

	public NotificationMethodNotDefinedException () {
		super();
	}
	
	public NotificationMethodNotDefinedException (String s) {
		super(s);
	}

}

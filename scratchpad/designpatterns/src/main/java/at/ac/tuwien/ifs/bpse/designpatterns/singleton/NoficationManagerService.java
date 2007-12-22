package at.ac.tuwien.ifs.bpse.designpatterns.singleton;

import at.ac.tuwien.ifs.bpse.designpatterns.delegation.NotificationManager;

/**
 * This Service class should be Singleton, i.e. all clients
 * work with only one instance of that class
 * @author aschatt
 *
 */
public class NoficationManagerService {
	
	/**
	 * This class should be a Singleton, hence the constructor is
	 * declared private. So it is not possible to get an instance
	 * of this class by using a constructor.
	 *
	 */
	private NoficationManagerService() {
	}
	
	private static NoficationManagerService notmanservice = new NoficationManagerService();
	
	/**
	 * This method returns the instance of this class.
	 * Only one instance is used for all "client" classes
	 * @return
	 */
	public static NoficationManagerService getInstance() {
		return notmanservice;
	}
	
	/* ID of this Service; just to show how setter and getter work with Singletons */
	private String serviceManagerID = "";

	/*
	 * This is an instance variable, but due to the fact, that the class is
	 * singleton and the instantiation is done here, it is only done once,
	 * hence the clients always get back only one instance of the 
	 * NotificationManager
	 */
	private NotificationManager notificationManager = new NotificationManager();

	public String getServiceManagerID() {
		return serviceManagerID;
	}

	public void setServiceManagerID(String serviceManagerID) {
		this.serviceManagerID = serviceManagerID;
	}

	public NotificationManager getNotificationManager() {
		return notificationManager;
	}

}

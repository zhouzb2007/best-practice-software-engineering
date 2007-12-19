package at.ac.tuwien.ifs.bpse.designpatterns.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


import at.ac.tuwien.ifs.bpse.designpatterns.delegation.INotification;

/**
 * This factory class reads notification methods from a configuration file.
 * In the configuration file every line stands for one notification method:
 * first stands the id then a "," then the class that implements the INotification Interface
 * e.g.:
 * 
 * Email, at.ac.tuwien.ifs.bpse.designpatterns.delegation.Email
 * 
 * The factory method reads the id and tries to find the appropriate notification method
 * then it makes an instance of the respective class.
 * 
 * @author aschatt
 *
 */
public class NotificationFactory {

	private static final String NOTIFICATION_CLASSES_CONFIG = "/notification_classes.cfg";
	private static NotificationFactory notificationFactory = new NotificationFactory();
	private HashMap<String, String> notificationClasses = new HashMap<String, String>();
	
	public NotificationFactory() {
		try {
			readCfg();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param type Type of notification; 
	 *             Possible Notification methods are read 
	 *             from configuration file
	 * @return Concrete instance of class to do notification
	 * @throws NotificationMethodNotDefinedException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static INotification createNotification (String type) 
	throws NotificationMethodNotDefinedException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (!notificationFactory.notificationClasses.containsKey(type))
			throw new NotificationMethodNotDefinedException("Notification '" + type + "' is not defined.");
		String classname = notificationFactory.notificationClasses.get(type);
		INotification notification = (INotification)Class.forName(classname).newInstance();
		return notification;
	}

	/*
	public static List<String> getNotificationIDs () {
	}
	*/
	
	/**
	 * Read configuration file that contains list of notification methods
	 * @throws IOException
	 */
	private void readCfg() throws IOException {
		// Get Input Stream from config file in classpath
		InputStream is = getClass().getResourceAsStream(NOTIFICATION_CLASSES_CONFIG);
		InputStreamReader isr = new InputStreamReader(is);
		// Buffered reader is used, as it provides a convenient readLine() method
		// to read a full line from the text file in one command
		BufferedReader br = new BufferedReader(isr);
		String id = null;
		String classname = null;
		String s = null;
		while ((s = br.readLine()) != null) {
			StringTokenizer stk = new StringTokenizer(s, ",");
			id = stk.nextToken().trim();
			classname = stk.nextToken().trim();
			notificationClasses.put(id, classname);
		}
	}
	

}

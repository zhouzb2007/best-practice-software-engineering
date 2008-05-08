package at.ac.tuwien.ifs.bpse.designpatterns.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

import at.ac.tuwien.ifs.bpse.designpatterns.delegation.INotification;
import at.ac.tuwien.ifs.bpse.designpatterns.immutable.NotificationIDs;
import at.ac.tuwien.ifs.bpse.designpatterns.proxy.RecordNotificationProxy;

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
 * This factory actually also is a Singleton implementation.
 * This is due to the fact, that the data should not be read from disk with
 * every access but only once at initialisation.
 * 
 * @author aschatt
 *
 */
public class NotificationFactory {

	private static final String NOTIFICATION_CLASSES_CONFIG = "/notification_classes.cfg";
	private static NotificationFactory notificationFactory = new NotificationFactory();
	private HashMap<String, String> notificationClasses = new HashMap<String, String>();
	private HashMap<String, Boolean> notificationRecord = new HashMap<String, Boolean>();
	
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
		// Create Instance of actual notification class
		INotification notification = (INotification)Class.forName(classname).newInstance();
		// Check if notification should be recorded, then 
		// usee RecordNotificationProxy
		boolean doRecord = notificationFactory.notificationRecord.get(type);
		if (doRecord) {
			INotification recordNotification = new RecordNotificationProxy (notification);
			return recordNotification;
		} else {
			return notification;			
		}
	}

	/**
	 * 
	 * @return sorted array of "ids", i.e. the types of notifications, e.g. for a
	 * User Interface to display the options
	 */
	public static NotificationIDs getNotificationIDs () {
		String[] id = (String[])notificationFactory.notificationClasses.keySet().toArray(new String[0]);
		Arrays.sort(id);
		return new NotificationIDs(id);
	}
	
	/**
	 * Read configuration file that contains list of notification methods
	 * @throws IOException
	 */
	private void readCfg() throws IOException {
		// Get Input Stream from config file in classpath, i.e. use classpath resource 
		InputStream is = getClass().getResourceAsStream(NOTIFICATION_CLASSES_CONFIG);
		InputStreamReader isr = new InputStreamReader(is);
		// Buffered reader is used, as it provides a convenient readLine() method
		// to read a full line from the text file in one command
		BufferedReader br = new BufferedReader(isr);
		String id = null;
		String classname = null;
		boolean doRecord = false;
		String s = null;
		// use String Tokenizer to break each line into
		// Notification "ID" and actual implementing class
		// put id and classname into Hashmap
		while ((s = br.readLine()) != null) {
			StringTokenizer stk = new StringTokenizer(s, ",");
			id = stk.nextToken().trim();
			classname = stk.nextToken().trim();
			s = stk.nextToken().trim();
			if (s.equals("record_on")) {
				doRecord = true;
			} else {
				doRecord = false;
			}
			notificationClasses.put(id, classname);
			notificationRecord.put(id, doRecord);
		}
	}
	

}

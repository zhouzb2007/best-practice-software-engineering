package at.ac.tuwien.ifs.qse.se1.basis.helper;

import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

/**
 * Configures the logger and returns a reference to the logger.
 * 
 * @author The SE-Team
 * @version 1.0
 * 
 */
public class Logger {

	private static boolean configured = false;
	
  /**
   * Configures the logger with the property file
   * defined in Constants.LOG_PROPERTIES_FILE.
   * Uses the system class loader o find that file
   * @see ClassLoader.getSystemResource(java.lang.String)
   */
	private static void configureLog4J(Class c) {
		URL url = c.getResource(Constants.LOG_PROPERTIES_FILE);
		if(url == null)
			System.out.println("Unable to load log configuration from " + Constants.LOG_PROPERTIES_FILE);
		else 
			PropertyConfigurator.configure(url);
		
		LogFactory.getLog(Logger.class).info("Logging configured");
	}
	
   /**
	* Configures the logger with the property file
	* defined in Constants.LOG_PROPERTIES_FILE.
	* Uses the system class loader o find that file
	* @see ClassLoader.getSystemResource(java.lang.String)
	*/
	public static Log getLogger(Class c)
	{
		if(!configured) {
			configureLog4J(c);
			configured = true;
		}			
		return LogFactory.getLog(c);		
	}
}

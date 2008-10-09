package at.ac.tuwien.ifs.qse.se1.basis.helper;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

/**
 * This Class contains application-wide used methods.
 * 
 * @author The SE-Team
 * @version 1.0
 * 
 */
public class Helper {

	private static Log log = LogFactory
			.getLog("at.ac.tuwien.ifs.qse.se1.basis");

	/**
	 * Configures the Logger.
	 * 
	 */
	public static void configureLog4j() {
		File file = new File(Constants.LOG_PROPERTIES_FILE);
		if (!file.exists())
			System.out.println(Constants.LOG_PROPERTIES_FILE + " File not found.");
		PropertyConfigurator.configure(Constants.LOG_PROPERTIES_FILE);
		log.info("Logger Started");
	}

}

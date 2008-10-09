package basis.test;

import org.apache.commons.logging.Log;

import basis.test.dao.JdbcStudentTest;
import basis.test.export.ExportImportTest;
import basis.test.export.HtmlExportTest;
import basis.test.export.XmlExportImportTest;

import junit.framework.Test;
import junit.framework.TestSuite;
import at.ac.tuwien.ifs.qse.se1.basis.helper.Logger;

/**
 * This class is the entry-point for a jUnit test run. Via the method <c>suite()</c>
 * jUnit retrives an instance of a test, which is the executed. If you want to
 * have your own TestClasses executed, you have to add them here.
 * 
 * @author The SE-Team
 * @version 1.0
 * @see <a href="http://junit.sourceforge.net/doc/cookbook/cookbook.htm">jUnit
 *      Coockbook</a>
 */
public class AllTests {

	/**
	 * Retrieves the logger for this root Package.
	 */
	private static Log log = Logger.getLogger(AllTests.class);
	
	/**
	 * Creates an new TestSuite containing all Tests in the sub-packages. If you
	 * want your own TestClasses executed you have to add them here to the
	 * TestSuit to have them executed in a testrun of jUnit.
	 * 
	 * @return an initialized TestSuite
	 */
	public static Test suite() {
		// configure log4j based on log.properties

		log.info("Starting Unit-Tests");
		TestSuite suite = new TestSuite(
				"Test for at.ac.tuwien.ifs.qse.se1.basis.test");
		// $JUnit-BEGIN$
		suite.addTestSuite(JdbcStudentTest.class);
		suite.addTestSuite(XmlExportImportTest.class);
		suite.addTestSuite(ExportImportTest.class);
		suite.addTestSuite(HtmlExportTest.class);
		// $JUnit-END$
		return suite;
	}

}

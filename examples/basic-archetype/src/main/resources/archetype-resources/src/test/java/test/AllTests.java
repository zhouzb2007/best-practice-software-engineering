package ${groupId}.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ${groupId}.test.dao.AllDAOTests;
import ${groupId}.test.export.AllExportTests;

/**
 * This class is the entry-point for a jUnit test run. If you want to
 * have your own TestClasses executed, you have to add them here.
 * 
 * @author The SE-Team
 * @version 1.0
 * @see <a href="http://junit.sourceforge.net/doc/cookbook/cookbook.htm">jUnit
 *      Coockbook</a>
 */
@RunWith(Suite.class)
@SuiteClasses( { AllDAOTests.class, AllExportTests.class })

public class AllTests {
}


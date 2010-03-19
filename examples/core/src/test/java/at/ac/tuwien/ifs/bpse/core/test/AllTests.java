package at.ac.tuwien.ifs.bpse.core.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import at.ac.tuwien.ifs.bpse.core.test.persistence.AllPersistanceTests;

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
@SuiteClasses( { AllPersistanceTests.class })

public class AllTests {
}


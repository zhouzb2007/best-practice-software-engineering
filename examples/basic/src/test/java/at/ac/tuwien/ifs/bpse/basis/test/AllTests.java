package at.ac.tuwien.ifs.bpse.basis.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import at.ac.tuwien.ifs.bpse.basis.test.dao.JdbcStudentTest;
import at.ac.tuwien.ifs.bpse.basis.test.export.ExportImportTest;
import at.ac.tuwien.ifs.bpse.basis.test.export.HtmlExportTest;
import at.ac.tuwien.ifs.bpse.basis.test.export.XmlExportImportTest;

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
@SuiteClasses( { JdbcStudentTest.class, ExportImportTest.class, HtmlExportTest.class, XmlExportImportTest.class })

public class AllTests {
}


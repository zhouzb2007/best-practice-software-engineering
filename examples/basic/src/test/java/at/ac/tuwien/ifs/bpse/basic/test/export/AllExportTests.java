package at.ac.tuwien.ifs.bpse.basic.test.export;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( { ExportImportTest.class, HtmlExportTest.class, XmlExportImportTest.class })
public class AllExportTests {
}

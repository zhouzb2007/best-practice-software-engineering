package at.ac.tuwien.ifs.bpse.basis.test.export;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import at.ac.tuwien.ifs.bpse.basis.domain.Student;
import at.ac.tuwien.ifs.bpse.basis.export_import.HtmlExport;
import at.ac.tuwien.ifs.bpse.basis.helper.Constants;

/**
 * Class containing the TestCases for the HTML Exporter. Because we have no
 * import-mechanism for HTML we can only compare the generated HTML-File to a
 * file which was checked manualy.
 * 
 * @author The SE-Team
 * @version 1.0
 */
public class HtmlExportTest extends TestCase {

	//private static Log log = LogFactory.getLog(HtmlExportTest.class);
	private XmlBeanFactory xbf;
	
	/**
	 * Holds the absolute path of the current directory. 
	 */
	private String pathToFile;
	
	/**
	 * The list of Students used as testdata, also reinitialized for every new
	 * TestCase.
	 */
	private List<Student> studenten;


	/**
	 * This method is executen before every TestCase
	 */
	protected void setUp() throws Exception {
		super.setUp();
		ClassPathResource res = new ClassPathResource(Constants.SPRINGBEANS_TEST);
		xbf = new XmlBeanFactory(res);
		ClassPathResource currentWorkingDir = new ClassPathResource(".");
		pathToFile = currentWorkingDir.getFile().getAbsolutePath();		
		studenten = new ArrayList<Student>();
		Student s1 = (Student) xbf.getBean("StudentAlexanderSchatten");
		s1.setId(new Integer(1));
		Student s2 = (Student) xbf.getBean("StudentHubertMeixner");
		s2.setId(new Integer(2));
		studenten.add(s1);
		studenten.add(s2);
	}

	/**
	 * This method is invoced after each TestCase
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		xbf.destroySingletons();
	}

	/**
	 * Reads a file into a String.
	 * 
	 * @param filename
	 *            the File to read.
	 * @return The content of the file as String.
	 */
	private String readFile(Reader r) {
		StringBuffer fcb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(r);

			String line;
			while ((line = br.readLine()) != null) {
				fcb.append(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fcb.toString();
	}

	/**
	 * TestCase to creating an HTML-File. Because we have no importer for HTML,
	 * we compare the result with a file that was manually checked.
	 * @throws IOException 
	 * 
	 */
	public void testWrite() throws IOException {
		String filename_test = pathToFile + "/test/export.html";
		
		// export test data to html file
		HtmlExport he = new HtmlExport();
		he.setTitle("Html Export");
		try {
			he.write(studenten, filename_test);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// check if export is correct
		//InputStream ipCorrect = getClass().getResourceAsStream("basic/target/test-classes/test/html-export.html");
		File newFile = new File("target/test-classes/test/html-export.html");
		String correct = readFile(new FileReader(newFile));
		//String correct = readFile(new InputStreamReader(ipCorrect));

		InputStream ipCorrect = getClass().getResourceAsStream("/test/html-export.html");

		correct = readFile(new InputStreamReader(ipCorrect));
		String test = readFile(new FileReader(filename_test));
		assertEquals(correct, test);
		
		ipCorrect.close();
		
		// delete file
		new File(filename_test).delete();
	}

}

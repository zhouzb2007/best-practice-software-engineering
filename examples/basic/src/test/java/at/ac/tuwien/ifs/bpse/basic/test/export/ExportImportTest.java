package at.ac.tuwien.ifs.bpse.basic.test.export;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import at.ac.tuwien.ifs.bpse.basic.domain.Student;
import at.ac.tuwien.ifs.bpse.basic.export_import.Export;
import at.ac.tuwien.ifs.bpse.basic.export_import.Import;
import at.ac.tuwien.ifs.bpse.basic.helper.Constants;

/**
 * Class containing the testcases for the Importers and Exporters. The instanced
 * for this TestCase are retrieved by the BeanFactory. Testing an Importer
 * combined with an Exporter ist rather easy: At first we generate some
 * TestData, then the TestData is exported and imported again. Finally we
 * compare the Imported data with the data we have first exported.
 * 
 * @author The SE-Team
 * @version 1.0
 */
public class ExportImportTest {

	/**
	 * Spring Frameworks XML Bean Factory.
	 */
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
	 * This method is executen before every TestCase.
	 */
	@Before
	public void setUp() throws Exception {
		ClassPathResource res = new ClassPathResource(Constants.SPRINGBEANS_TEST);
		xbf = new XmlBeanFactory(res);
		ClassPathResource currentWorkingDir = new ClassPathResource(".");
		pathToFile = currentWorkingDir.getFile().getAbsolutePath();		
		studenten = generateStudentList();
	}

	/**
	 * This method is invoced after each TestCase.
	 */
	@After
	public void tearDown() throws Exception {
		studenten = null;
		xbf.destroySingletons();
	}

	/**
	 * Generate some testdata. The data is generated by the BeanFactory.
	 * 
	 * @return Testdata
	 */
	private List<Student> generateStudentList() {
		List<Student> studenten = new ArrayList<Student>();
		Student s1 = (Student) xbf.getBean("StudentAlexanderSchatten");
		s1.setId(1);
		Student s2 = (Student) xbf.getBean("StudentHubertMeixner");
		s2.setId(2);
		studenten.add(s1);
		studenten.add(s2);
		return studenten;
	}

	/**
	 * TestCase for an Export followed by an Import. Afterwards the imported
	 * data is compared with the data that was previous exported.
	 * 
	 */
	@Test
	public void testWriteRead() {
		final String filename = pathToFile + "/test/studenten.xml";
		Export export = (Export) xbf.getBean("Export");

		// save and re-read document
		try {
			export.write(studenten, filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// read data as List object
		List<Student> impStud = null;
		try {
			Import imp = (Import) xbf.getBean("Import");
			impStud = imp.read(filename);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		assertThat(studenten, is(not(impStud)));
		assertThat(impStud.size(), is(studenten.size()));
		// Cycle through the imported students and compare them to the exported ones
		int i = 0;
		for (Student stud: impStud) {
			assertThat(stud.getId(), is(studenten.get(i).getId()));
			assertThat(stud.getMatnr(), is(studenten.get(i).getMatnr()));
			assertThat(stud.getFirstname(), is(studenten.get(i).getFirstname()));
			assertThat(stud.getLastname(), is(studenten.get(i).getLastname()));
			assertThat(stud.getEmail(), is(studenten.get(i).getEmail()));
			i++;
		}
	}

}

package basis.test.dao;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import at.ac.tuwien.ifs.qse.se1.basis.helper.Constants;
import at.ac.tuwien.ifs.qse.se1.basis.helper.Logger;
import at.ac.tuwien.ifs.qse.se1.bo.Student;
import at.ac.tuwien.ifs.qse.se1.dao.IStudentDAO;

/**
 * Class containing the testcases for StudentDAO. In which the TestCases are
 * executed ist not specified. Therefore every TestCase has to be stand-alone
 * and <b>must not</b> have side-effects on other TestCases.
 * 
 * @author The SE-Team
 * @version 1.0
 * @see StudentDAO
 * 
 */
public class JdbcStudentTest extends TestCase {
	
	/**
	 * Data Access Object for Student, fetched with xbf.
	 */
	private IStudentDAO studentDAO = null;

	/**
	 * Spring Framework XML Bean Factory.
	 */
	private XmlBeanFactory xbf;
	
	private static Log log = Logger.getLogger(JdbcStudentTest.class);

	/**
	 * This method is executed before every testcase.
	 * 
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		ClassPathResource res = new ClassPathResource(Constants.SPRINGBEANS);
		xbf = new XmlBeanFactory(res);
		studentDAO = (IStudentDAO) xbf.getBean("StudentDAO");
	}

	/**
	 * This method is executed after each testcase.
	 * 
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		studentDAO = null;
		xbf.destroySingletons();
	}

	/**
	 * Test StudentBean initialisation.
	 * 
	 */
	public void testBeanInit() {
		Student student = (Student) xbf.getBean("StudentAlexanderSchatten");
		assertNotNull(student);
		assertEquals(1L, student.getId());
		assertEquals("Alexander", student.getFirstName());
		assertEquals("Schatten", student.getLastName());
		assertEquals("alexander@schatten.info", student.getEmail());
	}

	/**
	 * Test the method getStudent.
	 * 
	 * @see StudentDAO#getStudent(Long)
	 * 
	 */
	public void testGetStudent() {
		Student student = studentDAO.getStudent(new Long(0));
		assertNotNull(student);
		assertEquals("Hubert", student.getFirstName());
		assertEquals("Meixner", student.getLastName());
	}
	
	/**
	 * Test the method getStudents, ordered by Matrikelnummer.
	 * 
	 * @see StudentDAO#getStudents(String)
	 */
	public void testGetStudentsMatnr() {
		// Order by Matrikelnummer
		List<Student> students = studentDAO.getStudents("Matrikelnummer");
		assertNotNull(students);
		Student pstud = null;
		// See if the Matrikelnumbers in the list are actually in order
		for (Student stud : students) {
			if (pstud != null) {
				// ID from DB aswell as Matrikelnumber must be unique
				assertNotSame(pstud.getId(), stud.getId());
				assertNotSame(pstud.getMatnr(), stud.getMatnr());
				log.info("Comparing student " + stud.getMatnr() + " to student " + pstud.getMatnr());
				assertTrue(stud.getMatnr().compareTo(pstud.getMatnr()) > 0);
			}
			pstud = stud;
		}
	}
}

package at.ac.tuwien.ifs.bpse.core.test.persistence;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import at.ac.tuwien.ifs.bpse.dao.interfaces.IStudentDAO;
import at.ac.tuwien.ifs.bpse.dao.interfaces.IStudentDAO.SortOrder;
import at.ac.tuwien.ifs.bpse.domain.Student;

/**
 * Class containing the testcases for StudentDAO. 
 * 
 * The test assumes that there is specific test-data in the hsqldb database and also reads test-data from the beans.xml
 * 
 * The hsqldb database with the test-data can be found in 
 * 
 * /basic/src/test/resources/data
 * 
 * when "mvn compile" is executed, this database files are copied to the target folder which is:
 * 
 *  basic/target/test-classes/data
 *  
 *  This database is used for the tests (as it is defined in the src/test/resources/beans.xml). 
 *  Consequently, if you should "destroy" the test data in the target directory,
 *  just make an "mvn clean compile" and the correct database is again copied from the src/test directory.
 *  
 *  
 * One addtional thing should be noted: there are two beans.xml files and two database directories:
 * 
 * One set in the src/main directory the other set in the src/test directory.
 *  
 * This allows us to distinguish between running tests and running the application. For example: when running the application
 * changing data cannot destroy the data that we need for the tests.
 * 
 * Additionally in the "operative" beans.xml there is no test-data included.
 * 
 * 
 * @author The SE-Team
 * @version 1.0
 * @see StudentDAO
 * 
 */
public class JdbcStudentTest {
	
	/**
	 * Spring Framework Application Context (Bean Factory, Global Scope).
	 */
	private static ApplicationContext ac; 
	
	/**
	 * Data Access Object for Student, fetched with xbf.
	 */
	private IStudentDAO studentDAO = null;
	
	private static Logger log = Logger.getLogger(JdbcStudentTest.class);

	
	/**
	 * This method is executed before all testcases.
	 * 
	 */
	@BeforeClass
	public static void setUpBefore() throws Exception {
		// notice, that the TEST beans.xml is loaded!
		ac = new FileSystemXmlApplicationContext("classpath:test-beans.xml");
	}
	
	/**
	 * This method is executed before every testcase.
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		studentDAO = (IStudentDAO) ac.getBean("StudentDAO");
	}

	/**
	 * This method is executed after each testcase.
	 * 
	 */
	@After
	public void tearDown() throws Exception {
		studentDAO = null;
		//xbf.destroySingletons();
	}

	/**
	 * Test StudentBean initialisation.
	 * 
	 */
	@Test
	public void beanInit_shouldLoadStudentFromSpringBeanConfig() {
		Student student = (Student) ac.getBean("StudentAlexanderSchatten");
		assertThat(student, is(notNullValue()));
		assertThat(student.getId(), is(1));
		assertThat(student.getFirstname(), is("Alexander"));
		assertThat(student.getLastname(), is("Schatten"));
		assertThat(student.getEmail(), is("alexander@schatten.info"));
	}

	/**
	 * Test the method getStudent.
	 * 
	 * 
	 * @see StudentDAO#getStudent(Long)
	 * 
	 */
	@Test
	public void getStudent_shouldRetrieveStudentFromDB() {
		Student s = (Student)ac.getBean("StudentGet");
		Student stud = studentDAO.getStudent(s.getId());
		assertThat( stud.getFirstname(), is(s.getFirstname()) );
		assertThat( stud.getMatnr(), is(s.getMatnr()) );
		assertThat( stud.getEmail(), is(s.getEmail()) );
		assertThat( stud.getLastname(), is(s.getLastname()) );
		assertThat( stud.getFullname(), is(s.getFullname()) );
		assertThat( stud.getId(), is(s.getId()) );
	}

	/**
	 * Test the method addStudent, deleteStudent.
	 * 
	 * actually this is a complete CRD lifecycle
	 * 
	 * (create, read, delete)
	 * 
	 * @see StudentDAO#addStudent(Student)
	 * @see StudentDAO#deleteStudent(Long)
	 * 
	 */
	@Test
	public void saveStudent_shouldAddStudentToDB() {
		// get a test-dataset from Spring config
		Student s = (Student)ac.getBean("StudentAdd");
		Integer oldId = s.getId();
		String matnr = s.getMatnr();
		String first = s.getFirstname();
		String last = s.getLastname();
		String full = first + " " + last;
		String email = s.getEmail();
		
		// Add Student
		Student stud = studentDAO.saveStudent(s);	
		// new ID should be different from old ID
		assertThat(stud.getId(), is(not(oldId)));
		//assertThat( stud.getId(), is(43));
		assertThat( stud.getMatnr(), is(matnr) );
		assertThat( stud.getFirstname(), is(first) );
		assertThat( stud.getLastname(), is(last) );
		assertThat( stud.getFullname(), is(full) );
		assertThat( stud.getEmail(), is(email) );
		
		// check if student was added
		// read student from database using the new generated ID
		// then compare if the newly read data equals the old one,
		// then check is complete
		Student checkStudent = studentDAO.getStudent(stud.getId());
		assertThat(checkStudent.getFirstname(), is(stud.getFirstname()));
		assertThat(checkStudent.getLastname(), is(stud.getLastname()));
		assertThat(checkStudent.getMatnr(), is(stud.getMatnr()));
	}
	
	@Test
	public void deleteStudent_shouldDeleteStudentFromDB() {
		// get a test-dataset from Spring config
		Student s = (Student)ac.getBean("StudentDelete");
		// Delete Student
		boolean ret = studentDAO.deleteStudent(s.getId());
		assertThat(ret, is(true));
		// check if student was deleted
		// try to retrieve student by ID again
		Student stud = studentDAO.getStudent(s.getId());
		// no dataset must be found (as it was deleted above)
		assertThat(stud, is(nullValue()));
	}
	
	/**
	 * Specifically test the update method 
	 *
	 * @see StudentDAO#updateStudent(Long, Student)
	 */
	@Test
	public void updateStudent_shouldUpdateStudentInDB() {
		// get a test-dataset from Spring config
		Student s = (Student)ac.getBean("StudentUpdate");
		int id = s.getId();
		String matnr = s.getMatnr();
		String first = s.getFirstname();
		String last = s.getLastname();
		String email = s.getEmail();
		// assuming this dataset already exists in the DB
		// change the student data and update this student in database
		s.setFirstname(first + "2");
		s.setLastname(last + "2");
		s.setEmail(email + ".at");
		Student stud = studentDAO.updateStudent(s);
		assertThat( stud, is(notNullValue()) );
		assertThat( stud.getId(), is(id) );
		assertThat( stud.getMatnr(), is(matnr) );
		assertThat( stud.getFirstname(), is(first+"2") );
		assertThat( stud.getLastname(), is(last+"2") );
		assertThat( stud.getEmail(), is(email+".at") );

		// retrieve student again from database and check if all fields are updated correctly
		Student newStudent = studentDAO.getStudent(id);
		assertThat( newStudent, is(notNullValue()) );
		assertThat( newStudent.getId(), is(id) );
		assertThat( newStudent.getMatnr(), is(matnr) );
		assertThat( newStudent.getFirstname(), is(first+"2") );
		assertThat( newStudent.getLastname(), is(last+"2") );
		assertThat( newStudent.getEmail(), is(email+".at") );
	}

	/**
	 * Test the method getStudents, ordered by Matrikelnummer.
	 * 
	 * @see StudentDAO#getStudents(String)
	 */
	@Ignore
	public void getStudents_shouldRetrieveAllStudentsOrderedByMatnrFromDB() {
		// Order by Matrikelnummer
		List<Student> students = studentDAO.getStudents(SortOrder.StudentId);
		assertThat(students, is(notNullValue()));
		Student pstud = null;
		// See if the Matrikelnumbers in the list are actually in order
		for (Student stud : students) {
			if (pstud != null) {
				// ID from DB aswell as Matrikelnumber must be unique
				assertThat(pstud.getId(), is(not(stud.getId())));
				assertThat(pstud.getMatnr(), is(not(stud.getMatnr())));
				log.info("Comparing student " + stud.getMatnr() + " to student " + pstud.getMatnr());
				assertThat(stud.getMatnr().compareTo(pstud.getMatnr()), is(greaterThan(0)));
			}
			pstud = stud;
		}
	}

	/**
	 * Test the method getStudents, ordered by Nachname.
	 * 
	 * @see StudentDAO#getStudents(String)
	 * 
	 */
	@Ignore
	public void getStudents_shouldRetrieveAllStudentsOrderedByNachnameFromDB() {
		// Order by Nachname
		List<Student> students = studentDAO.getStudents(SortOrder.LastName);
		assertThat(students, is(notNullValue()));
		Student pstud = null;
		// See if the Last Names (Nachname) in the list are actually in order
		for (Student stud : students) {
			if (pstud != null) {
				// ID from DB aswell as Matrikelnumber must be unique
				assertThat(pstud.getId(), is(not(stud.getId())));
				assertThat(pstud.getMatnr(), is(not(stud.getMatnr())));
				assertThat(stud.getLastname().compareTo(pstud.getLastname()), is(greaterThanOrEqualTo(0)));
			}
			pstud = stud;
		}
	}

}

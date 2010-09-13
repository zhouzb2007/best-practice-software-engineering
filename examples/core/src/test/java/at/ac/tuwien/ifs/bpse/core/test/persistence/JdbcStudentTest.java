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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
 * This allows uds to distinguish between running tests and running the application. For example: when running the application
 * changing data cannot destroy the data that we need for the tests.
 * 
 * Additionally in the "operative" beans.xml there is no test-data included.
 * 
 * 
 * @author The SE-Team
 * @version 2.1
 * @see StudentDAO
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-beans.xml"})
public class JdbcStudentTest {
	
	/**
	 * Spring Framework Application Context (Bean Factory).
	 */
	@Autowired
	private ApplicationContext ac; 
	
	/**
	 * Data Access Object for Student
	 */
	@Autowired
	private IStudentDAO studentDAO;
	
	private static Logger log = Logger.getLogger(JdbcStudentTest.class);

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
	 * Test the method saveStudent
	 * 
	 * 
	 * @see StudentDAO#addStudent(Student)
	 * @see StudentDAO#deleteStudent(Long)
	 * 
	 */
	@Test
	public void saveStudent_shouldNotAddNonUserStudentToDB() {
		// get a test-dataset from Spring config
		Student s = (Student)ac.getBean("NonUserStudentAdd");
		int sid = s.getId();
		// Add Student
		Student retstud = studentDAO.saveStudent(s);	
		// DAO should return null
		assertThat(retstud, is(nullValue()));
		// Student should not exist in DB
		Student s2 = studentDAO.getStudent(sid);
		assertThat(s2, is(nullValue()));
	}
	
	@Test
	public void saveStudent_shouldAddStudentToDBAsEnabledUser() {
		// get a test-dataset from Spring config
		Student s = (Student)ac.getBean("UserStudentAdd");
		Integer oldId = s.getId();
		String matnr = s.getMatnr();
		String first = s.getFirstname();
		String last = s.getLastname();
		String full = first + " " + last;
		String email = s.getEmail();
		
		// Add Student
		Student stud = studentDAO.saveStudent(s);
		assertThat( stud, is(notNullValue()) );
		// new ID should be different from old ID
		assertThat(stud.getId(), is(not(oldId)));
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
		// assuming this dataset already exists in the DB
		// and the dataset retrieved from test-beans.xml already contains slight changes to the data
		Student stud = studentDAO.updateStudent(s);
		assertThat( stud, is(notNullValue()) );
		assertThat( stud.getId(), is(id) );
		assertThat( stud.getMatnr(), is(matnr) );
		assertThat( stud.getFirstname(), is(first) );
		assertThat( stud.getLastname(), is(last) );
		//TODO: changing Email changes Username
		//assertThat( stud.getEmail(), is(email+".at") );

		// retrieve student again from database and check if all fields are updated correctly
		Student newStudent = studentDAO.getStudent(id);
		assertThat( newStudent, is(notNullValue()) );
		assertThat( newStudent.getId(), is(id) );
		assertThat( newStudent.getMatnr(), is(matnr) );
		assertThat( newStudent.getFirstname(), is(first) );
		assertThat( newStudent.getLastname(), is(last) );
		//assertThat( newStudent.getEmail(), is(email+".at") );
	}

	/**
	 * Test the method getStudents, ordered by Matrikelnummer.
	 * 
	 * @see StudentDAO#getStudents(String)
	 */
	@Test
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
	@Test
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

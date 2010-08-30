package at.ac.tuwien.ifs.bpse.core.test.persistence;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import at.ac.tuwien.ifs.bpse.dao.interfaces.IStudentDAO.SortOrder;
import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.persistence.SqlSessionFactoryBean;
import at.ac.tuwien.ifs.bpse.persistence.interfaces.StudentMapper;

public class StudentMapperTest {

	/**
	 * Spring Framework Application Context (Bean Factory, Global Scope).
	 */
	private static ApplicationContext ac; 
	
	/**
	 * SessionFactory is a Spring managed Singleton. (Local Scope)
	 */
	SqlSessionFactoryBean sqlSessionFactory;
	
	/**
	 * (Local/Class Scope)
	 */
	SqlSession sqlSession;
	
	/**
	 * Data Access Object for Student, fetched with ac. (Local/Method Scope)
	 */
	private StudentMapper studentMapper;

	private static Logger log = Logger.getLogger(StudentMapperTest.class);

	private Connection conn;

	private ScriptRunner runner;
	
	private final String database = "jdbc:hsqldb:mem:bpse-sample-medium";
	
	private final String dbUser = "sa";
	
	private final String dbPassword = "";
	
	private final String dbSchema = "bpse-sample-medium-TestSchema.sql";
	
	private final String destroySchema = "bpse-sample-medium-destroySchema.sql";
	
	private final String dbData = "bpse-sample-medium-TestData.sql";

	private Student testStudentGet;

	private Student testStudentAdd;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		// notice, that the TEST beans.xml is loaded!
		ac = new FileSystemXmlApplicationContext("classpath:test-beans.xml");
	}
	
	@Before
	public void setUp() {
		try {
			conn = DriverManager.getConnection(database, dbUser, dbPassword);
			runner = new ScriptRunner(conn);
			runner.runScript(Resources.getResourceAsReader(dbSchema));
			runner.runScript(Resources.getResourceAsReader(dbData));
		} catch (SQLException e) {
			log.info("Problem initializing DB connection " + database
					+ " with user " + dbUser + " and password " + dbPassword);
			e.printStackTrace();
		} catch (IOException e) {
			log.info("DB Script " + dbData + " not found");
			e.printStackTrace();
		}
		
		sqlSessionFactory = (SqlSessionFactoryBean) ac.getBean("sqlSessionFactory");
		sqlSession = sqlSessionFactory.openSession();
		studentMapper = sqlSession.getMapper(StudentMapper.class);
		
		testStudentGet = (Student)ac.getBean("StudentGet");
		testStudentAdd = (Student)ac.getBean("StudentAdd");
	}

	@After
	public void tearDown() {
		log.info("Test done, closing sqlSession");
		sqlSession.close();
		
		try {
			conn = DriverManager.getConnection(database, dbUser, dbPassword);
			runner = new ScriptRunner(conn);
			runner.runScript(Resources.getResourceAsReader(destroySchema));
		} catch (SQLException e) {
			log.info("Problem initializing DB connection " + database
					+ " with user " + dbUser + " and password " + dbPassword);
			e.printStackTrace();
		} catch (IOException e) {
			log.info("DB Script " + destroySchema + " not found");
			e.printStackTrace();
		}
		
		testStudentGet = null;
		testStudentAdd = null;
		System.gc();
	}

	@Test
	public void selectStudent_shouldGetStudentFromDB() {
		Student stud = studentMapper.selectStudent(testStudentGet.getId());
		assertThat( stud.getFirstname(), is(testStudentGet.getFirstname()) );
		assertThat( stud.getMatnr(), is(testStudentGet.getMatnr()) );
		assertThat( stud.getEmail(), is(testStudentGet.getEmail()) );
		assertThat( stud.getLastname(), is(testStudentGet.getLastname()) );
		assertThat( stud.getFullname(), is(testStudentGet.getFullname()) );
		assertThat( stud.getId(), is(testStudentGet.getId()) );
	}
	
	@Test
	public void selectStudentByMatrNr_shouldGetStudentFromDB() {
		Student stud = studentMapper.selectStudentByMatrNr(testStudentGet.getMatnr());
		assertThat( stud.getFirstname(), is(testStudentGet.getFirstname()) );
		assertThat( stud.getMatnr(), is(testStudentGet.getMatnr()) );
		assertThat( stud.getEmail(), is(testStudentGet.getEmail()) );
		assertThat( stud.getLastname(), is(testStudentGet.getLastname()) );
		assertThat( stud.getFullname(), is(testStudentGet.getFullname()) );
		assertThat( stud.getId(), is(testStudentGet.getId()) );
	}
	
	@Test
	public void insertStudent_shouldSaveStudentToDB() throws CloneNotSupportedException {
		
		final Student origStud = (Student)testStudentAdd.clone();
		
		assertThat(testStudentAdd.getId(), is(2));
		int ret = studentMapper.insertStudent(testStudentAdd);		
		assertThat(ret, is(1));
		assertThat(testStudentAdd.getId(), is(43));
		Student stud = studentMapper.selectStudent(testStudentAdd.getId());
		
		assertThat( stud.getMatnr(), is(origStud.getMatnr()) );
		assertThat( stud.getFirstname(), is(origStud.getFirstname()) );
		assertThat( stud.getLastname(), is(origStud.getLastname()) );
		assertThat( stud.getFullname(), is(origStud.getFullname()) );
		assertThat( stud.getEmail(), is(origStud.getEmail()) );
	}
	
	@Test
	public void deleteStudent_shouldDeleteStudentFromDB() {
		Student s = (Student)ac.getBean("StudentUpdateDelete");
		int ret = studentMapper.deleteStudent(s.getId());
		log.info("DELETE Return Value: " + ret);
		Student stud = studentMapper.selectStudent(s.getId());
		assertNull(stud);
	}
	
	@Test
	public void updateStudent_shouldUpdateStudentInDB() {
		Student s = (Student)ac.getBean("StudentUpdateDelete");
		int id = s.getId();
		String matnr = s.getMatnr();
		String first = s.getFirstname();
		String last = s.getLastname();
		String full = first + " " + last;
		String email = s.getEmail();
		
		int ret = studentMapper.updateStudent(s);
		log.info("UPDATE Return Value: " + ret);
		Student stud = studentMapper.selectStudent(id);
		assertThat( stud.getId(), is(id) );
		assertThat( stud.getMatnr(), is(matnr) );
		assertThat( stud.getFirstname(), is(first) );
		assertThat( stud.getLastname(), is(last) );
		assertThat( stud.getFullname(), is(full) );
		assertThat( stud.getEmail(), is(email) );
	}
	
	@Test
	public void findStudents() {
		List<Student> studs = studentMapper.findStudents("%");
		assertThat(studs.size(), is(3));
		assertThat(studs.get(0).getMatnr(), is("0027226"));
		assertThat(studs.get(1).getMatnr(), is("1234027"));
		assertThat(studs.get(2).getMatnr(), is("8027164"));
		
		studs = studentMapper.findStudents("%odrig%", SortOrder.matnr);
		assertThat(studs.size(), is(2));
		assertThat(studs.get(0).getMatnr(), is("0027226"));
		assertThat(studs.get(1).getMatnr(), is("8027164"));

		//assertThat(stud1.getMatnr(), is());
		studs = studentMapper.findStudents("%027%", SortOrder.lastname);
		assertThat(studs.size(), is(3));
		assertThat(studs.get(0).getLastname(), is("Rodrigues"));
		assertThat(studs.get(1).getLastname(), is("Rodrigues2"));
		assertThat(studs.get(2).getLastname(), is("Studentos"));
	}

	@Ignore
	public void getAllStudents() {
		List<Student> studs = studentMapper.selectAllStudents(SortOrder.lastname);
		assertThat(studs.size(), is(3));
		assertThat(studs.get(0).getLastname(), is("Rodrigues"));
		assertThat(studs.get(1).getLastname(), is("Rodrigues2"));
		assertThat(studs.get(2).getLastname(), is("Studentos"));
		
		studs = studentMapper.selectAllStudents();
		assertThat(studs.size(), is(3));
		assertThat(studs.get(0).getLastname(), is("Rodrigues"));
		assertThat(studs.get(1).getLastname(), is("Rodrigues2"));
		assertThat(studs.get(2).getLastname(), is("Studentos"));
	}
	
}

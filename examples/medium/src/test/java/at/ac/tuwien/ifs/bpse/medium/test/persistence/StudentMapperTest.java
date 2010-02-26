package at.ac.tuwien.ifs.bpse.medium.test.persistence;

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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.medium.persistence.SqlSessionFactoryBean;
import at.ac.tuwien.ifs.bpse.medium.persistence.interfaces.StudentMapper;

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
	}

	@Test
	public void selectStudent_shouldGetStudentFromDB() {
		Student s = (Student)ac.getBean("StudentGet");
		Student stud = studentMapper.selectStudent(s.getId());
		assertThat( stud.getFirstname(), is(s.getFirstname()) );
		assertThat( stud.getMatnr(), is(s.getMatnr()) );
		assertThat( stud.getEmail(), is(s.getEmail()) );
		assertThat( stud.getLastname(), is(s.getLastname()) );
		assertThat( stud.getFullname(), is(s.getFullname()) );
		assertThat( stud.getId(), is(s.getId()) );
	}
	
	@Test
	public void selectStudentByMatrNr_shouldGetStudentFromDB() {
		Student s = (Student)ac.getBean("StudentGet");
		Student stud = studentMapper.selectStudentByMatrNr(s.getMatnr());
		assertThat( stud.getFirstname(), is(s.getFirstname()) );
		assertThat( stud.getMatnr(), is(s.getMatnr()) );
		assertThat( stud.getEmail(), is(s.getEmail()) );
		assertThat( stud.getLastname(), is(s.getLastname()) );
		assertThat( stud.getFullname(), is(s.getFullname()) );
		assertThat( stud.getId(), is(s.getId()) );
	}
	
	@Test
	public void insertStudent_shouldSaveStudentToDB() {
		Student s = (Student)ac.getBean("StudentAdd");
		assertThat(s.getId(), is(2));
		
		//int id = s.getId();
		String matnr = s.getMatnr();
		String first = s.getFirstname();
		String last = s.getLastname();
		String full = first + " " + last;
		String email = s.getEmail();
		
		int ret = studentMapper.insertStudent(s);		
		assertThat(ret, is(1));
		assertThat(s.getId(), is(43));
		Student stud = studentMapper.selectStudent(s.getId());
		
		assertThat( stud.getMatnr(), is(matnr) );
		assertThat( stud.getFirstname(), is(first) );
		assertThat( stud.getLastname(), is(last) );
		assertThat( stud.getFullname(), is(full) );
		assertThat( stud.getEmail(), is(email) );
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
		List<Student> studs = studentMapper.findStudents("%odrig%");
		assertThat(studs.size(), is(2));
		List<Student> studs2 = studentMapper.findStudents("%027%");
		assertThat(studs2.size(), is(3));
	}

}

package at.ac.tuwien.ifs.bpse.persistence;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	private StudentMapper studentDAO;

	private static Logger log = Logger.getLogger(StudentMapperTest.class);

	private static Connection conn;

	private static ScriptRunner runner;
	
	private static final String database = "jdbc:hsqldb:mem:bpse-sample-medium";
	
	private static final String dbUser = "sa";
	
	private static final String dbPassword = "";
	
	private static final String dbSchema = "bpse-sample-medium-TestSchema.sql";
	
	private static final String dbData = "bpse-sample-medium-TestData.sql";
	
	@BeforeClass
	public static void setUpBeforeTests() {
		// notice, that the TEST beans.xml is loaded!
		ac = new FileSystemXmlApplicationContext("classpath:test-beans.xml");
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
	}
	
	@Before
	public void setUp() {
		sqlSessionFactory = (SqlSessionFactoryBean) ac.getBean("sqlSessionFactory");
		sqlSession = sqlSessionFactory.openSession();
		studentDAO = sqlSession.getMapper(StudentMapper.class);
	}

	@After
	public void tearDown() throws Exception {
		log.info("Test done, closing sqlSession");
		sqlSession.close();
	}

	@Test
	public void selectStudent_shouldGetStudentFromDB() {
		Student s = (Student)ac.getBean("StudentGet");
		Student stud = studentDAO.selectStudent(s.getId());
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
		Student stud = studentDAO.selectStudentByMatrNr(s.getMatnr());
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
		studentDAO.insertStudent(s);
		Student stud = studentDAO.selectStudent(s.getId());
		assertThat( stud.getFirstname(), is(s.getFirstname()) );
		assertThat( stud.getMatnr(), is(s.getMatnr()) );
		assertThat( stud.getEmail(), is(s.getEmail()) );
		assertThat( stud.getLastname(), is(s.getLastname()) );
		assertThat( stud.getFullname(), is(s.getFullname()) );
		assertThat( stud.getId(), is(s.getId()) );
	}
	
	@Test
	public void updateStudent_shouldUpdateStudentInDB() {
		Student s = (Student)ac.getBean("StudentUpdateDelete");
		int ret = studentDAO.updateStudent(s);
		log.info("UPDATE Return Value: " + ret);
		Student stud = studentDAO.selectStudent(s.getId());
		assertThat( stud.getFirstname(), is(s.getFirstname()) );
		assertThat( stud.getMatnr(), is(s.getMatnr()) );
		assertThat( stud.getEmail(), is(s.getEmail()) );
		assertThat( stud.getLastname(), is(s.getLastname()) );
		assertThat( stud.getFullname(), is(s.getFullname()) );
		assertThat( stud.getId(), is(s.getId()) );
	}
	
	@Test
	public void deleteStudent_shouldDeleteStudentFromDB() {
		Student s = (Student)ac.getBean("StudentUpdateDelete");
		int ret = studentDAO.deleteStudent(s.getId());
		log.info("DELETE Return Value: " + ret);
		Student stud = studentDAO.selectStudent(s.getId());
		assertNull(stud);
	}

}

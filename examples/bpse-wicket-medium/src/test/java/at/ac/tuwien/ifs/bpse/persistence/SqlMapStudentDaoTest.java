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
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.persistance.dao.SqlMapStudentDao;

public class SqlMapStudentDaoTest {

	/**
	 * Spring Framework Application Context (Bean Factory, Global Scope).
	 */
	private static ApplicationContext ac; 
	
	private SqlMapStudentDao studentDAO;
	
	private static Logger log = Logger.getLogger(SqlMapStudentDaoTest.class);

	private Connection conn;

	private ScriptRunner runner;
	
	private final String database = "jdbc:hsqldb:mem:bpse-sample-medium";
	
	private final String dbUser = "sa";
	
	private final String dbPassword = "";
	
	private final String dbSchema = "bpse-sample-medium-TestSchema.sql";
	
	private final String destroySchema = "bpse-sample-medium-destroySchema.sql";
	
	private final String dbData = "bpse-sample-medium-TestData.sql";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
			log.info("DB Script " + dbSchema + "\n or \n" + dbData + " not found");
			e.printStackTrace();
		}
		
		studentDAO = (SqlMapStudentDao) ac.getBean("StudentDAO");
	}

	@After
	public void tearDown() {
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
		Student stud = studentDAO.getStudent(s.getId());
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
		Student stud = studentDAO.getStudentByMatrNr(s.getMatnr());
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
		boolean ret = studentDAO.deleteStudent(s.getId());
		assertThat(ret, is(true));
		Student stud = studentDAO.getStudent(s.getId());
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
		Student stud = studentDAO.updateStudent(s);
		assertThat( stud.getId(), is(id) );
		assertThat( stud.getMatnr(), is(matnr) );
		assertThat( stud.getFirstname(), is(first) );
		assertThat( stud.getLastname(), is(last) );
		assertThat( stud.getEmail(), is(email) );
		assertThat( stud.getFullname(), is(full) );
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
		
		Student stud = studentDAO.saveStudent(s);	

		assertThat( stud.getId(), is(43));
		assertThat( stud.getMatnr(), is(matnr) );
		assertThat( stud.getFirstname(), is(first) );
		assertThat( stud.getLastname(), is(last) );
		assertThat( stud.getFullname(), is(full) );
		assertThat( stud.getEmail(), is(email) );
	}
}

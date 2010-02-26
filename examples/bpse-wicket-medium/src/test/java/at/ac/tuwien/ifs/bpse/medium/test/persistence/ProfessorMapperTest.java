package at.ac.tuwien.ifs.bpse.medium.test.persistence;

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

import at.ac.tuwien.ifs.bpse.domain.Professor;
import at.ac.tuwien.ifs.bpse.medium.persistence.SqlSessionFactoryBean;
import at.ac.tuwien.ifs.bpse.medium.persistence.interfaces.ProfessorMapper;

public class ProfessorMapperTest {

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
	 * Data Access Object for Professor, fetched with ac. (Local/Method Scope)
	 */
	private ProfessorMapper ProfessorMapper;

	private static Logger log = Logger.getLogger(ProfessorMapperTest.class);

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
		ProfessorMapper = sqlSession.getMapper(ProfessorMapper.class);
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
	public void selectProfessor_shouldGetProfessorFromDB() {
		Professor p = (Professor)ac.getBean("ProfessorGet");
		Professor newp = ProfessorMapper.selectProfessor(p.getId());
		assertThat( newp.getFirstname(), is(p.getFirstname()) );
		assertThat( newp.getOfficenr(), is(p.getOfficenr()) );
		assertThat( newp.getEmail(), is(p.getEmail()) );
		assertThat( newp.getLastname(), is(p.getLastname()) );
		assertThat( newp.getFullname(), is(p.getFullname()) );
		assertThat( newp.getId(), is(p.getId()) );
	}
	
	@Test
	public void selectProfessorByOfficeNr_shouldGetProfessorFromDB() {
		Professor p = (Professor)ac.getBean("ProfessorGet");
		Professor newp = ProfessorMapper.selectProfessorByOfficeNr(p.getOfficenr());
		assertThat( newp.getFirstname(), is(p.getFirstname()) );
		assertThat( newp.getOfficenr(), is(p.getOfficenr()) );
		assertThat( newp.getEmail(), is(p.getEmail()) );
		assertThat( newp.getLastname(), is(p.getLastname()) );
		assertThat( newp.getFullname(), is(p.getFullname()) );
		assertThat( newp.getId(), is(p.getId()) );
	}
	
	@Test
	public void insertProfessor_shouldSaveProfessorToDB() {
		Professor p = (Professor)ac.getBean("ProfessorAdd");
		assertThat(p.getId(), is(2));
		
		//int id = s.getId();
		String officenr = p.getOfficenr();
		String first = p.getFirstname();
		String last = p.getLastname();
		String full = first + " " + last;
		String email = p.getEmail();
		
		int ret = ProfessorMapper.insertProfessor(p);		
		assertThat(ret, is(1));
		assertThat(p.getId(), is(4));
		Professor newp = ProfessorMapper.selectProfessor(p.getId());
		
		assertThat( newp.getOfficenr(), is(officenr) );
		assertThat( newp.getFirstname(), is(first) );
		assertThat( newp.getLastname(), is(last) );
		assertThat( newp.getFullname(), is(full) );
		assertThat( newp.getEmail(), is(email) );
	}
	
	@Test
	public void deleteProfessor_shouldDeleteProfessorFromDB() {
		Professor p = (Professor)ac.getBean("ProfessorUpdateDelete");
		int ret = ProfessorMapper.deleteProfessor(p.getId());
		log.info("DELETE Return Value: " + ret);
		Professor newp = ProfessorMapper.selectProfessor(p.getId());
		assertNull(newp);
	}
	
	@Test
	public void updateProfessor_shouldUpdateProfessorInDB() {
		Professor p = (Professor)ac.getBean("ProfessorUpdateDelete");
		int id = p.getId();
		String officenr = p.getOfficenr();
		String first = p.getFirstname();
		String last = p.getLastname();
		String full = first + " " + last;
		String email = p.getEmail();
		
		int ret = ProfessorMapper.updateProfessor(p);
		log.info("UPDATE Return Value: " + ret);
		Professor newp = ProfessorMapper.selectProfessor(id);
		assertThat( newp.getId(), is(id) );
		assertThat( newp.getOfficenr(), is(officenr) );
		assertThat( newp.getFirstname(), is(first) );
		assertThat( newp.getLastname(), is(last) );
		assertThat( newp.getFullname(), is(full) );
		assertThat( newp.getEmail(), is(email) );
	}

}
